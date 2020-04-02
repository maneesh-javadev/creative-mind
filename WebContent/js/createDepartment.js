function hidedivValues() {
	hidelevels();
	hideRadiosAtStLvl();
	divStateAtDistrictlvl();
	divDistrictAtDistrictlvl();
	divDistrictAtSubDistrictlvl();
	divSubDistrictAtSubDistrictlvl();
	divDistrictAtVillagelvl();
	divSubDistrictAtVillagelvl();
	divVillageAtVillagelvl();
	divSpecificState();

}

function hideRadiosAtStLvl() {
	var rdSpecificState = document.getElementById('rdSpecificState');
	if (rdSpecificState.checked) {
		document.getElementById('divSpecificState').style.display = 'block';
	} else {
		document.getElementById('divSpecificState').style.display = 'none';
	}

}

function alphabetValidate(text) {

	for ( var k = 0; k < text.length; k++) {
		var char1 = text.charAt(k);
		var cc = char1.charCodeAt(0);

		if ((cc >= 65) && (cc <= 90) || (cc >= 97) && (cc <= 122) || (cc == 32)) {
		} else {
			return false;
		}
	}

	return true;
}

function hidelevels() {
	var id = document.getElementById('ddlocdepartment').value;

	if (id == 'S') {
		document.getElementById('divStatelvl').style.display = 'block';
		document.getElementById('divDistrictlvl').style.display = 'none';
		document.getElementById('divSubdistrictlvl').style.display = 'none';
		document.getElementById('divVillagelvl').style.display = 'none';
	} else if (id == 'D') {
		document.getElementById('divStatelvl').style.display = 'none';
		document.getElementById('divDistrictlvl').style.display = 'block';
		document.getElementById('divSubdistrictlvl').style.display = 'none';
		document.getElementById('divVillagelvl').style.display = 'none';
	} else if (id == 'T') {
		document.getElementById('divStatelvl').style.display = 'none';
		document.getElementById('divDistrictlvl').style.display = 'none';
		document.getElementById('divSubdistrictlvl').style.display = 'block';
		document.getElementById('divVillagelvl').style.display = 'none';
	} else if (id == 'V') {
		document.getElementById('divStatelvl').style.display = 'none';
		document.getElementById('divDistrictlvl').style.display = 'none';
		document.getElementById('divSubdistrictlvl').style.display = 'none';
		document.getElementById('divVillagelvl').style.display = 'block';
	} else {
		document.getElementById('divStatelvl').style.display = 'none';
		document.getElementById('divDistrictlvl').style.display = 'none';
		document.getElementById('divSubdistrictlvl').style.display = 'none';
		document.getElementById('divVillagelvl').style.display = 'none';
	}
}

function showlevels(id) {

	if (id == 'S') {
		document.getElementById('divStatelvl').style.display = 'block';
		document.getElementById('divDistrictlvl').style.display = 'none';
		document.getElementById('divSubdistrictlvl').style.display = 'none';
		document.getElementById('divVillagelvl').style.display = 'none';
	} else if (id == 'D') {
		document.getElementById('divStatelvl').style.display = 'none';
		document.getElementById('divDistrictlvl').style.display = 'block';
		document.getElementById('divSubdistrictlvl').style.display = 'none';
		document.getElementById('divVillagelvl').style.display = 'none';
	} else if (id == 'T') {
		document.getElementById('divStatelvl').style.display = 'none';
		document.getElementById('divDistrictlvl').style.display = 'none';
		document.getElementById('divSubdistrictlvl').style.display = 'block';
		document.getElementById('divVillagelvl').style.display = 'none';
	} else if (id == 'V') {
		document.getElementById('divStatelvl').style.display = 'none';
		document.getElementById('divDistrictlvl').style.display = 'none';
		document.getElementById('divSubdistrictlvl').style.display = 'none';
		document.getElementById('divVillagelvl').style.display = 'block';
	} else {
		document.getElementById('divStatelvl').style.display = 'none';
		document.getElementById('divDistrictlvl').style.display = 'none';
		document.getElementById('divSubdistrictlvl').style.display = 'none';
		document.getElementById('divVillagelvl').style.display = 'none';
	}

}

