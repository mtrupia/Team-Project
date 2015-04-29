<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Main Page</title>
		<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				var highestId = ${HighestId};
				$("#fetchComments").click(function() {
					// Use AJAX to fetch additional posts
					$.ajax({
							type: "POST",
							url: "${pageContext.servletContext.contextPath}/ajax/FetchPosts",
							data: { "startingId": highestId+1 },
							dataType: "json",
							success:
								function(data, textStatus, jqXHR) {
				            		// TODO: add new posts to the #comments table
				            		alert("Got some stuff!");
				            	},
							error:
								function(jqXHR, textStatus, errorThrown) {
				            		// TODO: better way to display error messages
				            		console.log("An error occurred: " + errorThrown);
				            	}
					});
				});
			});
		</script>-->
	</head>

	<body>
		<form action="${pageContext.servletContext.contextPath}/MainPage" method="post">
			<input type="Submit" name="userPage" value="My Page">
			<table>
				<tr>
					<td class="label">Comment:</td>
					<td>
					<textarea rows="4" cols="50" name="text" placeholder="What's going on?" role="textbox" autocomplete="off"></textarea>
					<input type="Submit" name="post" value="Post!">
					</td>
				</tr>
				<tr>
					<td><input type="Submit" name="allPosts" value="Show all!"></td>
				</tr>
			</table>
		</form>
		<form action="${pageContext.servletContext.contextPath}/MainPage" method="post">
			<table>
		        <tr>
		            <td class="label">Search:</td>
					<td>
					<input type="text" name="searching" size="30" value="" />
					</td>
		            <td><input type="Submit" name="search" value="Search"></td> 
		        </tr>
			</table>
		</form>
		<form action="${pageContext.servletContext.contextPath}/MainPage" method="post">
			<table id="comments">
				<c:forEach items="${Posts}" var="Comment">
				        <tr>
					        <c:if test="${Comment.userId == user.id}">
					        	<td><button type="submit" name="delete${Comment.id}" value="clicked">
								<img src="http://log.concept2.com/images/delete.png"/>
								</button></td>
							</c:if>
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
				            <td><button type="submit" name="like${Comment.id}" value="clicked">
							<img src="http://www.sciepub.com/images/like.png"/>
							</button></td></td>
				            <td><c:out value="Total likes: ${Comment.likes}"/></td> 
				            <td><button type="submit" name="flag${Comment.id}" value="clicked">
							<img src="http://i.stack.imgur.com/Fh47a.png"/>
							</button></td></td>
				            <td><c:out value="Total flags: ${Comment.flags}"/></td> 
				        </tr>
				   </c:forEach>
			</table>
			<input type="Submit" name="log" value="LogOut">
			<c:if test="${user.modded == 1}">
				<input type="Submit" name="ModLink" value="Moderator Page">
			</c:if>
		</form>
		<!--<button id="fetchComments">Load more comments</button>-->
	</body>
</html>