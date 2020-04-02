mapKeys = [];
mapValues = [];
var fulLB = 'N';
function loadConvertULBPage() {
	$("#ddIntermediateLb_error").hide();
	$("#ddDistrictPanchayat_error").hide();
	$("#ddDestVillageRLBs_error").hide();
	$("#ddUrbanLocalBodyType_error").hide();
	$("#ddUrbanLocalBodyTypeForUpgrade_error").hide();
	$("#upgradeType_error").hide();
	$("#rdmergeRLBtoULB_error").hide();
	$("#ddUrbanLocalBody_error").hide();
	$("#txtlocalBodyNameInEn_error").hide();
	$("#ddUrbanLocalBodyTypeNew_error").hide();

	$("#ddIntermediateLb1_error").hide();
	$("#ddDistrictPanchayat2_error").hide();
	$("#ddDestVillageRLBs1_error").hide();
	$("#ddDestVillageRLBs2_error").hide();
	$("#ddDestVillageRLBs3_error").hide();

	$("#ddDestVillageRLBs_PART_error").hide();
	$("#ddDestVillageRLBs1_PART_error").hide();
	$("#ddDestVillageRLBs2_PART_error").hide();
	$("#ddDestVillageRLBs3_PART_error").hide();

	$("#ddDestCoveredAreaRLB_error").hide();
	$("#ddSourceCoveredAreaRLB_error").hide();

	setValues();
}

function setValues() { // alert('setvalue start..');
	var mergeUlbClick = document.getElementById('mergeUlbClick').value;
	var declareUlbClick = document.getElementById('declareUlbClick').value;

	var hdnDistrictCode = document.getElementById('hdnDistrictCode').value;
	var hdnInterLBCode = document.getElementById('hdnInterLBCode').value;

	// alert('mergeUlbClick-->'+mergeUlbClick+",
	// declareUlbClick-->"+declareUlbClick);
	if (mergeUlbClick == "true") {
		document.getElementById('divmergeRLB').style.display = 'block';
		document.getElementById('divdeclareNewULB').style.display = 'none';
		document.getElementById('rdmergeRLBtoULB').checked = true;
		document.getElementById('rddeclarenewULB').checked = false;
		document.getElementById('divSaveButtons').style.display = 'block';
		document.getElementById('divPreviousButtons').style.display = 'none';
	} else if (declareUlbClick == "true") { // alert('else..');
		document.getElementById('divmergeRLB').style.display = 'none';
		document.getElementById('divdeclareNewULB').style.display = 'block';
		document.getElementById('rddeclarenewULB').checked = true;
		document.getElementById('rdmergeRLBtoULB').checked = false;
		document.getElementById('divSaveButtons').style.display = 'block';
		document.getElementById('divPreviousButtons').style.display = 'none';
	}

	if (hdnDistrictCode > 0) {
		// alert('inside hdndistrictcde-->'+hdnDistrictCode);
		dwr.util.setValue('ddDistrictPanchayat', parseInt(hdnDistrictCode));
		// dwr.util.setValue('ddDistrictPanchayat2', parseInt(hdnDistrictCode));
		setTimeout("assignedValues('hdnDistrictCode','ddDistrictPanchayat')", 10);
		// setTimeout("assignedValues('hdnDistrictCode','ddDistrictPanchayat2')",
		// 10);
	}
	if (hdnInterLBCode > 0) { // alert('inside
		// hdnInterLBCode-->'+hdnInterLBCode);
		dwr.util.setValue('ddIntermediateLb', parseInt(hdnInterLBCode));
		setTimeout("assignedValues('hdnInterLBCode','ddIntermediateLb')", 1000);
		// dwr.util.setValue('ddIntermediateLb1', parseInt(hdnInterLBCode));
		// setTimeout("assignedValues('hdnInterLBCode','ddIntermediateLb1')",
		// 1000);
	}
}

function assignedValues(obj1, obj2) {
	document.getElementById(obj2).value = document.getElementById(obj1).value;
}

function hidePanchayatLevels(category) {
	if (category == 'DIV') {
		document.getElementById('divDIV').style.display = 'block';
		// document.getElementById('divDV').style.display = 'none';
		// document.getElementById('divIV').style.display = 'none';
		// document.getElementById('divV').style.display = 'none';
	} else if (category == 'DV') {
		// document.getElementById('divDIV').style.display = 'none';
		// document.getElementById('divDV').style.display = 'block';
		// document.getElementById('divIV').style.display = 'none';
		// document.getElementById('divV').style.display = 'none';
	} else if (category == 'IV') {
		// document.getElementById('divDIV').style.display = 'none';
		// document.getElementById('divDV').style.display = 'none';
		// document.getElementById('divIV').style.display = 'block';
		// document.getElementById('divV').style.display = 'none';
	} else if (category == 'V' || category == 'VV') {
		// document.getElementById('divDIV').style.display = 'none';
		// document.getElementById('divDV').style.display = 'none';
		// document.getElementById('divIV').style.display = 'none';
		// document.getElementById('divV').style.display = 'block';
	} else {
		document.getElementById('divDIV').style.display = 'block';
		// document.getElementById('divDV').style.display = 'none';
		// document.getElementById('divIV').style.display = 'none';
		// document.getElementById('divV').style.display = 'none';
	}

}

function hasError() {
	hidePanchayatLevels("DIV");
	loadConvertULBPage();
	// document.getElementById('divDIV').style.display = 'none';
	document.getElementById('divFirstPanel').style.display = 'none';
	document.getElementById('divSecondPanel').style.display = 'block';
	document.getElementById('divdeclareNewULB').style.display = 'block';
}

