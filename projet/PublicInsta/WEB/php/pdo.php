<?php
session_start();

if(!isset($_SESSION['username'])) {
  $_SESSION['username'] = "guest";
  $_SESSION['user_id'] = "";
  $_SESSION['email'] = "";
  $_SESSION['profile_photo'] = "";
  $_SESSION['creation_date'] = "";
}

connect();

/*==========================================================================*/
/*	Function connect to database
/*		- Author(s) : Mario Ferreira & Thibaud Duchoud
/*		- Input
/*		- Output
/*==========================================================================*/
function connect() {
  global $pdo;
  global $crysession;
  global $cryip;

  $dbhost = 'localhost';
  $dbuser = 'root';
  $dbpass = 'pass';
  $dbname = 'publicInsta';

  $GLOBALS['crysession'] = md5(session_id());
  $GLOBALS['cryip'] = md5($_SERVER["REMOTE_ADDR"]);

  if($pdo == null) {
    $dsn = 'mysql:host='.$dbhost.';dbname='.$dbname.';charset=utf8';
    $pdo = new PDO($dsn,$dbuser,$dbpass);

    /* Correction du bug de caractères spéciaux dans la base de données */
    $pdo->exec('SET NAMES utf8');

    $pdo->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);
  }
}

/*==========================================================================*/
/*	Function check user
/*		- Author(s) : Mario Ferreira & Thibaud Duchoud
/*		- Input
/*			- $email	: user's email address
/*			- $pass	: user's password
/*		- Output
/*			- return 'username' if user and password ok
/*			- else return false
/*==========================================================================*/
function checkUser($email, $pass) {
  $crypass = hash ("sha256", $pass, false);

  $sql = 'SELECT username
    FROM user
    WHERE email = ? AND password = ?';
  $sqlp = $GLOBALS["pdo"]->prepare($sql);
  $fields = array($email,$crypass);
  $sqlp->execute($fields);

  if($sqlp->rowCount() == 1) {
    $userTmp = $sqlp->fetch();
    return $userTmp['username'];
  }else {
    return false;
  }
}

/*==========================================================================*/
/*	Function login (update sessionid)
/*		- Author(s) : Mario Ferreira & Thibaud Duchoud
/*		- Input
/*			- $email	: user's e-mail
/*		- Output
/*==========================================================================*/
function setLogged($email) {
  $sql = 'UPDATE user SET session_id = ?, ip = ? WHERE email = ?';
  $sqlp = $GLOBALS["pdo"]->prepare($sql);
  $fields = array($GLOBALS['crysession'],$GLOBALS['cryip'],$email);
  $sqlp->execute($fields);

  $sql = 'SELECT id, email, username, profile_photo, creation_date
    FROM user
    WHERE email = ?';

  $sqlp = $GLOBALS["pdo"]->prepare($sql);
  $fields = array($email);
  $sqlp->execute($fields);

  if($sqlp->rowCount() == 1) {
    $userTmp = $sqlp->fetch();
    $_SESSION['username'] = $userTmp['username'];
    $_SESSION['user_id'] = $userTmp['id'];
    $_SESSION['email'] = $userTmp['email'];
    $_SESSION['profile_photo'] = $userTmp['profile_photo'];
    $datetime = new DateTime($userTmp['creation_date']);
    $_SESSION['creation_date'] = $datetime->getTimestamp();
  }
}

function registerUser($email, $pass, $username, $profile_photo) {
  if(empty($email)) {
    $email = NULL;
  }

  $crypass = NULL;

  if(!empty($pass)) {
    $crypass = hash ("sha256", $pass, false);
  }

  if(empty($username)) {
    $username = NULL;
  }
  $sql = 'INSERT INTO user (email, password, username, profile_photo)
    VALUES (?, ?, ?, ?);';
  $sqlp = $GLOBALS["pdo"]->prepare($sql);
  $fields = array($email, $crypass, $username, $profile_photo);
  $sqlp->execute($fields);

  if($sqlp->rowCount() == 1) {
    mkdir("img/users/".$username, 0777);
    return true;
  }else {
    return false;
  }
}

