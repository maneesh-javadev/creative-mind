/*@Author - Ram Krishna Chauhan */
var i = 4;
var allchk = 0;
var dynstart = 0;
var dynend = 0;
var dropdown_pos = 1;
var valueText = 'designationCode';
var nameText = 'designationName';
var maxDesg = 0;
var l = 1;
var isnotcomplete = false;

function dropdown(divs) {
	var tmpDesig = new Array();
	var tmpReport = new Array();
	if (dynstart == 0) {
		dynstart = i;
	}
	dynend = i;
	if (l <= maxDesg - 2) {
		for ( var j = dynstart; j < dynend; j++) {
			if (document.getElementById("ddDesignation" + j) != null) {
				tmpDesig[j] = document.getElementById("ddDesignation" + j).value;
			}
		}

		divs.innerHTML += "<div id=div" + i + ">" + "<table><tr><td><select path='designation' name='designation' id='ddDesignation" + i
				+ "' class='frmsfield' style='width:150px'><option value=''>Select</option></select></td>"
				+ "<td><select path='reportTo' name='reportTo' id='ddDesignation" + (i + 1)
				+ "' class='frmsfield' style='width:150px'><option value=''>Select</option></select></td>"
				+ "<td><input type='image' src='images/delete.png' onclick='div" + i + ".parentNode.removeChild(div" + i + "); l-=1;callDelete(" + i
				+ ");'/></td></tr></table></div>";

		dwr.util.addOptions('ddDesignation' + i, globaldata, valueText, nameText);
		dwr.util.addOptions('ddDesignation' + (i + 1), globaldataR, valueText, nameText);
		for ( var j = dynstart; j < dynend; j++) {
			if (document.getElementById("ddDesignation" + j) != null) {
				document.getElementById("ddDesignation" + j).value = tmpDesig[j];
			}
		}
		i += 2;
		l += 1;
	} else {
		alert("no designation left for assign");
	}
}

function callDelete(z) {

	document.getElementById("bindex").value += z + "," + (z + 1) + ",";
}

function setMaxDesg() {
	maxDesg = 0;
	maxDesg = document.getElementById("ddDesignation2").length;
}

// @Author - Sarvapriya Malhotra
function updatelgTypeCode() {
	// 1st we refresh dropdown
	for ( var i = document.getElementById('lgTypeCode').length; i >= 0; i--) {
		document.getElementById('lgTypeCode').selectedIndex = i;
		removeSelectedValue('lgTypeCode');
	}
	/*
	 * if (document.getElementById('tierSetupCode').value.split('%')[1]=='V') {
	 * for (var i=1; i<document.getElementById('tierSetupCode').length;i++)
	 * $("#lgTypeCode").append("<option value=" +
	 * document.getElementById('tierSetupCode')[i].value +
	 * ">"+document.getElementById('tierSetupCode')[i].text+"</option>"); } if
	 * (document.getElementById('tierSetupCode').value.split('%')[1]=='I') { for
	 * (var i=1; i<document.getElementById('tierSetupCode').length;i++) { if
	 * (document.getElementById('tierSetupCode')[i].value.split('%')[1]=='D')
	 * $("#lgTypeCode").append("<option value=" +
	 * document.getElementById('tierSetupCode')[i].value +
	 * ">"+document.getElementById('tierSetupCode')[i].text+"</option>"); } }
	 */
}

function removeSelectedValue(val) {
	var elSel = document.getElementById(val);
	var i;
	for (i = elSel.length - 1; i >= 1; i--) {
		if (elSel.options[i].selected) {
			elSel.remove(i);
		}
	}
}

/*
 * function getLBReporting(tierSetupCode) {
 * lgdDwrDesignationService.getLBReportingDetail(tierSetupCode, { callback :
 * handleLBReportingSuccess, errorHandler : handleLBReportingError });
 * 
 * function handleLBReportingSuccess(data) { var fieldId = 'txLBReporting';
 * document.getElementById(fieldId).value = ""; dwr.util.setValue(fieldId,
 * data.designationName); setTimeout("getLBReportingDetail()", 200); } function
 * handleLBReportingError() { // Show a popup message } }
 */

