//DWR Dropdownlist Population

function onloaddistrict() {
	document.getElementById('addAnotherSD').value = "";
	var mypath = window.location.href;
	if (mypath.match('modify') == 'modify') {
		document.getElementById('Yes').checked = true;
		document.getElementById('reorgOption').style.visibility = 'visible';
		document.getElementById('newVillage').disabled = true;
		document.getElementById('Yes').disabled = true;
		document.getElementById('ddDistrict').disabled = true;
		document.getElementById('partSubdistrict').disabled = true;
		document.getElementById('Yes').disabled = true;
	}

	if (mypath.match('createdistrict') == 'createdistrict') {
		document.getElementById('Yes').checked = true;
		document.getElementById('reorgOption').style.visibility = 'visible';
		document.getElementById('newVillage').disabled = true;

		document.getElementById('newSubdistrict').disabled = true;

		document.getElementById('Yes').disabled = true;
		document.getElementById('ddDistrict').disabled = true;
		document.getElementById('partSubdistrict').disabled = true;
		document.getElementById('Yes').disabled = true;
		document.getElementById('newVillage').disabled = true;
	}

	hideGISComponentOnLoad();

}

function formSubmitAdd() {
	document.getElementById('addAnotherSD').value = "";
	document.getElementById('addAnotherSD').value = "true";
	document.forms['district'].submit();
}

function select() {
	var selObj0 = document.getElementById('ddSourceDistrict');
	var selObj = document.getElementById('ddDestDistrict');
	var selObj2 = document.getElementById('ddSubdistrictforsubdistrict');
	var selObj1 = document.getElementById('subDistrictListNew');
	var selObj3 = document.getElementById('villageList');
	var preDistrict = "";
	var District = "";
	var subDistrict = "";
	var subPreDistrict = "";
	var preVillage = "";
	var operation_state="";
	for ( var i = 0; i < selObj0.options.length; i++) {
		operation_state="A";
		//alert(selObj0.options[i].disabled);
		if(selObj0.options[i].disabled){
			operation_state="F";
		}
		if (preDistrict == "") {
			
			preDistrict = selObj0.options[i].value + ":" + selObj0.options[i].text+":"+operation_state;
		} else {
			preDistrict = preDistrict + "," + selObj0.options[i].value + ":" + selObj0.options[i].text+":"+operation_state;
		}
	}
	document.getElementById('preDistrict').value = preDistrict;

	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		if (District == "") {
			District = selObj.options[i].value + ":" + selObj.options[i].text;
		} else {
			District = District + "," + selObj.options[i].value + ":" + selObj.options[i].text;
		}
	}
	document.getElementById('districtNameEnglishTemp').value = District;
	for ( var i = 0; i < selObj2.options.length; i++) {
		if (subPreDistrict == "") {
			subPreDistrict = selObj2.options[i].value + ":" + selObj2.options[i].text;
		} else {
			subPreDistrict = subPreDistrict + "," + selObj2.options[i].value + ":" + selObj2.options[i].text;
		}
	}
	document.getElementById('preSubDistrictsTemp').value = subPreDistrict;
	for ( var i = 0; i < selObj1.options.length; i++) {
		selObj1.options[i].selected = true;
		if (subDistrict == "") {
			subDistrict = selObj1.options[i].value + ":" + selObj1.options[i].text;
		} else {
			subDistrict = subDistrict + "," + selObj1.options[i].value + ":" + selObj1.options[i].text;
		}
	}
	document.getElementById('contributedSubDistrictsTemp').value = subDistrict;

	if (selObj3) {
		for ( var i = 0; i < selObj3.options.length; i++) {
			if (preVillage == "") {
				preVillage = selObj3.options[i].value + ":" + selObj3.options[i].text;
			} else {
				preVillage = preVillage + "," + selObj3.options[i].value + ":" + selObj3.options[i].text;
			}
		}
		document.getElementById('preVillageTemp').value = preVillage;
	}
	var historyDistrict = "";
	var historysubDistrict = "";

	for ( var i = 0; i < selObj.options.length; i++) {
		if ((selObj.options[i].value).match('FULL') == 'FULL') {
			if (historyDistrict == "") {
				historyDistrict = selObj.options[i].text;
			} else {
				historyDistrict = historyDistrict + "," + selObj.options[i].text;
			}
		}
	}
	document.getElementById('historyDistrictList').value = historyDistrict;
	for ( var i = 0; i < selObj1.options.length; i++) {
		if ((selObj1.options[i].value).match('FULL') == 'FULL') {
			if (historysubDistrict == "") {
				historysubDistrict = selObj1.options[i].text;
			} else {
				historysubDistrict = historysubDistrict + "," + selObj1.options[i].text;
			}
		}
	}
	document.getElementById('histrorySubDistrictList').value = historysubDistrict;

	var totalVillageOption = document.getElementById('villageListNew');
	if (totalVillageOption) {
		for ( var i = 0; i < totalVillageOption.options.length; i++) {
			var temp = (totalVillageOption.options[i].value).split("FULL");
			if (document.getElementById('totalVillage').value == "") {
				document.getElementById('totalVillage').value = temp[0];
			} else {
				document.getElementById('totalVillage').value = document.getElementById('totalVillage').value + "," + temp[0];
			}
		}
	}

	var districtNameInEn = document.getElementById('districtNameInEn');
	if (document.getElementById('districtNameList').value == "") {
		document.getElementById('districtNameList').value = districtNameInEn.value;
	} else {
		document.getElementById('districtNameList').value = document.getElementById('districtNameList').value + "," + districtNameInEn.value;
	}

}

function formSubmitPreview() {
	if (validateform()) {
		if (validationadistrict()) {
			select();
			// alert('calling formSubmitPreview.. form going to submit..');
			document.getElementById('addAnotherSD').value = "";
			document.getElementById('addAnotherSD').value = "true";
			document.forms['form1'].submit();
		}
	}
}

