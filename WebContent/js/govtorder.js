var errors = new Array();
var correctionOrderDate;
function next(t) {

	var error = true;
	var show = t + 1;
	var hide = t;
	if (t == 1) {

		errors[0] = validateOrdeNo();
		errors[1] = validateOrdeDate();
		errors[2] = validateEffecDate();
		errors[3] = validateGazPubDate();
		// errors[4]=validateSFile();
		if (errors[0] == true) {
			$("#OrderNo_error").hide();
			$("#OrderNo").removeClass("error_fld");
			$("#OrderNo").removeClass("error_msg");
		}

		if (errors[1] == true) {
			$("#OrderDate_error").hide();
			$("#OrderDate").removeClass("error_fld");
			$("#OrderDate").removeClass("error_msg");
		}
		if (errors[2] == true) {
			$("#EffectiveDate_error").hide();
			$("#EffectiveDate").removeClass("error_fld");
			$("#EffectiveDate").removeClass("error_msg");
		}
		if (errors[3] == true) {
			$("#GazPubDate_error").hide();
			$("#GazPubDate").removeClass("error_fld");
			$("#GazPubDate").removeClass("error_msg");
		}

		if (errors[4] == true) {
			$("#filGovernmentOrder_error").hide();
			$("#filGovernmentOrder").removeClass("error_fld");
			$("#filGovernmentOrder").removeClass("error_msg");
		}

		for ( var i = 0; i < 5; i++) {
			if (errors[i] == false) {
				error = false;
			}

		}

		if (error == false) {
			alert("Please enter required fields ...!");

		} else {

			document.getElementById('tab2').style.display = 'block';
			document.getElementById('tab1').style.display = 'none';
			$("#tab2_header").addClass("current");
			$("#tab1_header").removeClass("current");
		}

	}

	else {
		$("#tab2_header").addClass("current");
		$("#tab1_header").removeClass("current");
		document.getElementById('tab2').style.display = 'block';
		document.getElementById('tab1').style.display = 'none';
	}
}

function previous(s) {
	document.getElementById('tab1').style.display = 'block';
	document.getElementById('tab2').style.display = 'none';
	$("#tab1_header").addClass("current");
	$("#tab2_header").removeClass("current");
}
// /////////code for clear/////////////////////
function onClear() {
	$(".frmfield").each(function() {
		$(this).val("");
		$(this).removeClass("error_fld");
		$(".error").hide();
		$(".helpBox").hide();
	});

	$(".combofield").each(function() {
		$(this).val("");
		$(this).removeClass("error_fld");
		$(".error").hide();
		$(".helpBox").hide();
	});

}
// /////////code for clear/////////////////////
function tabdisplay(id) {

	if (id == 'tab2_header') {
		next(1);
	} else {
		previous(2);

	}
}

function show_image(t) {
	$("#" + t).show();
}
function hide_image(s) {
	$("#" + s).hide();
}

function load_page() {
	$("#tab1_header").addClass("selectedTab");
}

function show_msg(Field_msg) {
	var error = '#' + Field_msg + "_error";
	$("#" + Field_msg).removeClass("error_fld");
	$("#" + Field_msg).removeClass("error_msg");

	$(error).hide();

}

function setOrderDateforCorrection(orderdate, errordiv) {
	$(errordiv).hide();
	correctionOrderDate = document.getElementById('OrderDate').value;

}
function vaidatetOrderDateforCorrectionEntity(orderdate, errordiv) {

	var effectiveDate = document.getElementById('EffectiveDate').value;

	if (compareDateYYMMDD(orderdate, effectiveDate, 'dd-mm-yyyy')) {
		$(errordiv).show();
		document.getElementById('OrderDate').value = correctionOrderDate;
	}

	return false;
}

function setEffectiveDate(orderdate) {
	document.getElementById('EffectiveDate').value = orderdate;
}
function setEffectiveDate1(orderdate1) {
	document.getElementById('EffectiveDate1').value = orderdate1;
}
function setFocus() {
	document.getElementById('filGovernmentOrder').focus();
}
function hideErrorMsges() {
	errors[0] = validateOrdeNo();

	if (errors[0] == true) {
		$("#OrderNo_error").hide();
		$("#OrderNo").removeClass("error_fld");
		$("#OrderNo").removeClass("error_msg");

		errors[1] = validateOrdeDate();

		if (errors[1] == true) {
			$("#OrderDate_error").hide();
			$("#OrderDate").removeClass("error_fld");
			$("#OrderDate").removeClass("error_msg");
			errors[2] = validateEffecDate();
			if (errors[2] == true) {
				$("#EffectiveDate_error").hide();
				$("#EffectiveDate").removeClass("error_fld");
				$("#EffectiveDate").removeClass("error_msg");
				errors[3] = validateGazPubDate();
				if (errors[3] == true) {
					$("#GazPubDate_error").hide();
					$("#GazPubDate").removeClass("error_fld");
					$("#GazPubDate").removeClass("error_msg");
					errors[4] = validateSFile1();
					if (errors[4] == true) {
						$("#loadfile_error").hide();
						$("#loadfile").removeClass("error_fld");
						$("#loadfile").removeClass("error_msg");
					}
				}
			}
		}
	}

}
function validateOrdeNo() {
	try{
	var orderNo =  $("#OrderNo");
	if ($( orderNo ).val() == "") {
		$( '#errOrderNo').text("Order No. is required.");
		$( orderNo ).addClass("textbox_errormsg");
		return false;

	} else {
		$( '#errOrderNo').text("");
		$( orderNo ).removeClass("textbox_errormsg");
		
		return true;
	}
	}catch(err) {
	    alert( err.message);
	}

}

