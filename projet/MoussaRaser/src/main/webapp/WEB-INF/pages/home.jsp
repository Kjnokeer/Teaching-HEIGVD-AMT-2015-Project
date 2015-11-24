<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/header.jsp" %>
        <link href="static/css/simple-sidebar.css" rel="stylesheet">
        <title>Welcome</title>

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
                    <h1>Your apps</h1>
                    <hr>
                    <c:choose>
                        <c:when test="${applications.size() > 0}">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Description</th>
                                        <th>API Key</th>
                                        <th>Users</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="count" value="0" scope="page" />
                                    <c:forEach items="${applications}" var="app">
                                        <tr>
                                            <td>${app.getName()}</td>
                                            <td>${app.getDescription()}</td>
                                            <td>${app.getApiKey().getApiKey()}</td>
                                            <c:choose>
                                                <c:when test="${nbEndUsersInApp[count] == 0}">
                                                    <td><p>${nbEndUsersInApp[count]}</p></td>
                                                </c:when> 
                                                <c:otherwise>
                                                    <td><a href="${pageContext.request.contextPath}/listUsers?app=${app.getApiKey().getApiKey()}&page=1">${nbEndUsersInApp[count]}</a></td>
                                                    </c:otherwise>
                                                </c:choose>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/editApp?app=${app.getApiKey().getApiKey()}"><button type="button" class="btn btn-default">edit</button></a>
                                                <input name="state" id="app-state" apiKey="${app.getApiKey().getApiKey()}" data-toggle="toggle" data-on="Enabled" data-off="Disabled" type="checkbox" onchange="changeState();" <c:if test="${app.isEnabled()}">checked</c:if>>
                                            </td>
                                        </tr>
                                        <c:set var="count" value="${count + 1}" scope="page"/>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <p>Sorry, you have no apps.</p>
                        </c:otherwise>
                    </c:choose>
                    <a  href="${pageContext.request.contextPath}/addApp"><button type="button" class="btn btn-default">Register new app</button></a>
                </div>
            </div>
        </div>
        <script>
            function changeState() {
                var apiKey = $('#app-state').attr('apiKey');
                
                $.post( "${pageContext.request.contextPath}/editApp", { action: "changeState", apiKey: apiKey } );
            }
        </script>
        <%@include file="includes/footer.jsp" %>
    </body>
</html>




