//DWR Dropdownlist Population
function selectEverything() {

	var selObj = document.getElementById('ddDestDistrict');

	var districtCode = "";
	for (i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		districtCode += selObj.options[i].value + ",";
	}

	getCoveredLandForDistRegion(districtCode);

}

function getCoveredLandForDistRegion(id) {

	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		lgdDwrStateService.getPartDistricts(id, {
			callback : handleDistrictSuccess,
			errorHandler : handleDistrictError
		});
	}
}

function handleDistrictSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddDistrict';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';

	if (doRemove)
		dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleDistrictError() {
	// Show a popup message
	alert("No data found!");
}

function formSubmitAdd() {
	document.getElementById('addAnotherSD').value = "true";
	selectFinal();
}

function validateForm(val2) {

	document.getElementById('buttonClicked').value = val2;

	formSubmitAdd(val2);

}

function hideerrorstateOrUt() {
	$("#stateOrUt_error").hide();
}
function NameInEn() {

	if (document.getElementById("districtNameInEn").value == "") {

		document.getElementById("districtNameInEn_error").innerHTML = "Name of new State (In English) required";
		$("#districtNameInEn_error").show();
		$("#districtNameInEn_msg").hide();
		$("#districtNameInEn").addClass("error_fld");
		$("#districtNameInEn").addClass("error_msg");
		return false;

	} else if (document.getElementById("districtNameInEn").value.length > 50) {

		document.getElementById("districtNameInEn_error").innerHTML = "Name of new State (In English) should  not exceed 50 characters.";
		$("#districtNameInEn_msg").hide();
		$("#districtNameInEn_error").show();
		$("#districtNameInEn").addClass("error_fld");
		$("#districtNameInEn").addClass("error_msg");
		return false;

	}

	else if (document.getElementById("districtNameInEn").value.length < 3) {

		document.getElementById("districtNameInEn_error").innerHTML = "Name of new State (In English) should  not less than 3 characters.";
		$("#districtNameInEn_msg").hide();
		$("#districtNameInEn_error").show();
		$("#districtNameInEn").addClass("error_fld");
		$("#districtNameInEn").addClass("error_msg");
		return false;

	} else {
		$("#districtNameInEn_msg").hide();
		return true;

	}
}

// Name in local language

function NameInLcl() {

	if (document.getElementById("districtNameInLcl").value == "") {

		document.getElementById("districtNameInLcl_error").innerHTML = "Name of new State (In English) required";
		$("#districtNameInLcl_error").show();
		$("#districtNameInLcl_msg").hide();
		$("#districtNameInLcl").addClass("error_fld");
		$("#districtNameInLcl").addClass("error_msg");
		return false;

	} else if (document.getElementById("districtNameInEn").value.length > 60) {

		document.getElementById("districtNameInLcl_error").innerHTML = "Name of new State (In English) should  not exceed 60 characters.";
		$("#districtNameInLcl_msg").hide();
		$("#districtNameInLcl_error").show();
		$("#districtNameInLcl").addClass("error_fld");
		$("#districtNameInLcl").addClass("error_msg");
		return false;

	}

	else if (document.getElementById("districtNameInLcl").value.length <= 5) {

		document.getElementById("districtNameInLcl_error").innerHTML = "Name of new State (In English) should  not less thsn 5 characters.";
		$("#districtNameInLcl_msg").hide();
		$("#districtNameInLcl_error").show();
		$("#districtNameInLcl").addClass("error_fld");
		$("#districtNameInLcl").addClass("error_msg");
		return false;

	} else {
		$("#districtNameInLcl_msg").hide();
		return true;

	}
}

// Alias of the district(In English)

function AliasInEn() {

	if (document.getElementById("districtAliasInEn").value == "") {

		document.getElementById("districtAliasInEn_error").innerHTML = "Name of new State (In English) required";
		$("#districtAliasInEn_error").show();
		$("#districtAliasInEn_msg").hide();
		$("#districtAliasInEn").addClass("error_fld");
		$("#districtAliasInEn").addClass("error_msg");
		return false;

	} else if (document.getElementById("districtAliasInEn").value.length > 60) {

		document.getElementById("districtAliasInEn_error").innerHTML = "Alias of the State should  not exceed 60 characters.";
		$("#districtAliasInEn_msg").hide();
		$("#districtAliasInEn_error").show();
		$("#districtAliasInEn").addClass("error_fld");
		$("#districtAliasInEn").addClass("error_msg");
		return false;

	}

	else if (document.getElementById("districtAliasInEn").value.length <= 5) {

		document.getElementById("districtAliasInEn_error").innerHTML = "Alias of the State should  not less thsn 5 characters.";
		$("#districtAliasInEn_msg").hide();
		$("#districtAliasInEn_error").show();
		$("#districtAliasInEn").addClass("error_fld");
		$("#districtAliasInEn").addClass("error_msg");
		return false;

	} else {
		$("#districtAliasInEn_msg").hide();
		return true;

	}
}

// Alias of the district (In Local language)

function AliasInLcl() {

	if (document.getElementById("districtAliasInLcl").value == "") {

		document.getElementById("districtNameInLcl_error").innerHTML = "Name of new State (In English) required";
		$("#districtAliasInLcl_error").show();
		$("#districtAliasInLcl_msg").hide();
		$("#districtAliasInLcl").addClass("error_fld");
		$("#districtAliasInLcl").addClass("error_msg");
		return false;

	} else if (document.getElementById("districtAliasInLcl").value.length > 60) {

		document.getElementById("districtNameInLcl_error").innerHTML = "Alias of the State (In Local language) should  not exceed 60 characters.";
		$("#districtAliasInLcl_msg").hide();
		$("#districtAliasInLcl_error").show();
		$("#districtAliasInLcl").addClass("error_fld");
		$("#districtAliasInLcl").addClass("error_msg");
		return false;

	}

	else if (document.getElementById("districtAliasInLcl").value.length <= 5) {

		document.getElementById("districtAliasInLcl_error").innerHTML = "Alias of the State (In Local language) should  not less thsn 5 characters.";
		$("#districtAliasInLcl_msg").hide();
		$("#districtAliasInLcl_error").show();
		$("#districtAliasInLcl").addClass("error_fld");
		$("#districtAliasInLcl").addClass("error_msg");
		return false;

	} else {
		$("#districtAliasInLcl_msg").hide();
		return true;

	}
}

