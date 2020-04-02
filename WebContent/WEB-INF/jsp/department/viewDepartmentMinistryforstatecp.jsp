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
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>	
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>		
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>	
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>
	<!-- jquery pagination  -->

 <script src="resource/dashboard-resource/plugins/datatables/jquery-1.12.4.js"></script>
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
	<section class="content">
                <div class="row">
                    <section class="col-lg-12">
                   <form:form action="viewdepartmentbyministrycodeforstate.htm" id="form1" method="POST" commandName="viewDeptforstate" class="form-horizontal">
                    
				      	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewdepartmentbyministrycodeforstate.htm"/>"/>
	     				<input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("stateCode")%>'/>
	 					<input type='hidden' id="specificLevel" name="SpecificLevel"  value="<c:out value='${category}' escapeXml='true'></c:out>"/>
	 	              
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.VIEWDEPT"></spring:message></h3>
                                </div>
                                 <div class="box-header subheading">
                                    <h4 ><spring:message htmlEscape="true"  code="Label.SELECTCRITERIA"></spring:message></h4>
                                </div>
                        <div class="box-body">
                            <div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELSEARCHENTITY"></spring:message> <span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								<form:select path="category"  class="form-control" id="category" onchange="hideAll();getandSetDistrictListDPforAdminUnitLevel(this.value);" htmlEscape="true">
											      	<form:option selected="selected" value="" label="--select--" htmlEscape="true"></form:option>
												  	<form:option value="S" label="State Line Department" htmlEscape="true" />
												  	<%-- <form:option value="D" label="District Line Department" htmlEscape="true" />
												  	<form:option value="T" label="SubDistrict Line Department" htmlEscape="true"/>
													<form:option value="B" label="Block Line Department" htmlEscape="true" />
													<form:option value="V" label="Village Line Department" htmlEscape="true" />
													<form:option value="A" label="Administrative Unit Department" htmlEscape="true"  /> --%>
											</form:select>
											<div id="error_category" class="errormsg">	<spring:message htmlEscape="true" 
															code="errorMessage.notselectedoption"></spring:message> </div>
								  </div>
						</div>   
						
						<div class="form-group" id="td_district">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTDISTRICT"></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								<form:select path="districtCode" class="form-control"  htmlEscape="true"
														id="ddSourceDistrict" onchange="hideAll();getandSetSubDistrictList(this.value);getandSetBlockList(this.value);">
														<form:options itemValue="districtCode" itemLabel="districtNameEnglish" htmlEscape="true"></form:options>
													</form:select>
													<div id="error_td_district" class="errormsg">	<spring:message htmlEscape="true" 
															code="error.select.SELECTDISTRICTNAME"></spring:message> </div>
								  </div>
						</div>   
						
						
						<div class="form-group"  id="td_subdistrict">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTSUBDISTRICT"></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								<form:select path="subdistrictNameEnglish" class="form-control"  id="ddSourceSubDistrict" onchange="hideAll();getandSetVillageListDP(this.value);" htmlEscape="true">
												<form:options itemValue="subdistrictCode" itemLabel="subdistrictNameEnglish" htmlEscape="true"></form:options>
												</form:select> <!-- <span class="error" id="ddSourceSubDistrict_error"></span> -->
												<div id="error_td_subdistrict" class="errormsg">	<spring:message htmlEscape="true" 
															code="error.select.SELECTSUBDISTRICTNAME"></spring:message> </div>
								  </div>
						</div> 
						
						
						<div class="form-group" id="td_village">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTVILLAGE"></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								<form:select path="villageNameEnglish" class="form-control"  onchange="hideAll();" id="ddvillage" htmlEscape="true">
												</form:select> <!-- <span class="error" id="ddSourceSubDistrict_error"></span> -->
												  <div id="error_td_village" class="errormsg">	<spring:message htmlEscape="true" 
															code="error.select.SELECTVILLAGENAME" text="please select one of village name"></spring:message> </div> 
								  </div>
						</div> 
						
						<div class="form-group" id="td_block">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true"   code="Label.SELBLOCK"></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								<form:select path="blockNameEnglish" class="form-control"  id="ddblock" onchange="hideAll();" htmlEscape="true">
												</form:select> <!-- <span class="error" id="ddSourceSubDistrict_error"></span> -->
												  <div id="error_td_block" class="errormsg">	<spring:message htmlEscape="true" 
															code="error.select.SELECTBLOCKNAME" text=" please select one of block name"></spring:message> </div>  
								  </div>
						</div> 
						
						
						<div class="form-group" id="trAdminUnitLevel" >
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="label.select.admin.unit.level" ></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								   <form:select path="" id="adminUnitLevel" class="form-control"  htmlEscape="true" onchange="getAdminUnitListByLevelCode(this.value);">
							     				</form:select>
								  </div>
						</div>
						
						
						<div class="form-group" id="td_adminunit">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="label.select.admin.unit.entity" ></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								  <form:select path="adminUnitCode" class="form-control"  htmlEscape="true"
														id="ddSourceAdminUnit" onchange="hideAll();">
														<form:options itemValue="adminUnitEntityCode" itemLabel="adminEntityNameEnglish" htmlEscape="true"></form:options>
													</form:select>
													<div id="error_td_adminunit" class="errormsg">	<spring:message htmlEscape="true" 
															code="error.select.SELECTADMINUNITDEPTNAME" text="please select one of admin unit name"></spring:message> </div>
								  </div>
						</div>
						
              </div> 
             
          
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                   <button  type="submit" name="Submit" class="btn btn-info" onclick="return validate_modifyDepartmnet();"   > <spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message></button>
				   <button type="button" name="Submit2" class="btn btn-warning"  onclick="javascript:location.href='viewdepartmentforstate.htm?<csrf:token uri='viewdepartmentforstate.htm'/>';" > <spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button>
                   <button type="button" class="btn btn-danger " name="Cancel"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div> 
    </div>   
       
       <!-- -------------- --------------------------------->
       <div  id='lbSpecific'>
		 	<div  id='showbylbtlevel' style=" visibility: hidden; display:none">
				<div class="box-header with-border">
							<h3><spring:message htmlEscape="true"  code="Label.SELECTCRITERIA"></spring:message></h3>
				</div>
					
				<input type="hidden" name="stateCode" value="<c:out value='${stateCode}'  escapeXml='true'></c:out>"/>
				<div class="box-body">
				<div class="form-group">
				  <label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message></label>
				    <div class="col-sm-6">
				     <form:select 	path="localBodyTypeCode"   id="localBodyTypeCode" class="form-control"  onchange="getLocalBodyList(this.value, stateCode.value)" htmlEscape="true">
									<form:option value="0" htmlEscape="true"><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message></form:option>
									<form:options items="${localBodyType}" itemLabel="localBodyTypeName" itemValue="localBodyTypeCode" htmlEscape="true"/>
					</form:select>
				   </div>
				</div>
				
				<div class="form-group">
				  <label class="col-sm-3 control-label"></label>
				    <div class="col-sm-6">
				    <label class="radio-inline">
				         <input name="isLocalBodySpecifc" type="radio" value="N" onchange="isLB()" checked="checked"/><spring:message htmlEscape="true"  code="Label.LBTYPESPECIFC"></spring:message>
				    </label>
				      <label class="radio-inline">
				         <input name="isLocalBodySpecifc" type="radio" id="Yes" value="Y" onchange="isLB()"/> <spring:message htmlEscape="true"  code="Label.LBSPECIFC"></spring:message>
				    </label>
				   </div>
				</div>
					
			   <div class="form-group" id="localBody">
				  <label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message></label>
				    <div class="col-sm-6">
				      <form:select path="localBodyCode"  id="localBodyCode" class="form-control" htmlEscape="true">
													<form:option value="0">
														<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
													</form:option>
												</form:select>
				   </div>
				</div>
		</div>
		
	  <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                   <button type="button" name="Submit" class="btn btn-info" onclick="return validate_modifyDepartmnet();excludeTopSelectAndSubmit('localBodyTypeCode');doSubmit('form1','Please select local body');" ><spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message> </button>
				   <button type="button" name="Submit2" class="btn btn-warning"  onclick="javascript:location.href='viewdepartmentforstate.htm?<csrf:token uri='viewdepartmentforstate.htm'/>';" > <spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button>
                   <button type="button" class="btn btn-danger " name="Cancel"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div> 
       
	</div>
