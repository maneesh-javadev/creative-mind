<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>Report Result</title>
 <script type="text/javascript" src="js/common.js" charset="utf-8"></script>
</head>
  <% 
  	String report_design = (String)request.getAttribute("report_design");
  System.out.print("${serverLoc}?__report="+report_design);
  %>
 <body>
	<%-- <iframe width="100%" height="500" src="${serverLoc}?__report=<%=report_design%>"></iframe> --%>
	
	<birt:viewer id="birtViewer" reportDesign="${report_design}" pattern="frameset" height="700" width="1500"   baseURL="${serverLoc}">
							</birt:viewer>
	
	
	<div class="btnpnl">
		<input type="button" id="close" name="close" value="Close" onclick="javascript:go('welcome.do');"></input>
 	</div> 
</body>
</html>