function clickOnProceed() {

	document.getElementById('divFirstPanel').style.display = 'none';
	document.getElementById('divSecondPanel').style.display = 'block';
	document.getElementById('divmergeRLB').style.display = 'none';
	document.getElementById('rdmergeRLBtoULB').checked = false;
	document.getElementById('rddeclarenewULB').checked = false;
	document.getElementById('divSaveButtons').style.display = 'none';
	document.getElementById('divPreviousButtons').style.display = 'block';
	document.getElementById('divdeclareNewULB').style.display = 'none';

	if (document.getElementById('rddeclarenewULB').checked == true) {
		document.getElementById('divmergeRLB').style.display = 'none';
		document.getElementById('divdeclareNewULB').style.display = 'block';

	} else if (document.getElementById('rdmergeRLBtoULB').checked == true) {
		document.getElementById('divmergeRLB').style.display = 'block';
		document.getElementById('divdeclareNewULB').style.display = 'none';

	}

	setValues();

}

function radioClick(id) {
	if (id == 'M') {
		if($("#rdmergeRLBtoULB").prop("checked"))
		   {
			  $("#rddeclarenewULB").prop("checked",false);
			  
		   }
		
		document.getElementById('divmergeRLB').style.display = 'block';
		document.getElementById('divdeclareNewULB').style.display = 'none';
		document.getElementById('divSaveButtons').style.display = 'block';
		document.getElementById('divPreviousButtons').style.display = 'none';
	
		
		
		
		
		

	} else if (id == 'N') {
		if($("#rddeclarenewULB").prop("checked"))
		   {
			  $("#rdmergeRLBtoULB").prop("checked",false);
			  
		   }
		document.getElementById('divmergeRLB').style.display = 'none';
		document.getElementById('divdeclareNewULB').style.display = 'block';
		document.getElementById('divPreviousButtons').style.display = 'none';
		document.getElementById('divSaveButtons').style.display = 'block';
		/*document.getElementById('rdmergeRLBtoULB').checked = false;*/
		
		
	

	}

}

function onPreviousClick() {
	document.getElementById('divFirstPanel').style.display = 'block';
	document.getElementById('divSecondPanel').style.display = 'none';
}

function getIntermediatePanchayatbyDcode(id) {
	dwr.util.removeAllOptions("ddDestVillageRLBs");
	dwr.util.removeAllOptions("ddSourceVillageRLBS");
	dwr.util.removeAllOptions("ddSourceCoveredAreaRLB");
	dwr.util.removeAllOptions("ddDestCoveredAreaRLB");
	if (id != 0) {
		lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
			callback : handleIntermediateLocalBodiesSuccess,
			errorHandler : handleIntermediateLocalBodiesError
		});
	}
}

function handleIntermediateLocalBodiesSuccess(data) {
	var fieldId = null;
	var st = null;
	var options1 = null;
	if (level.match('DI')) {
		fieldId = "ddIntermediateLb";
		options1 = $("#ddIntermediateLb");
		st = document.getElementById('ddIntermediateLb');
	} else if (level.match('D')) {
		fieldId = "ddSourceVillageRLBS";
		options1 = $("#ddSourceVillageRLBS");
	}
	dwr.util.removeAllOptions(fieldId);
	if (st) {
		st.add(new Option('Select Intermediate Panchayat', '0'));
	}
	dwr.util.removeAllOptions('ddSourceVillageRLBS');
	$.each(data, function(key, obj) {
		var option1 = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option1).attr('disabled', 'disabled');
			$(option1).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options1.append(option1);
		} else {
			$(option1).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options1.append(option1);
		}
	});

}

function handleIntermediateLocalBodiesError() {
	// Show a popup message
	alert("No data found!");
}
function getVillageLocalBodies1(id) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleVillageLocalBodiesSuccess1,
		errorHandler : handleVillageLocalBodiesError1
	});
}

function handleVillageLocalBodiesSuccess1(data) {
	// Assigns data to result id

	var fieldId = 'ddSourceVillageRLBS1';
	dwr.util.removeAllOptions(fieldId);

	var options = $("#ddSourceVillageRLBS1");
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

function handleVillageLocalBodiesError1() {
	// Show a popup message
	alert("No data found!");
}

function getVillageLocalBodies2(id) {
	if (id != 0) {
		lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
			callback : handleVillageLocalBodiesSuccess2,
			errorHandler : handleVillageLocalBodiesError2
		});
	}
}

function handleVillageLocalBodiesSuccess2(data) {
	// Assigns data to result id

	var fieldId = 'ddSourceVillageRLBS2';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleVillageLocalBodiesError2() {
	// Show a popup message
	alert("No data found!");
}

function getVillageLocalBodies(id) {
	if (id != 0) {
		dwr.util.removeAllOptions("ddDestVillageRLBs");
		dwr.util.removeAllOptions("ddSourceVillageRLBS");
		dwr.util.removeAllOptions("ddSourceCoveredAreaRLB");
		dwr.util.removeAllOptions("ddDestCoveredAreaRLB");
		lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
			callback : handleVillageLocalBodiesSuccess,
			errorHandler : handleVillageLocalBodiesError
		});
	}
}

function handleVillageLocalBodiesSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddSourceVillageRLBS';
	dwr.util.removeAllOptions(fieldId);
	var options = $("#ddSourceVillageRLBS");
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

function handleVillageLocalBodiesError() {
	// Show a popup message
	alert("No data found!");
}

