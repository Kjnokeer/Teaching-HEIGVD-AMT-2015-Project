<%-- 
    Document   : home
    Created on : 18 sept. 2015, 10:29:28
    Author     : Mathias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/header.jsp" %>
        <link href="static/css/simple-sidebar.css" rel="stylesheet">
        
        <script>
            $(document).ready(function() {
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
                <!-- left column -->
                <!-- Ajout d'une image si besoin
                <div class="col-md-3">
                    <div class="text-center">
                        <img src="//placehold.it/100" class="avatar img-circle" alt="avatar">
                        <h6>Upload a different photo...</h6>

                        <input class="form-control" type="file">
                    </div>
                </div>
                -->

                <!-- edit form column -->
                <div class="col-md-9 personal-info">
       
                    <h3>Personal info</h3>
                    <hr>

                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Email:</label>
                            <div class="col-lg-8">
                                <input class="form-control" value="<%= request.getSession().getAttribute("email") %>" disabled="true" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">First name:</label>
                            <div class="col-lg-8">
                                <input class="form-control" value="<%= request.getSession().getAttribute("firstname") %>" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Last name:</label>
                            <div class="col-lg-8">
                                <input class="form-control" value="<%= request.getSession().getAttribute("lastname") %>" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Password:</label>
                            <div class="col-md-8">
                                <input class="form-control" value="" type="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Confirm password:</label>
                            <div class="col-md-8">
                                <input class="form-control" value="" type="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label"></label>
                            <div class="col-md-8">
                                <input class="btn btn-primary" value="Save Changes" type="button">
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




