<%@include file="commanDraftSubdistrictJs.jsp"%>
<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/dwrRestructuredLocalBodyService.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/resources-localbody/scripts/jquery.fileDownload.js"></script>
<%-- <script type="text/javascript" language="javascript" src="<%=contextpthval%>/ckeditor/ckeditor.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/ckeditor/ckeditor_components.js"></script> --%>
<script type="text/javascript" src="<%=contextpthval%>/resource/dashboard-resource/dist/js/date.format.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/resource/dashboard-resource/dist/js/date.format.min.js"></script>
<script type="text/javascript" src="http://momentjs.com/downloads/moment.min.js"></script>

<script language="javascript">
$(document).ready(function() {
	
	
	
	$("#btnFormActionSaveDraft").click(function() {
		<c:if test="${isGovernmentOrderUpload}">
		if(!$_checkEmptyObject($("#orderCode").val())){
				$( "#gazettePublicationUpload" ).rules( "remove" );
			$( "#gazettePublicationUpload" ).removeClass( "error" );
			}
		</c:if>
		validateFormdeails("Draft");
	 });

	$("#btnFormActionPublish").click(function() {
		validateFormdeails("Publish");
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
		 $('#bdateParamTo').datetimepicker('setStartDate', Date.parse( selected.date));
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
		 $('#bdateParamTo').datetimepicker('setEndDate', Date.parse( selected.date));
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
 
 
validateFormdeails=function(formAction){
	  $( "span[id^=err]" ).each(function(){
			$( this ).text("");
	    });
	var error=true; 
	var errors=new Array();
	
	errors[1]= validateChangeSubdistrictNameEng();
	errors[2]=validateGoDetails();
	//alert('${isGovernmentOrderUpload}');
	/* <c:if test="${isGovernmentOrderUpload}">
	errors[3]=fileUploadValidate();
	</c:if>  */
	for ( var i = 0; i < errors.length; i++) {
		if (errors[i] == false) {
			error = false;
			break;
		}

	}
	
	if(error){
		//alert("save");
	 
		 $("#formAction").val(formAction);
		  $( "#btnFormActionSaveDraft" ).prop( "disabled", true );
		 $( "#btnFormActionPublish" ).prop( "disabled", true );
		 $( "#btnActionClose" ).prop( "disabled", true ); 
		callActionUrl('buildSubdistrictDraftChange.htm'); 
	} 
	   
			
};

fileUploadValidate=function(){
	//alert("in");
	//alert(isuploadNew);
	var editMode=isParseJson("${draftManageSubdistrictForm.editMode}");
	//alert(editMode);
	var gazettePublicationUpload=$("#gazettePublicationUpload");
	if(editMode){
		if(isuploadNew){
			if ($_checkEmptyObject($(gazettePublicationUpload).val())) {
				$( '#errgazettePublicationUpload').text("<spring:message code='GOVERMENTORDER.REQUIRED' htmlEscape='true'/>"); 
				return false;
			}else{
				var fileElement = null, mimeType = null, fileExtension = null;
				if ( $.browser.msie && $.browser.version < 10) {
					try{
						var objFSO = new ActiveXObject("Scripting.FileSystemObject"); 
						var filePath = $(element)[0].value;
						try{
						fileElement = objFSO.getFile(filePath);
						}
						catch (e) {
							return true;
						}
						if($_checkEmptyObject(fileElement)) return true;
						mimeType = objFSO.GetExtensionName(filePath);
						fileExtension = mimeType;//objFSO.GetFileName(filePath);
					} catch( e ){
						customAlert( 'Please make sure ActiveX is enabled in your IE browser.' );
						return false;
					}
				} else {
					
					fileElement = $(gazettePublicationUpload)[0].files[0];
					if($_checkEmptyObject(fileElement)) {
						return true;
					}
					mimeType = fileElement.type;
					fileExtension = fileElement.name.split('.').pop();
				}
				 if(! checkUploadedDocs(fileElement.size, '${attachmentMasterGO.individualFileSize}', '${attachmentMasterGO.fileType}', mimeType, fileExtension)){
					 $( '#errgazettePublicationUpload').text("<spring:message code='Error.invalidgofile' htmlEscape='true'/>"); 
					 return false;
				 }
				 else{
					 return true;
				 }
			}
		}

	}else{
		if ($_checkEmptyObject($(gazettePublicationUpload).val())) {
			$( '#errgazettePublicationUpload').text("<spring:message code='GOVERMENTORDER.REQUIRED' htmlEscape='true'/>"); 
			return false;
		}else{
			var fileElement = null, mimeType = null, fileExtension = null;
			if ( $.browser.msie && $.browser.version < 10) {
				try{
					var objFSO = new ActiveXObject("Scripting.FileSystemObject"); 
					var filePath = $(element)[0].value;
					try{
					fileElement = objFSO.getFile(filePath);
					}
					catch (e) {
						return true;
					}
					if($_checkEmptyObject(fileElement)) return true;
					mimeType = objFSO.GetExtensionName(filePath);
					fileExtension = mimeType;//objFSO.GetFileName(filePath);
				} catch( e ){
					customAlert( 'Please make sure ActiveX is enabled in your IE browser.' );
					return false;
				}
			} else {
				
				fileElement = $(gazettePublicationUpload)[0].files[0];
				if($_checkEmptyObject(fileElement)) {
					return true;
				}
				mimeType = fileElement.type;
				fileExtension = fileElement.name.split('.').pop();
			}
			 if(! checkUploadedDocs(fileElement.size, '${attachmentMasterGO.individualFileSize}', '${attachmentMasterGO.fileType}', mimeType, fileExtension)){
				 $( '#errgazettePublicationUpload').text("<spring:message code='Error.invalidgofile' htmlEscape='true'/>"); 
				 return false;
			 }
			 else{
				 return true;
			 }
		}
	}
	
	
	 
};

validateGoDetails=function(){
	 var _error_flag=true;
	 var orderNo=$("#orderNo");
	if ($_checkEmptyObject($(orderNo).val())) {
		$( '#errorderNo').text("<spring:message code='ORDERNUMBER.REQUIRED' htmlEscape='true'/>"); 
		_error_flag=false;
	}else if($(orderNo).val().length>60)
	{
		$( '#errorderNo').text("<spring:message code='Error.Sizevalid' htmlEscape='true'/>"); 
		_error_flag=false;
	}
	
	 var orderDate=$("#formDateOrderDate");
	 if ($_checkEmptyObject($(orderDate).val())) {
			$( '#errformDateOrderDate').text("<spring:message code='error.required.ORDERDATE' htmlEscape='true'/>"); 
			_error_flag=false;
	 }
	
	 var effectiveDate=$("#formDateEffectiveDate");
	 if ($_checkEmptyObject($(effectiveDate).val())) {
			$( '#errformDateEffectiveDate').text("<spring:message code='error.required.EFFECTIVEDATE' htmlEscape='true'/>"); 
			_error_flag=false;
	 }
	 
	return _error_flag;
};



validateChangeSubdistrictNameEngUnique=function(subDistrictNameEng){
	
	if (!$_checkEmptyObject(subDistrictNameEng)) {
		var subdistrictNameEnglishElement=$("#subdistrictNameEnglish");
		
		
			
			dwrDraftSubdistrictService.subdistrictNameIsUnique(subDistrictNameEng,parseInt('${draftManageSubdistrictForm.districtCode}'), {
					callback : function( uniqueFlag ) {
						if(uniqueFlag=='D'){
							$( '#errsubdistrictNameEnglish').text("<spring:message code='Error.subdistrict.name.unique.draft' htmlEscape='true'/>"); 
							$(subdistrictNameEnglishElement).focus();
						}else if(uniqueFlag=='P'){
							$( '#errsubdistrictNameEnglish').text("<spring:message code='Error.subdistrict.name.unique.publish' htmlEscape='true'/>"); 
							$(subdistrictNameEnglishElement).focus();
						}
					},
					errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
					async : true
				});
		
	}else{
		$("#subdistrictNameEnglish").val("");
	}
	
};


validateChangeSubdistrictNameEng=function(){
	 var _error_flag=true;
	var re = new RegExp("^[a-zA-Z ]+$", "g");
	 var subdistrictNameEnglish=$("#subdistrictNameEnglish").val();
	 //alert(SectionNameEng);
	 //alert(SectionNameEng.charAt(0).charCodeAt());
	 if($_checkEmptyObject(subdistrictNameEnglish)){
		 $( '#errsubdistrictNameEnglish').text("<spring:message code='Label.subdsitrict.name.required' htmlEscape='true'/>");
		 _error_flag=false;
	 }else{
		 if(!re.test(subdistrictNameEnglish)){
			 $( '#errsubdistrictNameEnglish').text("<spring:message code='Error.invalidchar' htmlEscape='true'/>");	 
			 _error_flag=false;
		 } else{
		
				 for(i=0;i<subdistrictNameEnglish.length;i++){
					 key=subdistrictNameEnglish.charAt(i).charCodeAt();
					 if(key==32)/* Space Key */
						{
							if(spaceCount>0){
								$( '#errsubdistrictNameEnglish').text("<spring:message code='Error.invalidspace' htmlEscape='true'/>"); 
								 _error_flag=false;
							}
							spaceCount++;
						}else {
							spaceCount=0;
						}
				 }
			
			 
			 
		 }
	 }
	 
	 if(_error_flag && subdistrictNameEnglish=='${draftManageSubdistrictForm.subdistrictNameEnglish}'){
		 $( '#errsubdistrictNameEnglish').text("<spring:message code='error.change.commonAlert' htmlEscape='true'/>");	 
		 _error_flag=false;
	 }else{
		 validateChangeSubdistrictNameEngUnique(subdistrictNameEnglish);
	 }
	 if(! _error_flag){
		 var subdistrictNameEnglishElement=$("#subdistrictNameEnglish");
			$( subdistrictNameEnglishElement ).addClass("error");
			if(!firstErrorElement){
				$(subdistrictNameEnglishElement).focus();
				firstErrorElement=true;
			}
			
	 }
	
	 return _error_flag;
};




 /**
  * The {@code previousEffectiveDate} used for formatting previous effective date,
  * value must be null in case of government order.
  * @return $.datepicker.formatDate(effective date).
  *
  */
var previousEffectiveDate = function(){
	 if(!$_checkEmptyObject('${draftManageSubdistrictForm.iParamEffectiveDate}')){
		 var iEffDateArray = "${draftManageSubdistrictForm.iParamEffectiveDate}".split(/-|\s|:/); 
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
		$('#formDateEffectiveDate').val((selected.date).format('d/m/Y'));
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
		 $('#bformDateEffectiveDate').datetimepicker('setStartDate', Date.parse( momentDate));
		}else{
		$('#bformDateEffectiveDate').datetimepicker('setStartDate', (selected.date).format('d/m/Y'));
		}
		$('#bformDateGazPubDate').datetimepicker('setStartDate',(selected.date).format('d/m/Y')); 
		_js_previous_ED = previousEffectiveDate();

	    });
	 
	 $("#bformDateOrderDate").datetimepicker({
			format: 'dd/mm/yyyy',
			startView : 'month',
	        autoclose: true,
			minView : 'month',
			pickerPosition : "bottom-left",
			StartDate:_js_previous_ED,
			EndDate:'0',
		}).on('changeDate', function(selected){
			$('#bformDateOrderDate').datetimepicker('setEndDate', (selected.date).format('d/m/Y'));
			
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
					//$('#formDateOrderDate').datetimepicker('setEndDate', '0');
					//$('#formDateEffectiveDate').datetimepicker('setEndDate', '0');
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
    $( "#divExistingOrderDeatils" ).modal('show');
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
			alert("<spring:message code='Error.invalidrequest' htmlEscape='true'/>");
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
   
    $( 'form[id=draftManageSubdistrictForm]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
	$( 'form[id=draftManageSubdistrictForm]' ).attr('method','post');
	$( 'form[id=draftManageSubdistrictForm]' ).submit();
};


var isuploadNew=isParseJson("${!draftManageSubdistrictForm.editMode}");
//alert(isuploadNew);
deleteExistUploadFile=function(){
	$("#attachFile").hide(); 
	$( '#errgazettePublicationUpload' ).text("");
	isuploadNew=true;
};

validateFileUpload=function(){
	if(!isuploadNew){
		$( '#errgazettePublicationUpload' ).text("<spring:message htmlEscape='true' code='Label.delete.file.first' text='Please Delete Earlier Attached File First'/>");
	}
	return isuploadNew;
};

function setEffectiveDate(orderdate) {
	document.getElementById('formDateEffectiveDate').value = orderdate;
}
</script>
