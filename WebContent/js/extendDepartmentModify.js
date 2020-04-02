/**
 * 
 */

registerOnclickMethods = function() {
	$('#mvFrState').click(function() {
		listBoxMoveAcross('ddSourceState', 'ddTargetState');
		if (accessLevel == _DISTRICT_LEVEL) {
			if (isCenterUser) {
				if ($("#ChkPartialCoverage").is(':checked')) {

					removeAllOptions("stateName");
					$("#tblStateList").hide(); // hiding the State list
					removeAllOptions("dddistrictAtStateLvl");
					removeAllOptions("ddTargetDistrict");
					$("#tblDistrictSpecific").hide();
					$("#ChkPartialCoverage").prop('checked', false);

				}
			}
		}
		if (accessLevel == _SUBDISTRICT_LEVEL) {

			if (isCenterUser) {
				if ($("#districtChkFull").is(':checked')) {
					removeAllOptions("stateName1");
					$("#tblStateList1").hide();

					removeAllOptions("dddistrictAtStateLvl");
					removeAllOptions("ddTargetDistrict");
					$("#tblDistrictSpecific").hide();

					// $("#tblDistrictChkPart").hide();
					$("#districtChkFull").prop('checked', false);

				}
				if ($("#DistrictChkPart").is(':checked')) {

					$("#DistrictChkPart").prop('checked', false);
					removeAllOptions("stateNameDistforSD");

					$("#tblDistrictChkPart").hide();

					removeAllOptions("district");
					$("#tblDistrictSelectBox").hide();
					$("#tblDistrictSelectBox1").hide();

					removeAllOptions("ddSubdistrictAtSubDistrictLvl");
					removeAllOptions("ddTargetDistrictSDLvl");
					$("#tblSubdistrictSpecific").hide();

				}
			}

		}
		if (accessLevel == _VILLAGE_LEVEL) {
			if (isCenterUser) {
				if ($("#districtChkFull").is(':checked')) {
					$("#tblSubDistrictSelectState").hide();
					$("#tblSubDistrictChkPart").hide();
					$("#tblDistrictSelectBox").hide();
					$("#tblDistrictSelectBox1").hide();
					$("#tblStateList1").hide();

					$("#tblSubDistrictChkFull").hide();
					removeAllOptions("stateName1");
					removeAllOptions("ddTargetState");

					removeAllOptions("stateNameDistforSD");
					removeAllOptions("district");
					removeAllOptions("stateNameSubDistForVillage");
					$("#SubDistrictChkFull").prop('checked', false);

					clearDistrictWithFullCoverage();
					clearSubdistrictLevel();
					clearVillageLevel();
				}

			}

		}

	});
	$('#mvBackState').click(function() {
		listBoxMoveAcross('ddTargetState', 'ddSourceState');
		if (accessLevel == _DISTRICT_LEVEL) {
			if (isCenterUser) {
				if ($("#ChkPartialCoverage").is(':checked')) {

					removeAllOptions("stateName");
					$("#tblStateList").hide(); // hiding the State list
					removeAllOptions("dddistrictAtStateLvl");
					removeAllOptions("ddTargetDistrict");
					$("#tblDistrictSpecific").hide();
					$("#ChkPartialCoverage").prop('checked', false);

				}
			}
		}

		if (accessLevel == _SUBDISTRICT_LEVEL) {

			if (isCenterUser) {
				if ($("#districtChkFull").is(':checked')) {
					removeAllOptions("stateName1");
					$("#tblStateList1").hide();

					removeAllOptions("dddistrictAtStateLvl");
					removeAllOptions("ddTargetDistrict");
					// $("#tblDistrictSpecific").hide();

					$("#tblDistrictChkPart").hide();
					$("#districtChkFull").prop('checked', false);

				}
				if ($("#DistrictChkPart").is(':checked')) {

					$("#DistrictChkPart").prop('checked', false);
					removeAllOptions("stateNameDistforSD");

					$("#tblDistrictChkPart").hide();

					removeAllOptions("district");
					$("#tblDistrictSelectBox").hide();
					$("#tblDistrictSelectBox1").hide();

					removeAllOptions("ddSubdistrictAtSubDistrictLvl");
					removeAllOptions("ddTargetDistrictSDLvl");
					$("#tblSubdistrictSpecific").hide();

				}
			}

		}

	});

	$('#mvFrDistt').click(function() {
		if (accessLevel == _VILLAGE_LEVEL) {

			if (!isCenterUser) {
				if ($("#SubDistrictChkFull").is(':checked')) {

					clearDistrictWithPartialCoverage(true);
					$("#SubDistrictChkFull").prop('checked', false);

				}

			} else {
				if ($("#SubDistrictChkFull").is(':checked')) {
					clearDistrictWithPartialCoverage();
					clearVillageLevel();
					$("#tblDistrictSelectBox1").hide();
					$("#tblSubDistrictSelectState").hide();
					removeAllOptions("stateNameDistforSD");
					removeAllOptions("stateNameSubDistForVillage");
				}
			}
		} else if (accessLevel == _SUBDISTRICT_LEVEL || accessLevel == _BLOCK_LEVEL) {
			if (isCenterUser) {

			} else {
				if ($("#DistrictChkPart").is(':checked')) {

					clearDistrictWithPartialCoverage(true);
					$("#SubDistrictChkFull").prop('checked', false);
				}
			}

		}
		if (accessLevel == _SUBDISTRICT_LEVEL) {
			if (isCenterUser) {

				if ($("#DistrictChkPart").is(':checked')) {

					$("#DistrictChkPart").prop('checked', false);
					removeAllOptions("stateNameDistforSD");

					removeAllOptions("district");
					$("#tblDistrictSelectBox").hide();
					$("#tblDistrictSelectBox1").hide();

					removeAllOptions("ddSubdistrictAtSubDistrictLvl");
					removeAllOptions("ddTargetDistrictSDLvl");
					$("#tblSubdistrictSpecific").hide();

				}

			}
		}
		listBoxMoveAcross('dddistrictAtStateLvl', 'ddTargetDistrict');

	});

	$('#mvBackDistt').click(function() {
		listBoxMoveAcross('ddTargetDistrict', 'dddistrictAtStateLvl');
		if (accessLevel == _SUBDISTRICT_LEVEL || accessLevel == _BLOCK_LEVEL) {
			if (isCenterUser) {
				$("#tblDistrictSelectBox1").hide();

			}
			clearDistrictWithPartialCoverage(true);
		} else if (accessLevel == _VILLAGE_LEVEL) {
			clearSubdistrictLevel();
			$("#tblDistrictSelectBox").hide();
			$("#SubDistrictChkFull").prop('checked', false);
			$("#tblSubDistrictChkPart").hide();
			clearVillageLevel();
			if (isCenterUser) {
				$("#tblDistrictSelectBox1").hide();
				$("#tblSubDistrictSelectState").hide();

			}

		}

	});
	$('#mvFrSubDist').click(function() {
		listBoxMoveAcross('ddSubdistrictAtSubDistrictLvl', 'ddTargetDistrictSDLvl');
		if ($("#SubDistrictChkPart").is(':checked')) {
			if (!isCenterUser) {
				if (accessLevel == _VILLAGE_LEVEL) {

					clearVillageLevel();

				}
			}
			if (isCenterUser) {
				if (accessLevel == _VILLAGE_LEVEL) {

					clearVillageLevel();
					$("#tblSubDistrictSelectState").hide();
				}
			}
		}
	});

	$('#mvBackSubDist').click(function() {
		listBoxMoveAcross('ddTargetDistrictSDLvl', 'ddSubdistrictAtSubDistrictLvl');
		if (accessLevel == _VILLAGE_LEVEL) {
			clearVillageLevel();
			if (isCenterUser) {

				$("#tblSubDistrictSelectState").hide();
			}

		}

	});
	$('#mvFrBlock').click(function() {
		listBoxMoveAcross('ddBlockAtDistrictLvl', 'ddTargetBlockSDLvl');
	});

	$('#mvBackBlock').click(function() {
		listBoxMoveAcross('ddTargetBlockSDLvl', 'ddBlockAtDistrictLvl');
	});

	$('#mvFrVillage').click(function() {
		listBoxMoveAcross('ddVillageAtVillLvl', 'ddTargetDistrictVillLvl');
	});

	$('#mvBackVillage').click(function() {
		listBoxMoveAcross('ddTargetDistrictVillLvl', 'ddVillageAtVillLvl');
	});

	$('#mvFrAdminEntity').click(function() {
		listBoxMoveAcross('ddAdminEnitiy', 'ddTargetAdminEnitiy');
	});

	$('#mvBackAdminEntity').click(function() {
		listBoxMoveAcross('ddTargetAdminEnitiy', 'ddAdminEnitiy');
	});
};

