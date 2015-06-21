<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="add-society-fields-holder">
    <div class="society-fields">
        <form action="<c:url value="/societies/add.do"/>" method="POST">
            <label for="name">Name</label>
            <input type="text" id="name" name="name" required="required">

            <label for="phone">Phone</label>
            <input type="text" id="phone" name="phone" required="required">

            <label for="address">Address</label>
            <input type="text" id="address" name="address" required="required">

            <button type="submit" id="submit-society">ADD</button>
        </form>
    </div>
    <div>

    </div>
</div>