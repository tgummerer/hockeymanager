<h1>Teams</h1>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="teams" class="beans.Teams" scope="request" />

<c:choose>
    <c:when test="${not empty error}">
        <div class="error">
            <%= request.getAttribute("error") %>
        </div>
    </c:when>
    <c:when test="${not empty success}">
        <div class="success">
            <%= request.getAttribute("success") %>
        </div>
    </c:when>
</c:choose>
<table>
	<c:forEach var="team" items="${teams.list}">
		<tr>
			<td><a href="ShowTeam?teamid=${team.teamID}">${team.teamName}</a></td>
			<c:if test="${user.accessLevel == 'admin'}">
				<td><a href="DeleteTeam?teamid=${team.teamID}">Delete</a></td>
			</c:if>
		</tr>
	</c:forEach>
</table>
