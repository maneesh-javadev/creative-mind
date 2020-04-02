function onloadShowTemplateVariable() {
	var oper = document.getElementById("operations").value;
	if (oper != null) {
		getVariableList(oper);
	}
}

function getVariableList(val) {
	lgdDwrGovtOrderTemplate.getVariableListonOperation(val, {
		callback : handleVariableListSuccess,
		errorHandler : handleVariableListError
	});
}
function handleVariableListSuccess(data) {
	// Assigns data to result id
	var fieldId = 'selType';
	var valueText = 'variable';
	var nameText = 'variableValue';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('selType');
	st.add(new Option('Select Template Variables', ''));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

	// dwr.util.addOptions("ulbListMain", data);
	// dwr.util.addOptions("ulbListMain", [{ name:'Africa', id:'AF' }, {
	// name:'America', id:'AM' }, { name:'Asia', id:'AS' }, {
	// name:'Australasia', id:'AU' }, { name:'Europe', id:'EU' }] ,id ,name );
}

function handleVariableListError() {
	// Show a popup message
	alert("No data found!");
}

function validateSelectAndSubmit() {
	if (validate()) {
		alert();
		document.getElementById('templateForm').action = "createTemplate.htm";
		document.forms["templateForm"].submit();
	}

}
function validateSelectAndPreview() {
	if (validate()) {
		document.getElementById('templateForm').action = "govtordertemplatepreview.htm";
		document.forms["templateForm"].submit();
	}
}
function validateSelectAndUpdate() {
	if (validate()) {
		document.getElementById('templateForm').action = "updateTemplate.htm";
		document.forms["templateForm"].submit();
	}
}
function validate() {

	var templateNameEnglish = document.getElementById('templateNameEnglish').value;
	var operations = document.getElementById('operations').value;
	var orderType = document.getElementById('orderType').value;
	var templateLanguage = document.getElementById('templateLanguage').value;
	var templateBodySrc = document.getElementById('templateBodySrc').value;
	if (templateNameEnglish == "" || templateNameEnglish == null) {
		alert("Please Enter Template Name");
		return false;
	}
	if (operations == 0) {
		alert("Please Select Operation");
		return false;
	}
	if (orderType == 0) {
		alert("Please Select Order Type");
		return false;
	}
	if (templateLanguage == 0) {
		alert("Please Select Template Language");
		return false;
	}
	/*
	 * if(document.getElementById('xEditingArea').value=="" ||
	 * document.getElementById('xEditingArea').value==null) { alert("Please
	 * Enter Template Body"); return false; } var val =
	 * FCKeditorAPI.GetInstance("templateBodySrc"); alert(val.value);
	 */
	return true;
}

function onSave() {
	var errors = new Array();
	var error = false;
	errors[0] = vlidateOnblur('templateNameEnglish', '1', '15', 'c');
	if (errors[0] == true) {
		error = true;
	}
	errors[1] = vlidateOnblur('operations', '1', '15', 'c');
	if (errors[1] == true) {
		error = true;
	}
	errors[2] = vlidateOnblur('orderType', '1', '15', 'c');
	if (errors[2] == true) {
		error = true;
	}
	errors[3] = vlidateOnblur('templateLanguage', '1', '15', 'c');
	if (errors[3] == true) {
		error = true;
	}
	errors[4] = vlidateOnblur('selType', '1', '15', 'c');
	if (errors[4] == true) {
		error = true;
	}

	if (error == true) {
		showClientSideError();
		return false;
	} else {
		// var val = FCKeditorAPI.GetInstance("templateBodySrc");
		document.getElementById('templateForm').action = "createTemplate.htm";
		document.forms["templateForm"].submit();
		return true;
	}
}

function onPreview() {
	var errors = new Array();
	var error = false;
	errors[0] = vlidateOnblur('templateNameEnglish', '1', '15', 'c');
	if (errors[0] == true) {
		error = true;
	}
	errors[1] = vlidateOnblur('operations', '1', '15', 'c');
	if (errors[1] == true) {
		error = true;
	}
	errors[2] = vlidateOnblur('orderType', '1', '15', 'c');
	if (errors[2] == true) {
		error = true;
	}
	errors[3] = vlidateOnblur('templateLanguage', '1', '15', 'c');
	if (errors[3] == true) {
		error = true;
	}
	errors[4] = vlidateOnblur('selType', '1', '15', 'c');
	if (errors[4] == true) {
		error = true;
	}
	if (error == true) {
		showClientSideError();
		return false;
	} else {
		// var val = FCKeditorAPI.GetInstance("templateBodySrc");
		document.getElementById('templateForm').action = "govtordertemplatepreview.htm";
		document.forms["templateForm"].submit();
		return true;
	}
}