function validationadistrict() {
	// alert('calling validationadistrict..');
	var error = 0;
	var tmp = 0;
	var tmpsubd = 0;
	document.getElementById('statusDist').value = 0;
	var currentDistName = document.getElementById('districtNameInEn').value;
	var districtNameList = document.getElementById('districtNameList').value;
	if (districtNameList != "") {
		var arraytemp = districtNameList.split(",");
		for ( var j = 0; j < arraytemp.length; j++) {
			if (currentDistName == arraytemp[j]) {
				$("#dialog-duplicate-district-two").dialog({
					resizable : false,
					height : 140,
					modal : true,
					buttons : {
						Ok : function() {
							$(this).dialog("close");
						}
					}
				});
				return false;
			}
		}
	}

	var bool = checkDuplicate();
	if (bool) {
		error = 1;
		return false;
	}
	if (document.getElementById('ddDestDistrict').options.length == 0) {
		error = 1;
		$("#alertboxTitle").text("Error Message");
		$("#alertboxbody").text("Kindly select one or more contributing District");
		$('#alertbox').modal('show');	
		/*$("#dialog-error5").dialog({
			resizable : false,
			height : 140,
			modal : true,
			buttons : {
				Ok : function() {
					$(this).dialog("close");
				}
			}
		});*/
	} else {
		for ( var i = 0; i < document.getElementById('ddDestDistrict').options.length; i++) {
			document.getElementById('ddDestDistrict').options[i].selected = true;
			if (document.getElementById('ddDestDistrict').options[i].value.match('FULL') == "FULL")
				tmp = 0;
			else {
				tmp = 1;
				break;
			}
		}
		if (document.getElementById('ddDestDistrict').options.length != 0 && tmp == 0) {

			document.getElementById('statusDist').value = 1;
			error = 0;
		} else {
			if (tmp == 1) {
				// alert(document.getElementById('subDistrictListNew').options.length);
				if (document.getElementById('subDistrictListNew').options.length == 0) {
					error = 1;
					$("#alertboxTitle").text("Error Message");
					$("#alertboxbody").text("Kindly select One or more contributing Sub-District");
					$('#alertbox').modal('show');	
					/*$("#dialog-error6").dialog({
						resizable : false,
						height : 140,
						modal : true,
						buttons : {
							Ok : function() {
								$(this).dialog("close");
							}
						}
					});*/
				}

				else {
					for ( var i = 0; i < document.getElementById('subDistrictListNew').options.length; i++) {
						document.getElementById('subDistrictListNew').options[i].selected = true;
						if (document.getElementById('subDistrictListNew').options[i].value.match('FULL') == "FULL")
							tmpsubd = 0;
						else {
							tmpsubd = 1;
							break;
						}
					}
					if (document.getElementById('subDistrictListNew').options.length != 0 && tmpsubd == 0) {
						document.getElementById('statusDist').value = 1;
						error = 0;

					} else {
						if (tmpsubd == 1) {
							if (document.getElementById('villageListNew').options.length == 0) {
								error = 1;
								document.getElementById('statusDist').value = 1;
								$("#alertboxTitle").text("Error Message");
								$("#alertboxbody").text("Kindly select one or more contributing villages");
								$('#alertbox').modal('show');	
								/*$("#dialog-error8").dialog({
									resizable : false,
									height : 140,
									modal : true,
									buttons : {
										Ok : function() {
											$(this).dialog("close");
										}
									}
								});*/
							} else {
								// alert('mergeVillageListId-->' +
								// document.getElementById('mergeVillageListId').value
								// + ", storedCombiValues-->"
								// +
								// document.getElementById('storedCombiValues').value
								// + ", storedNewCombiValues-->"
								// +
								// document.getElementById('storedNewCombiValues').value);

								if (document.getElementById('mergeVillageListId').value == "") {
									error = 1;
									$("#alertboxTitle").text("Error Message");
									$("#alertboxbody").text("You have not properly organize contributing village list");
									$('#alertbox').modal('show');	
									/*$("#dialog-error9").dialog({
										resizable : false,
										height : 200,
										modal : true,
										buttons : {
											Ok : function() {
												$(this).dialog("close");
											}
										}
									});*/
								} else {
									var len1 = document.getElementById('villageListNew').length;
									var len2 = 0;
									if (document.getElementById('storedCombiValues').value == ""
											&& document.getElementById('storedNewCombiValues').value != "") {
										// alert('1.');
										var obj = document.getElementById('storedNewCombiValues').value.split(",");
										if (obj.length > 0) {
											// alert('1.1');
											for ( var i = 0; i < obj.length; i++) {
												// alert('1.2');
												if (obj[i].indexOf(":") >= 0) {
													// alert('1.3');
													var substr = obj[i].split(":");
													// alert('1.4-->' +
													// substr.length);
													len2 = len2 + substr.length;
												} else {
													len2 = len2 + obj.length;
												}
											}
										}
									} else if (document.getElementById('storedNewCombiValues').value == ""
											&& document.getElementById('storedCombiValues').value != "") {
										// alert('2.')
										var obj = document.getElementById('storedCombiValues').value.split(",");
										if (obj.length > 0) {
											// alert('2.1');
											for ( var i = 0; i < obj.length; i++) {
												// alert('2.2');
												if (obj[i].indexOf(":") >= 0) {
													// alert('2.3');
													var substr = obj[i].split(":");
													// alert('2.4-->' +
													// substr.length);
													len2 = len2 + substr.length;
												} else {
													len2 = len2 + obj.length;
												}
											}
										}
									} else {
										// alert('3.')
										var m = document.getElementById('storedNewCombiValues').value.split(",");
										var n = document.getElementById('storedCombiValues').value.split(",");
										// len2 = (m.length) + (n.length);

										if (m.length > 0) {
											// alert('3.1');
											for ( var i = 0; i < m.length; i++) {
												// alert('3.2');
												if (m[i].indexOf(":") >= 0) {
													// alert('3.3');
													var substr = m[i].split(":");
													// alert('3.4-->' +
													// substr.length);
													len2 = len2 + substr.length;
												} else {
													len2 = len2 + m.length;
												}
											}
										}

										if (n.length > 0) {
											// alert('3.1.1');
											for ( var i = 0; i < n.length; i++) {
												// alert('3.1.2');
												if (n[i].indexOf(":") >= 0) {
													// alert('3.1.3');
													var substr = n[i].split(":");
													// alert('3.1.4-->' +
													// substr.length);
													len2 = len2 + substr.length;
												} else {
													len2 = len2 + n.length;
												}
											}
										}
									}
									// var villObj =
									// document.getElementById('mergeVillageListId').value.split(",");
									// var lenVill = villObj.length;
									// alert('len1-->' + len1 + ', len2-->' +
									// len2);
									if (len2 < len1) {
										error = 1;
										$("#dialog-error9").dialog({
											resizable : false,
											height : 200,
											modal : true,
											buttons : {
												Ok : function() {
													$(this).dialog("close");
												}
											}
										});
									}
								}
							}
						}
					}

				}
			}
		}

	}

	/*
	 */
	if (error == 0) {
		return true;
		// document.getElementById('ddDistrict').disabled = false;
		// document.forms['form1'].submit();
	}
	if (error == 1) {
		return false;
	}
}

