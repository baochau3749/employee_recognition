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
	<h2>Award Reporting Page</h2>
	<h4>Logged In: ${loggedInUser.email}</h4>
	<hr>
	
	<div>
		<div>			
			<h3>REPORT LIST</h3>
		</div>
		<table>
			<thead>
				<tr>
					<th>Report/Analytics</th>
					<th>Download Report</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="report" items="${report}">
				
					<c:url var="downloadLink" value="/admin/account/download_report">
						<c:param name="label" value="${report.label}"></c:param>
					</c:url>
					<tr>
						<td>${report.name}</td>
						<td><a href="${downloadLink}">Download</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<hr><br>
	<form:form action="${pageContect.request.contextPath}/logout"
		method="POST">
		<input type="submit" value="Logout">
	</form:form>
</body>
</html>