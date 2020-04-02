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
<style type="text/css">
	#tbl td{
		padding: 10px 10px 10px 10px;
	}
</style>
<script type="text/javascript">
	function validate(){
		$("#captchaAnswer").val($("#captcha").val());
		return true;
	}
	
	function back(){
		
	}
</script>
</head>
	<body>
		<div class="form_stylings">
			<div class="header">
				<h3>GIS Map Verification Status</h3>
			</div>
			<div class="page_content">
				<c:if test="${initialState==true}">
				<div class="form_container">
					
							
					<form:form  method="POST" action="gisMapVerificationStatus.do" commandName="viewDeptforadmin">
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="gisMapVerificationStatus.do"/>"/>
						<input type="hidden" id="captchaAnswer" name="captchaAnswer" value="">
					    <label class="inline">&nbsp;</label>
					    <div class="form_block" align="center">
					    <div class="col_1" style="width: 70%;" align="left">
								<table id="tbl">
									<tr>
										<td></td>
										<td>
											<img src="captchaImage" alt="Captcha Image" />
										</td>
									</tr>
									<tr>
										<td align="right">
											<label>
												<spring:message code="captcha.message"	htmlEscape="true" />
												<span class="mandate">*</span>
											</label>
										</td>
										<td>
											<input type="text" id="captcha" autocomplete="off" width="10%" />
											<c:if test="${not empty captchaFaliedMessage and not captchaFaliedMessage}">
												<br/><label><!-- Used Label, please dont remove. --></label>
												<span class="errormsg"><strong><spring:message code="captcha.errorMessage"/></strong></span>
											</c:if>
										</td>
									</tr>
									<tr>
									<td></td>
									<td>
										<input type="submit" onclick="return validate();"  value="<spring:message htmlEscape="true" code="Button.GETDATA"/>" />
										<input type="button" value="<spring:message code="Button.CLOSE"/>" onclick="javascript:location.href='welcome.do'"/>
									</td>
									</tr>
								</table>
								
							</div>
							</div>
					</form:form>
					</div>
					</c:if>
					<div align="center">
					<c:if test="${dataExists==true}">
						<% java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a"); %>
						  <div align="center">
							<birt:viewer id="birtViewer" reportDesign="${report_design}" pattern="frameset" height="700" width="1500" format="${format}"  baseURL="${serverLoc}">
								<birt:param name="rpt_curr_dt_tm" value="<%=df.format(new java.util.Date())%>"></birt:param>
							</birt:viewer>
						  </div>
						  <input type="button" onclick="javascript:location.href='gisMapVerificationStatus.do?<csrf:token uri='gisMapVerificationStatus.do'/>'" value="Back"/>
							<input type="button" value="<spring:message code="Button.CLOSE"/>" onclick="javascript:location.href='welcome.do'"/>
						</c:if> 
				
				
						</div>
				</div>
				
			</div>
                       
		
	</body>
</html>