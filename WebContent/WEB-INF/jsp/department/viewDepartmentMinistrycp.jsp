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
		<section class="content">
       <div class="row">
          <section class="col-lg-12">
  <div class="box">
             
			
		                <div class="box-header with-border">
		                  <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.VIEWDEPT"></spring:message></h3>
		                </div>
		                
		                <div class="box-header subheading">
										<h4><spring:message code="Label.AdminUnitsDetails" htmlEscape="true" text="Details of Admin Unit Level"></spring:message></h4>
									</div>
									<div><font size="3"><c:out value="${successMsg}" escapeXml="true"></c:out></font></div>
			<div class="box-body">
			
			<form:form action="viewdepartmentbyministrycode.htm" id="form1" method="POST" commandName="viewDept" class="form-horizontal">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewdepartmentbyministrycode.htm"/>"/>
	              <input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("stateCode")%>'/>
			        
			        <div class="form-group">
						<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELMIN"></spring:message><span class="errormsg">*</span></label>
					        <div class="col-sm-6"> 
					            <form:select path="ministryName"  class="form-control" id="ddMinistry">	
										  <form:option selected="selected" value="" label="--select--"></form:option>							           
										  <form:options items="${ministryList}" itemLabel="orgName" itemValue="organizationPK.orgCode"  />
								</form:select> 
								<form:errors path="ministryName" class="errormsg"></form:errors>
						    </div>				           
					 </div>
			      
	   <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                  <button type="submit" name="Submit"  class="btn btn-info"  onclick="getData();" ><spring:message htmlEscape="true" code="Button.GETDATA"></spring:message> </button>
				   <button type="button" name="Submit2" class="btn btn-warning" onclick="doRefresh();" ><spring:message htmlEscape="true" code="Button.CLEAR"></spring:message> </button>
                  <button type="button" class="btn btn-danger " name="Cancel value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div>
			      
		 <div  id='showbystatelevel' style=" visibility: hidden; display:none">
							<div class="box-header subheading"><spring:message htmlEscape="true"  code="Label.SELECTCRITERIA"></spring:message></div>
						
			 <div class="form-group">
			   <label class="radio-inline">
                      <input name="isLocalBodyTypeSpecifc" type="radio" id="stateLine" value="N" onclick='toggledisplaydeptbylevel("stateLine","showbystatelinelevel");'/>
		                              <spring:message htmlEscape="true"  code="Label.LINEDEPTLIST"></spring:message> 
		         </label>
							
				  <label class="radio-inline">
                      <input name="isLocalBodyTypeSpecifc" type="radio" id="isLocalBodyTypeSpecifc"  value="Y" onclick='isLBSpecific();toggledisplaydeptbylevel("isLocalBodyTypeSpecifc","showbylbtlevel");'/>
		                              <spring:message htmlEscape="true"  code="Label.LBTDEPTLIST"></spring:message> 
		          </label>			
										
											
				</div>							
								
		</div>	      
			      
			    <div  id='showbystatelinelevel' style=" visibility: hidden; display:none">
					
					<div class="box-header subheading"><h4><spring:message htmlEscape="true"  code="Label.SELECTCRITERIA"></spring:message></h4></div>
						<div class="form-group"><label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELSEARCHENTITY"></spring:message></label></br>
							<div class="col-sm-6">
							  <form:select path=""  class="form-control" id="category" onchange="getandSetDistrictList(this.value);">
										                 <form:option value=""><form:option selected="selected" value="" label="--select--" /></form:option>
												         <form:option value="S" label="State Line Department" />
												         <form:option value="D" label="District Line Department"  />
												         <form:option value="T" label="SubDistrict Line Department" />
												         <form:option value="B" label="Block Line Department" />
												         <form:option value="V" label="Village Line Department" />
						       </form:select>
						   </div>
					   </div>
					   
						<div class="form-group" id="td_district" >
								<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SOURCEDISTRICT"></spring:message></label>
							<div class="col-sm-6">
								<form:select path="districtCode" class="form-control"  htmlEscape="true"
														id="ddSourceDistrict" onchange="getandSetSubDistrictList(this.value);getandSetBlockList(this.value)">
														<form:options itemValue="districtCode" itemLabel="districtNameEnglish"></form:options>
								</form:select>
							</div>		
						</div>
											
						<div  class="form-group" id="td_subdistrict">
								<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTSUBDISTRICT"></spring:message></label>
								<div class="col-sm-6">
									<form:select path="subdistrictNameEnglish" class="combofield" style="width: 150px" id="ddSourceSubDistrict" onchange="getandSetVillageList(this.value);" htmlEscape="true">
											<form:options itemValue="subdistrictCode" itemLabel="subdistrictNameEnglish"></form:options>
								    </form:select>
								</div>
						</div>
											
											
						<div class="form-group"  id="td_village">
							<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELVILLAGE"></spring:message></label>
							<div class="col-sm-6">
								<form:select path="villageNameEnglish" class="form-control"  id="ddvillage" htmlEscape="true">
								</form:select>
							</div>	 
						</div>
						
                            <div class="form-group" id="td_block">
									<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELBLOCK"></spring:message></label>
									<form:select path="blockNameEnglish" class="form-control"  id="ddblock" >
									</form:select>
											
							</div>
											
									
		<div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                  <button type="submit" name="Submit" class="btn btn-success" onclick="excludeTopSelectAndSubmit('category');doSubmit('form1','Please select search category');"  ><spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message></button>
				   <button type="button" name="Submit2" class="btn btn-warning"  onclick="doRef();" ><spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message> </button>
                  <button type="button" class="btn btn-danger " name="Cancel value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div>	
									
 </div>  
			      
			<div  id='lbSpecific'>
			 <div  id='showbylbtlevel' style=" visibility: hidden; display:none">
				<div class="box-header subheading"><spring:message htmlEscape="true"  code="Label.SELECTCRITERIA"></spring:message></div>
				<div class="form-group"><input type="hidden" name="stateCode" value="<c:out value='${stateCode}' escapeXml='true'></c:out>"/>
					<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message></label>
					<div class="col-sm-6">
						<form:select path="localBodyTypeCode"  id="localBodyTypeCode" class="form-control" onchange="getLocalBodyList(this.value, stateCode.value)">
							<form:option value="0"><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message></form:option>
							<form:options items="${localBodyType}" itemLabel="localBodyTypeName" itemValue="localBodyTypeCode" />
						</form:select>
					</div>	
				</div>
				
				<div class="form-group"><label class="radio-inline"><input name="isLocalBodySpecifc" type="radio" value="N" onchange="isLB()" checked="checked"/> 
							<spring:message htmlEscape="true"  code="Label.LBTYPESPECIFC"></spring:message></label>
													
					<label class="radio-inline"> <input name="isLocalBodySpecifc" type="radio" id="Yes" value="Y" onchange="isLB()"/> 
							<spring:message htmlEscape="true"  code="Label.LBSPECIFC"></spring:message></label>
											
				</div>
				
				<div class="form-group" id="localBody" ><label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message></label>
					<div class="col-sm-6"><form:select path="localBodyCode"  id="localBodyCode" class="form-control">
						<form:option value="0"><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message></form:option>
					     </form:select>
					</div>					
				</div>
											
		<div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                   <button type="button" name="Submit" class="btn btn-info" onclick="excludeTopSelectAndSubmit('localBodyTypeCode');doSubmit('form1','Please select local body');" ><spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message></button>
				   <button type="button" name="Submit2" class="btn btn-warning"  onClick="doRefreshLBType()" ><spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button>
                  <button type="button" class="btn btn-danger " name="Cancel value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div>							
				
										
			</div>
		</div>      
			      
			
			<c:if test="${! empty listDepartment}">			
					<div  id="divData">
						<div class="box-body">
							
								<table class="table table-bordered table-hover"  align="center">
											<tr >
												<td rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
												<td  rowspan="2"><spring:message htmlEscape="true" 	code="Label.DEPTCODE"></spring:message></td>
												<td  rowspan="2"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message></td>
											
												<td colspan="6" align="center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></td>
								</tr>
								<tr >

												<td  align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></td>
												<td  align="center"><spring:message htmlEscape="true"  code="Label.MODIFY"></spring:message></td> 
												<td  align="center"><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></td>
												
								</tr>

								
									   <c:forEach var="listDepartment" varStatus="listDepartmentRow" items="${listDepartment}">
													<tr>
														<td ><c:out value="${listDepartmentRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${listDepartment.orgCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listDepartment.orgName}" escapeXml="true"></c:out></td>
														
													   	<td   align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageDepartment('viewDepartment.htm',${listDepartment.orgCode});" width="18" height="19" border="0" /></a></td>
														<td   align="center"><%-- <a href="#"><img src="images/edit.png" onclick="javascript:manageDepartment('modifyDepartment.htm',${listDepartment.orgCode});" width="18" height="19" border="0" /></a> --%></td>
														<td  align="center"><%-- <a href="#"><img src="images/delete.png" onclick="javascript:manageDepartment('Deletedepartmentforcenter.htm',${listDepartment.orgCode});" width="18" height="19" border="0" /></a> --%></td>
													   
													
													</tr>
										</c:forEach>	
										<form:input path="departmentId" type="hidden" name="departmentId" id="departmentId"  />																							
										</table>
										
						<div class="box-footer">                    
		                    <a href="#"><spring:message htmlEscape="true"  code="Label.PREVIOUS"></spring:message></a>
		                    <a href="#"><spring:message htmlEscape="true"  code="Label.NEXT"></spring:message></a>
		                  </div>
										
						 </div>
					   </div>
				   	</c:if>      
			      
			      
			      <c:if test="${! empty listDept}">			
					<div id="divData">
						<div class="box-body">
							<table class="table table-bordered table-hover" >
											<tr >
												<td rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
												<td  rowspan="2"><spring:message htmlEscape="true" 	code="Label.DEPTCODE"></spring:message></td>
												<td  rowspan="2"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message></td>
												
												
								</tr>
								 <tr >

												<td  align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></td>
												<td  align="center"><spring:message htmlEscape="true"  code="Label.MODIFY"></spring:message></td>
												<td  align="center"><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></td>

								</tr>											
									
									<c:forEach var="listDept" varStatus="listDepartmentRow" items="${listDept}">
													<tr >
														<td><c:out value="${listDepartmentRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${listDept.orgCode}" escapeXml="true"></c:out></td>
														<td  align="left"><c:out value="${listDept.orgName}" escapeXml="true"></c:out></td>
														<td   align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageDepartment('viewDepartment.htm',${listDept.orgCode});" width="18" height="19" border="0" /></a></td>
														<td   align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageDepartment('modifyDepartment.htm',${listDept.orgCode});" width="18" height="19" border="0" /></a></td>
														<td   align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageDepartment('Deletedepartmentforcenter.htm',${listDept.orgCode});" width="18" height="19" border="0" /></a></td>
														
													
													</tr>
												</c:forEach>
											<form:input path="departmentId" type="hidden" name="departmentId" id="departmentId"  />													
										</table>
										
						<div class="box-footer">                    
		                   <a href="#"><spring:message htmlEscape="true"  code="Label.PREVIOUS"></spring:message></a>
		                    <a href="#"><spring:message htmlEscape="true"  code="Label.NEXT"></spring:message></a>
		                  </div>
						 </div>
					   </div>
				   	</c:if>
			      
			      
			      <c:if test="${! empty listDistDept}">			
					<div  id="divData">
						<div class="box-body">
							<table class="table table-bordered table-hover" >
											<tr >
												<td  rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
												<td rowspan="2"><spring:message htmlEscape="true" 	code="Label.DEPTCODE"></spring:message></td>
												<td  rowspan="2"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message></td>
												
								</tr>
								<tr >

												<td ><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></td>
												<td ><spring:message htmlEscape="true"  code="Label.MODIFY"></spring:message></td>
												<td ><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></td>
								</tr>	
									<c:forEach var="listDistDept" varStatus="listDepartmentRow" items="${listDistDept}">
													<tr >
														<td><c:out value="${listDepartmentRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${listDistDept.orgCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listDistDept.orgName}" escapeXml="true"></c:out></td>
													    <td  align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageDepartment('viewDepartment.htm',${listDistDept.orgCode});" width="18" height="19" border="0" /></a></td>
											 	 	   <td   align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageDepartment('modifyDepartment.htm',${listDistDept.orgCode});" width="18" height="19" border="0" /></a></td>
 						  							   <td  align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageDepartment('Deletedepartmentforcenter.htm',${listDistDept.orgCode});" width="18" height="19" border="0" /></a></td>
													</tr>
												</c:forEach>
												<form:input path="departmentId" type="hidden" name="departmentId" id="departmentId"  />												
										</table>
										
						<div class="box-footer">                    
			                   <a href="#"><spring:message htmlEscape="true"  code="Label.PREVIOUS"></spring:message></a>
			                    <a href="#"><spring:message htmlEscape="true"  code="Label.NEXT"></spring:message></a>
		                  </div>
						 </div>
					   </div>
				   	</c:if>
			      
			      <c:if test="${! empty listLBTWiseDeptList}">			
					<div  id="divData">
						<div class="box-body">
							<table class="table table-bordered table-hover">
											<tr >
												<td  rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
												<td  rowspan="2"><spring:message htmlEscape="true" 	code="Label.DEPTCODE"></spring:message></td>
												<td  rowspan="2"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message></td>
												<td  rowspan="2"><spring:message htmlEscape="true"  code="Label.DEPTSHORTNAMEINENG"></spring:message></td>
												<td colspan="6" align="center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></td>
								</tr>
								<tr >

									<td  align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></td>
									<td  align="center"><spring:message htmlEscape="true"  code="Label.MODIFY"></spring:message></td>
									<td  align="center"><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></td>
								
								</tr> 											
									
									<c:forEach var="listLBTWiseDeptList" varStatus="listDepartmentRow" items="${listLBTWiseDeptList}">
													<tr >
														<td width="6%"><c:out value="${listDepartmentRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${listLBTWiseDeptList.orgCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listLBTWiseDeptList.orgName}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listLBTWiseDeptList.shortOrgName}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listDepartment.parentorgcode}" escapeXml="true"></c:out></td>
													    <td  width="8%" align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageDepartment('viewDepartment.htm',${listLBTWiseDeptList.orgCode});" width="18" height="19" border="0" /></a></td>
							 						    <td  width="8%" align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageDepartment('modifyDepartment.htm',${listLBTWiseDeptList.orgCode});" width="18" height="19" border="0" /></a></td>
							 						    <td  width="8%" align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageDepartment('Deletedepartmentforcenter.htm',${listLBTWiseDeptList.orgCode});" width="18" height="19" border="0" /></a></td>
													</tr>
												</c:forEach>
											<form:input path="departmentId" type="hidden" name="departmentId" id="departmentId"  />													
										</table>
							<div class="box-footer">                    
			                   <a href="#"><spring:message htmlEscape="true"  code="Label.PREVIOUS"></spring:message></a>
			                   <a href="#"><spring:message htmlEscape="true"  code="Label.NEXT"></spring:message></a>
		                  </div>
		                  
						 </div>
					   </div>
				   	</c:if>
			
			
			</form:form>
			
		              </div>
            </section>
          </div>
       </section>
				
</body>
</html>

