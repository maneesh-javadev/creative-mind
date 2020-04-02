/*@Author - Ram Krishna Chauhan */

var i = 4;
var dynstart = 0;
var dynend = 0;
var l = 1;

function formSubmitAddPreview() {
	document.getElementById('addAnotherBlock').value = "";
	document.getElementById('addAnotherBlock').value = "true";
	formSubmit();
}

function formSubmitSave() {
	document.getElementById('addAnotherBlock').value = "";
	document.getElementById('addAnotherBlock').value = "Save";
	formSubmit();
}
function validation() {
var flag = document.getElementById('flag1').value;
			if ((flag == 1)
					&& (document.getElementById('ddDistrict').selectedIndex == 0 || document.getElementById('ddDistrict')[document
							.getElementById('ddDistrict').selectedIndex].innerText == 'Select District')) {
				alert("Kindly Select the District from the list. !");
			} else if (document.getElementById('OfficialAddress').value == "") {
				alert("Please Enter Block Name. !");
			} else if (document.getElementById('blockListNew').options.length == 0 && document.getElementById('ULBListNew').options.length == 0
					&& document.getElementById('VilListNew').options.length == 0) {
				alert("Kindly Select One or More Contributing Landregions. !");
			} else if (document.getElementById('blockListNew').options.length == 1 && (document.getElementById('blockListNew').value.match('FULL')) == "FULL") {
				alert("You Can't Make the New Block by Shifting Only One Full Block. Kindly Select One More Contributing Block.  !");
			}

			else {
				document.getElementById("Save").disabled = true;
				document.forms['newblockform'].submit();
			}
}

function reorganizeYes() {
	if (document.getElementById('Yes').checked = true) {
		document.getElementById('Submit').style.visibility = 'hidden';
		document.getElementById('Proceed').style.visibility = 'visible';
	}
}
function reorganizeNo() {
	if (document.getElementById('No').checked = true) {
		document.getElementById('Submit').style.visibility = 'visible';
		document.getElementById('Proceed').style.visibility = 'hidden';
	}
}
function formSubmit() {
	var selObj = document.getElementById('blockListNew');
	var selObj2 = document.getElementById('villageListNew');
	var selObj3 = document.getElementById('VilListNew');
	var selObj4 = document.getElementById('ULBListNew');
	var selObj5 = document.getElementById('blockulblist');

	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}
	for ( var i = 0; i < selObj2.options.length; i++) {
		selObj2.options[i].selected = true;
	}
	for ( var i = 0; i < selObj3.options.length; i++) {
		selObj3.options[i].selected = true;
	}

	for ( var i = 0; i < selObj4.options.length; i++) {
		selObj4.options[i].selected = true;
	}
	for ( var i = 0; i < selObj5.options.length; i++) {
		selObj5.options[i].selected = true;
	}
	validation();
	// document.forms['newblockform'].submit();
}

function contriOption() {
	var selObj = document.getElementById('blockListNew');
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}
	if (document.getElementById('contriBlock').checked == false) {
		var optionValue = null;
		for ( var i = 0; i < selObj.options.length; i++) {
			optionValue += selObj.options[i].value;
		}
		if (optionValue.match('PART') != "PART") {
			alert("To Rename Contributing Blocks, Kindly Select At Least One Part Block. !");
			document.getElementById('Proceed').style.visibility = false;
		} else {
			document.getElementById('Proceed').style.visibility = 'visible';
		}
	}
}

// for first subdistrict
function getBlockList() {

	var id = document.getElementById('ddDistrict').value;
	if (document.getElementById('Blocklistchek').checked) {
		if (id > 0) {

			lgdDwrBlockService.getBlockList(id, {
				callback : handleBlockSuccess,
				errorHandler : handleBlockError
			});

		} else {
			alert("Please Select District.");
			document.getElementById('Blocklistchek').checked = false;
		}
	} else {
		$("#ddBlock").empty();
		$("#blockListNew").empty();
		$("#ulbListsofblock").empty();
		$("#blockulblist").empty();
		$("#villageList").empty();
		$("#villageListNew").empty();

	}

}

