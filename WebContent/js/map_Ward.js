function getZPWardsLists(id) {
	$("#GpListAvailable").empty();
	$("#GpListSelected").empty();
	$("#VillageListAvailable").empty();
	$("#VillageListContributing").empty();
	lgdDwrlocalBodyService.getlocalGovtBodyWardList(id, {
		callback : handleZPWardSuccess,
		errorHandler : handleInterPanchayatWardError
	});
}

function getZPIntermediatePanchyat(id) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleZPIntermediateSuccess,
		errorHandler : handleInterPanchayatWardError
	});
}
function handleZPIntermediateSuccess(data) {
	var fieldId = 'InterPanchyatID';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('InterPanchyatID');
	st.add(new Option('Select Block Panchayat', '0'));

	var options = $("#InterPanchyatID");
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
}

function handleZPWardSuccess(data) {
	var fieldId = 'WardCode';
	var valueText = 'wardCode';
	var nameText = 'wardNameInEnglish';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('WardCode');
	st.add(new Option('Select Ward Name', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getVillagePanchyatListforzp(id) {
	var lblist = document.getElementById('GpListSelected');
	var str = "";
	for ( var x = 0; x < lblist.length; x++) {
		str = lblist.options[x].value;
		if (str.indexOf('MAPD') == -1 || str.indexOf('PART') == -1) {

		}
		// lblist.remove(x);
	}
	var ddsource = document.getElementById('WardCode');
	if (ddsource.selectedIndex == 0) {
		alert("Please Select Ward");
		document.getElementById('InterPanchyatID').selectedIndex = 0;
		return false;
	} else

		lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
			callback : handleGPCASuccess,
			errorHandler : handleError
		});

}
function handleGPCASuccess(data) {

	var fieldId = 'GpListAvailable';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	var val = "3";

	dwr.util.removeAllOptions(fieldId);
	// added on 31/12/2014 for lgd impact by kirandeep
	var options = $("#GpListAvailable");
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
		removeMappedGPList(val);

	var selObj = document.getElementById('WardCode');
	var str = 0;
	for ( var j = 0; j < selObj.options.length; j++) {
		str = selObj.options[j].value + "," + str;

	}

	lgdDwrlocalBodyService.checkForExistingGp(str, {
		callback : function(data) {
			var status = true;

			var selObj = document.getElementById("GpListAvailable");

			for ( var i = 0; i < data.length; i++) {
				for ( var j = 0; j < selObj.options.length; j++) {

					if (selObj.options[j].value == data[i].localBodyCode) {
						// alert(selObj.options[j].value);
						selObj.options[j].text = selObj.options[j].text + "(PART)";

						// status = false;
						// return false;
						// alert("found");
					}

				}
			}

		},
		errorHandler : handleError
	});

}
function handleError(data) {
}

function addItemforZP(list1, list2, val, doAddVal) {
	var listofPartGpvalues = "0";
	if (val == 'PART') {

		/*
		 * var listofselectedGp = document.getElementById("GpListAvailable"); {
		 * for ( var k = 0; k < listofselectedGp.options.length; k++) { if
		 * (listofselectedGp.options[k].selected == true) {
		 * 
		 * if (listofselectedGp.options[k].value == code) { alert("mapped"); }
		 * else { } } } }
		 */

		var listofPartsGp = document.getElementById("GpListSelected");

		for ( var j = 0; j < listofPartsGp.options.length; j++) {
			var str = listofPartsGp.options[j].value;
			var checkFUllPart = str.substr(str.length - 4, str.length);

			if (checkFUllPart == 'PART') {

				var code = str.substr(0, str.length - 4);

				listofPartGpvalues = code + "," + listofPartGpvalues;
				// alert(listofPartGpvalues);

			}
		}

		if (document.getElementById(list2).selectedIndex >= 0) {
			for ( var j = 0; j < document.getElementById(list2).options.length; j++)
				if (document.getElementById(list2).options[j].selected == true) {
					var str = document.getElementById(list2).options[j].text;

					if (doAddVal) {
						var checkFUllPart = str.substr(str.length - 4, str.length);

						if (checkFUllPart == val) {

							var temp = listofPartGpvalues.split(",");
							// var selObj =
							// document.getElementById('GpListAvailable');
							var i = 0;

							var status = true;
							for (i = 0; i < temp.length; i++) {
								if (document.getElementById(list2).options[j].value == temp[i]) {
									status = false;
								}
							}
							if (status)
								$('#' + list1).append(
										"<option value=" + document.getElementById(list2)[j].value + val + ">"
												+ document.getElementById(list2)[j].text + "</option>");
						} else {
							var temp = listofPartGpvalues.split(",");
							// var selObj =
							// document.getElementById('GpListAvailable');
							var i = 0;

							var status = true;
							for (i = 0; i < temp.length; i++) {
								if (document.getElementById(list2).options[j].value == temp[i]) {
									status = false;
								}
							}
							if (status) {
								$('#' + list1).append(
										"<option value=" + document.getElementById(list2)[j].value + val + ">"
												+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
							}

						}

					} else {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
										+ "</option>");
					}

				}

			removeSelectedValue(list2);
		}

	} else {
		var listofPartsGp = document.getElementById("VillageListAvailable");
		var status = true;
		for ( var j = 0; j < listofPartsGp.options.length; j++) {
			if (listofPartsGp.options[j].selected == true) {
				var str = listofPartsGp.options[j].value;
				var villagelistContributinValidation = document.getElementById("VillageListContributing");

				for ( var k = 0; k < villagelistContributinValidation.options.length; k++) {
					var str1 = villagelistContributinValidation.options[k].value;
					var checkFUllPart = str1.substr(str1.length - 4, str1.length);
					if (checkFUllPart == 'FULL' || checkFUllPart == 'MAPD') {

						var code = str1.substr(0, str1.length - 4);
						if (code == str) {
							status = false;
						}

						// alert(listofPartGpvalues);

					}

				}
				if (status) {
					if (document.getElementById(list2).selectedIndex >= 0) {
						for ( var j = 0; j < document.getElementById(list2).options.length; j++)
							if (document.getElementById(list2).options[j].selected == true) {
								var str = document.getElementById(list2).options[j].text;

								if (doAddVal) {
									var checkFUllPart = str.substr(str.length - 4, str.length);

									if (checkFUllPart == val) {
										$('#' + list1).append(
												"<option value=" + document.getElementById(list2)[j].value + val + ">"
														+ document.getElementById(list2)[j].text + "</option>");
									} else {
										$('#' + list1).append(
												"<option value=" + document.getElementById(list2)[j].value + val + ">"
														+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
									}

								} else
									$('#' + list1).append(
											"<option value=" + document.getElementById(list2)[j].value + " >"
													+ document.getElementById(list2)[j].text + "</option>");
							}

						removeSelectedValue(list2);
					}
				}

			}

		}

	}

}

function addItemforZP1(list1, list2, val, doAddVal) {
	var selObj = document.getElementById('WardCode');
	var str = 0;
	for ( var j = 0; j < selObj.options.length; j++) {
		str = selObj.options[j].value + "," + str;

	}

	lgdDwrlocalBodyService.checkForExistingGp(str, {
		callback : function(data) {
			var status = true;
			;
			var selObj = document.getElementById("GpListAvailable");
			for ( var i = 0; i < data.length; i++) {
				for ( var j = 0; j < selObj.options.length; j++) {
					if (selObj.options[j].selected == true) {
						if (selObj.options[j].value == data[i].localBodyCode) {
							alert("Can not select as Whole");
							status = false;
							// return false;
							// alert("found");
						}
					}

				}
			}
			if (status == true) {
				getVillageListsforzpwardmappingvalid(list1, list2, val, doAddVal);
			}

		},
		errorHandler : handleError
	});

}

function removesingleItemforgp(list1, list2, doRemoveVal) {
	$("#VillageListAvailable").empty();
	$("#VillageListContributing").empty();
	if (document.getElementById(list1).selectedIndex >= 0) {
		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 4)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + "</option>");
		removeSelectedValue(list1);
	}

}
function removesingleItemforgpVillage(list1, list2, doRemoveVal) {
	if (document.getElementById(list1).selectedIndex >= 0) {
		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 4)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + "</option>");
		removeSelectedValue(list1);
	}

}

