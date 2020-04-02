//validation and submit
var t = 4;
var dynstart = 0;
var dynend = 0;

function validateform() {
	var lock = true;
	var s = document.getElementById("flag1").value;
	var ddSubdistrict = document.getElementById('ddSubdistrict').value;
	var villageName = document.getElementById('OfficialAddress').value;
	var numFlds = document.addVillageNew.surveyNumber.length;
	if (s == 0) {
		var ddDistrict = document.getElementById('ddDistrict').value;
		if (ddDistrict == 0) {
			alert("Please Select District");
			document.getElementById('ddDistrict').focus();
			lock = false;
		}
	}
	if (ddSubdistrict == "0") {
		alert("Please Select Sub-District");
		document.getElementById('ddSubdistrict').focus();
		lock = false;
	} else if (villageName == "") {
		alert("Please Enter Village Name in English");
		document.getElementById('OfficialAddress').focus();
		lock = false;
	} else if ( document.getElementById('chknothing').checked == false
			&& document.getElementById('chkcvillage').checked == false) {
		lock = false;
		alert("Please choose an option to create Village ");

	} else if ( document.getElementById('chknothing').checked == false
			&& document.getElementById('chkcvillage').checked == true) {

		if (document.getElementById('villageListNew').value == "") {
			alert("Please choose contributing Villages ");
			lock = false;
		} else {
			oList = document.addVillageNew.elements["contributedVillages"];
			if (oList.options.length == 1) {

				var village = oList.options[0].value;
				if (village.indexOf("FULL") != -1) {
					alert("You cannot create new village from one full existing village");
					lock = false;

				}
			} else {
				lock = true;
			}
		}
	} else if (document.getElementById('chknothing').checked == true
			&& document.getElementById('chkcvillage').checked == true) {
		if (document.getElementById('villageListNew').value == "") {
			alert("Please choose contributing Villages ");
			lock = false;
		} else {
			oList = document.addVillageNew.elements["contributedVillages"];
			if (oList.options.length == 1) {

				var village = oList.options[0].value;
				if (village.indexOf("FULL") != -1) {
					alert("New village can not be constructed by full contribution of a single village");
					lock = false;

				}
			} else {
				lock = true;
			}
		}

	} else if (document.getElementById('chknothing').checked == false
			&& document.getElementById('chkcvillage').checked == true) {
		if (document.getElementById('villageListNew').value == "") {
			alert("Please choose contributing Villages ");
			lock = false;
		} else {
			oList = document.addVillageNew.elements["contributedVillages"];
			if (oList.options.length == 1) {
				var village = oList.options[0].value;
				if (village.indexOf("FULL") != -1) {
					alert("New village can not be constructed by full contribution of a single village");
					lock = false;

				}
			} else {
				lock = true;
			}
		}
	}

	else if (document.getElementById('chknothing').checked == true
			&& document.getElementById('chkcvillage').checked == false) {
		lock = true;

	}

	/*else if (document.getElementById('chkculb').checked == true && document.getElementById('chknothing').checked == false
			&& document.getElementById('chkcvillage').checked == false) {
		if (document.getElementById('ulbListNew').value == "") {
			alert("Please choose contributing ULB's.");
			lock = false;
		} else {
			lock = true;
		}
	}*/

	if (numFlds > 0) {
		for ( var x = 0; x < numFlds; x++) {
			if (document.addVillageNew.surveyNumber[x].value == "") {
				alert("Survey Number cannot be blank");
				lock = false;
				break;
			} else {
				lock = true;
			}
		}
	}
	var selObj = document.getElementById('villageListNew');
	var villageText = "";
	var count = 0;
	var val = "";
	var i = "";
	for (i = 0; i < selObj.options.length; i++) {
		villageText = selObj.options[i].text;
		if (villageText.indexOf("PART") != -1)
			count++;
	}
	var id = "";
	var radios = document.getElementsByName("rename");
	for (i = 0; i < radios.length; i++) {
		if (radios[i].checked) {
			id = radios[i].value;
			break;
		}
	}
	var preVal = "";
	var renameId = "";
	if (id == 1) {
		lock=checkRenameVillageunique(villageName,count,lock)
	}
	villageVal(villageName);
	if (document.getElementById('OfficialAddress').value == "") {
		lock = false;
	}
	
	return lock;
}
/*
 * function villageVal(vilname) { var subdisid =
 * document.getElementById("ddSubdistrict").value;
 * lgdDwrVillageService.VilageExist(subdisid, vilname, { callback :
 * handlevillageValSuccess, errorHandler : handlevillageValError }); }
 * 
 * function handlevillageValSuccess(data) {
 * document.getElementById("errProSelectDist").innerHTML = ""; if (!data) {
 * document.getElementById("errProSelectDist").innerHTML = "<font color='red'><br><spring:message
 * code=" error.select.UNIQUEVILLAGE "/></font>";
 * 
 * document.getElementById("OfficialAddress").value = "";
 * document.getElementById("OfficialAddress").focus(); } }
 * 
 * function handlevillageValError() {
 * document.getElementById("OfficialAddress").value = "";
 * document.getElementById("OfficialAddress").focus(); }
 */


