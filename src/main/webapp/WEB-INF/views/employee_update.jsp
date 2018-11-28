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
	
		<h2 class="display-3"  align="center">Update Employee</h2>

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
				<a class="nav-link btn btn-outline-primary" href="${user.id}">Update Profile</a>
			</li>
			
			<li class="nav-item">
				<form:form action="${pageContect.request.contextPath}/logout" method="POST">
					<input class="nav-link btn btn-outline-primary" type="submit" value="Logout">
				</form:form>
			</li>
		</ul>

		<hr>
			
		<div>
		
			<c:if test="${not empty er}">
			<div class="container" align="center">
		   					${er}
		   	</div>
			</c:if>
	
			<form:form action="update_employee" modelAttribute="employee" method="POST">
				<form:errors path="*" cssClass="alert alert-danger" element="div"></form:errors>
				<form:input type="hidden" path="id"/>
				<div class="form-group row">
					<label class="col-sm-2">First Name</label>
					<form:input class="form-control col-sm-10" path="firstName"/>
				</div>
								
				<div class="form-group row">
					<label class="col-sm-2">Last Name</label>
					<form:input class="form-control col-sm-10" path="lastName"/>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2">Email</label>
					<form:input class="form-control col-sm-10" path="email"/>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2">Birth Date</label>
					<form:input class="form-control col-sm-10" path="birthDate"/>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2">Gender</label>
					<form:select class="form-control col-sm-10" path="gender">
					<c:if test="${employee.gender == 'Male'}">
							<option value="Male" ${'selected="selected"'}>Male</option>
					</c:if>
					<c:if test="${employee.gender == 'Female'}">
							<option value="Female" ${'selected="selected"'}>Female</option>
					</c:if>
					<c:if test="${employee.gender != 'Male'}">
							<option value="Male">Male</option>
					</c:if>
					<c:if test="${employee.gender != 'Female'}">
							<option value="Female">Female</option>
					</c:if>
					</form:select>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2">State</label>
					<form:select class="form-control col-sm-10" path="state">
					<c:forEach var="s" items="${states}">
						<c:if test="${s.state_id == employee.state}">
							<option value="${s.state_id}" ${'selected="selected"'}>${s.state}</option>
						</c:if>
						<c:if test="${s.state_id != employee.state}">
							<option value="${s.state_id}">${s.state}</option>
						</c:if>
					</c:forEach>
					</form:select>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2">Position</label>
					<form:select class="form-control col-sm-10" path="position">
					<c:forEach var="p" items="${positions}">
						<c:if test="${p.position_id == employee.position}">
							<option value="${p.position_id}" ${'selected="selected"'}>${p.position}</option>
						</c:if>
						<c:if test="${p.position_id != employee.position}">
							<option value="${p.position_id}">${p.position}</option> 
						</c:if>
							
					</c:forEach>
					</form:select>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2">Department</label>
					<form:select class="form-control col-sm-10" path="department">
					<c:forEach var="d" items="${departments}">
					<c:if test="${d.department_id == employee.department}">
							<option value="${d.department_id}" ${'selected="selected"'}>${d.department}</option>
						</c:if>
						<c:if test="${d.department_id != employee.department}">
							<option value="${d.department_id}">${d.department}</option> 
						</c:if>
							
					</c:forEach>
					</form:select>
				</div>
	
				<input type="submit" class="btn btn-primary" value="Update Employee">
			</form:form>
		</div>
	</div>
</body>
</html>
