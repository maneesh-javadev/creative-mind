/*@Author - Ram Krishna Chauhan */
var i = 2;
var max = 0;
var dropdown_pos = 1;
var sel_drop_value = new Array();
var sel_drop_value_pos = 1;
var allchk = 0;
var dynstart = 0;
var dynend = 0;
var dynfirst4 = 1;
var dynstart4 = 1;
var dynend4 = 1;
var tmplabel = "";
var isnew_officialtype = true;
var isnotcomplete = false;
var newsubtype = 1;
var divId = 0;
var designationCode;
var designationName;
var index;
var tempName;
/*
 * function validatorforDesignation() { isnotcomplete = false; for ( var j = 0;
 * j < i; j++) { if (document.getElementById("desgName" + j).value == "") {
 * alert("please enter the mandatory fields");
 * $("#desgName"+j).addClass("error_fld"); isnotcomplete = true;
 * alert("isnotcomplete:::: "+isnotcomplete); } }
 * 
 * if(isnotcomplete==false) { alert("isnotcomplete:::: "+isnotcomplete); for
 * (var z=50;z<i;z++) {
 * document.getElementById("isMultiple").value+=document.getElementById("chkbx" +
 * z).checked + ","; } document.forms['designationForm'].submit(); }
 * 
 * if (document.getElementById("lgtype").value == "") {
 * $("#lgtype").addClass("error_fld"); isnotcomplete = true; } for ( var j = 0;
 * j < i; j++) { if (document.getElementById("desgName" + j).value == "") {
 * alert("please enter the mandatory fields");
 * $("#desgName"+j).addClass("error_fld"); isnotcomplete = true; } if
 * (document.getElementById("desgNameLocal" + j).value == "") {
 * $("#desgNameLocal"+j).addClass("error_fld"); isnotcomplete = true; } //} //
 * To remove * if user has entered values if
 * (document.getElementById("lgtype").value != "")
 * $("#lgtype").removeClass("error_fld"); for ( var j = 0; j < i; j++) { if
 * (document.getElementById("desgName" + j).value !="") {
 * 
 * $("#desgName"+j).removeClass("error_fld"); isnotcomplete = false; }
 * 
 * if (document.getElementById("desgNameLocal" + j).value !="") {
 * $("#desgNameLocal"+j).removeClass("error_fld"); isnotcomplete = false; } }
 */

function getCheckBoxValue() {

	for ( var z = 1; z < i; z++) {
		document.getElementById("isMultiple").value += document.getElementById("chkbx" + z).checked + ",";

	}
}

/*
 * function onSubmitDesignationAtCenterFinal() { getCheckBoxValue();
 * 
 * var errors = new Array(); var error = false;
 * 
 * errors[0] = !validateOrganizationType(); errors[1] = !validateOrganization();
 * errors[2] = !validatelabel(); errors[3] = !validateTopDesignation();
 * errors[4] = !validateOtherDesignation(); errors[5] =
 * !validateTopOtherDedisgnation(); for ( var i = 0; i < 6; i++) {
 * 
 * if (errors[i] == true) {
 * 
 * error = true; break; } }
 * 
 * if (error == true) { showClientSideError(); return false; } else {
 * document.getElementById('orgType').disabled = false;
 * document.getElementById('orgCode').disabled = false; //
 * document.forms['designationForm'].submit(); return true; } }
 */
