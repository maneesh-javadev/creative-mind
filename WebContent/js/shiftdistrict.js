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
	/*
	 * var dataq = [ {name:"Select Target State"} ];
	 * dwr.util.addOptions(fieldId, dataq,"0", "name");
	 */

	var st = document.getElementById('ddDestState');
	st.add(new Option('Select Target State', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleStateError() {
	// Show a popup message
	alert("No data found!");
}

function getDistrictList(id) {

	lgdDwrDistrictService.getDistrictList(id, {
		callback : handleDistrictSuccess,
		errorHandler : handleDistrictError
	});

}

function getDistrictListAuthenticated(id) {

	lgdDwrDistrictService.getDistrictList(id, {
		callback : handleDistrictSuccess,
		errorHandler : handleDistrictError
	});

}

function handleDistrictSuccess(data) {
	var fieldId = 'ddSourceDistrict';
	var fieldId1 = 'ddDestDistrict';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);

	/*
	 * var st = document.getElementById('ddSourceDistrict'); st.add(new
	 * Option('----------- Select -------------', '0'));
	 */

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleDistrictError() {
	// Show a popup message
	alert("No data found!");
}

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

	// selectDistrict();

	var errors = new Array();
	var error = false;
	errors[0] = !validateSourceState();
	errors[1] = !validateTargetState();
	errors[2] = !validateDistrict();

	for ( var i = 0; i < 3; i++) {

		if (errors[i] == true) {

			error = true;
			break;
		}
	}

	if (error == true) {

		showClientSideError();

		return false;
	} else {
		return true;
	}
	return false;
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

function validateDistrict() {
	var dddistrict = document.getElementById('ddDestDistrict');

	if (dddistrict.value == "") {
		$("#ddDestDistrict_error").show();
		return false;
	}
	$("#ddDestDistrict_error").hide();
	return true;

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
		alert("Please select valid district to shift");

	}
	check = false;
	// show_hide();

}

function districtloadPage() {
	/*
	 * $("#ddSourceState_error").hide(); $("#ddDestState_error").hide();
	 * $("#ddDestDistrict_error").hide();
	 */

	var hdnStateSName = document.getElementById('hdnStateSName').value;
	var hdnStateDName = document.getElementById('hdnStateDName').value;

	if (hdnStateSName > 0) {
		dwr.util.setValue('ddSourceState', parseInt(hdnStateSName));
		setTimeout("assignedValues('hdnStateSName','ddSourceState')", 10);
		getTargetState(hdnStateSName);
	}
	if (hdnStateDName != 0) {
		dwr.util.setValue('ddDestState', parseInt(hdnStateDName));
		setTimeout("assignedValues('hdnStateDName','ddDestState')", 1000);
	}

}

function assignedValues(obj1, obj2) {
	document.getElementById(obj2).value = document.getElementById(obj1).value;
}

function getList(id) {
	if (id > 0) {
		getTargetState(id);
		getDistrictList(id);
	}
}

function show_msg(Field_msg) {
	var hint = '#' + Field_msg + "_msg";
	var error = '#' + Field_msg + "_error";
	$("#" + Field_msg).removeClass("error_fld");
	$("#" + Field_msg).removeClass("error_msg");
	$(hint).show();
	$(error).hide();

}
// Modify District validation
function officialAddress() {
	if (document.getElementById("OfficialAddress").value == "") {
		document.getElementById("OfficialAddress_error").innerHTML = "District Name in English is Required";
		$("#OfficialAddress_error").show();
		$("#OfficialAddress_msg").hide();
		$("#OfficialAddress").addClass("error_fld");
		$("#OfficialAddress").addClass("error_msg");
		return false;
	} else {
		$("#OfficialAddress_msg").hide();
		return true;

	}
}

function getDistrictNameEnglish() {
	var districtList = "";
	selectDistrict();
	var selDestDistrict = document.getElementById('ddDestDistrict');

	for (i = 0; i < selDestDistrict.options.length; i++) {
		selDestDistrict.options[i].selected = true;
		districtList += selDestDistrict.options[i].text + ",";
	}
	document.getElementById('hdnDistrictName').value = districtList;
}

// ///
function validateDistrictChangeAlert() {
	var errors = new Array();
	var error = false;
	errors[0] = vlidateOnblur('districtname', '1', '15', 'c');

	if (errors[0] == true) {
		error = true;
	}

	if (error == true) {

		showClientSideError();

		return false;
	} else {
		return true;
	}
}

function validateSubDistrictChangeAlert() {
	var errors = new Array();
	var error = false;
	errors[0] = vlidateOnblur('subdistrictname', '1', '15', 'c');

	if (errors[0] == true) {
		error = true;
	}

	if (error == true) {

		showClientSideError();

		return false;
	} else {
		return true;
	}
}

function validateVillageChangeAlertFinal() {
	var errors = new Array();
	var error = false;
	errors[0] = !validateVillNameF();

	if (errors[0] == true) {
		error = true;
	}

	if (error == true) {
		showClientSideError();
		return false;
	} else {
		return true;
	}
}

function validateVillNameF() {

	if (document.getElementById('villagename').value == "") {
		$("#villagename_error").show();
		return false;
	}
	$("#villagename_error").hide();
	return true;
}

function validateVillageChangeAlert() {
	var errors = new Array();
	var error = false;
	errors[0] = vlidateOnblur('villagename', '1', '15', 'c');

	if (errors[0] == true) {
		error = true;
	}

	if (error == true) {

		showClientSideError();

		return false;
	} else {
		return true;
	}
}

function validateSearchAlert() {

	var errors = new Array();
	var error = false;
	errors[0] = vlidateOnblur('entityNameA', '1', '15', 'c');

	if (errors[0] == true) {

		error = true;
	}

	if (error == true) {

		showClientSideError();

		return false;
	} else {
		return true;
	}
	return false;
}