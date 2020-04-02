var divlevel = 0;
function go(url) {

	if (url.indexOf('home.htm') > -1 || url.indexOf(window.location.pathname.split('/')[window.location.pathname.split('/').length - 1]) == -1) {
		if (url.indexOf("?") > -1)
			window.location = url + "&OWASP_CSRFTOKEN=" + getParameterByNameForCSRF();
		else
			window.location = url + "?OWASP_CSRFTOKEN=" + getParameterByNameForCSRF();
	} else
		window.location = window.location.href;

}

// ########## get Local body list open ######################
function getLocalBodyList(id) {
	document.getElementById('tr_village_dist').style.display = 'none';
	document.getElementById('tr_village_dist').style.visibility = 'hidden';
	document.getElementById('tr_village_inter').style.display = 'none';
	document.getElementById('tr_village_inter').style.visibility = 'hidden';
	document.getElementById('tr_village_pan').style.display = 'none';
	document.getElementById('tr_village_pan').style.visibility = 'hidden';
	document.getElementById('tr_district2').style.visibility = 'hidden';
	document.getElementById('tr_district2').style.display = 'none';
// alert("state:"+id);
	remove_error(0);
	// setButton(true);
	lgdDwrDesignationService.getLocalbodyDetailCantonment(id, {
		callback : handleLocalbodySuccess,
		errorHandler : handleLocalbodyError
	});
}

