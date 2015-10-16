<%-- 
    Document   : login
    Created on : 18 sept. 2015, 10:33:22
    Author     : Mathias
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/header.jsp" %>
    </head>

    <body>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                  
                    <form id="signin" class="navbar-form navbar-right" role="form" method="POST" action="auth">
                        
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <input id="email" name="email" type="email" class="form-control" name="email" value="" placeholder="Email Address">                                        
                        </div>

                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                            <input id="password" name="password" type="password" class="form-control" name="password" value="" placeholder="Password">                                        
                        </div>

                        <button type="submit" value="login" class="btn btn-primary" name="action">Login</button>
                        <a href="${pageContext.request.contextPath}/registrationPage"><button type="button" class="btn btn-primary" style="margin-left: 20px">Create account</button></a>
                    </form>
                    

                </div>
            </div>
        </nav>

        <div class="container" style="text-align: center">

            <c:if test="${loginError == 1}">
                <div class="alert alert-danger" role="alert">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                    <span class="sr-only">Error:</span>
                    Invalid email or password
                </div>
            </c:if>
            
            <div class="page-header">
                <h1>Welcome to MoussaRaser</h1>
            </div>

            <div class="alert alert-info">
                <p>232 accounts created</p>
                <p>483 applications managed</p>
            </div>

            <div class="alert alert-info">
                <p>8827 users created by applications during the last 30 days</p>
            </div>

            
        </div>
    </body>
</html>
