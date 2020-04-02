<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%-- <%@taglib uri="/esapi" prefix="esapi"%>
<%@taglib uri="/csrf" prefix="csrf"%> --%>
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Department Mapping - Add</title>
<!-- <link href="resources/css/panchayat_main.css" rel="stylesheet" type="text/css" /> -->
<!-- <script type="text/javascript" language="javascript" src="resources/js/jquery-1.9.1.js"></script>   -->

<script type='text/javascript' language="javascript" src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' language="javascript" src='<%=request.getContextPath()%>/dwr/util.js'></script>		
 <script type='text/javascript' language="javascript" src='<%=request.getContextPath()%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
 <script type='text/javascript' language="javascript" src='<%=request.getContextPath()%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>
  <script type='text/javascript' language="javascript" src='<%=request.getContextPath()%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>
  <script type='text/javascript' language="javascript" src='<%=request.getContextPath()%>/dwr/interface/lgdDwrOrganizationService.js'></script>
 

<script type="text/javascript" src="js/govtorder.js"></script>
<script type="text/javascript" src="js/departmentMapping.js"></script>
<script type="text/javascript" src="datepicker/jquery-1.6.2.js"	charset="utf-8"></script>


<style type="text/css">
	
fieldset {
	display: block;
	-webkit-margin-start: 2px;
	-webkit-margin-end: 2px;
	-webkit-padding-before: 0.35em;
	-webkit-padding-start: 0.75em;
	-webkit-padding-end: 0.75em;
	-webkit-padding-after: 0.625em;
	border: 2px groove threedface;
	border-image-source: initial;
	border-image-slice: initial;
	border-image-width: initial;
	border-image-outset: initial;
	border-image-repeat: initial;
	min-width: -webkit-min-content;
}
legend {
	display: block;
	-webkit-padding-start: 2px;
	-webkit-padding-end: 2px;
	border: none;
	border-image-source: initial;
	border-image-slice: initial;
	border-image-width: initial;
	border-image-outset: initial;
	border-image-repeat: initial;
}
/* ul.my_listing li {
	display: inline-block;
	*display: inline;
	*zoom: 1;
	vertical-align: top;
	margin-right: 2px;
	font-size: 12px;
	line-height: 16px;
     width: 20%;
	/* word-wrap:break-word; */
} */


</style>
<script>

function showAlert(){   
	if("${message}"!=null && "${message}" != ''){
		var alertMessage = "<spring:message code="${message}"></spring:message> ";
		alert(alertMessage); 
		if("${message}" == 'msg.noPrivilege'){
			window.location="welcome.htm?<csrf:token uri='welcome.htm'/>";
		}
	}
}

function toggleFromTo(val){
	var mappingtypesetup=document.getElementById("mappingType").value
	
	hideAll();
	$("#mappingDivID").show();
	$("#displaybtn").show();
	if(val == 'OMO'){
		$("#toDepartment").show();
		$("#fromDepartment").show();
		$("#toLabelID").text('<spring:message code="label.lineDep" />');
		$("#fromLabelID").text('<spring:message code="label.lineDep" />');
		document.getElementById("to_category").value = "O";
		document.getElementById("from_category").value = "O";
	}else if(val == 'GMO'){
		$("#toLocalBodySetup").show();
		$("#fromDepartment").show();
		$("#toLabelID").text('<spring:message code="common.localBody" />');
		$("#fromLabelID").text('<spring:message code="label.lineDep" />');
		document.getElementById("to_category").value = "G";
		document.getElementById("from_category").value = "O";
	}else if(val == 'OMG'){
		$("#toDepartment").show();
		$("#fromLocalBodySetup").show();
		$("#toLabelID").text('<spring:message code="label.lineDep" />');
		$("#fromLabelID").text('<spring:message code="common.localBody" />');
		document.getElementById("to_category").value = "O";
		document.getElementById("from_category").value = "G";
	}
	else if(val == 'OML'){
		$("#toDepartment").show();
		$("#fromLandRegion").show();		
		$("#toLabelID").text('<spring:message code="label.lineDep" />');
		$("#fromLabelID").text('<spring:message code="common.landRegion" />');
		document.getElementById("to_category").value = "O";
		document.getElementById("from_category").value = "L";
	}else if(val == 'LMO'){
		$("#toLandRegion").show();
		$("#fromDepartment").show();		
		$("#toLabelID").text('<spring:message code="common.landRegion" />');
		$("#fromLabelID").text('<spring:message code="label.lineDep" />');
		document.getElementById("to_category").value = "L";
		document.getElementById("from_category").value = "O";
	}
}

