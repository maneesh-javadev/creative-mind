var ParentLength = 0;
var newEntity = '';
var districtCoverage = '';
var subDistrictCoverage = '';
var villageCoverage = '';
var lbCoverage = '';
var wardCoverage = '';
var gpCoverage = '';
var gpVillageCoverage = '';
var stateCoverage = '';
var deletedCoverage = '';
var coverageModify = '1';
var distPartCoverage = '';
var subPartCoverage = '';
var lbPartCoverage = '';
var gpPartCoverage = '';
var stPartCoverage = '';
var partCoverage = '';
var covValues = new Array();
var paretnunitCode = '';
var lbEntityCodeValues = new Array();
var lbEntityCoverageTypeValues = new Array();
var lbEntityCodeWardValues = new Array();
var lbEntityCoverageTypeWardValues = new Array();
var lbEntityLbCodeWardValues = new Array();
var lbEntityCodeDistValues = new Array();
var lbEntityCoverageTypeDistValues = new Array();
var lbEntityCodeSubDistValues = new Array();
var lbEntityCoverageTypeSubDistValues = new Array();
var lbEntityCodeVillageValues = new Array();
var lbEntityCoverageTypeVillageValues = new Array();
var lbEntityCodeGPValues = new Array();
var lbEntityCoverageTypeGPValues = new Array();
var lbEntityLbCodeGPValues = new Array();
var lbEntityCodeGPVillageValues = new Array();
var lbEntityCoverageTypeGPVillageValues = new Array();
var lbEntityLbCodeGPVillageValues = new Array();
var lbEntityCodeStateValues = new Array();
var lbEntityCoverageTypeStateValues = new Array();
var loadWardFlag=true;

function validatename(deptUnitName, type) {
	var slc = document.getElementById("stateCode").value;
	var unitCode = 0;
	var parentCode = 0;
	if (slc > 0 && type == 'A') {
		lgdDwrOrganizationService.adminUnitOradminChildExist(slc, deptUnitName,
				unitCode, type, parentCode, {
					callback : handleUniqueAdminSuccess,
					errorHandler : handleAdminError
				});
	} else {
		unitCode = document.getElementById('unitLevelCode').value;
		parentCode = document.getElementById('parentUnit').value;
		lgdDwrOrganizationService.adminUnitOradminChildExist(slc, deptUnitName,
				unitCode, type, parentCode, {
					callback : handleUniqueEntitySuccess,
					errorHandler : handleAdminError
				});
	}

}

function ValidateAndSubmitforupdate() {
	if (minimumChangesformodifyAdminUnit()) {
		var obj = document.getElementById('adminLevelNameEng').value;
		var index = document.getElementById('parentUnit').selectedIndex;
		var status = true;
		if (obj.length == 0) {
			status = false;
			document.getElementById("UniqueDeptAdminError").innerHTML = "<font color='red'>Please Enter Administrative Unit Level (In English)</font>";

		} else if (index == 0) {
			status = false;
			document.getElementById("parentAdminUnit").innerHTML = "<font color='red'>Please Select Parent Administrative Unit Level </font>";

		}
		if (status) {
			parentArr=$("#parentUnit").val().split("#");
			$("#parentAdminCode").val(parseInt(parentArr[0]));
			$("#parentCategory").val(parentArr[1]);
			$('input[name=Submit]').prop('disabled', true);
			document.getElementById('form1').action = 'modifyDeptAdmitUnit.htm';
			document.getElementById('form1').submit();

		}
	} else
		alert("Atleast One Change is Required for Modification");
}


function minimumChangesformodifyAdminUnit() {
	/* added overlapunit by deepti on 09-05-2016 */
	var previouscov = document.getElementById('overlapUnithidden').value;
	var changecov = $("input[name='overlapUnit']:checked").val();
	if (previouscov != changecov)
		return true;

	var previousname;
	var changename;
	previousname = document.getElementById('adminUnitName').value;
	changename = document.getElementById('adminLevelNameEng').value;
	changename = changename.replace(/^\s+|\s+$/g, '');
	if (previousname != changename)
		return true;

	previousname = document.getElementById('adminUnitNameLocal').value;
	changename = document.getElementById('adminLevelNameLocal').value;
	changename = changename.replace(/^\s+|\s+$/g, '');
	if (previousname.length == 0 && changename.length > 0)
		return true;
	if (previousname.length > 0 && changename.length > 0
			&& previousname != changename)
		return true;

	previousname = document.getElementById('parentAdminCode').value;
	changename = document.getElementById('parentUnit').value;
	var chilRecords = document.getElementById('childRecords').value;
	if (chilRecords > 0)
		return false;
	else {
		if (previousname != changename) {
			return true;

		} else
			return false;
	}

}

function handleUniqueAdminSuccess(data) {
	if (!data) {
		document.getElementById("UniqueDeptAdminError").innerHTML = "<font color='red'>An Administrative Unit already exists with same name </font>";
		document.getElementById("adminLevelNameEng").value = "";
		document.getElementById("adminLevelNameEng").focus();

	}

}
function handleUniqueEntitySuccess(data) {
	if (!data) {
		document.getElementById("UniqueDeptAdminError").innerHTML = "<font color='red'>An Administrative Entity already exists with same name</font>";
		document.getElementById("adminLevelNameEng").value = "";

	}

}

function handleAdminError() {
	document.getElementById("adminLevelNameEng").value = "";
	document.getElementById("adminLevelNameEng").focus();
}

function chekNameValidatons(inputtxt) {
	document.getElementById("UniqueDeptAdminError").innerHTML = "";
	var val = inputtxt;
	var obj = "";
	var entityType = 'A';
	obj = document.getElementById('adminLevelNameEng');
	if (val.length == 0) {
		document.getElementById("UniqueDeptAdminError").innerHTML = "<font color='red'>Please Enter Unit Name</font>";
		return false;
	}
	if (inputtxt.match(/\S/)) {
		var letterNumber = "";
		inputtxt = inputtxt.replace(/\s+/g, '');
		letterNumber = /^[a-zA-Z\-\.]+$/;
		if (inputtxt.match(letterNumber)) {
			validatename(val, entityType);
		} else {

			document.getElementById("UniqueDeptAdminError").innerHTML = "<font color='red'>Please use [A-Z] [a-z] . - Space</font>";
			obj.value = "";

		}
	}

}

function chekModifyNameValidatons(inputtxt) {
	document.getElementById("UniqueDeptAdminError").innerHTML = "";
	var val = inputtxt;
	var obj = "";
	obj = document.getElementById('adminLevelNameEng');
	var unitName = document.getElementById('adminUnitName').value;
	var temp = inputtxt;
	temp = temp.replace(/^\s+|\s+$/g, '');
	var entityType = 'A';
	if (val.length == 0) {
		document.getElementById("UniqueDeptAdminError").innerHTML = "<font color='red'>Please Enter Unit Name</font>";
		return false;
	}
	if (inputtxt.match(/\S/)) {
		var letterNumber = "";
		inputtxt = inputtxt.replace(/\s+/g, '');
		letterNumber = /^[a-zA-Z\-\.]+$/;
		if (inputtxt.match(letterNumber)) {
			if (temp != unitName)
				validatename(val, entityType);
		} else {

			document.getElementById("UniqueDeptAdminError").innerHTML = "<font color='red'>Please use [A-Z] [a-z] . - space</font>";
			obj.value = "";

		}
	}

}

function ValidateAndSubmit() {
	var obj = document.getElementById('adminLevelNameEng').value;
	var index = document.getElementById('parentUnit').selectedIndex;
	var status = true;
	if (obj.length == 0) {
		status = false;
		document.getElementById("UniqueDeptAdminError").innerHTML = "<font color='red'>Please Enter the name of new Administrative Unit Level in English</font>";
	} else if (index == 0) {
		status = false;
		document.getElementById("parentAdminUnit").innerHTML = "<font color='red'>Please Select Parent Administrative Unit Level </font>";

	}
	if (status) {
		parentArr=$("#parentUnit").val().split("#");
		$("#parentAdminCode").val(parseInt(parentArr[0]));
		$("#parentCategory").val(parentArr[1]);
		document.getElementById('buttonClicked').value = 'D';
		$('button[name=Submit]').prop('disabled', true);
		$('button[name=Publish]').prop('disabled', true);
		document.getElementById('form1').action = 'saveDepartmenAdmitUnits.htm';
		document.getElementById('form1').submit();

	}
}
function ValidateAndSubmitPublish() {
	var obj = document.getElementById('adminLevelNameEng').value;
	var index = document.getElementById('parentUnit').selectedIndex;
	var status = true;
	if (obj.length == 0) {
		status = false;
		document.getElementById("UniqueDeptAdminError").innerHTML = "<font color='red'>Please Enter the name of new Administrative Unit Level in English</font>";
	} else if (index == 0) {
		status = false;
		document.getElementById("parentAdminUnit").innerHTML = "<font color='red'>Please Select Parent Administrative Unit Level </font>";

	}
	if (status) {
		parentArr=$("#parentUnit").val().split("#");
		$("#parentAdminCode").val(parseInt(parentArr[0]));
		$("#parentCategory").val(parentArr[1]);
		document.getElementById('buttonClicked').value = 'P';
		$('input[name=Submit]').prop('disabled', true);
		$('input[name=Publish]').prop('disabled', true);
		document.getElementById('form1').action = 'saveDepartmenAdmitUnits.htm';
		document.getElementById('form1').submit();

	}
}

