<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>

<head> 	
	<title>Create Award</title>
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
	
<style>
.nav-item
{
	margin: auto 10px auto;
}

h2
{
	margin-bottom: 20px;
}
</style>
</head>

<body>
	<div class="container">
	
		<h2 class="display-3"  align="center">Create Award</h2>
		
		<ul class="nav nav-pills nav-justified">
			<li class="nav-item">
				<a class="nav-link btn btn-outline-primary" href="${pageContect.request.contextPath}/user">Home</a>
			</li>
			
			<li class="nav-item">
				<a class="nav-link btn btn-outline-primary" href="employees">Employee Management</a>
			</li>
			
			<li class="nav-item">
				<a class="nav-link btn btn-outline-primary active" href="">Create Award</a>
			</li>
			
			<li class="nav-item">
				<a class="nav-link btn btn-outline-primary" href="${user.id}">Update Profile</a>
			</li>
			
			<li class="nav-item">
				<form:form action="${pageContect.request.contextPath}/logout" method="POST">
					<input class="nav-link btn btn-outline-primary" type="submit" value="Logout">
				</form:form>
			</li>	
		</ul>
		
		<hr>

		<div>
			<form:form action="createAward" modelAttribute="award" method="POST">
				<div class="form-group row">
					<label class="col-sm-2">Employee</label>
					<form:select class="form-control col-sm-10" path="employee">
					<c:forEach var="e" items="${employees}">
							<option value="${e.id}">${e.firstName} ${e.lastName}</option> 
					</c:forEach>
					</form:select>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2">Award Type</label>
					<form:select class="form-control col-sm-10" name="awardType" path="awardType">
					<c:forEach var="t" items="${awardTypes}">
							<option value="${t.awardTypeId}">${t.type}</option> 
					</c:forEach>
					</form:select>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2">Date and Time (ex. 11/2/2018 12:00 pm)</label>
					<form:input class="form-control col-sm-10" name="dateGiven" path="dateGiven"/>
				</div>
				
				<input type="submit" class="btn btn-primary" value="Create and Email Award">
			</form:form>
		</div>
	</div>

</body>
</html>