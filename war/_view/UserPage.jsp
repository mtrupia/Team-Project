<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>${user.userName}</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/UserPage.css">
		<link href='http://fonts.googleapis.com/css?family=Fjalla+One' rel='stylesheet' type='text/css'>
	</head>

	<body>
		<form action="${pageContext.servletContext.contextPath}/UserPage" method="post">
			<div id="button">
				<input type="Submit" name="mainPage" value="Main Page">
			</div>
			<h1>
				<span>${user.FName} ${user.LName}'s Page</span>
			</h1>
			<div id="comment">
				<table>
					<tr>
						<td class="label">Comment:</td>
						<td><input type="text" placeholder="Today I..." name="text"
						size="100" value="" /> <input type="Submit" name="post"
						value="Post!"></td>
					</tr>
				</table>
			</div>
		</form>
		<form action="${pageContext.servletContext.contextPath}/UserPage" method="post">
			<div id="button2">
				<input type="Submit" name="posts" value="My Posts"> <input
				type="Submit" name="likes" value="My Likes">
			</div>
		</form>
		<form action="${pageContext.servletContext.contextPath}/UserPage" method="post">
			<div id="tables">
			<table>
				<c:forEach items="${Posts}" var="Comment">
				        <tr>
					        <c:if test="${Comment.userId == user.id}">
					        	<td><input type="Submit" name="delete${Comment.id}" value="X"></td>
							</c:if>
				            <td><c:choose>
							<c:when test="${fn:contains(Comment.text, 'youtube')}">
						    	<td>
						    	<iframe width="560" height="315" src="${Comment.text}" frameborder="0" allowfullscreen></iframe>
								</td>
						    </c:when>
						    <c:when test="${fn:contains(Comment.text, 'http')}">
						        <td><img src="${Comment.text}" width="200" height="200" /></td>
						    </c:when>
						    <c:otherwise>
						    	<td><c:out value="${Comment.text}"/></td>
						    </c:otherwise> 
						    </c:choose></td> 
				            <td><c:out value="Total likes: ${Comment.likes}"/></td> 
				            <td><c:out value="Total flags: ${Comment.flags}"/></td> 
				        </tr>
				   </c:forEach>
			</table>
			</div>
			<div id="logout">
				<input type="Submit" name="log" value="LogOut">
			</div>
		</form>
		<div id="background"></div>
		<div id="chest">
			<div class="heart left side top"></div>
			<div class="heart center">&hearts;</div>
			<div class="heart right side"></div>
		</div>
	</body>
</html>