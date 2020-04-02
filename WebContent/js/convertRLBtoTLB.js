function loadConvertRLBTLBPage() {
	document.getElementById('rdmergeRLBtoTLB').checked = false;
	document.getElementById('rddeclarenewTLB').checked = false;
	document.getElementById('divSaveButtons').style.display = 'none';
	document.getElementById('divPreviousButtons').style.display = 'block';
	document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
	/*
	 * document.getElementById('divRuralIntermediatePanchayat').style.display =
	 * 'none'; document.getElementById('divRuralVillagePanchayat').style.display =
	 * 'none'; document.getElementById('divVillagePforExist').style.display =
	 * 'none'; document.getElementById('divDistrictPforExist').style.display =
	 * 'none'; document.getElementById('divIntermediatePforExist').style.display =
	 * 'none'; document.getElementById('divLgdVillagePforNew').style.display =
	 * 'none';
	 * document.getElementById('divLgdSelIntermediatePforNew').style.display =
	 * 'none';
	 */
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
			document.getElementById('divmergeTLB').style.display = 'block';
			document.getElementById('divdeclareNewTLB').style.display = 'none';
			document.getElementById('divSaveButtons').style.display = 'block';
			document.getElementById('divPreviousButtons').style.display = 'none';
			document.getElementById('rdmergeRLBtoTLB').checked = true;
			document.getElementById('rddeclarenewTLB').checked = false;
			document.getElementById('divgovtOrder').style.display = 'block';
		} else if (id == 'N') {
			document.getElementById('divmergeTLB').style.display = 'none';
			document.getElementById('divdeclareNewTLB').style.display = 'block';
			document.getElementById('divPreviousButtons').style.display = 'none';
			document.getElementById('divSaveButtons').style.display = 'block';
			document.getElementById('rdmergeRLBtoTLB').checked = false;
			document.getElementById('rddeclarenewTLB').checked = true;
			document.getElementById('divgovtOrder').style.display = 'block';
		}
		getTraditionalTypeList(typeCode2, id);
	}

}

function getTraditionalTypeList(typecode, id) {
	if (id == 'M') {
		getTraditionalTypeListforExist(typecode);
	} else if (id == 'N') {
		getTraditionalTypeListforNew(typecode);
	}
}

function getTraditionalTypeListforExist(typecode) {

	var category = 'T';
	lgdDwrlocalBodyService.getTypeListbylevel(typecode, category, {
		callback : getTradTypeListforExistSuccess,
		errorHandler : getTradTypeListforExistError
	});
}

