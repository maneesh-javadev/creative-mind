
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/plugins/datatables/dataTables.bootstrap.css">
 <script src="resource/dashboard-resource/plugins/datatables/jquery.dataTables.min.js"></script>
 <script src="resource/dashboard-resource/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/dwrRestructuredLocalBodyService.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/lgdDwrStateService.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/resources-localbody/scripts/jquery.numeric.js"></script>
<script>
var _JS_STATE_CODE;

removeAllOptions=function(id){
	dwr.util.removeAllOptions(id);
};



var buildLocalbodyHierarchy = function(stateCode){
	_JS_STATE_CODE=stateCode;
	removeAllOptions('lbTypeHierarchy')
	dwrRestructuredLocalBodyService.getLBTypeHierarchyStateWiseDetials(parseInt(stateCode), {
		callback : function( result ) {
			var options = $("#lbTypeHierarchy"); 
			var option = $("<option/>");
			$(option).val("").text("<spring:message code='Label.SEL' htmlEscape='true'/>");
			options.append(option);
			jQuery.each(result, function(index, obj) {
				var option = $("<option />");
				var _obj_value=obj.localBodySetupCode+","+obj.localBodySetupVersion+","+obj.hierarchyType;
				option.val(_obj_value).text(obj.localBodyTypeHierarchy);
				options.append(option);
			});
		},
		errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
		async : true
	});
};


/**
 * The {@code registerCallLocalBodyType} function to call dwr event
 * to retrieve List of Local Body Types.
 * @param setupCode
 * @param setupVersion
 */
var registerCallLocalBodyType = function() {
	$('#localBodyType option[value != ""]').remove();
	var _lb_type_value = $('#lbTypeHierarchy option:selected').val();
	if (!$_checkEmptyObject(_lb_type_value)) {
		var optVaues = _lb_type_value.split(',');
		var setupCode = optVaues[0]; // local body setup code
		var setupVersion = optVaues[1]; // local body setup version
		dwrRestructuredLocalBodyService.buildLocalBodyHierarchyWithCategory(parseInt(setupCode), parseInt(setupVersion), {
			callback : populateLBType,
			errorHandler : errorLbTypeProcess,
			async : true
		});
	}
};

/**
 * The {@code populateLBType} used for build drop-down box with 
 * Local Body Type Values. 
 */
var populateLBType = function(result) {
	var options = $("#localBodyType");
	jQuery.each(result, function(index, obj) {
		var option = $("<option />");
		var setOptValue = obj.localBodyTypeCode + "_" + obj.tierSetupCode + "_" + obj.parentTierSetupCode + "_" + obj.lbLevel+"_"+obj.lbCategory;
		option.val(setOptValue).text(obj.name);
		/* if (_JS_LOCAL_BODY_CREATION_TYPE != obj.lbType) {
			option.attr("disabled", true);
		} */
		options.append(option);
	});
};

/**
 * The {@code errorLbTypeProcess} called when error 
 * occured while DWR call {@link buildLocalBodyHierarchy}.
 */
var errorLbTypeProcess = function() {
	$("#errorLocalBodyType").html("<spring:message code='Error.invalidlbtype' htmlEscape='true'/>");
};

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
		 var category= localbodyTypeArray[4];
		 if((obj.selectedIndex - 1 > index) && (obj.value != localbodyTypeCode ) && (category!="U") ){
			 
		    var localbodyTypeName = $(this).text();
		    
		    var templateUL = $("<div/>");
		    templateUL.addClass("form-group");
		    
		   
			
		    // Added Label Elements
			var templateLabel = $("<label/>");
			templateLabel.html("<spring:message htmlEscape='true' code='Label.SELECT'/> " + localbodyTypeName + " <span class='mandatory'>*</span>");
			templateLabel.addClass('col-sm-4 control-label');
			
			 // Added Li Elements for UL
			var templateLI = $("<div/>");
			 templateLI.addClass("col-sm-6");
			
			
			// Added Html Select Elements
			var templateSelect = $("<select/>");
			templateSelect.attr("id", "localBodyLevelCodes_" + tiersetupCode + "_" + parentTiersetupCode);
			templateSelect.attr("name", "localBodyLevelCodes");
			templateSelect.addClass("form-control");
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
			templateUL.append(templateLabel);
			templateUL.append(templateLI);
			templateLI.append(templateSelect);
			templateLI.append(templateError);
			
			divTemplate.append(templateUL);
		 }
	});
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
 * The {@code buildDistrictPanchayatOptions} used create select box options at 
 * District Panchayat Level. 
 * @param elementTemplate (Element Template for given select box instance)
 * @param localbodyTypeCode
 */