function divSpecificState() {
	var rdSpecificState = document.getElementById('rdSpecificState');

	if (rdSpecificState.checked) {
		document.getElementById('divSpecificState').style.display = 'block';
	} else {
		document.getElementById('divSpecificState').style.display = 'none';
	}
}

function divStateAtDistrictlvl() {
	var rdAllStateDis = document.getElementById('rdAllStateDis');
	var rdSpecificStateDis = document.getElementById('rdSpecificStateDis');

	if (rdAllStateDis.checked) {
		document.getElementById('divspecificDistrict').style.display = 'none';
		document.getElementById('dstPart').style.display = 'none';
	}
	if (rdSpecificStateDis.checked) {
		document.getElementById('dstPart').style.display = 'none';
		document.getElementById('divSpecificStateDist').style.display = 'block';
		document.getElementById('DistrictChkFull').checked = false;
		document.getElementById('DistrictChkPart').checked = false;
		document.getElementById('TblFull').style.display = 'none';
		document.getElementById('divspecificDistrict').style.display = 'none';
	} else {
		document.getElementById('divSpecificStateDist').style.display = 'none';
	}
}

function DistrictFullPart() {
	var obj1 = document.getElementById('DistrictChkFull');
	var obj2 = document.getElementById('DistrictChkPart');
	var selObj = document.getElementById('ddTargetState');
	var stateList = null;

	if (obj1.checked) {
		document.getElementById('TblFull').style.display = 'block';
		document.getElementById('dstPart').style.display = 'block';
	}
	if (!obj1.checked) {
		document.getElementById('TblFull').style.display = 'none';
		document.getElementById('dstPart').style.display = 'none';
	}
	if (obj2.checked) {
		if (selObj.value > 0) {
			for ( var i = 0; i < selObj.options.length; i++) {
				if (stateList == null)
					stateList = selObj.options[i].value + ",";
				else
					stateList += selObj.options[i].value + ",";
				selObj.options[i].selected = true;
			}
			stateList = stateList.substring(0, stateList.length - 1);
			getStateList(stateList);
			document.getElementById('divspecificDistrict').style.display = 'block';
		} else {
			alert("Kindly select the states from the list first, afterwords click on this option.");
			obj2.checked = false;
		}
	}
	if (!obj2.checked) {
		document.getElementById('divspecificDistrict').style.display = 'none';
	}
}

function SubDistrictFullPart() {
	var obj1 = document.getElementById('DistrictChkFull');
	var obj2 = document.getElementById('DistrictChkPart');
	var obj3 = document.getElementById('StateChkFull');
	var selObj1 = document.getElementById('ddTargetState');
	var stateList = null;

	if (obj1.checked) {
		if (selObj1.value > 0) {
			for ( var i = 0; i < selObj1.options.length; i++) {
				if (stateList == null)
					stateList = selObj1.options[i].value + ",";
				else
					stateList += selObj1.options[i].value + ",";
				selObj1.options[i].selected = true;
			}
			stateList = stateList.substring(0, stateList.length - 1);
			getStateList(stateList);
			document.getElementById('TblDFull').style.display = 'block';
		} else {
			alert("Kindly select the states from the list first, afterwords click on this option.");
			obj1.checked = false;
		}
	}
	if (!obj1.checked) {
		document.getElementById('TblDFull').style.display = 'none';
	}
	if (obj2.checked) {
		if (selObj1.value > 0) {
			for ( var i = 0; i < selObj1.options.length; i++) {
				if (stateList == null)
					stateList = selObj1.options[i].value + ",";
				else
					stateList += selObj1.options[i].value + ",";
				selObj1.options[i].selected = true;
			}
			stateList = stateList.substring(0, stateList.length - 1);
			getStateListSDLvl(stateList);
			document.getElementById('divspecificSubDistrict').style.display = 'block';
		}
	}
	if (!obj2.checked) {
		document.getElementById('divspecificSubDistrict').style.display = 'none';
	}
	if (obj3.checked) {
		document.getElementById('TblSFull').style.display = 'block';
	}
	if (!obj3.checked) {
		document.getElementById('TblSFull').style.display = 'none';
	}
}

