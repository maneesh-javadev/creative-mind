changeHierarchy = function() {
	var levelHierarchy = document.getElementById('hierarchyLevel');
	for ( var k = 0; k < levelHierarchy.length; k++) {
		levelHierarchy.options[k].disabled = true;
	}
	var hieValue = levelHierarchy.value;
	if (hieValue == levelHierarchy.length) {
		levelHierarchy.options[0].disabled = false;
		levelHierarchy.selectedIndex = 0;
	} else {
		levelHierarchy.options[hieValue].disabled = false;
		levelHierarchy.selectedIndex = hieValue;
	}
};

getRadioValue = function() {
	if ($('input[name=setupCode]:radio:checked').length > 0) {
		return $('input[name=setupCode]:radio:checked').val();
	} else {
		return 0;
	}
};

validateHirarchy = function(childValue, parentValue, hierarchyValue) {
	var bool = true;
	var lbChild = document.getElementsByName('lbTypeChildValue');
	var lbParent = document.getElementsByName('lbTypeParentValue');
	var levelHierarchy = document.getElementsByName('levelHierarchyValue');
	for ( var i = 0; i < lbChild.length; i++) {
		var innChild = lbChild[i].value.split("#")[0];
		var innParent = lbParent[i].value;
		if (innParent != "-1") {
			innParent = innParent.split("#")[0];
			parentValue = parentValue.split("#")[0];
		}
		var innHierarchy = levelHierarchy[i].value;
		if ((innChild == childValue.split("#")[0]) && (innParent == parentValue) && (innHierarchy == hierarchyValue)) {
			bool = false;
			break;
		}
	}
	return bool;
};

buildHierarchy = function(tableId, buildProcess) {
	$("#lbTypeD").removeClass("redborder");
	$("#lbTypeI").removeClass("redborder");
	$("#lbTypeV").removeClass("redborder");
	var lbTypeD = document.getElementById('lbTypeD');
	var lbTypeI = document.getElementById('lbTypeI');
	var lbTypeV = document.getElementById('lbTypeV');
	if (lbTypeD.value == "-1" && lbTypeI.value == "-1" && lbTypeV.value == "-1") {
		$("#lbTypeD").addClass("redborder");
		$("#lbTypeI").addClass("redborder");
		$("#lbTypeV").addClass("redborder");
		return false;
	}
	var hVal = "";
	var hText = "";
	if (buildProcess == "add") {
		var levelHierarchy = document.getElementById('hierarchyLevel');
		hVal = levelHierarchy.value;
		hText = levelHierarchy.options[levelHierarchy.selectedIndex].text;
	} else if (buildProcess == "modify") {
		hVal = getRadioValue();
		hText = $("#hCount_" + hVal).val();
	} else {
		return false;
	}

	if (lbTypeD.value != "-1") {
		var parentCodeD = "-1";
		var parentTextD = "Self Parent";
		if (validateHirarchy(lbTypeD.value, parentCodeD, hVal)) {
			var selTextD = lbTypeD.options[lbTypeD.selectedIndex].text;
			addHierarchyRows(tableId, lbTypeD.value, selTextD, parentCodeD, parentTextD, hVal, hText);
		}
	}
	if (lbTypeI.value != "-1") {
		var parentCodeI = "-1";
		var parentTextI = "Self Parent";
		if (lbTypeD.value != "-1") {
			parentCodeI = lbTypeD.value;
			parentTextI = lbTypeD.options[lbTypeD.selectedIndex].text;
		}
		if (validateHirarchy(lbTypeI.value, parentCodeI, hVal)) {
			var selTextI = lbTypeI.options[lbTypeI.selectedIndex].text;
			addHierarchyRows(tableId, lbTypeI.value, selTextI, parentCodeI, parentTextI, hVal, hText);
		}
	}
	if (lbTypeV.value != "-1") {
		var parentCodeV = "-1";
		var parentTextV = "Self Parent";
		if (lbTypeI.value != "-1") {
			parentCodeV = lbTypeI.value;
			parentTextV = lbTypeI.options[lbTypeI.selectedIndex].text;
		} else if (lbTypeD.value != "-1") {
			parentCodeV = lbTypeD.value;
			parentTextV = lbTypeD.options[lbTypeD.selectedIndex].text;
		}
		if (validateHirarchy(lbTypeV.value, parentCodeV, hVal)) {
			var selTextV = lbTypeV.options[lbTypeV.selectedIndex].text;
			addHierarchyRows(tableId, lbTypeV.value, selTextV, parentCodeV, parentTextV, hVal, hText);
		}
	}
};