function findDuplicate(distName) {
	if (distName == "") {
		$("#dialog-confirm-dist-name").dialog({
			resizable : false,
			height : 140,
			modal : true,
			buttons : {
				Ok : function() {
					$(this).dialog("close");
				}
			}
		});
		return false;
	} else {
		var bool = validateSpecialCharacters(distName);
		var flag = false;
		var districtNameList = document.getElementById('districtNameList').value;
		var currentDistName = document.getElementById('districtNameInEn').value;
		// alert("bool-->" + bool);
		if (bool) {
			if (districtNameList != "") {
				var arraytemp = districtNameList.split(",");
				for ( var j = 0; j < arraytemp.length; j++) {
					if (currentDistName == arraytemp[j]) {
						flag = true;
						break;
					}
				}
				if (flag == false) {
					lgdDwrDistrictService.findDuplicate(distName, stateCode, {
						callback : handleDuplicateDistrict
					});
				} else {// if (flag == true && isDistDuplicate == true) {
					/*$("#dialog-duplicate-district-two").dialog({
						resizable : false,
						height : 140,
						modal : true,
						buttons : {
							Ok : function() {
								$(this).dialog("close");
							}
						}
					});*/
					$("#alertboxTitle").text("Error Message");
					$("#alertboxbody").text("District name can not be same as previously created District name");
					$('#alertbox').modal('show');
					return false;
				}
			} else {
				lgdDwrDistrictService.findDuplicate(distName, stateCode, {
					callback : handleDuplicateDistrict
				});
			}
		}
	}
}

function handleDuplicateDistrict(data) {
	if (data == "0") {
		isDistDuplicate = false;
	} else {
		isDistDuplicate = true;
		$("#dialog-duplicate-district").dialog({
			resizable : false,
			height : 140,
			modal : true,
			buttons : {
				Ok : function() {
					$(this).dialog("close");
				}
			}
		});
	}
}
function handleDistError() {
	alert("No data found for this district name");
}

// getsubdistrict start
function getSubDistricts(id) {

	lgdDwrSubDistrictService.getSubDistrictList(id, {
		callback : handleSubDistrict, // handleSubDistrictSuccess,
		errorHandler : handleSubDistError
	});

}

// data contains the returned value
function handleSubDistrict(data) {// handleSubDistrictSuccess(data) {
	// Assigns data to result id

	var fieldId = 'subDistrictListNew';
	var valueText = 'subdistrictCode';
	var nameText = 'subdistrictNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		name : "Select Subdistrict"
	} ];
	dwr.util.addOptions(fieldId, dataq, "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleSubDistError() {
	// Show a popup message
	alert("No data found!");
}

// getsubdistrict end

// for first subdistrict
function getSubDistrictList(id) {
	lgdDwrSubDistrictService.getSubDistrictList(id, {
		callback : handleSubDistrictSuccess,
		errorHandler : handleSubDistrictError
	});

}

// data contains the returned value
function handleSubDistrictSuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddDestDistrict';
	var valueText = 'subdistrictCode';
	var nameText = 'subdistrictNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		name : "Select Subdistrict"
	} ];
	dwr.util.addOptions(fieldId, dataq, "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleSubDistrictError() {
	// Show a popup message
	alert("No data found!");
}

// for second subdistrict
function getSubIDistrictList(id) {

	lgdDwrSubDistrictService.getSubDistrictList(id, {
		callback : handleSubIDistrictSuccess,
		errorHandler : handleSubIDistrictError
	});

}

// data contains the returned value
function handleSubIDistrictSuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddSubIdistrict';
	var valueText = 'subdistrictCode';
	var nameText = 'subdistrictNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleSubIDistrictError() {
	// Show a popup message
	alert("No data found!");
}

// //for village
function getVillageList(id) {

	lgdDwrVillageService.getVillageList(id, {
		callback : handleVillageSuccess,
		errorHandler : handleVillageError
	});

}
// data contains the returned value
function handleVillageSuccess(data) {
	// Assigns data to result id

	var fieldId = 'villageListMain';
	var valueText = 'villageCode';
	var nameText = 'villageNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		name : "Select Village"
	} ];
	dwr.util.addOptions(fieldId, dataq, '0', "name");
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleVillageError() {
	// Show a popup message
	alert("No data found!");
}

// Village Details
function getModifyVillageValue(id) {
	lgdDwrVillageService.getVillageDetailsModify(id, {
		callback : handleModifyVillageSuccess,
		errorHandler : handleModifyVillageError
	});

}

function handleModifyVillageSuccess(data) {
	// Assigns data to result id

	var fieldId = 'frmModifyVillage';
	dwr.util.setValue(fieldId, data);
}

function handleModifyVillageError() {
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

/*******************************************************************************
 * // list1 - list from which to add values from as string i.e id name * list2 -
 * list to which to add values from as string i.e id name val - value to append
 * at the end Note: doesn't matter what is it if doAddVal=false doAddVal - can
 * be either true or false...If True val is appended at the end if false val is
 * not appeneded
 */
/*
 */
function addItem(list1, list2, val, doAddVal) {

	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++)
			if (document.getElementById(list2).options[j].selected == true)
				if (doAddVal) {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + val + ">" + document.getElementById(list2)[j].text + " ("
									+ val + ")</option>");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");
				}
		removeSelectedValue(list2);
	}
}
/* Added by sushil */

// window.onload = function() {
/*
 * if(document.getElementById("src2Target1")) {
 * document.getElementById("src2Target1").onclick = function() { var selectBox =
 * document.getElementById("ddSourceDistrict"); var tarBox =
 * document.getElementById("ddDestDistrict"); var type =
 * document.getElementById("src2Target1"); var txtToAppend = "";
 * if((type.value).match('Whole')) { txtToAppend = "FULL"; } else { txtToAppend =
 * "PART"; } if (selectBox.type == "select-multiple") { var counter = 0; for
 * (var i = 0; i < selectBox.options.length; i++) {
 * if(selectBox.options[i].selected) { var val = selectBox.options[i].value; var
 * txt = (selectBox.options[i].text)+" ( "+txtToAppend+" )";
 * tarBox.options[counter] = new Option(txt, val); counter++; } } } }; }
 */
// };
function removeItem(list1, list2, doRemoveVal) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++)
			if (document.getElementById(list1).options[j].selected == true)
				if (doRemoveVal)
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 4) + " >"
									+ document.getElementById(list1)[j].innerText.substr(0, document.getElementById(list1)[j].innerText.length - 6)
									+ "</option>");
				else
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].innerText + " >" + document.getElementById(list1)[j].innerText
									+ "</option>");
		removeSelectedValue(list1);
	}
}

// /////////////////////////////////
/*
 * function addItem(list1,list2,val,doAddVal) {
 * if(document.getElementById(list2).selectedIndex>=0) { if (doAddVal) {
 * alert(document.getElementById(list2)[document.getElementById(list2).selectedIndex].value);
 * $('#' + list1).append("<option value=" +
 * document.getElementById(list2)[j].value + val + ">" +
 * document.getElementById(list2)[j].text + " (" + val + ")</option>"); } else
 * $('#' + list1).append("<option value=" +
 * document.getElementById(list2)[j].value + " >" +
 * document.getElementById(list2)[j].text + "</option>");
 * removeSelectedValue(list2); } }
 */