function selectVillageLocalBody() {
	var selObj = document.getElementById('ddDestVillageRLBs');
	var vilageLbCode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		vilageLbCode += selObj.options[i].value + ",";

	}
	getLocalGovtBodyCoveredVillageList(vilageLbCode);
}
function selectVillageLocalBodyforname() {
	var selObj = document.getElementById('ddDestVillageRLBs');
	var vilageLbCode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		vilageLbCode += selObj.options[i].value + ",";

	}
	getLocalGovtBodyCoveredVillageListforname(vilageLbCode);
}

/* Modified by sushil on 01-02-2013 */
function selectVillageLocalBodyforrlbtoulb() {
	// alert("inside method of selectVillageLocalBodyforrlbtoulb res for
	// mapValues..");
	dwr.util.removeAllOptions("ddSourceCoveredAreaRLB");
	mapValues = [];
	var selObj = document.getElementById('ddDestVillageRLBs');
	var vilageLbCode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		vilageLbCode += selObj.options[i].value + ",";
	}
	getLocalGovtBodyCoveredVillageListforrlbtoulb(vilageLbCode);
}

function getLocalGovtBodyCoveredVillageListforrlbtoulb(id) {
	// alert("ID---getCovered--"+id);
	if (id == '') {
		alert("Please Select PART of Village Panchayats to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART of Village Panchayats to get Covered areas.");
	} else {
		mapKeys.push(id);
		lgdDwrlocalBodyService.getLocalGovtBodyCoveredVillageListforrlbtoulb(id, {
			callback : handleCoveredAreaVillagesSuccessforrlbtoulb,
			errorHandler : handleCoveredAreaVillagesErrorforrlbtoulb
		});
	}

}

function handleCoveredAreaVillagesSuccessforrlbtoulb(data) {
	// Assigns data to result idasad
	if (data == "") {
		alert("No covered Areas available for these Village Panchayats");
	} else { // alert("data-->"+data);
		var fieldId = 'ddSourceCoveredAreaRLB';
		dwr.util.removeAllOptions(fieldId);
		var options = $("#ddSourceCoveredAreaRLB");
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'disabled');
				$(option).val(obj.landRegionCodeStr).text(obj.landRegionNameEnglish);
				options.append(option);
			} else {
				$(option).val(obj.landRegionCodeStr).text(obj.landRegionNameEnglish);
				options.append(option);
			}
		});

		createHashMap(data);
	}
}

/* Added by sushil on 01-02-2013 */
function createHashMap(data) {
	var map = {};
	for ( var i = 0; i < data.length; i++) {
		var obj = data[i];
		map["key" + i] = obj.landRegionCode;
	}

	for ( var key in map) {
		if (map.hasOwnProperty(key)) {
			mapValues.push(map[key]);
		}
	}
}

function selectVillageLocalBody1() {
	var selObj = document.getElementById('ddDestVillageRLBs1');
	var vilageLbCode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		vilageLbCode += selObj.options[i].value + ",";

	}
	getLocalGovtBodyCoveredVillageList(vilageLbCode);
}
function selectVillageLocalBody2() {
	var selObj = document.getElementById('ddDestVillageRLBs2');
	var vilageLbCode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		vilageLbCode += selObj.options[i].value + ",";

	}
	getLocalGovtBodyCoveredVillageList(vilageLbCode);
}

function selectVillageLocalBody3() {
	var selObj = document.getElementById('ddDestVillageRLBs3');
	var vilageLbCode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		vilageLbCode += selObj.options[i].value + ",";

	}
	getLocalGovtBodyCoveredVillageList(vilageLbCode);
}

function getLocalGovtBodyCoveredVillageList(id) {
	// alert("ID---getCovered--"+id);
	if (id == '') {
		alert("Please Select PART of Village Panchayats to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART of Village Panchayats to get Covered areas.");
	} else {
		lgdDwrlocalBodyService.getLocalGovtBodyCoveredVillageList(id, {
			callback : handleCoveredAreaVillagesSuccess,
			errorHandler : handleCoveredAreaVillagesError
		});
	}

}
function getLocalGovtBodyCoveredVillageListforname(id) {
	// alert("ID---getCovered--"+id);
	if (id == '') {
		alert("Please Select PART of Village Panchayats to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART of Village Panchayats to get Covered areas.");
	} else {
		lgdDwrlocalBodyService.getLocalGovtBodyCoveredVillageListforname(id, {
			callback : handleCoveredAreaVillagesSuccess,
			errorHandler : handleCoveredAreaVillagesError
		});
	}

}

// data contains the returned value
function handleCoveredAreaVillagesSuccess(data) {

	// Assigns data to result idasad
	if (data == "") {
		alert("No covered Areas available for these Village Panchayats");

	} else {
		var fieldId = 'ddSourceCoveredAreaRLB';
		var valueText = 'landRegionCode';
		var nameText = 'landRegionNameEnglish';

		dwr.util.removeAllOptions(fieldId);

		dwr.util.addOptions(fieldId, data, valueText, nameText);
	}
}

function handleCoveredAreaVillagesError() {
	// Show a popup message
	alert("No data found!");
}

function handleCoveredAreaVillagesErrorforrlbtoulb() {
	// Show a popup message
	alert("No data found!");
}
function handleCoveredAreaVillagesSuccess(data) {

	// Assigns data to result idasad
	if (data == "") {
		alert("No covered Areas available for these Village Panchayats");

	} else {
		var fieldId = 'ddSourceCoveredAreaRLB';
		var valueText = 'landRegionCode';
		var nameText = 'landRegionNameEnglish';

		dwr.util.removeAllOptions(fieldId);

		dwr.util.addOptions(fieldId, data, valueText, nameText);
	}
}

