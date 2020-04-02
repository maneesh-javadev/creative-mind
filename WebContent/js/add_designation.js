/*@Author - Ram Krishna Chauhan */
var i = 50;
var max = 0;
var dropdown_pos = 1;
var sel_drop_value = new Array();
var sel_drop_value_pos = 1;
var allchk = 0;
var dynstart = 0;
var dynend = 0;
var dynfirst4 = 1;
var dynstart4 = 1;
var dynend4 = 1;
var tmplabel = "";
var isnew_officialtype = true;
var isnotcomplete = false;
var newsubtype = 1;
var globaldata;
var valueText1 = 'orgLocatedLevelCode';
var nameText1 = 'orgLevelSpecificName';

function showOrgLevel(chkid) {
	var itr = chkid.substring(10);
	document.getElementById('orgLevel' + itr).style.visibility = 'hidden';
	if (document.getElementById(chkid).checked == true) {
		document.getElementById('orgLevel' + itr).style.visibility = 'visible';
	}
}

function lookup(input, cId) {
	controlId = cId.split("~")[1];
	lgdDwrOrganizationService.getDesignationName(input, function callback(temp) {
		removeList(controlId);
		if (temp.length > 0) {
			document.getElementById("suggestions~" + controlId).style.display = "block";
			var sList = document.getElementById("suggestionsList~" + controlId);
			var ul = null;
			ul = document.createElement('ul');
			for ( var i = 0; i < temp.length; i++) {

				var li = document.createElement('li');

				li.innerHTML = temp[i].designationName;
				li.setAttribute("onClick", "fillTextField('" + temp[i].designationName + "', controlId)");
				ul.appendChild(li);
			}
			sList.appendChild(ul);
		}
	});
	removeList(controlId);
}

function fillTextField(txt, controlId) {
	document.getElementById("desgName~" + controlId).value = txt;
	document.getElementById("suggestions~" + controlId).style.display = "none";

	removeList(controlId);
}

function removeList(controlId) {
	var sList = document.getElementById("suggestionsList~" + controlId);
	var children = sList.childNodes;
	for ( var i = 0; i < children.length; i++) {
		sList.removeChild(children[i]);
	}
	document.getElementById("suggestions~" + controlId).style.display = "none";
}