//fromLocalBodySetup
var mappingtypename=document.getElementById("mappingType").value
var fromLocalBodySetup=document.getElementById("fromLocalBodySetup").value
fromLocalBodySetup=parseInt(fromLocalBodySetup);

function saveData(){
	var mappingtypesetup=document.getElementById("mappingType").value
	if(validateForm()){
		 if(mappingtypesetup==="OMO")
	     {
		 if(showSaveError())
		{
		$("#Save").attr("disabled",true);
		document.deptMappingForm.action = "addDeptMappingForm.htm?<csrf:token uri='addDeptMappingForm.htm'/>";
		document.deptMappingForm.submit();
		}
	     }
		 
		 else if(mappingtypesetup==="GMO")
		 {
			 if(showSaveError())
				{
				$("#Save").attr("disabled",true);
				document.deptMappingForm.action = "addDeptMappingForm.htm?<csrf:token uri='addDeptMappingForm.htm'/>";
				document.deptMappingForm.submit();
				} 
			
		 } 
		 
		 else if(mappingtypesetup==="OMG")
		 {
			 if(showSaveErrorlb())
				{
				$("#Save").attr("disabled",true);
				document.deptMappingForm.action = "addDeptMappingForm.htm?<csrf:token uri='addDeptMappingForm.htm'/>";
				document.deptMappingForm.submit();
				} 
			 
		 }
		 
		else
			 {
		     $("#Save").attr("disabled",true);
			document.deptMappingForm.action = "addDeptMappingForm.htm?<csrf:token uri='addDeptMappingForm.htm'/>";
			document.deptMappingForm.submit();
			 }
	}
}
</script>