function clearDivs() {
	document.getElementById("parentAdminUnit").innerHTML = "";

}
function emptyElements() {
	$("#dialog:ui-dialog").dialog("destroy");
	$("#dialog-clearform").dialog({
		resizable : false,
		height : 140,
		modal : true,
		buttons : {
			"Yes" : function() {
				document.getElementById("adminLevelNameEng").value = "";
				document.getElementById("adminLevelNameLocal").value = "";
				document.getElementById('parentUnit').selectedIndex = 0;
				document.getElementById('unitLevelCode').selectedIndex = 0;
				document.getElementById('stateId').selectedIndex = 0;
				document.getElementById("parentAdminUnit").innerHTML = "";
				document.getElementById("UniqueDeptAdminError").innerHTML = "";
				emptyLBList();
				emptyLRList();
				$(this).dialog("close");
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		}
	});

}
function validateChildExist(adminUnitCode) {
	var slc = document.getElementById("stateCode").value;
	var deptUnitName = null;
	var type = 'C';
	document.getElementById("unitCode").value = adminUnitCode;
	document.getElementById("entityType").value = 0;
	var parentCode = -1;
	if (slc > -1) {
		lgdDwrOrganizationService.adminUnitOradminChildExist(slc, deptUnitName,
				adminUnitCode, type, parentCode, {
					callback : handleChildRecSuccesUnit,
					errorHandler : handleChildRecError
				});
	}

}

function handleChildRecSuccesUnit(data) {
	if (!data) {
		alert("The Administrative unit level is currently in use and cannot be deleted");

	} else {
		
		  $("#model-confirm").modal('show');
		 $("#customAlertbody").text("Are you sure to delete administrative unit level ?");
		 $(function () { $('#modelyes').on('click', function () {
		      
		        document.forms['form1'].method = "post";
				document.forms['form1'].action = "deleteDeptAdmitUnit.do?<csrf:token uri='deleteDeptAdmitUnit.do'/>";
				document.forms['form1'].submit();
		       })
		   });
		
		/*$("#dialog:ui-dialog").dialog("destroy");
		$("#dialog-confirm")
				.dialog(
						{
							resizable : false,
							height : 140,
							modal : true,
							buttons : {
								"Yes" : function() {
									document.forms['form1'].method = "post";
									document.forms['form1'].action = "deleteDeptAdmitUnit.do?<csrf:token uri='deleteDeptAdmitUnit.do'/>";
									document.forms['form1'].submit();
									$(this).dialog("close");
								},
								Cancel : function() {
									$(this).dialog("close");
								}
							}
						});*/

	}

}
function handleChildRecError(data) {
	alert("Something is not right!!")
}

function emptymodifyElements() {
	document.getElementById("adminLevelNameEng").value = "";
	document.getElementById("adminLevelNameLocal").value = "";
	document.getElementById("parentAdminUnit").innerHTML = "";
	document.getElementById("UniqueDeptAdminError").innerHTML = "";
	var chilRecords = document.getElementById('childRecords').value;
	if (chilRecords == 0)
		document.getElementById('parentUnit').selectedIndex = 0;
}

function ValidateAndSubmitforEntity(type) {
	var obj = document.getElementById('adminLevelNameEng').value;
	var index = document.getElementById('unitLevelCode').selectedIndex;
	var parentindex = document.getElementById('parentUnit').value;
	var childExist = document.getElementById('parentUnit').options.length;
	var levelName = "";
	var status = true;
	if (index == 0) {
		status = false;
		document.getElementById("parentAdminUnitLevel").innerHTML = "<font color='red'>Please Select Parent Administrative Unit Level</font>";

	}  
	else if (parentindex == -1) {
		status = false;
		document.getElementById("parentAdminUnit").innerHTML = "<font color='red'>Please Select Parent level Administrative Unit Entity</font>";

	}
	else if (childExist == 1 && parentindex == -1) {
		status = false;
		levelName = $("#unitLevelCode option:selected").html();
		customAlert("Please Create Entity of - " + levelName + " Parent First");

	} else if (obj.length == 0) {
		status = false;
		document.getElementById("UniqueDeptAdminError").innerHTML = "<font color='red'>Please Enter the name of new Administrative Unit Entity in English</font>";

	}
	if (document.getElementById('coverageExisttrue').checked) {
		if (selectedCoverageArea()) {
			status = false;
		}
	}
	if (status) {

		if (type == 'D') {
			$('#publishOrSave').val('D');
		} else {
			$('#publishOrSave').val('P');
		}

		$('#submit2').prop('disabled', true);
		$('#submit1').prop('disabled', true);
		$('input[name=Submit]').prop('disabled', true);
		document.getElementById('form1').action = 'saveAdminEntityUnit.htm';
		document.getElementById('form1').submit();

	}
}
function chekNameValidatonsforEntity(inputtxt) {
	var unitLevel = document.getElementById('unitLevelCode').selectedIndex;
	var parentCode = document.getElementById('parentUnit').value;
	if (unitLevel == -1) {
		document.getElementById("parentAdminUnitLevel").innerHTML = "<font color='red'>Please Select Administrative Unit Level, Parent Entity</font>";
		document.getElementById("adminLevelNameEng").value = "";
		return false;
	} else if (parentCode == -1) {
		document.getElementById("parentAdminUnit").innerHTML = "<font color='red'>Please Select Administrative Unit Entity</font>";
		document.getElementById("adminLevelNameEng").value = "";
		return false;
	}

	document.getElementById("UniqueDeptAdminError").innerHTML = "";
	var val = inputtxt;
	var obj = "";
	obj = document.getElementById('adminLevelNameEng');
	var entityType = 'E';
	if (val.length == 0) {
		document.getElementById("UniqueDeptAdminError").innerHTML = "<font color='red'>Please Enter Unit Name</font>";
		return false;
	}
	if (inputtxt.match(/\S/)) {
		var letterNumber = "";
		inputtxt = inputtxt.replace(/\s+/g, '');
		letterNumber = /^[a-zA-Z0-9\-]+$/;
		if (inputtxt.match(letterNumber)) {
			validatename(val, entityType);
		} else {

			document.getElementById("UniqueDeptAdminError").innerHTML = "<font color='red'>Please use [A-Z], [a-z], digits, - ,  space</font>";
			obj.value = "";

		}
	}

}
function ValidateAndSubmitforEntityupdate() {
	if (minimumChangesformodifyAdminEntity()) {
		var obj = document.getElementById('adminLevelNameEng').value;
		var index = document.getElementById('unitLevelCode').selectedIndex;
		var parentNewIndex = document.getElementById('parentUnit').value;
		var parentoldIndex = document.getElementById('parentAdminCode').value;
		var status = true;
		if (obj.length == 0) {
			status = false;
			document.getElementById("UniqueDeptAdminError").innerHTML = "<font color='red'>Please Enter Adminstration Unit Entity (In English)</font>";

		} else if (index == 0) {
			status = false;
			document.getElementById("parentAdminUnit").innerHTML = "<font color='red'>Please Select Administrative unit Level </font>";

		} else if (parentoldIndex > 0) {
			if (parentNewIndex == 0) {
				status = false;
				document.getElementById("parentAdminUnitforEntity").innerHTML = "<font color='red'>Please Select Parent Administrative Unit Level </font>";
			}

		}

		if (status) {
			$('input[name=Submit]').prop('disabled', true);
			document.getElementById('form1').action = 'modifyDeptAdmitEntity.htm';
			document.getElementById('form1').submit();

		}
	} else
		alert("Atleast One Change is Required for Modification");
}

function chekModifyNameValidatonsforEntity(inputtxt) {
	var prelevelCode = document.getElementById("administationUnit").value;
	var newlevelCode = document.getElementById("unitLevelCode").value;
	var parentmodifiedCode = document.getElementById("parentUnit").value;
	document.getElementById("UniqueDeptAdminError").innerHTML = "";
	var val = inputtxt;
	var obj = "";
	obj = document.getElementById('adminLevelNameEng');
	var unitName = document.getElementById('adminUnitName').value;
	var temp = inputtxt;
	temp = temp.replace(/^\s+|\s+$/g, '');
	var entityType = 'E';
	if (val.length == 0) {
		document.getElementById("UniqueDeptAdminError").innerHTML = "<font color='red'>Please Enter Unit Name</font>";
		return false;
	}
	if (inputtxt.match(/\S/)) {
		var letterNumber = "";
		inputtxt = inputtxt.replace(/\s+/g, '');
		letterNumber = /^[a-zA-Z0-9\-]+$/;
		if (inputtxt.match(letterNumber)) {

			if (temp != unitName || prelevelCode != newlevelCode
					|| parentmodifiedCode != paretnunitCode) {
				validatename(val, entityType);
				return true;
			}

		} else {

			document.getElementById("UniqueDeptAdminError").innerHTML = "<font color='red'>Please use [A-Z], [a-z], digits , - , space</font>";
			obj.value = "";

		}
	}

}
function minimumChangesformodifyAdminEntity() {

	var previousname;
	var changename;
	previousname = document.getElementById('adminUnitName').value;
	changename = document.getElementById('adminLevelNameEng').value;
	changename = changename.replace(/^\s+|\s+$/g, '');
	if (previousname != changename)
		return true;

	previousname = document.getElementById('adminUnitNameLocal').value;
	changename = document.getElementById('adminLevelNameLocal').value;
	changename = changename.replace(/^\s+|\s+$/g, '');
	if (previousname.length == 0 && changename.length > 0)
		return true;
	if (previousname.length > 0 && changename.length > 0
			&& previousname != changename)
		return true;

	previousname = document.getElementById('administationUnit').value;
	changename = document.getElementById('unitLevelCode').value;
	if (previousname != changename)
		return true;

	previousname = document.getElementById('parentAdminCode').value;
	changename = document.getElementById('parentUnit').value;
	var chilRecords = document.getElementById('childRecords').value;
	if (chilRecords > 0)
		return false;
	else {
		if (previousname != changename) {
			return true;

		} else
			return false;
	}
}
function validateEntityChildExist(adminUnitEntityCode) {
	var slc = document.getElementById("stateCode").value;
	var deptUnitName = null;
	var type = 'D';
	document.getElementById("unitCode").value = adminUnitEntityCode;
	document.getElementById("entityType").value = 1;
	var parentCode = 0;
	// if (slc > 0) {
	lgdDwrOrganizationService.adminUnitOradminChildExist(slc, deptUnitName,
			adminUnitEntityCode, type, parentCode, {
				callback : handleChildRecSuccesEntity,
				errorHandler : handleChildRecError
			});
	// }

}

function validateEntityChildExistforDraft(adminUnitCode) {
	var slc = document.getElementById("stateCode").value;
	var deptUnitName = null;
	var type = 'D';
	document.getElementById("unitCode").value = adminUnitCode;
	document.getElementById("entityType").value = 1;
	var parentCode = 0;
	// if (slc > 0) {
	lgdDwrOrganizationService.adminUnitOradminChildExist(slc, deptUnitName,
			adminUnitCode, type, parentCode, {
				callback : handleChildRecSuccesEntityForDraft,
				errorHandler : handleChildRecError
			});
	// }

}

function emptymodifyEntityElements() {
	document.getElementById("adminLevelNameEng").value = "";
	document.getElementById("adminLevelNameLocal").value = "";
	document.getElementById("parentAdminUnit").innerHTML = "";
	document.getElementById("UniqueDeptAdminError").innerHTML = "";
	document.getElementById("parentAdminUnitforEntity").innerHTML = "";
	document.getElementById('unitLevelCode').selectedIndex = 0;
	var chilRecords = document.getElementById('childRecords').value;
	if (chilRecords == 0)
		document.getElementById('parentUnit').selectedIndex = 0;
}
function handleChildRecSuccesEntity(data) {
	if (!data) {
		alert("The Administrative unit Entity is currently in use and cannot be deleted");

	} else {
		/*
		 * added by kirandeep on 19/05/2015 Issue in Manage Administrative Unit
		 * Entities
		 */

		lgdDwrOrganizationService.getAdminEntityOrgUnits(document
				.getElementById("unitCode").value, {
			callback : handleAdminOrgUnitsValidation,
			errorHandler : handleChildRecError
		});

	}

}

/* added by kirandeep on 19/05/2015 Issue in Manage Administrative Unit Entities */

function handleAdminOrgUnitsValidation(data) {

	if (data.length > 0) {
//		$("#dialog-message").dialog("open");
		$("#dialog-message").modal('show');
		$("#dialog-message").removeAttr("style")
		$("#modifyAdminUnitLevelOrg > tbody").empty();
		$.each(data, function(key, obj) {

			var row = $("<tr>");

			row.append($("<td class='tblRowB'>").text(obj));
			$("#modifyAdminUnitLevelOrg").append(row);
		});
	} else {
		
		

		 $("#model-confirm").modal('show');
		 $("#customAlertbody").text("Are you sure to delete administrative unit level ?");
		 $(function () { $('#modelyes').on('click', function () {
		     
			    document.forms['form1'].method = "post";
				document.forms['form1'].action = "deleteDeptAdmitUnit.htm?<csrf:token uri='deleteDeptAdmitUnit.htm'/>";
				document.forms['form1'].submit();
		      
		     })
		   });
		
		
		
	/*	$("#dialog:ui-dialog").dialog("destroy");
		$("#dialog-confirm")
				.dialog(
						{
							resizable : false,
							height : 140,
							modal : true,
							buttons : {
								"Yes" : function() {
									document.forms['form1'].method = "post";
									document.forms['form1'].action = "deleteDeptAdmitUnit.do?<csrf:token uri='deleteDeptAdmitUnit.do'/>";
									document.forms['form1'].submit();
									$(this).dialog("close");
								},
								Cancel : function() {
									$(this).dialog("close");
								}
							}
						});*/
	}
}

function handleChildRecSuccesEntityForDraft(data) {
	if (!data) {
		alert("The Administrative unit Entity is currently in use and cannot be deleted");

	} else {
		$("#dialog:ui-dialog").dialog("destroy");
		$("#dialog-confirm")
				.dialog(
						{
							resizable : false,
							height : 140,
							modal : true,
							buttons : {
								"Yes" : function() {
									document.forms['form1'].method = "post";
									document.forms['form1'].action = "deleteDeptAdmitUnitForDraft.do?<csrf:token uri='deleteDeptAdmitUnitForDraft.do'/>";
									document.forms['form1'].submit();
									$(this).dialog("close");
								},
								Cancel : function() {
									$(this).dialog("close");
								}
							}
						});

	}

}

function toggleCovergeDiv() {
	var status = false;
	var overlappValue = $("#overlappValue").val();
	if (overlappValue == 'N') {
		getAddedCoverage();
	}
	var obj = document.getElementById('adminLevelNameEng').value;
	var index = document.getElementById('unitLevelCode').selectedIndex;
	var slc = document.getElementById('stateCode').value;
	var index1 = document.getElementById('parentUnit').value;
	if (index1 ==-1) {
		status = true;
		document.getElementById("parentAdminUnit").innerHTML = "<font color='red'>Please Select Parent level Administrative Unit Level</font>";
	}
	if (obj.length == 0) {
		status = true;
		document.getElementById("UniqueDeptAdminError").innerHTML = "<font color='red'>Please Enter the name of new Administrative Unit Entity in English</font>";

	}
	if (index == 0) {
		status = true;
		document.getElementById("parentAdminUnitLevel").innerHTML = "<font color='red'>Please Select Parent Administrative Unit Level</font>";

	}
	if (status) {
		document.getElementById('coverageExisttrue').checked = false;
		return false;
	}
	if (document.getElementById('coverageExisttrue').checked) {
		document.getElementById('coveragediv').style.visibility = 'visible';
		document.getElementById('coveragediv').style.display = 'block';
		if (slc == 0) {
			document.getElementById('existingLB').disabled = true;
		}
		document.getElementById('existingLB').checked = false;
		document.getElementById('existingLR').checked = false;
	} else {
		document.getElementById('coveragediv').style.display = 'none';
		document.getElementById('LBRegion').style.display = 'none';
		document.getElementById('LRRegion').style.display = 'none';
		$("#SourceLBList").empty();
		$("#contributedLBList").empty();
		$("#souceWardList").empty();
		$("#contributedWardList").empty();
		$("#contributedDistirct").empty();
		$("#sourceSubDistrict").empty();
		$("#contributedSubDistirct").empty();
		$("#souceVilalgeList").empty();
		$("#villageListContributed").empty();

	}

}
function toggleLBCovergeDiv() {
	if (document.getElementById('existingLB').checked) {
		document.getElementById('LBRegion').style.visibility = 'visible';
		document.getElementById('LBRegion').style.display = 'block';

	} else {
		document.getElementById('LBRegion').style.display = 'none';
	}
}
function toggleLRCovergeDiv() {
	var slc = document.getElementById('stateCode').value;
	if (document.getElementById('existingLR').checked) {
		document.getElementById('LRRegion').style.visibility = 'visible';
		document.getElementById('LRRegion').style.display = 'block';
		if (slc == 0) {
			document.getElementById('stateSelectionBlock').style.visibility = 'visible';
			document.getElementById('stateSelectionBlock').style.display = 'block';
		}
		if (lbEntityCodeVillageValues.length > 0) {
			document.getElementById('villageListId').style.visibility = 'visible';
			document.getElementById('villageListId').style.display = 'block';
		}
	} else {
		document.getElementById('LRRegion').style.display = 'none';
	}

	removeAddedCoverage('D');
}

function addItem(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for (var j = 0; j < document.getElementById(list2).options.length; j++)
			if (document.getElementById(list2).options[j].selected == true)
				if (doAddVal) {
					$('#' + list1).append(
							"<option paramCategory="
									+ document.getElementById(list2)[j]
											.getAttribute('paramCategory')
									+ " value="
									+ document.getElementById(list2)[j].value
									+ val + ">"
									+ document.getElementById(list2)[j].text
									+ " (" + val + ")</option>");
				} else {
					$('#' + list1).append(
							"<option paramCategory="
									+ document.getElementById(list2)[j]
											.getAttribute('paramCategory')
									+ " value="
									+ document.getElementById(list2)[j].value
									+ " >"
									+ document.getElementById(list2)[j].text
									+ "</option>");
				}
		removeSelectedValue(list2);
	}
}

