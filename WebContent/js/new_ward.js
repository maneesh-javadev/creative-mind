var lbtypes = '';

function getLocalBodyList(localBodyTypeCode, stateCode) {
	lgdDwrlocalBodyService.getLocalBodyList(localBodyTypeCode, stateCode, {
		callback : handleLocalBodySuccess,
		errorHandler : handleLocalBodyError
	});
}
function handleLocalBodySuccess(data) {
	var fieldId = 'ddSourceVillage';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddSourceVillage');
	st.add(new Option('---------Select----------', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}
function handleLocalBodyError() {
	alert("No data found!");
}

function getLocalGovtBodyforCoveredVillageList(localBodyCode) {
	lgdDwrlocalBodyService.getLocalGovtBodyforCoveredVillageList(localBodyCode, {
		callback : handleLocalBodySuccess,
		errorHandler : handleLocalBodyError
	});
}
function handleLocalBodySuccess(data) {
	var fieldId = 'ddSourceVillage';
	var valueText = 'villageCode';
	var nameText = 'villageNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getSubdistrictListbyLBtypeforULB(id) { // id="501PART";

	lgdDwrlocalBodyService.getCoveredSubDistrictLocalBody(id, {
		callback : getCoveredLandForDistRegionforULBSuccess,
		errorHandler : getCoveredLandForDistRegionforULBError
	});
}

// data contains the returned value
function getCoveredLandForDistRegionforULBSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddLgdWardSubDistrictUListSource';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getCoveredLandForDistRegionforULBError() {
	// Show a popup message
	alert("No data found!");
}

// Urban Local Body List
function getPanchayatListbyStateandlbTypeCode(id) { // State Code Hard Coded
	// WIll Be change Later
	var temp = id.split(":");

	var id1 = temp[0];
	var id2 = temp[1];
	var stateCode = dwr.util.getValue('stateCode');

	lgdDwrlocalBodyService.getPanchayatListbyStateandlbTypeCode(stateCode, id1, {
		callback : getPanchayatListbyStateandlbTypeCodeSuccess,
		errorHandler : getPanchayatListbyStateandlbTypeCodeError
	});
}

// data contains the returned value
function getPanchayatListbyStateandlbTypeCodeSuccess(data) {
	// Assigns data to result id
	var fieldId = 'wardUrbanLocalBody';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	// villageCode,villageNameEnglish
	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('wardUrbanLocalBody');
	st.add(new Option('Select Local Body', '0'));

	var options = $("#wardUrbanLocalBody");
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

}

function getPanchayatListbyStateandlbTypeCodeError() {
	// Show a popup message
	alert("No data found!");
}

function hideShowDivforWardF(id, districtCode, lbtype, stateCode) {
	var temp = id.split(":");
	var id1 = temp[0];
	var id2 = temp[1];
	var id3 = temp[2];
	var id4 = temp[3];
	var id5 = temp[4];

	var stateCode = dwr.util.getValue('hdnStateCode');

	document.getElementById('flagCode').value = id1;
	// document.getElementById('cattype').value=category;
	document.getElementById('level').value = "";
	// added by kirandeep on 05/08/2014
	// document.getElementById('divCoveredArea').style.visibility = 'visible';
	/* comment by pooja on 09-07-2015 */
	//document.getElementById('divCoveredArea').style.display = 'block';

	document.getElementById('divLgdSelIntermediateP').style.visibility = 'hidden';
	document.getElementById('divLgdSelIntermediateP').style.display = 'none';
	document.getElementById('divLgdVillageP').style.display = 'none';
	document.getElementById('divLgdVillageP').style.visibility = 'hidden';
	document.getElementById('divLgdVillagePanch').style.display = 'none';
	document.getElementById('divLgdVillagePanch').style.visibility = 'hidden';
	document.getElementById('tr_district1').style.display = 'none';
	document.getElementById('tr_district1').style.display = 'none';
	document.getElementById('tr_intermediate1').style.display = 'none';
	document.getElementById('tr_intermediate1').style.visibility = 'hidden';
	document.getElementById('tr_village').style.display = 'none';
	document.getElementById('tr_village').style.visibility = 'hidden';

	if (id2 == 'D') {
		// added by kirandeep on 01/09/2014
		// document.getElementById('divLgdSelIntermediateP').style.visibility =
		// 'none';
		// document.getElementById('divLgdSelIntermediateP').style.display =
		// 'none';
		// document.getElementById('divLgdVillageP').style.display = 'none';
		// document.getElementById('divLgdVillageP').style.visibility =
		// 'hidden';
		// document.getElementById('divLgdVillagePanch').style.display = 'none';
		// document.getElementById('divLgdVillagePanch').style.visibility =
		// 'hidden';
		document.getElementById('tr_district1').style.display = 'none';
		// document.getElementById('tr_district1').style.visibility = 'hidden';
		document.getElementById('tr_intermediate1').style.display = 'none';
		// document.getElementById('tr_intermediate1').style.visibility =
		// 'hidden';
		document.getElementById('tr_village').style.display = 'none';
		// document.getElementById('tr_village').style.visibility = 'hidden';
		document.getElementById('tr_district1').style.display = 'block';
		document.getElementById('tr_district1').style.visibility = 'visible';
		// added by kirandeep on 01/09/2014
		// document.getElementById('divRuralDistrictPanchayat').style.visibility
		// = 'visible';
		// document.getElementById('divRuralDistrictPanchayat').style.display =
		// 'block';
		// document.getElementById('divRuralIntermediatePanchayat').style.display
		// = 'none';
		// document.getElementById('divRuralVillagePanchayat').style.visibility='visible';
		// document.getElementById('divRuralVillagePanchayat').style.display =
		// 'none';

		document.getElementById("firstlevel").innerHTML = " " + id3;

		// document.getElementById("errSelectVIP").innerHTML="Select "+id3;
		/*
		 * document.getElementById('tr_village_dist').style.display = 'block';
		 * document.getElementById('tr_village_dist').style.visibility='visible';
		 */
		if (id5 == 0) {
			// getdisPanchayatListforDP(stateCode,id1);
			if (districtCode == 0) {
				getDistrictPanchayatListforDPNewWard(id1, stateCode);
			}
			if (districtCode != 0) {
				getLandRegionWiseDis(id1, id2, districtCode, lbtype);
			}
			var idDist = id3;
			var fieldId = 'districtDP';
			var fieldId1 = 'districtDP';
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

		document.getElementById('tr_intermediate1').style.visibility = 'visible';
		document.getElementById('tr_intermediate1').style.display = 'block';
		// added by kirandeep on 01/09/2014
		// document.getElementById('divRuralIntermediatePanchayat').style.visibility
		// = 'visible';
		// document.getElementById('divRuralIntermediatePanchayat').style.display
		// = 'block';
		// document.getElementById('divRuralDistrictPanchayat').style.visibility='visible';
		// document.getElementById('divRuralDistrictPanchayat').style.display =
		// 'none';
		// document.getElementById('divRuralVillagePanchayat').style.visibility='visible';
		// document.getElementById('divRuralVillagePanchayat').style.display =
		// 'none';

		// document.getElementById('divRuralVillagePanchayat').style.visibility='visible';
		// document.getElementById('divRuralVillagePanchayat').style.display =
		// 'none';

		// document.getElementById("errSelectVDP").innerHTML="Select "+id3;
		/*
		 * document.getElementById('tr_village_inter').style.display = 'block';
		 * document.getElementById('tr_village_inter').style.visibility='visible';
		 */

		dwr.util.removeAllOptions('ddLgdLBInterPSourceList');
		if (id5 == 0) {
			getdisInterPanchayatListforIP(stateCode, id1);
		} else {
			getLocalBodyListbylblcCodeF(stateCode, id5);
		}

		var idInter = id3;
		var fieldId = 'districtIP';
		var fieldId1 = 'districtIP';
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
		document.getElementById('tr_village').style.visibility = 'visible';
		document.getElementById('tr_village').style.display = 'block';
		// added by kirandeep on 01/09/2014
		// document.getElementById('divRuralIntermediatePanchayat').style.display
		// = 'none';
		// document.getElementById('divRuralDistrictPanchayat').style.visibility='visible';
		// document.getElementById('divRuralDistrictPanchayat').style.display =
		// 'none';
		// document.getElementById('divRuralIntermediatePanchayat').style.visibility='visible';
		// document.getElementById('divRuralIntermediatePanchayat').style.display
		// = 'none';
		// document.getElementById('divRuralVillagePanchayat').style.visibility
		// = 'visible';
		// document.getElementById('divRuralVillagePanchayat').style.display =
		// 'block';

		// document.getElementById("errSelectVVP").innerHTML="Select "+id3;
		/*
		 * document.getElementById('tr_village_pan').style.display = 'block';
		 * document.getElementById('tr_village_pan').style.visibility='visible';
		 */
		// dwr.util.removeAllOptions('ddLgdLBVillageSourceAtVillageCA');
		dwr.util.removeAllOptions('ddLgdLBDistListAtVillageCA');
		var st = document.getElementById('ddLgdLBDistListAtVillageCA');
		st.add(new Option('Select', '0'));

		if (id5 == 0) {
			getdisVillagePanchayatListforVPOneLevel(stateCode, id1);
		} else {
			getLocalBodyListbylblcCodeF(stateCode, id5);
		}

		var idVill = id3;
		var fieldId = 'districtVP';
		var fieldId1 = 'districtVP';
		dwr.util.setValue(fieldId, idVill, {
			escapeHtml : false
		});
		dwr.util.setValue(fieldId1, idVill, {
			escapeHtml : false
		});

	}
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
	var fieldId = 'ddLgdLBDistListAtVillageCA';
	var fieldId1 = 'ddLgdLBInterPSourceList';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);
	var st = document.getElementById('ddLgdLBDistListAtVillageCA');
	st.add(new Option('Select', '0'));

	var options = $("#ddLgdLBDistListAtVillageCA");
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

	var st = document.getElementById('ddLgdLBInterPSourceList');
	st.add(new Option('Select', '0'));

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

	// dwr.util.addOptions(fieldId, data, valueText, nameText);
	// dwr.util.addOptions(fieldId1, data, valueText, nameText);
}

function handledisInterPanchayatErrorforIP() {
	// Show a popup message
	alert("No data found in District Panchayat!");
}

function getdisVillagePanchayatListforVP(id1, id2) {
	lgdDwrlocalBodyService.getPanchayatListbylblcCode(id1, id2, {
		callback : handledisVillagePanchayatSuccessforVP,
		errorHandler : handledisVillagePanchayatErrorforVP
	});
}

function handledisVillagePanchayatSuccessforVP(data) {

	var fieldId = 'ddLgdLBDistListAtInterCA';
	var fieldId1 = 'ddLgdLBVillageSourceAtVillageCA';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);
	var st1 = document.getElementById('ddLgdLBVillageSourceAtVillageCA');
	var st = document.getElementById('ddLgdLBDistListAtInterCA');
	st.add(new Option('Select', '0'));
	st1.add(new Option('Select', '0'));

	var options = $("#ddLgdLBDistListAtInterCA");
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

	/*
	 * var options = $("#ddLgdLBVillageSourceAtVillageCA"); $.each(data,
	 * function(key, obj) { var option = $("<option/>"); if
	 * (obj.operation_state == 'F') { $(option).attr('disabled', 'disabled');
	 * $(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
	 * options.append(option); } else {
	 * $(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
	 * options.append(option); } });
	 */

	// dwr.util.addOptions(fieldId, data, valueText, nameText);
	// dwr.util.addOptions(fieldId1, data, valueText, nameText);
}

