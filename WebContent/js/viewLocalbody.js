// Start of Local Govt LocalBody

function validateViewLocalBodyType() {

	var ddLBTypeCode = document.getElementById('tierSetupCode');

	if (ddLBTypeCode.selectedIndex == 0) {
		$("#tierSetupCode_error").show();
		return false;
	}
	$("#tierSetupCode_error").hide();
	return true;

}

function loadviewLBPage() {
	// alert("HariomLoad View Page");
	$("#localGovtBodyListMain_error").hide();
	$("#tierSetupCode_error").hide();
	$("#ddSourceLocalBody_error").hide();
	$("#parentLocalBody_error").hide();
	$("#localGovtBodyVillList_error").hide();

	var districtPanchayat = document.getElementById('hdnDistrictPanchayat').value;
	var typeCode = document.getElementById('hdnType').value;

	if (typeCode != "") {
		dwr.util.setValue('tierSetupCode', parseInt(typeCode));
		setTimeout("getLocalBody('typeCode','tierSetupCode')", 10);
	}
	var ddLBTypeCode = document.getElementById('tierSetupCode').value;
	var districtCode = dwr.util.getValue('districtCode');
	var lgdLBType = dwr.util.getValue('lbType');

	hideShowDistIVMunici(ddLBTypeCode, districtCode, lgdLBType);
	var intermediatePanchayat = document.getElementById('hdnIntermediatePanchayat').value;

	if (districtPanchayat != 0) {
		$('#hdnDistrictPanchayat').show();
		dwr.util.setValue('ddSourceLocalBody', parseInt(districtPanchayat));
		setTimeout("getLocalBody('hdnDistrictPanchayat','ddSourceLocalBody')", 10);
	}
	if (intermediatePanchayat != 0) {
		if (districtPanchayat != 0) {
			getLocalBodySubDistrictPanList(parseInt(districtPanchayat));
		}
	}

	if (intermediatePanchayat != 0) {
		dwr.util.setValue('localGovtBodyListMain', parseInt(intermediatePanchayat));
		setTimeout("getLocalBody('hdnIntermediatePanchayat','localGovtBodyListMain')", 1200);
	}
	document.getElementById('hdnType').value = '';

}

function loadviewLBPageTrad() {
	// alert("HariomLoad View Page");
	$("#ddlgdLBDistrictAtVillage_error").hide();
	$("#tierSetupCode_error").hide();
	$("#ddlgdLBDistAtInter_error").hide();
	$("#parentLocalBody_error").hide();
	$("#localGovtBodyVillList_error").hide();

	var districtPanchayat = document.getElementById('hdnDistrictPanchayat').value;
	var typeCode = document.getElementById('hdnType').value;

	if (typeCode != "") {
		dwr.util.setValue('tierSetupCode', parseInt(typeCode));
		setTimeout("getLocalBody('typeCode','tierSetupCode')", 10);
	}
	var ddLBTypeCode = document.getElementById('tierSetupCode').value;
	var districtCode = dwr.util.getValue('districtCode');
	var lgdLBType = dwr.util.getValue('lbType');

	hideShowDivforLocalBodyTradView(ddLBTypeCode, districtCode, lgdLBType);
	var intermediatePanchayat = document.getElementById('hdnIntermediatePanchayat').value;

	if (districtPanchayat != 0) {
		dwr.util.setValue('ddlgdLBDistAtInter', parseInt(districtPanchayat));
		setTimeout("getLocalBody('hdnDistrictPanchayat','ddlgdLBDistAtInter')", 1000);
	}
	if (intermediatePanchayat != 0) {
		if (districtPanchayat != 0) {
			getLocalBodySubDistrictPanList(parseInt(districtPanchayat));
		}
	}

	if (intermediatePanchayat != 0) {

		dwr.util.setValue('ddlgdLBDistrictAtVillage', parseInt(intermediatePanchayat));
		setTimeout("getLocalBody('hdnIntermediatePanchayat','ddlgdLBDistrictAtVillage')", 1200);
	}
	document.getElementById('hdnType').value = '';

}

function getLocalBody(obj1, obj2) {
	document.getElementById(obj2).value = document.getElementById(obj1).value;
}

/*
 * function validateViewLBPage() { var ddLBTypeCode=
 * document.getElementById('tierSetupCode').value;
 * 
 * var temp = ddLBTypeCode.split(":");
 * 
 * var id2=temp[1];
 * 
 * var id3=temp[2]; var msg=null;
 * 
 * if (!validateViewLocalBodyType()) {
 * 
 * msg="Please Select Local Body Type"+'\n'; } if(id3 != 'U' && id2=='I' ||
 * id2=='V' ) {
 * 
 * if(! validateDistrictPanchayt()) { if(msg!=null) { msg=msg+"Please Select
 * District Panchayat"+ '\n'; } else { msg="Please Select District Panchayat"+
 * '\n'; } } } if(id2=='V') { if(!validateIntermediatePanchayt()) {
 * if(msg!=null) { msg=msg+"Please Select Intermediate Panchayat"+ '\n'; } else {
 * msg="Please Select Intermediate Panchayat"+ '\n'; } } }
 * 
 * if(msg!=null) { return false; } else { return true; } return false; }
 */

function validateViewLBPage() {

	var ddLBTypeCode = document.getElementById('tierSetupCode').value;

	var temp = ddLBTypeCode.split(":");

	var id2 = temp[1];

	var id3 = temp[2];
	var msg = null;
	var errors = new Array();
	var error = false;
	errors[0] = !validateViewLocalBodyType();
	if (id3 != 'U' && id2 == 'I' || id2 == 'V') {
		errors[1] = !validateDistrictPanchayt();
	}
	if (id2 == 'V') {
		errors[2] = !validateIntermediatePanchayt();
	}
	for ( var i = 0; i < 3; i++) {

		if (errors[i] == true) {

			error = true;
			break;
		}
	}

	if (error == true) {

		showClientSideError();

		return false;
	} else {
		/*
		 * start Autohr Kirandeep Date 18/06/2014
		 */
		// $('input[name=Submit]').prop('disabled', true);
		return true;
	}

}
function validateViewLBTraditionalPage() {
	var ddLBTypeCode = document.getElementById('tierSetupCode').value;
	var parent_level = document.getElementById('parent_level_hierarchy').value;
	var temp = ddLBTypeCode.split(":");
	var id2 = temp[1];

	var id3 = temp[2];
	var msg = null;
	var errors = new Array();
	var error = false;
	errors[0] = !validateViewLocalBodyType();
	if (id3 != 'U' && (id2 == 'I' && temp[4] != 0) || (id2 == 'V' && temp[4] != 0 && parent_level == 'D')) {
		errors[1] = !validateDistrictCouncil();
	}
	if (id2 == 'V' && temp[4] != 0 && parent_level == 'I') {
		errors[2] = !validateBAC();
	}
	for ( var i = 0; i < 3; i++) {
		if (errors[i] == true) {
			error = true;
			break;
		}
	}

	if (error == true) {
		showClientSideError();
		return false;
	} else {
		return true;
	}
}
function validateIntermediatePanchayt() {
	var gtaAndDistList = $('#gtadp').val();
	var localGovtBodyListMain = document.getElementById('localGovtBodyListMain');
	var gtachild = $('#gtachild').val();
	var gtaIntermediateid = $('#gtaIntermediateid').val();
	var stateCode = dwr.util.getValue('hdnStateCode');
	if (stateCode == 19) {
		if (gtaAndDistList == "") {
			alert("please select Parent List");
			return false;
		}
		if (gtaAndDistList == 1) {
			// alert("Gta value=" + gtaAndDistList);
			if (localGovtBodyListMain.selectedIndex == 0) {
				$("#localGovtBodyListMain_error").show();
				return false;
			} else if (localGovtBodyListMain.selectedIndex == -1) {
				$("#localGovtBodyListMain_error").show();
				return false;
			}

		}
		if (gtaAndDistList == 20) {
			if (gtachild == 0) {

				alert("please select at lest one GTA");
				return false;
			}
			if (gtaIntermediateid == 0) {

				alert("please select at least one Intermediate GAT");
				return false;
			}

		}
	}

	$("#localGovtBodyListMain_error").hide();
	return true;
}
function validateBAC() {
	var localGovtBodyListMain = document.getElementById('ddlgdLBDistrictAtVillage');

	if (localGovtBodyListMain.selectedIndex == 0) {
		$("#ddlgdLBDistrictAtVillage_error").show();
		return false;
	} else if (localGovtBodyListMain.selectedIndex == -1) {
		$("#ddlgdLBDistrictAtVillage_error").show();
		return false;
	}
	$("#ddlgdLBDistrictAtVillage_error").hide();
	return true;
}
function validateDistrictPanchayt() {
	var ddSourceLocalBody = document.getElementById('ddSourceLocalBody');
	var gtadp = $('#gtadp').val();
	var gtachild = $('#gtachild').val();
	var stateCode = dwr.util.getValue('hdnStateCode');
	if (stateCode == 19) {
		if (gtadp == "") {

			alert("please select at lest one Parent type");
			return false;
		}

		if (gtadp == 1) {
			if (ddSourceLocalBody.selectedIndex == 0) {
				$("#ddSourceLocalBody_error").show();
				return false;
			} else if (ddSourceLocalBody.selectedIndex == -1) {
				$("#ddSourceLocalBody_error").show();
				return false;
			}
		}

		if (gtadp == 20) {
			if (gtachild == 0) {

				alert("please select at lest one GTA");
				return false;
			}
		}
	} else if (ddSourceLocalBody.selectedIndex == 0) {
		$("#ddSourceLocalBody_error").show();
		return false;
	} else if (ddSourceLocalBody.selectedIndex == -1) {
		$("#ddSourceLocalBody_error").show();
		return false;
	}

	$("#ddSourceLocalBody_error").hide();
	return true;
}

function validateDistrictCouncil() {
	var ddSourceLocalBody = document.getElementById('ddlgdLBDistAtInter');

	if (ddSourceLocalBody.selectedIndex == 0) {
		$("#ddlgdLBDistAtInter_error").show();
		return false;
	} else if (ddSourceLocalBody.selectedIndex == -1) {
		$("#ddlgdLBDistAtInter_error").show();
		return false;
	}
	$("#ddlgdLBDistAtInter_error").hide();
	return true;
}
// for manage localbody part
function hideShowDistIVMunici(id, districtCode, lbtype) {
	var temp = id.split(":");
	var id1 = temp[0];
	var id2 = temp[1];
	var id3 = temp[2];
	var id5 = temp[4];
	var stateCode = dwr.util.getValue('hdnStateCode');
	if (id3 == 'U') {
		// document.getElementById("msgid").innerHTML ="";
		document.getElementById("tr_district1").style.display = "none";
		document.getElementById("tr_intermediate1").style.display = "none";
	} else {
		switch (id2) {
		case "D":
			// document.getElementById("msgid").innerHTML ="";
			document.getElementById("tr_district1").style.display = "none";
			document.getElementById("tr_intermediate1").style.display = "none";
			/*
			 * if(districtCode !=0) { alert("inside D");
			 * getLandRegionWisedistrict(lbtype,districtCode,id1); }
			 */
			break;
		case "I":

			/*
			 * if(districtCode !=0) { alert("inside I");
			 * getLandRegionWisedistrict(lbtype,districtCode,id1); }
			 */
			document.getElementById("tr_district1").style.display = 'block';
			document.getElementById("tr_intermediate1").style.display = "none";

			/* modification to show GTA List in post */

			if (stateCode == 19) {
				RedirectFunction(temp);
				var gtadppost = document.getElementById('gtadppost').value;
				dwr.util.setValue('gtadp', parseInt(gtadppost));
				setTimeout("getLocalBody('gtadppost','gtadp')", 1000);
				if (gtadppost == 20) {
					document.getElementById("gtachildtr").style.display = "block";
					var getGtaList = $('#getGtaList').val();
					getParentLb(gtadppost);
					dwr.util.setValue('gtachild', parseInt(getGtaList));
					setTimeout("getLocalBody('getGtaList','gtachild')", 1000);
					$('#tr_intermediate1').hide();
				}
			}

			/* modification to show GTA List in post */

			if (districtCode != 0) {
				getLandRegionWiseDisF(id1, id2, districtCode, lbtype);
			}

			break;
		case "V":
			document.getElementById("tr_district1").style.display = "block";
			document.getElementById("tr_intermediate1").style.display = "block";

			if (stateCode == 19) {
				RedirectFunction(temp);
				var gtadppost = document.getElementById('gtadppost').value;
				dwr.util.setValue('gtadp', parseInt(gtadppost));
				setTimeout("getLocalBody('gtadppost','gtadp')", 1000);
				if (gtadppost == 20) {
					document.getElementById("gtachildtr").style.display = "block";
					var getGtaList = $('#getGtaList').val();
					getParentLb(gtadppost);
					dwr.util.setValue('gtachild', parseInt(getGtaList));
					setTimeout("getLocalBody('getGtaList','gtachild')", 1000);
					$('#gtaIntermediate').show();
					var getGtaInterPanch = $('#getGtaInterPanch').val();
					getGtaIntermediateDataPost(getGtaList);
					dwr.util.setValue('gtaIntermediateid', parseInt(getGtaInterPanch));
					setTimeout("getLocalBody('getGtaInterPanch','gtaIntermediateid')", 1000);
					$('#tr_intermediate1').hide();
				}

				/*
				 * setTimeout(function() { $('#gtadp').val(gtadp); }, 1000);
				 */
			}

			if (id5 != 0) {
				if (districtCode == 0) {
					getLocalBodyListbylblcCodeFinal(stateCode, id5);
				}
				if (districtCode != 0) {
					getLandRegionWiseDisF(id1, id2, districtCode, lbtype);
				}
			}

			break;
		default:
			document.getElementById("tr_district1").style.display = "none";
			document.getElementById("tr_intermediate1").style.display = "none";
		}

	}
}

// for manage localbody part Traditional
function hideShowDistIVMuniciTrad(id, districtCode, lbtype) {
	var temp = id.split(":");
	var id1 = temp[0];
	var id2 = temp[1];
	var id3 = temp[2];
	var id5 = temp[4];
	var stateCode = dwr.util.getValue('hdnStateCode');
	if (id3 == 'U') {
		// document.getElementById("msgid").innerHTML ="";
		document.getElementById("tr_district1").style.display = "none";
		document.getElementById("tr_intermediate1").style.display = "none";
	} else {
		switch (id2) {
		case "D":
			// document.getElementById("msgid").innerHTML ="";
			document.getElementById("tr_district1").style.display = "none";
			document.getElementById("tr_intermediate1").style.display = "none";
			/*
			 * if(districtCode !=0) { alert("inside D");
			 * getLandRegionWisedistrict(lbtype,districtCode,id1); }
			 */
			break;
		case "I":

			/*
			 * if(districtCode !=0) { alert("inside I");
			 * getLandRegionWisedistrict(lbtype,districtCode,id1); }
			 */
			document.getElementById("tr_district1").style.display = 'block';
			document.getElementById("tr_intermediate1").style.display = "none";
			if (districtCode != 0) {
				getLandRegionWiseDisF(id1, id2, districtCode, lbtype);
			}

			break;
		case "V":
			document.getElementById("tr_district1").style.display = "block";
			document.getElementById("tr_intermediate1").style.display = "block";
			if (id5 != 0) {
				if (districtCode == 0) {
					getLocalBodyListbylblcCodeFinalTrad(stateCode, id5);
				}
				if (districtCode != 0) {
					getLandRegionWiseDisF(id1, id2, districtCode, lbtype);
				}
			}

			break;
		default:
			document.getElementById("tr_district1").style.display = "none";
			document.getElementById("tr_intermediate1").style.display = "none";
		}

	}
}

function getLocalBodyListbylblcCodeFinalTrad(stateCode, lblc) {

	lgdDwrDesignationService.getLocalbodyDetailbyCode(stateCode, lblc, {
		callback : handleLocalbodylblcCodeSuccessFInalTrad,
		errorHandler : handleLocalbodylblcCodeErrorFinalTrad
	});
}

function handleLocalbodylblcCodeSuccessFInalTrad(data) {
	var stateCode = dwr.util.getValue('hdnStateCode');

	var districtCode = dwr.util.getValue('districtCode');
	var lbtype = dwr.util.getValue('lbType');
	var lgdLBType = dwr.util.getValue('ddLgdLBType');
	var temp = lgdLBType.split(":");
	var id1 = temp[0];
	var id2 = temp[1];

	var lblc = data[0].localBodyTypeCode;
	// alert(statecode);
	var plblc = data[0].parentTierSetupCode;
	var level = data[0].level;
	var name = data[0].localBodyTypeName;

	if (level == 'D') {
		if (plblc == 0) {
			if (districtCode == 0) {
				getdisVillagePanchayatListforVPTrad(stateCode, lblc);
			}
			if (districtCode != 0) {
				getLandRegionWiseDisFinal(id1, id2, districtCode, lbtype);
			}
		}
	}

	if (level == 'I') {
		if (plblc == 0) {
			getInterPanchayatListforIP(stateCode, lblc);
		}
	}

	/*
	 * else if (level == 'I') {
	 * 
	 * if (plblc == 0) { document.getElementById('divLgdVillageP').style.display =
	 * 'block'; document.getElementById('divLgdVillageP').style.visibility =
	 * 'visible'; dwr.util.removeAllOptions('ddLgdLBInterListAtVillageCA');
	 * getdisInterPanchayatListforIP(stateCode, lblc); } else {
	 * document.getElementById('divLgdVillageP').style.display = 'block';
	 * document.getElementById('divLgdVillageP').style.visibility = 'visible';
	 * dwr.util.removeAllOptions('ddLgdLBInterListAtVillageCA');
	 * getLocalBodyListbylblcCodeF(stateCode, plblc); } }
	 */
}

