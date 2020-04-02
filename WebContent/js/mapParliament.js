var type;
var fullULb;
var partSelected = 0;
var mappedZpList = '';
function loadParliamentMaptoLocalBody() {
	document.getElementById('divSpecificStateforward').style.display = 'none';
	document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
	document.getElementById('divRuralDistrictPanchayatforinter').style.display = 'none';
	document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
	document.getElementById('divRuralVillagePanchayat').style.display = 'none';
}

function getAssemblyRecordList() {
	$("#ddDistrictParliamnetSource").empty();
	var districtList = null;
	var id = document.getElementById('ddStateParliamnetDest').value;
	if (id != "") {
		if (id.match('PART') == 'PART')
			districtList = id.substring(0, (id.length));
		else
			alert("Kindly Select Part Parliament.");
	} else
		alert("Kindly Select Part Parliament.");
	if (districtList == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!districtList.match('PART')) {
		dwr.util.removeAllOptions('villageList');
		alert("Kindly Select the Part Parliament Constituency From the List");
	} else {

		lgdDwrAssemblyService.getAssembly(districtList, {
			callback : handleAcSucess, // handleSubDistrictSuccess,
			errorHandler : handleAcError
		});
	}
}

function handleAcSucess(data) {
	if (data.length > 0) {
		var fieldId = 'ddDistrictParliamnetSource';
		var valueText = 'acCode';
		var nameText = 'acNameEnglish';
		dwr.util.removeAllOptions(fieldId);
		dwr.util.addOptions(fieldId, data, valueText, nameText);
	} else
		alert("No Data Found.");

}

function handleAcError() {
	alert("No data found!");
}
function handleSubDistError() {
	alert("No data found!");
}
function getAssembly(id) {

	lgdDwrAssemblyService.getAssemblyconstuincy(id, {
		callback : handlepc, // handleSubDistrictSuccess,
		errorHandler : handleError
	});

}
// data contains the returned value
function handlepc(data) {
	var fieldId = 'ddassemblySource';
	var valueText = 'acCode';
	var nameText = 'acNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddassemblySource');
	st.add(new Option('------Select------', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleError() {

	alert("No data found!");
}

function getAssemblyListForAssembly(id) {
	document.getElementById('census2011Code').value = "";
	$("#ddDestinctDistrict").empty();
	lgdDwrAssemblyService.getAssemblyconstuincy(id, {
		callback : handleAssemblyForAssembly, // handleSubDistrictSuccess,
		errorHandler : handleErrorForAssembly
	});

}

function handleAssemblyForAssembly(data) {
	var fieldId = 'ddassemblySource';
	var valueText = 'acCode';
	var nameText = 'acNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleErrorForAssembly() {
	alert("No data found!");
}

function getAssemblyList() {

	var selObj = document.getElementById('ddStateParliamnetSource');
	var districtList = "";

	lgdDwrAssemblyService.getAssembly(districtList, {
		callback : handleSubDistrict, // handleSubDistrictSuccess,
		errorHandler : handleSubDistError
	});

}

// data contains the returned value
function handleSubDistrict(data) {// handleSubDistrictSuccess(data) {
	var fieldId = 'ddDistrictParliamnetSource';
	var valueText = 'acCode';
	var nameText = 'acNameEnglish';
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleSubDistError() {
	// Show a popup message
	alert("No data found!");
}

function selectFinal() {
	var errors = new Array();
	var error = false;
	errors[0] = !validParliament();

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
		document.forms['form1'].submit();

		return true;

	}
	return false;
}

function selectParliamentConstituency() {
	// selectDistrict();

	var errors = new Array();
	var error = false;
	errors[0] = !validModifyParliament();

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
		document.forms['form1'].submit();

		return true;

	}
	return false;
}

function selectAssemblyConstituency() {
	// selectDistrict();

	var errors = new Array();
	var error = false;
	errors[0] = !validModifyAssembly();

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
		document.forms['form1'].submit();

		return true;

	}
	return false;
}

function selectAssembly() {

	// selectDistrict();
	/* validation(); */
	var errors = new Array();
	var error = false;
	errors[0] = !validatePc();
	errors[1] = !validatePcEnglishName();

	var selObj = document.getElementById('ddDestinctDistrict');

	// alert(selECI);
	var optsLength = selObj.options.length;

	// alert("True" +optsLength);
	for ( var i = 0; i < 2; i++) {

		if (errors[i] == true) {

			error = true;
			break;
		}
	}

	if (error == true) {

		showClientSideError();

		return false;
	}

	if (!(optsLength > 0)) {

		alert("Contributing List of Assembly Constituency can not be left blank");
		return false;

	}

	var subDistrict = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		subDistrict += selObj.options[i].value + ",";
	}

	if (!((subDistrict.indexOf("PART") != -1 && subDistrict.indexOf("FULL") == -1)
			|| (subDistrict.indexOf("PART") != -1 && subDistrict.indexOf("FULL") != -1) || (subDistrict.indexOf("FULL") != subDistrict
			.lastIndexOf("FULL")
			&& subDistrict.indexOf("FULL") != -1 && subDistrict.indexOf("PART") == -1))) {
		alert("You are not select one full Assembly in Contributing List of Assembly Constituency ");
		return false;
	}
	/*
	 * errors[2] = !validatepane();
	 * 
	 * 
	 */

	if (error == false) {
		document.forms['form1'].submit();

		return true;

	}
	return false;
}

function validatePc() {

	var ddsource = document.getElementById('ddStateParliamnetSource');

	if (ddsource.selectedIndex == 0) {
		$("#ddStateParliamnetSource_error").show();
		return false;
	}
	$("#ddStateParliamnetSource_error").hide();
	return true;
}

function validModifyAssembly() {

	var acnameEnglish = document.getElementById('acnameEnglish');

	if (acnameEnglish.value == "") {
		$("#acnameEnglish_error").show();
		return false;
	}
	$("#acnameEnglish_error").hide();
	return true;
}

function validModifyParliament() {

	var pcNameEnglish = document.getElementById('pcNameEnglish');

	if (pcNameEnglish.value == "") {
		$("#pcNameEnglish_error").show();
		return false;
	}
	$("#pcNameEnglish_error").hide();
	return true;
}

function validParliament() {
	var districtNameInEn = document.getElementById('districtNameInEn');

	if (districtNameInEn.value == "") {
		$("#districtNameInEn_error").show();
		return false;
	}
	$("#districtNameInEn_error").hide();
	return true;
}

function validatePcEnglishName() {
	var districtNameInEn = document.getElementById('districtNameInEn');

	if (districtNameInEn.value == "") {
		$("#districtNameInEn_error").show();
		return false;
	}
	$("#districtNameInEn_error").hide();
	return true;
}

function validatepane() {

	var ddassemblySource = document.getElementById('ddassemblySource');

	if (ddassemblySource.value == "") {
		$("#ddassemblySource_error").show();
		return false;
	}
	$("#ddassemblySource_error").hide();

	return true;
}

function validation() {
	var ddStateParliamnetSource = document.getElementById('ddStateParliamnetSource').value;

	var ddDistrict = document.getElementById('districtNameInEn').value;

	if (ddStateParliamnetSource == 0) {
		alert("Please Select Parliament Constituency");
		document.getElementById('ddStateParliamnetSource').focus();
		return false;
	}
	if (ddDistrict == "") {
		alert("Please Enter Name Of the Assembly Constituency");
		document.getElementById('districtNameInEn').focus();
		return false;
	}
	return true;
}

function validateParliament() {

	var ddDistrict = document.getElementById('districtNameInEn').value;
	if (ddDistrict == "") {
		alert("Please Enter Name Of the Parliament Constituency");
		document.getElementById('districtNameInEn').focus();
		return false;
	}
	return true;
}

function containsRow(val) {
	var tbl = document.getElementById('dynamicTbl');

	for ( var i = 0; i < tbl.rows.length; i++) {
		if (tbl.rows[i].cells[1].innerHTML == val)
			return true;
	}
	return false;
}