function handleCoveredAreaVillagesError() {
	// Show a popup message
	alert("No data found!");
}

function getUrbanLocalBodies(id) {
	var stateCode = dwr.util.getValue('hdnState');
	lgdDwrlocalBodyService.getUrbanLocalBodyList(stateCode, id, {
		callback : handleUrbanLocalBodySuccess,
		errorHandler : handleUrbanLocalBodyError
	});
}

function handleUrbanLocalBodySuccess(data) {

	var fieldId = 'ddUrbanLocalBody';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById(fieldId);
	st.add(new Option('Select Urban Local Body', '0'));

	var options = $("#ddUrbanLocalBody");
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

function handleUrbanLocalBodyError() {
	// Show a popup message
	alert("No data found!");
}

function selectVillageRLBs() {
	var selSourceVillageRLBS = document.getElementById('ddSourceVillageRLBS');
	var selDestVillageRLBS = document.getElementById('ddDestVillageRLBs');
	// var
	// selSourceVillageRLBS1=document.getElementById('ddSourceVillageRLBS1');
	// var selDestVillageRLBS1=document.getElementById('ddDestVillageRLBs1');
	// var
	// selSourceVillageRLBS2=document.getElementById('ddSourceVillageRLBS2');
	// var selDestVillageRLBS2=document.getElementById('ddDestVillageRLBs2');

	var selSourceCoveredAreaRLB = document.getElementById('ddSourceCoveredAreaRLB');
	var selDestCoveredAreaRLB = document.getElementById('ddDestCoveredAreaRLB');

	// var
	// selSourceVillageRLBS3=document.getElementById('ddSourceVillageRLBS3');
	// var selDestVillageRLBS3=document.getElementById('ddDestVillageRLBs3');

	for ( var i = 0; i < selSourceVillageRLBS.options.length; i++) {
		selSourceVillageRLBS.options[i].selected = false;
	}
	for ( var i = 0; i < selDestVillageRLBS.options.length; i++) {
		selDestVillageRLBS.options[i].selected = true;
	}

	// for (var j = 0; j < selSourceVillageRLBS1.options.length; j++) {
	// selSourceVillageRLBS1.options[j].selected=false;
	// }
	// for (var j = 0; j < selDestVillageRLBS1.options.length; j++) {
	// selDestVillageRLBS1.options[j].selected=true;
	// }
	//	
	// for (var k = 0;k < selSourceVillageRLBS2.options.length; k++) {
	// selSourceVillageRLBS2.options[k].selected=false;
	// }
	// for (var k = 0; k < selDestVillageRLBS2.options.length; k++) {
	// selDestVillageRLBS2.options[k].selected=true;
	// }
	//	
	// for (var l = 0; l < selSourceVillageRLBS3.options.length; l++) {
	// selSourceVillageRLBS3.options[l].selected=false;
	// }
	// for (var l = 0; l < selDestVillageRLBS3.options.length; l++) {
	// selDestVillageRLBS3.options[l].selected=true;
	// }

	for ( var m = 0; m < selSourceCoveredAreaRLB.options.length; m++) {
		selSourceCoveredAreaRLB.options[m].selected = false;
	}
	for ( var m = 0; m < selDestCoveredAreaRLB.options.length; m++) {
		selDestCoveredAreaRLB.options[m].selected = true;
	}
}

function validateFirstAll(ctx) {
	
	if(isSingleTier){
		clickOnProceed();
		return true;
	}else{
		var stateCode = document.getElementById("stateid").value;
		if (stateCode == 34 && fulLB == 'Y') {
			clickOnProceed();
			return true;
		}
		selectVillageRLBs();
		var errors = new Array();
		var error = false;
		var obj = document.getElementById('divDIV');
		if (obj.style.display == 'block' || obj.style.display == '') {
			errors[0] = !validatedistrictLB();
		}
		if (obj.style.display == 'block' || obj.style.display == '') {
			errors[1] = !validateintermediateLB();
			errors[2] = !validatevillageLB();
			errors[3] = !validatevillageLBforFULLandPART();
			errors[4] = !validatevillageDestCoveredAreas();
		}

		for ( var i = 0; i < 5; i++) {
			if (errors[i] == true) {
				error = true;
				break;
			}
		}

		if (error == true) {
			showClientSideError();
			return false;
		} else {
			var vals = document.getElementById('ddDestVillageRLBs');
			if (vals != null) {
				var valArray = new Array();
				for ( var i = 0; i < vals.length; i++) {
					val = (vals[i].value).replace("_FULL", "");
					valArray[i] = parseInt(val);
				}
				lgdDwrlocalBodyService.isVillageExist(valArray, ctx, {
					callback : serverCallBack
				});
			}
		}
	}
	
	return false;
}

function serverCallBack(data) {
	if (data != null) {
		alert(data);
		return false;
	} else {
		clickOnProceed();
		return true;
	}
}

function validatedistrictLB() {
	if (document.getElementById('divDIV').style.display == 'block' || document.getElementById('divDIV').style.display == '') {
		var ddDistrictPanchayat = document.getElementById('ddDistrictPanchayat');
		if (ddDistrictPanchayat) {
			if (ddDistrictPanchayat.selectedIndex == 0) {
				$("#ddDistrictPanchayat_error").show();
				return false;
			}
			$("#ddDistrictPanchayat_error").hide();
		}
		return true;
	}
}

function validateintermediateLB() {
	if (document.getElementById('divDIV').style.display == 'block' || document.getElementById('divDIV').style.display == '') {
		var ddIntermediateLb = document.getElementById('ddIntermediateLb');
		if (ddIntermediateLb) {
			if (ddIntermediateLb.selectedIndex == -1) {
				$("#ddIntermediateLb_error").show();
				return false;
			} else if (ddIntermediateLb.selectedIndex == 0) {
				$("#ddIntermediateLb_error").show();
				return false;
			}
			$("#ddIntermediateLb_error").hide();
		}
		return true;
	}
}

function validatevillageLB() {
	if (document.getElementById('divDIV').style.display == 'block' || document.getElementById('divDIV').style.display == '') {
		var ddDestVillageRLBs = document.getElementById('ddDestVillageRLBs');

		if (ddDestVillageRLBs.options.length <= 0) {
			$("#ddDestVillageRLBs_error").show();
			return false;
		}
		$("#ddDestVillageRLBs_error").hide();
		return true;
	}
	// else if (document.getElementById('divIV').style.display =='block' )
	// {
	// var ddDestVillageRLBs1= document.getElementById('ddDestVillageRLBs1');
	//		
	// if(ddDestVillageRLBs1.options.length<=0)
	// {
	// $("#ddDestVillageRLBs1_error").show();
	// return false;
	// }
	// $("#ddDestVillageRLBs1_error").hide();
	// return true;
	// }
	// else if (document.getElementById('divDV').style.display =='block' )
	// {
	// var ddDestVillageRLBs2= document.getElementById('ddDestVillageRLBs2');
	//		
	// if(ddDestVillageRLBs2.options.length<=0)
	// {
	// $("#ddDestVillageRLBs2_error").show();
	// return false;
	// }
	// $("#ddDestVillageRLBs2_error").hide();
	// return true;
	// }
	// else if (document.getElementById('divV').style.display =='block' )
	// {
	// var ddDestVillageRLBs3= document.getElementById('ddDestVillageRLBs3');
	//		
	// if(ddDestVillageRLBs3.options.length<=0)
	// {
	// $("#ddDestVillageRLBs3_error").show();
	// return false;
	// }
	// $("#ddDestVillageRLBs3_error").hide();
	// return true;
	// }

}

function checkforFULLandPART(ddId) {
	var countFULL = 0;
	var countPART = 0;
	for ( var m = 0; m < ddId.options.length; m++) {

		if (ddId.options[m].value.match('PART')) {
			countPART = countPART + 1;
		} else if (ddId.options[m].value.match('FULL')) {
			countFULL = countFULL + 1;
		}
	}
	if ((countPART >= 1 && countFULL >= 0) || countFULL >= 1) {
		return true;
	}

	else {
		return false;
	}
	return false;
}

function validatevillageLBforFULLandPART() {

	if (document.getElementById('divDIV').style.display == 'block' || document.getElementById('divDIV').style.display == '') {
		var ddDestVillageRLBs = document.getElementById('ddDestVillageRLBs');

		if (!checkforFULLandPART(ddDestVillageRLBs)) {
			$("#ddDestVillageRLBs_PART_error").show();
			return false;
		}
		$("#ddDestVillageRLBs_PART_error").hide();
		return true;
	}
	// else if (document.getElementById('divIV').style.display =='block' )
	// {
	// var ddDestVillageRLBs1= document.getElementById('ddDestVillageRLBs1');
	//		
	// if(!checkforFULLandPART(ddDestVillageRLBs1))
	// {
	// $("#ddDestVillageRLBs1_PART_error").show();
	// return false;
	// }
	// $("#ddDestVillageRLBs1_PART_error").hide();
	// return true;
	// }
	// else if (document.getElementById('divDV').style.display =='block' )
	// {
	// var ddDestVillageRLBs2= document.getElementById('ddDestVillageRLBs2');
	//		
	// if(!checkforFULLandPART(ddDestVillageRLBs2))
	// {
	// $("#ddDestVillageRLBs2_PART_error").show();
	// return false;
	// }
	// $("#ddDestVillageRLBs2_PART_error").hide();
	// return true;
	// }
	// else if (document.getElementById('divV').style.display =='block' )
	// {
	// var ddDestVillageRLBs3= document.getElementById('ddDestVillageRLBs3');
	//		
	// if(!checkforFULLandPART(ddDestVillageRLBs3))
	// {
	// $("#ddDestVillageRLBs3_PART_error").show();
	// return false;
	// }
	// $("#ddDestVillageRLBs3_PART_error").hide();
	// return true;
	// }

}

function validatevillageSourceCoveredAreas() {
	if (document.getElementById('divDIV').style.display == 'block' || document.getElementById('divDIV').style.display == '') {
		var ddDestVillageRLBs = document.getElementById('ddDestVillageRLBs');

		if (checkforPart(ddDestVillageRLBs)) {
			var ddSourceCoveredAreaRLB = document.getElementById('ddSourceCoveredAreaRLB');

			if (ddSourceCoveredAreaRLB.options.length <= 0) {
				$("#ddSourceCoveredAreaRLB_error").show();
				return false;
			}
			$("#ddSourceCoveredAreaRLB_error").hide();
			return true;
		} else {
			$("#ddSourceCoveredAreaRLB_error").hide();
			return true;
		}
	}
	// else if (document.getElementById('divIV').style.display =='block' )
	// {
	// var ddDestVillageRLBs1= document.getElementById('ddDestVillageRLBs1');
	//		
	// if(checkforPart(ddDestVillageRLBs1))
	// {
	// var ddSourceCoveredAreaRLB=
	// document.getElementById('ddSourceCoveredAreaRLB');
	//			
	// if(ddSourceCoveredAreaRLB.options.length<=0)
	// {
	// $("#ddSourceCoveredAreaRLB_error").show();
	// return false;
	// }
	// $("#ddSourceCoveredAreaRLB_error").hide();
	// return true;
	// }
	// else
	// {
	// $("#ddSourceCoveredAreaRLB_error").hide();
	// return true;
	// }
	// }
	// else if (document.getElementById('divDV').style.display =='block' )
	// {
	// var ddDestVillageRLBs2= document.getElementById('ddDestVillageRLBs2');
	// if(checkforPart(ddDestVillageRLBs2))
	// {
	// var ddSourceCoveredAreaRLB=
	// document.getElementById('ddSourceCoveredAreaRLB');
	//			
	// if(ddSourceCoveredAreaRLB.options.length<=0)
	// {
	// $("#ddSourceCoveredAreaRLB_error").show();
	// return false;
	// }
	// $("#ddSourceCoveredAreaRLB_error").hide();
	// return true;
	// }
	// else
	// {
	// $("#ddSourceCoveredAreaRLB_error").hide();
	// return true;
	// }
	// }
	// else if (document.getElementById('divV').style.display =='block' )
	// {
	// var ddDestVillageRLBs3= document.getElementById('ddDestVillageRLBs3');
	// if(checkforPart(ddDestVillageRLBs3))
	// {
	// var ddSourceCoveredAreaRLB=
	// document.getElementById('ddSourceCoveredAreaRLB');
	//			
	// if(ddSourceCoveredAreaRLB.options.length<=0)
	// {
	// $("#ddSourceCoveredAreaRLB_error").show();
	// return false;
	// }
	// $("#ddSourceCoveredAreaRLB_error").hide();
	// return true;
	// }
	// else
	// {
	// $("#ddSourceCoveredAreaRLB_error").hide();
	// return true;
	// }
	// }
	// else
	// {
	// $("#ddSourceCoveredAreaRLB_error").hide();
	// return true;
	// }

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

function validatevillageDestCoveredAreas() {
	if (document.getElementById('divDIV').style.display == 'block' || document.getElementById('divDIV').style.display == '') {
		var ddDestVillageRLBs = document.getElementById('ddDestVillageRLBs');

		if (checkforPart(ddDestVillageRLBs)) {
			var ddDestCoveredAreaRLB = document.getElementById('ddDestCoveredAreaRLB');

			if (ddDestCoveredAreaRLB.options.length == 0) {
				$("#ddDestCoveredAreaRLB_error").show();
				return false;
			}
			$("#ddDestCoveredAreaRLB_error").hide();

			return true;
		} else {
			$("#ddDestCoveredAreaRLB_error").hide();
			return true;
		}
	}
	// else if (document.getElementById('divIV').style.display =='block' )
	// {
	// var ddDestVillageRLBs1= document.getElementById('ddDestVillageRLBs1');
	//		
	// if(checkforPart(ddDestVillageRLBs1))
	// {
	// var ddDestCoveredAreaRLB=
	// document.getElementById('ddDestCoveredAreaRLB');
	//			
	// if(ddDestCoveredAreaRLB.options.length==0)
	// {
	// $("#ddDestCoveredAreaRLB_error").show();
	// return false;
	// }
	// $("#ddDestCoveredAreaRLB_error").hide();
	//			
	// return true;
	// }
	// else
	// {
	// $("#ddDestCoveredAreaRLB_error").hide();
	// return true;
	// }
	// }
	// else if (document.getElementById('divDV').style.display =='block' )
	// {
	// var ddDestVillageRLBs2= document.getElementById('ddDestVillageRLBs2');
	// if(checkforPart(ddDestVillageRLBs2))
	// {
	// var ddDestCoveredAreaRLB=
	// document.getElementById('ddDestCoveredAreaRLB');
	//			
	// if(ddDestCoveredAreaRLB.options.length==0)
	// {
	// $("#ddDestCoveredAreaRLB_error").show();
	// return false;
	// }
	// $("#ddDestCoveredAreaRLB_error").hide();
	//			
	// return true;
	// }
	// else
	// {
	// $("#ddDestCoveredAreaRLB_error").hide();
	// return true;
	// }
	// }
	// else if (document.getElementById('divV').style.display =='block' )
	// {
	// var ddDestVillageRLBs3= document.getElementById('ddDestVillageRLBs3');
	// if(checkforPart(ddDestVillageRLBs3))
	// {
	// var ddDestCoveredAreaRLB=
	// document.getElementById('ddDestCoveredAreaRLB');
	//			
	// if(ddDestCoveredAreaRLB.options.length==0)
	// {
	// $("#ddDestCoveredAreaRLB_error").show();
	// return false;
	// }
	// $("#ddDestCoveredAreaRLB_error").hide();
	//			
	// return true;
	// }
	// else
	// {
	// $("#ddDestCoveredAreaRLB_error").hide();
	// return true;
	// }
	// }

}

function validateSecondULBAll() {
	
	var rdmergeRLBtoULB = document.getElementById('rdmergeRLBtoULB');
	var rddeclarenewULB = document.getElementById('rddeclarenewULB');

	var errors = new Array();
	var error = false;

	selectVillageRLBs();
	
	errors[0] = !validateRadio();

	if (rdmergeRLBtoULB.checked) {
		errors[1] = !validateLBTypeCode();
		errors[2] = !validateLBCode();
	} else if (rddeclarenewULB.checked) {
		errors[3] = !validateLBName();
		errors[4] = !validateLBCodeNew();
	}

	for ( var i = 0; i < 5; i++) {
		if (errors[i] == true) {
			error = true;
			break;
		}
	}
	if (error == true) {
		showClientSideError();
		return false;
	} else {
		// jQuery('input[type=submit]').attr('disabled', 'disabled');
		return true;
	}
	return false;
}

function validateRadio() {
	
	var rdmergeRLBtoULB = document.getElementById('rdmergeRLBtoULB');
	var rddeclarenewULB = document.getElementById('rddeclarenewULB');
	if (!rdmergeRLBtoULB.checked && !rddeclarenewULB.checked) {
		$("#rdmergeRLBtoULB_error").show();
		return false;
	}
	$("#rdmergeRLBtoULB_error").hide();
	return true;
}

function validateLBTypeCode() {
	var ddUrbanLocalBodyType = document.getElementById('ddUrbanLocalBodyType');
	var rbtn1 = document.convertForm.upgradeType[0];
	var rbtn2 = document.convertForm.upgradeType[1];
	if (rbtn1.checked == true && rbtn1.value == 'Y') {
		var obj = document.getElementById('ddUrbanLocalBodyTypeForUpgrade');
		if (obj.selectedIndex == 0) {
			$("#ddUrbanLocalBodyTypeForUpgrade_error").show();
			return false;
		}
		$("#ddUrbanLocalBodyTypeForUpgrade_error").hide();
		$("#upgradeType_error").hide();
		return true;
	}
	if (rbtn2.checked == true && rbtn2.value == 'N') {
		if (ddUrbanLocalBodyType.disabled != true) {
			if (ddUrbanLocalBodyType.selectedIndex == 0) {
				$("#ddUrbanLocalBodyType_error").show();
				return false;
			}
			$("#ddUrbanLocalBodyType_error").hide();
			$("#upgradeType_error").hide();
			return true;
		} else {
			$("#ddUrbanLocalBodyType_error").hide();
			return true;
		}
	}
	if (rbtn1.checked != true && rbtn2.checked != true) {
		$("#upgradeType_error").show();
		return false;
	}
}

function validateLBCode() {
	var ddUrbanLocalBody = document.getElementById('ddUrbanLocalBody');
	if (ddUrbanLocalBody.selectedIndex == -1) {
		$("#ddUrbanLocalBody_error").show();
		return false;
	} else if (ddUrbanLocalBody.selectedIndex == 0) {
		$("#ddUrbanLocalBody_error").show();
		return false;
	}
	$("#ddUrbanLocalBody_error").hide();
	return true;
}

function validateLBName() {
	if (document.getElementById('txtlocalBodyNameInEn').value == "") {

		$("#txtlocalBodyNameInEn_error").show();
		return false;
	}
	$("#txtlocalBodyNameInEn_error").hide();
	return true;

}

function validateLBCodeNew() {
	var ddUrbanLocalBodyType = document.getElementById('ddUrbanLocalBodyTypeNew');

	if (ddUrbanLocalBodyType.selectedIndex == 0) {
		$("#ddUrbanLocalBodyTypeNew_error").show();
		return false;
	}
	$("#ddUrbanLocalBodyTypeNew_error").hide();
	return true;
}

/* Added by Sushil on 01-02-2013 */
function checkValidPart(obj) {
	// alert("mkeys-->"+mapKeys+", mvalues-->"+mapValues);
	var selectBox = document.getElementById(obj);
	if (selectBox.type == "select-multiple") {
		var selectedValues = $("#" + selectBox.id).val() || [];
		// alert('selected val-->'+selectedValues);
		var mLength = mapValues.length;
		var sLength = selectedValues.length;
		if (sLength == mLength) {
			alert("You can not select all villages as a part");
			return false;
		} else {
			return true;
		}
	}
}
function ConvertULBnameVal(ulbtype)

{

	var slc = document.getElementById('hdnState').value;
	var ulbname = document.getElementById('txtlocalBodyNameInEn').value;

	if (ulbname.length != 0) {
		lgdDwrlocalBodyService.ULBExistforConvert(slc, ulbtype, ulbname, {
			callback : handleUniqueULBSuccess,
			errorHandler : handleUniqueULBError
		});
	} else {
		document.getElementById("UniqueULBrror").innerHTML = "<font color='red'><br>Please Enter ULB Name </font>";
		$('#ddUrbanLocalBodyTypeNew').prop('selectedIndex', 0);
	}
}

function handleUniqueULBSuccess(data) {
	document.getElementById("UniqueULBrror").innerHTML = "";
	if (!data) {
		document.getElementById("UniqueULBrror").innerHTML = "<font color='red'><br>Same Name ULB Alerady Exist, Please Give another Name </font>";

		document.getElementById("txtlocalBodyNameInEn").value = "";
		document.getElementById("txtlocalBodyNameInEn").focus();
		$('#ddUrbanLocalBodyTypeNew').prop('selectedIndex', 0);

	}

}

function handleUniqueULBError() {
	document.getElementById("txtlocalBodyNameInEn").value = "";
	document.getElementById("txtlocalBodyNameInEn").focus();
	$('#ddUrbanLocalBodyTypeNew').prop('selectedIndex', 0);
}
function ULbTypeDefualtSet() {
	$('#ddUrbanLocalBodyTypeNew').prop('selectedIndex', 0);
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
	dwr.util.removeAllOptions("ddSourceCoveredAreaRLB");
	dwr.util.removeAllOptions("ddDestCoveredAreaRLB");
	
}
function undoSelect(list1, list2, doRemoveVal) {
	var lastindex = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++)
			if (document.getElementById(list1).options[j].selected == true)
				if (doRemoveVal) {
					if (document.getElementById(list1)[j].value.indexOf("P") > 0) {
						lastindex = document.getElementById(list1)[j].value.indexOf("P");
					} else if (document.getElementById(list1)[j].value.indexOf("F") > 0) {
						lastindex = document.getElementById(list1)[j].value.indexOf("F");
					}
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, (lastindex - 1)) + ">"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.indexOf("("))
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
				}
		removeSelectedValue(list1);
	}

	if ($("#childbtn")) {
		document.getElementById("childbtn").disabled = false;
	}
}

