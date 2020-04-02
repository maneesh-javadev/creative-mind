//DWR Dropdownlist Population
function go(url) {

	if (url.indexOf('home.htm') > -1 || url.indexOf(window.location.pathname.split('/')[window.location.pathname.split('/').length - 1]) == -1) {
		if (url.indexOf("?") > -1)
			window.location = url + "&OWASP_CSRFTOKEN=" + getParameterByNameForCSRF();
		else
			window.location = url + "?OWASP_CSRFTOKEN=" + getParameterByNameForCSRF();
	} else
		window.location = window.location.href;

}

function validateDate(date) {
	if (!validateDateDDMMYYY(date)) {
		alert("Enter valid date(dd-mm-yyyy) format");
	}
}

function specState() {

	remvoe_error();

	document.getElementById('stateTR').style.display = 'none';
	document.getElementById('districtTR').style.display = 'none';
	document.getElementById('subDistrictTR').style.display = 'none';
	if (document.getElementById('specificState').checked) {
		document.getElementById('stateTR').style.display = 'block';
	} else if (document.getElementById('specificDistrict').checked) {
		document.getElementById('districtTR').style.display = 'block';
	} else if (document.getElementById('specificSubDistrict').checked) {
		// alert(123);
		document.getElementById('subDistrictTR').style.display = 'block';
	}

}

function specDistrict() {

	document.getElementById('districtTR').style.display = 'none';
	if (document.getElementById('specificDistrict').checked) {
		document.getElementById('districtTR').style.display = 'block';
	}
}
function modDate() {
	document.getElementById('modDate').style.display = 'none';
	if (document.getElementById('dm').checked) {
		document.getElementById('modDate').style.display = 'block';
	}
}

function getData() {
	var id = "";
	if (document.getElementById('entity').value == 'gpleveldata') {
		id = document.getElementById('multiplestate').value;
		if (id == "" || id == 0) {
			alert("Please Select State");
			return false;
		}
		id = document.getElementById('captchaAnswer').value;
		if (id == "") {
			alert("Please Enter Captcha Code");
			return false;
		}
	}
	frmSubmit();

}

