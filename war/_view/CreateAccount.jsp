<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Create Account</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/create_account.css"> 
		<style type="text/css">
		    .error {
		      color: red;
		    }
	    </style>
	</head>

	<body>
		
		<form id ="create_form" action="${pageContext.servletContext.contextPath}/CreateAccount" method="post">
		<div id = "texts">
		

		<p>Please enter account details:</p>
		<p>Username must be at least 6 characters long!</p>
		<p>Password must be at least 6 characters long with </p>
		<p>at least 1 number and upper and lower case character</p>
		</div>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>

			<table align="center" text-align = "center">
				<tr>
					<td class="label">First Name:</td>
					<td><input type="text" name="fname" size="20" value="" /></td>
				</tr>
				<tr>
					<td class="label">Last Name:</td>
					<td><input type="text" name="lname" size="20" value="" /></td>
				</tr>
				<tr>
					
					<td class="label">User Name:</td>
					<td><input type="text" name="username" size="20" value="" /></td>
				</tr>
				<tr>
					<td class="label">Password:</td>
					<td><input type="password" name="password" size="20" value="" /></td>
				</tr>
			</table>
			<input type="Submit" name="create" size="5" style = "font-size: 24px" style=" height: 30px;"value="Create Account">
			<input type="Submit" name="cancel" size="5" style = "font-size: 24px" style = "height = "30px;" value="Log-In">
		</form>
	</body>
</html>
