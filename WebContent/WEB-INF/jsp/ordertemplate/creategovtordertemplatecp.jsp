
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%@include file="../common/taglib_includes.jsp"%>
<%-- <%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%> --%>
 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type='text/javascript' src="js/validation.js"></script>
<script type='text/javascript' src="js/govtOrderTemplate.js"></script>
<script type='text/javascript' src="js/trim-jquery.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrGovtOrderTemplate.js'>dwr.engine.setActiveReverseAjax(true);</script>
<link href="../sample.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
} 
</script>
<script type="text/javascript">

	function insertVarFckEditor(val) {
		var editor1 = CKEDITOR.instances.templateBodySrc;		
		editor1.insertText(val);		
		return false;
	}
</script>
</head>
<body onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
<section class="content">
                <div class="row">
                    <section class="col-lg-12">
                            <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message htmlEscape="true" code="Label.GOVTORDERTEMPLATE" /></h3>
                                </div>
               <div class="box-body">
              <div class="box-header subheading">
                             <h4 class="box-title"><spring:message htmlEscape="true" code="Label.CREATEGOVTORDERTEMPLATE" /></h4>
                  </div>
			<form:form action="createTemplate.htm" id="templateForm" name="templateForm" method="POST" commandName="governmentOrderTemplateForm" class="form-horizontal">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="createTemplate.htm"/>"/>
							<input type="hidden" name="category" id="category" value="<c:out value='${category}' escapeXml='true'></c:out>"/>
									<div class="form-group">
										<label for="templateNameEnglish" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.TEMPLATENAMEENG"></spring:message><span id="required" class="mandatory">*</span></label>
										<div class="col-sm-6">
										 	 <form:input	path="templateNameEnglish" id="templateNameEnglish" maxlength="50" class="form-control" onkeypress="return validateforTextandspace(event);"/>
										  	<form:hidden path="templateCode" id="templateCode" style="width: 200px" htmlEscape="true" class="frmfield"></form:hidden>
								 		  	<div id="templateNameEnglish_error" class="mandatory " style="display: none" ><spring:message code="error.PETM" htmlEscape="true"></spring:message></div>
											<div class="mandatory"><form:errors htmlEscape="true" path="templateNameEnglish"></form:errors></div> 
											<div class="mandatory" id="templateNameEnglish_error2" style="display: none"></div>
										</div>
									</div>
									
									
								<div class="form-group">
										<label for="operations" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.OPERATION"></spring:message><span id="required" class="mandatory">*</span></label>
									<div class="col-sm-6">
										<form:select path="operations" id="operations" onchange="getVariableList(this.value);" class="form-control" htmlEscape="true">
										<form:option value="0"><spring:message htmlEscape="true"  code="error.PSO"></spring:message></form:option>
										<form:options items="${oprList}" itemLabel="operationName" itemValue="operationCode"></form:options>
										</form:select> 
										<div id="operations_error" class="error" style="display: none"><spring:message code="error.PSO" htmlEscape="true"></spring:message></div> 
										<div class="mandatory"><form:errors htmlEscape="true"  path="operations"></form:errors></div>
										<div class="mandatory" id="operations_error2" style="display: none" ></div>
									</div>
							       </div>
									
									
								<div class="form-group">
										<label for="orderType" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.TYPEOFGOVTORDER"></spring:message><span id="required" class="mandatory">*</span></label>
									<div class="col-sm-6">
										<form:select path="orderType" id="orderType" class="form-control">
										<form:option value="0"><spring:message htmlEscape="true"  code="error.PSOT"></spring:message></form:option>
										<form:option value="O"><spring:message htmlEscape="true"  code="Label.GOVTORDER"></spring:message></form:option>
										<form:option value="A"><spring:message htmlEscape="true"  code="Label.ADDENDUM"></spring:message></form:option>
										<form:option value="C"><spring:message htmlEscape="true"  code="Label.CORRIGENDUM"></spring:message></form:option>
										</form:select>
										<div id="orderType_error" class="error" style="display: none"><spring:message code="error.PSOT" htmlEscape="true"></spring:message></div>  
										<div class="mandatory"><form:errors htmlEscape="true" path="orderType"></form:errors></div>
										<div class="mandatory" id="orderType_error2" style="display: none"></div>
									</div>
								</div>	
									
									
							 <div class="form-group">
										<label for="templateLanguage" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.TEMPLATELANGUAGE"></spring:message><span id="required" class="mandatory">*</span></label>
									<div class="col-sm-6">	
										<form:select path="templateLanguage" id="templateLanguage" class="form-control">
											<form:option value="0"><spring:message htmlEscape="true"  code="Label.PSL"></spring:message></form:option>
											<form:option value="English"><spring:message htmlEscape="true"  code="Label.ENGLISH"></spring:message></form:option>
											<form:option value="Local"><spring:message htmlEscape="true"  code="Label.LOCAL"></spring:message></form:option>
										</form:select>
										<div id="templateLanguage_error" class="error" style="display: none"><spring:message code="error.PSTL" htmlEscape="true"></spring:message></div> 
										<div class="mandatory"><form:errors htmlEscape="true"  path="templateLanguage"></form:errors></div> 
										<div class="mandatory" id="templateLanguage_error2" style="display: none"></div>
									</div>
								</div>	
									
									
									<div class="form-group">
										  <label for="selType" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.TEMPLATESRC"></spring:message><span id="required"
										  class="mandatory">*</span></label>
										  <div class="col-sm-6">
										   <select	id="selType" onchange="insertVarFckEditor(this.value);" class="form-control">
											<option><spring:message htmlEscape="true" code="Label.STEMPVAR"></spring:message></option>
										 </select>
										  <div id="selType_error" class="error" style="display: none"><spring:message code="error.TEMPLATESRC" htmlEscape="true"></spring:message></div> 
										  <div class="mandatory" id="selType_error2" style="display: none"></div>
										</div>
									</div>
									
									
									<div class="form-group">
									<label class="col-sm-1 control-label"></label>
										<div class="col-sm-10">
											<form:textarea path="templateBodySrc" id="templateBodySrc" cssClass="ckeditor"/>
										<div class="mandatory"><form:errors htmlEscape="true"  path="templateBodySrc" ></form:errors></div> 
										</div>
									</div>
				</div>
				<div class="box-footer">
                   <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
                            <button type="button" onclick="return onSaveTemplate();" name="Submit"  value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>"  class="btn btn-success " > Submit</button>
                             <button type="button" id="actionSearchClose"  class="btn btn-danger " onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" value="<spring:message code="Button.CLOSE" htmlEscape="true" ></spring:message>" ><i class="fa fa-times-circle"></i> Close</button>
                        </div>
                     </div>   
                  </div>
			</form:form>
		
		</div>
		</section>
		</div>
		</section>
		
		<script src="/LGD/JavaScriptServlet"></script>
		<script type="text/javascript">
	/* CKEDITOR.replace( 'templateBodySrc', {
		filebrowserBrowseUrl: 'imgBrowser.htm',
	}); */
	CKEDITOR.replace('templateBodySrc');
</script>
</body>
</html>