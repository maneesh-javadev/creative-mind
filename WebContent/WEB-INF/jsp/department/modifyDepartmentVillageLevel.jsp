<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%@include file="../common/taglib_includes.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
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
function onLoadSelect(){
	divDistrictAtVillagelvl();
}
function formSubmit(){
	var isError=validation();
	if(isError==0){
		document.forms['modifyDepartmentCmd'].submit();
	}
}
function validation(){
	var error=0;
	var itr=0;
	var selObj1=document.getElementById('ddTargetState');
	var selObj2=document.getElementById('ddTargetDistrict');
	var selObj3=document.getElementById('ddTargetDistrictSDLvl');
	var selObj4=document.getElementById('ddTargetDistrictVillLvl');
	
	if(itr==0 && document.getElementById('parent').value=="" || document.getElementById('parent').value=="Select"){
		error=1;
		itr=1;
		alert("Kindly select the Parent from the list.");
	}
	if(itr==0 && document.getElementById('deptName').value==""){
		error=1;
		itr=1;
		alert("Kindly fill the Village level unit name.");
	}
	if(itr==0 && document.getElementById('rdSpecificVillages').checked){
		if(!document.getElementById('StateChkFull').checked){
			alert("Kindly select the specific detail.");
		}
		if(document.getElementById('StateChkFull').checked){
			for (var i = 0; i < selObj1.options.length; i++) {
			     selObj1.options[i].selected=true;
			}
			if(document.getElementById('ddTargetState').value==""){
				error=1;
				itr=1;
				alert("Kindly select State from the list.");
			}
		}
		if(document.getElementById('DistrictChkFull').checked){
			for (var i = 0; i < selObj2.options.length; i++) {
			     selObj2.options[i].selected=true;
			}
			if(document.getElementById('ddTargetDistrict').value==""){
				error=1;
				itr=1;
				alert("Kindly select District from the list.");
			}
		}
		if(document.getElementById('SubDistrictChkFull').checked){
			for (var i = 0; i < selObj3.options.length; i++) {
			     selObj3.options[i].selected=true;
			}
			if(document.getElementById('ddTargetDistrictSDLvl').value==""){
				error=1;
				itr=1;
				alert("Kindly select Sub District from the list.");
			}
		}
		if(document.getElementById('SubDistrictChkPart').checked){
			for (var i = 0; i < selObj4.options.length; i++) {
			     selObj4.options[i].selected=true;
			}
			if(document.getElementById('ddTargetDistrictVillLvl').value==""){
				error=1;
				itr=1;
				alert("Kindly select Village from the list.");
			}
		}
	}
	return error;
}
</script>
</head>
<body onload="onLoadSelect()">
	<div id="frmcontent">
		<div class="frmhd" >


			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message htmlEscape="true"  code="Label.CREATEDEPTVILLAGELEVEL"></spring:message>
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
			<form:form action="modifyDepartmentVillageLevel.htm?type=${level2}" method="POST" commandName="modifyDepartmentCmd">
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
												<spring:message htmlEscape="true"  code="Label.PARENTSELECT"></spring:message>
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
												<td width="56%"><form:errors htmlEscape="true"  path="parent"
														cssClass="errormsg"></form:errors></td>
											</tr>
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message htmlEscape="true"  code="Label.DEPTNAMEV"></spring:message>
													<span class="errormsg">*</span> <br /> <form:input
														path="deptName" id="deptName" style="width: 200px" class="frmfield"></form:input>
												</td>
												<td width="56%"><form:errors htmlEscape="true"  path="deptName"
														cssClass="errormsg"></form:errors></td>
											</tr>
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message htmlEscape="true"  code="Label.DEPTNAMELOCAL"></spring:message>
													<br /> <form:input path="deptNameLocal" style="width: 200px"
														class="frmfield"></form:input>
														 <form:input path="orgCode" type="hidden"
														class="frmfield"></form:input>
												</td>
												<td width="56%"></td>
											</tr>
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message htmlEscape="true" 
														code="Label.SHORTNAMEENGLISH"></spring:message> <br /> <form:input
														path="shortDeptName" style="width: 200px" class="frmfield"></form:input>
												</td>
												<td width="56%"></td>
											</tr>
											
										</table>
									</div></td>
							</tr>

							<tr>
								<td width="100%">
								</br>
									<div class="frmtxt" id="divSubdistrictlvl">
										<div class="frmhdtitle">
											<spring:message htmlEscape="true"  code="Label.VILLAGELVL"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="14%"></td>
												<td width="36%" class="tblclear"><form:radiobutton
														path="levelradio" id="rdAllVillages" htmlEscape="true"
														onclick="divDistrictAtVillagelvl();" value="F" /> <spring:message htmlEscape="true" 
														code="Label.ALLVILLAGE"></spring:message> 
														&nbsp;&nbsp;&nbsp;&nbsp;
														<form:radiobutton
														path="levelradio" value="S" id="rdSpecificVillages" checked="true" htmlEscape="true"
														onclick="divDistrictAtVillagelvl();" /> <spring:message htmlEscape="true" 
														code="Label.SPECIFICVILLAGE"></spring:message></td>
												<td width="50%"></td>
											</tr>
											<tr><td></br></br></td></tr>
											<tr>
												<td width="100%" colspan="3">

													<div class="frmtxt" id="divSpecificVillage">
														<div class="frmhdtitle">
															<spring:message htmlEscape="true"  code="Label.SPECIFICDETAIL"></spring:message>
														</div>
														<table width="100%" class="tbl_no_brdr">
															<tr>
																<td width="12%"></td>
																<td width="88%">
																<form:checkbox  name="checkbox" id="StateChkFull" value="SFull" path="specificLevel" htmlEscape="true"
																onclick="VillageFullPart();" />
																<spring:message htmlEscape="true"  code="Label.STATEFULLCOVERAGE"></spring:message>
															</br></br>
																</td>
															</tr>
															<tr>
															<td colspan="3">
															<div class="frmtxt" id="TblSFull">
																<div class="frmhdtitle">
																	<spring:message htmlEscape="true"  code="Label.SPECIFICSTATE"></spring:message>
																</div>
															<table>
															<tr>
																<td width="275" rowspan="3" align="center">
																<spring:message htmlEscape="true" 
																		code="Label.STATELIST"></spring:message><br /> <form:select
																		path="" multiple="true"
																		style="height:100px; width: 233px" class="frmtxtarea" id="ddSourceState">
																		<form:options items="${stateSourceList}" 
																			itemValue="statePK.stateCode" htmlEscape="true"
																			itemLabel="stateNameEnglish"></form:options>
																	</form:select></td>

																<td width="100" align="center"><input type="button"
																	class="btn" value=" &gt;&gt;" style="width: 40px"
																	onclick="listbox_moveacross('ddSourceState', 'ddTargetState')" />
																</td>

																<td width="275" rowspan="3" align="center"><spring:message htmlEscape="true" 
																		code="Label.STATELISTSELECTED"></spring:message><br /> <form:select
																		path="stateName" multiple="true" id="ddTargetState" htmlEscape="true"
																		style="height:100px; width: 233px" class="frmtxtarea"/>
																</td>
																<tr>
																<td align="center"><input type="button"
																	value=" &lt;&lt;" class="btn" style="width: 40px"
																	onclick="listbox_moveacross('ddTargetState', 'ddSourceState')" />
																</td>
															</tr>
															</tr>
															<tr>
															<table><tr></br>
															<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
															<td>
															<form:checkbox  name="checkbox" id="DistrictChkFull" value="DFull" path="specificLevel" htmlEscape="true"
																onclick="VillageFullPart();" />
																<spring:message htmlEscape="true"  code="Label.DISTRICTFULLCOVERAGE"></spring:message></br></br></td>
															</td></tr></table>
															</tr>
															</table>
															</br>
															<div class="frmtxt" id="TblDFull">
																		<div class="frmhdtitle">
																			<spring:message htmlEscape="true"  code="Label.SPECIFICDISTRICT"></spring:message>
																		</div>
																		<table width="650">
																			<table width="100%" class="tbl_no_brdr" align="left">
																				<tr>
																				<td width="1.5%"></td>
																				<td><spring:message htmlEscape="true"  code="Label.SELECTSTATE"></spring:message><br/>
																					<form:select path="" class="combofield" id="stateName"
																						onchange="divDistrictAtDistrictlvl();">
																					</form:select>
																				</td>
																				</tr>
																<tr>
																		<table width="650" class="tbl_no_brdr">

															<tr>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
															</tr>
															<tr>
																<td width="275" rowspan="3" align="center">
																<spring:message htmlEscape="true" 
																		code="Label.DISTRICTLIST"></spring:message><br /> <form:select
																		path="" multiple="true"
																		style="height:100px; width: 233px" class="frmtxtarea" id="dddistrictAtStateLvl">
																	</form:select></td>

																<td width="100" align="center"><input type="button"
																	class="btn" value=" &gt;&gt;" style="width: 40px"
																	onClick="listbox_moveacross('dddistrictAtStateLvl', 'ddTargetDistrict')" />
																</td>

																<td width="275" rowspan="3" align="center"><spring:message htmlEscape="true" 
																		code="Label.DISTRICTLISTSELECTED"></spring:message><br /> <form:select
																		path="districtName" multiple="true" id="ddTargetDistrict"
																		style="height:100px; width: 233px" class="frmtxtarea"/>
																</td>
															</tr>

															<tr>
																<td align="center"><input type="button"
																	value=" &lt;&lt;" class="btn" style="width: 40px"
																	onClick="listbox_moveacross('ddTargetDistrict', 'dddistrictAtStateLvl')" />
																</td>
															</tr>
															<tr>
															<table><tr></br>
															<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
															<td>
															<form:checkbox  name="checkbox" id="SubDistrictChkFull" value="SDFull" path="specificLevel" 
																onclick="VillageFullPart();" />
																<spring:message htmlEscape="true"  code="Label.SUBDISTRICTFULLCOVERAGE"></spring:message></br></br></td>
															</td></tr></table>
															</tr>
															<tr>
																	<td colspan="3">
																	</br>
																		<div class="frmtxt" id="TblSDFull">
																			<table width="650">
																			<div class="frmhdtitle">
																				<spring:message htmlEscape="true"  code="Label.SPECIFICSUBDISTRICT"></spring:message>
																			</div>

																			<table width="100%" class="tbl_no_brdr" align="left">
																				<tr>
																				<td width="1.5%"></td>
																				<td><spring:message htmlEscape="true"  code="Label.SELECTSTATE"></spring:message><br/>
																					<form:select path="" class="combofield" id="stateNameDistforSD"
																						onchange="getNotInDistrictList(this.value);">
																						<form:option value="0">Select State</form:option>
																					</form:select>
																				</td></tr>
																				<tr><td>&nbsp;</td></tr>
																				<tr>
																				<td width="1.5%"></td>
																				<td><spring:message htmlEscape="true" 
																		code="Label.SELECTDISTRICT"></spring:message><br /> <form:select
																		path="" class="combofield" id="dddistrictAtSDLvl"
																		style="width:200px"
																		onchange="getSubdistrictAtSubDistrictlvl(this.value);">
																	</form:select>
																				</td>
																				</tr>
																<tr>
																		<table width="650" class="tbl_no_brdr">

															<tr>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
															</tr>
															<tr>
																<td width="275" rowspan="3" align="center">
																<spring:message htmlEscape="true" 
																		code="Label.SUBDISTRICTLIST"></spring:message><br /> <form:select
																		path="" multiple="true"
																		style="height:100px; width: 233px" class="frmtxtarea" id="ddSubdistrictAtSubDistrictLvl">
																	</form:select></td>

																<td width="100" align="center"><input type="button"
																	class="btn" value=" &gt;&gt;" style="width: 40px"
																	onClick="listbox_moveacross('ddSubdistrictAtSubDistrictLvl', 'ddTargetDistrictSDLvl')" />
																</td>

																<td width="275" rowspan="3" align="center"><spring:message htmlEscape="true" 
																		code="Label.SUBDISTRICTTOINVALIDATE"></spring:message><br /> <form:select
																		path="subdistrictName" multiple="true" id="ddTargetDistrictSDLvl" htmlEscape="true"
																		style="height:100px; width: 233px" class="frmtxtarea"/>
																</td>
															</tr>

															<tr>
																<td align="center"><input type="button"
																	value=" &lt;&lt;" class="btn" style="width: 40px"
																	onClick="listbox_moveacross('ddTargetDistrictSDLvl', 'ddSubdistrictAtSubDistrictLvl')" />
																</td>
															</tr>
															<tr>
															<table><tr></br>
															<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
															<td>
																<form:checkbox  name="checkbox" id="SubDistrictChkPart" value="SDPart" path="specificLevel" 
																onclick="VillageFullPart();" />
																<spring:message htmlEscape="true"  code="Label.SUBDISTRICTPARTCOVERAGE"></spring:message></br></br></td>
															</td></tr></table>
															</tr>
															<tr>
																			<td colspan="3">
																			</br>
																			<div class="frmtxt" id="TblSDPart">
																			<div class="frmhdtitle">
																				<spring:message htmlEscape="true"  code="Label.SPECIFICVILLAGE"></spring:message>
																			</div>
																			<table width="100%" class="tbl_no_brdr" align="left">
																				<tr>
																				<td width="1.5%"></td>
																				<td><spring:message htmlEscape="true"  code="Label.SELECTSTATE"></spring:message><br/>
																					<form:select path="" class="combofield" id="stateNameDistforVill"
																						onchange="getNotInDistrictListVillLvl(this.value);">
																						<form:option value="0">Select State</form:option>
																					</form:select>
																				</td></tr>
																				<tr><td>&nbsp;</td></tr>
																				<tr>
																				<td width="1.5%"></td>
																				<td><spring:message htmlEscape="true" 
																		code="Label.SELECTDISTRICT"></spring:message><br/> <form:select
																		path="" class="combofield" id="dddistrictAtVillLvl"
																		style="width:200px"
																		onchange="getSubdistrictAtVilllvl(this.value);">
																	</form:select>
																				</td></tr>
																				<tr><td>&nbsp;</td></tr>
																				<tr>
																				<td width="1.5%"></td>
																				<td><spring:message htmlEscape="true" 
																		code="Label.SELECTSUBDISTRICT"></spring:message><br/> <form:select
																		path="" class="combofield" id="ddSubdistrictAtVillLvl"
																		style="width:200px"
																		onchange="getVillageListAtVillageLvl(this.value);">
																	</form:select>
																				</td>
																				</tr>
																<tr>
																		<table width="650" class="tbl_no_brdr">

															<tr>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
															</tr>
															<tr>
																<td width="275" rowspan="3" align="center">
																<spring:message htmlEscape="true" 
																		code="Label.VILLAGELIST"></spring:message><br /> <form:select
																		path="" multiple="true"
																		style="height:100px; width: 233px" class="frmtxtarea" id="ddVillageAtVillLvl">
																	</form:select></td>

																<td width="100" align="center"><input type="button"
																	class="btn" value=" &gt;&gt;" style="width: 40px"
																	onClick="listbox_moveacross('ddVillageAtVillLvl', 'ddTargetDistrictVillLvl')" />
																</td>

																<td width="275" rowspan="3" align="center"><spring:message htmlEscape="true" 
																		code="Label.VILLAGELISTSELECTED"></spring:message><br /> <form:select
																		path="villageName" multiple="true" id="ddTargetDistrictVillLvl"
																		style="height:100px; width: 233px" class="frmtxtarea"/>
																</td>
															</tr>

															<tr>
																<td align="center"><input type="button"
																	value=" &lt;&lt;" class="btn" style="width: 40px"
																	onClick="listbox_moveacross('ddTargetDistrictVillLvl', 'ddVillageAtVillLvl')" />
																</td>
															</tr>
														</table>
																<tr>
															</div>
											<div class="btnpnl">
												<table width="100%" class="tbl_no_brdr">
													<tr>
														<td width="16%" rowspan="2">&nbsp;</td>
														<td width="84%" align="left"><label> <input
																type="submit" class="button" name="Save" id="btnSave" onclick="formSubmit();"
																value=<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message> />
														</label><label> <input type="button" class="button"
																name="Cancel"
																value=<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>
																id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
															</div></td>

													</tr>
												</table>
											</div>
										</tr>
																				</tr>
																				<tr><td>&nbsp;</td></tr>
																				</tr>
																			</table></div></td></tr>
														</table>
																				</tr>
																				<tr><td>&nbsp;</td></tr>
																				</tr>
																			</table>
																			
																			<tr><td>&nbsp;</td></tr>
																		</table>
																		</div></td></tr>
														</table>
																
															</table></div></td></tr></table>
										
									</div>
															</div>
															</td>
															</tr>
															<tr>
																	<td colspan="3">
																		</td></tr></table>
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