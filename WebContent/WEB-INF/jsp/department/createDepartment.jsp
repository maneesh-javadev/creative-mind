<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<%@include file="../common/taglib_includes.jsp"%>
<script src="js/createDepartment.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script src="js/common.js"></script>
<script type="text/javascript" language="javascript">

$(document)
.ready(
		function() {
			$("#createDepartment")
					.validate(
							{
								rules : {
									ministryName : {
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
									deptName : {
										required : "<font color='red'><br><spring:message code='error.fill.DEPTNAME' text='Please enter department name'/></font>",
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

/* 	if(document.getElementById('ministryName').value==0 || document.getElementById('ministryName').value==""){
		error=1;
		itr=1;
		alert("Kindly select the Ministry from the list.");
	} else {
		error=0;
		itr=0;
	}
	if(itr==0 && document.getElementById('deptName').value==""){
		error=1;
		itr=1;
		alert("Kindly fill the Department name.");
	} else {
		error=0;
		itr=0;
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
	var bool = $("#createDepartment").validate().form();
	//alert('bool-->'+bool+", error-->"+error);
	if (error==0 && bool == true) {		//alert('submit..');
		document.forms['createDepartment'].submit();
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
<body>
	<div id="frmcontent">
		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message code="Label.CREATEDEPT" htmlEscape="true"></spring:message></td>
					<td align="right"><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /> </a></td>
					<%-- //this link is not working<td width="50" align="right" valign="middle"><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></td> --%>
					<td width="50" align="right"><a href="#" class="frmhelp">Help</a></td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="createDepartment.htm" method="POST" commandName="createDepartment" id="createDepartment" name="createDepartment">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="createDepartment.htm"/>" />
				<form:hidden path ="orgLevel" value="C"/>
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
													<form:select path="ministryName" style="width: 200px" id="ministryName" class="combofield">
															<form:option value="">
																<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
															</form:option>
															<form:options items="${ministryList}" itemLabel="orgName" itemValue="orgCode" />
													</form:select>
													</td>
													<td width="56%"><form:errors htmlEscape="true" path="ministryName" cssClass="errormsg" ></form:errors></td>
											</tr>
										</table>
									</div>
									</div>
								</td>

							</tr>
							<tr>
								<td width="100%"></br>
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message code="Label.GENERALDETAILS" htmlEscape="true"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message code="Label.DEPTNAMEINENG" htmlEscape="true"></spring:message> <span class="errormsg">*</span> <br />
												<form:input path="deptName" id="deptName" style="width: 200px" class="frmfield" htmlEscape="true" maxlength="200"></form:input>
												</td>
												<td width="56%"><form:errors htmlEscape="true" path="deptName" cssClass="errormsg"></form:errors></td>
											</tr>
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message code="Label.DEPTNAMEINLOCAL" htmlEscape="true"></spring:message> <br />
												<form:input path="deptNameLocal" style="width: 200px" class="frmfield" htmlEscape="true" maxlength="80"></form:input></td>
												<td width="56%"><form:errors htmlEscape="true" path="deptNameLocal" cssClass="errormsg"></form:errors></td>
											</tr>
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message code="Label.SHORTDEPTNAME" htmlEscape="true"></spring:message> <br />
												<form:input path="shortDeptName" style="width: 200px" class="frmfield" htmlEscape="true" maxlength="10"></form:input></td>
												<td width="56%"></td>
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
												<td><spring:message code="Label.SPECIFYLOCDEPT" htmlEscape="true"></spring:message><span class="errormsg">*</span> </br>
												</br></td>
											</tr>
											<tr>
												<td>
													<form:checkbox name="checkbox" value="S" path="specificLevel" id="stateL" onclick="isNext(this.id)" /> <spring:message code="Label.STATELEVEL" htmlEscape="true"></spring:message> &nbsp;&nbsp;
													<form:checkbox name="checkbox" value="D" path="specificLevel" id="districtL" /> <spring:message code="Label.DISTRICTTRADNME" htmlEscape="true"></spring:message> &nbsp;&nbsp;
													<form:checkbox name="checkbox" value="T" path="specificLevel" id="subDistrictL" /> <spring:message code="Label.SUBDISTRICTLVL" htmlEscape="true"></spring:message> &nbsp;&nbsp;
													<form:checkbox name="checkbox" value="V" path="specificLevel" id="villageL" /> <spring:message code="Label.VILLAGELVL" htmlEscape="true"></spring:message>
												</td>
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
								<td width="84%" align="left">
								<label> <input type="button" name="Save" class="button" id="btnSave" onclick="formSubmit();" value="<spring:message code="Button.SAVE"></spring:message>" /></label>
								<label><input type="button" class="button" name="Clear" value="<spring:message code="Button.CLEAR"></spring:message>" id="btnClear" onclick="javascript:location.href='createDepartmentCentral.htm?<csrf:token uri='createDepartmentCentral.htm'/>';" /></label>
								<label><input type="button" class="button" name="Cancel" value="<spring:message code="Button.CLOSE"></spring:message>" id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /></label>
								
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