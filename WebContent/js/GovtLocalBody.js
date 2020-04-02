//DWR Dropdownlist Population
var t = 4;
var dynstart = 0;
var dynend = 0;

// Author Sarva
function resets() {
	location.reload(true);
}

// Hide and show of different lists of local body
function getHideLocalBodyParentList(id, checked) {
	// alert("ID----------"+id+":::checked-----"+checked);
	var temp = id.split(":");
	// alert("Temp---2-"+temp[0]);
	var id1 = temp[0];

	if (checked)
		switch (id1) {
		case "1":

			document.getElementById("tr_district_List").style.visibility = "visible";
			document.getElementById("tr_district_List").style.display = "block";

			document.getElementById("tr_coveredArea").style.visibility = "visible";
			document.getElementById("tr_coveredArea").style.display = "block";

			document.getElementById("tr_intermediate_List").style.visibility = "hidden";
			document.getElementById("tr_intermediate_List").style.display = "none";

			document.getElementById("tr_village").style.visibility = "hidden";
			document.getElementById("tr_village").style.display = "none";

			break;
		case "2":

			document.getElementById("tr_district_List").style.visibility = "hidden";
			document.getElementById("tr_district_List").style.display = "none";

			document.getElementById("tr_intermediate_List").style.visibility = "visible";
			document.getElementById("tr_intermediate_List").style.display = "block";

			document.getElementById("tr_coveredArea").style.visibility = "visible";
			document.getElementById("tr_coveredArea").style.display = "block";

			document.getElementById("tr_village").style.visibility = "hidden";
			document.getElementById("tr_village").style.display = "none";

			break;
		case "3":

			document.getElementById("tr_village").style.visibility = "visible";
			document.getElementById("tr_village").style.display = "block";

			document.getElementById("tr_coveredArea").style.visibility = "visible";
			document.getElementById("tr_coveredArea").style.display = "block";

			document.getElementById("tr_district_List").style.visibility = "hidden";
			document.getElementById("tr_district_List").style.display = "none";

			document.getElementById("tr_intermediate_List").style.visibility = "hidden";
			document.getElementById("tr_intermediate_List").style.display = "none";

			break;
		default:

			document.getElementById("tr_district_List").style.visibility = "hidden";
			document.getElementById("tr_district_List").style.display = "none";

			document.getElementById("tr_intermediate_List").style.visibility = "hidden";
			document.getElementById("tr_intermediate_List").style.display = "none";

			document.getElementById("tr_village").style.visibility = "hidden";
			document.getElementById("tr_village").style.display = "none";

			document.getElementById("tr_coveredArea").style.visibility = "hidden";
			document.getElementById("tr_coveredArea").style.display = "none";
		}
	else {

		document.getElementById("tr_district_List").style.visibility = "hidden";
		document.getElementById("tr_district_List").style.display = "none";

		document.getElementById("tr_intermediate_List").style.visibility = "hidden";
		document.getElementById("tr_intermediate_List").style.display = "none";

		document.getElementById("tr_village").style.visibility = "hidden";
		document.getElementById("tr_village").style.display = "none";

		document.getElementById("tr_coveredArea").style.visibility = "hidden";
		document.getElementById("tr_coveredArea").style.display = "none";
	}

}
// End here Local body for list

// for create Local Body
function hideShowDistIV(id) {

	// alert("ID----2------"+id);
	var temp = id.split(":");
	// alert("Temp--3--"+temp[0]);
	var id1 = temp[0];

	switch (id1) {
	case "1":
		document.getElementById("tr_district").style.visibility = "hidden";
		document.getElementById("tr_district").style.display = "none";

		document.getElementById("tr_intermediate").style.visibility = "hidden";
		document.getElementById("tr_intermediate").style.display = "none";

		break;
	case "2":
		document.getElementById("tr_district").style.visibility = "visible";
		document.getElementById("tr_district").style.display = "block";

		document.getElementById("tr_intermediate").style.visibility = "hidden";
		document.getElementById("tr_intermediate").style.display = "none";
		break;
	case "3":

		document.getElementById("tr_district").style.visibility = "visible";
		document.getElementById("tr_district").style.display = "block";

		document.getElementById("tr_intermediate").style.visibility = "visible";
		document.getElementById("tr_intermediate").style.display = "block";

		break;
	default:
		document.getElementById("tr_district").style.visibility = "hidden";
		document.getElementById("tr_district").style.display = "none";

		document.getElementById("tr_intermediate").style.visibility = "hidden";
		document.getElementById("tr_intermediate").style.display = "none";

	}

}

