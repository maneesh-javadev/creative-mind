var singleFlag=false;
function HideShowDivs(id) {
	
	var st = "0";
	$("#ddlgdLBDistrictAtVillage").val($("#ddlgdLBDistrictAtVillage option:first").val());
	dwr.util.removeAllOptions('ddlgdLBInterAtVillage');
	st = document.getElementById('ddlgdLBInterAtVillage');
	st.add(new Option('Select', '0'));
	var type = document.getElementById('Tier').value;
	if (type == 2){
		twoTier(id);
	}else if (type == 3){
		threeTier(id);
	}else if (type == 1){
		singleTier(id);
		singleFlag=true;
	}
		
}

function singleTier(id){
	if (id != "" && id != "0"){
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];
		if(id2=="V"){
			document.getElementById('gplocalbody').style.visibility = 'visible';
			document.getElementById('gplocalbody').style.display = 'block';
		}
	}
}

function twoTier(id) {
	if (id != "" && id != "0") {
		var temp = id.split(":");

		var id1 = temp[0];
		var id2 = temp[1];
		getLBSubTypeList(id1);
		getMapUploadOption(id2);

		switch (id2) {

		case 'V':
			document.getElementById('divLgdSelIntermediateP').style.visibility = 'visible';
			document.getElementById('divLgdSelIntermediateP').style.display = 'block';
			document.getElementById('divLgdVillageP').style.display = 'none';
			document.getElementById('Districtlocalbody').style.display = 'none';
			document.getElementById('IPMerge1').style.display = 'none';
			document.getElementById('IPMerge2').style.display = 'none';
			document.getElementById('IPMerge3').style.display = 'none';
			document.getElementById('gplocalbody').style.display = 'none';
			document.getElementById('Districtlistlocalbodylist').style.display = 'none';
			document.getElementById('show_dp').checked = false;
			document.getElementById('hide_dp').checked = false;
			break;

		case 'D':
			document.getElementById('Districtlocalbody').style.visibility = 'visible';
			document.getElementById('Districtlocalbody').style.display = 'block';
			document.getElementById('divLgdVillageP').style.display = 'none';
			document.getElementById('divLgdSelIntermediateP').style.display = 'none';
			document.getElementById('IPlocalbody').style.display = 'none';
			document.getElementById('show_dp').checked = false;
			document.getElementById('hide_dp').checked = false;
			document.getElementById('Districtlistlocalbodylist').style.display = 'none';
			document.getElementById('IPMerge1').style.display = 'none';
			document.getElementById('IPMerge2').style.display = 'none';
			document.getElementById('IPMerge3').style.display = 'none';
			document.getElementById('Districtlistlocalbodylist').style.display = 'none';
			document.getElementById('gplocalbody').style.display = 'none';

			break;

		default:

			document.getElementById('divLgdSelIntermediateP').style.display = 'none';
			document.getElementById('divLgdVillageP').style.display = 'none';
			document.getElementById('Districtlocalbody').style.display = 'none';
			document.getElementById('IPMerge1').style.display = 'none';
			document.getElementById('Districtlistlocalbodylist').style.display = 'none';
			document.getElementById('show_dp').checked = false;
			document.getElementById('hide_dp').checked = false;

		}

		/*
		 * if (document.getElementById('ddlgdLBDistrictAtVillage').value != 0) {
		 * getWorkOnVillagePanchayatforDistrictP(document.getElementById('ddlgdLBDistrictAtVillage').value); }
		 */

	}

}
function threeTier(id) {

	if (id != "" && id != "0") {
		var temp = id.split(":");

		var id1 = temp[0];
		var id2 = temp[1];
		getLBSubTypeList(id1);
		getMapUploadOption(id2);

		switch (id2) {

		case 'I':
			document.getElementById('divLgdSelIntermediateP').style.visibility = 'visible';
			document.getElementById('divLgdSelIntermediateP').style.display = 'block';
			document.getElementById('divLgdVillageP').style.display = 'none';
			document.getElementById('Districtlocalbody').style.display = 'none';
			document.getElementById('IPMerge1').style.display = 'none';
			document.getElementById('IPMerge2').style.display = 'none';
			document.getElementById('IPMerge3').style.display = 'none';
			document.getElementById('gplocalbody').style.display = 'none';
			document.getElementById('Districtlistlocalbodylist').style.display = 'none';
			document.getElementById('show_dp').checked = false;
			document.getElementById('hide_dp').checked = false;

			break;
		case 'V':
			document.getElementById('divLgdSelIntermediateP').style.display = 'none';
			document.getElementById('divLgdVillageP').style.visibility = 'visible';
			document.getElementById('divLgdVillageP').style.display = 'block';
			document.getElementById('Districtlocalbody').style.display = 'none';
			document.getElementById('IPlocalbody').style.display = 'none';
			document.getElementById('IPMerge1').style.display = 'none';
			document.getElementById('IPMerge2').style.display = 'none';
			document.getElementById('IPMerge3').style.display = 'none';
			document.getElementById('Districtlistlocalbodylist').style.display = 'none';
			document.getElementById('show_dp').checked = false;
			document.getElementById('hide_dp').checked = false;

			break;
		case 'D':
			document.getElementById('Districtlocalbody').style.visibility = 'visible';
			document.getElementById('Districtlocalbody').style.display = 'block';
			document.getElementById('divLgdVillageP').style.display = 'none';
			document.getElementById('divLgdSelIntermediateP').style.display = 'none';
			document.getElementById('IPlocalbody').style.display = 'none';
			document.getElementById('show_dp').checked = false;
			document.getElementById('hide_dp').checked = false;
			document.getElementById('Districtlistlocalbodylist').style.display = 'none';
			document.getElementById('IPMerge1').style.display = 'none';
			document.getElementById('IPMerge2').style.display = 'none';
			document.getElementById('IPMerge3').style.display = 'none';
			document.getElementById('Districtlistlocalbodylist').style.display = 'none';
			document.getElementById('gplocalbody').style.display = 'none';
			break;

		default:

			document.getElementById('divLgdSelIntermediateP').style.display = 'none';
			document.getElementById('divLgdVillageP').style.display = 'none';
			document.getElementById('Districtlocalbody').style.display = 'none';
			document.getElementById('IPMerge1').style.display = 'none';
			document.getElementById('Districtlistlocalbodylist').style.display = 'none';
			document.getElementById('show_dp').checked = false;
			document.getElementById('hide_dp').checked = false;

		}

		/*
		 * if (document.getElementById('ddlgdLBDistrictAtVillage').value != 0) {
		 * getWorkOnVillagePanchayatforDistrictP(document.getElementById('ddlgdLBDistrictAtVillage').value); }
		 */

	}

}
function getlblist() {

	var id;
	var flag = document.getElementById("flag1").value;
	if (flag == 0) {
		alert("Please Select the Localbdoy You Want to delete");
		return false;
	}
	if (flag == 1) {

		id = document.getElementById("lblist1").value;
		document.getElementById('IPMerge1').style.visibility = 'visible';
		document.getElementById('IPMerge1').style.display = 'block';
		document.getElementById('Districtlistlocalbodylist').style.visibility = 'visible';
		document.getElementById('Districtlistlocalbodylist').style.display = 'block';

		lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
			callback : handledpSuccess,
			errorHandler : handledpError
		});

	}

	else if (flag == 2) {

		id = document.getElementById("lblist2").value;
		document.getElementById('IPMerge2').style.visibility = 'visible';
		document.getElementById('IPMerge2').style.display = 'block';

		document.getElementById('Districtlistlocalbodylist').style.visibility = 'visible';
		document.getElementById('Districtlistlocalbodylist').style.display = 'block';
		lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
			callback : handledpSuccess,
			errorHandler : handledpError
		});

	}

	else if (flag == 3) {

		id = document.getElementById("lblist3").value;
		document.getElementById('IPMerge3').style.visibility = 'visible';
		document.getElementById('IPMerge3').style.display = 'block';

		document.getElementById('Districtlistlocalbodylist').style.visibility = 'visible';
		document.getElementById('Districtlistlocalbodylist').style.display = 'block';
		lgdDwrlocalBodyService.getCoveredVillageListforLB(id, {
			callback : handleVPSuccess,
			errorHandler : handleVPError
		});
	}

}
function handleVPSuccess(data) {
	if (data != null) {
		var fieldId = 'availablelb';
		var valueText = 'landRegionCode';
		var nameText = 'landRegionNameEnglish';

		dwr.util.removeAllOptions(fieldId);

		var options = $("#availablelb");
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'disabled');
				$(option).val(obj.landRegionCode).text(obj.landRegionNameEnglish);
				options.append(option);
			} else {
				$(option).val(obj.landRegionCode).text(obj.landRegionNameEnglish);
				options.append(option);
			}
		});

	} else
		alert("No Data Found !");

}
function handleVPError() {
	// Show a popup message

}
function handledpSuccess(data) {
	// Assigns data to result id
	var fieldId = 'availablelb';

	dwr.util.removeAllOptions(fieldId);

	var options = $("#availablelb");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options.append(option);
		}
	});
}

