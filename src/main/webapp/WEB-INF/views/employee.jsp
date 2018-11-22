<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>

<head> 	
	<title>Add Employee</title>
</head>

<body>
	
	<a href="${user.id}">Update Profile</a>
	<br><br>
	<a href="employees">Employee Management</a>
	<br><br>
	<a href="award">Create Award</a>
	<br><br>
	
	<form:form action="${pageContect.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout">
	</form:form>
	<div>
		<div>
			<h2>Add Employee</h2>
		</div>
	</div>
		<div>
			<form:form action="addEmployee" modelAttribute="employee" method="GET">
				First Name: <form:input path="firstName"/>
				<br><br>
		
				Last Name: <form:input path="lastName"/>
				<br><br>
		
				Email: <form:input path="email"/>
				<br><br>
		
				Birth Date: <form:input path="birthDate"/>
				<br><br>
		
				Gender: <form:select path="gender">
				<option value="Female">Female</option>
				<option value="Male">Male</option>
				</form:select>
				<br><br>
				
				State: <form:select path="state">
				<c:forEach var="s" items="${states}">
						<option value="${s.state_id}">${s.state}</option> 
				</c:forEach>
				</form:select>
				<br><br>
				
				Position: <form:select path="position">
				<c:forEach var="p" items="${positions}">
						<option value="${p.position_id}">${p.position}</option> 
				</c:forEach>
				</form:select>
				<br><br>
				
				Department: <form:select path="department">
				<c:forEach var="d" items="${departments}">
						<option value="${d.department_id}">${d.department}</option> 
				</c:forEach>
				</form:select>
				<br><br>
				
			<input type="submit" value="Add Employee">
	</form:form>
	</div>

</body>
</html>