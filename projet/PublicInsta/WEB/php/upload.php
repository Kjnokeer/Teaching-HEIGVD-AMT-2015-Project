<?php
require_once 'pdo.php';

$time = (int)microtime(true);

$reponse = 'error';

$target_dir = "..\img\users\\".$_SESSION['username'].'\\';
$target_dir_db = "img/users/".$_SESSION['username'].'/';
$target_file = $target_dir.basename($time.$_FILES["photo_upload"]["name"]);
$target_file_db = $target_dir_db.basename($time.$_FILES["photo_upload"]["name"]);
$uploadOk = 1;
$imageFileType = pathinfo($target_file,PATHINFO_EXTENSION);
// Check if image file is a actual image or fake image
if(isset($_POST["submit"])) {
  $check = getimagesize($_FILES["photo_upload"]["tmp_name"]);
  if($check !== false) {
    $reponse = 'ok';
    $uploadOk = 1;
  } else {
    $reponse = "File is not an image.";
    $uploadOk = 0;
  }
}
// Check if file already exists
if (file_exists($target_file)) {
  $reponse = "Sorry, file already exists.";
  $uploadOk = 0;
}
// Check file size
/*if ($_FILES["photo_upload"]["size"] > 500000) {
echo "Sorry, your file is too large.";
$uploadOk = 0;
}*/
// Allow certain file formats
if($imageFileType != "jpg" && $imageFileType != "png" && $imageFileType != "jpeg"
&& $imageFileType != "gif" ) {
  $reponse =  "Sorry, only JPG, JPEG, PNG & GIF files are allowed.";
  $uploadOk = 0;
}
// Check if $uploadOk is set to 0 by an error
if ($uploadOk == 0) {
  $reponse = "Sorry, your file was not uploaded.";
  // if everything is ok, try to upload file
} else {
  if (move_uploaded_file($_FILES["photo_upload"]["tmp_name"], $target_file)) {
    insertImage($_SESSION['user_id'], $target_file_db, $_POST['photo_caption']);
  } else {
    $reponse = "Sorry, there was an error uploading your file.";
  }
}

echo json_encode(['reponse' => $reponse, 'path' => $target_file_db, 'text' => $_POST['photo_caption'], 'profile_photo' => $_SESSION['profile_photo'], 'username' => $_SESSION['username']]);
?>
