<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>

<head>
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
		h2
		{
			margin-bottom: 20px;
		}
		
		.nav-item
		{
			margin: auto 10px auto;
		}

		table {
			height: 100px;
			width: auto;
			margin: 5px;
			background-color: black;
			text-align: center;
		}
		
		tr, th, td {
			padding: 10px;
			background-color: white;
		}
	</style>
	<title>Employee Management</title>
</head>

<body>

	<div class="container">
	
		<h2 class="display-3"  align="center">Employee Management</h2>
		
		<ul class="nav nav-pills nav-justified">
			<li class="nav-item">
				<a class="nav-link btn btn-outline-primary" href="${pageContect.request.contextPath}/user">Home</a>
			</li>
			
			<li class="nav-item">
				<a class="nav-link btn btn-outline-primary active" href="">Employee Management</a>
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
			<a class="btn btn-primary" href="/user/employee">Add Employee</a>
			<br><br>
			<table class="table table-bordered table-striped">
				<tr>
					<th scope="col">First Name</th>
					<th scope="col">Last Name</th>
					<th scope="col">Email</th>
					<th scope="col">Action</th>
	
					<!-- loop over and print our employees -->
					<c:forEach var="emp" items="${employees}">
						<c:url var="deleteLink" value="/user/delete_employee">
							<c:param name="id" value="${emp.id}"></c:param>
						</c:url>
						<c:url var="updateLink" value="/user/editEmployee">
							    <c:param name="id" value="${emp.id}"></c:param>
						</c:url>
						<tr>
							<td> ${emp.firstName} </td> 
							<td> ${emp.lastName} </td> 
							<td> ${emp.email} </td> 
							<td>
							<a href="${updateLink}" class="btn btn-outline-primary"
							role="button">Update</a>
							<a href="${deleteLink}" class="btn btn-outline-primary"
							role="button">Delete</a></td>
						</tr>
					</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>