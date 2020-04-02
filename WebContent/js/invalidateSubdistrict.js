function setSessionValue(val1, val2, val3) {
	lgdDwrSubDistrictService.invalidateLoop(val1, val2, val3, {
		callback : handleInvalidateLoop,
		errorHandler : handleInvalidateLoopError
	});

}
function handleInvalidateLoop(data) {
	if (data) {

	}

}

function handleInvalidateLoopError() {
	alert("No data found!");
}

/*Modified by kirandeep for Invalidate Subdistrict on 16/03/2015 */
function validateSelectAddMore() {
	var ddSubdistrictMergeNo = document.getElementById('ddSubdistrictMergeNo').value;
	if (ddSubdistrictMergeNo == 0 || ddSubdistrictMergeNo == "") {
		alert("Please Select Target Subdistrict to invalidate");
		document.getElementById('ddSubdistrictMergeNo').focus();
		return false;
	}

	selectall();
	if (validateform()) {
		setSessionValue(document.getElementById('ddSubdistrictMergeNo').value, gelSelectedVillages('villageListNewContri'));
	}
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

	var ddDistrict = document.getElementById('ddDistrict').value;
	var ddSubdistrict = document.getElementById('ddSubdistrict').value;
	var ddSubdistrictMergeYes = document.getElementById('ddSubdistrictMergeYes').value;

	if (ddDistrict == 0) {
		alert("Please Select District");
		document.getElementById('ddDistrict').focus();
		return false;
	}
	if (ddSubdistrict == 0 || ddSubdistrict == "") {
		alert("Please Select Subdistrict to invalidate");
		document.getElementById('ddSubdistrict').focus();
		return false;
	}
   
	var c = document.getElementById('hiddenid').value;

	if (c == 1) {

		var mylist = document.getElementById("ddSubdistrict");
		
		BootstrapDialog.show({
	         title: 'Confirm Box',
	         message: "Do you want to invalidate all the Villages of " + mylist.options[mylist.selectedIndex].text + " SubDistrict",
	         buttons: [{
	             label: 'Yes',
	             action: function(dialog) {
	            	 BootstrapDialog.show({
	        	         title: 'Confirm Box',
	        	         message: "Are You Sure?",
	        	         buttons: [{
	        	             label: 'Confirm',
	        	             action: function(dialog) {
	        	            	 $('#flagSubDistrictInvalid').val("-1");
									if (document.getElementById('subdistrictdelete_no').checked) {
										if (document.getElementById('villageListMainContributing').length > 0) {
											alert('Please Select remaining villages');
											return false;
										} else if (document.getElementById('villageListNewContri').length > 0) {
											alert('Please Choose Villages to merge with Subdistrict ');
											return false;
										}

									}

									$('input[name=Submit]').prop('disabled', true);
									document.forms['invalidateForm'].submit();
	        	    				 dialog.close();
	        	             }
	        	         }, {
	        	             label: 'Cancel',
	        	             action: function(dialog) {
	        	            	 $('#id1').show();
									$('#flagSubDistrictInvalid').val("1");
									// var
									// checkboxvalue=document.getElementById('subdistrictdelete_yes').value
									$("#subdistrictdelete_yes").prop('checked', true);

									toggledisplay('subdistrictdelete_yes', 'fromothersubdistrict');

									$('#fromothersubdistrict').show();
									document.getElementById('hiddenid').value = 3;
									var invalidateSoursesubdist = $('#ddSubdistrict');
									getVillageList(invalidateSoursesubdist.val());
									removeItemFromOtherDropdowns(document.getElementById('ddSubdistrict'));
									dialog.close();
									return false;
									
	        	             }
	        	         }]
	        	     });
	             }
	         }, {
	             label: 'NO',
	             action: function(dialog) {
	            	 $('#id1').show();
						$('#flagSubDistrictInvalid').val("1");
						// var
						// checkboxvalue=document.getElementById('subdistrictdelete_yes').value
						$("#subdistrictdelete_yes").prop('checked', true);

						toggledisplay('subdistrictdelete_yes', 'fromothersubdistrict');

						$('#fromothersubdistrict').show();
						document.getElementById('hiddenid').value = 3;
						var invalidateSoursesubdist = $('#ddSubdistrict');
						getVillageList(invalidateSoursesubdist.val());
						removeItemFromOtherDropdowns(document.getElementById('ddSubdistrict'));
						 dialog.close();
						return false;
	            	
	             }
	         }]
	     });
		
		/*$("#dialog-confirm").html("Do you want to invalidate all the Villages of " + mylist.options[mylist.selectedIndex].text + " SubDistrict");
		$("#dialog-confirm").dialog({
			resizable : false,
			modal : true,
			title : "Confirm Box",
			height : 250,
			width : 400,
			buttons : {
				"Yes" : function() {
					$("#dialog-confirm").html("Are You Sure?");
					$("#dialog-confirm").dialog({
						resizable : false,
						modal : true,
						title : 'Confirm Box',
						height : 250,
						width : 400,
						buttons : {
							"Confirm" : function() {
								$('#flagSubDistrictInvalid').val("-1");
								if (document.getElementById('subdistrictdelete_no').checked) {
									if (document.getElementById('villageListMainContributing').length > 0) {
										alert('Please Select remaining villages');
										return false;
									} else if (document.getElementById('villageListNewContri').length > 0) {
										alert('Please Choose Villages to merge with Subdistrict ');
										return false;
									}

								}

								$('input[name=Submit]').prop('disabled', true);
								document.forms['invalidateForm'].submit();

							},
							"Cancel" : function() {
								$('#id1').show();
								$('#flagSubDistrictInvalid').val("1");
								// var
								// checkboxvalue=document.getElementById('subdistrictdelete_yes').value
								$("#subdistrictdelete_yes").prop('checked', true);

								toggledisplay('subdistrictdelete_yes', 'fromothersubdistrict');

								$('#fromothersubdistrict').show();
								document.getElementById('hiddenid').value = 3;
								var invalidateSoursesubdist = $('#ddSubdistrict');
								getVillageList(invalidateSoursesubdist.val());
								removeItemFromOtherDropdowns(document.getElementById('ddSubdistrict'));
								$(this).dialog('close');
								return false;

							}
						}
					});

				},
				"No" : function() {
					$('#id1').show();
					$('#flagSubDistrictInvalid').val("1");
					// var
					// checkboxvalue=document.getElementById('subdistrictdelete_yes').value
					$("#subdistrictdelete_yes").prop('checked', true);

					toggledisplay('subdistrictdelete_yes', 'fromothersubdistrict');

					$('#fromothersubdistrict').show();
					document.getElementById('hiddenid').value = 3;
					var invalidateSoursesubdist = $('#ddSubdistrict');
					getVillageList(invalidateSoursesubdist.val());
					removeItemFromOtherDropdowns(document.getElementById('ddSubdistrict'));
					$(this).dialog('close');
					return false;

				}
			}
		});*/

	}

	else {
		if (document.getElementById('hiddenid').value == 3) {
			if (document.getElementById('subdistrictdelete_yes').checked) {
				if (ddSubdistrictMergeYes == 0 || ddSubdistrictMergeYes == "") {
					alert("Please Sub-District To merge with");
					document.getElementById('ddSubdistrictMergeYes').focus();
					return false;
				}
			} else if (document.getElementById('subdistrictdelete_no').checked) {

				if (ddSubdistrictMergeNo == 0 || ddSubdistrictMergeNo == "") {

					if (!document.getElementById('chooseMoreBtn').disabled) {
						alert('Please Select the Villages to merge with target Sub-District');
						return false;
					}

					/*
					 * alert("Please Sub-District To merge with");
					 * document.getElementById('ddSubdistrictMergeNo').focus();
					 * return false; }
					 * if(document.getElementById('villageListNewContri').length==0) {
					 * alert('Please Select the Villages to merge with target
					 * Sub-District'); return false; } else
					 * if(document.getElementById('villageListMainContributing').length==0 &&
					 * document.getElementById('villageListNewContri').length==0) { //
					 * alert('Please Select the Villages to merge with target
					 * Sub-District'); return true;
					 */
				}
			}
		}
		return true;
	}

	// alert(document.getElementById('hiddenid').value);

	// return true;
}

