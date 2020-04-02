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
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>	
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>		
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>	
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/ministry.js"></script>
<script type="text/javascript">
/* function getData()
{
	if (document.getElementById('stateLine').value!='' || document.getElementById('ddMinistry').value!='')
		document.forms['form1'].submit();
	else
		alert('Please select search text!');
} */

function manageDepartment(url,id)
{
	dwr.util.setValue('departmentId', id, {	escapeHtml : false	});
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}

dwr.engine.setActiveReverseAjax(true);

function doRefresh(val)
{
	if (val)
		  removeSelectedValue('ddMinistry');
	else{
		
	}
}

 function removeSelectedValue(val)
{
  var elSel = document.getElementById(val);
  var i;
  for (i = elSel.length - 1; i>=0; i--) 
   	elSel.remove(i);
}

/* function removeSelectedValue(val)
{
	alert("Select State Wise Ministry List ");	
  var elSel = document.getElementById(val);
  var i;
  for (i = elSel.length - 1; i>=0; i--) 
   	elSel.remove(i);
}

function showHide()
{
	document.getElementById('chkBox').checked==true?document.getElementById('stateLine').disabled=true:document.getElementById('stateLine').disabled=false;
} */
/* function getData()
{
	document.getElementById('ddMinistry').value = trim(document.getElementById('ddMinistry').value);
	document.getElementById('text2').value = trim(document.getElementById('text2').value);
	
	if (document.getElementById('ddMinistry').value!='' || document.getElementById('text2').value!='')
		document.forms['form1'].submit();
	else
		alert('Please enter search text!');
}

function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
} */
function doRefreshLBType()
{

	document.getElementById('localBodyTypeCode').selectedIndex=0;
	 removeSelectedValue('localBodyCode');

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
	var st = document.getElementById('localBodyCode');
	st.add(new Option('---------Select----------', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

		
}
function handleLocalBodyError() {
		alert("No data found!");
}

function isLBSpecific(){

	document.getElementById('lbSpecific').style.display='none';
	if(document.getElementById('isLocalBodyTypeSpecifc').checked){
		document.getElementById('lbSpecific').style.display='block';
		document.getElementById('localBody').style.display='none';
	}
}
function isLB(){
	document.getElementById('localBody').style.display='none';
	if(document.getElementById('Yes').checked){
		document.getElementById('localBody').style.display='block';
	}
}
function doRefresh()
{
	document.getElementById('ddMinistry').value='';
	document.getElementById('stateLine').selectedIndex=0;
}

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
/* 	else
		{
		alert('Please enter search text!');
		} */
}
	else if(chkstate.checked)
	{
		document.getElementById('stateLine').value = trim(document.getElementById('stateLine').value);
		if (document.getElementById('stateLine').value!='')
		{
		document.forms['form1'].submit();
		}
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
	document.getElementById('lbSpecific').style.display='none';
	/* document.getElementById('localBody').style.display='none'; */
document.getElementById('chkcentre').checked=false;
document.getElementById('chkstate').checked=false;
document.getElementById('stateLine').checked=false;
if(document.getElementById('ddMinistry').value!='')
	{
	document.getElementById('chkcentre').checked=true;
	document.getElementById('chkstate').checked=false;
	}
else if(document.getElementById('stateLine').value!='')
	{
	document.getElementById('chkcentre').checked=false;
	document.getElementById('chkstate').checked=false;
	}
}
	
function doRef()
{
	document.getElementById('category').selectedIndex=0;
	removeSelectedValue('ddSourceDistrict');	
	removeSelectedValue('ddSourceSubDistrict');
	removeSelectedValue('ddvillage');
	removeSelectedValue('ddblock');
	getandSetDistrictList('S');
}	
	
	
/* function toggledisplaydept(obj,val)
{
	if (document.getElementById('divData')!=null)
		document.getElementById('divData').style.visibility='hidden';
	if (document.getElementById(obj).checked)
		{
			
			if (val=='showbycentrelevel')
				{
				    removeSelectedValue('stateLine');
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
 */
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
<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);" onload="loadPage()";>
		<div id="frmcontent">
			<div class="frmhd">
					<h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.VIEWDEPT"></spring:message></h3>
										 <ul id="showhelp" class="listing">
					 				      		<%--//these links are not working <li>
					 				      					<a href="#" rel="toggle[cat]"	data-openimage="images/minus.jpg"	data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /></a>
					 				      		
					 				      		</li>
					 				        
					 				        <li>
					 				        			<a href="#" class="frmhelp"><spring:message htmlEscape="true" 	code="Button.HELP"></spring:message> </a>
					 				        </li>
					 	 --%>
					 			        </ul>
				
			
				
		     </div>
			
			<div class="frmpnlbrdr">
				<form:form action="viewdepartmentbyministrycode.htm" id="form1" method="POST" commandName="viewDept">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewdepartmentbyministrycode.htm"/>"/>
	              <input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("stateCode")%>'/>
	              
	           
	
	
	
						<div class="frmtxt" align="center">
							
							<ul class="blocklist">
							
							<li>
										<label><spring:message htmlEscape="true" code="Label.SELMIN"></spring:message></label><span class="errormsg">*</span><br />
									           <form:select path="ministryName" style="width: 150px" class="combofield" id="ddMinistry">	
									           <form:option selected="selected" value="" label="--select--"></form:option>							           
									           <form:options items="${ministryList}" itemLabel="orgName" itemValue="organizationPK.orgCode"  />
									           </form:select> 
									           <form:errors path="ministryName" class="errormsg"></form:errors>
							
							</li>
													
							</ul>
							<div class="btnpnl">
										<label>
											<input type="submit" name="Submit"  class="btn"  onclick="getData();" value='<spring:message htmlEscape="true" code="Button.GETDATA"></spring:message>'/> </label><label>
											<input type="button" name="Submit2" class="btn" value=<spring:message htmlEscape="true" code="Button.CLEAR"></spring:message> onclick="doRefresh();" /> </label><label>
											<input type="button" name="Submit3"	class="btn" value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"/> </label>
									
							
							</div>
								<div class="clear"></div>
						  </div>
					   </div>
				
					
					
					
					<!-- State Line Department -->
		
					<div class="frmpnlbg" id='showbystatelevel' style=" visibility: hidden; display:none">
						<div class="frmtxt" align="center">
							<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.SELECTCRITERIA"></spring:message></div>
						
									<ul class="listing">
											<li>
												 <label>

												<input name="isLocalBodyTypeSpecifc" type="radio" id="stateLine" value="N" onclick='toggledisplaydeptbylevel("stateLine","showbystatelinelevel");'/>
		                                        </label>
											   <label> <spring:message htmlEscape="true"  code="Label.LINEDEPTLIST"></spring:message></label>
											</li>
											<li>
													  	<label>

														<input name="isLocalBodyTypeSpecifc" type="radio" id="isLocalBodyTypeSpecifc"  value="Y" onclick='isLBSpecific();toggledisplaydeptbylevel("isLocalBodyTypeSpecifc","showbylbtlevel");'/>
				                                        </label>
													    <label><spring:message htmlEscape="true"  code="Label.LBTDEPTLIST"></spring:message></label>
											</li>
									</ul>
						
								<div class="clear"></div>
						  </div>
					   </div>
					
					<div class="frmpnlbg" id='showbystatelinelevel' style=" visibility: hidden; display:none">
						<div class="frmtxt" align="center">
							<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.SELECTCRITERIA"></spring:message></div>
							
									<ul class="blocklist">
											<li>
																	<label><spring:message htmlEscape="true"  code="Label.SELSEARCHENTITY"></spring:message></label></br>
									
											<form:select path="" style="width: 150px" class="combofield" id="category" onchange="getandSetDistrictList(this.value);">
										                 <form:option value=""><form:option selected="selected" value="" label="--select--" /></form:option>
												         <form:option value="S" label="State Line Department" />
												         <form:option value="D" label="District Line Department"  />
												         <form:option value="T" label="SubDistrict Line Department" />
												         <form:option value="B" label="Block Line Department" />
												         <form:option value="V" label="Village Line Department" />
											</form:select>
											
											</li>
											<li id="td_district" >
														<label><spring:message htmlEscape="true"  code="Label.SOURCEDISTRICT"></spring:message></label><br />
													<form:select path="districtCode" class="combofield" style="width:150px" htmlEscape="true"
														id="ddSourceDistrict" onchange="getandSetSubDistrictList(this.value);getandSetBlockList(this.value)">
														<form:options itemValue="districtCode" itemLabel="districtNameEnglish"></form:options>
													</form:select>
											
											</li>
											
											<li id="td_subdistrict">
														<label><spring:message htmlEscape="true"  code="Label.SELECTSUBDISTRICT"></spring:message></label><br />
														<form:select path="subdistrictNameEnglish" class="combofield" style="width: 150px" id="ddSourceSubDistrict" onchange="getandSetVillageList(this.value);" htmlEscape="true">
														<form:options itemValue="subdistrictCode" itemLabel="subdistrictNameEnglish"></form:options>
														</form:select>
											</li>
											<li></li>
											
											<li  id="td_village">
														<label><spring:message htmlEscape="true"  code="Label.SELVILLAGE"></spring:message></label><br />
														<form:select path="villageNameEnglish" class="combofield" style="width: 150px" id="ddvillage" htmlEscape="true">
														</form:select> 
											</li>
											<li id="td_block">
														<label><spring:message htmlEscape="true"  code="Label.SELBLOCK"></spring:message></label><br />
														<form:select path="blockNameEnglish" class="combofield" style="width: 150px" id="ddblock" >
														</form:select>
											
											</li>
											<li></li>
									
									
									</ul>
									
									<div class="btnpnl">
											<label>
											<input type="submit" name="Submit" class="btn" onclick="excludeTopSelectAndSubmit('category');doSubmit('form1','Please select search category');"  value=<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message> /> </label><label>
											<input type="button" name="Submit2" class="btn" value=<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message> onclick="doRef();" /> </label><label>
											<input type="button" name="Submit3"	class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"/> </label>
							
									
									</div>
									
									<div class="clear"></div>
						  </div>
					   </div>
					
										   
                            <div class="frmpnlbg" id='lbSpecific'>
					            <div class="frmtxt" id='showbylbtlevel' style=" visibility: hidden; display:none">
									<div class="frmhdtitle">
											<spring:message htmlEscape="true"  code="Label.SELECTCRITERIA"></spring:message>
										</div>
										<ul class="blocklist">
											<li>
														<input type="hidden" name="stateCode" value="<c:out value='${stateCode}' escapeXml='true'></c:out>"/>
											<label><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message></label><br />
													<form:select path="localBodyTypeCode" style="width: 140px" id="localBodyTypeCode"
															class="combofield" onchange="getLocalBodyList(this.value, stateCode.value)">
															<form:option value="0">
																<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
															</form:option>
															<form:options items="${localBodyType}" itemLabel="localBodyTypeName" itemValue="localBodyTypeCode" />
														</form:select>
											</li>
											<li>
														<label>
													<input name="isLocalBodySpecifc" type="radio" value="N" onchange="isLB()" checked="checked"/> 
													</label>
													<label><spring:message htmlEscape="true"  code="Label.LBTYPESPECIFC"></spring:message></label>&nbsp;&nbsp;&nbsp;&nbsp;
													
													<label> 
													<input name="isLocalBodySpecifc" type="radio" id="Yes" value="Y" onchange="isLB()"/> 
													</label>
													<label><spring:message htmlEscape="true"  code="Label.LBSPECIFC"></spring:message></label>
											
											</li>
											<li id="localBody" >
														<label><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message></label><br />
													<form:select path="localBodyCode" style="width: 150px" id="localBodyCode" class="combofield">
															<form:option value="0">
																<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
															</form:option>
														</form:select>
											
											</li>
											
										
										</ul>
										<div class="btnpnl">
													<label>
											<input type="button" name="Submit" class="btn" onclick="excludeTopSelectAndSubmit('localBodyTypeCode');doSubmit('form1','Please select local body');" value=<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message> /> </label><label>
											<input type="button" name="Submit2" class="btn" value=<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message> onClick="doRefreshLBType()" /> </label><label>
											<input type="button" name="Submit3"	class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
										
										</div>
										
										<div class="clear"></div>
										
									</div>
					             </div>
									
					
				 <c:if test="${! empty listDepartment}">			
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
												<td width="16%" rowspan="2"><spring:message htmlEscape="true" 	code="Label.DEPTCODE"></spring:message></td>
												<td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message></td>
											
												<td colspan="6" align="center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></td>
								</tr>
								<tr class="tblRowTitle tblclear">

												<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></td>
												<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.MODIFY"></spring:message></td> 
												<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></td>
												
								</tr>

								
									   <c:forEach var="listDepartment" varStatus="listDepartmentRow" items="${listDepartment}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listDepartmentRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${listDepartment.orgCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listDepartment.orgName}" escapeXml="true"></c:out></td>
														
													   	<td  width="8%" align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageDepartment('viewDepartment.htm',${listDepartment.orgCode});" width="18" height="19" border="0" /></a></td>
														<td  width="8%" align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageDepartment('modifyDepartment.htm',${listDepartment.orgCode});" width="18" height="19" border="0" /></a></td>
														<td  width="8%" align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageDepartment('Deletedepartmentforcenter.htm',${listDepartment.orgCode});" width="18" height="19" border="0" /></a></td>
													   
													
													</tr>
										</c:forEach>	
										<form:input path="departmentId" type="hidden" name="departmentId" id="departmentId"  />																							
										</table>
									</td>
								</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
				
						
						
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
							</table>
						 </div>
					   </div>
				   	</c:if>

				   	
			  	 <c:if test="${! empty listDept}">			
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
												<td width="16%" rowspan="2"><spring:message htmlEscape="true" 	code="Label.DEPTCODE"></spring:message></td>
												<td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message></td>
												
												<%-- <td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.DEPTSHORTNAMEINENG"></spring:message></td>
												<td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.MINISTRYNAMEINENG"></spring:message></td> --%>
												<%-- <td colspan="6" align="center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></td> --%>
								</tr>
								 <tr class="tblRowTitle tblclear">

												<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></td>
												<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.MODIFY"></spring:message></td>
												<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></td>

								</tr>											
									
									<c:forEach var="listDept" varStatus="listDepartmentRow" items="${listDept}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listDepartmentRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${listDept.orgCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listDept.orgName}" escapeXml="true"></c:out></td>
													
     			                               
     			                          
														<td  width="8%" align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageDepartment('viewDepartment.htm',${listDept.orgCode});" width="18" height="19" border="0" /></a></td>
														<td  width="8%" align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageDepartment('modifyDepartment.htm',${listDept.orgCode});" width="18" height="19" border="0" /></a></td>
														<td  width="8%" align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageDepartment('Deletedepartmentforcenter.htm',${listDept.orgCode});" width="18" height="19" border="0" /></a></td>
														
													
													</tr>
												</c:forEach>
											<form:input path="departmentId" type="hidden" name="departmentId" id="departmentId"  />													
										</table>
									</td>
								</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						
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
							</table>
						 </div>
					   </div>
				   	</c:if>
			
			    
			    
			     <c:if test="${! empty listDistDept}">			
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
												<td width="16%" rowspan="2"><spring:message htmlEscape="true" 	code="Label.DEPTCODE"></spring:message></td>
												<td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message></td>
												
												<%-- <td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.DEPTSHORTNAMEINENG"></spring:message></td> --%>
												<%-- <td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.MINISTRYNAMEINENG"></spring:message></td> --%>
												<%-- <td colspan="6" align="center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></td> --%>
								</tr>
								<tr class="tblRowTitle tblclear">

												<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></td>
												<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.MODIFY"></spring:message></td>
												<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></td>
												<%--<td width="6%" align="center"><spring@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@:message htmlEscape="true"  code="Label.GOVTORDER"></spring:message></td>
												<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.HISTORY"></spring:message></td>
												<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.MAP"></spring:message></td> --%>
								</tr>	
									<c:forEach var="listDistDept" varStatus="listDepartmentRow" items="${listDistDept}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listDepartmentRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${listDistDept.orgCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listDistDept.orgName}" escapeXml="true"></c:out></td>
														<%-- <td align="left"><c:out value="${listDepartment.shortName}"></c:out></td>
													   <td align="left"><c:out value="${listDepartment.parentorgcode}"></c:out></td>  --%>
