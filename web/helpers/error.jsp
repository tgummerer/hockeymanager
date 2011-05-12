<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