function generateTempViewsforward() {
	if (document.getElementById('ddLgdWardSubDistrictUListDest').length == 0)
		return false;

	document.getElementById('dynamicTbl').style.visibility = "visible";

	var tbl = document.getElementById('dynamicTbl');
	var colCount = tbl.rows[0].cells.length;
	var row;

	if (!containsRow(document.getElementById('ddLgdWardSubDistrictUListDest')[0].text)) {
		row = tbl.insertRow(tbl.rows.length);
		row.className += "tblRowB";

		for ( var i = 0; i < colCount; i++) {

			var newcell = row.insertCell(i);
			if (i == 0)
				newcell.innerHTML = '<strong>' + document.getElementById('ddLgdLBType')[document.getElementById('ddLgdLBType').selectedIndex].text
						+ '</strong>';
			else if (document.getElementById('ddLgdWardSubDistrictUListDest')[0].text.indexOf('FULL') != -1 && i == 1)
				newcell.innerHTML = document.getElementById('ddLgdWardSubDistrictUListDest')[0].text;

			if (i == 2) {
				newcell.align = 'center';
				newcell.innerHTML = '<a href="#dynamicTbl" onclick="removeRows(this.parentNode.parentNode.rowIndex)"><img	src="images/delete.png" width="18" height="19" border="0" /></a>';
			}
		}
	}

	for ( var i = 1; i < document.getElementById('ddLgdWardSubDistrictUListDest').length; i++) {
		if (document.getElementById('ddLgdWardSubDistrictUListDest')[i].text.indexOf('FULL') != -1
				&& !containsRow(document.getElementById('ddLgdWardSubDistrictUListDest')[i].text)) {
			row = tbl.insertRow(tbl.rows.length);
			row.className += "tblRowB";
			for ( var j = 0; j < colCount; j++) {

				var newcell = row.insertCell(j);

				if (j == 1) {
					newcell.innerHTML = document.getElementById('ddLgdWardSubDistrictUListDest')[i].text;
				}
				if (j == 2) {
					newcell.align = 'center';
					newcell.innerHTML = '<a href="#dynamicTbl" onclick="removeRows(this.parentNode.parentNode.rowIndex)"><img	src="images/delete.png" width="18" height="19" border="0" /></a>';
				}
			}
		}
	}

	var hasParent = false;
	for ( var i = 0; i < document.getElementById('ddLgdLBVillageDestLatDCA').length; i++) {
		row = tbl.insertRow(tbl.rows.length);
		row.className += "tblRowB";
		for ( var j = 0; j < colCount; j++) {
			var newcell = row.insertCell(j);
			if (j == 0 && hasParent == false) {
				newcell.innerHTML = '<strong>Ward</strong>';
				hasParent = true;
			}
			if (j == 1)
				newcell.innerHTML = document.getElementById('ddLgdLBVillageDestLatDCA')[i].text;
			if (j == 2) {
				newcell.align = 'center';
				newcell.innerHTML = '<a href="#dynamicTbl" onclick="removeRows(this.parentNode.parentNode.rowIndex)"><img	src="images/delete.png" width="18" height="19" border="0" /></a>';
			}
		}
	}

}

function generateTempViewsforpanchayat() {

	if (document.getElementById('ddLgdLBVillageDestAtVillageCA').length == 0)
		return false;

	document.getElementById('dynamicTbl').style.visibility = "visible";

	var tbl = document.getElementById('dynamicTbl');
	var row = tbl.insertRow(tbl.rows.length);
	row.className += "tblRowB";
	var colCount = tbl.rows[0].cells.length;

	for ( var i = 0; i < colCount; i++) {

		var newcell = row.insertCell(i);
		if (i == 0)
			newcell.innerHTML = '<strong>' + document.getElementById('ddLgdLBType')[document.getElementById('ddLgdLBType').selectedIndex].text
					+ '</strong>';
		else if (document.getElementById('ddLgdLBVillageDestAtVillageCA')[0].text.indexOf('FULL') != -1 && i == 1)
			newcell.innerHTML = document.getElementById('ddLgdLBVillageDestAtVillageCA')[0].text;

		if (i == 2) {
			newcell.align = 'center';
			newcell.innerHTML = '<a href="#dynamicTbl" onclick="removeRows(this.parentNode.parentNode.rowIndex)"><img	src="images/delete.png" width="18" height="19" border="0" /></a>';
		}
	}

	for ( var i = 1; i < document.getElementById('ddLgdLBVillageDestAtVillageCA').length; i++) {
		if (document.getElementById('ddLgdLBVillageDestAtVillageCA')[i].text.indexOf('FULL') != -1) {
			row = tbl.insertRow(tbl.rows.length);
			row.className += "tblRowB";
			for ( var j = 0; j < colCount; j++) {
				var newcell = row.insertCell(j);

				if (j == 1)
					newcell.innerHTML = document.getElementById('ddLgdLBVillageDestAtVillageCA')[i].text;
				if (j == 2) {
					newcell.align = 'center';
					newcell.innerHTML = '<a href="#dynamicTbl" onclick="removeRows(this.parentNode.parentNode.rowIndex)"><img	src="images/delete.png" width="18" height="19" border="0" /></a>';
				}
			}
		}
	}

	var hasParent = false;
	for ( var i = 0; i < document.getElementById('ddLgdLBVillageDestLatDCAforvillage').length; i++) {
		if (document.getElementById('ddLgdLBVillageDestLatDCAforvillage')[i].text.indexOf('FULL') != -1) {
			row = tbl.insertRow(tbl.rows.length);
			row.className += "tblRowB";
			for ( var j = 0; j < colCount; j++) {
				var newcell = row.insertCell(j);
				if (j == 0 && hasParent == false) {
					newcell.innerHTML = '<strong>Village</strong>';
					hasParent = true;
				}
				if (j == 1)
					newcell.innerHTML = document.getElementById('ddLgdLBVillageDestLatDCAforvillage')[i].text;
				if (j == 2) {
					newcell.align = 'center';
					newcell.innerHTML = '<a href="#dynamicTbl" onclick="removeRows(this.parentNode.parentNode.rowIndex)"><img	src="images/delete.png" width="18" height="19" border="0" /></a>';
				}
			}
		}
	}

	row = tbl.insertRow(tbl.rows.length);
	row.className += "tblRowB";

	for ( var i = 0; i < colCount; i++) {
		var newcell = row.insertCell(i);
		newcell.innerHTML = '<br />';
	}

}

function generateTempViewsforvillage() {

	if (document.getElementById('ddLgdLBVillageDestAtVillageCA').length == 0)
		return false;

	document.getElementById('dynamicTbl').style.visibility = "visible";

	var tbl = document.getElementById('dynamicTbl');
	var row = tbl.insertRow(tbl.rows.length);
	row.className += "tblRowB";
	var colCount = tbl.rows[0].cells.length;

	for ( var i = 0; i < colCount; i++) {

		var newcell = row.insertCell(i);
		if (i == 0)
			newcell.innerHTML = '<strong>' + document.getElementById('ddLgdLBType')[document.getElementById('ddLgdLBType').selectedIndex].text
					+ '</strong>';
		else if (document.getElementById('ddLgdLBVillageDestAtVillageCA')[0].text.indexOf('FULL') != -1 && i == 1)
			newcell.innerHTML = document.getElementById('ddLgdLBVillageDestAtVillageCA')[0].text;

		if (i == 2) {
			newcell.align = 'center';
			newcell.innerHTML = '<a href="#dynamicTbl" onclick="removeRows(this.parentNode.parentNode.rowIndex)"><img	src="images/delete.png" width="18" height="19" border="0" /></a>';
		}
	}

	for ( var i = 1; i < document.getElementById('ddLgdLBVillageDestAtVillageCA').length; i++) {
		if (document.getElementById('ddLgdLBVillageDestAtVillageCA')[i].text.indexOf('FULL') != -1) {
			row = tbl.insertRow(tbl.rows.length);
			row.className += "tblRowB";
			for ( var j = 0; j < colCount; j++) {
				var newcell = row.insertCell(j);

				if (j == 1)
					newcell.innerHTML = document.getElementById('ddLgdLBVillageDestAtVillageCA')[i].text;
				if (j == 2) {
					newcell.align = 'center';
					newcell.innerHTML = '<a href="#dynamicTbl" onclick="removeRows(this.parentNode.parentNode.rowIndex)"><img	src="images/delete.png" width="18" height="19" border="0" /></a>';
				}
			}
		}
	}

	var hasParent = false;
	for ( var i = 0; i < document.getElementById('ddLgdLBVillageDestLatDCAforvillage').length; i++) {
		row = tbl.insertRow(tbl.rows.length);
		row.className += "tblRowB";
		for ( var j = 0; j < colCount; j++) {
			var newcell = row.insertCell(j);
			if (j == 0 && hasParent == false) {
				newcell.innerHTML = '<strong>Village</strong>';
				hasParent = true;
			}
			if (j == 1)
				newcell.innerHTML = document.getElementById('ddLgdLBVillageDestLatDCAforvillage')[i].text;
			if (j == 2) {
				newcell.align = 'center';
				newcell.innerHTML = '<a href="#dynamicTbl" onclick="removeRows(this.parentNode.parentNode.rowIndex)"><img	src="images/delete.png" width="18" height="19" border="0" /></a>';
			}
		}
	}

	row = tbl.insertRow(tbl.rows.length);
	row.className += "tblRowB";

	for ( var i = 0; i < colCount; i++) {
		var newcell = row.insertCell(i);
		newcell.innerHTML = '<br />';
	}

}

