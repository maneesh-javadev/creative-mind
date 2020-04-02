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
			<section class="content">
			 <div class="row">
			     <!-- main col -->
				<div class="container">
					<section class="col-lg-12 prebox">
				     	<div class="box">
				            <div class="box-header with-border">
				              <h3 class="box-title">Report on changes occurred in a given time period</h3>
				            </div>
				            
				            <div class="box-body">
				            
				            <c:if test="${dataExists}">
		                       	<div align="center">
		                       		<% java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a"); %>
		                            <birt:viewer id="birtViewer" reportDesign="${report_design}" pattern="frameset" height="700" width="1100" baseURL="${serverLoc}">
		                                <birt:param name="fromDate" value="${fromDate}"></birt:param>
		                                <birt:param name="toDate" value="${toDate}"></birt:param>
		                                <birt:param name="rpt_curr_dt_tm" value="<%=df.format(new java.util.Date())%>"></birt:param>
		                            </birt:viewer>
		                        	        
								</div>
							</c:if>
				            
				            <div class="box-footer">
			                     <div class="col-sm-offset-2 col-sm-10">
			                       <div class="pull-right">
			                      		<button type="submit" onclick="javascript:location.href='changedEntity.do?<csrf:token uri='changedEntity.do'/>'" class="btn btn-primary"><spring:message htmlEscape="true" code="Button.BACK"/></button>
			                            <button type="button" class="btn btn-danger " onclick="javascript:location.href='welcome.do'"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE"/></button>
			                        </div>
			                     </div>   
		                  	</div>
				            
				            </div>
				         </div>
				     </section>
				</div>
		</div>
		</section>
                       
		
	</body>
</html>