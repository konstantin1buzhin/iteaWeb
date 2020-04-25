<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title></title>
	<meta charset="UTF-8" />
	<link href="<c:url value='/static/styles/mainPage.css'/>" rel="stylesheet" />
</head>

<div class="mainPage">
	<center>${param['currentName']}<br><br>
		${!(param['currentSession'] eq 'checkLog') ? 
				"<form class='form' action='SignInController' method='post'>
					<button type='submit'>Sign in</button>
				</form>" :
		  
				"<form class='form' action='' method='post'>
					<input type='hidden' name='currentSession' value='tryCheck' />
					<button type='submit'>Log out</button>
		</form>"}
		 
		
		
		
		<form class='form' action="SignUpController" method="post">
		<input type='hidden' name='currentSession' value="${param['currentSession']}" />
		<input type="hidden" name="currentName" value="${param['currentName']}" />
			<button  type='submit'>Sign up</button>
		</form>
		</center>
</div>

</html>
