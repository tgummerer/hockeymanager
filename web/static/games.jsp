<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="games" class="beans.Games" scope="request" />
<h1>Games</h1>
<table>
	<tr>
		<th>Game date</th>
		<th>Home team</th>
		<th>Away team</th>
		<th>Result</th>
		<c:if test="${user.accesslevel == 'admin'}">
			<th>Delete</th>
		</c:if>
	</tr>
	<c:forEach var="game" items="${games.list}">
		<tr>
			<td>${game.date}</td>
			<td>${game.homeTeam}</td>
			<td>${game.awayTeam}</td>
			<td class=result> : </td>
			<c:if test="${user.accesslevel == 'admin'}">
				<td><a href="DeleteGame?gameid=${game.gameid}">Delete</a></td>
			</c:if>
		</tr>
	</c:forEach>
</table>
