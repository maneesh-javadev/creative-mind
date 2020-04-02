<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />

<%!String contextPath;%>

<%
	contextPath = request.getContextPath(); 
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="css/error.css" rel="stylesheet" type="text/css" />
</head>
	<body>
		<div class="form_stylings">
			<div class="header">
				<h3>List of Panchayat activated by NOFN in ${stateName}</h3>
			</div>
			<div class="page_content">
				<div class="form_container">
					
					<c:if test="${dataExists}">
                       	<div align="center">
                       		<% java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a"); %>
                        	<iframe width="100%" height="500" src="${serverLoc}?__report=${report_design}&stateName=${stateName}&stateCode=${entityName}&rpt_curr_dt_tm=<%=df.format(new java.util.Date())%>"></iframe>           
						</div>
					</c:if>
					    <div align="center" style="margin-left: -150px;">
						<input type="submit"  onclick="javascript:location.href='nofnStates.do?<csrf:token uri='nofnStates.do'/>'"  value="<spring:message code="Button.BACK"/>" />
						<input type="button" value="<spring:message code="Button.CLOSE"/>" onclick="javascript:location.href='welcome.do'"/>	
						</div>
				</div>
				</div>
			</div>
                       
		
	</body>
</html>