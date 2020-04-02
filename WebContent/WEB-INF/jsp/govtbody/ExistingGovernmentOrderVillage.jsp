 <% String contextPath = request.getContextPath();%>
<script type="text/javascript" src="<%=contextPath%>/dwr/interface/dwrRestructuredLocalBodyService.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources-localbody/scripts/jquery.fileDownload.js"></script>
<%-- <script type="text/javascript" language="javascript" src="<%=contextPath%>/ckeditor/ckeditor.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/ckeditor/ckeditor_components.js"></script> --%>
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
		numberOfMonths: 2,
		onSelect: function(selected) {
			$('#dateParamTo').datepicker("option","minDate", selected);
		},
		dateFormat: 'dd-mm-yy',
		showOn: 'both',
		buttonImage: "<%=contextPath%>/images/calender.gif",
		buttonImageOnly: true,
	});
    $('#dateParamTo').datepicker({
        numberOfMonths: 2,
        onSelect: function(selected) {
           $('#dateParamFrom').datepicker("option","maxDate", selected);
        },
        dateFormat: 'dd-mm-yy',
		showOn: 'both',
		buttonImage: "<%=contextPath%>/images/calender.gif",
		buttonImageOnly: true,
    }); 
   
	setDefaultDates();
	
	/* <c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
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
    	
		selectExistingGovernmentOrder();
	});
    $('#actionNewGO').click(function() {
    	$("#checkExistingGovtOrder").val('N');
    	$("#OrderNo").removeAttr("readonly");
		$("#orderCode, #OrderNo").val("");
		$("#OrderDate").val("");
		$("#OrderDate").datepicker( "option", "maxDate", '0' );
		$("#OrderDate").datepicker( "option", "minDate", null );
		$("#EffectiveDate").val("");
		$("#EffectiveDate").datepicker( "option", "maxDate", '0' );
		$("#EffectiveDate").datepicker( "option", "minDate", null );
		$("#GazPubDate").val("");
		$("#GazPubDate").datepicker( "option", "minDate", null );
	    $("#GazPubDate").datepicker( "option", "maxDate", null );
	    $("#elementTable1").append('<tr><td><input type="hidden" id="checkNewOrExist" /></td></tr>');
	    if($.browser.msie){
			$("#attachFile2").replaceWith($("#attachFile2").clone(true));
	    } else {
	    	$("#attachFile2").val('');
	    }
	    if($("#divExistGovtFileUpload")!=null)
	    	$("#divExistGovtFileUpload").hide();
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
 * The {@code setDefaultDates} set default setting for 
 * government order details.
 */
