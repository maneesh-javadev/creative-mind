function validateLocalNameInEng() {

	if (document.getElementById('localBodyNameInEn').value == "") {
		$("#localBodyNameInEn_error").show();
		return false;
	}
	$("#localBodyNameInEn_error").hide();
	return true;

}

function validateUploadMap() {
	if (document.getElementById('fileMapUpLoad').value == "") {
		$("#fileMapUpLoad_error").show();
		return false;
	}
	$("#fileMapUpLoad_error").hide();
	return true;

}

function validateLBlatitude() {
	if (document.getElementById('latitude').value == "") {
		$("#latitude_error").show();
		return false;
	}
	$("#latitude_error").hide();
	return true;

}

function validateLBlongitude() {
	if (document.getElementById('longitude').value == "") {
		$("#longitude_error").show();
		return false;
	}
	$("#longitude_error").hide();
	return true;

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

function validateLBDistAtInter() {
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
				$("#ddLgdLBInterPDestList1_error").show();
				value = false;
			} else {
				$("#ddLgdLBInterPDestList1_error").hide();
			}
		}
	}

	return value;
}

/*
 * function validateDistrictPachAtInter() { var value=true; var
 * ddlgdLBDistAtInter = document.getElementById('ddlgdLBDistAtInter');
 * alert("ddlgdLBDistAtInter===== "+ddlgdLBDistAtInter); if
 * (document.getElementById('divLgdLBInterCArea').style.display == 'block') { if
 * (ddlgdLBDistAtInter.options.length<=0) {
 * $("#ddlgdLBDistAtInter_error").show(); value=false; }
 * 
 * else if (ddlgdLBDistAtInter.selectedIndex == -1) {
 * $("#ddlgdLBDistAtInter_error").show(); value=false; }
 * 
 * if(ddlgdLBDistAtInter.options.length==1) {
 * if(checkforOnlyFull(ddlgdLBDistAtInter)) {
 * $("#ddLgdLBInterPDestList1_error").show(); value=false; } else {
 * $("#ddLgdLBInterPDestList1_error").hide(); } } }
 * 
 * return value; }
 */

function validateDistrictPachAtInter() {
	var ddlgdLBDistAtInter = document.getElementById('ddlgdLBDistAtInter');
	if (ddlgdLBDistAtInter.selectedIndex == 0) {
		$("#ddlgdLBDistAtInter_error").show();
		return false;
	}
	$("#ddlgdLBDistAtInter_error").hide();
	return true;
}

function validateInterPachAtVill() {
	if (document.getElementById('divLgdVillageP').style.display == 'block') {
		var ddlgdLBDistrictAtVillage = document.getElementById('ddlgdLBDistrictAtVillage');
		if (ddlgdLBDistrictAtVillage.selectedIndex == 0) {
			$("#ddlgdLBDistrictAtVillage_error").show();
			return false;
		}
	}
	$("#ddlgdLBDistrictAtVillage_error").hide();
	return true;
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

function validateLBDistrictAtVillage() {
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
				$("#ddLgdLBVillageDestAtVillageCA1_error").show();
				value = false;
			} else {
				$("#ddLgdLBVillageDestAtVillageCA1_error").hide();
			}
		} else {
			$("#ddLgdLBVillageDestAtVillageCA1_error").hide();
		}

	}
	return value;

}

function validateLBInterAtVillage() {
	if (document.getElementById('divLgdVillageP').style.display == 'block') {
		var ddlgdLBDistAtInter = document.getElementById('ddlgdLBInterAtVillage');
		if (ddlgdLBDistAtInter.selectedIndex == 0) {
			$("#ddlgdLBInterAtVillage_error").show();
			return false;
		} else if (ddlgdLBDistAtInter.selectedIndex == -1) {
			$("#ddlgdLBInterAtVillage_error").show();
			return false;
		}

	}
	$("#ddlgdLBInterAtVillage_error").hide();
	return true;
}

function validateCheckbox() {
	var chkLgdLBExistChk = document.getElementById('chkLgdLBExistChk');
	var chkLgdLBUnmapped = document.getElementById('chkLgdLBUnmapped');
	if (!chkLgdLBExistChk.checked && !chkLgdLBUnmapped.checked) {
		$("#chkLgdLBExistChk_error").show();
		return false;
	}
	$("#chkLgdLBExistChk_error").hide();
	return true;
}

