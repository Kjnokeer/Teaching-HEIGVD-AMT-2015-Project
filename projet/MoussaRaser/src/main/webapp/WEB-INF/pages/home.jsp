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
                           <c:forEach items="${applications}" var="app">
                              <tr>
                                 <td>${app.getName()}</td>
                                 <td>${app.getDescription()}</td>
                                 <td>${app.getApiKey()}</td>
                                 <td></td>
                                 <td>
                                    <a  href="${pageContext.request.contextPath}/editApp?app=${app.getApiKey()}"><button type="button" class="btn btn-default">edit</button></a>
                                    <c:choose>
                                       <c:when test="${app.isEnabled()}">
                                          <button type="button" class="btn btn-success" disabled>Enabled</button>
                                       </c:when>
                                       <c:otherwise>
                                          <button type="button" class="btn btn-danger" disabled>Disabled</button>
                                       </c:otherwise>
                                    </c:choose>
                                 </td>
                              </tr>
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
      <%@include file="includes/footer.jsp" %>
   </body>
</html>




