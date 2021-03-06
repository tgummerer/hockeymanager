<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${empty param.page}">
        <jsp:include page="static/games.jsp" />
    </c:when>
    <c:when test='${param.page == "register"}'>
        <jsp:include page="static/register.jsp" />
    </c:when>
	<c:when test='${param.page == "teams"}'>
		<jsp:include page="static/teams.jsp" />
	</c:when>
	<c:when test='${param.page == "addteam"}'>
		<jsp:include page="static/addteam.jsp" />
	</c:when>
	<c:when test='${param.page == "team"}'>
		<jsp:include page="static/team.jsp" />
	</c:when>
	<c:when test='${param.page == "games"}'>
		<jsp:include page="static/games.jsp" />
	</c:when>
    <c:when test='${param.page == "modifygame"}'>
        <jsp:include page="static/modifygame.jsp" />
    </c:when>
    <c:when test='${param.page == "gamedetails"}'>
        <jsp:include page="static/gamedetails.jsp" />
    </c:when>
    <c:when test='${param.page == "standings"}'>
        <jsp:include page="static/standings.jsp" />
    </c:when>
    <c:when test='${param.page == "changeuser"}'>
        <jsp:include page="static/changeuser.jsp" />
    </c:when>
    <c:otherwise>
        <h1>Page not implemented yet</h1>
    </c:otherwise>
</c:choose>

