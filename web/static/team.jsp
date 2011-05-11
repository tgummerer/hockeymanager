<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>${teamname}</h1>
<c:choose>
	<c:when test="${not empty playerlist}">
		<table>
			<tr>
				<th>Number</th>
				<th>Name</th>
				<c:if test="${user.accessLevel == 'admin' || (user.accessLevel == 'manager' && user.teamID == param.teamid)}">
					<th>Delete Player</th>
				</c:if>
			</tr>
			<c:forEach var="player" items="${playerlist}">
				<tr>
					<td>${player.number}</td>
					<td>${player.firstname} ${player.lastname}</td>
					<c:if test="${user.accessLevel == 'admin' || (user.accessLevel == 'manager' && user.teamID == param.teamid)}">
						<td><a href="DeletePlayer?playerid=${player.playerID}&teamid=${param.teamid}">Delete</a></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<p>
			No players in this team yet.
		</p>
	</c:otherwise>
</c:choose>
<c:if test="${user.accessLevel == 'admin' || (user.accessLevel == 'manager' && user.teamID == param.teamid)}">
<h2>Add Player</h2>
<form action="AddPlayer?teamid=${param.teamid}" method="post">
</form>
</c:if>

