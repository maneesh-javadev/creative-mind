//DWR Dropdownlist Population

//start for merge
//for first subdistrict
/*function getSubDistrictListMerge(id) {
 lgdDwrSubDistrictService.getSubDistrictList(id, {
 callback : handleSubDistrictSuccessMerge,
 errorHandler : handleSubDistrictErrorMerge
 });

 }

 //data contains the returned value
 function handleSubDistrictSuccessMerge(data) {
 // Assigns data to result id	
 //alert(data);
 var fieldId = 'ddSubdistrictMerge';
 var valueText = 'subdistrictCode';
 var nameText = 'subdistrictNameEnglish';
 dwr.util.removeAllOptions(fieldId);
 var st = document.getElementById('ddSubdistrictMerge');
 st.add(new Option('Select Subdistrict', '0'));
 dwr.util.addOptions(fieldId, data, valueText, nameText);

 }

 function handleSubDistrictErrorMerge() {
 // Show a popup message
 alert("No data found!");
 }*/

////for village
/*function getVillageListMerge(id) {
 lgdDwrVillageService.getVillageList(id, {
 callback : handleVillageSuccessMerge,
 errorHandler : handleVillageErrorMerge
 });

 }

 //data contains the returned value
 function handleVillageSuccessMerge(data) {
 // Assigns data to result id
 var fieldId = 'villageListMainMerge';
 var valueText = 'villageCode';
 var nameText = 'villageNameEnglish';
 dwr.util.removeAllOptions(fieldId);
 dwr.util.addOptions(fieldId, data, valueText, nameText);
 }

 function handleVillageErrorMerge() {
 // Show a popup message
 alert("No data found!");
 }*/
//end for merge
/** *********************** */
// //for ULB covered landregion
/* ulb coverage */
function getSubDistrictandULBList(id) {
	getSubDistrictList(id);
	// getSubDistrictListMerge(id);

}
// for first subdistrict
function getSubDistrictList(id) {
	try {
		lgdDwrSubDistrictService.getSubDistrictListForLocalbody(id, {
			callback : handleSubDistrictSuccess,
			errorHandler : handleSubDistrictError
		});
	} catch (e) {
		alert(e);
	}

}

// data contains the returned value
function handleSubDistrictSuccess(data) {
	// Assigns data to result id
	// alert(data);
	var fieldId = 'ddSubdistrict';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddSubdistrict');
	st.add(new Option('Select Subdistrict', '0'));
	var options = $("#ddSubdistrict");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
			options.append(option);
		}
	});

}

function handleSubDistrictError() {
	// Show a popup message
	alert("No data found!");
}

function getInvalidateVillageList(type) {
	if (document.getElementById('MapVillagelistchek').checked == false && document.getElementById('UnmapVillagelistchek').checked == false) {
		$("#villageListMainContributing").empty();
		$("#villageListNewContri").empty();
	}

	if (document.getElementById('ddDistrict').value < 1) {
		alert("Please Select District");
		if (type == 1)
			document.getElementById('MapVillagelistchek').checked = false;
		else if (type == 2)
			document.getElementById('UnmapVillagelistchek').checked = false;
	} else if (document.getElementById('ddSubdistrict').value < 1) {
		alert("Please Select Sub District");
		if (type == 1)
			document.getElementById('MapVillagelistchek').checked = false;
		else if (type == 2)
			document.getElementById('UnmapVillagelistchek').checked = false;

	}

	else {
		$("#villageListMainContributing").empty();
		$("#villageListNewContri").empty();
		var slc = document.getElementById('stateCode').value;
		var dlc = document.getElementById('ddDistrict').value;
		var tlc = document.getElementById('ddSubdistrict').value;
		if (document.getElementById('MapVillagelistchek').checked == true)
			lgdDwrVillageService.getmapUnmapVillageList(slc, dlc, tlc, 1, {
				callback : handleVillageSuccess,
				errorHandler : handleVillageError
			});
		if (document.getElementById('UnmapVillagelistchek').checked == true)
			lgdDwrVillageService.getmapUnmapVillageList(slc, dlc, tlc, 2, {
				callback : handleVillageSuccess,
				errorHandler : handleVillageError
			});
	}
}

