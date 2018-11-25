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
		
	<style>
		#table1 {
			height: 100px;
			width: auto;
			margin: 5px;
			background-color: black;
			text-align: center;
			border: 1px;
   			border-style: solid;
   			border-radius: 3px;
   			border-color: gray;
		}
		
		tr, th, td {
			padding: 10px;
			background-color: white;
			border: 1px;
   			border-style: solid;
   			border-radius: 3px;
   			border-color: gray;
		}
		
		li {
			text-align: left;
			margin-bottom: 5px;
		}
		
		li progress {
			float: left;
			margin: 0px 30px;
		}
	</style>
	
	
	<script>
	setTimeout(function () {
	    window.location.reload(1);
	}, 3000);
	</script>
	
	
	

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
			<h2 class="contentTitle">Award Reporting Page</h2>
		</div>
	</div>
	
	<%-- <h2>Award Reporting Page</h2>
	<h4>Logged In: ${loggedInUser.email}</h4>
	<hr>

	<a href="${pageContext.request.contextPath}/admin/user_management">User
		Management</a>
	<a href="${pageContext.request.contextPath}/admin/award_report">Award
		Report</a>

	<hr>
	<br>
 --%>
  <div style="margin: 50px 20px 20px 20px; font-weight: bold;">Below section is not formatted yet!</div>
	<div >
<!-- 		<div>
			<h3>REPORT LIST</h3>
		</div> -->
		
		<table id="table1">
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


	<h3>${title}</h3>
	<ul>
		<c:forEach var="current" items="${table}">
			<li>${current.key}: <progress value="${current.value}"
					max="${total}"></progress> ${current.value}
			</li>
		</c:forEach>
	</ul>


	<hr>
	<br>
	<form:form action="${pageContect.request.contextPath}/logout"
		method="POST">
		<input type="submit" value="Logout">
	</form:form>
</body>
</html>