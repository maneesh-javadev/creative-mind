var isSubmit = false;

function refresh() {
	javascript: location.reload(true);
}
/*
 * start Autohr Kirandeep Date 18/06/2014
 */
$(document).ready(function() {
	$("form").submit(function(e) {
		$('input:submit').attr("disabled", true);
		$('input[name=Submit]').attr('disabled', true);
	});

});
/* end */

function go(url) {

	if (url.indexOf('home.htm') > -1 || url.indexOf(window.location.pathname.split('/')[window.location.pathname.split('/').length - 1]) == -1) {
		if (url.indexOf("?") > -1)
			window.location = url + "&OWASP_CSRFTOKEN=" + getParameterByNameForCSRF();
		else
			window.location = url + "?OWASP_CSRFTOKEN=" + getParameterByNameForCSRF();
	} else
		window.location = window.location.href;

}

// To get parameters from the query string
function getParameterByNameForCSRF() {

	var name = "OWASP_CSRFTOKEN";
	name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
	var regexS = "[\\?&]" + name + "=([^&#]*)";
	var regex = new RegExp(regexS);
	var results = regex.exec(window.location.search);
	if (results == null)
		return "";
	else
		return decodeURIComponent(results[1].replace(/\+/g, " "));
}

function validateOnlyDigit() {

	var key;

	key = event.keyCode;
	if (key == 0)
		key = event.which;

	if ((key >= 48) && (key <= 57)) {
		return true;

	} else {
		alert("Please use numeric & Alphabets value only");
		event.returnValue = false;
		return false;
	}
}

function validateNumeric() {
	var key;

	key = event.keyCode;
	if (key == 0)
		key = event.which;

	if ((key >= 48) && (key <= 58) || (key == 45)) {
		return true;

	} else {
		alert("Please use numeric & Alphabets value only");
		event.returnValue = false;
		return false;
	}
}

function validateNumericKeys(event) {
	var key;
	key = event.keyCode;
	if (key == 0)
		key = event.which;
	if ((key >= 48) && (key <= 57) || (key == 39) || (key == 46) || (key == 8) || (key == 9)) {
		return true;
	} else {
		alert("Please use Numerics !");
		event.returnValue = false;
		return false;
	}
}

function validateNumericKeysAndDot() {
	var key = event.keyCode;

	if ((key >= 48) && (key <= 58) || (key == 46)) {

	} else {
		alert("Only dot[.] and numeric[1-9] values are allowed");
		event.returnValue = false;
	}

}

function validateNumericKeysAndDotLBody(event) {
	var key;
	key = event.keyCode;
	if (key == 0)
		key = event.which;

	if ((key >= 48) && (key <= 58) || (key == 46)) {
		return true;
	} else {
		alert("Only dot[.] and numeric[1-9] values are allowed");
		event.returnValue = false;
		return false;
	}
}

function rangelat() {
	var obj = document.getElementById('latitude');
	if (obj.value.length >= 1) {
		if (!(obj.value >= 6 && obj.value <= 38))
			alert("Kindly enter value in range of 6-38");
		return false;
	}
}

function rangelon() {
	var obj = document.getElementById('longitude');
	if (obj.value.length >= 2) {
		if (!(obj.value >= 32 && obj.value <= 98))
			alert("Kindly enter value in range of 32-98");
		return false;

	}
}

function validateAlphanumericKeysStateSp(event) {
	var key;

	key = event.keyCode;

	if (key == 0)
		key = event.which;

	if ((key >= 48) && (key <= 58) || (key >= 65) && (key <= 90) || (key >= 97) && (key <= 122)) {
		return true;
	} else {
		alert("Please use numeric & Alphabets value only");
		event.returnValue = false;
		return false;
	}
}

function validateAlphaWithSpace(event) {
	var key;
	// alert("kamlesh"+event.which);

	key = event.keyCode;
	if (key == 0)
		key = event.which;

	if ((key >= 65) && (key <= 90) || (key >= 97) && (key <= 122) || (key == 32) || (key == 13) || (key == 46) || (key == 8) || (key == 27)
			|| (key == 16) || (key == 9) || (key == 20)) {
		return true;
	} else {
		alert("Please use [A-Z],[a-z] and space only");
		event.returnValue = false;
		return false;
	}
}

function validateAlphanumericWithSpace(event) {
	var key;

	key = event.keyCode;

	// alert(key);
	if (key == 0)
		key = event.which;

	if ((key >= 48) && (key <= 58) || (key >= 65) && (key <= 90) || (key >= 97) && (key <= 122) || (key == 32) || (key == 8) || (key == 9)
			|| (key == 46)) {
		event.returnValue = true;
		return true;
	} else {
		alert("Please use numeric,Alphabets and Space value only");
		event.returnValue = false;
		return false;

	}
}

function validateSpecialCharacterKeys() {
	var key = event.keyCode;
	// if ((key >= 48) && (key <= 58) || (key >= 65) && (key <= 90) || (key >=
	// 97) && (key <= 122))
	if ((key == 33) || (key == 64) || (key == 35) || (key == 36) || (key == 37) || (key == 94) || (key == 38) || (key == 42) || (key == 40)
			|| (key == 41) || (key == 95) || (key == 43) || (key == 61) || (key == 93)(key == 93) || (key == 123) || (key == 125) || (key == 92)
			|| (key == 124) || (key == 59) || (key == 39) || (key == 58) || (key == 34) || (key == 44) || (key == 46) || (key == 47) || (key == 60)
			|| (key == 62) || (key == 63) || (key == 126) || (key == 96)) {
		alert("Please use numeric & Alphabets value only");
		event.returnValue = false;
	} else {
		event.returnValue = true;
	}
}

function validateforTextanddash() {
	var key = event.keyCode;

	if ((key >= 65) && (key <= 90) || (key >= 97) && (key <= 122) || (key == 45) || (key == 32)) {

	} else {
		alert("Please use [A-Z],[a-z], space, [-] in name");
		event.returnValue = false;
	}
}

function validateforTextandspace(event) {
	var key = event.keyCode;
	if (key == 0)
		key = event.which;
	if ((key >= 65) && (key <= 90) || (key >= 97) && (key <= 122) || (key == 32) || (key == 8) || (key == 44) || (key == 46)) {
		return true;
	} else {
		alert("Please use [A-Z],[a-z], space in name");
		event.returnValue = false;
		return false;
	}
}

function validateCharKeysWithDotSlash(event) {
	var key;
	key = event.keyCode;
	if (key == 0)
		key = event.which;
	// alert(key);
	if ((key >= 65) && (key <= 90) || (key >= 97) && (key <= 122) || (key == 32) || (key == 8) || (key == 9) || (key == 39) || (key == 45)
			|| (key == 46) || (key >= 48) && (key <= 57)) {
		event.returnValue = true;
		return true;
	} else {
		alert("Please use [A-Z],[a-z],[0-9], [- and .] in name");
		event.returnValue = false;
		return false;
	}
}

function validateCharKeysWithDotSlashBracket(event) {
	var key;
	key = event.keyCode;
	if (key == 0)
		key = event.which;
	// alert(key);
	if ((key >= 65) && (key <= 90) || (key >= 97) && (key <= 122) || (key == 45) || (key == 46) || (key == 40) || (key == 41) || (key == 91)
			|| (key == 93) || (key == 32) || (key == 8) || (key == 9))
	/*
	 * 32=Space 8=BackSpace 9=Tab 45=Desh[-] 46=Dot[.] 91=[ 93=] 40=( 41=)
	 * 
	 */
	{
		event.returnValue = true;
		return true;
	} else {
		alert("Please use [A-Z],[a-z], [- and . ()[]] in name");
		event.returnValue = false;
		return false;
	}
}

function validateCharKeys(event) {
	var key;
	key = event.keyCode;
	if (key == 0)
		key = event.which;
	// alert(key);
	if ((key >= 65) && (key <= 90) || (key >= 97) && (key <= 122) || (key == 32) || (key == 8 || (key == 9) || (key == 39))) {
		return true;
	} else {
		alert("Please use [A-Z],[a-z] in name");
		event.returnValue = false;
		return false;
	}
}

function validateAlphaNumeric(strValue) {
	var objRegExp = /^[a-zA-Z0-9 ()]*$/;
	return objRegExp.test(strValue.value);
}

function validateAlphanumericKeys(event) {
	var key;

	key = event.keyCode;

	if (key == 0)
		key = event.which;

	if ((key >= 65) && (key <= 90) || (key >= 97) && (key <= 122) || (key == 45) || (key == 32) || (key == 8) || (key == 44) || (key == 46)) {
		return true;
	} else {
		alert("Please use [A-Z],[a-z], space , [.,-] in name");
		event.returnValue = false;
		return false;
	}
}

function validateAlphanumericUpdateKeys() {
	var key = event.keyCode;
	if ((key >= 65) && (key <= 90) || (key >= 97) && (key <= 122) || (key == 45) || (key == 32) || (key >= 48) && (key <= 57) || (key == 40)
			|| (key == 41)) {

	} else {
		alert("Please use [A-Z],[a-z],numeric, space , (,) and - in name");
		event.returnValue = false;

	}
}

function validateAlphanumericUpdateKeysLBody(event) {
	var key;
	key = event.keyCode;
	if (key == 0)
		key = event.which;
	if ((key >= 65) && (key <= 90) || (key >= 97) && (key <= 122) || (key == 45) || (key == 32) || (key >= 48) && (key <= 57) || (key == 40)
			|| (key == 41)(key == 46)) {
		return true;
	} else {
		alert("Please use [A-Z],[a-z],numeric, space , (,) and - in name");
		event.returnValue = false;
		return false;
	}
}

function validateAlphanumericUpdateKeysLBodyFinal(event) {
	var key;
	key = event.keyCode;
	if (key == 0)
		key = event.which;
	if ((key >= 65) && (key <= 90) || (key >= 97) && (key <= 122) || (key == 45) || (key == 46) || (key == 32) || (key >= 48) && (key <= 57)
			|| (key == 8) || (key == 37)) {
		return true;
	} else {
		alert("Please use [A-Z],[a-z],numeric, space , (,) and - in name");
		event.returnValue = false;
		return false;
	}
}

function validateAlphanumericUpdateKeysWard(event) {

	var key;
	key = event.keyCode;
	if (key == 0)
		key = event.which;

	if ((key >= 65) && (key <= 90) || (key >= 97) && (key <= 122) || (key == 45) || (key == 46) || (key == 32) || (key >= 48) && (key <= 57)
			|| (key == 40) || (key == 41) || (key == 91) || (key == 93) || (key == 8)) {
		return true;
	} else {
		alert("Please use [A-Z],[a-z],numeric,(),[],space ,., (,) and - in Name");
		event.returnValue = false;
		return false;
	}
}

function validateAlphanumericUpdateKeysTemplateNm(event) {

	var key;
	key = event.keyCode;
	if (key == 0)
		key = event.which;

	if ((key >= 65) && (key <= 90) || (key >= 97) && (key <= 122) || (key == 32) || (key >= 48) && (key <= 57) || (key == 8) || (key == 37)) {
		return true;
	} else {
		alert("Please use [A-Z],[a-z],numeric,space in Name");
		event.returnValue = false;
		return false;
	}
}
// Function modified by Arnab to include / on 24/07/2013
function validateAlphanumericforOrder(event) {
	var key;
	key = event.keyCode;

	if (key == 0)
		key = event.which;
	/*
	 * if ((key >= 65) && (key <= 90) || (key >= 97) && (key <= 122) ||
	 * (key==45) || (key >= 48) && (key <= 57))
	 */
	if ((key >= 65) && (key <= 90) || (key >= 97) && (key <= 122) || (key == 45) || (key >= 48) && (key <= 57) || (key == 13) || (key == 37)
			|| (key == 46) || (key == 8) || (key == 27) || (key == 16) || (key == 9) || (key == 20) || (key >= 47)) {
		return true;
	} else {
		alert("Please use [A-Z],[a-z],numeric,/ in name");
		event.returnValue = false;
		return false;
	}
}

function validateAlphanumericKeysStateSp(event) {
	var key;

	key = event.keyCode;

	if (key == 0)
		key = event.which;

	if ((key >= 48) && (key <= 58) || (key >= 65) && (key <= 90) || (key >= 97) && (key <= 122) || (key == 8)) {
		return true;
	} else {
		alert("Please use numeric & Alphabets value only");
		event.returnValue = false;
		return false;
	}
}