</div>

<!---------------	 Center level show details       ---------------------------------------------------------------------------------------------------------------------------> 			
				 <c:if test="${! empty listDepartment}">			
				<div class="box">
										<table class="table table-bordered table-striped dataTable no-footer"  id="modifycenterDepartment">
												<thead>
													<tr >
															<th  rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></th>
															<th  rowspan="2"><spring:message htmlEscape="true" 	code="Label.DEPTCODE"></spring:message></th>
															<th  rowspan="2"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message></th>
															<th colspan="3" align="center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></th>
													</tr>
													<tr >
															<th  align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></th>
															<th  align="center"><spring:message htmlEscape="true"  code="Label.MODIFY"></spring:message></th> 
															<th  align="center"><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></th>
													</tr>
												</thead>
												
												<tbody>
												  		<c:forEach var="listDepartment" varStatus="listDepartmentRow" items="${listDepartment}">
																<tr >
																	<td ><c:out value="${listDepartmentRow.index+1}" escapeXml="true"></c:out></td>
																	<td><c:out value="${listDepartment.orgCode}" escapeXml="true"></c:out></td>
																	<td align="left"><c:out value="${listDepartment.orgName}" escapeXml="true"></c:out></td>
																	<td   align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageDepartmentState('viewDepartment.htm',${listDepartment.orgCode});" width="18" height="19" border="0" /></a></td>
																	<td   align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageDepartmentState('modifyDepartment.htm',${listDepartment.orgCode});" width="18" height="19" border="0" /></a></td>
																	<td   align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageDepartmentState('Deletedepartmentforstate.htm',${listDepartment.orgCode});" width="18" height="19" border="0" /></a></td>
																	
																	
																</tr>
													</c:forEach>	
												</tbody>
										</table>
										
									<form:input path="departmentId" type="hidden" name="departmentId" id="departmentId" htmlEscape="true" />
								
											<table width="301" class="tbl_no_brdr">
													<tr>
															<td width="96" align="right" class="pre"><a href="#"><spring:message htmlEscape="true"  code="Label.PREVIOUS"></spring:message></a></td>
															<td width="24" align="center" class="pageno">|</td>
															<td width="51" align="right" class="nxt tblclear"><a href="#"><spring:message htmlEscape="true"  code="Label.NEXT"></spring:message></a></td>
															<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
													</tr>
											</table>
								</div>	
				  </c:if>
