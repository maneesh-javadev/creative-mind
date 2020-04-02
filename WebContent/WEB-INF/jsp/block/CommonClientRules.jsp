<script type="text/javascript" src="<%=contextpthval%>/resources-localbody/scripts/jquery.numeric.js"></script>
<script type="text/javascript">

/**
 * Define Success Message Constatnt.
 */
 var _JS_SUCCESS_CONSTANT = "<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.SUCCESS_MESSAGE.toString()%>"; 
 
/**
 * Defined State Code and District Code which has been set in Controller Map Attribute. 
 */
var _JS_STATE_CODE = '${stateCode}';
var _JS_DISTRICT_CODE = '${districtId}';

$(document).ready( function() {
	//Registering rules for the form
	jQuery.validator.addMethod("regex", function(value, element, regexp) {
        if(!$_checkEmptyObject(value)){
        	var re = new RegExp(regexp);
            return !(this.optional(element) || re.test(value));	
        }else{
        	return true;	
        }
    });
	jQuery.validator.addMethod("regexAllowed", function(value, element, regexp) {
        if(!$_checkEmptyObject(value)){
        	var re = new RegExp(regexp);
            return this.optional(element) || re.test(value);	
        }else{
        	return true;	
        }
    });
	jQuery.validator.addMethod("onefull", function(value, element) {
		var isOneFullSelected = true;
		if($(element).find('option:selected').size() == 1 && $(convertCodeArrayFULL(value)).size() > 0 ){
			isOneFullSelected = false;
		}
		return isOneFullSelected;
	});
	jQuery.validator.addMethod("contributeFromAllLB", function(value, element) {
		var status = true;
		var partLBCodes = convertCodeArray( $( '#coverageLBContributingPanchayatLevel' ).val());
		var paramLBCodesArray = [];
		$($('option:selected', element)).each(function(index, obj) {
			paramLBCodesArray.push($(obj).attr('paramLBCode'));
		});
		jQuery.each(partLBCodes, function(index, localBodyCode) {
			if(!($.inArray(localBodyCode, paramLBCodesArray) > -1)){
				status = false;
				return false;
			}
		});
		return status;
	});
	jQuery.validator.addMethod("contributeAllFull", function(value, element) {
		var status = true;
		var partLBCodes = convertCodeArray( $( '#coverageLBContributingPanchayatLevel' ).val());
		var availableElementId = $( element ).attr('id').replace("Contributing","Available");
		var availableElementObj = $("#" + availableElementId);
		jQuery.each(partLBCodes, function(index, localBodyCode) {
			var array = [];
			$($('option:selected', element)).each(function(index, obj) { 
				var paramLB = $(obj).attr('paramLBCode');
				if(paramLB == localBodyCode){
					array.push($(obj).val());
				}
			});
			var partContributing = convertCodeArray( array );
			var fullContributing = convertCodeArrayFULL( array );
			if(jQuery.isEmptyObject(partContributing) && !jQuery.isEmptyObject(fullContributing)){
				var arrayAvailable = [];
				$($(availableElementObj).find('option')).each(function(index, objAvailable) {	
					var paramLBAvailable = $(objAvailable).attr('paramLBCode');
					if(paramLBAvailable == localBodyCode){
						arrayAvailable.push($(objAvailable).val());
					}
				});
				if(jQuery.isEmptyObject(arrayAvailable)){
					return status = false;
				}
			}
		});
		return status;
	});
	
	jQuery.validator.addMethod("fileUploadValidate", function(value, element) {
		
        var fileElement = null, mimeType = null, fileExtension = null;
		if ( $.browser.msie && $.browser.version < 10) {
			try{
				var objFSO = new ActiveXObject("Scripting.FileSystemObject"); 
				var filePath = $(element)[0].value;
				try{
				fileElement = objFSO.getFile(filePath);
				}
				catch (e) {
					return true;
				}
				if($_checkEmptyObject(fileElement)) return true;
				mimeType = objFSO.GetExtensionName(filePath);
				fileExtension = mimeType;//objFSO.GetFileName(filePath);
			} catch( e ){
				customAlert( 'Please make sure ActiveX is enabled in your IE browser.' );
				return false;
			}
		} else {
			fileElement = $(element)[0].files[0];
			if($_checkEmptyObject(fileElement)) {
				return true;
			}
			mimeType = fileElement.type;
			fileExtension = fileElement.name.split('.').pop();
		}
		return checkUploadedDocs(fileElement.size, '${attachmentMasterGO.individualFileSize}', '${attachmentMasterGO.fileType}', mimeType, fileExtension);
    });
	
	jQuery.validator.addMethod("fileUploadValidateMap", function(value, element) {
		var fileElement = null, mimeType = null, fileExtension = null;;
		if ( $.browser.msie && $.browser.version < 10) {
			try{
				var objFSO = new ActiveXObject("Scripting.FileSystemObject"); 
				var filePath = $(element)[0].value;
					if(! $_checkEmptyObject( filePath ) ){
						try{
						fileElement = objFSO.getFile(filePath);
						}
						catch(e){
							return true;
						}
						mimeType = objFSO.GetExtensionName(filePath);
						fileExtension = mimeType;
					}
					
			} catch( e ){
				customAlert( 'Please make sure ActiveX is enabled in your IE browser.' );
				return false;
			}
		} else {
			fileElement = $(element)[0].files[0];
		}
		if(! $_checkEmptyObject(fileElement))	{
			if( mimeType == null ){
				mimeType = fileElement.type;
				fileExtension = fileElement.name.split('.').pop();
			}
			
			return checkUploadedDocs(fileElement.size, '${attachmentMasterMap.individualFileSize}', '${attachmentMasterMap.fileType}', mimeType, fileExtension);
	 	}
		return true;
	});
	
	jQuery.validator.addMethod("customRanges", function(value, element, ranges) {
		var status = true;
        $("[name=" + $(element).attr('name') + "]").each(function(index, child){
			var childValue = $(child).val();
			if(! $_checkEmptyObject(childValue)){
				var noUpperBound = false, valid = false;
				for(var i = 0; i < ranges.length; i++) {
		            if(ranges[i].length == 1) { 
		                noUpperBound = true;
		            }
		            if(childValue >= ranges[i][0] && (childValue <= ranges[i][1] || noUpperBound)) {
		                valid = true;
		                break;
		            }
		        }
				if(status){
					status = valid;	
				}
			}
		});	
        return status;
	});
	
	jQuery.validator.addMethod("gisNodesMismatch", function() {
		var status = true;
        var lonArr = [];
		$("[name=longitude]").each(function(index, obj){
			if(! $_checkEmptyObject($(this).val())){
				lonArr.push($(this).val());
			}
		});	
		var latArr = [];
		$("[name=latitude]").each(function(index, obj){
			if(! $_checkEmptyObject($(this).val())){
				latArr.push($(this).val());
			}	
		});	
		if(lonArr.length != latArr.length){
			status = false;
		}
        return status;
	});
	
	jQuery.validator.addMethod("requiredHQ", function() {
		
		return !$_checkEmptyObject($('[name = headQuarterCode]:checked').val()); //$( '#tblHeadquarter >tbody >tr').length > 0 || 
	
	});
});	