function removeAllsgpward(list1, list2, doRemoveVal) {
	$("#VillageListAvailable").empty();
	$("#VillageListContributing").empty();
	for ( var i = document.getElementById(list1).length - 1; i >= 0; i--) {
		document.getElementById(list1).selectedIndex = i;

		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 4)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + "</option>");
		removeSelectedValue(list1);
	}
}

function getVillageListsforzpwardmapping() {

	var selObj = document.getElementById('GpListSelected');
	var localbodyCode = "";
	for ( var i = 0; i < selObj.options.length; i++) {
		// selObj.options[i].selected = true;
		localbodyCode += selObj.options[i].value + ",";
	}
	if (localbodyCode == '') {
		alert("Please Select PART to get Covered areas.");
	} else if (!localbodyCode.match('PART')) {
		// dwr.util.removeAllOptions('villageList');
		alert("Please Select Part Village Panchayat List ");
	} else {
		getVillageListatDPNew(localbodyCode);

	}
}

function getVillageListsforzpwardmappingvalid(list1, list2, val, doAddVal) {

	var selObj = document.getElementById('GpListAvailable');
	var localbodyCode = "";
	var selObj1 = document.getElementById('VillageListContributing');
	var mappedcodes = "";

	for ( var i = 0; i < selObj1.options.length; i++) {
		// selObj1.options[i].selected = true;
		var str = selObj1.options[i].value;
		var checkFUllPart = str.substr(str.length - 4, str.length);

		if (checkFUllPart == 'MAPD')
			mappedcodes += selObj1.options[i].value + ",";
	}

	for ( var i = 0; i < selObj.options.length; i++) {
		if (selObj.options[i].selected == true) {
			localbodyCode = selObj.options[i].value + ",";
			var name = selObj.options[i].text;
			getVillageListatDPNewvalid(localbodyCode, mappedcodes, list1, list2, val, doAddVal, name);
		}
	}

}

