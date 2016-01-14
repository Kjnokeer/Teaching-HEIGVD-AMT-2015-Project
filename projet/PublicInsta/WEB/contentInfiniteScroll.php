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

  <input type="hidden" id="first" value="<?php echo FIRST; ?>" />
  <input type="hidden" id="limit" value="<?php echo LIMIT; ?>" >

  </div><!-- /.container -->


  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <!-- Latest compiled and minified JavaScript -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>


  <script>



  function loadImages() {
      first = $('#first').val();
      limit = $('#limit').val();

        $.ajax({
          url : 'infiniteScroll.php',
          dataType: "json",
          method: 'post',
          data: {
             start : first,
             limit : limit
          },
          success: function( data ) {
            flag = true;
            $('#loader').hide();
            if(data.count > 0 ) {

              first = parseInt($('#first').val());
              limit = parseInt($('#limit').val());

              $('#first').val( first + limit );

              $.each(data.content, function(key, value ) {
                var content = '<div class="picture_post">';
                content += '<div class="post_header">';
                content += '<div class="picture_post_header">';
                content += '<img class="avatar img-circle" src="' + value.profile_photo + '" />';
                content += '</div>';
                content += '<div class="username_post_header">';
                content += '<a>' + value.username + '</a>';
                content += '</div>';
                content += '</div>';
                content += '<div class="row">';
                content += '<div class="col-md-12 picture_post_content">';
                content += '<img class="img-responsive" src="' + value.path + '" />';
                content += '</div>';
                content += '</div>';
                content += '<div class="row">';
                content += '<div class="col-md-12 comments_post">';
                content += '<div class="comments_post_caption">';
                content += '<p>' + value.text + '</p>';
                content += '</div>';
                content += '<div class="comments_post_opinion">';
                content += '<div class="input-group">';
                content += '<span class="input-group-btn">';
                content += '<button class="btn btn-success glyphicon glyphicon-thumbs-up" type="button"></button>';
                content += '<button class="btn btn-danger glyphicon glyphicon-thumbs-down" type="button"></button>';
                content += '</span>';
                content += '<input type="text" class="comments_post_edit form-control" placeholder="Add a comment...">';
                content += '<span class="input-group-btn">';
                content += '<button class="btn btn-default glyphicon glyphicon-send" type="button"></button>';
                content += '<button class="btn btn-primary glyphicon glyphicon-comment" type="button"></button>';
                content += '</span>';
                content += '</div>';
                content += '</div>';
                content += '</div>';
                content += '</div>';
                content += '</div>';

                $('#content').append(content);
              });
            }
          },
          error: function( data ){
            flag = true;
            no_data = false;
            alert('Something went wrong, Please contact admin');
          }
        });
  }

  loadImages();

  flag = true;
  $(window).scroll(function() {
    if($(window).scrollTop() + $(window).height() == $(document).height()){
      
      no_data = true;
      if(flag && no_data){
        flag = false;
        $('#loader').show();
        loadImages();
      }
      
      
    }
  }); 
  </script>
</body>
</html>