function VillageFullPart() {
	var obj1 = document.getElementById('DistrictChkFull');
	var obj2 = document.getElementById('SubDistrictChkFull');
	var obj3 = document.getElementById('StateChkFull');
	var obj4 = document.getElementById('SubDistrictChkPart');
	var selObj = document.getElementById('ddTargetState');
	var stateList = null;

	if (obj1.checked) {
		if (selObj.value > 0) {
			for ( var i = 0; i < selObj.options.length; i++) {
				if (stateList == null)
					stateList = selObj.options[i].value + ",";
				else
					stateList += selObj.options[i].value + ",";
				selObj.options[i].selected = true;
			}
			stateList = stateList.substring(0, stateList.length - 1);
			getStateList(stateList);
			document.getElementById('TblDFull').style.display = 'block';
		} else {
			alert("Kindly select the states from the list first, afterwords click on this option.");
			obj1.checked = false;
		}
	}
	if (!obj1.checked) {
		document.getElementById('TblDFull').style.display = 'none';
	}
	if (obj2.checked) {
		stateList = null;
		if (selObj.value > 0) {
			for ( var i = 0; i < selObj.options.length; i++) {
				if (stateList == null)
					stateList = selObj.options[i].value + ",";
				else
					stateList += selObj.options[i].value + ",";
				selObj.options[i].selected = true;
			}
			stateList = stateList.substring(0, stateList.length - 1);
			getStateListSDLvl(stateList);
			document.getElementById('TblSDFull').style.display = 'block';
		}
	}
	if (!obj2.checked) {
		document.getElementById('TblSDFull').style.display = 'none';
	}
	if (obj3.checked) {
		document.getElementById('TblSFull').style.display = 'block';
	}
	if (!obj3.checked) {
		document.getElementById('TblSFull').style.display = 'none';
	}
	if (obj4.checked) {
		stateList = null;
		if (selObj.value > 0) {
			for ( var i = 0; i < selObj.options.length; i++) {
				if (stateList == null)
					stateList = selObj.options[i].value + ",";
				else
					stateList += selObj.options[i].value + ",";
				selObj.options[i].selected = true;
			}
			stateList = stateList.substring(0, stateList.length - 1);
			getStateListVillLvl(stateList);
			document.getElementById('TblSDPart').style.display = 'block';
		}
	}
	if (!obj4.checked) {
		document.getElementById('TblSDPart').style.display = 'none';
	}
}

function showRadioOptAtDisLvl(id) {

	if (id == 0) {
		document.getElementById('trDistrictOption').style.visibility = 'hidden';
	} else {
		document.getElementById('trDistrictOption').style.visibility = 'visible';
	}
}

function divDistrictAtDistrictlvl() {
	getdistrictListAtStatelvlListBox(document.getElementById('stateName')[document.getElementById('stateName').selectedIndex].value);
	/*
	 * var rdSpecificDistrict =document.getElementById('rdSpecificDistrict');
	 * 
	 * if(rdSpecificDistrict.checked) {
	 * document.getElementById('divspecificDistrict').style.display='block';
	 * getdistrictListAtStatelvl(document.getElementById('stateNameDist')[document.getElementById('stateNameDist').selectedIndex].value); }
	 * else {
	 * document.getElementById('divspecificDistrict').style.display='none'; }
	 */
}

function divDistrictAtDistrictlvlforSD() {
	getdistrictListAtStatelvl(document.getElementById('stateNameDistforSD')[document.getElementById('stateNameDistforSD').selectedIndex].value);
}

function divDistrictAtSubDistrictlvl() {
	var rdAllDistrictSub = document.getElementById('rdAllDistrictSub');
	var rdSpecificDistrictSub = document.getElementById('rdSpecificDistrictSub');

	if (rdAllDistrictSub.checked) {
		document.getElementById('divSpecificDistSub').style.display = 'none';
		document.getElementById('StateChkFull').checked = false;
		document.getElementById('DistrictChkFull').checked = false;
		document.getElementById('DistrictChkPart').checked = false;
		document.getElementById('TblSFull').style.display = 'none';
		document.getElementById('TblDFull').style.display = 'none';
		document.getElementById('divspecificSubDistrict').style.display = 'none';
	}
	if (rdSpecificDistrictSub.checked) {
		document.getElementById('divSpecificDistSub').style.display = 'block';
		document.getElementById('StateChkFull').checked = false;
		document.getElementById('DistrictChkFull').checked = false;
		document.getElementById('DistrictChkPart').checked = false;
		document.getElementById('TblSFull').style.display = 'none';
		document.getElementById('TblDFull').style.display = 'none';
		document.getElementById('divspecificSubDistrict').style.display = 'none';
	} else {
		document.getElementById('divSpecificDistSub').style.display = 'none';
	}
}