function addsubdistrict(list1, list2, val, doAddVal) {

	if (document.getElementById(list2).selectedIndex >= 0) {
		if (doAddVal)
			$('#' + list1).append(
					"<option value=" + document.getElementById(list2)[document.getElementById(list2).selectedIndex].value + val + ">"
							+ document.getElementById(list2)[document.getElementById(list2).selectedIndex].innerText + " (" + val + ")</option>");
		else
			$('#' + list1).append(
					"<option value=" + document.getElementById(list2)[document.getElementById(list2).selectedIndex].value + " >"
							+ document.getElementById(list2)[document.getElementById(list2).selectedIndex].innerText + "</option>");
		removeSelectedValue(list2);
	}
}

function removeAll(list1, list2, doRemoveVal) {
	for ( var i = document.getElementById(list1).length - 1; i >= 0; i--) {
		document.getElementById(list1).selectedIndex = i;

		if (doRemoveVal)
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 4) + " >"
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 4) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText + "</option>");
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

function removeItem(list1, list2, doRemoveVal) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		if (doRemoveVal)
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 4) + " >"
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 4) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].innerText + "</option>");
		removeSelectedValue(list1);
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
// OfficialAddress1
function nomenInEnglish() {

	if (document.getElementById("NomenInEnglish").value == "") {
		document.getElementById("NomenInEnglish_error").innerHTML = "Nomenclature in English is Required";
		$("#NomenInEnglish_error").show();
		$("#NomenInEnglish_msg").hide();
		$("#NomenInEnglish").addClass("error_fld");
		$("#NomenInEnglish").addClass("error_msg");
		return false;

	} else {
		$("#NomenInEnglish_msg").hide();
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

/*
 * function addgisnodes() { var tmptextLati=new Array(); var tempchkLongi=new
 * Array();
 * 
 * if (dynstart==0) { dynstart=i; } dynend=i;
 * 
 * for(var j=dynstart;j<dynend;j++) { if (document.getElementById("lati" + j)){
 * if(document.getElementById("lati" + j)!=null &&
 * document.getElementById("longi" + j)!=null)
 * tmptextLati[j]=document.getElementById("lati" + j).value;
 * tempchkLongi[j]=document.getElementById("longi" + j).value; } }
 * 
 * document.getElementById("addgisnodes").innerHTML += "<div id=div" + i + "><td align='left'>" +
 * document.getElementById('lbllatitude').innerHTML + "</td><td width='75'>&nbsp;&nbsp;<input
 * type='text' name='lati' id='lati" + i + "' class='frmfield'></td><td width='145'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td width='150'>" +
 * document.getElementById('lbllongitude').innerHTML + "</td><td width='75'>&nbsp;&nbsp;<input
 * type='text' name='longi' id='longi" + i + "' class='frmfield'></td><td><input
 * type='button' value='Remove' onclick='div" + i +
 * ".parentNode.removeChild(div" + i +")'/></td></div>";
 * 
 * for(var j=dynstart;j<dynend;j++) { if (document.getElementById("lati" + j)){
 * if(document.getElementById("lati" + j)!=null &&
 * document.getElementById("longi" + j)!=null) document.getElementById("lati" +
 * j).value=tmptextLati[j]; document.getElementById("longi" +
 * j).value=tempchkLongi[j]; } } i++; }
 */
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
function selectSubdistrictList(paneName) {
	for ( var i = 0; i < document.getElementById(paneName).length; i++) {
		document.getElementById(paneName).options[i].selected = true;
		selectall(document.getElementById(paneName).options[i].value, document.getElementById(paneName).options[i].innerText);
	}
}

function selecteveryssd(id, name) {
	if (id.match('PART') == 'PART') {
		var selObj = document.getElementById('ddDestDistrict');
		var subDistrict = "";
		for (i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
			subDistrict += selObj.options[i].value + ",";
		}

		lgdDwrDistrictService.getSubdistrictListbyDistrict(id, {
			callback : handleSubDistrict, // handleSubDistrictSuccess,
			errorHandler : handleSubDistError
		});
	} else {
		dwr.util.removeAllOptions('villageList');
		alert("Kindly Select the Part District From the List");
	}
}

// data contains the returned value
function handleSubDistrict(data) {// handleSubDistrictSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddSubdistrictforsubdistrict';
	var valueText = 'subdistrictCode';
	var nameText = 'subdistrictNameEnglish';
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleSubDistError() {
	// Show a popup message
	alert("No data found!");
}

/*
 * function selectVillageList(paneName) { for (var i=0;i<document.getElementById(paneName).length;i++) {
 * document.getElementById(paneName).options[i].selected=true;
 * selectallforvillage(document.getElementById(paneName).options[i].value,document.getElementById(paneName).options[i].innerText); } }
 */

function selectVillageList() {
	resetValues(true);
	var selObj = document.getElementById('subDistrictListNew');
	var idValuePart = "";
	var selText = [];
	var counter = 0;
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		if ((selObj.options[i].value).match('PART') == 'PART') {
			var txt = selObj.options[i].text;
			if (txt != null) {
				selText[counter] = txt.substring(0, txt.indexOf('['));
				counter++;
			}
			if (idValuePart == "") {
				idValuePart = selObj.options[i].value;
			} else {
				idValuePart = idValuePart + "," + selObj.options[i].value;
			}
		}
	}
	if (idValuePart != "") {
		var totalVillage = document.getElementById('totalVillage').value;
		lgdDwrDistrictService.getVillageListRemovingOldVillageForDistrictForm(idValuePart, totalVillage, selText, {
			callback : handleVillageList,
			errorHandler : handleVillageError
		});
	} else {
		dwr.util.removeAllOptions('villageList');
		$("#alertboxTitle").text("Message Dialog");
		$("#alertboxbody").text("Please select at least one part Sud-District to get villages list");
		$('#alertbox').modal('show');	
		/*$("#dialog-village-button").dialog({
			resizable : false,
			height : 140,
			modal : true,
			buttons : {
				Ok : function() {
					$(this).dialog("close");
				}
			}
		});*/
	}
} // data contains the returned value
function handleVillageList(data) {
	dwr.util.removeAllOptions('villageList');

	/*
	 * if (data.length > 0) { var objData = []; for ( var i = 0; i <
	 * data.length; i++) { objData[i] = { name : data[i].villageNameEnglish + ' [ ' +
	 * data[i].subdistrict + ' ] ', code : data[i].villageCode }; }
	 */

	var options = $("#villageList");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.villageCode).text(obj.villageNameEnglish + ' [ ' + obj.subdistrict + ' ] ');
			options.append(option);
		} else {
			$(option).val(obj.villageCode).text(obj.villageNameEnglish + ' [ ' + obj.subdistrict + ' ] ');
			options.append(option);
		}
	});
	
	sortListBox('villageList');

	/*
	 * dwr.util.addOptions('villageList', objData, 'code', 'name'); }
	 */
}

