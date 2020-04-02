<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String message=null;
if(request.getAttribute("message")!=null)
	message=request.getAttribute("message").toString();

%>
<table width="60%">
<tr>
<td>
<div align="center" ><%=message %></div>
</td>
</tr>
</table>

  

</body>
</html>