/* Modified by pooja on 06-08-2015 */
function removeOnedistrictOption(list1, list2, doRemoveVal, type) {
	if (type == 1) {
		$("#souceWardList").empty();
		$("#contributedWardList").empty();
	} else if (type == 2) {
		$("#sourceSubDistrict").empty();
		$("#contributedSubDistirct").empty();
		$("#souceVilalgeList").empty();
		$("#villageListContributed").empty();
		$("#sourceGP").empty();
		$("#contributedGP").empty();
		$("#sourceGpVillageList").empty();
		$("#gpVillageListContributed").empty();
	} else if (type == 3) {
		$("#souceVilalgeList").empty();
		$("#villageListContributed").empty();
		$("#sourceGP").empty();
		$("#contributedGP").empty();
		$("#sourceGpVillageList").empty();
		$("#gpVillageListContributed").empty();
	} else if (type == 4) {
		$("#sourceGpVillageList").empty();
		$("#gpVillageListContributed").empty();
	} else if (type == 5) {
		$("#SourceDistrict").empty();
		$("#contributedDistirct").empty();
		$("#sourceSubDistrict").empty();
		$("#contributedSubDistirct").empty();
		$("#souceVilalgeList").empty();
		$("#villageListContributed").empty();
		$("#sourceGP").empty();
		$("#contributedGP").empty();
		$("#sourceGpVillageList").empty();
		$("#gpVillageListContributed").empty();
	}
	if (document.getElementById(list1).selectedIndex >= 0) {
		for (var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2)
							.append(
									"<option paramCategory="
											+ document.getElementById(list1)[j]
													.getAttribute('paramCategory')
											+ " value="
											+ document.getElementById(list1)[j].value
													.substr(
															0,
															document
																	.getElementById(list1)[j].value.length - 4)
											+ " >"
											+ document.getElementById(list1)[j].text
													.substr(
															0,
															document
																	.getElementById(list1)[j].text.length - 6)
											+ "</option>");
				} else {
					$('#' + list2).append(
							"<option paramCategory="
									+ document.getElementById(list1)[j]
											.getAttribute('paramCategory')
									+ " value="
									+ document.getElementById(list1)[j].text
									+ " >"
									+ document.getElementById(list1)[j].text
									+ "</option>");
				}
			}
		}
		removeSelectedValue(list1);
	}
}
/* Modified by pooja on 06-08-2015 */
function removeAllList(list1, list2, doRemoveVal, type) {
	selectAll(list1);
	if (type == 1) {
		$("#souceWardList").empty();
		$("#contributedWardList").empty();
	} else if (type == 2) {
		$("#sourceSubDistrict").empty();
		$("#contributedSubDistirct").empty();
		$("#souceVilalgeList").empty();
		$("#villageListContributed").empty();
		$("#sourceGP").empty();
		$("#contributedGP").empty();
		$("#sourceGpVillageList").empty();
		$("#gpVillageListContributed").empty();
	} else if (type == 3) {
		$("#souceVilalgeList").empty();
		$("#villageListContributed").empty();
		$("#sourceGP").empty();
		$("#contributedGP").empty();
		$("#sourceGpVillageList").empty();
		$("#gpVillageListContributed").empty();
	} else if (type == 4) {
		$("#sourceGpVillageList").empty();
		$("#gpVillageListContributed").empty();
	} else if (type == 5) {
		$("#SourceDistrict").empty();
		$("#contributedDistirct").empty();
		$("#sourceSubDistrict").empty();
		$("#contributedSubDistirct").empty();
		$("#souceVilalgeList").empty();
		$("#villageListContributed").empty();
		$("#sourceGP").empty();
		$("#contributedGP").empty();
		$("#sourceGpVillageList").empty();
		$("#gpVillageListContributed").empty();
	}
	if (document.getElementById(list1).selectedIndex >= 0) {
		for (var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2)
							.append(
									"<option paramCategory="
											+ document.getElementById(list1)[j]
													.getAttribute('paramCategory')
											+ " value="
											+ document.getElementById(list1)[j].value
													.substr(
															0,
															document
																	.getElementById(list1)[j].value.length - 4)
											+ " >"
											+ document.getElementById(list1)[j].text
													.substr(
															0,
															document
																	.getElementById(list1)[j].text.length - 6)
											+ "</option>");
				} else {
					$('#' + list2).append(
							"<option paramCategory="
									+ document.getElementById(list1)[j]
											.getAttribute('paramCategory')
									+ " value="
									+ document.getElementById(list1)[j].text
									+ " >"
									+ document.getElementById(list1)[j].text
									+ "</option>");
				}
			}
		}
		removeSelectedValue(list1);
	}
}
function removeSelectedValue(val) {
	var elSel = document.getElementById(val);
	for (var i = elSel.length - 1; i >= 0; i--) {
		if (elSel.options[i].selected) {
			elSel.remove(i);
		}
	}
}

function selectAll(obj) {
	var selObj = document.getElementById(obj);
	for (var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}
}

function getSubdistrictsList(isExistOrNew) {
	$("#sourceSubDistrict").empty();
	$("#contributedSubDistirct").empty();
	$("#souceVilalgeList").empty();
	$("#villageListContributed").empty();
	var selObj = document.getElementById('contributedDistirct');
	var idValuePart = "";
	var count = 0;
	var str = "";
	for (var i = 0; i < selObj.options.length; i++) {
		str = selObj.options[i].value;
		if (str.indexOf('PART') > -1) {
			if (idValuePart == "") {
				idValuePart = selObj.options[i].value;
			} else {
				idValuePart = idValuePart + "," + selObj.options[i].value;
			}
			count++;
		}
	}
	if (isExistOrNew == 'N') {
		if (count == 0) {
			alert("Please Select Part District ");
			return false;
		}
	}
	if (idValuePart != "") {
		lgdDwrDistrictService.getSubDistrictListbyDistrictForLocalBody(
				idValuePart, {
					callback : handleSubDistrictSuccess,
					errorHandler : handleError
				});
	}

}

function handleSubDistrictSuccess(data) {
	if (data.length > 0) {
		var options = $("#sourceSubDistrict");
		var options1 = $("#contributedSubDistirct");
		$
				.each(
						data,
						function(key, obj) {
							var option = $("<option/>");
							if (obj.operation_state == 'F') {
								$(option).attr('disabled', 'disabled');
								$(option).val(obj.subdistrictCode).text(
										obj.subdistrictNameEnglish
												+ ' [ District : '
												+ obj.districtNameEnglish
												+ ' ] ');
								options.append(option);
							} else {
								$(option).val(obj.subdistrictCode).text(
										obj.subdistrictNameEnglish
												+ ' [ District : '
												+ obj.districtNameEnglish
												+ ' ] ');
								options.append(option);
							}
							for (var i = 0; i < lbEntityCodeSubDistValues.length; i++) {
								var option1 = $("<option/>");
								var lbEntityCodeSubDistValue = lbEntityCodeSubDistValues[i];
								var lbEntityCoverageTypeSubDistValue = lbEntityCoverageTypeSubDistValues[i];
								if (lbEntityCodeSubDistValue == obj.subdistrictCode) {
									$(option1)
											.val(
													lbEntityCodeSubDistValue
															+ lbEntityCoverageTypeSubDistValue)
											.text(
													obj.subdistrictNameEnglish
															+ ' [ District : '
															+ obj.districtNameEnglish
															+ ' ] '
															+ ' ('
															+ lbEntityCoverageTypeSubDistValue
															+ ')');
									options1.append(option1);
								}
							}

						});

		// dwr.util.addOptions('sourceSubDistrict', objData, 'code', 'name');
		removeAddedCoverage('T');

		var sourceSubDistrict = document.getElementById('sourceSubDistrict');
		var contributedSubDistrict = document
				.getElementById('contributedSubDistirct');
		for (var i = 0; i < contributedSubDistrict.options.length; i++) {
			for (var j = 0; j < sourceSubDistrict.options.length; j++) {
				if (sourceSubDistrict.options[j].value == contributedSubDistrict.options[i].value
						.substring(
								0,
								contributedSubDistrict.options[i].value.length - 4)) {
					sourceSubDistrict.remove(j);
					break;
				}
			}
		}

		if (contributedSubDistrict.options.length > 0)
			selectVillageList('E');
		getGPList('E');
	}
}

