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
                $("#my-apps").addClass("active");
            });
        </script>
    </head>

    <body>
        <%@include file="includes/sidebar.jsp" %>
        <div class="container">
            <%@include file="includes/navbar.jsp" %>

            <h1>App details</h1>
            <hr>
            <div class="row">
                <!-- edit form column -->
                <div class="col-md-9 personal-info">

                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Name:</label>
                            <div class="col-lg-8">
                                <input class="form-control" value="" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Description:</label>
                            <div class="col-lg-8">
                                <input class="form-control" value="" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">API-Key:</label>
                            <div class="col-lg-8">
                                <input class="form-control" value="" disabled="true" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label"># Users:</label>
                            <div class="col-md-8">
                                <input class="form-control" value="" disabled="true" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">State:</label>
                            <div class="col-md-8">
                                <button type="button" class="btn btn-danger" disabled="true">disabled</button>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label"></label>
                            <div class="col-md-8">
                                <input class="btn btn-primary" value="Save changes" type="button">
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




