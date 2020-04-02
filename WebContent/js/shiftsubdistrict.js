function getList(id) {

	getTargetState(id);
	getSourceDistrictList(id);
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

function getSourceDistrictList(id) {

	
	lgdDwrDistrictService.getDistrictListbyStateCodeForLocalBody(id, {
		callback : handleSourceDistrictSuccess,
		errorHandler : handleSourceDistrictError
	});

}

function handleSourceDistrictSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddSourceDistrict';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddSourceDistrict');
	st.add(new Option('Select Source District', '0'));

	var options = $("#ddSourceDistrict");
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

function handleSourceDistrictError() {
	// Show a popup message
	alert("No data found!");
}

function getDestDistrictList(id) {

	lgdDwrDistrictService.getDistrictList(id, {
		callback : handleDestDistrictSuccess,
		errorHandler : handleDestDistrictError
	});

}

function handleDestDistrictSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddDestDistrict';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddDestDistrict');
	st.add(new Option('Select Target District', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleDestDistrictError() {
	alert("No data found!");
}

function getSubDistrictList(id) {
	var stateCode = dwr.util.getValue('hdnStateCode');
	if (id != '' && stateCode != "null") {
		getTargetDistrictShiftSDistrict(id, stateCode);
	}
	lgdDwrSubDistrictService.getSubDistrictListForLocalbody(id, {
		callback : handleSubDistrictSuccess,
		errorHandler : handleSubDistrictError
	});

}

// data contains the returned value
function handleSubDistrictSuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddSourceSubDistrict';
	var fieldId1 = 'ddDestSubDistrict';
	var valueText = 'subdistrictCode';
	var nameText = 'subdistrictNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);

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
	// dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleSubDistrictError() {
	// Show a popup message
	alert("No data found!");
}

function getTargetDistrictShiftSDistrict(id, stateCode) {
	lgdDwrDistrictService.getTargetDistrictShiftSDistrictForlocalbody(id, stateCode, {
		callback : handleTargetShiftDistrictSuccess,
		errorHandler : handleTargetShiftDistrictError
	});
}

// data contains the returned value
function handleTargetShiftDistrictSuccess(data) {
	var fieldId = 'ddDestDistrict';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';
	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddDestDistrict');
	st.add(new Option('Select Target District', '0'));

	var options = $("#ddDestDistrict");
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

function handleTargetShiftDistrictError() {
	alert("No data found!");
}

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
		alert("Please select valid subdistrict to shift");

	}
	check = false;
	// show_hide();

}
function selectSubDistrict() {
	var selSourceDistrict = document.getElementById('ddSourceSubDistrict');
	var selDestDistrict = document.getElementById('ddDestSubDistrict');
	for (i = 0; i < selSourceDistrict.options.length; i++) {
		selSourceDistrict.options[i].selected = false;
	}
	for (i = 0; i < selDestDistrict.options.length; i++) {
		selDestDistrict.options[i].selected = true;
	}
}

/*
 * function validateAll() { var msg=null; selectSubDistrict(); if
 * (!validateSourceState()) {
 * 
 * msg="Please Select Source State"+'\n'; } if (!validateTargetState()) {
 * if(msg!=null) { msg=msg+"Please Select Target State"+ '\n'; } else {
 * msg="Please Select Target State"+ '\n'; } } if (!validateSourceDistrict()) {
 * if(msg!=null) { msg=msg+"Please select Source District"+ '\n'; } else {
 * msg="Please select Source District"+ '\n'; } } if (!validateTargetDistrict()) {
 * if(msg!=null) { msg=msg+"Please select Target District"+ '\n'; } else {
 * msg="Please select Target District"+ '\n'; } }
 * 
 * if (!validateSubDistrict()) { if(msg!=null) { msg=msg+"Please select at least
 * one subdistrict before shifting"; } else { msg="Please select at least one
 * subdistrict before shifting"; } }
 * 
 * if(msg!=null) { alert(msg); return false; } else { return true; } return
 * false; }
 */
function selectDistrict() {
	var selSourceDistrict = document.getElementById('ddSourceDistrict');
	var selDestDistrict = document.getElementById('ddDestDistrict');
	for (i = 0; i < selSourceDistrict.options.length; i++) {
		selSourceDistrict.options[i].selected = false;
	}
	for (i = 0; i < selDestDistrict.options.length; i++) {
		selDestDistrict.options[i].selected = true;
	}
}

function validateAll() {
	// selectSubDistrict();

	var errors = new Array();
	var error = false;
	// errors[0] = !validateSourceState();
	// errors[1] = !validateTargetState();
	errors[0] = !validateSourceDistrict();
	errors[1] = !validateTargetDistrict();
	errors[2] = !validateSubDistrict();

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
	if (ddsource.selectedIndex == 0) {
		$("#ddSourceState_error").show();
		return false;
	}
	$("#ddSourceState_error").hide();
	return true;
}