addHierarchyRows = function(tableId, lbTypeChildValue, lbTypeChildText, lbTypeParentValue, lbTypeParentText, levelHierarchyValue, levelHierarchyText,
		isModified, tiersetupcode) {
	var tbl = document.getElementById(tableId);
	var lastRow = tbl.rows.length;
	var iteration = lastRow;
	var row = tbl.insertRow(lastRow);
	row.setAttribute('class', 'tblRowTitle tblclear');
	row.setAttribute('style', 'height:35px;');

	var subTypeChild = row.insertCell(0);
	var textNode = document.createTextNode(lbTypeChildText);
	subTypeChild.appendChild(textNode);

	var subTypeParent = row.insertCell(1);
	var textNode = document.createTextNode(lbTypeParentText);
	subTypeParent.appendChild(textNode);

	var cellHierarchy = row.insertCell(2);
	var textNode = document.createTextNode(levelHierarchyText);
	cellHierarchy.appendChild(textNode);

	// cell remove button
	var cellRemoveBtn = row.insertCell(3);
	var el = document.createElement('img');
	el.id = "remove" + iteration;
	el.name = "remove" + iteration;
	el.src = cPath + "/images/cancel.png";
	el.width = "13";
	el.height = "13";
	el.onclick = function() {
		var rownum = this.parentNode.parentNode.rowIndex;
		removeChild(tbl, rownum);
		tbl.deleteRow(rownum);
	};
	cellRemoveBtn.appendChild(el);

	var cellRight1 = row.insertCell(4);
	var el = document.createElement('input');
	el.type = 'hidden';
	el.name = 'lbTypeChildValue';
	el.value = lbTypeChildValue.split("#")[0];
	cellRight1.appendChild(el);
	cellRight1.setAttribute('style', 'display:none');

	var cellRight2 = row.insertCell(5);
	var el = document.createElement('input');
	el.type = 'hidden';
	el.name = 'lbTypeParentValue';
	if (lbTypeParentValue == "-1") {
		el.value = lbTypeParentValue;
	} else {
		el.value = lbTypeParentValue.split("#")[0];
	}
	cellRight2.appendChild(el);
	cellRight2.setAttribute('style', 'display:none');

	var cellRight3 = row.insertCell(6);
	var el = document.createElement('input');
	el.type = 'hidden';
	el.name = 'levelHierarchyValue';
	el.value = levelHierarchyValue;
	cellRight3.appendChild(el);
	cellRight3.setAttribute('style', 'display:none');

};

removeChild = function(tbl, rownum) {
	var lblHierarchy = document.getElementsByName('levelHierarchyValue');
	var hierarchyLevel = lblHierarchy[rownum].value;
	var lbCodeObj = document.getElementsByName('lbTypeChildValue');
	var lbValue = lbCodeObj[rownum].value;
	var parentLbCodeObj = document.getElementsByName('lbTypeParentValue');
	for ( var i = 0; i < lbCodeObj.length; i++) {
		var modValue = lblHierarchy[i].value;
		if (modValue == hierarchyLevel) {
			if (lbValue == parentLbCodeObj[i].value) {
				removeChild(tbl, i);
				tbl.deleteRow(i);
			}

		}
	}
};

getHierarchyDetails = function() {
	var hierarchyDetails = "";
	var lbChild = document.getElementsByName('lbTypeChildValue');
	var lbParent = document.getElementsByName('lbTypeParentValue');
	var levelHierarchy = document.getElementsByName('levelHierarchyValue');
	for ( var i = 0; i < lbChild.length; i++) {
		var childValue = lbChild[i].value;
		var parentValue = lbParent[i].value;
		var hierarchyValue = levelHierarchy[i].value;
		hierarchyDetails += childValue;
		hierarchyDetails += "|";
		hierarchyDetails += parentValue;
		hierarchyDetails += "|";
		hierarchyDetails += hierarchyValue;
		hierarchyDetails += "@@";
	}
	return hierarchyDetails.substring(0, hierarchyDetails.length - 2);
};

getHierarchyLevels = function() {
	var hierarchyLevels = "";
	var levelHierarchy = document.getElementsByName('levelHierarchyValue');
	for ( var i = 0; i < levelHierarchy.length; i++) {
		var hierarchyValue = levelHierarchy[i].value;
		hierarchyLevels += hierarchyValue;
		hierarchyLevels += ",";
	}
	return hierarchyLevels.substring(0, hierarchyLevels.length - 1);
};

submitHirarchyDetails = function() {
	var errorMsg = document.getElementById('errorHierarchy');
	errorMsg.innerHTML = "";
	var hierarchyyValues = getHierarchyDetails();
	if (hierarchyyValues != "") {
		document.getElementById('lbRuralHirarchyValues').value = hierarchyyValues;
		document.getElementById('ruralHirarchyLevels').value = getHierarchyLevels();
		document.getElementById('build').disabled = true;
		document.getElementById('save').disabled = true;
		document.forms['lGRuralSetupHierarchyForm'].action = "lgManageRuralSetup.htm?<csrf:token uri='lgManageRuralSetup.htm'/>";
		document.forms['lGRuralSetupHierarchyForm'].method = "post";
		document.forms['lGRuralSetupHierarchyForm'].submit();
		return true;
	} else {
		errorMsg.innerHTML = "<b>No Hierarchy Defined, Please Defined at least Hierarchy !</b>";
		return false;
	}
};