function setPriorityImg() {
	var retrievetable = document.getElementById('designationOthers');
	var w = 1;
	for ( var i = 1; i <= retrievetable.rows.length - 1; i++) {

		// retrievetable.rows[i].cells[0].childNodes[cellindex].innerHTML = i;
		if (retrievetable.rows[i].cells[5].childNodes[1] == null) {
			w = 0;
		} else {
			w = 1;
		}

		if (i == 1) {

			retrievetable.rows[i].cells[5].childNodes[w].style.display = "none";
		} else {
			retrievetable.rows[i].cells[5].childNodes[w].style.display = "inline";
		}

		if (i == retrievetable.rows.length - 1) {
			retrievetable.rows[i].cells[6].childNodes[w].style.display = "none";
		} else {
			retrievetable.rows[i].cells[6].childNodes[w].style.display = "inline";
		}

	}
}

function addTableRows(desigName, desigNameLocal, isMulti, isContrctPrmt, desigId) {

	var tbl = document.getElementById('designationOthers');
	var lastRow = tbl.rows.length;
	var iteration = lastRow;
	var row = tbl.insertRow(lastRow);

	row.setAttribute('style', 'height:25px;');

	var cellRight1 = row.insertCell(0);
	var el = document.createElement('input');
	el.type = 'text';
	el.id = 'desgName' + iteration;
	el.name = 'designationNameOthers';
	el.maxLength = 50;
	if (desigName != null) {
		el.value = desigName;
	}
	el.setAttribute('style', 'width:200px;');
	el.onkeypress = function() {
		removeBorderColor(this.id);
	};
	cellRight1.appendChild(el);

	var cellRight2 = row.insertCell(1);
	var el = document.createElement('input');
	el.type = 'text';
	el.id = 'desgNameLocal' + iteration;
	el.name = 'designationNameLocalOthers';
	el.maxLength = 60;
	if (desigNameLocal != null) {
		el.value = desigNameLocal;
	}
	el.setAttribute('style', 'width:200px;');
	cellRight2.appendChild(el);

	var cellRight2 = row.insertCell(2);
	var el = document.createElement('input');
	el.type = 'checkbox';
	el.name = 'isMultipleOthers';
	if (isMulti != null) {
		el.checked = isMulti;
	}
	cellRight2.appendChild(el);
	cellRight2.setAttribute('align', 'center');

	var cellRightcp = row.insertCell(3);
	var el = document.createElement('input');
	el.type = 'checkbox';
	el.name = 'isContrctPermanentOthers';
	if (isContrctPrmt != null) {
		el.checked = isContrctPrmt;
	}
	cellRightcp.appendChild(el);
	cellRightcp.setAttribute('align', 'center');

	// cell remove button
	var cellRemoveBtn = row.insertCell(4);
	var el = document.createElement('img');
	el.id = iteration;
	el.name = "remove" + iteration;
	el.src = cPath + "/images/cancel.png";
	el.width = "20";
	el.height = "20";
	el.onclick = function() {
		document.getElementById('errorCommon').innerHTML = "";
		document.getElementById('tdError').style.display = "none";
		var designationcode = document.getElementById('designId' + this.id).value;
		var rownum = this.parentNode.parentNode.rowIndex;
		if (designationcode == "") {
			tbl.deleteRow(rownum);
			setPriorityImg();
		} else {
			displayLoadingImage();
			lgdDesignationDwr
					.isDesignationBeingUsed(
							parseInt(designationcode),
							{
								async : true,
								callback : function(data) {
									if (data) {
										document.getElementById('tdError').style.display = "inline";
										document.getElementById('errorCommon').innerHTML = "You cannot Modify / Delete this designation. This is being used in reporting !";
									} else {
										tbl.deleteRow(rownum);
										setPriorityImg();
									}
									hideLoadingImage();
								}
							});
		}
	};
	cellRemoveBtn.appendChild(el);

	var cellRight5 = row.insertCell(5);
	var el = document.createElement('img');
	el.src = cPath + "/images/sort_asc.png";
	el.onclick = function() {
		var row = $(this).parents("tr:first");
		row.insertBefore(row.prev());
		setPriorityImg();
	};
	cellRight5.appendChild(el);

	var cellRight6 = row.insertCell(6);
	var el = document.createElement('img');
	el.src = cPath + "/images/sort_desc.png";
	el.onclick = function() {
		var row = $(this).parents("tr:first");
		row.insertAfter(row.next());
		setPriorityImg();
	};
	cellRight6.appendChild(el);

	var cellRight3 = row.insertCell(7);
	var el = document.createElement('input');
	el.type = 'hidden';
	el.id = 'designId' + iteration;
	el.name = 'designationCodeOthers';
	if (desigId != null) {
		el.value = desigId;
	}
	cellRight3.appendChild(el);
	cellRight3.setAttribute('style', 'display:none');

	var cellRight4 = row.insertCell(8);
	var el = document.createElement('input');
	el.type = 'hidden';
	el.name = 'isTopDesignationOthers';
	el.value = false;
	cellRight4.appendChild(el);
	cellRight4.setAttribute('style', 'display:none');
	setPriorityImg();

};