function handledisVillagePanchayatErrorforVP() {
	// Show a popup message
	alert("No data found in District Panchayat!");
}

function getLocalBodyListbylblcCodeF(stateCode, lblc) {
	lgdDwrlocalBodyService.getLocalbodyDetailbyCode(stateCode, lblc, {
		callback : handleLocalbodylblcCodeSuccessF,
		errorHandler : handleLocalbodylblcCodeErrorF
	});
}

function handleLocalbodylblcCodeSuccessF(data) {
	var stateCode = dwr.util.getValue('hdnStateCode');
	// var category=document.getElementById('cattype').value;
	var lblc = data[0].localBodyTypeCode;
	// alert(statecode);
	var plblc = data[0].parentTierSetupCode;
	var level = data[0].level;
	var name = data[0].localBodyTypeName;
	var districtCode = dwr.util.getValue('districtCode');
	var lbtype = dwr.util.getValue('lbType');
	var lgdLBType = dwr.util.getValue('ddLgdLBType');
	var temp = lgdLBType.split(":");
	var id1 = temp[0];
	var id2 = temp[1];
	if (level == 'D') {
		document.getElementById("firstlevel").innerHTML = " " + name;
		// document.getElementById("errSelectVIP").innerHTML="Select "+name;
		if (plblc == 0) {
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
	alert("No data found in District Panchayat!");
}

function getLandRegionWiseDisFinal(id1, id2, districtCode, lbtype) {
	lgdDwrlocalBodyService.getLandRegionWise(id1, id2, districtCode, lbtype, {
		callback : handledisVillagePanchayatSuccessforVP,
		errorHandler : handledisVillagePanchayatErrorforVP
	});
}

function hideShowDivforWard(id, districtCode, lbtype, stateCode) {
	var stateCode = dwr.util.getValue('hdnStateCode');
	if (id != "" && id != "0") {
		var temp = id.split(":");

		var id1 = temp[0];
		var id2 = temp[1];
		var id3 = temp[2];

		var parenttiersetup = temp[3];

		document.getElementById('divCoveredArea').style.visibility = 'visible';
		document.getElementById('divCoveredArea').style.display = 'block';
		switch (id2) {
		case 'D':
			/*
			 * if(stateCode !=0) {
			 * getDistrictPanchayatListforDP(stateCode,lbtype,id2); }
			 */
			document.getElementById('tr_district1').style.visibility = 'visible';
			document.getElementById('tr_district1').style.display = 'block';
			document.getElementById('divRuralDistrictPanchayat').style.visibility = 'visible';
			document.getElementById('divRuralDistrictPanchayat').style.display = 'block';
			// document.getElementById('tr_intermediate1').style.visibility='visible';
			document.getElementById('tr_intermediate1').style.display = 'none';
			// document.getElementById('tr_village').style.visibility='visible';
			document.getElementById('tr_village').style.display = 'none';
			// document.getElementById('divRuralIntermediatePanchayat').style.visibility='visible';
			document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
			// document.getElementById('divRuralVillagePanchayat').style.visibility='visible';
			document.getElementById('divRuralVillagePanchayat').style.display = 'none';
			if (districtCode == 0) {
				getDistrictPanchayatListforDPNewWard(id1, stateCode);
			}
			if (districtCode != 0) {
				getLandRegionWiseDis(id1, id2, districtCode, lbtype);
			}
			if (stateCode != 0) {
				getLocalBodyListStateWiseTierSetDP(lbtype, stateCode, parenttiersetup);
			}
			break;
		case 'I':

			// document.getElementById('tr_district1').style.visibility='visible';
			document.getElementById('tr_district1').style.display = 'none';
			document.getElementById('tr_intermediate1').style.visibility = 'visible';
			document.getElementById('tr_intermediate1').style.display = 'block';
			// document.getElementById('tr_village').style.visibility='visible';
			document.getElementById('tr_village').style.display = 'none';
			document.getElementById('divRuralIntermediatePanchayat').style.visibility = 'visible';
			document.getElementById('divRuralIntermediatePanchayat').style.display = 'block';
			// document.getElementById('divRuralDistrictPanchayat').style.visibility='visible';
			document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
			// document.getElementById('divRuralVillagePanchayat').style.visibility='visible';
			document.getElementById('divRuralVillagePanchayat').style.display = 'none';

			// document.getElementById('divRuralVillagePanchayat').style.visibility='visible';
			document.getElementById('divRuralVillagePanchayat').style.display = 'none';

			if (districtCode == 0) {
				// getDistrictPanchayatListforIPNewWard(id1,stateCode);
				getDistrictPanchayatListforIP(stateCode, lbtype, id2, id3);
			}
			if (districtCode != 0) {
				getLandRegionWiseInter(id1, id2, districtCode, lbtype);
			}
			if (stateCode != 0) {
				getLocalBodyListStateWiseTierSetIP(lbtype, stateCode, parenttiersetup);
			}
			break;
		case 'V':

			// document.getElementById('tr_district1').style.visibility='visible';
			document.getElementById('tr_district1').style.display = 'none';
			// document.getElementById('tr_intermediate1').style.visibility='visible';
			document.getElementById('tr_intermediate1').style.display = 'none';
			document.getElementById('tr_village').style.visibility = 'visible';
			document.getElementById('tr_village').style.display = 'block';
			// document.getElementById('divRuralIntermediatePanchayat').style.visibility='visible';
			document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
			// document.getElementById('divRuralDistrictPanchayat').style.visibility='visible';
			document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
			// document.getElementById('divRuralIntermediatePanchayat').style.visibility='visible';
			document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
			document.getElementById('divRuralVillagePanchayat').style.visibility = 'visible';
			document.getElementById('divRuralVillagePanchayat').style.display = 'block';

			if (districtCode != 0) {
				getLandRegionWiseVill(id1, id2, districtCode, lbtype);
			}
			if (districtCode == 0) {
				getDistrictPanchayatListforVP(stateCode, lbtype, id2, id3);
			}
			if (stateCode != 0) {
				getLocalBodyListStateWiseTierSetVP(lbtype, stateCode, parenttiersetup);
			}
			break;

		default:
			document.getElementById('tr_district1').style.display = 'none';
			document.getElementById('tr_intermediate1').style.display = 'none';
			document.getElementById('tr_village').style.display = 'none';
			document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
			document.getElementById('divRuralVillagePanchayat').style.display = 'none';
			document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
		}

		/*
		 * if (id2 == 'I') { getVillagePanchbyIntercodeAtVCAFinal(id1); }
		 */

	}

}

function getLocalBodyListStateWiseTierSetDP(lbtype, stateCode, parenttiersetup) {
	lgdDwrlocalBodyService.getLocalBodyListStateWiseTierSet(lbtype, stateCode, parenttiersetup, {
		callback : handleLabelDistrictDPSuccess,
		errorHandler : handleLabelDistrictDPError
	});
}

function handleLabelDistrictDPSuccess(data) {
	var fieldId = 'districtDP';

	for ( var i = 0; i < data.length; i++) {
		var labelDetails = data[i];
		var id = labelDetails.nomenclatureEnglish;
		dwr.util.setValue(fieldId, id, {
			escapeHtml : false
		});
	}
}

function handleLabelDistrictDPError() {
	alert("No data found!");
}

function getLocalBodyListStateWiseTierSetIP(lbtype, stateCode, parenttiersetup) {

	lgdDwrlocalBodyService.getLocalBodyListStateWiseTierSet(lbtype, stateCode, parenttiersetup, {
		callback : handleLabelDistrictIPSuccess,
		errorHandler : handleLabelDistrictIPError
	});

	lgdDwrlocalBodyService.getLocalBodyListStateWiseTierSet(lbtype, stateCode, parenttiersetup - 1, {
		callback : handleLabelInterIPSuccess,
		errorHandler : handleLabelInterIPError
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

function handleLabelInterIPSuccess(data) {

	var fieldId = 'interIP';

	for ( var i = 0; i < data.length; i++) {
		var labelDetails = data[i];
		var id = labelDetails.nomenclatureEnglish;
		dwr.util.setValue(fieldId, id, {
			escapeHtml : false
		});
	}
}

function handleLabelInterIPError() {
	alert("No data found!");
}

function getLocalBodyListStateWiseTierSetVP(lbtype, stateCode, parenttiersetup) {
	lgdDwrlocalBodyService.getLocalBodyListStateWiseTierSet(lbtype, stateCode, parenttiersetup, {
		callback : handleLabelDistrictVPSuccess,
		errorHandler : handleLabelDistrictVPError
	});

	lgdDwrlocalBodyService.getLocalBodyListStateWiseTierSet(lbtype, stateCode, parenttiersetup - 1, {
		callback : handleLabelInterVPSuccess,
		errorHandler : handleLabelInterVPError
	});
	lgdDwrlocalBodyService.getLocalBodyListStateWiseTierSet(lbtype, stateCode, parenttiersetup - 2, {
		callback : handleLabelVillageVPSuccess,
		errorHandler : handleLabelVillageVPError
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

	var fieldId = 'interVP';

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

function handleLabelVillageVPSuccess(data) {

	var fieldId = 'villageVP';

	for ( var i = 0; i < data.length; i++) {
		var labelDetails = data[i];
		var id = labelDetails.nomenclatureEnglish;
		dwr.util.setValue(fieldId, id, {
			escapeHtml : false
		});
	}
}

function handleLabelVillageVPError() {
	alert("No data found!");
}

function onloadShowDivforWard() {
	var stateCode = dwr.util.getValue('stateCode');
	var districtCode = dwr.util.getValue('districtCode');
	var lbtype = dwr.util.getValue('lbType');
	var id = dwr.util.getValue('ddLgdLBType');

	if (id != "" && id != "0") {
		var temp = id.split(":");

		var id1 = temp[0];
		var id2 = temp[1];
		var id3 = temp[2];
		var id4 = temp[3];
		var id5 = temp[4];
		document.getElementById('divCoveredArea').style.visibility = 'visible';
		document.getElementById('divCoveredArea').style.display = 'block';
		switch (id2) {
		case 'D':
			/*
			 * if(stateCode !=0) {
			 * getDistrictPanchayatListforDP(stateCode,lbtype,id2); }
			 */
			document.getElementById('tr_district1').style.visibility = 'visible';
			document.getElementById('tr_district1').style.display = 'block';
			document.getElementById('divRuralDistrictPanchayat').style.visibility = 'visible';
			document.getElementById('divRuralDistrictPanchayat').style.display = 'block';
			// document.getElementById('tr_intermediate1').style.visibility='visible';
			document.getElementById('tr_intermediate1').style.display = 'none';
			// document.getElementById('tr_village').style.visibility='visible';
			document.getElementById('tr_village').style.display = 'none';
			// document.getElementById('divRuralIntermediatePanchayat').style.visibility='visible';
			document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
			// document.getElementById('divRuralVillagePanchayat').style.visibility='visible';
			document.getElementById('divRuralVillagePanchayat').style.display = 'none';
			if (districtCode == 0) {
				getDistrictPanchayatListforDP(id1, stateCode);
			}
			if (districtCode != 0) {
				getLandRegionWiseDis(id1, id2, districtCode, lbtype);
			}
			break;
		case 'I':
			if (districtCode == 0) {
				getDistrictPanchayatListforIP(stateCode, lbtype, id2, id3, id4);
			}
			if (districtCode != 0) {
				getLandRegionWiseInter(id1, id2, districtCode, lbtype);
			}
			// document.getElementById('tr_district1').style.visibility='visible';
			document.getElementById('tr_district1').style.display = 'none';
			document.getElementById('tr_intermediate1').style.visibility = 'visible';
			document.getElementById('tr_intermediate1').style.display = 'block';
			// document.getElementById('tr_village').style.visibility='visible';
			document.getElementById('tr_village').style.display = 'none';
			document.getElementById('divRuralIntermediatePanchayat').style.visibility = 'visible';
			document.getElementById('divRuralIntermediatePanchayat').style.display = 'block';
			// document.getElementById('divRuralDistrictPanchayat').style.visibility='visible';
			document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
			// document.getElementById('divRuralVillagePanchayat').style.visibility='visible';
			document.getElementById('divRuralVillagePanchayat').style.display = 'none';
			// document.getElementById('divRuralVillagePanchayat').style.visibility='visible';
			document.getElementById('divRuralVillagePanchayat').style.display = 'none';

			break;
		case 'V':
			if (districtCode != 0) {
				getLandRegionWiseVill(id1, id2, districtCode, lbtype);
			}
			if (districtCode == 0) {
				getDistrictPanchayatListforVP(stateCode, lbtype, id2, id3, id4);
			}
			// document.getElementById('tr_district1').style.visibility='visible';
			document.getElementById('tr_district1').style.display = 'none';
			// document.getElementById('tr_intermediate1').style.visibility='visible';
			document.getElementById('tr_intermediate1').style.display = 'none';
			document.getElementById('tr_village').style.visibility = 'visible';
			document.getElementById('tr_village').style.display = 'block';
			// document.getElementById('divRuralIntermediatePanchayat').style.visibility='visible';
			document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
			// document.getElementById('divRuralDistrictPanchayat').style.visibility='visible';
			document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
			// document.getElementById('divRuralIntermediatePanchayat').style.visibility='visible';
			document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
			document.getElementById('divRuralVillagePanchayat').style.visibility = 'visible';
			document.getElementById('divRuralVillagePanchayat').style.display = 'block';

			break;

		default:
			document.getElementById('tr_district1').style.display = 'none';
			document.getElementById('tr_intermediate1').style.display = 'none';
			document.getElementById('tr_village').style.display = 'none';
			document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
			document.getElementById('divRuralVillagePanchayat').style.display = 'none';
			document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
		}

		/*
		 * if (id2 == 'I') { getVillagePanchbyIntercodeAtVCAFinal(id1); }
		 */

	}

}

function getDistrictPanchayatListforDP(id1, stateCode, lbtype, lbLevel) {
	lgdDwrlocalBodyService.getPanchayatListbyStateandCategoryNewWard(stateCode, lbtype, lbLevel, {
		callback : handleDistrictPanchayatSuccessListDP,
		errorHandler : handleDistrictErrorListDP
	});

}

function getDistrictPanchayatListforDPNewWard(id1, stateCode) {
	lgdDwrlocalBodyService.getPanchayatListbyStateandCategoryNewWardF(id1, stateCode, {
		callback : handleDistrictPanchayatSuccessListDP,
		errorHandler : handleDistrictErrorListDP
	});

}

function handleDistrictPanchayatSuccessListDP(data) {
	var fieldId = 'ddSourceLocalBody';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddSourceLocalBody');
	st.add(new Option('Select', '0'));

	var options = $("#ddSourceLocalBody");
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

function getDistrictPanchayatListforIPNewWard(id1, stateCode) {
	lgdDwrlocalBodyService.getPanchayatListbyStateandCategoryNewWardF(id1, stateCode, {
		callback : handleDistrictPanchayatSuccessListDP,
		errorHandler : handleDistrictErrorListDP
	});
}

function handleDistrictPanchayatSuccessListIP(data) {
	var fieldId = 'ddLgdLBDistListAtInterCA';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddLgdLBDistListAtInterCA');
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
	if (parentyresetupCd != 0) {
		lgdDwrlocalBodyService.getPanchayatListbyStateandCategoryParentInter(stateCode, lbtype, lbLevel, parentyresetupCd, {
			callback : handleInterPanchayatSuccessListVP,
			errorHandler : handleInterErrorListVP
		});
	}
}

function handleDistrictPanchayatSuccessListVP(data) {
	var fieldId = 'ddLgdLBDistListAtVillageCA';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddLgdLBDistListAtVillageCA');
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleDistrictErrorListVP() {
	alert("No data found!");
}

function handleInterPanchayatSuccessListVP(data) {
	var fieldId = 'ddLgdLBInterListAtVillageCA';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddLgdLBInterListAtVillageCA');
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleInterErrorListVP() {
	alert("No data found!");
}

function getVillagePanchbyIntercodeAtVCAFinal(id) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleVillagePanchbyIntercodeAtVCAFinalSuccess,
		errorHandler : handleVillagePanchbyIntercodeAtVCAFinalError
	});
}

function handleVillagePanchbyIntercodeAtVCAFinalSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddLgdLBInterPSourceList';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleVillagePanchbyIntercodeAtVCAFinalError() {
	// Show a popup message
	alert("No data found!");
}

function getLandRegionWiseDis(id1, id2, districtCode, lbtype) {
	lgdDwrlocalBodyService.getLandRegionWise(id1, id2, districtCode, lbtype, {
		callback : handleLandRegionWiseSuccessDisWard,
		errorHandler : handleLandRegionWiseErrorDisWard
	});
}

function handleLandRegionWiseSuccessDisWard(data) {
	var fieldId = 'ddSourceLocalBody';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddSourceLocalBody');
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleLandRegionWiseErrorDisWard() {
	alert("No data found!");
}

function getLandRegionWiseInter(id1, id2, districtCode, lbtype) {
	lgdDwrlocalBodyService.getLandRegionWise(id1, id2, districtCode, lbtype, {
		callback : handleLandRegionWiseSuccessInterWard,
		errorHandler : handleLandRegionWiseErrorInterWard
	});

}

function handleLandRegionWiseSuccessInterWard(data) {
	var fieldId = 'ddLgdLBDistListAtInterCA';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddLgdLBDistListAtInterCA');
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleLandRegionWiseErrorInterWard() {
	alert("No data found!");
}

function getLandRegionWiseVill(id1, id2, districtCode, lbtype) {
	lgdDwrlocalBodyService.getLandRegionWise(id1, id2, districtCode, lbtype, {
		callback : handleLandRegionWiseSuccessVillWard,
		errorHandler : handleLandRegionWiseErrorVillWard
	});

}

function handleLandRegionWiseSuccessVillWard(data) {
	var fieldId = 'ddLgdLBDistListAtVillageCA';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddLgdLBDistListAtVillageCA');
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleLandRegionWiseErrorVillWard() {
	alert("No data found!");
}

function hideShowDivforViewWardF(id, districtCode, lbtype, stateCode) {
	var temp = id.split(":");
	var id1 = temp[0];
	var id2 = temp[1];
	var id3 = temp[2];
	var id4 = temp[3];
	var id5 = temp[4];
	// var stateCode = dwr.util.getValue('hdnStateCode');

	document.getElementById('flagCode').value = id1;
	// document.getElementById('cattype').value=category;
	document.getElementById('level').value = "";

	document.getElementById('divLgdSelIntermediateP').style.visibility = 'hidden';
	document.getElementById('divLgdSelIntermediateP').style.display = 'none';
	document.getElementById('divLgdVillageP').style.display = 'none';
	document.getElementById('divLgdVillageP').style.visibility = 'hidden';
	document.getElementById('divLgdVillagePanch').style.display = 'none';
	document.getElementById('divLgdVillagePanch').style.visibility = 'hidden';
	document.getElementById('tr_district1').style.display = 'none';
	document.getElementById('tr_district1').style.visibility = 'hidden';
	document.getElementById('tr_intermediate1').style.display = 'none';
	document.getElementById('tr_intermediate1').style.visibility = 'hidden';
	document.getElementById('tr_village').style.display = 'none';
	document.getElementById('tr_village').style.visibility = 'hidden';

	if (id2 == 'D') {

		// document.getElementById('divLgdSelIntermediateP').style.visibility =
		// 'none';
		// document.getElementById('divLgdSelIntermediateP').style.display =
		// 'hidden';
		document.getElementById('divLgdVillageP').style.display = 'none';
		// document.getElementById('divLgdVillageP').style.visibility =
		// 'hidden';
		document.getElementById('divLgdVillagePanch').style.display = 'none';
		// document.getElementById('divLgdVillagePanch').style.visibility =
		// 'hidden';
		document.getElementById('tr_district1').style.display = 'none';
		// document.getElementById('tr_district1').style.visibility = 'hidden';
		document.getElementById('tr_intermediate1').style.display = 'none';
		// document.getElementById('tr_intermediate1').style.visibility =
		// 'hidden';
		document.getElementById('tr_village').style.display = 'none';
		document.getElementById('tr_village').style.visibility = 'hidden';
		document.getElementById('tr_district1').style.visibility = 'visible';
		document.getElementById('tr_district1').style.display = 'block';

		document.getElementById("firstlevel").innerHTML = " " + id3;

		// document.getElementById("errSelectVIP").innerHTML="Select "+id3;
		/*
		 * document.getElementById('tr_village_dist').style.display = 'block';
		 * document.getElementById('tr_village_dist').style.visibility='visible';
		 */
		if (id5 == 0) {
			// getdisPanchayatListforDP(stateCode,id1);
			if (districtCode == 0) {
				getDistrictPanchayatListforDPNewWard(id1, stateCode);
			}
			if (districtCode != 0) {
				getLandRegionWiseDis(id1, id2, districtCode, lbtype);
			}
			var idDist = id3;
			var fieldId = 'districtDP';
			var fieldId1 = 'districtDP';
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

		document.getElementById('tr_intermediate1').style.visibility = 'visible';
		document.getElementById('tr_intermediate1').style.display = 'block';

		// document.getElementById("errSelectVDP").innerHTML="Select "+id3;
		/*
		 * document.getElementById('tr_village_inter').style.display = 'block';
		 * document.getElementById('tr_village_inter').style.visibility='visible';
		 */

		// dwr.util.removeAllOptions('ddlgdLBDistrictAtVillage');
		if (id5 == 0) {
			getdisInterPanchayatListforIP(stateCode, id1);
		} else {
			getLocalBodyListbylblcCodeF(stateCode, id5);
		}

		var idInter = id3;
		var fieldId = 'districtIP';
		var fieldId1 = 'districtIP';
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
		document.getElementById('tr_village').style.visibility = 'visible';
		document.getElementById('tr_village').style.display = 'block';

		// document.getElementById("errSelectVVP").innerHTML="Select "+id3;
		/*
		 * document.getElementById('tr_village_pan').style.display = 'block';
		 * document.getElementById('tr_village_pan').style.visibility='visible';
		 */
		dwr.util.removeAllOptions('ddLgdLBVillageSourceAtVillageCA');
		if (id5 == 0) {
			getdisVillagePanchayatListforVP(stateCode, id1);
		} else {
			getLocalBodyListbylblcCodeF(stateCode, id5);
		}

		var idVill = id3;
		var fieldId = 'districtVP';
		var fieldId1 = 'districtVP';
		dwr.util.setValue(fieldId, idVill, {
			escapeHtml : false
		});
		dwr.util.setValue(fieldId1, idVill, {
			escapeHtml : false
		});

	}
}

function hideShowDivforViewWard(id, districtCode, lbtype, stateCode) {
	if (id != "" && id != "0") {
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];
		var id3 = temp[2];
		// var id4 = temp[3];
		// var id5 = temp[4];
		var parenttiersetup = temp[3];

		switch (id2) {
		case 'D':
			document.getElementById('tr_district1').style.visibility = 'visible';
			document.getElementById('tr_district1').style.display = 'block';
			document.getElementById('tr_intermediate1').style.display = 'none';
			document.getElementById('tr_village').style.display = 'none';

			if (districtCode == 0) {
				// getDistrictPanchayatListforDP(stateCode,lbtype,id2);
				getDistrictPanchayatListforDPNewWard(id1, stateCode);
			}
			if (districtCode != 0) {
				getLandRegionWiseDis(id1, id2, districtCode, lbtype);
			}
			if (stateCode != 0) {
				getLocalBodyListStateWiseTierSetDP(lbtype, stateCode, parenttiersetup);
			}

			break;
		case 'I':

			document.getElementById('tr_district1').style.display = 'none';
			document.getElementById('tr_intermediate1').style.visibility = 'visible';
			document.getElementById('tr_intermediate1').style.display = 'block';
			document.getElementById('tr_village').style.display = 'none';

			if (districtCode == 0) {
				getDistrictPanchayatListforIP(stateCode, lbtype, id2, id3);
			}
			if (districtCode != 0) {
				getLandRegionWiseInter(id1, id2, districtCode, lbtype);
			}
			if (stateCode != 0) {
				getLocalBodyListStateWiseTierSetIP(lbtype, stateCode, parenttiersetup);
			}

			break;
		case 'V':

			document.getElementById('tr_district1').style.display = 'none';
			document.getElementById('tr_intermediate1').style.display = 'none';
			document.getElementById('tr_village').style.visibility = 'visible';
			document.getElementById('tr_village').style.display = 'block';

			if (districtCode != 0) {
				getLandRegionWiseVill(id1, id2, districtCode, lbtype);
			}
			if (districtCode == 0) {
				getDistrictPanchayatListforVP(stateCode, lbtype, id2, id3);
			}
			if (stateCode != 0) {
				getLocalBodyListStateWiseTierSetVP(lbtype, stateCode, parenttiersetup);
			}

			break;

		default:
			document.getElementById('tr_district1').style.display = 'none';
			document.getElementById('tr_intermediate1').style.display = 'none';
			document.getElementById('tr_village').style.display = 'none';
		}
		/*
		 * if (id2 == 'I') { getVillagePanchbyIntercodeAtVCAFinal(id1); }
		 */
	}
}

function hideShowDivforViewWardOnLoadFinal() {
	var stateCode = dwr.util.getValue('stateCode');
	var districtCode = dwr.util.getValue('districtCode');
	var lbtype = dwr.util.getValue('lbType');
	var id = dwr.util.getValue('ddLgdLBType');

	if (id != "" && id != "0") {
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];
		switch (id2) {
		case 'D':
			document.getElementById('tr_district1').style.visibility = 'visible';
			document.getElementById('tr_district1').style.display = 'block';
			document.getElementById('tr_intermediate1').style.display = 'none';
			document.getElementById('tr_village').style.display = 'none';
			if (districtCode != 0) {
				getLandRegionWiseDis(id1, id2, districtCode, lbtype);
			}
			break;
		case 'I':
			if (districtCode != 0) {
				getLandRegionWiseInter(id1, id2, districtCode, lbtype);
			}
			document.getElementById('tr_district1').style.display = 'none';
			document.getElementById('tr_intermediate1').style.visibility = 'visible';
			document.getElementById('tr_intermediate1').style.display = 'block';
			document.getElementById('tr_village').style.display = 'none';
			break;
		case 'V':
			if (districtCode != 0) {
				getLandRegionWiseVill(id1, id2, districtCode, lbtype);
			}
			document.getElementById('tr_district1').style.display = 'none';
			document.getElementById('tr_intermediate1').style.display = 'none';
			document.getElementById('tr_village').style.visibility = 'visible';
			document.getElementById('tr_village').style.display = 'block';
			break;

		default:
			document.getElementById('tr_district1').style.display = 'none';
			document.getElementById('tr_intermediate1').style.display = 'none';
			document.getElementById('tr_village').style.display = 'none';
		}
		/*
		 * if (id2 == 'I') { getVillagePanchbyIntercodeAtVCAFinal(id1); }
		 */
	}
}

function loadWardFormtoretain() {
	var districtCode = dwr.util.getValue('districtCode');
	var lbtype = dwr.util.getValue('lbtype');
	var stateCode = document.getElementById('hdnStateCode').value;
	hideShowDivforViewWardF(document.getElementById('ddLgdLBType').value, districtCode, lbtype, stateCode);
}

function loadwardForm() {
	hideShowDivforViewWardonload(document.getElementById('ddLgdLBType').value);
}

function hideShowDivforViewWardonload(id) {
	if (id != "" && id != "0") {
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];
		var lbtype = dwr.util.getValue('lbtype');
		var districtCode = dwr.util.getValue('districtCode');

		switch (id2) {
		case 'D':

			document.getElementById('tr_district1').style.visibility = 'visible';
			document.getElementById('tr_district1').style.display = 'block';
			document.getElementById('tr_intermediate1').style.display = 'none';
			document.getElementById('tr_village').style.display = 'none';
			if (districtCode != 0) {
				getLandRegionWiseDis(id1, id2, districtCode, lbtype);
			}
			break;
		case 'I':
			if (districtCode != 0) {
				getLandRegionWiseInter(id1, id2, districtCode, lbtype);
			}
			document.getElementById('tr_district1').style.display = 'none';
			document.getElementById('tr_intermediate1').style.visibility = 'visible';
			document.getElementById('tr_intermediate1').style.display = 'block';
			document.getElementById('tr_village').style.display = 'none';
			break;
		case 'V':
			if (districtCode != 0) {
				getLandRegionWiseVill(id1, id2, districtCode, lbtype);
			}
			document.getElementById('tr_district1').style.display = 'none';
			document.getElementById('tr_intermediate1').style.display = 'none';
			document.getElementById('tr_village').style.visibility = 'visible';
			document.getElementById('tr_village').style.display = 'block';
			break;

		default:
			document.getElementById('tr_district1').style.display = 'none';
			document.getElementById('tr_intermediate1').style.display = 'none';
			document.getElementById('tr_village').style.display = 'none';
		}
		/*
		 * if (id2 == 'I') { getVillagePanchbyIntercodeAtVCAFinal(id1); }
		 */
	}
}

function hideShowDivforUrbanWard(id) {
	var temp = id.split(":");

	var id1 = temp[0];
	var id2 = temp[1];
	// document.getElementById('divCoveredArea').style.display = 'block';
	switch (id2) {

	case 'I':
		// added by kirandeep on 01/09/2014
		// document.getElementById('tr_district1').style.display = 'block';
		$('#tr_district1').show();

		// document.getElementById('wardUrbanLocalBody').style.display =
		// 'block';
		// 'visible';
		document.getElementById('wardUrbanLocalBody').style.display = 'block';
		break;

	default:
		document.getElementById('tr_district1').style.display = 'none';

	}

}

function loadWARDforUrbanForm() {
	/*
	 * $("#localBodyType_error").hide(); $("#wardname_error").hide();
	 * $("#wardnumber_error").hide(); $("#wardUrbanLocalBody_error").hide();
	 * $("#wardUrbanLocalBodyy_error").hide();
	 * $("#ddSourceLocalBody_error").hide();
	 * $("#txtLgdLBlatitude_error").hide();
	 * $("#txtLgdLBlongitude_error").hide();
	 * $("#txtLBfileMapUpLoad_error").hide();
	 * $("#ddLgdWardSubDistrictUListDest_error").hide();
	 */

	// document.getElementById('tr_district1').style.display = 'none';
	var id = dwr.util.getValue('localBodyType');
	var stateCode = dwr.util.getValue('stateCode');

	var temp = id.split(":");

	var id1 = temp[0];
	var id2 = temp[1];
	// document.getElementById('divCoveredArea').style.display = 'block';
	switch (id2) {
	case 'I':
		$('#tr_district1').show();
		// added by kirandeep on 01/09/2014
		/* document.getElementById('tr_district1').style.visibility = 'visible'; */
		/* document.getElementById('tr_district1').style.display = 'block'; */

		/*
		 * document.getElementById('wardUrbanLocalBody').style.display =
		 * 'block';
		 */

		lgdDwrlocalBodyService.getPanchayatListbyStateandlbTypeCode(stateCode, id1, {
			callback : getPanchayatListbyStateandlbTypeCodeSuccess,
			errorHandler : getPanchayatListbyStateandlbTypeCodeError
		});

		break;

	default:
		document.getElementById('tr_district1').style.display = 'none';

	}

}

function loadWARDforPRIForm() {

	/*
	 * $("#ward_NameLocal_error").hide(); $("#txtLgdLBlatitude_error").hide();
	 * $("#txtLgdLBlongitude_error").hide();
	 * $("#txtLBfileMapUpLoad_error").hide(); $("#OrderNo_error").hide();
	 * $("#ddLgdLBDistListAtInterCA_error").hide();
	 * $("#ddLgdLBInterPSourceList_error").hide();
	 * $("#ddLgdLBDistListAtVillageCA_error").hide();
	 */

	/*
	 * $("#ddLgdLBTypeMsg").hide(); $("#ddSourceLocalBodyMsg").hide();
	 * $("#ddLgdLBDistListAtInterCAMsg").hide();
	 * $("#ddLgdLBInterPSourceListMsg").hide();
	 * $("#ddLgdLBDistListAtVillageCAMsg").hide();
	 * $("#ddLgdLBInterListAtVillageCAMsg").hide();
	 * $("#ddLgdLBVillageSourceAtVillageCAMsg").hide();
	 * $("#wardnameMsg").hide(); $("#wardnumberMsg").hide();
	 * 
	 * $("#ddLgdLBType_error").hide(); $("#ddSourceLocalBody_error").hide();
	 * $("#ddLgdLBDistListAtInterCA_error").hide();
	 * $("#ddLgdLBInterPSourceList_error").hide();
	 * $("#ddLgdLBDistListAtVillageCA_error").hide();
	 * $("#ddLgdLBInterListAtVillageCA_error").hide();
	 * $("#ddLgdLBVillageSourceAtVillageCA_error").hide();
	 * $("#wardname_error").hide(); $("#wardnumber_error").hide();
	 */

	/*
	 * document.getElementById('divCoveredArea').style.display = 'none';
	 * document.getElementById('tr_district1').style.display = 'none';
	 * document.getElementById('tr_intermediate1').style.display = 'none';
	 * document.getElementById('tr_village').style.display = 'none';
	 * document.getElementById('divRuralDistrictPanchayat').style.display =
	 * 'none'; document.getElementById('divRuralVillagePanchayat').style.display =
	 * 'none';
	 * document.getElementById('divRuralIntermediatePanchayat').style.display =
	 * 'none';
	 */
	if (document.getElementById('ddLgdLBType').value != '') {
		hideShowDivforWard(document.getElementById('ddLgdLBType').value);
	}
}

function loadViewWARDforPRIForm() {

	$("#ddLgdLBType_error").hide();
	$("#ddSourceLocalBody_error").hide();
	$("#ddLgdLBDistListAtInterCA_error").hide();
	$("#ddLgdLBInterPSourceList_error").hide();
	$("#ddLgdLBDistListAtVillageCA_error").hide();
	$("#ddLgdLBInterListAtVillageCA_error").hide();
	$("#ddLgdLBVillageSourceAtVillageCA_error").hide();

	document.getElementById('tr_district1').style.display = 'none';
	document.getElementById('tr_intermediate1').style.display = 'none';
	document.getElementById('tr_village').style.display = 'none';

}

function getCoveredDistrictList(id) {

	lgdDwrlocalBodyService.getCoveredDistrictListforLB(id, {
		callback : getCoveredLandForDistRegionSuccess,
		errorHandler : getCoveredLandForDistRegionError
	});
}

// data contains the returned value
function getCoveredLandForDistRegionSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddLgdLBDistCAreaSourceL';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';
	// villageCode,villageNameEnglish
	// dwr.util.removeAllOptions(fieldId);
	// var dataq = [ {name:"Select Subdistrict"} ];
	// dwr.util.addOptions(fieldId, dataq, "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getCoveredLandForDistRegionError() {
	// Show a popup message
	alert("No data found!");
}

function getCovereSubdistrictList(id) {

	lgdDwrlocalBodyService.getCoveredSubDistrictListforLB(id, {
		callback : getCoveredSubDistSuccess,
		errorHandler : getCoveredSubDistError
	});
}
// data contains the returned value
function getCoveredSubDistSuccess(data) {

	var fieldId = 'ddLgdLBInterCAreaSourceL';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getCoveredSubDistError() {
	// Show a popup message
	alert("No data found!");
}

function getCovereVillageList(id) {

	lgdDwrlocalBodyService.getCoveredVillageListforLB(id, {
		callback : getCovereVillageSuccess,
		errorHandler : getCovereVillageError
	});
}

// data contains the returned value
function getCovereVillageSuccess(data) {

	var fieldId = 'ddLgdLBVillageCAreaSourceL';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getCovereVillageError() {
	// Show a popup message
	alert("No data found!");
}

/*
 * function validateLocalBodyType() { var ddLBTypeCode=
 * document.getElementById('ddLgdLBType');
 * 
 * if(ddLBTypeCode.selectedIndex ==0) { $("#ddLgdLBType_error").show(); return
 * false; } $("#ddLgdLBType_error").hide(); return true; }
 */

function getVillagePanchWardbyIntercode(id) {

	lgdDwrlocalBodyService.getchildlocalbodiesWithoutCountByParentcode(id, {
		callback : handleVillagePanchWardbyIntercodeSuccess,
		errorHandler : handleVillagePanchWardbyIntercodeError
	});
}

function handleVillagePanchWardbyIntercodeSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddLgdLBVillageSourceAtVillageCA';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddLgdLBVillageSourceAtVillageCA');
	st.add(new Option('Select Village Panchayat', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleVillagePanchWardbyIntercodeError() {
	// Show a popup message
	alert("No data found!");
}

function getCoveredSubDistforWardAtInter(id) {

	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		lgdDwrlocalBodyService.getLocalGovtBodyforCoveredSubDistList(id, {
			callback : getSubDistforWardAtInterSuccess,
			errorHandler : getSubDistforWardAtInterError
		});
	}
}

// data contains the returned value
function getSubDistforWardAtInterSuccess(data) {

	var fieldId = 'ddLgdLBInterCAreaSourceL';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getSubDistforWardAtInterError() {
	// Show a popup message
	alert("No data found!");
}
/*
 * function selectAllforPRIWard() {
 * 
 * var selObj = document.getElementById('ddLgdLBDistPDestList'); var
 * districtList = ""; for (i = 0; i < selObj.options.length; i++) {
 * selObj.options[i].selected = true; districtList += selObj.options[i].value +
 * ","; }
 * 
 * getDistrictListbydistrictCode(districtList); }
 */

function getInterPanchayatWardbyDcode(id) {

	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleInterPanchayatWardSuccess,
		errorHandler : handleInterPanchayatWardError
	});
}

function handleInterPanchayatWardSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddLgdLBInterPSourceList';
	var fieldId1 = 'ddLgdLBDistListAtVillageCA';
	var fieldId2 = 'ddLgdLBVillageSourceAtVillageCA';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);
	dwr.util.removeAllOptions(fieldId2);

	var st = document.getElementById('ddLgdLBInterPSourceList');
	st.add(new Option('Select Intermediate Panchayat', '0'));
	var st = document.getElementById('ddLgdLBDistListAtVillageCA');
	st.add(new Option('Select', '0'));
	var st = document.getElementById('ddLgdLBVillageSourceAtVillageCA');
	st.add(new Option('Select', '0'));

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

	if (document.getElementById('divLgdVillageP').style.display == 'block') {
		var options = $("#ddLgdLBDistListAtVillageCA");
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
	} else {
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

	// dwr.util.addOptions(fieldId, data, valueText, nameText);
	// dwr.util.addOptions(fieldId1, data, valueText, nameText);
	// dwr.util.addOptions(fieldId2, data, valueText, nameText);
}

function handleInterPanchayatWardError() {
	// Show a popup message
	alert("No data found!");
}

function getLGBforCoveredDistListExWard(id) {

	lgdDwrlocalBodyService.getLGBforCoveredDistListExWard(id, {
		callback : getCoveredLandForDistExWardRegionSuccess,
		errorHandler : getCoveredLandForDistExWardRegionError
	});
}

// data contains the returned value
function getCoveredLandForDistExWardRegionSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddLgdLBDistCAreaSourceL';
	var fieldId1 = 'ddLgdLBDistCAreaDestL';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';
	dwr.util.removeAllOptions(fieldId1);
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getCoveredLandForDistExWardRegionError() {
	// Show a popup message
	alert("No data found!");
}

function getLGBforCoveredSubDistListExWard(id) {

	lgdDwrlocalBodyService.getLGBforCoveredSubDistListExWard(id, {
		callback : getCoveredSubDistExWardSuccess,
		errorHandler : getCoveredSubDistExWardError
	});
}

// data contains the returned value
function getCoveredSubDistExWardSuccess(data) {

	var fieldId = 'ddLgdLBInterCAreaSourceL';
	var fieldId1 = 'ddLgdLBInterCAreaDestL';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';

	dwr.util.removeAllOptions(fieldId1);
	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getCoveredSubDistExWardError() {
	// Show a popup message
	alert("No data found!");
}

function getLGBforCoveredVillageListExWard(id) {

	lgdDwrlocalBodyService.getLGBforCoveredVillageListExWard(id, {
		callback : getCoveredVillageExWardSuccess,
		errorHandler : getCoveredVillageExWardError
	});
}

// data contains the returned value
function getCoveredVillageExWardSuccess(data) {

	var fieldId = 'ddLgdLBVillageCAreaSourceL';
	var fieldId1 = 'ddLgdLBVillageCAreaDestL';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';
	dwr.util.removeAllOptions(fieldId1);
	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getCoveredVillageExWardError() {

	alert("No data found!");
}

function selectCovereVillageExWardListAtDCA() {

	var selObj = document.getElementById('ddLgdLBInterCAreaDestL');
	var subdistrictcode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		subdistrictcode += selObj.options[i].value + ",";
	}
	getCovereVillageExWardList(subdistrictcode);
}

function getCovereVillageExWardList(id) {
	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		lgdDwrlocalBodyService.getLGBforCoveredVillListforWard(id, {
			callback : getCovereVillageExWardSuccess,
			errorHandler : getCovereVillageExWardError
		});
	}
}

// data contains the returned value
function getCovereVillageExWardSuccess(data) {

	var fieldId = 'ddLgdLBVillageCAreaSourceL';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getCovereVillageExWardError() {
	// Show a popup message
	alert("No data found!");
}

function selectCovereVillageExWardListAtDCAFinal(type) {

	var selObj = document.getElementById('ddLgdLBInterCAreaDestL');
	var id = '';
	var str;
	var temp = '';
	var count = 0;
	for ( var i = 0; i < selObj.options.length; i++) {
		str = selObj.options[i].value;
		temp = str.substr(0, str.indexOf("_"));
		if (str.indexOf('PART') > -1) {
			count++;
			id = id + "," + temp;
		}

	}
	if (count == 0) {
		alert("Please Select Part Sub District");
	} else {
		lbtypes = type;
		id = id.substr(1, id.length);
		lgdDwrlocalBodyService.getLocalGovtBodyCoveredVillageListforWard(id, {
			callback : getVillageforWardSuccess,
			errorHandler : getVillageforWardError
		});
	}
}

// data contains the returned value
function getCovereVillageExWardSuccessFinal(data) {
	var fieldId = 'ddLgdLBVillageSourceLatICA';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getCovereVillageExWardErrorFinal() {
	// Show a popup message
	alert("No data found!");
}

// Get List Of SubDist For Urban Excluding Ward Fully Covered
function getCovereSubDistUrbanExWardList(id) {

	lgdDwrlocalBodyService.getLGBforCoveredSubDistUrbanListExWard(id, {
		callback : getCovereSubDistUrbanExWardSuccess,
		errorHandler : getCovereSubDistUrbanExWardError
	});
}

// data contains the returned value
function getCovereSubDistUrbanExWardSuccess(data) {
	var fieldId = 'ddLgdWardSubDistrictUListSource';
	var fieldId1 = 'ddLgdWardSubDistrictUListDest';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';
	dwr.util.removeAllOptions(fieldId1);
	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getCovereSubDistUrbanExWardError() {
	// Show a popup message
	alert("No data found!");
}

function getInterPanchayatbyDcodeAtVWard(id, val) {

	if (val == '') {
		getVillagePanchWardbyIntercode(id);
	} else {
		lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
			callback : handleInterPWardVCASuccess,
			errorHandler : handleInterPWardVCAError
		});
	}
}

function getInterPanchayatbyDcodeAtVWardFinal(id) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleInterPWardVCASuccess,
		errorHandler : handleInterPWardVCAError
	});
}

function handleInterPWardVCASuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddLgdLBInterListAtVillageCA';
	var fieldId1 = 'ddLgdLBVillageSourceAtVillageCA';

	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);
	var st = document.getElementById('ddLgdLBInterListAtVillageCA');
	st.add(new Option('Select Intermediate Panchayat', '0'));
	var st = document.getElementById('ddLgdLBVillageSourceAtVillageCA');
	st.add(new Option('Select', '0'));

	var options = $("#ddLgdLBInterListAtVillageCA");
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

function handleInterPWardVCAError() {
	// Show a popup message
	alert("No data found!");
}

// /////////////////////////////////////////////////////////////////////////

function selectDistrictforWardAtDCA() {
	document.getElementById('ddLgdLBSubDistrictDestLatDCA').options.length = 0;
	document.getElementById('ddLgdLBVillageSourceLatDCA').options.length = 0;
	document.getElementById('ddLgdLBVillageDestLatDCA').options.length = 0;
	var selObj = document.getElementById('ddLgdLBDistCAreaDestL');
	var id = '';
	var str;
	var temp = '';
	var count = 0;
	for ( var i = 0; i < selObj.options.length; i++) {
		str = selObj.options[i].value;
		temp = str.substr(0, str.indexOf("_"));
		if (str.indexOf('PART') > -1) {
			count++;
			id = id + "," + temp;
		}

	}
	if (count == 0) {
		alert("Please Select Part District");
	} else {
		id = id.substr(1, id.length);
		lgdDwrlocalBodyService.getLocalGovtBodyforCoveredSubDistListforWard(id, {
			callback : getSubDistrictListforWardSuccess,
			errorHandler : getSubDistrictListforWardError
		});
	}
}

function getSubDistrictListforWardSuccess(data) {
	var fieldId = 'ddLgdLBSubDistrictSourceLatDCA';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getSubDistrictListforWardError() {
	// Show a popup message
	alert("No data found!");
}

function selectSubdistrictforWardAtDCA(type) {
	document.getElementById('ddLgdLBVillageDestLatDCA').options.length = 0;
	var selObj = document.getElementById('ddLgdLBSubDistrictDestLatDCA');
	var id = '';
	var str;
	var temp = '';
	var count = 0;
	for ( var i = 0; i < selObj.options.length; i++) {
		str = selObj.options[i].value;
		temp = str.substr(0, str.indexOf("_"));
		if (str.indexOf('PART') > -1) {
			count++;
			id = id + "," + temp;
		}

	}
	if (count == 0) {
		alert("Please Select Part Sub District");
	} else {
		id = id.substr(1, id.length);
		lbtypes = type;
		lgdDwrlocalBodyService.getLocalGovtBodyCoveredVillageListforWard(id, {
			callback : getVillageforWardSuccess,
			errorHandler : getVillageforWardError
		});
	}

}

function getVillageforWardSuccess(data) {
	var val = lbtypes;
	var fieldId = '';
	if (val == 1)
		fieldId = 'ddLgdLBVillageSourceLatDCA';
	else if (val == 2)
		fieldId = 'ddLgdLBVillageSourceLatICA';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getVillageforWardError() {
	// Show a popup message
	alert("No data found!");
}

function getInterPanchayatforWard(id) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleInterPanchayatforWardSuccess,
		errorHandler : handleInterPanchayatforWardError
	});
}

function handleInterPanchayatforWardSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddLgdLBInterListAtVillageCA';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddLgdLBInterListAtVillageCA');
	st.add(new Option('Select Intermediate Panchayat', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleInterPanchayatforWardError() {
	// Show a popup message
	alert("No data found!");
}

function selectSubDistrictforWardAtICA() {
	var selObj = document.getElementById('ddLgdLBInterCAreaDestL');
	var subdistrictcode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		subdistrictcode += selObj.options[i].value + ",";

	}
	getVillageListforWardatICA(subdistrictcode);
}

function getVillageListforWardatICA(id) {
	if (id == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!id.match('PART')) {
		alert("Please Select PART to get Covered areas.");
	} else {
		lgdDwrlocalBodyService.getLocalGovtBodyCoveredVillageList(id, {
			callback : getVillageforWardatICASuccess,
			errorHandler : getVillageforWardatICAError
		});
	}
}

function getVillageforWardatICASuccess(data) {
	var fieldId = 'ddLgdLBVillageSourceLatICA';
	var valueText = 'villageCode';
	var nameText = 'villageNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getVillageforWardatICAError() {
	// Show a popup message
	alert("No data found!");
}

function validateViewUrbanWardAll() {
	// selectALLVillages();
	// validateUraban();

	var errors = new Array();
	var error = false;

	errors[0] = !validateLBtypeNameUrbanWard();
	errors[1] = !validateUrbanWardLBody();

	/*
	 * var ddLBTypeCode= document.getElementById('localBodyType').value; var
	 * temp = ddLBTypeCode.split(":"); var id2 = temp[1];
	 * 
	 * if (id2 == 'I') { errors[1] = !validateLBSubDistListAtInterUrban(); }
	 */

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
		showLoadingImage();
		return true;
	}
	return false;
}

function validateLBtypeNameUrbanWard() {
	var ddLgdLBType = document.getElementById('localBodyType');
	if (ddLgdLBType.selectedIndex == 0) {
		$("#localBodyType_error").show();
		return false;
	}

	$("#localBodyType_error").hide();
	return true;
}

function validateUrbanWardLBody() {
	var ddWardLB = document.getElementById('wardUrbanLocalBody');
	if (ddWardLB.selectedIndex == 0) {
		$("#wardUrbanLocalBody_error").show();
		return false;
	}

	$("#wardUrbanLocalBody_error").hide();
	return true;
}

function validateViewWardPage() {
	var errors = new Array();
	var error = false;
	errors[1] = !validateLBtypeName();

	var ddLBTypeCode = document.getElementById('ddLgdLBType').value;
	var temp = ddLBTypeCode.split(":");
	var id2 = temp[1];
	var slc = document.getElementById('hdnStateCode').value;
	var status = false;
	if (slc == 34) {
		if (document.getElementById('ddLgdLBType').selectedIndex == 0) {
			alert("Please Select Local Body Type!");
			status = false;
		} else {
			status = checkRecords(id2);
		}
		return status;

	}
	if (id2 == 'D') {

		errors[2] = !validateLBDistAtDist();
	}
	if (id2 == 'I') {
		errors[3] = !validateLBDistAtInter();
		errors[4] = !validateLBInterAtInter();
	}

	if (id2 == 'V' && slc != 13) {
		errors[5] = !validateLBDistAtVillageWard();
		var ddlgdLBDistAtVill = document.getElementById('ddLgdLBDistListAtVillageCA').value;
		if (ddlgdLBDistAtVill != 0) {
			errors[6] = !validateLBInterAtVillageWard();
		}
		errors[7] = !validateLBVillAtVillageWard();
	}

	for ( var i = 0; i < 8; i++) {
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

function validateLBtypeName() {
	var ddLgdLBType = document.getElementById('ddLgdLBType');
	if (ddLgdLBType.selectedIndex == 0) {
		$("#ddLgdLBType_error").show();
		return false;
	}

	$("#ddLgdLBType_error").hide();
	return true;
}

function validateLBDistAtDist() {

	if (document.getElementById('tr_district1').style.display == 'block') {
		var ddlgdLBDistAtInter = document.getElementById('ddSourceLocalBody');
		if (ddlgdLBDistAtInter.selectedIndex == 0) {
			$("#ddSourceLocalBody_error").show();
			return false;
		} else if (ddlgdLBDistAtInter.selectedIndex == -1) {
			$("#ddSourceLocalBody_error").show();
			return false;
		}
	}
	$("#ddSourceLocalBody_error").hide();
	return true;
}

function validateLBDistAtInter() {

	if (document.getElementById('tr_intermediate1').style.display == 'block') {
		var ddlgdLBDistAtInter = document.getElementById('ddLgdLBDistListAtInterCA');
		if (ddlgdLBDistAtInter.selectedIndex == 0) {
			$("#ddLgdLBDistListAtInterCA_error").show();
			return false;
		} else if (ddlgdLBDistAtInter.selectedIndex == -1) {
			$("#ddLgdLBDistListAtInterCA_error").show();
			return false;
		}

	}
	$("#ddLgdLBDistListAtInterCA_error").hide();
	return true;
}

function validateLBInterAtInter() {
	if (document.getElementById('tr_intermediate1').style.display == 'block') {
		var ddlgdLBDistAtInter = document.getElementById('ddLgdLBInterPSourceList');
		if (ddlgdLBDistAtInter.selectedIndex == 0) {
			$("#ddLgdLBInterPSourceList_error").show();
			return false;
		} else if (ddlgdLBDistAtInter.selectedIndex == -1) {
			$("#ddLgdLBInterPSourceList_error").show();
			return false;
		}

	}
	$("#ddLgdLBInterPSourceList_error").hide();
	return true;
}

function validateLBDistAtVillageWard() {
	if (document.getElementById('tr_village').style.display == 'block') {
		var ddlgdLBDistAtVill = document.getElementById('ddLgdLBDistListAtInterCA');
		if (ddlgdLBDistAtVill.selectedIndex == 0) {
			$("#ddLgdLBDistListAtInterCA_error").show();
			return false;
		} else if (ddlgdLBDistAtVill.selectedIndex == -1) {
			$("#ddLgdLBDistListAtInterCA_error").show();
			return false;
		}

	}
	$("#dddLgdLBDistListAtInterCA_error").hide();
	return true;
}

function validateLBDistAtVillage() {
	if (document.getElementById('tr_village').style.display == 'block') {
		var ddlgdLBDistAtInter = document.getElementById('ddLgdLBDistListAtVillageCA');
		if (ddlgdLBDistAtInter.selectedIndex == 0) {
			$("#ddLgdLBDistListAtVillageCA_error").show();
			return false;
		} else if (ddlgdLBDistAtInter.selectedIndex == -1) {
			$("#ddLgdLBDistListAtVillageCA_error").show();
			return false;
		}

	}
	$("#ddLgdLBDistListAtVillageCA_error").hide();
	return true;

}

function validateLBInterAtVillageWard() {
	if (document.getElementById('tr_village').style.display == 'block') {
		var ddlgdLBDistAtVill = document.getElementById('ddLgdLBDistListAtVillageCA');
		if (ddlgdLBDistAtVill.selectedIndex == 0) {
			$("#ddLgdLBDistListAtVillageCA_error").show();
			return false;
		} else if (ddlgdLBDistAtVill.selectedIndex == -1) {
			$("#ddLgdLBDistListAtVillageCA_error").show();
			return false;
		}
	}
	$("#ddLgdLBDistListAtVillageCA_error").hide();
	return true;
}

function validateLBInterAtVillage() {
	if (document.getElementById('tr_village').style.display == 'block') {
		var ddlgdLBDistAtInter = document.getElementById('ddLgdLBInterListAtVillageCA');
		if (ddlgdLBDistAtInter.selectedIndex == 0) {
			$("#ddLgdLBInterListAtVillageCA_error").show();
			return false;
		} else if (ddlgdLBDistAtInter.selectedIndex == -1) {
			$("#ddLgdLBInterListAtVillageCA_error").show();
			return false;
		}
	}
	$("#ddLgdLBInterListAtVillageCA_error").hide();
	return true;
}

function validateLBVillAtVillageWard() {
	if (document.getElementById('tr_village').style.display == 'block') {
		var ddlgdLBDistAtVill = document.getElementById('ddLgdLBVillageSourceAtVillageCA');
		if (ddlgdLBDistAtVill.selectedIndex == 0) {
			$("#ddLgdLBVillageSourceAtVillageCA_error").show();
			return false;
		} else if (ddlgdLBDistAtVill.selectedIndex == -1) {
			$("#ddLgdLBVillageSourceAtVillageCA_error").show();
			return false;
		}
	}
	$("#ddLgdLBVillageSourceAtVillageCA_error").hide();
	return true;
}

function validateLBVillAtVillage() {
	if (document.getElementById('tr_village').style.display == 'block') {
		var ddlgdLBDistAtInter = document.getElementById('ddLgdLBVillageSourceAtVillageCA');
		if (ddlgdLBDistAtInter.selectedIndex == 0) {
			$("#ddLgdLBVillageSourceAtVillageCA_error").show();
			return false;
		} else if (ddlgdLBDistAtInter.selectedIndex == -1) {
			$("#ddLgdLBVillageSourceAtVillageCA_error").show();
			return false;
		}
	}
	$("#ddLgdLBVillageSourceAtVillageCA_error").hide();
	return true;
}

function loadViewWARDforUrbanForm() {

	$("#localBodyType_error").hide();
	$("#wardUrbanLocalBody_error").hide();
	hideShowDivforUrbanWard(document.getElementById('localBodyType').value);
	getPanchayatListbyStateandlbTypeCode(document.getElementById('localBodyType').value);
}
function checkRecords(val) {
	var value = true;
	if (val == 'I') {
		if (document.getElementById('ddLgdLBInterPSourceList').selectedIndex == 0) {
			alert("Please Select Local Body!");
			value = false;
		}
	} else if (val == 'V') {
		if (document.getElementById('ddLgdLBDistListAtVillageCA').selectedIndex == 0) {
			alert("Please Select Intermediate Panchayat!");
			value = false;
		} else if (document.getElementById('ddLgdLBVillageSourceAtVillageCA').selectedIndex == 0) {
			alert("Please Select Village Panchayat!");
			value = false;
		}

	}
	return value;
}

function UniqueWardValidation(wardData, type, filedId) {

	var lbdetail = document.getElementById('ddLgdLBType').value;
	var temp = lbdetail.split(":");
	var lbtype = temp[1];
	var lblc = "";

	if (lbtype == 'D')
		lblc = document.getElementById('ddSourceLocalBody').value;
	else if (lbtype == 'I')
		lblc = document.getElementById('ddLgdLBInterPSourceList').value;
	else if (lbtype == 'V')
		lblc = document.getElementById('ddLgdLBVillageSourceAtVillageCA').value;
	var length = wardData.length;
	if (length > 0 && lblc > 0) {
		if (type == 1) {
			lgdDwrlocalBodyService.WardExist(lblc, wardData, type, {
				callback : UniqueWardNameSuccess,
				arg : filedId,
				errorHandler : UniqueWardNameError
			});
		} else if (type == 2) {
			lgdDwrlocalBodyService.WardExist(lblc, wardData, type, {
				callback : UniqueWardCodeSuccess,
				arg : filedId,
				errorHandler : UniqueWardCodeError
			});
		}
	}
}
function UniqueULBWardValidation(wardData, type) {

	var lblc = document.getElementById('wardUrbanLocalBody').value;

	var length = wardData.length;
	if (length > 0) {
		if (type == 1 && lblc > 0) {
			lgdDwrlocalBodyService.WardExist(lblc, wardData, type, {
				callback : UniqueWardNameSuccess,
				errorHandler : UniqueWardNameError
			});
		} else if (type == 2 && lblc > 0) {

			lgdDwrlocalBodyService.WardExist(lblc, wardData, type, {
				callback : UniqueWardCodeSuccess,
				errorHandler : UniqueWardCodeError
			});
		}
	}
}
function UniqueWardNameSuccess(data, fieldId) {
	if (data == 0) {

	} else if (data == 1) {
		alert("Ward Name already Exist Kindly Restore It Or Provide New Ward Name");
		$('#wardname' + fieldId).val('');
		$('#wardname' + fieldId).addClass("error_fld");
	} else if (data == 2) {
		alert("Ward Name already Exist in UnPublish Table Kindly Provide New Ward Name");
		$('#wardname' + fieldId).val('');
		$('#wardname' + fieldId).addClass("error_fld");
	}
}

function UniqueWardNameError() {
	document.getElementById("wardname").value = "";
	document.getElementById("wardname").focus();
}
// added by kirandeep on 01/09/2014
function UniqueWardCodeSuccess(data, fieldId) {
	if (data == 0) {

	} else if (data == 1) {
		// added by kirandeep on 01/09/2014
		alert("Ward Number already Exist Kindly Restore It Or Provide New Ward Number");
		$('#wardnumber' + fieldId).val('');
		$('#wardnumber' + fieldId).addClass("error_fld");
	} else if (data == 2) {
		alert("Ward Number already Exist in UnPublish Table Kindly Provide New Ward Number");
		$('#wardnumber' + fieldId).val('');
		$('#wardnumber' + fieldId).addClass("error_fld");
	}
}

function UniqueWardCodeError() {
	document.getElementById("wardnumber").value = "";
	document.getElementById("wardnumber").focus();
}

function UniqueWardValidationforManage(wardData, type) {

	var oldName = document.getElementById("oldWardName").value;
	var oldCode = document.getElementById("oldWardCode").value;
	var lblc = document.getElementById("lblc").value;
	var length = wardData.length;
	if (length > 0 && lblc > 0) {
		if (type == 1 && wardData != oldName) {
			lgdDwrlocalBodyService.WardExist(lblc, wardData, type, {
				callback : UniqueWardNameSuccess,
				errorHandler : UniqueWardNameError
			});
		} else if (type == 2 && wardData != oldCode) {
			lgdDwrlocalBodyService.WardExist(lblc, wardData, type, {
				callback : UniqueWardCodeSuccess,
				errorHandler : UniqueWardCodeError
			});
		}
	}
}

function addItem(list1, list2, val, doAddVal, type) {
	var str = '';
	var temp = "_" + val + "_" + type;
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var i = 0; i < document.getElementById(list2).options.length; i++) {
			if (document.getElementById(list2).options[i].selected == true) {
				str = document.getElementById(list2)[i].text;
				if (str.indexOf('PART') > -1) {
					alert("Please Select Part");
					return false;
				}
			}
		}
		for ( var j = 0; j < document.getElementById(list2).options.length; j++)

			if (document.getElementById(list2).options[j].selected == true)

				$('#' + list1).append(
						"<option value=" + document.getElementById(list2)[j].value + temp + ">" + document.getElementById(list2)[j].text + " (" + val
								+ ")</option>");

		removeSelectedValue(list2);
	}
}

// added by kirandeep on 01/09/2014
function resetNewWArdInputs() {
	document.getElementById('wardname').value = "";
	document.getElementById('wardnumber').value = "";
	document.getElementById('wardnamelocal').value = "";
	document.getElementById('ddLgdLBDistCAreaDestL').options.length = 0;
	document.getElementById('ddLgdLBSubDistrictSourceLatDCA').options.length = 0;
	document.getElementById('ddLgdLBSubDistrictDestLatDCA').options.length = 0;
	document.getElementById('ddLgdLBVillageSourceLatDCA').options.length = 0;
	document.getElementById('ddLgdLBVillageDestLatDCA').options.length = 0;
	document.getElementById('ddLgdLBInterCAreaDestL').options.length = 0;
	document.getElementById('ddLgdLBVillageSourceLatICA').options.length = 0;
	document.getElementById('ddLgdLBVillageDestLatICA').options.length = 0;
	document.getElementById('ddLgdLBVillageCAreaDestL').options.length = 0;
}

function addItemforWard(list1, list2, val, doAddVal, type) {
	var str = '';
	var temp = "_" + val + "_" + type;
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var i = 0; i < document.getElementById(list2).options.length; i++) {
			if (document.getElementById(list2).options[i].selected == true) {
				str = document.getElementById(list2)[i].text;
				if (str.indexOf('PART') > -1) {
					alert("Please Select Part");
					return false;
				}
			}
		}
		for ( var j = 0; j < document.getElementById(list2).options.length; j++)

			if (document.getElementById(list2).options[j].selected == true)

				$('#' + list1).append(
						"<option value=" + document.getElementById(list2)[j].value + temp + ">" + document.getElementById(list2)[j].text + " (" + val
								+ ")</option>");

		removeSelectedValue(list2);
	}
}

