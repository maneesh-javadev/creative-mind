<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../common/taglib_includes.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>



 <link href="css/errorLB.css" rel="stylesheet" type="text/css" />

<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>

<!-- <script src="js/trim-jquery.js"></script> 
<link href="css/error.css" rel="stylesheet" type="text/css" />
<link href="css/successMessage.css" rel="stylesheet" type="text/css" /> 
 -->

<script src="js/validation.js"></script>
<script src="js/govtorder.js"></script>
<script src="js/common.js"></script> 
<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.9.1.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>

<link rel="stylesheet" href="datepicker/demos.css" />
Added by Sushil on 17-11-2014 -->
<script type="text/javascript" language="javascript">
	
	$(document).ready(function() {	
		if('${preVersionMaxEffDate}' != null && '${preVersionMaxEffDate}' != ""){
			$('#preVersionEffDate').val('${preVersionMaxEffDate}');
		}
	});
	
	$(function() {

		$("#bOrderDate").datetimepicker({
			format: 'dd-mm-yyyy',
			startView : 'month',
			endDate: '+0d',
	        autoclose: true,
			minView : 'month',
			pickerPosition : "bottom-left",
			EndDate:'0',
		});
		
		<%-- $( "#OrderDate" ).datepicker({
			dateFormat: 'dd-mm-yy',
			value: new Date(<%=currentYear%>, <%=currentMonth%>, <%=currentDate%>),
			changeMonth: true,
			changeYear: true
		});	 --%>
	});
	
	$(function() {
		$("#bEffectiveDate").datetimepicker({
			format: 'dd-mm-yyyy',
			startView : 'month',
			endDate: '+0d',
	        autoclose: true,
			minView : 'month',
			pickerPosition : "bottom-left",
			EndDate:'0',
		});
	
	});
	
	$(function() {
		$("#bGazPubDate").datetimepicker({
			format: 'dd-mm-yyyy',
			startView : 'month',
			endDate: '+0d',
	        autoclose: true,
			minView : 'month',
			pickerPosition : "bottom-left",
			EndDate:'0',
		});
	
	});
	
	<%-- $(function() {
		$( "#OrderDate" ).datepicker({dateFormat: 'dd-mm-yy',
			value: new Date(<%=currentYear%>, <%=currentMonth%>, <%=currentDate%>),
			changeMonth: true,
			changeYear: true
		});	
	});
	
	$(function() {
	$( "#EffectiveDate" ).datepicker({dateFormat: 'dd-mm-yy',
		value: new Date(<%=currentYear%>, <%=currentMonth%>, <%=currentDate%>),
		changeMonth: true,
		changeYear: true
	});
	});
	
	$(function() {
	$( "#GazPubDate" ).datepicker({dateFormat: 'dd-mm-yy',
		value: new Date(<%=currentYear%>, <%=currentMonth%>, <%=currentDate%>),
		changeMonth: true,
		changeYear: true
	}); 
	});--%>
	
</script>

</head>

