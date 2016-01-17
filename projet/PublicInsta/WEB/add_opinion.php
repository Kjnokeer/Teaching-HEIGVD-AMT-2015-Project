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

    echo json_encode(array('ok' => 1));

    
  }
  else {
    echo json_encode(array('ok' => 0));
  }

  exit;
}

?>
