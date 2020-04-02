<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/dwrRestructuredLocalBodyService.js"></script>
<script>
var lbtype = $('#localBodyType option:selected');
var _JS_STATE_CODE = '${stateCode}';
var _JS_DISTRICT_CODE = '${districtId}';
$(document).ready(function() {
	registerCallDynamicHierarchy(lbtype);
	
	var dt = $('#example').DataTable({
		"bJQueryUI": false,
		"order": [[ 1, "asc" ]]
	});
	
	
	$("#actionFetchLBDetails").click(function() {
		if(validateForm()){
			callActionUrl('updateGISBoundaries.htm');
		}
		
	});
	
	$( "#actionSearchClose" ).click(function() {
		callActionUrl('home.htm');
	});
	
	$( "#actionClose" ).click(function() {
		callActionUrl('home.htm');
	});
});


validateForm =function(){
	var element = $( '[name = localBodyLevelCodes]' );
	var localBodyElement = $( element )[$( element ).length - 1];
	if(!$_checkEmptyObject(localBodyElement) && !validateLBCode(localBodyElement)){
		return false;
	}
	return true;
};

var callActionUrl = function (url) {
	$( 'form[id=formCDraftedLB]' ).attr('action', url +'?<csrf:token uri="'+ url +'"/>');
	$( 'form[id=formCDraftedLB]' ).attr('method','post');
	$( 'form[id=formCDraftedLB]' ).submit();
};


/**
 * The {@code validateLBCode} function used to check for valid  
 * local body code at different levels.
 */
var validateLBCode = function (localBodyElement){
	if($_checkEmptyObject($( localBodyElement ).val())){
		 var elements = $( '[name = localBodyLevelCodes]' );
		 $(elements).each(function(index){
			 
			 if($_checkEmptyObject($( this ).val())){
				$( this ).addClass("error");
				$( '#error' + $( this ).attr('id') ).text("<spring:message code='Label.SELLOCALBODYTYPE' htmlEscape='true'/>");	 
			 }
		 });
		 return false;
	}
	return true; 
};
/**
 * The {@code $_checkEmptyObject} used to check object / element  
 * is empty or undefined.
 */
var $_checkEmptyObject = function(obj) {
	if (jQuery.type(obj) === "undefined" || (obj == null || $.trim(obj).length == 0)) {
		return true;
	}
	return false;
};
/**
 * The {@code isDistrictUser} used to check logged-in user   
 * is of district or state.
 */
var isDistrictUser = function (){
	if(_JS_DISTRICT_CODE != null && parseInt(_JS_DISTRICT_CODE) > 0){
		return true;
	} else{
		return false;
	}
}
/**
 * The {@code registerCallDynamicHierarchy} function to call dwr event
 * to build dynamic hierarchy based on local body type.
 */
var registerCallDynamicHierarchy = function(obj) {
	 $("#divCreateDynamicHierarchy").empty();
	 var divTemplate = $("#divCreateDynamicHierarchy");
	 var isDWRCallRequired = true;
	 var localBodyTypeElement = $('#localBodyType option[value != ""]');
	 
	 $(localBodyTypeElement).each(function(index){
		 var localbodyTypeArray = $(this).val().split("_");
		 var localbodyTypeCode = localbodyTypeArray[0];
		 var tiersetupCode = localbodyTypeArray[1];
		 var parentTiersetupCode = localbodyTypeArray[2];
		 if((3 != localbodyTypeCode )){
			 
		    var localbodyTypeName = $(this).text();
		    
		    var templateUL = $("<div/>");
		    templateUL.addClass('form-group');
		    
		    // Added Li Elements for UL
			
			
		    // Added Label Elements
			var templateLabel = $("<label />");
			templateLabel.addClass('col-sm-3 control-label');
			templateLabel.html("<spring:message htmlEscape='true' code='Label.SELECT'/> " + localbodyTypeName + " <span class='mandate'>*</span>");
			
			var templateLI = $("<div class=col-sm-6/>");
			// Added Html Select Elements
			var templateSelect = $("<select class=form-control/>");
			templateSelect.attr("id", "localBodyLevelCodes_" + tiersetupCode + "_" + parentTiersetupCode);
			templateSelect.attr("name", "localBodyLevelCodes");
			$(templateSelect).change(function() {
				$( this ).removeClass("error");
				$( '#error' + $( this ).attr('id') ).text("");
				if(index != (obj.selectedIndex - 2)){
					configureLocalBodyTypeHierarchy(this);	
				}
			});
			
			var options = $(templateSelect);
			options.append($("<option />").val("").text("<spring:message htmlEscape='true' code='Label.SELECT'/>"));
			if(isDWRCallRequired){
				isDWRCallRequired = false;
				buildDistrictPanchayatOptions(options, localbodyTypeCode);
			}
			
			var templateError = $("<span/>");
			templateError.attr("id", "error" + $(templateSelect).attr('id'));
			templateError.attr("class", "mandatory");
			
			// Adding Dynamic Elements to Template Div.
			//templateLI.append(templateLabel);
			templateLI.append(templateSelect);
			templateLI.append(templateError);
			
			templateUL.append(templateLabel);
			templateUL.append(templateLI);
			divTemplate.append(templateUL);
		 }
	});
	 
	 
};