<body>
<section class="content">
 <div class="row">
     <section class="col-lg-12">
          <div class="box">
 				<div class="box-header with-border">
                     <h3 class="box-title"><spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message></h3>
                 </div>
     <form:form id="uploadGovtOrder" commandName="governmentOrder" onsubmit="cursorwait();" action="publishform.htm" method="POST" enctype="multipart/form-data" class="form-horizontal">
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="publishform.htm"/>" />
				               
                      
                               
                                 
                        <div class="box-body">
                        
				       <div class="form-group">
								<label  class="col-sm-3 control-label" for="OrderNo"><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message> <span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								   <form:input path="orderNo" htmlEscape="true" id="OrderNo" type="text" class="form-control" onblur="vlidateOrderNo('OrderNo','1','60');" onkeypress="return validateaGovtOrderNO(event);" maxlength="60"/>
									<span class="errormsg" id="errOrderNo"></span>
									<form:errors htmlEscape="true" path="orderNo" class="mandatory"/>
								</div>
						</div>  	
                   
             
                   <div class="form-group">
							<label for="OrderDate" class="col-sm-3 control-label"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
							<div class="col-sm-6">
								<div class="input-group date datepicker" id="bOrderDate">
								<form:input path="orderDate" readonly="true" id="OrderDate" type="text" class="form-control"  onchange="setEffectiveDate(this.value);" />
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
								</div>
								<span class="errormsg" id="errOrderDate"></span>
								<form:errors htmlEscape="true" path="orderDate" class="mandatory"/>
							</div>
							
							
							
						</div>
                     
						<div class="form-group">
							<label for="EffectiveDate" class="col-sm-3 control-label"><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message></label>
							<div class="col-sm-6">
								<div class="input-group date datepicker" id="bEffectiveDate">
								 	<form:input id="EffectiveDate" readonly="true" path="effectiveDate" type="text" class="form-control" onblur="vlidateOnblur('EffectiveDate','1','15','m');hideHelp();" onkeyup="hideMessageOnKeyPress('EffectiveDate')" />
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
								</div>
								<span class="errormsg" id="errEffectiveDate"></span>
								<form:errors htmlEscape="true" path="effectiveDate" class="mandatory"/>
							</div>
						</div>
                     
						<%-- <div class="form-group">
								<label for="EffectiveDate" class="col-sm-3 control-label"><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
							 <div class="col-sm-6">		
									  <form:input id="EffectiveDate" readonly="true" path="effectiveDate" type="text" class="form-control" onkeypress="validateNumeric();"
											onfocus="validateOnFocus('EffectiveDate');helpMessage(this,'EffectiveDate_msg');" onblur="vlidateOnblur('EffectiveDate','1','15','m');hideHelp();" onkeyup="hideMessageOnKeyPress('EffectiveDate')" />
										    <div id="EffectiveDate_error" class="error"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"></spring:message></div>
											<div id="EffectiveDate_msg" style="display:none"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"/></div>
											<div class="errormsg" id="EffectiveDate_error1"><form:errors path="effectiveDate" htmlEscape="true"/></div>  
											<div class="errormsg" id="EffectiveDate_error2" style="display: none" ></div>
											<input type="hidden" name="preVersionEffDate" id="preVersionEffDate" />	
							</div>
						</div> --%>
						
						<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
								<div class="form-group">
									<label for="GazPubDate" class="col-sm-3 control-label"><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message> </label>
									 <div class="col-sm-6">
										 <div class="input-group date datepicker" id="bGazPubDate">
								 			 <form:input id="GazPubDate" readonly="true" path="gazPubDate" type="text"  class="form-control"  onkeypress="validateNumeric();"  	onfocus="validateOnFocus('GazPubDate');"
											onblur="vlidateOnblur('GazPubDate','1','15','m');" onkeyup="hideMessageOnKeyPress('GazPubDate')"/>
											<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
										</div>
									
										<span class="errormsg" id="errGazPubDate"></span>
										<form:errors htmlEscape="true" path="gazPubDate" class="mandatory"/>	
									</div>			
								</div>
					   </c:if>
						
                <div class="form-group">
                  <form:hidden path="govtOrderConfig" value="${govtOrderConfig}" id="hdngovtOrderConfig" />
              
                  <%@ include file="../common/attachementcp.jspf"%></td>
                
                </div>
                
                <c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
					<div class="form-group">
					<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELGOT"></spring:message> <span class="mandatory">*</span></label>
					<div class="col-sm-6">
					 <form:select	path="templateList" id="templateList"  class="form-control"  onblur="vlidateOnblur('templateList','1','15','m');"
												onfocus="validateOnFocus('templateList');" onkeyup="hideMessageOnKeyPress('templateList')">
								<form:option value="0"><spring:message htmlEscape="true" code="Label.SELECT" ></spring:message></form:option>
										<form:options items="${templateList}" itemLabel="templateNameEnglish" itemValue="templateCode"></form:options>
								</form:select> 
								</br>
									<span class="errormsg" id="errtemplateList"></span>	
									<form:errors htmlEscape="true" path="templateList" class="mandatory"/>	
							</div>
							</div>
				  </c:if>
                   
              
      </div> 
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                   <input type="submit" class="btn btn-success " id="btnSave" name="Submit"  onclick="return validateGovtOrder1();"  value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" />
                   <input type="button" class="btn btn-danger " name="Cancel" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"/>
                 </div>
           </div>   
       </div> 
        </form:form>  
     </div> 
     
      <div class="modal fade" id="alertbox" tabindex="-1" role="dialog" >
		<div class="modal-dialog" >
		<div class="modal-content">
 				<div class="modal-header">
  				 
   			  	<h4 class="modal-title" id="alertboxTitle"></h4>
 				</div>
 				<div class="modal-body" id="alertboxbody">
       
 				</div>
    			 <div class="modal-footer">
       		  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
     
     			</div>
			</div>
			</div>
		</div>  
             
       </section>
    </div>
</section>
   
   <script src="/LGD/JavaScriptServlet"></script>
</body>
</html>