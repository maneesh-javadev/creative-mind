t = 4;
var dynstart = 0;
var dynend = 0;
var earlyChecked = '';
function hideShowDivforLocalBody(id, districtCode, lbtype) {
	var st = "0";
	dwr.util.removeAllOptions('ddlgdLBDistrictAtVillage');
	dwr.util.removeAllOptions('ddlgdLBDistAtInter');
	st = document.getElementById('ddlgdLBDistrictAtVillage');
	st.add(new Option('Select', '0'));
	st = document.getElementById('ddlgdLBDistAtInter');
	st.add(new Option('Select', '0'));
	if (id != 0) {
		var temp = id.split(":");

		var id1 = temp[0];
		var id2 = temp[1];
		var id3 = temp[2];
		var id4 = temp[3];
		var id5 = temp[4];
		document.getElementById('levelcheck').value = id2;

		var stateCode = dwr.util.getValue('hdnStateCode');
		var chkExist = document.getElementById('chkLgdLBExistChk');
		var chkLgdLBUnmapped = document.getElementById('chkLgdLBUnmapped');
		getLBSubTypeList(id1);

		document.getElementById('flagCode').value = id1;
		// document.getElementById('cattype').value=category;
		document.getElementById('level').value = "";
		// var statecode=parseInt(id3);
		// remove_error(0);
		document.getElementById('divLgdSelIntermediateP').style.visibility = 'hidden';
		document.getElementById('divLgdSelIntermediateP').style.display = 'none';
		document.getElementById('divLgdVillageP').style.display = 'none';
		document.getElementById('divLgdVillageP').style.visibility = 'hidden';
		document.getElementById('divLgdVillagePanch').style.display = 'none';
		document.getElementById('divLgdVillagePanch').style.visibility = 'hidden';
		if (id2 == 'D') {
			// document.getElementById('divLgdSelIntermediateP').style.visibility
			// = 'none';
			document.getElementById('divLgdSelIntermediateP').style.display = 'none';
			document.getElementById('divLgdVillageP').style.display = 'none';
			document.getElementById('divLgdVillageP').style.visibility = 'hidden';
			document.getElementById('divLgdVillagePanch').style.display = 'none';
			document.getElementById('divLgdVillagePanch').style.visibility = 'hidden';
			document.getElementById("firstlevel").innerHTML = " " + id3;
			getMapUploadOptionLBody(id2, lbtype);
			// document.getElementById("errSelectVIP").innerHTML="Select "+id3;
			/*
			 * document.getElementById('tr_village_dist').style.display =
			 * 'block';
			 * document.getElementById('tr_village_dist').style.visibility='visible';
			 */
			if (id5 == 0) {
				// getdisPanchayatListforDP(stateCode,id1);
				if (districtCode == 0) {
					getDistrictPanchayatListforDPNewLBody(id1, stateCode);
				}
				if (districtCode != 0) {
					getLandRegionWiseDis(id1, id2, districtCode, lbtype);
				}
				var idDist = id3;
				var fieldId = 'districtPanchAvail';
				var fieldId1 = 'districtPanchContri';
				dwr.util.setValue(fieldId, idDist, {
					escapeHtml : false
				});
				dwr.util.setValue(fieldId1, idDist, {
					escapeHtml : false
				});

			}
		} else if (id2 == 'I') {
			document.getElementById('level').value = 'I';
			document.getElementById("secondlevel").innerHTML = " " + id3;
			// document.getElementById("errSelectVDP").innerHTML="Select "+id3;
			/*
			 * document.getElementById('tr_village_inter').style.display =
			 * 'block';
			 * document.getElementById('tr_village_inter').style.visibility='visible';
			 */
			dwr.util.removeAllOptions('ddlgdLBDistrictAtVillage');
			getMapUploadOptionLBody(id2, lbtype);
			if (id5 == 0) {
				getdisInterPanchayatListforIP(stateCode, id1);
			} else {
				getLocalBodyListbylblcCodeF(stateCode, id5);
			}

			var idInter = id3;
			var fieldId = 'interPanchAvail';
			var fieldId1 = 'interPanchContri';
			dwr.util.setValue(fieldId, idInter, {
				escapeHtml : false
			});
			dwr.util.setValue(fieldId1, idInter, {
				escapeHtml : false
			});
		} else if (id2 == 'V') {
			// alert("idv"+id);
			// alert(id4);
			document.getElementById("thirdlevel").innerHTML = " " + id3;
			// document.getElementById("errSelectVVP").innerHTML="Select "+id3;
			/*
			 * document.getElementById('tr_village_pan').style.display =
			 * 'block';
			 * document.getElementById('tr_village_pan').style.visibility='visible';
			 */
			dwr.util.removeAllOptions('ddLgdLBVillageSourceAtVillageCA');
			getMapUploadOptionLBody(id2, lbtype);
			if (id5 == 0) {
				getdisVillagePanchayatListforVP(stateCode, id1);
			} else {
				getLocalBodyListbylblcCodeF(stateCode, id5);
			}

			var idVill = id3;
			var fieldId = 'villagePanchAvail';
			var fieldId1 = 'villagePanchContri';
			dwr.util.setValue(fieldId, idVill, {
				escapeHtml : false
			});
			dwr.util.setValue(fieldId1, idVill, {
				escapeHtml : false
			});

		}
		if (chkExist.checked) {
			getHideLocalBodyParentListChk(id, chkExist.checked);
		}
		if (chkLgdLBUnmapped.checked) {
			getHideUnmappedListChk(id, chkLgdLBUnmapped.checked);
		}
	} else {
		document.getElementById('divLgdSelIntermediateP').style.visibility = 'hidden';
		document.getElementById('divLgdSelIntermediateP').style.display = 'none';
		document.getElementById('divLgdVillageP').style.display = 'none';
		document.getElementById('divLgdVillageP').style.visibility = 'hidden';
		document.getElementById('divLgdVillagePanch').style.display = 'none';
		document.getElementById('divLgdVillagePanch').style.visibility = 'hidden';
	}

}

function hideShowDivforLocalBodyTradView(id, districtCode, lbtype) {

	var st = "0";
	$("#ddlgdLBDistrictAtVillage_error").hide();
	$("#ddlgdLBDistAtInter_error").hide();
	// dwr.util.removeAllOptions('ddlgdLBDistrictAtVillage');
	// dwr.util.removeAllOptions('ddlgdLBDistAtInter');
	// st = document.getElementById('ddlgdLBDistrictAtVillage');
	// st.add(new Option('Select', '0'));
	// st = document.getElementById('ddlgdLBDistAtInter');
	// st.add(new Option('Select', '0'));
	if (id != 0) {
		var temp = id.split(":");

		var id1 = temp[0];
		var id2 = temp[1];
		var id3 = temp[2];
		var id4 = temp[3];
		var id5 = temp[4];
		document.getElementById('levelcheck').value = id2;

		var stateCode = dwr.util.getValue('hdnStateCode');

		document.getElementById('level').value = "";
		document.getElementById('divLgdSelIntermediateP').style.visibility = 'hidden';
		document.getElementById('divLgdSelIntermediateP').style.display = 'none';
		document.getElementById('divLgdVillageP').style.display = 'none';
		document.getElementById('divLgdVillageP').style.visibility = 'hidden';
		document.getElementById('divLgdVillagePanch').style.display = 'none';
		document.getElementById('divLgdVillagePanch').style.visibility = 'hidden';

		if (id2 == 'D') {
			// document.getElementById('divLgdSelIntermediateP').style.visibility
			// = 'none';
			document.getElementById('divLgdSelIntermediateP').style.display = 'none';
			document.getElementById('divLgdVillageP').style.display = 'none';
			document.getElementById('divLgdVillageP').style.visibility = 'hidden';
			document.getElementById('divLgdVillagePanch').style.display = 'none';
			document.getElementById('divLgdVillagePanch').style.visibility = 'hidden';
			document.getElementById("firstlevel").innerHTML = " " + id3;
			// getMapUploadOptionLBody(id2, lbtype);

			if (id5 == 0) {
				// getdisPanchayatListforDP(stateCode,id1);
				if (districtCode == 0) {
					getDistrictPanchayatListforDPNewLBody(id1, stateCode);
				}
				if (districtCode != 0) {
					getLandRegionWiseDis(id1, id2, districtCode, lbtype);
				}
				var idDist = id3;
				var fieldId = 'districtPanchAvail';
				var fieldId1 = 'districtPanchContri';
				dwr.util.setValue(fieldId, idDist, {
					escapeHtml : false
				});
				dwr.util.setValue(fieldId1, idDist, {
					escapeHtml : false
				});

			}
		} else if (id2 == 'I') {
			document.getElementById('level').value = 'I';
			document.getElementById("secondlevel").innerHTML = " " + id3;

			dwr.util.removeAllOptions('ddlgdLBDistrictAtVillage');
			// getMapUploadOptionLBody(id2, lbtype);
			if (id5 == 0) {
				getdisInterPanchayatListforIP(stateCode, id1);
			} else {
				getLocalBodyListbylblcCodeF(stateCode, id5);
			}

			var idInter = id3;
			var fieldId = 'interPanchAvail';
			var fieldId1 = 'interPanchContri';
			dwr.util.setValue(fieldId, idInter, {
				escapeHtml : false
			});
			dwr.util.setValue(fieldId1, idInter, {
				escapeHtml : false
			});
		} else if (id2 == 'V') {

			document.getElementById("thirdlevel").innerHTML = " " + id3;

			if (id5 == 0) {
				getdisVillagePanchayatListforVP(stateCode, id1);
			} else {
				getLocalBodyListbylblcCodeF(stateCode, id5);
			}

			var idVill = id3;
			var fieldId = 'villagePanchAvail';
			var fieldId1 = 'villagePanchContri';
			dwr.util.setValue(fieldId, idVill, {
				escapeHtml : false
			});
			dwr.util.setValue(fieldId1, idVill, {
				escapeHtml : false
			});

		}

	} else {
		document.getElementById('divLgdSelIntermediateP').style.visibility = 'hidden';
		document.getElementById('divLgdSelIntermediateP').style.display = 'none';
		document.getElementById('divLgdVillageP').style.display = 'none';
		document.getElementById('divLgdVillageP').style.visibility = 'hidden';
		document.getElementById('divLgdVillagePanch').style.display = 'none';
		document.getElementById('divLgdVillagePanch').style.visibility = 'hidden';
	}

}

function getHideLocalBodyParentListChk(id, checked) {

	if (id != "0") {
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];

		if (checked == true) {
			switch (id2) {
			case 'D':
				document.getElementById('divLgdLBDistCArea').style.visibility = 'visible';
				document.getElementById('divLgdLBDistCArea').style.display = 'block';
				document.getElementById('divLgdLBVillageCArea').style.display = 'none';
				document.getElementById('divLgdLBInterCArea').style.display = 'none';

				document.getElementById('divLgdSelIntermediateP').style.visibility = 'none';
				document.getElementById('divLgdSelIntermediateP').style.display = 'hidden';
				document.getElementById('divLgdVillageP').style.display = 'none';
				document.getElementById('divLgdVillageP').style.visibility = 'hidden';
				document.getElementById('divLgdVillagePanch').style.display = 'none';
				document.getElementById('divLgdVillagePanch').style.visibility = 'hidden';

				break;
			case 'I':
				document.getElementById('divLgdLBInterCArea').style.visibility = 'visible';
				document.getElementById('divLgdLBInterCArea').style.display = 'block';
				document.getElementById('divLgdLBDistCArea').style.display = 'none';
				document.getElementById('divLgdLBVillageCArea').style.display = 'none';
				break;
			case 'V':
				document.getElementById('divLgdLBVillageCArea').style.visibility = 'visible';
				document.getElementById('divLgdLBVillageCArea').style.display = 'block';
				document.getElementById('divLgdLBInterCArea').style.display = 'none';
				document.getElementById('divLgdLBDistCArea').style.display = 'none';
				break;

			default:
				document.getElementById('divLgdLBVillageCArea').style.display = 'none';
				document.getElementById('divLgdLBInterCArea').style.display = 'none';
				document.getElementById('divLgdLBDistCArea').style.display = 'none';
			}
		} else {
			document.getElementById('divLgdLBVillageCArea').style.display = 'none';
			document.getElementById('divLgdLBInterCArea').style.display = 'none';
			document.getElementById('divLgdLBDistCArea').style.display = 'none';

			removeListsMapped();

			document.getElementById("getHeadQuartersD").innerHTML = '';
			document.getElementById("getHeadQuartersT").innerHTML = '';
			document.getElementById("getHeadQuartersV").innerHTML = '';
		}
	} else {
		alert("Please First Select Type of Local Body");
		document.getElementById('divLgdLBVillageCArea').style.display = 'none';
		document.getElementById('divLgdLBInterCArea').style.display = 'none';
		document.getElementById('divLgdLBDistCArea').style.display = 'none';
	}

}

function getHideUnmappedListChk(id, checked) {
	if (id != "0") {
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];

		if (checked == true) {
			switch (id2) {
			case 'D':
				document.getElementById('divLgdLBDistCAreaUnmapped').style.visibility = 'visible';
				document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'block';
				document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
				document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
				document.getElementById('divLgdSelIntermediateP').style.visibility = 'none';
				document.getElementById('divLgdSelIntermediateP').style.display = 'hidden';
				document.getElementById('divLgdVillageP').style.display = 'none';
				document.getElementById('divLgdVillageP').style.visibility = 'hidden';
				document.getElementById('divLgdVillagePanch').style.display = 'none';
				document.getElementById('divLgdVillagePanch').style.visibility = 'hidden';

				getUnmappedAreaByType('D');
				break;
			case 'I':
				document.getElementById('divLgdLBInterCAreaUnmapped').style.visibility = 'visible';
				document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'block';
				document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
				document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
				getUnmappedAreaByType('T');
				break;
			case 'V':
				document.getElementById('divLgdLBVillageCAreaUnmapped').style.visibility = 'visible';
				document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'block';
				document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
				document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
				// getUnmappedAreaByType('V');
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

			removeListsUnMapped();

			document.getElementById("getHeadQuartersD").innerHTML = '';
			document.getElementById("getHeadQuartersT").innerHTML = '';
			document.getElementById("getHeadQuartersV").innerHTML = '';
		}

	} else {
		alert("Please First Select Type of Local Body");

		document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
		document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
		document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
	}

}

function clearLBLists() {
	$('#ddLgdLBVillageSourceAtVillageCA').empty();
	$('#ddLgdLBVillageDestAtVillageCA').empty();
	$('#ddLgdLBVillageCAreaSourceL').empty();
	$('#ddLgdLBVillageCAreaDestL').empty();
	$('#chkLgdLBExistChk').prop('checked', false);

}

function getdisPanchayatListforDP(id1, id2) {
	lgdDwrlocalBodyService.getPanchayatListbylblcCode(id1, id2, {
		callback : handledisPanchayatSuccessforDP,
		errorHandler : handledisPanchayatErrorforDP
	});
}

function handledisPanchayatSuccessforDP(data) {
	// Assigns data to result id

	var fieldId = 'ddlgdLBDistAtInter';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById(fieldId);
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handledisPanchayatErrorforDP() {
	// Show a popup message
	alert("No data found in District Panchayat!");
}

function getdisInterPanchayatListforIP(id1, id2) {
	// alert("inter"+id1+":"+id2);
	lgdDwrlocalBodyService.getPanchayatListbylblcCode(id1, id2, {
		callback : handledisInterPanchayatSuccessforIP,
		errorHandler : handledisInterPanchayatErrorforIP
	});
}

function handledisInterPanchayatSuccessforIP(data) {
	// Assigns data to result id
	// alert("ti");
	// alert(data);
	var fieldId = 'ddlgdLBDistrictAtVillage';
	var fieldId1 = 'ddLgdLBInterPSourceList';

	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);

	var st = document.getElementById('ddlgdLBDistrictAtVillage');
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
	dwr.util.addOptions(fieldId1, data, valueText, nameText);
}

function handledisInterPanchayatErrorforIP() {
	// Show a popup message
	alert("No data found in District Panchayat!");
}

function getLocalBodyListbylblcCodeF(stateCode, lblc) {

	var value = $('#gtadp').val();
	if (stateCode == 19 && (lblc == 42 || lblc == 41) && (value == "" || value == 20)) {
		$('#gtadp1').show();
		// $('#gtachildtr').show();
		lgdDwrlocalBodyService.getStateTopHierarchy(stateCode, {
			callback : getStateWbTopHierarchySuccess,
			errorHandler : handlegetStateWbTopHierarchyError
		});
	} else {

		lgdDwrDesignationService.getLocalbodyDetailbyCode(stateCode, lblc, {
			callback : handleLocalbodylblcCodeSuccessF,
			errorHandler : handleLocalbodylblcCodeErrorF
		});
	}
}

function handleLocalbodylblcCodeSuccessF(data) {
	var stateCode = dwr.util.getValue('hdnStateCode');

	var districtCode = dwr.util.getValue('districtCode');
	var lbtype = dwr.util.getValue('lbType');
	var lgdLBType = dwr.util.getValue('ddLgdLBType');
	var temp = lgdLBType.split(":");
	var id1 = temp[0];
	var id2 = temp[1];

	// var category=document.getElementById('cattype').value;
	var lblc = data[0].localBodyTypeCode;
	// alert(statecode);
	var plblc = data[0].parentTierSetupCode;
	var level = data[0].level;
	var name = data[0].nomenclatureEnglish;
	if (temp[2] != null && temp[2] == 'T') {
		document.getElementById("parent_level_hierarchy").value = level;
	}
	if (level == 'D') {
		document.getElementById("firstlevel").innerHTML = " " + name;
		// document.getElementById("errSelectVIP").innerHTML="Select "+name;
		// alert("inside1");
		if (plblc == 0) {
			// alert("inside2");
			document.getElementById('divLgdSelIntermediateP').style.display = 'block';
			document.getElementById('divLgdSelIntermediateP').style.visibility = 'visible';
			if (districtCode == 0) {
				getdisVillagePanchayatListforVP(stateCode, lblc);
			}
			if (districtCode != 0) {
				getLandRegionWiseDisFinal(id1, id2, districtCode, lbtype);
			}

		}
	}

	else if (level == 'I') {
		document.getElementById("secondlevel").innerHTML = " " + name;
		// document.getElementById("errSelectVDP").innerHTML="Select "+name;
		document.getElementById('level').value = 'I';
		if (plblc == 0) {
			document.getElementById('divLgdVillageP').style.display = 'block';
			document.getElementById('divLgdVillageP').style.visibility = 'visible';
			dwr.util.removeAllOptions('ddLgdLBInterListAtVillageCA');
			getdisInterPanchayatListforIP(stateCode, lblc);
		} else {
			document.getElementById('divLgdVillageP').style.display = 'block';
			document.getElementById('divLgdVillageP').style.visibility = 'visible';
			dwr.util.removeAllOptions('ddLgdLBInterListAtVillageCA');
			getLocalBodyListbylblcCodeF(stateCode, plblc);
		}
	}
}

function handleLocalbodylblcCodeErrorF() {
	// Show a popup message
	alert("No data found in local body by lblc!");
}

/* function start for westbengal */

function getParentLb(value) {
	// alert(value);
	var stateCode = dwr.util.getValue('hdnStateCode');
	if (value == 20) {
		lgdDwrlocalBodyService.getPanchayatListbylblcCode(stateCode, value, {
			callback : getParentLbSuccess,
			errorHandler : handledisPanchayatError
		});
	} else if (value == 1) {
		var id = $('#ddLgdLBType').val();
		// alert(id);
		var temp = id.split(":");

		var id1 = temp[0];
		var id2 = temp[1];
		var id3 = temp[2];
		var id4 = temp[3];
		var id5 = temp[4];
		lgdDwrDesignationService.getLocalbodyDetailbyCode(stateCode, id5, {
			callback : handleLocalbodylblcCodeSuccessF,
			errorHandler : handleLocalbodylblcCodeErrorF
		});

	}

}

function getParentLbSuccess(data) {
	// alert(data);
	var fieldId = 'gtachild';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById(fieldId);
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handledisPanchayatError() {
	// Show a popup message
	alert("No data found!");
}

function getStateWbTopHierarchySuccess(data) {
	var fieldId = 'gtadp';
	var valueText = 'localBodyTypeCode';
	var nameText = 'localBodyName';
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		id : "",
		select : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "id", "select");
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handlegetStateWbTopHierarchyError() {
	// Show a popup message
	alert("No data found hello!");
}

/* function for westbengal */

function getdisVillagePanchayatListforVP(id1, id2) {
	lgdDwrlocalBodyService.getPanchayatListbylblcCode(id1, id2, {
		callback : handledisVillagePanchayatSuccessforVP,
		errorHandler : handledisVillagePanchayatErrorforVP
	});
}

function getLandRegionWiseDisFinal(id1, id2, districtCode, lbtype) {
	lgdDwrlocalBodyService.getLandRegionWise(id1, id2, districtCode, lbtype, {
		callback : handledisVillagePanchayatSuccessforVP,
		errorHandler : handledisVillagePanchayatErrorforVP
	});
}

function handledisVillagePanchayatSuccessforVP(data) {
	var fieldId = 'ddlgdLBDistAtInter';
	var fieldId1 = 'ddLgdLBVillageSourceAtVillageCA';
	// var valueText = 'localBodyCode';
	// var nameText = 'localBodyNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);
	var st = document.getElementById('ddlgdLBDistAtInter');
	st.add(new Option('Select', '0'));
	// dwr.util.addOptions(fieldId, data, valueText, nameText);
	// dwr.util.addOptions(fieldId1, data, valueText, nameText);

	var options1 = $("#ddlgdLBDistAtInter");
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
	var options2 = $("#ddLgdLBVillageSourceAtVillageCA");
	$.each(data, function(key, obj) {
		var option2 = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option2).attr('disabled', 'disabled');
			$(option2).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options2.append(option2);
		} else {
			$(option2).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options2.append(option2);
		}
	});
}

function handledisVillagePanchayatErrorforVP() {
	// Show a popup message
	alert("No data found in District Panchayat!");
}

function hideShowDiv(id, val, districtCode, lbtype) {
	var stateCode = dwr.util.getValue('hdnStateCode');

	if (id != "" && id != "0") {
		var temp = id.split(":");

		var id1 = temp[0];
		var id2 = temp[1];
		var id3 = temp[2];
		var tiersetup = temp[3];
		var parenttiersetup = temp[4];

		var chkExist = document.getElementById('chkLgdLBExistChk');
		var chkLgdLBUnmapped = document.getElementById('chkLgdLBUnmapped');
		getLBSubTypeList(id1);
		getMapUploadOption(id2);

		switch (id2) {
		case 'D':
			document.getElementById('divLgdSelIntermediateP').style.display = 'none';
			document.getElementById('divLgdVillageP').style.display = 'none';
			if (districtCode != 0) {
				getLandRegionWiseDis(id1, id2, districtCode, lbtype);
			}
			if (districtCode == 0) {
				getDistrictPanchayatListforDPNewLBody(id1, stateCode);
			}

			var id = id3;
			var fieldId = 'districtPanchAvail';
			var fieldId1 = 'districtPanchContri';
			dwr.util.setValue(fieldId, id, {
				escapeHtml : false
			});
			dwr.util.setValue(fieldId1, id, {
				escapeHtml : false
			});

			break;
		case 'I':

			if (districtCode != 0) {
				getLandRegionWiseInter(id1, id2, districtCode, lbtype);
			}
			if (districtCode == 0) {
				// getDistrictPanchayatListforIPNewWard(id1,stateCode);
				getDistrictPanchayatListforIP(stateCode, lbtype, id2, tiersetup);
			}
			document.getElementById('divLgdSelIntermediateP').style.visibility = 'visible';
			document.getElementById('divLgdSelIntermediateP').style.display = 'block';
			document.getElementById('divLgdVillageP').style.display = 'none';
			// document.getElementById('divLgdlistSubTypeCode').style.display =
			// 'block';
			if (stateCode != 0) {
				getLocalBodyListStateWiseTierSet(lbtype, stateCode, parenttiersetup);
			}

			var id = id3;
			var fieldId = 'interPanchAvail';
			var fieldId1 = 'interPanchContri';
			dwr.util.setValue(fieldId, id, {
				escapeHtml : false
			});
			dwr.util.setValue(fieldId1, id, {
				escapeHtml : false
			});

			break;
		case 'V':
			if (districtCode != 0) {
				getLandRegionWiseVill(id1, id2, districtCode, lbtype);
			}
			if (districtCode == 0) {
				getDistrictPanchayatListforVP(stateCode, lbtype, id2, tiersetup);
			}
			document.getElementById('divLgdSelIntermediateP').style.display = 'none';
			document.getElementById('divLgdVillageP').style.visibility = 'visible';
			document.getElementById('divLgdVillageP').style.display = 'block';
			// document.getElementById('divLgdlistSubTypeCode').style.display =
			// 'block';
			if (stateCode != 0) {
				getLocalBodyListStateWiseTierSetVillage(lbtype, stateCode, parenttiersetup);
			}
			var id = id3;
			var fieldId = 'villagePanchAvail';
			var fieldId1 = 'villagePanchContri';
			dwr.util.setValue(fieldId, id, {
				escapeHtml : false
			});
			dwr.util.setValue(fieldId1, id, {
				escapeHtml : false
			});

			break;

		default:
			document.getElementById('divLgdSelIntermediateP').style.display = 'none';
			document.getElementById('divLgdVillageP').style.display = 'none';
			// document.getElementById('divLgdlistSubTypeCode').style.display =
			// 'block';
		}

		// if (id2 == 'I' && document.getElementById('ddlgdLBDistAtInter').value
		// == 0)
		if (id2 == 'I') {
			getVillagePanchbyIntercodeAtVCAFinal(id1);
		}

		if (document.getElementById('ddlgdLBDistrictAtVillage').value != 0) {
			getWorkOnVillagePanchayatforDistrictP(document.getElementById('ddlgdLBDistrictAtVillage').value, val);
		}

		if (chkExist.checked) {
			getHideLocalBodyParentList(id, chkExist.checked);
		}
		if (chkLgdLBUnmapped.checked) {
			getHideUnmappedList(id, chkLgdLBUnmapped.checked);
		}
	}
}

function getDistrictPanchayatListforDPNewLBody(id1, stateCode) {
	lgdDwrlocalBodyService.getPanchayatListbyStateandCategoryNewWardF(id1, stateCode, {
		callback : handleDistrictPanchayatSuccessListDP,
		errorHandler : handleDistrictErrorListDP
	});

}

