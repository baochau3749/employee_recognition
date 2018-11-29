<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>User Update Page</title>
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
		.container hr {
			margin-bottom: 50px;
		}
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
		
		<h2 class="display-3"  align="center">User Update Page</h2>
		
		<ul class="nav nav-pills nav-justified">
			<li class="nav-item">
				<a class="nav-link btn btn-outline-primary" href="${pageContect.request.contextPath}/user">Home</a>
			</li>
			
			<li class="nav-item">
				<a class="nav-link btn btn-outline-primary" href="${pageContect.request.contextPath}/user/employees">Employee Management</a>
			</li>
			
			<li class="nav-item">
				<a class="nav-link btn btn-outline-primary" href="${pageContect.request.contextPath}/user/award">Create Award</a>
			</li>
			
			<li class="nav-item">
				<a class="nav-link btn btn-outline-primary active" href="${pageContect.request.contextPath}/user/${user.userId}">Update Profile</a>
			</li>
			
			<li class="nav-item">
				<form:form action="${pageContect.request.contextPath}/logout" method="POST">
					<input class="nav-link btn btn-outline-primary" type="submit" value="Logout">
				</form:form>
			</li>
		</ul>
		
		<hr>
		
		<form:form modelAttribute="user" enctype="multipart/form-data">
			<form:errors path="*" cssClass="alert alert-danger" element="div"></form:errors>
			
			<c:if test="${user != null}">
				<form:input type="hidden" path="userId"/>
				<form:input type="hidden" path="timeCreated"/>			
			</c:if>	
			
			<div class="form-group row">
				<label class="col-sm-2">First Name</label>
				<form:input class="form-control col-sm-10" path="userProfile.firstName"/>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">Last Name</label> 
				<form:input class="form-control col-sm-10" path="userProfile.lastName"/>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">Email</label> 
				<form:input class="form-control col-sm-10" path="email"/>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">Password</label> 
				<form:input class="form-control col-sm-10" path="password"/>
			</div>				
			
			<div class="form-group row">
				<label class="col-sm-2">Signature</label>
				<input class="col-sm-8" type="file" name="file">
			</div>
		
			<input type="submit" class="btn btn-primary" value="Save Changes">
		</form:form>
		
		<br>
		Your current signature:
		<br>
		<img src="/image" /> 
	</div>

</body>
</html>