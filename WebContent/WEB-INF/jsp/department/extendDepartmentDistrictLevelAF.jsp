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
<script src="js/extendDepartment.js"></script>
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
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
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
	
	var itr=0;
	var selObj2=document.getElementById('ddTargetDistrict');
	var flag=true;
	document.getElementById('errorparent').innerHTML="";
	document.getElementById('errorshortDeptName').innerHTML="";
	document.getElementById('errordeptName').innerHTML="";
	document.getElementById('errordeptNameLocal').innerHTML="";
	document.getElementById('errorddTargetDistrict').innerHTML="";
	
	if(itr==0 && document.getElementById('parent').value=="" || document.getElementById('parent').value=="Select"){
				document.getElementById('errorparent').innerHTML="Kindly select the Parent from the list.";
				return false;
	}
	if(itr==0 && document.getElementById('deptName').value==""){
	
		document.getElementById('errordeptName').innerHTML="Kindly fill the District level unit name.";
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
	
	if(itr==0 && document.getElementById('rdSpecificStateDis').checked){
			for (var i = 0; i < selObj2.options.length; i++) {
			     selObj2.options[i].selected=true;
			}
			if(document.getElementById('ddTargetDistrict').value==""){
			flag=false;
			document.getElementById('errorddTargetDistrict').innerHTML="Kindly select District from the list.";
			}
	}
	return flag;
}
function toggle()
{	
	var rdAllStateDis =document.getElementById('rdAllStateDis');
	var rdSpecificStateDis =document.getElementById('rdSpecificStateDis');
	
	if(rdAllStateDis.checked)
		{
			document.getElementById('divspecificDistrict').style.display='none';
			document.getElementById('dstPart').style.display='none';
		}
		if(rdSpecificStateDis.checked)
		{
			document.getElementById('divspecificDistrict').style.display='block';
		}
	else
		{
			document.getElementById('divSpecificStateDist').style.display='none';
		}
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
			<form:form action="extendDepartmentDistrictLevel.htm?level=${level}" method="POST" commandName="extendDepartment">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="extendDepartmentDistrictLevel.htm"/>"/>
			<form:input path="levelNew" type="hidden" />
		
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
												<td width="30%"><spring:message htmlEscape="true"  code="Label.DEPTNAMED"></spring:message>
													<span class="errormsg">*</span> 
													<c:if test="${setNew==false}">
													&nbsp;<label><c:out value="${orgDetail.orgLevelSpecificName}"  escapeXml="true"></c:out></label> <form:input path="deptName" type="hidden"></form:input>
													
													</c:if>
													<c:if test="${setNew==true}">
													<br /><form:input path="deptName" id="deptName" maxlength="200" style="width: 200px" class="frmfield" onBlur="checkExistDepartment(${stateCode},'D',this.value)"></form:input>
													</c:if>
													
												</td>
												<td width="56%"><div id="errordeptName" class="errormsg"></div><form:errors htmlEscape="true"  path="deptName"
														cssClass="errormsg"></form:errors></td>
											</tr>
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message htmlEscape="true"  code="Label.DEPTNAMELOCAL"></spring:message>
													<c:if test="${setNew==false}">
														&nbsp;<label><c:out value="${orgDetail.orgLevelSpecificNameLocal}"  escapeXml="true"></c:out></label> 
													<form:input path="deptNameLocal" type="hidden"></form:input>
													</c:if>
													<c:if test="${setNew==true}">
													<br /> <form:input path="deptNameLocal"  id="deptNameLocal" maxlength="80" style="width: 200px" class="frmfield"></form:input>
													</c:if>
													
													
												</td>
												<td width="56%"><div id="errordeptNameLocal" class="errormsg"></div></td>
											</tr>
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message htmlEscape="true" 
														code="Label.SHORTNAMEENGLISH"></spring:message> 
														<c:if test="${setNew==false}">
													&nbsp;<label><c:out value="${orgDetail.orgLevelSpecificShortName}"  escapeXml="true"></c:out></label>  
													<form:input path="shortDeptName" type="hidden"></form:input>
													</c:if>
														
														<c:if test="${setNew==true}">
													<br /> <form:input path="shortDeptName" id="shortDeptName" maxlength="10" style="width: 200px" class="frmfield"></form:input>
													</c:if>
													 
												</td>
												<td width="56%"><div id="errorshortDeptName" class="errormsg"></div></td>
											</tr>
											
										</table>
									</div></td>
							</tr>
							<tr>
								<td width="100%">
								</br>
									<div class="frmtxt" id="divDistrictlvl">
										<div class="frmhdtitle">
											<spring:message htmlEscape="true"  code="Label.DISTRICTTRADNME"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="16%"></td>
												<td width="34%" class="tblclear">
												
												<c:if test="${fn:containsIgnoreCase(orgDetail.coverage,'F')}">      																	
												<form:radiobutton
														path="levelradio" id="rdAllStateDis" htmlEscape="true" 	checked="true"		
														onclick="toggle();" value="F" />
														 <spring:message htmlEscape="true" 
														code="Label.ALLDISTRICTSTATE"></spring:message> 
														&nbsp;&nbsp;&nbsp;&nbsp;
														<form:radiobutton
														path="levelradio" value="S" id="rdSpecificStateDis" disabled="true" 
														onclick="toggle();" /> <spring:message htmlEscape="true" 
														code="Label.SPECIFICDISTRICT"></spring:message>
														
														
	  											</c:if>
	  											<c:if test="${fn:containsIgnoreCase(orgDetail.coverage,'S')}">
	  											<form:radiobutton
														path="levelradio" id="rdAllStateDis" htmlEscape="true" 	
														onclick="toggle();" value="F" />
														 <spring:message htmlEscape="true" 
														code="Label.ALLDISTRICTSTATE"></spring:message> 
														&nbsp;&nbsp;&nbsp;&nbsp;
														<form:radiobutton
														path="levelradio" value="S" id="rdSpecificStateDis" disabled="true" checked="true"		
														onclick="toggle();" /> <spring:message htmlEscape="true" 
														code="Label.SPECIFICDISTRICT"></spring:message>
	  											</c:if>
	  											
	  												<c:if test="${setNew==true}">
	  												<form:radiobutton
														path="levelradio" id="rdAllStateDis" htmlEscape="true" 	
														onclick="toggle();" value="F" />
														 <spring:message htmlEscape="true" 
														code="Label.ALLDISTRICTSTATE"></spring:message> 
														&nbsp;&nbsp;&nbsp;&nbsp;
														<form:radiobutton
														path="levelradio" value="S" id="rdSpecificStateDis"  checked="true"		
														onclick="toggle();" /> <spring:message htmlEscape="true" 
														code="Label.SPECIFICDISTRICT"></spring:message>
	  											</c:if>
	  											
												</td>
												<td width="50%"></td>
											</tr>
											<tr><td></br></td></tr>

																<tr>
																	<td colspan="3">
																	</br>
																		<div class="frmtxt" id="divspecificDistrict">
																			<div class="frmhdtitle">
																				<spring:message htmlEscape="true"  code="Label.SPECIFICDISTRICT"></spring:message>
																			</div>

																			<table width="100%" class="tbl_no_brdr" align="left">
																<tr>
																		<table width="800" class="tbl_no_brdr">

															<tr>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
															</tr>
															<tr>
																<td width="200" rowspan="3" align="center">
																<spring:message htmlEscape="true" 
																		code="Label.DISTRICTLIST"></spring:message><br /> 
																	<form:select
																		path="" multiple="true"
																		style="height:100px; width: 233px" class="frmtxtarea" id="dddistrictAtStateLvl">
																		<form:options items="${distList}"
																			itemValue="districtCode"
																			itemLabel="districtNameEnglish"></form:options>
																	</form:select></td>

																<td width="100" align="center"><input type="button"
																	class="btn" value=" &gt;&gt;" style="width: 40px"
																	onclick="listbox_moveacross('dddistrictAtStateLvl', 'ddTargetDistrict')" />
																</td>

																<td width="200" rowspan="3" align="center"><spring:message htmlEscape="true" 
																		code="Label.DISTRICTLISTSELECTED"></spring:message><br /> <form:select
																		path="districtName" multiple="true" id="ddTargetDistrict"
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
													type="submit" class="button" name="Save" id="btnSave" onclick="return validation();"
													value=<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message> />
											</label>
													<input type="button" value="Add Another District Level Setup" onclick="addAnotherSubmit();"/>
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