function getDetails() {
	

	remvoe_error();

	// alert("select value:"+document.getElementById('entity').value);
	document.getElementById('stateTR').style.display = 'none';
	document.getElementById('districtTR').style.display = 'none';
	document.getElementById('subDistrictTR').style.display = 'none';
	document.getElementById('acwisemap').style.display = 'none';
	document.getElementById('pcwisemap').style.display = 'none';
	document.getElementById('localBodyMappingTR').style.display = 'none';
	// document.getElementById('state').selectedIndex=0;
	// document.getElementByName(combo_'name').selectedIn dex = 0;

	if (document.getElementById('entity').value == 'allState') {
		// alert(document.getElementById('radiospecificState'));

		/*
		 * document.getElementById('radiospecificState').style.display ='none';
		 * document.getElementById('radiospecificDistrict').style.display
		 * ='none';
		 * document.getElementById('radiospecificSubDistrict').style.display
		 * ='none';
		 */

		// alert(RptMessage);
		document.getElementById("rptMessages").value = "State of India";

		document.getElementById('stateTR').style.display = 'none';
		document.getElementById('districtTR').style.display = 'none';
		document.getElementById('subDistrictTR').style.display = 'none';
		document.getElementById('GPoptionsSelect').style.display = 'none';
		document.getElementById('specificState').checked = false;
		document.getElementById('specificDistrict').checked = false;
		document.getElementById('specificSubDistrict').checked = false;
	} else if (document.getElementById('entity').value == 'allDistrict') {
		// alert(document.getElementById('radiospecificState'));
		/*
		 * document.getElementById('radiospecificState').style.display ='none';
		 * document.getElementById('radiospecificDistrict').style.display
		 * ='none';
		 * document.getElementById('radiospecificSubDistrict').style.display
		 * ='none';
		 */
		document.getElementById("rptMessages").value = "District of India";
		document.getElementById('stateTR').style.display = 'none';
		document.getElementById('districtTR').style.display = 'none';
		document.getElementById('subDistrictTR').style.display = 'none';
		document.getElementById('GPoptionsSelect').style.display = 'none';
		document.getElementById('specificState').checked = false;
		document.getElementById('specificDistrict').checked = false;
		document.getElementById('specificSubDistrict').checked = false;
	} else if (document.getElementById('entity').value == 'specificStateDistrict') {

		// alert(document.getElementById('radiospecificState'));
		// alert("state display");

		// document.getElementById('radiospecificState').style.display ='block';
		// document.getElementById('radiospecificDistrict').style.display
		// ='none';
		// document.getElementById('radiospecificSubDistrict').style.display
		// ='none';
		document.getElementById('state').selectedIndex = 0;
		document.getElementById('stateTR').style.display = 'block';
		document.getElementById('districtTR').style.display = 'none';
		document.getElementById('subDistrictTR').style.display = 'none';
		document.getElementById('GPoptionsSelect').style.display = 'none';
		document.getElementById('specificState').checked = false;
		document.getElementById('specificDistrict').checked = false;
		document.getElementById('specificSubDistrict').checked = false;
	} else if (document.getElementById('entity').value == 'allSubDistrict') {
		// alert(document.getElementById('radiospecificState'));
		// document.getElementById('radiospecificState').style.display ='none';
		// document.getElementById('radiospecificDistrict').style.display
		// ='none';
		// document.getElementById('radiospecificSubDistrict').style.display
		// ='none';
		document.getElementById("rptMessages").value = "Sub District of India";
		document.getElementById('stateTR').style.display = 'none';
		document.getElementById('districtTR').style.display = 'none';
		document.getElementById('subDistrictTR').style.display = 'none';
		document.getElementById('GPoptionsSelect').style.display = 'none';
		document.getElementById('specificState').checked = false;
		document.getElementById('specificDistrict').checked = false;
		document.getElementById('specificSubDistrict').checked = false;
	} else if (document.getElementById('entity').value == 'specificStateSubDistrict') {
		// alert(document.getElementById('radiospecificState'));

		// document.getElementById('radiospecificState').style.display ='block';
		// document.getElementById('radiospecificDistrict').style.display
		// ='none';
		// document.getElementById('radiospecificSubDistrict').style.display
		// ='none';
		document.getElementById('state').selectedIndex = 0;
		document.getElementById('stateTR').style.display = 'block';
		document.getElementById('districtTR').style.display = 'none';
		document.getElementById('subDistrictTR').style.display = 'none';
		document.getElementById('GPoptionsSelect').style.display = 'none';
		document.getElementById('specificState').checked = false;
		document.getElementById('specificDistrict').checked = false;
		document.getElementById('specificSubDistrict').checked = false;
	} else if (document.getElementById('entity').value == 'specificDistrictSubDistrict') {
		// alert(document.getElementById('radiospecificState'));
		// document.getElementById('radiospecificState').style.display ='none';
		// document.getElementById('radiospecificDistrict').style.display
		// ='block';
		// document.getElementById('radiospecificSubDistrict').style.display
		// ='none';
		document.getElementById('state1').selectedIndex = 0;
		document.getElementById('district_st').selectedIndex = 0;
		document.getElementById('stateTR').style.display = 'none';
		document.getElementById('districtTR').style.display = 'block';
		document.getElementById('subDistrictTR').style.display = 'none';
		document.getElementById('GPoptionsSelect').style.display = 'none';
		document.getElementById('specificState').checked = false;
		document.getElementById('specificDistrict').checked = false;
		document.getElementById('specificSubDistrict').checked = false;
	} else if (document.getElementById('entity').value == 'specificStateVillages') {
		// alert(document.getElementById('radiospecificState'));
		// document.getElementById('radiospecificState').style.display ='block';
		// document.getElementById('radiospecificDistrict').style.display
		// ='none';
		// document.getElementById('radiospecificSubDistrict').style.display
		// ='none';
		document.getElementById('state').selectedIndex = 0;
		document.getElementById('stateTR').style.display = 'block';
		document.getElementById('districtTR').style.display = 'none';
		document.getElementById('subDistrictTR').style.display = 'none';
		document.getElementById('GPoptionsSelect').style.display = 'none';
		document.getElementById('specificState').checked = false;
		document.getElementById('specificDistrict').checked = false;
		document.getElementById('specificSubDistrict').checked = false;
	} else if (document.getElementById('entity').value == 'specificDistrictVillages') {
		// alert(document.getElementById('radiospecificState'));
		// document.getElementById('radiospecificState').style.display ='none';
		// document.getElementById('radiospecificDistrict').style.display
		// ='block';
		// document.getElementById('radiospecificSubDistrict').style.display
		// ='none';
		document.getElementById('state1').selectedIndex = 0;
		document.getElementById('district_st').selectedIndex = 0;
		document.getElementById('stateTR').style.display = 'none';
		document.getElementById('districtTR').style.display = 'block';
		document.getElementById('subDistrictTR').style.display = 'none';
		document.getElementById('GPoptionsSelect').style.display = 'none';
		document.getElementById('specificState').checked = false;
		document.getElementById('specificDistrict').checked = false;
		document.getElementById('specificSubDistrict').checked = false;
	} else if (document.getElementById('entity').value == 'specificSubDistrictVillages') {
		// alert(document.getElementById('radiospecificState'));
		// document.getElementById('radiospecificState').style.display ='none';
		// document.getElementById('radiospecificDistrict').style.display
		// ='none';
		// document.getElementById('radiospecificSubDistrict').style.display
		// ='block';
		document.getElementById('state2').selectedIndex = 0;
		document.getElementById('district').selectedIndex = 0;
		document.getElementById('subDistrict').selectedIndex = 0;
		document.getElementById('stateTR').style.display = 'none';
		document.getElementById('districtTR').style.display = 'none';
		document.getElementById('subDistrictTR').style.display = 'block';
		document.getElementById('GPoptionsSelect').style.display = 'none';
		document.getElementById('specificState').checked = false;
		document.getElementById('specificDistrict').checked = false;
		document.getElementById('specificSubDistrict').checked = false;
	} else if (document.getElementById('entity').value == 'PriLbSpecificState' || document.getElementById('entity').value == 'ULBWardforState'
			|| document.getElementById('entity').value == 'PriWards') {
		// alert(document.getElementById('radiospecificState'));
		// document.getElementById('radiospecificState').style.display ='block';
		// document.getElementById('radiospecificDistrict').style.display
		// ='none';
		// document.getElementById('radiospecificSubDistrict').style.display
		// ='none';
		document.getElementById('stateTR').style.display = 'block';
		document.getElementById('districtTR').style.display = 'none';
		document.getElementById('subDistrictTR').style.display = 'none';
		document.getElementById('GPoptionsSelect').style.display = 'none';
		document.getElementById('specificState').checked = false;
		document.getElementById('specificDistrict').checked = false;
		document.getElementById('specificSubDistrict').checked = false;
	} else if (document.getElementById('entity').value == 'PriLbSpecificDistrict') {
		// alert(document.getElementById('radiospecificState'));
		// document.getElementById('radiospecificState').style.display ='none';
		// document.getElementById('radiospecificDistrict').style.display
		// ='block';
		// document.getElementById('radiospecificSubDistrict').style.display
		// ='none';
		document.getElementById('stateTR').style.display = 'none';
		document.getElementById('districtTR').style.display = 'block';
		document.getElementById('subDistrictTR').style.display = 'none';
		document.getElementById('GPoptionsSelect').style.display = 'none';
		document.getElementById('specificState').checked = false;
		document.getElementById('specificDistrict').checked = false;
		document.getElementById('specificSubDistrict').checked = false;
	}

	else if (document.getElementById('entity').value == 'UlbSpecificState') {
		// alert(document.getElementById('radiospecificState'));
		// document.getElementById('radiospecificState').style.display ='block';
		// document.getElementById('radiospecificDistrict').style.display
		// ='none';
		// document.getElementById('radiospecificSubDistrict').style.display
		// ='none';
		document.getElementById('stateTR').style.display = 'block';
		document.getElementById('districtTR').style.display = 'none';
		document.getElementById('subDistrictTR').style.display = 'none';
		document.getElementById('GPoptionsSelect').style.display = 'none';
		document.getElementById('specificState').checked = false;
		document.getElementById('specificDistrict').checked = false;
		document.getElementById('specificSubDistrict').checked = false;
	} else if (document.getElementById('entity').value == 'UlbSpecificDistrict') {
		// alert(document.getElementById('radiospecificState'));
		// document.getElementById('radiospecificState').style.display ='none';
		// document.getElementById('radiospecificDistrict').style.display
		// ='block';
		// document.getElementById('radiospecificSubDistrict').style.display
		// ='none';
		document.getElementById('stateTR').style.display = 'none';
		document.getElementById('districtTR').style.display = 'block';
		document.getElementById('subDistrictTR').style.display = 'none';
		document.getElementById('GPoptionsSelect').style.display = 'none';
		document.getElementById('specificState').checked = false;
		document.getElementById('specificDistrict').checked = false;
		document.getElementById('specificSubDistrict').checked = false;
	} else if (document.getElementById('entity').value == 'TlbSpecificState') {
		// alert(document.getElementById('radiospecificState'));
		// document.getElementById('radiospecificState').style.display ='block';
		// document.getElementById('radiospecificDistrict').style.display
		// ='none';
		// document.getElementById('radiospecificSubDistrict').style.display
		// ='none';
		document.getElementById('stateTR').style.display = 'block';
		document.getElementById('districtTR').style.display = 'none';
		document.getElementById('subDistrictTR').style.display = 'none';
		document.getElementById('GPoptionsSelect').style.display = 'none';
		document.getElementById('specificState').checked = false;
		document.getElementById('specificDistrict').checked = false;
		document.getElementById('specificSubDistrict').checked = false;
	} else if (document.getElementById('entity').value == 'TlbSpecificDistrict') {
		// alert(document.getElementById('radiospecificState'));
		// document.getElementById('radiospecificState').style.display ='none';
		// document.getElementById('radiospecificDistrict').style.display
		// ='block';
		// document.getElementById('radiospecificSubDistrict').style.display
		// ='none';
		document.getElementById('stateTR').style.display = 'none';
		document.getElementById('districtTR').style.display = 'block';
		document.getElementById('subDistrictTR').style.display = 'none';
		document.getElementById('GPoptionsSelect').style.display = 'none';
		document.getElementById('specificState').checked = false;
		document.getElementById('specificDistrict').checked = false;
		document.getElementById('specificSubDistrict').checked = false;
	} else if (document.getElementById('entity').value == 'PCWiseLocalBodyMap') {
		// alert(document.getElementById('radiospecificState'));
		// document.getElementById('radiospecificState').style.display ='block';
		// document.getElementById('radiospecificDistrict').style.display
		// ='none';
		// document.getElementById('radiospecificSubDistrict').style.display
		// ='none';
		document.getElementById('pcwisemap').style.display = 'block';
		document.getElementById('stateTR').style.display = 'none';
		document.getElementById('districtTR').style.display = 'none';
		document.getElementById('subDistrictTR').style.display = 'none';
		document.getElementById('GPoptionsSelect').style.display = 'none';
		document.getElementById('specificState').checked = false;
		document.getElementById('specificDistrict').checked = false;
		document.getElementById('specificSubDistrict').checked = false;
	} else if (document.getElementById('entity').value == 'ACWiseLocalBodyMap') {
		// alert(document.getElementById('radiospecificState'));
		// document.getElementById('radiospecificState').style.display ='block';
		// document.getElementById('radiospecificDistrict').style.display
		// ='none';
		// document.getElementById('radiospecificSubDistrict').style.display
		// ='none';
		document.getElementById('acwisemap').style.display = 'block';
		document.getElementById('pcwisemap').style.display = 'none';
		document.getElementById('stateTR').style.display = 'none';
		document.getElementById('districtTR').style.display = 'none';
		document.getElementById('subDistrictTR').style.display = 'none';
		document.getElementById('GPoptionsSelect').style.display = 'none';
		document.getElementById('specificState').checked = false;
		document.getElementById('specificDistrict').checked = false;
		document.getElementById('specificSubDistrict').checked = false;
	} else if (document.getElementById('entity').value == 'LocalbodyMappingtoCensusLandregionCode') {
		document.getElementById('state').selectedIndex = 0;
		document.getElementById('stateTR').style.display = 'none';
		document.getElementById('districtTR').style.display = 'none';
		document.getElementById('subDistrictTR').style.display = 'none';
		document.getElementById('localBodyMappingTR').style.display = 'block';
		document.getElementById('GPoptionsSelect').style.display = 'none';
		document.getElementById('specificState').checked = false;
		document.getElementById('specificDistrict').checked = false;
		document.getElementById('specificSubDistrict').checked = false;
	} else if (document.getElementById('entity').value == 'gpleveldata') {
		// alert(document.getElementById('radiospecificState'));
		// document.getElementById('radiospecificState').style.display ='none';
		// document.getElementById('radiospecificDistrict').style.display
		// ='block';
		// document.getElementById('radiospecificSubDistrict').style.display
		// ='none';
		document.getElementById('state1').selectedIndex = 0;
		document.getElementById('district_st').selectedIndex = 0;
		document.getElementById('stateTR').style.display = 'none';
		document.getElementById('districtTR').style.display = 'none';
		document.getElementById('subDistrictTR').style.display = 'none';
		document.getElementById('GPoptionsSelect').style.display = 'block';
		document.getElementById('specificState').checked = false;
		document.getElementById('specificDistrict').checked = false;
		document.getElementById('specificSubDistrict').checked = false;

	} else if (document.getElementById('entity').value == 'specificStateBlock') {
		// added by kirandeep for download directory Block on 17/09/2014
		document.getElementById('state').selectedIndex = 0;
		document.getElementById('stateTR').style.display = 'block';
		document.getElementById('districtTR').style.display = 'none';
		document.getElementById('subDistrictTR').style.display = 'none';
		document.getElementById('GPoptionsSelect').style.display = 'none';
		document.getElementById('specificState').checked = false;
		document.getElementById('specificDistrict').checked = false;
		document.getElementById('specificSubDistrict').checked = false;
	}

}

