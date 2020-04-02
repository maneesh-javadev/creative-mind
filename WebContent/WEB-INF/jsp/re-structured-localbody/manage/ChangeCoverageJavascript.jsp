<%@include file="../CommonClientRules.jsp" %>
<script type='text/javascript'>

	var _JS_LOCAL_BODY_CREATION_TYPE = '${LOCAL_BODY_CREATION_TYPE}';
	
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
	
	/**
	 * Defined mapping array for level of local bodies with display div elements. 
	 */
	var _JS_LB_MAPPING_ARRAY = {D : _JS_ARRAY_LB_FULL, I : _JS_ARRAY_LB_SECOND_LEVEL, V : _JS_ARRAY_LB_THIRD_LEVEL};
	var _JS_LR_MAPPING_ARRAY = {D : _JS_ARRAY_LR_UNMAPPED_FULL, I : _JS_ARRAY_LR_SECOND_LEVEL, V :_JS_ARRAY_LR_THIRD_LEVEL};
	
	var removedLandRegions = [];
	
	
	
	/**
	 * The {@code ready} initialized once page started excuting 
	 * and invoke all on load events.
	 */ 
	$(document).ready(function() {
		
		 /*
		  *  For Preview GIS Coverage#started
		 */
		
		 $("#btnPreviewGIS").click(function() {
			  previewCOVERAGEInGIsFn(); 
		  });
		 /*
		  *  For Preview GIS Coverage#end
		 */
		 
		
		  
		
		$( "INPUT[id^='checkboxCoverage']" ).click(function() {
			resetHeadQuarter();
			var checkboxValue = $(this).attr('param');
			buildPRICoverageDetailsEvents(checkboxValue);
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
				    },
				    "Reset": function(sourceElement, destElement, level, lb_lr_code) {
				    	moveElements(destElement, sourceElement, null, false, true);
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
		
		$( "img[id^='remove']" ).click(function() {
			removeExistingCoverages($(this).attr('param'));
			
		});
		
		$( 'INPUT[id^=btnFormAction]' ).click(function() {
			resetValidations();
	 		$( 'SELECT[name^=contributing] option').attr("selected", "selected");
	 		var rcoverages = $( '#removedLangRegionCodes' ).val();
	 		if(! $_checkEmptyObject(rcoverages)){
	 			removedLandRegions.push(rcoverages);	
	 		}
	 		$( '#removedLangRegionCodes' ).val(removedLandRegions);
	 		$('[name = processAction]').val($(this).attr('param'));
	 		validationForm();
		   
		});
		
		$("#btnResetExistingCoverages").click(function() {
			resetCoverageforModification();
		});
		
		$("#localBodyForm").validate({
            ignoreTitle: true ,
            submitHandler: function (form) {
            	if ( ! hasFormError ){
            		disableAllButtons();
					form.submit();
    			}
   			},
            invalidHandler: function(form, validator) {
                var errors = validator.numberOfInvalids();
                if (errors) {
                  var message = errors == 1
                    ? 'Please correct the following error:\n'
                    : 'Please correct the following ' + errors + ' errors.\n';
                  var errors = "";
                  if (validator.errorList.length > 0) {
                      for (var x = 0; x < validator.errorList.length; x++) {
                          errors += "\n\u25CF " + validator.errorList[x].message;
                      }
                  }
                  //alert(message + errors);
                  hasFormError = true;
                }
              } 
        }); 
		validationForm();
	});

	 var resetCoverageforModification = function (){
			$( '#divExistingDraftedLBCoverages, #btnResetExistingCoverages' ).hide();
			$( '#divCompleteLBCoverages, #divCompleteLBHeardQuarter' ).show();
			$( '[name = isResetedCoverage]' ).val(true);
			$( '[name = contributingLBCodes]' ).val("");
			$( '#tblHeadquarter > tbody' ).empty();
	 }; 
	 
	 var resetValidations = function() {
		 $( "span[id^=error]" ).each(function(){
				$( this ).text("");
		 });
		 var checkboxCoverageElement = $( "INPUT[id^='checkboxCoverage']" );
		 $( checkboxCoverageElement ).each(function(index){
	    	$( this ).rules( "remove" );
			$( this ).removeClass( "error" );
		 });
	     $( "[id^=coverageLBContributing]" ).each(function( ) {
	    	$( this ).rules( "remove" );
			$( this ).removeClass( "error" );
	     });
	     $( "[id^=coverageLRContributingUnmapped]" ).each(function( ) {
	    	$( this ).rules( "remove" );
			$( this ).removeClass( "error" );
	    });
	    <c:if test="${isGovernmentOrderUpload}">
 			if(!$_checkEmptyObject($("#orderCode").val())){
 				$( "#gazettePublicationUpload" ).rules( "remove" );
				$( "#gazettePublicationUpload" ).removeClass( "error" );
 			}
 		</c:if>
	 };

	 var buildPRICoverageDetailsEvents = function(checkboxCoverageValue){
			var localBodyCode = $( '#paramLBCode' ).val();
			var lbTypeCode  = $( '#localBodyTypeCode' ).val();
			var lbTypeLevel = '${localBodyTypeLevel}';
			var callCheckboxEventCoverage = {
					"LB_COVERAGE": function(localBodyCode, lbTypeCode, lbTypeLevel) { 
						resetCoverageDetails(true, null);
						if( $( '#checkboxCoverageLB' ).is( ':checked' ) ) {
							var _js_parent_code = parseInt( '${clientParentCode}' ); //localBodyForm.parentLocalBodyCode
							if ( _js_parent_code == 0 ) {
								buildDistrictPanchayatOptions( $( '#coverageLBAvailablePanchayatLevel' ), lbTypeCode, localBodyCode );
							} else {
								buildParentwisePanchayatOptions('coverageLBAvailablePanchayatLevel', localBodyCode, _js_parent_code);
							}
							showCoverageElements( $.makeArray( _JS_LB_MAPPING_ARRAY [lbTypeLevel] ) );
						}	
				    },
				    "UNMAPPED_COVERAGE": function(localBodyCode, lbTypeCode, lbTypeLevel) {//localBodyElement, 
				    	resetCoverageDetails(null, true);
						if( $( '#checkboxCoverageUnmapped' ).is( ':checked' ) ) {
							var unmappedElement = $( "SELECT[id^='coverageLRAvailableUnmapped" + lbTypeLevel + "']" );
							buildUnmappedLandRegionOptions( unmappedElement , lbTypeLevel, localBodyCode);
							showCoverageElements( $.makeArray( _JS_LR_MAPPING_ARRAY [lbTypeLevel]) );
						}	
				    }
			}; 

			callCheckboxEventCoverage[ checkboxCoverageValue ]( localBodyCode, lbTypeCode, lbTypeLevel);
		};
	 
	/**
	 * The {@code getLocalBodyTypeLevel} used to get the level of panchayat
	 * local body type belongs to.
	 */ 
    var getLocalBodyTypeLevel = function (){
		return '${localBodyTypeLevel}';
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
	 * The {@code buildDistrictPanchayatOptions} used create select box options at 
	 * District Panchayat Level. 
	 * @param elementTemplate (Element Template for given select box instance)
	 * @param localbodyTypeCode
	 */
	var buildDistrictPanchayatOptions = function(elementTemplate, localbodyTypeCode, localBodyCode) {
		var someObject = {};
	    someObject.paramElementTemplate = elementTemplate;
	    someObject.paramLocalBodyCode = localBodyCode;
	    if(isDistrictUser()){
			dwrRestructuredLocalBodyService.getDistrictPanchayatListForDistrictUser(parseInt(localbodyTypeCode), parseInt(_JS_DISTRICT_CODE), localBodyCode, getChangeCoverageProcessId(), {
				callback : callbackHandlerForDPOptions,
				callbackArg : someObject,
				async : true
			});	
		}else{
			dwrRestructuredLocalBodyService.getDistrictPanchayatList(parseInt(localbodyTypeCode), parseInt(_JS_STATE_CODE), localBodyCode, getChangeCoverageProcessId(), {
				callback : callbackHandlerForDPOptions,
				callbackArg : someObject,
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
	var callbackHandlerForDPOptions = function (result, someObject){
		jQuery.each(result, function(index, obj) {
			if(someObject.paramLocalBodyCode != obj.localBodyCode){
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
				someObject.paramElementTemplate.append(option);	
			}
		});
	};
	
	/**
	 * The {@code buildParentwisePanchayatOptions} used create select box options at 
	 * Local Body Level using selected local body code as parent. 
	 * @param elementNode (Element Template for given select box instance)
	 * @param localBodyCode
	 */
	var buildParentwisePanchayatOptions = function (elementNode , localBodyCode, parentCode){
		dwrRestructuredLocalBodyService.getParentwiseLocalBody(parentCode, parseInt(localBodyCode), getChangeCoverageProcessId(), {//getDraftTempCode(),
			callback : function(result){
				var options = $("#" + elementNode);
				jQuery.each(result, function(index, obj) {
					if(localBodyCode != obj.localBodyCode){
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
					}
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
	var buildUnmappedLandRegionOptions = function (elementTemplate, localbodyTypeLevel, localbodyCode) {
		localbodyTypeLevel = ( localbodyTypeLevel == _JS_INTERMEDIATE_PANCHAYAT_LEVEL ) ? _JS_SUBDISTRICT_CONSTANT : localbodyTypeLevel;
		dwrRestructuredLocalBodyService.getUnmappedLandRegions(localbodyTypeLevel, parseInt(_JS_STATE_CODE), parseInt(_JS_DISTRICT_CODE), localbodyCode, getChangeCoverageProcessId(), {
			callback : function(result){
				jQuery.each(result, function(index, obj) {
					var optionText = obj.landRegionNameEnglish;
					var option = $("<option />");
					if (obj.isUsed) {
						option.attr("disabled", true);
						optionText = optionText.concat(" <spring:message code='Label.draftlandregion' htmlEscape='true'/>");
					}
					$(option).attr('title', obj.entityHierarchy);
					option.val(obj.landRegionCode).text(optionText);
					elementTemplate.append(option);
				});
			},
			async : true
		});
	};
	
	/**
	 * The {@code buildLocalBodyCoveredAreas} used create select box options at 
	 * Land Region Coverages for Existing Local Body Coverages Level. 
	 * @param localbodyCodes
	 * @param landRegionType
	 * @param lbLandRegionCodes
	 * @param elementId
	 */
	var buildLocalBodyCoveredAreas = function (localbodyCodes, landRegionType, lbLandRegionCodes, elementId){
		if(!jQuery.isEmptyObject(localbodyCodes) && !jQuery.isEmptyObject(lbLandRegionCodes)){
			var options = $("#" + elementId ); 
			dwrRestructuredLocalBodyService.fetchCoverageLBLandRegion(localbodyCodes.toString(), landRegionType, lbLandRegionCodes, {
				callback : function( result ) {
					jQuery.each(result, function(index, obj) {
						var option = $("<option />");moveElements
						var lrName = concatPartFull(obj.coverageType, obj.landRegionNameEnglish);
						option.val(obj.landRegionCode).text(lrName);
						options.append(option);
					});
				},
				errorHandler : commonErrorProcess,
				async : true
			});	
		}
	};
	
	/**
	 * The {@code buildLandRegionCoveredAreas} used create select box options at 
	 * Land Region Coverages for Existing Unmapped Land Regions. 
	 * @param unmappedCoverageLevel
	 * @param localBodyCodes
	 * @param localBodyType
	 * @param elementId
	 */
	var buildLandRegionCoveredAreas = function (unmappedCoverageLevel, localBodyCodes, localBodyType, elementId){
		if(!jQuery.isEmptyObject(localBodyCodes)){	
			var options = $("#" + elementId ); 
			dwrRestructuredLocalBodyService.fetchCoverageDetailsLandRegion(unmappedCoverageLevel, localBodyCodes.toString(), localBodyType, {
				callback : function( result ) {
					jQuery.each(result, function(index, obj) {buildUnmappedLandRegionOptions
						var option = $("<option />");
						option.val(obj.landRegionCode).text(obj.landRegionNameEnglish);
						$(option).attr('title', obj.entityHierarchy);
						options.append(option);
					});
				},
				errorHandler : commonErrorProcess,
				async : true
			});	
		}
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
						$(option).attr('title', obj.entityHierarchy);
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
			buildHQFromExistingLR();
			if(!$_checkEmptyObject($(lbCoveragesElements).val()) || !$_checkEmptyObject($(lrCoveragesElements).val())){
				callHQPartTemplate(lbCoveragesElements);
				callHQPartTemplate(lrCoveragesElements);
			}	
			var hqElement = '#tblHeadquarter';
			if($(hqElement + ' >tbody >tr').length > 0){
				$( hqElement ).show();
				$('input[name=headQuarterCode][value=${localBodyForm.headQuarterCode}]').attr('checked',true);
			}
		}
	};
	
	var buildHQFromExistingLR = function(){
		var table = $( '#tblCoverage_${localBodyTypeLevel}');
		$('#tblCoverage_${localBodyTypeLevel} > tbody > tr').each(function() {
			var landregionCode = $(this).find("td:first").html();  
			var landregionText = $(this).find("td:nth-child(2)").html();  
			createHQTable(landregionCode, landregionText);
		});
		
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
		//templateRadio.attr("id", "hq" + value);
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
	 
	 
	 
	 var removeExistingCoverages = function ( landRegionCode ){
		 removedLandRegions.push(landRegionCode);
		 $('#tr_' + landRegionCode ).remove();
		 var headquarterRow = $( '#hqTR_' + landRegionCode );
		 if(!(jQuery.type( headquarterRow ) === "undefined")){
			 $( headquarterRow ).remove();	 
		 }
		 var childs = $("[id = remove_" + landRegionCode + "]");
		 $.each( childs , function( index ) {
			 var paramCode = $(this).attr('param');
			 removedLandRegions.push(paramCode);
			 $('#tr_' + paramCode ).remove();
			 var subChilds = $("[id = remove_" + paramCode + "]");
			 $.each( subChilds , function( index ) {
				 var sabParamCode = $(this).attr('param');
				 removedLandRegions.push(sabParamCode);
				 $('#tr_' + sabParamCode ).remove();
			 });
		 });
	 };
	 
	 
	 /**
		 * The {@code validationForm} function registering rules for all required
		 * filed with diffent validations and rules at once.
		 * $.Jquery rules add with different form filed and few of them are also
		 * mapped with registered form lavel rules defined in $(document).ready() function.	 
		 */
		var validationForm = function (){
			hasFormError = false;
		 	var resetedCoverage = $('[name = isResetedCoverage]').val();
		 	var checkboxCoverageElement = $( "INPUT[id^='checkboxCoverage']" );
		 	if( ($_checkEmptyObject( $( '#removedLangRegionCodes' ).val()) || $( '[id^=tblCoverage_] > tbody tr' ).length == 0) && $( checkboxCoverageElement ).not( ':checked' ) ){
				if($_checkEmptyObject(resetedCoverage) ||  isParseJson(resetedCoverage)){
					if(!($(checkboxCoverageElement).is( ':checked' ))){
				 		$('#checkboxCoverageLB' ).rules( "add", {
					 		required : true,  
					 		messages: { required: "<spring:message code='Error.existinglbcoverarea' htmlEscape='true'/>" }
						});
				 		$('#checkboxCoverageUnmapped' ).rules( "add", {
					 		required : true,  
					 		messages: { required: "<spring:message code='Error.landrgncoverarea' htmlEscape='true'/>" }
						});
				 	}
				 	validateLBLRCoverage();
				}	
			}
		 	
		 	$( '#showHQError' ).rules( "add", {
				requiredHQ : true,  
				messages: {
					requiredHQ : "<spring:message code='Error.headquarter' htmlEscape='true'/>",
	 			}
			});
		 	
		 	$("#orderNo" ).rules( "add", {
		 		required : true, 
		 		maxlength: 60,
		 		regex: "[\#\%&\~\!\@\$\^\*\_\+\`\=\{\}\\[\\]\|\\\\:\;\'\"\<\>\?\]" ,
		 		messages: { 
		 			required: "<spring:message code='ORDERNUMBER.REQUIRED' htmlEscape='true'/>",
		 			maxlength: jQuery.format("<spring:message code='Error.Sizevalid' htmlEscape='true'/>"),
		 			regex    : "<spring:message code='Error.invalidegovordrno' htmlEscape='true'/>"
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
		 			if(($_checkEmptyObject(resetedCoverage) ||  isParseJson(resetedCoverage)) && isParseJson("${not modifyProcess}")){
		 				$("#gazettePublicationUpload" ).rules( "add", {
			 		 		required : true, 
			 		 		fileUploadValidate : true,
			 		 		messages: { 
			 		 			required: "<spring:message code='GOVERMENTORDER.REQUIRED' htmlEscape='true'/>",
			 		 			fileUploadValidate: "<spring:message code='Error.invalidgofile' htmlEscape='true'/>"
			 		 		}
			 			});
		 			}
		 			if(isParseJson("${modifyProcess}")){
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
		 
		 /*
		  *  For Preview GIS Coverage#started
		 */
		 previewCOVERAGEInGIsFn=function(){
			var lbTypeLevel = getLocalBodyTypeLevel();
			if(_JS_VILLAGE_PANCHAYAT_LEVEL ==  lbTypeLevel){
				//var localbodyCodes = convertCodeArrayGISPreview('coverageLBContributingPanchayatLevel');
				var villageCodes = convertCodeArrayGISPreview('coverageLBContributingVillageLevel');
				var unmaapedVillageCodes=convertCodeArrayGISPreview('coverageLRContributingUnmappedVillageLevel'); 
				var insertCodes=villageCodes.concat(unmaapedVillageCodes);
				var localBodyCode = $( '#paramLBCode' ).val();
				var localBodyName='${localBodyForm.localBodyNameEnglish}';
				callGISMapView(localBodyCode,localBodyName,'PREVIEW',false,removedLandRegions.toString(),insertCodes.toString());
			}
		 };
		 
		 var convertCodeArrayGISPreview = function(selectedCodeId) {
			 var codesArray = [];
			$('#'+selectedCodeId+' option').each(function() { 
				a= $(this).val();
				if(a.indexOf('PART') > -1){
					codesArray.push(a.replace('_PART', 'N'));
				}
				else if(a.indexOf('FULL') > -1)
					{
					codesArray.push(a.replace('_FULL', 'N'));
					}
			  });
			 
			return codesArray;
			};
			
	    	var callGISMapView = function ( localBodyCode, localBodyName,isShowOnlyBoundary,updateApprovedGP,deleteCode,insertCode ){
	    		dwrRestructuredLocalBodyService.getMappedLBsForGISPreview(parseInt(localBodyCode),localBodyName,isShowOnlyBoundary,updateApprovedGP,deleteCode, insertCode, {
	    			callback : function( result ){
	    				//alert(result);
	    				 if("FAILED" == result){
	    					customAlert(result);
	    				}else{
	    					/* $("#dialogBX").dialog({
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
	    					});	 */
	    					
	    					
	    					$("#dialogBXTitle").text('GIS Map View ( Local Body Code : ' + localBodyCode + ' , Local Body Name : ' + localBodyName + ' )');
	    					
	    					 $('#myIframe').attr('src', result);
	    					/*  $("#myIframe").load(function(){
	    		            	//alert("test"); 
	    				    }); */
	    					$('#dialogBX').modal('show'); 
	    				} 
	    			},
	    			errorHandler : function( errorString, exception){
	    					customAlert(exception);
	    			},
	    			async : true
	    		});		
	    	};
	    	
	    	/*
			  *  For Preview GIS Coverage#end
			 */

//-->	
</script>

<c:if test="${checkedServerValidation}"> 
	<script type='text/javascript'>
		$(window).load(function () {
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
			
			
		}); 
	</script>
</c:if> 

