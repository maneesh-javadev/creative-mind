
<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/lgdDwrVillageService.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/dwrRestructuredLocalBodyService.js"></script>
<%@include file="../CommonClientRules.jsp" %>

<script type='text/javascript'>
<!--	
	/**
	 * Defined Unmapped / Partially Mapped Div arrays are to be used to build coverage Elements. 
	 */
	var _JS_ARRAY_LR_UNMAPPED_FULL = [ "firstLevelUnmappedLRDiv", "secondLevelUnmappedLRDiv", "thirdLevelUnmappedLRDiv" ];
	var _JS_ARRAY_LR_SECOND_LEVEL = [ "secondLevelUnmappedLRDiv", "thirdLevelUnmappedLRDiv" ];
	var _JS_ARRAY_LR_THIRD_LEVEL = [ "thirdLevelUnmappedLRDiv" ];
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
	 var _JS_URBAN_CONSTANT = "<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.LB_URBAN.toString()%>";   
	 var _JS_LOCAL_BODY_CREATION_TYPE = '${LOCAL_BODY_CREATION_TYPE}';
	 var _JS_DISTRICT_PANCHAYAT_LEVEL = "<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.DISTRICT_PANCHAYAT_LEVEL.toString()%>";
	 var _JS_INTERMEDIATE_PANCHAYAT_LEVEL = "<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.INTERMEDIATE_PANCHAYAT_LEVEL.toString()%>";
	 var _JS_VILLAGE_PANCHAYAT_LEVEL = "<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.VILLAGE_PANCHAYAT_LEVEL.toString()%>";
	 var _JS_LR_MAPPING_ARRAY = {D : _JS_ARRAY_LR_UNMAPPED_FULL, I : _JS_ARRAY_LR_SECOND_LEVEL, V :_JS_ARRAY_LR_THIRD_LEVEL};
	 var _STATE_WISE_HABIATION_CONFIGURATION='${habitationConfigration}'; 
	 var _JS_HABITAION_NOT_CONFIGURE_CONSTANT="<%=in.nic.pes.lgd.constant.CommanConstant.HABITHABITATION_PARENT_TYPE_NOT_CONFIGURE.toString()%>";
	 var hasFormError = false;
	 /**
	  * The {@code ready} initialized once page started excuting 
	  * and invoke all on load events.
	  */ 
	 $(document).ready(function() {	
		 var lbTypeLevel = '${localBodyTypeLevel}';	
		 if(_JS_LOCAL_BODY_CREATION_TYPE==_JS_URBAN_CONSTANT && (_JS_IS_DISRTICT_LEVEL_LOCAL_BODY)){
			 lbTypeLevel='D';
		 }
		 var unmappedElement = $( "SELECT[id^='coverageLRAvailableUnmapped" + lbTypeLevel + "']" );
		 buildUnmappedLandRegionOptions(unmappedElement,null);	
		 showCoverageElements( $.makeArray( _JS_LR_MAPPING_ARRAY [lbTypeLevel]) );
		
		jQuery.validator.addMethod("requiredMapHQ", function() {
				return !$_checkEmptyObject($('[name = headQuarterCodes]:checked').val()); 
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
                  //alert(message + errors); // This Line used to check error messages in javascript alert.
                  hasFormError = true;
                }
              } 
        }); 
		
				 
		$( '[id = btnEventCoverage]' ).click(function() {		
// 			resetCoverageAreasUrban($(this).attr('level'), $(this).attr('paramLBLR'));
			var sourceElement = 'coverage' + $(this).attr('paramLBLR') + 'Available' +  $(this).attr('level');
			var destElement   = 'coverage' + $(this).attr('paramLBLR') + 'Contributing' +  $(this).attr('level');
			var callMovingElements = {
					"Whole": function(sourceElement, destElement) {
						
						if(validatePartMapping(sourceElement)){
							alert("<spring:message code='Error.partlandrgnvalidation' htmlEscape='true'/>");
							return false;	
						}
						moveElements(sourceElement, destElement, 'FULL', false, false);
					},
				    "Back": function(sourceElement, destElement, level, lb_lr_code) {
				    	moveElements(destElement, sourceElement, null, true, false);
				    	if ( _JS_VILLAGE_PANCHAYAT_LEVEL ==  lbTypeLevel ){
							setCoverageMap($(this).attr('param'),'F');
							getHabitationList();
						}
				    },
				    "Reset": function(sourceElement, destElement, level, lb_lr_code) {
				    	moveElements(destElement, sourceElement, null, false, true);
				    	if ( _JS_VILLAGE_PANCHAYAT_LEVEL ==  lbTypeLevel ){
							setCoverageMap($(this).attr('param'),'F');
							getHabitationList();
						}
				    },
				    "Part": function(sourceElement, destElement, level, lb_lr_code) {
				    	moveElements(sourceElement, destElement, 'PART', false, false);
				    }
			}; 
			return callMovingElements[ $(this).attr('param') ](sourceElement, destElement);
		});
		
	
		$( 'INPUT[id^=btnFormAction]' ).click(function() {
			//resetValidations();
			$( 'SELECT[id^=coverageLRContributingUnmapped] option').attr("selected", "selected");
			validationForm();
	 		$('[name = processAction]').val($(this).attr('param'));
		});

		$('[id^=btnFetchCoverage]').click(function() {
			resetCoverageAreasUrban($(this).attr('level'), $(this).attr('paramLBLR'));
// 			if(document.getElementById('coverageLRAvailableUnmappedIntermediateLevel').options.length == 0){
				fetchCoverageDetailsForLR( this );
// 			}
		});

		$('[id^=btnFetchVillageCoverage]').click(function() {
			resetCoverageAreasUrban($(this).attr('level'), $(this).attr('paramLBLR'));
// 			if(document.getElementById('coverageLRAvailableUnmappedVillageLevel').options.length == 0){
				fetchCoverageDetailsForLR( this );
// 			}
		});
		
		$('[id^=contributed][id$=_F]').mouseover(function() {
			if(!$(this).attr('checked')){
				if ( _JS_VILLAGE_PANCHAYAT_LEVEL ==  lbTypeLevel ){
					setCoverageMap($(this).attr('param'),'F');
					getHabitationList();
				}
				
				
				getLBListofSelectedLRforFull($(this).attr('param'),$(this));	
			}
					
		});
		$('[id^=contributed][id$=_P]').mouseover(function() {
			if(!$(this).attr('checked')){
				if ( _JS_VILLAGE_PANCHAYAT_LEVEL ==  lbTypeLevel ){
					setCoverageMap($(this).attr('param'),'P');
					getHabitationList();	
				}
				
				getLBListofSelectedLRforPart($(this).attr('param'),$(this));	
			}
					
		});		
		
		$("#actionBuildHeadQuarter").click(function() {			
			buildHeadQuarter();
		});		
		
		$("[name=headQuarterCodes]" ).click(function() {
			$('input[id=headQuarterCode]').val($(this).val());
		});
		
		
		$('[id^=btnFetchHabitationList]').click(function() {
			getHabitationList();
		});
		
		$('[id^=btnCoveragePartFull]').click(function() {			
			buildExistingavilableCoverage($(this).attr('param'));
			$("#ChangeCoverageLandregionType").attr("param", $(this).attr('param') );
			$("#changeExistCoverageFull").attr("paramLandregionType", $(this).attr('paramLandregionType') );
			$("#changeExistCoverageBACK").attr("paramLandregionType", $(this).attr('paramLandregionType') );
			$("#changeExistCoveragePART").attr("paramLandregionType", $(this).attr('paramLandregionType') );
		});	
		
		$("#ChangeCoverageLandregionType").click(function() {	
			
			setChangeCoverageTypeLRNew($(this).attr('param'));
		});	
		
		$('[id^=changeExistCoverage]').click(function() {	
		moveElementExistCoverage($(this).attr('param'),$(this).attr('paramLandregionType'));
		});	
		validationForm();
		
	});
	  
  	var resetValidations = function() {
	     $( "[id=coverageLRContributingUnmappedDisttLevel]" ).each(function( ) {
	    	$( this ).rules( "remove" );
			$( this ).removeClass( "error" );
	    });
	};  
	
	 
	/**
	 * The {@code resetCoverageAreasUrban} used to call reset method  to clear option boxes
	 * based on their access level.
	 * @param COVERAGE_LAVEL
	 * @param LB_LR_LABEL
	 */ 
	var resetCoverageAreasUrban =  function (COVERAGE_LAVEL, LB_LR_LABEL) {
		var callRemoveOptionElements = {				
			    "UnmappedIntermediateLevel": function() {
			    	levelWiseClearOptionsUrban(["VillageLevel"], LB_LR_LABEL);
			    },
			    "UnmappedVillageLevel": function() {
			    	// Do not remove this block (In Use)
			    },
			    "UnmappedDisttLevel": function() {
			    	levelWiseClearOptions(["IntermediateLevel","VillageLevel"], LB_LR_LABEL);
			    },
			    "VillageLevel": function() {
			    	// Do not remove this block (In Use)
			    }
		};
		callRemoveOptionElements[ COVERAGE_LAVEL ]();
	};
	
	
	
	/**
	 * The {@code buildUnmappedLandRegionOptionsUrban} used create select box options at 	
	 * District Panchayat Level. 
	 * @param elementTemplate (Element Template for given select box instance)
	 */
	var buildUnmappedLandRegionOptions = function (elementTemplate, localBodyCode) {
		 var localbodyTypeLevel ="";
		 if(_JS_LOCAL_BODY_CREATION_TYPE==_JS_URBAN_CONSTANT){
			 localbodyTypeLevel= getUserLevelParameter();
		 }else{
			 var lbTypeLevel='${localBodyTypeLevel}';
			 localbodyTypeLevel = ( lbTypeLevel == _JS_INTERMEDIATE_PANCHAYAT_LEVEL ) ? _JS_SUBDISTRICT_CONSTANT : lbTypeLevel;
		 }
		dwrRestructuredLocalBodyService.getUnmappedLandRegions(localbodyTypeLevel, parseInt(_JS_STATE_CODE), parseInt(_JS_DISTRICT_CODE), localBodyCode, null, {
			callback : function(result){
				if ( _JS_VILLAGE_PANCHAYAT_LEVEL ==  lbTypeLevel && _STATE_WISE_HABIATION_CONFIGURATION == _JS_VILLAGE_CONSTANT){
					getHabitationList();
				}
				
				jQuery.each(result, function(index, obj) {
					var option = $("<option />");
					var optionText = obj.landRegionNameEnglish;
					if (obj.isUsed) {
						option.attr("disabled", true);
						optionText = optionText.concat(" <spring:message code='Label.draftlandregion' htmlEscape='true'/>");
					}
					if (obj.isDisturbed) {
						option.attr("disabled", true);
						optionText = optionText.concat(" <spring:message code='Label.disturblocalbody' htmlEscape='true'/>");
					}
					$(option).attr('title', obj.entityHierarchy);
					option.val(obj.landRegionCode).text(optionText);
					elementTemplate.append(option);
				});
			},
			async : true
		});
	};

	var fetchCoverageDetailsForLR = function (buttonObject) {		
		var btnCoverageLRValue = $(buttonObject ).attr( 'param' );
		var lbTypeLevel ='${localBodyTypeLevel}';
		var callButtonEventCoverage = {			
			    "LB_COVERAGE_UNMAPPED_DISTRICT": function() {
			    	 if(_JS_LOCAL_BODY_CREATION_TYPE==_JS_URBAN_CONSTANT && (_JS_IS_DISRTICT_LEVEL_LOCAL_BODY)){	    	
					    	var selectedLBCodes = convertCodeArrayUrban('coverageLRContributingUnmappedDisttLevel');
					    	buildLandRegionCoveredAreas(_JS_SUBDISTRICT_CONSTANT, selectedLBCodes, lbTypeLevel, 'coverageLRAvailableUnmappedIntermediateLevel');
			    		
			    	}else{ 
			    		setSelectedOptionBox(['coverageLRContributingUnmappedDisttLevel']);			    	
				    	var selectedLBCodes = convertCodeArray($('#coverageLRContributingUnmappedDisttLevel').val());
				    	buildLandRegionCoveredAreas(_JS_SUBDISTRICT_CONSTANT, selectedLBCodes, lbTypeLevel, 'coverageLRAvailableUnmappedIntermediateLevel');
			    	}
			    	
			    },
			    "LB_COVERAGE_UNMAPPED_SUB_DISTRICT": function() {
			    	
			    	 if(_JS_LOCAL_BODY_CREATION_TYPE==_JS_URBAN_CONSTANT){
			    		 //var selectedLBCodes = convertCodeArrayUrban('coverageLRContributingUnmappedIntermediateLevel');
			    		 buildLandRegionCoveredAreasUrbans(_JS_VILLAGE_CONSTANT, lbTypeLevel, 'coverageLRAvailableUnmappedVillageLevel');
			    		//buildSubdistrictCoveredAreas(_JS_VILLAGE_CONSTANT,lbTypeLevel,'coverageLRAvailableUnmappedVillageLevel');
			    	}else{ 
			    		setSelectedOptionBox(['coverageLRContributingUnmappedIntermediateLevel']);			    	
				    	var selectedLBCodes = convertCodeArray($('#coverageLRContributingUnmappedIntermediateLevel').val());
				    	buildLandRegionCoveredAreas(_JS_VILLAGE_CONSTANT, selectedLBCodes, lbTypeLevel, 'coverageLRAvailableUnmappedVillageLevel');
			    	}
			    	
			    }
		}; 
		callButtonEventCoverage[ btnCoverageLRValue ]();
	};
		
	
	 /**
	 * The {@code validationForm} function registering rules for all required
	 * filed with diffent validations and rules at once.
	 * $.Jquery rules add with different form filed and few of them are also
	 * mapped with registered form lavel rules defined in $(document).ready() function.	 
	 */
	var validationForm = function (){
		 hasFormError = false;
		 if(_JS_LOCAL_BODY_CREATION_TYPE != _JS_URBAN_CONSTANT){
			$( '#showMapHQError' ).rules( "add", {
				requiredMapHQ : true,  
				messages: {
					requiredMapHQ : "<spring:message code='Error.headquarter' htmlEscape='true'/>",
		 		}
			});
		} 	
	};