function generateTempViewsforintermediate() {

	if (document.getElementById('ddLgdLBInterPDestList').length == 0)
		return false;

	document.getElementById('dynamicTbl').style.visibility = "visible";

	var tbl = document.getElementById('dynamicTbl');
	var row = tbl.insertRow(tbl.rows.length);
	row.className += "tblRowB";
	var colCount = tbl.rows[0].cells.length;

	for ( var i = 0; i < colCount; i++) {
		;
		var newcell = row.insertCell(i);
		if (i == 0)
			newcell.innerHTML = '<strong>' + document.getElementById('ddLgdLBType')[document.getElementById('ddLgdLBType').selectedIndex].text
					+ '</strong>';
		else if (document.getElementById('ddLgdLBInterPDestList')[0].text.indexOf('FULL') != -1 && i == 1)
			newcell.innerHTML = document.getElementById('ddLgdLBInterPDestList')[0].text;

		if (i == 2) {
			newcell.align = 'center';
			newcell.innerHTML = '<a href="#dynamicTbl" onclick="removeRows(this.parentNode.parentNode.rowIndex)"><img	src="images/delete.png" width="18" height="19" border="0" /></a>';
		}
	}

	for ( var i = 1; i < document.getElementById('ddLgdLBInterPDestList').length; i++) {
		if (document.getElementById('ddLgdLBInterPDestList')[i].text.indexOf('FULL') != -1) {
			row = tbl.insertRow(tbl.rows.length);
			row.className += "tblRowB";
			for ( var j = 0; j < colCount; j++) {
				var newcell = row.insertCell(j);

				if (j == 1)
					newcell.innerHTML = document.getElementById('ddLgdLBInterPDestList')[i].text;
				if (j == 2) {
					newcell.align = 'center';
					newcell.innerHTML = '<a href="#dynamicTbl" onclick="removeRows(this.parentNode.parentNode.rowIndex)"><img	src="images/delete.png" width="18" height="19" border="0" /></a>';
				}
			}
		}
	}

	var hasParent = false;

	row = tbl.insertRow(tbl.rows.length);
	row.className += "tblRowB";

	for ( var i = 0; i < colCount; i++) {
		var newcell = row.insertCell(i);
		newcell.innerHTML = '<br />';
	}
}

function generateTempViews() {

	if (document.getElementById('ddLgdLBDistPList').length == 0)
		return false;

	document.getElementById('dynamicTbl').style.visibility = "visible";

	var tbl = document.getElementById('dynamicTbl');
	var row = tbl.insertRow(tbl.rows.length);
	row.className += "tblRowB";
	var colCount = tbl.rows[0].cells.length;

	for ( var i = 0; i < colCount; i++) {
		;
		var newcell = row.insertCell(i);
		if (i == 0)
			newcell.innerHTML = '<strong>' + document.getElementById('ddLgdLBType')[document.getElementById('ddLgdLBType').selectedIndex].text
					+ '</strong>';
		else if (document.getElementById('ddLgdLBDistPList')[0].text.indexOf('FULL') != -1 && i == 1)
			newcell.innerHTML = document.getElementById('ddLgdLBDistPList')[0].text;

		if (i == 2) {
			newcell.align = 'center';
			newcell.innerHTML = '<a href="#dynamicTbl" onclick="removeRows(this.parentNode.parentNode.rowIndex)"><img	src="images/delete.png" width="18" height="19" border="0" /></a>';
		}
	}

	for ( var i = 1; i < document.getElementById('ddLgdLBDistPList').length; i++) {
		if (document.getElementById('ddLgdLBDistPList')[i].text.indexOf('FULL') != -1) {
			row = tbl.insertRow(tbl.rows.length);
			row.className += "tblRowB";
			for ( var j = 0; j < colCount; j++) {
				var newcell = row.insertCell(j);

				if (j == 1)
					newcell.innerHTML = document.getElementById('ddLgdLBDistPList')[i].text;
				if (j == 2) {
					newcell.align = 'center';
					newcell.innerHTML = '<a href="#dynamicTbl" onclick="removeRows(this.parentNode.parentNode.rowIndex)"><img	src="images/delete.png" width="18" height="19" border="0" /></a>';
				}
			}
		}
	}

	row = tbl.insertRow(tbl.rows.length);
	row.className += "tblRowB";

	for ( var i = 0; i < colCount; i++) {
		var newcell = row.insertCell(i);
		newcell.innerHTML = '<br />';
	}

}

function removeRows(rowID) {
	if (document.getElementById('dynamicTbl').rows[rowID].cells[0].innerHTML != ''
			&& document.getElementById('dynamicTbl').rows[rowID + 1].cells[0].innerHTML == '') {
		document.getElementById('dynamicTbl').rows[rowID + 1].cells[0].innerHTML = document.getElementById('dynamicTbl').rows[rowID].cells[0].innerHTML;
		document.getElementById('dynamicTbl').rows[rowID + 1].cells[0].style.fontWeight = 'bold';
	}
	removeFromPane(getParent(rowID), document.getElementById('dynamicTbl').rows[rowID].cells[1].innerHTML);
	document.getElementById('dynamicTbl').deleteRow(rowID);
}

function removeFromPane(key, val) {
	switch (key.split('<strong>')[1].split('</strong>')[0]) {
	case 'Select Local Body Type':
		for ( var j = 0; j < document.getElementById('ddLgdWardSubDistrictUListDest').length; j++) {
			if (document.getElementById('ddLgdWardSubDistrictUListDest')[j].innerText == val) {
				document.getElementById('ddLgdWardSubDistrictUListDest').selectedIndex = j;
				break;
			}
		}
		removeItem('ddLgdWardSubDistrictUListDest', 'ddLgdWardSubDistrictUListSource', true);
		break;
	case 'Ward':
		for ( var j = 0; j < document.getElementById('ddLgdLBVillageDestLatDCA').length; j++) {
			if (document.getElementById('ddLgdLBVillageDestLatDCA')[j].innerText == val) {
				document.getElementById('ddLgdLBVillageDestLatDCA').selectedIndex = j;
				break;
			}
		}
		removeItem('ddLgdLBVillageDestLatDCA', 'ddLgdLBVillageSourceLatDCA', true);
		break;
	break;
default:
	break;
}
}

function getParent(rowID) {
for ( var i = rowID; i >= 0; i--) {
	if (document.getElementById('dynamicTbl').rows[i].cells[0].innerHTML != '')
		return document.getElementById('dynamicTbl').rows[i].cells[0].innerHTML;
}
return '';
}

function getPanListbyStateandTypeCode(id) { // State Code Hard Coded
// WIll Be change Later
var statecode = document.getElementById('hdnState').value;
var pcCode = document.getElementById('ddStateParliamnetSource').value;
var acCode = document.getElementById('ddassemblySource').value;
var temp = id.split(":");
var id1 = temp[0];
if (id1 == 4 || id1 == 5 || id1 == 6 || id1 == 1 || id1 == 7) {
	if (statecode == 34 && id1 == 1) {
		id1 = 2;

	}
	if (acCode > 0)
		lgdDwrlocalBodyService.getmappedlbforPConsituency(statecode, id1, acCode, 'A', {
			callback : getPanchayatListbyStateandlbTypeCodeSuccess,
			errorHandler : getPanchayatListbyStateandlbTypeCodeError
		});
	else
		lgdDwrlocalBodyService.getmappedlbforPConsituency(statecode, id1, pcCode, 'P', {
			callback : getPanchayatListbyStateandlbTypeCodeSuccess,
			errorHandler : getPanchayatListbyStateandlbTypeCodeError
		});
}

// Added for pondicherry intermediate & village type
if (statecode == 34 && id1 == 3) {
	id1 = 2;
	if (acCode > 0)
		lgdDwrlocalBodyService.getmappedlbforPConsituency(statecode, id1, acCode, 'A', {
			callback : getPanchayatListbyStateandlbTypeCodeSuccess,
			errorHandler : getPanchayatListbyStateandlbTypeCodeError
		});
	else
		lgdDwrlocalBodyService.getmappedlbforPConsituency(statecode, id1, pcCode, 'P', {
			callback : getPanchayatListbyStateandlbTypeCodeSuccess,
			errorHandler : getPanchayatListbyStateandlbTypeCodeError
		});
}

}

