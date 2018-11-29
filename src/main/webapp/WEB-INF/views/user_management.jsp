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
	<title>User Management</title>
	
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
			margin-top: 50px;
			margin-bottom: 30px;
		}
	</style>
</head>

<body>
	<div class="container">
	
		<h2 class="display-3"  align="center">User Management Page</h2>
		
		<ul class="nav nav-pills justify-content-center">
			<li class="nav-item">
				<a class="nav-link btn btn-outline-primary active" href="${pageContext.request.contextPath}/admin/user_management">User Management</a>
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
		
		<div>
			<div>			
				<h3 style="margin-bottom: 20px" align="center">USER LIST</h3>				
			</div>
			<div style="margin: 0px 0px 20px 10px">
				<c:url var="addUserLink" value="/admin/account/add_user" />
				<c:url var="addAdminLink" value="/admin/account/add_admin" />
				<a style="margin-right: 20px" class="btn btn-primary" href="${addUserLink}">Add New User</a>
				<a class="btn btn-primary" href="${addAdminLink}">Add New Admin</a>
			</div>
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th scope="col">Email</th>
						<th scope="col">Created Date</th>
						<th scope="col">Type</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${users}">
						<c:url var="updateLink" value="/admin/account/${user.userId}" />
						<c:url var="deleteLink" value="/admin/account/delete_account">
							<c:param name="userId" value="${user.userId}"></c:param>
						</c:url>
						<tr>
							<td>${user.email}</td>
							<td>${user.timeCreated}</td>
							<td>${user.role.role}</td>
							<td>
								<a href="${updateLink}" class="btn btn-outline-primary" role="button">Update</a>
								<a href="${deleteLink}"  role="button" class="btn btn-outline-primary 
										${loggedInUser.email.equals(user.email) ? 'disabled' : ''}">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	

		</div>
		
	</div>
</body>
</html>