// end here local body type

function getHideUnmappedList(checked) {

	if (checked) {

		document.getElementById("divLgdLBDistCAreaUnmapped").style.visibility = "visible";
		document.getElementById("divLgdLBDistCAreaUnmapped").style.display = "block";
	} else {
		document.getElementById("divLgdLBDistCAreaUnmapped").style.visibility = "hidden";
		document.getElementById("divLgdLBDistCAreaUnmapped").style.display = "none";
	}
}

// End here modify NamegetHideCoverageArea
// Modify Type of Parent of Rural and Traditional LocalBody
/*
 * function getHideModifyParentList(checked) { if (checked){
 * 
 * document.getElementById("modify_parent_list").style.visibility="visible";
 * document.getElementById("modify_parent_list").style.display="block"; }else{
 * document.getElementById("modify_parent_list").style.visibility="hidden";
 * document.getElementById("modify_parent_list").style.display="none"; } }
 */
// End here Modify Rural or Traditional Localbodies
// Modify Type of Local body
/*
 * function getHideModifyTypeList(checked) { if (checked){
 * 
 * document.getElementById("modify_type").style.visibility="visible";
 * document.getElementById("modify_type").style.display="block"; }else{
 * document.getElementById("modify_type").style.visibility="hidden";
 * document.getElementById("modify_type").style.display="none"; } }
 */

// End here Type of Localbody
// Modify Coverage Area of Localbody
/*
 * function getHideCoverageArea(checked) { if (checked){
 * 
 * document.getElementById("divLgdLBCoveredArea").style.visibility="visible";
 * document.getElementById("divLgdLBCoveredArea").style.display="block"; }else{
 * document.getElementById("divLgdLBCoveredArea").style.visibility="hidden";
 * document.getElementById("divLgdLBCoveredArea").style.display="none"; } }
 */
// End of Coverage Area
/*
 * function handleLocGovtBodySuccess(data) { // Assigns data to result id //
 * alert("DATA---2-8--"+data); var fieldId = 'localGovtBodySubDistListMain'; var
 * valueText = 'localBodyCode'; var nameText = 'localBodyNameEnglish'; //
 * alert("Code--"+valueText+"::nameText--"+nameText);
 * dwr.util.removeAllOptions(fieldId); var dataq = [ {name:"Select Intermediate
 * Body"} ]; dwr.util.addOptions(fieldId, dataq,'0', "name");
 * dwr.util.addOptions(fieldId, data, valueText, nameText); }
 * 
 * function handleLocGovtBodyError() { // Show a popup message alert("No data
 * found!"); }
 */

// getLocalGovtBody's ParentListList
function getLocalBodyParentList(id) {

	lgdDwrlocalBodyService.getLocalGovtBodyParentList(id, {
		callback : govtBodyParentSuccess,
		errorHandler : govtBodyParentError
	});

}

// data contains the returned value
function govtBodyParentSuccess(data) {
	// Assigns data to result id
	// alert("DATA--12221-2---"+data);
	var fieldId = 'localGovtBodyParentList'; // tierSetupCode,nomenclatureEnglish
	var valueText = 'tierSetupCode';
	var nameText = 'nomenclatureEnglish';
	// alert("Code--"+valueText+"::nameText--"+nameText);
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		name : "Select Local Body"
	} ];
	dwr.util.addOptions(fieldId, dataq, '0', "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function govtBodyParentError() {
	// Show a popup message
	alert("No data found!");
}

// End of ParentList acording to localbody

// Get Covered Land Region getCoveredLandRegion

function getCoveredLandRegion(id) {
	// alert("ID---getCovered--"+id);
	lgdDwrlocalBodyService.getLocalGovtBodyCoveredVillageList(id, {
		callback : getVillageSuccess,
		errorHandler : getVillageError
	});
}

// data contains the returned value
function getVillageSuccess(data) {
	// Assigns data to result id
	// alert("DATA--"+data)
	var fieldId = 'ddCoveredVillageList';
	var valueText = 'villageCode';
	var nameText = 'villageNameEnglish';
	// villageCode,villageNameEnglish
	dwr.util.removeAllOptions(fieldId);
	// dwr.util.addOptions(fieldId, dataq, "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getVillageError() {
	// Show a popup message
	alert("No data found!");
}
// End here

// Get Covered Land Region For Sub District

