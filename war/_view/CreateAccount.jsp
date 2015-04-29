<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Create Account</title>
		<style type="text/css">
		    .error {
		      color: red;
		    }
	    </style>
	</head>

	<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
		<form action="${pageContext.servletContext.contextPath}/CreateAccount" method="post">
		<p>Please enter account details:</p>
		<p>Username must be at least 6 characters long!</p>
		<p>Password must be at least 6 characters long with </p>
		<p>at least 1 number and upper and lower case character</p>
			<table>
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
			<input type="Submit" name="create" value="Create Account">
			<input type="Submit" name="cancel" value="Log-In">
		</form>
	</body>
</html>