function handleVillageError() {
	// Show a popup message
	alert("No data found!");

}

/*
 * function selectallforvillage() { var
 * selObj=document.getElementById('subDistrictListNew'); //var
 * selObj2=document.getElementById('surveyListNew'); //var
 * selObj=document.getElementById('subDistrictListNew'); var subVillage=""; for
 * (i = 0; i < selObj.options.length; i++) { selObj.options[i].selected=true;
 * subVillage +=selObj.options[i].value+","; } getVillageList(subVillage); }
 * 
 * function getVillageList(id) {
 * 
 * lgdDwrDistrictService.getVillageList(id, { callback : handleVillageSuccess,
 * errorHandler : handleVillageError }); }
 * 
 * //data contains the returned value function handleVillageSuccess(data) { //
 * Assigns data to result id
 * 
 * var fieldId = 'villageList'; var valueText = 'villageCode'; var nameText =
 * 'villageNameEnglish'; dwr.util.removeAllOptions(fieldId); //var dataq = [
 * {name:"Select Village"} ]; //dwr.util.addOptions(fieldId, dataq,'0', "name");
 * dwr.util.addOptions(fieldId, data, valueText, nameText); }
 * 
 * function handleVillageError() { // Show a popup message alert("No data
 * found!"); }
 */

function validation() {
	if (document.getElementById('subDistrictListNew').options.length == 1
			&& (document.getElementById('subDistrictListNew').value.match('FULL')) == "FULL") {
		alert("You Can't Make the New  District by Shifting Only One Full Subdistrict. Kindly Select One More Contributing Sub District.  !");
		return false;
	} else {
		return true;
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

function getTargetState(id) {

	lgdDwrStateService.getStateTargetList(id, {
		callback : handleStateSuccess,
		errorHandler : handleStateError
	});

}

// data contains the returned value
function handleStateSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddDestState';
	var valueText = 'statePK.stateCode';
	var nameText = 'stateNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleStateError() {
	// Show a popup message
	alert("No data found!");
}

function getDistrictList(id) {

	lgdDwrDistrictService.getDistrictList(id, {
		callback : handleDistrictSuccess,
		errorHandler : handleDistrictError
	});

}

function getDistricts(id) {

	lgdDwrStateService.getDistricts(id, {
		callback : handleDistrictSuccess,
		errorHandler : handleDistrictError
	});

}

function handleDistrictSuccess(data) {
	// Assigns data to result id
	var fieldId = 'ddSourceDistrict';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleDistrictError() {
	// Show a popup message
	alert("No data found!");
}

function getSubDistrictList(id) {

	lgdDwrDistrictService.getDistrictList(id, {
		callback : handleDistrictSuccess,
		errorHandler : handleDistrictError
	});

}

function handleDistrictSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddSourceDistrict';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleDistrictError() {
	// Show a popup message
	alert("No data found!");
}

// ////////////////////////////////////////////////////////

// For sub district

function getSubDistrictList(id) {

	lgdDwrDistrictService.getSubDistrictList(id, {
		callback : handleSubDistrictSuccess,
		errorHandler : handleSubDistrictError
	});

}

function handleSubDistrictSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddSourceDistrict';
	var valueText = 'districtPK.districtCode';
	var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleSubDistrictError() {
	// Show a popup message
	alert("No data found!");
}

// //////////////////////////////////////
function listbox_move(listID, direction) {

	var listbox = document.getElementById(listID);
	var selIndex = listbox.selectedIndex;

	if (-1 == selIndex) {
		alert("Please select an option to move.");
		return;
	}

	var increment = -1;
	if (direction == 'up')
		increment = -1;
	else
		increment = 1;

	if ((selIndex + increment) < 0 || (selIndex + increment) > (listbox.options.length - 1)) {
		return;
	}

	var selValue = listbox.options[selIndex].value;
	var selText = listbox.options[selIndex].text;
	listbox.options[selIndex].value = listbox.options[selIndex + increment].value
	listbox.options[selIndex].text = listbox.options[selIndex + increment].text

	listbox.options[selIndex + increment].value = selValue;
	listbox.options[selIndex + increment].text = selText;

	listbox.selectedIndex = selIndex + increment;
}
// For whole
/*
 * function addItem(list1,list2,val,doAddVal) {
 * if(document.getElementById(list2).selectedIndex>=0) { if (doAddVal) $('#' +
 * list1).append("<option value=" +
 * document.getElementById(list2)[document.getElementById(list2).selectedIndex].value +
 * val + ">" +
 * document.getElementById(list2)[document.getElementById(list2).selectedIndex].text+ " (" +
 * val + ")</option>"); else $('#' + list1).append("<option value=" +
 * document.getElementById(list2)[document.getElementById(list2).selectedIndex].value + " >" +
 * document.getElementById(list2)[document.getElementById(list2).selectedIndex].text + "</option>");
 * removeSelectedValue(list2); } }
 */
function listbox_moveacross(sourceID, destID) {
	var src = document.getElementById(sourceID);
	var dest = document.getElementById(destID);
	var check;
	for ( var count = 0; count < src.options.length; count++) {

		if (src.options[count].selected == true) {
			var option = src.options[count];
			check = true;
			var newOption = document.createElement("option");
			// newOption.value = option.value+"(Full)";
			// newOption.text = option.text+"(Full)";
			newOption.value = option.value;
			newOption.text = option.text;
			newOption.selected = true;
			try {
				dest.add(newOption, null); // Standard
				src.remove(count, null);
			} catch (error) {
				dest.add(newOption); // IE only
				src.remove(count);
			}
			count--;

		}

	}

	if (check != true) {
		alert("Please select valid district to shift");

	}
	check = false;
	// show_hide();

}

// //////////////////////////////

// dwr.engine.setActiveReverseAjax(true);

// State and district list
function getList(id) {

	getDistrictList(id);
}

function getVillageList(id) {

	getDistrictList(id);
}

// For part selection
function part(sourceID, destID) {
	var src = document.getElementById(sourceID);
	var dest = document.getElementById(destID);
	var check;
	for ( var count = 0; count < src.options.length; count++) {

		if (src.options[count].selected == true) {
			var option = src.options[count];
			check = true;
			var newOption = document.createElement("option");
			// newOption.value = option.value+"(Full)";
			// newOption.text = option.text+"(Full)";
			newOption.value = option.value;
			newOption.text = option.text;
			newOption.selected = true;
			try {
				dest.add(newOption, null); // Standard
				src.remove(count, null);
			} catch (error) {
				dest.add(newOption); // IE only
				src.remove(count);
			}
			count--;

		}

	}

	if (check != true) {
		alert("Please select valid district to shift");

	}
	check = false;
	// show_hide();

}
/*
 * ///vanisha function addgisnodes() {
 * document.getElementById("addgisnodes").innerHTML += "<div width='100%'>" +
 * document.getElementById('lbllatitude').innerText + "<input type='text'
 * name='latitude'>" + document.getElementById('lbllongitude').innerText + "<input
 * type='text' name='longitude'><input type='button' value='Remove'
 * onclick='this.parentNode.parentNode.removeChild(this.parentNode)'></div>"; }
 */

function reorgOpen() {
	document.getElementById('reorganized').value = "true";
	if (document.getElementById('newSubdistrict').checked == true) {
		/* document.getElementById('newSubdistrict').disabled = true; */
	}
	// document.getElementById('Submit').style.visibility = 'hidden';
	// document.getElementById('AddAnotherSD').style.visibility = 'hidden';
	document.getElementById('Reorganize').style.visibility = 'visible';
}

/*
 * function reorgOption(){
 * 
 * document.getElementById('reorganized').value="true";
 * if(document.getElementById('isModifyVillage').checked==true){
 * document.getElementById('newVillage').disabled = true; }
 * //document.getElementById('Submit').style.visibility = 'hidden';
 * //document.getElementById('AddAnotherSD').style.visibility = 'hidden';
 * document.getElementById('Reorganize').style.visibility = 'visible'; }
 */

function reorg() {
	document.getElementById('reorganized').value = "true";
	if (document.getElementById('isModifySubdistrict').checked == true) {
		document.getElementById('newSubdistrict').disabled = true;
	}
	// document.getElementById('Submit').style.visibility = 'hidden';
	// document.getElementById('AddAnotherSD').style.visibility = 'hidden';
	document.getElementById('Reorganize').style.visibility = 'visible';
}

function reorganizeYes() {
	if (document.getElementById('Yes').checked = true) {

		document.getElementById('newVillage').disabled = false;
		document.getElementById('reorganized').value = "";
		document.getElementById('reorganized').value = "true";
		document.getElementById('reorgOption').style.visibility = 'visible';
		// document.getElementById('Submit').style.visibility = 'hidden';
		// document.getElementById('AddAnotherSD').style.visibility = 'hidden';
		document.getElementById('Yes').disabled = false;
	}
}
function reorganizeNo() {
	if (document.getElementById('No').checked = true) {
		document.getElementById('reorganized').value = "";
		document.getElementById('reorgOption').style.visibility = 'hidden';
		// document.getElementById('Submit').style.visibility = 'visible';
		// document.getElementById('AddAnotherSD').style.visibility = 'visible';
		document.getElementById('Reorganize').style.visibility = 'hidden';
	}
}
function reorganizingYes() {
	if (document.getElementById('Yes').checked = true) {

		document.getElementById('newVillage').disabled = false;
		document.getElementById('reorganized').value = "";
		document.getElementById('reorganized').value = "true";
		document.getElementById('reorgOption').style.visibility = 'visible';
		document.getElementById('Yes').disabled = false;
	}
}
function reorganizingNo() {

	if (document.getElementById('No').checked = true) {
		document.getElementById('reorganized').value = "";
		document.getElementById('reorgOption').style.visibility = 'hidden';
	}

	document.getElementById('Reorganize').style.visibility = 'hidden';
}
function formSubmitfinal() {
	var selObj1 = document.getElementById('ddDestDistrict');
	var selObj = document.getElementById('subDistrictListNew');
	var selObj2 = document.getElementById('villageListNew');

	if (document.getElementById('isModifySubdistrict').checked == true) {
		var count = 0;
		for ( var i = 0; i < selObj.options.length; i++) {
			if (selObj.options[i].selected) {
				count++;

			}
		}
		if (count == 0 || count > 1) {
			alert("To Modify the Subdistrict Kindly Select Only One From the List. !");
		} else {
			document.getElementById('modifySubDistrict').value = selObj.options[count].value;
			for ( var i = 0; i < selObj2.options.length; i++) {
				selObj2.options[i].selected = true;
			}
			for ( var i = 0; i < selObj1.options.length; i++) {
				selObj1.options[i].selected = true;
			}
			if (!validateform()) {
				return false;
			} else {
				document.forms['form1'].submit();
			}
		}
	} else {
		for ( var i = 0; i < selObj1.options.length; i++) {
			selObj1.options[i].selected = true;
		}
		for ( var i = 0; i < selObj2.options.length; i++) {
			selObj2.options[i].selected = true;
		}
		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
		}
		if (!validateform()) {
			return false;
		} else {
			document.forms['form1'].submit();
		}
	}
}

