function vaildateOrgTypeName() {
	if (document.getElementById('txtorgTypeName').value == "") {
		$("#txtorgTypeName_error").show();
		return false;
	}
	$("#txtorgTypeName_error").hide();
	return true;
}

// //////////////////////////////////////////////// alert message
// function////////////////////////////////////////////////////

function vaildateOrgTypeAll() {

	var errors = new Array();

	var error = false;

	errors[0] = vlidateOnblur('txtorgTypeName', '1', '35', 'm');

	if (errors[0] == true) {
		error = true;
	}

	if (error == true) {

		showClientSideError();

		return false;
	} else {
		return true;
	}
	return false;

}

function loadOrgTypePage() {
	$("#txtorgTypeName_error").hide();
}

function loadMinistryPage() {

	$("#txtMinistryName_error").hide();
}

function resetOrgTypeForm() {
	document.getElementById('txtorgTypeName').value = "";
}

function resetMinistryForm() {
	document.getElementById('txtMinistryName').value = "";
	document.getElementById('txtshortministryName').value = "";

}

// //////////////////

// //////////////////////////////////////////////// alert message
// function////////////////////////////////////////////////////

function validateMinistryName() {

	var errors = new Array();

	var error = false;

	errors[0] = vlidateOnblur('txtMinistryName', '1', '35', 'm');

	if (errors[0] == true) {
		error = true;
	}

	if (error == true) {
		showClientSideError();

		return false;
	} else {
		return true;
	}
	return false;
}