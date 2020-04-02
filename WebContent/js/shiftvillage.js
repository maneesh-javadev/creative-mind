function getList(id) {

	getTargetState(id);
	getDistrictList(id);
}

function getDistrictListGlobal(id) {

	lgdDwrDistrictService.getDistrictListGlobal(id, {
		callback : handleDistrictGlobalSuccess,
		errorHandler : handleDistrictGlobalError
	});

}

function handleDistrictGlobalSuccess(data) {
	var fieldId = 'ddSourceDistrict';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddSourceDistrict');
	st.add(new Option('---------Select----------', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleDistrictGlobalError() {
	// Show a popup message
	alert("No data found!");
}

function getDistrictList(id) {
	if (id != '') {
		getTargetStateShiftVillage(id);
	}
	if (id != '') {
		lgdDwrDistrictService.getDistrictList(id, {
			callback : handleDistrictSuccess,
			errorHandler : handleDistrictError
		});
	}
}

function handleDistrictSuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddSourceDistrict';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddSourceDistrict');
	st.add(new Option('---------Select----------', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleDistrictError() {
	// Show a popup message
	alert("No data found!");
}

function getTargetStateShiftVillage(id) {

	lgdDwrStateService.getStateTargetList(id, {
		callback : handleTargetStateShiftVillageSuccess,
		errorHandler : handleTargetStateShiftVillageError
	});

}

// data contains the returned value
function handleTargetStateShiftVillageSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddTargetState';
	var valueText = 'stateCode';
	var nameText = 'stateNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddTargetState');
	st.add(new Option('Select Target State', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleTargetStateShiftVillageError() {
	alert("No data found!");
}

function getTargetState(id) {

	lgdDwrStateService.getStateTargetList(id, {
		callback : handleStateSuccess,
		errorHandler : handleStateError
	});

}

// data contains the returned value
function handleStateSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddDestState';
	var valueText = 'stateCode';
	var nameText = 'stateNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddDestState');
	st.add(new Option('Select Target State', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleStateError() {
	// Show a popup message
	alert("No data found!");
}

function getSourceList(id) {
	getTargetDistrictListforBlock(id);
	getSourceBlockList(id);
}

function getTargetState(id) {
	lgdDwrStateService.getStateTargetList(id, {
		callback : handleStateSuccess,
		errorHandler : handleStateError
	});

}

// data contains the returned value
function handleStateSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddDestState';
	var valueText = 'stateCode';
	var nameText = 'stateNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddDestState');
	st.add(new Option('Select Target State', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleStateError() {
	// Show a popup message
	alert("No data found!");
}

function getTargetDistrictListforBlock(id) {

	lgdDwrDistrictService.getTargetDistrictList(id, {
		callback : handleDistrictTargetSuccess,
		errorHandler : handleDistrictTargetError
	});

}

function handleDistrictTargetSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddTargetDistrict';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddTargetDistrict');
	st.add(new Option('Select Target District', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleDistrictTargetError() {
	// Show a popup message
	alert("No data found!");
}

function getSourceList(id) {
	getTargetDistrictListforBlock(id);
	getSourceBlockList(id);
}

function getTargetDistrictandSubdistrictList(id) {
	// getTargetDistrictList(id);
	getSubDistrictList(id);
}

function getSubDistrictList(id) {
	if (id != 0)
		lgdDwrSubDistrictService.getSubDistrictListForLocalbody(id, {
			callback : handleSubDistrictSuccess,
			errorHandler : handleSubDistrictError
		});

}

function handleSubDistrictSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddSourceSubDistrict';
	var valueText = 'subdistrictCode';
	var nameText = 'subdistrictNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddSourceSubDistrict');
	st.add(new Option('------------Select------------', '0'));

	var options = $("#ddSourceSubDistrict");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
			options.append(option);
		}
	});

}

function handleSubDistrictError() {
	// Show a popup message
	alert("No data found!");
}

function getTargetDistrictList(id) {

	var stateCode = dwr.util.getValue('ddTargetState');
	lgdDwrDistrictService.getTargetDistrictList(id, stateCode, {
		callback : handleTargetDistrictSuccess,
		errorHandler : handleTargetDistrictError
	});

}

function handleTargetDistrictSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddDestDistrict';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddDestDistrict');
	st.add(new Option('Select Target District', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleTargetDistrictError() {
	// Show a popup message
	alert("No data found!");
}

function getTargetSubDistrictandVillageList(id) {
	getTargetSubDistrictList(id);
	getVillageList(id);
}

function getTargetSubDistrictList(id) {

	var subdistrictCode = dwr.util.getValue('ddSourceSubDistrict');
	lgdDwrSubDistrictService.getTargetSubDistListforShiftForLocalbody(subdistrictCode, id, {
		callback : handleTargetSubDistrictSuccess,
		errorHandler : handleTargetSubDistrictError
	});

}

function handleTargetSubDistrictSuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddTargetSubDistrict';
	var valueText = 'subdistrictCode';
	var nameText = 'subdistrictNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddTargetSubDistrict');
	st.add(new Option('Select Target SubDistrict', '0'));

	var options = $("#ddTargetSubDistrict");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
			options.append(option);
		}
	});

	// dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleTargetSubDistrictError() {
	// Show a popup message
	alert("No data found!");
}