function validateOrdeDate() {

	var orderdateObj =  $("#OrderDate");
	var orderdate =  $(orderdateObj).val();
	
	if (orderdate== "") {
		$( '#errOrderDate').text("Order Date is required.");
		$( orderdateObj ).addClass("textbox_errormsg");
		return false;

	}

	else if (!validateDateDDMMYYY(orderdate)) {
		$( '#errOrderDate').text("Enter valid date(dd-mm-yyyy) format");
		$( orderdateObj ).addClass("textbox_errormsg");
		
		return false;
	} else if (!compareDateDDMMYYY(orderdate)) {
		$( '#errOrderDate').text("Order Date should be equal or earlier to Current Date");
		$( orderdateObj ).addClass("textbox_errormsg");
		
		return false;
	} else {
		$( '#errOrderDate').text("");
		$( orderdateObj ).removeClass("textbox_errormsg");
		return true;
	}

}

function validateEffecDate() {
	var effecdateObj =  $("#EffectiveDate")
	var effecdate =  $(effecdateObj).val();
	
	if (effecdate == "") {
		$( '#errEffectiveDate').text("Effective Date is required.");
		$( effecdateObj ).addClass("textbox_errormsg");
		
		return false;

	}

	else if (!validateDateDDMMYYY(effecdate)) {
		$( '#errEffectiveDate').text("Enter valid date(dd-mm-yyyy) format");
		$( effecdateObj ).addClass("textbox_errormsg");
		
		return false;
	}
	// not allowed future date
	else if (!compareDateforFutureDDMMYYY(effecdate)) {
		$( '#errEffectiveDate').text("Effective Date should not be future date");
		$( effecdateObj ).addClass("textbox_errormsg");
		
		return false;
	}

	else if (!compareDateforEffectDDMMYYY(effecdate)) {
		$( '#errEffectiveDate').text("Order date should be less or equal to the Effective Date");
		$( effecdateObj ).addClass("textbox_errormsg");
		return false;
	}	

	 else if ($('#preVersionEffDate').val() != null && $('#preVersionEffDate').val() != "") {
		if (!compareDateforPreVersionEffectDDMMYYY(effecdate)) {
			$( '#errEffectiveDate').text("Effective date should not be earlier than effective date of previous versions of Village");
			$( effecdateObj ).addClass("textbox_errormsg");
			
			return false;
		} else {
			$( '#errEffectiveDate').text("");
			$( effecdate ).removeClass("textbox_errormsg");
			return true;
		}
	}
	else {

		$( '#errEffectiveDate').text("");
		$( effecdate ).removeClass("textbox_errormsg");
		return true;
	}
}

function validateGazPubDate() {
	var gazPubDateObj =  $("#GazPubDate");
	var gazPubDate =  $(gazPubDateObj).val();
	
	if (gazPubDate != "") {
		if (!validateDateDDMMYYY(gazPubDate)) {
			$( '#errGazPubDate').text("Enter valid date(dd-mm-yyyy) format");
			$( gazPubDateObj ).addClass("textbox_errormsg");
			return false;

		}

		else if (!compareDateforEffectDDMMYYY(gazPubDate)) {
			$( '#errGazPubDate').text("Gazette Publication Date should be equal to or greater than Order Date");
			$( gazPubDateObj ).addClass("textbox_errormsg");
			
			return false;
		}

		else {
			$( '#errGazPubDate').text("");
			$( gazPubDateObj ).removeClass("textbox_errormsg");
			return true;
		}
	} else {
		$( '#errGazPubDate').text("");
		$( gazPubDateObj ).removeClass("textbox_errormsg");
		return true;
	}
}

function validateGazPubDateForVilModify() {

	var gazPubDate = document.getElementById('GazPubDate').value;
	if (document.getElementById('GazPubDate').value != "") {
		if (!validateDateDDMMYYY(gazPubDate)) {

			document.getElementById("GazPubDate_error").innerHTML = "Enter valid date(dd-mm-yyyy) format";
			$("#GazPubDate_error").show();
			$("#gazPubDatecr").addClass("error_fld");
			$("#gazPubDatecr").addClass("error_msg");
			return false;

		}

		else if (!compareDateforEffectDDMMYYY(gazPubDate)) {
			document.getElementById("GazPubDate_error").innerHTML = "Gazette Publication Date should be equal to or greater than Order Date ";
			$("#GazPubDate_error").show();
			$("#gazPubDatecr").addClass("error_fld");
			$("#gazPubDatecr").addClass("error_msg");
			return false;
		}

		else {
			$("#GazPubDate_error").hide();
			$("#gazPubDatecr").removeClass("error_fld");
			$("#gazPubDatecr").removeClass("error_msg");
			return true;
		}
	} else {
		$("#GazPubDate_error").hide();
		$("#gazPubDatecr").removeClass("error_fld");
		$("#gazPubDatecr").removeClass("error_msg");
		return true;
	}
}

