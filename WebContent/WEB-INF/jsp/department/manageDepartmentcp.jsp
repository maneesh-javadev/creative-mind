<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
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
 <script src="resource/dashboard-resource/plugins/datatables/jquery-1.12.4.js"></script>

<script type="text/javascript" src="js/ministry.js"></script>

<script type="text/javascript">



//jquery pagination
$(document).ready(function() {
    $('#modifystateDepartment').DataTable();
    $('#modifyDeparmentLowLevel').DataTable();
} );



function manageDepartmentState(url,id)
{
	//alert(id);
	dwr.util.setValue('departmentId', id, {	escapeHtml : false	});
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url+'?<csrf:token uri="viewdepartmentbyministrycodeforstate.htm"/>';
	document.getElementById('form1').submit();
}


dwr.engine.setActiveReverseAjax(true);



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

function hideAll()
{
	
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
	 	              <input type="hidden" id="specificLevel" name=SpecificLevel"  value="<c:out value='${category}' escapeXml='true'></c:out>" />
	 	              
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
								<form:select path="category" class="form-control" id="category" onchange="hideAll();getandSetDistrictListDPforAdminUnitLevel(this.value);" htmlEscape="true">
<%-- 											      	<form:option value="" label="-----------Select----------" htmlEscape="true" /> --%>
												  	<form:option value="S" label="State Line Department" htmlEscape="true"/>
												  	<%-- <form:option value="D" label="District Line Department"  htmlEscape="true" />
												  	<form:option value="T" label="SubDistrict Line Department" htmlEscape="true"/>
													<form:option value="B" label="Block Line Department" htmlEscape="true" />
													<form:option value="V" label="Village Line Department" htmlEscape="true" />
													<form:option value="A" label="Administrative Unit Department" htmlEscape="true" /> --%>
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
								<form:select path="villageNameEnglish" class="form-control" style="width: 150px" onchange="hideAll();" id="ddvillage" htmlEscape="true">
												</form:select> <!-- <span class="error" id="ddSourceSubDistrict_error"></span> -->
												   <div id="error_td_village" class="errormsg">	<spring:message htmlEscape="true" 
															code="error.select.SELECTVILLAGENAME" text="please select one of village name"></spring:message> </div> 
								  </div>
						</div> 
						
						<div class="form-group" id="td_block">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true"   code="Label.SELBLOCK"></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								<form:select path="blockNameEnglish" class="form-control"  id="ddblock" onchange="hideAll();" >
												</form:select> <!-- <span class="error" id="ddSourceSubDistrict_error"></span> -->
												 <div id="error_td_block" class="errormsg">	<spring:message htmlEscape="true" 
															code="error.select.SELECTBLOCKNAME" text=" please select one of block name"></spring:message> </div> 
								  </div>
						</div> 
						
						
						<div class="form-group" id="trAdminUnitLevel" style="display: none">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="" text="Select Admin Unit Level"></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								   <form:select path="" id="adminUnitLevel" class="form-control"  htmlEscape="true" onchange="getAdminUnitListByLevelCode(this.value);">
							     	</form:select>
								  </div>
						</div>
						
						
						<div class="form-group" id="td_adminunit">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTADMINUNITDEPT" text="Select Admin Unit"></spring:message><span class="mandatory">*</span></label>
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
                   <button type="submit" name="Submit" class="btn btn-info"   onclick="return validate_modifyDepartmnet();"  ><spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message> </button>
				   <button type="button" name="Submit2" class="btn btn-warning"  onclick="javascript:location.href='viewdepartmentforstate.htm?<csrf:token uri='viewdepartmentforstate.htm'/>';" ><spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message> </button>
                   <button type="button" class="btn btn-danger " name="Cancel"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div> 
       </div>
       <!---------------	 State level show details       ---------------------------------------------------------------------------------------------------------------------------> 			   	
 				 <c:if test="${! empty orgList}">	
 				 <div class="box">	
 				 	<div class="box-body">
					<table class="table table-bordered table-striped dataTable no-footer" id="modifystateDepartment">
													<thead>
															<tr >
																<th align="center"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></th>
																<th align="center"><spring:message htmlEscape="true" 	code="Label.DEPTCODE"></spring:message></th>
																<th align="left"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message></th>
																<th align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></th>
																<th align="center"><spring:message htmlEscape="true"  text="Rename"></spring:message></th>
															</tr>
											 				
													</thead>
													<tbody>
														<c:forEach var="obj" varStatus="objRow" items="${orgList}">
															<tr >
																<td align="center"><c:out value="${objRow.index+1}"  escapeXml="true"></c:out></td>
																<td align="center"><c:out value="${obj.orgCode}" escapeXml="true"></c:out></td>
																<td align="left"><c:out value="${obj.orgName}" escapeXml="true"></c:out></td>
																<td align="center"><a href="#" onclick="javascript:manageDepartmentState('viewDepartment.htm',${obj.orgCode});" ><i class="fa fa-eye" aria-hidden="true"></i>
																</a></td>
																<td align="center">
																	<a href="#"  onclick="javascript:manageDepartmentState('modifyOrganisationAtLevel.htm',${obj.orgCode});"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
						 											</a>
																</td>
															</tr>
											</c:forEach>
									</tbody>
						</table>
											
					   <form:input path="departmentId" type="hidden" name="departmentId" id="departmentId" htmlEscape="true" />
						<form:input path="entityCode" type="hidden" htmlEscape="true" />
						</div>
						</div>
				   	</c:if>
				   	
<!--------------	End of  State level show details       ---------------------------------------------------------- -->
       
<!---------------	 District, Sub-district,Block and village level show details       ---------------------------------------------------------------------------------------------------------------------------> 		    
			     <c:if test="${! empty listDistDept}">
			     <div class="box">	
			     <div class="box-body">		
					<table class="table table-bordered table-striped dataTable no-footer" id="modifyDeparmentLowLevel">
												<thead>
														<tr >
																		<th   align="center"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></th>
																		<th  align="center"><spring:message htmlEscape="true" 	code="Label.DEPTCODE"></spring:message></th>
																		<th   align="left"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message></th>
																		<th  align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></th>
														</tr>
														
												</thead>
												<tbody>
														<c:forEach var="listDistDept" varStatus="listDepartmentRow" items="${listDistDept}">
															<tr >
																<td align="center"><c:out value="${listDepartmentRow.index+1}"  escapeXml="true"></c:out></td>
																<td align="center"><c:out value="${listDistDept.orgCode}" escapeXml="true"></c:out></td>
																<td align="left"><c:out value="${listDistDept.orgName}" escapeXml="true"></c:out></td>
																<td align="center"><a href="#" onclick="javascript:manageDepartmentState('viewDepartment.htm',${listDistDept.orgCode});"><i class="fa fa-eye" aria-hidden="true"></i>
																</a></td>
															</tr>
														</c:forEach>
												</tbody>
					</table>
	  			 	<form:input path="departmentId" type="hidden" name="departmentId" id="departmentId" htmlEscape="true"/>
					<form:input path="entityCode" type="hidden" htmlEscape="true" />
					</div>
					</div>
   				</c:if>
<!-- ---------------End of	 District, Sub-district,Block and village level show details       ---------------- -->
       
       
       
       
       
       
       
       
       
       
     
             
    </form:form>      
   </section>
   </div>
   </section>
<script src="/LGD/JavaScriptServlet"></script>

</html>
