<%@include file="CommonIncludeLB.jsp" %>
<script type='text/javascript'>
<!--	
	/**
	 * Defined Value for Urban Local Body at Level (District or Sub-district).  . 
	 */
	var _JS_IS_DISRTICT_LEVEL_LOCAL_BODY = isParseJson( '${isDistrictLevel}' );
	
	/**
	 * Defined constants Local Body Level for Panchayats. 
	 */
	 var _JS_DISTRICT_CONSTANT = "<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.DISTRICT_CONSTANT.toString()%>";
	 var _JS_SUBDISTRICT_CONSTANT = "<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.SUB_DISTRICT_CONSTANT.toString()%>"; 
	 var _JS_VILLAGE_CONSTANT = "<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.VILLAGE_CONSTANT.toString()%>";   
	 
	 var INTERMEDIATE_PANCHAYAT_LEVEL = "<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.INTERMEDIATE_PANCHAYAT_LEVEL.toString()%>";   
	 
	 /**
	  * The {@code ready} initialized once page started excuting 
	  * and invoke all on load events.
	  */ 
	 $(document).ready(function() {
		$( "INPUT[id^='checkboxCoverage']" ).click(function() {
			$( "#localBodyType" ).removeClass( "error" );
			$( "label[for='localBodyType']" ).text(""); 
			var checkboxValue = $(this).attr('param');
			buildUrbanCoverageDetailsEvents(checkboxValue);
		});
		$("#localBodyType").change(function() {
			$( this ).removeClass("error");
			$( '#errorLocalBodyType' ).text("");
			resetCoverageDetailsUrban(true, true);
			checkUncheckBox(false, false);
			checkMapUpload($('option:selected', this).attr('paramTierSetup'));
			if(isParseJson( '${modifyProcess}' )){
				resetCoverageforModification();
			}
		});
		$( '[id = btnEventCoverage]' ).click(function() {
			resetCoverageAreasUrban($(this).attr('level'), $(this).attr('paramLBLR'));
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
			resetCoverageAreasUrban($(this).attr('level'), $(this).attr('paramLBLR'));
			fetchCoverageDetailsForLB();
		});
		
		$('[id^=btnFetchCoverage]').click(function() {
			resetCoverageAreasUrban($(this).attr('level'), $(this).attr('paramLBLR'));
			fetchCoverageDetailsForLandRegions( this );
		});
	});
	  
	  
	
	/**
	 * The {@code resetCoverageAreasUrban} used to call reset method  to clear option boxes
	 * based on their access level.
	 * @param COVERAGE_LAVEL
	 * @param LB_LR_LABEL
	 */ 
	var resetCoverageAreasUrban =  function (COVERAGE_LAVEL, LB_LR_LABEL) {
		var callRemoveOptionElements = {
				"PanchayatLevel": function() {
					levelWiseClearOptions(["DisttLevel", "SubDisttLevel", "VillageLevel"], LB_LR_LABEL);
				},
			    "DisttLevel": function() {
			    	levelWiseClearOptions(["SubDisttLevel", "VillageLevel"], LB_LR_LABEL);
			    },
			    "UnmappedDisttLevel": function() {
			    	levelWiseClearOptions(["IntermediateLevel","VillageLevel"], LB_LR_LABEL);
			    },
			    "UnmappedVillageLevel": function() { 
			    	// Do not remove this block (In Use)
			    },
			    "SubDisttLevel": function() {
			    	levelWiseClearOptions(["VillageLevel"], LB_LR_LABEL);
			    },
			    "UnmappedSubDisttLevel": function() {
			    	levelWiseClearOptions(["VillageLevel"], LB_LR_LABEL);
			    },
			    "VillageLevel": function() {
			    	// Do not remove this block (In Use)
			    }
		};
		callRemoveOptionElements[ COVERAGE_LAVEL ]();
	};
	
	/**
	 * The {@code showCoverageElementsUrban} used to display all elemets assigned in input 
	 * parameter array.
	 * @param _PARAM_SHOW_ARRAY
	 */ 
	var showCoverageElementsUrban = function ( elementId ){
			$("#" + elementId).show();
	};
	
	var getUserLevelParameter = function () {
		/* if(_JS_IS_DISRTICT_LEVEL_LOCAL_BODY){
			return _JS_DISTRICT_CONSTANT;	
		}else {
			return _JS_SUBDISTRICT_CONSTANT;
		} */
		return _JS_DISTRICT_CONSTANT;
	};
	
	/**
	 * The {@code buildDistrictPanchayatOptionsUrban} used create select box options at 
	 * District Panchayat Level. 
	 * @param elementTemplate (Element Template for given select box instance)
	 * @param localbodyTypeCode
	 */
	var buildDistrictPanchayatOptionsUrban = function(elementTemplate, localbodyTypeCode) {
		if(isDistrictUser()){
			dwrRestructuredLocalBodyService.getDistrictPanchayatListForDistrictUser(parseInt(localbodyTypeCode), parseInt(_JS_DISTRICT_CODE), getDraftTempCode(), getCreateLBProcessId(),{
				callback : callbackHandlerForDPOptions,
				callbackArg : elementTemplate,
				async : true
			});	
		}else{
			dwrRestructuredLocalBodyService.getDistrictPanchayatList(parseInt(localbodyTypeCode), parseInt(_JS_STATE_CODE), getDraftTempCode(), getCreateLBProcessId(), {
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
	 * The {@code buildUnmappedLandRegionOptionsUrban} used create select box options at 
	 * District Panchayat Level. 
	 * @param elementTemplate (Element Template for given select box instance)
	 */
	var buildUnmappedLandRegionOptionsUrban = function (elementTemplate) {
		dwrRestructuredLocalBodyService.getUnmappedLandRegions(getUserLevelParameter(), parseInt(_JS_STATE_CODE), parseInt(_JS_DISTRICT_CODE), getDraftTempCode(), getCreateLBProcessId(), {
			callback : function(result){
				jQuery.each(result, function(index, obj) {
					var option = $("<option />");
					var optionText = obj.landRegionNameEnglish;
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
	 * The {@code resetCoverageDetailsUrban} function used to reset 
	 * coverage details display elements.
	 */
	var resetCoverageDetailsUrban = function ( IS_LB_HIDE, IS_LR_UNMAPPED_HIDE ) {
		 // Hide all Existing Local Bodies Div Elements.
		 if(IS_LB_HIDE) {
			 $( "SELECT[id^='coverageLB']" ).empty();
			 $( "#divCoverageLBLevel" ).hide();
		 };
		 // Hide all Upmapped / Partially Mapped Land Region Div Elements.
		 if(IS_LR_UNMAPPED_HIDE) {
			 $( "SELECT[id^='coverageLR']" ).empty();
			 $( "#divCoverageLRLevel" ).hide();
		};
	};
		
	var buildUrbanCoverageDetailsEvents = function(checkboxCoverageValue){
		var localBodyTypeElement = $( '#localBodyType' );
		var selectedlocalBodyType = $( localBodyTypeElement ).val();
		if( $_checkEmptyObject(selectedlocalBodyType) ){
			$(localBodyTypeElement).addClass("error");
			$( '#errorLocalBodyType' ).text("<spring:message code='error.select.LBTYPE' htmlEscape='true'/>");
			return false;
		}
		var callCheckboxEventCoverage = {
				"LB_COVERAGE": function(lbTypeCode) {
					resetCoverageDetailsUrban(true, null);
					if( $( '#checkboxCoverageLB' ).is( ':checked' ) ) {
						buildDistrictPanchayatOptionsUrban( $( '#coverageLBAvailablePanchayatLevel' ), lbTypeCode );
						showCoverageElementsUrban( 'divCoverageLBLevel' );
					}	
			    },
			    "UNMAPPED_COVERAGE": function(localBodyElement, lbTypeCode, lbTypeLevel) {
			    	resetCoverageDetailsUrban(null, true);
					if( $( '#checkboxCoverageUnmapped' ).is( ':checked' ) ) {
						buildUnmappedLandRegionOptionsUrban( $( "SELECT[id = 'coverageLRAvailableUnmappedDisttLevel']" ) );
						showCoverageElementsUrban( 'divCoverageLRLevel' );
					}	
			    }
			    
		}; 
		callCheckboxEventCoverage[ checkboxCoverageValue ]( selectedlocalBodyType );
		
		
	};
	
	var fetchCoverageDetailsForLB = function () {
		var options = $( "SELECT[id='coverageLBAvailableDisttLevel']" );
		setSelectedOptionBox(['coverageLBContributingPanchayatLevel']);
		var localbodyCodes = convertCodeArray( $( '#coverageLBContributingPanchayatLevel' ).val());
		if(!jQuery.isEmptyObject(localbodyCodes)){	
			dwrRestructuredLocalBodyService.fetchCoverageDetailsLocalBody(localbodyCodes.toString(), getUserLevelParameter(),  {
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
	
	
	var validateUrbanLBLRCoverage = function (){
		if($("#checkboxCoverageLB").is( ':checked' )){
			$('#coverageLBContributingPanchayatLevel' ).rules( "add", {
				required : true,  
				onefull : true,
			 	messages: {
			 		required: "<spring:message code='Error.contributingulb' htmlEscape='true'/>",
			 		onefull : "<spring:message code='Error.fulllocalbdyvalidation' htmlEscape='true'/>"
				}
			});
			setSelectedOptionBox(['coverageLBContributingPanchayatLevel']);
			var lbPartCodes = convertCodeArray( $( '#coverageLBContributingPanchayatLevel' ).val());
			if($(lbPartCodes).size() > 0){
				$('#coverageLBContributingDisttLevel' ).rules( "add", {
					required : true,  
					contributeFromAllLB : true,
					contributeAllFull : true,
				 	messages: {
				 		required: "<spring:message code='Error.contributinglandrgn' htmlEscape='true'/>",
				 		contributeFromAllLB : "<spring:message code='Error.selectcovragevalidation' htmlEscape='true'/>",
		 				contributeAllFull : "<spring:message code='Error.partlocalbdyvalidation' htmlEscape='true'/>"
					}
				});
			}
		}
		if($("#checkboxCoverageUnmapped").is( ':checked' )){
			$('#coverageLRContributingUnmappedDisttLevel' ).rules( "add", {
				required : true,  
			 	messages: {
			 		required: "<spring:message code='Error.contributinglandrgn' htmlEscape='true'/>",
				}
			});
		}
	};
	
	var fetchCoverageDetailsForLandRegions = function ( buttonObject ) {
		var btnCoverageLRValue = $( buttonObject ).attr( 'param' );
		var callButtonEventCoverage = {
				
			    "LR_COVERAGE_UNMAPPED_SUB_DISTRICT": function() {
			    	
			    	$("#coverageLRAvailableUnmappedSubDisttLevel option").remove();
			    	$("#coverageLRContributingUnmappedSubDisttLevel option").remove();
			    	
			    	setSelectedOptionBox(['coverageLRContributingUnmappedDisttLevel']);			    	
			    	var selectedLBCodes = convertCodeArray($('#coverageLRContributingUnmappedDisttLevel').val());
			    	buildLandRegionCoveredAreas(_JS_SUBDISTRICT_CONSTANT, selectedLBCodes,INTERMEDIATE_PANCHAYAT_LEVEL,'coverageLRAvailableUnmappedSubDisttLevel');
			    }	,
				"LB_COVERAGE_VILLAGE_DISTRICT": function(localBodyCodes) {
			    	setSelectedOptionBox(['coverageLBContributingPanchayatLevel', 'coverageLBContributingSubDisttLevel']);
			    	var selectedLBCodes = convertCodeArray($('#coverageLBContributingPanchayatLevel').val());
			    	var landRegionCodes = convertCodeArray($('#coverageLBContributingSubDisttLevel').val() );
			    	buildLocalBodyCoveredAreas(selectedLBCodes, _JS_VILLAGE_CONSTANT, landRegionCodes, 'coverageLBAvailableVillageLevel');
			    },
			    
			    "LB_COVERAGE_SUB_DISTRICT": function(localBodyCodes) {
			    	
			    	$("#coverageLBAvailableSubDisttLevel option").remove();
			    	$("#coverageLBContributingSubDisttLevel option").remove();
			    	
			    	setSelectedOptionBox(['coverageLBContributingPanchayatLevel', 'coverageLBContributingDisttLevel']);
			    	var selectedLBCodes = convertCodeArray($('#coverageLBContributingPanchayatLevel').val());
			    	var landRegionCodes = convertCodeArray($('#coverageLBContributingDisttLevel').val() );
			    	buildLocalBodyCoveredAreas(selectedLBCodes, _JS_SUBDISTRICT_CONSTANT, landRegionCodes, 'coverageLBAvailableSubDisttLevel');
			    },
			    
			    "LR_COVERAGE_UNMAPPED_VILLAGE": function(localBodyCodes) {
			    	setSelectedOptionBox(['coverageLRContributingUnmappedSubDisttLevel']);
			    	var selectedLBCodes = convertCodeArray($('#coverageLRContributingUnmappedSubDisttLevel').val());
			    	buildLandRegionCoveredAreas(_JS_VILLAGE_CONSTANT, selectedLBCodes, _JS_VILLAGE_CONSTANT, 'coverageLRAvailableUnmappedVillageLevel');
			    }
		}; 
		callButtonEventCoverage[ btnCoverageLRValue ]();
	}; 
//-->	
</script>
<c:if test="${checkedServerValidation}"> 
	<script type='text/javascript'>
		$(window).load(function () {
			latitudelongitudeOnload();
			checkMapUpload($( "#localBodyType option:selected" ).val());
		});
	
	</script>
</c:if> 