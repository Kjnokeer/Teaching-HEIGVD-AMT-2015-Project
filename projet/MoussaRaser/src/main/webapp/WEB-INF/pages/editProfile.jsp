<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <%@include file="includes/header.jsp" %>
      <link href="static/css/simple-sidebar.css" rel="stylesheet">
      <title>Edit profile</title>

      <script>
         $(document).ready(function () {
            $("#my-account").addClass("active");
         });
      </script>
   </head>

   <body>
      <%@include file="includes/sidebar.jsp" %>
      <div class="container">
         <%@include file="includes/navbar.jsp" %>

         <h1>Edit Profile</h1>
         <hr>
         <div class="row">
            <!-- edit form column -->
            <div class="col-md-9 personal-info">

               <h3>Personal info</h3>
               <hr>

               <form class="form-horizontal" role="form" method="POST" action="editProfile">
                  <div class="form-group">
                     <label class="col-lg-3 control-label">Email:</label>
                     <div class="col-lg-8">
                        <input class="form-control" value="<%= request.getSession().getAttribute("email")%>" disabled="true" type="text">
                     </div>
                  </div>
                  <div class="form-group">
                     <label class="col-lg-3 control-label">First name:</label>
                     <div class="col-lg-8">
                        <input id="fname" class="form-control" name="firstname" value="<%= request.getSession().getAttribute("firstname")%>" type="text">
                     </div>
                  </div>
                  <div class="form-group">
                     <label class="col-lg-3 control-label">Last name:</label>
                     <div class="col-lg-8">
                        <input id="lname" class="form-control" name="lastname" value="<%= request.getSession().getAttribute("lastname")%>" type="text">
                     </div>
                  </div>
                  <div class="form-group">
                     <label class="col-md-3 control-label">New Password:</label>
                     <div class="col-md-8">
                        <input id="password" class="form-control" name="password" value="" type="password" required>
                     </div>
                  </div>
                  <div class="form-group">
                     <label class="col-md-3 control-label">Confirm password:</label>
                     <div class="col-md-8">
                        <input id="rpassword" class="form-control" name="rpassword" value="" type="password" required>
                     </div>
                  </div>
                  <div class="form-group">
                     <label class="col-md-3 control-label"></label>
                     <div class="col-md-8">
                        <input id="bSaveChanges" class="btn btn-primary" name="action" value="Save Changes" type="submit">
                        <span></span>
                        <input class="btn btn-default" value="Cancel" type="reset">
                     </div>
                  </div>
               </form>
            </div>
         </div>
      </div>
      <%@include file="includes/footer.jsp" %>
   </body>
</html>




