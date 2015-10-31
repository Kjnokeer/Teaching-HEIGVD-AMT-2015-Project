<%-- 
    Document   : home
    Created on : 18 sept. 2015, 10:29:28
    Author     : Mathias
--%>

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

                     <c:forEach items="${applications}" var="app">
                        <tr>
                           <td>${app.getName()}</td>
                           <td>${app.getDescription()}</td>
                           <td>${app.getApiKey()}</td>
                           <td></td>
                           <td>
                              <button type="button" class="btn btn-default" href="${pageContext.request.contextPath}/editApp">edit</button>
                              <input <c:if test="${app.isEnabled()}"><c:out value="checked" /></c:if> data-toggle="toggle" data-on="Enabled" data-off="Disabled" type="checkbox">
                           </td>
                        </tr>
                     </c:forEach>
                  </tbody>

               </table>
               <a  href="${pageContext.request.contextPath}/addApp"><button type="button" class="btn btn-default">Register new app</button></a>

            </div>
         </div>
      </div>


      <%@include file="includes/footer.jsp" %>
   </body>
</html>