function getTradTypeListforExistSuccess(data) {
	var valueText = 'typeCode';
	var fieldId = 'ddTraditionalLBType';
	var nameText = 'localBodyTypeName';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddTraditionalLBType');
	st.add(new Option('Select Traditional Local Body Type', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getTradTypeListforExistError() {
	// Show a popup message
	alert("No data found!");
}

function getTraditionalTypeListforNew(typecode) {
	var category = 'T';
	lgdDwrlocalBodyService.getTypeListbylevel(typecode, category, {
		callback : getTradTypeListforNewSuccess,
		errorHandler : getTradTypeListforNewError
	});
}

function getTradTypeListforNewSuccess(data) {
	var valueText = 'typeCode';
	var fieldId = 'ddTribalLocalBodyTypeNew';
	var nameText = 'localBodyTypeName';
	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddTribalLocalBodyTypeNew');
	st.add(new Option('Select Traditional Local Body Type', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getTradTypeListforNewError() {
	// Show a popup message
	alert("No data found!");
}

function getHideShowRuralLBList(id) {
	if (id != "") {
		var temp = id.split(":");

		var id1 = temp[0];
		var id2 = temp[1];

		switch (id2) {

		case 'D':

			document.getElementById('divRuralDistrictPanchayat').style.display = 'block';
			document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
			document.getElementById('divRuralVillagePanchayat').style.display = 'none';
			break;
		case 'I':
			document.getElementById('divRuralIntermediatePanchayat').style.display = 'block';
			document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
			document.getElementById('divRuralVillagePanchayat').style.display = 'none';

			break;
		case 'V':
			document.getElementById('divRuralVillagePanchayat').style.display = 'block';
			document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
			document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
			break;

		default:
			document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
			document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
			document.getElementById('divRuralVillagePanchayat').style.display = 'none';
		}

	} else {
		document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
		document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
		document.getElementById('divRuralVillagePanchayat').style.display = 'none';
	}
}
function hideShowDivforNewTLB(id) {

	if (id != "") {
		var temp = id.split(":");

		var id1 = temp[0];
		var id2 = temp[1];

		switch (id2) {

		case 'I':
			document.getElementById('divLgdSelIntermediatePforNew').style.display = 'block';
			document.getElementById('divLgdVillagePforNew').style.display = 'none';
			getDistrictPanchayatListbyTypeCodeforNewAtInter(id1);

			break;
		case 'V':
			document.getElementById('divLgdVillagePforNew').style.display = 'block';
			document.getElementById('divLgdSelIntermediatePforNew').style.display = 'none';
			getDistrictPanchayatListbyTypeCodeforNewAtVillage(id1);
			break;

		default:
			document.getElementById('divLgdVillagePforNew').style.display = 'none';
			document.getElementById('divLgdSelIntermediatePforNew').style.display = 'none';

		}

	} else {
		document.getElementById('divLgdVillagePforNew').style.display = 'none';
		document.getElementById('divLgdSelIntermediatePforNew').style.display = 'none';
	}
}

function getDistrictLBListbyTypeCodeforExistAtDistrict(id) {
	var stateCode = dwr.util.getValue('hdnState');
	alert(stateCode);
	lgdDwrlocalBodyService.getPanchayatListbyStateandlbTypeCode(stateCode, id, {
		callback : handleDistrictPanchayatforExistAtDistrictSuccess,
		errorHandler : handleDistrictPanchayatforExistAtDistrictError
	});
}

function handleDistrictPanchayatforExistAtDistrictSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddlgdLBDistrictPforExist';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddlgdLBDistrictPforExist');
	st.add(new Option('Select District Panchayat', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleDistrictPanchayatforExistAtDistrictError() {
	// Show a popup message
	alert("No data found!");
}

function getDistrictLBListbyTypeCodeforExistAtInter(id) {
	var stateCode = dwr.util.getValue('hdnState');
	alert(stateCode);
	lgdDwrlocalBodyService.getPanchayatListbyStateandlbTypeCode(stateCode, id, {
		callback : handleDistrictPanchayatforExistAtInterSuccess,
		errorHandler : handleDistrictPanchayatforExistAtInterError
	});
}

function handleDistrictPanchayatforExistAtInterSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddlgdLBDistrictAtInterforExist';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddlgdLBDistrictAtInterforExist');
	st.add(new Option('Select District Panchayat', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleDistrictPanchayatforExistAtInterError() {
	// Show a popup message
	alert("No data found!");
}

function getDistrictLBListbyTypeCodeforExistAtVillage(id) {
	var stateCode = dwr.util.getValue('hdnState');
	alert(stateCode);
	lgdDwrlocalBodyService.getPanchayatListbyStateandlbTypeCode(stateCode, id, {
		callback : handleDistrictPanchayatforExistAtVillageSuccess,
		errorHandler : handleDistrictPanchayatforExistAtVillageError
	});
}

function handleDistrictPanchayatforExistAtVillageSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddlgdLBDistrictAtVillageforExist';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddlgdLBDistrictAtVillageforExist');
	st.add(new Option('Select District Panchayat', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleDistrictPanchayatforExistAtVillageError() {
	// Show a popup message
	alert("No data found!");
}

function getDistrictPanchayatListbyTypeCodeforNewAtInter(id) {
	var stateCode = dwr.util.getValue('hdnState');
	alert(stateCode);
	lgdDwrlocalBodyService.getPanchayatListbyStateandlbTypeCode(stateCode, id, {
		callback : handleDistrictPanchayatforNewAtInterSuccess,
		errorHandler : handleDistrictPanchayatforNewAtInterError
	});
}

function handleDistrictPanchayatforNewAtInterSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddlgdLBDistAtInterforNew';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddlgdLBDistAtInterforNew');
	st.add(new Option('Select District Panchayat', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleDistrictPanchayatforNewAtInterError() {
	// Show a popup message
	alert("No data found!");
}

function getDistrictPanchayatListbyTypeCodeforNewAtVillage(id) {
	var stateCode = dwr.util.getValue('hdnState');
	alert(stateCode);
	lgdDwrlocalBodyService.getPanchayatListbyStateandlbTypeCode(stateCode, id, {
		callback : handleDistrictPanchayatforNewAtVillageSuccess,
		errorHandler : handleDistrictPanchayatforNewAtVillageError
	});
}

function handleDistrictPanchayatforNewAtVillageSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddlgdLBDistrictAtVillageforNew';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddlgdLBDistrictAtVillageforNew');
	st.add(new Option('Select District Panchayat', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleDistrictPanchayatforNewAtVillageError() {
	// Show a popup message
	alert("No data found!");
}

function hideShowDivforExistTLB(id) {
	alert(id);
	if (id != "") {
		var temp = id.split(":");

		var id1 = temp[0];
		var id2 = temp[1];

		switch (id2) {

		case 'D':
			document.getElementById('divDistrictPforExist').style.display = 'block';
			document.getElementById('divIntermediatePforExist').style.display = 'none';
			document.getElementById('divVillagePforExist').style.display = 'none';
			getDistrictLBListbyTypeCodeforExistAtDistrict(id1);
			break;
		case 'I':
			document.getElementById('divIntermediatePforExist').style.display = 'block';
			document.getElementById('divDistrictPforExist').style.display = 'none';
			document.getElementById('divVillagePforExist').style.display = 'none';
			getDistrictLBListbyTypeCodeforExistAtInter(id1);
			break;
		case 'V':
			document.getElementById('divVillagePforExist').style.display = 'block';
			document.getElementById('divDistrictPforExist').style.display = 'none';
			document.getElementById('divIntermediatePforExist').style.display = 'none';
			getDistrictLBListbyTypeCodeforExistAtVillage(id1);
			break;

		default:
			document.getElementById('divVillagePforExist').style.display = 'none';
			document.getElementById('divDistrictPforExist').style.display = 'none';
			document.getElementById('divIntermediatePforExist').style.display = 'none';

		}

	} else {
		document.getElementById('divVillagePforExist').style.display = 'none';
		document.getElementById('divDistrictPforExist').style.display = 'none';
		document.getElementById('divIntermediatePforExist').style.display = 'none';
	}
}

function clickOnProceed() {
	document.getElementById('divFirstPanel').style.display = 'none';
	document.getElementById('divSecondPanel').style.display = 'block';
	document.getElementById('divmergeTLB').style.display = 'none';
	// document.getElementById('divgovtOrder').style.display = 'none';
	document.getElementById('divdeclareNewTLB').style.display = 'none';
	if (document.getElementById('rddeclarenewTLB').checked == true) {
		document.getElementById('divmergeTLB').style.display = 'none';
		document.getElementById('divdeclareNewTLB').style.display = 'block';
		// document.getElementById('divgovtOrder').style.display = 'block';
	} else if (document.getElementById('rdmergeRLBtoTLB').checked == true) {
		document.getElementById('divmergeTLB').style.display = 'block';
		document.getElementById('divdeclareNewTLB').style.display = 'none';
		// document.getElementById('divgovtOrder').style.display = 'block';
	}
}

function onPreviousClick() {
	document.getElementById('divFirstPanel').style.display = 'block';
	document.getElementById('divSecondPanel').style.display = 'none';
}

function getInterPanchayatAtInterbyDcodeforExist(id) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleInterPanchayatAtInterforExistSuccess,
		errorHandler : handleInterPanchayatAtInterforExistError
	});
}

function handleInterPanchayatAtInterforExistSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddlgdLBIntermediatePanchayatforExist';
	var fieldId1 = 'ddlgdLBIntermediatePanchayatforNew';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);

	var st = document.getElementById('ddlgdLBIntermediatePanchayatforExist');
	st.add(new Option('Select Intermediate Panchayat', '0'));
	var st1 = document.getElementById('ddlgdLBIntermediatePanchayatforNew');
	st1.add(new Option('Select Intermediate Panchayat', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);
	dwr.util.addOptions(fieldId1, data, valueText, nameText);
}

function handleInterPanchayatAtInterforExistError() {
	// Show a popup message
	alert("No data found!");
}

function getInterPanchayatAtVillagebyDcodeforExist(id) {
	if (id != 0) {
		lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
			callback : handleInterPanchayatAtVillageforExistSuccess,
			errorHandler : handleInterPanchayatAtVillageforExistError
		});
	}
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
	// Show a popup message
	alert("No data found!");
}

function getVillagePanchayatAtVillagebyDcodeforExist(id) {
	if (id != 0) {
		lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
			callback : handleVillagePanchayatAtVillageforExistSuccess,
			errorHandler : handleVillagePanchayatAtVillageforExistError
		});
	}
}

function handleVillagePanchayatAtVillageforExistSuccess(data) {
	// Assigns data to result id
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
	// Show a popup message
	alert("No data found!");
}

function getVillagePanchayatAtVillagebyDcodeforExistNew(id) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleVillagePanchayatAtVillageforExistSuccessNew,
		errorHandler : handleVillagePanchayatAtVillageforExistErrorNew
	});
}

