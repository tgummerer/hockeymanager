<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../helpers/error.jsp" />
<h1>Game Details</h1>
<h2>${fn:escapeXml(hometeam)} ${hometeamscore} - ${fn:escapeXml(awayteam)} ${awayteamscore}</h2>
<h3>Goals</h3>
<h4>1st period</h4>
<c:forEach var="goal" items="${goals}">
    <c:if test="${goal.period == 1}">
        <strong>${fn:escapeXml(goal.teamName)}</strong> - <em>${fn:escapeXml(goal.scorer)}</em> (${fn:escapeXml(goal.assist1)}, ${fn:escapeXml(goal.assist2)}) - ${goal.formattedTime}
        <c:if test="${user.accessLevel == 'admin' || (user.accessLevel == 'manager' && user.teamID == hometeamid)}">
            <a href="DeleteGoal?goalid=${goal.goalID}&gameid=${gameid}">Delete</a>
        </c:if>
        <br />
    </c:if>
</c:forEach>

<h4>2nd period</h3>
<c:forEach var="goal" items="${goals}">
    <c:if test="${goal.period == 2}">
        <strong>${fn:escapeXml(goal.teamName)}</strong> - <em>${fn:escapeXml(goal.scorer)}</em> (${fn:escapeXml(goal.assist1)}, ${fn:escapeXml(goal.assist2)}) - ${goal.formattedTime}
        <c:if test="${user.accessLevel == 'admin' || (user.accessLevel == 'manager' && user.teamID == hometeamid)}">
                <a href="DeleteGoal?goalid=${goal.goalID}&gameid=${gameid}">Delete</a>
        </c:if>
        <br />
    </c:if>
</c:forEach>

<h4>3rd period</h4>
<c:forEach var="goal" items="${goals}">
    <c:if test="${goal.period == 3}">
        <strong>${fn:escapeXml(goal.teamName)}</strong> - <em>${fn:escapeXml(goal.scorer)}</em> (${fn:escapeXml(goal.assist1)}, ${fn:escapeXml(goal.assist2)}) - ${goal.formattedTime}
        <c:if test="${user.accessLevel == 'admin' || (user.accessLevel == 'manager' && user.teamID == hometeamid)}">
            <a href="DeleteGoal?goalid=${goal.goalID}&gameid=${gameid}">Delete</a>
        </c:if>
        <br />
    </c:if>
</c:forEach>
<br />
<%-- Allow the admin and the team manager of the hometeam to add scores and penalties --%>

<c:if test="${user.accessLevel == 'admin' || (user.accessLevel == 'manager' && user.teamID == hometeamid)}">

<form action="AddGoal?gameid=${gameid}" method="post">
    <div class="addgoal">
		<label for="scorer">Scorer</label>
    </div>
    <select name="scorer">
        <c:forEach var="player" items="${players}">
            <option value="${fn:escapeXml(player.team)}:${player.playerID}">(${fn:escapeXml(player.team)}) #${player.number} - ${fn:escapeXml(player.firstname)} ${fn:escapeXml(player.lastname)}</option>
        </c:forEach>
    </select>
    <br />

    <div class="addgoal">
		<label for="assist1">First Assist</label>
    </div>
    <select name="assist1">
        <option value="-1">None</option>
        <c:forEach var="player" items="${players}">
            <option value="${fn:escapeXml(player.team)}:${player.playerID}">(${fn:escapeXml(player.team)}) #${player.number} - ${fn:escapeXml(player.firstname)} ${fn:escapeXml(player.lastname)}</option>
        </c:forEach>
    </select>
    <br />

    <div class="addgoal">
        <label for="assist2">Second Assist</label>
    </div>
    <select name="assist2">
        <option value="-1">None</option>
        <c:forEach var="player" items="${players}">
            <option value="${fn:escapeXml(player.team)}:${player.playerID}">(${fn:escapeXml(player.team)}) #${player.number} - ${fn:escapeXml(player.firstname)} ${fn:escapeXml(player.lastname)}</option>
        </c:forEach>
    </select>
    <br />

    <div class="addgoal">
		<label for="time">Time (format: mm:ss)</label>
	</div>
    <input type="text" name="time" />
	<br />
	<input type="submit" value="Add Goal" />
	<input type="reset" value="Reset" />

