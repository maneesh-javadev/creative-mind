<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/lgdDwrBlockService.js"></script>
<script type='text/javascript'>

var _JS_STATE_CODE = '<c:out value="${stateCode}"/>';
var _JS_DISTRICT_CODE = '<c:out value="${districtId}"/>';


/**
 * The {@code isDistrictUser} used to check logged-in user   
 * is of district or state.
 */
var getJSDistrictCode = function (){
	if(_JS_DISTRICT_CODE != null && parseInt(_JS_DISTRICT_CODE) > 0){
		return _JS_DISTRICT_CODE;
	} else{
		return $("#ddDistrict").val();
	}
}

var _checkEmptyObject = function(obj) {
	if (jQuery.type(obj) === "undefined" || (obj == null || $.trim(obj).length == 0)) {
		return true;
	}
	return false;
};

$(document).ready(function() {
	if(isParseJson('<c:out value="${districtId gt 0}"/>')){
		buildBlockPanchayatBlockSyncUI(_JS_STATE_CODE, getJSDistrictCode());
	}
	
	$("#ddDistrict").change(function() {
		buildBlockPanchayatBlockSyncUI(_JS_STATE_CODE, $(this).val());
	});
	
	$('#btnCreateBlock').click(function() {
		var districtCode = getJSDistrictCode();
		var blockPanchayatCode = $("#paramBlockNameEnglish").attr('paramBPCodeForCreate');
		createNewBlockForSync(districtCode, blockPanchayatCode);
	});
	
	$('#btnClose').click(function() {
		var blockPanchayatCode = $("#paramBlockNameEnglish").attr('paramBPCodeForCreate');
		$("#selBoxBlock_" + blockPanchayatCode).val("");
		$('#divCreateBlockUI').dialog('close');
	});
	
	$('#btnSynchronizeBP').click(function() {
		var multiCodesToSync = "";
		$(':checkbox:checked').each(function(i){
	        var blockPanchayatCode = $(this).val();
			var blockCode = $("#selBoxBlock_" + blockPanchayatCode).val();
			if (_checkEmptyObject( blockCode ) || blockCode == 'createBlock'){	
				blockCode = $("#txtBlockCode_" + blockPanchayatCode).val();
			}
			var codesToSync = blockPanchayatCode + ":" + blockCode;	
			multiCodesToSync += codesToSync + ",";
	    });	
		if(_checkEmptyObject(multiCodesToSync)){
			alert("Please Select one or more Block Panchayat (s) for Sync.")
			return false;
		}
		synchropnizeAtDatabase(getJSDistrictCode(), multiCodesToSync.slice(0, -1));
	});
});

var buildBlockPanchayatBlockSyncUI = function (stateCode, districtCode){
	$( '#tblBlockPanchayats > tbody').empty();$("#divShowData").hide();
	if( _checkEmptyObject( districtCode )){
		return false;
	}
	lgdDwrBlockService.onloadAttributesBPMapping(parseInt(stateCode), parseInt(districtCode),{
		callback : function( result ) {
			buildTableTemplate(result.listOfBlockPanchayats, result.listOfBlocks, result.listSyncedBlocks, districtCode);
			$("#divShowData").show();
		},
		errorHandler : function(errorString) { customAlert(errorString);},
		async : true
	}); 
};


var grepBlockDeatailsforBP = function(paramBlocks, paramBlockPanchayatName) {
	var blockArray = [];
	$.each(jQuery.grep(paramBlocks, function(a) {
		return ($.trim(a.blockNameEnglish) == $.trim(paramBlockPanchayatName));
	}), function(i, v) {
		blockArray.push(v.blockCode);
		blockArray.push(v.blockNameEnglish);
	});
	return blockArray;
};


var grepUnsedBlocks = function(paramBlocks, paramBPs, paramSyncedBlocks) {
	var unusedBlocksArray = [];
	$.each(jQuery.grep(paramBlocks, function(a) {
		var check = true;
		$.each(paramBPs	, function(index, objBP) {
			if($.trim(a.blockNameEnglish) == $.trim(objBP.localBodyNameEnglish)){
				check = false;
				return false;
			}
		});
		if( check ){
			$.each(paramSyncedBlocks, function(index, objSynced) {
				if($.trim(a.blockNameEnglish) == $.trim(objSynced[2])){
					check = false;
					return false;
				}
			});
		}
		return check;
	}), function(i, v) {
		unusedBlocksArray.push(v);
	});
	return unusedBlocksArray;
};



