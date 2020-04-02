function setSessionValue(val1, val2, val3) {
	lgdDwrBlockService.invalidateBlockLoop(val1, val2, val3, {
		callback : handleInvalidateLoop,
		errorHandler : handleInvalidateLoopError
	});

}
function handleInvalidateLoop(data) {
	if (data) {
		// alert('success');
	}
}

function handleInvalidateLoopError() {
	alert("No data found!");
}

function validateSelectAddMore() {
	selectall();
	if (validateformWithoutSave()) {
		setSessionValue(document.getElementById('ddTargetBlockListNo').value, gelSelectedVillages('contributedVillages'));
		appendList();
		generateTempView();
		removeSelectedValueDistrict('ddTargetBlockListNo');
		removeEverything('contributedVillages');
		$("#contributedBlockUlb").empty();
		if (document.getElementById('ddVillage').length == 0 && document.getElementById('avaBlockUlb').length == 0)
			document.getElementById('chooseMoreBtn').disabled = true;

	}

}
function appendList() {
	var list = "";
	var a = "";
	var districtlist = "";
	var subdistrictlist = "";
	if (document.getElementById('contributedVillages').length > 0) {
		a = document.getElementById('listformat').value;
		list = a;
		districtlist = document.getElementById("ddTargetBlockListNo");
		list += districtlist.options[districtlist.selectedIndex].value + "#";
		subdistrictlist = document.getElementById("contributedVillages");
		for ( var x = 0; x < subdistrictlist.length; x++) {
			list += subdistrictlist.options[x].value + ":";
		}
		list = list.toString().substring(0, list.length - 1);
		list += ",";
		document.getElementById('listformat').value = list;
	}
	/*if (document.getElementById('contributedBlockUlb').length > 0) {
		a = document.getElementById('ulbListFormat').value;
		list = a;
		districtlist = document.getElementById("ddTargetBlockListNo");
		list += districtlist.options[districtlist.selectedIndex].value + "#";
		subdistrictlist = document.getElementById("contributedBlockUlb");
		for ( var x = 0; x < subdistrictlist.length; x++) {
			list += subdistrictlist.options[x].value + ":";
		}
		list = list.toString().substring(0, list.length - 1);
		list += ",";
		document.getElementById('ulbListFormat').value = list;
	}*/

}

