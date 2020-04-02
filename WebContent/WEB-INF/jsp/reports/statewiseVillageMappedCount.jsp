<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="../taglib_includes.jsp"%>
<%@include file="../homebody/commanInclude.jsp"%>
<%@ taglib uri="/birt.tld" prefix="birt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script>
function validationForm()  
{   
	var error = false;
	
	 var captcha = $("#captchaAnswer" ).val();
 	if(captcha == ""){
 		
 		$("#errorCaptcha").text("Please enter the Captcha Shown Above");
 		
 		error=true;
 	}
 		 if(!error){
 			 callActionUrl('getStatewiseVillageMappedCount.do');
 		 }
};
callActionUrl=function(url){
$( 'form[id=genericReportingEntity]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
$( 'form[id=genericReportingEntity]' ).attr('method','post');
$( 'form[id=genericReportingEntity]' ).submit();
};
</script>
</head>
<body>

	
<section class="content">

 <!-- Main row -->
 <div class="row">
     <!-- main col -->
	<div class="container">
	<section class="col-lg-12 prebox">
     	<div class="box">
            <div class="box-header with-border">
            <h3 class="box-title">State Wise Block To Village Mapping Status</h3>
              </div><!-- /.box-header -->
            <c:choose>
            <c:when test="${showReport eq false}">
             <form:form class="form-horizontal" action="getStatewiseVillageMappedCount.do" method="POST" id="genericReportingEntity" commandName="genericReportingEntity">
             <input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="getStatewiseVillageMappedCount.do"/>" />
            <div class="box-body">
		                    <div class="form-group">
		                     	<label for="captchaImage" class="col-sm-4 control-label"></label>
		                      <div class="col-sm-6">
		                        <img src="captchaImage" alt="Captcha Image"  id="captchaImageId"/>
		                       </div>
		                    </div>
		                    <div class="form-group">
		                      <label for="captchaAnswer" class="col-sm-4 control-label"><spring:message code="captcha.message"	htmlEscape="true" /><span class="mandatory">*</span></label>
		                      <div class="col-sm-6">
		                      	<form:input	path="captchaAnswer" id="captchaAnswer" autocomplete="off"  maxlength="5"/>
								<a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>
								<span id="errorCaptcha" class="errormsg"
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
		                      		<button type=button class="btn btn-primary" onclick="return validationForm();" id="actionFetchDetails"><i class="fa fa-undo"></i> <spring:message htmlEscape="true" code="Button.GETDATA"/></button>
		                            <button type="button" class="btn btn-danger " onclick="javascript:location.href='welcome.do'"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE"/></button>
		                        </div>
		                     </div>   
		    </div><!-- /.box-footer --> 
            </form:form>
            </c:when>
            <c:otherwise>
            <% java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a"); %>
             <birt:viewer id="birtViewer" reportDesign="${report_design}" pattern="frameset" height="700" width="1111"
               baseURL="${serverLoc}">
              <birt:param name="rpt_curr_dt_tm" value="<%=df.format(new java.util.Date())%>"></birt:param>
              </birt:viewer>
		                  
             <div class="box-footer" id="showbutton">
                    <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">

							<button type="button" name="Submit3" id="btn1" class="btn btn-danger" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
					   </div>
					 </div>
			 </div>
            
            </c:otherwise>
            
            </c:choose>
          
           </div>
            </section>
            </div>
            </div>
            </section>


</body>
</html>