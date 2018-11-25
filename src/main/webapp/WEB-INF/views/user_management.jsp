<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>User Management</title>
	
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
			<h2 class="contentTitle">User List</h2>
			<a class="btn btn-outline-success btn-sm appButton"
				href="/admin/account/add_admin">Add New Admin</a> <a
				class="btn btn-success btn-sm appButton"
				href="/admin/account/add_user">Add New User</a>
		</div>
		<table id="mainTable" class="table table-bordered table-hover">
			<thead class="mainTableHeader">
				<tr class="mainTableHeader">
					<th>Email</th>
					<th>Created Date</th>
					<th>Type</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${users}">
					<c:url var="updateLink" value="/admin/account/${user.id}" />
					<c:url var="deleteLink" value="/admin/account/delete_account">
						<c:param name="id" value="${user.id}"></c:param>
					</c:url>
					<tr>
						<td>${user.email}</td>
						<td>${user.timeCreated}</td>
						<td>${user.role.role}</td>
						<td><a class="btn btn-outline-success btn-sm tableLink"
							href="${updateLink}">Update</a> <c:if
								test="${loggedInUser.email.equals(user.email)}">
								<a class="btn btn-outline-success btn-sm tableLink disabled"
									href="${deleteLink}">Delete</a>
							</c:if> <c:if test="${!loggedInUser.email.equals(user.email)}">
								<a class="btn btn-outline-success btn-sm tableLink"
									href="${deleteLink}">Delete</a>
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>