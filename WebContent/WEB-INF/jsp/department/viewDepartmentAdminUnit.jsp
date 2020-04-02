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
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/datatable/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/datatable/jquery-ui-1.8.7.custom.min.js"></script>
<script src="<%=contextpthval%>/datatable/TableTools.js" charset="utf-8" type="text/javascript"></script>
<link href="<%=contextpthval%>/datatable/jquery-ui-1.8.7.custom.css" rel="stylesheet" type="text/css" />
<link href="<%=contextpthval%>/datatable/demo_table_jui.css" rel="stylesheet" type="text/css" />
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/ministry.js"></script>
<script type="text/javascript">
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

		if (department == "")

		{
			$('#err_category').html("Please Select Department");
			return false;
		} else if (departmentMinistry == "Select")

		{
			$('#err_tdMinDepartment').html("Please Select Department");
			return false;
		}

		else if (departLevel == "Select")

		{
			$('#err_adminUnitLevelName').html("Please Select Department Level");
			return false;
		}

		else if (departOrgunit == "Select")

		{
			$('#err_organizationUnit').html("Please Select Organization Unit");
			return false;
		}

		else if (ulbRadio <= 0) {

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

		}
		if (orgUnitName == '') {
			$('#err_orgUnitName').html("Please Enter the Org Unit Name");
			return false;
		}
	  /* modified by pooja on 02-07-2015 for checking existing orgUnitName within State   */
		else if(name == orgUnitName){
			furtherValidation(email,phoneNo,address1,pinCode);
		}
		else{
			var stateLevelId = '${stateCode}';
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
	}
	function furtherValidation(email,phoneNo,address1,pinCode){
		if (email.length > 0) {
			var emailReg = new RegExp(
					/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
			var valid = emailReg.test(email);
			if (!valid){
				$('#err_email').html("Please Enter the Valid Email Address");
				return false;
			}
		}
		if (phoneNo.length > 0) {
			var pattern = /^\d{10}$/;
			if (!pattern.test(phoneNo)) {
				$('#err_phoneno').html("Please Enter the Valid Phone No ");
				return false;
			}
		}
		if (address1 == '') {
			$('#err_address1').html("Please Enter the Address");
			return false;
		}

		if ((pinCode.length > 0)) {
			if (pinCode.length < 6) {
				$('#err_pinCode').html("Please Enter the 6 digit pincode");
				return false;
			} else if (!regPin.test(pinCode)) {
				$('#err_pinCode').html("Please Enter valid  6 digit pincode");
				return false;
			}
		}
		document.forms['viewDeptforadmin'].method = "POST";
		document.forms['viewDeptforadmin'].action = "viewOrganizationUnit.htm";
		document.forms['viewDeptforadmin'].submit(); 
	}
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
</script>

</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle">
				<spring:message htmlEscape="true" code="Label.CaptureOrgUnit" text="Capture Org Unit Details"></spring:message>
			</h3>
			<ul id="showhelp" class="listing">
			<%-- //these links are not working 	<li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img
						src="images/plus.jpg" border="0" /></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></li> --%>
			</ul>

		</div>
		<div class="clear"></div>
 		<div class="frmpnlbrdr">
			<form:form commandName="viewDeptforadmin" action="viewOrganizationUnit.htm" method="POST" id="viewDeptforadmin">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewOrganizationUnit.htm"/>" />
			<input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("stateCode")%>' />
	    	<input type='hidden' id="specificLevel" name="SpecificLevel" value='<c:out value='${category}' escapeXml="true"></c:out>' />
		    <div id="cat">
				<div class="frmpnlbg" id='showbystatelinelevel'>
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message>
						</div>
							
						<div>
							<ul class="blocklist">
								<c:if test="${isCenterUser}">
										<li id="tdMinistry"><label for="tdMinistry"><spring:message htmlEscape="true" code="Label.SELMIN"
												text=""></spring:message></label><span class="errormsg label">*</span><br />
											<form:select path="" class="combofield" style="width: 500px;" id="category" onchange="getDepartmentDetails(this.value);resetList1();">
											<form:option selected="selected" value="" label="select"></form:option>
											<form:options items="${orgList}" itemValue="orgCode" itemLabel="orgName"></form:options>
											</form:select>
											<div id="err_category" class="errormsg label"></div><br/>
											<label for="tdMinDepartment2"><spring:message htmlEscape="true" code="Label.UnitLevelDEPT"
											       text="Select Organization"></spring:message>
											</label><span class="errormsg label">*</span><br /> 
											<form:select path="" id="tdMinDepartment" style="width: 500px;" onchange="getAdminUnitDepartmentDetailsOrg(this.value);resetList1();" class="combofield">
										    	<form:option value="0">
												<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
												</form:option>
											</form:select>
											<div id="err_tdMinDepartment" class="errormsg label"></div>
										</li>
										<!-- modified by pooja on 19-06-2015 -->
										 <li><label for="adminUnitLevelName"><spring:message htmlEscape="true" 
									           code="Label.UnitLevelOrganization" text="Select Organization level "></spring:message></label>
											   <span class="errormsg label">*</span><br /> <!-- getOrganizationParent1	 --> <!-- viewdepartmentforadmin    -->
											  <form:select path="" id="adminUnitLevelName" style="width: 500px;" onchange="getOrganizationParent1(tdMinDepartment.value,this.value);resetList();" class="combofield">
										       <form:option value="0">
											   <spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
										       </form:option>
									          </form:select>
								 	    <div id="err_adminUnitLevelName" class="errormsg label"></div>
								    	</li>
								</c:if>
								<c:if test="${!isCenterUser}">
										<li id="tdDepartment"><label><spring:message htmlEscape="true" code="label.Organization"
												text="Select  Organization "></spring:message></label><span class="errormsg label">*</span><br /> 
										<form:select
											path="" class="combofield" id="category" style="width: 500px;" onchange="getAdminUnitDepartmentDetailsOrg(this.value);resetList1();">
											<form:option value="">
												<form:option selected="selected" value="" label="select" />
											</form:option>
											<form:options items="${orgList}" itemValue="orgCode" itemLabel="orgName"></form:options>
										</form:select>
										<div id="err_category" class="errormsg label"></div>
										</li>
								
									    <li><label for="adminUnitLevelName"><spring:message htmlEscape="true" 
									           code="Label.UnitLevelOrganization" text="Select Organization level "></spring:message></label>
											   <span class="errormsg label">*</span><br /> <!-- getOrganizationParent1	 --> <!-- viewdepartmentforadmin    -->
											  <form:select path="" id="adminUnitLevelName" style="width: 500px;" onchange="getOrganizationParent1(category.value,this.value);resetList();" class="combofield">
										       <form:option value="0">
											   <spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
										       </form:option>
									          </form:select>
								 	    <div id="err_adminUnitLevelName" class="errormsg label"></div>
								    	</li>
								    	</c:if>
								    	<li><label for="organizationUnit"><spring:message htmlEscape="true" code="label.orgUnits"
											text="Select Org Units"></spring:message></label><span class="errormsg label">*</span><br /> <form:select path=""
										id="organizationUnit" class="combofield" style="width: 500px;" onchange="populateValue(this.value);">
										<form:option value="0">
											<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
										</form:option>
									</form:select>
									<div id="err_organizationUnit" class="errormsg label"></div>
									   </li>
								       <li>
									  <div class="clear"></div>
								      </li>
							</ul> <div class="btnpnl"></div><div class="clear"></div>
						</div>
					</div>
			  </div>

					<div class="frmpnlbg">
						<label><spring:message code="">Organisation / Campus location (either Urban local body or Rural local body)</spring:message></label><font
							                   color="#FF0000">*</font> <br />
						<ul class="listing">
							<li>
								<!-- cntVillTown -->
								 <form:radiobutton path="" id="level" name="level" value="1" class="frmfield"
									onclick="showRlbUlb(this.value);stateList(0);stateUlblist(0);resetList();" onfocus="" onblur="" />
									<label><spring:message code="label.ulb" text="ULB"></spring:message></label>
								<div class="mndt" id="helpId6" style="display: none;">
									<spring:message code="label.btn.ulb" />
								</div> <form:radiobutton path="" id="level" name="level" value="0" class="frmfield"
								                     	 onclick="showRlbUlb(this.value);stateList(0);getDataFromServer();resetList();" onfocus="" onblur="" />
								                     	 <label><spring:message code="label.rlb" text="RLB"></spring:message></label> <br />
								<div class="mndt" id="helpId7" style="display: none;">
									<spring:message code="label.btn.rlb" />
								</div>
								<div id="err_level" class="errormsg label"></div>

							</li>
						</ul>
					</div>

				         	<c:if test="${isCenterUser}">
						    	<ul class="blocklist">
							    	 <li id="Statelabel"><label for="statelabel"><spring:message code="State" text="State"></spring:message>
								  	<font color="#FF0000">*</font></label> <br />
										<div id="errormsgrlb" style="display: none; color: red"></div> 
									<select name="statelabel" id="statelabel"
								            onchange="stateUlblist(0);setSlcZero();getDataFromServer();" class="combofield" onfocus="" onblur="">
									<option value=""><spring:message code="common.select" text="Select" />
									</option>
							        </select>
								    <div id="err_statelabel" class="errormsg label"></div></li>
						       </ul>
					     </c:if>
					<!--  //end of state -->
								<ul class="blocklist">
									<li id="ulbTbl" style="display: none;"><label for="ulbList"><spring:message code="ULB" text="ULB"></spring:message><font
											color="#FF0000">*</font></label> <br />
											 <select name="ulbList" id="ulbList" class="combofield" onfocus="" onblur="">
											<option value="0">
											<spring:message code="common.select" text="Select" />
											</option>
									       </select>
										   <div class="mndt" id="helpId13" style="display: none;"><spring:message code="" />
										  </div>
										  <div id="err_ulbList" class="errormsg label"></div>
									</li>
								</ul>
		
							 <ul class="blocklist">
								<li id="rlbTbl" style="display: none;"><label for="districtListRLB"><spring:message code="District"
											text="District"></spring:message> <font color="#FF0000">*</font></label> <br />
									<div id="err_districtListRLB" class="errormsg label"></div>
									<div id="errormsgrlb" style="display: none; color: red"></div>
									 <select name="districtListRLB"
									         id="districtListRLB" onchange="populateSubDistrict(this.value, 1);" class="combofield"
									         onfocus="clearrlbUlbErrors();" >
									<option value="0">
									<spring:message code="common.select" text="Select" />
									</option>
								   </select>
									<div style="height: 15px; padding-top: 3px;"></div> 
									<label for="subDistrictListRLB"><spring:message
									   	 code="venueForm.subdist" text="SubDistrict" /><font color="#FF0000">*</font>
									</label> <br />
									<div id="err_subDistrictListRLB" class="errormsg label"></div> 
								   <select name="subDistrictListRLB"
								     id="subDistrictListRLB" onchange="populateVillage(this.value, 1);" class="combofield"
								     onfocus="clearrlbUlbErrors();" onblur="">
									<option value="0">
									<spring:message code="common.select" text="Select" />
									</option>
							     </select>
								 <div style="height: 15px; padding-top: 3px;"></div>
								<div class="mndt" id="helpId11" style="display: none;">
								<spring:message code="label.subdistrict" />
								</div> <label for="villageListRLB"><spring:message code="venueForm.vill" text="Village" /><font
										      color="#FF0000">*</font></label> <br />
									   <div id="err_villageListRLB" class="errormsg label"></div>
									        <select name="villageListRLB" id="villageListRLB"
									        class="combofield" onfocus="clearrlbUlbErrors();" onblur="">
										    <option value="0">
											<spring:message code="common.select" text="Select" />
										   </option>
								           </select><br />
									  <div class="mndt" id="helpId12" style="display: none;">
										<spring:message code="label.vlgonly.slct" />
									</div>
								</li>
							</ul>
					<div class="frmpnlbg" id="content2">
						<ul class="blocklist">
							<li id="trAddressName">
								<fieldset class="fldset">
									<legend>
										<label>Organization Unit Details</label>
									</legend>
								</fieldset>
								</li>
								</ul>
									<ul class="blocklist">
									<li id="code" style="display: none;"><label for="orgunitcode"><spring:message code="label.orgunitcode" text="Org Unit Code" /></label>
										<form:input type="hidden" path="orgUnitCode" id="orgunitcode" value=""/>
										<div id="orgUnitCode"  />
										 </li>
										<li><label for="orgunitname"><spring:message code="label.orgunitname " text="Org Unit Name " /><font
												   color="#FF0000">*</font>
										</label><div id="err_orgUnitName" class="errormsg label"></div> 
										<form:input path="orgUnitName" id="orgunitname" class="frmfield" style="width: 500px;" maxlength="70" onfocus=""
												    onblur="" />
												    
									   </li>
										<li><label for="orgspecificcode"><spring:message code="label.orgspecificcode"
													text="Org Specific Code" />
											</label><br />
											 <form:input path="orgSpecCode" id="orgSpecCode" value=""
												         class="frmfield" style="width: 200px;" maxlength="10" onfocus=""  />
										</li>
										<li><label for="emailid"><spring:message code="label.emailid" text="Email Id" /></label>
											  <div id="err_email" class="errormsg label"></div>
											  <form:input path="email" id="email" value=""
												          class="frmfield" style="width: 200px;" maxlength="70" onfocus=""  />
										</li>
										<li><label for="phoneno"><spring:message code="label.phoneno" text="Phone No" /> </label>
											   <div id="err_phoneno" class="errormsg label"></div>
											    <form:input path="phoneNo" id="phoneNo" value=""
												            class="frmfield" style="width: 200px;" maxlength="10" onfocus=""  />
												<form:errors id="phoneNo" path="phoneNo" cssClass="errormsg"></form:errors>
										</li>
										<li><label for="address1"><spring:message code="label.address1" text="Address 1" /><font
												   color="#FF0000">*</font></label>
												   <div id="err_address1" class="errormsg label"></div>
											 <form:textarea path="address1" id="address1" value="" class="frmfield"  style="width: 500px; height: 20px;" onfocus="" />
										     <form:errors id="address1" path="address1" cssClass="errormsg"></form:errors>
											<div id="err_address1" class="errormsg label"></div></li>

										<li><label for="address2"><spring:message code="label.address2" text="Address 2" /> </label> <br /> <form:textarea
												   path="address2" id="address2" value="" class="frmfield"  style="width: 500px; height: 20px;" onfocus="" />
										</li>
										<li><label for="address3"><spring:message code="label.address3" text="Address 3" /></label> <br /> <form:textarea
											 	   path="address3" id="address3" value="" class="frmfield"  style="width: 500px; height: 20px;" onfocus=""  />
										</li>

										<li><label for="pinCode"><spring:message code="label.pincode" text="Pin Code" /></label> <br /> <form:input
												   path="pinCode" id="pinCode" value="" class="frmfield" maxlength="6" onfocus=""  />
											 <form:errors id="pinCode" path="pinCode" cssClass="errormsg"></form:errors>
											<div id="err_pinCode" class="errormsg label"></div>
											</li>
										
									</ul>
								
						<!-- </fieldset> -->
					</div>
					<div id="navigationButtons">
						<div class="btnpnl">
							<input type="button" name="Submit" class="bttn" onclick="return updatesave();" value=<spring:message htmlEscape="true"  code="Save "></spring:message> />
							<input type="button" name="close" value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>" onclick="javascript:window.location.href='home.htm'" />
						</div>
					</div>
		   </div>
				  <c:if test="${! empty msgid}">
					<script>
						  alert('${msgid}');
					</script>
				  </c:if>

			</form:form>
			<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>
</body>
</html>








