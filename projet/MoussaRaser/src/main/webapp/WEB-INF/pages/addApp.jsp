<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/header.jsp" %>
        <link href="static/css/simple-sidebar.css" rel="stylesheet">
        <title>Add application</title>

        <script>
            $(document).ready(function () {
                $("#my-apps").addClass("active");
            });
        </script>
    </head>

    <body>
        <div id="wrapper">
            <%@include file="includes/sidebar.jsp" %>
            <div id="page-content-wrapper">
                <div class="container">
                    <%@include file="includes/navbar.jsp" %>

                    <h1>Register new app</h1>
                    <hr>
                    <div class="row">
                        <!-- edit form column -->
                        <div class="col-md-9 personal-info">

                            <form class="form-horizontal" role="form" method="POST" action="addApp">
                                <div class="form-group">
                                    <label class="col-lg-3 control-label">Name:</label>
                                    <div class="col-lg-8">
                                        <input class="form-control" name="name" value="" type="text" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3 control-label">Description:</label>
                                    <div class="col-lg-8">
                                        <input class="form-control" name="description" value="" type="text" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label"></label>
                                    <div class="col-md-8">
                                        <input class="btn btn-primary" name="action" value="Register" type="submit">
                                        <span></span>
                                        <a class="btn btn-default" href="${pageContext.request.contextPath}/home">Cancel</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="includes/footer.jsp" %>
    </body>
</html>




