<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

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
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>	
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>		
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>
<!-- jquery pagination  -->
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/datatable/jquery.dataTables.js"></script>
	<script type="text/javascript" language="javascript" src="<%=contextpthval%>/datatable/jquery-ui-1.8.7.custom.min.js"></script>
<script src="<%=contextpthval%>/datatable/TableTools.js" charset="utf-8" type="text/javascript"></script>
	<link href="<%=contextpthval%>/datatable/jquery-ui-1.8.7.custom.css" rel="stylesheet"  type="text/css" />
	<link href="<%=contextpthval%>/datatable/demo_table_jui.css" rel="stylesheet"  type="text/css" />
<!-- jquery pagination  -->

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/ministry.js"></script>
<script type="text/javascript">


//jquery pagination  
$(document).ready(function() {
	$('#OrganizationStateLevel').dataTable({
		"sScrollY": "100px",
		"bScrollCollapse": true,
		"bPaginate": true,
		"aoColumnDefs": [
			{ "sWidth": "10%","sHeight":"20%", "aTargets": [ -1 ] }
		],
		"bJQueryUI": true,
		"aaSorting": [],
		"aoColumns": [
						null,
						null,
						null,
						{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false }
			  			
			  			],
		"sPaginationType": "full_numbers"
	});
} );
//jquery pagination  

//jquery pagination  
$(document).ready(function() {
	$('#OrganizationLowLevel').dataTable({
		"sScrollY": "100px",
		"bScrollCollapse": true,
		"bPaginate": true,
		"aoColumnDefs": [
			{ "sWidth": "10%","sHeight":"20%", "aTargets": [ -1 ] }
		],
		"bJQueryUI": true,
		"aaSorting": [],
		"aoColumns": [
						null,
						null,
						null,
						{ "bSortable": false },
			  			{ "bSortable": false }
			  		],
		"sPaginationType": "full_numbers"
	});
} );
//jquery pagination  


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
	hideAll();
	document.getElementById('td_adminunit').style.display='none';
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

function hideAll()
{
	
	//alert("hide");
	$("#error_category").hide();
	$("#error_td_district").hide();
 	$("#error_td_subdistrict").hide();
	$("#error_td_village").hide();
	$("#error_td_block").hide();
	$("#error_td_adminunit").hide();
	
	
	
}

if ( window.addEventListener ) { 
    window.addEventListener( "load",loadPage, false );
 }
 else 
    if ( window.attachEvent ) { 
       window.attachEvent( "onload", loadPage );
 } else 
       if ( window.onLoad ) {
          window.onload = loadPage;
 }
</script>