function getLBReportingDetail() {
	setMaxDesg();
	var txtN = i;
	dynamicDiv.innerHTML = "";
	var temptxt = null;
	var tempDesig = null;

	if (document.getElementById("txLBReporting").value != "") {
		document.getElementById('tierSetupCode').disabled = 'true';
		temptxt = document.getElementById("txLBReporting").value.split("~");
		tempDesig = temptxt[0].split(",");
		document.getElementById("ddDesignation0").value = tempDesig[0];
		if (tempDesig[1] != null)
			document.getElementById("ddDesignation1").value = tempDesig[1];

		for ( var k = 1; k < temptxt.length - 1; k++) {
			tempDesig = temptxt[k].split(",");
			if (k == 1) {
				document.getElementById("ddDesignation2").value = tempDesig[0];
				document.getElementById("ddDesignation3").value = tempDesig[1];
			} else {
				dropdown(dynamicDiv);
				setTimeout("", 200);
				document.getElementById("ddDesignation" + txtN).value = tempDesig[0];
				document.getElementById("ddDesignation" + (txtN + 1)).value = tempDesig[1];
				txtN = txtN + 2;
			}
		}
		document.getElementById('Back').style.visibility = 'visible';
	}
	document.getElementById("txLBReporting").value = "";
}

function refresh() {
	javascript: location.reload(true);
}

function validateLocalBodyReporting() {
	isnotcomplete = false;
	var del = document.getElementById("bindex").value;
	var newi;
	var delindex = del.split(",");
	var delflag = true;
	if (document.getElementById("tierSetupCode").value == "") {
		$("#tierSetupCode").addClass("error_fld");
		isnotcomplete = true;
	}
	for ( var j = 2; j < i; j++) {
		delflag = true;
		for ( var x = 0; x < delindex.length; x++) {
			newi = parseInt(delindex[x]);
			if (j == newi) {
				delflag = false;
				break;
			}
		}
		if (delflag == true) {
			if (document.getElementById("ddDesignation" + j).value == "") {
				$("#ddDesignation" + j).addClass("error_fld");
				isnotcomplete = true;
			}
		}
	}
	// To remove * if user has entered values
	if (document.getElementById("tierSetupCode").value != "")
		$("#tierSetupCode").removeClass("error_fld");
	for ( var j = 2; j < i; j++) {

		delflag = true;
		for ( var x = 0; x < delindex.length; x++) {
			newi = parseInt(delindex[x]);
			if (j == newi) {
				delflag = false;
				break;
			}
		}
		if (delflag == true) {
			if (document.getElementById("ddDesignation" + j).value != "") {
				$("#ddDesignation" + j).removeClass("error_fld");
				isnotcomplete = false;
			}
		}
	}
}

// ------------------New Code

var listbox;
var popList;
var trec;
var updateData;
var selTierSetupCode;
var parntTierSetupCode;
var parentDesCode = null;
var delID;
function getOfficalDesignation(tierSetupCode) {
	if (tierSetupCode != "0") {
		selTierSetupCode = tierSetupCode;
		lgdDesignationDwr.getLBReportingDetail(tierSetupCode, {
			async : true,
			callback : handleLBReportingSuccess,
			errorHandler : handleLBReportingError
		});
	}

}

function handleLBReportingSuccess(data) {
	updateData = data;
	lgdDesignationDwr.getOfficialDesignation(selTierSetupCode, {
		callback : handleOfficialDesignationSuccess,
		errorHandler : handleOfficialDesignationError
	});

}
function handleLBReportingError() {
	// Show a popup message
}

