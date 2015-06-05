<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <title><tiles:insertAttribute name="title" ignore="true">.</tiles:insertAttribute></title>

    <link rel="icon" href="<c:url value="/resources/images/favicon.ico"/>" type="image/x-icon" />
    <%--API related scripts--%>
    <%--<script src="<c:url value="/resources/js/jquery-2.1.3.min.js"/>"></script>--%>
    <%--<script src="<c:url value="/resources/js/bootstrap.min.js"/> "></script>--%>
    <%--<script src="<c:url value="/resources/js/jquery.dataTables.min.js"/> "></script>--%>
    <%--<script src="<c:url value="/resources/js/dataTables.bootstrap.min.js"/> "></script>--%>
    <%-------%>

    <%--custom scripts--%>
    <%--<script src="<c:url value="/resources/js/home.js"/> "></script>--%>
    <%-------%>

    <%--API related CSS files--%>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/dataTables.bootstrap.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/jquery.dataTables.min.css"/>" rel="stylesheet"/>
    <%--<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet"/>--%>
    <%--<link href="<c:url value="/resources/css/dataTables.bootstrap.css"/>" rel="stylesheet"/>--%>
    <%--<link href="<c:url value="/resources/css/jquery.dataTables.min.css"/>" rel="stylesheet"/>--%>
    <%-------%>

    <%--custom CSS files--%>
    <link href="<c:url value="/resources/css/login.register.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/agrocom.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/public.css"/>" rel="stylesheet"/>
    <%--<link href="<c:url value="/resources/css/menu.css"/>" rel="stylesheet"/>--%>
    <%-------%>
</head>
<body style="background-color: #FFF">
<div class="page">
    <div class="header">
        <tiles:insertAttribute name="header"/>
    </div>
    <div class="content">
        <div id="body">
            <%--

                    the page will serve as login/register, contact and info about the platform

            --%>

            <%--<div class="container">
                <div id="wrapper" class="active">
                    <div id="sidebar-wrapper">
                        <tiles:insertAttribute name="menu"/>
                    </div>
                    <div id="page-content-wrapper">
                        <tiles:insertAttribute name="body"/>
                    </div>
                </div>
            </div>--%>
            <tiles:insertAttribute name="body"/>
        </div>
    </div>
    <div class="footer">
        <tiles:insertAttribute name="footer"/>
    </div>
</div>

<script src="<c:url value="/resources/js/jquery-2.1.3.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.dataTables.min.js"/> "></script>


<script src="<c:url value="/resources/js/publicPage.js"/> "></script>

</body>
</html>
