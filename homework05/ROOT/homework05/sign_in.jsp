<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<meta charset="UTF-8" />
	<link href="form.css" rel="stylesheet" />
</head>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="homework05.CheckerSignIn" %>

<jsp:useBean id="arrayBean" class="homework05.CheckerSignIn"/>

<jsp:setProperty name='arrayBean' property='login' value="${param['login']}"/>
<jsp:setProperty name='arrayBean' property='password' value="${param['password']}"/>

<body>
	<c:set var='log' value="${arrayBean.login}"/>
    <c:set var='access' value="${arrayBean.access}"/>
    <c:set var='show' value="${arrayBean.show}"/>
</body>

<c:if test = "${access eq 'Successfully logged'}">
<script>
    alert( 'Successfully logged!' );
</script>
<center><button align="center" class="button" onclick="window.location.href = 'index.jsp';">
				Return Menu</button></center>
</c:if>

<c:if test = "${show eq false}">

<center>
<button align="center" class="button" onclick="window.location.href = 'index.jsp';">Return Menu</button>
<form id="loginForm" action="" method="post">
<table>


	<tr>
	
	
		<td>
		<label>Enter your login:</label>
		</td>
		<td align="center">
		<input type="text" name="login" id="login" value="${param['login']}"/>
		</td>
	
	</tr>

	<tr>
		<td>
		<label>Enter your password:</label>
		</td>
		<td align="center">
		<input type="password" name="password" value="" id="pass" />
		</td>
	
	
	</tr>
	

<c:if test = "${access eq 'Incorrect username or password' && param['login']!=null}">
	<tr>
	<td><label>Error:</label></td>
	<td align="center"><label>Incorrect username or password</label></td>
	</tr>
</c:if>
	
</table>

	<div class="submit">
		<button type="submit">Enter</button>
		<label id="remember" align="left"><input name="" type="checkbox" value="" /> Remember me</label>
	</div>

</form>
</c:if>
</center>
</html>
