<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
<title>User Main Account</title>

<style>
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

.nav-item
{
	margin: auto 10px auto;
}

h2
{
	margin-bottom: 20px;
}
h3
{
	margin: 30px auto;
}
</style>
</head>

<body>
	<div class="container">

		<h2 class="display-3"  align="center">User Main Page</h2>

		<ul class="nav nav-pills nav-justified">
			<li class="nav-item">
				<a class="nav-link btn btn-outline-primary active" href="${pageContect.request.contextPath}/user">Home</a>
			</li>

			<li class="nav-item">
				<a class="nav-link btn btn-outline-primary" href="${pageContect.request.contextPath}/user/employees">Employee Management</a>
			</li>

			<li class="nav-item">
				<a class="nav-link btn btn-outline-primary" href="${pageContect.request.contextPath}/user/award">Create Award</a>
			</li>
					
			<li class="nav-item">
				<a class="nav-link btn btn-outline-primary" href="${pageContect.request.contextPath}/user/${user.id}">Update Profile</a>
			</li>
			
			<li class="nav-item">
				<form:form action="${pageContect.request.contextPath}/logout" method="POST">
					<input class="nav-link btn btn-outline-primary" type="submit" value="Logout">
				</form:form>
			</li>
		</ul>
		
		<hr>

		<h3>Welcome: ${user.email}</h3>


		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th scope="col">Award Type</th>
					<th scope="col">Employee Name</th>
					<th scope="col">Employee Email</th>
					<th scope="col">Date</th>
					<th scope="col">Action</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="award" items="${awards}" varStatus="i">
					<c:url var="deleteLink" value="/delete_award">
						<c:param name="id" value="${award.id}"></c:param>
					</c:url>

					<tr>
						<td>${award.awardType.type}</td>
						<td>${employees[i.index]}</td>
						<td>${emails[i.index]}</td>
						<td>${award.dateGiven}</td>
						<td><a href="${deleteLink}" class="btn btn-outline-primary"
							role="button">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>