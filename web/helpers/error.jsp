<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${not empty error}">
        <div class="error">
            ${error}
        </div>
    </c:when>
    <%-- If a page has more errors --%>
    <c:when test="${not empty errors}">
        <div class="error">
            <ul>
                <c:forEach var="error" items="${errors}">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </div>
    </c:when>
    <c:when test="${not empty success}">
        <div class="success">
            ${success}
        </div>
    </c:when>
</c:choose>