/*
 * function askForUpgrade(obj) { $("#dialog-confirm").dialog({ resizable: false,
 * height:140, modal: true, buttons: { "Yes": function() { if(obj.value != 0) {
 * $("#trForUpgrade").show(); var selObj1 =
 * document.getElementById("ddUrbanLocalBodyType"); var selObj2 =
 * document.getElementById("ddUrbanLocalBodyTypeForUpgrade"); var tmp =
 * selObj1.value; selObj1.disabled = true; selObj2.disabled = false; if(tmp !=
 * 0) { var op =
 * document.getElementById("ddUrbanLocalBodyTypeForUpgrade").getElementsByTagName("option");
 * for (var i = 0; i < op.length; i++) { if (op[i].value == tmp) {
 * op[i].disabled = true; } } } } $( this ).dialog( "close" ); }, Cancel:
 * function() { $( this ).dialog( "close" ); } } }); }
 */

function askForUpgrade(obj) {
	if (obj.value != 0) {
		$("#trForUpgrade1").show();
	}
}

function selectForUpgrade(obj) {
	var selObj1 = document.getElementById("ddUrbanLocalBodyType");
	var selObj2 = document.getElementById("ddUrbanLocalBodyTypeForUpgrade");
	if (obj.value == "Y") {
		$("#trForUpgrade2").show();
		var tmp = selObj1.value;
		selObj1.disabled = true;
		selObj2.disabled = false;
		if (tmp != 0) {
			var op = document.getElementById("ddUrbanLocalBodyTypeForUpgrade").getElementsByTagName("option");
			for ( var i = 0; i < op.length; i++) {
				if (op[i].value == tmp) {
					op[i].disabled = true;
				} else {
					op[i].disabled = false;
				}
			}
		}
	} else {
		selObj2.disabled = true;
		$("#trForUpgrade2").hide();
		selObj1.disabled = false;
	}
}
function handleVillageLBListRecord(data) {
	var gpLbid = '';
	alert("handleVillageLBListRecord");
	for ( var i = 0; i < data.length; i++)
		gpLbid = gpLbid + "," + data[i].localBodyCode;
	document.getElementById("coverage").value = gpLbid.substring(1, gpLbid.length);

}

