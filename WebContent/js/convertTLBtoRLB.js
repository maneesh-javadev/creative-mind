function getHideShowTLBToRLBList(id) {
	if (id != "" && id != "0") {
		var temp = id.split(":");

		var id1 = temp[0];
		var id2 = temp[1];

		switch (id2) {
		case 'D':
			// getExitingTraditioanalTypeNameAsPRIforD(id2);
			document.getElementById('divTraditionalDistrictPanchayat').style.visibility = 'visible';
			document.getElementById('divTraditionalDistrictPanchayat').style.display = 'block';
			document.getElementById('divTraditionalIntermediatePanchayat').style.display = 'none';
			document.getElementById('divTraditionalVillagePanchayat').style.display = 'none';
			break;
		case 'I':
			// getExitingTraditioanalTypeNameAsPRIforI(id2);
			document.getElementById('divTraditionalIntermediatePanchayat').style.visibility = 'visible';
			document.getElementById('divTraditionalIntermediatePanchayat').style.display = 'block';
			document.getElementById('divTraditionalDistrictPanchayat').style.display = 'none';
			document.getElementById('divTraditionalVillagePanchayat').style.display = 'none';
			break;
		case 'V':
			// getExitingPRITypeNameAsTraditionalforV(id2);
			document.getElementById('divTraditionalVillagePanchayat').style.visibility = 'visible';
			document.getElementById('divTraditionalVillagePanchayat').style.display = 'block';
			document.getElementById('divTraditionalDistrictPanchayat').style.display = 'none';
			document.getElementById('divTraditionalIntermediatePanchayat').style.display = 'none';
			break;
		default:
			document.getElementById('divTraditionalDistrictPanchayat').style.display = 'none';
			document.getElementById('divTraditionalIntermediatePanchayat').style.display = 'none';
			document.getElementById('divTraditionalVillagePanchayat').style.display = 'none';
		}

	} else {
		document.getElementById('divTraditionalDistrictPanchayat').style.display = 'none';
		document.getElementById('divTraditionalIntermediatePanchayat').style.display = 'none';
		document.getElementById('divTraditionalVillagePanchayat').style.display = 'none';
	}
}

function loadConvertTLBRLBPage() {
	document.getElementById('rdmergeTLBtoRLB').checked = false;
	document.getElementById('rddeclarenewRLB').checked = false;
	document.getElementById('divSaveButtons').style.display = 'none';
	document.getElementById('divPreviousButtons').style.display = 'block';
	document.getElementById('divTraditionalDistrictPanchayat').style.display = 'none';
}

function validateFirstAllTLBtoRLB() {
	selectTLBtoRLB();

	var errors = new Array();
	var error = false;

	var ddLBTypeCode = document.getElementById('ddLgdLBType').value;
	var temp = ddLBTypeCode.split(":");
	var id2 = temp[1];

	errors[0] = !validateLBtypeName();
	errors[1] = !validateDPList();
	errors[2] = !validateIPList();
	errors[3] = !validateVPList();
	for ( var i = 0; i < 4; i++) {
		if (errors[i] == true) {
			error = true;
			break;
		}
	}

	if (error == true) {
		showClientSideError();
		return false;
	} else {
		// showLoadingImage();
		clickOnProceed();
		return true;
	}
	return false;
}

function validateLBtypeName() {
	var ddLgdLBType = document.getElementById('ddLgdLBType');
	if (ddLgdLBType.selectedIndex == 0) {
		$("#ddLgdLBType_error").show();
		return false;
	}

	$("#ddLgdLBType_error").hide();
	return true;
}

function validateDPList() {
	var ddDPList = document.getElementById('ddLgdLBDistListAtVillageCA');
	if (ddDPList.selectedIndex == 0) {
		$("#ddLgdLBDistListAtVillageCA_error").show();
		return false;
	}
	$("#ddLgdLBDistListAtVillageCA_error").hide();
	return true;
}

