 /*
	*By Pranav Tiwari
	*Added on 21 Sep 2016
	*setParentOrgUnitCenter.js
	*Ministry and Department wise 
	*/

getTopLevelEntityByType=function(type){
	if(type==2){
		$("#commonDiv").show();
		$("#selOrgDiv").hide();
		$("#minDiv").show();
		$("#deptDiv").show();
		
	}else{
		$("#commonDiv").show();
		$("#selOrgDiv").show();
		$("#minDiv").show();
		$("#deptDiv").hide();
	}
	$('#orgCode').find('option:gt(0)').remove();
	$('#orgType').find('option:gt(0)').remove();
	dwr.util.removeAllOptions('orgDeptType');
	dwr.util.removeAllOptions('orgLevelDept');
	dwr.util.removeAllOptions('orgLevel');
	dwr.util.removeAllOptions('parentLevelOrg');
	dwr.util.removeAllOptions('sourceOrgList');
	dwr.util.removeAllOptions('contributedOrgist');
	var slc = $("#stateCode").val();
	lgdAdminDepatmentDwr.getOrganizationbyCriteria(slc,1, {
		callback : getTopEntityByType,
	});
	
};

function getTopEntityByType(data) {
	dwr.util.removeAllOptions('orgCode');
	st = document.getElementById('orgCode');
	st.add(new Option('--- Select --', '0'));
	var fieldId = 'orgCode';
	var valueText = 'olc';
	var nameText = 'orgName';
	if (data.length == 0) {
		alert("No Record Found");
	} else {
		dwr.util.addOptions(fieldId, data, valueText, nameText);
	}
}

function getParentLevelEntity(parentCode) {
   dwr.util.removeAllOptions('orgDeptType');
	dwr.util.removeAllOptions('orgLevelDept');
	dwr.util.removeAllOptions('orgLevel');
	dwr.util.removeAllOptions('parentLevelOrg');
	dwr.util.removeAllOptions('sourceOrgList');
	dwr.util.removeAllOptions('contributedOrgist');
var orgTypeCode=$("#topLevelEntityType").val();
if(orgTypeCode==3){orgTypeCode=orgTypeCode-1;}
	lgdAdminDepatmentDwr.getCenterOrganizationbyCriteria(orgTypeCode,parentCode, { 
		callback : insertDepartments,
		errorHandler : handleOrgnizationError
	});
}

function insertDepartments(data) { 
	dwr.util.removeAllOptions('orgType');
	var fieldId = 'orgType';
	var valueText = 'olc';
	var nameText = 'orgName';
	if (data.length == 0){
		alert("No record found");
	}else{
		st = document.getElementById('orgType');
		st.add(new Option('--- Select --', '0'));
		dwr.util.addOptions(fieldId, data, valueText, nameText);
	}
	
}
function handleOrgnizationError() {
	alert("Error!");
}

function getLevelofDepartment(deptCode){
	var orgTypeCode=$("#topLevelEntityType").val();
	dwr.util.removeAllOptions('orgLevelDept');
	dwr.util.removeAllOptions('orgLevel');
	dwr.util.removeAllOptions('parentLevelOrg');
	dwr.util.removeAllOptions('sourceOrgList');
	dwr.util.removeAllOptions('contributedOrgist');
	if(orgTypeCode==3){
		lgdAdminDepatmentDwr.getCenterOrganizationbyCriteria(orgTypeCode,deptCode, { 
			callback : orgOfDepartments,
		});
	}else{
	lgdDwrOrganizationService.getOrgLocatedbyOrgCodemodify(deptCode, {
		callback : insertDepartmentLevel,
		
	});}
}
function orgOfDepartments(data){
	
	dwr.util.removeAllOptions('orgDeptType');
	var fieldId = 'orgDeptType';
	var valueText = 'olc';
	var nameText = 'orgName';
	if (data.length == 0){
		alert("No record found");
	}else{
		st = document.getElementById('orgDeptType');
		st.add(new Option('--- Select --', '0'));
		dwr.util.addOptions(fieldId, data, valueText, nameText);
	}
	
}
function getLevelofOrganisation(){
	
	var unitCode = $("#orgDeptType").val();
	dwr.util.removeAllOptions('parentLevelOrg');
	dwr.util.removeAllOptions('sourceOrgList');
	dwr.util.removeAllOptions('contributedOrgist');
	lgdDwrOrganizationService.getOrgLocatedbyOrgCodemodify(unitCode,{
		callback : insertDepartmentLevel,
	});
}


