<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/dwrRestructuredLocalBodyService.js"></script>
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />

<script>

/**
 * Defined Local Body Ceartion Type and State Code set in Controller Map Attribute. 
 */
var _JS_LOCAL_BODY_CREATION_TYPE = '${PANCHAYAT_TYPE}';
//var _JS_STATE_CODE = '${stateCode}';
var _JS_URBAN_CONSTANT = '${URBAN_PROCESS_CONSTANT}';


var checkNotUrbanProcess = function (){
	var panchayatType='${PANCHAYAT_TYPE}';
	if(panchayatType!='U'){
		return true;
	}
	return false;
};


var _JS_STATE_CODE = '${stateCode}';
var _JS_DISTRICT_CODE = '${districtId}';

$(document).ready(function() {
	if(checkNotUrbanProcess()){
	$("#lbTypeHierarchy").change(function() {
		$( '#divCreateDynamicHierarchy' ).empty();
		$( '#errorLbTypeHierarchy' ).text("");
		$( this ).removeClass("error");
		registerCallLocalBodyType();
	});	
	}
	$("#localBodyType").change(function() {
		$( '#errorLocalBodyType' ).text("");
		$( this ).removeClass("error");
		if(checkNotUrbanProcess()){
		registerCallDynamicHierarchy(this);	
		}else{
			registerCallDynamicHierarchyforUrban(this);
		}
	});

});

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
		dwrRestructuredLocalBodyService.buildLocalBodyHierarchy(parseInt(setupCode), parseInt(setupVersion), {
			callback : populateLBType,
			errorHandler : errorLbTypeProcess,
			async : true
		});
	}
};

/**
 * The {@code errorLbTypeProcess} called when error 
 * occured while DWR call {@link buildLocalBodyHierarchy}.
 */