// Headquarters

function headquarters() {

	if (document.getElementById("districtHeadquarters").value == "") {

		document.getElementById("districtHeadquarters_error").innerHTML = "Name of new State (In English) required";
		$("#districtHeadquarters_error").show();
		$("#districtHeadquarters_msg").hide();
		$("#districtHeadquarters").addClass("error_fld");
		$("#districtHeadquarters").addClass("error_msg");
		return false;

	} else if (document.getElementById("districtHeadquarters").value.length > 60) {

		document.getElementById("districtHeadquarters_error").innerHTML = "Name of new State (In English) should  not exceed 60 characters.";
		$("#districtHeadquarters_msg").hide();
		$("#districtHeadquarters_error").show();
		$("#districtHeadquarters").addClass("error_fld");
		$("#districtHeadquarters").addClass("error_msg");
		return false;

	}

	else if (document.getElementById("districtHeadquarters").value.length <= 5) {

		document.getElementById("districtNameInLcl_error").innerHTML = "Name of new State (In English) should  not less thsn 5 characters.";
		$("#districtHeadquarters_msg").hide();
		$("#districtHeadquarters_error").show();
		$("#districtHeadquarters").addClass("error_fld");
		$("#districtHeadquarters").addClass("error_msg");
		return false;

	} else {
		$("#districtHeadquarters_msg").hide();
		return true;

	}
}

// Census2011 Code

function Cns2011Code() {

	if (document.getElementById("census2011Code").value == "") {

		document.getElementById("census2011Code_error").innerHTML = "Name of new State (In English) required";
		$("#census2011Code_error").show();
		$("#census2011Code_msg").hide();
		$("#census2011Code").addClass("error_fld");
		$("#census2011Code").addClass("error_msg");
		return false;

	} else if (document.getElementById("census2011Code").value.length > 60) {

		document.getElementById("census2011Code_error").innerHTML = "Census2011 Code should  not exceed 60 characters.";
		$("#census2011Code_msg").hide();
		$("#census2011Code_error").show();
		$("#census2011Code").addClass("error_fld");
		$("#census2011Code").addClass("error_msg");
		return false;

	}

	else if (document.getElementById("census2011Code").value.length <= 1) {

		document.getElementById("census2011Code_error").innerHTML = "Census2011 Code should  not less thsn 1 characters.";
		$("#census2011Code_msg").hide();
		$("#census2011Code_error").show();
		$("#census2011Code").addClass("error_fld");
		$("#census2011Code").addClass("error_msg");
		return false;

	} else {
		$("#census2011Code_msg").hide();
		return true;

	}
}