initialShowHide = function() {

	if (accessLevel == _STATE_LEVEL) {
		if (isCenterUser) {
			$("#tblPartialCoverage").hide();
		}

	} else if (accessLevel == _DISTRICT_LEVEL) {
		if (isCenterUser) {

			$("#tblDistrictSpecific").hide();
			$("#tblStateList").hide();
			$("#tblStateSpecific").hide();
			// $("#tblPartialCoverage").hide();
		}

	} else if (accessLevel == _SUBDISTRICT_LEVEL) {
		$("#tblDistrictSpecific").hide();
		// $("#tblDistrictChkPart").hide();
		$("#tblDistrictSelectBox").hide();
		$("#tblSubdistrictSpecific").hide();
		if (isCenterUser) {

			$('#tblStateSpecific').hide();
			// $('#tblCheckCoverageDistrictFull').hide();
			$("#tblPartialCoverage").hide();
			$("#tblDistrictSelectBox1").hide();
			$("#tblStateList1").hide();

		}

	} else if (accessLevel == _BLOCK_LEVEL) {
		$("#tblDistrictSpecific").hide();
		$("#tblDistrictSelectBox").hide();
		$("#tblBlockSpecific").hide();

	} else if (accessLevel == _VILLAGE_LEVEL) {
		$("#tblSubDistrictSelectState").hide();

		$("#tblDistrictSpecific").hide();
		$("#tblDistrictChkPart").hide();
		$("#tblDistrictSelectBox").hide();
		// $("#tblSubDistrictChkFull").hide();
		$("#tblSubdistrictSpecific").hide();
		// $("#tblSubDistrictChkPart").hide();
		$("#tblDisttSubDisttVillLvlSelectBox").hide();
		$("#tblVillageSpecific").hide();
		if (isCenterUser) {
			$('#tblStateSpecific').hide();
			$("#tblDistrictSelectBox1").hide();
			$("#tblStateList1").hide();
			// $("#tblCheckCoverageDistrictFull").hide();
			$("#tblPartialCoverage").hide();

		}
	} else if (accessLevel == _ADMINISTRATIVE_LEVEL) {

	}
};

showAllOrSpecificDetails = function(paramAllOrSpecificLevel) {
	if (paramAllOrSpecificLevel == "F") {
		$("#tblAllSpecificDetails").hide();

		if (accessLevel == _VILLAGE_LEVEL) {
			clearVillageLevel();
			clearSubdistrictLevel();

			$("#tblSubDistrictChkFull").hide();
			$("#SubDistrictChkFull").prop('checked', false);
			$("#SubDistrictChkPart").prop('checked', false);

			$("#tblSubDistrictChkPart").hide();
			if (isCenterUser) {

				$("#tblSubDistrictSelectState").hide();
				$("#tblSubDistrictChkPart").hide();
				$("#tblDistrictSelectBox").hide();
				$("#tblDistrictSelectBox1").hide();
				$("#tblStateList1").hide();
				$("#tblStateSpecific").hide();

				$("#tblSubDistrictChkFull").hide();
				removeAllOptions("stateName1");
				removeAllOptions("ddTargetState");
				$("#tblCheckCoverageDistrictFull").hide();

				removeAllOptions("stateNameDistforSD");
				removeAllOptions("district");
				removeAllOptions("stateNameSubDistForVillage");
				$("#SubDistrictChkFull").prop('checked', false);
				$("#ChkFullCoverage").prop('checked', false);

				clearDistrictWithFullCoverage();
				clearSubdistrictLevel();
				clearVillageLevel();

			}

		}
		if (accessLevel == _DISTRICT_LEVEL) {
			if (isCenterUser) {
				$("#tblDistrictSpecific").hide();
				removeAllOptions("stateName");
				$("#tblStateList").hide();
				removeAllOptions("ddTargetState");
				$("#tblStateSpecific").hide();
				$("#tblPartialCoverage").hide();
				$("#ChkPartialCoverage").prop('checked', false);
				$("#ChkFullCoverage").prop('checked', false);

			}

		}

		if (accessLevel == _SUBDISTRICT_LEVEL) {
			if (isCenterUser) {

				$("#ChkFullCoverage").prop('checked', false);
				removeAllOptions("stateName1");
				removeAllOptions("ddTargetState");
				$("#tblStateSpecific").hide();
				$("#tblStateList1").hide();

				$("#tblDistrictSelectBox1").hide();

				removeAllOptions("dddistrictAtStateLvl");
				removeAllOptions("ddTargetDistrict");
				$("#tblDistrictSpecific").hide();

				$("#tblCheckCoverageDistrictFull").hide();

				removeAllOptions("stateNameDistforSD");

				$("#DistrictChkPart").prop('checked', false);
				$("#tblDistrictChkPart").hide();

				removeAllOptions(district);
				$("#tblDistrictSelectBox").hide();

				removeAllOptions("ddSubdistrictAtSubDistrictLvl");
				removeAllOptions("ddTargetDistrictSDLvl");
				$("#tblSubdistrictSpecific").hide();

			}

		}

	} else {
		$("#tblAllSpecificDetails").show();
		if (accessLevel != _DISTRICT_LEVEL) {
			$("#tblCheckCoverageDistrictFull").show();
			$("#tblDistrictChkPart").show();
			clearDistrictWithPartialCoverage(false);
			clearDistrictWithFullCoverage();
			clearBlockLevel();
			$("#tblSubDistrictChkFull").show();
			$("#tblDistrictChkPart").show();

		}
		if (accessLevel == _DISTRICT_LEVEL) {
			$("#tblPartialCoverage").show();

		}
		/*
		 * if (accessLevel == _VILLAGE_LEVEL) {
		 * $("#tblSubDistrictChkFull").hide(); }
		 */
	}
};

