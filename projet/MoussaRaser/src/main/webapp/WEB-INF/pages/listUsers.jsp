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
                            <c:forEach begin="1" end="${nbPagesRequired}" varStatus="loop">
                                <c:choose>
                                    <c:when test="${loop.count == pageNumber}">
                                        <li class="active"><a href='listUsers?app=${app}&page=${loop.count}'>${loop.count}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                        <li><a href='listUsers?app=${app}&page=${loop.count}'>${loop.count}</a></li>
                                        </c:otherwise>
                                    </c:choose>

                            </c:forEach>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
        <%@include file="includes/footer.jsp" %>
    </body>
</html>