/**
 * The {@code checkUploadedDocs} used to check uploaded file's type   
 * size and mime type.
 */
var checkUploadedDocs = function (iSize, maxFileSize, validFileTypes, mimeType, fileExtension){
	validFileTypes = validFileTypes + ",application/x-download";  
	var sizeInMB = (iSize / (1024*1024)).toFixed(2);
	//Validating for 5 MB size.
    if( parseFloat( sizeInMB ) > parseFloat( maxFileSize ) ) {	
    	return false;
    }
    var status = false, extenstions = validFileTypes.split(",");
    $(extenstions).each(function(index, value){
    	if(mimeType.indexOf(value) > -1 && extenstions.indexOf(fileExtension) > -1){
			status = true;
			return false;
		}
	});	
    return status;
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
 * The {@code disableAllButtons} used to disabled all 
 * buttons (input or submit).
 */
var disableAllButtons = function (){
	$("input[type='button']").attr('disabled','disabled');
	$("input[type='submit']").attr('disabled','disabled');
};

/**
 * The {@code validatePartMapping} used to check partially mapped / partially un-mapped
 * land regions can't be assigned as whole.
 */
var validatePartMapping = function(sourceElement) {
	var selectedText = $("#" + sourceElement + " option:selected").text().toUpperCase();
	return (selectedText.indexOf('( PART )') > -1) || (selectedText.indexOf('(PARTLY)') > -1);
};

/**
 * The {@code setSelectedOptionBox} used to set all options as selected in 
 * multi select box.  
 * Taking array of element ids as parameter.
 * @param _PARAM_ELEMENT_IDS_ARRAY
 */
var setSelectedOptionBox = function(_PARAM_ELEMENT_IDS_ARRAY) {
	jQuery.each(_PARAM_ELEMENT_IDS_ARRAY, function(index, val) {
		$("#" + val + " option").attr("selected", "selected");
	});
}; 


/**
 * The {@code levelWiseClearOptions} used to remove option boxes 
 * for local body and land region.
 * @param ELEMENT_IDS
 * @param LB_LR_LABEL
 */
var levelWiseClearOptions = function(ELEMENT_IDS, LB_LR_LABEL) {
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
				$("SELECT[id='coverageLRContributingUnmapped" + val + "']").empty();
			});
		}
	};
	removeOptionElements[LB_LR_LABEL]();
};