function getCoveredLandForSubDistRegion(id) {
	// id="501PART";
	lgdDwrlocalBodyService.getLocalGovtBodyforCoveredSubDistList(id, {
		callback : getSubDistSuccess,
		errorHandler : getSubDistError
	});
}

// data contains the returned value
function getSubDistSuccess(data) {
	// Assigns data to result id
	alert("DATA--" + data);
	var fieldId = 'ddCoveredVillageList';
	var valueText = 'subDistCode';
	var nameText = 'subDistNameEnglish';
	// villageCode,villageNameEnglish
	dwr.util.removeAllOptions(fieldId);
	// var dataq = [ {name:"Select Subdistrict"} ];
	// dwr.util.addOptions(fieldId, dataq, "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getSubDistError() {
	// Show a popup message
	alert("No data found!");
}
// End here

// Get Covered Land Region selectallDistrictName

/*
 * function selectallDistrictName() {
 * 
 * var selObj=document.getElementById('ddDestDistLocalGovtBody'); var
 * districtList=""; for (i = 0; i < selObj.options.length; i++) {
 * selObj.options[i].selected=true; districtList +=selObj.options[i].value+","; }
 * getCoveredLandRegionforDistrict(districtList); }
 */

function getCoveredLandRegionforDistrict(id) {
	// alert("ID-dist----"+id);
	lgdDwrlocalBodyService.getLocalGovtBodyforCoveredDistrictList(id, {
		callback : getDistrictSuccess,
		errorHandler : getDistrictError
	});
}

// data contains the returned value
function getDistrictSuccess(data) {
	// Assigns data to result id
	// alert("DATA--"+data);
	var fieldId = 'ddCoveredVillageList';
	var valueText = 'districtCode';
	var nameText = 'districNameEnglish';
	// villageCode,villageNameEnglish
	dwr.util.removeAllOptions(fieldId);
	// var dataq = [ {name:"Select Subdistrict"} ];
	// dwr.util.addOptions(fieldId, dataq, "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getDistrictError() {
	// Show a popup message
	alert("No data found!");
}
// End here selectallDistrictName

// for first subdistrict
function getSubDistrictList(id) {

	lgdDwrSubDistrictService.getSubDistrictList(id, {
		callback : handleSubDistrictSuccess,
		errorHandler : handleSubDistrictError
	});

}

// data contains the returned value
function handleSubDistrictSuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddSubdistrict';
	var valueText = 'subdistrictCode';
	var nameText = 'subdistrictNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		name : "Select Subdistrict"
	} ];
	dwr.util.addOptions(fieldId, dataq, "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleSubDistrictError() {
	// Show a popup message
	alert("No data found!");
}

// for second subdistrict
/*
 * function getSubIDistrictList(id) {
 * 
 * lgdDwrSubDistrictService.getSubDistrictList(id, { callback :
 * handleSubIDistrictSuccess, errorHandler : handleSubIDistrictError }); }
 * 
 * //data contains the returned value function handleSubIDistrictSuccess(data) { //
 * Assigns data to result id var fieldId = 'ddSubIdistrict'; var valueText =
 * 'subdistrictCode'; var nameText = 'subdistrictNameEnglish';
 * dwr.util.removeAllOptions(fieldId); dwr.util.addOptions(fieldId, data,
 * valueText, nameText); }
 * 
 * function handleSubIDistrictError() { // Show a popup message alert("No data
 * found!"); }
 */

// For Local Govt Body **
function getandsetLocalBodyList(id) {
	// alert("getandSet---"+id);
	getLocalBodyList(id);
	getLocalBodySubDistrictList(id);
	setLocalBodyList(id);

}

function setLocalBodyList(id) {
	document.getElementById('ddSourceLocalBodySubDistrict').selectedIndex = id;
}

function getLocalBodySubDistrictList(id) {

	lgdDwrLocalGovtBodyService.getLocalGovtBodySubDistList(id, {
		callback : handleLocalGovtBodySDSuccess,
		errorHandler : handleLocalGovtBodySDError
	});
}

function handleLocalGovtBodySDSuccess(data) {
	// Assigns data to result id
	// alert("DATA---2-55--"+data);
	var fieldId = 'localGovtBodyListSubDistrict';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	// alert("Code--"+valueText+"::nameText--"+nameText);
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		name : "Select Intermediate Body"
	} ];
	dwr.util.addOptions(fieldId, dataq, '0', "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleLocalGovtBodySDError() {
	// Show a popup message
	alert("No data found!");
}