/*
 * function validateLocalbodyAll() {
 * 
 * var msg = null; selectALLVillages();
 * 
 * if (!validateLocalNameInEng()) { msg = "Please Enter Name of New Local Body
 * in English" + '\n'; }
 * 
 * if (!validateLBtypeName()) { if (msg != null) { msg = msg + "Please Select
 * Local Body Type" + '\n'; } else { msg = "Please Select Local Body Type" +
 * '\n'; } } if (!validateCheckbox()) { if (msg != null) { msg = msg + "Please
 * check at least one checkbox" + '\n'; } else { msg = "Please check at least
 * one checkbox" + '\n'; } }
 * 
 * var chkLgdLBExistChk = document.getElementById('chkLgdLBExistChk');
 * if(chkLgdLBExistChk.checked) { var ddLBTypeCode =
 * document.getElementById('ddLgdLBType').value; var temp =
 * ddLBTypeCode.split(":"); var id2 = temp[1]; if (id2 == 'D') { if
 * (!validateDistrictPanchayatList()) { if (msg != null) { msg = msg + "Please
 * Select at least one District Panchayat to convert" + '\n'; } else { msg =
 * "Please Select at least one District Panchayat to convert" + '\n'; } }
 * 
 * else if(!validateDistrictPListforFULLandPART()) { if (msg != null) { msg =
 * msg + "Please Select minimum one PART/one FULL and one PART/Two FULL" + '\n'; }
 * else { msg = "Please Select minimum one PART/one FULL and one PART/Two FULL" +
 * '\n'; } }
 * 
 * if(!validateDistrictCoveredAreaAtDistrictLevel()) { if (msg != null) { msg =
 * msg + "Please Select at least one District to convert" + '\n'; } else { msg =
 * "Please Select at least one District to convert" + '\n'; } }
 * if(!validateSubDistrictCoveredAreaAtDCA()) { if (msg != null) { msg = msg +
 * "Please Select at least one SubDistrict to convert" + '\n'; } else { msg =
 * "Please Select at least one SubDistrict to convert" + '\n'; } }
 * if(!validateVillageCoverdAreaAtDCA()) { if (msg != null) { msg = msg +
 * "Please Select at least one Village to convert" + '\n'; } else { msg =
 * "Please Select at least one Village to convert" + '\n'; } } } else if (id2 ==
 * 'I') { if (!validateLBDistAtInter()) { if (msg != null) { msg = msg + "Please
 * Select District Panchayat" + '\n'; } else { msg = "Please Select District
 * Panchayat" + '\n'; } } if(!validateIntermediatePanchayatList()){ if (msg !=
 * null) { msg = msg + "Please Select at least one Intermediate Panchayat to
 * convert" + '\n'; } else { msg = "Please Select at least one Intermediate
 * Panchayat to convert" + '\n'; } }
 * 
 * else if(!validateIntermediatePListforFULLandPART()) { if (msg != null) { msg =
 * msg + "Please Select minimum one PART/one FULL and one PART/Two FULL" + '\n'; }
 * else { msg = "Please Select minimum one PART/one FULL and one PART/Two FULL" +
 * '\n'; } } if(!validateSubDistrictCoveredAreaAtICA()) { if (msg != null) { msg =
 * msg + "Please Select at least one SubDistrict to convert" + '\n'; } else {
 * msg = "Please Select at least one SubDistrict to convert" + '\n'; } }
 * if(!validateVillageCoverdAreaAtICA()) { if (msg != null) { msg = msg +
 * "Please Select at least one Village to convert" + '\n'; } else { msg =
 * "Please Select at least one Village to convert" + '\n'; } } } else if (id2 ==
 * 'V') {
 * 
 * if (!validateLBDistrictAtVillage()) { if (msg != null) { msg = msg + "Please
 * Select District Panchayat" + '\n'; } else { msg = "Please Select District
 * Panchayat" + '\n'; } } if (!validateLBInterAtVillage()) { if (msg != null) {
 * msg = msg + "Please Select Intermediate Panchayat" + '\n'; } else { msg =
 * "Please Select Intermediate Panchayat" + '\n'; } }
 * if(!validateVillagePanchayatList()) { if (msg != null) { msg = msg + "Please
 * Select Village Panchayat to convert" + '\n'; } else { msg = "Please Select
 * Village Panchayat to convert" + '\n'; } } else
 * if(!validateVillagePListforFULLandPART()) { if (msg != null) { msg = msg +
 * "Please Select minimum one PART/one FULL and one PART/Two FULL" + '\n'; }
 * else { msg = "Please Select minimum one PART/one FULL and one PART/Two FULL" +
 * '\n'; } }
 * 
 * if(!validateVillageCoverdAreaAtVCA()) { if (msg != null) { msg = msg +
 * "Please Select at least one Village to convert" + '\n'; } else { msg =
 * "Please Select at least one Village to convert" + '\n'; } } } }
 * 
 * if (msg != null) { alert(msg); return false; } else { return true; } return
 * false; }
 */

