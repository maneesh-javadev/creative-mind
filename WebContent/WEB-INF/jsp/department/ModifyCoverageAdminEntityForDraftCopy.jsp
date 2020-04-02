<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<title>E-Panchayat</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<script src="js/departmentAdminUnit.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
</script>
<script> 
$(document).ready(function() {
   <c:forEach var="coverageCode" items="${entityCoverageData}" varStatus="status">
    	covValues.push("${coverageCode.entityCode}"); 
   </c:forEach>
	getPartAddedCoverage();
	<c:forEach var="entityCoverageDetailsForLB" items="${entityCoverageDetailsForLB}" varStatus="status">
		lbEntityCodeValues.push("${entityCoverageDetailsForLB.entityCode}"); 
		lbEntityCoverageTypeValues.push("${entityCoverageDetailsForLB.coverageType}");
	</c:forEach>	
	<c:forEach var="entityCoverageDetailsForWard" items="${entityCoverageDetailsForWard}" varStatus="status">
		lbEntityCodeWardValues.push("${entityCoverageDetailsForWard.entityCode}"); 
		lbEntityCoverageTypeWardValues.push("${entityCoverageDetailsForWard.coverageType}");
		lbEntityLbCodeWardValues.push("${entityCoverageDetailsForWard.lbCodeForWard}");
	</c:forEach>
	<c:forEach var="entityCoverageDetailsForDist" items="${entityCoverageDetailsForDist}" varStatus="status">
		lbEntityCodeDistValues.push("${entityCoverageDetailsForDist.entityCode}"); 
		lbEntityCoverageTypeDistValues.push("${entityCoverageDetailsForDist.coverageType}");
	</c:forEach>
	<c:forEach var="entityCoverageDetailsForSubDist" items="${entityCoverageDetailsForSubDist}" varStatus="status">
		lbEntityCodeSubDistValues.push("${entityCoverageDetailsForSubDist.entityCode}"); 
		lbEntityCoverageTypeSubDistValues.push("${entityCoverageDetailsForSubDist.coverageType}");
	</c:forEach>	
	<c:forEach var="entityCoverageDetailsForVillage" items="${entityCoverageDetailsForVillage}" varStatus="status">
		lbEntityCodeVillageValues.push("${entityCoverageDetailsForVillage.entityCode}"); 
		lbEntityCoverageTypeVillageValues.push("${entityCoverageDetailsForVillage.coverageType}");
	</c:forEach>
	<c:forEach var="entityCoverageDetailsForGp" items="${entityCoverageDetailsForGp}" varStatus="status">
		lbEntityCodeGPValues.push("${entityCoverageDetailsForGp.entityCode}"); 
		lbEntityCoverageTypeGPValues.push("${entityCoverageDetailsForGp.coverageType}");
		lbEntityLbCodeGPValues.push("${entityCoverageDetailsForGp.lbCodeForWard}");
	</c:forEach>	
	<c:forEach var="entityCoverageDetailsForGpVillage" items="${entityCoverageDetailsForGpVillage}" varStatus="status">
		lbEntityCodeGPVillageValues.push("${entityCoverageDetailsForGpVillage.entityCode}"); 
		lbEntityCoverageTypeGPVillageValues.push("${entityCoverageDetailsForGpVillage.coverageType}");
		lbEntityLbCodeGPVillageValues.push("${entityCoverageDetailsForGpVillage.lbCodeForWard}");
	</c:forEach>
	<c:forEach var="entityCoverageDetailsForState" items="${entityCoverageDetailsForState}" varStatus="status">
		lbEntityCodeStateValues.push("${entityCoverageDetailsForState.entityCode}"); 
		lbEntityCoverageTypeStateValues.push("${entityCoverageDetailsForState.coverageType}");
	</c:forEach>
} );
</script>
<style>
</style>
</head>
<body onload="modifyCoverageOptions('${coverageEntityTypeList}','D','${isExistOverlap}')">
	
	
	<section class="content">
    	<div class="row">
        	<section class="col-lg-12 ">
	        	<div class="box ">
	       			<div class="box-header with-border">
	
	           			<h3 class="box-title"><spring:message code="Label.ModifyAdminUnitDraft" htmlEscape="true"	text="Modify Draft Administrative Unit Level"></spring:message></h3>
	       			</div>
		                <div class="box-body">
							<div class="box-header subheading">
		                              <h4><spring:message code="Label.GenDetailAdminUnitsDraft" htmlEscape="true"
										text="General Details of Draft Admin Unit Level"></spring:message></h4>
		                    </div>
		                   <form:form action="saveAdminModifyCoverage.htm" class="form-horizontal" method="POST" id="form1" commandName="departmentAdminForm">
								<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="saveAdminModifyCoverage.htm"/>" />
								<input type="hidden" name="stateCode" id="stateCode" value="<c:out value="${stateCode}" escapeXml="true"></c:out>" />
								<input type="hidden" name="unitLevelCode" id="unitLevelCode" value="<c:out value="${departmentAdminForm.adminUnitLevelCode}" escapeXml="true"></c:out>" />
								<input type="hidden" name="adminCoverage" id="adminCoverage" value="" />
								<input type="hidden" name="wardCoverage" id="wardCoverage" value="" />
								<input type="hidden" name="lbidWard" id='lbidWard' />
								<!-- <input type="hidden" name="deletedCoverage" id='deletedCoverage' /> -->
								<input type="hidden" name="overlappValue" id ='overlappValue' value='${isExistOverlap}' />
								<input type="hidden" name="adminUnitCode" id='adminUnitCode' value="<c:out value="${departmentAdminForm.adminUnitCode}" escapeXml="true"></c:out>" />
								<table class="table table-striped table-bordered " cellspacing="0" width="100%" >
									<tbody>
										<tr>
											<td><spring:message code="Label.GENDETAILENTITY" htmlEscape="true" text="General Details of Admin Unit Entity"></spring:message></td>
											<td><c:out value="${departmentAdminForm.adminLevelNameEnglish}" escapeXml="true"></c:out></</td>
										</tr>
										<tr>
											<td><spring:message code="Label.AdminUnitEntityLoc" text="Administrative Unit Entity (In Local)"></spring:message></td>
											<td><c:out value="${departmentAdminForm.adminLevelNameLocal}" escapeXml="true"></c:out></</td>
										</tr>
									</tbody>
								</table>
								
								<c:if test="${! empty entityCoverageData}">
								<div class="box-header subheading">
		                              <h4><spring:message code="Label.COVEREDAREA" htmlEscape="true"></spring:message></h4>
		                    	</div>
								<table class="table table-striped table-bordered " cellspacing="0" width="100%" id="coverageTable">
									<tr>
										<td width="10%"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
										<td width="25%"><spring:message code="Label.ENTITYTYPE" htmlEscape="true"></spring:message></td>
										<td width="50%"><spring:message code="Label.ENTITYNAMEENGLISH" htmlEscape="true"></spring:message></td>
										<td width="15%"><spring:message code="Label.TYPE" htmlEscape="true"></spring:message></td>
									</tr>
									<c:forEach var="adminEntityDetail" varStatus="row" items="${entityCoverageData}">
										<tr>
											<td><c:out value="${row.count}" escapeXml="true" /></td>
											<td><c:out value="${adminEntityDetail.entityTypeName}" escapeXml="true"></c:out></td>
											<td><c:out value="${adminEntityDetail.entityName}" escapeXml="true"></c:out></td>
											<td><c:out value="${adminEntityDetail.coverageType}" escapeXml="true"></c:out></td>
										</tr>
									</c:forEach>
								</table>
							</c:if>
							
							
						<div id="coveragediv" class="frmpnlbg" style="visibility: hidden; display: none;">
							
							
							<div class="box-header subheading">
                            	<h4><spring:message code="Label.ADMINEXISTINGLANDREGION" text="Existing Land Regions"></spring:message></h4>
		                    </div>
		                    
		                    
		                    <div class="form-group">
								<div class="col-sm-12">
									<input type="checkbox" id="existingLB" onclick="toggleLBCovergeDiv();"></input> <label>
									<spring:message code="existingLB" text="Select From Existing Local Bodies"></spring:message>
									</label> 
									<input
									type="checkbox" id="existingLR" onclick="toggleLRCovergeDiv()"></input>
									 <label><spring:message code="existingLR" text="Select From Land Region"></spring:message></label>
								 </div>
							</div>
		                    
							
							
							<div id="LBRegion" style="visibility: hidden; display: none" class="frmtxt">
									<div class="box-header subheading">
		                            	<h4><spring:message code="Label.EXISTINGLANDREGION"></spring:message></h4>
				                    </div>
								
								
									<div class="form-group">
										<label class="col-sm-6"><spring:message htmlEscape="true" code="Label.SELECTLOCALBODYTYPE" text="Select Zilla  Panchyat"></spring:message></label>
										<div class="col-sm-6">
											<form:select htmlEscape="true" path="" class="form-control" id="ulbID" onchange="getUrbanlocabodies(this.value);">
												<form:option value="0">
													<spring:message code="Label.SELECT"></spring:message>
												</form:option>
												<form:options items="${uLBTypeList}" itemLabel="nomenclatureEnglish" itemValue="localBodyTypeCode" />
											</form:select>
											<div class="errormsg"></div> <span class="errormsg" id="ddDestDistrict_error"> </span>
										</div>
				                    </div>
								
								
								
								<div class="ms_container row" style="margin-left: 10px;">
									<div class="ms_selectable col-sm-5 form-group">
										<label><spring:message htmlEscape="true" code="Label.AVAILABLEURBANLB"></spring:message></label>
										<form:select path="" class="form-control" id="SourceLBList" multiple="true"></form:select>
									</div>
									<div class="ms_buttons col-sm-2"><br />
										<button type="button" id="src2Target1" name="Submit1" onclick="addItemPartCheck('contributedLBList','SourceLBList','FULL','G');" class="btn btn-primary btn-xs btn-block"><spring:message code="Label.SELECTVILLAGE"/>  <i class="fa fa-angle-double-right" aria-hidden="true"></i>
										</button>
										<button type="button" id="target2Src1" name="Submit2" onclick="removeOnedistrictOption('contributedLBList', 'SourceLBList', true,1);" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-left" aria-hidden="true"></i>
										</button>
										<button type="button" id="target2Src2" name="Submit3" onclick="removeAllList('contributedLBList', 'SourceLBList', true,1);" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
										</button>
										<button type="button" id="src2Target2" name="Submit3" onclick="addItem('contributedLBList','SourceLBList', 'PART',true);" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
										</button>
										</button>
									</div>
									<div class="ms_selection col-sm-5">
										<label><spring:message htmlEscape="true" code="Label.CONTRIBUTEURBANLB"></spring:message></label>
										<form:select name="select4" id="contributedLBList" multiple="multiple" path="" class="form-control">
										</form:select>
										<button type="button"  name="button2" onclick="getWardList('N');" class="btn btn-primary"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
											Get Ward List
										</button>
                                	</div>
				                  </div>
								
								
								
								<div class="ms_container row" style="margin-left: 10px;">
									<div class="ms_selectable col-sm-5 form-group">
										<label><spring:message htmlEscape="true" code="Label.AVAILEWARDLIST"></spring:message></label>
										<form:select name="souceWardList"  id="souceWardList" path="" multiple="multiple" class="form-control">
										</form:select>
									</div>
									<div class="ms_buttons col-sm-2"><br />
										<button type="button" id="btnaddSubDistrictFull" name="Submit4" onclick="addItem('contributedWardList','souceWardList','FULL',true);" class="btn btn-primary btn-xs btn-block">Whole &gt;&gt; <i class="fa fa-angle-double-right" aria-hidden="true"></i>
										</button>
										<button type="button" id="buttonRemove1" name="Submit2" onclick="removeOnedistrictOption('contributedWardList', 'souceWardList', true,0);" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-left" aria-hidden="true"></i>
										&lt;
										</button>
										<button type="button" id="buttonRemove2" name="Submit2" onclick="removeAllList('contributedWardList', 'souceWardList', true,0)" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-left" aria-hidden="true"></i>
										&lt;&lt;
										</button>
									</div>
									<div class="ms_selection col-sm-5">
										<label><spring:message htmlEscape="true" code="Label.CONTRIBUTWARDLIST"></spring:message></label>
										<form:select name="select4" id="contributedLBList" multiple="multiple" path="" class="form-control">
										</form:select>
										<button type="button"  name="button2" class="btn btn-primary" onclick="emptyLBList();">
											<spring:message code="Button.RESET" htmlEscape="true"></spring:message>
										</button>
                                	</div>
				                  </div>
								
								
								</div>
								<div id="LRRegion" style="visibility: hidden; display: none" class="frmtxt">
									<div class="box-header subheading">
		                            	<h4><spring:message code="Label.ADMINEXISTINGLANDREGION" text="Existing Land Regions"></spring:message></h4>
				                    </div>
				                    
				                    <div class="ms_container row" id="stateSelectionBlock" style="visibility: hidden; display: none" style="margin-left: 10px;"> 
										<div class="ms_selectable col-sm-5 form-group">
											<label><spring:message htmlEscape="true" code="Label.STATELIST"></spring:message></label>
											<form:select path="" class="form-control" id="SourceState" multiple="true">
												<c:forEach items="${stateList}" var="stateListvar">
													<c:if test="${stateListvar.operation_state == 'F'.charAt(0)}">
														<form:option value="${stateListvar.stateCode}" disabled="true">
															<c:out value="${stateListvar.stateNameEnglish}" escapeXml="true"></c:out>
														</form:option>
													</c:if>
													<c:if test="${stateListvar.operation_state == 'A'.charAt(0)}">
														<form:option value="${stateListvar.stateCode}">
															<c:out value="${stateListvar.stateNameEnglish}" escapeXml="true"></c:out>
														</form:option>
													</c:if>
												</c:forEach>
											</form:select>
										</div>
										<div class="ms_buttons col-sm-2"><br />
											<button type="button" id="src2Target1"  onclick="addItemPartCheck('contributedState','SourceState','FULL','S');" class="btn btn-primary btn-xs btn-block">Whole<i class="fa fa-angle-double-right" aria-hidden="true"></i>
											</button>
											<button type="button" id="target2Src1"  onclick="removeOnedistrictOption('contributedState', 'SourceState', true,5);" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
											</button>
											<button type="button" id="target2Src2"  onclick="removeAllList('contributedState', 'SourceState', true,5);" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
											</button>
											<button type="button" id="src2Target2"  onclick="addItem('contributedState','SourceState', 'PART',true);" class="btn btn-primary btn-xs btn-block">Part<i class="fa fa-angle-double-right" aria-hidden="true"></i>
											</button>
											
										</div>
										<div class="ms_selection col-sm-5">
											<label><spring:message htmlEscape="true" code="Label.CONTStateList" text="Contributing States List"></spring:message></label>
											<form:select name="select4" id="contributedState" multiple="multiple" path="" class="form-control">
											</form:select>
											<button type="button"  name="button2" class="btn btn-primary" onclick="getDistrictList('N')">
												Get District List
											</button>
	                                	</div>
				                  </div>
				                  
				                  
				                  
				                    <div class="ms_container row"  style="margin-left: 10px;"> 
										<div class="ms_selectable col-sm-5 form-group">
											<label><spring:message htmlEscape="true" code="Label.DISTRICTLIST"></spring:message></label>
											<form:select path="" class="form-control" id="SourceDistrict" multiple="true">
												<c:forEach items="${distList}" var="distListvar">
													<c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
														<form:option value="${distListvar.districtCode}" disabled="true">
															<c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out>
														</form:option>
													</c:if>
													<c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
														<form:option value="${distListvar.districtCode}">
															<c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out>
														</form:option>
													</c:if>
												</c:forEach> 
											</form:select>
										</div>
										<div class="ms_buttons col-sm-2"><br />
											<button type="button" id="src2Target1"  onclick="addItemPartCheck('contributedDistirct','SourceDistrict','FULL','D');" class="btn btn-primary btn-xs btn-block">Whole<i class="fa fa-angle-double-right" aria-hidden="true"></i>
											</button>
											<button type="button" id="target2Src1"  onclick="removeOnedistrictOption('contributedDistirct', 'SourceDistrict', true,2);" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-left" aria-hidden="true"></i>
											</button>
											<button type="button" id="target2Src2"  onclick="removeAllList('contributedDistirct', 'SourceDistrict', true,2);" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
											</button>
											<button type="button" id="src2Target2"  onclick="addItem('contributedDistirct','SourceDistrict', 'PART',true);" class="btn btn-primary btn-xs btn-block">Part<i class="fa fa-angle-double-right" aria-hidden="true"></i>
											</button>
										</div>
										<div class="ms_selection col-sm-5">
											<label><spring:message htmlEscape="true" code="Label.CONTRIBUTEDISTRICTLIST"></spring:message></label>
											<form:select name="select4" id="contributedDistirct" multiple="multiple" path="" class="form-control">
											</form:select>
											<button type="button"  name="button2" class="btn btn-primary" onclick="getSubdistrictsList('N')">
												Get Sub-district List
											</button>
	                                	</div>
				                  </div>
				                  
				                  
				                  <div class="ms_container row"  style="margin-left: 10px;"> 
										<div class="ms_selectable col-sm-5 form-group">
											<label><spring:message htmlEscape="true" code="Label.SUBDISTRICTLIST"></spring:message></label>
											<form:select name="sourceSubDistrict"  id="sourceSubDistrict" path="" multiple="multiple" class="form-control">
											</form:select>
										</div>
										<div class="ms_buttons col-sm-2"><br />
											<button type="button" id="btnaddSubDistrictFull"  name="Submit4" onclick="addItemPartCheck('contributedSubDistirct','sourceSubDistrict','FULL','T');" class="btn btn-primary btn-xs btn-block">Whole<i class="fa fa-angle-double-right" aria-hidden="true"></i>
											</button>
											<button type="button" id="buttonRemove1"  onclick="removeOnedistrictOption('contributedSubDistirct', 'sourceSubDistrict', true,3)" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-left" aria-hidden="true"></i>
											</button>
											<button type="button" id="buttonRemove2"  onclick="removeAllList('contributedSubDistirct', 'sourceSubDistrict', true,3)" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
											</button>
											<button type="button" id="btnaddSubDistrictPart" name="Submit4" onclick="addItem('contributedSubDistirct','sourceSubDistrict','PART',true);" class="btn btn-primary btn-xs btn-block">Part<i class="fa fa-angle-double-right" aria-hidden="true"></i>
											</button>
											
										</div>
										<div class="ms_selection col-sm-5">
											<label><spring:message htmlEscape="true" code="Label.CONTRIBUTESUBDISTRICTLIST"></spring:message></label>
											<form:select name="select4" id="contributedSubDistirct" multiple="multiple" path="" class="form-control">
											</form:select>
											<button type="button"   class="btn btn-primary" onclick="selectVillageList('N')">
												Get Village List
											</button>
											<button type="button"   class="btn btn-primary" onclick="getGPList('N')">
												Get Gram Panchayat List
											</button>
	                                	</div>
				                  </div>
				                  
				                  
				                  <div id="villageListId" style="visibility: hidden; display: none;margin-left: 10px;" class="ms_container row"> 
										<div class="ms_selectable col-sm-5 form-group">
											<label><spring:message htmlEscape="true" code="Label.VILLAGELIST"></spring:message></label>
											<form:select name="souceVilalgeList"  id="souceVilalgeList" path="" multiple="multiple" class="form-control">
											</form:select>
										</div>
										<div class="ms_buttons col-sm-2"><br />
											<button type="button" id="btnaddVillageFull"  name="Submit4" onclick="addItem('villageListContributed','souceVilalgeList','FULL',true);" class="btn btn-primary btn-xs btn-block">Whole<i class="fa fa-angle-double-right" aria-hidden="true"></i>
											</button>
											<button type="button" id="buttonRemove1" name="button22" onclick="removeOnedistrictOption('villageListContributed', 'souceVilalgeList', true,0)" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-left" aria-hidden="true"></i>
											</button>
											<button type="button" id="buttonRemove2"  name="button22" onclick="removeAllList('villageListContributed', 'souceVilalgeList', true,0)" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
											</button>
										</div>
										<div class="ms_selection col-sm-5">
											<label><spring:message htmlEscape="true" code="Label.CONTRIBUTVILLAGELIST"></spring:message></label>
											<form:select name="select4" id="villageListContributed" multiple="multiple" path="" class="form-control">
											</form:select>
	                                	</div>
				                  </div>
				                  
				                  <div id="gpListId" style="visibility: hidden; display: none ;margin-left: 10px;" class="ms_container row" > 
										<div class="ms_selectable col-sm-5 form-group">
											<label><spring:message htmlEscape="true" code="Label.GRAMPANCHAYTLIST"></spring:message></label>
											<form:select name="sourceGP"  id="sourceGP" path="" multiple="multiple" class="form-control">
											</form:select>
										</div>
										<div class="ms_buttons col-sm-2"><br />
											<button type="button" id="btnAddGPFull"  name="Submit4" onclick="addItemPartCheck('contributedGP','sourceGP','FULL','GP');" class="btn btn-primary btn-xs btn-block">Whole<i class="fa fa-angle-double-right" aria-hidden="true"></i>
											</button>
											<button type="button" id="buttonRemove1"  onclick="removeOnedistrictOption('contributedGP', 'sourceGP', true,4)" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-left" aria-hidden="true"></i>
											</button>
											<button type="button" id="buttonRemove2"  name="button22" onclick="removeAllList('contributedGP', 'sourceGP', true,4)" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
											</button>
											<button type="button" id="btnAddGPPart"  name="Submit4" onclick="addItem('contributedGP','sourceGP','PART',true);" class="btn btn-primary btn-xs btn-block">Part<i class="fa fa-angle-double-right" aria-hidden="true"></i>
											</button>
										</div>
										<div class="ms_selection col-sm-5">
											<label><spring:message htmlEscape="true" code="Label.CONTRIGRAMPANCHAYT"></spring:message></label>
											<form:select name="select4" id="contributedGP" multiple="multiple" path="" class="form-control">
											</form:select>
											<button type="button"   class="btn btn-primary" onclick="getCoveredAreaofLocalBodyList('N')">Get Gram Panchayat Village List
											</button>
	                                	</div>
				                  </div>
				                  
				                  <div id="gpVillageListId" style="visibility: hidden; display: none;margin-left: 10px" class="ms_container row"> 
										<div class="ms_selectable col-sm-5 form-group">
											<label><spring:message htmlEscape="true" code="Label.GPVILLAGELIST"></spring:message></label>
											<form:select name="sourceGpVillageList"  id="sourceGpVillageList" path="" multiple="multiple" class="form-control">
											</form:select>
										</div>
										<div class="ms_buttons col-sm-2"><br />
											<button type="button" id="btnAddGpVillageFull"  name="Submit4" onclick="addItem('gpVillageListContributed','sourceGpVillageList','FULL',true);" class="btn btn-primary btn-xs btn-block">Whole<i class="fa fa-angle-double-right" aria-hidden="true"></i>
											</button>
											<button type="button" id="buttonRemove1" name="button22" onclick="removeOnedistrictOption('gpVillageListContributed', 'sourceGpVillageList', true,0)" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-left" aria-hidden="true"></i>
											</button>
											<button type="button"  name="button22" onclick="removeAllList('gpVillageListContributed', 'sourceGpVillageList', true,0)" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
											</button>
											
										</div>
										<div class="ms_selection col-sm-5">
											<label><spring:message htmlEscape="true" code="Label.GPCONTRIBUTVILLAGELIST"></spring:message></label>
											<form:select name="select4" id="gpVillageListContributed" multiple="multiple" path="" class="form-control">
											</form:select>
											
	                                	</div>
	                                	
	                                	  <div class="col-sm-offset-2 col-sm-10">
				                    <div class="pull-right">
											<button type="button" name="button2" onclick="emptyLRList()" class="btn btn-primary"> <spring:message code="Button.RESET" htmlEscape="true"></spring:message>  <i class="fa fa-floppy-o"></i></button>
									</div>
									</div>
				                  </div>
				                  
				                    
									
								</div>
							
							</div>
							
							<div class="box-footer">
			                     <div class="col-sm-offset-2 col-sm-10">
			                       <div class="pull-right">
			                            <button type="button" id="submit1" name="Submit" class="btn btn-success" onclick="ValSubmitChangeCoverageEntityForDraft();"><i class="fa fa-floppy-o"></i> <spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button>
										<button type="button" name="Submit6" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
			                        </div>
			                     </div>   
                  			</div>
							
							<div id="dialog-clearform" title="Clear Details" style="display: none">
								<p>
									<span class="ui-icon ui-icon-alert"></span> All details entered will be lost, Do you still want to clear the form?
								</p>
							</div>
							</form:form>
							<script src="/LGD/JavaScriptServlet"></script>
						</div>
				</div>
			</section>
		</div>
	</section>
</body>
</html>