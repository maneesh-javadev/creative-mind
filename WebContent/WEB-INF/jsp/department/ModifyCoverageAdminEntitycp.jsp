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
<link href="HTML_panchayat - 2.2/css/panchayat_main.css" rel="stylesheet" type="text/css" />
<script src="js/departmentAdminUnit.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<!-- <script type="text/javascript" src="js/invalidatetrilocalbody.js"
	charset="utf-8"></script> -->
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
<body onload="modifyCoverageOptions('${coverageEntityTypeList}','P','${isExistOverlap}');">
	<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                    <form:form action="saveAdminModifyCoverage.htm" method="POST" id="form1" commandName="departmentAdminForm" class="form-horizontal">
					    <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="saveAdminModifyCoverage.htm"/>" />
				        <input type="hidden" name="stateCode" id="stateCode" value="<c:out value='${stateCode}'  escapeXml='true'></c:out>" />
				        <input type="hidden" name="unitLevelCode" id="unitLevelCode" value="<c:out value='${departmentAdminForm.adminUnitLevelCode}'  escapeXml='true'></c:out>" />
				        <input type="hidden" name="adminCoverage" id="adminCoverage" value="" />
						<input type="hidden" name="wardCoverage" id="wardCoverage" value="" />
						<input type="hidden" name="lbidWard" id='lbidWard' />
						<input type="hidden" name="overlappValue" id ='overlappValue' value='${isExistOverlap}' />
						<form:hidden path="adminUnitLevelCode" />
						<form:hidden path="parentAdminCode" />
						
						
				       <!-- <input type="hidden" name="deletedCoverage" id='deletedCoverage' /> -->
				       <input type="hidden" name="adminUnitCode" id='adminUnitCode' value="<c:out value='${departmentAdminForm.adminUnitCode}'  escapeXml='true'></c:out>" />
						  
				 
                    <div class="box">
                            <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.AdminUnit" htmlEscape="true" text="Administrative Unit Entity"></spring:message></h3>
                                </div>
                        
                                 <div class="box-header subheading">
                                    <h4><spring:message code="Label.GENDETAILENTITY" htmlEscape="true" text="General Details of Admin Unit Entity"></spring:message></h4>
                                </div>
                 <div class="box-body">
                   <table class="table table-bordered table-hover" >
									<tr >
										<td width="50%"><spring:message code="Label.AdminUnitEntityEng" text="Administrative Unit Entity (In English)"></spring:message></td>
										<td width="50%"><c:out value="${departmentAdminForm.adminLevelNameEnglish}"  escapeXml="true"></c:out></td>
										
									</tr>
									<tr >
										<td ><spring:message code="Label.AdminUnitEntityLoc" text="Administrative Unit Entity (In Local)"></spring:message></td>
										<td > <c:out value="${departmentAdminForm.adminLevelNameLocal}" escapeXml="true"></c:out></td>
										
									</tr>
									
					 </table>
     
     
       <c:if test="${! empty entityCoverageData}">
								<div class="box-header subheading"><h4><spring:message code="Label.COVEREDAREA" htmlEscape="true"></spring:message></h4></div>
								<table class="table table-bordered table-hover" id="coverageTable">
									<tr >
										<th><spring:message code="Label.SNO" htmlEscape="true"></spring:message></th>
										<th><spring:message code="Label.ENTITYTYPE" htmlEscape="true"></spring:message></th>
										<th><spring:message code="Label.ENTITYNAMEENGLISH" htmlEscape="true"></spring:message></th>
										<th><spring:message code="Label.TYPE" htmlEscape="true"></spring:message></th>
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
							
			
			<div id="coveragediv"  style="visibility: hidden; display: none;">
			   <div class="box-header subheading">
						<h4><spring:message code="Label.CONTRIBUTINGLANDREGION" htmlEscape="true"></spring:message></h4>
				</div>
			<div class="form-group" style="margin-left: 10px;">
			    <label class="checkbox-inline"><input type="checkbox" id="existingLB" onclick="toggleLBCovergeDiv();"></input><spring:message code="existingLB" text="Select From Existing Local Bodies"></spring:message></label>
			     <label class="checkbox-inline"><input type="checkbox" id="existingLR" onclick="toggleLRCovergeDiv()"></input><spring:message code="existingLR" text="Select From Land Region"></spring:message></label>
			</div>
	    <div id="LBRegion" style="visibility: hidden; display: none" >
			<div>
				<label><spring:message code="Label.EXISTINGLANDREGION"></spring:message></label>
			</div>
			
			<div class="ms_container row" style="margin-left: 10px;" >
	                 <div class="ms_selectable col-sm-5 form-group">
		                  <label ><spring:message htmlEscape="true" code="Label.AVAILABLEURABLBTYPE"></spring:message> </label>
		                  <form:select path="" class="form-control" id="lbTypeList" multiple="true">
								<form:options items="${uLBTypeList}" itemLabel="nomenclatureEnglish" itemValue="localBodyTypeCode" />
						 </form:select>
		               </div>
					 <div class="ms_buttons col-sm-2"><br>
						<button class="btn btn-primary btn-xs btn-block" type="button" onclick="moveElement('FORWARD');" value="&gt;" >&gt;</button>
						<button class="btn btn-primary btn-xs btn-block" type="button" onclick="moveElement('BACK');" value="&lt;" >&lt;</button>
						<button class="btn btn-primary btn-xs btn-block" type="button" onclick="moveElement('BACKALL');" value="&lt;&lt;" >&lt;&lt;</button>
						<button class="btn btn-primary btn-xs btn-block" type="button" onclick="moveElement('FORWARDALL');;" value="&gt;&gt;" >&gt;&gt;</button>
					 </div>
					<div class="ms_selection col-sm-5">
					 <div class="form-group">
					    <label ><spring:message htmlEscape="true" code="Label.CONTRIURABLBTYPE"></spring:message></label> 
					    <form:select name="select4" id="contributedLBTypeList"  multiple="multiple" path="" class="form-control">
								<form:options items="${uLBTypeListCoverage}" itemLabel="nomenclatureEnglish" itemValue="localBodyTypeCode" />
						 </form:select>
						   <button name="button2" class="btn btn-primary" type="button" onclick="getLbList();" value="Get Urban List" >Get Urban List</button>
					     </div>				
			            </div>
			    </div>
			
			
			<div class="ms_container row" style="margin-left: 10px;" >
	                 <div class="ms_selectable col-sm-5 form-group">
		                  <label ><spring:message htmlEscape="true" code="Label.AVAILABLEURBANLB"></spring:message> </label>
		                    <form:select path="" class="form-control" id="SourceLBList" multiple="true">
							</form:select>
		               </div>
					 <div class="ms_buttons col-sm-2"><br>
						 <button id="src2Target1" class="btn btn-primary btn-xs btn-block" type="button" onclick="addItemPartCheck('contributedLBList','SourceLBList','FULL','G');" >Whole &gt;&gt;</button>
								 <button id="target2Src1" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeOnedistrictOption('contributedLBList', 'SourceLBList', true,1);"  > &lt;</button>
								 <button id="target2Src2" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeAllList('contributedLBList', 'SourceLBList', true,1);"  >&lt;&lt;</button>
                                 <button id="src2Target2" class="btn btn-primary btn-xs btn-block" type="button" onclick="addItem('contributedLBList','SourceLBList', 'PART',true);" >Part &gt;&gt;</button>
					 </div>
					<div class="ms_selection col-sm-5">
					 <div class="form-group">
					    <label ><spring:message htmlEscape="true" code="Label.CONTRIBUTEURBANLB"></spring:message></label> 
					      <form:select name="select4" id="contributedLBList"  multiple="multiple" path="" class="form-control">
							</form:select>
						   <button name="button2" class="btn btn-primary" type="button" onclick="getWardList('N')"  >Get Ward List</button>
					     </div>				
			            </div>
			    </div>
			    
			    <div class="ms_container row"  style="margin-left: 10px;">
	                 <div class="ms_selectable col-sm-5 form-group">
		                  <label ><spring:message htmlEscape="true" code="Label.AVAILEWARDLIST"></spring:message> </label>
		                    <form:select name="souceWardList"  id="souceWardList" path="" multiple="multiple" class="form-control">
												</form:select>
		               </div>
					 <div class="ms_buttons col-sm-2"><br>
						   <buttton type="button" class="btn btn-primary btn-xs btn-block" id="btnaddSubDistrictFull" name="Submit4" onclick="addItem('contributedWardList','souceWardList','FULL',true);"  >Whole &gt;&gt;</buttton>
						   <buttton id="buttonRemove1" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeOnedistrictOption('contributedWardList', 'souceWardList', true,0)"  > &lt;</buttton>
						   <buttton id="buttonRemove2" type="button" class="btn btn-primary btn-xs btn-block" onclick="removeAllList('contributedWardList', 'souceWardList', true,0)"  >&lt;&lt;</buttton>
					 </div>
					<div class="ms_selection col-sm-5">
					 <div class="form-group">
					    <label ><spring:message htmlEscape="true" code="Label.CONTRIBUTWARDLIST"></spring:message></label> 
					      <form:select name="select4" id="contributedWardList" multiple="multiple" path="" class="form-control" htmlEscape="true">
												</form:select></br>
						  <button name="button2" class="btn btn-primary" type="button" onclick="emptyLBList()" ><spring:message code="Button.RESET" htmlEscape="true"></spring:message></button>
					     </div>				
			            </div>
			    </div>
			
									
									
		 						
		  </div>
			
			<div id="LRRegion" style="visibility: hidden; display: none" >
								<div class="box-header subheading">
									<label><spring:message code="Label.ADMINEXISTINGLANDREGION" text="Existing Land Regions"></spring:message></label>
								</div>
								
									<div id="stateSelectionBlock" style="visibility: hidden; display: none">
									    <div class="ms_container row"   style="margin-left: 10px;">
							                 <div class="ms_selectable col-sm-5 form-group">
								                  <label ><spring:message htmlEscape="true" code="Label.STATELIST"></spring:message> </label>
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
											 <div class="ms_buttons col-sm-2"><br>
												  	<button id="src2Target1" class="btn btn-primary btn-xs btn-block" type="button" onclick="addItemPartCheck('contributedState','SourceState','FULL','S');"  >Whole &gt;&gt;</button> 
													<button id="target2Src1" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeOnedistrictOption('contributedState', 'SourceState', true,5);" >&lt; </button>
													<button id="target2Src2" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeAllList('contributedState', 'SourceState', true,5);"  >&lt;&lt;</button>
													<button  id="src2Target2" class="btn btn-primary btn-xs btn-block" type="button" onclick="addItem('contributedState','SourceState', 'PART',true);"  >Part &gt;&gt;</button>
											 </div>
											<div class="ms_selection col-sm-5">
											 <div class="form-group">
											    <label ><spring:message htmlEscape="true" code="Label.CONTStateList" text="Contributing States List"></spring:message></label> 
											      <form:select name="select4" id="contributedState" multiple="multiple" path="" class="form-control">
												    </form:select>
												  <button name="button2" class="btb btn-primary" type="button" onclick="getDistrictList('N')" value="Get District List" >Get District List</button>
											     </div>				
									            </div>
									    </div>
									</div>
									
									
									<!-- block end for State at center Administrative Entities. -->
								 <div class="ms_container row"   style="margin-left: 10px;">
							                 <div class="ms_selectable col-sm-5 form-group">
								                  <label ><spring:message htmlEscape="true" code="Label.DISTRICTLIST"></spring:message></label>
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
											 <div class="ms_buttons col-sm-2"><br>
												  	<button id="src2Target1" class="btn btn-primary btn-xs btn-block" type="button" onclick="addItemPartCheck('contributedDistirct','SourceDistrict','FULL','D');" >Whole &gt;&gt;</button> 
											        <button id="target2Src1" type="button" class="btn btn-primary btn-xs btn-block" onclick="removeOnedistrictOption('contributedDistirct', 'SourceDistrict', true,2);"  > &lt;</button>
													<button id="target2Src2" type="button" class="btn btn-primary btn-xs btn-block" onclick="removeAllList('contributedDistirct', 'SourceDistrict', true,2);">&lt;&lt; </button>
													<button id="src2Target2" type="button" class="btn btn-primary btn-xs btn-block" onclick="addItem('contributedDistirct','SourceDistrict', 'PART',true);"  >Part &gt;&gt;</button>
											 </div>
											<div class="ms_selection col-sm-5">
											 <div class="form-group">
											    <label ><spring:message htmlEscape="true" code="Label.CONTRIBUTEDISTRICTLIST"></spring:message></label> 
											      <form:select name="select4" id="contributedDistirct"  multiple="multiple" path="" class="form-control">
												</form:select>

												<button name="button2" class="btn btn-primary" type="button" onclick="getSubdistrictsList('N')" value="Get Sub-district List" >Get Sub-district List</button>
											     </div>				
									            </div>
									    </div>
									    
									
									
									
									 <div class="ms_container row"   style="margin-left: 10px;">
							                 <div class="ms_selectable col-sm-5 form-group">
								                  <label ><spring:message htmlEscape="true" code="Label.SUBDISTRICTLIST"></spring:message> </label>
								                  <form:select name="sourceSubDistrict" id="sourceSubDistrict" path="" multiple="multiple" class="form-control">
												</form:select>
								               </div>
											 <div class="ms_buttons col-sm-2"><br>
												  	<button type="button" class="btn btn-primary btn-xs btn-block" id="btnaddSubDistrictFull" name="Submit4" onclick="addItemPartCheck('contributedSubDistirct','sourceSubDistrict','FULL','T');"  >Whole &gt;&gt;</button>
												 <button id="buttonRemove1" type="button" class="btn btn-primary btn-xs btn-block" onclick="removeOnedistrictOption('contributedSubDistirct', 'sourceSubDistrict', true,3)"  >&lt;</button>
												 <button id="buttonRemove2" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeAllList('contributedSubDistirct', 'sourceSubDistrict', true,3)"  >&lt;&lt;</button>  
												 <button type="button" class="btn btn-primary btn-xs btn-block" id="btnaddSubDistrictPart" name="Submit4"  onclick="addItem('contributedSubDistirct','sourceSubDistrict','PART',true);" >Part &gt;&gt;</button>
											 </div>
											<div class="ms_selection col-sm-5">
											 <div class="form-group">
											    <label ><spring:message htmlEscape="true" code="Label.CONTRIBUTESUBDISTRICTLIST"></spring:message></label> 
											     <form:select name="select4" id="contributedSubDistirct"  multiple="multiple" path="" class="form-control" htmlEscape="true">
												</form:select>

												<button type="button" class="btn btn-primary" value="Get Village List" onclick="selectVillageList('N');" >Get Village List </button>
												<button type="button" class="btn btn-primary" value="Get Gram Panchayat List" onclick="getGPList('N')" >Get Gram Panchayat List</button>
											     </div>				
									            </div>
									    </div>
									    
									    
											<div id="villageListId" style="visibility: hidden; display: none">
											 <div class="ms_container row"  style="margin-left: 10px;">
							                 <div class="ms_selectable col-sm-5 form-group">
								                  <label ><spring:message htmlEscape="true" code="Label.VILLAGELIST"></spring:message></label>
								                  <form:select name="souceVilalgeList"  id="souceVilalgeList" path="" multiple="multiple" class="form-control">
												</form:select>
								               </div>
											 <div class="ms_buttons col-sm-2"></br></br>
												  	  <button type="button" class="btn btn-primary btn-xs btn-block" id="btnaddVillageFull" name="Submit4" onclick="addItem('villageListContributed','souceVilalgeList','FULL',true);"  >Whole &gt;&gt;</button>
								                      <button name="button22" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeOnedistrictOption('villageListContributed', 'souceVilalgeList', true,0)"  >&lt; </button>
								                      <button name="button22" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeAllList('villageListContributed', 'souceVilalgeList', true,0)"  >&lt;&lt;</button>
											 </div>
											<div class="ms_selection col-sm-5">
											 <div class="form-group">
											    <label ><spring:message htmlEscape="true" code="Label.CONTRIBUTVILLAGELIST"></spring:message></label> 
											       <form:select name="select4" id="villageListContributed" multiple="multiple" path="" class="form-control">
												   </form:select>

											     </div>				
									            </div>
									    </div>
											
								</div>
									
								<div id="gpListId" style="visibility: hidden; display: none">	
								 <div class="ms_container row"  style="margin-left: 10px;">
							                 <div class="ms_selectable col-sm-5 form-group">
								                  <label ><spring:message htmlEscape="true" code="Label.GRAMPANCHAYTLIST" text="Gram Panchayat List"></spring:message>  </label>
								                 <form:select name="sourceGP" size="1" id="sourceGP" path="" multiple="multiple" class="form-control">
												</form:select>
								               </div>
											 <div class="ms_buttons col-sm-2"><br>
												  	<button type="button" class="btn btn-primary btn-xs btn-block" id="btnAddGPFull" name="Submit4" onclick="addItemPartCheck('contributedGP','sourceGP','FULL','GP');" value=" Whole &gt;&gt;" ></button>
													<button id="buttonRemove1" type="button" class="btn btn-primary btn-xs btn-block" onclick="removeOnedistrictOption('contributedGP', 'sourceGP', true,4)" value="&lt;" > </button>
													<button id="buttonRemove2" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeAllList('contributedGP', 'sourceGP', true,4)" value="&lt;&lt;" > </button>
													<button type="button" class="btn btn-primary btn-xs btn-block" id="btnAddGPPart" name="Submit4" value=" Part &gt;&gt;" onclick="addItem('contributedGP','sourceGP','PART',true);" ></button>
											 </div>
											<div class="ms_selection col-sm-5">
											 <div class="form-group">
											    <label ><spring:message htmlEscape="true" code="Label.CONTRIGRAMPANCHAYT"></spring:message></label> 
											    <form:select name="select4" id="contributedGP"  multiple="multiple" path="" class="form-control" htmlEscape="true">
												</form:select>

												<button type="button" class="btn btn-primary"  onclick="getCoveredAreaofLocalBodyList('N')" >Get Gram Panchayat Village List</button>
											     </div>				
									            </div>
									    </div>
								</div>
									
									
									<div id="gpVillageListId" style="visibility: hidden; display: none">
									
									   <div class="ms_container row" >
							                 <div class="ms_selectable col-sm-5 form-group">
								                  <label ><spring:message htmlEscape="true" code="Label.GPVILLAGELIST" text="Gram Panchayat Village List"></spring:message> </label>
								                 <form:select name="sourceGpVillageList"  id="sourceGpVillageList" path="" multiple="multiple" class="form-control">
												</form:select>
								               </div>
											 <div class="ms_buttons col-sm-2"><br>
												  	<button type="button" class="btn btn-primary btn-xs btn-block" id="btnAddGpVillageFull" name="Submit4" onclick="addItem('gpVillageListContributed','sourceGpVillageList','FULL',true);"  >Whole &gt;&gt;</button>
							 						<button name="button22" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeOnedistrictOption('gpVillageListContributed', 'sourceGpVillageList', true,0)"  >&lt;</button>
							 						<button name="button22" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeAllList('gpVillageListContributed', 'sourceGpVillageList', true,0)"  >&lt;&lt;</button>
											 </div>
											<div class="ms_selection col-sm-5">
											 <div class="form-group">
											    <label ><spring:message htmlEscape="true" code="Label.GPCONTRIBUTVILLAGELIST" text="Contributing Gram Panchayat Village List"></spring:message></label> 
											    <form:select name="select4" id="gpVillageListContributed"  multiple="multiple" path="" class="form-control">
												</form:select>
											     </div>				
									            </div>
									    </div>
									 
									 
									 
									</div>
									
									<div class="form-group">
										<div style="width: 100%;">
											<div style="margin-left: 550px;">
												<button name="button2" class="btn btn-primary" type="button" onclick="emptyLRList()" value="" ><spring:message code="Button.RESET" htmlEscape="true"></spring:message></button>
											</div>
										</div>
									</div>
									
				</div>
				
				
			</div>				
     </div>   
    
      <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                   <button type="button" class="btn btn-success" name="Submit" onclick="ValSubmitChangeCoverageEntity()" id="submit1"  ><spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button>
                   <button type="button" class="btn btn-danger " name="Cancel"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div>
		 </div>
		<div id="dialog-clearform" title="Clear Details" style="display: none">
			<p>
			  <span class="ui-icon ui-icon-alert"></span> All details entered will be lost, Do you still want to clear the form?
			</p>
	    </div>			
					  
        
        </div>
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>