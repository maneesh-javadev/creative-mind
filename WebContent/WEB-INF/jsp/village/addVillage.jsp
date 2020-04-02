<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="addVillageJs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<title><spring:message code="Label.CREATENEWVILLAGE"
		htmlEscape="true"></spring:message></title>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrCoveredLandRegionByULBService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>	
	
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageServiceForDraftVillageList.js'></script>

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSurveyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageServiceForDraft.js'></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<link href="css/error.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/govtorder.js"></script>
<script type="text/javascript" src="datepicker/jquery-1.6.2.js"
	charset="utf-8"></script>
<script type="text/javascript" src="datepicker/jquery.ui.core.js"></script>
<script type="text/javascript" src="datepicker/jquery.ui.widget.js"></script>
<script type="text/javascript" src="datepicker/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="datepicker/calender.js"></script>
<!-- <script type="text/javascript" src="/js/common.js" charset="utf-8"></script> -->
<link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<!-- <script src="js/trim-jquery.js"></script> -->
<link rel="stylesheet" href="datepicker/demos.css" />

<script type="text/javascript" language="javascript">

function hideAll()
{
	
$("#EffectiveFutureDate_error").hide();
$("#EffectiveDateBlank_error").hide();
$("#EffectiveDateData_error").hide();
}


 if ( window.addEventListener ) { 
     window.addEventListener( "load",hideAll, false );
  }
  else 
     if ( window.attachEvent ) { 
        window.attachEvent( "onload", hideAll );
  } else 
        if ( window.onLoad ) {
           window.onload = hideAll;
  }

function validateDatetoFuture(id,diverror)
{
	var entityDate=document.getElementById(id).value;
	 $(diverror).hide();
	 if(!compareDateforFutureDDMMYYY(entityDate)){ 
			$(diverror).show();
			document.getElementById(id).value="";
			
			
	 }
	 
}

