<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<%@include file="../homebody/commanInclude.jsp"%>
<%@include file="../common/taglib_includes.jsp"%>
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>
<script type="text/javascript">
var id = "";
function getData() {
	id = document.getElementById('captchaAnswer').value;
	if (id == "") {
		alert("Please Enter Captcha Code");
		return false;
		}
	frmSubmit();
}
function frmSubmit(){
	document.getElementById("errormsg").innerHTML ="";
	document.forms['form1'].submit();
}
</script>
</head>
<body>
<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                     <form:form action="wardToHalkaMappingReport.do" method="POST" commandName="downloadView" id="form1" class="form-horizontal" >
	                    <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="wardToHalkaMappingReport.do"/>" />
						
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.WardHalkaMappingReport" text="Ward to Halka Mapping Report (Jharkhand State)" htmlEscape="true"></spring:message></h3>
                                </div>
                                
                                <div class="box-header subheading"><h4><spring:message htmlEscape="true"  code="Label.SELECTCRITERIA"></spring:message></h4></div>
                                 
                        <div class="box-body">
                        
                            <div class="form-group">
								<label  class="col-sm-4 control-label"></label>
								<div class="col-sm-6" >
								       <label class="radio-inline"><input type="radio" id="chkPdf" name="downloadOption" value="pdf" checked="checked" />&nbsp; <spring:message code="Label.PDF" htmlEscape="true"></spring:message></label>	        
									   <label class="radio-inline"><input type="radio" id="chkHtm" name="downloadOption" value="htm" />&nbsp; <spring:message code="Label.HTM" htmlEscape="true"></spring:message></label>	
								       <label class="radio-inline"><input type="radio" id="chkXls" name="downloadOption" value="xls" />&nbsp; <spring:message code="Label.XLS" htmlEscape="true"></spring:message></label>
								       <label class="radio-inline"><input type="radio" id="chkCsv" name="downloadOption" value="csv" />&nbsp; <spring:message code="Label.CSV" htmlEscape="true"></spring:message></label>    
								       <label class="radio-inline"><input type="radio" id="chkOdt" name="downloadOption" value="odt" />&nbsp; <spring:message code="Label.ODT" htmlEscape="true" text="ODT"></spring:message></label>      
								</div>
							</div>
							
							   <%@ include file="../common/captcha_integration.jspf"%>
							
								
					
     
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                  <button type="button" class="btn btn-success" onclick="getData();" ><i class="fa fa-floppy-o"></i> <spring:message code="Label.GENERATEREPORT" htmlEscape="true"></spring:message></button>
                  <button type="button" name="Submit2" class="btn btn-warning"  onclick="javascript:location.href='wardToHalkaMappingReport.do?<csrf:token uri='wardToHalkaMappingReport.do'/>';" ><spring:message code="Button.CLEAR" htmlEscape="true"></spring:message></button>
                  <button type="button" name="Submit3" class="btn btn-danger"  onclick="javascript:go('welcome.do');" ><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button>
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