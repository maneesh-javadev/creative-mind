var divlevel = 0;
var lblevel;
var fetchListIdlb;
var fetchListIdward;
var fetchlevel;
var selParentCodeList;
var partvlc=[];
var subdistrictPartMap =new Map();
var villagePartMap =new Map();	
var districtPartMap =new Map();	
function hideShowDivforWard(id, districtCode, lbtype) {

	var temp = id.split(":");

	var id1 = temp[0];
	var id2 = temp[1];
	var id3 = temp[2];
	var id4 = temp[4];
	var id5 = temp[5];
	var category = temp[3];
	lblevel = id2;
	// alert(id2);
	document.getElementById('cattype').value = category;
	document.getElementById('level').value = "";
	document.getElementById('lblc').value = "";
	document.getElementById('selLevel').value = id2;
	var statecode = parseInt(id3);
	document.getElementById('state').value = statecode;
	remove_error(0);
	document.getElementById('tr_district2').style.visibility = 'hidden';
	document.getElementById('tr_district2').style.display = 'none';
	document.getElementById('tr_village_dist').style.display = 'none';
	document.getElementById('tr_village_dist').style.visibility = 'hidden';
	document.getElementById('tr_village_inter').style.display = 'none';
	document.getElementById('tr_village_inter').style.visibility = 'hidden';
	document.getElementById('tr_village_pan').style.display = 'none';
	document.getElementById('tr_village_pan').style.visibility = 'hidden';
	document.getElementById('tr_district2').style.visibility = 'hidden';
	document.getElementById('tr_district2').style.display = 'none';
	if ((id2 == 'D' && category == 'P') || (id2 == 'D' && category == 'T')) {
		document.getElementById("firstlevel").innerHTML = " " + id5;
		document.getElementById("errSelectVIP").innerHTML = "Select " + id5;
		document.getElementById('tr_village_dist').style.display = 'block';
		document.getElementById('tr_village_dist').style.visibility = 'visible';
		if (id4 == 0)
			getdisPanchayatList(statecode, id1);

	} else if ((id2 == 'I' && category == 'P') || (id2 == 'I' && category == 'T')) {
		document.getElementById('level').value = 'I';
		document.getElementById("secondlevel").innerHTML = " " + id5;
		document.getElementById("errSelectVDP").innerHTML = "Select " + id5;
		document.getElementById('tr_village_inter').style.display = 'block';
		document.getElementById('tr_village_inter').style.visibility = 'visible';
		dwr.util.removeAllOptions('ddLgdLBInterListAtVillageCA');
		document.getElementById("ddLgdLBInterListAtVillageCA_error").innerHTML = "";
		if (id4 == 0)
			getdisInterPanchayatList(statecode, id1);
		else
			getLocalBodyListbylblcCode(statecode, id4);

	} else if ((id2 == 'V' && category == 'P') || (id2 == 'V' && category == 'T')) {

		document.getElementById("thirdlevel").innerHTML = " " + id5;
		document.getElementById("errSelectVVP").innerHTML = "Select " + id5;
		document.getElementById('tr_village_pan').style.display = 'block';
		document.getElementById('tr_village_pan').style.visibility = 'visible';
		dwr.util.removeAllOptions('ddLgdLBVillageSourceAtVillageCA');
		document.getElementById("ddLgdLBVillageSourceAtVillageCA_error").innerHTML = "";
		document.getElementById("ddLgdLBVillageSourceAtVillageCA_error1").innerHTML = "";

		if (id4 == 0) {
			divlevel = 1;
			getdisVillagePanchayatList(statecode, id1);
		}

		else {
			getLocalBodyListbylblcCode(statecode, id4);
		}

	} else if (id2 == 'I' && category == 'U') {

		document.getElementById('tr_district2').style.visibility = 'visible';
		document.getElementById('tr_district2').style.display = 'block';
		document.getElementById("urbanlevel").innerHTML = " " + id5;
		document.getElementById("errSelectUrban").innerHTML = "Select " + id5;

		getPanchayatListbyStateandlbTypeCode(id1, statecode);
	}

}

