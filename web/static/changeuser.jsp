<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../helpers/error.jsp" />
<c:choose>
    <c:when test="${user.accessLevel == 'admin'}">
        <h1>Change Permissions</h1>
        <form action="ChangePermissions" method="post">
            User:<br />
            <jsp:useBean id="users" class="beans.Users" scope="request" />
            <select name="user">
            <c:forEach var="user" items="${users.list}">
                <option value="${user.userID}">${user.firstName} ${user.lastName}</option>
            </c:forEach>
            </select>
            <br />
            Permission level:<br />
            <select name="permission_level">
                <option>default</option>
                <option>manager</option>
                <option>admin</option>
            </select>
            <br />
            Team: (only needed if permission level is set to admin)<br />
            <jsp:useBean id="teams" class="beans.Teams" scope="request" />
            <select name="team">
            <c:forEach var="team" items="${teams.list}">
                <option value="${team.teamID}">${team.teamName}</option>
            </c:forEach>
            </select><br />
            <input type="submit" value="Change Permissions" />
        </form>
    </c:when>
    <c:otherwise>
        <h1>Change Permissions</h1>
        <p>
            You are not allowed to see this page
        </p>
    </c:otherwise>
</c:choose>
