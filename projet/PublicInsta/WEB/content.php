<?php
require_once 'php/pdo.php';

if(loggedIn() == false) {
  header("Location:index.php");
}

define('FIRST', 1);
define('LIMIT', 4);
?>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="icon" href="img/ui/favicon.ico" />

  <title>PublicInsta</title>

  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
  <link href="css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />

  <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
  <!--<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Pacifico' type='text/css'>-->
  <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Indie+Flower' type='text/css'>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="css/style.css" type='text/css'>

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body>

  <?php include('partials/navbar.php'); ?>

  <div class="container content" id="content">

    <div class="scroll-top-wrapper ">
      <span class="scroll-top-inner">
        <i class="fa fa-2x fa-arrow-circle-up"></i>
      </span>
    </div>

    <div class="picture_post" id="add_new_post">
      <div class="post_header">
        <div class="picture_post_header">
          <img class="avatar img-circle" src="<?php echo $_SESSION['profile_photo']; ?>" />
        </div>
        <div class="username_post_header">
          <a><?php echo $_SESSION['username']; ?></a>
        </div>
      </div>
      <div class="row">
        <div class="post_content col-md-12">
          <form id="upload_photo_form" action="php/upload.php" method="POST" enctype="multipart/form-data">
            <div class="form-group">
              <input type="text" name="photo_caption" id="photo_caption" tabindex="1" class="form-control" placeholder="Message...">
            </div>
            <div class="form-group">
              <input id="photo_upload" name="photo_upload" type="file" tabindex="2" class="file file-loading" accept="image/*" >
            </div>
            <div class="form-group">
              <input type="submit" value="Upload Image" name="submit">
            </div>
          </form>
        </div>
      </div>
    </div>

    <input type="hidden" id="first" value="<?php echo FIRST; ?>" />
    <input type="hidden" id="limit" value="<?php echo LIMIT; ?>" >
  </div><!-- /.container -->


  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <!-- Latest compiled and minified JavaScript -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

  <!-- canvas-to-blob.min.js is only needed if you wish to resize images before upload.
  This must be loaded before fileinput.min.js -->
  <script src="js/plugins/canvas-to-blob.min.js" type="text/javascript"></script>
  <script src="js/fileinput.min.js"></script>
  <script src="js/plugins/jquery.toaster.js"></script>

  <script src="js/content.js"></script>
  <script src="js/back_to_top.js"></script>
  <?php
    if($_SESSION['firstConnection']) {
      echo "<script type='text/javascript'>$.toaster({ priority : 'info', title : '<img src=http://www.justjon.net/wp-content/uploads/2010/05/Newbie-badge-foursquare2.png width=30>', message : '  First connection !'});</script>";

      $_SESSION['firstConnection'] = false;
    }
  ?>

  <script type="text/javascript">
  $(document).ready(function() {
    // Lorsque je soumets le formulaire
    $('#upload_photo_form').on('submit', function(e) {
      e.preventDefault(); // J'empêche le comportement par défaut du navigateur, c-à-d de soumettre le formulaire

      var $form = $(this);
      var formdata = (window.FormData) ? new FormData($form[0]) : null;
      var data = (formdata !== null) ? formdata : $form.serialize();

      // Je vérifie une première fois pour ne pas lancer la requête HTTP
      // si je sais que mon PHP renverra une erreur
      // Envoi de la requête HTTP en mode asynchrone
      $.ajax({
        url: $form.attr('action'),
        type: $form.attr('method'),
        contentType: false, // obligatoire pour de l'upload
        processData: false, // obligatoire pour de l'upload
        dataType: 'json', // selon le retour attendu
        data: data,
        success: function (res) {
          if(res.reponse == 'ok') {
            var content = '<div class="picture_post">';
            content += '<div class="post_header">';
            content += '<div class="picture_post_header">';
            content += '<img class="avatar img-circle" src="' + res.profile_photo + '" />';
            content += '</div>';
            content += '<div class="username_post_header">';
            content += '<a>' + res.username + '</a>';
            content += '</div>';
            content += '</div>';
            content += '<div class="row">';
            content += '<div class="col-md-12 picture_post_content">';
            content += '<img class="img-responsive" src="' + res.path + '" />';
            content += '</div>';
            content += '</div>';
            content += '<div class="row">';
            content += '<div class="col-md-12 comments_post">';
            content += '<div class="comments_post_caption">';
            content += '<p>' + res.text + '</p>';
            content += '</div>';
            content += '<div class="comments_post_opinion">';
            content += '<div class="input-group">';
            content += '<span class="input-group-btn">';
            content += '<button class="btn btn-success glyphicon glyphicon-thumbs-up" type="button"></button>';
            content += '<button class="btn btn-danger glyphicon glyphicon-thumbs-down" type="button"></button>';
            content += '</span>';
            content += '<input type="text" class="comments_post_edit form-control" placeholder="Add a comment...">';
            content += '<span class="input-group-btn">';
            content += '<button id="send-comment" class="btn btn-default glyphicon glyphicon-send" type="button"></button>';
            content += '<button class="btn btn-primary glyphicon glyphicon-comment" type="button"></button>';
            content += '</span>';
            content += '</div>';
            content += '</div>';
            content += '</div>';
            content += '</div>';
            content += '</div>';
            $('#photo_caption').val('');
            $('#photo_upload').val('');
            $('#add_new_post').after(content);
          }
          else {
            alert(res.reponse);
          }

        }
      });
    });
  });
  </script>
</body>
</html>
