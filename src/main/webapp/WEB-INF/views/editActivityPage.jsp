<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
    <div class="activity-info">
        <form action="<c:url value="/activity/update.do"/> " method="post" class="edit-details">
            <label for="employeeId">Worker : </label>
            <select id="employeeId" name="employeeId">
                <%--<option value="-1">-select employee-</option>--%>
                <c:forEach var="employee" items="${employees}">
                    <option value="${employee.userId}"
                            <c:if test="${employee.userId eq employeeId}">
                                selected="selected"
                            </c:if>
                            >${employee.firstName} ${employee.lastName}</option>
                </c:forEach>
            </select>
            <br/>

            <label for="infieldId">Infield : </label>
            <select id="infieldId" name="infieldId">
                <%--<option value="-1">-select employee-</option>--%>
                <c:forEach var="infield" items="${infields}">
                    <option value="${infield.infieldId}"
                            <c:if test="${infield.infieldId eq infieldId}">
                                selected="selected"
                            </c:if>
                            >${infield.locationCode}</option>
                </c:forEach>
            </select>
            <br/>

            <label for="machineryId">Machinery : </label>
            <select id="machineryId" name="machineryId">
                <%--<option value="-1">-select employee-</option>--%>
                <c:forEach var="machinery" items="${machineries}">
                    <option value="${machinery.machineryId}"
                            <c:if test="${machinery.machineryId eq machineryId}">
                                selected="selected"
                            </c:if>
                            >${machinery.machineryName}</option>
                </c:forEach>
            </select>
            <br/>

            <label for="workTypeId">Work type: </label>
            <select id="workTypeId" name="workTypeId">
                <%--<option value="-1">-select employee-</option>--%>
                <c:forEach var="workType" items="${workTypes}">
                    <option value="${workType}"
                            <c:if test="${workType eq activityWorkType}">
                                selected="selected"
                            </c:if>
                            >${workType}</option>
                </c:forEach>
            </select>
            <br/>

            <label for="date">Date :</label>
            <input disabled type="text" id="editActivityDate" readonly value="${activity.date}" name="date" required/>    <br/>
            <input disabled type="text" id="hiddenEditActivityDate" hidden="hidden"
                    value="${activity.date}" name="hiddenEditActivityDate">
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

<script type="application/javascript">
    var df = new Date(document.getElementById('editActivityDate').value);
    document.getElementById('editActivityDate').value = ((df.getMonth() + 1) + '/' + df.getDate() + '/' +  df.getFullYear());
</script>
