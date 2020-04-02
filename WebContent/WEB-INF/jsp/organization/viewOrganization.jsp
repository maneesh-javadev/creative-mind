<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<%@include file="../common/taglib_includes.jsp"%>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/ministry.js"></script>
<script type="text/javascript">

function manageOrganization(url,id)
{
	dwr.util.setValue('organizationId', id, {	escapeHtml : false	});
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}

function doRefresh(val)
{
	if (val)
		removeSelectedValue('ddDepartment');
	else
		{
		
		}
}

function removeSelectedValue(val)
{
  var elSel = document.getElementById(val);
  var i;
  for (i = elSel.length - 1; i>=0; i--) 
   	elSel.remove(i);
}

function showHide()
{
	document.getElementById('chkBox').checked==true?document.getElementById('ddSourceState').disabled=true:document.getElementById('ddSourceState').disabled=false;
	document.getElementById('chkBox').checked==true?document.getElementById('ddMinistry').disabled=true:document.getElementById('ddMinistry').disabled=false;
}

/* function getData()
{
	document.getElementById('text1').value = trim(document.getElementById('text1').value);
	document.getElementById('text2').value = trim(document.getElementById('text2').value);
	
	if (document.getElementById('text1').value!='' || document.getElementById('text2').value!='')
		document.forms['form1'].submit();
	else
		alert('Please enter search text!');
}

function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
} */



///////////////////////////////////////////////////////////////////////////////////////////////

/* function doRefresh()
{
	document.getElementById('ddMinistry').value='';
	document.getElementById('stateLine').selectedIndex=0;
} */

function getData()
{
	var chkcentre=document.getElementById('chkcentre');
	var chkstate=document.getElementById('chkstate');
	
	if(chkcentre.checked)
	{
	document.getElementById('ddMinistry').value = trim(document.getElementById('ddMinistry').value);
	
	if (document.getElementById('ddMinistry').value!='')
		{
		document.forms['form1'].submit();
		}
	else
		{
		alert('Please enter search text!');
		}
}
	else if(chkstate.checked)
	{
		document.getElementById('ddMinistry').value='';
		document.forms['form1'].submit();
		chkstate.checked=true;
		chkcentre.checked=false;
	}
}

function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}

function loadPage()
{	
	document.getElementById('td_district').style.display='none';
	document.getElementById('td_subdistrict').style.display='none';
	document.getElementById('td_block').style.display='none';
	document.getElementById('td_village').style.display='none';
	//document.getElementById('lbSpecific').style.display='none';
	
document.getElementById('chkcentre').checked=false;
document.getElementById('chkstate').checked=false;
if(document.getElementById('ddMinistry').value!='')
	{
	document.getElementById('chkcentre').checked=true;
	document.getElementById('chkstate').checked=false;
	}
/* else if(document.getElementById('stateLine').value!=0)
	{
	document.getElementById('chkcentre').checked=false;
	document.getElementById('chkstate').checked=true;
	} */

	}
	
function doRef()
{
	document.getElementById('category').selectedIndex=0;
//	removeSelectedValue('category');
//	removeSelectedValue('ddMinistry');
	removeSelectedValue('ddDepartment');
	removeSelectedValue('ddSourceDistrict');	
	removeSelectedValue('ddSourceSubDistrict');
	removeSelectedValue('ddvillage');
	removeSelectedValue('ddblock');
	getandSetDistrictList('S');
}	
	
	
function toggledisplayorg(obj,val)
{
	if (document.getElementById('divData')!=null)
		document.getElementById('divData').style.visibility='hidden';
	if (document.getElementById(obj).checked)
		{
			
			if (val=='showbycentrelevel')
				{
					document.getElementById('showbycentrelevel').style.visibility='visible';
					document.getElementById('showbycentrelevel').style.display='block';
					document.getElementById('showbystatelevel').style.visibility='hidden';
					document.getElementById('showbystatelevel').style.display='none';
					
									
				}
			else if (val=='showbystatelevel')
			{
				document.getElementById('showbystatelevel').style.visibility='visible';
				document.getElementById('showbystatelevel').style.display='block';
				document.getElementById('showbycentrelevel').style.visibility='hidden';
				document.getElementById('showbycentrelevel').style.display='none';
			}
			
		}
}

