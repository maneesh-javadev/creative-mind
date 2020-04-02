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

			retrievetable.rows[i].cells[4].childNodes[w].style.display = "none";
		} else {
			retrievetable.rows[i].cells[4].childNodes[w].style.display = "inline";
		}

		if (i == retrievetable.rows.length - 1) {
			retrievetable.rows[i].cells[5].childNodes[w].style.display = "none";
		} else {
			retrievetable.rows[i].cells[5].childNodes[w].style.display = "inline";
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
	var el = document.createElement('Label');
	el.id = 'desgName' + iteration;
	if (desigName != null) {
		el.innerHTML = desigName;
	}
	el.setAttribute('style', 'width:200px;');
	cellRight1.appendChild(el);

	var cellRight2 = row.insertCell(1);
	var el = document.createElement('Label');
	el.id = 'desgNameLocal' + iteration;
	if (desigNameLocal != null) {
		el.innerHTML = desigNameLocal;
	}
	el.setAttribute('style', 'width:200px;');
	cellRight2.appendChild(el);

	var cellRight3 = row.insertCell(2);
	var el = document.createElement('input');
	el.type = 'checkbox';
	el.name = 'isMultipleOthers';
	if (isMulti != null) {
		el.checked = isMulti;
	}
	el.setAttribute('disabled', 'false');
	cellRight3.appendChild(el);
	cellRight3.setAttribute('align', 'center');

	var cellRightcp = row.insertCell(3);
	var el = document.createElement('input');
	el.type = 'checkbox';
	el.name = 'isContrctPermanentOthers';
	el.setAttribute('disabled', 'false');
	if (isContrctPrmt != null) {
		el.checked = isContrctPrmt;
	}
	cellRightcp.appendChild(el);
	cellRightcp.setAttribute('align', 'center');

	// cell remove button

	var cellRight4 = row.insertCell(4);
	var el = document.createElement('img');
	el.src = cPath + "/images/sort_asc.png";
	el.onclick = function() {
		var row = $(this).parents("tr:first");
		row.insertBefore(row.prev());
		setPriorityImg();
	};
	cellRight4.appendChild(el);
	cellRight4.setAttribute('align', 'center');

	var cellRight5 = row.insertCell(5);
	var el = document.createElement('img');
	el.src = cPath + "/images/sort_desc.png";
	el.onclick = function() {
		var row = $(this).parents("tr:first");
		row.insertAfter(row.next());
		setPriorityImg();
	};
	cellRight5.appendChild(el);
	cellRight5.setAttribute('align', 'center');

	var cellRight6 = row.insertCell(6);
	var el = document.createElement('input');
	el.type = 'hidden';
	el.id = 'designId' + iteration;
	el.name = 'designationCodeOthers';
	if (desigId != null) {
		el.value = desigId;
	}
	cellRight6.appendChild(el);
	cellRight6.setAttribute('style', 'display:none');

	var cellRight7 = row.insertCell(7);
	var el = document.createElement('input');
	el.type = 'hidden';
	el.name = 'isTopDesignationOthers';
	el.value = false;
	cellRight7.appendChild(el);
	cellRight7.setAttribute('style', 'display:none');
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
				for ( var i = 0; i < datalen; i++) {
					var topdesignation = data[i].isTopDesignation;
					var designationName = data[i].designationName;
					var designationNameLocal = data[i].designationNameLocal;
					var ismultiple = data[i].isMultiple;
					var isContractPerma = data[i].isContractPermanent;
					var designationCode = data[i].designationCode;

					if (topdesignation) {
						document.getElementById('desgNameTop').innerHTML = designationName;
						document.getElementById('desgNameLocalTop').innerHTML = designationNameLocal;
						document.getElementById('desgIdTop').value = designationCode;
					} else {
						addTableRows(designationName, designationNameLocal, ismultiple, isContractPerma, designationCode);

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
	document.getElementById('desgNameTop').innerHTML = "";
	document.getElementById('desgNameLocalTop').innerHTML = "";

	removeRows();
}

function removeRows() {
	retrievetable = document.getElementById("designationOthers");
	for ( var i = retrievetable.rows.length - 1; i > 0; i--) {
		retrievetable.deleteRow(i);
	}
};

function getOtherDesignations() {
	var tiersetupcode = document.getElementById('tierSetupCode').value;
	if (tiersetupcode == "-1") {
		document.getElementById('errorTierSetup').innerHTML = "Please Select a Local Govt Type.";
		return false;
	}
	var desCodes = document.getElementsByName('designationCodeOthers');
	var len = desCodes.length;
	var otherDesignationDetails = "";
	for ( var i = 0; i < len; i++) {
		otherDesignationDetails += desCodes[i].value + "@@";
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
