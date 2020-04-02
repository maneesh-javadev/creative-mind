function getTargetDictrictandBlockList(id) {

	getTargetDistrictListforBlock(id);
	getBlockList(id);
}
function getTargetDistrictListforBlock(id) {

	var statecode = dwr.util.getValue('ddSourceState');

	lgdDwrDistrictService.getTargetDistrictList(id, statecode, {
		callback : handleDistrictTargetSuccess,
		errorHandler : handleDistrictTargetError
	});

}

function getTargetDictrictandBlockListFinal(id) {

	getTargetDistrictListforBlockFinal(id);
	getBlockList(id);
}

function getTargetDistrictListforBlockFinal(id) {

	var statecode = dwr.util.getValue('ddSourceState');

	lgdDwrDistrictService.getTargetDistrictShiftSDistrictForlocalbody(id, statecode, {
		callback : handleDistrictTargetSuccess,
		errorHandler : handleDistrictTargetError
	});

}

function handleDistrictTargetSuccess(data) {
	// Assigns data to result id

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

function handleDistrictTargetError() {
	// Show a popup message
	alert("No data found!");
}
function getBlockList(id) {

	lgdDwrBlockService.getBlockList(id, {
		callback : handleBlockSuccess,
		errorHandler : handleBlockError
	});
}

function handleBlockSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddSourceBlock';
	var fiedlId1 = 'ddDestBlock';
	var valueText = 'blockCode';
	var nameText = 'blockNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fiedlId1);

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

function handleBlockError() {
	// Show a popup message
	alert("No data found!");
}

function getSourceDistrictList(id) {

	lgdDwrDistrictService.getDistrictList(id, {
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

	dwr.util.addOptions(fieldId, data, valueText, nameText);

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
	// Show a popup message
	alert("No data found!");
}

function selectblock() {
	var selSourceBlock = document.getElementById('ddSourceBlock');
	var selDestBlock = document.getElementById('ddDestBlock');
	for (i = 0; i < selSourceBlock.options.length; i++) {
		selSourceBlock.options[i].selected = false;
	}
	for (i = 0; i < selDestBlock.options.length; i++) {
		selDestBlock.options[i].selected = true;
	}
}

/*function validateAll() {
	var msg = null;
	selectblock();

	if (!validateSourceDistrict()) {
		if (msg != null) {
			// msg=msg+"Please select Source District"+ '\n';
		} else {
			// msg="Please select Source District"+ '\n';
		}
	}
	if (!validateTargetDistrict()) {
		if (msg != null) {
			// msg=msg+"Please select Target District"+ '\n';
		} else {
			// msg="Please select Target District"+ '\n';
		}
	}

	if (!validateBlock()) {
		if (msg != null) {
			// msg=msg+"Please select at least one block before shifting";
		} else {
			// msg="Please select at least one block before shifting";
		}
	}

	if (msg != null) {
		// alert(msg);
		return false;
	} else {
		return true;
	}
	return false;
}*/

function validateAll() {

	var errors = new Array();
	var error = false;
	errors[0] = !validateSourceDistrict();
	errors[1] = !validateTargetDistrict();
	errors[2] = !validateBlock();

	for ( var i = 0; i < 3; i++) {

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

function validateBlock() {
	var ddDestBlock = document.getElementById('ddDestBlock');

	if (ddDestBlock.value == "") {
		$("#ddDestBlock_error").show();
		return false;
	}
	$("#ddDestBlock_error").hide();
	return true;

}

function blockloadpage() {

	$("#ddSourceDistrict_error").hide();
	$("#ddDestDistrict_error").hide();
	$("#ddDestBlock_error").hide();
	/*$("#ddSourceDistrict_msg").hide();
	$("#ddDestDistrict_msg").hide();
	$("#ddDestBlock_msg").hide();*/

	var hdnDistrictSName = document.getElementById('hdnDistrictSName').value;
	var hdnDistrictDName = document.getElementById('hdnDistrictDName').value;

	if (hdnDistrictSName > 0) {
		dwr.util.setValue('ddSourceDistrict', parseInt(hdnDistrictSName));
		setTimeout("assignedValues('hdnDistrictSName','ddSourceDistrict')", 10);
		getTargetDistrictListforBlock(hdnDistrictSName);
	}
	if (hdnDistrictDName != 0) {
		dwr.util.setValue('ddDestDistrict', parseInt(hdnDistrictDName));
		setTimeout("assignedValues('hdnDistrictDName','ddDestDistrict')", 1000);
	}

}

function blockloadpageFinal() {

	/*
	 * $("#ddSourceDistrict_error").hide(); $("#ddDestDistrict_error").hide();
	 * $("#ddDestBlock_error").hide(); $("#ddSourceDistrict_msg").hide();
	 * $("#ddDestDistrict_msg").hide(); $("#ddDestBlock_msg").hide();
	 */

	var hdnDistrictSName = document.getElementById('hdnDistrictSName').value;
	var hdnDistrictDName = document.getElementById('hdnDistrictDName').value;

	if (hdnDistrictSName > 0) {
		dwr.util.setValue('ddSourceDistrict', parseInt(hdnDistrictSName));
		setTimeout("assignedValues('hdnDistrictSName','ddSourceDistrict')", 10);
		getTargetDistrictListforBlock(hdnDistrictSName);
	}
	if (hdnDistrictDName != 0) {
		dwr.util.setValue('ddDestDistrict', parseInt(hdnDistrictDName));
		setTimeout("assignedValues('hdnDistrictDName','ddDestDistrict')", 1000);
	}

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
		alert("Please select valid block to shift");

	}
	check = false;
	// show_hide();

}

function assignedValues(obj1, obj2) {
	document.getElementById(obj2).value = document.getElementById(obj1).value;
}

function getBlockName() {
	var blockList = "";
	selectblock();
	var selDestBlock = document.getElementById('ddDestBlock');

	for (i = 0; i < selDestBlock.options.length; i++) {
		selDestBlock.options[i].selected = true;
		blockList += selDestBlock.options[i].text + ",";
	}
	document.getElementById('hdnBlockName').value = blockList;
}