function handleLocalbodySuccess(data) {
	// Assigns data to result id
	// alert("ti");
	// alert(data);
	var fieldId = 'ddLgdLBType';
	// alert(data);
	var valueText = 'nomenclatureEnglish';
	var nameText = 'localBodyTypeName';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddLgdLBType');
	st.add(new Option('Select Local Body', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleLocalbodyError() {
	// Show a popup message
	alert("No data found in local body!");
}

// ########## get Local body list Close ######################

// ########## get district panchayat list open ######################
function getdisPanchayatList(id1, id2) {
	// alert("district:"+id1+":"+id2);

	lgdDwrlocalBodyService.getPanchayatListbylblcCode(id1, id2, {
		callback : handledisPanchayatSuccess,
		errorHandler : handledisPanchayatError
	});
}

function handledisPanchayatSuccess(data) {
	// Assigns data to result id
	// alert("ti");
	// alert(data);
	var fieldId = 'ddLgdLBDistListAtVillageCA';
	// alert(data);
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById(fieldId);
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handledisPanchayatError() {
	// Show a popup message
	alert("No data found in District Panchayat!");
}

// ########## get district panchayat list close ######################

function getdisInterPanchayatList(id1, id2) {
	// alert("inter"+id1+":"+id2);

	lgdDwrlocalBodyService.getPanchayatListbylblcCode(id1, id2, {
		callback : handledisInterPanchayatSuccess,
		errorHandler : handledisInterPanchayatError
	});
}

function handledisInterPanchayatSuccess(data) {
	// Assigns data to result id
	// alert("ti");
	// alert(data);
	var fieldId = 'ddLgdLBInterListAtVillageCA';
	// alert(data);
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddLgdLBInterListAtVillageCA');
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handledisInterPanchayatError() {
	// Show a popup message
	alert("No data found in District Panchayat!");
}

// ########## get district panchayat list close ######################

// ########## get district panchayat list close ######################

function getdisVillagePanchayatList(id1, id2) {
	// alert("village:"+id1+":"+id2);

	lgdDwrlocalBodyService.getPanchayatListbylblcCode(id1, id2, {
		callback : handledisVillagePanchayatSuccess,
		errorHandler : handledisVillagePanchayatError
	});
}

function handledisVillagePanchayatSuccess(data) {
	// Assigns data to result id
	// alert("ti");
	// alert(data);
	var fieldId = 'ddLgdLBDistListAtVillageCA';
	if (divlevel == 1)
		fieldId = 'ddLgdLBVillageSourceAtVillageCA';
	divlevel = 0;
	// alert(data);
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById(fieldId);
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handledisVillagePanchayatError() {
	// Show a popup message
	alert("No data found in District Panchayat!");
}

// ########## get district panchayat list close ######################

function setLBLC(id) {
	// alert("lblc:"+id);
	remove_error(1);
	document.getElementById('lblc').value = id;
	// alert(document.getElementById('lblc').value);
	/* setButton(false); */
}

// ########## get cover district list open ######################
function getLGBforCoveredDistListExWard(id) {
	// alert("gi");
	// alert(id);
	/*
	 * lgdDwrlocalBodyService.getLGBforCoveredDistListExWard(id, { callback :
	 * getCoveredLandForDistExWardRegionSuccess, errorHandler :
	 * getCoveredLandForDistExWardRegionError });
	 */
}

// data contains the returned value
function getCoveredLandForDistExWardRegionSuccess(data) {
	// Assigns data to result id
	// alert(data);
	var fieldId = 'ddLgdLBDistCAreaSourceL';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getCoveredLandForDistExWardRegionError() {
	// Show a popup message
	alert("No data found!");
}

// ########## get cover district list close ######################

// ########## get inter panchayat list open ######################
function getInterPanchayatWardbyDcode(id) {

	// alert("inter");
	// alert(id);
	remove_error(2);
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleInterPanchayatWardSuccess,
		errorHandler : handleInterPanchayatWardError
	});
}

function handleInterPanchayatWardSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddLgdLBInterPSourceList';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddLgdLBInterPSourceList');
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleInterPanchayatWardError() {
	// Show a popup message
	alert("No data found!");
}

// ########## get inter panchayat list close ######################

function setButton(flag) {
	// document.getElementById('btnGet').disabled=flag;
}

function getLGBforCoveredSubDistListExWard(id) {
	remove_error(2);
	document.getElementById('lblc').value = id;
	// alert(document.getElementById('lblc').value);
	/* setButton(false); */
}

function getLGBforCoveredVillageListExWard(id) {
	document.getElementById('lblc').value = id;
	// alert(document.getElementById('lblc').value);
	/* setButton(false); */
}

function getCovereSubDistUrbanExWardList(id) {
	remove_error(0);
	document.getElementById('lblc').value = id;

	// alert(document.getElementById('lblc').value);
	/* setButton(false); */
}

function getPanchayatListbyStateandlbTypeCode(id1, stateCode) { // State Code
	// Hard Coded
	// WIll Be change Later
	// alert("id"+id1+":"+stateCode+"urban list");

	lgdDwrlocalBodyService.getPanchayatListbyStateandlbTypeCode(stateCode, id1, {
		callback : getPanchayatListbyStateandlbTypeCodeSuccess,
		errorHandler : getPanchayatListbyStateandlbTypeCodeError
	});
}

// data contains the returned value
function getPanchayatListbyStateandlbTypeCodeSuccess(data) {
	// Assigns data to result id
	// alert(data);
	var fieldId = 'wardUrbanLocalBody';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	// villageCode,villageNameEnglish
	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('wardUrbanLocalBody');
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getPanchayatListbyStateandlbTypeCodeError() {
	// Show a popup message
	alert("No data found!");
}

/*
 * function setDirection(val) { document.getElementById('direction').value=val;
 * document.forms['viewWardForm'].action = "viewWardPagination.htm?<csrf:token
 * uri='viewWardPagination.htm'/>"; document.forms['viewWardForm'].submit(); }
 */

function getVillagePanchWardbyIntercode(id) {

	// alert(id);
	document.getElementById('lblc').value = id;
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
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleVillagePanchWardbyIntercodeError() {
	// Show a popup message
	alert("No data found!");
}

function validatePRIWardAll() {

	var captchaAnswer = document.getElementById("captchaAnswer").value;
	var id = document.getElementById('ddLgdLBType').value;
	var inSelectState = document.getElementById("state").value;

	var temp = id.split(":");
	var id2 = temp[1];
	var category = temp[3];
	var flag = false;

	if (inSelectState != 0 || inSelectState != "") {
		if (id != 0) {

			if (document.getElementById('tr_village_dist').style.visibility == 'visible'
					|| document.getElementById('tr_village_dist').style.display == 'block') {
				var first = document.getElementById('ddLgdLBDistListAtVillageCA').value;
				// alert("first:"+first);
				/* alert("msg:"+document.getElementById('firstlevel').value); */
				if (first != 0)/* || first!="") */
				{
					flag = true;
				} else {
					// alert("inside1");
					document.getElementById("errSelectVIP").style.visibility = 'visible';
					document.getElementById('errSelectVIP').style.display = 'block';
					document.getElementById("ddLgdLBDistListAtVillageCA").focus();
					flag = false;
				}
			}

			if (document.getElementById('tr_village_inter').style.visibility == 'visible'
					|| document.getElementById('tr_village_inter').style.display == 'block') {
				// alert("second:"+second);
				var second = document.getElementById('ddLgdLBInterListAtVillageCA').value;
				if (second != 0) {
					flag = true;
				} else {
					// alert("inside2");
					document.getElementById("errSelectVDP").style.visibility = 'visible';
					document.getElementById('errSelectVDP').style.display = 'block';
					document.getElementById("ddLgdLBInterListAtVillageCA").focus();
					flag = false;
				}
			}

			if (document.getElementById('tr_village_pan').style.visibility == 'visible'
					|| document.getElementById('tr_village_pan').style.display == 'block') {
				// alert("third:"+third);
				var third = document.getElementById('ddLgdLBVillageSourceAtVillageCA').value;
				if (third != 0) {
					flag = true;
				} else {
					// alert("inside3");
					document.getElementById("errSelectVVP").style.visibility = 'visible';
					document.getElementById('errSelectVVP').style.display = 'block';
					document.getElementById("ddLgdLBVillageSourceAtVillageCA").focus();
					flag = false;
				}
			}

			if (id2 == 'I' && category == 'U') {
				var inSelectIU = document.getElementById("wardUrbanLocalBody").value;
				if (inSelectIU != 0) {

					flag = true;

				} else {
					document.getElementById("errSelectUrban").style.visibility = 'visible';
					document.getElementById('errSelectUrban').style.display = 'block';

					document.getElementById("wardUrbanLocalBody").focus();
					flag = false;
				}

			}

		} else {

			document.getElementById("errSelectLocalBodyType").innerHTML = "<font color='red'>Please select the local body type<spring:message code='error.select.LBTYPE' /></font>";
			document.getElementById("ddLgdLBType").focus();
			flag = false;
		}

	} else {

		document.getElementById("errSelectState").innerHTML = "<font color='red'>Please select the state from the list<spring:message code='error.select.STATEFRMLIST' /></font>";
		document.getElementById("state").focus();
		flag = false;
	}

	if (captchaAnswer == "") {
		document.getElementById('errorCaptcha').style.display = 'block';
		document.getElementById('errorCaptcha').style.visibility = 'visible';
		document.getElementById("errorCaptcha").focus();
		flag = false;
	}

	// alert(flag);

	return flag;
}

function remove_error(id) {

	/*
	 * var id=document.getElementById('ddLgdLBType').value;
	 * 
	 * 
	 * var temp = id.split(":"); var id2 = temp[1]; var category=temp];
	 */
	// alert("hizi"+id);
	document.getElementById("errSelectState").innerHTML = "";
	document.getElementById("errSelectLocalBodyType").innerHTML = "";
	if (id == 0) {
		document.getElementById("errSelectVVP").style.visibility = 'hidden';
		document.getElementById('errSelectVVP').style.display = 'none';
	} else if (id == 1) {
		document.getElementById("errSelectLocalBody").innerHTML = "";
	} else if (id == 2) {
		document.getElementById("errSelectVDP").style.visibility = 'hidden';
		document.getElementById('errSelectVDP').style.display = 'none';
		document.getElementById("errSelectVIP").style.visibility = 'hidden';
		document.getElementById('errSelectVIP').style.display = 'none';
	} else if (id == 3) {

		document.getElementById("errSelectVDP").style.visibility = 'hidden';
		document.getElementById('errSelectVDP').style.display = 'none';
		document.getElementById("errSelectVIP").style.visibility = 'hidden';
		document.getElementById('errSelectVIP').style.display = 'none';
		document.getElementById("errSelectVVP").style.visibility = 'hidden';
		document.getElementById('errSelectVVP').style.display = 'none';

	} else {
		document.getElementById("errSelectUrban").style.visibility = 'hidden';
		document.getElementById('errSelectUrban').style.display = 'none';

	}

}

function getLocalBodyListbylblcCode(statecode, lblc) {
	// alert("state:"+id);
	// remove_error(0);
	// setButton(true);
	lgdDwrDesignationService.getLocalbodyDetailbyCode(statecode, lblc, {
		callback : handleLocalbodylblcCodeSuccess,
	// errorHandler : handleLocalbodylblcCodeError
	});
}

function handleLocalbodylblcCodeSuccess(data) {
	// Assigns data to result id
	// alert("ti");
	// alert(data);
	var statecode = document.getElementById('state').value;
	var category = document.getElementById('cattype').value;
	var lblc = data[0].localBodyTypeCode;
	// alert(statecode);
	var plblc = data[0].parentTierSetupCode;
	var level = data[0].level;
	var name = data[0].localBodyTypeName;
	// alert(plblc);
	// alert(lblc);
	if (level == 'D') {
		document.getElementById("firstlevel").innerHTML = " " + name;
		document.getElementById("errSelectVIP").innerHTML = "Select " + name;
		// alert("inside1");
		if (plblc == 0) {
			// alert("inside2");
			document.getElementById('tr_village_dist').style.display = 'block';
			document.getElementById('tr_village_dist').style.visibility = 'visible';
			getdisVillagePanchayatList(statecode, lblc);
		}
	}

	else if (level == 'I') {
		document.getElementById("secondlevel").innerHTML = " " + name;
		document.getElementById("errSelectVDP").innerHTML = "Select " + name;

		document.getElementById('level').value = 'I';
		if (plblc == 0) {
			document.getElementById('tr_village_inter').style.display = 'block';
			document.getElementById('tr_village_inter').style.visibility = 'visible';
			dwr.util.removeAllOptions('ddLgdLBInterListAtVillageCA');
			getdisInterPanchayatList(statecode, lblc);
		} else {
			document.getElementById('tr_village_inter').style.display = 'block';
			document.getElementById('tr_village_inter').style.visibility = 'visible';
			dwr.util.removeAllOptions('ddLgdLBInterListAtVillageCA');
			getLocalBodyListbylblcCode(statecode, plblc);
		}
	}
	// alert(data);

}

function handleLocalbodylblcCodeError() {
	// Show a popup message
	alert("No data found in local body by lblc!");
}

function hideShowDivforWard(id, districtCode, lbtype) {

	/* setButton(true); */
	var temp = id.split(":");
	// alert(temp);
	var id1 = temp[0];
	var id2 = temp[1];
	var id3 = temp[2];
	var id4 = temp[4];
	var id5 = temp[5];
	// alert(id4);
	var category = temp[3];
	document.getElementById('cattype').value = category;
	document.getElementById('level').value = "";
	var statecode = parseInt(id3);
	remove_error(0);
	document.getElementById('tr_district2').style.visibility = 'hidden';
	document.getElementById('tr_district2').style.display = 'none';
	document.getElementById('tr_village_dist').style.display = 'none';
	document.getElementById('tr_village_dist').style.visibility = 'hidden';
	document.getElementById('tr_village_inter').style.display = 'none';
	document.getElementById('tr_village_inter').style.visibility = 'hidden';
	document.getElementById('tr_village_pan').style.display = 'none';
	document.getElementById('tr_village_pan').style.visibility = 'hidden';
	document.getElementById('tr_district2').style.visibility = 'hidden';
	document.getElementById('tr_district2').style.display = 'none';

	// alert("level:"+id2+"category:"+category);
	if ((id2 == 'D' && category == 'P') || (id2 == 'D' && category == 'T')) {
		document.getElementById("firstlevel").innerHTML = " " + id5;
		document.getElementById("errSelectVIP").innerHTML = "Select " + id5;
		document.getElementById('tr_village_dist').style.display = 'block';
		document.getElementById('tr_village_dist').style.visibility = 'visible';
		if (id4 == 0)
			getdisPanchayatList(statecode, id1);

	} else if ((id2 == 'I' && category == 'P') || (id2 == 'I' && category == 'T')) {
		document.getElementById('level').value = 'I';
		document.getElementById("secondlevel").innerHTML = " " + id5;
		document.getElementById("errSelectVDP").innerHTML = "Select " + id5;
		document.getElementById('tr_village_inter').style.display = 'block';
		document.getElementById('tr_village_inter').style.visibility = 'visible';
		dwr.util.removeAllOptions('ddLgdLBInterListAtVillageCA');
		if (id4 == 0)
			getdisInterPanchayatList(statecode, id1);
		else
			getLocalBodyListbylblcCode(statecode, id4);

	} else if ((id2 == 'V' && category == 'P') || (id2 == 'V' && category == 'T')) {
		// alert("idv"+id);
		// alert(id4);
		document.getElementById("thirdlevel").innerHTML = " " + id5;
		document.getElementById("errSelectVVP").innerHTML = "Select " + id5;
		document.getElementById('tr_village_pan').style.display = 'block';
		document.getElementById('tr_village_pan').style.visibility = 'visible';
		dwr.util.removeAllOptions('ddLgdLBVillageSourceAtVillageCA');
		if (id4 == 0) {
			divlevel = 1;
			getdisVillagePanchayatList(statecode, id1);
		}

		else {
			getLocalBodyListbylblcCode(statecode, id4);
		}

	} else if (id2 == 'I' && category == 'U') {

		// alert("urban");
		document.getElementById('tr_district2').style.visibility = 'visible';
		document.getElementById('tr_district2').style.display = 'block';
		/*document.getElementById("urbanlevel").innerHTML = " " + id5;*/
		document.getElementById("errSelectUrban").innerHTML = "Select " + id5;

		getPanchayatListbyStateandlbTypeCode(id1, statecode);
	}

}

function callList(id) {
	category = document.getElementById('cattype').value;
	level = document.getElementById('level').value;
	document.getElementById('lblc').value = id;

	// alert("callfun");
	// alert("cat:"+category+":level:"+level);
	if ((category == 'P' || category == 'T') && (level == 'I')) {
		getInterPanchayatbyDcodeAtVWard(id, 'inter');

	} else {
		getVillagePanchWardbyIntercode(id);
	}

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

function handleInterPWardVCASuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddLgdLBInterListAtVillageCA';

	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddLgdLBInterListAtVillageCA');
	st.add(new Option('Select Intermediate Panchayat', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleInterPWardVCAError() {
	// Show a popup message
	alert("No data found!");
}

function onload() {
	document.getElementById('state').selectedIndex = 0;
}

if (window.addEventListener) {
	window.addEventListener("load", onload, false);
} else if (window.attachEvent) {
	window.attachEvent("onload", onload);
} else if (window.onLoad) {
	window.onload = onload;
}