// data contains the returned value
function handleBlockSuccess(data) {
	if (data == '') {
		alert("No data found!");
		document.getElementById('Blocklistchek').checked = false;
	}
	var fieldId = 'ddBlock';
	dwr.util.removeAllOptions(fieldId);

	var options = $("#ddBlock");
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

function handleBlockError() {
	// Show a popup message
	alert("No data found!");
}

// //for village
function getVillageList(id) {
	if (id.match('PART') == 'PART') {

		var refinedId = id.substring(0, (id.length) - 4);
		lgdDwrVillageService.getBlockVillagebyBlockCode(refinedId, {
			callback : handleVillageSuccess,
			errorHandler : handleVillageError
		});

	} else {
		dwr.util.removeAllOptions('villageList');
		alert("Kindly Select the Part Block From the List. !");
	}

}
// data contains the returned value
function handleVillageSuccess(data) {
	var fieldId = 'villageList';
	var valueText = 'villageCode'; // temp. holding VillCode
	var nameText = 'villageNameEnglish'; // temp. holding VillName
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleVillageError() {
	// Show a popup message
	alert("No data found!");
}

// @Author - Sarvapriya Malhotra
function toggledisplay(obj, val) {
	if (document.getElementById(obj).checked) {

		if (val == 'cvillage') {
			document.getElementById('cvillage').style.visibility = 'visible';
			document.getElementById('csurvey').style.visibility = 'visible';
		}
		if (val == 'culb') {
			document.getElementById('culb').style.visibility = 'visible';
		}
	} else {
		if (val == 'cvillage') {
			document.getElementById('cvillage').style.visibility = 'hidden';
			document.getElementById('csurvey').style.visibility = 'hidden';
		}
		if (val == 'culb') {
			document.getElementById('culb').style.visibility = 'hidden';
		}
	}
}

/**
 * list1 - list from which to add values from as string i.e id name list2 - list
 * to which to add values from as string i.e id name val - value to append at
 * the end Note: doesn't matter what is it if doAddVal=false doAddVal - can be
 * either true or false...If True val is appended at the end if false val is not
 * appeneded
 */
/*
 * function addItem(list1,list2,val,doAddVal) {
 * if(document.getElementById(list2).selectedIndex>=0) { if (doAddVal) $('#' +
 * list1).append("<option value=" +
 * document.getElementById(list2)[document.getElementById(list2).selectedIndex].value +
 * val + ">" +
 * document.getElementById(list2)[document.getElementById(list2).selectedIndex].innerText + " (" +
 * val + ")</option>"); else $('#' + list1).append("<option value=" +
 * document.getElementById(list2)[document.getElementById(list2).selectedIndex].value + " >" +
 * document.getElementById(list2)[document.getElementById(list2).selectedIndex].innerText + "</option>");
 * removeSelectedValue(list2); } }
 */
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
function removeAll(list1, list2, doRemoveVal) {
	for ( var i = document.getElementById(list1).length - 1; i >= 0; i--) {
		document.getElementById(list1).selectedIndex = i;

		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 4)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length - 6) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + "</option>");
		removeSelectedValue(list1);
	}
}

function getSurverNumbers(list1) {
	if (document.getElementById(list1).value.substr(document.getElementById(list1).value.length - 4) == "FULL") {
		alert("You Can Only Select Survey Numbers Of \"(Part)\" Villages");
	} else if (document.getElementById(list1).value.substr(document.getElementById(list1).value.length - 4) == "PART") {
		// TODO: CALL DWR HERE
	}
}

function removesingleItem(list1, list2, doRemoveVal) {
	document.getElementById('partSubdistrict').disabled = false;
	if (document.getElementById(list1).selectedIndex >= 0) {
		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 4)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length - 6) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + "</option>");
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
// for create village validation by vanisha

