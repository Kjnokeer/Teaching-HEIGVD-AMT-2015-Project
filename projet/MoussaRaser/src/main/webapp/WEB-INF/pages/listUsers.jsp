<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/header.jsp" %>
        <link href="static/css/simple-sidebar.css" rel="stylesheet">
        <title>Users's list</title>

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
                    <h1>List of users for "${applicationName}"</h1>
                    <hr>
                    <table class="table table-striped">
                        
                        <thead>
                            <tr>
                                <td>User ID</td>
                                <td>Firstname</td>
                                <td>Lastname</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${endUsers}" var="endUser">
                            <tr>
                                <td>${endUser.getId()}</td>
                                <td>${endUser.getFirstName()}</td>
                                <td>${endUser.getLastName()}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <nav>
                        <ul class="pagination">
                            <li class="disabled"><a href=""><span aria-hidden="true">&laquo;</span></a></li>
                            <li class="active"><a href="">1<span class="sr-only">(current)</span></a></li>
                            <li><a href="">2</a></li>
                            <li><a href="">3</a></li>
                            <li><a href=""><span aria-hidden="true">&raquo;</span></a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
        <%@include file="includes/footer.jsp" %>
    </body>
</html>