function handleDistrictPanchayatSuccessListDP(data) {
	var fieldId = 'ddLgdLBDistPSourceList';
	dwr.util.removeAllOptions(fieldId);
	var options = $("#ddLgdLBDistPSourceList");
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

function handleDistrictErrorListDP() {
	alert("No data found!");
}

function getDistrictPanchayatListforIP(stateCode, lbtype, lbLevel, parentyresetupCd) {
	if (parentyresetupCd != 0) {
		lgdDwrlocalBodyService.getPanchayatListbyStateandCategoryParentInter(stateCode, lbtype, lbLevel, parentyresetupCd, {
			callback : handleDistrictPanchayatSuccessListIP,
			errorHandler : handleDistrictErrorListIP
		});
	}
	if (parentyresetupCd == 0) {
		lgdDwrlocalBodyService.getPanchayatListbyStateandCategoryParentInter(stateCode, lbtype, lbLevel, parentyresetupCd, {
			callback : handleDistrictPanchayatInterSuccessListIP,
			errorHandler : handleDistrictErrorInterListIP
		});
	}
}

function handleDistrictPanchayatSuccessListIP(data) {
	var fieldId = 'ddlgdLBDistAtInter';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddlgdLBDistAtInter');
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleDistrictErrorListIP() {
	alert("No data found!");
}

function handleDistrictPanchayatInterSuccessListIP(data) {
	var fieldId = 'ddLgdLBInterPSourceList';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddLgdLBInterPSourceList');
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleDistrictErrorInterListIP() {
	alert("No data found!");
}

function getDistrictPanchayatListforVP(stateCode, lbtype, lbLevel, parentyresetupCd) {
	if (parentyresetupCd != 0) {
		lgdDwrlocalBodyService.getPanchayatListbyStateandCategoryParentInter(stateCode, lbtype, lbLevel, parentyresetupCd, {
			callback : handleDistrictPanchayatSuccessListVP,
			errorHandler : handleDistrictErrorListVP
		});
	}
	if (parentyresetupCd == 0) {
		lgdDwrlocalBodyService.getPanchayatListbyStateandCategoryParentInter(stateCode, lbtype, lbLevel, parentyresetupCd, {
			callback : handleInterPanchayatSuccessListVP,
			errorHandler : handleInterErrorListVP
		});
	}
}

function handleDistrictPanchayatSuccessListVP(data) {
	var fieldId = 'ddlgdLBDistrictAtVillage';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddlgdLBDistrictAtVillage');
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleDistrictErrorListVP() {
	alert("No data found!");
}

function handleInterPanchayatSuccessListVP(data) {
	var fieldId = 'ddlgdLBDistrictAtVillage';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddlgdLBDistrictAtVillage');
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleInterErrorListVP() {
	alert("No data found!");
}

function getLocalBodyListStateWiseTierSet(lbtype, stateCode, parenttiersetup) {
	lgdDwrlocalBodyService.getLocalBodyListStateWiseTierSet(lbtype, stateCode, parenttiersetup, {
		callback : handleLabelDistrictIPSuccess,
		errorHandler : handleLabelDistrictIPError
	});
}

function handleLabelDistrictIPSuccess(data) {
	var fieldId = 'districtIP';

	for ( var i = 0; i < data.length; i++) {
		var labelDetails = data[i];
		var id = labelDetails.nomenclatureEnglish;
		dwr.util.setValue(fieldId, id, {
			escapeHtml : false
		});
	}
}

function handleLabelDistrictIPError() {
	alert("No data found!");
}

function getLocalBodyListStateWiseTierSetVillage(lbtype, stateCode, parenttiersetup) {

	lgdDwrlocalBodyService.getLocalBodyListStateWiseTierSet(lbtype, stateCode, parenttiersetup, {
		callback : handleLabelDistrictVPSuccess,
		errorHandler : handleLabelDistrictVPError
	});

	lgdDwrlocalBodyService.getLocalBodyListStateWiseTierSetF(lbtype, stateCode, parenttiersetup - 1, {
		callback : handleLabelInterVPSuccess,
		errorHandler : handleLabelInterVPError
	});

}

function handleLabelDistrictVPSuccess(data) {
	var fieldId = 'districtVP';

	for ( var i = 0; i < data.length; i++) {
		var labelDetails = data[i];
		var id = labelDetails.nomenclatureEnglish;
		dwr.util.setValue(fieldId, id, {
			escapeHtml : false
		});
	}
}

function handleLabelDistrictVPError() {
	alert("No data found!");
}

function handleLabelInterVPSuccess(data) {
	var fieldId = 'InterVP';

	for ( var i = 0; i < data.length; i++) {
		var labelDetails = data[i];
		var id = labelDetails.nomenclatureEnglish;
		dwr.util.setValue(fieldId, id, {
			escapeHtml : false
		});
	}
}

function handleLabelInterVPError() {
	alert("No data found!");
}

function getLandRegionWiseDis(id1, id2, districtCode, lbtype) {
	lgdDwrlocalBodyService.getLandRegionWise(id1, id2, districtCode, lbtype, {
		callback : handleLandRegionWiseSuccessDis,
		errorHandler : handleLandRegionWiseErrorDis
	});
}

function handleLandRegionWiseSuccessDis(data) {
	var fieldId = 'ddLgdLBDistPSourceList';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	// var st = document.getElementById('ddLgdLBDistPSourceList');
	// st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleLandRegionWiseErrorDis() {
	alert("No data found!");
}

function getLandRegionWiseInter(id1, id2, districtCode, lbtype) {
	lgdDwrlocalBodyService.getLandRegionWise(id1, id2, districtCode, lbtype, {
		callback : handleLandRegionWiseSuccessInter,
		errorHandler : handleLandRegionWiseErrorInter
	});

}

function handleLandRegionWiseSuccessInter(data) {
	var fieldId = 'ddlgdLBDistAtInter';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddlgdLBDistAtInter');
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleLandRegionWiseErrorInter() {
	alert("No data found!");
}

function getLandRegionWiseVill(id1, id2, districtCode, lbtype) {
	lgdDwrlocalBodyService.getLandRegionWise(id1, id2, districtCode, lbtype, {
		callback : handleLandRegionWiseSuccessVill,
		errorHandler : handleLandRegionWiseErrorVill
	});
}

function handleLandRegionWiseSuccessVill(data) {
	var fieldId = 'ddlgdLBDistrictAtVillage';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddlgdLBDistrictAtVillage');
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleLandRegionWiseErrorVill() {
	alert("No data found!");
}

function getVillagePanchbyIntercodeAtVCAFinal(id) {

	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleVillagePanchbyIntercodeAtVCAFinalSuccess,
		errorHandler : handleVillagePanchbyIntercodeAtVCAFinalError
	});
}

function handleVillagePanchbyIntercodeAtVCAFinalSuccess(data) {
	var fieldId;
	var e = document.getElementById("ddLgdLBType").value;

	var gtaChild = document.getElementById("gtachild");
	if (gtaChild != null && gtaChild.value > 0 && e.split(":")[1] == 'V') {
		fieldId = 'ddLgdLBVillageSourceAtVillageCA';
	} else {

		fieldId = 'ddLgdLBInterPSourceList';
	}
	// Assigns data to result id

	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleVillagePanchbyIntercodeAtVCAFinalError() {
	// Show a popup message
	alert("No data found!");
}

function hideShowDivModify(id) {
	if (id != "" && id != "0") {
		var temp = id.split(":");

		var id1 = temp[0];
		var id2 = temp[1];

		var chkExist = document.getElementById('chkLgdLBExistChk');
		var chkLgdLBUnmapped = document.getElementById('chkLgdLBUnmapped');
		getLBSubTypeList(id1);
		getMapUploadOption(id2);

		if (document.getElementById('selectBox').value != 0) {
			getWorkOnVillagePanchayatforDistrictP(document.getElementById('selectBox').value);
		}
		if (chkExist.checked) {
			getHideLocalBodyParentList(id, chkExist.checked);
		}
		if (chkLgdLBUnmapped.checked) {
			getHideUnmappedList(id, chkLgdLBUnmapped.checked);
		}
	}

}

function loadlocalBodyForm() {
	/*
	 * document.getElementById('divLgdSelIntermediateP').style.display = 'none';
	 * document.getElementById('divLgdVillageP').style.display = 'none';
	 * document.getElementById('divLgdlistSubTypeCode').style.display = 'none';
	 */

	var lgdtype = document.getElementById('ddLgdLBType').value;

	var temp = lgdtype.split(":");
	var id2 = temp[1];

	getMapUploadOption(id2);

	// document.getElementById('map-1').style.display = 'none';

	/*
	 * $("#ddLgdLBType_error").hide();
	 * $("#ddlgdLBDistrictAtVillage_error").hide();
	 * $("#ddlgdLBDistAtInter_error").hide();
	 * $("#ddlgdLBInterAtVillage_error").hide();
	 * $("#ddLgdLBDistCAreaDestLUmapped_error").hide();
	 * 
	 * $("#chkLgdLBExistChk_error").hide();
	 * 
	 * $("#ddLgdLBDistPDestList_error").hide();
	 * $("#ddLgdLBDistPDestList1_error").hide();
	 * $("#ddLgdLBDistCAreaDestL_error").hide();
	 * $("#ddLgdLBSubDistrictDestLatDCA_error").hide();
	 * $("#ddLgdLBVillageDestLatDCA_error").hide();
	 * 
	 * $("#ddLgdLBInterPDestList_error").hide();
	 * $("#ddLgdLBInterPDestList1_error").hide();
	 * $("#ddLgdLBVillageDestLatICA_error").hide();
	 * $("#ddLgdLBInterCAreaDestL_error").hide();
	 * 
	 * $("#ddLgdLBVillageDestAtVillageCA_error").hide();
	 * $("#ddLgdLBVillageDestAtVillageCA1_error").hide();
	 * $("#ddLgdLBVillageCAreaDestL_error").hide();
	 * 
	 * $("#localBodyNameInEn_error").hide();
	 */
	/*
	 * document.getElementById('divLgdLBDistCArea').style.display = 'none';
	 * document.getElementById('divLgdLBVillageCArea').style.display = 'none';
	 * document.getElementById('divLgdLBInterCArea').style.display = 'none';
	 * document.getElementById('divLgdLBVillageCAreaUnmapped').style.display =
	 * 'none';
	 * document.getElementById('divLgdLBInterCAreaUnmapped').style.display =
	 * 'none';
	 * document.getElementById('divLgdLBDistCAreaUnmapped').style.display =
	 * 'none';
	 */
	hideGISComponentOnLoad();
	// hideShowDiv(document.getElementById('ddLgdLBType').value);
}

function getWorkOnIntermediatePanchayat(id) {
	var st = "0";
	if (id > 0)
		getInterPanchayatbyDcodeAtICAFinal(id);
	else {
		dwr.util.removeAllOptions('ddlgdLBDistrictAtVillage');
		st = document.getElementById('ddlgdLBDistrictAtVillage');
		st.add(new Option('Select', '0'));
	}
}

function getInterPanchayatbyDcodeAtICAFinal(id) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleInterPanchayatICASuccessFinal,
		errorHandler : handleInterPanchayatICAErrorFinal
	});
}

function handleInterPanchayatICASuccessFinal(data) {
	// Assigns data to result id

	var fieldId = 'ddlgdLBDistrictAtVillage';
	var fieldId1 = 'ddLgdLBInterPSourceList';
	var fieldId2 = 'ddLgdLBVillageSourceAtVillageCA';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);
	dwr.util.removeAllOptions(fieldId2);

	var st = document.getElementById('ddlgdLBDistrictAtVillage');
	st.add(new Option('Select', '0'));

	var options1 = $("#ddlgdLBDistrictAtVillage");
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
	var options2 = $("#ddLgdLBInterPSourceList");
	$.each(data, function(key, obj) {
		var option2 = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option2).attr('disabled', 'disabled');
			$(option2).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options2.append(option2);
		} else {
			$(option2).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options2.append(option2);
		}
	});

	var options3 = $("#ddLgdLBVillageSourceAtVillageCA");
	$.each(data, function(key, obj) {
		var option3 = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option3).attr('disabled', 'disabled');
			$(option3).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options3.append(option3);
		} else {
			$(option3).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options3.append(option3);
		}
	});
}

function handleInterPanchayatICAErrorFinal() {
	// Show a popup message
	alert("No data found!");
}

function getWorkOnIntermediatePanchayatModify(id) {

	// setDistrcitPanchayatbyDcodeAtICA(id);
	getInterPanchayatbyDcodeAtICAModify(id);
}

function setDistrcitPanchayatbyDcodeAtICA(id) {
	var ddLgdLBDistListAtInterCA = document.getElementById('ddLgdLBDistListAtInterCA');
	dwr.util.setValue(ddLgdLBDistListAtInterCA, id);
}

function getIntermediatePanchayatbyDcode(id) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleIntermediatePanchayatSuccess,
		errorHandler : handleIntermediatePanchayatError
	});
}

function handleIntermediatePanchayatSuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddlgdLBInterAtVillage';
	var fieldId1 = 'ddLgdLBVillageSourceAtVillageCA';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);

	var st = document.getElementById('ddlgdLBInterAtVillage');
	// var st = document.getElementById('ddLgdLBVillageSourceAtVillageCA');
	st.add(new Option('Select Intermediate Panchayat', '0'));
	// added on 31/12/2014 for lgd impact
	var options = $("#ddlgdLBInterAtVillage");
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

	var options = $("#ddLgdLBVillageSourceAtVillageCA");
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

	// dwr.util.addOptions(fieldId, data, valueText, nameText);
	// dwr.util.addOptions(fieldId1, data, valueText, nameText);
}

function handleIntermediatePanchayatError() {
	// Show a popup message
	alert("No data found!");
}

function getWorkOnVillagePanchayatforDistrictP(id) {
	var st = "0";
	if (id > 0)
		getIntermediatePanchayatbyDcode(id);
	else {
		dwr.util.removeAllOptions('ddlgdLBInterAtVillage');
		st = document.getElementById('ddlgdLBInterAtVillage');
		st.add(new Option('Select', '0'));
	}
}

function setDistrcitPanchayatbyDcodeAtVCA(id) {
	var ddLgdLBDistListAtVillageCA = document.getElementById('ddLgdLBDistListAtVillageCA');
	dwr.util.setValue(ddLgdLBDistListAtVillageCA, id);
}

function selectALLcoveredarea() {
	var errors = new Array();
	var error = false;
	var val = document.getElementById('hdnLbTypeCode').value;

	if (val == "U") {
		var selObj = document.getElementById('ddLgdLBInterCAreaDestLUmapped');
		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
		}
		errors[0] = !validateLBUrbanCorrect();
	} else {
		var selObj = document.getElementById('ddLgdLBVillageCAreaDestLUnmapped');
		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
		}
	}
	for ( var i = 0; i < 1; i++) {

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

function validateLBUrbanCorrect() {
	var value = true;
	var ddlgdLBUrbanCorrect = document.getElementById('ddLgdLBInterCAreaDestLUmapped');

	if (ddlgdLBUrbanCorrect != null) {
		if (ddlgdLBUrbanCorrect.options.length <= 0) {
			$("#ddLgdLBInterCAreaDestLUmapped_error").show();
			$("#ddLgdLBInterCAreaDestLUmapped1_error").hide();
			value = false;
		}

		else if (ddlgdLBUrbanCorrect.selectedIndex == -1) {
			$("#ddLgdLBInterCAreaDestLUmapped_error").show();
			$("#ddLgdLBInterCAreaDestLUmapped1_error").hide();
			value = false;
		}
	}
	return value;
}

function selectALLcoveredareaforurban() {
	// alert("inside method");
	var selObj = document.getElementById('ddLgdLBInterCAreaDestLUmapped');
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}

}

// Start Load InterMediate Panchayt List At Village Level(Covered Area)
function setandgetInterPanchayatbyDcodeAtVCA(id) {
	// setInterPanchayatbyDcodeAtVCA(id);
	getVillagePanchbyIntercodeAtVCA(id);
}

function setandgetInterPanchayatbyDcodeAtVCAModify(id, lbCode) {
	// setInterPanchayatbyDcodeAtVCA(id);
	getVillagePanchbyIntercodeAtVCAforCov(id, lbCode);
}

function setInterPanchayatbyDcodeAtVCA(id) {
	var ddLgdLBInterListAtVillageCA = document.getElementById('ddLgdLBInterListAtVillageCA');
	dwr.util.setValue(ddLgdLBInterListAtVillageCA, id);

}

function getInterPanchayatbyDcodeAtVCA(id) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleInterPanchayatVCASuccess,
		errorHandler : handleInterPanchayatVCAError
	});
}

function handleInterPanchayatVCASuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddLgdLBInterListAtVillageCA';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddLgdLBInterListAtVillageCA');
	st.add(new Option('Select Intermediate Panchayat', '0'));
	var options1 = $("#ddLgdLBInterListAtVillageCA");
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

function handleInterPanchayatVCAError() {
	// Show a popup message
	alert("No data found!");
}
// END Load InterMediate Panchayt List At Village Level(Covered Area)

// Load Intermediate PAnchayat List At Intermediate Level

function getInterPanchayatbyDcodeAtICA(id) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleInterPanchayatICASuccess,
		errorHandler : handleInterPanchayatICAError
	});
}

function handleInterPanchayatICASuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddlgdLBDistrictAtVillage';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleInterPanchayatICAError() {
	// Show a popup message
	alert("No data found!");
}

// END Load InterMediate Panchayt List At Intermediate Level(Covered Area)

// Load Village Panchayat List At Village Level

/*
 * function getVillagePanchbyIntercodeAtVCA(id) {
 * lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, { callback :
 * handleVillagePanchbyIntercodeAtVCASuccess, errorHandler :
 * handleVillagePanchbyIntercodeAtVCAError }); }
 * 
 * function handleVillagePanchbyIntercodeAtVCASuccess(data) { // Assigns data to
 * result id
 * 
 * var fieldId = 'ddLgdLBVillageSourceAtVillageCA'; var valueText =
 * 'localBodyCode'; var nameText = 'localBodyNameEnglish';
 * 
 * dwr.util.removeAllOptions(fieldId); dwr.util.addOptions(fieldId, data,
 * valueText, nameText); }
 */
function handleVillagePanchbyIntercodeAtVCAError() {
	// Show a popup message
	alert("No data found!");
}

function setandgetInterPanchayatbyDcodeAtVCAforPanc(id) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleVillagePanchbyIntercodeAtVCASuccessforPanc,
		errorHandler : handleVillagePanchbyIntercodeAtVCAErrorforPanc
	});
}

function handleVillagePanchbyIntercodeAtVCASuccessforPanc(data) {
	// Assigns data to result id

	var fieldId = 'ddLgdLBVillageSourceAtVillageCA';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleVillagePanchbyIntercodeAtVCAErrorforPanc() {
	// Show a popup message
	alert("No data found!");
}

// END Load Village Panchayt List At Village Level(Covered Area)

function getHideLocalBodyParentList(id, checked) {

	if (id != "0") {
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];
		var id5 = temp[4];
		var stateCode = dwr.util.getValue('hdnStateCode');
		var gtaChild = document.getElementById("gtachild");
		if (gtaChild != null && gtaChild.value > 0) {
			if (id2 == 'I') {
				getVillagePanchbyIntercodeAtVCAFinal(gtaChild.value);
			} else {
				var gtaIntermediate = document.getElementById("gtaIntermediateid");
				getVillagePanchbyIntercodeAtVCAFinal(gtaIntermediate.value);
			}
		}

		if (checked == true) {
			switch (id2) {
			case 'D':
				document.getElementById('divLgdLBDistCArea').style.visibility = 'visible';
				document.getElementById('divLgdLBDistCArea').style.display = 'block';
				document.getElementById('divLgdLBVillageCArea').style.display = 'none';
				document.getElementById('divLgdLBInterCArea').style.display = 'none';
				break;
			case 'I':
				document.getElementById('divLgdLBInterCArea').style.visibility = 'visible';
				document.getElementById('divLgdLBInterCArea').style.display = 'block';
				document.getElementById('divLgdLBDistCArea').style.display = 'none';
				document.getElementById('divLgdLBVillageCArea').style.display = 'none';
				break;
			case 'V':
				document.getElementById('divLgdLBVillageCArea').style.visibility = 'visible';
				document.getElementById('divLgdLBVillageCArea').style.display = 'block';
				document.getElementById('divLgdLBInterCArea').style.display = 'none';
				document.getElementById('divLgdLBDistCArea').style.display = 'none';
				break;

			default:
				document.getElementById('divLgdLBVillageCArea').style.display = 'none';
				document.getElementById('divLgdLBInterCArea').style.display = 'none';
				document.getElementById('divLgdLBDistCArea').style.display = 'none';
			}
		} else {
			document.getElementById('divLgdLBVillageCArea').style.display = 'none';
			document.getElementById('divLgdLBInterCArea').style.display = 'none';
			document.getElementById('divLgdLBDistCArea').style.display = 'none';

			removeListsMapped();

			document.getElementById("getHeadQuartersD").innerHTML = '';
			document.getElementById("getHeadQuartersT").innerHTML = '';
			document.getElementById("getHeadQuartersV").innerHTML = '';
		}
	} else {
		alert("Please First Select Type of Local Body");
		document.getElementById('divLgdLBVillageCArea').style.display = 'none';
		document.getElementById('divLgdLBInterCArea').style.display = 'none';
		document.getElementById('divLgdLBDistCArea').style.display = 'none';
	}

}

function removeListsMapped() {
	var srcPDestList = document.getElementById("ddLgdLBDistPDestList");
	for ( var count = srcPDestList.options.length - 1; count >= 0; count--) {
		srcPDestList.remove(count, null);
	}

	var srcLBDistCAreaSourceL = document.getElementById("ddLgdLBDistCAreaSourceL");
	for ( var count = srcLBDistCAreaSourceL.options.length - 1; count >= 0; count--) {
		srcLBDistCAreaSourceL.remove(count, null);
	}

	var srcLBDistCAreaDestL = document.getElementById("ddLgdLBDistCAreaDestL");
	for ( var count = srcLBDistCAreaDestL.options.length - 1; count >= 0; count--) {
		srcLBDistCAreaDestL.remove(count, null);
	}

	var srcLgdLBSubDistrictSourceLatDCA = document.getElementById("ddLgdLBSubDistrictSourceLatDCA");
	for ( var count = srcLgdLBSubDistrictSourceLatDCA.options.length - 1; count >= 0; count--) {
		srcLgdLBSubDistrictSourceLatDCA.remove(count, null);
	}

	var srcLgdLBSubDistrictDestLatDCA = document.getElementById("ddLgdLBSubDistrictDestLatDCA");
	for ( var count = srcLgdLBSubDistrictDestLatDCA.options.length - 1; count >= 0; count--) {
		srcLgdLBSubDistrictDestLatDCA.remove(count, null);
	}

	var srcLgdLBVillageSourceLatDCA = document.getElementById("ddLgdLBVillageSourceLatDCA");
	for ( var count = srcLgdLBVillageSourceLatDCA.options.length - 1; count >= 0; count--) {
		srcLgdLBVillageSourceLatDCA.remove(count, null);
	}

	var srcLgdLBVillageDestLatDCA = document.getElementById("ddLgdLBVillageDestLatDCA");
	for ( var count = srcLgdLBVillageDestLatDCA.options.length - 1; count >= 0; count--) {
		srcLgdLBVillageDestLatDCA.remove(count, null);
	}

	var srcLgdLBInterPDestList = document.getElementById("ddLgdLBInterPDestList");
	for ( var count = srcLgdLBInterPDestList.options.length - 1; count >= 0; count--) {
		srcLgdLBInterPDestList.remove(count, null);
	}

	var srcLgdLBInterCAreaSourceL = document.getElementById("ddLgdLBInterCAreaSourceL");
	for ( var count = srcLgdLBInterCAreaSourceL.options.length - 1; count >= 0; count--) {
		srcLgdLBInterCAreaSourceL.remove(count, null);
	}

	var srcLgdLBInterCAreaDestL = document.getElementById("ddLgdLBInterCAreaDestL");
	for ( var count = srcLgdLBInterCAreaDestL.options.length - 1; count >= 0; count--) {
		srcLgdLBInterCAreaDestL.remove(count, null);
	}

	var srcLgdLBVillageSourceLatICA = document.getElementById("ddLgdLBVillageSourceLatICA");
	for ( var count = srcLgdLBVillageSourceLatICA.options.length - 1; count >= 0; count--) {
		srcLgdLBVillageSourceLatICA.remove(count, null);
	}

	var srcLgdLBVillageDestLatICA = document.getElementById("ddLgdLBVillageDestLatICA");
	for ( var count = srcLgdLBVillageDestLatICA.options.length - 1; count >= 0; count--) {
		srcLgdLBVillageDestLatICA.remove(count, null);
	}

	var srcLgdLBVillageDestAtVillageCA = document.getElementById("ddLgdLBVillageDestAtVillageCA");
	for ( var count = srcLgdLBVillageDestAtVillageCA.options.length - 1; count >= 0; count--) {
		srcLgdLBVillageDestAtVillageCA.remove(count, null);
	}

	var srcLgdLBVillageCAreaSourceL = document.getElementById("ddLgdLBVillageCAreaSourceL");
	for ( var count = srcLgdLBVillageCAreaSourceL.options.length - 1; count >= 0; count--) {
		srcLgdLBVillageCAreaSourceL.remove(count, null);
	}

	var srcLgdLBVillageCAreaDestL = document.getElementById("ddLgdLBVillageCAreaDestL");
	for ( var count = srcLgdLBVillageCAreaDestL.options.length - 1; count >= 0; count--) {
		srcLgdLBVillageCAreaDestL.remove(count, null);
	}

}

function getHideModNameDisturbed(id, checked) {

	if (id != "0") {
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];
		var id3 = temp[2];

		if (checked == true) {
			document.getElementById('modify_name').style.display = 'block';
			// document.getElementById('modify_parent_list').style.display =
			// 'none';
		} else {
			document.getElementById('modify_name').style.display = 'none';
		}

	} else {
		document.getElementById('modify_name').style.display = 'none';
		document.getElementById('modify_parent_list').style.display = 'none';
	}
}

function getHideModParentDisturbed(id, checked) {

	if (id != "0") {
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];
		var id3 = temp[2];

		if (checked == true) {
			document.getElementById('modify_parent_list').style.display = 'block';
		} else {
			document.getElementById('modify_parent_list').style.display = 'none';
		}

	} else {
		document.getElementById('modify_name').style.display = 'none';
		document.getElementById('modify_parent_list').style.display = 'none';
	}
}

function getHideLocalBodyParentListModify(id, checked, lbCode) {

	var stateCode = dwr.util.getValue('hdnStateCode');
	if (id != "0") {
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];
		var id3 = temp[2];
		var a = $('#localBCode').val();
		if (checked == true) {
			if (id3 != "U") {
				switch (id2) {
				case 'D':
					document.getElementById('divLgdLBDistCArea').style.display = 'block';
					document.getElementById('divLgdLBVillageCArea').style.display = 'none';
					document.getElementById('divLgdLBInterCArea').style.display = 'none';
					document.getElementById('divLgdLBUrban').style.display = 'none';
					break;
				case 'I':
					document.getElementById('divLgdLBInterCArea').style.display = 'block';
					document.getElementById('divLgdLBDistCArea').style.display = 'none';
					document.getElementById('divLgdLBVillageCArea').style.display = 'none';
					getWorkOnIntermediatePanchayatModifyChCov(document.getElementById('localBCode').value, lbCode);
					document.getElementById('divLgdLBUrban').style.display = 'none';
					break;
				case 'V':
					document.getElementById('divLgdLBVillageCArea').style.display = 'block';
					document.getElementById('divLgdLBInterCArea').style.display = 'none';
					document.getElementById('divLgdLBDistCArea').style.display = 'none';
					if (temp[4] != 0) {
						setandgetInterPanchayatbyDcodeAtVCAModify(document.getElementById('localBCode').value, lbCode);
					} else {
						setandgetInterPanchayatbyDcodeAtVCAModifyWithoutParent(stateCode, id1, lbCode);
					}
					document.getElementById('divLgdLBUrban').style.display = 'none';
					break;

				default:
					document.getElementById('divLgdLBVillageCArea').style.display = 'none';
					document.getElementById('divLgdLBInterCArea').style.display = 'none';
					document.getElementById('divLgdLBDistCArea').style.display = 'none';
					document.getElementById('divLgdLBUrban').style.display = 'none';
				}
			} else {
				document.getElementById('divLgdLBUrban').style.display = 'block';
				document.getElementById('divLgdLBVillageCArea').style.display = 'none';
				document.getElementById('divLgdLBInterCArea').style.display = 'none';
				document.getElementById('divLgdLBDistCArea').style.display = 'none';
				getULBListbyLBtypeChangeCoverage(id1, lbCode);
			}

		} else {
			document.getElementById('divLgdLBVillageCArea').style.display = 'none';
			document.getElementById('divLgdLBInterCArea').style.display = 'none';
			document.getElementById('divLgdLBDistCArea').style.display = 'none';
			document.getElementById('divLgdLBUrban').style.display = 'none';

		}

	} else {
		alert("Please First Select Type of Local Body");
		document.getElementById('divLgdLBVillageCArea').style.display = 'none';
		document.getElementById('divLgdLBInterCArea').style.display = 'none';
		document.getElementById('divLgdLBDistCArea').style.display = 'none';
		document.getElementById('divLgdLBUrban').style.display = 'none';
	}

}

function getWorkOnIntermediatePanchayatModifyChCov(id, lbCode) {

	getInterPanchayatbyDcodeAtICAModifyChCov(id, lbCode);
}

function getInterPanchayatbyDcodeAtICAModifyChCov(id, lbCode) {

	lgdDwrlocalBodyService.getchildlocalbodiesByParentcodeChangeCoverage(id, lbCode, {
		callback : handleInterPanchayatICASuccessChCov,
		errorHandler : handleInterPanchayatICAErrorChCov
	});
}

function handleInterPanchayatICASuccessChCov(data) {
	// Assigns data to result id

	var fieldId = 'ddLgdLBInterPSourceList';
	dwr.util.removeAllOptions(fieldId);
	var options = $("#ddLgdLBInterPSourceList");
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

function handleInterPanchayatICAErrorChCov() {
	// Show a popup message
	alert("No data found!");
}

function selectallDistrictName() {

	var selObj = document.getElementById('ddLgdLBDistPDestList');
	var districtList = "";
	for (i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		districtList += selObj.options[i].value + ",";

	}

	getDistrictListbydistrictCode(districtList);
}

function getDistrictListbydistrictCode(id) {

	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		lgdDwrSubDistrictServiceString.getLGBodySubDistListByString(id, {
			callback : handleSubDistrictStringSuccess,
			errorHandler : handleSubDistrictStringError
		});
	}

}

// data contains the returned value
function handleSubDistrictStringSuccess(data) {
	// Assigns data to result id

	var fieldId = 'gbSourceSubDistList';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		name : "Select Subdistrict"
	} ];
	dwr.util.addOptions(fieldId, dataq, "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleSubDistrictStringError() {
	// Show a popup message
	alert("No data found!");
}

function selectLocalBodyListAtDCA() {

	var selObj = document.getElementById('ddLgdLBDistPDestList');

	var districtCode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		districtCode += selObj.options[i].value + ",";
	}

	getCoveredLandForDistRegion(districtCode);

}
// Start Load Dist. List At Dist Pachayat Level(Covered Area)

function getCoveredLandForDistRegion(id) {

	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		lgdDwrlocalBodyService.getLocalGovtBodyforCoveredDistrictListFinalforLB(id, {
			callback : getCoveredLandForDistRegionSuccess,
			errorHandler : getCoveredLandForDistRegionError
		});
	}
}

// data contains the returned value
function getCoveredLandForDistRegionSuccess(data) {
	if (data == '') {
		alert("Coverage are not mapped for Selected Local Body. Please Map Covered Area first.");
	} else {
		var fieldId = 'ddLgdLBDistCAreaSourceL';
		dwr.util.removeAllOptions(fieldId);
		var options = $("#ddLgdLBDistCAreaSourceL");
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'disabled');
				$(option).val(obj.landRegionCode).text(obj.landRegionNameEnglish);
				options.append(option);
			} else {
				$(option).val(obj.landRegionCode).text(obj.landRegionNameEnglish);
				options.append(option);
			}
		});
	}
}

function getCoveredLandForDistRegionError() {
	// Show a popup message
	alert("No data found!");
}

function selectLocalBodyListAtDCAforModifyCoverageF() {

	var selObj = document.getElementById('ddLgdLBDistPDestList');

	var districtCode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		districtCode += selObj.options[i].value + ",";
	}

	getCoveredLandForDistRegionforCovChangeF(districtCode);

}
// Start Load Dist. List At Dist Pachayat Level(Covered Area)

function getCoveredLandForDistRegionforCovChangeF(id) {

	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		lgdDwrlocalBodyService.getLocalGovtBodyforCoveredDistrictListFinalforLBforMCov(id, {
			callback : getCoveredLandForDistRegionSuccessforMCov,
			errorHandler : getCoveredLandForDistRegionErrorforMCov
		});
	}
}

// data contains the returned value
function getCoveredLandForDistRegionSuccessforMCov(data) {
	if (data == '') {
		alert("Coverage are not mapped for Selected Local Body. Please Map Covered Area first.");
	} else {
		var fieldId = 'ddLgdLBDistCAreaSourceL';
		var fieldId1 = 'ddLgdLBDistCAreaDestL';
		var fieldId2 = 'ddLgdLBSubDistrictSourceLatDCA';
		var fieldId3 = 'ddLgdLBSubDistrictDestLatDCA';
		var fieldId4 = 'ddLgdLBVillageSourceLatDCA';
		var fieldId5 = 'ddLgdLBVillageDestLatDCA';

		dwr.util.removeAllOptions(fieldId);
		dwr.util.removeAllOptions(fieldId1);
		dwr.util.removeAllOptions(fieldId2);
		dwr.util.removeAllOptions(fieldId3);
		dwr.util.removeAllOptions(fieldId4);
		dwr.util.removeAllOptions(fieldId5);

		var options = $("#ddLgdLBDistCAreaSourceL");
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'disabled');
				$(option).val(obj.localbodyCoverage).text(obj.landRegionNameEnglish);
				options.append(option);
			} else {
				$(option).val(obj.localbodyCoverage).text(obj.landRegionNameEnglish);
				options.append(option);
			}
		});
	}
}

function getCoveredLandForDistRegionErrorforMCov() {
	// Show a popup message
	alert("No data found!");
}

// End here

// Hide Unmaaped List

function getHideUnmappedList(id, checked) {
	if (id != "0") {
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];

		if (checked == true) {
			switch (id2) {
			case 'D':
				document.getElementById('divLgdLBDistCAreaUnmapped').style.visibility = 'visible';
				document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'block';
				document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
				document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
				getUnmappedAreaByType('D');
				break;
			case 'I':
				document.getElementById('divLgdLBInterCAreaUnmapped').style.visibility = 'visible';
				document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'block';
				document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
				document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
				getUnmappedAreaByType('T');
				break;
			case 'V':
				document.getElementById('divLgdLBVillageCAreaUnmapped').style.visibility = 'visible';
				document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'block';
				document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
				document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
				// getUnmappedAreaByType('V');
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

			removeListsUnMapped();

			document.getElementById("getHeadQuartersD").innerHTML = '';
			document.getElementById("getHeadQuartersT").innerHTML = '';
			document.getElementById("getHeadQuartersV").innerHTML = '';
		}

	} else {
		alert("Please First Select Type of Local Body");

		document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
		document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
		document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
	}

}

