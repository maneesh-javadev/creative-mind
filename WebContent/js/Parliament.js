var type;



function loadParliamentMaptoLocalBody() {
	// document.getElementById('rdmergeRLBtoTLB').checked = false;
	// document.getElementById('rddeclarenewTLB').checked = false;
	// document.getElementById('divSaveButtons').style.display = 'none';

	document.getElementById('divSpecificStateforward').style.display = 'none';

	document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
	document.getElementById('divRuralDistrictPanchayatforinter').style.display = 'none';

	document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
	document.getElementById('divRuralVillagePanchayat').style.display = 'none';

	// document.getElementById('divVillagePforExist').style.display = 'none';
	// document.getElementById('divDistrictPforExist').style.display = 'none';
	// document.getElementById('divIntermediatePforExist').style.display =
	// 'none';
	// document.getElementById('divLgdVillagePforNew').style.display = 'none';
	// document.getElementById('divLgdSelIntermediatePforNew').style.display =
	// 'none';
}

// getsubdistrict end

function getSelectedIndexes(select) {
	var selected = [];
	for ( var i = 0; i < select.options.length; i++) {
		if (select.options[i].selected) {
			selected.push(i);
		}
	}
	return selected;
}

function getAssemblyRecordList() {

	var select = document.getElementById("ddStateParliamnetDest");
	var districtList = "";
	for ( var i = 0; i < select.length; i++) {
		districtList += select[i].value + ",";
	}
	// alert(districtList);

	if (districtList == '') {
		$("#AssemblyListData_errorcov").show();
	} else if (!districtList.match('PART')) {
		dwr.util.removeAllOptions('villageList');
		$("#AssemblyListData_error").show();
	} else {
		hideAll();
		lgdDwrAssemblyService.getAssembly(districtList, {
			callback : handleAcSucess
		// handleSubDistrictSuccess,
		// errorHandler : handleAcError
		});
	}

	/*
	 * 
	 * $("#ddDistrictParliamnetSource").empty(); var districtList = null;
	 * 
	 * 
	 * 
	 * var id = document.getElementById('ddStateParliamnetDest').value;
	 * alert(id); if (id != "") { if (id.match('PART') == 'PART') districtList =
	 * id.substring(0, (id.length)); else $("#AssemblyListData_error").show(); }
	 * else $("#AssemblyListData_error").show();
	 * 
	 * var selObj = document.getElementById('ddStateParliamnetDest'); var
	 * districtList = ""; for (i = 0; i < selObj.options.length; i++) {
	 * selObj.options[i].selected = true; districtList +=
	 * selObj.options[i].value + ","; }
	 * 
	 * if (districtList == '') { $("#AssemblyListData_errorcov").show(); } else
	 * if (!districtList.match('PART')) {
	 * dwr.util.removeAllOptions('villageList');
	 * $("#AssemblyListData_error").show(); } else { hideAll();
	 * lgdDwrAssemblyService.getAssembly(districtList, { callback :
	 * handleAcSucess, // handleSubDistrictSuccess, errorHandler : handleAcError
	 * }); }
	 */
}

function handleAcSucess(data) {// handleSubDistrictSuccess(data) {
	// Assigns data to result id
	if (data.length > 0) {
		var fieldId = 'ddDistrictParliamnetSource';
		var valueText = 'acCode';
		var nameText = 'acNameEnglish';
		dwr.util.removeAllOptions(fieldId);
		dwr.util.addOptions(fieldId, data, valueText, nameText);
	} else
		alert("No List Found.");

}

function handleAcError() {
	// Show a popup message
	alert("No data found!");
}
function handleSubDistError() {
	// Show a popup message
	alert("No data found!");
}
function getAssembly(id) {

	lgdDwrAssemblyService.getAssemblyconstuincy(id, {
		callback : handlepc, // handleSubDistrictSuccess,
		errorHandler : handleError
	});

}
// data contains the returned value
function handlepc(data) {// handleSubDistrictSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddassemblySource';
	var valueText = 'acCode';
	var nameText = 'acNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleError() {
	// Show a popup message
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
// data contains the returned value
function handleAssemblyForAssembly(data) {
	// Assigns data to result id

	var fieldId = 'ddassemblySource';
	var valueText = 'acCode';
	var nameText = 'acNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleErrorForAssembly() {
	// Show a popup message
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
	// Assigns data to result id

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
	// selectDistrict();

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
		/*
		 * start Autohr Kirandeep Date 18/06/2014
		 */
		document.getElementById("Submit").disabled = true;
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
		//alert("");
		document.getElementById("Submit123").disabled = true;
		document.forms['form1'].submit();

		return true;

	}
	return false;
}

