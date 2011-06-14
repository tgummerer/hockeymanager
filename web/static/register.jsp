<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../helpers/error.jsp" />

<form action="Register" method="post" onSubmit="return validateForm()">
    <p>
        <div class="register">
            <label for="firstname">First Name</label><br />
        </div>
        <input type="text" name="firstname" value="${firstname}" class="register" id="firstname" placeholder="First Name" />
    </p>
    <p>
        <div class="register">
            <label for="lastname">Last Name</label><br />
        </div>
        <input type="text" name="lastname" value="${lastname}" class="register" id="lastname" placeholder="Password" />
    </p>
    <p>
        <div class="register">
            <label for="email">Email</label><br />
        </div>
        <input type="email" name="email" value="${email}" class="register" id="email" placeholder="Email-Address" />
    </p>
    <p>
        <div class="register">
            <label for="password">Password</label><br />
        </div>
        <input type="password" name="password" class="register" id="password" placeholder="Password" />
    </p>
    <p>
        <div class="register">
            <label for="passwordconfirm">Confirm password</label><br />
        </div>
        <input type="password" name="passwordconfirm" class="register" id="passwordconfirm" placeholder="Confirm password" />
    </p>
    <p>
        <input type="submit" value="Register" />
    </p>
</form>

