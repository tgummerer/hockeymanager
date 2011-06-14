<%-- 
    Document   : index
    Created on : Mar 22, 2011, 10:56:16 PM
    Author     : tommy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hockey league manager</title>
		<link href="css/reset.css" rel="stylesheet" type="text/css" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/register.js"></script>
    </head>
    <body>
		<div class="container">
			<div class="header">
				<jsp:include page="header.jsp" />
			</div>
            <div class="navigation">
                <jsp:include page="navigation.jsp" />
            </div>
			<div class="body">
				<jsp:include page="body.jsp" />
			</div>
			<div class="footer">
				<jsp:include page="footer.jsp" />
			</div>
		</div>
    </body>
</html>
