function loadImages() {
  first = $('#first').val();
  limit = $('#limit').val();

  $.ajax({
    url : 'get_news.php',
    dataType: "json",
    method: 'post',
    data: {
      start : first,
      limit : limit
    },
    success: function(data) {
      flag = true;
      $('#loader').hide();
      if(data.count > 0 ) {

        first = parseInt($('#first').val());
        limit = parseInt($('#limit').val());

        $('#first').val( first + limit );

        $.each(data.content, function(key, value ) {

          console.log(value);

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
          content += '<button class="btn btn-success glyphicon glyphicon-thumbs-up positive" onclick="addOpinion(this, 1, ' + value.imageid + ')" data-toggle="tooltip" data-original-title="' + value.nbPositive + '" type="button"></button>';
          content += '<button class="btn btn-danger glyphicon glyphicon-thumbs-down negative" onclick="addOpinion(this, 0, ' + value.imageid + ')" data-toggle="tooltip" data-original-title="' + value.nbNegative + '" type="button"></button>';
          content += '</span>';
          content += '<input type="text" id="comment-content-send-' + value.imageid + '" class="comments_post_edit form-control" placeholder="Add a comment...">';
          content += '<span class="input-group-btn">';
          content += '<div style="display:none;" id="image-id">' + value.imageid + '</div>';
          content += '<button id="send-comment" onclick="addComment(' + value.imageid + ')" class="btn btn-default glyphicon glyphicon-send" type="button"></button>';
          content += '<button onclick="openComments(' + value.imageid + ')" class="btn btn-primary glyphicon glyphicon-comment" type="button"></button>';
          content += '</span>';
          content += '</div>';
          content += '</div>';
          content += '</div>';
          content += '</div>';
          content += '</div>';

          $('#content').append(content);
        });

        $('[data-toggle="tooltip"]').tooltip();

      }
    },
    error: function( data ){
      flag = true;
      no_data = false;
      alert('Something went wrong, Please contact admin');
    }
  });
}

flag = true;
$(window).scroll(function() {
  if($(window).scrollTop() + $(window).height() >= $(document).height() - 1){

    no_data = true;
    if(flag && no_data){
      flag = false;
      $('#loader').show();
      loadImages();
    }


  }
});

function addOpinion(item, isPositive, imageId) {

  $.post('add_opinion.php', {
    isPositive: isPositive,
    imageId: imageId
  }).done(function(data) {
    data = jQuery.parseJSON(data);

    if(data.ok === 1) {

      var number = parseInt($(item).attr('data-original-title'));

      number++;

      $(item)
          .attr('data-original-title', number)
          .tooltip('show');
    }
    else {
      alert('One opinion per image')
    }
  });
}

function addComment(imageId) {
  var textcontent = $('#comment-content-send-' + imageId).val();

  $.post('add_comment.php', {
    textcontent: textcontent,
    imageId: imageId
  }).done(function(data) {
    data = jQuery.parseJSON(data);

    if(data.ok === 1) {
      $.toaster({ priority : 'success', title : 'Success : ', message : '  Comment added correctly!'});
      $('#comment-content-send-' + imageId).val('');
    }
    else {
      $.toaster({ priority : 'danger', title : 'Error : ', message : '  Problem when adding the comment!'});
    }
  });
}

$(document).ready(function(){

  loadImages();

});