function handledpError() {
	// Show a popup message
	alert("No data found!");
}
function hidelblist() {

	document.getElementById('Districtlistlocalbodylist').style.display = 'none';
	document.getElementById('IPMerge1').style.display = 'none';
	document.getElementById('IPMerge2').style.display = 'none';
	document.getElementById('IPMerge3').style.display = 'none';

}

function validateSelectedAddMore() {

	selectall();
	if (validateformWithoutSave()) {

		var flag = document.getElementById("flag1").value;
		if (flag == 1) {
			setSessionValue(document.getElementById('mergelocalbody').value, gelSelectedVillages('choosenlb'));

			removeSelectedValueDistrict('mergelocalbody');
			removeEverything('choosenlb');
		} else if (flag == 2) {
			setSessionValue(document.getElementById('mergelocalbody2').value, gelSelectedVillages('choosenlb'));

			removeSelectedValueDistrict('mergelocalbody2');
			removeEverything('choosenlb');
		} else if (flag == 3) {
			setSessionValue(document.getElementById('mergelocalbody3').value, gelSelectedVillages('choosenlb'));

			removeSelectedValueDistrict('mergelocalbody3');
			removeEverything('choosenlb');
		}

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
function setSessionValue(val1, val2, val3) {
	lgdDwrDistrictService.invalidateLoop(val1, val2, val3, {
		callback : handleInvalidateLoop,
		errorHandler : handleInvalidateLoopError
	});

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

function handleInvalidateLoop(data) {

	if (data) {
		// alert('success');
	}

}

function handleInvalidateLoopError() {
	alert("No data found!");
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

function generateTempView() {
	if (document.getElementById('choosenlb').length == 0)
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
			newcell.innerHTML = '<strong>' + document.getElementById('mergelocalbody')[document.getElementById('mergelocalbody').selectedIndex].text
					+ '</strong>';
		else
			newcell.innerHTML = document.getElementById('choosenlb')[0].text;
	}

	for ( var i = 1; i < document.getElementById('choosenlb').length; i++) {
		row = tbl.insertRow(tbl.rows.length);
		row.className += "tblRowB";
		for ( var j = 0; j < colCount; j++) {
			var newcell = row.insertCell(j);

			if (j == 1)
				newcell.innerHTML = document.getElementById('choosenlb')[i].text;
		}
	}

	row = tbl.insertRow(tbl.rows.length);
	row.className += "tblRowB";

	for ( var i = 0; i < colCount; i++) {
		var newcell = row.insertCell(i);
		newcell.innerHTML = '<br />';
	}

}
function generateTempView2() {
	if (document.getElementById('choosenlb').length == 0)
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
					+ document.getElementById('mergelocalbody2')[document.getElementById('mergelocalbody2').selectedIndex].text + '</strong>';
		else
			newcell.innerHTML = document.getElementById('choosenlb')[0].text;
	}

	for ( var i = 1; i < document.getElementById('choosenlb').length; i++) {
		row = tbl.insertRow(tbl.rows.length);
		row.className += "tblRowB";
		for ( var j = 0; j < colCount; j++) {
			var newcell = row.insertCell(j);

			if (j == 1)
				newcell.innerHTML = document.getElementById('choosenlb')[i].text;
		}
	}

	row = tbl.insertRow(tbl.rows.length);
	row.className += "tblRowB";

	for ( var i = 0; i < colCount; i++) {
		var newcell = row.insertCell(i);
		newcell.innerHTML = '<br />';
	}

}
function selectall() {

	var selObj = document.getElementById('choosenlb');
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}
	var selObj = document.getElementById('availablelb');
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}
}