function handleLocalbodylblcCodeErrorFinalTrad() {
	// Show a popup message
	alert("No data found in local body by lblc!");
}

function getdisVillagePanchayatListforVPTrad(id1, id2) {

	lgdDwrlocalBodyService.getPanchayatListbylblcCode(id1, id2, {
		callback : handledisVillagePanchayatSuccessforVPTrad,
		errorHandler : handledisVillagePanchayatErrorforVPTrad
	});
}

function handledisVillagePanchayatSuccessforVPTrad(data) {
	var fieldId = 'ddSourceLocalBody';
	var fieldId1 = 'localGovtBodyListMain';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);
	var st = document.getElementById('ddSourceLocalBody');
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
	dwr.util.addOptions(fieldId1, data, valueText, nameText);
}

function handledisVillagePanchayatErrorforVPTrad() {
	// Show a popup message
	alert("No data found in District Panchayat!");
}

function getLandRegionWiseDisF(id1, id2, districtCode, lbtype) {
	lgdDwrlocalBodyService.getLandRegionWise(id1, id2, districtCode, lbtype, {
		callback : handledisVillagePanchayatSuccess,
		errorHandler : handledisVillagePanchayatError
	});
}

function handledisVillagePanchayatSuccess(data) {

	var fieldId = 'ddSourceLocalBody';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddSourceLocalBody');
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handledisVillagePanchayatError() {
	// Show a popup message
	alert("No data found in District Panchayat!");
}

function getLocalBodyListbylblcCodeFinal(stateCode, lblc) {

	lgdDwrDesignationService.getLocalbodyDetailbyCode(stateCode, lblc, {
		callback : handleLocalbodylblcCodeSuccessFInal,
		errorHandler : handleLocalbodylblcCodeErrorFinal
	});
}

function handleLocalbodylblcCodeSuccessFInal(data) {
	var stateCode = dwr.util.getValue('hdnStateCode');

	var districtCode = dwr.util.getValue('districtCode');
	var lbtype = dwr.util.getValue('lbType');
	var lgdLBType = dwr.util.getValue('ddLgdLBType');
	var temp = lgdLBType.split(":");
	var id1 = temp[0];
	var id2 = temp[1];

	var lblc = data[0].localBodyTypeCode;
	// alert(statecode);
	var plblc = data[0].parentTierSetupCode;
	var level = data[0].level;
	var name = data[0].localBodyTypeName;

	/*
	 * if (level == 'D') {
	 * 
	 * if (plblc == 0) { if (districtCode == 0) {
	 * getdisVillagePanchayatListforVP(stateCode, lblc); } if (districtCode !=
	 * 0) { getLandRegionWiseDisFinal(id1, id2, districtCode, lbtype); } } }
	 */
	if (level == 'I') {
		if (plblc == 0) {
			getInterPanchayatListforIP(stateCode, lblc);
		}
	}

	/*
	 * else if (level == 'I') {
	 * 
	 * if (plblc == 0) { document.getElementById('divLgdVillageP').style.display =
	 * 'block'; document.getElementById('divLgdVillageP').style.visibility =
	 * 'visible'; dwr.util.removeAllOptions('ddLgdLBInterListAtVillageCA');
	 * getdisInterPanchayatListforIP(stateCode, lblc); } else {
	 * document.getElementById('divLgdVillageP').style.display = 'block';
	 * document.getElementById('divLgdVillageP').style.visibility = 'visible';
	 * dwr.util.removeAllOptions('ddLgdLBInterListAtVillageCA');
	 * getLocalBodyListbylblcCodeF(stateCode, plblc); } }
	 */
}

function handleLocalbodylblcCodeErrorFinal() {
	// Show a popup message
	alert("No data found in local body by lblc!");
}

function getInterPanchayatListforIP(id1, id2) {

	lgdDwrlocalBodyService.getPanchayatListbylblcCode(id1, id2, {
		callback : handlInterPanchayatSuccforIP,
		errorHandler : handlInterPanchayatErrforIP
	});
}

function handlInterPanchayatSuccforIP(data) {

	var fieldId = 'localGovtBodyListMain';

	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('localGovtBodyListMain');
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handlInterPanchayatErrforIP() {
	// Show a popup message
	alert("No data found in District Panchayat!");
}

function getLandRegionWisedistrict(lbtype, districtCode, id1) {
	lgdDwrlocalBodyService.getLandRegionWisedistrict(lbtype, districtCode, id1, {
		callback : handleLandRegionWisedistrictSuccessDis,
		errorHandler : handleLandRegionWisedistrictErrorDis
	});
}

function handleLandRegionWisedistrictSuccessDis(data) {
	var fieldId = 'ddLgdLBDistPSourceList';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	// var st = document.getElementById('ddLgdLBDistPSourceList');
	// st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleLandRegionWisedistrictErrorDis() {
	alert("No data found!");
}
function getInterPanchayatbyDcode(id) {

	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleInterPanchayatICASuccess,
		errorHandler : handleInterPanchayatICAError
	});
}

function handleInterPanchayatICASuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddlgdLBInterAtVillage';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddlgdLBInterAtVillage');

	st.add(new Option('Select Intermediate Panchayat', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

	// dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleInterPanchayatICAError() {
	// Show a popup message
	alert("No data found!");
}

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
	st.add(new Option('Select District Panchayat', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleDLBError12() {
	// Show a popup message
	alert("No data found!");
}

// end here of manage local body

// For Local Govt Body **

function getLocalBodySubDistrictPanListF(id) {
	if (id != '') {
		lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
			callback : handleLocalGovtBodySDPSuccess,
			errorHandler : handleLocalGovtBodySDPError
		});
	}
}

function getLocalBodySubDistrictPanList(id, val) {
	if (val != '') {
		lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
			callback : handleLocalGovtBodySDPSuccess,
			errorHandler : handleLocalGovtBodySDPError
		});
	}
}

function handleLocalGovtBodySDPSuccess(data) {
	var localGovtBodyListMain = document.getElementById('localGovtBodyListMain');

	/*
	 * if (localGovtBodyListMain != null) { // Assigns data to result id var
	 * fieldId = 'localGovtBodyListMain'; var valueText = 'localBodyCode'; var
	 * nameText = 'localBodyNameEnglish'; dwr.util.removeAllOptions(fieldId);
	 * 
	 * var st = document.getElementById('localGovtBodyListMain'); st.add(new
	 * Option('Select Intermediate Body', '0')); dwr.util.addOptions(fieldId,
	 * data, valueText, nameText); }
	 */

	if (localGovtBodyListMain != null) {
		var fieldId = 'localGovtBodyListMain';
		dwr.util.removeAllOptions(fieldId);
		var options = $("#localGovtBodyListMain");
		options.append(new Option('Select Intermediate Body', '0'));
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
}

function handleLocalGovtBodySDPError() {
	// Show a popup message
	alert("No data found !");
}

function getLocalBodyList(id) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleLocalGovtBodySuccess,
		errorHandler : handleLocalGovtBodyError
	});
}

// data contains the returned value
function handleLocalGovtBodySuccess(data) {
	// Assigns data to result id
	var fieldId = 'localGovtBodyListMain';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('localGovtBodyListMain');
	st.add(new Option('Select Intermediate Body', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleLocalGovtBodyError() {
	// Show a popup message
	alert("No data found!");
}

function getLocalBodyVillageParent(id) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleLocalVillageParentSuccess,
		errorHandler : handleLocalVillageParentError
	});
}

