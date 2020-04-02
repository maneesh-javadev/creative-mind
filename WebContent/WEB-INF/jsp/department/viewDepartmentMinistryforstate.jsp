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
	$('#modifystateDepartment').dataTable({
		"sScrollY": "300px",
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


$(document).ready(function() {
	$('#modifyDeparmentLowLevel').dataTable({
		"sScrollY": "300px",
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
// jquery pagination  

function manageDepartmentState(url,id)
{
	//alert(id);
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
	hideAll();
	document.getElementById('td_adminunit').style.display='none';
	document.getElementById('td_district').style.display='none';
	document.getElementById('td_subdistrict').style.display='none';
	document.getElementById('td_block').style.display='none';
	document.getElementById('td_village').style.display='none';
	document.getElementById('lbSpecific').style.display='none';
	document.getElementById('trAdminUnitLevel').style.display='none';
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
	
	
function toggledisplaydept(obj,val)
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
	<div id="frmcontent">
		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
                <tr>
                   <td><spring:message htmlEscape="true"  code="Label.VIEWDEPT"></spring:message></td>
                   <td align="right"><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a></td>
                 <%--//this link is not working   <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a></td> --%>
                </tr>
             </table>
		    </div>
		<div class="clear"></div>
	<div class="frmpnlbrdr">
	
	<form:form action="viewdepartmentbyministrycodeforstate.htm" id="form1" method="POST" commandName="viewDeptforstate">
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewdepartmentbyministrycodeforstate.htm"/>"/>
	     <input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("stateCode")%>'/>
	 <input type='hidden' id="specificLevel" name="SpecificLevel"  value="<c:out value='${category}' escapeXml='true'></c:out>"/>
	  
	     
	     	<div id="cat">	
				    	<%-- <div class="frmpnlbg">
						      <div class="frmtxt" align="center">
							     <div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.FILTEROPTFORVIEWDEPT"></spring:message></div>
						          <table width="800" class="tbl_no_brdr">
						          <div>
						             <td width="86%">
                                          <table width="900" height="21" class="tbl_no_brdr">
                                            <tr>
                                               <td class="tblclear">
                                                  <label>
                                                     <form:radiobutton path="selectLevel" id='chkcentre' onclick='toggledisplaydept("chkcentre","showbycentrelevel");'/>
                                                  </label>
                                               </td>
                                              <td width="85" class="lblBold"><label><spring:message htmlEscape="true"  code="Label.CENTRELEVEL"></spring:message></label></td>
                                             <!--   <td>&nbsp;&nbsp;</td>  -->
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
					</div>
				    </div>
	
	 --%>
	
	
		
<%-- 	<div class="frmpnlbg" id='showbycentrelevel' style=" visibility: hidden; display:none">
		<div class="frmtxt" align="center">
				<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.SELECTCRITERIA"></spring:message></div>
						<table width="800" class="tbl_no_brdr">

								
								<tr>
								  <td width="14%" rowspan="9">&nbsp;</td>
								  <td width="86%" class="lblBold" align="left"><spring:message htmlEscape="true"  code="Label.SELMIN"></spring:message><br />
									<form:select path="ministryName" class="combofield" style="width: 150px" id="stateLine">
									            <form:option value=""><form:option selected="selected" value="" label="--select--" /></form:option>
									            <form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
									</form:select> <span class="error" id="stateLine_error"></span>
							   </tr>
							   <tr>
								<td width="14%">&nbsp;</td>
								<td width="86%" class="lblBold" align="left"><spring:message htmlEscape="true"  code="Label.SELECTSTATE"></spring:message><br />
									<form:select path="stateNameEnglish" class="combofield" style="width: 150px" id="stateLine" onchange="getMinistryListbyState(this.value)">
									            <form:option value=""><form:option selected="selected" value="" label="--select--" /></form:option>
									            <form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
									</form:select> <span class="error" id="stateLine_error"></span>
							  </tr>
							   
							   <tr>
								<td width="24%" align="left">
									    <label> <form:checkbox name="checkbox" id="chkBox" value="" path="" onclick="getMinistryList();showHide();doRefresh(!this.checked);" /></label>
									    <spring:message htmlEscape="true"  code="Label.SELECTALLSTATES"></spring:message>
									</td>								
								</tr>
								
								<tr>
								<td width="14%">&nbsp;</td>
								<td class="lblBold" align="left"><label><spring:message htmlEscape="true" code="Label.SELMIN"></spring:message></label><span class="errormsg">*</span><br />
									           <form:select path="ministryName" style="width: 150px" class="combofield" id="ddMinistry">	
									            <form:option value=""><form:option selected="selected" value="" label="--select--" /></form:option>							           
									           <form:options items="${ministryList}" itemLabel="orgName" itemValue="organizationPK.orgCode"  />
									           </form:select> 
									           <form:errors path="ministryName" class="errormsg"></form:errors></td>
							   </tr>
								
							   <tr>
                                        <td width="14%">&nbsp;</td>
										<td align="left" class="lblBold"><spring:message htmlEscape="true"  code="Label.SELMIN"></spring:message><br />
											
											<form:select path="ministryName" class="combofield">
												<form:option value="0">
													      <form:option selected="selected" value="" label="--select--" />
												</form:option>
												<form:options items="${ministryList}" itemLabel="orgName" itemValue="organizationPK.orgCode"  />
											</form:select>
									   </td>
										<td width="56%"><form:errors path="ministryName" cssStyle="color:red"></form:errors></td>
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
											<input type="submit" name="Submit"  class="btn"  onClick="getData();" value='<spring:message htmlEscape="true" code="Button.GETDATA"></spring:message>'/> </label><label>
											<input type="button" name="Submit2" class="btn" value=<spring:message htmlEscape="true" code="Button.CLEAR"></spring:message> onclick="javascript:location.href='viewdepartmentforstate.htm?<csrf:token uri='viewdepartmentforstate.htm'/>';" /> </label><label>
											<input type="button" name="Submit3"	class="btn" value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>"onclick="javascript:go('home.htm');" /> </label></td>
								</tr>
							</table>
						  </div>
					   </div>
				 --%>
					
					
					
					<!-- State Line Department -->
		<%-- 
					<div class="frmpnlbg" id='showbystatelevel'>
						<div class="frmtxt" align="center">
							<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.SELECTCRITERIA"></spring:message></div>
							<table width="450" class="tbl_no_brdr">

								
								<tr>
								  <td width="14%" rowspan="9">&nbsp;</td>
								  <td width="86%" class="lblBold" align="left"><spring:message htmlEscape="true"  code="Label.SELMIN"></spring:message><br />
									<form:select path="ministryName" class="combofield" style="width: 150px" id="stateLine">
									            <form:option value=""><form:option selected="selected" value="" label="--select--" /></form:option>
									            <form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
									</form:select> <span class="error" id="stateLine_error"></span>
							   </tr>
							 
							   
							  <tr>
								     <td width=150 align="left">
									    <label> <form:checkbox  id="stateLine"  path="" value="" onclick="getStateLineDepartmentList(this.checked);" /></label>
									    <label>
                                                 <form:radiobutton path="" value="" id='stateLine' onclick='getStateLineDepartmentList(this.checked);toggledisplaydeptbylevel("stateLine","showbystatelinelevel");'/>
										<input name="isLocalBodyTypeSpecifc" type="radio" id="stateLine" value="N"  onclick='toggledisplaydeptbylevel("stateLine","showbystatelinelevel");'/>
                                        </label>
									   <label> <spring:message htmlEscape="true"  code="Label.LINEDEPTLIST"></spring:message></label>
									</td>	
									
									<td width=250 align="left">
									     <label>
                                                 <form:radiobutton path="" value="" id="isLocalBodyTypeSpecifc" onclick="isLBSpecific()"/>
											<input name="isLocalBodyTypeSpecifc" type="radio" id="isLocalBodyTypeSpecifc" value="Y" onclick='isLBSpecific();toggledisplaydeptbylevel("isLocalBodyTypeSpecifc","showbylbtlevel");'/>
                                        </label>
									    <label><spring:message htmlEscape="true"  code="Label.LBTDEPTLIST"></spring:message></label>
									</td>	
									
								</tr> 
								
								</br>
							</table>
						  </div>
					   </div> --%>
					
		<div class="frmpnlbg" id='showbystatelinelevel' >
				<div class="frmtxt" align="center">
						<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.SELECTCRITERIA"></spring:message></div>
							<table width="400" class="tbl_no_brdr">
								<tr>	
										<td align="left" class="lblBold"><label><spring:message htmlEscape="true"  code="Label.SELSEARCHENTITY"></spring:message></label><span class="errormsg">*</span></br>
											<form:select path="category" style="width: 150px" class="combofield" id="category" onchange="hideAll();getandSetDistrictListDPforAdminUnitLevel(this.value);" htmlEscape="true">
											      	<form:option selected="selected" value="" label="--select--" htmlEscape="true"></form:option>
												  	<form:option value="S" label="State Line Department" htmlEscape="true" />
												  	<form:option value="D" label="District Line Department" htmlEscape="true" />
												  	<form:option value="T" label="SubDistrict Line Department" htmlEscape="true"/>
													<form:option value="B" label="Block Line Department" htmlEscape="true" />
													<form:option value="V" label="Village Line Department" htmlEscape="true" />
													<form:option value="A" label="Administrative Unit Department" htmlEscape="true"  />
											</form:select>
											<div id="error_category" class="errormsg">	<spring:message htmlEscape="true" 
															code="errorMessage.notselectedoption"></spring:message> </div>
										</td>
								  </tr>
								  
								<tr>
										<td align="left" class="lblBold" id="td_district"><label><spring:message htmlEscape="true"  code="Label.SELECTDISTRICT"></spring:message></label><span class="errormsg">*</span><br />
													<form:select path="districtCode" class="combofield" style="width:150px" htmlEscape="true"
														id="ddSourceDistrict" onchange="hideAll();getandSetSubDistrictList(this.value);getandSetBlockList(this.value);">
														<form:options itemValue="districtCode" itemLabel="districtNameEnglish" htmlEscape="true"></form:options>
													</form:select>
													<div id="error_td_district" class="errormsg">	<spring:message htmlEscape="true" 
															code="error.select.SELECTDISTRICTNAME"></spring:message> </div>
													<%-- <span class="errormsg" id="ddSourceDistrict_error"><spring:message htmlEscape="true" 
															code="Error.SOURCEDISTRICT"></spring:message> </span> --%>
 										</td>
								 </tr>
								<tr>
								        <td class="lblBold" align="left" id="td_subdistrict"><label><spring:message htmlEscape="true"  code="Label.SELECTSUBDISTRICT"></spring:message></label><span class="errormsg">*</span><br />
												<form:select path="subdistrictNameEnglish" class="combofield" style="width: 150px" id="ddSourceSubDistrict" onchange="hideAll();getandSetVillageListDP(this.value);" htmlEscape="true">
												<form:options itemValue="subdistrictCode" itemLabel="subdistrictNameEnglish" htmlEscape="true"></form:options>
												</form:select> <!-- <span class="error" id="ddSourceSubDistrict_error"></span> -->
												<div id="error_td_subdistrict" class="errormsg">	<spring:message htmlEscape="true" 
															code="error.select.SELECTSUBDISTRICTNAME"></spring:message> </div>
										</td>
											
							      </tr>
							    <tr>
								              <td class="lblBold" align="left" id="td_village"><label><spring:message htmlEscape="true"  code="Label.SELECTVILLAGE"></spring:message></label><span class="errormsg">*</span><br />
												<form:select path="villageNameEnglish" class="combofield" style="width: 150px" onchange="hideAll();" id="ddvillage" htmlEscape="true">
												</form:select> <!-- <span class="error" id="ddSourceSubDistrict_error"></span> -->
												  <div id="error_td_village" class="errormsg">	<spring:message htmlEscape="true" 
															code="error.select.SELECTVILLAGENAME" text="please select one of village name"></spring:message> </div> 
																
											 </td>
											
							       </tr>
							     <tr>
								        <td class="lblBold" align="left" id="td_block"><label><spring:message htmlEscape="true"   code="Label.SELBLOCK"></spring:message></label><span class="errormsg">*</span><br />
												<form:select path="blockNameEnglish" class="combofield" style="width: 150px" id="ddblock" onchange="hideAll();" htmlEscape="true">
												</form:select> <!-- <span class="error" id="ddSourceSubDistrict_error"></span> -->
												  <div id="error_td_block" class="errormsg">	<spring:message htmlEscape="true" 
															code="error.select.SELECTBLOCKNAME" text=" please select one of block name"></spring:message> </div> 
															
										</td>
							      </tr>
							      
							      
							     <tr id="trAdminUnitLevel">
							     			<td>
							     					<label><spring:message htmlEscape="true"  code="label.select.admin.unit.level" ></spring:message></label><span class="errormsg">*</span><br />
							     					<form:select path="" id="adminUnitLevel" class="combofield" style="width:150px" htmlEscape="true" onchange="getAdminUnitListByLevelCode(this.value);">
							     					
							     					
							     					</form:select>
							     				
							     			
							     			</td> 
							     </tr>
							     <tr>
										<td align="left" class="lblBold" id="td_adminunit"><label><spring:message htmlEscape="true"  code="label.select.admin.unit.entity" ></spring:message></label><span class="errormsg">*</span><br />
													<form:select path="adminUnitCode" class="combofield" style="width:150px" htmlEscape="true"
														id="ddSourceAdminUnit" onchange="hideAll();">
														<form:options itemValue="adminUnitEntityCode" itemLabel="adminEntityNameEnglish" htmlEscape="true"></form:options>
													</form:select>
													<div id="error_td_adminunit" class="errormsg">	<spring:message htmlEscape="true" 
															code="error.select.SELECTADMINUNITDEPTNAME" text="please select one of admin unit name"></spring:message> </div>
													<%-- <span class="errormsg" id="ddSourceDistrict_error"><spring:message htmlEscape="true" 
															code="Error.SOURCEDISTRICT"></spring:message> </span> --%>
 										</td>
								 </tr>
								 <tr>
									<td>&nbsp;</td>
								 </tr>
								 <tr>
									<td>&nbsp;</td>
								 </tr>
								 <tr>
									<td>&nbsp;</td>
								 </tr>
								 <tr>
									<td height="50" colspan="4" align="center"><label>
											<input type="submit" name="Submit" class="btn" onclick="return validate_modifyDepartmnet();"  value=<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message> /> </label><label>
											<input type="button" name="Submit2" class="btn" value=<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message> onclick="javascript:location.href='viewdepartmentforstate.htm?<csrf:token uri='viewdepartmentforstate.htm'/>';" /> </label><label>
											<input type="button" name="Submit3"	class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"onclick="javascript:go('home.htm');" /> </label>
									</td>
								</tr>
							</table>
					</div>
		 </div>
		 <div class="frmpnlbg" id='lbSpecific'>
		 	<div class="frmtxt" id='showbylbtlevel' style=" visibility: hidden; display:none">
				<div class="frmhdtitle">
							<spring:message htmlEscape="true"  code="Label.SELECTCRITERIA"></spring:message>
				</div>
					<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width=90% align="center">
											<input type="hidden" name="stateCode" value="<c:out value='${stateCode}'  escapeXml='true'></c:out>"/>
											<label><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message></label><br />
											<form:select 	path="localBodyTypeCode" 
															style="width: 140px" 
															id="localBodyTypeCode"
															class="combofield" 
															onchange="getLocalBodyList(this.value, stateCode.value)" htmlEscape="true">
																		
																		<form:option value="0" htmlEscape="true">
																			<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
																		</form:option>
																		<form:options items="${localBodyType}" itemLabel="localBodyTypeName" itemValue="localBodyTypeCode" htmlEscape="true"/>
											</form:select>
									</td>
								</tr>
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
								</tr>
								<tr><td>&nbsp;</td></tr>
								<tr id="localBody">
										<td width=150 align="center"><label><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message></label><br />
											<form:select path="localBodyCode" style="width: 150px" id="localBodyCode" class="combofield" htmlEscape="true">
													<form:option value="0">
														<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
													</form:option>
												</form:select>
										</td>
								</tr>
								<tr>
										<td height="50" colspan="4" align="center"><label>
												<input type="button" name="Submit" class="btn" onclick="return validate_modifyDepartmnet();excludeTopSelectAndSubmit('localBodyTypeCode');doSubmit('form1','Please select local body');" value=<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message> /> </label><label>
												<input type="button" name="Submit2" class="btn" value=<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message> onclick="javascript:location.href='viewdepartmentforstate.htm?<csrf:token uri='viewdepartmentforstate.htm'/>';" /> </label><label>
												<input type="button" name="Submit3"	class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" 	onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
										</td>
								</tr>
						</table>
					</div>
				</div>
<!---------------	 Center level show details       ---------------------------------------------------------------------------------------------------------------------------> 			
				 <c:if test="${! empty listDepartment}">			
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table width="100%" height="20%" cellpadding="0" cellspacing="0" border="0" class="display" id="modifycenterDepartment">
												<thead>
													<tr class="tblRowTitle tblclear">
															<th width="5%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></th>
															<th width="16%" rowspan="2"><spring:message htmlEscape="true" 	code="Label.DEPTCODE"></spring:message></th>
															<th width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message></th>
															<th colspan="3" align="center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></th>
													</tr>
													<tr class="tblRowTitle tblclear">
															<th width="6%" align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></th>
															<th width="6%" align="center"><spring:message htmlEscape="true"  code="Label.MODIFY"></spring:message></th> 
															<th width="6%" align="center"><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></th>
													</tr>
												</thead>
												
												<tbody>
												  		<c:forEach var="listDepartment" varStatus="listDepartmentRow" items="${listDepartment}">
																<tr class="tblRowB">
																	<td width="6%"><c:out value="${listDepartmentRow.index+1}" escapeXml="true"></c:out></td>
																	<td><c:out value="${listDepartment.orgCode}" escapeXml="true"></c:out></td>
																	<td align="left"><c:out value="${listDepartment.orgName}" escapeXml="true"></c:out></td>
																	<td  width="8%" align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageDepartmentState('viewDepartment.htm',${listDepartment.orgCode});" width="18" height="19" border="0" /></a></td>
																	<td  width="8%" align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageDepartmentState('modifyDepartment.htm',${listDepartment.orgCode});" width="18" height="19" border="0" /></a></td>
																	<td  width="8%" align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageDepartmentState('Deletedepartmentforstate.htm',${listDepartment.orgCode});" width="18" height="19" border="0" /></a></td>
																	
																	
																</tr>
													</c:forEach>	
												</tbody>
										</table>
									</td>
								</tr>
								<tr>
									<td><form:input path="departmentId" type="hidden" name="departmentId" id="departmentId" htmlEscape="true" /></td>
								</tr>
								<tr>
									<td height="30" align="right">
											<table width="301" class="tbl_no_brdr">
													<tr>
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
<!---------------	End of Center level show details       ---------------------------------------------------------------------------------------------------------------------------> 
<!---------------	 State level show details       ---------------------------------------------------------------------------------------------------------------------------> 			   	
 				 <c:if test="${! empty listDept}">			
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td width="14%" align="center">
											<table width="100%" height="5%" cellpadding="0" cellspacing="0" border="0" class="display" id="modifystateDepartment">
													<thead>
															<tr class="tblRowTitle tblclear">
																<th width="5%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></th>
																<th width="16%" rowspan="2"><spring:message htmlEscape="true" 	code="Label.DEPTCODE"></spring:message></th>
																<th width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message></th>
																<th colspan="2" align="center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></th>
															</tr>
											 				<tr class="tblRowTitle tblclear">
																<th width="6%" align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></th>
																<th width="6%" align="center"><spring:message htmlEscape="true"  code="Label.MODIFY"></spring:message></th>
																 <th width="6%" align="center"><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></th> 
															
															</tr>		
													</thead>
													<tbody>
															<c:forEach var="listDept" varStatus="listDepartmentRow" items="${listDept}">
																	<tr class="tblRowB">
																		<td width="6%"><c:out value="${listDepartmentRow.index+1}" escapeXml="true"></c:out></td>
																		<td><c:out value="${listDept.orgCode}" escapeXml="true"></c:out></td>
																		<td align="left"><c:out value="${listDept.orgName}" escapeXml="true"></c:out></td>
																		<td  width="8%" align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageDepartmentState('viewDepartment.htm',${listDept.orgCode});" width="18" height="19" border="0" /></a></td>
																		<td  width="8%" align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageDepartmentState('modifyDepartment.htm',${listDept.orgCode});" width="18" height="19" border="0" /></a></td>
																	<td  width="8%" align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageDepartmentState('Deletedepartmentforstate.htm',${listDept.orgCode});" width="18" height="19" border="0" /></a></td> 	
																	</tr>
															</c:forEach>
													</tbody>
												</table>
											</td>
									</tr>
									<tr>
										<td><form:input path="departmentId" type="hidden" name="departmentId" id="departmentId" htmlEscape="true" /></td>
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
				   	
<!---------------	End of  State level show details       ---------------------------------------------------------------------------------------------------------------------------> 						
<!---------------	 District, Sub-district,Block and village level show details       ---------------------------------------------------------------------------------------------------------------------------> 		    
			     <c:if test="${! empty listDistDept}">			
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
											<table width="100%" height="10%" cellpadding="0" cellspacing="0" border="0" class="display" id="modifyDeparmentLowLevel">
												<thead>
														<tr class="tblRowTitle tblclear">
																		<th width="5%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></th>
																		<th width="16%" rowspan="2"><spring:message htmlEscape="true" 	code="Label.DEPTCODE"></spring:message></th>
																		<th width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message></th>
																		<th colspan="2" align="center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></th>
														</tr>
														<tr class="tblRowTitle tblclear">
							
																		<th width="6%" align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></th>
																		<th width="6%" align="center"><spring:message htmlEscape="true"  code="Label.MODIFY"></spring:message></th>
																		<%-- <th width="6%" align="center"><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></th> --%>
														</tr>	
												</thead>
												<tbody>
														<c:forEach var="listDistDept" varStatus="listDepartmentRow" items="${listDistDept}">
																<tr class="tblRowB">
																		<td width="6%"><c:out value="${listDepartmentRow.index+1}" escapeXml="true"></c:out></td>
																		<td><c:out value="${listDistDept.orgCode}" escapeXml="true"></c:out></td>
																		<td align="left"><c:out value="${listDistDept.orgName}" escapeXml="true"></c:out></td>
																		<td  width="8%" align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageDepartmentState('viewDepartment.htm',${listDistDept.orgCode});" width="18" height="19" border="0" /></a></td>
																		<td  width="8%" align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageDepartmentState('modifyDepartment.htm',${listDistDept.orgCode});" width="18" height="19" border="0" /></a></td>
																		<%-- <td  width="8%" align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageDepartmentState('Deletedepartmentforstate.htm',${listDistDept.orgCode});" width="18" height="19" border="0" /></a></td>	 --%>
															
																</tr>
														</c:forEach>
												</tbody>
										</table>
										
									</td>
								</tr>
								<tr>
									<td><form:input path="departmentId" type="hidden" name="departmentId" id="departmentId" htmlEscape="true"/> </td>
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
<!---------------End of	 District, Sub-district,Block and village level show details       ---------------------------------------------------------------------------------------------------------------------------> 
				
				<c:if test="${! empty listLBTWiseDeptList}">			
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table width="100%" height="20%" cellpadding="0" cellspacing="0" border="0" class="display" id="modifyLocalBodyLevelDepartment">
												<thead>
														<tr class="tblRowTitle tblclear">
																<td width="5%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
																<td width="16%" rowspan="2"><spring:message htmlEscape="true" 	code="Label.DEPTCODE"></spring:message></td>
																<td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message></td>
																<td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.DEPTSHORTNAMEINENG"></spring:message></td>
																<td colspan="3" align="center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></td>
														</tr>
														<tr class="tblRowTitle tblclear">
																<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></td>
																<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.MODIFY"></spring:message></td>
																<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></td>
														</tr>
												</thead>
												<tbody>
													<c:forEach var="listLBTWiseDeptList" varStatus="listDepartmentRow" items="${listLBTWiseDeptList}">
															<tr class="tblRowB">
																	<td width="6%"><c:out value="${listDepartmentRow.index+1}"  escapeXml="true"></c:out></td>
																	<td><c:out value="${listLBTWiseDeptList.orgCode}" escapeXml="true"></c:out></td>
																	<td align="left"><c:out value="${listLBTWiseDeptList.orgName}" escapeXml="true"></c:out></td>
																	<td align="left"><c:out value="${listLBTWiseDeptList.shortOrgName}" escapeXml="true"></c:out></td>
																	<td  width="8%" align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageDepartmentState('viewDepartment.htm',${listLBTWiseDeptList.orgCode});" width="18" height="19" border="0" /></a></td>
																	<td  width="8%" align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageDepartmentState('modifyDepartment.htm',${listLBTWiseDeptList.orgCode});" width="18" height="19" border="0" /></a></td>
																	<td  width="8%" align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageDepartmentState('Deletedepartmentforstate.htm',${listLBTWiseDeptList.orgCode});" width="18" height="19" border="0" /></a></td>	
															</tr>
													</c:forEach>
												</tbody>
												
										</table>
									</td>
								</tr>
						<tr>
							<td><form:input path="departmentId" type="hidden" name="departmentId" id="departmentId" htmlEscape="true" />	</td>
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
					<c:if test="${empty listLBTWiseDeptList}">
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">					
							<tr>
								<td colspan="4" align="center"><spring:message htmlEscape="true"  code="error.DEPARTMENTFOUND"></spring:message></td>
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

