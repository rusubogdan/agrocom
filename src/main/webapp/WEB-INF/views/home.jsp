<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="application/javascript">

    // todo
    <%--var selectedMenuItem = ${selectedMenuItem};--%>
</script>

<div class="page-content inset">
    <div class="row">
        <div class="col-md-12">
            <div class="container">
                <%--<c:out value="${sessionScope.user.email}"/>--%>
            </div>
        </div>
    </div>
</div>


<%--samples--%>
<table style="display: none"
       id="example" class="display table table-striped table-bordered" cellspacing="0" width="100%">
    <thead>
    <tr>
        <%--<th>Id</th>
        <th>Name</th>
        <th>Email</th>--%>
    </tr>
    </thead>
</table>

<div id="gen-info-sample" class="gen-info-holder" style="display: none">
    <div class="gen-info-description">
        <div id="name" style="font-size: 30px;"><span></span></div>
        <div id="owner" style="font-size: 20px;"><span></span></div>
        <div id="address" style="font-size: 15px;"></div>
        <div id="employeesNumber" style="font-size: 15px;"><span></span> employees</div>
        <div id="garagesCapacity">Total capacity of garages: <span></span> square meters </div>
        <div id="totalArea">Total area: <span></span> hectares </div>
    </div>

    <div class="gen-info-links">
        <div class="gen-info-links-left">

        </div>
        <div class="gen-info-links-right">

        </div>
    </div>
</div>
<div id="button-sample" style="display: none;">
    <button type="button" name="ADD" value="ADD">ADD</button>
</div>