function getVillageLBforRLBULB(id) {
	fulLB = 'N';
	var stateCode = document.getElementById("stateid").value;
	$("#ddSourceVillageRLBS").empty();
	$("#ddDestVillageRLBs").empty();
	$("#ddSourceCoveredAreaRLB").empty();
	$("#ddDestCoveredAreaRLB").empty();
	if (id != 0 ) {
		dwr.util.removeAllOptions("ddDestVillageRLBs");
		dwr.util.removeAllOptions("ddSourceVillageRLBS");
		dwr.util.removeAllOptions("ddSourceCoveredAreaRLB");
		dwr.util.removeAllOptions("ddDestCoveredAreaRLB");
		lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
			callback : handleVillageLocalBodiesSuccess,
			errorHandler : handleVillageLocalBodiesError
		});
	} else if (id != 0) {
		$("#dialog:ui-dialog").dialog("destroy");
		$("#dialog-commune").dialog({
			resizable : false,
			height : 140,
			modal : true,
			buttons : {
				"Yes" : function() {

					$(this).dialog("close");

					var r = confirm("The Entire Commune Panchayat will be Invalidated, Click ok to Continue");
					if (r == true) {
						lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
							callback : handleVillageLBListRecord,
							errorHandler : handleVillageLocalBodiesError
						});
						fulLB = 'Y';
						document.getElementById("lbFull").value = 'Y';
						validateFirstAll('R');
					} else {
						fulLB = 'N';
						dwr.util.removeAllOptions("ddDestVillageRLBs");
						dwr.util.removeAllOptions("ddSourceVillageRLBS");
						dwr.util.removeAllOptions("ddSourceCoveredAreaRLB");
						dwr.util.removeAllOptions("ddDestCoveredAreaRLB");
						lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
							callback : handleVillageLocalBodiesSuccess,
							errorHandler : handleVillageLocalBodiesError
						});
					}

				},
				"No" : function() {
					if (id != 0) {
						fulLB = 'N';
						dwr.util.removeAllOptions("ddDestVillageRLBs");
						dwr.util.removeAllOptions("ddSourceVillageRLBS");
						dwr.util.removeAllOptions("ddSourceCoveredAreaRLB");
						dwr.util.removeAllOptions("ddDestCoveredAreaRLB");
						lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
							callback : handleVillageLocalBodiesSuccess,
							errorHandler : handleVillageLocalBodiesError
						});
					}
					$(this).dialog("close");
				}
			}
		});
	}

}