listBoxMoveAcross = function(sourceId, targetId) {
	$("#" + sourceId + " option:selected ").each(function() {
		var optval = $(this).val();
		var textval = $(this).text();
		$("#" + targetId).append(new Option(textval, optval));
		$("#" + targetId + " option[value=" + optval + "]").attr('selected', true);
		$(this).remove();
	});
};

removeAllOptions = function(elementId) {
	dwr.util.removeAllOptions(elementId);
};

addSelectOptions = function(fieldId, data, valueText, nameText) {
	dwr.util.addOptions(fieldId, data, valueText, nameText);
};

clearSubdistrictLevel = function() {
	removeAllOptions('ddSubdistrictAtSubDistrictLvl');
	removeAllOptions('ddTargetDistrictSDLvl');
	$("#tblSubdistrictSpecific").hide();
};

clearBlockLevel = function() {
	removeAllOptions('ddBlockAtDistrictLvl');
	removeAllOptions('ddTargetBlockSDLvl');
	$("#tblBlockSpecific").hide();
};

clearDistrictWithFullCoverage = function() {
	$('#districtChkFull').prop('checked', false);
	removeAllOptions('dddistrictAtStateLvl');
	removeAllOptions('ddTargetDistrict');
	$("#tblDistrictSpecific").hide();

	if (accessLevel == _SUBDISTRICT_LEVEL || accessLevel == _BLOCK_LEVEL) {
		clearDistrictWithPartialCoverage();
	}
};

clearDistrictWithPartialCoverage = function(isBack) {

	if (accessLevel == _SUBDISTRICT_LEVEL || accessLevel == _BLOCK_LEVEL) {
		$('#DistrictChkPart').prop('checked', false);

	} else if (accessLevel == _VILLAGE_LEVEL) {
		$('#SubDistrictChkFull').prop('checked', false);

	}

	removeAllOptions('district');
	if (!isBack) {
		$("#tblDistrictChkPart").hide();
	}

	$("#tblDistrictSelectBox").hide();
	if (accessLevel == _SUBDISTRICT_LEVEL || accessLevel == _VILLAGE_LEVEL) {
		if (accessLevel == _VILLAGE_LEVEL) {
			clearVillageLevel();
			// $("#tblSubDistrictChkPart").hide();
			$('#SubDistrictChkPart').prop('checked', false);
			$("#tblDistrictChkPart").show();
			$("#tblSubDistrictChkPart").show();
		}
		clearSubdistrictLevel();
	} else if (accessLevel == _BLOCK_LEVEL) {
		clearBlockLevel();
	}
};

clearVillageLevel = function() {

	removeAllOptions('ddVillageAtVillLvl');
	removeAllOptions('ddTargetDistrictVillLvl');
	removeAllOptions('dddistrictAtVillLvl');
	removeAllOptions('ddSubdistrictAtVillLvl');
	$('#SubDistrictChkPart').prop('checked', false);
	$("#tblDisttSubDisttVillLvlSelectBox").hide();
	$("#tblVillageSpecific").hide();

};

toggle2 = function() {
	var checkdistfull = document.getElementById('districtChkFull');

	if (checkdistfull.checked) {
		$("#tblDistrictSpecific").show();
		if (accessLevel == _SUBDISTRICT_LEVEL || accessLevel == _BLOCK_LEVEL) {
			if (isCenterUser) {
				SubDistrictFullPart();
				$("#tblStateList1").show();
				$("#tblDistrictChkPart").show();

				return;

			}
			$("#tblDistrictChkPart").show();
		}

		if (accessLevel == _VILLAGE_LEVEL) {
			if (isCenterUser) {
				SubDistrictFullPart();
				$("#tblStateList1").show();

				$("#tblDistrictChkPart").show();
				$("#tblSubDistrictChkFull").show();

				return;

			}

			$("#tblSubDistrictChkFull").show();

		}

		getdistrictListAtStatelvlListBox(stateLevelId, orgCode, unitLevelCode);
	} else {
		$("#tblDistrictSpecific").hide();
		// $("#tblDistrictChkPart").hide();
		$("#tblDistrictSelectBox").hide();
		// $("#tblSubdistrictSpecific").hide();
		if (accessLevel == _VILLAGE_LEVEL) {
			$("#tblStateList1").hide();
			// $("#tblSubDistrictChkFull").hide();
			$("#SubDistrictChkFull").prop('checked', false);
			// $("#tblSubDistrictChkPart").hide();
			if (!isCenterUser) {
				clearSubdistrictLevel();
				clearVillageLevel();
			}

		}
		if (accessLevel == _BLOCK_LEVEL) {
			$("#tblBlockSpecific").hide();
			removeAllOptions("ddBlockAtDistrictLvl");
			removeAllOptions("ddTargetBlockSDLvl");

		}
		removeAllOptions("ddTargetDistrict");
		$("#DistrictChkPart").prop('checked', false);
		if (accessLevel == _SUBDISTRICT_LEVEL) {
			if (isCenterUser) {

				removeAllOptions("stateName1");
				$("#tblStateList1").hide();

				removeAllOptions("dddistrictAtStateLvl");
				removeAllOptions("ddTargetDistrict");
				$("#tblDistrictSpecific").hide();

				removeAllOptions("stateNameDistforSD");
				$("#tblDistrictSelectBox1").hide();
				/*
				 * $("#DistrictChkPart").prop('checked', false);
				 * $("#tblDistrictChkPart").hide();
				 * 
				 * 
				 * $("#tblDistrictSelectBox").hide();
				 */
				removeAllOptions("district");
				removeAllOptions("ddSubdistrictAtSubDistrictLvl");
				removeAllOptions("ddTargetDistrictSDLvl");
				// $("#tblSubdistrictSpecific").hide();

			}

		}
	}
};