function onUpdateTemplate() {

	var errors = new Array();
	var error = false;

	errors[0] = !validateTemplateName();
	errors[1] = !validateLOparations();
	errors[2] = !validateGovtOrderType();
	errors[3] = !validateTemplLanguage();
	errors[4] = !validateTemplVariable();

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
		document.getElementById('templateForm').action = "updateTemplate.htm";
		document.forms["templateForm"].submit();
		return true;
	}
}

function onSaveTemplate() {

	var errors = new Array();
	var error = false;

	errors[0] = !validateTemplateName();
	errors[1] = !validateLOparations();
	errors[2] = !validateGovtOrderType();
	errors[3] = !validateTemplLanguage();
	errors[4] = !validateTemplVariable();

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
		// var val = FCKeditorAPI.GetInstance("templateBodySrc");
		/*
		 * start Autohr Kirandeep Date 18/06/2014
		 */
		$('input[name=Submit]').attr('disabled', true);
		// end
		document.getElementById('templateForm').action = "createTemplate.htm";
		document.forms["templateForm"].submit();
		return true;
	}
}

function onUpdatePreviewTemplate() {

	var errors = new Array();
	var error = false;

	errors[0] = !validateTemplateName();
	errors[1] = !validateLOparations();
	errors[2] = !validateGovtOrderType();
	errors[3] = !validateTemplLanguage();
	errors[4] = !validateTemplVariable();

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
		// var val = FCKeditorAPI.GetInstance("templateBodySrc");
		document.getElementById('templateForm').action = "govtordertemplatepreviewupdate.htm";
		document.forms["templateForm"].submit();
		return true;
	}
}

function onPreviewTemplate() {

	var errors = new Array();
	var error = false;

	errors[0] = !validateTemplateName();
	errors[1] = !validateLOparations();
	errors[2] = !validateGovtOrderType();
	errors[3] = !validateTemplLanguage();
	errors[4] = !validateTemplVariable();

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
		// var val = FCKeditorAPI.GetInstance("templateBodySrc");
		document.getElementById('templateForm').action = "govtordertemplatepreview.htm";
		document.forms["templateForm"].submit();
		return true;
	}
}

function validateTemplateName() {
	if (document.getElementById('templateNameEnglish').value == "") {
		$("#templateNameEnglish_error").show();
		return false;
	}
	$("#templateNameEnglish_error").hide();
	return true;
}

function validateLOparations() {
	var operationName = document.getElementById('operations');
	if (operationName.selectedIndex == 0) {
		$("#operations_error").show();
		return false;
	}

	$("#operations_error").hide();
	return true;
}

function validateGovtOrderType() {
	var orderType = document.getElementById('orderType');
	if (orderType.selectedIndex == 0) {
		$("#orderType_error").show();
		return false;
	}

	$("#orderType_error").hide();
	return true;
}

function validateTemplLanguage() {
	var templateLanguage = document.getElementById('templateLanguage');
	if (templateLanguage.selectedIndex == 0) {
		$("#templateLanguage_error").show();
		return false;
	}

	$("#templateLanguage_error").hide();
	return true;
}

function validateTemplVariable() {
	var selType = document.getElementById('selType');
	if (selType.selectedIndex == 0) {
		$("#selType_error").show();
		return false;
	}

	$("#selType_error").hide();
	return true;
}

function onUpdate() {
	var errors = new Array();
	var error = false;
	errors[0] = vlidateOnblur('templateNameEnglish', '1', '15', 'c');
	if (errors[0] == true) {
		error = true;
	}
	errors[1] = vlidateOnblur('operations', '1', '15', 'c');
	if (errors[1] == true) {
		error = true;
	}
	errors[2] = vlidateOnblur('orderType', '1', '15', 'c');
	if (errors[2] == true) {
		error = true;
	}
	errors[3] = vlidateOnblur('templateLanguage', '1', '15', 'c');
	if (errors[3] == true) {
		error = true;
	}
	errors[4] = vlidateOnblur('selType', '1', '15', 'c');
	if (errors[4] == true) {
		error = true;
	}
	if (error == true) {
		showClientSideError();
		return false;
	} else {
		// var val = FCKeditorAPI.GetInstance("templateBodySrc");
		document.getElementById('templateForm').action = "updateTemplate.htm";
		document.forms["templateForm"].submit();
		return true;
	}
}

function getTemplateListByOperationCode(val) {

	lgdDwrGovtOrderTemplate.getTemplateListByOperationCode(val, {
		callback : handleTemplateListByOperationCode,
		errorHandler : handleTemplateListByOperationCodeError
	});

}

function handleTemplateListByOperationCode(data) {
	if (data) {
		document.getElementById("divTemplateList").innerHTML('Hello');
	}
}

function handleTemplateListByOperationCodeError() {
	alert("No data found!");
}
