
function validate()
{
	var error = false;
	var errors = new Array();
	
	errors[0] = !validateState();
	errors[1] = !validateDistrict();
	for ( var i = 0; i < 2; i++)
	{
		
		if (errors[i] == true) {			
			error = true;
			break;
		}
	}

	if (error == true) {

		showClientSideError();
		return false;
	} else {
		return true;
	}
	return false;
		
}
function validateDistrict() {
	var ddSourceDistrict = document.getElementById('ddSourceDistrict');

	if (ddSourceDistrict.selectedIndex < 1) {		
		$("#ddSourceDistrict_error").show();
		return false;
	}
	$("#ddSourceDistrict_error").hide();
	return true;
}
function validateState() {
	var ddSourceState = document.getElementById('ddSourceState');

	if (ddSourceState.selectedIndex < 1) {			
		$("#ddSourceState_error").show();
		return false;
	}
	$("#ddSourceState_error").hide();
	return true;
}

function validateSubdistrict() {
	var ddSourceSubDistrict = document.getElementById('ddSourceSubDistrict');

	if (ddSourceSubDistrict.selectedIndex < 1) {			
		$("#ddSourceSubDistrict_error").show();
		return false;
	}
	$("#ddSourceSubDistrict_error").hide();
	return true;
}


function validateAllVillage()
{
	var error = false;
	var errors = new Array();
	
	errors[0] = !validateState();
	errors[1] = !validateDistrict();
	errors[2] = !validateSubdistrict();
	for ( var i = 0; i < 3; i++)
	{
		
		if (errors[i] == true) {			
			error = true;
			break;
		}
	}

	if (error == true) {

		showClientSideError();
		return false;
	} else {
		return true;
	}
	return false;	
}