</head>
<body onload="showAlert()">
<section class="content">
                <div class="row">
                    <section class="col-lg-12">
                 <form:form name="deptMappingForm" method="POST" action="addDeptMappingForm.htm" class="form-horizontal" onsubmit="return saveData();">
                 <input type="hidden" id="stateId" name="stateId" value="${stateCode}" />
                 <input type="hidden" id="userId" name="userId"  value="${userId}" />
                  <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="addDeptMappingForm.htm"/>" />
                    <div class="box">
                    <div class="box-header with-border">
                      <h4><spring:message code="menu.deptLBMapping" htmlEscape="true"></spring:message></h4>
                    </div>
                        <div class="box-body">
                          <div class="form-group">
                            	<label  class="col-sm-3 control-label" ><spring:message code="menu.deptMapping" htmlEscape="true"></spring:message></label>									
								  <div class="col-sm-6">	
										<select id="mappingType" name="mappingType" class="form-control">
											<option value="">
												<spring:message code="Label.SELECT" />
											</option>
											<c:forEach var="mappingTypeList" items="${mappingTypeList}">
												<option value="${mappingTypeList.key}">
													<spring:message code='${mappingTypeList.value}'  htmlEscape="true" />
												</option>
											</c:forEach>
									   </select>
                                    </div>	
                          </div>
                          
                         <div id="mappingDivID" style="display: none">

											<fieldset class="for-panel">
												<legend>
													<label id="labelID"></label>
												</legend>
												
												<div class="col-sm-6">
													
														<fieldset>
															<legend>Part A</legend>
															<div id="toTableID">
																
																<div class="form-group">
																	<label  class="col-sm-3 control-label" for="deptId" id="toLabelID"></label>
																  	<div class="col-sm-6">
																  		<select class="form-control" id="toLocalBodySetup" name="toLocalBodySetup" style="display: none">
																			<option value="">
																				<spring:message code="Label.SELECT" htmlEscape="true" />
																			</option>
																			<c:forEach var="lbSetup" items="${localGovtSetup}">
																				<option value="${lbSetup.localBodyTypeCode}" paramCategory="${lbSetup.category}">
																					<c:out value="${lbSetup.localBodyTypeName}" escapeXml="true"></c:out>
																				</option>
																			</c:forEach>
																		</select>
																		<select class="form-control" id="toDepartment" name="toDepartment" style="display: none">
																			<option value="" htmlEscape="true">
																				<spring:message code="Label.SELECT" />
																			</option>
																			<c:forEach var="orgnList" items="${organizationList}">
																				<option value="${orgnList.orgCode}-${orgnList.orgUnitCode}">
																					<c:out value="${orgnList.orgUnitName}" escapeXml="true"></c:out>
																				</option>
																			</c:forEach>
																		</select>
																			<input type="hidden" id="to_level_id" name="to_level_id" value="" />
																			<input type="hidden" id="to_selected_idx" name="to_selected_idx" value="" />
																			<input type="hidden" id="to_category" name="to_category" value="" />
																  		</div>
																  </div>
																  	
																	<div class="form-group" id="toDepartmentLevelID" style="display: none">
																	<label  class="col-sm-3 control-label" ><spring:message code="label.departmentLevel" /></label>
																  	<div class="col-sm-6">
																  		<select class="form-control" id="toDepartmentLevel" name="toDepartmentLevel">
																			<option value="">
																				<spring:message code="Label.SELECT" htmlEscape="true" />
																			</option>
																	</select>
																  	</div>
																	</div>
																	<div class="form-group" id="toDepartmentOrgUnitID" style="display: none">
																	<label  class="col-sm-3 control-label" ><spring:message code="label.childOrgUnits" /></label>
																  	<div class="col-sm-6">
																  	     
																  	     <%-- <select class="form-control" id="toOrgUnits" name="toDepartmentOrgUnits"  onchange="loadExistingMappingDeptToLB(this.value)" >
																			<option value="" htmlEscape="true">
																				<spring:message code="Label.SELECT" />
																			</option>
																		</select> --%>
																  	    
																  	     
																  		<select class="form-control" id="toOrgUnits" name="toDepartmentOrgUnits"  onchange="loadExistingMappingDept(this.value)" >
																			<option value="" htmlEscape="true">
																				<spring:message code="Label.SELECT" />
																			</option>
																		</select>
																	    
																  	</div>
																</div>
																	
																	<div id="toDepartmentLevelHierarchy">
																	
																	</div>
																
															</div>
															<%-- <div id="IsLbMapped" style="display: none">
															
															
															<div class="form-group">
							  									<label  class="col-sm-6 control-label" for="sel1"><spring:message code="label.isLBMapped" text="Is Local Body Mapped" /></label>
																  <div class="col-sm-6">
																  <label class="radio-inline">
																   		<input type="radio" name="mappedCoverageType" id="mappedCoverageTypeF" value="F" checked="checked" />
																   		<spring:message code="label.Full" text="FULL" />
																  </label>
																  <label class="radio-inline">
																  	<input type="radio" name="mappedCoverageType" id="mappedCoverageTypeP" value="P" />
																	<spring:message code="label.Part" text="PART" />
																  </label>
																  
																  </div>
															</div>
															
																
															</div>
															<div id="wardDiv" style="display: none">
															</div> --%>
														</fieldset>
												
												</div>
                                                <div class="col-sm-6">
													
														<fieldset>
															<legend>Part B</legend>
															<div id="fromTableID">
															
																<div class="form-group">
																	<label  class="col-sm-3 control-label" id="fromLabelID"></label>
																  	<div class="col-sm-6">
																	  		<select class="form-control" id="fromLocalBodySetup" name="fromLocalBodySetup" style="display: none">
																				<option value="" htmlEscape="true">
																					<spring:message code="Label.SELECT" />
																				</option>
																				<c:forEach var="lbSetup" items="${localGovtSetup}">
																					<option value="${lbSetup.localBodyTypeCode}">
																						<c:out value="${lbSetup.localBodyTypeName}" escapeXml="true"></c:out>
																					</option>
																				</c:forEach>
																		  </select> 
																		  <select class="form-control" id="fromDepartment" name="fromDepartment" style="display: none">
																				<option value="" htmlEscape="true">
																					<spring:message code="Label.SELECT" />
																				</option>
																				<c:forEach var="orgnList" items="${organizationList}">
																					<option value="${orgnList.orgCode}-${orgnList.orgUnitCode}">
																						<c:out value="${orgnList.orgUnitName}" escapeXml="true"></c:out>
																					</option>
																				</c:forEach>
																		   </select>
																		   <input type="hidden" id="from_level_id" name="from_level_id" value="" />
																		   <input type="hidden" id="from_selected_idx" name="from_selected_idx" value="" />
																		    <input type="hidden" id="from_category" name="from_category" value="" />
																  	</div>
																</div>
															
															
																<div class="form-group" id="fromDepartmentLevelID" style="display: none">
																	<label  class="col-sm-3 control-label" ><spring:message code="label.departmentLevel" /></label>
																  	<div class="col-sm-6">
																  		<select class="form-control" id="fromDepartmentLevel" name="fromDepartmentLevel" >
																			<option value="" htmlEscape="true">
																				<spring:message code="Label.SELECT" />
																			</option>
																		</select>
																	
																  	</div>
																</div>
																 
																    <div class="form-group" id="fromDepartmentOrgUnitID" style="display: none">
																	<label  class="col-sm-3 control-label" ><spring:message code="label.childOrgUnits" /></label>
																  	<div class="col-sm-6">
																  		<select class="form-control" id="fromOrgUnits" name="fromDepartmentOrgUnit" multiple="multiple" >
																			
																		</select>
																	
																  	</div>
																</div>
																
																<div id="fromDepartmentLevelHierarchy">
																	
															 </div>
																
															</div>
														</fieldset>
													
													</div>
												
											</fieldset>
										</div>
								  
										 
										<div id="showMappedData"  style="display:none;">
										<table id="showMappedTable" class="table table-bordered table-striped dataTable no-footer"  width="100%" cellspacing="0">
								        <thead>
										<tr>
                                        <th><spring:message code="Label.SNO" htmlEscape="true"></spring:message></th>
                                        <th><spring:message code="label.lineDep" htmlEscape="true"></spring:message></th>
                                        <th><spring:message code="label.DepartmentLevel" htmlEscape="true"></spring:message></th>
                                        <th><spring:message code="Label.DepartmentHierarchy" htmlEscape="true"></spring:message></th>
                                        <th><spring:message code="Label.Action" htmlEscape="true" text="Action"></spring:message></th>
                                        
									    </tr>
									  </thead>
									  <tbody>
									  <td><c:out value="" escapeXml="true"></c:out></td>
									  <td><c:out value="" escapeXml="true"></c:out></td>
									  <td><c:out value="" escapeXml="true"></c:out></td>
									  <td><c:out value="" escapeXml="true"></c:out></td> 
									  <td><c:out value="" escapeXml="true"></c:out></td>
									 
									</tbody>
									</table> 
								 
									</div>
									
									
                        </div>
                          <div class="box-footer">
		                 <div class="col-sm-offset-2 col-sm-10">
		                 <div class="pull-right">
						   <button type="button" class="btn btn-success" name="Save" id="Save"  onclick=" return saveData()" ><spring:message code="Button.SAVE" /></button>
						   <button type="button" class=" btn btn-danger" onclick="javascript:location.href='home.htm'"><spring:message code="Button.CLOSE"/></button>
                        </div>
                      </div>
                   </div>
                        
                    </div>
                    
                    </form:form>
             
            </section>
          </div>
    </section>
</body>
</html>