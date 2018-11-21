<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>

<head> 	
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
	<title>Employee Management</title>
</head>

<body>
	<a href="${user.id}">Update Profile</a>
	<br><br>
	<a href="">Employee Management</a>
	<br><br>
	<a href="award">Create Award</a>
	<br><br>

	<form:form action="${pageContect.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout">
	</form:form>

	<div>
		<div>
			<h2>Employees</h2>
		</div>
	</div>
<div>
<div>
<div>	
<div>
<!-- Brian's changes start here -->
<a href="/user/employee">Add Employee</a>
	<br><br>
		<table>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Action</th>

				<!-- loop over and print our employees -->
				<c:forEach var="emp" items="${employees}">
				<c:url var="deleteLink" value="/user/delete_employee">
					<c:param name="id" value="${emp.id}"></c:param>
				</c:url>
					<tr>
						<td> ${emp.firstName} </td> 
						<td> ${emp.lastName} </td> 
						<td> ${emp.email} </td>
						<td><a href="${deleteLink}">Delete</a></td>
					</tr>
				</c:forEach>
				
<!-- 				Brian's changes end here -->
		</table>
		</div>
		</div>
	</div>
	</div>
</body>
</html>