function getUnmapLbbyStatetypeListCode(id) { // State Code Hard Coded
// WIll Be change Later
var statecode = document.getElementById('hdnState').value;
var pcCode = document.getElementById('ddStateParliamnetSource').value;
var acCode = document.getElementById('ddassemblySource').value;
var temp = id.split(":");
var id1 = temp[0];
if (statecode == 34) {
	if (id1 == 1)
		id1 = 2;
	else if (id1 == 2)
		id1 = 3;
}
if (acCode > 0) {
	lgdDwrlocalBodyService.getunmappedlbforPConsituency(statecode, id1, acCode, 'A', {
		callback : getPanchayatListbyStateandlbTypeCodeSuccessforunmap,
		errorHandler : getPanchayatListbyStateandlbTypeCodeError
	});
} else {
	lgdDwrlocalBodyService.getunmappedlbforPConsituency(statecode, id1, pcCode, 'P', {
		callback : getPanchayatListbyStateandlbTypeCodeSuccessforunmap,
		errorHandler : getPanchayatListbyStateandlbTypeCodeError
	});
}

}

// data contains the returned value
function getPanchayatListbyStateandlbTypeCodeSuccess(data) {
var fieldId;
var lbselected = document.getElementById("ddLgdLBType").value;
var temp = lbselected.split(":");
var val = temp[0];
var statecode = document.getElementById('hdnState').value;
if (val == 4 || val == 5 || val == 6 || val == 4 || val == 7) {
	// Modidyloadedlist('ddLgdWardSubDistrictUListDest', 'FULL', true);
	fieldId = 'ddLgdWardSubDistrictUListSource';
} else if (val == 1) {
	// Modidyloadedlist('ddLgdLBDistPList', 'FULL', true);
	fieldId = 'ddLgdDispandist';
}
if (statecode == 34 && val == 3) {
	// Modidyloadedlist('ddLgdLBDistPList', 'FULL', true);
	fieldId = 'ddLgdLBDistListAtVillageCA';
}

var valueText = 'localBodyCode';
var nameText = 'localBodyNameEnglish';
dwr.util.removeAllOptions(fieldId);

//added on the 31/12/2014 for lgd impact by kirandeep
var options = $("#" + fieldId);
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
function getPanchayatListbyStateandlbTypeCodeSuccessforunmap(data) {
var fieldId;
var lbselected = document.getElementById("ddLgdLBType").value;
var temp = lbselected.split(":");
var val = temp[0];
if (val == 4 || val == 5 || val == 6 || val == 4 || val == 7) {

	fieldId = 'ddLgdWardSubDistrictUListDest';
} else if (val == 1) {

	fieldId = 'ddLgdLBDistPList';
} else if (val == 3) {
	fieldId = 'ddLgdLBVillageDestAtVillageCA';
} else if (val == 2) {
	fieldId = 'ddLgdLBInterPDestList';
}
var valueText = 'localBodyCode';
var nameText = 'localBodyNameEnglish';
// villageCode,villageNameEnglish
dwr.util.removeAllOptions(fieldId);

dwr.util.addOptions(fieldId, data, valueText, nameText);
fullULb = data.length;

}

function getPanchayatListbyStateandlbTypeCodeError() {

}

function getHideShowRuralLBListParliament(id) {
if (document.getElementById('ddStateParliamnetSource').selectedIndex == 0) {
	alert("Please Select Parliament Constituency!");
	$('#ddLgdLBType').prop('selectedIndex', 0);
	return false;
} else if (document.getElementById('ddassemblySource').length > 1 && document.getElementById('ddassemblySource').selectedIndex == 0) {

	alert("Please Select Assembly Constituency!");
	$('#ddLgdLBType').prop('selectedIndex', 0);
	return false;

}
emptyOptionBox();
if (id != "") {
	var temp = id.split(":");

	var id2 = temp[1];
	var id3 = temp[2];
	switch (id2) {
	case 'D':

		document.getElementById('divRuralDistrictPanchayat').style.display = 'block';
		document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
		document.getElementById('divRuralVillagePanchayat').style.display = 'none';
		document.getElementById('divSpecificStateforward').style.display = 'none';
		break;
	case 'I':
		if (id3 != "U") {
			document.getElementById('divRuralIntermediatePanchayat').style.display = 'block';
			document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
			document.getElementById('divRuralVillagePanchayat').style.display = 'none';
			document.getElementById('divSpecificStateforward').style.display = 'none';
		} else {
			document.getElementById('divSpecificStateforward').style.display = 'block';
			document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
			document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
			document.getElementById('divRuralVillagePanchayat').style.display = 'none';
		}

		break;
	case 'V':

		document.getElementById('divRuralVillagePanchayat').style.display = 'block';
		document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
		document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
		document.getElementById('divSpecificStateforward').style.display = 'none';

		break;

	default:
		document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
		document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
		document.getElementById('divRuralVillagePanchayat').style.display = 'none';
		document.getElementById('divSpecificStateforward').style.display = 'none';

	}

} else {
	document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
	document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
	document.getElementById('divRuralVillagePanchayat').style.display = 'none';
	document.getElementById('divSpecificStateforward').style.display = 'none';

}
}

function getHeader(val) {
document.getElementById('lbl_header').innerHTML = 'Available ' + val;
document.getElementById('lbl_header_contri').innerHTML = 'Contributing ' + val;
}

function selectDistrictPanchaytListAtDP() {

var selObj = document.getElementById('ddLgdLBDistPList');
var localbodyCode = "";
for ( var i = 0; i < selObj.options.length; i++) {
	selObj.options[i].selected = true;
	localbodyCode += selObj.options[i].value + ",";

}
if (localbodyCode == '') {
	alert("Please Select PART to get Covered areas.");
} else if (!localbodyCode.match('PART')) {
	// dwr.util.removeAllOptions('villageList');
	alert("Kindly Select at least one PART From the List");
} else {
	getIntermediatePanchayatbyParentCode(localbodyCode);
}
}

function getIntermediatePanchayatbyParentCode(id) {
lgdDwrlocalBodyService.getIntermediatePanchayatbyParentCodeWithoutChild(id, {
	callback : getIntermediatePanchayatSuccesspart,
	errorHandler : getIntermediatePanchayatErrorpart
});
}

function getIntermediatePanchayatErrorpart() {
// Show a popup message
alert("No data found!");
}

// data contains the returned value
function getIntermediatePanchayatSuccesspart(data) {

// Assigns data to result id

var fieldId = 'ddLgdLBDistCAreaSourceL';
var valueText = 'localBodyCode';
var nameText = 'localBodyNameEnglish';

dwr.util.removeAllOptions(fieldId);

dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function selectIntermediatePAtDP() {

var selObj = document.getElementById('ddLgdLBDistCAreaDestL');
var localbodyCode = "";
for ( var i = 0; i < selObj.options.length; i++) {
	selObj.options[i].selected = true;
	localbodyCode += selObj.options[i].value + ",";

}
if (localbodyCode == '') {
	alert("Please Select PART to get Covered areas.");
} else if (!localbodyCode.match('PART')) {
	// dwr.util.removeAllOptions('villageList');
	alert("Kindly Select at least one PART From the List");
} else {
	getVillagesPanchayatListbyParentCode(localbodyCode);
}
}

function getVillagesPanchayatListbyParentCode(id) {

lgdDwrlocalBodyService.getIntermediatePanchayatbyParentCodeWithoutChild(id, {
	callback : getVillagessucesspart,
	errorHandler : getVillagesErrorpart
});
}
// data contains the returned value
function getVillagessucesspart(data) {
// Assigns data to result id

var fieldId = 'ddLgdLBwardatDCA';
var valueText = 'localBodyCode';
var nameText = 'localBodyNameEnglish';

dwr.util.removeAllOptions(fieldId);

dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getVillagesErrorpart() {
// Show a popup message
alert("No data found!");
}

function selectIntermediate() {

var selObj = document.getElementById('ddLgdLBInterPDestList');
var localbodyCode = "";
for ( var i = 0; i < selObj.options.length; i++) {
	selObj.options[i].selected = true;
	localbodyCode += selObj.options[i].value + ",";

}
if (localbodyCode == '') {
	alert("Please Select PART to get Covered areas.");
} else if (!localbodyCode.match('PART')) {
	// dwr.util.removeAllOptions('villageList');
	alert("Kindly Select the Part Parliament Constituency From the List");
} else {
	getVillagePanchayatListbyParentCode(localbodyCode);
}
}

function getVillagePanchayatListbyParentCode(id) {

lgdDwrlocalBodyService.getIntermediatePanchayatbyParentCodeWithoutChild(id, {
	callback : getVillagePanchayatSupartsdda,
	errorHandler : getVillageErrorpartweewew
});
}
// data contains the returned value
function getVillagePanchayatSupartsdda(data) {
// Assigns data to result id

var fieldId = 'ddLgdLBInterCAreaSourceL';
var valueText = 'localBodyCode';
var nameText = 'localBodyNameEnglish';

dwr.util.removeAllOptions(fieldId);

dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getVillageErrorpartweewew() {
// Show a popup message
alert("No data found!");
}

function selectVillagesforinter() {

var selObj = document.getElementById('ddLgdLBInterCAreaDestL');
var localbodyCode = "";
for ( var i = 0; i < selObj.options.length; i++) {
	selObj.options[i].selected = true;
	localbodyCode += selObj.options[i].value + ",";
}
if (localbodyCode == '') {
	alert("Please Select PART to get Covered areas.");
} else if (!localbodyCode.match('PART')) {
	// dwr.util.removeAllOptions('villageList');
	alert("Kindly Select the Part Parliament Constituency From the List");
} else {
	getVillageListatvillageDP(localbodyCode);
	// alert(""+localbodyCode);
}
}

function getVillageListatvillageDP(id) {

// alert(""+id);
lgdDwrlocalBodyService.getLocalGovtBodyCoveredVillageList(id, {
	callback : getVillagewwwSuccess,
	errorHandler : getVillagqqqqError
});
}

// data contains the returned value
function getVillagewwwSuccess(data) {
// Assigns data to result id
if (data == '') {
	alert("No data found!");
} else {
	var fieldId = 'ddLgdLBVillageSourceLatDCA';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

}

function getVillagqqqqError() {
// Show a popup message
alert("No data found!");
}

function selectVillages() {
$("#ddLgdLBVillageSourceLatDCAforvillage").empty();
var selObj = document.getElementById('ddLgdLBVillageDestAtVillageCA');
var localbodyCode = "";
for ( var i = 0; i < selObj.options.length; i++) {
	selObj.options[i].selected = true;
	localbodyCode += selObj.options[i].value + ",";
}
if (localbodyCode == '') {
	alert("Please Select PART to get Covered areas.");
} else if (!localbodyCode.match('PART')) {
	// dwr.util.removeAllOptions('villageList');
	alert("Please Select Part Village Panchayat List ");
} else {
	getVillageListatDP(localbodyCode);

}
}

function getVillageListatDP(id) {
// alert("getIntermediatePanchayatbyParentCodeWithoutChild"+id);
lgdDwrlocalBodyService.getLocalGovtBodyCoveredVillageList(id, {
	callback : getVillagesListResultSuccess,
	errorHandler : getVillageResultListError
});
}

// data contains the returned value
function getVillagesListResultSuccess(data) {
if (data != '') {
	var fieldId = 'ddLgdLBVillageSourceLatDCAforvillage';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var options = $("#ddLgdLBVillageSourceLatDCAforvillage");
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

	// dwr.util.addOptions(fieldId, data, valueText, nameText);
}
if (data.length > 0)
	removeMappedVillageWard(1);
}

function getVillageResultListError() {
// Show a popup message
alert("No data found!");
}

function selectVillagesatDP() {

var selObj = document.getElementById('ddLgdLBSubDistrictDestLatDCA');
var localbodyCode = "";
for ( var i = 0; i < selObj.options.length; i++) {
	selObj.options[i].selected = true;
	localbodyCode += selObj.options[i].value + ",";
}
if (localbodyCode == '') {
	alert("Please Select PART to get Covered areas.");
} else if (!localbodyCode.match('PART')) {
	// dwr.util.removeAllOptions('villageList');
	alert("Kindly Select at least one PART From the List");
} else {
	getVillageListatDP1(localbodyCode);

}
}

function getVillageListatDP1(id) {
// alert("getLocalGovtBodyCoveredVillageList"+id);
lgdDwrlocalBodyService.getLocalGovtBodyCoveredVillageList(id, {
	callback : getVillageListSuccess,
	errorHandler : getVillageListError
});
}

// data contains the returned value
function getVillageListSuccess(data) {
// Assigns data to result id
// alert("data "+data);
if (data != '') {
	var fieldId = 'ddLgdLBVillageLatDCA';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);
} else
	alert("No data found!");

}

function getVillageListError() {
// Show a popup message
alert("No data found!");
}

function getInterPanchayatforintermediate(id) {

lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
	callback : handleInterPanchayforintermediateSuccess,
	errorHandler : handleInterPanchayatforintermediateError
});
}

function handleInterPanchayforintermediateSuccess(data) {
// Assigns data to result id

var fieldId = 'ddLgdLBInterPSourceList';
var valueText = 'localBodyCode';
var nameText = 'localBodyNameEnglish';

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

// dwr.util.addOptions(fieldId, data, valueText, nameText);
if (data.length > 0)
	removeMappedIPVp(2);
}

function handleInterPanchayatforintermediateError() {
// Show a popup message
alert("No data found!");
}

function getInterPanchayat(id) {
$("#ddLgdLBVillageSourceAtVillageCA").empty();
lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
	callback : getInterPanchayatUnmappedListforPCAC,
	errorHandler : handleInterPanchayatVCAError
});
}