function removeItemVillListSubListWardULB(list1, list2, doRemoveVal) {
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

	}

}
function removeAllVillageListforWardULB(list1, list2, doRemoveVal) {

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

	}
}

function clearMethod() {
	document.getElementById('wardname').value = "";
	document.getElementById('wardnumber').value = "";
	// added by kirandeep on 01/09/2014
	document.getElementById('wardnamelocal').value = "";
	document.getElementById('ddLgdLBDistCAreaDestL').options.length = 0;
	document.getElementById('ddLgdLBSubDistrictSourceLatDCA').options.length = 0;
	document.getElementById('ddLgdLBSubDistrictDestLatDCA').options.length = 0;
	document.getElementById('ddLgdLBVillageSourceLatDCA').options.length = 0;
	document.getElementById('ddLgdLBVillageDestLatDCA').options.length = 0;
	document.getElementById('ddLgdLBInterCAreaDestL').options.length = 0;
	document.getElementById('ddLgdLBVillageSourceLatICA').options.length = 0;
	document.getElementById('ddLgdLBVillageDestLatICA').options.length = 0;
	document.getElementById('ddLgdLBVillageCAreaDestL').options.length = 0;
	document.getElementById("ddLgdLBType").selectedIndex = 0;
	document.getElementById("ddSourceLocalBody").options.length = 0;
	document.getElementById("ddLgdLBDistListAtInterCA").options.length = 0;
	document.getElementById("ddLgdLBInterPSourceList").options.length = 0;
	document.getElementById("ddLgdLBDistListAtVillageCA").options.length = 0;
	document.getElementById("ddLgdLBVillageSourceAtVillageCA").options.length = 0;

}