/**
 * The {@code configureLocalBodyTypeHierarchy} function used to configue 
 * child nodes local bodies based on Parent LB code.
 */
var configureLocalBodyTypeHierarchy = function(object){
	var localBodyCode = $(object).val();
	var elementIdArray =  $(object).attr('id').split("_");
	var selectedElementId = elementIdArray[0]; 
	var selectedElementTiersetupCode = elementIdArray[1];
	var childNode = null;
	var nextParent = selectedElementTiersetupCode;
	
	$('#localBodyType option[value != ""]').each(function(index){
		
		var localbodyTypeArray = $(this).val().split("_");
		var tiersetupCode = localbodyTypeArray[1];
		var parentTiersetupCode = localbodyTypeArray[2];
		
		//Used to remove child nodes of selected Local Body.
		if(nextParent != tiersetupCode && selectedElementTiersetupCode != tiersetupCode){
			$('#' + selectedElementId + "_" + tiersetupCode + "_" + nextParent +' option[value != ""]').remove();
		}
		nextParent = tiersetupCode;
		
		//Used for assign element Id to next child nodes of selected Local Body.
		if(selectedElementTiersetupCode == parentTiersetupCode){
			childNode  = selectedElementId + "_" + tiersetupCode + "_" + selectedElementTiersetupCode;
		}
	});
	if(!$_checkEmptyObject(localBodyCode)){
		buildParentwisePanchayatOptions(childNode, localBodyCode);
	}
};

/**
 * The {@code buildDistrictPanchayatOptions} used create select box options at 
 * District Panchayat Level. 
 * @param elementTemplate (Element Template for given select box instance)
 * @param localbodyTypeCode
 */
var buildDistrictPanchayatOptions = function(elementTemplate, localbodyTypeCode) {
	if(isDistrictUser()){
		dwrRestructuredLocalBodyService.getDistrictPanchayatListForDistrictUser(parseInt(localbodyTypeCode), parseInt(_JS_DISTRICT_CODE), null, null,{
			callback : callbackHandlerForDPOptions,
			callbackArg : elementTemplate,
			async : true
		});	
	}else{
		dwrRestructuredLocalBodyService.getDistrictPanchayatList(parseInt(localbodyTypeCode), parseInt(_JS_STATE_CODE), null, null,{
			callback : callbackHandlerForDPOptions,
			callbackArg : elementTemplate,
			async : true
		});	
	}
};

/**
 * The {@code callbackHandlerForDPOptions} used create select box options at 
 * Local Body Level using selected local body code for district and state users. 
 * @param result Object Array returns from call back
 * @param elementTemplate (Element Template for given select box instance)
 */