</form>

</c:if>
<br />
<br />
<br />
<h3>Penalties</h3>
<h4>1st period</h4>
<c:forEach var="penalty" items="${penalties}">
    <c:if test="${penalty.period == 1}">
        <strong>${fn:escapeXml(penalty.teamName)}</strong> - <em>${fn:escapeXml(penalty.player)}</em> (${penalty.minutes} Min ${penalty.type}) - ${penalty.formattedTime}
        <c:if test="${user.accessLevel == 'admin' || (user.accessLevel == 'manager' && user.teamID == hometeamid)}">
            <a href="DeletePenalty?penaltyid=${penalty.penaltyID}&gameid=${gameid}">Delete</a>
        </c:if>
        <br />
    </c:if>
</c:forEach>
<h4>2nd period</h4>
<c:forEach var="penalty" items="${penalties}">
    <c:if test="${penalty.period == 2}">
        <strong>${fn:escapeXml(penalty.teamName)}</strong> - <em>${fn:escapeXml(penalty.player)}</em> (${penalty.minutes} Min ${penalty.type}) - ${penalty.formattedTime}
        <c:if test="${user.accessLevel == 'admin' || (user.accessLevel == 'manager' && user.teamID == hometeamid)}">
            <a href="DeletePenalty?penaltyid=${penalty.penaltyID}&gameid=${gameid}">Delete</a>
        </c:if>
        <br />
    </c:if>
</c:forEach>
<h4>3rd period</h4>
<c:forEach var="penalty" items="${penalties}">
    <c:if test="${penalty.period == 3}">
        <strong>${fn:escapeXml(penalty.teamName)}</strong> - <em>${fn:escapeXml(penalty.player)}</em> (${penalty.minutes} Min ${penalty.type}) - ${penalty.formattedTime}
        <c:if test="${user.accessLevel == 'admin' || (user.accessLevel == 'manager' && user.teamID == hometeamid)}">
            <a href="DeletePenalty?penaltyid=${penalty.penaltyID}&gameid=${gameid}">Delete</a>
        </c:if>
        <br />
    </c:if>
</c:forEach>

<%-- Allow the admin and the team manager of the hometeam to add scores and penalties --%>

<c:if test="${user.accessLevel == 'admin' || (user.accessLevel == 'manager' && user.teamID == hometeamid)}">

<form action="AddPenalty?gameid=${gameid}" method="post">
    <div class="addpenalty">
		<label for="penalized">Penalized Player</label>
    </div>
    <select name="penalized">
        <c:forEach var="player" items="${players}">
            <option value="${player.playerID}">(${fn:escapeXml(player.team)}) #${player.number} - ${fn:escapeXml(player.firstname)} ${fn:escapeXml(player.lastname)}</option>
        </c:forEach>
    </select>
    <br />

    <div class="addpenalty">
        <label for="penalty">Penalty Type</label>
    </div>
    <select name="penalty">
        <c:forEach var="type" items="${penaltytypes}">
            <option value="${type.typeID}">${fn:escapeXml(type.type)} (${type.minutes} min)</option>
        </c:forEach>
    </select>
    <br />
    <div class="addpenalty">
		<label for="time">Time (format: mm:ss)</label>
	</div>
    <input type="text" name="time" />
	<br />
	<input type="submit" value="Add Penalty" />
	<input type="reset" value="Reset" />

</form>
</c:if>

<hr />
<c:forEach var="comment" items="${comments}" varStatus="status">
    <div class="comment">
        <strong>${fn:escapeXml(comment.firstName)} ${fn:escapeXml(comment.lastName)}:</strong><br />
        <small>${comment.date}</small><br />
        ${fn:escapeXml(comment.text)}
    </div>
    ${not status.last? '<hr />' : '<br /><br />'}
</c:forEach>

<%-- Let the users add some comments to the game --%>
<c:if test="${not empty user}">
<form action="AddComment?gameid=${gameid}" method="post">
    <textarea name="text"></textarea>
    <br />
    <input type="submit" value="Add Comment" />
</form>
</c:if>
