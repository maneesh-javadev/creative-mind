<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
</head>
  <% 
  	
    String report_design = (String)request.getAttribute("report_design");
  	String stCode = request.getAttribute("stateCode").toString();
  	String orgLvlCode = (String)request.getAttribute("orgLvlCode");
  	String orgzN = (String)request.getAttribute("orgztn");
  	String orgLvl = (String)request.getAttribute("orgLvl");
  	String orgCode = (String)request.getAttribute("orgCode");
    
  %>
  <body>
	<%-- <iframe width="100%" height="500" src="${serverLoc}?__report=<%=report_design%>&_ollc=<%=orgLvlCode%>&_orgName=<%=orgzN%>&_orgLvl=<%=orgLvl%>&_slc=<%=stCode%>&_orgCode=<%=orgCode%>"></iframe> --%>
	
	<birt:viewer id="birtViewer" reportDesign="${report_design}" pattern="frameset" height="1100" width="500"  frameborder="no"	baseURL="${serverLoc}" showToolBar="false" showNavigationBar="false">
    	<birt:param name="_ollc" value="<%=orgLvlCode%>"></birt:param>
    	<birt:param name="_orgName" value="<%=orgzN%>"></birt:param>
    	<birt:param name="_orgLvl" value="<%=orgLvl%>"></birt:param>
    	<birt:param name="_slc" value="<%=stCode%>"></birt:param>
    	<birt:param name="_orgCode" value="<%=orgCode%>"></birt:param>
    </birt:viewer>
	
	<div class="btnpnl">
		<input type="button" id="close" name="close" value="Close" onclick="javascript:go('welcome.do');"></input>
 	</div> 
  </body>
</html>