var buildTableTemplate = function ( listOfBlockPanchayats, listOfBlocks, listSyncedBlocks, districtCode){
	
	var unusedBlocks = grepUnsedBlocks(listOfBlocks, listOfBlockPanchayats, listSyncedBlocks);
	var templateTBODY = $("<tbody/>");
	jQuery.each(listOfBlockPanchayats, function(index, obj) {
		var blockPanchayatCode = obj.localBodyCode;
		var blockArray = grepBlockDeatailsforBP(listOfBlocks, obj.localBodyNameEnglish);
		var templateTR = $("<tr/>");
		templateTR.attr("id", "tr_" + blockPanchayatCode);
		if(obj.isSynced){
			templateTR.addClass("blushBG");
		}
		var templateTDCheckBox = $("<td/>");
		var templateChk = $( "<input />" );
		templateChk.attr("type", "checkbox");
		templateChk.attr("id", "checkbox_" + blockPanchayatCode);
		templateChk.val(blockPanchayatCode);
		$( templateTDCheckBox ).append(templateChk);
		
		var templateTD = $("<td/>");
		var templateBPLabel = $( "<label />" );
		templateBPLabel.html(obj.localBodyNameEnglish);
		$( templateTD ).append(templateBPLabel);
		
		var templateTDBlock = $("<td/>");
		templateTDBlock.attr('id', 'tdBName_' + blockPanchayatCode);
		var templateBlockCode = $( "<input />" );
		templateBlockCode.attr("type", "hidden");
		templateBlockCode.attr("id", "txtBlockCode_" + blockPanchayatCode);
		
		if(blockArray.length > 0){
			templateBlockCode.val(blockArray[0]); //Block Code
			var templateBlockLabel = $( "<label />" );
			templateBlockLabel.html(blockArray[1]);	//Block Name English
			$( templateTDBlock ).append(templateBlockLabel);
		} else {
			var syncedUnmatchedBlocks = getUnmachedBlockDetails(listSyncedBlocks, blockPanchayatCode);
			if(_checkEmptyObject(syncedUnmatchedBlocks)) {
				var autoCompCombobox = $( "<select />" ).append("<option value=''>Select</option>");
			    autoCompCombobox.attr('paramBPName', obj.localBodyNameEnglish);
			    autoCompCombobox.attr('paramBPCode', blockPanchayatCode);
			    autoCompCombobox.attr('id', 'selBoxBlock_' + blockPanchayatCode);
			    autoCompCombobox.addClass("combofield");
			    var optGrpExisting = $( "<optgroup/> ")
			    optGrpExisting.attr("label", "Existing Blocks");
			    $( autoCompCombobox ).append(optGrpExisting);
			    jQuery.each(unusedBlocks, function(index, objBlock) {
			    	var option = $( "<option/> ")
				    option.val(objBlock.blockCode).text(objBlock.blockNameEnglish);
				    $(optGrpExisting).append(option);	
			    });
			    var optGrpNew = $( "<optgroup/> ")
			    optGrpNew.attr("label", "New Block ");
			    var optionN = $( "<option/> ")
			    optionN.val("createBlock").text("Create New Block");
			    $(optGrpNew).append(optionN);
			    $(autoCompCombobox).change(function() {
			    	var chkboxObj = $( "#checkbox_" + $(this).attr('paramBPCode') );
		    		chkboxObj.attr('checked', false);
		    		chkboxObj.hide();
		    		$( "#btnSync_" + $(this).attr('paramBPCode') ).hide();
			    	if($(this).val() == "createBlock"){
			    		$( "#divCreateBlockUI" ).dialog({
			    	    	title : "Create New Block",
			    	    	resizable: false, width:700, height:300, autoOpen: false, modal: true,
			    	    	position: { my: "center", at: "center", of: window },
			    	    	show: {
			    	          effect: "blind",
			    	          duration: 1000
			    	        },
			    	        hide: {
			    	          effect: "explode",
			    	          duration: 1000
			    	        },
			    	        close: function() {
			    	        	$("#selBoxBlock_" + blockPanchayatCode).val("");
			    	        }
			    	    });
			    		$( "#divCreateBlockUI" ).dialog( "open" );
			    		$( "#paramBlockNameEnglish" ).val( $(this).attr('paramBPName') );
			    		$( "#paramBlockNameEnglish" ).attr('paramBPCodeForCreate', $(this).attr('paramBPCode') );
			    	} else if(!_checkEmptyObject($(this).val())) {
			    		    $( "#checkbox_" + $(this).attr('paramBPCode') ).show();
				    		$( "#btnSync_" + $(this).attr('paramBPCode') ).show();
			    	}
			    });
			    $( autoCompCombobox ).append(optGrpNew);
				$( templateTDBlock ).append( autoCompCombobox );
				templateChk.css("display","none");				
			} else {
				templateBlockCode.val(syncedUnmatchedBlocks[0]); // Block Code
				var templateBlockUnmatched = $( "<label />" );
				templateBlockUnmatched.html(syncedUnmatchedBlocks[1]);// Block Name English
				$( templateTDBlock ).append(templateBlockUnmatched);
			}
		}
		$( templateTDBlock ).append(templateBlockCode);
		
		/*var templateTDSync 	= $("<td align='center'/>");
		
		var templateBtn = $( "<input />" );
		templateBtn.attr("type", "button");
		templateBtn.attr("id", "btnSync_" + blockPanchayatCode);
		templateBtn.val("Sync");
		if(blockArray.length == 0){
			templateBtn.css("display","none");
		}
		$(templateBtn).click(function() {
			var checkboxObj = $("#checkbox_" + blockPanchayatCode);
			if(checkboxObj.is(":checked")){
				var blockCode = $("#selBoxBlock_" + blockPanchayatCode).val();
				if(_checkEmptyObject( blockCode ) || blockCode == 'createBlock') {	
					blockCode = $("#txtBlockCode_" + blockPanchayatCode).val();
				}
				var codesToSync = blockPanchayatCode + ":" + blockCode;	
				synchropnizeAtDatabase(districtCode, codesToSync);
			} else {
				customAlert("Please Block Panchayat for synchronization.");
			}
		});
		$( templateTDSync ).append(templateBtn);*/
		
		$( templateTR ).append( templateTDCheckBox ).append( templateTD ).append( templateTDBlock );//.append( templateTDSync );
		$( templateTBODY ).append( templateTR );
	});
	$( '#tblBlockPanchayats' ).append( templateTBODY );
};