function getLocalBodyListAtVillageLvl(id) {
	lgdDwrLocalGovtBodyService.getLocalGovtBodySubDistList(id, {
		callback : handleLocalGovtBodySuccessAtVillageLvl,
		errorHandler : handleLocalGovtBodyErrorAtVillageLvl
	});
}

// data contains the returned value
function handleLocalGovtBodySuccessAtVillageLvl(data) {
	// Assigns data to result id
	// alert("DATA---2-55--"+data);
	var fieldId = 'localGovtBodyListSubDistrict';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	// alert("Code--"+valueText+"::nameText--"+nameText);
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		name : "Select Intermediate Body"
	} ];
	dwr.util.addOptions(fieldId, dataq, '0', "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleLocalGovtBodyErrorAtVillageLvl() {
	// Show a popup message
	alert("No data found!");
}

function getLocalBodyList(id) {
	// alert('ID---start----'+id);

	lgdDwrLocalGovtBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleLocalGovtBodySuccess,
		errorHandler : handleLocalGovtBodyError
	});
}

// data contains the returned value
function handleLocalGovtBodySuccess(data) {
	// Assigns data to result id
	// alert("DATA---2-55--"+data);
	var fieldId = 'localGovtBodyListMain';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	// alert("Code--"+valueText+"::nameText--"+nameText);
	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('localGovtBodyListMain');
	st.add(new Option('Select Intermediate Body', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleLocalGovtBodyError() {
	// Show a popup message
	alert("No data found!");
}

// Get SubDistrict Local Body

// For Local Intermediate Govt Body **

function setandGetSubDistList1(id) {
	// alert("ID at intermediate time List time--"+id);
	setLocalBodyIntermediateList(id);
	getLocalSubDistBodyList1(id);
}

function setLocalBodyIntermediateList(id) {
	dwr.util.setValue('localDistGovtBodyListMain', id);
}

// End here

function getLocalSubDistBodyList1(id) {
	// alert('ID---start--Intermedaite-23-11-11--'+id);
	lgdDwrlocalBodyService.getLocalGovtBodySubDistList(id, {
		callback : handleSubDistGovtBodySuccess,
		errorHandler : handleSubDistGovtBodyError
	});
}

// data contains the returned value
function handleSubDistGovtBodySuccess(data) {
	// Assigns data to result id
	// alert("DATA---2---"+data);
	var fieldId = 'localSubDistGovtBodyListMain';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	// alert("Code--"+valueText+"::nameText--"+nameText);
	dwr.util.removeAllOptions(fieldId);
	// var dataq = [ {name:"Select Intermediate Body"} ];
	// dwr.util.addOptions(fieldId, dataq,'0', "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleSubDistGovtBodyError() {
	// Show a popup message
	alert("No data found!");
}
// End here for Subdistrict body

// End here Local Govt Body

// Get Intermendiate Data for Modify Parent in Modify UC-039.1
// *****************************
function setandGetSubDistList2(id) {
	alert("ID at intermediate time List time--" + id);
	setLocalBodyIntermediateList(id);
	getLocalSubDistBodyList2(id);
}

function setLocalBodyIntermediateList(id) {
	dwr.util.setValue('localDistGovtBodyListMain', id);
}

// End here

function getLocalSubDistBodyList2(id) {
	// alert('ID---start--Intermedaite-23-11-11--'+id);
	lgdDwrlocalBodyService.getLocalGovtBodySubDistList(id, {
		callback : handleSubDistGovtBody1Success,
		errorHandler : handleSubDistGovtBody1Error
	});
}

// data contains the returned value
function handleSubDistGovtBody1Success(data) {
	// Assigns data to result id
	// alert("DATA---2---"+data);
	var fieldId = 'localSubDistGovtBodyListMain';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	// alert("Code--"+valueText+"::nameText--"+nameText);
	dwr.util.removeAllOptions(fieldId);
	// var dataq = [ {name:"Select Intermediate Body"} ];
	// dwr.util.addOptions(fieldId, dataq,'0', "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleSubDistGovtBody1Error() {
	// Show a popup message
	alert("No data found!");
}
// End here for Subdistrict body********************************************

// End here

// getLocalGovtBodyVillageList

function setandgetLocalBodyVillageList(id) {
	// alert("ID at village List time--"+id);
	setLocalBodyVillageList(id);
	getLocalBodyVillageList(id);
}

function setLocalBodyVillageList(id) {
	dwr.util.setValue('localGovtBodyListSubDistrict', id);
}

function getLocalBodyVillageList(id) {
	// alert('ID---start----'+id);

	lgdDwrlocalBodyService.getLocalGovtBodyVillageList(id, {
		callback : handleLocalGovtBodyVillageSuccess,
		errorHandler : handleLocalGovtBodyVillageError
	});
}

// data contains the returned value
function handleLocalGovtBodyVillageSuccess(data) {
	// Assigns data to result id
	// alert("DATA---2---"+data);

	var fieldId = 'localGovtBodyVillageList';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	// alert("Code--"+valueText+"::nameText--"+nameText);
	dwr.util.removeAllOptions(fieldId);
	// var dataq = [ {name:"Select Local Body"} ];
	// dwr.util.addOptions(fieldId, dataq,'0', "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleLocalGovtBodyVillageError() {
	// Show a popup message
	alert("No data found!");
}

// End here 17-10-2011

// Get Parent code

function getLocalBodyParent(id) {
	// alert('ID---start----'+id);
	lgdDwrLocalGovtBodyService.getLocalGovtBodyList(id, {
		callback : handleLocalGovtBodyParentSuccess,
		errorHandler : handleLocalGovtBodyParentError
	});
}

// data contains the returned value
function handleLocalGovtBodyParentSuccess(data) {
	// Assigns data to result id
	// alert("DATA---2---"+data);
	var fieldId = 'localGovtBodyListMain';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';
	// alert("Code--"+valueText+"::nameText--"+nameText);
	dwr.util.removeAllOptions(fieldId);
	// var dataq = [ {name:"Select Local Body"} ];
	// dwr.util.addOptions(fieldId, dataq,'0', "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleLocalGovtBodyParentError() {
	// Show a popup message
	alert("No data found!");
}

// End here for parent code

// for Unmapped LocalBody

function setandgetUnmappedLocalBodyList(id) {
	// alert("ID---1--"+id);
	// setLocalBodyUnmappedLocalBodyList(id);
	getUnMappedLocalBody(id);

	// To Forcefully Refresh CheckBox If selected previously
	document.getElementById('chkculb').checked = false;
	getHideLocalBodyParentList(document.getElementById('tierSetupCode').value, document.getElementById('chkculb').checked);
}

function setLocalBodyUnmappedLocalBodyList(id) {
	// alert("ID---2--"+id);
	dwr.util.setValue('localBodyNameEnglishListUnMapped', id);
}

function getUnMappedLocalBody(id) {
	var temp = id.split(":");
	// alert("Temp---2-"+temp[0]);
	var id1 = temp[0];
	var id2 = temp[1];

	var selObj = document.getElementById('ddSourceLocalBody').value;
	var selObjSubDist = document.getElementById('localGovtBodyListMain').value;

	alert('ID---start--3--' + selObj + "::selObjSubDist---" + selObjSubDist);
	var localBodyCode1 = 0;
	if (id2 == 'D') {
		localBodyCode1 = 6; // If id2= D (District) then state code should pass
		// 5-12-11
	}
	if (id2 == 'I') {
		id2 = "";
		id2 = "T";
		localBodyCode1 = selObj;
	}
	if (id2 == 'V') {
		localBodyCode1 = selObjSubDist;
	}

	// alert("localBodyCode---"+localBodyCode1);

	lgdDwrlocalBodyService.getLocalGovtBodyforUnmappedLocalBodyList(id2, localBodyCode1, {
		callback : handleUnMappedLocalBodySuccess,
		errorHandler : handleUnMappedLocalBodyError
	});
}

// data contains the returned value
function handleUnMappedLocalBodySuccess(data) {
	// Assigns data to result id
	// alert("DATA---44---"+data);
	var fieldId = 'localBodyNameEnglishListUnMapped';
	var valueText = 'landRegionCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleUnMappedLocalBodyError() {
	// Show a popup message
	alert("No data found!");
}

// End here unmapped localbody

// //for village
function getVillageList(id) {

	lgdDwrVillageService.getVillageList(id, {
		callback : handleVillageSuccess,
		errorHandler : handleVillageError
	});

}
// data contains the returned value
function handleVillageSuccess(data) {
	// Assigns data to result id

	var fieldId = 'villageListMain';
	var valueText = 'villageCode';
	var nameText = 'villageNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		name : "Select Village"
	} ];
	dwr.util.addOptions(fieldId, dataq, '0', "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleVillageError() {
	// Show a popup message
	alert("No data found!");
}

// Village Details
/*
 * function getModifyVillageValue(id) { alert("id--"+id);
 * lgdDwrVillageService.getVillageDetailsModify(id, { callback :
 * handleModifyVillageSuccess, errorHandler : handleModifyVillageError }); }
 * 
 * function handleModifyVillageSuccess(data) { // Assigns data to result id
 * 
 * var fieldId = 'frmModifyVillage'; dwr.util.setValue(fieldId,data); }
 * 
 * function handleModifyVillageError() { // Show a popup message alert("No data
 * found!"); }
 */

// @Author - Sarvapriya Malhotra
function toggledisplay(obj, val) {
	if (document.getElementById(obj).checked) {

		if (val == 'cvillage') {
			document.getElementById('cvillage').style.visibility = 'visible';
			document.getElementById('csurvey').style.visibility = 'visible';
		}
		if (val == 'culb') {
			document.getElementById('culb').style.visibility = 'visible';
		}
	} else {
		if (val == 'cvillage') {
			document.getElementById('cvillage').style.visibility = 'hidden';
			document.getElementById('csurvey').style.visibility = 'hidden';
		}
		if (val == 'culb') {
			document.getElementById('culb').style.visibility = 'hidden';
		}
	}
}

/**
 * list1 - list from which to add values from as string i.e id name list2 - list
 * to which to add values from as string i.e id name val - value to append at
 * the end Note: doesn't matter what is it if doAddVal=false doAddVal - can be
 * either true or false...If True val is appended at the end if false val is not
 * appeneded
 */
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

function addItemforLBCHCovFULL(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'PART') {
					alert("You can not select DISTURBED Intermediate Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'FULL') {
					alert("You can not select DISTURBED Intermediate Mapped Panchayat");
				} else {

					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + ">" + document.getElementById(list2)[j].text
									+ " (" + val + ")</option>");
					// removeSelectedValueforLB(list2, val);
					addLandregionforChCovInterFull();
				}
			}
		}
		removeSelectedValueforLBDisturbed(list2, val);
	}
}