function validateformModify() {
	var lock = true;
	var s = document.getElementById("flag1").value;
	var ddSubdistrict = document.getElementById('ddSubdistrict').value;
	var villageName = document.getElementById('OfficialAddress').value;
	var numFlds = document.modifyVillageCmd.surveyNumber.length;
	if (s == 0) {
		var ddDistrict = document.getElementById('ddDistrict').value;
		if (ddDistrict == 0) {
			alert("Please Select District");
			document.getElementById('ddDistrict').focus();
			lock = false;
		}
	}
	if (ddSubdistrict == "0") {
		alert("Please Select Sub-District");
		document.getElementById('ddSubdistrict').focus();
		lock = false;
	} else if (villageName == "") {
		alert("Please Enter Village Name in English");
		document.getElementById('OfficialAddress').focus();
		lock = false;
	} else if (document.getElementById('chkculb').checked == false && document.getElementById('chknothing').checked == false
			&& document.getElementById('chkcvillage').checked == false) {
		lock = false;
		alert("Please choose an option to create Village ");

	} else if (document.getElementById('chkculb').checked == false && document.getElementById('chknothing').checked == false
			&& document.getElementById('chkcvillage').checked == true) {

		if (document.getElementById('villageListNew').value == "") {
			alert("Please choose contributing Villages ");
			lock = false;
		} else {
			oList = document.modifyVillageCmd.elements["contributedVillages"];
			if (oList.options.length == 1) {

				var village = oList.options[0].value;
				if (village.indexOf("FULL") != -1) {
					alert("You cannot create new village from one full existing village");
					lock = false;

				}
			} else {
				lock = true;
			}
		}

	} else if (document.getElementById('chkculb').checked == false && document.getElementById('chknothing').checked == true
			&& document.getElementById('chkcvillage').checked == true) {
		if (document.getElementById('villageListNew').value == "") {
			alert("Please choose contributing Villages ");
			lock = false;
		} else {
			oList = document.modifyVillageCmd.elements["contributedVillages"];
			if (oList.options.length == 1) {

				var village = oList.options[0].value;
				if (village.indexOf("FULL") != -1) {
					alert("New village can not be constructed by full contribution of a single village");
					lock = false;

				}
			} else {
				lock = true;
			}
		}

	} else if (document.getElementById('chkculb').checked == true && document.getElementById('chknothing').checked == false
			&& document.getElementById('chkcvillage').checked == true) {
		if (document.getElementById('villageListNew').value == "") {
			alert("Please choose contributing Villages ");
			lock = false;
		} else {
			oList = document.modifyVillageCmd.elements["contributedVillages"];
			if (oList.options.length == 1) {
				var village = oList.options[0].value;
				if (village.indexOf("FULL") != -1) {
					alert("New village can not be constructed by full contribution of a single village");
					lock = false;

				}
			} else {
				lock = true;
			}
		}
	}

	else if (document.getElementById('chkculb').checked == false && document.getElementById('chknothing').checked == true
			&& document.getElementById('chkcvillage').checked == false) {
		lock = true;

	}

	else if (document.getElementById('chkculb').checked == true && document.getElementById('chknothing').checked == false
			&& document.getElementById('chkcvillage').checked == false) {
		if (document.getElementById('ulbListNew').value == "") {
			alert("Please choose contributing ULB's.");
			lock = false;
		} else {
			lock = true;
		}

	}
	if (numFlds > 0) {
		for ( var x = 0; x < numFlds; x++) {
			if (document.modifyVillageCmd.surveyNumber[x].value == "") {
				alert("Survey Number cannot be blank");
				lock = false;
				break;
			} else {
				lock = true;
			}
		}
	}
	var selObj = document.getElementById('villageListNew');
	var villageText = "";
	var count = 0;
	var val = "";
	var i = "";
	for (i = 0; i < selObj.options.length; i++) {
		villageText = selObj.options[i].text;
		if (villageText.indexOf("PART") != -1)
			count++;
	}
	var renameRadio = document.getElementById('rename').value;
	var id = "";
	if (renameRadio == 1) {
		var radios = document.getElementsByName("rename");
		for (i = 0; i < radios.length; i++) {
			if (radios[i].checked) {
				id = radios[i].value;
				break;
			}
		}
	}
	var preVal = "";
	var renameId = "";
	if (id == 1) {
		for (i = 0; i < count; i++) {
			if (document.getElementById("reNameFlagId" + i).checked) {
				id = 0;
				val = document.getElementById("reNamedVillIdNew" + i).value;
				renameId = document.getElementById("reNamedVillIdNew" + i);
				if (i > 0)
					preVal = document.getElementById("reNamedVillIdNew"
							+ (i - 1)).value;
				else
					preVal = villageName;
				if (val == null || val.trim() == "") {
					val = document.getElementById("reNamedVillId" + i).value;
					alert('Please enter ReName of Village ' + val);
					lock = false;
					break;
				}else {
					if (val.replace(/ /g,'').toUpperCase() == preVal.replace(/ /g,'').toUpperCase()) {
						alert('Village name should be unique.');
						renameId.focus();
						lock = false;
						break;
					} else {
						var subdisid = document.getElementById("ddSubdistrict").value;
						lgdDwrVillageService.VilageExist(subdisid, renameId.value, {
							async : false,
							callback : function(data) {
								if (data=='P') {
									lock = false;
									alert("Village name should be unique.");	
									renameId.value = "";
									renameId.focus();
								}
								else if(data == 'S'){
									lock = false;	
									alert("Village name should be unique.");
									renameId.value = "";
									renameId.focus();
								}
							}			
						});
					}
				}
			}
		}
	}
	if (id == 1) {
		alert("Please Rename Atleast One Village or Select No Option");
		lock = false;
	}
	return lock;
}

