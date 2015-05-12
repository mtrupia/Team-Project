<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Main Page</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/MainPage.css"> 
	</head>

	<body>
	<div class="titler">
		<h1>Anonymous Comments</h1>
	</div>
		<form class="posty" action="${pageContext.servletContext.contextPath}/MainPage" method="post">
			<div class="showy">
				<td><input type="Submit" name="allPosts" value="Show all!"></td>
			</div>
			<table>
				<tr>
					<td>
					<textarea rows="3" cols="50" name="text" placeholder="What's going on?" role="textbox" autocomplete="off"></textarea>
					<div class="postyy">
					<input type="Submit" name="post" value="Post!">
					</div>
					</td>
				</tr>
			</table>
		</form>
		<form class="searchy" action="${pageContext.servletContext.contextPath}/MainPage" method="post">
			<table>
		        <tr>
					<td>
					<input type="text" placeholder="Search something..." name="searching" size="30" value="" />
					</td>
		            <td><input type="Submit" name="search" value="Search"></td> 
		        </tr>
			</table>
		</form>
		<form action="${pageContext.servletContext.contextPath}/MainPage" method="post">
			<div class="commenty">
			<table id="comments">
				<c:forEach items="${Posts}" var="Comment">
				        <tr align="center">
				            <c:choose>
							<c:when test="${fn:contains(Comment.text, 'youtube')}">
						    	<div class="text9000">
						    	<td id="texter" align="center">
						    	<iframe width="300" height="215" src="${Comment.text}" frameborder="0" allowfullscreen></iframe>
								</td>
								</div>
						    </c:when>
						    <c:when test="${fn:contains(Comment.text, 'http')}">
						        <td id="texter"><img src="${Comment.text}" width="300" height="215" /></td>
						    </c:when>
						    <c:otherwise>
						    	<td id="texter"><c:out value="${Comment.text}"/></td>
						    </c:otherwise> 
						    </c:choose></td>
				            <td id="liker"><button type="submit" name="like${Comment.id}" value="clicked">
							<img src="http://www.sciepub.com/images/like.png"/>
							</button></td></td>
				            <td id="likertext"><c:out value="${Comment.likes}"/></td> 
				            <c:if test="${Comment.userId != user.id}">
				            <td id="flagger"><button type="submit" name="flag${Comment.id}" value="clicked">
							<img src="http://i.stack.imgur.com/Fh47a.png"/>
							</c:if>
							</button></td></td>
							<c:if test="${Comment.userId == user.id || user.id == 0}">
					        	<td id="remover"><button type="submit" name="delete${Comment.id}" value="clicked">
								<img src="http://log.concept2.com/images/delete.png"/>
								</button></td>
							</c:if>
				        </tr>
				   </c:forEach>
			</table>
			</div>
			<div class="logey">
			<input type="Submit" name="log" value="LogOut">
			</div>
			<div class="links">
			<input type="Submit" name="userPage" value="My Page">
			<c:if test="${user.modded == 1}">
				<input type="Submit" name="ModLink" value="Moderator Page">
				</div>
			</c:if>
		</form>
	</body>
</html>