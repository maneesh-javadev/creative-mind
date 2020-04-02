/**
 * 
 * @author Hemant Gupta
 */

// ==========================For Multiple Browse Button
// Activity=======================
var fileTableG = "";
var titleIDG = "";
var fileIDG = "";
var counterIndex = 0;

// Validate Add More Button
function validateAddMore(fileTitle, fileName, fileTable, maxFileLimit, requiredTitle) {

	if (fileTableG != fileTable) {
		fileTableG = fileTable;
		counterIndex = 0;
	}
	if (counterIndex == 0) {
		titleIDG = fileTitle;
		fileIDG = fileName;
		counterIndex = 1;
	}

	if (fileTable != "") {
		var titleValue = document.getElementById(titleIDG);
		var fileValue = document.getElementById(fileIDG);
		if (titleValue != "" || fileValue != "") {
			var titleRequire = document.getElementById(requiredTitle).value;
			if (titleRequire == 'yes' || titleRequire == 'YES') {
				if (titleValue == null || titleValue.value.length == 0 || titleValue.value.trim() == "") {
					alert("Title field cannot be left blank.");
					return false;
				} else if (fileValue == null || fileValue.value.length == 0 || fileValue.value.trim() == "") {
					alert("Please select file first.");
					return false;
				} else {
					var newTableOne = document.getElementById(fileTable);
					var maxFileValueOne = document.getElementById(maxFileLimit).value;

					var rowCountOne = newTableOne.rows.length;
					var newRowCountOne = rowCountOne + 1;

					if (maxFileValueOne != 0) {
						if (maxFileValueOne >= newRowCountOne) {

							addRowToTable(fileTable, fileTitle, fileName, requiredTitle);
						} else {
							alert("allowed file limit is " + maxFileValueOne + ".!you can't add more.");
						}
					} else {
						addRowToTable(fileTable, fileTitle, fileName, requiredTitle);
					}
				}
			} else {
				if (fileValue == null || fileValue.value.length == 0 || fileValue.value.trim() == "") {
					alert("Please select file first.");
					return false;
				} else {
					var newTableTwo = document.getElementById(fileTable);
					var maxFileValueTwo = document.getElementById(maxFileLimit).value;
					var rowCountTwo = newTableTwo.rows.length;
					var newRowCountTwo = rowCountTwo + 1;

					if (maxFileValueTwo != 0) {
						if (maxFileValueTwo >= newRowCountTwo) {

							addRowToTable(fileTable, fileTitle, fileName, requiredTitle);
						} else {
							alert("allowed file limit is " + maxFileValueTwo + ".!you can't add more.");
						}

					} else {
						addRowToTable(fileTable, fileTitle, fileName, requiredTitle);
					}
				}
			}
		}
	}

}

// Creating Dynamic Rows
function addRowToTable(fileTable, fileTitle, fileName, requiredTitle) {
	var titleRequire = document.getElementById(requiredTitle).value;
	var tbl = document.getElementById(fileTable);
	var lastRow = tbl.rows.length;

	var randomnumber = Math.floor(Math.random() * 101);
	var fileTitleID = fileTitle + randomnumber;
	var fileTitleName = fileTitle;
	var attachFileID = fileName + randomnumber;
	var attachFileName = fileName;

	var onclickFunction = "deleteRowFromTable(this," + "'" + fileTable + "'" + "," + "'" + fileTitle + "'" + "," + "'" + fileName + "'" + ")";
	var onclickFunctionTitle = "return validateEmptyTitle(this," + "'" + fileTable + "'" + "," + "'" + fileTitle + "'" + "," + "'" + requiredTitle
			+ "'" + ")";

	var row = tbl.insertRow(lastRow);
	if (titleRequire == 'yes' || titleRequire == 'YES') {
		var cellLeft = row.insertCell(0);
		var textNode = document.createTextNode("Title:");
		cellLeft.appendChild(textNode);
		var cellRight = row.insertCell(1);
		var e1 = document.createElement('input');
		e1.setAttribute('type', 'text');
		e1.setAttribute('id', fileTitleID);
		e1.setAttribute('name', fileTitleName);
		e1.setAttribute('class', 'frmfield');
		cellRight.appendChild(e1);
	} else {
		var cellLeft = row.insertCell(0);
		var textNode = document.createTextNode("");
		cellLeft.appendChild(textNode);
		var cellRight = row.insertCell(1);
		var e1 = document.createElement('input');
		e1.setAttribute('type', 'hidden');
		e1.setAttribute('id', fileTitleID);
		e1.setAttribute('name', fileTitleName);
		e1.setAttribute('class', 'frmfield');
		cellRight.appendChild(e1);
	}

	var cellRight = row.insertCell(2);
	var e2 = document.createElement('input');
	e2.setAttribute('type', 'file');
	e2.setAttribute('id', attachFileID);
	e2.setAttribute('name', attachFileName);
	e2.setAttribute('class', 'frmfield');
	e2.setAttribute('onclick', onclickFunctionTitle);
	cellRight.appendChild(e2);
	var cellRight = row.insertCell(3);
	var e3 = document.createElement('input');
	e3.setAttribute('type', 'button');
	e3.setAttribute('id', randomnumber);
	e3.setAttribute('name', 'removeRow');
	e3.setAttribute('value', 'Remove  ');
	e3.setAttribute('class', 'btn');
	e3.setAttribute('onclick', onclickFunction);
	cellRight.appendChild(e3);
	titleIDG = fileTitleID;
	fileIDG = attachFileID;

}

