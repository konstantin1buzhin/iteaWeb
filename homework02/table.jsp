<center>
<%
	int rows =  Integer.parseInt(request.getParameter("rowsCount"));
	int columns = Integer.parseInt(request.getParameter("columnsCount"));
	out.write("Rows="+rows);
	out.write(" Columns="+columns);
	if((rows%2)!=0&&(columns%2)!=0){
		out.write("<h1 style='color:#00ff00'>Your table</h1>");%>
		<table border='1' width="300px">
		<center>
		<%
		int centerTable = (rows*columns)/2+1;
		for(int k=1;k<=(rows*columns);k++){
		for(int i=1;i<=rows;i++){
				for(int j=1;j<=columns;j++){
					if(i==1||j==1||i==rows||j==columns){
						out.write("<td bgcolor='red'>"+k+"</td>");
					}else{
						if(k==centerTable){
							out.write("<td>"+k+"</td>");
						}else{
							out.write("<td bgcolor='green'>"+k+"</td>");
						}
							
						
					}
					
					
					k++;
				}
		out.write("</tr>");
		}
		}
				%>
		
		<center>
		</table><br>
		
		<form action="index.jsp">
			<button type="submit">Come back</button>
			</form>
	  <%
	}else{
		out.write("<h1 style='color:#ff0000'>You`ve entered wrong data</h1>");
		%>
		
		<form action="index.jsp">
         <button type="submit">Come back</button>
      </form>
	  <%
	  
	}
	
	
%>
</center>