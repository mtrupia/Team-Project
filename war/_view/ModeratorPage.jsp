<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Moderator Page</title>	
	</head>

	<body>
		<form action="${pageContext.servletContext.contextPath}/ModeratorPage" method="post">
			<input type="Submit" name="MainPage" value="Main Page">
			<input type="Submit" name="userPage" value="Go To UserPage">
			<table id="comments">
				<c:forEach items="${Posts}" var="Comment">
				        <tr>
				            <c:choose>
							<c:when test="${fn:contains(Comment.text, 'youtube')}">
						    	<td>
						    	<iframe width="560" height="315" src="${Comment.text}" frameborder="0" allowfullscreen></iframe>
								</td>
						    </c:when>
						    <c:when test="${fn:contains(Comment.text, 'http')}">
						        <td><img src="${Comment.text}" width="470" height="470" /></td>
						    </c:when>
						    <c:otherwise>
						    	<td><c:out value="${Comment.text}"/></td>
						    </c:otherwise> 
						    </c:choose></td>
				            <td><c:out value="Flag total: ${Comment.flags}"/></td> 
				            <td><input type="Submit" name="return${Comment.id}" value="Return to Main"></td>
				            <td><input type="Submit" name="remove${Comment.id}" value="Remove"></td>
				        </tr>
				   </c:forEach>
			</table>
			
			<table id="users">
				<c:forEach items="${Users}" var="Userd">
					<tr>
						<td><c:out value="${Userd.userName}"/></td>
						<td><input type="Submit" name="mod${Userd.id}" value="mod"></td>
					</tr>
				</c:forEach>
			</table>
			<input type="Submit" name="log" value="LogOut">
		</form>
	</body>
</html>