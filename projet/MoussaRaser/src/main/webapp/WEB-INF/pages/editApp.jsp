<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="ch.heigvd.amt.moussaraser.model.entities.Application"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/header.jsp" %>
        <link href="static/css/simple-sidebar.css" rel="stylesheet">
        <title>Edit application</title>

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

                    <h1>App details</h1>
                    <hr>
                    <div class="row">
                        <!-- edit form column -->
                        <div class="col-md-9 personal-info">

                            <form class="form-horizontal" role="form" action="editApp">
                                <div class="form-group">
                                    <label class="col-lg-3 control-label">Name:</label>
                                    <div class="col-lg-8">
                                        <input class="form-control" name="name" value="${application.getName()}" type="text" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3 control-label">Description:</label>
                                    <div class="col-lg-8">
                                        <input class="form-control" name="description" value="${application.getDescription()}" type="text" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3 control-label">API-Key:</label>
                                    <div class="col-lg-8">
                                        <input class="form-control" value="${application.getApiKey().getApiKey()}" disabled="true" type="text">
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
                                        <input name="state" data-toggle="toggle" data-on="Enabled" data-off="Disabled" type="checkbox" <c:if test="${application.isEnabled()}">checked</c:if>>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label"></label>
                                        <div class="col-md-8">
                                            <input class="btn btn-primary" name="action" value="Save changes" type="submit">
                                            <span></span>
                                            <input class="btn btn-default" value="Cancel" type="reset">
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