// to display help text
function show_msg(Field_msg) {
	var hint = '#' + Field_msg + "_msg";
	var error = '#' + Field_msg + "_error";
	$("#" + Field_msg).removeClass("error_fld");
	$("#" + Field_msg).removeClass("error_msg");
	$(hint).show();
	$(error).hide();

}
// OfficialAddress1
function officialAddress() {

	if (document.getElementById("OfficialAddress").value == "") {
		document.getElementById("OfficialAddress_error").innerHTML = "Village Name in English is Required";
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

// /modify village option by vanisha
function toggledisplay2(obj, val) {
	if (document.getElementById(obj).checked) {

		if (val == 'correctionvillage') {
			document.getElementById('correctionvillage').style.visibility = 'visible';

		}
		if (val == 'changevillage') {
			document.getElementById('changevillage').style.visibility = 'visible';
		}

	} else {
		if (val == 'changevillage') {
			document.getElementById('correctionvillage').style.visibility = 'hidden';

		}
		if (val == 'correctionvillage') {
			document.getElementById('changevillage').style.visibility = 'hidden';
		}

	}
}

function addgisnodes() {
	var tmptextLati = new Array();
	var tempchkLongi = new Array();

	if (dynstart == 0) {
		dynstart = i;
	}
	dynend = i;

	for ( var j = dynstart; j < dynend; j++) {
		if (document.getElementById("lati" + j)) {
			if (document.getElementById("lati" + j) != null && document.getElementById("longi" + j) != null)
				tmptextLati[j] = document.getElementById("lati" + j).value;
			tempchkLongi[j] = document.getElementById("longi" + j).value;
		}
	}

	document.getElementById("addgisnodes").innerHTML += "<div id=div"
			+ i
			+ "><td align='left'>"
			+ document.getElementById('lbllatitude').innerHTML
			+ "</td><td width='75'>&nbsp;&nbsp;<input type='text' name='lati' id='lati"
			+ i
			+ "' class='frmfield'></td><td width='145'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td width='150'>"
			+ document.getElementById('lbllongitude').innerHTML + "</td><td width='75'>&nbsp;&nbsp;<input type='text' name='longi' id='longi" + i
			+ "' class='frmfield'></td><td><input type='button' value='Remove' onclick='div" + i + ".parentNode.removeChild(div" + i
			+ ")'/></td></div>";

	for ( var j = dynstart; j < dynend; j++) {
		if (document.getElementById("lati" + j)) {
			if (document.getElementById("lati" + j) != null && document.getElementById("longi" + j) != null)
				document.getElementById("lati" + j).value = tmptextLati[j];
			document.getElementById("longi" + j).value = tempchkLongi[j];
		}
	}
	i++;
}

function addsurveys() {
	document.getElementById("addsurveys").innerHTML += "<div width='100%'><input type='text' name='survey_number'><input type='button' value='Remove' onclick='this.parentNode.parentNode.removeChild(this.parentNode)'></div>";
}

// modify village option by vanisha
function toggledisplay2(obj, val) {

	if (document.getElementById(obj).checked) {

		if (val == 'correctionvillage') {
			document.getElementById('correctionvillage').style.visibility = 'visible';
			document.getElementById('changevillage').style.visibility = 'hidden';

		}
		if (val == 'changevillage') {
			document.getElementById('changevillage').style.visibility = 'visible';
			document.getElementById('correctionvillage').style.visibility = 'hidden';
		}

	}
}

function addItems(list1, list2, val, doAddVal) {
	document.getElementById('partSubdistrict').disabled = false;
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

function addSelectedItems(list1, list2, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++)
			if (document.getElementById(list2).options[j].selected == true)
				if (doAddVal)
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + ">" + document.getElementById(list2)[j].text + " </option>");
				else
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");
		removeSelectedValue(list2);
	}
}

function removeAlls(list1, list2, doRemoveVal) {
	$("#villageList").empty();
	$("#villageListNew").empty();
	$("#ulbListsofblock").empty();
	$("#blockulblist").empty();
	document.getElementById('partSubdistrict').disabled = false;
	for ( var i = document.getElementById(list1).length - 1; i >= 0; i--) {
		document.getElementById(list1).selectedIndex = i;

		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 4)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length - 6) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + "</option>");
		removeSelectedValue(list1);
	}
}