/**
 * The {@code errorLbTypeProcess} called when error 
 * occured while DWR call {@link buildLocalBodyHierarchy}.
 */
var errorLbTypeProcess = function() {
	$("#errorLocalBodyType").html("<spring:message code='Error.invalidlbtype' htmlEscape='true'/>");
};

/**
 * The {@code commonErrorProcess} called when error 
 * occured while DWR call {@link buildLocalBodyHierarchy}.
 */
var commonErrorProcess = function() {
	customAlert("<spring:message code='Error.errfetchndata' htmlEscape='true'/>");
};

/**
 * The {@code concatPartFull} used to append text in provided in input with
 * eithor (PATR) or (Full).
 */
var concatPartFull = function(coverageType, elementText) {
	var callAppendText = {
		"P" : function() {
			elementText = elementText.concat(" ( PART )");
		},
		"F" : function() {
			elementText = elementText.concat(" ( FULL )");
		}
	};
	callAppendText[coverageType]();
	return elementText;
};


/**
 * The {@code moveElements} used to select elements from source and add into  
 * destination element based on different button events i.e. (Whole, Part, Back and Reset).
 * @param source
 * @param destination
 * @param specification
 * @param isBack
 * @param isReset
 */
var moveElements = function(source, destination, specification, isBack, isReset) {
	if (isReset) {
		setSelectedOptionBox([ source ]);
	}
	$("#" + source + " option:selected").each(function() {
		var optionText = $(this).text();
		var optionVal = $(this).val();
		var optionTitle=$(this).attr('title')
		/* if(optionValArr.indexOf("_")>-1){
			optionVal=optionValArr.split("_")[0];
		}else{
			optionVal=optionValArr;
		} */
		var paramLBCode = $(this).attr('paramLBCode');
		var option = $("<option />");
		if (isBack || isReset) {
			optionText = optionText.replace(" ( FULL )", "").replace(" ( PART )", "");
			optionVal = optionVal.replace("_FULL", "").replace("_PART", "");
		} else {
			optionText = optionText.concat(" ( " + specification + " )");
			optionVal = optionVal.concat("_" + specification);
		}
		option.val(optionVal).text(optionText);
		if (jQuery.type( paramLBCode ) != "undefined") {
			$(option).attr('paramLBCode', paramLBCode);
		}
		$(option).attr('title', optionTitle);
		$("#" + destination).append(option);
	});
	$("#" + source + " option:selected").remove();
};

/**
 * The {@code convertCodeArray} used to convert partly mapped Local Body Codes with 'PART' text
 * as comma separated arry of LB codes.
 * @param selectedCodes
 */
var convertCodeArray = function(selectedCodes) {
	var codesArray = [];
	$.each(jQuery.grep($.makeArray(selectedCodes), function(a) {
		a = a.indexOf('PART') > -1;
		return a;
	}), function(i, v) {
		codesArray.push(v.replace('_PART', ''));
	});
	return codesArray;
};

/**
 * The {@code convertCodeArrayFULL} used to convert partly mapped Local Body Codes with 'FULL' text
 * as comma separated arry of LB codes.
 * @param selectedCodes
 */