function removeListsUnMapped() {
	var srcLgdLBDistCAreaDestLUmapped = document.getElementById("ddLgdLBDistCAreaDestLUmapped");
	for ( var count = srcLgdLBDistCAreaDestLUmapped.options.length - 1; count >= 0; count--) {
		srcLgdLBDistCAreaDestLUmapped.remove(count, null);
	}

	var srcLgdLBSubDistrictSourceLatDCAUmapped = document.getElementById("ddLgdLBSubDistrictSourceLatDCAUmapped");
	for ( var count = srcLgdLBSubDistrictSourceLatDCAUmapped.options.length - 1; count >= 0; count--) {
		srcLgdLBSubDistrictSourceLatDCAUmapped.remove(count, null);
	}

	var srcLgdLBSubDistrictDestLatDCAUmapped = document.getElementById("ddLgdLBSubDistrictDestLatDCAUmapped");
	for ( var count = srcLgdLBSubDistrictDestLatDCAUmapped.options.length - 1; count >= 0; count--) {
		srcLgdLBSubDistrictDestLatDCAUmapped.remove(count, null);
	}

	var srcLgdLBVillageSourceLatDCAUmapped = document.getElementById("ddLgdLBVillageSourceLatDCAUmapped");
	for ( var count = srcLgdLBVillageSourceLatDCAUmapped.options.length - 1; count >= 0; count--) {
		srcLgdLBVillageSourceLatDCAUmapped.remove(count, null);
	}

	var srcLgdLBVillageDestLatDCAUmapped = document.getElementById("ddLgdLBVillageDestLatDCAUmapped");
	for ( var count = srcLgdLBVillageDestLatDCAUmapped.options.length - 1; count >= 0; count--) {
		srcLgdLBVillageDestLatDCAUmapped.remove(count, null);
	}

	var srcLgdLBInterCAreaDestLUmapped = document.getElementById("ddLgdLBInterCAreaDestLUmapped");
	for ( var count = srcLgdLBInterCAreaDestLUmapped.options.length - 1; count >= 0; count--) {
		srcLgdLBInterCAreaDestLUmapped.remove(count, null);
	}

	var srcLgdLBVillageSourceLatICAUmapped = document.getElementById("ddLgdLBVillageSourceLatICAUmapped");
	for ( var count = srcLgdLBVillageSourceLatICAUmapped.options.length - 1; count >= 0; count--) {
		srcLgdLBVillageSourceLatICAUmapped.remove(count, null);
	}

	var srcLgdLBVillageDestLatICAUmapped = document.getElementById("ddLgdLBVillageDestLatICAUmapped");
	for ( var count = srcLgdLBVillageDestLatICAUmapped.options.length - 1; count >= 0; count--) {
		srcLgdLBVillageDestLatICAUmapped.remove(count, null);
	}

	var srcLgdLBVillageCAreaDestLUnmapped = document.getElementById("ddLgdLBVillageCAreaDestLUnmapped");
	for ( var count = srcLgdLBVillageCAreaDestLUnmapped.options.length - 1; count >= 0; count--) {
		srcLgdLBVillageCAreaDestLUnmapped.remove(count, null);
	}

}

function getHideUnmappedListModify(id, checked) {
	if (id != "0") {
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];
		if (checked == true) {
			switch (id2) {
			case 'D':
				document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'block';
				document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
				document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
				getUnmappedAreaByType('D');
				break;
			case 'I':
				document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'block';
				document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
				document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
				getUnmappedAreaByType('T');
				break;
			case 'V':
				document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'block';
				document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
				document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
				getUnmappedAreaByType('V');
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
/*
 * function getHideUnmappedListModifyforcoveredarea(id, checked) { if (id !=
 * "0") { var temp = id.split(":"); var id1 = temp[0]; var id2 = temp[1]; if
 * (checked == true) { switch (id2) { case 'D':
 * document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'block';
 * document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
 * document.getElementById('divLgdLBVillageCAreaUnmapped').style.display =
 * 'none'; getUnmappedAreaByTypeforcoveredarea('D'); break; case 'I':
 * document.getElementById('divLgdLBInterCAreaUnmapped').style.display =
 * 'block'; document.getElementById('divLgdLBDistCAreaUnmapped').style.display =
 * 'none'; document.getElementById('divLgdLBVillageCAreaUnmapped').style.display =
 * 'none'; getUnmappedAreaByTypeforcoveredarea('T'); break; case 'V':
 * document.getElementById('divLgdLBVillageCAreaUnmapped').style.display =
 * 'block'; document.getElementById('divLgdLBInterCAreaUnmapped').style.display =
 * 'none'; document.getElementById('divLgdLBDistCAreaUnmapped').style.display =
 * 'none'; getUnmappedAreaByTypeforcoveredarea('V'); break;
 * 
 * default:
 * document.getElementById('divLgdLBVillageCAreaUnmapped').style.display =
 * 'none'; document.getElementById('divLgdLBInterCAreaUnmapped').style.display =
 * 'none'; document.getElementById('divLgdLBDistCAreaUnmapped').style.display =
 * 'none'; } } else {
 * document.getElementById('divLgdLBVillageCAreaUnmapped').style.display =
 * 'none'; document.getElementById('divLgdLBInterCAreaUnmapped').style.display =
 * 'none'; document.getElementById('divLgdLBDistCAreaUnmapped').style.display =
 * 'none'; } } else { alert("Please First Select Type of Local Body");
 * 
 * document.getElementById('divLgdLBVillageCAreaUnmapped').style.display =
 * 'none'; document.getElementById('divLgdLBInterCAreaUnmapped').style.display =
 * 'none'; document.getElementById('divLgdLBDistCAreaUnmapped').style.display =
 * 'none'; } }
 */

function getHideUnmappedListModifyforcoveredarea(id) {
	if (id != "0") {
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];
		var id3 = temp[2];
		var checked = document.getElementById('chkLgdLBUnmapped').checked;
		if (checked == true) {
			if (id3 != "U") {
				switch (id2) {
				case 'D':
					document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'block';
					document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
					document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
					document.getElementById('divLgdLBUrbanUnmapped').style.display = 'none';
					getUnmappedAreaByTypeforcoveredarea('D');
					break;
				case 'I':
					document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'block';
					document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
					document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
					document.getElementById('divLgdLBUrbanUnmapped').style.display = 'none';
					getUnmappedAreaByTypeforcoveredarea('T');
					break;
				case 'V':
					document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'block';
					document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
					document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
					document.getElementById('divLgdLBUrbanUnmapped').style.display = 'none';
					getUnmappedAreaByTypeforcoveredarea('V');
					break;

				default:
					document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
					document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
					document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
					document.getElementById('divLgdLBUrbanUnmapped').style.display = 'none';
				}
			} else {
				document.getElementById('divLgdLBUrbanUnmapped').style.display = 'block';
				document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
				document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
				document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
				getUnmappedLBSubDPListatUrban('T');
			}
		} else {
			document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
			document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
			document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
			document.getElementById('divLgdLBUrbanUnmapped').style.display = 'none';
		}

	} else {
		alert("Please First Select Type of Local Body");

		document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
		document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
		document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
	}

}

function getUnmappedAreaByType(type) {
	if (type == 'D') {
		getUnmappedLBDPListatUmapped(type);
		// getLBDPListatPartmapped(type);
	} else if (type == 'T') {
		getUnmappedLBSubDPListat(type);
		// getSubDPListatPartmapped(type);
	} else if (type == 'V') {
		getUnmappedLBVillageList(type);
		// getVillageListatPartmapped(type);
	}
}

function getUnmappedAreaByTypeforcoveredarea(type) {
	if (type == 'D') {
		getUnmappedLBDPListatUmappedforcoveredarea(type);
		// getLBDPListatPartmapped(type);
	} else if (type == 'T') {
		getUnmappedLBSubDPListatforcoveredarea(type);
		// getSubDPListatPartmapped(type);
	} else if (type == 'V') {
		getUnmappedLBVillageListforcoveredarea(type);
		// getVillageListatPartmapped(type);
	}
}

// Get UnmappedLand Region District List
function getUnmappedLBDPListatUmapped(type) {
	var stateCode = dwr.util.getValue('hdnStateCode');
	var districtCode = dwr.util.getValue('districtCode');

	if (districtCode == 0) {
		lgdDwrlocalBodyService.getUnMapLRStaWiseList(type, stateCode, {
			callback : getUnmappedLBDPListatUmappedSuccess,
			errorHandler : getUnmappedLBDPListatUmappedError
		});
	} else if (districtCode > 0) {
		lgdDwrlocalBodyService.getUnMapLRDistWiseList(type, districtCode, {
			callback : getUnmappedLBDPListatUmappedSuccess,
			errorHandler : getUnmappedLBDPListatUmappedError
		});
	}
}

function getUnmappedLBDPListatUmappedforcoveredarea(type) {
	var stateCode = dwr.util.getValue('hdnStateCode');
	var districtCode = dwr.util.getValue('districtCode');
	if (districtCode == 0) {
		lgdDwrlocalBodyService.getUnMapLRStaWiseList(type, stateCode, {
			callback : getUnmappedLBDPListatUmappedSuccessforcoveredarea,
			errorHandler : getUnmappedLBDPListatUmappedError
		});
	} else if (districtCode > 0) {
		lgdDwrlocalBodyService.getUnMapLRDistWiseList(type, districtCode, {
			callback : getUnmappedLBDPListatUmappedSuccessforcoveredarea,
			errorHandler : getUnmappedLBDPListatUmappedError
		});
	}
}

function getUnmappedLBDPListatUmappedSuccess(data) {
	var fieldId = 'ddLgdLBDistCAreaSourceLUmapped';
	var valueText = 'landRegionCode';
	var nameText = 'localBodyNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getUnmappedLBDPListatUmappedSuccessforcoveredarea(data) {
	var fieldId = 'ddLgdLBDistCAreaSourceLUmapped';
	dwr.util.removeAllOptions("ddLgdLBDistCAreaSourceLUmapped");
	var options = $("#ddLgdLBDistCAreaSourceLUmapped");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.landRegionCode).text(obj.localBodyNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.landRegionCode).text(obj.localBodyNameEnglish);
			options.append(option);
		}
	});
}

function getUnmappedLBDPListatUmappedError() {

	alert("No data found!");
}
// Get Unmapped Land Region SubDistrict List
function getUnmappedLBSubDPListat(type) {
	var stateCode = dwr.util.getValue('hdnStateCode');
	var districtCode = dwr.util.getValue('districtCode');
	if (districtCode == 0) {
		lgdDwrlocalBodyService.getUnMapLRStaWiseList(type, stateCode, {
			callback : getUnmappedLBSubDPListatSuccess,
			errorHandler : getUnmappedLBSubDPListatError
		});
	} else if (districtCode > 0) {
		lgdDwrlocalBodyService.getUnMapLRDistWiseList(type, districtCode, {
			callback : getUnmappedLBSubDPListatSuccess,
			errorHandler : getUnmappedLBSubDPListatError
		});
	}
}

function getUnmappedLBSubDPListatforcoveredarea(type) {
	var stateCode = dwr.util.getValue('hdnStateCode');
	var districtCode = dwr.util.getValue('districtCode');
	if (districtCode == 0) {
		lgdDwrlocalBodyService.getUnMapLRStaWiseList(type, stateCode, {
			callback : getUnmappedLBSubDPListatSuccessforcoveredarea,
			errorHandler : getUnmappedLBSubDPListatError
		});
	} else if (districtCode > 0) {
		lgdDwrlocalBodyService.getUnMapLRDistWiseList(type, districtCode, {
			callback : getUnmappedLBSubDPListatSuccessforcoveredarea,
			errorHandler : getUnmappedLBSubDPListatError
		});
	}
}

function getUnmappedLBSubDPListatSuccess(data) {
	var fieldId = 'ddLgdLBInterCAreaSourceLUmapped';
	var valueText = 'landRegionCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getUnmappedLBSubDPListatSuccessforcoveredarea(data) {

	var options = $("#ddLgdLBInterCAreaSourceLUmapped");
	dwr.util.removeAllOptions("ddLgdLBInterCAreaSourceLUmapped");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.landRegionCode).text(obj.localBodyNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.landRegionCode).text(obj.localBodyNameEnglish);
			options.append(option);
		}
	});

}

function getUnmappedLBSubDPListatError() {

	alert("No data found!");
}

// Get UnmappedLand Region Village List
function getUnmappedLBVillageList(type) {
	var stateCode = dwr.util.getValue('hdnStateCode');
	var districtCode = dwr.util.getValue('districtCode');
	if (districtCode == 0) {
		lgdDwrlocalBodyService.getUnMapLRStaWiseList(type, stateCode, {
			callback : getLBVillageListatSuccess,
			errorHandler : getLBVillageListatError
		});
	} else if (districtCode > 0) {
		lgdDwrlocalBodyService.getUnMapLRDistWiseList(type, districtCode, {
			callback : getLBVillageListatSuccess,
			errorHandler : getLBVillageListatError
		});
	}
}

function getUnmappedLBVillageListforcoveredarea(type) {
	var stateCode = dwr.util.getValue('hdnStateCode');
	var districtCode = dwr.util.getValue('districtCode');
	/*
	 * if (districtCode == 0) {
	 * lgdDwrlocalBodyService.getUnMapLRStaWiseList(type, stateCode, { callback :
	 * getLBVillageListatSuccessforcoveredarea, errorHandler :
	 * getLBVillageListatError }); } else if (districtCode > 0) {
	 * lgdDwrlocalBodyService.getUnMapLRDistWiseList(type, districtCode, {
	 * callback : getLBVillageListatSuccessforcoveredarea, errorHandler :
	 * getLBVillageListatError }); }
	 */
}
function getLBVillageListatSuccessforcoveredarea(data) {
	var fieldId = 'ddLgdLBVillageCAreaSourceLUnmapped';
	var valueText = 'landRegionCode';
	var nameText = 'localBodyNameEnglish';

	// dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getLBVillageListatSuccess(data) {
	var fieldId = 'ddLgdLBVillageCAreaSourceLUnmapped';
	var valueText = 'landRegionCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getLBVillageListatError() {

	alert("No data found!");
}

// Get Partially Mapped Land Region Dist List By StateCode
function getLBDPListatPartmapped(type) {
	var stateCode = dwr.util.getValue('hdnStateCode');

	lgdDwrlocalBodyService.getPartlyMapLRStaWiseList(type, stateCode, {
		callback : getLBDPListatPartmappedSuccess,
		errorHandler : getLBDPListatPartmappedError
	});
}

function getLBDPListatPartmappedSuccess(data) {

	var fieldId = 'ddLgdLBDistCAreaSourceLUmapped';
	var valueText = 'landRegionCode';
	var nameText = 'localBodyNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getLBDPListatPartmappedError() {

	alert("No data found!");
}

// Get Partially Mapped Land Region SubDist List By StateCode
function getSubDPListatPartmapped(type) {
	var stateCode = dwr.util.getValue('hdnStateCode');

	lgdDwrlocalBodyService.getPartlyMapLRStaWiseList(type, stateCode, {
		callback : getSubDPListatPartmappedSuccess,
		errorHandler : getSubDPListatPartmappedError
	});
}

function getSubDPListatPartmappedSuccess(data) {

	var fieldId = 'ddLgdLBInterCAreaSourceLUmapped';
	var valueText = 'landRegionCode';
	var nameText = 'localBodyNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getSubDPListatPartmappedError() {

	alert("No data found!");
}

// Get Partially Mapped Land Region Village List By StateCode
function getVillageListatPartmapped(type) {
	var stateCode = dwr.util.getValue('hdnStateCode');

	lgdDwrlocalBodyService.getPartlyMapLRStaWiseList(type, stateCode, {
		callback : getVillageListatPartmappedSuccess,
		errorHandler : getVillageListatPartmappedError
	});
}

function getVillageListatPartmappedSuccess(data) {

	var fieldId = 'ddLgdLBVillageCAreaSourceLUnmapped';
	var valueText = 'landRegionCode';
	var nameText = 'localBodyNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getVillageListatPartmappedError() {

	alert("No data found!");
}

// Get UnmappedSubDistrict List By Dist Code modified by sushil
function getUnmappedLBSDPListatUmapped(type, category) {
	var selObj = document.getElementById('ddLgdLBDistCAreaDestLUmapped');

	var subdistrictCodelistUn = "";
	var subdistrictCodelistPM = new Array();
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;

		if (selObj.options[i].text.match("UNMAPPED")) {
			subdistrictCodelistUn += selObj.options[i].value + ",";
		} else if (selObj.options[i].text.match("PARTLY")) {
			// subdistrictCodelistPM += selObj.options[i].value + ",";
			subdistrictCodelistPM.push(selObj.options[i].value);
		} else {
			subdistrictCodelistUn += selObj.options[i].value + ",";
		}
	}

	if (subdistrictCodelistUn != "") {
		lgdDwrlocalBodyService.getUnmappedSubDistList(type, subdistrictCodelistUn, {
			callback : getUnmappedLBSubDPListatUmappedSuccess,
			errorHandler : getUnmappedLBSubDPListatUmappedError
		});
	}
	if (subdistrictCodelistPM != "") {
		getPartlyMappedSubDListbyDistCode(type, subdistrictCodelistPM, category);
	}

}

function getUnmappedLBSDPListatUmappedFinal(type, category, level) {
	var selObj = document.getElementById('ddLgdLBDistCAreaDestLUmapped');

	// var subdistrictCodelistUn = "";
	var subdistrictCodelistPM = new Array();
	var subdistrictCodelistUn = new Array();
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;

		if (selObj.options[i].text.match("UNMAPPED")) {
			if (selObj.options[i].value.match('PART')) {
				// subdistrictCodelistUn += selObj.options[i].value + ",";
				subdistrictCodelistUn.push(selObj.options[i].value);
			}
		} else if (selObj.options[i].text.match("PARTLY")) {
			// subdistrictCodelistPM += selObj.options[i].value + ",";
			subdistrictCodelistPM.push(selObj.options[i].value);
		} else {
			subdistrictCodelistPM += selObj.options[i].value + ",";
		}
	}
	if (subdistrictCodelistUn != "") {
		lgdDwrlocalBodyService.getUnmappedSubDistListLevelWiseFinal(type, subdistrictCodelistUn, level, {
			callback : getUnmappedLBSubDPListatUmappedSuccess,
			errorHandler : getUnmappedLBSubDPListatUmappedError
		});
	}

	if (subdistrictCodelistPM != "") {

		lgdDwrlocalBodyService.getUnmappedSubDistListLevelWiseFinal(type, subdistrictCodelistPM, level, {
			callback : getUnmappedLBSubDPListatUmappedSuccessFinal,
			errorHandler : getUnmappedLBSubDPListatUmappedError
		});

		lgdDwrlocalBodyService.getPartlyMappedSubDistOrVillListFinal(type, subdistrictCodelistPM, category, level, {
			callback : getPartlyMappedSubDListSuccessFinal,
			errorHandler : getPartlyMappedSubDListError
		});

	}
}

function getPartlyMappedSubDListbyDistCodeLevelWise(type, districtCode, category, level) {
	lgdDwrlocalBodyService.getPartlyMappedSubDistOrVillListFinal(type, districtCode, category, level, {
		callback : getPartlyMappedSubDListSuccessF,
		errorHandler : getPartlyMappedSubDListError
	});

	lgdDwrlocalBodyService.getUnmappedSubDistListLevelWiseFinal(type, districtCode, level, {
		callback : getUnmappedLBSubDPListatUmappedSuccess,
		errorHandler : getUnmappedLBSubDPListatUmappedError
	});
}

function getPartlyMappedSubDListSuccessF(data) {

	var fieldId = 'ddLgdLBSubDistrictSourceLatDCAUmapped';
	var fieldId1 = 'ddLgdLBSubDistrictDestLatDCAUmapped';
	var fieldId2 = 'ddLgdLBVillageSourceLatDCAUmapped';
	var fieldId3 = 'ddLgdLBVillageDestLatDCAUmapped';

	var valueText = 'landRegionCode';
	var nameText = 'localBodyNameEnglish';

	// dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);
	dwr.util.removeAllOptions(fieldId2);
	dwr.util.removeAllOptions(fieldId3);

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getPartlyMappedSubDListSuccessFinal(data) {

	var options = $("#ddLgdLBSubDistrictSourceLatDCAUmapped");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.landRegionCode).text(obj.localBodyNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.landRegionCode).text(obj.localBodyNameEnglish);
			options.append(option);
		}
	});
}

function getUnMappedSubDListbyDistCodeLevelWise(type, districtCode, level) {
	lgdDwrlocalBodyService.getUnmappedSubDistListLevelWiseFinal(type, districtCode, level, {
		callback : getUnmappedLBSubDPListatUmappedSuccess,
		errorHandler : getUnmappedLBSubDPListatUmappedError
	});
}

function getUnmappedLBSubDPListatUmappedSuccess(data) {

	var fieldId = 'ddLgdLBSubDistrictSourceLatDCAUmapped';
	dwr.util.removeAllOptions(fieldId);

	var options = $("#ddLgdLBSubDistrictSourceLatDCAUmapped");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.landRegionCode).text(obj.localBodyNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.landRegionCode).text(obj.localBodyNameEnglish);
			options.append(option);
		}
	});
}

function getUnmappedLBSubDPListatUmappedSuccessFinal(data) {

	dwr.util.removeAllOptions("ddLgdLBSubDistrictSourceLatDCAUmapped");
	var options = $("#ddLgdLBSubDistrictSourceLatDCAUmapped");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.landRegionCode).text(obj.localBodyNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.landRegionCode).text(obj.localBodyNameEnglish);
			options.append(option);
		}
	});
}

function getUnmappedLBSubDPListatUmappedError() {
	alert("No data found!");
}

// Get Partly Mapped Subdistrict List By District Code
function getPartlyMappedSubDListbyDistCode(type, districtCode, category) {
	lgdDwrlocalBodyService.getPartlyMappedSubDistOrVillList(type, districtCode, category, {
		callback : getPartlyMappedSubDListSuccess,
		errorHandler : getPartlyMappedSubDListError
	});
}

function getPartlyMappedSubDListSuccess(data) {
	var fieldId = 'ddLgdLBSubDistrictSourceLatDCAUmapped';
	var fieldId1 = 'ddLgdLBSubDistrictDestLatDCAUmapped';
	var fieldId2 = 'ddLgdLBVillageSourceLatDCAUmapped';
	var fieldId3 = 'ddLgdLBVillageDestLatDCAUmapped';

	var valueText = 'landRegionCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);
	dwr.util.removeAllOptions(fieldId2);
	dwr.util.removeAllOptions(fieldId3);

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getPartlyMappedSubDListError() {
	alert("No data found!");
}

function getUnmappedLBVillPListatUmappedFinal(type, category, level) {

	var selObj = document.getElementById('ddLgdLBSubDistrictDestLatDCAUmapped');
	var subdistrictCodelistPM = new Array();
	var subdistrictCodelistUn = new Array();
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;

		if (selObj.options[i].text.match("UNMAPPED")) {
			if (selObj.options[i].value.match('PART')) {
				// subdistrictCodelistUn += selObj.options[i].value + ",";
				subdistrictCodelistUn.push(selObj.options[i].value);
			}
		} else if (selObj.options[i].text.match("PARTLY")) {
			// subdistrictCodelistPM += selObj.options[i].value + ",";
			subdistrictCodelistPM.push(selObj.options[i].value);
		} else {
			subdistrictCodelistPM.push(selObj.options[i].value);
		}
	}

	if (subdistrictCodelistUn != "") {
		lgdDwrlocalBodyService.getUnmappedSubDistListLevelWiseFinal(type, subdistrictCodelistUn, level, {
			callback : getUnmappedLBVillPListatUmappedSuccess,
			errorHandler : getUnmappedLBVillPListatUmappedError
		});
	}

	if (subdistrictCodelistPM != "") {
		// getPartlyMappedVillageListbySDistCodeF(type, subdistrictCodelistPM,
		// category, level);
		// getUnmappedVillageListBySDistFinal(type, subdistrictCodelistPM,
		// level);

		lgdDwrlocalBodyService.getPartlyMappedSubDistOrVillListFinal(type, subdistrictCodelistPM, category, level, {
			callback : getPartlyMappedVillageListSuccess,
			errorHandler : getPartlyMappedVillageListError
		});

		lgdDwrlocalBodyService.getUnmappedSubDistListLevelWiseFinal(type, subdistrictCodelistPM, level, {
			callback : getUnmappedLBVillPListatUmappedSuccess,
			errorHandler : getUnmappedLBVillPListatUmappedError
		});

	}
}

function getPartlyMappedVillageListbySDistCodeF(type, subdistrictCode, category, level) {

	lgdDwrlocalBodyService.getPartlyMappedSubDistOrVillListFinal(type, subdistrictCode, category, level, {
		callback : getPartlyMappedVillageListSuccess,
		errorHandler : getPartlyMappedVillageListError
	});

	lgdDwrlocalBodyService.getUnmappedSubDistListLevelWiseFinal(type, subdistrictCode, level, {
		callback : getUnmappedLBVillPListatUmappedSuccess,
		errorHandler : getUnmappedLBVillPListatUmappedError
	});
}

function getUnmappedVillageListBySDistFinal(type, subdistrictCode, level) {

	lgdDwrlocalBodyService.getUnmappedSubDistListLevelWiseFinal(type, subdistrictCode, level, {
		callback : getUnmappedLBVillPListatUmappedSuccess,
		errorHandler : getUnmappedLBVillPListatUmappedError
	});
}

// Get Unmapped VillageList By SubDist Code
function getUnmappedLBVillPListatUmapped(type, category) {

	var selObj = document.getElementById('ddLgdLBSubDistrictDestLatDCAUmapped');
	var subdistrictCodelistUn = "";
	var subdistrictCodelistPM = new Array();
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;

		if (selObj.options[i].text.match("UNMAPPED")) {
			subdistrictCodelistUn += selObj.options[i].value + ",";
		} else if (selObj.options[i].text.match("PARTLY")) {
			// subdistrictCodelistPM += selObj.options[i].value + ",";
			subdistrictCodelistPM.push(selObj.options[i].value);
		} else {
			subdistrictCodelistPM.push(selObj.options[i].value);
		}
	}

	if (subdistrictCodelistUn != "") {
		lgdDwrlocalBodyService.getUnmappedSubDistOrVillList(type, subdistrictCodelistUn, {
			callback : getUnmappedLBVillPListatUmappedSuccess,
			errorHandler : getUnmappedLBVillPListatUmappedError
		});
	}

	if (subdistrictCodelistPM != "") {
		getPartlyMappedVillageListbySDistCode(type, subdistrictCodelistPM, category);
	}
}

function getUnmappedLBVillPListatUmappedSuccess(data) {

	var options = $("#ddLgdLBVillageSourceLatDCAUmapped");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.landRegionCode).text(obj.localBodyNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.landRegionCode).text(obj.localBodyNameEnglish);
			options.append(option);
		}
	});
}

function getUnmappedLBVillPListatUmappedError() {
	alert("No data found!");
}

// Get Partly Mapped Village List By SubDistrict Code At District panchayt case
function getPartlyMappedVillageListbySDistCode(type, subdistrictCode, category) {

	lgdDwrlocalBodyService.getPartlyMappedSubDistOrVillList(type, subdistrictCode, category, {
		callback : getPartlyMappedVillageListSuccess,
		errorHandler : getPartlyMappedVillageListError
	});
}

function getPartlyMappedVillageListSuccess(data) {
	var options = $("#ddLgdLBVillageSourceLatDCAUmapped");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.landRegionCode).text(obj.localBodyNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.landRegionCode).text(obj.localBodyNameEnglish);
			options.append(option);
		}
	});
}

function getPartlyMappedVillageListError() {
	alert("No data found!");
}

function selectLocalBodyListAtICAforChCoverage() {
	var selObj = document.getElementById('ddLgdLBInterPDestList');

	var subDistrictCode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		subDistrictCode += selObj.options[i].value + ",";
	}

	getCoveredLandForSubDistRegionforCov(subDistrictCode);
}

// Get Covered Land Region For Sub District

function getCoveredLandForSubDistRegionforCov(id) {
	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {

		lgdDwrlocalBodyService.getLocalGovtBodyforCoveredSubDistListFinalforCov(id, {
			callback : getSubDistSuccessChangeCov,
			errorHandler : getSubDistErrorChangeCov
		});
	}
}

function getSubDistSuccessChangeCov(data) {

	if (data == '') {
		alert("Coverage are not mapped for Selected Local Body. Please Map Covered Area first.");
	} else {
		var fieldId = 'ddLgdLBInterCAreaSourceL';
		var fieldId1 = 'ddLgdLBInterCAreaDestL';
		var fieldId2 = 'ddLgdLBVillageSourceLatICA';
		var fieldId3 = 'ddLgdLBVillageDestLatICA';
		dwr.util.removeAllOptions(fieldId);
		dwr.util.removeAllOptions(fieldId1);
		dwr.util.removeAllOptions(fieldId2);
		dwr.util.removeAllOptions(fieldId3);
		var options = $("#ddLgdLBInterCAreaSourceL");
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'disabled');
				$(option).val(obj.localbodyCoverage).text(obj.landRegionNameEnglish);
				options.append(option);
			} else {
				$(option).val(obj.localbodyCoverage).text(obj.landRegionNameEnglish);
				options.append(option);
			}
		});

	}
}

function getSubDistErrorChangeCov() {
	// Show a popup message
	alert("No data found!");
}

function selectLocalBodyListAtICA() {
	var selObj = document.getElementById('ddLgdLBInterPDestList');

	var subDistrictCode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		subDistrictCode += selObj.options[i].value + ",";
	}

	getCoveredLandForSubDistRegion(subDistrictCode);
}

// Get Covered Land Region For Sub District

function getCoveredLandForSubDistRegion(id) {
	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		lgdDwrlocalBodyService.getLocalGovtBodyforCoveredSubDistListFinal(id, {
			callback : getSubDistSuccess,
			errorHandler : getSubDistError
		});
	}
}

function getSubDistSuccess(data) {

	if (data == '') {
		alert("Coverage are not mapped for Selected Local Body. Please Map Covered Area first.");
	} else {
		var fieldId = 'ddLgdLBInterCAreaSourceL';
		var valueText = 'landRegionCode';
		var nameText = 'landRegionNameEnglish';
		dwr.util.removeAllOptions(fieldId);
		dwr.util.addOptions(fieldId, data, valueText, nameText);
	}
}

function getSubDistError() {
	// Show a popup message
	alert("No data found!");
}
// End here

function selectVillageICAFinal() {
	var selObjSDis = document.getElementById('ddLgdLBInterCAreaDestL');
	var selObjCoverg = document.getElementById('ddLgdLBInterPDestList');

	var subdistrictcode = "";
	var coveragecode = "";

	for ( var i = 0; i < selObjSDis.options.length; i++) {
		selObjSDis.options[i].selected = true;
		subdistrictcode += selObjSDis.options[i].value + ",";
	}
	for ( var i = 0; i < selObjCoverg.options.length; i++) {
		selObjCoverg.options[i].selected = true;
		coveragecode += selObjCoverg.options[i].value + ",";
	}

	getVillageICAFinal(subdistrictcode, coveragecode);
}

function getVillageICAFinal(subdistrictcode, coveragecode) {
	if (subdistrictcode == '') {
		alert("Please Select PART to get Village.");
	} else if (!subdistrictcode.match('PART')) {
		alert("Please Select PART to get Village.");
	} else {
		lgdDwrlocalBodyService.getVillageListforDCAFinal(subdistrictcode, coveragecode, {
			callback : getVillageICASuccessFinal,
			errorHandler : getVillageICAErrorFinal
		});
	}
}

function getVillageICASuccessFinal(data) {
	if (data == '') {
		var subdistrictcode = "";
		var selObjSDis = document.getElementById('ddLgdLBInterCAreaDestL');
		for ( var i = 0; i < selObjSDis.options.length; i++) {
			selObjSDis.options[i].selected = true;
			subdistrictcode += selObjSDis.options[i].value + ",";
		}
		lgdDwrlocalBodyService.getVillageListforDCA(subdistrictcode, {
			callback : getVillageforICASuccess,
			errorHandler : getVillageforICAError
		});
	}
	if (data != '') {
		var fieldId = 'ddLgdLBVillageSourceLatICA';
		var valueText = 'landRegionCode';
		var nameText = 'landRegionNameEnglish';
		dwr.util.removeAllOptions(fieldId);
		dwr.util.addOptions(fieldId, data, valueText, nameText);
	}
}