function insertDepartmentLevel(data){
	var orgTypeCode=$("#topLevelEntityType").val();
	//dwr.util.removeAllOptions('orgLevelDept');
	var orgDept = "";
	if(orgTypeCode==3){
		orgDept = "Organisation";
		dwr.util.removeAllOptions('orgLevelDept');
		var st = '';
		st = document.getElementById('orgLevelDept');
		st.add(new Option('--- Select --', '0'));
	}else{
		orgDept = "Department";
		dwr.util.removeAllOptions('orgLevel');
		var st = '';
		st = document.getElementById('orgLevel');
		st.add(new Option('--- Select --', '0'));
	}
	
	
	for ( var i = 0; i < data.length; i++) {
		if (data[i].locatedAtLevel == 0)
			st.add(new Option('Center Level ' +orgDept , '0'));
		else if (data[i].locatedAtLevel == 1)
			st.add(new Option('State Level '+ orgDept, '1'));
		else if (data[i].locatedAtLevel == 2)
			st.add(new Option('District Level '+orgDept, '2'));
		else if (data[i].locatedAtLevel == 3)
			st.add(new Option('SubDistrict Level '+orgDept, '3'));
		else if (data[i].locatedAtLevel == 4)
			st.add(new Option('Village Level '+orgDept, '4'));
		else if (data[i].locatedAtLevel == 5)
			st.add(new Option('Block Level '+orgDept, '5'));
		else if (data[i].locatedAtLevel > 5)
			st.add(new Option(data[i].unitLevelNameEnglish + ' Level '+orgDept, data[i].locatedAtLevel));
		else if (data[i].locatedAtLevel < 0)
			st.add(new Option(data[i].unitLevelNameEnglish + ' Level '+orgDept, data[i].locatedAtLevel));
	}
}

function getparentOrganizations(value){ 
	var orgTypeCode=$("#topLevelEntityType").val();
	if(orgTypeCode==3){
		var orgCode = $("#orgDeptType").val();
	}else{
		var orgCode = $("#orgType").val();
	}
	
	dwr.util.removeAllOptions('parentLevelOrg');
	dwr.util.removeAllOptions('sourceOrgList');
	dwr.util.removeAllOptions('contributedOrgist');
	lgdDwrOrganizationService.getOrganizationParent(orgCode, value, {
	callback : getOrganizationParent,
	errorHandler : handleOrgnizationError
});
}

function getOrganizationParent(data){
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

function getChildOrganizations(){
	document.getElementById("parentOrganization").innerHTML = "";
	document.getElementById("childOrganization").innerHTML = "";
	dwr.util.removeAllOptions('sourceOrgList');
	dwr.util.removeAllOptions('contributedOrgist');
	var selObj = document.getElementById("parentLevelOrg");
	var temp = '';
	var count = 0;
	
	for ( var i = 0; i < selObj.options.length; i++) {
		if (selObj.options[i].selected == true) {
			temp = selObj.options[i].value;
			count++;
			break;
		}

	}
		
	if (count == 0) {

		document.getElementById("parentOrganization").innerHTML = "<font color='red'>Please select a organization unit to view its child organization units</font>";
		return false;

	} else if (count > 1) {
		document.getElementById("parentOrganization").innerHTML = "<font color='red'>Please Select Single Parent</font>";
		$('#parentLevelOrg option').removeAttr('selected');
		return false;

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
function getChildOrganizationsList(data) {

	var fieldId = 'sourceOrgList';
	var valueText = 'orgCode';
	var nameText = 'orgName';
	dwr.util.addOptions(fieldId, data, valueText, nameText);
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




function getSpecificChildOrganizations() {
	
	document.getElementById("parentOrganization").innerHTML = "";
	document.getElementById("childOrganization").innerHTML = "";
	dwr.util.removeAllOptions('contributedOrgist');
			
	var orgLevel=$("#orgLevel").val();
	var orgLevelDept = $("#orgLevelDept").val();
	if(orgLevel==0 || orgLevelDept == 0){
		shiftSuitableCenter();
	}else{
		
	dwr.util.removeAllOptions('sourceOrgList');	
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
		errorHandler : handleOrgnizationError
	});

	lgdDwrOrganizationService.getOrganizationChilds(temp, {
		callback : getChildWithSpecificOrgList,
		errorHandler : handleOrgnizationError
	});
 }
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

function shiftSuitableCenter(){ 
	var selObj = document.getElementById("parentLevelOrg");
	var temp = selObj.options[0].value;

	lgdDwrOrganizationService.getOrganizationChilds(temp, {
		callback : getShifted,
		errorHandler : handleOrgnizationError
	});
}
function getShifted(data){
	dwr.util.removeAllOptions('sourceOrgList');
	var fieldId = 'contributedOrgist';
	var valueText = 'orgCode';
	var nameText = 'orgName';
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}
