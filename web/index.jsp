<%-- 
    Document   : index
    Created on : Mar 22, 2011, 10:56:16 PM
    Author     : tommy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hockey league manager</title>
		<link href="css/reset.css" rel="stylesheet" type="text/css" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
		<div class="container">
			<div class="header">
				<jsp:include page="header.jsp" />
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
