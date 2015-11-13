<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Registration</title>
      <%@include file="includes/header.jsp" %>

      <style>
         .aster {
            color:#f00;
         }
      </style>
   </head>

   <body>
      <div class="container-fluid">
         <section class="container">
            <div class="container-page">	
               <form method="POST" action="reg">
                  <h1>Registration</h1>

                  <c:if test="${loginError == 1}">
                     <div class="alert alert-danger" role="alert">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <span class="sr-only">Error:</span>
                        An error has occurred, please try again.
                     </div>
                  </c:if>

                  <div class="form-group col-lg-12">
                     <label>Email Address<span class="aster">*</span></label>
                     <input type="email" name="email" class="form-control" id="email" value="${email}" autofocus required>
                  </div>

                  <div class="form-group col-lg-12">
                     <label>First name<span class="aster">*</span></label>
                     <input type="text" name="fname" class="form-control" id="fname" value="${fname}" required>
                  </div>

                  <div class="form-group col-lg-12">
                     <label>Last name<span class="aster">*</span></label>
                     <input type="text" name="lname" class="form-control" id="lname" value="${lname}" required>
                  </div>

                  <div class="form-group col-lg-6">
                     <label>Password<span class="aster">*</span></label>
                     <input type="password" name="password" class="form-control" id="password" value="" required>
                  </div>

                  <div class="form-group col-lg-6">
                     <label>Repeat Password<span class="aster">*</span></label>
                     <input type="password" name="rpassword" class="form-control" id="rpassword" value="" required>
                  </div>

                  <a href="${pageContext.request.contextPath}"><button type="button" class="btn btn-default">Cancel</button></a>
                  <button type="submit" id="bRegister" class="btn btn-primary">Register</button>
               </form>
            </div>
         </section>
      </div>
   </body>
</html>