function selectVillageList(isExistOrNew) {
	$("#souceVilalgeList").empty();
	$("#villageListContributed").empty();
	selObj = document.getElementById('contributedSubDistirct');
	var temp = "";
	var villageId = "";
	var count = 0;
	for (var i = 0; i < selObj.options.length; i++) {
		if ((selObj.options[i].value)
				.substr(selObj.options[i].value.length - 4) == "PART") {
			temp = selObj.options[i].value;

			temp = temp.substr(0, temp.length - 4);
			villageId = villageId + temp + ",";
			count++;

		}
	}
	if (isExistOrNew == 'N') {
		$("#sourceGP").empty();
		$("#contributedGP").empty();
		$("#sourceGpVillageList").empty();
		$("#gpVillageListContributed").empty();
		document.getElementById('gpListId').style.visibility = 'hidden';
		document.getElementById('gpListId').style.display = 'none';
		document.getElementById('gpVillageListId').style.visibility = 'hidden';
		document.getElementById('gpVillageListId').style.display = 'none';
		document.getElementById('villageListId').style.visibility = 'visible';
		document.getElementById('villageListId').style.display = 'block';
		if (count == 0) {
			alert("Please Select Part Subdistrict ");
			return false;
		} else {
			villageId = villageId.substring(0, villageId.length - 1);
			lgdDwrVillageService.getVillagebysubdisrictCodesForLocalBody(
					villageId, {
						callback : handleVillageSuccess,
						errorHandler : handleError
					});
		}
	}
	if (isExistOrNew == 'E') {
		if (count > 0) {
			villageId = villageId.substring(0, villageId.length - 1);
			lgdDwrVillageService.getVillagebysubdisrictCodesForLocalBody(
					villageId, {
						callback : handleVillageSuccess,
						errorHandler : handleError
					});
		}
	}
}
function handleVillageSuccess(data) {
	var fieldId = 'souceVilalgeList';
	var valueText = 'villageCode';
	var nameText = 'villageNameEnglish';
	dwr.util.removeAllOptions(fieldId);

	var options = $("#souceVilalgeList");
	var options1 = $("#villageListContributed");
	$
			.each(
					data,
					function(key, obj) {
						var option = $("<option/>");
						if (obj.operation_state == 'F') {
							$(option).attr('disabled', 'disabled');
							$(option).val(obj.villageCode).text(
									obj.villageNameEnglish+"("+obj.villageCode+")");
							options.append(option);
						} else {
							$(option).val(obj.villageCode).text(
									obj.villageNameEnglish+"("+obj.villageCode+")");
							options.append(option);
						}
						for (var i = 0; i < lbEntityCodeVillageValues.length; i++) {
							var option1 = $("<option/>");
							var lbEntityCodeVillageValue = lbEntityCodeVillageValues[i];
							var lbEntityCoverageTypeVillageValue = lbEntityCoverageTypeVillageValues[i];
							if (lbEntityCodeVillageValue == obj.villageCode) {
								$(option1)
										.val(
												lbEntityCodeVillageValue
														+ lbEntityCoverageTypeVillageValue)
										.text(
												obj.villageNameEnglish+
												'('+lbEntityCodeVillageValue+')('
														+ lbEntityCoverageTypeVillageValue
														+ ')');
								options1.append(option1);
							}
						}
					});

	// dwr.util.addOptions(fieldId, data, valueText, nameText);
	if (data.length > 0)
		removeAddedCoverage('V');

	var sourceVillage = document.getElementById('souceVilalgeList');
	var contributedVillage = document.getElementById('villageListContributed');
	for (var i = 0; i < contributedVillage.options.length; i++) {
		for (var j = 0; j < sourceVillage.options.length; j++) {
			if (sourceVillage.options[j].value == contributedVillage.options[i].value
					.substring(0,
							contributedVillage.options[i].value.length - 4)) {
				sourceVillage.remove(j);
				break;
			}
		}
	}
	if (contributedVillage.options.length > 0) {
		document.getElementById('villageListId').style.visibility = 'visible';
		document.getElementById('villageListId').style.display = 'block';
	}
}
function getGPList(isExistOrNew) {
	$("#sourceGP").empty();
	$("#contributedGP").empty();
	$("#sourceGpVillageList").empty();
	$("#gpVillageListContributed").empty();
	selObj = document.getElementById('contributedSubDistirct');
	var temp = "";
	var subDistricts = "";
	var count = 0;
	for (var i = 0; i < selObj.options.length; i++) {
		if ((selObj.options[i].value)
				.substr(selObj.options[i].value.length - 4) == "PART") {
			temp = selObj.options[i].value;

			temp = temp.substr(0, temp.length - 4);
			subDistricts = subDistricts + temp + ",";
			count++;

		}
	}
	if (isExistOrNew == 'N') {
		$("#souceVilalgeList").empty();
		$("#villageListContributed").empty();
		document.getElementById('villageListId').style.visibility = 'hidden';
		document.getElementById('villageListId').style.display = 'none';
		document.getElementById('gpListId').style.visibility = 'visible';
		document.getElementById('gpListId').style.display = 'block';
		if (count == 0) {
			alert("Please Select Part Subdistrict ");
			return false;
		} else {
			subDistricts = subDistricts.substring(0, subDistricts.length - 1);
			lgdDwrVillageService.getLBDetailsBySubDistricts(subDistricts, {
				callback : handleGPSuccess,
				errorHandler : handleError
			});
		}
	} else if (isExistOrNew == 'E') {
		if (count > 0) {
			subDistricts = subDistricts.substring(0, subDistricts.length - 1);
			lgdDwrVillageService.getLBDetailsBySubDistricts(subDistricts, {
				callback : handleGPSuccess,
				errorHandler : handleError
			});
		}
	}
}
function handleGPSuccess(data) {
	var fieldId = 'sourceGP';
	dwr.util.removeAllOptions(fieldId);

	var options = $("#sourceGP");
	var options1 = $("#contributedGP");
	$.each(data, function(key, obj) {
		var option = $("<option/>");

		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.localBodyCode).text(
					obj.localBodyName + " [ Sub-District : "
							+ obj.subDistrictName + "]");
			options.append(option);
		} else {

			$(option).attr('paramCategory', obj.subDistrictCode);
			$(option).val(obj.localBodyCode).text(
					obj.localBodyName + " [ Sub-District : "
							+ obj.subDistrictName + "]");
			options.append(option);
		}

		for (var i = 0; i < lbEntityCodeGPValues.length; i++) {
			var option1 = $("<option/>");
			var lbEntityCodeGPValue = lbEntityCodeGPValues[i];
			var lbEntityCoverageTypeGPValue = lbEntityCoverageTypeGPValues[i];
			var lbEntityLbCodeGPValue = lbEntityLbCodeGPValues[i];
			if (lbEntityCodeGPValue == obj.localBodyCode) {
				$(option1).val(
						lbEntityCodeGPValue + lbEntityCoverageTypeGPValue)
						.text(
								obj.localBodyName + " [ Sub-District : "
										+ obj.subDistrictName + "]" + " ("
										+ lbEntityCoverageTypeGPValue + ")");
				$(option1).attr('paramCategory', lbEntityLbCodeGPValue);
				options1.append(option1);
			}
		}

	});

	// dwr.util.addOptions(fieldId, data, valueText, nameText);
	if (data.length > 0)
		removeAddedCoverage('GP');

	var sourceGP = document.getElementById('sourceGP');
	var contributedGP = document.getElementById('contributedGP');
	for (var i = 0; i < contributedGP.options.length; i++) {
		for (var j = 0; j < sourceGP.options.length; j++) {
			if (sourceGP.options[j].value == contributedGP.options[i].value
					.substring(0, contributedGP.options[i].value.length - 4)) {
				sourceGP.remove(j);
				break;
			}
		}
	}
	if (contributedGP.options.length > 0) {
		document.getElementById('gpListId').style.visibility = 'visible';
		document.getElementById('gpListId').style.display = 'block';
		getCoveredAreaofLocalBodyList('E');
	}
}
function getCoveredAreaofLocalBodyList(isExistOrNew) {
	$("#sourceGpVillageList").empty();
	$("#gpVillageListContributed").empty();
	selObj = document.getElementById('contributedGP');
	var temp = "";
	var localBodyCodes = "";
	var count = 0;
	for (var i = 0; i < selObj.options.length; i++) {
		if ((selObj.options[i].value)
				.substr(selObj.options[i].value.length - 4) == "PART") {
			temp = selObj.options[i].value;

			temp = temp.substr(0, temp.length - 4);
			localBodyCodes = localBodyCodes + temp + ",";
			count++;

		}
	}
	if (isExistOrNew == 'N') {
		document.getElementById('gpVillageListId').style.visibility = 'visible';
		document.getElementById('gpVillageListId').style.display = 'block';
		if (count == 0) {
			alert("Please Select Part Gram Panchayat");
			return false;
		} else {
			localBodyCodes = localBodyCodes.substring(0,
					localBodyCodes.length - 1);
			lgdDwrlocalBodyService.getCoveredAreaListByLbCodes(localBodyCodes,
					{
						callback : handleGpVillageSuccess,
						errorHandler : handleError
					});
		}
	} else if (isExistOrNew == 'E') {
		if (count > 0) {
			localBodyCodes = localBodyCodes.substring(0,
					localBodyCodes.length - 1);
			lgdDwrlocalBodyService.getCoveredAreaListByLbCodes(localBodyCodes,
					{
						callback : handleGpVillageSuccess,
						errorHandler : handleError
					});
		}
	}
}
function handleGpVillageSuccess(data) {
	var fieldId = 'sourceGpVillageList';
	dwr.util.removeAllOptions(fieldId);

	var options = $("#sourceGpVillageList");
	var options1 = $("#gpVillageListContributed");
	$
			.each(
					data,
					function(key, obj) {
						var option = $("<option/>");
						if (obj.operation_state == 'F') {
							$(option).attr('disabled', 'disabled');
							$(option).val(obj.landRegionCode).text(
									obj.landRegionNameEnglish);
							options.append(option);
						} else {

							$(option).attr('paramCategory', obj.localBodyCode);
							$(option).val(obj.landRegionCode).text(
									obj.landRegionNameEnglish
											+ " [ Gram-Panchayat : "
											+ obj.localBodyNameEnglish + "]");
							options.append(option);
						}

						for (var i = 0; i < lbEntityCodeGPVillageValues.length; i++) {
							var option1 = $("<option/>");
							var lbEntityCodeGPVillageValue = lbEntityCodeGPVillageValues[i];
							var lbEntityCoverageTypeGPVillageValue = lbEntityCoverageTypeGPVillageValues[i];
							var lbEntityLbCodeGPVillageValue = lbEntityLbCodeGPVillageValues[i];
							if (lbEntityCodeGPVillageValue == obj.landRegionCode) {
								$(option1)
										.val(
												lbEntityCodeGPVillageValue
														+ lbEntityCoverageTypeGPVillageValue)
										.text(
												obj.landRegionNameEnglish
														+ " [ Gram-Panchayat : "
														+ obj.localBodyNameEnglish
														+ "]"
														+ " ("
														+ lbEntityCoverageTypeGPVillageValue
														+ ")");
								$(option1).attr('paramCategory',
										lbEntityLbCodeGPVillageValue);
								options1.append(option1);
							}
						}

					});

	// dwr.util.addOptions(fieldId, data, valueText, nameText);

	if (data.length > 0)
		removeAddedCoverage('GV');

	var sourceGpVillageList = document.getElementById('sourceGpVillageList');
	var gpVillageListContributed = document
			.getElementById('gpVillageListContributed');
	for (var i = 0; i < gpVillageListContributed.options.length; i++) {
		for (var j = 0; j < sourceGpVillageList.options.length; j++) {
			if (sourceGpVillageList.options[j].value == gpVillageListContributed.options[i].value
					.substring(
							0,
							gpVillageListContributed.options[i].value.length - 4)) {
				sourceGpVillageList.remove(j);
				break;
			}
		}
	}
	if (gpVillageListContributed.options.length > 0) {
		document.getElementById('gpVillageListId').style.visibility = 'visible';
		document.getElementById('gpVillageListId').style.display = 'block';
	}
}
function handleError() {
	alert("No data found!");
}

