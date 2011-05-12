<h1>Teams</h1>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="teams" class="beans.Teams" scope="request" />

<jsp:include page="../helpers/error.jsp" />
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
