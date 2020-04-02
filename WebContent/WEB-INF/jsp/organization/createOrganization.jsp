<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<%@include file="../common/taglib_includes.jsp"%>
<script src="js/createDepartment.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script src="js/common.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type="text/javascript" language="javascript">
$(document)
.ready(
		function() {
			$("#createOrganization")
					.validate(
							{
								rules : {
									ministryName : {
										required : true
									},
									deptOrgCode : {
										required : true
									},
									orgType : {
										required : true
									},
									deptName : {
										required : true,
										onlyLetterSpace : true
									},
									deptNameLocal : {
										nameFormatLocal : true
									},
									shortDeptName: {
										shortName : true
									}
								},
								messages : {
									ministryName : {
										required : "<font color='red'><br><spring:message code='error.select.MINISTRYFRMLIST' text='Please select ministry name'/></font>"
									},
									deptOrgCode : {
										required : "<font color='red'><br><spring:message code='error.select.DEPTFRMLIST' text='Please select department'/></font>"
									},
									orgType : {
										required : "<font color='red'><br><spring:message code='error.select.ORGTYPEFRMLIST' text='Please select organization type'/></font>"
									},
									deptName : {
										required : "<font color='red'><br><spring:message code='error.fill.ORGNAME' text='Please enter organization name'/></font>",
										onlyLetterSpace : "<font color='red'><br><spring:message code='error.invalid.format' text='Please enter value in correct format'/></font>"
									},
									deptNameLocal : {
										nameFormatLocal : "<font color='red'><br><spring:message code='error.invalid.format' text='Please enter value in correct format'/></font>"
									},
									shortDeptName : {
										shortName : "<font color='red'><br><spring:message code='error.common.short.name' text='Short Name is invalid'/></font>"
									}
								}
							});
		});
		
function formSubmit(){
	var error=0;
	var itr=0;
	var stateL=document.getElementById('stateL');
	var districtL=document.getElementById('districtL');
	var subDistrictL=document.getElementById('subDistrictL');
	var villageL=document.getElementById('villageL');

	/* if(document.getElementById('ministryName').value==0 || document.getElementById('ministryName').value==""){
		error=1;
		itr=1;
		alert("Kindly select the Ministry from the list.");
	}
	if(itr==0 && (document.getElementById('deptOrgCode').value==0 || document.getElementById('deptOrgCode').value==""||document.getElementById('deptOrgCode').value=="Select")){
		error=1;
		itr=1;
		alert("Kindly select the Department from the list.");
	}
	if(itr==0 && document.getElementById('orgType').value==0 || document.getElementById('orgType').value==""){
		error=1;
		itr=1;
		alert("Kindly select the Organization Type from the list.");
	}
	if(itr==0 && document.getElementById('deptName').value==""){
		error=1;
		itr=1;
		alert("Kindly fill the Organization name.");
	} */

	var obj = document.getElementsByName("specificLevel");
	var flag = false;
	for(var i =0; i < obj.length; i++) {
		if(obj[i].checked) {
			flag = false;
			break;
		} else {
			flag = true;
		}
	}
	if(flag) {
		alert("Kindly select at least one level.");
		error =1;
	}
	
	if(itr==0 && districtL.checked && !subDistrictL.checked && !villageL.checked){
		if(!stateL.checked){
			error=1;
			itr=1;
			alert("Kindly select the State level as well.");
		}
	}
	
	if(subDistrictL.checked && !villageL.checked){
		if(!stateL.checked && !districtL.checked){
			error=1;
			itr=1;
			alert("Kindly select the State and District level as well.");
		}
		if(itr==0 &&!districtL.checked){
			error=1;
			itr=1;
			alert("Kindly select the District level as well.");
		}
		if(itr==0 &&!stateL.checked){
			error=1;
			itr=1;
			alert("Kindly select the State level as well.");
		}
		itr=0;
	}
	if(villageL.checked){
		if(!stateL.checked && !districtL.checked && !subDistrictL.checked){
			error=1;
			itr=1;
			alert("Kindly select the State, District and Sub District level as well.");
		}
		if(itr==0 && !stateL.checked && !districtL.checked){
			error=1;
			itr=1;
			alert("Kindly select the State and District level as well.");
		}
		if(itr==0 &&!subDistrictL.checked){
			error=1;
			itr=1;
			alert("Kindly select the Sub District level as well.");
		}
		if(itr==0 &&!districtL.checked){
			error=1;
			itr=1;
			alert("Kindly select the District level as well.");
		}
		if(itr==0 &&!stateL.checked){
			error=1;
			itr=1;
			alert("Kindly select the State level as well.");
		}
	}
	
	var bool = $("#createOrganization").validate().form();
	if (error==0 && bool){
		document.forms['createOrganization'].submit();
	}
}
	
