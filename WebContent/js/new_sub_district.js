/*@Author - Ram Krishna Chauhan */

var i = 4;
var dynstart = 0;
var dynend = 0;
var l = 1;
var modify = "";

function onloadSelect() {
	document.getElementById('addAnotherSD').value = "";
	var mypath = window.location.href;
	if (mypath.match('modify') == 'modify') {
		document.getElementById('Yes').checked = true;
		document.getElementById('reorgOption').style.visibility = 'visible';
		document.getElementById('newVillage').disabled = true;
		document.getElementById('ddDistrict').disabled = true;
		document.getElementById('partSubdistrict').disabled = true;
		document.getElementById('Yes').disabled = true;
	}
	hideGISComponentOnLoad();
}
function validation(val) {
	var error = 0;
	var temp = "";
	if (document.getElementById('subDistrictListNew').options.length == 1
			&& (document.getElementById('subDistrictListNew').value.match('FULL')) == "FULL") {
		error = 1;
		alert("You Can't Make the New Sub District by Shifting Only One Full Subdistrict. Kindly Select One More Contributing Sub District.  !");
	}
	if (document.getElementById('subDistrictListNew').options.length == 0) {
		error = 1;
		alert("Kindly Select One or More Contributing Sub District.  !");
	}
	if (document.getElementById('subDistrictListNew').value.match('PART') == "PART") {
		if (document.getElementById('villageListNew').options.length == 0) {
			error = 1;
			alert("Kindly Select One or More Contributing Villages.  !");
		}
	}
	if (error == 0) {
		var selObj = document.getElementById('subDistrictListNew');
		for ( var i = 0; i < selObj.options.length; i++) {
			if ((selObj.options[i].value).substr(selObj.options[i].value.length - 4) == "PART") {
				if (document.getElementById("villageListNew").options.length == 0) {
					alert("Kindly Select at least one Village of part Subdistrict ");
					error = 1;

				}
				if (error == 0)
					if (document.getElementById("villageList").options.length == 0) {
						alert("You can't Select all Vilage from Part Villages ");
						error = 1;

					}
				break;
			}
		}
	}
	if (error == 0) {
		document.getElementById('ddDistrict').disabled = false;
		SetAddvaluestemp(val);
		document.getElementById("Submit").disabled = true;
		document.getElementById("Submit2").disabled = true;
		document.forms['newsubdistrictform'].submit();
	}
}

function contriOption() {
	var selObj = document.getElementById('subDistrictListNew');
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}
	if (document.getElementById('contriSD').checked == false) {
		var optionValue = null;
		for ( var i = 0; i < selObj.options.length; i++) {
			optionValue += selObj.options[i].value;
		}
		if (optionValue.match('PART') != "PART") {
			alert("To Rename Contributing Sub Districts, Kindly Select At Least One Part SubDistrict. !");
		}
		document.getElementById('Proceed').style.visibility = 'visible';
		document.getElementById('Reorganize').style.visibility = 'hidden';
	}
}

function reorgOption() {
	document.getElementById('reorganized').value = "true";
	if (document.getElementById('isModifyVillage').checked == true) {
		// document.getElementById('newVillage').disabled = true;
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
		// document.getElementById('AddAnotherSD').style.visibility = 'hidden';
		document.getElementById('Yes').disabled = true;
	}
}
function reorganizeNo() {
	if (document.getElementById('No').checked = true) {
		document.getElementById('reorganized').value = "";
		document.getElementById('reorgOption').style.visibility = 'hidden';
		// document.getElementById('Submit').style.visibility = 'visible';
		// document.getElementById('AddAnotherSD').style.visibility = 'visible';
		document.getElementById('Reorganize').style.visibility = 'hidden';
		document.getElementById('Yes').disabled = false;
	}
}
function formSubmit(val) {
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
			for ( var i = 0; i < selObj2.options.length; i++) {
				selObj2.options[i].selected = true;
			}
			document.getElementById('ddDistrict').disabled = false;
			document.forms['newsubdistrictform'].submit();
		}
	} else {
		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
		}
		for ( var i = 0; i < selObj2.options.length; i++) {
			selObj2.options[i].selected = true;
		}
		validation(val);
	}
}
function formSubmitAdd(val) {

	if (checkMandatoryFields()) {

		formSubmit(val);
	}
}

function checkMandatoryFields() {
	if (document.getElementById('ddDistrict').value < 1) {
		alert('Please Select Source District');
		return false;
	}
	if (document.getElementById('OfficialAddress').value == '') {
		alert('Please Give Name For New Subdistrict');
		return false;
	}
	return true;
}

