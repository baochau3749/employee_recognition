<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %> --%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login</title>
	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
</head>

<body>
	<div class="container">
		<h2 class="display-3"  align="center">Login Page</h2>
		<hr>
		
		<form:form action="${pageContect.request.contextPath}/processLoginCredential" method="POST">
			<br>
			<div align="center">
				<c:if test="${param.error != null}">
					<i class="alert alert-danger" role="alert">Invalid email and/or password.</i>
				</c:if>
				<c:if test="${param.logout != null}">
					<i class="alert alert-success" role="alert">You have been logged out.</i>
				</c:if>
			</div>
			<br>
			
			<br>
			<div class="form-group row">
				<label class="col-sm-2">Email</label>
				<input class="form-control col-sm-10" type="email" name="username" />
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">Password</label>
				<input class="form-control col-sm-10" type="password" name="password" />
			</div>
	
			<input type="submit" class="btn btn-primary" value="Log in">
			<br><br>
			<a href="${pageContext.request.contextPath}/reset_password">Forget your password?</a>	
		</form:form>
	</div>
</body>
</html>