function getVillageList(id) {

	var districtCode = dwr.util.getValue('districtCode');
	if (districtCode != 0) {
		getTargetSubDistrictList(districtCode);
	}
	lgdDwrVillageService.getVillageListbySubDistrictCodeCov(id, {
		callback : handleVillageSuccess,
		errorHandler : handleVillageError
	});

}

function handleVillageSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddSourceVillage';
	var fieldId1 = 'ddTargetVillage';
	var valueText = 'villageCode';
	var nameText = 'villageNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);
    var _show_text="";
	var options = $("#ddSourceVillage");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		_show_text=obj.villageNameEnglish+"(Village Code-"+obj.villageCode+")";
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			
			$(option).val(obj.villageCode).text(_show_text);
			options.append(option);
		} else {
			$(option).val(obj.villageCode).text(_show_text);
			options.append(option);
		}
	});

	// dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleVillageError() {
	// Show a popup message
	alert("No data found!");
}

function getVillageByBlockList(id) {

	var districtCode = dwr.util.getValue('districtCode');
	if (districtCode != 0) {
		getTargetBlockListforDistUser(districtCode);
	}
	lgdDwrVillageService.getVillageByBlockListForLocalBody(id, {
		callback : handlegetVillageByBlockListSuccess,
		errorHandler : handlegetVillageByBlockListError
	});
}

function getTargetBlockListforDistUser(id) {

	var sourceBlock = dwr.util.getValue('ddSourceBlock');
	lgdDwrBlockService.getTargetBlockListforDistUser(sourceBlock, id, {
		callback : handleTargetBlockforDistUserSuccess,
		errorHandler : handleTargetBlockforDistUserError
	});
}

function handleTargetBlockforDistUserSuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddTargetBlock';
	var valueText = 'blockCode';
	var nameText = 'blockNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		name : "Select Target Block"
	} ];
	dwr.util.addOptions(fieldId, dataq, '0', "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleTargetBlockforDistUserError() {
	// Show a popup message
	alert("No data found!");
}

function handlegetVillageByBlockListSuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddSourceVillage';
	var fieldId1 = 'ddTargetVillage';
	var valueText = 'villageCode';
	var nameText = 'villageNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);

	var options = $("#ddSourceVillage");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.villageCode).text(obj.villageNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.villageCode).text(obj.villageNameEnglish);
			options.append(option);
		}
	});

	// dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handlegetVillageByBlockListError() {
	// Show a popup message
	alert("No data found!");
}

// SHIFT VILLAGE LIST MOVE

function listbox_move(listID, direction) {

	var listbox = document.getElementById(listID);
	var selIndex = listbox.selectedIndex;

	if (-1 == selIndex) {
		alert("Please select an option to move.");
		return;
	}

	var increment = -1;
	if (direction == 'up')
		increment = -1;
	else
		increment = 1;

	if ((selIndex + increment) < 0 || (selIndex + increment) > (listbox.options.length - 1)) {
		return;
	}

	var selValue = listbox.options[selIndex].value;
	var selText = listbox.options[selIndex].text;
	listbox.options[selIndex].value = listbox.options[selIndex + increment].value
	listbox.options[selIndex].text = listbox.options[selIndex + increment].text

	listbox.options[selIndex + increment].value = selValue;
	listbox.options[selIndex + increment].text = selText;

	listbox.selectedIndex = selIndex + increment;
}

