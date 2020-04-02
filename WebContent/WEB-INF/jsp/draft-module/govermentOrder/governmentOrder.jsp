<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<% String contextPath = request.getContextPath();%>

<%@include file="../../common/taglib_includes.jsp"%>
<%-- <%@include file="draftExistingGovernmentOrderJs.jsp"%> --%>
<script type="text/javascript" src="<%=contextPath%>/dwr/interface/dwrRestructuredLocalBodyService.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources-localbody/scripts/jquery.fileDownload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resource/dashboard-resource/dist/js/date.format.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resource/dashboard-resource/dist/js/date.format.min.js"></script>
<script type="text/javascript" src="http://momentjs.com/downloads/moment.min.js"></script>

<c:set var="isPublish" value="<%=in.nic.pes.lgd.draft.constant.DraftConstant.FORM_ACTION_PUBLISH.toString()%>"></c:set>
<c:set var="isDraft" value="<%=in.nic.pes.lgd.draft.constant.DraftConstant.DISTRICT_NAME_ENGLISH.toString()%>"></c:set>
<!-- <style>
	/* This is the style for the trigger icon. The margin-bottom value causes the icon to shift down to center it. */
	.ui-datepicker-trigger {
		margin-left:1px;
		margin-top: 8px;
		margin-bottom: -3px;
	}
	.textHead{color: #3B5998;}
</style> -->
<script type="text/javascript">
var _inval_list_size=parseInt("<c:out value='${invaliSubdistList.size()}' />");
var isIvalidateSubdistrict=isParseJson("${isIvalidateSubdistrict}");
/**
 * The {@code ready} initialized once page started excuting 
 * and invoke all on load events.
 */ 
$(document).ready(function() {
	//date picker
	

	
	$("#btnFormActionSaveDraft").click(function() {
		<c:if test="${isGovernmentOrderUpload}">
		if(!$_checkEmptyObject($("#orderCode").val())){
				$( "#gazettePublicationUpload" ).rules( "remove" );
			$( "#gazettePublicationUpload" ).removeClass( "error" );
			}
		</c:if>
		/* validateForm(); */
	 var error=validateGovForm();

		if(error=false){
			 $("#formAction").val("Draft");
		}  
		
			
		
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
			$("#formDateOrderDate").val("");
			/* $('#bformDateOrderDate').datetimepicker('setStartDate', null);
			$('#bformDateOrderDate').datetimepicker('setStartDate', '0'); */
			$("#formDateEffectiveDate").val("");
		/* 	$('#bformDateEffectiveDate').datetimepicker('setStartDate', null);
			$('#bformDateEffectiveDate').datetimepicker('setStartDate', '0'); */
			$("#formDateGazPubDate").val("");
			/* $('#bformDateGazPubDate').datetimepicker('setStartDate', null);
			$('#bformDateGazPubDate').datetimepicker('setStartDate', '0'); */
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
	
	$("#bdateParamFrom").datetimepicker({
			format: 'dd/mm/yyyy',
			startView : 'month',
			endDate: '+0d',
	        autoclose: true,
			minView : 'month',
			pickerPosition : "bottom-left",
			EndDate:'0',
		}).on('changeDate', function(selected){
		 $('#dateParamTo').datetimepicker('setStartDate', Date.parse( selected.date));
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
		 $('#dateParamTo').datetimepicker('setEndDate', Date.parse( selected.date));
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
	 $("#bformDateOrderDate").datetimepicker({
			format: 'dd/mm/yyyy',
			startView : 'month',
			endDate: '+0d',
	        autoclose: true,
			minView : 'month',
			pickerPosition : "bottom-left",
			EndDate:'0',
		}).on('changeDate', function(selected){
		//alert((selected.date).format('d/m/Y'));
		var momentDate = moment(_js_previous_ED, 'DD/MM/YYYY').toDate();
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
		 $("#bformDateGazPubDate").datetimepicker({
			format: 'dd/mm/yyyy',
			startView : 'month',
	        autoclose: true,
			minView : 'month',
			pickerPosition : "bottom-left",
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
	//$("#formDateOrderDate").val(orderDate).attr("readonly", "readonly");
	$("#formDateEffectiveDate").val(effectiveDate).attr("readonly", "readonly");
	//$("#formDateOrderDate").datepicker( "destroy" );
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



var validateGovForm=function(){
	 $( "span[id^=err]" ).each(function(){
			$( this ).text("");
	    });
	var error=false;
	var errors=new Array();
	errors[0]= validateOrderNo();
	errors[1]= validateFieldRequried("formDateOrderDate","<spring:message code='error.required.ORDERDATE' htmlEscape='true'/>");
	errors[2]= validateFieldRequried("formDateEffectiveDate","<spring:message code='error.required.EFFECTIVEDATE' htmlEscape='true'/>");
	<c:if test="${isGovernmentOrderUpload}">
		if($_checkEmptyObject($("#orderCode").val())){
			if( isParseJson("${not modifyProcess}") ){
					errors[3]= validateUploadGovOrder();
			}
		}
	</c:if> 
	for ( var i = 0; i < errors.length; i++) {
		if (errors[i] == true) {
			error = true;
			break;
		}

	}
	//alert(error);
return error;
};

validateOrderNo=function(){
	var _error_flag=false;
	var orderno= $("#orderNo" ).val();
	if($_checkEmptyObject(orderno)){
		$( '#errorderNo').text("<spring:message code='ORDERNUMBER.REQUIRED' htmlEscape='true'/>"); 
		_error_flag=true;
	}else{
		var re = new RegExp("^[a-zA-Z0-9 ]+$", "g");
		 if(!re.test(orderno)){
			 $( '#errorderNo').text("<spring:message code='Error.invalidegovordrno' htmlEscape='true'/>");	 
			 _error_flag=true;
		 } 
	}
	return _error_flag;
}

validateFieldRequried=function(id,errorMsg){
	var _error_flag=false;
	var orderno= $("#"+id ).val();
	if($_checkEmptyObject(orderno)){
		$( '#err'+id).text(errorMsg); 
		_error_flag=true;
	}
	return _error_flag;
};


validateUploadGovOrder=function(){
	var _error_flag=false;
	var gazettePublicationUpload= $("#gazettePublicationUpload" ).val();
	if($_checkEmptyObject(gazettePublicationUpload)){
		$( '#errgazettePublicationUpload').text("<spring:message code='GOVERMENTORDER.REQUIRED' htmlEscape='true'/>"); 
		_error_flag=true;
	}
	return _error_flag;
}
/* $(function () {
    $("#tblGornmentOrderDetails").DataTable();
  }); */
  
  
</script>



<title><spring:message htmlEscape="true"  code="Title.goverment.order"></spring:message></title>
</head>
<body>


	<section class="content">
		<div class="row">
			<section class="col-lg-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title"><spring:message htmlEscape="true" code="Title.CREATENEWSUBDISTRICT"></spring:message></h3>
					</div>
					<form:form action="buildDraftSubdistrict.htm" method="post" id="draftGovermentOrderForm" commandName="draftGovermentOrderForm" enctype="multipart/form-data" class="form-horizontal">
						<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="buildDraftSubdistrict.htm"/>" />
						<form:hidden path="formAction"	id="formAction"/>
						<form:hidden path="landRegionType"	/>
						<form:hidden path="landRegionCode"	/>
						<form:hidden path="operationType"	/>
						<div class="box-body">
						
<!-- 			------modal start---		 -->	
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
				<!-- 		------modal end--    -->
							
							<div class="box-header subheading">
					            <h4><spring:message htmlEscape="true"  code="Legend.GENERALDETAILOFNEWSUBDISTRICT"></spring:message></h4>
					        </div>
						
							<c:if test="${isGovernmentOrderUpload}">
								<div class="form-group" style="display: none">
			                      	<div class="col-sm-6">
			                        	<button type="button" class="btn btn-primary" id="actionExistingGO"><spring:message code='Label.selectexistinggo' htmlEscape='true'/></button>
										<button type="button" class="btn btn-primary" id="actionNewGO"><spring:message code='Label.selectnewgo' htmlEscape='true'/></button>
			                      	</div>
			                    </div>
		                    </c:if>
							<div class="form-group">
		                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message><span class="mandatory">*</span></label>
		                      	<div class="col-sm-6">
		                        	<form:hidden path="orderCode" id="orderCode" htmlEscape="true"/>
									<form:input path="orderNo" id="orderNo" maxlength="60" htmlEscape="true" class="form-control"/>
									<br/>
									<form:errors htmlEscape="true" path="orderNo" cssClass="error"/>
									<span class="errormessage" id="errorderNo"></span>
									<input type="hidden" id="checkNewOrExistGovtOrder"/>
		                      	</div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-sm-3 control-label"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
		                      	<div class="col-sm-6">
		                      	 <div class="input-group date datepicker" id="bformDateOrderDate">
			                        	<form:input path="orderDate" id="formDateOrderDate" readonly="true" type="text" class="form-control" /><span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
									</div> 
									<span class="errormessage" id="errformDateOrderDate"></span>
									<br/>
									<form:errors htmlEscape="true" path="orderDate" cssClass="error"/>
		                      	</div>
		                    </div>
		                    
		                    
		                   <%--   <form:input path="orderDate" readonly="true" id="OrderDate"  class="form-control" onchange="setEffectiveDate(this.value);"
							onkeypress="validateNumeric();" onfocus="validateOnFocus('OrderDate');helpMessage(this,'OrderDate_msg');" onblur="vlidateOnblur('OrderDate','1','15','m');hideHelp();" onkeyup="hideMessageOnKeyPress('OrderDate')" /> 
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span> --%>
		                    
		                    
		                    <div class="form-group">
		                    	<label class="col-sm-3 control-label">
		                    		<spring:message	code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> 
									<c:if test="${not empty draftGovermentOrderForm.iParamEffectiveDate}">
										<strong>( <spring:message	code="Label.Previous.Version.Effective.Date" htmlEscape="true"></spring:message> : <fmt:formatDate pattern="dd/MM/yyyy" value="${draftGovermentOrderForm.iParamEffectiveDate}" /> )</strong>
									</c:if><span class="mandatory">*</span>
								</label>
		                      	<div class="col-sm-6">
		                      		 <div class="input-group date datepicker" id="bformDateEffectiveDate">
		                        		<form:input path="effectiveDate" id="formDateEffectiveDate" readonly="true" class="form-control"/>
		                        		<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
		                        	</div> 
									<span class="errormessage" id="errformDateEffectiveDate"></span>
									<form:hidden path="iParamEffectiveDate" htmlEscape="true"/><!-- Previous Version Effective Date -->
									<br/>
									<form:errors htmlEscape="true" path="effectiveDate" cssClass="error"/>
		                      	</div>
		                    </div>
		                    <c:choose>
								<c:when test="${isGovernmentOrderUpload}">
									<div class="form-group">
				                    	<label class="col-sm-3 control-label"><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message></label>
				                      	<div class="col-sm-6">
				                      <div class="input-group date datepicker" id="bformDateGazPubDate">
		                        		<form:input	 path="gazPubDate" id="formDateGazPubDate" readonly="true" class="form-control"/><span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
		                        	</div> 
											<br/>
											<form:errors htmlEscape="true" path="gazPubDate" cssClass="error"/>
				                      	</div>
				                    </div>
				                    <div class="form-group">
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
										<label class="col-sm-3 control-label">	
												<spring:message code="Label.UPLOADGOVTORDER" htmlEscape="true"/>
												<spring:message code='Label.allowedfileexe' htmlEscape='true'/>
												<span class="mandatory">*</span>								
										</label>
										<div class="col-sm-6" id="divGazettePublicationUpload" <c:if test="${not empty draftGovermentOrderForm.orderCode}">style="display: none;"</c:if>>
											<form:input path="gazettePublicationUpload" id="gazettePublicationUpload" type="file" class="form-control"/>
											<span class="errormessage" id="errgazettePublicationUpload"></span>
											<c:set var="gazettePublicationUploadHasBindError">
												<form:errors path="gazettePublicationUpload"/>
											</c:set>
											<c:if test="${! empty gazettePublicationUploadHasBindError }">
												<span class="mandatory">${gazettePublicationUploadHasBindError}</span>
												<c:if test="${fn:contains(gazettePublicationUploadHasBindError, 'restricted keyword(s)')}">
													<a onclick="showInfo()" href="#"><i class="fa fa-info-circle fa-2x" aria-hidden="true"></i></a>
												</c:if>
											</c:if>
										</div>	
				                    </div>
								</c:when>
								<c:otherwise>
									<div class="form-group">
				                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELGOT"></spring:message><span class="mandatory">*</span>	</label>
				                      	<div class="col-sm-6">
				                        	<form:select path="templateId" id="templateId" class="form-control">
												<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT"/></form:option>
												<form:options items="${templateList}" itemLabel="templateNameEnglish" itemValue="templateCode"></form:options>
											</form:select>
				                      	</div>
				                    </div>
				                    <div class="form-group">
				                      	<div class="col-sm-12" id="divCKEditor" <c:if test="${not (modifyProcess or checkedServerValidation)}">style="display:none"</c:if>>
											<form:textarea path="editorTemplateDetails" id="editorTemplateDetails" cssClass="ckeditor" rows="10" cols="80"/>
												 
				                      	</div>
				                    </div>
								</c:otherwise>
							</c:choose>
							<div class="box-footer">
		                    	<div class="col-sm-offset-2 col-sm-10">
		                    		<div class="pull-right">
										<div style="display: none" id="drafthide">
										<button class="btn btn-primary" id="btnFormActionSaveDraft" type="submit"><spring:message htmlEscape="true" code="Button.DRAFT"/></button> 
										</div>
										<button class="btn btn-success" id="btnFormActionPublish" type="button"><spring:message htmlEscape="true" code="Button.SP"/></button>
										<button class="btn btn-danger" id="btnActionClose" type="button" onclick="callActionUrl('home.htm')"><spring:message htmlEscape="true" code="Button.CLOSE"/></button>
		                        	</div>
		                    	 </div>   
	                  		</div>
                  		</div>
					</form:form>
				</div>
			</section>
		</div>
	</section>
	<%@include file="commanGovErrorPage.jsp"%>
	<!-- Modal -->
  
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
  
  
  
  
		
	<c:if test="${not empty draftGovermentOrderForm.orderCode}"> 
		<script type='text/javascript'>
			$(window).load(function () {
				$("#checkNewOrExistGovtOrder").val('Y');
				$("#orderNo").attr("readonly", "readonly");
				//$("#formDateOrderDate").datepicker( "destroy" );
				$("#formDateEffectiveDate").datepicker( "destroy" );
				$("#formDateGazPubDate").datepicker( "destroy" );
			}); 
			
			
			
		</script>
	</c:if>
</body>
</html>





