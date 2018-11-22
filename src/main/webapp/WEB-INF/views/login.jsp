<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %> --%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login</title>
</head>
<body>
	<h2>Login Page</h2>
	<hr>
	
	<form:form action="${pageContect.request.contextPath}/processLoginCredential" method="POST">
		<c:if test="${param.error != null}">
			<i>Invalid email and/or password.</i>
		</c:if>
		<c:if test="${param.logout != null}">
			<i>You have been logged out.</i>
		</c:if>
		<p>Email: <input type="email" name="username" /></p>
		<p>Password: <input type="password" name="password" /></p>	
		<br>	
		<input type="submit" value="Log in">
		<br><br>
		<a href="${pageContext.request.contextPath}/reset_password">Forget your password?</a>	
	</form:form>
</body>
</html>