function validateLocalbodyAll() {

	var msg = null;
	selectALLVillages();
	var errors = new Array();
	var error = false;
	errors[0] = !validateLocalNameInEng();
	errors[1] = !validateLBtypeName();
	errors[2] = !validateCheckbox();

	var chkLgdLBExistChk = document.getElementById('chkLgdLBExistChk');

	if (chkLgdLBExistChk.checked) {
		var ddLBTypeCode = document.getElementById('ddLgdLBType').value;
		var temp = ddLBTypeCode.split(":");
		var id2 = temp[1];
		if (id2 == 'D') {
			errors[3] = !validateDistrictPanchayatList();

			// errors[4] = !validateDistrictPListforFULLandPART();
			// errors[5] = !validateDistrictCoveredAreaAtDistrictLevel();
			/*
			 * errors[6] = !validateSubDistrictCoveredAreaAtDCA(); errors[7] =
			 * !validateVillageCoverdAreaAtDCA();
			 */
		}

		else if (id2 == 'I') {
			errors[8] = !validateLBDistAtInter();

			/*
			 * errors[9] = !validateIntermediatePanchayatList(); errors[10] =
			 * !validateIntermediatePListforFULLandPART(); errors[11] =
			 * !validateSubDistrictCoveredAreaAtICA(); errors[12] =
			 * !validateVillageCoverdAreaAtICA();
			 */
		} else if (id2 == 'V') {
			errors[13] = !validateLBDistrictAtVillage();

			/*
			 * errors[14] = !validateVillagePanchayatList(); errors[15] =
			 * !validateVillageCoverdAreaAtVCA();
			 */
		}
	}

	for ( var i = 0; i < 16; i++) {

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

function validateLocalbodyAllFinal() {
	var msg = null;

	/* 	 */

	var abc = "";
	var temp;
	/*
	 * validation error removed for unmapped Land Region (coverage) @author
	 * Ashish Dhupia @date 09-10-2014
	 */
	var id = "";
	if ($('#ddLgdLBDistPDestList').is(":visible")) {
		// alert("one visible");
		var selObj = document.getElementById('ddLgdLBDistPDestList');
		for ( var i = 0; i < selObj.options.length; i++) {
			temp = selObj.options[i].value;
			var test = temp.split("_");
			id = test[1];
			// alert("id:" + id);
			$('#ddLgdLBDistCAreaDestL option').attr('selected', true);
			var a = $('#ddLgdLBDistCAreaDestL').val();
			// abc = abc + id + "@";
		}
	}

	if ($('#ddLgdLBInterPDestList').is(":visible")) {
		// alert("two visible");
		var selObj = document.getElementById('ddLgdLBInterPDestList');
		for ( var i = 0; i < selObj.options.length; i++) {
			temp = selObj.options[i].value;
			var test = temp.split("_");
			id = test[1];
			// alert("id:" + id);
			$('#ddLgdLBInterCAreaDestL option').attr('selected', true);
			var a = $('#ddLgdLBInterCAreaDestL').val();
			// abc = abc + id + "@";
		}
	}

	if ($('#ddLgdLBVillageDestAtVillageCA').is(":visible")) {
		// alert("three visible");
		var selObj = document.getElementById('ddLgdLBVillageDestAtVillageCA');
		for ( var i = 0; i < selObj.options.length; i++) {
			temp = selObj.options[i].value;
			var test = temp.split("_");
			id = test[1];
			// alert("id:" + id);
			$('#ddLgdLBVillageCAreaDestL option').attr('selected', true);
			var a = $('#ddLgdLBVillageCAreaDestL').val();
			// abc = abc + id + "@";
		}
	}
	if (id == "PART") {
		if (a == null || a == "") {
			alert("Must select coverage from partly selected localbody");
			// var abc1 = $('#ddLgdLBDistCAreaDestL').length;
			// var aa = $(a).find("option").length;
			return false;
		}
	}

	/*	 */

	selectALLVillages();

	var selObj = document.getElementById('ddLgdLBVillageDestLatDCA');
	var villageCode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		villageCode += selObj.options[i].value + ",";
	}

	var errors = new Array();
	var error = false;
	errors[0] = !validateLocalNameInEng();
	errors[1] = !validateLBtypeName();
	errors[2] = !validateCheckbox();

	var chkLgdLBExistChk = document.getElementById('chkLgdLBExistChk');
	var chkLgdLBUnmappedChk = document.getElementById('chkLgdLBUnmapped');

	var ddLBTypeCode = document.getElementById('ddLgdLBType').value;
	var temp = ddLBTypeCode.split(":");
	var id2 = temp[1];
	if (id2 == 'I') {
		if (chkLgdLBExistChk.checked) {
			var gtadp = document.getElementById('gtadp').value;
			if (gtadp == 1 || gtadp == 0) {
				errors[3] = !validateDistrictPachAtInter();
			}
			if (gtadp == 20) {
				var gtachild = document.getElementById('gtachild').value;
				if (gtachild == 0) {
					$('#errSelectGta').show();
					// alert("Kindly add the Gta Child List");
					return false;
				}

			}
		}
	} else if (id2 == 'V') {
		if (chkLgdLBExistChk.checked) {
			var stateCode = dwr.util.getValue('hdnStateCode');
			if (stateCode == 19) {
				var gtadp = document.getElementById('gtadp').value;
				if (gtadp == 0) {
					$('#errParentType').show();
					// alert("Enter parent type");
					return false;

				}
				if (gtadp == 1) {
					errors[4] = !validateDistrictPachAtInter();
					errors[5] = !validateInterPachAtVill();
				}
				if (gtadp == 20) {
					var gtachild = document.getElementById('gtachild').value;
					if (gtachild == 0) {
						$('#errSelectGta').show();
						// alert("Kindly add the Gta Child List");
						return false;
					}
					var gtaInterMediate = document.getElementById('gtaIntermediateid').value;
					if (gtaInterMediate == 0) {
						$('#errSelectGtaInter').show();
						// alert("Kindly add the Intermediate Panchayat List");
						return false;
					}

				}

			} else {

				errors[4] = !validateDistrictPachAtInter();
				errors[5] = !validateInterPachAtVill();
			}
		}
	}

	if (chkLgdLBExistChk.checked) {
		if (id2 == 'D') {
			errors[6] = !validateDistrictPanchayatList();

			// errors[4] = !validateDistrictPListforFULLandPART();
			// errors[5] = !validateDistrictCoveredAreaAtDistrictLevel();
			/*
			 * errors[6] = !validateSubDistrictCoveredAreaAtDCA(); errors[7] =
			 * !validateVillageCoverdAreaAtDCA();
			 */
		} else if (id2 == 'I') {
			errors[7] = !validateLBDistAtInter();

			/*
			 * errors[10] = !validateIntermediatePListforFULLandPART();
			 * errors[11] = !validateSubDistrictCoveredAreaAtICA(); errors[12] =
			 * !validateVillageCoverdAreaAtICA();
			 */

		} else if (id2 == 'V') {
			errors[8] = !validateLBDistrictAtVillage();

			/*
			 * errors[14] = !validateVillagePanchayatList(); errors[15] =
			 * !validateVillageCoverdAreaAtVCA();
			 */
		}
	}
	if (chkLgdLBUnmappedChk.checked) {
		if (id2 == 'D') {
			errors[9] = !validateLBUnmappedDistrictAtDP();
		}
		if (id2 == 'I') {
			errors[10] = !validateLBUnmappedSubdistrictAtIP();
		}
		if (id2 == 'V') {
			errors[11] = !validateLBUnmappedVillageAtVP();
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

		$("#localBodyFormId").submit();
		return true;
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

function selectALLUrban() {
	// alert("validateUrbanLocalbodyAll");
	var chkExist = document.getElementById('chkLgdLBExistChk');
	var chkLgdLBUnmapped = document.getElementById('chkLgdLBUnmapped');
	if (chkExist.checked) {
		var selObj = document.getElementById('ddLgdUrbanLBSubdistrictListDest');
		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
		}
		var selObj1 = document.getElementById('ddLgdUrbanLBDistExistDest');
		for ( var i = 0; i < selObj1.options.length; i++) {
			selObj1.options[i].selected = true;
		}
	}

	if (chkLgdLBUnmapped.checked) {
		// alert("hello"+chkLgdLBUnmapped.checked);
		var selObj = document.getElementById('ddLgdUrbanLBDistUmappedDest');
		// alert("hhh"+selObj);
		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
		}
		/*
		 * var selObj1 =
		 * document.getElementById('ddLgdUrbanLBSubdistrictLUmappedDest'); for (
		 * 
		 * r i = 0; i < selObj1.options.length; i++) { selObj1.options[i].selec =
		 * true; }
		 */
	}
}

/*
 * function validateDistrictPanchayatList() { var value=true; var
 * ddLgdLBDistPDestList = document.getElementById('ddLgdLBDistPDestList'); if
 * (document.getElementById('divLgdLBDistCArea').style.display == 'block') {
 * 
 * if (ddLgdLBDistPDestList.options.length<=0) {
 * $("#ddLgdLBDistPDestList_error").show(); value=false; } else if
 * (ddLgdLBDistPDestList.selectedIndex == -1) {
 * $("#ddLgdLBDistPDestList_error").show(); value=false; }
 * 
 * if(checkforOnlyFull(ddLgdLBDistPDestList)) {
 * $("#ddLgdLBDistPDestList1_error").show(); value=false; } else {
 * $("#ddLgdLBSubDistrictDestLatDCA_error").hide(); } } return value; }
 */

function validateDistrictPListforFULLandPART() {
	var ddLgdLBDistPDestList = document.getElementById('ddLgdLBDistPDestList');
	if (!checkforFULLandPART(ddLgdLBDistPDestList)) {
		$("#ddLgdLBDistPDestList1_error").show();
		return false;
	}
	$("#ddLgdLBDistPDestList1_error").hide();
	return true;
}

function checkforOnlyFull(ddId) {
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
	if ((countPART >= 1 && countFULL >= 0) || countFULL > 1) {
		return true;
	}

	else {
		return false;
	}
	return false;
}

function checkforOnlyPart(ddId) {
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

function validateDistrictCoveredAreaAtDistrictLevel() {
	if (document.getElementById('divLgdLBDistCArea').style.display == 'block') {

		var ddLgdLBDistPDestList = document.getElementById('ddLgdLBDistPDestList');

		if (checkforOnlyPart(ddLgdLBDistPDestList)) {
			var ddLgdLBDistCAreaDestL = document.getElementById('ddLgdLBDistCAreaDestL');

			if (ddLgdLBDistCAreaDestL.options.length == 0) {
				$("#ddLgdLBDistCAreaDestL_error").show();
				return false;
			}
			$("#ddLgdLBDistCAreaDestL_error").hide();

			return true;
		} else {
			$("#ddLgdLBDistCAreaDestL_error").hide();
			return true;
		}

		$("#ddLgdLBDistCAreaDestL_error").hide();
		return true;
	} else {
		$("#ddLgdLBDistCAreaDestL_error").hide();
		return true;
	}
}

function validateSubDistrictCoveredAreaAtDCA() {
	if (document.getElementById('divLgdLBDistCArea').style.display == 'block') {

		var ddLgdLBDistCAreaDestL = document.getElementById('ddLgdLBDistCAreaDestL');

		if (checkforOnlyPart(ddLgdLBDistCAreaDestL)) {
			var ddLgdLBSubDistrictDestLatDCA = document.getElementById('ddLgdLBSubDistrictDestLatDCA');

			if (ddLgdLBSubDistrictDestLatDCA.options.length == 0) {
				$("#ddLgdLBSubDistrictDestLatDCA_error").show();
				return false;
			}
			$("ddLgdLBSubDistrictDestLatDCA_error").hide();

			return true;
		} else {
			$("#ddLgdLBSubDistrictDestLatDCA_error").hide();
			return true;
		}

		$("#ddLgdLBSubDistrictDestLatDCA_error").hide();
		return true;
	} else {
		$("#ddLgdLBSubDistrictDestLatDCA_error").hide();
		return true;
	}

}

function validateSubDistrictCoveredAreaAtICA() {
	if (document.getElementById('divLgdLBInterCArea').style.display == 'block') {

		var ddLgdLBInterPDestList = document.getElementById('ddLgdLBInterPDestList');

		if (checkforOnlyPart(ddLgdLBInterPDestList)) {
			var ddLgdLBInterCAreaDestL = document.getElementById('ddLgdLBInterCAreaDestL');

			if (ddLgdLBInterCAreaDestL.options.length == 0) {
				$("#ddLgdLBInterCAreaDestL_error").show();
				return false;
			}
			$("ddLgdLBInterCAreaDestL_error").hide();

			return true;
		} else {
			$("#ddLgdLBInterCAreaDestL_error").hide();
			return true;
		}

		$("#ddLgdLBInterCAreaDestL_error").hide();
		return true;
	} else {
		$("#ddLgdLBInterCAreaDestL_error").hide();
		return true;
	}
}

function validateVillageCoverdAreaAtDCA() {
	if (document.getElementById('divLgdLBDistCArea').style.display == 'block') {

		var ddLgdLBSubDistrictDestLatDCA = document.getElementById('ddLgdLBSubDistrictDestLatDCA');

		if (checkforOnlyPart(ddLgdLBSubDistrictDestLatDCA)) {
			var ddLgdLBVillageDestLatDCA = document.getElementById('ddLgdLBVillageDestLatDCA');

			if (ddLgdLBVillageDestLatDCA.options.length == 0) {
				$("#ddLgdLBVillageDestLatDCA_error").show();
				return false;
			}
			$("ddLgdLBVillageDestLatDCA_error").hide();

			return true;
		} else {
			$("#ddLgdLBVillageDestLatDCA_error").hide();
			return true;
		}

		$("#ddLgdLBVillageDestLatDCA_error").hide();
		return true;
	} else {
		$("#ddLgdLBVillageDestLatDCA_error").hide();
		return true;
	}
}

function validateVillageCoverdAreaAtICA() {
	if (document.getElementById('divLgdLBInterCArea').style.display == 'block') {

		var ddLgdLBInterCAreaDestL = document.getElementById('ddLgdLBInterCAreaDestL');

		if (checkforOnlyPart(ddLgdLBInterCAreaDestL)) {
			var ddLgdLBVillageDestLatICA = document.getElementById('ddLgdLBVillageDestLatICA');

			if (ddLgdLBVillageDestLatICA.options.length == 0) {
				$("#ddLgdLBVillageDestLatICA_error").show();
				return false;
			}
			$("ddLgdLBVillageDestLatICA_error").hide();

			return true;
		} else {
			$("#ddLgdLBVillageDestLatICA_error").hide();
			return true;
		}

		$("#ddLgdLBVillageDestLatICA_error").hide();
		return true;
	} else {
		$("#ddLgdLBVillageDestLatICA_error").hide();
		return true;
	}
}

function validateVillageCoverdAreaAtVCA() {
	if (document.getElementById('divLgdLBVillageCArea').style.display == 'block') {

		var ddLgdLBVillageDestAtVillageCA = document.getElementById('ddLgdLBVillageDestAtVillageCA');

		if (checkforOnlyPart(ddLgdLBVillageDestAtVillageCA)) {
			var ddLgdLBVillageCAreaDestL = document.getElementById('ddLgdLBVillageCAreaDestL');

			if (ddLgdLBVillageCAreaDestL.options.length == 0) {
				$("#ddLgdLBVillageCAreaDestL_error").show();
				return false;
			} else {
				$("#ddLgdLBVillageCAreaDestL_error").hide();
				return true;
			}
		} else {
			$("#ddLgdLBVillageCAreaDestL_error").hide();
			return true;
		}
	} else {
		$("#ddLgdLBVillageCAreaDestL_error").hide();
		return true;
	}
}

function validateIntermediatePanchayatList() {
	if (document.getElementById('divLgdLBInterCArea').style.display == 'block') {

		var ddLgdLBInterPDestList = document.getElementById('ddLgdLBInterPDestList');

		if (ddLgdLBInterPDestList.options.length <= 0) {
			$("#ddLgdLBInterPDestList_error").show();
			return false;
		}
		$("#ddLgdLBInterPDestList_error").hide();
		return true;
	}
}

function validateIntermediatePListforFULLandPART() {
	var ddLgdLBDistPDestList = document.getElementById('ddLgdLBInterPDestList');
	if (!checkforFULLandPART(ddLgdLBDistPDestList)) {
		$("#ddLgdLBInterPDestList1_error").show();
		return false;
	}
	$("#ddLgdLBInterPDestList1_error").hide();
	return true;
}

function validateVillagePanchayatList() {
	if (document.getElementById('divLgdLBVillageCArea').style.display == 'block') {

		var ddLgdLBVillageDestAtVillageCA = document.getElementById('ddLgdLBVillageDestAtVillageCA');

		if (ddLgdLBVillageDestAtVillageCA.options.length <= 0) {
			$("#ddLgdLBVillageDestAtVillageCA_error").show();
			return false;
		}

		$("#ddLgdLBVillageDestAtVillageCA_error").hide();
		return true;
	}
}

function validateVillagePListforFULLandPART() {
	var ddLgdLBVillageDestAtVillageCA = document.getElementById('ddLgdLBVillageDestAtVillageCA');
	if (!checkforFULLandPART(ddLgdLBVillageDestAtVillageCA)) {
		$("#ddLgdLBVillageDestAtVillageCA1_error").show();
		return false;
	}
	$("#ddLgdLBVillageDestAtVillageCA1_error").hide();
	return true;
}

/*
 * function validateUrbanLocalbodyAll() {
 * 
 * var msg = null; selectALLUrban();
 * 
 * if (!validateLocalNameInEng()) {
 * 
 * msg = "Please Enter Name of New Local Body in English" + '\n'; }
 * 
 * if (!validateLBtypeName()) { if (msg != null) { msg = msg + "Please Select
 * Local Body Type" + '\n'; } else { msg = "Please Select Local Body Type" +
 * '\n'; } } var ddLgdLBType = document.getElementById('ddLgdLBType');
 * 
 * 
 * if (!validateCheckbox()) { if (msg != null) { msg = msg + "Please check at
 * least one checkbox" + '\n'; } else { msg = "Please check at least one
 * checkbox" + '\n'; } }
 * 
 * var chkLgdLBExistChk = document.getElementById('chkLgdLBExistChk');
 * 
 * if(chkLgdLBExistChk.checked) {
 * 
 * if (!validateUrbanLocalBodyList()) { if (msg != null) { msg = msg + "Please
 * Select at least one Urban Local Body to convert" + '\n'; } else { msg =
 * "Please Select at least one Urban Local Body to convert" + '\n'; } } else
 * if(!validateUrbanLocalBodyforFULLandPART()) { if (msg != null) { msg = msg +
 * "Please Select minimum one PART/one FULL and one PART/Two FULL" + '\n'; }
 * else { msg = "Please Select minimum one PART/one FULL and one PART/Two FULL" +
 * '\n'; } }
 * 
 * if(!validateCoveredAreaforUrban()) { if (msg != null) { msg = msg + "Please
 * Select at least one Sub district to convert" + '\n'; } else { msg = "Please
 * Select at least one Sub district to convert" + '\n'; } } }
 * 
 * if (msg != null) { alert(msg); return false; } else { return true; } return
 * false; }
 */

function validateUrbanLocalbodyAll() {
	var msg = null;

	var chkExist = document.getElementById('chkLgdLBExistChk');
	var chkLgdLBUnmapped = document.getElementById('chkLgdLBUnmapped');
	if (chkExist.checked) {
		var selObj = document.getElementById('ddLgdUrbanLBSubdistrictListDest');
		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
		}
		var selObj1 = document.getElementById('ddLgdUrbanLBDistExistDest');
		for ( var i = 0; i < selObj1.options.length; i++) {
			selObj1.options[i].selected = true;
		}
		var selObj2 = document.getElementById('ddLgdSubDestListhiddenULB');
		for ( var i = 0; i < selObj2.options.length; i++) {
			selObj2.options[i].selected = true;
		}

	}

	if (chkLgdLBUnmapped.checked) {
		// alert("hello"+chkLgdLBUnmapped.checked);
		var selObj = document.getElementById('ddLgdUrbanLBDistUmappedDest');
		// alert("hhh"+selObj);
		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
		}
		/*
		 * var selObj1 =
		 * document.getElementById('ddLgdUrbanLBSubdistrictLUmappedDest'); for (
		 * 
		 * r i = 0; i < selObj1.options.length; i++) { selObj1.options[i].selec =
		 * true; }
		 */
	}
	// selectALLUrban();
	// var ddLgdLBType = document.getElementById('ddLgdLBType');
	var errors = new Array();
	var error = false;
	errors[0] = !validateLocalNameInEng();
	errors[1] = !validateLBtypeName();

	var ddLgdLBType = document.getElementById('ddLgdLBType');
	errors[2] = !validateCheckbox();
	var chkLgdLBExistChk = document.getElementById('chkLgdLBExistChk');

	errors[3] = !validateUrbanPanchayatList();

	if (chkLgdLBUnmapped.checked) {
		errors[3] = !validateLBUnmappedSubDistrictUrban();
	}

	// if(chkLgdLBExistChk.checked){
	// errors[3] = !validateUrbanLocalBodyList();

	// errors[4] = !validateUrbanLocalBodyforFULLandPART();
	// errors[5] = !validateCoveredAreaforUrban();
	// }
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
		return true;
	}
}

