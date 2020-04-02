<%@include file="../CommonClientRules.jsp" %>

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
	 
	 var removedLandRegions = [];
	 
	 /**
	  * The {@code ready} initialized once page started excuting 
	  * and invoke all on load events.
	  */ 
	 $(document).ready(function() {
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
		 
		$( "INPUT[id^='checkboxCoverage']" ).click(function() {
			var checkboxValue = $(this).attr('param');
			buildUrbanCoverageDetailsEvents(checkboxValue);
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
		
		$('[id^=btnFetchCoverage]').click(function() {
			resetCoverageAreasUrban($(this).attr('level'), $(this).attr('paramLBLR'));
			fetchCoverageDetailsForLandRegions( this );
		});
		
		$("#btnResetExistingCoverages").click(function() {
			resetCoverageforModification();
		});
	});
	  
  	var resetValidations = function() {
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
	
	var resetCoverageforModification = function (){
		$('#divExistingDraftedLBCoverages, #btnResetExistingCoverages').hide();
		$('#divCompleteLBCoverages').show();
		$('[name = isResetedCoverage]').val(true);
		$('[name = contributingLBCodes]').val("");
	};
		
	/**
	 * The {@code resetCoverageAreasUrban} used to call reset method  to clear option boxes
	 * based on their access level.
	 * @param COVERAGE_LAVEL
	 * @param LB_LR_LABEL
	 */ 
	var resetCoverageAreasUrban =  function (COVERAGE_LAVEL, LB_LR_LABEL) {
		var callRemoveOptionElements = {
				"PanchayatLevel": function() {
					levelWiseClearOptions(["DisttLevel", "SubdisttLevel", "VillageLevel"], LB_LR_LABEL);
				},
			    "DisttLevel": function() {
			    	levelWiseClearOptions(["SubdisttLevel", "VillageLevel"], LB_LR_LABEL);
			    },
			    "UnmappedDisttLevel": function() {
			    	levelWiseClearOptions(["SubDisttLevel","VillageLevel"], LB_LR_LABEL);
			    },
			    "UnmappedVillageLevel": function() { 
			    	// Do not remove this block (In Use)
			    },
			    "SubdisttLevel": function() {
			    	levelWiseClearOptions(["VillageLevel"], LB_LR_LABEL);
			    },
			    "SubDisttLevel": function() {
			    	levelWiseClearOptions(["VillageLevel"], LB_LR_LABEL);
			    },
			    "VillageLevel": function() {
			    	// Do not remove this block (In Use)
			    },
			    "UnmappedSubDisttLevel": function() {
			    	levelWiseClearOptions(["VillageLevel"], LB_LR_LABEL);
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
		/* if(_JS_IS_DISRTICT_LEVEL_LOCAL_BODY){ */
			return _JS_DISTRICT_CONSTANT;	
		/* }else {
			return _JS_SUBDISTRICT_CONSTANT;
		} */
	};
	
	/**
	 * The {@code buildDistrictPanchayatOptionsUrban} used create select box options at 
	 * District Panchayat Level. 
	 * @param elementTemplate (Element Template for given select box instance)
	 * @param localbodyTypeCode
	 */
	var buildDistrictPanchayatOptionsUrban = function(elementTemplate, localbodyTypeCode, localBodyCode) {
		 var someObject = {};
		 someObject.paramElementTemplate = elementTemplate;
		 someObject.paramLocalBodyCode = localBodyCode;
		 if(isDistrictUser()){
			dwrRestructuredLocalBodyService.getDistrictPanchayatListForDistrictUser(parseInt(localbodyTypeCode), parseInt(_JS_DISTRICT_CODE), localBodyCode, getChangeCoverageProcessId(),{
				callback : callbackHandlerForDPOptions,
				callbackArg : someObject,
				async : true
			});	
		 } else {
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
	 * The {@code buildUnmappedLandRegionOptionsUrban} used create select box options at 
	 * District Panchayat Level. 
	 * @param elementTemplate (Element Template for given select box instance)
	 */
	var buildUnmappedLandRegionOptionsUrban = function (elementTemplate, localBodyCode) {
		dwrRestructuredLocalBodyService.getUnmappedLandRegions(getUserLevelParameter(), parseInt(_JS_STATE_CODE), parseInt(_JS_DISTRICT_CODE), localBodyCode, getChangeCoverageProcessId(), {
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
		var localBodyCode = $( '#paramLBCode' ).val();
		var lbTypeCode  = $( '#localBodyTypeCode' ).val();
				
		var callCheckboxEventCoverage = {
			"LB_COVERAGE": function( localBodyCode, lbTypeCode ) {
				resetCoverageDetailsUrban(true, null);
				if( $( '#checkboxCoverageLB' ).is( ':checked' ) ) {
					buildDistrictPanchayatOptionsUrban( $( '#coverageLBAvailablePanchayatLevel' ), lbTypeCode, localBodyCode );
					showCoverageElementsUrban( 'divCoverageLBLevel' );
				}	
		    },
		    "UNMAPPED_COVERAGE": function( localBodyCode, lbTypeCode ) {
		    	resetCoverageDetailsUrban(null, true);
				if( $( '#checkboxCoverageUnmapped' ).is( ':checked' ) ) {
					buildUnmappedLandRegionOptionsUrban( $( "SELECT[id = 'coverageLRAvailableUnmappedDisttLevel']" ), localBodyCode );
					showCoverageElementsUrban( 'divCoverageLRLevel' );
				}	
		    }
		}; 
		callCheckboxEventCoverage[ checkboxCoverageValue ]( localBodyCode, lbTypeCode );
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
				validateUrbanLBLRCoverage();
			}	
		}
			 	
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

	var fetchCoverageDetailsForLandRegions = function ( buttonObject ) {
		var btnCoverageLRValue = $( buttonObject ).attr( 'param' );
		var callButtonEventCoverage = {
				
			    "LR_COVERAGE_UNMAPPED_SUB_DISTRICT": function() {
			    	setSelectedOptionBox(['coverageLRContributingUnmappedSubDisttLevel']);			    	
			    	var selectedLBCodes = convertCodeArray($('#coverageLRContributingUnmappedSubDisttLevel').val());
			    	buildLandRegionCoveredAreas(_JS_VILLAGE_CONSTANT, selectedLBCodes,_JS_VILLAGE_CONSTANT,'coverageLRAvailableUnmappedVillageLevel');
			    }	,
			    
			    "LR_COVERAGE_UNMAPPED_DISTRICT": function() {
			    	setSelectedOptionBox(['coverageLRContributingUnmappedDisttLevel']);			    	
			    	var selectedLBCodes = convertCodeArray($('#coverageLRContributingUnmappedDisttLevel').val());
			    	buildLandRegionCoveredAreas(_JS_SUBDISTRICT_CONSTANT, selectedLBCodes,INTERMEDIATE_PANCHAYAT_LEVEL,'coverageLRAvailableUnmappedSubDisttLevel');
			    }	,
			    
			    "LB_COVERAGE_DISTRICT": function(localBodyCodes) {
			    	setSelectedOptionBox(['coverageLBContributingPanchayatLevel', 'coverageLBContributingDisttLevel']);
			    	var selectedLBCodes = convertCodeArray($('#coverageLBContributingPanchayatLevel').val());
			    	var landRegionCodes = convertCodeArray($('#coverageLBContributingDisttLevel').val() );
			    	buildLocalBodyCoveredAreas(selectedLBCodes, _JS_SUBDISTRICT_CONSTANT, landRegionCodes, 'coverageLBAvailableSubDisttLevel');
			    },
				"LB_COVERAGE_SUB_DISTRICT": function(localBodyCodes) {
			    	setSelectedOptionBox(['coverageLBContributingPanchayatLevel', 'coverageLBContributingSubDisttLevel']);
			    	var selectedLBCodes = convertCodeArray($('#coverageLBContributingPanchayatLevel').val());
			    	var landRegionCodes = convertCodeArray($('#coverageLBContributingSubDisttLevel').val() );
			    	buildLocalBodyCoveredAreas(selectedLBCodes, _JS_VILLAGE_CONSTANT, landRegionCodes, 'coverageLBAvailableVillageLevel');
			    }
		}; 
		callButtonEventCoverage[ btnCoverageLRValue ]();
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
					jQuery.each(result, function(index, obj) {
						var option = $("<option />");
						option.val(obj.landRegionCode).text(obj.landRegionNameEnglish);
						/* if($('#tr_' + obj.landRegionCode ).length==0){ */
							options.append(option);
						/* } */
					});
				},
				errorHandler : commonErrorProcess,
				async : true
			});	
		}
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
						var option = $("<option />");
						var lrName = concatPartFull(obj.coverageType, obj.landRegionNameEnglish);
						option.val(obj.landRegionCode).text(lrName);
						if($('#tr_' + obj.landRegionCode ).length==0){
							options.append(option);
						}
					});
				},
				errorHandler : commonErrorProcess,
				async : true
			});	
		}
	};
//-->	
</script>
