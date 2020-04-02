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
function onLoadSelect(){
	document.getElementById('addAnother').value="";
	var mypath = window.location.href;
	var mySplitResult = mypath.split("=")[1].split(",");
	if (mySplitResult[1] != null) {
		document.getElementById('btnSave').value="Next";
	} 
	divStateAtDistrictlvl();
}
function addAnotherSubmit(){
	document.getElementById('addAnother').value="true";
	var isError=validation();
	if(isError==0){
		document.forms['createDepartment'].submit();
	}
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
	var selObj1=document.getElementById('ddTargetState');
	var selObj2=document.getElementById('ddTargetDistrict');
	
	if(itr==0 && document.getElementById('parent').value=="" || document.getElementById('parent').value=="Select"){
		error=1;
		itr=1;
		alert("Kindly select the Parent from the list.");
	}
	if(itr==0 && document.getElementById('deptName').value==""){
		error=1;
		itr=1;
		alert("Kindly fill the District level unit name.");
	}
	
	if(itr==0 && document.getElementById('rdSpecificStateDis').checked){
		if(!document.getElementById('DistrictChkFull').checked){
			error=1;
			itr=1;
			alert("Kindly select the specific detail.");
		}
		if(document.getElementById('DistrictChkFull').checked){
			for (var i = 0; i < selObj1.options.length; i++) {
			     selObj1.options[i].selected=true;
			}
			if(document.getElementById('ddTargetState').value==""){
				error=1;
				itr=1;
				alert("Kindly select State from the list.");
			}
		}
		if(document.getElementById('DistrictChkPart').checked){
			for (var i = 0; i < selObj2.options.length; i++) {
			     selObj2.options[i].selected=true;
			}
			if(document.getElementById('ddTargetDistrict').value==""){
				error=1;
				itr=1;
				alert("Kindly select District from the list.");
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
					<td><spring:message htmlEscape="true"  code="Label.CREATEDEPTDISTRICTLEVEL"></spring:message>
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
			<form:form action="createDepartmentDistrictLevel.htm?level=${level}" method="POST" commandName="createDepartment">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="createDepartmentDistrictLevel.htm"/>"/>
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
												<td width="56%"><form:errors htmlEscape="true"  path="parent"
														cssClass="errormsg"></form:errors></td>
											</tr>
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message htmlEscape="true"  code="Label.DISTDEPTNAMEENG"></spring:message>
													<span class="errormsg">*</span> <br /> <form:input
														path="deptName" id="deptName" style="width: 200px" class="frmfield"></form:input>
												</td>
												<td width="56%"><form:errors htmlEscape="true"  path="deptName"
														cssClass="errormsg"></form:errors></td>
											</tr>
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message htmlEscape="true"  code="Label.DISTDEPTNAMELC"></spring:message>
													<br /> <form:input path="deptNameLocal" style="width: 200px"
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
								<br/>
									<div class="frmtxt" id="divDistrictlvl">
										<div class="frmhdtitle">
											<spring:message htmlEscape="true"  code="Label.DISTRICTTRADNME"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="16%"></td>
												<td width="34%" class="tblclear"><form:radiobutton
														path="levelradio" id="rdAllStateDis" htmlEscape="true"
														onclick="divStateAtDistrictlvl();" value="F" /> <spring:message htmlEscape="true" 
														code="Label.ALLDISTRICT"></spring:message> 
														&nbsp;&nbsp;&nbsp;&nbsp;
														<form:radiobutton
														path="levelradio" value="S" id="rdSpecificStateDis" checked="true"
														onclick="divStateAtDistrictlvl();" /> <spring:message htmlEscape="true" 
														code="Label.SPECIFICDISTRICT"></spring:message></td>
												<td width="50%"></td>
											</tr>
											<tr><td></br></td></tr>

											<tr>
												<td width="100%" colspan="3">
													<div class="frmtxt" id="divSpecificStateDist">
														<div class="frmhdtitle">
															<spring:message htmlEscape="true"  code="Label.SPECIFICSTATE"></spring:message>
														</div>
														<table width="100%" class="tbl_no_brdr">
															<tr>
																<td width="14%"></td>
																<td><form:checkbox  name="checkbox" id="DistrictChkFull" value="Full" path="specificLevel" htmlEscape="true"
																onclick="DistrictFullPart();" />
																<spring:message htmlEscape="true"  code="Label.STATEFULLCOVERAGE"></spring:message></td>
																<td id="dstPart">
																<div style="margin-right: 350px">
																<form:checkbox  name="checkbox" id="DistrictChkPart" value="Part" path="specificLevel" 
																onclick="DistrictFullPart();" />
																<spring:message htmlEscape="true"  code="Label.STATEPARTCOVERAGE"></spring:message>
																</div>
																</td>
															</tr>
															<tr id="trDistrictOption">
															<table width="650" class="tbl_no_brdr" id="TblFull">

															<tr>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
															</tr>
															<tr>
																<td width="275" rowspan="3" align="center">
																<spring:message htmlEscape="true" 
																		code="Label.STATELIST"></spring:message><br /> <form:select
																		path="" multiple="true" htmlEscape="true"
																		style="height:100px; width: 233px" class="frmtxtarea" id="ddSourceState">
																		<form:options items="${stateSourceList}" 
																			itemValue="statePK.stateCode"
																			itemLabel="stateNameEnglish"></form:options>
																	</form:select></td>

																<td width="100" align="center"><input type="button"
																	class="btn" value=" &gt;&gt;" style="width: 40px"
																	onclick="listbox_moveacross('ddSourceState', 'ddTargetState')" />
																</td>

																<td width="275" rowspan="3" align="center"><spring:message htmlEscape="true" 
																		code="Label.STATELISTSELECTED"></spring:message><br /> <form:select
																		path="stateName" multiple="true" id="ddTargetState"
																		style="height:100px; width: 233px" class="frmtxtarea"/>
																</td>
															</tr>

															<tr>
																<td align="center"><input type="button"
																	value=" &lt;&lt;" class="btn" style="width: 40px"
																	onclick="listbox_moveacross('ddTargetState', 'ddSourceState')" />
																</td>
															</tr>
															<%-- <tr>
															<table><tr></br>
															<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
															<td id="dstPart"><form:checkbox  name="checkbox" id="DistrictChkPart" value="Part" path="specificLevel" 
																onclick="DistrictFullPart();" />
																<spring:message htmlEscape="true"  code="Label.STATEPARTCOVERAGE"></spring:message></td>
																<td width="50%"></td></tr></table>
															</tr> --%>
														</table>
															</tr>
																<tr>
																	<td colspan="3">
																	</br>
																		<div class="frmtxt" id="divspecificDistrict">
																			<div class="frmhdtitle">
																				<spring:message htmlEscape="true"  code="Label.SPECIFICDISTRICT"></spring:message>
																			</div>

																			<table width="100%" class="tbl_no_brdr" align="left">
																				<tr>
																				<td width="1.5%"></td>
																				<td><spring:message htmlEscape="true"  code="Label.SELECTSTATE"></spring:message><br/>
																					<form:select path="stateName" class="combofield" id="stateName"
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
														</table>
																				</tr>
																			</table>
																			
																		</div></td>
																		<tr><td>&nbsp;</td></tr>
																		<tr>
							<div class="btnpnl">
									<table width="100%" class="tbl_no_brdr">
										<tr>
										<input type="hidden" name="addAnother" id="addAnother"/>
											<td width="16%" rowspan="2">&nbsp;</td>
											<td width="84%" align="left"><label> <input
													type="button" class="button" name="Save" id="btnSave" onclick="formSubmit();"
													value=<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message> />
											</label>
<!-- 													<input type="button" value="Add Another District Level Setup" onclick="addAnotherSubmit();"/> -->
													<label> <input type="button" class="button"
													name="Cancel"
													value=<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>
													id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
												</div> <br /> <br /> <br /></td>

										</tr>
										
									</table>
								</div>
								</tr>
																</tr>
														</table>


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