function selectall() {

	if (document.getElementById('chknothing').checked) {
		document.getElementById('rename').value = 0;
		return true;
	}
	if (document.getElementById('chkcvillage').checked) {
		var selObj = document.getElementById('villageListNew');

		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
		}
		fillSelectVillageSNo();

	}
	/*if (document.getElementById('chkculb').checked) {
		document.getElementById('rename').value = 0;
		var selObj3 = document.getElementById('ulbListNew');
		for ( var k = 0; k < selObj3.options.length; k++) {
			selObj3.options[k].selected = true;

		}
	}*/

}

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

	var fieldId = 'ulbListMain';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleCoveredLandRegionByULBError() {
	// Show a popup message
	alert("No data found!");
}
// **********ULB coverage END******/

function getSubDistrictandULBList(id) {
	$( '#errddDistrict').text(""); 
	$("#villageListMain").empty();
	$("#villageListNew").empty();
	$("#surveyListMain").empty();
	$("#surveyListNew").empty();
	$("#ulbListMain").empty();
	$("#ulbListNew").empty();
	document.getElementById("survernoset").value = "0";
	document.getElementById('cvillage').style.display = 'none';
	document.getElementById('culb').style.display = 'none';
	document.getElementById('chkcvillage').checked = false;
	document.getElementById('chknothing').checked = false;
	//document.getElementById('chkculb').checked = false;
	document.getElementById('subDistrictCode').value = 0;
	document.getElementById('districtCode').value = id;
	getSubDistrictList(id);
	getULBList(id);

}

function getSubDistrictandULBListModify(id) {

	$("#villageListMain").empty();
	$("#villageListNew").empty();
	$("#surveyListMain").empty();
	$("#surveyListNew").empty();
	$("#ulbListMain").empty();
	$("#ulbListNew").empty();
	document.getElementById("survernoset").value = "0";
	getULBList(id);

}

// ************ulb list start*******/
function getULBList(id) {
	try {
		
		lgdDwrlocalBodyService.getULBList(id, {
			callback : handleULBSuccess,
			errorHandler : handleULBError
		});
	} catch (err) {
		alert(err);
	}
}

/*Modified by Pooja on 24-07-2015*/
function handleULBSuccess(data) {
	var selectedUlbCode = document.getElementById("selectedUlbCode").value;
	var coverage = selectedUlbCode.charAt(selectedUlbCode.length-1);
	selectedUlbCode = selectedUlbCode.substr(0,selectedUlbCode.length - 1);
	var fieldId = 'ulbListMain';
	var fieldId1 = 'ulbListNew';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);
	var options = $("#ulbListMain");
	var options1 = $("#ulbListNew");
	for(var i=0;i<data.length;i++){
		var option = $("<option/>");
		var option1 = $("<option/>");
		if((selectedUlbCode == data[i].localBodyCode) && (data[i].operation_state == 'F')){
			if(coverage == 'F'){
			$(option1).val(data[i].localBodyCode+"FULL").text(data[i].localBodyNameEnglish + " (FULL)");
			}else if(coverage == 'P'){
			$(option1).val(data[i].localBodyCode+"PART").text(data[i].localBodyNameEnglish + " (PART)");
			}
			options1.append(option1);
		}
		else{
			if ((data[i].operation_state == 'F') && (selectedUlbCode != data[i].localBodyCode)) {
				$(option).attr('disabled', 'disabled');
				$(option).val(data[i].localBodyCode).text(data[i].localBodyNameEnglish + "(Used in Draft mode)");
			} else {
				$(option).val(data[i].localBodyCode).text(data[i].localBodyNameEnglish);
			}
			options.append(option);
		}
	}
}

function handleULBError() {
	// Show a popup message
	alert("No data found!");
}

// **************ulb list fetching end***********/

// for first subdistrict
function getSubDistrictList(id) {
	try {
		lgdDwrSubDistrictService.getSubDistrictListForLocalbody(id, {
			callback : handleSubDistrictSuccess,
			errorHandler : handleSubDistrictError
		});
	} catch (err) {
		alert(err);
	}

}

// data contains the returned value
function handleSubDistrictSuccess(data) {
	// Assigns data to result id
	// alert(data);
	var fieldId = 'ddSubdistrict';
	dwr.util.removeAllOptions(fieldId);
	// var dataq = [ {name:"Select Subdistrict"} ];
	var st = document.getElementById('ddSubdistrict');
	st.add(new Option('Select Subdistrict', '0'));
	// dwr.util.addOptions(fieldId, dataq, "name");

	var options = $("#ddSubdistrict");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish + "(Used in Draft mode)");
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

function getVillageList(id) {
	$( '#errddSubdistrict').text(""); 
	$("#villageListMain").empty();
	$("#villageListNew").empty();
	$("#surveyListMain").empty();
	$("#surveyListNew").empty();
	document.getElementById("survernoset").value = "0";
	// $("#ulbListMain").empty();
	// $("#ulbListNew").empty();

	document.getElementById('cvillage').style.display = 'none';
	//document.getElementById('culb').style.display = 'none';
	document.getElementById('chkcvillage').checked = false;
	document.getElementById('chknothing').checked = false;
	//document.getElementById('chkculb').checked = false;
	document.getElementById('subDistrictCode').value = id;
	try {
		lgdDwrVillageService.getVillageListWithOperationState(parseInt(id), {
			asyn : false,
			callback : handleVillageSuccess,
			errorHandler : handleVillageError
		});
	} catch (err) {
		alert(err);
	}

}

