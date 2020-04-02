
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <%@include file="../homebody/commanInclude.jsp"%>
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib uri="/birt.tld" prefix="birt" %>

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
		
		error = false;
		 var captcha = $("#captchaAnswer").val();
		if(captcha == "")
			{
			$("#errorCaptcha").text("Please enter the Captcha Shown Above");
			error=true;
			}
		if(!error)
			{
			callActionUrl('getStateWiseUrbanLB.do');
			}
		
	};
	callActionUrl=function(url){
		$( 'form[id=genericReportingEntity]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
		$( 'form[id=genericReportingEntity]' ).attr('method','post');
		$( 'form[id=genericReportingEntity]' ).submit();
		};

	
	function back(){
		
	}
</script>
</head>
<body>
	<section class="content">
  		<div class="row">
          <!-- main col -->
     		<section class="col-lg-12">

       			<div class="box">
				      <div class="box-header with-border">
				        <h3 class="box-title">Report on State Wise number of Urban Localbodies and Wards</h3>
				      </div><!-- /.box-header -->
				
				
				
				<c:if test="${initialState==true}">

				<form:form class="form-horizontal" method="POST" id="genericReportingEntity" action="getStateWiseUrbanLB.do" commandName="viewDeptforadmin">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="getStateWiseUrbanLB.do"/>"/>
					<!-- <input type="hidden" id="captchaAnswer" name="captchaAnswer" value=""> -->
						
						 <div class="box-body">
		                    <div class="form-group">
		                     	<label for="captchaImage" class="col-sm-4 control-label"></label>
		                      <div class="col-sm-6">
		                        <img src="captchaImage" alt="Captcha Image" id="captchaImageId"/>
		                       </div>
		                    </div>
		                    <div class="form-group">
		                      <label for="captchaAnswer" class="col-sm-4 control-label"><spring:message code="captcha.message"	htmlEscape="true" /><span class="mandatory">*</span></label>
		                      <div class="col-sm-6">
		                      	<input type="text" id="captchaAnswer" name="captchaAnswer" autocomplete="off" style="width:34%; " maxlength="5"  />
								<a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>
								<span id="errorCaptcha" class ="errormsg"></span>
								<c:if test="${not empty captchaFaliedMessage and not captchaFaliedMessage}">
									<br/><label><!-- Used Label, please dont remove. --></label>
									<span class="mandatory"><strong><spring:message code="captcha.errorMessage"/></strong></span>
								</c:if>
		                       </div>
	                    	</div>
					  </div>
						
						
					 <div class="box-footer">
	                     <div class="col-sm-offset-2 col-sm-10">
	                       <div class="pull-right">
					   			<input class="btn btn-primary" type="button" onclick="return validate();"  value="<spring:message htmlEscape="true" code="Button.GETDATA"/>" />
								<input class="btn btn-danger" type="button" value="<spring:message code="Button.CLOSE"/>" onclick="javascript:location.href='welcome.do'"/>
							</div>
						   </div>
					 </div>
				
					</form:form>
					</c:if>
					
					<c:if test="${dataExists==true}">
						<% java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a"); %> 
						  
						  
							<birt:viewer id="birtViewer" reportDesign="StateWiseNumberOfBodies.rptdesign" pattern="frameset" height="700" width="1500"   baseURL="${serverLoc}">
								<birt:param name="rpt_curr_dt_tm" value="<%=df.format(new java.util.Date())%>"></birt:param>
							</birt:viewer>
						
						<div class="box-footer">
	                     <div class="col-sm-offset-2 col-sm-10">
	                       <div class="pull-right">
						  		<input class="btn btn-primary" type="button" onclick="javascript:location.href='getStateWiseUrbanLB.do?<csrf:token uri='gisMapVerificationStatus.do'/>'" value="Back"/>
								<input class="btn btn-danger" type="button" value="<spring:message code="Button.CLOSE"/>" onclick="javascript:location.href='welcome.do'"/>
							</div>
						  </div>
						</div>
					</c:if> 
				
				
					
				</div>
				</section>
			</div>
		</section>	
		
	</body>
</html>