function getInterPanchayatUnmappedListforPCAC(data) {
var tiertype = document.getElementById('lbtiertype').value;
var fieldId = "";
var st = "";
if (tiertype == 3)
	fieldId = 'ddLgdLBInterListAtVillageCA';
else
	fieldId = 'ddLgdLBVillageSourceAtVillageCA';
var valueText = 'localBodyCode';
var nameText = 'localBodyNameEnglish';
dwr.util.removeAllOptions(fieldId);
if (tiertype == 3) {
	st = document.getElementById('ddLgdLBInterListAtVillageCA');
	st.add(new Option(' -------- Select --------- ', '0'));
}

var options = $("#" + fieldId);
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
if (data.length > 0) {
	removemappedIPforVpAssemblymapping();
	removeMappedIPVp(3);
}
}

function handleInterPanchayatVCAError() {
// Show a popup message
alert("No data found!");
}

function getVillagePanchayat(id) {
lgdDwrlocalBodyService.getIntermediatePanchayatbyParentCodeWithoutChild(id, {
	callback : handleVillagePanchSuccess,
	errorHandler : handleVillagePanchbyIntercodeAtVCAError
});
}

function handleVillagePanchSuccess(data) {
$("#ddLgdLBVillageSourceLatDCAforvillage").empty();
var fieldId = 'ddLgdLBVillageSourceAtVillageCA';
var valueText = 'localBodyCode';
var nameText = 'localBodyNameEnglish';

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

dwr.util.addOptions(fieldId, data, valueText, nameText);
if (data.length > 0)
	removeMappedIPVp(3);

}

function handleVillagePanchbyIntercodeAtVCAError() {
// Show a popup message
alert("No data found!");
}

function selectMap() {
var errors = new Array();
var error = false;
errors[0] = !validatemapforPc();
errors[1] = !validatemapforLocalBody();
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
	/*
	 * start Autohr Kirandeep Date 18/06/2014
	 */
	$('input[name=Submit]').prop('disabled', true);
	document.forms['form1'].submit();

	return true;

}
return false;
}

function validatemapforPc() {

var ddStateParliamnetSource = document.getElementById('ddStateParliamnetSource');

if (ddStateParliamnetSource.selectedIndex == 0) {
	$("#ddStateParliamnetSource_error").show();
	return false;
}
$("#ddStateParliamnetSource_error").hide();
return true;
}

function validatemapforLocalBody() {

var dynamicTbl = document.getElementById('dynamicTbl');

if (dynamicTbl.selectedIndex == 0) {
	$("#dynamicTbl_error").show();
	return false;
}
$("#dynamicTbl_error").hide();
return true;
}

function validatemapaddLocalBody() {

var dynamicTbl = document.getElementById('dynamicTbl');
if (dynamicTbl.selectedIndex == 0) {
	$("#dynamicTbl_error").show();
	return false;
}
$("#dynamicTbl_error").hide();
return true;
}

function validateform() {
var ddStateParliamnetSource = document.getElementById('ddStateParliamnetSource').value;
var ddLgdLBType = document.getElementById('ddLgdLBType').value;

if (ddStateParliamnetSource == 0) {
	alert("Please Select Parliament Constituency");
	document.getElementById('ddStateParliamnetSource').focus();
	return false;
}
if (ddLgdLBType == 0) {
	alert("Please Select Local Body Type");
	document.getElementById('ddLgdLBType').focus();
	return false;
}
return true;
}
function validateformForAssembly() {
var ddStateParliamnetSource = document.getElementById('ddStateParliamnetSource').value;
if (ddStateParliamnetSource == 0) {
	alert("Please Select Parliament Constituency");
	document.getElementById('ddStateParliamnetSource').focus();
	return false;
}

return true;
}
function validateNumericdigit(Entitycode, type) {
var num = document.getElementById('census2011Code').value;

if (isNaN(num)) {

	alert("Please use Numeric Value!");
	document.getElementById("census2011Code").value = "";
	document.getElementById("census2011Code").focus();

} else {

	if (type == 'A')
		Entitycode = document.getElementById('ddStateParliamnetSource').value;

	if (Entitycode == "") {
		alert("please select one of Parliment first");
		document.getElementById('census2011Code').value = "";

	} else {
		lgdDwrParlimentService.existECICODE(Entitycode, num, type, {
			callback : handleParlimentSuccess,
			errorHandler : handleParlimentError
		});

	}

}

}