function validateformWithoutSave() {

	var status = false;
	var flag = document.getElementById("flag1").value;
	if (flag == 1) {
		if (merge1())
			status = true;
	} else if (flag == 2) {
		if (merge2())
			status = true;
	} else if (flag == 3) {
		if (merge3())
			status = true;
	}
	return status;
}
function merge1() {

	var show_dp = document.getElementById('show_dp').value;
	var mergelocalbody = document.getElementById('mergelocalbody').value;
	if (mergelocalbody == 0) {
		alert("Please Select Localbody ");
		document.getElementById('mergelocalbody').focus();
		return false;
	}
	if (document.getElementById('show_dp').checked) {
		if (show_dp == 0 || show_dp == "") {
			alert("Please Select Localbody To merge with");
			document.getElementById('show_dp').focus();
			return false;
		}
	} else if (document.getElementById('hide_dp').checked) {
		// alert(document.getElementById('hide_dp').checked);
		if (mergelocalbody == 0 || mergelocalbody == "") {
			alert("Please Select Local Body  To merge with");
			document.getElementById('mergelocalbody').focus();
			return false;
		}
		if (mergelocalbody > 0 || mergelocalbody != "") {
			if (!document.getElementById('chooseMoreBtn').disabled && document.getElementById('availablelb').length > 0
					&& document.getElementById('mergelocalbody').length <= 2) {
				alert('Please Select all Child Local Body  to merge with IP');
				return false;
			}
		}

	}
	appendList();
	generateTempView();
	hasMoreItems("availablelb", "chooseMoreBtn");
	return true;
}
function merge2() {

	var show_dp = document.getElementById('show_dp').value;
	var mergelocalbody = document.getElementById('mergelocalbody2').value;
	if (mergelocalbody == 0) {
		alert("Please Select Localbody ");
		document.getElementById('mergelocalbody2').focus();
		return false;
	}
	if (document.getElementById('show_dp').checked) {
		if (show_dp == 0 || show_dp == "") {
			alert("Please Select Localbody To merge with");
			document.getElementById('show_dp').focus();
			return false;
		}
	} else if (document.getElementById('hide_dp').checked) {
		// alert(document.getElementById('hide_dp').checked);
		if (mergelocalbody == 0 || mergelocalbody == "") {
			alert("Please Select Local Body  To merge with");
			document.getElementById('mergelocalbody2').focus();
			return false;
		}
		if (mergelocalbody > 0 || mergelocalbody != "") {
			if (!document.getElementById('chooseMoreBtn').disabled && document.getElementById('availablelb').length > 0
					&& document.getElementById('mergelocalbody2').length <= 2) {
				alert('Please Select all Child Local Body  to merge with IP');
				return false;
			}
		}

	}
	appendList2();
	generateTempView2();
	hasMoreItems("availablelb", "chooseMoreBtn");
	return true;
}
function appendList() {

	var list = "";
	var a = document.getElementById('listformat').value;
	list = a;

	var districtlist = document.getElementById("mergelocalbody");
	list += districtlist.options[districtlist.selectedIndex].value + "#";

	var subdistrictlist = document.getElementById("choosenlb");

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
function appendList2() {
	var list = "";
	var a = document.getElementById('listformat').value;
	list = a;
	var districtlist = document.getElementById("mergelocalbody2");
	list += districtlist.options[districtlist.selectedIndex].value + "#";
	var subdistrictlist = document.getElementById("choosenlb");
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
function hasMoreItems(dropdownName, buttonName) {
	if (document.getElementById(dropdownName).length == 0)
		document.getElementById(buttonName).disabled = true;

}

function validateSelectAndSubmit() {
	if (prevalidate()) {
		selectall();
		var mergelocalbody = document.getElementById('show_dp').value;
		if (validateform()) {
			if (document.getElementById('lblist1').checked) {
				if (mergelocalbody == 0) {
					alert('Please Select Localbody to be merge with');
					return false;
				}
			}

			document.forms['invalidateLocalBodyforTRI'].submit();
		}
	}
}
function validateform() {
	var ddLgdLBType = document.getElementById('ddLgdLBType').value;
	/*
	 * var ddDistrictMergeYes =
	 * document.getElementById('ddDistrictMergeYes').value;
	 */

	if (ddLgdLBType == 0) {
		// alert("Please Select District");
		document.getElementById('ddLgdLBType').focus();
		return false;
	}
	/*
	 * if (document.getElementById('districtdelete_yes').checked) { if
	 * (ddDistrictMergeYes == 0 || ddDistrictMergeYes == "") { // alert("Please
	 * Select District To merge with");
	 * document.getElementById('ddDistrictMergeYes').focus(); return false; } }
	 * else
	 */
	if (document.getElementById('show_dp').checked) {

		if (!document.getElementById('chooseMoreBtn').disabled) {
			alert('Please Select all localbody to merge with target LocalBody');
			return false;
		}

	}
	return true;
}

function getlistofIntermediatePanchayat(id) {

	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleInterPanchayatICASuccess,
		errorHandler : handleInterPanchayatICAError
	});
}

