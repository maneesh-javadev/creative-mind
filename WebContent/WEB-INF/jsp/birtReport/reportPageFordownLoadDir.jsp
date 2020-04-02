<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>

 <title>Report Result</title>
  </head>
  <% 
 	String report_design = (String)request.getAttribute("report_design");
	String format = (String)request.getAttribute("format");
  	String entityCode = (String)request.getAttribute("entityCode");
  	String serverloc = (String)request.getAttribute("serverloc");
    
%>
  
  <body>
  
   <iframe width="100%" height="500" src="<%=serverloc%>?__report=<%=report_design%>&__format=<%=format%>&__asattachment=true&entity_code=<%=entityCode%>"></iframe>
  
  </body>
</html>