var localbodyMap=new Map();
var changeLRTypeMap =new Map();
var getLBListofSelectedLRforFull = function(lRCode,selectedLR){
	var lbTypeCode  = $( '#localBodyTypeCode' ).val();	
	var localBodyCode = $( '#paramLBCode' ).val();
	if(!localbodyMap.has(lRCode)){
		getSelectedLRLocalBodyListForFull(lRCode,lbTypeCode,selectedLR);		
	}else{		
		displayUsedLocalbodyTable(lRCode);
		displayExistingLocalBody();
		var usedLbObj=localbodyMap.get(lRCode);	
		if(usedLbObj.length==1){
			if(usedLbObj[0][0]==localBodyCode){			
				$(selectedLR).attr('checked','checked');
				setChangeCoverageTypeLR(lRCode,selectedLR);
			}
}
};
}
var getLBListofSelectedLRforPart = function(lRCode,selectedLR){
	var lbTypeCode  = $( '#localBodyTypeCode' ).val();	
	if(!localbodyMap.has(lRCode)){
		getSelectedLRLocalBodyListForPart(lRCode,lbTypeCode,selectedLR);	
	}else{
		displayUsedLocalbodyTable(lRCode); 	
		displayExistingLocalBody();	
		$(selectedLR).attr('checked','checked');
		setChangeCoverageTypeLR(lRCode,selectedLR);
	}
};

