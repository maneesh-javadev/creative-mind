<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/lgdDwrVillageService.js"></script>
<%@include file="CommonIncludeLB.jsp" %>
<script type='text/javascript'>
<!--
	/**
	 * Defined Existing Local Body Div arrays are to be used to build coverage Elements. 
	 */
	var _JS_ARRAY_LB_FULL = [ "firstLevelLBDiv", "secondLevelLBDiv", "thirdLevelLBDiv", "fourthLevelLBDiv" ];
	var _JS_ARRAY_LB_SECOND_LEVEL = [ "firstLevelLBDiv", "thirdLevelLBDiv", "fourthLevelLBDiv" ];
	var _JS_ARRAY_LB_THIRD_LEVEL = [ "firstLevelLBDiv", "fourthLevelLBDiv" ];
	
	/**
	 * Defined Unmapped / Partially Mapped Div arrays are to be used to build coverage Elements. 
	 */
	var _JS_ARRAY_LR_UNMAPPED_FULL = [ "firstLevelUnmappedLRDiv", "secondLevelUnmappedLRDiv", "thirdLevelUnmappedLRDiv" ];
	var _JS_ARRAY_LR_SECOND_LEVEL = [ "secondLevelUnmappedLRDiv", "thirdLevelUnmappedLRDiv" ];
	var _JS_ARRAY_LR_THIRD_LEVEL = [ "thirdLevelUnmappedLRDiv" ];
	
	/**
	 * Defined constants Local Body Level for Panchayats. 
	 */
	var _JS_DISTRICT_PANCHAYAT_LEVEL = "<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.DISTRICT_PANCHAYAT_LEVEL.toString()%>";
	var _JS_INTERMEDIATE_PANCHAYAT_LEVEL = "<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.INTERMEDIATE_PANCHAYAT_LEVEL.toString()%>";
	var _JS_VILLAGE_PANCHAYAT_LEVEL = "<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.VILLAGE_PANCHAYAT_LEVEL.toString()%>";
	var _JS_SUBDISTRICT_CONSTANT = "<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.SUB_DISTRICT_CONSTANT.toString()%>";
	var _JS_SUBDISTRICT_ELEMENT_CONSTANT = "<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.SUB_DISTRICT_START_CONSTANT.toString()%>";
	var _JS_VILLAGE_CONSTANT = "<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.VILLAGE_CONSTANT.toString()%>";
	var _JS_HABITAION_NOT_CONFIGURE_CONSTANT="<%=in.nic.pes.lgd.constant.CommanConstant.HABITHABITATION_PARENT_TYPE_NOT_CONFIGURE.toString()%>";
	/**
	 * Defined mapping array for level of local bodies with display div elements. 
	 */
	var _JS_LB_MAPPING_ARRAY = {D : _JS_ARRAY_LB_FULL, I : _JS_ARRAY_LB_SECOND_LEVEL, V : _JS_ARRAY_LB_THIRD_LEVEL};
	var _JS_LR_MAPPING_ARRAY = {D : _JS_ARRAY_LR_UNMAPPED_FULL, I : _JS_ARRAY_LR_SECOND_LEVEL, V :_JS_ARRAY_LR_THIRD_LEVEL};
	var _STATE_WISE_HABIATION_CONFIGURATION='${habitationConfigration}'; 
	
	/**
	 * The {@code ready} initialized once page started excuting 
	 * and invoke all on load events.
	 */ 
	$(document).ready(function() {
		
		if(isParseJson('${!checkedServerValidation}')){
			if('${fn:length(lbTypeHierarchy) eq 1}'){
				registerCallLocalBodyType();
			}
		}
		$("#lbTypeHierarchy").change(function() {
			$( '#divCreateDynamicHierarchy' ).empty();
			$( this ).removeClass("error");
			$( '#errorLbTypeHierarchy' ).text("");
			resetCoverageDetails(true, true);
			checkUncheckBox(false, false);
			registerCallLocalBodyType();
			if(isParseJson( '${modifyProcess}' )){
				resetCoverageforModification();
			}
		});
		$( "INPUT[id^='checkboxCoverage']" ).click(function() {
			$( "#lbTypeHierarchy, #localBodyType" ).removeClass( "error" );
			$( "label[for='lbTypeHierarchy']" ).text("");
			$( "label[for='localBodyType']" ).text("");
			resetHeadQuarter();
			var checkboxValue = $(this).attr('param');
			buildPRICoverageDetailsEvents(checkboxValue);
		});
		$("#localBodyType").change(function() {
			$( this ).removeClass("error");
			$( '#errorLocalBodyType' ).text("");
			resetCoverageDetails(true, true);
			checkUncheckBox(false, false);
			registerCallDynamicHierarchy(this);
			checkMapUpload($( this ).val().split('_')[1]);
			if(isParseJson( '${modifyProcess}' )){
				resetCoverageforModification();
			}
			showHideHabitationDiv($( this ).val().split('_')[0]);
		});
		$( '[id = btnEventCoverage]' ).click(function() {
			resetHeadQuarter();
			resetCoverageAreas($(this).attr('level'), $(this).attr('paramLBLR'));
			var sourceElement = 'coverage' + $(this).attr('paramLBLR') + 'Available' +  $(this).attr('level');
			var destElement   = 'coverage' + $(this).attr('paramLBLR') + 'Contributing' +  $(this).attr('level');
			var callMovingElements = {
				    "Whole": function(sourceElement, destElement) {
						if(validatePartMapping(sourceElement)){
							customAlert("<spring:message code='Error.partlandrgnvalidation' htmlEscape='true'/>");
							return false;	
						}
						moveElements(sourceElement, destElement, 'FULL', false, false);
					},
				    "Back": function(sourceElement, destElement, level, lb_lr_code) {
				    	moveElements(destElement, sourceElement, null, true, false);
				    	hideHabitationDiv();
				    },
				    "Reset": function(sourceElement, destElement, level, lb_lr_code) {
				    	moveElements(destElement, sourceElement, null, false, true);
				    	hideHabitationDiv();
				    },
				    "Part": function(sourceElement, destElement, level, lb_lr_code) {
				    	moveElements(sourceElement, destElement, 'PART', false, false);
				    }
			}; 
			return callMovingElements[ $(this).attr('param') ](sourceElement, destElement);
		});
		$("#fetchCoverageofLocalBody").click(function() {
			resetCoverageAreas($(this).attr('level'), $(this).attr('paramLBLR'));
			fetchCoverageDetailsForLB();
		});
		$('[id^=btnFetchCoverage]').click(function() {
			resetCoverageAreas($(this).attr('level'), $(this).attr('paramLBLR'));
			fetchCoverageDetailsForLandRegions( this );
		});
		$("#actionExistingGO").click(function() {
			selectExistingGovernmentOrder();
		});
		$("#actionBuildHeadQuarter").click(function() {
			buildHeadQuarter();
		});
		
		$("#actionBuildHabitation").click(function() {
			buildHabitation();
		});
	});
	
	/**
	 * The {@code getLocalBodyTypeLevel} used to get the level of panchayat
	 * local body type belongs to.
	 */ 
	var getLocalBodyTypeLevel = function (){
		return $( '#localBodyType' ).val().split('_')[3];
	};
	
	/**
	 * The {@code resetHeadQuarter} used to reset Head Quarter Details.
	 * Hide Head Quarter Table from User Interface.
	 */ 
	var resetHeadQuarter = function (){
		$( '#tblHeadquarter > tbody' ).empty();
		$( "#tblHeadquarter" ).hide();
	};
	
	/**
	 * The {@code resetCoverageAreas} used to call reset method  to clear option boxes
	 * based on their access level.
	 * @param COVERAGE_LAVEL
	 * @param LB_LR_LABEL
	 */ 
	var resetCoverageAreas =  function (COVERAGE_LAVEL, LB_LR_LABEL) {
		var callRemoveOptionElements = {
				"PanchayatLevel": function() {
					levelWiseClearOptions(["DisttLevel", "SubdisttLevel", "VillageLevel"], LB_LR_LABEL);
				},
			    "DisttLevel": function() {
			    	levelWiseClearOptions(["SubdisttLevel", "VillageLevel"], LB_LR_LABEL);
			    },
			    "SubdisttLevel": function() {
			    	levelWiseClearOptions(["VillageLevel"], LB_LR_LABEL);
			    },
			    "VillageLevel": function() {
			    	// Do not remove this block (In Use)
			    },
			    "UnmappedDisttLevel": function() {
			    	levelWiseClearOptions(["IntermediateLevel","VillageLevel"], LB_LR_LABEL);
			    },
			    "UnmappedIntermediateLevel": function() {
			    	levelWiseClearOptions(["VillageLevel"], LB_LR_LABEL);
			    },
			    "UnmappedVillageLevel": function() {
			    	// Do not remove this block (In Use)
			    }
		};
		callRemoveOptionElements[ COVERAGE_LAVEL ]();
	};
	
	/**
	 * The {@code showCoverageElements} used to display all elemets assigned in input 
	 * parameter array.
	 * @param _PARAM_SHOW_ARRAY
	 */ 
	var showCoverageElements = function ( _PARAM_SHOW_ARRAY ){
		jQuery.each(_PARAM_SHOW_ARRAY, function(index, val) {
			$("#" + val).show();
		});
	};
	
	/**
	 * The {@code showCoverageElements} used to hide all elemets assigned in input 
	 * parameter array.
	 * @param _PARAM_HIDE_ARRAY
	 */
	var hideCoverageElements = function ( _PARAM_HIDE_ARRAY ){
		jQuery.each(_PARAM_HIDE_ARRAY, function(index, val) {
			$("#" + val).hide();
		});
	};
	
	/**
	 * The {@code resetCoverageDetails} function used to reset 
	 * coverage details display elements.
	 */
	var resetCoverageDetails = function ( IS_LB_HIDE, IS_LR_UNMAPPED_HIDE ) {
		 // Hide all Existing Local Bodies Div Elements.
		 if(IS_LB_HIDE) {
			 $( "SELECT[id^='coverageLB']" ).empty();
			 hideCoverageElements( _JS_ARRAY_LB_FULL );
		 };
		 // Hide all Upmapped / Partially Mapped Land Region Div Elements.
		 if(IS_LR_UNMAPPED_HIDE) {
			 $( "SELECT[id^='coverageLR']" ).empty();
			 hideCoverageElements( _JS_ARRAY_LR_UNMAPPED_FULL );
		};
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
					$( '#error' + $( this ).attr('id') ).text("<spring:message code='Label.SELECTLOCALBODY' htmlEscape='true'/>");	 
				 }
			 });
			 return false;
		}
		return true; 
	};
	
	/**
	 * The {@code buildDistrictPanchayatOptions} used create select box options at 
	 * District Panchayat Level. 
	 * @param elementTemplate (Element Template for given select box instance)
	 * @param localbodyTypeCode
	 */
	var buildDistrictPanchayatOptions = function(elementTemplate, localbodyTypeCode) {
		 if(isDistrictUser()){
			dwrRestructuredLocalBodyService.getDistrictPanchayatListForDistrictUser(parseInt(localbodyTypeCode), parseInt(_JS_DISTRICT_CODE), getDraftTempCode(), getCreateLBProcessId(), {
				callback : callbackHandlerForDPOptions,
				callbackArg : elementTemplate,
				async : true
			});	
		}else{
			dwrRestructuredLocalBodyService.getDistrictPanchayatList(parseInt(localbodyTypeCode), parseInt(_JS_STATE_CODE), getDraftTempCode(), getCreateLBProcessId(),  {
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
		dwrRestructuredLocalBodyService.getParentwiseLocalBody(parseInt(localBodyCode), getDraftTempCode(), getCreateLBProcessId(), {
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
	
	/**
	 * The {@code buildDistrictPanchayatOptions} used create select box options at 
	 * District Panchayat Level. 
	 * @param elementTemplate (Element Template for given select box instance)
	 * @param localbodyTypeCode
	 */
	var buildUnmappedLandRegionOptions = function (elementTemplate, localbodyTypeLevel) {
		localbodyTypeLevel = ( localbodyTypeLevel == _JS_INTERMEDIATE_PANCHAYAT_LEVEL ) ? _JS_SUBDISTRICT_CONSTANT : localbodyTypeLevel;
		dwrRestructuredLocalBodyService.getUnmappedLandRegions(localbodyTypeLevel, parseInt(_JS_STATE_CODE), parseInt(_JS_DISTRICT_CODE), getDraftTempCode(), getCreateLBProcessId(), {
			callback : function(result){
				jQuery.each(result, function(index, obj) {
					var optionText = obj.landRegionNameEnglish;
					var option = $("<option />");
					if (obj.isUsed) {
						option.attr("disabled", true);
						optionText = optionText.concat(" <spring:message code='Label.draftlandregion' htmlEscape='true'/>");
					}
					option.val(obj.landRegionCode).text(optionText);
					elementTemplate.append(option);
				});
			},
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
			dwrRestructuredLocalBodyService.buildLocalBodyHierarchy(parseInt(setupCode), parseInt(setupVersion), {
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
			var setOptValue = obj.localBodyTypeCode + "_" + obj.tierSetupCode + "_" +obj.parentTierSetupCode + "_" + obj.lbLevel;
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
			 if((obj.selectedIndex - 1 > index) && (obj.value != localbodyTypeCode )){
			    var localbodyTypeName = $(this).text();
			    
			    var templateDivv = $("<div class='form-group'/>");
			    // Added Li Elements for UL
				var templateLI = $("<li/>");
				
			    // Added Label Elements
				var templateLabel = $("<label class='col-sm-3 control-label'/>");
				templateLabel.html("<spring:message htmlEscape='true' code='Label.SELECT'/> " + localbodyTypeName);
				
				var templateSubDivv = $("<div class='col-sm-6'/>");
				// Added Html Select Elements
				var templateSelect = $("<select class='form-control'/>");
				templateSelect.attr("id", "localBodyLevelCodes_" + tiersetupCode + "_" + parentTiersetupCode);
				templateSelect.attr("name", "localBodyLevelCodes");
				$(templateSelect).change(function() {
					$( this ).removeClass("error");
					$( '#error' + $( this ).attr('id') ).text("");
					resetHeadQuarter();
					resetCoverageDetails(true, true);
					checkUncheckBox(false, false);
					if(isParseJson( '${modifyProcess}' )){
						resetCoverageforModification();
					}
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
				templateError.attr("class", "errormessage");
				
				// Adding Dynamic Elements to Template Div.
				templateDivv.append(templateLabel);
				templateSubDivv.append(templateSelect);
				templateSubDivv.append(templateError);
				templateDivv.append(templateSubDivv);
				divTemplate.append(templateDivv);
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
	
	
	var buildPRICoverageDetailsEvents = function(checkboxCoverageValue){
		
		var lbTypeHierarchylement = $( '#lbTypeHierarchy' );
		if( $_checkEmptyObject($( lbTypeHierarchylement ).val()) ){
			$(lbTypeHierarchylement).addClass("error");
			$( '#errorLbTypeHierarchy' ).text("<spring:message code='error.CHOOSEHIERARCHY' htmlEscape='true'/>");
			return false;
		}
		
		var localBodyTypeElement = $( '#localBodyType' );
		var selectedlocalBodyType = $( localBodyTypeElement ).val();
		if( $_checkEmptyObject(selectedlocalBodyType) ){
			$(localBodyTypeElement).addClass("error");
			$( '#errorLocalBodyType' ).text("<spring:message code='error.select.LBTYPE' htmlEscape='true'/>");
			return false;
		}
		
		var lbTypeArray = selectedlocalBodyType.split("_");
		var lbTypeCode  = lbTypeArray[0];
		var lbTypeLevel = lbTypeArray[3];
		var element = $( '[name = localBodyLevelCodes]' );
		var localBodyElement = $( element )[$( element ).length - 1];
		
		var callCheckboxEventCoverage = {
				"LB_COVERAGE": function(localBodyElement, lbTypeCode, lbTypeLevel) {
					$('#labelAvailablePanchayat, #labelContributingPanchayat').empty();
					resetCoverageDetails(true, null);
					if( $( '#checkboxCoverageLB' ).is( ':checked' ) ) {
						var selectedLBTypeText = $('#localBodyType option:selected').text() + ' <spring:message htmlEscape="true" code="Label.LIST"/>';
						$('#labelAvailablePanchayat').append('<spring:message htmlEscape="true" code="Label.AVAILABLE"/> ' + selectedLBTypeText);
						$('#labelContributingPanchayat').append('<spring:message htmlEscape="true" code="Label.CONTRIBUTE"/> ' + selectedLBTypeText);
						if (jQuery.type( localBodyElement ) === "undefined") {
							buildDistrictPanchayatOptions( $( '#coverageLBAvailablePanchayatLevel' ), lbTypeCode );
						} else {
							if(!validateLBCode(localBodyElement)){
								$( '#checkboxCoverageLB' ).prop('checked', false);
								return false;
							} 
							buildParentwisePanchayatOptions('coverageLBAvailablePanchayatLevel', $(localBodyElement).val());
						}
						showCoverageElements( $.makeArray( _JS_LB_MAPPING_ARRAY [lbTypeLevel] ) );
					}	
			    },
			    "UNMAPPED_COVERAGE": function(localBodyElement, lbTypeCode, lbTypeLevel) {
			    	resetCoverageDetails(null, true);
					if( $( '#checkboxCoverageUnmapped' ).is( ':checked' ) ) {
						var unmappedElement = $( "SELECT[id^='coverageLRAvailableUnmapped" + lbTypeLevel + "']" );
						if (!(jQuery.type( localBodyElement ) === "undefined")) {
							 if(!validateLBCode(localBodyElement)){
								 $( '#checkboxCoverageUnmapped' ).prop('checked', false);
								 return false;
							 }
						} 
						buildUnmappedLandRegionOptions( unmappedElement , lbTypeLevel );
						showCoverageElements( $.makeArray( _JS_LR_MAPPING_ARRAY [lbTypeLevel]) );
					}	
			    }
		}; 
		callCheckboxEventCoverage[ checkboxCoverageValue ](localBodyElement, lbTypeCode, lbTypeLevel);
	};
	
	var fetchCoverageDetailsForLB = function () {
		var lbTypeLevel = getLocalBodyTypeLevel();
		var options = $( "SELECT[id^='coverageLBAvailable" + (lbTypeLevel == _JS_INTERMEDIATE_PANCHAYAT_LEVEL ? _JS_SUBDISTRICT_ELEMENT_CONSTANT : lbTypeLevel) + "']" );
		setSelectedOptionBox(['coverageLBContributingPanchayatLevel']);
		var localbodyCodes = convertCodeArray( $( '#coverageLBContributingPanchayatLevel' ).val());
		if(!jQuery.isEmptyObject(localbodyCodes)){	
			dwrRestructuredLocalBodyService.fetchCoverageDetailsLocalBody(localbodyCodes.toString(), ( lbTypeLevel == _JS_INTERMEDIATE_PANCHAYAT_LEVEL ) ? _JS_SUBDISTRICT_CONSTANT : lbTypeLevel,  {
				callback : function( result ) {
					jQuery.each(result, function(index, obj) {
						var option = $("<option />");
						var lrName = concatPartFull(obj.coverageType, obj.landRegionNameEnglish);
						option.val(obj.landRegionCode).text(lrName);
						if(! $_checkEmptyObject(obj.localbodyCoverage) ){
							option.attr('paramLBCode', obj.localbodyCoverage.split(":")[0]);
						}
						options.append(option);
					});
				},
				errorHandler : commonErrorProcess,
				async : true
			});	
		}
	};
	
	var fetchCoverageDetailsForLandRegions = function ( buttonObject ) {
		var btnCoverageLRValue = $( buttonObject ).attr( 'param' );
		var callButtonEventCoverage = {
				"LB_COVERAGE_DISTRICT": function () {
					setSelectedOptionBox(['coverageLBContributingPanchayatLevel', 'coverageLBContributingDisttLevel']);
					var selectedLBCodes = convertCodeArray($('#coverageLBContributingPanchayatLevel').val());
					var landRegionCodes = convertCodeArray($("#coverageLBContributingDisttLevel").val());
					buildLocalBodyCoveredAreas(selectedLBCodes, _JS_SUBDISTRICT_CONSTANT, landRegionCodes, 'coverageLBAvailableSubdisttLevel');
			    },
			    "LB_COVERAGE_SUB_DISTRICT": function(localBodyCodes) {
			    	setSelectedOptionBox(['coverageLBContributingPanchayatLevel', 'coverageLBContributingSubdisttLevel']);
			    	var selectedLBCodes = convertCodeArray($('#coverageLBContributingPanchayatLevel').val());
			    	var landRegionCodes = convertCodeArray($('#coverageLBContributingSubdisttLevel').val() );
			    	buildLocalBodyCoveredAreas(selectedLBCodes, _JS_VILLAGE_CONSTANT, landRegionCodes, 'coverageLBAvailableVillageLevel');
			    },
			    "LB_COVERAGE_UNMAPPED_DISTRICT": function() {
			    	setSelectedOptionBox(['coverageLRContributingUnmappedDisttLevel']);
			    	var lbTypeLevel = getLocalBodyTypeLevel(); //$( '#localBodyType' ).val().split('_')[3];
			    	var selectedLBCodes = convertCodeArray($('#coverageLRContributingUnmappedDisttLevel').val());
			    	buildLandRegionCoveredAreas(_JS_SUBDISTRICT_CONSTANT, selectedLBCodes, lbTypeLevel, 'coverageLRAvailableUnmappedIntermediateLevel');
			    },
			    "LB_COVERAGE_UNMAPPED_SUB_DISTRICT": function() {
			    	setSelectedOptionBox(['coverageLRContributingUnmappedIntermediateLevel']);
			    	var lbTypeLevel = getLocalBodyTypeLevel();// $( '#localBodyType' ).val().split('_')[3];
			    	var selectedLBCodes = convertCodeArray($('#coverageLRContributingUnmappedIntermediateLevel').val());
			    	buildLandRegionCoveredAreas(_JS_VILLAGE_CONSTANT, selectedLBCodes, lbTypeLevel, 'coverageLRAvailableUnmappedVillageLevel');
			    }
		}; 
		callButtonEventCoverage[ btnCoverageLRValue ]();
	};
	
	/**
	 * The {@code buildHeadQuarter} used to call dynamic Head Quarter build up.
	 */ 
	var buildHeadQuarter = function (){
		 $( '#tblHeadquarter > tbody' ).empty();
		 var lbTypeLevel = getLocalBodyTypeLevel();
		var lbCoverageLevel = null;
		var lrCoverageLevel = null;
		var hqHeading = null;
		if( _JS_DISTRICT_PANCHAYAT_LEVEL == lbTypeLevel ) {
			lbCoverageLevel = "DisttLevel";
			lrCoverageLevel = "UnmappedDisttLevel";
			hqHeading = "<spring:message code='Label.CONTRIBUTDISTRICTLIST' htmlEscape='true'/>";
		} else if( _JS_INTERMEDIATE_PANCHAYAT_LEVEL ==  lbTypeLevel ) {
			lbCoverageLevel = "SubdisttLevel";
			lrCoverageLevel = "UnmappedIntermediateLevel";
			hqHeading = "<spring:message code='Label.CONTRIBUTESUBDISTRICTLIST' htmlEscape='true'/>";
		} else if ( _JS_VILLAGE_PANCHAYAT_LEVEL ==  lbTypeLevel ) {
			lbCoverageLevel = "VillageLevel";
			lrCoverageLevel = "UnmappedVillageLevel";
			hqHeading = "<spring:message code='Label.CONTRIBUTVILLAGELIST' htmlEscape='true'/>";
		}
		if( !$_checkEmptyObject(lbCoverageLevel) && !$_checkEmptyObject(lrCoverageLevel) ){
			$( '#lblHQAtLevel' ).html( hqHeading );
			
			// Generating Template for Fully Mapped Local Bodies.
			setSelectedOptionBox(['coverageLBContributingPanchayatLevel']);
			var localbodyCodes = convertCodeArrayFULL( $( '#coverageLBContributingPanchayatLevel' ).val());
			if(!jQuery.isEmptyObject(localbodyCodes)){	
				dwrRestructuredLocalBodyService.fetchCoverageDetailsLocalBody(localbodyCodes.toString(), ( lbTypeLevel == _JS_INTERMEDIATE_PANCHAYAT_LEVEL ) ? _JS_SUBDISTRICT_CONSTANT : lbTypeLevel,  {
					callback : function( result ) {
						jQuery.each(result, function(index, obj) {
							createHQTable(obj.landRegionCode, concatPartFull(obj.coverageType, obj.landRegionNameEnglish));
							var hqElement = '#tblHeadquarter';
							if($(hqElement + ' >tbody >tr').length > 0){
								$( hqElement ).show();
							}
						});
					},
					errorHandler : function() {
						$( '#tblHeadquarter > tbody' ).empty();
						commonErrorProcess();
					},
					async : true
				});	
			}
			// Generating Template for Partially Mapped Land Regions.
			setSelectedOptionBox(['coverageLBContributing' + lbCoverageLevel, 'coverageLRContributing' + lrCoverageLevel]);
			var lbCoveragesElements = $( "#coverageLBContributing" + lbCoverageLevel + " option:selected" );
			var lrCoveragesElements = $( "#coverageLRContributing" + lrCoverageLevel + " option:selected" );
			if(!$_checkEmptyObject($(lbCoveragesElements).val()) || !$_checkEmptyObject($(lrCoveragesElements).val())){
				callHQPartTemplate(lbCoveragesElements);
				callHQPartTemplate(lrCoveragesElements);
			}	
			var hqElement = '#tblHeadquarter';
			if($(hqElement + ' >tbody >tr').length > 0){
				$( hqElement ).show();
			}
		}
	};
	
	/**
	 * The {@code callHQPartTemplate} used to build table template for head quarter using partially selected LRs
	 * and generate tbody elements for HQ table.
	 * @param lbCoveragesObjects
	 */ 
	var callHQPartTemplate = function (lbCoveragesObjects){
		if(!jQuery.isEmptyObject(lbCoveragesObjects)){
			jQuery.each($.makeArray( lbCoveragesObjects ), function(index, lbSelectElements) {
				createHQTable($(lbSelectElements).val().replace('_PART', '').replace('_FULL', ''), $(lbSelectElements).text());
			});
		}
	}; 
	
	/**
	 * The {@code createHQTable} used to build table template for head quarter
	 * and generate tbody elements for HQ table.
	 * @param value (radio button value)
	 * @param text (Land Region Name)
	 */ 
	var createHQTable = function (value, text){
		var templateTR = $("<tr/>");
		var templateTDHQId = $("<td/>");
		var templateRadio = $( "<input />" );
		templateRadio.attr("type", "radio");
		templateRadio.attr("name", "headQuarterCode");
		$( templateRadio ).val(value);
		$( templateTDHQId ).append(templateRadio);
		var templateTDHQName = $("<td/>");
		$( templateTDHQName ).html(text);
		$( templateTR ).append( templateTDHQId ).append( templateTDHQName );
		$( '#tbodyHQuarter' ).append( templateTR );
	};
		
	var validateLBLRCoverage = function (){
		var lbTypeLevel = getLocalBodyTypeLevel();
		if($("#checkboxCoverageLB").is( ':checked' )){
			setSelectedOptionBox(['coverageLBContributingPanchayatLevel']);
			$('#coverageLBContributingPanchayatLevel' ).rules( "add", {
				required : true,  
				onefull : true,
				messages: {
			 		required: "<spring:message code='Error.contributingpanchayat' htmlEscape='true'/>",
			 		onefull : "<spring:message code='Error.fulllocalbdyvalidation' htmlEscape='true'/>"
				}
			});
			var selectedContributingLBs = $( '#coverageLBContributingPanchayatLevel' ).val();
			var lbPartCodes = convertCodeArray( selectedContributingLBs );
			if($(lbPartCodes).size() > 0){
				setErrorLevelLB(lbTypeLevel == _JS_INTERMEDIATE_PANCHAYAT_LEVEL ? _JS_SUBDISTRICT_ELEMENT_CONSTANT : lbTypeLevel);	
			}
		}
		if($("#checkboxCoverageUnmapped").is( ':checked' )){
			setErrorLevelLR(lbTypeLevel);
		}
	};
	
	var setErrorLevelLB = function ( lbTypeLevel ){
		var elements = $("SELECT[id^='coverageLBContributing" + lbTypeLevel + "']"); 
		$(elements).each(function(index){
			$( this ).rules( "add", {
				required : true,  
				contributeFromAllLB : true,
				contributeAllFull : true,
	 			messages: {
	 				required : "<spring:message code='Error.coveragevalidation' htmlEscape='true'/>",
	 				contributeFromAllLB : "<spring:message code='Error.selectcovragevalidation' htmlEscape='true'/>",
	 				contributeAllFull : "<spring:message code='Error.partlocalbdyvalidation' htmlEscape='true'/>"
				}
			});
		}); 
	 };
	 
	 var setErrorLevelLR = function ( lbTypeLevel ){
		 var elements = $("SELECT[id^='coverageLRContributingUnmapped" + lbTypeLevel + "']");
		 $(elements).each(function(index){
			setSelectedOptionBox([$(this).attr('id')]);
			$( this ).rules( "add", {
				required : true,  
				messages: {
	 				required : "<spring:message code='Error.coveragevalidation' htmlEscape='true'/>",
	 			}
			});
		}); 
	 };	
	 
	 var setErrorHQ = function (){
		 $( '#showHQError' ).rules( "add", {
			requiredHQ : true,  
			messages: {
				requiredHQ : "<spring:message code='Error.headquarter' htmlEscape='true'/>",
 			}
		});
	 };
	 
	
	showHideHabitationDiv = function(type) {
	
		if (!$_checkEmptyObject(type) && parseInt(type) == 3) {

			if (_STATE_WISE_HABIATION_CONFIGURATION == _JS_HABITAION_NOT_CONFIGURE_CONSTANT || _STATE_WISE_HABIATION_CONFIGURATION == _JS_VILLAGE_CONSTANT) {
				$("#habitationDiv").show();
			} else {
				$("#habitationDiv").hide();
			}

		} else {
			$("#habitationDiv").hide();
		}

	};

	buildHabitation = function() {
		if (_STATE_WISE_HABIATION_CONFIGURATION == _JS_HABITAION_NOT_CONFIGURE_CONSTANT) {
			customAlert("Habitations configuration is not  defined in the State, Kindly configure it using configure habitations form available in the configure section.");
		} else {
			partlycovVillage = new Array();
			$('#coverageLBContributingVillageLevel option').each(function() {
				var village_arr = $(this).val().split("_");
				if (village_arr[1] == "PART") {
					partlycovVillage.push(village_arr[0]);
				}
			});

			$('#coverageLRContributingUnmappedVillageLevel option').each(
					function() {
						var village_arr = $(this).val().split("_");
						if (village_arr[1] == "PART") {
							partlycovVillage.push(village_arr[0]);
						}
					});
			removeAllOptions('contributingHabiationCodes');
			removeAllOptions('avilableHabitation');
			if (partlycovVillage.length > 0) {
				$("#habitationListDiv").show();
				lgdDwrVillageService.getHabitationList(partlycovVillage.toString(), {
					callback : function(result) {
						var options = $("#avilableHabitation");
							jQuery.each(result, function(index, obj) {
							var _val=obj.habitationCode+"_N";
							var option = $("<option />");
							option.val(_val).text(obj.habitationNameEnglish);
							options.append(option);
						});
					},
					errorHandler : function(errorString, exception) {
						alert(errorString + " dwr");
						alert(exception);
					},
					async : true
				});
			} else {
				$("#habitationListDiv").hide();
			}
		}

	};

	/**
	 * The {@code moveElement} function used move items from one listbox to another,
	 * based on copyid,pasteId and action(parameter)
	 */
	moveElement = function(action) {
		var copyId = null;
		var pasteId = null;
		if (action == "FORWARD" || action == "WHOLE") {
			copyId = 'avilableHabitation';
			pasteId = 'contributingHabiationCodes';
		} else if (action == "BACK" || action == "BACKALL") {
			copyId = 'contributingHabiationCodes';
			pasteId = 'avilableHabitation';
		}

		if (action == "WHOLE" || action == "BACKALL") {
			$('#' + copyId + ' option').clone().appendTo('#' + pasteId);
			removeAllOptions(copyId);
		} else if (action == "BACK" || action == "FORWARD") {
			$('#' + copyId + '> option:selected').appendTo('#' + pasteId);
		}
		sortListBox(copyId);
		sortListBox(pasteId);

	};

	/* The {@code removeAllOptions} used to clear drop down box based on their id. 
	 */
	removeAllOptions = function(id) {
		dwr.util.removeAllOptions(id);
	};
	
	hideHabitationDiv=function(){
		removeAllOptions('contributingHabiationCodes');
		removeAllOptions('avilableHabitation');
		var habitationListDiv=$("#habitationListDiv");
		if(habitationListDiv.length>0){
			$(habitationListDiv).hide();
		}
		
	};
	
	 /**
	  * The {@code sortListBox} function used sort items of listbox ,
	  * based on listbox id
	  */
	 sortListBox=function(id){
	 	 var $r = $("#"+id+" option");
	     $r.sort(function(a, b) {
	         if (a.text < b.text) return -1;
	         if (a.text == b.text) return 0;
	         return 1;
	     });
	     $($r).remove();
	     $("#"+id).append($($r));
	     
	     
	 };
	 
//-->
</script>

<c:if test="${checkedServerValidation}"> 
	<script type='text/javascript'>
	
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
						$("#localBodyType option[value='${localBodyForm.localBodyTypePanchayat}']").attr("selected", "selected");
						registerCallDynamicHierarchy($('#localBodyType').get(0));
						checkMapUpload($( "#localBodyType option:selected" ).val().split('_')[1]);
						setTimeout(function(){
							var localbodyLevelCodes =  '${localBodyForm.localBodyLevelCodes}'.split(",");
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
						
						if(isParseJson( '${!modifyProcess}' )){
							setTimeout(function(){
								if(isParseJson('${localBodyForm.checkboxCoverageLB}')){
									var checkboxLBElement = $('#checkboxCoverageLB');
									$(checkboxLBElement).prop('checked', '${localBodyForm.checkboxCoverageLB}');
									var checkboxValue = $(checkboxLBElement).attr('param');
									buildPRICoverageDetailsEvents(checkboxValue);	
								}
								
								if(isParseJson('${localBodyForm.checkboxCoverageUnmapped}')){
									var checkboxUnmappedElement = $('#checkboxCoverageUnmapped');
									$(checkboxUnmappedElement).prop('checked', '${localBodyForm.checkboxCoverageUnmapped}');
									var checkboxValue = $(checkboxUnmappedElement).attr('param');
									buildPRICoverageDetailsEvents(checkboxValue);	
								}
							}, 500);
						}
					},
					errorHandler : errorLbTypeProcess,
					async : true
				});
			}
			latitudelongitudeOnload();
		}); 
		
		
		
	</script>
</c:if> 