function formSubmitAddPreview() {
	document.getElementById('addAnotherSD').value = "";
	document.getElementById('addAnotherSD').value = "true";
	document.forms['newsubdistrictform'].submit();
}
// DWR Dropdownlist Population

// for first subdistrict
function getSubDistrictList(id) {
	lgdDwrSubDistrictService.getSubDistrictList(id, {
		callback : handleSubDistrictSuccess,
		errorHandler : handleSubDistrictError
	});

}

// data contains the returned value
function handleSubDistrictSuccess(data) {
	dwr.util.removeAllOptions('subDistrictListNew');
	dwr.util.removeAllOptions('villageListNew');
	dwr.util.removeAllOptions('villageList');
	// Assigns data to result id
	var fieldId = 'ddSubdistrict';
	var valueText = 'subdistrictCode';
	var nameText = 'subdistrictNameEnglish';
	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleSubDistrictError() {
	// Show a popup message
	alert("No data found!");
}

// //for village
function getVillageList() {
	$("#villageListNew").empty();
	selObj = document.getElementById('subDistrictListNew');
	var temp = "";
	var villageId = "";
	var count = 0;
	for ( var i = 0; i < selObj.options.length; i++) {
		if ((selObj.options[i].value).substr(selObj.options[i].value.length - 4) == "PART") {
			temp = selObj.options[i].value;

			temp = temp.substr(0, temp.length - 4);
			villageId = villageId + temp + ",";
			count++;

		}
	}
	if (count == 0) {
		alert("Please Select part Subdistrict ");
		return false;
	}
	villageId = villageId.substring(0, villageId.length - 1);
	lgdDwrVillageService.getVillagebysubdisrictCodes(villageId, {
		callback : handleVillageSuccess,
		errorHandler : handleVillageError
	});

}
// data contains the returned value
function handleVillageSuccess(data) {
	// Assigns data to result id

	var fieldId = 'villageList';
	var valueText = 'villageCode';
	var nameText = 'villageNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
	if (data.length > 0) {
		removeEarlierAddedVillages();
	}
}

function handleVillageError() {
	// Show a popup message
	alert("No data found!");
}

// Village Details
/*
 * function getModifyVillageValue(id) {
 * 
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
/*
 * function addItem(list1,list2,val,doAddVal) {
 * if(document.getElementById(list2).selectedIndex>=0) { if (doAddVal) $('#' +
 * list1).append("<option value=" +
 * document.getElementById(list2)[document.getElementById(list2).selectedIndex].value+
 * val + ">" +
 * document.getElementById(list2)[document.getElementById(list2).selectedIndex].innerText + " (" +
 * val + ")</option>"); else $('#' + list1).append("<option value=" +
 * document.getElementById(list2)[document.getElementById(list2).selectedIndex].value + " >" +
 * document.getElementById(list2)[document.getElementById(list2).selectedIndex].innerText + "</option>");
 * removeSelectedValue(list2); } }
 */
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
function removeAllListItems(list1, list2, doRemoveVal) {
	$("#villageList").empty();
	$("#villageListNew").empty();
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
 * document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0,document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length-6) + "</option>");
 * else $('#' + list2).append("<option value=" +
 * document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + " >" +
 * document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + "</option>");
 * removeSelectedValue(list1); } }
 * 
 * function removeSelectedValue(val) { var elSel = document.getElementById(val);
 * var i; for (i = elSel.length - 1; i>=0; i--) { if (elSel.options[i].selected) {
 * elSel.remove(i); } } }
 */
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
/*
 * function officialAddress() {
 * 
 * if(document.getElementById("OfficialAddress").value == "" ) {
 * document.getElementById("OfficialAddress_error").innerHTML="Village Name in
 * English is Required"; $("#OfficialAddress_error").show();
 * $("#OfficialAddress_msg").hide();
 * $("#OfficialAddress").addClass("error_fld");
 * $("#OfficialAddress").addClass("error_msg"); return false; } else {
 * $("#OfficialAddress_msg").hide(); return true; } }
 */

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

