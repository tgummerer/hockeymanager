<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${user.accessLevel == 'admin'}">
<jsp:useBean id="teams" class="beans.Teams" scope="request" />
<jsp:include page="../helpers/error.jsp" />
<h1>Modify Game</h1>
<form action="ModifyGame?gameid=${param.gameid}" method="post">
	<div class="addgame">
		<label for="hometeam">Home team</label>
	</div>
	<select name="hometeam">
		<c:forEach var="team" items="${teams.list}">
            <c:choose>
                <c:when test="${team.teamID == hometeam}">
                    <option value="${team.teamID}" selected>${team.teamName}</option>
                </c:when>
                <c:otherwise>
                    <option value="${team.teamID}">${team.teamName}</option>
                </c:otherwise>
            </c:choose>
		</c:forEach>
	</select>
	<br />
	<br />
	<div class="addgame">
		<label for="awayteam">Away team</label>
	</div>
	<select name="awayteam">
		<c:forEach var="team" items="${teams.list}">
            <c:choose>
                <c:when test="${team.teamID == awayteam}">
                    <option value="${team.teamID}" selected>${team.teamName}</option>
                </c:when>
                <c:otherwise>
                    <option value="${team.teamID}">${team.teamName}</option>
                </c:otherwise>
            </c:choose>
		</c:forEach>
	</select>
	<br />
	<div class="addgame">
		<label for="datetime">Date (format: jjjj-mm-ddThh:mmZ)</label>
	</div>
    <input type="datetime" name="datetime" value="${date}" />
	<br />
	<input type="submit" value="Modify Game" />
	<input type="reset" value="Reset" />
</form>
</c:if>
