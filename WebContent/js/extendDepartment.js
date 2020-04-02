function getDepartmentListByMinistry(id) {
	lgdDwrOrganizationService.getDepartmentListforOrg(id, {
		callback : handleDepartmentByMinistrySuccess,
		errorHandler : handleDepartmentError1
	});
}

function handleDepartmentByMinistrySuccess(data) {
	var fieldId = 'orgName';
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

function getExtendDepatmentDetails(id) {
	try {
		lgdDwrOrganizationService.getExtendDepatmentInformation(id, {
			callback : handleDepartmentSuccess1,
			errorHandler : handleDepartmentError1
		});
	} catch (e) {
		alert(e);
	}
}

function handleDepartmentSuccess1(data) {
	if (data != null) {
		var tempData = data.split("~");
		document.getElementById('deptName').value = tempData[0];
		document.getElementById('deptNameLocal').value = tempData[1];
		document.getElementById('shortDeptName').value = tempData[2];
	}
}
function handleDepartmentError1() {
	alert("No data found!");
}

function getAdminUnitDepartmentDetails(id) {
	var isCenterFlag = 'F';
	if (isCenterUser) {
		isCenterFlag = 'T';
	}
	try {
		lgdDwrOrganizationService.getAdministrativeUnitLevelByOrgCode(id, isCenterFlag, {
			callback : handleAdminUnitDepartmentSuccess,
			errorHandler : handleAdminUnitDepartmentError
		});
	} catch (e) {
		alert(e);
	}
}

function handleAdminUnitDepartmentSuccess(data) {

	var fieldId = 'adminUnitLevelName';
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		select : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "select");
	var options = $("#adminUnitLevelName");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		var pageAccess = "";
		if (obj.adminunitlevelcode == "1") {
			pageAccess = "S";
		} else if (obj.adminunitlevelcode == "2") {
			pageAccess = "D";
		} else if (obj.adminunitlevelcode == "3") {
			pageAccess = "T";
		} else if (obj.adminunitlevelcode == "4") {
			pageAccess = "V";
		} else if (obj.adminunitlevelcode == "5") {
			pageAccess = "B";
		} else {
			pageAccess = "A";
		}
		var accessMap = obj.adminunitlevelcode + "|" + pageAccess;
		$(option).val(accessMap + "-" + obj.orglocatedlevelcode).text(obj.unitlevelnameenglish);
		options.append(option);

	});

}
function handleAdminUnitDepartmentError() {
	alert("No data found!");
}

function getData() {

	if (isCenterUser) {
		if (document.getElementById('ministryId').value == "") {
			alert("Please Select Ministry");
			return false;
		}
	}
	if (document.getElementById('orgName').value == "" || document.getElementById('orgName').value == "Select") {
		alert("Please Select Deparment");
	} else if (document.getElementById('adminUnitLevelName').value == 0 || document.getElementById('adminUnitLevelName').value == "Select") {
		alert("Please Select Organization Unit Level");
	} else {
		document.getElementById("GetButton").disabled = true;
		document.getElementById('form1').method = "post";
		document.getElementById('form1').action = "extendDepartmentState.htm?<csrf:token uri='extendDepartment.htm'/>";
		document.getElementById('form1').submit();
	}

}

function getdistrictListAtStatelvlListBoxforExtend(stateCode, olc) {
	lgdDwrDistrictService.getDistrictListExtend(stateCode, olc, {
		callback : handleDistrictSuccessExtend,
		errorHandler : handleDistrictErrorExtend
	});

}

