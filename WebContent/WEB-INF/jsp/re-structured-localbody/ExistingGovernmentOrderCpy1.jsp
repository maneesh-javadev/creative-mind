<% String contextPath = request.getContextPath();%>
<script type="text/javascript" src="<%=contextPath%>/dwr/interface/dwrRestructuredLocalBodyService.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources-localbody/scripts/jquery.fileDownload.js"></script>
<%-- <script type="text/javascript" language="javascript" src="<%=contextPath%>/ckeditor/ckeditor.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/ckeditor/ckeditor_components.js"></script> --%>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/external/jqueryCustom/js/jquery-ui-1.8.7.custom.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resource/dashboard-resource/dist/js/date.format.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resource/dashboard-resource/dist/js/date.format.min.js"></script>
<script type="text/javascript" src="http://momentjs.com/downloads/moment.min.js"></script>

 
<style>
	/* This is the style for the trigger icon. The margin-bottom value causes the icon to shift down to center it. */
	.ui-datepicker-trigger {
		margin-left:1px;
		margin-top: 8px;
		margin-bottom: -3px;
	}
	.textHead{color: #3B5998;}
</style>
<script type="text/javascript">

/**
 * The {@code ready} initialized once page started excuting 
 * and invoke all on load events.
 */ 
$(document).ready(function() {
	
	$("#bdateParamFrom").datetimepicker({
		format: 'dd/mm/yyyy',
		startView : 'month',
		endDate: '+0d',
        autoclose: true,
		minView : 'month',
		pickerPosition : "bottom-left",
		EndDate:'0',
	}).on('changeDate', function(selected){
		$('#bdateParamFrom').datetimepicker('setStartDate', (selected.date).format('d/m/Y'));
	
	});
	
	$("#bdateParamTo").datetimepicker({
		format: 'dd/mm/yyyy',
		startView : 'month',
		endDate: '+0d',
        autoclose: true,
		minView : 'month',
		pickerPosition : "bottom-left",
		EndDate:'0',
	}).on('changeDate', function(selected){
		$('#bdateParamFrom').datetimepicker('setStartDate', (selected.date).format('d/m/Y'));
	});


   
	setDefaultDates();
	
	<c:if test="${not isGovernmentOrderUpload}">
		$("#templateId").change(function() {
			CKEDITOR.instances.editorTemplateDetails.insertHtml('');
			$("#divCKEditor").hide();
			var templateCode = $("option:selected", this).val();
			if(!$_checkEmptyObject(templateCode)){
				dwrRestructuredLocalBodyService.getGovernmentOrderTemplate(parseInt(templateCode), {
					callback : populateGovernmentOrderTemplate,
					errorHandler : function (){
						customAlert("<spring:message code='Error.errfetchnggotemplate' htmlEscape='true'/>");
					},
					async : true
				});
			}
		});
	</c:if>
    $('#actionExistingGO').click(function() {
    	$("#checkNewOrExistGovtOrder").val('Y');
		selectExistingGovernmentOrder();
	});
    $('#actionNewGO').click(function() {
    	$("#checkNewOrExistGovtOrder").val('N');
    	$("#orderNo").removeAttr("readonly");
		$("#orderCode, #orderNo").val("");
		$("#formDateOrderDate").val("");
		//$('#bformDateOrderDate').datetimepicker('setStartDate', null);
		//$('#bformDateOrderDate').datetimepicker('setEndDate', '0');
		$("#formDateEffectiveDate").val("");
		//$('#bformDateEffectiveDate').datetimepicker('setStartDate', null);
		//$('#bformDateEffectiveDate').datetimepicker('setEndDate', '0');
		$("#formDateGazPubDate").val("");
		//$('#bformDateGazPubDate').datetimepicker('setStartDate', null);
		//$('#bformDateGazPubDate').datetimepicker('setEndDate', '0');
	    $("#attachedUploadedFile").remove();
		if($.browser.msie){
	    	$("#gazettePublicationUpload").replaceWith($("#gazettePublicationUpload").clone(true));
	    } else {
	    	$("#gazettePublicationUpload").val('');
	    }
		$("#divGazettePublicationUpload").show();
		setDefaultDates();
	});
	$('#fetchGODetails').click(function() {
		return buildGovernmentOrderDetails();
	});
	$('#goClose').click(function() {
		$('#divExistingOrderDeatils').modal('hide');	
	});
});

 /**
  * The {@code previousEffectiveDate} used for formatting previous effective date,
  * value must be null in case of government order.
  * @return $.datepicker.formatDate(effective date).
  *
  */
var previousEffectiveDate = function(){
	 if(!$_checkEmptyObject('${localBodyForm.iParamEffectiveDate}')){
		 var iEffDateArray = "${localBodyForm.iParamEffectiveDate}".split(/-|\s|:/); 
		 return $.datepicker.formatDate( 'dd/mm/yy', new Date(iEffDateArray[0], iEffDateArray[1]-1, iEffDateArray[2]));	 
	 } else return null; 
};

/**
 * The {@code setDefaultDates} set default setting for 
 * government order details.
 *
 */
var setDefaultDates = function (){
	 var _js_previous_ED =  previousEffectiveDate(); // Previous version effective date.
	 //var effectDate = ${date};

	 $("#bformDateOrderDate").datetimepicker({
			format: 'dd/mm/yyyy',
			startView : 'month',
			endDate: _js_previous_ED,
	        autoclose: true,
			minView : 'month',
			pickerPosition : "bottom-left",
			EndDate: _js_previous_ED,
		}).on('changeDate', function(selected){
		//alert((selected.date).format('d/m/Y'));
		var momentDate = moment(_js_previous_ED, 'DD/MM/YYYY').toDate();//moment(_js_previous_ED, 'DD/MM/YYYY').toDate();
			<c:if test="${empty GOV_ORDER_ACTION}">
			if(!$_checkEmptyObject(_js_previous_ED)){
				if(Date.parse(_js_previous_ED ) <Date.parse( selected.date )){
					_js_previous_ED = selected.date;
					$('#formDateEffectiveDate').val((selected.date).format('d/m/Y'));	
				}
			}else{
				$('#formDateEffectiveDate').val((selected.date).format('d/m/Y'));
			}
		</c:if>
		
		if(!$_checkEmptyObject(_js_previous_ED)){
			
		$('#bformDateEffectiveDate').datetimepicker('setStartDate', momentDate );
		}else{
			
		$('#bformDateEffectiveDate').datetimepicker('setStartDate', (selected.date).format('d/m/Y'));
		}
		$('#bformDateGazPubDate').datetimepicker('setStartDate',(selected.date).format('d/m/Y'));
		_js_previous_ED = previousEffectiveDate();

	    });
	 
	 $("#bformDateEffectiveDate").datetimepicker({
			format: 'dd/mm/yyyy',
			startView : 'month',
	        autoclose: true,
			minView : 'month',
			pickerPosition : "bottom-left",
			StartDate:_js_previous_ED,
			EndDate:'0',
		}).on('changeDate', function(selected){
		
			$('#bformDateOrderDate').datetimepicker('setEndDate',(selected.date).format('d/m/Y'));
			
	    });

	 <c:if test="${isGovernmentOrderUpload}">
	 var _js_previous_ED =  previousEffectiveDate(); // Previous version effective date.

		 $("#bformDateGazPubDate").datetimepicker({
			format: 'dd/mm/yyyy',
			startView : 'month',
	        autoclose: true,
			minView : 'month',
			pickerPosition : "bottom-left",
			endDate: _js_previous_ED,

		}).on('changeDate', function(selected){
				var parsedDate = Date.parse( selected.date );
				$('#bformDateOrderDate').datetimepicker('setEndDate', (selected.date).format('d/m/Y'));
				if(!$_checkEmptyObject(_js_previous_ED)){
					if(Date.parse( _js_previous_ED ) < parsedDate){	
						$('#bformDateEffectiveDate').datetimepicker('setEndDate', (selected.date).format('d/m/Y'));
					}
				} else {
				$('#bformDateEffectiveDate').datetimepicker('setEndDate', (selected.date).format('d/m/Y'));
				}
				if( parsedDate > new Date() ){
					//$('#bformDateOrderDate').datetimepicker('setEndDate', '0');
					//$('#bformDateEffectiveDate').datetimepicker('setEndDate', '0');
				}
		});
	</c:if> 
	 
	
}; 
 
/**
 * The {@code replaceTemplateVariables} used to dynamically replace template
 * variables with actual data.
 * @param templateDescription
 * @param array
 * @param isInput
 */ 
var replaceTemplateVariables = function (templateDescription, array, isInput){
	$.map( array, function( value, key ) {
		var elementVal = "";
		if(value != null){
			if(isInput){
				if(jQuery.type( $("#" + value).val() ) != "undefined"){
					elementVal = $("#" + value).val();
				}	
			}else{
				var eleId = null;
				if("localBodyLevelCodes" == value){
					eleId = "[id^=" + value + "]";
				} else {
					eleId = "#" + value.replace("," , ",#");
					if(!$_checkEmptyObject($(eleId).attr('multiple'))){
						$(eleId ).find("option").attr("selected", "selected");	
					}
				}
				elementVal = $(eleId).find('option:selected').map(function () {
			        return $(this).text();
			    }).get().join(',');
			}	
		}
		templateDescription = templateDescription.replace(key, elementVal);
	});
	return templateDescription;
};

/**
 * The {@code getDynamicTemplate} used to create dynamic arrays for 
 * variables mapped with elements objects.
 * @param templateDescription
 */
var getDynamicTemplate = function(templateDescription){
	var commonValriableArray = {
			"{NameoftheNewLocalGovtBody}" : "localBodyNameEnglish",  
			"{AliasNameoftheNewLocalGovtBody}" : "localBodyAliasEnglish",  
			"{OrderNo}" : "orderNo",
			"{OrderDate}" : "formDateOrderDate",
			"{EffectiveDate}" : "formDateEffectiveDate",
			"{PESAActImplemented(Yes/No)}" : "pesaActImpl"
	};	
	templateDescription = replaceTemplateVariables(templateDescription, commonValriableArray, true);
	if(_JS_LOCAL_BODY_CREATION_TYPE == _JS_URBAN_CONSTANT){
		var urbanVarArray = {
				"{LocalGovtType}" : "localBodyType",
				"{ContributingVillages}": null,
				"{ParentofNewLocalGovtBody}" : null
		}
		templateDescription = replaceTemplateVariables(templateDescription, urbanVarArray, false);
		if(_JS_IS_DISRTICT_LEVEL_LOCAL_BODY){
			templateDescription = replaceTemplateVariables(templateDescription, {"{ContributingDistricts}" : "coverageLBContributingDisttLevel,coverageLRContributingUnmappedDisttLevel"}, false);
		}else{
			templateDescription = replaceTemplateVariables(templateDescription, {"{ContributingSubDistricts}" : "coverageLBContributingDisttLevel,coverageLRContributingUnmappedDisttLevel"}, false);
		}
	} else {
		var panchayatVarArray = {
				"{LocalGovtType}" : "localBodyType",
				"{ContributingDistricts}" : "coverageLBContributingDisttLevel,coverageLRContributingUnmappedDisttLevel",
				"{ContributingSubDistricts}" : "coverageLBContributingSubdisttLevel,coverageLRContributingUnmappedIntermediateLevel",
				"{ContributingVillages}": "coverageLBContributingVillageLevel,coverageLRContributingUnmappedVillageLevel",
				"{ParentofNewLocalGovtBody}" : "localBodyLevelCodes"
		}
		templateDescription = replaceTemplateVariables(templateDescription, panchayatVarArray, false);
	}
	return templateDescription;
};
 
/**
 * The {@code populateGovernmentOrderTemplate} used to instanciate 
 * ckeditor template has been assigned to panchayat type.
 * @param lbAttribuetesObj
 */ 
var populateGovernmentOrderTemplate = function(lbAttribuetesObj) {
	if(!$_checkEmptyObject(lbAttribuetesObj.templateSource.templateDescription)){
		var templateDescription = lbAttribuetesObj.templateSource.templateDescription;
				
		CKEDITOR.instances['editorTemplateDetails'].setData(getDynamicTemplate(templateDescription));
		$("#divCKEditor").show();	
	}
}; 
 
/**
 * The {@code selectExistingGovernmentOrder} used to display dialog box 
 * to show Government Order Details.
 */  
 var selectExistingGovernmentOrder = function (){
		/* $( "#divExistingOrderDeatils" ).dialog({
	    	title : "<spring:message code='Label.findexistingGo' htmlEscape='true'/>",
	    	resizable: false,
	      	width:900,
	      	height:700,
	      	modal: true
	    }); */
	    $('#divExistingOrderDeatils').modal('show');
	};

/**
 * The {@code resetAllMessage} used to reset all error messages 
 * starts with 'goErr' element ids.
 */  
var resetAllMessage = function () {
	$('[id^=goErr]').text('');
};

/**
 * The {@code buildGovernmentOrderDetails} used to build local government order details
 * in tabular format to dispaly and option to pick  individual
 * existing order no attach with new entity. 
 * starts with 'goErr' element ids.
 * @throws errorHandler (display error message) 
 */ 
var buildGovernmentOrderDetails = function () {
	resetAllMessage();
	$( '#tblGornmentOrderDetails > tbody').empty();
	
	var paramOrderNo  = $('#paramOrderNo').val();
	var paramFromDate = $('#dateParamFrom').val();
	var paramToDate   = $('#dateParamTo').val();
	if($_checkEmptyObject(paramOrderNo) && $_checkEmptyObject(paramFromDate) && $_checkEmptyObject(paramToDate)){
		$('#goErrOrderNo').text("<spring:message code='ORDERNUMBER.REQUIRED' htmlEscape='true'/>");
		$('#goErrFromDate').text("<spring:message code='error.select.FROMDATE' htmlEscape='true'/>");
		$('#goErrToDate').text("<spring:message code='error.select.TODATE' htmlEscape='true'/>");
		return false;	
	}
	if($_checkEmptyObject(paramOrderNo)){
		if($_checkEmptyObject(paramFromDate)){
			$('#goErrFromDate').text("<spring:message code='error.select.FROMDATE' htmlEscape='true'/>");
			return false;
		}
		if($_checkEmptyObject(paramToDate)){
			$('#goErrToDate').text("<spring:message code='error.select.TODATE' htmlEscape='true'/>");
			return false;
		}
	}	
	//DWR call to fetch Government Order Details 
	dwrRestructuredLocalBodyService.fetchExistingGovernmentOrder(paramOrderNo, paramFromDate , paramToDate, parseInt( '${stateCode}' ), {
		callback : function(result){
			$( '#tblGornmentOrderDetails' ).show();
			var templateTBODY = $("<tbody/>");
			jQuery.each(result, function(index, obj) {
				var templateTR = $("<tr/>");
				var templateTDOrderCode = $("<td/>");
				var templateRadio = $( "<input />" );
				templateRadio.attr("type", "radio");
				templateRadio.attr("name", "orderCode");
				$( templateRadio ).val(obj.orderCode);
				$( templateTDOrderCode ).append(templateRadio);
				$( templateRadio ).click(function() {
					setGovernmentOrderDetailsToForm(obj.orderCode,
													obj.orderNo,
													$.datepicker.formatDate('dd/mm/yy', obj.orderDate),
													$.datepicker.formatDate('dd/mm/yy', obj.effectiveDate),
													$.datepicker.formatDate('dd/mm/yy', obj.gazPubDate));
					$('#divExistingOrderDeatils').modal('hide');							
				});
				var templateTDOrderNo 		= $("<td/>");
				var templateTDOrderDate 	= $("<td/>");
				var templateTDEffectiveDate = $("<td/>");
				var templateTDGazPubDate 	= $("<td/>");
				var templateTDOrderPath 	= $("<td/>");
				
				$( templateTDOrderNo ).html(obj.orderNo);
				$( templateTDOrderDate ).text($.datepicker.formatDate('dd/mm/yy', obj.orderDate));
				$( templateTDEffectiveDate ).text($.datepicker.formatDate('dd/mm/yy', obj.effectiveDate));
				$( templateTDGazPubDate ).text($.datepicker.formatDate('dd/mm/yy', obj.gazPubDate));
				if(!$_checkEmptyObject(obj.orderPath)){
					$( templateTDOrderPath ).html("<a href='downloadLBGovernementOrder.htm?filename=" + obj.fileTimestamp + "&<csrf:token uri='downloadLBGovernementOrder.htm'/>'>" + obj.fileName + " </a>");
				}
				$( templateTR ).append( templateTDOrderCode ).append( templateTDOrderNo ).append( templateTDOrderDate );
				$( templateTR ).append( templateTDEffectiveDate ).append( templateTDGazPubDate ).append( templateTDOrderPath );
				$( templateTBODY ).append( templateTR );
			});
			$( '#tblGornmentOrderDetails' ).append( templateTBODY );
		},
		errorHandler : function (){
			customAlert("<spring:message code='Error.invalidrequest' htmlEscape='true'/>");
		},
		async : true
	});
};

/**
 * The {@code setGovernmentOrderDetailsToForm} used to set all attributes of government order
 * details to corresponding display pages.
 * @param orderCode 
 * @param orderNo 
 * @param orderDate 
 * @param effectiveDate 
 * @param gazPubDate
 */ 
var setGovernmentOrderDetailsToForm = function (orderCode, orderNo, orderDate, effectiveDate, gazPubDate) {
	$("#orderCode").val(orderCode).attr("readonly", "readonly");
	$("#orderNo").val(orderNo).attr("readonly", "readonly");
	$("#formDateOrderDate").val(orderDate).attr("readonly", "readonly");
	$("#formDateEffectiveDate").val(effectiveDate).attr("readonly", "readonly");
	$("#bformDateOrderDate").datepicker('remove');
	$("#bformDateEffectiveDate").datepicker('remove');
	
	<c:if test="${isGovernmentOrderUpload}">
		$("#formDateGazPubDate").val(gazPubDate).attr("readonly", "readonly");
		$("#bformDateGazPubDate").datepicker('remove');
		
		$("#attachedUploadedFile").remove();
		if($.browser.msie){
	    	$("#gazettePublicationUpload").replaceWith($("#gazettePublicationUpload").clone(true));
	    } else {
	    	$("#gazettePublicationUpload").val('');
	    }
		$("#divGazettePublicationUpload").hide();
	</c:if>
};
//-->
</script>

<!-- Select From Existing Local Government Order Started -->

<div class="modal fade" id="divExistingOrderDeatils" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Existing Local Government Order</h4>
        </div>
        <div class="modal-body">
                <div class="box-header subheading">
                    <h4 >Search By Government Order No</h4>
                </div>	
                <div class="form-group">
					<label class="col-sm-3 control-label"> <spring:message code='Label.GOvordernumber' htmlEscape='true'/> </label>
					<div class="col-sm-6">
		    		     <input type="text" id="paramOrderNo"  maxlength="200" class="form-control"/>
							    		<span class="errormessage" id="goErrOrderNo"></span>
		    		</div>
				</div>
				<div class="box-header subheading">
                    <h4 >Search By Government Order Date</h4>
                </div>	
                <div class="form-group">
					<label class="col-sm-3 control-label"> <spring:message code='Label.FROMDATE' htmlEscape='true'/>  </label>
					<div class="col-sm-6">
		    		   <div class="input-group date datepicker" id="bdateParamFrom">
			                        		<input type="text" id="dateParamFrom" readonly="readonly" class="form-control"/>
			                      <span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
							  </div> 
							    		<span class="errormessage" id="goErrFromDate"></span>
		    		</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label"> <spring:message code='Label.TODATE' htmlEscape='true'/> </label>
					<div class="col-sm-6">
		    		      <div class="input-group date datepicker" id="bdateParamTo">
			          <input type="text" id="dateParamTo" readonly="readonly" class="form-control"/><span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
						  </div>
						 <span class="errormessage" id="goErrToDate"></span>
		    		</div>
				</div>
				
				
	   	<table id="tblGornmentOrderDetails" class="table table-bordered table-hover" cellspacing="0" width="100%" style="display:none">
												<thead>
													<tr>
														<th><!-- Header for Radio Button --></th>
														<th><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message></th>
														<th><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message></th>
														<th><spring:message	code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message></th>
														<th><spring:message	code="Label.GAZPUBDATE" htmlEscape="true"></spring:message></th>
														<th><spring:message code='Label.UGO' htmlEscape='true'/></th>
													</tr>
												</thead>
											</table>
	   
        </div>
        <div class="modal-footer">
          <button  class="btn btn-info" type="button" id="fetchGODetails" value="" > Fetch Gornment Order</button>
          <button type="button" class="btn btn-default" id="goClose" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
</div>
<!-- Select From Existing Local Government Order Ends -->

<!-- Block for Government Order Details Started -->	
	<div class="box-header subheading">
        <h4><spring:message htmlEscape="true" code="Label.GOVTORDERDETAILS"></spring:message></h4>
    </div>
    <div class="form-group" style="display:none;">
	    <div class="col-sm-6">
	    	<c:if test="${isGovernmentOrderUpload}">
				<button type="button" class="btn btn-primary" id="actionExistingGO"><spring:message code='Label.selectexistinggo' htmlEscape='true'/></button>
				<button type="button" class="btn btn-primary" id="actionNewGO"><spring:message code='Label.selectnewgo' htmlEscape='true'/></button>
			</c:if>
	    </div>
    </div>
    <div class="form-group">
    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message><span class="mandatory">*</span></label>
      	<div class="col-sm-6">  
      	     <form:hidden path="orderCode" id="orderCode" htmlEscape="true"/>
			<form:input path="orderNo" id="orderNo" maxlength="60" htmlEscape="true" class="form-control"/>
			<br/>
			<form:errors htmlEscape="true" path="orderNo" cssClass="error"/>
			<div id="OrderNo_error" style="color: red;"></div>
			
			<input type="hidden" id="checkNewOrExistGovtOrder"/>
      	</div>
    </div>
	<div class="form-group">
		<label class="col-sm-3 control-label"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
      	<div class="col-sm-6">
      	
      		<div class="input-group date datepicker" id="bformDateOrderDate">
      			<form:input path="orderDate" id="formDateOrderDate" readonly="true" class="form-control"/>
      			<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
      						<div id="OrderDateBlank_error" style="color: red;"></div>
      			
      			
      		</div>
			
			<form:errors htmlEscape="true" path="orderDate" cssClass="error"/>
			<span class="errormessage" id="errformDateOrderDate"></span>
			
      	</div>
    </div><br/>
    <div class="form-group">
    	<label class="col-sm-3 control-label"><spring:message	code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> 
					      <span class="mandatory"> *</span></label>
      	<div class="col-sm-6">

      	 <!-- <div class="input-group date datepicker" id="bformDateEffectiveDate">
      		<form:input path="effectiveDate" id="formDateEffectiveDate" readonly="readonly" class="form-control"/>
      		<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
      	</div> -->
      	<div class="input-group date datepicker">
						<input readonly="readonly"  class="form-control" value="${date}" /> 
							      		<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
					</div>
			<form:hidden path="iParamEffectiveDate" htmlEscape="true"/><!-- Previous Version Effective Date -->
			<br/>
			<span class="errormessage" id="errformDateEffectiveDate"></span>
      	</div>
    </div>
   		<div class="form-group">
		    	<label class="col-sm-3 control-label"><spring:message	code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>						
		    	</label>
		      
		      	<div class="col-sm-6">
		      	 <div class="input-group date datepicker" id="bformDateGazPubDate">
		      		<form:input	 path="gazPubDate" id="formDateGazPubDate" readonly="true" class="form-control"/>
		      		<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
		      		</div>
		      					      		  <div id="gazPubDate_error" style="color: red;"></div>
		      		
					<br/>
					<form:errors htmlEscape="true" path="gazPubDate" cssClass="error"/>
		      	</div>
		    </div>
		
				<div class="form-group" id="divGazettePublicationUpload" >
			      	<c:set var="gazettePublicationUploadHasBindError">
							<form:errors path="gazettePublicationUpload"/>
						</c:set>
			      	<label class="col-sm-3 control-label">
			      		<spring:message code="Label.UPLOADGOVTORDER" htmlEscape="true"/>
						<spring:message code='Label.allowedfileexe' htmlEscape='true'/>
						<span class="mandatory">*</span>
			      	</label>
			      	<div class="col-sm-6">
			      		<form:input path="gazettePublicationUpload" id="gazettePublicationUpload" type="file" class="form-control"/>
			      		  <div id="gazettePublicationUpload_error" style="color: red;"></div>
			      		
						
						<c:if test="${! empty gazettePublicationUploadHasBindError }">
						<span class="mandatory">${gazettePublicationUploadHasBindError}</span>
						<%-- <span class="mandatory"><c:out value="${nameHasBindError}" /></span> --%>
						<c:if test="${fn:contains(gazettePublicationUploadHasBindError, 'restricted keyword(s)')}">
							<a onclick="showInfo()" href="#"><i class="fa fa-info-circle fa-2x" aria-hidden="true"></i></a>
						</c:if>
						</c:if>
						<!-- <form:errors htmlEscape="true" path="gazettePublicationUpload" cssClass="mandatory"/> -->
						
						<!-- <span class="errormessage" id="errgazettePublicationUpload"></span> -->
			      	</div>
		    	</div>
		    
		
	
<!-- Restricted Keywords dialog box and script #started -->
<div class="modal fade" id="dialogBXRestKey" tabindex="-1" role="dialog" >
		<div class="modal-dialog" style="width:950px;">
				<div class="modal-content">
	  				<div class="modal-header">
	   				   <button type="button" class="close" data-dismiss="modal">&times;</button>
	    			  	<h2 class="modal-title" ><div class="mandatory"><c:out value="Restricted Keywords" /></div> </h2>
	    			  	
	  				</div>
	  				<div class="modal-body" id="dialogBXbody">
	        		<c:out value="select, javascript:alert, insert, drop table, sleep, XOR, sysdate, union, order by, order+by, version, database,
	        		<script, <script>, <script, onmouseover, <script>alert(, onclick, >script>, <META HTTP-EQUIV, refresh, <META HTTP-EQUIV, alert, onabort, onactivate, 
              onafterprint, onafterupdate, onbeforeactivate, onbeforecopy, onbeforecut, onbeforedeactivate, onbeforeeditfocus, onbeforepaste, onbeforeprint, onbeforeunload, 
              onbeforeupdate, onblur, onbounce, oncellchange, onchange, oncontextmenu, oncontrolselect, oncopy, oncut, ondataavailable, ondatasetchanged, ondatasetcomplete, 
              ondblclick, ondeactivate, ondrag, ondragend, ondragenter, ondragleave, ondragover, ondragstart, ondrop, onerror, onerrorupdate, onfilterchange, onfinish, 
              onfocus, onfocusin, onfocusout, onhashchange, onhelp, oninput, onkeydown, onkeypress, onkeyup, onload, onlosecapture, onmessage, onmousedown, onmouseenter, 
              onmouseleave, onmousemove, onmouseout, onmouseup, onmousewheel, onmove, onmoveend, onmovestart, onoffline, ononline, onpaste, onpropertychange, 
              onreadystatechange, onreset, onresize, onresizeend, onresizestart, onrowenter, onrowexit, onrowsdelete, onrowsinserted, onscroll, onsearch, 
              onselect, onselectionchange, onselectstart, onstart, onstop, onsubmit, onunload, %253, onload, %22%3E, <scr<script>ipt>, <s<script>cript>, 
              <sC<script>ript>, <SC<script>ript>, <SC<script>Ript>, <SC<script>RIpt>, <SC<script>RIPt>, <SC<script>RIPT>, <SC<Script>RIPT>, <SC<SCript>RIPT>, 
              <SC<SCRipt>RIPT>, <SC<SCRIpt>RIPT>, <SC<SCRIPt>RIPT>, <SC<SCRIPT>RIPT>, text/javascript, <script type=, confirm, prompt" />
	        			
	     			 
					</div>
					
		</div>
	</div>
</div>
<script lang="javascript">
function showInfo()
{
	$('#dialogBXRestKey').modal('show'); 	
}

</script>
<!-- Restricted Keywords dialog box and script #end -->	
				    
<!-- Block for Government Order Details Ends Here-->	

<c:if test="${not empty localBodyForm.orderCode}"> 
	<script type='text/javascript'>
		$(window).load(function () {
			
			
		}); 
	</script>
</c:if>