function onSubmitDesignationAtCenterFinal() {

	var temp = null;
	var flag = 0;
	var sub = false;
	isnotcomplete = false;
	var desName = "", desNameLocal = "";
	if (document.getElementById('orgType').selectedIndex == 0) {
		// k=1;
		isnotcomplete = true;
		alert("Please select Organization Type");
	}
	if (document.getElementById('orgCode').selectedIndex == 0) {
		// k=1;
		isnotcomplete = true;
		alert("Please select Organization");
	}
	if (document.getElementById('locatedAtLevel').selectedIndex == 0) {
		// k=1;
		isnotcomplete = true;
		alert("Please select Organization Level");
	}

	var del = document.getElementById('bindex').value;
	var delflag;
	var newi;
	var n = del.split(",");
	for ( var j = 0; j < i; j++) {
		delflag = true;
		for ( var k = 0; k < n.length; k++) {
			newi = parseInt(n[k]);
			if (newi == j) {
				delflag = false;

			}

		}
		if (delflag == true) {
			if (document.getElementById("desgName" + j) != null && document.getElementById("desgNameLocal" + j) != null) {
				if (document.getElementById("desgName" + j).value == "" || document.getElementById("desgNameLocal" + j).value == "") {
					flag = 1;
					$("#desgName" + j).addClass("error_fld");
					isnotcomplete = true;
					// alert("isnotcomplete:::: "+isnotcomplete);
				} else {
					desName = desName + document.getElementById("desgName" + j).value;
					desNameLocal = desNameLocal + document.getElementById("desgNameLocal" + j).value;
				}
			}

			/*
			 * alert(document.getElementById("desgName" + j).value.trim() =="");
			 * alert(document.getElementById("desgNameLocal" + j).value.trim() ==
			 * "");
			 */

			if (flag == 1) {
				alert("please enter the mandatory fields");
				flag = 0;
				document.getElementById("errormsg").innerHTML = "";
			} else {

				if (isnotcomplete == false) {

					// alert("isnotcomplete:::: "+isnotcomplete);
					for ( var y = 0; y <= j; y++) {
						delflag = true;
						for ( var k = 0; k < n.length; k++) {
							newi = parseInt(n[k]);
							if (newi == y) {
								delflag = false;

							}

						}
						if (delflag == true) {

							for ( var m = 0; m < y; m++) {

								delflag = true;
								for ( var k = 0; k < n.length; k++) {
									newi = parseInt(n[k]);
									if (newi == m) {
										delflag = false;

									}

								}
								if (delflag == true) {

									if (document.getElementById("desgName" + y).value == document.getElementById("desgName" + m).value) {
										isnotcomplete = true;
									}
								}
							}

							if (isnotcomplete == true) {
								document.getElementById("errormsg").innerHTML = "*Error-Top Designation Name and Other's Designation Name must be unique";
								break;
							} else {
								for ( var m = 0; m < y; m++) {
									delflag = true;
									for ( var k = 0; k < n.length; k++) {
										newi = parseInt(n[k]);
										if (newi == m) {
											delflag = false;

										}

									}
									if (delflag == true) {
										if (document.getElementById("desgNameLocal" + y).value != ""
												|| document.getElementById("desgNameLocal" + m).value != "") {
											if (document.getElementById("desgNameLocal" + y).value == document.getElementById("desgNameLocal" + m).value) {
												isnotcomplete = true;
											}
										}
									}
								}

								if (isnotcomplete == true) {
									document.getElementById("errormsg").innerHTML = "*Error-Top Designation Name Local and Other's Designation Name Local must be unique";
									break;
								}
							}

						}

					}
				}

				/*  */

			}

		}
	}

	for ( var z = 1; z < i; z++) {
		if (document.getElementById("chkbx" + z) != null)
			document.getElementById("isMultiple").value += document.getElementById("chkbx" + z).checked + ",";
		if (document.getElementById("contractPer" + z) != null)
			document.getElementById("isContractPer").value += document.getElementById("contractPer" + z).checked + ",";
	}

	if (document.getElementById("DE") != null) {
		if (document.getElementById('deletedDesignation').value != "") {
			var itrCount = ((document.getElementById("DE").value.split("~")).length - 1)
					- (document.getElementById('deletedDesignation').value.split(",")).length;
		} else {
			var itrCount = ((document.getElementById("DE").value.split("~")).length - 1);
		}

		if (i >= itrCount) {

			for ( var a = itrCount; a < i; a++) {
				if (document.getElementById("desgName" + a) != null)

					if (temp == null)
						temp = document.getElementById("desgName" + a).value + "," + document.getElementById("desgNameLocal" + a).value + ","
								+ document.getElementById("chkbx" + a).checked + "," + document.getElementById("contractPer" + a).checked + "~";
					else
						temp += document.getElementById("desgName" + a).value + "," + document.getElementById("desgNameLocal" + a).value + ","
								+ document.getElementById("chkbx" + a).checked + "," + document.getElementById("contractPer" + a).checked + "~";
			}
			document.getElementById("modifiedDesignation").value = "";

			document.getElementById("modifiedDesignation").value = temp;

		}
	}

	if (isnotcomplete == false) {
		document.getElementById('locatedAtLevel').disabled = false;
		sub = true;

	}

	for ( var k = 0; k < desName.length; k++) {
		var char1 = desName.charAt(k);
		var cc = char1.charCodeAt(0);

		if ((cc >= 48) && (cc <= 58) || (cc >= 65) && (cc <= 90) || (cc >= 97) && (cc <= 122) || (cc == 32)) {

		} else {
			document.getElementById("errormsg").innerHTML = "Please use numeric,Alphabets and Space value only in Designation Name";
			return false;
		}
	}

	for ( var k = 0; k < desNameLocal.length; k++) {
		var char1 = desNameLocal.charAt(k);
		var cc = char1.charCodeAt(0);

		if ((cc >= 48) && (cc <= 58) || (cc >= 65) && (cc <= 90) || (cc >= 97) && (cc <= 122) || (cc == 32)) {

		} else {
			document.getElementById("errormsg").innerHTML = "Please use numeric,Alphabets and Space value only in Designation Name Local";
			return false;
		}
	}
	return sub;
}

function validateOrganizationType() {

	var orgType = document.getElementById('orgType');

	if (orgType.selectedIndex == 0) {
		$("#orgType_error").show();
		return false;
	}
	$("#orgType_error").hide();
	return true;
}

