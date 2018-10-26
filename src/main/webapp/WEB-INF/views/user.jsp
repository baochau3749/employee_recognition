<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
</style>

</head>
<body>
	<h2>User Main Page</h2>

	<a href="user/${user.id}">Update Profile</a>
	<br><br>
	<a href="">Employee Management</a>
	<br><br>
	<a href="">Create Award</a>
	<br><br>
	<form:form action="${pageContect.request.contextPath}/logout"
		method="POST">
		<input type="submit" value="Logout">
	</form:form>
	<hr>

	<h3>Welcome: ${user.email}</h3>


	<table>
		<thead>
			<tr>
				<th>Award Type</th>
				<th>Employee</th>
				<th>Date</th>
				<th>Action</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="award" items="${awards}">

				<c:url var="deleteLink" value="/delete_award">
					<c:param name="id" value="${award.id}"></c:param>
				</c:url>
				
				<tr>
					<td>${award.awardType}</td>
					<td>${award.employee}</td>
					<td>${award.timeCreated}</td>
					<td><a href="${deleteLink}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>