// Delete Row
function deleteRowFromTable(id, fileTable, fileTitle, fileName) {

	// alert("========INSIDE DELETE ROW id ===="+id);
	var table = document.getElementById(fileTable);
	var oRow = id.parentElement.parentElement;
	var i = oRow.rowIndex;
	table.deleteRow(i);
	titleIDG = fileTitle;
	fileIDG = fileName;

}

// Delete All Rows
function deleteAllRows(fileTitle, fileName, fileTable) {

	var table = document.getElementById(fileTable);
	var lastRow = table.rows.length;
	var i = lastRow - 1;
	// alert("===========i============:"+i);
	if (i > 0) {
		for (i; i > 0; i--) {
			table.deleteRow(i);
		}
	}
	if (i == 0) {

		document.getElementById(fileTitle).value = "";
		oField = document.getElementById(fileName);
		oField.select();
		r = oField.createTextRange();
		r.execCommand('delete');
	}
	titleIDG = fileTitle;
	fileIDG = fileName;

}

// ==========================For Multiple Browse Button
// Activity=======================

// ************************************************************************************
// ====================For Deletion
// Activity===========================================
// hide row
function hideThisRow(rowID) {
	// alert(rowID);
	// alert(document.getElementById('row' + rowID));
	document.getElementById('row' + rowID).style.display = 'none';
}

var delFlag = null;
function delAttachFileConfirm() {
	var confirmDel = confirm("Are You Sure To Delete Attached File");
	if (!confirmDel) {
		return false;
	} else {
		return true;
	}
}
function setFlag(obj) {
	delFlag = obj;
	return delFlag;

}

// hold attachment id
function holdAttachmentID(rowID, deleteID, deletedFileID) {
	// alert(deletedFileID);
	var deleteAttachmentValue = "<input type='hidden' name='" + deletedFileID + "' id='" + deletedFileID + "' value='" + rowID + "' />";
	document.getElementById(deleteID).innerHTML = document.getElementById(deleteID).innerHTML + deleteAttachmentValue;
}

// hold file path
function holdFilePath(filePath, deletePath, deletedFileName) {

	var deleteAttachmentValue = "<input type='hidden' name='" + deletedFileName + "' value='" + filePath + "' />";
	document.getElementById(deletePath).innerHTML = document.getElementById(deletePath).innerHTML + deleteAttachmentValue;
}

// hold file size
function holdFileSize(fileSize, deleteFileSize, deletedFileSizeList) {

	var deleteFileSizeValue = "<input type='hidden' name='" + deletedFileSizeList + "' value='" + fileSize + "' />";
	document.getElementById(deleteFileSize).innerHTML = document.getElementById(deleteFileSize).innerHTML + deleteFileSizeValue;
}

// ===================================================================================================

function validateEmptyTitle(fileTitle, fileTable, fileName, requiredTitle) {

	var counter = document.getElementById('mapfilecount').value;
	if (counter > 0) {
		alert("Please Delete Earlier Attached File First");
		return false;
	}

	if (fileTableG != fileTable) {
		fileTableG = fileTable;
		counterIndex = 0;
	}
	if (counterIndex == 0) {
		titleIDG = fileTitle;
		fileIDG = fileName;
		counterIndex = 1;
	}

	if (fileTable != "") {
		var titleRequire = document.getElementById(requiredTitle).value;
		var titleValue = document.getElementById(titleIDG);
		if (titleRequire == 'yes' || titleRequire == 'YES') {
			if (titleValue == null || titleValue.value.length == 0 || titleValue.value.trim() == "") {
				alert("Title field cannot be left blank.");
				return false;
			}
		}
	}
}
function validateEmptyFile(fileTitle, fileTable, fileName, requiredTitle) {
	var counter = document.getElementById('govtfilecount').value;
	if (counter > 0) {
		alert("Please Delete Earlier Attached File First");
		return false;
	}

	if (fileTableG != fileTable) {
		fileTableG = fileTable;
		counterIndex = 0;
	}
	if (counterIndex == 0) {
		titleIDG = fileTitle;
		fileIDG = fileName;
		counterIndex = 1;
	}

	if (fileTable != "") {
		var titleRequire = document.getElementById(requiredTitle).value;
		var titleValue = document.getElementById(titleIDG);
		if (titleRequire == 'yes' || titleRequire == 'YES') {
			if (titleValue == null || titleValue.value.length == 0 || titleValue.value.trim() == "") {
				alert("Title field cannot be left blank.");
				return false;
			}
		}
	}
}

function setcounter() {

	var s = document.getElementById('govtfilecount').value;
	s = s - 1;
	document.getElementById('govtfilecount').value = s;

}

function setmapcounter() {

	var m = document.getElementById('mapfilecount').value;
	m = m - 1;
	document.getElementById('mapfilecount').value = m;

}

function openFileByPath(filePath) {
	alert("filePath" + filePath);
	/*
	 * lgdDwrStateService.openFile(filePath, { callback : openFileCallBack });
	 */
}