function validateEffectiveDatecompOrderDate(orderdateId,effectivedateID,diverror)
{
	 var orderDate=document.getElementById(orderdateId).value;
	 var effectiveDate=document.getElementById(effectivedateID).value;
	if(orderDate!="") 
		{
		$(diverror).hide();	
		
			if (compareDateYYMMDD(orderDate, effectiveDate, 'dd-mm-yyyy'))
		{
				$(diverror).show();
				document.getElementById(effectivedateID).value=orderDate;
				
			}
		}
	 
	 
}
	function open_win() {

		var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '',
				"dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
		//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
	}
	dwr.engine.setActiveReverseAjax(true);
	$(document).ready(function() {
		hideTable();
		if('${subDistrictCode}' !=''){
			getVillageList('${subDistrictCode}');
		}
		/* var s = document.getElementById("flag2").value;
		if (s > 0)
			getDisUserSubDistrictList(s); */
	});
	
	function hideTable() {
		document.getElementById('surveytable').style.visibility = "hidden";
	}
	function incrementCount() {
		document.getElementById('surveytable').style.visibility = "visible";
		document.getElementById("errBlockCode").innerHTML = "";
		var ptable = document.getElementById('ptablePerson');
		var numFlds = document.addVillageNew.surveyNumber.length;
		if (numFlds > 9) {
			alert("Cannot Add More Survey No");
			return false;
		}
		for ( var x = 0; x < numFlds; x++) {
			for ( var y = x + 1; y < numFlds; y++) {
				if (document.addVillageNew.surveyNumber[x].value == document.addVillageNew.surveyNumber[y].value) {
					document.getElementById("errBlockCode").innerHTML = "<font color='red'><br><spring:message code="Error.Unique.SurveNo"/></font>";
					document.addVillageNew.surveyNumber[y].value = "";
					return false;
				}
			}
		}
		var lastElement = ptable.rows.length;
		var index = lastElement;
		var row = ptable.insertRow(lastElement);
		var cellLeft = row.insertCell(0);
		var textNode = document.createTextNode(index);
		cellLeft.appendChild(textNode);
		var cellText = row.insertCell(1);
		var element = document.createElement('input');
		element.type = 'text';
		element.name = 'surveyNumber';
		element.setAttribute("class", "frmfield");
		element.id = 'person' + index;
		element.size = 26;
		element.setAttribute('maxlength', '10');
		element.onblur = function() {
			checkmultipleSno(element.id);
		};
		cellText.appendChild(element);
		document.getElementById("psize").value = index;

	}
	function removeRow() {
		document.getElementById("errBlockCode").innerHTML = "";
		var ptable = document.getElementById('ptablePerson');
		var lastElement = ptable.rows.length;
		if (lastElement > 2)
			ptable.deleteRow(lastElement - 1);
		if (document.getElementById("psize").value > 1) {
			document.getElementById("psize").value = document.getElementById("psize").value - 1;
		}
		if (document.getElementById("psize").value < 2) {
			hideTable();
		}
	}

	function getSubdistrictCode() {
		lgdDwrVillageService.getSubdistrict({
			async : false,
			callback : handleSubDistrictDraftSuccess,
		});
	}

	function getSubDistrictList(id) {
		lgdDwrSubDistrictService.getSubDistrictListForLocalbody(id, {
			async : false,
			callback : handleSubDistrictSuccess,
			errorHandler : handleSubDistrictError
		});
	}

	function getVillageListCodes() {
		lgdDwrVillageService.getSelectedVillages({
			async : false,
			callback : handleVillageListDraftSuccess,
		});
	}

	function getSubdistrictInformation(value) {
		getVillageList(value);
		if (document.getElementById('villageListMain').size > 0) {
			document.getElementById('chkcvillage').checked = true;
			document.getElementById('chknothing').checked = false;
			toggledisplay("chkcvillage", "cvillage");
			getVillageListCodes();
		}
	}

	function handleSubDistrictDraftSuccess(data) {
		document.getElementById('ddSubdistrict').value = data;
		getSubdistrictInformation(data);
	}

	function handleVillageListDraftSuccess(data) {
		var list = "" + data;
		setTimeout("repopulateSelectedVillages('" + list + "')", 1000);
		//repopulateSelectedVillages(list);
	}

	function repopulateSelectedVillages(list) {
		var villageList = list.split(',');
		for ( var i = 0; i < villageList.length; i++) {
			var tempVillageCode = villageList[i].substring(0, villageList[i].length - 4);
			var tempVillageCoverage = villageList[i].substring(villageList[i].length - 4);
			document.getElementById('villageListMain').value = tempVillageCode;
			if (tempVillageCoverage == "FULL")
				addItem('villageListNew', 'villageListMain', 'FULL', true);
			else if (tempVillageCoverage == "PART")
				addItem('villageListNew', 'villageListMain', 'PART', true)
		}
	}

	function callSaveDraft() {
		selectall();
		document.getElementById('form1').action = "saveAsDraftVillage.htm";
		document.forms['form1'].submit();
	}

	function callGetDraft() {
		document.getElementById('form1').action = "publishSaveAsDraftVillage.htm";
		document.forms['form1'].submit();
	}

	function getDraftData() {

		if (document.getElementById('ddDistrict').selectedIndex > 0) {
			getSubDistrictandULBList(document.getElementById('ddDistrict').value);
			getSubdistrictCode();
			//alert('hi');

			var surveyNumbers = document.getElementById('surveyNumber').value.split(',');
			document.getElementById('surveyNumber').value = surveyNumbers[0];

			for ( var i = 1; i < surveyNumbers.length; i++)
				addsurveys();

			//Can't put in same for loop as dynamic boxes should be made 1st completely
			for ( var i = 1; i < surveyNumbers.length; i++)
				document.getElementsByName('surveyNumber').item(i).value = surveyNumbers[i];

			var gisLatitudes = document.getElementsByName('latitude').item(0).value.split(',');
			var gisLongitudes = document.getElementsByName('longitude').item(0).value.split(',');

			for ( var i = 1; i < gisLatitudes.length; i++)
				addgisnodes();

			for ( var i = 0; i < gisLatitudes.length; i++) {
				document.getElementsByName('latitude').item(i).value = gisLatitudes[i];
				document.getElementsByName('longitude').item(i).value = gisLongitudes[i];
			}

		}

	}

	function fillVillage(num, villName) {

		if (document.getElementById("reNameFlagId" + num).checked == true) {
			var split = villName.substring(0, villName.length - 6);
			var VillageName = split.replace(/^\s+|\s+$/g, "");
			document.getElementById("reNamedVillId" + num).value = VillageName;
			document.getElementById("reNamedVillIdNew" + num).disabled = false;

		} else {
			if (document.getElementById("reNameFlagId" + num).checked == false) {
				document.getElementById("reNamedVillId" + num).value = "";
				document.getElementById("reNamedVillIdNew" + num).disabled = true;
				document.getElementById("reNamedVillIdNew" + num).value = "";
			}
		}

	}
	var renameListOnLoadVal;
	function renameListMethod(val) {
		if (val == 1) {
			oList = document.addVillageNew.elements["contributedVillages"];
			if (oList.options.length > 0) {

				var table = document.getElementById("renameList");
				table.style.display = "inline";
				n = table.rows.length;

				for ( var i = table.rows.length - 1; i > 0; i--) {
					table.deleteRow(i);

				}

				if (n >= 1) {
					var count = 0;

					for ( var i = 0; i < oList.options.length; i++) {

						var village = oList.options[i].value;
						var villageText = oList.options[i].text;

						if (village.indexOf("PART") != -1) {

							var rowCount = table.rows.length;
							var row = table.insertRow(rowCount);

							var cell1 = row.insertCell(0);
							cell1.innerHTML = rowCount;

							var cell2 = row.insertCell(1);
							var element1 = document.createElement("input");
							element1.type = "text";
							element1.name = "villName" + count;
							element1.id = "villName" + count;
							element1.value = villageText;
							element1.size = 30;
							element1.setAttribute('readonly', 'readonly');
							element1.setAttribute('class', 'frmfield');
							cell2.appendChild(element1);

							var cell3 = row.insertCell(2);
							var element2 = document.createElement("input");
							element2.name = "reNameFlag" + count;
							element2.id = "reNameFlagId" + count;
							element2.type = "checkbox";
							element2.setAttribute('onClick', "fillVillage(" + count + ",'" + villageText + "')"); //function(){fillVillage(rowCount,villageText);};
							cell3.appendChild(element2);
							cell3.setAttribute('align', 'center');

							var cell4 = row.insertCell(3);
							var element3 = document.createElement("input");
							element3.type = "text";
							element3.name = "reNamedVillName" + count;
							element3.id = "reNamedVillId" + count;
							element3.setAttribute('class', 'frmfield');
							element3.setAttribute('disabled', 'true');
							cell4.appendChild(element3);

							var cell5 = row.insertCell(4);
							var element4 = document.createElement("input");
							element4.type = "text";
							element4.name = "reNamedVillNameNew" + count;
							element4.id = "reNamedVillIdNew" + count;
							element4.setAttribute('class', 'frmfield');
							element4.setAttribute('disabled', 'true');
							cell5.appendChild(element4);

							var cell6 = row.insertCell(5);
							var element5 = document.createElement("input");
							element5.type = "hidden";
							element5.name = "villId" + count;
							element5.id = "villId" + count;
							element5.value = village;
							element5.setAttribute('class', 'frmfield');
							cell5.appendChild(element5);
							count++;
							document.getElementById("count").value = count;
						}

					}

				}
			} else {
				document.getElementById("rename").checked = false;
				alert("Please choose contributing Villages");
			}

		}

		else {
			var table = document.getElementById("renameList");
			table.style.display = "none";
		}
	}

	function renameListMethodOnLoad(val, renameList) {

		renameListOnLoadVal = renameList;
		if (val == 1) {
			try {
				oList = document.addVillageNew.elements["contributedVillages"];
			} catch (err) {
				alert(err);
			}
			if (oList.options.length > 0) {
				var table = document.getElementById("renameList");
				table.style.display = "inline";
				n = table.rows.length;
				for ( var i = table.rows.length - 1; i > 0; i--) {
					table.deleteRow(i);
				}
				if (n >= 1) {
					var count = 0;
					for ( var i = 0; i < oList.options.length; i++) {

						var village = oList.options[i].value;
						var villageText = oList.options[i].text;
						if (village.indexOf("PART") != -1) {
							var renameFlag = false;
							var renameSplit = renameList.split(",");
							var renameVilNameSplit = "";
							var reNameVilNameChanged = "";
							for ( var j = 0; j < renameSplit.length; j++) {
								renameVilNameSplit = renameSplit[j].split(":");
								for ( var k = 0; k < renameVilNameSplit.length; k++) {
									if ((village.substr(0, village.length - 4)) == renameVilNameSplit[0]) {
										reNameVilNameChanged = renameVilNameSplit[1];
										renameFlag = true;
										break;
									}
								}
							}

							if (renameFlag == true) {
								var rowCount = table.rows.length;
								var row = table.insertRow(rowCount);

								var cell1 = row.insertCell(0);
								cell1.innerHTML = rowCount;

								var cell2 = row.insertCell(1);
								var element1 = document.createElement("input");
								element1.type = "text";
								element1.name = "villName" + count;
								element1.id = "villName" + count;
								element1.value = villageText;
								element1.size = 30;
								element1.setAttribute('readonly', 'readonly');
								element1.setAttribute('class', 'frmfield');
								cell2.appendChild(element1);

								var cell3 = row.insertCell(2);
								var element2 = document.createElement("input");
								element2.name = "reNameFlag" + count;
								element2.id = "reNameFlagId" + count;
								element2.type = "checkbox";
								element2.checked = true;
								element2.setAttribute('onClick', "fillVillage(" + count + ",'" + villageText + "')"); //function(){fillVillage(rowCount,villageText);};
								cell3.appendChild(element2);
								cell3.setAttribute('align', 'center');

								var split = villageText.substring(0, villageText.length - 6);
								var VillageName = split.replace(/^\s+|\s+$/g, "");

								var cell4 = row.insertCell(3);
								var element3 = document.createElement("input");
								element3.type = "text";
								element3.name = "reNamedVillName" + count;
								element3.id = "reNamedVillId" + count;
								element3.value = VillageName;
								element3.setAttribute('class', 'frmfield');
								element3.setAttribute('disabled', 'true');
								cell4.appendChild(element3);

								var cell5 = row.insertCell(4);
								var element4 = document.createElement("input");
								element4.type = "text";
								element4.name = "reNamedVillNameNew" + count;
								element4.id = "reNamedVillIdNew" + count;
								element4.value = reNameVilNameChanged;
								element4.setAttribute('class', 'frmfield');
								//element4.setAttribute('disabled', 'false');
								cell5.appendChild(element4);

								var cell6 = row.insertCell(5);
								var element5 = document.createElement("input");
								element5.type = "hidden";
								element5.name = "villId" + count;
								element5.id = "villId" + count;
								element5.value = village;
								element5.setAttribute('class', 'frmfield');
								cell5.appendChild(element5);
								count++;
								document.getElementById("count").value = count;

							} else {

								var rowCount = table.rows.length;
								var row = table.insertRow(rowCount);

								var cell1 = row.insertCell(0);
								cell1.innerHTML = rowCount;

								var cell2 = row.insertCell(1);
								var element1 = document.createElement("input");
								element1.type = "text";
								element1.name = "villName" + count;
								element1.id = "villName" + count;
								element1.value = villageText;
								element1.size = 30;
								element1.setAttribute('readonly', 'readonly');
								element1.setAttribute('class', 'frmfield');
								cell2.appendChild(element1);

								var cell3 = row.insertCell(2);
								var element2 = document.createElement("input");
								element2.name = "reNameFlag" + count;
								element2.id = "reNameFlagId" + count;
								element2.type = "checkbox";
								element2.setAttribute('onClick', "fillVillage(" + count + ",'" + villageText + "')");
								cell3.appendChild(element2);
								cell3.setAttribute('align', 'center');

								var cell4 = row.insertCell(3);
								var element3 = document.createElement("input");
								element3.type = "text";
								element3.name = "reNamedVillName" + count;
								element3.id = "reNamedVillId" + count;
								element3.setAttribute('class', 'frmfield');
								element3.setAttribute('disabled', 'true');
								cell4.appendChild(element3);

								var cell5 = row.insertCell(4);
								var element4 = document.createElement("input");
								element4.type = "text";
								element4.name = "reNamedVillNameNew" + count;
								element4.id = "reNamedVillIdNew" + count;
								element4.setAttribute('class', 'frmfield');
								element4.setAttribute('disabled', 'true');
								cell5.appendChild(element4);

								var cell6 = row.insertCell(5);
								var element5 = document.createElement("input");
								element5.type = "hidden";
								element5.name = "villId" + count;
								element5.id = "villId" + count;
								element5.value = village;
								element5.setAttribute('class', 'frmfield');
								cell5.appendChild(element5);
								count++;
								document.getElementById("count").value = count;
							}

						}

					}

				}
			} else {
				document.getElementById("rename").checked = false;
				alert("Please choose contributing Villages");
			}

		}

		else {
			var table = document.getElementById("renameList");
			table.style.display = "none";
		}
	}

	function addItemVillageForDraft(list1, list2, val, doAddVal) {
		if (document.getElementById(list2).selectedIndex >= 0) {
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
		if (document.getElementById("rename").checked)
			renameListMethodAfterLoad(1);
	}
	function removevillageandsurveynoForDraft(list1, list2, doRemoveVal) {
		var selObj = document.getElementById('villageListNew');
		for ( var i = 0; i < selObj.options.length; i++) {
			if (selObj.options[i].selected)
				if ((selObj.options[i].value).substr(selObj.options[i].value.length - 4) == "PART") {
					$("#surveyListMain").empty();
					$("#surveyListNew").empty();
					document.getElementById("survernoset").value = "0";
					break;
				}

		}

		if (document.getElementById(list1).selectedIndex >= 0) {
			for ( var i = 0; i < document.getElementById(list1).options.length; i++)
				if (document.getElementById(list1).options[i].selected == true)
					if (doRemoveVal)
						$('#' + list2).append(
								"<option value="
										+ document.getElementById(list1)[i].value.substr(0, document.getElementById(list1)[i].value.length - 4)
										+ " >" + document.getElementById(list1)[i].text.substr(0, document.getElementById(list1)[i].text.length - 6)
										+ "</option>");
					else
						$('#' + list2).append(
								"<option value=" + document.getElementById(list1)[i].text + " >" + document.getElementById(list1)[i].text
										+ "</option>");
			removeSelectedValue(list1);

		}
		if (document.getElementById("rename").checked)
			renameListMethodAfterLoad(1);
	}

	function removeAllSelectedVillagesForDraft(list1, list2, doRemoveVal) {
		$("#surveyListMain").empty();
		$("#surveyListNew").empty();
		document.getElementById("survernoset").value = "0";
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
		if (document.getElementById("rename").checked)
			renameListMethodAfterLoad(1);
	}
	function renameListMethodAfterLoad(val) {
		var renameList = renameListOnLoadVal;
		if (val == 1) {
			try {
				oList = document.addVillageNew.elements["contributedVillages"];
			} catch (err) {
				alert(err);
			}
			if (oList.options.length > 0) {
				var table = document.getElementById("renameList");
				table.style.display = "inline";
				n = table.rows.length;
				document.getElementById("rename").checked = true;
				for ( var i = table.rows.length - 1; i > 0; i--) {
					table.deleteRow(i);
				}
				if (n >= 1) {
					var count = 0;
					for ( var i = 0; i < oList.options.length; i++) {

						var village = oList.options[i].value;
						var villageText = oList.options[i].text;
						if (village.indexOf("PART") != -1) {
							var renameFlag = false;
							if (typeof (renameList) != "undefined") {
								var renameSplit = renameList.split(",");
								var renameVilNameSplit = "";
								var reNameVilNameChanged = "";
								for ( var j = 0; j < renameSplit.length; j++) {
									renameVilNameSplit = renameSplit[j].split(":");
									for ( var k = 0; k < renameVilNameSplit.length; k++) {
										if ((village.substr(0, village.length - 4)) == renameVilNameSplit[0]) {
											reNameVilNameChanged = renameVilNameSplit[1];
											renameFlag = true;
											break;
										}
									}
								}
							}
							if (renameFlag == true) {
								var rowCount = table.rows.length;
								var row = table.insertRow(rowCount);

								var cell1 = row.insertCell(0);
								cell1.innerHTML = rowCount;

								var cell2 = row.insertCell(1);
								var element1 = document.createElement("input");
								element1.type = "text";
								element1.name = "villName" + count;
								element1.id = "villName" + count;
								element1.value = villageText;
								element1.size = 30;
								element1.setAttribute('readonly', 'readonly');
								element1.setAttribute('class', 'frmfield');
								cell2.appendChild(element1);

								var cell3 = row.insertCell(2);
								var element2 = document.createElement("input");
								element2.name = "reNameFlag" + count;
								element2.id = "reNameFlagId" + count;
								element2.type = "checkbox";
								element2.checked = true;
								element2.setAttribute('onClick', "fillVillage(" + count + ",'" + villageText + "')"); //function(){fillVillage(rowCount,villageText);};
								cell3.appendChild(element2);
								cell3.setAttribute('align', 'center');

								var split = villageText.substring(0, villageText.length - 6);
								var VillageName = split.replace(/^\s+|\s+$/g, "");

								var cell4 = row.insertCell(3);
								var element3 = document.createElement("input");
								element3.type = "text";
								element3.name = "reNamedVillName" + count;
								element3.id = "reNamedVillId" + count;
								element3.value = VillageName;
								element3.setAttribute('class', 'frmfield');
								element3.setAttribute('disabled', 'true');
								cell4.appendChild(element3);

								var cell5 = row.insertCell(4);
								var element4 = document.createElement("input");
								element4.type = "text";
								element4.name = "reNamedVillNameNew" + count;
								element4.id = "reNamedVillIdNew" + count;
								element4.value = reNameVilNameChanged;
								element4.setAttribute('class', 'frmfield');
								//element4.setAttribute('disabled', 'false');
								cell5.appendChild(element4);

								var cell6 = row.insertCell(5);
								var element5 = document.createElement("input");
								element5.type = "hidden";
								element5.name = "villId" + count;
								element5.id = "villId" + count;
								element5.value = village;
								element5.setAttribute('class', 'frmfield');
								cell5.appendChild(element5);
								count++;
								document.getElementById("count").value = count;

							} else {

								var rowCount = table.rows.length;
								var row = table.insertRow(rowCount);

								var cell1 = row.insertCell(0);
								cell1.innerHTML = rowCount;

								var cell2 = row.insertCell(1);
								var element1 = document.createElement("input");
								element1.type = "text";
								element1.name = "villName" + count;
								element1.id = "villName" + count;
								element1.value = villageText;
								element1.size = 30;
								element1.setAttribute('readonly', 'readonly');
								element1.setAttribute('class', 'frmfield');
								cell2.appendChild(element1);

								var cell3 = row.insertCell(2);
								var element2 = document.createElement("input");
								element2.name = "reNameFlag" + count;
								element2.id = "reNameFlagId" + count;
								element2.type = "checkbox";
								element2.setAttribute('onClick', "fillVillage(" + count + ",'" + villageText + "')");
								cell3.appendChild(element2);
								cell3.setAttribute('align', 'center');

								var cell4 = row.insertCell(3);
								var element3 = document.createElement("input");
								element3.type = "text";
								element3.name = "reNamedVillName" + count;
								element3.id = "reNamedVillId" + count;
								element3.setAttribute('class', 'frmfield');
								element3.setAttribute('disabled', 'true');
								cell4.appendChild(element3);

								var cell5 = row.insertCell(4);
								var element4 = document.createElement("input");
								element4.type = "text";
								element4.name = "reNamedVillNameNew" + count;
								element4.id = "reNamedVillIdNew" + count;
								element4.setAttribute('class', 'frmfield');
								element4.setAttribute('disabled', 'true');
								cell5.appendChild(element4);

								var cell6 = row.insertCell(5);
								var element5 = document.createElement("input");
								element5.type = "hidden";
								element5.name = "villId" + count;
								element5.id = "villId" + count;
								element5.value = village;
								element5.setAttribute('class', 'frmfield');
								cell5.appendChild(element5);
								count++;
								document.getElementById("count").value = count;
							}

						}

					}

				}
			} else {
				document.getElementById("rename").checked = false;
				alert("Please choose contributing Villages");
				var table = document.getElementById("renameList");
				table.style.display = "none";
			}

		}

		else {
			var table = document.getElementById("renameList");
			table.style.display = "none";
		}
	}

	function checkAll(field) {
		var table = document.getElementById("renameList");
		n = table.rows.length;
		for ( var i = 0; i < n - 1; i++) {
			document.getElementById("reNameFlagId" + i).checked = true;
			var vilNameArray = document.addVillageNew.villName;
			var villName = document.getElementById("villName" + i).value;
			//var split = villName.substring(0,villName.length-6);
			var VillageName = villName.replace(/^\s+|\s+$/g, "");
			document.getElementById("reNamedVillId" + i).value = VillageName;
			document.getElementById("reNamedVillIdNew" + i).disabled = false;
		}
	}
	function uncheckAll(field) {
		var table = document.getElementById("renameList");
		n = table.rows.length;
		for ( var i = 0; i < n - 1; i++) {
			document.getElementById("reNameFlagId" + i).checked = false;
			document.getElementById("reNamedVillId" + i).value = "";
			document.getElementById("reNamedVillIdNew" + i).disabled = true;
			document.getElementById("reNamedVillIdNew" + i).value = "";
		}

	}
	/*DWR code for the exist village */

	function villageVal(vilname) {
		var disid = document.getElementById("ddDistrict").value;
		var subdisid = document.getElementById("ddSubdistrict").value;
		lgdDwrVillageService.VilageExist(subdisid, vilname, {
			callback : handleDistrictSuccess,
			errorHandler : handleDistrictError
		});

	}

	function handleDistrictSuccess(data) {
		document.getElementById("errProSelectDist").innerHTML = "";
		/*Modified by Pooja on 21-07-2015 for display difft. error msg*/
		if (data=='P') {
			document.getElementById("errProSelectDist").innerHTML = "<font color='red'><br>Same Village Name already exist.</font>";
			document.getElementById("OfficialAddress").value = "";
			document.getElementById("OfficialAddress").focus();
		}
		else if(data == 'S'){
			document.getElementById("errProSelectDist").innerHTML = "<font color='red'><br>Same Village Name already exist in Draft mode. Plz go to View n Manage Draft entities and Publish the village or delete the Draft version.</font>";
			document.getElementById("OfficialAddress").value = "";
			document.getElementById("OfficialAddress").focus();
		}

	}

	function handleDistrictError() {
		document.getElementById("OfficialAddress").value = "";
		document.getElementById("OfficialAddress").focus();
	}
	/*DWR code ends*/
	function toggledisplayed(obj, val) {

		if (document.getElementById(obj).checked) {
			if (val == 'cvillage') {
				document.getElementById('cvillage').style.visibility = 'visible';
				document.getElementById('csurvey').style.visibility = 'visible';
				document.getElementById('cvillage').style.display = 'block';
				document.getElementById('csurvey').style.display = 'block';
				//document.getElementById('culb').style.display = 'none';
				document.getElementById('chknothing').checked = false;
				//document.getElementById('chkculb').checked = false;
				
				/* 	$("#villageListNew").empty();
					$("#surveyListMain").empty();
					$("#surveyListNew").empty(); */

			} else if (val == 'culb') {
				/* document.getElementById('culb').style.visibility = 'visible';
				document.getElementById('culb').style.display = 'block'; */
				document.getElementById('cvillage').style.display = 'none';
				document.getElementById('csurvey').style.display = 'none';
				document.getElementById('chkcvillage').checked = false;
				document.getElementById('chknothing').checked = false;
				/* $("#ulbListNew").empty(); */
			} else {
				document.getElementById('cvillage').style.display = 'none';
				document.getElementById('csurvey').style.display = 'none';
				//document.getElementById('culb').style.display = 'none';
				document.getElementById('chkcvillage').checked = false;
				//document.getElementById('chkculb').checked = false;

			}
		}
	}
	function selectValidateAndSubmit(btnMode) {
	var govtOrderConfig="${govtOrderConfig == 'govtOrderUpload'}";
		selectall();
		if(gisNodesMismatch()){
			if(checkLatitudeLongitudeRange()){
				if (validateform()) {
					if (surverNoSelected()) {
						if(validateIsPesaFlag()){
							if (validateGovtOrderDetailsForVillage(govtOrderConfig)) {
								$("#OrderDate").removeAttr("disabled");
								$("#EffectiveDate").removeAttr("disabled");
								if(govtOrderConfig!="false"){
									$("#GazPubDate").removeAttr("disabled");
								}
								
								document.getElementById('form1').action = 'enterVillageOrderDetails.htm?<csrf:token uri="enterVillageOrderDetails.htm"/>';
								document.getElementById('buttonClicked').value = btnMode;
								 document.getElementById("BtnSA").disabled = true;
								document.getElementById("BtnSAP").disabled = true;
								document.getElementById('form1').submit(); 
							}
						}
						
					}

				}
			}
		}else{
			$("#cAlert").html("<spring:message code='Error.lengthmismatch' htmlEscape='true'/>");
			$("#cAlert").dialog({
				title : '<spring:message htmlEscape="true"  code="Label.CREATENEWVILLAGE"></spring:message>',
				resizable : false,
				height : 200,
				modal : true,
				buttons : {
					Ok : function() {
						$(this).dialog("close");
						return false;
					}
				}
			});
	}		  
		

	}
	
	checkIsPesaStatus=function(){
		
		var contributeVillageLits = $('#villageListNew option');
		if($(contributeVillageLits).length == 0){
			$("#divispesaFlag").empty();
			 $("#isPesaStatus").val("");
		}else{
			var _village_code_list="";
			$(contributeVillageLits).each(function(index){
				// alert( $(this).val());
				 _village_str= $(this).val();
				 _village_code=_village_str.substring(0,( _village_str.length-4));
				 _village_code_list=_village_code_list+_village_code+"@";
				
			 });
			_village_code_list=_village_code_list.substring(0,(_village_code_list.length-1));
			lgdDwrVillageService.getISPesaFlag(_village_code_list,{
				callback : function( result ) {
					if(result!=null){
						createDynamicIsPesaDiv(result);
					}
				},
				errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
				async : true
			});
			
		}
		//var isPesaStructure=$("#isPesaStructure");
		/* if(isPesaStructure!=null){
			$(isPesaStructure).empty();
		} */
		/* if($(contributeVillageLits).length == 0){
			$("#isPesaFlagNo").prop('checked', false);
			$("#isPesaFlagYes").prop('checked', false);
			alert("Please Select contributing village first");
		}else{ */
			/*var _village_str=null;
			var _village_code=null;*/
			
			
			//alert(_village_code_list);
		//}
		};
		
		var enableIsPesaButton=false;
		
		createDynamicIsPesaDiv=function(ispesaFlag){
			
			enableIsPesaButton=false;
			if(ispesaFlag.trim()=="M"){
				enableIsPesaButton=true;
			}
			
			$("#divispesaFlag").empty();
			var divTemplate = $("#divispesaFlag");
			 
			

			
			 var divfrmtxt = $("<div/>");
			 //divfrmtxt.attr("id", "isPesaStructure" );
			 divfrmtxt.addClass('frmtxt');
			 
			 var divTitle=$("<div/>");
			 divTitle.addClass('frmhdtitle');
			 divTitle.html("<spring:message htmlEscape='true' code='Label.ispesa.flag'/> ");
			 
			/* var labelTitle = $("<label/>");
			 labelTitle
			 
			 divTitle.append(labelTitle);
			 */
			 var divContainer=$("<div/>");
			 
			 var UL=$("<UL/>")
			 UL.addClass('listing');
			 
			 var LIONE=$("<LI/>");
			 
			 
			 var labelYes = $("<label/>");
			 labelYes.html("<spring:message htmlEscape='true' code='Label.YES'/> ");
			 
			
			 var templateYesInput=$("<input/>");
			 templateYesInput.attr("id", "isPesaFlagYes" );
			 templateYesInput.attr("name", "isPesaFlag" );
			 templateYesInput.attr("type", "radio" );
			 templateYesInput.attr("value", "F" );
			 if(enableIsPesaButton){
				 $(templateYesInput).click(function() {
					 $("#isPesaStatus").val("F");
					});
			 }else
				 {
				 if(ispesaFlag.trim()=="F"){
					 $(templateYesInput).prop('checked', true);
					 $("#isPesaStatus").val("F");
				 }
				 $(templateYesInput).prop('disabled', true);
				 }
			 
			 
			 LIONE.append(labelYes);
			 LIONE.append(templateYesInput);
			 
			 var LITWO=$("<LI/>");
			 
			 
			 var labelNo = $("<label/>");
			 labelNo.html("<spring:message htmlEscape='true' code='Label.NO'/> ");
			 
			
			 var templateNoInput=$("<input/>");
			 templateNoInput.attr("id", "isPesaFlagNo" );
			 templateNoInput.attr("name", "isPesaFlag" );
			 templateNoInput.attr("type", "radio" );
			 templateNoInput.attr("value", "N" );
			 if(enableIsPesaButton){
				 $(templateNoInput).click(function() {
					 $("#isPesaStatus").val("N");
					});
			 }else
				 {
				 if(ispesaFlag.trim()=="N"){
					 $(templateNoInput).prop('checked', true);
					 $("#isPesaStatus").val("N");
				 }
				 $(templateNoInput).prop('disabled', true);
				 }
			 
			 var templateUnselectInput=$("<input/>");
			 templateUnselectInput.attr("id", "unselect" );
			 templateUnselectInput.attr("name", "isPesaFlag" );
			 templateUnselectInput.attr("type", "radio" );
			 templateUnselectInput.attr("value", "unselect" );
			 templateUnselectInput.attr("style", "Display:none" );
			 if(ispesaFlag.trim()=="M"){
			 $(templateUnselectInput).prop('checked', true);
			 $("#isPesaStatus").val("");
			 }
			 
			 LITWO.append(labelNo);
			 LITWO.append(templateNoInput);
			 LITWO.append(templateUnselectInput);
			 
			 var LITHREE=$("<LI/>");
			 
			 templateSpanError=$("<span/>")
			 templateSpanError.attr("id", "errorIsPesaFlag");
			 templateSpanError.addClass('errormessage');
			 
			 LITHREE.append(templateSpanError);
			 
			 UL.append(LIONE);
			 UL.append(LITWO);
			 UL.append(LITHREE);
			 
			 divContainer.append(UL);
			 
			 divfrmtxt.append(divTitle);
			 divfrmtxt.append(divContainer);
			 
			 divTemplate.append(divfrmtxt);
			 
		};

		
		validateIsPesaFlag=function(){
			$( '#errorIsPesaFlag').text("");
			if ($("#chkcvillage").is(':checked')) {
				var contributeVillageLits = $('#villageListNew option');
					if($(contributeVillageLits).length == 0){
						alert("Please choose contributing Villages ");
						return false;
					}else if(enableIsPesaButton==true){
							   var isPesaFlag=$("#isPesaStatus").val();
		         	  				if(isPesaFlag.trim()==""){
		         		  					$( '#errorIsPesaFlag').text("<spring:message code='label.choose.isPesa.flag' htmlEscape='true'/>");
		         		 					return false;
		         	   				  }
			
					}
			
		   }
			return true;
			
		};
		
		

	
</script>
</head>
<body onload="hideGISComponentOnLoad();" oncontextmenu="return false" > <!--remove this for user requirment paste in local name 0007785 by Maneesh 16may2016  onkeypress="disableCtrlKeyCombination(event);"	onkeydown="disableCtrlKeyCombination(event);" -->


	<div class="overlay" id="overlay1" style="display: none;"></div>
	<div class="box" id="box1">
		<a class="boxclose" id="boxclose1"></a>
		<div>
			<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<div class="success"></div>
							</center></td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div></td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center>
									<c:out value="${param.family_msg}"></c:out>
								</center>
							</div></td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><div class="failur"></div></td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center></td>
					</tr>
					<tr>
						<td><div id="failurMsg" class="errorfont">
								<c:out value="${family_error}"></c:out>
							</div></td>
					</tr>
				</table>

			</c:if>

		</div>
	</div>

	<div class="box" id="box">
		<a class="boxclose" id="boxclose"></a>
		<div id="validate_error">
			<table>
				<tr>
					<td rowspan="2"><div class="errorImg"></div></td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div></td>
				</tr>
				<tr>
					<td><div class="errorfont">
							<spring:message htmlEscape="true"
								code="Error.entermandatoryfield"></spring:message>
						</div></td>
				</tr>
			</table>

		</div>
	</div>

	<div id="helpDiv" class="helpMessage">
		<div class="helpheader">
			<h4>Help Message</h4>
		</div>
		<div id="helpMsgText" class="helptext"></div>
	</div>


	<div id="frmcontent">
		<form:form action="addVillage.htm" method="POST"
			commandName="addVillageNew" id="form1" enctype="multipart/form-data"
			name="addVillageNew">
			<input type="hidden" name="flag1" id="flag1" value="${flag1}" />
			<input type="hidden" name="flag2" id="flag2" value="${flag2}" />
			<input type="hidden" name="subDistrictCode" id="subDistrictCode" value="${subDistrictCode}" />
				<input type="hidden" name="districtCode" id="districtCode" value="${districtCode}" />
			<input type="hidden" name="survernoset" id="survernoset" value="0" />
			<input type="hidden" name="contributedSurvey" id="contributedSurvey"
				value="" />
			<input type="hidden" name="<csrf:token-name/>"
				value="<csrf:token-value uri="addVillage.htm"/>" />
			<input type="hidden" name="count" value="" id="count"></input>
			<input type="hidden" name="selectedUlbCode" id="selectedUlbCode" value="0"/>
			<form:hidden path="isPesaStatus" value="N"/>
			<div id="dialogBX" style="display: none;">
						<iframe id="myIframe" width="910" height="650"></iframe>
			</div>
		<div class="frmhd">
			<h3 class="subtitle">
				<label><spring:message code="Label.CREATENEWVILLAGE" htmlEscape="true"></spring:message></label>
			</h3>
			<ul id="showhelp" class="listing">				
				
			<%--//these links are not working 	<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message code="Label.HELP" htmlEscape="true"></spring:message> </a></li> --%>
			</ul>
		</div>								
			
			
			<div class="clear"></div>
			<div class="frmpnlbrdr">

				<div class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message>
						</div>											
						
					<div class="margin_left_0per">
						<ul class="listing" style="width: 100%">
							<form:hidden htmlEscape="true" path="govtOrderConfig"
								value="${govtOrderConfig}" />
							<form:hidden htmlEscape="true" path="operation" value="C" />
													
							
									
								<li style="width:48%;">
								
										<label><spring:message
												code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message>
										</label><span id="required" class="errormsg">*</span><br /> 
										
										
										<select
											htmlEscape="true" name="districtNameEnglish"
											class="combofield" id="ddDistrict" style="width: 150px"
											onchange="getSubDistrictandULBList(this.value);"
											onclick="clearalllist();"
											onfocus="validateOnFocus('ddDistrict');helpMessage(this,'ddDistrict_msg');"
											onblur="vlidateOnblur('ddDistrict','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddDistrict')">
											<option value="0">
												<spring:message code="Label.SELECTDISTRICT"
													htmlEscape="true"></spring:message>
											</option>
												<c:choose>
											 		<c:when test="${flag1 eq 0}">
											 			<c:forEach items="${districtList}" var="districtList">
															
															<c:if test="${fn:containsIgnoreCase(districtList.operation_state, 'A'.charAt(0))}">
																<option value="${districtList.districtCode}" >${districtList.districtNameEnglish}
																</option>
															</c:if>
															<c:if test="${fn:containsIgnoreCase(districtList.operation_state, 'F'.charAt(0))}">
																<option value="${districtList.districtCode}" disabled="true">${districtList.districtNameEnglish}																				
																</option>
															</c:if>
														</c:forEach>	
											 		</c:when>
											 		<c:otherwise>
											 			<c:forEach items="${districtList}" var="districtList">
																<c:if test="${districtId eq districtList.districtCode}">
																<option value="${districtList.districtCode}" <c:if test="${fn:containsIgnoreCase(districtList.operation_state, 'F'.charAt(0))}">disabled="disabled"</c:if>>${districtList.districtNameEnglish}</option>
																</c:if>
														</c:forEach>
											 		</c:otherwise>
											 	</c:choose>
										</select> 
										<br /> 
										<span class="errormessage" id="errddDistrict"></span>
										<div id="ddDistrict_msg" style="display: none">
											<spring:message code="Error.SOURCEDISTRICT" htmlEscape="true" />
										</div>									
								</li>
								
								
								<li style="width:48%;">
											<label><spring:message
												code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message>
											</label><span id="required" class="errormsg">*</span><br /> <label>
											<form:select htmlEscape="true" path="subdistrictNameEnglish"
												class="combofield" id="ddSubdistrict" style="width: 150px"
												onchange="getVillageList(this.value);"
												onclick="clearalllist();"
												onfocus="validateOnFocus('ddSubdistrict');helpMessage(this,'ddSubdistrict_msg');"
												onblur="vlidateOnblur('ddSubdistrict','1','15','c');hideHelp();"
												onkeyup="hideMessageOnKeyPress('ddSubdistrict')">
												<c:if test="${not empty subDistrictList }">
													<form:option value="0">
													<spring:message code="Label.SELECTDISTRICT"
														htmlEscape="true"></spring:message>
													</form:option>
													<%-- <form:options items="${subDistrictList}"
														itemLabel="subdistrictNameEnglish"
														itemValue="subdistrictPK.subdistrictCode" /> --%>
												</c:if>
											</form:select>
										</label>
										<span class="errormessage" id="errddSubdistrict"></span>
										<div class="errormsg">
											<form:errors htmlEscape="true" path="subdistrictNameEnglish"></form:errors>
										</div>
										<div id="ddSubdistrict_msg" style="display: none">
											<spring:message code="error.PSSDT" htmlEscape="true" />
										</div>
								</li>						
							
									
							<%-- <c:if test="${flag1 eq 1}">	
							
							
							
								<li style="width:48%;">	
										<label><spring:message
												code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message>
										</label><span id="required" class="errormsg">*</span><br /> <label>
											<form:select htmlEscape="true" path="subdistrictNameEnglish"
												class="combofield" id="ddSubdistrict" style="width: 150px"
												onchange="getVillageList(this.value);"
												onclick="clearalllist();"
												onfocus="validateOnFocus('ddSubdistrict');helpMessage(this,'ddSubdistrict_msg');"
												onblur="vlidateOnblur('ddSubdistrict','1','15','c');hideHelp();"
												onkeyup="hideMessageOnKeyPress('ddSubdistrict')">
											</form:select>
										</label>
										<div class="errormsg">
											<form:errors htmlEscape="true" path="subdistrictNameEnglish"></form:errors>
										</div>
										<div id="ddSubdistrict_msg" style="display: none">
											<spring:message code="error.PSSDT" htmlEscape="true" />
										</div>
								</li>						
							</c:if> --%>																						
						  </ul>								
					   </div>	
				   </div>
			    </div>
			    
				<div class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message code="Label.GENERALDETAILOFNEWVILLAGE"
								htmlEscape="true"></spring:message>
						</div>						
						
					<div >
						<ul class="noBullets">
							<li>
								<ul class="listing" style="width: 100%">
								<li style="width: 48%">
									<label><spring:message
											code="Label.NAMEOFNEWVILLAGEENGLISH" htmlEscape="true"></spring:message>
									</label><span id="required" class="errormsg">*</span><br /> <form:input
										htmlEscape="true" path="newVillageNameEnglish"
										style="width: 150px" id="OfficialAddress" class="frmfield"
										onfocus="clear_message();show_msg('OfficialAddress');validateOnFocus('OfficialAddress');helpMessage(this,'OfficialAddress_msg');"
										onblur="officialAddressVil();villageVal(this.value);vlidateOnblur('OfficialAddress','1','15','c');hideHelp();"
										onkeypress="return validateAlphanumericUpdateKeysWard(event);"
										maxlength="150"
										onkeyup="hideMessageOnKeyPress('OfficialAddress')" />


									<span class="errormessage" id="errOfficialAddress"></span>
									<div id="errProSelectDist" style="color: red;"></div> <span
									class="errormsg" id="OfficialAddress_error"></span> <label></label>
									<div class="errormsg">
										<form:errors htmlEscape="true" path="newVillageNameEnglish"></form:errors>
									</div>
									<div id="OfficialAddress_msg" style="display: none">
										Please Enter Village Name
										<%-- <spring:message code="error.PSV" htmlEscape="true"/> --%>
									</div>
								</li>
								<li style="width: 48%">
									<label><spring:message
											code="Label.NAMEOFNEWVILLAGELOCAL" htmlEscape="true"></spring:message>
									</label><br /> <label> <form:input htmlEscape="true" id="villageNameLocal"
											path="newVillageNameLocal" style="width: 150px"
											class="frmfield" maxlength="50" onfocus="show_msg('villageNameLocal');helpMessage(this,'villageNameLocal_msg');"
											onblur="hideHelp();validateSpecialCharactersforVillageValues(this.value,1);" />
									</label> <br />
									<div class="errormsg">
										<form:errors htmlEscape="true" path="newVillageNameLocal"></form:errors>
									</div>
									<div id="villageNameLocal_msg" style="display: none">
										Please Enter Village Name Local
										<%-- <spring:message code="error.PSV" htmlEscape="true"/> --%>
									</div>
								</li>
							   </ul>
							</li>
							<li>
								<ul class="listing" style="width: 100%" >
								<li style="width: 48%" >
									<label><spring:message
											code="Label.ALIASENGLISH" htmlEscape="true"></spring:message>
									</label><br /> <label> <form:input htmlEscape="true"
											path="newVillageAliasEnglish" style="width: 150px"
											class="frmfield" maxlength="50" />
									</label>
									<div class="errormsg">
										<form:errors htmlEscape="true" path="newVillageAliasEnglish"></form:errors>
									</div>
								</li>

								<li style="width: 48%" >
									<label><spring:message
											code="Label.ALIASLOCAL" htmlEscape="true"></spring:message> </label><br />
									<label> <form:input path="newVillageAliasLocal"
											style="width: 150px" class="frmfield" maxlength="50"
											onblur="validateSpecialCharactersforVillageValues(this.value,2);" />
									</label>
									<div class="errormsg">
										<form:errors htmlEscape="true" path="newVillageAliasLocal"></form:errors>
									</div>
								</li>
								</ul>
							</li>

							<li>
								<ul class="listing" style="width: 100%">
								<li style="width: 48%">
									<label><spring:message
											code="Label.VILLAGESTATUS" htmlEscape="true"></spring:message>
									</label><br /> <label> <form:select path="villageType"
											cssClass="combofield" cssStyle="width: 150px">
											<option value="">
												<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
											</option>
											<option value="R">Forest Village</option>
											<option value="U">Un-inhabitant</option>
											<option value="I">Inhabitant</option>
										</form:select> <%-- <form:option>Select</form:option> --%>
									</label> <br />
									<div class="errormsg"></div>
								</li>

								<%-- <li style="width: 48%">
									<label><spring:message
											code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message>
									</label><br /> <label> <form:input htmlEscape="true"
											path="census2011Code" style="width: 150px" class="frmfield"
											onblur="numericvaluesTest(this.value,1)" maxlength="7" />
									</label>
									<div class="errormsg">
										<form:errors htmlEscape="true" path="census2011Code"></form:errors>
									</div>
								</li>
								</ul>
							</li> --%>
							<li style="width: 48%">
									<label><spring:message
											code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message>
									</label><br /> <label> <form:input path="sscode"
											style="width: 150px" class="frmfield" id="sscode"
											name="sscode" maxlength="5"
											onblur="chekcalphanumeric(this.value,3);" />
									</label>
									<div class="errormsg">
										<form:errors htmlEscape="true" path="stateSpecificCode"></form:errors>
									</div>


							</li>
							<li style="width: 48%">
									<label><spring:message
											code="Label.SURVEYNUMBER" htmlEscape="true"></spring:message>
									</label> <br /> <input type="hidden" name="psize" id="psize" />
									<table id="ptablePerson" align="center">
										<tr>
										</tr>
										<tr>
											<td>1</td>
											<td><form:input htmlEscape="true" type="text"
													class="frmfield" style="width: 157px" path="surveyNumber"
													id="surveyNumber" name="surveyNumber"
													onblur="chekcalphanumeric(this.value,4);" maxlength="10" />
												<input type="button" name="Submit32"
												value="Add Survey Number" onclick="incrementCount()" /></td>
										</tr>

									</table>
									<div id=errBlockCode style="color: red;"></div>
									<table align="left" id="surveytable">
										<tr>
											<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button"
												value="Remove" style="width: 150px" onclick="removeRow();"
												align="left" /></td>
										</tr>
									</table> 
									<div class="errormsg"></div>
									<div id="addsurveys"></div>
							</li>
						</ul>
					</div>
				</div>				
			</div>
			
			
				<div class="frmpnlbg">
								<div class="frmtxt">
									<div class="frmhdtitle">
										<spring:message code="Label.GISNODES" htmlEscape="true"></spring:message>
									</div>
								
									<div align="center">
									<%@ include file="../common/modifyGis_nodesVillage.jspf"%>
									</div>									
									
								</div>
							</div> 
				
				<div class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message code="Label.HOWVILLAGEFORMED" htmlEscape="true">
								<span class="errormsg">*</span>
							</spring:message>
						</div>
						
								<div>
									<ul class="blocklist">
										<li>
											<div>
											<ul class="listing">
											<li>
												<label><spring:message code="Label.EXISTINGVILLAGE" htmlEscape="true"></spring:message></label>
											
												<label> <form:input htmlEscape="true" name="checkbox" type="radio" value="true" id='chkcvillage' path="createFromExistingVillages" onclick='toggledisplayed("chkcvillage","cvillage")' /> </label>
											</li>
											</ul>
											</div>
										</li>
										<li>
											<ul class="listing">
												<li>
													<label><spring:message code="Label.NEWLAND" htmlEscape="true"></spring:message> </label>
												
													<label> <form:input htmlEscape="true" name="checkbox" type="radio" value="true" id='chknothing' path="createFromNewLand" onclick='toggledisplayed("chknothing","chknothing")' /></label>			
												</li>
											</ul>
										</li>
										 <li style="visibility: hidden; display: none;">
											<ul class="listing">
												<li>												
														<label><spring:message code="Label.ULB" htmlEscape="true"></spring:message> </label>
													
														<label> <form:input htmlEscape="true" name="checkbox" type="radio" value="true" id='chkculb' path="createFromCoverageOfUrbanLocalBody" onclick='toggledisplayed("chkculb","culb")' />
											</label>
												</li>
											</ul>
										</li> 
										<li>
											<div class="errormsg">
													<form:errors path="createFromExistingVillages"></form:errors>
											</div>
											
										</li>
									</ul>
								</div>

								<div class="errormsg"></div>
					</div>
				</div>


				<!--  11  -->


				<div id='cvillage' class="frmpnlbg"
					style="visibility: hidden; display: none;">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message code="Label.CONTRIBUTINGLANDREGION"
								htmlEscape="true"></spring:message>
						</div>
	
					
					<div class="margin_left_0per">
						<div class="ms_container">
							<div class="ms_selectable">
								<strong><spring:message code="Label.VILLAGES" htmlEscape="true"></spring:message> </strong>	<br />							
								<form:select name="select9" size="1" id="villageListMain" path="villageList" multiple="multiple" class="frmtxtarea"> </form:select>
							</div>
							<div class="ms_buttons">
								<label> <input type="button" class="bttn" id="btnaddVillageFull" name="Submit4" value="Select Full Villages&gt;&gt;" onclick="addItemVillage('villageListNew','villageListMain','FULL',true);checkIsPesaStatus();" /> </label>
								<label> <input type="button" class="bttn" id="btnremoveOneVillage" name="Submit4" value=" &lt; " onclick="removevillageandsurveynoForDraft('villageListNew','villageListMain',true);checkIsPesaStatus();" /> </label>
								<label> <input type="button" class="bttn" id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;" onclick="removeAllSelectedVillagesForDraft('villageListNew','villageListMain',true)checkIsPesaStatus();" /> </label>
								<label> <input type="button" class="bttn" id="btnaddVillagePart" name="Submit4" value="Select Part Villages&gt;&gt;" onclick="addItemVillageForDraft('villageListNew','villageListMain','PART',true);checkIsPesaStatus();" /> </label>
							</div>
							<div class="ms_selection">
								<label><spring:message code="Label.CONTRIBUTINGVILLAGELIST" htmlEscape="true"></spring:message> </label><br />
								<form:select name="select4" id="villageListNew" size="1" multiple="multiple" path="contributedVillages" class="frmtxtarea"> </form:select>
								<span class="errormessage" id="errvillageListNew"></span>
								<div class="errormsg">
									<form:errors path="contributedVillages"></form:errors>
								</div>
									<label> <input type="button" class="btn" id="tbnSurveyNumbers" name="Submit7" onclick="getSurveyNobyVillage();" value="<spring:message code="Label.GETPARTVILLAGESURVEYNUMBER" htmlEscape="true"></spring:message>" /> </label>
										<div class="errormsg"></div>
							</div>
						</div>
						<div class="clear"></div>
					</div>																
				<div>																																									
			</div>		
	
	
	
						<div id='csurvey' style="visibility: hidden;">							

							<div class="margin_left_0per">
								<div class="ms_container">
										<div class="ms_selectable">
											<label><spring:message code="Label.SELECTEDVILLAGESURVEYNUMBER" htmlEscape="true"></spring:message> </label><br />
											<form:select name="select5" id="surveyListMain" size="1" multiple="multiple" path="surveyList" class="frmtxtarea"> </form:select>
										</div>
										<div class="ms_buttons">						
											<label> <input type="button" name="Submit42" class="bttn" value="<spring:message code="Label.SELECTSURVEYNUMBER" htmlEscape="true"></spring:message>&gt;&gt;" onclick="fillselectedSurveryNo('surveyListNew','surveyListMain',document.getElementById('surveyListMain').value,false)" /> </label>
											<label> <input type="button" class="bttn" id="btnremoveOneSurvey" name="Submit4" value=" &lt; " onclick="removeItem('surveyListNew','surveyListMain',false)" /> </label> 
											<label> <input type="button" class="bttn" name="Submit42" id="btnremoveAllSurveys" value="&lt;&lt;" onclick="removeAll('surveyListNew','surveyListMain',false)" /> </label>
										</div>
										<div class="ms_selection">
												<label><spring:message code="Label.CONTRIBUTINGSURVEYNUMBER" htmlEscape="true"></spring:message> </label><br />
												<form:select name="select5" id="surveyListNew" size="1" multiple="multiple" path="contributedSurvey" class="frmtxtarea"> </form:select>
										</div>	
										<div class="clear"></div>																	
								</div>
							</div>										
									<br></br>	
								<div id="renameDiv" class="frmtxt">
											<div class="frmhdtitle">
												<spring:message code="Label.RENAME.CONTRIBUTING.VILLAGE"
													htmlEscape="true"></spring:message>
											</div>
											
									<div  >	
										<ul class="blocklist">
											
											<li>
												<div>
													<ul class="listing">
														<li>
															<label><spring:message code="Label.YES" htmlEscape="true"></spring:message> </label>
													
															<input type="radio" name="rename" id="rename" value="1" onclick="renameListMethod(this.value);"></input>												
														</li>											
													</ul>
												</div>
											</li>
											<li>
												<ul class="listing">
												<li>
													<label><spring:message code="Label.NO" htmlEscape="true"></spring:message> </label>												
											
													<input type="radio" name="rename" id="rename" value="0" onclick="renameListMethod(this.value);" checked="checked"></input>												
												</li>											
												</ul>
											</li>
										</ul>
									</div>	
									<table id="renameList" style="display: none">
												<tr>
													<td width="5%"><strong><spring:message
																code="Label.SNO" htmlEscape="true"></spring:message></strong></td>
													<td width="40%"><strong><spring:message
																code="Label.CONTRIBUTINGVILLAGE" htmlEscape="true"></spring:message></strong></td>
													<td width="40%" align="center"><input type="button"
														name="CheckAll" value="Check All"
														onclick="checkAll(document.addVillageNew.reNameFlag)">
															<input type="button" name="UnCheckAll"
															value="Uncheck All"
															onclick="uncheckAll(document.addVillageNew.reNameFlag)"></td>
													<td width="40%"><strong><spring:message
																code="Label.CURRENTVILLAGENAME" htmlEscape="true"></spring:message></strong></td>
													<td width="40%"><strong><spring:message
																code="Label.RENAMEVILLAGE" htmlEscape="true"></spring:message></strong></td>
												</tr>
											</table>																		
											<%-- <ul id="renameList" class="listing" style="display: none">												
													<li><strong><spring:message code="Label.SNO" htmlEscape="true"></spring:message></strong></li>
													<li><strong><spring:message code="Label.CONTRIBUTINGVILLAGE" htmlEscape="true"></spring:message></strong></li>
													<li>
														<input type="button" name="CheckAll" value="Check All" onClick="checkAll(document.addVillageNew.reNameFlag)">
														<input type="button" name="UnCheckAll" value="Uncheck All" onClick="uncheckAll(document.addVillageNew.reNameFlag)">
													</li>
													<li><strong><spring:message code="Label.CURRENTVILLAGENAME" htmlEscape="true"></spring:message></strong></li>
													<li><strong><spring:message code="Label.RENAMEVILLAGE" htmlEscape="true"></spring:message></strong></li>												
											</ul> --%>
								</div>
								<br/>
								<div>
								
								<input class="bttn" id="btnPreviewGIS" type="button"  onclick="showGISPreview();" value="<spring:message code='Button.PREVIEWGIS'   htmlEscape='true'/>"  />		
			
								</div>
								<br/>
								<div id="divispesaFlag" >
								
								<%--  <div class="frmtxt">
									<div class="frmhdtitle">
													<spring:message code="Label.ispesa.flag" htmlEscape="true"></spring:message>
									</div>
									<div>	
										<ul class="listing">
														<li>
															<label><spring:message code="Label.YES" htmlEscape="true"></spring:message> </label>
													
															<input type="radio" name="isPesaFlag"  id="isPesaFlagYes" value="F" onclick="setIsPesa(this.value);"></input>												
														</li>
														
														<li>
													<label><spring:message code="Label.NO" htmlEscape="true"></spring:message> </label>												
											
													<input type="radio" name="isPesaFlag" id="isPesaFlagNo" value="N" onclick="setIsPesa(this.value);" ></input>		
													<input type="radio"  id="UNSELECT" 	value="UNSELECT"  checked="checked" style="Display:none" name="isPesaFlag" />										
												</li>											
											</ul>
											
									</div>	
								</div>  --%>
								</div>
								<div class="errormsg"></div>								
						</div>

					</div>
				</div>

			 <div id='culb' style="margin: 20px 20px 0px 20px; background: #F7F7F7; padding: 10px; visibility: hidden; display: none">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message code="Label.SELECTEDDISTRICTULB" htmlEscape="true"></spring:message>
						</div>
						
						
						<div>								
								<div class="ms_container">
										<div class="ms_selectable">
											<label><spring:message code="Label.ULBCOVEREDLANDREGION" htmlEscape="true"></spring:message> </label><br />
											<form:select name="select6" path="coveredLandRegionByULB" id="ulbListMain" size="1" class="frmtxtarea" multiple="multiple"> </form:select>
										</div>
										<div class="ms_buttons">
													<label> <input type="button" class="bttn" name="Submit4222" onclick="addItem('ulbListNew','ulbListMain','FULL',true)" value="<spring:message code="Label.FULLULB" htmlEscape="true"></spring:message>&gt;&gt;" /> </label>
														
													<label> <input type="button" class="bttn" id="btnremoveOneULB" name="Submit4" value=" &lt; " onclick="removeItem('ulbListNew','ulbListMain',true)" /> </label>
													
													<label> <input type="button" class="bttn" name="Submit22223" value="&lt;&lt;" style="width: 58px" onclick="removeAll('ulbListNew','ulbListMain',true)" /> </label>
													
													<label> <input type="button" class="bttn" name="Submit22222" onclick="addItem('ulbListNew','ulbListMain','PART',true)" value="<spring:message code="Label.PARTULB" htmlEscape="true"></spring:message>&gt;&gt;" /> </label>														
										</div>
										<div class="ms_selection"></br>
											<form:select name="select6" path="selectedCoveredLandRegionByULB" id="ulbListNew" multiple="multiple" class="frmtxtarea"> </form:select>
												<div class="errormsg">
													<form:errors path="selectedCoveredLandRegionByULB"></form:errors>
												</div>
										</div>																		
								</div>																
								<div class="errormsg"></div>
								<div class="clear"></div>
						</div>						
						<div class="errormsg"></div>
						
					</div>
				</div> 

				<!-- Govt Order Config -->
				<%-- <jsp:include page="../common/commonGovtOrder.jsp" /></jsp:include> --%>

				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message>
							</div>
							<%@ include file="../govtbody/ExistingGovernmentOrderVillage.jsp"%>
							
						 <div >							
							<ul class="blocklist">
							
								<li>								
											<label><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message> </label><span class="errormsg">*</span><br /> 
											<form:input path="orderNo" htmlEscape="true" id="OrderNo" type="text" class="frmfield" style="width: 140px" maxLength="60" onfocus="helpMessage(this,'OrderNo_error');" onblur="vlidateOrderNo('OrderNo','1','60');hideHelp();" onkeypress="return validateaGovtOrderNO(event);" /> 											

										<div id="OrderNo_error" class="error">
											<spring:message code="error.required.ORDERNUM"
												htmlEscape="true"></spring:message>
										</div>
										<div id="OrderNo_msg" class="error">
											<spring:message code="error.required.ORDERINPUTTYPE"
												text="Please Enter AlphaNumerics Space . / - ( ) Only"
												htmlEscape="true" />
										</div>
										<div class="errormsg" id="OrderNo_error1">
											<form:errors path="orderNo" htmlEscape="true" />
										</div>
										<div class="errormsg" id="OrderNo_error2"
											style="display: none"></div>
								</li>
								<li>
										<label><spring:message code="Label.ORDERDATE"
												htmlEscape="true"></spring:message> </label><span class="errormsg">*</span><br />
										<form:input path="orderDate" readonly="true" id="OrderDate"
											type="text" class="frmfield" style="width: 140px"
											onchange="setEffectiveDate(this.value);"
											onkeypress="validateNumeric();"
											onfocus="validateOnFocus('OrderDate');helpMessage(this,'OrderDate_msg');"
											onblur="vlidateOnblur('OrderDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('OrderDate')" /> 

										<div id="OrderDate_error" class="error">
											<spring:message code="error.required.ORDERDATE"
												htmlEscape="true"></spring:message>
										</div>
										<div id="OrderDate_msg" style="display: none">
											<spring:message code="error.required.ORDERDATE"
												htmlEscape="true" />
										</div>
										<div class="errormsg" id="OrderDate_error1">
											<form:errors path="orderDate" htmlEscape="true" />
										</div>
										<div class="errormsg" id="OrderDate_error2"
											style="display: none"></div>
								</li>
								<li>
										<label><spring:message
												code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message>
									</label><span class="errormsg">*</span><br /> <form:input
											id="EffectiveDate" readonly="true" path="effectiveDate"
											type="text" class="frmfield" style="width: 140px"
											onkeypress="validateNumeric();"
											onchange="validateDatetoFuture('EffectiveDate','#EffectiveFutureDate_error');validateEffectiveDatecompOrderDate('OrderDate','EffectiveDate','#EffectiveDateData_error');"
											onfocus="validateOnFocus('EffectiveDate');helpMessage(this,'EffectiveDate_msg');"
											onblur="vlidateOnblur('EffectiveDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('EffectiveDate')" /> 
										<div id="EffectiveDate_error" class="error">
											<spring:message code="error.required.EFFECTIVEDATE"
												htmlEscape="true"></spring:message>
										</div>
										<div id="EffectiveDate_msg" style="display: none">
											<spring:message code="error.required.EFFECTIVEDATE"
												htmlEscape="true" />
										</div>
										<div class="errormsg" id="EffectiveDate_error1">
											<form:errors path="effectiveDate" htmlEscape="true" />
										</div>
										<div class="errormsg" id="EffectiveDate_error2"
											style="display: none"></div>
																						
											<div class="errormsg" id="EffectiveDateData_error">
												<spring:message htmlEscape="true" code="error.compare.EFFECTIVEDATE"></spring:message>
									       </div>
											
											<div class="errormsg" id="EffectiveDateBlank_error">
												<spring:message htmlEscape="true" code="ordereffectiveDate.required"></spring:message>
									       </div>
											<div class="errormsg" id="EffectiveFutureDate_error">
												<spring:message htmlEscape="true" code="error.valid.EFFECTIVEFUTUREDATE"></spring:message>
									       </div>
								</li>

								<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
								<li>
											<label><spring:message
													code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>
											</label><br /> <form:input id="GazPubDate" path="gazPubDate"
												type="text" class="frmfield" style="width: 140px"
												onkeypress="validateNumeric();"
												onfocus="validateOnFocus('GazPubDate');helpMessage(this,'GazPubDate_msg');"
												onblur="vlidateOnblur('GazPubDate','1','15','m');hideHelp();"
												onkeyup="hideMessageOnKeyPress('GazPubDate')" /> <span class="errormsg" id="GazPubDate_error"></span> <form:errors
												path="gazPubDate" cssClass="errormsg" htmlEscape="true"></form:errors> <span
											id="GazPubDate_msg" style="display: none">Please Enter
												Gazette Publication Date Like 12-04-2012</span>
											<div id="GazPubDate_error" class="error">
												<%-- <spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true"></spring:message> --%>
											</div>
											<div id="GazPubDate_msg" style="display: none">
												<spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true" />
											</div>
											<div class="errormsg" id="GazPubDate_error1">
												<form:errors path="gazPubDate" htmlEscape="true" />
											</div>
											<div class="errormsg" id="GazPubDate_error2"
												style="display: none"></div>									
								</li>
								</c:if>
								
								<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
									<li id="divGazettePublicationUpload">
											<%@ include file="../common/attachmentgovt.jspf"%>
									</li>
								</c:if>
								
								<c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
									<li>										
											<label><spring:message
													htmlEscape="true" code="Label.SELGOT"></spring:message> </label><span
											class="errormsg">*</span><br /> <form:select
												path="templateList" id="templateList" style="width:280px"
												class="combofield"
												onblur="vlidateOnblur('templateList','1','15','m');hideHelp();"
												onfocus="validateOnFocus('templateList');helpMessage(this,'templateList_msg');"
												onkeyup="hideMessageOnKeyPress('templateList')">
												<form:option value="0">
													<spring:message htmlEscape="true"
														code="Label.SELECT"></spring:message>
												</form:option>
												<form:options items="${templateList}"
													itemLabel="templateNameEnglish" itemValue="templateCode"></form:options>
											</form:select> <span class="errormsg" id="templateList_error"></span> <span><form:errors
													cssClass="errormsg" path="templateList" htmlEscape="true"></form:errors> </span> <span
											style="display: none;" id="templateList_msg">Please
												Select Government order Template</span>
											<div id="templateList_error" class="error">
												<%-- <spring:message code="error.blank.template"
													htmlEscape="true"></spring:message> --%>
											</div>
											<div id="templateList_msg" style="display: none">
												<spring:message code="error.blank.template"
													htmlEscape="true" />
											</div>
											<div class="errormsg" id="templateList_error1">
												<form:errors path="templateList" htmlEscape="true" />
											</div>
											<div class="errormsg" id="templateList_error2"
												style="display: none"></div>
									</li>
								</c:if>														
							</ul>												
						</div>							
					</div>
				</div>
			</div> 

				<!-- End of Govt Order Config -->
			
			<form:hidden htmlEscape="true" path="buttonClicked" value="" />
			<div class="btnpnl">
					<label> <input type="button" id="BtnSA"
					onclick="return selectValidateAndSubmit('S');" name="Submit"
					value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" />
				</label>
				 <label> <input type="button" id="BtnSAP"
						onclick="return selectValidateAndSubmit('P');" name="Submit"
						value="<spring:message code="Label.PUBLISH" htmlEscape="true" text="Publish" ></spring:message>" /> 
				</label>  
				<label> <input type="button" class="btn" name="Submit6"
						value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
						onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
				</label>
			</div>
	</div>
	</form:form>
</div>
</body>
</html>