function Districtsbumit() {
	var selObj1 = document.getElementById('ddDestDistrict');
	var selObj = document.getElementById('subDistrictListNew');
	var selObj2 = document.getElementById('villageListNew');
	var selObj3 = document.getElementById('ddDestinctDistrict');

	if (document.getElementById('isModifyDistrict').checked == true) {
		var count = 0;
		for ( var i = 0; i < selObj3.options.length; i++) {
			if (selObj3.options[i].selected) {
				count++;
			}
		}
		if (count == 0 || count > 1) {
			alert("To Modify the district Kindly Select Only One From the List. !");
		} else {
			document.getElementById('modifyDistrict').value = selObj3.options[count].value;
			for ( var i = 0; i < selObj2.options.length; i++) {
				selObj2.options[i].selected = true;
			}
			for ( var i = 0; i < selObj.options.length; i++) {
				selObj.options[i].selected = true;
			}
			if (!validateform()) {
				return false;
			} else {
				document.forms['form1'].submit();
			}
		}
	} else {
		for ( var i = 0; i < selObj1.options.length; i++) {
			selObj1.options[i].selected = true;
		}
		for ( var i = 0; i < selObj2.options.length; i++) {
			selObj2.options[i].selected = true;
		}
		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
		}
		for ( var i = 0; i < selObj3.options.length; i++) {
			selObj3.options[i].selected = true;
		}
		if (!validateform()) {
			return false;
		} else {
			document.forms['form1'].submit();
		}
	}
}