function validateOrganization() {

	var orgCode = document.getElementById('orgCode');

	if (orgCode.selectedIndex == 0) {
		$("#orgCode_error").show();
		return false;
	}
	$("#orgCode_error").hide();
	return true;
}

function validatelabel() {

	var locatedAtLevel = document.getElementById('locatedAtLevel');

	if (locatedAtLevel.selectedIndex == 0) {
		$("#locatedAtLevel_error").show();
		return false;
	}
	$("#locatedAtLevel_error").hide();
	return true;
}

function validateTopDesignation() {
	if (document.getElementById('desgName0').value == "") {
		$("#desgName0_error").show();
		return false;
	}
	$("#desgName0_error").hide();
	return true;
}

function validateOtherDesignation() {
	if (document.getElementById('desgName1').value == "") {
		$("#desgName1_error").show();
		return false;
	}
	$("#desgName1_error").hide();
	return true;
}

function validateTopOtherDedisgnation() {
	if (document.getElementById('desgName0').value != "" && document.getElementById('desgName1').value != "") {
		var topdesig = document.getElementById('desgName0').value;
		var otherdesig = document.getElementById('desgName1').value;
		if (topdesig == otherdesig) {
			document.getElementById("errormsg").innerHTML = "*Error-Top Designation Name and Other's Designation Name must be unique";
			return false;
		}
		$("#errormsg").hide();
		return true;
	}
}

function validateLocalNameInEng() {

	if (document.getElementById('localBodyNameInEn').value == "") {
		$("#localBodyNameInEn_error").show();
		return false;
	}
	$("#localBodyNameInEn_error").hide();
	return true;

}

function onSubmitDesignationAtCenter() {

	getCheckBoxValue();
	var errors = new Array();
	var error = false;

	errors[0] = vlidateOnblur('orgType', '1', '15', 'c');
	if (errors[0] == true) {
		error = true;
	}
	errors[1] = vlidateOnblur('orgCode', '1', '15', 'c');
	if (errors[1] == true) {
		error = true;
	}
	alert("error=== " + error);
	if (error == true) {

		showClientSideError();

		return false;
	} else {
		document.getElementById('orgType').disabled = false;
		document.getElementById('orgCode').disabled = false;
		document.forms['designationForm'].submit();
		return true;
	}

}