function toggledisplaydept(obj,val)
{
	if (document.getElementById('divData')!=null)
		document.getElementById('divData').style.visibility='hidden';
	if (document.getElementById(obj).checked)
		{
			
			if (val=='showbycentrelevel')
				{
					document.getElementById('showbycentrelevel').style.visibility='visible';
					document.getElementById('showbycentrelevel').style.display='block';
					document.getElementById('showbystatelevel').style.visibility='hidden';
					document.getElementById('showbystatelevel').style.display='none';
					document.getElementById('showbylbtlevel').style.visibility='hidden';
					document.getElementById('showbystatelinelevel').style.visibility='hidden';				
				}
			else if (val=='showbystatelevel')
			{
				document.getElementById('showbystatelevel').style.visibility='visible';
				document.getElementById('showbystatelevel').style.display='block';
				document.getElementById('showbycentrelevel').style.visibility='hidden';
				document.getElementById('showbycentrelevel').style.display='none';
				document.getElementById('showbylbtlevel').style.visibility='hidden';
				document.getElementById('showbystatelinelevel').style.visibility='hidden';	
			}
			
		}
}

function toggledisplaydeptbylevel(obj,val)
{
	if (document.getElementById('divData')!=null)
		document.getElementById('divData').style.visibility='hidden';
	if (document.getElementById(obj).checked)
		{

			
			if (val=='showbystatelinelevel')
				{

					document.getElementById('showbystatelinelevel').style.visibility='visible';
					document.getElementById('showbystatelinelevel').style.display='block';
					document.getElementById('showbylbtlevel').style.visibility='hidden';
					document.getElementById('showbylbtlevel').style.display='none';									
				}
			else if (val=='showbylbtlevel')
			{
	
				document.getElementById('showbylbtlevel').style.visibility='visible';
				document.getElementById('showbylbtlevel').style.display='block';
				document.getElementById('showbystatelinelevel').style.visibility='hidden';
				document.getElementById('showbystatelinelevel').style.display='none';
			}

		}
}



</script>