// data contains the returned value
function handleLocalVillageParentSuccess(data) {
	// Assigns data to result id
	var fieldId = 'localBodyVillageParent';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('localBodyVillageParent');
	st.add(new Option('Select Intermediate Body', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleLocalVillageParentError() {
	// Show a popup message
	alert("No data found!");
}

function excludeTopSelectAndSubmitView(objName, formName) {
	var tierSetupCode = document.getElementById("tierSetupCode").value;

	var temp = tierSetupCode.split(":");
	var id1 = temp[0];
	var id2 = temp[1];
	var ddSourceLocalBody = document.getElementById("ddSourceLocalBody").value;
	var localGovtBodyListMain = document.getElementById("localGovtBodyListMain").value;

	if (id2 == "D") {
		document.forms[formName].submit();
	}
	if (id2 == "I") {
		document.forms[formName].submit();

	}
	if (id2 == "V") {
		document.forms[formName].submit();

	}

}

function setandGetSubDistListView(id) {
	setLocalBodyIntermediateList(id);
	getLocalSubDistBodyList(id);
}

function setLocalBodyIntermediateList(id) {
	dwr.util.setValue('localDistGovtBodyListMain', id);
}

function getLocalSubDistBodyList(id) {
	lgdDwrlocalBodyService.getLocalGovtBodyList(id, {
		callback : handleSubDistGovtBodySuccess,
		errorHandler : handleSubDistGovtBodyError
	});
}

// data contains the returned value
function handleSubDistGovtBodySuccess(data) {
	// Assigns data to result id
	var fieldId = 'localSubDistGovtBodyListMain';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	// dwr.util.removeAllOptions(fieldId);
	// var dataq = [ {name:"Select Intermediate Body"} ];
	// dwr.util.addOptions(fieldId, dataq,'0', "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleSubDistGovtBodyError() {
	// Show a popup message
	alert("No data found!");
}
// End here for Subdistrict body
function setandgetLocalBodyViewList(id) {
	setLocalBodyVillageList(id);
	getLocalBodyVillageList(id);
}

function setLocalBodyVillageList(id) {
	dwr.util.setValue('localGovtBodyListSubDistrict', id);
	dwr.util.setValue('localGovtBodyListSubDistrict', id);// added new one
	// 3-11-11
	dwr.util.setValue('localGovtBodyVillageList', id);// add new on 3-11-11
}

function getLocalBodyVillageList(id) {

	lgdDwrlocalBodyService.getLocalGovtBodyVillageList(id, {
		callback : handleLocalGovtBodyVillageSuccess,
		errorHandler : handleLocalGovtBodyVillageError
	});
}

// data contains the returned value
function handleLocalGovtBodyVillageSuccess(data) {
	// Assigns data to result id

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

function validateModify() {
	// alert("inside function");
	var msg = null;
	var urbanTypeCheck = false;
	var mofilyParentCheck = false;
	var modifyNameCheck = document.getElementById('localBodyNameInEn');
	// alert("inside function"+localBodyNameInEn);
	if (document.getElementById('hiddenCheckBox').value == 'U') {
		urbanTypeCheck = document.getElementById('modifyType');
	}
	if (document.getElementById('hiddenCheckBox').value == 'I' || document.getElementById('hiddenCheckBox').value == 'V') {
		mofilyParentCheck = document.getElementById('modifyParent');
	}
	if (!validateOrdeNo()) {
		if (msg != null) {
			msg = msg + "Please Check Error in entering Order No." + '\n';
		} else {
			msg = "Please Check Error in entering Order No." + '\n';
		}
	}
	if (!validateOrdeDate()) {
		if (msg != null) {
			msg = msg + "Please Check Error in entering Order Date" + '\n';
		} else {
			msg = "Please Check Error in entering Order Date" + '\n';
		}
	}
	if (!validateEffecDate()) {
		if (msg != null) {
			msg = msg + "Please Check Error in entering Effective Date" + '\n';
		} else {
			msg = "Please Check Error in entering Effective Date" + '\n';
		}
	}
	if (!validateGazPubDate()) {
		if (msg != null) {
			msg = msg + "Please Check Error in entering Gazette Publication Date" + '\n';
		} else {
			msg = "Please Check Error in entering Gazette Publication Date " + '\n';
		}
	}
	var hdnOrderCode = document.getElementById('hdnOrderCode').value;

	if (hdnOrderCode == "") {
		if (!validateSFile1()) {
			if (msg != null) {
				msg = msg + "Please Check Error in Uploading File" + '\n';
			} else {
				msg = "Please Check Error in Uploading File" + '\n';
			}
		}
	}
	if (urbanTypeCheck.checked) {

		if (!localBodyTypeMunicipility()) {

			if (msg != null) {
				msg = msg + "Please select the new local government type." + '\n';
			} else {
				msg = "Please select the new local government type." + '\n';
			}
		}
	}

	if (!checkBoxces()) {
		if (msg != null) {
			msg = msg + "You didn\'t choose any of the checkboxes!" + '\n';
		} else {
			msg = "You didn\'t choose any of the checkboxes!" + '\n';
		}
	}
	if (modifyNameCheck.checked) {

		if (!validateLocalNameInEngModify()) {
			if (msg != null) {
				msg = msg + "Local Body Name (in English) is required." + '\n';
			} else {
				msg = "Local Body Name (in English) is required." + '\n';
			}
		}

	}
	if (mofilyParentCheck.checked) {
		if (!localBodyParentCheck()) {
			if (msg != null) {
				msg = msg + "Please select the appropriate parent of the local government body." + '\n';
			} else {
				msg = "Please select the appropriate parent of the local government body." + '\n';
			}
		}

	}

	if (msg != null) {
		alert(msg);
		return false;
	} else {
		return true;
	}

	return false;

}

function validateModifyfororder() {
	var errors = new Array();
	var error = false;
	var govtOrderStatus = document.getElementById('hdngovtOrderConfig').value;

	var selObj = document.getElementById('ddLgdUrbanLBDistExistDest');
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}

	var selObj1 = document.getElementById('ddLgdUrbanLBSubdistrictListDest');
	for ( var i = 0; i < selObj1.options.length; i++) {
		selObj1.options[i].selected = true;
	}
	var selObj2 = document.getElementById('ddLgdUrbanLBDistUmappedDest');
	for ( var i = 0; i < selObj2.options.length; i++) {
		selObj2.options[i].selected = true;
	}

	var selObj3 = document.getElementById('availddLgdLBInterCAreaSourceLUmappedUrban');
	for ( var i = 0; i < selObj3.options.length; i++) {
		selObj3.options[i].selected = true;
	}

	var selObj4 = document.getElementById('availddLgdLBDistCAreaSourceLUmapped');
	for ( var i = 0; i < selObj4.options.length; i++) {
		selObj4.options[i].selected = true;
	}
	var selObj5 = document.getElementById('availddLgdLBSubDistrictSourceLatDCAUmapped');
	for ( var i = 0; i < selObj5.options.length; i++) {
		selObj5.options[i].selected = true;
	}
	var selObj6 = document.getElementById('availddLgdLBVillageSourceLatDCAUmapped');
	for ( var i = 0; i < selObj6.options.length; i++) {
		selObj6.options[i].selected = true;
	}
	var selObj7 = document.getElementById('ddLgdLBDistCAreaDestL');
	for ( var i = 0; i < selObj7.options.length; i++) {
		selObj7.options[i].selected = true;
	}
	var selObj8 = document.getElementById('ddLgdLBSubDistrictDestLatDCA');
	for ( var i = 0; i < selObj8.options.length; i++) {
		selObj8.options[i].selected = true;
	}
	var selObj9 = document.getElementById('ddLgdLBVillageDestLatDCA');
	for ( var i = 0; i < selObj9.options.length; i++) {
		selObj9.options[i].selected = true;
	}
	var selObj10 = document.getElementById('ddLgdLBDistCAreaDestLUmapped');
	for ( var i = 0; i < selObj10.options.length; i++) {
		selObj10.options[i].selected = true;
	}
	var selObj11 = document.getElementById('ddLgdLBSubDistrictDestLatDCAUmapped');
	for ( var i = 0; i < selObj11.options.length; i++) {
		selObj11.options[i].selected = true;
	}

	var selObj12 = document.getElementById('ddLgdLBVillageDestLatDCAUmapped');
	for ( var i = 0; i < selObj12.options.length; i++) {
		selObj12.options[i].selected = true;
	}

	var selObj13 = document.getElementById('availddLgdLBInterCAreaSourceLUmapped');
	for ( var i = 0; i < selObj13.options.length; i++) {
		selObj13.options[i].selected = true;
	}
	var selObj14 = document.getElementById('availddLgdLBVillageSourceLatICAUmapped');
	for ( var i = 0; i < selObj14.options.length; i++) {
		selObj14.options[i].selected = true;
	}
	var selObj15 = document.getElementById('ddLgdLBInterCAreaDestL');
	for ( var i = 0; i < selObj15.options.length; i++) {
		selObj15.options[i].selected = true;
	}
	var selObj16 = document.getElementById('ddLgdLBVillageDestLatICA');
	for ( var i = 0; i < selObj16.options.length; i++) {
		selObj16.options[i].selected = true;
	}
	var selObj17 = document.getElementById('ddLgdLBInterCAreaDestLUmapped');
	for ( var i = 0; i < selObj17.options.length; i++) {
		selObj17.options[i].selected = true;
	}
	var selObj18 = document.getElementById('ddLgdLBVillageDestLatICAUmapped');
	for ( var i = 0; i < selObj18.options.length; i++) {
		selObj18.options[i].selected = true;
	}
	var selObj19 = document.getElementById('availddLgdLBVillageCAreaSourceLUnmapped');
	for ( var i = 0; i < selObj19.options.length; i++) {
		selObj19.options[i].selected = true;
	}
	var selObj20 = document.getElementById('ddLgdLBVillageCAreaDestL');
	for ( var i = 0; i < selObj20.options.length; i++) {
		selObj20.options[i].selected = true;
	}
	var selObj21 = document.getElementById('ddLgdLBVillageCAreaDestLUnmapped');
	for ( var i = 0; i < selObj21.options.length; i++) {
		selObj21.options[i].selected = true;
	}

	errors[0] = !validateOrderNo();
	errors[1] = !validateOrdeDate();
	errors[2] = !validateEffecDate();
	errors[3] = !validateGazPubDate();
	errors[4] = !validateSFile1();

	if (govtOrderStatus == 'govtOrderGenerate') {
		errors[5] = !validateFileTemplate();
	}

	for ( var i = 0; i < 6; i++) {
		if (errors[i] == true) {
			error = true;
			break;
		}
	}

	if (error == true) {
		showClientSideError();
		return false;
	} else {
		return true;
	}
	return false;
}

function validateModifyfororderChangeName() {
	var lbName = document.getElementById('lbNameEng').value;
	var lbNameNew = document.getElementById('localBodyNameInEn').value;
	lbNameNew = lbNameNew.replace(/^\s+|\s+$/g, '');
	if (lbName == lbNameNew) {
		document.getElementById("modifyNameError").innerHTML = "<font color='red'><br>Modification of Local Body Name (In English) Required</font>";
		return false;
	}

	var errors = new Array();
	var error = false;
	var govtOrderStatus = document.getElementById('hdngovtOrderConfig').value;

	errors[0] = !validateOrderNo();
	errors[1] = !validateOrdeDate();
	errors[2] = !validateEffecDate();

	if (govtOrderStatus == 'govtOrderUpload') {
		errors[3] = !validateGazPubDate();
		errors[4] = !validateSFile1();
	} else if (govtOrderStatus == 'govtOrderGenerate') {
		errors[5] = !validateFileTemplate();
	}

	// errors[6] = !validateLBNameEng();
	errors[6] = !validateLocalNameInEngMod();

	for ( var i = 0; i < 7; i++) {
		if (errors[i] == true) {
			error = true;
			break;
		}
	}

	if (error == true) {
		showClientSideError();
		return false;
	} else {
		$('input[name=submit]').prop('disabled', true);
		return true;
	}
	return false;
}

function validateforDisturbedState(categorycheck, levelcheck) {

	var errors = new Array();
	var error = false;

	var chkChangeNameDisturbed = document.getElementById('chkChangeNameDisturbed');

	if (chkChangeNameDisturbed.checked) {
		errors[0] = !validateLocalNameInEngMod();
	}
	if (categorycheck != 'U') {
		if (levelcheck != 'D') {
			var chkParentTypeDisturbed = document.getElementById('chkParentTypeDisturbed');
			if (chkParentTypeDisturbed.checked) {
				errors[1] = !validateParentLBtype();
			}
		}
	}

	for ( var i = 0; i < 2; i++) {
		if (errors[i] == true) {
			error = true;
			break;
		}
	}

	if (error == true) {
		showClientSideError();
		return false;
	} else {
		return true;
	}
	return false;
}

function validateModifyfororderChangeTypeULB() {

	var errors = new Array();
	var error = false;
	var govtOrderStatus = document.getElementById('hdngovtOrderConfig').value;
	errors[0] = !validateOrderNo();
	errors[1] = !validateOrdeDate();
	errors[2] = !validateEffecDate();
	errors[3] = !validateLBtype();

	if (govtOrderStatus == 'govtOrderUpload') {
		errors[4] = !validateGazPubDate();
		errors[5] = !validateSFile1();
	} else if (govtOrderStatus == 'govtOrderGenerate') {
		errors[6] = !validateFileTemplate();
	}

	for ( var i = 0; i < 7; i++) {
		if (errors[i] == true) {
			error = true;
			break;
		}
	}
	if (error == true) {
		showClientSideError();
		return false;
	} else {
		$('input:submit').attr("disabled", true);
		return true;
	}
	return false;
}

function validateModifyfororderChangeUpperTierLB() {

	var errors = new Array();
	var error = false;
	var govtOrderStatus = document.getElementById('hdngovtOrderConfig').value;
	errors[0] = !validateOrderNo();
	errors[1] = !validateOrdeDate();
	errors[2] = !validateEffecDate();
	errors[3] = !validateParentLBtype();

	if (govtOrderStatus == 'govtOrderUpload') {
		errors[4] = !validateGazPubDate();
		errors[5] = !validateSFile1();
	} else if (govtOrderStatus == 'govtOrderGenerate') {
		errors[6] = !validateFileTemplate();
	}

	for ( var i = 0; i < 7; i++) {
		if (errors[i] == true) {
			error = true;
			break;
		}
	}
	if (error == true) {
		showClientSideError();
		return false;
	} else {
		return true;
	}
	return false;
}

function validateCorrectCoverageLB() {

	var errors = new Array();
	var error = false;
	var temp = null;
	var testdata = false;
	var selObj = document.getElementById('ddLgdLBDistCAreaDestLUmapped');
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}
	var selObj1 = document.getElementById('ddLgdLBSubDistrictDestLatDCAUmapped');
	for ( var i = 0; i < selObj1.options.length; i++) {
		selObj1.options[i].selected = true;
	}
	var selObj2 = document.getElementById('ddLgdLBVillageDestLatDCAUmapped');
	for ( var i = 0; i < selObj2.options.length; i++) {
		selObj2.options[i].selected = true;
	}

	var selObj3 = document.getElementById('ddLgdLBInterCAreaDestLUmapped');
	for ( var i = 0; i < selObj3.options.length; i++) {
		selObj3.options[i].selected = true;
	}
	var selObj4 = document.getElementById('ddLgdLBVillageDestLatICAUmapped');
	for ( var i = 0; i < selObj4.options.length; i++) {
		selObj4.options[i].selected = true;
	}
	var selObj5 = document.getElementById('ddLgdLBVillageCAreaDestLUnmapped');
	for ( var i = 0; i < selObj5.options.length; i++) {
		selObj5.options[i].selected = true;
	}
	var selObj6 = document.getElementById('ddLgdLBInterCAreaDestLUmapped');
	for ( var i = 0; i < selObj6.options.length; i++) {
		selObj6.options[i].selected = true;
	}

	var selObj7 = document.getElementById('ddLgdUrbanLBDistUmappedDest');
	for ( var i = 0; i < selObj7.options.length; i++) {
		selObj7.options[i].selected = true;
	}

	var selObj7 = document.getElementById('availddLgdLBVillageCAreaSourceLUnmapped');
	for ( var i = 0; i < selObj7.options.length; i++) {
		selObj7.options[i].selected = true;
	}

	var selObj8 = document.getElementById('availddLgdLBInterCAreaSourceLUmappedUrban');
	for ( var i = 0; i < selObj8.options.length; i++) {
		selObj8.options[i].selected = true;
	}
	var selObj9 = document.getElementById('availddLgdLBInterCAreaSourceLUmapped');
	for ( var i = 0; i < selObj9.options.length; i++) {
		selObj9.options[i].selected = true;
	}
	var selObj10 = document.getElementById('availddLgdLBDistCAreaSourceLUmapped');
	for ( var i = 0; i < selObj10.options.length; i++) {
		selObj10.options[i].selected = true;
	}
	var selObj11 = document.getElementById('availddLgdLBSubDistrictSourceLatDCAUmapped');
	for ( var i = 0; i < selObj11.options.length; i++) {
		selObj11.options[i].selected = true;
	}
	var selObj12 = document.getElementById('availddLgdLBVillageSourceLatDCAUmapped');
	for ( var i = 0; i < selObj12.options.length; i++) {
		selObj12.options[i].selected = true;
	}
	var selObj13 = document.getElementById('availddLgdLBVillageSourceLatICAUmapped');
	for ( var i = 0; i < selObj13.options.length; i++) {
		selObj13.options[i].selected = true;
	}
	var landregionlength = document.getElementById('landregionlength').value;

	for (i = 0; i < landregionlength; i++) {

		if (temp == null) {
			if (document.getElementById("chkpartP" + i).checked == true && document.getElementById("chkpartF" + i).checked == false) {
				temp = document.getElementById("chkpartP" + i).value + "," + document.getElementById("chkpartP" + i).checked + "~";
			} else if (document.getElementById("chkpartF" + i).checked == true && document.getElementById("chkpartP" + i).checked == false) {
				temp = document.getElementById("chkpartF" + i).value + "," + document.getElementById("chkpartP" + i).checked + "~";
			}
		} else {
			if (document.getElementById("chkpartP" + i).checked == true && document.getElementById("chkpartF" + i).checked == false) {
				temp += document.getElementById("chkpartP" + i).value + "," + document.getElementById("chkpartP" + i).checked + "~";
			} else if (document.getElementById("chkpartF" + i).checked == true && document.getElementById("chkpartP" + i).checked == false) {
				temp += document.getElementById("chkpartF" + i).value + "," + document.getElementById("chkpartP" + i).checked + "~";
			}
		}
	}

	document.getElementById("landregiondetails").value = "";
	document.getElementById("landregiondetails").value = temp;

	var id = document.getElementById('lgdLBTypeNamehidden').value;
	var temp = id.split(":");
	var id1 = temp[0];
	var id2 = temp[1];
	var id3 = temp[2];

	if (id3 != 'U') {
		for ( var i = 0; i < document.getElementsByName('headQuarterCode').length; i++) {
			if (document.getElementById("chk" + i).checked == true) {
				testdata = true;
			}
		}
	}

	if (id3 == 'U') {
		errors[0] = !validateLBUrbanCorrectCov();
	} else if (id3 == 'P' || id3 == 'T') {
		if (id2 == 'D') {
			errors[1] = !validateLBDistrictCorrectCov();
		} else if (id2 == 'I') {
			errors[2] = !validateLBSubDistrictCorrectCov();
		} else if (id2 == 'V') {

			errors[3] = !validateLBVillageCorrectCovF();
			// errors[4] = !validateHeadqurterVillageCorrectCov();
		}
	}

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

		if (testdata == true) {

			return true;
		} else {
			if (id3 != 'U') {
				alert("Please Select a Head Quarter");
				return false;
			} else {
				$('input:submit').attr("disabled", true);
				return true;
			}
		}
	}

	return false;
}

function validateLBVillageCorrectCovF() {
	var value = true;
	var ddlgdLVillage = document.getElementById('ddLgdLBVillageCAreaDestLUnmapped');
	var availVillageList = document.getElementById('availddLgdLBVillageCAreaSourceLUnmapped');

	if (availVillageList.length == 0) {
		if (ddlgdLVillage != null) {
			if (ddlgdLVillage.options.length <= 0) {
				$("#ddLgdLBVillageCAreaDestLUnmapped_error").show();
				value = false;
			} else if (ddlgdLVillage.selectedIndex == -1) {
				$("#ddLgdLBVillageCAreaDestLUnmapped_error").show();
				value = false;
			}
		}
	}
	return value;
}

function validateLBUrbanCorrectCov() {
	var value = true;
	var ddlgdLBUrban = document.getElementById('ddLgdUrbanLBDistUmappedDest');
	var ddavailUrban = document.getElementById('availddLgdLBInterCAreaSourceLUmappedUrban');
	if (ddavailUrban.length == 0) {
		if (ddlgdLBUrban != null) {
			if (ddlgdLBUrban.options.length <= 0) {
				$("#ddLgdUrbanLBDistUmappedDest_error").show();
				value = false;
			} else if (ddlgdLBUrban.selectedIndex == -1) {
				$("#ddLgdUrbanLBDistUmappedDest_error").show();
				value = false;
			}
		}
	}
	return value;
}

function validateLBDistrictCorrectCov() {
	var value = true;
	var ddlgdLDistrict = document.getElementById('ddLgdLBDistCAreaDestLUmapped');
	var availdistDP = document.getElementById('availddLgdLBDistCAreaSourceLUmapped');

	if (availdistDP.length == 0) {
		if (ddlgdLDistrict != null) {
			if (ddlgdLDistrict.options.length <= 0) {
				$("#ddLgdLBDistCAreaDestLUmapped_error").show();
				value = false;
			} else if (ddlgdLDistrict.selectedIndex == -1) {
				$("#ddLgdLBDistCAreaDestLUmapped_error").show();
				value = false;
			}
		}
	}
	return value;
}

function validateLBSubDistrictCorrectCov() {
	var value = true;
	var ddlgdLSubDistrict = document.getElementById('ddLgdLBInterCAreaDestLUmapped');
	var availsubIP = document.getElementById('availddLgdLBInterCAreaSourceLUmapped');

	if (availsubIP.length == 0) {
		if (ddlgdLSubDistrict != null) {
			if (ddlgdLSubDistrict.options.length <= 0) {
				$("#ddLgdLBInterCAreaDestLUmapped_error").show();
				value = false;
			} else if (ddlgdLSubDistrict.selectedIndex == -1) {
				$("#ddLgdLBInterCAreaDestLUmapped_error").show();
				value = false;
			}
		}
	}
	return value;
}

function validateLBVillageCorrectCov() {
	var value = true;
	var ddlgdLVillage = document.getElementById('ddLgdLBVillageCAreaDestLUnmapped');
	var availVillageList = document.getElementById('availddLgdLBVillageCAreaSourceLUnmapped');

	if (availVillageList.options.length == 0) {
		if (ddlgdLVillage != null) {
			if (ddlgdLVillage.options.length <= 0) {
				$("#ddLgdLBVillageCAreaDestLUnmapped_error").show();
				value = false;
			} else if (ddlgdLVillage.selectedIndex == -1) {
				$("#ddLgdLBVillageCAreaDestLUnmapped_error").show();
				value = false;
			}
		}
	}
	return value;
}

/*
 * function validateHeadqurterVillageCorrectCov() { var ddlgdLVillage =
 * document.getElementById('ddLgdLBVillageCAreaDestLUnmapped'); }
 */
var finalval1 = "";
var urban = "";
function validateModifyCoverageLB() {
	var errors = new Array();
	var error = false;
	var testdata = false;
	var districtlistavailDP = null;
	var districtlistavailFinalDP = null;
	var districtlistavailLengthDP = null;
	var districtlistavailFinalLengthDP = null;
	var subdistrictlistavailIP = null;
	var subdistrictlistavailFinalIP = null;
	var subdistrictlistavailLengthIP = null;
	var subdistrictlistavailFinalLengthIP = null;
	var villagelistavailVP = null;
	var VillagelistavailFinalVP = null;
	var villagelistavailLengthVP = null;
	var VillagelistavailFinalLengthVP = null;
	var subdistrictlistavailUrban = null;
	var subdistrictlistavailFinalUrban = null;
	var subdistrictlistavailLengthUrban = null;
	var subdistrictlistavailFinalLengthUrban = null;
	var s = "";
	var selObj = document.getElementById('ddLgdUrbanLBDistExistDest');
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}
	var selObj1 = document.getElementById('ddLgdUrbanLBSubdistrictListDest');
	for ( var i = 0; i < selObj1.options.length; i++) {
		selObj1.options[i].selected = true;
		urban = 1;
		s = s + "@" + (selObj1.options[i].value);
	}
	var selObj2 = document.getElementById('ddLgdUrbanLBDistUmappedDest');
	for ( var i = 0; i < selObj2.options.length; i++) {
		urban = 1;
		s = s + "@" + (selObj2.options[i].value);
	}

	var selObj3 = document.getElementById('ddLgdLBDistPDestList');
	for ( var i = 0; i < selObj3.options.length; i++) {
		selObj3.options[i].selected = true;
	}
	var selObj4 = document.getElementById('ddLgdLBInterPDestList');
	for ( var i = 0; i < selObj4.options.length; i++) {
		selObj4.options[i].selected = true;
	}
	var selObj5 = document.getElementById('ddLgdLBVillageDestAtVillageCA');
	for ( var i = 0; i < selObj5.options.length; i++) {
		selObj5.options[i].selected = true;
	}
	var selObj6 = document.getElementById('availddLgdLBInterCAreaSourceLUmappedUrban');
	for ( var i = 0; i < selObj6.options.length; i++) {
		selObj6.options[i].selected = true;
	}
	var selObj7 = document.getElementById('availddLgdLBDistCAreaSourceLUmapped');
	for ( var i = 0; i < selObj7.options.length; i++) {
		selObj7.options[i].selected = true;
	}
	var selObj8 = document.getElementById('availddLgdLBSubDistrictSourceLatDCAUmapped');
	for ( var i = 0; i < selObj8.options.length; i++) {
		selObj8.options[i].selected = true;
	}
	var selObj9 = document.getElementById('availddLgdLBVillageSourceLatDCAUmapped');
	for ( var i = 0; i < selObj9.options.length; i++) {
		selObj9.options[i].selected = true;
	}
	var selObj10 = document.getElementById('availddLgdLBInterCAreaSourceLUmapped');
	for ( var i = 0; i < selObj10.options.length; i++) {
		selObj10.options[i].selected = true;
	}
	var selObj11 = document.getElementById('availddLgdLBVillageSourceLatICAUmapped');
	for ( var i = 0; i < selObj11.options.length; i++) {
		selObj11.options[i].selected = true;
	}
	var selObj12 = document.getElementById('availddLgdLBVillageCAreaSourceLUnmapped');
	for ( var i = 0; i < selObj12.options.length; i++) {
		selObj12.options[i].selected = true;
	}
	var selObj13 = document.getElementById('ddLgdLBVillageDestLatICAUmapped');
	for ( var i = 0; i < selObj13.options.length; i++) {
		selObj13.options[i].selected = true;
		s = s + "@" + (selObj13.options[i].value);
	}
	var selObj14 = document.getElementById('ddLgdLBVillageDestLatICA');
	for ( var i = 0; i < selObj14.options.length; i++) {
		selObj14.options[i].selected = true;
		s = s + "@" + (selObj14.options[i].value);
	}
	var selObj15 = document.getElementById('ddLgdLBVillageCAreaDestL');
	for ( var i = 0; i < selObj15.options.length; i++) {
		selObj15.options[i].selected = true;
		s = s + "@" + (selObj15.options[i].value);
	}
	var selObj16 = document.getElementById('ddLgdLBVillageCAreaDestLUnmapped');
	for ( var i = 0; i < selObj16.options.length; i++) {
		selObj16.options[i].selected = true;
		s = s + "@" + (selObj16.options[i].value);
	}
	var selObj17 = document.getElementById('ddLgdLBVillageDestLatDCA');
	for ( var i = 0; i < selObj17.options.length; i++) {
		selObj17.options[i].selected = true;
		s = s + "@" + (selObj17.options[i].value);
	}
	var selObj18 = document.getElementById('ddLgdLBVillageDestLatDCAUmapped');
	for ( var i = 0; i < selObj18.options.length; i++) {
		selObj18.options[i].selected = true;
		s = s + "@" + (selObj18.options[i].value);
	}
	var selObj19 = document.getElementById('ddLgdLBSubDistrictDestLatDCA');
	for ( var i = 0; i < selObj19.options.length; i++) {
		selObj19.options[i].selected = true;
		s = s + "@" + (selObj19.options[i].value);
	}
	var selObj20 = document.getElementById('ddLgdLBDistCAreaDestL');
	for ( var i = 0; i < selObj20.options.length; i++) {
		selObj20.options[i].selected = true;
		s = s + "@" + (selObj20.options[i].value);
	}
	var selObj21 = document.getElementById('ddLgdLBSubDistrictDestLatDCAUmapped');
	for ( var i = 0; i < selObj21.options.length; i++) {
		selObj21.options[i].selected = true;
		s = s + "@" + (selObj21.options[i].value);
	}
	var selObj22 = document.getElementById('ddLgdLBVillageDestLatDCAUmapped');
	for ( var i = 0; i < selObj22.options.length; i++) {
		selObj22.options[i].selected = true;
	}
	var selObj23 = document.getElementById('ddLgdLBDistCAreaDestLUmapped');
	for ( var i = 0; i < selObj23.options.length; i++) {
		selObj23.options[i].selected = true;
		s = s + "@" + (selObj23.options[i].value);
	}
	var selObj24 = document.getElementById('ddLgdLBInterCAreaDestLUmapped');
	for ( var i = 0; i < selObj24.options.length; i++) {
		selObj24.options[i].selected = true;
		s = s + "@" + (selObj24.options[i].value);
	}
	var selObj25 = document.getElementById('ddLgdLBVillageDestLatICAUmapped');
	for ( var i = 0; i < selObj25.options.length; i++) {
		selObj25.options[i].selected = true;
	}
	var selObj26 = document.getElementById('ddLgdLBInterCAreaDestL');
	for ( var i = 0; i < selObj26.options.length; i++) {
		selObj26.options[i].selected = true;
		s = s + "@" + (selObj26.options[i].value);
	}
	var selObj27 = document.getElementById('ddLgdLBVillageDestLatICA');
	for ( var i = 0; i < selObj27.options.length; i++) {
		selObj27.options[i].selected = true;
	}
	var selObj28 = document.getElementById('ddLgdLBDistPDestListhidden');
	for ( var i = 0; i < selObj28.options.length; i++) {
		selObj28.options[i].selected = true;
	}
	var selObj29 = document.getElementById('ddLgdInterSubDestListhidden');
	for ( var i = 0; i < selObj29.options.length; i++) {
		selObj29.options[i].selected = true;
	}
	var selObj30 = document.getElementById('availddLgdLBDistCAreaSourceLUmappedHidden');
	for ( var i = 0; i < selObj30.options.length; i++) {
		selObj30.options[i].selected = true;
	}
	var selObj31 = document.getElementById('availddLgdLBInterCAreaSourceLUmappedHidden');
	for ( var i = 0; i < selObj31.options.length; i++) {
		selObj31.options[i].selected = true;
	}
	var selObj32 = document.getElementById('availddLgdLBVillageCAreaSourceLUnmappedHidden');
	for ( var i = 0; i < selObj32.options.length; i++) {
		selObj32.options[i].selected = true;
	}
	var selObj33 = document.getElementById('availddLgdLBInterCAreaSourceLUmappedUrbanHidden');
	for ( var i = 0; i < selObj33.options.length; i++) {
		selObj33.options[i].selected = true;
	}

	/*
	 * manage pri coverage (duplicate coverage is comming resolved) author
	 * Ashish Dhupia date 07/08/2014
	 */
	var eid = "";
	var e = s.split("@");
	if (urban == 1) {
		for ( var k = 1; k < e.length; k++) {
			eid = e[k];
			if (eid.indexOf(":") > -1) {
				var p = eid.split(":");
				eid = p[1];
			}
			var l = eid.split("_");
			var type = "";
			var finalval = "";
			type = l[2];
			finalval = l[0] + "_" + type;
			finalval1 = finalval1 + finalval + "@";
		}

	} else {
		for ( var k = 1; k < e.length; k++) {
			eid = e[k];
			if (eid.indexOf(":") > -1) {
				var p = eid.split(":");
				eid = p[1];
			}
			var l = eid.split("_");
			var type = l[2];
			var finalval = l[0] + "_" + type;
			finalval1 = finalval1 + finalval + "@";
		}
	}

	var status = alreadyCoveredCoverageData();
	if (status) {
	} else {
		return false;
	}
	/*
	 * manage pri coverage (duplicate coverage is comming resolved) author
	 * Ashish Dhupia date 07/08/2014
	 */

	var govtOrderStatus = document.getElementById('hdngovtOrderConfig').value;
	var id = document.getElementById('lgdLBTypeNamehidden').value;

	var temp = id.split(":");
	var id1 = temp[0];
	var id2 = temp[1];
	var id3 = temp[2];

	errors[0] = !validateOrderNo();
	errors[1] = !validateOrdeDate();
	errors[2] = !validateEffecDate();
	// errors[3] = !validateGazPubDate();
	// errors[4] = !validateSFile1();

	if (id3 != 'U') {
		for ( var i = 0; i < document.getElementsByName('headQuarterCode').length; i++) {
			if (document.getElementById("chk" + i).checked == true) {
				testdata = true;
			}
		}
	}

	if (id3 == 'P' || id3 == 'T') {
		if (id2 == 'D') {
			districtlistavailDP = document.getElementById('availddLgdLBDistCAreaSourceLUmappedHidden');
			districtlistavailFinalDP = document.getElementById('availddLgdLBDistCAreaSourceLUmapped');
			districtlistavailLengthDP = districtlistavailDP.options.length;
			districtlistavailFinalLengthDP = districtlistavailFinalDP.options.length;
		}
		if (id2 == 'I') {
			subdistrictlistavailIP = document.getElementById('availddLgdLBInterCAreaSourceLUmappedHidden');
			subdistrictlistavailFinalIP = document.getElementById('availddLgdLBInterCAreaSourceLUmapped');
			subdistrictlistavailLengthIP = subdistrictlistavailIP.options.length;
			subdistrictlistavailFinalLengthIP = subdistrictlistavailFinalIP.options.length;
		}
		if (id2 == 'V') {
			villagelistavailVP = document.getElementById('availddLgdLBVillageCAreaSourceLUnmapped');
			VillagelistavailFinalVP = document.getElementById('availddLgdLBVillageCAreaSourceLUnmappedHidden');
			villagelistavailLengthVP = villagelistavailVP.options.length;
			VillagelistavailFinalLengthVP = VillagelistavailFinalVP.options.length;
		}
	} else if (id3 == 'U') {
		subdistrictlistavailUrban = document.getElementById('availddLgdLBInterCAreaSourceLUmappedUrbanHidden');
		subdistrictlistavailFinalUrban = document.getElementById('availddLgdLBInterCAreaSourceLUmappedUrbanHidden');
		subdistrictlistavailLengthUrban = subdistrictlistavailUrban.options.length;
		subdistrictlistavailFinalLengthUrban = subdistrictlistavailFinalUrban.options.length;
	}

	if (govtOrderStatus == 'govtOrderUpload') {
		errors[3] = !validateGazPubDate();
		errors[4] = !validateSFile1();
	} else if (govtOrderStatus == 'govtOrderGenerate') {
		errors[5] = !validateFileTemplate();
	}

	var chkLgdLBExistMappedChk = document.getElementById('chkLgdLBExistChk');
	var chkLgdLBExistUnmappedChk = document.getElementById('chkLgdLBUnmapped');

	if (id3 == 'P' || id3 == 'T') {
		if (id2 == 'D') {
			if (districtlistavailLengthDP == districtlistavailFinalLengthDP) {
				if (chkLgdLBExistMappedChk.checked == false) {
					if (chkLgdLBExistUnmappedChk.checked == false) {
						alert("Please either remove or insert new Land Region");
						return false;
					}
				}
			}
		} else if (id2 == 'I') {
			if (subdistrictlistavailLengthIP == subdistrictlistavailFinalLengthIP) {
				if (chkLgdLBExistMappedChk.checked == false) {
					if (chkLgdLBExistUnmappedChk.checked == false) {
						alert("Please either remove or insert new Land Region");
						return false;
					}
				}
			}
		} else if (id2 == 'V') {

			if (villagelistavailLengthVP == VillagelistavailFinalLengthVP) {
				if (chkLgdLBExistMappedChk.checked == false) {
					if (chkLgdLBExistUnmappedChk.checked == false) {
						alert("Please either remove or insert new Land Region");
						return false;
					}
				}
			}
		}
	} else if (id3 == 'U') {
		if (subdistrictlistavailLengthUrban == subdistrictlistavailFinalLengthUrban) {
			if (chkLgdLBExistMappedChk.checked == false) {
				if (chkLgdLBExistUnmappedChk.checked == false) {
					alert("Please either remove or insert new Land Region");
					return false;
				}
			}
		}
	}

	if (id3 == 'U') {
		if (chkLgdLBExistMappedChk.checked) {
			errors[6] = !validateLBUrban();
			errors[7] = !validateSubDistUrban();
		}
	}

	/*
	 * else if (id3 == 'P' || id3 == 'T') { if (id2 == 'D') { if
	 * (chkLgdLBExistMappedChk.checked) { errors[8] = !validateLBDistrict();
	 * errors[9] = !validateDistrictDPChCov(); } } else if (id2 == 'I') { if
	 * (chkLgdLBExistMappedChk.checked) { errors[10] = !validateLBSubDistrict();
	 * errors[11] = !validateSubDistrictIPChCov(); } } else if (id2 == 'V') { if
	 * (chkLgdLBExistMappedChk.checked) { errors[12] = !validateLBVillage();
	 * errors[13] = !validateVillageVPChCov(); } } }
	 */

	if (chkLgdLBExistUnmappedChk.checked) {
		if (id2 == 'D') {
			errors[8] = !validateLBUnmappedDistrictAtDP();
		}
		if (id2 == 'I') {
			errors[9] = !validateLBUnmappedSubdistrictAtIP();
		}
		if (id2 == 'V') {
			errors[10] = !validateLBUnmappedVillageAtVP();
		}
	}
	if (chkLgdLBExistUnmappedChk.checked) {
		if (id3 == 'U') {
			errors[11] = !validateLBUnmappedSubDistrictUrban();
		}
	}

	for ( var i = 0; i < 12; i++) {

		if (errors[i] == true) {
			error = true;
			break;
		}
	}

	if (error == true) {
		showClientSideError();
		return false;
	} else {
		if (testdata == true) {
			return true;
		} else {
			if (id3 != 'U') {
				alert("Please Select a Head Quarter");
				return false;
			} else {
				return true;
			}
		}
	}

	return false;
}