function validateEntityNameExist(stateCode, entityName, entityType) {
type = entityType;
lgdDwrParlimentService.existEntityName(stateCode, entityName, entityType, {
	callback : handleEntityNameSuccess,
	errorHandler : handleEntityNameError
});
}

function handleEntityNameSuccess(data) {
if (data == true) {
	if (type == 'A') {
		alert("This name of Assembly is already Exist in your State");
		document.getElementById("districtNameInEn").value = "";
		document.getElementById("districtNameInEn").focus();
	} else {
		alert("This name of Parliment is already Exist in your State");
		document.getElementById("districtNameInEn").value = "";
		document.getElementById("districtNameInEn").focus();
	}

}
}

function handleEntityNameError() {
alert("data not found!");
}

function handleParlimentSuccess(data) {
// Assigns data to result id
if (data == true) {
	alert("This ECI Code alredy exsit,Please try another");
	document.getElementById("census2011Code").value = "";
	document.getElementById("census2011Code").focus();
}
}

function handleParlimentError() {
alert("error to check exist ECI Code of Parliment");

}
function emptyOptionBox() {

$("#ddLgdWardSubDistrictUListDest").empty();
$("#ddLgdLBwardSourceLatDCA").empty();
$("#ddLgdLBVillageDestLatDCA").empty();

$("#ddLgdLBDistPList").empty();
$("#ddLgdLBDistCAreaSourceL").empty();
$("#ddLgdLBDistCAreaDestL").empty();
$("#ddLgdLBwardatDCA").empty();
$("#ddLgdLBSubDistrictDestLatDCA").empty();
$("#ddLgdLBVillageLatDCA").empty();
$("#ddLgdLBVillDestLatDCA").empty();

$("#ddLgdLBInterPDestList").empty();
$("#ddLgdLBInterCAreaSourceL").empty();
$("#ddLgdLBInterCAreaDestL").empty();
$("#ddLgdLBVillageSourceLatDCA").empty();

$("#ddLgdLBVillageDestAtVillageCA").empty();
$("#ddLgdLBVillageSourceLatDCAforvillage").empty();
$("#ddLgdLBVillageDestLatDCAforvillage").empty();

}

function Modidyloadedlist(list2, val, doAddVal) {
var len = document.getElementById(list2).options.length;

for ( var j = 0; j < len; j++) {

	if (doAddVal) {
		$('#' + list2).append(
				"<option value=" + document.getElementById(list2)[j].value + val + ">" + document.getElementById(list2)[j].text + " (" + val
						+ ")</option>");
	} else {
		$('#' + list2).append(
				"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");
	}

}
removeSelectedValuelbduplicate(list2, len);
}
function removeSelectedValuelbduplicate(val, len) {
var elSel = document.getElementById(val);
var i;
for (i = len - 1; i >= 0; i--) {

	elSel.remove(i);

}
}
function appendLbAddedList() {

var lbselected = document.getElementById("ddLgdLBType").value;
var list = "";
var lblist;
var lbwardlist;
var str = "";
var the_length;
var temp = lbselected.split(":");
var val = temp[0];
var last_char;
lblist = document.getElementById("ddLgdWardSubDistrictUListDest");
for ( var x = 0; x < lblist.length; x++) {
	str = lblist.options[x].value;
	if (str.indexOf('FULL') > -1) {
		str = str.substring(0, str.length - 4);
		the_length = str.length;
		last_char = lblist.options[x].value.charAt(the_length);

		if (last_char == 'F')
			list += str + ":" + val + ":" + "F" + ";";
	}

}

lbwardlist = document.getElementById("ddLgdLBVillageDestLatDCA");
for ( var x = 0; x < lbwardlist.length; x++) {
	str = lbwardlist.options[x].value;
	if (str.indexOf('FULL') > -1) {
		str = str.substring(0, str.length - 4);
		the_length = str.length;
		last_char = lbwardlist.options[x].value.charAt(the_length);
		if (last_char == 'F')
			list += str + ":" + "W" + ":" + "F" + ";";
		else
			list += str + ":" + "W" + ":" + "P" + ";";
	}
}

document.getElementById('listformat').value = list;
}
function appendLbAddedListtwo() {

var lbselected = document.getElementById("ddLgdLBType").value;
var list = "";
var lblist;
var str = "";
var the_length;
var temp = lbselected.split(":");
var val = temp[0];
var last_char;
lblist = document.getElementById("ddLgdLBDistPList");
for ( var x = 0; x < lblist.length; x++) {

	str = lblist.options[x].value;
	str = str.substring(0, str.length - 4);
	the_length = str.length;
	last_char = lblist.options[x].value.charAt(the_length);

	if (last_char == 'F')
		list += str + ":" + val + ":" + "F" + ";";

}
document.getElementById('listformat').value = list;
}
function appendLbAddedListthree() {

var lbselected = document.getElementById("ddLgdLBType").value;
var list = "";
var lblist;
var str = "";
var the_length;
var temp = lbselected.split(":");
var val = temp[0];
var last_char;
lblist = document.getElementById("ddLgdLBInterPDestList");
for ( var x = 0; x < lblist.length; x++) {

	str = lblist.options[x].value;
	str = str.substring(0, str.length - 4);
	the_length = str.length;
	last_char = lblist.options[x].value.charAt(the_length);

	if (last_char == 'F')
		list += str + ":" + val + ":" + "F" + ";";

}
document.getElementById('listformat').value = list;
}
function appendLbAddedListfour() {

var lbselected = document.getElementById("ddLgdLBType").value;
var list = "";
var lblist;
var lbwardlist;
var str = "";
var the_length;
var temp = lbselected.split(":");
var val = temp[0];
var last_char;
lblist = document.getElementById("ddLgdLBVillageDestAtVillageCA");
for ( var x = 0; x < lblist.length; x++) {

	str = lblist.options[x].value;
	str = str.substring(0, str.length - 4);
	the_length = str.length;
	last_char = lblist.options[x].value.charAt(the_length);

	if (last_char == 'F')
		list += str + ":" + val + ":" + "F" + ";";

}

lbwardlist = document.getElementById("ddLgdLBVillageDestLatDCAforvillage");
for ( var x = 0; x < lbwardlist.length; x++) {
	str = lbwardlist.options[x].value;
	str = str.substring(0, str.length - 4);
	the_length = str.length;
	last_char = lbwardlist.options[x].value.charAt(the_length);
	if (last_char == 'F')
		list += str + ":" + "V" + ":" + "F" + ";";
	else
		list += str + ":" + "V" + ":" + "P" + ";";
}

document.getElementById('listformat').value = list;
}

