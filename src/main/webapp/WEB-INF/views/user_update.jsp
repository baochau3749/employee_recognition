<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>User Update Page</title>
</head>
<body>
	<h2>User Update Page</h2>
	
	<a href="/user/employees">Employee Management</a>
	<br><br>
	<a href="">Create Award</a>
	<br><br>
	<form:form action="${pageContect.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout">
	</form:form>
	<hr>
	<br>
	
	<form:form modelAttribute="user" enctype="multipart/form-data">
		First Name: <form:input path="userProfile.firstName"/>
		<br><br>
		
		Last Name: <form:input path="userProfile.lastName"/>
		<br><br>
		
		Email: <form:input path="email"/>
		<br><br>
		
		Password: <form:input path="password"/>
		<br><br>
		
		Signature: <input type="file" name="file">
		<c:if test="${not empty er}">
   			${er}
		</c:if>
		<br><br>
		
		<input type="submit" value="Save">
	</form:form>
	<img src="/image" /> 
	
	<%-- <c:url var="updateLink4" value="${pageContect.request.contextPath}/my_text.txt" /> --%>
	<a href="/awardImage">1.txt</a>

</body>
</html>