function isNext(id){
	document.getElementById('btnSave').value="Save";
	if (document.getElementById(id).checked) {
		document.getElementById('btnSave').value="Next";
	} 
}
</script>
</head>
<body onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">

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
									<c:out value="${family_msg}" escapeXml="true"></c:out>
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
								<c:out value="${family_error}" escapeXml="true"></c:out>
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
					<td><div class="errorfont"><spring:message htmlEscape="true" code="Error.entermandatoryfield"></spring:message></div></td>
				</tr>
			</table>

		</div>
	</div>
	<div id="frmcontent">
		<div class="frmhd">


			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message code="Label.CREATEORG" htmlEscape="true"></spring:message></td>
					<td align="right"><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /> </a></td>

					<td width="50" align="right"><a href="#" class="frmhelp">Help</a></td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="createOrganization.htm" method="POST" commandName="createOrganization" id="createOrganization" name="createOrganization">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="createOrganization.htm"/>" />
				<div id="cat">
					<div class="frmpnlbg">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td width="100%">
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message code="Label.SELMIN" htmlEscape="true"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="14%">
													<td width="30%"><spring:message code="Label.MINISTRIES" htmlEscape="true"></spring:message> <span class="errormsg">*</span> <br />
													<form:select path="ministryName" style="width: 200px" id="ministryName" class="combofield" onchange="getDepartmentList1(this.value)" onblur="vlidateOnblur('ministryName','1','15','c');">
															<form:option value="">
																<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
															</form:option>
															<form:options items="${ministryList}" itemLabel="orgName" itemValue="orgCode" />
														</form:select></td>
													<td width="56%"><form:errors htmlEscape="true" path="ministryName" cssClass="errormsg"></form:errors></td>
											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td width="14%">
													<td width="30%"><spring:message code="Label.DEPTLIST" htmlEscape="true"></spring:message> <span class="errormsg">*</span> <br /> <form:select path="deptOrgCode" style="width: 200px" id="deptOrgCode" class="combofield"
															onblur="vlidateOnblur('deptOrgCode','1','15','c');">
															<form:option value="">
																<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
															</form:option>
														</form:select></td>
													<td width="56%"><form:errors htmlEscape="true" path="deptOrgCode" cssClass="errormsg"></form:errors></td>
											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td width="14%">
													<td width="30%"><spring:message code="Label.ORGTYPELIST" htmlEscape="true"></spring:message> <span class="errormsg">*</span> <br /> <form:select path="orgType" style="width: 200px" id="orgType" class="combofield"
															onblur="vlidateOnblur('orgType','1','15','c');">
															<form:option value="">
																<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
															</form:option>
															<form:options items="${orgType}" itemLabel="orgType" itemValue="orgTypeCode" />
														</form:select></td>
													<td width="56%"><form:errors htmlEscape="true" path="orgType" cssClass="errormsg"></form:errors></td>
											</tr>
										</table>
									</div>
									</div>
								</td>

							</tr>
							<tr>
								<td width="100%"><br/>
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message code="Label.GENERALDETAILS" htmlEscape="true"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message code="Label.ORGNAMEINENG" htmlEscape="true"></spring:message> <span class="errormsg">*</span> <br />
												<form:input path="deptName" id="deptName" style="width: 200px" class="frmfield" onblur="('deptName','1','15','c');" htmlEscape="true" maxlength="200"></form:input></td>
												<td width="56%"><form:errors htmlEscape="true" path="deptName" cssClass="errormsg"></form:errors></td>
											</tr>
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message code="Label.ORGNAMEINLOCAL" htmlEscape="true"></spring:message> <br />
												<form:input path="deptNameLocal" style="width: 200px" class="frmfield" htmlEscape="true" maxlength="80"></form:input></td>
												<td width="56%"><form:errors htmlEscape="true" path="deptNameLocal" cssClass="errormsg"></form:errors></td>
											</tr>
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message code="Label.ORGSHORTNAMEINENG" htmlEscape="true"></spring:message> <br />
												<form:input path="shortDeptName" style="width: 200px" class="frmfield" htmlEscape="true" maxlength="10"></form:input></td>
												<td width="56%"><form:errors htmlEscape="true" path="shortDeptName" cssClass="errormsg"></form:errors></td>
											</tr>

										</table>
									</div></td>
							</tr>

							<tr>
								<td width="100%"></br>
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message code="Label.SPECIFYLOCATION" htmlEscape="true"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td><spring:message code="Label.SPECIFYLOCORG" htmlEscape="true"></spring:message> <br/>
												<br/></td>
											</tr>
											<tr>
												<td><form:checkbox name="checkbox" value="S" path="specificLevel" id="stateL" onclick="isNext(this.id)" /> <spring:message code="Label.STATELEVEL" htmlEscape="true"></spring:message> &nbsp;&nbsp; <form:checkbox name="checkbox"
														value="D" path="specificLevel" id="districtL" /> <spring:message code="Label.DISTRICTTRADNME" htmlEscape="true"></spring:message> &nbsp;&nbsp; <form:checkbox name="checkbox" value="T" path="specificLevel" id="subDistrictL" /> <spring:message
														code="Label.SUBDISTRICTLVL" htmlEscape="true"></spring:message> &nbsp;&nbsp; <form:checkbox name="checkbox" value="V" path="specificLevel" id="villageL" /> <spring:message code="Label.VILLAGELVL" htmlEscape="true"></spring:message></td>
											</tr>
										</table>
									</div></td>
							</tr>
						</table>


					</div>
					<div class="btnpnl">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td width="16%" rowspan="2">&nbsp;</td>
								<td width="84%" align="left"><label> <input type="button" name="Save" id="btnSave" onclick="formSubmit();" value="<spring:message code="Button.SAVE"></spring:message>" />
								</label><label> <input type="button" class="btn" name="Cancel" value="<spring:message code="Button.CLOSE"></spring:message>" id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
								</label>
									</div> <br /> <br /> <br /></td>

							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
						</table>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	</td>

</body>
</html>