var setChangeCoverageTypeLR=function(lRCode,selectedLR){
	var changeLRTypeArray=new Array();	
	if(changeLRTypeMap.has(lRCode)){
		changeLRTypeMap.delete(lRCode);
	}else{
		changeLRTypeMap.set(lRCode,$(selectedLR).val());					
	}	
	changeLRTypeMap.forEach(function(value, key) {
		changeLRTypeArray.push(key+"_"+value+"_"+$(selectedLR).attr("paramRegionType"));		
		}, changeLRTypeMap);  
	$( "[id=changeCoverageTypeLRList]" ).val(changeLRTypeArray);	
	
}

var getSelectedLRLocalBodyListForFull=function(lRCode,lbTypeCode,selectedLR){
	var localBodyCode = $( '#paramLBCode' ).val();
	 dwrRestructuredLocalBodyService.getLBListOfSelectedLR(lRCode,lbTypeCode, {
		 callback : function(result) {
				localbodyMap.set(lRCode,result);
				displayUsedLocalbodyTable(lRCode);
				displayExistingLocalBody();
				var usedLbObj=localbodyMap.get(lRCode);				
				if(usedLbObj.length==1){
					if(usedLbObj[0][0]==localBodyCode){			
						$(selectedLR).attr('checked','checked');
						setChangeCoverageTypeLR(lRCode,selectedLR);
					}
				}
		},		
			errorHandler : commonErrorProcess,
			async : true
		});	
};

