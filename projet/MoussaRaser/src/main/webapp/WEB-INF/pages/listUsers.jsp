<%-- 
    Document   : listUsers
    Created on : 9 oct. 2015, 09:25:50
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
        <%@include file="includes/sidebar.jsp" %>
        <div class="container">
            <%@include file="includes/navbar.jsp" %>


            <h1>List of users for "xxx"</h1>
            <hr>


            <table class="table table-striped">
                <thead>
                    <tr>
                        <td>User ID</td>
                        <td>Creation date</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>12345</td>
                        <td>20.05.2015</td>
                    </tr>

                    <tr>
                        <td>44344</td>
                        <td>21.05.2015</td>
                    </tr>
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


        <%@include file="includes/footer.jsp" %>
    </body>
</html>