function checkBoxValueDesignation() {
	// IMPLEMENTED IN DESIGNATION HIRERARCHY PAGES --- SHIVA
	// validator();
	var temp = null;
	var flag = 0;
	var sub = false;
	isnotcomplete = false;
	var desName = "", desNameLocal = "";
	if (document.getElementById('lgtype').selectedIndex == 0) {
		// k=1;
		isnotcomplete = true;
		alert("Please select Localbody type");
	}

	var del = document.getElementById('bindex').value;
	var delflag;
	var newi;
	var n = del.split(",");
	for ( var j = 0; j < i; j++) {
		delflag = true;
		for ( var k = 0; k < n.length; k++) {
			newi = parseInt(n[k]);
			if (newi == j) {
				delflag = false;

			}

		}
		if (delflag == true) {
			if (document.getElementById("desgName" + j) != null && document.getElementById("desgNameLocal" + j) != null) {
				if (document.getElementById("desgName" + j).value == "" || document.getElementById("desgNameLocal" + j).value == "") {

					flag = 1;
					$("#desgName" + j).addClass("error_fld");
					isnotcomplete = true;
					// alert("isnotcomplete:::: "+isnotcomplete);
				} else {
					desName = desName + document.getElementById("desgName" + j).value;
					desNameLocal = desNameLocal + document.getElementById("desgNameLocal" + j).value;
				}
			}

			/*
			 * alert(document.getElementById("desgName" + j).value.trim() ==
			 * ""); alert(document.getElementById("desgNameLocal" +
			 * j).value.trim() == "");
			 */

			if (flag == 1) {
				alert("please enter the mandatory fields");
				flag = 0;
				document.getElementById("errormsg").innerHTML = "";
			} else {

				if (isnotcomplete == false) {

					// alert("isnotcomplete:::: "+isnotcomplete);
					for ( var y = 0; y <= j; y++) {
						delflag = true;
						for ( var k = 0; k < n.length; k++) {
							newi = parseInt(n[k]);
							if (newi == y) {
								delflag = false;

							}

						}
						if (delflag == true) {

							for ( var m = 0; m < y; m++) {

								delflag = true;
								for ( var k = 0; k < n.length; k++) {
									newi = parseInt(n[k]);
									if (newi == m) {
										delflag = false;

									}

								}
								if (delflag == true) {

									if (document.getElementById("desgName" + y).value == document.getElementById("desgName" + m).value) {
										isnotcomplete = true;
									}
								}
							}

							if (isnotcomplete == true) {
								document.getElementById("errormsg").innerHTML = "*Error-Top Designation Name and Other's Designation Name must be unique";
								break;
							} else {
								for ( var m = 0; m < y; m++) {
									delflag = true;
									for ( var k = 0; k < n.length; k++) {
										newi = parseInt(n[k]);
										if (newi == m) {
											delflag = false;

										}

									}
									if (delflag == true) {

										if (document.getElementById("desgNameLocal" + y).value == document.getElementById("desgNameLocal" + m).value) {
											isnotcomplete = true;

										}
									}
								}

								if (isnotcomplete == true) {
									document.getElementById("errormsg").innerHTML = "*Error-Top Designation Name Local and Other's Designation Name Local must be unique";
									break;
								}
							}

						}

					}
				}

				/*  */

			}

		}
	}
	// alert(isnotcomplete);
	for ( var z = 1; z < i; z++) {
		if (document.getElementById("chkbx" + z) != null)
			document.getElementById("isMultiple").value += document.getElementById("chkbx" + z).checked + ",";
	}
	if (document.getElementById("DE") != null) {
		if (document.getElementById('deletedDesignation').value != "") {
			var itrCount = ((document.getElementById("DE").value.split("~")).length - 1)
					- (document.getElementById('deletedDesignation').value.split(",")).length;
		} else {
			var itrCount = ((document.getElementById("DE").value.split("~")).length - 1);
		}
		if (i >= itrCount) {
			for ( var a = itrCount; a < i; a++) {
				if (document.getElementById("desgName" + a) != null)
					if (temp == null)
						temp = document.getElementById("desgName" + a).value + "," + document.getElementById("desgNameLocal" + a).value + ","
								+ document.getElementById("chkbx" + a).checked + "~";
					else
						temp += document.getElementById("desgName" + a).value + "," + document.getElementById("desgNameLocal" + a).value + ","
								+ document.getElementById("chkbx" + a).checked + "~";
			}
			/*
			 * alert("sub:" + temp);
			 * alert(document.getElementById("modifiedDesignation").value);
			 */

			document.getElementById("modifiedDesignation").value = "";

			document.getElementById("modifiedDesignation").value = temp;

		}
	}

	if (isnotcomplete == false) {
		document.getElementById('lgtype').disabled = false;
		sub = true;

	}

	for ( var k = 0; k < desName.length; k++) {
		var char1 = desName.charAt(k);
		var cc = char1.charCodeAt(0);

		if ((cc >= 48) && (cc <= 58) || (cc >= 65) && (cc <= 90) || (cc >= 97) && (cc <= 122) || (cc == 32)) {

		} else {
			document.getElementById("errormsg").innerHTML = "Please use numeric,Alphabets and Space value only in Designation Name";
			return false;
		}
	}

	if (!validateSpecialCharacters(desNameLocal)) {
		document.getElementById("errormsg").innerHTML = "Desination Name in Local contain Invalid character";
		return false;
	}
	/*
	 * for ( var k = 0; k < desNameLocal.length; k++) { var char1 =
	 * desNameLocal.charAt(k); var cc = char1.charCodeAt(0);
	 * 
	 * if ((cc >= 48) && (cc <= 58) || (cc >= 65) && (cc <= 90) || (cc >= 97) &&
	 * (cc <= 122) || (cc == 32)) { } else { } }
	 */return sub;

	/* } */

}

function checkBoxValue() {
	// IMPLEMENTED IN DESIGNATION HIRERARCHY PAGES --- RAM
	validator();
	var temp = null;
	if (isnotcomplete == false) {
		for ( var z = 1; z < i; z++) {
			document.getElementById("isMultiple").value += document.getElementById("chkbx" + z).checked + ",";
		}
		if (document.getElementById("DE") != null) {
			if (document.getElementById('deletedDesignation').value != "") {
				var itrCount = ((document.getElementById("DE").value.split("~")).length - 1)
						- (document.getElementById('deletedDesignation').value.split(",")).length;
			} else {
				var itrCount = ((document.getElementById("DE").value.split("~")).length - 1);
			}
			if (i >= itrCount) {
				for ( var a = itrCount; a < i; a++) {
					if (temp == null)
						temp = document.getElementById("desgName" + a).value + "," + document.getElementById("desgNameLocal" + a).value + ","
								+ document.getElementById("chkbx" + a).checked + "~";
					else
						temp += document.getElementById("desgName" + a).value + "," + document.getElementById("desgNameLocal" + a).value + ","
								+ document.getElementById("chkbx" + a).checked + "~";
				}
				/*
				 * alert("check:" + temp);
				 * 
				 * alert(document.getElementById("modifiedDesignation").value);
				 */
				document.getElementById("modifiedDesignation").value = "";
				document.getElementById("modifiedDesignation").value = temp;
			}
		}

		document.getElementById('lgtype').disabled = false;
		document.forms['designationForm'].submit();
	}
}