var getSelectedLRLocalBodyListForPart=function(lRCode,lbTypeCode,selectedLR){	
	 dwrRestructuredLocalBodyService.getLBListOfSelectedLR(lRCode,lbTypeCode, {
		 callback : function(result) {
				localbodyMap.set(lRCode,result);
				displayUsedLocalbodyTable(lRCode);
				displayExistingLocalBody();					
				$(selectedLR).attr('checked','checked');
				setChangeCoverageTypeLR(lRCode,selectedLR);
		},		
			errorHandler : commonErrorProcess,
			async : true
		});	
};

var displayUsedLocalbodyTable=function(lRCode){
	
	 $( '#existingLocalBodyDetails > tbody' ).empty();	
	$( '#existingLocalBodyDetails' ).show();	
	jQuery.each(localbodyMap.get(lRCode), function(index, obj) {
		var templateTR = $("<tr/>");
		var templateTDHQId = $("<td/>");
		$( templateTDHQId ).html(obj[0]);			
		var templateTDHQName = $("<td/>");
		$( templateTDHQName ).html(obj[1]);
		$( templateTR ).append( templateTDHQId ).append( templateTDHQName );
		$( '#tbodyExistLB' ).append( templateTR );
	});
	
}
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
				var _show_text="";
				jQuery.each(result, function(index, obj) {
					var option = $("<option />");
					var lname="Subdistrict";
					if(obj.landRegionType=='T'){
						lname="District";
					}
					_show_text=obj.landRegionNameEnglish+"("+obj.landRegionCode+")";//+lname+":"+obj.parentName+"]";
					//_show_text=obj.landRegionNameEnglish+"("+obj.landRegionCode+")";
					option.val(obj.landRegionCode).text(_show_text);
					if(unmappedCoverageLevel==_JS_VILLAGE_CONSTANT){
						if($('#tr_' + obj.landRegionCode ).length==0){
							options.append(option);
							}
					}
					$(option).attr('title', obj.entityHierarchy);
					options.append(option);
				});
			},
			errorHandler : commonErrorProcess,
			async : true
		});	
	}
};