function divDistrictAtVillagelvl() {
	var rdAllVillages = document.getElementById('rdAllVillages');
	var rdSpecificVillages = document.getElementById('rdSpecificVillages');

	if (rdAllVillages.checked) {
		document.getElementById('divSpecificVillage').style.display = 'none';
		document.getElementById('StateChkFull').checked = false;
		document.getElementById('DistrictChkFull').checked = false;
		document.getElementById('SubDistrictChkFull').checked = false;
		document.getElementById('SubDistrictChkPart').checked = false;
		document.getElementById('TblSFull').style.display = 'none';
		document.getElementById('TblDFull').style.display = 'none';
		document.getElementById('TblSDFull').style.display = 'none';
		document.getElementById('TblSDPart').style.display = 'none';
	}
	if (rdSpecificVillages.checked) {
		document.getElementById('divSpecificVillage').style.display = 'block';
		document.getElementById('StateChkFull').checked = false;
		document.getElementById('DistrictChkFull').checked = false;
		document.getElementById('SubDistrictChkFull').checked = false;
		document.getElementById('SubDistrictChkPart').checked = false;
		document.getElementById('TblSFull').style.display = 'none';
		document.getElementById('TblDFull').style.display = 'none';
		document.getElementById('TblSDFull').style.display = 'none';
		document.getElementById('TblSDPart').style.display = 'none';
	} else {
		document.getElementById('divSpecificDistSub').style.display = 'none';
	}
}
function showRadioOptAtSubDisLvl(id) {
	if (id == 0) {
		document.getElementById('tr_SubDistrict').style.visibility = 'hidden';
	} else {
		document.getElementById('tr_SubDistrict').style.visibility = 'visible';
	}
}
function showRadioOptAtSubDisVillLvl(id) {
	if (id == 0) {
		document.getElementById('tr_Vill_SubDistrict').style.visibility = 'hidden';
	} else {
		document.getElementById('tr_Vill_SubDistrict').style.visibility = 'visible';
	}
}
function showRadioOptAtVillageLvl(id) {
	if (id == 0) {
		document.getElementById('tr_VillageLevel').style.visibility = 'hidden';
	} else {
		document.getElementById('tr_VillageLevel').style.visibility = 'visible';
	}
}

function divSubDistrictAtSubDistrictlvl() {
	getSubdistrictAtSubDistrictlvl(document.getElementById('distNameSubDist')[document.getElementById('distNameSubDist').selectedIndex].value);
	/*
	 * var rdSpecificSubdistrict
	 * =document.getElementById('rdSpecificSubdistrict');
	 * 
	 * if(rdSpecificSubdistrict.checked) {
	 * document.getElementById('divspecificSubDistrict').style.display='block';
	 * getSubdistrictAtSubDistrictlvl(document.getElementById('distNameSubDist')[document.getElementById('distNameSubDist').selectedIndex].value); }
	 * else {
	 * document.getElementById('divspecificSubDistrict').style.display='none'; }
	 */
}

// /////////code for clear/////////////////////
function onClear() {
	$(".frmfield").each(function() {
		$(this).val("");
		$(this).removeClass("error_fld");
		$(".error").hide();
		$(".helpBox").hide();
	});

	$(".combofield").each(function() {
		$(this).val("");
		$(this).removeClass("error_fld");
		$(".error").hide();
		$(".helpBox").hide();
	});

}
// /////////code for clear/////////////////////

