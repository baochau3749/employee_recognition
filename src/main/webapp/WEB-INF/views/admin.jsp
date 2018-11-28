
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Admin Main Page</title>
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
	
<style>
.nav-item
{
	margin: auto 10px auto;
}

h2
{
	margin-bottom: 20px;
}
</style>
</head>
<body>
	<div class="container">
		<h2 class="display-3"  align="center">Admin Main Page</h2>
		
		<ul class="nav nav-pills justify-content-center">
				<li class="nav-item">
					<a class="nav-link btn btn-outline-primary" href="${pageContext.request.contextPath}/admin/user_management">User Management</a>
				</li>
				
				<li class="nav-item">
					<a class="nav-link btn btn-outline-primary" href="${pageContext.request.contextPath}/admin/award_report">Award Report</a>
				</li>
				
				<li class="nav-item">
					<form:form action="${pageContect.request.contextPath}/logout"
						method="POST">
						<input class="nav-link btn btn-outline-primary" type="submit" value="Logout">
					</form:form>
				</li>
		</ul>
		
		<hr>
		
		<h3>Welcome: ${loggedInUser.email}</h3>
		
		<br><br>
		<div class="jumbotron jumbotron-fluid">
 			<div class="container">
   				<h1 class="display-4">Employee Administrative Page</h1>
   				<p class="lead">Welcome admin, here you can manage users and view reports.</p>
   			</div>
		</div>
	</div>
</body>
</html>