function onSubmit() {
	selectall();
	var errors = new Array();

	var error = false;

	errors[0] = vlidateOnblur('ddDistrict', '1', '15', 'c');
	if (errors[0] == true) {
		error = true;
	}
	errors[1] = vlidateOnblur('ddSubdistrict', '1', '15', 'c');
	if (errors[1] == true) {
		error = true;
	}

	errors[2] = vlidateOnblur('ddSubdistrictMergeYes', '1', '15', 'c');
	if (errors[2] == true) {
		error = true;
	}
	if (document.getElementById('subdistrictdelete_no').checked) {
		errors[3] = vlidateOnblur('villageListNewContri', '1', '15', 'p');
		if (errors[3] == true) {
			error = true;
		}
	}
	if (error == true) {
		showClientSideError();

		return false;
	} else {

		document.forms['invalidateForm'].submit();
		return true;
	}

}

function validateSelectAndSubmit() {

	selectall();

	// var
	// ddSubdistrictMergeNo=document.getElementById('ddSubdistrictMergeNo').value;
	// alert(document.getElementById('chooseMoreBtn').disabled);

	if (validateform()) {
		if (document.getElementById('subdistrictdelete_no').checked) {
			if (document.getElementById('villageListMainContributing').length > 0) {
				alert('Please Select remaining villages');
				return false;
			} else if (document.getElementById('villageListNewContri').length > 0) {
				alert('Please Choose Villages to merge with Subdistrict ');
				return false;
			}

		}

		$('input[name=Submit]').prop('disabled', true);
		document.forms['invalidateForm'].submit();
	}
}