function getVillageforICASuccess(data) {
	var fieldId = 'ddLgdLBVillageSourceLatICA';
	var valueText = 'key';
	var nameText = 'value';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getVillageforICAError() {
	alert("No data found!");
}

function getVillageICAErrorFinal() {
	alert("No data found!");
}

function selectVillageICA() {
	var selObj = document.getElementById('ddLgdLBInterCAreaDestL');
	var subDistrictCode = "";
	for (i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		subDistrictCode += selObj.options[i].value + ",";
	}

	getVillageICA(subDistrictCode);
}

function getVillageICA(id) {
	if (id == '') {
		alert("Please Select PART to get Village.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Village.");
	} else {
		lgdDwrlocalBodyService.getVillageListforDCA(id, {
			callback : getVillageICASuccess,
			errorHandler : getVillageICAError
		});
	}
}

function getVillageICASuccess(data) {

	var fieldId = 'ddLgdLBVillageSourceLatICA';
	var valueText = 'key';
	var nameText = 'value';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getVillageICAError() {
	alert("No data found!");
}
// End here

function selectLocalBodyListAtVCA() {
	var selObj = document.getElementById('ddLgdLBVillageDestAtVillageCA');

	var villageCode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		villageCode += selObj.options[i].value + ",";

	}
	getCoveredLandRegionforVillage(villageCode);
}

// Get Covered Land Region getCoveredLandRegion

function getCoveredLandRegionforVillage(id) {
	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		lgdDwrlocalBodyService.getLocalGovtBodyCoveredVillageListforLB(id, {
			callback : getVillageSuccess,
			errorHandler : getVillageError
		});
	}
}

// data contains the returned value
function getVillageSuccess(data) {
	if (data == '') {
		alert("Coverage are not mapped for Selected Local Body. Please Map Covered Area first.");
	} else {
		var fieldId = 'ddLgdLBVillageCAreaSourceL';
		dwr.util.removeAllOptions(fieldId);

		var options = $("#ddLgdLBVillageCAreaSourceL");
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'disabled');
				$(option).val(obj.landRegionCode).text(obj.landRegionNameEnglish);
				options.append(option);
			} else {
				$(option).val(obj.landRegionCode).text(obj.landRegionNameEnglish);
				options.append(option);
			}
		});

	}
}

function getVillageError() {
	// Show a popup message
	alert("No data found!");
}
// End here

function selectLocalBodyListAtVCAforModifyCoverageF() {
	var selObj = document.getElementById('ddLgdLBVillageDestAtVillageCA');

	var villageCode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		villageCode += selObj.options[i].value + ",";

	}
	getCoveredLandRegionforVillageforModifyCoverageF(villageCode);
}

function getCoveredLandRegionforVillageforModifyCoverageF(id) {
	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		lgdDwrlocalBodyService.getLocalGovtBodyCoveredVillageListforLBforMCov(id, {
			callback : getVillageSuccessF,
			errorHandler : getVillageErrorF
		});
	}
}

// data contains the returned value
function getVillageSuccessF(data) {
	if (data == '') {
		alert("Coverage are not mapped for Selected Local Body. Please Map Covered Area first.");
	} else {
		var fieldId = 'ddLgdLBVillageCAreaSourceL';
		var fieldId1 = 'ddLgdLBVillageCAreaDestL';
		dwr.util.removeAllOptions(fieldId);
		dwr.util.removeAllOptions(fieldId1);
		var options = $("#ddLgdLBVillageCAreaSourceL");
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'disabled');
				$(option).val(obj.localbodyCoverage).text(obj.landRegionNameEnglish);
				options.append(option);
			} else {
				$(option).val(obj.localbodyCoverage).text(obj.landRegionNameEnglish);
				options.append(option);
			}
		});
	}
}

function getVillageErrorF() {
	// Show a popup message
	alert("No data found!");
}

function selectLocalBodyListAtVCAforModifyCoverage() {
	var selObj = document.getElementById('ddLgdLBVillageDestAtVillageCA');

	var villageCode = "";
	for (i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		villageCode += selObj.options[i].value + ",";

	}
	getCoveredLandRegionforVillageforModifyCoverage(villageCode);
}

// Get Covered Land Region getCoveredLandRegion

function getCoveredLandRegionforVillageforModifyCoverage(id) {
	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		lgdDwrlocalBodyService.getLocalGovtBodyCoveredVillageListforModifyCoverage(id, {
			callback : getVillageSuccessforModify,
			errorHandler : getVillageErrorforModify
		});
	}
}

function selectLocalBodyListAtVCAforModify() {
	var selObj = document.getElementById('ddLgdLBVillageDestAtVillageCA');

	var villageCode = "";
	for (i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		villageCode += selObj.options[i].value + ",";

	}
	getCoveredLandRegionforVillageforModify(villageCode);
}

// Get Covered Land Region getCoveredLandRegion

function getCoveredLandRegionforVillageforModify(id) {
	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		lgdDwrlocalBodyService.getLocalGovtBodyCoveredVillageListforModify(id, {
			callback : getVillageSuccessforModify,
			errorHandler : getVillageErrorforModify
		});
	}
}

// data contains the returned value
function getVillageSuccessforModify(data) {

	var fieldId = 'ddLgdLBVillageCAreaSourceL';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getVillageErrorforModify() {
	// Show a popup message
	alert("No data found!");
}

function selectDistrictAtDCA() {
	// alert("Function called");
	var selObj = document.getElementById('ddLgdWardSubDistrictUListDest');
	var districtcode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		districtcode += selObj.options[i].value + ",";

	}
	if (districtcode == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!districtcode.match('PART')) {
		// dwr.util.removeAllOptions('villageList');
		alert("Kindly Select the Part");
	} else {
		getSubDistrictListatDCA(districtcode);
		// alert(districtcode);
	}
}

function getSubDistrictListatDCA(id) {
	// alert(id);
	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		// alert(id);
		lgdDwrlocalBodyService.getLocalGovtBodyforCoveredSubDistList(id, {
			callback : getSubDistrictListforLBSuccess,
			errorHandler : getSubDistrictListforLBError
		});
	}
}

function getSubDistrictListforLBSuccess(data) {

	var fieldId = 'ddLgdLBSubDistrictSourceLatDCA';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getSubDistrictListforLBError() {
	// Show a popup message
	alert("No data found!");
}

//
function selectDistrictAtDCAUmapped() {
	var selObj = document.getElementById('ddLgdLBDistCAreaDestLUmapped');
	var districtcode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		districtcode += selObj.options[i].value + ",";

	}
	getSubDistrictListatDCAUmapped(districtcode);
}

function getSubDistrictListatDCAUmapped(id) {
	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		lgdDwrlocalBodyService.getLocalGovtBodyforUnmappedLocalBodyList('D', id, {
			callback : getSubDistrictListforLBSuccessUmapped,
			errorHandler : getSubDistrictListforLBErrorUmapped
		});
	}
}

function getSubDistrictListforLBSuccessUmapped(data) {
	var fieldId = 'ddLgdLBSubDistrictSourceLatDCAUmapped';
	var valueText = 'subdistrictCode';
	var nameText = 'subdistrictNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getSubDistrictListforLBErrorUmapped() {
	// Show a popup message
	alert("No data found!");
}

function selectSubdistrictAtDCAUnmapped(subdistrictCode) {
	var selObj = document.getElementById('ddLgdLBSubDistrictDestLatDCAUmapped');
	var subdistrictcode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		subdistrictcode += selObj.options[i].value + ",";

	}
	getVillageListatDCAUnmapped(subdistrictcode);
}

function getVillageListatDCAUnmapped(id) {

	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		lgdDwrlocalBodyService.getLocalGovtBodyCoveredVillageList(id, {
			callback : getVillageforLBSuccess,
			errorHandler : getVillageforLBError
		});
	}
}

function getVillageforLBSuccess(data) {

	var fieldId = 'ddLgdLBVillageSourceLatDCA';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getVillageforLBError() {
	// Show a popup message
	alert("No data found!");
}

function selectSubdistrictAtDCAFinal() {
	var selObjDis = document.getElementById('ddLgdLBDistCAreaDestL');
	var selObjCoverg = document.getElementById('ddLgdLBDistPDestList');

	var districtcode = "";
	var coveragecode = "";
	for ( var i = 0; i < selObjDis.options.length; i++) {
		selObjDis.options[i].selected = true;
		districtcode += selObjDis.options[i].value + ",";
	}
	for ( var i = 0; i < selObjCoverg.options.length; i++) {
		selObjCoverg.options[i].selected = true;
		coveragecode += selObjCoverg.options[i].value + ",";
	}
	getSubDistrictatDCAFinal(districtcode, coveragecode);
}

function getSubDistrictatDCAFinal(districtcode, coveragecode) {
	if (districtcode == '') {
		alert("Please Select PART to get Sub District list.");
	} else if (!districtcode.match('PART')) {
		alert("Please Select PART to get Sub District list.");
	} else {
		lgdDwrlocalBodyService.getLocalGovtBodySubDistrictListFinal(districtcode, coveragecode, {
			callback : getSubDistrictforLBSuccessFinal,
			errorHandler : getSubDistrictforLBErrorFinal
		});
	}
}

function getSubDistrictforLBSuccessFinal(data) {
	if (data == '') {
		var districtcode = "";
		var selObjDis = document.getElementById('ddLgdLBDistCAreaDestL');
		for ( var i = 0; i < selObjDis.options.length; i++) {
			selObjDis.options[i].selected = true;
			districtcode += selObjDis.options[i].value + ",";
		}

		lgdDwrlocalBodyService.getLocalGovtBodySubDistrictListForLocalBody(districtcode, {
			callback : getSubDistrictforLBSuccess,
			errorHandler : getSubDistrictatDCAError
		});
	}
	if (data != '') {
		var fieldId = 'ddLgdLBSubDistrictSourceLatDCA';
		dwr.util.removeAllOptions(fieldId);
		var options = $("#ddLgdLBSubDistrictSourceLatDCA");
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'disabled');
				$(option).val(obj.landRegionCode).text(obj.landRegionNameEnglish);
				options.append(option);
			} else {
				$(option).val(obj.landRegionCode).text(obj.landRegionNameEnglish);
				options.append(option);
			}
		});
	}
}

function getSubDistrictforLBErrorFinal() {
	// Show a popup message
	alert("No data found!");
}

function getSubDistrictatDCAError() {
	// Show a popup message
	alert("No data found!");
}

// Code added by Arnab----Start

function selectSubdistrictAtDCACovChng() {
	var selObj = document.getElementById('ddLgdLBDistCAreaDestL');
	var selObjCoverg = document.getElementById('ddLgdLBDistPDestList');
	var districtcode = "";
	var coveragecode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		districtcode += selObj.options[i].value + ",";
	}
	for ( var i = 0; i < selObjCoverg.options.length; i++) {
		selObjCoverg.options[i].selected = true;
		coveragecode += selObjCoverg.options[i].value + ",";
	}
	getSubDistrictatDCACovChng(districtcode, coveragecode);
}

function getSubDistrictatDCACovChng(districtcode, coveragecode) {
	if (districtcode == '') {
		alert("Please Select PART to get Sub District list.");
	} else if (!districtcode.match('PART')) {
		alert("Please Select PART to get Sub District list.");
	} else {
		lgdDwrlocalBodyService.getLocalGovtBodySubDistrictListFinalChangeCov(districtcode, coveragecode, {
			callback : getSubDistrictforLBSuccessCovChngFinal,
			errorHandler : getSubDistrictforLBErrorCovChngFinal
		});
	}
}

function getSubDistrictforLBSuccessCovChngFinal(data) {

	if (data == '') {
		var districtcode = "";
		var selObjDis = document.getElementById('ddLgdLBDistCAreaDestL');
		for ( var i = 0; i < selObjDis.options.length; i++) {
			selObjDis.options[i].selected = true;
			districtcode += selObjDis.options[i].value + ",";
		}

		lgdDwrlocalBodyService.getLocalGovtBodySubDistrictListChangeCover(districtcode, {
			callback : getSubDistrictforLBCovChngSuccess,
			errorHandler : getSubDistrictatDCACovChngError
		});
	}
	if (data != '') {

		var fieldId = 'ddLgdLBSubDistrictSourceLatDCA';
		var fieldId1 = 'ddLgdLBSubDistrictDestLatDCA';
		var fieldId2 = 'ddLgdLBVillageSourceLatDCA';
		var fieldId3 = 'ddLgdLBVillageDestLatDCA';
		dwr.util.removeAllOptions(fieldId);
		dwr.util.removeAllOptions(fieldId1);
		dwr.util.removeAllOptions(fieldId2);
		dwr.util.removeAllOptions(fieldId3);

		var options = $("#ddLgdLBSubDistrictSourceLatDCA");
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'disabled');
				$(option).val(obj.localbodyCoverage).text(obj.landRegionNameEnglish);
				options.append(option);
			} else {
				$(option).val(obj.localbodyCoverage).text(obj.landRegionNameEnglish);
				options.append(option);
			}
		});

	}
}

function getSubDistrictforLBErrorCovChngFinal() {
	// Show a popup message
	alert("No data found!");
}

function getSubDistrictforLBCovChngSuccess(data) {
	var fieldId = 'ddLgdLBSubDistrictSourceLatDCA';
	dwr.util.removeAllOptions(fieldId);

	var options = $("#ddLgdLBSubDistrictSourceLatDCA");
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

}

function getSubDistrictatDCACovChngError() {
	// Show a popup message
	alert("No data found!");
}

function selectVillageAtDCACovChng() {
	var selObjSDis = document.getElementById('ddLgdLBSubDistrictDestLatDCA');
	var selObjCoverg = document.getElementById('ddLgdLBDistPDestList');

	var subdistrictcode = "";
	var coveragecode = "";
	for ( var i = 0; i < selObjSDis.options.length; i++) {
		selObjSDis.options[i].selected = true;
		subdistrictcode += selObjSDis.options[i].value + ",";
	}
	for ( var i = 0; i < selObjCoverg.options.length; i++) {
		selObjCoverg.options[i].selected = true;
		coveragecode += selObjCoverg.options[i].value + ",";
	}

	getvillageatDCACovChngFinal(subdistrictcode, coveragecode);
}

function getvillageatDCACovChngFinal(subdistrictcode, coveragecode) {

	if (subdistrictcode == '') {
		alert("Please Select PART to get Village list.");
	} else if (!subdistrictcode.match('PART')) {
		alert("Please Select PART to get Village list.");
	} else {
		lgdDwrlocalBodyService.getVillageListforDCAFinal(subdistrictcode, coveragecode, {
			callback : getVillageforDCASuccessCovChngFinal,
			errorHandler : getVillageforDCAErrorCovChngFinal
		});
	}
}

function getVillageforDCASuccessCovChngFinal(data) {
	if (data == '') {
		var subdistrictcode = "";
		var selObjSDis = document.getElementById('ddLgdLBSubDistrictDestLatDCA');
		for ( var i = 0; i < selObjSDis.options.length; i++) {
			selObjSDis.options[i].selected = true;
			subdistrictcode += selObjSDis.options[i].value + ",";
		}
		lgdDwrlocalBodyService.getVillageListforLocalBodyBySubdistCode(subdistrictcode, {
			callback : getVillageforDCACovChngSuccess,
			errorHandler : getVillageforDCACovChngError
		});
	}
	if (data != '') {
		var fieldId = 'ddLgdLBVillageSourceLatDCA';
		var fieldId1 = 'ddLgdLBVillageDestLatDCA';

		dwr.util.removeAllOptions(fieldId);
		dwr.util.removeAllOptions(fieldId1);
		var options = $("#ddLgdLBVillageSourceLatDCA");
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'disabled');
				$(option).val(obj.localbodyCoverage).text(obj.landRegionNameEnglish);
				options.append(option);
			} else {
				$(option).val(obj.localbodyCoverage).text(obj.landRegionNameEnglish);
				options.append(option);
			}
		});
	}
}

function getVillageforDCAErrorCovChngFinal() {
	// Show a popup message
	alert("No data found!");
}

function getVillageforDCACovChngSuccess(data) {
	var fieldId = 'ddLgdLBVillageSourceLatDCA';

	dwr.util.removeAllOptions(fieldId);

	var options = $("#ddLgdLBVillageSourceLatDCA");
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
}

function getVillageforDCACovChngError() {
	alert("No data found!");
}

function selectVillageICACovChng() {
	var selObj = document.getElementById('ddLgdLBInterCAreaDestL');
	var selObjCoverg = document.getElementById('ddLgdLBInterPDestList');
	var subDistrictCode = "";
	var coveragecode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		subDistrictCode += selObj.options[i].value + ",";
	}
	for ( var i = 0; i < selObjCoverg.options.length; i++) {
		selObjCoverg.options[i].selected = true;
		coveragecode += selObjCoverg.options[i].value + ",";
	}
	getVillageICACovChng(subDistrictCode, coveragecode);
}

function getVillageICACovChng(subdistrictcode, coveragecode) {
	if (subdistrictcode == '') {
		alert("Please Select PART to get Village.");
	} else if (!subdistrictcode.match('PART')) {
		alert("Please Select PART to get Village.");
	} else {
		lgdDwrlocalBodyService.getVillageListforDCAFinal(subdistrictcode, coveragecode, {
			callback : getVillageICACovChngSuccess,
			errorHandler : getVillageICACovChngError
		});
	}
}

function getVillageICACovChngSuccess(data) {

	if (data == '') {
		var subdistrictcode = "";
		var selObjSDis = document.getElementById('ddLgdLBInterCAreaDestL');
		for ( var i = 0; i < selObjSDis.options.length; i++) {
			selObjSDis.options[i].selected = true;
			subdistrictcode += selObjSDis.options[i].value + ",";
		}
		lgdDwrlocalBodyService.getVillageListforLocalBodyBySubdistCode(subdistrictcode, {
			callback : getVillageforICACovChngSuccess,
			errorHandler : getVillageforICACovChngError
		});
	}
	if (data != '') {
		var fieldId = 'ddLgdLBVillageSourceLatICA';
		var fieldId1 = 'ddLgdLBVillageDestLatICA';
		dwr.util.removeAllOptions(fieldId);
		dwr.util.removeAllOptions(fieldId1);

		var options = $("#ddLgdLBVillageSourceLatICA");
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'disabled');
				$(option).val(obj.localbodyCoverage).text(obj.landRegionNameEnglish);
				options.append(option);
			} else {
				$(option).val(obj.localbodyCoverage).text(obj.landRegionNameEnglish);
				options.append(option);
			}
		});
	}
}

function getVillageICACovChngError(data) {
	var fieldId = 'ddLgdLBVillageSourceLatICA';
	var valueText = 'key';
	var nameText = 'value';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getVillageforICAError() {
	alert("No data found!");
}

function getVillageforICACovChngSuccess(data) {
	var fieldId = 'ddLgdLBVillageSourceLatICA';
	dwr.util.removeAllOptions(fieldId);
	var options = ("#ddLgdLBVillageSourceLatICA");
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
}

function getVillageforICACovChngError() {
	alert("No data found!");
}

// Code added by Arnab----End

function selectSubdistrictAtDCA() {
	var selObj = document.getElementById('ddLgdLBDistCAreaDestL');
	var districtcode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		districtcode += selObj.options[i].value + ",";
	}
	getSubDistrictatDCA(districtcode);
}

function getSubDistrictatDCA(id) {
	if (id == '') {
		alert("Please Select PART to get Sub District list.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Sub District list.");
	} else {
		lgdDwrlocalBodyService.getLocalGovtBodySubDistrictList(id, {
			callback : getSubDistrictforLBSuccess,
			errorHandler : getSubDistrictforLBError
		});
	}
}

function getSubDistrictforLBSuccess(data) {
	var fieldId = 'ddLgdLBSubDistrictSourceLatDCA';
	dwr.util.removeAllOptions(fieldId);
	var options = $("#ddLgdLBSubDistrictSourceLatDCA");

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
}

function getSubDistrictforLBError() {
	// Show a popup message
	alert("No data found!");
}

function selectVillageAtDCAFinal() {
	var selObjSDis = document.getElementById('ddLgdLBSubDistrictDestLatDCA');
	var selObjCoverg = document.getElementById('ddLgdLBDistPDestList');

	var subdistrictcode = "";
	var coveragecode = "";
	for ( var i = 0; i < selObjSDis.options.length; i++) {
		selObjSDis.options[i].selected = true;
		subdistrictcode += selObjSDis.options[i].value + ",";
	}
	for ( var i = 0; i < selObjCoverg.options.length; i++) {
		selObjCoverg.options[i].selected = true;
		coveragecode += selObjCoverg.options[i].value + ",";
	}

	getvillageatDCAFinal(subdistrictcode, coveragecode);
}

function getvillageatDCAFinal(subdistrictcode, coveragecode) {

	if (subdistrictcode == '') {
		alert("Please Select PART to get Village list.");
	} else if (!subdistrictcode.match('PART')) {
		alert("Please Select PART to get Village list.");
	} else {
		lgdDwrlocalBodyService.getVillageListforDCAFinal(subdistrictcode, coveragecode, {
			callback : getVillageforDCASuccessFinal,
			errorHandler : getVillageforDCAErrorFinal
		});
	}
}

function getVillageforDCASuccessFinal(data) {
	if (data == '') {
		var subdistrictcode = "";
		var selObjSDis = document.getElementById('ddLgdLBSubDistrictDestLatDCA');
		for ( var i = 0; i < selObjSDis.options.length; i++) {
			selObjSDis.options[i].selected = true;
			subdistrictcode += selObjSDis.options[i].value + ",";
		}
		lgdDwrlocalBodyService.getVillageListforDCA(subdistrictcode, {
			callback : getVillageforDCASuccess,
			errorHandler : getVillageforDCAError
		});
	}
	if (data != '') {
		var fieldId = 'ddLgdLBVillageSourceLatDCA';
		dwr.util.removeAllOptions(fieldId);

		var options = ("#ddLgdLBVillageSourceLatDCA");
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'disabled');
				$(option).val(obj.landRegionCode).text(obj.landRegionNameEnglish);
				options.append(option);
			} else {
				$(option).val(obj.landRegionCode).text(obj.landRegionNameEnglish);
				options.append(option);
			}

		});
	}
}

function getVillageforDCAErrorFinal() {
	alert("No data found!");
}

function selectVillageAtDCA() {
	var selObj = document.getElementById('ddLgdLBSubDistrictDestLatDCA');
	var subdistrictcode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		subdistrictcode += selObj.options[i].value + ",";
	}

	getvillageatDCA(subdistrictcode);
}

function getvillageatDCA(id) {

	if (id == '') {
		alert("Please Select PART to get Village list.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Village list.");
	} else {
		lgdDwrlocalBodyService.getVillageListforDCA(id, {
			callback : getVillageforDCASuccess,
			errorHandler : getVillageforDCAError
		});
	}
}

function getVillageforDCASuccess(data) {
	var fieldId = 'ddLgdLBVillageSourceLatDCA';
	var valueText = 'key';
	var nameText = 'value';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getVillageforDCAError() {
	alert("No data found!");
}

function getVillageListatDCA(id) {

	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		lgdDwrlocalBodyService.getLocalGovtBodyCoveredVillageList(id, {
			callback : getVillageforLBSuccess,
			errorHandler : getVillageforLBError
		});
	}
}

function getVillageforLBSuccess(data) {

	var fieldId = 'ddLgdLBVillageSourceLatDCA';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getVillageforLBError() {
	// Show a popup message
	alert("No data found!");
}

function selectSubdistrictAtDCAUmapped(subdistrictCode) {
	var selObj = document.getElementById('ddLgdLBSubDistrictDestLatDCAUmapped');
	var subdistrictcode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		subdistrictcode += selObj.options[i].value + ",";

	}
	getUmappedVillageListatDCA(subdistrictcode);
}

function getUmappedVillageListatDCA(id) {
	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		lgdDwrlocalBodyService.getVillageListforLocalBody(id, {
			callback : getVillageUmappedforLBSuccess,
			errorHandler : getVillageUmappedforLBError
		});
	}
}

function getVillageUmappedforLBSuccess(data) {
	var fieldId = 'ddLgdLBVillageSourceLatDCAUmapped';
	var valueText = 'villageCode';
	var nameText = 'villageNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getVillageUmappedforLBError() {
	// Show a popup message
	alert("No data found!");
}

function selectSubDistrictAtICA() {
	var selObj = document.getElementById('ddLgdLBInterCAreaDestL');
	var subdistrictcode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		subdistrictcode += selObj.options[i].value + ",";

	}
	getVillageListatICA(subdistrictcode);
}

function getVillageListatICA(id) {
	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		lgdDwrlocalBodyService.getLocalGovtBodyCoveredVillageList(id, {
			callback : getVillageforLBatICASuccess,
			errorHandler : getVillageforLBatICAError
		});
	}
}

function getVillageforLBatICASuccess(data) {
	var fieldId = 'ddLgdLBVillageSourceLatICA';
	var valueText = 'villageCode';
	var nameText = 'villageNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getVillageforLBatICAError() {
	// Show a popup message
	alert("No data found!");
}

function selectSubDistrictAtICAUmapped(type, category, level) {
	var selObj = document.getElementById('ddLgdLBInterCAreaDestLUmapped');
	// alert(" selTestObj " + selTestObj);
	var subdistrictCodelistPM = new Array();
	var subdistrictCodelistUn = new Array();
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		if (selObj.options[i].text.match("UNMAPPED")) {
			if (selObj.options[i].value.match('PART')) {
				// subdistrictCodelistUn += selObj.options[i].value + ",";
				subdistrictCodelistUn.push(selObj.options[i].value);
			}
		} else if (selObj.options[i].text.match("PARTLY")) {
			// subdistrictCodelistPM += selObj.options[i].value + ",";
			subdistrictCodelistPM.push(selObj.options[i].value);
		} else {
			// subdistrictCodelistPM.push(selObj.options[i].value);
			subdistrictCodelistUn += selObj.options[i].value + ",";
		}

	}
	if (subdistrictCodelistUn != "") {
		// getUmappedVillageListatICA(type, subdistrictCodelistUn, level);
		lgdDwrlocalBodyService.getUnmappedSubDistOrVillIPListFinal(type, subdistrictCodelistUn, level, {
			callback : getUmappedVillageforLBatICASuccess,
			errorHandler : getUmappedVillageforLBatICAError
		});

	}
	if (subdistrictCodelistPM != "") {
		getPartMappedVillageListatICA(type, subdistrictCodelistPM, category, level);
		// getUmappedVillageListatICAFin(type, subdistrictCodelistPM, level);
	}
}

function getUmappedVillageListatICAFin(type, subdistrictcode, level) {
	lgdDwrlocalBodyService.getUnmappedSubDistOrVillIPListFinal(type, subdistrictcode, level, {
		callback : getUmappedVillageforLBatICASuccess,
		errorHandler : getUmappedVillageforLBatICAError
	});

}

// Get Unmapped Village List By Subdistrict Code at InterM Lvel
function getUmappedVillageListatICA(type, subdistrictcode, level) {
	if (subdistrictcode == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!subdistrictcode.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		lgdDwrlocalBodyService.getUnmappedSubDistOrVillIPListFinal(type, subdistrictcode, level, {
			callback : getUmappedVillageforLBatICASuccess,
			errorHandler : getUmappedVillageforLBatICAError
		});
	}
}

function getUmappedVillageforLBatICASuccess(data) {

	var options = $("#ddLgdLBVillageSourceLatICAUmapped");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.landRegionCode).text(obj.localBodyNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.landRegionCode).text(obj.localBodyNameEnglish);
			options.append(option);
		}
	});
}

function getUmappedVillageforLBatICAError() {
	// Show a popup message
	alert("No data found!");
}

// Get Mapped Village List By Subdistrict Code at InterM Lvel
function getPartMappedVillageListatICA(type, subdistrictcode, category, level) {
	lgdDwrlocalBodyService.getPartlyMappedSubDistOrVillListFinal(type, subdistrictcode, category, level, {
		callback : getPartMappedVillageforLBatICASuccess,
		errorHandler : getPartMappedVillageforLBatICAError
	});

	lgdDwrlocalBodyService.getUnmappedSubDistOrVillIPListFinal(type, subdistrictcode, level, {
		callback : getUmappedVillageforLBatICASuccess,
		errorHandler : getUmappedVillageforLBatICAError
	});
}

function getPartMappedVillageforLBatICASuccess(data) {

	var options = $("#ddLgdLBVillageSourceLatICAUmapped");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.landRegionCode).text(obj.localBodyNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.landRegionCode).text(obj.localBodyNameEnglish);
			options.append(option);
		}
	});
}

function getPartMappedVillageforLBatICAError() {
	// Show a popup message
	alert("No data found!");
}
function selectALLVillages() {

	var chkExist = document.getElementById('chkLgdLBExistChk');
	var chkLgdLBUnmapped = document.getElementById('chkLgdLBUnmapped');

	if (chkExist.checked) {
		/* var selObj = document.getElementById('ddLgdLBVillageDestLatDCA'); */
		/* var selObj1 = document.getElementById('ddLgdLBVillageDestLatICA'); */
		var selObj2 = document.getElementById('ddLgdLBVillageDestAtVillageCA');
		var selObj3 = document.getElementById('ddLgdLBInterCAreaDestL');
		/*
		 * var selObj4 =
		 * document.getElementById('ddLgdLBSubDistrictDestLatDCA');
		 */
		var selObj5 = document.getElementById('ddLgdLBDistCAreaDestL');
		var selObj6 = document.getElementById('ddLgdLBVillageCAreaDestL');
		var selObj7 = document.getElementById('ddLgdLBInterPDestList');
		var selObj8 = document.getElementById('ddLgdLBDistPDestList');
		var selObj9 = document.getElementById('ddLgdLBVillageDestLatICA');
		var selObj10 = document.getElementById('ddLgdLBDistPDestListhidden');
		var selObj11 = document.getElementById('ddLgdInterSubDestListhidden');
		var selObj12 = document.getElementById('ddLgdInterVillageListhidden');

		/*
		 * for ( var i = 0; i < selObj.options.length; i++) {
		 * selObj.options[i].selected = true; } for ( var i = 0; i <
		 * selObj1.options.length; i++) { selObj1.options[i].selected = true; }
		 */
		for ( var i = 0; i < selObj2.options.length; i++) {
			selObj2.options[i].selected = true;

		}
		for ( var i = 0; i < selObj3.options.length; i++) {
			selObj3.options[i].selected = true;

		}
		/*
		 * for ( var i = 0; i < selObj4.options.length; i++) {
		 * selObj4.options[i].selected = true; }
		 */
		for ( var i = 0; i < selObj5.options.length; i++) {
			selObj5.options[i].selected = true;

		}
		for ( var i = 0; i < selObj6.options.length; i++) {
			selObj6.options[i].selected = true;
		}

		for ( var i = 0; i < selObj7.options.length; i++) {
			selObj7.options[i].selected = true;

		}
		for ( var i = 0; i < selObj8.options.length; i++) {
			selObj8.options[i].selected = true;

		}

		for ( var i = 0; i < selObj9.options.length; i++) {
			selObj9.options[i].selected = true;
		}
		for ( var i = 0; i < selObj10.options.length; i++) {
			selObj10.options[i].selected = true;
		}
		for ( var i = 0; i < selObj11.options.length; i++) {
			selObj11.options[i].selected = true;
		}
		for ( var i = 0; i < selObj12.options.length; i++) {
			selObj12.options[i].selected = true;
		}

	}

	if (chkLgdLBUnmapped.checked) {

		var selObj9 = document.getElementById('ddLgdLBVillageCAreaDestLUnmapped');
		var selObj10 = document.getElementById('ddLgdLBVillageDestLatICAUmapped');
		var selObj12 = document.getElementById('ddLgdLBInterCAreaDestLUmapped');
		var selObj13 = document.getElementById('ddLgdLBVillageDestLatDCAUmapped');
		var selObj14 = document.getElementById('ddLgdLBSubDistrictDestLatDCAUmapped');
		var selObj15 = document.getElementById('ddLgdLBDistCAreaDestLUmapped');

		for ( var i = 0; i < selObj9.options.length; i++) {
			selObj9.options[i].selected = true;

		}
		for ( var i = 0; i < selObj10.options.length; i++) {
			selObj10.options[i].selected = true;

		}

		for ( var i = 0; i < selObj12.options.length; i++) {
			selObj12.options[i].selected = true;

		}
		for ( var i = 0; i < selObj13.options.length; i++) {
			selObj13.options[i].selected = true;

		}
		for ( var i = 0; i < selObj14.options.length; i++) {
			selObj14.options[i].selected = true;
		}

		for ( var i = 0; i < selObj15.options.length; i++) {
			selObj15.options[i].selected = true;

		}
	}

}