function selectlist() {
var ddStateParliamnetSource = document.getElementById('ddStateParliamnetSource').value;
var selectedlb = document.getElementById('ddLgdLBType').selectedIndex;

if (ddStateParliamnetSource == 0) {
	alert("Please Select Parliament Constituency");
	document.getElementById('ddStateParliamnetSource').focus();
	return false;
} else if (document.getElementById('ddassemblySource').length > 1 && document.getElementById('ddassemblySource').selectedIndex == 0) {

	alert("Please Select Assembly Constituency!");
	return false;

} else if (selectedlb == 0) {
	alert("Please Select Local Body Type ");
	document.getElementById('ddLgdLBType').focus();
	return false;
}

var success = document.getElementById('flag1').value;
if (success == 1) {
	selectMap();
} else {
	alert("Please Complete Mapping by Click on Add Covered Area ");
	return false;
}

}
function checkData() {
updatePartULb();
if (DataSuccess()) {
	document.getElementById('flag1').value = 1;
	var lbselected = document.getElementById("ddLgdLBType").value;
	var temp = lbselected.split(":");
	var val = temp[0];

	if (val == 4 || val == 5 || val == 6 || val == 4 || val == 7) {
		appendLbAddedList();
		generateTempViewsforward();
		document.getElementById("chooseMoreBtn1").disabled = true;

	} else if (val == 1) {
		appendLbAddedListtwo();
		generateTempViews();
		document.getElementById("chooseMoreBtn2").disabled = true;
	} else if (val == 2) {
		appendLbAddedListthree();
		generateTempViewsforintermediate();
		document.getElementById("chooseMoreBtn3").disabled = true;
	} else if (val == 3) {
		appendLbAddedListfour();
		generateTempViewsforvillage();
		document.getElementById("chooseMoreBtn4").disabled = true;
	}
} else
	document.getElementById('flag1').value = 0;
}
function DataSuccess() {

var status = true;

if (document.getElementById('ddLgdLBType').selectedIndex > 0) {
	var id = document.getElementById('ddLgdLBType').value;

	var temp = id.split(":");
	var id2 = temp[0];

	if (id2 == '1') {
		if (document.getElementById('ddLgdLBDistPList').length == 0) {
			alert("Please Select Contributing Zilla Panchayat");
			status = false;
		}
	}

	else if (id2 == '2') {
		if (document.getElementById('ddLgdLBDistListAtInterCA').selectedIndex == 0) {
			alert("Please Select Contributing Zilla Panchayat");
			status = false;
		} else if (document.getElementById('ddLgdLBInterPDestList').length == 0) {
			alert("Please Select Contributing Intermediate Panchayat");
			status = false;
		}
	}

	else if (id2 == '3') {
		if (document.getElementById('ddLgdLBDistListAtVillageCA').selectedIndex == 0) {
			alert("Please Select Contributing Zilla Panchayat");
			status = false;
		} else if (document.getElementById('lbtiertype').value == 3) {
			if (document.getElementById('ddLgdLBInterListAtVillageCA').selectedIndex == 0) {
				alert("Please Select Contributing Intermediate Panchayat");
				status = false;
			}
		} else if (document.getElementById('ddLgdLBVillageDestAtVillageCA').length == 0) {
			alert("Please Select Contributing Village Panchayat");
			status = false;
		} else {
			var selObj = "";
			selObj = document.getElementById('ddLgdLBVillageDestAtVillageCA');
			var vplist = "";
			var counter = 0;
			for ( var i = 0; i < selObj.options.length; i++) {
				vplist = selObj.options[i].text;
				if (vplist.indexOf("FULL") > 0) {
					counter = 1;
					break;
				}
			}
			if (counter == 0) {
				selObj = document.getElementById('ddLgdLBVillageDestLatDCAforvillage');
				if (selObj.options.length == 0)
					counter = 2;
			}
			if (counter == 2) {
				alert("Please Select at least One Full Village Panchayat or Contributing Villages ");
				status = false;
			}
		}

	}

	else if (id2 == '4') {
		if (document.getElementById('partULb').value == 2) {
			alert("Please Select Contributing  Municipal Corporations");
			status = false;
		} else if (document.getElementById('ddLgdLBVillageDestLatDCA').length == 0 && document.getElementById('partULb').value == 1) {
			alert("Please Select Wards");
			status = false;
		}

	} else if (id2 == '5') {
		if (document.getElementById('partULb').value == 2) {
			if (partSelected == 1)
				alert("Please Select Wards");
			else
				alert("Please Select Contributing Municipality");
			status = false;
		} else if (document.getElementById('ddLgdLBVillageDestLatDCA').length == 0 && document.getElementById('partULb').value == 1) {
			alert("Please Select Wards");
			status = false;
		}
	} else if (id2 == '6') {
		if (document.getElementById('partULb').value == 2) {
			alert("Please Select Contributing Notified Area Council");
			status = false;
		} else if (document.getElementById('ddLgdLBVillageDestLatDCA').length == 0 && document.getElementById('partULb').value == 1) {
			alert("Please Select Wards");
			status = false;
		}
	} else if (id2 == '7') {
		if (document.getElementById('partULb').value == 2) {
			alert("Please Select Contributing Town Panchayat");
			status = false;
		} else if (document.getElementById('ddLgdLBVillageDestLatDCA').length == 0 && document.getElementById('partULb').value == 1) {
			alert("Please Select Wards");
			status = false;
		}
	} else if (id2 == '8') {
		if (document.getElementById('partULb').value == 2) {
			alert("Please Select Contributing Cantonment Board");
			status = false;
		} else if (document.getElementById('ddLgdLBVillageDestLatDCA').length == 0 && document.getElementById('partULb').value == 1) {
			alert("Please Select Wards");
			status = false;
		}

	}
} else {
	alert("Please Select Localbody");
	status = false;
}

return status;
}
function hideAll() {

$("#ddStateParliamnetSource_error").hide();
$("#ddLgdLBType_error").hide();

loadParliamentMaptoLocalBody();

}
function resetSelectList() {
$('#ddLgdLBType').prop('selectedIndex', 0);
$("#ddLgdWardSubDistrictUListSource").empty();
$("#ddLgdDispandist").empty();
$("#ddLgdLBInterPSourceList").empty();
emptyOptionBox();
}

function removeAddedPanchaytsfromDPOption(data) {
var id = document.getElementById('ddLgdLBType').value;
var temp = id.split(":");
id1 = temp[0];
var selObj;
if (id1 == 2)
	selObj = document.getElementById('ddLgdLBDistListAtInterCA');
else
	selObj = document.getElementById('ddLgdLBDistListAtVillageCA');
for ( var i = 0; i < data.length; i++) {
	temp = data[i].localBodyCode;
	for ( var j = 0; j < selObj.options.length; j++) {
		if (selObj.options[j].value == temp)
			selObj.remove(j);
	}
}

}

function removemappedIPforVpAssemblymapping() {
var statecode = document.getElementById('hdnState').value;
var pcCode = document.getElementById('ddStateParliamnetSource').value;
var acCode = document.getElementById('ddassemblySource').value;
if (acCode > 0) {
	lgdDwrlocalBodyService.getunmappedlbforPConsituency(statecode, 2, acCode, 'A', {
		callback : removeIPListVpAssemblymappingSuccess,
		errorHandler : getPanchayatListbyStateandlbTypeCodeError
	});
} else {
	lgdDwrlocalBodyService.getunmappedlbforPConsituency(statecode, 2, pcCode, 'P', {
		callback : removeIPListVpAssemblymappingSuccess,
		errorHandler : getPanchayatListbyStateandlbTypeCodeError
	});
}
}
function removeIPListVpAssemblymappingSuccess(data) {
selObj = document.getElementById('ddLgdLBInterListAtVillageCA');
for ( var i = 0; i < data.length; i++) {
	temp = data[i].localBodyCode;
	for ( var j = 0; j < selObj.options.length; j++) {
		if (selObj.options[j].value == temp) {
			selObj.remove(j);
			break;
		}
	}
}

}
function removeMappedIPVp(val) {
var selObj = "";
var mapObj = "";
var temp = "";
if (val == 2) {
	var selObj = document.getElementById('ddLgdLBInterPSourceList');
	var mapObj = document.getElementById('ddLgdLBInterPDestList');
} else if (val == 3) {
	var selObj = document.getElementById('ddLgdLBVillageSourceAtVillageCA');
	var mapObj = document.getElementById('ddLgdLBVillageDestAtVillageCA');
}
for ( var i = 0; i < mapObj.options.length; i++) {
	temp = mapObj.options[i].value;
	for ( var j = 0; j < selObj.options.length; j++) {
		if (selObj.options[j].value == temp) {
			selObj.remove(j);
			break;
		}
	}

}
}
function deleteMappedEntity() {
var id = document.getElementById('ddLgdLBType').value;
var lbtype = id.split(":");
var val = lbtype[0];
var fieldID = "";
if (val == 4 || val == 5 || val == 6 || val == 4 || val == 7) {
	fieldID = 'ddLgdWardSubDistrictUListDest';

} else if (val == 1) {
	fieldID = 'ddLgdLBDistPList';
} else if (val == 2) {
	fieldID = 'ddLgdLBInterPDestList';
} else if (val == 3) {
	fieldID = 'ddLgdLBVillageDestAtVillageCA';
}
var selObj = document.getElementById(fieldID);
var earlieradded = document.getElementById('choosenlb').value;
var temp = "";
var movedid = "";
var count = "0";
for ( var i = 0; i < selObj.options.length; i++) {
	if (selObj.options[i].selected) {
		temp = selObj.options[i].value;
		if (temp.indexOf('PART') == -1 && temp.indexOf('FULL') == -1) {
			movedid = selObj.options[i].value + "," + movedid;
			count = 1;
		}
	}
}
movedid = movedid.substring(0, movedid.length - 1);
if (earlieradded != 1 && count == 1) {
	earlieradded = earlieradded.substring(0, earlieradded.length - 2);
	movedid = movedid + "," + earlieradded + "," + val;
} else if (count == 1)
	movedid = movedid + "," + val;
if (count == 1)
	document.getElementById('choosenlb').value = movedid;
}