function validateNumberWardNum(event) {
	var key;
	var chkey = -1;
	key = event.keyCode;
	if (key == 0) {
		chkey = key;
		key = event.which;
	}

	if ((key == 47) || (key == 48) || (key == 49) || (key == 50) || (key == 51) || (key == 52) || (key == 53) || (key == 54) || (key == 55)
			|| (key == 56) || (key == 57) || (key == 8) || (key == 46 && chkey == -1)) {
		return true;
	} else {
		alert("Please use numeric value & / only");
		event.returnValue = false;
		return false;
	}

}

function validateAlphanumericKeysforNomenclature() {
	var key = event.keyCode;

	if ((key >= 65) && (key <= 90) || (key >= 97) && (key <= 122) || (key == 46) || (key == 34) || (key == 39)) {

	} else {
		alert("Please use only alphabets and [.,?,'] in Nomenclature");
		event.returnValue = false;

	}
}
function validateAlphanumericandDotKeysforNomenclature() {
	var key = event.keyCode;
	// alert(key);
	if ((key >= 65) && (key <= 90) || (key >= 97) && (key <= 122) || (key == 46)) {

	} else {
		alert("Please use only alphabets and [.] in Nomenclature");
		event.returnValue = false;

	}
}

var message = "Can not use right Click"; // edit this message to say what you
// want

function clickIE() {
	if (event.button == 0) {
		alert(message);
		return false;
	}
}
function clickNS(e) {
	if (document.layers || (document.getElementById && !document.all)) {
		if (e.which == 2 || e.which == 3) {
			alert(message);
			return false;
		}
	}
}

if (window.navigator.appName == "Netscape") {
	document.onmouseup = clickNS;
	document.oncontextmenu = clickIE;
	document.oncontextmenu = new Function("return false")
} else if (window.navigator.appName == "Microsoft Internet Explorer") {
	document.oncontextmenu = clickIE;
} else if (window.navigator.appName == "Opera") {

} else if (document.layers) {
	document.captureEvents(Event.MOUSEDOWN);
	document.onmousedown = clickNS;
} else {
	document.onmouseup = clickNS;
	document.oncontextmenu = clickIE;
}

function disableCtrlKeyCombination(e) {

	// list all CTRL + key combinations you want to disable
	var forbiddenKeys = new Array('a', 'n', 'c', 'x', 'v', 'j', 'w');
	var key;
	var isCtrl;

	if (window.event) {
		key = window.event.keyCode; // IE

		if (window.event.ctrlKey)
			isCtrl = true;
		else
			isCtrl = false;
	} else {
		key = e.which;
		if (e.ctrlKey)
			isCtrl = true;
		else
			isCtrl = false;
	}

	// if ctrl is pressed check if other key is in forbidenKeys array
	if (isCtrl) {
		for (i = 0; i < forbiddenKeys.length; i++) {
			// case-insensitive comparation
			if (forbiddenKeys[i].toLowerCase() == String.fromCharCode(key).toLowerCase()) {
				alert("Key combination CTRL " + String.fromCharCode(key) + " has been disabled.");
				return false;
			}
		}
	}
	return true;
}

function validateNumericAlphaKeys() {

	var key = event.keyCode;

	if ((key >= 48) && (key <= 58) || (key >= 65) && (key <= 90) || (key >= 97) && (key <= 122) || (key >= 32)) {

	} else {
		event.returnValue = false;
	}
}

function cursorwait() {
	document.body.style.opacity = '0.7';
	document.body.style.cursor = 'wait';

}

function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g, '');
}

function fullTrim(stringToTrim) {
	return stringToTrim.replace(/(?:(?:^|\n)\s+|\s+(?:$|\n))/g, '').replace(/\s+/g, ' ');
}

function excludeTopSelectAndSubmit(objName) {
	if (document.getElementById(objName).selectedIndex > 0)
		isSubmit = true;
}

function checkTextInput(inputName) {
	if (trim(document.getElementById(inputName).value).length > 0)
		isSubmit = true;
}

function doSubmit(formName, errorText) {
	if (isSubmit)
		document.getElementById(formName).submit();
	else
		alert(errorText);
}

// //////////////////////End of View LocalBody

function addItem(list1, list2, val, doAddVal) {

	var ulbCount = document.getElementById(list1).options.length;

	if (ulbCount == 0) {
		if (document.getElementById(list2).selectedIndex >= 0) {
			var count = 0;
			for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
				if (document.getElementById(list2).options[j].selected == true) {
					count++;
				}
			}

			if (count > 1) {
				alert("Only single ulb can be selected to create a new village.");
			} else {
				for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
					if (document.getElementById(list2).options[j].selected == true) {
						if (doAddVal) {
							$('#' + list1).append(
									"<option value=" + document.getElementById(list2)[j].value + val + ">" + document.getElementById(list2)[j].text
											+ " (" + val + ")</option>");
						} else {
							$('#' + list1).append(
									"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
											+ "</option>");
						}

					}
				}

				removeSelectedValue(list2);
			}

		}
	} else {
		alert("Only single ulb can be selected to create a new village.");

	}
}

function addItemforUrbanChangeCoverage(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'PART') {
					alert("You can not select DISTURBED Urban Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'FULL') {
					alert("You can not select DISTURBED Urban Mapped Panchayat");
				} else {
					if (doAddVal) {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + val + ">" + document.getElementById(list2)[j].text
										+ " (" + val + ")</option>");
					} else {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
										+ "</option>");
					}
					// removeSelectedValue(list2);
					// removeSelectedValueforLB(list2, val);
				}
			}
		}
		// removeSelectedValueforLB(list2, val);
		removeSelectedValueforLBDisturbed(list2, val);
	}
}

function addItemforSubDisWard(list1, list2, val, doAddVal) {

	var ulbCount = document.getElementById(list1).options.length;

	if (ulbCount == 0) {
		if (document.getElementById(list2).selectedIndex >= 0) {
			var count = 0;
			for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
				if (document.getElementById(list2).options[j].selected == true) {
					count++;
				}
			}

			/*
			 * if(count > 1){ alert("Only single ulb can be selected to create a
			 * new village."); } else{
			 */
			for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
				if (document.getElementById(list2).options[j].selected == true) {
					if (doAddVal) {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "T" + ">"
										+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
					} else {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
										+ "</option>");
					}

				}
			}

			removeSelectedValue(list2);
			// }

		}
	} else {
		alert("Only single ulb can be selected to create a new village.");

	}
}

function addItemforSubDisWardF(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region.");
				} else {
					if (doAddVal) {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "T" + ">"
										+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
					} else {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
										+ "</option>");
					}
					removeSelectedValue(list2);
				}
			}
		}
	}
}

function addItemforSubDisWardPart(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (doAddVal) {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "T" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");
				}

			}
		}
		removeSelectedValue(list2);
	}
}

function addItemforDistrictWard(list1, list2, val, doAddVal) {

	var ulbCount = document.getElementById(list1).options.length;

	if (ulbCount == 0) {
		if (document.getElementById(list2).selectedIndex >= 0) {
			var count = 0;
			for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
				if (document.getElementById(list2).options[j].selected == true) {
					count++;
				}
			}

			/*
			 * if(count > 1){ alert("Only single ulb can be selected to create a
			 * new village."); } else{
			 */
			for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
				if (document.getElementById(list2).options[j].selected == true) {
					if (doAddVal) {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "D" + ">"
										+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
					} else {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
										+ "</option>");
					}

				}
			}

			removeSelectedValue(list2);
			// }

		}
	} else {
		alert("Only single ulb can be selected to create a new village.");

	}
}

function addItemforDistrictWardPart(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		var count = 0;
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				count++;
			}
		}

		/*
		 * if(count > 1){ alert("Only single ulb can be selected to create a new
		 * village."); } else{
		 */
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (doAddVal) {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "D" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");
				}

			}
		}
		removeSelectedValue(list2);
	}
}

function addItemforSubDistrictWard(list1, list2, val, doAddVal) {

	var ulbCount = document.getElementById(list1).options.length;

	if (ulbCount == 0) {
		if (document.getElementById(list2).selectedIndex >= 0) {
			var count = 0;
			for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
				if (document.getElementById(list2).options[j].selected == true) {
					count++;
				}
			}

			/*
			 * if(count > 1){ alert("Only single ulb can be selected to create a
			 * new village."); } else{
			 */
			for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
				if (document.getElementById(list2).options[j].selected == true) {
					if (doAddVal) {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "T" + ">"
										+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
					} else {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
										+ "</option>");
					}

				}
			}

			removeSelectedValue(list2);
			// }

		}
	} else {
		alert("Only single ulb can be selected to create a new village.");

	}
}

function addItemforSubDistrictWardF(list1, list2, val, doAddVal) {

	if (document.getElementById(list2).selectedIndex >= 0) {
		var count = 0;
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				count++;
			}
		}

		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region.");
				} else {
					if (doAddVal)
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "T" + ">"
										+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
					else
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
										+ "</option>");
					removeSelectedValue(list2);
				}
			}
		}
	}
}

function addItemforDistrictWardF(list1, list2, val, doAddVal) {

	if (document.getElementById(list2).selectedIndex >= 0) {
		var count = 0;
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				count++;
			}
		}

		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region.");
				} else {
					if (doAddVal)
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "D" + ">"
										+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
					else
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
										+ "</option>");
					removeSelectedValue(list2);
				}
			}
		}
	}
}

function addItemforSubDistrictWardPart(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (doAddVal) {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "T" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");

				}
			}
		}
		removeSelectedValue(list2);
	}
}

function addItemWardVillageFinal(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region.");
				} else {
					if (doAddVal) {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "V" + ">"
										+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
					} else {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
										+ "</option>");

					}

				}
			}
		}
		removeSelectedValue(list2);
	}
}

function addItemWardVillage(list1, list2, val, doAddVal) {

	var ulbCount = document.getElementById(list1).options.length;

	if (document.getElementById(list2).selectedIndex >= 0) {
		var count = 0;
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				count++;
			}
		}

		/*
		 * if(count > 1){ alert("Only single ulb can be selected to create a new
		 * village."); }
		 */
		/* else{ */
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region.");
				} else {
					if (doAddVal) {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "V" + ">"
										+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
					} else {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
										+ "</option>");
					}
					removeSelectedValue(list2);
				}
			}
		}

		/* } */

	}

}

function addItemWardVillageF(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		var count = 0;
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				count++;
			}
		}

		/*
		 * if(count > 1){ alert("Only single ulb can be selected to create a new
		 * village."); }
		 */
		/* else{ */
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region.");
				} else {
					if (doAddVal)
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "V" + ">"
										+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
					else
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
										+ "</option>");
					removeSelectedValue(list2);

				}
			}
		}
	}

}

function addItemWardVillagePart(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (doAddVal) {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "V" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");
				}

			}
		}
		removeSelectedValue(list2);
	}

}

function removeAllforVPanchayatforWard(list1, list2, doRemoveVal) {

	/*
	 * var selObj = document.getElementById(val); var i;
	 * 
	 * for (i = 0; i < selObj.options.length; i++) { selObj.options[i].selected =
	 * true; }
	 */
	var selObj = document.getElementById("ddLgdLBVillageCAreaDestL");

	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}

	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
				}
			}
		}
		removeSelectedValue(list1);
	}
}

function removeAllSubdistrictListforWard(list1, list2, doRemoveVal) {
	// removeSelectedValueDListF(list1);
	var selObj = document.getElementById("ddLgdLBDistCAreaDestL");
	var selObj1 = document.getElementById("ddLgdLBSubDistrictSourceLatDCA");
	var selObj2 = document.getElementById("ddLgdLBSubDistrictDestLatDCA");
	var selObj3 = document.getElementById("ddLgdLBVillageSourceLatDCA");
	var selObj4 = document.getElementById("ddLgdLBVillageDestLatDCA");
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}
	for ( var i = 0; i < selObj1.options.length; i++) {
		selObj1.options[i].selected = true;
	}
	for ( var i = 0; i < selObj2.options.length; i++) {
		selObj2.options[i].selected = true;
	}
	for ( var i = 0; i < selObj3.options.length; i++) {
		selObj3.options[i].selected = true;
	}
	for ( var i = 0; i < selObj4.options.length; i++) {
		selObj4.options[i].selected = true;
	}
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
				}
			}
		}
		removeSelectedValue(list1);
		removeSelectedValueVListWard('ddLgdLBDistCAreaDestL');
		removeSelectedValueSDListWard('ddLgdLBSubDistrictSourceLatDCA');
		removeSelectedValueSDListWard('ddLgdLBSubDistrictDestLatDCA');
		removeSelectedValueVListWard('ddLgdLBVillageSourceLatDCA');
		removeSelectedValueVListWard('ddLgdLBVillageDestLatDCA');
	}
}

