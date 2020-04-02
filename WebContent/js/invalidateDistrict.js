function setSessionValue(val1, val2, val3) {
	lgdDwrDistrictService.invalidateLoop(val1, val2, val3, {
		callback : handleInvalidateLoop,
		errorHandler : handleInvalidateLoopError
	});

}
function handleInvalidateLoop(data) {
	if (data) {
		// alert('success');
	}

}

function handleInvalidateLoopError() {
	alert("No data found!");
}

function validateSelectAddMore() {

	// var list="";
	// alert(list);
	// var districtlist = document.getElementById("ddDistrictMergeNo");
	// list+= districtlist.options[districtlist.selectedIndex].value+"#";
	// alert(list);

	selectall();
	if (validateformWithoutSave()) {
		setSessionValue(document.getElementById('ddDistrictMergeNo').value, gelSelectedVillages('contributedSubDistricts'));

		removeSelectedValueDistrict('ddDistrictMergeNo');
		removeEverything('contributedSubDistricts');
	}
}

function gelSelectedVillages(val) {
	var elSel = document.getElementById(val);
	var i;
	var output = "";
	for (i = elSel.length - 1; i >= 0; i--) {
		if (elSel.options[i].selected) {
			output += elSel.options[i].value + ",";
		}
	}
	return output.substring(0, output.length - 1);
}
/* dwr.engine.setActiveReverseAjax(true); */
function validateform() {
	var ddDistrict = document.getElementById('ddDistrict').value;
	var ddDistrictMergeYes = document.getElementById('ddDistrictMergeYes').value;
	var ddDistrictMergeNo = document.getElementById('ddDistrictMergeNo').value;
	if (ddDistrict == 0) {
		// alert("Please Select District");
		document.getElementById('ddDistrict').focus();
		return false;
	}
	if (document.getElementById('districtdelete_yes').checked) {
		if (ddDistrictMergeYes == 0 || ddDistrictMergeYes == "") {
			// alert("Please Select District To merge with");
			document.getElementById('ddDistrictMergeYes').focus();
			return false;
		}
	} else if (document.getElementById('districtdelete_no').checked) {
		// alert(document.getElementById('districtdelete_no').checked);
		/*
		 * if(ddDistrictMergeNo==0 || ddDistrictMergeNo=="") { alert("Please
		 * Select District To merge with");
		 * document.getElementById('ddDistrictMergeNo').focus(); return false; }
		 */
		if (!document.getElementById('chooseMoreBtn').disabled) {
			alert('Please Select all Sub-District to merge with target District');
			return false;
		}
		/*
		 * alert('Please Select all Sub-District to merge with target
		 * District'); return false;
		 */
	}
	return true;
}

function validateformWithoutSave() {
	var ddDistrict = document.getElementById('ddDistrict').value;
	var ddDistrictMergeYes = document.getElementById('ddDistrictMergeYes').value;
	var ddDistrictMergeNo = document.getElementById('ddDistrictMergeNo').value;
	if (ddDistrict == 0) {
		alert("Please Select District");
		document.getElementById('ddDistrict').focus();
		return false;
	}
	if (document.getElementById('districtdelete_yes').checked) {
		if (ddDistrictMergeYes == 0 || ddDistrictMergeYes == "") {
			alert("Please Select District To merge with");
			document.getElementById('ddDistrictMergeYes').focus();
			return false;
		}
	} else if (document.getElementById('districtdelete_no').checked) {
		// alert(document.getElementById('districtdelete_no').checked);
		if (ddDistrictMergeNo == 0 || ddDistrictMergeNo == "") {
			alert("Please Select District To merge with");
			document.getElementById('ddDistrictMergeNo').focus();
			return false;
		}
		if (ddDistrictMergeNo > 0 || ddDistrictMergeNo != "") {
			if (!document.getElementById('chooseMoreBtn').disabled && document.getElementById('ddSubdistrict').length > 0
					&& document.getElementById('ddDistrictMergeNo').length <= 2) {
				alert('Please Select all Sub-District to merge with target District');
				return false;
			}
		}

	}
	appendList();
	generateTempView();
	hasMoreItems("ddSubdistrict", "chooseMoreBtn");
	return true;
}

function validateSelectAndSubmit() {
	selectall();
	var ddDistrictMergeNo = document.getElementById('ddDistrictMergeYes').value;
	if (validateform()) {
		if (document.getElementById('districtdelete_yes').checked) {
			if (ddDistrictMergeNo == 0) {
				alert('Please Select District to be merge with');
				return false;
			}
		}
		$('input[name=Submit]').prop('disabled', true);
		document.forms['invalidateForm'].submit();
	}
}

