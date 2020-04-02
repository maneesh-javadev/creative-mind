/*@Author - Ram Krishna Chauhan */
var globaldata;
var globaldataR;
var i = 4;
var allchk = 0;
var dynstart = 0;
var dynend = 0;
var dropdown_pos = 1;
var valueText = 'designationCode';
var nameText = 'designationName';
var maxDesg = 0;
var l = 1;

function getOrganizationReporting(OrgType) {
	lgdDwrOrganizationService.getOrganizationReporting(OrgType, {
		callback : handleOrgReptSuccess,
		errorHandler : handleOrgReptError
	});

}
function handleOrgReptSuccess(data) {
	var fieldId = 'orgParentCode';
	var valueText = 'orgCode';
	var nameText = 'orgName';
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		name : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleOrgReptError() {
	dwr.util.removeAllOptions('orgParentCode');
	alert("No data found!");
}

function getDesignation(id, locatedAtLevelCode) {
	lgdDwrOrganizationService.getDesignationByOrgCode(id, locatedAtLevelCode, {
		callback : handleSuccess,
		errorHandler : handleError
	});
}
function handleSuccess(data) {
	globaldata = data;
	var valueText = 'designationCode';
	var nameText = 'designationName';

	var fieldId1 = 'ddDesignation0';
	var fieldId2 = 'ddDesignation3';
	dwr.util.removeAllOptions(fieldId1);
	dwr.util.removeAllOptions(fieldId2);

	var dataq = [ {
		name : "Select"
	} ];
	dwr.util.addOptions(fieldId2, dataq, "name");

	dwr.util.addOptions(fieldId1, data, valueText, nameText);
	dwr.util.addOptions(fieldId2, data, valueText, nameText);
}
function getDesignationTopR(id) {
	lgdDwrOrganizationService.getReportingDesignationForTopDesignation(id, {
		callback : handleSuccessTopR,
		errorHandler : handleError
	});

}
function handleSuccessTopR(data) {
	var valueText = 'designationCode';
	var nameText = 'designationName';

	var fieldId = 'ddDesignation1';
	dwr.util.removeAllOptions(fieldId);

	var dataq = [ {
		name : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "name");

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getDesignationR(id, locatedAtLevelCode) {
	lgdDwrOrganizationService.getDesignationByOrgCodeNonTop(id, locatedAtLevelCode, {
		callback : handleSuccessR,
		errorHandler : handleError
	});

}
function handleSuccessR(data) {
	globaldataR = data;
	var valueText = 'designationCode';
	var nameText = 'designationName';

	var fieldId1 = 'ddDesignation2';
	dwr.util.removeAllOptions(fieldId1);

	var dataq = [ {
		name : "Select"
	} ];
	dwr.util.addOptions(fieldId1, dataq, "name");

	dwr.util.addOptions(fieldId1, data, valueText, nameText);
}
function handleError() {
	dwr.util.removeAllOptions('ddDesignation0');
	alert("No data found!");
}
function dropdown(divs) {
	var tmptextR = new Array();
	var tmptext = new Array();
	var tmpDesig;
	var temp = new Array();

	if (dynstart == 0) {
		dynstart = i;
	}

	dynend = i;
	if (l <= maxDesg - 2) {
		dynend = i;
		/*
		 * for(var j=dynstart;j<i;j+2) {
		 * alert(document.getElementById("ddDesignation" + (j+1)));
		 * alert(document.getElementById("ddDesignation" + (j+1)).value);
		 * if(document.getElementById("ddDesignation" + j).value!=null)
		 * tmptextR[j]=document.getElementById("ddDesignation" + j).value;
		 * if(document.getElementById("ddDesignation" + (j+1)).value!=null &&
		 * document.getElementById("ddDesignation" + (j+1)).value!="")
		 * tmptext[j]=document.getElementById("ddDesignation" + (j+1)).value; }
		 */
		divs.innerHTML += "<div id=div" + i + ">" + "<table><tr><td><select path='designation' name='designation' id='ddDesignation" + i
				+ "' class='combofield' style='width:150px'><option value=''>Select</option></select></td>"
				+ "<td><select path='reportTo' name='reportTo' id='ddDesignation" + (i + 1)
				+ "' class='combofield' style='width:150px'><option value=''>Select</option></select></td>"
				+ "<td><input type='button' value='delete' onclick='div" + i + ".parentNode.removeChild(div" + i
				+ "); l-=1;'/></td></tr></table></div>";

		dwr.util.addOptions('ddDesignation' + i, globaldataR, valueText, nameText);
		dwr.util.addOptions('ddDesignation' + (i + 1), globaldata, valueText, nameText);

		/*
		 * for(var j=dynstart;j<dynend;j+2) {
		 * document.getElementById("ddDesignation" + j).value=tmptextR[j];
		 * document.getElementById("ddDesignation" + j+1).value=tmptext[j]; }
		 */
		/*
		 * tmpDesig=document.getElementById("ddDesignation2").value + ",";
		 * if(i!=4){ for(var k=4; k<=i; k++){
		 * tmpDesig+=document.getElementById("ddDesignation" + k).value + ",";
		 * k++; } } alert(tmpDesig);
		 */
		/*
		 * var temp=tmpDesig.split(","); for(var z=0; z<temp.length;z++){
		 * document.getElementById("ddDesignation"+i).value=temp[z];
		 * document.getElementById("ddDesignation"+i).remove(document.getElementById("ddDesignation"+i).selectedIndex); }
		 */

		i += 2;
		l += 1;
	}
}

function populate() {
	var trec;
	document.getElementById("designation").value = "";
	document.getElementById("reportTo").value = "";
	// alert(":" + document.getElementById("tierSetupCode").value + ":" + trec);
	for ( var h = 1; h < trec; h++) {
		document.getElementById("designationCode" + h + "_error").innerHTML = "";
	}
	var error = false;
	if (document.getElementById('RdesignationCode0').disabled == false) {
		if (document.getElementById('RdesignationCode0').value == "") {
			$("#designationCode0_error").show();
			error = true;
		}
	}
	for ( var h = 1; h < trec; h++) {

		if (document.getElementById("RdesignationCode" + h).value == "" || document.getElementById("RdesignationCode" + h).value == "Select") {

			document.getElementById("designationCode" + h + "_error").innerHTML = "<font color='red'><br>Please Select Designation </font>";
			error = true;
		}
	}

	if (error == true) {
		return false;
	} else {
		for ( var k = 0; k < trec; k++) {
			document.getElementById("designation").value += document.getElementById("designationCode" + k).value + ",";
			document.getElementById("reportTo").value += document.getElementById("RdesignationCode" + k).value + ",";
		}
		alert("Designation " + document.getElementById("designation").value + "reportTo " + document.getElementById("reportTo").value);
		document.getElementById('tierSetupCode').disabled = false;
		return true;
	}
}

function populateOrg() {
	// document.getElementById("designation").value+=document.getElementById("ddDesignation0").value
	// + ",";
	// document.getElementById("reportTo").value+=document.getElementById("ddDesignation1").value
	// + ",";
	try {
		for ( var k = 2; k <= i; k += 2) {
			document.getElementById("designation").value += document.getElementById("designationCode" + k).value + ",";
			document.getElementById("reportTo").value += document.getElementById("RdesignationCode" + k).value + ",";
			alert("deisg== " + document.getElementById("designation").value);
			alert("report== " + document.getElementById("reportTo").value);
		}
	} finally {
		var isError = validationDesigReportingForm();
		if (isError == 0) {
			document.forms['designationReportingFormOrg'].submit();
		}
	}
}
function setMaxDesg() {
	maxDesg = 0;
	maxDesg = document.getElementById("ddDesignation2").length;
}
function getOrgbyType(id) {
	lgdDwrOrganizationService.getOrgbyOrgType(id, {
		callback : handleOrgSuccess,
		errorHandler : handleOrgError
	});

}

function handleOrgSuccess(data) {
	var fieldId = 'orgCode';
	var valueText = 'orgCode';
	var nameText = 'orgName';
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		select : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "select");
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleOrgError() {
	dwr.util.removeAllOptions('ddDept');
	alert("No data found!");
}
function getOrgbyTypeForReporting(orgTypeCode, orgCode) {
	lgdDwrOrganizationService.getOrgbyOrgTypeForReporting(orgTypeCode, orgCode, {
		async : true,
		callback : handleOrgbyTypeForReportingSuccess,
		errorHandler : handleOrgbyTypeForReportingError
	});

}

function handleOrgbyTypeForReportingSuccess(data) {
	// var fieldId = 'orgCodeR';
	// var fieldId = 'ddDesignation1';
	var fieldId = 'orgCodeR';
	var valueText = 'orgCode';
	var nameText = 'orgName';
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		select : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "select");
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleOrgbyTypeForReportingError() {
	dwr.util.removeAllOptions('orgCodeR');
	alert("No data found!");
}

function getOrgLocatedbyOrgCode(id) {
	lgdDwrOrganizationService.getOrgLocatedbyOrgCode(id, {
		callback : handleOrgLocatedAt,
		errorHandler : handlehandleOrgLocatedAtError
	});
}
function handleOrgLocatedAt(data) {
	var fieldId = 'locatedAtLevel';
	var valueText = 'orgLocatedLevelCode';
	var nameText = 'orgLevelSpecificName';
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		select : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "select");
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handlehandleOrgLocatedAtError() {
	alert("No data found!");
}

function validationDesigReportingForm() {
	var error = 0;
	var itr = 0;
	var objOrgType = document.getElementById("orgType");
	var objOrg = document.getElementById("orgCode");

	var objorgLocated = document.getElementById("locatedAtLevel");
	if (objOrgType.value == 0) {
		error = 1;
		itr = 1;
		alert("Kindly select the Organization Type from the list.");
	}
	if (itr == 0 && objOrg.value == 0 || objOrg.value == "Select") {
		error = 1;
		itr = 1;
		alert("Kindly select the Organization from the list.");
	}
	if (itr == 0 && objorgLocated.value == 0 || objorgLocated.value == "Select") {
		error = 1;
		itr = 1;
		alert("Kindly select the Level from the list.");
	}
	return error;
}