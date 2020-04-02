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


<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/ministry.js"></script>
<script type="text/javascript">



//jquery pagination  
$(document).ready(function() {
	
	  $('#OrganizationStateLevel').DataTable();
	  $('#OrganizationLowLevel').DataTable();
	  

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
		<section class="content">
                <div class="row">
                    <section class="col-lg-12">
              <form:form action="vieworganizationbydepartmentcodeforState.htm" id="form1" method="POST" commandName="viewDeptforstate" class="form-horizontal">
				 <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="vieworganizationbydepartmentcode.htm"/>"/>
				 <input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("stateCode")%>'/>
	 	              
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.VIEWORG"></spring:message></h3>
                                </div>
                                 <div class="box-header subheading">
                                    <h4 ><spring:message htmlEscape="true"  code="Label.SELECTCRITERIA"></spring:message></h4>
                                </div>
                      <div id='showbycentrelevel' style=" visibility: hidden; display:none">          
                        <div class="box-body" >
                            <div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message  htmlEscape="true"  code="Label.SELMIN"></spring:message><span class="errormsg">*</span></label>
								<div class="col-sm-6" >
								<form:select path="ministryName"  class="form-control" id="ddMinistry" onchange="getDepartmentListByMinistry(this.value)">
											            <form:option selected="selected" value="" label="--select--"></form:option>
											            <form:options items="${ministryList}" itemLabel="orgName" itemValue="organizationPK.orgCode"  />
											           </form:select> 
											           <form:errors htmlEscape="true"  path="ministryName" class="errormsg"></form:errors>
								  </div>
						</div>   
						
						<div class="form-group" id="td_district">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELDEPT"></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								 <form:select path="deptName"  class="form-control" id="ddDepartment">
											           <form:options items="${departmentList}" itemLabel="orgName" itemValue="organizationPK.orgCode"  />
											           </form:select> 
								  </div>
						</div>   
						
						
						
						
              </div> 
             
          
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                   <button type="submit" name="Submit" class="btn btn-info"   > <spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message></button>
				   <button type="button" name="Submit2" class="btn btn-warning"  onclick="doRef();" ><spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button>
				   <button type="button" name="Submit3"	class="btn btn-danger"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
           </div>   
       </div> 
    </div>   
    </div>
    <!-- --------------- -->
    
    <div  id='showbystatelevel'>
						
							<div class="box-body">
								
	                                <div class="form-group">
									
									<label for="ddDepartment" class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELDEPT"></spring:message><span class="errormsg">*</span></label>
										      <div class="col-sm-6">  
										           <form:select path="deptName"  class="form-control" id="ddDepartment">
										           <form:options items="${stateDeptList}" itemLabel="orgName" itemValue="organizationPK.orgCode"  />
										           </form:select> 
										        </div>
									</div>
								  
	                                
	                               <div class="form-group">
										<label for="category" class="col-sm-3 control-label" ><spring:message htmlEscape="true"  code="Label.SELSEARCHENTITY"></spring:message><span class="errormsg">*</span></label></br>
										<div class="col-sm-6">
												<form:select path="category"  class="form-control" id="category" onchange="hideAll();getandSetDistrictListDP(this.value);">
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
												
										      </div>		
									     </div>
									     
									     <div class="form-group" id="td_district">
													<label for="ddSourceDistrict" class="col-sm-3 control-label" ><spring:message htmlEscape="true"  code="Label.SOURCEDISTRICT"></spring:message><span class="errormsg">*</span></label></br>
													<div class="col-sm-6">	
														<form:select path="districtCode" class="form-control"
															id="ddSourceDistrict" onchange="hideAll();getandSetSubDistrictList(this.value);getandSetBlockList(this.value)">
															<form:options itemValue="districtCode" itemLabel="districtNameEnglish"></form:options>
														</form:select>
														<div id="error_td_district" class="errormsg">	<spring:message htmlEscape="true" 
																code="error.select.SELECTDISTRICTNAME"></spring:message> </div>	
												   </div>			
													
										</div>
										
										<div class="form-group" id="td_subdistrict">
									           <label for="ddSourceSubDistrict" class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTSUBDISTRICT"></spring:message><span class="errormsg">*</span></label></br>
												<div class="col-sm-6">
													<form:select path="subdistrictNameEnglish" class="form-control" id="ddSourceSubDistrict" onchange="hideAll();getandSetVillageListDP(this.value);">
													<form:options itemValue="subdistrictCode" itemLabel="subdistrictNameEnglish"></form:options>
													</form:select> <!-- <span class="error" id="ddSourceSubDistrict_error"></span> -->
													<div id="error_td_subdistrict" class="errormsg">	<spring:message htmlEscape="true" code="error.select.SELECTSUBDISTRICTNAME"></spring:message> </div>
												</div>	
								       
								       </div>
								       
								        <div class="form-group" id="td_village">
									              <label for="ddvillage" class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTVILLAGE"></spring:message><span class="errormsg">*</span></label></br>
												 <div class="col-sm-6">	
														<form:select path="villageNameEnglish" class="form-control" id="ddvillage" onchange="hideAll();" >
														</form:select> <!-- <span class="error" id="ddSourceSubDistrict_error"></span> -->
														 <div id="error_td_village" class="errormsg">	
														<spring:message htmlEscape="true" code="error.SELECTONEVILLAGE"></spring:message>	 </div>
													</div>
								         </div>
								       
								       <div class="form-group" id="td_block">
									              <label for="ddblock" class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELBLOCK"></spring:message><span class="errormsg">*</span></label></br>
												  <div class="col-sm-6">	
														<form:select path="blockNameEnglish" class="form-control" id="ddblock" onchange="hideAll();" >
														</form:select>
														<div id="error_td_block" class="errormsg"> <spring:message htmlEscape="true" code="error.SELECTONEBLOCK"></spring:message></div>
													</div>
													
								       </div>
										 <div class="form-group" id="td_adminunit">
											<label for="ddSourceAdminUnit" class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTADMINUNITDEPT" text="Select Admin Unit"></spring:message><span class="errormsg">*</span></label></br>
													<div class="col-sm-6">	
														<form:select path="adminUnitCode" class="form-control" htmlEscape="true"
															id="ddSourceAdminUnit" onchange="hideAll();">
															<form:options itemValue="adminUnitEntityCode" itemLabel="adminEntityNameEnglish"></form:options>
														</form:select>
														<div id="error_td_adminunit" class="errormsg">	<spring:message htmlEscape="true" 
																code="error.select.SELECTADMINUNITDEPTNAME" text="please select one of admin unit name"></spring:message> </div>
	 										       </div>
	 										</div>
									 
	
	 							
 								</div>
 								
 					<div class="box-footer">
           				<div class="col-sm-offset-2 col-sm-10">
                        <div class="pull-right">
                           <button type="submit" name="Submit"  class="btn btn-info" onclick="return validate_modifyOrganization();"  ><spring:message htmlEscape="true"  code="Button.GETDATA" ></spring:message></button>
							<button type="button" name="Submit2" class="btn btn-warning"  onclick="javascript:location.href='vieworganizationforstate.htm?<csrf:token uri='vieworganizationforstate.htm'/>';" ><spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message> </button>
							<button type="button" name="Submit3"  class="btn btn-danger"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message> </button>
				           </div>   
				       </div> 
				    </div>
						
			 </div>
					 
       
       <!-- -------------- --------------------------------->
       <!--------------------------------------------------------------------- State Level data ----------------------------------------------------------------------------------------------------- -->
				<c:if test="${! empty listDeptstate}">
					<div class="box-body">
					<div class="table-responsive">
					<table class="table table-bordered table-striped dataTable no-footer" id="OrganizationStateLevel">
				      <thead>
							<tr>
								<th rowspan="2"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></th>
								<th  rowspan="2"><spring:message htmlEscape="true" code="Label.ORGCODE"></spring:message></th>
								<th rowspan="2"><spring:message htmlEscape="true" code="Label.ORGNAMEINENG"></spring:message></th>
								<th colspan="3" style="text-align:center"><spring:message htmlEscape="true" code="Label.ACTION"></spring:message></th>
							</tr>
							<tr >
							    <th align="center"><spring:message htmlEscape="true" code="Label.VIEW"></spring:message></th>
								<th align="center"><spring:message htmlEscape="true" code="Label.MODIFY"></spring:message></th>
								<th align="center"><spring:message htmlEscape="true" code="Label.DELETE"></spring:message></th>
	                       </tr>
					</thead>
                  <tbody>
						<c:forEach var="listDept" varStatus="listOrganizationRow" 	items="${listDeptstate}">
							<tr >
								<td><c:out value="${listOrganizationRow.index+1}" escapeXml="true"></c:out></td>
								<td><c:out value="${listDept.orgCode}" escapeXml="true"></c:out></td>
								<td align="left"><c:out value="${listDept.orgName}(${orgTypeName})" escapeXml="true"></c:out></td>
                               <td align="center"><a href="#" onclick="javascript:manageOrganization('viewOrganizationStateDetail.htm',${listDept.orgCode});"><i class="fa fa-eye" aria-hidden="true"></i>
                               </a>
								 </td>
								 <td align="center"><a href="#"  onclick="javascript:manageOrganization('modifyOrganizationState.htm',${listDept.orgCode});"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
								 </a>
														</td>
								<td align="center"><a href="#" onclick="javascript:manageOrganization('DeleteOrganizationState.htm',${listDept.orgCode});"><i class="fa fa-trash-o" aria-hidden="true"></i>
								</a>
								</td>

							</tr>
						</c:forEach>
					</tbody>
                </table>
                </div>
					</div>
					<form:input path="organizationId" type="hidden"  name="organizationId" id="organizationId" />
				</c:if>

<!---------------	 Center level show details       ---------------------------------------------------------------------------------------------------------------------------> 			
				 <c:if test="${! empty listDept}">			
					<div class="box-body">
						<div class="table-responsive">
							
										<table class="table table-bordered table-striped dataTable no-footer" id="OrganizationLowLevel">
											<thead>
											<tr >
												<th rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></th>
												<th rowspan="2"><spring:message htmlEscape="true" 	code="Label.ORGCODE"></spring:message></th>
												<th rowspan="2"><spring:message htmlEscape="true"  code="Label.ORGNAMEINENG"></spring:message></th>												
											
												<th colspan="2" style="text-align:center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></th>
											</tr>
											<tr >

											    <th align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></th>
												<th align="center"><spring:message htmlEscape="true"  code="Label.MODIFY"></spring:message></th>
												
											</tr>
											
											</thead>
											
                                      <tbody>
                                      <c:forEach var="listDept" varStatus="listOrganizationRow" items="${listDept}">
													<tr >
														<td ><c:out value="${listOrganizationRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${listDept.orgCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listDept.orgName}" escapeXml="true"></c:out></td>
													
														<td  align="center"><a href="#" onclick="javascript:manageOrganization('viewOrganizationStateDetail.htm',${listDept.orgCode});" ><i class="fa fa-eye" aria-hidden="true"></i>
														</a></td>
														<td  align="center"><a href="#" onclick="javascript:manageOrganization('modifyOrganizationState.htm',${listDept.orgCode});"> <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
														</a></td>
														
													</tr>
												</c:forEach>	
                                      </tbody>
								
									
																					
										</table>
							
							<form:input path="organizationId" type="hidden" name="organizationId" id="organizationId"  />
						 </div>
					   </div>
				   	</c:if>
<!---------------	End of Center level show details       ---------------------------------------------------------------------------------------------------------------------------> 
       
     
       
   
             
    </form:form>      
   </section>
   </div>
   </section>
<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>