var setDefaultDates = function (){
	$("#OrderDate").datepicker({
		dateFormat: 'dd-mm-yy',
		showOn: 'both',
		maxDate: '0',
		onSelect: function(selected) {
			<c:if test="${empty GOV_ORDER_ACTION}">
				$('#EffectiveDate').val(selected);
			</c:if>
			$('#EffectiveDate').datepicker("option","minDate", selected);
			$("#GazPubDate").datepicker("option","minDate", selected);
		},
		buttonImage: "<%=contextPath%>/images/calender.gif",
		buttonImageOnly: true,
	});
	$("#EffectiveDate").datepicker({
		dateFormat: 'dd-mm-yy',
		showOn: 'both',
		maxDate: '0',
		onSelect: function(selected) {
			$('#OrderDate').datepicker("option","maxDate", selected);
		},
		buttonImage: "<%=contextPath%>/images/calender.gif",
		buttonImageOnly: true,
	});
	<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
		$("#GazPubDate").datepicker({
			dateFormat: 'dd-mm-yy',
			showOn: 'both',
			onSelect: function(selected) {
				$('#OrderDate, #EffectiveDate').datepicker("option","maxDate", selected);
				var parsedDate = $.datepicker.parseDate( 'dd-mm-yy', selected );
				if( parsedDate > new Date() ){
					$('#OrderDate, #EffectiveDate').datepicker("option","maxDate", '0');
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
/* var replaceTemplateVariables = function (templateDescription, array, isInput){
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
}; */

/**
 * The {@code getDynamicTemplate} used to create dynamic arrays for 
 * variables mapped with elements objects.
 * @param templateDescription
 */
/* var getDynamicTemplate = function(templateDescription){
	var commonValriableArray = {
			"{NameoftheNewLocalGovtBody}" : "localBodyNameEnglish",  
			"{AliasNameoftheNewLocalGovtBody}" : "localBodyAliasEnglish",  
			"{OrderNo}" : "orderNo",
			"{OrderDate}" : "OrderDate",
			"{EffectiveDate}" : "EffectiveDate",
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
}; */
 
/**
 * The {@code populateGovernmentOrderTemplate} used to instanciate 
 * ckeditor template has been assigned to panchayat type.
 * @param lbAttribuetesObj
 */ 
/* var populateGovernmentOrderTemplate = function(lbAttribuetesObj) {
	if(!$_checkEmptyObject(lbAttribuetesObj.templateSource.templateDescription)){
		var templateDescription = lbAttribuetesObj.templateSource.templateDescription;
				
		CKEDITOR.instances['editorTemplateDetails'].setData(getDynamicTemplate(templateDescription));
		$("#divCKEditor").show();	
	}
}; */  
 
/**
 * The {@code selectExistingGovernmentOrder} used to display dialog box 
 * to show Government Order Details.
 */  
var selectExistingGovernmentOrder = function (){
	
	 $( "#divExistingOrderDeatils" ).dialog({
    	title : "<spring:message code='Label.findexistingGo' htmlEscape='true'/>",
    	resizable: false,
      	width:900,
      	height:700,
      	modal: true
    });
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
					$("#checkExistingGovtOrder").val('Y');
					setGovernmentOrderDetailsToForm(obj.orderCode,
													obj.orderNo,
													$.datepicker.formatDate('dd-mm-yy', obj.orderDate),
													$.datepicker.formatDate('dd-mm-yy', obj.effectiveDate),
													$.datepicker.formatDate('dd-mm-yy', obj.gazPubDate),
													obj.fileName,
													obj.fileTimestamp);
					$('#divExistingOrderDeatils').dialog('close');							
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
var setGovernmentOrderDetailsToForm = function (orderCode, orderNo, orderDate, effectiveDate, gazPubDate, fileName, fileTimestamp) {
	$("#orderCode").val(orderCode).attr("readonly", "readonly");
	$("#OrderNo").val(orderNo).attr("readonly", "readonly");
	$("#OrderDate").val(orderDate).attr("readonly", "readonly");
	$("#EffectiveDate").val(effectiveDate).attr("readonly", "readonly");
	$("#OrderDate").datepicker( "destroy" );
	$("#EffectiveDate").datepicker( "destroy" );
	<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
		$("#GazPubDate").val(gazPubDate).attr("readonly", "readonly");
		$("#GazPubDate").datepicker( "destroy" );
		$("#checkNewOrExist").remove();
		if($("#checkNewOrExist")!=null)
			$("#checkNewOrExist").remove();
		if($.browser.msie){
	    	$("#attachFile2").replaceWith($("#attachFile2").clone(true));
	    } else {
	    	$("#attachFile2").val('');
	    }
		<%-- Modified by pooja on 19-11-2015 for showing existing uploaded govt. order file --%>
		if ($("#divExistGovtFileUpload") != null) {
			$("#divfileUpload").remove();
			$("#divExistGovtFileUpload")
					.append(
							'<div id="divfileUpload"><label><spring:message code='Label.UGO' htmlEscape='true'/></label>&nbsp;&nbsp;<a href="downloadLBGovernementOrder.htm?filename='+fileTimestamp+'&<csrf:token uri='downloadLBGovernementOrder.htm'/>">'+fileName+'</a></div>');
			$("#divExistGovtFileUpload").show();
		}
		$("#divGazettePublicationUpload").hide();
	</c:if>
	};
	//-->

	var $_checkEmptyObject = function(obj) {
		if (jQuery.type(obj) === "undefined"
				|| (obj == null || $.trim(obj).length == 0)) {
			return true;
		}
		return false;
	};
</script>

<!-- Select From Existing Local Government Order Started -->
<div id="divExistingOrderDeatils" class="form_stylings" style="display: none;">

<!-- Block for Search Options Started-->							
	<div class="form_block">
		<div class="col_1">
			<h4>Search By Government Order No</h4>
			<ul class="form_body">
				<li>
					<label class="inline">
						<spring:message code='Label.GOvordernumber' htmlEscape='true'/>
		    		</label>
		    		<input type="text" id="paramOrderNo" />
		    		<span class="errormessage" id="goErrOrderNo"></span>
				</li>
			</ul>
			
			<h4>Search By Government Order Date</h4>
			<ul class="form_body">
				<li>
					<label class="inline">
						<spring:message code='Label.FROMDATE' htmlEscape='true'/>
		    		</label>
		    		<input type="text" id="dateParamFrom" readonly="readonly"/>
		    		<span class="errormessage" id="goErrFromDate"></span>
				</li>
				<li>
					<label class="inline">
						<spring:message code='Label.TODATE' htmlEscape='true'/>
		    		</label>
		    		<input type="text" id="dateParamTo" readonly="readonly"/>
		    		<span class="errormessage" id="goErrToDate"></span>
				</li>
			</ul>
		</div>
	</div>
<br/>
<!-- Block for Search Options Ends Here-->


<!-- Block for Fetch Buttons and GO Details-->
	<ul class="form_body" >
		<li>
		    <label class="inline">&nbsp;</label>
			<input class="bttn" type="button" id="fetchGODetails" value="Fetch Gornment Order" />
			<input class="bttn" type="button" id="goClose" value="Close" />	
		</li>
	</ul>	
	<br/>
	<table id="tblGornmentOrderDetails" class="data_grid" width="100%" style="display: none;">
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
<!-- Select From Existing Local Government Order Ends -->

<!-- Block for Government Order Details Started -->	

		<!-- <ul class="form_body"> -->
			<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
				<!-- <li> -->
				<div class="form-group" style="margin-left: 10px;"style="margin-right: 10px;display:none;">
					<div class="col-sm-12">
						<input type="button" class="btn " id="actionExistingGO" value="<spring:message code='Label.selectexistinggo' htmlEscape='true'/>"/>
						<input type="button" class="btn " id="actionNewGO" value="<spring:message code='Label.selectnewgo' htmlEscape='true'/>"/>
						<input type="hidden" name="checkExistingGovtOrder" id="checkExistingGovtOrder" value="N"/>
						<form:hidden path="orderCode" id="orderCode" htmlEscape="true"/>
					</div>
				</div>
				<!-- </li> -->
			</c:if>
		<!-- </ul> -->
	

<!-- Block for Government Order Details Ends Here-->	

 <c:if test="${(not empty orderCode) && (orderCode > 0)}"> 
	<script type='text/javascript'>
		$(window).load(function () {
		
			$("#OrderNo").attr("readonly", "readonly");
			$("#OrderDate").datepicker( "destroy" );
			$("#OrderDate").attr("readonly","readonly");
			$("#EffectiveDate").datepicker( "destroy" );
			$("#EffectiveDate").attr("readonly","readonly");
			$("#GazPubDate").datepicker( "destroy" );
			$("#GazPubDate").attr("readonly","readonly");
		}); 
	</script>
</c:if>

