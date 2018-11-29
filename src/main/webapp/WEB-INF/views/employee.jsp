<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>

<head> 	
	<title>Add Employee</title>
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
	
		<h2 class="display-3"  align="center">Add Employee</h2>

		<ul class="nav nav-pills nav-justified">
			<li class="nav-item">
				<a class="nav-link btn btn-outline-primary" href="${pageContect.request.contextPath}/user">Home</a>
			</li>
			
			<li class="nav-item">
				<a class="nav-link btn btn-outline-primary" href="employees">Employee Management</a>
			</li>
			
			<li class="nav-item">
				<a class="nav-link btn btn-outline-primary" href="award">Create Award</a>
			</li>
			
			<li class="nav-item">
				<a class="nav-link btn btn-outline-primary" href="${user.userId}">Update Profile</a>
			</li>
			
			<li class="nav-item">
				<form:form action="${pageContect.request.contextPath}/logout" method="POST">
					<input class="nav-link btn btn-outline-primary" type="submit" value="Logout">
				</form:form>
			</li>
		</ul>

		<hr>
			
		<div>
			<form:form action="${pageContext.request.contextPath}/user/addEmployee" 
					   modelAttribute="employee" method="POST">
				<form:errors path="*" cssClass="alert alert-danger" element="div"></form:errors>
				<div class="form-group row">
					<label class="col-sm-2">First Name</label>
					<form:input class="form-control col-sm-10" path="firstName" value="${employee.firstName}"/>				
				</div>				
				
				<div class="form-group row">
					<label class="col-sm-2">Last Name</label>
					<form:input class="form-control col-sm-10" path="lastName" value="${employee.lastName}"/>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2">Email</label>
					<form:input class="form-control col-sm-10" path="email" value="${employee.email}"/>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2">Birth Date</label>
					<form:input class="form-control col-sm-10" path="birthDate"/>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2">Gender</label>
					<form:select class="form-control col-sm-10" path="gender">
						<form:option value="Male">Male</form:option>
						<form:option value="Female">Female</form:option>						
					</form:select>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2">State</label>
					<form:select class="form-control col-sm-10" path="state">
						<c:forEach var="s" items="${states}">
								<form:option value="${s.state_id}" >${s.state}</form:option> 
						</c:forEach>
					</form:select>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2">Position</label>
					<form:select class="form-control col-sm-10" path="position">
						<c:forEach var="p" items="${positions}">
								<form:option value="${p.position_id}">${p.position}</form:option> 
						</c:forEach>
					</form:select>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2">Department</label>
					<form:select class="form-control col-sm-10" path="department">
						<c:forEach var="d" items="${departments}">
								<form:option value="${d.department_id}">${d.department}</form:option> 
						</c:forEach>
					</form:select>
				</div>
	
				<input type="submit" class="btn btn-primary" value="Add Employee">
			</form:form>
		</div>
	</div>
</body>
</html>