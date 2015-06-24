<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<div class="navbar navbar-inverse navbar-fixed-top" >
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <%--<a class="navbar-brand" href="<c:url value="/societies"/>">--%>
                <%--<img src="<c:url value="/resources/images/favicon.ico"/>"/>--%>
            <%--</a>--%>
            <c:choose>
                <c:when test="${sessionScope.user eq null}">
                    <a class="navbar-brand" href="<c:url value="/login"/>">
                        <img src="<c:url value="/resources/images/favicon.ico"/>"/>
                    </a>
                </c:when>
                <c:otherwise>
                    <a class="navbar-brand" href="<c:url value="/societies"/>">
                        <img src="<c:url value="/resources/images/favicon.ico"/>"/>
                    </a>
                </c:otherwise>
            </c:choose>
            <%--<a class="navbar-brand" href="<c:url value="/home"/>">${companyName}</a>--%>
            <a class="navbar-brand" href="<c:url value="/home"/>">${sessionScope.society.name}</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <%--@elvariable id="loggedIn" type="java"--%>
                <c:choose>
                    <c:when test="${sessionScope.user eq null}">
                        <li><a href="<c:url value="/login"/>">Login</a></li>
                        <%--<li><a href="<c:url value="/"/>">About</a></li>--%>
                        <%--<li><a href="<c:url value="/"/>">Contact</a></li>--%>
                    </c:when>
                    <c:otherwise>
                        <%--@elvariable id="fullName" type="java"--%>
                        <li><a href="#">${sessionScope.user.firstName} ${sessionScope.user.lastName}</a></li>
                        <li><a href="/societies">Societies</a></li>
                        <li><a href="<c:url value="/j_spring_security_logout"/>">Logout</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>

    </div>
</div>