// /////////////////////////
function onloadState() {
	document.getElementById('addAnotherSD').value = "";
	var mypath = window.location.href;
	if (mypath.match('modify') == 'modify') {
		document.getElementById('Yes').checked = true;
		document.getElementById('reorgOption').style.visibility = 'visible';
		document.getElementById('newDistrict').disabled = true;
		document.getElementById('Yes').disabled = true;
		document.getElementById('ddDistrict').disabled = true;
		document.getElementById('partSubdistrict').disabled = true;
		document.getElementById('Yes').disabled = true;
	}

	if (mypath.match('districtmodify') == 'districtmodify') {
		document.getElementById('Yes').checked = true;
		document.getElementById('reorgOption').style.visibility = 'visible';
		document.getElementById('isModifyDistrict').disabled = true;

		document.getElementById('newDistrict').disabled = true;
		document.getElementById('Yes').disabled = true;
		document.getElementById('ddDistrict').disabled = true;
		document.getElementById('partSubdistrict').disabled = true;
		document.getElementById('Yes').disabled = true;
		// document.getElementById('isModifyDistrict').disabled = true;

	}

	if (mypath.match('createsubdistrict') == 'createsubdistrict') {
		document.getElementById('Yes').checked = true;
		document.getElementById('reorgOption').style.visibility = 'visible';
		document.getElementById('newVillage').disabled = true;
		document.getElementById('isModifyDistrict').disabled = true;
		document.getElementById('newDistrict').disabled = true;
		document.getElementById('Yes').disabled = true;
		document.getElementById('ddDistrict').disabled = true;
		document.getElementById('partSubdistrict').disabled = true;
		document.getElementById('Yes').disabled = true;
	}
	if (mypath.match('subdistrictmodify ') == 'subdistrictmodify ') {
		document.getElementById('Yes').checked = true;
		document.getElementById('reorgOption').style.visibility = 'visible';
		document.getElementById('newVillage').disabled = true;
		document.getElementById('isModifyDistrict').disabled = true;
		document.getElementById('newDistrict').disabled = true;
		document.getElementById('isModifySubdistrict').disabled = true;

		document.getElementById('Yes').disabled = true;
		document.getElementById('ddDistrict').disabled = true;
		document.getElementById('partSubdistrict').disabled = true;
		document.getElementById('Yes').disabled = true;
	}

	if (mypath.match('village') == 'village') {
		document.getElementById('Yes').checked = true;
		document.getElementById('reorgOption').style.visibility = 'visible';
		document.getElementById('newSubdistrict').disabled = true;
		document.getElementById('newVillage').disabled = true;

		document.getElementById('newDistrict').disabled = true;
		document.getElementById('isModifyDistrict').disabled = true;
		document.getElementById('isModifySubdistrict').disabled = true;
		document.getElementById('Yes').disabled = true;
		document.getElementById('ddDistrict').disabled = true;
		document.getElementById('partSubdistrict').disabled = true;
		document.getElementById('Yes').disabled = true;

	}

	if (mypath.match('villagemodify ') == 'villagemodify ') {
		document.getElementById('Yes').checked = true;
		document.getElementById('reorgOption').style.visibility = 'visible';

		document.getElementById('isModifyVillage').disabled = true;
		document.getElementById('newSubdistrict').disabled = true;
		document.getElementById('newDistrict').disabled = true;
		document.getElementById('newVillage').disabled = true;
		document.getElementById('isModifyDistrict').disabled = true;
		document.getElementById('isModifySubdistrict').disabled = true;
		document.getElementById('Yes').disabled = true;
		document.getElementById('ddDistrict').disabled = true;
		document.getElementById('partSubdistrict').disabled = true;
		document.getElementById('Yes').disabled = true;

	}
	hideGISComponentOnLoad();
}
function validationforstate() {
	var error = 0;

	if (document.getElementById('ddDestDistrict').options.length == 1 && (document.getElementById('ddDestDistrict').value.match('FULL')) == "FULL") {
		error = 1;
		alert("You Can't Make the New  State by Shifting Only One Full State. Kindly Select One More Contributing State.  !");
	}
	if (document.getElementById('ddDestDistrict').options.length == 0) {
		error = 1;
		alert("Kindly Select One or More Contributing  State.  !");
	}
	if (document.getElementById('ddDestDistrict').value.match('PART') == "PART") {
		if (document.getElementById('ddDestinctDistrict').options.length == 0) {
			error = 1;
			alert("Kindly Select One or More Contributing District.  !");
		}
	}

	if (document.getElementById('ddDestinctDistrict').options.length == 1
			&& (document.getElementById('ddDestinctDistrict').value.match('FULL')) == "FULL") {
		error = 1;
		alert("You Can't Make the New  State by Shifting Only One Full State. Kindly Select One More Contributing District.  !");
	}

	if (document.getElementById('ddDestinctDistrict').value.match('PART') == "PART") {
		if (document.getElementById('subDistrictListNew').options.length == 0) {
			error = 1;
			alert("Kindly Select One or More Contributing Subdistrict.  !");
		}
	}

	if (document.getElementById('subDistrictListNew').options.length == 1
			&& (document.getElementById('subDistrictListNew').value.match('FULL')) == "FULL") {
		error = 1;
		alert("You Can't Make the New State by Shifting Only One Full State. Kindly Select One More Contributing Sub District.  !");
	}

	/*
	 * if(document.getElementById('subDistrictListNew').value.match('PART')=="PART"){
	 * if(document.getElementById('villageListNew').options.length==0){ error=1;
	 * alert("Kindly Select One or More Contributing Villages. !"); } }
	 */
	if (error == 0) {
		// document.getElementById('ddDistrict').disabled = false;
		document.forms['form1'].submit();
	}
}

function selectpreview() {
	hideGISComponentOnLoad();
}

function selectFinal() {

	var selObj = document.getElementById('subDistrictListNew');
	var selObj1 = document.getElementById('ddDestDistrict');
	// var selObj2=document.getElementById('villageListNew');
	var selObj3 = document.getElementById('ddDestinctDistrict');

	var subDistrict = "";
	for (i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		subDistrict += selObj.options[i].value + ",";
	}
	for (j = 0; j < selObj1.options.length; j++) {
		selObj1.options[j].selected = true;
		subDistrict += selObj1.options[j].value + ",";
	}
	/*
	 * for (j = 0; j < selObj2.options.length; j++) {
	 * selObj2.options[j].selected=true; subDistrict
	 * +=selObj2.options[j].value+","; }
	 */
	for (j = 0; j < selObj3.options.length; j++) {
		selObj3.options[j].selected = true;
		subDistrict += selObj3.options[j].value + ",";
	}
	hideGISComponentOnLoad();
}

function formSubmitAdd() {
	document.getElementById('addAnotherSD').value = "";
	document.getElementById('addAnotherSD').value = "true";
	document.forms['district'].submit();
}
function formSubmitPreview() {
	selectFinal();
	document.getElementById('addAnotherSD').value = "";
	document.getElementById('addAnotherSD').value = "true";
	if (validateform())
		if (validationforstate())

			document.forms['form1'].submit();

}
// getsubdistrict start
function getSubDistricts(id) {

	lgdDwrSubDistrictService.getSubDistrictList(id, {
		callback : handleSubDistrict, // handleSubDistrictSuccess,
		errorHandler : handleSubDistError
	});

}

// data contains the returned value
function handleSubDistrict(data) {// handleSubDistrictSuccess(data) {
	// Assigns data to result id

	var fieldId = 'subDistrictListNew';
	var valueText = 'subdistrictCode';
	var nameText = 'subdistrictNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		name : "Select Subdistrict"
	} ];
	dwr.util.addOptions(fieldId, dataq, "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleSubDistError() {
	// Show a popup message
	alert("No data found!");
}

// //for village
function getVillageList(id) {

	lgdDwrVillageService.getVillageList(id, {
		callback : handleVillageSuccess,
		errorHandler : handleVillageError
	});

}
// data contains the returned value
function handleVillageSuccess(data) {
	// Assigns data to result id

	var fieldId = 'villageListMain';
	var valueText = 'villageCode';
	var nameText = 'villageNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		name : "Select Village"
	} ];
	dwr.util.addOptions(fieldId, dataq, '0', "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleVillageError() {
	// Show a popup message
	alert("No data found!");
}