function onSubmit() {
	selectall();
	var errors = new Array();
	var error = false;

	errors[0] = vlidateOnblur('ddDistrict', '1', '15', 'c');
	if (errors[0] == true) {
		error = true;
	}
	if (document.getElementById('districtdelete_yes').checked) {
		errors[1] = vlidateOnblur('ddDistrictMergeYes', '1', '15', 'c');
		if (errors[1] == true) {
			error = true;
		}
	}
	if (document.getElementById('districtdelete_no').checked) {

		errors[2] = vlidateOnblur('ddDistrictMergeNo', '1', '15', 'c');
		if (errors[2] == true) {
			error = true;
		}
		errors[3] = vlidateOnblur('contributedSubDistricts', '1', '15', 'p');
		if (errors[3] == true) {
			error = true;
		}

	}

	if (error == true) {

		showClientSideError();

		return false;
	} else {

		document.forms['invalidateForm'].submit();
		return true;

	}
	return false;

}

function selectall() {

	var selObj = document.getElementById('contributedSubDistricts');
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}
	var selObj = document.getElementById('ddSubdistrict');
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}
}

function removeItemFromOtherDropdowns(val) {
	var dwrDistrictList = document.getElementById('ddDistrict');

	if (val.selectedIndex > 0) {
		var selectedValue = val.value;
		var pos;

		removeEverything('ddDistrictMergeYes');
		removeEverything('ddDistrictMergeNo');

		document.getElementById('ddDistrictMergeYes').add(new Option('Select District', 0));
		document.getElementById('ddDistrictMergeNo').add(new Option('Select District', 0));

		var options = $("#ddDistrictMergeNo");
		for ( var i = 0; i < dwrDistrictList.length; i++) {
			var option = $("<option/>");
			if (dwrDistrictList[i].disabled) {
				$(option).attr('disabled', 'disabled');
				$(option).val(dwrDistrictList[i].value).text(dwrDistrictList[i].text);
				options.append(option);
			} else {
				$(option).val(dwrDistrictList[i].value).text(dwrDistrictList[i].text);
				options.append(option);
			}
		}
		var options = $("#ddDistrictMergeYes");
		for ( var i = 0; i < dwrDistrictList.length; i++) {
			var option = $("<option/>");
			if (dwrDistrictList[i].disabled) {
				$(option).attr('disabled', 'disabled');
				$(option).val(dwrDistrictList[i].value).text(dwrDistrictList[i].text);
				options.append(option);
			} else {
				$(option).val(dwrDistrictList[i].value).text(dwrDistrictList[i].text);
				options.append(option);
			}

		}
		// document.getElementById('ddDistrictMergeYes').add(new
		// Option(dwrDistrictList[i].text, dwrDistrictList[i].value));
		// document.getElementById('ddDistrictMergeNo').add(new
		// Option(dwrDistrictList[i].text, dwrDistrictList[i].value));
		for ( var i = 0; i < document.getElementById('ddDistrictMergeYes').length; i++) {
			if (document.getElementById('ddDistrictMergeYes')[i].value == selectedValue) {
				pos = i;
			}
		}

		document.getElementById('ddDistrictMergeYes').selectedIndex = pos;
		document.getElementById('ddDistrictMergeNo').selectedIndex = pos;
		removeSelectedValue('ddDistrictMergeYes');
		removeSelectedValue('ddDistrictMergeNo');
	}
}

function removeEverything(val) {
	var elSel = document.getElementById(val);
	for ( var i = elSel.length - 1; i >= 0; i--)
		elSel.remove(i);
}

function removeSelectedValueDistrict(val) {
	var elSel = document.getElementById(val);
	var i;
	for (i = elSel.length - 1; i >= 1; i--) {
		if (elSel.options[i].selected) {
			elSel.remove(i);
		}
	}
}

function getSubDistrictList(id) {
	// alert(id);
	// appendlistDistrict();
	lgdDwrSubDistrictService.getSubDistrictListForLocalbody(id, {
		callback : handleSubDistrictSuccess,
		errorHandler : handleSubDistrictError
	});

}

function appendlistDistrict() {
	/*
	 * alert("dd"); list="@"; alert(list); var districtlist =
	 * document.getElementById("ddDistrict"); list+=
	 * districtlist.options[districtlist.selectedIndex].value+"$";
	 * document.getElementById("listformat").value=list;
	 * 
	 * alert(list);
	 */
}

