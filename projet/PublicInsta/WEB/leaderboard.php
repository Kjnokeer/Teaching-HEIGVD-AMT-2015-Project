<?php
require_once 'php/pdo.php';

if(loggedIn() == false) {
  header("Location:index.php");
}

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

    <h1>Leaderboard</h1>
    <table class="table table-striped table-responsive">
      <thead>
        <th>#</th>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Score</th>
      </thead>
      <tbody>
        <tr>
          <!-- Faire une condition ici - img(src="http://www.flashlet.com/uploads/2/0/6/9/20695664/7228077_orig.png" width="50px" style="padding-left: 20px") -->
          <td>1<img src="https://cdn0.iconfinder.com/data/icons/gamification-flat-awards-and-badges/500/crown1-512.png" width="50px" style="padding-left: 20px"/></td>
          <td>endUser</td>
          <td>endUser.lastname</td>
          <td>endUser.score</td>
        </tr>
      </tbody>
    </table>
    
  </div><!-- /.container -->


  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <!-- Latest compiled and minified JavaScript -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
  <script src="js/back_to_top.js"></script>
</body>
</html>
