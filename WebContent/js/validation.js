// JavaScript Document
//Validation API by sumit kumar sharma 
//Methods 
//validateOnKeyPessDown(event,Id Of field,Type Of Input You want to validate)
//vlidateOnblur(Field id,Minimum length of field,Maximumm Length of field)

function vlidateDateOnblur(id, fDate, type) {

	// $.datepicker = new Datepicker();
	// alert($.datepicker.val());

	// var p = $(id).datepicker({
	// changeMonth: true,
	// changeYear: true
	// });
	// alert(p.val());

	$("#" + id + "_msg").hide();
	// $("#"+id).val(trimString($("#"+id).val()));
	// var tempValue = $("#"+id).val();
	// if(type == 'm')
	// {
	// if(tempValue.length < minLength || tempValue.length > maxLength)
	// {
	// $("#"+id).addClass("error_fld");
	// $("#"+id+"_error").fadeIn(500);
	// return true;
	// }
	// else
	// {
	// return false;
	// }
	// }
	// else if(type == 'o')
	// {
	// if(tempValue.length!=0)
	// {
	// if(tempValue.length < minLength || tempValue.length > maxLength)
	// {
	// $("#"+id).addClass("error_fld");
	// $("#"+id+"_error").fadeIn(500);
	// return true;
	// }
	// else
	// {
	// return false;
	// }
	// }
	// else
	// {
	// return false;
	// }
	// }
	return true;
}

function validateOnKeyPessDown(e, id, type) {
	if (type == 'char') {
		// if (e.shiftKey || e.ctrlKey || e.altKey) { // if shift, ctrl or alt
		// keys held down
		// e.preventDefault();
		// $("#"+id+"_type_error").fadeIn(1000,function(){$("#"+id+"_type_error").fadeOut(1000);});
		// }
		// else
		// {
		var n = e.keyCode;
		if ((n >= 48 && n <= 57) // numbers on keyboard
				|| (n >= 96 && n <= 105) // number on keypad
		) {
			e.preventDefault(); // Prevent character input
			$("#" + id + "_type_error").fadeIn(1000, function() {
				$("#" + id + "_type_error").fadeOut(1000);
			});
		}

		// }
	}

	else if (type == 'number') {
		if (e.shiftKey || e.ctrlKey || e.altKey) { // if shift, ctrl or alt
			// keys held down
			e.preventDefault();
			$("#" + id + "_type_error").fadeIn(1000, function() {
				$("#" + id + "_type_error").fadeOut(1000);
			});
		} else {
			var n = e.keyCode;
			if (!((n == 8) // backspace
					|| (n == 46) // delete
					|| (n == 9) // tab
					|| (n >= 35 && n <= 40) // arrow keys/home/end
					|| (n >= 48 && n <= 57) // numbers on keyboard
					|| (n >= 96 && n <= 105) // number on keypad
			|| (n >= 112 && n <= 122)) // f1 to f12
			) {
				e.preventDefault(); // Prevent character input
				$("#" + id + "_type_error").fadeIn(1000, function() {
					$("#" + id + "_type_error").fadeOut(1000);
				});
			}

		}
	} else if (type == "charnum") {
		if (e.ctrlKey || e.altKey) { // if shift, ctrl or alt keys held down
			e.preventDefault();
			$("#" + id + "_type_error").fadeIn(1000, function() {
				$("#" + id + "_type_error").fadeOut(1000);
			});
		}
	}

	else {
		if (e.ctrlKey || e.altKey) { // if shift, ctrl or alt keys held down
			e.preventDefault();
		}

	}
}

function vlidateEmailOnblur(id, type) {

	$("#" + id + "_msg").hide();
	$("#" + id).val(trimString($("#" + id).val()));
	var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
	var tempValue = $("#" + id).val();
	if (type == 'm') {
		if (!tempValue.length > 0) {
			$("#" + id).addClass("error_fld");
			$("#" + id + "_error").fadeIn(500);
			return true;
		} else if (!emailReg.test(tempValue)) {
			$("#" + id).addClass("error_fld");
			$("#" + id + "_invalid_error").fadeIn(500);
			return true;
		} else {
			return false;
		}
	} else if (type == 'o' && tempValue.length > 0) {
		if (!emailReg.test(tempValue)) {
			$("#" + id).addClass("error_fld");
			$("#" + id + "_error").fadeIn(500);
			return true;
		} else {
			return false;
		}
	} else {
		return false;
	}
}

function vlidateOnblur(id, minLength, maxLength, type) {
	$("#" + id + "_msg").hide();
	$("#" + id).val(trimString($("#" + id).val()));
	var tempValue = $("#" + id).val();
	if (type == 'm') {
		if (tempValue.length < minLength || tempValue.length > maxLength) {
			$("#" + id).addClass("error_fld");
			$("#" + id + "_error").fadeIn(500);
			return true;
		} else {
			return false;
		}
	} else if (type == 'o') {
		if (tempValue.length != 0) {
			if (tempValue.length < minLength || tempValue.length > maxLength) {
				$("#" + id).addClass("error_fld");
				$("#" + id + "_error").fadeIn(500);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}

function validateOnFocus(id) {
	$("#" + id + "_msg").fadeIn(500);
	$("#" + id).removeClass("error_fld");
	$("#" + id + "_error").hide();
	$("#" + id + "_invalid_error").hide();
	$('#' + id).bind("cut copy paste", function(e) {
		e.preventDefault();
	});
}

function dependentField(field1, field2) {

	// / alert("hi");
	$("#" + field1).val(trimString($("#" + field1).val()));
	$("#" + field2).val(trimString($("#" + field2).val()));

	// /alert("asdasda");

	var first = $("#" + field1).val();
	var second = $("#" + field2).val();

	// alert(field1+field2+"first = "+"----"+"second = "+second);
	if (second.length > 0) {
		if (!first.length > 0) {
			$("#" + field1).addClass("error_fld");
			$("#" + field1 + "_error").fadeIn(500);
			return true;
		} else {
			return false;
		}

	}
	return false;

}