/*
 * manage pri coverage (duplicate coverage is comming resolved) author Ashish
 * Dhupia date 07/08/2014
 */
function alreadyCoveredCoverageData() {
	var abc = "";
	var temp;
	if ($('#availddLgdLBVillageCAreaSourceLUnmapped').is(":visible")) {
		var selObj = document.getElementById('availddLgdLBVillageCAreaSourceLUnmapped');
		for ( var i = 0; i < selObj.options.length; i++) {
			temp = selObj.options[i].value;
			var test = temp.split("_");
			var id = test[0] + "_V";
			// alert("id:"+id);
			abc = abc + id + "@";
		}
	}

	var temp1;
	if ($('#availddLgdLBInterCAreaSourceLUmapped').is(":visible")) {
		var selObj1 = document.getElementById('availddLgdLBInterCAreaSourceLUmapped');
		for ( var i = 0; i < selObj1.options.length; i++) {
			temp1 = selObj1.options[i].value;
			var test1 = temp1.split("_");
			var id1 = test1[0] + "_T";
			abc = abc + id1 + "@";
		}
	}
	var temp2;
	if ($('#availddLgdLBVillageSourceLatICAUmapped').is(":visible")) {
		var selObj2 = document.getElementById('availddLgdLBVillageSourceLatICAUmapped');
		for ( var i = 0; i < selObj2.options.length; i++) {
			temp2 = selObj2.options[i].value;
			var test2 = temp2.split("_");
			var id2 = test2[0] + "_V";
			abc = abc + id2 + "@";
		}
	}
	var temp3;
	if ($('#availddLgdLBDistCAreaSourceLUmapped').is(":visible")) {
		var selObj3 = document.getElementById('availddLgdLBDistCAreaSourceLUmapped');
		for ( var i = 0; i < selObj3.options.length; i++) {
			temp3 = selObj3.options[i].value;
			var test3 = temp3.split("_");
			var id3 = test3[0] + "_D";
			abc = abc + id3 + "@";
		}
	}
	var temp4;
	if ($('#availddLgdLBSubDistrictSourceLatDCAUmapped').is(":visible")) {
		var selObj4 = document.getElementById('availddLgdLBSubDistrictSourceLatDCAUmapped');
		for ( var i = 0; i < selObj4.options.length; i++) {
			temp4 = selObj4.options[i].value;
			var test4 = temp4.split("_");
			var id4 = test4[0] + "_T";
			abc = abc + id4 + "@";
		}
	}
	var temp5;
	if ($('#availddLgdLBVillageSourceLatDCAUmapped').is(":visible")) {
		var selObj5 = document.getElementById('availddLgdLBVillageSourceLatDCAUmapped');
		for ( var i = 0; i < selObj5.options.length; i++) {
			temp5 = selObj5.options[i].value;
			var test5 = temp5.split("_");
			var id5 = test5[0] + "_V";
			abc = abc + id5 + "@";
		}
	}

	var temp6;
	if ($('#availddLgdLBInterCAreaSourceLUmappedUrban').is(":visible")) {
		var selObj6 = document.getElementById('availddLgdLBInterCAreaSourceLUmappedUrban');
		for ( var i = 0; i < selObj6.options.length; i++) {
			temp6 = selObj6.options[i].value;
			var test6 = temp6.split("_");
			var id6 = test6[0] + "_T";
			abc = abc + id6 + "@";
		}
	}

	var alredycovered = abc.split("@");
	var alreadycoveredsplit = finalval1.split("@");
	for ( var i = 0; i < alredycovered.length - 1; i++) {
		var alreadycoveredfinal = alredycovered[i];
		for ( var j = 0; j < alreadycoveredsplit.length; j++) {
			var checkval = alreadycoveredsplit[j];
			if (checkval == alreadycoveredfinal) {
				alert("Selected area has already mapped with this local body");
				finalval1 = "";
				return false;
			} else {
			}

		}
	}
	return true;
}
/*
 * end of manage pri coverage (duplicate coverage is comming resolved) author
 * Ashish Dhupia date 07/08/2014
 */