</head>
<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);" onload="loadPage()";>
		<div id="frmcontent">
			<div class="frmhd">
			
                   <h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.VIEWORG"></spring:message></h3>
                   <ul class="listing">
                   <li>
                   <a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a>
                   </li>
                 <%--//this link is not working    <li>
                   <a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a>
               </li> --%>
               </ul>
		     </div>
			<div class="clear"></div>		
			<div class="frmpnlbrdr">
				<form:form action="vieworganizationbydepartmentcodeforState.htm" id="form1" method="POST" commandName="viewDeptforstate">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="vieworganizationbydepartmentcode.htm"/>"/>
				<div id="cat">	
				 <input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("stateCode")%>'/>
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
                                                     <form:radiobutton path="selectLevel" value="" id='chkstate' onclick='toggledisplaydept("chkstate","showbystatelevel");' />
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
					
					<div class="frmpnlbg" id='showbycentrelevel' style=" visibility: hidden; display:none">
						<div class="frmtxt" align="center">
							<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.SELECTCRITERIA"></spring:message></div>
								<div class="block">
									<ul class="blocklist">
										<li>
										     <label for="ddMinistry"><spring:message  htmlEscape="true"  code="Label.SELMIN"></spring:message></label><span class="errormsg">*</span><br />
											           <form:select path="ministryName" style="width: 150px" class="combofield" id="ddMinistry" onchange="getDepartmentListByMinistry(this.value)">
											            <form:option selected="selected" value="" label="--select--"></form:option>
											            <form:options items="${ministryList}" itemLabel="orgName" itemValue="organizationPK.orgCode"  />
											           </form:select> 
											           <form:errors htmlEscape="true"  path="ministryName" class="errormsg"></form:errors>
									   </li>
									   
									   <%-- 	<tr>
										<td width="24%" align="left">
											    <label> <form:checkbox name="checkbox" value="" id="chkBox" path="" onclick="getDepartmentList();showHide();doRefresh(!this.checked);" /></label>
											    <spring:message htmlEscape="true"  code="Label.SELECTALLSTATES"></spring:message>
											</td>								
										</tr> --%>
									   
									   <li>
										<label for="ddDepartment"><spring:message htmlEscape="true"  code="Label.SELDEPT"></spring:message></label><span class="errormsg">*</span><br />
											           <form:select path="deptName" style="width: 150px" class="combofield" id="ddDepartment">
											           <form:options items="${departmentList}" itemLabel="orgName" itemValue="organizationPK.orgCode"  />
											           </form:select> 
									   </li>
								   </ul>
							   </div>
								
							 
							<div class="btnpnl">
								<input type="submit" name="Submit" class="btn"  value=<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message> /> 
								<input type="button" name="Submit2" class="btn" value=<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message> onclick="doRef();" />
								<input type="button" name="Submit3"	class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
							</div>
						  </div>
					   </div>
					   
					   
					<!-- State Line Department -->
		
					<div class="frmpnlbg" id='showbystatelevel'>
						<div class="frmtxt">
							<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.SELECTCRITERIA"></spring:message></div>
							<div class="block">
								<ul class="blocklist">
	                                <li>
									
									<label for="ddDepartment"><spring:message htmlEscape="true"  code="Label.SELDEPT"></spring:message><span class="errormsg">*</span></label></br>
										           <form:select path="deptName" style="width: 150px" class="combofield" id="ddDepartment">
										           
										           <form:options items="${stateDeptList}" itemLabel="orgName" itemValue="organizationPK.orgCode"  />
										           </form:select> 
								   </li>
	                                
	                               <li>
										<label for="category"><spring:message htmlEscape="true"  code="Label.SELSEARCHENTITY"></spring:message><span class="errormsg">*</span></label></br>
										
												<form:select path="category" style="width: 150px" class="combofield" id="category" onchange="hideAll();getandSetDistrictListDP(this.value);">
											                 <form:option value=""><form:option selected="selected" value="" label="--select--" /></form:option>
													         <form:option value="S" label="State Level Organization" />
													         <form:option value="D" label="District Level Organization"  />
													         <form:option value="T" label="SubDistrict Level Organization" />
													         <form:option value="B" label="Block Level Organization" />
													         <form:option value="V" label="Village Level Organization" />
													         <form:option value="A" label="Administrative Unit Department" />
												</form:select>
												<form:errors htmlEscape="true"  path="category" class="errormsg"></form:errors>
												<div id="error_category" class="errormsg">	<spring:message htmlEscape="true" 
																code="errorMessage.notselectedoption"></spring:message> </div>
												
												
									     </li>
									     
									     <li id="td_district">
													<label for="ddSourceDistrict"><spring:message htmlEscape="true"  code="Label.SOURCEDISTRICT"></spring:message><span class="errormsg">*</span></label></br>
														<form:select path="districtCode" class="combofield" style="width:150px"
															id="ddSourceDistrict" onchange="hideAll();getandSetSubDistrictList(this.value);getandSetBlockList(this.value)">
															<form:options itemValue="districtCode" itemLabel="districtNameEnglish"></form:options>
														</form:select><%-- <span class="errormsg" id="ddSourceDistrict_error"><spring:message htmlEscape="true" 
																code="Error.SOURCEDISTRICT"></spring:message> </span> --%>
														<div id="error_td_district" class="errormsg">	<spring:message htmlEscape="true" 
																code="error.select.SELECTDISTRICTNAME"></spring:message> </div>		
													
										</li>
										
										<li id="td_subdistrict">
									              <label for="ddSourceSubDistrict"><spring:message htmlEscape="true"  code="Label.SELECTSUBDISTRICT"></spring:message><span class="errormsg">*</span></label></br>
													<form:select path="subdistrictNameEnglish" class="combofield" style="width: 150px" id="ddSourceSubDistrict" onchange="hideAll();getandSetVillageListDP(this.value);">
													<form:options itemValue="subdistrictCode" itemLabel="subdistrictNameEnglish"></form:options>
													</form:select> <!-- <span class="error" id="ddSourceSubDistrict_error"></span> -->
													<div id="error_td_subdistrict" class="errormsg">	<spring:message htmlEscape="true" code="error.select.SELECTSUBDISTRICTNAME"></spring:message> </div>
													
								       </li>
								       
								        <li id="td_village">
									              <label for="ddvillage"><spring:message htmlEscape="true"  code="Label.SELECTVILLAGE"></spring:message><span class="errormsg">*</span></label></br>
													<form:select path="villageNameEnglish" class="combofield" style="width: 150px" id="ddvillage" onchange="hideAll();" >
													</form:select> <!-- <span class="error" id="ddSourceSubDistrict_error"></span> -->
													 <div id="error_td_village" class="errormsg">	
													<spring:message htmlEscape="true" code="error.SELECTONEVILLAGE"></spring:message>															
																 </div>
													
								       </li>
								       
								       <li id="td_block">
									              <label for="ddblock"><spring:message htmlEscape="true"  code="Label.SELBLOCK"></spring:message><span class="errormsg">*</span></label></br>
													<form:select path="blockNameEnglish" class="combofield" style="width: 150px" id="ddblock" onchange="hideAll();" >
													</form:select>
													<div id="error_td_block" class="errormsg"> <spring:message htmlEscape="true" code="error.SELECTONEBLOCK"></spring:message></div>
													 <%-- <div id="error_td_block" class="errormsg">	<spring:message htmlEscape="true" 
																code="error.select.SELECTSUBDISTRICTNAME"></spring:message> </div>  --%><!-- <span class="error" id="ddSourceSubDistrict_error"></span> -->
													
								       </li>
										 <li id="td_adminunit">
											<label for="ddSourceAdminUnit"><spring:message htmlEscape="true"  code="Label.SELECTADMINUNITDEPT" text="Select Admin Unit"></spring:message><span class="errormsg">*</span></label></br>
														<form:select path="adminUnitCode" class="combofield" style="width:150px" htmlEscape="true"
															id="ddSourceAdminUnit" onchange="hideAll();">
															<form:options itemValue="adminUnitEntityCode" itemLabel="adminEntityNameEnglish"></form:options>
														</form:select>
														<div id="error_td_adminunit" class="errormsg">	<spring:message htmlEscape="true" 
																code="error.select.SELECTADMINUNITDEPTNAME" text="please select one of admin unit name"></spring:message> </div>
	 										</li>
									 
	
	 								</ul>
 								</div>
									
									<div class="btnpnl">
											<input type="submit" name="Submit" class="btn" onclick="return validate_modifyOrganization();"  value=<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message> /> 
											<input type="button" name="Submit2" class="btn" value=<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message> onclick="javascript:location.href='vieworganizationforstate.htm?<csrf:token uri='vieworganizationforstate.htm'/>';" /> 
											<input type="button" name="Submit3"	class="btn" value=<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message> onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> 
								</div>
						  </div>
					   </div>
					 </div>
				