function removeSelectedValueVListWard(val) {
	var selObj = document.getElementById(val);
	var i;

	for (i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}

	for (i = selObj.length - 1; i >= 0; i--) {
		if (selObj.options[i].selected) {
			selObj.remove(i);
		}
	}
}

function removeSelectedValueSDListWard(val) {
	var selObj = document.getElementById(val);

	var i;

	for (i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}

	for (i = selObj.length - 1; i >= 0; i--) {
		if (selObj.options[i].selected) {
			selObj.remove(i);
		}
	}
}

function removeAllVillageListforWard(list1, list2, doRemoveVal) {

	var selObj = document.getElementById("ddLgdLBSubDistrictDestLatDCA");
	var selObj1 = document.getElementById("ddLgdLBVillageSourceLatDCA");
	var selObj2 = document.getElementById("ddLgdLBVillageDestLatDCA");

	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}
	for ( var i = 0; i < selObj1.options.length; i++) {
		selObj1.options[i].selected = true;
	}
	for ( var i = 0; i < selObj2.options.length; i++) {
		selObj2.options[i].selected = true;
	}

	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
				}
			}
		}
		removeSelectedValue(list1);
		removeSelectedValueVListWard('ddLgdLBVillageSourceLatDCA');
		removeSelectedValueVListWard('ddLgdLBVillageDestLatDCA');

	}
}

function removeAllVillageListInterforWard(list1, list2, doRemoveVal) {

	var selObj = document.getElementById("ddLgdLBInterCAreaDestL");
	var selObj1 = document.getElementById("ddLgdLBVillageSourceLatICA");
	var selObj2 = document.getElementById("ddLgdLBVillageDestLatICA");

	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}
	for ( var i = 0; i < selObj1.options.length; i++) {
		selObj1.options[i].selected = true;
	}
	for ( var i = 0; i < selObj2.options.length; i++) {
		selObj2.options[i].selected = true;
	}

	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
				}
			}
		}
		removeSelectedValue(list1);
		removeSelectedValueVListWard('ddLgdLBVillageSourceLatICA');
		removeSelectedValueVListWard('ddLgdLBVillageDestLatICA');
	}
}

/*
 * function addItemWardVillageF(list1, list2, val, doAddVal) {
 * 
 * alert("list2 "+list2+"val "+val); if
 * (document.getElementById(list2).selectedIndex >= 0) { var count = 0; for (
 * var j = 0; j < document.getElementById(list2).options.length; j++) { if
 * (document.getElementById(list2).options[j].selected == true) { count++; } }
 * 
 * 
 * if(count > 1){ alert("Only single ulb can be selected to create a new
 * village."); }
 * 
 * else{ for ( var j = 0; j < document.getElementById(list2).options.length;
 * j++) { if (document.getElementById(list2).options[j].selected == true) { if
 * (doAddVal) { $('#' + list1).append( "<option value=" +
 * document.getElementById(list2)[j].value + "_" + val + "_" + "V" + ">" +
 * document.getElementById(list2)[j].text + " (" + val + ")</option>"); } else {
 * $('#' + list1).append( "<option value=" +
 * document.getElementById(list2)[j].value + " >" +
 * document.getElementById(list2)[j].text + "</option>"); } } }
 * 
 * removeSelectedValue(list2); } } }
 */

function addItemULB(list1, list2, val, doAddVal) {

	// var ulbCount = document.getElementById(list1).options.length;

	/*
	 * if(ulbCount==0) { if(document.getElementById(list2).selectedIndex>=0) {
	 * var count = 0; for (var j = 0; j <
	 * document.getElementById(list2).options.length; j++) { if
	 * (document.getElementById(list2).options[j].selected==true) { count++; } }
	 * 
	 * if(count > 1) { alert("Only single ulb can be selected to create a new
	 * village."); } else {
	 */

	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (doAddVal) {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + ">" + document.getElementById(list2)[j].text
									+ " (" + val + ")</option>");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");
				}

			}
		}
		removeSelectedValue(list2);
	}
	if ($("#childbtn")) {
		if (val == "FULL") {
			// document.getElementById("childbtn").disabled = true;
		} else {
			document.getElementById("childbtn").disabled = false;
		}
	}

	/*
	 * } } else { alert("Only single ulb can be selected to create a new
	 * village."); }
	 */
}

function addItemULBFinalFULL(list1, list2, val, doAddVal) {

	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'PART') {
					alert("You can not select DISTURBED Urban Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'FULL') {
					alert("You can not select DISTURBED Urban Mapped Panchayat");
				} else {
					if (doAddVal) {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + ">" + document.getElementById(list2)[j].text
										+ " (" + val + ")</option>");
					} else {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
										+ "</option>");
					}
					addLandregionforULBFull();
					// removeSelectedValue(list2);
				}
			}
		}
		removeSelectedValueforLBDisturbed(list2, val);
		/*
		 * addLandregionforULBFull(); removeSelectedValue(list2);
		 */
	}
}

function addLandregionforULBFull() {
	var selObj = document.getElementById('ddLgdUrbanLBDistExistDest');
	var subdistrictCode = "";
	for (i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		subdistrictCode += selObj.options[i].value + ",";
	}

	// Conditional Check whether Logged in state is ULB operate District wise.
	if ((typeof (isDistrictLevel) != "undefined") && $.parseJSON(isDistrictLevel)) {
		lgdDwrlocalBodyService.getLocalGovtBodyforCoveredDistrictListFinalTo(subdistrictCode, {
			callback : getCoveredLandForSubDistRegionSuccessULBFin,
			errorHandler : getCoveredLandForSubDistRegionErrorULBFin
		});
	} else {
		lgdDwrlocalBodyService.getLocalGovtBodyforCoveredSubDistrictListFinalTo(subdistrictCode, {
			callback : getCoveredLandForSubDistRegionSuccessULBFin,
			errorHandler : getCoveredLandForSubDistRegionErrorULBFin
		});
	}
}

function getCoveredLandForSubDistRegionSuccessULBFin(data) {
	var fieldId = 'ddLgdSubDestListhiddenULB';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, getvalueTextULB, getnameTextULB);
}

function getvalueTextULB(data) {

	if (data.coverageType == 'F') {
		sd = data.landRegionCode + "_FULL_" + data.landRegionType;
	} else if (data.coverageType == 'P') {
		sd = data.landRegionCode + "_PART_" + data.landRegionType;
	}
	return sd;
}

function getnameTextULB(data) {
	return data.landRegionNameEnglish;
}

function getCoveredLandForSubDistRegionErrorULBFin() {
	alert("No data found!");
}

function addItemULBFinal(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'PART') {
					alert("You can not select DISTURBED Urban Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'FULL') {
					alert("You can not select DISTURBED Urban Mapped Panchayat");
				} else {
					if (doAddVal) {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + ">" + document.getElementById(list2)[j].text
										+ " (" + val + ")</option>");
					} else {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
										+ "</option>");
					}
					// removeSelectedValue(list2);
				}
			}
		}
		removeSelectedValueforLBDisturbed(list2, val);
	}

}

function addItemULBFinalSubD(list1, list2, val, doAddVal) {

	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				// Conditional Check whether Logged in state is ULB operate
				// District wise.
				if ((typeof (isDistrictLevel) != "undefined") && $.parseJSON(isDistrictLevel)) {
					if (doAddVal) {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "D" + ">"
										+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
					} else {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
										+ "</option>");
					}
				} else {
					if (doAddVal) {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "T" + ">"
										+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
					} else {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
										+ "</option>");
					}
				}

			}
		}
		removeSelectedValue(list2);
	}

}

function addItemULBFinalSubDFULL(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region.");
				} else {
					// Conditional Check whether Logged in state is ULB operate
					// District wise.
					if ((typeof (isDistrictLevel) != "undefined") && $.parseJSON(isDistrictLevel)) {
						if (doAddVal) {
							$('#' + list1).append(
									"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "D" + ">"
											+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
						} else {
							$('#' + list1).append(
									"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
											+ "</option>");
						}
					} else {
						if (doAddVal) {
							$('#' + list1).append(
									"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "T" + ">"
											+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
						} else {
							$('#' + list1).append(
									"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
											+ "</option>");
						}
					}
					removeSelectedValue(list2);
				}
			}
		}

	}

}

function addItemVillage(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (doAddVal) {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + val + ">" + document.getElementById(list2)[j].text + " ("
									+ val + ")</option>");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");
				}

			}
		}
		removeSelectedValue(list2);
	}
}

function addItemSurvey(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {

		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {

				if (doAddVal) {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + val + ">" + document.getElementById(list2)[j].text + " ("
									+ val + ")</option>");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");
				}

			}
		}
		removeSelectedValue(list2);
	}
}

function removeSelectedValue(val) {
	var elSel = document.getElementById(val);
	var i;
	for (i = elSel.length - 1; i >= 0; i--) {
		if (elSel.options[i].selected) {
			elSel.remove(i);
		}
	}
}

function removeSelectedValuevalid(val, name) {
	var elSel = document.getElementById(val);
	var i;
	for (i = elSel.length - 1; i >= 0; i--) {
		if (elSel.options[i].selected) {
			if (elSel.options[i].text == name)
				elSel.remove(i);
		}
	}
}

function removeAll8(list1, list2, doRemoveVal) {

	for ( var i = document.getElementById(list1).length - 1; i >= 0; i--) {
		document.getElementById(list1).selectedIndex = i;

		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length - 6) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + "</option>");
		removeSelectedValue(list1);
	}
	/*
	 * if (document.getElementById(list1).options.length == 0) {
	 * document.getElementById("ddLgdLBVillageCAreaSourceL").options.length = 0;
	 * document.getElementById("ddLgdLBVillageCAreaDestL").options.length = 0;
	 * document.getElementById('getHeadQuartersV').style.display = 'none'; }
	 */
}

function removeAll9(list1, list2, doRemoveVal) {

	for ( var i = document.getElementById(list1).length - 1; i >= 0; i--) {
		document.getElementById(list1).selectedIndex = i;

		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 4)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length - 6) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + "</option>");
		removeSelectedValue(list1);
	}
	if (document.getElementById(list1).options.length == 0) {
		document.getElementById("ddLgdLBInterCAreaSourceL").options.length = 0;
		document.getElementById("ddLgdLBInterCAreaDestL").options.length = 0;
		document.getElementById("ddLgdLBVillageSourceLatICA").options.length = 0;
		document.getElementById("ddLgdLBVillageDestLatICA").options.length = 0;

	}
}

function removeAll10(list1, list2, doRemoveVal) {

	for ( var i = document.getElementById(list1).length - 1; i >= 0; i--) {
		document.getElementById(list1).selectedIndex = i;

		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length - 6) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + "</option>");
		removeSelectedValue(list1);
	}
	if (document.getElementById(list1).options.length == 0) {
		document.getElementById("ddLgdLBVillageSourceLatICA").options.length = 0;
		document.getElementById("ddLgdLBVillageDestLatICA").options.length = 0;
	}
}

function removeAll11(list1, list2, doRemoveVal) {

	for ( var i = document.getElementById(list1).length - 1; i >= 0; i--) {
		document.getElementById(list1).selectedIndex = i;

		if (doRemoveVal)
			$('#' + list2).append(
					"<option value="
							+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7)
							+ " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
									.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length - 6) + "</option>");
		else
			$('#' + list2).append(
					"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + " >"
							+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + "</option>");
		removeSelectedValue(list1);
	}
	if (document.getElementById(list1).options.length == 0) {
		document.getElementById("ddLgdLBVillageSourceLatICAUmapped").options.length = 0;
		document.getElementById("ddLgdLBVillageDestLatICAUmapped").options.length = 0;
	}
}
function removeItem(list1, list2, doRemoveVal) {
	var lastindex = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++)
			if (document.getElementById(list1).options[j].selected == true)
				if (doRemoveVal) {
					if (document.getElementById(list1)[j].value.indexOf("P") > 0)
						lastindex = document.getElementById(list1)[j].value.indexOf("P");
					else if (document.getElementById(list1)[j].value.indexOf("F") > 0)
						lastindex = document.getElementById(list1)[j].value.indexOf("F");
					$('#' + list2).append("<option value=" + document.getElementById(list1)[j].value.substr(0, lastindex) // length
							// - 7)
							+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.indexOf("(")) // length
							// - 6)
							+ "</option>");
				} else
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
		removeSelectedValue(list1);

	}
	/* Added by Sushil on 01-02-2013 */
	if ($("#childbtn")) {
		document.getElementById("childbtn").disabled = false;
	}
}

