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
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script src="js/common.js"></script>
<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
</script>
<script type="text/javascript" language="javascript">

 $(document)
.ready(
		function() {
			$("#createDepartmentforState")
					.validate(
							{
								rules :{ deptName : {
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
	var districtL=document.getElementById('districtL');
	var subDistrictL=document.getElementById('subDistrictL');
	var villageL=document.getElementById('villageL');
	var deptName=document.getElementById('deptName').value;
	
	
	
	var obj = document.getElementsByName("specificLevel");
	var flag = false;
	/* for(var i =0; i < obj.length; i++) {
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
 */
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
	
	
	var bool = $("#createDepartmentforState").validate().form();
	//alert(error+":"+bool);
	if (error==0 && bool == true) 
		{
		return true;
		}
	else
		{
		return false;
		}
		
	/* var flag=true;
		document.getElementById('errorshortDeptName').innerHTML="";
		document.getElementById('errordeptName').innerHTML="";
		document.getElementById('errordeptNameLocal').innerHTML="";
	if(itr==0 && deptName==""){
		
		document.getElementById('errordeptName').innerHTML="Kindly fill the Department name.";
		return false;
	}
	else
	{
		if(!alphabetValidate(deptName))
			{
			document.getElementById('errordeptName').innerHTML="Please use [A-Z],[a-z] and space in Department name.";
			flag=false;
			}
	}
	
	if(document.getElementById('deptNameLocal').value!="")
		{
		if(!alphabetValidate(document.getElementById('deptNameLocal').value))
			{
			document.getElementById('errordeptNameLocal').innerHTML="Please use [A-Z],[a-z] and space in Department name Local.";
			flag=false;
			}
		
		}
	
	if(document.getElementById('shortDeptName').value!="")
	{
	if(!alphabetValidate(document.getElementById('shortDeptName').value))
		{
		document.getElementById('errorshortDeptName').innerHTML="Please use [A-Z],[a-z] and space in Sort Department name.";
		flag=false;
		}
	
	} */
	
	/* if(subDistrictL.checked && !villageL.checked){
		if(!districtL.checked){
			error=1;
			itr=1;
			alert("Kindly select the District level as well.");
		}
		itr=0;
	}
	if(villageL.checked){
		if(!districtL.checked && !subDistrictL.checked){
			error=1;
			itr=1;
			alert("Kindly select the District and Sub District level as well.");
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
	} */
	/* if (error==0){
		document.forms['createDepartment'].submit();
	} */
}
function isNext(id){
	document.getElementById('btnSave').value="Save";
	if (document.getElementById('districtL').checked||document.getElementById('subDistrictL').checked||document.getElementById('blockL').checked||document.getElementById('villageL').checked) {
		document.getElementById('btnSave').value="Next";
	}
}
function getLocalBodyList(localBodyTypeCode, stateCode) {
	lgdDwrlocalBodyService.getLocalBodyList(localBodyTypeCode, stateCode, {
			callback : handleLocalBodySuccess,
			errorHandler : handleLocalBodyError
		});
}
function handleLocalBodySuccess(data) {
	var fieldId = 'localBodyCode';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}
function handleLocalBodyError() {
		alert("No data found!");
}
function formLoad(){
	
	document.getElementById('lbSpecific').style.display = 'none';
	document.getElementById('lbSpecific').style.visibility = 'hidden';
}
function isLBSpecific(chkRd,chkRd1,idname,idname2){
	
	
	var chkRd = document.getElementById(chkRd);
	var chkRd1 = document.getElementById(chkRd1);

	if (chkRd.checked) {
		document.getElementById(idname).style.display='none';
		document.getElementById(idname).style.visibility = 'hidden';

	} else if (chkRd1.checked) {
		
		document.getElementById(idname).style.display = 'block';
		document.getElementById(idname).style.visibility = 'visible';
		document.getElementById(idname2).style.display='none';
		document.getElementById(idname2).style.visibility = 'hidden'; 
		
	}
	

	
}
function isLB(chkRd,chkRd1,idname,idname2){
	var chkRd = document.getElementById(chkRd);
	var chkRd1 = document.getElementById(chkRd1);

	if (chkRd.checked) {
		document.getElementById(idname2).style.display='none';
		document.getElementById(idname2).style.visibility = 'hidden';

	} else if (chkRd1.checked) {
		
		document.getElementById(idname2).style.display = 'block';
		document.getElementById(idname2).style.visibility = 'visible';
	
		
	}
	
}


if ( window.addEventListener ) { 
	     window.addEventListener( "load",formLoad, false );
	  }
	  else 
	     if ( window.attachEvent ) { 
	        window.attachEvent( "onload", formLoad );
	  } else 
	        if ( window.onLoad ) {
	           window.onload = formLoad;
	  }
</script>
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd" >
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message htmlEscape="true"  code="Label.CREATEDEPT"></spring:message>
					</td>
					<td align="right"><a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a></td>
				<%-- //this link is not working <td width="50" align="right" valign="middle"><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></td> --%>
					<td width="50" align="right"><a href="#" class="frmhelp">Help</a>
					</td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="createDepartment.htm" method="POST" commandName="createDepartment" id="createDepartmentforState" name="createDepartmentforState">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="createDepartment.htm"/>"/>
				<div id="cat">
					<div class="frmpnlbg">
						<table width="100%" class="tbl_no_brdr">
					<%-- 		<tr>
								<td width="100%">
									<div class="frmtxt">
									<div class="frmhdtitle">
											<spring:message htmlEscape="true"  code="Label.SELDEPT"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
											<td>&nbsp;&nbsp;</td>
												<td width="30" class="tblclear">
													<label>
													<input name="isLocalBodyTypeSpecifc" type="radio"  id="isLineDepartment"   value="N" onclick="isLBSpecific('isLineDepartment','isLocalBodySpecifc','lbSpecific','localBody')" checked="checked"/>
													</label>
												</td>
												<td width="150" class="tblclear">
													<spring:message htmlEscape="true"  code="Label.LINEDEPT"></spring:message>
												</td>
												<td width="30" class="tblclear">
													<label> 
													<input name="isLocalBodyTypeSpecifc" type="radio" id="isLocalBodySpecifc"  value="Y" onclick="isLBSpecific('isLineDepartment','isLocalBodySpecifc','lbSpecific','localBody')"/> 
													</label>
												</td>
												<td class="tblclear">
													<spring:message htmlEscape="true"  code="Label.LBDEPT"></spring:message>
												</td>
											</tr>
										</table>
									</div>
									</div>
								</td>
							</tr> --%>
							<tr>
								<td width="100%">
								<br/>
									<div class="frmtxt" id="lbSpecific">
									<div class="frmhdtitle">
											<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
										<tr>
											<td width=160>&nbsp;</td>
												<td width="20" class="tblclear">
													<label>
													<input name="isLocalBodySpecifc" id="LocalBodyType" type="radio" value="N" onclick="isLB('LocalBodyType','LocalBody','lbSpecific','localBody')" checked="checked"/> 
													</label>
												</td>
												
												<td width="150" class="tblclear">
												<div>
													<spring:message htmlEscape="true"  code="Label.LBTYPESPECIFC"></spring:message>
													</div>
												</td>
												
												<td width="30" class="tblclear">
												<div>
													<label> 
													<input name="isLocalBodySpecifc" type="radio" id="LocalBody" value="Y" onclick="isLB('isLineDepartment','isLocalBodySpecifc','lbSpecific','localBody')"/> 
													</label>
													</div>
												</td>
												<td class="tblclear">
												<div>
													<spring:message htmlEscape="true"  code="Label.LBSPECIFC"></spring:message>
													</div>
												</td>
											</tr>
											<tr><td>&nbsp;</td></tr>
										<tr>
										<td width="160">&nbsp;</td>
											<td width="30%">
											<input type="hidden" name="stateCode" value="<c:out value='${stateCode}' escapeXml='true'></c:out>"/>
											<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message><br />
													<form:select path="localBodyTypeCode" style="width: 200px" id="localBodyTypeCode" htmlEscape="true"
															class="combofield" onchange="getLocalBodyList(this.value, stateCode.value)">
															<form:option value="0">
																<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
															</form:option>
															<form:options items="${localBodyType}" itemLabel="localBodyTypeName"
																itemValue="localBodyTypeCode" />
														</form:select>
												</br></br></td>
												<td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td>
											</tr>
											
											<tr id="localBody">
											<td width=160>&nbsp;</td>
											<td width="30%"><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message><br />
													<form:select path="localBodyCode" style="width: 200px" id="localBodyCode"
															class="combofield">
															<form:option value="0">
																<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
															</form:option>
														</form:select>
												</td>
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
												<td width="14%"></td>
												<td width="30%"><spring:message htmlEscape="true"  code="Label.DEPTNAME"></spring:message>
													<span class="errormsg">*</span> <br /> <form:input
														path="deptName" id="deptName" maxlength="200" style="width: 200px" class="frmfield"></form:input>
												</td>
												<td width="56%"><div id="errordeptName" class="errormsg"></div> <form:errors htmlEscape="true"  path="deptName"
														cssClass="errormsg"></form:errors></td>
											</tr>
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message htmlEscape="true"  code="Label.DEPTNAMELOCAL"></spring:message>
													<br /> <form:input path="deptNameLocal" id="deptNameLocal" maxlength="80" style="width: 200px"
														class="frmfield"></form:input>
												</td>
												<td width="56%"><div id="errordeptNameLocal" class="errormsg"></div><form:errors htmlEscape="true"  path="deptNameLocal"
														cssClass="errormsg"></form:errors></td>
											</tr>
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message htmlEscape="true" 
														code="Label.SHORTDEPTNAME"></spring:message> <br /> <form:input
														path="shortDeptName" id="shortDeptName" maxlength="10" style="width: 200px" class="frmfield"></form:input>
												</td>
												<td width="56%"><div id="errorshortDeptName" class="errormsg"></div><form:errors htmlEscape="true"  path="shortDeptName"
														cssClass="errormsg"></form:errors></td>
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
														<td><form:errors htmlEscape="true"  path="specificLevel"
														cssClass="errormsg"></form:errors></td>
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
										type="submit" name="Save" class="button" id="btnSave" onclick="return formSubmit();"
										value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" /> </label>
										<label> <input type="button" class="button"
													name="CLEAR"
													value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>"
													id="btnCancel" onclick="javascript:location.href='createDepartmentState.htm?<csrf:token uri='createDepartmentState.htm'/>';" /> </label>
										
										<label>
										<input type="button" class="button" name="Cancel"
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