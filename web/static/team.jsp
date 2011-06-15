<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
					<td>${fn:escapeXml(player.firstname)} ${fn:escapeXml(player.lastname)}</td>
					<c:if test="${user.accessLevel == 'admin' || (user.accessLevel == 'manager' && user.teamID == param.teamid)}">
						<td><a href="DeletePlayer?playerid=${player.playerID}&teamid=${fn:escapeXml(param.teamid)}">Delete</a></td>
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
<jsp:include page="../helpers/error.jsp" />
<c:if test="${user.accessLevel == 'admin' || (user.accessLevel == 'manager' && user.teamID == param.teamid)}">
<h2>Add Player</h2>
<form action="AddPlayer?teamid=${param.teamid}" method="post">
	<p>
		<label for="number">Number</label><br />
		<input type="text" name="number" placeholder="Number" />
	</p>
	<p>
		<label for="firstname">First Name</label><br />
		<input type="text" name="firstname" placeholder="First Name" />
	</p>
	<p>
		<label for="lastname">Last Name</label><br />
		<input type="text" name="lastname" placeholder="Last Name" />
	</p>
	<p>
		<input type="submit" value="Add Player" />
		<input type="reset" value="Reset" />
	</p>
</form>
</c:if>