function getVillageListatDPNewvalid(id, mappedcodes, list1, list2, val, doAddVal, name) {
	/*
	 * var selObj = document.getElementById('VillageListContributing'); var str =
	 * ""; for ( var j = 0; j < selObj.options.length; j++) { str =
	 * selObj.options[j].text; if (str.indexOf('MAPD') == -1) {
	 * selObj.remove(j); } }
	 */
	lgdDwrlocalBodyService.getLocalGovtBodyCoveredVillageListValid(id, mappedcodes, {
		callback : getVillagesListResultSuccessvalid,
		arg : name,
		errorHandler : getVillageResultListError
	});

}

function getVillageListatDPNew(id) {
	/*
	 * var selObj = document.getElementById('VillageListContributing'); var str =
	 * ""; for ( var j = 0; j < selObj.options.length; j++) { str =
	 * selObj.options[j].text; if (str.indexOf('MAPD') == -1) {
	 * selObj.remove(j); } }
	 */
	lgdDwrlocalBodyService.getLocalGovtBodyCoveredVillageList(id, {
		callback : getVillagesListResultSuccess,
		errorHandler : getVillageResultListError
	});

}

function getVillagesListResultSuccessvalid(data, name) {
	var list1;
	var list2;
	var val;
	var doAddVal;
	if (data != null) {
		list1 = 'GpListSelected';

		list2 = 'GpListAvailable';
		val = 'FULL';
		doAddVal = true;

		if (document.getElementById(list2).selectedIndex >= 0) {
			for ( var j = 0; j < document.getElementById(list2).options.length; j++)
				if (document.getElementById(list2).options[j].selected == true)
					if (document.getElementById(list2).options[j].text == name)
						if (doAddVal)
							$('#' + list1).append(
									"<option value=" + document.getElementById(list2)[j].value + val + ">" + document.getElementById(list2)[j].text
											+ " (" + val + ")</option>");
						else
							$('#' + list1).append(
									"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
											+ "</option>");
			removeSelectedValuevalid(list2, name);

		}

	}

	if (data == null) {
		alert(name + " Can't be selected as Full");
	}

	/*
	 * var fieldId = 'VillageListAvailable'; var valueText = 'landRegionCode';
	 * var nameText = 'landRegionNameEnglish';
	 * 
	 * dwr.util.removeAllOptions(fieldId);
	 * 
	 * dwr.util.addOptions(fieldId, data, valueText, nameText); if (data.length >
	 * 0) removeMappedGPList("V");
	 */
}

