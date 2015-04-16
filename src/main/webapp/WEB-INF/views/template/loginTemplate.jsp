<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <title><tiles:insertAttribute name="title" ignore="true">.</tiles:insertAttribute></title>

    <link href="<c:url value="/resources/css/bootstrap-theme.min.css"/> " rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/> " rel="stylesheet"/>
    <link href="<c:url value="/resources/css/login.register.css"/> " rel="stylesheet"/>

    <script src="<c:url value="/resources/js/jquery-2.1.3.min.js"/>" type="application/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="application/javascript"></script>

</head>
<body>
<div class="login-register">
    <%--<tiles:insertAttribute name="header"/>--%>
    <div class="content">
        <div id="body">
            <tiles:insertAttribute name="body"/>
        </div>
    </div>
    <%--<tiles:insertAttribute name="footer"/>--%>
</div>
</body>
</html>