var callbackHandlerForDPOptions = function (result, elementTemplate){
	jQuery.each(result, function(index, obj) {
		var optionText = obj.localBodyNameEnglish;
		var option = $("<option />");
		if (obj.isDisturbed) {
			option.attr("disabled", true);
			optionText = optionText.concat(" <spring:message code='Label.disturblocalbody' htmlEscape='true'/>");
		}
		if (obj.isUsed) {
			option.attr("disabled", true);
			optionText = optionText.concat(" <spring:message code='Label.draftlocalbdy' htmlEscape='true'/>");
		}
		option.val(obj.localBodyCode).text(optionText);
		elementTemplate.append(option);
	});
};


/**
 * The {@code buildParentwisePanchayatOptions} used create select box options at 
 * Local Body Level using selected local body code as parent. 
 * @param elementNode (Element Template for given select box instance)
 * @param localBodyCode
 */
var buildParentwisePanchayatOptions = function (elementNode , localBodyCode){
	dwrRestructuredLocalBodyService.getParentwiseLocalBody(parseInt(localBodyCode), null, null, {
		callback : function(result){
			var options = $("#" + elementNode);
			jQuery.each(result, function(index, obj) {
				var optionText = obj.localBodyNameEnglish;
				var option = $("<option />");
				if (obj.isDisturbed) {
					option.attr("disabled", true);
					optionText = optionText.concat(" <spring:message code='Label.disturblocalbody' htmlEscape='true'/>");
					
				}
				if (obj.isUsed) {
					option.attr("disabled", true);
					optionText = optionText.concat(" <spring:message code='Label.draftlocalbdy' htmlEscape='true'/>");
				}
				option.val(obj.localBodyCode).text(optionText);
				options.append(option);
			});
		},
		async : true
	});	
};

var callGISMapView = function ( localBodyCode, localBodyName,isShowOnlyBoundary,updateApprovedGP ){
	//alert("updateApprovedGP:"+updateApprovedGP);
	dwrRestructuredLocalBodyService.getMappedLBsForGIS(parseInt(localBodyCode),localBodyName,isShowOnlyBoundary,updateApprovedGP, {
		callback : function( result ){
			//alert(result);
			if("FAILED" == result){
				customAlert(result);
			}else{
				
				$("#dialogBXTitle").text('GIS Map View ( Local Body Code : ' + localBodyCode + ' , Local Body Name : ' + localBodyName + ' )');
				
				 $('#myIframe').attr('src', result);
				 $('#dialogBX').modal('show'); 
				
				 /*$("#dialogBX").dialog({
					title:'GIS Map View ( Local Body Code : ' + localBodyCode + ' , Local Body Name : ' + localBodyName + ' )',
				    modal: true,
				    width:950,
				    height: 700,
				    resizable:false,
				    open: function(ev, ui){
				    	 showLoadingImage();
			             $('#myIframe').attr('src', result);
			             $("#myIframe").load(function(){
			            	 hideLoadingImage(); 
					    });
			    	}
				});	*/
			}
		},
		errorHandler : function( errorString, exception){
				customAlert(exception);
		},
		async : true
	});		
};

/*
 * Listening for 'message' from GIS. Mandatory to define this method before
 * registaration of event listener.
 * @link window.addEventListener("message", receiveMessageFromGIS, false)
 *
 */
  var receiveMessageFromGIS = function (event){  
	
	//if (event.origin == "http://10.1.43.239" ) {
   		var dataFromGIS = event.data;//'12345:Y';
   	// alert(dataFromGIS);
		 if(! $_checkEmptyObject( dataFromGIS )){
   			var dataArray = dataFromGIS.split(":");
			if("N" == dataArray[1]){
				$('#dialogBX').dialog('close');
				return false;
			}
			callGISCoverage(parseInt(dataArray[0]),'callChangeCoveredAreaPublishedLocalBody.htm','Y');
			/* dwrRestructuredLocalBodyService.saveGISStatus( dataFromGIS , {
   				callback : function( result ){
   					if("SUCCESS" == result){
   						$('#dialogBX').dialog('close');
						customAlert('Map has been saved successfully.');
   					} else {
						$('#dialogBX').dialog('close');
					}
   				},
   				async : true
   			});	 */	
   		} 
	//}
	}
	
/**
 * Register event listner for GIS Communication.
 */
window.addEventListener("message", receiveMessageFromGIS, false);


</script>