function validateSFile() {

	if (document.getElementById('filGovernmentOrder').value == "") {
		document.getElementById("filGovernmentOrder_error").innerHTML = "Please upload at least one file as Government Order";
		$("#filGovernmentOrder_error").show();

		$("#filGovernmentOrder").addClass("error_fld");
		$("#filGovernmentOrder").addClass("error_msg");
		return false;

	} else {
		$("#filGovernmentOrder_error").hide();

		return true;
	}

}
function days_between(date1, date2) {

	// The number of milliseconds in one day
	var ONE_DAY = 1000 * 60 * 60 * 24;

	// Convert both dates to milliseconds
	var date1_ms = date1.getTime();
	var date2_ms = date2.getTime();

	// Calculate the difference in milliseconds
	var difference_ms = date1_ms - date2_ms;

	// Convert back to days and return
	return Math.round(difference_ms / ONE_DAY);

}

function compareDateDDMMYYY(orderdate) {
	var string_start = orderdate.split("-");
	var date_start = string_start[0];
	var month_start = string_start[1] - 1;
	var year_start = string_start[2];
	var ordeDate = new Date(year_start, month_start, date_start);

	if (days_between(ordeDate, new Date()) > 0) {
		return false;
	} else {
		return true;
	}

}

function compareDateforEffectDDMMYYY(effectiveDate) {

	var orderdate = document.getElementById('OrderDate').value;
	var string_start = orderdate.split("-");
	var date_start = string_start[0];
	var month_start = string_start[1] - 1;
	var year_start = string_start[2];
	var ordeDate = new Date(year_start, month_start, date_start);

	var string_start1 = effectiveDate.split("-");
	var date_start1 = string_start1[0];
	var month_start1 = string_start1[1] - 1;
	var year_start1 = string_start1[2];
	var effectDate = new Date(year_start1, month_start1, date_start1);

	if (days_between(effectDate, ordeDate) >= 0) {
		return true;
	} else {
		return false;
	}

}

function compareDateforFutureDDMMYYY(effectiveDate) {

	var string_start1 = effectiveDate.split("-");
	var date_start1 = string_start1[0];
	var month_start1 = string_start1[1] - 1;
	var year_start1 = string_start1[2];
	var effectDate = new Date(year_start1, month_start1, date_start1);
	if (effectDate <= new Date()) {

		return true;
	} else {

		return false;
	}

}
function compareDateforEffectDDMMYYYcr(effectiveDate) {

	var orderdate = document.getElementById('OrderDate1').value;
	var string_start = orderdate.split("-");
	var date_start = string_start[0];
	var month_start = string_start[1] - 1;
	var year_start = string_start[2];
	var ordeDate = new Date(year_start, month_start, date_start);

	var string_start1 = effectiveDate.split("-");
	var date_start1 = string_start1[0];
	var month_start1 = string_start1[1] - 1;
	var year_start1 = string_start1[2];
	var effectDate = new Date(year_start1, month_start1, date_start1);

	if (days_between(effectDate, ordeDate) >= 0) {
		return true;
	} else {
		return false;
	}

}

function validateDateDDMMYYY(DateOfBirth) {

	var Char1 = DateOfBirth.charAt(2);
	var Char2 = DateOfBirth.charAt(5);
	// alert(Char1); alert(Char2);

	var flag = false;

	if (Char1 == '-' && Char2 == '-') {
		// alert ('valid positions of non numeric characters.');
		flag = true;
	} else {
		// alert('invalid position of non numeric symbols');
		flag = false;
	}

	var day;
	var month;
	var year;

	day = DateOfBirth.substring(0, 2);
	month = DateOfBirth.substring(3, 5);
	year = DateOfBirth.substring(6, 10);

	// alert(day); alert(month);alert(year);
	if (validDay(day) && validMonth(month) && validYear(year) && (flag == true)) {
		// alert(' Valid Date')
		return true;
	} else {
		// alert('Invalid Date Format: Please enter dd-mm-yyyy for Date of
		// Birth!');
		return false;
	}

} // end func

function IsNumeric(sText) {
	var ValidChars = "0123456789.";
	var IsNumber = true;
	var Char;

	for (i = 0; i < sText.length && IsNumber == true; i++) {
		Char = sText.charAt(i);
		if (ValidChars.indexOf(Char) == -1) {
			IsNumber = false;
		}
	}

	return IsNumber;
} // end func

function validDay(day) {
	if (IsNumeric(day)) {
		if (day > 0 && day < 32) {
			return true;
		} else {
			return false;
		}
	} else {
		return false;
	}

}// end func

function validMonth(month) {
	if (IsNumeric(month)) {
		if (month > 0 && month < 13) {
			return true;
		} else {
			return false;
		}
	} else {
		return false;
	}
}// end func

function validYear(year) {
	var d = new Date();
	var currentYear = d.getFullYear();

	if (year.length != 4) {
		return false;
	}

	if (IsNumeric(year)) {
		if (year > 0 && year <= 2023) {
			return true;
		} else {
			return false;
		}

	} else {
		return false;
	}

}// end func

