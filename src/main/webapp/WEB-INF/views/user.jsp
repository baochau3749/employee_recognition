<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>User Account</title>
</head>
<body>
	<h2>Update User Account</h2>
	<hr><br><br><br>
	
	<hr><br>
	<form:form action="${pageContect.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout">
	</form:form>

</body>
</html>