<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib uri="/birt.tld" prefix="birt" %>
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
		  <div class="box">
		  <div class="box-header  with-border"">
				<h3>State Wise Unmapped Village List(${stateName})</h3>
			</div>
                <div class="box-body">
                     <% java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a"); %>
                        	<birt:viewer id="birtViewer" reportDesign="${report_design}" pattern="frameset" height="700" width="1500"  baseURL="${serverLoc}">
                        	<%-- <birt:param name="stateName" value="${stateName}"></birt:param> --%>
                        	<birt:param name="stateCode" value="${entityName}"></birt:param>
                        	<birt:param name="rpt_curr_dt_tm" value="<%=df.format(new java.util.Date())%>"></birt:param>
                        		</birt:viewer>           
                </div>
		            <div class="box-footer">                    
		            		<button type="button"  class="btn btn-danger pull-right"" id="close" name="beck" value="Close" onclick="javascript:location.href='welcome.do'"><i class="fa fa-times-circle"></i> Close</button>
			          </div>
		               
              </div>
	</body>
</html>