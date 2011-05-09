<div class="title"><h1>Hockey League Manager</h1></div>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${empty sessionScope.user}">
        <div class="loginform">
            
            <c:if test="${not empty loginerror}">
                <div class="error">
                    ${loginerror}
                </div>
            </c:if>
            <form action="Login?return=${param.page}" method="post">
                <p>
                    <div class="login">
                        <label for="email">Email</label>
                    </div>
                    <input type="text" name="email" placeholder="Email-Address" />
                </p>
                <p>
                    <div class="login">
                        <label for="password">Password</label>
                    </div>
                    <input type="password" name="password" placeholder="Password"/>
                </p>
                <p>
                    <input type="submit" value="Submit" />
                    <input type="reset" value="Reset" />
                </p>
            </form>
        </div>
    </c:when>
    <c:otherwise>
        <div class="loggedin">
            Welcome ${user.firstName} ${user.lastName}!<br />
            <a href="Logout?return=${param.page}">Logout</a>
        </div>
    </c:otherwise>
</c:choose>

