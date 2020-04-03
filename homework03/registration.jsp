<%
// login(email) type text (empty регулярка проверки на email)
//name type text    not empty
//age text    12-100 age
//address type select check address
//gender radioButton not empty
//comments textarea not empty
//i agree  to install AmigoBrowser(no DB) checkbox     not emptys
// password password (error repassword, min:8 numbers and 2Capital letters Latin only
// repeat password(no DB) password


//rightside Error text
%>
<table>
	<tr>
		<td>
<form action = ''>
<table border = '1'>
  <tr>
    <td width = '50' align = 'center'>
    Login
    </td>
    <td width = '50' align = 'center'>
    <input type = 'text', name = 'login' pattern="[A-Za-z]{2,}@[A-Za-z]{2,}\.[A-Za-z]{2,}"/>
    </td>
  </tr>
  
  <tr>
    <td width = '50' align = 'center'>
    Password
    </td>
    <td width = '50' align = 'center'>
    <input type = 'password', name = 'password' pattern="(?=.*[0-9])(?=.*[A-Z]{2,})[0-9a-zA-Z]{8,}"/>
    </td>
  </tr>
  
  <tr>
    <td width = '100' align = 'center' >
    Re-Password
    </td>
    <td width = '50' align = 'center'>
    <input type = 'password', name = 're-password'/>
    </td>
  </tr>
  
  <tr>
    <td width = '50' align = 'center'>
    Age(12-100)
    </td>
    <td width = '50' align = 'center'>
    <input type = 'number', name = 'age' min="12" max="100"/>
    </td>
  </tr>
  
  <tr>
		<td width = '50' align = 'center'>
		Gender
		</td>
		<td width = '50' align = 'center'>
		F<input type = 'radio', name = 'gender' value='F'/>
		M<input type = 'radio', name = 'gender' value='M'/>
		</td>
	  </tr>
  
   <tr>
    <td width = '50' align = 'center'>
    Address
    </td>
    <td width = '50' align = 'center'>
    <select name='address'>
		<option selected>Choose</option>
		<option value='1'>DNR</option>
		<option value='2'>LNR</option>
		<option value='3'>Crimea</option>
    </td>
  </tr>
  
  <tr>
    <td width = '50' align = 'center'>
    Comments
    </td>
    <td width = '50' align = 'center'>
    <textarea name='comments' cols='20' rows='10'></textarea>
    </td>
  </tr>
  
    <tr>
    <td width = '50' align = 'center'>
    Amigo
    </td>
    <td width = '50' align = 'center'>
    <input type = 'checkbox', name = 'amigo'/>
    </td>
  </tr>
  
   <tr>
    <td width = '50' align = 'center'>
    
    </td>
    <td width = '50' align = 'center'>
    <input type = 'submit', value = 'OK'/>
    </td>
  </tr>
 </table>
 </form>
		</td>
 <%
 	String login = request.getParameter("login");
 	String password = request.getParameter("password");
 	String repassword = request.getParameter("re-password");
 	String age = request.getParameter("age");
 	String gender = request.getParameter("gender");
 	String address = request.getParameter("address");
 	String comments = request.getParameter("comments");
 	String amigo = request.getParameter("amigo");
	
	if(login!=null){
		boolean existError= false;
		String errorText = "<ul >";
		
		if(login.length()==0) {
			existError = true;
			errorText += "<li>Empty Login</li>";
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
			out.write("<td>"+"Registration Success"+"</td>");
		}
	}
 
 %>
 </tr>
 </table>