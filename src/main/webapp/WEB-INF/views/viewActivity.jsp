<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
    <div class="activity-info">
        <table id="activity-details" class="view-details">
            <tbody>


            </tbody>
        </table>
        <br/><br/><br/>
        <form action="<c:url value="/employee/edit/${employee.userId}"/>" method="get">
            <input type="submit" value="Edit">
        </form>
    </div>
</div>