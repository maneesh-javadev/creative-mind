/*function validatePRIWardAll() {

	var msg = null;
	selectALLVillages();	

	if (!validateLBtypeName()) {
		if (msg != null) {
			msg = msg + "Please Select Local Body Type" + '\n';
		} else {
			msg = "Please Select Local Body Type" + '\n';
		}
		
	}
	
	if (!validateWardName()) {
		
		msg = "Please Enter Name of Ward" + '\n';
	

	}
    if (!validateWardNumber()) {
		
		msg = "Please Enter  Ward Number" + '\n';
   

	}
	
	

	
	var ddLBTypeCode= document.getElementById('ddLgdLBType').value;
	var temp = ddLBTypeCode.split(":");
	var id2 = temp[1];
	
	if (id2 == 'D') {
		if (!validateLBDistAtDist()) {
			if(msg != null) {
				
				msg = msg + "Please Select District Panchayat" + '\n';
				 
			} else {
				msg = "Please Select District Panchayat" + '\n';
			}
		}
		 
	
	}
	if (id2 == 'I') {
		if (!validateLBDistAtInter()) {
			if (msg != null) {
				msg = msg + "Please Select District Panchayat" + '\n';
			} else {
				msg = "Please Select District Panchayat" + '\n';
			}
		}
		if (!validateLBInterAtInter()) {
			if (msg != null) {
				msg = msg + "Please Select Intermediate Panchayat" + '\n';
			} else {
				msg = "Please Select Intermediate Panchayat" + '\n';
			}
		}
		
	}
   if(id2 == 'V'){
		
		if (!validateLBDistAtVillage()) {
			if (msg != null) {
				msg = msg + "Please Select District Panchayat" + '\n';
			} else {
				msg = "Please Select District Panchayat" + '\n';
			}
		}
		
		if(!validateLBInterAtVillage())
			{
			if (msg != null) {
				msg = msg + "Please Select Intermedaite Panchayat" + '\n';
			} else {
				msg = "Please Select Intermedaite Panchayat" + '\n';
			}
			}
		if (!validateLBVillAtVillage()) {
			if (msg != null) {
				msg = msg + "Please Select Village Panchayat" + '\n';
			} else {
				msg = "Please Select Village Panchayat" + '\n';
			}
		}
	}
	 
	if(msg !=null)
		{
		alert(msg);
		return false;
		}
	else
		{
		return true;
		}
	return false;
}
 */
