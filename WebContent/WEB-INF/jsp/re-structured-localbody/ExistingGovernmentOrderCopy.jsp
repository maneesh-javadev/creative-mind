<% String contextPath = request.getContextPath();%>
<script type="text/javascript" src="<%=contextPath%>/dwr/interface/dwrRestructuredLocalBodyService.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources-localbody/scripts/jquery.fileDownload.js"></script>
<%-- <script type="text/javascript" language="javascript" src="<%=contextPath%>/ckeditor/ckeditor.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/ckeditor/ckeditor_components.js"></script> --%>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/external/jqueryCustom/js/jquery-ui-1.8.7.custom.min.js"></script> 
<link href="datepicker/calender.css" rel="stylesheet" type="text/css" />

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
<!--
/**
 * The {@code ready} initialized once page started excuting 
 * and invoke all on load events.
 */ 
$(document).ready(function() {
	$('#dateParamFrom').datepicker({
		changeMonth: true,
        changeYear: true,
		numberOfMonths: 1,
		yearRange: "-10:+0",
		maxDate:0,
		onSelect: function(selected) {
			$('#dateParamTo').datepicker("option","minDate", selected);
		},
		dateFormat: 'dd/mm/yy',
		showOn: 'both',
		buttonImage: "<%=contextPath%>/images/calender.gif",
		buttonImageOnly: true,
	});
    $('#dateParamTo').datepicker({
    	changeMonth: true,
        changeYear: true,
		numberOfMonths: 1,
		maxDate:0,
        onSelect: function(selected) {
           $('#dateParamFrom').datepicker("option","maxDate", selected);
        },
        dateFormat: 'dd/mm/yy',
		showOn: 'both',
		buttonImage: "<%=contextPath%>/images/calender.gif",
		buttonImageOnly: true,
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
		var selector = "[id^=formDate]";
		$(selector).val("");
		$(selector).datepicker( "option", "maxDate", '0' );
		$(selector).datepicker( "option", "minDate", null );
	    $("[id=formDateGazPubDate]").datepicker( "option", "maxDate", null );
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
		$('#divExistingOrderDeatils').dialog('close');
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
	 var _js_previous_ED = previousEffectiveDate(); // Previous version effective date.
	 $("#formDateOrderDate").datepicker({
		changeMonth: true,
	    changeYear: true, 
		dateFormat: 'dd/mm/yy',
		showOn: 'both',
		maxDate: '0',
		onSelect: function(selected) {
			<c:if test="${empty GOV_ORDER_ACTION}">
				if(!$_checkEmptyObject(_js_previous_ED)){
					if($.datepicker.parseDate( 'dd/mm/yy', _js_previous_ED ) < $.datepicker.parseDate( 'dd/mm/yy', selected )){
						_js_previous_ED = selected;
						$('#formDateEffectiveDate').val(selected);	
					}
				}else{
					$('#formDateEffectiveDate').val(selected);
				}
			</c:if>
			if(!$_checkEmptyObject(_js_previous_ED)){
				$('#formDateEffectiveDate').datepicker("option","minDate", _js_previous_ED);
			}else{
				$('#formDateEffectiveDate').datepicker("option","minDate", selected);
			}
			$("#formDateGazPubDate").datepicker("option","minDate", selected);
			_js_previous_ED = previousEffectiveDate();
		},
		buttonImage: "<%=contextPath%>/images/calender.gif",
		buttonImageOnly: true,
	});
	
	$("#formDateEffectiveDate").datepicker({
		changeMonth: true,
        changeYear: true,
		dateFormat: 'dd/mm/yy',
		showOn: 'both',
		maxDate: '0',
		minDate: _js_previous_ED,
		onSelect: function(selected) {
			$('#formDateOrderDate').datepicker("option","maxDate", selected);
		},
		buttonImage: "<%=contextPath%>/images/calender.gif",
		buttonImageOnly: true,
	});
	<c:if test="${isGovernmentOrderUpload}">
		$("#formDateGazPubDate").datepicker({
			changeMonth: true,
	        changeYear: true,
			dateFormat: 'dd/mm/yy',
			showOn: 'both',
			onSelect: function(selected) {
				var parsedDate = $.datepicker.parseDate( 'dd/mm/yy', selected );
				$('#formDateOrderDate').datepicker("option","maxDate", selected);
				if(!$_checkEmptyObject(_js_previous_ED)){
					if($.datepicker.parseDate( 'dd/mm/yy', _js_previous_ED ) < parsedDate){
						$('#formDateEffectiveDate').datepicker("option","maxDate", selected);	
					}
				} else {
					$('#formDateEffectiveDate').datepicker("option","maxDate", selected);
				}
				if( parsedDate > new Date() ){
					$('#formDateOrderDate, #formDateEffectiveDate').datepicker("option","maxDate", '0');
				}
			},
			buttonImage: "<%=contextPath%>/images/calender.gif",
			buttonImageOnly: true,
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
	 
	 $( "#divExistingOrderDeatils" ).css("display", "block");
	 
	 
	/* $( "#divExistingOrderDeatils" ).dialog({
    	title : "<spring:message code='Label.findexistingGo' htmlEscape='true'/>",
    	resizable: false,
      	width:900,
      	height:700,
      	modal: true
    }); */
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
					$('#divExistingOrderDeatils').dialog('close');							
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
	$("#formDateOrderDate").datepicker( "destroy" );
	$("#formDateEffectiveDate").datepicker( "destroy" );
	<c:if test="${isGovernmentOrderUpload}">
		$("#formDateGazPubDate").val(gazPubDate).attr("readonly", "readonly");
		$("#formDateGazPubDate").datepicker( "destroy" );
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
	<div class="modal fade" id="myModal">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Search By Government Order No</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <div class="box-header subheading">
				<h4>Search By Government Order No</h4>
		</div>
	
		<div class="form-group">
			<label for="paramOrderNo" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.GOvordernumber"></spring:message></label>
			<div class="col-sm-6">
				<input type="text" id="paramOrderNo" class="form-control"/>
	    		<span class="errormsg" id="goErrOrderNo"></span>
			</div>
		</div>
	
		<div class="box-header subheading">
				<h4>Search By Government Order Date</h4>
		</div>
	
		<div class="form-group">
			<label for="dateParamFrom" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.FROMDATE"></spring:message></label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="dateParamFrom" readonly="readonly"/>
	    		<span class="errormsg" id="goErrFromDate"></span>
			</div>
		</div>
		
		<div class="form-group">
			<label for="dateParamTo" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.TODATE"></spring:message></label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="dateParamTo" readonly="readonly"/>
	    		<span class="errormsg" id="goErrToDate"></span>
			</div>
		</div>
	      </div>
	      <div class="modal-footer">
	        <div class="col-sm-offset-2 col-sm-10">
            	<div class="pull-right">
            	<button class="btn btn-primary" type="button" id="fetchGODetails" >Fetch Gornment Order</button>
				<button class="btn btn-dengar" type="button" data-dismiss="modal" id="goClose">Close</button>	
            	</div>
            </div>
	      </div>
	    </div>
	  </div>
</div>
	
<!-- Select From Existing Local Government Order Ends -->

<!-- Block for Government Order Details Started -->	
	<div class="box-header subheading">
		<h4><spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message></h4>
	</div>
	<c:if test="${isGovernmentOrderUpload}">
	  		<div class="form-group">
		  		<div class="col-sm-6">
		  		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" id="actionExistingGO"><spring:message code='Label.selectexistinggo' htmlEscape='true'/></button>
				<button type="button" class="btn btn-primary" id="actionNewGO"><spring:message code='Label.selectnewgo' htmlEscape='true'/></button>
				</div>
			</div>
	</c:if>
	
	
	<div class="form-group">
		<label for="orderNo" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message><span class="mandatory">*</span></label>
		<div class="col-sm-6">
			<form:hidden path="orderCode" id="orderCode" htmlEscape="true"/>
				<form:input path="orderNo" id="orderNo" class="form-control" maxlength="60" htmlEscape="true"/>
				<br/>
				<form:errors htmlEscape="true" path="orderNo" cssClass="errormsg"/>
				<span class="errormsg" id="errorderNo"></span>
				<input type="hidden" id="checkNewOrExistGovtOrder"/> 
		</div>
	</div>
	
	<div class="form-group">
		<label for="formDateOrderDate" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.ORDERDATE"></spring:message><span class="mandatory">*</span></label>
		<div class="col-sm-6">
			<form:input path="orderDate" id="formDateOrderDate" class="form-control" readonly="true" />
			<br/>
			<form:errors htmlEscape="true" path="orderDate" cssClass="errormsg"/>
			<span class="errormsg" id="errformDateOrderDate"></span> 
		</div>
	</div>
	
	<div class="form-group">
		<label for="formDateEffectiveDate" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.EFFECTIVEDATE"></spring:message>
		<c:if test="${not empty localBodyForm.iParamEffectiveDate}">
						<strong>( <spring:message	code="Label.Previous.Version.Effective.Date" htmlEscape="true"></spring:message> : <fmt:formatDate pattern="dd/MM/yyyy" value="${localBodyForm.iParamEffectiveDate}" /> )</strong>
					</c:if>
					<span class="mandatory">*</span>									
				</label>
		<div class="col-sm-6">
			
				<form:input path="effectiveDate" class="form-control" id="formDateEffectiveDate" readonly="true" />
				<form:hidden path="iParamEffectiveDate" htmlEscape="true"/><!-- Previous Version Effective Date -->
				<br/>
				<form:errors htmlEscape="true" path="effectiveDate" cssClass="errormsg"/>
				<span class="errormsg" id="errformDateEffectiveDate"></span>
		</div>
	</div>
	
	
	<c:choose>
		<c:when test="${isGovernmentOrderUpload}">
			<div class="form-group">
				<label for="formDateGazPubDate" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.GAZPUBDATE"></spring:message></label>
				<div class="col-sm-6">
					<form:input	 class="form-control" path="gazPubDate" id="formDateGazPubDate" readonly="true"/>
					<br/>
					<form:errors htmlEscape="true" path="gazPubDate" cssClass="errormsg"/>
				</div>
			</div>
			
			<div class="form-group">
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
				<div id="divGazettePublicationUpload" <c:if test="${not empty localBodyForm.orderCode}">style="display: none;"</c:if>>
					<label for="gazettePublicationUpload" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.UPLOADGOVTORDER"></spring:message>
					<spring:message htmlEscape="true" code="Label.allowedfileexe"></spring:message>
					<span class="mandatory">*</span>
					</label>
					<div class="col-sm-6">
						<form:input class="form-control" path="gazettePublicationUpload" id="gazettePublicationUpload" type="file"/>
						<form:errors htmlEscape="true" path="gazettePublicationUpload" cssClass="errormsg"/>
						<span class="errormsg" id="errgazettePublicationUpload"></span>
					</div>
				</div>
			</div>
	</c:when>
	<c:otherwise>
				<div class="form-group">
					<label for="templateId" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELGOT"></spring:message><span class="mandatory">*</span></label>
					<div class="col-sm-6">
					<form:select path="templateId" id="templateId" class ="form-control">
						<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT"/></form:option>
						<form:options items="${templateList}" itemLabel="templateNameEnglish" itemValue="templateCode"></form:options>
					</form:select>
					</div>
				</div>	
				<div id="divCKEditor" <c:if test="${not (modifyProcess or checkedServerValidation)}">style="display: none;"</c:if>>
					<div class="form-group">
						<form:textarea path="editorTemplateDetails" id="editorTemplateDetails" cssClass="ckeditor"/>
					</div>	
				</div>
	</c:otherwise>
</c:choose>
	
	
	
			                   

<!-- Block for Government Order Details Ends Here-->	

<c:if test="${not empty localBodyForm.orderCode}"> 
	<script type='text/javascript'>
		$(window).load(function () {
			$("#checkNewOrExistGovtOrder").val('Y');
			$("#orderNo").attr("readonly", "readonly");
			$("#formDateOrderDate").datepicker( "destroy" );
			$("#formDateEffectiveDate").datepicker( "destroy" );
			$("#formDateGazPubDate").datepicker( "destroy" );
		}); 
	</script>
</c:if>