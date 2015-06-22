<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
    <div class="add-form">
        <div>
            <p>Please enter the email of the newly employed person.</p>
            <p>Note that this person will not have administrative roles.</p>
            <p>To change the role go to person profile.</p>
            <br/><br/><br/>
        </div>
        <form id="add-employee-form" action="<c:url value="/employee/add.do"/>" method="post">
            <input type="text" name="emailOrPIN" id="emailOrPIN"/>
            <button type="submit">Add</button>
        </form>
        <label class="error" id="add-emp-error">${error}</label>
    </div>
</div>