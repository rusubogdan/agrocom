<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
    <div class="job-info">
        <form action="<c:url value="/job/update.do"/> " method="post" class="edit-details">
            <label hidden="hidden" for="employeeId">Worker : </label>
            <select hidden="hidden" id="employeeId" name="employeeId">
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

            <label for="infieldId1">Infield : </label>
            <select hidden="hidden" id="infieldId" name="infieldId">
                <%--<option value="-1">-select employee-</option>--%>
                <c:set var="inf" value=""/>
                <c:forEach var="infield" items="${infields}">
                    <option value="${infield.infieldId}"
                            <c:if test="${infield.infieldId eq infieldId}">
                                selected="selected"
                                <c:set var="inf" value="${infield.locationCode}"/>
                            </c:if>
                            >${infield.locationCode}</option>
                </c:forEach>
            </select>
            <input style="border: none;" type="text" id="infieldId1" name="infieldId1"
                   readonly value="${inf}"/>
            <br/>

            <label for="machineryId1">Machinery : </label>
            <select hidden="hidden" id="machineryId" name="machineryId">
                <%--<option value="-1">-select employee-</option>--%>
                <c:set var="mac" value=""/>
                    <c:forEach var="machinery" items="${machineries}">
                    <option value="${machinery.machineryId}"
                            <c:if test="${machinery.machineryId eq machineryId}">
                                selected="selected"
                                <c:set var="mac" value="${machinery.machineryName}"/>
                            </c:if>
                            >${machinery.machineryName}</option>
                </c:forEach>
            </select>
            <input style="border: none;" type="text" readonly="readonly" id="machineryId1" name="machineryId1" value="${mac}">
            <br/>

            <label for="workTypeId1">Work type: </label>
            ${activityWorkType}
            <select hidden="hidden" id="workTypeId" name="workTypeId">
                <%--<option value="-1">-select employee-</option>--%>
                <c:set var="wor" value="PLOWING"/>
                    <c:forEach var="workType" items="${workTypes}">
                    <option value="${workType}"
                            <c:if test="${workType eq activityWorkType}">
                                selected="selected"
                                <c:set var="wor" value="${workType}"/>
                            </c:if>
                            >${workType}</option>
                </c:forEach>
            </select>
            <input readonly type="text" style="border: none;" id="workTypeId1"
                   name="workTypeId1" value="${wor}">
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