function addLandregionforChCovInterFull() {
	var selObj = document.getElementById('ddLgdLBInterPDestList');
	var subdistrictCode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		subdistrictCode += selObj.options[i].value + ",";
	}

	lgdDwrlocalBodyService.getLocalGovtBodyforCoveredSubDistrictListInterTo(subdistrictCode, {
		callback : getCoveredLandForSubDistRegionSuccessChCovFin,
		errorHandler : getCoveredLandForSubDistRegionErrorChCovFin
	});

}

function getCoveredLandForSubDistRegionSuccessChCovFin(data) {
	var fieldId = 'ddLgdInterSubDestListhidden';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, getvalueTextInterChCov, getnameTextInterChCov);
	getHeadQuarterSubDistListChCoverage('ddLgdInterSubDestListhidden', 'T');
}

function getvalueTextInterChCov(data) {
	if (data.coverageType == 'F') {
		sd = data.landRegionCode + "_FULL_" + data.landRegionType;
	} else if (data.coverageType == 'P') {
		sd = data.landRegionCode + "_PART_" + data.landRegionType;
	}
	return sd;
}

function getnameTextInterChCov(data) {
	var sd1 = null;
	if (data.coverageType == 'F') {
		sd1 = data.landRegionNameEnglish + "(FULL)";
	} else if (data.coverageType == 'P') {
		sd1 = data.landRegionNameEnglish + "(PART)";
	}
	return sd1;
}

