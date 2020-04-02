<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<% String contextPath = request.getContextPath();%>

<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="draftExistingGovernmentOrderJs.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/dwr/interface/dwrRestructuredLocalBodyService.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources-localbody/scripts/jquery.fileDownload.js"></script>
<%-- <script type="text/javascript" language="javascript" src="<%=contextPath%>/ckeditor/ckeditor.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/ckeditor/ckeditor_components.js"></script> --%>
<c:set var="isPublish" value="<%=in.nic.pes.lgd.draft.constant.DraftConstant.FORM_ACTION_PUBLISH.toString()%>"></c:set>
<c:set var="isDraft" value="<%=in.nic.pes.lgd.draft.constant.DraftConstant.DISTRICT_NAME_ENGLISH.toString()%>"></c:set>
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
	
	
	$("#btnFormActionSaveDraft").click(function() {
		
		<c:if test="${isGovernmentOrderUpload}">
		if(!$_checkEmptyObject($("#orderCode").val())){
				$( "#gazettePublicationUpload" ).rules( "remove" );
			$( "#gazettePublicationUpload" ).removeClass( "error" );
			}
		</c:if>
		validateForm();
		/* var error=validateGovForm();
		alert(error);
		if(error=false){
			$('#invalidateSubDistDiv').modal('show');
		}
		 $("#formAction").val("Draft");
			 */
		
		//callActionUrl('buildDraftSubdistrict.htm');
	 });
	
	
	
	
	
			
	
	$("#btnFormActionPublish").click(function() {
		var error=validateGovForm();
	
		if(error==false){
			 $("#formAction").val("Publish");
			if(isIvalidateSubdistrict){
				
				$('#invalidateSubDistDiv').modal('show');
				
			}else{
				
				 $( "#btnFormActionSaveDraft" ).prop( "disabled", true );
				 $( "#btnFormActionPublish" ).prop( "disabled", true );
				 $( "#btnActionClose" ).prop( "disabled", true );
				callActionUrl('buildDraftSubdistrict.htm');
			}
			
		}
		
	 });
	
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
    
   
});
 
 

 var validationForm = function (){
		$("#orderNo" ).rules( "add", {
	 		required : true, 
	 		maxlength: 60,
	 		//regex: "[\#\%&\~\!\@\$\^\*\_\+\`\=\{\}\\[\\]\|\\\\:\;\'\"\<\>\?\]" ,
	 		messages: { 
	 			required: "<spring:message code='ORDERNUMBER.REQUIRED' htmlEscape='true'/>",
	 			maxlength: jQuery.format("<spring:message code='Error.Sizevalid' htmlEscape='true'/>") /* ,
	 			regex    : "<spring:message code='Error.invalidegovordrno' htmlEscape='true'/>" */
	 		}
		});
	 	 $("#formDateOrderDate" ).rules( "add", {
	 		required : true,  
	 		messages: { required: "<spring:message code='error.required.ORDERDATE' htmlEscape='true'/>" }
		});
	 	$("#formDateEffectiveDate" ).rules( "add", {
	 		required : true,  
	 		messages: { required: "<spring:message code='error.required.EFFECTIVEDATE' htmlEscape='true'/>" }
		});
	 	<c:if test="${isGovernmentOrderUpload}">
	 		if($_checkEmptyObject($("#orderCode").val())){
	 			if( isParseJson("${not modifyProcess}") ){
				    $("#gazettePublicationUpload" ).rules( "add", {
				 		required : true, 
				 		fileUploadValidate : true,
				 		messages: { 
				 			required: "<spring:message code='GOVERMENTORDER.REQUIRED' htmlEscape='true'/>",
				 			fileUploadValidate: "<spring:message code='Error.invalidgofile' htmlEscape='true'/>"
				 		}
					});
	 			} else {
	 				$("#gazettePublicationUpload" ).rules( "add", {
		 		 		fileUploadValidate : true,
		 		 		messages: { 
		 		 			fileUploadValidate: "<spring:message code='Error.invalidgofile' htmlEscape='true'/>"
		 		 		}
		 			});
	 			}
			}
	 	</c:if> 
	};

 /**
  * The {@code previousEffectiveDate} used for formatting previous effective date,
  * value must be null in case of government order.
  * @return $.datepicker.formatDate(effective date).
  *
  */