function selectAssemblyList() {

	var selObj = document.getElementById('ddStateParliamnetDest');
	var selObj1 = document.getElementById('ddDistrictParliamnetDest');
	// var selECI = document.getElementById('census2011Code').value;
	var districtNameInEn = document.getElementById('districtNameInEn').value;
	var districtNameInLc = document.getElementById('districtNameInLcl').value;
	// alert(selECI);
	var optsLength = selObj.options.length;
	var optsLength1 = selObj1.options.length;
	// alert("True" +optsLength);

	var error = false;

	if (!validatePcEnglishName()) {
		showClientSideError();

		return false;
	}

	if (!validatePcEnglishNames(districtNameInEn, '#districtNameInEns_error'))
		error = true;
	if (!validatePcLocalNames(districtNameInLc, '#districtNameInLoc_error'))
		error = true;
	if (!validateAssemblyListBlank(optsLength, "#AssemblyList_error"))
		error = true;
	else if (!validateParliamentListData(selObj, selObj1))
		error = true;
	if (!validateAssemblyListBlank(optsLength1, "#AssemblyListBlank_error"))
		error = true;

	if (error != true)
		return true;
	return false;

}

function selectAssembly() {
	hideAll();
	var districtNameInEn = document.getElementById('districtNameInEn').value;
	var districtNameInLc = document.getElementById('districtNameInLcl').value;
	var selObj = document.getElementById('ddDestinctDistrict');
	var optsLength = selObj.options.length;
	var errors = new Array();
	var error = false;
	errors[0] = !validatePc();
	errors[1] = !validatePcEnglishName();
	for ( var i = 0; i < 3; i++) {

		if (errors[i] == true) {

			error = true;
			break;
		}
	}
	// alert(error);
	if (error == true) {

		showClientSideError();

		return false;
	}

	if (!validatePcEnglishNames(districtNameInEn, '#districtNameInEns_error'))
		error = true;
	if (!validatePcLocalNames(districtNameInLc, '#districtNameInLoc_error'))
		error = true;
	if (!validateAssemblyListBlank(optsLength, "#AssemblyList_error"))
		error = true;
	else if (!validateAssemblyListData(selObj))
		error = true;

	if (error == false) {
		$('input[name=Submit]').attr('disabled', true);
		document.forms['form1'].submit();

		return true;

	}
	return false;
}

function validateParliamentListData(selObj, selObj1) {
	var subDistrict = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		subDistrict += selObj.options[i].value + ",";
	}

	if (subDistrict.indexOf("PART") == -1) {
		$("#ddStateParliamnetSource_error").show();
		return false;
	} else {
		for ( var i = 0; i < selObj1.options.length; i++) {
			selObj1.options[i].selected = true;
			subDistrict += selObj1.options[i].value + ",";

		}
		return true;
	}

	// alert("1==== "+subDistrict);

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

function validateAssemblyListBlank(optsLength, showerror) {

	if (!(optsLength > 0)) {

		$(showerror).show();
		return false;

	} else {
		$(showerror).hide();
		return true;
	}

}