/**
 * The {@code buildHeadQuarter} used to call dynamic Head Quarter build up.
 */ 
var buildHeadQuarter = function (){	
	 $( '#tblHeadquarter > tbody' ).empty();	
	 var lbTypeLevel = getLocalBodyTypeLevel();	
	var lrCoverageLevel = null;
	var hqHeading = null;
	if( _JS_DISTRICT_PANCHAYAT_LEVEL == lbTypeLevel ) {		
		lrCoverageLevel = "UnmappedDisttLevel";
		hqHeading = "<spring:message code='Label.CONTRIBUTDISTRICTLIST' htmlEscape='true'/>";
	} else if( _JS_INTERMEDIATE_PANCHAYAT_LEVEL ==  lbTypeLevel ) {	
		lrCoverageLevel = "UnmappedIntermediateLevel";
		hqHeading = "<spring:message code='Label.CONTRIBUTESUBDISTRICTLIST' htmlEscape='true'/>";
	} else if ( _JS_VILLAGE_PANCHAYAT_LEVEL ==  lbTypeLevel ) {	
		lrCoverageLevel = "UnmappedVillageLevel";
		hqHeading = "<spring:message code='Label.CONTRIBUTVILLAGELIST' htmlEscape='true'/>";
	}	
	if(!$_checkEmptyObject(lrCoverageLevel) ){
		$( '#lblHQAtLevel' ).html( hqHeading );
		// Generating Template for Partially Mapped Land Regions.
		setSelectedOptionBox(['coverageLRContributing' + lrCoverageLevel]);		
		var lrCoveragesElements = $( "#coverageLRContributing" + lrCoverageLevel + " option:selected" );
		buildHQFromExistingLR();
		if(!$_checkEmptyObject($(lrCoveragesElements).val())){						
			callHQPartTemplate(lrCoveragesElements);
		}
		$("#tblHeadquarter").show();
		var hqElement = '#tblHeadquarter';
		if($(hqElement + ' >tbody >tr').length > 0){
			$( hqElement ).show();
			 var mainRegionCode='${mainRegionCode}';
			$('input[name=headQuarterCodes][value='+mainRegionCode+']').attr('checked',true); 
		}
	}
	
};

var buildHQFromExistingLR = function(){	
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
	templateRadio.attr("name", "headQuarterCodes");
	$( templateRadio ).val(value);	
	$( templateRadio ).change(function() {	
		$('#headQuarterCode').val($(this).val());
		
	});
	$( templateTDHQId ).append(templateRadio);	
	var templateTDHQName = $("<td/>");
	$( templateTDHQName ).html(text);
	$( templateTR ).append( templateTDHQId ).append( templateTDHQName );
	$( '#tbodyHQuarter' ).append( templateTR );
};