function getPanchayatListbyStateandlbTypeCode(id1, stateCode) { // State Code
	lgdDwrlocalBodyService.getPanchayatListbyStateandlbTypeCode(stateCode, id1, {
		callback : getPanchayatListbyStateandlbTypeCodeSuccess,
		errorHandler : getPanchayatListbyStateandlbTypeCodeError
	});
}

// data contains the returned value
function getPanchayatListbyStateandlbTypeCodeSuccess(data) {

	var fieldId = 'wardUrbanLocalBody';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('wardUrbanLocalBody');
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function getPanchayatListbyStateandlbTypeCodeError() {
	alert("No data found!");
}

// ########## get district panchayat list open ######################
function getdisPanchayatList(id1, id2) {
	// alert("district:"+id1+":"+id2);

	lgdDwrlocalBodyService.getPanchayatListbylblcCode(id1, id2, {
		callback : handledisPanchayatSuccess,
		errorHandler : handledisPanchayatError
	});
}

function handledisPanchayatSuccess(data) {
	// Assigns data to result id
	// alert("ti");
	// alert(data);
	var fieldId = 'ddLgdLBDistListAtVillageCA';
	// alert(data);
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById(fieldId);
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handledisPanchayatError() {
	// Show a popup message
	alert("No data found in District Panchayat!");
}

function remove_error(id) {

	/*
	 * var id=document.getElementById('ddLgdLBType').value;
	 * 
	 * 
	 * var temp = id.split(":"); var id2 = temp[1]; var category=temp];
	 */
	// alert("hizi"+id);
	/* document.getElementById("errSelectState").innerHTML = ""; */

	document.getElementById("ddLgdLBDistListAtVillageCA_error").innerHTML = "";
	document.getElementById("errSelectLocalBodyType").innerHTML = "";
	if (id == 0) {
		document.getElementById("errSelectVVP").style.visibility = 'hidden';
		document.getElementById('errSelectVVP').style.display = 'none';
	} else if (id == 1) {
		document.getElementById("errSelectLocalBody").innerHTML = "";
	} else if (id == 2) {
		document.getElementById("errSelectVDP").style.visibility = 'hidden';
		document.getElementById('errSelectVDP').style.display = 'none';
		document.getElementById("errSelectVIP").style.visibility = 'hidden';
		document.getElementById('errSelectVIP').style.display = 'none';
	} else if (id == 3) {

		document.getElementById("errSelectVDP").style.visibility = 'hidden';
		document.getElementById('errSelectVDP').style.display = 'none';
		document.getElementById("errSelectVIP").style.visibility = 'hidden';
		document.getElementById('errSelectVIP').style.display = 'none';
		document.getElementById("errSelectVVP").style.visibility = 'hidden';
		document.getElementById('errSelectVVP').style.display = 'none';

	} else {
		document.getElementById("errSelectUrban").style.visibility = 'hidden';
		document.getElementById('errSelectUrban').style.display = 'none';

	}

}

// ########## get district panchayat list close ######################

function getdisInterPanchayatList(id1, id2) {
	// alert("inter"+id1+":"+id2);

	lgdDwrlocalBodyService.getPanchayatListbylblcCode(id1, id2, {
		callback : handledisInterPanchayatSuccess,
		errorHandler : handledisInterPanchayatError
	});
}

function handledisInterPanchayatSuccess(data) {
	// Assigns data to result id
	// alert("ti");
	// alert(data);
	var fieldId = 'ddLgdLBInterListAtVillageCA';
	// alert(data);
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddLgdLBInterListAtVillageCA');
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handledisInterPanchayatError() {
	// Show a popup message
	alert("No data found in District Panchayat!");
}

// ########## get district panchayat list close ######################

function getLocalBodyListbylblcCode(statecode, lblc) {
	// alert("state:"+id);
	// remove_error(0);
	// setButton(true);
	lgdDwrDesignationService.getLocalbodyDetailbyCode(statecode, lblc, {
		callback : handleLocalbodylblcCodeSuccess,
	// errorHandler : handleLocalbodylblcCodeError
	});
}

function handleLocalbodylblcCodeSuccess(data) {
	// Assigns data to result id
	// alert("ti");
	// alert(data);
	var statecode = document.getElementById('state').value;
	var category = document.getElementById('cattype').value;
	var lblc = data[0].localBodyTypeCode;
	// alert(statecode);
	var plblc = data[0].parentTierSetupCode;
	var level = data[0].level;
	var name = data[0].localBodyTypeName;
	// alert(plblc);
	// alert(lblc);
	if (level == 'D') {
		document.getElementById("firstlevel").innerHTML = " " + name;
		document.getElementById("errSelectVIP").innerHTML = "Select " + name;
		// alert("inside1");
		if (plblc == 0) {
			// alert("inside2");
			document.getElementById('tr_village_dist').style.display = 'block';
			document.getElementById('tr_village_dist').style.visibility = 'visible';
			getdisVillagePanchayatList(statecode, lblc);
		}
	}

	else if (level == 'I') {
		document.getElementById("secondlevel").innerHTML = " " + name;
		document.getElementById("errSelectVDP").innerHTML = "Select " + name;

		document.getElementById('level').value = 'I';
		if (plblc == 0) {
			document.getElementById('tr_village_inter').style.display = 'block';
			document.getElementById('tr_village_inter').style.visibility = 'visible';
			dwr.util.removeAllOptions('ddLgdLBInterListAtVillageCA');
			getdisInterPanchayatList(statecode, lblc);
		} else {
			document.getElementById('tr_village_inter').style.display = 'block';
			document.getElementById('tr_village_inter').style.visibility = 'visible';
			dwr.util.removeAllOptions('ddLgdLBInterListAtVillageCA');
			getLocalBodyListbylblcCode(statecode, plblc);
		}
	}
	// alert(data);

}

function handleLocalbodylblcCodeError() {
	// Show a popup message
	alert("No data found in local body by lblc!");
}

// ########## get district panchayat list close ######################

function getdisVillagePanchayatList(id1, id2) {
	// alert("village:"+id1+":"+id2);

	lgdDwrlocalBodyService.getPanchayatListbylblcCode(id1, id2, {
		callback : handledisVillagePanchayatSuccess,
		errorHandler : handledisVillagePanchayatError
	});
}

function handledisVillagePanchayatSuccess(data) {
	// Assigns data to result id
	// alert("ti");
	// alert(data);
	var fieldId = 'ddLgdLBDistListAtVillageCA';
	if (divlevel == 1)
		fieldId = 'ddLgdLBVillageSourceAtVillageCA';
	divlevel = 0;
	// alert(data);
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById(fieldId);
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handledisVillagePanchayatError() {
	// Show a popup message
	alert("No data found in District Panchayat!");
}

// ########## get district panchayat list close ######################

function getLGBforCoveredVillageListExWard(id) {
	document.getElementById('lblc').value = id;
	// alert(document.getElementById('lblc').value);
	/* setButton(false); */
}

function callList(id) {
	category = document.getElementById('cattype').value;
	level = document.getElementById('level').value;
	if (lblevel == 'D') {
		document.getElementById('lblc').value = id;
	}

	// alert("callfun");
	// alert("cat:"+category+":level:"+level);
	if ((category == 'P' || category == 'T') && (level == 'I')) {
		getInterPanchayatbyDcodeAtVWard(id, 'inter');

	} else {
		getVillagePanchWardbyIntercode(id);
	}

}

function getInterPanchayatbyDcodeAtVWard(id, val) {

	if (val == '') {
		getVillagePanchWardbyIntercode(id);
	} else {
		lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
			callback : handleInterPWardVCASuccess,
			errorHandler : handleInterPWardVCAError
		});
	}
}

function handleInterPWardVCASuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddLgdLBInterListAtVillageCA';

	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);

	var st = document.getElementById('ddLgdLBInterListAtVillageCA');
	st.add(new Option('Select Intermediate Panchayat', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleInterPWardVCAError() {
	// Show a popup message
	alert("No data found!");
}

function getVillagePanchWardbyIntercode(id) {

	level = document.getElementById('level').value;
	if (lblevel == 'I') {
		document.getElementById('lblc').value = id;
	}

	lgdDwrlocalBodyService.getchildlocalbodiesWithoutCountByParentcode(id, {
		callback : handleVillagePanchWardbyIntercodeSuccess,
		errorHandler : handleVillagePanchWardbyIntercodeError
	});
}

function handleVillagePanchWardbyIntercodeSuccess(data) {
	// Assigns data to result id

	var fieldId = 'ddLgdLBVillageSourceAtVillageCA';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddLgdLBVillageSourceAtVillageCA');
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleVillagePanchWardbyIntercodeError() {
	// Show a popup message
	alert("No data found!");
}

function getCovereSubDistUrbanExWardList(id) {
	remove_error(0);
	document.getElementById('lblc').value = id;
}

function validateSelectWardCoverage() {
	lblcOjb = document.getElementById('lblc');
	if (lblcOjb != null) {
		if (lblcOjb.value == null || lblcOjb.value == "") {
			$('#errorMsg').show().html('Please Select Local Body');

			return false;
		} else {
			$('#btnGet').attr('disabled', true);
			var form = document.getElementById('form1');
			$("#btnGet").attr("disabled", "disabled");
			form.submit();
			return true;
		}
	}
}

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

function editWard(wardCode,lblc) {
	
	lgdDwrWardService.checkWardCoverageExistinLocalbody(parseInt(lblc), {
			callback : function(result) {
				if(result){
					$("#wardCode").val(wardCode);
					callActionUrl('addnModifyWardCoverage.htm','wardCoverageForm');
					
				}
				else{
					showDialogBox("Currently there is no coverage added for selected ward");
	  	 		  	
				}
		},
		async : false
	});
	
}

function addItemforLBWardCoverage(listId, coverageListId, coverageType) {
	listObj = document.getElementById(listId);
	coverageListObj = document.getElementById(coverageListId);
	errorFlag = false;
	if (listObj.selectedIndex >= 0) {
		for ( var j = 0; j < listObj.options.length; j++) {
			if (listObj.options[j].selected == true) {
				if (coverageType == "Full" && listObj[j].text.match("Part")) {
					errorFlag = true;
				} else {
					$('#' + coverageListId).append(
							"<option value=" + listObj[j].value + "_" + coverageType + ">" + listObj[j].text + " (" + coverageType + ")</option>");
				}

			}

		}

		if (errorFlag) {
			alert("You can not select FULL of Partially Mapped Land Region");
		} else {
			removeSelectedValueinList(listObj);
		}

	}
}

function backItemfromLBWardCoverage(listId, coverageListId, type) {
	listObj = document.getElementById(listId);
	coverageListObj = document.getElementById(coverageListId);

	if (coverageListObj.selectedIndex >= 0) {
		for ( var j = 0; j < coverageListObj.options.length; j++) {
			if (coverageListObj.options[j].selected == true) {

				rtext = coverageListObj[j].text;
				rvalue = coverageListObj[j].value;
				if (rtext.indexOf(")") != rtext.lastIndexOf(")")) {
					rvalue = rvalue.substring(0, rvalue.indexOf("_") > 0 ? (rvalue.indexOf("_")) : (rvalue.length - 1));
					rtext = rtext.substring(0, rtext.indexOf(")") > 0 ? (rtext.indexOf(")") + 1) : (rtext.length - 1));
					// alert("<option value=" + rvalue + ">" + rtext +
					// "</option>");
					$('#' + listId).append("<option value=" + rvalue + ">" + rtext + "</option>");
				} else {
					rvalue = rvalue.substring(0, rvalue.indexOf("_") > 0 ? (rvalue.indexOf("_")) : (rvalue.length - 1));
					rtext = rtext.substring(0, rtext.indexOf("(") > 0 ? (rtext.indexOf("(")) : (rtext.length - 1));
					// alert("<option value=" + rvalue + ">" + rtext +
					// "</option>");
					$('#' + listId).append("<option value=" + rvalue + ">" + rtext + "</option>");
				}

			}

		}
		switch (type) {
		case 'D':
			$("#localbodyCoverageSubdistrictList").empty();
			$("#wardCoverageSubdistrictList").empty();
			$("#localbodyCoverageVillageList").empty();
			$("#wardCoverageVillageList").empty();
			break;
		case 'T':
			$("#localbodyCoverageVillageList").empty();
			$("#wardCoverageVillageList").empty();
			break;
		case 'V':
			// $("#wardCoverageVillageList").empty();
			break;
		}
		sortListBox(listObj);
		removeSelectedValueinList(coverageListObj);

	}
}

function backAllItemfromLBWardCoverage(listId, coverageListId, type) {
	listObj = document.getElementById(listId);
	coverageListObj = document.getElementById(coverageListId);

	for ( var j = 0; j < coverageListObj.options.length; j++) {

		rtext = coverageListObj[j].text;
		rvalue = coverageListObj[j].value;
		if (rtext.indexOf(")") != rtext.lastIndexOf(")")) {
			rvalue = rvalue.substring(0, rvalue.indexOf("_") > 0 ? (rvalue.indexOf("_")) : (rvalue.length - 1));
			rtext = rtext.substring(0, rtext.indexOf(")") > 0 ? (rtext.indexOf(")") + 1) : (rtext.length - 1));
			// alert("<option value=" + rvalue + ">" + rtext + "</option>");
			$('#' + listId).append("<option value=" + rvalue + ">" + rtext + "</option>");
		} else {
			rvalue = rvalue.substring(0, rvalue.indexOf("_") > 0 ? (rvalue.indexOf("_")) : (rvalue.length - 1));
			rtext = rtext.substring(0, rtext.indexOf("(") > 0 ? (rtext.indexOf("(")) : (rtext.length - 1));
			// alert("<option value=" + rvalue + ">" + rtext + "</option>");
			$('#' + listId).append("<option value=" + rvalue + ">" + rtext + "</option>");
		}

	}
	switch (type) {
	case 'D':
		$("#localbodyCoverageSubdistrictList").empty();
		$("#wardCoverageSubdistrictList").empty();
		$("#localbodyCoverageVillageList").empty();
		$("#wardCoverageVillageList").empty();
		break;
	case 'T':
		$("#localbodyCoverageVillageList").empty();
		$("#wardCoverageVillageList").empty();
		break;
	case 'V':
		$("#wardCoverageVillageList").empty();
		break;
	}
	sortListBox(listObj);
	removeAllValueinList(coverageListObj);

}

function removeSelectedValueinList(listObj) {
	var i;
	for (i = listObj.length - 1; i >= 0; i--) {
		if (listObj.options[i].selected == true) {
			listObj.remove(i);
		}
	}
}

function removeAllValueinList(listObj) {
	var i;
	for (i = listObj.length - 1; i >= 0; i--) {

		listObj.remove(i);

	}
}

function sortListBox(box) {
	var temp_opts = new Array();
	var temp = new Object();
	for ( var i = 0; i < box.options.length; i++) {
		temp_opts[i] = box.options[i];
	}
	for ( var x = 0; x < temp_opts.length - 1; x++) {
		for ( var y = (x + 1); y < temp_opts.length; y++) {
			if (temp_opts[x].text > temp_opts[y].text) {
				tempT = temp_opts[x].text;
				tempV = temp_opts[x].value;
				temp_opts[x].text = temp_opts[y].text;
				temp_opts[x].value = temp_opts[y].value;
				temp_opts[y].text = tempT;
				temp_opts[y].value = tempV;
			}
		}
	}
	for ( var i = 0; i < box.options.length; i++) {
		box.options[i].text = temp_opts[i].text;
		box.options[i].value = temp_opts[i].value;
	}
}

function coverageDetail(selParentid, childIdlb, childIdward, level) {
	listObj = document.getElementById(selParentid);
	fetchListIdlb = childIdlb;
	fetchListIdward = childIdward;
	fetchlevel = level;
	var wardCode=$("#wardCode").val();
	var lblc=$("#lblc").val();
	//alert(lblc);
	var distList = '';
	for ( var j = 0; j < listObj.options.length; j++) {
		/* if (listObj.options[j].selected == true) { */
		distList = distList + listObj[j].value + "@@";
		listObj.options[j].selected = true;
		/* } */
	}
	selParentCodeList = distList;
	if (distList != '' && distList.match("Part")) {
		if (level == 'T') {
			$("#localbodyCoverageSubdistrictList").empty();
			$("#wardCoverageSubdistrictList").empty();
			$("#localbodyCoverageVillageList").empty();
			$("#wardCoverageVillageList").empty();
		} else if (level == 'V') {
			$("#localbodyCoverageVillageList").empty();
			$("#wardCoverageVillageList").empty();
		}
		//alert(lblc);
		lgdDwrWardService.getWardCoverageDetailbyLevel( parseInt(lblc),parseInt(wardCode), level, distList, false, {
			callback : handlecoverageDetailSuccess,
			errorHandler : handlecoverageDetailError
		});
	} else {
		alert("Please Select PART to get Covered areas");
	}

}

function handlecoverageDetailSuccess(data) {
	dwr.util.removeAllOptions(fetchListIdlb);
	 var options = $("#"+fetchListIdlb);
	
	jQuery.each(data, function(index, obj) {
		var option = $("<option />");
		if(obj.coverageType!=null && obj.coverageType=='P'){
			var setOptValue = obj.entityCode + "_" + "Part";
			option.val(setOptValue).text(obj.entityName+"(Part)");
			/*if (obj.coverageType == 'F') {
				$(option).attr('disabled', 'disabled');
			}*/
		}else{
			var setOptValue = obj.entityCode ;
			option.val(setOptValue).text(obj.entityName);
		}
		
		
		/* if (_JS_LOCAL_BODY_CREATION_TYPE != obj.lbType) {
			option.attr("disabled", true);
		} */
		options.append(option);
	});
	

	if (data != null) {

		var lbCode = document.getElementById('lblc').value;
		var wardCode = document.getElementById('wardCode').value;
		lgdDwrWardService.getWardCoverageDetailbyLevel(parseInt(lbCode), parseInt(wardCode), fetchlevel, selParentCodeList, true, {
			callback : handleWardCoverageSuccess,
			errorHandler : handleWardcoverageError
		});
	}

}

function handleWardCoverageSuccess(data) {
	
	dwr.util.removeAllOptions(fetchListIdward);
	 var options = $("#"+fetchListIdward);
	
	jQuery.each(data, function(index, obj) {
		var option = $("<option />");
		if(obj.coverageType!=null){
			var coverageType=obj.coverageType=='P'?"Part":"Full";
			var setOptValue = obj.entityCode + "_" + coverageType;
			option.val(setOptValue).text(obj.entityName+"("+coverageType+")");
			/*if (obj.coverageType == 'F') {
				$(option).attr('disabled', 'disabled');
			}*/
		}else{
			var setOptValue = obj.entityCode ;
			option.val(setOptValue).text(obj.entityName);
		}
		
		
		/* if (_JS_LOCAL_BODY_CREATION_TYPE != obj.lbType) {
			option.attr("disabled", true);
		} */
		options.append(option);
	});
	
	
	/*var fieldId = fetchListIdward;
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, 'entityCode', 'entityName');*/

}

function handleWardcoverageError() {
	alert("No data found!");
}

function handlecoverageDetailError() {
	alert("No data found!");
}

function validateSaveCoverageWard() {
	covDisListObj = document.getElementById('wardCoverageDistrictList');
	covSubdisListObj = document.getElementById('wardCoverageSubdistrictList');
	covVillListObj = document.getElementById('wardCoverageVillageList');
	selLevelObj = document.getElementById('selLevel');
	selLevel = selLevelObj != null ? selLevelObj.value : null;

	if (covDisListObj != null) {
		for ( var j = 0; j < covDisListObj.options.length; j++) {
			covDisListObj.options[j].selected = true;
		}
	}

	if (covSubdisListObj != null) {
		for ( var j = 0; j < covSubdisListObj.options.length; j++) {
			covSubdisListObj.options[j].selected = true;
		}
	}

	if (covVillListObj != null) {
		for ( var j = 0; j < covVillListObj.options.length; j++) {
			covVillListObj.options[j].selected = true;
		}
	}

	if (selLevel == 'D' && covDisListObj != null && covDisListObj.value != "" || (selLevel == 'T' ||selLevel == 'I')  && covSubdisListObj != null
			&& covSubdisListObj.value != "" || selLevel == 'V' && covVillListObj != null && covVillListObj.value != "") {

		var form = document.getElementById('wardCoverageForm');
		$("#btnSave").attr("disabled", "disabled");
		form.submit();
	} else {
		$('#errorMsg').show().html('Please Select mandatory fields(*)');
	}

}


/**
 * The {@code moveElement} function used move items from one listbox to another,
 * based on copyid,pasteId and action(parameter)
 */
moveElement=function(copyId,pasteId,action){
	
	
	 if(action=="PART" ){
		//$('#'+copyId+'> option:selected').appendTo('#'+pasteId);
		var options = $("#"+pasteId); 
		$('#'+copyId+' option:selected').each(function() { 
			var option = $("<option />");
			_value=$(this).val();
			_text=$(this).text();
			if(!(parseInt($(this).val().indexOf("Part"))>-1)){
				_value=$(this).val()+"_Part";
				_text=$(this).text()+"(PART)";
				_v=$(this).val();
				if(copyId.indexOf("Subdistrict")>0){
					if(!subdistrictPartMap.has(_v)){
						subdistrictPartMap.set(_v,_v);
					}
					
				}else if(copyId.indexOf("Village")>0){ 
					if(!villagePartMap.has(_v)){
						villagePartMap.set(_v,_v);
					}
					
				}else if(copyId.indexOf("District")>0){ 
					if(!districtPartMap.has(_v)){
						districtPartMap.set(_v,_v);
					}
					
				}
			}else{
				
				partvlc.push($(this).val().split("_")[0]);
				_v=$(this).val().split("_")[0];
				if(copyId.indexOf("Subdistrict")>0){
					if(!subdistrictPartMap.has(_v)){
						subdistrictPartMap.set(_v,_v);
					}
				}else if(copyId.indexOf("Village")>0){ 
					if(!villagePartMap.has(_v)){
						villagePartMap.set(_v,_v);
					}
				}else if(copyId.indexOf("District")>0){ 
					if(!districtPartMap.has(_v)){
						districtPartMap.set(_v,_v);
					}
				}
			}
			
			
			option.val(_value).text(_text);
			options.append(option);
			
			$(this).remove();
			
			});
	}else if(action=="Full" ){
		//$('#'+copyId+'> option:selected').appendTo('#'+pasteId);
		var options = $("#"+pasteId);
		var partToFullFlag=false;
		var _part_list="";
		$('#'+copyId+' option:selected').each(function() { 
			
			if(!(parseInt($(this).val().indexOf("Part"))>-1)){
				var option = $("<option />");
				_value=$(this).val()+"_Full";
				_text=$(this).text()+"(Full)";
				option.val(_value).text(_text);
				options.append(option);
				$(this).remove();
			}else{
				partToFullFlag=true;
				_temp=$(this).text();
				_part_list=_part_list+_temp.substring(0,_temp.indexOf("("))+",";
				
			}
			
			
			});
		if(partToFullFlag){
			_part_list=_part_list.substring(0,_part_list.length-1);
			if(copyId.indexOf("Subdistrict")>0){
				entity="Subdistrict";
			}else if(copyId.indexOf("Village")>0){ 
				entity="Village";
			}else if(copyId.indexOf("District")>0){ 
				entity="District";
			}
			
			
			alert(_part_list+" "+entity+"(s) already part contributing");
			
		}
		
	}else if(action=="BACK" ){
		var options = $("#"+pasteId); 
		
		if(copyId.indexOf("District")>0){
			removeAllOptions('localbodyCoverageSubdistrictList');
			removeAllOptions('wardCoverageSubdistrictList');
			removeAllOptions('localbodyCoverageVillageList');
			removeAllOptions('wardCoverageVillageList');
		}else if(copyId.indexOf("Subdistrict")>0){
			removeAllOptions('localbodyCoverageVillageList');
			removeAllOptions('wardCoverageVillageList');
		}
		
		
		$('#'+copyId+' option:selected').each(function() { 
			var option = $("<option />");
			_tmpv=$(this).val();
			_tmpt=$(this).text();
			
			_value=_tmpv;
			_text=_tmpt;
			
			
			if(!partvlc.includes(_tmpv.split("_")[0])){
				ispart=false;
				if(copyId.indexOf("Subdistrict")>0){
					if(subdistrictPartMap.has(_tmpv.split("_")[0])){
						ispart=true;
						subdistrictPartMap.delete(lRCode);
					}
					
				}else if(copyId.indexOf("Village")>0){ 
					if(villagePartMap.has(_tmpv.split("_")[0])){
						ispart=true;
						villagePartMap.delete(lRCode);
					}
					
				}else if(copyId.indexOf("District")>0){ 
					if(districtPartMap.has(_tmpv.split("_")[0])){
						ispart=true;
						districtPartMap.delete(lRCode);
					}
					
				}
				
				if(ispart){
					_value=_tmpv.split("_")[0];
					_text=_tmpt.substring(0, _tmpt.lastIndexOf("("));
				}
				
				
			}
			
			option.val(_value).text(_text);
			options.append(option);
			$(this).remove();
			});
	}else if(action=="RESET" ){
		var options = $("#"+pasteId); 
		
		if(copyId.indexOf("District")>0){
			removeAllOptions('localbodyCoverageSubdistrictList');
			removeAllOptions('wardCoverageSubdistrictList');
			removeAllOptions('localbodyCoverageVillageList');
			removeAllOptions('wardCoverageVillageList');
		}else if(copyId.indexOf("Subdistrict")>0){
			removeAllOptions('localbodyCoverageVillageList');
			removeAllOptions('wardCoverageVillageList');
		}
		
		$('#'+copyId+' option').each(function() { 
			var option = $("<option />");
			_tmpv=$(this).val();
			_tmpt=$(this).text();
			
			_value=_tmpv;
			_text=_tmpt;
			
			
			
			
			
			
			if(!partvlc.includes(_tmpv.split("_")[0])){
				ispart=false;
				_v=_tmpv.split("_")[0];
				if(copyId.indexOf("Subdistrict")>0){
					if(subdistrictPartMap.has(_v)){
						ispart=true;
						subdistrictPartMap.delete(_v);
					}
					
				}else if(copyId.indexOf("Village")>0){ 
					if(villagePartMap.has(_v)){
						ispart=true;
						villagePartMap.delete(_v);
					}
					
				}else if(copyId.indexOf("District")>0){ 
					if(districtPartMap.has(_v)){
						ispart=true;
						districtPartMap.delete(_v);
					}
					
				}
				
				if(ispart){
					_value=_tmpv.split("_")[0];
					_text=_tmpt.substring(0, _tmpt.lastIndexOf("("));
				}
				
			}
			
			option.val(_value).text(_text);
			options.append(option);
			$(this).remove();
			});
	}
	sortListBox(copyId);
	sortListBox(pasteId);
		
	
};


/* The {@code $_checkEmptyObject} used to check object / element  
* is empty or undefined.
*/
var $_checkEmptyObject = function(obj) {
	if (jQuery.type(obj) === "undefined" || (obj == null || $.trim(obj).length == 0)) {
		return true;
	}
	return false;
};

/**
 * The {@code sortListBox} function used sort items of listbox ,
 * based on listbox id
 */
sortListBox=function(id){
	 var $r = $("#"+id+" option");
    $r.sort(function(a, b) {
        if (a.text < b.text) return -1;
        if (a.text == b.text) return 0;
        return 1;
    });
    $($r).remove();
    $("#"+id).append($($r));
    
    
};

/* The {@code removeAllOptions} used to clear drop down box based on their id. 
 */
 removeAllOptions=function(id){
 	dwr.util.removeAllOptions(id);
 };