<!---------------	End of Center level show details       ---------------------------------------------------------------------------------------------------------------------------> 
       
       <!---------------	 State level show details       ---------------------------------------------------------------------------------------------------------------------------> 			   	
 				 <c:if test="${! empty listDept}">			
					<div class="box">
											<table class="table table-bordered table-striped dataTable no-footer"  id="modifystateDepartment">
													<thead>
															<tr >
																<th  rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></th>
																<th  rowspan="2"><spring:message htmlEscape="true" 	code="Label.DEPTCODE"></spring:message></th>
																<th rowspan="2"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message></th>
																<th colspan="2" align="center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></th>
															</tr>
											 				<tr >
																<th  align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></th>
																<th align="center"><spring:message htmlEscape="true"  code="Label.MODIFY"></spring:message></th>
																 <th align="center"><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></th> 
															
															</tr>		
													</thead>
													<tbody>
															<c:forEach var="listDept" varStatus="listDepartmentRow" items="${listDept}">
																	<tr >
																		<td ><c:out value="${listDepartmentRow.index+1}" escapeXml="true"></c:out></td>
																		<td><c:out value="${listDept.orgCode}" escapeXml="true"></c:out></td>
																		<td align="left"><c:out value="${listDept.orgName}" escapeXml="true"></c:out></td>
																		<td  align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageDepartmentState('viewDepartment.htm',${listDept.orgCode});" width="18" height="19" border="0" /></a></td>
																		<td   align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageDepartmentState('modifyDepartment.htm',${listDept.orgCode});" width="18" height="19" border="0" /></a></td>
																	<td   align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageDepartmentState('Deletedepartmentforstate.htm',${listDept.orgCode});" width="18" height="19" border="0" /></a></td> 	
																	</tr>
															</c:forEach>
													</tbody>
												</table>
						</div>			
					   <form:input path="departmentId" type="hidden" name="departmentId" id="departmentId" htmlEscape="true" />
				   	</c:if>
				   	
<!--------------	End of  State level show details       ---------------------------------------------------------- -->
       
