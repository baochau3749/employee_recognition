<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>User Main Page</title>
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
		<c:set var="pageLabel" value="Add New User Account"/>
		<c:if test="${account != null}">
			<c:set var="pageLabel" value="Update User Account"/>
		</c:if>		
		<h2 class="display-3"  align="center">${pageLabel}</h2>	
		
		<ul class="nav nav-pills justify-content-center">
				<li class="nav-item">
					<a class="nav-link btn btn-outline-primary" href="${pageContext.request.contextPath}/admin/user_management">User Management</a>
				</li>
				
				<li class="nav-item">
					<a class="nav-link btn btn-outline-primary" href="${pageContext.request.contextPath}/admin/award_report">Award Report</a>
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
	
		<form:form modelAttribute="account" method="POST" enctype="multipart/form-data"
				   action="${pageContext.request.contextPath}/admin/account/save_user">
			
			<c:if test="${account != null}">
				<input type="hidden" id="id" name="id" value="${id}"/>
				<input type="hidden" id="timeCreated" name="timeCreated" value="${account.timeCreated}"/>
				<input type="hidden" id="sig" name="sig" value="${account.userProfile.targetFile }"/>
				<input type="hidden" id="userProfileId" name="userProfileId" value="${account.userProfile.id}"/>
				<%-- <input type="hidden" id="userProfile" name="userProfile" value="${account.userProfile}"/> --%>
			</c:if>			   
			<c:if test="${account == null}">
				<input type="hidden" id="userProfileId" name="userProfileId" value="${-1}"/>
			</c:if>
			
			<div class="form-group row">
				<label class="col-sm-2">Email</label>
				<input class="form-control col-sm-10" type="email" name="email" value="${account.email}"/>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">Password</label>
				<input class="form-control col-sm-10" type="password" name="password" value="${account.password}"/>
			</div>

			<c:if test="${account != null}">
				<div class="form-group row">
					<label class="col-sm-2">First Name</label>
					<input class="form-control col-sm-10" type="text" name="firstName" value="${account.userProfile.firstName}" placeholder="First Name"/></p>
				</div>
			</c:if>
				
			<c:if test="${account == null}">
				<div class="form-group row">
					<label class="col-sm-2">First Name</label>
					<input class="form-control col-sm-10" type="text" name="firstName" value="" placeholder="First Name"/></p>
				</div>
			</c:if>	
			
			<c:if test="${account != null}">
				<div class="form-group row">
					<label class="col-sm-2">Last Name</label>
					<input class="form-control col-sm-10" type="text" name="lastName" value="${account.userProfile.lastName}" placeholder="Last Name"/></p>
				</div>
			</c:if>	
			
			<c:if test="${account == null}">
				<div class="form-group row">
					<label class="col-sm-2">Last Name</label>
					<input class="form-control col-sm-10" type="text" name="lastName" value="" placeholder="Last Name"/></p>
				</div>
			</c:if>	
			
			<div class="form-group row">
				<label class="col-sm-2">Signature</label>
				<input class="col-sm-8" type="file" name="file"/></p>
			</div>
			
			<input type="submit" class="btn btn-primary" value="Save"/>
			
			<c:if test="${not empty er}">
				${er}
			</c:if>
		</form:form>
	</div>
	
	
</body>
</html>