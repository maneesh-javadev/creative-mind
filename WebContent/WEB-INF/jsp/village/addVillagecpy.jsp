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
<title><spring:message code="Label.CREATENEWVILLAGE"		htmlEscape="true"></spring:message></title>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
<script src="js/validation.js"></script>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/govtorder.js"></script>

<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrCoveredLandRegionByULBService.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrVillageServiceForDraftVillageList.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrSurveyService.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrVillageServiceForDraft.js'></script>

<c:if test="${isVillageSaved}">
<script>
$(function() {		
	$('#sucessAlert').modal('show');
	var districtCode='${addVillageNew.districtNameEnglish}';
	var subdistrictCode='${addVillageNew.subdistrictNameEnglish}';
	//alert("districtCode:"+districtCode+":"+"subdistrictCode:"+subdistrictCode);
	getSubDistrictandULBList(districtCode);
	setTimeout(function(){
		$("#ddDistrict option[value='" + districtCode + "']").attr("selected", "selected");
		$("#ddSubdistrict option[value='" + subdistrictCode + "']").attr("selected", "selected");
	},200);
});
</script>
</c:if>

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
		$('#form1 [type=text]').attr("autocomplete",'off');
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
		element.setAttribute("class", "form-control");
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
				table.style.display = "";
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
							element1.setAttribute('class', 'form-control');
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
							element3.setAttribute('class', 'form-control');
							element3.setAttribute('disabled', 'true');
							cell4.appendChild(element3);

							var cell5 = row.insertCell(4);
							var element4 = document.createElement("input");
							element4.type = "text";
							element4.name = "reNamedVillNameNew" + count;
							element4.id = "reNamedVillIdNew" + count;
							element4.setAttribute('class', 'form-control');
							element4.setAttribute('disabled', 'true');
							cell5.appendChild(element4);

							var cell6 = row.insertCell(5);
							var element5 = document.createElement("input");
							element5.type = "hidden";
							element5.name = "villId" + count;
							element5.id = "villId" + count;
							element5.value = village;
							element5.setAttribute('class', 'form-control');
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
								element1.setAttribute('class', 'form-control');
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
								element3.setAttribute('class', 'form-control');
								element3.setAttribute('disabled', 'true');
								cell4.appendChild(element3);

								var cell5 = row.insertCell(4);
								var element4 = document.createElement("input");
								element4.type = "text";
								element4.name = "reNamedVillNameNew" + count;
								element4.id = "reNamedVillIdNew" + count;
								element4.value = reNameVilNameChanged;
								element4.setAttribute('class', 'form-control');
								//element4.setAttribute('disabled', 'false');
								cell5.appendChild(element4);

								var cell6 = row.insertCell(5);
								var element5 = document.createElement("input");
								element5.type = "hidden";
								element5.name = "villId" + count;
								element5.id = "villId" + count;
								element5.value = village;
								element5.setAttribute('class', 'form-control');
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
								element1.setAttribute('class', 'form-control');
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
								element3.setAttribute('class', 'form-control');
								element3.setAttribute('disabled', 'true');
								cell4.appendChild(element3);

								var cell5 = row.insertCell(4);
								var element4 = document.createElement("input");
								element4.type = "text";
								element4.name = "reNamedVillNameNew" + count;
								element4.id = "reNamedVillIdNew" + count;
								element4.setAttribute('class', 'form-control');
								element4.setAttribute('disabled', 'true');
								cell5.appendChild(element4);

								var cell6 = row.insertCell(5);
								var element5 = document.createElement("input");
								element5.type = "hidden";
								element5.name = "villId" + count;
								element5.id = "villId" + count;
								element5.value = village;
								element5.setAttribute('class', 'form-control');
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
								element1.setAttribute('class', 'form-control');
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
								element3.setAttribute('class', 'form-control');
								element3.setAttribute('disabled', 'true');
								cell4.appendChild(element3);

								var cell5 = row.insertCell(4);
								var element4 = document.createElement("input");
								element4.type = "text";
								element4.name = "reNamedVillNameNew" + count;
								element4.id = "reNamedVillIdNew" + count;
								element4.value = reNameVilNameChanged;
								element4.setAttribute('class', 'form-control');
								//element4.setAttribute('disabled', 'false');
								cell5.appendChild(element4);

								var cell6 = row.insertCell(5);
								var element5 = document.createElement("input");
								element5.type = "hidden";
								element5.name = "villId" + count;
								element5.id = "villId" + count;
								element5.value = village;
								element5.setAttribute('class', 'form-control');
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
								element1.setAttribute('class', 'form-control');
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
								element3.setAttribute('class', 'form-control');
								element3.setAttribute('disabled', 'true');
								cell4.appendChild(element3);

								var cell5 = row.insertCell(4);
								var element4 = document.createElement("input");
								element4.type = "text";
								element4.name = "reNamedVillNameNew" + count;
								element4.id = "reNamedVillIdNew" + count;
								element4.setAttribute('class', 'form-control');
								element4.setAttribute('disabled', 'true');
								cell5.appendChild(element4);

								var cell6 = row.insertCell(5);
								var element5 = document.createElement("input");
								element5.type = "hidden";
								element5.name = "villId" + count;
								element5.id = "villId" + count;
								element5.value = village;
								element5.setAttribute('class', 'form-control');
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
				document.getElementById('culb').style.display = 'none';
				document.getElementById('chknothing').checked = false;
				document.getElementById('chkculb').checked = false;
				
				/* 	$("#villageListNew").empty();
					$("#surveyListMain").empty();
					$("#surveyListNew").empty(); */

			} else if (val == 'culb') {
				document.getElementById('culb').style.visibility = 'visible';
				document.getElementById('culb').style.display = 'block';
				document.getElementById('cvillage').style.display = 'none';
				document.getElementById('csurvey').style.display = 'none';
				document.getElementById('chkcvillage').checked = false;
				document.getElementById('chknothing').checked = false;
				/* $("#ulbListNew").empty(); */
			} else {
				document.getElementById('cvillage').style.display = 'none';
				document.getElementById('csurvey').style.display = 'none';
				document.getElementById('culb').style.display = 'none';
				document.getElementById('chkcvillage').checked = false;
				document.getElementById('chkculb').checked = false;

			}
		}
	}
	function selectValidateAndSubmit(btnMode) {
		
		//alert($("#chkcvillage").val());
		//alert(document.getElementById('chkcvillage').value());
		
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
								 //document.getElementById("BtnSA").disabled = true;
								document.getElementById("BtnSAP").disabled = true;
								document.getElementById('form1').submit(); 
							}
						}
						
					}

				}
			}
		}else{
			customAlert("<spring:message code='Error.lengthmismatch' htmlEscape='true'/>");
			
	}		  
		

	}
	
	checkIsPesaStatus=function(){
		
		/* var contributeVillageLits = $('#villageListNew option');
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
			
		} */
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
			 
			

			
			 /* var divfrmtxt = $("<div/>");
			 //divfrmtxt.attr("id", "isPesaStructure" );
			 divfrmtxt.addClass(''); */
			 
			 var divTitle=$("<div/>");
			 divTitle.addClass('box-header subheading');
			 divTitle.html("<spring:message htmlEscape='true' code='Label.ispesa.flag'/> ");
			 
			/* var labelTitle = $("<label/>");
			 labelTitle
			 
			 divTitle.append(labelTitle);
			 */
			/*  var divContainer=$("<div/>"); */
			 
			 var UL=$("<div/>")
			 UL.addClass('form-group');
			 
			 var LIONE=$("<label class=col-sm-2/>");
			 
			 
			 
			 var labelY = $("<div class=col-sm-4/>");
			 
			 var LIR = $("<div class=radio-inline/>");
			 
			 var labelYes = $("<label class=col-sm-3 control label/>");
			 labelYes.html("<spring:message htmlEscape='true' code='Label.YES'/> ");
			
			 
			
			 var templateYesInput=$("<input class=form-control/>");
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
			 
			/*  var tbl=$("<table></table>");
			 var tr=$("<tr></tr>");
			 var td1=$("<td></td>");
			 var td2=$("<td></td>"); */
			/*  td1.append(labelYes);
			 td1.append(templateYesInput); */
			 LIONE.append(labelY);
			 LIONE.append(LIR);
			 LIONE.append(labelYes);
			 LIONE.append(templateYesInput); 
			 
			 var LITWO=$("<label class=col-sm-2/>");
			 
			 
			 var labelN = $("<div class=col-sm-4/>");
			 
			 var NIR = $("<div class=radio-inline/>");
			 
			 var labelNo = $("<label class=col-sm-3 control label/>");
			 labelNo.html("<spring:message htmlEscape='true' code='Label.NO'/> ");
			 
			
			 var templateNoInput=$("<input class=form-control/>");
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
			 
			 var templateUnselectInput=$("<input class=form-control/>");
			 templateUnselectInput.attr("id", "unselect" );
			 templateUnselectInput.attr("name", "isPesaFlag" );
			 templateUnselectInput.attr("type", "radio" );
			 templateUnselectInput.attr("value", "unselect" );
			 templateUnselectInput.attr("style", "Display:none" );
			 if(ispesaFlag.trim()=="M"){
			 $(templateUnselectInput).prop('checked', true);
			 $("#isPesaStatus").val("");
			 }
			 
			 /* td2.append(labelNo);
			 td2.append(templateNoInput); */
			 LITWO.append(labelN);
			 LITWO.append(NIR);
			 LITWO.append(labelNo);
			 LITWO.append(templateNoInput);
			 LITWO.append(templateUnselectInput);
			 /* tr.append(td1).append(td2);
			 tbl.append(tr); */
			 var LITHREE=$("<div/>");
			 
			 templateSpanError=$("<span/>")
			 templateSpanError.attr("id", "errorIsPesaFlag");
			 templateSpanError.addClass('mandatory');
			 
			 LITHREE.append(templateSpanError);
			 /* UL.append(tbl); */
			 UL.append(LIONE);
			 UL.append(LITWO);
			 UL.append(LITHREE);
			 
			/*  divContainer.append(UL);
			 
			 divfrmtxt.append(divTitle);
			 divfrmtxt.append(divContainer); */
			 
			 divTemplate.append(divTitle);
			 divTemplate.append(UL);
			 
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


<body onload="hideGISComponentOnLoad();" oncontextmenu="return false">

  <section class="content">
	<div class="row" id="frmcontent">
	   <section class="col-lg-12">
		  <div class="box">
			 <div class="box-header with-border">
				<h3 class="box-title"><spring:message code="Label.CREATENEWVILLAGE" htmlEscape="true"></spring:message></h3>
			 </div>
			<form:form class="form-horizontal" action="addVillage.htm" method="POST" commandName="addVillageNew" id="form1" enctype="multipart/form-data" name="addVillageNew">
				<input type="hidden" name="flag1" id="flag1" value="${flag1}" />
				<input type="hidden" name="flag2" id="flag2" value="${flag2}" />
				<input type="hidden" name="subDistrictCode" id="subDistrictCode" value="${subDistrictCode}" />
				<input type="hidden" name="districtCode" id="districtCode" value="${districtCode}" />
				<input type="hidden" name="survernoset" id="survernoset" value="0" />
				<input type="hidden" name="contributedSurvey" id="contributedSurvey" value="" />
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="addVillage.htm"/>" />
				<input type="hidden" name="count" value="" id="count"></input>
				<input type="hidden" name="selectedUlbCode" id="selectedUlbCode" value="0" />
				<form:hidden path="isPesaStatus" value="N" />
				
				<div class="box-header subheading">
					<h4>
						<spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message>
					</h4>
				</div>
		<div class="box-body">
		   <div style="width: 100%">
			 <form:hidden htmlEscape="true" path="govtOrderConfig" value="${govtOrderConfig}" />
			 <form:hidden htmlEscape="true" path="operation" value="C" />
				
				<div class="form-group">
					<label class="col-sm-3 control-label"><spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
						<div class="col-sm-6">
							<select class="form-control" htmlEscape="true" name="districtNameEnglish" id="ddDistrict" onchange="getSubDistrictandULBList(this.value);" onclick="clearalllist();"
								onfocus="validateOnFocus('ddDistrict');helpMessage(this,'ddDistrict_msg');" onblur="vlidateOnblur('ddDistrict','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('ddDistrict')">
								
								  <c:choose>
								  	<c:when test="${flag1 eq 0}">
									<option value="0"><spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message></option>
										<c:forEach items="${districtList}" var="districtList">
										   <c:if test="${fn:containsIgnoreCase(districtList.operation_state, 'A'.charAt(0))}">
											  <option value="${districtList.districtCode}">${districtList.districtNameEnglish}</option>
										   </c:if>
										   
										   <c:if test="${fn:containsIgnoreCase(districtList.operation_state, 'F'.charAt(0))}">
												<option value="${districtList.districtCode}" disabled="true">${districtList.districtNameEnglish}</option>
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
							<span class="errormessage" id="errddDistrict"></span>
								<div id="ddDistrict_msg" class="mandatory" style="display: none">
									<spring:message code="Error.SOURCEDISTRICT" htmlEscape="true" />
								</div>			
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
						  <div class="col-sm-6">
							<form:select htmlEscape="true" path="subdistrictNameEnglish" class="form-control" id="ddSubdistrict" onchange="getVillageList(this.value);" onclick="clearalllist();"
								onfocus="validateOnFocus('ddSubdistrict');helpMessage(this,'ddSubdistrict_msg');" onblur="vlidateOnblur('ddSubdistrict','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('ddSubdistrict')">
								<c:if test="${not empty subDistrictList }">
									<form:option value="0">
										<spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message>
									</form:option>
								</c:if>
							</form:select>
								<span class="errormessage" id="errddSubdistrict"></span>
								<div class="errormsg">
									<form:errors htmlEscape="true" path="subdistrictNameEnglish"></form:errors>
								</div>
								<div id="ddSubdistrict_msg" class="mandatory" style="display: none">
									<spring:message code="error.PSSDT" htmlEscape="true" />
								</div>
						</div>
					</div>
				</div>

					<div class="box-header subheading">
						<h4>
							<spring:message code="Label.GENERALDETAILOFNEWVILLAGE" htmlEscape="true"></spring:message>
						</h4>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.NAMEOFNEWVILLAGEENGLISH" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
							<div class="col-sm-6">
							   <form:input htmlEscape="true" path="newVillageNameEnglish" id="OfficialAddress" class="form-control" onfocus="clear_message();show_msg('OfficialAddress');validateOnFocus('OfficialAddress');helpMessage(this,'OfficialAddress_msg');"
								onblur="officialAddressVil();vlidateOnblur('OfficialAddress','1','15','c');hideHelp();" onkeypress="return validateAlphanumericUpdateKeysWard(event);" maxlength="150"  />
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
							</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.NAMEOFNEWVILLAGELOCAL" htmlEscape="true"></spring:message></label>
						  <div class="col-sm-6">
							<form:input htmlEscape="true" id="villageNameLocal" path="newVillageNameLocal" class="form-control" maxlength="50"
								onfocus="show_msg('villageNameLocal');helpMessage(this,'villageNameLocal_msg');" onblur="hideHelp();validateSpecialCharactersforVillageValues(this.value,1);" />
								<div class="errormsg">
										<form:errors htmlEscape="true" path="newVillageNameLocal"></form:errors>
									</div>
									<div id="villageNameLocal_msg" style="display: none">
										Please Enter Village Name Local
										<%-- <spring:message code="error.PSV" htmlEscape="true"/> --%>
									</div>
						</div>
					</div>


					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.ALIASENGLISH" htmlEscape="true"></spring:message></label>
						  <div class="col-sm-6">
							<form:input htmlEscape="true" path="newVillageAliasEnglish" class="form-control" maxlength="50" />
								<div class="errormsg">
										<form:errors htmlEscape="true" path="newVillageAliasEnglish"></form:errors>
									</div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.ALIASLOCAL" htmlEscape="true"></spring:message></label>
						  <div class="col-sm-6">
							<form:input path="newVillageAliasLocal" class="form-control" maxlength="50" onblur="validateSpecialCharactersforVillageValues(this.value,2);" />
							<div class="errormsg">
										<form:errors htmlEscape="true" path="newVillageAliasLocal"></form:errors>
									</div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.VILLAGESTATUS" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
						  <div class="col-sm-6">
							<form:select path="villageType" cssClass="form-control">
								<option value=""><spring:message code="Label.SELECT" htmlEscape="true"></spring:message></option>
								<option value="R">Forest Village</option>
								<option value="U">Un-inhabitant</option>
								<option value="I">Inhabitant</option>
							</form:select>
						</div>
					</div>


					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message></label>
					   	  <div class="col-sm-6">
							<form:input path="sscode" class="form-control" id="sscode" name="sscode" maxlength="5" onblur="chekcalphanumeric(this.value,3);" />
						  </div>
					</div>
					

					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.SURVEYNUMBER" htmlEscape="true"></spring:message></label>
						  <div class="col-sm-6">
							<input type="hidden" name="psize" id="psize" />
							<table id="ptablePerson" align="left">
								<tr></tr>
								<tr>
									<td> 1 &nbsp;</td>
									<td>
										<form:input htmlEscape="true" type="text" class="form-control" path="surveyNumber" id="surveyNumber"
										name="surveyNumber" onblur="chekcalphanumeric(this.value,4);" maxlength="10" />
										<td><input type="button" name="Submit32" class="btn btn-info" value="Add Survey Number" onclick="incrementCount()" /></td>
									</td>
								</tr>

							</table>
							<div id=errBlockCode style="color: red;"></div>
							<table id="surveytable">
								<tr>
									<td><input type="button" class="btn btn-warning" value="Remove" onclick="removeRow();" /></td>
								</tr>
							</table>

						</div>
					</div>
				<!-- Part 3 GIS Nodes -->

				<%-- <div class="box-header subheading">
					<h4>
						<spring:message code="Label.GISNODES" htmlEscape="true"></spring:message>
					</h4>
				</div>
				<div align=""><%@ include
						file="../common/modifyGis_nodesVillage.jspf"%></div>
 --%>
				<div class="box-header subheading">
					<h4>
						<spring:message code="Label.HOWVILLAGEFORMED" htmlEscape="true"><span class="mandatory">*</span></spring:message>
					</h4>
				</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="sel1"></label>
						<div class="col-sm-6">
							<div class="row"> <!-- Max of 4 divs can be used inside this row div -->
								
								<div class="col">
									<label>
									<input type="radio"  htmlEscape="true" value="true" id='chkcvillage' name="createFromExistingVillages" onclick='toggledisplayed("chkcvillage","cvillage")' /> 
									<spring:message code="Label.EXISTINGVILLAGE" htmlEscape="true"></spring:message></label>
								</div>
								<div class="col">
									<label>
									<input type="radio" htmlEscape="true" value="true" id='chknothing' name="createFromNewLand" onclick='toggledisplayed("chknothing","chknothing")'/>
									<spring:message code="Label.NEWLAND" htmlEscape="true"></spring:message></label>
								</div>
								
								<div class="col" style = "display:none">
									<label>
									<input type="radio" htmlEscape="true" value="true" id='chkculb' name="createFromCoverageOfUrbanLocalBody" onclick='toggledisplayed("chkculb","culb")' />
									<spring:message code="Label.ULB" htmlEscape="true"></spring:message></label>
								</div>
							</div>
						</div>
					</div>
				
			<div id='cvillage' class="" style="visibility: hidden; display: none;">
				<div class="box-header subheading">
					<h4>
						<spring:message code="Label.CONTRIBUTINGLANDREGION" htmlEscape="true"></spring:message>
					</h4>
				</div>

					<div class="ms_container row" style="margin-left: 10px;">
						<div class="ms_selectable col-sm-5 form-group">
							<label for="ddSourceBlock">
								<strong><spring:message code="Label.VILLAGES" htmlEscape="true"></spring:message> </strong>
							</label> 
							<select class="form-control" name="select9" id="villageListMain" path="villageList" multiple="multiple"></select> 
						</div>
						<div class="ms_buttons col-sm-2">
							<br>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" id="btnaddVillageFull" name="Submit4" onclick="addItemVillage('villageListNew','villageListMain','FULL',true);checkIsPesaStatus();" >Select Full Villages <i class="fa fa-angle-double-right" aria-hidden="true"></i>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" id="btnremoveOneVillage" name="Submit4" onclick="removevillageandsurveynoForDraft('villageListNew','villageListMain',true);checkIsPesaStatus();"><i class="fa fa-angle-left" aria-hidden="true"></i>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" id="btnremoveAllVillages" name="Submit4" onclick="removeAllSelectedVillagesForDraft('villageListNew','villageListMain',true)checkIsPesaStatus();"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" id="btnaddVillagePart" name="Submit4" onclick="addItemVillageForDraft('villageListNew','villageListMain','PART',true);checkIsPesaStatus();">Select Part Villages <i class="fa fa-angle-right" aria-hidden="true"></i>
						</div>
						<div class="ms_selection col-sm-5">
							<label for="ddSourceBlock"><spring:message code="Label.CONTRIBUTINGVILLAGELIST" htmlEscape="true"></spring:message></label>
							<form:select name="_blockNameEnglish" id="villageListNew" multiple="multiple" path="contributedVillages" class="form-control"/>
							<span class="errormessage" id="errvillageListNew"></span>
							<div class="errormsg">
								<form:errors path="contributedVillages"></form:errors>
							</div>
							<button type="button" class="btn btn-primary" id="tbnSurveyNumbers" name="Submit7" onclick="getSurveyNobyVillage();"><spring:message code="Label.GETPARTVILLAGESURVEYNUMBER" htmlEscape="true"></spring:message></button>
							<div class="errormsg"></div>
							<br>
						</div>
					</div>
				
					
				<div id='csurvey' style="visibility: hidden;">				
					<div class="ms_container row" style="margin-left: 10px;">
						<div class="ms_selectable col-sm-5 form-group">
							<label for="ddSourceBlock"><spring:message code="Label.SELECTEDVILLAGESURVEYNUMBER" htmlEscape="true"></spring:message></label>
							<select class="form-control" name="select5" id="surveyListMain" multiple="multiple" path="surveyList"></select>
						</div>
						
						<div class="ms_buttons col-sm-2"><br>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" name="Submit42" onclick="fillselectedSurveryNo('surveyListNew','surveyListMain',document.getElementById('surveyListMain').value,false)"><spring:message code="Label.SELECTSURVEYNUMBER" htmlEscape="true"></spring:message><i class="fa fa-angle-double-right" aria-hidden="true" ></i>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" id="btnremoveOneSurvey" name="Submit4" onclick="removeItem('surveyListNew','surveyListMain',false)"><i class="fa fa-angle-left" aria-hidden="true"></i>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" name="Submit42" id="btnremoveAllSurveys" onclick="removeAll('surveyListNew','surveyListMain',false)"  ><i class="fa fa-angle-double-left" aria-hidden="true"></i>
						</div>
						
						<div class="ms_selection col-sm-5">
							 <div class="form-group">
								<label for="ddDestBlock"><spring:message code="Label.CONTRIBUTINGSURVEYNUMBER" htmlEscape="true"></spring:message></label> 
								<select  class="form-control" name="select5" id="surveyListNew" multiple="multiple" path="contributedSurvey"></select>
								<input type="hidden" name="_blockNameEnglish" value="1">
							</div>				
						</div>
				</div>
				<div class="box-header subheading">
						<h4>
							<spring:message code="Label.RENAME.CONTRIBUTING.VILLAGE" htmlEscape="true"></spring:message>
						</h4>
					</div>


					<div class="form-group">
						<label class="col-sm-2 control-label" for="sel1"></label>
						<div class="col-sm-6">
							<div class="radio">
								<label><input type="radio" name="rename" id="rename" value="1" onclick="renameListMethod(this.value);"><spring:message code="Label.YES" htmlEscape="true"></spring:message></label>
							</div>

							<div class="radio">
								<label><input type="radio" name="rename" id="rename" value="0" onclick="renameListMethod(this.value);" checked="checked"><spring:message code="Label.NO" htmlEscape="true"></spring:message></label>
							</div>
						</div>
					</div>
					<table id="renameList" class="table table-bordered table-hover" style="display: none">
						<tr>
							<td width="5%" style="text-align: center;"><strong><spring:message code="Label.SNO" htmlEscape="true"></spring:message></strong></td>
							<td width="25%" style="text-align: center;"><strong><spring:message code="Label.CONTRIBUTINGVILLAGE" htmlEscape="true"></spring:message></strong></td>
							<td width="20%" style="text-align: center;"><input type="button" name="CheckAll" value="Check All" onclick="checkAll(document.addVillageNew.reNameFlag)"> <input type="button" name="UnCheckAll" value="Uncheck All" onclick="uncheckAll(document.addVillageNew.reNameFlag)"></td>
							<td width="25%" style="text-align: center;"><strong><spring:message code="Label.CURRENTVILLAGENAME" htmlEscape="true"></spring:message></strong></td>
							<td width="25%" style="text-align: center;"><strong><spring:message code="Label.RENAMEVILLAGE" htmlEscape="true"></spring:message></strong></td>
						</tr>
					</table>
					<div style="display: none;">
						<input class="btn btn-info" id="btnPreviewGIS" type="button" onclick="showGISPreview();" value="<spring:message code='Button.PREVIEWGIS'   htmlEscape='true'/>" />
					</div>
						
					<div id="divispesaFlag"></div>
			</div>
		</div>		
					

					

				<!-- /.box-body  COntributing land region-->
				
				
<!-- ULB BLOCK BEGINS HERE -->

		<div id='culb' style="margin: 20px 20px 0px 20px; background: #F7F7F7; padding: 10px; visibility: hidden; display: none">
			<div class="box-header subheading">
				<h4><spring:message code="Label.SELECTEDDISTRICTULB" htmlEscape="true"></spring:message> </h4>
			</div>
				
		<div class="ms_container row" style="margin-left: 10px;">
			<div class="ms_selectable col-sm-5 form-group">
				<label for="ddSourceBlock"><strong><spring:message code="Label.ULBCOVEREDLANDREGION" htmlEscape="true"></spring:message></strong></label> 
				<select class="form-control" name="select6" path="coveredLandRegionByULB" id="ulbListMain" multiple="multiple"></select> 
			</div>
			
			<div class="ms_buttons col-sm-2"><br>
				<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" name="Submit4222"   onclick="addItem('ulbListNew','ulbListMain','FULL',true)"> <spring:message code="Label.FULLULB" htmlEscape="true"></spring:message><i class="fa fa-angle-double-right" aria-hidden="true"></i>
				<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" id="btnremoveOneULB" name="Submit4" onclick="removeItem('ulbListNew','ulbListMain',true)" ><i class="fa fa-angle-left" aria-hidden="true"></i>
				<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" name="Submit22223"   onclick="removeAll('ulbListNew','ulbListMain',true)"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
				<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" name="Submit22222"   onclick="addItem('ulbListNew','ulbListMain','PART',true)"><spring:message code="Label.PARTULB" htmlEscape="true"></spring:message><i class="fa fa-angle-right" aria-hidden="true"></i>
			</div>

			<div class="ms_selection col-sm-5"><br>
						<form:select name="select6" path="selectedCoveredLandRegionByULB" id="ulbListNew" multiple="multiple" class="form-control"/>
						<div class="errormsg"><form:errors path="selectedCoveredLandRegionByULB"></form:errors></div>
			</div>
		</div>
	</div>


<!-- Box body endeth "ULB" -->

			 	<div class="box-header subheading">
					<h4>
						<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message>
					</h4>
				</div>
				<!-- /.box-Sub-header -->

			  <%@ include file="../govtbody/ExistingGovernmentOrderVillagecp.jsp"%>  
	
				
			<div class="form-group">
				<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message><span class="mandatory">*</span></label>
					<div class="col-sm-6">
						<form:input path="orderNo" htmlEscape="true" id="OrderNo" type="text" class="form-control" maxLength="60" onfocus="helpMessage(this,'OrderNo_error');" onblur="vlidateOrderNo('OrderNo','1','60');hideHelp();" onkeypress="return validateaGovtOrderNO(event);" />
							<div id="OrderNo_error" class="error"><spring:message code="error.required.ORDERNUM" htmlEscape="true"></spring:message></div>
							<div id="OrderNo_msg" class="mandatory"><spring:message code="error.required.ORDERINPUTTYPE" text="Please Enter AlphaNumerics Space . / - ( ) Only" htmlEscape="true" /></div>
							<div class="errormsg" id="OrderNo_error1"><form:errors path="orderNo" htmlEscape="true" /></div>
							<div class="errormsg" id="OrderNo_error2" style="display: none"></div>
					</div>
			</div>   			
										
									
			<div class="form-group">
				<label class="col-sm-3 control-label"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
					<div class="col-sm-6">
					  <div class="input-group date datepicker" id="bOrderDate">
						  <form:input path="orderDate" readonly="true" id="OrderDate" type="text" class="form-control" onchange="setEffectiveDate(this.value);"
							onkeypress="validateNumeric();" onfocus="validateOnFocus('OrderDate');helpMessage(this,'OrderDate_msg');" onblur="vlidateOnblur('OrderDate','1','15','m');hideHelp();" onkeyup="hideMessageOnKeyPress('OrderDate')" /> 
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
						</div>
							<div id="OrderDate_error" class="error"><spring:message code="error.required.ORDERDATE" htmlEscape="true"></spring:message></div>
							<div id="OrderDate_msg" class="mandatory" style="display: none"><spring:message code="error.required.ORDERDATE" htmlEscape="true" /></div>
							<div class="errormsg" id="OrderDate_error1"><form:errors path="orderDate" htmlEscape="true" /></div>
							<div class="errormsg" id="OrderDate_error2" style="display: none"></div>
					</div>
			</div>   


		<div class="form-group">
			<label class="col-sm-3 control-label"><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
				<div class="col-sm-6">
				   <div class="input-group date datepicker" id="bEffectiveDate">
					<form:input id="EffectiveDate" readonly="true" path="effectiveDate" type="text" class="form-control" onkeypress="validateNumeric();"
						onchange="validateDatetoFuture('EffectiveDate','#EffectiveFutureDate_error');validateEffectiveDatecompOrderDate('OrderDate','EffectiveDate','#EffectiveDateData_error');" onfocus="validateOnFocus('EffectiveDate');helpMessage(this,'EffectiveDate_msg');"
						onblur="vlidateOnblur('EffectiveDate','1','15','m');hideHelp();" onkeyup="hideMessageOnKeyPress('EffectiveDate')" /> 
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				  </div>
					<div id="EffectiveDate_error" class="error"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"></spring:message></div>
					<div id="EffectiveDate_msg" class="mandatory" style="display: none"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true" /></div>
					<div class="errormsg" id="EffectiveDate_error1"><form:errors path="effectiveDate" htmlEscape="true" /></div>
					<div class="errormsg" id="EffectiveDate_error2" style="display: none"></div>
					<div class="errormsg" id="EffectiveDateData_error"><spring:message htmlEscape="true" code="error.compare.EFFECTIVEDATE"></spring:message></div>
					<div class="errormsg" id="EffectiveDateBlank_error"><spring:message htmlEscape="true" code="ordereffectiveDate.required"></spring:message></div>
					<div class="errormsg" id="EffectiveFutureDate_error"><spring:message htmlEscape="true" code="error.valid.EFFECTIVEFUTUREDATE"></spring:message></div>
				</div>
		</div>	
					
		<div class="form-group">
			<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
				<label class="col-sm-3 control-label"><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message></label>
					<div class="col-sm-6">
					   <div class="input-group date datepicker" id="bGazPubDate">
						<form:input id="GazPubDate" path="gazPubDate" type="text" class="form-control" readonly="true" />
						  <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
						</div>
						   <span class="errormsg" id="GazPubDate_error"></span>
						  <form:errors path="gazPubDate" cssClass="errormsg" htmlEscape="true"></form:errors>
						  <span id="GazPubDate_msg" class="mandatory" style="display: none">Please Enter Gazette Publication Date Like 12-04-2012</span>
						<div id="GazPubDate_error" class="error"></div>
						<div id="GazPubDate_msg" style="display: none"><spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true" /></div>
						<div class="errormsg" id="GazPubDate_error1"><form:errors path="gazPubDate" htmlEscape="true" /></div>
						<div class="errormsg" id="GazPubDate_error2" style="display: none"></div>
					</div>
				</c:if>
		</div>	
					<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
							<div id="divGazettePublicationUpload">
							<%@ include file="../common/attachmentgovtcp.jspf"%>
							</div>
					</c:if>
					
				<div class="form-group">
					<c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
					  <label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELGOT"></spring:message><span class="mandatory">*</span></label>
						<div class="col-sm-6">
							<form:select path="templateList" id="templateList" style="width:280px" class="form-control" onblur="vlidateOnblur('templateList','1','15','m');hideHelp();"
								onfocus="validateOnFocus('templateList');helpMessage(this,'templateList_msg');" onkeyup="hideMessageOnKeyPress('templateList')">
								<form:option value="0"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message></form:option>
								<form:options items="${templateList}" itemLabel="templateNameEnglish" itemValue="templateCode"></form:options>
							</form:select> <span class="errormsg" id="templateList_error"></span>
								<span><form:errors cssClass="errormsg" path="templateList" htmlEscape="true"></form:errors> </span>
								<span style="display: none;" class="mandatory" id="templateList_msg">Please Select Government order Template</span>
								<div id="templateList_error" class="error"></div>
								<div id="templateList_msg" style="display: none"><spring:message code="error.blank.template" htmlEscape="true" /></div>
								<div class="errormsg" id="templateList_error1"><form:errors path="templateList" htmlEscape="true" /></div>
								<div class="errormsg" id="templateList_error2" style="display: none"></div>
						</div>
					</c:if>
				</div>	


				
		<!-- Save/Publish/Close here begins -->

	<form:hidden htmlEscape="true" path="buttonClicked" value="" />
		<div class="box-footer">
			<div class="col-sm-offset-2 col-sm-10">
				<div class="pull-right">
				
				 <%-- <button type="button"  class="btn btn-success" id="BtnSA" onclick="return selectValidateAndSubmit('S');" name="Submit"  ><i class="fa fa-floppy-o"></i> <spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button> --%>
				 
					 <button type="button" class="btn btn-primary" id="BtnSAP" onclick="return selectValidateAndSubmit('P');"name="Submit"><spring:message code="Label.PUBLISH" htmlEscape="true" text="Publish" ></spring:message></button> 
					 <button type="button" class="btn btn-danger " name="Submit6" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button>
				</div>
			</div>
		</div>
				
		<!-- Save/Publish/Close here endeth -->
	 </div>
	</form:form>
			 <div class="modal fade" id="dialogBX" tabindex="-1" role="dialog" >
					<div class="modal-dialog" style="width:950px;">
							<div class="modal-content">
				  				<div class="modal-header">
				   				   <button type="button" class="close" data-dismiss="modal">&times;</button>
				    			  	<h4 class="modal-title" id="dialogBXTitle"></h4>
				    			  	
				  				</div>
				  				<div class="modal-body" id="dialogBXbody">
				        			<iframe id="myIframe" width="910" height="650"></iframe>
				        			
				     			 
								</div>
								
					</div>
				</div>
			</div>
			
			<div class="modal fade" id="sucessAlert" role="dialog">
	    <div class="modal-dialog">
	    
	      <!-- Modal content-->
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">Message</h4>
	        </div>
	        <div class="modal-body">
	           <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
			   <esapi:encodeForHTML>${msgid}.</esapi:encodeForHTML>
	        </div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-default"  id="modelclose" onclick="modelClose();"  data-dismiss="modal">Ok</button>
	        </div>
	      </div>
	      
	    </div>
  </div>
   </div>
  </section>
 </div>
</section>

<c:if test="${flag1 eq 1}">
<script>
var districtCode=parseInt('${flag2}');
getSubDistrictandULBList(districtCode);
</script>
</c:if>


</body>
</html>