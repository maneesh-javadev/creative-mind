var errors = new Array();

function show_msg(Field_msg) {
	var hint = '#' + Field_msg + "_msg";
	var error = '#' + Field_msg + "_error";
	$("#" + Field_msg).removeClass("error_fld");
	$("#" + Field_msg).removeClass("error_msg");
	$(hint).show();
	$(error).hide();

}
// OfficialAddress1
function statemap() {
	var ss = document.getElementById("stateseperateserver");
	if (ss.checked) {
		if (document.getElementById("isbaseUrlstate").value == "") {

// document.getElementById("isbaseUrlstate_error").innerHTML = "Base URL is
// Required";
			$("#isbaseUrlstate_error").show();
			$("#isbaseUrlstate_msg").hide();
			$("#isbaseUrlstate").addClass("error_fld");
			$("#isbaseUrlstate").addClass("error_msg");
			return false;

		} else {
			$("#isbaseUrlstate_msg").hide();
			return true;

		}
	} else {
		$("#isbaseUrlstate_msg").hide();
		return true;

	}
}
function lgdmmap() {

	var ss = document.getElementById("seperateServer_${administratorUnitRow.index+1}");
	if (ss.checked) {
		if (document.getElementById("baseUrl").value == "") {

// document.getElementById("baseUrlerror__${administratorUnitRow.index+1}").innerHTML
// = "Base URL is Required";
			$("#isbaseUrlstate_error").show();
			$("#isbaseUrlstate_msg").hide();
			$("#isbaseUrlstate").addClass("error_fld");
			$("#isbaseUrlstate").addClass("error_msg");
			return false;

		} else {
			$("#isbaseUrlstate_msg").hide();
			return true;

		}
	} else {
		$("#isbaseUrlstate_msg").hide();
		return true;

	}
}
function districtmap() {
	var dd = document.getElementById("districtseperateserver");
	if (dd.checked) {

		if (document.getElementById("districtbaseulr").value == "") {
// document.getElementById("districtbaseulr_error").innerHTML = "Base URL is
// Required";
			$("#districtbaseulr_error").show();
			$("#districtbaseulr_msg").hide();
			$("#districtbaseulr").addClass("error_fld");
			$("#districtbaseulr").addClass("error_msg");
			return false;

		} else {
			$("#districtbaseulr_msg").hide();
			return true;

		}
	} else {
		$("#districtbaseulr_msg").hide();
		return true;

	}

}
function subdistrictmap() {
	var tt = document.getElementById("subdistrictseperateserver");
	if (tt.checked) {
		if (document.getElementById("subdistrictbaseurl").value == "") {
// document.getElementById("subdistrictbaseurl_error").innerHTML = "Base URL is
// Required";
			$("#subdistrictbaseurl_error").show();
			$("#subdistrictbaseurl_msg").hide();
			$("#subdistrictbaseurl").addClass("error_fld");
			$("#subdistrictbaseurl").addClass("error_msg");
			return false;

		} else {
			$("#subdistrictbaseurl_msg").hide();
			return true;

		}
	} else {
		$("#subdistrictbaseurl_msg").hide();
		return true;

	}
}
function villagemap() {
	var vv = document.getElementById("villageseperateserver");
	if (vv.checked) {

		if (document.getElementById("villagebaseurl").value == "") {
// document.getElementById("villagebaseurl_error").innerHTML = "Base URL is
// Required";
			$("#villagebaseurl_error").show();
			$("#villagebaseurl_msg").hide();
			$("#villagebaseurl").addClass("error_fld");
			$("#villagebaseurl").addClass("error_msg");
			return false;

		} else {
			$("#villagebaseurl_msg").hide();
			return true;

		}
	}
	else {
		$("#villagebaseurl_msg").hide();
		return true;

	}
}
// modify Block
function blockmap() {
	
	var bb = document.getElementById("blockseperateserver");
	if (bb.checked) {
		if (document.getElementById("blockbaseurl").value == "") {

// document.getElementById("blockbaseurl_error").innerHTML = "Base URL is
// Required";
			$("#blockbaseurl_error").show();
			$("#blockbaseurl_msg").hide();
			$("#blockbaseurl").addClass("error_fld");
			$("#blockbaseurl").addClass("error_msg");
			return false;

		} else {
			$("#blockbaseurl_msg").hide();
			return true;

		}
	} else {
		$("#blockbaseurl_msg").hide();
		return true;

	}
}