function changeIt(divs, type)
// IMPLEMENTED IN DESIGNATION HIRERARCHY PAGES --- RAM
{
	var tmptextE = new Array();
	var tmptextL = new Array();
	var tempchk = new Array();
	var newdata;
	var delfun = "onclick='isLBReportingExisting(designationCode" + i + ".value," + i + ")'";
	var onblurfun = "onblur='isLBReportingExistingM(designationCode" + i + ".value," + i + ")'";
	if (dynstart == 0) {
		dynstart = i;
	}
	dynend = i;
	// alert("dynstart : " + dynstart + "and dynend : " + dynend);
	for ( var j = dynstart; j < dynend; j++) {
		if (document.getElementById("desgName" + j)) {
			if (document.getElementById("desgName" + j) != null && document.getElementById("desgNameLocal" + j) != null) {
				tmptextE[j] = document.getElementById("desgName" + j).value;
				tmptextL[j] = document.getElementById("desgNameLocal" + j).value;
				tempchk[j] = document.getElementById("chkbx" + j).checked;
			}
		}
	}

	if (type == 'dynamic') {
		delfun = "onclick='isLBReportingExistingDynamic(designationCode" + i + ".value," + i + ")'";
		onblurfun = "";
	}

	newdata = "<div id=div"
			+ i
			+ ">"
			+ "<td><input name='designationCode' id='designationCode"
			+ i
			+ "' type='hidden' /><input type='text' name='desgName'  onfocus='setPreName("
			+ i
			+ ")' "
			+ onblurfun
			+ " maxlength='50' id='desgName"
			+ i
			+ "' class='frmfield' style='width:200px'/></td>&nbsp;&nbsp;"
			+ "<td><label id='lblreport"
			+ i
			+ "' style='color:red'/><input type='text' name='desgNameLocal'  maxlength='50' id='desgNameLocal"
			+ i
			+ "' class='frmfield' style='width:200px'/></td>&nbsp;"
			+ "<td align='center'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='checkbox' name='isMultiple1' id='chkbx"
			+ i
			+ "'/></td>"
			+ "<td align='center'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+ "<input type='button' value='Delete' " + delfun + " /></td></div>";
	divs.innerHTML += newdata;
	for ( var j = dynstart; j < dynend; j++) {
		if (document.getElementById("desgName" + j)) {
			if (document.getElementById("desgName" + j) != null && document.getElementById("desgNameLocal" + j) != null) {
				document.getElementById("desgName" + j).value = tmptextE[j];
				document.getElementById("desgNameLocal" + j).value = tmptextL[j];
				document.getElementById("chkbx" + j).checked = tempchk[j];
			}
		}
	}
	i++;
}

function changeItOrgan(divs, type) {

	var tmptextE = new Array();
	var tmptextL = new Array();
	var tempchk = new Array();
	var tempchk1 = new Array();
	var delfun = null;
	var onblurfun = null;
	var delfun = "onclick='isLBReportingExistingOrgan(designationCode" + i + ".value," + i + ")'";
	var onblurfun = "onblur='isLBReportingExistingMOrgan(designationCode" + i + ".value," + i + ")'";
	var newdata;
	if (dynstart == 0) {
		dynstart = i;
	}

	dynend = i;
	// alert("dynstart : " + dynstart + "and dynend : " + dynend);
	for ( var j = dynstart; j < dynend; j++) {
		if (document.getElementById("desgName" + j)) {
			if (document.getElementById("desgName" + j) != null && document.getElementById("desgNameLocal" + j) != null) {
				tmptextE[j] = document.getElementById("desgName" + j).value;
				tmptextL[j] = document.getElementById("desgNameLocal" + j).value;
				tempchk[j] = document.getElementById("chkbx" + j).checked;
				tempchk1[j] = document.getElementById("contractPer" + j).checked;
			}
		}
	}

	if (type == 'dynamic') {
		document.getElementById('locatedAtLevel').disabled = false;
	}

	if (type == 'dynamic') {
		delfun = "onclick='isLBReportingExistingDynamic(designationCode" + i + ".value," + i + ")'";
		onblurfun = "";
	}

	newdata = "<div id=div"
			+ i
			+ ">"
			+ "<td><input name='designationCode' id='designationCode"
			+ i
			+ "' type='hidden' /><input type='text' name='desgName'  onfocus='setPreName("
			+ i
			+ ")' "
			+ onblurfun
			+ " maxlength='50' id='desgName"
			+ i
			+ "' class='frmfield' style='width:200px'/></td>&nbsp;&nbsp;"
			+ "<td><label id='lblreport"
			+ i
			+ "' style='color:red'/><input type='text' name='desgNameLocal'  maxlength='50' id='desgNameLocal"
			+ i
			+ "' class='frmfield' style='width:200px'/></td>&nbsp;"
			+ "<td align='center'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='checkbox' name='isMultiple1' id='chkbx"
			+ i
			+ "'/></td>"
			+ "<td align='center'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='checkbox' name='isContractPer1' id='contractPer"
			+ i + "'/></td>" + "<td align='center'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+ "<input type='button' value='Delete' " + delfun + " /></td></div>";

	divs.innerHTML += newdata;
	for ( var j = dynstart; j < dynend; j++) {
		if (document.getElementById("desgName" + j)) {
			if (document.getElementById("desgName" + j) != null && document.getElementById("desgNameLocal" + j) != null) {
				document.getElementById("desgName" + j).value = tmptextE[j];
				document.getElementById("desgNameLocal" + j).value = tmptextL[j];
				document.getElementById("chkbx" + j).checked = tempchk[j];
				document.getElementById("contractPer" + j).checked = tempchk1[j];
			}
		}
	}
	i++;
}

