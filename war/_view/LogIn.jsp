<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>	
		<title>Login</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/LogIn.css"> 
	</head>

	<body>
		<div id = "Title">
			Anonymous Comments
		</div>
		<div class="formme">
		<form id="login_form" action="${pageContext.servletContext.contextPath}/LogIn" method="post">
			<div id = "discription" >
			<p>
			<marquee behavior=scroll direction="center" scrollamount="2">Anonymous Comments Rock!</marquee>
			</p>
			</div>
			<div id="login_label">Please Log In</div>
			<div id="login_table_container">
			<div id = "tables">
			<c:if test="${! empty errorMessage}">
				<div class="error">${errorMessage}</div>
			</c:if>
			<table>
				<tr>
					<td class="label">Username:</td>
					<td><input type="text" name="name" style= "font-size: 24px;" size="5" style = "height: 30px;"value="${name}" /></td>
				</tr>
				<tr>
					<td class="label">Password:</td>
					<td><input type="password" name="pass" style = "font-size: 24px" size="5" style="height: 30px;"" value="${pass}" /></td>
				</tr>
			</table>
			<input type="Submit" name="log" size="5" style = "font-size: 24px" style=" height: 30px;" value="Login">
			<input type="Submit" name="create" value="Create Account" size="5" style = "font-size: 24px" style = "height = "30px;" value="Create Account">
			</div>
			</div>
		</form>
		</div>
	</body>
</html>