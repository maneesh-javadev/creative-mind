/**
 * @author Chandra shekhar joshi File Name : setParentOrgUnit.js
 * @Date 07 August 2014
 * @Modified on 05 September 2014
 */
function getTopLevelEntityByType(val) {

	document.getElementById("topLevelType").innerHTML = "";
	document.getElementById("parentAdminOrg").innerHTML = "";
	document.getElementById("parentAdminOrgLevel").innerHTML = "";
	document.getElementById("parentOrganization").innerHTML = "";
	document.getElementById("childOrganization").innerHTML = "";
	$('#orgCode').find('option:gt(0)').remove();
	$('#orgType').find('option:gt(0)').remove();
	dwr.util.removeAllOptions('sourceOrgList');
	dwr.util.removeAllOptions('contributedOrgist');
	var slc = $("#stateCode").val();
	lgdAdminDepatmentDwr.getOrganizationbyCriteria(slc, val, {
		callback : getTopEntityByType,
	});
}
function getTopEntityByType(data) {
	dwr.util.removeAllOptions('parentLevelOrg');
	var fieldId = 'orgCode';
	var valueText = 'olc';
	var nameText = 'orgName';
	if (data.length == 0) {
		alert("No Record Found");
	} else {
		dwr.util.addOptions(fieldId, data, valueText, nameText);
	}
}

function getParentLevelEntity() {
	document.getElementById("parentAdminOrg").innerHTML = "";
	document.getElementById("parentAdminOrgLevel").innerHTML = "";
	document.getElementById("parentOrganization").innerHTML = "";
	document.getElementById("childOrganization").innerHTML = "";
	dwr.util.removeAllOptions('sourceOrgList');
	dwr.util.removeAllOptions('contributedOrgist');
	dwr.util.removeAllOptions('parentLevelOrg');
	var unitCode = parseInt($("#orgCode").val());
	
	lgdAdminDepatmentDwr.getOrgLocatedbyOrgCodeNew(unitCode, {
		callback : insertOrgaizationLevel,
		errorHandler : handleOrgnizationError
	});
}

function getParentLevelEntity_Load(unitCode) {
	
	
	lgdDwrOrganizationService.getOrgLocatedbyOrgCodemodify(unitCode, {
		callback : insertOrgaizationLevel,
		errorHandler : handleOrgnizationError
	});
}

function insertOrgaizationLevel(data) {

	dwr.util.removeAllOptions('orgType');
	var st = '';
	st = document.getElementById('orgType');
	st.add(new Option('--- Select --', '0'));
	for ( var i = 0; i < data.length; i++) {
		if (data[i].locatedAtLevel == 1)
			st.add(new Option('State Level Organization', '1'));
		else if (data[i].locatedAtLevel == 2)
			st.add(new Option('District Level Organization', '2'));
		else if (data[i].locatedAtLevel == 3)
			st.add(new Option('SubDistrict Level Organization', '3'));
		else if (data[i].locatedAtLevel == 4)
			st.add(new Option('Village Level Organization', '4'));
		else if (data[i].locatedAtLevel == 5)
			st.add(new Option('Block Level Organization', '5'));
		else if (data[i].locatedAtLevel > 5 || data[i].locatedAtLevel < 0)
			// modified by Pranav to get local body type on  29 Nov
			st.add(new Option(data[i].unitLevelNameEnglish + ' Level Organization', data[i].locatedAtLevel));
	}
}
function handleOrgnizationError() {
	alert("error");
}
function getparentOrganizations(val) {
	document.getElementById("parentAdminOrgLevel").innerHTML = "";
	document.getElementById("parentOrganization").innerHTML = "";
	document.getElementById("childOrganization").innerHTML = "";
	dwr.util.removeAllOptions('sourceOrgList');
	dwr.util.removeAllOptions('contributedOrgist');
	var orgCode = $("#orgCode").val();
	lgdDwrOrganizationService.getOrganizationParent(orgCode, val, {
		callback : getOrganizationParent,
		errorHandler : handleOrgnizationError
	});
}

function getparentOrganizations_load(val,orgCode) {
	document.getElementById("parentAdminOrgLevel").innerHTML = "";
	document.getElementById("parentOrganization").innerHTML = "";
	document.getElementById("childOrganization").innerHTML = "";
	dwr.util.removeAllOptions('sourceOrgList');
	dwr.util.removeAllOptions('contributedOrgist');
	lgdDwrOrganizationService.getOrganizationParent(orgCode, val, {
		callback : getOrganizationParent,
		errorHandler : handleOrgnizationError
	});
}