var getLocalBodyTypeLevel = function (){
	return '${localBodyTypeLevel}';
}; 


var getUserLevelParameter = function () {
	if(_JS_IS_DISRTICT_LEVEL_LOCAL_BODY){
		return _JS_DISTRICT_CONSTANT;	
	}else {
		return _JS_SUBDISTRICT_CONSTANT;
	}
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
 * The {@code selectExistingGovernmentOrder} used to display dialog box 
 * to show Government Order Details.
 */  
var displayExistingLocalBody = function (){
	 $('#displayExistingLocalBody').modal('show');	
	/* $( "#displayExistingLocalBody" ).dialog({
    	title : "Associated Local bodies",
    	resizable: false,
      	width:400,
      	height:400,
      	modal: true
    }); */
};

var existVMap=new Map();
var newVMap=new Map();
var existVillageCov=new Array();

setCoverageMap=function(code,type){
	if(!existVMap.has(code)){
		existVMap.set(code,type);
		}else{
			existVMap.delete(code);
			existVMap.set(code,type);
		
	}
	
};

getHabitationList=function(){
	//alert("in");
	if (_STATE_WISE_HABIATION_CONFIGURATION == _JS_HABITAION_NOT_CONFIGURE_CONSTANT) {
			alert("Habitations configuration is not  defined in the State, Kindly configure it using configure habitations form available in the configure section.");
	}else{
		existVillageCov=new Array();
		existVMap.forEach(function(value, key) {
			if(value=="P"){
				existVillageCov.push(key);	
			}
			}, existVMap); 
		
		$('#coverageLRContributingUnmappedVillageLevel option').each(function() { 
			var village_arr=$(this).val().split("_");
			if(village_arr[1]=="PART"){
				existVillageCov.push(village_arr[0]);
			}
		});
		
		
		removeAllOptions('avilableHabitation');
		//alert(existVillageCov.length);	
		if(existVillageCov.length>0){
			lgdDwrVillageService.getHabitationList(existVillageCov.toString(), {
				callback : function( result ) {
					var options = $("#avilableHabitation"); 
					
					jQuery.each(result, function(index, obj) {
						var _val=obj.habitationCode+"_N";
						var option = $("<option />");
						option.val(_val).text(obj.habitationNameEnglish);
						options.append(option);
					});
				},
				errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
				async : true
			});
		}
		
		var localBodyCode = $( '#paramLBCode' ).val();
		removeAllOptions('contributingHabiationCodes');
		
		lgdDwrVillageService.getMappedHabitationList(parseInt(localBodyCode), {
			callback : function( result ) {
				var options = $("#contributingHabiationCodes"); 
				
				jQuery.each(result, function(index, obj) {
					var _val=obj.habitationCode+"_E";
					var option = $("<option />");
					option.val(_val).text(obj.habitationNameEnglish);
					options.append(option);
				});
			},
			errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
			async : true
		});
	}
};


/* The {@code removeAllOptions} used to clear drop down box based on their id. 
*/
removeAllOptions=function(id){
	dwr.util.removeAllOptions(id);
};


/**
 * The {@code moveElement} function used move items from one listbox to another,
 * based on copyid,pasteId and action(parameter)
 */
moveElement=function(action){
	var copyId=null;
	var pasteId=null;
	if(action=="FORWARD" || action== "WHOLE" ){
		copyId='avilableHabitation';
		pasteId='contributingHabiationCodes';
	}else if(action=="BACK"|| action=="BACKALL" ){
		copyId='contributingHabiationCodes';
		pasteId='avilableHabitation'; 
	}
	
	if(action=="WHOLE" ||  action=="BACKALL"){
		$('#'+copyId+' option').clone().appendTo('#'+pasteId);
		removeAllOptions(copyId);
	}else if(action=="BACK" || action=="FORWARD"){
		$('#'+copyId+'> option:selected').appendTo('#'+pasteId);
	}
	sortListBox(copyId);
	sortListBox(pasteId);
		
	
};

buildExistingavilableCoverage=function(changeCovId){
	removeAllOptions('avilableExistCoverage');
	removeAllOptions('contributeExistCoverage');
	$('#'+changeCovId+' option').each(function() { 
		if( $(this).is(":disabled") && ($(this).val().split("_")[1] == "FULL" || $(this).val().split("_")[1] == "F")){
			 $(this).clone().appendTo('#avilableExistCoverage');
		}
	});
	
	$('#avilableExistCoverage option').each(function() { 
		$( this).prop( "disabled", false );
	});
	
	 $('#changeExistCoverage').modal('show')
};

moveElementExistCoverage=function(action,landregionType){
	var copyId=null;
	var pasteId=null;
	
	var avilSelObj=$( "#avilableExistCoverage option:selected" );
	var selEntityArr =$(avilSelObj).val().split("_"); 
	
	if(selEntityArr[1]==action[0] || selEntityArr[1]==action){
		alert("coverage aleardy PART/FULL");
	}else{
		if(action=="FULL"){
			dwrRestructuredLocalBodyService.lbPartCoverageExistState(parseInt(_JS_STATE_CODE),landregionType,parseInt(selEntityArr[0]), {
				callback : function( result ) {
					var flag= result ;
					if(flag){
						alert("partly subdistrict mapped with other localbodies");
					}else{
						
						cur_text=$(avilSelObj).text().replace(/PART/g,'FULL');         
						
						
						$('#contributeExistCoverage').append($('<option>',
								 {
								    value:"F_"+ selEntityArr[0]+"_"+landregionType,
								    text : cur_text
								 }));
						$(avilSelObj).remove();
						
					}
				},
				errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
				async : true
			});
		}
		else if(action=="PART"){
			cur_text=$(avilSelObj).text().replace(/FULL/g,'PART');     
			$('#contributeExistCoverage').append($('<option>',
					 {
					    value: "P_"+selEntityArr[0]+"_"+landregionType,
					    text : cur_text
					 }));
			$(avilSelObj).remove();
		
		}
	}
};





/**
 * The {@code levelWiseClearOptionsurban} used to remove option boxes 
 * for local body and land region.
 * @param ELEMENT_IDS
 * @param LB_LR_LABEL
 */
 var levelWiseClearOptionsUrban = function(ELEMENT_IDS, LB_LR_LABEL) {
		var removeOptionElements = {
			"LB" : function() {
				jQuery.each(ELEMENT_IDS, function(index, val) {
					$("SELECT[id='coverageLBAvailable" + val + "']").empty();
					$("SELECT[id='coverageLBContributing" + val + "']").empty();
					
				});
			},
			"LR" : function(sourceElement, destElement) {
				jQuery.each(ELEMENT_IDS, function(index, val) {
					$("SELECT[id='coverageLRAvailableUnmapped" + val + "']").empty();
					//$("SELECT[id='coverageLRContributingUnmapped" + val + "']").empty();
					$('#coverageLBAvailable'+val+' option').each(function() { 
						if( !$(this).is(":disabled")){
							$(this).remove();
						}
					});
				});
			}
		};
		removeOptionElements[LB_LR_LABEL]();
	};
	
	

	/**
	 * The {@code buildSubdistrictCoveredAreas} used create select box options at 
	 * Land Region Coverages for Existing Unmapped Land Regions. 
	 * @param unmappedCoverageLevel
	 * @param localBodyCodes
	 * @param localBodyType
	 * @param elementId
	 */
	var buildLandRegionCoveredAreasUrbans = function (unmappedCoverageLevel, localBodyType, elementId){
	try{
		 var lname="Subdistrict";
		 if(unmappedCoverageLevel != 'V'){
			 lname="district";
		}else{
			var parentArr=new Array();
			 var childArr=new Array();
			 $('#coverageLRContributingUnmappedIntermediateLevel option').each(function() { 
				if($(this).val().split("_")[1]=="PART")
				 parentArr.push($(this).val().split("_")[0]);
			 });
			 
			 $('#coverageLRContributingUnmappedVillageLevel option').each(function() { 
					
				childArr.push($(this).val().split("_")[0]);
				 });
			 
		}
		
		
		 
		if(!jQuery.isEmptyObject(parentArr.toString())){	
			var options = $("#" + elementId ); 
			dwrRestructuredLocalBodyService.fetchCoverageDetailsVillage(unmappedCoverageLevel, parentArr.toString(),childArr.toString(), localBodyType, {
				callback : function( result ) {
					var _show_text="";
					jQuery.each(result, function(index, obj) {
						var option = $("<option />");
						_show_text=obj.landRegionNameEnglish+"("+obj.landRegionCode+")["+lname+":"+obj.parentName+"]";
						option.val(obj.landRegionCode).text(_show_text);
						if(unmappedCoverageLevel==_JS_VILLAGE_CONSTANT){
							if($('#tr_' + obj.landRegionCode ).length==0){
								options.append(option);
								}
						}
						options.append(option);
					});
				},
				errorHandler : commonErrorProcess,
				async : true
			});	
		}
	 }catch(err){
		 alert( err.message);
	 }
	};
	
	var setChangeCoverageTypeLRNew=function(changeCovId){
		var changeLRTypeArray=new Array();
		var deleteLRTypeMap =new Map();
		var landRegionType;
		 $('#contributeExistCoverage option').each(function() { 
				
				var newCovArr= $(this).val().split("_");
				lRCode=newCovArr[1];
				if(landRegionType==null){
					landRegionType=newCovArr[2];
				}
				if(changeLRTypeMap.has(lRCode)){
					changeLRTypeMap.delete(lRCode);
					deleteLRTypeMap.set(lRCode,newCovArr[0]);
				}else{
					changeLRTypeMap.set(lRCode,newCovArr[0]);
					
				}	
			 });
		 
		 	
		 $('#'+changeCovId+' option').each(function() { 
		 		var valArr=$(this).val().split("_");
		 		var lbcode=valArr[0];
		 		if( $(this).is(":disabled") && ((changeLRTypeMap.has(lbcode)) || (deleteLRTypeMap.has(lbcode))) && valArr[1]=="PART"){
		 			$(this).val(valArr[0]+"_FULL");
		 			var temp= $(this).text().replace(/PART/g,'FULL');
		 			var l=temp.indexOf("-")>temp.indexOf("(")?temp.indexOf("("): temp.indexOf("-");
					var dname=temp.substring(0,l);
					dname=$.trim(dname);
					removeVillageAfterChangeLandRegionType(dname,changeCovId);
		 			$(this).text(temp);
		 		}else if( $(this).is(":disabled") && ((changeLRTypeMap.has(lbcode)) || (deleteLRTypeMap.has(lbcode))) && (valArr[1]=="FULL" || valArr[1]=="F")){
		 			$(this).val(valArr[0]+"_PART");
					var temp= $(this).text().replace(/FULL/g,'PART');
					var l=temp.indexOf("-")>temp.indexOf("(")?temp.indexOf("("): temp.indexOf("-");
					var dname=temp.substring(0,l);
					dname=$.trim(dname);
					removeVillageAfterChangeLandRegionType(dname,changeCovId);
					$(this).text(temp)
		 		}
		 	 });	
		 
		 
		 changeLRTypeMap.forEach(function(value, key) {
				changeLRTypeArray.push(key+"_"+value+"_"+landRegionType);		
				}, changeLRTypeMap);  
		 	
		 	
		 	$( "[id=changeCoverageTypeLRList]" ).val(changeLRTypeArray);	
		
	}
	
	removeVillageAfterChangeLandRegionType=function(dname,changeCovId){
		var avilRemoveId = null;
		var contriRemoveId = null;
		if(changeCovId=='coverageLRContributingUnmappedDisttLevel'){
			
			avilRemoveId='coverageLRAvailableUnmappedIntermediateLevel';
			contriRemoveId='coverageLRContributingUnmappedIntermediateLevel';
		}else if(changeCovId=='coverageLRContributingUnmappedIntermediateLevel'){
			avilRemoveId='coverageLRAvailableUnmappedVillageLevel';
			contriRemoveId='coverageLRContributingUnmappedVillageLevel';
		}
		
		if(avilRemoveId != null){
			$('#'+avilRemoveId+' option').each(function() { 
				if($(this).text().indexOf(("Subdistrict:"+dname))>-1){
					$(this).remove();
				}
			});
		}
		
		if(contriRemoveId != null){
			$('#'+contriRemoveId+' option').each(function() { 
				if($(this).text().indexOf(("Subdistrict:"+dname))>-1){
					$(this).remove();
				}
			});
		}
		
	};
	
	var convertCodeArrayUrban = function(selectedCodes) {
		var codesArray = [];
		 $('#'+selectedCodes+' option').each(function() { 
			 var valArr=$(this).val().split("_");
			 if(valArr[1]=="PART"){
				 codesArray.push(valArr[0]);
			 }
		 });
		 return  codesArray;
	};

</script>
