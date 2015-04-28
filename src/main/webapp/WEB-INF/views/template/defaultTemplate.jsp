<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <title><tiles:insertAttribute name="title" ignore="true">.</tiles:insertAttribute></title>

    <script src="<c:url value="/resources/js/jquery-2.1.3.min.js"/>"></script>

    <script src="<c:url value="/resources/js/home.js"/> "></script>

</head>
<body style="background-color: #FFF">
<div class="page">
    <tiles:insertAttribute name="header"/>
    <div class="content">
        <div id="body">
            <tiles:insertAttribute name="body"/>
        </div>
    </div>
    <tiles:insertAttribute name="footer"/>
</div>
</body>
</html>