function listbox_moveacross(sourceID, destID) {
	var src = document.getElementById(sourceID);
	var dest = document.getElementById(destID);
	var check;
	for ( var count = 0; count < src.options.length; count++) {

		if (src.options[count].selected == true) {
			var option = src.options[count];
			check = true;
			var newOption = document.createElement("option");
			newOption.value = option.value;
			newOption.text = option.text;
			newOption.selected = true;
			try {
				dest.add(newOption, null); // Standard
				src.remove(count, null);
			} catch (error) {
				dest.add(newOption); // IE only
				src.remove(count);
			}
			count--;

		}

	}
	if (check != true) {
		alert("Please select valid village to shift");

	}
	check = false;
	// show_hide();

}

// SHIFT VILLAGE END HERE

function listbox_move(listID, direction) {

	var listbox = document.getElementById(listID);
	var selIndex = listbox.selectedIndex;

	if (-1 == selIndex) {
		alert("Please select an option to move.");
		return;
	}

	var increment = -1;
	if (direction == 'up')
		increment = -1;
	else
		increment = 1;

	if ((selIndex + increment) < 0 || (selIndex + increment) > (listbox.options.length - 1)) {
		return;
	}

	var selValue = listbox.options[selIndex].value;
	var selText = listbox.options[selIndex].text;
	listbox.options[selIndex].value = listbox.options[selIndex + increment].value
	listbox.options[selIndex].text = listbox.options[selIndex + increment].text

	listbox.options[selIndex + increment].value = selValue;
	listbox.options[selIndex + increment].text = selText;

	listbox.selectedIndex = selIndex + increment;
}

function listbox_moveacross(sourceID, destID) {
	var src = document.getElementById(sourceID);
	var dest = document.getElementById(destID);
	var check;
	for ( var count = 0; count < src.options.length; count++) {

		if (src.options[count].selected == true) {
			var option = src.options[count];
			check = true;
			var newOption = document.createElement("option");
			newOption.value = option.value;
			newOption.text = option.text;
			newOption.selected = true;
			try {
				dest.add(newOption, null); // Standard
				src.remove(count, null);
			} catch (error) {
				dest.add(newOption); // IE only
				src.remove(count);
			}
			count--;

		}

	}
	if (check != true) {
		alert("Please select valid village to shift");

	}
	check = false;
	// show_hide();

}

// dwr.engine.setActiveReverseAjax(true);

function getSourceBlockListFinal(id) {
	var stateCode = dwr.util.getValue('hdnStateCode');
	if (id != '' && stateCode != null) {
		getTargetDistrictShifVillagesBlkFinal(id, stateCode);
	}

	lgdDwrBlockService.getBlockList(id, {
		callback : handleSourceBlockSuccess,
		errorHandler : handleSourceBlockError
	});
}

function getSourceBlockList(id) {
	var stateCode = dwr.util.getValue('hdnStateCode');
	if (id != '' && stateCode != null) {
		getTargetDistrictShifVillagesBlk(id, stateCode);
	}

	lgdDwrBlockService.getBlockList(id, {
		callback : handleSourceBlockSuccess,
		errorHandler : handleSourceBlockError
	});
}

function handleSourceBlockSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddSourceBlock';
	var valueText = 'blockCode';
	var nameText = 'blockNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		name : "Select Source Block"
	} ];

	dwr.util.addOptions(fieldId, dataq, '0', "name");

	var options = $("#ddSourceBlock");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.blockCode).text(obj.blockNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.blockCode).text(obj.blockNameEnglish);
			options.append(option);
		}
	});

	// dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleSourceBlockError() {
	// Show a popup message
	alert("No data found!");
}

function getTargetDistrictShifVillagesBlk(id, stateCode) {
	lgdDwrDistrictService.getTargetDistrictShiftSDistrict(id, stateCode, {
		callback : handleTargetShiftVillageBlkSuccess,
		errorHandler : handleTargetShiftVillageBlkError
	});
}

function getTargetDistrictShifVillagesBlkFinal(id, stateCode) {
	lgdDwrDistrictService.getDistrictListbyStateCodeForLocalBody(stateCode, {
		callback : handleTargetShiftVillageBlkSuccess,
		errorHandler : handleTargetShiftVillageBlkError
	});
}

// data contains the returned value
function handleTargetShiftVillageBlkSuccess(data) {
	var fieldId = 'ddTargetDistrict';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';
	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddTargetDistrict');
	st.add(new Option('Select Target District', '0'));

	var options = $("#ddTargetDistrict");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.districtCode).text(obj.districtNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.districtCode).text(obj.districtNameEnglish);
			options.append(option);
		}
	});

	// dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleTargetShiftVillageBlkError() {
	alert("No data found!");
}

