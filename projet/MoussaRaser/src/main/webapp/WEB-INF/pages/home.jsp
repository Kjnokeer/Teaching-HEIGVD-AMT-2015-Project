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
    </head>



    <body>
        <div class="container">
            <%@include file="includes/navbar.jsp" %>
            
            
            <h2>Welcome to the demo app!</h2>

            
            <div class="alert alert-info" role="alert">
                You are logged in as ${principal}.
            </div>

            
        </div>


        <%@include file="includes/footer.jsp" %>
    </body>
</html>




