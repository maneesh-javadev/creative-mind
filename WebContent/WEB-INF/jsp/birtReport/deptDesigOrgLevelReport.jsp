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
  	String orgLvlCode = (String)request.getAttribute("orgLvlCode");
  	String orgCode = (String)request.getAttribute("orgCode");
	String orgzN = (String)request.getAttribute("orgztn");
	String stateCode = (String)request.getAttribute("stateCode");
	String serverloc = (String)request.getAttribute("serverloc");
  %>
  <body>
	<%-- <iframe width="100%" height="500" src="${serverLoc}?__report=<%=report_design%>&_ollc=<%=orgLvlCode%>&_orgcode=<%=orgCode%>&_orgName=<%=orgzN%>&_statecode=<%=stateCode%>>"></iframe> --%>
	
	
		<birt:viewer id="birtViewer" reportDesign="${report_design}" pattern="frameset" height="1100" width="500"  frameborder="no"	
              baseURL="${serverLoc}" showToolBar="false" showNavigationBar="false">
              
              <birt:param name="_orgcode" value="<%=orgCode%>"></birt:param>
              <birt:param name="_ollc" value="<%=orgLvlCode%>"></birt:param>
              <birt:param name="_orgName" value="<%=orgzN%>"></birt:param>
              <birt:param name="_statecode" value="<%=stateCode%>"></birt:param>
          </birt:viewer>
	
	
	<div class="btnpnl">
		<input type="button" id="close" name="close" value="Close" onclick="javascript:go('welcome.do');"></input>
 	</div> 
  </body>
</html>