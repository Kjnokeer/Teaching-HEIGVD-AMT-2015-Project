<?php

require_once 'php/pdo.php';

if(isset( $_POST['imageId'] ) ) {
  $sql = 'SELECT text, username
  FROM comment
  INNER JOIN user ON user.id = comment.user_id
  WHERE image_id = ?';
  $sqlp = $GLOBALS["pdo"]->prepare($sql);
  $fields = array($_POST['imageId']);
  $sqlp->execute($fields);
  $comments = $sqlp->fetchAll();
  if($sqlp->rowCount() != 0) {
    echo json_encode(['ok' => 1, 'content' => $comments]);
  }
  else {
    echo json_encode(['ok' => 0]);
  }

  exit;
}

?>