function getVillage(subdistCode, draftVilCode) {

	$("#villageListMain").empty();
	$("#villageListNew").empty();
	$("#surveyListMain").empty();
	$("#surveyListNew").empty();
	document.getElementById("survernoset").value = "0";
	try {
		lgdDwrVillageService.getDraftVillageListWithOperationState(subdistCode, draftVilCode, {
			asyn : false,
			callback : handleVillageSuccessModify,
			errorHandler : handleVillageError
		});
	} catch (err) {
		alert(err);
	}

}

// data contains the returned value
function handleVillageSuccessModify(data) {
	// Assigns data to result id
	
	var fieldId = 'villageListMain';
	var fieldId2 = 'villageListNew';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId2);
	var options = $("#villageListMain");
	var options2 = $("#villageListNew");
	var renameFlag = false;
	var renameList = "";
	$.each(data, function(key, obj) {
		if (obj.opeartion_state == 'S' || obj.opeartion_state == 'P') {
			var option = $("<option/>");
			if (obj.opeartion_state == 'S' || obj.operation_state == 'F') {
				$(option).attr('disabled', 'disabled');
				$(option).val(obj.villageCode).text(obj.villageNameEnglish + "(Used in Draft mode)");
			} else {
				$(option).val(obj.villageCode).text(obj.villageNameEnglish);
			}

			options.append(option);
		} else {
			var option2 = $("<option/>");
			$(option2).val(obj.villageCode + obj.partFullFlag).text(obj.villageNameEnglish);
			options2.append(option2);
			if (obj.renameNameVillageList != null && obj.renameNameVillageList != '') {
				document.getElementById("rename").checked = true;
				renameFlag = true;
				renameList = obj.renameNameVillageList;
			}
		}
	});
	if (renameFlag == true) {
		renameListMethodOnLoad(1, renameList);
	}
}

// data contains the returned value
function handleVillageSuccess(data) {
	// Assigns data to result id
	var fieldId = 'villageListMain';
	dwr.util.removeAllOptions(fieldId);
	var options = $("#villageListMain");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.opeartion_state == 'S' || obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.villageCode).text(obj.villageNameEnglish + "(Used in Draft mode)");
		} else {
			$(option).val(obj.villageCode).text(obj.villageNameEnglish);
		}

		options.append(option);
	});
	// dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleVillageError() {
	// Show a popup message
	alert("No data found!");
}

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

function getSurverNumbers(list1) {
	if (document.getElementById(list1).value.substr(document.getElementById(list1).value.length - 4) == "FULL") {
		alert("You Can Only Select Survey Numbers Of \"(Part)\" Villages");
	} else if (document.getElementById(list1).value.substr(document.getElementById(list1).value.length - 4) == "PART") {
		// TODO: CALL DWR HERE
	}
}
function getSurveyNobyVillage() {
	$("#surveyListMain").empty();
	$("#surveyListNew").empty();
	var selObj = document.getElementById('villageListNew');
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
		alert("Select part village to get survey number");
		return false;
	}
	document.getElementById("survernoset").value = "1";
	villageId = villageId.substring(0, villageId.length - 1);
	lgdDwrVillageService.getVillageSuerveryNo(villageId, {
		callback : handleVillageSurveySuccess,
		errorHandler : handleVillageSurveyError
	});

}