function handleInterPanchayatICASuccess(data) {

	document.getElementById('IPlocalbody').style.visibility = 'visible';
	document.getElementById('IPlocalbody').style.display = 'block';
	var fieldId = 'lblist2';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('lblist2');
	st.add(new Option('Select LocalBody', '0'));

	var options = $("#lblist2");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options.append(option);
		}
	});

}

function handleInterPanchayatICAError() {

	alert("No data found!");
}
function setflag1() {
	
	
	document.getElementById('flag1').value = "1";
	document.getElementById('IPMerge1').style.display = 'none';
	document.getElementById('Districtlistlocalbodylist').style.display = 'none';
	document.getElementById('show_dp').checked = false;
	dwr.util.setValue('selectEntity', "Select Child Local Bodies", {
		escapeHtml : false
	});
	dwr.util.setValue('selectedEntity', "Selected Child Local Bodies", {
		escapeHtml : false
	});
}

function setflag2() {
	document.getElementById('flag1').value = "2";
	document.getElementById('IPMerge2').style.display = 'none';
	document.getElementById('Districtlistlocalbodylist').style.display = 'none';
	document.getElementById('show_dp').checked = false;
	var type = document.getElementById('Tier').value;
	if (type == 2) {
		dwr.util.setValue('selectEntity', "Select Village", {
			escapeHtml : false
		});
		dwr.util.setValue('selectedEntity', "Selected Villages", {
			escapeHtml : false
		});
	} else if (type == 3) {
		dwr.util.setValue('selectEntity', "Select Child Local Bodies", {
			escapeHtml : false
		});
		dwr.util.setValue('selectedEntity', "Selected Child Local Bodies", {
			escapeHtml : false
		});
	}

}
function setflag3() {

	document.getElementById('flag1').value = "3";
	document.getElementById('IPMerge3').style.display = 'none';
	document.getElementById('Districtlistlocalbodylist').style.display = 'none';
	document.getElementById('show_dp').checked = false;
	dwr.util.setValue('selectEntity', "Select Village", {
		escapeHtml : false
	});
	dwr.util.setValue('selectedEntity', "Selected Villages", {
		escapeHtml : false
	});
}
function removeListItem() {
	var flag = document.getElementById("flag1").value;
	if (flag == 1)
		removeItem1();
	if (flag == 2)
		removeItem2();
	if (flag == 3)
		removeItem3();
}
function removeItem1(val) {

	var dwrDistrictList = document.getElementById('lblist1');

	if (dwrDistrictList.selectedIndex > 0) {

		var selectedValue = dwrDistrictList.value;
		var pos;

		removeEverything('mergelocalbody');

		document.getElementById('mergelocalbody').add(new Option('Select Localbody', 0));

		var options = $("#mergelocalbody");
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
		/*
		 * for ( var i = 0; i < dwrDistrictList.length; i++) {
		 * 
		 * document.getElementById('mergelocalbody').add(new
		 * Option(dwrDistrictList[i].text, dwrDistrictList[i].value)); }
		 */

		for ( var i = 0; i < document.getElementById('mergelocalbody').length; i++) {
			if (document.getElementById('mergelocalbody')[i].value == selectedValue) {
				pos = i;
			}
		}

		document.getElementById('mergelocalbody').selectedIndex = pos;

		removeSelectedValue('mergelocalbody');

	}
}