function addgisnodes() {
	// alert("hhhii");
	var tmptextLati = new Array();
	var tempchkLongi = new Array();

	if (dynstart == 0) {
		dynstart = t;
	}
	dynend = t;

	for ( var j = dynstart; j < dynend; j++) {
		if (document.getElementById("latitude" + j)) {
			if (document.getElementById("latitude" + j) != null && document.getElementById("longitude" + j) != null)
				tmptextLati[j] = document.getElementById("latitude" + j).value;
			tempchkLongi[j] = document.getElementById("longitude" + j).value;
		}
	}

	document.getElementById("addgisnodes").innerHTML += "<div id=div" + t + "><td>" + document.getElementById('latitude').innerHTML
			+ "</td><td><input type='text' name='latitude' id='latitude" + t
			+ "' class='frmfield'></td><td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td >"
			+ document.getElementById('longitude').innerHTML + "</td><td >	<input type='text' name='longitude' id='longitude" + t
			+ "' class='frmfield'></td><td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><input type='button' value='Remove' onclick='div" + t
			+ ".parentNode.removeChild(div" + t + ")'/></td></div>";

	for ( var j = dynstart; j < dynend; j++) {
		if (document.getElementById("latitude" + j)) {
			if (document.getElementById("latitude" + j) != null && document.getElementById("longitude" + j) != null)
				document.getElementById("latitude" + j).value = tmptextLati[j];
			document.getElementById("longitude" + j).value = tempchkLongi[j];
		}
	}
	t++;
}

function getHideLocalBodyParentListforUrban(id, checked) {
	if (id != "0") {
		var temp = id.split(":");
		var id1 = temp[0];
		if (checked == true && id1 > 0) {
			document.getElementById('divLgdLBVExist').style.visibility = 'visible';
			document.getElementById('divLgdLBVExist').style.display = 'block';
		} else if (checked == false) {
			document.getElementById('divLgdLBVExist').style.display = 'none';
			removeListsMappedUrban();
		} else {
			alert("Please First Select Local Body Type");
			document.getElementById('divLgdLBVExist').style.display = 'none';
		}
	} else {
		alert("Please First Select Type of Local Body");
		document.getElementById('divLgdLBVExist').style.visibility = 'none';
		document.getElementById('divLgdLBVExist').style.display = 'none';
	}
}

function removeListsMappedUrban() {
	var srcLgdUrbanLBDistExistDest = document.getElementById("ddLgdUrbanLBDistExistDest");
	for ( var count = srcLgdUrbanLBDistExistDest.options.length - 1; count >= 0; count--) {
		srcLgdUrbanLBDistExistDest.remove(count, null);
	}

	var srcLgdUrbanLBSubdistrictListSource = document.getElementById("ddLgdUrbanLBSubdistrictListSource");
	for ( var count = srcLgdUrbanLBSubdistrictListSource.options.length - 1; count >= 0; count--) {
		srcLgdUrbanLBSubdistrictListSource.remove(count, null);
	}

	var srcLgdUrbanLBSubdistrictListDest = document.getElementById("ddLgdUrbanLBSubdistrictListDest");
	for ( var count = srcLgdUrbanLBSubdistrictListDest.options.length - 1; count >= 0; count--) {
		srcLgdUrbanLBSubdistrictListDest.remove(count, null);
	}

}

function getHidePartiallyMappedforUrban(id, checked) {
	if (id != "0") {
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];

		if (checked == true && id1 > 0) {
			document.getElementById('divLgdLBVUmapped').style.visibility = 'visible';
			document.getElementById('divLgdLBVUmapped').style.display = 'block';
			getUnmappedLBSubDPListatUrban('T');
			// getLBDPListatPartmappedUrban('T');
		} else if (checked == false) {
			document.getElementById('divLgdLBVUmapped').style.display = 'none';
			removeListsUnMappedUrban();
		} else {
			alert("Please First Select Local Body Type");
			document.getElementById('divLgdLBVUmapped').style.display = 'none';
		}
	} else {
		alert("Please First Select Type of Local Body");
		document.getElementById('divLgdLBVUmapped').style.visibility = 'none';
		document.getElementById('divLgdLBVUmapped').style.display = 'none';
	}
}

function removeListsUnMappedUrban() {
	var srcLgdUrbanLBDistUmappedDest = document.getElementById("ddLgdUrbanLBDistUmappedDest");
	for ( var count = srcLgdUrbanLBDistUmappedDest.options.length - 1; count >= 0; count--) {
		srcLgdUrbanLBDistUmappedDest.remove(count, null);
	}
}

// Get Unmapped Land Region SubDistrict List For Urban
function getUnmappedLBSubDPListatUrban(type) {
	var stateCode = dwr.util.getValue('hdnStateCode');
	var districtCode = dwr.util.getValue('districtCode');
	// Conditional Check whether Logged in state is ULB operate District wise.
	if ((typeof (isDistrictLevel) != "undefined") && $.parseJSON(isDistrictLevel)) {
		lgdDwrlocalBodyService.getUnMapLRStaWiseList('D', stateCode, {
			callback : getUnmappedLBSubDPListatUrbanSuccess,
			errorHandler : getUnmappedLBSubDPListatUrbanError
		});
	} else {
		if (districtCode == 0) {
			lgdDwrlocalBodyService.getUnMapLRStaWiseList(type, stateCode, {
				callback : getUnmappedLBSubDPListatUrbanSuccess,
				errorHandler : getUnmappedLBSubDPListatUrbanError
			});
		} else if (districtCode > 0) {
			lgdDwrlocalBodyService.getUnMapLRDistWiseList(type, districtCode, {
				callback : getUnmappedLBSubDPListatUrbanSuccess,
				errorHandler : getUnmappedLBSubDPListatUrbanError
			});
		}
	}

}

function getUnmappedLBSubDPListatUrbanSuccess(data) {
	var fieldId = 'ddLgdUrbanLBDistUmappedSource';
	dwr.util.removeAllOptions(fieldId);
	var options = $("#ddLgdUrbanLBDistUmappedSource");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.landRegionCode).text(obj.localBodyNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.landRegionCode).text(obj.localBodyNameEnglish);
			options.append(option);
		}
	});
}

function getUnmappedLBSubDPListatUrbanError() {

	alert("No data found!");
}

// Get Partilly Mapped Land Region List For Urban
function getLBDPListatPartmappedUrban(type) {
	var stateCode = dwr.util.getValue('hdnStateCode');

	lgdDwrlocalBodyService.getPartlyMapLRStaWiseList(type, stateCode, {
		callback : getLBDPListatPartmappedUrbanSuccess,
		errorHandler : getLBDPListatPartmappedUrbanError
	});
}

function getLBDPListatPartmappedUrbanSuccess(data) {

	var fieldId = 'ddLgdUrbanLBDistUmappedSource';
	var valueText = 'landRegionCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getLBDPListatPartmappedUrbanError() {

	alert("No data found!");
}

// Get Unmapped VillageList By SubDist Code
function getUnmappedLBVillPListatUrban(type) {

	var selObj = document.getElementById('ddLgdUrbanLBDistUmappedDest');
	var subdistrictCodelistUn = "";

	var subdistrictCodelistPM = "";
	for (i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		// subdistrictCodelist += selObj.options[i].value + ",";
		if (selObj.options[i].text.match("UNMAPPED")) {
			subdistrictCodelistUn += selObj.options[i].value + ",";
		} else if (selObj.options[i].text.match("PARTLY")) {
			subdistrictCodelistPM += selObj.options[i].value + ",";
		}
	}
	if (subdistrictCodelistUn != "") {

		lgdDwrlocalBodyService.getUnmappedSubDistOrVillList(type, subdistrictCodelistUn, {
			callback : getUnmappedLBVillPListatUrbanSuccess,
			errorHandler : getUnmappedLBVillPListatUrbanError
		});

	}
	if (subdistrictCodelistPM != "") {
		getPartlyMapVillListbySDistCodeForUrban(type, subdistrictCodelistPM);
	}
}

function getUnmappedLBVillPListatUrbanSuccess(data) {
	var fieldId = 'ddLgdUrbanLBSubdistrictLUmappedSource';
	var valueText = 'landRegionCode';
	var nameText = 'localBodyNameEnglish';

	// dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getUnmappedLBVillPListatUrbanError() {
	alert("No data found!");
}

// Get Partly Mapped Village List By SubDistrict Code At District panchayt case
function getPartlyMapVillListbySDistCodeForUrban(type, subdistrictCode) {
	lgdDwrlocalBodyService.getPartlyMappedSubDistOrVillList(type, subdistrictCode, {
		callback : getPartlyMapVillListUrbanSuccess,
		errorHandler : getPartlyMapVillListUrbanError
	});
}

function getPartlyMapVillListUrbanSuccess(data) {
	var fieldId = 'ddLgdUrbanLBSubdistrictLUmappedSource';
	var valueText = 'landRegionCode';
	var nameText = 'localBodyNameEnglish';

	// dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getPartlyMapVillListUrbanError() {
	alert("No data found!");
}

function loadlocalBodyFormforUrban() {

	/*
	 * $("#localBodyNameInEn_error").hide(); $("#ddLgdLBType_error").hide();
	 * $("#chkLgdLBExistChk_error").hide();
	 */
	// document.getElementById('divLgdlistSubTypeCode').style.display = 'none';
	/*
	 * $("#ddLgdUrbanLBSubdistrictListDest_error").hide();
	 * $("#ddLgdUrbanLBDistExistDest_error").hide();
	 * $("#ddLgdUrbanLBDistExistDest1_error").hide();
	 */

	// document.getElementById('divLgdLBVExist').style.display = 'none';
	// document.getElementById('divLgdLBVUmapped').style.display = 'none';
	// If Validation Fail load if Drop down Selected
	document.getElementById('map-1').style.display = 'none';

	if (document.getElementById('ddLgdLBType').value != null) {
		hideShowDivUrban(document.getElementById('ddLgdLBType').value);
	}
}

function selectALLUrban() {
	var chkExist = document.getElementById('chkLgdLBExistChk');

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
}

function selectALLVillagesforUrban() {
	var chkExist = document.getElementById('chkLgdLBExistChk');

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
}

function selectLocalBodyListforUrban() {
	var selObj = document.getElementById('ddLgdUrbanLBDistExistDest');
	var subDistrictCode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		subDistrictCode += selObj.options[i].value + ",";

	}
	getUlbListbylbCode(subDistrictCode);
}

function getUlbListbylbCode(id) {
	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		if ((typeof (isDistrictLevel) != "undefined") && $.parseJSON(isDistrictLevel)) {
			lgdDwrlocalBodyService.getLocalGovtBodyforCoveredDistrictListFinalforLB(id, {
				callback : handleULBSuccess,
				errorHandler : handleULBError
			});
		} else {
			lgdDwrlocalBodyService.getCoveredAreaforULBFinalforLB(id, {
				callback : handleULBSuccess,
				errorHandler : handleULBError
			});
		}
	}
}

function handleULBSuccess(data) {

	if (data == '') {
		alert("Coverage are not mapped for Selected Local Body. Please Map Covered Area first.");
	} else {
		var fieldId = 'ddLgdUrbanLBSubdistrictListSource';
		var valueText = 'landRegionCode';
		var nameText = 'landRegionNameEnglish';

		dwr.util.removeAllOptions(fieldId);
		dwr.util.addOptions(fieldId, data, valueText, nameText);
	}
}

function handleULBError() {
	// Show a popup message
	alert("No data found!");
}

function selectLocalBodyListforUrbanforModF() {
	var selObj = document.getElementById('ddLgdUrbanLBDistExistDest');
	var subDistrictCode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		subDistrictCode += selObj.options[i].value + ",";
	}
	getUlbListbylbCodeforMF(subDistrictCode);
}

function getUlbListbylbCodeforMF(id) {
	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		// Conditional Check whether Logged in state is ULB operate District
		// wise.
		if ((typeof (isDistrictLevel) != "undefined") && $.parseJSON(isDistrictLevel)) {
			lgdDwrlocalBodyService.getLocalGovtBodyforCoveredDistrictListFinalforLBforMCov(id, {
				callback : handleULBSuccessFin,
				errorHandler : handleULBErrorFin
			});
		} else {
			lgdDwrlocalBodyService.getCoveredAreaforULBFinalforLBforMF(id, {
				callback : handleULBSuccessFin,
				errorHandler : handleULBErrorFin
			});
		}

	}
}

function handleULBSuccessFin(data) {

	if (data == '') {
		alert("Coverage are not mapped for Selected Local Body. Please Map Covered Area first.");
	} else {
		var fieldId = 'ddLgdUrbanLBSubdistrictListSource';
		var fieldId1 = 'ddLgdUrbanLBSubdistrictListDest';
		dwr.util.removeAllOptions(fieldId);
		dwr.util.removeAllOptions(fieldId1);
		var options = $("#ddLgdUrbanLBSubdistrictListSource");
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'disabled');
				$(option).val(obj.localbodyCoverage).text(obj.landRegionNameEnglish);
				options.append(option);
			} else {
				$(option).val(obj.localbodyCoverage).text(obj.landRegionNameEnglish);
				options.append(option);
			}
		});
	}
}

function handleULBErrorFin() {
	// Show a popup message
	alert("No data found!");
}

function selectLocalBodyListforUrbanforMod() {
	var selObj = document.getElementById('ddLgdUrbanLBDistExistDest');
	var subDistrictCode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		subDistrictCode += selObj.options[i].value + ",";

	}

	getUlbListbylbCodeforM(subDistrictCode);
}

function getUlbListbylbCodeforM(id) {
	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {

		lgdDwrlocalBodyService.getCoveredAreaforULBFinalforLBforM(id, {
			callback : handleULBSuccess,
			errorHandler : handleULBError
		});
	}
}

function hideShowDivUrban(id) {
	if (id != "" && id != "0") {
		var temp = id.split(":");
		var id1 = temp[0];
		getUrbanMapUploadOption(id1);

		var chkExist = document.getElementById('chkLgdLBExistChk');
		var chkLgdLBUnmapped = document.getElementById('chkLgdLBUnmapped');

		if (chkExist.checked && id > 0) {
			getHideLocalBodyParentListforUrban(id, chkExist.checked);
		}
		if (chkLgdLBUnmapped.checked && id > 0) {
			getHidePartiallyMappedforUrban(id, chkLgdLBUnmapped.checked);
		}
		// document.getElementById('divLgdlistSubTypeCode').style.display =
		// 'block';
		getULBListbyLBtype(id1);
		getLBSubTypeList(id1);
	}
}

function getULBListbyLBtype(id) {
	var stateCode = dwr.util.getValue('hdnStateCode');
	var districtCode = dwr.util.getValue('districtCode');
	if (districtCode == 0) {
		lgdDwrlocalBodyService.getUrbanLocalBodyList(stateCode, id, {
			callback : handleULBSuccess1,
			errorHandler : handleULBError1
		});
	} else if (districtCode > 0) {
		getLandRegionWiseUrban(id, districtCode);
	}
}

function getULBListbyLBtypeChangeCoverage(id, lbCode) {
	var stateCode = dwr.util.getValue('hdnStateCode');
	var districtCode = dwr.util.getValue('districtCode');
	if (districtCode == 0) {
		lgdDwrlocalBodyService.getUrbanLocalBodyListChangeCoverage(stateCode, id, lbCode, {
			callback : handleULBSuccess1,
			errorHandler : handleULBError1
		});
	} else if (districtCode > 0) {
		getLandRegionWiseUrban(id, districtCode);
	}
}

function setandgetInterPanchayatbyDcodeAtVCAModifyWithoutParent(stateCode, id, lbCode) {

	lgdDwrlocalBodyService.getUrbanLocalBodyListChangeCoverage(stateCode, id, lbCode, {
		callback : handleVillagePanchbyIntercodeAtVCASuccessforCov,
		errorHandler : handleVillagePanchbyIntercodeAtVCAErrorforCov
	});
}

function handleULBSuccess1(data) {

	var fieldId = 'ddLgdUrbanLBDistExistSource';
	dwr.util.removeAllOptions(fieldId);
	var options = $("#ddLgdUrbanLBDistExistSource");
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

function handleULBError1() {
	// Show a popup message
	alert("No data found!");
}

function getLandRegionWiseUrban(id1, districtCode) {
	lgdDwrlocalBodyService.getLandRegionWiseUrban(id1, districtCode, {
		callback : handleULBSuccess1,
		errorHandler : handleULBError1
	});
}

function getInterPanchayatbyDcodeAtICA(id) {

	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleInterPanchayatICASuccess,
		errorHandler : handleInterPanchayatICAError
	});
}

function getInterPanchayatbyDcodeAtICAModify(id) {

	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleInterPanchayatICASuccess,
		errorHandler : handleInterPanchayatICAError
	});
}

function handleInterPanchayatICASuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddLgdLBInterPSourceList';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleInterPanchayatICAError() {
	// Show a popup message
	alert("No data found!");
}

function getVillagePanchbyIntercodeAtVCAforCov(id, lbCode) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcodeChangeCoverage(id, lbCode, {
		callback : handleVillagePanchbyIntercodeAtVCASuccessforCov,
		errorHandler : handleVillagePanchbyIntercodeAtVCAErrorforCov
	});
}

function handleVillagePanchbyIntercodeAtVCASuccessforCov(data) {

	var fieldId = 'ddLgdLBVillageSourceAtVillageCA';
	dwr.util.removeAllOptions(fieldId);
	var options = $("#ddLgdLBVillageSourceAtVillageCA");
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

function handleVillagePanchbyIntercodeAtVCAErrorforCov() {
	// Show a popup message
	alert("No data found!");
}

function getVillagePanchbyIntercodeAtVCA(id) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleVillagePanchbyIntercodeAtVCASuccess,
		errorHandler : handleVillagePanchbyIntercodeAtVCAError
	});
}

function handleVillagePanchbyIntercodeAtVCASuccess(data) {
	var fieldId = 'ddLgdLBVillageSourceAtVillageCA';
	dwr.util.removeAllOptions(fieldId);
	var options = $("#ddLgdLBVillageSourceAtVillageCA");
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

function handleVillagePanchbyIntercodeAtVCAError() {
	// Show a popup message
	alert("No data found!");
}

function getLBSubTypeList(id) {
	var stateCode = dwr.util.getValue('hdnStateCode');
	lgdDwrlocalBodyService.getLBSubTypeList(stateCode, id, {
		callback : handleLBSubTypeListSuccess,
		errorHandler : handlehandleLBSubTypeListError
	});
}

function handleLBSubTypeListSuccess(data) {

	var fieldId = 'ddlgdLBSubTypeCode';
	var valueText = 'local_body_subtype_code';
	var nameText = 'subtype_name_eng';

	dwr.util.removeAllOptions(fieldId);
	if (data == "") {
		document.getElementById('divLgdlistSubTypeCode').style.display = 'none';
		/*
		 * var st = document.getElementById('ddlgdLBSubTypeCode'); st.add(new
		 * Option('No Subtypes Available', '0'));
		 */
	} else {
		document.getElementById('divLgdlistSubTypeCode').style.visibility = 'visible';
		document.getElementById('divLgdlistSubTypeCode').style.display = 'block';
		dwr.util.addOptions(fieldId, data, valueText, nameText);
	}
}

function handlehandleLBSubTypeListError() {
	// Show a popup message
	alert("No data found!");
}

function getMapUploadOptionLBody(id, lbType) {
	var stateCode = dwr.util.getValue('hdnStateCode');
	lgdDwrlocalBodyService.getMapDLBody(stateCode, id, lbType, {
		callback : handleMapSuccessLBody,
		errorHandler : handleMapListErrorLBody
	});
}

function handleMapSuccessLBody(data) {
	for ( var i = 0; i < data.length; i++) {
		pesDetails = data[i];
		var id = pesDetails.ismapupload;
		if (id == true) {
			document.getElementById('map-1').style.visibility = 'visible';
			document.getElementById('map-1').style.display = 'block';
		} else {
			document.getElementById('map-1').style.visibility = 'hidden';
			document.getElementById('map-1').style.display = 'none';
		}

	}

}

function handleMapListErrorLBody() {
	alert("No data found!");
}

function getMapUploadOption(id) {
	var stateCode = dwr.util.getValue('hdnStateCode');
	lgdDwrlocalBodyService.getMapD(stateCode, id, {
		callback : handleMapSuccess,
		errorHandler : handleMapListError
	});
}

function handleMapSuccess(data) {
	for ( var i = 0; i < data.length; i++) {
		pesDetails = data[i];
		var id = pesDetails.ismapupload;
		if (id == true) {
			document.getElementById('map-1').style.visibility = 'visible';
			document.getElementById('map-1').style.display = 'block';
		} else {
			document.getElementById('map-1').style.display = 'none';
		}

	}

}

function handleMapListError() {
	alert("No data found!");
}

function getUrbanMapUploadOption(id) {

	var stateCode = dwr.util.getValue('hdnStateCode');
	lgdDwrlocalBodyService.getUrbanMapD(stateCode, id, {
		callback : handleUrbanMapSuccess,
		errorHandler : handleUrbanMapListError
	});
}

function handleUrbanMapSuccess(data) {
	for ( var i = 0; i < data.length; i++) {
		pesDetails = data[i];
		var id = pesDetails.ismapupload;
		if (id == true) {
			document.getElementById('map-1').style.visibility = 'visible';
			document.getElementById('map-1').style.display = 'block';
		} else {
			document.getElementById('map-1').style.visibility = 'hidden';
			document.getElementById('map-1').style.display = 'none';
		}

	}

}

function handleUrbanMapListError() {
	alert("No data found!");
}

function addItemforLBforChangeCoverage(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'PART') {
					alert("You can not select DISTURBED Village Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'FULL') {
					alert("You can not select DISTURBED Village Mapped Panchayat");
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

function addItemforLB(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'PART') {
					alert("You can not select DISTURBED District Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'FULL') {
					alert("You can not select DISTURBED District Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + ">" + document.getElementById(list2)[j].text
									+ " (" + val + ")</option>");
					// removeSelectedValueforLB(list2, val);
				}
			}

		}
		// removeSelectedValueforLB(list2, val);
		removeSelectedValueforLBDisturbed(list2, val);
	}

}

function addItemforLBChangeCov(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'PART') {
					alert("You can not select DISTURBED District Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'FULL') {
					alert("You can not select DISTURBED District Mapped Panchayat");
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

function addItemforLBChangeCovFULL(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'PART') {
					alert("You can not select DISTURBED District Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'FULL') {
					alert("You can not select DISTURBED District Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + ">" + document.getElementById(list2)[j].text
									+ " (" + val + ")</option>");
					addLandregionChangeCovforFull();
				}
			}
		}
		removeSelectedValueforLBDisturbed(list2, val);
	}
}
var districtCode = "";
function addLandregionChangeCovforFull() {
	var selObj = document.getElementById('ddLgdLBDistPDestList');

	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		districtCode += selObj.options[i].value + ",";
	}

	lgdDwrlocalBodyService.getLocalGovtBodyforCoveredDistrictListFinalToDistrict(districtCode, {
		callback : getCoveredLandForDistRegionChangeCovSuccessFin,
		errorHandler : getCoveredLandForDistRegionChangeCovErrorFin
	});

}

// data contains the returned value
function getCoveredLandForDistRegionChangeCovSuccessFin(data) {

	var fieldId = 'ddLgdLBDistPDestListhidden';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, getITBValueChCov, getITBNameChCov);

	lgdDwrlocalBodyService.getLocalGovtBodyforCoveredDistrictListforHeadQuarter(districtCode, {
		callback : getCoveredLandForDistRegionChangeCovSuccessFinal,
		errorHandler : getCoveredLandForDistRegionChangeCovErrorFinal
	});

	// getHeadQuarterListChangeCoverDPFULL('ddLgdLBDistPDestListhidden', 'D');
}

function getITBValueChCov(data) {
	if (data.coverageType == 'F') {
		sd = data.landRegionCode + "_FULL_" + data.landRegionType;
	} else if (data.coverageType == 'P') {
		sd = data.landRegionCode + "_PART_" + data.landRegionType;
	}
	return sd;
}

function getITBNameChCov(data) {

	var sd1 = null;
	if (data.coverageType == 'F') {
		sd1 = data.landRegionNameEnglish + "(FULL)";
	} else if (data.coverageType == 'P') {
		sd1 = data.landRegionNameEnglish + "(PART)";
	}
	return sd1;
}

// data contains the returned value
function getCoveredLandForDistRegionChangeCovSuccessFinal(data) {

	var fieldId = 'ddLgdLBDistPDestListhiddenforHeadQuarter';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, getITBValueChCovforheadQuarter, getITBNameChCovforheadQuarter);

	getHeadQuarterListChangeCoverDPFULL('ddLgdLBDistPDestListhiddenforHeadQuarter', 'D');
}

function getITBValueChCovforheadQuarter(data) {
	if (data.coverageType == 'F') {
		sd = data.landRegionCode + "_FULL_" + data.landRegionType;
	} else if (data.coverageType == 'P') {
		sd = data.landRegionCode + "_PART_" + data.landRegionType;
	}
	return sd;
}

function getITBNameChCovforheadQuarter(data) {

	var sd1 = null;
	if (data.coverageType == 'F') {
		sd1 = data.landRegionNameEnglish + "(FULL)";
	} else if (data.coverageType == 'P') {
		sd1 = data.landRegionNameEnglish + "(PART)";
	}
	return sd1;
}

function getCoveredLandForDistRegionChangeCovErrorFinal() {
	alert("No data found!");
}
function getCoveredLandForDistRegionChangeCovErrorFin() {
	alert("No data found!");
}

function getHeadQuarterListChangeCoverDPFULL(list1, lb) {
	var list = document.getElementById(list1);
	var list2 = document.getElementById('availddLgdLBDistCAreaSourceLUmapped');
	var listFinal = [];
	var innerHTMLText = '';

	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
	if (list2.value != null) {
		for ( var i = 0; i < list2.length; i++) {
			listFinal[listFinal.length] = list2[i];
		}
	}
	var headquarter = "getHeadQuarters" + lb;
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

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
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

function addItemforLBFULL(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'PART') {
					alert("You can not select DISTURBED District Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'FULL') {
					alert("You can not select DISTURBED District Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + ">" + document.getElementById(list2)[j].text
									+ " (" + val + ")</option>");
					addLandregionforFull();
					// removeSelectedValueforLB(list2, val);
				}
			}
		}
		// removeSelectedValueforLB(list2, val);
		removeSelectedValueforLBDisturbed(list2, val);
		/*
		 * addLandregionforFull(); removeSelectedValueforLB(list2, val);
		 */
	}

}

function addLandregionforFull() {
	var selObj = document.getElementById('ddLgdLBDistPDestList');
	var districtCode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		districtCode += selObj.options[i].value + ",";
	}

	lgdDwrlocalBodyService.getLocalGovtBodyforCoveredDistrictListFinalToDistrict(districtCode, {
		callback : getCoveredLandForDistRegionSuccessFin,
		errorHandler : getCoveredLandForDistRegionErrorFin
	});

}

// data contains the returned value
function getCoveredLandForDistRegionSuccessFin(data) {
	var fieldId = 'ddLgdLBDistPDestListhidden';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, getITBValue, getITBName);

	getHeadQuarterListFF('ddLgdLBDistPDestListhidden', 'D');
}

function getITBValue(data) {
	var sd = null;
	if (data.coverageType == 'F') {
		sd = data.landRegionCode + "_FULL_" + data.landRegionType;
	} else if (data.coverageType == 'P') {
		sd = data.landRegionCode + "_PART_" + data.landRegionType;
	}
	return sd;
}

function getITBName(data) {

	var sd1 = null;
	if (data.coverageType == 'F') {
		sd1 = data.landRegionNameEnglish + "(FULL)";
	} else if (data.coverageType == 'P') {
		sd1 = data.landRegionNameEnglish + "(PART)";
	}
	return sd1;
}

function getCoveredLandForDistRegionErrorFin() {
	alert("No data found!");
}

function addItemforLBforDist(list1, list2, val, doAddVal, type) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else {

					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "D" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");

				}

			}

		}
		removeSelectedValueforLB(list2, val);
	}

}

function addItemforLBforDistMapCov(list1, list2, list3, val, doAddVal, type) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else {

					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "D" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");

				}

			}

		}
		document.getElementById('divLgdLBCoveredArea').style.visibility = 'visible';
		document.getElementById('divLgdLBCoveredArea').style.display = 'block';
		document.getElementById('getHeadQuartersD').style.visibility = 'visible';
		document.getElementById('getHeadQuartersD').style.display = 'block';
		removeSelectedValueforLB(list2, val);

		getHeadQuarterListFinalWithoutMappedLBDP(list1, list3, 'D');

	}

}

function addItemforLBforDistforCovChange(list1, list2, val, doAddVal, type) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "D" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
				}
			}
		}
		document.getElementById('divLgdLBCoveredAreaHeadQuarter').style.visibility = 'visible';
		document.getElementById('divLgdLBCoveredAreaHeadQuarter').style.display = 'block';
		document.getElementById('getHeadQuartersD').style.visibility = 'visible';
		document.getElementById('getHeadQuartersD').style.display = 'block';
		removeSelectedValueforLB(list2, val);
		getHeadQuarterListFinalWithMappedUnmappedLBDP(list1, 'D');
	}
}

function addItemforLBforsub(list1, list2, val, doAddVal, type) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region");
				} else {

					// Conditional Check whether Logged in state is ULB operate
					// District wise.
					if ((typeof (isDistrictLevel) != "undefined") && $.parseJSON(isDistrictLevel)) {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "D" + ">"
										+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
					} else {

						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "T" + ">"
										+ document.getElementById(list2)[j].text + " (" + val + ")</option>");

					}
				}

			}

		}
		removeSelectedValueforLB(list2, val);
	}

}

function addItemforLBforsubIP(list1, list2, list3, val, doAddVal, type) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region");
				} else {

					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "T" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");

				}

			}

		}
		document.getElementById('divLgdLBCoveredArea').style.visibility = 'visible';
		document.getElementById('divLgdLBCoveredArea').style.display = 'block';
		document.getElementById('getHeadQuartersT').style.visibility = 'visible';
		document.getElementById('getHeadQuartersT').style.display = 'block';
		removeSelectedValueforLB(list2, val);

		getHeadQuarterListFinalWithoutMappedLBIPFFF(list1, list3, 'T');

	}

}

function addItemforLBforsubUnMappedIP(list1, list2, val, doAddVal, type) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region");
				} else {

					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "T" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
				}

			}
		}
		document.getElementById('divLgdLBCoveredAreaHeadQuarter').style.visibility = 'visible';
		document.getElementById('divLgdLBCoveredAreaHeadQuarter').style.display = 'block';
		document.getElementById('getHeadQuartersT').style.visibility = 'visible';
		document.getElementById('getHeadQuartersT').style.display = 'block';
		removeSelectedValueforLB(list2, val);
		getHeadQuarterListFinalWithMappedUnmappedLBIP(list1, 'T');
	}

}

