<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="page-content inset">
    <div class="row">
        <div class="col-md-12">
            <div class="container">
                <c:out value="${sessionScope.user.email}"/>
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
        <div class="gen-info-title">
            
        </div>
        <div class="gen-info-logo">

        </div>
    </div>
    <div class="gen-info-links">
        <div class="gen-info-links-left">

        </div>
        <div class="gen-info-links-right">

        </div>
    </div>
</div>