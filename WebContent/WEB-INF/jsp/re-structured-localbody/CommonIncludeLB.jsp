<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/dwrRestructuredLocalBodyService.js"></script>
<%@include file="CommonClientRules.jsp" %>

<script type="text/javascript">
	/**
	 * Defined Local Body Ceartion Type and State Code set in Controller Map Attribute. 
	 */
	var _JS_LOCAL_BODY_CREATION_TYPE = '${LOCAL_BODY_CREATION_TYPE}';
	var _JS_URBAN_CONSTANT = "<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.LB_URBAN.toString()%>"; 
	var hasFormError = false;
	/**
	 * The {@code ready} initialized once page started excuting 
	 * and invoke all on load events.
	 */ 
	$(document).ready(function() {
		 $("#localBodyForm").validate({
            ignoreTitle: true ,
            submitHandler: function (form) {
            	if ( ! hasFormError ){
            		var localbodyTypeCode = $("#localBodyType").val();
       			 	var parentCode = 0;
    			 	if(_JS_LOCAL_BODY_CREATION_TYPE != _JS_URBAN_CONSTANT){
       			 		localbodyTypeCode = localbodyTypeCode.split("_")[0];
       				 	var element = $( '[name = localBodyLevelCodes]' );
       				 	var localBodyElement = $( element )[$( element ).length - 1];
       				 	if (jQuery.type( localBodyElement ) != "undefined") {
       				 		parentCode = $( localBodyElement ).val();
       				 	}
       				}
    			 	dwrRestructuredLocalBodyService.checkLocalBodyNameExist($("#localBodyNameEnglish").val(), parseInt(localbodyTypeCode), parentCode, parseInt(_JS_STATE_CODE), getDraftTempCode(), {
    					callback : function(result) {
    						//if(!result){
    						if( _JS_SUCCESS_CONSTANT == result ) {	
    							disableAllButtons();
    							form.submit();
    						} else {
    							$( "#errLBNameEnglish" ).text( result );
    							$( "#localBodyNameEnglish" ).focus();
    							return false;
    						}	
    					},
    					async : true
    				});		
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
                  //alert(message + errors); // This Line used to check error messages in javascript alert.
                  hasFormError = true;
                }
              } 
        }); 
		validationForm();  
		
		$("#btnAddLatLong").click(function() {
			buildLatitudeLongitude('', '');
		}); 
		
		$( 'INPUT[id^=btnFormAction]' ).click(function() {
			$( "span[id^=error]" ).each(function(){
				$( this ).text("");
		    });
			var checkboxCoverageElement = $( "INPUT[id^='checkboxCoverage']" );
		    if($(checkboxCoverageElement).is( ':checked' )){
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
		    }
		    $( "#longitude, #latitude" ).rules( "remove" );
			$( "#longitude, #latitude" ).removeClass( "error" );
		    
		    <c:if test="${isGovernmentOrderUpload}">
	 			if(!$_checkEmptyObject($("#orderCode").val())){
	 				$( "#gazettePublicationUpload" ).rules( "remove" );
					$( "#gazettePublicationUpload" ).removeClass( "error" );
	 			}
	 		</c:if>
	 		$( 'SELECT[name^=contributing] option').attr("selected", "selected");
	 		validationForm();
		    $( "#latitude" ).rules( "add", {
		    	gisNodesMismatch : true, messages: {gisNodesMismatch : "<spring:message code='Error.lengthmismatch' htmlEscape='true'/>"}
			}); 
		    $( "#longitude" ).rules( "add", {
		    	gisNodesMismatch : true, messages: {gisNodesMismatch : "<spring:message code='Error.lengthmismatch' htmlEscape='true'/>"}
			});
		    $('[name = processAction]').val($(this).attr('param'));
		});
		
		$("#btnActionClose").click(function() {
			$(location).attr('href', $( this ).attr( 'param' ).concat('.htm'));
		});
		
		$("#btnResetExistingCoverages").click(function() {
			resetCoverageforModification();
		});
		$("#longitude, #latitude").on('keyup', function(e){
			checkDecimalPlaces(e)
		}).numeric({ decimal: ".", negative: false }, function() {
			this.value = ""; this.focus(); 
		});
	});
	 	 
	var resetCoverageforModification = function (){
		$('#divExistingDraftedLBCoverages').hide();
		$('#divCompleteLBCoverages').show();
		if(_JS_LOCAL_BODY_CREATION_TYPE != _JS_URBAN_CONSTANT){
			$('#divCompleteLBHeardQuarter').show();
		}
		$('[name = isResetedCoverage]').val(true);
		$('[name = contributingLBCodes]').val("");
		$('#btnResetExistingCoverages').hide();
	}; 
	 
	var getDraftTempCode = function (){
		var draftTempId = $('#draftTempId').val();
	 	if(jQuery.type( draftTempId ) === "undefined"){
	 		draftTempId = null;
	 	}
	 	return draftTempId;
	}; 
	 
	

	/**
	 * The {@code checkUncheckBox} used to set check box selection 
	 * process for both localbody and land region.
	 * @param lbCheck
	 * @param lrCheck
	 */
	var checkUncheckBox = function(lbCheck, lrCheck) {
		$('#checkboxCoverageLB').attr('checked', lbCheck);
		$('#checkboxCoverageUnmapped').attr('checked', lrCheck);
	};
	
	
	/**
	 * The {@code checkMapUpload} function used to validate if level of panchayat  
	 * is allowed for map upload.
	 */
	var checkMapUpload = function (tiersetupCode){
		 $("#mapUpload").replaceWith($("#mapUpload").clone());
		 $("#divMapUpload").hide();
		 if(!$_checkEmptyObject(tiersetupCode)){
			 dwrRestructuredLocalBodyService.checkMapUpload(parseInt(tiersetupCode), {
				callback : function(result) {
					if(result){
						$("#divMapUpload").show();
					}
				},
				async : true
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
	 	$("#localBodyNameEnglish" ).rules( "add", {
			  required: true,
			  maxlength: 200,
			  regex: "[#%&\/\~\!\@\$\^\*\_\+\`\=\\{\}\\[\\]\|\\\\:\,\;\'\"\<\>\?]" ,//Allow '(' and ')' brackets in Name of the Localbody (In English) @Pooja @date 08-10-15
			  messages: {
			    required : "<spring:message code='error.blank.localBodyNameInEn' htmlEscape='true'/>",
			    maxlength: jQuery.format("<spring:message code='Error.Sizevalid' htmlEscape='true'/>"),
			    regex    : "<spring:message code='Error.invalidchar' htmlEscape='true'/>"
			  }
		});
	 	$("#localBodyNameLocal" ).rules( "add", {
	 		  maxlength: 100,
			  regex: "[#%&\/\~\!\@\$\^\*\_\+\`\=\\{\}\\[\\]\|\\\\:\,\;\'\"\<\>\?]" ,//Allow '(' and ')' brackets in Name of Localbody (In Local) @Pooja @date 08-10-15
			  messages: {
			    maxlength: jQuery.format("<spring:message code='Error.Sizevalid' htmlEscape='true'/>"),
			    regex    : "<spring:message code='Error.localbodylocal' htmlEscape='true'/>"
			  }
		});
	 	$("#localBodyAliasEnglish" ).rules( "add", {
	 		  maxlength: 50,
			  regex: "[#%&\/\~\!\@\$\^\*\_\+\`\=\\(\)\{\}\\[\\]\|\\\\:\,\;\'\"\<\>\?]" ,
			  messages: {
			    maxlength: jQuery.format("<spring:message code='Error.Sizevalid' htmlEscape='true'/>"),
			    regex    : "<spring:message code='Error.invalidchar' htmlEscape='true'/>"
			  }
		});
	 	$("#localBodyAliasLocal" ).rules( "add", {
	 		  maxlength: 50,
			  regex: "[#%&\/\~\!\@\$\^\*\_\+\`\=\\(\)\{\}\\[\\]\|\\\\:\,\;\'\"\<\>\?]" ,
			  messages: {
			    maxlength: jQuery.format("<spring:message code='Error.Sizevalid' htmlEscape='true'/>"),
			    regex    : "<spring:message code='Error.lbaliaslocal' htmlEscape='true'/>"
			  }
		});
	 	$("#stateSpecificCode" ).rules( "add", {
	 		  maxlength: 7,
	 		  regexAllowed: "^[a-zA-Z0-9]*$" ,
			  messages: {
			    maxlength: jQuery.format("<spring:message code='Error.Sizevalid' htmlEscape='true'/>"),
			    regexAllowed    : "<spring:message code='Error.numberandalphabet' htmlEscape='true'/>"
			  }
		});
	 	$("#localBodyType" ).rules( "add", {
	 		required : true,  
	 		messages: { required: "<spring:message code='error.select.LBTYPE' htmlEscape='true'/>" }
		});
	 	
	 	var resetedCoverage = $('[name = isResetedCoverage]').val();
	 	if($_checkEmptyObject(resetedCoverage) ||  isParseJson(resetedCoverage)){
	 		var checkboxCoverageElement = $( "INPUT[id^='checkboxCoverage']" );
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
		 	if(_JS_LOCAL_BODY_CREATION_TYPE != _JS_URBAN_CONSTANT){
		 		$("#lbTypeHierarchy" ).rules( "add", {
			 		required : true,  
			 		messages: { required: "<spring:message code='Error.heirarchyselect' htmlEscape='true'/>" }
				});
		 		validateLBLRCoverage();
		 		setErrorHQ();
			}else{
				validateUrbanLBLRCoverage();
			}
	 	}	 	
	 	
	 	 $("[name=longitude]" ).rules( "add", {
	 		 customRanges : [[32, 98]],
	 		 messages: { 
	 			customRanges: "<spring:message code='Error.longituderng' htmlEscape='true'/>" 
	 		 }
		}); 
	 	$("[name=latitude]" ).rules( "add", {
	 		customRanges : [[6, 38]],
	 		messages: { 
	 			customRanges: "<spring:message code='Error.latituderng' htmlEscape='true'/>" 
	 		}
		});
	 	$("#mapUpload" ).rules( "add", {
	 		fileUploadValidateMap : true,
	 		messages: { 
	 			fileUploadValidateMap: "<spring:message code='Error.invalidmapfile' htmlEscape='true'/>"
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
	 			/* if(isParseJson("${modifyProcess}")){ */
	 				$("#gazettePublicationUpload" ).rules( "add", {
		 		 		fileUploadValidate : true,
		 		 		messages: { 
		 		 			fileUploadValidate: "<spring:message code='Error.invalidgofile' htmlEscape='true'/>"
		 		 		}
		 			});
	 			/* } */
	 		}
	 		/* added by pooja on 23-11-2015 */
	 		if(!$_checkEmptyObject($("#checkNewOrExistGovtOrder").val())){
	 			if($("#checkNewOrExistGovtOrder").val() == "N") {
	 				$("#gazettePublicationUpload" ).rules( "add", {
	 					required : true,
	 		 			messages: { 
	 		 				required: "<spring:message code='GOVERMENTORDER.REQUIRED' htmlEscape='true'/>"
	 		 			}
	 				});
	 			}
	 		}
	 	</c:if>
	};
	
	/**
	 * The {@code latitudelongitudeOnload} called for setting Longitude and Lattitude 
	 * values from Server.
	 * Cases Handled for server side validation failure, Modification of Drafted Local Body.
	 */
	latitudelongitudeOnload();
	
//-->


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
					jQuery.each(result, function(index, obj) {
						var option = $("<option />");
						option.val(obj.landRegionCode).text(obj.landRegionNameEnglish);
						options.append(option);
					});
				},
				errorHandler : commonErrorProcess,
				async : true
			});	
		}
	};

</script>