function getVillageBlockList(id) {
	// getTargetBlockList(id);
	$('#ddTargetDistrict').val(0);
	//$("#ddTargetDistrict option[value='0']").attr("selected", "selected");
	dwr.util.removeAllOptions('ddTargetBlock');
	getVillageByBlockList(id);
}

function getTargetBlockList(id) {

	var sourceBlk = document.getElementById("ddSourceBlock").value;

	lgdDwrBlockService.getTargetBlockListforSVillBlockForLocalbody(id, sourceBlk, {
		callback : handleTargetBlockSuccess,
		errorHandler : handleSourceBlockError
	});
}

function handleTargetBlockSuccess(data) {

	// Assigns data to result id
	var fieldId = 'ddTargetBlock';
	var valueText = 'blockCode';
	var nameText = 'blockNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		name : "Select Target Block"
	} ];
	dwr.util.addOptions(fieldId, dataq, '0', "name");

	var options = $("#ddTargetBlock");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.blockCode).text(obj.blockNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.blockCode).text(obj.blockNameEnglish);
			options.append(option);
		}
	});

	// dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleTargetBlockError() {
	// Show a popup message
	alert("No data found!");
}

function selectVillage() {
	var selSourceVillage = document.getElementById('ddSourceVillage');
	var selDestVillage = document.getElementById('ddTargetVillage');
	for ( var i = 0; i < selSourceVillage.options.length; i++) {
		selSourceVillage.options[i].selected = false;
	}
	for ( var i = 0; i < selDestVillage.options.length; i++) {
		selDestVillage.options[i].selected = true;
	}
}

/*
 * function validateSDAll() { var msg=null; selectVillage(); if
 * (!validateSourceState()) {
 * 
 * msg="Please Select Source State"+'\n'; } if (!validateSourceDistrict()) {
 * if(msg!=null) { msg=msg+"Please select Source District"+ '\n'; } else {
 * msg="Please select Source District"+ '\n'; } } if
 * (!validateSourceSubDistrict()) { if(msg!=null) { msg=msg+"Please select
 * Source Subdistrict"+ '\n'; } else { msg="Please select Source Subdistrict"+
 * '\n'; } }
 * 
 * if(!validateTargetDistrict()) { if(msg!=null) { msg=msg+"Please select Target
 * District"+ '\n'; } else { msg="Please select Target District"+ '\n'; } }
 * 
 * if (!validateTargetSubDistrict()) { if(msg!=null) { msg=msg+"Please select
 * Target Subdistrict"+ '\n'; } else { msg="Please select Target Subdistrict"+
 * '\n'; } }
 * 
 * 
 * if (!validateVillage()) { if(msg!=null) { msg=msg+"Please select at least one
 * village before shifting"; } else { msg="Please select at least one village
 * before shifting"; } }
 * 
 * if(msg!=null) { alert(msg); return false; } else { return true; } return
 * false; }
 */
function validateSDAll() {
	selectVillage();

	var errors = new Array();
	var error = false;
	var districtCode = dwr.util.getValue('districtCode');
	if (districtCode == 0) {
		errors[0] = !validateSourceState();
		errors[1] = !validateSourceDistrict();
	}
	errors[2] = !validateSourceSubDistrict();
	if (districtCode == 0) {
		errors[3] = !validateTargetDistrict();
	}
	errors[4] = !validateTargetSubDistrict();
	errors[5] = !validateVillage();
	for ( var i = 0; i < 6; i++) {

		if (errors[i] == true) {
			error = true;
			break;
		}
	}

	if (error == true) {
		//showClientSideError();
		return false;
	} else {
		return true;
	}
}

/*
 * function validateBlockAll() { var msg=null; selectVillage();
 * 
 * 
 * if (!validateSourceDistrict()) {
 * 
 * msg="Please select Source District"+ '\n'; } if (!validateSourceBlock()) {
 * if(msg!=null) { msg=msg+"Please select Source Block"+ '\n'; } else {
 * msg="Please select Source Block"+ '\n'; } } if (!validateTargetBlock()) {
 * if(msg!=null) { msg=msg+"Please select Target Block"+ '\n'; } else {
 * msg="Please select Target Block"+ '\n'; } }
 * 
 * 
 * if (!validateVillage()) { if(msg!=null) { msg=msg+"Please select at least one
 * village before shifting"; } else { msg="Please select at least one village
 * before shifting"; } }
 * 
 * if(msg!=null) { alert(msg); return false; } else { return true; } return
 * false; }
 */
