<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/lgdDwrVillageService.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/lgdDwrSubDistrictService.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/dwrRestructuredLocalBodyService.js"></script>

<script>

var lbtype = $('#localBodyType option:selected');
var _JS_STATE_CODE ="<c:out value='${stateCode}' escapeXml='true'></c:out>";
var _JS_DISTRICT_CODE ="<c:out value='${districtId}' escapeXml='true'></c:out>";
var parentType="<c:out value='${habitationForm.parentType}' escapeXml='true'></c:out>";
$(document).ready(function() {
	
	
	
	if(parentType=="G"){
		$("#divDisplayVillage").hide(); 
		$("#divCreateDynamicHierarchy").show();
	}else{
		$("#divCreateDynamicHierarchy").hide(); 
		$("#divDisplayVillage").show();
	}
	
	

	
	$("#ddSourceDistrict").change(function() {
		$('#ddSourceSubDistrict option[value != ""]').remove();
		$('#ddSourceVillage option[value != ""]').remove();
		if(isEmptyObject($(this).val()))return false;
		$( '#errorddSourceDistrict' ).text("");
		buildSubDistrict($(this).val());
	});
	
	
	$("#ddSourceSubDistrict").change(function() {
		$('#ddSourceVillage option[value != ""]').remove();
		if(isEmptyObject($(this).val()))return false;
		$( '#errorddSourceSubDistrict' ).text("");
		buildVillage($(this).val());
	});
	
	$("#ddSourceVillage").change(function() {
		if(isEmptyObject($(this).val()))return false;
		$( '#errorddSourceVillage' ).text("");
	});
	
	//alert(parentType);
	 $("#btnFormActionSave").click(function() {
		 validateHabitationForm(); 
	  });
	
	/**
	 * The habitaionNameEnglish keypress event allow only alphanumbric and some special character{} . 
	 */
	 $("INPUT[name^=habitationNameEnglish]").keypress(function(e){
		// get the key that was pressed
		
		$( '#errhabitationNameEnglish').text(""); 	
		var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
		
		//alert(key);
			if(key == 13 && this.nodeName.toLowerCase() == "input")
			{
				return true;
			}
			else if(key == 13)
			{
				return false;
			}
			var allow = false;
			// allow Ctrl+A
			if((e.ctrlKey && key == 97 /* firefox */) || (e.ctrlKey && key == 65) /* opera */) return true;
			// allow Ctrl+X (cut)
			if((e.ctrlKey && key == 120 /* firefox */) || (e.ctrlKey && key == 88) /* opera */) return true;
			// allow Ctrl+C (copy)
			if((e.ctrlKey && key == 99 /* firefox */) || (e.ctrlKey && key == 67) /* opera */) return true;
			// allow Ctrl+Z (undo)
			if((e.ctrlKey && key == 122 /* firefox */) || (e.ctrlKey && key == 90) /* opera */) return true;
			// allow or deny Ctrl+V (paste), Shift+Ins
			if((e.ctrlKey && key == 118 /* firefox */) || (e.ctrlKey && key == 86) /* opera */
			|| (e.shiftKey && key == 45)) return false;
			
			if(this.value.length==0) //intial letter 
			{
				if(key>64 && key<91 /* A-Z */  || key>96 && key<123  /*  a-z */ ){
					
					return true;
				}else{
					$( '#errhabitationNameEnglish').text("<spring:message code='error.first.letter.must.be.alphabet.habiation' htmlEscape='true'/>"); 
					return false;
				}
			}else{
				if(
						
						key==126 /* ~ */ ||
						key==96 /* ` */ ||
						key==33 /* ! */ ||
						key==64 /* @ */ ||
						key==35 /* # */ ||
						key==36 /* $ */ ||
						key==37 /* % */ ||
						key==94 /* ^ */ ||
						key==38 /* & */ ||
						key==42 /* * */ ||
						key==95 /* - */ ||
						key==43 /* + */ ||
						key==44 /* , */ ||
						key==61 /* = */ ||
						key==123 /* { */ ||
						key==125 /* } */ ||
						key==47  /* / */  ||
						key==124 /* | */ ||
						key==92 /* \ */ ||
						key==39 /* ' */ ||
						key==34 /* " */ ||
						key==58 /* : */ ||
						key==59 /* ; */ ||
						key==60 /* < */ ||
						key==62 /* > */ ||
						key==63 /* ? */
						
						
					){
					$( '#errhabitationNameEnglish').text("<spring:message code='Error.invalidchar' htmlEscape='true'/>"); 
					return false;
					
				}
				
				if(key==32)/* Space Key */
				{
					if(spaceCount>0){
						$( '#errhabitationNameEnglish').text("<spring:message code='Error.invalidspace' htmlEscape='true'/>"); 
						return false;
					}
					spaceCount++;
				}else {
					spaceCount=0;
				}
			}
		
	 });
	 
	 
	 registerCallDynamicHierarchy(lbtype);
	 
	
	<%--  $("#effectiveDate").datepicker({
			changeMonth: true,
	        changeYear: true,
			dateFormat: 'dd-mm-yy',
			showOn: 'both',
			onSelect: function(selected) {
				
			},
			buttonImage: "<%=contextpthval%>/images/calender.gif",
			buttonImageOnly: true,
		}); --%>
	 
	
});


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
		 if((localbodyTypeCode<=3 )){
			 
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
			templateError.attr("class", "errormessage");
			
			// Adding Dynamic Elements to Template Div.
			templateUL.append(templateLabel);
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
		 if(selectedElementTiersetupCode < tiersetupCode   ){
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
				$( '#error' + $( this ).attr('id') ).text("<spring:message code='Label.SELECT' htmlEscape='true'/>"+" "+"<spring:message code='common.localBody' htmlEscape='true'/>");	 
			 }
		 });
		 return false;
	}
	return true; 
};

