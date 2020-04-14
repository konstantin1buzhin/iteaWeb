<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<meta charset="UTF-8" />
	<link href="form.css" rel="stylesheet" />
</head>

<%@ page import="home04.CheckerSignIn"%>
<%
	String login = request.getParameter("login");
	String password = request.getParameter("password");
	boolean showForm = true; 
	String result = "";
	
	if(login!=null){
    CheckerSignIn checker = new CheckerSignIn(login, password);
    showForm = !checker.checking();
	result = checker.getAccess();
  }
  
if(result.equals("Successfully logged")){
%>
<script>
    alert( 'Successfully logged!' );
</script>
<center><button align="center" class="button" onclick="window.location.href = 'index.jsp';">
				Return Menu</button></center>
<%
}
if(showForm){
%>

<center>
<button align="center" class="button" onclick="window.location.href = 'index.jsp';">Return Menu</button>
<form id="loginForm" action="" method="post">
<table>


	<tr>
	
	
		<td>
		<label>Enter your login:</label>
		</td>
		<td align="center">
		<input type="text" name="login" id="login" value='<%=(login!=null)?login:""%>'/>
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
	
	<%
if(result.equals("Incorrect username or password")){
%>
	<tr>
	<td><label>Error:</label></td>
	<td align="center"><label><%=result%></label></td>
	</tr>
<%}%>
	
</table>

	<div class="submit">
		<button type="submit">Enter</button>
		<label id="remember" align="left"><input name="" type="checkbox" value="" /> Remember me</label>
	</div>

</form>
<%}%>
</center>
</html>