/* Added by Sushil on 01-02-2013 */
function removeAll(obj1, obj2, flag) {
	if ($("#childbtn")) {
		document.getElementById("childbtn").disabled = false;
	}
}

function removeItemVillListSubListWard(list1, list2, doRemoveVal) {
	var selObj = document.getElementById("ddLgdLBInterCAreaDestL");
	var selObj1 = document.getElementById("ddLgdLBVillageSourceLatICA");
	var selObj2 = document.getElementById("ddLgdLBVillageDestLatICA");
	var selObj3 = document.getElementById("ddLgdLBVillageSourceLatDCA");
	var selObj4 = document.getElementById("ddLgdLBVillageDestLatDCA");

	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}
	for ( var i = 0; i < selObj1.options.length; i++) {
		selObj1.options[i].selected = true;
	}
	for ( var i = 0; i < selObj2.options.length; i++) {
		selObj2.options[i].selected = true;
	}
	for ( var i = 0; i < selObj3.options.length; i++) {
		selObj3.options[i].selected = true;
	}
	for ( var i = 0; i < selObj4.options.length; i++) {
		selObj4.options[i].selected = true;
	}

	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
				}
			}
		}
		removeSelectedValue(list1);
		removeSelectedValueListWard('ddLgdLBInterCAreaDestL');
		removeSelectedValueListWard('ddLgdLBVillageSourceLatDCA');
		removeSelectedValueListWard('ddLgdLBVillageDestLatDCA');
		removeSelectedValueListWard('ddLgdLBVillageSourceLatICA');
		removeSelectedValueListWard('ddLgdLBVillageDestLatICA');

	}

}

function removeItemVillList(list1, list2, doRemoveVal) {

	var selObj = document.getElementById("ddLgdLBInterCAreaDestL");
	var selObj1 = document.getElementById("ddLgdLBVillageSourceLatICA");
	var selObj2 = document.getElementById("ddLgdLBVillageDestLatICA");
	var selObj3 = document.getElementById("ddLgdLBVillageSourceLatDCA");
	var selObj4 = document.getElementById("ddLgdLBVillageDestLatDCA");
	var selObj5 = document.getElementById("ddLgdLBSubDistrictSourceLatDCA");
	var selObj6 = document.getElementById("ddLgdLBSubDistrictDestLatDCA");

	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}
	for ( var i = 0; i < selObj1.options.length; i++) {
		selObj1.options[i].selected = true;
	}
	for ( var i = 0; i < selObj2.options.length; i++) {
		selObj2.options[i].selected = true;
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

	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
				}
			}
		}
		removeSelectedValue(list1);
		removeSelectedValueListWard('ddLgdLBInterCAreaDestL');
		removeSelectedValueListWard('ddLgdLBVillageSourceLatDCA');
		removeSelectedValueListWard('ddLgdLBVillageDestLatDCA');
		removeSelectedValueListWard('ddLgdLBVillageSourceLatICA');
		removeSelectedValueListWard('ddLgdLBVillageDestLatICA');
		removeSelectedValueListWard('ddLgdLBSubDistrictSourceLatDCA');
		removeSelectedValueListWard('ddLgdLBSubDistrictDestLatDCA');

	}
}

function removeItemVillListforWard(list1, list2, doRemoveVal) {

	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
				}
			}
		}
		removeSelectedValue(list1);
	}
}

function removeSelectedValueListWard(val) {
	var selObj = document.getElementById(val);
	var i;

	for (i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
	}

	for (i = selObj.length - 1; i >= 0; i--) {
		if (selObj.options[i].selected) {
			selObj.remove(i);
		}
	}
}

function removeAllVillList(list1, list2, doRemoveVal) {
	removeSelectedValueDListF(list1);
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 7)
									+ " >" + document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");
				}
			}
		}
		removeSelectedValue(list1);
	}
}

function removeItemvillageModify(list1, list2, doRemoveVal) {

	var count = 0;
	if (document.getElementById(list1).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
			if (document.getElementById(list1).options[j].selected == true) {
				count++;
				if (doRemoveVal) {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 7) + " >"
									+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
									+ "</option>");
				} else {
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text + "</option>");

				}

			}

		}
		removeSelectedValue(list1);

		getHeadQuarterListFinalWithoutMappedLBVPBackButton(list1, 'V');

		/*
		 * if (document.getElementById(list1).options.length == 0) {
		 * document.getElementById('getHeadQuartersV').style.display = 'none'; }
		 * else { var list = document.getElementById(list1); if
		 * (list.options.length == 1) {
		 * document.getElementById('getHeadQuartersV').style.display = 'none'; }
		 * if (list.options.length > 1) {
		 * document.getElementById('getHeadQuartersV').style.display = 'block';
		 * getHeadQuarterListModify(list1, "V"); } }
		 */
	}
}

function listbox_move(listID, direction) {

	var listbox = document.getElementById(listID);
	var selIndex = listbox.selectedIndex;

	if (-1 == selIndex) {
		alert("Please select an option to move.");
		return;
	}

	var increment = -1;
	if (direction == 'up')
		increment = -1;
	else
		increment = 1;

	if ((selIndex + increment) < 0 || (selIndex + increment) > (listbox.options.length - 1)) {
		return;
	}

	var selValue = listbox.options[selIndex].value;
	var selText = listbox.options[selIndex].text;
	listbox.options[selIndex].value = listbox.options[selIndex + increment].value
	listbox.options[selIndex].text = listbox.options[selIndex + increment].text

	listbox.options[selIndex + increment].value = selValue;
	listbox.options[selIndex + increment].text = selText;

	listbox.selectedIndex = selIndex + increment;
}

function listbox_moveacross(sourceID, destID, type) {
	var check = true;
	var src = document.getElementById(sourceID);
	var dest = document.getElementById(destID);
	/* added by sushil on 01-02-2013 */
	if (type != null && type.id == "btnAdd") {
		check = checkValidPart(sourceID);
	}
	if (check != null && check != false) {
		for ( var count = 0; count < src.options.length; count++) {
			if (src.options[count].selected == true) {
				var option = src.options[count];
				check = true;
				var newOption = document.createElement("option");
				newOption.value = option.value;
				newOption.text = option.text;
				newOption.selected = true;
				try {
					dest.add(newOption, null); // Standard
					src.remove(count, null);
				} catch (error) {
					dest.add(newOption); // IE only
					src.remove(count);
				}
				count--;
			}
		}

		if (check != true) {
			if (type == 'D')
				alert("Please select valid district to shift");
			else if (type == 'B')
				alert("Please select valid block to shift");
			else if (type == 'T')
				alert("Please select valid subdistrict to shift");
			else if (type == 'V')
				alert("Please select valid village to shift");
		}
		check = false;
	}
}

function validateforLATIandLONGI(event) {
	var key;
	// alert("kamlesh"+event.which);

	key = event.keyCode;
	if (key == 0)
		key = event.which;

	if ((key >= 48) && (key <= 58) || (key >= 65) && (key <= 90) || (key >= 97) && (key <= 122) || (key == 32)) {
		return true;
	} else {
		alert("Please use [A-Z],[a-z], space ,numeric only");
		event.returnValue = false;
		return false;
	}
}

/** Code for adding GIS Node starts* */
function hideGISComponentOnLoad() {
	$("#longitude_error").hide();
	$("#latitude_error").hide();
	$("#fileMapUpLoad_error").hide();
	$("#ddDistrict_error").hide();
	$("#ddSubdistrict_error").hide();
	$("#OfficialAddress_error").hide();
	$("#chkcvillage_error").hide();

	// /////// for govt order page///////

	$("#OrderNo_error").hide();
	$("#OrderDate_error").hide();
	$("#EffectiveDate_error").hide();
	$("#templateList_error").hide();
	$("#category_error").hide();
	$("#OrderNo_msg").hide();
	$("#OrderDate_msg").hide();
	$("#EffectiveDate_msg").hide();
	$("#GazPubDate_msg").hide();

}
function deleteRow(row) {
	var i = row.parentNode.parentNode.rowIndex;
	document.getElementById('maptabidF').deleteRow(i);
}
function addGISnodes() {
	var table = document.getElementById('maptabidF');
	var rowCount = table.rows.length;
	var newRow = document.getElementById('maptabidF').insertRow(rowCount);
	var c4 = newRow.insertCell(0);
	c4.width = '50%';
	var c3 = newRow.insertCell(0);
	c3.width = '20%';
	var c2 = newRow.insertCell(0);
	c2.width = '20%';
	var c1 = newRow.insertCell(0);
	c1.width = '10%';
	c1.innerHTML = "<td><input type='text' name='latitude' id='latitude' class='frmfield' /></td>";
	c2.innerHTML = "<td ><input type='text'  name='longitude' id='longitude' class='frmfield' /></td>";
	c3.innerHTML = "<td ><img src='images/delete.png' onclick='deleteRow(this);' /></td>";
	c4.innerHTML = "<td align='right'></td>";
	/*
	 * var tmptextLati = new Array(); var tempchkLongi = new Array();
	 * 
	 * if (dynstart == 0) { dynstart = t; } dynend = t; for ( var j = dynstart;
	 * j < dynend; j++) { if (document.getElementById("latitude" + j)) { if
	 * (document.getElementById("latitude" + j) != null &&
	 * document.getElementById("longitude" + j) != null) tmptextLati[j] =
	 * document .getElementById("latitude" + j).value; tempchkLongi[j] =
	 * document.getElementById("longitude" + j).value; } }
	 * document.getElementById("addgisnodes").innerHTML += "<div id=div" + t + "><table
	 * width='491' class='tbl_no_brdr'><tr><td width='150'><input type='text'
	 * name='latitude' id='latitude" + t + "' class='frmfield'></td><td width='10' align='right'></td>" + "<td width='161'><input
	 * type='text' name='longitude' id='longitude" + t + "' class='frmfield'></td><td width='170' align='center'><img
	 * src='images/delete.png' onclick='div"+ t + ".parentNode.removeChild(div" +
	 * t + ")' /></td></tr></table></div>"; for ( var j = dynstart; j <
	 * dynend; j++) { if (document.getElementById("latitude" + j)) { if
	 * (document.getElementById("latitude" + j) != null &&
	 * document.getElementById("longitude" + j) != null)
	 * document.getElementById("latitude" + j).value = tmptextLati[j];
	 * document.getElementById("longitude" + j).value = tempchkLongi[j]; } }
	 * t++;
	 */
}

/** Code for adding GIS Node ends* */

function addItemforVillage(list1, list2, val, doAddVal, type) {

	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++)
			if (document.getElementById(list2).options[j].selected == true)
				if (doAddVal)
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + ">" + document.getElementById(list2)[j].text
									+ " (" + val + ")</option>");

				else
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");

		removeSelectedValue(list2);
		getHeadQuarterList(list1, type);
	}
}

function addItemforVillageG(list1, list2, list3, val, doAddVal, type) {

	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++)
			if (document.getElementById(list2).options[j].selected == true)
				if (doAddVal)
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "V" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
				else
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");

		removeSelectedValue(list2);
		document.getElementById('getHeadQuartersV').style.visibility = 'visible';
		document.getElementById('getHeadQuartersV').style.display = 'block';
		getHeadQuarterList(list1, list3, type);
	}
}

function addItemforVillageGFULL(list1, list2, list3, val, doAddVal, type) {

	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region.");
				} else {
					if (doAddVal)
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "V" + ">"
										+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
					else
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
										+ "</option>");

					removeSelectedValue(list2);
					document.getElementById('getHeadQuartersV').style.visibility = 'visible';
					document.getElementById('getHeadQuartersV').style.display = 'block';
					getHeadQuarterList(list1, list3, type);
				}
			}
		}
	}
}

function addItemforSubDistrict(list1, list2, list3, val, doAddVal, type) {

	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++)
			if (document.getElementById(list2).options[j].selected == true)
				if (doAddVal)
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "T" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
				else
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");

		document.getElementById('getHeadQuartersT').style.visibility = 'visible';
		document.getElementById('getHeadQuartersT').style.display = 'block';
		removeSelectedValue(list2);
		getHeadQuarterList(list1, list3, type);
	}
}