function addgisnodes() {
	var tmptextLati = new Array();
	var tempchkLongi = new Array();

	if (dynstart == 0) {
		dynstart = i;
	}
	dynend = i;

	for ( var j = dynstart; j < dynend; j++) {
		if (document.getElementById("lati" + j)) {
			if (document.getElementById("lati" + j) != null && document.getElementById("longi" + j) != null)
				tmptextLati[j] = document.getElementById("lati" + j).value;
			tempchkLongi[j] = document.getElementById("longi" + j).value;
		}
	}

	document.getElementById("addgisnodes").innerHTML += "<div id=div"
			+ i
			+ "><td align='left'>"
			+ document.getElementById('lbllatitude').innerHTML
			+ "</td><td width='75'>&nbsp;&nbsp;<input type='text' name='lati' id='lati"
			+ i
			+ "' class='frmfield'></td><td width='145'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td width='150'>"
			+ document.getElementById('lbllongitude').innerHTML + "</td><td width='75'>&nbsp;&nbsp;<input type='text' name='longi' id='longi" + i
			+ "' class='frmfield'></td><td><input type='button' value='Remove' onclick='div" + i + ".parentNode.removeChild(div" + i
			+ ")'/></td></div>";

	for ( var j = dynstart; j < dynend; j++) {
		if (document.getElementById("lati" + j)) {
			if (document.getElementById("lati" + j) != null && document.getElementById("longi" + j) != null)
				document.getElementById("lati" + j).value = tmptextLati[j];
			document.getElementById("longi" + j).value = tempchkLongi[j];
		}
	}
	i++;
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
function uniquesubdistrictNameValidation(subdistrictname) {

	var disId = document.getElementById("ddDistrict").value;
	if (disId > 0) {
		lgdDwrSubDistrictService.subDistrictExist(disId, subdistrictname, {
			callback : handleUniqueBlkSuccess,
			errorHandler : handleUniqueBlkError
		});
	}
}

function handleUniqueBlkSuccess(data) {
	document.getElementById("UniquesubDistrictError").innerHTML = "";
	if (!data) {
		document.getElementById("UniquesubDistrictError").innerHTML = "<font color='red'><br>Subdistrict Name Must Be Unique for Same District </font>";
		document.getElementById("OfficialAddress").value = "";
		document.getElementById("OfficialAddress").focus();

	}

}

function handleUniqueBlkError() {
	document.getElementById("OfficialAddress").value = "";
	document.getElementById("OfficialAddress").focus();
}
function chekcalphanumeric(inputtxt, num) {
	var status = true;
	if (inputtxt.match(/\S/)) {
		var obj = "";
		if (num == 1)
			obj = document.getElementById('OfficialAddress');
		else if (num == 2)
			obj = document.getElementById('aliasEnglish');
		else if (num == 3)
			obj = document.getElementById('sscode');
		var letterNumber = "";

		var retVal = false;
		if (num == 1 || num == 2) {
			inputtxt = inputtxt.replace(/\s+/g, '');
			letterNumber = /^[0-9a-zA-Z.-]+$/;
		} else if (num == 3)
			letterNumber = /^[0-9a-zA-Z]+$/;
		if (inputtxt.match(letterNumber))
			retVal = true;
		if (retVal == false) {
			if (num == 1)
				alert("Subdistrict Name contains invalid characters Use AlphaNumerics !");
			else if (num == 2)
				alert("Subdistrict Alias Name contains invalid characters Use AlphaNumerics !");
			else if (num == 3)
				alert("State Speficic Code contains invalid characters Use AlphaNumerics !");
			obj.value = "";
			status = false;
		}
	}
	return stautus;
}
function validatelocalCharachterforsubdistrict(value, num) {
	var status = true;
	var obj = "";
	if (num == 1)
		obj = document.getElementById('subdistrictNameLocal');
	else if (num == 2)
		obj = document.getElementById('aliasLocal');
	var retVal = true;
	retVal = validateSpecialCharacters(value);
	if (retVal == false) {
		if (num == 1)
			alert("Subdistrict Name in Local Language contains invalid characters !");
		else if (num == 2)
			alert("Subdistrict Alias Name in Local Language contains invalid characters !");
		obj.value = "";
		status = false;
	}

	return stautus;
}
function numericvaluesTest(inputtxt, num) {
	var status = true;
	var obj = "";
	if (num == 1)
		obj = document.getElementById('census2011Code');
	else if (num == 2)
		obj = document.getElementById('txtCensus');
	if (isNaN(inputtxt)) {
		alert("Census2011 Code contains invalid characters Use Numerics !");
		obj.value = "";
		status = false;
	}

	return stautus;
}
function removepartVilagelist() {
	var selObj = document.getElementById('subDistrictListNew');
	for ( var i = 0; i < selObj.options.length; i++) {
		if (selObj.options[i].selected)
			if ((selObj.options[i].value).substr(selObj.options[i].value.length - 4) == "PART") {
				$("#villageList").empty();
				$("#villageListNew").empty();
				break;
			}

	}

}
function SetAddvaluestemp(val) {

	if (val == "Add Another") {
		document.getElementById('addAnotherSD').value = "true";
		var selObj2 = document.getElementById('villageListNew');
		var villageNameList = "";
		for ( var i = 0; i < selObj2.options.length; i++) {
			if (i == 0) {
				villageNameList = selObj2.options[i].text;
			} else {
				villageNameList = villageNameList + "," + selObj2.options[i].text;
			}
			document.getElementById('villageNameList').value = villageNameList;
		}

		var selObj2 = document.getElementById('ddSubdistrict');
		var subDistrictListForSession = "";
		for ( var i = 0; i < selObj2.options.length; i++) {
			if (i == 0) {
				subDistrictListForSession = selObj2.options[i].text + ":" + selObj2.options[i].value;
			} else {
				subDistrictListForSession = subDistrictListForSession + "," + selObj2.options[i].text + ":" + selObj2.options[i].value;
			}
			document.getElementById('subDistrictListForSession').value = subDistrictListForSession;
		}

		var selObj2 = document.getElementById('subDistrictListNew');
		var contsubDistrictListForSession = "";
		for ( var i = 0; i < selObj2.options.length; i++) {
			if (i == 0) {
				contsubDistrictListForSession = selObj2.options[i].text + ":" + selObj2.options[i].value;
			} else {
				contsubDistrictListForSession = contsubDistrictListForSession + "," + selObj2.options[i].text + ":" + selObj2.options[i].value;
			}
			document.getElementById('contsubDistrictListForSession').value = contsubDistrictListForSession;
		}

		var selObj2 = document.getElementById('villageList');
		var villagesListForSession = "";
		for ( var i = 0; i < selObj2.options.length; i++) {
			if (i == 0) {
				villagesListForSession = selObj2.options[i].text + ":" + selObj2.options[i].value;
			} else {
				villagesListForSession = villagesListForSession + "," + selObj2.options[i].text + ":" + selObj2.options[i].value;
			}
			document.getElementById('villagesListForSession').value = villagesListForSession;
		}

		var selObj2 = document.getElementById('villageListNew');
		var contvillagesListForSession = "";
		for ( var i = 0; i < selObj2.options.length; i++) {
			if (i == 0) {
				contvillagesListForSession = selObj2.options[i].text + ":" + selObj2.options[i].value;
			} else {
				contvillagesListForSession = contvillagesListForSession + "," + selObj2.options[i].text + ":" + selObj2.options[i].value;
			}
			document.getElementById('contvillagesListForSession').value = contvillagesListForSession;
		}

	}
	/*if (document.getElementById('census2011Code').value != null && document.getElementById('census2011Code').value != "") {
		var numbers = /^[0-9]+$/;
		if ((document.getElementById('census2011Code').value.match(numbers))) {
		} else {
			$("#dialogcscode").dialog({
				resizable : false,
				height : 140,
				modal : true,
				buttons : {
					"Ok" : function() {
						$(this).dialog("close");
					},

				}
			});
			return false;
		}
	}*/
	if (document.getElementById('sscode').value != null && document.getElementById('sscode').value != "") {
		var numbers = /^[0-9a-zA-Z]+$/;
		if ((document.getElementById('sscode').value.match(numbers))) {
		} else {
			$("#dialogsscode").dialog({
				resizable : false,
				height : 140,
				modal : true,
				buttons : {
					"Ok" : function() {
						$(this).dialog("close");
					},

				}
			});
			return false;
		}
	}

}
function removeEarlierAddedVillages() {
	var temp = document.getElementById('previousAddedVillageCodes').value;
	var selObj = document.getElementById('villageList');
	var a = temp.split(","); // Delimiter is a string
	for ( var i = 0; i < a.length; i++) {
		for ( var j = 0; j < selObj.options.length; j++) {
			if (selObj.options[j].value == a[i]) {
				selObj.remove(j);
			}
		}

	}
}

function CheckthenaddItem(list1, list2, val, doAddVal) {
	var status = true;
	var temp = document.getElementById('partAddedSubdistrict').value;
	var value = "";
	for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
		if (document.getElementById(list2).options[j].selected == true) {
			value = document.getElementById(list2).options[j].text;
			if (temp.indexOf(value) > -1) {
				alert(value + " Subdistrict Already Selected As Part Earlier Please Select it as Part");
				status = false;
				break;
			}
		}
	}
	if (status == true)
		addItem(list1, list2, val, doAddVal);
}
