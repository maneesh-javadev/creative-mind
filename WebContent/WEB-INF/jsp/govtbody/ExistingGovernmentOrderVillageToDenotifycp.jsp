<% String contextPath = request.getContextPath();%>
<script type="text/javascript" src="<%=contextPath%>/dwr/interface/dwrRestructuredLocalBodyService.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources-localbody/scripts/jquery.fileDownload.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/ckeditor/ckeditor.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/ckeditor/ckeditor_components.js"></script>
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
	
	/* $("#bdateParamFrom").datetimepicker({
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
 */

   
	setDefaultDates();
	
	/* <c:if test="${not isGovernmentOrderUpload}">
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
	</c:if> */
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
		 return $.datepicker.formatDate( 'dd-mm-yy', new Date(iEffDateArray[0], iEffDateArray[1]-1, iEffDateArray[2]));	 
	 } else return null; 
};

/**
 * The {@code setDefaultDates} set default setting for 
 * government order details.
 *
 */
var setDefaultDates = function (){
	// var _js_previous_ED ='05/03/2018';//  previousEffectiveDate(); // Previous version effective date.
	 
	 $("#bformDateOrderDate").datetimepicker({
			format: 'dd-mm-yyyy',
			startView : 'month',
			endDate: '+0d',
	        autoclose: true,
			minView : 'month',
			pickerPosition : "bottom-left",
			EndDate:'0',
		}).on('changeDate', function(selected){
		//alert((selected.date).format('d/m/Y'));
		//var momentDate = moment(_js_previous_ED, 'DD/MM/YYYY').toDate();//moment(_js_previous_ED, 'DD/MM/YYYY').toDate();
			 <c:if test="${empty GOV_ORDER_ACTION}">
			 /*if(!$_checkEmptyObject(_js_previous_ED)){
				if(Date.parse(_js_previous_ED ) <Date.parse( selected.date )){
					_js_previous_ED = selected.date;
					$('#formDateEffectiveDate').val((selected.date).format('d/m/Y'));	
				}
			}else{ */
				$('#formDateEffectiveDate').val((selected.date).format('d-m-Y'));
			//}
		</c:if>
		
		/* if(!$_checkEmptyObject(_js_previous_ED)){
			
		$('#bformDateEffectiveDate').datetimepicker('setStartDate', momentDate );
		}else{ */
			
		$('#bformDateEffectiveDate').datetimepicker('setStartDate', (selected.date).format('d-m-Y'));
		//}
		$('#bformDateGazPubDate').datetimepicker('setStartDate',(selected.date).format('d-m-Y'));
		//_js_previous_ED = previousEffectiveDate();

	    });
	 
	 $("#bformDateEffectiveDate").datetimepicker({
			format: 'dd-mm-yyyy',
			startView : 'month',
	        autoclose: true,
			minView : 'month',
			pickerPosition : "bottom-left",
			endDate: '+0d',
			EndDate:'0',
		}).on('changeDate', function(selected){
		
			$('#bformDateOrderDate').datetimepicker('setEndDate',(selected.date).format('d-m-Y'));
			
	    });

	 
		 $("#bformDateGazPubDate").datetimepicker({
			format: 'dd-mm-yyyy',
			startView : 'month',
	        autoclose: true,
			minView : 'month',
			endDate: '+0d',
			EndDate:'0',
			pickerPosition : "bottom-left",
		}).on('changeDate', function(selected){
				var parsedDate = Date.parse( selected.date );
				$('#bformDateOrderDate').datetimepicker('setEndDate', (selected.date).format('d-m-Y'));
				/* if(!$_checkEmptyObject(_js_previous_ED)){
					if(Date.parse( _js_previous_ED ) < parsedDate){	
						$('#bformDateEffectiveDate').datetimepicker('setEndDate', (selected.date).format('d/m/Y'));
					}
				} else { */
				$('#bformDateEffectiveDate').datetimepicker('setEndDate', (selected.date).format('d-m-Y'));
				//}
				if( parsedDate > new Date() ){
					//$('#bformDateOrderDate').datetimepicker('setEndDate', '0');
					//$('#bformDateEffectiveDate').datetimepicker('setEndDate', '0');
				}
		});
	
	 
	
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
													$.datepicker.formatDate('dd-mm-yy', obj.orderDate),
													$.datepicker.formatDate('dd-mm-yy', obj.effectiveDate),
													$.datepicker.formatDate('dd-mm-yy', obj.gazPubDate));
					$('#divExistingOrderDeatils').modal('hide');							
				});
				var templateTDOrderNo 		= $("<td/>");
				var templateTDOrderDate 	= $("<td/>");
				var templateTDEffectiveDate = $("<td/>");
				var templateTDGazPubDate 	= $("<td/>");
				var templateTDOrderPath 	= $("<td/>");
				
				$( templateTDOrderNo ).html(obj.orderNo);
				$( templateTDOrderDate ).text($.datepicker.formatDate('dd-mm-yy', obj.orderDate));
				$( templateTDEffectiveDate ).text($.datepicker.formatDate('dd-mm-yy', obj.effectiveDate));
				$( templateTDGazPubDate ).text($.datepicker.formatDate('dd-mm-yy', obj.gazPubDate));
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
							    		<span class="mandatory" id="goErrOrderNo"></span>
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
							    		<span class="mandatory" id="goErrFromDate"></span>
		    		</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label"> <spring:message code='Label.TODATE' htmlEscape='true'/> </label>
					<div class="col-sm-6">
		    		      <div class="input-group date datepicker" id="bdateParamTo">
			          <input type="text" id="dateParamTo" readonly="readonly" class="form-control"/><span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
						  </div>
						 <span class="mandatory" id="goErrToDate"></span>
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
			<form:errors htmlEscape="true" path="orderNo" class="mandatory"/>
			<span class="mandatory" id="errorderNo"></span>
			<input type="hidden" id="checkNewOrExistGovtOrder"/>
      	</div>
    </div>
	<div class="form-group">
		<label class="col-sm-3 control-label"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
      	<div class="col-sm-6">
      	
      		<div class="input-group date datepicker" id="bformDateOrderDate">
      			<form:input path="orderDate" id="formDateOrderDate" readonly="true" class="form-control"/>
      			<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
      		</div>
			
			<form:errors htmlEscape="true" path="orderDate" class="mandatory"/>
			<span class="mandatory" id="errformDateOrderDate"></span>
			
      	</div>
    </div><br/>
    <div class="form-group">
    	<label class="col-sm-3 control-label"><spring:message	code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> 
					      <span class="mandatory"> *</span></label>
      	<div class="col-sm-6">

      	 <div class="input-group date datepicker" id="bformDateEffectiveDate">
      		<form:input path="effectiveDate" id="formDateEffectiveDate" readonly="true" class="form-control"/>
      		<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
      	</div>
      		<%-- <c:if test="${not empty localBodyForm.iParamEffectiveDate}">
						<strong>( <spring:message	code="Label.Previous.Version.Effective.Date" htmlEscape="true"></spring:message> : <fmt:formatDate pattern="dd/MM/yyyy" value="${localBodyForm.iParamEffectiveDate}" /> )</strong>
					</c:if> 
			<form:hidden path="iParamEffectiveDate" htmlEscape="true"/><!-- Previous Version Effective Date --> --%>
			<br/>
			<form:errors htmlEscape="true" path="effectiveDate" class="mandatory"/>
			<span class="mandatory" id="errformDateEffectiveDate"></span>
      	</div>
    </div>
    <%-- <c:choose>
		<c:when test="${isGovernmentOrderUpload}"> --%>
			<div class="form-group">
		    	<label class="col-sm-3 control-label"><spring:message	code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>	</label>
		      	<div class="col-sm-6">
		      	 <div class="input-group date datepicker" id="bformDateGazPubDate">
		      		<form:input	 path="gazPubDate" id="formDateGazPubDate" readonly="true" class="form-control"/>
		      		<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
		      		</div>
					<br/>
					<form:errors htmlEscape="true" path="gazPubDate" class="mandatory"/>
					<span class="mandatory" id="errgazPubDate"></span>
		      	</div>
		    </div>
		    <c:if test="${(empty localBodyForm.orderCode) and (modifyProcess or checkedServerValidation)}">
		    	<c:set var="fileName" value=""/>
				<c:if test="${not empty localBodyForm.orderPath}">
					<c:set var="substrng" value="${fn:substring(localBodyForm.orderPath, fn:indexOf(localBodyForm.orderPath, '_'), fn:indexOf(localBodyForm.orderPath, '.'))}" />
					<c:set var="fileName" value="${fn:replace(localBodyForm.orderPath, substrng, '')}" />
				</c:if>
				<a id="attachedUploadedFile" href="downloadLBGovernementOrder.htm?filename=${localBodyForm.orderPath}&<csrf:token uri='downloadLBGovernementOrder.htm'/>">
					<c:out value="${fileName}"/>
				</a>
				<form:hidden path="orderPath"/>
				<br/><br/>
				</c:if>
				<div class="form-group" id="divGazettePublicationUpload" <c:if test="${not empty localBodyForm.orderCode}">style="display: none;"</c:if>>
			      	<label class="col-sm-3 control-label">
			      		<spring:message code="Label.UPLOADGOVTORDER" htmlEscape="true"/>
						<spring:message code='Label.allowedfileexe' htmlEscape='true'/>
						<span class="mandatory">*</span>	
			      	</label>
			      	<div class="col-sm-6">
			      		<form:input path="attachFile2" id="gazettePublicationUpload" type="file" class="form-control"/>
						<form:errors htmlEscape="true" path="attachFile2" class="mandatory"/>
						<span class="mandatory" id="errgazettePublicationUpload"></span>
			      	</div>
		    	</div>
		    
		<%-- </c:when>
		<c:otherwise>
			<div class="form-group">
		      	<label class="col-sm-3 control-label">
		      		<spring:message htmlEscape="true" code="Label.SELGOT"></spring:message>
					<span class="mandatory">*</span>	
		      	</label>
		      	<div class="col-sm-6">
		      		<form:select path="templateId" id="templateId" class="form-control">
						<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT"/></form:option>
						<form:options items="${templateList}" itemLabel="templateNameEnglish" itemValue="templateCode"></form:options>
					</form:select>
		      	</div>
	    	</div>
	    	<div class="form-group" id="divCKEditor" <c:if test="${not (modifyProcess or checkedServerValidation)}">style="display: none;"</c:if>>
		      	<div class="col-sm-12">
		      		<form:textarea path="editorTemplateDetails" id="editorTemplateDetails" cssClass="ckeditor" class="form-control"/>
		      	</div>
	    	</div>
		</c:otherwise>
	</c:choose> --%>
	
				    
<!-- Block for Government Order Details Ends Here-->	

<c:if test="${not empty localBodyForm.orderCode}"> 
	<script type='text/javascript'>
		$(window).load(function () {
			$("#checkNewOrExistGovtOrder").val('Y');
			$("#orderNo").attr("readonly", "readonly");
			
			$("#bformDateOrderDate").datepicker('remove');
			$("#bformDateEffectiveDate").datepicker('remove');
			$("#bformDateGazPubDate").datepicker('remove');
			
		}); 
	</script>
</c:if>