function handleOfficialDesignationSuccess(data) {
	var valueText = 'designationCode';
	var nameText = 'designationName';
	var globaldata = data;
	popList = data;
	listbox = new Array(data.length);
	for ( var m = 0; m < data.length; m++) {
		listbox[m] = new Array(2);
	}
	var listData = new Array();

	var y = 0;
	var table, tr, td1, td2, td3, inp1, inp2, select, div1;

	for ( var r = 1; r < trec; r++) {
		document.getElementById("ReportTable").removeChild(document.getElementById("row" + r));
	}

	for ( var x = 0; x < globaldata.length; x++) {

		var labelDetails = globaldata[x];

		if (labelDetails.isTopDesignation) {
			listData[y] = labelDetails;
			y++;
			document.getElementById("ddDesignationName0").value = labelDetails.designationName;
			document.getElementById("designationCode0").value = labelDetails.designationCode;
		} else {

			
			
			
			 var divTemplate = $("#ReportTable");
			 
			 var othNameEngDiv = $("<div/>");
			 othNameEngDiv.addClass("form-group col-sm-4");
			 
			 var othNameEngInput = $("<input/>");
			 othNameEngInput.attr("id", "ddDesignationName" + x);
			 othNameEngInput.attr("style", "width:200px");
			 othNameEngInput.attr("class", "form-control");
			 othNameEngInput.attr("readonly", "readonly");
			 othNameEngInput.attr("value", labelDetails.designationName);
			 
			 
			 othNameEngDiv.append(othNameEngInput);
			 
			 
			 var othCodeInput = $("<input/>");
			 othCodeInput.attr("Type", "hidden");
			 othCodeInput.attr("path", "desig");
			 othCodeInput.attr("id", "designationCode" + x);
			 othCodeInput.attr("Value", labelDetails.designationCode);
			 
			 othNameEngDiv.append(othCodeInput);
			 
			 var othRpttoDiv = $("<div/>");
			 othRpttoDiv.addClass("form-group col-sm-4");
			 
			 
			 var othRpttoSelect = $("<select/>");
			 othRpttoSelect.attr("path", "report");
			 othRpttoSelect.attr("id", "RdesignationCode" + x);
			 /*othRpttoSelect.attr("style", "width:200px");*/
			 othRpttoSelect.attr("onchange", "nextList(this.value," + x + ");clearError(" + x + ");");
			 othRpttoSelect.attr("class", "form-control");
			 
			 othRpttoDiv.append(othRpttoSelect);
	
			// othCodeInput.setAttribute("class", "form-control");
			 
			 
			 var othErrorDiv = $("<div/>");
			 othErrorDiv.addClass("form-group col-sm-4");
			 
			 
			 var ErrorDiv = $("<div/>");
			 ErrorDiv.attr("id", "designationCode" + x + "_error");
			 
			
			 othErrorDiv.append(ErrorDiv);
			 
			 
			 divTemplate.append(othNameEngDiv);
			 divTemplate.append(othRpttoDiv);
			 divTemplate.append(othErrorDiv);
			 
			
			if (x != 1) {
				listData[y] = labelDetails;
				y++;
			}

		}

	}
	trec = x;

	if (x >= 1) {
		dwr.util.removeAllOptions('RdesignationCode1');
		var dataq = [ {
			name : "Select"
		} ];

		/*
		 * var firstRow = new Array(); firstRow.designationName = "None";
		 * firstRow.designationCode = 0; firstRow.designationNameLocal = null;
		 * firstRow.isTopDesignation = false;
		 * dwr.util.addOptions('RdesignationCode1', dataq, "name");
		 * dwr.util.setValues(firstRow, { 'RdesignationCode1' : 'person' });
		 */
		/*
		 * dwr.util.addOptions('RdesignationCode1', firstRow, valueText,
		 * nameText);
		 */

		dwr.util.addOptions('RdesignationCode1', dataq, "name");
		var x = document.getElementById("RdesignationCode1");
		var optn = document.createElement("OPTION");
		optn.text = "None";
		optn.value = 0;
		x.options.add(optn);
		dwr.util.addOptions('RdesignationCode1', listData, valueText, nameText);

	}

	var topreportTo = 0;
	if (updateData != null) {
		var temp = updateData.designationName;
		if (temp != null) {
			var tempRepotTo = temp.split("~");

			topreportTo = tempRepotTo[0];
			parentDesCode = tempRepotTo[0];

			for ( var s = 1; s < (tempRepotTo.length - 1); s++) {
				document.getElementById("RdesignationCode" + s).value = tempRepotTo[s];
				nextList(tempRepotTo[s], s);
			}
		}

	}

	if (topreportTo != "null") {
		lgdDwrDesignationService.getParentTierSetupCodeforDes(topreportTo, {
			callback : getParentTierSetupCodeforDesSuccess,
			errorHandler : handleParentListError
		});
	}

}

function getParentTierSetupCodeforDesSuccess(data) {
	if (data != null) {
		if (data.designationName != null) {
			var temptierSetup = data.designationName;
			parntTierSetupCode = temptierSetup;
			document.getElementById("lgTypeCode").value = temptierSetup;
			getDesignation1(temptierSetup);
		}

	}
}

