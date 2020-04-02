<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<!-- <script type="text/javascript" src="js/invalidateVillage.js" charset="utf-8"></script> -->
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDesignationService.js'></script>	
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script src="js/common.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/external/jqueryCustom/js/jquery-ui-1.8.7.custom.min.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/external/jqueryCustom/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf-8" 		 src="<%=contextpthval%>/external/jqueryCustom/js/TableTools.js" ></script>
<link href="<%=contextpthval%>/external/jqueryCustom/css/jquery-ui-1.8.7.custom.css" rel="stylesheet"  type="text/css" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*" %> 
<link href="css/error.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">

$(function() {
	
	$('#Villageli').hide();
	$('#districtli').hide();
	$('#subdistrictli').hide();
	$('#villagelist').hide();
	$('#lbforgp').hide();
	
	});
	
	function SelectParent(parent) {
		var slc =$('#slc').val();
		if(slc == ""){
			alert("Please select a State First");
			$('input[value=V]').prop("checked", false);
			$('input[value=G]').prop("checked", false);
			//document.getElementById('parenttype').checked = false;
			return;
		}
		if (parent == 'V') {
			$('#districtli').show();
			$('#subdistrictli').show();
			$('#villagelist').show();
			$('#Villageli').hide();
			$('#divLgdSelIntermediateP').hide();
			$('#divLgdVillageP').hide();
		}
		if(parent == 'G'){
			getLocalBodyforGP(parent);
			$('#Villageli').show();
			$('#districtli').hide();
			$('#subdistrictli').hide();
			$('#villagelist').hide();
		}
		else{
			$('#Villageli').hide();
		}
	}
	
	function unselectall(){
		/* $('#ddLgdLBVillageSourceAtVillageCA').hide();
		$('#ddlgdLBDistAtInter').hide();
		$('#ddlgdLBDistAtVillage').hide();
		$('#ddDistrict').hide();
		$('#ddSubdistrict').hide();
		$('#villageListMain').hide(); */
		$('#districtli').hide();
		$('#subdistrictli').hide();
		$('#villagelist').hide();
		$('#divLgdSelIntermediateP').hide();
		$('#divLgdVillageP').hide();
		$('#Villageli').hide();
		$('input[value=V]').prop("checked", false);
		$('input[value=G]').prop("checked", false);
	}
	
	function getVillageList(id) {
		lgdDwrVillageService.getVillageList(id, {
			callback : handleVillageSuccess,
			errorHandler : handleVillageError
		});

	}
	// data contains the returned value
	function handleVillageSuccess(data) {
		var fieldId = 'villageListMain';
		var valueText = 'villageCode';
		var nameText = 'villageNameEnglish';
		dwr.util.removeAllOptions(fieldId);
		var dataq = [ {
			name : "Select Village"
		} ];
		dwr.util.addOptions(fieldId, dataq, '0', "name");
		dwr.util.addOptions(fieldId, data, valueText, nameText);
	}

	function handleVillageError() {
		// Show a popup message
		alert("No data found!");
	}
	
	
	function validateform() {
		clearFormErrors();
		//var parent = $('#parenttype').val();
		var parent = $('input[name=checkbox]:radio:checked').val();
		var district = $('#ddDistrict').val();
		var subdistrict = $('#ddSubdistrict').val();
		var village = $('#villageListMain').val();
		var habitationNameEng =$('#habitationNameEng').val();
		var habitationNameLocal =$('#habitationNameLocal').val();
		var scheduleDate =$('#scheduleDate').val();
		var captchaAnswer=$('#captchaAnswer').val();
		var slc=$('#slc').val();
		
		
		if(slc == ""){
			$('#err_State_Name').html("Please Enter the State");
			return false;
		}
		if(captchaAnswer == ""){
			$('#errorCaptcha').html("Please Enter the Captcha");
			return false;
		}
		if (parent == "" || typeof parent == 'undefined') {
				$('#err_parenttype').html("Please Select a parent Type");
			return false;
		}
		if (parent == 'V') {
			if (district == 0) {
				$('#err_district').html("Please Select a District");
				return false;
			}
			if (subdistrict == 0) {
				$('#err_Subdistrict').html("Please Select a Sub-District");
				return false;
			}
			if (village == 'undefined') {
				$('#err_village').html("Please Select a Village");
				return false;
			}
		}
		if (parent == 'G') {
			var districtforgp = $('#ddLgdLBVillageSourceAtVillageCA').val();
			if (districtforgp == 0) {
				$('#err_dpgp').html("Please Select a District Panchayat");
				return false;
			}
			var ipforgp = $('#ddlgdLBDistAtInter').val();
			if (ipforgp == 0) {
				$('#err_ipgp').html("Please Select a Intermediate Panchayat");
				return false;
			}
			var vpforgp = $('#ddlgdLBDistAtVillage').val();
			if (vpforgp == 0) {
				$('#err_vpgp').html("Please Select a Village Panchayat");
				return false;
			}
		}
	}
	function clearFormErrors() {
		$('.label').html('');
	}
	/*		Tier archi for GP        */

	function getLocalBodyforGP(parent) {
		var slc =$('#slc').val();
		lgdDwrlocalBodyService.gethierarchyforGP('P', slc, {
			callback : handleLBforGPSuccess,
			errorHandler : handleTradionalTypebyPRIforVError
		});
	}

	function handleLBforGPSuccess(data) {
		// Assigns data to result id
		var fieldId = 'ddLGforGP';

		dwr.util.removeAllOptions(fieldId);
		/* var dataq = [ {
			name : "Select Type"
		} ];
		dwr.util.addOptions(fieldId, dataq, '0', "name"); */

		var options = $("#ddLGforGP");
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			$(option).val(
					obj.localBodyTypeCode + ':' + obj.level + ':' + obj.nomenclatureEnglish + ':' + obj.tierSetupCode + ':'
							+ obj.parentLocalBodyTypeCode).text(obj.nomenclatureEnglish);
			options.append(option);

		});
		var sel = $('#ddLGforGP option').eq(0).val();
		hideShowDivforLocalBody(sel, '0', 'P');
	}
	function handleTradionalTypebyPRIforVError() {
		// Show a popup message
		alert("Nooo data found!");
	}

	function hideShowDivforLocalBody(id, districtCode, lbtype) {
		var st = document.getElementById('ddLgdLBVillageSourceAtVillageCA');
		document.getElementById('divLgdVillageP').style.display = 'none';
		document.getElementById('divLgdSelIntermediateP').style.display = 'none';
		st.add(new Option('Select', '0'));
		st = document.getElementById('ddlgdLBDistAtInter');
		st.add(new Option('Select', '0'));
		if (id != 0) {
			var temp = id.split(":");

			var id1 = temp[0];
			var id2 = temp[1];
			var id3 = temp[2];
			var id5 = temp[4];
			var stateCode = $('#slc').val();
			document.getElementById('flagCode').value = id1;
			document.getElementById('level').value = "";
			if (id2 == 'V') {
				document.getElementById("thirdlevel").innerHTML = " " + id3;
				if (id5 == 0) {
					getdisVillagePanchayatListforVP(stateCode, id1);
				} else {
					getLocalBodyListbylblcCodeF(stateCode, id5);
				}
			}
		}
	}
	/* ------ */
	function getLBSubTypeList(id) {
		var slc =$('#slc').val();
		lgdDwrlocalBodyService.getLBSubTypeList(slc, id, {
			callback : handleLBSubTypeListSuccess,
			errorHandler : handlehandleLBSubTypeListError
		});
	}

	function handleLBSubTypeListSuccess(data) {

	}

	function handlehandleLBSubTypeListError() {
		// Show a popup message
		alert("No data found!");
	}

	/* 	 V  */

	function getLocalBodyListbylblcCodeF(slc, lblc) {
		var slc =$('#slc').val();
		lgdDwrDesignationService.getLocalbodyDetailbyCode(slc, lblc, {
			callback : handleLocalbodylblcCodeSuccessF,
			errorHandler : handleLocalbodylblcCodeErrorF
		});
	}

	function handleLocalbodylblcCodeSuccessF(data) {

		var stateCode = $('#slc').val();
		var districtCode = 0;
		var lbtype = $('#lbType').val();
		var lgdLBType = $('#ddLGforGP').val();
		var temp = lgdLBType.split(":");
		var id1 = temp[0];
		var id2 = temp[1];

		var lblc = data[0].localBodyTypeCode;
		var plblc = data[0].parentTierSetupCode;
		var level = data[0].level;
		var name = data[0].nomenclatureEnglish;
		if (level == 'D') {
			document.getElementById("firstlevel").innerHTML = " " + name;
			if (plblc == 0) {
				document.getElementById('divLgdSelIntermediateP').style.display = 'block';
				document.getElementById('divLgdSelIntermediateP').style.visibility = 'visible';
				if (districtCode == 0) {
					getdisVillagePanchayatListforVP(stateCode, lblc);
				}
				if (districtCode != 0) {
					getLandRegionWiseDisFinal(id1, id2, districtCode, lbtype);
				}

			}
		}

		else if (level == 'I') {
			document.getElementById("secondlevel").innerHTML = " " + name;
			document.getElementById('level').value = 'I';
			if (plblc == 0) {
				document.getElementById('divLgdVillageP').style.display = 'block';
				document.getElementById('divLgdVillageP').style.visibility = 'visible';
				getdisInterPanchayatListforIP(stateCode, lblc);
			} else {
				document.getElementById('divLgdVillageP').style.display = 'block';
				document.getElementById('divLgdVillageP').style.visibility = 'visible';
				getLocalBodyListbylblcCodeF(stateCode, plblc);
			}
		}

	}

	function handleLocalbodylblcCodeErrorF() {
		// Show a popup message
		alert("No data found in local body by lblc!");
	}

	function getdisInterPanchayatListforIP(id1, id2) {
		lgdDwrlocalBodyService.getPanchayatListbylblcCode(id1, id2, {
			callback : handledisInterPanchayatSuccessforIP,
			errorHandler : handledisInterPanchayatErrorforIP
		});
	}

	function handledisInterPanchayatSuccessforIP(data) {
		var fieldId = 'ddlgdLBDistrictAtVillage';
		var fieldId1 = 'ddLgdLBInterPSourceList';

		var valueText = 'localBodyCode';
		var nameText = 'localBodyNameEnglish';

		dwr.util.removeAllOptions(fieldId);
		dwr.util.removeAllOptions(fieldId1);

		var st = document.getElementById('ddlgdLBDistrictAtVillage');
		st.add(new Option('Select', '0'));
		dwr.util.addOptions(fieldId, data, valueText, nameText);
		dwr.util.addOptions(fieldId1, data, valueText, nameText);
	}

	function handledisInterPanchayatErrorforIP() {
		// Show a popup message
		alert("No data found in District Panchayat!");
	}

	function getdisVillagePanchayatListforVP(id1, id2) {
		lgdDwrlocalBodyService.getPanchayatListbylblcCode(id1, id2, {
			callback : handledisVillagePanchayatSuccessforVP,
			errorHandler : handledisVillagePanchayatErrorforVP
		});
	}

	function handledisVillagePanchayatSuccessforVP(data) {
		var fieldId = 'ddlgdLBDistAtInter';
		var fieldId1 = 'ddLgdLBVillageSourceAtVillageCA';
		var localBodyCode = 'localBodyCode';
		var localBodyNameEnglish = 'localBodyNameEnglish';
		
		dwr.util.removeAllOptions(fieldId);
		dwr.util.removeAllOptions(fieldId1);
		var st = document.getElementById('ddLgdLBVillageSourceAtVillageCA');
		st.add(new Option('------Select------', '0'));	
		dwr.util.addOptions(fieldId1, data,localBodyCode,localBodyNameEnglish);
	}

	function handledisVillagePanchayatErrorforVP() {
		// Show a popup message
		alert("No data found in District Panchayat!");
	}

	function getWorkOnVillagePanchayatforDistrictP(id) {
		var st = "0";
		if (id > 0)
			getIntermediatePanchayatbyDcode(id);
		else {
			dwr.util.removeAllOptions('ddlgdLBInterAtVillage');
			st = document.getElementById('ddlgdLBInterAtVillage');
			st.add(new Option('Select', '0'));
		}
	}

	function getIntermediatePanchayatbyDcode(id) {
		lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
			callback : handleIntermediatePanchayatSuccess,
			errorHandler : handleIntermediatePanchayatError
		});
	}

	function handleIntermediatePanchayatSuccess(data) {
		// Assigns data to result id
		var fieldId = 'ddlgdLBDistAtInter';
		var localBodyCode = 'localBodyCode';
		var localBodyNameEnglish = 'localBodyNameEnglish';
		dwr.util.removeAllOptions(fieldId);
		var st1 = document.getElementById(fieldId);
		st1.add(new Option('------Select------', '0'));	
		var st = document.getElementById('ddlgdLBDistAtInter');
		dwr.util.addOptions(fieldId, data,localBodyCode,localBodyNameEnglish);

	}

	function handleIntermediatePanchayatError() {
		// Show a popup message
		alert("No data found!");
	}

	function getWorkOnVillagePanchayat(id) {
		var st = "0";
		if (id > 0)
			getVillagePanchayatbyDcode(id);
		else {
			//alert("asas");
			dwr.util.removeAllOptions('ddlgdLBDistAtVillage');
			st = document.getElementById('ddlgdLBDistAtVillage');
			st.add(new Option('Select', '0'));
		}
	}

	function getVillagePanchayatbyDcode(id) {
		lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
			callback : handleVillagePanchayatSuccess,
			errorHandler : handleIntermediatePanchayatError
		});
	}

	function handleVillagePanchayatSuccess(data) {
		// Assigns data to result id
		var fieldId = 'ddlgdLBDistAtVillage';
		var localBodyCode = 'localBodyCode';
		var localBodyNameEnglish = 'localBodyNameEnglish';
		dwr.util.removeAllOptions(fieldId);
		var st1 = document.getElementById(fieldId);
		st1.add(new Option('------Select------', '0'));	
		var st = document.getElementById('ddlgdLBDistAtVillage');
		dwr.util.addOptions(fieldId, data,localBodyCode,localBodyNameEnglish);
	}

	function handleIntermediatePanchayatError() {
		// Show a popup message
		alert("No data found!");
	}
	function validateSpecialCharactersforVillageValues(value, num) {
		var status = true;
		var obj = "";
		obj = document.getElementById('habitationNameLocal');
		var retVal = true;
		retVal = validateSpecialCharacters(value);
		if (retVal == false) {
			alert("Habitation Name in Local Language contains invalid characters  !");
			obj.value = "";
			status = false;
		}

		return stautus;
	}

	function checkdate(val) {
		if (val == "") {
			alert("Please Select a Date");
			return false;
		}
	}
	
	function getDistrictList(id){
		
		lgdDwrDistrictService.getDistrictList(id, {
			callback : handleDistrictSuccess,
			errorHandler : handleDistrictError
		});
		
	}


	function handleDistrictSuccess(data) {
		// Assigns data to result id	
		
		var fieldId = 'ddDistrict';
		var valueText = 'districtCode';
		var nameText = 'districtNameEnglish';
		dwr.util.removeAllOptions(fieldId);	
		var st = document.getElementById(fieldId);
		st.add(new Option('---------Select District----------', '0'));		
		dwr.util.addOptions(fieldId, data,valueText,nameText);
	}

	function handleDistrictError() {
		// Show a popup message
		alert("No data found!");
	}
	
	function getSubDistrictandULBList(id) {
		getSubDistrictList(id);
		// getSubDistrictListMerge(id);

	}
	// for first subdistrict
	function getSubDistrictList(id) {
		lgdDwrSubDistrictService.getSubDistrictListForLocalbody(id, {
			callback : handleSubDistrictSuccess,
			errorHandler : handleSubDistrictError
		});

	}

	function handleSubDistrictSuccess(data) {
		var fieldId = 'ddSubdistrict';
		var subdistrictCode = 'subdistrictCode';
		var subdistrictNameEnglish = 'subdistrictNameEnglish';
		dwr.util.removeAllOptions(fieldId);
		var st = document.getElementById('ddSubdistrict');
		st.add(new Option('Select Subdistrict', '0'));
		dwr.util.addOptions(fieldId, data, subdistrictCode, subdistrictNameEnglish);
	}

	function handleSubDistrictError() {
		alert("No data found!");
	}

