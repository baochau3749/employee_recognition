<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>

<head> 	
	<title>Give Award</title>
</head>

<body>

	<a href="${user.id}">Update Profile</a>
	<br><br>
	<a href="employees">Employee Management</a>
	<br><br>
	<a href="">Create Award</a>
	<br><br>

	<form:form action="${pageContect.request.contextPath}/logout"
		method="POST">
		<input type="submit" value="Logout">
	</form:form>
	<hr>
	<div>
		<div>
			<h2>Give Award</h2>
		</div>
	</div>
		<div>
			<form:form action="createAward" modelAttribute="award" method="POST">
				<%-- user is ${user.email} --%>
				<br>
			
				Employee: <form:select path="employee">
				<c:forEach var="e" items="${employees}">
						<option value="${e.id}">${e.firstName} ${e.lastName}</option> 
				</c:forEach>
				</form:select>
				<br><br>
				
				Award Type: <form:select name="awardType" path="awardType">
				<c:forEach var="t" items="${awardTypes}">
						<option value="${t.awardTypeId}">${t.type}</option> 
				</c:forEach>
				</form:select>
				<br><br>
				
				Date and Time (ex. 11/2/2018 12:00 pm): <form:input name="dateGiven" path="dateGiven"/>
				<br><br>
				
			<input type="submit" value="Create Award">			
	</form:form>
	</div>

</body>
</html>