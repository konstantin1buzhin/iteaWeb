
<%
  String login = request.getParameter("login");
  String password = request.getParameter("password");
  String logOut = request.getParameter("logOut");
  boolean showForm = true;
  String result = "";
  String key = "key";
  
	
	out.write("session: "+session.getId());

  if(login!=null){
    
    if(login.equals("admin") && password.equals("123")){
      result = "access granted";
      showForm=false;
	  session.setAttribute(key, "sessionCheck");
    }else{
      result = "access denied";
    }
	
	
  }	
	
	if(logOut != null){
		session.invalidate();
		session = request.getSession(true);
		showForm = true;
	}
	
	if(session.getAttribute(key) != null){
		result = "Hello guest";
		showForm = (session.getAttribute(key)!=null)?false: true;
	}
  
  if(showForm){
  %>

  <form action = ''>
  <table border = '1'>
    <tr>
    <td width = '50' align = 'center'>
    <%out.write("Login");%>
    </td>
    <td width = '50' align = 'center'>
    <input type = 'text', name = 'login'/>
    </td>
    </tr>
    <tr>
    <td width = '50' align = 'center'>
    <%out.write("Password");%>
    </td>
    <td width = '50' align = 'center'>
    <input type = 'text', name = 'password'/>
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
  <%
  }else{ %>
  <form action = '' method="post">
  <table>
  <tr>
    
    <td width = '50' align = 'center'>
    <input type = 'hidden', name = 'login' value="smth"/>
    </td>
    </tr>
	
	<tr>
    <td width = '50' align = 'center'>
    
    </td>
    <td width = '50' align = 'center'>
    <input type = 'submit', name = 'logOut' value = 'LogOut'/>
    </td>
    </tr>
	</table>
	</form>
  <%} out.write(result); 
  
	
  %>