function validateIPList() {
	var ddIPList = document.getElementById('ddLgdLBInterListAtVillageCA');
	if (ddIPList.selectedIndex == 0) {
		$("#ddLgdLBInterListAtVillageCA_error").show();
		return false;
	}
	$("#ddLgdLBInterListAtVillageCA_error").hide();
	return true;
}

function validateVPList() {
	if (document.getElementById('ddLgdLBVillageDestAtVillageCA').value == "") {
		$("#ddLgdLBVillageDestAtVillageCA_error").show();
		return false;
	}
	$("#dddLgdLBVillageDestAtVillageCA_error").hide();
	return true;
}

function selectTLBtoRLB() {
	var selDistPDestList = document.getElementById('ddLgdLBDistPDestList');
	var selInterDestList = document.getElementById('ddIntermediateLbDesteL');
	var selIntermediateLbDesteL = document.getElementById('ddIntermediateLbDesteL');
	var selLBVillageInterCAreaDestL = document.getElementById('ddLgdLBVillageInterCAreaDestL');
	var selLBVillageCAreaDestL = document.getElementById('ddLgdLBVillageCAreaDestL');
	var selVillageList = document.getElementById('ddLgdLBVillageDestAtVillageCA');

	for ( var m = 0; m < selDistPDestList.options.length; m++) {
		selDistPDestList.options[m].selected = true;
	}
	for ( var m = 0; m < selInterDestList.options.length; m++) {
		selInterDestList.options[m].selected = true;
	}

	for ( var m = 0; m < selIntermediateLbDesteL.options.length; m++) {
		selIntermediateLbDesteL.options[m].selected = true;
	}
	for ( var m = 0; m < selLBVillageInterCAreaDestL.options.length; m++) {
		selLBVillageInterCAreaDestL.options[m].selected = true;
	}
	for ( var m = 0; m < selLBVillageCAreaDestL.options.length; m++) {
		selLBVillageCAreaDestL.options[m].selected = true;
	}
	for ( var m = 0; m < selVillageList.options.length; m++) {
		selVillageList.options[m].selected = true;
	}
}

function clickOnProceed() {
	document.getElementById('divFirstPanel').style.display = 'none';
	document.getElementById('divSecondPanel').style.display = 'block';
	document.getElementById('divmergeRLB').style.display = 'none';
	// document.getElementById('divgovtOrder').style.display = 'none';
	document.getElementById('divdeclareNewRLB').style.display = 'none';
	if (document.getElementById('rddeclarenewRLB').checked == true) {
		document.getElementById('divmergeRLB').style.display = 'none';
		document.getElementById('divdeclareNewRLB').style.display = 'block';
		// document.getElementById('divgovtOrder').style.display = 'block';
	} else if (document.getElementById('rdmergeTLBtoRLB').checked == true) {
		document.getElementById('divmergeRLB').style.display = 'block';
		document.getElementById('divdeclareNewRLB').style.display = 'none';
		// document.getElementById('divgovtOrder').style.display = 'block';
	}
}

function radioClick(id, typecode) {
	if (typecode) {
		var temp = typecode.split(":");
		var typeCode1 = temp[0];
		var typeCode2 = temp[1];
		/*
		 * $("#OrderNo_error").hide(); $("#OrderDate_error").hide();
		 * $("#EffectiveDate_error").hide(); $("#GazPubDate_error").hide();
		 * $("#filGovernmentOrder_error").hide();
		 */
		if (id == 'M') {
			document.getElementById('divdeclareNewRLB').style.display = 'none';
			document.getElementById('divmergeRLB').style.display = 'block';
			document.getElementById('divSaveButtons').style.display = 'block';
			document.getElementById('divPreviousButtons').style.display = 'none';
			document.getElementById('rdmergeTLBtoRLB').checked = true;
			document.getElementById('rddeclarenewRLB').checked = false;
			document.getElementById('divgovtOrder').style.display = 'block';
		} else if (id == 'N') {
			document.getElementById('divmergeRLB').style.display = 'none';
			document.getElementById('divdeclareNewRLB').style.display = 'block';
			document.getElementById('divPreviousButtons').style.display = 'none';
			document.getElementById('divSaveButtons').style.display = 'block';
			document.getElementById('rdmergeTLBtoRLB').checked = false;
			document.getElementById('rddeclarenewRLB').checked = true;
			document.getElementById('divgovtOrder').style.display = 'block';
		}
		getRuralTypeList(typeCode2, id);
	}

}

