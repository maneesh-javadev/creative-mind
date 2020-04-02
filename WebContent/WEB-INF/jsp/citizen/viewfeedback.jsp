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
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>

<script>
function validateForm(){
	flag=true;
	var name=document.getElementById("citizenName").value;
	var mail=document.getElementById("citizenEmail").value;
	var feedback=document.getElementById("feedback").value;
	if(name==""){
		document.getElementById("errorCitizenName").innerHTML = "<font color='red'><spring:message code='error.citizen.name.blank' text='Name is mandatory!'/></font>"; 
	    document.getElementById("errorCitizenName").focus();
	    flag=false;
		}else if(!validateLetterSpace(name)){
			document.getElementById("errorCitizenName").innerHTML="<font color='red'><spring:message code='error.citizen.name.validate' text='Name must be [A-Z][a-z] and space'/></font>";
			flag= false;
		}
	if(mail==""){
	document.getElementById("errorCitizenEmail").innerHTML = "<font color='red'><spring:message code='error.citizen.mail.blank' text='Email is mandatory!'/></font>"; 
    document.getElementById("errorCitizenEmail").focus();
    flag=false;
	}else if(!validateEmail(mail)){
		document.getElementById("errorCitizenEmail").innerHTML="<font color='red'><spring:message code='error.citizen.mail.validate' text='Email is not valid format'/></font>";
		flag= false;
	}
	
	if(feedback==""){
	document.getElementById("errorFeedback").innerHTML = "<font color='red'><spring:message code='error.citizen.feedback.blank' text='Feedback is mandatory!'/></font>"; 
	document.getElementById("errorFeedback").focus();
	flag=false;
	}

	if(flag){
		var errorCaptcha = document.getElementById('errorCaptcha');
		errorCaptcha.innerHTML = "";
		var sessionId = document.getElementById('sessionId').value; 
		var capchaImgVal = document.getElementById('captchaAnswer').value;
		/* Empty Captcha Check */
		if(capchaImgVal == null || capchaImgVal==""){
				document.getElementById("errEmptyCaptcha").innerHTML = "<font color='red'><spring:message code='Error.EmptyCaptcha'/></font>"; 
			    document.getElementById("errEmptyCaptcha").focus();
			    flag=false;
			}else{
				document.getElementById("errEmptyCaptcha").innerHTML= "";}
		}
	return flag;
}



function validateEmail(mail){
	var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/; 
	var flag=true;
	 if(!emailReg.test(mail)) {
		 flag=false;
	 }
	 return flag;
}
</script>
</head>
<body>
<section class="content">
    	<!-- Main row -->
   <div class="row">
        <!-- main col -->
            <section class="col-lg-12 ">
	           <div class="box ">
            	  <div class="box-header with-border">
						<h3 class="box-title"><spring:message code="Label.Feedback" htmlEscape="true" text="Feedback"/></h3>
            	  </div>
		              <!-- /.box-header -->
		                <div class="box-body">
							 <form:form autocomplete="off" class="form-horizontal" action="citizenFeedback.do" name="form1" id="form1" method="POST" commandName="citizenFeedback">
			                	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="citizenFeedback.do"/>" />
								<input type="hidden" id="sessionId" value='<%=sessionId%>'/>
								<center><label>We welcome your feedback and suggestions about the Local Government Directory to help us improve further and serve you better. <br/>(All fields are mandatory).</label></center>
								<center><c:if test="${message!=null}"><div id="message" class="errormsg"><font size="3"><c:out value="${message }" escapeXml="true"></c:out></font></div></c:if></center>
			                    
			                    	<div class="form-group">
                      					<label for="citizenName" class="col-sm-4 control-label"><spring:message code="Label.citizen.name" htmlEscape="true" text="Your Name"/><span class="mandatory">*</span></label>
	                      				<div class="col-sm-6">
		                      				<form:input type="text" path="citizenName" class="form-control" maxlength="50" length="60" />
											<form:errors path="citizenName" class="errormsg" />
									 		<div id="errorCitizenName"></div>
	                        			</div>
                    				</div>
                    				
                    				<div class="form-group">
                      					<label for="citizenEmail" class="col-sm-4 control-label"><spring:message code="Label.citizen.email" htmlEscape="true" text="Your Email"/><span class="mandatory">*</span></label>
	                      				<div class="col-sm-6">
		                      				<form:input type="text" class="form-control" path="citizenEmail" maxlength="50" length="60" />
											<form:errors path="citizenEmail" class="errormsg" />
											<div id="errorCitizenEmail"></div>
	                      				</div>
                    				</div>
                    				
                    				<div class="form-group">
                      					<label for="feedback" class="col-sm-4 control-label"><spring:message code="Label.citizen.feedback" htmlEscape="true" text="Feedback"/><span class="mandatory">*</span></label>
	                      				<div class="col-sm-6">
		                      				<form:textarea path="feedback" class="form-control" maxlength="500" rows="5" cols="50"  />
											<small>(Maximum upto 500 characters)</small>
											<form:errors path="feedback" class="errormsg" />
									 		<div id="errorFeedback"></div>
	                      				</div>
                    				</div>
                    				
                    				<div class="form-group"><%@ include file="../common/captcha_integration.jspf"%>
                    					<label class="col-sm-4 control-label"></label>
                      						<div class="col-sm-6">
		                      				
												<div class="errormsg">
												<c:if test="${ captchaSuccess2 == false }">
													<spring:message code="captcha.errorMessage" htmlEscape="true" />
												</c:if>
											</div>
											</div>
									
                    				</div>
                    				
                 <div class="box-footer">
                     <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
                      		<button type="submit" class="btn btn-success" onclick="return validateForm()"><i class="fa fa-arrow-circle-o-left" aria-hidden="true"></i> <spring:message code="button.citizen.send.feedback" htmlEscape="true" text="Send Feedback"/></button>
							<button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
					   </div>
					 </div>
			    </div>		    
			    
			    
	 </form:form>
	</div>
   </div>
  </section>
 </div>
</section>
</body>
</html>

