<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Page</title>

<noscript>
	<meta http-equiv="Refresh" content="0; URL=nonscript.htm" />
</noscript>
</head>
<body onload="">
 <div >
		
		<div style="height: 300px;">
			<center>
				<table style="margin-top: 100px;">
					<tr>
						<td align="center">
							<img src='${pageContext.request.contextPath}/images/error_stop.jpg' width="50px" height="50px" style="vertical-align: middle;"></img>
						</td>
						<td>
							<h1 style="color: red;font-size: 20px; margin-left: 10px;">
								Currently the site is experiencing technical problems. <a href="<%=getServletContext().getInitParameter("URL")%>/welcome.do?">Click here</a> to go to Home Page	
							</h1>
						</td>	
					</tr>
				</table>
			</center>
		</div>
		
	</div> 
	
</body>

</html>