// data contains the returned value
function getVillagesListResultSuccess(data) {
	for ( var i = 0; i < data.length; i++) {
		if (data[i] == null) {
			data.remove(i);
		}

	}

	var fieldId = 'VillageListAvailable';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var options = $("#VillageListAvailable");
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
	if (data.length > 0)
		removeMappedGPList("V");
}
function onSubmit() {
	var lblist = document.getElementById("GpListSelected");
	var lbMappedList = document.getElementById("mappedgpList").value;
	var lbMappedVillageList = document.getElementById('mappedvillageList').value;
	deleteMappedEntiyLbList(lbMappedList);
	deleteMappedEntiyVillageList(lbMappedVillageList);
	var temp = "";
	var str;
	var list = "";
	for ( var x = 0; x < lblist.length; x++) {
		str = lblist.options[x].value;
		temp = str.substring(0, str.length - 4);
		temp = ":" + temp + ":";
		if (str.indexOf('FULL') > -1 && lbMappedList.indexOf(temp) == -1) {
			str = str.substring(0, str.length - 4);
			list += str + ":";

		}

	}
	list = list.substring(0, list.length - 1);
	document.getElementById('listformat').value = list;
	list = "";
	lblist = document.getElementById("VillageListContributing");
	for ( var x = 0; x < lblist.length; x++) {
		str = lblist.options[x].value;
		temp = str.substring(0, str.length - 4);
		temp = ":" + temp + ":";
		if (str.indexOf('FULL') > -1 && lbMappedVillageList.indexOf(temp) == -1) {
			str = str.substring(0, str.length - 4);
			list += str + ":";

		}

	}
	list = list.substring(0, list.length - 1);
	if (list == "") {

		var lblist1 = document.getElementById("VillageListContributing");
		for ( var x = 0; x < lblist1.length; x++) {
			if (lblist1.options[x].selected == true)
				lblist1.options[x].selected == false;

		}

	}

	document.getElementById('contVillage').value = list;
	document.getElementById('form1').action = "mapZpConsituencyData.htm";
	document.forms['form1'].submit();
}
function checkData() {
	var lblist = "";
	var str;
	var count = 0;
	var status = true;
	var len = 0;
	len = document.getElementById("GpListSelected").length + document.getElementById("VillageListContributing").length;
	if (document.getElementById('ZilaPanchyatID').selectedIndex == 0) {
		alert("Please Select Zilla Panchayat");
		status = false;
	} else if (document.getElementById('WardCode').selectedIndex == 0) {
		alert("Please Select Ward");
		status = false;
	} else if (document.getElementById('InterPanchyatID').selectedIndex == 0 && len == 0) {
		alert("Please Select Block Panchayat");
		status = false;
	} else {

		lblist = document.getElementById("GpListSelected");
		for ( var x = 0; x < lblist.length; x++) {
			str = lblist.options[x].value;
			if (!(str.indexOf('PART') > -1)) {
				count++;

			}

		}
		lblist = document.getElementById("VillageListContributing");
		count = count + lblist.length;
		/*
		 * for ( var x = 0; x < lblist.length; x++) { str =
		 * lblist.options[x].value; if (str.indexOf('FULL') > -1) { count++; } }
		 */
		if (count == 0) {
			alert("Please Select Mapped Entity");
			status = false;
		}

	}
	if (status)
		onSubmit();

}

function checkData1() {
	var lblist = "";
	var str;
	var count = 0;
	var status = true;
	var len = 0;
	len = document.getElementById("GpListSelected").length + document.getElementById("VillageListContributing").length;
	if (document.getElementById('ZilaPanchyatID').selectedIndex == 0) {
		alert("Please Select Zilla Panchayat");
		status = false;
	} else if (document.getElementById('WardCode').selectedIndex == 0) {
		alert("Please Select Ward");
		status = false;
	} else {

		lblist = document.getElementById("GpListSelected");
		for ( var x = 0; x < lblist.length; x++) {
			str = lblist.options[x].value;
			if (!(str.indexOf('PART') > -1)) {
				count++;

			}

		}
		lblist = document.getElementById("VillageListContributing");
		count = count + lblist.length;
		/*
		 * for ( var x = 0; x < lblist.length; x++) { str =
		 * lblist.options[x].value; if (str.indexOf('FULL') > -1) { count++; } }
		 */
		if (count == 0) {
			alert("Please Select Mapped Entity");
			status = false;
		}

	}
	if (status)
		onSubmit();

}
function removeMappedGPList(val) {
	var wholeData = "true";
	var wardid = 0;
	if (val == 3)
		lgdDwrlocalBodyService.mappedZpWardConsituencyDetail(wardid, val, wholeData, {
			callback : RemoveWholeLBMappedEntitySuccess,
			errorHandler : handleError
		});
	else if (val == 'V')
		lgdDwrlocalBodyService.mappedZpWardConsituencyDetail(wardid, val, wholeData, {
			callback : RemoveWholeVillageMappedEntitySuccess,
			errorHandler : handleError
		});

}
function RemoveWholeLBMappedEntitySuccess(data) {
	var selObj = document.getElementById("GpListAvailable");
	for ( var i = 0; i < data.length; i++) {
		for ( var j = 0; j < selObj.options.length; j++) {
			if (selObj.options[j].value == data[i].localBodyCode) {
				selObj.remove(j);
				break;
			}
		}
	}

}
function RemoveWholeVillageMappedEntitySuccess(data) {
	var selObj = document.getElementById("VillageListAvailable");
	for ( var i = 0; i < data.length; i++) {
		for ( var j = 0; j < selObj.options.length; j++) {
			if (selObj.options[j].value == data[i].localBodyCode) {
				selObj.remove(j);
				break;
			}
		}
	}

}
function fillMappedEntities(wardid) {

	$("#GpListAvailable").empty();
	$("#GpListSelected").empty();
	$("#VillageListAvailable").empty();
	$("#VillageListContributing").empty();
	document.getElementById("InterPanchyatID").selectedIndex = 0;
	var type = '3';
	var wholeData = "false";
	lgdDwrlocalBodyService.mappedZpWardConsituencyDetail(wardid, type, wholeData, {
		callback : handleMappedEntitySuccess,
		errorHandler : handleError
	});
}