function removeItem2() {

	var dwrDistrictList = document.getElementById('lblist2');

	if (dwrDistrictList.selectedIndex > 0) {
		var selectedValue = dwrDistrictList.value;
		var pos;

		removeEverything('mergelocalbody2');

		document.getElementById('mergelocalbody2').add(new Option('Select Localbody', 0));

		var options = $("#mergelocalbody2");
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

		/*
		 * for ( var i = 0; i < dwrDistrictList.length; i++) {
		 * 
		 * document.getElementById('mergelocalbody2').add(new
		 * Option(dwrDistrictList[i].text, dwrDistrictList[i].value)); }
		 */

		for ( var i = 0; i < document.getElementById('mergelocalbody2').length; i++) {
			if (document.getElementById('mergelocalbody2')[i].value == selectedValue) {
				pos = i;
			}
		}

		document.getElementById('mergelocalbody2').selectedIndex = pos;

		removeSelectedValue('mergelocalbody2');

	}
}
function removeItem3() {

	var dwrDistrictList = document.getElementById('lblist3');

	if (dwrDistrictList.selectedIndex > 0) {
		var selectedValue = dwrDistrictList.value;
		var pos;

		removeEverything('mergelocalbody3');

		document.getElementById('mergelocalbody3').add(new Option('Select Localbody', 0));

		var options = $("#mergelocalbody3");
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

		/*
		 * for ( var i = 0; i < dwrDistrictList.length; i++) {
		 * 
		 * document.getElementById('mergelocalbody3').add(new
		 * Option(dwrDistrictList[i].text, dwrDistrictList[i].value)); }
		 */

		for ( var i = 0; i < document.getElementById('mergelocalbody3').length; i++) {
			if (document.getElementById('mergelocalbody3')[i].value == selectedValue) {
				pos = i;
			}
		}

		document.getElementById('mergelocalbody3').selectedIndex = pos;

		removeSelectedValue('mergelocalbody3');

	}
}
function removeEverything(val) {
	var elSel = document.getElementById(val);
	for ( var i = elSel.length - 1; i >= 0; i--)
		elSel.remove(i);
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

function getlistofgp(id) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleGPCASuccess,
		errorHandler : handleInterPanchayatICAError
	});
}
function handleGPCASuccess(data) {

	document.getElementById('gplocalbody').style.visibility = 'visible';
	document.getElementById('gplocalbody').style.display = 'block';
	var fieldId = 'lblist3';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('lblist3');
	st.add(new Option('Select Gram Panchayat', '0'));
	var options = $("#lblist3");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options.append(option);
		}
	});

}
function HideShow(id) {
	if (id != "" && id != "0") {
	
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];
		getLBSubTypeList(id1);
		getMapUploadOption(id2);
		setUrbanlocabodies(id1);
		/*
		 * switch (id1) { case '5': setUrbanlocabodies(id1);
		 * document.getElementById('Muncipiality').style.visibility='visible';
		 * document.getElementById('Muncipiality').style.display = 'block';
		 * document.getElementById('Districtlocalbody').style.display = 'none';
		 * break; case '4': setUrbanlocabodies(id1);
		 * document.getElementById('Districtlocalbody').style.visibility='visible';
		 * document.getElementById('Districtlocalbody').style.display = 'block';
		 * document.getElementById('Muncipiality').style.display = 'none';
		 * break; case '6': setUrbanlocabodies(id1); break; case '7':
		 * setUrbanlocabodies(id1); break; case '8': setUrbanlocabodies(id1);
		 * break; default: break; }
		 */

		if (document.getElementById('ddlgdLBDistrictAtVillage').value != 0) {
			getWorkOnVillagePanchayatforDistrictP(document.getElementById('ddlgdLBDistrictAtVillage').value);
		}
		if (chkExist.checked) {
			getHideLocalBodyParentList(id, chkExist.checked);
		}
		if (chkLgdLBUnmapped.checked) {
			getHideUnmappedList(id, chkLgdLBUnmapped.checked);
		}
	}

}
function setUrbanlocabodies(s) {
	var scode = document.getElementById('flag2').value;
	lgdDwrlocalBodyService.getPanchayatListbyStateandlbTypeCode(scode, s, {
		callback : handlUrbanSuccess,
		errorHandler : handleUrbanError
	});
}
function handlUrbanSuccess(data) {
	document.getElementById('Districtlocalbody').style.visibility = 'visible';
	document.getElementById('Districtlocalbody').style.display = 'block';
	// Assigns data to result id
	var fieldId = 'lblist1';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('lblist1');
	st.add(new Option(' --  Select LocalBody -- ', '0'));
	var options = $("#lblist1");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options.append(option);
		}
	});
}