function getList(id) {

	remvoe_error();
	lgdDwrDistrictService.getDistrictList(id, {
		callback : handleDistrictSuccess_st,
		errorHandler : handleDistrictError_st
	});
}

function handleDistrictSuccess_st(data) {
	// Assigns data to result id
	// alert(data);
	var fieldId = 'district_st';
	// alert(data);
	// var valueText = 'districtCode';
	// var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('district_st');
	st.add(new Option('Select District', '0'));
	dwr.util.addOptions(fieldId, data, 'districtCode', 'districtNameEnglish');

}

function handleDistrictError_st() {
	// Show a popup message
	alert("No data found!");
}

function getListParliment(id) {
	// alert("parli:"+id);
	// remvoe_error();

	lgdDwrParlimentService.getParliamentConstituencyDetail(id, {
		callback : handleParlimentSuccess,
		errorHandler : handleParlimentError
	});
}

function handleParlimentSuccess(data) {
	// Assigns data to result id
	// alert("hibye");
	// alert(data);
	var fieldId = 'parliment';

	var valueText = 'pcCode';
	var nameText = 'pcNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('parliment');
	st.add(new Option('Select Parliament', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleParlimentError() {
	// Show a popup message
	alert("No data found in Parliment!");
}

function getListParliment1(id) {
	// alert("1parli:"+id);
	// remvoe_error();

	lgdDwrParlimentService.getParliamentConstituencyDetail(id, {
		callback : handleParlimentSuccess1,
		errorHandler : handleParlimentError1
	});
}

function handleParlimentSuccess1(data) {
	// Assigns data to result id
	// alert("hibye");
	// alert(data);
	var fieldId = 'parliment1';
	// alert(data);
	// var valueText = 'districtCode';
	// var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('parliment1');
	st.add(new Option('Select Parliment', '0'));
	dwr.util.addOptions(fieldId, data, 'pcCode', 'pcNameEnglish');

}

function handleParlimentError1() {
	// Show a popup message
	alert("No data found in Parliment!");
}

function getListAssembly(id) {
	// alert("assem:"+id);
	// remvoe_error();

	lgdDwrdownloadAssemblyService.getAssemblyConstituencyDetail(id, {
		callback : handleAssemblySuccess,
		errorHandler : handleAssemblyError
	});
}

function handleAssemblySuccess(data) {
	// Assigns data to result id
	// alert("tztz");
	// alert(data);
	var fieldId = 'assembly';
	// alert(data);
	// var valueText = 'districtCode';
	// var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('assembly');
	st.add(new Option('Select Assembly', '0'));
	dwr.util.addOptions(fieldId, data, 'acCode', 'acNameEnglish');

}

function handleAssemblyError() {
	// Show a popup message
	alert("No data found in Assembly!");
}

function getDistrictList(id) {

	remvoe_error();
	lgdDwrDistrictService.getDistrictList(id, {
		callback : handleDistrictSuccess,
		errorHandler : handleDistrictError
	});

}

function handleDistrictSuccess(data) {
	// Assigns data to result id
	var fieldId = 'district';
	// alert(data);
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('district');
	st.add(new Option('Select District', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleDistrictError() {
	// Show a popup message
	alert("No data found!");
}

function getSubDistList(id) {

	lgdDwrSubDistrictService.getSubDistrictList(id, {
		callback : handleSubDistrictSuccess,
		errorHandler : handleSubDistrictError
	});

}

function handleSubDistrictSuccess(data) {

	// Assigns data to result id
	var fieldId = 'subDistrict';
	// alert("Maneesh"+data);
	var valueText = 'subdistrictCode';
	// alert("valueText"+valueText);
	var nameText = 'subdistrictNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('subDistrict');
	st.add(new Option('Select Subdistrict', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleSubDistrictError() {
	// Show a popup message
	alert("No data found!");
}

function setToDate(toDate) {
	alert(toDate);
	document.getElementById('toDate').value = toDate;
}

// end func

function validateFromDate() {

	var infromDate = document.getElementById("fromDate").value;

	if (infromDate == "") {
		document.getElementById("errSelectfromDate").innerHTML = "From Date is required.";
		return false;

	}

	else if (!validateDateDDMMYYY(infromDate)) {
		document.getElementById("errSelectfromDate").innerHTML = "Enter valid date(dd-mm-yyyy) format";
		return false;
	} else if (!compareDateDDMMYYY(infromDate)) {
		document.getElementById("errSelectfromDate").innerHTML = "From Date should be equal or earlier to Current Date ";

		return false;
	} else {
		return true;
	}

}
function validateToDate() {

	/* alert("validtodate"); */
	var intoDate = document.getElementById("toDate").value;

	if (intoDate == "") {
		document.getElementById("errSelecttoDate").innerHTML = "To Date is required.";
		return false;

	}

	else if (!validateDateDDMMYYY(intoDate)) {
		document.getElementById("errSelecttoDate").innerHTML = "Enter valid date(dd-mm-yyyy) format";
		return false;
	}
	// not allowed future date
	else if (!compareDateforFutureDDMMYYY(intoDate)) {

		document.getElementById("errSelecttoDate").innerHTML = "To Date should not be future date";

		return false;
	}

	else if (!compareToDateforEffectDDMMYYY(intoDate)) {

		document.getElementById("errSelecttoDate").innerHTML = "To Date should be less or equal to the From Date";
		return false;
	}

	else {
		return true;
	}
}

function compareToDateforEffectDDMMYYY(fromDate)

{
	var infromDate = document.getElementById("fromDate").value;

	var string_start = infromDate.split("-");
	var date_start = string_start[0];
	var month_start = string_start[1] - 1;
	var year_start = string_start[2];
	var ToDate = new Date(year_start, month_start, date_start);

	var string_start1 = fromDate.split("-");
	var date_start1 = string_start1[0];
	var month_start1 = string_start1[1] - 1;
	var year_start1 = string_start1[2];
	var FromDate = new Date(year_start1, month_start1, date_start1);

	if (days_between(FromDate, ToDate) >= 0) {
		return true;
	} else {
		return false;
	}

}
function downloadonlymodi() {
	document.forms['form1'].method = "get";
	document.forms['form1'].action = "downloadDirectoryLBModification.do?<csrf:token uri='downloadDirectoryLBModification.do'/>";
	document.forms['form1'].submit();
	return true;

}
function downloadfull() {
	document.getElementById('flag1').value = 2;
	document.forms['form1'].method = "get";
	document.forms['form1'].action = "downloadDirectory.do?<csrf:token uri='downloadDirectory.do'/>";
	document.forms['form1'].submit();
	return true;

}


	
	