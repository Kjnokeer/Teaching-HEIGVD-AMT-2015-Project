<?php

require_once 'php/pdo.php';

if( isset( $_POST['textcontent'] ) && isset( $_POST['imageId'] ) ) {
  $sql = 'INSERT INTO comment (text, user_id, image_id)
  VALUES (?, ?, ?);';
  $sqlp = $GLOBALS["pdo"]->prepare($sql);
  $fields = array($_POST['textcontent'], $_SESSION['user_id'], $_POST['imageId']);
  $sqlp->execute($fields);
  if($sqlp->rowCount() == 1 && $_POST['textcontent'] != "") {
    echo json_encode(array('ok' => 1));
  }
  else {
    echo json_encode(array('ok' => 0));
  }

  exit;
}

?>