<%--      			                                   <td align="center"><a href="viewDepartment.htm?id=${listDistDept.orgCode}&<csrf:token uri="viewDepartment.htm"/>"><img src="images/view.png" width="18" height="19" border="0" /></a></td>
 --%>													
													   <td  width="8%" align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageDepartment('viewDepartment.htm',${listDistDept.orgCode});" width="18" height="19" border="0" /></a></td>
													
											<%-- 	   <td align="center"><a href="modifyDepartment.htm?id=${listDistDept.orgCode}&<csrf:token uri="modifyDepartment.htm"/>"><img	src="images/edit.png" width="18" height="19" border="0" /></a></td>   
											 --%>
											 	 	   <td  width="8%" align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageDepartment('modifyDepartment.htm',${listDistDept.orgCode});" width="18" height="19" border="0" /></a></td>
											 			
											 	<%--   <td align="center"><a href="abolishDepartment.htm?orgCode=${listDistDept.orgCode}&<csrf:token uri="abolishDepartment.htm"/>"><img src="images/delete.png" width="18" height="19" border="0" /></a></td> 
 --%>													
 						  							   <td  width="8%" align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageDepartment('Deletedepartmentforcenter.htm',${listDistDept.orgCode});" width="18" height="19" border="0" /></a></td>
 														<!--  <td align="center"><a href="#"><img src="images/gvt.order.png" width="18" height="19" /></a></td> -->
														<%-- <td align="center"><a href="viewBlockHistory.htm?id=${listDepartment.blockCode}"><img	src="images/history.png" width="18" height="19" border="0" /></a></td> --%>
														<!-- <td align="center"><a href="#"><img src="images/map.png" width="18" height="19" /></a></td> -->
													</tr>
												</c:forEach>
												<form:input path="departmentId" type="hidden" name="departmentId" id="departmentId"  />												
										</table>
									</td>
								</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						
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
							</table>
						 </div>
					   </div>
				   	</c:if>
			
			    
			    
			    <c:if test="${! empty listLBTWiseDeptList}">			
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
												<td width="16%" rowspan="2"><spring:message htmlEscape="true" 	code="Label.DEPTCODE"></spring:message></td>
												<td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message></td>
												
												<td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.DEPTSHORTNAMEINENG"></spring:message></td>
												<%-- <td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.MINISTRYNAMEINENG"></spring:message></td> --%>
												<td colspan="6" align="center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></td>
								</tr>
								<tr class="tblRowTitle tblclear">

												<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></td>
												 <td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.MODIFY"></spring:message></td>
												<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></td>
												<%-- <td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.GOVTORDER"></spring:message></td>
												<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.HISTORY"></spring:message></td>
												<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.MAP"></spring:message></td>  --%>
								</tr> 											
									
									<c:forEach var="listLBTWiseDeptList" varStatus="listDepartmentRow" items="${listLBTWiseDeptList}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listDepartmentRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${listLBTWiseDeptList.orgCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listLBTWiseDeptList.orgName}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listLBTWiseDeptList.shortOrgName}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listDepartment.parentorgcode}" escapeXml="true"></c:out></td>
     			                                     <%--   <td align="center"><a href="viewDepartment.htm?id=${listLBTWiseDeptList.orgCode}&<csrf:token uri="viewDepartment.htm"/>"><img src="images/view.png" width="18" height="19" border="0" /></a></td>
											 --%>
													   <td  width="8%" align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageDepartment('viewDepartment.htm',${listLBTWiseDeptList.orgCode});" width="18" height="19" border="0" /></a></td>
							<%-- 					   <td align="center"><a href="modifyDepartment.htm?id=${listLBTWiseDeptList.orgCode}&<csrf:token uri="modifyDepartment.htm"/>"><img src="images/edit.png" width="18" height="19" border="0" /></a></td>  
							 --%>						 
							 						  <td  width="8%" align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageDepartment('modifyDepartment.htm',${listLBTWiseDeptList.orgCode});" width="18" height="19" border="0" /></a></td>
							
							<%-- 					  <td align="center"><a href="abolishDepartment.htm?orgCode=${listLBTWiseDeptList.orgCode}&<csrf:token uri="abolishDepartment.htm"/>"><img src="images/delete.png" width="18" height="19" border="0" /></a></td> 
							 --%>						
							 						  <td  width="8%" align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageDepartment('Deletedepartmentforcenter.htm',${listLBTWiseDeptList.orgCode});" width="18" height="19" border="0" /></a></td>
							
													
													</tr>
												</c:forEach>
											<form:input path="departmentId" type="hidden" name="departmentId" id="departmentId"  />													
										</table>
									</td>
								</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						
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
							</table>
						 </div>
					   </div>
				   	</c:if>
				 
				</form:form>
				
				
				<script src="/LGD/JavaScriptServlet"></script>		
			</div>
		
		
				
</body>
</html>