function validateAssemblyListData(selObj) {

	var subDistrict = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		subDistrict += selObj.options[i].value + ",";
	}

	if (!((subDistrict.indexOf("PART") != -1 && subDistrict.indexOf("FULL") == -1)
			|| (subDistrict.indexOf("PART") != -1 && subDistrict.indexOf("FULL") != -1) || (subDistrict.indexOf("FULL") != subDistrict
			.lastIndexOf("FULL")
			&& subDistrict.indexOf("FULL") != -1 && subDistrict.indexOf("PART") == -1))) {
		$("#AssemblyListData_error").show();
		return false;
	}

	$("#AssemblyListData_error").hide();
	return true;

}
function validatePcEnglishNames(entityName, errordiv) {

	// alert(districtNameInEn);
	for ( var k = 0; k < entityName.length; k++) {
		var char1 = entityName.charAt(k);
		var cc = char1.charCodeAt(0);

		if ((cc >= 65) && (cc <= 90) || (cc >= 97) && (cc <= 122) || (cc == 32)) {

		} else {
			$(errordiv).show();
			return false;
		}

	}
	$(errordiv).hide();
	return true;
}

function validatePcLocalNames(entityName, errordiv) {

	// alert(districtNameInEn);
	var flag = validateSpecialCharacters(entityName);
	if (flag == false) {
		$(errordiv).show();
		return false;
	}

	$(errordiv).hide();
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
/*
 * function selectAssembly() { if (validation()) var selObj =
 * document.getElementById('ddDestinctDistrict');
 * 
 * var subDistrict = ""; for ( var i = 0; i < selObj.options.length; i++) {
 * selObj.options[i].selected = true; subDistrict += selObj.options[i].value +
 * ","; } var errors = new Array(); var error = false;
 * 
 * 
 * errors[0] = vlidateOnblur('ddStateParliamnetSource','1','15','c');
 * 
 * 
 * if(errors[0]==true){ error = true; }
 * 
 * 
 * 
 * errors[1] = vlidateOnblur('districtNameInEn','1','15','c');
 * 
 * 
 * if(errors[1]==true){ error = true; }
 * 
 * errors[1] = onblur=vlidateOnblur('ddassemblySource','1','15','c');
 * 
 * 
 * if(errors[1]==true){ error = true; }
 * 
 * 
 * 
 * 
 * if (!subdistrictmap()) {
 * 
 * errors[2] = vlidateOnblur('subdistrictbaseurl','1','15','c'); }
 * 
 * if(errors[2]==true){ error = true; }
 * 
 * if (!villagemap()) {
 * 
 * errors[3] = vlidateOnblur('villagebaseurl','1','15','c'); }
 * 
 * if(errors[3]==true){ error = true; }
 * 
 * if(error == true) {
 * 
 * showClientSideError();
 * 
 * return false; } else {
 * 
 * return true; }
 * 
 * 
 * 
 * document.forms['form1'].submit(); }
 * 
 */

function selectgazetteDate() {
	/*
	 * if (validationGazetePublication())
	 */document.forms['form1'].submit();
}

function validationGazetePublication() {

	// var CheckBoxSelect=document.getElementById('CheckBoxSelect').value;
	var GazettePublicationdate = document.getElementById('GazettePublicationdate').value;

	/*
	 * if (CheckBoxSelect == "") { alert("Please Select One Check Box");
	 * document.getElementById('CheckBoxSelect').focus(); return false; }
	 */

	if (GazettePublicationdate == "") {
		alert("Please Enter Gazette Publication Date");
		document.getElementById('GazettePublicationdate').focus();
		return false;
	}
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
		if (document.getElementById('ddLgdLBVillageDestLatDCA')[i].text.indexOf('FULL') != -1
				&& !containsRow(document.getElementById('ddLgdLBVillageDestLatDCA')[i].text)) {
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

	/*
	 * row = tbl.insertRow(tbl.rows.length); row.className += "tblRowB";
	 * 
	 * for(var i=0; i<colCount; i++) { var newcell = row.insertCell(i);
	 * newcell.innerHTML = '<br />'; }
	 */

	// If to be removed un-comment
	// removeSelectedValue('ddLgdLBType');
	// removeEverything('ddLgdLBDistPDestList');
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

	// If to be removed un-comment
	// removeSelectedValue('ddLgdLBType');
	// removeEverything('ddLgdLBDistPDestList');

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

	// If to be removed un-comment
	// removeSelectedValue('ddLgdLBType');
	// removeEverything('ddLgdLBDistPDestList');

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
	for ( var i = 0; i < document.getElementById('ddLgdLBInterCAreaDestL').length; i++) {
		if (document.getElementById('ddLgdLBInterCAreaDestL')[i].text.indexOf('FULL') != -1) {
			row = tbl.insertRow(tbl.rows.length);
			row.className += "tblRowB";
			for ( var j = 0; j < colCount; j++) {
				var newcell = row.insertCell(j);
				if (j == 0 && hasParent == false) {
					newcell.innerHTML = '<strong>Village Panchayat</strong>';
					hasParent = true;
				}
				if (j == 1)
					newcell.innerHTML = document.getElementById('ddLgdLBInterCAreaDestL')[i].text;
				if (j == 2) {
					newcell.align = 'center';
					newcell.innerHTML = '<a href="#dynamicTbl" onclick="removeRows(this.parentNode.parentNode.rowIndex)"><img	src="images/delete.png" width="18" height="19" border="0" /></a>';
				}
			}
		}
	}

	hasParent = false;
	for ( var i = 0; i < document.getElementById('ddLgdLBVillageDestLatDCAVillage').length; i++) {
		if (document.getElementById('ddLgdLBVillageDestLatDCAVillage')[i].text.indexOf('FULL') != -1) {
			row = tbl.insertRow(tbl.rows.length);
			row.className += "tblRowB";
			for ( var j = 0; j < colCount; j++) {
				var newcell = row.insertCell(j);
				if (j == 0 && hasParent == false) {
					newcell.innerHTML = '<strong>Village </strong>';
					hasParent = true;
				}
				if (j == 1)
					newcell.innerHTML = document.getElementById('ddLgdLBVillageDestLatDCAVillage')[i].text;
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

	// If to be removed un-comment
	// removeSelectedValue('ddLgdLBType');
	// removeEverything('ddLgdLBDistPDestList');

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

	var hasParent = false;
	for ( var i = 0; i < document.getElementById('ddLgdLBDistCAreaDestL').length; i++) {
		if (document.getElementById('ddLgdLBDistCAreaDestL')[i].text.indexOf('FULL') != -1) {
			row = tbl.insertRow(tbl.rows.length);
			row.className += "tblRowB";
			for ( var j = 0; j < colCount; j++) {
				var newcell = row.insertCell(j);
				if (j == 0 && hasParent == false) {
					newcell.innerHTML = '<strong>Intermediate Panchayat</strong>';
					hasParent = true;
				}
				if (j == 1)
					newcell.innerHTML = document.getElementById('ddLgdLBDistCAreaDestL')[i].text;
				if (j == 2) {
					newcell.align = 'center';
					newcell.innerHTML = '<a href="#dynamicTbl" onclick="removeRows(this.parentNode.parentNode.rowIndex)"><img	src="images/delete.png" width="18" height="19" border="0" /></a>';
				}
			}
		}
	}

	hasParent = false;
	for ( var i = 0; i < document.getElementById('ddLgdLBSubDistrictDestLatDCA').length; i++) {
		if (document.getElementById('ddLgdLBSubDistrictDestLatDCA')[i].text.indexOf('FULL') != -1) {
			row = tbl.insertRow(tbl.rows.length);
			row.className += "tblRowB";
			for ( var j = 0; j < colCount; j++) {
				var newcell = row.insertCell(j);
				if (j == 0 && hasParent == false) {
					newcell.innerHTML = '<strong>Village Panchayat</strong>';
					hasParent = true;
				}
				if (j == 1)
					newcell.innerHTML = document.getElementById('ddLgdLBSubDistrictDestLatDCA')[i].text;
				if (j == 2) {
					newcell.align = 'center';
					newcell.innerHTML = '<a href="#dynamicTbl" onclick="removeRows(this.parentNode.parentNode.rowIndex)"><img	src="images/delete.png" width="18" height="19" border="0" /></a>';
				}
			}
		}
	}

	hasParent = false;
	for ( var i = 0; i < document.getElementById('ddLgdLBVillDestLatDCA').length; i++) {
		if (document.getElementById('ddLgdLBVillDestLatDCA')[i].text.indexOf('FULL') != -1) {
			row = tbl.insertRow(tbl.rows.length);
			row.className += "tblRowB";
			for ( var j = 0; j < colCount; j++) {
				var newcell = row.insertCell(j);
				if (j == 0 && hasParent == false) {
					newcell.innerHTML = '<strong>Village</strong>';
					hasParent = true;
				}
				if (j == 1)
					newcell.innerHTML = document.getElementById('ddLgdLBVillDestLatDCA')[i].text;
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

	// If to be removed un-comment
	// removeSelectedValue('ddLgdLBType');
	// removeEverything('ddLgdLBDistPDestList');

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
if (acCode > 0)
	lgdDwrlocalBodyService.getmappedlbforPConsituency(statecode, id1, acCode, {
		callback : getPanchayatListbyStateandlbTypeCodeSuccess,
		errorHandler : getPanchayatListbyStateandlbTypeCodeError
	});
else
	lgdDwrlocalBodyService.getmappedlbforPConsituency(statecode, id1, pcCode, {
		callback : getPanchayatListbyStateandlbTypeCodeSuccess,
		errorHandler : getPanchayatListbyStateandlbTypeCodeError
	});

}

// data contains the returned value
function getPanchayatListbyStateandlbTypeCodeSuccess(data) {
var fieldId;
var lbselected = document.getElementById("ddLgdLBType").value;
var temp = lbselected.split(":");
var val = temp[0];
if (val == 4 || val == 5 || val == 6 || val == 4 || val == 7) {
	Modidyloadedlist('ddLgdWardSubDistrictUListDest', 'FULL', true);
	fieldId = 'ddLgdWardSubDistrictUListSource';
} else if (val == 1) {
	Modidyloadedlist('ddLgdLBDistPList', 'FULL', true);
	fieldId = 'ddLgdDispandist';
}

var valueText = 'localBodyCode';
var nameText = 'localBodyNameEnglish';
// villageCode,villageNameEnglish
dwr.util.removeAllOptions(fieldId);

dwr.util.addOptions(fieldId, data, valueText, nameText);

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
}
var valueText = 'localBodyCode';
var nameText = 'localBodyNameEnglish';
// villageCode,villageNameEnglish
dwr.util.removeAllOptions(fieldId);

dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getPanchayatListbyStateandlbTypeCodeError() {
// Show a popup message
alert("No data found!");
}

function getHideShowRuralLBListParliament(id) {
if (document.getElementById('ddStateParliamnetSource').selectedIndex == 0) {
	alert("Please Select Parliament Constituency!");
	$('#ddLgdLBType').attr('selectedIndex', 0);
	return false;
}
emptyOptionBox();
if (id != "") {
	var temp = id.split(":");

	var id1 = temp[0];
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

		break;

	default:
		document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
		document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
		document.getElementById('divRuralVillagePanchayat').style.display = 'none';

	}

} else {
	document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
	document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
	document.getElementById('divRuralVillagePanchayat').style.display = 'none';

}
}

function getHeader(val) {
document.getElementById('lbl_header').innerHTML = 'Available ' + val;
document.getElementById('lbl_header_contri').innerHTML = 'Contributing ' + val;
}

function selectDistrictPanchaytListAtDP() {

var selObj = document.getElementById('ddLgdLBDistPList');
var localbodyCode = "";
for (i = 0; i < selObj.options.length; i++) {
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

lgdDwrLocalGovtBodyService.getIntermediatePanchayatbyParentCodeWithoutChild(id, {
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
for (i = 0; i < selObj.options.length; i++) {
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

lgdDwrLocalGovtBodyService.getIntermediatePanchayatbyParentCodeWithoutChild(id, {
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
for (i = 0; i < selObj.options.length; i++) {
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

lgdDwrLocalGovtBodyService.getIntermediatePanchayatbyParentCodeWithoutChild(id, {
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
for (i = 0; i < selObj.options.length; i++) {
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
lgdDwrLocalGovtBodyService.getLocalGovtBodyCoveredVillageList(id, {
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

var selObj = document.getElementById('ddLgdLBVillageDestAtVillageCA');
var localbodyCode = "";
for (i = 0; i < selObj.options.length; i++) {
	selObj.options[i].selected = true;
	localbodyCode += selObj.options[i].value + ",";
}
if (localbodyCode == '') {
	alert("Please Select PART to get Covered areas.");
} else if (!localbodyCode.match('PART')) {
	// dwr.util.removeAllOptions('villageList');
	alert("Kindly Select the Part Parliament Constituency From the List");
} else {
	getVillageListatDP(localbodyCode);

}
}

function getVillageListatDP(id) {
// alert("getIntermediatePanchayatbyParentCodeWithoutChild"+id);
lgdDwrLocalGovtBodyService.getLocalGovtBodyCoveredVillageList(id, {
	callback : getVillagesListResultSuccess,
	errorHandler : getVillageResultListError
});
}

// data contains the returned value
function getVillagesListResultSuccess(data) {
// Assigns data to result id
// alert("Data is@@@ !!!!"+data);
if (data != '') {
	var fieldId = 'ddLgdLBVillageSourceLatDCAforvillage';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);
} else
	alert("No Data Found!")

}

function getVillageResultListError() {
// Show a popup message
alert("No data found!");
}

function selectVillagesatDP() {

var selObj = document.getElementById('ddLgdLBSubDistrictDestLatDCA');
var localbodyCode = "";
for (i = 0; i < selObj.options.length; i++) {
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
lgdDwrLocalGovtBodyService.getLocalGovtBodyCoveredVillageList(id, {
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

lgdDwrLocalGovtBodyService.getchildlocalbodiesByParentcode(id, {
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

dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleInterPanchayatforintermediateError() {
// Show a popup message
alert("No data found!");
}

function getInterPanchayat(id) {

lgdDwrLocalGovtBodyService.getchildlocalbodiesByParentcode(id, {
	callback : handleInterPanchayatVCASuccess,
	errorHandler : handleInterPanchayatVCAError
});
}

function handleInterPanchayatVCASuccess(data) {
// Assigns data to result id

var fieldId = 'ddLgdLBInterListAtVillageCA';
var valueText = 'localBodyCode';
var nameText = 'localBodyNameEnglish';

dwr.util.removeAllOptions(fieldId);

dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleInterPanchayatVCAError() {
// Show a popup message
alert("No data found!");
}

function getVillagePanchayat(id) {
lgdDwrLocalGovtBodyService.getIntermediatePanchayatbyParentCodeWithoutChild(id, {
	callback : handleVillagePanchSuccess,
	errorHandler : handleVillagePanchbyIntercodeAtVCAError
});
}

function handleVillagePanchSuccess(data) {
// Assigns data to result id
var fieldId = 'ddLgdLBVillageSourceAtVillageCA';
var valueText = 'localBodyCode';
var nameText = 'localBodyNameEnglish';

dwr.util.removeAllOptions(fieldId);
dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleVillagePanchbyIntercodeAtVCAError() {
// Show a popup message
alert("No data found!");
}

function selectMap() {

/* validateform(); */
var errors = new Array();
var error = false;
errors[0] = !validatemapforPc();
errors[1] = !validatemapforLocalBody();
/* errors[2] = !validatemapaddLocalBody(); */
/*
 * errors[2] = !validatepane();
 */
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
if (num != '') {
	if (isNaN(num)) {
		$("#ECICODEData_error").show();

		// alert("Please use Numeric Value!");
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

}

function validateEntityNameExistforParliamnet(stateCode, entityName, entityType) {
type = entityType;
lgdDwrParlimentService.existEntityName(stateCode, entityName, entityType, {
	callback : handleEntityNameSuccessParliament,
	errorHandler : handleEntityNameError
});
}

function handleEntityNameSuccessParliament(data) {
if (data == true) {
	$("#districtNameInEnExist_error").show();
	document.getElementById("districtNameInEn").value = "";
	document.getElementById("districtNameInEn").focus();
}
}

function handleEntityNameError() {
alert("data not found!");
}

function handleParlimentSuccess(data) {
// Assigns data to result id
if (data == true) {
	$("#ECICODEExist_error").show();
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
/*
 * lblist = document.getElementById("mergelocalbody"); list +=
 * lblist.options[lblist.selectedIndex].value + "#";
 */

lblist = document.getElementById("ddLgdWardSubDistrictUListDest");
for ( var x = 0; x < lblist.length; x++) {

	str = lblist.options[x].value;
	str = str.substring(0, str.length - 4);
	the_length = str.length;
	last_char = lblist.options[x].value.charAt(the_length);

	if (last_char == 'F')
		list += str + ":" + val + ":" + "F" + ";";

}

lbwardlist = document.getElementById("ddLgdLBVillageDestLatDCA");
for ( var x = 0; x < lbwardlist.length; x++) {
	str = lbwardlist.options[x].value;
	str = str.substring(0, str.length - 4);
	the_length = str.length;
	last_char = lbwardlist.options[x].value.charAt(the_length);
	if (last_char == 'F')
		list += str + ":" + "W" + ":" + "F" + ";";
	else
		list += str + ":" + "W" + ":" + "P" + ";";
}

document.getElementById('listformat').value = list;
}
function appendLbAddedListtwo() {

var lbselected = document.getElementById("ddLgdLBType").value;
var list = "";
var lblist;
var lbwardlist;
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

lblist = document.getElementById("ddLgdLBDistCAreaDestL");
for ( var x = 0; x < lblist.length; x++) {

	str = lblist.options[x].value;
	str = str.substring(0, str.length - 4);
	the_length = str.length;
	last_char = lblist.options[x].value.charAt(the_length);

	if (last_char == 'F')
		list += str + ":" + 2 + ":" + "F" + ";";

}
lblist = document.getElementById("ddLgdLBSubDistrictDestLatDCA");
for ( var x = 0; x < lblist.length; x++) {

	str = lblist.options[x].value;
	str = str.substring(0, str.length - 4);
	the_length = str.length;
	last_char = lblist.options[x].value.charAt(the_length);

	if (last_char == 'F')
		list += str + ":" + 3 + ":" + "F" + ";";

}

lbwardlist = document.getElementById("ddLgdLBVillDestLatDCA");
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
function appendLbAddedListthree() {

var lbselected = document.getElementById("ddLgdLBType").value;
var list = "";
var lblist;
var lbwardlist;
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

lblist = document.getElementById("ddLgdLBInterCAreaDestL");
for ( var x = 0; x < lblist.length; x++) {

	str = lblist.options[x].value;
	str = str.substring(0, str.length - 4);
	the_length = str.length;
	last_char = lblist.options[x].value.charAt(the_length);

	if (last_char == 'F')
		list += str + ":" + 3 + ":" + "F" + ";";

}

lbwardlist = document.getElementById("ddLgdLBVillageDestLatDCA");
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
} else if (selectedlb == 0) {
	alert("Please Select Local Body Type ");
	document.getElementById('ddLgdLBType').focus();
	return false;
}

var success = document.getElementById('flag1').value;
if (success == 1) {
	selectMap();
} else {
	alert("Please Add Local Body");
	return false;
}

}
function checkData() {

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
		} else if (document.getElementById('ddLgdLBDistCAreaDestL').length == 0) {
			alert("Please Select Contributing Intermediate Panchayat");
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
		} else if (document.getElementById('ddLgdLBInterCAreaDestL').length == 0) {
			alert("Please Select Contributing Village Panchayat");
			status = false;
		}
	}

	else if (id2 == '3') {
		if (document.getElementById('ddLgdLBDistListAtVillageCA').selectedIndex == 0) {
			alert("Please Select Contributing Zilla Panchayat");
			status = false;
		} else if (document.getElementById('ddLgdLBInterListAtVillageCA').selectedIndex == 0) {
			alert("Please Select Contributing Intermediate Panchayat");
			status = false;
		} else if (document.getElementById('ddLgdLBVillageDestAtVillageCA').length == 0) {
			alert("Please Select Contributing Village Panchayat");
			status = false;
		}

	}

	else if (id2 == '4') {
		if (document.getElementById('ddLgdWardSubDistrictUListDest').length == 0) {
			alert("Please Select Contributing  Municipal Corporations");
			status = false;
		} else if (document.getElementById('ddLgdLBVillageDestLatDCA').length == 0) {
			alert("Please Select Wards");
			status = false;
		}

	} else if (id2 == '5') {
		if (document.getElementById('ddLgdWardSubDistrictUListDest').length == 0) {
			alert("Please Select Contributing Municipality");
			status = false;
		} else if (document.getElementById('ddLgdLBVillageDestLatDCA').length == 0) {
			alert("Please Select Wards");
			status = false;
		}
	} else if (id2 == '6') {
		if (document.getElementById('ddLgdWardSubDistrictUListDest').length == 0) {
			alert("Please Select Contributing Notified Area Council");
			status = false;
		} else if (document.getElementById('ddLgdLBVillageDestLatDCA').length == 0) {
			alert("Please Select Wards");
			status = false;
		}
	} else if (id2 == '7') {
		if (document.getElementById('ddLgdWardSubDistrictUListDest').length == 0) {
			alert("Please Select Contributing Town Panchayat");
			status = false;
		} else if (document.getElementById('ddLgdLBVillageDestLatDCA').length == 0) {
			alert("Please Select Wards");
			status = false;
		}
	} else if (id2 == '8') {
		if (document.getElementById('ddLgdWardSubDistrictUListDest').length == 0) {
			alert("Please Select Contributing Cantonment Board");
			status = false;
		} else if (document.getElementById('ddLgdLBVillageDestLatDCA').length == 0) {
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

function constituencynameVal(blkname, val) {
if (val == 'P')
	document.getElementById("UniquePCError").innerHTML = "";
else
	document.getElementById("UniqueACError").innerHTML = "";
var parname = document.getElementById('parliamentName').value;
parname = parname.toUpperCase();
var newname = blkname.toUpperCase();
if (parname != newname) {
	var disId = document.getElementById("stateCodeinteger").value;
	if (disId > 0 && val == 'P') {
		lgdDwrParlimentService.parliamentExist(disId, blkname, val, {
			callback : handlemodifyPCSuccess,
			errorHandler : handleUniquePCError
		});
	} else if (disId > 0 && val == 'A') {
		lgdDwrParlimentService.parliamentExist(disId, blkname, val, {
			callback : handlemodifyACSuccess,
			errorHandler : handleUniquePCError
		});
	}
}
}

function handlemodifyPCSuccess(data) {
document.getElementById("UniquePCError").innerHTML = "";
if (!data) {
	document.getElementById("UniquePCError").innerHTML = "<font color='red'><br>Parliament Name already Exist in this State </font>";
	document.getElementById("pcNameEnglish").value = "";
	document.getElementById("pcNameEnglish").focus();
}
}
function handleUniquePCError() {
}
function handlemodifyACSuccess(data) {
document.getElementById("UniqueACError").innerHTML = "";
if (!data) {
	document.getElementById("UniqueACError").innerHTML = "<font color='red'><br>Assembly Name already Exist in this State  </font>";
	document.getElementById("acnameEnglish").value = "";
	document.getElementById("acnameEnglish").focus();
}

}

function validateNumeriandUniquecdigit(Entitycode, type) {
var parname = document.getElementById('eciUniqueCode').value;
var num = "";
if (type == 'P') {
	num = document.getElementById('ecicode').value;
	Entitycode = document.getElementById('stateCodeinteger').value;
} else {
	num = document.getElementById('ECICODE').value;
	Entitycode = document.getElementById('parliamentCCode').value;
}
if (parname != num) {
	if (type == 'P') {
		lgdDwrParlimentService.existECICODE(Entitycode, num, type, {
			callback : handleParlimentUniqueSuccess,
			errorHandler : handleParlimentError
		});
	} else {
		lgdDwrParlimentService.existECICODE(Entitycode, num, type, {
			callback : handleAssemblyUniqueSuccess,
			errorHandler : handleParlimentError
		});
	}

}
}

function handleParlimentUniqueSuccess(data) {
if (data == true) {
	// $("#ECICODEExist_error").show();
	document.getElementById("ecicode").value = "";

	document.getElementById("ecicode").focus();
} else
	$("#ECICODEExist_error").hide();
}

function handleAssemblyUniqueSuccess(data) {
if (data == true) {
	$("#ECICODEExist_error").show();
	document.getElementById("ECICODE").value = "";
	document.getElementById("ECICODE").focus();
} else
	$("#ECICODEExist_error").hide();
}