function getHeadQuarterListFinalWithMappedUnmappedLBIP(list1, lb) {
	var list = document.getElementById(list1);
	var list2 = document.getElementById('availddLgdLBInterCAreaSourceLUmapped');
	var list3 = document.getElementById('ddLgdLBInterCAreaDestL');
	var list4 = document.getElementById('ddLgdInterSubDestListhidden');

	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}

	for ( var i = 0; i < list2.length; i++) {
		listFinal[listFinal.length] = list2[i];
	}

	for ( var i = 0; i < list3.length; i++) {
		listFinal[listFinal.length] = list3[i];
	}
	for ( var i = 0; i < list4.length; i++) {
		listFinal[listFinal.length] = list4[i];
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
				innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " checked="
						+ chkhead[j] + " value=" + listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb
						+ "'); /></td>" + "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>"
						+ "</tr>";
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

function addItemforLBforsubChangeCov(list1, list2, list3, list4, val, doAddVal, type) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region");
				} else {
					if (!document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
						addremoveSelectedValueforSubDistrictFin(list1, list2, val);
					} else if (document.getElementById(list2).options[j].text.match('PART')
							|| document.getElementById(list2).options[j].text.match('PART') && val == 'PART') {
						addremoveSelectedPartSubDistrictFin(list1, list2, val);
					}

				}
			}

		}
		// removeSelectedValueforLB(list2, val);
		document.getElementById('divLgdLBCoveredAreaHeadQuarter').style.visibility = 'visible';
		document.getElementById('divLgdLBCoveredAreaHeadQuarter').style.display = 'block';
		document.getElementById('getHeadQuartersT').style.visibility = 'visible';
		document.getElementById('getHeadQuartersT').style.display = 'block';

		getHeadQuarterListFinalWithoutMappedLBIP(list1, list3, list4, 'T');

	}

}

function addremoveSelectedValueforSubDistrictFin(list1, list2, val) {
	var elSel = document.getElementById(list2);
	var i;
	for (i = elSel.length - 1; i >= 0; i--) {
		if (elSel.options[i].selected) {
			if (elSel.length == 1) {
				alert("You cannot select ALL FULL Mapped Land Region");
			} else {
				$('#' + list1).append(
						"<option value=" + document.getElementById(list2)[i].value + "_" + val + "_" + "T" + ">"
								+ document.getElementById(list2)[i].text + " (" + val + ")</option>");
				elSel.remove(i);
			}
		}
	}
}

function addremoveSelectedPartSubDistrictFin(list1, list2, val) {
	var elSel = document.getElementById(list2);
	var i;
	for (i = elSel.length - 1; i >= 0; i--) {
		if (elSel.options[i].selected) {
			$('#' + list1).append(
					"<option value=" + document.getElementById(list2)[i].value + "_" + val + "_" + "T" + ">" + document.getElementById(list2)[i].text
							+ " (" + val + ")</option>");
			elSel.remove(i);
		}
	}
}

function addItemforLBforvillage(list1, list2, val, doAddVal, type) {

	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else {

					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "V" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");

				}

			}

		}
		removeSelectedValueforLB(list2, val);
	}

}

// Code added by Arnab on 28/03/2013 for incorpration changed for Mapped LB
// ----START

function addItemforLBforvillageMappedUnMappedCovChange(list1, list2, val, doAddVal, type) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "V" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
				}
			}
		}
		document.getElementById('divLgdLBCoveredAreaHeadQuarter').style.visibility = 'visible';
		document.getElementById('divLgdLBCoveredAreaHeadQuarter').style.display = 'block';
		document.getElementById('getHeadQuartersV').style.visibility = 'visible';
		document.getElementById('getHeadQuartersV').style.display = 'block';
		removeSelectedValueforLB(list2, val);

		getHeadQuarterListFinalWithMappedUnmappedLB(list1, 'V');
	}
}

function addItemforLBforvillageMappedChangedCoverage(list1, list2, list3, val, doAddVal, type) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region");
				} else {
					if (!document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
						addremoveSelectedValueforvillageFin(list1, list2, val);
					} else {

						if (!document.getElementById(list2).options[j].text.match('PART')
								|| document.getElementById(list2).options[j].text.match('PART') && val == 'PART') {
							addremoveSelectedPartVillageFin(list1, list2, val);
						}

						/*
						 * $('#' + list1).append( "<option value=" +
						 * document.getElementById(list2)[j].value + "_" + val +
						 * "_" + "V" + ">" +
						 * document.getElementById(list2)[j].text + " (" + val + ")</option>");
						 * 
						 * if
						 * (!document.getElementById(list2).options[j].text.match('PART') ||
						 * document.getElementById(list2).options[j].text.match('PART') &&
						 * val == 'PART') { removeSelectedValueforLB(list2,
						 * val); }
						 */
					}
				}
			}

		}
		document.getElementById('divLgdLBCoveredAreaHeadQuarter').style.visibility = 'visible';
		document.getElementById('divLgdLBCoveredAreaHeadQuarter').style.display = 'block';
		document.getElementById('getHeadQuartersV').style.visibility = 'visible';
		document.getElementById('getHeadQuartersV').style.display = 'block';
		// removeSelectedValueforLB(list2, val);

		getHeadQuarterListFinalWithoutMappedLBVP(list1, list3, 'V');
	}
}

function addremoveSelectedValueforvillageFin(list1, list2, val) {
	var elSel = document.getElementById(list2);
	var i;
	for (i = elSel.length - 1; i >= 0; i--) {
		if (elSel.options[i].selected) {
			if (elSel.length == 1) {
				alert("You cannot select ALL FULL Mapped Land Region");
			} else {
				$('#' + list1).append(
						"<option value=" + document.getElementById(list2)[i].value + "_" + val + "_" + "V" + ">"
								+ document.getElementById(list2)[i].text + " (" + val + ")</option>");
				elSel.remove(i);
			}
		}
	}
}

function addremoveSelectedPartVillageFin(list1, list2, val) {
	var elSel = document.getElementById(list2);
	var i;
	for (i = elSel.length - 1; i >= 0; i--) {
		if (elSel.options[i].selected) {
			$('#' + list1).append(
					"<option value=" + document.getElementById(list2)[i].value + "_" + val + "_" + "V" + ">" + document.getElementById(list2)[i].text
							+ " (" + val + ")</option>");
			elSel.remove(i);
		}
	}
}

function addremoveSelectedValueforDistrictFin(list1, list2, val) {
	var elSel = document.getElementById(list2);
	var i;
	for (i = elSel.length - 1; i >= 0; i--) {
		if (elSel.options[i].selected) {
			if (elSel.length == 1) {
				alert("You cannot select ALL FULL Mapped Land Region");
			} else {
				$('#' + list1).append(
						"<option value=" + document.getElementById(list2)[i].value + "_" + val + "_" + "D" + ">"
								+ document.getElementById(list2)[i].text + " (" + val + ")</option>");
				elSel.remove(i);
			}
		}
	}
}

function addremoveSelectedPartDistrictFin(list1, list2, val) {
	var elSel = document.getElementById(list2);
	var i;
	for (i = elSel.length - 1; i >= 0; i--) {
		if (elSel.options[i].selected) {
			$('#' + list1).append(
					"<option value=" + document.getElementById(list2)[i].value + "_" + val + "_" + "D" + ">" + document.getElementById(list2)[i].text
							+ " (" + val + ")</option>");
			elSel.remove(i);
		}
	}
}

function addItemforLBforvillageMapped(list1, list2, list3, val, doAddVal, type) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "V" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
				}
			}
		}
		document.getElementById('divLgdLBCoveredArea').style.visibility = 'visible';
		document.getElementById('divLgdLBCoveredArea').style.display = 'block';
		document.getElementById('getHeadQuartersV').style.visibility = 'visible';
		document.getElementById('getHeadQuartersV').style.display = 'block';
		removeSelectedValueforLB(list2, val);

		getHeadQuarterListFinalWithoutMappedLBVPFF(list1, list3, 'V');
	}
}

function getHeadQuarterListFinalWithoutMappedLBVPFF(list1, list3, lb) {
	var list = document.getElementById(list1);
	var list1 = document.getElementById(list3);
	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
	for ( var i = 0; i < list1.length; i++) {
		listFinal[listFinal.length] = list1[i];
	}

	if (listFinal.length >= 1) {
		for ( var k = 0; k < listFinal.length; k++) {
			var checktempVal = listFinal[k].value;
			var tempcheckVal = checktempVal.split("_");
			var tempcheckCode = tempcheckVal[0];
		}
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

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
				+ name + " List" + "" + "</td>" + "" + "</tr>";

		for ( var j = 0; j < listFinal.length; j++) {
			var name;

			var checkVal = listFinal[j].value;
			var tempcheckVal = checkVal.split("_");
			var tempcheckCode = tempcheckVal[0];
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

			/*
			 * innerHTMLText += "<tr class='tblRowB'><td width='50'><input
			 * type='checkbox' name='headQuarterCode' id=chk"+j +"
			 * value="+listFinal[j].value+"
			 * onclick='javascript:SingleSelectFinal(this);' /></td>" + "<td width='200'><form:label
			 * path='headQuarterName' id=lbl"+j+">"+name+"</form:label></td>" + "</tr>";
			 */
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
						+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>" + "</tr>";
			}
		}

		innerHTMLText += "</table></div>";
		document.getElementById(headquarter).innerHTML += innerHTMLText;
	}

}

function getHeadQuarterListFinalWithoutMappedLBVPFFBackReset(lb) {
	var list = document.getElementById('availddLgdLBVillageCAreaSourceLUnmapped');
	var list1 = document.getElementById('ddLgdLBVillageCAreaDestLUnmapped');
	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
	for ( var i = 0; i < list1.length; i++) {
		listFinal[listFinal.length] = list1[i];
	}

	if (listFinal.length >= 1) {
		for ( var k = 0; k < listFinal.length; k++) {
			var checktempVal = listFinal[k].value;
			var tempcheckVal = checktempVal.split("_");
			var tempcheckCode = tempcheckVal[0];
		}
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

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
				+ name + " List" + "" + "</td>" + "" + "</tr>";

		for ( var j = 0; j < listFinal.length; j++) {
			var name;

			var checkVal = listFinal[j].value;
			var tempcheckVal = checkVal.split("_");
			var tempcheckCode = tempcheckVal[0];
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

			/*
			 * innerHTMLText += "<tr class='tblRowB'><td width='50'><input
			 * type='checkbox' name='headQuarterCode' id=chk"+j +"
			 * value="+listFinal[j].value+"
			 * onclick='javascript:SingleSelectFinal(this);' /></td>" + "<td width='200'><form:label
			 * path='headQuarterName' id=lbl"+j+">"+name+"</form:label></td>" + "</tr>";
			 */
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
						+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>" + "</tr>";
			}
		}

		innerHTMLText += "</table></div>";
		document.getElementById(headquarter).innerHTML += innerHTMLText;
	}

}

function getHeadQuarterListFinalWithMappedUnmappedLBDP(list1, lb) {
	var list = document.getElementById(list1);
	var list2 = document.getElementById('availddLgdLBDistCAreaSourceLUmapped');
	var list3 = document.getElementById('ddLgdLBDistCAreaDestL');
	var list4 = document.getElementById('ddLgdLBDistPDestListhidden');

	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}

	for ( var i = 0; i < list2.length; i++) {
		listFinal[listFinal.length] = list2[i];
	}

	for ( var i = 0; i < list3.length; i++) {
		listFinal[listFinal.length] = list3[i];
	}
	for ( var i = 0; i < list4.length; i++) {
		listFinal[listFinal.length] = list4[i];
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
				innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " checked="
						+ chkhead[j] + " value=" + listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb
						+ "'); /></td>" + "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>"
						+ "</tr>";
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

function getHeadQuarterListFinalWithMappedUnmappedLB(list1, lb) {
	var list = document.getElementById(list1);
	var list2 = document.getElementById('availddLgdLBVillageCAreaSourceLUnmapped');
	var list3 = document.getElementById('ddLgdLBVillageCAreaDestL');

	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}

	for ( var i = 0; i < list2.length; i++) {
		listFinal[listFinal.length] = list2[i];
	}

	for ( var i = 0; i < list3.length; i++) {
		listFinal[listFinal.length] = list3[i];
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
				innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " checked="
						+ chkhead[j] + " value=" + listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb
						+ "'); /></td>" + "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>"
						+ "</tr>";
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

function getHeadQuarterListFinalWithoutMappedLBIP(list1, list3, list4, lb) {
	var list = document.getElementById(list1);
	var list1 = document.getElementById(list3);
	var list2 = document.getElementById('ddLgdLBInterCAreaDestLUmapped');
	var list3 = document.getElementById(list4);
	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
	for ( var i = 0; i < list1.length; i++) {
		listFinal[listFinal.length] = list1[i];
	}
	for ( var i = 0; i < list2.length; i++) {
		listFinal[listFinal.length] = list2[i];
	}
	for ( var i = 0; i < list3.length; i++) {
		listFinal[listFinal.length] = list3[i];
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

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
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

			/*
			 * innerHTMLText += "<tr class='tblRowB'><td width='50'><input
			 * type='checkbox' name='headQuarterCode' id=chk"+j +"
			 * value="+listFinal[j].value+"
			 * onclick='javascript:SingleSelectFinal(this);' /></td>" + "<td width='200'><form:label
			 * path='headQuarterName' id=lbl"+j+">"+name+"</form:label></td>" + "</tr>";
			 */
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

function getHeadQuarterListFinalWithoutMappedLBIPFFF(list1, list3, lb) {
	var list = document.getElementById(list1);
	var list1 = document.getElementById(list3);

	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
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

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
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

			/*
			 * innerHTMLText += "<tr class='tblRowB'><td width='50'><input
			 * type='checkbox' name='headQuarterCode' id=chk"+j +"
			 * value="+listFinal[j].value+"
			 * onclick='javascript:SingleSelectFinal(this);' /></td>" + "<td width='200'><form:label
			 * path='headQuarterName' id=lbl"+j+">"+name+"</form:label></td>" + "</tr>";
			 */
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

function getHeadQuarterListFinalWithoutMappedLBIPFFFBackReset(lb) {
	var list = document.getElementById('availddLgdLBInterCAreaSourceLUmapped');
	var list1 = document.getElementById('ddLgdLBInterCAreaDestLUmapped');

	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
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

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
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

			/*
			 * innerHTMLText += "<tr class='tblRowB'><td width='50'><input
			 * type='checkbox' name='headQuarterCode' id=chk"+j +"
			 * value="+listFinal[j].value+"
			 * onclick='javascript:SingleSelectFinal(this);' /></td>" + "<td width='200'><form:label
			 * path='headQuarterName' id=lbl"+j+">"+name+"</form:label></td>" + "</tr>";
			 */
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

function getHeadQuarterListFinalWithoutMappedLBIPYesNo(lb) {
	var list = document.getElementById('availddLgdLBInterCAreaSourceLUmapped');
	var list1 = document.getElementById('ddLgdLBInterCAreaDestLUmapped');
	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
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

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
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

			/*
			 * innerHTMLText += "<tr class='tblRowB'><td width='50'><input
			 * type='checkbox' name='headQuarterCode' id=chk"+j +"
			 * value="+listFinal[j].value+"
			 * onclick='javascript:SingleSelectFinal(this);' /></td>" + "<td width='200'><form:label
			 * path='headQuarterName' id=lbl"+j+">"+name+"</form:label></td>" + "</tr>";
			 */
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

function getHeadQuarterListFinalWithoutMappedLB(list1, list3, list4, lb) {
	var list = document.getElementById(list1);
	var list1 = document.getElementById(list3);
	var list2 = document.getElementById('ddLgdLBDistCAreaDestLUmapped');
	var list3 = document.getElementById(list4);
	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
	for ( var i = 0; i < list1.length; i++) {
		listFinal[listFinal.length] = list1[i];
	}
	for ( var i = 0; i < list2.length; i++) {
		listFinal[listFinal.length] = list2[i];
	}
	for ( var i = 0; i < list3.length; i++) {
		listFinal[listFinal.length] = list3[i];
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

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
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

			/*
			 * innerHTMLText += "<tr class='tblRowB'><td width='50'><input
			 * type='checkbox' name='headQuarterCode' id=chk"+j +"
			 * value="+listFinal[j].value+"
			 * onclick='javascript:SingleSelectFinal(this);' /></td>" + "<td width='200'><form:label
			 * path='headQuarterName' id=lbl"+j+">"+name+"</form:label></td>" + "</tr>";
			 */
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

function getHeadQuarterListFinalWithoutMappedLBVPBackButton(list1, lb) {

	var list = document.getElementById(list1);
	var list1 = document.getElementById('availddLgdLBVillageCAreaSourceLUnmapped');
	var list2 = document.getElementById('ddLgdLBVillageCAreaDestLUnmapped');

	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
	for ( var i = 0; i < list1.length; i++) {
		listFinal[listFinal.length] = list1[i];
	}
	for ( var i = 0; i < list2.length; i++) {
		listFinal[listFinal.length] = list2[i];
	}
	if (listFinal.length >= 1) {
		for ( var k = 0; k < listFinal.length; k++) {
			var checktempVal = listFinal[k].value;
			var tempcheckVal = checktempVal.split("_");
			var tempcheckCode = tempcheckVal[0];
		}
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

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
				+ name + " List" + "" + "</td>" + "" + "</tr>";

		for ( var j = 0; j < listFinal.length; j++) {
			var name;

			var checkVal = listFinal[j].value;
			var tempcheckVal = checkVal.split("_");
			var tempcheckCode = tempcheckVal[0];
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

			/*
			 * innerHTMLText += "<tr class='tblRowB'><td width='50'><input
			 * type='checkbox' name='headQuarterCode' id=chk"+j +"
			 * value="+listFinal[j].value+"
			 * onclick='javascript:SingleSelectFinal(this);' /></td>" + "<td width='200'><form:label
			 * path='headQuarterName' id=lbl"+j+">"+name+"</form:label></td>" + "</tr>";
			 */
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
						+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>" + "</tr>";
			}
		}

		innerHTMLText += "</table></div>";
		document.getElementById(headquarter).innerHTML += innerHTMLText;
	}

}

function getHeadQuarterListFinalWithoutMappedLBVP(list1, list3, lb) {
	var list = document.getElementById(list1);
	var list1 = document.getElementById(list3);
	var list2 = document.getElementById('ddLgdLBVillageCAreaDestLUnmapped');

	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
	for ( var i = 0; i < list1.length; i++) {
		listFinal[listFinal.length] = list1[i];
	}
	for ( var i = 0; i < list2.length; i++) {
		listFinal[listFinal.length] = list2[i];
	}
	if (listFinal.length >= 1) {
		for ( var k = 0; k < listFinal.length; k++) {
			var checktempVal = listFinal[k].value;
			var tempcheckVal = checktempVal.split("_");
			var tempcheckCode = tempcheckVal[0];
		}
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

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
				+ name + " List" + "" + "</td>" + "" + "</tr>";

		for ( var j = 0; j < listFinal.length; j++) {
			var name;

			var checkVal = listFinal[j].value;
			var tempcheckVal = checkVal.split("_");
			var tempcheckCode = tempcheckVal[0];
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

			/*
			 * innerHTMLText += "<tr class='tblRowB'><td width='50'><input
			 * type='checkbox' name='headQuarterCode' id=chk"+j +"
			 * value="+listFinal[j].value+"
			 * onclick='javascript:SingleSelectFinal(this);' /></td>" + "<td width='200'><form:label
			 * path='headQuarterName' id=lbl"+j+">"+name+"</form:label></td>" + "</tr>";
			 */
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
						+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>" + "</tr>";
			}
		}

		innerHTMLText += "</table></div>";
		document.getElementById(headquarter).innerHTML += innerHTMLText;
	}

}

function getHeadQuarterListFinalWithoutMappedLBVPforYesNo(lb) {

	var list = document.getElementById('availddLgdLBVillageCAreaSourceLUnmapped');
	var list1 = document.getElementById('ddLgdLBVillageCAreaDestLUnmapped');

	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
	if (list1.value != null) {
		for ( var i = 0; i < list1.length; i++) {
			listFinal[listFinal.length] = list1[i];
		}
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

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
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

			/*
			 * innerHTMLText += "<tr class='tblRowB'><td width='50'><input
			 * type='checkbox' name='headQuarterCode' id=chk"+j +"
			 * value="+listFinal[j].value+"
			 * onclick='javascript:SingleSelectFinal(this);' /></td>" + "<td width='200'><form:label
			 * path='headQuarterName' id=lbl"+j+">"+name+"</form:label></td>" + "</tr>";
			 */
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

function getHeadQuarterListFinalWithoutMappedLBDP(list1, list3, lb) {
	var list = document.getElementById(list1);
	var list1 = document.getElementById(list3);

	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
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

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
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

			/*
			 * innerHTMLText += "<tr class='tblRowB'><td width='50'><input
			 * type='checkbox' name='headQuarterCode' id=chk"+j +"
			 * value="+listFinal[j].value+"
			 * onclick='javascript:SingleSelectFinal(this);' /></td>" + "<td width='200'><form:label
			 * path='headQuarterName' id=lbl"+j+">"+name+"</form:label></td>" + "</tr>";
			 */
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

function getHeadQuarterListFinalWithoutMappedLBDPBackReset(lb) {
	var list = document.getElementById('availddLgdLBDistCAreaSourceLUmapped');
	var list1 = document.getElementById('ddLgdLBDistCAreaDestLUmapped');

	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
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

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
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

			/*
			 * innerHTMLText += "<tr class='tblRowB'><td width='50'><input
			 * type='checkbox' name='headQuarterCode' id=chk"+j +"
			 * value="+listFinal[j].value+"
			 * onclick='javascript:SingleSelectFinal(this);' /></td>" + "<td width='200'><form:label
			 * path='headQuarterName' id=lbl"+j+">"+name+"</form:label></td>" + "</tr>";
			 */
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

function getHeadQuarterListFinalWithoutMappedLBDPYesNo(lb) {
	var list = document.getElementById('availddLgdLBDistCAreaSourceLUmapped');
	var list1 = document.getElementById('ddLgdLBDistCAreaDestLUmapped');
	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
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

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
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

			/*
			 * innerHTMLText += "<tr class='tblRowB'><td width='50'><input
			 * type='checkbox' name='headQuarterCode' id=chk"+j +"
			 * value="+listFinal[j].value+"
			 * onclick='javascript:SingleSelectFinal(this);' /></td>" + "<td width='200'><form:label
			 * path='headQuarterName' id=lbl"+j+">"+name+"</form:label></td>" + "</tr>";
			 */
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

// Code added by Arnab on 28/03/2013 for incorpration changed for Mapped LB
// ----END

function addItemforLBforvillageFULL(list1, list2, val, doAddVal, type) {

	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else if (!document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of of One Fully Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else {

					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "V" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");

				}

			}

		}
		removeSelectedValueforLB(list2, val);
	}

}

/*
 * ////////for test /////////////////// function
 * addItemforVillageLocalBody(list1,list2,val,doAddVal,type) {
 * 
 * if(document.getElementById(list2).selectedIndex>=0) { for (var j = 0; j <
 * document.getElementById(list2).options.length; j++) if
 * (document.getElementById(list2).options[j].selected==true) if (doAddVal)
 * $('#' + list1).append("<option value=" +
 * document.getElementById(list2)[j].value + "_"+ val + "_"+ "D" + ">" +
 * document.getElementById(list2)[j].text + " (" + val + ")</option>");
 * 
 * else $('#' + list1).append("<option value=" +
 * document.getElementById(list2)[j].value + " >" +
 * document.getElementById(list2)[j].text + "</option>");
 * 
 * removeSelectedValue(list2); getHeadQuarterList(list1,type); } }
 * //////////test end///////////////////
 */

function addItemforLBFinal(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Un-Mapped Land Region");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Un-Mapped Land Region");
				} else {

					// Conditional Check whether Logged in state is ULB operate
					// District wise.
					if ((typeof (isDistrictLevel) != "undefined") && $.parseJSON(isDistrictLevel)) {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "D" + ">"
										+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
					} else {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "T" + ">"
										+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
					}
				}
			}
		}
		removeSelectedValueforLB(list2, val);
	}
}

function addItemforVillageLB(list1, list2, list3, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "V" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
				}

			}

		}
		removeSelectedValueforLB(list2, val);
		getHeadQuarterListFinal(list1, list3, 'V');

	}

}

function addItemforVillageLBFinal(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "V" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
				}

			}

		}
		removeSelectedValueforLB(list2, val);
		// getHeadQuarterListFinal(list1,list3,'V');

	}

}

function addItemforVillageLBFF(list1, list2, list3, val, doAddVal) {

	var chkExist = document.getElementById('chkLgdLBExistChk');
	var chkLgdLBUnmapped = document.getElementById('chkLgdLBUnmapped');

	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Un-Mapped Land Region");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Un-Mapped Land Region");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "V" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
				}

			}

		}
		document.getElementById('getHeadQuartersV').style.visibility = 'visible';
		document.getElementById('getHeadQuartersV').style.display = 'block';
		removeSelectedValueforLB(list2, val);

		if (chkLgdLBUnmapped.checked == true && chkExist.checked == true) {
			getHeadQuarterListFinal(list1, list3, 'V');
		}
		if (chkExist.checked == true) {
			getHeadQuarterListFinal(list1, list3, 'V');
		}

		if (chkLgdLBUnmapped.checked == true && chkExist.checked == false) {
			getHeadQuarterListFinalWithoutMapped(list1, 'V');
		}

	}

}

function addItemforVillageLBFFinal(list1, list2, list3, list4, val, doAddVal) {

	var chkExist = document.getElementById('chkLgdLBExistChk');
	var chkLgdLBUnmapped = document.getElementById('chkLgdLBUnmapped');

	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Un-Mapped Land Region");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Un-Mapped Land Region");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "V" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
				}

			}

		}
		document.getElementById('getHeadQuartersV').style.visibility = 'visible';
		document.getElementById('getHeadQuartersV').style.display = 'block';
		removeSelectedValueforLB(list2, val);

		if (chkLgdLBUnmapped.checked == true && chkExist.checked == true) {
			getHeadQuarterListFinal(list1, list3, list4, 'V');
		}
		if (chkExist.checked == true) {
			getHeadQuarterListFinal(list1, list3, list4, 'V');
		}

		if (chkLgdLBUnmapped.checked == true && chkExist.checked == false) {
			getHeadQuarterListFinalWithoutMapped(list1, 'V');
		}

	}

}

function addItemforSubDistLB(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "T" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
				}

			}

		}
		removeSelectedValueforLB(list2, val);
	}

}

function addItemforLBDistrict(list1, list2, list3, list4, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "D" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
				}

			}

		}
		removeSelectedValueforLB(list2, val);
		getHeadQuarterListFinal(list1, list3, list4, 'D');
	}

}

function addItemforLBDistrictFF(list1, list2, list3, list4, val, doAddVal) {
	var chkExist = document.getElementById('chkLgdLBExistChk');
	var chkLgdLBUnmapped = document.getElementById('chkLgdLBUnmapped');
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Un-Mapped Land Region");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Un-Mapped Region");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "D" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
				}

			}

		}

		document.getElementById('getHeadQuartersD').style.visibility = 'visible';
		document.getElementById('getHeadQuartersD').style.display = 'block';

		removeSelectedValueforLB(list2, val);

		if (chkLgdLBUnmapped.checked == true && chkExist.checked == true) {
			getHeadQuarterListFinal(list1, list3, list4, 'D');
		}
		if (chkExist.checked == true) {
			getHeadQuarterListFinal(list1, list3, list4, 'D');
		}

		if (chkLgdLBUnmapped.checked == true && chkExist.checked == false) {
			getHeadQuarterListFinalWithoutMapped(list1, 'D');
		}
	}

}

function addItemforVillageLocalBody(list1, list2, val, doAddVal, type) {

	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++)
			if (document.getElementById(list2).options[j].selected == true)
				if (doAddVal)
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "D" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");

				else
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");

		removeSelectedValue(list2);

	}
}

function addItemforVillageLocalBodyDP(list1, list2, list3, list4, val, doAddVal, type) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region");
				} else {
					if (!document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
						addremoveSelectedValueforDistrictFin(list1, list2, val);
					} else {
						if (document.getElementById(list2).options[j].text.match('PART')
								|| document.getElementById(list2).options[j].text.match('PART') && val == 'PART') {
							addremoveSelectedPartDistrictFin(list1, list2, val);
						}
					}
				}
			}
		}
		// removeSelectedValue(list2);

		document.getElementById('divLgdLBCoveredAreaHeadQuarter').style.visibility = 'visible';
		document.getElementById('divLgdLBCoveredAreaHeadQuarter').style.display = 'block';
		document.getElementById('getHeadQuartersD').style.visibility = 'visible';
		document.getElementById('getHeadQuartersD').style.display = 'block';

		getHeadQuarterListFinalWithoutMappedLB(list1, list3, list4, 'D');
	}
}

function addItemforVillageLocalBodyFF(list1, list2, list3, val, doAddVal, type) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region.");
				} else {
					if (doAddVal)
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "D" + ">"
										+ document.getElementById(list2)[j].text + " (" + val + ")</option>");

					else
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
										+ "</option>");

					// removeSelectedValue(list2);
					document.getElementById('getHeadQuartersD').style.display = 'block';
					getHeadQuarterListDP(list1, list3, type);
				}
			}
		}
		removeSelectedValue(list2);
	}
}

function getHeadQuarterListDP(list1, list3, lb) {
	var list = document.getElementById(list1);
	var list1 = document.getElementById(list3);
	var list2 = document.getElementById('ddLgdLBDistCAreaDestLUmapped');

	var innerHTMLText = '';
	var listFinal = [];
	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
	for (i = 0; i < list1.length; i++) {
		listFinal[listFinal.length] = list1[i];
	}
	for (i = 0; i < list2.length; i++) {
		listFinal[listFinal.length] = list2[i];
	}
	var headquarter = "getHeadQuarters" + lb;
	document.getElementById("getHeadQuartersD").innerHTML = '';
	document.getElementById("getHeadQuartersT").innerHTML = '';
	document.getElementById("getHeadQuartersV").innerHTML = '';
	// if(list.options.length > 1)
	if (listFinal.length >= 1) {
		var name;
		if (lb == 'D')
			name = 'District';
		else if (lb == 'T')
			name = 'Sub District';
		else if (lb == 'V')
			name = 'Village';

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
				+ name + " List" + "" + "</td>" + "" + "</tr>";

		for ( var j = 0; j < listFinal.length; j++) {
			var name;

			if (listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text.replace("(FULL)", "");
			} else if (listFinal[j].text.match("(PART)")) {
				name = listFinal[j].text.replace("(PART)", "");
			}
			if (!listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text;
			}

			/*
			 * innerHTMLText += "<tr class='tblRowB'><td width='50'><input
			 * type='checkbox' name='headQuarterCode' id=chk"+j +"
			 * value="+listFinal[j].value+"
			 * onclick='javascript:SingleSelectFinal(this);' /></td>" + "<td width='200'><form:label
			 * path='headQuarterName' id=lbl"+j+">"+name+"</form:label></td>" + "</tr>";
			 */
			innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " value="
					+ listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb + "'); /></td>"
					+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td>" + "</tr>";
		}
		innerHTMLText += "</table></div>";
		document.getElementById(headquarter).innerHTML += innerHTMLText;
	}
}

function addItemforLBSubDistrict(list1, list2, list3, list4, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "T" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
				}

			}

		}
		removeSelectedValueforLB(list2, val);
		getHeadQuarterListFinal(list1, list3, list4, 'T');

	}

}

function addItemforLBSubDistrictFF(list1, list2, list3, list4, val, doAddVal) {
	var chkExist = document.getElementById('chkLgdLBExistChk');
	var chkLgdLBUnmapped = document.getElementById('chkLgdLBUnmapped');

	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Un-Mapped Land Region");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Un-Mapped Land Region");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "T" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
				}

			}

		}

		document.getElementById('getHeadQuartersT').style.visibility = 'visible';
		document.getElementById('getHeadQuartersT').style.display = 'block';

		removeSelectedValueforLB(list2, val);

		if (chkLgdLBUnmapped.checked == true && chkExist.checked == true) {
			getHeadQuarterListFinal(list1, list3, list4, 'T');
		}
		if (chkExist.checked == true) {
			getHeadQuarterListFinal(list1, list3, list4, 'T');
		}

		if (chkLgdLBUnmapped.checked == true && chkExist.checked == false) {
			getHeadQuarterListFinalWithoutMapped(list1, 'T');
		}

	}

}

