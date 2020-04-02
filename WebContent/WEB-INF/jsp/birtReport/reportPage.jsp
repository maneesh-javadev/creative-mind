<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>Report Result</title>
  </head>
  <% 
  	String report_design = (String)request.getAttribute("report_design");
  	String parenttype = (String)request.getAttribute("parenttype");
 
  %>
  <body>
  <iframe width="100%" height="500" src="${serverLoc}?__report=<%=report_design%>&_ptype=<%=parenttype%>"></iframe>
  </body>
</html>