function getCoveredLandForSubDistRegionErrorChCovFin() {
	alert("No data found!");
}

function getHeadQuarterSubDistListChCoverage(list1, lb) {
	var list = document.getElementById(list1);
	var list2 = document.getElementById('availddLgdLBInterCAreaSourceLUmapped');
	var headquarter = "getHeadQuarters" + lb;

	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
	for ( var i = 0; i < list2.length; i++) {
		listFinal[listFinal.length] = list2[i];
	}

	document.getElementById("getHeadQuartersD").innerHTML = '';
	document.getElementById("getHeadQuartersT").innerHTML = '';
	document.getElementById("getHeadQuartersV").innerHTML = '';
	if (listFinal.length >= 1) {
		var name;

		if (lb == 'D')
			name = 'District';
		else if (lb == 'T')
			name = 'Sub District';
		else if (lb == 'V')
			name = 'Village';

		innerHTMLText += "<div><table width='1200' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
				+ name + " List" + "" + "</td>" + "" + "</tr>";

		for ( var j = 0; j < listFinal.length; j++) {
			var name;

			var checkVal = listFinal[j].value;
			var tempcheckVal = checkVal.split("_");
			var finalcheckVal = tempcheckVal[2];
			var chkhead = [];

			if (listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text.replace("(FULL)", "");
			} else if (listFinal[j].text.match("(PART)")) {
				name = listFinal[j].text.replace("(PART)", "");
			}
			if (!listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text;
			}

			if (finalcheckVal == 'true') {
				chkhead[j] = 'yes';
				innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " checked="
						+ chkhead[j] + " value=" + listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb
						+ "'); /></td>" + "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></br></td>"
						+ "</tr>";
			} else {
				chkhead[j] = 'no';
				innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " value="
						+ listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb + "'); /></td>"
						+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></br></td>" + "</tr>";
			}

		}

		innerHTMLText += "</table></div>";
		document.getElementById(headquarter).innerHTML += innerHTMLText;
	}
}