function getUrbanlocabodies(s) {
	$("#SourceLBList").empty();
	$("#contributedLBList").empty();
	$("#souceWardList").empty();
	$("#contributedWardList").empty();
	dwr.util.removeAllOptions("SourceLBList");
	var selObj = document.getElementById('ulbID');
	var scode = document.getElementById('stateCode').value;
	var ulbId = "";
	for (var i = 0; i < selObj.options.length; i++) {
		if (selObj.options[i].selected == true) {
			ulbId = selObj.options[i].value;
			lgdDwrlocalBodyService.getPanchayatListbyStateandlbTypeCode(scode,
					ulbId, {
						callback : fillULBList,
						errorHandler : handleError
					});
		}
	}

}
function fillULBList(data) {
	var options = $("#SourceLBList");
	var options1 = $("#contributedLBList");
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
		for (var i = 0; i < lbEntityCodeValues.length; i++) {
			var option1 = $("<option/>");
			var lbEntityCodeValue = lbEntityCodeValues[i];
			var lbEntityCoverageTypeValue = lbEntityCoverageTypeValues[i];
			if (lbEntityCodeValue == obj.localBodyCode) {
				$(option1).val(lbEntityCodeValue + lbEntityCoverageTypeValue)
						.text(
								obj.localBodyNameEnglish + ' ('
										+ lbEntityCoverageTypeValue + ')');
				options1.append(option1);
			}
		}
	});

	if (data.length > 0)
		removeAddedCoverage('G');

	var sourceLBList = document.getElementById("SourceLBList");
	var contributedLBList = document.getElementById("contributedLBList");
	for (var i = 0; i < contributedLBList.options.length; i++) {
		for (var j = 0; j < sourceLBList.options.length; j++) {
			if (sourceLBList.options[j].value == contributedLBList.options[i].value
					.substring(0, contributedLBList.options[i].value.length - 4)) {
				sourceLBList.remove(j);
				break;
			}
		}
	}
	if (contributedLBList.options.length > 0)
		getWardList(null);
}

function getWardList(existOrNew) {
	$("#souceWardList").empty();
	$("#contributedWardList").empty();
	var selObj = document.getElementById('contributedLBList');
	var id = "";
	var count = 0;
	for (var i = 0; i < selObj.options.length; i++) {
		id = selObj.options[i].value;
		if (id.indexOf('PART') > -1) {
			id = id.substr(0, id.length - 4);
			document.getElementById('lbidWard').value = id;
			lgdDwrlocalBodyService.getlocalGovtBodyWardList(id, {
				callback : handleZPWardSuccess,
				errorHandler : handleVillageError,
				arg : id
			});
			count++;
		}
	}
	if (existOrNew == 'N') {
		if (count == 0) {
			alert("Please Select part ULB ");
			return false;
		}
	}
}

/* modified by pooja on 06-08-2015 */
function handleZPWardSuccess(data, lbId) {
	var options = $("#souceWardList");
	var options1 = $("#contributedWardList");
	$
			.each(
					data,
					function(key, obj) {
						var option = $("<option/>");
						if (obj.operation_state == 'F') {
							$(option).attr('disabled', 'disabled');
							$(option).val(obj.wardCode).text(
									obj.wardNameInEnglish);
							options.append(option);
						} else {
							$(option).attr('paramCategory', lbId);
							$(option).val(obj.wardCode).text(
									obj.wardNameInEnglish);
							options.append(option);
						}
						for (var i = 0; i < lbEntityCodeWardValues.length; i++) {
							var option1 = $("<option/>");
							var lbEntityCodeWardValue = lbEntityCodeWardValues[i];
							var lbEntityCoverageTypeWardValue = lbEntityCoverageTypeWardValues[i];
							var lbEntityLbCodeWardValue = lbEntityLbCodeWardValues[i];
							if (lbEntityCodeWardValue == obj.wardCode) {
								$(option1)
										.val(
												lbEntityCodeWardValue
														+ lbEntityCoverageTypeWardValue)
										.text(
												obj.wardNameInEnglish
														+ ' ('
														+ lbEntityCoverageTypeWardValue
														+ ')');
								$(option1).attr('paramCategory',
										lbEntityLbCodeWardValue);
								options1.append(option1);
							}
						}
					});

	// dwr.util.addOptions(fieldId, data, valueText, nameText);
	if (data.length > 0)
		removeAddedCoverage('W');
}

function handleVillageError() {
	alert("No data found!");
}
function selectedCoverageArea() {
	var selObj = '';
	var coveragelength = 0;
	var id = "";
	var temp = '';
	var coverage = '';
	var i = '';
	
	selObj = document.getElementById('contributedState');
	if(selObj!=null){
		for (i = 0; i < selObj.options.length; i++) {
			id = selObj.options[i].value;
			temp = '';
			if (id.indexOf('FULL') > -1) {
				id = id.substring(0, id.length - 4);
				temp = id + ";" + 'S' + ";" + 'F' + ";" + 0;

			} else {
				id = id.substring(0, id.length - 4);
				temp = id + ";" + 'S' + ";" + 'P' + ";" + 0;
			}
			coverage = coverage + ":" + temp;
		}
	}
	
	selObj = document.getElementById('contributedDistirct');
	if(selObj!=null){
		for (i = 0; i < selObj.options.length; i++) {
			id = selObj.options[i].value;
			temp = '';
			if (id.indexOf('FULL') > -1) {
				id = id.substring(0, id.length - 4);
				temp = id + ";" + 'D' + ";" + 'F' + ";" + 0;

			} else {
				id = id.substring(0, id.length - 4);
				temp = id + ";" + 'D' + ";" + 'P' + ";" + 0;
			}
			coverage = coverage + ":" + temp;
		}
	}
	
	selObj = document.getElementById('contributedSubDistirct');
	if(selObj!=null){
	for (i = 0; i < selObj.options.length; i++) {
		id = selObj.options[i].value;
		temp = '';
		if (id.indexOf('FULL') > -1) {
			id = id.substring(0, id.length - 4);
			temp = id + ";" + 'T' + ";" + 'F' + ";" + 0;

		} else {
			id = id.substring(0, id.length - 4);
			temp = id + ";" + 'T' + ";" + 'P' + ";" + 0;
		}
		coverage = coverage + ":" + temp;
	}
	}
	
	selObj = document.getElementById('villageListContributed');
	if(selObj!=null){
		for (i = 0; i < selObj.options.length; i++) {
			id = selObj.options[i].value;
			temp = '';
			id = id.substring(0, id.length - 4);
			temp = id + ";" + 'V' + ";" + 'F' + ";" + 0;
			coverage = coverage + ":" + temp;
		}
	}
	
	selObj = document.getElementById('contributedLBList');
	if(selObj!=null){
		for (i = 0; i < selObj.options.length; i++) {
			id = selObj.options[i].value;
			temp = '';
			if (id.indexOf('FULL') > -1) {
				id = id.substring(0, id.length - 4);
				temp = id + ";" + 'G' + ";" + 'F' + ";" + 0;

			} else {
				id = id.substring(0, id.length - 4);
				temp = id + ";" + 'G' + ";" + 'P' + ";" + 0;
			}
			coverage = coverage + ":" + temp;
		}
	}
	
	selObj = document.getElementById('contributedGP');
	if(selObj!=null){
		for (i = 0; i < selObj.options.length; i++) {
			id = selObj.options[i].value;
			subDistId = selObj.options[i].getAttribute("paramCategory");
			temp = '';
			if (id.indexOf('FULL') > -1) {
				id = id.substring(0, id.length - 4);
				temp = id + ";" + 'G' + ";" + 'F' + ";" + subDistId;

			} else {
				id = id.substring(0, id.length - 4);
				temp = id + ";" + 'G' + ";" + 'P' + ";" + subDistId;
			}
			coverage = coverage + ":" + temp;
		}	
	}
	
	selObj = document.getElementById('gpVillageListContributed');
	if(selObj!=null){
		for (i = 0; i < selObj.options.length; i++) {
			id = selObj.options[i].value;
			GpId = selObj.options[i].getAttribute("paramCategory");
			temp = '';
			id = id.substring(0, id.length - 4);
			temp = id + ";" + 'V' + ";" + 'F' + ";" + GpId;
			coverage = coverage + ":" + temp;
		}
	}
	
	coverage = coverage.substring(1, coverage.length);
	document.getElementById('adminCoverage').value = coverage;
	coveragelength = coverage.length;
	var tempObj = '';
	selObj = document.getElementById('contributedWardList');
	tempObj = document.getElementById('contributedLBList');
	var wardname = '';
	var wardsel = '';
	coverage = '';
	if(selObj!=null && tempObj!=null){
		for (i = 0; i < selObj.options.length; i++) {
			wardsel = selObj.options[i].text;
			for (var j = 0; j < tempObj.options.length; j++) {
				wardname = tempObj.options[j].text;
				if (wardname.indexOf('PART') > -1) {
					temp = '';
					wardname = wardname.substring(0, wardname.length - 6);
					/* Modified by pooja on 06-08-2015 */
					if ((selObj.options[i].getAttribute('paramCategory')) == (tempObj.options[j].value
							.substring(0, tempObj.options[j].value.length - 4))) {
						wardname = tempObj.options[j].value.substring(0,
								tempObj.options[j].value.length - 4);
						wardsel = selObj.options[i].value.substring(0,
								selObj.options[i].value.length - 4);
						temp = wardname + ";" + wardsel;
						coverage = coverage + ":" + temp;
						break;
					}
				}
			}
		}
	}
	coverage = coverage.substring(1, coverage.length);
	document.getElementById('wardCoverage').value = coverage;
	coveragelength = coveragelength + coverage.length;
	if (coveragelength > 4) {
		return false;
	} else {
		alert("Please Select Coverage Area");
		coverageModify = '0';
		return true;
	}
}
function emptyLBList() {
	$("#SourceLBList").empty();
	$("#contributedLBList").empty();
	$("#souceWardList").empty();
	$("#contributedWardList").empty();
	$('#contributedLBTypeList option').clone().appendTo('#lbTypeList');
    removeAllOptions('contributedLBTypeList');
    sortListBox('contributedLBTypeList');
    
	
}

/* Modified by pooja on 21-10-2015 */
function emptyLRList() {
	if (document.getElementById("stateCode").value == 0){
		removeAllList('contributedState', 'SourceState', true, 5);
		sortListBox('SourceState');
	}
	else{
		removeAllList('contributedDistirct', 'SourceDistrict', true, 2);
		sortListBox('SourceDistrict');
	}
	
}

