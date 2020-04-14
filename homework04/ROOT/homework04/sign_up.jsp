<%@ page import="home04.DBInserter"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<meta charset="UTF-8" />
	<link href="form.css" rel="stylesheet" />
</head>
<center>
<button align="center" class="button" onclick="window.location.href = 'index.jsp';">Return Menu</button>
<table>
<tr>
 <%
 	String login = request.getParameter("login");
 	String name = request.getParameter("name");
 	String password = request.getParameter("password");
 	String repassword = request.getParameter("re-password");
 	String age = request.getParameter("age");
 	String gender = request.getParameter("gender");
 	String address = request.getParameter("address");
 	String comments = request.getParameter("comments");
 	String amigo = request.getParameter("amigo");
	String registration = "";
	
	
	if(login!=null){
		boolean existError= false;
		String errorText = "<ul >";
		
		if(login.length()==0) {
			existError = true;
			errorText += "<li>Empty Login</li>";
		}
		if(name.length()==0) {
			existError = true;
			errorText += "<li>Empty Name</li>";
		}
		
		if(password.length()==0) {
			existError = true;
			errorText += "<li>Empty Password</li>";
		}
		if(repassword.length()==0) {
			existError = true;
			errorText += "<li>Empty Re-Password</li>";
		}else{
			if(!repassword.equals(password)){
				existError = true;
				errorText += "<li>Passwords aren`t equals</li>";
			}
		}
		if(gender==null) {
			existError = true;
			errorText += "<li>Choose your gender</li>";
		}
		if(address.equals("Choose")) {
			existError = true;
			errorText += "<li>Choose your address</li>";
		}
		if(comments.length()==0) {
			existError = true;
			errorText += "<li>Empty Comments</li>";
		}
		
		if(amigo==null) {
			existError = true;
			
			errorText += "<li>Confirm installation AmigoBrowser</li>";
		}
		
		
		
		
		errorText += "</ul>";
		
		if(existError){
			out.write("<td>"+errorText+"</td>");
		}else{
			
			
			if(!DBInserter.checkLogin(login)){
				registration = "success";
				DBInserter.insert(login, name, password, Integer.valueOf(age), gender, comments, address, amigo);
				out.write("<br>Successful registration!<br>");
			}else{
				%>
				
		<script>
			alert( 'This e-mail exists!' );
		</script>
<%
				out.write("<ul><td>Change this e-mail</td></ul>");
			}
			
			
			
		}
	} else{
		out.write("<ul><td>Enter your data</td></ul>");
	}
 if(!registration.equals("success")){
	 
 %>



	
		<td>
<form id="loginForm" action = '' method = 'post'>
<table border = '1'>
	<div class="field">
	  <tr>
		<td align = 'center'>
		<label>Login(e-mail)</label>
		</td>
		<td align = 'center'>
		<div class="input"><input type = 'text', name = 'login' required='required' 
						pattern="[A-Za-z]{2,}@[A-Za-z]{2,}\.[A-Za-z]{2,}" value='<%=(login!=null)?login:""%>'/></div>
		</td>
	  </tr>
	 </div>
	 
	 <div class="field">
	  <tr>
		<td align = 'center'>
		<label>Name</label>
		</td>
		<td align = 'center'>
		<div class="input"><input type = 'text', name = 'name' value='<%=(name!=null)?name:""%>'/></div>
		</td>
	  </tr>
	 </div>
  
  <div class="field">
	  <tr>
		<td width = '50' align = 'center'>
		<label>Password</label>
		</td>
		<td width = '50' align = 'center'>
		<input type = 'password', name = 'password' pattern="(?=.*[0-9])(?=.*[A-Z]{2,})[0-9a-zA-Z]{8,}"/>
		</td>
	  </tr>
  </div>
  
	<div class="field">
	  <tr>
		<td width = '200' align = 'center' >
		<label>Re-Password</label>
		</td>
		<td width = '200' align = 'center'>
		<input type = 'password', name = 're-password'/>
		</td>
	  </tr>
	</div>
  
	<div class="field">
	  <tr>
		<td width = '50' align = 'center'>
		<label>Age(12-100)</label>
		</td>
		<td width = '50' align = 'center'>
		<input type = 'number', name = 'age' min="12" max="100" value="<%=(age!=null)?age:""%>"/>
		</td>
	  </tr>
	</div>
	
	<div class="field">
		 <tr>
				<td width = '50' align = 'center'>
				<label>Gender</label>
				</td>
				<td width = '50' align = 'center'>
				F<input type = 'radio', name = 'gender' value='F' <%=(gender!=null&&gender.equals("F"))?"checked":""%>/><br>
				M<input type = 'radio', name = 'gender' value='M' <%=(gender!=null&&gender.equals("M"))?"checked":""%>/>
				</td>
		</tr>
	</div>
  
  <div class="field">
	   <tr>
		<td width = '50' align = 'center'>
		<label>Address</label>
		</td>
		<td width = '50' align = 'center'>
		<select name='address'>
			<option>Choose</option>
			<option value='1' <%=(address!=null&&address.equals("1"))?"selected":""%>>DNR</option>
			<option value='2' <%=(address!=null&&address.equals("2"))?"selected":""%>>LNR</option>
			<option value='3' <%=(address!=null&&address.equals("3"))?"selected":""%>>Crimea</option>
		</td>
	  </tr>
  </div>
  
  <div class="field">
	  <tr>
		<td width = '50' align = 'center'>
		<label>Comments</label>
		</td>
		<td width = '50' align = 'center'>
		<textarea name='comments' cols='20' rows='10'><%=(comments!=null)?comments:""%></textarea>
		</td>
	  </tr>
  </div>
  
  <div class="field">
		<tr>
		<td width = '50' align = 'center'>
		<label>Amigo</label>
		</td>
		<td width = '50' align = 'center'>
		<input type = 'checkbox', name = 'amigo' <%=(amigo!=null)?"checked":""%>/>
		</td>
	  </tr>
  </div>
  
  
  
	
 </table>
 <div class="submit">
 <button type="submit">Enter</button>
	   <tr>
		<td width = '50' align = 'center'>
		
		</td>
		<td height='50' width = '50' align = 'center'>
		
		</td>
	  </tr>
	</div>
 </form>
  <%}%>
		</td>

 </tr>

 </table>
 </center>
 