</head>
<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
	<div id="frmcontent">
		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message htmlEscape="true" code="Label.VIEWORG"></spring:message></td>
					<td align="right"><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a></td>
					<%--//this link is not working  <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></td> --%>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="vieworganizationbydepartmentcodeCenter.htm" id="form1" method="POST" commandName="viewDept">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="vieworganizationbydepartmentcodeCenter.htm"/>" />
				<div id="cat">
					<input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("stateCode")%>' />
					<%-- <div class="frmpnlbg">
						      <div class="frmtxt" align="center">
							     <div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.FILTEROPTFORVIEWDEPT"></spring:message></div>
						          <table width="800" class="tbl_no_brdr">
						          <div>
						             <td width="86%">
                                          <table width="270" height="21" class="tbl_no_brdr">
                                            <tr>
                                              <td class="tblclear">
                                                  <label>
                                                     <form:radiobutton path="selectLevel" id='chkcentre' onclick='toggledisplaydept("chkcentre","showbycentrelevel");'/>
                                                  </label>
                                               </td>
                                              <td width="95" class="lblBold"><label><spring:message htmlEscape="true"  code="Label.CENTRELEVEL"></spring:message></label></td>
                                               <td>&nbsp;&nbsp;</td>
                                            <td class="tblclear">
                                                   <label>
                                                     <form:radiobutton path="selectLevel" id='chkstate' onclick='toggledisplaydept("chkstate","showbystatelevel");' />
                                                  </label>
                                               </td>
                                              <td width="85" class="lblBold"><label><spring:message htmlEscape="true"  code="Label.STATELEVEL"></spring:message></label></td> 
                                            </tr>
                                          </table>
                                      </td>
						          </div>						          
                          </table>
						</div>
					</div> --%>

					<div class="frmpnlbg" id='showbycentrelevel'>
						<div class="frmtxt" align="center">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message>
							</div>
							<table width="800" class="tbl_no_brdr">
								<%-- <tr>
									<td width="150" class="lblBold"><spring:message htmlEscape="true" 	code="Label.DEPTCODE"></spring:message></td>
									<td><label><form:input type="text" id="text1" onkeypress="validateNumericKeys(event)" path="" class="frmfield"/> </label></td>
									<td width="300" align="right" class="lblBold"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message></td>
									<td align="right"><label><form:input type="text" id="text2" onkeypress="validateCharKeys(event)" path="" class="frmfield"/> </label></td>
								</tr> --%>

								<%-- <tr>
								  <td width="14%" rowspan="9">&nbsp;</td>
								  <td width="86%" class="lblBold" align="left"><spring:message htmlEscape="true"  code="Label.SELMIN"></spring:message><br />
									<form:select path="ministryName" class="combofield" style="width: 150px" id="ddSourceState">
									            <form:option value=""><form:option selected="selected" value="" label="--select--" /></form:option>
									            <form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
									</form:select> <span class="error" id="ddSourceState_error"></span>
							   </tr> --%>


								<%--   <tr>
								<td width="14%">&nbsp;</td>
								<td width="86%" class="lblBold" align="left"><spring:message htmlEscape="true"  code="Label.SELECTSTATE"></spring:message><br />
									<form:select path="stateNameEnglish" class="combofield" style="width: 150px" id="ddSourceState" onchange="getMinistryListbyState(this.value)">
									            <form:option value=""><form:option selected="selected" value="" label="--select--" /></form:option>
									            <form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
									</form:select> <span class="error" id="ddSourceState_error"></span>
							  </tr> --%>


								<tr>
									<td width="14%">&nbsp;</td>
									<td class="lblBold" align="left"><label><spring:message htmlEscape="true" code="Label.SELMIN"></spring:message></label><span class="errormsg">*</span><br /> <form:select path="ministryName" style="width: 150px" class="combofield"
											id="ddMinistry" onchange="getDepartmentListByMinistry(this.value)">
											<form:option value="">
												<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
											</form:option>
											<form:options items="${ministryList}" itemLabel="orgName" itemValue="organizationPK.orgCode" />
										</form:select> <form:errors htmlEscape="true" path="ministryName" class="errormsg"></form:errors></td>
								</tr>

								<%-- 	<tr>
								<td width="24%" align="left">
									    <label> <form:checkbox name="checkbox" value="" id="chkBox" path="" onclick="getDepartmentList();showHide();doRefresh(!this.checked);" /></label>
									    <spring:message htmlEscape="true"  code="Label.SELECTALLSTATES"></spring:message>
									</td>								
								</tr> --%>

								<tr>
									<td width="14%">&nbsp;</td>
									<td class="lblBold" align="left"><label><spring:message htmlEscape="true" code="Label.SELDEPT"></spring:message></label><br /> <form:select path="deptName" style="width: 150px" class="combofield" id="ddDepartment">
											<form:options items="${departmentList}" itemLabel="orgName" itemValue="organizationPK.orgCode" />
										</form:select></td>
								</tr>

								<%--  <tr>
                                        <td width="14%">&nbsp;</td>
										<td align="left" class="lblBold"><spring:message htmlEscape="true"  code="Label.SELMIN"></spring:message><br />
											
											<form:select path="ministryName" class="combofield">
												<form:option value="0">
													      <form:option selected="selected" value="" label="--select--" />
												</form:option>
												<form:options items="${ministryList}" itemLabel="orgName" itemValue="organizationPK.orgCode"  />
											</form:select>
									   </td>
										<td width="56%"><form:errors htmlEscape="true"  path="ministryName" cssClass="errormsg"></form:errors></td>
								</tr> --%>


								<tr>
									<td>&nbsp;</td>
								</tr>

								<tr>
									<!-- correctly aligned no. of records label 22/05/2012 -->
									<%--  
	<td colspan="5" class="lblBold" align="right" >
		<label>
			<spring:message htmlEscape="true" code="Label.NOOFRECORDPERPAGE"></spring:message>
		<form:select htmlEscape="true" path="pageRows" class="combofield" style="width: 50px">
			<form:option value="5" label="5" />
			<form:option value="10" label="10" />
			<form:option value="25" label="25" selected="selected" />
			<form:option value="50" label="50" />
			<form:option value="100" label="100" />
		</form:select>
	   </label>
	</td>
 --%>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td height="50" colspan="4" align="center"><label> <input type="submit" name="Submit" class="btn" value=<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message> />
									</label><label> <input type="button" name="Submit2" class="btn" value=<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>
											onclick="javascript:location.href='vieworganization.htm?<csrf:token uri='vieworganization.htm'/>';" />
									</label><label> <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
									</label></td>
								</tr>
							</table>
						</div>
					</div>


					<!-- State Line Department -->

					<div class="frmpnlbg" id='showbystatelevel' style="visibility: hidden; display: none">
						<div class="frmtxt" align="center">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message>
							</div>
							<table width="400" class="tbl_no_brdr">

								<tr>
									<td align="left" class="lblBold"><label><spring:message htmlEscape="true" code="Label.SELSEARCHENTITY"></spring:message></label></br> <form:select path="category" style="width: 150px" class="combofield" id="category"
											onchange="getandSetDistrictList(this.value);">
											<form:option selected="selected" value="" label="--select--"></form:option>
											<form:option value="S" label="State Line Department" />
											<form:option value="D" label="District Line Department" />
											<form:option value="T" label="SubDistrict Line Department" />
											<form:option value="B" label="Block Line Department" />
											<form:option value="V" label="Village Line Department" />
										</form:select> <form:errors htmlEscape="true" path="category" class="errormsg"></form:errors></td>
								</tr>

								<tr>
									<td align="left" class="lblBold" id="td_district"><label><spring:message htmlEscape="true" code="Label.SOURCEDISTRICT"></spring:message></label><br /> <form:select path="districtCode" class="combofield" style="width:150px"
											id="ddSourceDistrict" onchange="getandSetSubDistrictList(this.value);getandSetBlockList(this.value)">
											<form:options itemValue="districtCode" itemLabel="districtNameEnglish"></form:options>
										</form:select>
										<%-- <span class="errormsg" id="ddSourceDistrict_error"><spring:message htmlEscape="true" 
															code="Error.SOURCEDISTRICT"></spring:message> </span> --%></td>
								</tr>

								<tr>
									<td class="lblBold" align="left" id="td_subdistrict"><label><spring:message htmlEscape="true" code="Label.SELECTSUBDISTRICT"></spring:message></label><br /> <form:select path="subdistrictNameEnglish" class="combofield"
											style="width: 150px" id="ddSourceSubDistrict" onchange="getandSetVillageList(this.value);">
											<form:options itemValue="subdistrictCode" itemLabel="subdistrictNameEnglish"></form:options>
										</form:select> <!-- <span class="error" id="ddSourceSubDistrict_error"></span> --></td>
								</tr>

								<tr>
									<td class="lblBold" align="left" id="td_village"><label><spring:message htmlEscape="true" code="Label.SELVILLAGE"></spring:message></label><br /> <form:select path="villageNameEnglish" class="combofield" style="width: 150px"
											id="ddvillage">
										</form:select> <!-- <span class="error" id="ddSourceSubDistrict_error"></span> --></td>
								</tr>

								<tr>
									<td class="lblBold" align="left" id="td_block"><label><spring:message htmlEscape="true" code="Label.SELBLOCK"></spring:message></label><br /> <form:select path="blockNameEnglish" class="combofield" style="width: 150px" id="ddblock">
										</form:select> <!-- <span class="error" id="ddSourceSubDistrict_error"></span> --></td>
								</tr>

								<%-- 								 <tr>
								<td width="14%">&nbsp;</td>
								<td width="86%" class="lblBold" align="left"><spring:message htmlEscape="true"  code="Label.SELECTSTATELINEDEPT"></spring:message><br />
									<form:select path="" class="combofield" style="width: 150px" id="ddDepartment" onchange="getDistrictLineDepartmentList(this.value);">
									           
									            <form:options itemValue="deptCode" itemLabel="deptName"></form:options>
									</form:select> 
							  </tr> --%>

								<tr>
									<td>&nbsp;</td>
								</tr>

								<%--<tr>
								<td width="14%">&nbsp;</td>
								<td class="lblBold" align="left"><spring:message htmlEscape="true"  code="Label.SELECTDISTLINEDEPT"></spring:message><br />
									           <form:select path="" style="width: 150px" class="combofield" id="dDepartment" onchange="getSubDistrictLineDepartmentList(this.value);">
									          <form:options itemValue="deptCode" itemLabel="deptName"></form:options> 
									           </form:select> </td>
							   </tr>
							   
							   <tr>
									<td>&nbsp;</td>
								</tr>
							   
							    <tr>
								<td width="14%">&nbsp;</td>
								<td class="lblBold" align="left"><spring:message htmlEscape="true"  code="Label.SELECTSUBDISTLINEDEPT"></spring:message><br />
									           <form:select path="" style="width: 150px" class="combofield" id="sdDepartment" onchange="getVillageLineDepartmentList(this.value);">
									           <form:options itemValue="deptCode" itemLabel="deptName"></form:options> 
									           </form:select> </td>
							   </tr>
							   
							   <tr>
									<td>&nbsp;</td>
								</tr>
							   
							   <tr>
								<td width="14%">&nbsp;</td>
								<td class="lblBold" align="left"><spring:message htmlEscape="true"  code="Label.SELECTVILLLINEDEPT"></spring:message><br />
									           <form:select path="" style="width: 150px" class="combofield" id="vDepartment">
									           <form:options itemValue="deptCode" itemLabel="deptName"></form:options> 
									           </form:select> </td>
							   </tr> --%>

								<tr>
									<td>&nbsp;</td>
								</tr>

								<tr>
									<!-- correctly aligned no. of records label 22/05/2012 -->
									<%--
	<td colspan="5" class="lblBold" align="right" >
		<label>
			<spring:message htmlEscape="true" code="Label.NOOFRECORDPERPAGE"></spring:message>
		<form:select htmlEscape="true" path="pageRows" class="combofield" style="width: 50px">
			<form:option value="5" label="5" />
			<form:option value="10" label="10" />
			<form:option value="25" label="25" selected="selected" />
			<form:option value="50" label="50" />
			<form:option value="100" label="100" />
		</form:select>
	   </label>
	</td>
 --%>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td height="50" colspan="4" align="center"><label> <input type="button" name="Submit" class="btn" onclick="excludeTopSelectAndSubmit('category');doSubmit('form1','Please select search category');"
											value=<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message> />
									</label><label> <input type="button" name="Submit2" class="btn" value=<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>
											onclick="javascript:location.href='vieworganization.htm?<csrf:token uri='vieworganization.htm'/>';" />
									</label><label> <input type="button" name="Submit3" class="btn" value=<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message> onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
									</label></td>
								</tr>
							</table>
						</div>
					</div>

					<%--   <div class="frmpnlbg" id='showbystatelinelevel' style=" visibility: hidden; display:none">
						<div class="frmtxt" align="center">
							<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.SELECTCRITERIA"></spring:message></div>
							<table width="400" class="tbl_no_brdr">

                                <tr>	
									<td align="left" class="lblBold"><label><spring:message htmlEscape="true"  code="Label.SELSEARCHENTITY"></spring:message></label></br>
									
											<form:select path="category" style="width: 150px" class="combofield" id="category" onchange="getandSetDistrictList(this.value);">
										                 <form:option value="N" label="-----------Select----------" />
												         <form:option value="S" label="State Line Department" />
												         <form:option value="D" label="District Line Department"  />
												         <form:option value="T" label="SubDistrict Line Department" />
												         <form:option value="B" label="Block Line Department" />
												         <form:option value="V" label="Village Line Department" />
											</form:select></td>
								     </tr>
								     
								     <tr>
												<td align="left" class="lblBold" id="td_district"><label><spring:message htmlEscape="true"  code="Label.SOURCEDISTRICT"></spring:message></label><br />
													<form:select path="districtCode" class="combofield" style="width:150px"
														id="ddSourceDistrict" onchange="getandSetSubDistrictList(this.value);getandSetBlockList(this.value)">
														<form:options itemValue="districtCode" itemLabel="districtNameEnglish"></form:options>
													</form:select><span class="errormsg" id="ddSourceDistrict_error"><spring:message htmlEscape="true" 
															code="Error.SOURCEDISTRICT"></spring:message> </span>
												</td>
									</tr>
									
									<tr>
								              <td class="lblBold" align="left" id="td_subdistrict"><label><spring:message htmlEscape="true"  code="Label.SELECTSUBDISTRICT"></spring:message></label><br />
												<form:select path="subdistrictNameEnglish" class="combofield" style="width: 150px" id="ddSourceSubDistrict" onchange="getandSetVillageList(this.value);">
												<form:options itemValue="subdistrictCode" itemLabel="subdistrictNameEnglish"></form:options>
												</form:select> <!-- <span class="error" id="ddSourceSubDistrict_error"></span> --></td>
							       </tr>
							       
							        <tr>
								              <td class="lblBold" align="left" id="td_village"><label><spring:message htmlEscape="true"  code="Label.SELECTVILLAGE"></spring:message></label><br />
												<form:select path="villageNameEnglish" class="combofield" style="width: 150px" id="ddvillage" >
												</form:select> <!-- <span class="error" id="ddSourceSubDistrict_error"></span> --></td>
							       </tr>
							       
							       <tr>
								              <td class="lblBold" align="left" id="td_block"><label><spring:message htmlEscape="true"  code="Label.SELBLOCK"></spring:message></label><br />
												<form:select path="blockNameEnglish" class="combofield" style="width: 150px" id="ddblock" >
												</form:select> <!-- <span class="error" id="ddSourceSubDistrict_error"></span> --></td>
							       </tr>

								 <tr>
								<td width="14%">&nbsp;</td>
								<td width="86%" class="lblBold" align="left"><spring:message htmlEscape="true"  code="Label.SELECTSTATELINEDEPT"></spring:message><br />
									<form:select path="" class="combofield" style="width: 150px" id="ddDepartment" onchange="getDistrictLineDepartmentList(this.value);">
									           
									            <form:options itemValue="deptCode" itemLabel="deptName"></form:options>
									</form:select> 
							  </tr>
								
								<tr>
									<td>&nbsp;</td>
								</tr>
								
							<tr>
								<td width="14%">&nbsp;</td>
								<td class="lblBold" align="left"><spring:message htmlEscape="true"  code="Label.SELECTDISTLINEDEPT"></spring:message><br />
									           <form:select path="" style="width: 150px" class="combofield" id="dDepartment" onchange="getSubDistrictLineDepartmentList(this.value);">
									          <form:options itemValue="deptCode" itemLabel="deptName"></form:options> 
									           </form:select> </td>
							   </tr>
							   
							   <tr>
									<td>&nbsp;</td>
								</tr>
							   
							    <tr>
								<td width="14%">&nbsp;</td>
								<td class="lblBold" align="left"><spring:message htmlEscape="true"  code="Label.SELECTSUBDISTLINEDEPT"></spring:message><br />
									           <form:select path="" style="width: 150px" class="combofield" id="sdDepartment" onchange="getVillageLineDepartmentList(this.value);">
									           <form:options itemValue="deptCode" itemLabel="deptName"></form:options> 
									           </form:select> </td>
							   </tr>
							   
							   <tr>
									<td>&nbsp;</td>
								</tr>
							   
							   <tr>
								<td width="14%">&nbsp;</td>
								<td class="lblBold" align="left"><spring:message htmlEscape="true"  code="Label.SELECTVILLLINEDEPT"></spring:message><br />
									           <form:select path="" style="width: 150px" class="combofield" id="vDepartment">
									           <form:options itemValue="deptCode" itemLabel="deptName"></form:options> 
									           </form:select> </td>
							   </tr>
								
								<tr>
									<td>&nbsp;</td>
								</tr>

								<tr>
	 <!-- correctly aligned no. of records label 22/05/2012 -->
	<td colspan="5" class="lblBold" align="right" >
		<label>
			<spring:message htmlEscape="true" code="Label.NOOFRECORDPERPAGE"></spring:message>
		<form:select htmlEscape="true" path="pageRows" class="combofield" style="width: 50px">
			<form:option value="5" label="5" />
			<form:option value="10" label="10" />
			<form:option value="25" label="25" selected="selected" />
			<form:option value="50" label="50" />
			<form:option value="100" label="100" />
		</form:select>
	   </label>
	</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td height="50" colspan="4" align="center"><label>
											<input type="button" name="Submit" class="btn" onclick="excludeTopSelectAndSubmit('category');doSubmit('form1','Please select search category');"  value=<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message> /> </label><label>
											<input type="button" name="Submit2" class="btn" value=<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message> onclick="doRef();" /> </label><label>
											<input type="button" name="Submit3"	class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"onclick="javascript:go('home.htm');" /> </label></td>
								</tr>
							</table>
						  </div>
					   </div> --%>

					<%--  <div class="frmpnlbg" id='lbSpecific'>
					            <div class="frmtxt" id='showbylbtlevel' style=" visibility: hidden; display:none">
									<div class="frmhdtitle">
											<spring:message htmlEscape="true"  code="Label.SELECTCRITERIA"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
										<tr>
										
											<td width=90% align="center">
											<input type="hidden" name="stateCode" value="${stateCode}"/>
											<label><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message></label><br />
													<form:select path="localBodyTypeCode" style="width: 140px" id="localBodyTypeCode"
															class="combofield" onchange="getLocalBodyList(this.value, stateCode.value)">
															<form:option value="0">
																<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
															</form:option>
															<form:options items="${localBodyType}" itemLabel="localBodyTypeName" itemValue="localBodyTypeCode" />
														</form:select>
												</td>
												
											</tr><td><br /></td>
											<tr>											
												<td align="center">
													<label>
													<input name="isLocalBodySpecifc" type="radio" value="N" onchange="isLB()" checked="checked"/> 
													</label>
													<label><spring:message htmlEscape="true"  code="Label.LBTYPESPECIFC"></spring:message></label>&nbsp;&nbsp;&nbsp;&nbsp;
													
													<label> 
													<input name="isLocalBodySpecifc" type="radio" id="Yes" value="Y" onchange="isLB()"/> 
													</label>
													<label><spring:message htmlEscape="true"  code="Label.LBSPECIFC"></spring:message></label>
												</td>
												
												<td>
													<spring:message htmlEscape="true"  code="Label.LBTYPESPECIFC"></spring:message>
												</td>
												
												<td>												
													<label> 
													<input name="isLocalBodySpecifc" type="radio" id="Yes" value="Y" onchange="isLB()"/> 
													</label>
													<spring:message htmlEscape="true"  code="Label.LBSPECIFC"></spring:message>
												</td>
												<td>
													<spring:message htmlEscape="true"  code="Label.LBSPECIFC"></spring:message>
												</td>
											</tr>
											<tr><td>&nbsp;</td></tr>
											<tr id="localBody">
											
											<td width=150 align="center"><label><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message></label><br />
													<form:select path="localBodyCode" style="width: 150px" id="localBodyCode" class="combofield">
															<form:option value="0">
																<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
															</form:option>
														</form:select>
												</td>
											  </tr>
											<tr>
								</tr>
								<tr>
									<td height="50" colspan="4" align="center"><label>
											<input type="button" name="Submit" class="btn" onclick="excludeTopSelectAndSubmit('localBodyTypeCode');doSubmit('form1','Please select local body');" value=<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message> /> </label><label>
											<input type="button" name="Submit2" class="btn" value=<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message> /> </label><label>
											<input type="button" name="Submit3"	class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"onclick="javascript:go('home.htm');" /> </label></td>
								</tr>
										</table>
									</div>
					             </div> --%>

				</div>