function addItemforSubDistrictforFULL(list1, list2, list3, val, doAddVal, type) {

	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('PART') && val == 'FULL') {
					alert("You can not select FULL of Partially Mapped Land Region");
				} else {
					if (doAddVal)
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "T" + ">"
										+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
					else
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
										+ "</option>");

					document.getElementById('getHeadQuartersT').style.visibility = 'visible';
					document.getElementById('getHeadQuartersT').style.display = 'block';
					removeSelectedValue(list2);
					getHeadQuarterList(list1, list3, type);
				}
			}
		}
	}
}

function addItemforSubDistrictFinal(list1, list2, val, doAddVal, type) {

	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++)
			if (document.getElementById(list2).options[j].selected == true)
				if (doAddVal)
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "T" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");
				else
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");

		document.getElementById('getHeadQuartersT').style.visibility = 'visible';
		document.getElementById('getHeadQuartersT').style.display = 'block';
		removeSelectedValue(list2);
		getHeadQuarterList(list1, list3, type);
	}
}

function addItemforVillageLocalBody(list1, list2, val, doAddVal, type) {

	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++)
			if (document.getElementById(list2).options[j].selected == true)
				if (doAddVal)
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "D" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");

				else
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");

		removeSelectedValue(list2);
		getHeadQuarterList(list1, type);
	}
}

function addItemforVillageModify(list1, list2, val, doAddVal, type) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++)
			if (document.getElementById(list2).options[j].selected == true)
				if (doAddVal)
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + val + ">" + document.getElementById(list2)[j].text + " ("
									+ val + ")</option>");
				else
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");

		removeSelectedValue(list2);
		var list = document.getElementById(list1);
		if (list.options.length > 1) {
			var headquarter = "getHeadQuarters" + type;
			document.getElementById(headquarter).style.display = 'block';
			getHeadQuarterListModify(list1, type);
		}
	}
}
function getHeadQuarterListModify(list1, lb) {
	var list = document.getElementById(list1);
	var headquarter = "getHeadQuarters" + lb;
	document.getElementById("getHeadQuartersD").innerHTML = '';
	document.getElementById("getHeadQuartersT").innerHTML = '';
	document.getElementById("getHeadQuartersV").innerHTML = '';
	if (list.options.length > 1) {
		var name;
		if (lb == 'D')
			name = 'District';
		else if (lb == 'T')
			name = 'Sub District';
		else if (lb == 'V')
			name = 'Village';

		document.getElementById(headquarter).innerHTML += "<table width='600' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='8'>Sno</td><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
				+ name + " List" + "" + "</td>" + "" + "</tr>";

		for ( var j = 0; j < list.options.length; j++) {
			var name;
			if (list.options[j].text.match("(FULL)")) {

				name = list.options[j].text.replace("(FULL)", "");
			} else if (list.options[j].text.match("(PART)"))
				name = list.options[j].text.replace("(PART)", "");
			document.getElementById(headquarter).innerHTML += "<tr class='tblRowB'><td>&nbsp;&nbsp;"
					+ (j + 1)
					+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td width='50'><input type='checkbox' name='headQuarterCode' id=chk"
					+ j
					+ " value="
					+ list.options[j].value
					+ " onclick=SingleSelect(this,'"
					+ list1
					+ "','"
					+ lb
					+ "'); ></input></td>"
					+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<td width='200'><form:label path='headQuarterName' id=lbl"
					+ j + ">" + name + "</form:label></td><br/>" + "</tr>";
		}

		document.getElementById(headquarter).innerHTML += "</table>";

	}
}
// var listFinal=[];
function getHeadQuarterListFinal(list1, list3, list4, lb) {
	var list = document.getElementById(list1);
	var list2 = document.getElementById(list3);
	var list3 = document.getElementById(list4);

	var innerHTMLText = '';
	var listFinal = [];
	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
	for (i = 0; i < list2.length; i++) {
		listFinal[listFinal.length] = list2[i];
	}
	for (i = 0; i < list3.length; i++) {
		listFinal[listFinal.length] = list3[i];
	}

	var headquarter = "getHeadQuarters" + lb;
	document.getElementById("getHeadQuartersD").innerHTML = '';
	document.getElementById("getHeadQuartersT").innerHTML = '';
	document.getElementById("getHeadQuartersV").innerHTML = '';
	if (listFinal.length >= 1) {
		var name;
		if (lb == 'D')
			name = 'District';
		else if (lb == 'T')
			name = 'Sub District';
		else if (lb == 'V')
			name = 'Village';

		innerHTMLText += "<div><table width='1200' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
				+ name + " List" + "" + "</td>" + "" + "</tr>";

		for ( var j = 0; j < listFinal.length; j++) {
			var name;

			if (listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text.replace("(FULL)", "");
			} else if (listFinal[j].text.match("(PART)")) {
				name = listFinal[j].text.replace("(PART)", "");
			}
			if (!listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text;
			}

			/*
			 * innerHTMLText += "<tr class='tblRowB'><td width='50'><input
			 * type='checkbox' name='headQuarterCode' id=chk"+j +"
			 * value="+listFinal[j].value+"
			 * onclick='javascript:SingleSelectFinal(this);' /></td>" + "<td width='200'><form:label
			 * path='headQuarterName' id=lbl"+j+">"+name+"</form:label></td>" + "</tr>";
			 */
			innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " value="
					+ listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb + "'); /></td>"
					+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td>" + "</tr>";
		}

		innerHTMLText += "</table></div>";
		document.getElementById(headquarter).innerHTML += innerHTMLText;
	}

}

function getHeadQuarterListFinalWithoutMapped(list1, lb) {
	var list = document.getElementById(list1);
	// var list2=document.getElementById(list3);
	// var list3=document.getElementById(list4);

	var innerHTMLText = '';
	var listFinal = [];
	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
	/*
	 * for(i=0;i<list2.length;i++) { listFinal[listFinal.length]=list2[i]; }
	 * for(i=0;i<list3.length;i++) { listFinal[listFinal.length]=list3[i]; }
	 */

	var headquarter = "getHeadQuarters" + lb;
	document.getElementById("getHeadQuartersD").innerHTML = '';
	document.getElementById("getHeadQuartersT").innerHTML = '';
	document.getElementById("getHeadQuartersV").innerHTML = '';
	if (listFinal.length >= 1) {
		var name;
		if (lb == 'D')
			name = 'District';
		else if (lb == 'T')
			name = 'Sub District';
		else if (lb == 'V')
			name = 'Village';

		innerHTMLText += "<div><table width='1200' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
				+ name + " List" + "" + "</td>" + "" + "</tr>";

		for ( var j = 0; j < listFinal.length; j++) {
			var name;

			if (listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text.replace("(FULL)", "");
			} else if (listFinal[j].text.match("(PART)")) {
				name = listFinal[j].text.replace("(PART)", "");
			}
			if (!listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text;
			}

			/*
			 * innerHTMLText += "<tr class='tblRowB'><td width='50'><input
			 * type='checkbox' name='headQuarterCode' id=chk"+j +"
			 * value="+listFinal[j].value+"
			 * onclick='javascript:SingleSelectFinal(this);' /></td>" + "<td width='200'><form:label
			 * path='headQuarterName' id=lbl"+j+">"+name+"</form:label></td>" + "</tr>";
			 */
			innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " value="
					+ listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb + "'); /></td>"
					+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td>" + "</tr>";
		}

		innerHTMLText += "</table></div>";
		document.getElementById(headquarter).innerHTML += innerHTMLText;
	}

}

/*
 * function SingleSelectFinal(current) { if(current.checked) { for(var i=0; i<
 * listFinal.length; i++) { var chk='chk'+i;
 * if(document.getElementById(chk).checked==true) {
 * document.getElementById(chk).checked=false; } } } current.checked=true; }
 */
function SingleSelectFinal(current, list) {
	if (current.checked) {
		for ( var i = 0; i < list.length; i++) {
			var chk = 'chk' + i;
			if (document.getElementById(chk).checked == true) {
				document.getElementById(chk).checked = false;
			}
		}
	}
	current.checked = true;
}

function getHeadQuarterList(list1, list3, lb) {
	var list = document.getElementById(list1);
	var list1 = document.getElementById(list3);
	var list2 = document.getElementById('ddLgdLBInterCAreaDestLUmapped');

	var innerHTMLText = '';
	var listFinal = [];
	for ( var i = 0; i < list.length; i++) {
		listFinal[listFinal.length] = list[i];
	}
	for (i = 0; i < list1.length; i++) {
		listFinal[listFinal.length] = list1[i];
	}
	for (i = 0; i < list2.length; i++) {
		listFinal[listFinal.length] = list2[i];
	}
	var headquarter = "getHeadQuarters" + lb;
	document.getElementById("getHeadQuartersD").innerHTML = '';
	document.getElementById("getHeadQuartersT").innerHTML = '';
	document.getElementById("getHeadQuartersV").innerHTML = '';
	// if(list.options.length > 1)
	if (listFinal.length >= 1) {
		var name;
		if (lb == 'D')
			name = 'District';
		else if (lb == 'T')
			name = 'Sub District';
		else if (lb == 'V')
			name = 'Village';

		innerHTMLText += "<div><table width='1200' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
				+ name + " List" + "" + "</td>" + "" + "</tr>";

		for ( var j = 0; j < listFinal.length; j++) {
			var name;

			if (listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text.replace("(FULL)", "");
			} else if (listFinal[j].text.match("(PART)")) {
				name = listFinal[j].text.replace("(PART)", "");
			}
			if (!listFinal[j].text.match("(FULL)")) {
				name = listFinal[j].text;
			}

			/*
			 * innerHTMLText += "<tr class='tblRowB'><td width='50'><input
			 * type='checkbox' name='headQuarterCode' id=chk"+j +"
			 * value="+listFinal[j].value+"
			 * onclick='javascript:SingleSelectFinal(this);' /></td>" + "<td width='200'><form:label
			 * path='headQuarterName' id=lbl"+j+">"+name+"</form:label></td>" + "</tr>";
			 */
			innerHTMLText += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk" + j + " value="
					+ listFinal[j].value + " onclick=javascript:SingleSelectFinal(this,'" + listFinal + "','" + lb + "'); /></td>"
					+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td>" + "</tr>";
		}
		innerHTMLText += "</table></div>";
		document.getElementById(headquarter).innerHTML += innerHTMLText;
	}
}

function getHeadQuarterListFF(list1, lb) {
	var list = document.getElementById(list1);
	var headquarter = "getHeadQuarters" + lb;
	document.getElementById("getHeadQuartersD").innerHTML = '';
	document.getElementById("getHeadQuartersT").innerHTML = '';
	document.getElementById("getHeadQuartersV").innerHTML = '';
	if (list.options.length >= 1) {
		var name;
		if (lb == 'D')
			name = 'District';
		else if (lb == 'T')
			name = 'Sub District';
		else if (lb == 'V')
			name = 'Village';

		document.getElementById(headquarter).innerHTML += "<div><table width='1200' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
				+ name + " List" + "" + "</td>" + "" + "</tr>";

		for ( var j = 0; j < list.options.length; j++) {
			var name;
			name = list.options[j].text;
			if (!list.options[j].text.match("(FULL)")) {
				// name = list.options[j].text.replace("(FULL)","");
				document.getElementById(headquarter).innerHTML += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk"
						+ j
						+ " value="
						+ list.options[j].value
						+ " onclick=SingleSelect(this,'"
						+ list1
						+ "','"
						+ lb
						+ "'); ></input></td>"
						+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td>" + "</tr>";
			}
		}

		document.getElementById(headquarter).innerHTML += "</table></div>";
	}
}

function SingleSelect(current, list) {
	if (current.checked) {
		for ( var i = 0; i < document.getElementById(list).options.length; i++) {
			var chk = 'chk' + i;

			if (document.getElementById(chk).checked == true) {
				document.getElementById(chk).checked = false;
			}

		}

	}
	current.checked = true;
}

function refreshDropdownList(fieldId) {
	dwr.util.removeAllOptions(fieldId);
}
function getGisNodesforLB() {
	if (document.getElementById('txtlatitude').value != '') {
		var gisList = document.getElementById('txtlatitude').value.split(':');
		// i=gisList.length;

		document.getElementById('txtlatitude').value = gisList[0].split(',')[0];
		document.getElementById('txtLongitude').value = gisList[0].split(',')[1];

		for ( var k = 1; k < gisList.length; k++) {
			addgisnodes1();
			document.getElementById('lati' + k).value = gisList[k].split(',')[0];
			document.getElementById('longi' + k).value = gisList[k].split(',')[1];
		}
	}
}