<!---------------	 District, Sub-district,Block and village level show details       ---------------------------------------------------------------------------------------------------------------------------> 		    
			     <c:if test="${! empty listDistDept}">			
					<div class="box">
											<table class="table table-bordered table-striped dataTable no-footer" id="modifyDeparmentLowLevel">
												<thead>
														<tr >
																		<th  rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></th>
																		<th  rowspan="2"><spring:message htmlEscape="true" 	code="Label.DEPTCODE"></spring:message></th>
																		<th  rowspan="2"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message></th>
																		<th colspan="2" align="center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></th>
														</tr>
														<tr >
							
																		<th  align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></th>
																		<th  align="center"><spring:message htmlEscape="true"  code="Label.MODIFY"></spring:message></th>
																		<%-- <th width="6%" align="center"><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></th> --%>
														</tr>	
												</thead>
												<tbody>
														<c:forEach var="listDistDept" varStatus="listDepartmentRow" items="${listDistDept}">
																<tr >
																		<td ><c:out value="${listDepartmentRow.index+1}" escapeXml="true"></c:out></td>
																		<td><c:out value="${listDistDept.orgCode}" escapeXml="true"></c:out></td>
																		<td align="left"><c:out value="${listDistDept.orgName}" escapeXml="true"></c:out></td>
																		<td   align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageDepartmentState('viewDepartment.htm',${listDistDept.orgCode});" width="18" height="19" border="0" /></a></td>
																		<td  align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageDepartmentState('modifyDepartment.htm',${listDistDept.orgCode});" width="18" height="19" border="0" /></a></td>
																		<%-- <td  width="8%" align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageDepartmentState('Deletedepartmentforstate.htm',${listDistDept.orgCode});" width="18" height="19" border="0" /></a></td>	 --%>
															
																</tr>
														</c:forEach>
												</tbody>
										</table>
										
							</div>
	  			 	<form:input path="departmentId" type="hidden" name="departmentId" id="departmentId" htmlEscape="true"/> 
   				</c:if>
<!-- ---------------End of	 District, Sub-district,Block and village level show details       ---------------- -->
       <c:if test="${! empty listLBTWiseDeptList}">			
					<div class="box">
										<table class="table table-bordered table-striped dataTable no-footer" id="modifyLocalBodyLevelDepartment">
												<thead>
														<tr >
																<td  rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
																<td  rowspan="2"><spring:message htmlEscape="true" 	code="Label.DEPTCODE"></spring:message></td>
																<td  rowspan="2"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message></td>
																<td  rowspan="2"><spring:message htmlEscape="true"  code="Label.DEPTSHORTNAMEINENG"></spring:message></td>
																<td colspan="3" align="center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></td>
														</tr>
														<tr >
																<td  align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></td>
																<td  align="center"><spring:message htmlEscape="true"  code="Label.MODIFY"></spring:message></td>
																<td  align="center"><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></td>
														</tr>
												</thead>
												<tbody>
													<c:forEach var="listLBTWiseDeptList" varStatus="listDepartmentRow" items="${listLBTWiseDeptList}">
															<tr >
																	<td ><c:out value="${listDepartmentRow.index+1}"  escapeXml="true"></c:out></td>
																	<td><c:out value="${listLBTWiseDeptList.orgCode}" escapeXml="true"></c:out></td>
																	<td align="left"><c:out value="${listLBTWiseDeptList.orgName}" escapeXml="true"></c:out></td>
																	<td align="left"><c:out value="${listLBTWiseDeptList.shortOrgName}" escapeXml="true"></c:out></td>
																	<td   align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageDepartmentState('viewDepartment.htm',${listLBTWiseDeptList.orgCode});" width="18" height="19" border="0" /></a></td>
																	<td   align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageDepartmentState('modifyDepartment.htm',${listLBTWiseDeptList.orgCode});" width="18" height="19" border="0" /></a></td>
																	<td   align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageDepartmentState('Deletedepartmentforstate.htm',${listLBTWiseDeptList.orgCode});" width="18" height="19" border="0" /></a></td>	
															</tr>
													</c:forEach>
												</tbody>
												
										</table>
						</div>
					   <form:input path="departmentId" type="hidden" name="departmentId" id="departmentId" htmlEscape="true" />
				   	</c:if>
       
   
             
    </form:form>      
   </section>
   </div>
   </section>
<script src="/LGD/JavaScriptServlet"></script>
		</body>
</html>

