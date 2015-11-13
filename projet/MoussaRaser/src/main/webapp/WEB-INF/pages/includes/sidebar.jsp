<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="sidebar-wrapper">
    <ul class="sidebar-nav">
        <li class="sidebar-brand">
            <a href="${pageContext.request.contextPath}/home">MoussaRaser</a>
        </li>
        <li id="my-apps">
            <a href="${pageContext.request.contextPath}/home"><span class="glyphicon glyphicon-qrcode" aria-hidden="true"></span> My apps</a>
        </li>
        <li id="my-account">
            <a href="${pageContext.request.contextPath}/editProfile"><span class="glyphicon glyphicon-cog" aria-hidden="true" ></span> My account</a>
        </li>
    </ul>
</div>