// /////////function for Alert Task//////////
function chkCorrectionOnLoad() {
	$("#OrderNo_error").hide();
	$("#OrderDate_error").hide();
	$("#EffectiveDate_error").hide();
	// $("#filGovernmentOrder_error").hide();
}

function validateCorrectionAlert() {

	var errors = new Array();
	var error = false;

	if (!validateOrdeNo()) {
		errors[0] = vlidateOnblur('OrderNo', '1', '15', 'c');
	}
	if (errors[0] == true) {
		error = true;
	}

	if (!validateOrdeDate()) {
		errors[1] = vlidateOnblur('OrderDate', '1', '15', 'c');
	}
	if (errors[1] == true) {
		error = true;
	}

	if (!validateEffecDate()) {
		errors[2] = vlidateOnblur('EffectiveDate', '1', '15', 'c');
	}
	if (errors[2] == true) {
		error = true;
	}
	/*
	 * if(!validateSFile()){ errors[4] =
	 * vlidateOnblur('filGovernmentOrder','1','15','c'); } if(errors[4]==true){
	 * error = true; }
	 */

	/*
	 * for ( var i = 0; i < 5; i++) {
	 * 
	 * if (errors[i] == true) {
	 * 
	 * error = true; break; } }
	 */

	if (error == true) {

		showClientSideError();

		return false;
	} else {
		return true;
	}
}

function captchaResetImage(imageId, focusId) {

	var img = document.getElementById(imageId);
	img.src = 'jcaptcha?v' + new Date().getTime();
	var txtValue = document.getElementById(focusId);
	txtValue.focus();
}

function addItemVillageFinal(list1, list2, val, doAddVal) {

	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++)
			if (document.getElementById(list2).options[j].selected == true)
				if (doAddVal)
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + val + ">" + document.getElementById(list2)[j].text + " ("
									+ val + ")</option>");
				else
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");
		removeSelectedValue(list2);
	}
}

function addItemVillageFinalS(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'PART') {
					alert("You can not select DISTURBED Village Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'FULL') {
					alert("You can not select DISTURBED Village Mapped Panchayat");
				} else {
					if (doAddVal)
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + ">" + document.getElementById(list2)[j].text
										+ " (" + val + ")</option>");
					else
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
										+ "</option>");
					// removeSelectedValue(list2);
				}
			}
		}
		// removeSelectedValue(list2);
		removeSelectedValueforLBDisturbed(list2, val);
	}
}

function addItemVillageFinalSInter(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'PART') {
					alert("You can not select DISTURBED Intermediate Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'FULL') {
					alert("You can not select DISTURBED Intermediate Mapped Panchayat");
				} else {
					if (doAddVal)
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + ">" + document.getElementById(list2)[j].text
										+ " (" + val + ")</option>");
					else
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
										+ "</option>");

				}
			}
		}
		// removeSelectedValue(list2);
		removeSelectedValueforLBDisturbed(list2, val);
	}
}

function addItemVillageFinalSFULL(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'PART') {
					alert("You can not select DISTURBED Intermediate Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'FULL') {
					alert("You can not select DISTURBED Intermediate Mapped Panchayat");
				} else {
					if (doAddVal)
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + ">" + document.getElementById(list2)[j].text
										+ " (" + val + ")</option>");
					else
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
										+ "</option>");
					addLandregionforInterFull();
					// removeSelectedValue(list2);
				}
			}
		}
		// removeSelectedValue(list2);
		removeSelectedValueforLBDisturbed(list2, val);
	}
}

function addLandregionforInterFull() {
	var selObj = document.getElementById('ddLgdLBInterPDestList');
	var subdistrictCode = "";
	for (i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		subdistrictCode += selObj.options[i].value + ",";
	}

	lgdDwrlocalBodyService.getLocalGovtBodyforCoveredSubDistrictListInterTo(subdistrictCode, {
		callback : getCoveredLandForSubDistRegionSuccessFin,
		errorHandler : getCoveredLandForSubDistRegionErrorFin
	});

}

function getCoveredLandForSubDistRegionSuccessFin(data) {
	var fieldId = 'ddLgdInterSubDestListhidden';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, getvalueTextInter, getnameTextInter);
	getHeadQuarterSubDistListFF('ddLgdInterSubDestListhidden', 'T');
}

function getvalueTextInter(data) {

	if (data.coverageType == 'F') {
		sd = data.landRegionCode + "_FULL_" + data.landRegionType;
	} else if (data.coverageType == 'P') {
		sd = data.landRegionCode + "_PART_" + data.landRegionType;
	}
	return sd;
}

function getnameTextInter(data) {

	var sd1 = null;
	if (data.coverageType == 'F') {
		sd1 = data.landRegionNameEnglish + "(FULL)";
	} else if (data.coverageType == 'P') {
		sd1 = data.landRegionNameEnglish + "(PART)";
	}
	return sd1;
}

function getCoveredLandForSubDistRegionErrorFin() {
	alert("No data found!");
}

function addItemVillageFinalFULL(list1, list2, val, doAddVal) {
	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'PART') {
					alert("You can not select DISTURBED Village Mapped Panchayat");
				} else if (document.getElementById(list2).options[j].text.match('DISTURBED') && val == 'FULL') {
					alert("You can not select DISTURBED Village Mapped Panchayat");
				} else {
					if (doAddVal)
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + "_" + val + ">" + document.getElementById(list2)[j].text
										+ " (" + val + ")</option>");
					else
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text
										+ "</option>");
					addLandregionforVillageFull();
					// removeSelectedValue(list2);
				}
			}
		}
		// addLandregionforVillageFull();
		// removeSelectedValue(list2);
		removeSelectedValueforLBDisturbed(list2, val);
	}
}
function addLandregionforVillageFull() {
	var selObj = document.getElementById('ddLgdLBVillageDestAtVillageCA');
	var villageCode = "";
	for (i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		villageCode += selObj.options[i].value + ",";
	}

	lgdDwrlocalBodyService.getLocalGovtBodyforCoveredVillageListFinalTo(villageCode, {
		callback : getCoveredLandForVillagetRegionSuccessFin,
		errorHandler : getCoveredLandForVillageRegionErrorFin
	});

}

function getCoveredLandForVillagetRegionSuccessFin(data) {
	var fieldId = 'ddLgdInterVillageListhidden';
	var valueText = 'landRegionCode';
	var nameText = 'landRegionNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, getvalueText, getnameText);
	getHeadQuarterVillageListFF('ddLgdInterVillageListhidden', 'V');
}

function getvalueText(data) {

	if (data.coverageType == 'F') {
		sd = data.landRegionCode + "_FULL_" + data.landRegionType;
	} else if (data.coverageType == 'P') {
		sd = data.landRegionCode + "_PART_" + data.landRegionType;
	}
	return sd;
}

function getnameText(data) {
	var sd1 = null;
	if (data.coverageType == 'F') {
		sd1 = data.landRegionNameEnglish + "(FULL)";
	} else if (data.coverageType == 'P') {
		sd1 = data.landRegionNameEnglish + "(PART)";
	}
	return sd1;
}

function getCoveredLandForVillageRegionErrorFin() {
	alert("No data found!");
}

function getHeadQuarterVillageListFF(list1, lb) {
	var list = document.getElementById(list1);
	var headquarter = "getHeadQuarters" + lb;
	document.getElementById("getHeadQuartersD").innerHTML = '';
	document.getElementById("getHeadQuartersT").innerHTML = '';
	document.getElementById("getHeadQuartersV").innerHTML = '';
	if (list.options.length >= 1) {
		var name;
		if (lb == 'D')
			name = 'District';
		else if (lb == 'T')
			name = 'Sub District';
		else if (lb == 'V')
			name = 'Village';

		document.getElementById(headquarter).innerHTML += "<div><table width='1200' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
				+ name + " List" + "" + "</td>" + "" + "</tr>";

		for ( var j = 0; j < list.options.length; j++) {
			var name;
			name = list.options[j].text;
			if (!list.options[j].text.match("(FULL)")) {
				// name = list.options[j].text.replace("(FULL)","");
				document.getElementById(headquarter).innerHTML += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk"
						+ j
						+ " value="
						+ list.options[j].value
						+ " onclick=SingleSelect(this,'"
						+ list1
						+ "','"
						+ lb
						+ "'); ></input></td>"
						+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td>" + "</tr>";
			}
		}

		document.getElementById(headquarter).innerHTML += "</table></div>";
	}
}

function getHeadQuarterSubDistListFF(list1, lb) {
	var list = document.getElementById(list1);
	var headquarter = "getHeadQuarters" + lb;
	document.getElementById("getHeadQuartersD").innerHTML = '';
	document.getElementById("getHeadQuartersT").innerHTML = '';
	document.getElementById("getHeadQuartersV").innerHTML = '';
	if (list.options.length >= 1) {
		var name;
		if (lb == 'D')
			name = 'District';
		else if (lb == 'T')
			name = 'Sub District';
		else if (lb == 'V')
			name = 'Village';

		document.getElementById(headquarter).innerHTML += "<div><table width='1200' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='50'>Select HeadQuarter</td><td width='200'>Contributing "
				+ name + " List" + "" + "</td>" + "" + "</tr>";

		for ( var j = 0; j < list.options.length; j++) {
			var name;
			name = list.options[j].text;
			if (!list.options[j].text.match("(FULL)")) {
				// name = list.options[j].text.replace("(FULL)","");
				document.getElementById(headquarter).innerHTML += "<tr class='tblRowB'><td width='50'><input type='radio' name='headQuarterCode' id=chk"
						+ j
						+ " value="
						+ list.options[j].value
						+ " onclick=SingleSelect(this,'"
						+ list1
						+ "','"
						+ lb
						+ "'); ></input></td>"
						+ "<td width='200'><form:label path='headQuarterName' id=lbl" + j + ">" + name + "</form:label></td>" + "</tr>";
			}
		}

		document.getElementById(headquarter).innerHTML += "</table></div>";
	}
}

function addItemSubDistFinalLocalBody(list1, list2, val, doAddVal) {

	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++)
			if (document.getElementById(list2).options[j].selected == true)
				if (doAddVal)
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "T" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");

				else
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");
		removeSelectedValue(list2);
	}
}

function addItemVillageFinalLocalBodyPan(list1, list2, val, doAddVal) {

	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++)
			if (document.getElementById(list2).options[j].selected == true)
				if (doAddVal)
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "V" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");

				else
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");
		removeSelectedValue(list2);
	}
}

function addItemVillageFinalLocalBody(list1, list2, val, doAddVal) {

	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++)
			if (document.getElementById(list2).options[j].selected == true)
				if (doAddVal)
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + "_" + val + "_" + "D" + ">"
									+ document.getElementById(list2)[j].text + " (" + val + ")</option>");

				else
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");
		removeSelectedValue(list2);
	}
}

function addItemPC(list1, list2, val, doAddVal) {

	// var ulbCount = document.getElementById(list1).options.length;

	/*
	 * if(ulbCount==0) { if(document.getElementById(list2).selectedIndex>=0) {
	 * var count = 0; for (var j = 0; j <
	 * document.getElementById(list2).options.length; j++) { if
	 * (document.getElementById(list2).options[j].selected==true) { count++; } }
	 * 
	 * if(count > 1) { alert("Only single ulb can be selected to create a new
	 * village."); } else {
	 */

	if (document.getElementById(list2).selectedIndex >= 0) {
		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
			if (document.getElementById(list2).options[j].selected == true) {
				if (doAddVal) {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + val + ">" + document.getElementById(list2)[j].text + " ("
									+ val + ")</option>");
				} else {
					$('#' + list1).append(
							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");
				}

			}
		}
		removeSelectedValue(list2);
	}

	/*
	 * } } else { alert("Only single ulb can be selected to create a new
	 * village."); }
	 */
}
function hideMessageOnKeyPress(id) {
	if (document.getElementById(id).value != "") {
		$("#" + id + "_msg").fadeOut(1000);
	}
}