/*
 * function officialAddress() {
 * 
 * if(document.getElementById("OfficialAddress").value == "" ) {
 * document.getElementById("OfficialAddress_error").innerHTML="State Name in
 * English is Required"; $("#OfficialAddress_error").show();
 * $("#OfficialAddress_msg").hide();
 * $("#OfficialAddress").addClass("error_fld");
 * $("#OfficialAddress").addClass("error_msg"); return false; } else {
 * $("#OfficialAddress_msg").hide(); return true; } }
 */
function onCsave() {

	var errors = new Array();
	var error = false;

	errors[0] = vlidateOnblur('deptNamecr', '1', '15', 'c');
	if (errors[0] == true) {
		error = true;
	}

	errors[1] = vlidateOnblur('deptNameLocal', '1', '15', 'c');
	if (errors[1] == true) {
		error = true;
	}
	errors[2] = vlidateOnblur('shortDeptName', '1', '15', 'c');
	if (errors[2] == true) {
		error = true;
	}

	if (error == true) {

		showClientSideError();

		return false;
	} else {

		return true;
	}

}

// Modify mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmnmmmmmmChange ----
function validateAllGov1() {
	var msg = null;

	if (!validateOrdeNo1()) {

		// msg="Please Enter Order Number"+ '\n';

	}
	if (!validateOrdeDate1()) {
		if (msg != null) {
			// msg=msg+"Please select Order Date"+ '\n';
		} else {
			// msg="Please select Order Date"+ '\n';
		}
	}
	if (!validateEffecDate1()) {
		if (msg != null) {
			// msg=msg+"Please select Effective Date"+ '\n';
		} else {
			// msg="Please select Effective Date"+ '\n';
		}
	}
	if (!validateGazPubDate1()) {
		if (msg != null) {
			// msg=msg+"Please select Gaz Publication Date"+ '\n';
		} else {
			// msg="Please select Gaz Publication Date"+ '\n';
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
function validateAllGov1() {

	var errors = new Array();
	var error = false;
	errors[0] = !validateOrdeNo1();
	errors[1] = !validateOrdeDate1();
	errors[2] = !validateEffecDate1();
	errors[3] = !validateGazPubDate1();

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

function validateOrdeNo1() {

	if (document.getElementById('OrderNo1').value == "") {
		// document.getElementById("OrderNo_error1").innerHTML="Order No. is
		// required.";
		$("#OrderNo_error1").show();
		$("#OrderNo_msg1").hide();
		$("#OrderNo1").addClass("error_fld");
		$("#OrderNo1").addClass("error_msg");
		return false;

	} else {
		// $("#OrderNo_msg1").hide();

		return true;
	}

}

function validateOrdeDate1() {

	var orderdate = document.getElementById('OrderDate1').value;
	if (document.getElementById('OrderDate1').value == "") {
		document.getElementById("OrderDate_error1").innerHTML = "Order Date is required.";
		$("#OrderDate_error1").show();
		$("#OrderDate_msg1").hide();
		$("#OrderDate1").addClass("error_fld");
		$("#OrderDate1").addClass("error_msg");
		return false;

	}

	else if (!validateDateDDMMYYY(orderdate)) {
		document.getElementById("OrderDate_error1").innerHTML = "Enter valid date(dd-mm-yyyy) format";
		$("#OrderDate_error1").show();
		$("#OrderDate_msg1").hide();
		$("#OrderDate1").addClass("error_fld");
		$("#OrderDate1").addClass("error_msg");
		return false;
	} else if (!compareDateDDMMYYY(orderdate)) {
		document.getElementById("OrderDate_error1").innerHTML = "Order Date should be equal or previous to Current Date ";
		$("#OrderDate_error1").show();
		$("#OrderDate_msg1").hide();
		$("#OrderDate1").addClass("error_fld");
		$("#OrderDate1").addClass("error_msg");
		return false;
	} else {
		$("#OrderDate_msg1").hide();
		return true;
	}

}
function validateEffecDate1() {

	var effecdate = document.getElementById('EffectiveDate1').value;
	if (document.getElementById('EffectiveDate1').value == "") {
		document.getElementById("EffectiveDate_error1").innerHTML = "Effective Date is required.";
		$("#EffectiveDate_error1").show();
		$("#EffectiveDate_msg1").hide();
		$("#EffectiveDate1").addClass("error_fld");
		$("#EffectiveDate1").addClass("error_msg");
		return false;

	}

	else if (!validateDateDDMMYYY(effecdate)) {
		document.getElementById("EffectiveDate_error1").innerHTML = "Enter valid date(dd-mm-yyyy) format";
		$("#EffectiveDate_error1").show();
		$("#EffectiveDate_msg1").hide();
		$("#EffectiveDate1").addClass("error_fld");
		$("#EffectiveDate1").addClass("error_msg");
		return false;
	} else if (!compareDateforEffectDDMMYYYcr(effecdate)) {
		document.getElementById("EffectiveDate_error1").innerHTML = "Effective Date should be equal to or greater than Order Date ";
		$("#EffectiveDate_error1").show();
		$("#EffectiveDate_msg1").hide();
		$("#EffectiveDate1").addClass("error_fld");
		$("#EffectiveDate1").addClass("error_msg");
		return false;
	}

	else {
		$("#EffectiveDate_msg1").hide();

		return true;
	}
}

function validateGazPubDate1() {

	var gazPubDate = document.getElementById('gazPubDatecr').value;
	if (document.getElementById('gazPubDatecr').value == "") {
		document.getElementById("GazPubDate_error1").innerHTML = " Gazette Publication Date is required.";
		$("#GazPubDate_error1").show();
		$("#GazPubDate_msg1").hide();
		$("#gazPubDatecr").addClass("error_fld");
		$("#gazPubDatecr").addClass("error_msg");
		return false;

	}

	else if (!validateDateDDMMYYY(gazPubDate)) {

		document.getElementById("GazPubDate_error1").innerHTML = "Enter valid date(dd-mm-yyyy) format";
		$("#GazPubDate_error1").show();
		$("#GazPubDate_msg1").hide();
		$("#gazPubDatecr").addClass("error_fld");
		$("#gazPubDatecr").addClass("error_msg");
		return false;

	}

	else if (!compareDateforEffectDDMMYY(gazPubDate)) {
		document.getElementById("GazPubDate_error1").innerHTML = "Gazette Publication Date should be equal to or greater than Order Date ";
		$("#GazPubDate_error1").show();
		$("#GazPubDate_msg1").hide();
		$("#gazPubDatecr").addClass("error_fld");
		$("#gazPubDatecr").addClass("error_msg");
		return false;
	}

	else {
		$("#GazPubDate_msg1").hide();
		return true;
	}

}
// Modify Correction
/*
 * function validateAllGov() { var msg=null;
 * 
 * if (!validateOrdeNo()) {
 * 
 * msg="Please Enter Order Number"+ '\n'; } if (!validateOrdeDate()) {
 * if(msg!=null) { msg=msg+"Please select Order Date"+ '\n'; } else {
 * msg="Please select Order Date"+ '\n'; } } if (!validateEffecDate()) {
 * if(msg!=null) { msg=msg+"Please select Effective Date"+ '\n'; } else {
 * msg="Please select Effective Date"+ '\n'; } } if (!validateGazPubDate()) {
 * if(msg!=null) { msg=msg+"Please select Gaz Publication Date"+ '\n'; } else {
 * msg="Please select Gaz Publication Date"+ '\n'; } } if (!validateSFile()) {
 * if(msg!=null) { msg=msg+"Please Upload File"+ '\n'; } else { msg="Please
 * Upload File"+ '\n'; } } if (!officialAddress()) { if(msg!=null) {
 * msg=msg+"Please Enter State name "+ '\n'; } else { msg="Please Enter State
 * Name"+ '\n'; } }
 * 
 * if(msg!=null) { alert(msg); return false; } else{
 * 
 * return true; }
 * 
 * return false; }
 */
function validateDeptAllGov() {

	var errors = new Array();
	var error = false;

	errors[0] = !validateOrdeNo();
	errors[1] = !validateOrdeDate();
	errors[2] = !validateEffecDate();
	errors[3] = !validateGazPubDate();

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
	return false;
}

function validateVillage() {
	var msg = null;

	if (!validateOrdeNo()) {

		msg = "Please Enter Order Number" + '\n';

	}
	if (!validateOrdeDate()) {
		if (msg != null) {
			msg = msg + "Please select Order Date" + '\n';
		} else {
			msg = "Please select Order Date" + '\n';
		}
	}
	if (!validateEffecDate()) {
		if (msg != null) {
			msg = msg + "Please select Effective Date" + '\n';
		} else {
			msg = "Please select Effective Date" + '\n';
		}
	}
	if (!validateGazPubDate()) {
		if (msg != null) {
			msg = msg + "Please select Gaz Publication Date" + '\n';
		} else {
			msg = "Please select Gaz Publication Date" + '\n';
		}
	}
	if (!validateSFile()) {
		if (msg != null) {
			msg = msg + "Please Upload File" + '\n';
		} else {
			msg = "Please Upload File" + '\n';
		}
	}
	if (!villageName()) {
		if (msg != null) {
			msg = msg + "Please Enter Village name " + '\n';
		} else {
			msg = "Please Enter Village Name" + '\n';
		}
	}

	if (msg != null) {
		alert(msg);
		return false;
	} else {

		return true;
	}

	return true;
}
function officialAddress() {

	if (document.getElementById("OfficialAddress").value == "") {
		document.getElementById("OfficialAddress_error").innerHTML = "Local Government Type Name is Required";
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

function validatesubDistrict() {

	var errors = new Array();

	var error = false;

	errors[0] = vlidateOnblur('subdistrictname', '1', '15', 'm');

	if (errors[0] == true) {
		error = true;
	}

	/*
	 * for(var i=startNum ; i<=endNum ; i++) { if(errors[i]==true) { error =
	 * true; } }
	 */

	if (error == true) {

		showClientSideError();

		return false;
	} else {
		return true;
	}

}
/*
 * function validateAll() {
 * 
 * selectDistrict();
 * 
 * var errors = new Array(); var error = false; errors[0] =
 * !validateSourceState(); errors[1] = !validateTargetState(); errors[2] =
 * !validateDistrict();
 * 
 * for ( var i = 0; i < 3; i++) {
 * 
 * if (errors[i] == true) {
 * 
 * error = true; break; } }
 * 
 * if (error == true) {
 * 
 * showClientSideError();
 * 
 * return false; } else { return true; } }
 */
function validatedistrict() {
	var msg = null;
	if (!validateOrdeNo()) {

		msg = "Please Enter Order Number" + '\n';

	}
	if (!validateOrdeDate()) {
		if (msg != null) {
			msg = msg + "Please select Order Date" + '\n';
		} else {
			msg = "Please select Order Date" + '\n';
		}
	}
	if (!validateEffecDate()) {
		if (msg != null) {
			msg = msg + "Please select Effective Date" + '\n';
		} else {
			msg = "Please select Effective Date" + '\n';
		}
	}
	if (!validateGazPubDate()) {
		if (msg != null) {
			msg = msg + "Please select Gaz Publication Date" + '\n';
		} else {
			msg = "Please select Gaz Publication Date" + '\n';
		}
	}
	if (!validateSFile()) {
		if (msg != null) {
			msg = msg + "Please Upload File" + '\n';
		} else {
			msg = "Please Upload File" + '\n';
		}
	}
	if (!districtName()) {
		if (msg != null) {
			msg = msg + "Please Enter District name " + '\n';
		} else {
			msg = "Please Enter District Name" + '\n';
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
function validatedepartment() {
	var msg = null;
	if (!validateOrdeNo()) {

		msg = "Please Enter Order Number" + '\n';

	}
	if (!validateOrdeDate()) {
		if (msg != null) {
			msg = msg + "Please select Order Date" + '\n';
		} else {
			msg = "Please select Order Date" + '\n';
		}
	}
	if (!validateEffecDate()) {
		if (msg != null) {
			msg = msg + "Please select Effective Date" + '\n';
		} else {
			msg = "Please select Effective Date" + '\n';
		}
	}
	if (!validateGazPubDate()) {
		if (msg != null) {
			msg = msg + "Please select Gaz Publication Date" + '\n';
		} else {
			msg = "Please select Gaz Publication Date" + '\n';
		}
	}
	if (!validateSFile()) {
		if (msg != null) {
			msg = msg + "Please Upload File" + '\n';
		} else {
			msg = "Please Upload File" + '\n';
		}
	}
	if (!districtName()) {
		if (msg != null) {
			msg = msg + "Please Enter District name " + '\n';
		} else {
			msg = "Please Enter District Name" + '\n';
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
function validateblock() {
	var msg = null;

	if (!validateOrdeNo()) {

		msg = "Please Enter Order Number" + '\n';

	}
	if (!validateOrdeDate()) {
		if (msg != null) {
			msg = msg + "Please select Order Date" + '\n';
		} else {
			msg = "Please select Order Date" + '\n';
		}
	}
	if (!validateEffecDate()) {
		if (msg != null) {
			msg = msg + "Please select Effective Date" + '\n';
		} else {
			msg = "Please select Effective Date" + '\n';
		}
	}
	if (!validateGazPubDate()) {
		if (msg != null) {
			msg = msg + "Please select Gaz Publication Date" + '\n';
		} else {
			msg = "Please select Gaz Publication Date" + '\n';
		}
	}
	if (!validateSFile()) {
		if (msg != null) {
			msg = msg + "Please Upload File" + '\n';
		} else {
			msg = "Please Upload File" + '\n';
		}
	}
	if (!blockName()) {
		if (msg != null) {
			msg = msg + "Please Enter Block name " + '\n';
		} else {
			msg = "Please Enter Block Name" + '\n';
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

function MinistryNamecr() {
	if (document.getElementById("ministryNamecr").value == "") {

		document.getElementById("ministryNamecr_error").innerHTML = "Ministry Name is Required";
		$("#ministryNamecr_error").show();
		$("#ministryNamecr_msg").hide();
		$("#ministryNamecr").addClass("error_fld");
		$("#ministryNamecr").addClass("error_msg");
		return false;
	} else {
		$("#ministryName_msg").hide();
		return true;

	}
}

/*
 * function validateMinistryChange() { var msg=null;
 * 
 * if (!validateOrdeNo()) {
 * 
 * //msg="Please Enter Order Number"+ '\n'; } if (!validateOrdeDate()) {
 * if(msg!=null) { //msg=msg+"Please select Order Date"+ '\n'; } else {
 * //msg="Please select Order Date"+ '\n'; } } if (!validateEffecDate()) {
 * if(msg!=null) { //msg=msg+"Please select Effective Date"+ '\n'; } else {
 * //msg="Please select Effective Date"+ '\n'; } } if (!validateGazPubDate()) {
 * if(msg!=null) { //msg=msg+"Please select Gaz Publication Date"+ '\n'; } else {
 * //msg="Please select Gaz Publication Date"+ '\n'; } } if (!validateSFile()) {
 * if(msg!=null) { //msg=msg+"Please Upload File"+ '\n'; } else { //msg="Please
 * Upload File"+ '\n'; } } if (!MinistryNamecr()) { if(msg!=null) {
 * //msg=msg+"Please Enter Ministry name "+ '\n'; } else { //msg="Please Enter
 * Ministry Name"+ '\n'; } } if(msg!=null) { alert(msg); return false; } else{
 * 
 * return true; }
 * 
 * return false; }
 */

function validateMinistryChange() {

	var errors = new Array();
	var error = false;
	if (!MinistryNamecr()) {
		errors[0] = vlidateOnblur('ministryNamecr', '1', '15', 'c');
	}
	if (errors[0] == true) {
		error = true;
	}

	if (!validateOrdeNo()) {
		errors[1] = vlidateOnblur('OrderNo', '1', '15', 'c');
	}
	if (errors[1] == true) {
		error = true;
	}

	if (!validateOrdeDate()) {
		errors[2] = vlidateOnblur('OrderDate', '1', '15', 'c');
	}
	if (errors[2] == true) {
		error = true;
	}

	if (!validateEffecDate()) {
		errors[3] = vlidateOnblur('EffectiveDate', '1', '15', 'c');
	}
	if (errors[3] == true) {
		error = true;
	}

	if (!validateSFile()) {
		errors[4] = vlidateOnblur('filGovernmentOrder', '1', '15', 'c');
	}
	if (errors[4] == true) {
		error = true;
	}

	/*
	 * for ( var i = 0; i < 5; i++) {
	 * 
	 * if (errors[i] == true) {
	 * 
	 * error = true; break; } }
	 */

	if (error == true) {

		showClientSideError();

		return false;
	} else {
		return true;
	}
}

function validateAllGov123() {
	var msg = null;

	if (!validateOrdeNo()) {

		msg = "Please Enter Order Number" + '\n';

	}
	if (!validateOrdeDate()) {
		if (msg != null) {
			msg = msg + "Please select Order Date" + '\n';
		} else {
			msg = "Please select Order Date" + '\n';
		}
	}
	if (!validateEffecDate()) {
		if (msg != null) {
			msg = msg + "Please select Effective Date" + '\n';
		} else {
			msg = "Please select Effective Date" + '\n';
		}
	}
	if (!validateGazPubDate()) {
		if (msg != null) {
			msg = msg + "Please select Gaz Publication Date" + '\n';
		} else {
			msg = "Please select Gaz Publication Date" + '\n';
		}
	}
	if (!validateSFile()) {
		if (msg != null) {
			msg = msg + "Please Upload File" + '\n';
		} else {
			msg = "Please Upload File" + '\n';
		}
	}
	/*
	 * if (!officialAddress()) { if(msg!=null) { msg=msg+"Please Enter State
	 * name "+ '\n'; } else { msg="Please Enter State Name"+ '\n'; } }
	 */

	/*
	 * if (!officialAddressType()) { if(msg!=null) { msg=msg+"Please Enter Local
	 * Government Type Name "+ '\n'; } else { msg="Please Enter Local Government
	 * Type Name"+ '\n'; } }
	 */
	if (msg != null) {
		alert(msg);
		return false;
	} else {

		return true;
	}

	return false;
}
function validateLbType() {

	if (document.getElementById("txtlocalBodyTypeName").value == "") {
		document.getElementById("txtlocalBodyTypeName_error").innerHTML = "Local Government Type Name is Required";
		$("#txtlocalBodyTypeName_error").show();
		$("#OfficialAddressType").addClass("error_fld");
		$("#OfficialAddressType").addClass("error_msg");
		return false;

	} else {
		$("#txtlocalBodyTypeName_error").hide();
		return true;

	}
}
function validateLocalBodyType() {
	var msg = null;

	if (!validateLbType()) {
		if (msg != null) {
			msg = msg + "Please Enter Local Government Type name " + '\n';
		} else {
			msg = "Please Enter Local Government Type Name" + '\n';
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

function validateGovtOrder1() {

	var errors = new Array();
	var error = false;
	var govtOrderStatus = document.getElementById('hdngovtOrderConfig').value;

	errors[0] = !validateOrdeNo();
	errors[1] = !validateOrdeDate();
	errors[2] = !validateEffecDate();

	if (govtOrderStatus == 'govtOrderUpload') {
		errors[3] = !validateGazPubDate();
		errors[4] = !validateSFile1();

	} else if (govtOrderStatus == 'govtOrderGenerate') {
		errors[5] = !validateFileTemplate();
	}

	for ( var i = 0; i < 6; i++) {
		if (errors[i] == true) {
			error = true;
			break;
		}
	}
	//alert(error);
	if (error == true) {
		alert('You have not entered the mandatory fields.');
		//showClientSideError();
		

		return false;
	} else {
		 //$( "#btnSave" ).prop( "disabled", true );
		 $( "#btnCancel" ).prop( "disabled", true );
		// jQuery('input[type=submit]').attr('disabled', 'disabled');
		// document.getElementById("btnSave").disabled = "disabled";
		return true;
	}

	return false;
}

function validateGovtOrderDetails() {

	var errors = new Array();
	var error = false;

	errors[0] = !validateOrdeNo();
	errors[1] = !validateOrdeDate();
	errors[2] = !validateEffecDate();

	errors[3] = !validateGazPubDate();
	errors[4] = !validateSFile1();

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
		return true;
	}

	return false;
}

function validateGovtOrderDetailsForVil() {

	var errors = new Array();
	var error = false;

	if (document.getElementById('actionNonExistingGO').checked == false && document.getElementById('actionExistingGO').checked == false) {
		alert("Please Select Goverment Order Type");
		return false;
	}
	errors[0] = !validateOrdeNo();
	errors[1] = !validateOrdeDate();
	errors[2] = !validateEffecDate();
	errors[3] = !validateGazPubDate();
	if (document.getElementById('actionNonExistingGO').checked) {
		errors[4] = !validateSFile1ForVil();
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
		return true;
	}

	return false;
}

/*Added by pooja on 19-08-2015*/
function validateGovtOrderDetailsForVillage(govtOrderConfig) {
	var errors = new Array();
	var error = false;
	errors[0] = !validateOrdeNo();
	errors[1] = !validateOrdeDate();
	errors[2] = !validateEffecDate();
	if(govtOrderConfig!="false"){
	errors[3] = !validateGazPubDate();
	}
	if(document.getElementById('checkNewOrExist') != null)
	errors[4] = !validateSFile1ForVil();
	
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
		return true;
	}
	return false;
}

/*Modified by POoja on 20-08-2015*/
function validateGovtOrderDetailsForVilModify() {
	var errors = new Array();
	var error = false;
	errors[0] = !validateOrdeNo();
	errors[1] = !validateOrdeDate();
	errors[2] = !validateEffecDate();
	errors[3] = !validateGazPubDateForVilModify();
	var filecount = document.getElementById('govtfilecount').value;
	if (document.getElementById('checkNewOrExist') != null && filecount == 0)
		errors[4] = !validateSFile1();
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
		return true;
	}
	return false;
}

function validateSFile1() {
	var attachFile1 =  $("#attachFile1").val();
	if (attachFile1 == "") {
		$( '#errattachFile1').text("Please upload at atleast one file as Government Order");
		$( attachFile1 ).addClass("error");
		return false;

	} else {
		$( '#errattachFile1').text("");
		$( attachFile1 ).removeClass("error");
		return true;
	}

}

function validateSFile1ForVil() {

	if (document.getElementById('attachFile2').value == "") {
		document.getElementById("attachFile2_error").innerHTML = "Please upload at aleast one file as Government Order";
		$("#attachFile2_error").show();
		$("#attachFile2").addClass("error_fld");
		$("#attachFile2").addClass("error_msg");
		return false;

	} else {
		$("#attachFile2_error").hide();
		$("#attachFile2").removeClass("error_fld");
		$("#attachFile2").removeClass("error_msg");
		return true;
	}

}

function validateFileTemplate() {
	if (document.getElementById('templateList').selectedIndex == 0) {
		$( '#errtemplateList').text("Please select Government Order Template");
		$( '#templateList' ).addClass("error");
		
		return false;
	} else {
		$( '#errtemplateList').text("");
		$( '#templateList' ).removeClass("error");
		return true;
	}
}

function validateGovtOrder() {

	var msg = null;

	if (!validateOrdeNo()) {

		msg = "Please Enter Order Number" + '\n';

	}
	if (!validateOrdeDate()) {
		if (msg != null) {
			msg = msg + "Please Enter valid Order Date" + '\n';
		} else {
			msg = "Please Enter valid Order Date" + '\n';
		}
	}
	if (!validateEffecDate()) {
		if (msg != null) {
			msg = msg + "Please Enter valid Effective Date" + '\n';
		} else {
			msg = "Please Enter valid Effective Date" + '\n';
		}
	}
	if (!validateGazPubDate()) {
		if (msg != null) {
			msg = msg + "Please Enter valid Gazette Publication Date" + '\n';
		} else {
			msg = "Please Enter valid Gazette Publication Date" + '\n';
		}
	}
	if (!validateSFile1()) {
		if (msg != null) {
			msg = msg + "Please Upload at least one File" + '\n';
		} else {
			msg = "Please Upload at least one File" + '\n';
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

/**
 * Comparing Effective Date to effective date of Previous versions of Village
 * @author Pooja
 * @since 16-11-2015
 * @param effectiveDate
 * @returns {Boolean}
 */
function compareDateforPreVersionEffectDDMMYYY(effectiveDate) {

	var preVersionEffDate = $('#preVersionEffDate').val();
	var string_start = preVersionEffDate.split("-");
	var date_start = string_start[2];
	var month_start = string_start[1] - 1;
	var year_start = string_start[0];
	var preVersionEffecDate = new Date(year_start, month_start, date_start);
	
	var string_start1 = effectiveDate.split("-");
	var date_start1 = string_start1[0];
	var month_start1 = string_start1[1] - 1;
	var year_start1 = string_start1[2];
	var effectDate = new Date(year_start1, month_start1, date_start1);
	if (days_between(effectDate, preVersionEffecDate) >= 0) {
		return true;
	} else {
		return false;
	}

}