var buildDistrictPanchayatOptions = function(elementTemplate, localbodyTypeCode) {
	
		dwrRestructuredLocalBodyService.getDistrictPanchayatListRPT(parseInt(localbodyTypeCode), parseInt(_JS_STATE_CODE), null, null,{
			callback : callbackHandlerForDPOptions,
			callbackArg : elementTemplate,
			async : true,
			errorHandler : handleDistrictErrorExtend
		});	
	
};

function handleDistrictErrorExtend() {
	alert("data not found!");

}

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
		/* if (obj.isDisturbed) {
			option.attr("disabled", true);
			optionText = optionText.concat(" <spring:message code='Label.disturblocalbody' htmlEscape='true'/>");
		}
		if (obj.isUsed) {
			option.attr("disabled", true);
			optionText = optionText.concat(" <spring:message code='Label.draftlocalbdy' htmlEscape='true'/>");
		} */
		option.val(obj.localBodyCode).text(optionText);
		elementTemplate.append(option);
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
 * The {@code buildParentwisePanchayatOptions} used create select box options at 
 * Local Body Level using selected local body code as parent. 
 * @param elementNode (Element Template for given select box instance)
 * @param localBodyCode
 */
var buildParentwisePanchayatOptions = function (elementNode , localBodyCode){
	dwrRestructuredLocalBodyService.getParentwiseLocalBodyRPT(parseInt(localBodyCode), null, null, {
		callback : function(result){
			var options = $("#" + elementNode);
			jQuery.each(result, function(index, obj) {
				var optionText = obj.localBodyNameEnglish;
				var option = $("<option />");
				/* if (obj.isDisturbed) {
					option.attr("disabled", true);
					optionText = optionText.concat(" <spring:message code='Label.disturblocalbody' htmlEscape='true'/>");
					
				}
				if (obj.isUsed) {
					option.attr("disabled", true);
					optionText = optionText.concat(" <spring:message code='Label.draftlocalbdy' htmlEscape='true'/>");
				} */
				option.val(obj.localBodyCode).text(optionText);
				options.append(option);
			});
		},
		async : true
	});	
};

/**
 * The {@code validateLBCode} function used to check for valid  
 * local body code at different levels.
 */
var validateLBCode = function (localBodyElement){
	var _error_flag=true;
	 if($_checkEmptyObject($( localBodyElement ).val())){
		 var elements = $( '[name = localBodyLevelCodes]' );
	
		 $(elements).each(function(index){
			 
			 if($_checkEmptyObject($( this ).val())){
				$( this ).addClass("error");
				$( '#error' + $( this ).attr('id') ).text("<spring:message code='Label.SELLOCALBODYTYPE' htmlEscape='true'/>");	
				_error_flag=false;
			 }
		 });
		 
	}
	
	return _error_flag; 
};

var callActionUrl = function (url) {
	$( 'form[id=genericReportingEntity]' ).attr('action', url +'?<csrf:token uri="'+ url +'"/>');
	$( 'form[id=genericReportingEntity]' ).attr('method','post');
	$( 'form[id=genericReportingEntity]' ).submit();
};

var viewEntityDetailsInPopup = function (entityCode, cusurl, paramName)	{
	if( isEmptyObject(entityCode) ){
		alert("No Record(s) found.");
		return false;
	}
/* 	$("#myIframe").contents().find("body").html('');
	$("#dialogBX").dialog({
		dialogClass: 'noTitleStuff',
	    modal: true,
	    width:950,
	    height: 700,
	    resizable:false ,
	    "open": function(){
	    	showLoadingImage();
	    	 $('#myIframe').attr('src', cusurl + "?" + paramName + "=" + entityCode + "&isState='N'&<csrf:token uri='" + cusurl + "'/>");  
             $("#myIframe").load(function(){
            	 hideLoadingImage(); 
		    });  
    	},
    });	
	$(".ui-dialog-titlebar").removeClass('ui-widget-header'); */
	
	
	$('#myIframe').attr('src', cusurl + "?" + paramName + "=" + entityCode + "&isState='N'&<csrf:token uri='" + cusurl + "'/>");  
	
	$('#dialogBX').modal('show'); 
};