function validateCordination(event, id) {
	var key;
	key = event.keyCode;

	if (key == 0)
		key = event.which;

	if (key != 8 && key != 46) {
		var cord = document.getElementById(id).value;
		if (key != 46 && (key >= 48) && (key <= 58) && cord.length <= 2)
			cord = cord + String.fromCharCode(key);
		var f = cord.indexOf(".");

		if (f < 0) {
			if ((key >= 48) && (key <= 58) || (key == 46)) {

				if (key == 46 || (cord.length > 1 && key != 46)) {

					var cordi = parseInt(cord);
					if (!(cordi >= 6 && cordi <= 38) && id == 'latitude') {
						alert("Please Enter [6-38] Range value in latitude ");
						event.returnValue = false;
						return false;
					}

					if (!(cordi >= 32 && cordi <= 98) && id == 'longitude') {
						alert("Please Enter [32-98] Range value in longitude ");
						event.returnValue = false;
						return false;
					}
				}

			} else {
				alert("Please use [0-9],.(DOT)");
				event.returnValue = false;
				return false;
			}
		}

		if (key == 46 && f >= 0) {
			alert("Please use only one decimal");
			event.returnValue = false;
			return false;
		}

		if (f >= 0) {
			var subcord = cord.substr((f + 1), cord.length);
			if (subcord.length < 3 && !((key >= 48) && (key <= 58))) {
				alert("Please use only [0-9]");
				event.returnValue = false;
				return false;
			}
			if (subcord.length >= 3 && !(key == 119 || key == 115 || key == 110 || key == 101 || key == 87 || key == 83 || key == 78 || key == 69)) {
				alert("Please Use only [N,W,S,E]");
				event.returnValue = false;
				return false;
			}
		}
	}

	return true;
}

function validateCordinationLatandLongi(event, id, name) {
	var key;
	key = event.keyCode;

	if (key == 0)
		key = event.which;

	if (key != 8 && key != 46) {
		var cord = document.getElementById(id).value;
		if (key != 46 && (key >= 48) && (key <= 58) && cord.length <= 2)
			cord = cord + String.fromCharCode(key);
		var f = cord.indexOf(".");

		if (f < 0) {
			if ((key >= 48) && (key <= 58) || (key == 46)) {

				if (key == 46 || (cord.length > 1 && key != 46)) {

					var cordi = parseInt(cord);
					if (!(cordi >= 6 && cordi <= 38) && name == 'lati') {
						alert("Please Enter [6-38] Range value in latitude ");
						event.returnValue = false;
						return false;
					}

					if (!(cordi >= 32 && cordi <= 98) && name == 'longi') {
						alert("Please Enter [32-98] Range value in longitude ");
						event.returnValue = false;
						return false;
					}
				}

			} else {
				alert("Please use [0-9],.(DOT)");
				event.returnValue = false;
				return false;
			}
		}

		if (key == 46 && f >= 0) {
			alert("Please use only one decimal");
			event.returnValue = false;
			return false;
		}

		if (f >= 0) {
			var subcord = cord.substr((f + 1), cord.length);
			if (subcord.length < 3 && !((key >= 48) && (key <= 58))) {
				alert("Please use only [0-9]");
				event.returnValue = false;
				return false;
			}
			if (subcord.length >= 3 && !(key == 119 || key == 115 || key == 110 || key == 101 || key == 87 || key == 83 || key == 78 || key == 69)) {
				alert("Please Use only [N,W,S,E]");
				event.returnValue = false;
				return false;
			}
		}
	}

	return true;
}

function validateSpecialCharactersWardNLocal(value) {

	var retVal = true;
	retVal = validateSpecialCharacters(value);
	if (retVal == false) {
		alert("Ward Name in Local contains invalid characters");
		var obj = document.getElementById('wardnamdlocal');
		obj.value = "";
		return false;
	} else {
		return true;
	}
}

function validateSpecialCharactersLBName(value) {

	var retVal = true;
	retVal = validateSpecialCharacters(value);
	if (retVal == false) {
		alert("LocalBody Name in Local contains invalid characters");
		var obj = document.getElementById('localBodyNameInLocal');
		var obj1 = document.getElementById('localBodyNameInLcl');
		if (obj != null) {
			obj.value = "";
		}
		if (obj1 != null) {
			obj1.value = "";
		}
		return false;
	} else {
		return true;
	}
}

function validateSpecialCharactersLBNameAliasLocal(value) {

	var retVal = true;
	retVal = validateSpecialCharacters(value);
	if (retVal == false) {
		alert("LocalBody Name in Alias Local contains invalid characters");
		var obj = document.getElementById('localBodyNameInAliasLocal');
		var obj1 = document.getElementById('localBodyAliasInLcl');
		if (obj != null) {
			obj.value = "";
		}
		if (obj1 != null) {
			obj1.value = "";
		}
		return false;
	} else {
		return true;
	}
}

/**
 * {@code validateSpecialCharacters} method validation for special characters,
 * If any special charater exist in string {@code method} return false, true
 * returns for valid string.
 * 
 * @return boolean {true, false}
 * @author vinay Yadav
 */
validateSpecialCharacters = function(value) {
	/*
	 * Allow '-'(hyphen) in Name of the Localbody (In English) and Name of
	 * Localbody (In Local language) @Ashish Dhupia @date 09-09-2014
	 */
	var _string_reg_ex = "[\#\/%&\~\!\@\$\^\*\_\+\`\=\{\}\\[\\]\|\\\\:\;\'\"\<\>\?\]";
	var regularExpression = new RegExp(_string_reg_ex);
	return !regularExpression.test(value);
};

/**
 * {@code validateSpecialCharacters} method validation for special characters,
 * If any special charater exist in string {@code method} return false, true
 * returns for valid string.
 * 
 * @return boolean {true, false}
 * @author vinay Yadav
 */
/**
 * Change remove / from _string_reg_ex to allow '/' in local Name
 */
validateSpecialCharactersWithHyphen = function(value) {
	var _string_reg_ex = "[\#\%&\~\!\@\$\^\*\_\+\`\=\{\}\\[\\]\|\\\\:\;\'\"\<\>\?\]";
	var regularExpression = new RegExp(_string_reg_ex);
	return !regularExpression.test(value);
};

/**
 * {@code validateLetterSpace} method validation for characters with space only,
 * this {@code} check for alpha space using basic javascript regex.
 * 
 * @return boolean {true, false}
 */
validateLetterSpace = function(value) {
	return /^[A-Za-z\s]+$/.test(value);
};

validateAlphabetWithSpaceDotBrackets = function(value) {
	var _string_reg_ex = "^[a-zA-Z0-9\\/\\.\\-\\(\\)\\s]+$";
	var regEx = new RegExp(_string_reg_ex);
	return regEx.test(value);
};

// Custom validations