function handleVillageSurveySuccess(data) {
	// Assigns data to result id

	var fieldId = 'surveyListMain';
	var valueText = 'villageCode';
	var nameText = 'villageNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleVillageSurveyError() {
	alert("No Survey no found!");
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
function removevillageandsurveyno(list1, list2, doRemoveVal) {
	var selObj = document.getElementById('villageListNew');
	for ( var i = 0; i < selObj.options.length; i++) {
		if (selObj.options[i].selected)
			if ((selObj.options[i].value).substr(selObj.options[i].value.length - 4) == "PART") {
				$("#surveyListMain").empty();
				$("#surveyListNew").empty();
				document.getElementById("survernoset").value = "0";
				break;
			}

	}

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

function officialAddressVil() {
	$( '#errOfficialAddress').text(""); 
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

}

function addgisnodes() {

	var tmptextLati = new Array();
	var tempchkLongi = new Array();

	if (dynstart == 0) {
		dynstart = t;
	}
	dynend = t;
	alert(dynend);
	for ( var j = dynstart; j < dynend; j++) {
		if (document.getElementById("lati" + j)) {
			if (document.getElementById("lati" + j) != null && document.getElementById("longi" + j) != null)
				tmptextLati[j] = document.getElementById("lati" + j).value;
			tempchkLongi[j] = document.getElementById("longi" + j).value;
		}
	}

	document.getElementById("addgisnodes").innerHTML += "<div id=div"
			+ t
			+ "><td align='left'>"
			+ document.getElementById('lbllatitude').innerHTML
			+ "</td><td width='75'>&nbsp;&nbsp;<input type='text' name='latitude' id='lati"
			+ t
			+ "' class='frmfield'></td><td width='145'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td width='150'>"
			+ document.getElementById('lbllongitude').innerHTML + "</td><td width='75'>&nbsp;&nbsp;<input type='text' name='longitude' id='longi" + t
			+ "' class='frmfield'></td><td><input type='button' value='Remove' onclick='div" + t + ".parentNode.removeChild(div" + t
			+ ")'/></td></div>";

	for ( var j = dynstart; j < dynend; j++) {
		if (document.getElementById("lati" + j)) {
			if (document.getElementById("lati" + j) != null && document.getElementById("longi" + j) != null)
				document.getElementById("lati" + j).value = tmptextLati[j];
			document.getElementById("longi" + j).value = tempchkLongi[j];
		}
	}
	t++;
}

/*
 * function addgisnodes() { document.getElementById("addgisnodes").innerHTML += "<div
 * width='100%'>" + document.getElementById('lbllatitude').text + "<input
 * type='text' class='frmfield' name='latitude'>" +
 * document.getElementById('lbllongitude').text + "<input type='text'
 * class='frmfield' name='longitude'><input type='button' value='Remove'
 * onclick='this.parentNode.parentNode.removeChild(this.parentNode)'></div>"; }
 */
function addsurveys() {
	document.getElementById("addsurveys").innerHTML += "<div width='100%'><input type='text'  class='frmfield' maxlength='10' name='surveyNumber'><input type='button'  value='Remove' onclick='this.parentNode.parentNode.removeChild(this.parentNode)'></div>";
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
/*
 * function validateOrdeNo() { var x = document.getElementById('orno').value; if
 * (x == 1) { if (document.getElementById('OrderNo').value == "") {
 * document.getElementById("OrderNo_error").innerHTML = "Order No. is
 * required."; $("#OrderNo_error").show(); $("#OrderNo_msg").hide();
 * $("#OrderNo").addClass("error_fld"); $("#OrderNo").addClass("error_msg");
 * return false; } else { $("#OrderNo_msg").hide();
 * 
 * return true; } } else { $("#OrderNo_msg").hide();
 * 
 * return true; } }
 * 
 * function validateOrdeDate() { var orderdate =
 * document.getElementById('OrderDate').value; var x =
 * document.getElementById('orno').value; if (x == 1) { if
 * (document.getElementById('OrderDate').value == "") {
 * document.getElementById("OrderDate_error").innerHTML = "Order Date is
 * required."; $("#OrderDate_error").show(); $("#OrderDate_msg").hide();
 * $("#OrderDate").addClass("error_fld"); $("#OrderDate").addClass("error_msg");
 * return false; }
 * 
 * else if (!validateDateDDMMYYY(orderdate)) {
 * document.getElementById("OrderDate_error").innerHTML = "Enter valid
 * date(dd-mm-yyyy) format"; $("#OrderDate_error").show();
 * $("#OrderDate_msg").hide(); $("#OrderDate").addClass("error_fld");
 * $("#OrderDate").addClass("error_msg"); return false; } else if
 * (!compareDateDDMMYYY(orderdate)) {
 * document.getElementById("OrderDate_error").innerHTML = "Order Date should be
 * equal or earlier to Current Date "; $("#OrderDate_error").show();
 * $("#OrderDate_msg").hide(); $("#OrderDate").addClass("error_fld");
 * $("#OrderDate").addClass("error_msg"); return false; } else {
 * $("#OrderDate_msg").hide(); return true; } } else if
 * (document.getElementById('OrderNo').value != "") { if
 * (document.getElementById('OrderDate').value == "") {
 * document.getElementById("OrderDate_error").innerHTML = "Order Date is
 * required."; $("#OrderDate_error").show(); $("#OrderDate_msg").hide();
 * $("#OrderDate").addClass("error_fld"); $("#OrderDate").addClass("error_msg");
 * return false; }
 * 
 * else if (!validateDateDDMMYYY(orderdate)) {
 * document.getElementById("OrderDate_error").innerHTML = "Enter valid
 * date(dd-mm-yyyy) format"; $("#OrderDate_error").show();
 * $("#OrderDate_msg").hide(); $("#OrderDate").addClass("error_fld");
 * $("#OrderDate").addClass("error_msg"); return false; } else if
 * (!compareDateDDMMYYY(orderdate)) {
 * document.getElementById("OrderDate_error").innerHTML = "Order Date should be
 * equal or earlier to Current Date "; $("#OrderDate_error").show();
 * $("#OrderDate_msg").hide(); $("#OrderDate").addClass("error_fld");
 * $("#OrderDate").addClass("error_msg"); return false; } else {
 * $("#OrderDate_msg").hide(); return true; } } else {
 * $("#OrderDate_msg").hide(); return true; } }
 * 
 * function validateEffecDate() { var effecdate =
 * document.getElementById('EffectiveDate').value;
 * document.getElementById('EffectiveDate_error').style.visibility = 'visible';
 * var x = document.getElementById('orno').value; if (x == 1) { if
 * (document.getElementById('EffectiveDate').value == "") {
 * document.getElementById("EffectiveDate_error").innerHTML = "Effective Date is
 * required."; $("#EffectiveDate_error").show(); $("#EffectiveDate_msg").hide();
 * $("#EffectiveDate").addClass("error_fld");
 * $("#EffectiveDate").addClass("error_msg"); return false; }
 * 
 * else if (!validateDateDDMMYYY(effecdate)) {
 * document.getElementById("EffectiveDate_error").innerHTML = "Enter valid
 * date(dd-mm-yyyy) format"; $("#EffectiveDate_error").show();
 * $("#EffectiveDate_msg").hide(); $("#EffectiveDate").addClass("error_fld");
 * $("#EffectiveDate").addClass("error_msg"); return false; } else if
 * (!compareDateforEffectDDMMYYY(effecdate)) {
 * 
 * document.getElementById("EffectiveDate_error").innerHTML = "Effective Date
 * should not be earlier than Order Date "; $("#EffectiveDate_error").show();
 * $("#EffectiveDate_msg").hide(); $("#EffectiveDate").addClass("error_fld");
 * $("#EffectiveDate").addClass("error_msg"); return false; }
 * 
 * else { $("#EffectiveDate_msg").hide();
 * 
 * return true; } } else if (document.getElementById('OrderNo').value != "") {
 * if (document.getElementById('EffectiveDate').value == "") {
 * document.getElementById("EffectiveDate_error").innerHTML = "Effective Date is
 * required."; $("#EffectiveDate_error").show(); $("#EffectiveDate_msg").hide();
 * $("#EffectiveDate").addClass("error_fld");
 * $("#EffectiveDate").addClass("error_msg"); return false; }
 * 
 * else if (!validateDateDDMMYYY(effecdate)) {
 * document.getElementById("EffectiveDate_error").innerHTML = "Enter valid
 * date(dd-mm-yyyy) format"; $("#EffectiveDate_error").show();
 * $("#EffectiveDate_msg").hide(); $("#EffectiveDate").addClass("error_fld");
 * $("#EffectiveDate").addClass("error_msg"); return false; } else if
 * (!compareDateforEffectDDMMYYY(effecdate)) {
 * 
 * document.getElementById("EffectiveDate_error").innerHTML = "Effective Date
 * should not be earlier than Order Date "; $("#EffectiveDate_error").show();
 * $("#EffectiveDate_msg").hide(); $("#EffectiveDate").addClass("error_fld");
 * $("#EffectiveDate").addClass("error_msg"); return false; }
 * 
 * else { $("#EffectiveDate_msg").hide();
 * 
 * return true; } } else { $("#EffectiveDate_msg").hide();
 * 
 * return true; } }
 * 
 * function validateGazPubDate() {
 * 
 * var gazPubDate = document.getElementById('GazPubDate').value; if
 * (document.getElementById('GazPubDate').value == "") {
 * document.getElementById("GazPubDate_error").innerHTML = " Gazette Publication
 * Date is required."; $("#GazPubDate_error").show();
 * $("#GazPubDate_msg").hide(); $("#GazPubDate").addClass("error_fld");
 * $("#GazPubDate").addClass("error_msg"); return false; }
 * 
 * else if (!validateDateDDMMYYY(gazPubDate)) {
 * 
 * document.getElementById("GazPubDate_error").innerHTML = "Enter valid
 * date(dd-mm-yyyy) format"; $("#GazPubDate_error").show();
 * $("#GazPubDate_msg").hide(); $("#GazPubDate").addClass("error_fld");
 * $("#GazPubDate").addClass("error_msg"); return false; }
 * 
 * else if (!compareDateforEffectDDMMYYY(gazPubDate)) {
 * document.getElementById("GazPubDate_error").innerHTML = "Gazette Publication
 * Date should be equal to or greater than Order Date ";
 * $("#GazPubDate_error").show(); $("#GazPubDate_msg").hide();
 * $("#GazPubDate").addClass("error_fld");
 * $("#GazPubDate").addClass("error_msg"); return false; }
 * 
 * else { $("#GazPubDate_error").hide(); return true; } }
 * 
 * function validateSFile() {
 * 
 * if (document.getElementById('filGovernmentOrder').value == "") {
 * document.getElementById("filGovernmentOrder_error").innerHTML = "Please
 * upload at least one file as Government Order";
 * $("#filGovernmentOrder_error").show(); $("#filGovernmentOrder_msg").hide();
 * $("#filGovernmentOrder").addClass("error_fld");
 * $("#filGovernmentOrder").addClass("error_msg"); return false; } else {
 * $("#filGovernmentOrder_error").hide();
 * 
 * return true; } }
 * 
 * function validateSFile1() {
 * 
 * if (document.getElementById('loadfile').value == "") {
 * document.getElementById("loadfile_error").innerHTML = "Please upload at least
 * one file as Government Order"; $("#loadfile_error").show();
 * $("#loadfile").addClass("error_fld"); $("#loadfile").addClass("error_msg");
 * return false; } else { $("#loadfile_error").hide(); return true; } }
 */
function validateChange() {

	var msg = null;

	if (!validateOrdeNo()) {

		msg = "Please Enter Order Number" + '\n';

	}
	if (!validateOrdeDate()) {
		if (msg != null) {
			msg = msg + "Please select Order Date" + '\n';
		} else {
			msg = "Please select Order Date" + '\n';
		}
	}
	if (!validateEffecDate()) {
		if (msg != null) {
			msg = msg + "Please select Effective Date" + '\n';
		} else {
			msg = "Please select Effective Date" + '\n';
		}
	}
	if (!validateGazPubDate()) {
		if (msg != null) {
			msg = msg + "Please select Gaz Publication Date" + '\n';
		} else {
			msg = "Please select Gaz Publication Date" + '\n';
		}
	}
	if (!OfficialAddress()) {
		if (msg != null) {
			msg = msg + "Please Enter Village name " + '\n';
		} else {
			msg = "Please Enter Village Name" + '\n';
		}
	}

	if (msg != null) {
		alert(msg);
		return false;
	} else {

		return true;
	}

	return false;
}
function resetView() {
	alert(document.getElementById('text1').value);
	document.getElementById('text1').value = "";
	document.getElementById('text2').value = "";
}
function modifyvilllagevalidate() {
	var x = document.getElementById('orno').value;
	if (x == 0) {
		validateCorrectionAlert();
		if (document.getElementById('OrderNo').value != "") {
			if (document.getElementById('OrderDate').value == "" || (document.getElementById('EffectiveDate').value == "")) {
				return false;
			}
		}

		if (document.getElementById('OrderDate').value != "") {
			if (document.getElementById('OrderNo').value == "" || (document.getElementById('EffectiveDate').value == "")) {

				alert("Please Enter All Government Order Details ");
				return false;
			}
		}

		if (document.getElementById('EffectiveDate').value != "") {
			if (document.getElementById('OrderDate').value == "" || (document.getElementById('OrderNo').value == "")) {
				alert("Please Enter All Government Order Details ");
				return false;
			}
		}

		if (document.getElementById('attachFile1').value != "") {
			if (document.getElementById('OrderNo').value == "" || (document.getElementById('EffectiveDate').value == "")
					|| (document.getElementById('OrderDate').value == "")) {

				alert("Please Enter All Government Order Details ");
				return false;
			}
		}

	}

	else
		validateCorrectionAlert();

}
function getDisUserSubDistrictList(id) {
	getSubDistrictList(id);
	getULBList(id);
}

function getDistrictbyhierarchy(id) {
	var districtid = document.getElementById('districtCode').value;
	if (districtid > 0)
		lgdDwrDistrictService.getDistrictListByDistCode(districtid, {
			callback : ManageVillageDistrictSuccess,
			errorHandler : ManageVillageDistrictError
		});
	else
		lgdDwrDistrictService.getDistrictList(id, {
			callback : ManageVillageDistrictSuccess,
			errorHandler : ManageVillageDistrictError
		});
}

function ManageVillageDistrictSuccess(data) {
	if (data == '')
		alert("No Data Found.");
	var fieldId = 'ddSourceDistrict';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddSourceDistrict');
	st.add(new Option(' --------- Select -------- ', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function ManageVillageDistrictError() {
	// Show a popup message
	alert("No data found!");
}
function validateSpecialCharactersforVillageValues(value, num) {
	var status = true;
	var obj = "";
	if (num == 1)
		obj = document.getElementById('newVillageNameLocal');
	else if (num == 2)
		obj = document.getElementById('newVillageAliasLocal');
	var retVal = true;
	retVal = validateSpecialCharacters(value);
	if (retVal == false) {
		if (num == 1)
			alert("Village Name in Local Language contains invalid characters !");
		else if (num == 2)
			alert("Village Alias Name in Local Language contains invalid characters  !");
		obj.value = "";
		status = false;
	}

	return stautus;
}

function removeAllSelectedVillages(list1, list2, doRemoveVal) {
	$("#surveyListMain").empty();
	$("#surveyListNew").empty();
	document.getElementById("survernoset").value = "0";
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
function chekcalphanumeric(inputtxt, num) {
	var status = true;
	if (inputtxt.match(/\S/)) {
		var obj = "";
		if (num == 3)
			obj = document.getElementById('sscode');
		else if (num == 4)
			obj = document.getElementById('surveyNumber');
		else if (num == 5)
			obj = document.getElementById('txtSscode');
		var retVal = false;
		var letterNumber = /^[0-9a-zA-Z]+$/;
		if (inputtxt.match(letterNumber))
			retVal = true;
		if (retVal == false) {

			if (num == 3 || num == 5)
				alert("State Specific Code  contains invalid characters Use AlphaNumerics !");
			else if (num == 4)
				alert("Survey No. contains invalid characters Use AlphaNumerics !");

			obj.value = "";
			status = false;
		}
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
function removeDataCorrection() {
	document.getElementById('subdistrictname').value = "";
	document.getElementById('txtNameLocal').value = "";
	document.getElementById('txtAliasEnglish').value = "";
	document.getElementById('txtAliasLocal').value = "";
	document.getElementById('subdistrictname').focus();
}
function fillSelectVillageSNo() {
	var slist = document.getElementById("surveyListNew");
	var textval;
	var vlc;
	var sno;
	var temp;
	var surveyno = "";
	for ( var x = 0; x < slist.length; x++) {
		vlc = slist.options[x].value;
		textval = slist.options[x].text;
		temp = textval.split("-");
		textval = temp[1];
		sno = vlc + ":" + textval + ",";
		surveyno = surveyno + sno;
	}
	surveyno = surveyno.substring(0, surveyno.length - 1);
	document.getElementById("contributedSurvey").value = surveyno;
}
function surverNoSelected() {
	if (document.getElementById('chkcvillage').checked) {
		var selObj = document.getElementById('villageListNew');
		var temp;
		var surveystatus = true;
		var count = 0;
		var vilsurveryno = new Array();
		var vilsurverynotwo = new Array();
		var unique = new Array();
		var uniquetwo = new Array();
		var len;
		var i;
		for (i = 0; i < selObj.options.length; i++) {
			if ((selObj.options[i].value).substr(selObj.options[i].value.length - 4) == "PART")
				count++;
		}
		if (count > 0) {
			temp = document.getElementById("survernoset").value;
			/*if (temp == 0) {
				alert("Please Check Survey No is available, By Clicking on Get Survey No of Part Villages");
				surveystatus = false;
			} else {*/
				count = document.getElementById('surveyListMain').options.length;
				if (count > 0) {
					selObj = document.getElementById('surveyListMain');
					for (i = 0; i < selObj.options.length; i++)
						vilsurveryno[i] = selObj.options[i].value;

					selObj = document.getElementById('surveyListNew');
					for ( var j = i; j < selObj.options.length; j++)
						vilsurveryno[j] = selObj.options[j].value;

					unique = vilsurveryno.filter(function(itm, i, vilsurveryno) {
						return i == vilsurveryno.indexOf(itm);
					});
					len = unique.length;
					for (i = 0; i < selObj.options.length; i++)
						vilsurverynotwo[i] = selObj.options[i].value;

					uniquetwo = vilsurverynotwo.filter(function(itm, i, vilsurverynotwo) {
						return i == vilsurverynotwo.indexOf(itm);
					});
					count = uniquetwo.length;
					if (count < len) {
						alert("Please Select Atleast One Survery No from each Village");
						surveystatus = false;
					}
				}
			//}
		}
		return surveystatus;
	} else {
		return true;
	}

}
function fillselectedSurveryNo(list1, list2, val, doAddVal) {
	if (prevenetallSurveryNo())
		addItemSurvey(list1, list2, val, doAddVal);

}
function prevenetallSurveryNo() {
	var surveysutatus = true;
	var unique = new Array();
	var vilsurveryno = new Array();
	var i;
	var total;
	var left;
	var selected;
	var temp;
	var selObj = document.getElementById('surveyListMain');
	for (i = 0; i < selObj.options.length; i++) {

		if (selObj.options[i].selected)
			vilsurveryno[i] = selObj.options[i].value;
	}
	unique = vilsurveryno.filter(function(itm, i, vilsurveryno) {
		return i == vilsurveryno.indexOf(itm);
	});
	for (i = 0; i < unique.length; i++) {
		temp = unique[i];
		total = 0;
		left = 0;
		selected = 0;
		selObj = document.getElementById('surveyListMain');
		for (i = 0; i < selObj.options.length; i++) {
			if (temp == selObj.options[i].value)
				total++;
		}
		left = total;

		selObj = document.getElementById('surveyListNew');
		for (i = 0; i < selObj.options.length; i++)
			if (temp == selObj.options[i].value)
				total++;
		if (total > 1) {

			selObj = document.getElementById('surveyListMain');
			for (i = 0; i < selObj.options.length; i++)
				if (selObj.options[i].selected)
					if (temp == selObj.options[i].value)
						selected++;
			if (left == selected) {
				surveysutatus = false;
				alert("You Can't Select all Survey No's from a Single Village");
				break;
			}

		}

	}
	return surveysutatus;

}
function checkmultipleSno(val, num) {
	var inputtxt = document.getElementById(val).value;
	var status = true;
	if (inputtxt.match(/\S/)) {
		var obj = "";
		obj = document.getElementById(val);
		var retVal = false;
		var letterNumber = /^[0-9a-zA-Z]+$/;
		if (inputtxt.match(letterNumber))
			retVal = true;
		if (retVal == false) {
			alert("Survey No. contains invalid characters Use AlphaNumerics !");
			obj.value = "";
			status = false;
		}
	}
	return stautus;
}

checkRenameVillageunique=function(villageName,reNameSize,lock){
	var renameCount=0;
	for (i = 0; i < reNameSize; i++) {
		if (document.getElementById("reNameFlagId" + i).checked) {
			renameCount++;
		renameVillage = document.getElementById("reNamedVillIdNew" + i).value
		nameVillage = document.getElementById("reNamedVillId" + i).value;
		if (renameVillage == null || renameVillage.trim() == "") {
			alert('Please enter ReName of Village ' + renameVillage);
			lock = false;
			break;
		}else
		{
			if(renameVillage.replace(/ /g,'').toUpperCase() == nameVillage.replace(/ /g,'').toUpperCase() || renameVillage.replace(/ /g,'').toUpperCase() == villageName.replace(/ /g,'').toUpperCase()){
				alert('Village name should be unique.('+renameVillage+")");
				lock = false;
				break;
			}
			else{
				
				for(j=0;j<reNameSize;j++){
					compareVillage = document.getElementById("reNamedVillIdNew" + j).value
					if(i!=j && renameVillage.replace(/ /g,'').toUpperCase() == compareVillage.replace(/ /g,'').toUpperCase()){
						alert('Village name should be unique.('+renameVillage+")");
						lock = false;
						break;
					}
				}
			}
			
		}
		
      if(lock){
    	  var subdisid = document.getElementById("ddSubdistrict").value;
  		lgdDwrVillageService.VilageExist(subdisid, renameVillage, {
  			async : false,
  			callback : function(data) {
  				if (data=='P') {
  					lock = false;
  					alert("Village name should be unique.("+renameVillage+")");	
  				
  				}
  				else if(data == 'S'){
  					lock = false;	
  					alert("Village name should be unique.("+renameVillage+")");
  					
  				}
  			}			
  		});
      }
		
	}
		
		if(lock==false){
			break;
		}
		
	}
	if(renameCount==0){
			alert("Please Rename Atleast One Village or Select No Option");
			lock = false;
	}
	return lock;
	
};