function validatePRIWardAll() {
	var msg = null;
	/*by pooja on 08-07-2015*/
	//selectALLWard();
	var errors = new Array();
	var error = false;
	errors[0] = !validateLBtypeName();
	// added by kirandeep on 01/09/2014
	// modified by kirandeep
	// errors[1] = !validateWardName();
	// errors[2] = !validateWardNumber();

	var ddLBTypeCode = document.getElementById('ddLgdLBType').value;
	var temp = ddLBTypeCode.split(":");
	var id2 = temp[1];

	if (id2 == 'D') {

		errors[3] = !validateLBDistAtDist();
		// modified by kirandeep
		// errors[4] = !validateLBDistListAtDist();
	}
	if (id2 == 'I') {
		// errors[5] = !validateLBDistAtInter();
		errors[6] = !validateLBInterAtInter();
		// modified by kirandeep
		// errors[7] = !validateLBSubDistListAtInter();
	}

	if (id2 == 'V') {
		/*
		 * errors[8] = !validateLBDistAtVillage(); errors[9] =
		 * !validateLBInterAtVillage(); errors[10] = !validateLBVillAtVillage();
		 */
		errors[8] = validateLBVillageListAtVillage();

	}

	for ( var i = 0; i < 9; i++) {
		if (errors[i] == true) {
			error = true;
			break;
		}
	}

	if (error == true) {
		showClientSideError();
		return false;
	} else {
		showLoadingImage();
		// document.getElementById('btnSave').disabled = true;
		return true;
	}
	return false;
	
	
	var captchaAnswer=$("#captchaAnswer" );
		var captchaAnswer=$("#captchaAnswer" );
    	if( $_checkEmptyObject(captchaAnswer.val()) ){
    		$(captchaAnswer).addClass("error");
    		$( '#errEmptyCaptcha' ).text("Please Enter CAPTCHA in the textbox");
    		return false;
		}
    	else {
    		showLoadingImage();
    		// document.getElementById('btnSave').disabled = true;
    		return true;
	
}

showLoadingImage = function() {
	$.blockUI({
		theme : true,
		title : '<spring:message code="LGD.common.DWRProccessing"/>',
		message : "<div style='text-align: center'><img  src='<%= request.getContextPath() %>/images/ajax-loader-2.gif'/></div>"
	});
};
function validateWardName() {
	if (document.getElementById('wardname').value == "") {
		$("#wardname_error").show();
		return false;
	}
	$("#wardname_error").hide();
	return true;

}

function validateWardNumber() {

	if (document.getElementById('wardnumber').value == "") {
		$("#wardnumber_error").show();
		return false;
	}
	$("#wardnumber_error").hide();
	return true;

}

function validateLBlatitude() {
	if (document.getElementById('txtLgdLBlatitude').value == "") {
		$("#txtLgdLBlatitude_error").show();
		return false;
	}
	$("#txtLgdLBlatitude_error").hide();
	return true;

}

function validateLBlongitude() {
	if (document.getElementById('txtLgdLBlongitude').value == "") {
		$("#txtLgdLBlongitude_error").show();
		return false;
	}
	$("#txtLgdLBlongitude_error").hide();
	return true;

}

function validateUploadMap() {
	if (document.getElementById('txtLBfileMapUpLoad').value == "") {
		$("#txtLBfileMapUpLoad_error").show();
		return false;
	}
	$("#txtLBfileMapUpLoad_error").hide();
	return true;

}

function validateLBtypeName() {
	var ddLgdLBType = document.getElementById('ddLgdLBType');
	if (ddLgdLBType.selectedIndex == 0) {
		$("#ddLgdLBType_error").show();
		return false;
	}

	$("#ddLgdLBType_error").hide();
	return true;

}

function validateLBtypeNameUrbanWard() {
	var ddLgdLBType = document.getElementById('localBodyType');
	if (ddLgdLBType.selectedIndex == 0) {
		$("#localBodyType_error").show();
		return false;
	}

	$("#localBodyType_error").hide();
	return true;
}

function validateUrbanWardLBody() {
	var ddWardLB = document.getElementById('wardUrbanLocalBody');
	if (ddWardLB.selectedIndex == 0) {
		$("#wardUrbanLocalBody_error").show();
		return false;
	}

	$("#wardUrbanLocalBody_error").hide();
	return true;
}

function validateLBDistAtDist() {

	if (document.getElementById('tr_district1').style.display == 'block') {
		var ddlgdLBDistAtInter = document.getElementById('ddSourceLocalBody');
		if (ddlgdLBDistAtInter.selectedIndex == 0) {
			$("#ddSourceLocalBody_error").show();
			return false;
		} else if (ddlgdLBDistAtInter.selectedIndex == -1) {
			$("#ddSourceLocalBody_error").show();
			return false;
		}

	}
	$("#ddSourceLocalBody_error").hide();
	return true;

}

function validateLBDistListAtDist() {
	if (document.getElementById('tr_district1').style.display == 'block') {
		if (document.getElementById('ddLgdLBDistCAreaDestL').value == "") {
			$("#ddLgdLBDistCAreaDestL_error").show();
			return false;
		}
	}
	$("#ddLgdLBDistCAreaDestL_error").hide();
	return true;
}

function validateLBDistAtInter() {

	if (document.getElementById('tr_intermediate1').style.display == 'block') {
		var ddlgdLBDistAtInter = document.getElementById('ddLgdLBDistListAtInterCA');
		if (ddlgdLBDistAtInter.selectedIndex == 0) {
			$("#ddLgdLBDistListAtInterCA_error").show();
			return false;
		} else if (ddlgdLBDistAtInter.selectedIndex == -1) {
			$("#ddLgdLBDistListAtInterCA_error").show();
			return false;
		}

	}
	$("#ddLgdLBDistListAtInterCA_error").hide();
	return true;

}

function validateLBInterAtInter() {
	if (document.getElementById('tr_intermediate1').style.display == 'block') {
		var ddlgdLBDistAtInter = document.getElementById('ddLgdLBInterPSourceList');
		if (ddlgdLBDistAtInter.selectedIndex == 0) {
			$("#ddLgdLBInterPSourceList_error").show();
			return false;
		} else if (ddlgdLBDistAtInter.selectedIndex == -1) {
			$("#ddLgdLBInterPSourceList_error").show();
			return false;
		}

	}
	$("#ddLgdLBInterPSourceList_error").hide();
	return true;

}

function validateLBSubDistListAtInter() {
	if (document.getElementById('tr_intermediate1').style.display == 'block') {
		if (document.getElementById('ddLgdLBInterCAreaDestL').value == "") {
			$("#ddLgdLBInterCAreaDestL_error").show();
			return false;
		}
	}
	$("#ddLgdLBInterCAreaDestL_error").hide();
	return true;
}

function validateLBSubDistListAtInterUrban() {
	if (document.getElementById('ddLgdWardSubDistrictUListDest').value == "") {
		$("#ddLgdWardSubDistrictUListDest_error").show();
		return false;
	} else {
		return true;
	}
}

function validateLBDistAtVillage() {
	if (document.getElementById('tr_village').style.display == 'block') {
		var ddlgdLBDistAtInter = document.getElementById('ddLgdLBDistListAtVillageCA');
		if (ddlgdLBDistAtInter.selectedIndex == 0) {
			$("#ddLgdLBDistListAtVillageCA_error").show();
			return false;
		} else if (ddlgdLBDistAtInter.selectedIndex == -1) {
			$("#ddLgdLBDistListAtVillageCA_error").show();
			return false;
		}

	}
	$("#ddLgdLBDistListAtVillageCA_error").hide();
	return true;

}

function validateLBInterAtVillage() {
	if (document.getElementById('tr_village').style.display == 'block') {
		var ddlgdLBDistAtInter = document.getElementById('ddLgdLBInterListAtVillageCA');
		if (ddlgdLBDistAtInter.selectedIndex == 0) {
			$("#ddLgdLBInterListAtVillageCA_error").show();
			return false;
		} else if (ddlgdLBDistAtInter.selectedIndex == -1) {
			$("#ddLgdLBInterListAtVillageCA_error").show();
			return false;
		}

	}
	$("#ddLgdLBInterListAtVillageCA_error").hide();
	return true;

}

function validateLBVillAtVillage() {
	if (document.getElementById('tr_village').style.display == 'block') {
		var ddlgdLBDistAtInter = document.getElementById('ddLgdLBVillageSourceAtVillageCA');
		if (ddlgdLBDistAtInter.selectedIndex == 0) {
			$("#ddLgdLBVillageSourceAtVillageCA_error").show();
			return false;
		} else if (ddlgdLBDistAtInter.selectedIndex == -1) {
			$("#ddLgdLBVillageSourceAtVillageCA_error").show();
			return false;
		}

	}
	$("#ddLgdLBVillageSourceAtVillageCA_error").hide();
	return true;

}

// added by kirandeep on 01/09/2014
function validateLBVillageListAtVillage() {
	/*
	 * if (document.getElementById('ddLgdLBDistListAtInterCA').value == 0) {
	 * return true; } else { if
	 * (document.getElementById('ddLgdLBDistListAtVillageCA').value == 0) {
	 * return true; } else { if
	 * (document.getElementById('ddLgdLBVillageSourceAtVillageCA').value == 0) { }
	 * else {
	 * 
	 * return false; } } } // modified by kirandeep
	 * $("#ddLgdLBVillageCAreaDestL_error").hide(); return true;
	 */
	var id = $('#ddLgdLBType').val();
	var temp = id.split(":");
	var id1 = temp[0];
	var id2 = temp[1];
	var id3 = temp[2];
	var id4 = temp[3];
	var id5 = temp[4];

	if (document.getElementById('tr_village').style.display == 'block') {
		if (document.getElementById('divLgdSelIntermediateP').style.display == 'block') {
			if (document.getElementById('ddLgdLBDistListAtInterCA').value == 0) {
				return true;
			}
			if (document.getElementById('ddLgdLBVillageSourceAtVillageCA').value == 0) {
				return true;
			}

		}
		if (document.getElementById('divLgdVillageP').style.display == 'block') {
			if (id5 == 0) {
				if (document.getElementById('ddLgdLBDistListAtInterCA').value == 0) {
					return true;
				}
			}

			if (document.getElementById('ddLgdLBVillageSourceAtVillageCA').value == 0) {
				return true;
			}

		}
		if (document.getElementById('ddLgdLBVillageSourceAtVillageCA').value == 0) {
			$("#ddLgdLBVillageSourceAtVillageCA_error").show();
			return true;
		}
 /*modified by pooja on 08-07-2015 to remove ward coverage part*/
		/*if (document.getElementById('ddLgdLBVillageCAreaDestL').value == "") {
			$("#ddLgdLBVillageCAreaDestL_error").show();
			return false;
		}*/
		return false;
	}
	/*$("#ddLgdLBVillageCAreaDestL_error").hide();*/
	return true;

}

/*
 * function validateUrbanWardAll() {
 * 
 * var msg = null; //selectALLVillages(); validateUraban(); if
 * (!validateLBUrbantypeName()) {
 * 
 * if (msg != null) { msg = msg + "Please Select Local Body Type" + '\n'; } else {
 * msg = "Please Select Local Body Type" + '\n'; } }
 * 
 * if (!validateWardName()) { if (msg != null) { msg = msg +"Please Enter Name
 * of Ward" + '\n'; }else { msg = "Please Enter Name of Ward" + '\n'; } } if
 * (!validateWardNumber()) { if (msg != null) { msg = msg + "Please Enter Ward
 * Number" + '\n'; }else { msg = "Please Enter Ward Number" + '\n'; } }
 * 
 * var ddLBTypeCode= document.getElementById('localBodyType');
 * 
 * if(ddLBTypeCode.selectedIndex > 0) { if (!validateLBUrbanTehsil()) { if (msg !=
 * null) { msg = msg + "Please Select Tehsil Panchayat" + '\n'; } else { msg =
 * "Please Select Tehsil Panchayat" + '\n'; } } } if(!validateLBSubDistAtUrban) {
 * 
 * if (msg != null) { msg = msg + "Please Select Contributing SubDistrict" +
 * '\n'; }else { msg = "Please Select Contributing SubDistrict" + '\n'; } }
 * if(msg !=null) { alert(msg); return false; } else { return true; } return
 * false; }
 */

function validateUrbanWardModifyAll() {

	var errors = new Array();
	var error = false;
	errors[0] = !validateWardName();
	errors[1] = !validateWardNumber();

	for ( var i = 0; i < 2; i++) {
		if (errors[i] == true) {
			error = true;
			break;
		}
	}

	if (error == true) {
		showClientSideError();
		return false;
	} else {
		showLoadingImage();
		return true;
	}
	return false;
}

function validateUrbanWardAll() {
	// selectALLVillages();
	// added by kirandeep on 01/09/2014
	// validateUraban();

	var errors = new Array();
	var error = false;
	errors[0] = !validateLBtypeNameUrbanWard();
	errors[1] = !validateUrbanWardLBody();
	// added by kirandeep on 01/09/2014
	// errors[2] = !validateWardName();
	// errors[3] = !validateWardNumber();

	var ddLBTypeCode = document.getElementById('localBodyType').value;
	var temp = ddLBTypeCode.split(":");
	var id2 = temp[1];
	// added by kirandeep on 01/09/2014
	/*
	 * if (id2 == 'I') { errors[4] = !validateLBSubDistListAtInterUrban(); }
	 */

	for ( var i = 0; i < 5; i++) {
		if (errors[i] == true) {
			error = true;
			break;
		}
	}

	if (error == true) {
		showClientSideError();
		return false;
	} else {
		showLoadingImage();
		return true;
	}
	return false;
}

function validateUraban() {

	var selObj = document.getElementById('ddLgdWardSubDistrictUListDest');
	// var selObj1 = document.getElementById('ddLgdWardSubDistrictUListSource');

	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;

	}
	/*
	 * for ( var i = 0; i < selObj1.options.length; i++) {
	 * selObj1.options[i].selected = false; }
	 */
}