function nextList(code, id) {
	var listData = new Array();
	var find = false;
	var matcharray = new Array();
	var listArray = new Array();
	var m = 0;
	var p = 0;
	if (trec > id + 1) {

		listbox[id - 1][0] = code;
		listbox[id - 1][1] = document.getElementById("designationCode" + id).value;
		var ddcode = document.getElementById("designationCode" + parseInt(id + 1)).value;
		matcharray.push(ddcode);
		for ( var d = 0; d < matcharray.length; d++) {
			for ( var z = 0; z < popList.length; z++) {
				var labelDetails = popList[z];
				/*
				 * if (labelDetails.designationCode != code) listData[m] =
				 * labelDetails;
				 */

				for ( var k = 0; k < (id + p); k++) {

					if (listbox[k][0] == undefined) {
						p++;
					}
					if (listbox[k][0] == matcharray[d])

					{
						if (labelDetails.designationCode == listbox[k][1]) {
							matcharray.push(labelDetails.designationCode);
							listArray.push(labelDetails.designationCode);

							break;

						}
					}
				}

				/*
				 * if (find == false && labelDetails.designationCode != ddcode) {
				 * listData[m] = labelDetails; m++; }
				 */
			}
		}
		for ( var z = 0; z < popList.length; z++) {
			var labelDetails = popList[z];
			find = false;
			for ( var u = 0; u < listArray.length; u++) {
				if (labelDetails.designationCode == listArray[u]) {
					find = true;
				}

			}

			if (find == false && labelDetails.designationCode != ddcode) {
				listData[m] = labelDetails;
				m++;
			}

		}

		for ( var t = parseInt(id + 1); t < popList.length; t++) {
			dwr.util.removeAllOptions(('RdesignationCode' + t));
		}

		var dataq = [ {
			name : "Select"
		} ];
		dwr.util.addOptions(('RdesignationCode' + parseInt(id + 1)), dataq, "name");
		var x = document.getElementById(('RdesignationCode' + parseInt(id + 1)));
		var optn = document.createElement("OPTION");
		optn.text = "None";
		optn.value = 0;
		x.options.add(optn);
		dwr.util.addOptions(('RdesignationCode' + parseInt(id + 1)), listData, valueText, nameText);
	}

}

function handleOfficialDesignationError() {
	alert("No Data found");
}
function getData() {
	document.forms['designationReportingForm'].action = "create_designation_reporting";
	document.forms['designationReportingForm'].submit();

}

function hideAll() {
	$("#tierSetupCode_error").hide();
	$("#designationCode0_error").hide();
	$("#lgTypeCode_error").hide();
}
function populate() {

	document.getElementById("designation").value = "";
	document.getElementById("reportTo").value = "";
	// alert(":" + document.getElementById("tierSetupCode").value + ":" + trec);
	for ( var h = 1; h < trec; h++) {
		document.getElementById("designationCode" + h + "_error").innerHTML = "";
	}
	var error = false;
	if (document.getElementById("tierSetupCode").value == "") {
		$("#tierSetupCode_error").show();
		error = true;
	}

	if (document.getElementById('RdesignationCode0').disabled == false) {
		if (document.getElementById('RdesignationCode0').value == "" || document.getElementById('RdesignationCode0').value == "Select") {
			$("#designationCode0_error").show();
			error = true;
		}

		if (document.getElementById("lgTypeCode").value == "") {
			$("#lgTypeCode_error").show();
			error = true;
		}

	}
	for ( var h = 1; h < trec; h++) {

		if (document.getElementById("RdesignationCode" + h).value == "" || document.getElementById("RdesignationCode" + h).value == "Select") {

			document.getElementById("designationCode" + h + "_error").innerHTML = "<font color='red'><br>Please Select Designation </font>";
			error = true;
		}
	}

	if (error == true) {
		return false;
	} else {
		for ( var k = 0; k < trec; k++) {
			document.getElementById("designation").value += document.getElementById("designationCode" + k).value + ",";
			document.getElementById("reportTo").value += document.getElementById("RdesignationCode" + k).value + ",";
		}
		document.getElementById('tierSetupCode').disabled = false;
		return true;
	}

	/*
	 * validateLocalBodyReporting(); var del =
	 * document.getElementById("bindex").value; var newi; var delindex =
	 * del.split(","); var delflag = true; if (isnotcomplete == false) {
	 * document.getElementById('tierSetupCode').disabled = false;
	 * document.getElementById("designation").value =
	 * document.getElementById("ddDesignation0").value + ",";
	 * document.getElementById("reportTo").value =
	 * document.getElementById("ddDesignation1").value + ","; try { for ( var k =
	 * 2; k < i; k++) { delflag = true; for ( var x = 0; x < delindex.length;
	 * x++) { newi = parseInt(delindex[x]); if (k == newi) { delflag = false;
	 * break; } } if (delflag == true) { if (k % 2 == 0)
	 * document.getElementById("designation").value +=
	 * document.getElementById("designationCode" + k).value + ","; else
	 * document.getElementById("reportTo").value +=
	 * document.getElementById("RdesignationCode" + k).value + ","; } } }
	 * finally { document.forms['designationReportingForm'].submit(); } }
	 */
}