function handleVillagePanchayatAtVillageforExistSuccessNew(data) {
	// Assigns data to result id

	var fieldId = 'ddlgdLBVillagePanchayatforExistNew';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddlgdLBVillagePanchayatforExistNew');
	st.add(new Option('Select Village Panchayat', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleVillagePanchayatAtVillageforExistErrorNew() {
	// Show a popup message
	alert("No data found!");
}

function getIntermediatePanchayatbyDcodeforNew(id) {
	if (id != 0) {
		lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
			callback : handleInterPanchayatAtVillageforNewSuccess,
			errorHandler : handleInterPanchayatAtVillageforNewError
		});
	}
}

function handleInterPanchayatAtVillageforNewSuccess(data) {
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

function handleInterPanchayatAtVillageforNewError() {
	// Show a popup message
	alert("No data found!");
}

function selectIntermediateLocalBodyListAtDCA() {
	var selObj = document.getElementById('ddLgdLBDistPDestList');
	var districtCode = "";
	for (i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		districtCode += selObj.options[i].value + ",";
	}
	getIntermediateList(districtCode);
}

function getIntermediateList(id) {
	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		lgdDwrlocalBodyService.getchildlocalbodiesByParentcodeInter(id, {
			callback : handleIntermediateLocalBodiesSuccess,
			errorHandler : handleIntermediateLocalBodiesError
		});
	}
}

function handleIntermediateLocalBodiesSuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddIntermediateLbSourceL';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddIntermediateLbSourceL');
	st.add(new Option('Select Intermediate Panchayat', '0'));

	dwr.util.removeAllOptions('ddIntermediateLbSourceL');
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleIntermediateLocalBodiesError() {
	// Show a popup message
	alert("No data found!");
}