var errorLbTypeProcess = function() {
	$("#errorLocalBodyType").html("<spring:message code='Error.invalidlbtype' htmlEscape='true'/>");
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
 * The {@code populateLBType} used for build drop-down box with 
 * Local Body Type Values. 
 */
var populateLBType = function(result) {
	var options = $("#localBodyType");
	jQuery.each(result, function(index, obj) {
		var option = $("<option />");
		var setOptValue = obj.localBodyTypeCode + "_" + obj.tierSetupCode + "_" + obj.parentTierSetupCode + "_" + obj.lbLevel;
		option.val(setOptValue).text(obj.name);
		 if (_JS_LOCAL_BODY_CREATION_TYPE != obj.lbType) {
			option.attr("disabled", true);
		} 
		options.append(option);
	});
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
		 if((obj.selectedIndex  > index) && (obj.value != localbodyTypeCode )){
			 
		    var localbodyTypeName = $(this).text();
		    
		    var templateUL = $("<ul/>");
		    templateUL.addClass('form_body');
		    
		    // Added Li Elements for UL
			var templateLI = $("<li/>");
			
		    // Added Label Elements
			var templateLabel = $("<label/>");
			templateLabel.html("<spring:message htmlEscape='true' code='Label.SELECT'/> " + localbodyTypeName + " <span class='mandate'>*</span>");
			
			// Added Html Select Elements
			var templateSelect = $("<select/>");
			templateSelect.attr("id", "localBodyLevelCodes_" + tiersetupCode + "_" + parentTiersetupCode);
			templateSelect.attr("name", "localBodyLevelCodes");
			$(templateSelect).change(function() {
				$( this ).removeClass("error");
				$( '#error' + $( this ).attr('id') ).text("");
				if(index != (obj.selectedIndex - 1)){
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
			templateError.attr("class", "errormessage");
			
			// Adding Dynamic Elements to Template Div.
			templateLI.append(templateLabel);
			templateLI.append(templateSelect);
			templateLI.append(templateError);
			templateUL.append(templateLI);
			divTemplate.append(templateUL);
		 }
	});
};




var registerCallDynamicHierarchyforUrban = function(obj) {
	 $("#divCreateDynamicHierarchy").empty();
	 var divTemplate = $("#divCreateDynamicHierarchy");
	 var isDWRCallRequired = true;
	 var localBodyTypeElement = $('#localBodyType option[value != ""]');
	 
	 $(localBodyTypeElement).each(function(index){
		 var localbodyTypeArray = $(this).val().split("_");
		 var localbodyTypeCode = localbodyTypeArray[0];
		 var tiersetupCode = localbodyTypeArray[1];
		 var parentTiersetupCode = localbodyTypeArray[2];
		 if(((obj.selectedIndex -1) == index)){
			 
		    var localbodyTypeName = $(this).text();
		    
		    var templateUL = $("<ul/>");
		    templateUL.addClass('form_body');
		    
		    // Added Li Elements for UL
			var templateLI = $("<li/>");
			
		    // Added Label Elements
			var templateLabel = $("<label/>");
			templateLabel.html("<spring:message htmlEscape='true' code='Label.SELECT'/> " + localbodyTypeName + " <span class='mandate'>*</span>");
			
			// Added Html Select Elements
			var templateSelect = $("<select/>");
			templateSelect.attr("id", "localBodyLevelCodes_" + tiersetupCode + "_" + parentTiersetupCode);
			templateSelect.attr("name", "localBodyLevelCodes");
			var options = $(templateSelect);
			options.append($("<option />").val("").text("<spring:message htmlEscape='true' code='Label.SELECT'/>"));
			 if(isDWRCallRequired){
				isDWRCallRequired = false;
				buildDistrictPanchayatOptions(options, localbodyTypeCode);
			}
			 
			var templateError = $("<span/>");
			templateError.attr("id", "error" + $(templateSelect).attr('id'));
			templateError.attr("class", "errormessage");
			
			// Adding Dynamic Elements to Template Div.
			templateLI.append(templateLabel);
			templateLI.append(templateSelect);
			templateLI.append(templateError);
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
 * The {@code isDistrictUser} used to check logged-in user   
 * is of district or state.
 */
var isDistrictUser = function (){
	if(_JS_DISTRICT_CODE != null && parseInt(_JS_DISTRICT_CODE) > 0){
		return true;
	} else{
		return false;
	}
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
		async : false
	});	
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
</script>
<c:if test="${showTable eq false and PANCHAYAT_TYPE ne 'U' }">
<script>
$(window).load(function () {
	$('#localBodyType option[value != ""]').remove();
	var _lb_type_value = $('#lbTypeHierarchy option:selected').val();
	if (!$_checkEmptyObject(_lb_type_value)) {
		var optVaues = _lb_type_value.split(',');
		var setupCode = optVaues[0]; // local body setup code
		var setupVersion = optVaues[1]; // local body setup version
		dwrRestructuredLocalBodyService.buildLocalBodyHierarchy(parseInt(setupCode), parseInt(setupVersion), {
			callback : function(result) {
				populateLBType(result);
				$("#localBodyType option[value='${wardForm.paramLocalBodyTypeCode}']").attr("selected", "selected");
				registerCallDynamicHierarchy($('#localBodyType').get(0));
				setTimeout(function(){
					var localbodyLevelCodes =  '${wardForm.localBodyLevelCodes}'.split(",");
					var localBodyLevelElement = $("SELECT[name='localBodyLevelCodes']");
					var elementCount = $(localBodyLevelElement).size()-1;
					$(localBodyLevelElement).each(function(index){
						$("#" + $(this).attr('id') +" option[value='" + localbodyLevelCodes[index] + "']").attr("selected", "selected");
						if(index < elementCount ){
							configureLocalBodyTypeHierarchy(this);	
						}
						if(index == elementCount ){
							var elementId = $(this).attr('id');
							setTimeout(function(){
								$("#" + elementId +" option[value='" + localbodyLevelCodes[index] + "']").attr("selected", "selected");
							},200);
						}
					});
				}, 200);
			},
			errorHandler : errorLbTypeProcess,
			async : true
		});
	}
}); 
</script>
</c:if>

		<div id="divCenterAligned" class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4>Search Criteria</h4>
								<ul class="form_body" >
									     <c:if  test="${PANCHAYAT_TYPE ne 'U'}">
											    <li>
													<label>
														<spring:message code='Label.Selecthierarchylevel' htmlEscape='true'/>
														<span class="mandate">*</span>
													</label>
													<form:select path="lbTypeHierarchy" id="lbTypeHierarchy">
														<c:if test="${fn:length(lbTypeHierarchy) ne 1}">
				   											<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT"/></form:option>
														</c:if>
														<c:forEach items="${lbTypeHierarchy}" var="objHierarchy" >
															<form:option value="${objHierarchy.localBodySetupCode},${objHierarchy.localBodySetupVersion}">
																<c:out value="${objHierarchy.localBodyTypeHierarchy}" escapeXml="true"></c:out>
															</form:option>
														</c:forEach>
													</form:select>
													<span class="errormessage" id="errorLbTypeHierarchy"></span>
												</li>
										</c:if>
									
									<li>
										<label>
											<spring:message code='Label.SELECTLOCALBODYTYPE' htmlEscape='true'/>
											<span class="mandate">*</span>
										</label>
										<form:select path="paramLocalBodyTypeCode" id="localBodyType">
											<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT" ></spring:message></form:option>
											<c:if test="${PANCHAYAT_TYPE eq 'U' }">
												<c:forEach items="${localBodyTypeList}" var="objLBTypes">
													<form:option value="${objLBTypes.localBodyTypeCode}">
														<c:out value="${objLBTypes.name}" escapeXml="true"></c:out>
													</form:option>
												</c:forEach>
											</c:if>
										</form:select>
										<span class="errormessage" id="errorLocalBodyType"></span>
									</li>
									<div id="divCreateDynamicHierarchy">
										<!-- This Div used to generate Hierarchy -->
									</div>
									
									
							