<%-- 				<c:if test="${! empty listOrganization}"> --%>
					<div class="frmpnlbg">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" rowspan="2"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></td>
												<td width="16%" rowspan="2"><spring:message htmlEscape="true" code="Label.ORGCODE"></spring:message></td>
												<td width="21%" rowspan="2"><spring:message htmlEscape="true" code="Label.ORGNAMEINENG"></spring:message></td>
												<%-- <td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.ORGSHORTNAMEINENG"></spring:message></td> --%>
												<%-- <td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.MINISTRYNAMEINENG"></spring:message></td> --%>
												<td colspan="6" align="center"><spring:message htmlEscape="true" code="Label.ACTION"></spring:message></td>
											</tr>
											<tr class="tblRowTitle tblclear">

												<td width="6%" align="center"><spring:message htmlEscape="true" code="Label.VIEW"></spring:message></td>
												<td width="6%" align="center"><spring:message htmlEscape="true" code="Label.MODIFY"></spring:message></td>
												<td width="6%" align="center"><spring:message htmlEscape="true" code="Label.DELETE"></spring:message></td>
												<%-- <td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.GOVTORDER"></spring:message></td>
												<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.HISTORY"></spring:message></td>
												<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.MAP"></spring:message></td> --%>
											</tr>

											<c:choose>
												<c:when test="${! empty  listOrganization}">
												<c:forEach var="listOrganization" varStatus="listOrganizationRow" items="${listOrganization}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listOrganizationRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${listOrganization.orgCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listOrganization.orgName}" escapeXml="true"></c:out></td>
														<%-- <td align="left"><c:out value="${listOrganization.shortName}"></c:out></td> --%>
														<%-- <td align="left"><c:out value="${listOrganization.parentorgcode}"></c:out></td> --%>
														<%--   <td align="center"><a href="viewOrganizationCenterDetail.htm?id=${listOrganization.orgCode}&<csrf:token uri="viewOrganizationCenterDetail.htm"/>"><img	src="images/view.png" width="18" height="19" border="0" /></a></td>
														   	<td align="center"><a href="modifyOrganizationCentral.htm?id=${listOrganization.orgCode}&<csrf:token uri="modifyOrganizationCentral.htm"/>"><img	src="images/edit.png" width="18" height="19" border="0" /></a></td>   
															<td align="center"><a href="abolishOrganization.htm?orgCode=${listOrganization.orgCode}&<csrf:token uri="abolishOrganization.htm"/>"><img src="images/delete.png" width="18" height="19" border="0" /></a></td> --%>
	
														<td width="8%" align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageOrganization('viewOrganizationCenterDetail.htm',${listOrganization.orgCode});" width="18" height="19" border="0" /></a></td>
														<td width="8%" align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageOrganization('modifyOrganizationCentral.htm',${listOrganization.orgCode});" width="18" height="19" border="0" /></a></td>
														<td width="8%" align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageOrganization('DeleteOrganizationCenterDetail.htm',${listOrganization.orgCode});" width="18" height="19" border="0" /></a></td>
	
	
														<%--  <td align="center"><a href="#"><img src="images/gvt.order.png" width="18" height="19" /></a></td>
															<td align="center"><a href="viewBlockHistory.htm?id=${listDepartment.blockCode}"><img	src="images/history.png" width="18" height="19" border="0" /></a></td>
															<td align="center"><a href="#"><img src="images/map.png" width="18" height="19" /></a></td> --%>
													</tr>
												</c:forEach>
												</c:when>
												<c:otherwise>
													<tr class="tblRowA"><td width="100%" align="center"><spring:message htmlEscape="true" code="Error.dialog-confirm-nodata"></spring:message></td></tr>
												</c:otherwise>
											</c:choose>
											<form:input path="organizationId" type="hidden" name="organizationId" id="organizationId" />
										</table>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<%-- 						
						<tr>
									<td height="30" align="right"><table width="301" class="tbl_no_brdr">
											<tr>
												<!-- <td width="99" align="right" class="pageno">(1 - 50 of 464)</td> -->
												<td width="96" align="right" class="pre"><a href="#"><spring:message htmlEscape="true"  code="Label.PREVIOUS"></spring:message></a></td>
												<td width="24" align="center" class="pageno">|</td>
												<td width="51" align="right" class="nxt tblclear"><a href="#"><spring:message htmlEscape="true"  code="Label.NEXT"></spring:message></a></td>
												<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr>
 --%>
							</table>
						</div>
					</div>
<%-- 				</c:if> --%>

				<%-- <c:if test="${fn:length(viewPage) > 0}"> 
					<c:if test="${empty listOrganization}">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">					
							<tr>
								<td colspan="4" align="center"><spring:message htmlEscape="true"  code="error.NOORGFOUND"></spring:message></td>
							</tr>					
						</table>
						</div>
					</div>
				 </c:if>
			    </c:if> --%>

			</form:form>
			<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>
</body>
</html>