function addItemforLBVillage(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('PARTLY') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Panchayat");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "V" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
				}

			}

		}
		removeSelectedValueforLB(list2, val);
	}
}

function removeAll(list1, list2, doRemoveVal) {
	for ( var i = document.getElementById(list1).length - 1; i >= 0; i--) {
		document.getElementById(list1).selectedIndex = i;

		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length - 6) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + "</option>");
		removeSelectedValue(list1);
	}
}

function removeAllVillUnmappedF(list1, list2, doRemoveVal) {
	for ( var i = document.getElementById(list1).length - 1; i >= 0; i--) {
		document.getElementById(list1).selectedIndex = i;

		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length - 6) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + "</option>");
		removeSelectedValue(list1);

		getHeadQuarterListBackButton('V');
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
							"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 5) + " >"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				else
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
		removeSelectedValue(list1);
	}
}

function removeItemLB(list1, list2, doRemoveVal) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++)
			if (document.getElementById(list1).options[j].selected == true)
				if (doRemoveVal)
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7) + " >"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				else
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
		removeSelectedValue(list1);
	}
}

function removeItemCorrectLBforVP(list1, list2, list3, doRemoveVal) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7) + " >"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
				}
			}
		}
		removeSelectedValue(list1);
		getHeadQuarterListFinalWithoutMappedLBVPFFBackReset('V');
	}
}

function removeItemCorrectLBforVPReset(list1, list2, list3, doRemoveVal) {
	removeSelectedValueDListF(list1);
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7) + " >"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
				}
			}
		}
		removeSelectedValue(list1);
		getHeadQuarterListFinalWithoutMappedLBVPFFBackReset('V');
	}
}

function removeItemVillUnmapped(list1, list2, doRemoveVal) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++)
			if (document.getElementById(list1).options[j].selected == true)
				if (doRemoveVal)
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				else
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
		removeSelectedValue(list1);
		// getHeadQuarterListFinalWithMappedUnmappedLB(list1, 'V');

		getHeadQuarterListBackButton('V');

		// document.getElementById('getHeadQuartersV').style.display = 'none';
	}
}

function removeItemVillUnmappedCreate(list1, list2, doRemoveVal) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++)
			if (document.getElementById(list1).options[j].selected == true)
				if (doRemoveVal)
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				else
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
		removeSelectedValue(list1);
		getHeadQuarterListBackReset('V');

	}
}

function removeAllVillUnmapped(list1, list2, doRemoveVal) {
	removeSelectedValueDListF(list1);
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
				}
			}
		}
		removeSelectedValue(list1);
		getHeadQuarterListBackReset('V');
	}
}

function removeItemforVPanchayat(list1, list2, doRemoveVal) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++)
			if (document.getElementById(list1).options[j].selected == true)
				if (doRemoveVal)
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				else
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
		removeSelectedValue(list1);
		getHeadQuarterListBackReset('V');

		// document.getElementById('getHeadQuartersV').style.display = 'none';

	}
}

function getHeadQuarterListBackButton(lb) {

	var list = document.getElementById('ddLgdLBVillageCAreaDestL');
	var list1 = document.getElementById('ddLgdLBVillageCAreaDestLUnmapped');
	var list2 = document.getElementById('availddLgdLBVillageCAreaSourceLUnmapped');

	var innerHTMLText = '';
	var listFinal = [];
	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
	for (i = 0; i < list1.length; i++) {
		listFinal[listFinal.length] = list1[i];
	}
	for (i = 0; i < list2.length; i++) {
		listFinal[listFinal.length] = list2[i];
	}

	var headquarter = "getHeadQuarters" + lb;
	document.getElementById("getHeadQuartersD").innerHTML = '';
	document.getElementById("getHeadQuartersT").innerHTML = '';
	document.getElementById("getHeadQuartersV").innerHTML = '';
	// if(list.options.length > 1)
	if (listFinal.length >= 1) {
		var name;
		if (lb == 'D')
			name = 'District';
		else if (lb == 'T')
			name = 'Sub District';
		else if (lb == 'V')
			name = 'Village';

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
				+ name + " List" + "" + "</td>" + "" + "</tr>";

		for ( var j = 0; j < listFinal.length; j++) {
			var name;

			if (listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text.replace("(FULL)", "");
			} else if (listFinal[j].text.match("(PART)")) {
				name = listFinal[j].text.replace("(PART)", "");
			}
			if (!listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text;
			}

			/*
			 * innerHTMLText += "<tr class='tblRowB'><td width='50'><input
			 * type='checkbox' name='headQuarterCode' id=chk"+j +"
			 * value="+listFinal[j].value+"
			 * onclick='javascript:SingleSelectFinal(this);' /></td>" + "<td width='200'><form:label
			 * path='headQuarterName' id=lbl"+j+">"+name+"</form:label></td>" + "</tr>";
			 */
			innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " value="
					+ listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb + "'); /></td>"
					+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>" + "</tr>";
		}
		innerHTMLText += "</table></div>";
		document.getElementById(headquarter).innerHTML += innerHTMLText;
	}
}

function removeAllforVPanchayat(list1, list2, doRemoveVal) {
	removeSelectedValueDListF(list1);
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
				}
			}
		}
		removeSelectedValue(list1);
		getHeadQuarterListBackReset('V');

	}
}

function getHeadQuarterListBackReset(lb) {
	var list = document.getElementById('ddLgdLBVillageCAreaDestL');
	var list1 = document.getElementById('ddLgdLBVillageCAreaDestLUnmapped');

	var innerHTMLText = '';
	var listFinal = [];
	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
	for (i = 0; i < list1.length; i++) {
		listFinal[listFinal.length] = list1[i];
	}

	var headquarter = "getHeadQuarters" + lb;
	document.getElementById("getHeadQuartersD").innerHTML = '';
	document.getElementById("getHeadQuartersT").innerHTML = '';
	document.getElementById("getHeadQuartersV").innerHTML = '';
	// if(list.options.length > 1)
	if (listFinal.length >= 1) {
		var name;
		if (lb == 'D')
			name = 'District';
		else if (lb == 'T')
			name = 'Sub District';
		else if (lb == 'V')
			name = 'Village';

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
				+ name + " List" + "" + "</td>" + "" + "</tr>";

		for ( var j = 0; j < listFinal.length; j++) {
			var name;

			if (listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text.replace("(FULL)", "");
			} else if (listFinal[j].text.match("(PART)")) {
				name = listFinal[j].text.replace("(PART)", "");
			}
			if (!listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text;
			}

			/*
			 * innerHTMLText += "<tr class='tblRowB'><td width='50'><input
			 * type='checkbox' name='headQuarterCode' id=chk"+j +"
			 * value="+listFinal[j].value+"
			 * onclick='javascript:SingleSelectFinal(this);' /></td>" + "<td width='200'><form:label
			 * path='headQuarterName' id=lbl"+j+">"+name+"</form:label></td>" + "</tr>";
			 */
			innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " value="
					+ listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb + "'); /></td>"
					+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>" + "</tr>";
		}
		innerHTMLText += "</table></div>";
		document.getElementById(headquarter).innerHTML += innerHTMLText;
	}
}

function removeItemLocalBody(list1, list2, doRemoveVal) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++)
			if (document.getElementById(list1).options[j].selected == true)
				if (doRemoveVal)
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 5)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				else
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
		removeSelectedValue(list1);
		removeSelectedValueDList('ddLgdLBDistCAreaSourceL');
		removeSelectedValueSDList('ddLgdLBSubDistrictSourceLatDCA');
		removeSelectedValueVList('ddLgdLBVillageSourceLatDCA');
		removeSelectedValueDList('ddLgdLBDistCAreaDestL');
		removeSelectedValueSDList('ddLgdLBSubDistrictDestLatDCA');
		removeSelectedValueVList('ddLgdLBVillageDestLatDCA');

		removeSelectedValueVList('ddLgdLBInterCAreaSourceL');
		removeSelectedValueDList('ddLgdLBInterCAreaDestL');
		removeSelectedValueSDList('ddLgdLBVillageSourceLatICA');
		removeSelectedValueVList('ddLgdLBVillageDestLatICA');

		removeSelectedValueSDList('ddLgdLBVillageCAreaSourceL');
		removeSelectedValueVList('ddLgdLBVillageCAreaDestL');

		getHeadQuarterListBackReset('V');

	}
}

function removeAllLocalBody(list1, list2, doRemoveVal) {
	removeSelectedValueDListF(list1);
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 5)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
				}
			}
		}
		removeSelectedValue(list1);

		removeSelectedValueDList('ddLgdLBDistCAreaSourceL');
		removeSelectedValueSDList('ddLgdLBSubDistrictSourceLatDCA');
		removeSelectedValueVList('ddLgdLBVillageSourceLatDCA');
		removeSelectedValueDList('ddLgdLBDistCAreaDestL');
		removeSelectedValueSDList('ddLgdLBSubDistrictDestLatDCA');
		removeSelectedValueVList('ddLgdLBVillageDestLatDCA');

		removeSelectedValueVList('ddLgdLBInterCAreaSourceL');
		removeSelectedValueDList('ddLgdLBInterCAreaDestL');
		removeSelectedValueSDList('ddLgdLBVillageSourceLatICA');
		removeSelectedValueVList('ddLgdLBVillageDestLatICA');

		removeSelectedValueSDList('ddLgdLBVillageCAreaSourceL');
		removeSelectedValueVList('ddLgdLBVillageCAreaDestL');

		document.getElementById('getHeadQuartersD').style.display = 'none';

	}
}

function removeItemSubdistrictList(list1, list2, doRemoveVal) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++)
			if (document.getElementById(list1).options[j].selected == true)
				if (doRemoveVal)
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				else
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
		removeSelectedValue(list1);
		removeSelectedValueVList('ddLgdLBVillageSourceLatDCA');
		removeSelectedValueSDList('ddLgdLBSubDistrictSourceLatDCA');
		removeSelectedValueSDList('ddLgdLBSubDistrictDestLatDCA');
		removeSelectedValueVList('ddLgdLBVillageDestLatDCA');

		getHeadQuarterListDPBackButton('D');

		// document.getElementById('getHeadQuartersD').style.display = 'none';

	}
}

function getHeadQuarterListDPBackButton(lb) {
	var list = document.getElementById('ddLgdLBDistCAreaDestL');
	var list1 = document.getElementById('ddLgdLBDistCAreaDestLUmapped');

	var innerHTMLText = '';
	var listFinal = [];
	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
	for (i = 0; i < list1.length; i++) {
		listFinal[listFinal.length] = list1[i];
	}

	var headquarter = "getHeadQuarters" + lb;
	document.getElementById("getHeadQuartersD").innerHTML = '';
	document.getElementById("getHeadQuartersT").innerHTML = '';
	document.getElementById("getHeadQuartersV").innerHTML = '';
	// if(list.options.length > 1)
	if (listFinal.length >= 1) {
		var name;
		if (lb == 'D')
			name = 'District';
		else if (lb == 'T')
			name = 'Sub District';
		else if (lb == 'V')
			name = 'Village';

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
				+ name + " List" + "" + "</td>" + "" + "</tr>";

		for ( var j = 0; j < listFinal.length; j++) {
			var name;

			if (listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text.replace("(FULL)", "");
			} else if (listFinal[j].text.match("(PART)")) {
				name = listFinal[j].text.replace("(PART)", "");
			}
			if (!listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text;
			}

			/*
			 * innerHTMLText += "<tr class='tblRowB'><td width='50'><input
			 * type='checkbox' name='headQuarterCode' id=chk"+j +"
			 * value="+listFinal[j].value+"
			 * onclick='javascript:SingleSelectFinal(this);' /></td>" + "<td width='200'><form:label
			 * path='headQuarterName' id=lbl"+j+">"+name+"</form:label></td>" + "</tr>";
			 */
			innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " value="
					+ listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb + "'); /></td>"
					+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>" + "</tr>";
		}
		innerHTMLText += "</table></div>";
		document.getElementById(headquarter).innerHTML += innerHTMLText;
	}
}

function removeAllSubdistrictList(list1, list2, doRemoveVal) {
	removeSelectedValueDListF(list1);
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
				}
			}
		}
		removeSelectedValue(list1);
		removeSelectedValueVList('ddLgdLBVillageSourceLatDCA');
		removeSelectedValueSDList('ddLgdLBSubDistrictSourceLatDCA');
		removeSelectedValueSDList('ddLgdLBSubDistrictDestLatDCA');
		removeSelectedValueVList('ddLgdLBVillageDestLatDCA');

		getHeadQuarterListDPBackButton('D');

		// document.getElementById('getHeadQuartersD').style.display = 'none';

	}
}

function removeItemVillageList(list1, list2, doRemoveVal) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++)
			if (document.getElementById(list1).options[j].selected == true)
				if (doRemoveVal)
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				else
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
		removeSelectedValue(list1);
		removeSelectedValueVList('ddLgdLBVillageSourceLatDCA');
		removeSelectedValueVList('ddLgdLBVillageDestLatDCA');
		removeSelectedValueVList('ddLgdLBVillageSourceLatICA');
		removeSelectedValueVList('ddLgdLBVillageDestLatICA');

		document.getElementById('getHeadQuartersT').style.display = 'none';

	}
}

function removeItemVillageListIP(list1, list2, doRemoveVal) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++)
			if (document.getElementById(list1).options[j].selected == true)
				if (doRemoveVal)
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				else
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
		removeSelectedValue(list1);
		removeSelectedValueVList('ddLgdLBVillageSourceLatDCA');
		removeSelectedValueVList('ddLgdLBVillageDestLatDCA');
		removeSelectedValueVList('ddLgdLBVillageSourceLatICA');
		removeSelectedValueVList('ddLgdLBVillageDestLatICA');

		getHeadQuarterListFinalIPBackButton('T');

		// document.getElementById('getHeadQuartersT').style.display = 'none';
	}
}

function removeAllVillageList(list1, list2, doRemoveVal) {
	removeSelectedValueDListF(list1);
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
				}
			}
		}
		removeSelectedValue(list1);
		removeSelectedValueVList('ddLgdLBVillageSourceLatDCA');
		removeSelectedValueVList('ddLgdLBVillageDestLatDCA');
		removeSelectedValueVList('ddLgdLBVillageSourceLatICA');
		removeSelectedValueVList('ddLgdLBVillageDestLatICA');
		// document.getElementById('getHeadQuartersT').style.display = 'none';
	}
}

function removeAllVillageListIP(list1, list2, doRemoveVal) {
	removeSelectedValueDListF(list1);
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
				}
			}
		}
		removeSelectedValue(list1);
		removeSelectedValueVList('ddLgdLBVillageSourceLatDCA');
		removeSelectedValueVList('ddLgdLBVillageDestLatDCA');
		removeSelectedValueVList('ddLgdLBVillageSourceLatICA');
		removeSelectedValueVList('ddLgdLBVillageDestLatICA');
		getHeadQuarterListIPBackReset('T');
	}
}

function removeAllVillageListUnMappedInterPanch(list1, list2, doRemoveVal) {

	removeSelectedValueDListF(list1);
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
				}
			}
		}
		removeSelectedValue(list1);
		removeSelectedValueVList('ddLgdLBVillageSourceLatDCA');
		removeSelectedValueVList('ddLgdLBVillageDestLatDCA');
		removeSelectedValueVList('ddLgdLBVillageSourceLatICA');
		removeSelectedValueVList('ddLgdLBVillageDestLatICA');
		getHeadQuarterListIPBackReset('T');
	}
}

function getHeadQuarterListIPBackReset(lb) {
	var list = document.getElementById('ddLgdLBInterCAreaDestL');
	var list1 = document.getElementById('ddLgdLBInterCAreaDestLUmapped');

	var innerHTMLText = '';
	var listFinal = [];
	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
	for (i = 0; i < list1.length; i++) {
		listFinal[listFinal.length] = list1[i];
	}

	var headquarter = "getHeadQuarters" + lb;
	document.getElementById("getHeadQuartersD").innerHTML = '';
	document.getElementById("getHeadQuartersT").innerHTML = '';
	document.getElementById("getHeadQuartersV").innerHTML = '';
	// if(list.options.length > 1)
	if (listFinal.length >= 1) {
		var name;
		if (lb == 'D')
			name = 'District';
		else if (lb == 'T')
			name = 'Sub District';
		else if (lb == 'V')
			name = 'Village';

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
				+ name + " List" + "" + "</td>" + "" + "</tr>";

		for ( var j = 0; j < listFinal.length; j++) {
			var name;

			if (listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text.replace("(FULL)", "");
			} else if (listFinal[j].text.match("(PART)")) {
				name = listFinal[j].text.replace("(PART)", "");
			}
			if (!listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text;
			}

			/*
			 * innerHTMLText += "<tr class='tblRowB'><td width='50'><input
			 * type='checkbox' name='headQuarterCode' id=chk"+j +"
			 * value="+listFinal[j].value+"
			 * onclick='javascript:SingleSelectFinal(this);' /></td>" + "<td width='200'><form:label
			 * path='headQuarterName' id=lbl"+j+">"+name+"</form:label></td>" + "</tr>";
			 */
			innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " value="
					+ listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb + "'); /></td>"
					+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>" + "</tr>";
		}
		innerHTMLText += "</table></div>";
		document.getElementById(headquarter).innerHTML += innerHTMLText;
	}
}

function removeItemSubdistrictListUnMapped(list1, list2, doRemoveVal) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++)
			if (document.getElementById(list1).options[j].selected == true)
				if (doRemoveVal)
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				else
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
		removeSelectedValue(list1);
		removeSelectedValueVList('ddLgdLBSubDistrictSourceLatDCAUmapped');
		removeSelectedValueSDList('ddLgdLBSubDistrictDestLatDCAUmapped');
		removeSelectedValueSDList('ddLgdLBVillageSourceLatDCAUmapped');
		removeSelectedValueVList('ddLgdLBVillageDestLatDCAUmapped');

		getHeadQuarterListDPBackButton('D');
		// document.getElementById('getHeadQuartersD').style.display = 'none';

	}
}

function removeAllSubdistrictListUnMapped(list1, list2, doRemoveVal) {
	removeSelectedValueDListF(list1);
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++)
			if (document.getElementById(list1).options[j].selected == true)
				if (doRemoveVal)
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				else
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
		removeSelectedValue(list1);
		removeSelectedValueVList('ddLgdLBSubDistrictSourceLatDCAUmapped');
		removeSelectedValueSDList('ddLgdLBSubDistrictDestLatDCAUmapped');
		removeSelectedValueSDList('ddLgdLBVillageSourceLatDCAUmapped');
		removeSelectedValueVList('ddLgdLBVillageDestLatDCAUmapped');

		getHeadQuarterListDPBackButton('D');

	}
}

function removeItemVillageListUnMapped(list1, list2, doRemoveVal) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++)
			if (document.getElementById(list1).options[j].selected == true)
				if (doRemoveVal)
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				else
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
		removeSelectedValue(list1);
		removeSelectedValueSDList('ddLgdLBVillageSourceLatDCAUmapped');
		removeSelectedValueVList('ddLgdLBVillageDestLatDCAUmapped');
		removeSelectedValueSDList('ddLgdLBVillageSourceLatICAUmapped');
		removeSelectedValueVList('ddLgdLBVillageDestLatICAUmapped');

		getHeadQuarterListFinalIPBackButton('T');

		// document.getElementById('getHeadQuartersT').style.display = 'none';
	}
}

function removeItemVillageListUnMappedIP(list1, list2, doRemoveVal) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++)
			if (document.getElementById(list1).options[j].selected == true)
				if (doRemoveVal)
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				else
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
		removeSelectedValue(list1);
		removeSelectedValueSDList('ddLgdLBVillageSourceLatDCAUmapped');
		removeSelectedValueVList('ddLgdLBVillageDestLatDCAUmapped');
		removeSelectedValueSDList('ddLgdLBVillageSourceLatICAUmapped');
		removeSelectedValueVList('ddLgdLBVillageDestLatICAUmapped');

		getHeadQuarterListIPBackReset('T');

		// document.getElementById('getHeadQuartersT').style.display = 'none';
	}
}

function getHeadQuarterListFinalIPBackButton(lb) {
	var list = document.getElementById('ddLgdLBInterCAreaDestL');
	var list2 = document.getElementById('ddLgdLBInterCAreaDestLUmapped');

	var innerHTMLText = '';
	var listFinal = [];
	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
	for (i = 0; i < list2.length; i++) {
		listFinal[listFinal.length] = list2[i];
	}

	var headquarter = "getHeadQuarters" + lb;
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

		innerHTMLText += "<div><table width='100%' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
				+ name + " List" + "" + "</td>" + "" + "</tr>";

		for ( var j = 0; j < listFinal.length; j++) {
			var name;

			if (listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text.replace("(FULL)", "");
			} else if (listFinal[j].text.match("(PART)")) {
				name = listFinal[j].text.replace("(PART)", "");
			}
			if (!listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text;
			}

			/*
			 * innerHTMLText += "<tr class='tblRowB'><td width='50'><input
			 * type='checkbox' name='headQuarterCode' id=chk"+j +"
			 * value="+listFinal[j].value+"
			 * onclick='javascript:SingleSelectFinal(this);' /></td>" + "<td width='200'><form:label
			 * path='headQuarterName' id=lbl"+j+">"+name+"</form:label></td>" + "</tr>";
			 */
			innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " value="
					+ listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb + "'); /></td>"
					+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>" + "</tr>";
		}

		innerHTMLText += "</table></div>";
		document.getElementById(headquarter).innerHTML += innerHTMLText;
	}

}

function removeAllVillageListUnMapped(list1, list2, doRemoveVal) {
	removeSelectedValueDListF(list1);
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
				}
			}
		}
		removeSelectedValue(list1);
		removeSelectedValueSDList('ddLgdLBVillageSourceLatDCAUmapped');
		removeSelectedValueVList('ddLgdLBVillageDestLatDCAUmapped');
		removeSelectedValueSDList('ddLgdLBVillageSourceLatICAUmapped');
		removeSelectedValueVList('ddLgdLBVillageDestLatICAUmapped');

		document.getElementById('getHeadQuartersT').style.display = 'none';
	}
}

function removeItemUrbanLB(list1, list2, doRemoveVal) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 5)
									+ " >"
									+ document.getElementById(list1)[j].innerText.substr(0, document.getElementById(list1)[j].innerText.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].innerText + " >" + document.getElementById(list1)[j].innerText
									+ "</option>");
				}
			}
		}
		removeSelectedValue(list1);

		removeSelectedValueSDList('ddLgdUrbanLBDistExistDest');
		removeSelectedValueSDList('ddLgdUrbanLBSubdistrictListSource');
		removeSelectedValueSDList('ddLgdUrbanLBSubdistrictListDest');
	}
}

function removeAllUrbanLB(list1, list2, doRemoveVal) {
	removeSelectedValueDListF(list1);
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 5)
									+ " >"
									+ document.getElementById(list1)[j].innerText.substr(0, document.getElementById(list1)[j].innerText.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].innerText + " >" + document.getElementById(list1)[j].innerText
									+ "</option>");
				}
			}
		}
		removeSelectedValue(list1);
		removeSelectedValueSDList('ddLgdUrbanLBDistExistDest');
		removeSelectedValueSDList('ddLgdUrbanLBSubdistrictListSource');
		removeSelectedValueSDList('ddLgdUrbanLBSubdistrictListDest');
	}
}

function removeItemListUrbanSDist(list1, list2, doRemoveVal) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++)
			if (document.getElementById(list1).options[j].selected == true)
				if (doRemoveVal)
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >"
									+ document.getElementById(list1)[j].innerText.substr(0, document.getElementById(list1)[j].innerText.length - 6)
									+ "</option>");
				else
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].innerText + " >" + document.getElementById(list1)[j].innerText
									+ "</option>");
		removeSelectedValue(list1);

		removeSelectedValueSDList('ddLgdUrbanLBSubdistrictListDest');
	}
}

function removeAllUrbanSDist(list1, list2, doRemoveVal) {
	removeSelectedValueDListF(list1);
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >"
									+ document.getElementById(list1)[j].innerText.substr(0, document.getElementById(list1)[j].innerText.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].innerText + " >" + document.getElementById(list1)[j].innerText
									+ "</option>");
				}
			}
		}
		removeSelectedValue(list1);

		removeSelectedValueSDList('ddLgdUrbanLBSubdistrictListDest');
	}
}

function removeItemUnmappedSDist(list1, list2, doRemoveVal) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++)
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal)
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >"
									+ document.getElementById(list1)[j].innerText.substr(0, document.getElementById(list1)[j].innerText.length - 6)
									+ "</option>");
				else
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].innerText + " >" + document.getElementById(list1)[j].innerText
									+ "</option>");
			}
		removeSelectedValue(list1);
	}
}

function removeAllUnmappedSDist(list1, list2, doRemoveVal) {
	removeSelectedValueDListF(list1);
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >"
									+ document.getElementById(list1)[j].innerText.substr(0, document.getElementById(list1)[j].innerText.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].innerText + " >" + document.getElementById(list1)[j].innerText
									+ "</option>");
				}
			}
		}
		removeSelectedValue(list1);
	}
}

function removeItemModify(list1, list2, doRemoveVal) {
	var count = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				count++;
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7) + " >"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");

				}

			}

		}
		removeSelectedValue(list1);
		if (document.getElementById(list1).options.length == 0) {
			// document.getElementById('getHeadQuartersD').style.display =
			// 'none';
			document.getElementById("ddLgdLBSubDistrictSourceLatDCA").options.length = 0;
			document.getElementById("ddLgdLBSubDistrictDestLatDCA").options.length = 0;
			document.getElementById("ddLgdLBVillageSourceLatDCA").options.length = 0;
			document.getElementById("ddLgdLBVillageDestLatDCA").options.length = 0;

		} else {
			var list = document.getElementById(list1);
			/*
			 * if (list.options.length == 1) {
			 * document.getElementById('getHeadQuartersD').style.display =
			 * 'none'; } if (list.options.length > 1) {
			 * document.getElementById('getHeadQuartersD').style.display =
			 * 'block'; getHeadQuarterListModify(list1, "D"); }
			 */
		}

		getHeadQuarterListFinalWithMappedUnmappedLBDPBackButton('D');
	}
}

function removeItemLevel1(list1, list2, doRemoveVal) {
	var count = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				count++;
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 5) + " >"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");

				}

			}

		}
		removeSelectedValue(list1);
		if (document.getElementById(list1).options.length == 0) {
			document.getElementById("ddLgdLBDistCAreaDestL").options.length = 0;
			document.getElementById("ddLgdLBDistCAreaSourceL").options.length = 0;
			document.getElementById("ddLgdLBSubDistrictSourceLatDCA").options.length = 0;
			document.getElementById("ddLgdLBSubDistrictDestLatDCA").options.length = 0;
			document.getElementById("ddLgdLBVillageSourceLatDCA").options.length = 0;
			document.getElementById("ddLgdLBVillageDestLatDCA").options.length = 0;
			// document.getElementById('getHeadQuartersD').style.display =
			// 'none';
		}

	}
}

var villageCodeHidden = "";
function removeItemforCovArea(list1, list2, list3, list4, doRemoveVal) {
	var count = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				count++;
				if (doRemoveVal) {
					addandremoveSelectedValueChangeCov(document.getElementById(list1).options[j].value, list2, list3, list4, list1);
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
				}
			}
		}
		getHeadQuarterListFinalWithoutMappedLBonLoadDist('D');
	}
}
function addandremoveSelectedValueChangeCov(list1, list2, list3, list4, dislist1) {
	var elSelDist = list1;
	var elSelSubDist = document.getElementById(list3);
	var elSelVillage = document.getElementById(list4);
	var diselSel = document.getElementById(dislist1);
	var districtCode = "";
	var subdistrictCode = "";
	var villageCode = "";

	/*
	 * for ( var i = 0; i < elSel.length; i++) { elSel.options[i].selected =
	 * true; districtCode += elSel.options[i].value + ","; }
	 */
	for ( var i = 0; i < elSelSubDist.options.length; i++) {
		elSelSubDist.options[i].selected = true;
		subdistrictCode += elSelSubDist.options[i].value + ",";
	}

	for ( var i = 0; i < elSelVillage.options.length; i++) {
		elSelVillage.options[i].selected = true;
		villageCode += elSelVillage.options[i].value + ",";
		villageCodeHidden += elSelVillage.options[i].value + ",";
	}
	removeMappedSubDist(elSelDist, subdistrictCode, diselSel, villageCode);
}

function removeMappedSubDist(districtCode, subdistrictCode, diselSel, villageCode) {
	var seldistCode = "";
	for ( var i = 0; i < diselSel.options.length; i++) {
		if (diselSel.options[i].selected == true) {
			seldistCode += diselSel.options[i].value + ",";
		}
	}

	var i;

	var counter = allSelectedDistricts(diselSel);
	if (diselSel.length == counter) {
		alert("You cannot remove all Districts");
	} else {
		for (i = diselSel.length - 1; i >= 0; i--) {
			if (diselSel.options[i].selected) {
				diselSel.remove(i);
			}
		}

		lgdDwrlocalBodyService.getDeleteSubDistMapped(seldistCode, subdistrictCode, {
			callback : getDeleteSubDistMappedSuccess,
			errorHandler : getgetDeleteSubDistMappedError
		});
	}
}

function allSelectedDistricts(diselSel) {
	var counter = 0;
	for ( var i = diselSel.length - 1; i >= 0; i--) {
		if (diselSel.options[i].selected) {
			counter++;
		}
	}

	return counter;
}

function getDeleteSubDistMappedSuccess(data) {

	var elSelSubDist = document.getElementById('availddLgdLBSubDistrictSourceLatDCAUmapped');
	var selVillageList = document.getElementById("availddLgdLBVillageSourceLatDCAUmapped");
	for ( var k = 0; k < data.length; k++) {
		lgdDwrlocalBodyService.getDeleteVillageMappedDP(data[k].tlc, villageCodeHidden, {
			callback : getDeleteVillagesMappedSuccess,
			errorHandler : getgetDeleteVillagesMappedError
		});
	}

	var i, j;

	for (i = 0; i < data.length; i++) {
		var vv = data[i].tlc;
		for (j = elSelSubDist.length - 1; j >= 0; j--) {
			if (elSelSubDist.options[j].selected) {
				var templistselSubDist = elSelSubDist.options[j].value;
				var listselSubDist = templistselSubDist.replace("_P_true", "").replace("_P_false", "").replace("_F_false", "").replace("_F_true", "");
				if (vv == listselSubDist) {
					elSelSubDist.remove(j);
				}
			}
		}
	}

}

function getDeleteVillagesMappedSuccess(data) {

	var elSelVillages = document.getElementById('availddLgdLBVillageSourceLatDCAUmapped');
	var i, j;

	for (i = 0; i < data.length; i++) {
		var vv = data[i].vlc;
		for (j = elSelVillages.length - 1; j >= 0; j--) {
			if (elSelVillages.options[j].selected) {
				var templistselVillages = elSelVillages.options[j].value;
				var listselVillages = templistselVillages.replace("_P_true", "").replace("_P_false", "").replace("_F_false", "").replace("_F_true",
						"");
				if (vv == listselVillages) {
					elSelVillages.remove(j);
				}
			}
		}
	}

}

function getgetDeleteSubDistMappedError() {

}