function validateTargetState() {
	var ddDestState = document.getElementById('ddDestState');

	if (ddDestState.selectedIndex == -1) {
		$("#ddDestState_error").show();
		return false;
	}
	if (ddDestState.selectedIndex == 0) {
		$("#ddDestState_error").show();
		return false;
	}
	$("#ddDestState_error").hide();
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

function validateTargetDistrict() {
	var ddDestDistrict = document.getElementById('ddDestDistrict');

	if (ddDestDistrict.selectedIndex == -1) {
		$("#ddDestDistrict_error").show();
		return false;
	}
	if (ddDestDistrict.selectedIndex == 0) {
		$("#ddDestDistrict_error").show();
		return false;
	}
	$("#ddDestDistrict_error").hide();
	return true;

}

function validateSubDistrict() {
	var ddDestSubDistrict = document.getElementById('ddDestSubDistrict');

	if (ddDestSubDistrict.value == "") {
		$("#ddDestSubDistrict_error").show();
		return false;
	}
	$("#ddDestSubDistrict_error").hide();
	return true;

}

function subdistrictloadpage() {

	$("#ddSourceState_error").hide();
	$("#ddDestState_error").hide();
	$("#ddSourceDistrict_error").hide();
	$("#ddDestDistrict_error").hide();
	$("#ddDestSubDistrict_error").hide();
	/*$("#ddDestState_msg").hide();
	$("#ddDestDistrict_msg").hide();
	$("#ddSourceDistrict_msg").hide();
	$("#ddSourceState_msg").hide();
	$("#ddDestSubDistrict_msg").hide();*/
	var hdnStateSName = document.getElementById('hdnStateSName').value;
	var hdnStateDName = document.getElementById('hdnStateDName').value;
	var hdnDistrictSName = document.getElementById('hdnDistrictSName').value;
	var hdnDistrictDName = document.getElementById('hdnDistrictDName').value;

	if (hdnStateSName > 0) {
		dwr.util.setValue('ddSourceState', parseInt(hdnStateSName));
		setTimeout("assignedValues('hdnStateSName','ddSourceState')", 10);
		getList(hdnStateSName);
	}
	if (hdnStateDName > 0) {
		dwr.util.setValue('ddDestState', parseInt(hdnStateDName));
		setTimeout("assignedValues('hdnStateDName','ddDestState')", 1000);
		getDestDistrictList(hdnStateDName);

	}

	if (hdnDistrictSName > 0) {
		dwr.util.setValue('ddSourceDistrict', parseInt(hdnDistrictSName));
		setTimeout("assignedValues('hdnDistrictSName','ddSourceDistrict')", 2000);
	}
	if (hdnDistrictDName > 0) {
		dwr.util.setValue('ddDestDistrict', parseInt(hdnDistrictDName));
		setTimeout("assignedValues('hdnDistrictDName','ddDestDistrict')", 2000);
	}

	if (document.getElementById('ddSourceState').selectedIndex > 0) {

		getList(document.getElementById('ddSourceState').value);
		getDestDistrictList(document.getElementById('ddSourceState').value);
	}

}

function subdistrictloadpageFinal() {

	/*
	 * $("#ddSourceState_error").hide(); $("#ddDestState_error").hide();
	 * $("#ddSourceDistrict_error").hide(); $("#ddDestDistrict_error").hide();
	 * $("#ddDestSubDistrict_error").hide(); $("#ddDestState_msg").hide();
	 * $("#ddDestDistrict_msg").hide(); $("#ddSourceDistrict_msg").hide();
	 * $("#ddSourceState_msg").hide(); $("#ddDestSubDistrict_msg").hide();
	 */
	var hdnStateSName = document.getElementById('hdnStateSName').value;
	var hdnStateDName = document.getElementById('hdnStateDName').value;
	var hdnDistrictSName = document.getElementById('hdnDistrictSName').value;
	var hdnDistrictDName = document.getElementById('hdnDistrictDName').value;

	if (hdnStateSName > 0) {
		dwr.util.setValue('ddSourceState', parseInt(hdnStateSName));
		setTimeout("assignedValues('hdnStateSName','ddSourceState')", 10);
		getList(hdnStateSName);
	}
	if (hdnStateDName > 0) {
		dwr.util.setValue('ddDestState', parseInt(hdnStateDName));
		setTimeout("assignedValues('hdnStateDName','ddDestState')", 1000);
		// getDestDistrictList(hdnStateDName);
	}

	if (hdnDistrictSName > 0) {
		dwr.util.setValue('ddSourceDistrict', parseInt(hdnDistrictSName));
		setTimeout("assignedValues('hdnDistrictSName','ddSourceDistrict')", 2000);
	}
	/*
	 * if(hdnDistrictDName > 0) { dwr.util.setValue('ddDestDistrict',
	 * parseInt(hdnDistrictDName));
	 * setTimeout("assignedValues('hdnDistrictDName','ddDestDistrict')", 2000); }
	 */

	/*
	 * if (document.getElementById('ddSourceState').selectedIndex>0) {
	 * getList(document.getElementById('ddSourceState').value);
	 * getDestDistrictList(document.getElementById('ddSourceState').value); }
	 */

}

function assignedValues(obj1, obj2) {
	document.getElementById(obj2).value = document.getElementById(obj1).value;
}

function getSubdistrictNameEnglish() {

	var subdistrictList = "";
	selectSubDistrict();
	var selDestSubDistrict = document.getElementById('ddDestSubDistrict');

	for (i = 0; i < selDestSubDistrict.options.length; i++) {
		selDestSubDistrict.options[i].selected = true;
		subdistrictList += selDestSubDistrict.options[i].text + ",";
	}
	document.getElementById('hdnSubdistrictName').value = subdistrictList;
}