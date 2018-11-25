<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>User Main Page</title>
	
	<link rel="stylesheet" href="/css/style.css" type="text/css" />
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
		integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
		crossorigin="anonymous">
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
</head>

<body id="mainBody">
	<div id="titleContainer">
		<div id="appName">EMPLOYEE RECOGNITION</div>
		<div id="projectDescription">CS 467 - Capstone Project</div>
	</div>

	<nav id="menuNav"
		class="navbar navbar-light sticky-top navbar-expand-sm">
		<button type="button" class="navbar-toggler" data-toggle="collapse"
			data-target="#mainMenu">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div id="mainMenu" class="menu collapse navbar-collapse">
			<ul class="menu navbar-nav">
				<li class="btn menu nav-item"><a class="menu nav-link"
					href="${pageContext.request.contextPath}/admin/user_management">
						User Management </a></li>
				<li class="btn menu nav-item"><a class="menu nav-link"
					href="${pageContext.request.contextPath}/admin/award_report">
						Award Report </a></li>
			</ul>
			<ul class="menu navbar-nav ml-auto">
				<li class="btn lastMenu nav-item">${loggedInUser.email}<a
					class="lastMenu nav-link"
					href="${pageContect.request.contextPath}/logout"> Log out </a>
				</li>
			</ul>
		</div>
	</nav>
	
	<div id="contentContainer">
		<div class="contentTitleContainer">
			<h2 class="contentTitle">Add User Account</h2>
		</div>
		<div id="formDiv">
			<form:form modelAttribute="account" method="POST" 
				action="${pageContext.request.contextPath}/admin/account/save_admin">

				<c:if test="${account != null}">
					<input type="hidden" id="id" name="id" value="${id}"/>
					<input type="hidden" id="timeCreated" name="timeCreated" value="${account.timeCreated}"/>			
				</c:if>	


				<div style="margin: 20px 0px;" class="form-group row">
					<label class="formLabel" for="email">Email:</label> 
					<input class="formInput" type="email" name="email" value="${account.email}" />
				</div>

				<div style="margin: 20px 0px;" class="form-group row">
					<label class="formLabel" for="password">Password:</label>
					<input class="formInput" type="password" name="password" value="${account.password}" />
				</div>

				<div style="margin: 20px 0px;" class="form-group row">
					<input class="formSubmitButton" type="submit" value="Save" />
				</div>
			</form:form>
		</div>
	</div>			
</body>
</html>