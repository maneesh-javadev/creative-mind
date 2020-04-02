<%-- <script type="text/javascript" language="javascript" src="<%=contextPath%>/external/jqueryCustom/js/jquery-ui-1.8.7.custom.min.js"></script>
<link href="<%=contextPath%>/external/jqueryCustom/css/jquery-ui-1.8.7.custom.css" rel="stylesheet"  type="text/css" /> --%>

<%--  <script type="text/javascript">
$(document).ready(function() {
$('#OrderDate').datepicker({
	dateFormat: 'dd-mm-yy',
	showOn: 'both',
	"showButtonPanel":  false,
	changeYear : true,
	changeMonth : true,
	buttonImage: "<%=contextPath%>/images/calender.gif",
	buttonImageOnly: true,
}); 
$('#EffectiveDate').datepicker({
	dateFormat: 'dd-mm-yy',
	showOn: 'both',
	changeYear : true,
	changeMonth : true,
	buttonImage: "<%=contextPath%>/images/calender.gif",
	buttonImageOnly: true,
	autoclose: true,
});
$('#GazPubDate').datepicker({
	dateFormat: 'dd-mm-yy',
	showOn: 'both',
	changeYear : true,
	changeMonth : true,
	buttonImage: "<%=contextPath%>/images/calender.gif",
	buttonImageOnly: true,
	autoclose: true,
});

$('#OrderDate').on('change', function(){
$('#ui-datepicker-div').hide();
});  
$('#EffectiveDate').on('change', function(){
$('#ui-datepicker-div').hide();

}); 
$('#GazPubDate').on('change', function(){
$('#ui-datepicker-div').hide();

});  
});
</script> --%>
<div>

			<div class="form-group">								
				<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message> <span class="errormsg">*</span></label>
				<div class="col-sm-6">
					  <form:input path="orderNo" htmlEscape="true" class="form-control" id="OrderNo" type="text" maxLength="60" onfocus="helpMessage(this,'OrderNo_error');" onblur="vlidateOrderNo('OrderNo','1','60');hideHelp();" onkeypress="return validateaGovtOrderNO(event);" /> 											
					<div id="OrderNo_error" class="errormsg"><spring:message code="error.required.ORDERNUM" htmlEscape="true"></spring:message></div>
					<div id="OrderNo_msg" class="errormsg"><spring:message code="error.required.ORDERINPUTTYPE" text="Please Enter AlphaNumerics Space . / - ( ) Only" htmlEscape="true" /></div>
					<div class="errormsg" id="OrderNo_error1"><form:errors path="orderNo" htmlEscape="true" /></div>
					<div class="errormsg" id="OrderNo_error2"></div>
				</div>
			</div>
			<div class="form-group">	
				<label class="col-sm-3 control-label"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message><span class="errormsg">*</span></label>
				
				
				<div class="col-sm-6">
				
				
				<div class="input-group date datepicker">
								 	  <form:input path="orderDate" readonly="true" id="OrderDate" type="text" class="form-control" onchange="setEffectiveDate(this.value);" onkeypress="validateNumeric();" onfocus="validateOnFocus('OrderDate');helpMessage(this,'OrderDate_msg');" onblur="vlidateOnblur('OrderDate','1','15','m');hideHelp();" onkeyup="hideMessageOnKeyPress('OrderDate')" /> 
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
								</div>
								
					
					<div id="OrderDate_error" class="errormsg"><spring:message code="error.required.ORDERDATE" htmlEscape="true"></spring:message></div>
					<div id="OrderDate_msg" class="errormsg"><spring:message code="error.required.ORDERDATE" htmlEscape="true" /></div>
					<div class="errormsg" id="OrderDate_error1"><form:errors path="orderDate" htmlEscape="true" /></div>
					<div class="errormsg" id="OrderDate_error2"></div>
				</div>
			</div>
			<div class="form-group">	
				<label class="col-sm-3 control-label"><spring:message	code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message>
				<span class="errormsg">*</span></label>
				<div class="col-sm-6">
				
				<div class="input-group date datepicker">
								 	  <form:input id="EffectiveDate" readonly="true" class="form-control" path="effectiveDate" type="text" onkeypress="validateNumeric();" onfocus="validateOnFocus('EffectiveDate');helpMessage(this,'EffectiveDate_msg');" onblur="vlidateOnblur('EffectiveDate','1','15','m');hideHelp();" onkeyup="hideMessageOnKeyPress('EffectiveDate')" /> 
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
								</div>
								
								
				
					<div id="EffectiveDate_error" class="errormsg"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"></spring:message></div>
					<div id="EffectiveDate_msg" class="errormsg"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true" /></div>
					<div class="errormsg" id="EffectiveDate_error1"><form:errors path="effectiveDate" htmlEscape="true" /></div>
					<div class="errormsg" id="EffectiveDate_error2" style="display: none"></div>
					</div>
			</div>
			<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
			<div class="form-group">	
						<label class="col-sm-3 control-label"><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>
						</label>
					<div class="col-sm-6">
					
					<div class="input-group date datepicker">
								 	<form:input id="GazPubDate" path="gazPubDate" class="form-control" type="text" onkeypress="validateNumeric();" onfocus="validateOnFocus('GazPubDate');helpMessage(this,'GazPubDate_msg');" onblur="vlidateOnblur('GazPubDate','1','15','m');hideHelp();" onkeyup="hideMessageOnKeyPress('GazPubDate')" /> 
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
								</div>
								
								
                         
						<div id="GazPubDate_error" class="errormsg"><spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true"></spring:message></div>
						<div id="GazPubDate_msg" class="errormsg"><spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true" /></div>
						<div class="errormsg" id="GazPubDate_error1"><form:errors path="gazPubDate" htmlEscape="true" /></div>
						<div class="errormsg" id="GazPubDate_error2"style="display: none"></div>	
					</div>								
			</div>
			</c:if>
			<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
				<div id="divGazettePublicationUpload">
						<%@ include file="../common/attachmentgovtcp.jspf"%>
				</div>
			</c:if>
			<c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
				<div class="form-group">										
						<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELGOT"></spring:message> <span class="errormsg">*</span></label> 
						<div class="col-sm-6">
						<form:select path="templateList" class="form-control"  id="templateList" onblur="vlidateOnblur('templateList','1','15','m');hideHelp();" onfocus="validateOnFocus('templateList');helpMessage(this,'templateList_msg');" onkeyup="hideMessageOnKeyPress('templateList')">
							<form:option value="0"><spring:message htmlEscape="true" code="Error.templateselect"></spring:message></form:option>
							<form:options items="${templateList}" itemLabel="templateNameEnglish" itemValue="templateCode"></form:options>
						</form:select> 
						<div id="templateList_error" class="errormsg"><spring:message code="error.blank.template" htmlEscape="true"></spring:message></div>
						<div id="templateList_msg" class="errormsg"><spring:message code="error.blank.template" htmlEscape="true" /></div>
						<div class="errormsg" id="templateList_error1"><form:errors path="templateList" htmlEscape="true" /></div>
						<div class="errormsg" id="templateList_error2" style="display: none"></div>
				  </div>
				</div>
			</c:if>														
	
</div>