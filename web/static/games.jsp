<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="games" class="beans.Games" scope="request" />
<jsp:include page="../helpers/error.jsp" />
<h1>Games</h1>
<table>
	<tr>
		<th>Game date</th>
		<th>Home team</th>
		<th>Away team</th>
		<th>Result</th>
		<c:if test="${user.accessLevel == 'admin'}">
			<th>Delete</th>
			<th>Modify</th>
		</c:if>
	</tr>
	<c:forEach var="game" items="${games.list}">
		<tr>
            <td><a href="GameDetails?gameid=${game.gameID}">${game.date}</a></td>
			<td>${game.homeTeam}</td>
			<td>${game.awayTeam}</td>
            <td class=result>${game.homeScore} : ${game.awayScore}</td>
			<c:if test="${user.accessLevel == 'admin'}">
				<td><a href="DeleteGame?gameid=${game.gameID}">Delete</a></td>
				<td><a href="ModifyGame?gameid=${game.gameID}">Modify</a></td>
			</c:if>
		</tr>
	</c:forEach>
</table>
<c:if test="${user.accessLevel == 'admin'}">
<jsp:useBean id="teams" class="beans.Teams" scope="request" />
<h2>Add Game</h2>
<form action="AddGame" method="post">
	<div class="addgame">
		<label for="hometeam">Home team</label>
	</div>
	<select name="hometeam">
		<c:forEach var="team" items="${teams.list}">
			<option value="${team.teamID}">${team.teamName}</option>
		</c:forEach>
	</select>
	<br />
	<br />
	<div class="addgame">
		<label for="awayteam">Away team</label>
	</div>
	<select name="awayteam">
		<c:forEach var="team" items="${teams.list}">
			<option value="${team.teamID}">${team.teamName}</option>
		</c:forEach>
	</select>
	<br />
	<div class="addgame">
		<label for="datetime">Date (format: jjjj-mm-ddThh:mmZ)</label>
	</div>
	<input type="datetime" name="datetime" />
	<br />
	<input type="submit" value="Add Game" />
	<input type="reset" value="Reset" />
</form>
</c:if>


