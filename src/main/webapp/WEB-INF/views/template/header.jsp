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
            <a class="navbar-brand" href="<c:url value="/"/>">YOUR LOGO</a>
            <a class="navbar-brand" href="<c:url value="/home"/>">${companyName}</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <%--@elvariable id="loggedIn" type="java"--%>
                <c:choose>
                    <c:when test="${loggedIn eq null or loggedIn eq false}">
                        <li><a href="<c:url value="/login"/>">Login</a></li>
                        <li><a href="<c:url value="/"/>">About</a></li>
                        <li><a href="<c:url value="/"/>">Contact</a></li>
                    </c:when>
                    <c:otherwise>
                        <%--@elvariable id="fullName" type="java"--%>
                        <li><a href="/account/${fullName}">${fullName}</a></li>
                        <li><a href="<c:url value="/j_spring_security_logout"/>">Logout</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>

    </div>
</div>