function getDesignationDetails(tiersetupcode, desType) {
	clearAll();
	if (tiersetupcode == "-1") {
		document.getElementById('errorTierSetup').innerHTML = "Please Select a Local Govt Type.";
		return false;
	}
	displayLoadingImage();
	lgdDesignationDwr.getExistingDesignations(parseInt(tiersetupcode), desType, {
		async : true,
		callback : function(data) {
			var datalen = data.length;
			if (datalen > 0) {
				var firstrowothers = true;

				for ( var i = 0; i < datalen; i++) {
					var topdesignation = data[i].isTopDesignation;
					var designationName = data[i].designationName;
					var designationNameLocal = data[i].designationNameLocal;
					var ismultiple = data[i].isMultiple;
					var isContractPerma = data[i].isContractPermanent;
					var designationCode = data[i].lgdDesignationPK.designationCode;

					if (topdesignation) {
						document.getElementById('desgNameTop').value = designationName;
						document.getElementById('desgNameLocalTop').value = designationNameLocal;
						document.getElementById('desgIdTop').value = designationCode;
					} else {
						if (firstrowothers) {
							document.getElementById('desgNameOthers').value = designationName;
							document.getElementById('desgNameLocalOthers').value = designationNameLocal;
							document.getElementById('isMultipleOthers').checked = ismultiple;
							document.getElementById('isContrctPermanentOthers').checked = isContractPerma;
							document.getElementById('desgIdOthers').value = designationCode;
							firstrowothers = false;
						} else {
							addTableRows(designationName, designationNameLocal, ismultiple, isContractPerma, designationCode);
						}
					}
				}

			}
			document.getElementById('submit').disabled = false;
			hideLoadingImage();
		}
	});
}

function clearAll() {
	document.getElementById('errorTierSetup').innerHTML = "";
	document.getElementById('desgNameTop').value = "";
	document.getElementById('desgNameLocalTop').value = "";
	document.getElementById('desgIdTop').value = "";
	document.getElementById('desgNameOthers').value = "";
	document.getElementById('desgNameLocalOthers').value = "";
	document.getElementById('isMultipleOthers').checked = false;
	document.getElementById('isContrctPermanentOthers').checked = false;
	document.getElementById('desgIdOthers').value = "";
	removeRows();
}

function removeRows() {
	retrievetable = document.getElementById("designationOthers");
	for ( var i = retrievetable.rows.length - 1; i > 1; i--) {
		retrievetable.deleteRow(i);
	}
};

function getOtherDesignations() {

	var tiersetupcode = document.getElementById('tierSetupCode').value;
	if (tiersetupcode == "-1") {
		document.getElementById('errorTierSetup').innerHTML = "Please Select a Local Govt Type.";
		return false;
	}

	var desgNameTop = document.getElementById('desgNameTop').value;
	if (trim(desgNameTop) == "") {
		document.getElementById('errDesgNameTop').innerHTML = "Please enter a valid Designation.";
		return false;
	}

	if (!validateAlphabetWithSpaceDotBrackets(desgNameTop)) {
		customAlert('You have entered an invalid Top Designation !');
		addBorderColor('desgNameTop');
		document.getElementById('desgNameTop').focus();
		return false;
	}

	var desgNameLocalTop = document.getElementById('desgNameLocalTop').value;
	if (!validateSpecialCharactersWithHyphen(desgNameLocalTop)) {
		customAlert('You have entered an invalid Top Designation in Local !');
		addBorderColor('desgNameLocalTop');
		document.getElementById('desgNameLocalTop').focus();
		return false;
	}

	var desName = document.getElementsByName('designationNameOthers');
	var desNameLocals = document.getElementsByName('designationNameLocalOthers');
	var desMultiples = document.getElementsByName('isMultipleOthers');
	var desContrctPermas = document.getElementsByName('isContrctPermanentOthers');
	var desCodes = document.getElementsByName('designationCodeOthers');
	var len = desName.length;

	var otherDesignationDetails = "";
	for ( var i = 0; i < len; i++) {
		var designationName = desName[i].value;
		if (fullTrim(designationName) != "") {
			if (!validateAlphabetWithSpaceDotBrackets(designationName)) {
				customAlert('You have entered an invalid Designation !');
				addBorderColor(desName[i].id);
				document.getElementById(desName[i].id).focus();
				return false;
			}

			if (!validateSpecialCharactersWithHyphen(desNameLocals[i].value)) {
				customAlert('You have entered an invalid Designation in Local !');
				addBorderColor(desNameLocals[i].id);
				document.getElementById(desNameLocals[i].id).focus();
				return false;
			}

			if (fullTrim(designationName) == fullTrim(desgNameTop)) {
				customAlert('Designation Name already referenced, Please change designation !');
				addBorderColor(desName[i].id);
				document.getElementById(desName[i].id).focus();
				return false;
			}

			for ( var j = 0; j < len; j++) {
				if (i != j) {
					if (fullTrim(designationName) == fullTrim(desName[j].value)) {
						customAlert('Designation Name already referenced, Please change designation !');
						addBorderColor(desName[j].id);
						document.getElementById(desName[j].id).focus();
						return false;
					}
				}
			}
			otherDesignationDetails += desCodes[i].value + "##";
			otherDesignationDetails += designationName + "##";
			otherDesignationDetails += desNameLocals[i].value + "##";
			otherDesignationDetails += desMultiples[i].checked + "##";
			otherDesignationDetails += desContrctPermas[i].checked + "@@";
		}
	}
	document.getElementById('otherDesignations').value = otherDesignationDetails.substring(0, otherDesignationDetails.length - 2);
	displayLoadingImage();
	return true;
}

function addBorderColor(inputId) {
	$("#" + inputId).addClass("redborder");
}

function removeBorderColor(inputId) {
	$("#" + inputId).removeClass("redborder");
}
