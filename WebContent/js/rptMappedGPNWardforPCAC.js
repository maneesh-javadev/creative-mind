function toggledisplay(pcid, acid) {
	var pcObj = document.getElementById(pcid);
	var acObj = document.getElementById(acid);
	document.getElementById("pcCheck").value = pcObj.checked;

	if (pcObj.checked) {

		document.getElementById('showpc').style.visibility = 'visible';
		document.getElementById('showpc').style.display = 'block';
		document.getElementById('showac').style.visibility = 'hidden';
		document.getElementById('showac').style.display = 'none';
	} else if (acObj.checked) {
		document.getElementById('showac').style.visibility = 'visible';
		document.getElementById('showac').style.display = 'block';
		document.getElementById('showpc').style.visibility = 'hidden';
		document.getElementById('showpc').style.display = 'none';
	}

}

function getListParliment(id) {

	lgdDwrParlimentService.getParliamentConstituencyDetail(id, {
		callback : handleParlimentSuccess,
		errorHandler : handleParlimentError
	});
}

function handleParlimentSuccess(data) {

	var fieldId = 'parliment';
	var valueText = 'pcCode';
	var nameText = 'pcNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('parliment');
	st.add(new Option('Select Parliament', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleParlimentError() {
	// Show a popup message
	alert("No data found in Parliment!");
}

function getListParlimentac(id) {

	lgdDwrParlimentService.getParliamentConstituencyDetail(id, {
		callback : handleParlimentSuccessac,
		errorHandler : handleParlimentError
	});
}

function handleParlimentSuccessac(data) {
	// Assigns data to result id
	// alert("hibye");
	// alert(data);
	var fieldId = 'parlimentac';
	var valueText = 'pcCode';
	var nameText = 'pcNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('parlimentac');
	st.add(new Option('Select Parliament', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function getListAssembly(id) {
	lgdDwrAssemblyService.getAssemblyConstituencyListbyParliamenCodet(id, {
		callback : function(result) {
			// alert(result);
			 dwr.util.removeAllOptions('assembly');
			var options = $("#assembly");
			var option = $("<option/>");
			$(option).val("-1").text("Select");
			options.append(option);
			$.each(result, function(key, obj) {
				var option = $("<option/>");
				(option).val(obj.acCode).text(obj.acNameEnglish);
				options.append(option);
				
			});
		},
		async : true
	});		 
}



function validatepc() {
	var pcObj = document.getElementById("selectPC");
	
	if (pcObj.checked) {
		var error = false;
		var inSelectState = document.getElementById("ddSourceState").value;
		var inSelectPC = document.getElementById("parliment").value;

		if (inSelectState != 0 && inSelectState != "") {

			if (inSelectPC == 0 || inSelectPC == "") {
				error = true;
				$("#error_selectpc").show();

			}
		} else {

			$("#error_selectstate").show();
			error = true;
		}

		if (error == true) {
			return false;
		} else {
			return true;
		}
	}
	
	else
		{
		validateac();
		}

}

function validateac() {
	var error = false;
	var inSelectState = document.getElementById("ddSourceState1").value;
	var inSelectPC = document.getElementById("parlimentac").value;
	var inSelectAC = document.getElementById("assembly").value;
	var code = document.getElementById("captchaAnswer").value;
	
	if (inSelectState != 0 && inSelectState != "") {

		if (inSelectPC != 0 && inSelectPC != "") {

			if (inSelectAC == 0 || inSelectAC == "") {
				error = true;
				$("#error_selectac").show();
			}
		} else {

			$("#error_selectpc1").show();
			error = true;

		}
	} 
	else if(code==0 ||code ==null)
	{
		document.getElementById("errEmptyCaptcha").innerHTML = "Please Enter CAPTCHA hello in the textbox"; 
	    document.getElementById("errEmptyCaptcha").focus();
	    return false;
		} 
	
	else {

		$("#error_selectstate1").show();
		error = true;
	}

	if (error == true) {
		return false;
	} else {
		return true;
	}

}