function getDesig() {
	var txtN = 1;

	dynamicDiv.innerHTML = "";
	if (document.getElementById("DE").value != "") {
		document.getElementById('lgtype').disabled = 'true';
		temptxt = document.getElementById("DE").value.split("~");
		temptxt2 = document.getElementById("DL").value.split("~");

		tempDL = temptxt2[0].split(",");
		tempDE = temptxt[0].split(",");
		document.getElementById("desgName0").value = tempDE[1];
		document.getElementById("desgNameLocal0").value = tempDL[0];
		document.getElementById("designationCode0").value = tempDL[1];
		for ( var k = 1; k < temptxt.length - 1; k++) {
			tempDE = temptxt[k].split(",");
			tempDL = temptxt2[k].split(",");
			if (k > 1) {
				i = k;
				changeIt(dynamicDiv, '');
			}
			if (tempDE[0] == "true") {
				document.getElementById("chkbx" + txtN).checked = "true";
			}
			document.getElementById("desgName" + txtN).value = tempDE[1];
			document.getElementById("designationCode" + txtN).value = tempDL[1];
			// alert(document.getElementById("designationCode" + txtN).value +
			// ":" + txtN);
			document.getElementById("desgNameLocal" + txtN).value = tempDL[0];
			txtN++;
		}
	}
	document.getElementById('Back').style.visibility = 'visible';
	// document.getElementById("DE").value="";
	// document.getElementById("DL").value="";
}

function refresh() {
	javascript: location.reload(true);
}

function getOrganizationDesignation(orgCode, orgLevel) {
	if (orgLevel != 0) {
		lgdDwrDesignationService.getOrganizationDesignationDetails(orgCode, orgLevel, {
			callback : handleOrganizationDetailSuccess,
			errorHandler : handleOrganizationdetailError
		});
	}
}

function handleOrganizationDetailSuccess(data) {
	if (data != null) {
		document.getElementById("opeation").value = "U";
		document.getElementById("designationDiv").style.visibility = 'hidden';
		dwr.util.removeAllRows("Designationrows");
		dwr.util.addRows("Designationrows", data, [ col11, col12 ], {
			escapeHtml : false
		});
		getOrganizationDesig();
	} else {
		document.getElementById("opeation").value = "I";
	}
}

function col11(data) {

	var tx = document.createElement("input");
	tx.value = "";
	tx.type = "hidden";
	tx.id = "DE";
	tx.value = data.designationName;
	return tx;
}
function col12(data) {

	var tx = document.createElement("input");
	tx.value = "";
	tx.type = "hidden";
	tx.id = "DL";
	// alert(data.designationNameLocal);
	tx.value = data.designationNameLocal;
	return tx;
}

function getOrganizationDesig() {
	var txtN = i - 1;
	dynamicDiv.innerHTML = "";
	if (document.getElementById("DE").value != "") {
		document.getElementById('locatedAtLevel').disabled = 'true';
		temptxt = document.getElementById("DE").value.split("~");
		temptxt2 = document.getElementById("DL").value.split("~");

		tempDL = temptxt2[0].split(",");
		tempDE = temptxt[0].split(",");
		document.getElementById("desgName0").value = tempDE[2];
		document.getElementById("desgNameLocal0").value = tempDL[0];
		document.getElementById("designationCode0").value = tempDL[1];
		for ( var k = 1; k < temptxt.length - 1; k++) {

			tempDE = temptxt[k].split(",");
			tempDL = temptxt2[k].split(",");
			if (k > 1) {
				changeItOrgan(dynamicDiv, '');
			}
			if (tempDE[0] == "true") {
				document.getElementById("chkbx" + txtN).checked = "true";
			}
			if (tempDE[1] == "true") {
				document.getElementById("contractPer" + txtN).checked = "true";
			}
			document.getElementById("desgName" + txtN).value = tempDE[2];
			document.getElementById("designationCode" + txtN).value = tempDL[1];
			document.getElementById("desgNameLocal" + txtN).value = tempDL[0];

			txtN++;
		}
	}
	document.getElementById('Back').style.visibility = 'visible';
	// document.getElementById("DE").value="";
	// document.getElementById("DL").value="";
}