function getParentLevelEntity() {
	document.getElementById("UniqueDeptAdminError").innerHTML = "";
	document.getElementById("parentAdminUnitLevel").innerHTML = "";
	var slc = document.getElementById("stateCode").value;
	var distCode = document.getElementById("districtCode").value;
	var unitCode = document.getElementById("unitLevelCode").value;

	document.getElementById('coverageExistfalse').checked = true;
	document.getElementById('coveragediv').style.display = 'none';
	document.getElementById('LBRegion').style.display = 'none';
	document.getElementById('LRRegion').style.display = 'none';
	$("#SourceLBList").empty();
	$("#contributedLBList").empty();
	$("#souceWardList").empty();
	$("#contributedWardList").empty();
	$("#contributedDistirct").empty();
	$("#sourceSubDistrict").empty();
	$("#contributedSubDistirct").empty();
	$("#souceVilalgeList").empty();
	$("#villageListContributed").empty();
	$("#sourceGP").empty();
	$("#contributedGP").empty();
	$("#sourceGpVillageList").empty();
	$("#gpVillageListContributed").empty();
	$("#covdiv").show();
	
	
	/* Modified by pooja on 20-10-2015 */
	lgdDwrOrganizationService.getParentAdmnUnitLvlCode(unitCode, {
		callback : function(data) {
			if(data==-100){
				$("#covdiv").hide();
			}
			if (slc == 0 && data >= 2 && data <= 5) {
				$('#stateId').val(0).attr("selected", "selected");
				$("#parentUnit").empty();
				document.getElementById('statePopup').style.display = 'block';
			} else {
				document.getElementById('statePopup').style.display = 'none';
				if (distCode != null && distCode > 0) {
					lgdDwrOrganizationService
							.getUnitLevelNamesForLocalBodyDistrictUser(
									unitCode, distCode, {
										callback : insertParentAdminEntity,
										errorHandler : handleAdminError
									});
				} else {
					lgdDwrOrganizationService.getUnitLevelNamesForLocalBody(
							unitCode, slc, {
								callback : insertParentAdminEntity,
								errorHandler : handleAdminError
							});
				}
			}
		}
	});
}
function getParentLvlEntity(slc) {

	document.getElementById("err_state").innerHTML = "";
	document.getElementById("UniqueDeptAdminError").innerHTML = "";
	document.getElementById("parentAdminUnitLevel").innerHTML = "";
	var unitCode = document.getElementById("unitLevelCode").value;
	dwr.util.removeAllOptions('parentUnit');
	document.getElementById('coverageExistfalse').checked = true;
	document.getElementById('coveragediv').style.display = 'none';
	document.getElementById('LBRegion').style.display = 'none';
	document.getElementById('LRRegion').style.display = 'none';
	$("#SourceLBList").empty();
	$("#contributedLBList").empty();
	$("#souceWardList").empty();
	$("#contributedWardList").empty();
	$("#contributedDistirct").empty();
	$("#sourceSubDistrict").empty();
	$("#contributedSubDistirct").empty();
	$("#souceVilalgeList").empty();
	$("#villageListContributed").empty();
	$("#sourceGP").empty();
	$("#contributedGP").empty();
	$("#sourceGpVillageList").empty();
	$("#gpVillageListContributed").empty();
	if (slc == 0) {
		document.getElementById("err_state").innerHTML = "Please Select State.";
		document.getElementById("stateId").focus();
	} else {
		lgdDwrOrganizationService.getUnitLevelNamesForLocalBody(unitCode, slc,
				{
					callback : insertParentAdminEntity,
					errorHandler : handleAdminError
				});
	}
}
function insertParentAdminEntity(data) {
	var levelName = '';
	dwr.util.removeAllOptions('parentUnit');
	var st = '';
	if (data.length > 1) {
		st = document.getElementById('parentUnit');
		st.add(new Option('--- Select --', '-1'));
	}
	var fieldId = 'parentUnit';
	var valueText = 'parentAdminCode';
	var nameText = 'adminLevelNameEng';

	var options = $("#parentUnit");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.parentAdminCode).text(
					obj.adminLevelNameEng + " (" + obj.parentAdminCode + ")");
			options.append(option);
		} else {
			$(option).val(obj.parentAdminCode).text(
					obj.adminLevelNameEng + " (" + obj.parentAdminCode + ")");
			options.append(option);
		}
	});

	// dwr.util.addOptions(fieldId, data, valueText, nameText);
	if (data.length == 0) {
		levelName = $("#unitLevelCode option:selected").html();
		customAlert("There is no parent entity defined for selected administrative unit level.");

	}
	if (data.length > 0)
		checkEntityNameexists();

}

/*
 * function resetAdminEntityName() {
 * document.getElementById("adminLevelNameEng").value = "";
 *  }
 */
/*modified by deepti on 13-05-2016*/
function validateGetEntityByLevel() {
	var overlappValue = $("#overlappValue").val();
	var index = document.getElementById('unitLevelCode').selectedIndex;
	var index1 = document.getElementById('parentUnit').selectedIndex;
	if (index == 0) {
		document.getElementById("parentAdminUnitLevel").innerHTML = "<font color='red'>Please Select Parent Administrative Unit Level</font>";
		return false;
	}
	/*if (overlappValue == 'Y') {
		if (index1 == 0) {
			document.getElementById("parentAdminUnit").innerHTML = "<font color='red'>Please Select Parent level Administrative Unit Level</font>";
			return false;
		}
	}*/
	document.getElementById('form1').action = 'manage_AdminEntiy_byParent.htm';
	document.getElementById('form1').submit();
}

function getUnitLevelEntity() {
	document.getElementById("parentAdminUnitLevel").innerHTML = "";
	var slc = document.getElementById("stateCode").value;
	var unitCode = document.getElementById("unitLevelCode").value;
	var distCode = document.getElementById("districtCode").value;

	/* Modified by pooja on 20-10-2015 */
	lgdDwrOrganizationService.getParentAdmnUnitLvlCode(unitCode, {
		callback : function(data) {
			if (slc == 0 && data >= 2 && data <= 5) {
				$('#stateId').val(0).attr("selected", "selected");
				$("#parentUnit").empty();
				document.getElementById('statePopup').style.display = 'block';
			} else {
				document.getElementById('statePopup').style.display = 'none';
				if (distCode != null && distCode > 0) {
					lgdDwrOrganizationService
							.getUnitLevelNamesForLocalBodyDistrictUser(
									unitCode, distCode, {
										callback : insertParentAdminEntity,
										errorHandler : handleAdminError
									});
				} else {
					lgdDwrOrganizationService.getUnitLevelNamesForLocalBody(
							unitCode, slc, {
								callback : insertParentAdminEntity,
								errorHandler : handleAdminError
							});
				}
			}
		}
	});
}
function getUnitParentLvlEntity(slc) {

	document.getElementById("err_state").innerHTML = "";
	document.getElementById("parentAdminUnitLevel").innerHTML = "";
	var unitCode = document.getElementById("unitLevelCode").value;
	dwr.util.removeAllOptions('parentUnit');
	if (slc == 0) {
		document.getElementById("err_state").innerHTML = "Please Select State.";
		document.getElementById("stateId").focus();
	} else {
		lgdDwrOrganizationService.getUnitLevelNamesForLocalBody(unitCode, slc,
				{
					callback : insertParentAdminEntity,
					errorHandler : handleAdminError
				});
	}
}
function checkEntityNameexists() {
	if(document.getElementById("adminLevelNameEng")!=null){
		var val = document.getElementById("adminLevelNameEng").value;
		if (val.length > 0) {
			validatename(val, 'E');
		}
	}
	
}
function insertParentAdminEntitySearch(data) {
	var levelName = '';
	dwr.util.removeAllOptions('parentUnit');
	var st = '';
	if (data.length > 1) {
		st = document.getElementById('parentUnit');
		st.add(new Option('--- Select --', '0'));
	}
	var fieldId = 'parentUnit';
	var valueText = 'parentAdminCode';
	var nameText = 'adminLevelNameEng';

	var options = $("#parentUnit");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.parentAdminCode).text(obj.adminLevelNameEng);
			options.append(option);
		} else {
			$(option).val(obj.parentAdminCode).text(obj.adminLevelNameEng);
			options.append(option);
		}
	});

	// dwr.util.addOptions(fieldId, data, valueText, nameText);
	if (data.length == 0) {
		levelName = $("#unitLevelCode option:selected").html();
		customAlert("No Administrative Unit Entity is defined for Administrative unit Level - "
				+ levelName);

	}

}
/* Modified by pooja for Manage Coverage Entity */
function modifyCoverageOptions(coverageEntityTypeList, status,val) {
	//alert("coverageEntityTypeList"+coverageEntityTypeList+"@status"+status+"@val"+val);
	if (val == 'N') {
		getAddedCoverage();
	}
	document.getElementById('coveragediv').style.visibility = 'visible';
	document.getElementById('coveragediv').style.display = 'block';
	if (coverageEntityTypeList != null) {
		var coverageEntityTypes = coverageEntityTypeList.split(",");
		var coverageEntityType = "";
		var coverageEntityCode = "";
		for (var i = 0; i < coverageEntityTypes.length; i++) {
			var coverageEntity = coverageEntityTypes[i].split(":");
			coverageEntityType = coverageEntityType + coverageEntity[0] + ",";
			coverageEntityCode = coverageEntityCode + coverageEntity[1] + ",";
		}
		var arrayCoverageType = (coverageEntityType.substring(0,
				coverageEntityType.length - 1)).split(",");
		var arrayLbCode = (coverageEntityCode.substring(0,
				coverageEntityCode.length - 1)).split(",");
		for (var i = 0; i < arrayCoverageType.length; i++) {
			if ((arrayCoverageType[i] == 'G' && arrayLbCode[i] == 'null')
					|| arrayCoverageType[i] == 'W') {
				document.getElementById('existingLB').checked = true;
				toggleLBCovergeDiv();
			}
			if (arrayCoverageType[i] == 'S'
					|| arrayCoverageType[i] == 'D'
					|| arrayCoverageType[i] == 'T'
					|| arrayCoverageType[i] == 'V'
					|| (arrayCoverageType[i] == 'G' && arrayLbCode[i] != 'null')) {
				document.getElementById('existingLR').checked = true;
				toggleLRCovergeDiv();
			}
		}
		if (document.getElementById("stateCode").value == 0) {
			document.getElementById('existingLB').disabled = true;
			lgdDwrStateService.getStateListWithOperationState({
				callback : getStates,
				errorHandler : addedCoveredAreaError
			});
		} else {
			if (document.getElementById('existingLB').checked) {
				var adminUnitCode = document.getElementById('adminUnitCode').value;
				var slc = document.getElementById("stateCode").value;
				lgdDwrOrganizationService.getLocalBobyTypeListByAdminUnitCode(
						adminUnitCode, slc, status, {
							callback : getLocalBodyType,
							errorHandler : addedCoveredAreaError
						});
			}
			if (document.getElementById('existingLR').checked) {
				var slc = document.getElementById("stateCode").value;
				lgdDwrDistrictService.getDistrictListbyStateCodeForLocalBody(
						slc, {
							callback : getDistricts,
							errorHandler : addedCoveredAreaError
						});
			}
		}
	}
	var overlappVal = $("#overlappValue").val();
	//alert(overlappVal);
	if (overlappValue == 'N') {
		getAddedCoverage();
	}
		
}
/* Added by pooja on 21-10-2015 */
function getStates(data) {
	$("#SourceState").empty();
	var options = $("#SourceState");
	var options1 = $("#contributedState");
	$.each(
					data,
					function(key, obj) {
						var option = $("<option/>");
						if (obj.operation_state == 'F') {
							$(option).attr('disabled', 'disabled');
							$(option).val(obj.stateCode).text(
									obj.stateNameEnglish);
							options.append(option);
						} else {
							$(option).val(obj.stateCode).text(
									obj.stateNameEnglish);
							options.append(option);
						}
						for (var i = 0; i < lbEntityCodeStateValues.length; i++) {
							var option1 = $("<option/>");
							var lbEntityCodeStateValue = lbEntityCodeStateValues[i];
							var lbEntityCoverageTypeStateValue = lbEntityCoverageTypeStateValues[i];
							if (lbEntityCodeStateValue == obj.stateCode) {
								$(option1)
										.val(
												lbEntityCodeStateValue
														+ lbEntityCoverageTypeStateValue)
										.text(
												obj.stateNameEnglish
														+ ' ('
														+ lbEntityCoverageTypeStateValue
														+ ')');
								options1.append(option1);
							}
						}
					});
	if (data.length > 0)
		removeAddedCoverage('S');

	var sourceState = document.getElementById('SourceState');
	var contributedState = document.getElementById('contributedState');
	for (var i = 0; i < contributedState.options.length; i++) {
		for (var j = 0; j < sourceState.options.length; j++) {
			if (sourceState.options[j].value == contributedState.options[i].value
					.substring(0, contributedState.options[i].value.length - 4)) {
				sourceState.remove(j);
				break;
			}
		}
	}
	if (contributedState.options.length > 0)
		getDistrictList(null);
}
function getDistrictList(isExistOrNew) {
	$("#SourceDistrict").empty();
	$("#contributedDistirct").empty();
	$("#sourceSubDistrict").empty();
	$("#contributedSubDistirct").empty();
	$("#souceVilalgeList").empty();
	$("#villageListContributed").empty();
	var selObj = document.getElementById('contributedState');
	var idValuePart = "";
	var count = 0;
	var str = "";
	for (var i = 0; i < selObj.options.length; i++) {
		str = selObj.options[i].value;
		if (str.indexOf('PART') > -1) {
			if (idValuePart == "") {
				idValuePart = selObj.options[i].value;
			} else {
				idValuePart = idValuePart + "," + selObj.options[i].value;
			}
			count++;
		}
	}
	if (isExistOrNew == 'N') {
		if (count == 0) {
			alert("Please Select Part State ");
			return false;
		}
	}
	if (idValuePart != "") {
		lgdDwrDistrictService.getDistrictListbyStates(idValuePart, {
			callback : getDistricts,
			errorHandler : handleError
		});
	}

}

