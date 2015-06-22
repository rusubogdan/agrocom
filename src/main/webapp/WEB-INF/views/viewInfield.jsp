<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
    <div class="infield-info">
        <table id="infield-details" class="view-details">
            <tbody>
            <tr>
                <td class="label-style">Location code: </td><td>${infield.locationCode}</td>
            </tr>
            <tr>
                <td class="label-style">Landlord name: </td><td>${infield.landLordFullName}</td>
            </tr>
            <tr>
                <td class="label-style">Area ha: </td><td>${infield.areaHa}</td>
            </tr>
            <tr>
                <td class="label-style">County: </td><td>${infield.county}</td>
            </tr>
            <tr>
                <td class="label-style">Village: </td><td>${infield.village}</td>
            </tr>
            <tr>
                <td class="label-style">Last year: </td><td>${infield.lastYear}</td>
            </tr>
            <tr>
                <td class="label-style">Two years ago: </td><td>${infield.twoYearsAgo}</td>
            </tr>
            <tr>
                <td class="label-style">Three years ago: </td><td>${infield.threeYearsAgo}</td>
            </tr>
            <tr>
                <td class="label-style">Four years ago: </td><td>${infield.fourYearsAgo}</td>
            </tr>
            <tr>
                <td class="label-style">Five years ago: </td><td>${infield.fiveYearsAgo}</td>
            </tr>

            </tbody>
        </table>
        <br/><br/><br/>
        <form action="<c:url value="/infield/edit/${infield.infieldId}"/>" method="get">
            <input type="submit" value="Edit">
        </form>
    </div>
</div>