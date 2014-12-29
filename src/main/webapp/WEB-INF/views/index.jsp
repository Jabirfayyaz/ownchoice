<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Cache-control" content="no-cache" charset="UTF-8">
		
		
		
		<!--[if lt IE 7 ]> <html lang="en" class="ie6 ielt8"> <![endif]-->
		<!--[if IE 7 ]>    <html lang="en" class="ie7 ielt8"> <![endif]-->
		<!--[if IE 8 ]>    <html lang="en" class="ie8"> <![endif]-->
		<!--[if (gte IE 9)|!(IE)]><!--> <html lang="en"> <!--<![endif]-->
		
		<title>Login</title>
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<link rel="stylesheet" type="text/css" href="css/box.css" />
		
		
		<!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
	</head>
	<body>

	
	<section id="content">
		<form action="${pageContext.request.contextPath}/login" method="POST" >
			<h1>Login Form</h1>
			<c:if test="${error ne null}">
				<div class="alert-box error">
					<c:out value="${error}"></c:out>
				</div>
			</c:if>
			<div>
				<input type="text" placeholder="User Name" required="" name="userName" id="userName" />
			</div>
			<div>
				<input type="password" placeholder="Password" required="" name="password"  id="password" />
			</div>
			<div>
				<input type="submit" value="Log in" class="content form input" />
				<a href="#">Lost your password?</a>
				<a href="#">Register</a>
			</div>
		</span><!-- form -->
		
	</section><!-- content -->
</div><!-- container -->
	</body>
</html>