var viewEntityDetailsInPopupForHistory = function (entityCode, cusurl, paramName)	{
	if( isEmptyObject(entityCode) ){
		alert("No Record(s) found.");
		return false;
	}
	/* $("#myIframe").contents().find("body").html('');
	$("#dialogBX").dialog({
		dialogClass: 'noTitleStuff',
	    modal: true,
	    width:950,
	    height: 700,
	    resizable:false ,
	    "open": function(){
	    	showLoadingImage();
	    	 $('#myIframe').attr('src', cusurl + "?" + paramName + "=" + entityCode + "&<csrf:token uri='" + cusurl + "'/>");  
             $("#myIframe").load(function(){
            	 hideLoadingImage(); 
		    });  
    	},
    });	
	$(".ui-dialog-titlebar").removeClass('ui-widget-header'); */
	 $('#myIframe').attr('src', cusurl + "?" + paramName + "=" + entityCode + "&<csrf:token uri='" + cusurl + "'/>");  
	 $('#dialogBX').modal('show'); 
	
};

var retrieveFile1 = function (entityCode, entityType) {
	lgdDwrStateService.getGovtOrderByEntityCode( parseInt(entityCode), entityType, {
  	 	callback: function( result ) {
  	 		if(result == null){
  	 			alert("No Government Order found.");
  	 		} else {
  	 			var filePath = result[0].filelocation;
  				lgdDwrStateService.openFile(filePath, {
  				  	callback: function( innerResult ) {
  				  		if(innerResult == null ) {
  						 	alert("File has been moved or deleted.");
  						} else {
  							if(innerResult.length > 5)	{
  								var message = innerResult.substring(0,5);
  								if(message == "ERROR") {
  									alert( innerResult );
  								}else{
  									var filename= innerResult.split('/').pop();
  									$(window).attr("location","downloadReportGO.do?filename="+ filename +"&<csrf:token uri='downloadReportGO.do'/>");
  								}
  				 			}
  						}
  				  	},	
  					errorHandler : function(exception) { alert(exception); },
  					async : true  
  			  	});
  	 		}
  	 	},
  	 	errorHandler : function(exception) { alert(exception); },
		async : true
		});
}



buildHierachyMessage=function(){
var obj=$("#localBodyType")[0];	
var stateName=$("#ddSourceState").find('option:selected').text();
 var localBodyTypeElement = $('#localBodyType option[value != ""]');
	 var msgElement=[];
	 var elementIndex=-1;
	 $(localBodyTypeElement).each(function(index){
		 var localbodyTypeArray = $(this).val().split("_");
		 var localbodyTypeCode = localbodyTypeArray[0];
		 var tiersetupCode = localbodyTypeArray[1];
		 var parentTiersetupCode = localbodyTypeArray[2];
		 var category= localbodyTypeArray[4];
		 if((obj.selectedIndex  > index)  && (category!="U") ){
			 
		    var localbodyTypeName = $(this).text();
		    msgElement[++elementIndex]=localbodyTypeName;
		    //alert(localbodyTypeName);
		    if(obj.selectedIndex-1  > index){
		    	
		    	  var localbodyValue=$("#localBodyLevelCodes_"+tiersetupCode+"_"+parentTiersetupCode).find('option:selected').text();
		    	  msgElement[++elementIndex]=localbodyValue;
				    //alert(localbodyValue);
		    } 
		  
		 }else if((category=="U") && (obj.selectedIndex -1 == index) ){
			 var localbodyTypeName = $(this).text();
			    msgElement[++elementIndex]=localbodyTypeName;
		 }
		 
		 
	 }); 
		   //alert(msgElement.length);
		   var msg="";
		   if(parseInt(msgElement.length)==5){
			  msg= msgElement[4]+" of "+msgElement[3]+"("+msgElement[2]+"),"+msgElement[1]+"("+msgElement[0]+"),"+stateName;
		   }else if(parseInt(msgElement.length)==3){
			   msg=msgElement[2]+" of "+msgElement[1]+"("+msgElement[0]+"),"+stateName;
		   }else{
			   msg=msgElement[0]+" of "+stateName;
		   }
		   $("#entitesForMsg").val(msg);
};

</script>
<div class="modal fade" id="dialogBX" tabindex="-1" role="dialog" >
									<div class="modal-dialog" style="width:950px;">
											<div class="modal-content">
								  				<div class="modal-header">
								   				   <button type="button" class="close" data-dismiss="modal">&times;</button>
								    			  	<h4 class="modal-title" id="dialogBXTitle"></h4>
								    			  	
								  				</div>
								  				<div class="modal-body" id="dialogBXbody">
								        			<iframe id="myIframe" width="910" height="650"></iframe>
								        			
								     			 
												</div>
												
									</div>
								</div>
							</div>