function validateLBUnmappedSubDistrictUrban() {

	var value = true;
	var ddlgdLBUnmappedSubDistrictAtUrban = document.getElementById('ddLgdUrbanLBDistUmappedDest');
	if (document.getElementById('divLgdLBVUmapped').style.display == 'block') {
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

function validateUrbanPanchayatList() {
	var value = true;
	var ddLgdUrbanList = document.getElementById('ddLgdUrbanLBDistExistDest');
	if (document.getElementById('divLgdLBVExist').style.display == 'block') {

		if (ddLgdUrbanList.options.length <= 0) {
			$("#ddLgdUrbanLBDistExistDest_error").show();
			value = false;
		} else if (ddLgdUrbanList.selectedIndex == -1) {
			$("#ddLgdUrbanLBDistExistDest_error").show();
			value = false;
		}
		if (ddLgdUrbanList.options.length == 1) {
			if (checkforOnlyFull(ddLgdUrbanList)) {
				$("#ddLgdUrbanLBDistExistDest1_error").show();
				value = false;
			} else {
				$("#ddLgdUrbanLBDistExistDest1_error").hide();
			}
		} else {
			$("#ddLgdUrbanLBDistExistDest1_error").hide();
		}
	}
	return value;
}

function validateUrbanLocalBodyList() {
	if (document.getElementById('divLgdLBVExist').style.display == 'block') {
		var ddLgdUrbanLBDistExistDest = document.getElementById('ddLgdUrbanLBDistExistDest');

		if (ddLgdUrbanLBDistExistDest.options.length <= 0) {
			$("#ddLgdUrbanLBDistExistDest_error").show();
			return false;
		}

		$("#ddLgdUrbanLBDistExistDest_error").hide();
		return true;
	} else {
		$("#ddLgdUrbanLBDistExistDest_error").hide();
		return true;
	}
}

function validateUrbanLocalBodyforFULLandPART() {
	var ddLgdUrbanLBDistExistDest = document.getElementById('ddLgdUrbanLBDistExistDest');
	if (!checkforFULLandPART(ddLgdUrbanLBDistExistDest)) {
		$("#ddLgdUrbanLBDistExistDest1_error").show();
		return false;
	}
	$("#ddLgdUrbanLBDistExistDest1_error").hide();
	return true;
}

function validateCoveredAreaforUrban() {
	if (document.getElementById('divLgdLBVExist').style.display == 'block') {

		var ddLgdUrbanLBDistExistDest = document.getElementById('ddLgdUrbanLBDistExistDest');

		if (checkforOnlyPart(ddLgdUrbanLBDistExistDest)) {
			var ddLgdUrbanLBSubdistrictListDest = document.getElementById('ddLgdUrbanLBSubdistrictListDest');

			if (ddLgdUrbanLBSubdistrictListDest.options.length == 0) {
				$("#ddLgdUrbanLBSubdistrictListDest_error").show();
				return false;
			} else {
				$("#ddLgdUrbanLBSubdistrictListDest_error").hide();
				return true;
			}
		} else {
			$("#ddLgdUrbanLBSubdistrictListDest_error").hide();
			return true;
		}
	} else {
		$("#ddLgdUrbanLBSubdistrictListDest_error").hide();
		return true;
	}
}