function removeandfilldata(val, num, type) {
var temp = "";
var selObj = "";
if (type == 1)
	selObj = document.getElementById('ddLgdWardSubDistrictUListDest');
else if (type == 2)
	selObj = document.getElementById('ddLgdLBVillageDestAtVillageCA');
else if (type == 3)
	selObj = document.getElementById('ddLgdLBVillageDestLatDCAforvillage');
else if (type == 4)
	selObj = document.getElementById('ddLgdLBVillageDestLatDCA');
for ( var i = 0; i < selObj.options.length; i++) {
	if (selObj.options[i].selected) {
		temp = selObj.options[i].value;
		if (temp.indexOf('PART') == -1 && temp.indexOf('FULL') == -1)

			selObj.options[i].value = selObj.options[i].value + "FULL";
	}
}
removeItem(val, num, true);
}

function getMappedVillageWardofConsituency() {
var id = document.getElementById('ddLgdLBType').value;
var lbtype = id.split(":");
var val = lbtype[0];
var statecode = document.getElementById('hdnState').value;
var pcCode = document.getElementById('ddStateParliamnetSource').value;
var acCode = document.getElementById('ddassemblySource').value;
if (acCode > 0) {
	if (val == 3)
		lgdDwrlocalBodyService.getMappedVillageWardofConsituency(acCode, 'A', 'V', statecode, {
			callback : getVillagesWardsListResultSuccess,
			errorHandler : getVillageResultListError
		});
	else if (val == 4 || val == 5 || val == 6 || val == 7)
		lgdDwrlocalBodyService.getMappedVillageWardofConsituency(acCode, 'A', 'W', statecode, {
			callback : getVillagesWardsListResultSuccess,
			errorHandler : getVillageResultListError
		});
} else {
	if (val == 3)
		lgdDwrlocalBodyService.getMappedVillageWardofConsituency(pcCode, 'P', 'V', statecode, {
			callback : getVillagesWardsListResultSuccess,
			errorHandler : getVillageResultListError
		});
	else if (val == 4 || val == 5 || val == 6 || val == 7)
		lgdDwrlocalBodyService.getMappedVillageWardofConsituency(pcCode, 'P', 'W', statecode, {
			callback : getVillagesWardsListResultSuccess,
			errorHandler : getVillageResultListError
		});
}

}

// data contains the returned value
function getVillagesWardsListResultSuccess(data) {
var id = document.getElementById('ddLgdLBType').value;
var lbtype = id.split(":");
var val = lbtype[0];
var fieldId = "";
if (val == 3)
	fieldId = 'ddLgdLBVillageDestLatDCAforvillage';
else
	fieldId = 'ddLgdLBVillageDestLatDCA';
var valueText = 'localBodyCode';
var nameText = 'localBodyNameEnglish';
dwr.util.removeAllOptions(fieldId);
dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function removeMappedVillageWard(val) {
var selObj = "";
var mapObj = "";
var temp = "";
if (val == 1) {
	var selObj = document.getElementById('ddLgdLBVillageSourceLatDCAforvillage');
	var mapObj = document.getElementById('ddLgdLBVillageDestLatDCAforvillage');
} else if (val == 2) {
	var selObj = document.getElementById('ddLgdLBwardSourceLatDCA');
	var mapObj = document.getElementById('ddLgdLBVillageDestLatDCA');
}
for ( var i = 0; i < mapObj.options.length; i++) {
	temp = mapObj.options[i].value;
	for ( var j = 0; j < selObj.options.length; j++) {
		if (selObj.options[j].value == temp) {
			selObj.remove(j);
			break;
		}
	}

}
}

function deleteEarlierMappedEntity(id) {
var fieldID = "";
var val = "";
if (id == 1) {
	fieldID = 'ddLgdLBVillageDestLatDCAforvillage';
	val = "V";
} else if (id == 2) {
	fieldID = 'ddLgdLBVillageDestLatDCA';
	val = "W";
}
var selObj = document.getElementById(fieldID);
var earlieradded = document.getElementById('ward_number').value;
var temp = "";
var movedid = "";
var count = "0";
for ( var i = 0; i < selObj.options.length; i++) {
	if (selObj.options[i].selected) {
		temp = selObj.options[i].value;
		if (temp.indexOf('PART') == -1 && temp.indexOf('FULL') == -1) {
			movedid = selObj.options[i].value + "," + movedid;
			count = 1;
		}
	}
}
movedid = movedid.substring(0, movedid.length - 1);
if (earlieradded != 1 && count == 1) {
	earlieradded = earlieradded.substring(0, earlieradded.length - 2);
	movedid = movedid + "," + earlieradded + "," + val;
} else if (count == 1)
	movedid = movedid + "," + val;
if (count == 1)
	document.getElementById('ward_number').value = movedid;
}

function getPcWardListFromMunicipality() {

var selObj = document.getElementById('ddLgdWardSubDistrictUListDest');

var localBodyCode = "";
for ( var i = 0; i < selObj.options.length; i++) {
	selObj.options[i].selected = true;
	localBodyCode += selObj.options[i].value + ",";

}

if (localBodyCode == '') {
	alert("Please Select PART to get Covered areas.");
} else if (!localBodyCode.match('PART')) {

	alert("Please Select the Part of Available Local Body ");
} else {

	PCWardListforrPCAC(localBodyCode);

}
}

function PCWardListforrPCAC(id) {
if (id == '') {
	alert("Please Select PART to get Covered areas.");
} else if (!id.match('PART')) {
	alert("Please Select PART to get Covered areas.");
} else {
	lgdDwrlocalBodyService.getWardListFromContributingMunicipality(id, {
		callback : gePCtWardListforCMSuccess,
		errorHandler : getWardListforCMError
	});
}
}

function gePCtWardListforCMSuccess(data) {
var fieldId = 'ddLgdLBwardSourceLatDCA';
var valueText = 'wardCode';
var nameText = 'wardNameEnglish';
dwr.util.removeAllOptions(fieldId);
dwr.util.addOptions(fieldId, data, valueText, nameText);
if (data.length > 0)
	removeMappedVillageWard(2);
}
function updatePartULb() {
document.getElementById('partULb').value = 0;
partSelected = 0;
var i;
var temp;
var count = 0;
var selObj = document.getElementById('ddLgdWardSubDistrictUListDest');
for (i = 0; i < selObj.options.length; i++) {
	temp = selObj.options[i].value;
	if (temp.indexOf('PART') > -1) {
		document.getElementById('partULb').value = 1;
		partSelected = 1;
		break;
	}
}
for (i = 0; i < selObj.options.length; i++) {
	temp = selObj.options[i].text;

	if (temp.indexOf('FULL') > -1) {
		count = count + 1;
	}
}
selObj = document.getElementById('ddLgdLBVillageDestLatDCA');
if (fullULb == count && selObj.options.length == 0) {
	document.getElementById('partULb').value = 2;
}
}

function getMappedZps() {
var statecode = document.getElementById('hdnState').value;
var pcCode = document.getElementById('ddStateParliamnetSource').value;
var acCode = document.getElementById('ddassemblySource').value;
var id1 = 1;
if (acCode > 0) {
	lgdDwrlocalBodyService.getunmappedlbforPConsituency(statecode, id1, acCode, 'A', {
		callback : ListmappedZpSuccess,
		errorHandler : getPanchayatListbyStateandlbTypeCodeError
	});
} else {
	lgdDwrlocalBodyService.getunmappedlbforPConsituency(statecode, id1, pcCode, 'P', {
		callback : ListmappedZpSuccess,
		errorHandler : getPanchayatListbyStateandlbTypeCodeError
	});
}

}
function ListmappedZpSuccess(data) {
lbCoverage = '';
for ( var i = 0; i < data.length; i++) {
	lbCoverage = data[i].localBodyCode + ":" + lbCoverage;
}
mappedZpList = lbCoverage.substring(0, lbCoverage.length - 1);
}
function removeZpLbMapped(type) {
var tempid = type.split(":");
type = tempid[0];
var temp = '';
var selObj = '';
var mappedValues = new Array();
if (type == 2 || type == 3) {
	mappedValues = mappedZpList.split(":");
	if (type == 2)
		selObj = document.getElementById('ddLgdLBDistListAtInterCA');
	else if (type == 3)
		selObj = document.getElementById('ddLgdLBDistListAtVillageCA');
	for ( var i = 0; i < mappedValues.length; i++) {
		temp = mappedValues[i];
		for ( var j = 0; j < selObj.options.length; j++) {
			if (selObj.options[j].value == temp) {
				selObj.remove(j);
				break;
			}
		}
	}
}

}