function getRuralTypeList(typecode, id) {
	if (id == 'M') {
		getRuralTypeListforExist(typecode);
	} else if (id == 'N') {
		getRuralTypeListforNew(typecode);
	}
}

function getRuralTypeListforExist(typecode) {

	var category = 'P';
	lgdDwrlocalBodyService.getTypeListbylevel(typecode, category, {
		callback : getRuralTypeListforExistSuccess,
		errorHandler : getRuralTypeListforExistError
	});
}

function getRuralTypeListforExistSuccess(data) {
	var valueText = 'typeCode';
	var fieldId = 'ddRuralLBType';
	var nameText = 'localBodyTypeName';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddRuralLBType');
	st.add(new Option('Select Rural Local Body Type', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getRuralTypeListforExistError() {
	alert("No data found!");
}

function getRuralTypeListforNew(typecode) {
	var category = 'P';
	lgdDwrlocalBodyService.getTypeListbylevel(typecode, category, {
		callback : getRuralTypeListforNewSuccess,
		errorHandler : getRuralTypeListforNewError
	});
}

function getRuralTypeListforNewSuccess(data) {
	var valueText = 'typeCode';
	var fieldId = 'ddRuralLocalBodyTypeNew';
	var nameText = 'localBodyTypeName';
	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddRuralLocalBodyTypeNew');
	st.add(new Option('Select Rural Local Body Type', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getRuralTypeListforNewError() {
	alert("No data found!");
}

function validateFinalAllTLBtoRLB() {
	selectTLBtoRLB();
	var rdmergeTLBtoRLB = document.getElementById('rdmergeTLBtoRLB');
	var rddeclarenewRLB = document.getElementById('rddeclarenewRLB');

	var errors = new Array();
	var error = false;

	/*
	 * var ddLBTypeCode= document.getElementById('ddLgdLBType').value; var temp =
	 * ddLBTypeCode.split(":"); var id2 = temp[1];
	 */
	errors[0] = !validateRadio();

	// alert("rddeclarenewTLB "+rddeclarenewTLB);
	if (rddeclarenewRLB.checked) {
		errors[1] = !validateNewTLBName();
		errors[2] = !validateTLBTypeNew();
		errors[3] = !validateDPTLBTypeNew();
		errors[4] = !validateIPTypeNew();
	} else if (rdmergeTLBtoRLB.checked) {
		errors[5] = !validateTLBTypeMerge();
		errors[6] = !validateDPTLBMerge();
		errors[7] = !validateIPTLBMerge();
		errors[8] = !validateVPTLBMerge();
	}

	for ( var i = 0; i < 9; i++) {
		if (errors[i] == true) {
			error = true;
			break;
		}
	}

	if (error == true) {
		showClientSideError();
		return false;
	} else {
		showLoadingImage();
		// clickOnProceed();
		return true;
	}
	return false;
}

function validateNewTLBName() {
	if (document.getElementById('txtlocalBodyNameInEn').value == "") {
		$("#txtlocalBodyNameInEn_error").show();
		return false;
	}
	$("#txtlocalBodyNameInEn_error").hide();
	return true;
}

function validateTLBTypeNew() {
	var ddTLBTypeNew = document.getElementById('ddRuralLocalBodyTypeNew');
	if (ddTLBTypeNew.selectedIndex == 0) {
		$("#ddRuralLocalBodyTypeNew_error").show();
		return false;
	}
	$("#dddRuralLocalBodyTypeNew_error").hide();
	return true;
}

function validateDPTLBTypeNew() {
	var ddIPList = document.getElementById('ddlgdLBDistrictAtVillageforNew');
	if (ddIPList.selectedIndex == 0) {
		$("#ddlgdLBDistrictAtVillageforNew_error").show();
		return false;
	}
	$("#ddlgdLBDistrictAtVillageforNew_error").hide();
	return true;
}

function validateIPTypeNew() {
	var ddIPList = document.getElementById('ddlgdLBInterAtVillageforNew');
	if (ddIPList.selectedIndex == 0) {
		$("#ddlgdLBInterAtVillageforNew_error").show();
		return false;
	}
	$("#ddlgdLBInterAtVillageforNew_error").hide();
	return true;
}

function validateTLBTypeMerge() {
	var ddTLBTypeMerge = document.getElementById('ddRuralLBType');
	if (ddTLBTypeMerge.selectedIndex == 0) {
		$("#ddRuralLBType_error").show();
		return false;
	}
	$("#ddRuralLBType_error").hide();
	return true;
}

function validateDPTLBMerge() {
	var ddIPList = document.getElementById('ddlgdLBDistrictAtVillageforExist');
	if (ddIPList.selectedIndex == 0) {
		$("#ddlgdLBDistrictAtVillageforExist_error").show();
		return false;
	}
	$("#ddlgdLBDistrictAtVillageforExist_error").hide();
	return true;
}

function validateIPTLBMerge() {
	var ddIPList = document.getElementById('ddlgdLBInterAtVillageforExist');
	if (ddIPList.selectedIndex == 0) {
		$("#ddlgdLBInterAtVillageforExist_error").show();
		return false;
	}
	$("#ddlgdLBInterAtVillageforExist_error").hide();
	return true;
}

function validateVPTLBMerge() {
	var ddIPList = document.getElementById('ddlgdLBVillagePanchayatforExist');
	if (ddIPList.selectedIndex == 0) {
		$("#ddlgdLBVillagePanchayatforExist_error").show();
		return false;
	}
	$("#ddlgdLBVillagePanchayatforExist_error").hide();
	return true;
}

function validateRadio() {
	var rdmergeTLBtoRLB = document.getElementById('rdmergeTLBtoRLB');
	var rddeclarenewRLB = document.getElementById('rddeclarenewRLB');
	if (!rdmergeTLBtoRLB.checked && !rddeclarenewRLB.checked) {
		$("#rdmergeTLBtoRLB_error").show();
		return false;
	}
	$("#rdmergeTLBtoRLB_error").hide();
	return true;
}

function gethideShowDivforExistRLB(id) {
	if (id != "" && id != "0") {
		var temp = id.split(":");

		var id1 = temp[0];
		var id2 = temp[1];

		switch (id2) {
		case 'D':
			document.getElementById('divDistrictPforExist').style.visibility = 'visible';
			document.getElementById('divDistrictPforExist').style.display = 'block';
			document.getElementById('divIntermediatePforExist').style.display = 'none';
			document.getElementById('divVillagePforExist').style.display = 'none';
			// getDistrictLBListbyTypeCodeforExistAtDistrict(id1);
			break;
		case 'I':
			document.getElementById('divIntermediatePforExist').style.visibility = 'visible';
			document.getElementById('divIntermediatePforExist').style.display = 'block';
			// document.getElementById('divDistrictPforExist').style.display =
			// 'none';
			document.getElementById('divVillagePforExist').style.display = 'none';
			// getDistrictLBListbyTypeCodeforExistAtInter(id1);
			break;
		case 'V':
			document.getElementById('divVillagePforExist').style.visibility = 'visible';
			document.getElementById('divVillagePforExist').style.display = 'block';
			// document.getElementById('divDistrictPforExist').style.display =
			// 'none';
			document.getElementById('divIntermediatePforExist').style.display = 'none';
			// getDistrictLBListbyTypeCodeforExistAtVillage(id1);
			break;

		default:
			document.getElementById('divVillagePforExist').style.display = 'none';
			// document.getElementById('divDistrictPforExist').style.display =
			// 'none';
			document.getElementById('divIntermediatePforExist').style.display = 'none';
		}
	} else {
		// document.getElementById('divVillagePforExist').style.display =
		// 'none';
		document.getElementById('divDistrictPforExist').style.display = 'none';
		document.getElementById('divIntermediatePforExist').style.display = 'none';
	}
}

/*
 * function getHideShowTLBToRuralList(id) { if (id != "" && id != "0") { var
 * temp = id.split(":");
 * 
 * var id1 = temp[0]; var id2 = temp[1];
 * 
 * switch (id2) { case 'D': //getExitingTraditioanalTypeNameAsPRIforD(id2);
 * document.getElementById('divTraditionalDistrictPanchayat').style.visibility='visible';
 * document.getElementById('divTraditionalDistrictPanchayat').style.display =
 * 'block';
 * document.getElementById('divTraditionalIntermediatePanchayat').style.display =
 * 'none';
 * document.getElementById('divTraditionalVillagePanchayat').style.display =
 * 'none'; break; case 'I': //getExitingTraditioanalTypeNameAsPRIforI(id2);
 * document.getElementById('divTraditionalIntermediatePanchayat').style.visibility =
 * 'visible';
 * document.getElementById('divTraditionalIntermediatePanchayat').style.display =
 * 'block';
 * document.getElementById('divTraditionalDistrictPanchayat').style.display =
 * 'none';
 * document.getElementById('divTraditionalVillagePanchayat').style.display =
 * 'none'; break; case 'V': //getExitingTraditioanalTypeNameAsPRIforV(id2);
 * document.getElementById('divTraditionalVillagePanchayat').style.visibility =
 * 'visible';
 * document.getElementById('divTraditionalVillagePanchayat').style.display =
 * 'block';
 * document.getElementById('divTraditionalDistrictPanchayat').style.display =
 * 'none';
 * document.getElementById('divTraditionalIntermediatePanchayat').style.display =
 * 'none'; break; default:
 * document.getElementById('divTraditionalDistrictPanchayat').style.display =
 * 'none';
 * document.getElementById('divTraditionalIntermediatePanchayat').style.display =
 * 'none';
 * document.getElementById('divTraditionalVillagePanchayat').style.display =
 * 'none'; } } else {
 * document.getElementById('divTraditionalDistrictPanchayat').style.display =
 * 'none';
 * document.getElementById('divTraditionalIntermediatePanchayat').style.display =
 * 'none';
 * document.getElementById('divTraditionalVillagePanchayat').style.display =
 * 'none'; } }
 */

function getHideShowTLBToRuralList(id) {
	if (id != "" && id != "0") {
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];

		switch (id2) {
		case 'D':
			document.getElementById('divDistrictPforNew').style.visibility = 'visible';
			document.getElementById('divDistrictPforNew').style.display = 'block';
			document.getElementById('divLgdSelIntermediatePforNew').style.display = 'none';
			document.getElementById('divLgdVillagePforNew').style.display = 'none';
			// getDistrictLBListbyTypeCodeforExistAtDistrict(id1);
			break;
		case 'I':
			document.getElementById('divLgdSelIntermediatePforNew').style.visibility = 'visible';
			document.getElementById('divLgdSelIntermediatePforNew').style.display = 'block';
			document.getElementById('divLgdVillagePforNew').style.display = 'none';
			document.getElementById('divDistrictPforNew').style.display = 'none';
			// getDistrictPanchayatListbyTypeCodeforNewAtInter(id1);

			break;
		case 'V':
			document.getElementById('divLgdVillagePforNew').style.visibility = 'visible';
			document.getElementById('divLgdVillagePforNew').style.display = 'block';
			document.getElementById('divLgdSelIntermediatePforNew').style.display = 'none';
			document.getElementById('divDistrictPforNew').style.display = 'none';
			// getDistrictPanchayatListbyTypeCodeforNewAtVillage(id1);
			break;

		default:
			document.getElementById('divDistrictPforNew').style.display = 'none';
			document.getElementById('divLgdVillagePforNew').style.display = 'none';
			document.getElementById('divLgdSelIntermediatePforNew').style.display = 'none';
		}

	} else {
		document.getElementById('divDistrictPforNew').style.display = 'none';
		document.getElementById('divLgdVillagePforNew').style.display = 'none';
		document.getElementById('divLgdSelIntermediatePforNew').style.display = 'none';
	}
}

function getInterPanchayatAtVillagebyDcodeforExist(id) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleInterPanchayatAtVillageforExistSuccess,
		errorHandler : handleInterPanchayatAtVillageforExistError
	});
}

function handleInterPanchayatAtVillageforExistSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddlgdLBInterAtVillageforExist';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddlgdLBInterAtVillageforExist');
	st.add(new Option('Select Village Panchayat', '0'));

	var options = $("#ddlgdLBInterAtVillageforExist");
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

function handleInterPanchayatAtVillageforExistError() {
	alert("No data found!");
}

function getVillagePanchayatAtVillagebyDcodeforExist(id) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleVillagePanchayatAtVillageforExistSuccess,
		errorHandler : handleVillagePanchayatAtVillageforExistError
	});
}

