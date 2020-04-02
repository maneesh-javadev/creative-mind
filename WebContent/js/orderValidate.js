validateOrderDetails = function() {

	var OrderNo = document.getElementById('OrderNo').value;

	var status = true;

	status = checkLengthGeneral(OrderNo, 'OrderNo');
	if (OrderNo == '') {
		$("#OrderNo_error").show();
		status = false;
	} else {
		status = validateOrderNoGeneral(OrderNo);
	}

	return status;
};

function validateOrderNoGeneral(id) {

	var pattern = "[~#%&\!\@\$\^\*\_\+\`\=\{\}\\[\\]\|\:\;\'\"\<\>\?\,\]";
	var regularExpression = new RegExp(pattern);
	var test = regularExpression.test(id);

	if (test) {
		$("#" + 'OrderNo').removeClass("frmfield");
		$("#" + 'OrderNo').addClass("error_fld");
		$("#" + 'OrderNo_msg').show();
		return false;
	} else {

		for ( var k = 0; k < id.length; k++) {
			var char1 = id.charAt(k);
			var unicode = char1.charCodeAt(0);

			if (unicode == 92) {
				$("#" + 'OrderNo').removeClass("frmfield");
				$("#" + 'OrderNo').addClass("error_fld");
				$("#" + 'OrderNo_msg').show();
				return false;
			}

		}
		return true;

	}

}
function checkLengthGeneral(value, id) {
	if (value.length > 60) {

		$("#" + id).removeClass("frmfield");
		$("#" + id).addClass("error_fld");

		$("#" + id + "_error").show();

		document.getElementById("OrderNo").value = '';

		return false;
	} else {
		return true;
	}

}

function validateaGovtOrderNOforModify(event) {
	$("#" + 'OrderNo_msg').hide();
	$("#" + 'errormsg').hide();
	var unicode = event.charCode ? event.charCode : event.keyCode;
	var actualkey = String.fromCharCode(unicode);
	var pattern = "[~#%&\!\@\$\^\*\_\+\`\=\{\}\\[\\]\|\:\;\'\"\<\>\?\,\]";
	var regularExpression = new RegExp(pattern);
	var test = regularExpression.test(actualkey);
	if (test) {
		$("#" + 'OrderNo').removeClass("frmfield");
		$("#" + 'OrderNo').addClass("error_fld");
		$("#" + 'OrderNo_msg').show();
		return false;
	} else {
		if (unicode == 92) {
			$("#" + 'OrderNo').removeClass("frmfield");
			$("#" + 'OrderNo').addClass("error_fld");
			$("#" + 'OrderNo_msg').show();
			return false;
		} else {
			return true;
		}

	}

}

function hideMessageOnKeyPressForOrder(id) {
	if (document.getElementById(id).value != "") {
		$("#" + id + "_msg").fadeOut(3000);
	}
}

clearOrdernoErrors = function() {
	var gtaInterPanch = $('#gtaInterPanch').val();
	$("#" + 'OrderNo_msg').hide();
	$("#" + 'OrderNo_error').hide();

};
