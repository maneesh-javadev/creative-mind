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
             <span>About PES</span> </div>
            <div class="about-us-section"> 
            <span>
           The Ministry of Panchayati Raj (MoPR) has undertaken e-Panchayat Mission Mode Project(e-Panchayat MMP) 
				with a view to introduce and strengthen e-Governance in Panchayati Raj Institutions (PRIs) 
				across the country and build associated capacities of the PRIs for effective adoption of 
				the e-Governance initiative. Under this project, Panchayat Enterprise Suite (PES) has been 
				conceptualised which comprises 11 Core Common applications. At present, Panchayat Enterprise 
				Suite has been deployed/operational with 10 Core Common Applications and GIS layer module is under 
				conceptualisation. The operational modules includes LGD(Local Government Directory), 
				Area Profiler(Socio-economic & general details), PlanPlus(to strengthen Decentralised & 
				Participatory Planning), PriaSoft(Panchayat Accounting), ActionSoft(Works/scehme implementation 
				Monitoring System), NAD(National Asset Directory), Service Plus(To facilitate Service Delivery), 
				Social Audit, Training and National Panchayat Portal(Dynamic Website of Panchayats.
           </br/>
           
            
            </span> 
			</form:form>
		</div>
	</div>
</body>
</html>

