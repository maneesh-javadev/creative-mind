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
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script src="js/common.js"></script>
<script type="text/javascript" language="javascript">
if ( window.addEventListener ) { 
    window.addEventListener( "load",onLoadSelect, false );
 }
 else 
    if ( window.attachEvent ) { 
       window.attachEvent( "onload", onLoadSelect );
 } else 
       if ( window.onLoad ) {
          window.onload = onLoadSelect;
 }

function onLoadSelect(){
	var mypath = window.location.href;
	var mySplitResult = mypath.split("=")[1].split(",");
	if (mySplitResult[1] != null) {
		document.getElementById('btnSave').value="Next";
	} 
	toggle2();
}
function formSubmit(){
	var isError=validation();
	if(isError==0){
		document.forms['createDepartment'].submit();
	}
}
function validation(){
	var error=0;
	var itr=0;
	var selObj2=document.getElementById('ddTargetDistrict');
	var selObj3=document.getElementById('ddTargetDistrictSDLvl');
	
	document.getElementById('errorparent').innerHTML="";
	document.getElementById('errorshortDeptName').innerHTML="";
	document.getElementById('errordeptName').innerHTML="";
	document.getElementById('errordeptNameLocal').innerHTML="";
	document.getElementById('errorDistrictChkFull').innerHTML="";
	var flag=true;
	if(itr==0 && document.getElementById('parent').value=="" || document.getElementById('parent').value=="Select"){
		error=1;
		itr=1;
		document.getElementById('errorparent').innerHTML="Kindly select the Parent from the list.";
		return false;
	}
	if(itr==0 && document.getElementById('deptName').value==""){
		error=1;
		itr=1;
		document.getElementById('errordeptName').innerHTML="Kindly fill the Sub District level unit name.";
		return false;
	}
	else
		{
			if(!alphabetValidate(document.getElementById('deptName').value))
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
	
	}
	
	if(itr==0 && document.getElementById('rdSpecificDistrictSub').checked){
		document.getElementById('errorddTargetDistrict').innerHTML="";
		if(!document.getElementById('DistrictChkFull').checked){
			//alert("Kindly select the specific detail.");
			document.getElementById('errorDistrictChkFull').innerHTML="Kindly select the specific detail.";
			flag=false;
		}
		if(document.getElementById('DistrictChkFull').checked){
			for (var i = 0; i < selObj2.options.length; i++) {
			     selObj2.options[i].selected=true;
			}
			if(document.getElementById('ddTargetDistrict').value==""){
				itr=1;
				//alert("Kindly select District from the list.");
				document.getElementById('errorddTargetDistrict').innerHTML="Kindly select District from the list.";
				flag=false;
			}
		}
		if(document.getElementById('DistrictChkPart').checked){
			document.getElementById('errorddTargetDistrictSDLvl').innerHTML="";
			for (var i = 0; i < selObj3.options.length; i++) {
			     selObj3.options[i].selected=true;
			}
			if(document.getElementById('ddTargetDistrictSDLvl').value==""){
				flag=false;
				itr=1;
				//alert("Kindly select Sub District from the list.");
					document.getElementById('errorddTargetDistrictSDLvl').innerHTML="Kindly select Sub District from the list.";
				
			}
		}
	}
	return flag;
}
function toggle1()
{
	var rdAllDistrictSub =document.getElementById('rdAllDistrictSub');
	var rdSpecificDistrictSub =document.getElementById('rdSpecificDistrictSub');
	
	if(rdAllDistrictSub.checked)
		{
			document.getElementById('divSpecificDistSub').style.display='none';
			document.getElementById('divspecificSubDistrict').style.display='none';
		}
		if(rdSpecificDistrictSub.checked)
		{
			document.getElementById('divSpecificDistSub').style.display='block';
			document.getElementById('StateChkFull').checked=false;
			document.getElementById('DistrictChkFull').checked=false;
			document.getElementById('DistrictChkPart').checked=false;
			document.getElementById('TblSFull').style.display='none';
			document.getElementById('TblDFull').style.display='none';
			document.getElementById('divspecificSubDistrict').style.display='none';
		}
	else
		{
			document.getElementById('divSpecificDistSub').style.display='none';
		}
}
function toggle2(){
	var obj1=document.getElementById('DistrictChkFull');
	var obj2=document.getElementById('DistrictChkPart');
	
	document.getElementById('TblDFull').style.display='block';
	document.getElementById('divspecificSubDistrict').style.display='none';
	if(obj1.checked){
		getdistrictListAtStatelvlListBox(document.getElementById('stateName')[document.getElementById('stateName').selectedIndex].value);
	}
	if(!obj1.checked){
		document.getElementById('TblDFull').style.display='none';
		obj2.checked=false;
		document.getElementById('divspecificSubDistrict').style.display='none';
	}
}
function toggle3(){
	var obj1=document.getElementById('DistrictChkPart');
	var selObj1=document.getElementById('ddTargetDistrict');
	
	if(obj1.checked){
		if(selObj1.value>0){
		getNotInDistrictList(document.getElementById('stateNameDistforSD')[document.getElementById('stateNameDistforSD').selectedIndex].value);
		document.getElementById('divspecificSubDistrict').style.display='block';
		}
		else{
			alert("Kindly select the districts from the list first, afterwords click on this option.");
			obj1.checked=false;
		}
	}
	if(!obj1.checked){
		document.getElementById('divspecificSubDistrict').style.display='none';
	}
}
</script>
</head>
<body >
	<div id="frmcontent">
		<div class="frmhd" >


			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message htmlEscape="true"  code="Label.CREATEDEPTSUBDISTRICTLEVEL"></spring:message>
					</td>
					<td align="right"><a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a></td>
					<td width="50" align="right"><a href="#" class="frmhelp">Help</a>
					</td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="createDepartmentSubdistrictLevel.htm?level=${level}" method="POST" commandName="createDepartment">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="createDepartmentSubdistrictLevel.htm"/>"/>
				<div id="cat">
					<div class="frmpnlbg">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td width="100%">

									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message htmlEscape="true"  code="Label.GENERALDETAILS"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="14%"></td>
												<td width="30%">
												<spring:message htmlEscape="true"  code="Label.SELPARENT"></spring:message>
												<span class="errormsg">*</span><br /> 
												<form:select path="parent" class="combofield" id="parent">
												<form:option value="">
																<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
												</form:option>
												<form:options items="${parent}"
															itemValue="deptName"
															itemLabel="deptName"></form:options>
												</form:select>
												</td>
												<td width="56%"><div id="errorparent" class="errormsg"></div><form:errors htmlEscape="true"  path="parent"
														cssClass="errormsg"></form:errors></td>
											</tr>
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message htmlEscape="true"  code="Label.SUBDISTDEPTNAMEENG"></spring:message>
													<span class="errormsg">*</span> <br /> <form:input
														path="deptName" id="deptName" maxlength="200" style="width: 200px" class="frmfield"></form:input>
												</td>
												<td width="56%"><div id="errordeptName" class="errormsg"></div><form:errors htmlEscape="true"  path="deptName"
														cssClass="errormsg"></form:errors></td>
											</tr>
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message htmlEscape="true"  code="Label.SUBDISTDEPNAMELC"></spring:message>
													<br /> <form:input path="deptNameLocal" id="deptNameLocal" maxlength="80" style="width: 200px"
														class="frmfield"></form:input>
												</td>
												<td width="56%"><div id="errordeptNameLocal" class="errormsg"></td>
											</tr>
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message htmlEscape="true" 
														code="Label.SHORTNAMEENGLISH"></spring:message> <br /> <form:input
														path="shortDeptName" id="shortDeptName" maxlength="10" style="width: 200px" class="frmfield"></form:input>
												</td>
												<td width="56%"><div id="errorshortDeptName" class="errormsg"></td>
											</tr>
											
										</table>
									</div></td>
							</tr>

							<tr>
								<td width="100%">
								</br>
									<div class="frmtxt" id="divSubdistrictlvl">
										<div class="frmhdtitle">
											<spring:message htmlEscape="true"  code="Label.SUBDISTRICTLVL"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="14%"></td>
												<td width="36%" class="tblclear"><form:radiobutton
														path="levelradio" id="rdAllDistrictSub" htmlEscape="true"
														onclick="toggle1();" value="F" /> <spring:message htmlEscape="true" 
														code="Label.ALLSUBDISTRICTSTATE"></spring:message> 
														&nbsp;&nbsp;&nbsp;&nbsp;
														<form:radiobutton
														path="levelradio" value="S" id="rdSpecificDistrictSub" checked="true"
														onclick="toggle1();" /> <spring:message htmlEscape="true" 
														code="Label.SPECIFICSUBDISTRICT"></spring:message></td>
												<td width="50%"></td>
											</tr>
											<tr><td></br></td></tr>
											<tr>
												<td width="100%" colspan="3">
													<div class="frmtxt" id="divSpecificDistSub">
														<div class="frmhdtitle">
															<spring:message htmlEscape="true"  code="Label.SPECIFICDETAIL"></spring:message>
														</div>
														<table width="100%" class="tbl_no_brdr">
															<tr>
															<td width="12%"></td>
															<td width="88%">
															<form:checkbox  name="checkbox" id="DistrictChkFull" value="DFull" path="specificLevel" htmlEscape="true"
																onclick="toggle2();" />
																<spring:message htmlEscape="true"  code="Label.DISTRICTFULLCOVERAGE"></spring:message>
																<div id="errorDistrictChkFull" class="errormsg"></div>
															</td>
															</tr>
															
															<tr>
																	<td colspan="3">
																	</br>
																		<div class="frmtxt" id="TblDFull">
																		<div class="frmhdtitle">
																			<spring:message htmlEscape="true"  code="Label.SPECIFICDISTRICT"></spring:message>
																		</div>
																		<table width="650">
																			<table width="100%" class="tbl_no_brdr" align="left">
																				<tr style="display: none;">
																				<td width="1.5%"></td>
																				<td><spring:message htmlEscape="true"  code="Label.SELECTSTATE"></spring:message><br/>
																					<form:select path="" class="combofield" id="stateName" htmlEscape="true"
																						onchange="divDistrictAtDistrictlvl();">
																					<form:options items="${stateList}"
																					itemValue="stateCode"
																					itemLabel="stateNameEnglish"></form:options>
																					</form:select>
																				</td>
																				</tr>
																<tr>
																		<table width="800" class="tbl_no_brdr">

															<tr>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
															</tr>
															<tr>
																<td width="200" rowspan="3" align="center">
																<spring:message htmlEscape="true" 
																		code="Label.DISTRICTLIST"></spring:message><br /> <form:select
																		path="" multiple="true"
																		style="height:100px; width: 233px" class="frmtxtarea" id="dddistrictAtStateLvl">
																	</form:select></td>

																<td width="100" align="center"><input type="button"
																	class="btn" value=" &gt;&gt;" style="width: 40px"
																	onclick="listbox_moveacross('dddistrictAtStateLvl', 'ddTargetDistrict')" />
																</td>

																<td width="200" rowspan="3" align="center"><spring:message htmlEscape="true" 
																		code="Label.DISTRICTLISTSELECTED"></spring:message><br /> <form:select
																		path="districtName" multiple="true" id="ddTargetDistrict" htmlEscape="true"
																		style="height:100px; width: 233px" class="frmtxtarea"/>
																</td>
																<td width="300"><div id="errorddTargetDistrict" class="errormsg"></div></td>
															</tr>

															<tr>
																<td align="center"><input type="button"
																	value=" &lt;&lt;" class="btn" style="width: 40px"
																	onclick="listbox_moveacross('ddTargetDistrict', 'dddistrictAtStateLvl')" />
																</td>
															</tr>
															<tr>
															<table><tr>
															<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
															<td>
															<form:checkbox  name="checkbox" id="DistrictChkPart" value="DPart" path="specificLevel" htmlEscape="true"
																onclick="toggle3();" />
																<spring:message htmlEscape="true"  code="Label.DISTRICTPARTCOVERAGE"></spring:message></br></br></td>
															</td></tr></table>
															</tr>
														</table>
														<tr id="tr_SubDistrict" >
																<tr>
																	<td colspan="3">
																	</br>
																		<div class="frmtxt" id="divspecificSubDistrict">
																			<table width="650">
																			<div class="frmhdtitle">
																				<spring:message htmlEscape="true"  code="Label.SPECIFICSUBDISTRICT"></spring:message>
																			</div>

																			<table width="100%" class="tbl_no_brdr" align="left">
																				<tr style="display: none;">
																				<td width="1.5%"></td>
																				<td>
																				<spring:message htmlEscape="true"  code="Label.SELECTSTATE"></spring:message><br/>
																					<form:select path="" class="combofield" id="stateNameDistforSD" htmlEscape="true"
																						onchange="getNotInDistrictList(this.value);">
																					<form:options items="${stateList}"
																					itemValue="stateCode"
																					itemLabel="stateNameEnglish"></form:options>
																					</form:select>
																				</td></tr>
																				<tr><td>&nbsp;</td></tr>
																				<tr>
																				<td width="1.5%"></td>
																				<td><spring:message htmlEscape="true" 
																		code="Label.SELECTDISTRICT"></spring:message><br /> <select
																		name="districtName1" class="combofield" id="dddistrictAtSDLvl"
																		style="width:200px"
																		onchange="getSubdistrictAtSubDistrictlvl(this.value);">
																		<option value="">Select</option>
																	</select>
																				</td>
																				</tr>
																<tr>
																		<table width="800" class="tbl_no_brdr">

															<tr>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
															</tr>
															<tr>
																<td width="200" rowspan="3" align="center">
																<spring:message htmlEscape="true" 
																		code="Label.SUBDISTRICTLIST"></spring:message><br /> <form:select
																		path="" multiple="true" htmlEscape="true"
																		style="height:100px; width: 233px" class="frmtxtarea" id="ddSubdistrictAtSubDistrictLvl">
																	</form:select></td>

																<td width="100" align="center"><input type="button"
																	class="btn" value=" &gt;&gt;" style="width: 40px"
																	onClick="listbox_moveacross('ddSubdistrictAtSubDistrictLvl', 'ddTargetDistrictSDLvl')" />
																</td>

																<td width="200" rowspan="3" align="center"><spring:message htmlEscape="true" 
																		code="Label.SELECTEDSUBDISTRICT"></spring:message><br /> <form:select
																		path="subdistrictName" multiple="true" id="ddTargetDistrictSDLvl" htmlEscape="true"
																		style="height:100px; width: 233px" class="frmtxtarea"/>
																</td>
																<td width="300"><div id="errorddTargetDistrictSDLvl" class="errormsg"></div></td>
															</tr>

															<tr>
																<td align="center"><input type="button"
																	value=" &lt;&lt;" class="btn" style="width: 40px"
																	onClick="listbox_moveacross('ddTargetDistrictSDLvl', 'ddSubdistrictAtSubDistrictLvl')" />
																</td>
															</tr>
														</table>
														<tr>
											<div class="btnpnl">
												<table width="100%" class="tbl_no_brdr">
													<tr>
														<td width="16%" rowspan="2">&nbsp;</td>
														<td width="84%" align="left"><label> <input
																type="submit" class="button" name="Save" id="btnSave" onclick="return validation();"
																value=<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message> />
														</label>
														
															<label> 
																	<input 	type="button" 
																			class="button" 
																			name="CLEAR" 
																			value=<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>
																			id="btnCancel" 
																			onclick="javascript:location.href='createDepartmentSubdistrictLevel.htm?level=${plevel}&<csrf:token uri='createDepartmentSubdistrictLevel.htm'/>';" /> 
															</label>
														<label> <input type="button" class="button"
																name="Cancel"
																value=<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>
																id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
															</div> <br /> <br /> <br /></td>

													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>
												</table>
											</div>
										</tr>	
																				</tr>
																				<tr><td>&nbsp;</td></tr>
																				</tr>
																			</table>
																			<tr><td>&nbsp;</td></tr>
																		
																		</table>
																		</div></td></tr>
															
															</table></div></td></tr></table>
										
									</div></td></tr></table>
															</div>
													</div>
												</td>

											</tr>
										</table>
									</div></td>
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