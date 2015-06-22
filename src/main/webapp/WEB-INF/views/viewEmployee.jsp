<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
    <div class="employee-info">
        <table id="employee-details" class="view-details">
            <tbody>
                <tr>
                    <td>${employee.firstName}</td>
                </tr> 
                <tr>
                    <td>${employee.lastName}</td>
                </tr> 
                <tr>
                    <td>${employee.PIN}</td>
                </tr> 
                <tr>
                    <td>${employee.email}</td>
                </tr> 
                <tr>
                    <td>${employee.phone}</td>
                </tr> 
                <tr>
                    <td>${employee.mobile}</td>
                </tr>

            </tbody>
        </table>
        <br/><br/><br/>
        <form action="<c:url value="/employee/edit/${employee.userId}"/>" method="get">
            <input type="submit" value="Edit">
        </form>
    </div>
</div>