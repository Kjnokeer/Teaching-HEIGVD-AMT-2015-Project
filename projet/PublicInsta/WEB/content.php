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

  <title>PublicInsta</title>

  link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
  <link href="css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />

  <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
  <!--<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Pacifico' type='text/css'>-->
  <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Indie+Flower' type='text/css'>
  <link rel="stylesheet" href="css/style.css" type='text/css'>

  <link rel="icon" href="img/ui/favicon.ico" />

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body>
  <nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">PublicInsta</a>
      </div>
      <div id="navbar" class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
          <li class="active"><a href="#news">News</a></li>
          <li><a href="#user">User</a></li>
          <li><a href="#awards">Awards</a></li>
        </ul>
      </div><!--/.nav-collapse -->
    </div>
  </nav>

  <div class="container content" id="content">

    <div class="picture_post">
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
          <div class="form-group">
            <input type="text" name="photo_caption" id="photo_caption" tabindex="1" class="form-control" placeholder="Message...">
          </div>
          <div class="form-group">
            <input id="photo_upload" type="file" tabindex="2" class="file file-loading" accept="image/*" >
          </div>

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

  <script src="js/content.js"></script>
</body>
</html>