<!--------------------------------------------------------------------- State Level data ----------------------------------------------------------------------------------------------------- -->
				<c:if test="${! empty listDeptstate}">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" cellpadding="0" cellspacing="0" border="0" class="display" align="center"
											id="OrganizationStateLevel">
											<thead>
												<tr class="tblRowTitle tblclear">
													<th rowspan="2"><spring:message
															htmlEscape="true" code="Label.SNO"></spring:message>
													</th>
													<th  rowspan="2"><spring:message
															htmlEscape="true" code="Label.ORGCODE"></spring:message>
													</th>
													<th rowspan="2"><spring:message
															htmlEscape="true" code="Label.ORGNAMEINENG"></spring:message>
													</th>
													<th colspan="3" align="center"><spring:message
															htmlEscape="true" code="Label.ACTION"></spring:message>
													</th>
												</tr>
												<tr class="tblRowTitle tblclear">

													<th align="center"><spring:message htmlEscape="true"
															code="Label.VIEW"></spring:message>
													</th>
													<th align="center"><spring:message htmlEscape="true"
															code="Label.MODIFY"></spring:message>
													</th>
													<th align="center"><spring:message htmlEscape="true"
															code="Label.DELETE"></spring:message>
													</th>

												</tr>
											</thead>


											<tbody>
												<c:forEach var="listDept" varStatus="listOrganizationRow"
													items="${listDeptstate}">
													<tr class="tblRowB">
														<td><c:out value="${listOrganizationRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${listDept.orgCode}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out value="${listDept.orgName}(${orgTypeName})" escapeXml="true"></c:out>
														</td>

														<td align="center"><a href="#"><img
																src="images/view.png"
																onclick="javascript:manageOrganization('viewOrganizationStateDetail.htm',${listDept.orgCode});"
																width="18" height="19" border="0" />
														</a>
														</td>
														<td align="center"><a href="#"><img
																src="images/edit.png"
																onclick="javascript:manageOrganization('modifyOrganizationState.htm',${listDept.orgCode});"
																width="18" height="19" border="0" />
														</a>
														</td>
														<td align="center"><a href="#"><img
																src="images/delete.png"
																onclick="javascript:manageOrganization('DeleteOrganizationState.htm',${listDept.orgCode});"
																width="18" height="19" border="0" />
														</a>
														</td>

													</tr>
												</c:forEach>
											</tbody>


										</table></td>
								</tr>
								<tr>
									<td><form:input path="organizationId" type="hidden"
											name="organizationId" id="organizationId" /></td>
								</tr>

								<%-- <tr>
									<td height="30" align="right"><table width="301"
											class="tbl_no_brdr">
											<tr>
												<!-- <td width="99" align="right" class="pageno">(1 - 50 of 464)</td> -->
												<td width="96" align="right" class="pre"><a href="#"><spring:message
															htmlEscape="true" code="Label.PREVIOUS"></spring:message>
												</a>
												</td>
												<td width="24" align="center" class="pageno">|</td>
												<td width="51" align="right" class="nxt tblclear"><a
													href="#"><spring:message htmlEscape="true"
															code="Label.NEXT"></spring:message>
												</a>
												</td>
												<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
											</tr>
										</table></td>
								</tr> --%>
							</table>
						</div>
					</div>
				</c:if>
				<!--  -----------------------------------------------------------------------end of state level ------------------------------------------------------------------------------------------------------->
			
