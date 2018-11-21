<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Reset Password Page</title>
</head>
<body>
	<h2>Reset Password Page</h2>
	<a href="/">Login</a>
	
	<hr>
	
	<form:form modelAttribute="user"
			   action="${pageContect.request.contextPath}/reset_password">
			   
		<c:if test="${message != null}">
			<p>${message}</p>
		</c:if>

		<p>Please provide an email to retrieve a new password.</p>
		
		<p>Email: <form:input path="email"/></p>
		<br><br>
		
		<input type="submit" value="Reset Password">
	</form:form>

</body>
</html>