function getDistricts(data) {
	$("#SourceDistrict").empty();
	var options = $("#SourceDistrict");
	var options1 = $("#contributedDistirct");
	$
			.each(
					data,
					function(key, obj) {
						var option = $("<option/>");
						if (obj.operation_state == 'F') {
							$(option).attr('disabled', 'disabled');
							if (document.getElementById("stateCode").value == 0)
								$(option).val(obj.districtCode).text(
										obj.districtNameEnglish + ' [ State : '
												+ obj.stateNameEnglish + ' ] ');
							else
								$(option).val(obj.districtCode).text(
										obj.districtNameEnglish);
							options.append(option);
						} else {
							if (document.getElementById("stateCode").value == 0)
								$(option).val(obj.districtCode).text(
										obj.districtNameEnglish + ' [ State : '
												+ obj.stateNameEnglish + ' ] ');
							else
								$(option).val(obj.districtCode).text(
										obj.districtNameEnglish);
							options.append(option);
						}
						for (var i = 0; i < lbEntityCodeDistValues.length; i++) {
							var option1 = $("<option/>");
							var lbEntityCodeDistValue = lbEntityCodeDistValues[i];
							var lbEntityCoverageTypeDistValue = lbEntityCoverageTypeDistValues[i];
							if (lbEntityCodeDistValue == obj.districtCode) {
								if (document.getElementById("stateCode").value == 0)
									$(option1)
											.val(
													lbEntityCodeDistValue
															+ lbEntityCoverageTypeDistValue)
											.text(
													obj.districtNameEnglish
															+ ' [ State : '
															+ obj.stateNameEnglish
															+ ' ] '
															+ ' ('
															+ lbEntityCoverageTypeDistValue
															+ ')');
								else
									$(option1)
											.val(
													lbEntityCodeDistValue
															+ lbEntityCoverageTypeDistValue)
											.text(
													obj.districtNameEnglish
															+ ' ('
															+ lbEntityCoverageTypeDistValue
															+ ')');
								options1.append(option1);
							}
						}
					});
	if (data.length > 0)
		removeAddedCoverage('D');

	var sourceDistrict = document.getElementById('SourceDistrict');
	var contributedDistrict = document.getElementById('contributedDistirct');
	for (var i = 0; i < contributedDistrict.options.length; i++) {
		for (var j = 0; j < sourceDistrict.options.length; j++) {
			if (sourceDistrict.options[j].value == contributedDistrict.options[i].value
					.substring(0,
							contributedDistrict.options[i].value.length - 4)) {
				sourceDistrict.remove(j);
				break;
			}
		}
	}
	if (contributedDistrict.options.length > 0)
		getSubdistrictsList(null);
}

function getLocalBodyType(data) {
	//alert("data-->"+data);
	var lbtypeCodes=[];
	for (var i = 0; i < data.length; i++) {
		if (data[i] != null) {
			lbtypeCodes.push(data[i]);
		}
	}
	var stateCode=$('#stateCode ').val();
	
	getUrbanListbylocalbodyTypeList(lbtypeCodes.toString(),stateCode);
	//alert(stateCode);
	//alert(lbtypeCodes);
	/*$("#ulbID").val(data[i]);
	getUrbanlocabodies(data[i]);*/
}
function getAddedCoverage() {
	var entityCode = document.getElementById('unitLevelCode').value;
	var slc = document.getElementById("stateCode").value;
	lgdDwrOrganizationService.adminEntityCoveredArea(entityCode, slc, 'F', {
		callback : addedCoveredArea,
		errorHandler : addedCoveredAreaError
	});
}
function addedCoveredArea(data) {
	districtCoverage = '';
	subDistrictCoverage = '';
	villageCoverage = '';
	lbCoverage = '';
	wardCoverage = '';
	gpCoverage = '';
	gpVillageCoverage = '';
	stateCoverage = '';
	for (var i = 0; i < data.length; i++) {
		if (data[i].entiyType == 'D')
			districtCoverage = data[i].entityCode + ":" + districtCoverage;
		else if (data[i].entiyType == 'T')
			subDistrictCoverage = data[i].entityCode + ":"
					+ subDistrictCoverage;
		else if (data[i].entiyType == 'V' && data[i].lbCodeForWard == null)
			villageCoverage = data[i].entityCode + ":" + villageCoverage;
		else if (data[i].entiyType == 'G' && data[i].lbCodeForWard == null)
			lbCoverage = data[i].entityCode + ":" + lbCoverage;
		else if (data[i].entiyType == 'W')
			wardCoverage = data[i].entityCode + ":" + wardCoverage;
		else if (data[i].entityType == 'G' && data[i].lbCodeForWard != null)
			gpCoverage = data[i].entityCode + ":" + gpCoverage;
		else if (data[i].entityType == 'V' && data[i].lbCodeForWard != null)
			gpVillageCoverage = data[i].entityCode + ":" + gpVillageCoverage;
		else if (data[i].entityType == 'S')
			stateCoverage = data[i].entityCode + ":" + stateCoverage;
	}

}
function removeAddedCoverage(type) {
	var coverage = new Array();
	var selObj = '';
	var temp = '';
	if (type == 'G') {
		selObj = document.getElementById('SourceLBList');
		coverage = lbCoverage.split(":");
	} else if (type == 'W') {
		selObj = document.getElementById('souceWardList');
		coverage = wardCoverage.split(":");
	} else if (type == 'D') {
		selObj = document.getElementById('SourceDistrict');
		coverage = districtCoverage.split(":");
	} else if (type == 'T') {
		selObj = document.getElementById('sourceSubDistrict');
		coverage = subDistrictCoverage.split(":");
	} else if (type == 'V') {
		selObj = document.getElementById('souceVilalgeList');
		coverage = villageCoverage.split(":");
	} else if (type == 'GP') {// GP used for Gram Panchayat
		selObj = document.getElementById('sourceGP');
		coverage = gpCoverage.split(":");
	} else if (type == 'GV') {// GV used for Gram Panchayat Village
		selObj = document.getElementById('sourceGpVillageList');
		coverage = gpVillageCoverage.split(":");
	} else if (type == 'S') {
		selObj = document.getElementById('SourceState');
		coverage = stateCoverage.split(":");
	}
	for (var i = 0; i < coverage.length; i++) {
		temp = coverage[i];
		for (var j = 0; j < selObj.options.length; j++) {
			if (selObj.options[j].value == temp) {
				selObj.remove(j);
				break;
			}
		}
	}
}
function addedCoveredAreaError() {
	// alert(data.length);
}
function s() {

	var i = 0;
	for (i = 0; i < covValues.length; i++) {
		if (covValues[i] == val)
			;
		break;
	}
	for (; i < covValues.length - 1; i++) {
		covValues[i] = covValues[i + 1];
	}
}
/*
 * function deleteAddedCoverege(val) { // var index = covValues.indexOf(val); //
 * covValues.splice(index, 1); var i = 0; var index = 0; for (i = 0; i <
 * covValues.length; i++) { if (covValues[i] == val) { index = i + 1; break; } }
 * for (; i < covValues.length - 1; i++) { covValues[i] = covValues[i + 1]; }
 * document.getElementById("coverageTable").deleteRow(index); deletedCoverage =
 * val + "," + deletedCoverage; i = 0; $('#coverageTable tr').each(function() {
 * if (i > 0) { $(this).find("td:first").text(i); } i++; }); }
 */
function ValSubmitChangeCoverageEntity() {
	var status = true;
	if (selectedCoverageArea()) {
		status = false;
	}
	if (status) {
		$('input[name=Submit]').prop('disabled', true);
		document.getElementById('form1').action = 'saveModifyCoverage.htm';
		document.getElementById('form1').submit();

	}
}

function getPartAddedCoverage() {
	var entityCode = document.getElementById('unitLevelCode').value;
	var slc = document.getElementById("stateCode").value;
	lgdDwrOrganizationService.adminEntityCoveredArea(entityCode, slc, 'P', {
		callback : addedPartCoveredArea,
		errorHandler : addedCoveredAreaError
	});
}
function addedPartCoveredArea(data) {
	districtCoverage = '';
	subDistrictCoverage = '';
	villageCoverage = '';
	lbCoverage = '';
	wardCoverage = '';
	gpCoverage = '';
	gpVillageCoverage = '';
	for (var i = 0; i < data.length; i++) {
		if (data[i].entiyType == 'D')
			distPartCoverage = data[i].entityCode + ":" + distPartCoverage;
		else if (data[i].entiyType == 'T')
			subPartCoverage = data[i].entityCode + ":" + subPartCoverage;
		else if (data[i].entiyType == 'G' && data[i].lbCodeForWard == null)
			lbPartCoverage = data[i].entityCode + ":" + lbPartCoverage;
		else if (data[i].entiyType == 'G' && data[i].lbCodeForWard != null)
			gpPartCoverage = data[i].entityCode + ":" + gpPartCoverage;
		else if (data[i].entiyType == 'S')
			stPartCoverage = data[i].entityCode + ":" + stPartCoverage;
	}
	distPartCoverage = ':' + distPartCoverage;
	subPartCoverage = ':' + subPartCoverage;
	lbPartCoverage = ':' + lbPartCoverage;
	gpPartCoverage = ':' + gpPartCoverage;
	stPartCoverage = ':' + stPartCoverage;

}

