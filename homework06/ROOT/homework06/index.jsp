<html>
<head>
	<title></title>
	<meta charset="UTF-8" />
	<link href="mainPage.css" rel="stylesheet" />
</head>

<div class="mainPage">
	<center>${param['currentName']}<br><br>
		${!(param['currentSession'] eq 'checkLog') ? 
				"<form class='form' action='sign_in.jsp' method='post'>
					<button type='submit'>Sign in</button>
				</form>" :
		  
				"<form class='form' action='' method='post'>
					<input type='hidden' name='currentSession' value='tryCheck' />
					<button type='submit'>Log out</button>
		</form>"}
		 
		
		
		
		<form class='form' action="sign_up.jsp" method="post">
		<input type='hidden' name='currentSession' value="${param['currentSession']}" />
		<input type="hidden" name="currentName" value="${param['currentName']}" />
			<button  type='submit'>Sign up</button>
		</form>
		</center>
</div>

</html>
