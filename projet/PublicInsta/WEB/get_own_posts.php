<?php

require_once 'php/pdo.php';

if( isset( $_POST['start'] ) && isset( $_POST['limit'] ) && !empty( $_POST['start'] ) && !empty( $_POST['limit'] ) ) {
   $start = $_POST['start'];
   $limit = $_POST['limit'];
   if($start == 1) {
      $query = "SELECT username, profile_photo, path, text
        FROM image
        INNER JOIN user ON user.id = image.user_id
        WHERE username = '".$_SESSION['username']."'
        ORDER BY image.id DESC
        LIMIT 0, $limit";
   }
   else {
      $query = "SELECT username, profile_photo, path, text
        FROM image
        INNER JOIN user ON user.id = image.user_id
        WHERE username = '".$_SESSION['username']."'
        ORDER BY image.id DESC
        LIMIT $start, $limit";
   }


   $sqlp = $GLOBALS["pdo"]->query($query);
   $data['count'] = $sqlp->rowCount();
   $post_images = $sqlp->fetchAll();

   foreach($post_images as $post_image) {
      $data['content'][] = $post_image;
   }

   echo json_encode($data);

   exit;
}

?>