function gelSelectedVillages(val) {
	var elSel = document.getElementById(val);
	var i;
	var output = "";
	for (i = elSel.length - 1; i >= 0; i--) {
		if (elSel.options[i].selected) {
			output += elSel.options[i].value + ",";
		}
	}
	return output.substring(0, output.length - 1);
}
dwr.engine.setActiveReverseAjax(true);
function validateform() {
	var flag = document.getElementById('flag1').value;
	var ddDistrict = "";
	if (flag == 0)
		ddDistrict = document.getElementById('ddDistrict').value;
	var ddTargetDistrictYes = document.getElementById('ddTargetDistrictYes').value;
	var ddTargetDistrictNo = document.getElementById('ddTargetDistrictNo').value;
	var ddSrcBlock = document.getElementById('ddSrcBlock').value;
	var ddTargetBlockListYes = document.getElementById('ddTargetBlockListYes').value;
	var ddTargetBlockListNo = document.getElementById('ddTargetBlockListNo').value;
	if (ddDistrict == 0 && flag == 0) {
		alert("Please Select District");
		document.getElementById('ddDistrict').focus();
		return false;
	}
	if (ddSrcBlock == 0) {
		alert("Please Select Block to Invalidate");
		document.getElementById('ddSrcBlock').focus();
		return false;
	}
	if (document.getElementById('blockdelete_yes').checked) {
		
		if (ddTargetDistrictYes == 0 || ddTargetDistrictYes == "") {
			alert("Please Select Target District");
			document.getElementById('ddTargetDistrictYes').focus();
			return false;
		}
		if (ddTargetBlockListYes == 0 || ddTargetBlockListYes == "") {
			alert("Please Select Block To merge with");
			document.getElementById('ddTargetBlockListYes').focus();
			return false;
		}
	} else if (document.getElementById('blockdelete_no').checked) {
		if (!villageflag &&(ddTargetDistrictNo == 0 || ddTargetDistrictNo == "")) {
			alert("Please Select Target District");
			document.getElementById('ddTargetDistrictNo').focus();
			return false;
		}
		if(!villageflag){
			if (!document.getElementById('chooseMoreBtn').disabled) {
				alert('Please Select all Villages, ULBs  to merge with target District');
				return false;
			}
		}
		

	}
	return true;
}
function validateformWithoutSave() {
	var flag = document.getElementById('flag1').value;
	var ddDistrict = "";
	if (flag == 0)
		ddDistrict = document.getElementById('ddDistrict').value;
	var ddTargetDistrictYes = document.getElementById('ddTargetDistrictYes').value;
	var ddTargetDistrictNo = document.getElementById('ddTargetDistrictNo').value;
	var ddSrcBlock = document.getElementById('ddSrcBlock').value;

	var ddTargetBlockListYes = document.getElementById('ddTargetBlockListYes').value;
	var ddTargetBlockListNo = document.getElementById('ddTargetBlockListNo').value;
	if (ddDistrict == 0 && flag == 0) {
		alert("Please Select District");
		document.getElementById('ddDistrict').focus();
		return false;
	}
	if (ddSrcBlock == 0) {
		alert("Please Select Block to Invalidate");
		document.getElementById('ddSrcBlock').focus();
		return false;
	}
	if (document.getElementById('blockdelete_yes').checked) {

		if (ddTargetDistrictYes == 0 || ddTargetDistrictYes == "") {
			alert("Please Select Target District");
			document.getElementById('ddTargetDistrictYes').focus();
			return false;
		}
		if (ddTargetBlockListYes == 0 || ddTargetBlockListYes == "") {
			alert("Please Block To merge with");
			document.getElementById('ddTargetBlockListYes').focus();
			return false;
		}
	} else if (document.getElementById('blockdelete_no').checked) {
		if (ddTargetDistrictNo == 0 || ddTargetDistrictNo == "") {
			alert("Please Select Target District");
			document.getElementById('ddTargetDistrictNo').focus();
			return false;
		}
		if (ddTargetBlockListNo == 0 || ddTargetBlockListNo == "") {
			alert("Please Select the Block To merge with");
			document.getElementById('ddTargetBlockListNo').focus();

			return false;
		}
	}
	return true;
}

/*
 * start Autohr Kirandeep change Date 18/06/2014
 */
function validateSelectAndSubmit() {
	selectall();
	if (validateform()) {
		$('input[name=Submit]').prop('disabled', true);
		document.forms['invalidateForm'].submit();
	}
}
function selectall() {

	var selObj = "";
	selObj = document.getElementById('contributedVillages');
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}
	/*selObj = document.getElementById('contributedBlockUlb');
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}*/

}
function onSubmit() {

	var errors = new Array();
	var error = false;

	errors[0] = vlidateOnblur('ddDistrict', '1', '15', 'c');
	if (errors[0] == true) {
		error = true;
	}
	errors[1] = vlidateOnblur('ddSrcBlock', '1', '15', 'c');
	if (errors[1] == true) {
		error = true;
	}

	if (error == true) {

		showClientSideError();

		return false;
	} else {

		document.forms['invalidateForm'].submit();
		return true;

	}

}

