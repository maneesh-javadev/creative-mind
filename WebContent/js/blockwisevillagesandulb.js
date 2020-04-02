// ----- Get District List --------------------------------  
function getDistrictList(id) {

	document.getElementById("ddSourceState_error").style.visibility = 'hidden';
	document.getElementById('ddSourceState_error').style.display = 'none';
	lgdDwrDistrictService.getDistrictList(id, {
		callback : handleDistrictSuccess,
		errorHandler : handleDistrictError
	});

}

function handleDistrictSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddSourceDistrict';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById(fieldId);
	st.add(new Option('----Select District -----', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleDistrictError() {
	// Show a popup message
	alert("No data found!");
}

// --------end Get District List-------------------------------------

// -------block List -------------------------------------------

function getBlockList(id) {
	document.getElementById("ddSourceDistrict_error").style.visibility = 'hidden';
	document.getElementById('ddSourceDistrict_error').style.display = 'none';
	lgdDwrBlockService.getBlockList(id, {
		callback : handleBlockSuccess,
		errorHandler : handleDepartmentError1
	});
}

function handleBlockSuccess(data) {
	var fieldId = 'ddSourceblock';
	var valueText = 'blockCode';
	var nameText = 'blockNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById(fieldId);
	st.add(new Option('----Select Block -----', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleDepartmentError1() {
	alert("No data found!");
}

// -------end block List -------------------------------------------

// --remove block error-----------------------------------------

function remove_error() {
	document.getElementById("ddSourceblock_error").style.visibility = 'hidden';
	document.getElementById('ddSourceblock_error').style.display = 'none';
}
// --end of remove block error-----------------------------------------

// -------- validation
function getParentData() {
	// alert("Parent Data called");
	var stateName = document.getElementById("ddSourceState").value;
	var districtName = document.getElementById("ddSourceDistrict").value;
	var blockName = document.getElementById("ddSourceblock").value;
	var code = document.getElementById("captchaAnswers").value;

	if (stateName == null || stateName == "") {
		// alert(stateName);
		document.getElementById("ddSourceState_error").style.visibility = 'visible';
		document.getElementById('ddSourceState_error').style.display = 'block';

		// $("#ddSourceState_error").show();
		return false;
	} else {
		document.getElementById("ddSourceState_error").style.visibility = 'hidden';
		document.getElementById('ddSourceState_error').style.display = 'none';
	}
	if (districtName == null || districtName == "0") {
		// alert(districtName);
		document.getElementById("ddSourceDistrict_error").style.visibility = 'visible';
		document.getElementById('ddSourceDistrict_error').style.display = 'block';
		// $("#ddSourceDistrict_error").show();
		return false;
	} else {
		document.getElementById("ddSourceDistrict_error").style.visibility = 'hidden';
		document.getElementById('ddSourceDistrict_error').style.display = 'none';
	}
	if (blockName == null || blockName == "0") {
		// alert(subDistrictName);
		document.getElementById("ddSourceblock_error").style.visibility = 'visible';
		document.getElementById('ddSourceblock_error').style.display = 'block';
		// $("#ddSourceSubDistrict_error").show();
		return false;
	} else {
		document.getElementById("ddSourceblock_error").style.visibility = 'hidden';
		document.getElementById('ddSourceblock_error').style.display = 'none';
		// document.getElementById("ddSourceSubDistrict_error").innerHTML= "";
	}
	if (code == "" || code == null) {
		document.getElementById("errorCaptchas").style.visibility = 'visible';
		document.getElementById('errorCaptchas').style.display = 'block';

		return false;
	} else {
		document.getElementById("errorCaptchas").style.visibility = 'hidden';
		document.getElementById('errorCaptchas').style.display = 'none';
	}
	// alert("after the innerhtml");

	return true;
}
// ----- end of vlaidation---------------------------------------------

// --- Show Goverment Order---------------------------------------
function ViewEntityGovermentOrder(entityCode, type) {

	if (type == "Village") {
		lgdDwrStateService.getGovtOrderByEntityCode(parseInt(entityCode), 'V', {
			callback : getDataFromServerCallBack
		});
	} else {
		lgdDwrStateService.getGovtOrderByEntityCode(parseInt(entityCode), 'G', {
			callback : getDataFromServerCallBack
		});
	}

}

showLoadingImage = function() {
	$.blockUI({
		theme : true,
		title : 'Loading...',
		message : "<div style='text-align: center'><img  src='<%= request.getContextPath() %>/resources/images/ajax-loader.gif'/></div>"
	});
};

hideLoadingImage = function() {
	$.unblockUI();
};

function getDataFromServerCallBack(data) {
	/* alert(data[0].filelocation); */
	if (data == null) {
		alert("No Government Order found.");
	} else {
		var filePath = data[0].filelocation;
		lgdDwrStateService.openFile(filePath, {
			callback : openFileCallBack
		});
	}
}

function openFileCallBack(data) {

	if (data == null) {
		alert("File has been moved or deleted.");
	} else {
		// str.substring(3,7)
		if (data.length > 5) {
			var d = data.substring(0, 5);
			if (d == "ERROR") {
				alert("File has been moved or deleted.");
			} else {
				var form = document.form1;
				var tempTarget = form.target;
				var tempAction = form.action;
				form.target = 'download_page';
				form.method = "post";
				form.action = "viewGovtOrder.do?<csrf:token uri='viewGovtOrder.do'/>";
				document.form1.govfilePath.value = data;
				document.form1.fileDisplayType.value = "<%=ApplicationConstant.FILE_INLINE%>";

				if ($.browser.msie) {
					p_windowProperties = "width=700px,height=450px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised";
					newWindow = window.open('', 'download_page', p_windowProperties);
					if (newWindow) {
						form.submit();
						form.target = tempTarget;
						form.action = tempAction;
						newWindow.focus();
					} else {
						alert('You must allow popups for this to work.');
					}
				} else if ($.browser.mozilla) {
					form.submit();
				}

				else {
					NewWindow = window
							.open('', "download_page",
									"width=903px,height=527px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised");
					form.submit();
				}
			}

		} else {
			alert("File has been moved or deleted.");
		}

	}

}
// --- end of Show Goverment Order---------------------------------------

// ---View Village or ULB---------------------------------------
function viewEntityDetails(entityCode, type) {
	var form = document.form1;
	var tempTarget = form.target;
	var tempAction = form.action;
	form.target = 'download_page';
	form.method = "post";
	if (type == "Village") {
		dwr.util.setValue('globalvillageId', entityCode, {
			escapeHtml : false
		});
		form.action = "globalviewVillageDetail.do?<csrf:token uri='globalviewVillageDetail.do'/>";
	} else {
		dwr.util.setValue('globallocalbodyId', entityCode, {
			escapeHtml : false
		});
		form.action = "globalviewLocalBodyDetail.do";
	}

	if ($.browser.msie) {
		p_windowProperties = "width=950px,height=527px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised";
		newWindow = window.open('', 'download_page', p_windowProperties);
		if (newWindow) {
			form.submit();
			form.target = tempTarget;
			form.action = tempAction;
			newWindow.focus();
		} else {
			alert('You must allow popups for this to work.');
		}
	} else if ($.browser.mozilla) {
		NewWindow = window
				.open('', "download_page",
						"width=950px,height=527px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised");
		form.submit();
	}

	else {
		NewWindow = window
				.open('', "download_page",
						"width=903px,height=527px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised");
		form.submit();
	}

}

// -------- Close Button------------------------------------

function go(url) {

	if (url.indexOf('home.htm') > -1 || url.indexOf(window.location.pathname.split('/')[window.location.pathname.split('/').length - 1]) == -1) {
		if (url.indexOf("?") > -1)
			window.location = url + "&OWASP_CSRFTOKEN=" + getParameterByNameForCSRF();
		else
			window.location = url + "?OWASP_CSRFTOKEN=" + getParameterByNameForCSRF();
	} else
		window.location = window.location.href;

}

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

// --------end of Close Button------------------------------------

