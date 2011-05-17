<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../helpers/error.jsp" />
<h1>Game Details</h1>
<h2>${hometeam} ${hometeamscore} - ${awayteam} ${awayteamscore}</h2>
<h3>Goals</h3>
<h4>1st period</h4>
<c:forEach var="goal" items="${goals}">
    <c:if test="${goal.period == 1}">
        <strong>${goal.teamName}</strong> - <em>${goal.scorer}</em> (${goal.assist1}, ${goal.assist2}) - ${goal.formattedTime}<br />
    </c:if>
</c:forEach>

<h4>2nd period</h3>
<c:forEach var="goal" items="${goals}">
    <c:if test="${goal.period == 2}">
        <strong>${goal.teamName}</strong> - <em>${goal.scorer}</em> (${goal.assist1}, ${goal.assist2}) - ${goal.formattedTime}<br />
    </c:if>
</c:forEach>

<h4>3rd period</h4>
<c:forEach var="goal" items="${goals}">
    <c:if test="${goal.period == 3}">
        <strong>${goal.teamName}</strong> - <em>${goal.scorer}</em> (${goal.assist1}, ${goal.assist2}) - ${goal.formattedTime}<br />
    </c:if>
</c:forEach>
<br />
<%-- Allow the admin and the team manager of the hometeam to add scores and penalties --%>
<c:if test="${user.accessLevel == 'admin' || (user.accessLevel == 'manager' && user.teamID == hometeamid)}">
</c:if>
<br />
<br />
<br />
<h3>Penalties</h3>
<h4>1st period</h4>
<c:forEach var="penalty" items="${penalties}">
    <c:if test="${penalty.period == 1}">
    <strong>${penalty.teamName}</strong> - <em>${penalty.player}</em> (${penalty.minutes} Min ${penalty.type}) - ${penalty.formattedTime}<br />
    </c:if>
</c:forEach>
<h4>2nd period</h4>
<i>No penalties yet</i>
<h4>3rd period</h4>
<i>No penalties yet</i>