getdistrictListAtStatelvlListBox = function(id, orgCode, unitLevelCode) {
	lgdDwrDistrictService.getDistrictListbyStateCodeForListBoxExtended(id, orgCode, unitLevelCode, {
		callback : handleDistrictSuccess,
		errorHandler : handleError,
		async : true
	});
};

handleDistrictSuccess = function(data) {
	var fieldId = 'dddistrictAtStateLvl';
	removeAllOptions(fieldId);
	var options = $("#dddistrictAtStateLvl");
	var options2 = $("#ddTargetDistrict");
	$.each(data, function(key, obj) {
		if (obj.operation_extend_flag == 'A') {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'true');
			}
			$(option).val(obj.districtCode).text(obj.districtNameEnglish);
			options.append(option);
		} else {
			var option2 = $("<option/>");
			$(option2).attr('disabled', 'true');
			$(option2).val(obj.districtCode).text(obj.districtNameEnglish);
			options2.append(option2);
		}
	});

};

toggle3 = function() {

	// var stateLevelId = 9;
	if ($("#DistrictChkPart").is(':checked')) {
		var selObj1 = document.getElementById('ddTargetDistrict');
		if (accessLevel == _SUBDISTRICT_LEVEL) {
			if (isCenterUser) {
				selObj1 = document.getElementById('ddTargetState');
			}

		}
		if (selObj1.options.length > 0) {
			$("#tblDistrictSelectBox").show();
			if (accessLevel == _SUBDISTRICT_LEVEL) {
				if (isCenterUser) {
					getStateListForDist();

					$("#tblDistrictSelectBox1").show();
					$("#tblSubdistrictSpecific").show();

					return;

				}
				$("#tblSubdistrictSpecific").show();

			} else if (accessLevel == _BLOCK_LEVEL) {

				$("#tblBlockSpecific").show();
			}
			getNotInDistrictList(stateLevelId);
		} else {
			if (accessLevel == _SUBDISTRICT_LEVEL) {
				if (isCenterUser) {
					getStateListForDist();
					$("#tblDistrictSelectBox1").show();
					$("#tblDistrictSelectBox").show();
					$("#tblSubdistrictSpecific").show();

					return;

				} else {
					getNotInDistrictList(stateLevelId);
					$("#tblDistrictSelectBox").show();
					$("#tblSubdistrictSpecific").show();
				}

			}
			if (accessLevel == _BLOCK_LEVEL) {
				getNotInDistrictList(stateLevelId);
				$("#tblDistrictSelectBox").show();
				$("#tblBlockSpecific").show();
			}
			// customAlert("Kindly select the districts from the list first,
			// afterwords click on this option.");
			// $("#DistrictChkPart").prop('checked', false);
		}
	}

	if (!($("#DistrictChkPart").is(':checked'))) {
		$("#tblDistrictSelectBox").hide();

		if (accessLevel == _SUBDISTRICT_LEVEL) {
			if (isCenterUser) {
				$("#tblDistrictSelectBox1").hide();
				clearSubdistrictLevel();

			} else {
				clearSubdistrictLevel();
			}
		} else if (accessLevel == _BLOCK_LEVEL) {
			clearBlockLevel();
		}
	}
};

/* function for village level */

checkSubDistFullCovrage = function() {

	if (accessLevel == _VILLAGE_LEVEL) {
		if (isCenterUser) {
			getdataforVillagelvl();
			return;
		}
	}

	if ($("#SubDistrictChkFull").is(':checked')) {

		var selObj1 = document.getElementById('ddTargetDistrict');
		if (selObj1.options.length > 0) {
			$("#tblDistrictSelectBox").show();
			$("#tblSubdistrictSpecific").show();
			$("#tblSubDistrictChkPart").show();
			getNotInDistrictList(stateLevelId);
		} else {
			$("#tblDistrictSelectBox").show();
			$("#tblSubdistrictSpecific").show();
			$("#tblSubDistrictChkPart").show();
			getNotInDistrictList(stateLevelId);
			// customAlert("Kindly select the districts from the list first,
			// afterwords click on this option.");
			// $("#SubDistrictChkFull").prop('checked', false);
		}

	}
	if (!($("#SubDistrictChkFull").is(':checked'))) {
		if ($("#SubDistrictChkFull").is(':checked')) {
			clearVillageLevel();
		}
		clearSubdistrictLevel();
		$("#tblDistrictSelectBox").hide();
		removeAllOptions('ddSubdistrictAtSubDistrictLvl');
		removeAllOptions('ddTargetDistrictSDLvl');
		// $("#tblSubDistrictChkPart").hide();
		$("#tblDisttSubDisttVillLvlSelectBox").hide();
		$("#tblVillageSpecific").hide();
		// $("#SubDistrictChkPart").prop('checked', false);

	}

};

