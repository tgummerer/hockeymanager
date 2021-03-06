<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navitem"><a href="index.jsp">Home</a></div>
<div class="navitem"><a href="index.jsp?page=games">All Games</a></div>
<div class="navitem"><a href="index.jsp?page=standings">Standings</a></div>
<c:choose>
    <c:when test="${user.accessLevel == 'manager'}">
        <div class="navitem last"><a href="index.jsp?page=teams">Teams</a></div>
    </c:when>
    <c:otherwise>
        <div class="navitem"><a href="index.jsp?page=teams">Teams</a></div>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${user.accessLevel == 'admin'}">
		<div class="navitem"><a href="index.jsp?page=addteam">Add Team</a></div>
        <div class="navitem last"><a href="index.jsp?page=changeuser">Set Permissions</a></div>
	</c:when>
</c:choose>
<c:choose>
    <c:when test="${empty user}">
        <div class="navitem last"><a href="index.jsp?page=register">Register</a></div>
    </c:when>
</c:choose>