function UniqueWardValidationforUrban(wardData, type, filedId) {
	var lblc = document.getElementById('wardUrbanLocalBody').value;
	var length = wardData.length;
	if (length > 0 && lblc > 0) {
		if (type == 1) {
			lgdDwrlocalBodyService.WardExist(lblc, wardData, type, {
				callback : UniqueWardNameSuccess,
				arg : filedId,
				errorHandler : UniqueWardNameError
			});
		} else if (type == 2) {
			lgdDwrlocalBodyService.WardExist(lblc, wardData, type, {
				callback : UniqueWardCodeSuccess,
				arg : filedId,
				errorHandler : UniqueWardCodeError
			});
		}
	}
}

function validateSpecialCharactersWardNLocalnew(object) {

	var retVal = true;
	retVal = validateSpecialCharacters(object.value);
	if (retVal == false) {
		alert("Ward Name in Local contains invalid characters");
		var obj = object;
		obj.value = "";
		return false;
	} else {
		return true;
	}
}

function getdisVillagePanchayatListforVPOneLevel(id1, id2) {
	lgdDwrlocalBodyService.getPanchayatListbylblcCode(id1, id2, {
		callback : handledisVillagePanchayatSuccessforVPOneLevel,
		errorHandler : handledisVillagePanchayatErrorforVP
	});
}

function handledisVillagePanchayatSuccessforVPOneLevel(data) {

	var fieldId = 'ddLgdLBDistListAtInterCA';
	var fieldId1 = 'ddLgdLBVillageSourceAtVillageCA';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);
	var st1 = document.getElementById('ddLgdLBVillageSourceAtVillageCA');
	var st = document.getElementById('ddLgdLBDistListAtInterCA');
	st.add(new Option('Select', '0'));
	st1.add(new Option('Select', '0'));

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

	/*
	 * var options = $("#ddLgdLBVillageSourceAtVillageCA"); $.each(data,
	 * function(key, obj) { var option = $("<option/>"); if
	 * (obj.operation_state == 'F') { $(option).attr('disabled', 'disabled');
	 * $(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
	 * options.append(option); } else {
	 * $(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
	 * options.append(option); } });
	 */

	// dwr.util.addOptions(fieldId, data, valueText, nameText);
	// dwr.util.addOptions(fieldId1, data, valueText, nameText);
}