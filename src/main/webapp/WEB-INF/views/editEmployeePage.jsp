<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
    <div class="employee-info">
        <form action="<c:url value="/employee/edit.do"/> " method="post" class="edit-details">
            <label for="firstName">First name: </label>
            <input type="text" value="${employee.firstName}" name="firstName" required/>    <br/>
            <label for="lastName">Last name: </label>
            <input type="text" value="${employee.lastName}" name="lastName" required/>      <br/>
            <label for="PIN">PIN: </label>
            <input type="text" value="${employee.PIN}" name="PIN" required/>                <br/>
            <label for="Email">Email: </label>
            <input type="text" value="${employee.email}" name="email" required/>            <br/>
            <label for="Phone: ">Phone: </label>
            <input type="text" value="${employee.phone}" name="phone"/>                     <br/>
            <label for="mobile">Mobile: </label>
            <input type="text" value="${employee.mobile}" name="mobile"/>                   <br/>
            <input type="text" value="${employee.userId}" name="userId" style="display: none;"/><br/>

            <label for="role">Role: </label>
            <select id="role" name="role">
                <option value="1" <c:if test="${role eq 1}">checked</c:if>>Admin</option>
                <option value="2" <c:if test="${role eq 2}">checked</c:if>>Employee</option>
            </select>

            <button type="submit" value="Done">Done</button>
        </form>
    </div>
</div>
