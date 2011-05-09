<h1>Teams</h1>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="teams" class="beans.Teams" scope="session" />
<table>
	<c:forEach var="team" items="${teams.list}">
		<tr>
			<td>${team.teamName}</td>
		</tr>
	</c:forEach>
</table>