function addItemPartCheck(list1, list2, val, type) {
	var overlappValue = $("#overlappValue").val();
	var coverage = '';
	if (type == 'G')
		coverage = lbPartCoverage;
	else if (type == 'D')
		coverage = distPartCoverage;
	else if (type == 'T')
		coverage = subPartCoverage;
	else if (type == 'GP')
		coverage = gpPartCoverage;
	else if (type == 'S')
		coverage = stPartCoverage;
	partCoverage = '';
	var temp = '';
	if (document.getElementById(list2).selectedIndex >= 0) {
		for (var j = 0; j < document.getElementById(list2).options.length; j++)
			if (document.getElementById(list2).options[j].selected == true) {
				temp = ':' + document.getElementById(list2)[j].value + ':';
				if (coverage.indexOf(temp) == -1 || overlappValue == 'Y' ) {
					$('#' + list1).append(
							"<option paramCategory="
									+ document.getElementById(list2)[j]
											.getAttribute('paramCategory')
									+ " value="
									+ document.getElementById(list2)[j].value
									+ val + ">"
									+ document.getElementById(list2)[j].text
									+ " (" + val + ")</option>");
				} else {
					document.getElementById(list2).options[j].selected = false;
					partCoverage = partCoverage + ", "
							+ document.getElementById(list2)[j].text;
				}

			}
		removeSelectedValue(list2);
	}
	
	if (partCoverage.length > 0 && overlappValue == 'N' ) {
		partCoverage = partCoverage.substring(2, partCoverage.length);
		customAlert("Coverage " + partCoverage	+ " in Use, Please Select as Part");
	}
}

function checkmodifyEntityName() {
	var entityType = 'E';
	var entityvalue = document.getElementById('adminLevelNameEng').value;
	if (entityvalue.length > 0) {
		validatename(entityvalue, entityType);
	}
}
function resetAdminParentIndex() {
	document.getElementById('parentUnit').selectedIndex = 0;
}



function ValidateAndSubmitforupdateForDraft() {
	if (minimumChangesformodifyAdminUnit()) {

		var obj = document.getElementById('adminLevelNameEng').value;
		var index = document.getElementById('parentUnit').selectedIndex;
		var status = true;
		if (obj.length == 0) {
			status = false;
			document.getElementById("UniqueDeptAdminError").innerHTML = "<font color='red'>Please Enter Administrative Unit Level (In English)</font>";

		} else if (index == 0) {
			status = false;
			document.getElementById("parentAdminUnit").innerHTML = "<font color='red'>Please Select Parent Administrative Unit Level </font>";

		}
		if (status) {
			document.getElementById('buttonClicked').value = 'D';
			$('input[name=Submit]').prop('disabled', true);
			$('input[name=Publish]').prop('disabled', true);
			document.getElementById('form1').action = 'modifyDeptAdmitUnitDraft.htm';
			document.getElementById('form1').submit();

		}
	} else
		alert("Atleast One Change is Required for Modification");
}
function ValidateAndSubmitforupdateForDraftPublish() {
	// if (minimumChangesformodifyAdminUnit()) {

	var obj = document.getElementById('adminLevelNameEng').value;
	var index = document.getElementById('parentUnit').selectedIndex;
	var status = true;
	if (obj.length == 0) {
		status = false;
		document.getElementById("UniqueDeptAdminError").innerHTML = "<font color='red'>Please Enter Administrative Unit Level (In English)</font>";

	} else if (index == 0) {
		status = false;
		document.getElementById("parentAdminUnit").innerHTML = "<font color='red'>Please Select Parent Administrative Unit Level </font>";

	}
	if (status) {
		document.getElementById('buttonClicked').value = 'P';
		$('input[name=Submit]').prop('disabled', true);
		$('input[name=Publish]').prop('disabled', true);
		document.getElementById('form1').action = 'modifyDeptAdmitUnitDraft.htm';
		document.getElementById('form1').submit();

	}
	/*
	 * } else alert("Atleast One Change is Required for Modification");
	 */
}

function validateGetEntityByLevelForDraft() {
	var index = document.getElementById('unitLevelCode').selectedIndex;
	if (index == 0) {
		document.getElementById("parentAdminUnitLevel").innerHTML = "<font color='red'>Please Select Parent Administrative Unit Level</font>";
		return false;
	}
	document.getElementById('form1').action = 'manage_AdminEntiy_byParentForDraft.htm';
	document.getElementById('form1').submit();
}

function publishAdminEntity(entityCode) {
	lgdDwrOrganizationService.publishAdminEntity(entityCode, {
		callback : publishAdminEntityCallBack,
		errorHandler : addedCoveredAreaError,
		arg : entityCode

	});

}

function publishAdminEntityCallBack(data, entityCode) {

	if (data) {
		alert("Admin unit Entity Publish successfully from Draft");
		// $('input[name=Submit]').prop('disabled', true);
		// document.getElementById('form1').action =
		// 'manage_AdminEntiy_byParentForDraft.htm';
		// document.getElementById('form1').submit();
		document.forms['form1'].method = "get";
		document.forms['form1'].action = "home.htm?<csrf:token uri='home.htm'/>";
		document.forms['form1'].submit();

	} else {
		alert("error occured");
	}
}

function ValidateAndSubmitforEntityupdateForDraft() {
	if (minimumChangesformodifyAdminEntity()) {
		var obj = document.getElementById('adminLevelNameEng').value;
		var index = document.getElementById('unitLevelCode').selectedIndex;
		var parentNewIndex = document.getElementById('parentUnit').value;
		var parentoldIndex = document.getElementById('parentAdminCode').value;
		var status = true;
		if (obj.length == 0) {
			status = false;
			document.getElementById("UniqueDeptAdminError").innerHTML = "<font color='red'>Please Enter Adminstration Unit Entity (In English)</font>";

		} else if (index == 0) {
			status = false;
			document.getElementById("parentAdminUnit").innerHTML = "<font color='red'>Please Select Administrative unit Level </font>";

		} else if (parentoldIndex > 0) {
			if (parentNewIndex == 0) {
				status = false;
				document.getElementById("parentAdminUnitforEntity").innerHTML = "<font color='red'>Please Select Parent Administrative Unit Level </font>";
			}

		}

		if (status) {
			$('input[name=Submit]').prop('disabled', true);
			document.getElementById('form1').action = 'modifyDeptAdmitEntityForDraft.htm';
			document.getElementById('form1').submit();

		}
	} else
		alert("Atleast One Change is Required for Modification");
}

function ValSubmitChangeCoverageEntityForDraft() {
	var status = true;
	if (selectedCoverageArea()) {
		status = false;
	}
	if (status) {
		$('input[name=Submit]').prop('disabled', true);
		document.getElementById('form1').action = 'saveModifyCoverageForDraft.htm';
		document.getElementById('form1').submit();

	}
}

/*added by deepti on 11-05-2016*/
function getOverlappingValue(obj) {
	var overlapval = obj.value;
	lgdDwrOrganizationService.getOverlappingExist(overlapval, {
		callback : function(data) {
			$("#overlappValue").val(data);
		}
	});
}


 function populateDistrictList(){
		var slc = document.getElementById("stateCode").value;
		lgdDwrDistrictService.getDistrictListbyStateCodeForLocalBody(slc,{
				callback : populateDistrictListCallBack
			});	
	}

	
 function populateDistrictListCallBack(data) {
	dwr.util.removeAllOptions('SourceDistrict');
	var options = $("#SourceDistrict");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F'.charAt(0)) {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.districtCode).text(obj.districtNameEnglish);
			options.append(option);
		} else if (obj.operation_state == 'A'.charAt(0)) {
			$(option).val(obj.districtCode).text(obj.districtNameEnglish);
			options.append(option);
		}
	});
}
 
 
 /**
  * The {@code moveElement} function used move items from one listbox to another,
  * based on copyid,pasteId and action(parameter)
  */
 moveElement=function(action){
 	var copyId=null;
 	var pasteId=null;
 	if(action=="FORWARD" ||action=="FORWARDALL" ){
 		copyId='lbTypeList';
 		pasteId='contributedLBTypeList';
 	}else if(action=="BACK" ||action=="BACKALL" ){
 		copyId='contributedLBTypeList';
 		pasteId='lbTypeList'; 
 		clearAllListbox();
 		
 	}
 	
 	if(action=="FORWARDALL" ||action=="BACKALL" ){
 		$('#'+copyId+' option').clone().appendTo('#'+pasteId);
 		removeAllOptions(copyId);
 	}else if(action=="FORWARD" ||action=="BACK" ){
 		$('#'+copyId+'> option:selected').appendTo('#'+pasteId);
 	}
 	sortListBox(copyId);
 	sortListBox(pasteId);
 	
 	
 };
 
 
 /**
  * The {@code sortListBox} function used sort items of listbox ,
  * based on listbox id
  */
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
 
 /* The {@code removeAllOptions} used to clear drop down box based on their id. 
 */
 removeAllOptions=function(id){
 	dwr.util.removeAllOptions(id);
 };
 
 
 getLbList=function(){
	 var selLbTypeArray = [];
		$('#contributedLBTypeList option').each(function() { 
			selLbTypeArray.push($(this).val());
			});
		var selLbTypeCodes=selLbTypeArray.toString();
		var stateCode=$('#stateCode ').val();
		clearAllListbox();
		getUrbanListbylocalbodyTypeList(selLbTypeCodes,stateCode);
 }
		 
 getUrbanListbylocalbodyTypeList=function(selLbTypeCodes,stateCode){
	
		removeAllOptions('SourceLBList');
		
		if(selLbTypeCodes!=""){
			lgdDwrlocalBodyService.getUrbanListbyLocalbodyTypeList(selLbTypeCodes,parseInt(stateCode), {
				callback : function(data) {
					
					var options = $("#SourceLBList");
					var options1 = $("#contributedLBList");
					var _text="";
					$.each(data, function(key, obj) {
						var option = $("<option/>");
						_text=obj.localBodyNameEnglish+"("+obj.localBodyTypeName+")"
						if (obj.operation_state == 'F' || _text.indexOf("DISTURBED") >= 0 ) {
							$(option).attr('disabled', 'disabled');
							$(option).val(obj.localBodyCode).text(_text);
							options.append(option);
						} else {
							$(option).val(obj.localBodyCode).text(_text);
							options.append(option);
						}
						for (var i = 0; i < lbEntityCodeValues.length; i++) {
							var option1 = $("<option/>");
							var lbEntityCodeValue = lbEntityCodeValues[i];
							var lbEntityCoverageTypeValue = lbEntityCoverageTypeValues[i];
							if (lbEntityCodeValue == obj.localBodyCode) {
								$(option1).val(lbEntityCodeValue + lbEntityCoverageTypeValue)
										.text(
												obj.localBodyNameEnglish + ' ('
														+ lbEntityCoverageTypeValue + ')');
								options1.append(option1);
							}
						}
						
					
							
					});
					
					if (data.length > 0){
						removeAddedCoverage('G');
					}
						
					
					var sourceLBList = document.getElementById("SourceLBList");
					var contributedLBList = document.getElementById("contributedLBList");
					for (var i = 0; i < contributedLBList.options.length; i++) {
						for (var j = 0; j < sourceLBList.options.length; j++) {
							if (sourceLBList.options[j].value == contributedLBList.options[i].value
									.substring(0, contributedLBList.options[i].value.length - 4)) {
								sourceLBList.remove(j);
								break;
							}
						}
					}
						if (contributedLBList.options.length > 0){
							
							getWardList(null);
							
						}
					
					
					
				},
				async : true
			});	
		}
		
 };
 
 clearAllListbox=function(){
	 	removeAllOptions('SourceLBList');
		removeAllOptions('contributedLBList');
		removeAllOptions('souceWardList');
		removeAllOptions('contributedWardList');
 };
 
 
 /**
  * The {@code sortListBox} function used sort items of listbox ,
  * based on listbox id
  */
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
 
 
 checkForCountryLevel=function(){
	
	 $("#overlapUnityes").attr("disabled", false);
	 parent= $("#parentUnit").val();
	 if(parent.indexOf("#")>0){
		 var type=parent.split("#")[1];
		 if(type=="C"){
			 $("#overlapUnityes").prop('checked', false);
			 $("#overlapUnitno").prop('checked', true);
			 $("#overlapUnityes").attr("disabled", true);
		 }
		
	 }
	
	 
 }
 