function getgetDeleteVillagesMappedError() {

}

function removeItemforcoveredsubdistic(list1, list2, list3, doRemoveVal) {
	var count = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				count++;
				if (doRemoveVal) {
					addandremoveSelectedValueforsubdisticChangeCovIP(document.getElementById(list1).options[j].value, list2, list3, list1);
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");

				}
			}
		}
		getHeadQuarterListFinalWithoutMappedLBonLoadSubDist('T');
	}
}

function removeItemforcoveredsubdisticDP(list1, list2, list3, doRemoveVal) {

	var count = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				count++;
				if (doRemoveVal) {
					addandremoveSelectedValueforsubdisticChangeCov(document.getElementById(list1).options[j].value, list2, list3, list1);
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");

				}
			}
		}
	}
}

function addandremoveSelectedValueforsubdisticChangeCovIP(list1, list2, list3, subdislist1) {
	var elSelSubDist = list1;
	var elSelVillage = document.getElementById(list3);
	var subdiselSel = document.getElementById(subdislist1);
	var villageCode = "";

	for ( var i = 0; i < elSelVillage.options.length; i++) {
		elSelVillage.options[i].selected = true;
		villageCode += elSelVillage.options[i].value + ",";
	}
	removeMappedVillageIP(elSelSubDist, villageCode, subdiselSel);
}

function removeMappedVillageIP(elSelSubDist, villageCode, subdiselSel) {
	/*
	 * lgdDwrlocalBodyService.getDeleteVillageMappedforSubDist(elSelSubDist,
	 * villageCode, { callback : getDeleteVillageMappedSuccessIP, errorHandler :
	 * getDeleteVillageMappedErrorIP });
	 */

	var selsubdistCode = "";
	for ( var i = 0; i < subdiselSel.options.length; i++) {
		if (subdiselSel.options[i].selected == true) {
			selsubdistCode += subdiselSel.options[i].value + ",";
		}
	}

	var i;

	var counter = allSelectedSubDistricts(subdiselSel);
	if (subdiselSel.length == counter) {
		alert("You cannot remove all existing Sub Districts");
	} else {
		for (i = subdiselSel.length - 1; i >= 0; i--) {
			if (subdiselSel.options[i].selected) {
				subdiselSel.remove(i);
			}

		}
		lgdDwrlocalBodyService.getDeleteVillageMappedforSubDist(selsubdistCode, villageCode, {
			callback : getDeleteVillageMappedSuccessIP,
			errorHandler : getDeleteVillageMappedErrorIP
		});

	}
}

function allSelectedSubDistricts(diselSel) {
	var counter = 0;
	for ( var i = diselSel.length - 1; i >= 0; i--) {
		if (diselSel.options[i].selected) {
			counter++;
		}
	}
	return counter;
}

function getDeleteVillageMappedSuccessIP(data) {

	var elSelVillages = document.getElementById('availddLgdLBVillageSourceLatICAUmapped');
	var i, j;

	for (i = 0; i < data.length; i++) {
		var vv = data[i].vlc;
		for (j = elSelVillages.length - 1; j >= 0; j--) {
			if (elSelVillages.options[j].selected) {
				var templistselVillages = elSelVillages.options[j].value;
				var listselVillages = templistselVillages.replace("_P_true", "").replace("_P_false", "").replace("_F_false", "").replace("_F_true",
						"");

				if (vv == listselVillages) {
					elSelVillages.remove(j);
				}
			}
		}
	}
}

function getDeleteVillageMappedErrorIP() {

}

function addandremoveSelectedValueforsubdisticChangeCov(list1, list2, list3, subdislist1) {
	var elSelSubDist = list1;
	var elSelVillage = document.getElementById(list3);
	var subdiselSel = document.getElementById(subdislist1);
	var villageCode = "";

	for ( var i = 0; i < elSelVillage.options.length; i++) {
		elSelVillage.options[i].selected = true;
		villageCode += elSelVillage.options[i].value + ",";
	}
	removeMappedVillage(elSelSubDist, villageCode, subdiselSel);
}

function removeMappedVillage(elSelSubDist, villageCode, subdiselSel) {
	var selsubdistCode = "";
	for ( var i = 0; i < subdiselSel.options.length; i++) {
		if (subdiselSel.options[i].selected == true) {
			selsubdistCode += subdiselSel.options[i].value + ",";
		}
	}
	lgdDwrlocalBodyService.getDeleteVillageMappedforSubDist(selsubdistCode, villageCode, {
		callback : getDeleteVillageMappedSuccess,
		errorHandler : getDeleteVillageMappedError
	});
	var i;
	for (i = subdiselSel.length - 1; i >= 0; i--) {
		if (subdiselSel.options[i].selected) {
			subdiselSel.remove(i);
		}
	}
}

function getDeleteVillageMappedSuccess(data) {

	var elSelVillages = document.getElementById('availddLgdLBVillageSourceLatDCAUmapped');
	var i, j;

	for (i = 0; i < data.length; i++) {
		var vv = data[i].vlc;
		for (j = elSelVillages.length - 1; j >= 0; j--) {
			if (elSelVillages.options[j].selected) {
				var templistselVillages = elSelVillages.options[j].value;
				var listselVillages = templistselVillages.replace("_P_true", "").replace("_P_false", "").replace("_F_false", "").replace("_F_true",
						"");
				if (vv == listselVillages) {
					elSelVillages.remove(j);
				}
			}
		}
	}
}

function getDeleteVillageMappedError() {

}

function removeItemforcoveredsubdisticUrban(list1, list2, doRemoveVal) {

	var count = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				count++;
				if (doRemoveVal) {
					addandremoveSelectedValueforsubdisticUrban(list1, list2);
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");

				}
			}
		}
	}
}

function removeItemforcoveredsubdisticUrbanFin(list1, list2, doRemoveVal) {
	var count = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				count++;
				if (doRemoveVal) {
					addandremoveSelectedValueforsubdisticUrbanFin(list1, list2);
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");

				}
			}
		}
	}
}

function addandremoveSelectedValueforsubdisticUrbanFin(list1, list2) {
	var elSel = document.getElementById(list1);

	var i;

	var counter = allSelectedSubDistrictsUrban(elSel);
	if (elSel.length == counter) {
		alert("You cannot remove all existing Sub Districts");
	} else {
		for (i = elSel.length - 1; i >= 0; i--) {
			if (elSel.options[i].selected) {
				elSel.remove(i);
			}
		}
	}
}

function allSelectedSubDistrictsUrban(elSel) {
	var counter = 0;
	for ( var i = elSel.length - 1; i >= 0; i--) {
		if (elSel.options[i].selected) {
			counter++;
		}
	}
	return counter;
}

function removeItemforcoveredvillage(list1, list2, doRemoveVal) {
	var count = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				count++;
				if (doRemoveVal) {
					addandremoveSelectedValueforvillage(list1, list2);
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");

				}
			}
		}

	}
}

function removeItemforcoveredvillageFin(list1, list2, doRemoveVal) {
	var count = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				count++;
				if (doRemoveVal) {
					addandremoveSelectedValueforvillageFin(list1, list2);
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");

				}
			}
		}
		getHeadQuarterListFinalWithoutMappedLBonLoad('V');

	}
}

function addandremoveSelectedValue(list1, list2) {
	var elSel = document.getElementById(list1);

	var i;
	for (i = elSel.length - 1; i >= 0; i--) {
		if (elSel.options[i].selected) {
			// alert("value=== "+elSel.options[i].value+"name
			// "+elSel.options[i].text);
			var vv = elSel.options[i].text;
			elSel.remove(i);
			// alert("finalVal"+finalVal);
			// finalVal.add(i);
			// $("#ddLgdLBDistCAreaSourceLUmapped").append("<option value='" +
			// elSel[i] + "'>" + vv + "</option");

		}
	}

}
function addandremoveSelectedValueforsubdistic(list1, list2) {
	var elSel = document.getElementById(list1);

	var i;
	for (i = elSel.length - 1; i >= 0; i--) {
		if (elSel.options[i].selected) {
			// alert("value=== "+elSel.options[i].value+"name
			// "+elSel.options[i].text);
			var vv = elSel.options[i].text;
			elSel.remove(i);
			// alert("finalVal"+finalVal);
			// finalVal.add(i);
			// $("#ddLgdLBSubDistrictSourceLatDCAUmapped").append("<option
			// value='" + elSel[i] + "'>" + vv + "</option");

		}
	}

}

function addandremoveSelectedValueforsubdisticUrban(list1, list2) {
	var elSel = document.getElementById(list1);

	var i;
	for (i = elSel.length - 1; i >= 0; i--) {
		if (elSel.options[i].selected) {
			// alert("value=== "+elSel.options[i].value+"name
			// "+elSel.options[i].text);
			var vv = elSel.options[i].text;
			elSel.remove(i);
			// alert("finalVal"+finalVal);
			// finalVal.add(i);
			// $("#ddLgdUrbanLBDistUmappedSource").append("<option value='" +
			// elSel[i] + "'>" + vv + "</option");

		}
	}

}

function addandremoveSelectedValueforvillage(list1, list2) {
	var elSel = document.getElementById(list1);

	var i;
	for (i = elSel.length - 1; i >= 0; i--) {
		if (elSel.options[i].selected) {
			// alert("value=== "+elSel.options[i].value+"name
			// "+elSel.options[i].text);
			var vv = elSel.options[i].text;
			elSel.remove(i);
			// alert("finalVal"+finalVal);
			// finalVal.add(i);
			// $("#ddLgdLBVillageSourceLatDCAUmapped").append("<option value='"
			// + elSel[i] + "'>" + vv + "</option");

		}
	}

}

function addandremoveSelectedValueforvillageFin(list1, list2) {
	var elSel = document.getElementById(list1);

	var i;

	var counter = allSelectedVillages(elSel);
	if (elSel.length == counter) {
		alert("You cannot remove all existing Villages");
	} else {
		for (i = elSel.length - 1; i >= 0; i--) {
			if (elSel.options[i].selected) {
				elSel.remove(i);
			}
		}
	}
}

function allSelectedVillages(elSel) {
	var counter = 0;
	for ( var i = elSel.length - 1; i >= 0; i--) {
		if (elSel.options[i].selected) {
			counter++;
		}
	}
	return counter;
}

function removeAllItemLevel9Urban(list1, list2, doRemoveVal) {

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
	if (document.getElementById(list1).options.length == 0) {
		document.getElementById("ddLgdUrbanLBSubdistrictListSource").options.length = 0;
		document.getElementById("ddLgdUrbanLBSubdistrictListDest").options.length = 0;
	}
}

function removeAllLevel1(list1, list2, doRemoveVal) {

	for ( var i = document.getElementById(list1).length - 1; i >= 0; i--) {
		document.getElementById(list1).selectedIndex = i;

		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 5)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length - 6) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + "</option>");
		removeSelectedValue(list1);
	}
	if (document.getElementById(list1).options.length == 0) {
		document.getElementById("ddLgdLBDistCAreaDestL").options.length = 0;
		document.getElementById("ddLgdLBDistCAreaSourceL").options.length = 0;
		document.getElementById("ddLgdLBSubDistrictSourceLatDCA").options.length = 0;
		document.getElementById("ddLgdLBSubDistrictDestLatDCA").options.length = 0;
		document.getElementById("ddLgdLBVillageSourceLatDCA").options.length = 0;
		document.getElementById("ddLgdLBVillageDestLatDCA").options.length = 0;
		document.getElementById('getHeadQuartersD').style.display = 'none';
	}
}

function removeAllVillageModify(list1, list2, doRemoveVal) {

	for ( var i = document.getElementById(list1).length - 1; i >= 0; i--) {
		document.getElementById(list1).selectedIndex = i;

		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length - 6) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + "</option>");
		removeSelectedValue(list1);
	}

	getHeadQuarterListFinalWithoutMappedLBVPBackButton(list1, 'V');
	/*
	 * if (document.getElementById(list1).options.length == 0) {
	 * document.getElementById("ddLgdLBVillageCAreaSourceL").options.length = 0;
	 * document.getElementById("ddLgdLBVillageCAreaDestL").options.length = 0;
	 * document.getElementById('getHeadQuartersV').style.display = 'none'; }
	 */
}

function removeAllModify(list1, list2, doRemoveVal) {

	for ( var i = document.getElementById(list1).length - 1; i >= 0; i--) {
		document.getElementById(list1).selectedIndex = i;

		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length - 6) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + "</option>");
		removeSelectedValue(list1);
	}
	if (document.getElementById(list1).options.length == 0) {
		// document.getElementById('getHeadQuartersD').style.display = 'none';
		document.getElementById("ddLgdLBSubDistrictSourceLatDCA").options.length = 0;
		document.getElementById("ddLgdLBSubDistrictDestLatDCA").options.length = 0;
		document.getElementById("ddLgdLBVillageSourceLatDCA").options.length = 0;
		document.getElementById("ddLgdLBVillageDestLatDCA").options.length = 0;

	} else {
		var list = document.getElementById(list1);
		/*
		 * if (list.options.length == 1) {
		 * document.getElementById('getHeadQuartersD').style.display = 'none'; }
		 * if (list.options.length > 1) {
		 * document.getElementById('getHeadQuartersD').style.display = 'block';
		 * getHeadQuarterListModify(list1, "D"); }
		 */
	}
	getHeadQuarterListFinalWithMappedUnmappedLBDPBackButton('D');

}

function removeItemLevel3(list1, list2, doRemoveVal) {
	var count = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				count++;
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7) + " >"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");

				}

			}

		}
		removeSelectedValue(list1);
		if (document.getElementById(list1).options.length == 0) {

			document.getElementById("ddLgdLBVillageSourceLatDCA").options.length = 0;
			document.getElementById("ddLgdLBVillageDestLatDCA").options.length = 0;

		}

	}
}

function removeAllLevel3(list1, list2, doRemoveVal) {

	for ( var i = document.getElementById(list1).length - 1; i >= 0; i--) {
		document.getElementById(list1).selectedIndex = i;

		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length - 6) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + "</option>");
		removeSelectedValue(list1);
	}
	if (document.getElementById(list1).options.length == 0) {
		document.getElementById("ddLgdLBVillageSourceLatDCA").options.length = 0;
		document.getElementById("ddLgdLBVillageDestLatDCA").options.length = 0;

	}
}
function removeItemLevel8(list1, list2, doRemoveVal) {
	var count = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				count++;
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 5) + " >"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");

				}

			}

		}
		removeSelectedValue(list1);
		if (document.getElementById(list1).options.length == 0) {
			document.getElementById("ddLgdLBVillageCAreaSourceL").options.length = 0;
			document.getElementById("ddLgdLBVillageCAreaDestL").options.length = 0;

		}

	}
}

function removeItemLevel9(list1, list2, doRemoveVal) {
	var count = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				count++;
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 5) + " >"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");

				}

			}

		}
		removeSelectedValue(list1);
		if (document.getElementById(list1).options.length == 0) {

			document.getElementById("ddLgdLBInterCAreaSourceL").options.length = 0;
			document.getElementById("ddLgdLBInterCAreaDestL").options.length = 0;
			document.getElementById("ddLgdLBVillageSourceLatICA").options.length = 0;
			document.getElementById("ddLgdLBVillageDestLatICA").options.length = 0;

		}

	}
}

function removeItemLevel10(list1, list2, doRemoveVal) {
	var count = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				count++;
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 5) + " >"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");

				}

			}

		}
		removeSelectedValue(list1);
		if (document.getElementById(list1).options.length == 0) {
			document.getElementById("ddLgdLBVillageSourceLatICA").options.length = 0;
			document.getElementById("ddLgdLBVillageDestLatICA").options.length = 0;

		}

		getHeadQuarterListFinalWithMappedUnmappedLBIPBackButton('T');

	}
}

function removeAllLevel10IP(list1, list2, doRemoveVal) {

	for ( var i = document.getElementById(list1).length - 1; i >= 0; i--) {
		document.getElementById(list1).selectedIndex = i;

		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length - 6) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + "</option>");
		removeSelectedValue(list1);
	}
	if (document.getElementById(list1).options.length == 0) {
		document.getElementById("ddLgdLBVillageSourceLatICA").options.length = 0;
		document.getElementById("ddLgdLBVillageDestLatICA").options.length = 0;
	}
	getHeadQuarterListFinalWithMappedUnmappedLBIPBackButton('T');

}

function removeItemLevel10IP(list1, list2, doRemoveVal) {
	var count = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				count++;
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7) + " >"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");

				}

			}

		}
		removeSelectedValue(list1);
		if (document.getElementById(list1).options.length == 0) {
			document.getElementById("ddLgdLBVillageSourceLatICA").options.length = 0;
			document.getElementById("ddLgdLBVillageDestLatICA").options.length = 0;

		}

		getHeadQuarterListFinalWithMappedUnmappedLBIPBackButton('T');

	}

}

function removeAllDistrict(list1, list2, doRemoveVal) {

	for ( var i = document.getElementById(list1).length - 1; i >= 0; i--) {
		document.getElementById(list1).selectedIndex = i;
		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length - 6) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + "</option>");
		removeSelectedValue(list1);
	}
	if (document.getElementById(list1).options.length == 0) {
		document.getElementById("ddLgdLBSubDistrictSourceLatDCAUmapped").options.length = 0;
		document.getElementById("ddLgdLBSubDistrictDestLatDCAUmapped").options.length = 0;
		document.getElementById("ddLgdLBVillageSourceLatDCAUmapped").options.length = 0;
		document.getElementById("ddLgdLBVillageDestLatDCAUmapped").options.length = 0;
	}
	getHeadQuarterListFinalWithMappedUnmappedLBDPBackButton('D');
}

function removeItemDistrict(list1, list2, doRemoveVal) {
	var count = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				count++;
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7) + " >"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");

				}

			}

		}
		removeSelectedValue(list1);
		if (document.getElementById(list1).options.length == 0) {
			document.getElementById("ddLgdLBSubDistrictSourceLatDCAUmapped").options.length = 0;
			document.getElementById("ddLgdLBSubDistrictDestLatDCAUmapped").options.length = 0;
			document.getElementById("ddLgdLBVillageSourceLatDCAUmapped").options.length = 0;
			document.getElementById("ddLgdLBVillageDestLatDCAUmapped").options.length = 0;

		}

		getHeadQuarterListFinalWithMappedUnmappedLBDPBackButton('D');

	}
}

function getHeadQuarterListFinalWithMappedUnmappedLBDPBackButton(lb) {
	var list = document.getElementById('availddLgdLBDistCAreaSourceLUmapped');
	var list2 = document.getElementById('ddLgdLBDistCAreaDestL');
	var list3 = document.getElementById('ddLgdLBDistCAreaDestLUmapped');

	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}

	for ( var i = 0; i < list2.length; i++) {
		listFinal[listFinal.length] = list2[i];
	}

	for ( var i = 0; i < list3.length; i++) {
		listFinal[listFinal.length] = list3[i];
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
				innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " checked="
						+ chkhead[j] + " value=" + listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb
						+ "'); /></td>" + "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>"
						+ "</tr>";
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

function removeItemCorrectCovforDP(list1, list2, list3, doRemoveVal) {
	var count = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				count++;
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7) + " >"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");

				}

			}

		}
		removeSelectedValue(list1);
		if (document.getElementById(list1).options.length == 0) {
			document.getElementById("ddLgdLBSubDistrictSourceLatDCAUmapped").options.length = 0;
			document.getElementById("ddLgdLBSubDistrictDestLatDCAUmapped").options.length = 0;
			document.getElementById("ddLgdLBVillageSourceLatDCAUmapped").options.length = 0;
			document.getElementById("ddLgdLBVillageDestLatDCAUmapped").options.length = 0;

		}
		getHeadQuarterListFinalWithoutMappedLBDPBackReset('D');

	}
}

function removeItemCorrectCovforDPReset(list1, list2, list3, doRemoveVal) {
	var count = 0;
	removeSelectedValueDListF(list1);
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				count++;
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7) + " >"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");

				}

			}

		}
		removeSelectedValue(list1);
		if (document.getElementById(list1).options.length == 0) {
			document.getElementById("ddLgdLBSubDistrictSourceLatDCAUmapped").options.length = 0;
			document.getElementById("ddLgdLBSubDistrictDestLatDCAUmapped").options.length = 0;
			document.getElementById("ddLgdLBVillageSourceLatDCAUmapped").options.length = 0;
			document.getElementById("ddLgdLBVillageDestLatDCAUmapped").options.length = 0;

		}
		getHeadQuarterListFinalWithoutMappedLBDPBackReset('D');

	}
}

function removeAllSubdistrict(list1, list2, doRemoveVal) {

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
	if (document.getElementById(list1).options.length == 0) {
		document.getElementById("ddLgdLBVillageSourceLatDCAUmapped").options.length = 0;
		document.getElementById("ddLgdLBVillageDestLatDCAUmapped").options.length = 0;
	}
}
function removeItemSubdistrict(list1, list2, doRemoveVal) {
	var count = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				count++;
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 4) + " >"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");

				}

			}

		}
		removeSelectedValue(list1);
		if (document.getElementById(list1).options.length == 0) {
			document.getElementById("ddLgdLBVillageSourceLatDCAUmapped").options.length = 0;
			document.getElementById("ddLgdLBVillageDestLatDCAUmapped").options.length = 0;

		}

	}
}

function removeAllSubdistrictUnmapped(list1, list2, doRemoveVal) {

	for ( var i = document.getElementById(list1).length - 1; i >= 0; i--) {
		document.getElementById(list1).selectedIndex = i;

		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length - 6) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + "</option>");
		removeSelectedValue(list1);
	}
	if (document.getElementById(list1).options.length == 0) {
		document.getElementById("ddLgdLBVillageSourceLatICAUmapped").options.length = 0;
		document.getElementById("ddLgdLBVillageDestLatICAUmapped").options.length = 0;
	}
	getHeadQuarterListFinalWithMappedUnmappedLBIPBackButton('T');
}

function removeItemSubdistrictUnmapped(list1, list2, doRemoveVal) {
	var count = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				count++;
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7) + " >"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");

				}

			}

		}
		removeSelectedValue(list1);
		if (document.getElementById(list1).options.length == 0) {
			document.getElementById("ddLgdLBVillageSourceLatICAUmapped").options.length = 0;
			document.getElementById("ddLgdLBVillageDestLatICAUmapped").options.length = 0;

		}

		getHeadQuarterListFinalWithMappedUnmappedLBIPBackButton('T');

	}
}

function getHeadQuarterListFinalWithMappedUnmappedLBIPBackButton(lb) {

	var list = document.getElementById('availddLgdLBInterCAreaSourceLUmapped');
	var list2 = document.getElementById('ddLgdLBInterCAreaDestL');
	var list3 = document.getElementById('ddLgdLBInterCAreaDestLUmapped');

	var innerHTMLText = '';
	var listFinal = [];

	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}

	for ( var i = 0; i < list2.length; i++) {
		listFinal[listFinal.length] = list2[i];
	}

	for ( var i = 0; i < list3.length; i++) {
		listFinal[listFinal.length] = list3[i];
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
				innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " checked="
						+ chkhead[j] + " value=" + listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb
						+ "'); /></td>" + "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td></br>"
						+ "</tr>";
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

function removeItemCorrectCovforIP(list1, list2, list3, doRemoveVal) {
	var count = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				count++;
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7) + " >"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");

				}

			}

		}
		removeSelectedValue(list1);
		if (document.getElementById(list1).options.length == 0) {
			document.getElementById("ddLgdLBVillageSourceLatICAUmapped").options.length = 0;
			document.getElementById("ddLgdLBVillageDestLatICAUmapped").options.length = 0;

		}
		getHeadQuarterListFinalWithoutMappedLBIPFFFBackReset('T');

	}
}

function removeItemCorrectCovforIPReset(list1, list2, list3, doRemoveVal) {
	var count = 0;
	removeSelectedValueDListF(list1);
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				count++;
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7) + " >"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");

				}

			}

		}
		removeSelectedValue(list1);
		if (document.getElementById(list1).options.length == 0) {
			document.getElementById("ddLgdLBVillageSourceLatICAUmapped").options.length = 0;
			document.getElementById("ddLgdLBVillageDestLatICAUmapped").options.length = 0;

		}
		getHeadQuarterListFinalWithoutMappedLBIPFFFBackReset('T');

	}
}

function removeItemVillage(list1, list2, doRemoveVal) {
	var count = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				count++;
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 4) + " >"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");

				}

			}

		}
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
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				else if (document.getElementById(list1)[j].value.substr(document.getElementById(list1)[j].value.length - 1) == 0)
					$('#localBodyNameEnglishListUnMapped').append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 6)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
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
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length - 6) + "</option>");
		else if (document.getElementById(list1)[document.getElementById(list1).selectedIndex].value.substr(document.getElementById(list1)[document
				.getElementById(list1).selectedIndex].value.length - 1) == 0)
			$('#localBodyNameEnglishListUnMapped').append(
					"<option value="
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].value.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].value.length - 6)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length - 6) + "</option>");
		removeSelectedValue(list1);
	}
}

function addItemCoveredArea(list1, list2, val, doAddVal) {
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

function addItemUnampped(list1, val, doAddVal) {
	if (document.getElementById('ddCoveredVillageList').selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById('ddCoveredVillageList').options.length; j++)
			if (document.getElementById('ddCoveredVillageList').options[j].selected == true)
				if (doAddVal)
					$('#' + list1).append(
							"<option value=" + document.getElementById('ddCoveredVillageList')[j].value + val + "_1 >"
									+ document.getElementById('ddCoveredVillageList')[j].text + " (" + val + ")</option>");
				else
					$('#' + list1).append(
							"<option value=" + document.getElementById('ddCoveredVillageList')[j].value + "_1 >"
									+ document.getElementById('ddCoveredVillageList')[j].text + "</option>");
		removeSelectedValue('ddCoveredVillageList');
	} else if (document.getElementById('localBodyNameEnglishListUnMapped').selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById('localBodyNameEnglishListUnMapped').options.length; j++)
			if (document.getElementById('localBodyNameEnglishListUnMapped').options[j].selected == true)
				if (doAddVal)
					$('#' + list1).append(
							"<option value=" + document.getElementById('localBodyNameEnglishListUnMapped')[j].value + val + "_0 >"
									+ document.getElementById('localBodyNameEnglishListUnMapped')[j].text + " (" + val + ")</option>");
				else
					$('#' + list1).append(
							"<option value=" + document.getElementById('localBodyNameEnglishListUnMapped')[j].value + "_0 >"
									+ document.getElementById('localBodyNameEnglishListUnMapped')[j].text + "</option>");
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

function removeSelectedValueDList(val) {
	var selObj = document.getElementById(val);
	var i;

	for (i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}

	for (i = selObj.length - 1; i >= 0; i--) {
		if (selObj.options[i].selected) {
			selObj.remove(i);
		}
	}
}

function removeSelectedValueDListF(val) {
	var selObj = document.getElementById(val);
	var i;

	for (i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}

	/*
	 * for (i = selObj.length-0; i >= 0; i--) { if (selObj.options[i].selected) {
	 * selObj.remove(i); } }
	 */
}

function removeSelectedValueSDList(val) {
	var selObj = document.getElementById(val);

	var i;

	for (i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}

	for (i = selObj.length - 1; i >= 0; i--) {
		if (selObj.options[i].selected) {
			selObj.remove(i);
		}
	}
}

function removeSelectedValueVList(val) {
	var selObj = document.getElementById(val);
	var i;

	for (i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}

	for (i = selObj.length - 1; i >= 0; i--) {
		if (selObj.options[i].selected) {
			selObj.remove(i);
		}
	}
}

function removeSelectedValueforLB(list, val) {
	var elSel = document.getElementById(list);
	var i;
	for (i = elSel.length - 1; i >= 0; i--) {
		if (elSel.options[i].selected == true) {
			if (elSel.options[i].text.match('PART') && val == 'FULL') {

			} else {
				elSel.remove(i);
			}
		}
	}
}

function removeSelectedValueforLBDisturbed(list, val) {
	var elSel = document.getElementById(list);

	var i;
	for (i = elSel.length - 1; i >= 0; i--) {
		if (elSel.options[i].selected == true) {
			if (elSel.options[i].text.match('DISTURBED') && val == 'FULL') {

			} else if (elSel.options[i].text.match('DISTURBED') && val == 'PART') {

			} else {
				elSel.remove(i);
			}
		}
	}
}

function refreshOptions(fieldId) {
	dwr.util.removeAllOptions(fieldId);
}

// Tanuj for Map Parliament Constituency retrieving ward list from contributing
// municipality

function retreiveWardListFromMunicipality() {
	// alert("Function called");
	var selObj = document.getElementById('ddLgdWardSubDistrictUListDest');
	// alert("testss"+selObj);
	var localBodyCode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		localBodyCode += selObj.options[i].value + ",";

	}
	// alert(localBodyCode);
	if (localBodyCode == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!localBodyCode.match('PART')) {
		// dwr.util.removeAllOptions('villageList');
		alert("Please Select the Part of Available Local Body ");
	} else {
		// alert("Goin to the function");
		getWardListFromMunicipality(localBodyCode);
		// alert(districtcode);
	}
}

function getWardListFromMunicipality(id) {
	// alert(id);
	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		// alert(id);
		lgdDwrlocalBodyService.getWardListFromContributingMunicipality(id, {
			callback : getWardListforCMSuccess,
			errorHandler : getWardListforCMError
		});
	}
}

function getWardListforCMSuccess(data) {

	// alert("Enter success"+data);
	var fieldId = 'ddLgdLBwardSourceLatDCA';
	var valueText = 'wardCode';
	var nameText = 'wardNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	// alert("."+fieldId+".."+valueText+"..."+nameText);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getWardListforCMError() {
	// Show a popup message
	alert("No data found!");
}
function checkRequiredInputsforPesa() {
	var dpid = document.getElementById('districtpanchyatId').selectedIndex;
	var id = document.getElementById('ddlgdLBInterAtVillage').selectedIndex;
	var count = 1;
	if (dpid == 0) {
		count = 0;
		document.getElementById("districtpanchyatIdError").innerHTML = "<font color='red'>Please Select District Panchayat </font>";
	} else if (id == 0) {
		count = 0;
		document.getElementById("intermediatepanchyatIdError").innerHTML = "<font color='red'>Please Select Intermediate Panchayat </font>";
	}
	if (count == 0)
		return false;
}
function clearlbPesaInputErrors() {
	document.getElementById("districtpanchyatIdError").innerHTML = "";
	document.getElementById("intermediatepanchyatIdError").innerHTML = "";
}
function updatePesaRecords() {
	var coverage = new Array();
	var deletedid = '';
	var modify = 1;
	var checkedValues = $('input:checkbox:checked').map(function() {
		return this.value;
	}).get();
	var previouschecked = earlyChecked.toString();
	var newchecked = checkedValues.toString();
	coverage = earlyChecked.toString().split(",");
	var temp = '';
	for ( var i = 0; i < coverage.length; i++) {
		temp = coverage[i];
		if (newchecked.indexOf(temp) == -1) {
			deletedid = deletedid + "," + temp;
		}
	}
	deletedid = deletedid.substr(1, deletedid.length);
	if (previouschecked.length == 0 && newchecked.length == 0) {
		alert("Please update the PESA Status for atleast one localbody.");
		return false;
	}

	else if (deletedid.length == 0 && previouschecked.length == newchecked.length) {
		alert("Please update the PESA Status for atleast one localbody.");
		return false;
	} else {
		document.getElementById("choosenlb").value = checkedValues;
		document.getElementById("listformat").value = deletedid;
	}
	document.forms['form1'].method = "post";
	document.forms['form1'].action = "saveLbPesaRecords.do?<csrf:token uri='saveLbPesaRecords.do'/>";
	document.forms['form1'].submit();
	return true;

}