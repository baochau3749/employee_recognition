<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>User Main Page</title>
</head>
<body>
	<c:set var="pageLabel" value="Add New User Account"/>
	<c:if test="${account != null}">
		<c:set var="pageLabel" value="Update User Account"/>
	</c:if>		
	<h2>${pageLabel}</h2>	
	<h4>Logged In: ${loggedInUser.email}</h4>	
	<hr>

	<form:form modelAttribute="account" method="POST"
			   action="${pageContext.request.contextPath}/admin/account/save_user">
		
		<c:if test="${account != null}">
			<input type="hidden" id="id" name="id" value="${id}"/>
			<input type="hidden" id="timeCreated" name="timeCreated" value="${account.timeCreated}"/>
			<input type="hidden" id="userProfile" name="userProfile" value="${account.userProfile}"/>
		</c:if>			   
					
		<p>Email: <input type="email" name="email" value="${account.email}"/></p>
		<p>Password: <input type="password" name="password" value="${account.password}"/></p>
		
		<p>First Name: <input type="text" name="firstName" value="" placeholder="First Name"/></p>
		<p>Last Name: <input type="text" name="lastName" value="" placeholder="Last Name"/></p>
		<p>Signature: <input type="file" name="targetFile"/></p>
			
		<input type="submit" value="Save"/>
		
	</form:form>
	
	<hr><br>
	<form:form action="${pageContect.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout">
	</form:form>
	
</body>
</html>