function selectVillageLocalBodyListAtICA() {
	var selObj = document.getElementById('ddLgdLBInterPDestList');
	var districtCode = "";
	for (i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		districtCode += selObj.options[i].value + ",";
	}
	getVillageLocalBodyListAtICA(districtCode);
}

function getVillageLocalBodyListAtICA(id) {
	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		lgdDwrlocalBodyService.getchildlocalbodiesByParentcodeInter(id, {
			callback : handleIntermediateVillLocalBodiesSuccess,
			errorHandler : handleIntermediateVillLocalBodiesError
		});
	}
}

function handleIntermediateVillLocalBodiesSuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddLgdLBVillageInterCAreaSourceL';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddLgdLBVillageInterCAreaSourceL');
	st.add(new Option('Select Intermediate Panchayat', '0'));

	dwr.util.removeAllOptions('ddLgdLBVillageInterCAreaSourceL');
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleIntermediateVillLocalBodiesError() {
	// Show a popup message
	alert("No data found!");
}

// ******************validation Start***********************

function validateFirstAllRLBtoTLB() {
	selectRLBtoTLB();
	// clickOnProceed();

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

showLoadingImage = function() {
	$.blockUI({
		theme : true,
		title : '<spring:message code="LGD.common.DWRProccessing"/>',
		message : "<div style='text-align: center'><img src='<%= request.getContextPath() %>/images/ajax-loader-2.gif'/></div>"
	});
};

function validateFinalAllRLBtoTLB() {
	selectRLBtoTLB();

	var rdmergeRLBtoTLB = document.getElementById('rdmergeRLBtoTLB');
	var rddeclarenewTLB = document.getElementById('rddeclarenewTLB');

	var errors = new Array();
	var error = false;
	errors[0] = !validateRadio();
	// alert("rddeclarenewTLB " + rddeclarenewTLB);
	if (rddeclarenewTLB.checked) {
		errors[1] = !validateNewTLBName();
		errors[2] = !validateTLBTypeNew();
		errors[3] = !validateDPTLBTypeNew();
		errors[4] = !validateIPTypeNew();
	} else if (rdmergeRLBtoTLB.checked) {
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
		clickOnProceed();
		return true;
	}
	return false;
}

function validateRadio() {
	var rdmergeRLBtoTLB = document.getElementById('rdmergeRLBtoTLB');
	var rddeclarenewTLB = document.getElementById('rddeclarenewTLB');
	if (!rdmergeRLBtoTLB.checked && !rddeclarenewTLB.checked) {
		$("#rdmergeRLBtoTLB_error").show();
		return false;
	}
	$("#rdmergeRLBtoTLB_error").hide();
	return true;
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
	var ddTLBTypeNew = document.getElementById('ddTribalLocalBodyTypeNew');
	if (ddTLBTypeNew.selectedIndex == 0) {
		$("#ddTribalLocalBodyTypeNew_error").show();
		return false;
	}
	$("#ddTribalLocalBodyTypeNew_error").hide();
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
	var ddTLBTypeMerge = document.getElementById('ddTraditionalLBType');
	if (ddTLBTypeMerge.selectedIndex == 0) {
		$("#ddTraditionalLBType_error").show();
		return false;
	}
	$("#ddTraditionalLBType_error").hide();
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

function selectRLBtoTLB() {
	var selDistPDestList = document.getElementById('ddLgdLBDistPDestList');
	var selInterDestList = document.getElementById('ddIntermediateLbDesteL');
	var selIntermediateLbDesteL = document.getElementById('ddIntermediateLbDesteL');
	var selLBVillageInterCAreaDestL = document.getElementById('ddLgdLBVillageInterCAreaDestL');
	var selLBVillageCAreaDestL = document.getElementById('ddLgdLBVillageCAreaDestL');
	var seldesPRIList = document.getElementById('ddLgdLBVillageDestAtVillageCA');

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
	if (selLBVillageCAreaDestL) {
		for ( var m = 0; m < selLBVillageCAreaDestL.options.length; m++) {
			selLBVillageCAreaDestL.options[m].selected = true;
		}
	}
	for ( var m = 0; m < seldesPRIList.options.length; m++) {
		seldesPRIList.options[m].selected = true;
	}

}

function validateFirstAll() {
	selectRLBs();
	/*
	 * var errors = new Array(); var error = false;
	 * if(document.getElementById('divDIV').style.display =='block' ||
	 * document.getElementById('divDV').style.display == 'block') { errors[0] =
	 * !validatedistrictLB(); }
	 * if(document.getElementById('divDIV').style.display =='block' ||
	 * document.getElementById('divIV').style.display == 'block') { errors[1] =
	 * !validateintermediateLB(); errors[2] = !validatevillageLB(); errors[3] =
	 * !validatevillageLBforFULLandPART(); errors[4] =
	 * !validatevillageDestCoveredAreas(); }
	 * 
	 * for (var i = 0; i < 5; i++) {
	 * 
	 * if (errors[i] == true) {
	 * 
	 * error = true; break; } }
	 */
	/*
	 * if (error == true) {
	 * 
	 * showClientSideError(); return false; } else {
	 */
	clickOnProceed();
	// return true;
	// }

	return false;
}

/*
 * function validateFirstAll() { var msg=null; selectRLBs();
 * 
 * if (!validateLocalBodyTypeforRural()) { msg="Please Select Rural Local Body
 * Type"+'\n'; }
 * 
 * var id=dwr.util.getValue('validateLocalBodyTypeforRural'); var
 * temp=id.split(":"); id1=temp[0]; id2=temp[1];
 * 
 * if(!validateDistrictPanchayat(id2)) { if(msg!=null) { msg=msg+"Please Select
 * District Panchayat" + '\n'; } else { msg="Please Select District Panchayat" +
 * '\n'; } } if(!validateIntermediatePanchayat(id2)) { if(msg!=null) {
 * msg=msg+"Please Select Intermediate Panchayat" + '\n'; } else { msg="Please
 * Select Intermediate Panchayat" + '\n'; } } if(!validateVillagePanchayat(id2)) {
 * if(msg!=null) { msg=msg+"Please Select Village Panchayat" + '\n'; } else {
 * msg="Please Select Village Panchayat" + '\n'; } }
 * if(validateDistrictCoverages(id2)) { if(msg!=null) { msg=msg+"Please Select
 * Village Panchayat" + '\n'; } else { msg="Please Select Village Panchayat" +
 * '\n'; } }
 * 
 * 
 * if(msg!=null) { alert(msg); return false; } else{ clickOnProceed(); return
 * true; }
 * 
 * return false; }
 */

function validateDistrictCoverages(id) {
	if (id == 'D') {
		var ddLgdLBDistCAreaDestL = document.getElementById('ddLgdLBDistCAreaDestL');

		if (ddLgdLBDistCAreaDestL.options.length <= 0) {
			$("#ddLgdLBDistCAreaDestL_error").show();
			return false;
		} else {
			$("#ddLgdLBDistCAreaDestL_error").hide();
			return true;
		}
	}
}

function validateDistrictPanchayat(id) {
	if (id == 'D') {
		var ddLgdLBDistPDestList = document.getElementById('ddLgdLBDistPDestList');

		if (ddLgdLBDistPDestList.options.length <= 0) {
			$("#ddLgdLBDistPDestList_error").show();
			return false;
		} else {
			$("#ddLgdLBDistPDestList_error").hide();
			return true;
		}
	} else if (id == 'I') {
		var ddLgdLBDistListAtInterCA = document.getElementById('ddLgdLBDistListAtInterCA');

		if (ddLgdLBDistListAtInterCA.selectedIndex = 0) {
			$("#ddLgdLBDistListAtInterCA_error").show();
			return false;
		} else if (ddLgdLBDistListAtInterCA.selectedIndex = -1) {
			$("#ddLgdLBDistListAtInterCA_error").show();
			return false;
		} else {
			$("#ddLgdLBDistListAtInterCA_error").hide();
			return true;
		}
	}

	else if (id == 'V') {
		var ddLgdLBDistListAtVillageCA = document.getElementById('ddLgdLBDistListAtVillageCA');

		if (ddLgdLBDistListAtVillageCA.selectedIndex = 0) {
			$("#ddLgdLBDistListAtVillageCA_error").show();
			return false;
		} else if (ddLgdLBDistListAtVillageCA.selectedIndex = -1) {
			$("#ddLgdLBDistListAtVillageCA_error").show();
			return false;
		} else {
			$("#ddLgdLBDistListAtVillageCA_error").hide();
			return true;
		}
	}
}

function validateIntermediatePanchayat(id) {
	if (id == 'I') {
		var ddLgdLBInterPDestList = document.getElementById('ddLgdLBInterPDestList');

		if (ddLgdLBInterPDestList.options.length <= 0) {
			$("#ddLgdLBInterPDestList_error").show();
			return false;
		} else {
			$("#ddLgdLBDistListAtInterCA_error").hide();
			return true;
		}
	}

	else if (id == 'V') {
		var ddLgdLBInterListAtVillageCA = document.getElementById('ddLgdLBInterListAtVillageCA');

		if (ddLgdLBInterListAtVillageCA.selectedIndex = 0) {
			$("#ddLgdLBInterListAtVillageCA_error").show();
			return false;
		} else if (ddLgdLBDistListAtVillageCA.selectedIndex = -1) {
			$("#ddLgdLBInterListAtVillageCA_error").show();
			return false;
		} else {
			$("#ddLgdLBInterListAtVillageCA_error").hide();
			return true;
		}
	}
}

function validateVillagePanchayat(id) {
	var ddLgdLBVillageDestAtVillageCA = document.getElementById('ddLgdLBVillageDestAtVillageCA');
	if (id == 'V') {
		if (ddLgdLBVillageDestAtVillageCA.options.length <= 0) {
			$("#ddLgdLBVillageDestAtVillageCA_error").show();
			return false;
		} else {
			$("#ddLgdLBVillageDestAtVillageCA_error").hide();
			return true;
		}
	}
}

function checkforPart(ddId) {
	var count = 0;
	for ( var m = 0; m < ddId.options.length; m++) {

		if (ddId.options[m].value.match('PART')) {
			count = count + 1;
		} else {
			count = 0;
		}

	}
	if (count == 0) {
		return false;
	} else {
		return true;
	}

}

function validateLocalBodyTypeforRural() {
	var ddLgdLBType = document.getElementById('ddLgdLBType');

	if (ddLgdLBType.selectedIndex == 0) {
		$("#ddLgdLBType_error").show();
		return false;
	}

	$("#ddLgdLBType_error").hide();
	return true;

}

function selectRLBs() {
	var selDistPDestList = document.getElementById('ddLgdLBDistPDestList');
	var selDistPSourceList = document.getElementById('ddLgdLBDistPSourceList');

	var selDistCAreaSourceL = document.getElementById('ddLgdLBDistCAreaSourceL');
	var selDistCAreaDestL = document.getElementById('ddLgdLBDistCAreaDestL');

	var selSubDistrictSourceLatDCA = document.getElementById('ddLgdLBSubDistrictSourceLatDCA');
	var selSubDistrictDestLatDCA = document.getElementById('ddLgdLBSubDistrictDestLatDCA');

	var selVillageSourceLatDCA = document.getElementById('ddLgdLBVillageSourceLatDCA');
	var selVillageDestLatDCA = document.getElementById('ddLgdLBVillageDestLatDCA');

	var selInterPSourceList = document.getElementById('ddLgdLBInterPSourceList');
	var selInterPDestList = document.getElementById('ddLgdLBInterPDestList');

	var selInterCAreaSourceL = document.getElementById('ddLgdLBInterCAreaSourceL');
	var selInterCAreaDestL = document.getElementById('ddLgdLBInterCAreaDestL');

	var selVillageSourceLatICA = document.getElementById('ddLgdLBVillageSourceLatICA');
	var selVillageDestLatICA = document.getElementById('ddLgdLBVillageDestLatICA');

	var selVillageSourceAtVillageCA = document.getElementById('ddLgdLBVillageSourceAtVillageCA');
	var selVillageDestAtVillageCA = document.getElementById('ddLgdLBVillageDestAtVillageCA');

	var selVillageCAreaSourceL = document.getElementById('ddLgdLBVillageCAreaSourceL');
	var selVillageCAreaDestL = document.getElementById('ddLgdLBVillageCAreaDestL');

	for ( var i = 0; i < selDistPSourceList.options.length; i++) {
		selDistPSourceList.options[i].selected = false;
	}
	for ( var i = 0; i < selDistPDestList.options.length; i++) {
		selDistPDestList.options[i].selected = true;
	}

	for ( var j = 0; j < selDistCAreaSourceL.options.length; j++) {
		selDistCAreaSourceL.options[j].selected = false;
	}
	for ( var j = 0; j < selDistCAreaDestL.options.length; j++) {
		selDistCAreaDestL.options[j].selected = true;
	}

	for ( var k = 0; k < selSubDistrictSourceLatDCA.options.length; k++) {
		selSubDistrictSourceLatDCA.options[k].selected = false;
	}
	for ( var k = 0; k < selSubDistrictDestLatDCA.options.length; k++) {
		selSubDistrictDestLatDCA.options[k].selected = true;
	}

	for ( var l = 0; l < selVillageSourceLatDCA.options.length; l++) {
		selVillageSourceLatDCA.options[l].selected = false;
	}
	for ( var l = 0; l < selVillageDestLatDCA.options.length; l++) {
		selVillageDestLatDCA.options[l].selected = true;
	}

	for ( var m = 0; m < selInterPSourceList.options.length; m++) {
		selInterPSourceList.options[m].selected = false;
	}
	for ( var m = 0; m < selInterPDestList.options.length; m++) {
		selInterPDestList.options[m].selected = true;
	}

	for ( var m = 0; m < selInterCAreaSourceL.options.length; m++) {
		selInterCAreaSourceL.options[m].selected = false;
	}
	for ( var m = 0; m < selInterCAreaDestL.options.length; m++) {
		selInterCAreaDestL.options[m].selected = true;
	}

	for ( var m = 0; m < selVillageSourceLatICA.options.length; m++) {
		selVillageSourceLatICA.options[m].selected = false;
	}
	for ( var m = 0; m < selVillageDestLatICA.options.length; m++) {
		selVillageDestLatICA.options[m].selected = true;
	}

	for ( var m = 0; m < selVillageSourceAtVillageCA.options.length; m++) {
		selVillageSourceAtVillageCA.options[m].selected = false;
	}
	for ( var m = 0; m < selVillageDestAtVillageCA.options.length; m++) {
		selVillageDestAtVillageCA.options[m].selected = true;
	}

	for ( var m = 0; m < selVillageCAreaSourceL.options.length; m++) {
		selVillageCAreaSourceL.options[m].selected = false;
	}
	for ( var m = 0; m < selVillageCAreaDestL.options.length; m++) {
		selVillageCAreaDestL.options[m].selected = true;
	}
}

function getHideShowRuralToTLBLBList(id) {
	if (id != "" && id != "0") {
		var temp = id.split(":");

		var id1 = temp[0];
		var id2 = temp[1];

		switch (id2) {
		case 'D':
			getExitingTraditioanalTypeNameAsPRIforD(id2);
			document.getElementById('divRuralDistrictPanchayat').style.visibility = 'visible';
			document.getElementById('divRuralDistrictPanchayat').style.display = 'block';
			document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
			document.getElementById('divRuralVillagePanchayat').style.display = 'none';
			break;
		case 'I':
			getExitingTraditioanalTypeNameAsPRIforI(id2);
			document.getElementById('divRuralIntermediatePanchayat').style.visibility = 'visible';
			document.getElementById('divRuralIntermediatePanchayat').style.display = 'block';
			document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
			document.getElementById('divRuralVillagePanchayat').style.display = 'none';
			break;
		case 'V':
			getExitingTraditioanalTypeNameAsPRIforV(id2);
			document.getElementById('divRuralVillagePanchayat').style.visibility = 'visible';
			document.getElementById('divRuralVillagePanchayat').style.display = 'block';
			document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
			document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
			break;
		default:
			document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
			document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
			document.getElementById('divRuralVillagePanchayat').style.display = 'none';
		}

	} else {
		document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
		document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
		document.getElementById('divRuralVillagePanchayat').style.display = 'none';
	}
}

function getExitingTraditioanalTypeNameAsPRIforD(id) {
	var stateCode = dwr.util.getValue('hdnState');
	lgdDwrlocalBodyService.getTraditioanlTypebyPRITypeD(stateCode, id, {
		callback : handleTradionalTypebyPRIforDSuccess,
		errorHandler : handleTradionalTypebyPRIforDError
	});
}

function handleTradionalTypebyPRIforDSuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddTraditionalLBType';
	var fieldId1 = 'ddTribalLocalBodyTypeNew';
	var valueText = 'localBodyTypeCode';
	var nameText = 'nomenclatureEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);
	if (data.length > 0) {
		if (data.length > 1) {
			var st = document.getElementById('ddTraditionalLBType');
			st.add(new Option('Select Traditional Type', '0'));
			var st1 = document.getElementById('ddTribalLocalBodyTypeNew');
			st1.add(new Option('Select Traditional Type', '0'));
			dwr.util.addOptions(fieldId, data, disTest, disValue);
			dwr.util.addOptions(fieldId1, data, disTest, disValue);
		} else if (data.length == 1) {
			var st = document.getElementById('ddTraditionalLBType');
			st.add(new Option('Select Traditional Type', '0'));
			var st1 = document.getElementById('ddTribalLocalBodyTypeNew');
			st1.add(new Option('Select Traditional Type', '0'));
			dwr.util.addOptions(fieldId, data, disTest, disValue);
			dwr.util.addOptions(fieldId1, data, disTest, disValue);
		}
	} else {
		alert("You cannot convert this type of Panchayat Local Body as there is no TLB at this Level in your State");
	}
}

function disTest(data) {

	var v = data.localBodyTypeCode + ":D";
	return v;
}

function disValue(data) {
	return data.nomenclatureEnglish;
}

function handleTradionalTypebyPRIforDError() {
	// Show a popup message
	alert("No data found!");
}

function getExitingTraditioanalTypeNameAsPRIforI(id) {
	var stateCode = dwr.util.getValue('hdnState');
	lgdDwrlocalBodyService.getTraditioanlTypebyPRITypeI(stateCode, id, {
		callback : handleTradionalTypebyPRIforISuccess,
		errorHandler : handleTradionalTypebyPRIforIError
	});
}

function handleTradionalTypebyPRIforISuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddTraditionalLBType';
	var fieldId1 = 'ddTribalLocalBodyTypeNew';
	var valueText = 'localBodyTypeCode';
	var nameText = 'nomenclatureEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);
	if (data.length > 0) {
		if (data.length > 1) {
			var st = document.getElementById('ddTraditionalLBType');
			st.add(new Option('Select Traditional Type', '0'));
			var st1 = document.getElementById('ddTribalLocalBodyTypeNew');
			st1.add(new Option('Select Traditional Type', '0'));
			dwr.util.addOptions(fieldId, data, intText, intValue);
			dwr.util.addOptions(fieldId1, data, intText, intValue);
		} else if (data.length == 1) {
			var st = document.getElementById('ddTraditionalLBType');
			st.add(new Option('Select Traditional Type', '0'));
			var st1 = document.getElementById('ddTribalLocalBodyTypeNew');
			st1.add(new Option('Select Traditional Type', '0'));
			dwr.util.addOptions(fieldId, data, intText, intValue);
			dwr.util.addOptions(fieldId1, data, intText, intValue);
		}
	} else {
		alert("You cannot convert this type of Panchayat Local Body as there is no TLB at this Level in your State");
	}
}

