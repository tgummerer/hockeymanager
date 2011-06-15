<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<h1>Standings</h1>
<jsp:useBean id="teams" class="beans.Standings" scope="request" />
<table>
    <tr>
        <th>Teamname</th>
        <th>Points</th>
    </tr>
    <c:forEach var="team" items="${teams.list}">
        <tr>
            <td>${fn:escapeXml(team.teamName)}</td>
            <td>${team.points}</td>
        </tr>
    </c:forEach>
</table>