// Village Details
/*
 * function getModifyVillageValue(id) { alert("id--"+id);
 * lgdDwrVillageService.getVillageDetailsModify(id, { callback :
 * handleModifyVillageSuccess, errorHandler : handleModifyVillageError }); }
 * 
 * function handleModifyVillageSuccess(data) { // Assigns data to result id
 * 
 * var fieldId = 'frmModifyVillage'; dwr.util.setValue(fieldId,data); }
 * 
 * function handleModifyVillageError() { // Show a popup message alert("No data
 * found!"); }
 */

// @Author - Sarvapriya Malhotra
function toggledisplay(obj, val) {
	if (document.getElementById(obj).checked) {

		if (val == 'cvillage') {
			document.getElementById('cvillage').style.visibility = 'visible';
			document.getElementById('csurvey').style.visibility = 'visible';
		}
		if (val == 'culb') {
			document.getElementById('culb').style.visibility = 'visible';
		}
	} else {
		if (val == 'cvillage') {
			document.getElementById('cvillage').style.visibility = 'hidden';
			document.getElementById('csurvey').style.visibility = 'hidden';
		}
		if (val == 'culb') {
			document.getElementById('culb').style.visibility = 'hidden';
		}
	}
}

/**
 * list1 - list from which to add values from as string i.e id name list2 - list
 * to which to add values from as string i.e id name val - value to append at
 * the end Note: doesn't matter what is it if doAddVal=false doAddVal - can be
 * either true or false...If True val is appended at the end if false val is not
 * appeneded
 */
function addItem(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++)
			if (document.getElementById(list2).options[j].selected == true)
				if (doAddVal) {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + val + ">" + document.getElementById(list2)[j].text + " ("
									+ val + ")</option>");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");
				}
		removeSelectedValue(list2);
	}
}
function removeAllState(tmplist1, tmplist2, doRemoveVal, tmplist3, tmplist4) {
	var list1 = document.getElementById(tmplist1);
	var list3 = document.getElementById(tmplist3);
	var list4 = document.getElementById(tmplist4);
	for (i = 0; i < list1.options.length; i++)
		list1.options[i].selected = true;
	for ( var i = 0; i < list1.options.length; i++) {

		if (doRemoveVal)
			$('#' + tmplist2).append(
					"<option value=" + document.getElementById(tmplist1)[i].value.substr(0, document.getElementById(tmplist1)[i].value.length - 4)
							+ " >" + document.getElementById(tmplist1)[i].text.substr(0, document.getElementById(tmplist1)[i].text.length - 6)
							+ "</option>");
		else
			$('#' + tmplist2).append(
					"<option value=" + document.getElementById(tmplist1)[document.getElementById(tmplist1).selectedIndex].text + " >"
							+ document.getElementById(tmplist1)[i].text + "</option>");

	}
	removeSelectedValue(tmplist1);
	for ( var j = 0; j < list3.options.length; j++)
		list3.options[j].selected = true;
	for ( var j = 0; j < list4.options.length; j++)
		list4.options[j].selected = true;
	removeSelectedValue(tmplist3);
	removeSelectedValue(tmplist4);
}

function removeAllDist(tmplist1, tmplist2, doRemoveVal) {
	var list1 = document.getElementById(tmplist1);
	var list2 = document.getElementById(tmplist2);
	for (i = 0; i < list1.options.length; i++)
		list1.options[i].selected = true;
	for ( var i = 0; i < list1.options.length; i++) {

		if (doRemoveVal)
			$('#' + tmplist2).append(
					"<option value=" + document.getElementById(tmplist1)[i].value.substr(0, document.getElementById(tmplist1)[i].value.length - 4)
							+ " >" + document.getElementById(tmplist1)[i].text.substr(0, document.getElementById(tmplist1)[i].text.length - 6)
							+ "</option>");
		else
			$('#' + tmplist2).append(
					"<option value=" + document.getElementById(tmplist1)[document.getElementById(tmplist1).selectedIndex].text + " >"
							+ document.getElementById(tmplist1)[i].text + "</option>");

	}
	removeSelectedValue(tmplist1);
}

function removeOneItem(list1, list2, doRemoveVal) {
	// alert('called doRemoveVal-->' + doRemoveVal);
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				// alert('selected--> true');
				if (doRemoveVal) {
					// alert('1.');
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 4) + " >"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					// alert('2.');
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
				}
			}
		}
		removeSelectedValue(list1);
	}
}

// /////////////////////////////////
/*
 * function addItem(list1,list2,val,doAddVal) {
 * if(document.getElementById(list2).selectedIndex>=0) { if (doAddVal) {
 * alert(document.getElementById(list2)[document.getElementById(list2).selectedIndex].value);
 * $('#' + list1).append("<option value=" +
 * document.getElementById(list2)[document.getElementById(list2).selectedIndex].value +
 * val + ">" +
 * document.getElementById(list2)[document.getElementById(list2).selectedIndex].innerText + " (" +
 * val + ")</option>"); } else $('#' + list1).append("<option value=" +
 * document.getElementById(list2)[document.getElementById(list2).selectedIndex].value + " >" +
 * document.getElementById(list2)[document.getElementById(list2).selectedIndex].innerText + "</option>");
 * removeSelectedValue(list2); } }
 */

function addsubdistrict(list1, list2, val, doAddVal) {

	if (document.getElementById(list2).selectedIndex >= 0) {
		if (doAddVal)
			$('#' + list1).append(
					"<option value=" + document.getElementById(list2)[document.getElementById(list2).selectedIndex].value + val + ">"
							+ document.getElementById(list2)[document.getElementById(list2).selectedIndex].innerText + " (" + val + ")</option>");
		else
			$('#' + list1).append(
					"<option value=" + document.getElementById(list2)[document.getElementById(list2).selectedIndex].value + " >"
							+ document.getElementById(list2)[document.getElementById(list2).selectedIndex].innerText + "</option>");
		removeSelectedValue(list2);
	}
}