function handleDistrictSuccessExtend(data) {
	// Assigns data to result id
	var fieldId = 'dddistrictAtStateLvl';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleDistrictErrorExtend() {
	alert("data not found!");

}

function getdistrictListAtStatelvlListBoxforExtendforBlock(stateCode, olc) {
	lgdDwrDistrictService.getDistrictListExtendforBlock(stateCode, olc, {
		callback : handleDistrictSuccessExtendforBlock,
		errorHandler : handleDistrictErrorExtendforBlock
	});

}

function handleDistrictSuccessExtendforBlock(data) {
	// Assigns data to result id
	var fieldId = 'dddistrictAtStateLvl';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleDistrictErrorExtendforBlock() {
	alert("data not found!");

}

function getSubdistrictAtSubDistrictlvlExtend(id) {
	var olc = document.getElementById('orgCode').value;
	lgdDwrSubDistrictService.getSubDistrictListbyDistrictCodewithDistrictNameExtend(id, olc, {
		callback : handleSubDistrictSuccessExtend,
		errorHandler : handleSubDistrictErrorExtend
	});

}

function handleSubDistrictSuccessExtend(data) {
	// Assigns data to result id
	var fieldId = 'ddSubdistrictAtSubDistrictLvl';
	var valueText = 'subdistrictCode';
	var nameText = 'subdistrictNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleSubDistrictErrorExtend() {
	alert("data not found!");

}

function getNotInDistrictListExtend(id, type) {
	var selObj = document.getElementById('ddTargetDistrict');
	var districtList = null;
	var olc = document.getElementById('orgCode').value;
	if (selObj.value > 0) {
		for ( var i = 0; i < selObj.options.length; i++) {
			if (districtList == null)
				districtList = selObj.options[i].value + ",";
			else
				districtList += selObj.options[i].value + ",";
			selObj.options[i].selected = true;
		}
		districtList = districtList.substring(0, districtList.length - 1);
	} /*
		 * else { alert("Kindly select the districts from the list in abov
		 * section first, afterwords click on this option.");
		 */
	lgdDwrDistrictService.getDistrictListbyStateCodeExtend(id, districtList, olc, type, {
		callback : handleDistrictSuccessSDLvlExtend,
		errorHandler : handleDistrictErrorExtend
	});

}
function handleDistrictSuccessSDLvlExtend(data) {
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

function getBlockListExtend(id) {
	var olc = document.getElementById('orgCode').value;
	lgdDwrBlockService.getBlockListExtrend(id, olc, {
		callback : handleBlockSuccessforExtend,
		errorHandler : handleBlockErrorforExtend
	});
}

function handleBlockSuccessforExtend(data) {
	var fieldId = 'ddSubdistrictAtSubDistrictLvl';
	var valueText = 'blockCode';
	var nameText = 'blockNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleBlockErrorforExtend() {
	alert("data not Found!");
}

function checkExistDepartment(stateCode, level, deptName) {
	/*
	 * lgdDwrOrganizationService.checkExistNameDeparment(stateCode, level,
	 * deptName, { callback : checkExistNameDeparmentSuccess, errorHandler :
	 * checkExistNameDeparmentError });
	 */

}

function checkExistNameDeparmentSuccess(data) {
	if (data != null) {
		if (data == false) {
			alert("This Deparment Name is alredy Exist.");
			document.getElementById('deptName').value = "";
			document.getElementById('deptName').focus();
		}

	}
}

function checkExistNameDeparmentError() {
	alert("data not Found!");
}

function handleDepartmentdetail(data) {
	var fieldId = 'tdMinDepartment';
	var valueText = 'orgCode';
	var nameText = 'orgName';
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		select : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "select");
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}
/*
 * //get department details on 04/01/2015 added by anju Gupta
 */
function getDepartmentDetails(id) {
	lgdDwrOrganizationService.getDepartmentDetails(id, {
		callback : handleDepartmentdetail,
		errorHandler : handleDistrictErrorExtend
	});

}
/*
 * get Organization Parent Name on 15/01/2015 added by anju Gupta
 * added locatAtLevel parameter on 18-06-2015 by pooja
 */

function getOrganizationParent1(olc,locatAtLevel) {

	try {

		lgdDwrOrganizationService.getOrganizationParentName(olc,locatAtLevel,{
			callback : handleOrgUnitDepartmentSuccess,
			errorHandler : handleAdminUnitDepartmentError
		});
	} catch (e) {
		alert(e);
	}
}
function handleOrgUnitDepartmentSuccess(data) {

	var fieldId = 'organizationUnit';
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		select : "Select"
	} ];

	dwr.util.addOptions(fieldId, dataq, "select");
	// var options = $("#organizationUnit");

	var valueText = 'orgCode';
	var nameText = 'orgName';
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}
/*
 * OrganizationUnitDetailsby OrgLevel on 11-1-2015 added by anju
 */
function getOrganizationnUnitLevelByOrgLevel(id) {

	try {

		lgdDwrOrganizationService.getOrganizationnUnitLevelByOrgLevel(id, {
			callback : handleAdminUnitDepartmentSuccess11,
			errorHandler : handleAdminUnitDepartmentError
		});
	} catch (e) {
		alert(e);
	}

}
/*
 * Added by Anju Gupta 17/03/2015 getOrg_located_at_levelsOrgCode
 * 
 */
function getAdminUnitDepartmentDetailsOrg(id) {

	var isCenterFlag = 'F';
	if (isCenterUser) {
		isCenterFlag = 'T';
	}
	try {

		lgdDwrOrganizationService.getOrg_located_at_levelsByOrgCode(id, isCenterFlag, {
			callback : handleAdminUnitDepartmentSuccess11,
			errorHandler : handleAdminUnitDepartmentError
		});
	} catch (e) {
		alert(e);
	}
}

/*
 * 
 * on 11-1-2015 added by anju
 */
function handleAdminUnitDepartmentSuccess11(data) {

	var fieldId = 'adminUnitLevelName';
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		select : "Select"
	} ];

	dwr.util.addOptions(fieldId, dataq, "select");
	var options = $("#adminUnitLevelName");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		var pageAccess = "";
		if (obj.locatedAtLevel == "1") {
			pageAccess = "S";
		} else if (obj.locatedAtLevel == "2") {
			pageAccess = "D";
		} else if (obj.locatedAtLevel == "3") {
			pageAccess = "T";
		} else if (obj.locatedAtLevel == "4") {
			pageAccess = "V";
		} else if (obj.locatedAtLevel == "5") {
			pageAccess = "B";
		} else {
			pageAccess = "A";
		}
		var accessMap = obj.locatedAtLevel + "|" + pageAccess;
		$(option).val(obj.adminunitlevelcode).text(obj.unitlevelnameenglish);//modified by pooja on 18-06-2015
		options.append(option);

	});

}
