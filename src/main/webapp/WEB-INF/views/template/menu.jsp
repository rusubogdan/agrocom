<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="menu-holder">
    <%--todo create logic dependant to user role!!!--%>

    <ul class="sidebar-nav" id="sidebar">

        <li id="general"><a href="#">General</a></li>
        <c:if test="${role == 'admin'}">
            <li id="activities"><a href="#">Recent activity</a></li>
        </c:if>

        <c:if test="${role == 'admin'}">
            <li id="payments"><a href="#">Payments</a></li>
        </c:if>

        <c:if test="${role == 'employee'}">
            <li id="jobs"><a href="#">My activity</a></li>
        </c:if>

        <c:if test="${role == 'admin'}">
            <li id="employees"><a href="#">Employees</a></li>
        </c:if>
        <c:if test="${role == 'admin'}">
            <li id="tenants"><a href="#">Landlords</a></li>
        </c:if>
        <c:if test="${role == 'admin'}">
            <li id="infields"><a href="#">Infields</a></li>
        </c:if>
        <c:if test="${role == 'admin'}">
            <li id="garages"><a href="#">Garages</a></li>
        </c:if>
    </ul>
</div>