function validateBlockAll() {
	selectVillage();
	var districtCode = dwr.util.getValue('districtCode');
	var errors = new Array();
	var error = false;
	if (districtCode == 0) {
		errors[0] = !validateSourceDistrict();
	}
	errors[1] = !validateSourceBlock();
	errors[2] = !validateTargetBlock();
	if (districtCode == 0) {
		errors[3] = !validateTargetDistrictBlk();
	}
	errors[4] = !validateVillage();

	for ( var i = 0; i < 5; i++) {
		if (errors[i] == true) {
			error = true;
			break;
		}
	}

	if (error == true) {

		//showClientSideError();

		return false;
	} else {
		return true;
	}
}

function validateSourceState() {
	var ddsource = document.getElementById('ddSourceState');
	if (ddsource != null) {
		if (ddsource.selectedIndex == 0) {
			$("#ddSourceState_error").show();
			return false;
		}
		$("#ddSourceState_error").hide();
	}
	return true;
}

function validateTargetDistrictBlk() {
	var ddTargetDistrict = document.getElementById('ddTargetDistrict');

	if (ddTargetDistrict.selectedIndex == -1) {
		$("#ddTargetDistrict_error").show();
		return false;
	}
	if (ddTargetDistrict.selectedIndex == 0) {
		$("#ddTargetDistrict_error").show();
		return false;
	}
	$("#ddTargetDistrict_error").hide();
	return true;
}

function validateSourceDistrict() {
	var ddSourceDistrict = document.getElementById('ddSourceDistrict');

	if (ddSourceDistrict.selectedIndex == -1) {
		$("#ddSourceDistrict_error").show();
		return false;
	}
	if (ddSourceDistrict.selectedIndex == 0) {
		$("#ddSourceDistrict_error").show();
		return false;
	}
	$("#ddSourceDistrict_error").hide();
	return true;

}

function validateTargetState() {
	var ddsource = document.getElementById('ddDestState');
	if (ddsource.selectedIndex == -1) {
		$("#ddDestState_error").show();
		return false;
	}
	if (ddsource.selectedIndex == 0) {
		$("#ddDestState_error").show();
		return false;
	}
	$("#ddDestState_error").hide();
	return true;
}

function validateTargetDistrict() {
	var ddSourceDistrict = document.getElementById('ddDestDistrict');

	if (ddSourceDistrict.selectedIndex == -1) {
		$("#ddDestDistrict_error").show();
		return false;
	}
	if (ddSourceDistrict.selectedIndex == 0) {
		$("#ddDestDistrict_error").show();
		return false;
	}
	$("#ddDestDistrict_error").hide();
	return true;

}

function validateSourceSubDistrict() {
	var ddSourceDistrict = document.getElementById('ddSourceSubDistrict');

	if (ddSourceDistrict.selectedIndex == -1) {
		$("#ddSourceSubDistrict_error").show();
		return false;
	}
	if (ddSourceDistrict.selectedIndex == 0) {
		$("#ddSourceSubDistrict_error").show();
		return false;
	}
	$("#ddSourceSubDistrict_error").hide();
	return true;

}

function validateTargetSubDistrict() {
	var ddDestDistrict = document.getElementById('ddTargetSubDistrict');

	if (ddDestDistrict.selectedIndex == -1) {
		$("#ddTargetSubDistrict_error").show();
		return false;
	}
	if (ddDestDistrict.selectedIndex == 0) {
		$("#ddTargetSubDistrict_error").show();
		return false;
	}
	$("#ddTargetSubDistrict_error").hide();
	return true;

}

function validateSourceBlock() {
	var ddSourceBlock = document.getElementById('ddSourceBlock');

	if (ddSourceBlock.selectedIndex == -1) {
		$("#ddSourceBlock_error").show();
		return false;
	}
	if (ddSourceBlock.selectedIndex == 0) {
		$("#ddSourceBlock_error").show();
		return false;
	}
	$("#ddSourceBlock_error").hide();
	return true;

}

function validateTargetBlock() {
	var ddTargetBlock = document.getElementById('ddTargetBlock');

	if (ddTargetBlock.selectedIndex == -1) {
		$("#ddTargetBlock_error").show();
		return false;
	}
	if (ddTargetBlock.selectedIndex == 0) {
		$("#ddTargetBlock_error").show();
		return false;
	}
	$("#ddTargetBlock_error").hide();
	return true;

}