// Modify Change ----
/*function validateblockAll() {

	var msg = null;

	if ((!blockmap()) && bb.checked) {
		msg = "Please Enter Base URL" + '\n';
		alert("base url");
	}

	if (msg != null) {
		alert(msg);
		return false;
	} else {
		return true;
	}
	return false;
}*/

function validateblockAll() {

	var errors = new Array();
	var error = false;

	if ((!blockmap())) {
		errors[0] = vlidateOnblur('blockbaseurl','1','15','c');
	}

	  if(errors[0]==true){
		  error = true;
	  }
	
		if(error == true)
		{
		
		showClientSideError();
	
		return false;
		}
	else
		{
		doSubmit1();
		return true;
		}		
}


/*
 * function validatelandAlls() {
 * 
 * var msg = null; alert(statemap()); if (!statemap()) {
 * 
 * msg = "Please Enter Base URL" + '\n'; } if (!districtmap()) {
 * 
 * msg = "Please Enter Base URL" + '\n'; } if (!subdistrictmap()) {
 * 
 * msg = "Please Enter Base URL" + '\n'; } if (!villagemap()) {
 * 
 * msg = "Please Enter Base URL" + '\n'; } if (msg != null) { alert(msg); return
 * false; } else {
 * 
 * doSubmit1(); return true; } return false; }
 */

function validatelandAlls() {
	
	var errors = new Array();
	var error = false;
	
	if (!statemap()){
	    errors[0] = vlidateOnblur('isbaseUrlstate','1','15','c');
	}
	   
	  if(errors[0]==true){
		  error = true;
	  }
	  
	  if (!districtmap()) {

		  errors[1] = vlidateOnblur('districtbaseulr','1','15','c');

		}
	  if(errors[1]==true){
		  error = true;
	  }
	  
		if (!subdistrictmap()) {

			  errors[2] = vlidateOnblur('subdistrictbaseurl','1','15','c');

		}
	  
		 if(errors[2]==true){
			  error = true;
		  }
		
			if (!villagemap()) {

				 errors[3] = vlidateOnblur('villagebaseurl','1','15','c');

			}
			
			 if(errors[3]==true){
				  error = true;
			  }
					 
		if(error == true)
			{
			
			showClientSideError();
		
			return false;
			}
		else
			{
			doSubmit1();
			return true;
			}	
		
}



function validatelandregion() {
	
	var errors = new Array();
	var error = false;
	
	if (!statemap()){
	    errors[0] = vlidateOnblur('isbaseUrlstate','1','15','c');
	}
	   
	  if(errors[0]==true){
		  error = true;
	  }
	  

	  if (!districtmap()) {
		  errors[1] = vlidateOnblur('districtbaseulr','1','15','c');
	  }
	  
	  if(errors[1]==true){
		  error = true;
	  }
	  

	  if(errors[2]==true){
			  errors[2] = vlidateOnblur('subdistrictbaseurl','1','15','c');
	  }

	  
		 if(errors[2]==true){
			  error = true;
		  }
		
	
			if (!villagemap()) {
				 errors[3] = vlidateOnblur('villagebaseurl','1','15','c');
			}

			
			 if(errors[3]==true){
				  error = true;
			  }
					 
		if(error == true)
			{
			
			showClientSideError();
		
			return false;
			}
		else
			{
			doSubmit1();
			return true;
			}	
		
}








function doSubmit1() {
	document.getElementById('savebtn').disabled = true;
	document.forms['form1'].submit();
}
