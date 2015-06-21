<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
    <div class="societies-holder">
        <c:forEach var="society" items="${societies}" varStatus="loop">
            <div id="society-${society.societyId}" class="society-holder">
                <div class="top-side">
                    <div>
                        ${society.name}
                    </div>
                </div>
                <div class="bottom-side">
                    <div class="owner">
                        ${society.owner.firstName}
                    </div>
                </div>
            </div>
        </c:forEach>
        <div id="add-society-button" class="add-society-holder">
            <%--<div id="add-society-button" class="add-society">--%>
                <%--+--%>
            <%--</div>--%>
            <div class="top-side">
                <div>
                    +
                </div>
            </div>
            <div class="bottom-side">
                <div class="owner">
                </div>
            </div>
        </div>
    </div>
</div>

<div class="society-holder" id="sample-society" style="display: none;">
    <div class="top-side">
        <div>
            title
        </div>
    </div>
    <div class="bottom-side">
        <div class="owner">
            owner
        </div>
    </div>
</div>