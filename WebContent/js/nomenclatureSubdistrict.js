function reorganizeYes() {
	if (document.getElementById('Yes').checked = true) {
		document.getElementById('coveredLandRegionByULB').value = "";
		document.getElementById('Submit').style.visibility = 'hidden';
		document.getElementById('Proceed').style.visibility = 'visible';
	}
}
function reorganizeNo() {
	if (document.getElementById('No').checked = true) {
		document.getElementById('coveredLandRegionByULB').value = "";
		document.getElementById('coveredLandRegionByULB').value = "Final";
		document.getElementById('Submit').style.visibility = 'visible';
		document.getElementById('Proceed').style.visibility = 'hidden';
	}
}
// DWR Dropdownlist Population
/** *********************** */
// //for ULB covered landregion
/* ulb coverage */
function getCoveredLandRegionByULB(id) {
	// alert("id----------"+id);
	lgdDwrCoveredLandRegionByULBService.getCoveredLandRegionByULBList(id, {
		callback : handleCoveredLandRegionByULBSuccess,
		errorHandler : handleCoveredLandRegionByULBError
	});
}

// data contains the returned value
function handleCoveredLandRegionByULBSuccess(data) {
	// Assigns data to result id
	// alert("data-----"+data);
	var fieldId = 'ulbListMain';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
	// dwr.util.addOptions("ulbListMain", data);
	// dwr.util.addOptions("ulbListMain", [{ name:'Africa', id:'AF' }, {
	// name:'America', id:'AM' }, { name:'Asia', id:'AS' }, {
	// name:'Australasia', id:'AU' }, { name:'Europe', id:'EU' }] ,id ,name );
}

function handleCoveredLandRegionByULBError() {
	// Show a popup message
	alert("No data found!");
}
// **********ULB coverage END******/

function getSubDistrictandULBList(id) {
	getSubDistrictList(id);
	getULBList(id);

}
// ************ulb list start*******/
function getULBList(id) {
	// alert(id);
	lgdDwrlocalBodyService.getULBList(id, {
		callback : handleULBSuccess,
		errorHandler : handleULBError
	});
}

// data contains the returned value
function handleULBSuccess(data) {
	// alert(hi);
	// Assigns data to result id localbodyCode,localBodyNameEnglish
	var fieldId = 'ddULBList';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	// alert(valueText);
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		name : "Select ULB"
	} ];
	dwr.util.addOptions(fieldId, dataq, "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleULBError() {
	// Show a popup message
	alert("No data found!");
}

// **************ulb list fetching end***********/

// for first subdistrict
function getSubDistrictList(id) {

	lgdDwrSubDistrictService.getSubDistrictList(id, {
		callback : handleSubDistrictSuccess,
		errorHandler : handleSubDistrictError
	});

}

// data contains the returned value
function handleSubDistrictSuccess(data) {
	// Assigns data to result id
	// alert(data);
	var fieldId = 'ddSubdistrict';
	var valueText = 'subdistrictCode';
	var nameText = 'subdistrictNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		name : "Select Subdistrict"
	} ];
	dwr.util.addOptions(fieldId, dataq, "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleSubDistrictError() {
	// Show a popup message
	alert("No data found!");
}

// for second subdistrict
/*
 * function getSubIDistrictList(id) {
 * 
 * lgdDwrSubDistrictService.getSubDistrictList(id, { callback :
 * handleSubIDistrictSuccess, errorHandler : handleSubIDistrictError }); }
 * 
 * //data contains the returned value function handleSubIDistrictSuccess(data) { //
 * Assigns data to result id var fieldId = 'ddSubIdistrict'; var valueText =
 * 'subdistrictCode'; var nameText = 'subdistrictNameEnglish';
 * dwr.util.removeAllOptions(fieldId); dwr.util.addOptions(fieldId, data,
 * valueText, nameText); }
 * 
 * function handleSubIDistrictError() { // Show a popup message alert("No data
 * found!"); }
 */

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
	// alert(data);
	var fieldId = 'villageListMain';

	var valueText = 'villageCode';
	var nameText = 'villageNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	// var dataq = [ {name:"Select Village"} ];
	// dwr.util.addOptions(fieldId, dataq,'0', "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);
	// dwr.util.addOptions(fieldId, data);
}

function handleVillageError() {
	// Show a popup message
	alert("No data found!");
}

