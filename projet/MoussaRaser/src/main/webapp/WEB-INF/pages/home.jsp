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


                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <td>Name</td>
                                <td>Description</td>
                                <td>API Key</td>
                                <td>Users</td>
                                <td></td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>demo1</td>
                                <td>Just a test</td>
                                <td>xxxxxxxxxxxxxxxxxxxxxxxxxx</td>
                                <td><a href="${pageContext.request.contextPath}/listUsers">120</a></td>
                                <td>
                                    <a  href="${pageContext.request.contextPath}/editApp"><button type="button" class="btn btn-default">edit</button></a>
                                    <input data-toggle="toggle" data-on="Enabled" data-off="Disabled" type="checkbox">
                                </td>
                            </tr>

                            <tr>
                                <td>test app</td>
                                <td>another test</td>
                                <td>yyyyyyyyyyyyyyyyyyyyyyyyyy</td>
                                <td>No Users</td>
                                <td>

                                    <button type="button" class="btn btn-default" href="${pageContext.request.contextPath}/editApp">edit</button>
                                    <input data-toggle="toggle" data-on="Enabled" data-off="Disabled" type="checkbox">
                                </td>
                            </tr>
                        </tbody>

                    </table>
                    <a  href="${pageContext.request.contextPath}/addApp"><button type="button" class="btn btn-default">Register new app</button></a>

                </div>
            </div>
        </div>


        <%@include file="includes/footer.jsp" %>
    </body>
</html>