function intText(data) {

	var v = data.localBodyTypeCode + ":I";
	return v;
}

function intValue(data) {
	return data.nomenclatureEnglish;
}

function handleTradionalTypebyPRIforIError() {
	// Show a popup message
	alert("No data found!");
}

function getExitingTraditioanalTypeNameAsPRIforV(id) {
	var stateCode = dwr.util.getValue('hdnState');
	lgdDwrlocalBodyService.getTraditioanlTypebyPRITypeV(stateCode, id, {
		callback : handleTradionalTypebyPRIforVSuccess,
		errorHandler : handleTradionalTypebyPRIforVError
	});
}

function handleTradionalTypebyPRIforVSuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddTraditionalLBType';
	var fieldId1 = 'ddTribalLocalBodyTypeNew';
	var valueText = 'localBodyTypeCode';
	var nameText = 'nomenclatureEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);
	if (data.length > 0) {
		if (data.length > 1) {
			var st = document.getElementById('ddTraditionalLBType');
			st.add(new Option('Select Traditional Type', '0'));
			var st1 = document.getElementById('ddTribalLocalBodyTypeNew');
			st1.add(new Option('Select Traditional Type', '0'));
			dwr.util.addOptions(fieldId, data, villTest, villValue);
			dwr.util.addOptions(fieldId1, data, villTest, villValue);
		} else if (data.length == 1) {
			var st = document.getElementById('ddTraditionalLBType');
			st.add(new Option('Select Traditional Type', '0'));
			var st1 = document.getElementById('ddTribalLocalBodyTypeNew');
			st1.add(new Option('Select Traditional Type', '0'));
			dwr.util.addOptions(fieldId, data, villTest, villValue);
			dwr.util.addOptions(fieldId1, data, villTest, villValue);
		}
	} else {
		alert("You cannot convert this type of Panchayat Local Body as there is no TLB at this Level in your State");
	}
}