function validateModifyCoverageLBDisturbed() {

	var errors = new Array();
	var error = false;
	var testdata = false;
	var districtlistavailDP = null;
	var districtlistavailFinalDP = null;
	var districtlistavailLengthDP = null;
	var districtlistavailFinalLengthDP = null;
	var subdistrictlistavailIP = null;
	var subdistrictlistavailFinalIP = null;
	var subdistrictlistavailLengthIP = null;
	var subdistrictlistavailFinalLengthIP = null;
	var villagelistavailVP = null;
	var VillagelistavailFinalVP = null;
	var villagelistavailLengthVP = null;
	var VillagelistavailFinalLengthVP = null;
	var subdistrictlistavailUrban = null;
	var subdistrictlistavailFinalUrban = null;
	var subdistrictlistavailLengthUrban = null;
	var subdistrictlistavailFinalLengthUrban = null;

	var selObj = document.getElementById('ddLgdUrbanLBDistExistDest');
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}
	var selObj1 = document.getElementById('ddLgdUrbanLBSubdistrictListDest');
	for ( var i = 0; i < selObj1.options.length; i++) {
		selObj1.options[i].selected = true;
	}
	var selObj2 = document.getElementById('ddLgdUrbanLBDistUmappedDest');
	for ( var i = 0; i < selObj2.options.length; i++) {
		selObj2.options[i].selected = true;
	}

	var selObj3 = document.getElementById('ddLgdLBDistPDestList');
	for ( var i = 0; i < selObj3.options.length; i++) {
		selObj3.options[i].selected = true;
	}
	var selObj4 = document.getElementById('ddLgdLBInterPDestList');
	for ( var i = 0; i < selObj4.options.length; i++) {
		selObj4.options[i].selected = true;
	}
	var selObj5 = document.getElementById('ddLgdLBVillageDestAtVillageCA');
	for ( var i = 0; i < selObj5.options.length; i++) {
		selObj5.options[i].selected = true;
	}
	var selObj6 = document.getElementById('availddLgdLBInterCAreaSourceLUmappedUrban');
	for ( var i = 0; i < selObj6.options.length; i++) {
		selObj6.options[i].selected = true;
	}
	var selObj7 = document.getElementById('availddLgdLBDistCAreaSourceLUmapped');
	for ( var i = 0; i < selObj7.options.length; i++) {
		selObj7.options[i].selected = true;
	}
	var selObj8 = document.getElementById('availddLgdLBSubDistrictSourceLatDCAUmapped');
	for ( var i = 0; i < selObj8.options.length; i++) {
		selObj8.options[i].selected = true;
	}
	var selObj9 = document.getElementById('availddLgdLBVillageSourceLatDCAUmapped');
	for ( var i = 0; i < selObj9.options.length; i++) {
		selObj9.options[i].selected = true;
	}
	var selObj10 = document.getElementById('availddLgdLBInterCAreaSourceLUmapped');
	for ( var i = 0; i < selObj10.options.length; i++) {
		selObj10.options[i].selected = true;
	}
	var selObj11 = document.getElementById('availddLgdLBVillageSourceLatICAUmapped');
	for ( var i = 0; i < selObj11.options.length; i++) {
		selObj11.options[i].selected = true;
	}
	var selObj12 = document.getElementById('availddLgdLBVillageCAreaSourceLUnmapped');
	for ( var i = 0; i < selObj12.options.length; i++) {
		selObj12.options[i].selected = true;
	}
	var selObj13 = document.getElementById('ddLgdLBVillageDestLatICAUmapped');
	for ( var i = 0; i < selObj13.options.length; i++) {
		selObj13.options[i].selected = true;
	}
	var selObj14 = document.getElementById('ddLgdLBVillageDestLatICA');
	for ( var i = 0; i < selObj14.options.length; i++) {
		selObj14.options[i].selected = true;
	}
	var selObj15 = document.getElementById('ddLgdLBVillageCAreaDestL');
	for ( var i = 0; i < selObj15.options.length; i++) {
		selObj15.options[i].selected = true;
	}
	var selObj16 = document.getElementById('ddLgdLBVillageCAreaDestLUnmapped');
	for ( var i = 0; i < selObj16.options.length; i++) {
		selObj16.options[i].selected = true;
	}
	var selObj17 = document.getElementById('ddLgdLBVillageDestLatDCA');
	for ( var i = 0; i < selObj17.options.length; i++) {
		selObj17.options[i].selected = true;
	}
	var selObj18 = document.getElementById('ddLgdLBVillageDestLatDCAUmapped');
	for ( var i = 0; i < selObj18.options.length; i++) {
		selObj18.options[i].selected = true;
	}
	var selObj19 = document.getElementById('ddLgdLBSubDistrictDestLatDCA');
	for ( var i = 0; i < selObj19.options.length; i++) {
		selObj19.options[i].selected = true;
	}
	var selObj20 = document.getElementById('ddLgdLBDistCAreaDestL');
	for ( var i = 0; i < selObj20.options.length; i++) {
		selObj20.options[i].selected = true;
	}
	var selObj21 = document.getElementById('ddLgdLBSubDistrictDestLatDCAUmapped');
	for ( var i = 0; i < selObj21.options.length; i++) {
		selObj21.options[i].selected = true;
	}
	var selObj22 = document.getElementById('ddLgdLBVillageDestLatDCAUmapped');
	for ( var i = 0; i < selObj22.options.length; i++) {
		selObj22.options[i].selected = true;
	}
	var selObj23 = document.getElementById('ddLgdLBDistCAreaDestLUmapped');
	for ( var i = 0; i < selObj23.options.length; i++) {
		selObj23.options[i].selected = true;
	}
	var selObj24 = document.getElementById('ddLgdLBInterCAreaDestLUmapped');
	for ( var i = 0; i < selObj24.options.length; i++) {
		selObj24.options[i].selected = true;
	}
	var selObj25 = document.getElementById('ddLgdLBVillageDestLatICAUmapped');
	for ( var i = 0; i < selObj25.options.length; i++) {
		selObj25.options[i].selected = true;
	}
	var selObj26 = document.getElementById('ddLgdLBInterCAreaDestL');
	for ( var i = 0; i < selObj26.options.length; i++) {
		selObj26.options[i].selected = true;
	}
	var selObj27 = document.getElementById('ddLgdLBVillageDestLatICA');
	for ( var i = 0; i < selObj27.options.length; i++) {
		selObj27.options[i].selected = true;
	}
	var selObj28 = document.getElementById('ddLgdLBDistPDestListhidden');
	for ( var i = 0; i < selObj28.options.length; i++) {
		selObj28.options[i].selected = true;
	}
	var selObj29 = document.getElementById('ddLgdInterSubDestListhidden');
	for ( var i = 0; i < selObj29.options.length; i++) {
		selObj29.options[i].selected = true;
	}
	var selObj30 = document.getElementById('availddLgdLBDistCAreaSourceLUmappedHidden');
	for ( var i = 0; i < selObj30.options.length; i++) {
		selObj30.options[i].selected = true;
	}
	var selObj31 = document.getElementById('availddLgdLBInterCAreaSourceLUmappedHidden');
	for ( var i = 0; i < selObj31.options.length; i++) {
		selObj31.options[i].selected = true;
	}
	var selObj32 = document.getElementById('availddLgdLBVillageCAreaSourceLUnmappedHidden');
	for ( var i = 0; i < selObj32.options.length; i++) {
		selObj32.options[i].selected = true;
	}
	var selObj33 = document.getElementById('availddLgdLBInterCAreaSourceLUmappedUrbanHidden');
	for ( var i = 0; i < selObj33.options.length; i++) {
		selObj33.options[i].selected = true;
	}

	// var govtOrderStatus =
	// document.getElementById('hdngovtOrderConfig').value;
	var id = document.getElementById('lgdLBTypeNamehidden').value;

	var temp = id.split(":");
	var id1 = temp[0];
	var id2 = temp[1];
	var id3 = temp[2];

	// errors[0] = !validateOrderNo();
	// errors[1] = !validateOrdeDate();
	// errors[2] = !validateEffecDate();
	// errors[3] = !validateGazPubDate();
	// errors[4] = !validateSFile1();

	if (id3 != 'U') {
		for ( var i = 0; i < document.getElementsByName('headQuarterCode').length; i++) {
			if (document.getElementById("chk" + i).checked == true) {
				testdata = true;
			}
		}
	}

	if (id3 == 'P' || id3 == 'T') {
		if (id2 == 'D') {
			districtlistavailDP = document.getElementById('availddLgdLBDistCAreaSourceLUmappedHidden');
			districtlistavailFinalDP = document.getElementById('availddLgdLBDistCAreaSourceLUmapped');
			districtlistavailLengthDP = districtlistavailDP.options.length;
			districtlistavailFinalLengthDP = districtlistavailFinalDP.options.length;
		}
		if (id2 == 'I') {
			subdistrictlistavailIP = document.getElementById('availddLgdLBInterCAreaSourceLUmappedHidden');
			subdistrictlistavailFinalIP = document.getElementById('availddLgdLBInterCAreaSourceLUmapped');
			subdistrictlistavailLengthIP = subdistrictlistavailIP.options.length;
			subdistrictlistavailFinalLengthIP = subdistrictlistavailFinalIP.options.length;
		}
		if (id2 == 'V') {
			villagelistavailVP = document.getElementById('availddLgdLBVillageCAreaSourceLUnmapped');
			VillagelistavailFinalVP = document.getElementById('availddLgdLBVillageCAreaSourceLUnmappedHidden');
			villagelistavailLengthVP = villagelistavailVP.options.length;
			VillagelistavailFinalLengthVP = VillagelistavailFinalVP.options.length;
		}
	} else if (id3 == 'U') {
		subdistrictlistavailUrban = document.getElementById('availddLgdLBInterCAreaSourceLUmappedUrban');
		subdistrictlistavailFinalUrban = document.getElementById('availddLgdLBInterCAreaSourceLUmappedUrbanHidden');
		subdistrictlistavailLengthUrban = subdistrictlistavailUrban.options.length;
		subdistrictlistavailFinalLengthUrban = subdistrictlistavailFinalUrban.options.length;
	}

	/*
	 * if (govtOrderStatus == 'govtOrderUpload') { errors[3] =
	 * !validateGazPubDate(); errors[4] = !validateSFile1(); } else if
	 * (govtOrderStatus == 'govtOrderGenerate') { errors[5] =
	 * !validateFileTemplate(); }
	 */

	var chkLgdLBExistMappedChk = document.getElementById('chkLgdLBExistChk');
	var chkLgdLBExistUnmappedChk = document.getElementById('chkLgdLBUnmapped');

	if (id3 == 'P' || id3 == 'T') {
		if (id2 == 'D') {
			if (districtlistavailLengthDP == districtlistavailFinalLengthDP) {
				if (chkLgdLBExistMappedChk.checked == false) {
					if (chkLgdLBExistUnmappedChk.checked == false) {
						alert("Please either remove or insert new Land Region");
						return false;
					}
				}
			}
		} else if (id2 == 'I') {
			if (subdistrictlistavailLengthIP == subdistrictlistavailFinalLengthIP) {
				if (chkLgdLBExistMappedChk.checked == false) {
					if (chkLgdLBExistUnmappedChk.checked == false) {
						alert("Please either remove or insert new Land Region");
						return false;
					}
				}
			}
		} else if (id2 == 'V') {

			if (villagelistavailLengthVP == VillagelistavailFinalLengthVP) {
				if (chkLgdLBExistMappedChk.checked == false) {
					if (chkLgdLBExistUnmappedChk.checked == false) {
						alert("Please either remove or insert new Land Region");
						return false;
					}
				}
			}
		}
	} else if (id3 == 'U') {
		if (subdistrictlistavailLengthUrban == subdistrictlistavailFinalLengthUrban) {
			if (chkLgdLBExistMappedChk.checked == false) {
				if (chkLgdLBExistUnmappedChk.checked == false) {
					alert("Please either remove or insert new Land Region");
					return false;
				}
			}
		}
	}

	if (id3 == 'U') {
		if (chkLgdLBExistMappedChk.checked) {
			errors[0] = !validateLBUrban();
			errors[1] = !validateSubDistUrban();
		}
	} else if (id3 == 'P' || id3 == 'T') {
		if (id2 == 'D') {
			if (chkLgdLBExistMappedChk.checked) {
				errors[2] = !validateLBDistrict();
				errors[3] = !validateDistrictDPChCov();
			}
		} else if (id2 == 'I') {
			if (chkLgdLBExistMappedChk.checked) {
				errors[4] = !validateLBSubDistrict();
				errors[5] = !validateSubDistrictIPChCov();
			}
		} else if (id2 == 'V') {
			if (chkLgdLBExistMappedChk.checked) {
				errors[6] = !validateLBVillage();
				errors[7] = !validateVillageVPChCov();
			}
		}
	}

	if (chkLgdLBExistUnmappedChk.checked) {
		if (id2 == 'D') {
			errors[8] = !validateLBUnmappedDistrictAtDP();
		}
		if (id2 == 'I') {
			errors[8] = !validateLBUnmappedSubdistrictAtIP();
		}
		if (id2 == 'V') {
			errors[9] = !validateLBUnmappedVillageAtVP();
		}
	}
	if (chkLgdLBExistUnmappedChk.checked) {
		if (id3 == 'U') {
			errors[9] = !validateLBUnmappedSubDistrictUrban();
		}
	}

	for ( var i = 0; i < 10; i++) {

		if (errors[i] == true) {
			error = true;
			break;
		}
	}

	if (error == true) {
		showClientSideError();
		return false;
	} else {
		if (testdata == true) {
			return true;
		} else {
			if (id3 != 'U') {
				alert("Please Select a Head Quarter");
				return false;
			} else {
				return true;
			}
		}
	}

	return false;
}

function validateLBUnmappedDistrictAtDP() {

	var value = true;
	var ddlgdLBUnmappedDistrictAtDP = document.getElementById('ddLgdLBDistCAreaDestLUmapped');
	if (document.getElementById('divLgdLBDistCAreaUnmapped').style.display == 'block') {
		if (ddlgdLBUnmappedDistrictAtDP.options.length <= 0) {
			$("#ddLgdLBDistCAreaDestLUmapped_error").show();
			value = false;
		} else if (ddlgdLBUnmappedDistrictAtDP.selectedIndex == -1) {
			$("#ddLgdLBDistCAreaDestLUmapped_error").show();
			value = false;
		}

	}
	return value;
}

function validateLBUnmappedSubdistrictAtIP() {
	var value = true;
	var ddlgdLBUnmappedSubDistrictAtIP = document.getElementById('ddLgdLBInterCAreaDestLUmapped');
	if (document.getElementById('divLgdLBInterCAreaUnmapped').style.display == 'block') {
		if (ddlgdLBUnmappedSubDistrictAtIP.options.length <= 0) {
			$("#ddLgdLBInterCAreaDestLUmapped_error").show();
			value = false;
		} else if (ddlgdLBUnmappedSubDistrictAtIP.selectedIndex == -1) {
			$("#ddLgdLBInterCAreaDestLUmapped_error").show();
			value = false;
		}

	}
	return value;

}

function validateLBUnmappedVillageAtVP() {

	var value = true;
	var ddlgdLBUnmappedVillageAtVP = document.getElementById('ddLgdLBVillageCAreaDestLUnmapped');
	if (document.getElementById('divLgdLBVillageCAreaUnmapped').style.display == 'block') {
		if (ddlgdLBUnmappedVillageAtVP.options.length <= 0) {
			$("#ddLgdLBVillageCAreaDestLUnmapped_error").show();
			value = false;
		} else if (ddlgdLBUnmappedVillageAtVP.selectedIndex == -1) {
			$("#ddLgdLBVillageCAreaDestLUnmapped_error").show();
			value = false;
		}

	}
	return value;
}

function validateLBUnmappedSubDistrictUrban() {
	var value = true;
	var ddlgdLBUnmappedSubDistrictAtUrban = document.getElementById('ddLgdUrbanLBDistUmappedDest');
	if (document.getElementById('divLgdLBUrbanUnmapped').style.display == 'block') {
		if (ddlgdLBUnmappedSubDistrictAtUrban.options.length <= 0) {
			$("#ddLgdUrbanLBDistUmappedDest_error").show();
			value = false;
		} else if (ddlgdLBUnmappedSubDistrictAtUrban.selectedIndex == -1) {
			$("#ddLgdUrbanLBDistUmappedDest_error").show();
			value = false;
		}

	}
	return value;
}

function validateSubDistUrban() {

	var value = true;
	var ddlgdLBUrban = document.getElementById('ddLgdUrbanLBSubdistrictListDest');

	if (ddlgdLBUrban != null) {
		if (ddlgdLBUrban.options.length <= 0) {
			$("#ddLgdUrbanLBSubdistrictListDest_error").show();
			value = false;
		} else if (ddlgdLBUrban.selectedIndex == -1) {
			$("#ddLgdUrbanLBSubdistrictListDest_error").show();
			value = false;
		}
	}
	return value;
}

function validateDistrictDPChCov() {
	var value = true;
	var ddlgdDistrictDP = document.getElementById('ddLgdLBDistCAreaDestL');

	if (document.getElementById('divLgdLBDistCArea').style.display == 'block') {

		if (ddlgdDistrictDP.options.length <= 0) {
			$("#ddLgdLBDistCAreaDestL_error").show();
			value = false;
		} else if (ddlgdDistrictDP.selectedIndex == -1) {
			$("#ddLgdLBDistCAreaDestL_error").show();
			value = false;
		}
	}
	return value;
}

function validateSubDistrictIPChCov() {
	// alert("asasaa");
	var value = true;
	var ddlgdLBSubDistAtInter = document.getElementById('ddLgdLBInterCAreaDestL');
	if (document.getElementById('divLgdLBInterCArea').style.display == 'block') {
		// alert("yessss");
		if (ddlgdLBSubDistAtInter.options.length <= 0) {
			$("#ddLgdLBInterCAreaDestL_error").show();
			value = false;
		} else if (ddlgdLBSubDistAtInter.selectedIndex == -1) {
			$("#ddLgdLBInterCAreaDestL_error").show();
			value = false;
		}
	}
	return value;
}

function validateVillageVPChCov() {
	var value = true;
	var ddlgdLBDistAtVill = document.getElementById('ddLgdLBVillageCAreaDestL');
	if (document.getElementById('divLgdLBVillageCArea').style.display == 'block') {
		if (ddlgdLBDistAtVill.options.length <= 0) {
			$("#ddLgdLBVillageCAreaDestL_error").show();
			value = false;
		} else if (ddlgdLBDistAtVill.selectedIndex == -1) {
			$("#ddLgdLBVillageCAreaDestL_error").show();
			value = false;
		}
	}
	return value;
}

function validateLBUrban() {
	var value = true;
	var ddlgdLBUrban = document.getElementById('ddLgdUrbanLBDistExistDest');

	if (ddlgdLBUrban != null) {
		if (ddlgdLBUrban.options.length <= 0) {
			$("#ddLgdUrbanLBDistExistDest_error").show();
			$("#ddLgdUrbanLBDistExistDest1_error").hide();
			value = false;
		}

		else if (ddlgdLBUrban.selectedIndex == -1) {
			$("#ddLgdUrbanLBDistExistDest_error").show();
			$("#ddLgdUrbanLBDistExistDest1_error").hide();
			value = false;
		}
		if (checkforFull(ddlgdLBUrban)) {
			$("#ddLgdUrbanLBDistExistDest1_error").show();
			// $("#ddLgdUrbanLBDistExistDest_error").hide();
			value = false;
		} else {
			$("#ddLgdUrbanLBDistExistDest1_error").hide();
			// $("#ddLgdUrbanLBDistExistDest_error").hide();
		}
	}
	return value;
}

function validateDistrictPanchayatList() {
	var value = true;
	var ddLgdLBDistPDestList = document.getElementById('ddLgdLBDistPDestList');
	if (document.getElementById('divLgdLBDistCArea').style.display == 'block') {

		if (ddLgdLBDistPDestList.options.length <= 0) {
			$("#ddLgdLBDistPDestList_error").show();
			value = false;
		} else if (ddLgdLBDistPDestList.selectedIndex == -1) {
			$("#ddLgdLBDistPDestList_error").show();
			value = false;
		}
		if (ddLgdLBDistPDestList.options.length == 1) {
			if (checkforOnlyFull(ddLgdLBDistPDestList)) {
				$("#ddLgdLBDistPDestList1_error").show();
				value = false;
			} else {
				$("#ddLgdLBSubDistrictDestLatDCA_error").hide();
			}
		} else {
			$("#ddLgdLBSubDistrictDestLatDCA_error").hide();
		}
	}
	return value;
}