var previousEffectiveDate = function(){
	 if(!$_checkEmptyObject('${draftGovermentOrderForm.iParamEffectiveDate}')){
		 var iEffDateArray = "${draftGovermentOrderForm.iParamEffectiveDate}".split(/-|\s|:/); 
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

/* The {@code $_checkEmptyObject} used to check object / element  
* is empty or undefined.
*/
var $_checkEmptyObject = function(obj) {
	if (jQuery.type(obj) === "undefined" || (obj == null || $.trim(obj).length == 0)) {
		return true;
	}
	return false;
};

callActionUrl=function(url){
 	/* document.forms['sectionForm'].action = "buildSelection.htm?<csrf:token uri='"buildSelection.htm'/>";
	document.forms['sectionForm'].method = "POST";
    document.forms['sectionForm'].submit(); */
   
    $( 'form[id=draftGovermentOrderForm]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
	$( 'form[id=draftGovermentOrderForm]' ).attr('method','post');
	$( 'form[id=draftGovermentOrderForm]' ).submit();
};

</script>



<title><spring:message htmlEscape="true"  code="Title.goverment.order"></spring:message></title>
</head>
<body>
	<!-- Main Form Stylings starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message htmlEscape="true" code="Title.CREATENEWSUBDISTRICT"></spring:message></h3>
			<%-- <ul class="item_list">
				<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li>
			</ul> --%>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form action="buildDraftSubdistrict.htm" method="post" id="draftGovermentOrderForm" commandName="draftGovermentOrderForm" enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="buildDraftSubdistrict.htm"/>" />
				<form:hidden path="formAction"	id="formAction"/>
				<form:hidden path="landRegionType"	/>
				<form:hidden path="landRegionCode"	/>
				<form:hidden path="operationType"	/>
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
		    		<input type="text" id="paramOrderNo"  maxlength="200"/>
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
			<input class="bttn" type="button" id="fetchGODetails" value="Fetch Government Order" />
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
<div class="form_block">
	<div class="col_1">
		<h4><spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message></h4>
		<ul class="form_body">
			<c:if test="${isGovernmentOrderUpload}">
				<li>
					<input type="button" class="bttn" id="actionExistingGO" value="<spring:message code='Label.selectexistinggo' htmlEscape='true'/>"/>
					<input type="button" class="bttn" id="actionNewGO" value="<spring:message code='Label.selectnewgo' htmlEscape='true'/>"/>
				</li>
			</c:if>
			<li>
				<label>
					<spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message>
					<span class="mandate">*</span>
				</label>
				<form:hidden path="orderCode" id="orderCode" htmlEscape="true"/>
				<form:input path="orderNo" id="orderNo" maxlength="60" htmlEscape="true"/>
				<br/>
				<form:errors htmlEscape="true" path="orderNo" cssClass="error"/>
				<span class="errormessage" id="errorderNo"></span>
				<input type="hidden" id="checkNewOrExistGovtOrder"/>
			</li>
			<li>
			   	<label>	
					<spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message>
					<span class="mandate">*</span>											
				</label>
				<form:input path="orderDate" id="formDateOrderDate" readonly="true" />
				<span class="errormessage" id="errformDateOrderDate"></span>
				<br/>
				<form:errors htmlEscape="true" path="orderDate" cssClass="error"/>
			</li>
			<li>
			   	<label>	
					<spring:message	code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> 
					<c:if test="${not empty draftGovermentOrderForm.iParamEffectiveDate}">
						<strong>( <spring:message	code="Label.Previous.Version.Effective.Date" htmlEscape="true"></spring:message> : <fmt:formatDate pattern="dd/MM/yyyy" value="${draftGovermentOrderForm.iParamEffectiveDate}" /> )</strong>
					</c:if>
					<span class="mandate">*</span>									
				</label>
				<form:input path="effectiveDate" id="formDateEffectiveDate" readonly="true" />
				<span class="errormessage" id="errformDateEffectiveDate"></span>
				<form:hidden path="iParamEffectiveDate" htmlEscape="true"/><!-- Previous Version Effective Date -->
				<br/>
				<form:errors htmlEscape="true" path="effectiveDate" cssClass="error"/>
			</li>
			
			<c:choose>
				<c:when test="${isGovernmentOrderUpload}">
					<li>
			   			<label>	
						<spring:message	code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>									
						</label>
						<form:input	 path="gazPubDate" id="formDateGazPubDate" readonly="true"/>
						<br/>
						<form:errors htmlEscape="true" path="gazPubDate" cssClass="error"/>
					</li>
					<li>
						<c:if test="${(empty draftGovermentOrderForm.orderCode) and (modifyProcess or checkedServerValidation)}">
							<c:set var="fileName" value=""/>
							<c:if test="${not empty draftGovermentOrderForm.orderPath}">
								<c:set var="substrng" value="${fn:substring(draftGovermentOrderForm.orderPath, fn:indexOf(draftGovermentOrderForm.orderPath, '_'), fn:indexOf(draftGovermentOrderForm.orderPath, '.'))}" />
								<c:set var="fileName" value="${fn:replace(draftGovermentOrderForm.orderPath, substrng, '')}" />
							</c:if>
							<a id="attachedUploadedFile" href="downloadLBGovernementOrder.htm?filename=${draftGovermentOrderForm.orderPath}&<csrf:token uri='downloadLBGovernementOrder.htm'/>">
								<c:out value="${fileName}"/>
							</a>
							<form:hidden path="orderPath"/>
							<br/><br/>
						</c:if>
						<div id="divGazettePublicationUpload" <c:if test="${not empty draftGovermentOrderForm.orderCode}">style="display: none;"</c:if>>
							<label>	
								<spring:message code="Label.UPLOADGOVTORDER" htmlEscape="true"/>
								<spring:message code='Label.allowedfileexe' htmlEscape='true'/>
								<span class="mandate">*</span>								
							</label>
							<form:input path="gazettePublicationUpload" id="gazettePublicationUpload" type="file"/>
								<c:set var="gazettePublicationUploadHasBindError">
									<form:errors path="gazettePublicationUpload"/>
								</c:set>
								<c:if test="${! empty gazettePublicationUploadHasBindError }">
									<span class="mandatory">${gazettePublicationUploadHasBindError}</span>
										<c:if test="${fn:contains(gazettePublicationUploadHasBindError, 'restricted keyword(s)')}">
											<a onclick="showInfo()" href="#"><i class="fa fa-info-circle fa-2x" aria-hidden="true"></i></a>
										</c:if>
								</c:if>
							<span class="errormessage" id="errgazettePublicationUpload"></span>
							<%-- <form:errors htmlEscape="true" path="gazettePublicationUpload" cssClass="error"/> --%>
						</div>		
					</li>	
				</c:when>
				<c:otherwise>
					<li>
			   			<label>	
						<spring:message htmlEscape="true" code="Label.SELGOT"></spring:message>
						<span class="mandate">*</span>								
						</label>
						<form:select path="templateId" id="templateId">
							<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT"/></form:option>
							<form:options items="${templateList}" itemLabel="templateNameEnglish" itemValue="templateCode"></form:options>
						</form:select>
					</li>	
					<li>
						<div id="divCKEditor" <c:if test="${not (modifyProcess or checkedServerValidation)}">style="display: none;"</c:if>>
							<form:textarea path="editorTemplateDetails" id="editorTemplateDetails" cssClass="ckeditor"/>
						</div>
			   		</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</div>

<!-- Block for Government Order Details Ends Here-->	
				<input class="bttn" id="btnFormActionSaveDraft" type="submit" value="<spring:message htmlEscape="true" code="Button.DRAFT"/>" />
				<input class="bttn" id="btnFormActionPublish" type="button" value="<spring:message htmlEscape="true" code="Button.SP"/>" />
				<input class="bttn" id="btnActionClose" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="callActionUrl('home.htm')"/>
				
				
				<c:if test="${isIvalidateSubdistrict}">
				<form:hidden path="invalidateSubdistrictcodes"/> 
				<div class="modal fade" id="invalidateSubDistDiv" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				        <h4 class="modal-title" id="exampleModalLabel">Choose Subdistrict to Invalidate</h4>
				      </div>
				      <div class="modal-body">
				       			<div class="long_latitude" >
					<div class="row"> <!-- Max of 4 divs can be used inside this row div -->
						<c:forEach  var="obj" varStatus="rowstatus" items="${invaliSubdistList}">
							<div class="col">
								<label>
									<c:out value="${obj.subdistrictNameEnglish}" />
								</label>
							</div>
							<div class="col">
								<label>
									<input type="radio"  id="invalYes${rowstatus.count}" 	value="${obj.subDistrictCode}" onclick="showHideOption();" name="chk${obj.subDistrictCode}"  /> 
									YES
								</label>
							</div>
							<div class="col">
								<label>
									<input type="radio"  id="invalNo${rowstatus.count}" 	value="no" onclick="showHideOption();" checked="checked" name="chk${obj.subDistrictCode}" />
									NO
								</label>
							</div>
							<br/>
						</c:forEach>
						
						<ul>
								<Li>
									
								
								</Li>
						
						
						</ul>
							
													
					</div>
				</div>
				      </div>
				      <div class="modal-footer">
				      <input class="bttn" class="btn btn-default" id="btnInvalidateAction" type="button" value="Invalidate Action" onclick="checkInvalidateSubdistrict()" />
				      
				      </div>
				    </div>
				  </div>
				</div>
				
			
				</c:if>
		</form:form>	
		</div>
	</div>
	<!-- Page Content ends -->
	<!-- </div> -->
			
</div>
<%@include file="commanGovErrorPage.jsp"%>

<!-- Main Form Stylings ends -->
<c:if test="${not empty draftGovermentOrderForm.orderCode}"> 
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
</body>
</html>