checkSubDistPartialCovrage = function() {
	if ($("#SubDistrictChkPart").is(':checked')) {
		if (accessLevel == _VILLAGE_LEVEL) {
			if (isCenterUser) {
				showVillageLevel();
				return;
			}
		}
		getNotInDistrictListVillLvl(stateLevelId);
		$("#tblDisttSubDisttVillLvlSelectBox").show();
		$("#tblVillageSpecific").show();
	}
	if (!($("#SubDistrictChkPart").is(':checked'))) {

		$("#tblDisttSubDisttVillLvlSelectBox").hide();
		$("#tblVillageSpecific").hide();
		if (accessLevel == _VILLAGE_LEVEL) {
			if (isCenterUser) {
				$("#tblSubDistrictSelectState").hide();
				removeAllOptions("dddistrictAtVillLvl");
				removeAllOptions("ddSubdistrictAtVillLvl");

			} else {
				removeAllOptions("dddistrictAtVillLvl");
				removeAllOptions("ddVillageAtVillLvl");
				removeAllOptions("ddTargetDistrictVillLvl");
				removeAllOptions("ddSubdistrictAtVillLvl");
			}
		}

	}
};
getNotInDistrictListVillLvl = function(id) {
	var selObj;
	if (accessLevel == _VILLAGE_LEVEL) {
		if (!isCenterUser) {
			selObj = document.getElementById('ddTargetDistrict');
		} else {
			selObj = document.getElementById('ddTargetDistrict');
		}

	} else {

		selObj = document.getElementById('ddTargetDistrict');
	}
	var districtList = null;

	if (selObj.options.length > 0) {
		for ( var i = 0; i < selObj.options.length; i++) {
			if (districtList == null)
				districtList = selObj.options[i].value + ",";
			else
				districtList += selObj.options[i].value + ",";
			selObj.options[i].selected = true;
		}
		districtList = districtList.substring(0, districtList.length - 1);
	} else {
		if (accessLevel == _VILLAGE_LEVEL) {
			if (!isCenterUser) {

				if (districtList == null) {
					districtList = "0";
				}
				lgdDwrDistrictService.getDistrictListbyStateCodeExtended(id, districtList, orgCode, unitLevelCode, {
					callback : handleDistrictSuccessVillLvl,
					errorHandler : handleError
				});
				// customAlert("Kindly select the SubDistrict from the list
				// first, afterwords click on this option.");
				// $("#SubDistrictChkPart").prop('checked', false);

			} else {
				lgdDwrDistrictService.getDistrictListbyStateCodeExtended(id, 0, orgCode, unitLevelCode, {
					callback : handleDistrictSuccessVillLvl,
					errorHandler : handleError
				});
			}
		} else {
			customAlert("Kindly select the districts from the list in above section first, afterwords click on this option.");
		}
	}

	lgdDwrDistrictService.getDistrictListbyStateCodeExtended(id, districtList, orgCode, unitLevelCode, {
		callback : handleDistrictSuccessVillLvl,
		errorHandler : handleError
	});
};

handleDistrictSuccessVillLvl = function(data) {
	var fieldId = 'dddistrictAtVillLvl';

	dwr.util.removeAllOptions(fieldId);

	var dataq = [ {
		id : "",
		select : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "id", "select");
	var options = $("#dddistrictAtVillLvl");
	$.each(data, function(key, obj) {
		if (obj.operation_extend_flag == 'A') {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'true');
			}
			$(option).val(obj.districtCode).text(obj.districtNameEnglish);
			options.append(option);
		} else {
			var option2 = $("<option/>");
			$(option2).attr('disabled', 'true');
			$(option2).val(obj.districtCode).text(obj.districtNameEnglish);
			options.append(option2);
		}
	});

};

getSubdistrictAtVilllvl = function(id) {

	var selObj = document.getElementById('ddTargetDistrictSDLvl');
	var subDistrictList = null;
	/*
	 * if ((ddTargetDistrictVillLvl.options.length > 0) ||
	 * (ddVillageAtVillLvl.options.length > 0)) {
	 * removeAllOptions(ddTargetDistrictVillLvl);
	 * removeAllOptions(ddVillageAtVillLvl); }
	 */
	if (selObj.options.length > 0) {
		for ( var i = 0; i < selObj.options.length; i++) {
			if (subDistrictList == null)
				subDistrictList = selObj.options[i].value + ",";
			else
				subDistrictList += selObj.options[i].value + ",";
			selObj.options[i].selected = true;
		}
		subDistrictList = subDistrictList.substring(0, subDistrictList.length - 1);
	} else {
		subDistrictList = "0";
	}
	lgdDwrSubDistrictService.getSubDistrictListbyDistrictCodeExtended(id, subDistrictList, orgCode, unitLevelCode, {
		callback : handleSubDistrictSuccessVillLevel,
		errorHandler : handleError
	});

};

handleSubDistrictSuccessVillLevel = function(data) {
	var fieldId = 'ddSubdistrictAtVillLvl';
	dwr.util.removeAllOptions(fieldId);

	var dataq = [ {
		id : "",
		select : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "id", "select");
	var options = $("#ddSubdistrictAtVillLvl");
	$.each(data, function(key, obj) {
		if (obj.operation_extend_flag == 'A') {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'true');
			}
			$(option).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
			options.append(option);
		} else {
			var option2 = $("<option/>");
			$(option2).attr('disabled', 'true');
			$(option2).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
			options.append(option2);
		}
	});

};

getVillageListAtVillageLvl = function(id) {

	lgdDwrVillageService.getVillageListbySubDistrictWithSDNameExtended(id, orgCode, unitLevelCode, {
		callback : handleVillageSuccess,
		errorHandler : handleError
	});

};

handleVillageSuccess = function(data) {

	var fieldId = 'ddVillageAtVillLvl';
	dwr.util.removeAllOptions(fieldId);
	var options = $("#ddVillageAtVillLvl");
	var options2 = $("#ddTargetDistrictVillLvl");
	$.each(data, function(key, obj) {
		if (obj.operation_extend_flag == 'A') {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'true');
			}
			$(option).val(obj.villageCode).text(obj.villageNameEnglish);
			options.append(option);
		} else {
			var option2 = $("<option/>");
			$(option2).attr('disabled', 'true');
			$(option2).val(obj.villageCode).text(obj.villageNameEnglish);
			options2.append(option2);
		}
	});

};

getNotInDistrictList = function(id) {
	var selObj = document.getElementById('ddTargetDistrict');
	var districtList = null;
	if (selObj.options.length > 0) {
		for ( var i = 0; i < selObj.options.length; i++) {
			if (districtList == null)
				districtList = selObj.options[i].value + ",";
			else
				districtList += selObj.options[i].value + ",";
			selObj.options[i].selected = true;
		}
		if (accessLevel == _VILLAGE_LEVEL) {
			if (isCenterUser) {
				clearVillageLevel();
				$("#tblSubDistrictSelectState").hide();
				// removeAllOptions(ddSubdistrictAtSubDistrictLvl);
				// removeAllOptions(ddTargetDistrictSDLvl);
			}
		}
		districtList = districtList.substring(0, districtList.length - 1);

	} else {
		districtList = 0;
		// customAlert("Kindly select the districts from the list in above
		// section first, afterwords click on this option.");
	}
	lgdDwrDistrictService.getDistrictListbyStateCodeExtended(id, districtList, orgCode, unitLevelCode, {
		callback : handleDistrictSuccessSDLvl,
		errorHandler : handleError
	});

};

