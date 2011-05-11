<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Check access level --%>
<c:if test="${user.accessLevel == 'admin'}">
	<c:choose>
		<c:when test="${not empty error}">
			<div class="error">
				<%= request.getAttribute("error") %>
			</div>
		</c:when>
		<c:when test="${not empty success}">
			<div class="success">
				<%= request.getAttribute("success") %>
			</div>
		</c:when>
	</c:choose>

	<h1>Add new Team</h1>
	<form action="AddTeam" method="post">
		<p>
			<label for="teamname">Team name:</label>
			<input type="text" name="teamname" placeholder="Team name" />
		</p>
		<p>
			<input type="submit" value="Add Team" />
		</p>
	</form>
</c:if>