function validateLBDistrict() {
	var value = true;
	var ddlgdLBDistrict = document.getElementById('ddLgdLBDistPDestList');

	if (document.getElementById('divLgdLBDistCArea').style.display == 'block') {

		if (ddlgdLBDistrict.options.length <= 0) {
			$("#ddLgdLBDistPDestList_error").show();
			value = false;
		} else if (ddlgdLBDistrict.selectedIndex == -1) {
			$("#ddLgdLBDistPDestList_error").show();
			value = false;
		}
		if (ddlgdLBDistrict.options.length == 1) {
			if (checkforOnlyFull(ddlgdLBDistrict)) {
				$("#ddLgdLBDistPDestList_error").show();
				value = false;
			} else {
				$("#ddLgdLBDistPDestList1_error").hide();
			}
		} else {
			$("#ddLgdLBDistPDestList1_error").hide();
		}
	}
	return value;
}

/*
 * function validateLBDistrict() { var value = true; var ddlgdLBDistrict =
 * document.getElementById('ddLgdLBDistPDestList');
 * 
 * if (ddlgdLBDistrict != null) { if (ddlgdLBDistrict.options.length <= 0) {
 * $("#ddLgdLBDistPDestList_error").show();
 * $("#ddLgdLBDistPDestList1_error").hide(); value = false; }
 * 
 * else if (ddlgdLBDistrict.selectedIndex == -1) {
 * $("#ddLgdLBDistPDestList_error").show();
 * $("#ddLgdLBDistPDestList1_error").hide(); value = false; } if
 * (checkforFull(ddlgdLBDistrict)) { $("#ddLgdLBDistPDestList1_error").show(); //
 * $("#ddLgdUrbanLBDistExistDest_error").hide(); value = false; } else {
 * $("#ddLgdLBDistPDestList1_error").hide(); //
 * $("#ddLgdUrbanLBDistExistDest_error").hide(); } } return value; }
 */

function validateLBSubDistrict() {
	var value = true;
	var ddlgdLBDistAtInter = document.getElementById('ddLgdLBInterPDestList');
	if (document.getElementById('divLgdLBInterCArea').style.display == 'block') {
		if (ddlgdLBDistAtInter.options.length <= 0) {
			$("#ddLgdLBInterPDestList_error").show();
			value = false;
		}

		else if (ddlgdLBDistAtInter.selectedIndex == -1) {
			$("#ddLgdLBInterPDestList_error").show();
			value = false;
		}
		if (ddlgdLBDistAtInter.options.length == 1) {
			if (checkforOnlyFull(ddlgdLBDistAtInter)) {
				$("#ddLgdLBInterPDestList_error").show();
				value = false;
			} else {
				$("#ddLgdLBInterPDestList_error").hide();
			}
		}
	}

	return value;
}

/*
 * function validateLBSubDistrict() { var value = true; var ddlgdLBSubDistrict =
 * document.getElementById('ddLgdLBInterPDestList');
 * 
 * if (ddlgdLBSubDistrict != null) { if (ddlgdLBSubDistrict.options.length <= 0) {
 * $("#ddLgdLBInterPDestList_error").show();
 * $("#ddLgdLBInterPDestList1_error").hide(); value = false; }
 * 
 * else if (ddlgdLBSubDistrict.selectedIndex == -1) {
 * $("#ddLgdLBInterPDestList_error").show();
 * $("#ddLgdLBInterPDestList1_error").hide(); value = false; } if
 * (checkforFull(ddlgdLBSubDistrict)) {
 * $("#ddLgdLBInterPDestList1_error").show(); //
 * $("#ddLgdUrbanLBDistExistDest_error").hide(); value = false; } else {
 * $("#ddLgdLBInterPDestList1_error").hide(); //
 * $("#ddLgdUrbanLBDistExistDest_error").hide(); } } return value; }
 */

function validateLBVillage() {
	var value = true;
	var ddlgdLBDistAtVill = document.getElementById('ddLgdLBVillageDestAtVillageCA');
	if (document.getElementById('divLgdLBVillageCArea').style.display == 'block') {
		if (ddlgdLBDistAtVill.options.length <= 0) {
			$("#ddLgdLBVillageDestAtVillageCA_error").show();
			value = false;
		} else if (ddlgdLBDistAtVill.selectedIndex == -1) {
			$("#ddLgdLBVillageDestAtVillageCA_error").show();
			value = false;
		}

		if (ddlgdLBDistAtVill.options.length == 1) {
			if (checkforOnlyFull(ddlgdLBDistAtVill)) {
				$("#ddLgdLBVillageDestAtVillageCA_error").show();
				value = false;
			} else {
				$("#ddLgdLBVillageDestAtVillageCA_error").hide();
			}
		} else {
			$("#ddLgdLBVillageDestAtVillageCA_error").hide();
		}

	}
	return value;

}

/*
 * function validateLBVillage() { var value = true; var ddlgdLBVillage =
 * document.getElementById('ddLgdLBVillageDestAtVillageCA');
 * 
 * if (ddlgdLBVillage != null) { if (ddlgdLBVillage.options.length <= 0) {
 * $("#ddLgdLBVillageDestAtVillageCA_error").show();
 * $("#ddLgdLBVillageDestAtVillageCA1_error").hide(); value = false; }
 * 
 * else if (ddlgdLBVillage.selectedIndex == -1) {
 * $("#ddLgdLBVillageDestAtVillageCA_error").show();
 * $("#ddLgdLBVillageDestAtVillageCA1_error").hide(); value = false; } if
 * (checkforFull(ddlgdLBVillage)) {
 * $("#ddLgdLBVillageDestAtVillageCA1_error").show(); //
 * $("#ddLgdUrbanLBDistExistDest_error").hide(); value = false; } else {
 * $("#ddLgdLBVillageDestAtVillageCA1_error").hide(); //
 * $("#ddLgdUrbanLBDistExistDest_error").hide(); } } return value; }
 */
function checkforFull(ddId) {
	var count = 0;
	for ( var m = 0; m < ddId.options.length; m++) {
		if (ddId.options[m].value.match('FULL')) {
			count = count + 1;
		} else {
			count = 0;
		}
	}
	if (count == 0) {
		return false;
	}
	if (count > 1) {
		return false;
	}
	if (count == 1) {
		return true;
	}
}

function validateLBNameEng() {

	if (document.getElementById('localBodyNameInEn').value == "") {
		document.getElementById("localBodyNameInEn_error").innerHTML = "Local Body Name English is required.";
		$("#localBodyNameInEn_error").show();
		$("#localBodyNameInEn_msg").hide();
		$("#localBodyNameInEn").addClass("error_fld");
		$("#localBodyNameInEn").addClass("error_msg");
		return false;
	} else {
		$("#localBodyNameInEn_msg").hide();
		return true;
	}
}

function validateLocalNameInEngMod() {

	if (document.getElementById('localBodyNameInEn').value == "") {
		$("#localBodyNameInEn_error").show();
		return false;
	}
	$("#localBodyNameInEn_error").hide();
	return true;

}

function validateOrderNoCorrectGovt() {

	var x = document.getElementById('OrderNo').value.trim();
	if (document.getElementById('OrderNo').value.trim() == "") {
		document.getElementById("OrderNo_error").innerHTML = "Order No. is required.";
		$("#OrderNo_error").show();
		$("#OrderNo_msg").hide();
		$("#OrderNo").addClass("error_fld");
		$("#OrderNo").addClass("error_msg");
		return false;
	} else {
		if (x.length > 20) {
			$("#OrderNo_error").show();
			document.getElementById("OrderNo_error").innerHTML = "Order No. cannot be more than 20 digits.";
			return false;
		}
		$("#OrderNo_msg").hide();
		return true;
	}
}

function validateOrderNo() {

	var x = document.getElementById('OrderNo').value.trim();
	if (document.getElementById('OrderNo').value.trim() == "") {
		document.getElementById("OrderNo_error").innerHTML = "Order No. is required.";
		$("#OrderNo_error").show();
		$("#OrderNo_msg").hide();
		$("#OrderNo").addClass("error_fld");
		$("#OrderNo").addClass("error_msg");
		return false;
	} else {
		if (x.length > 60) {
			$("#OrderNo_error").show();
			document.getElementById("OrderNo_error").innerHTML = "Order No. cannot be more than 20 digits.";
			return false;
		}
		$("#OrderNo_msg").hide();
		return true;
	}
}

function validateLBtype() {

	var urbanLBType = document.getElementById('urbanTypeLocalBody');

	if (urbanLBType.selectedIndex == 0) {
		$("#urbanTypeLocalBody_error").show();
		return false;
	}
	$("#urbanTypeLocalBody_error").hide();
	return true;
}

function validateParentLBtype() {

	var parentLB = document.getElementById('parentLocalBody');

	if (parentLB.selectedIndex == 0) {
		$("#parentLocalBody_error").show();
		return false;
	}
	$("#parentLocalBody_error").hide();
	return true;
}

function validateSourceState() {

	var ddsource = document.getElementById('ddSourceState');

	if (ddsource.selectedIndex == 0) {
		$("#ddSourceState_error").show();
		return false;
	}
	$("#ddSourceState_error").hide();
	return true;
}

function validateSFile1() {

	if (document.getElementById('attachFile1').value == "") {
		var tbl = document.getElementById("tid");
		var len = 0;
		if (tbl) {
			len = tbl.rows.length;
		}
		if (len <= 2) {
			document.getElementById("attachFile1_error").innerHTML = "Please upload at least one file as Government Order";
			$("#attachFile1_error").show();
			$("#attachFile1").addClass("error_fld");
			$("#attachFile1").addClass("error_msg");
			return false;
		} else {
			return true;
		}
	} else {
		$("#attachFile1_error").hide();
		$("#attachFile1").removeClass("error_fld");
		$("#attachFile1").removeClass("error_msg");
		return true;
	}

}

// //////
function validateLocalNameInEngModify() {

	var checkModifyValue = document.getElementById('localBodyNameInEn').value;
	var lbName = document.getElementById('lbNameEng').value;
	checkModifyValue = checkModifyValue.replace(/^\s+|\s+$/g, '');
	if (checkModifyValue == "") {
		document.getElementById("localBodyNameInEn_error").innerHTML = "Local Body Name (in English) is required.";
		$("#localBodyNameInEn_error").show();
		return false;

	} else if (lbName == checkModifyValue) {
		document.getElementById("localBodyNameInEn_error").innerHTML = "Modification of Local Body Name (in English) is Required.";
		$("#localBodyNameInEn_error").show();
		return false;

	} else {
		$("#localBodyNameInEn_error").hide();
		return true;
	}
}

function loadModifyNamePage() {
	$("#ddLgdLBType_error").hide();
	$("#ddlgdLBDistrictAtVillage_error").hide();
	$("#ddlgdLBDistAtInter_error").hide();
	$("#ddlgdLBInterAtVillage_error").hide();
	$("#ddLgdLBDistCAreaDestLUmapped_error").hide();
	$("#chkLgdLBExistChk_error").hide();
	$("#ddLgdLBDistPDestList_error").hide();
	$("#ddLgdLBDistPDestList1_error").hide();
	$("#ddLgdLBDistCAreaDestL_error").hide();
	$("#ddLgdLBSubDistrictDestLatDCA_error").hide();
	$("#ddLgdLBVillageDestLatDCA_error").hide();
	$("#ddLgdLBInterPDestList_error").hide();
	$("#ddLgdLBInterPDestList1_error").hide();
	$("#ddLgdLBVillageDestLatICA_error").hide();
	$("#ddLgdLBInterCAreaDestL_error").hide();
	$("#ddLgdLBVillageDestAtVillageCA_error").hide();
	$("#ddLgdLBVillageDestAtVillageCA1_error").hide();
	$("#ddLgdLBVillageCAreaDestL_error").hide();
	$("#localBodyNameInEn_error").hide();
	$("#availddLgdLBDistCAreaDestLUmapped_error").hide();

	if (document.getElementById('divLgdLBDistCArea')) {
		document.getElementById('divLgdLBDistCArea').style.display = 'none';
	}
	if (document.getElementById('divLgdLBVillageCArea')) {
		document.getElementById('divLgdLBVillageCArea').style.display = 'none';
	}

	if (document.getElementById('divLgdLBInterCArea')) {
		document.getElementById('divLgdLBInterCArea').style.display = 'none';
	}

	if (document.getElementById('divLgdLBVillageCAreaUnmapped')) {
		document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
	}

	if (document.getElementById('divLgdLBInterCAreaUnmapped')) {
		document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
	}

	if (document.getElementById('divLgdLBDistCAreaUnmapped')) {
		document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
	}

	if (document.getElementById('divLgdLBDistCAreaUnmapped')) {
		document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
	}
	if (document.getElementById('divLgdLBInterCAreaUnmapped')) {
		document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
	}

	// document.getElementById('modifyLocalBodyName');
	// if(document.getElementById('modifyLocalBodyName').checked)

	// if(document.getElementById('modifyParent') != null)
	// {
	// if(document.getElementById('modifyParent').checked){
	//			 
	// getHideModifyParentList(document.getElementById('modifyParent').checked);
	// }
	// }
	//	
	// if(document.getElementById('modifyType') != null)
	// {
	// if(document.getElementById('modifyType').checked)
	// getHideModifyTypeList();
	// }
	//	

	// getHideUnmappedListModifyforcoveredarea(document.getElementById('selectBox').value);
	var id = document.getElementById('selectBox').value;

	if (id != "0") {
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];

		if (id2 != "") {
			switch (id2) {
			case 'D':
				document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'block';
				document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
				document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
				// getUnmappedAreaByTypeforcoveredarea('D');
				break;
			case 'I':
				document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'block';
				document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
				document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
				// getUnmappedAreaByTypeforcoveredarea('T');
				break;
			case 'V':
				document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'block';
				document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
				document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
				// getUnmappedAreaByTypeforcoveredarea('V');
				break;

			default:
				document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
				document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
				document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
			}
		} else {
			document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
			document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
			document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
		}

	} else {
		alert("Please First Select Type of Local Body");

		document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
		document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
		document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
	}
}

function ondataLoad(id) {
	if (id != "0") {
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];
		var id3 = temp[2];

		if (id3 != 'U') {
			switch (id2) {
			case 'D':

				if (document.getElementById('availdivLgdLBDistCAreaUnmapped')) {
					document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'block';
				}
				if (document.getElementById('availdivLgdLBInterCAreaUnmapped')) {
					document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'none';
				}
				if (document.getElementById('availdivLgdLBVillageCAreaUnmapped')) {
					document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'none';
				}
				document.getElementById('availdivLgdUrban').style.display = 'none';
				break;
			case 'I':
				document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'block';
				document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'none';
				document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'none';
				document.getElementById('availdivLgdUrban').style.display = 'none';
				break;
			case 'V':

				document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'block';
				document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'none';
				document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'none';
				document.getElementById('availdivLgdUrban').style.display = 'none';
				document.getElementById('divLgdLBCoveredAreaHeadQuarter').style.display = 'block';
				document.getElementById('divLgdLBCoveredAreaHeadQuarter').style.visibility = 'visible';
				document.getElementById('getHeadQuartersV').style.display = 'block';
				document.getElementById('getHeadQuartersV').style.visibility = 'visible';
				getHeadQuarterListFinalWithoutMappedLBonLoad('V');

				break;
			}
		} else {
			document.getElementById('availdivLgdUrban').style.display = 'block';
			document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'none';
			document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'none';
			document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'none';
		}

	}

}

function getHeadQuarterListFinalWithoutMappedLBonLoadSubDist(lb) {
	var list1 = document.getElementById('availddLgdLBInterCAreaSourceLUmapped');

	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list1.length; i++) {
		listFinal[listFinal.length] = list1[i];
	}

	/*
	 * for(i=0;i<list2.length;i++) { listFinal[listFinal.length]=list2[i]; }
	 * for(i=0;i<list3.length;i++) { listFinal[listFinal.length]=list3[i]; }
	 */

	var headquarter = "getHeadQuarters" + lb;
	/*
	 * document.getElementById("getHeadQuartersD").innerHTML = '';
	 * document.getElementById("getHeadQuartersT").innerHTML = '';
	 */
	document.getElementById("getHeadQuartersT").innerHTML = '';
	if (listFinal.length >= 1) {
		var name;
		if (lb == 'D')
			name = 'District';
		else if (lb == 'T')
			name = 'Sub District';
		else if (lb == 'V')
			name = 'Village';

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter *</td><td width='200'>Contributing "
				+ name + " List" + "" + "</td>" + "" + "</tr>";

		for ( var j = 0; j < listFinal.length; j++) {

			var name = '';
			var finalchk = '';
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
				innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " checked" + " value="
						+ listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb + "'); /></td>"
						+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>" + "</tr>";
			} else {
				chkhead[j] = 'no';
				innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " value="
						+ listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb + "'); /></td>"
						+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>" + "</tr>";
			}
		}
		innerHTMLText += "</table></div>";
		document.getElementById(headquarter).innerHTML += innerHTMLText;
	}
}