function getOrganizationParent(data) {
	dwr.util.removeAllOptions('parentLevelOrg');
	var fieldId = 'parentLevelOrg';
	var valueText = 'orgCode';
	var nameText = 'orgName';
	if (data.length == 0) {
		alert("No Record Found");
	} else {
		dwr.util.addOptions(fieldId, data, valueText, nameText);
	}
}

function getOrgChildOrganizations() {
	var selObj = document.getElementById("parentLevelOrg");
	var temp = '';
	for ( var i = 0; i < selObj.options.length; i++) {
		if (selObj.options[i].selected == true) {
			temp = selObj.options[i].value;
			break;
		}

	}
	lgdDwrOrganizationService.getOrganizationChilds(temp, {
		callback : getChildOrganizationsList,
		errorHandler : handleOrgnizationError
	});

	lgdDwrOrganizationService.getOrganizationMappedChilds(temp, {
		callback : getMappedChildOrganizationsList,
		errorHandler : handleOrgnizationError
	});

}
function getMappedChildOrganizationsList(data) {

	var fieldId = 'contributedOrgist';
	var valueText = 'orgCode';
	var nameText = 'orgName';
	dwr.util.addOptions(fieldId, data, valueText, nameText);

	var temp = '';
	var selObj = document.getElementById('sourceOrgList');
	for ( var i = 0; i < data.length; i++) {
		temp = data[i].orgCode;
		for ( var j = 0; j < selObj.options.length; j++) {
			if (selObj.options[j].value == temp) {
				selObj.remove(j);
				break;
			}
		}
	}
}

function getChildOrganizationsList(data) {

	var fieldId = 'sourceOrgList';
	var valueText = 'orgCode';
	var nameText = 'orgName';
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function addOrgUnits(list1, list2) {
	document.getElementById("childOrganization").innerHTML = "";
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++)
			if (document.getElementById(list2).options[j].selected == true) {
				$('#' + list1).append(
						"<option value=" + document.getElementById(list2)[j].value + ">" + document.getElementById(list2)[j].text + "</option>");

			}
		removeSelectedValue(list2);
	}
}

function removeSelectedValue(val) {
	var elSel = document.getElementById(val);
	for ( var i = elSel.length - 1; i >= 0; i--) {
		if (elSel.options[i].selected) {
			elSel.remove(i);
		}
	}
}

function removeOrgUnits(list1, list2) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				$('#' + list2).append(
						"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length) + " >"
								+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length) + "</option>");
			}
		}
		removeSelectedValue(list1);
	}
}

function getChildOrganizations() {
	document.getElementById("parentOrganization").innerHTML = "";
	document.getElementById("childOrganization").innerHTML = "";
	dwr.util.removeAllOptions('sourceOrgList');
	dwr.util.removeAllOptions('contributedOrgist');
	var selObj = document.getElementById("parentLevelOrg");
	var count = 0;
	for ( var i = 0; i < selObj.options.length; i++) {
		if (selObj.options[i].selected == true)
			count++;
	}
	if (count == 0) {

		document.getElementById("parentOrganization").innerHTML = "<font color='red'>Please select a organization unit to view its child organization units</font>";
		return false;

	} else if (count > 1) {
		document.getElementById("parentOrganization").innerHTML = "<font color='red'>Please Select Single Parent</font>";
		$('#parentLevelOrg option').removeAttr('selected');
		return false;

	}
	getOrgChildOrganizations();
}

