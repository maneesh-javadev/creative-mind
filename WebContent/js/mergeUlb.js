function getLBSubTypeList(id, val) {
	var state = document.getElementById('stateCode').value;
	var selObj = document.getElementById('ddLgdLBType');
	var lb = "";
	var code = "";
	var lbcode = "";
	var i = "";
	var count = 0;
	var len = 0;
	var validation = 1;
	if (val == 1) {
		$("#availablelb").empty();
		$("#choosenlb").empty();
		for ( var i = 0; i < selObj.options.length; i++) {
			if (selObj.options[i].selected == true) {
				lbcode = selObj.options[i].value;
				lb = lbcode.split(":");
				code = lb[0];
				lgdDwrlocalBodyService.getPanchayatListbyStateandlbTypeCode(state, code, {
					callback : ULBDetailListSuccess,
					errorHandler : ULBDetailListError
				});
			}

		}

	} else {
		selObj = document.getElementById('ddLgdLBType');
		for (i = 0; i < selObj.options.length; i++)
			if (selObj.options[i].selected == true)
				count++;
		selObj = document.getElementById('choosenlb');
		for (i = 0; i < selObj.options.length; i++)
			len++;
		if (count == 0) {
			alert("Please Select Local Body Type");
			document.getElementById('ddUrbanLocalBodyType').selectedIndex = 0;
			validation = 0;

		} else if (len == 0) {
			alert("Please Select Contributing Urban Local Body");
			document.getElementById('ddUrbanLocalBodyType').selectedIndex = 0;
			validation = 0;

		}
		lb = id.split(":");
		code = lb[0];
		if (validation == 1)
			lgdDwrlocalBodyService.getPanchayatListbyStateandlbTypeCode(state, code, {
				callback : ULBDetailListSuccessmerge,
				errorHandler : ULBDetailListError
			});
	}
}
function ULBDetailListSuccess(data) {
	var fieldId = 'availablelb';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}
function ULBDetailListSuccessmerge(data) {
	var fieldId = 'ddUrbanLocalBody';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddUrbanLocalBody');
	st.add(new Option(' Select Local Body ', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
	if (data.length > 0)
		removeDuplidateListData();

}

function ULBDetailListError() {
	alert("No Data Fond");
}

function selectLocalBodyListforUrban() {
	var selObj = document.getElementById('choosenlb');
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

		lgdDwrlocalBodyService.getCoveredAreaforULBFinalforLB(id, {
			callback : handleULBSuccess,
			errorHandler : handleULBError
		});
	}
}

function handleULBSuccess(data) {

	if (data == '') {
		alert("Coverage are not mapped for Selected Local Body. Please Map Covered Area first.");
	} else {
		var fieldId = 'partUlbSubdistrict';
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
function addItemLB(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {

		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {

				if (doAddVal) {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + ">" + document.getElementById(list2)[j].text
									+ " (" + val + ")</option>");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");
				}

			}
		}
		removeSelectedValue(list2);
	}
}

function askForUpgrade(obj) {
	if (obj.value != 0) {
		$("#trForUpgrade1").show();
	}
}
function selectForUpgrade(obj) {
	var selObj1 = document.getElementById("ddUrbanLocalBodyType");
	var selObj2 = document.getElementById("ddUrbanLocalBodyTypeForUpgrade");
	if (obj.value == "Y") {
		document.getElementById("operationCode").value = 1;
		$("#trForUpgrade2").show();
		var tmp = selObj1.value;
		selObj1.disabled = true;
		selObj2.disabled = false;
		if (tmp != 0) {
			var op = document.getElementById("ddUrbanLocalBodyTypeForUpgrade").getElementsByTagName("option");
			for ( var i = 0; i < op.length; i++) {
				if (op[i].value == tmp) {
					op[i].disabled = true;
				} else {
					op[i].disabled = false;
				}
			}
		}
	} else {
		$("#trForUpgrade2").hide();
		document.getElementById("operationCode").value = 0;
		selObj1.disabled = false;
	}
}

function checkValues() {
	var i = "";
	var selObj = document.getElementById('choosenlb');
	var localbodyCode = "";
	for (i = 0; i < selObj.options.length; i++) {
		if (selObj.options[i].text.indexOf('PART') == -1)
			localbodyCode += selObj.options[i].value + ",";

	}
	document.getElementById('listformat').value = localbodyCode.substring(0, localbodyCode.length - 1);

}
function getData() {

	var selObj = document.getElementById('ddLgdLBType');
	var count = 0;
	var len = 0;
	var i = "";
	var status = true;
	var optionType = document.getElementById("operationCode").value;
	for (i = 0; i < selObj.options.length; i++)
		if (selObj.options[i].selected == true)
			count++;
	selObj = document.getElementById('choosenlb');
	for (i = 0; i < selObj.options.length; i++)
		len++;
	if (count == 0) {
		alert("Please Select Local Body Type");
		status = false;
	} else if (len == 0) {
		alert("Please Select Contributing Urban Local Body");
		status = false;
	} else if (document.getElementById('ddUrbanLocalBodyType').selectedIndex == 0) {
		alert("Please Select Local Body Type");
		status = false;
	} else if (document.getElementById('ddUrbanLocalBody').selectedIndex == 0) {
		alert("Please Select The ULB to be Meged With");
		status = false;
	} else if (optionType == 2) {
		alert("Please Select The UPGrade Option");
		status = false;
	} else if (optionType == 1) {

		if (document.getElementById('ddUrbanLocalBodyTypeForUpgrade').selectedIndex == 0) {
			alert("Please Select UPgraded New ULB Type ");
			status = false;
		}
	}
	if (status)

	{
		selObj = document.getElementById('choosenlb');
		var localbodyCode = "";
		for (i = 0; i < selObj.options.length; i++) {
			if (selObj.options[i].text.indexOf('PART') == -1)
				localbodyCode += selObj.options[i].value + ",";

		}
		document.getElementById('listformat').value = localbodyCode.substring(0, localbodyCode.length - 1);
	} else
		return status;
}
function removeDuplidateListData() {
	var selObj = document.getElementById('ddUrbanLocalBody');
	var mapObj = document.getElementById('choosenlb');
	var temp = "";
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
function resetList() {
	document.getElementById('ddUrbanLocalBodyType').selectedIndex = 0;
	document.getElementById('ddUrbanLocalBody').selectedIndex = 0;

}