function fillMappedEntities1(wardid) {
	$("#GpListAvailable").empty();
	$("#GpListSelected").empty();
	$("#VillageListAvailable").empty();
	$("#VillageListContributing").empty();
	// document.getElementById("InterPanchyatID").selectedIndex = 0;
	getVillagePanchyatListforzp($("#ZilaPanchyatID").val());
	var type = '3';
	var wholeData = "false";
	lgdDwrlocalBodyService.mappedZpWardConsituencyDetail(wardid, type, wholeData, {
		callback : handleMappedEntitySuccess,
		errorHandler : handleError
	});
}

function handleMappedEntitySuccess(data) {
	var fieldId = 'GpListSelected';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
	if (data.length > 0)
		ListMappedEntities(1);
	fillMappedEntitiesforVillage();
}
function fillMappedEntitiesforVillage() {
	var wardid = document.getElementById("WardCode").value;
	var type = 'V';
	var wholeData = "false";
	lgdDwrlocalBodyService.mappedZpWardConsituencyDetail(wardid, type, wholeData, {
		callback : handleMappedEntityVillageSuccess,
		errorHandler : handleError
	});
}
function handleMappedEntityVillageSuccess(data) {
	var fieldId = 'VillageListContributing';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
	if (data.length > 0)
		ListMappedEntities(2);

}

function ListMappedEntities(type) {
	var selObj = document.getElementById('GpListSelected');
	var str = "";
	var val = "";
	if (type == 1) {
		for ( var j = 0; j < selObj.options.length; j++) {
			str = selObj.options[j].value;
			str = str.substr(0, str.length - 4);
			val = str + ":" + val;

		}

		document.getElementById('mappedgpList').value = ":" + val;
	}
	val = "";
	selObj = document.getElementById('VillageListContributing');
	if (type == 2) {
		for ( var j = 0; j < selObj.options.length; j++) {
			str = selObj.options[j].value;
			str = str.substr(0, str.length - 4);
			val = str + ":" + val;
		}
		document.getElementById('mappedvillageList').value = ":" + val;
	}

}
function deleteMappedEntiyLbList(value) {
	value = value.substr(1, value.length - 2);
	var temp = value.split(":");
	var selObj = document.getElementById('GpListAvailable');
	var i = 0;
	var val = "";
	for (i = 0; i < temp.length; i++) {
		for ( var j = 0; j < selObj.options.length; j++) {
			str = selObj.options[j].value;
			if (str == temp[i])
				val = temp[i] + "," + val;
		}
	}
	val = val.substr(0, val.length - 1);
	document.getElementById('deletedlbList').value = val;

}
function deleteMappedEntiyVillageList(value) {
	value = value.substr(1, value.length - 2);
	var temp = value.split(":");
	var selObj = document.getElementById('VillageListAvailable');
	var i = 0;
	var val = "";
	for (i = 0; i < temp.length; i++) {
		for ( var j = 0; j < selObj.options.length; j++) {
			str = selObj.options[j].value;
			if (str == temp[i])
				val = temp[i] + "," + val;
		}
	}
	val = val.substr(0, val.length - 1);
	document.getElementById('deletedvillageList').value = val;

}

function checkExistingGramPanchayat() {

	var selObj = document.getElementById('WardCode');
	var str = 0;
	for ( var j = 0; j < selObj.options.length; j++) {
		if (selObj.options[j].selected == true)
			str = selObj.options[j].value;

	}

	lgdDwrlocalBodyService.checkForExistingGp(str, {
		callback : function(data) {
			var status = true;

			var selObj = document.getElementById("GpListAvailable");

			for ( var i = 0; i < data.length; i++) {

				$('#GpListSelected').append(
						"<option value=" + data[i].localBodyCode + "PART" + " >" + data[i].localBodyNameEnglish + "(PART)" + "</option>");
				// status = false;
				// return false;
				// alert("found");

			}

		},
		errorHandler : handleError
	});

}
// checkForExistingGpSuccess