function Shiftsbumit() {
	var selObj1 = document.getElementById('ddDestDistrict');
	var selObj = document.getElementById('subDistrictListNew');
	var selObj2 = document.getElementById('villageListNew');
	var selObj3 = document.getElementById('ddDestinctDistrict');

	if (document.getElementById('isModifyShiftdistrict').checked == true) {
		var count = 0;
		for ( var i = 0; i < selObj3.options.length; i++) {
			if (selObj3.options[i].selected) {
				count++;
			}
		}
		if (count == 0 || count > 1) {
			alert("To Modify the district Kindly Select Only One From the List. !");
		} else {
			document.getElementById('modifyDistrict').value = selObj3.options[count].value;
			for ( var i = 0; i < selObj2.options.length; i++) {
				selObj2.options[i].selected = true;
			}
			for ( var i = 0; i < selObj.options.length; i++) {
				selObj.options[i].selected = true;
			}
			if (!validateform()) {
				return false;
			} else {
				document.forms['form1'].submit();
			}
		}
	} else {
		for ( var i = 0; i < selObj1.options.length; i++) {
			selObj1.options[i].selected = true;
		}
		for ( var i = 0; i < selObj2.options.length; i++) {
			selObj2.options[i].selected = true;
		}

		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
		}
		for ( var i = 0; i < selObj3.options.length; i++) {
			selObj3.options[i].selected = true;
		}
		if (!validateform()) {
			return false;
		} else {
			document.forms['form1'].submit();
		}
	}
}
function formStateSubmit() {
	var selObj = document.getElementById('subDistrictListNew');
	var selObj1 = document.getElementById('ddDestDistrict');
	var selObj2 = document.getElementById('villageListNew');
	var selObj3 = document.getElementById('ddDestinctDistrict');

	if (document.getElementById('isModifyVillage').checked == true) {
		var count = 0;
		for ( var i = 0; i < selObj2.options.length; i++) {
			if (selObj2.options[i].selected) {
				count++;
			}
		}
		if (count == 0 || count > 1) {
			alert("To Modify the Village Kindly Select Only One From the List. !");
		} else {
			document.getElementById('modifyVillage').value = selObj2.options[count].value;
			for ( var i = 0; i < selObj.options.length; i++) {
				selObj.options[i].selected = true;
			}
			for ( var i = 0; i < selObj1.options.length; i++) {
				selObj1.options[i].selected = true;
			}
			if (!validateform()) {
				return false;
			} else {
				document.forms['form1'].submit();
			}
		}
	} else {
		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
		}

		for ( var i = 0; i < selObj1.options.length; i++) {
			selObj1.options[i].selected = true;
		}
		for ( var i = 0; i < selObj2.options.length; i++) {
			selObj2.options[i].selected = true;
		}
		for ( var i = 0; i < selObj3.options.length; i++) {
			selObj3.options[i].selected = true;
		}
		if (!validateform()) {
			return false;
		} else {
			document.forms['form1'].submit();
		}

	}
}

function formSubmit() {

	var selObj1 = document.getElementById('ddDestDistrict');
	var selObj = document.getElementById('subDistrictListNew');
	var selObj2 = document.getElementById('villageListNew');

	if (document.getElementById('isModifyVillage').checked == true) {
		var count = 0;
		for ( var i = 0; i < selObj2.options.length; i++) {
			if (selObj2.options[i].selected) {
				count++;
			}
		}
		if (count == 0 || count > 1) {
			alert("To Modify the Village Kindly Select Only One From the List. !");
		} else {
			document.getElementById('modifyVillage').value = selObj2.options[count].value;
			for ( var i = 0; i < selObj.options.length; i++) {
				selObj.options[i].selected = true;
			}
			for ( var i = 0; i < selObj1.options.length; i++) {
				selObj1.options[i].selected = true;
			}
			if (!validateform()) {
				return false;
			} else {
				document.forms['form1'].submit();
			}
		}
	} else {
		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
		}

		for ( var i = 0; i < selObj1.options.length; i++) {
			selObj1.options[i].selected = true;
		}
		for ( var i = 0; i < selObj2.options.length; i++) {
			selObj2.options[i].selected = true;
		}
		if (!validateform()) {
			return false;
		} else {
			document.forms['form1'].submit();
		}

	}
}

function checkforModify() {

	if (document.getElementById('newVillage').checked == true) {
		select();
		if (!validateform()) {
			return false;
		} else {
			document.forms['form1'].submit();
		}

		/* formSubmit(); */
	}

	else if (document.getElementById('isModifySubdistrict').checked == true) {

		formSubmitfinal();
	}

	else if (document.getElementById('newSubdistrict').checked == true) {
		select();
		if (!validateform()) {
			return false;
		} else {
			document.forms['form1'].submit();
		}
		/* formSubmitfinal(); */
	}

	else if (document.getElementById('isModifyVillage').checked == true) {

		formSubmit();
	}

	else if (document.getElementById('ShiftVillageroorg').checked == true) {
		alert("ShiftVillage");
		document.forms['form1'].submit();
	}

}

function validateform() {
	// var districtName = document.getElementById('districtNameInEn').value;
	var bool = $("#form1").validate().form();
	if (!bool) {
		$("#alertboxTitle").text("Message Dialog");
		$("#alertboxbody").text("Please fill mandatory fields in correct format");
		$('#alertbox').modal('show');	
		/*$("#dialog-confirm-dist-name").dialog({
			resizable : false,
			height : 140,
			modal : true,
			buttons : {
				Ok : function() {
					$(this).dialog("close");
				}
			}
		});*/
		return false;
	}
	if (document.getElementById('ddDestDistrict').options.length == 1) {
		document.getElementById('ddDestDistrict').options[0].selected = true;

		if (document.getElementById('ddDestDistrict').value.match('FULL') == "FULL") {
			alert("You Can't Make the New District by Shifting Only One	 Full District. Kindly Select One More Contributing District. !");
			return false;
		} else {
			return true;
		}

	}
	return true;
}

function addItemNew(list1, list2, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++)
			if (document.getElementById(list2).options[j].selected == true) {
				if (doAddVal) {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");
				}
			}
		removeSelectedValue(list2);
	}
}

window.onload = function() {
	if (document.getElementById("target2Src")) {
		document.getElementById("target2Src").onclick = function() {
			sureTransfer(document.getElementById('selectedDialogVillageList'), document.getElementById('dialogVillageList'), false);
		};
	}
};
/*
 * if (document.getElementById(list2).selectedIndex >= 0) { for ( var j = 0; j <
 * document.getElementById(list2).options.length; j++) { if
 * (document.getElementById(list2).options[j].selected == true) { if
 * (doRemoveVal) { $('#' + list1).append("<option value=" +
 * document.getElementById(list2).value.substr(0,
 * document.getElementById(list2).value.length - 4) + " >"+
 * document.getElementById(list2)[j].innerText + "</option>"); } else { $('#' +
 * list1).append("<option value=" + document.getElementById(list2)[j].innerText + " >" +
 * document.getElementById(list2)[j].innerText+ "</option>"); } } }
 * removeSelectedValue(list2); }
 */

/* Functions added and modified by Sushil */