// Village Details
/*
 * function getModifyVillageValue(id) {
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
// @Modified BY: Chandan Soni
function toggledisplay(obj, val) {
	if (document.getElementById(obj).checked) {

		if (val == 'cvillage') {
			document.getElementById('cvillage').style.visibility = 'visible';
			document.getElementById('csurvey').style.visibility = 'visible';
			document.getElementById('cvillage').style.display = 'block';
			document.getElementById('csurvey').style.display = 'block';
		}
		if (val == 'culb') {
			document.getElementById('culb').style.visibility = 'visible';
			document.getElementById('culb').style.display = 'block';
		}
	} else {
		if (val == 'cvillage') {
			document.getElementById('cvillage').style.visibility = 'hidden';
			document.getElementById('csurvey').style.visibility = 'hidden';
			document.getElementById('cvillage').style.display = 'none';
			document.getElementById('csurvey').style.display = 'none';
		}
		if (val == 'culb') {
			document.getElementById('culb').style.visibility = 'hidden';
			document.getElementById('culb').style.display = 'none';
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

function removeAll(list1, list2, doRemoveVal) {
	for ( var i = document.getElementById(list1).length - 1; i >= 0; i--) {
		document.getElementById(list1).selectedIndex = i;

		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 4)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText.length - 6) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText + "</option>");
		removeSelectedValue(list1);
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

		var sp = document.getElementById(list1).value.substr(document.getElementById(list1).value.length
				- document.getElementById(list1).value.length, document.getElementById(list1).value.length - 4);

		getSurveyNobyVillage(sp);

	}
}

function getSurveyNobyVillage(id) {

	lgdDwrSurveyService.getSurveyList(id, {
		callback : handleVillageSurveySuccess,
		errorHandler : handleVillageSurveyError
	});

}

function handleVillageSurveySuccess(data) {
	// Assigns data to result id

	var fieldId = 'surveyListMain';
	var valueText = 'surveyCode';
	var nameText = 'surveyNumber';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleVillageSurveyError() {
	// Show a popup message
	alert("No Survey no found!");
}

function removeItem(list1, list2, doRemoveVal) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 4)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText.length - 6) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText + "</option>");
		removeSelectedValue(list1);
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
	if (document.getElementById('divData') != null)
		document.getElementById('divData').style.visibility = 'hidden';
	if (document.getElementById(obj).checked) {

		if (val == 'correctionvillage') {
			document.getElementById('correctionvillage').style.visibility = 'visible';
			document.getElementById('correctionvillage').style.display = 'block';
			document.getElementById('changevillage').style.visibility = 'hidden';
			document.getElementById('changevillage').style.display = 'none';

		} else if (val == 'changevillage') {
			document.getElementById('changevillage').style.visibility = 'visible';
			document.getElementById('changevillage').style.display = 'block';
			document.getElementById('correctionvillage').style.visibility = 'hidden';
			document.getElementById('correctionvillage').style.display = 'none';
		}

	}
}

function toggledisplay3(obj, val) {
	if (document.getElementById('divData') != null)
		document.getElementById('divData').style.visibility = 'hidden';
	if (document.getElementById(obj).checked) {

		if (val == 'showbytext') {
			document.getElementById('showbytext').style.visibility = 'visible';
			document.getElementById('showbytext').style.display = 'block';
			document.getElementById('showbydropdown').style.visibility = 'hidden';
			document.getElementById('showbydropdown').style.display = 'none';

		} else if (val == 'showbydropdown') {
			document.getElementById('showbydropdown').style.visibility = 'visible';
			document.getElementById('showbydropdown').style.display = 'block';
			document.getElementById('showbytext').style.visibility = 'hidden';
			document.getElementById('showbytext').style.display = 'none';
		}

	}
	/*
	 * else { if (val=='changevillage') {
	 * document.getElementById('correctionvillage').style.visibility='hidden';
	 * document.getElementById('correctionvillage').style.display='none'; } if
	 * (val=='correctionvillage') {
	 * document.getElementById('changevillage').style.visibility='hidden';
	 * document.getElementById('changevillage').style.display='none'; } }
	 */
}

function addgisnodes() {
	document.getElementById("addgisnodes").innerHTML += "<div width='100%'>"
			+ document.getElementById('lbllatitude').innerText
			+ "<input type='text' class='frmfield' name='latitude'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+ document.getElementById('lbllongitude').innerText
			+ "<input type='text' class='frmfield' name='longitude'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' value='Remove' onclick='this.parentNode.parentNode.removeChild(this.parentNode)'></div>";
}

/*
 * function addgisnodes() { document.getElementById("addgisnodes").innerHTML += "<div
 * width='100%'>" + document.getElementById('lbllatitude').innerText + "<input
 * type='text' class='frmfield' name='latitude'>" +
 * document.getElementById('lbllongitude').innerText + "<input type='text'
 * class='frmfield' name='longitude'><input type='button' value='Remove'
 * onclick='this.parentNode.parentNode.removeChild(this.parentNode)'></div>"; }
 */
function addsurveys() {
	document.getElementById("addsurveys").innerHTML += "<div width='100%'><input type='text'  class='frmfield' name='survey_number'><input type='button'  value='Remove' onclick='this.parentNode.parentNode.removeChild(this.parentNode)'></div>";
}

// Modify Village
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

function validateAndSubmit() {
	if (document.getElementById('NomenInEnglish').value == "") {
		alert('Please Enter Nomenclature in English');
		return false;
	}
	// alert(document.getElementById('btnSave'));
	document.getElementById('btnSave').disabled = true;
	document.forms['formmodifynomen'].submit();
}

function chekcalphanumeric(inputtxt) {
	var status = true;
	if (inputtxt.match(/\S/)) {
		var obj = document.getElementById('NomenInEnglish');
		var letterNumber = "";
		var retVal = false;
		inputtxt = inputtxt.replace(/\s+/g, '');
		letterNumber = /^[0-9a-zA-Z.-]+$/;
		if (inputtxt.match(letterNumber))
			retVal = true;
		if (retVal == false) {
			alert("Subdistrict Name contains invalid characters Use AlphaNumerics !");
			obj.value = "";
			status = false;
		}
	}
	return stautus;
}

function validatelocalCharachterforsubdistrict(value) {
	var status = true;
	var obj = "";
	obj = document.getElementById('nameinlocalLanguage');
	var retVal = true;
	retVal = validateSpecialCharacters(value);
	if (retVal == false) {
		alert("Subdistrict Name in Local Language contains invalid characters !");
		obj.value = "";
		status = false;
	}

	return stautus;
}