function removeItemFromOtherDropdowns(val) {
	var dwrDistrictList = document.getElementById('ddDistrict');

	if (val.selectedIndex > 0) {
		var selectedValue = val.value;
		var pos;

		removeEverything('ddTargetBlockListYes');
		removeEverything('ddTargetBlockListNo');

		document.getElementById('ddTargetBlockListYes').add(new Option('Select District', 0));
		document.getElementById('ddTargetBlockListNo').add(new Option('Select District', 0));

		var options = $("#ddTargetBlockListNo");
		for ( var i = 0; i < dwrDistrictList.length; i++) {
			var option = $("<option/>");
			if (dwrDistrictList[i].disabled) {
				$(option).attr('disabled', 'disabled');
				$(option).val(dwrDistrictList[i].value).text(dwrDistrictList[i].text);
				options.append(option);
			} else {
				$(option).val(dwrDistrictList[i].value).text(dwrDistrictList[i].text);
				options.append(option);
			}
		}
		var options = $("#ddTargetBlockListYes");
		for ( var i = 0; i < dwrDistrictList.length; i++) {
			var option = $("<option/>");
			if (dwrDistrictList[i].disabled) {
				$(option).attr('disabled', 'disabled');
				$(option).val(dwrDistrictList[i].value).text(dwrDistrictList[i].text);
				options.append(option);
			} else {
				$(option).val(dwrDistrictList[i].value).text(dwrDistrictList[i].text);
				options.append(option);
			}

		}

		/*
		 * for ( var i = 0; i < dwrDistrictList.length; i++) {
		 * 
		 * document.getElementById('ddTargetBlockListYes').add(new
		 * Option(dwrDistrictList[i].innerText, dwrDistrictList[i].value));
		 * document.getElementById('ddTargetBlockListNo').add(new
		 * Option(dwrDistrictList[i].innerText, dwrDistrictList[i].value)); }
		 */

		for ( var i = 0; i < document.getElementById('ddTargetBlockListYes').length; i++) {
			if (document.getElementById('ddTargetBlockListYes')[i].value == selectedValue) {
				pos = i;
			}
		}

		document.getElementById('ddTargetBlockListYes').selectedIndex = pos;
		document.getElementById('ddTargetBlockListNo').selectedIndex = pos;
		removeSelectedValue('ddTargetBlockListYes');
		removeSelectedValue('ddTargetBlockListNo');
	}
}

function removeEverything(val) {
	var elSel = document.getElementById(val);
	for ( var i = elSel.length - 1; i >= 0; i--)
		elSel.remove(i);
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

function getBlockList(id) {
	// alert(id);
	lgdDwrBlockService.getBlockList(id, {
		callback : handleBlockSrcSuccess,
		errorHandler : handleBlockSrcError
	});

}


function handleBlockSrcSuccess(data) {
	// alert(data);
	var fieldId = 'ddSrcBlock';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddSrcBlock');
	st.add(new Option('Please Select Block', '0'));

	var options = $("#ddSrcBlock");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.blockCode).text(obj.blockNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.blockCode).text(obj.blockNameEnglish);
			options.append(option);
		}
	});

}

function handleBlockSrcError() {
	alert("No data found!");
}

function getTargetBlockListYes(id) {
	// alert(id);
	lgdDwrBlockService.getBlockList(id, {
		callback : handleBlockSuccessYes,
		errorHandler : handleBlockErrorYes
	});

}
function handleBlockSuccessYes(data) {
	// alert(data);
	var fieldId = 'ddTargetBlockListYes';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddTargetBlockListYes');
	st.add(new Option('Please Select Block', '0'));

	var options = $("#ddTargetBlockListYes");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.blockCode).text(obj.blockNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.blockCode).text(obj.blockNameEnglish);
			options.append(option);
		}
	});

	if (data.length > 0)
		removeduplicatebLock(1);

}

function handleBlockErrorYes() {
	alert("No data found!");
}

function getTargetBlockListNo(id) {
	// alert(id);
	lgdDwrBlockService.getBlockList(id, {
		callback : handleBlockSuccessNo,
		errorHandler : handleBlockErrorNo
	});

}
function handleBlockSuccessNo(data) {
	// alert(data);
	var fieldId = 'ddTargetBlockListNo';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddTargetBlockListNo');
	st.add(new Option('Please Select Block', '0'));

	var options = $("#ddTargetBlockListNo");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.blockCode).text(obj.blockNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.blockCode).text(obj.blockNameEnglish);
			options.append(option);
		}
	});

	if (data.length > 0)
		removeduplicatebLock(2);

}

function handleBlockErrorNo() {
	alert("No data found!");
}
function getBlockVillages(id) {
	// alert(id);
	lgdDwrBlockService.getBlockVillagebyBlockCodeForLocalBody(id, {
		callback : getBlockVillagesSuccess,
		errorHandler : getBlockVillagesError
	});

}
/*Facility to invalidate Block even if no village exists under it*/
var villageflag=false;