function getLocalBodyParentList(tierSetupCode) {
	if (tierSetupCode != "0") {
		lgdDesignationDwr.getLocalBodyTierSetupParentList(tierSetupCode, {
			async : true,
			callback : handleParentListSuccess,
			errorHandler : handleParentListError
		});
	}

}
function handleParentListSuccess(data) {
	// Assigns data to result id
	var valueText = 'designationCode';
	var nameText = 'designationName';
	var fieldId = 'lgTypeCode';

	var fieldId1 = 'RdesignationCode0';
	dwr.util.removeAllOptions(fieldId1);
	dwr.util.removeAllOptions(fieldId);

	var dataq = [ {
		name : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "name");
	/* dwr.util.addOptions(fieldId1, dataq, "name"); */
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}
function handleParentListError() {
	alert("No data found!");
}

function getParentTierSetupList(tierSetupCode) {

	document.getElementById('tierSetupCode').disabled = true;
	if (tierSetupCode != "0") {
		lgdDwrDesignationService.getParentTierSetupList(tierSetupCode, {
			async : true,
			callback : handleParentTierSetUpListSuccess,
			errorHandler : handleParentTierSetUpListError
		});
	}

}

function handleParentTierSetUpListSuccess(data) {
	if (data) {
		document.getElementById('RdesignationCode0').disabled = true;
		document.getElementById('lgTypeCode').disabled = true;
	} else {
		document.getElementById('RdesignationCode0').disabled = false;
		document.getElementById('lgTypeCode').disabled = false;
	}

	// cb20.readOnly="readOnly";
	/*
	 * var valueText='designationCode'; var nameText='designationName'; var
	 * fieldId = 'lgTypeCode';
	 * 
	 * var fieldId1 = 'ddDesignation1'; dwr.util.removeAllOptions(fieldId1);
	 * dwr.util.removeAllOptions(fieldId); var dataq = [ {name:"Select"} ];
	 * dwr.util.addOptions(fieldId, dataq, "name");
	 * dwr.util.addOptions(fieldId1, dataq, "name");
	 * dwr.util.addOptions(fieldId, data,valueText,nameText);
	 */
}
function handleParentTierSetUpListError() {
	alert("No data found!");
}

function getDesignation1(id) {
	if (id != "0" && id != "Select") {
		lgdDesignationDwr.getDesignationReporting(id, {
			async : true,
			callback : handleDesigSuccess1,
			errorHandler : handleParentTierSetUpListError
		});
	} else {
		var fieldId1 = 'RdesignationCode0';
		dwr.util.removeAllOptions(fieldId1);
		document.getElementById('lgTypeCode').value = "Select";
	}
}

function handleDesigSuccess1(data) {
	// alert(document.getElementById('RdesignationCode0').value);
	var valueText = 'designationCode';
	var nameText = 'designationName';

	var fieldId1 = 'RdesignationCode0';
	dwr.util.removeAllOptions(fieldId1);
	var dataq = [ {
		name : "Select"
	} ];

	dwr.util.addOptions(fieldId1, dataq, "name");
	dwr.util.addOptions(fieldId1, data, valueText, nameText);
	if (parentDesCode != null) {
		document.getElementById(fieldId1).value = parentDesCode;

	} else
		document.getElementById(fieldId1).value = "Select";
}

if (window.addEventListener) {
	window.addEventListener("load", hideAll, false);
} else if (window.attachEvent) {
	window.attachEvent("onload", hideAll);
} else if (window.onLoad) {
	window.onload = hideAll;
}

function clearError(h) {
	document.getElementById("designationCode" + h + "_error").innerHTML = "";
}

/*
 * function deletDes(rowid, desCode) { delID = rowid;
 * lgdDwrDesignationService.isLbReportRefExist(desCode, { callback :
 * isLbReportRefExistSuccess, errorHandler : handleParentTierSetUpListError }); }
 * 
 * function isLbReportRefExistSuccess(data) { if (data != null) { alert("This
 * Designation is Report to Other Designation from this Local Body or Parent
 * Local Body"); } else {
 * document.getElementById("ReportTable").removeChild(document.getElementById(delID)); } }
 */

function setEnableButton() {
	document.getElementById("saveReport").disabled = false;
}