handleDistrictSuccessSDLvl = function(data) {
	/*
	 * var fieldId = 'district'; var valueText = 'districtCode'; var nameText =
	 * 'districtNameEnglish';
	 * 
	 * removeAllOptions(fieldId);
	 */
	var fieldId = 'district';
	removeAllOptions(fieldId);
	var dataq = [ {
		id : "",
		select : "Select"
	} ];

	dwr.util.addOptions(fieldId, dataq, "id", "select");

	// addSelectOptions(fieldId, data, valueText, nameText);

	var options = $("#district");
	$.each(data, function(key, obj) {
		if (obj.operation_extend_flag == 'A') {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'true');
			}
			$(option).val(obj.districtCode).text(obj.districtNameEnglish);
			options.append(option);
		} else {
			var option2 = $("<option/>");
			$(option2).attr('disabled', 'true');
			$(option2).val(obj.districtCode).text(obj.districtNameEnglish);
			options.append(option2);
		}
	});
};

getSubdistrictOrBlocksByDistrict = function(districtId) {
	if (accessLevel == _SUBDISTRICT_LEVEL) {

		getSubdistrictAtSubDistrictlvl(districtId, orgCode, unitLevelCode);
	} else if (accessLevel == _BLOCK_LEVEL) {
		var ddtargetblock = document.getElementById("ddTargetBlockSDLvl");

		if (ddtargetblock.options.length > 0) {

		}

		getBlockList(districtId, orgCode, unitLevelCode);
	} else if (accessLevel == _VILLAGE_LEVEL) {
		var ddtargetdistsdlvl = document.getElementById("ddTargetDistrictSDLvl");

		if (ddtargetdistsdlvl.options.length > 0) {

			if ($("#SubDistrictChkPart").is(':checked')) {
				clearVillageLevel();
				$("#tblSubDistrictSelectState").hide();

			}

		}
		getSubdistrictAtSubDistrictlvl(districtId, orgCode, unitLevelCode);
	}

};

getSubdistrictAtSubDistrictlvl = function(id, orgCode, unitLevelCode) {
	lgdDwrSubDistrictService.getSubDistrictListbyDistrictCodewithDistrictNameExtended(id, orgCode, unitLevelCode, {
		callback : handleSubDistrictSuccess,
		errorHandler : handleError,
		async : true
	});

};

getBlockList = function(id) {
	lgdDwrBlockService.getBlockListExtended(id, orgCode, unitLevelCode, {
		callback : handleBlockSuccess,
		errorHandler : handleError,
		async : true
	});
};

handleSubDistrictSuccess = function(data) {

	var fieldId = 'ddSubdistrictAtSubDistrictLvl';
	removeAllOptions(fieldId);
	var options = $("#ddSubdistrictAtSubDistrictLvl");
	var options2 = $("#ddTargetDistrictSDLvl");
	$.each(data, function(key, obj) {
		if (obj.operation_extend_flag == 'A') {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'true');
			}
			$(option).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
			options.append(option);
		} else {
			var option2 = $("<option/>");
			$(option2).attr('disabled', 'true');
			$(option2).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
			options2.append(option2);
		}
	});

};

handleBlockSuccess = function(data) {

	var fieldId = 'ddBlockAtDistrictLvl';
	removeAllOptions(fieldId);
	var options = $("#ddBlockAtDistrictLvl");
	var options2 = $("#ddTargetBlockSDLvl");
	$.each(data, function(key, obj) {
		if (obj.operation_extend_flag == 'A') {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'true');
			}
			$(option).val(obj.blockCode).text(obj.blockNameEnglish);
			options.append(option);
		} else {
			var option2 = $("<option/>");
			$(option2).attr('disabled', 'true');
			$(option2).val(obj.blockCode).text(obj.blockNameEnglish);
			options2.append(option2);
		}
	});
};

handleError = function() {
	// Show a popup message
	customAlert("No data found!");
};

StateFullPart = function() {
	if ($("#ChkFullCoverage").is(':checked')) {

		$('#tblStateSpecific').show();
		if (accessLevel == _DISTRICT_LEVEL) {
			if (isCenterUser) {
				$("#tblPartialCoverage").show();

			}
		}
		/* $("#tblPartialCoverage").show(); *//* change here is not working */

		if (accessLevel == _SUBDISTRICT_LEVEL || accessLevel == _VILLAGE_LEVEL) {
			if (isCenterUser) {
				$("#tblCheckCoverageDistrictFull").show();

			} else {
				$("#tblPartialCoverage").show();

			}

		}

	} else {

		if (accessLevel == _DISTRICT_LEVEL) {

			if (isCenterUser) {
				// var ddTargetState1=document.getElementById("ddTargetState");
				removeAllOptions("ddTargetState");
				// removeAllOptions("stateName");
				// $("#tblDistrictSpecific").hide();
				// $("#tblStateList").hide();
				$("#tblStateSpecific").hide();
				// $("#tblPartialCoverage").hide();
				// $("#ChkPartialCoverage").prop('checked', false);

			}

		}
		if (accessLevel == _SUBDISTRICT_LEVEL) {
			if (isCenterUser) {

				removeAllOptions("stateName1");
				removeAllOptions("ddTargetState");
				$("#tblStateSpecific").hide();
				$("#tblStateList1").hide();

				removeAllOptions("dddistrictAtStateLvl");
				removeAllOptions("ddTargetDistrict");
				$("#tblDistrictSpecific").hide();

				removeAllOptions("stateNameDistforSD");

				$("#DistrictChkPart").prop('checked', false);
				$("#districtChkFull").prop('checked', false);
				// $("#tblDistrictChkPart").hide();
				// $("#tblCheckCoverageDistrictFull").hide();
				$("#tblStateList1").hide();

				removeAllOptions("district");
				$("#tblDistrictSelectBox").hide();
				$("#tblDistrictSelectBox1").hide();

				removeAllOptions("ddSubdistrictAtSubDistrictLvl");
				removeAllOptions("ddTargetDistrictSDLvl");
				// $("#tblSubdistrictSpecific").hide();

			}

		}

		if (accessLevel == _VILLAGE_LEVEL) {
			if (isCenterUser) {

				$("#tblSubDistrictSelectState").hide();
				// $("#tblSubDistrictChkPart").hide();
				// $("#tblDistrictSelectBox").hide();
				// $("#tblDistrictSelectBox1").hide();
				$("#tblStateList1").hide();
				$("#tblStateSpecific").hide();

				// $("#tblSubDistrictChkFull").hide();
				removeAllOptions("stateName1");
				removeAllOptions("ddTargetState");
				// $("#tblCheckCoverageDistrictFull").hide();

				removeAllOptions("stateNameDistforSD");
				removeAllOptions("district");
				removeAllOptions("stateNameSubDistForVillage");
				$("#SubDistrictChkFull").prop('checked', false);

				clearDistrictWithFullCoverage();
				clearSubdistrictLevel();
				clearVillageLevel();

			}
		}

	}

};
StatePartialPart = function() {

	var obj2 = document.getElementById('ChkPartialCoverage');
	var obj3 = document.getElementById('ChkFullCoverage');
	var selObj = document.getElementById('ddTargetState');
	var stateList = null;

	if (obj2.checked) {
		if (selObj.options.length > 0) {
			for ( var i = 0; i < selObj.options.length; i++) {
				if (stateList == null)
					stateList = selObj.options[i].value + ",";
				else
					stateList += selObj.options[i].value + ",";
				selObj.options[i].selected = true;
			}
			stateList = stateList.substring(0, stateList.length - 1);
			getStateList(stateList);
			$("#tblStateList").show();
			$("#tblDistrictSpecific").show();

		} else {
			// stateList = stateList.substring(0, stateList.length - 1);
			getStateList(0);
			$("#tblStateList").show();
			$("#tblDistrictSpecific").show();
			// obj3.checked = false;
			/*
			 * customAlert("Kindly select the states from the list first,
			 * afterwords click on this option."); obj2.checked = false;
			 */
		}
	}
	if (!obj2.checked) {
		selObj.value = '';
		removeAllOptions("stateName");
		$("#tblStateList").hide();
		removeAllOptions("dddistrictAtStateLvl");

		removeAllOptions("ddTargetDistrict");
		$("#tblDistrictSpecific").hide();

	}

};

