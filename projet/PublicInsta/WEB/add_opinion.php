<?php

require_once 'php/pdo.php';

if( isset( $_POST['isPositive'] ) && isset( $_POST['imageId'] ) && !empty( $_POST['imageId'] ) ) {

  $sql = 'SELECT * FROM opinion WHERE user_id = ? AND image_id = ?';
  $sqlp = $GLOBALS["pdo"]->prepare($sql);
  $fields = array($_SESSION['user_id'], $_POST['imageId']);
  $sqlp->execute($fields);

  // Un opinion par utilisateur par image
  if($sqlp->rowCount() == 0) {
    $positive = 0;
    $negative = 0;

    if($_POST['isPositive']) {
      $positive = 1;
    }
    else {
      $negative = 1;
    }

    $sql = 'INSERT INTO opinion (positive, negative, user_id, image_id) VALUES (?, ?, ?, ?);';
    $sqlp = $GLOBALS["pdo"]->prepare($sql);
    $fields = array($positive, $negative, $_SESSION['user_id'], $_POST['imageId']);
    $sqlp->execute($fields);


    $sql = 'SELECT * FROM image WHERE image.id = ?';
    $sqlp = $GLOBALS["pdo"]->prepare($sql);
    $fields = array($_POST['imageId']);
    $sqlp->execute($fields);
    $image = $sqlp->fetch();

    $sql = 'SELECT * FROM opinion INNER JOIN image ON image.id = opinion.id AND image.user_id = ? AND opinion.positive = 1';
    $sqlp = $GLOBALS["pdo"]->prepare($sql);
    $fields = array($image['user_id']);
    $sqlp->execute($fields);

    if($sqlp->rowCount() == 10) {
      $sql = 'SELECT * FROM user WHERE id = ?';
      $sqlp = $GLOBALS["pdo"]->prepare($sql);
      $fields = array($image['user_id']);
      $sqlp->execute($fields);
      $user = $sqlp->fetch();

      $endUsers = json_decode(callApi('GET', 'http://localhost:8080/MoussaRaser/api/users'));
      foreach($endUsers as $endUser) {
        if($endUser->firstName == $user['username']) {
          callApi('POST', 'http://localhost:8080/MoussaRaser/api/events', array('eventType' => 'tenratings', 'toUserId' => $endUser->id));
          
          break;
        }
      }
    }

    echo json_encode(array('ok' => 1));
  }
  else {
    echo json_encode(array('ok' => 0));
  }

  exit;
}

?>