$(document).ready(
		function() {
			// only [0-9a-zA-Z]

			jQuery.validator.addMethod("onlyLetterNumber", function(value, element) {
				return this.optional(element) || /^[0-9a-zA-Z ]+$/i.test(value);
			}, "<font color='red'>No special characters allowed.</font>");

			// only ascii letters, space and '
			jQuery.validator.addMethod("onlyLetterSp", function(value, element) {
				return this.optional(element) || /^[a-zA-Z\ \']+$/i.test(value);
			}, "<font color='red'>Ascii letters, space and ' only</font>");

			// only ascii letters, number, space & and '
			jQuery.validator.addMethod("onlyLetterNumberCustom", function(value, element) {
				return this.optional(element) || /^[0-9a-zA-Z&\ \']+$/i.test(value);
			}, "<font color='red'>No special characters allowed.</font>");

			// only [0-9] and space

			jQuery.validator.addMethod("onlyNumberSp", function(value, element) {
				var flag = this.optional(element) || /^[0-9]+$/i.test(value);
				if (flag)
					return this.optional(element) || /^[0-9]+$/i.test(value);
				else
					value = null;
			}, "<font color='red'>Only [0-9] and space</font>");

			// ipaddress

			jQuery.validator.addMethod("ipv4", function(value, element) {
				return this.optional(element)
						|| /^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$/i.test(value);
			}, "<font color='red'>Invalid IP address</font>");

			// phone

			jQuery.validator.addMethod("phone", function(value, element) {
				return this.optional(element)
						|| /^([\+][0-9]{1,3}[ \.\-])?([\(]{1}[0-9]{2,6}[\)])?([0-9 \.\-\/]{3,20})((x|ext|extension)[ ]?[0-9]{1,4})?$/i.test(value);
			}, "<font color='red'>Invalid phone number");

			jQuery.validator.addMethod("nameFormat", function(value, element) {
				return this.optional(element) || /^([a-zA-Z ]+)$/i.test(value);
			}, "<font color='red'>Invalid Name</font>");

			jQuery.validator.addMethod("shortName", function(value, element) {
				return this.optional(element) || /^([a-zA-Z]+)$/i.test(value);
			}, "<font color='red'>Invalid Name</font>");

			jQuery.validator.addMethod("nameFormatLocal", function(value, element) {
				return validateSpecialCharacters(value);
			}, "<font color='red'>Invalid Name</font>");

			jQuery.validator.addMethod("userIdFormat", function(value, element) {
				return this.optional(element) || /^[0-9a-zA-Z\_\-]+$/i.test(value);
			}, "<font color='red'>Invalid User Id Format.</font>");

			// only onlyLetterNumberWithDot
			jQuery.validator.addMethod("onlyLetterNumberWithDot", function(value, element) {
				return this.optional(element) || /^[0-9a-zA-Z. ]+$/i.test(value);
			}, "<font color='red'>Invalid Format.</font>");

			// only letters and space
			jQuery.validator.addMethod("onlyLetterSpace", function(value, element) {
				return this.optional(element) || /^[a-zA-Z ]*$/i.test(value);
			}, "<font color='red'>letters and space only</font>");
		});

// common method for Maneesh use case

function validateEntityEnglishNameBlank(entityName, errordiv) {
	if (entityName == "") {
		$(errordiv).show();
		return false;
	}

	$(errordiv).hide();
	return true;
}

function validateEntityNumbricData(entityName, errordiv) {
	for ( var k = 0; k < entityName.length; k++) {
		var char1 = entityName.charAt(k);
		var cc = char1.charCodeAt(0);

		if ((cc >= 48) && (cc <= 58)) {

		} else {
			$(errordiv).show();
			return false;
		}

	}
	$(errordiv).hide();
	return true;

}

function validateSubdistrictNameEnglish(entityName, errordiv) {

	for ( var k = 0; k < entityName.length; k++) {
		var char1 = entityName.charAt(k);
		var cc = char1.charCodeAt(0);
		// alert(cc);
		if ((cc >= 65) && (cc <= 90) || (cc >= 97) && (cc <= 122) || (cc == 45) || (cc == 46) || (cc == 32) || (cc == 8) || (cc == 9) || (cc >= 48)
				&& (cc <= 58)) {
			/*
			 * 32=Space 8=BackSpace 9=Tab 45=Desh[-] 46=Dot[.] 91=[ 93=] 40=(
			 * 41=)
			 * 
			 */

		} else {
			$(errordiv).show();
			return false;
		}

	}
	$(errordiv).hide();
	return true;

}

function validateBlockNameEnglish(entityName, errordiv) {

	for ( var k = 0; k < entityName.length; k++) {
		var char1 = entityName.charAt(k);
		var cc = char1.charCodeAt(0);
		// alert(cc);
		if ((cc >= 65) && (cc <= 90) || (cc >= 97) && (cc <= 122) || (cc == 45) || (cc == 46) || (cc == 32) || (cc == 8) || (cc == 9)) {
			/*
			 * 32=Space 8=BackSpace 9=Tab 45=Desh[-] 46=Dot[.] 91=[ 93=] 40=(
			 * 41=)
			 * 
			 */

		} else {
			$(errordiv).show();
			return false;
		}

	}
	$(errordiv).hide();
	return true;

}

function validateVillageNameEnglish(entityName, errordiv) {

	for ( var k = 0; k < entityName.length; k++) {
		var char1 = entityName.charAt(k);
		var cc = char1.charCodeAt(0);
		// alert(cc);
		if ((cc >= 65) && (cc <= 90) || (cc >= 97) && (cc <= 122) || (cc == 45) || (cc == 46) || (cc == 40) || (cc == 41) || (cc == 91) || (cc == 93)
				|| (cc == 32) || (cc == 8) || (cc == 9) || (cc >= 48) && (cc <= 58)) {
			/*
			 * 32=Space 8=BackSpace 9=Tab 45=Desh[-] 46=Dot[.] 91=[ 93=] 40=(
			 * 41=)
			 * 
			 */

		} else {
			$(errordiv).show();
			return false;
		}

	}
	$(errordiv).hide();
	return true;

}

function validateDistrictEnglishNameData(entityName, errordiv) {

	// alert(districtNameInEn);
	for ( var k = 0; k < entityName.length; k++) {
		var char1 = entityName.charAt(k);
		var cc = char1.charCodeAt(0);

		if ((cc >= 65) && (cc <= 90) || (cc >= 97) && (cc <= 122) || (cc == 32) || (cc >= 48) && (cc <= 58)) {

		} else {
			$(errordiv).show();
			return false;
		}

	}
	$(errordiv).hide();
	return true;
}

function validateEntityEnglishNameData(entityName, errordiv) {

	// alert(districtNameInEn);
	for ( var k = 0; k < entityName.length; k++) {
		var char1 = entityName.charAt(k);
		var cc = char1.charCodeAt(0);

		if ((cc >= 65) && (cc <= 90) || (cc >= 97) && (cc <= 122) || (cc == 32)) {

		} else {
			$(errordiv).show();
			return false;
		}

	}
	$(errordiv).hide();
	return true;
}

function validateEntityNameLocalData(entityName, errordiv) {

	// alert(districtNameInEn);
	var flag = validateSpecialCharacters(entityName);
	if (flag == false) {
		$(errordiv).show();
		return false;
	}

	$(errordiv).hide();
	return true;
}

function validateOrderNo(entityName, errordiv) {
	for ( var k = 0; k < entityName.length; k++) {
		var char1 = entityName.charAt(k);
		var cc = char1.charCodeAt(0);

		if ((cc >= 65) && (cc <= 90) || (cc >= 97) && (cc <= 122) || (cc == 45) || (cc >= 48) && (cc <= 57) || (cc == 13) || (cc == 37) || (cc == 46)
				|| (cc == 8) || (cc == 27) || (cc == 16) || (cc == 9) || (cc == 20)) {

		} else {
			$(errordiv).show();
			return false;
		}

	}
	$(errordiv).hide();
	return true;
}
function validateEntityAlphaNumbericData(entityName, errordiv) {
	for ( var k = 0; k < entityName.length; k++) {
		var char1 = entityName.charAt(k);
		var cc = char1.charCodeAt(0);

		if ((cc >= 65) && (cc <= 90) || (cc >= 97) && (cc <= 122) || (cc == 32) || (cc >= 48) && (cc <= 58)) {

		} else {
			$(errordiv).show();
			return false;
		}

	}
	$(errordiv).hide();
	return true;

}

function sureTransfer(from, to, all) {
	if (from.getElementsByTagName && to.appendChild) {
		while (getCount(from, !all) > 0) {
			transfer(from, to, all);
		}
	}
}

function getCount(target, isSelected) {
	var options = target.getElementsByTagName("option");
	if (!isSelected) {
		return options.length;
	}
	var count = 0;
	for (i = 0; i < options.length; i++) {
		if (isSelected && options[i].selected) {
			count++;
		}
	}
	return count;
}

function transfer(from, to, all) {
	if (from.getElementsByTagName && to.appendChild) {
		var options = from.getElementsByTagName("option");
		for (i = 0; i < options.length; i++) {
			if (all) {
				to.appendChild(options[i]);
			} else {
				if (options[i].selected) {
					to.appendChild(options[i]);
				}
			}
		}
	}
}

var entityFieldId;
var previousEntityName;
function validateEntityNameExist(entityParentCode, entityParentType, entityName, fieldId) {
	$("#entityNameInEnExist_error").hide();
	if (previousEntityName != entityName) {
		entityFieldId = fieldId;
		lgdDwrCommonService.existEntityName(entityParentCode, entityParentType, entityName, {
			callback : handleEntityNameSuccess,
			errorHandler : handleEntityNameError
		});
	}
}

function handleEntityNameSuccess(data) {
	if (data == true) {
		$("#entityNameInEnExist_error").show();
		document.getElementById(entityFieldId).value = previousEntityName;
		document.getElementById(entityFieldId).focus();
	}

}

function handleEntityNameError() {
	alert("data not found!");
}

function validateSpecialCharactersUpdateStandardCodeLocal(event) {

	var c = String.fromCharCode(event.which);

	var retVal = true;
	retVal = validateSpecialCharactersPRIWard(c);
	if (retVal == false) {
		alert("Invalid character not allowed");
		event.returnValue = false;
		return false;
	}
	return true;
}

function validateSpecialCharactersPRIWard(value) {
	var _string_reg_ex = "[#%&\~\!\@\$\^\*\_\+\`\=\{\}\\[\\]\|\\\\:\;\'\"\<\>\?]";
	var regularExpression = new RegExp(_string_reg_ex);
	return !regularExpression.test(value);
}
function validateSpecialCharactersUpdateStandardCodeDesignation(event) {
	var c = String.fromCharCode(event.which);
	var retVal = true;
	retVal = validateSpecialCharactersWithHyphen(c);
	if (retVal == false) {
		alert("Invalid character not allowed");
		event.returnValue = false;
		return false;
	}
	return true;
}

function noOrderDataValid(fieldId, errorId, orderDateId) {
	var OrderDate = document.getElementById(orderDateId).value;
	// alert(OrderDate);
	$(errorId).hide();
	if (OrderDate == "") {
		$(errorId).show();
		document.getElementById(fieldId).value = "";

	}

}

function compareGuzpubDatetoOrderDate(fieldId, errorId, orderDateId) {
	var OrderDate = document.getElementById(orderDateId).value;
	var guzPubdate = document.getElementById(fieldId).value;

	$(errorId).hide();

	if (compareDateYYMMDD(OrderDate, guzPubdate, 'dd-mm-yyyy')) {
		$(errorId).show();
		document.getElementById(fieldId).value = "";

	}

}

function compareDateYYMMDD(dt1, dt2, formatString) {
	var returnVal = 2;
	var dt1Parts;
	var dt2Parts;
	var dt1dd;
	var dt1mm;
	var dt1yyyy;
	var dt2dd;
	var dt2mm;
	var dt2yyyy;
	if (formatString == 'dd/mm/yyyy') {
		dt1Parts = dt1.split('/');
		dt2Parts = dt2.split('/');
		dt1dd = parseInt(dt1Parts[0]);
		dt1mm = parseInt(dt1Parts[1]);
		dt1yyyy = parseInt(dt1Parts[2]);
		dt2dd = parseInt(dt2Parts[0]);
		dt2mm = parseInt(dt2Parts[1]);
		dt2yyyy = parseInt(dt2Parts[2]);
	} else if (formatString == 'dd-mm-yyyy') {
		dt1Parts = dt1.split('-');
		dt2Parts = dt2.split('-');
		dt1dd = parseInt(dt1Parts[0]);
		dt1mm = parseInt(dt1Parts[1]);
		dt1yyyy = parseInt(dt1Parts[2]);
		dt2dd = parseInt(dt2Parts[0]);
		dt2mm = parseInt(dt2Parts[1]);
		dt2yyyy = parseInt(dt2Parts[2]);
	} else {
		alert(formatString + ' format is not supported.');
	}

	if (dt1yyyy == dt2yyyy && dt1mm == dt2mm && dt1dd == dt2dd) {
		returnVal = false;
	} else if (dt1yyyy > dt2yyyy) {
		returnVal = true;
	} else if (dt1yyyy == dt2yyyy) {
		if (dt1mm > dt2mm) {
			returnVal = true;
		} else if (dt1mm == dt2mm) {
			if (dt1dd > dt2dd) {
				returnVal = true;
			} else {
				returnVal = false;
			}
		} else {
			returnVal = false;
		}
	} else {
		returnVal = false;
	}
	return returnVal;
}

var id;
var cordinateList;
var govtOrderConfig;
function setgisnodes() {
	var cordinates = cordinateList.split(",");
	for ( var i = 1; i <= cordinates.length; i++) {
		var temp = cordinates[i - 1];
		var cordinate = temp.split(":");
		table = document.getElementById('gisnode');
		tr = document.createElement('TR');
		td1 = document.createElement('TD');
		td2 = document.createElement('TD');
		td3 = document.createElement('TD');
		inp1 = document.createElement('INPUT');
		inp2 = document.createElement('INPUT');

		tr.setAttribute("id", "row" + i);

		inp1.setAttribute("id", "lati" + i);
		inp1.setAttribute("name", "lati");
		inp1.setAttribute("size", "10");
		inp1.setAttribute("maxlength", "6");
		inp1.setAttribute("class", "frmfield");
		inp1.setAttribute("Value", cordinate[0]);

		inp2.setAttribute("id", "longi" + i);
		inp2.setAttribute("name", "longi");
		inp2.setAttribute("maxlength", "6");
		inp2.setAttribute("size", "10");
		inp2.setAttribute("class", "frmfield");
		inp2.setAttribute("Value", cordinate[1]);

		var deleteIcon = document.createElement('IMG');
		deleteIcon.setAttribute('src', 'images/delete.png');
		deleteIcon.setAttribute("onclick", "deleteNode(" + i + ")");

		table.appendChild(tr);
		td1.appendChild(inp1);
		td2.appendChild(inp2);
		td3.appendChild(deleteIcon);
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		document.getElementById("row" + i).style.visibility = "visible";
		document.getElementById("row" + i).style.display = "block";

	}
	id = i;
	// alert(id);
}

function addgisnodesLandregion() {

	// alert(id);
	table = document.getElementById('gisnode');
	tr = document.createElement('TR');
	td1 = document.createElement('TD');
	td2 = document.createElement('TD');
	td3 = document.createElement('TD');
	inp1 = document.createElement('INPUT');
	inp2 = document.createElement('INPUT');

	tr.setAttribute("id", "row" + id);

	inp1.setAttribute("id", "lati" + id);
	inp1.setAttribute("name", "lati");
	inp1.setAttribute("size", "10");
	inp1.setAttribute("maxlength", "6");
	inp1.setAttribute("class", "frmfield");

	inp2.setAttribute("id", "longi" + id);
	inp2.setAttribute("name", "longi");
	inp2.setAttribute("maxlength", "6");

	inp2.setAttribute("size", "10");
	inp2.setAttribute("class", "frmfield");

	var deleteIcon = document.createElement('IMG');
	deleteIcon.setAttribute('src', 'images/delete.png');
	deleteIcon.setAttribute("onclick", "deleteNode(" + id + ")");

	table.appendChild(tr);
	td1.appendChild(inp1);
	td2.appendChild(inp2);
	td3.appendChild(deleteIcon);
	tr.appendChild(td1);
	tr.appendChild(td2);
	tr.appendChild(td3);
	document.getElementById("row" + id).style.visibility = "visible";
	document.getElementById("row" + id).style.display = "block";
	id++;

}

function deleteNode(delid) {

	document.getElementById('row' + delid).style.visibility = 'hidden';
	document.getElementById('row' + delid).style.display = 'none';

}

function checkVisibleElement(rowid) {
	if (document.getElementById("row" + rowid).style.visibility == 'visible' || document.getElementById("row" + rowid).style.display == 'block')
		return true;
	else
		return false;
}

function checkLatitude(lati) {
	try {
		var latitude = parseFloat(lati);
		if (latitude < 6 || latitude > 38) {
			return true;
		} else {
			return false;
			false;
		}
	} catch (e) {
		return true;
	}

}

function checkLongitude(longi) {
	try {

		var longitude = parseFloat(longi);
		if (longitude < 32 || longitude > 98) {
			return true;
		} else {
			return false;

		}
	} catch (e) {
		return true;
	}

}

function checknumbericanddot(no) {
	var reg = new RegExp("^[0-9\\.\\,]+$");
	// alert(no.match(reg));
	if (no.match(reg) == null)
		return true;
	else
		return false;
}

function openGISModal(inputCode, mapLevel, lrlb, vpFlag, vpStateCode) {

	var sharedObject = {};
	sharedObject.inputparam = inputCode;
	sharedObject.levelCode = mapLevel;
	sharedObject.localGovBodyType = lrlb;
	sharedObject.vpFlag = vpFlag;
	sharedObject.stateCode = vpStateCode;
	var sOptions = "dialogWidth:600px; dialogHeight:460px; dialogLeft: 300; dialogTop: 350; scroll:no; center:yes; resizable: no; status:no; edge:sunken;unadorned :yes";
	//alert(window.showModalDialog);
	if(window.showModalDialog){
		showModalDialog("gisMapViewInModal.do", sharedObject, sOptions);
	} else {
		window.open("gisMapViewInModal.do", sharedObject, sOptions);
	}
	
}