/*
 * function removeAll(list1,list2,doRemoveVal) { for (var
 * i=document.getElementById(list1).length-1;i>=0;i--) {
 * document.getElementById(list1).selectedIndex=i;
 * 
 * if (doRemoveVal) $('#' + list2).append("<option value=" +
 * document.getElementById(list1).value.substr(0,document.getElementById(list1).value.length-4) + " >" +
 * document.getElementById(list1).value.substr(0,document.getElementById(list1).value.length-4) + "</option>");
 * else $('#' + list2).append("<option value=" +
 * document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText + " >" +
 * document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText + "</option>");
 * removeSelectedValue(list1); } }
 */

function getSurverNumbers(list1) {
	if (document.getElementById(list1).value.substr(document.getElementById(list1).value.length - 4) == "FULL") {
		alert("You Can Only Select Survey Numbers Of \"(Part)\" Villages");
	} else if (document.getElementById(list1).value.substr(document.getElementById(list1).value.length - 4) == "PART") {
		// TODO: CALL DWR HERE
	}
}

/*
 * function removeItem(list1,list2,doRemoveVal) {
 * if(document.getElementById(list1).selectedIndex>=0) { if (doRemoveVal) $('#' +
 * list2).append("<option value=" +
 * document.getElementById(list1).value.substr(0,document.getElementById(list1).value.length-4) + " >" +
 * document.getElementById(list1).value.substr(0,document.getElementById(list1).value.length-4) + "</option>");
 * else $('#' + list2).append("<option value=" +
 * document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText + " >" +
 * document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText + "</option>");
 * removeSelectedValue(list1); } }
 */

function removeSelectedValue(val) {
	var elSel = document.getElementById(val);
	var i;
	for (i = elSel.length - 1; i >= 0; i--) {
		if (elSel.options[i].selected) {
			elSel.remove(i);
		}
	}
}
// for create village validation by vanisha

