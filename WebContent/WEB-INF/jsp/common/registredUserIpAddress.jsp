<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <%@include file="../homebody/commanInclude.jsp"%>
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
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>
<style type="text/css">
	#tbl td{
		padding: 10px 10px 10px 10px;
	}
</style>
<script  type="text/javascript">
	function validate(){
		   var error=false;
		   var capchaAns=$("#captchaAnswer").val();
		   if(capchaAns =="")
			{
			 alert("Please Enter the Captcha...")
		    return false;
			}
		  if(!error){
			 callActionUrl('registredUserIpReport.do');
		 }
	}
	
	
	
	 /* 	function validateReport(){
		
		   var error=false;
		   var capchaAns=$("#captchaAnswer").val();
			var stateVal=$("#financialYear").val();
			if( stateVal==""){
				alert("Please Select Financial Year")
				return false;
			}
			 if(capchaAns =="")
				{
				 /* document.getElementById("errorCaptcha").innerHTML = "Please Enter CAPTCHA  in the textbox"; 
			    document.getElementById("errorCaptcha").focus(); 
			    alert("Please Enter the Captcha...")
			    return false;
				}
			
		      if(!error){
				 callActionUrl('rptVillageConvertedRuralToUrbanView.do');
			 }
		} 
	 */
		
</script>
</head>
	<body>
		<div class="form_stylings">
			<div class="header">
				<h3>Report on Consumers of Web Services</h3>
			</div>
			<div class="page_content">
				<c:if test="${initialState==true}">
				<div class="form_container">
					
							
					<form:form  method="POST" action="registredUserIpReport.do" commandName="viewDeptforadmin">
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="registredUserIpReport.do"/>"/>
						<!-- <input type="hidden" id="captchaAnswer" name="captchaAnswer" value=""> -->
					    <label class="inline">&nbsp;</label>
					    <div class="form_block" align="center">
					    <div class="col_1" style="width: 70%;" align="left">
					        <div class="table-responsive">
								<table id="tbl">
									<tr>
										<td></td>
										<td>
											<img src="captchaImage" alt="Captcha Image" id="captchaImageId" />
										</td>
									</tr>
									<tr>
										<td align="right">
											<label>
												<spring:message code="captcha.message"	htmlEscape="true" />
												<span class="mandatory">*</span>
											</label>
										</td>
										<td>
											<input type="text" id="captchaAnswer" name="captchaAnswer" autocomplete="off" width="10%" maxlength="5"/>
											<a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>
											<span id="errormsg" class="errormsg"></span>
											<c:if test="${not empty captchaFaliedMessage and not captchaFaliedMessage}">
												<br/><label><!-- Used Label, please dont remove. --></label>
												<span class="errormsg"><strong><spring:message code="captcha.errorMessage"/></strong></span>
											</c:if>
										</td>
									</tr>
									<tr>
									<td></td>
									<td>
										<%-- <input type="submit" onclick="return validate();" <spring:message htmlEscape="true" code="Button.GETDATA"/>" />
										<input type="button" value="<spring:message code="Button.CLOSE"/>" onclick="javascript:location.href='welcome.do'"/> --%>
										
										<button class="btn btn-success" type="submit" onclick="return  validate();"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true" code="Button.GETDATA"></spring:message></button>
	                                    <button type="button" class="btn btn-danger" onclick="javascript:location.href='welcome.do'"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE"/></button>
									</td>
									</tr>
								</table>
								</div>
							</div>
							</div>
					</form:form>
					</div>
					</c:if>
					<div align="center">
					<c:if test="${dataExists==true}">
						<% java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a"); %>
						  <div align="center">
							<birt:viewer id="birtViewer" reportDesign="${report_design}" pattern="frameset" height="700" width="1450" format="${format}"  baseURL="${serverLoc}">
								<birt:param name="rpt_curr_dt_tm" value="<%=df.format(new java.util.Date())%>"></birt:param>
							</birt:viewer>
						  </div>
						  <input type="button" onclick="javascript:location.href='registredUserIpReport.do?<csrf:token uri='registredUserIpReport.do'/>'" value="Back"/>
 						  <input type="button" value="<spring:message code="Button.CLOSE"/>" onclick="javascript:location.href='welcome.do'"/>
						</c:if> 
				
				
						</div>
				</div>
				
			</div>
                       
		
	</body>
</html>