function divSubDistrictAtVillagelvl() {
	var rdAllSubDistatVL = document.getElementById('rdAllSubDistatVL');
	var rdSpecificSubDistatVL = document.getElementById('rdSpecificSubDistatVL');

	if (rdAllSubDistatVL.checked) {
		document.getElementById('divAllSubDistrictVill').style.display = 'block';
	} else {
		document.getElementById('divAllSubDistrictVill').style.display = 'none';
	}
	if (rdSpecificSubDistatVL.checked) {
		document.getElementById('divSpecificSubDistVill').style.display = 'block';
		getSubdistrictAtVilllvl(document.getElementById('distNameVill')[document.getElementById('distNameVill').selectedIndex].value);
	} else {
		document.getElementById('divSpecificSubDistVill').style.display = 'none';
	}
}

function divVillageAtVillagelvl() {
	var rdSpecificVillagesatVL = document.getElementById('rdSpecificVillagesatVL');

	if (rdSpecificVillagesatVL.checked) {
		document.getElementById('divSpecificSDVillages').style.display = 'block';
		getVillageListAtVillageLvl(document.getElementById('ddSubdistrictAtVillLvl')[document.getElementById('ddSubdistrictAtVillLvl').selectedIndex].value);
	} else {
		document.getElementById('divSpecificSDVillages').style.display = 'none';
	}
}

// DWR Implementation

function getdistrictListAtStatelvl(id) {
	lgdDwrDistrictService.getDistrictList(id, {
		callback : handleDistrictSuccess,
		errorHandler : handleDistrictError
	});

}

