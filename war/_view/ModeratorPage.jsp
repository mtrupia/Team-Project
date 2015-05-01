<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Moderator Page</title>
		<link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>	
		<style>
			body{
				background-image: -webkit-gradient(linear, left top, right bottom, color-stop(0, #FFFFFF), color-stop(1, #00A3EF));
				color: gold;
			}
			
			.table1{
				display: block;
				overflow-y: auto;
				margin-left: 10%;
				width: 80%;
				height: 400px;
				border: 2px solid lightgray;
			   	background: grey;
			   	font-family: Lobster;
			    
			}
			
			.table2{
				display: block;
				overflow-y: auto;
				margin-left: 10%;
				width: 80%;
				height: 200px;
				border: 2px solid lightgray;
			    background: gray;
			    color: gold;
			    font-family: Lobster;
			    padding-left: 10px; 
			    text-align: left;
			}
			
			.buttons1{
				background: 
				radial-gradient(rgba(255,255,255,0) 0, rgba(255,255,255,.15) 30%, rgba(255,255,255,.3) 32%, rgba(255,255,255,0) 33%) 0 0,
				radial-gradient(rgba(255,255,255,0) 0, rgba(255,255,255,.1) 11%, rgba(255,255,255,.3) 13%, rgba(255,255,255,0) 14%) 0 0,
				radial-gradient(rgba(255,255,255,0) 0, rgba(255,255,255,.2) 17%, rgba(255,255,255,.43) 19%, rgba(255,255,255,0) 20%) 0 110px,
				radial-gradient(rgba(255,255,255,0) 0, rgba(255,255,255,.2) 11%, rgba(255,255,255,.4) 13%, rgba(255,255,255,0) 14%) -130px -170px,
				radial-gradient(rgba(255,255,255,0) 0, rgba(255,255,255,.2) 11%, rgba(255,255,255,.4) 13%, rgba(255,255,255,0) 14%) 130px 370px,
				radial-gradient(rgba(255,255,255,0) 0, rgba(255,255,255,.1) 11%, rgba(255,255,255,.2) 13%, rgba(255,255,255,0) 14%) 0 0,
				linear-gradient(45deg, #343702 0%, #184500 20%, #187546 30%, #006782 40%, #0b1284 50%, #760ea1 60%, #83096e 70%, #840b2a 80%, #b13e12 90%, #e27412 100%);
				background-size: 470px 470px, 970px 970px, 410px 410px, 610px 610px, 530px 530px, 730px 730px, 100% 100%;
				background-color: #840b2a;
				width: 110px;
				
				padding: 5px;
				height: 10%;
				margin-left: 10%;
			}
			.buttons2{
				margin-top: 10px;
				height: 30px;
				text-align: center;
				margin-bottom: 3%;
				
			}
			.method1 {
				font-family: Lobster;
				height: 20%;
				font-size: 40px;
				margin-left: 10%;
				color: grey;
				background: -webkit-linear-gradient(top, #878787, #000);
				background: linear-gradient(top, #878787, #000);
				-webkit-background-clip: text;
				-webkit-text-fill-color: transparent;
			}
			.method2 {
				color: grey;
				font-family: Lobster;
				text-align: center;
				font-size: 60px;
				height: 10%;
				-webkit-mask-image: -webkit-linear-gradient(top, rgba(0,0,0,1), rgba(0,0,0,.5) 50%, rgba(0,0,0,1));
				-webkit-mask-image: linear-gradient(top, rgba(0,0,0,1), rgba(0,0,0,.5) 50%, rgba(0,0,0,1));
			}
			input[type=submit] {
			    border: 2px solid white;
			    background: green;
			    color: gold;
			    font-family: Lobster;
			    padding: 5px 10px;
			    border-radius: 3px;
			}
			input[type=submit]:hover {
			    cursor: pointer;
			}
	
		</style>
	</head>

	<body>
		<form action="${pageContext.servletContext.contextPath}/ModeratorPage" method="post">
			<div class="method2">
			
				<span>Mod's Page</span>
			
			</div>
			<div class="buttons1">
			<input type="Submit" name="MainPage" value="Main Page">
			<input type="Submit" name="userPage" value="Go To UserPage">
			</div>
			<div class="method1" >
			<span>Flagged List</span>
			</div>
			<table class="table1" >
				<c:forEach items="${Posts}" var="Comment">
					      
					        <tr >
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
			<div class="method1">
			<span >Make a Mod</span>
			</div>
			<table class="table2" >
				<c:forEach items="${Users}" var="Userd">
					<div class="fonts">
					<tr>
						<td><c:out value="${Userd.userName}"/></td>
						<td><input type="Submit" name="mod${Userd.id}" value="mod"></td>
					</tr>
					</div>
				</c:forEach>
			</table>
			<div class="buttons2">
			<input type="Submit" name="log" value="LogOut">
			</div>
		</form>
	</body>
</html>