function insertImage($user_id, $path, $text) {
  if(empty($user_id)) {
    $user_id = NULL;
  }

  if(empty($path)) {
    $path = NULL;
  }

  if(empty($text)) {
    $text = NULL;
  }
  $sql = 'INSERT INTO image (path, text, user_id)
    VALUES (?, ?, ?);';
  $sqlp = $GLOBALS["pdo"]->prepare($sql);
  $fields = array($path, $text, $user_id,);
  $sqlp->execute($fields);

  if($sqlp->rowCount() == 1) {
    return true;
  }else {
    return false;
  }
}


/*==========================================================================*/
/*	Function check connected
/*		- Author(s) : Mario Ferreira & Thibaud Duchoud
/*		- Input
/*		- Output
/*			- return true if connected
/*			- else return false
/*==========================================================================*/
function loggedIn() {
  $sql = 'SELECT username FROM user WHERE session_id = ? AND ip = ?';
  $sqlp = $GLOBALS["pdo"]->prepare($sql);
  $fields = array($GLOBALS['crysession'],$GLOBALS['cryip']);
  $sqlp->execute($fields);

  if($sqlp->rowCount() == 1) {
    return true;
  }else {
    return false;
  }
}

/*==========================================================================*/
/*	Function logout
/*		- Author(s) : Mario Ferreira & Thibaud Duchoud
/*		- Input
/*		- Output
/*==========================================================================*/
function logout () {
  $sql = 'UPDATE user SET session_id = NULL, ip = NULL WHERE session_id = ? AND ip = ?';
  $sqlp = $GLOBALS["pdo"]->prepare($sql);
  $fields = array($GLOBALS['crysession'],$GLOBALS['cryip']);
  $sqlp->execute($fields);

  session_unset();

  session_destroy();

  return true;
}

/*==========================================================================*/
/*	Function contrôle syntaxe e-mail
/*		- Author(s) : Mario Ferreira & Thibaud Duchoud
/*		- Input
/*		- Output
/*==========================================================================*/
function isEmailValidRegex($email) {
  if(preg_match("#^[a-z0-9._-]+@[a-z0-9._-]{2,}\.[a-z]{2,4}$#", $email)) {
    return true;
  }else {
    return false;
  }
}

/*==========================================================================*/
/*	Function contrôle filtre e-mail php
/*		- Author(s) : Mario Ferreira & Thibaud Duchoud
/*		- Input
/*		- Output
/*==========================================================================*/
function isEmailValidFilter($email) {
  if(filter_var($email, FILTER_VALIDATE_EMAIL) === FALSE) {
    return false;
  }else {
    return true;
  }
}

/*==========================================================================*/
/*	Function contrôle dns domaine e-mail
/*		- Author(s) : Mario Ferreira & Thibaud Duchoud
/*		- Input
/*		- Output
/*==========================================================================*/
function isEmailValidDomain($email) {
  list($user,$domain) = split('@',$email);
  return checkdnsrr($domain,'MX');
}


// POUR L'instant QUE LES POST ET GET
// Exemple de post : callApi('POST', 'http://localhost:8080/MoussaRaser/api/users', array('firstname' => 'dsadsad', 'lastname' => 'dsadadsd'));
// Exemple de get : $endUsers = json_decode(callApi('GET', 'http://localhost:8080/MoussaRaser/api/users'))
function callApi($method, $url, $data = false)
{
    $url .= '?apiKey=1d62fc14560843b1b519b8da0df28f2e';

    $ch = curl_init($url);

    switch($method) {
        case "POST":
            curl_setopt($ch, CURLOPT_POST, 1);
            curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));
            curl_setopt($ch, CURLOPT_HTTPHEADER, array(                                                                          
                'Content-Type: application/json',                                                                                
                'Content-Length: ' . strlen(json_encode($data)))                                                                       
            );
            break;

        case "PUT":
            break;
    }

    curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);

    $result = curl_exec($ch);

    curl_close($ch);

    return $result;

}

?>