function validateLBUrbantypeName() {
	var ddLgdLBType = document.getElementById('localBodyType');

	if (ddLgdLBType.selectedIndex == 0) {
		$("#localBodyType_error").show();

		return false;
	}

	$("#localBodyType_error").hide();
	return true;

}

function validateLBUrbanTehsil() {
	var wardUrbanLocalBody = document.getElementById('wardUrbanLocalBody');

	if (wardUrbanLocalBody.selectedIndex <= 0) {
		$("#wardUrbanLocalBody_error").show();

		return false;
	}

	$("#wardUrbanLocalBody_error").hide();
	return true;

}

function validateLBSubDistAtUrban() {

	var ddLgdWardSubDistrictUListDest = document.getElementById('ddLgdWardSubDistrictUListDest');

	if (ddLgdWardSubDistrictUListDest.options.length <= 0) {
		$("#ddLgdWardSubDistrictUListDest_error").show();
		return false;
	}

	$("#ddLgdWardSubDistrictUListDest_error").hide();
	return true;

}

// ////////////////////////////////////////////////new js copied
// here////////////////////////////////////////////////////

/*
 * function validateForm() { selectALLWard(); var errors = new Array();
 * 
 * var error = false;
 * 
 * errors[0] = vlidateOnblur('ddLgdLBType','1','15','c');
 * 
 * errors[1] = vlidateOnblur('ddSourceLocalBody','1','15','c');
 * 
 * errors[2] = vlidateOnblur('ddLgdLBDistListAtInterCA','1','15','c');
 * 
 * errors[3] = vlidateOnblur('ddLgdLBInterPSourceList','1','15','c');
 * 
 * errors[4] = vlidateOnblur('ddLgdLBDistListAtVillageCA','1','15','c');
 * 
 * errors[6] = vlidateOnblur('ddLgdLBInterListAtVillageCA','1','15','c');
 * 
 * errors[5] = vlidateOnblur('ddLgdLBVillageSourceAtVillageCA','1','15','c');
 * 
 * errors[7] = vlidateOnblur('wardname','1','15','c');
 * 
 * errors[8] = vlidateOnblur('wardnumber','1','15','c');
 * 
 * if(document.getElementById('ddLgdLBType').value=="1:D"){
 * if(errors[0]==true||errors[1]==true){ error = true; } }
 * if(document.getElementById('ddLgdLBType').value=="2:I"){
 * if(errors[0]==true||errors[2]==true||errors[3]==true){ error = true; } }
 * if(document.getElementById('ddLgdLBType').value=="3:V"){
 * if(errors[0]==true||errors[4]==true||errors[5]==true||errors[6]==true){ error =
 * true; } }
 * 
 * if(errors[7]==true||errors[8]==true){ error = true; } for(var i=startNum ; i<=endNum ;
 * i++) { if(errors[i]==true) { error = true; } }
 * 
 * if(error == true) {
 * 
 * showClientSideError();
 * 
 * return false; } else { return true; } return false; }
 */