function getVillageLists() {
	document.getElementById('partSubdistrict').disabled = true;
	$("#villageList").empty();
	$("#villageListNew").empty();
	$("#ulbListsofblock").empty();
	$("#blockulblist").empty();
	selObj = document.getElementById('blockListNew');
	var temp = "";
	var blockId = "";
	var count = 0;
	for ( var i = 0; i < selObj.options.length; i++) {
		if (i == 0)
			$("#villageList").empty();
		if ((selObj.options[i].value).substr(selObj.options[i].value.length - 4) == "PART") {
			temp = selObj.options[i].value;
			temp = temp.substr(0, temp.length - 4);
			blockId = temp + "," + blockId;
			
			count++;
		}
	}
	var len=blockId.length;
	if(len>0){
		var blockids=blockId.substr(0,len-1)
		lgdDwrVillageService.getVillageByBlockListForLocalBody(blockids, {
		callback : handleVillageSuccess,
		errorHandler : handleVillageError
	});
		
	}else{
		alert("Please Select part Block ");
		return false;
	}
	
	
	if (count == 0) {
		alert("Please Select part Block ");
		return false;
	} else {
		blockId = blockId.substring(0, blockId.length - 1);
		getselectedblockUlbs(blockId);
	}

}

function handleVillageSuccess(data) {
	// Assigns data to result id

	var fieldId = 'villageList';
	dwr.util.removeAllOptions(fieldId);
	var options = $("#villageList");
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

function handleVillageError() {
	// Show a popup message
	alert("No data found!");
}

function getunmapvillageList(id) {
	var s = document.getElementById('ddDistrict').value;
	if (document.getElementById('Villagelistchek').checked) {
		if (s > 0) {
			lgdDwrBlockService.getUnmapVillagesForLocalBody(s, {
				callback : handleunmapVilSuccess,
				errorHandler : handleunmapVilError
			});
		} else {
			alert("Please Select District.");
			document.getElementById('Villagelistchek').checked = false;
		}
	} else {
		$("#vilunmapbblocks").empty();
		$("#VilListNew").empty();
	}

}
function handleunmapVilSuccess(data) {
	// Assigns data to result id
	if (data == '') {
		alert("No data found!");
		document.getElementById('Villagelistchek').checked = false;
	}
	var fieldId = 'vilunmapbblocks';
	dwr.util.removeAllOptions(fieldId);

	var options = $("#vilunmapbblocks");
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

function handleunmapVilError() {
	// Show a popup message
	alert("No data found!");
	document.getElementById('Villagelistchek').checked = false;
}

function getunmapULBList(id) {
	var s = document.getElementById('ddDistrict').value;
	if (document.getElementById('Ulblistchek').checked) {
		if (s > 0) {

			lgdDwrBlockService.getUlbbyDistrictCode(s, {
				callback : unmapULBListSuccess,
				errorHandler : handleunmapVilError
			});

		} else {
			alert("Please Select District.");
			document.getElementById('Ulblistchek').checked = false;
		}
	} else {
		$("#ulbunmapbblocks").empty();
		$("#ULBListNew").empty();
	}

}
function unmapULBListSuccess(data) {
	if (data == '') {
		alert("No data found!");
		document.getElementById('Ulblistchek').checked = false;
	}
	var fieldId = 'ulbunmapbblocks';
	dwr.util.removeAllOptions(fieldId);

	var options = $("#ulbunmapbblocks");
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
function getselectedblockUlbs(id) {

	lgdDwrBlockService.getblockULbForLocalBody(id, {
		callback : blockUlbSuccess,
		errorHandler : handleunmapVilError
	});

}
function blockUlbSuccess(data) {
	if (data == null)
		alert("No data found for Block Ulb's!");
	else {
		var fieldId = 'ulbListsofblock';
		dwr.util.removeAllOptions(fieldId);

		var options = $("#ulbListsofblock");
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

function clearalllist() {
	$("#ddBlock").empty();
	$("#blockListNew").empty();
	$("#ulbListsofblock").empty();
	$("#blockulblist").empty();
	$("#villageList").empty();
	$("#villageListNew").empty();
	$("#ulbunmapbblocks").empty();
	$("#ULBListNew").empty();
	$("#vilunmapbblocks").empty();
	$("#VilListNew").empty();
	document.getElementById('Ulblistchek').checked = false;
	document.getElementById('Villagelistchek').checked = false;
	document.getElementById('Blocklistchek').checked = false;
}
function blocknameVal(blkname)

{
	var disId = document.getElementById("ddDistrict").value;
	if (disId > 0) {
		lgdDwrBlockService.BlockExist(disId, blkname, {
			callback : handleUniqueBlkSuccess,
			errorHandler : handleUniqueBlkError
		});
	}
}

function handleUniqueBlkSuccess(data) {
	document.getElementById("UniqueBlockError").innerHTML = "";
	if (!data) {
		document.getElementById("UniqueBlockError").innerHTML = "<font color='red'><br>Block Name Must Be Unique for Same District </font>";

		document.getElementById("OfficialAddress").value = "";
		document.getElementById("OfficialAddress").focus();

	}

}

function handleUniqueBlkError() {
	document.getElementById("OfficialAddress").value = "";
	document.getElementById("OfficialAddress").focus();
}

function chekcalphanumeric(inputtxt, num) {
	var status = true;
	if (inputtxt.match(/\S/)) {
		var obj = "";
		if (num == 1)
			obj = document.getElementById('OfficialAddress');
		else if (num == 2)
			obj = document.getElementById('aliasEnglish');
		else if (num == 3)
			obj = document.getElementById('ssCode');
		var letterNumber = "";

		var retVal = false;
		if (num == 1 || num == 2) {
			inputtxt = inputtxt.replace(/\s+/g, '');
			letterNumber = /^[0-9a-zA-Z.-]+$/;
		} else if (num == 3)
			letterNumber = /^[0-9a-zA-Z]+$/;
		if (inputtxt.match(letterNumber))
			retVal = true;
		if (retVal == false) {
			if (num == 1)
				alert("Block Name contains invalid characters Use AlphaNumerics!");
			else if (num == 2)
				alert("Block Alias Name contains invalid characters Use AlphaNumerics!");
			else if (num == 3)
				alert("State Speficic Code contains invalid characters Use AlphaNumerics!");
			obj.value = "";
			status = false;
		}
	}
	return stautus;
}

function clearblocksulbbvil() {
	selObj = document.getElementById('blockListNew');
	for ( var i = 0; i < selObj.options.length; i++) {
		if ((selObj.options[i].value).substr(selObj.options[i].value.length - 4) == "PART") {
			$("#villageList").empty();
			$("#villageListNew").empty();
			$("#ulbListsofblock").empty();
			$("#blockulblist").empty();
			break;
		}
	}
}

function validatelocalCharforBLKValues(value, num) {
	var status = true;
	var obj = "";
	if (num == 1)
		obj = document.getElementById('blockNameLocal');
	else if (num == 2)
		obj = document.getElementById('aliasLocal');
	var retVal = true;
	retVal = validateSpecialCharacters(value);
	if (retVal == false) {
		if (num == 1)
			alert("Blocak Name in Local Language contains invalid characters!");
		else if (num == 2)
			alert("Block Alias  Name in Local Language contains invalid characters!");
		obj.value = "";
		status = false;
	}

	return stautus;
}

function checkthenAdd() {
	var selObj = document.getElementById('villageList');
	var temp = "";
	var avacount = 0;
	var selcount = 0;
	var val = "";
	var check = true;
	for ( var i = 0; i < selObj.options.length; i++) {
		avacount = 0;
		selcount = 0;
		if (selObj.options[i].selected) {
			temp = selObj.options[i].text;
			var pos = temp.indexOf("[");
			temp = temp.substr(pos, temp.length);
			for ( var j = 0; j < selObj.options.length; j++) {
				val = selObj.options[j].text;
				if (val.indexOf(temp) > -1)
					avacount++;
			}
			for ( var k = 0; k < selObj.options.length; k++) {
				if (selObj.options[k].selected) {
					val = selObj.options[k].text;
					if (val.indexOf(temp) > -1)
						selcount++;
				}
			}
			if (selcount == avacount) {
				check = false;
				break;
			}
		}
	}
	if (check)
		addItems('villageListNew', 'villageList', 'FULL', true);
	else
		alert("You can not select all Villages of part Block");
}

addItemList=function(selListId, coverageListId) {
	
	$('#'+selListId+'> option:selected').appendTo('#'+coverageListId); 
	sortListBox(coverageListId);
	};
	
	sortListBox=function(id){
		 var $r = $("#"+id+" option");
	     $r.sort(function(a, b) {
	         if (a.text < b.text) return -1;
	         if (a.text == b.text) return 0;
	         return 1;
	     });
	     $($r).remove();
	     $("#"+id).append($($r));
	     
	};
	