function getHeadQuarterListFinalWithoutMappedLBonLoadDist(lb) {
	var list1 = document.getElementById('availddLgdLBDistCAreaSourceLUmapped');

	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list1.length; i++) {
		listFinal[listFinal.length] = list1[i];
	}

	/*
	 * for(i=0;i<list2.length;i++) { listFinal[listFinal.length]=list2[i]; }
	 * for(i=0;i<list3.length;i++) { listFinal[listFinal.length]=list3[i]; }
	 */

	var headquarter = "getHeadQuarters" + lb;
	/*
	 * document.getElementById("getHeadQuartersD").innerHTML = '';
	 * document.getElementById("getHeadQuartersT").innerHTML = '';
	 */
	document.getElementById("getHeadQuartersD").innerHTML = '';
	if (listFinal.length >= 1) {
		var name;
		if (lb == 'D')
			name = 'District';
		else if (lb == 'T')
			name = 'Sub District';
		else if (lb == 'V')
			name = 'Village';

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter *</td><td width='200'>Contributing "
				+ name + " List" + "" + "</td>" + "" + "</tr>";

		for ( var j = 0; j < listFinal.length; j++) {

			var name = '';
			var finalchk = '';
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
				innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " checked" + " value="
						+ listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb + "'); /></td>"
						+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>" + "</tr>";
			} else {
				chkhead[j] = 'no';
				innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " value="
						+ listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb + "'); /></td>"
						+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>" + "</tr>";
			}
		}
		innerHTMLText += "</table></div>";
		document.getElementById(headquarter).innerHTML += innerHTMLText;
	}
}

function getHeadQuarterListFinalWithoutMappedLBonLoad(lb) {
	var list1 = document.getElementById('availddLgdLBVillageCAreaSourceLUnmapped');

	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list1.length; i++) {
		listFinal[listFinal.length] = list1[i];
	}

	/*
	 * for(i=0;i<list2.length;i++) { listFinal[listFinal.length]=list2[i]; }
	 * for(i=0;i<list3.length;i++) { listFinal[listFinal.length]=list3[i]; }
	 */

	var headquarter = "getHeadQuarters" + lb;
	/*
	 * document.getElementById("getHeadQuartersD").innerHTML = '';
	 * document.getElementById("getHeadQuartersT").innerHTML = '';
	 */
	document.getElementById("getHeadQuartersV").innerHTML = '';
	if (listFinal.length >= 1) {
		var name;
		if (lb == 'D')
			name = 'District';
		else if (lb == 'T')
			name = 'Sub District';
		else if (lb == 'V')
			name = 'Village';

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter *</td><td width='200'>Contributing "
				+ name + " List" + "" + "</td>" + "" + "</tr>";

		for ( var j = 0; j < listFinal.length; j++) {

			var name = '';
			var finalchk = '';
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
				innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " checked" + " value="
						+ listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb + "'); /></td>"
						+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>" + "</tr>";
			} else {
				chkhead[j] = 'no';
				innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " value="
						+ listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb + "'); /></td>"
						+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>" + "</tr>";
			}
		}
		innerHTMLText += "</table></div>";
		document.getElementById(headquarter).innerHTML += innerHTMLText;
	}
}

/*
 * function CallPrint() { document.getElementById('footer').style.display =
 * 'block'; document.getElementById('footer').style.visibility = 'visible'; var
 * printContent = document.getElementById("divData");
 * //document.getElementById('btn1').style.visibility = 'hidden'; var Win4Print =
 * window.open('','','left=0,top=0,width=500,height=500,top=301,left=365,resizable=1,status=0,toolbar=0');
 * //alert("contect"+printContent.innerHTML);
 * Win4Print.document.write(printContent.innerHTML); Win4Print.document.close();
 * Win4Print.focus(); Win4Print.print(); Win4Print.close();
 * document.getElementById('footer').style.display = 'none';
 * document.getElementById('footer').style.visibility = 'hidden'; }
 */
var tmpchkpart;

function centeredPopup(ldRegionCd, chkpart) {

	var lbtypecode = document.getElementById('lbtypecode').value;
	lgdDwrlocalBodyService.getLocalGovtBodyMappedVillageList(ldRegionCd, lbtypecode, {
		callback : handleLocalGovtBodyMappedVillageSuccess,
		errorHandler : handleLocalGovtBodyMappedVillageError
	});
	tmpchkpart = chkpart;

}

function handleLocalGovtBodyMappedVillageSuccess(data) {
	// Assigns data to result id

	var lbody = document.getElementById('hdnLbCode').value;
	var fieldId = 'hiddenavaillgd_LBMappedLocalbody';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);

	if (data != null || data != "") {
		if (data.length > 1) {
			tmpchkpart.checked = true;
		}
		if (data.length == 1) {
			if (data[0].localBodyCode != lbody) {
				tmpchkpart.checked = true;
			}
		}
		var w = window.open('', '',
				'width=400,height=400,resizeable,position=center,scrollbars=yes,toolbar=no,menubar=no,location=no,directories=no,status=yes',
				align = "center");

		var list1 = document.getElementById('hiddenavaillgd_LBMappedLocalbody');
		var listFinal = [];
		var innerHTMLText = '';
		for ( var i = 0; i < list1.length; i++) {
			listFinal[listFinal.length] = list1[i];
		}

		innerHTMLText += "<table width='1200' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='200'>Associated Local bodies "
				+ "</td></tr>";

		if (listFinal.length >= 1) {
			for ( var j = 0; j < listFinal.length; j++) {

				var name = '';
				var checkVal = listFinal[j].value;
				var tempcheckVal = checkVal.split("_");
				var tempPFull = tempcheckVal[1];

				if (listFinal[j].text.match("(FULL)")) {
					name = listFinal[j].text.replace("(FULL)", "");
				} else if (listFinal[j].text.match("(PART)")) {
					name = listFinal[j].text.replace("(PART)", "");
				}
				if (!listFinal[j].text.match("(FULL)")) {
					name = listFinal[j].text;
				}

				innerHTMLText += "<tr></tr><tr></tr><tr class='tblRowB'><td width='150'><form:label path='lgd_LBNameInEnh' id=lbl" + j + ">" + name
						+ "</form:label></td></br>" + "" + " </td></tr></table>";

				// w.document.write(innerHTMLText);
				// w.document.close();
			}
			w.document.write(innerHTMLText);
			w.document.close();

			var close = function() {
				w.close();
			};
			setTimeout(close, 2000);

			/*
			 * w.onfocus=function() { w.close(); };
			 */

			// w.close();
			/*
			 * setTimeout(function() { popup.close(); window.location="2.html"
			 * },5500)
			 */
			/*
			 * w.onfocus=function() { w.document.close(); }
			 */
		}
	}

}

function handleLocalGovtBodyMappedVillageError() {
	// Show a popup message
	alert("No data found!");
}

function getCoveredandRegionwithTypesListFinal(lb) {
	var list1 = document.getElementById('availddLgdLBVillageCAreaSourceLUnmapped');

	var innerHTMLText = '';
	var listFinal = [];
	for ( var i = 0; i < list1.length; i++) {
		listFinal[listFinal.length] = list1[i];
	}
	var headquarter = "getLandRegionWithTypes" + lb;
	document.getElementById("getLandRegionWithTypesV").innerHTML = '';
	if (listFinal.length >= 1) {
		var name;
		if (lb == 'D')
			name = 'District';
		else if (lb == 'T')
			name = 'Sub District';
		else if (lb == 'V')
			name = 'Village';

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='200'>Contributing " + name
				+ " List" + "" + "</td><td width='50'>Select Part </td>" + " " + "<td width='50'>Select Full </td></tr>";

		document.getElementById('landregionlength').value = listFinal.length;

		for ( var j = 0; j < listFinal.length; j++) {

			var name = '';
			var checkVal = listFinal[j].value;
			var tempcheckVal = checkVal.split("_");
			var tempLandregion = tempcheckVal[0];
			var tempPFull = tempcheckVal[1];

			if (listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text.replace("(FULL)", "");
			} else if (listFinal[j].text.match("(PART)")) {
				name = listFinal[j].text.replace("(PART)", "");
			}
			if (!listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text;
			}

			if (tempPFull == 'P') {
				innerHTMLText += "<tr class='tblRowB'><td width='50'><form:label path='headQuarterName' id=lbl" + j + ">" + name
						+ "</form:label></td>" + "" + "<td width='500'><input type='radio' name='partR" + j + "' id=chkpartP" + j + " checked"
						+ " value=" + listFinal[j].value + " /></td>" + "<td width='500'><input type='radio' name='partR" + j + "' id=chkpartF" + j
						+ " value=" + listFinal[j].value + " onclick=javascript:centeredPopup(" + tempLandregion + ",chkpartP" + j + ");return false"
						+ " /></br></td></tr>";

			} else if (tempPFull == 'F') {
				innerHTMLText += "<tr class='tblRowB'><td width='50'><form:label path='headQuarterName' id=lbl" + j + ">" + name
						+ "</form:label></td>" + "" + "<td width='500'><input type='radio' name='partR" + j + "' id=chkpartP" + j + " value="
						+ listFinal[j].value + " /></td>" + "<td width='500'><input type='radio' name='partR" + j + "' id=chkpartF" + j + " checked"
						+ " value=" + listFinal[j].value + " /></br></td></tr>";
			}
		}
		innerHTMLText += "</table></div>";
		document.getElementById(headquarter).innerHTML += innerHTMLText;
	}
}

function getCoveredandRegionwithTypesListFinalDP(lb) {
	var list1 = document.getElementById('availddLgdLBDistCAreaSourceLUmapped');

	var innerHTMLText = '';
	var listFinal = [];
	for ( var i = 0; i < list1.length; i++) {
		listFinal[listFinal.length] = list1[i];
	}
	var headquarter = "getLandRegionWithTypes" + lb;
	document.getElementById("getLandRegionWithTypesD").innerHTML = '';
	if (listFinal.length >= 1) {
		var name;
		if (lb == 'D')
			name = 'District';
		else if (lb == 'T')
			name = 'Sub District';
		else if (lb == 'V')
			name = 'Village';

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='200'>Contributing " + name
				+ " List" + "" + "</td><td width='50'>Select Part </td>" + " " + "<td width='50'>Select Full </td></tr>";

		document.getElementById('landregionlength').value = listFinal.length;

		for ( var j = 0; j < listFinal.length; j++) {

			var name = '';
			var checkVal = listFinal[j].value;
			var tempcheckVal = checkVal.split("_");
			var tempLandregion = tempcheckVal[0];
			var tempPFull = tempcheckVal[1];

			if (listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text.replace("(FULL)", "");
			} else if (listFinal[j].text.match("(PART)")) {
				name = listFinal[j].text.replace("(PART)", "");
			}
			if (!listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text;
			}

			if (tempPFull == 'P') {
				innerHTMLText += "<tr class='tblRowB'><td width='150'><form:label path='headQuarterName' id=lbl" + j + ">" + name
						+ "</form:label></td>" + "" + "<td width='50'><input type='radio' name='partR" + j + "' id=chkpartP" + j + " checked"
						+ " value=" + listFinal[j].value + " /></td>" + "<td width='50'><input type='radio' name='partR" + j + "' id=chkpartF" + j
						+ " value=" + listFinal[j].value + " onclick=javascript:centeredPopup(" + tempLandregion + ",chkpartP" + j + ");return false"
						+ " /></br></td></tr>";

			} else if (tempPFull == 'F') {
				innerHTMLText += "<tr class='tblRowB'><td width='150'><form:label path='headQuarterName' id=lbl" + j + ">" + name
						+ "</form:label></td>" + "" + "<td width='50'><input type='radio' name='partR" + j + "' id=chkpartP" + j + " value="
						+ listFinal[j].value + " /></td>" + "<td width='50'><input type='radio' name='partR" + j + "' id=chkpartF" + j + " checked"
						+ " value=" + listFinal[j].value + " /></br></td></tr>";
			}
		}
		innerHTMLText += "</table></div>";
		document.getElementById(headquarter).innerHTML += innerHTMLText;
	}
}

function getCoveredandRegionwithTypesListFinalIP(lb) {
	var list1 = document.getElementById('availddLgdLBInterCAreaSourceLUmapped');

	var innerHTMLText = '';
	var listFinal = [];
	for ( var i = 0; i < list1.length; i++) {
		listFinal[listFinal.length] = list1[i];
	}
	var headquarter = "getLandRegionWithTypes" + lb;
	document.getElementById("getLandRegionWithTypesT").innerHTML = '';
	if (listFinal.length >= 1) {
		var name;
		if (lb == 'D')
			name = 'District';
		else if (lb == 'T')
			name = 'Sub District';
		else if (lb == 'V')
			name = 'Village';

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='200'>Contributing " + name
				+ " List" + "" + "</td><td width='50'>Select Part </td>" + " " + "<td width='50'>Select Full </td></tr>";

		document.getElementById('landregionlength').value = listFinal.length;

		for ( var j = 0; j < listFinal.length; j++) {

			var name = '';
			var checkVal = listFinal[j].value;
			var tempcheckVal = checkVal.split("_");
			var tempLandregion = tempcheckVal[0];
			var tempPFull = tempcheckVal[1];

			if (listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text.replace("(FULL)", "");
			} else if (listFinal[j].text.match("(PART)")) {
				name = listFinal[j].text.replace("(PART)", "");
			}
			if (!listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text;
			}

			if (tempPFull == 'P') {
				innerHTMLText += "<tr class='tblRowB'><td width='150'><form:label path='headQuarterName' id=lbl" + j + ">" + name
						+ "</form:label></td>" + "" + "<td width='50'><input type='radio' name='partR" + j + "' id=chkpartP" + j + " checked"
						+ " value=" + listFinal[j].value + " /></td>" + "<td width='50'><input type='radio' name='partR" + j + "' id=chkpartF" + j
						+ " value=" + listFinal[j].value + " onclick=javascript:centeredPopup(" + tempLandregion + ",chkpartP" + j + ");return false"
						+ " /></br></td></tr>";

			} else if (tempPFull == 'F') {
				innerHTMLText += "<tr class='tblRowB'><td width='150'><form:label path='headQuarterName' id=lbl" + j + ">" + name
						+ "</form:label></td>" + "" + "<td width='50'><input type='radio' name='partR" + j + "' id=chkpartP" + j + " value="
						+ listFinal[j].value + " /></td>" + "<td width='50'><input type='radio' name='partR" + j + "' id=chkpartF" + j + " checked"
						+ " value=" + listFinal[j].value + " /></br></td></tr>";
			}
		}
		innerHTMLText += "</table></div>";
		document.getElementById(headquarter).innerHTML += innerHTMLText;
	}
}

function getHeadQuarterListFinalWithoutMappedLBonLoadDP(lb) {
	var list1 = document.getElementById('availddLgdLBDistCAreaSourceLUmapped');

	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list1.length; i++) {
		listFinal[listFinal.length] = list1[i];
	}

	/*
	 * for(i=0;i<list2.length;i++) { listFinal[listFinal.length]=list2[i]; }
	 * for(i=0;i<list3.length;i++) { listFinal[listFinal.length]=list3[i]; }
	 */

	var headquarter = "getHeadQuarters" + lb;
	/*
	 * document.getElementById("getHeadQuartersD").innerHTML = '';
	 * document.getElementById("getHeadQuartersT").innerHTML = '';
	 */
	document.getElementById("getHeadQuartersD").innerHTML = '';
	if (listFinal.length >= 1) {
		var name;
		if (lb == 'D')
			name = 'District';
		else if (lb == 'T')
			name = 'Sub District';
		else if (lb == 'V')
			name = 'Village';

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter *</td><td width='200'>Contributing "
				+ name + " List" + "" + "</td>" + "" + "</tr>";

		for ( var j = 0; j < listFinal.length; j++) {

			var name = '';
			var finalchk = '';
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
				innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " checked" + " value="
						+ listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb + "'); /></td>"
						+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>" + "</tr>";
			} else {
				chkhead[j] = 'no';
				innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " value="
						+ listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb + "'); /></td>"
						+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>" + "</tr>";
			}
		}
		innerHTMLText += "</table></div>";
		document.getElementById(headquarter).innerHTML += innerHTMLText;
	}
}

function getHeadQuarterListFinalWithoutMappedLBonLoadIP(lb) {
	var list1 = document.getElementById('availddLgdLBInterCAreaSourceLUmapped');

	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list1.length; i++) {
		listFinal[listFinal.length] = list1[i];
	}

	/*
	 * for(i=0;i<list2.length;i++) { listFinal[listFinal.length]=list2[i]; }
	 * for(i=0;i<list3.length;i++) { listFinal[listFinal.length]=list3[i]; }
	 */

	var headquarter = "getHeadQuarters" + lb;
	/*
	 * document.getElementById("getHeadQuartersD").innerHTML = '';
	 * document.getElementById("getHeadQuartersT").innerHTML = '';
	 */
	document.getElementById("getHeadQuartersT").innerHTML = '';
	if (listFinal.length >= 1) {
		var name;
		if (lb == 'D')
			name = 'District';
		else if (lb == 'T')
			name = 'Sub District';
		else if (lb == 'V')
			name = 'Village';

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter *</td><td width='200'>Contributing "
				+ name + " List" + "" + "</td>" + "" + "</tr>";

		for ( var j = 0; j < listFinal.length; j++) {

			var name = '';
			var finalchk = '';
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
				innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " checked" + " value="
						+ listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb + "'); /></td>"
						+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>" + "</tr>";
			} else {
				chkhead[j] = 'no';
				innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " value="
						+ listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb + "'); /></td>"
						+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>" + "</tr>";
			}
		}
		innerHTMLText += "</table></div>";
		document.getElementById(headquarter).innerHTML += innerHTMLText;
	}
}

