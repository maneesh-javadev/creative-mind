<!DOCTYPE html>
<html>
<%!String allowedNoOfAttachment = "1";
	String uploadLocation = "C:\\files";
	String allowedFileType = "gif,jpg,pdf,png";
	Long allowedTotalFileSize = 5L;
	Long allowedIndividualFileSize = 1L;
	String requiredTitle = "no";
	String uniqueTitle = "no";%>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="manageOrgLevelsJS.jsp"%>
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
<script>
$(document).ready(function() {
	
	$('#manageOrgLevels input[type=text]').attr("autocomplete",'off');
	
	$('.date').datetimepicker({
		format : 'dd-mm-yyyy',
		startView : 'month',
		minView : 'month',
		autoclose : true,
		pickerPosition : "bottom-left"
	});
});
	



</script>
</head>
<body class="dt-example">
		<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                    <form:form method="POST" name="manageOrgLevels" id="manageOrgLevels" commandName="orgLocatedAtLevelsForm" action="manageOrgLevels.htm" enctype="multipart/form-data" class="form-horizontal">
				      <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="draftShiftBlock.htm"/>"/>
				      
				                <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="manageOrgLevels.htm"/>" />
					             <input type="hidden" name="orgName" id="orgName" value="" />
                           <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Rename Level Specific Name of Organization</h3>
                                </div>
                                 <div class="box-header subheading">
                                    <h4 >General Details</h4>
                                </div>
                        <div class="box-body">
                            <div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.ORGTYPELIST"/><span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								   <form:select path="orgType" id="orgType" class="form-control" >
											<form:option value="">																
												<spring:message code="Label.SELECT" htmlEscape="true"/>
											</form:option>
											<form:options items="${orgTypeList}" itemLabel="orgType" itemValue="orgTypeCode" />
										</form:select>	<br/>										
										<span class="error" id="orgTypeError"></span>							
										<form:errors  path="orgType" htmlEscape="true" cssClass="mandatory"/>	
										<div id="err_orgType" class="mandatory label"></div>		
								  </div>
						</div>   
						
						   <div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.ORGLIST"/><span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								   <form:select path="olc" id="olc" class="form-control" >
											<form:option value="">																
												<spring:message code="Label.SELECT" htmlEscape="true"/>
											</form:option>
										</form:select><br/>										
										<span class="error" id="olcError"></span>							
										<form:errors  path="olc" htmlEscape="true" cssClass="mandatory"/>	
										<div id="err_olc" class="mandatory label"></div>				
								  </div>
						</div>
						
						<div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.ORGLEVEL"/><span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								   <form:select path="orgLocatedLevelCode" id="orgLocatedLevelCode" class="form-control" >
											<form:option value="">																
												<spring:message code="Label.SELECT" htmlEscape="true"/>
											</form:option>
										</form:select><br/>											
										<span class="error" id="orgLocatedLevelCodeError"></span>							
										<form:errors  path="orgLocatedLevelCode" htmlEscape="true" class="mandatory"/>
										<div id="err_orgLocatedLevelCode" class="mandatory label"></div>				
								  </div>
						</div>
						
						<div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.NewOrgLevelName" text="New Level specific Name of the Organisation/Department in English"></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								   <form:input path="orgLevelSpecificName" id="orgLevelSpecificName" maxlength="200" htmlEscape="true" class="form-control"/>
											<span class="error" id="orgLevelSpecificNameError"></span>
										<form:errors htmlEscape="true" path="orgLevelSpecificName" cssClass="error"/>
										<div id="err_orgLevelSpecificName" class="mandatory label"></div>			
								 </div>
						</div>
						
							<!-- Government Order Details Block Start -->
						
						<div class="box-header subheading"><h4>Government Order Details</h4></div>
						
						<div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message></label>
								<div class="col-sm-6" >
								  	<form:input path="orderNo" id="orderNo" maxlength="200" htmlEscape="true" class="form-control"/>
										<span class="error" id="orderNoError"></span>
										<form:errors htmlEscape="true" path="orderNo" cssClass="error"/>
										<div id="err_orderNo" class="mandatory label"></div>		
								 </div>
						</div>
						
						<div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.ORDERDATE"></spring:message></label>
								<div class="col-sm-6" >
								  <div class="input-group date datepicker">
								  		<form:input path="orderDate" id="OrderDate" maxlength="200" htmlEscape="true" class="form-control"/>
								  		<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
								   </div>		
										<br/><span class="error" id="orderDateError"></span>
										<form:errors htmlEscape="true" path="orderDate" cssClass="error"/>
										<div id="err_orderDate" class="mandatory label"></div>	
								 </div>
						</div>
						
						<div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.EFFECTIVEDATE"></spring:message></label>
								<div class="col-sm-6" >
								  <div class="input-group date datepicker">
								  		<form:input path="ordereffectiveDate" id="EffectiveDate" maxlength="200" htmlEscape="true" class="form-control" />
								  		<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
								  </div>
											<br/><span class="error" id="effectiveDateError"></span>
										<form:errors htmlEscape="true" path="ordereffectiveDate" cssClass="error"/>
										<div id="err_effectiveDate" class="mandatory label"></div>
								 </div>
						</div>
						
						<div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.GAZPUBDATE"></spring:message></label>
								<div class="col-sm-6" >
								 <div class="input-group date datepicker">
								  	<form:input path="gazPubDate" id="gazPubDatecr" maxlength="200" htmlEscape="true" class="form-control"/>
								  	<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
								  </div>
										<br/><span class="error" id="gazPubDateError"></span>
										<form:errors htmlEscape="true" path="gazPubDate" cssClass="error"/>
										<div id="err_gazPubDate" class="mandatory label"></div>								
							      </div>
						</div>
						
						<div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message code="Label.UPLOADGOVTORDER" htmlEscape="true" /></label>
								<div class="col-sm-6" >
								<spring:message code='Label.allowedfileexe' htmlEscape='true' />
								  	<form:input path="attachFile" id="attachFile" type="file" />
										<form:errors htmlEscape="true" path="attachFile" cssClass="error" />
										<div id="err_attachFile" class="mandatory label"></div>							
							      </div>
						</div>
						
                 </div> 
             
                     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                   <button class="btn btn-success" id="btnFormActionSave" type="button" ><i class="fa fa-floppy-o"></i> Submit</button>
				   <button class="btn btn-warning" id="btnFormActionClear" type="button"   >Clear</button>
                   <button type="button" class="btn btn-danger " name="Cancel"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div> 
     </div>   
             
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>