function handleVillagePanchayatAtVillageforExistSuccess(data) {
	var fieldId = 'ddlgdLBVillagePanchayatforExist';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddlgdLBVillagePanchayatforExist');
	st.add(new Option('Select Village Panchayat', '0'));
	var options = $("#ddlgdLBVillagePanchayatforExist");
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

function handleVillagePanchayatAtVillageforExistError() {
	alert("No data found!");
}

function getInterPanchayatAtVillagebyDcodeforExistF(id) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleInterPanchayatAtVillageforExistSuccessF,
		errorHandler : handleInterPanchayatAtVillageforExistErrorF
	});
}

function handleInterPanchayatAtVillageforExistSuccessF(data) {
	// Assigns data to result id

	var fieldId = 'ddlgdLBDistAtInterforNew';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddlgdLBDistAtInterforNew');
	st.add(new Option('Select Village Panchayat', '0'));
	var options = $("#ddlgdLBDistAtInterforNew");
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

function handleInterPanchayatAtVillageforExistErrorF() {
	alert("No data found!");
}

function getIntermediatePanchayatbyDcodeforNewF(id) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleInterPanchayatAtVillageforNewSuccessF,
		errorHandler : handleInterPanchayatAtVillageforNewErrorF
	});
}

function handleInterPanchayatAtVillageforNewSuccessF(data) {
	// Assigns data to result id

	var fieldId = 'ddlgdLBInterAtVillageforNew';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddlgdLBInterAtVillageforNew');
	st.add(new Option('Select Intermediate Panchayat', '0'));

	var options = $("#ddlgdLBInterAtVillageforNew");
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

function handleInterPanchayatAtVillageforNewErrorF() {
	alert("No data found!");
}

function getVillagePanchayatbyDcodeforNewF(id) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleVillagePanchayatAtVillageforNewSuccessF,
		errorHandler : handleVillagePanchayatAtVillageforNewErrorF
	});
}

function handleVillagePanchayatAtVillageforNewSuccessF(data) {
	// Assigns data to result id

	var fieldId = 'ddlgdLBVillagePanchayatforExistNew';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddlgdLBVillagePanchayatforExistNew');
	st.add(new Option('Select Village Panchayat', '0'));

	var options = $("#ddlgdLBVillagePanchayatforExistNew");
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

function handleVillagePanchayatAtVillageforNewErrorF() {
	alert("No data found!");
}