function ValidateAndSubmitforEntity() {
	document.getElementById("topLevelType").innerHTML = "";
	document.getElementById("parentAdminOrg").innerHTML = "";
	document.getElementById("parentAdminOrgLevel").innerHTML = "";
	document.getElementById("parentOrganization").innerHTML = "";
	document.getElementById("childOrganization").innerHTML = "";
	var orgtypeIndex = document.getElementById('topLevelEntityType').selectedIndex;
	var orgindex = document.getElementById('orgCode').selectedIndex;
	var typeindex = document.getElementById('orgType').selectedIndex;
	var count = 0;
	var status = true;
	if (orgtypeIndex == 0) {
		document.getElementById("topLevelType").innerHTML = "<font color='red'>Please Select Organization Type </font>";
		return false;

	} else if (orgindex == 0) {
		document.getElementById("parentAdminOrg").innerHTML = "<font color='red'>Please Select an Organization</font>";
		return false;

	} else if (typeindex == 0) {
		document.getElementById("parentAdminOrgLevel").innerHTML = "<font color='red'>Please Select Level Of Organization</font>";
		return false;

	}
	var selObj = document.getElementById("parentLevelOrg");
	for ( var i = 0; i < selObj.options.length; i++) {
		if (selObj.options[i].selected == true)
			count++;
	}
	if (count == 0) {

		document.getElementById("parentOrganization").innerHTML = "<font color='red'>Please select a parent organization unit to view its child organization units</font>";
		return false;

	} else if (count > 1) {
		document.getElementById("parentOrganization").innerHTML = "<font color='red'>Please Select Single Parent</font>";
		return false;

	}
	selObj = document.getElementById("contributedOrgist");
	if (selObj.options.length == 0) {
		document.getElementById("childOrganization").innerHTML = "<font color='red'>Please select child organization units to be mapped with parent organization unit</font>";
		return false;

	}
	// $("#childOrgCode").val()=
	var temp = '';
	var selObj = document.getElementById('contributedOrgist');
	for ( var i = 0; i < selObj.options.length; i++) {
		temp = selObj.options[i].value + "," + temp;
	}
	temp = temp.substring(0, temp.length - 1);
	$("#childOrgCode").val(temp);
	$("#parentOrgCode").val($("#parentLevelOrg").val());
	// alert($("#parentLevelOrg").val());
	if (status) {
		$('#dialog-confirm').modal('show');
		
		/*$("#dialog:ui-dialog").dialog("destroy");
		$("#dialog-confirm").dialog({
			resizable : false,
			height : 140,
			modal : true,
			buttons : {
				"Yes" : function() {
					document.forms['form1'].method = "post";
					document.forms['form1'].action = "setOrgParentChilds.htm?<csrf:token uri='setOrgParentChilds.htm'/>";
					document.forms['form1'].submit();
					$(this).dialog("close");
				},
				Cancel : function() {
					$(this).dialog("close");
				}
			}
		});*/

	}
}
function emptyElements() {
	
	$('#dialog-clear').modal('show');
	/*$("#dialog:ui-dialog").dialog("destroy");
	$("#dialog-clear").dialog({
		resizable : false,
		height : 140,
		modal : true,
		buttons : {
			"Yes" : function() {
				$('#topLevelEntityType').attr('selectedIndex', 0);
				$('#orgCode').find('option:gt(0)').remove();
				$('#orgType').find('option:gt(0)').remove();
				$("#parentLevelOrg").empty();
				$("#sourceOrgList").empty();
				$("#contributedOrgist").empty();
				$(this).dialog("close");
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		}
	});*/

}

function getSpecificChildOrganizations() {
	document.getElementById("parentOrganization").innerHTML = "";
	document.getElementById("childOrganization").innerHTML = "";
	dwr.util.removeAllOptions('sourceOrgList');
	dwr.util.removeAllOptions('contributedOrgist');
	var selObj = document.getElementById("parentLevelOrg");
	var temp = '';
	for ( var i = 0; i < selObj.options.length; i++) {
		if (selObj.options[i].selected == true) {
			temp = selObj.options[i].value;
			break;
		}

	}
	lgdDwrOrganizationService.getOrganizationMappedChilds(temp, {
		callback : getMappedChildOrganizationsList,
		errorHandler : handleOrgnizationError	});

	lgdDwrOrganizationService.getOrganizationChilds(temp, {
		callback : getChildWithSpecificOrgList,
		errorHandler : handleOrgnizationError
	});

}
function getChildWithSpecificOrgList(data) {

	var fieldId = 'sourceOrgList';
	var valueText = 'orgCode';
	var nameText = 'orgName';
	dwr.util.addOptions(fieldId, data, valueText, nameText);
	var selObj = document.getElementById("parentLevelOrg");
	var temp = '';
	if (data.length > 0) {
		for ( var i = 0; i < selObj.options.length; i++) {
			if (selObj.options[i].selected == true) {
				temp = selObj.options[i].value;
				break;
			}

		}
		lgdDwrOrganizationService.getOrganizationSpecificChilds(temp, {
			callback : getspecificChildOrganizationsList,
			errorHandler : handleOrgnizationError
		});
	}
}

function getspecificChildOrganizationsList(data) {
	var fieldId = 'contributedOrgist';
	var valueText = 'orgCode';
	var nameText = 'orgName';

	dwr.util.addOptions(fieldId, data, valueText, nameText);

	var temp = '';
	var selObj = document.getElementById('sourceOrgList');
	for ( var i = 0; i < data.length; i++) {
		temp = data[i].orgCode;
		for ( var j = 0; j < selObj.options.length; j++) {
			if (selObj.options[j].value == temp) {
				selObj.remove(j);
				break;
			}
		}
	}

}
function clearOrgsUnitsData() {
	$("#sourceOrgList").empty();
	$("#contributedOrgist").empty();
}