validateHabitationForm=function(){
	 $( "span[id^=error]" ).each(function(){
			$( this ).text("");
	    });
	var error=true; 
	var errors=new Array();
	errors[0]= validateHabitationNameEng();
	
	//errors[2]=validatePinCode();
	if (parentType=='G') {
	
		var element = $( '[name = localBodyLevelCodes]' );
		var localBodyElement = $( element )[$( element ).length - 1];
		if(!$_checkEmptyObject(localBodyElement) && !validateLBCode(localBodyElement)){
			errors[1]= false;
		}
		
		
	}else{
		if(isEmptyObject($('#ddSourceDistrict').val())){
			$( '#errorddSourceDistrict' ).text("Please select a District.");
			errors[1]= false;
		}
		if(isEmptyObject($('#ddSourceSubDistrict').val())){
			$( '#errorddSourceSubDistrict' ).text("Please select a Sub-District.");
			errors[2]= false;
		} 
		if(isEmptyObject($('#ddSourceVillage').val())){
			$( '#errorddSourceVillage' ).text("Please select a Village.");
			errors[3]= false;
		} 
		
	}
	
	for ( var i = 0; i < errors.length; i++) {
		if (errors[i] == false) {
			error = false;
			break;
		}

	}
	if(error){
	
		$( "#btnFormActionSave" ).prop( "disabled", true );
		
		callActionUrl('buildHabitation.htm');
	}
};




callActionUrl=function(url){
 	/* document.forms['sectionForm'].action = "buildSelection.htm?<csrf:token uri='"buildSelection.htm'/>";
	document.forms['sectionForm'].method = "POST";
    document.forms['sectionForm'].submit(); */
   
    $( 'form[id=habitationForm]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
	$( 'form[id=habitationForm]' ).attr('method','post');
	$( 'form[id=habitationForm]' ).submit();
};


validateHabitationNameEng=function(){
	
	var re = new RegExp("^[a-zA-Z0-9 \.\[\]\(\)]+$", "g");
	//var re = new RegExp("^[a-zA-Z0-9 \.\,\(\)\/-\]+$", "g");
	 var habitationNameEng=$("#habitationNameEnglish").val();
	 //alert(SectionNameEng);
	 //alert(SectionNameEng.charAt(0).charCodeAt());
	 if($_checkEmptyObject(habitationNameEng)){
		 $( '#errhabitationNameEnglish').text("<spring:message code='Label.habitation.name.required' htmlEscape='true'/>");
		 return false;
	 }else{
		 if(re.test(habitationNameEng)){
			 $( '#errhabitationNameEnglish').text("<spring:message code='Error.invalidchar' htmlEscape='true'/>");	 
			 return false;
		 } else{
			 var key=habitationNameEng.charAt(0).charCodeAt();
			 //alert(key);
			 if(!(key>64 && key<91 /* A-Z */  || key>96 && key<123  /*  a-z */)){
				 $( '#errhabitationNameEnglish').text("<spring:message code='error.first.letter.must.be.alphabet.habitation' htmlEscape='true'/>");	
				 return false;
			 }else{
				 for(i=0;i<habitationNameEng.length;i++){
					 key=habitationNameEng.charAt(i).charCodeAt();
					 if(key==32)/* Space Key */
						{
							if(spaceCount>0){
								$( '#errhabitationNameEnglish').text("<spring:message code='Error.invalidspace' htmlEscape='true'/>"); 
								return false;
							}
							spaceCount++;
						}else {
							spaceCount=0;
						}
				 }
			 }
			 
			 
		 }
	 }
	 return true;
};


var buildSubDistrict = function(districtCode){
	lgdDwrSubDistrictService.getSubDistrictList(parseInt(districtCode), {
		callback : function( result ) {
			var options = $("#ddSourceSubDistrict"); 
			jQuery.each(result, function(index, obj) {
				var option = $("<option />");
				option.val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
				options.append(option);
			});
		},
		errorHandler : function(errorString, exception) { alert(errorString + " Bauwa"); alert(exception); },
		async : true
	});
};


var buildVillage = function(subdistrictCode){
	lgdDwrVillageService.getVillageList(parseInt(subdistrictCode), {
		callback : function( result ) {
			var options = $("#ddSourceVillage"); 
			jQuery.each(result, function(index, obj) {
				var option = $("<option />");
				option.val(obj.villageCode).text(obj.villageNameEnglish);
				options.append(option);
			});
		},
		errorHandler : function(errorString, exception) { alert(errorString + " Bauwa"); alert(exception); },
		async : true
	});
};

</script>