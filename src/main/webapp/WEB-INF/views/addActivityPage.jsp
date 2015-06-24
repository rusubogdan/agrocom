<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
    <div class="activity-info">
        <form action="<c:url value="/activity/add.do"/> " method="post" class="edit-details">
            <label for="employeeId">Worker : </label>
            <select id="employeeId" name="employeeId">
                <%--<option value="-1">-select employee-</option>--%>
                <c:forEach var="employee" items="${employees}">
                    <option value="${employee.userId}"
                            <%--<c:if test="${employee.userId eq employeeId}">--%>
                                <%--selected="selected"--%>
                            <%--</c:if>--%>
                            >${employee.firstName} ${employee.lastName}</option>
                </c:forEach>
            </select>
            <br/>

            <label for="infieldId">Infield : </label>
            <select id="infieldId" name="infieldId">
                <%--<option value="-1">-select employee-</option>--%>
                <c:forEach var="infield" items="${infields}">
                    <option value="${infield.infieldId}"
                            <%--<c:if test="${infield.infieldId eq infieldId}">--%>
                                <%--selected="selected"--%>
                            <%--</c:if>--%>
                            >${infield.locationCode}</option>
                </c:forEach>
            </select>
            <br/>

            <label for="machineryId">Machinery : </label>
            <select id="machineryId" name="machineryId">
                <%--<option value="-1">-select employee-</option>--%>
                <c:forEach var="machinery" items="${machineries}">
                    <option value="${machinery.machineryId}"
                            <%--<c:if test="${machinery.machineryId eq machineryId}">--%>
                                <%--selected="selected"--%>
                            <%--</c:if>--%>
                            >${machinery.machineryName}</option>
                </c:forEach>
            </select>
            <br/>

            <label for="workType">Work type: </label>
            <select id="workType" name="workType">
                <%--<option value="-1">-select employee-</option>--%>
                <c:forEach var="workType" items="${workTypes}" varStatus="loop">
                    <option value="${workType}"
                            <%--<c:if test="${workType eq activityWorkType}">--%>
                                <%--selected="selected"--%>
                            <%--</c:if>--%>
                            >${workType}</option>
                </c:forEach>
            </select>
            <br/>

            <%--<label for="date">Date :</label>--%>
            <%--<input type="text" value="${activity.date}" name="date" required/>    <br/>--%>
            <label for="description">Description: </label>
            <input type="text" value="${activity.description}" name="description" required/> <br/>
            <label for="duration">Duration: </label>
            <input type="text" value="${activity.duration}" name="duration" required/>     <br/>
            <label for="status">Status: </label>
            <input type="text" value="${activity.status}" name="status" required/>         <br/>

            <input type="text" value="${activity.workHistoryId}" name="workHistoryId"
                   style="display: none;"/><br/>

            <button type="submit" value="Done">Done</button>
        </form>
    </div>
</div>
