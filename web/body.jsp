<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${empty param.page}">
        <h1>News</h1>
        <p>
            Coming soon...
        </p>
    </c:when>
    <c:when test='${param.page == "register"}'>
        <jsp:include page="static/register.jsp" />
    </c:when>
	<c:when test='${param.page == "teams"}'>
		<jsp:include page="static/teams.jsp" />
	</c:when>
</c:choose>

