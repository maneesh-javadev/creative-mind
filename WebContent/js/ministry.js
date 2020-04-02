// Ministry 

function getMinistryListbyState(id) {
	if (id != '')
		lgdDwrlocalBodyService.getMinistryListbyState(id, {
			callback : handleMinistrySuccess,
			errorHandler : handleMinistryError
		});
}

function handleMinistrySuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddMinistry';
	var valueText = 'orgCode';
	var nameText = 'orgName';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddMinistry');
	st.add(new Option('---------Select----------', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleMinistryError() {
	// Show a popup message
	alert("No data found!");
}

function getMinistryList() {

	lgdDwrlocalBodyService.getMinistryList({
		callback : handleMinistrySuccess1,
		errorHandler : handleMinistryErrors
	});
}

function handleMinistrySuccess1(data) {
	// Assigns data to result id
	var fieldId = 'ddMinistry';
	var valueText = 'orgCode';
	var nameText = 'orgName';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddMinistry');
	st.add(new Option('---------Select----------', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleMinistryErrors() {
	// Show a popup message
	alert("No data found!");
}

// Department

function getDepartmentListByMinistry(id) {
	if (id != '')
		lgdDwrOrganizationService.getDepartmentListByMinistry(id, {
			callback : handleDepartmentSuccess,
			errorHandler : handleDepartmentError
		});
}

function handleDepartmentSuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddDepartment';
	var valueText = 'orgCode';
	var nameText = 'orgName';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddDepartment');
	st.add(new Option('---------Select----------', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleDepartmentError() {
	// Show a popup message
	alert("No data found!");
}

function getDepartmentList() {
	lgdDwrlocalBodyService.getDepartmentList({
		callback : handleDepartmentByMinistrySuccess,
		errorHandler : handleDepartmentByMinistryError
	});
}

function handleDepartmentByMinistrySuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddDepartment';
	var valueText = 'orgCode';
	var nameText = 'orgName';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddDepartment');
	st.add(new Option('---------Select----------', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleDepartmentByMinistryError() {
	// Show a popup message
	alert("No data found!");
}

// State Line Department List

function getStateLineDepartmentList(chkVal) {
	if (chkVal) {
		var stateCode = dwr.util.getValue('hdnStateCode');
		// var stateCode = 35;
		lgdDwrlocalBodyService.getStateLineDepartmentList(stateCode, 2, 'S', {
			callback : handleStateLineDepartmentSuccess,
			errorHandler : handleStateLineDepartmentError
		});
	}
}

function handleStateLineDepartmentSuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddDepartment';
	var valueText = 'orgCode';
	var nameText = 'orgName';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddDepartment');
	st.add(new Option('---------Select----------', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleStateLineDepartmentError() {
	// Show a popup message
	alert("No data found!");
}

// District Line Department List

function getDistrictLineDepartmentList(id) {

	// var orgLocatedLevelCode=138; //need to change

	var level = 'S';
	lgdDwrlocalBodyService.getDistrictLineDepartmentList(id, level, {
		callback : handleDistLineDepartmentSuccess,
		errorHandler : handleDistLineDepartmentError
	});

}

function handleDistLineDepartmentSuccess(data) {
	// Assigns data to result id

	var fieldId = 'dDepartment';
	var valueText = 'deptCode';
	var nameText = 'deptName';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('dDepartment');
	st.add(new Option('---------Select----------', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleDistLineDepartmentError() {
	// Show a popup message
	alert("No data found!");
}

// SubDistrict Line Department List

function getSubDistrictLineDepartmentList(id) {

	// var orgLocatedLevelCode=138; //need to change

	var level = 'D';
	lgdDwrlocalBodyService.getSubDistrictLineDepartmentList(id, level, {
		callback : handleSubDistLineDepartmentSuccess,
		errorHandler : handleSubDistLineDepartmentError
	});

}

function handleSubDistLineDepartmentSuccess(data) {
	// Assigns data to result id

	var fieldId = 'sdDepartment';
	var valueText = 'deptCode';
	var nameText = 'deptName';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('sdDepartment');
	st.add(new Option('---------Select----------', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleSubDistLineDepartmentError() {
	// Show a popup message
	alert("No data found!");
}

// Village Line Department List

function getVillageLineDepartmentList(id) {

	// var orgLocatedLevelCode=138; //need to change

	var level = 'T';
	lgdDwrlocalBodyService.getVillageLineDepartmentList(id, level, {
		callback : handleVillageLineDepartmentSuccess,
		errorHandler : handleVillageLineDepartmentError
	});

}

function handleVillageLineDepartmentSuccess(data) {
	// Assigns data to result id

	var fieldId = 'vDepartment';
	var valueText = 'deptCode';
	var nameText = 'deptName';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('vDepartment');
	st.add(new Option('---------Select----------', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleVillageLineDepartmentError() {
	// Show a popup message
	alert("No data found!");
}

// // ward

function getdistrictPanchahyatList(id) {

	var code = dwr.util.getValue('hdnStateCode');

	lgdDwrlocalBodyService.getPanchayatListbyStateandlbTypeCode(code, id, {
		callback : handleDLBSuccess12,
		errorHandler : handleDLBError12
	});
}

function handleDLBSuccess12(data) {

	var fieldId = 'ddSourceLocalBody';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddSourceLocalBody');
	st.add(new Option('----------Select----------', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleDLBError12() {
	// Show a popup message
	alert("No data found!");
}

function hideShowDistforMinistry(id) {

	var temp = id.split(":");

	var id1 = temp[0];
	var id2 = temp[1];
	var id3 = temp[2];
	if (id3 == 'U') {

		document.getElementById("tr_district1").style.display = "block";
		document.getElementById("tr_intermediate1").style.display = "none";
		document.getElementById("tr_village").style.display = "none";
	} else {
		switch (id2) {
		case "D":

			document.getElementById("tr_district1").style.display = "block";
			document.getElementById("tr_intermediate1").style.display = "none";
			document.getElementById("tr_village").style.display = "none";
			break;
		case "I":

			document.getElementById("tr_district1").style.display = 'block';
			document.getElementById("tr_intermediate1").style.display = "block";
			document.getElementById("tr_village").style.display = "none";

			break;
		case "V":

			document.getElementById("tr_district1").style.display = "block";
			document.getElementById("tr_intermediate1").style.display = "block";
			document.getElementById("tr_village").style.display = "block";

			break;
		default:
			document.getElementById("tr_district1").style.display = "none";
			document.getElementById("tr_intermediate1").style.display = "none";
			document.getElementById("tr_village").style.display = "none";
		}

	}

}

function loadViewMinistryPage() {
	document.getElementById("tr_district1").style.display = "none";
	document.getElementById("tr_intermediate1").style.display = "none";
	document.getElementById("tr_village").style.display = "none";
}

function getLBWardSubDistrictPanList(id) {
	alert(id);

	lgdDwrLocalGovtBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleLocalGovtBodySDPSuccess,
		errorHandler : handleLocalGovtBodySDPError
	});

}

function handleLocalGovtBodySDPSuccess(data) {
	// Assigns data to result id

	var fieldId = 'localGovtBodyListMain';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	dwr.util.removeAllOptions(fieldId);

	/*
	 * var st = document.getElementById('localGovtBodyListMain'); st.add(new
	 * Option('Select Intermediate Panchayat', '0'));
	 */

	var st = document.getElementById('localGovtBodyListMain');
	st.add(new Option('Select Intermediate Body', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

	/*
	 * var dataq = [ {name:"Select Intermediate Body"} ];
	 * dwr.util.addOptions(fieldId, dataq,'0', "name");
	 * dwr.util.addOptions(fieldId, data, valueText, nameText);
	 */

}

function handleLocalGovtBodySDPError() {
	// Show a popup message
	alert("No data found!");
}

function getLBWardSubDistrictVillList(id) {

	lgdDwrLocalGovtBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleLbVillageSDPSuccess,
		errorHandler : handleLBVillageSDPError
	});

}

function handleLbVillageSDPSuccess(data) {
	// Assigns data to result id

	var fieldId = 'localGovtBodyListVillage';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	dwr.util.removeAllOptions(fieldId);

	/*
	 * var st = document.getElementById('localGovtBodyListMain'); st.add(new
	 * Option('Select Intermediate Panchayat', '0'));
	 */

	var st = document.getElementById('localGovtBodyListVillage');
	st.add(new Option('Select Village Body', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

	/*
	 * var dataq = [ {name:"Select Intermediate Body"} ];
	 * dwr.util.addOptions(fieldId, dataq,'0', "name");
	 * dwr.util.addOptions(fieldId, data, valueText, nameText);
	 */

}

function handleLBVillageSDPError() {
	// Show a popup message
	alert("No data found!");
}

function getPanchayatListbyStateandlbTypeCode(id) { // State Code Hard Coded
	// WIll Be change Later

	var temp = id.split(":");

	var id1 = temp[0];
	var id2 = temp[1];
	var id3 = temp[2];
	lgdDwrlocalBodyService.getPanchayatListbyStateandlbTypeCode(9, id1, {
		callback : getPanchayatListbyStateandlbTypeCodeSuccess,
		errorHandler : getPanchayatListbyStateandlbTypeCodeError
	});
}

// data contains the returned value
function getPanchayatListbyStateandlbTypeCodeSuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddSourceLocalBody';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	// villageCode,villageNameEnglish
	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddSourceLocalBody');
	st.add(new Option('-------Select-------', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getPanchayatListbyStateandlbTypeCodeError() {
	// Show a popup message
	alert("No data found!");
}

function getDistrictList() {
	var stateCode = dwr.util.getValue('hdnStateCode');
	lgdDwrDistrictService.getDistrictListbyStateCodeForLocalBody(stateCode, {
		callback : handleDistrictSuccess,
		errorHandler : handleDistrictError
	});
}

function handleDistrictSuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddSourceDistrict';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddSourceDistrict');
	st.add(new Option('---------Select----------', '0'));

	var options = $("#ddSourceDistrict");
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
function getAdminUnitList() {
	var stateCode = dwr.util.getValue('hdnStateCode');
	lgdAdminDepatmentDwr.getDeptAdminUnitList(stateCode, {
		callback : handleAdminDeptUnitSuccess,
		errorHandler : handleDistrictError
	});
}

function handleAdminDeptUnitSuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddSourceAdminUnit';
	var valueText = 'adminUnitEntityCode';
	var nameText = 'adminEntityNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddSourceAdminUnit');
	st.add(new Option('---------Select----------', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleDistrictError() {
	// Show a popup message
	alert("No data found!");
}

function getandSetDistrictList(id) {
	if (id == 'S') {
		document.getElementById('td_district').style.display = 'none';
		document.getElementById('td_subdistrict').style.display = 'none';
		document.getElementById('td_block').style.display = 'none';
		document.getElementById('td_village').style.display = 'none';
	}

	else {
		document.getElementById('td_district').style.display = 'block';
		getDistrictList();
	}
	if (id == 'D') {
		document.getElementById('td_subdistrict').style.display = 'none';
		document.getElementById('td_block').style.display = 'none';
		document.getElementById('td_village').style.display = 'none';
		getDistrictList();
	}
}

function getandSetDistrictListDP(id) {

	if (id != '') {
		if (id == 'S') {
			document.getElementById('td_district').style.display = 'none';
			document.getElementById('td_subdistrict').style.display = 'none';
			document.getElementById('td_block').style.display = 'none';
			document.getElementById('td_village').style.display = 'none';
			document.getElementById('td_adminunit').style.display = 'none';
		} else if (id == 'A') {
			document.getElementById('td_adminunit').style.display = 'block';
			document.getElementById('td_district').style.display = 'none';
			document.getElementById('td_subdistrict').style.display = 'none';
			document.getElementById('td_block').style.display = 'none';
			document.getElementById('td_village').style.display = 'none';
			getAdminUnitList();
		} else {
			document.getElementById('td_district').style.display = 'block';
			getDistrictList();
		}
		if (id == 'D' || id == 'T' || id == 'V' || id == 'B') {
			document.getElementById('td_subdistrict').style.display = 'none';
			document.getElementById('td_block').style.display = 'none';
			document.getElementById('td_village').style.display = 'none';
			document.getElementById('td_adminunit').style.display = 'none';
			getDistrictList();
		}
	}
}

function getandSetDistrictListDPforAdminUnitLevel(id) {

	if (id != '') {
		if (id == 'S') {
			document.getElementById('td_district').style.display = 'none';
			document.getElementById('td_subdistrict').style.display = 'none';
			document.getElementById('td_block').style.display = 'none';
			document.getElementById('td_village').style.display = 'none';
			document.getElementById('td_adminunit').style.display = 'none';
			document.getElementById('trAdminUnitLevel').style.display = 'none';
		} else if (id == 'A') {
			document.getElementById('td_adminunit').style.display = 'block';
			document.getElementById('td_district').style.display = 'none';
			document.getElementById('td_subdistrict').style.display = 'none';
			document.getElementById('td_block').style.display = 'none';
			document.getElementById('td_village').style.display = 'none';
			document.getElementById('trAdminUnitLevel').style.display = 'block';
			// getAdminUnitList();
			getAdminUnitLelelList();

		} else {
			document.getElementById('td_district').style.display = 'block';
			document.getElementById('trAdminUnitLevel').style.display = 'none';
			getDistrictList();
		}
		if (id == 'D' || id == 'T' || id == 'V' || id == 'B') {
			document.getElementById('td_subdistrict').style.display = 'none';
			document.getElementById('td_block').style.display = 'none';
			document.getElementById('td_village').style.display = 'none';
			document.getElementById('td_adminunit').style.display = 'none';
			document.getElementById('trAdminUnitLevel').style.display = 'none';
			getDistrictList();
		}
	}
}

// /////////////SubDistrict///////////////

function getSubDistrictList(id) {

	if (id != 0)
		lgdDwrSubDistrictService.getSubDistrictListForLocalbody(id, {
			callback : handleSubDistrictSuccess,
			errorHandler : handleSubDistrictError
		});

}

function handleSubDistrictSuccess(data) {

	var fieldId = 'ddSourceSubDistrict';
	var valueText = 'subdistrictCode';
	var nameText = 'subdistrictNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddSourceSubDistrict');
	st.add(new Option('------------Select------------', '0'));

	var options = $("#ddSourceSubDistrict");
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

	// dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleSubDistrictError() {
	// Show a popup message
	alert("No data found!");
}

function getandSetSubDistrictList(id) {
	var categoryId = dwr.util.getValue('category');
	if (categoryId == 'T' || categoryId == 'V') {
		document.getElementById('td_subdistrict').style.display = 'block';
		document.getElementById('td_block').style.display = 'none';
		document.getElementById('td_village').style.display = 'none';
		document.getElementById('td_adminunit').style.display = 'none';
		/* document.getElementById('td_block').style.display = 'block'; */
		getSubDistrictList(id);
	} else {
		document.getElementById('td_subdistrict').style.display = 'none';
	}
}

// /////////////Block///////////////

function getBlockList(id) {
	if (id != 0)
		lgdDwrBlockService.getBlockList(id, {
			callback : handleBlockSuccess,
			errorHandler : handleBlockError
		});

}

function handleBlockSuccess(data) {
	var fieldId = 'ddblock';
	var valueText = 'blockCode';
	var nameText = 'blockNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddblock');
	st.add(new Option('------------Select------------', '0'));

	var options = $("#ddblock");
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

function getandSetBlockList(id) {
	var categoryId = dwr.util.getValue('category');
	if (categoryId == 'B') {
		document.getElementById('td_block').style.display = 'block';
		getBlockList(id);
	} else {
		document.getElementById('td_block').style.display = 'none';
	}
}

// /////////////Village///////////////

function getVillageList(id) {
	if (id != 0)
		lgdDwrVillageService.getVillageListbySubDistrictCodeCov(id, {
			callback : handleVillageSuccess,
			errorHandler : handleVillageError
		});

}

function handleVillageSuccess(data) {
	var fieldId = 'ddvillage';
	var valueText = 'villageCode';
	var nameText = 'villageNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddvillage');
	st.add(new Option('------------Select------------', '0'));

	var options = $("#ddvillage");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.villageCode).text(obj.villageNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.villageCode).text(obj.villageNameEnglish);
			options.append(option);
		}
	});

	// dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleVillageError() {
	// Show a popup message
	alert("No data found!");
}

function getandSetVillageList(id) {
	var categoryId = dwr.util.getValue('category');
	// alert(categoryId);
	if (categoryId == 'T' || categoryId == 'V') {
		document.getElementById('td_village').style.display = 'block';
		getVillageList(id);
	} else {
		document.getElementById('td_village').style.display = 'none';
	}

}

function getandSetVillageListDP(id) {
	var categoryId = dwr.util.getValue('category');
	// alert(categoryId);
	if (categoryId == 'V') {
		document.getElementById('td_village').style.display = 'block';
		getVillageList(id);
	} else {
		document.getElementById('td_village').style.display = 'none';
	}

}

function checkSelectValue(objName) {
	if (document.getElementById(objName).selectedIndex > 0)
		return true;
	else
		return false;
}

function checkVisibleElement(objName) {
	if (document.getElementById(objName).style.visibility == 'visible' || document.getElementById(objName).style.display == 'block')
		return true;
	else
		return false;
}

function validate_modifyOrganization() {
	error = false;
	if (!checkSelectValue('category')) {
		error = true;
		$("#error_category").show();
	}

	if (checkVisibleElement('td_district')) {
		if (!checkSelectValue('ddSourceDistrict')) {
			error = true;
			$("#error_td_district").show();
		}
	}

	if (checkVisibleElement('td_subdistrict')) {
		if (!checkSelectValue('ddSourceSubDistrict')) {
			error = true;
			$("#error_td_subdistrict").show();
		}
	}

	if (checkVisibleElement('td_village')) {
		if (!checkSelectValue('ddvillage')) {
			error = true;
			$("#error_td_village").show();
		}
	}

	if (checkVisibleElement('td_block')) {
		if (!checkSelectValue('ddblock')) {
			error = true;
			$("#error_td_block").show();
		}
	}
	if (checkVisibleElement('td_adminunit')) {
		if (!checkSelectValue('ddSourceAdminUnit')) {
			error = true;
			$("#error_td_adminunit").show();
		}
	}

	if (error == true) {
		return false;
	} else {
		return true;
	}
}

function validate_modifyDepartmnet() {
	hideAll();
	error = false;

	if (checkSelectValue('category'))
		if (document.getElementById('category').value == '' || !checkSelectValue('category')) {
			error = true;
			$("#error_category").show();
		}

	if (checkVisibleElement('td_district')) {
		if (!checkSelectValue('ddSourceDistrict')) {
			error = true;
			$("#error_td_district").show();
		}
	}

	if (checkVisibleElement('td_subdistrict')) {
		if (!checkSelectValue('ddSourceSubDistrict')) {
			error = true;
			$("#error_td_subdistrict").show();
		}
	}

	if (checkVisibleElement('td_village')) {
		if (!checkSelectValue('ddvillage')) {
			error = true;
			$("#error_td_village").show();
		}
	}

	if (checkVisibleElement('td_block')) {
		if (!checkSelectValue('ddblock')) {
			error = true;
			$("#error_td_block").show();
		}
	}
	if (checkVisibleElement('td_adminunit')) {
		if (!checkSelectValue('ddSourceAdminUnit')) {
			error = true;
			$("#error_td_adminunit").show();
		}
	}

	if (error == true) {
		return false;
	} else {
		return true;
	}

}

function getAdminUnitLelelList() {
	var stateCode = dwr.util.getValue('hdnStateCode');
	lgdAdminDepatmentDwr.getAdminUnitLevelList(stateCode, {
		callback : handleAdminDeptUnitLevelSuccess,
		errorHandler : handleDistrictError
	});
}

function handleAdminDeptUnitLevelSuccess(data) {
	// Assigns data to result id
	var fieldId = 'adminUnitLevel';
	var valueText = 'adminUnitCode';
	var nameText = 'adminLevelNameEng';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('adminUnitLevel');
	st.add(new Option('---------Select----------', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getAdminUnitListByLevelCode(levleCode) {
	var stateCode = dwr.util.getValue('hdnStateCode');
	lgdAdminDepatmentDwr.getAdminUnitListbyStateCodeandLevel(stateCode, levleCode, {
		callback : handleAdminDeptUnitSuccess,
		errorHandler : handleDistrictError
	});
}