function getStateList(ids) {
	lgdDwrStateService.getAllNotInStatesExtended(ids, orgCode, unitLevelCode, {
		callback : handleStateSuccess,
		errorHandler : handleError
	});
}

function handleStateSuccess(data) {

	var fieldId = "";
	var options = "";
	if (accessLevel == _SUBDISTRICT_LEVEL || accessLevel == _VILLAGE_LEVEL) {
		if (isCenterUser) {
			fieldId = 'stateName1';
			options = $("#stateName1");
		}
	} else {
		fieldId = 'stateName';
		options = $("#stateName");
	}

	var valueText = 'stateCode';
	var nameText = 'stateNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		id : "",
		select : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "id", "select");
	$.each(data, function(key, obj) {
		if (obj.operation_extend_flag == 'A') {
			var option = $("<option/>");
			$(option).val(obj.stateCode).text(obj.stateNameEnglish);
			options.append(option);
		} else {
			var option2 = $("<option/>");
			$(option2).attr('disabled', 'true');
			$(option2).val(obj.stateCode).text(obj.stateNameEnglish);
			options.append(option2);
		}
	});

}

getDistrictsforVillageS = function() {

	// removeAllOptions(dddistrictAtStateLvl);
	// removeAllOptions(ddTargetDistrict);
	if (accessLevel == _SUBDISTRICT_LEVEL || accessLevel == _VILLAGE_LEVEL) {
		if (isCenterUser) {
			getdistrictListAtStatelvlListBox(document.getElementById('stateName1')[document.getElementById('stateName1').selectedIndex].value,
					orgCode, unitLevelCode);
			// getdistrictListAtStatelvlListBox(document.getElementById('stateName1')[document.getElementById('stateName1').selectedIndex].value);

		}
	} else {
		getdistrictListAtStatelvlListBox(document.getElementById('stateName')[document.getElementById('stateName').selectedIndex].value, orgCode,
				unitLevelCode);

	}
	if (accessLevel == _VILLAGE_LEVEL) {
		if (isCenterUser) {
			if ($("#SubDistrictChkFull").is(':checked')) {
				clearDistrictWithPartialCoverage();
				clearVillageLevel();
				removeAllOptions("stateNameDistforSD");

				$("#tblDistrictSelectBox1").hide();
				removeAllOptions("stateNameSubDistForVillage");

				$("#tblSubDistrictSelectState").hide();

			}

		}
	}

};

SubDistrictFullPart = function() {
	var obj1 = document.getElementById('districtChkFull');
	// var obj2 = document.getElementById('DistrictChkPart');
	var selObj1 = document.getElementById('ddTargetState');
	var stateList = null;

	if (obj1.checked) {
		if (selObj1.value > 0) {
			for ( var i = 0; i < selObj1.options.length; i++) {
				if (stateList == null)
					stateList = selObj1.options[i].value + ",";
				else
					stateList += selObj1.options[i].value + ",";
				selObj1.options[i].selected = true;
			}
			stateList = stateList.substring(0, stateList.length - 1);
			getStateList(stateList);

		} else {
			getStateList(0);
			// customAlert("Kindly select the states from the list first,
			// afterwords click on this option.");
			// obj1.checked = false;
		}

	}

};
function getStateListSDLvl(ids) {

	lgdDwrStateService.getAllNotInStatesExtended(ids, orgCode, unitLevelCode, {
		callback : handleStateSuccessSDLvl,
		errorHandler : handleError
	});
}

function handleStateSuccessSDLvl(data) {

	var fieldId = 'stateNameDistforSD';
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		id : "",
		select : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "id", "select");
	var options = $("#stateNameDistforSD");
	$.each(data, function(key, obj) {
		if (obj.operation_extend_flag == 'A') {
			var option = $("<option/>");
			$(option).val(obj.stateCode).text(obj.stateNameEnglish);
			options.append(option);
		} else {
			var option2 = $("<option/>");
			$(option2).attr('disabled', 'true');
			$(option2).val(obj.stateCode).text(obj.stateNameEnglish);
			options.append(option2);
		}
	});

}