function selectall() {

	var selObj = document.getElementById('villageListNewContri');
	for ( var i = 0; i < selObj.options.length; i++) {

		selObj.options[i].selected = true;
	}
	/*
	 * var selObj=document.getElementById('villageListMainContributing'); for
	 * (var i = 0; i < selObj.options.length; i++) {
	 * selObj.options[i].selected=true; }
	 */
}

function removeItemFromOtherDropdowns(val) {
	var dwrSubdistrictList = document.getElementById('ddSubdistrict');

	if (val.selectedIndex > 0) {
		var selectedValue = val.value;
		var pos;

		removeEverything('ddSubdistrictMergeYes');
		removeEverything('ddSubdistrictMergeNo');

		var options = $("#ddSubdistrictMergeNo");
		var st = document.getElementById('ddSubdistrictMergeNo');
		st.add(new Option('Select Subdistrict', '0'));
		for ( var i = 0; i < dwrSubdistrictList.length; i++) {
			var option = $("<option/>");
			if (dwrSubdistrictList[i].disabled) {
				$(option).attr('disabled', 'disabled');
				$(option).val(dwrSubdistrictList[i].value).text(dwrSubdistrictList[i].text);
				options.append(option);
			} else {
				$(option).val(dwrSubdistrictList[i].value).text(dwrSubdistrictList[i].text);
				options.append(option);
			}
		}
		var options = $("#ddSubdistrictMergeYes");
		var st = document.getElementById('ddSubdistrictMergeYes');
		st.add(new Option('Select Subdistrict', '0'));
		for ( var i = 0; i < dwrSubdistrictList.length; i++) {
			var option = $("<option/>");
			if (dwrSubdistrictList[i].disabled) {
				$(option).attr('disabled', 'disabled');
				$(option).val(dwrSubdistrictList[i].value).text(dwrSubdistrictList[i].text);
				options.append(option);
			} else {
				$(option).val(dwrSubdistrictList[i].value).text(dwrSubdistrictList[i].text);
				options.append(option);
			}

		}

		/*
		 * for ( var i = 0; i < dwrSubdistrictList.length; i++) {
		 * document.getElementById('ddSubdistrictMergeYes').add(new
		 * Option(dwrSubdistrictList[i].text, dwrSubdistrictList[i].value));
		 * document.getElementById('ddSubdistrictMergeNo').add(new
		 * Option(dwrSubdistrictList[i].text, dwrSubdistrictList[i].value)); }
		 */

		for ( var i = 0; i < document.getElementById('ddSubdistrictMergeYes').length; i++) {
			if (document.getElementById('ddSubdistrictMergeYes')[i].value == selectedValue) {
				pos = i;
			}
		}

		document.getElementById('ddSubdistrictMergeYes').selectedIndex = pos;
		document.getElementById('ddSubdistrictMergeNo').selectedIndex = pos;
		removeSelectedValue('ddSubdistrictMergeYes');
		removeSelectedValue('ddSubdistrictMergeNo');
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
// start for merge
// for first subdistrict
function getSubDistrictListMerge(id) {
	lgdDwrSubDistrictService.getSubDistrictListForLocalbody(id, {
		callback : handleSubDistrictSuccessMerge,
		errorHandler : handleSubDistrictErrorMerge
	});

}

// data contains the returned value
function handleSubDistrictSuccessMerge(data) {
	// Assigns data to result id
	// alert(data);
	var fieldIdYes = 'ddSubdistrictMergeYes';
	var fieldIdNo = 'ddSubdistrictMergeNo';
	dwr.util.removeAllOptions(fieldIdYes);
	var st = document.getElementById('ddSubdistrictMergeYes');
	st.add(new Option('Select Subdistrict', '0'));
	dwr.util.removeAllOptions(fieldIdNo);
	st = document.getElementById('ddSubdistrictMergeNo');
	st.add(new Option('Select Subdistrict', '0'));

	var optionsYes = $("#ddSubdistrictMergeYes");
	$.each(data, function(key, obj) {
		var optionYes = $("<option/>");
		if (obj.operation_state == 'F') {
			$(optionYes).attr('disabled', 'disabled');
			$(optionYes).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
			optionsYes.append(optionYes);
		} else {
			$(optionYes).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
			optionsYes.append(optionYes);
		}
	});

	var optionsNo = $("#ddSubdistrictMergeNo");
	$.each(data, function(key, obj) {
		var optionNo = $("<option/>");
		if (obj.operation_state == 'F') {
			$(optionNo).attr('disabled', 'disabled');
			$(optionNo).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
			optionsNo.append(optionNo);
		} else {
			$(optionNo).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
			optionsNo.append(optionNo);
		}
	});
}

function handleSubDistrictErrorMerge() {
	// Show a popup message
	alert("No data found!");
}

// //for village
function getVillageListMerge(id) {
	// alert(id);
	lgdDwrVillageService.getVillageList(id, {
		callback : handleVillageSuccessMerge,
		errorHandler : handleVillageErrorMerge
	});

}

// data contains the returned value
function handleVillageSuccessMerge(data) {
	// Assigns data to result id
	// alert(data);
	var fieldId = 'villageListMainMerge';
	var valueText = 'villageCode';
	var nameText = 'villageNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleVillageErrorMerge() {
	// Show a popup message
	alert("No data found!");
}
// end for merge
/** *********************** */
// //for ULB covered landregion
/* ulb coverage */
function getSubDistrictandULBList(id) {
	getSubDistrictList(id);
	getSubDistrictListMerge(id);
}
// for first subdistrict
function getSubDistrictList(id) {

	lgdDwrSubDistrictService.getSubDistrictListForLocalbody(id, {
		callback : handleSubDistrictSuccess,
		errorHandler : handleSubDistrictError
	});

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

// //for village
function getVillageList(id) {
	// alert(id);
	lgdDwrVillageService.getVillagebysubdisrictCodesForLocalBody(id, {
		callback : handleVillageSuccess,
		errorHandler : handleVillageError
	});

}

// data contains the returned value
function handleVillageSuccess(data) {
	// Assigns data to result id
	// alert(data);
	var fieldId = 'villageListMain';
	dwr.util.removeAllOptions(fieldId);

	var options = $("#villageListMain");
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

	var fieldId2 = 'villageListMainContributing';
	dwr.util.removeAllOptions(fieldId2);
	var optionsContri = $("#villageListMainContributing");
	$.each(data, function(key, obj) {
		var optionContri = $("<option/>");
		if (obj.operation_state == 'F') {
			$(optionContri).attr('disabled', 'disabled');
			$(optionContri).val(obj.villageCode).text(obj.villageNameEnglish);
			optionsContri.append(optionContri);
		} else {
			$(optionContri).val(obj.villageCode).text(obj.villageNameEnglish);
			optionsContri.append(optionContri);
		}
	});

}

function handleVillageError() {
	// Show a popup message
	alert("No data found!");
}

// @Author - Sarvapriya Malhotra
// @Modified BY: Chandan Soni
function toggledisplay(obj, val) {

	if (document.getElementById(obj).checked) {
		// alert(val);
		// alert(obj);
		if (val == 'cvillage') {
			document.getElementById('cvillage').style.visibility = 'visible';
			document.getElementById('cvillage').style.display = 'block';
			document.getElementById('fromothersubdistrict').style.visibility = 'hidden';
			document.getElementById('fromothersubdistrict').style.display = 'none';
		}
		if (val == 'fromothersubdistrict') {
			document.getElementById('fromothersubdistrict').style.visibility = 'visible';
			document.getElementById('fromothersubdistrict').style.display = 'block';
			document.getElementById('cvillage').style.visibility = 'hidden';
			document.getElementById('cvillage').style.display = 'none';
		}
	} else {
		document.getElementById('cvillage').style.visibility = 'visible';
		document.getElementById('cvillage').style.display = 'block';
		document.getElementById('fromothersubdistrict').style.visibility = 'hidden';
		document.getElementById('fromothersubdistrict').style.display = 'none';
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

function generateTempView() {
	var ddSubdistrictMergeNo = document.getElementById('ddSubdistrictMergeNo').value;
	if (ddSubdistrictMergeNo == 0 || ddSubdistrictMergeNo == "") {

		return false;
	}
	if (document.getElementById('villageListNewContri').length == 0)
		return false;

	document.getElementById('dynamicTbl').style.visibility = "visible";
	var tbl = document.getElementById('dynamicTbl');
	var row = tbl.insertRow(tbl.rows.length);
	row.className += "tblRowB";
	var colCount = tbl.rows[0].cells.length;

	for ( var i = 0; i < colCount; i++) {
		var newcell = row.insertCell(i);
		if (i == 0)
			newcell.innerHTML = '<strong>'
					+ document.getElementById('ddSubdistrictMergeNo')[document.getElementById('ddSubdistrictMergeNo').selectedIndex].text
					+ '</strong>';
		else
			newcell.innerHTML = document.getElementById('villageListNewContri')[0].text;
	}

	for ( var i = 1; i < document.getElementById('villageListNewContri').length; i++) {
		row = tbl.insertRow(tbl.rows.length);
		row.className += "tblRowB";
		for ( var j = 0; j < colCount; j++) {
			var newcell = row.insertCell(j);

			if (j == 1)
				newcell.innerHTML = document.getElementById('villageListNewContri')[i].text;
		}
	}

	row = tbl.insertRow(tbl.rows.length);
	row.className += "tblRowB";

	for ( var i = 0; i < colCount; i++) {
		var newcell = row.insertCell(i);
		newcell.innerHTML = '<br />';
	}

	removeSelectedValue('ddSubdistrictMergeNo');
	removeEverything('villageListNewContri');

}

function hasMoreItems(dropdownName, buttonName) {
	var ddSubdistrictMergeNo = document.getElementById('ddSubdistrictMergeNo').value;
	if (ddSubdistrictMergeNo == 0 || ddSubdistrictMergeNo == "") {

		return false;
	}

	if (document.getElementById(dropdownName).length == 0)
		document.getElementById(buttonName).disabled = true;

}

function hideBoxes() {
	$('#fromothersubdistrict').hide();
	$('#id1').hide();
	document.getElementById('hiddenid').value = 1;
	$('#cvillage').hide();
	dwr.util.removeAllOptions($('#ddSubdistrictMergeYes'));
	dwr.util.removeAllOptions('villageListMainContributing');
	$("#subdistrictdelete_yes").prop('checked', true);
	document.getElementById('dynamicTbl').style.visibility = "hidden";
	var table = document.getElementById("dynamicTbl");

	var rowCount = table.rows.length;

	for ( var i = 1; i <= rowCount; i++) {
		document.getElementById("dynamicTbl").deleteRow(i);

	}

}