function handleUrbanError() {
	// Show a popup message
	alert("No data found!");
}
function validateAndSubmit() {
	if (document.getElementById('ddLgdLBType').selectedIndex == 0) {
		alert("Please Select local body Type");
		return false;
	}
	var flag = document.getElementById("flag1").value;
	if (flag == 0) {
		alert("Please Select local body");
		return false;
	}
	if (flag == 1) {
		if (document.getElementById('lblist1').selectedIndex == 0) {
			alert("Please Select local body");
			return false;
		}
	}
	if (flag == 2) {
		if (document.getElementById('lblist2').selectedIndex == 0) {
			alert("Please Select local body");
			return false;
		}
	}
	/*
	 * start Autohr Kirandeep Date 18/06/2014
	 */
	$('input[name=Submit]').prop('disabled', true);
	
	//$('#invalidateLocalBodyforURB').submit();
	// document.forms['invalidateLocalBodyforURB'].submit();
	 callActionUrl('invalidateLocalBodyforURB.htm'); //invalidateURBFinal
}



function prevalidate() {
	
	var success = true;
	if(singleFlag){
		var elt = document.getElementById('ddLgdLBType').value;
		var invalidateLb=document.getElementById('lblist3').value;
		if(elt=="0"){
			alert("Please Select Local Body Type");
			success = false;
		}else if(invalidateLb=="0"){
			alert("Please Select Local Body to Delete");
			success = false;
		}else if (document.getElementById('show_dp').checked == false && document.getElementById('hide_dp').checked == false) {
			alert("Please Select Option Value");
			success = false;
		}
	}else{
		var elt = document.getElementById('ddLgdLBType').value;
		var temp = elt.split(":");
		var id = temp[0];
		
		var lblist2 = 1;
		var type = document.getElementById('Tier').value;
		if (elt == 0) {
			alert("Please Select Local Body Type");
			success = false;
		}
		if (id == 9 || id == 10 || id == 13) {
			var dp = document.getElementById('lblist1').value;
			if (dp == 0) {
				alert("Please Select Local Body to Delete");
				success = false;
			} else if (document.getElementById('show_dp').checked == false && document.getElementById('hide_dp').checked == false) {
				alert("Please Select Option Value");
				success = false;
			}
		}

		else if (id == 14 || type == 2) {
			var dp = document.getElementById('ddlgdLBDistAtInter').value;
			if (dp == 0) {
				alert("Please Select Intermediate Local Body Type");
				success = false;
			} else {
				lblist2 = document.getElementById('lblist2').value;

				if (lblist2 == 0) {
					alert("Please Select Local Body to Delete");
					success = false;
				} else if (document.getElementById('show_dp').checked == false && document.getElementById('hide_dp').checked == false) {
					alert("Please Select Option Value");
					success = false;
				}
			}
		} else if (id == 11 || id == 12 || id == 15 || id == 16) {
			var dp = document.getElementById('ddlgdLBDistrictAtVillage').value;
			var ip = document.getElementById('ddlgdLBInterAtVillage').value;
			if (dp == 0) {
				alert("Please Select Intermediate Local Body Type");
				success = false;
			} else if (ip == 0) {
				alert("Please Select Village Local Body Type");
				success = false;
			} else {
				lblist2 = document.getElementById('lblist3').value;

				if (lblist2 == 0) {
					alert("Please Select Local Body to Delete");
					success = false;
				} else if (document.getElementById('show_dp').checked == false && document.getElementById('hide_dp').checked == false) {
					alert("Please Select Option Value");
					success = false;
				}
			}
		}
	}
	
	return success;
}
function merge3() {

	var show_dp = document.getElementById('show_dp').value;
	var mergelocalbody = document.getElementById('mergelocalbody3').value;
	if (mergelocalbody == 0) {
		alert("Please Select Localbody ");
		document.getElementById('mergelocalbody3').focus();
		return false;
	}
	if (document.getElementById('show_dp').checked) {
		if (show_dp == 0 || show_dp == "") {
			alert("Please Select Localbody To merge with");
			document.getElementById('show_dp').focus();
			return false;
		}
	} else if (document.getElementById('hide_dp').checked) {
		// alert(document.getElementById('hide_dp').checked);
		if (mergelocalbody == 0 || mergelocalbody == "") {
			alert("Please Select Local Body  To merge with");
			document.getElementById('mergelocalbody3').focus();
			return false;
		}
		if (mergelocalbody > 0 || mergelocalbody != "") {
			if (!document.getElementById('chooseMoreBtn').disabled && document.getElementById('availablelb').length > 0
					&& document.getElementById('mergelocalbody3').length <= 2) {
				alert('Please Select all Child Local Body  to merge with IP');
				return false;
			}
		}

	}
	appendList3();
	generateTempView3();
	hasMoreItems("availablelb", "chooseMoreBtn");
	return true;
}
function appendList3() {
	var list = "";
	var a = document.getElementById('listformat').value;
	list = a;
	var districtlist = document.getElementById("mergelocalbody3");
	list += districtlist.options[districtlist.selectedIndex].value + "#";
	var subdistrictlist = document.getElementById("choosenlb");
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
function generateTempView3() {
	if (document.getElementById('choosenlb').length == 0)
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
					+ document.getElementById('mergelocalbody3')[document.getElementById('mergelocalbody3').selectedIndex].text + '</strong>';
		else
			newcell.innerHTML = document.getElementById('choosenlb')[0].text;
	}

	for ( var i = 1; i < document.getElementById('choosenlb').length; i++) {
		row = tbl.insertRow(tbl.rows.length);
		row.className += "tblRowB";
		for ( var j = 0; j < colCount; j++) {
			var newcell = row.insertCell(j);

			if (j == 1)
				newcell.innerHTML = document.getElementById('choosenlb')[i].text;
		}
	}

	row = tbl.insertRow(tbl.rows.length);
	row.className += "tblRowB";

	for ( var i = 0; i < colCount; i++) {
		var newcell = row.insertCell(i);
		newcell.innerHTML = '<br />';
	}

}
function removeData() {
	$("#availablelb").empty();
	$("#choosenlb").empty();
	deletetemptable();
	document.getElementById("chooseMoreBtn").disabled = false;
}
function deletetemptable() {

	var table = document.getElementById('dynamicTbl');
	var rowCount = table.rows.length;
	while (rowCount > 1) {
		for ( var i = 1; i < rowCount; i++) {
			table.deleteRow(i);
			rowCount--;
		}
	}
}