function addItemforLBCHCov(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'PART') {
					alert("You can not select DISTURBED Intermediate Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'FULL') {
					alert("You can not select DISTURBED Intermediate Mapped Panchayat");
				} else {

					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + ">" + document.getElementById(list2)[j].text
									+ " (" + val + ")</option>");
					// removeSelectedValueforLB(list2, val);
				}
			}
		}
		removeSelectedValueforLBDisturbed(list2, val);
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

function getSurverNumbers(list1) {
	if (document.getElementById(list1).value.substr(document.getElementById(list1).value.length - 4) == "FULL") {
		alert("You Can Only Select Survey Numbers Of \"(Part)\" Villages");
	} else if (document.getElementById(list1).value.substr(document.getElementById(list1).value.length - 4) == "PART") {
		// TODO: CALL DWR HERE
	}
}

function removeItem(list1, list2, doRemoveVal) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++)
			if (document.getElementById(list1).options[j].selected == true)
				if (doRemoveVal)
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 4) + " >"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				else
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
		removeSelectedValue(list1);
	}
}

function removeItemUnmapped(list1) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++)
			if (document.getElementById(list1).options[j].selected == true)
				if (document.getElementById(list1)[j].value.substr(document.getElementById(list1)[j].value.length - 1) == 1)
					$('#ddCoveredVillageList').append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 6)
									+ " >"
									+ document.getElementById(list1)[j].innerText.substr(0, document.getElementById(list1)[j].innerText.length - 6)
									+ "</option>");
				else if (document.getElementById(list1)[j].value.substr(document.getElementById(list1)[j].value.length - 1) == 0)
					$('#localBodyNameEnglishListUnMapped').append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 6)
									+ " >"
									+ document.getElementById(list1)[j].innerText.substr(0, document.getElementById(list1)[j].innerText.length - 6)
									+ "</option>");
		removeSelectedValue(list1);
	}
}

function removeAllItemUnmapped(list1) {
	for ( var i = document.getElementById(list1).length - 1; i >= 0; i--) {
		document.getElementById(list1).selectedIndex = i;

		if (document.getElementById(list1)[document.getElementById(list1).selectedIndex].value.substr(document.getElementById(list1)[document
				.getElementById(list1).selectedIndex].value.length - 1) == 1)
			$('#ddCoveredVillageList').append(
					"<option value="
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].value.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].value.length - 6)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText.length - 6) + "</option>");
		else if (document.getElementById(list1)[document.getElementById(list1).selectedIndex].value.substr(document.getElementById(list1)[document
				.getElementById(list1).selectedIndex].value.length - 1) == 0)
			$('#localBodyNameEnglishListUnMapped').append(
					"<option value="
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].value.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].value.length - 6)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText.length - 6) + "</option>");
		removeSelectedValue(list1);
	}
}

function addItemCoveredArea(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++)
			if (document.getElementById(list2).options[j].selected == true)
				if (doAddVal)
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + val + ">" + document.getElementById(list2)[j].innerText
									+ " (" + val + ")</option>");
				else
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].innerText
									+ "</option>");
		removeSelectedValue(list2);
	}
}

