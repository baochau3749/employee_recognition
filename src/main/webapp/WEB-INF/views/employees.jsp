<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>

<head> 	
	<title>Employees</title>
</head>

<body>
	<div>
		<div>
			<h2>Employees</h2>
		</div>
	</div>
	<div>
		
	<h3>Add Employee Page</h3>
		<div>
		<div>
			<form:form action="${pageContect.request.contextPath}/logout" method="POST">
			<input type="submit" value="Logout">
			</form:form>
		</div>
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
				<option value="f">female</option>
				<option value="m">male</option>
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
<div>
<div>	
<div>
		<table>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>

				<!-- loop over and print our customers -->
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