function getStateListForDist() {

	var obj2 = document.getElementById('DistrictChkPart');
	var selObj1 = document.getElementById('ddTargetState');
	var stateList = null;

	if (obj2.checked) {

		if (selObj1.options.length > 0) {
			for ( var i = 0; i < selObj1.options.length; i++) {
				if (stateList == null)
					stateList = selObj1.options[i].value + ",";
				else
					stateList += selObj1.options[i].value + ",";
				selObj1.options[i].selected = true;
			}
			stateList = stateList.substring(0, stateList.length - 1);
			getStateListSDLvl(stateList);
		} else {
			getStateListSDLvl(0);
		}
	}
	if (!obj2.checked) {
		/*
		 * document.getElementById('divspecificSubDistrict').style.display =
		 * 'none';
		 */
	}

}

function getdataforVillagelvl() {
	var obj2 = document.getElementById('SubDistrictChkFull');
	var selObj = document.getElementById('ddTargetState');
	var stateList = null;
	if (obj2.checked) {
		stateList = null;
		if (selObj.value > 0) {
			for ( var i = 0; i < selObj.options.length; i++) {
				if (stateList == null)
					stateList = selObj.options[i].value + ",";
				else
					stateList += selObj.options[i].value + ",";
				selObj.options[i].selected = true;
			}
			$("#tblDistrictSelectBox1").show();
			$("#tblDistrictSelectBox").show();
			$("#tblSubdistrictSpecific").show();
			$("#tblSubDistrictChkPart").show();

			stateList = stateList.substring(0, stateList.length - 1);
			getStateListSDLvl(stateList);
			/* document.getElementById('TblSDFull').style.display = 'block'; */
		} else {
			getStateListSDLvl(0);
			$("#tblDistrictSelectBox1").show();
			$("#tblDistrictSelectBox").show();
			$("#tblSubdistrictSpecific").show();
			$("#tblSubDistrictChkPart").show();
		}
	}
	if (!obj2.checked) {
		/* document.getElementById('TblSDFull').style.display = 'none'; */
		$("#tblDistrictSelectBox1").hide();
		$("#tblDistrictSelectBox").hide();
		$("#tblSubdistrictSpecific").hide();
		// $("#tblSubDistrictChkPart").hide();
		removeAllOptions('district');
		clearVillageLevel();
		clearSubdistrictLevel();
		$("#tblSubDistrictSelectState").hide();

	}

}

showVillageLevel = function() {

	var obj4 = document.getElementById('SubDistrictChkPart');
	var selObj = document.getElementById('ddTargetState');
	var stateList = null;

	if (obj4.checked) {
		stateList = null;
		if (selObj.value > 0) {
			for ( var i = 0; i < selObj.options.length; i++) {
				if (stateList == null)
					stateList = selObj.options[i].value + ",";
				else
					stateList += selObj.options[i].value + ",";
				selObj.options[i].selected = true;
			}
			removeAllOptions("ddTargetDistrictVillLvl");
			removeAllOptions("ddVillageAtVillLvl");
			stateList = stateList.substring(0, stateList.length - 1);
			getStateListVillLvl(stateList);
			/* document.getElementById('TblSDPart').style.display = 'block'; */
		} else {
			removeAllOptions("ddTargetDistrictVillLvl");
			removeAllOptions("ddVillageAtVillLvl");
			getStateListVillLvl(0);
		}
	}
	if (!obj4.checked) {
		removeAllOptions("ddTargetDistrictVillLvl");
		removeAllOptions("ddVillageAtVillLvl");
	}

	$("#tblDisttSubDisttVillLvlSelectBox").show();
	$("#tblSubDistrictSelectState").show();

	$("#tblVillageSpecific").show();

	return;
};

function getStateListVillLvl(ids) {
	lgdDwrStateService.getAllNotInStates(ids, {
		callback : handleStateSuccessVillLvl,
		errorHandler : handleError
	});
}

function handleStateSuccessVillLvl(data) {
	var fieldId = 'stateNameSubDistForVillage';
	var valueText = 'stateCode';
	var nameText = 'stateNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		id : "",
		select : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "id", "select");
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getDepartmentList1(id) {
	if (isCenterUser) {
		if (isOrganizationFlow) {
			removeAllOptions("deptOrgCode");

			if (id == '') {
				var fieldId = 'deptOrgCode';
				dwr.util.removeAllOptions(fieldId);

				var dataq = [ {
					id : "",
					select : "Select"
				} ];
				dwr.util.addOptions(fieldId, dataq, "id", "select");

			} else {
				lgdDwrOrganizationService.getDepartmentListforOrg(id, {
					callback : handleDepartmentSuccess1,
					errorHandler : handleError
				});
			}
		}
	}
}

function handleDepartmentSuccess1(data) {

	var fieldId = 'deptOrgCode';
	var valueText = 'orgCode';
	var nameText = 'orgName';

	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		id : "",
		select : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "id", "select");

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

processForm = function(processId) {
	if (validateForm()) {
		var deptNameEnglish = $("#deptNameEnglish").val();
		lgdAdminDepatmentDwr.checkExistingDepartmentNameForExtendedDept(parseInt(stateLevelId), deptNameEnglish, orgLocatedLevelCode, null, {
			async : true,
			callback : function(data) {
				if (data) {
					$('#err_deptName').html("Department Name Already Exist");
					return false;
				} else {
					var url;
					if (processId == "Next") {
						$('#btnCreateDeptNext').attr('disabled', 'disabled');
						url = _continue_url;
					} else if (processId == "Save") {
						$('#btnSaveDept').attr('disabled', 'disabled');
						url = _save_url;
					} else {
						customAlert("Invalid Procees Called.");
						return false;
					}
					$('#close').attr('disabled', 'disabled');
					$("select[multiple] option").prop("selected", "selected");
					document.forms['adminOrgDeptForm'].method = "POST";
					document.forms['adminOrgDeptForm'].action = url;
					document.forms['adminOrgDeptForm'].submit();
					return true;
				}
			}
		});
	}
};
processFormExtended = function(processId) {
	if (validateFormExtended()) {
		var url;
		if (processId == "Next") {
			$('#btnCreateDeptNext').attr('disabled', 'disabled');
			url = _continue_url;
		} else if (processId == "Save") {
			$('#btnSaveDept').attr('disabled', 'disabled');
			url = _save_url;
		} else {
			customAlert("Invalid Procees Called.");
			return false;
		}
		$('#close').attr('disabled', 'disabled');
		$("select[multiple] option").prop("selected", "selected");
		document.forms['adminOrgDeptForm'].method = "POST";
		document.forms['adminOrgDeptForm'].action = url;
		document.forms['adminOrgDeptForm'].submit();
		return true;
	}
};