function validateVillage() {
	var ddDestVillage = document.getElementById('ddTargetVillage');

	if (ddDestVillage.value == "") {
		$("#ddTargetVillage_error").show();
		return false;
	}
	$("#ddTargetVillage_error").hide();
	return true;
}

function villageBloadpage() {
	$("#ddSourceDistrict_error").hide();
	$("#ddSourceBlock_error").hide();
	$("#ddTargetBlock_error").hide();
	$("#ddTargetVillage_error").hide();

}

function villageSDloadpage() {
	$("#ddSourceState_error").hide();
	$("#ddSourceDistrict_error").hide();
	$("#ddDestDistrict_error").hide();
	$("#ddSourceSubDistrict_error").hide();
	$("#ddTargetSubDistrict_error").hide();
	$("#ddTargetVillage_error").hide();

}

function handleunmapVilError() {
	// Show a popup message
	alert("No data found!");
	document.getElementById('Villagelistchek').checked = false;
}

function shiftVillagesubmit() {

	var selObj = document.getElementById('ddTargetVillage');
	var temp = "";
	var i = "";
	for (i = 0; i < selObj.options.length; i++) {
		temp = selObj.options[i].text;
		if (temp.indexOf('MAPPED') == -1) {
			selObj.options[i].selected = true;
		}

	}
	selObj = document.getElementById('ddSourceVillage');
	for (i = 0; i < selObj.options.length; i++) {
		temp = selObj.options[i].text;
		if (temp.indexOf('MAPPED') > -1) {
			selObj.options[i].selected = true;
		}

	}
	if (shiftvillagevalidation()) {
		/*
		 * start Autohr Kirandeep Date 18/06/2014
		 */
		$("#btnSave").prop("disabled", true);
		document.forms['shiftvillageForm'].submit();
	}
}

function shiftvillagevalidation() {
	if (document.getElementById('ddTargetBlock').selectedIndex == 0) {
		alert("Kindly Select the BLock from the list !");
		return false;
	} else if (document.getElementById('ddTargetVillage').options.length == 0) {
		alert("Kindly Select One or More Villages !");
		return false;
	} else {
		return true;
	}

}

function getBlkMapUnVillageLists(id) {
	var s = document.getElementById('ddTargetBlock').value;
	if (id == 0)
		lgdDwrBlockService.getUnmapVillagesbyBlockCode(s, 'U', {
			callback : handleUnmapVilSuccess,
			errorHandler : handleunmapVilError,
			async : true
		});
	else
		lgdDwrBlockService.getUnmapVillagesbyBlockCode(s, 'M', {
			callback : handleMappedVilSuccess,
			errorHandler : handleunmapVilError,
			async : true

		});
}
function handleUnmapVilSuccess(data) {
	var fieldId = 'ddSourceVillage';
	dwr.util.removeAllOptions(fieldId);
	var _show_text="";
	var options = $("#ddSourceVillage");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		_show_text=obj.villageNameEnglish+"("+obj.villageCode+")";
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.villageCode).text(_show_text);
			options.append(option);
		} else {
			$(option).val(obj.villageCode).text(_show_text);
			options.append(option);
		}
	});

	// dwr.util.addOptions(fieldId, data, valueText, nameText);
	getBlkMapUnVillageLists(1);

}
function handleMappedVilSuccess(data) {
	var fieldId = 'ddTargetVillage';
	dwr.util.removeAllOptions(fieldId);
	var options = $("#ddTargetVillage");
	var _show_text="";
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		_show_text=obj.villageNameEnglish+"("+obj.villageCode+")";
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.villageCode).text(_show_text);
			options.append(option);
		} else {
			$(option).val(obj.villageCode).text(_show_text);
			options.append(option);
		}
	});
	
}


function loadpage() {
	$("#ddSourceDistrict_error").hide();
	$("#ddSourceBlock_error").hide();
	$("#ddTargetDistrict_error").hide();
	$("#ddTargetBlock_error").hide();
	$("#ddTargetVillage_error").hide();	

}

function loadpagevillSubdistrict() {
	$("#ddSourceState_error").hide();
	$("#ddSourceSubDistrict_error").hide();
	$("#ddDestDistrict_error").hide();
	$("#ddTargetSubDistrict_error").hide();
	$("#ddTargetVillage_error").hide();	

}