function handleDistrictSuccess(data) {
	// Assigns data to result id
	var fieldId = 'dddistrictAtStateLvl';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getdistrictListAtSDlvl(id) {
	lgdDwrDistrictService.getDistrictList(id, {
		callback : handleDistrictSuccessSDLevel,
		errorHandler : handleDistrictError
	});

}

function handleDistrictSuccessSDLevel(data) {
	// Assigns data to result id
	var fieldId = 'dddistrictAtSDLvl';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var dataq = [ {
		name : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "name");

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getdistrictListAtVilllvl(id) {
	lgdDwrDistrictService.getDistrictList(id, {
		callback : handleDistrictSuccessVillLevel,
		errorHandler : handleDistrictError
	});

}

function handleDistrictSuccessVillLevel(data) {
	// Assigns data to result id
	var fieldId = 'dddistrictAtVillLvl';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var dataq = [ {
		name : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "name");

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleDistrictError() {
	// Show a popup message
	alert("No data found!");
}

function getSubdistrictAtSubDistrictlvl(id) {
	lgdDwrSubDistrictService.getSubDistrictListbyDistrictCodewithDistrictName(id, {
		callback : handleSubDistrictSuccess,
		errorHandler : handleSubDistrictError
	});

}

function handleSubDistrictSuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddSubdistrictAtSubDistrictLvl';
	var valueText = 'subdistrictCode';
	var nameText = 'subdistrictNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getSubdistrictAtVilllvl(id) {
	var selObj = document.getElementById('ddTargetDistrictSDLvl');
	var subDistrictList = null;
	if (selObj.value > 0) {
		for ( var i = 0; i < selObj.options.length; i++) {
			if (subDistrictList == null)
				subDistrictList = selObj.options[i].value + ",";
			else
				subDistrictList += selObj.options[i].value + ",";
			selObj.options[i].selected = true;
		}
		subDistrictList = subDistrictList.substring(0, subDistrictList.length - 1);
	} else {
		alert("Kindly select the sub districts from the list in above section first, afterwords click on this option.");
	}
	lgdDwrSubDistrictService.getSubDistrictListbyDistrictCode(id, subDistrictList, {
		callback : handleSubDistrictSuccessVillLevel,
		errorHandler : handleSubDistrictError
	});

}

function handleSubDistrictSuccessVillLevel(data) {
	// Assigns data to result id
	var fieldId = 'ddSubdistrictAtVillLvl';
	var valueText = 'subdistrictCode';
	var nameText = 'subdistrictNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		select : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "select");
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleSubDistrictError() {
	// Show a popup message
	alert("No data found!");
}

function getVillageListAtVillageLvl(id) {
	lgdDwrVillageService.getVillageListbySubDistrictWithSDName(id, {
		callback : handleVillageSuccess,
		errorHandler : handleVillageError
	});

}

function handleVillageSuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddVillageAtVillLvl';
	var valueText = 'villageCode';
	var nameText = 'villageNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleVillageError() {
	// Show a popup message
	alert("No data found!");
}

function validatePhoneNo() {
	var key = event.keyCode;

	if ((key >= 48) && (key <= 58) || (key == 45)) {

	} else {
		alert("Phone no. should contain [0,9] and �-� only");
		event.returnValue = false;
	}
}

function getStateList(ids) {
	lgdDwrStateService.getAllNotInStates(ids, {
		callback : handleStateSuccess,
		errorHandler : handleStateError
	});
}

function handleStateSuccess(data) {
	var fieldId = 'stateName';
	var valueText = 'stateCode';
	var nameText = 'stateNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}
function getStateListSDLvl(ids) {
	lgdDwrStateService.getAllNotInStates(ids, {
		callback : handleStateSuccessSDLvl,
		errorHandler : handleStateError
	});
}

function handleStateSuccessSDLvl(data) {
	var fieldId = 'stateNameDistforSD';
	var valueText = 'stateCode';
	var nameText = 'stateNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		select : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "select");
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getStateListVillLvl(ids) {
	lgdDwrStateService.getAllNotInStates(ids, {
		callback : handleStateSuccessVillLvl,
		errorHandler : handleStateError
	});
}

function handleStateSuccessVillLvl(data) {
	var fieldId = 'stateNameDistforVill';
	var valueText = 'stateCode';
	var nameText = 'stateNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		select : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "select");
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}
function handleStateError() {
	alert("No data found!");
}
function getNotInDistrictList(id) {
	var selObj = document.getElementById('ddTargetDistrict');
	var districtList = null;
	if (selObj.value > 0) {
		for ( var i = 0; i < selObj.options.length; i++) {
			if (districtList == null)
				districtList = selObj.options[i].value + ",";
			else
				districtList += selObj.options[i].value + ",";
			selObj.options[i].selected = true;
		}
		districtList = districtList.substring(0, districtList.length - 1);
	} else {
		alert("Kindly select the districts from the list in above section first, afterwords click on this option.");
	}
	lgdDwrDistrictService.getDistrictListbyStateCode(id, districtList, {
		callback : handleDistrictSuccessSDLvl,
		errorHandler : handleDistrictError
	});

}
function handleDistrictSuccessSDLvl(data) {
	var fieldId = 'dddistrictAtSDLvl';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		select : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "select");
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}
function getNotInDistrictListVillLvl(id) {
	var selObj = document.getElementById('ddTargetDistrict');
	var districtList = null;
	if (selObj.value > 0) {
		for ( var i = 0; i < selObj.options.length; i++) {
			if (districtList == null)
				districtList = selObj.options[i].value + ",";
			else
				districtList += selObj.options[i].value + ",";
			selObj.options[i].selected = true;
		}
		districtList = districtList.substring(0, districtList.length - 1);
	} else {
		alert("Kindly select the districts from the list in above section first, afterwords click on this option.");
	}
	lgdDwrDistrictService.getDistrictListbyStateCode(id, districtList, {
		callback : handleDistrictSuccessVillLvl,
		errorHandler : handleDistrictError
	});

}
function handleDistrictSuccessVillLvl(data) {
	var fieldId = 'dddistrictAtVillLvl';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		select : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "select");
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getDepartmentList1(id) {
	lgdDwrOrganizationService.getDepartmentListforOrg(id, {
		callback : handleDepartmentSuccess1,
		errorHandler : handleDepartmentError1
	});
}

function handleDepartmentSuccess1(data) {
	var fieldId = 'deptOrgCode';
	var valueText = 'orgCode';
	var nameText = 'orgName';

	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		select : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "select");
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}
function handleDepartmentError1() {
	alert("No data found!");
}

function getdistrictListAtStatelvlListBox(id) {
	lgdDwrDistrictService.getDistrictListbyStateCodeForListBox(id, {
		callback : handleDistrictSuccess,
		errorHandler : handleDistrictError
	});

}

function getBlockList(id) {
	lgdDwrBlockService.getBlockList(id, {
		callback : handleBlockSuccess,
		errorHandler : handleDepartmentError1
	});
}

function handleBlockSuccess(data) {
	var fieldId = 'ddSubdistrictAtSubDistrictLvl';
	var valueText = 'blockCode';
	var nameText = 'blockNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}
