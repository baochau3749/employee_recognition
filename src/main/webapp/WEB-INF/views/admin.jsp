<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Admin Main Page</title>
</head>
<body>
	<h2>Admin Main Page</h2>
	<hr>
	
	<a href="${pageContext.request.contextPath}/admin/user_management">User Management</a>

	<hr><br>
	<form:form action="${pageContect.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout">
	</form:form>
</body>
</html>