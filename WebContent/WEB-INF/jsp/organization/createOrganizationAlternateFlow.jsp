<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%@include file="../common/taglib_includes.jsp"%>
<head>
<script src="js/createDepartment.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script type="text/javascript" language="Javascript">
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script src="js/common.js"></script>
<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
</script>
<script type="text/javascript" language="javascript">
function formSubmit(){
	var error=0;
	var itr=0;
	var districtL=document.getElementById('districtL');
	var subDistrictL=document.getElementById('subDistrictL');
	var villageL=document.getElementById('villageL');

	if(itr==0 && document.getElementById('deptOrgCode').value==0 || document.getElementById('deptOrgCode').value==""||document.getElementById('deptOrgCode').value=="Select"){
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
	}
	
	if (error==0){
		document.forms['createOrganization'].submit();
	}
}
function isNext(id){
	document.getElementById('btnSave').value="Save";
	if (document.getElementById('districtL').checked||document.getElementById('subDistrictL').checked||document.getElementById('blockL').checked||document.getElementById('villageL').checked) {
		document.getElementById('btnSave').value="Next";
	} 
}
</script>
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd" >


			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message htmlEscape="true"  code="Label.CREATEORG"></spring:message>
					</td>
					<td align="right"><a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a></td>
				<%--//this link is not working  	<td width="50" align="right" valign="middle"><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></td> --%>
					<td width="50" align="right"><a href="#" class="frmhelp">Help</a>
					</td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="createOrganization.htm" method="POST" commandName="createOrganization">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="createOrganization.htm"/>"/>
				<div id="cat">
					<div class="frmpnlbg">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td width="100%">
									<div class="frmtxt">
									<div class="frmhdtitle">
											<spring:message htmlEscape="true"  code="Label.SELSTATELVLDEPT"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="14%">
													<td width="30%"><spring:message htmlEscape="true"  code="Label.DEPTLIST"></spring:message>
														<span class="errormsg">*</span> <br /> <form:select
															path="deptOrgCode" style="width: 200px" id="deptOrgCode"
															class="combofield">
															<form:option value="0">
																<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
															</form:option>
															<form:options items="${stateDeptList}" itemLabel="orgName"
																itemValue="orgCode" />
														</form:select>
												</td>
													<td width="56%"><form:errors htmlEscape="true"  path="ministryName"
															cssClass="errormsg"></form:errors></td>
											</tr>
										</table>
									</div>
									</div>
								</td>

							</tr>
							<tr>
								<td width="100%">
								</br>
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message htmlEscape="true"  code="Label.GENERALDETAILS"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
										<tr>
												<td width="14%">
													<td width="30%"><spring:message htmlEscape="true"  code="Label.ORGTYPELIST"></spring:message>
														<span class="errormsg">*</span> <br /> <form:select
															path="orgType" style="width: 200px" id="orgType"
															class="combofield">
															<form:option value="0">
																<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
															</form:option>
															<form:options items="${orgType}" itemLabel="orgType"
																itemValue="orgTypeCode" />
														</form:select></br></br>
												</td>
											</tr>
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message htmlEscape="true"  code="Label.ORGNAMEINENG"></spring:message>
													<span class="errormsg">*</span> <br /> <form:input
														path="deptName" id="deptName" maxlength="200" style="width: 200px" class="frmfield"></form:input>
												<br/><br/></td>
												<td width="56%"><form:errors htmlEscape="true"  path="deptName"
														cssClass="errormsg"></form:errors></td>
											</tr>
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message htmlEscape="true"  code="Label.ORGNAMEINLOCAL"></spring:message>
													<br /> <form:input path="deptNameLocal" maxlength="80" style="width: 200px"
														class="frmfield"></form:input>
												<br/><br/></td>
												<td width="56%"></td>
											</tr>
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message htmlEscape="true" 
														code="Label.ORGSHORTNAMEINENG"></spring:message> <br /> <form:input
														path="shortDeptName" maxlength="10" style="width: 200px" class="frmfield"></form:input>
												<br/><br/></td>
												<td width="56%"></td>
											</tr>
											
										</table>
									</div></td>
							</tr>

							<tr>
								<td width="100%">
								<br/>
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message htmlEscape="true"  code="Label.SPECIFYLOCATION"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td><spring:message htmlEscape="true" 
														code="Label.SPECIFYBRANCHLOC"></spring:message> <br/><br/></td>
											</tr>
													<tr> 
														<td>
														<form:checkbox  name="checkbox" value="D" path="specificLevel" id="districtL" onclick="isNext(this.id)"/>
															<spring:message htmlEscape="true"  code="Label.DISTRICTTRADNME"></spring:message>
														&nbsp;&nbsp;
														<form:checkbox  name="checkbox" value="T" path="specificLevel" id="subDistrictL" onclick="isNext(this.id)"/>
															<spring:message htmlEscape="true"  code="Label.SUBDISTRICTLVL"></spring:message>
														&nbsp;&nbsp;
														<form:checkbox  name="checkbox" value="B" path="specificLevel" id="blockL" onclick="isNext(this.id)"/>
															<spring:message htmlEscape="true"  code="Label.BLOCKLEVEL"></spring:message>
														&nbsp;&nbsp;
														<form:checkbox  name="checkbox" value="V" path="specificLevel" id="villageL" onclick="isNext(this.id)"/>
															<spring:message htmlEscape="true"  code="Label.VILLAGELVL"></spring:message>
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
								<td width="84%" align="left"><label> <input
										type="button" name="Save" id="btnSave" onclick="formSubmit();"
										value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" /> </label><label>
										<input type="button" class="btn" name="Cancel"
										value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
										id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
					</div>
					<br /> <br /> <br />
					</td>

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