var convertCodeArrayFULL = function(selectedCodes) {
	var codesArray = [];
	$.each(jQuery.grep($.makeArray(selectedCodes), function(a) {
		a = a.indexOf('FULL') > -1;
		return a;
	}), function(i, v) {
		codesArray.push(v.replace('_FULL', ''));
	});
	return codesArray;
};

/**
 * The {@code latitudelongitudeOnload} used for setting Longitude and Lattitude 
 * values from Server.
 * Cases Handled for server side validation failure,
 * Modification of Drafted Local Body.
 */
var latitudelongitudeOnload = function(){
	var longitudeData = '${localBodyForm.longitude}';
	var latitudeData = '${localBodyForm.latitude}';
	if(isParseJson( '${modifyProcess}' )){
		var coordinates = '${localBodyForm.coordinates}';
		var array = coordinates.split(",");
		var arraysize = $(array).size() - 1;
		$(array).each(function(i){
			var childArray = array[i].split(":");
			longitudeData = longitudeData.concat(childArray[0]);
			latitudeData = latitudeData.concat(childArray[1]);
			if(i != arraysize){
				longitudeData = longitudeData.concat(",");
				latitudeData = latitudeData.concat(",");
			}
		});
	}
	
	if(!$_checkEmptyObject(longitudeData) && !$_checkEmptyObject(latitudeData)){
		var longitudeArray = longitudeData.split(",");
		var latitudeArray = latitudeData.split(",");
		$("#longitude").val(longitudeArray[0]);
		$("#latitude").val(latitudeArray[0]);
		$(longitudeArray).each(function(index){
			if(index > 0){
				buildLatitudeLongitude(longitudeArray[index], latitudeArray[index]);
			}
		});	 
	}
};

var checkDecimalPlaces = function (e){
	var maxPlaces = 3, integer = e.target.value.split('.')[0], mantissa = e.target.value.split('.')[1];
    if (typeof mantissa === 'undefined') {
        mantissa = '';
    }
    if (mantissa.length > maxPlaces) {
        e.target.value = integer + '.' + mantissa.substring(0, maxPlaces);
    }
};

/**
 * The {@code buildLatitudeLongitude} function used to create dynamic  
 * row elements to store latitude and longitude.
 */
var counter = 1;
var buildLatitudeLongitude = function(longval, latval ) {
	var template = '<div class="row">' 
				 + '<div class="col"><input type="text" name="longitude" id="longitude" maxlength="6" value="' + longval + '"/></div>'
			     + '<div class="col"><input type="text" name="latitude" id="latitude" maxlength="6" value="' + latval + '"/></div>'
			     + '<div class="col"><input type="button" class="bttn remove" id="removeLatLong_' + counter + '" value="Remove"/></div>'
			     + '</div>';
	$('#divLatitudeLongitude').before(template);
	$('#removeLatLong_' + counter).click(function() {
		$(this).closest('.row').remove();
	});
	$("#longitude, #latitude").on('keyup', function(e){
		checkDecimalPlaces(e)
	}).numeric({ decimal: ".", negative: false }, function() {
		this.value = ""; this.focus(); 
	});
	counter++;
};

var getChangeCoverageProcessId = function () {
	var _JS_CC_PROCESS_ID = '<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.LB_CHANGE_COVERAGE_PROCESS.toString()%>';
	
	if(! isParseJson( '${modifyProcess}' )){
		 _JS_CC_PROCESS_ID = null; 
	 }
	return _JS_CC_PROCESS_ID;
};

var getCreateLBProcessId = function () {
	var _JS_CREATE_LB_PROCESS_ID = '<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.LB_CREATION_PROCESS.toString()%>';
	
	if(! isParseJson('${modifyProcess}' ))
		{
		_JS_CREATE_LB_PROCESS_ID = null; 
	 }
	
	
	return _JS_CREATE_LB_PROCESS_ID;
};

customAlert=function(msg){
	$("#customalertboxbody").text(msg);
	$('#customalertbox').modal('show');	
}

//-->	
</script>
<div class="modal fade" id="customalertbox" tabindex="-1" role="dialog" >
	<div class="modal-dialog" >
		<div class="modal-content">
			<div class="modal-header">
				 	<button type="button" class="close" data-dismiss="modal">&times;</button>
 			  		<h4 class="modal-title" >Alert</h4>
			</div>
			<div class="modal-body" id="customalertboxbody">
     
			</div>
    			
		</div>
	</div>
</div>