<!--------------------------------------------------------------------- Low Level data ----------------------------------------------------------------------------------------------------- -->			
					
				 <c:if test="${! empty listDept}">			
					<div class="frmpnlbg">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" cellpadding="0" cellspacing="0" border="0" class="display" id="OrganizationLowLevel">
											<thead>
											<tr class="tblRowTitle tblclear">
												<th rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></th>
												<th rowspan="2"><spring:message htmlEscape="true" 	code="Label.ORGCODE"></spring:message></th>
												<th rowspan="2"><spring:message htmlEscape="true"  code="Label.ORGNAMEINENG"></spring:message></th>												
											
												<th colspan="2" align="center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></th>
											</tr>
											<tr class="tblRowTitle tblclear">

											    <th align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></th>
												<th align="center"><spring:message htmlEscape="true"  code="Label.MODIFY"></spring:message></th>
												
											</tr>
											
											</thead>
											
                                      <tbody>
                                      <c:forEach var="listDept" varStatus="listOrganizationRow" items="${listDept}">
													<tr class="tblRowB">
														<td ><c:out value="${listOrganizationRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${listDept.orgCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listDept.orgName}" escapeXml="true"></c:out></td>
													
														<td  align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageOrganization('viewOrganizationStateDetail.htm',${listDept.orgCode});" width="18" height="19" border="0" /></a></td>
														<td  align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageOrganization('modifyOrganizationState.htm',${listDept.orgCode});" width="18" height="19" border="0" /></a></td>
														
													</tr>
												</c:forEach>	
                                      </tbody>
								
									
																					
										</table>
									</td>
								</tr>
						<tr>
							<td><form:input path="organizationId" type="hidden" name="organizationId" id="organizationId"  />	</td>
						</tr>
						
						<%-- <tr>
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
								</tr> --%>
							</table>
						 </div>
					   </div>
				   	</c:if>
				   	
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

