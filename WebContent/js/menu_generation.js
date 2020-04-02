// ############################################ creating request object for ajax #######################

function createRequestObject() {
	var xmlhttp = false;
	try {
		xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e) {
		try {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (E) {
			xmlhttp = false;
		}
	}
	if (!xmlhttp && typeof XMLHttpRequest != 'undefined') {
		xmlhttp = new XMLHttpRequest();
	}
	return xmlhttp;
}
// ------------------------------------------

// global variables *******************************
var entityType = "";
var parentId = 0;
var selectToAppend = "";
var currentSelect = "";
var level = 0;
var selectedlevelis = 0;
var single = 1;
// ************************************************

// Handling state level or center level assigned unit
function getLevelOfAssignedUnit() {

	// Reseting tr and reseting combos.
	reset(0);
	// $("#stateId").val("");
	$("#assignedUnitDiv").hide();
	$("#stateTr").hide();
	$("#level0tr").hide();
	$("#level1tr").hide();
	$("#level2tr").hide();
	$("#level3tr").hide();
	$("#level4tr").hide();
	$("#assignedUnittr").hide();

	var typeOfUser = $("#typeOfUser").val();
	if (typeOfUser == "c") {
		$("#assignedUnitDiv").fadeIn(1000);
		$("#assignedUnittr").fadeIn(1000);

	} else if (typeOfUser == "s") {

		$("#assignedUnitDiv").fadeIn(1000);
		$("#stateTr").fadeIn(1000);

	} else {

	}

}

// ######################################################## Select entityType
// ############################################

function getEntityType() {

	// var request = createRequestObject(); ///Request object for ajax call.
	var categoryCode = $("#stateId").val();

	if (categoryCode != "") {
		pesswitchunitService.get(categoryCode, {
			callback : getDataFromServerCallBack
		});
	}

	/*
	 * if(categoryCode != "") request.open('POST',
	 * 'getEntityType.htm?categoryCode='+categoryCode+actionPath, true);
	 * 
	 * request.onreadystatechange = function() { if(request.readyState < 4) {
	 * 
	 * $(".process").fadeIn(500); }
	 * 
	 * else if(request.readyState == 4) { if(request.status == 200) {
	 * 
	 * var response = request.responseText; //alert(response);
	 * 
	 * if(response!=null && response.length > 0) {
	 * createOptionEntityType(response); } else {
	 * 
	 * alert("No entity fonnd . !"); }
	 * 
	 * 
	 *  } } } request.send(null);
	 */
}// end of function

function getDataFromServerCallBack(data) {
	var response = data; // alert(response);

	if (response != null && response.length > 0) {
		createOptionEntityType(response);
		var entityObject = response.split("_");

		if (entityObject.length > 1) {
			single = 0;
		} else {
			getAssignedUnit(0);
		}
	} else {

		alert("No entity fonnd . !");
	}
}

function createOptionEntityType(consolidatedString) {
	// alert("abc");
	$("#level0tr").fadeIn(1000);

	var entityTypeSelect = document.getElementById("level0");
	// alert(entityTypeSelect);
	// To remove the options
	entityTypeSelect.options.length = 0;

	/*
	 * //To add the select options var newOption =
	 * document.createElement("option"); newOption.value =""; newOption.text =
	 * "-- Select --";
	 * 
	 * try { entityTypeSelect.add(newOption, null); //Standard
	 * 
	 * }catch(error) { entityTypeSelect.add(newOption); // IE only }
	 */

	// To add the options
	var entityObject = consolidatedString.split("_");

	if (entityObject.length > 1) {
		var newOption = document.createElement("option");
		newOption.value = "";
		newOption.text = "--Select--";
		try {
			entityTypeSelect.add(newOption, null); // Standard

		} catch (error) {
			entityTypeSelect.add(newOption); // IE only

		}
	}

	for ( var i = 0; i < entityObject.length; i++) {
		var temp = entityObject[i].split(":");

		var newOption = document.createElement("option");

		newOption.value = temp[0] + "_" + temp[1];
		newOption.text = temp[2];

		try {
			entityTypeSelect.add(newOption, null); // Standard

		} catch (error) {
			entityTypeSelect.add(newOption); // IE only

		}
	}
	$(".process").fadeOut();

	return true;
}

// ######################################################## end of Select
// entityType ############################################

// ######################################################## getting Assigned
// Units ############################################

function getAssignedUnit(s) {
	// alert(s);
	level = s; // Selected combo box that has been selected by user
	reset(level); // Resetting the combo boxes.

	if (s == 0) {
		entityType = document.getElementById("level0").value;
		currentSelect = "level" + level;
		parentId = 0;
	} else {
		currentSelect = "level" + level;
		parentId = document.getElementById(currentSelect).value;
	}

	// alert(currentSelect);
	/*
	 * var request = createRequestObject(); ///Request object for ajax call.
	 * if(entityType!="") request.open('POST',
	 * 'getAssignedUnit.htm?entityType='+entityType+'&parentId='+parentId+unitPath ,
	 * true);
	 * 
	 * request.onreadystatechange = function() { if(request.readyState < 4) {
	 * $(".process").fadeIn(500); }
	 * 
	 * else if(request.readyState == 4) { if(request.status == 200) { var
	 * response = request.responseText; //alert(response);
	 * 
	 * if(response!=null && response.length > 0) {
	 * createOptionAssignedUnit(response); } else { alert("No record found .
	 * !"); } } } } request.send(null);
	 */

	if (entityType != "") {
		pesswitchunitService.getAssignedUnit(entityType, parentId + "", null, {
			callback : getAssignedDataFromServerCallBack
		});
	}
}// end of function

function getAssignedDataFromServerCallBack(data) {
	var response = data;
	// alert(response);

	if (response != null && response.length > 0) {
		createOptionAssignedUnit(response);

	} else {
		alert("No record found !!");
	}

	var entityObject = response.split("_");

	if (entityObject.length > 2) {
		single = 0;

	} else {

		var assignedUnitSelect = document.getElementById(selectToAppend);

		if (entityObject[0] != "au" && assignedUnitSelect.value > 0) {
			getAssignedUnit(selectedlevelis);
		}

		if (entityObject[0] == "au" && assignedUnitSelect.value > 0 && single == 1) {
			window.document.switchForm.action = 'switchunit.htm';
			// window.document.switchForm.submit();
		}

		if (entityObject[0] == "au" && assignedUnitSelect.value > 0 && currentSelect == 'level0') {
			validateZilaDistrict(document.getElementById("applicationId").value);
		} else if (assignedUnitSelect.value > 0) {
			validateDistrict(document.getElementById("applicationId").value);
		}

		/*
		 * if((entityObject[0] == "Zilla Parishad" || entityObject[0] == "Zilla
		 * Panchayat" || entityObject[0] == "District Planning & Development
		 * Board") && assignedUnitSelect.value > 0 ){
		 * validateDistrict(document.getElementById("applicationId").value); }
		 */
		/*
		 * if(currentSelect == 'level1' && assignedUnitSelect.value > 0 ){
		 * validateDistrict(document.getElementById("applicationId").value); }
		 */

		// getAssignedUnit(level);
	}
} // end of function

function createOptionAssignedUnit(consolidatedString) {
	if (consolidatedString != null) {

		var assignedUnitObject = consolidatedString.split("_");

		enableTr(level, assignedUnitObject[0]); // Enabling hidden tr and select
		// boxes.

		// alert(level);
		var assignedUnitSelect = document.getElementById(selectToAppend);
		// alert(selectToAppend);
		// alert(assignedUnitSelect);
		// To add the options

		if (assignedUnitObject[1] == null) {
			alert("No result found . !");
		} else {

			// To remove the options
			assignedUnitSelect.options.length = 0;

			// To add the select options
			/*
			 * var newOption = document.createElement("option"); newOption.value
			 * =""; newOption.text = "-- Select --";
			 * 
			 * try { assignedUnitSelect.add(newOption, null); //Standard
			 * 
			 * }catch(error) {
			 * 
			 * assignedUnitSelect.add(newOption); // IE only }
			 */

			if (assignedUnitObject.length > 2) {
				var newOption = document.createElement("option");
				newOption.value = "";
				newOption.text = "--Select--";
				try {
					assignedUnitSelect.add(newOption, null); // Standard

				} catch (error) {
					assignedUnitSelect.add(newOption); // IE only

				}
			} else {

			}

			// To add the options
			for ( var i = 1; i < assignedUnitObject.length; i++) {
				var temp = assignedUnitObject[i].split(":");
				var newOption = document.createElement("option");

				newOption.value = temp[0];
				newOption.text = temp[1];

				try {
					assignedUnitSelect.add(newOption, null); // Standard

				} catch (error) {
					assignedUnitSelect.add(newOption); // IE only

				}
			}

		}
		$(".process").fadeOut();
		return true;

	} else {
		$(".process").fadeOut();
		alert("Server side error . !");
	}

}

// ############################### enabling tr and select
// ###############################
function enableTr(level, selectLabel) {
	// alert(level+"="+selectLabel);
	// / alert(selectLabel == "au");
	if (selectLabel == "au") {
		// alert("if");
		$("#assignedUnittr").fadeIn(1000);
		selectToAppend = "assignedUnit";
	} else {
		$("label[for=" + "'level" + (level + 1) + "'" + "]").text(selectLabel);
		$("#level" + (level + 1) + "tr").fadeIn(1000);
		selectToAppend = "level" + (level + 1);
	}
	selectedlevelis = level + 1;
}

// #################################################### reset method
// #################################

function reset(level) {
	// alert("reset = "+level);

	// Resetting level combo box.
	for ( var i = (level + 1); i < 5; i++) {

		var select = document.getElementById("level" + i);
		// alert(select);

		// To remove the options
		select.options.length = 0;

		// To add the select options
		var newOption = document.createElement("option");
		newOption.value = "";
		newOption.text = "-- Select --";
		try {
			select.add(newOption, null); // Standard

		} catch (error) {
			select.add(newOption); // IE only

		}
		$("#level" + i + "tr").hide(500);
	}

	// Resetting Assigned unit combo box.
	var assignedUnitSelect = document.getElementById("assignedUnit");

	// To remove the options
	assignedUnitSelect.options.length = 0;

	// To add the select options
	var newOption = document.createElement("option");
	newOption.value = "";
	newOption.text = "-- Select --";
	try {
		assignedUnitSelect.add(newOption, null); // Standard

	} catch (error) {
		assignedUnitSelect.add(newOption); // IE only

	}
	$("#assignedUnittr").hide(500);
}

// =============================================================== validation
// =========================================================

function validate() {

	var errors = new Array();
	var error = false;

	errors[0] = vlidateOnblur('stateId', '1', '75', 'm');

	errors[1] = vlidateOnblur('level0', '1', '75', 'm');

	if (!document.getElementById("level1").hasAttribute("disabled"))
		errors[2] = vlidateOnblur('level1', '1', '75', 'm');

	else
		errors[2] = false;

	if (!document.getElementById("level2").hasAttribute("disabled"))
		errors[3] = vlidateOnblur('level2', '1', '75', 'm');

	else
		errors[3] = false;

	if (!document.getElementById("level3").hasAttribute("disabled"))
		errors[4] = vlidateOnblur('level3', '1', '75', 'm');

	else
		errors[4] = false;

	errors[5] = vlidateOnblur('assignedUnit', '1', '75', 'm');

	for ( var i = 0; i <= 5; i++)

	{
		if (errors[i] == true) {
			error = true;
			break;
		}
	}

	if (error == true) {
		alert("please select the village name");
		return false;
	} else {
		return true;
	}

}

// ==========add these functions in menu_generation.js===== validation
// ===========

function validateDistrict(appId) {
	var district = document.getElementById("level1").value;
	var entityType = document.getElementById("level0").value;
	var stateId = document.getElementById("stateId").value;

	if (district != "" && district.length > 0) {
		pesswitchunitService.validateSwitchUnitForm(entityType, parseInt(stateId), parseInt(appId), parseInt(district), {
			callback : validateSwitchUnitFormSuccess,
			errorHandler : validateSwitchUnitFormFailure
		});
	}
}

function enableSwitchunitForm() {
	document.switchForm.switchunitsubmit.disabled = false;
}

function validateSwitchUnitFormSuccess(data) {
	var entityType = document.getElementById("level0").value;

	if (data == false) {
		$("#" + "district_not_enable").fadeIn(500);
		document.switchForm.switchunitsubmit.disabled = true;

		document.switchForm.level2.disabled = true;
		document.switchForm.level3.disabled = true;
		document.switchForm.level4.disabled = true;

		if (entityType != 'G_1') {
			document.switchForm.assignedUnit.disabled = true;
			document.getElementById("assignedUnit").options.length = 0;
		}
		document.getElementById("level2").options.length = 0;
		document.getElementById("level3").options.length = 0;
		document.getElementById("level4").options.length = 0;

		return false;
	} else {
		$("#" + "district_not_enable").hide();
		document.switchForm.switchunitsubmit.disabled = false;
		document.switchForm.assignedUnit.disabled = false;
		document.switchForm.level2.disabled = false;
		document.switchForm.level3.disabled = false;
		document.switchForm.level4.disabled = false;

	}
}

function validateSwitchUnitFormFailure() {

	alert("dwr Error:: while validating district code");
}

/*
 * function validateZilaDistrict(appId) { var
 * entityType=document.getElementById("level0").value;
 * 
 * if(entityType == 'G_1'){ var
 * district=document.getElementById("assignedUnit").value; var
 * stateId=document.getElementById("stateId").value;
 * 
 * pesswitchunitService.validateSwitchUnitForm(entityType,
 * stateId,appId,district, { callback: validateSwitchUnitFormSuccess,
 * errorHandler : validateSwitchUnitFormFailure }); }else{
 * $("#"+"district_not_enable").hide();
 * document.switchForm.assignedUnit.disabled=false;
 * document.switchForm.level2.disabled=false;
 * document.switchForm.level3.disabled=false;
 * document.switchForm.level4.disabled=false;
 * document.switchForm.switchunitsubmit.disabled=false; } }
 */

function validateZilaDistrict(appId) {
	var entityType = document.getElementById("level0").value;

	if (entityType == 'G_1' || entityType == 'G_11') {
		var levl1 = document.getElementById("level1").value;
		if (levl1 == null || levl1 == " " || levl1.length == 0) {
			var district = document.getElementById("assignedUnit").value;
			var stateId = document.getElementById("stateId").value;
			pesswitchunitService.validateSwitchUnitForm(entityType, parseInt(stateId), parseInt(appId), parseInt(district), {
				callback : validateSwitchUnitFormSuccess,
				errorHandler : validateSwitchUnitFormFailure
			});
		}
	} else {
		$("#" + "district_not_enable").hide();
		document.switchForm.assignedUnit.disabled = false;
		document.switchForm.level2.disabled = false;
		document.switchForm.level3.disabled = false;
		document.switchForm.level4.disabled = false;
		document.switchForm.switchunitsubmit.disabled = false;
	}
}