function getCoveredandRegionwithTypesListFinalUP(lb) {
	var list1 = document.getElementById('availddLgdLBInterCAreaSourceLUmapped');

	var innerHTMLText = '';
	var listFinal = [];
	for ( var i = 0; i < list1.length; i++) {
		listFinal[listFinal.length] = list1[i];
	}
	var headquarter = "getLandRegionWithTypes" + lb;
	document.getElementById("getLandRegionWithTypesT").innerHTML = '';
	if (listFinal.length >= 1) {
		var name;
		if (lb == 'D')
			name = 'District';
		else if (lb == 'T')
			name = 'Sub District';
		else if (lb == 'V')
			name = 'Village';

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='200'>Contributing " + name
				+ " List" + "" + "</td><td width='50'>Select Part </td>" + " " + "<td width='50'>Select Full </td></tr>";

		document.getElementById('landregionlength').value = listFinal.length;

		for ( var j = 0; j < listFinal.length; j++) {

			var name = '';
			var checkVal = listFinal[j].value;
			var tempcheckVal = checkVal.split("_");
			var tempLandregion = tempcheckVal[0];
			var tempPFull = tempcheckVal[1];

			if (listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text.replace("(FULL)", "");
			} else if (listFinal[j].text.match("(PART)")) {
				name = listFinal[j].text.replace("(PART)", "");
			}
			if (!listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text;
			}

			if (tempPFull == 'P') {
				innerHTMLText += "<tr class='tblRowB'><td width='150'><form:label path='headQuarterName' id=lbl" + j + ">" + name
						+ "</form:label></td></br>" + "" + "<td width='50'><input type='radio' name='partR" + j + "' id=chkpartP" + j + " checked"
						+ " value=" + listFinal[j].value + " /></td>" + "<td width='50'><input type='radio' name='partR" + j + "' id=chkpartF" + j
						+ " value=" + listFinal[j].value + " onclick=javascript:centeredPopup(" + tempLandregion + ",chkpartP" + j + ");return false"
						+ " /></td></tr>";

			} else if (tempPFull == 'F') {
				innerHTMLText += "<tr class='tblRowB'><td width='150'><form:label path='headQuarterName' id=lbl" + j + ">" + name
						+ "</form:label></td></br>" + "" + "<td width='50'><input type='radio' name='partR" + j + "' id=chkpartP" + j + " value="
						+ listFinal[j].value + " /></td>" + "<td width='50'><input type='radio' name='partR" + j + "' id=chkpartF" + j + " checked"
						+ " value=" + listFinal[j].value + " /></td></tr>";
			}
		}
		innerHTMLText += "</table></div>";
		document.getElementById(headquarter).innerHTML += innerHTMLText;
	}
}

function ondataLoadforCorrectCov(id) {

	/*
	 * $("#ddLgdLBType_error").hide();
	 * $("#ddlgdLBDistrictAtVillage_error").hide();
	 * $("#ddlgdLBDistAtInter_error").hide();
	 * $("#ddlgdLBInterAtVillage_error").hide();
	 * $("#ddLgdLBDistCAreaDestLUmapped_error").hide();
	 * $("#chkLgdLBExistChk_error").hide();
	 * $("#ddLgdLBDistPDestList_error").hide();
	 * $("#ddLgdLBDistPDestList1_error").hide();
	 * $("#ddLgdLBDistCAreaDestL_error").hide();
	 * $("#ddLgdLBSubDistrictDestLatDCA_error").hide();
	 * $("#ddLgdLBVillageDestLatDCA_error").hide();
	 * $("#ddLgdLBInterPDestList_error").hide();
	 * $("#ddLgdLBInterPDestList1_error").hide();
	 * $("#ddLgdLBVillageDestLatICA_error").hide();
	 * $("#ddLgdLBInterCAreaDestL_error").hide();
	 * $("#ddLgdLBVillageDestAtVillageCA_error").hide();
	 * $("#ddLgdLBVillageDestAtVillageCA1_error").hide();
	 * $("#ddLgdLBVillageCAreaDestL_error").hide();
	 * $("#localBodyNameInEn_error").hide();
	 */$("#ddLgdUrbanLBDistUmappedDest_error").hide();

	if (id != "0") {
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];
		var id3 = temp[2];
		if (id3 != 'U') {
			switch (id2) {
			case 'D':
				if (document.getElementById('availdivLgdLBDistCAreaUnmapped')) {
					document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'block';
				}
				if (document.getElementById('availdivLgdLBInterCAreaUnmapped')) {
					document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'none';
				}
				if (document.getElementById('availdivLgdLBVillageCAreaUnmapped')) {
					document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'none';
				}
				document.getElementById('divLgdLBCoveredArea').style.visibility = 'visible';
				document.getElementById('divLgdLBCoveredArea').style.display = 'block';
				document.getElementById('availdivLgdUrban').style.display = 'none';
				document.getElementById('getHeadQuartersD').style.visibility = 'visible';
				document.getElementById('getHeadQuartersD').style.display = 'block';

				getHeadQuarterListFinalWithoutMappedLBonLoadDP('D');
				break;
			case 'I':
				document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'block';
				document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'none';
				document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'none';
				document.getElementById('availdivLgdUrban').style.display = 'none';
				document.getElementById('divLgdLBCoveredArea').style.visibility = 'visible';
				document.getElementById('divLgdLBCoveredArea').style.display = 'block';
				document.getElementById('getHeadQuartersT').style.visibility = 'visible';
				document.getElementById('getHeadQuartersT').style.display = 'block';

				getHeadQuarterListFinalWithoutMappedLBonLoadIP('T');

				break;
			case 'V':
				document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'block';
				document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'none';
				document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'none';
				document.getElementById('availdivLgdUrban').style.display = 'none';

				document.getElementById('divLgdLBCoveredArea').style.visibility = 'visible';
				document.getElementById('divLgdLBCoveredArea').style.display = 'block';
				document.getElementById('getHeadQuartersV').style.visibility = 'visible';
				document.getElementById('getHeadQuartersV').style.display = 'block';

				getHeadQuarterListFinalWithoutMappedLBonLoad('V');

				break;
			}
		} else {
			document.getElementById('availdivLgdUrban').style.display = 'block';
			document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'none';
			document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'none';
			document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'none';
		}

		if (id != "0") {
			if (id3 != 'U') {
				if (id2 != "") {
					switch (id2) {
					case 'D':
						document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'block';
						document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
						document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
						break;
					case 'I':
						document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'block';
						document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
						document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
						break;
					case 'V':
						document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'block';
						document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
						document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
						break;
					default:
						document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
						document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
						document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
					}
				}
			} else {
				document.getElementById('divLgdLBUrbanUnmapped').style.display = 'block';
				document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
				document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
				document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
			}

		} else {
			alert("Please First Select Type of Local Body");
			document.getElementById('divLgdLBUrbanUnmapped').style.display = 'none';
			document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
			document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
			document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
		}

	}
}

function checkBoxces() {

	var modifyNameCheck = document.getElementById('modifyLocalBodyName');
	var modifyTypeCheck = document.getElementById('modifyType');

	var modifyParentCheck = document.getElementById('modifyParent');

	if (document.getElementById('hiddenCheckBox').value == 'U') {
		if (modifyNameCheck.checked == false && modifyTypeCheck.checked == false) {
			return false;
		}
		return true;

	} else if (document.getElementById('hiddenCheckBox').value != 'D' && document.getElementById('hiddenCheckBox').value != 'U') {
		if (modifyParentCheck.checked == false && modifyNameCheck.checked == false) {
			return false;
		}
		return true;

	} else if (document.getElementById('hiddenCheckBox').value == 'D') {
		if (modifyNameCheck.checked == false) {
			return false;
		}
		return true;
	} else {
		return true;
	}
}

function localBodyTypeMunicipility() {
	var ddurbanTypeLocalBody = document.getElementById('urbanTypeLocalBody');

	if (ddurbanTypeLocalBody.selectedIndex == 0) {
		$("#urbanTypeLocalBody_error").show();

		return false;

	}
	$("#urbanTypeLocalBody_error").hide();
	return true;

}

function localBodyParentCheck() {

	var localBodyParent = document.getElementById('parentLocalBody');
	var localBodyVillageParent = document.getElementById('ddlgdLBInterAtVillage');
	if (document.getElementById('hiddenCheckBox').value == 'I') {

		if (localBodyParent.selectedIndex == 0) {

			$("#parentLocalBody_error").show();

			return false;

		}

	} else if (document.getElementById('hiddenCheckBox').value == 'V')

		if (localBodyParent.selectedIndex == 0) {
			$("#parentLocalBody_error").show();

			return false;

		}

		else if (localBodyParent.selectedIndex > 0) {
			if (localBodyVillageParent.selectedIndex == 0) {
				$("#ddlgdLBInterAtVillage_error").show();

				return false;

			}

		}
	$("#parentLocalBody_error").hide();
	$("#ddlgdLBInterAtVillage_error").hide();

	return true;

	// document.getElementById("localBodyNameInEn_error").innerHTML="Local Body
	// Name (in English) is required.";

}

function checkValidationCovChange() {

	var flag = false;
	var flag2 = false;
	if (document.getElementById('attachFile1').value.trim() == "") {
		var tbl = document.getElementById("tid");
		var len = 0;
		if (tbl) {
			len = tbl.rows.length;
			if (len > 2) {
				if (delFlag != null && delFlag == "attachFile1") {
					flag = false;
				} else {
					flag = true;
				}
			}
		} else {
			flag = false;
		}
	} else {
		flag = true;
	}

	if (document.getElementById('attachFile2').value.trim() == "") {
		var tbl = document.getElementById("maptid");
		var len = 0;
		if (tbl) {
			len = tbl.rows.length;
			if (len > 2) {
				if (delFlag != null && delFlag == "attachFile2") {
					flag2 = false;
				} else {
					flag2 = true;
				}
			}
		} else {
			flag2 = false;
		}
	} else {
		flag2 = true;
	}

	if (flag == true && flag2 == true) {
		$("#attachFile1_error").hide();
		$("#attachFile1").removeClass("error_fld");
		$("#attachFile1").removeClass("error_msg");
		$("#attachFile2_error").hide();
		$("#attachFile2").removeClass("error_fld");
		$("#attachFile2").removeClass("error_msg");
		return true;
	} else if (flag == false && flag2 == true) {
		document.getElementById("attachFile1_error").innerHTML = "Please upload at least one file as Government Order";
		$("#attachFile1_error").show();
		$("#attachFile1").addClass("error_fld");
		$("#attachFile1").addClass("error_msg");
		return false;
	} else if (flag == true && flag2 == false) {
		document.getElementById("attachFile2_error").innerHTML = "Please upload at least one file as Government Order";
		$("#attachFile2_error").show();
		$("#attachFile2").addClass("error_fld");
		$("#attachFile2").addClass("error_msg");
		return false;
	}

}

function validateGovtOrderCovChng() {
	var temp = document.getElementById('orderCodeHidden').value;
	var countnum = document.getElementById('govtfilecount').value;
	var orNumber;
	var orDate;
	var efDate;
	var fileattached;
	var status = true;
	$("#OrderNo_error").hide();
	$("#OrderDate_error").hide();
	$("#EffectiveDate_error").hide();
	$("#error_govorder").hide();
	orNumber = document.getElementById('OrderNo').value;
	orDate = document.getElementById('OrderDate').value;
	if (temp.length == 0) {
		efDate = document.getElementById('EffectiveDate').value;
	} else {
		efDate = document.getElementById('EffectiveDateNNull').value;
	}
	gPubdate = document.getElementById('GazPubDate').value;
	fileattached = document.getElementById('attachFile1').value;
	if (orNumber == "") {
		document.getElementById("OrderNo_error").innerHTML = "Order No. is required.";
		$("#OrderNo_error").show();
		status = false;
	}
	if (orDate == "") {
		document.getElementById("OrderDate_error").innerHTML = "Order Date is required.";
		$("#OrderDate_error").show();
		status = false;
	}
	if (efDate == "") {
		document.getElementById("EffectiveDate_error").innerHTML = "Effective Date is required.";
		$("#EffectiveDate_error").show();
		status = false;
	}
	if (fileattached == "") {
		if (countnum == 0) {
			document.getElementById("error_govorder").innerHTML = "Please Upload Government Order.";
			$("#error_govorder").show();
			status = false;
		}
	}
	return status;
}

/*
 * function validateOrdeNo() {
 * alert("document.getElementById('OrderNo').value"+document.getElementById('OrderNo').value);
 * if(document.getElementById('OrderNo').value=="") {
 * document.getElementById("OrderNo_error").innerHTML="Order No. is required.";
 * $("#OrderNo_error").show(); $("#OrderNo").addClass("error_fld");
 * $("#OrderNo").addClass("error_msg"); return false; } else {
 * $("#OrderNo_error").hide(); $("#OrderNo").removeClass("error_fld");
 * $("#OrderNo").removeClass("error_msg"); return true; } }
 */

function validateOrdeDate() {

	var orderdate = document.getElementById('OrderDate').value.trim();
	if (document.getElementById('OrderDate').value.trim() == "") {

		document.getElementById("OrderDate_error").innerHTML = "Order Date is required.";
		$("#OrderDate_error").show();
		$("#OrderDate").addClass("error_fld");
		$("#OrderDate").addClass("error_msg");
		return false;
	}

	else if (!validateDateDDMMYYY(orderdate)) {
		document.getElementById("OrderDate_error").innerHTML = "Enter valid date(dd-mm-yyyy) format";
		$("#OrderDate_error").show();
		$("#OrderDate").addClass("error_fld");
		$("#OrderDate").addClass("error_msg");
		return false;
	} else if (!compareDateDDMMYYY(orderdate)) {
		document.getElementById("OrderDate_error").innerHTML = "Order Date should be equal or earlier to Current Date ";
		$("#OrderDate_error").show();
		$("#OrderDate").addClass("error_fld");
		$("#OrderDate").addClass("error_msg");
		return false;
	} else {
		$("#OrderDate_error").hide();
		$("#OrderDate").removeClass("error_fld");
		$("#OrderDate").removeClass("error_msg");
		return true;
	}
}

function validateEffecDate() {

	var effecdate = document.getElementById('EffectiveDate').value;

	if (document.getElementById('EffectiveDate').value == "") {
		document.getElementById("EffectiveDate_error").innerHTML = "Effective Date is required.";
		$("#EffectiveDate_error").show();
		$("#EffectiveDate").addClass("error_fld");
		$("#EffectiveDate").addClass("error_msg");
		return false;

	}

	else if (!validateDateDDMMYYY(effecdate)) {
		document.getElementById("EffectiveDate_error").innerHTML = "Enter valid date(dd-mm-yyyy) format";
		$("#EffectiveDate_error").show();
		$("#EffectiveDate").addClass("error_fld");
		$("#EffectiveDate").addClass("error_msg");
		return false;
	}
	// not allowed future date
	else if (!compareDateforFutureDDMMYYY(effecdate)) {

		document.getElementById("EffectiveDate_error").innerHTML = "Effective Date should not be future date";
		$("#EffectiveDate_error").show();
		$("#EffectiveDate").addClass("error_fld");
		$("#EffectiveDate").addClass("error_msg");
		return false;
	}

	else if (!compareDateforEffectDDMMYYY(effecdate)) {

		document.getElementById("EffectiveDate_error").innerHTML = "Order date should be less or equal to the Effective Date";
		$("#EffectiveDate_error").show();
		$("#EffectiveDate").addClass("error_fld");
		$("#EffectiveDate").addClass("error_msg");
		return false;
	}

	else {

		$("#EffectiveDate_error").hide();
		$("#EffectiveDate").removeClass("error_fld");
		$("#EffectiveDate").removeClass("error_msg");
		return true;
	}
}

function validateGazPubDate() {
	var gazPubDate = document.getElementById('GazPubDate').value;
	if (document.getElementById('GazPubDate').value.trim() != "") {

		if (!validateDateDDMMYYY(gazPubDate)) {

			document.getElementById("GazPubDate_error").innerHTML = "Enter valid date(dd-mm-yyyy) format";
			$("#GazPubDate_error").show();
			$("#GazPubDate").addClass("error_fld");
			$("#GazPubDate").addClass("error_msg");
			return false;

		}

		else if (!compareDateforEffectDDMMYYY(gazPubDate)) {
			document.getElementById("GazPubDate_error").innerHTML = "Gazette Publication Date should be equal to or greater than Order Date ";
			$("#GazPubDate_error").show();
			$("#GazPubDate").addClass("error_fld");
			$("#GazPubDate").addClass("error_msg");
			return false;
		}

		else {
			$("#GazPubDate_error").hide();
			$("#GazPubDate").removeClass("error_fld");
			$("#GazPubDate").removeClass("error_msg");
			return true;
		}
	} else {
		$("#GazPubDate_error").hide();
		$("#GazPubDate").removeClass("error_fld");
		$("#GazPubDate").removeClass("error_msg");
		return true;
	}
}

function validateSFile() {

	if (document.getElementById('attachFile1').value == "") {
		document.getElementById("attachFile1_error").innerHTML = "Please upload at least one file as Government Order";
		$("#attachFile1_error").show();
		$("#attachFile1").addClass("error_fld");
		$("#attachFile1").addClass("error_msg");
		return false;
	} else {
		$("#attachFile1_error").hide();
		$("#attachFile1").removeClass("error_fld");
		$("#attachFile1").removeClass("error_msg");
		return true;
	}
}

function validateFileTemplate() {
	if (document.getElementById('templateList').selectedIndex == 0) {
		document.getElementById("templateList_error").innerHTML = "Please select Government Order Template";
		$("#templateList_error").show();
		$("#templateList").addClass("error_fld");
		$("#templateList").addClass("error_msg");
		return false;
	} else {
		$("#templateList_error").hide();
		$("#templateList").removeClass("error_fld");
		$("#templateList").removeClass("error_msg");
		return true;
	}
}
function clearNames() {
	document.getElementById("modifyNameError").innerHTML = "";
}