</script>
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message htmlEscape="true" text="Report on Habitations" code="Label.habitations"></spring:message></h3>
			<ul class="listing">
				<%--//these links are not working <li>
					<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message> </a>
				</li> --%>
				<%-- <li>
					<a href="viewManualHelps.do?formName=${formId}&<csrf:token uri='viewManualHelps.do'/>" class="frmhelp" onclick="centeredPopup(this.href,'myWindow','yes');return false">Help</a>
				</li> --%>
			</ul>
		</div>
		
		<div class="clear"></div>
		
		<div class="frmpnlbrdr">
			<form:form commandName="habitation1" method="post" action="birtReportPost.do">
			<%-- <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="birtReport.do"/>"/> --%>
			<%-- <form:form commandName="habitation1" method="post" action="habitationpost.do">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="habitationpost.htm"/>"/> --%>
			<input type="hidden" name="flagCode" id="flagCode" value=""></input>
			<input type="hidden"  id="level" value=""></input>
			<input type="hidden" id="firstlevel" value=""></input>
			<input type="hidden" id="secondlevel" value=""></input>
			<input type="hidden" id="thirdlevel" value=""></input>
			<input type="hidden" id="lbType" value="P" ></input>
				<div class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message htmlEscape="true" text="Select Habitation" code="Label.SelectHabitation"></spring:message>
						</div>
						<%-- <div><h3>${msgid}</h3></div> to show  No data found--%>
						<div class="search_criteria">
							<div class="block">
								<ul class="blocklist">
									<li>
										<label for="ddSourceState"><spring:message	htmlEscape="true" code="Label.SELECTSTATE"></spring:message></label>
										<form:select path="" class="combofield" id="slc"	onchange="getDistrictList(this.value);unselectall(this.val);"><form:option value="">
											<spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
											<form:options items="${slc}" itemValue="stateCode" itemLabel="stateNameEnglish"></form:options>
										</form:select><br/>
										<div id="err_State_Name" class="errormsg errorr label"></div>
									</li>
									<li>
										<label for=""><spring:message text="Select Parent Type"></spring:message><strong><span id="required" class="errormsg">*</span></strong></label>
										<form:radiobutton name="checkbox" path="" id="parenttype" value="V" onclick="SelectParent(this.value);" /><spring:message htmlEscape="true" text="Village" code="Label.Village"></spring:message>
										<form:radiobutton name="checkbox" path="" id="parenttype" value="G" onclick="SelectParent(this.value);"/><spring:message htmlEscape="true"  text="Gram Panchayat" code="Label.GramPanchayat"/>
										<div id="err_parenttype" class="errormsg errorr label"></div>
										<form:errors htmlEscape="true" path="" cssClass="errormsg"/>
									</li>
									<li id="districtli">
										<label for="ddDistrict"><spring:message  text="Select District"></spring:message><strong><span id="required" class="errormsg">*</span> </strong></label>
										<form:select htmlEscape="true" path="" id="ddDistrict" Class="combofield" onchange="getSubDistrictandULBList(this.value);">
												<%-- <form:option value="0"><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
												<form:options items="${districtList}" itemLabel="districtNameEnglish" itemValue="districtPK.districtCode" /> --%>
										</form:select>
										<div id="err_district" class="errormsg errorr label"></div>
									</li>
									<li id="subdistrictli">
										<label for="ddSubdistrict"><spring:message text="Select Sub District" htmlEscape="true"></spring:message><strong><span id="required" class="errormsg">*</span> </strong></label>
										<form:select htmlEscape="true" path=""  id="ddSubdistrict" Class="combofield" onchange="getVillageList(this.value);">
										<%-- <form:option value="0"><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option> --%>
										</form:select>
										<div id="err_Subdistrict" class="errormsg errorr label"></div>
									</li>
									<li id="villagelist">
										<label for="villageListMain"><spring:message text="Select Village" htmlEscape="true"></spring:message><strong><span id="required" class="errormsg">*</span> </strong></label>
										<form:select htmlEscape="true" path="parentCode"  id="villageListMain" Class="combofield" onchange="">
										<form:option value="0"><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
										</form:select>
										<div id="err_village" class="errormsg errorr label"></div>
										<form:errors htmlEscape="true" path="" cssClass="errormsg"/>
									</li>
									
										<!-- Part for GP -->
										
									<li id="lbforgp">
										<label><spring:message htmlEscape="true" text="Select Local Body Type"></spring:message><strong><span id="required" class="errormsg">*</span> </strong></label>
										<form:select id="ddLGforGP" path="" size="1" class="combofield" onchange="hideShowDivforLocalBody(this.value,'0','P');">
											<form:option value="0">
												<spring:message htmlEscape="true" text="SELECTLOCALBODYTYPE"></spring:message>
											</form:option>
										</form:select>
										<div id="err_localbody" class="errormsg errorr label"></div>
									</li>
								<!-- 	 Uncomment it    -->
									<%-- <li id="" style="display: none">
										<label><spring:message htmlEscape="true" text="DP" ></spring:message></label>
										<form:select path="" cssClass="combofield" id="ddlgdLBDistrictAtVillage">
										<form:option value="0">
											<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
										</form:option>
										<form:option selected="selected" value="" label="--select--" />
										</form:select>
									</li> --%>
									
									<%-- <li>
										<label><spring:message htmlEscape="true" code="Label.AVAILSUBTYPE"></spring:message></label>
										<form:select path="" Class="combofield " id="ddlgdLBSubTypeCode">
										</form:select>  
									</li> --%>
									<li id="divLgdSelIntermediateP" style="display: none">
										<label><spring:message htmlEscape="true" text="Select District Panchayat" code="Label.VP" ></spring:message><strong><span id="required" class="errormsg">*</span> </strong></label>
										<!-- <label id="villagePanchAvail"></label> -->
										<form:select path="" class="combofield" id="ddLgdLBVillageSourceAtVillageCA" onchange="getWorkOnVillagePanchayatforDistrictP(this.value);">			
										</form:select>
										<div id="err_dpgp" class="errormsg errorr label"></div>
									</li>
									<li id="divLgdVillageP" style="display: none">
										<label><spring:message htmlEscape="true" text="Select Intermediate Panchayat" ></spring:message><strong><span id="required" class="errormsg">*</span> </strong></label>
										<form:select path="" Class="combofield" id="ddlgdLBDistAtInter" onchange="getWorkOnVillagePanchayat(this.value)">
										 <form:option value="0">
											<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
										</form:option> 
										<%-- <form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" /> --%>
										</form:select>
										<div id="err_ipgp" class="errormsg errorr label"></div>
									</li>
									<li id="Villageli">
										<label><spring:message htmlEscape="true" text="Select Gram Panchayat" ></spring:message><strong><span id="required" class="errormsg">*</span> </strong></label>
										<form:select path="parentCode" Class="combofield" id="ddlgdLBDistAtVillage" onchange="">
										 <form:option value="0">
											<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
										</form:option> 
										<%-- <form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" /> --%>
										</form:select>
										<div id="err_vpgp" class="errormsg errorr label"></div>
										<form:errors htmlEscape="true" path="" cssClass="errormsg"/>
									</li>
									<!-- Part for GP -->
								</ul>
							</div>
							<div class="block">
								<ul class="captcha_fields">
									<li><img src="captchaImage" alt="Captcha Image" /></li>
									<li>
										<label><spring:message code="captcha.message" htmlEscape="true" /></label><br /> 
										<form:input path="captchaAnswer" name="captchaAnswer" id="captchaAnswer" onblur="clearMessage(this.value);" /> 
										<div id="errorCaptcha" class="errormsg errorr label"></div>
										<c:if test="${captchaAnswer == false}">
											<div class="errormsg"><spring:message code="captcha.errorMessage" htmlEscape="true" /></div>
										</c:if> 
										<div class="errormsg"><form:errors path="captchaAnswer" cssStyle="color:red" /></div>
									</li>
									<li>
										<input type="submit" id="createhabitation" name="submmit" value="<spring:message code="Button.GET" htmlEscape="true" /> " onclick="return validateform();"></input>
										<!-- <input type="submit" id="createhabitation" name="submmit" value="Save" onclick="" /> -->
										<!-- <input type="submit" id="createhabitation" name="submmit" value="Save" onclick="" /> -->
										<input type="button" id="close" name="close" value="Close" onclick="javascript:go('welcome.do');" ></input>
									</li>
								</ul>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>