<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<%@include file="../common/taglib_includes.jsp"%>
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>
<script type="text/javascript">
function getData() {
	document.getElementById("errEmptyCaptcha").innerHTML = "";
	var stateobj = document.getElementById("ddSourceState");
	var disttobj = document.getElementById("ddSourceDistrict");
	var inSelectDist = disttobj.value;
	if(inSelectDist == null || inSelectDist == ""){
		document.getElementById("errSelectDistrict").innerHTML = "<font color='red'><br><b><spring:message code="error.select.DISTRICTFRMLIST"/></b></font>";  
		return false;
	}
	var capchaImgVal = document.getElementById('captchaAnswer').value;
	if(capchaImgVal == null || capchaImgVal == ""){
		document.getElementById("errEmptyCaptcha").style.display = 'block';
		document.getElementById("errEmptyCaptcha").innerHTML = "<font color='red'><br><b><spring:message code="Error.EmptyCaptcha"/></b></font>"; 
		document.getElementById('captchaAnswer').focus();
		return false;
	}
	
	document.getElementById('form1').statename.value = stateobj.options[stateobj.selectedIndex].text;
	document.getElementById('form1').disttname.value = disttobj.options[disttobj.selectedIndex].text;
	document.getElementById('form1').method = "post";
	document.getElementById('form1').action = "halkaToVillageMappingReport.do?<csrf:token uri='halkaToVillageMappingReport.do'/>";
	document.getElementById('form1').submit();
	return true;
}
function noenter(e) {
    e = e || window.event;
    var key = e.keyCode || e.charCode;
    return key !== 13; 
}
function removeError(){
	document.getElementById("errSelectDistrict").innerHTML="";
	document.getElementById("errEmptyCaptcha").innerHTML="";
}
</script>
</head>
<body>
			<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                     <form:form action="halkaToVillageMappingReport.do" method="POST" commandName="downloadView" id="form1" class="form-horizontal">
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="halkaToVillageMappingReport.do"/>" />
						<input type="hidden" id="statename" name="statename" />
						<input type="hidden" id="disttname" name="disttname" />
						
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.halkaToVillageMappingReport" text="Halka to Village Mapping Report (Jharkhand State)" htmlEscape="true"></spring:message></h3>
                                </div>
                                
                                 
                        <div class="box-body">
                        
                            <div class="form-group">
								<label  class="col-sm-4 control-label"for="ddSourceState"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								<form:select path="stateNameEnglish" class="form-control"  id="ddSourceState" disabled="true">
									<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message>
									</form:option>
									<form:options items="${statelist}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
								</form:select>
									<div id="errSelectState" class="mandatory"></div>		        
								</div>
							</div>
							
							
								<div class="form-group" id="Statelabel">
								<label class="col-sm-4 control-label" for="ddSourceDistrict"><spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
									<div class="col-sm-6">
										<form:select path="districtNameEnglish" class="form-control"  id="ddSourceDistrict" onfocus="validateOnFocus('ddSourceDistrict');helpMessage(this,'ddSourceDistrict_msg');"
												onblur="vlidateOnblur('ddSourceDistrict','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('ddSourceDistrict')" onchange="removeError();">
												<form:option value="">
													<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
												</form:option>
												<form:options items="${districtList}" itemValue="districtPK.districtCode" itemLabel="districtNameEnglish"></form:options>
											</form:select>
											<div id="errSelectDistrict" class="mandatory"></div>
									 </div>			
						        </div>
						        
						    <div class="form-group">
								<label  class="col-sm-4 control-label"></label>
								<div class="col-sm-6" >
								      	        
									   <label class="radio-inline"><input type="radio" id="chkPdf" name="downloadOption" value="pdf" checked="checked" />&nbsp;<spring:message code="Label.PDF" htmlEscape="true"></spring:message></label>	
								       <label class="radio-inline"><input type="radio" id="chkXls" name="downloadOption" value="xls" />&nbsp; <spring:message code="Label.XLS" htmlEscape="true"></spring:message></label>
								       <label class="radio-inline"><input type="radio" id="chkHtm" name="downloadOption" value="htm" />&nbsp; <spring:message code="Label.HTM" htmlEscape="true"></spring:message></label>    
								       <label class="radio-inline"><input type="radio" id="chkOdt" name="downloadOption" value="odt" />&nbsp; <spring:message code="Label.ODT" htmlEscape="true" text="ODT"></spring:message></label>      
								</div>
							</div>
							
							<%@ include file="../common/captcha_integration.jspf"%>
							
							
							
							
							
							
							</div>
					
     
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                  <button type="button"  class="btn btn-success" onclick="getData();" ><i class="fa fa-floppy-o"></i> <spring:message code="Label.GENERATEREPORT" htmlEscape="true"></spring:message></button>
                 <%--  <button type="button" name="Submit2" class="btn btn-info"  onclick="javascript:location.href='halkaToVillageMappingReport.do?<csrf:token uri='halkaToVillageMappingReport.do'/>';" ><spring:message code="Button.CLEAR" htmlEscape="true"></spring:message></button> --%>
				  <button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:go('welcome.do');" ><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button>
                 </div>
           </div>   
       </div>
       
       
    
     </div>   
             
    </form:form>      
   </section>
   </div>
   </section>
</body>
</html>