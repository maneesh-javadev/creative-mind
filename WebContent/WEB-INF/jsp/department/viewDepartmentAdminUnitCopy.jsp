<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="js/extendDepartment.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<!-- lgdDwrVillageService -->
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrCoveredLandRegionByULBService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/ministry.js"></script>
<script type="text/javascript">


$( document ).ready(function() {
	$('#viewDeptforadmin input[type=text]').attr("autocomplete",'off');
});


var errorFlag =0;
	var name = "";
	var regPin ="";
	var isCenterUser = isParseJson('${isCenterUser}');
	function populateSubDistrict(districtId, districtVer) {
		var val1 = parseInt(districtId);
		var val2 = parseInt(districtVer);//getSubDistrictList
		lgdDwrSubDistrictService.getSubDistrictList(val1, {
			callback : getRLBSubDistrictListCallBack,
			errorHandler : function() {
				alert("No Data Found");
			}
		});
	}

	function getRLBSubDistrictListCallBack(data) {
		var fieldId = 'subDistrictListRLB';
		var valueText = 'subdistrictCode';
		var nameText = 'subdistrictNameEnglish';
		dwr.util.removeAllOptions('subDistrictListRLB');
		var dataq = [ {
			select : "Select"
		} ];
		dwr.util.addOptions(fieldId, dataq, "select");
		dwr.util.addOptions(fieldId, data, valueText, nameText);
		displaySelectedValueInCombo(fieldId, tlc);
	}

	function clearFormErrors() {
		$('label').html('');
	};

	function showRlbUlb(id) {

		if (isParseJson('${isCenterUser}')) {
			$('#Statelabel').show();
			if (id == 1) {

				$('#ulbTbl').show();
				$('#rlbTbl').hide();

			} else {

				$('#rlbTbl').show();
				$('#ulbTbl').hide();

			}
		} else if (id == 1) {

			$('#ulbTbl').show();
			$('#rlbTbl').hide();

		} else {
			$('#rlbTbl').show();
			$('#ulbTbl').hide();

		}
	}

	/* modified by pooja 0n 26-06-2015 for centerlevel */

	function stateList(slc) {
		if (isParseJson('${isCenterUser}')) {
			$('#Statelabel').show();
			lgdDwrStateService.getAllStates({
				callback : getstaetForCenterCallBack,
				arg : slc
			});
		} else {
			$('#Statelabel').hide();
		}
	}
	function displaySelectedValueInCombo(id, code) {
		$('#' + id).val(code).attr("selected", "selected");
	}

	function getDataFromServer() {
		var stateCode = 0;
		if (isParseJson('${isCenterUser}') && slc == 0) {
			stateCode = $('#statelabel').val();
		} else if (isParseJson('${isCenterUser}') && slc > 0) {
			stateCode = slc;
		} else {
			stateCode = parseInt('${stateCode}');
		}
		lgdDwrDistrictService.getDistrictList(stateCode, {
			callback : getDataFromServerCallBack,
			errorHandler : function() {
			}
		});

	}

	function getDataFromServerCallBack(data) {

		var fieldId = 'districtListRLB';
		var valueText = 'districtCode';
		var nameText = 'districtNameEnglish';
		dwr.util.removeAllOptions('districtListRLB');
		var dataq = [ {
			select : "Select"
		} ];
		dwr.util.addOptions(fieldId, dataq, "select");
		dwr.util.addOptions(fieldId, data, valueText, nameText);
		displaySelectedValueInCombo(fieldId, dlc);
	}

	function updatesave() {
		
		clearFormErrors();
		var department = $('#category').val();
		var departmentMinistry = $('#tdMinDepartment').val();
		var departLevel = $('#adminUnitLevelName').val();
		var departOrgunit = $('#organizationUnit').val();
		
		var departOrgMinist = $('#tdMinDepartment').val(); //cntVillTown1
		var ulbRadio = $('input[name=level]:checked').length;
		var ulbList1 = $('#ulbList').val();
		var districtRlb = $('#districtListRLB').val();
		var subDistrictRlb = $('#subDistrictListRLB').val();
		var VillageRlb = $('#villageListRLB').val();
		var address1 = $('#address1').val();//adrLine1
		var stateValue = $('#statelabel').val();
		var ulbRadioButton = $('input[name=level]:checked').val();
		var orgUnitName = $('#orgunitname').val();
		var pinCode = $('#pinCode').val();
		regPin = /^[1-9][0-9]+$/;
		var email = $('#email').val();
		var phoneNo = $('#phoneNo').val();
		var reg = /^[0-9]+$/;

		  if (department == "")

		{
			$('#err_category').html("Please Select Department");
			return false;
			errorFlag =0;
		} else if (departmentMinistry == "Select")

		{
			$('#err_tdMinDepartment').html("Please Select Department");
			return false;
			errorFlag =0;
		}

		else if (departLevel == "Select")

		{
			$('#err_adminUnitLevelName').html("Please Select Department Level");
			return false;
			errorFlag =0;
		}

		else if (departOrgunit == "Select")

		{
			$('#err_organizationUnit').html("Please Select Organization Unit");
			return false;
			errorFlag =0;
		}  
         
		else
			$('#err_organizationUnit').html(" ");
		//var pinCode = $('#pinCode').val();
	     
	
		if(pinCode != '')
			{
		 if (!reg.test(pinCode)){
			$('#err_pinCode').html("Please Enter digit only");
			return false;
			errorFlag =0;
		}
		 else if ((pinCode.length > 0)) {
			if (pinCode.length < 6) {
				$('#err_pinCode').html("Please Enter the 6 digit pincode");
				return false;
				errorFlag =0;
			}  
		
			else
			$('#err_pinCode').html(" ");
		 }
			}
		
		//hide email error
		 
		 if (email.length > 0) {
				var emailReg = new RegExp(
						/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
				var valid = emailReg.test(email);
				if (!valid){
					$('#err_email').html("Please Enter the Valid Email Address");
					return false;
					errorFlag =0;
				}
				else
				$('#err_email').html(" ");
				
			}
		 
		 // hide phone error
		 if (phoneNo.length > 0) {
				var pattern = /^\d{12}$/;
				if (!pattern.test(phoneNo)) {
					$('#err_phoneno').html("Please Enter the Valid Phone No ");
					return false;
					errorFlag =0;
					
				}
				else
				$('#err_phoneno').html(" ");
			}
			

			//var orgUnitName = $('#orgunitname').val();
			var nameReg = /^[a-zA-Z][0-9a-zA-Z.,()-\s\/]+$/;
			//var reg = /^[0-9]+$/;
			
			if (orgUnitName == '') {
				$('#err_orgUnitName').html("Please Enter the Org Unit Name");
				return false;
				errorFlag =0;
			}
			
			
	         else if (!nameReg.test(orgUnitName)){
				$('#err_orgUnitName').html("Please Enter the Valid Org Unit Name");
				return false;
				errorFlag =0;
				}
			else
				$('#err_orgUnitName').html(" ");
		 
		 
		 errorFlag =1;
		
		if(errorFlag == 1)
			{
			document.forms['viewDeptforadmin'].method = "POST";
			document.forms['viewDeptforadmin'].action = "viewOrganizationUnit.htm";
			document.forms['viewDeptforadmin'].submit(); 
			
			}
		
		
	}
	/*	else if (ulbRadio <= 0) {

			$('#err_level').html("Please Select atleast One ULB / RLB");
			return false;
		}

		else if (ulbRadioButton == 1) {

			if (stateValue == "Select") {

				$('#err_statelabel').html("Please Select State");
				return false;
			}

			else if (ulbList1 == "Select") {
				$('#err_ulbList').html("Please select ULB");
				return false;
			}

		} else if (ulbRadioButton == 0) {

			if (stateValue == "Select") {
				$('#err_statelabel').html("Please Select State");
				return false;
			} else if (districtRlb == "Select") {
				$('#err_districtListRLB').html("Please Select District");
				return false;
			} else if (subDistrictRlb == "Select")

			{
				$('#err_subDistrictListRLB').html("Please Select Sub-District");
				return false;
			} else if (VillageRlb == "Select")

			{
				$('#err_villageListRLB').html("Please Select Village");
				return false;
			}

		}*/
		
		
		/*if (orgUnitName == '') {
			$('#err_orgUnitName').html("Please Enter the Org Unit Name");
			return false;
		}
	  /* modified by pooja on 02-07-2015 for checking existing orgUnitName within State   
		else if(name == orgUnitName){
			furtherValidation(email,phoneNo,address1,pinCode);
		}
		else{
			
			// new code here 
			var nameReg = /^[a-zA-Z][a-zA-Z.,()-\s\/]+$/;
			if (!nameReg.test(orgUnitName)){
			$('#err_orgUnitName').html("Please Enter the Valid Org Unit Name");
			return false;
			}
			furtherValidation(email,phoneNo,address1,pinCode);
			
			//temp code was disabled check for duplicate org unit, other code show above. Maneesh Kumar 15July2019 discuss with mam 
			/* var stateLevelId = '${stateCode}';
			lgdDwrOrganizationService.checkExistingOrgUnitName(parseInt(stateLevelId),orgUnitName,{
				async : true,
				callback : function(data) {
					if (data) {
						$('#err_orgUnitName').html("Org Unit Name Already Exist");
						return false;
					} else {
						var nameReg = /^[a-zA-Z][a-zA-Z.,()-\s\/]+$/;
						if (!nameReg.test(orgUnitName)){
						$('#err_orgUnitName').html("Please Enter the Valid Org Unit Name");
						return false;
						}
						furtherValidation(email,phoneNo,address1,pinCode);
					}
				},
			errorHandler:function() { alert("Error"); }
			}); 
			
			
		}
	}*/
	/* 
	function furtherValidation(email,phoneNo,pinCode){ */
		 /*if (email.length > 0) {
			var emailReg = new RegExp(
					/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
			var valid = emailReg.test(email);
			if (!valid){
				$('#err_email').html("Please Enter the Valid Email Address");
				return false;
			}
			
		} */
		
		/*if (phoneNo.length > 0) {
			var pattern = /^\d{10}$/;
			if (!pattern.test(phoneNo)) {
				$('#err_phoneno').html("Please Enter the Valid Phone No ");
				return false;
			}
		}*/
		
		// Modified by Sushma Singh on date  19/july/2019
		
		//remove mandatry from address1
		
		
		/*if (address1 == '') {
			$('#err_address1').html("Please Enter the Address");
			return false;
		}

		
		/* if ((pinCode.length > 0)) {
			if (pinCode.length < 6) {
				$('#err_pinCode').html("Please Enter the 6 digit pincode");
			/* 	return false;
			} else if (!regPin.test(pinCode)) {
				$('#err_pinCode').html("Please Enter valid  6 digit pincode");
				return false;
			}*/
		
		
		
		
	
	
	/* modified by pooja on 24-06-2015 */
	function populateValue(id) {
		name = $('#organizationUnit option:selected').text();
		$('#code').show();
		$('#orgunitcode').val(id);
		$("#orgUnitCode").text(id);
		lgdDwrOrganizationService.getOrganizationUnitDetails(id, {
			callback : getOrgUnitDetails,
			errorHandler : function() {
				alert("No Data Found");
			}
		});

	}
	/* added by pooja on 24-06-2015 */
	function getOrgUnitDetails(data) {
		var orgUnitList = data;
		$("#orgSpecCode").val(orgUnitList[0].orgSpecCode);
		$("#orgunitname").val(orgUnitList[0].orgUnitName);
		$("#email").val(orgUnitList[0].email);
		$("#phoneNo").val(orgUnitList[0].phoneNo);
		$("#address1").val(orgUnitList[0].address1);
		$("#address2").val(orgUnitList[0].address2);
		$("#address3").val(orgUnitList[0].address3);
		$("#pinCode").val(orgUnitList[0].pinCode);
		var code = orgUnitList[0].code;
		if (orgUnitList[0].level == "U") {
			jQuery(function() {
				RadionButtonSelectedValueSet('level', '1');
			});
			showRlbUlb(1);
			lgdDwrOrganizationService.getSlcByLbOrVillageCode(code, {
				callback : getSlc,
				arg : code,
				errorHandler : function() {
					alert("Error");
				}
			});

		} else if (orgUnitList[0].level == "R") {
			jQuery(function() {
				RadionButtonSelectedValueSet('level', '0');
			});
			showRlbUlb(0);
			lgdDwrOrganizationService.getVlcTlcDlc(code, {
				callback : getVlcTlcDlcList,
				errorHandler : function() {
					alert("Error");
				}
			});
		} else {
			$("input:radio[name='level']").each(function(i) {
				this.checked = false;
			});
			$('#ulbTbl').hide();
			$('#rlbTbl').hide();
		}
	}
	function getSlc(data, code) {
		slc = data;
		$("#statelabel").val(slc);
		stateUlblist(code);
		stateList(slc);
	}
	var vlc = 0;
	var tlc = 0;
	var dlc = 0;
	function getVlcTlcDlcList(data) {
		var vlcTlcDlcList = data;
		vlc = vlcTlcDlcList[0];
		tlc = vlcTlcDlcList[1];
		dlc = vlcTlcDlcList[2];
		slc = vlcTlcDlcList[3];
		getDataFromServer();
		populateSubDistrict(dlc, 1);
		populateVillage(tlc, 1);
		if (isParseJson('${isCenterUser}')) {
			stateList(slc);
		}
	}
	function RadionButtonSelectedValueSet(name, SelectdValue) {
		$('input[name="' + name + '"][value="' + SelectdValue + '"]').prop(
				'checked', true);
	}
	function selectCode(code) {
		$('#ulbList').val(code).attr("selected", "selected");
	}
	function populateVillage(val1, val2) {
		val1 = parseInt(val1);
		val2 = parseInt(val2);
		lgdDwrVillageService.getVillageList(val1, {
			callback : getVillageListCallBack,
			errorHandler : function() {
				alert("No Data Found");
			}
		});
	}

	function getVillageListCallBack(data) {
		var fieldId = 'villageListRLB';
		var valueText = 'villageCode';
		var nameText = 'villageNameEnglish';
		dwr.util.removeAllOptions('villageListRLB');
		var dataq = [ {
			select : "Select"
		} ];
		dwr.util.addOptions(fieldId, dataq, "select");
		dwr.util.addOptions(fieldId, data, valueText, nameText);
		displaySelectedValueInCombo(fieldId, vlc);
	}
	/* modified by pooja on 24-06-2015 */
	var slc = 0;
	function stateUlblist(orgUnitCode) {
		var stateCode = 0;
		if (isParseJson('${isCenterUser}') && orgUnitCode == '0') {
			stateCode = $('#statelabel').val();
		} else if (isParseJson('${isCenterUser}') && orgUnitCode != '0') {
			stateCode = slc;
		} else {
			stateCode = parseInt('${stateCode}');
		}
		lgdDwrCoveredLandRegionByULBService.getULBList(stateCode, {
			callback : getRlbUlbForCenterCallBack,
			arg : orgUnitCode,
			errorHandler : function() {
			}
		});
	}

	function getRlbUlbForCenterCallBack(data, orgUnitCode) {
		var fieldId = 'ulbList';
		var valueText = 'localBodyNameEnglish';
		var nameText = 'localBodyCode';
		dwr.util.removeAllOptions('ulbList');
		var dataq = [ {
			select : "Select"
		} ];
		dwr.util.addOptions(fieldId, dataq, "select");
		dwr.util.addOptions(fieldId, data, nameText, valueText);
		selectCode(orgUnitCode);
	}

	function getstaetForCenterCallBack(data, slc) {
		var fieldId = 'statelabel';
		var valueText = 'stateCode';
		var nameText = 'stateNameEnglish';
		dwr.util.removeAllOptions('statelabel');
		var dataq = [ {
			select : "Select"
		} ];
		dwr.util.addOptions(fieldId, dataq, "select");
		dwr.util.addOptions(fieldId, data, valueText, nameText);
		checkCode(slc);
	}
	function checkCode(code) {
		$('#statelabel').val(code).attr("selected", "selected");
	}
	function resetList1() {
		$("tdMinDepartment").val(0);
		$("#adminUnitLevelName").val(0);
		$("#organizationUnit").val(0);
		resetList();
	}
	function resetList() {
		setSlcZero();
		$("#statelabel").val(0);
		$("#ulbList").val(0);
		$("#districtListRLB").val(0);
		$("#subDistrictListRLB").val(0);
		$("#villageListRLB").val(0);
	}
	function setSlcZero() {
		slc = 0;
	}
	function getAllStates() {

		lgdDwrStateService.getAllStates({
			callback : getstaetForCenterCallBack,

		});

	}
	function clearFormErrors() {
		$('.label').html('');
	};
	
	// Modified by Sushma Singh on 23-july-2019
	//Fuctions for hide error messages after entering correct value
	
	function checkEmail()
	{
		var email = $('#email').val();
		
		if (email.length > 0) {
			var emailReg = new RegExp(
					/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
			var valid = emailReg.test(email);
			if (!valid){
				$('#err_email').html("Please Enter the Valid Email Address");
				return false;
			}
			else
			$('#err_email').html(" ");
			
		}
	}
	
	function checkPhoneNo()
	{
		var phoneNo = $('#phoneNo').val();
		if (phoneNo.length > 0) {
			var pattern = /^\d{12}$/;
			if (!pattern.test(phoneNo)) {
				$('#err_phoneno').html("Please Enter the Valid Phone No ");
				return false;
			}
			else
			$('#err_phoneno').html(" ");
		}
		
	}
	
	function checkPinCode()
	{
		
	
		var pinCode = $('#pinCode').val();
		var reg = /^[0-9]+$/;
		
		if(pinCode != '')
			{
		 if (!reg.test(pinCode)){
			$('#err_pinCode').html("Please Enter digit only");
			return false;
			errorFlag =0;
		}
		 else if ((pinCode.length > 0)) {
			if (pinCode.length < 6) {
				$('#err_pinCode').html("Please Enter the 6 digit pincode");
				return false;
				errorFlag =0;
			}  
			else
			$('#err_pinCode').html(" ");
		}
		
			}
		else
			{
			return true;
			}
			}
			
	
	
	function checkError() {

		clearFormErrors();
		var department = $('#category').val();
		var departmentMinistry = $('#tdMinDepartment').val();
		var departLevel = $('#adminUnitLevelName').val();
		var departOrgunit = $('#organizationUnit').val();
		
		var departOrgMinist = $('#tdMinDepartment').val(); //cntVillTown1
		
         if (department == " ")

		{
			$('#err_category').html("Please Select Department");
			return false;
		}
         else
        	 $('#err_category').html(" ");
         
          if (departmentMinistry == "Select")

		{
			$('#err_tdMinDepartment').html("Please Select Department");
			return false;
		}
          else
        	  $('#err_tdMinDepartment').html(" ");
          
		if (departLevel == "Select")

		{
			$('#err_adminUnitLevelName').html("Please Select Department Level");
			return false;
		}
		else
			$('#err_adminUnitLevelName').html(" ");

	 if (departOrgunit == "Select")

		{
			$('#err_organizationUnit').html("Please Select Organization Unit");
			return false;
		}
	   else
		 $('#err_organizationUnit').html(" ");	 
	
	}
	
	
	function orgUnitError()
	{
		
	
		var orgUnitName = $('#orgunitname').val();
		var nameReg = /^[a-zA-Z][0-9a-zA-Z.,()-\s\/]+$/;
		var reg = /^[0-9]+$/;
		
		if (orgUnitName == '') {
			$('#err_orgUnitName').html("Please Enter the Org Unit Name");
			return false;
		}
		else if(!reg.test(orgUnitName))
			{
			$('#err_orgUnitName').html(" ");
			return false;
			}
		
         else if (!nameReg.test(orgUnitName)){
			$('#err_orgUnitName').html("Please Enter the Valid Org Unit Name");
			return false;
			}
		else
			$('#err_orgUnitName').html(" ");
	}
	// end of hide error message fuction
		
	
</script>

</head>
<body>
	<section class="content">
    	<div class="row">
        	<section class="col-lg-12 ">
	        	<div class="box ">
	       			<div class="box-header with-border">
	           			<h3 class="box-title">
	           				<spring:message htmlEscape="true" code="Label.CaptureOrgUnit" text="Modify Organisation Unit Details"></spring:message>
	           			</h3>
	       			</div>
		            <div class="box-body">
		            	<div class="box-header subheading">
                        	<h4><spring:message code="Label.SELECTCRITERIA" htmlEscape="true"></spring:message></h4>
	                    </div>
	                    
	                    <form:form commandName="viewDeptforadmin" class="form-horizontal" onsubmit="return updatesave();" action="viewOrganizationUnit.htm" method="POST"  id="viewDeptforadmin">
							<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewOrganizationUnit.htm"/>" />
							<input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("stateCode")%>' />
					    	<input type='hidden' id="specificLevel" name="SpecificLevel" value='<c:out value='${category}' escapeXml="true"></c:out>' />
	                    <c:if test="${isCenterUser}">
	                    	<div class="form-group">
								<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELMIN"></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6">
									<form:select path="" class="form-control" id="category" onchange="getDepartmentDetails(this.value);resetList1();">
										<form:option selected="selected" value="" label="select"></form:option>
										<form:options items="${orgList}" itemValue="orgCode" itemLabel="orgName"></form:options>
									</form:select>
									<div id="err_category" class="mandatory"></div>
		             			</div>
	                       </div>
	                       <div class="form-group">
								<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.UnitLevelDEPT" text="Select Organization"></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6">
									<form:select path="" id="tdMinDepartment" onchange="getAdminUnitDepartmentDetailsOrg(this.value);resetList1();" class="form-control">
										<form:option value="0">
											<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
										</form:option>
									</form:select>
									<div id="err_tdMinDepartment" class="mandatory"></div>
		             			</div>
	                       </div>
	                       <div class="form-group">
								<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.UnitLevelOrganization" text="Select Organization level"></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6">
									<form:select path="" id="adminUnitLevelName" onchange="getOrganizationParent1(tdMinDepartment.value,this.value);resetList();" class="form-control">
								    	<form:option value="0">
											<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
										</form:option>
									</form:select>
		             			</div>
		             			<div id="err_adminUnitLevelName" class="mandatory"></div>
	                       </div>
	                    </c:if>
	                    
	                    <c:if test="${!isCenterUser}">
	                    
		                       <div class="form-group">
									<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.Organization" text="Select  Organization"></spring:message><span class="mandatory">*</span></label>
									<div class="col-sm-6">
										<form:select
											path="" class="form-control" id="category" onchange="getAdminUnitDepartmentDetailsOrg(this.value);resetList1();checkError();">
											<form:option value="">
												<form:option selected="selected" value="" label="select" />
											</form:option>
											<form:options items="${orgList}" itemValue="orgCode" itemLabel="orgName"></form:options>
										</form:select>
			             			</div>
			             			<div id="err_category" class="mandatory"></div>
		                       </div>
		                       
		                       <div class="form-group">
									<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.UnitLevelOrganization" text="Select Organization level"></spring:message><span class="mandatory">*</span></label>
									<div class="col-sm-6">
										<form:select path="" id="adminUnitLevelName" onchange="getOrganizationParent1(category.value,this.value);resetList();checkError();" class="form-control">
											<form:option value="0">
												 <spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
										    </form:option>
									    </form:select>
								 	    <div id="err_adminUnitLevelName" class="mandatory"></div>
			             			</div>
		                       </div>
	                    </c:if>
	                    
	                    		<div class="form-group">
									<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.orgUnits" text="Select Org Units"></spring:message><span class="mandatory">*</span></label>
									<div class="col-sm-6">
										<form:select path="" id="organizationUnit" class="form-control"  onchange="populateValue(this.value);checkError();">
											<form:option value="0">
												<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
											</form:option>
										</form:select>
										<div id="err_organizationUnit" class="mandatory"></div>
			             			</div>
		                       </div>
	                    
	                    
	                    		<div class="box-header subheading">
                             		<h4>Organization Unit Location</h4>
		                   		</div>
	                    
	                    		<div class="form-group" style="margin-left: 10%">
									<div class="col-sm-12">
			             			<label class="radio-inline">
      									<form:radiobutton path="" id="level" name="level" value="1" 
									onclick="showRlbUlb(this.value);stateList(0);stateUlblist(0);resetList();" onfocus="" onblur="" /><spring:message htmlEscape="true" code="Label.ulb" text="ULB"></spring:message>
    								</label>
    								<!-- <div class="mandatory" id="helpId6" style="display: none;"> 
									<spring:message code="label.btn.ulb" />
									</div>-->
    								<label class="radio-inline">
      									<form:radiobutton path="" id="level" name="level" value="0" onclick="showRlbUlb(this.value);stateList(0);getDataFromServer();resetList();" onfocus="" onblur="" /><spring:message code="label.rlb" text="RLB"></spring:message>
    								</label>
    								<%-- <div class="mandatory" id="helpId7" style="display: none;">
									<spring:message code="label.btn.rlb" />
									</div> --%>
									<label class="radio-inline">
      									<input type="radio" name="imgsel" value="" checked="checked" style="display:none;" />
    								</label>
									
									
			             			</div>
		                       </div>
	                    
	                    
	                    <c:if test="${isCenterUser}">
						    	<div class="form-group">
									<label class="col-sm-3 control-label"><spring:message code="State" text="State"></spring:message><!-- <span class="mandatory">*</span> --></label>
									<!-- <div id="errormsgrlb" style="display: none; color: red"></div>  -->
									<div class="col-sm-6">
										<select name="statelabel" id="statelabel"
								            onchange="stateUlblist(0);setSlcZero();getDataFromServer();" class="form-control" onfocus="" onblur="">
									<option value=""><spring:message code="common.select" text="Select" />
									</option>
							        </select>
								   <!--  <div id="err_statelabel" class="mandatory"></div> -->
			             			</div>
		                       </div>
					     </c:if>
	                    
	                    <div class="form-group" id="ulbTbl" style="display: none;">
									<label class="col-sm-3 control-label"><spring:message code="ULB" text="ULB"></spring:message><!-- <span class="mandatory">*</span> --></label>
									<div class="col-sm-6">
										<select name="ulbList" id="ulbList" class="form-control" onfocus="" onblur="">
											<option value="0">
											<spring:message code="common.select" text="Select" />
											</option>
									       </select>
									       <div class="mndt" id="helpId13" style="display: none;"><spring:message code="" />
			             			</div>
		                       </div>
	                    <!-- <div id="err_ulbList" class="mandatory"></div> -->
	                    </div>
	                    
	                    <div id="rlbTbl" style="display: none;">
	                    	<div class="form-group">
									<label class="col-sm-3 control-label"><spring:message code="District" text="District"></spring:message></label>
									
									<!-- <div id="errormsgrlb" style="display: none; color: red"></div> -->
									<div class="col-sm-6">
										<select name="districtListRLB" id="districtListRLB" onchange="populateSubDistrict(this.value, 1);" class="form-control" onfocus="clearrlbUlbErrors();" >
											<option value="0">
												<spring:message code="common.select" text="Select" />
											</option>
								   		</select>
			             			</div>
		                      </div>
		                      
		                      <div class="form-group">
									<label class="col-sm-3 control-label"><spring:message code="venueForm.subdist" text="SubDistrict" /></label>
									 
									<div class="col-sm-6">
										<select name="subDistrictListRLB" id="subDistrictListRLB" onchange="populateVillage(this.value, 1);" class="form-control" onfocus="clearrlbUlbErrors();" onblur="">
											<option value="0">
												<spring:message code="common.select" text="Select" />
											</option>
							     		</select>
			             			</div>
		                      </div>
		                      
		                      
		                      <div class="mndt" id="helpId11" style="display: none;">
								<spring:message code="label.subdistrict" />
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label"><spring:message code="venueForm.vill" text="Village" /></label>
									
									<div class="col-sm-6">
										<select name="villageListRLB" id="villageListRLB" class="form-control" onfocus="clearrlbUlbErrors();" onblur="">
											<option value="0">
												<spring:message code="common.select" text="Select" />
										    </option>
								        </select>
			             			</div>
			             			<!-- <div class="mndt" id="helpId12" style="display: none;"> 
										<spring:message code="label.vlgonly.slct" />
									</div>-->
		                      </div>
	                    </div>
	                    
	                    
	                    
	                    <div class="box-header subheading">
                        	<h4>Organization Unit Details</h4>
		                </div>
	                    
	                    
                    	<div class="form-group" id="code" style="display: none;">
							<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.orgunitcode" text="Org Unit Code"></spring:message></label>
							<div class="col-sm-6" id="orgUnitCode">
								
							</div>
							
	             		</div>
	                    
	                    <div class="form-group">
							<label class="col-sm-3 control-label"><spring:message code="label.orgunitname " text="Org Unit Name (In English)" /></label>
							<form:input type="hidden" path="orgUnitCode" id="orgunitcode"/>
							<div class="col-sm-6">
							<div id="err_orgUnitName" class="mandatory"></div>
								<form:input path="orgUnitName" id="orgunitname" class="form-control"  maxlength="70" onkeyup="orgUnitError();" onblur="" />
							</div>
	             		</div>
	             		
	             		<!--    add one field for input org unit in local language name by Sushma Singh on date 19/july/2019 -->
	             		
	             		<div class="form-group">
							<label class="col-sm-3 control-label"><spring:message code="label.orgunitnameinlocal " text="Org Unit Name (In Local Language)" /></label>
							<%-- <form:input type="hidden" path="orgUnitCode" id="orgunitcode"/> --%>
							<div class="col-sm-6">
							<!-- <div id="err_orgUnitName" class="mandatory"></div> -->
								<form:input path="orgUnitNameInLocal" id="orgUnitNameInLocalId" class="form-control"  maxlength="70" onfocus="" onblur="" />
							</div>
	             		</div>
	             		
	             		<div class="form-group">
							<label class="col-sm-3 control-label"><spring:message code="label.orgspecificcode" text="Org Specific Code" /></label>
							<div class="col-sm-6">
								<form:input path="orgSpecCode" id="orgSpecCode" value="" class="form-control"  maxlength="10" onfocus=""  />
							</div>
	             		</div>
	             		
	             		<div class="form-group">
							<label class="col-sm-3 control-label"><spring:message code="label.emailid" text="Email Id" /></label>
							<div class="col-sm-6">
							<div id="err_email" class="mandatory"></div>
								<form:input path="email" id="email" value="" class="form-control" maxlength="70" onkeyup="checkEmail();"  />
								<form:errors id="email" path="email" cssClass="errormsg"></form:errors>
							
							
							</div>
	             		</div>
	             		
	             		<div class="form-group">
							<label class="col-sm-3 control-label"><spring:message code="label.phoneno" text="Phone No" /></label>
							<div class="col-sm-6">
								<div id="err_phoneno" class="mandatory"></div>
							    <form:input path="phoneNo" id="phoneNo" value="" class="form-control"  maxlength="12" onkeyup="checkPhoneNo();"  />
								<form:errors id="phoneNo" path="phoneNo" cssClass="errormsg"></form:errors>
							</div>
	             		</div>
	             		
	             		
	             		<div class="form-group">
							<label class="col-sm-3 control-label"><spring:message code="label.address1" text="Address 1" /></label>
							<div class="col-sm-6">
								
								 <form:textarea path="address1" id="address1" value="" class="form-control"   onfocus="" />
							    
								
							    
							</div>
	             		</div>
	             		
	             		
	             		<div class="form-group">
							<label class="col-sm-3 control-label"><spring:message code="label.address2" text="Address 2" /></label>
							<div class="col-sm-6">
								<form:textarea path="address2" id="address2" value="" class="form-control" onfocus="" />
							</div>
	             		</div>
	             		
	             		
	             		<div class="form-group">
							<label class="col-sm-3 control-label"><spring:message code="label.address3" text="Address 3" /></label>
							<div class="col-sm-6">
							<form:textarea path="address3" id="address3" value="" class="form-control" onfocus=""  />
							</div>
	             		</div>
	             		
	             		<div class="form-group">
							<label class="col-sm-3 control-label"><spring:message code="label.pincode" text="Pin Code" /></label>
							<div class="col-sm-6">
								<form:input path="pinCode" id="pinCode" value="" class="form-control" maxlength="6" onkeyup="checkPinCode()" />
								 <form:errors id="pinCode" path="pinCode" cssClass="errormsg"></form:errors>
								<div id="err_pinCode" class="mandatory"></div>
							</div>
	             		</div>
	                    
	                    
	                    <div class="box-footer">
		                     <div class="col-sm-offset-2 col-sm-10">
		                       <div class="pull-right">
		                            <button type="button"  name="Submit" class="btn btn-success" onclick="return updatesave();"><i class="fa fa-floppy-o"></i> <spring:message code="Button.SAVE"  htmlEscape="true"></spring:message></button>
									<button type="button" name="Submit6" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
		                        </div>
		                     </div>   
                 		</div>	
	                    
	                    
	                    </form:form>
	                    
	                    
		            </div>
		         </div>
		    </section>
		</div>
</section>
</body>
</html>