function getBlockVillagesSuccess(data) {
	// alert('hi');
	// alert(data);
	var fieldId = 'ddVillage';
	dwr.util.removeAllOptions(fieldId);
	
	/*
	 * var st = document.getElementById('ddVillage'); st.add(new Option('Please
	 * Select Block', '0'));
	 */
	if(data.length==0){
		$("#cvillage").hide();
		villageflag=true;
	}
	else{
		if($('#blockdelete_no').is(':checked')){
			$("#cvillage").show();
		}
		villageflag=false;
	}

	var options = $("#ddVillage");
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

function getBlockVillagesError() {
	alert("No data found!");
}
// @Author - Sarvapriya Malhotra
// @Modified BY: Chandan Soni
function toggledisplay(obj, val) {

	if (document.getElementById(obj).checked) {

		if (val == 'cvillage') {
			
			$("#fromothersubdistrict").hide();
			if(!villageflag){
				$("#cvillage").show();
			}
		}
		/*if(villageflag){
			$("#cvillage").hide();
		}*/
		if (val == 'fromothersubdistrict') {
			$("#fromothersubdistrict").show();
			$("#cvillage").hide();
		}
	} else {
		$("#cvillage").show();
		$("#fromothersubdistrict").hide();
	}

}
function addItem(list1, list2, val, doAddVal) {
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
/*
 * function addItem(list1,list2,val,doAddVal) { alert("chek1");
 * if(document.getElementById(list2).selectedIndex>=0) { for (var j = 0; j <
 * document.getElementById(list2).options.length; j++) if
 * (document.getElementById(list2).options[j].selected==true) if (doAddVal)
 * $('#' + list1).append("<option value=" +
 * document.getElementById(list2)[j].value + val + ">" +
 * document.getElementById(list2)[j].innerText + " (" + val + ")</option>");
 * else $('#' + list1).append("<option value=" +
 * document.getElementById(list2)[j].value + " >" +
 * document.getElementById(list2)[j].innerText + "</option>");
 * removeSelectedValue(list2); } }
 */
function removeAll(list1, list2, doRemoveVal) {
	for ( var i = document.getElementById(list1).length - 1; i >= 0; i--) {
		document.getElementById(list1).selectedIndex = i;

		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 4)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText.length - 6) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText + "</option>");
		removeSelectedValue(list1);
	}
}
function removeItem(list1, list2, doRemoveVal) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 4)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText.length - 6) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText + "</option>");
		removeSelectedValue(list1);
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

function validateAll() {
	var msg = null;
	if (!validateDistrict()) {
		if (msg != null) {
			msg = msg + "Please select  District " + '\n';
		} else {
			msg = "Please District " + '\n';
		}
	}
}
function validateDistrict() {
	var dddistrict = document.getElementById('ddDistrict');

	if (dddistrict.value == "") {
		$("#ddDestDistrict_error").show();
		return false;
	}
	$("#ddDestDistrict_error").hide();
	return true;

}

/*
 * function generateTempView() { alert("ss"); if
 * (document.getElementById('contributedVillages').length==0) return false;
 * 
 * document.getElementById('dynamicTbl').style.visibility="visible";
 * document.getElementById('dynamicTbl').style.display="block"; var tbl =
 * document.getElementById('dynamicTbl'); var row =
 * tbl.insertRow(tbl.rows.length); row.className += "tblRowB"; var colCount =
 * tbl.rows[0].cells.length;
 * 
 * for(var i=0; i<colCount; i++) { var newcell = row.insertCell(i); if (i==0)
 * newcell.innerHTML = '<strong>' +
 * document.getElementById('ddTargetBlockListNo')[document.getElementById('ddTargetBlockListNo').selectedIndex].innerText + '</strong>';
 * else newcell.innerHTML =
 * document.getElementById('contributedVillages')[0].innerText; }
 * 
 * for(var i=1; i<document.getElementById('contributedVillages').length; i++) {
 * row = tbl.insertRow(tbl.rows.length); row.className += "tblRowB"; for (var j =
 * 0; j<colCount; j++) { var newcell = row.insertCell(j);
 * 
 * if (j==1) newcell.innerHTML =
 * document.getElementById('contributedVillages')[i].innerText; } }
 * 
 * row = tbl.insertRow(tbl.rows.length); row.className += "tblRowB";
 * 
 * for(var i=0; i<colCount; i++) { var newcell = row.insertCell(i);
 * newcell.innerHTML = '<br />'; }
 * 
 * removeSelectedValue('ddTargetBlockListNo');
 * removeEverything('contributedVillages'); }
 */
