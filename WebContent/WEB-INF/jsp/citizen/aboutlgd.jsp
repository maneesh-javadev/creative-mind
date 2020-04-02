<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="in.nic.pes.lgd.utils.ApplicationConstant"%>
<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>

</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message code="Label.sitemap" htmlEscape="true" text="Sitemap"/></h3>
			<ul class="listing">
				<li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /></a></li>
				<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a></li>
			</ul>			
		</div>		
		
		<div class="frmpnlbrdr">
			<form:form  name="form1" id="form1" method="POST" >
			<div class="about-us-top"> 
             <span>About Local Government Directory</span> </div>
            <div class="about-us-section"> 
            <span>
            Primary objective of Local Government directory is to facilitate State Departments to update the directory with newly formed panchayats/local bodies,re-organization in panchayats, 
            conversion from Rural to Urban area etc and provide the same info in public domain. <br/><br/>
            Key Features of Local Government Directory:
           </br/>
            <ul>
            	<li style="width: 100%;padding-left:1.5%;">Generation of unique code for each local government body - each local government body is assigned with a unique code.</li>
            	<li style="width: 100%;padding-left:1.5%;">Maintenance of local government bodies and its mapping with constituting land region entities. For ex. gram panchayat mapping with villages.</li>
            	<li style="width: 100%;padding-left:1.5%;">Mandatory upload of Govt. order for each modification in the directory - to ascertain the users that the data published in LGD is authentic.</li>
            	<li style="width: 100%;padding-left:1.5%;">Maintenance of historical data - when modifications take place in LGD, the old values/data is archived. </li>
            	<li style="width: 100%;padding-left:1.5%;">Provision to maintain state specific local government setup.</li>
            	<li style="width: 100%;padding-left:1.5%;">Compliance with Census 2011 codes.</li>
            	<li style="width: 100%;padding-left:1.5%;">Facility to integrate with state specific standard codes - if any state is following standard codes for state level software applications, the same code can be linked to LGD code.</li>
            </ul>
            
            </span> 
			</form:form>
		</div>
	</div>
</body>
</html>

