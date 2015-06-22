<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="add-infield-fields-holder">
    <div class="infield-fields">
        <form action="<c:url value="/infield/add.do"/>" method="POST">

            <label for="landLordSelectAdd">Landlord: </label>
            <select id="landLordSelectAdd" name="landLordSelectAdd">
                <option value="-1">No landlord</option>
                <c:forEach var="landLord" items="${landLords}">
                    <option value="${landLord.userId}">${landLord.firstName} ${landLord.lastName}
                    </option>
                </c:forEach>
            </select>
            <br/>
            <label for="locationCode">Location code: </label>
            <input type="text" id="locationCode" name="locationCode" required="required">
            <br/>
            <label for="areaHa">Area hectares: </label>
            <input type="text" id="areaHa" name="areaHa" required="required">
            <br/>
            <label for="county">County</label>
            <input type="text" id="county" name="county" required="required">
            <br/>
            <label for="Village">Village</label>
            <input type="text" id="village" name="village" required="required">
            <br/>
            <p>Crops - 5 years period</p>
            <br/>
            <label for="lastYear">Last year: </label>
            <input type="text" id="lastYear" name="lastYear" required="required">
            <br/>
            <label for="twoYearsAgo">Two years ago: </label>
            <input type="text" id="twoYearsAgo" name="twoYearsAgo">
            <br/>
            <label for="threeYearsAgo">Three years ago: </label>
            <input type="text" id="threeYearsAgo" name="threeYearsAgo">
            <br/>
            <label for="fourYearsAgo">Four years ago: </label>
            <input type="text" id="fourYearsAgo" name="fourYearsAgo">
            <br/>
            <label for="fiveYearsAgo">Five years ago: </label>
            <input type="text" id="fiveYearsAgo" name="fiveYearsAgo">
            <br/>
            <button type="submit" id="submit-infield">ADD</button>
        </form>
        <label class="error">${error}</label>
    </div>
    <div>

    </div>
</div>