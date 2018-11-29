<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
		integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
		crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.js"></script>
	<title>Award Report</title>
	
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
		
		li {
			text-align: left;
			margin-bottom: 5px;
		}
		
		li progress {
			float: left;
			margin: 0px 30px;
		}
		
		.nav-item
		{
			margin: auto 10px auto;
		}
		
		h2
		{
			margin-bottom: 20px;
		}
		h3
		{
			margin-top: 50px;
			margin-bottom: 30px;
		}
	</style>
</head>

<body>
	<div class="container">
		<c:url var="reportLink" value="${pageContext.request.contextPath}/admin/award_report">
			<c:param name="label" value="default"></c:param>
		</c:url>
			
		<h2 class="display-3"  align="center">Award Reporting Page</h2>
		
		<ul class="nav nav-pills justify-content-center">
			<li class="nav-item">
				<a class="nav-link btn btn-outline-primary" href="${pageContext.request.contextPath}/admin/user_management">User Management</a>
			</li>
			
			<li class="nav-item">
				<a class="nav-link btn btn-outline-primary active" href="${reportLink}">Award Report</a>
			</li>
			
			<li class="nav-item">
				<form:form action="${pageContect.request.contextPath}/logout"
					method="POST">
					<input class="nav-link btn btn-outline-primary" type="submit" value="Logout">
				</form:form>
			</li>
		</ul>
		
		<hr>
	
		<h3>Welcome: ${loggedInUser.email}</h3>

		<div>
			<div>
				<h3 align="center">REPORT LIST</h3>
			</div>
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th scope="col">Report/Analytics</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="report" items="${report}">
	
						<c:url var="downloadLink" value="/admin/account/download_report">
							<c:param name="label" value="${report.label}"></c:param>
						</c:url>
						<c:url var="analysisLink" value="${pageContext.request.contextPath}/admin/award_report">
							<c:param name="label" value="${report.label}"></c:param>
						</c:url>
						<tr>
							<td>${report.name}</td>
							<td>
								<a href="${downloadLink}" class="btn btn-outline-primary" role="button">Download</a>
								<a style="margin-left: 15px" href="${analysisLink}" class="btn btn-outline-primary" role="button">Show Analysis</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	
		<h2 style="text-align:center; margin-top: 50px">${title}</h2>

		<!-- Backup if other approach shows problems last minute 
		<ul>
			<c:forEach var="current" items="${table}">
				<li>
					${current.key}:
					<progress value="${current.value}" max="${total}"></progress>
					${current.value}
				</li>
			</c:forEach>
		</ul>
		-->
		
		<canvas id="myChart"></canvas>
	
		<script language="JavaScript">
			var labelList = new Array();
			var dataList = new Array();
		
			<c:forEach var="current" items="${table}" varStatus="loop">
				labelList.push(String('${current.key}'));
				dataList.push(${current.value});
			</c:forEach>
			
			var ctx = document.getElementById("myChart").getContext('2d');
			var myChart = new Chart(ctx, {
			  type: 'bar',
			  data: {
			    labels: labelList,
			    datasets: [{
			      label: 'Awards Receieved',
			      data: dataList,
			      backgroundColor: [
			        'rgba(255, 99, 132, 0.2)',
			        'rgba(54, 162, 235, 0.2)',
			        'rgba(255, 206, 86, 0.2)',
			        'rgba(75, 192, 192, 0.2)',
			        'rgba(153, 102, 255, 0.2)',
			        'rgba(255, 159, 64, 0.2)'
			      ],
			      borderColor: [
			        'rgba(255,99,132,1)',
			        'rgba(54, 162, 235, 1)',
			        'rgba(255, 206, 86, 1)',
			        'rgba(75, 192, 192, 1)',
			        'rgba(153, 102, 255, 1)',
			        'rgba(255, 159, 64, 1)'
			      ],
			      borderWidth: 1
			    }]
			  },
			  options: {
				title: {
					display: true,
					text: 'Awards Received',
				},
				legend: {
					display: false,
				},
			    scales: {
			      yAxes: [{
			        ticks: {
			          beginAtZero: true
			        }
			      }]
			    }
			  }
			});
		</script>
		<hr><br>
	</div>
</body>
</html>