/* For moving single selected option */
function removeOnedistrictOption(list1, list2, doRemoveVal) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		if(list1=="subDistrictListNew"){
			removeAllOptions('villageList');
			removeAllOptions('villageListNew');
		}else if(list1=="ddDestDistrict"){
			removeAllOptions('villageList');
			removeAllOptions('villageListNew');
			removeAllOptions('ddSubdistrictforsubdistrict');
			removeAllOptions('subDistrictListNew');
		}
		//alert(list1);
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					var _temp_value= document.getElementById(list1)[j].value;
					var _temp_text= document.getElementById(list1)[j].text;
					if(_temp_value.indexOf("PART")!=-1){
						_temp_value=_temp_value.substr(0,_temp_value.indexOf("PART")).trim();
						_temp_text=_temp_text.substr(0,_temp_text.indexOf("(PART)")).trim();
					}else if(_temp_value.indexOf("FULL")!=-1){
						_temp_value=_temp_value.substr(0,_temp_value.indexOf("FULL")).trim();
						_temp_text=_temp_text.substr(0,_temp_text.indexOf("(FULL)")).trim();
					}
					
					
					
					$('#' + list2).append(
							"<option value=" + _temp_value
									+ " >" +_temp_text
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
				}
			}
		}
		removeSelectedValue(list1);
	}
}

/* For moving all option */
function removeAllList(list1, list2, doRemoveVal) {
	selectAll(list1);
	if (document.getElementById(list1).selectedIndex >= 0) {
		if(list1=="subDistrictListNew"){
				removeAllOptions('villageList');
				removeAllOptions('villageListNew');
			}else if(list1=="ddDestDistrict"){
				removeAllOptions('villageList');
				removeAllOptions('villageListNew');
				removeAllOptions('ddSubdistrictforsubdistrict');
				removeAllOptions('subDistrictListNew');
			}
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					var _temp_value= document.getElementById(list1)[j].value;
					var _temp_text= document.getElementById(list1)[j].text;
					if(_temp_value.indexOf("PART")!=-1){
						_temp_value=_temp_value.substr(0,_temp_value.indexOf("PART")).trim();
						_temp_text=_temp_text.substr(0,_temp_text.indexOf("(PART)")).trim();
					}else if(_temp_value.indexOf("FULL")!=-1){
						_temp_value=_temp_value.substr(0,_temp_value.indexOf("FULL")).trim();
						_temp_text=_temp_text.substr(0,_temp_text.indexOf("(FULL)")).trim();
					}
					
					
					$('#' + list2).append(
							"<option value=" + _temp_value
									+ " >" +_temp_text
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
				}
			}
		}
		removeSelectedValue(list1);
	}
}

function removeSelectedValue(val) {
	var elSel = document.getElementById(val);
	for ( var i = elSel.length - 1; i >= 0; i--) {
		if (elSel.options[i].selected) {
			elSel.remove(i);
		}
	}
}

function selectAll(obj) {
	var selObj = document.getElementById(obj);
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}
}

function selectsubdistMarkPesa() {

	dwr.util.removeAllOptions("ddSubdistrictforsubdistrict");
	dwr.util.removeAllOptions("subDistrictListNew");
	dwr.util.removeAllOptions("villageList");
	//dwr.util.removeAllOptions("villageListNew");
	dwr.util.removeAllOptions("subDistrictListNewFull");

	var selObj = document.getElementById('ddDestDistrict');

	var idValuePart = "";
	var idValueFull = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		if ((selObj.options[i].value).match('PART') == 'PART') {
			if (idValuePart == "") {
				idValuePart = selObj.options[i].value;
			} else {
				idValuePart = idValuePart + "," + selObj.options[i].value;
			}
		}
		if ((selObj.options[i].value).match('FULL') == 'FULL') {
			if (idValueFull == "") {
				idValueFull = selObj.options[i].value;
			} else {
				idValueFull = idValueFull + "," + selObj.options[i].value;
			}
		}
		if (idValuePart != "") {
			lgdDwrDistrictService.getSubdistrictListbyDistrict(idValuePart, {
				callback : handleSubDistrictMarkPesa,
				errorHandler : handleSubDistErrorMarkPesa
			});
		}
		if (idValueFull != "") {
			lgdDwrDistrictService.getSubdistrictListbyDistrict(idValueFull, {
				callback : handleSubDistrictFullMarkPesa,
				errorHandler : handleSubDistErrorFullMarkPesa
			});
		}
	}
	if (idValuePart == "") {
		$("#dialog-district-button").dialog({
			resizable : false,
			height : 140,
			modal : true,
			buttons : {
				Ok : function() {
					$(this).dialog("close");
				}
			}
		});
		return false;
	}
}

function handleSubDistrictMarkPesa(data) {

	var fieldId = 'ddSubdistrictforsubdistrict';
	var fieldId2 = 'subDistrictListNew';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId2);
	var options = $("#subDistrictListNew");
	var options2 = $("#ddSubdistrictforsubdistrict");
	$.each(data, function(key, obj) {
		if (obj.is_pesa == 'F') {
			var option = $("<option/>");
			/* $(option).attr('disabled', 'disabled'); */// commented by anju
			$(option).val(obj.subdistrictCode + "FULL").text(obj.subdistrictNameEnglish + ' [ ' + obj.districtNameEnglish + ' ] ' + "(FULL)");
			options.append(option);
		} else if (obj.is_pesa == 'P') {
			var option = $("<option/>");
			/* $(option).attr('disabled', 'disabled'); */// commented by anju
			$(option).val(obj.subdistrictCode + "PART").text(obj.subdistrictNameEnglish + ' [ ' + obj.districtNameEnglish + ' ] ' + "(PART)");
			options.append(option);
		} else if (obj.is_pesa == 'N') {
			var option2 = $("<option/>");
			$(option2).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish + ' [ ' + obj.districtNameEnglish + ' ] ');
			options2.append(option2);
		}

	});
}

function handleSubDistErrorMarkPesa() {
	$("#dialog-confirm-nodata").dialog({
		resizable : false,
		height : 140,
		modal : true,
		buttons : {
			Ok : function() {
				$(this).dialog("close");
			}
		}
	});
}

function handleSubDistrictFullMarkPesa(data) {
	var fieldId = 'subDistrictListNewFull';
	var valueText = 'subdistrictCode';
	var nameText = 'subdistrictNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleSubDistErrorFullMarkPesa() {

	$("#dialog-confirm-nodata").dialog({
		resizable : false,
		height : 140,
		modal : true,
		buttons : {
			Ok : function() {
				$(this).dialog("close");
			}
		}
	});

}

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

removeAllOptions=function(id){
	dwr.util.removeAllOptions(id);
};


getVillageListofPartSubdistrict=function(){
	var subMTODMAP =new Map();
	var subdistPartArr = []; 
	$('#subDistrictListNew option').each(function() { 
		var _val=$(this).val();
		if(_val.indexOf("PART")!=-1){
			var _tlc=_val.substring(0, _val.indexOf("PART"));
			subdistPartArr.push(_tlc);
			if(_val.indexOf("SUBMTOD")!=-1){
				subMTODMAP.set(_val,_val);	
			}
		
		}
		
	});
	
	
};