function handleOrganizationdetailError() {
	// Show a popup message
}

function getDesignation(isElected, id) {
	if (id != 0) {
		lgdDwrDesignationService.getDesignationDetails(isElected, id, {
			callback : handleSuccess,
			errorHandler : handleError
		});
	}
}
function handleSuccess(data) {
	if (data != null) {
		document.getElementById("opeation").value = "U";
		document.getElementById("designationDiv").style.visibility = 'hidden';
		dwr.util.removeAllRows("Designationrows");
		dwr.util.addRows("Designationrows", data, [ col1, col2 ], {
			escapeHtml : false
		});
		getDesig();
	} else {
		document.getElementById("opeation").value = "I";
	}

}
function col1(data) {
	var tx = document.createElement("input");
	tx.value = "";
	tx.type = "hidden";
	tx.id = "DE";
	tx.value = data.designationName;
	;
	return tx;
}
function col2(data) {
	var tx = document.createElement("input");
	tx.value = "";
	tx.type = "hidden";
	tx.id = "DL";
	// alert(data.designationNameLocal);

	tx.value = data.designationNameLocal;
	return tx;
}

function handleError() {
	// Show a popup message
}

function isLBReportingExistingOrgan(designationName, id) {
	divId = id;
	lgdDwrDesignationService.isOrganizationReportingExist(designationName, {
		callback : reportingExistOrgan,
		errorHandler : reportingExistOrganError
	});
}

function reportingExistOrgan(data) {
	/* alert(data); */
	if (data != null) {

		alert("This designation is being used in organization reporting. To delete this, kindly remove it from reporting first.");
	} else {

		try {
			temptxt = document.getElementById("DL").value.split("~");

			for ( var k = 0; k < temptxt.length - 1; k++) {
				if (document.getElementById('desgNameLocal' + divId).value == (temptxt[k].split(","))[0]) {
					document.getElementById('deletedDesignation').value = (temptxt[k].split(","))[1] + ",";
					designationCode = parseInt(document.getElementById('deletedDesignation').value);
					break;
				}
			}

			lgdDwrDesignationService.checkOrganDesignationDelete(designationCode, {
				callback : checkReportOrgan,
				errorHandler : problemReportOrgan
			});
		} catch (err) {
			isLBReportingExistingDynamic('', divId);
		} finally {

		}
	}

}
function reportingExistOrganError() {

	try {
		temptxt = document.getElementById("DL").value.split("~");

		for ( var k = 0; k < temptxt.length - 1; k++) {
			if (document.getElementById('desgNameLocal' + divId).value == (temptxt[k].split(","))[0]) {
				document.getElementById('deletedDesignation').value = (temptxt[k].split(","))[1] + ",";
				designationCode = parseInt(document.getElementById('deletedDesignation').value);
				break;
			}
		}

		lgdDwrDesignationService.checkOrganDesignationDelete(designationCode, {
			callback : checkReportOrgan,
			errorHandler : problemReportOrgan
		});
	} catch (err) {
		isLBReportingExistingDynamic('', divId);
	} finally {

	}
}

/*
 * function isLBReportingExistingOrgan(designationName, id) { divId = id; try {
 * temptxt = document.getElementById("DL").value.split("~");
 * 
 * for ( var k = 0; k < temptxt.length - 1; k++) { if
 * (document.getElementById('desgNameLocal' + divId).value ==
 * (temptxt[k].split(","))[0]) {
 * document.getElementById('deletedDesignation').value =
 * (temptxt[k].split(","))[1] + ","; designationCode =
 * parseInt(document.getElementById('deletedDesignation').value); break; } }
 * 
 * lgdDwrDesignationService.checkOrganDesignationDelete(designationCode, {
 * callback : checkReportOrgan, errorHandler : problemReportOrgan }); } catch
 * (err) { isLBReportingExistingDynamic('', divId); } finally { } }
 */

function checkReportOrgan(data) {
	var delflag;
	if (data == true) {
		delflag = data;
		if (delflag) {
			document.getElementById('div' + divId).parentNode.removeChild(document.getElementById('div' + divId));
			var del = document.getElementById('bindex').value; // var
			del = del + divId + ",";
			document.getElementById('bindex').value = del;
			i;
			divId = 0;
		}
	}
	return false;
}

function problemReportOrgan() {
	alert("operation denied");
}