function validateWardForm() {
	var errors = new Array();

	var error = false;

	errors[0] = vlidateOnblur('ddLgdLBType', '1', '15', 'c');

	errors[1] = vlidateOnblur('ddSourceLocalBody', '1', '15', 'c');

	errors[2] = vlidateOnblur('ddLgdLBDistListAtInterCA', '1', '15', 'c');

	errors[3] = vlidateOnblur('ddLgdLBInterPSourceList', '1', '15', 'c');

	errors[4] = vlidateOnblur('ddLgdLBDistListAtVillageCA', '1', '15', 'c');

	errors[6] = vlidateOnblur('ddLgdLBInterListAtVillageCA', '1', '15', 'c');

	errors[5] = vlidateOnblur('ddLgdLBVillageSourceAtVillageCA', '1', '15', 'c');
	if (errors[0] == true) {
		error = true;
	}

	if (document.getElementById('ddLgdLBType').value == "1:D:P") {

		if (errors[0] == true || errors[1] == true) {
			error = true;
		}
	}

	if (document.getElementById('ddLgdLBType').value == "2:I:P") {
		if (errors[0] == true || errors[2] == true || errors[3] == true) {
			error = true;
		}
	}

	if (document.getElementById('ddLgdLBType').value == "3:V:P") {
		if (errors[0] == true || errors[4] == true || errors[5] == true || errors[6] == true) {
			error = true;
		}
	}
	if (document.getElementById('ddLgdLBType').value == "9:D:T") {
		if (errors[1] == true || errors[2] == true) {
			error = true;
		}
	}
	if (document.getElementById('ddLgdLBType').value == "14:I:T") {
		if (errors[1] == true || errors[2] == true || errors[3] == true) {
			error = true;
		}
	}
	if (document.getElementById('ddLgdLBType').value == "11:V:T") {
		if (errors[4] == true || errors[5] == true || errors[6] == true) {
			error = true;
		}
	}
	/*
	 * if(document.getElementById('ddLgdLBType').value=="1:D"){
	 * if(errors[0]==true||errors[1]==true){ error = true; } }
	 * if(document.getElementById('ddLgdLBType').value=="2:I"){
	 * if(errors[0]==true||errors[2]==true||errors[3]==true){ error = true; } }
	 * if(document.getElementById('ddLgdLBType').value=="3:V"){
	 * if(errors[0]==true||errors[4]==true||errors[5]==true||errors[6]==true){
	 * error = true; } }
	 */

	if (error == true) {

		showClientSideError();

		return false;
	} else {
		return true;
	}

}
/*comment by pooja on 08-07-2015*/
/*function selectALLWard() {

	var selObj = document.getElementById('ddLgdLBVillageDestLatDCA');
	var selObj1 = document.getElementById('ddLgdLBVillageDestLatICA');
	var selObj3 = document.getElementById('ddLgdLBInterCAreaDestL');
	var selObj4 = document.getElementById('ddLgdLBSubDistrictDestLatDCA');
	var selObj5 = document.getElementById('ddLgdLBDistCAreaDestL');
	var selObj6 = document.getElementById('ddLgdLBVillageCAreaDestL');

	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;

	}
	for ( var i = 0; i < selObj1.options.length; i++) {
		selObj1.options[i].selected = true;

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
	for ( var i = 0; i < selObj6.options.length; i++) {
		selObj6.options[i].selected = true;
	}

}*/
function validateUrbanWardForm() {

	var localBodyType = document.getElementById('localBodyType').value;
	var wardUrbanLocalBody = document.getElementById('wardUrbanLocalBody').value;
	var errors = new Array();

	var error = false;

	errors[0] = vlidateOnblur('localBodyType', '1', '15', 'c');

	errors[1] = vlidateOnblur('wardUrbanLocalBody', '1', '15', 'c');

	if (localBodyType >= 0) {
		if (errors[0] == true) {

			error = true;
		}
	}

	if (wardUrbanLocalBody >= 0) {

		if (errors[1] == true) {

			error = true;
		}
	}

	/*
	 * if(document.getElementById('ddLgdLBType').value=="1:D"){
	 * if(errors[0]==true||errors[1]==true){ error = true; } }
	 * if(document.getElementById('ddLgdLBType').value=="2:I"){
	 * if(errors[0]==true||errors[2]==true||errors[3]==true){ error = true; } }
	 * if(document.getElementById('ddLgdLBType').value=="3:V"){
	 * if(errors[0]==true||errors[4]==true||errors[5]==true||errors[6]==true){
	 * error = true; } }
	 */

	if (error == true) {

		showClientSideError();

		return false;
	} else {
		return true;
	}

}