// data contains the returned value
function handleVillageSuccess(data) {

	// var fieldId2 = 'villageListMainContributing';
	var fieldId = 'villageListMainContributing';
	var options = $("#villageListMainContributing");
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

// @Author - Sarvapriya Malhotra
// @Modified BY: Chandan Soni
/*
 * function toggledisplay(obj, val) { if (document.getElementById(obj).checked) {
 * if (val == 'cvillage') { document.getElementById('cvillage').style.visibility =
 * 'visible'; document.getElementById('cvillage').style.display = 'block';
 * document.getElementById('fromothersubdistrict').style.visibility = 'hidden';
 * document.getElementById('fromothersubdistrict').style.display = 'none'; } if
 * (val == 'fromothersubdistrict') {
 * document.getElementById('fromothersubdistrict').style.visibility = 'visible';
 * document.getElementById('fromothersubdistrict').style.display = 'block';
 * document.getElementById('cvillage').style.visibility = 'hidden';
 * document.getElementById('cvillage').style.display = 'none'; } } else {
 * document.getElementById('cvillage').style.visibility = 'visible';
 * document.getElementById('cvillage').style.display = 'block';
 * document.getElementById('fromothersubdistrict').style.visibility = 'hidden';
 * document.getElementById('fromothersubdistrict').style.display = 'none'; } }
 */

/**
 * list1 - list from which to add values from as string i.e id name list2 - list
 * to which to add values from as string i.e id name val - value to append at
 * the end Note: doesn't matter what is it if doAddVal=false doAddVal - can be
 * either true or false...If True val is appended at the end if false val is not
 * appeneded
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
function addItemForInvalidate(list1, list2, val, doAddVal) {
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
function removeItem(list1, list2, doRemoveVal) {
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

function EmptlyVillageList() {
	$("#villageListMainContributing").empty();
	$("#villageListNewContri").empty();
	document.getElementById('MapVillagelistchek').checked = false;
	document.getElementById('UnmapVillagelistchek').checked = false;
}

function getunmapViltoUlb() {
	if (document.getElementById('ddDistrict').value < 1) {
		alert("Please Select District");
		return false;
	}
	$("#villageListMainContributing").empty();
	$("#villageListNewContri").empty();
	var slc = document.getElementById('stateCode').value;
	var dlc = document.getElementById('ddDistrict').value;
	var tlc = document.getElementById('ddSubdistrict').value;
	lgdDwrVillageService.getmapUnmapVillageList(slc, dlc, tlc, 2, {
		callback : handleVillageSuccess,
		errorHandler : handleVillageError
	});

}

function getULBListbyLBtype(id) {
	var slc = document.getElementById('stateCode').value;
	var id = document.getElementById('ddLgdLBType').value;
	var lbtype = id.split(":");
	var val = lbtype[0];
	$("#ULBListNewContri").empty();
	if (slc > 0) {
		lgdDwrlocalBodyService.getUrbanLocalBodyList(slc, val, {
			callback : handleULBSuccess1,
			errorHandler : handleULBError1
		});
	}
}

function handleULBSuccess1(data) {

	var fieldId = 'ULBListMainContributing';
	dwr.util.removeAllOptions(fieldId);

	var options = $("#ULBListMainContributing");
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

function handleULBError1() {
	alert("No data found!");
}

function addItemSinglevalidation(val, type) {
	var len = document.getElementById('ULBListNewContri').length;
	var length = 0;

	for ( var j = 0; j < document.getElementById('ULBListMainContributing').options.length; j++)
		if (document.getElementById('ULBListMainContributing').options[j].selected == true)
			length++;
	len = len + length;
	if (len > 1)
		alert("Only Single ULb Can be Selected");
	else
		addItemForInvalidate(val, type, '', false);

}

/**
 * Add by ripunj for invalidate draft mode 
 * Modified by Pooja
 * @param invalidateVillageList
 */

function getInvalidateVillageListDraft(invalidateVillageList) {
	
	if (document.getElementById('ddDistrict').value < 1) {
		alert("Please Select District");
	} else if (document.getElementById('ddSubdistrict').value < 1) {
		alert("Please Select Sub District");
	}

	else {
		$("#villageListMainContributing").empty();
		$("#villageListNewContri").empty();
		var invalidateVillage = invalidateVillageList.split(",");
		lgdDwrVillageService.isMappedOrUnmapped(invalidateVillage,{
		callback : handleIsMappedOrUnmappedSuccess,
		errorHandler : handleVillageError
			});
	}
		
}

function handleIsMappedOrUnmappedSuccess(data){
	var slc = document.getElementById('stateCode').value;
	var dlc = document.getElementById('ddDistrict').value;
	var tlc = document.getElementById('ddSubdistrict').value;
	var draftVilCode = document.getElementById('draftVilCode').value;
	
	if(data[0]){
		document.getElementById('MapVillagelistchek').checked = true;
		lgdDwrVillageService.getmapUnmapVillageListInvalidateDraftMode(slc, dlc, tlc, 1, draftVilCode, {
			callback : handleVillageSuccessDraft,
			errorHandler : handleVillageError
		});	
	}
	if(data[1]){
		document.getElementById('UnmapVillagelistchek').checked = true;
		lgdDwrVillageService.getmapUnmapVillageListInvalidateDraftMode(slc, dlc, tlc, 2, draftVilCode, {
			callback : handleVillageSuccessDraft,
			errorHandler : handleVillageError
		});
	}
	
}
function getInvalidateVillageDraftList(type) {
	if (document.getElementById('MapVillagelistchek').checked == false && document.getElementById('UnmapVillagelistchek').checked == false) {
		$("#villageListMainContributing").empty();
		$("#villageListNewContri").empty();
	}

	if (document.getElementById('ddDistrict').value < 1) {
		alert("Please Select District");
		if (type == 1)
			document.getElementById('MapVillagelistchek').checked = false;
		else if (type == 2)
			document.getElementById('UnmapVillagelistchek').checked = false;
	} else if (document.getElementById('ddSubdistrict').value < 1) {
		alert("Please Select Sub District");
		if (type == 1)
			document.getElementById('MapVillagelistchek').checked = false;
		else if (type == 2)
			document.getElementById('UnmapVillagelistchek').checked = false;

	}

	else {
		$("#villageListMainContributing").empty();
		$("#villageListNewContri").empty();
		var slc = document.getElementById('stateCode').value;
		var dlc = document.getElementById('ddDistrict').value;
		var tlc = document.getElementById('ddSubdistrict').value;
		var draftVilCode = document.getElementById('draftVilCode').value;
		if (document.getElementById('MapVillagelistchek').checked == true)
			lgdDwrVillageService.getmapUnmapVillageListInvalidateDraftMode(slc, dlc, tlc, 1, draftVilCode, {
				callback : handleVillageSuccessDraft,
				errorHandler : handleVillageError
			});
		if (document.getElementById('UnmapVillagelistchek').checked == true)
		lgdDwrVillageService.getmapUnmapVillageListInvalidateDraftMode(slc, dlc, tlc, 2, draftVilCode, {
			callback : handleVillageSuccessDraft,
			errorHandler : handleVillageError
		});
	}
}
function handleVillageSuccessDraft(data) {
	var options = $("#villageListMainContributing");
	var options2 = $("#villageListNewContri");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F' && obj.opeartion_state == 'U') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.villageCode).text(obj.villageNameEnglish + " : used in draft mode");
			options.append(option);
		} else {
			if (obj.opeartion_state == 'I') {
				$(option).val(obj.villageCode).text(obj.villageNameEnglish);
				options2.append(option);
			} else if (obj.opeartion_state == 'U') {
				$(option).val(obj.villageCode).text(obj.villageNameEnglish);
				options.append(option);
			}
		}
	});
}