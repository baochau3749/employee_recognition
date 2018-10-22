<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>

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
	<div>
		<div>
			<h3>USER LIST</h3>
		</div>
		<table>
			<thead>
				<tr>
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
						<td><a href="${updateLink}">Update</a> | <a
							href="${deleteLink}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<c:url var="addUserLink" value="/admin/account/add_user" />
		<c:url var="addAdminLink" value="/admin/account/add_admin" />
		<br><a href="${addUserLink}">Add New User</a><br>
		<br><a href="${addAdminLink}">Add New Admin</a><br>
		<div></div>
	</div>

	<hr><br>
	<form:form action="${pageContect.request.contextPath}/logout"
		method="POST">
		<input type="submit" value="Logout">
	</form:form>
</body>
</html>