function addItemUnampped(list1, val, doAddVal) {
	if (document.getElementById('ddCoveredVillageList').selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById('ddCoveredVillageList').options.length; j++)
			if (document.getElementById('ddCoveredVillageList').options[j].selected == true)
				if (doAddVal)
					$('#' + list1).append(
							"<option value=" + document.getElementById('ddCoveredVillageList')[j].value + val + "_1 >"
									+ document.getElementById('ddCoveredVillageList')[j].innerText + " (" + val + ")</option>");
				else
					$('#' + list1).append(
							"<option value=" + document.getElementById('ddCoveredVillageList')[j].value + "_1 >"
									+ document.getElementById('ddCoveredVillageList')[j].innerText + "</option>");
		removeSelectedValue('ddCoveredVillageList');
	} else if (document.getElementById('localBodyNameEnglishListUnMapped').selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById('localBodyNameEnglishListUnMapped').options.length; j++)
			if (document.getElementById('localBodyNameEnglishListUnMapped').options[j].selected == true)
				if (doAddVal)
					$('#' + list1).append(
							"<option value=" + document.getElementById('localBodyNameEnglishListUnMapped')[j].value + val + "_0 >"
									+ document.getElementById('localBodyNameEnglishListUnMapped')[j].innerText + " (" + val + ")</option>");
				else
					$('#' + list1).append(
							"<option value=" + document.getElementById('localBodyNameEnglishListUnMapped')[j].value + "_0 >"
									+ document.getElementById('localBodyNameEnglishListUnMapped')[j].innerText + "</option>");
		removeSelectedValue('localBodyNameEnglishListUnMapped');
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
// for create village validation by vanisha

// to display help text
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
	if (document.getElementById(obj).checked) {

		if (val == 'correctionvillage') {
			document.getElementById('correctionvillage').style.visibility = 'visible';

		}
		if (val == 'changevillage') {
			document.getElementById('changevillage').style.visibility = 'visible';
		}

	} else {
		if (val == 'changevillage') {
			document.getElementById('correctionvillage').style.visibility = 'hidden';

		}
		if (val == 'correctionvillage') {
			document.getElementById('changevillage').style.visibility = 'hidden';
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
			+ document.getElementById('lbllatitude').innerText
			+ "</td><td width='75'>&nbsp;&nbsp;<input type='text' name='latitude' id='lati"
			+ t
			+ "' class='frmfield'></td><td width='145'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td width='150'>"
			+ document.getElementById('lbllongitude').innerText + "</td><td width='75'>&nbsp;&nbsp;<input type='text' name='longitude' id='longi" + t
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
 * width='100%'>" + document.getElementById('lbllatitude').innerText + "<input
 * type='text' class='frmfield'
 * name='latitude'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
 * document.getElementById('lbllongitude').innerText + "<input type='text'
 * class='frmfield'
 * name='longitude'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
 * type='button' value='Remove'
 * onclick='this.parentNode.parentNode.removeChild(this.parentNode)'></div>"; }
 */

function addsurveys() {
	document.getElementById("addsurveys").innerHTML += "<div width='100%'><input type='text' name='survey_number'><input type='button' value='Remove' onclick='this.parentNode.parentNode.removeChild(this.parentNode)'></div>";
}

// modify village option by vanisha
function toggledisplay2(obj, val) {

	if (document.getElementById(obj).checked) {

		if (val == 'correctionvillage') {
			document.getElementById('correctionvillage').style.visibility = 'visible';
			document.getElementById('changevillage').style.visibility = 'hidden';

		}
		if (val == 'changevillage') {
			document.getElementById('changevillage').style.visibility = 'visible';
			document.getElementById('correctionvillage').style.visibility = 'hidden';
		}

	}
}

// @Author - Sarvapriya Malhotra
function toggleEnable(objChk, obj) {
	if (document.getElementById(objChk).checked)
		document.getElementById(obj).style.visibility = "visible";
	// document.getElementById(obj).disabled=false;
	else
		document.getElementById(obj).style.visibility = "hidden";
	// document.getElementById(obj).disabled='disabled';
}

function load_pageCheck() {

	if (document.getElementById('chkculb').checked) {
		var ddsource1 = document.getElementById('tierSetupCode');
		var ddsource = ddsource1.options[ddsource1.selectedIndex].value;
		// alert("ddsource----1--"+ddsource);
		getHideLocalBodyParentList(document.getElementById('tierSetupCode').value, true);
		hideShowDistIV(ddsource);// it will show different list
	}
	if (document.getElementById('unmappedLocalBody').checked) {
		var ddsource1 = document.getElementById('tierSetupCode');
		var ddsource = ddsource1.options[ddsource1.selectedIndex].value;
		// alert("ddsource----2--"+ddsource);
		getHideUnmappedList(true);

		hideShowDistIV(ddsource);
	}
}

// //for Modify