var createNewBlockForSync = function (districtCode, blockPanchayatCode){
	lgdDwrBlockService.createNewBlockForSync(parseInt(districtCode), parseInt(blockPanchayatCode),{
		callback : function( result ) {
			var newBlockDetails = result.split(",");
			if( newBlockDetails.length == 2 ){
				$('#txtBlockCode_' + blockPanchayatCode).val(newBlockDetails[0]);
				var templateTDBlock = $('#tdBName_' + blockPanchayatCode);
				var templateBlockLabel = $( "<label />" );
				templateBlockLabel.html(newBlockDetails[1]);
				$( templateTDBlock ).append(templateBlockLabel);
				$( "#selBoxBlock_" + blockPanchayatCode).hide();
				$( "#checkbox_" + blockPanchayatCode).show();
				$( "#btnSync_" + blockPanchayatCode).show();
				$( "#divCreateBlockUI").dialog('close');
			} else {
				customAlert(result);
			}	
		},
		errorHandler : function(errorString, exception) { customAlert("Error in DWR processing."); },
		async : true
	}); 
};



var synchropnizeAtDatabase = function (districtCode, paramSyncCodes){
	lgdDwrBlockService.syncProcessBlockWithBP(parseInt(districtCode), paramSyncCodes,{
		callback : function( result ) {
			if("SUCCESS" == result){
				var bpCodesArr = paramSyncCodes.split(",");
				jQuery.each(bpCodesArr, function(index, obj) {
					var syncedBPCode = obj.split(":")[0];
					$("#tr_" + syncedBPCode).addClass("blushBG");
					var selBox = $("#selBoxBlock_" + syncedBPCode);
					if( selBox.length > 0 ){
						var selectedBlockCode = selBox.val();
						$( "#txtBlockCode_" + syncedBPCode).val(selectedBlockCode);
						var templateTDBlock = $('#tdBName_' + syncedBPCode);
						var templateBlockLabel = $( "<label />" );
						templateBlockLabel.html(selBox.find('option:selected').text());
						$( templateTDBlock ).append(templateBlockLabel);
						$("option[value='" + selectedBlockCode + "']").remove();
						selBox.remove();
					}
				});
				$( "input:checked" ).removeAttr('checked');
				alert("Synchronization Process Completed Successfully.");
			}else{
				alert(result);	
			}
		},
		errorHandler : function(errorString) { customAlert(errorString);},
		async : true
	}); 
};

var getUnmachedBlockDetails = function(paramSyncedBlocks, bpCode){
	var syncedBlockArray = []; 
	$.each(paramSyncedBlocks, function(index, objSynced) {
		if(bpCode == objSynced[0]){
			syncedBlockArray.push(objSynced[1]); // Block Code
			syncedBlockArray.push(objSynced[2]); // Block Name English
			return false;
		}
	});
	return syncedBlockArray;
};

</script>
