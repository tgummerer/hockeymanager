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

<form action="Register" method="post">
    <p>
        <div class="register">
            <label for="firstname">First Name</label><br />
        </div>
        <input type="text" name="firstname" class="register" placeholder="First Name" />
    </p>
    <p>
        <div class="register">
            <label for="lastname">Last Name</label><br />
        </div>
        <input type="text" name="lastname" class="register" placeholder="Password" />
    </p>
    <p>
        <div class="register">
            <label for="email">Email</label><br />
        </div>
        <input type="email" name="email" class="register" placeholder="Email-Address" />
    </p>
    <p>
        <div class="register">
            <label for="password">Password</label><br />
        </div>
        <input type="password" name="password" class="register" placeholder="Password" />
    </p>
    <p>
        <div class="register">
            <label for="passwordconfirm">Confirm password</label><br />
        </div>
        <input type="password" name="passwordconfirm" class="register" placeholder="Confirm password" />
    </p>
    <p>
        <input type="submit" value="Register" />
    </p>
</form>