function villTest(data) {

	var v = data.localBodyTypeCode + ":V";
	return v;
}

function villValue(data) {
	return data.nomenclatureEnglish;
}

function handleTradionalTypebyPRIforVError() {
	// Show a popup message
	alert("No data found!");
}

function gethideShowDivforExistTLB(id) {
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
			// document.getElementById('divDistrictPforExist').style.display
			// = 'none';
			document.getElementById('divVillagePforExist').style.display = 'none';
			// getDistrictLBListbyTypeCodeforExistAtInter(id1);
			break;
		case 'V':
			document.getElementById('divVillagePforExist').style.visibility = 'visible';
			document.getElementById('divVillagePforExist').style.display = 'block';
			// document.getElementById('divDistrictPforExist').style.display
			// = 'none';
			document.getElementById('divIntermediatePforExist').style.display = 'none';
			// getDistrictLBListbyTypeCodeforExistAtVillage(id1);
			break;

		default:
			document.getElementById('divVillagePforExist').style.display = 'none';
			// document.getElementById('divDistrictPforExist').style.display
			// = 'none';
			document.getElementById('divIntermediatePforExist').style.display = 'none';
		}
	} else {
		// document.getElementById('divVillagePforExist').style.display =
		// 'none';
		document.getElementById('divDistrictPforExist').style.display = 'none';
		document.getElementById('divIntermediatePforExist').style.display = 'none';
	}
}

function hideShowDivforNewTLBFinal(id) {
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

// ******************validation End***********************
