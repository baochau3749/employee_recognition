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
<a href="/user/employees">Employee Management</a>
	<br><br>
	<a href="">Create Award</a>
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
<a href="/user/employee">Add Employee</a>
	<br><br>
		<table>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>

				<!-- loop over and print our employees -->
				<c:forEach var="emp" items="${employees}">
					<tr>
						<td> ${emp.firstName} </td> 
						<td> ${emp.lastName} </td> 
						<td> ${emp.email} </td> 
					</tr>
				</c:forEach>
		</table>
		</div>
		</div>
	</div>
	</div>
</body>
</html>