// to display help text
function show_msg(Field_msg) {
	var hint = '#' + Field_msg + "_msg";
	var error = '#' + Field_msg + "_error";
	$("#" + Field_msg).removeClass("error_fld");
	$("#" + Field_msg).removeClass("error_msg");
	$(hint).show();
	$(error).hide();

}
// OfficialAddress1
function officialAddress() {

	if (document.getElementById("OfficialAddress").value == "") {
		document.getElementById("OfficialAddress_error").innerHTML = "Village Name in English is Required";
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
// OfficialAddress1
function nomenInEnglish() {

	if (document.getElementById("NomenInEnglish").value == "") {
		document.getElementById("NomenInEnglish_error").innerHTML = "Nomenclature in English is Required";
		$("#NomenInEnglish_error").show();
		$("#NomenInEnglish_msg").hide();
		$("#NomenInEnglish").addClass("error_fld");
		$("#NomenInEnglish").addClass("error_msg");
		return false;

	} else {
		$("#NomenInEnglish_msg").hide();
		return true;

	}
}

// /modify village option by vanisha
function toggledisplay2(obj, val) {
	if (document.getElementById(obj).checked) {

		if (val == 'correctionvillage') {
			document.getElementById('correctionvillage').style.visibility = 'visible';

		}
		if (val == 'changevillage') {
			document.getElementById('changevillage').style.visibility = 'visible';
		}

	} else {
		if (val == 'changevillage') {
			document.getElementById('correctionvillage').style.visibility = 'hidden';

		}
		if (val == 'correctionvillage') {
			document.getElementById('changevillage').style.visibility = 'hidden';
		}

	}
}

function addsurveys() {
	document.getElementById("addsurveys").innerHTML += "<div width='100%'><input type='text' name='survey_number'><input type='button' value='Remove' onclick='this.parentNode.parentNode.removeChild(this.parentNode)'></div>";
}

// modify village option by vanisha
function toggledisplay2(obj, val) {

	if (document.getElementById(obj).checked) {

		if (val == 'correctionvillage') {
			document.getElementById('correctionvillage').style.visibility = 'visible';
			document.getElementById('changevillage').style.visibility = 'hidden';

		}
		if (val == 'changevillage') {
			document.getElementById('changevillage').style.visibility = 'visible';
			document.getElementById('correctionvillage').style.visibility = 'hidden';
		}

	}
}
/*
 * function selectsubdistrict(paneName) {
 * 
 * for (var i=0;i<document.getElementById(paneName).length;i++) {
 * document.getElementById(paneName).options[i].selected=true;
 * i==0?doRemove=true:doRemove=false;
 * selectall(document.getElementById(paneName).options[i].value,document.getElementById(paneName).options[i].innerText); } }
 */

function selectsubdieverything()

{
	// alert("kamlesh");
	var selObj = document.getElementById('ddDestinctDistrict');

	var subDistrictCode = "";
	for (i = 0; i < selObj.options.length; i++) {
		// alert(selObj.options.length);
		selObj.options[i].selected = true;
		subDistrictCode += selObj.options[i].value + ",";
		// alert("i"+i);
	}

	getCoveredLandForSubDistRegion(subDistrictCode);

}

function getCoveredLandForSubDistRegion(id) {

	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		lgdDwrDistrictService.getPartSubdistrictListbyDistrict(id, {
			callback : handleSubDistrict1, // handleSubDistrictSuccess,
			errorHandler : handleSubDistError1
		});
	}
}

// data contains the returned value
function handleSubDistrict1(data) {// handleSubDistrictSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddSubdistrictforsubdistrict';
	var valueText = 'subdistrictCode';
	var nameText = 'subdistrictNameEnglish';
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleSubDistError1() {
	// Show a popup message
	alert("No data found!");
}
/*
 * function selectVillageList(paneName) { for (var i=0;i<document.getElementById(paneName).length;i++) {
 * document.getElementById(paneName).options[i].selected=true;
 * i==0?doRemove=true:doRemove=false;
 * selectallforvillage(document.getElementById(paneName).options[i].value,document.getElementById(paneName).options[i].innerText); } }
 */

function selectVillageList(id, name) {
	if (id.match('PART') == 'PART') {
		var selObj2 = document.getElementById('subDistrictListNew');
		// var selObj2=document.getElementById('surveyListNew');
		// var selObj=document.getElementById('subDistrictListNew');
		/*
		 * var subVillage=""; for (i = 0; i < selObj2.options.length; i++) {
		 * selObj2.options[i].selected=true; subVillage
		 * +=selObj2.options[i].value+","; }
		 */
		lgdDwrDistrictService.getVillageList(id, {
			callback : handleVillageSuccess,
			errorHandler : handleVillageError
		});

	} else {
		dwr.util.removeAllOptions('villageList');
		alert("Kindly Select the Part Sub District From the List");
	}
} // data contains the returned value
function handleVillageSuccess(data) {
	// Assigns data to result id

	var fieldId = 'villageList';
	var valueText = 'villageCode';
	var nameText = 'villageNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	// var dataq = [ {name:"Select Village"} ];
	// dwr.util.addOptions(fieldId, dataq,'0', "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleVillageError() {
	// Show a popup message
	alert("No data found!");

}

/*
 * function selectallforvillage() { alert(dsdsdsdd); var
 * selObj=document.getElementById('subDistrictListNew'); //var
 * selObj2=document.getElementById('surveyListNew'); //var
 * selObj=document.getElementById('subDistrictListNew'); var subVillage=""; for
 * (i = 0; i < selObj.options.length; i++) { selObj.options[i].selected=true;
 * subVillage +=selObj.options[i].value+","; } getVillageList(subVillage); }
 * 
 * function getVillageList(id) {
 * 
 * lgdDwrDistrictService.getVillageList(id, { callback : handleVillageSuccess,
 * errorHandler : handleVillageError }); }
 * 
 * //data contains the returned value function handleVillageSuccess(data) { //
 * Assigns data to result id
 * 
 * var fieldId = 'villageList'; var valueText = 'villageCode'; var nameText =
 * 'villageNameEnglish'; dwr.util.removeAllOptions(fieldId); //var dataq = [
 * {name:"Select Village"} ]; //dwr.util.addOptions(fieldId, dataq,'0', "name");
 * dwr.util.addOptions(fieldId, data, valueText, nameText); }
 * 
 * function handleVillageError() { // Show a popup message alert("No data
 * found!"); }
 */

function reorganizeYes() {
	if (document.getElementById('Yes').checked = true) {
		document.getElementById('Submit').style.visibility = 'hidden';
		document.getElementById('Proceed').style.visibility = 'visible';
	}
}
function reorganizeNo() {
	if (document.getElementById('No').checked = true) {
		document.getElementById('Submit').style.visibility = 'visible';
		document.getElementById('Proceed').style.visibility = 'hidden';
	}
}

function reorganizingYes() {
	if (document.getElementById('Yes').checked = true) {
		document.getElementById('newVillage').disabled = false;
		document.getElementById('reorganized').value = "";
		document.getElementById('reorganized').value = "true";
		document.getElementById('reorgOption').style.visibility = 'visible';
		document.getElementById('Yes').disabled = false;
	}
}
function reorganizingNo() {
	if (document.getElementById('No').checked = true) {
		document.getElementById('reorganized').value = "";
		document.getElementById('reorgOption').style.visibility = 'hidden';
		document.getElementById('Yes').disabled = false;
	}
	document.getElementById('Reorganize').style.visibility = 'hidden';
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
	var valueText = 'statePK.stateCode';
	var nameText = 'stateNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleStateError() {
	// Show a popup message
	alert("No data found!");
}
// For sub district

function getSubDistrictList(id) {

	lgdDwrDistrictService.getSubDistrictList(id, {
		callback : handleSubDistrictSuccess,
		errorHandler : handleSubDistrictError
	});

}

function handleSubDistrictSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddSourceDistrict';
	var valueText = 'districtPK.districtCode';
	var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleSubDistrictError() {
	// Show a popup message
	alert("No data found!");
}

// //////////////////////////////////////
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
// For whole
/*
 * function addItem(list1,list2,val,doAddVal) {
 * if(document.getElementById(list2).selectedIndex>=0) { if (doAddVal) $('#' +
 * list1).append("<option value=" +
 * document.getElementById(list2)[document.getElementById(list2).selectedIndex].value +
 * val + ">" +
 * document.getElementById(list2)[document.getElementById(list2).selectedIndex].innerText + " (" +
 * val + ")</option>"); else $('#' + list1).append("<option value=" +
 * document.getElementById(list2)[document.getElementById(list2).selectedIndex].value + " >" +
 * document.getElementById(list2)[document.getElementById(list2).selectedIndex].innerText + "</option>");
 * removeSelectedValue(list2); } }
 */
function listbox_moveacross(sourceID, destID) {
	var src = document.getElementById(sourceID);
	var dest = document.getElementById(destID);
	var check;
	for ( var count = 0; count < src.options.length; count++) {

		if (src.options[count].selected == true) {
			var option = src.options[count];
			check = true;
			var newOption = document.createElement("option");
			// newOption.value = option.value+"(Full)";
			// newOption.text = option.text+"(Full)";
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

// //////////////////////////////

// dwr.engine.setActiveReverseAjax(true);

// State and district list
function getList(id) {

	getDistrictList(id);
}

function getVillageList(id) {

	getDistrictList(id);
}

// For part selection
function part(sourceID, destID) {
	var src = document.getElementById(sourceID);
	var dest = document.getElementById(destID);
	var check;
	for ( var count = 0; count < src.options.length; count++) {

		if (src.options[count].selected == true) {
			var option = src.options[count];
			check = true;
			var newOption = document.createElement("option");
			// newOption.value = option.value+"(Full)";
			// newOption.text = option.text+"(Full)";
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

function reorgOption() {
	document.getElementById('reorganized').value = "true";
	if (document.getElementById('isModifyVillage').checked == true) {
		document.getElementById('newVillage').disabled = true;
	}
	// document.getElementById('Submit').style.visibility = 'hidden';
	// document.getElementById('AddAnotherSD').style.visibility = 'hidden';
	document.getElementById('Reorganize').style.visibility = 'visible';
}

function reorg() {
	document.getElementById('reorganized').value = "true";
	if (document.getElementById('isModifySubdistrict').checked == true) {
		document.getElementById('newSubdistrict').disabled = true;
	}
	// document.getElementById('Submit').style.visibility = 'hidden';
	// document.getElementById('AddAnotherSD').style.visibility = 'hidden';
	document.getElementById('Reorganize').style.visibility = 'visible';
}

function reorganizeYes() {
	if (document.getElementById('Yes').checked = true) {

		document.getElementById('newVillage').disabled = false;
		document.getElementById('reorganized').value = "";
		document.getElementById('reorganized').value = "true";
		document.getElementById('reorgOption').style.visibility = 'visible';
		// document.getElementById('Submit').style.visibility = 'hidden';
		// document.getElementById('AddAnotherSD').style.visibility =
		// 'hidden';
		document.getElementById('Yes').disabled = true;
	}
}
function reorganizeNo() {
	if (document.getElementById('No').checked = true) {
		document.getElementById('reorganized').value = "";
		document.getElementById('reorgOption').style.visibility = 'hidden';
		// document.getElementById('Submit').style.visibility = 'visible';
		// document.getElementById('AddAnotherSD').style.visibility =
		// 'visible';
		document.getElementById('Reorganize').style.visibility = 'hidden';
	}
}

function formSubmitfinal() {
	var selObj1 = document.getElementById('ddDestDistrict');
	var selObj = document.getElementById('subDistrictListNew');
	var selObj2 = document.getElementById('villageListNew');

	if (document.getElementById('isModifySubdistrict').checked == true) {
		var count = 0;
		for ( var i = 0; i < selObj.options.length; i++) {
			if (selObj.options[i].selected) {
				count++;

			}
		}
		if (count == 0 || count > 1) {
			alert("To Modify the Subdistrict Kindly Select Only One From the List. !");
		} else {
			document.getElementById('modifySubDistrict').value = selObj.options[count].value;
			for ( var i = 0; i < selObj2.options.length; i++) {
				selObj2.options[i].selected = true;
			}
			for ( var i = 0; i < selObj1.options.length; i++) {
				selObj1.options[i].selected = true;
			}
			if (!validateform()) {
				return false;
			} else {
				document.forms['form1'].submit();
			}
		}
	} else {
		for ( var i = 0; i < selObj1.options.length; i++) {
			selObj1.options[i].selected = true;
		}
		for ( var i = 0; i < selObj2.options.length; i++) {
			selObj2.options[i].selected = true;
		}
		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
		}
		if (!validateform()) {
			return false;
		} else {
			document.forms['form1'].submit();
		}
	}
}

function Districtsbumit() {

	var selObj1 = document.getElementById('ddDestDistrict');
	var selObj = document.getElementById('subDistrictListNew');
	var selObj2 = document.getElementById('villageListNew');
	var selObj3 = document.getElementById('ddDestinctDistrict');

	if (document.getElementById('isModifyDistrict').checked == true) {
		var count = 0;
		for ( var i = 0; i < selObj3.options.length; i++) {
			if (selObj3.options[i].selected) {
				count++;
			}
		}
		if (count == 0 || count > 1) {
			alert("To Modify the district Kindly Select Only One From the List. !");
		} else {
			document.getElementById('modifyDistrict').value = selObj3.options[count].value;
			for ( var i = 0; i < selObj2.options.length; i++) {
				selObj2.options[i].selected = true;
			}
			for ( var i = 0; i < selObj.options.length; i++) {
				selObj.options[i].selected = true;
			}
			if (!validateform()) {
				return false;
			} else {
				document.forms['form1'].submit();
			}
		}
	} else {
		for ( var i = 0; i < selObj1.options.length; i++) {
			selObj1.options[i].selected = true;
		}
		for ( var i = 0; i < selObj2.options.length; i++) {
			selObj2.options[i].selected = true;
		}
		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
		}
		for ( var i = 0; i < selObj3.options.length; i++) {
			selObj3.options[i].selected = true;
		}
		if (!validateform()) {
			return false;
		} else {
			document.forms['form1'].submit();
		}
	}
}

function Shiftsbumit() {
	var selObj1 = document.getElementById('ddDestDistrict');
	var selObj = document.getElementById('subDistrictListNew');
	var selObj2 = document.getElementById('villageListNew');
	var selObj3 = document.getElementById('ddDestinctDistrict');

	if (document.getElementById('isModifyShiftdistrict').checked == true) {
		var count = 0;
		for ( var i = 0; i < selObj3.options.length; i++) {
			if (selObj3.options[i].selected) {
				count++;
			}
		}
		if (count == 0 || count > 1) {
			alert("To Modify the district Kindly Select Only One From the List. !");
		} else {
			document.getElementById('modifyDistrict').value = selObj3.options[count].value;
			for ( var i = 0; i < selObj2.options.length; i++) {
				selObj2.options[i].selected = true;
			}
			for ( var i = 0; i < selObj.options.length; i++) {
				selObj.options[i].selected = true;
			}
			if (!validateform()) {
				return false;
			} else {
				document.forms['form1'].submit();
			}
		}
	} else {
		for ( var i = 0; i < selObj1.options.length; i++) {
			selObj1.options[i].selected = true;
		}
		for ( var i = 0; i < selObj2.options.length; i++) {
			selObj2.options[i].selected = true;
		}

		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
		}
		for ( var i = 0; i < selObj3.options.length; i++) {
			selObj3.options[i].selected = true;
		}
		if (!validateform()) {
			return false;
		} else {
			document.forms['form1'].submit();
		}
	}
}
function formStateSubmit() {
	var selObj = document.getElementById('subDistrictListNew');
	var selObj1 = document.getElementById('ddDestDistrict');
	var selObj2 = document.getElementById('villageListNew');
	var selObj3 = document.getElementById('ddDestinctDistrict');

	if (document.getElementById('isModifyVillage').checked == true) {
		var count = 0;
		for ( var i = 0; i < selObj2.options.length; i++) {
			if (selObj2.options[i].selected) {
				count++;
			}
		}
		if (count == 0 || count > 1) {
			alert("To Modify the Village Kindly Select Only One From the List. !");
		} else {
			document.getElementById('modifyVillage').value = selObj2.options[count].value;
			for ( var i = 0; i < selObj.options.length; i++) {
				selObj.options[i].selected = true;
			}
			for ( var i = 0; i < selObj1.options.length; i++) {
				selObj1.options[i].selected = true;
			}
			if (!validateform()) {
				return false;
			} else {
				document.forms['form1'].submit();
			}
		}
	} else {
		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
		}

		for ( var i = 0; i < selObj1.options.length; i++) {
			selObj1.options[i].selected = true;
		}
		for ( var i = 0; i < selObj2.options.length; i++) {
			selObj2.options[i].selected = true;
		}
		for ( var i = 0; i < selObj3.options.length; i++) {
			selObj3.options[i].selected = true;
		}
		if (!validateform()) {
			return false;
		} else {
			document.forms['form1'].submit();
		}

	}
}

function formSubmitstate() {

	var selObj1 = document.getElementById('ddDestDistrict');
	var selObj = document.getElementById('subDistrictListNew');
	var selObj2 = document.getElementById('villageListNew');

	if (document.getElementById('isModifyVillage').checked == true) {
		var count = 0;
		for ( var i = 0; i < selObj2.options.length; i++) {
			if (selObj2.options[i].selected) {
				count++;
			}
		}
		if (count == 0 || count > 1) {
			alert("To Modify the Village Kindly Select Only One From the List. !");
		} else {
			document.getElementById('modifyVillage').value = selObj2.options[count].value;
			for ( var i = 0; i < selObj.options.length; i++) {
				selObj.options[i].selected = true;
			}
			for ( var i = 0; i < selObj1.options.length; i++) {
				selObj1.options[i].selected = true;
			}
			if (!validateform()) {
				return false;
			} else {
				document.forms['form1'].submit();
			}
		}
	} else {
		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
		}

		for ( var i = 0; i < selObj1.options.length; i++) {
			selObj1.options[i].selected = true;
		}
		for ( var i = 0; i < selObj2.options.length; i++) {
			selObj2.options[i].selected = true;
		}
		if (!validateform()) {
			return false;
		} else {
			document.forms['form1'].submit();
		}

	}
}

function checkforModifyforstate() {

	if (document.getElementById('isModifyVillage').checked == true) {

		formSubmit();
	}

	else if (document.getElementById('newVillage').checked == true) {

		formSubmitstate();
	} else if (document.getElementById('isModifySubdistrict').checked == true) {

		formSubmitfinal();
	} else if (document.getElementById('newSubdistrict').checked == true) {

		formSubmitfinal();
	} else if (document.getElementById('newDistrict').checked == true) {
		Districtsbumit();
	}

	else if (document.getElementById('isModifyDistrict').checked == true) {

		Districtsbumit();
	}

	else if (document.getElementById('ShiftDistrict').checked == true) {
		alert("Shiftsbumit");
		document.forms['form1'].submit();
	} else if (document.getElementById('ShiftSubDistrict').checked == true) {
		alert("ShiftSubDistrict");
		document.forms['form1'].submit();
	}

	else if (document.getElementById('ShiftVillage').checked == true) {
		alert("ShiftVillage");
		document.forms['form1'].submit();
	} else if (document.getElementById('invalidateVillage').checked == true) {
		alert("invalidateVillage");
		document.forms['form1'].submit();
	}
}

function validateform() {
	var ddDistrict = document.getElementById('districtNameInEn').value;
	if (ddDistrict == "") {
		alert("Please Enter Name Of the State");
		document.getElementById('districtNameInEn').focus();
		return false;
	}
	return true;
}

function formSubmitAddStatePreview() {
	document.getElementById('addAnotherSD').value = "";
	document.getElementById('addAnotherSD').value = "true";
	document.forms['state'].submit();
}

function findDuplicate(objStateName) {
	if (objStateName.value != null && objStateName.value != "") {
		var stateName = objStateName.value;

		var flag = false;
		lgdDwrStateService.findDuplicate(stateName, {
			callback : handleDuplicateState,
			errorHandler : handleStateError
		});

	}
}

function handleDuplicateState(data) {
	if (data == "0") {
		document.getElementById('duplicate').value = 0;
	} else {
		document.getElementById('duplicate').value = 1;
		$("#dialog-duplicate-state").dialog({
			resizable : false,
			height : 140,
			modal : true,
			buttons : {
				Ok : function() {
					$(this).dialog("close");
				}
			}
		});
	}

}
function handleStateError() {
	alert("No data found for this district name");
}