function DynamicText(divs) {
	var isError = validationDesigForm();
	if (isError == 0) {
		var tmptextE = new Array();
		var tempchk = new Array();
		var tempchk2 = new Array();
		var tempCombo = new Array();

		if (dynstart == 0) {
			dynstart = i;
		}
		dynend = i;
		for ( var j = dynstart; j < dynend; j++) {
			if (document.getElementById("desgName~" + j)) {
				if (document.getElementById("desgName~" + j) != null) {
					tmptextE[j] = document.getElementById("desgName~" + j).value;
					tempchk[j] = document.getElementById("isMultipleChk" + j).checked;
					tempchk2[j] = document.getElementById("isLevelChk" + j).checked;
					tempCombo[j] = document.getElementById("orgLevel" + j).value;
				}
			}
		}

		divs.innerHTML += "<div id=div"
				+ i
				+ ">"
				+ "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ "<input type='checkbox' id='isMultipleChk"
				+ i
				+ "' /></td>"
				+ "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
				+ "<td><input type='checkbox' id='isLevelChk"
				+ i
				+ "' onchange='showOrgLevel(this.id)'/></td><td>&nbsp;</td>"
				+ "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ "<input type='text' name='desgName' id='desgName~" + i + "' class='frmfield' style='width:200px'/></td>"
				+ "<td>&nbsp;&nbsp;</td><td><select name='orgLevel' id='orgLevel" + i + "' style='width:100px;visibility: hidden;'/></td>"
				+ "<td>&nbsp;&nbsp;</td><td><input type='button' value='delete' onclick='div" + i + ".parentNode.removeChild(div" + i
				+ ")'/></td></div>";

		var dataq = [ {
			select : "Select"
		} ];
		dwr.util.addOptions('orgLevel' + i, dataq, "select");
		dwr.util.addOptions('orgLevel' + i, globaldata, valueText1, nameText1);
		for ( var j = dynstart; j < dynend; j++) {
			if (document.getElementById("desgName~" + j)) {
				if (document.getElementById("desgName~" + j) != null) {
					document.getElementById("desgName~" + j).value = tmptextE[j];
					document.getElementById("isMultipleChk" + j).checked = tempchk[j];
					document.getElementById("isLevelChk" + j).checked = tempchk2[j];
					document.getElementById("orgLevel" + j).value = tempCombo[j];
				}
			}
		}
		i++;
	}
}
function checkBox() {

	document.getElementById("isMultiple").value = "";
	document.getElementById("isLevelSpecific").value = "";
	document.getElementById("levelSpecificCode").value = "";

	document.getElementById("isMultiple").value = document.getElementById("isMultipleChk0").checked + ",";
	document.getElementById("isLevelSpecific").value = document.getElementById("isLevelChk0").checked + ",";
	document.getElementById("levelSpecificCode").value = "0" + ",";
	if (document.getElementById("isLevelChk0").checked == true) {
		document.getElementById("levelSpecificCode").value = document.getElementById("orgLevel0").value + ",";
	}
	for ( var z = 50; z < i; z++) {
		document.getElementById("isMultiple").value += document.getElementById("isMultipleChk" + z).checked + ",";
		document.getElementById("isLevelSpecific").value += document.getElementById("isLevelChk" + z).checked + ",";
		if (document.getElementById("isLevelChk" + z).checked == true) {
			document.getElementById("levelSpecificCode").value += document.getElementById("orgLevel" + z).value + ",";
		} else {
			document.getElementById("levelSpecificCode").value += 0 + ",";
		}
	}
	var isError = validationDesigForm();
	if (isError == 0) {
		document.getElementById('orgType').disabled = false;
		document.getElementById('orgCode').disabled = false;
		document.forms['designationForm'].submit();
	}
}
function getOrgLocatedbyOrgCode(id) {
	lgdDwrOrganizationService.getOrgLocatedbyOrgCode(id, {
		async : true,
		callback : handleOrgLocatedAt,
		errorHandler : handlehandleOrgLocatedAtError
	});
}
function handleOrgLocatedAt(data) {
	globaldata = data;
	var fieldId = 'orgLevel0';
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

function getOrgbyTypeAtLevel(id, level, stateCode) {

	var hdnstateCode = dwr.util.getValue('hdnStateCode');

	if (hdnstateCode > 0) {
		stateCode = hdnstateCode;
	} else {
		stateCode = 0;
	}

	lgdDwrOrganizationService.getOrgbyTypeCodeAtlevel(id, level, stateCode, {
		async : true,
		callback : handleOrgTypeLevelSuccess,
		errorHandler : handleOrgTypeLevelError
	});
}

function handleOrgTypeLevelSuccess(data) {
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

function handleOrgTypeLevelError() {
	dwr.util.removeAllOptions('ddDept');
	alert("No data found!");
}

function getOrgAtLevel(id, orglevels) {

	lgdDwrOrganizationService.getOrgAtLevels(id, orglevels, {
		callback : handleOrgAtLevelSuccess,
		errorHandler : handleOrgAtLevelError
	});

}

function handleOrgAtLevelSuccess(data) {
	var fieldId = 'locatedAtLevel';
	var valueText = 'org_located_level_code';
	var nameText = 'located_at_level';
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		select : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "select");
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleOrgAtLevelError() {
	dwr.util.removeAllOptions('ddDept');
	alert("No data found!");
}

/*
 * function validationDesigForm(){ var error=0; var itr=0; var
 * objOrgType=document.getElementById("orgType"); var
 * objOrg=document.getElementById("orgCode"); var
 * objTopDesig=document.getElementById("desgName~0");
 * 
 * if(objOrgType.value==0){ error=1; itr=1; //alert("Kindly select the
 * Organization Type from the list."); } if(itr==0 && objOrg.value==0 ||
 * objOrg.value=="Select"){ error=1; itr=1; //alert("Kindly select the
 * Organization from the list."); } if(itr==0 && objTopDesig.value==0){ error=1;
 * itr=1; //alert("Kindly fill the Top Designation."); } return error; }
 */
function onSubmit() {

	var errors = new Array();
	var error = false;

	errors[0] = vlidateOnblur('orgType', '1', '15', 'c');
	if (errors[0] == true) {
		error = true;
	}
	errors[1] = vlidateOnblur('orgCode', '1', '15', 'c');
	if (errors[1] == true) {
		error = true;
	}
	if (error == true) {

		showClientSideError();

		return false;
	} else {
		document.getElementById('orgType').disabled = false;
		document.getElementById('orgCode').disabled = false;
		document.forms['designationForm'].submit();
		return true;
	}

}
function getOrgDesignation(id) {
	lgdDwrOrganizationService.getOrgDesignationDetails(id, {
		callback : handleOrgDesigSuccess,
		errorHandler : handleeOrgDesigError
	});

	function handleOrgDesigSuccess(data) {
		var fieldId = 'orgDesigDetails';
		document.getElementById(fieldId).value = "";
		dwr.util.setValue(fieldId, data.designationName);
		getOrgDesig();
	}
	function handleeOrgDesigError() {
		// Show a popup message
	}

}

function getOrgDesig() {
	var txtN = i;
	dynamicDiv.innerHTML = "";
	if (document.getElementById("orgDesigDetails").value != "") {
		document.getElementById('orgType').disabled = 'true';
		document.getElementById('orgCode').disabled = 'true';
		temptxt = document.getElementById("orgDesigDetails").value.split("~");
		tempDE = temptxt[0].split(",");
		document.getElementById("desgName~0").value = tempDE[1];
		if (tempDE[2] > 0) {
			document.getElementById("isLevelChk0").checked = "true";
			document.getElementById('orgLevel0').style.visibility = 'visible';
			document.getElementById("orgLevel0").value = tempDE[2];
		}
		for ( var k = 1; k < temptxt.length - 1; k++) {
			tempDE = temptxt[k].split(",");
			DynamicText(dynamicDiv);
			if (tempDE[0] == "true") {
				document.getElementById("isMultipleChk" + txtN).checked = "true";
			}
			if (tempDE[2] > 0) {
				document.getElementById('orgLevel' + txtN).style.visibility = 'visible';
				document.getElementById("isLevelChk" + txtN).checked = "true";
				document.getElementById("orgLevel" + txtN).value = tempDE[2];
			}
			document.getElementById("desgName~" + txtN).value = tempDE[1];
			txtN++;
		}
	}
	document.getElementById('Back').style.visibility = 'visible';
	document.getElementById("orgDesigDetails").value = "";
}
function refresh() {
	javascript: location.reload(true);
}