function appendList() {

	// alert("append list");
	var list = "";
	var a = document.getElementById('listformat').value;
	list = a;
	// alert(list);
	var districtlist = document.getElementById("ddDistrictMergeNo");
	list += districtlist.options[districtlist.selectedIndex].value + "#";

	var subdistrictlist = document.getElementById("contributedSubDistricts");

	// alert(subdistrictlist.length);

	for ( var x = 0; x < subdistrictlist.length; x++) {

		// alert(InvForm.SelBranch[x].value);
		list += subdistrictlist.options[x].value + ":";
		// alert(list);

	}
	list = list.toString().substring(0, list.length - 1);
	list += ",";
	// alert(list);
	document.getElementById('listformat').value = list;
}
// data contains the returned value
function handleSubDistrictSuccess(data) {
	// Assigns data to result id
	// alert(data);
	var fieldId = 'ddSubdistrict';
	dwr.util.removeAllOptions(fieldId);
	/*
	 * var st = document.getElementById('ddSubdistrict'); st.add(new
	 * Option('Select Subdistrict', '0'));
	 */

	var options = $("#ddSubdistrict");
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
// @Author - Sarvapriya Malhotra
// @Modified BY: Chandan Soni
function toggledisplay(obj, val) {

	if (document.getElementById(obj).checked) {

		if (val == 'cvillage') {

			document.getElementById('cvillage').style.visibility = 'visible';
			document.getElementById('cvillage').style.display = 'block';
			document.getElementById('fromothersubdistrict').style.visibility = 'hidden';
			document.getElementById('fromothersubdistrict').style.display = 'none';
		}
		if (val == 'fromothersubdistrict') {
			document.getElementById('fromothersubdistrict').style.visibility = 'visible';
			document.getElementById('fromothersubdistrict').style.display = 'block';
			document.getElementById('cvillage').style.visibility = 'hidden';
			document.getElementById('cvillage').style.display = 'none';
		}
	} else {
		document.getElementById('cvillage').style.visibility = 'visible';
		document.getElementById('cvillage').style.display = 'block';
		document.getElementById('fromothersubdistrict').style.visibility = 'hidden';
		document.getElementById('fromothersubdistrict').style.display = 'none';
	}

}

function addItem(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++)
			if (document.getElementById(list2).options[j].selected == true)
				if (doAddVal)
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + val + ">" + document.getElementById(list2)[j].text + " ("
									+ val + ")</option>");
				else
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");
		removeSelectedValue(list2);
	}
}
function removeAll(list1, list2, doRemoveVal) {
	for ( var i = document.getElementById(list1).length - 1; i >= 0; i--) {
		document.getElementById(list1).selectedIndex = i;

		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 4)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length - 6) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + "</option>");
		removeSelectedValue(list1);
	}
}
function removeItem(list1, list2, doRemoveVal) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 4)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length - 6) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + "</option>");
		removeSelectedValue(list1);
	}

}
function removeSelectedValue(val) {
	var elSel = document.getElementById(val);
	var i;
	for (i = elSel.length - 1; i >= 0; i--) {
		if (elSel.options[i].selected) {
			elSel.remove(i);
		}
	}
}

function validateAll() {
	var msg = null;
	if (!validateDistrict()) {
		if (msg != null) {
			msg = msg + "Please select  District " + '\n';
		} else {
			msg = "Please Select District " + '\n';
		}
	}
}
function validateDistrict() {
	var dddistrict = document.getElementById('ddDistrict');

	if (dddistrict.value == "") {
		$("#ddDestDistrict_error").show();
		return false;
	}
	$("#ddDestDistrict_error").hide();
	return true;

}

function generateTempView() {
	if (document.getElementById('contributedSubDistricts').length == 0)
		return false;

	document.getElementById('dynamicTbl').style.visibility = "visible";
	document.getElementById('dynamicTbl').style.display = "block";
	var tbl = document.getElementById('dynamicTbl');
	var row = tbl.insertRow(tbl.rows.length);
	row.className += "tblRowB";
	var colCount = tbl.rows[0].cells.length;

	for ( var i = 0; i < colCount; i++) {
		var newcell = row.insertCell(i);
		if (i == 0)
			newcell.innerHTML = '<strong>'
					+ document.getElementById('ddDistrictMergeNo')[document.getElementById('ddDistrictMergeNo').selectedIndex].text + '</strong>';
		else
			newcell.innerHTML = document.getElementById('contributedSubDistricts')[0].text;
	}

	for ( var i = 1; i < document.getElementById('contributedSubDistricts').length; i++) {
		row = tbl.insertRow(tbl.rows.length);
		row.className += "tblRowB";
		for ( var j = 0; j < colCount; j++) {
			var newcell = row.insertCell(j);

			if (j == 1)
				newcell.innerHTML = document.getElementById('contributedSubDistricts')[i].text;
		}
	}

	row = tbl.insertRow(tbl.rows.length);
	row.className += "tblRowB";

	for ( var i = 0; i < colCount; i++) {
		var newcell = row.insertCell(i);
		newcell.innerHTML = '<br />';
	}

}

function hasMoreItems(dropdownName, buttonName) {
	if (document.getElementById(dropdownName).length == 0)
		document.getElementById(buttonName).disabled = true;

}