function generateTempView() {

	document.getElementById('dynamicTbl').style.visibility = "visible";
	document.getElementById('dynamicTbl').style.display = "block";
	var tbl = document.getElementById('dynamicTbl');
	var row = tbl.insertRow(tbl.rows.length);
	row.className += "tblRowB";
	var colCount = tbl.rows[0].cells.length;
	var s = 0;
	var t = 0;

	for ( var i = 0; i < colCount; i++) {
		var newcell = row.insertCell(i);
		if (i == 0)
			newcell.innerHTML = '<strong>'
					+ document.getElementById('ddTargetBlockListNo')[document.getElementById('ddTargetBlockListNo').selectedIndex].text + '</strong>';
		else {
			if (document.getElementById('contributedVillages').length > 0) {
				newcell.innerHTML = document.getElementById('contributedVillages')[0].text + " (Village)";
				s = 1;
			} else {
				newcell.innerHTML = document.getElementById('contributedBlockUlb')[0].text + " (ULB)";
				t = 1;
			}

		}

	}

	for ( var i = s; i < document.getElementById('contributedVillages').length; i++) {
		row = tbl.insertRow(tbl.rows.length);
		row.className += "tblRowB";
		for ( var j = 0; j < colCount; j++) {
			var newcell = row.insertCell(j);

			if (j == 1)
				newcell.innerHTML = document.getElementById('contributedVillages')[i].text + " (Village)";
		}
	}

	for ( var i = t; i < document.getElementById('contributedBlockUlb').length; i++) {
		row = tbl.insertRow(tbl.rows.length);
		row.className += "tblRowB";
		for ( var j = 0; j < colCount; j++) {
			var newcell = row.insertCell(j);

			if (j == 1)
				newcell.innerHTML = document.getElementById('contributedBlockUlb')[i].text + " (ULB)";
		}
	}

	row = tbl.insertRow(tbl.rows.length);
	row.className += "tblRowB";

	for ( var i = 0; i < colCount; i++) {
		var newcell = row.insertCell(i);
		newcell.innerHTML = '<br />';
	}

}
function hasMoreItems(dropdownName, buttonName) {
	var tbl = document.getElementById('dynamicTbl');
	var row = tbl.rows.length;
	if (document.getElementById(dropdownName).length == 0 && row > 1)
		document.getElementById(buttonName).disabled = true;

}
function removeEverything(val) {
	var elSel = document.getElementById(val);
	for ( var i = elSel.length - 1; i >= 0; i--)
		elSel.remove(i);
}

function removeSelectedValueDistrict(val) {
	var elSel = document.getElementById(val);
	var i;
	for (i = elSel.length - 1; i >= 1; i--) {
		if (elSel.options[i].selected) {
			elSel.remove(i);
		}
	}
}

function getselectedblockUlbData(id) {

	lgdDwrBlockService.getblockULbForLocalBody(id, {
		callback : blockUlbSuccess,
		errorHandler : getBlockVillagesError
	});

}
function blockUlbSuccess(data) {
	if (data == null)
		alert("No data found for Block Ulb's!");
	else {
		var fieldId = 'avaBlockUlb';
		dwr.util.removeAllOptions(fieldId);

		var options = $("#avaBlockUlb");
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
}
function removeduplicatebLock(val) {
	var selObj = "";
	if (val == 1)
		selObj = document.getElementById('ddTargetBlockListYes');
	else
		selObj = document.getElementById('ddTargetBlockListNo');
	var temp = document.getElementById('ddSrcBlock').value;
	for ( var j = 0; j < selObj.options.length; j++) {
		if (selObj.options[j].value == temp) {
			selObj.remove(j);
			break;
		}
	}
}

function removeBockListItem(list1, list2, doRemoveVal) {
	var lastindex = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++)
			if (document.getElementById(list1).options[j].selected == true)
				if (doRemoveVal) {
					lastindex = list1.length;
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, lastindex) + " >"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.indexOf("("))
									+ "</option>");
				} else
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
		removeSelectedValue(list1);

	}
	if ($("#childbtn")) {
		document.getElementById("childbtn").disabled = false;
	}
}