function isLBReportingExisting(designationName, id) {
	/*
	 * alert(document.getElementById("designationCode" + id).value);
	 * alert(document.getElementById("designationCode0").value);
	 * alert(document.getElementById("designationCode2").value);
	 */

	divId = id;
	lgdDwrDesignationService.isLBReportingExist(designationName, {
		callback : reportingExist,
		errorHandler : reportingExistError
	});
}
function reportingExist(data) {
	/* alert(data); */
	if (data != null) {

		alert("This designation is being used in reporting. To delete this, kindly remove it from reporting first.");
	} else {

		try {
			temptxt = document.getElementById("DL").value.split("~");

			for ( var k = 0; k < temptxt.length - 1; k++) {
				if (document.getElementById('desgNameLocal' + divId).value == (temptxt[k].split(","))[0]) {
					document.getElementById('deletedDesignation').value = (temptxt[k].split(","))[1] + ",";
					designationCode = parseInt(document.getElementById('deletedDesignation').value);

					break;
				}
			}

			lgdDwrDesignationService.checkDesignationPesDelete(designationCode, {
				callback : checkReport,
				errorHandler : problemReport
			});
		} catch (err) {
			isLBReportingExistingDynamic('', divId);
		} finally {

		}
	}

}
function reportingExistError() {

	try {
		temptxt = document.getElementById("DL").value.split("~");

		for ( var k = 0; k < temptxt.length - 1; k++) {
			if (document.getElementById('desgNameLocal' + divId).value == (temptxt[k].split(","))[0]) {
				document.getElementById('deletedDesignation').value = (temptxt[k].split(","))[1] + ",";
				designationCode = parseInt(document.getElementById('deletedDesignation').value);

				break;
			}
		}

		lgdDwrDesignationService.checkDesignationPesDelete(designationCode, {
			callback : checkReport,
			errorHandler : problemReport
		});
	} catch (err) {
		isLBReportingExistingDynamic('', divId);
	} finally {

	}
}

function isLBReportingExistingDynamic(designationName, id) {

	document.getElementById('div' + id).parentNode.removeChild(document.getElementById('div' + id));
	var del = document.getElementById('bindex').value; // var
	del = del + id + ",";
	document.getElementById('bindex').value = del;
}

function checkReport(data) {
	var delflag;
	/* alert("Data " + data); */
	if (data == true) {
		delflag = data;
		if (delflag) {
			document.getElementById('div' + divId).parentNode.removeChild(document.getElementById('div' + divId));
			var del = document.getElementById('bindex').value; // var
			del = del + divId + ",";
			document.getElementById('bindex').value = del;
			i;
			divId = 0;
		}
	} else {
		alert("This designation is being used by other application.so delete operation not perform");
	}
	return false;
}

function problemReport() {
	alert("operation denied");
}

function clr() {
	var LBType = document.getElementById('lgtype').value;
	document.getElementById("designationForm").reset();
	document.getElementById('lgtype').value = LBType;
}

function setPreName(id) {
	tempName = document.getElementById('desgName' + id).value;
}

function isLBReportingExistingMOrgan(designationName, id) {
	// alert(document.getElementById("designationCode0").value);
	designationCode = document.getElementById('designationCode' + id).value;

	index = id;
	if (tempName != document.getElementById('desgName' + id).value) {
		lgdDwrDesignationService.isLBReportingOrganExist(designationCode, {
			callback : reportingExistMOrgan,
			errorHandler : reportingExistErrorMOrgan
		});
	}
}

function reportingExistErrorMOrgan() {
	alert("operation denied");
}

function reportingExistMOrgan(data) {
	if (data != null) {
		alert("This Organisation designation is being used in reporting. To modify this, kindly remove it from reporting first.");
		document.getElementById('desgName' + index).value = tempName;
		/* document.getElementById('desgName' + index).focus(); */
	}
}

function isLBReportingExistingM(designationName, id) {
	// alert(document.getElementById("designationCode0").value);
	designationCode = document.getElementById('designationCode' + id).value;

	index = id;
	if (tempName != document.getElementById('desgName' + id).value) {
		lgdDwrDesignationService.isLBReportingExist(designationCode, {
			callback : reportingExistM,
			errorHandler : reportingExistErrorM
		});
	}

}
function reportingExistM(data) {
	if (data != null) {
		alert("This designation is being used in reporting. To modify this, kindly remove it from reporting first.");
		document.getElementById('desgName' + index).value = tempName;
		/* document.getElementById('desgName' + index).focus(); */
	}
}
function reportingExistErrorM() {

	/*  */

	try {

		lgdDwrDesignationService.checkDesignationPesDeleteM(designationCode, {
			callback : checkReportM,
			errorHandler : problemReportM
		});
	} catch (err) {

	} finally {

	}

}

function checkReportM(data) {
	var delflag;
	if (data != null) {
		delflag = data;

		if (delflag) {

		} else {
			alert("This designation is being used by other application.so modify operation not perform");
			document.getElementById('desgName' + index).value = tempName;
			/* document.getElementById('desgName' + index).focus(); */
		}
	}
}

function problemReportM() {
	alert("operation denied");
}
