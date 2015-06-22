<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
    <div class="infield-info">
        <form action="<c:url value="/infield/update.do"/> " method="post" class="edit-details">
            <label for="landLordId">Landlord name: </label>
            <select id="landLordSelectEdit" name="landLordId">
                <option value="-1">-select landlord-</option>
                <c:forEach var="landLord" items="${landLords}">
                    <option value="${landLord.userId}"
                    <c:if test="${landLord.userId eq landLordId}">
                        selected="selected"
                    </c:if>
                    >${landLord.firstName} ${landLord.lastName}</option>
                </c:forEach>
            </select>
            <br/>
            <label for="locationCode">locationCode :</label>
            <input type="text" value="${infield.locationCode}" name="locationCode" required/>    <br/>
            <label for="areaHa">areaHa: </label>
            <input type="text" value="${infield.areaHa}" name="areaHa" required/>      <br/>
            <label for="county">county: </label>
            <input type="text" value="${infield.county}" name="county" required/>                <br/>
            <label for="village">village </label>
            <input type="text" value="${infield.village}" name="village" required/>            <br/>
            <label for="lastYear">lastYear </label>
            <input type="text" value="${infield.lastYear}" name="lastYear"/>                   <br/>
            <label for="twoYearsAgo">Two years ago: </label>
            <input type="text" value="${infield.twoYearsAgo}" name="twoYearsAgo"/>              <br/>
            <label for="threeYearsAgo">Three years ago: </label>
            <input type="text" value="${infield.threeYearsAgo}" name="threeYearsAgo"/>              <br/>
            <label for="fourYearsAgo">Four years ago: </label>
            <input type="text" value="${infield.fourYearsAgo}" name="fourYearsAgo"/>              <br/>
            <label for="fiveYearsAgo">Five years ago: </label>
            <input type="text" value="${infield.fiveYearsAgo}" name="fiveYearsAgo"/>              <br/>

            <input type="text" value="${infield.infieldId}" name="infieldId" style="display: none;"/><br/>

            <button type="submit" value="Done">Done</button>
        </form>
    </div>
</div>
