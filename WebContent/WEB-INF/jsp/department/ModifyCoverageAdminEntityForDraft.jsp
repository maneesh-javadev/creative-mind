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
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script src="js/departmentAdminUnit.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
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
<body onload="modifyCoverageOptions('${coverageEntityTypeList}','D','${isExistOverlap}')">
	<div id="frmcontent">
		<div class="frmhd">

			<h3 class="subtitle">
				<spring:message code="Label.AdminUnit" htmlEscape="true" text="Administrative Unit Entity"></spring:message>
			</h3>
			<ul id="showhelp" class="listing">
				<%--//these links are not working <li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>

		<div class="frmpnlbrdr">
			<form:form action="saveAdminModifyCoverage.htm" method="POST" id="form1" commandName="departmentAdminForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="saveAdminModifyCoverage.htm"/>" />
				<input type="hidden" name="stateCode" id="stateCode" value="<c:out value="${stateCode}" escapeXml="true"></c:out>" />
				<input type="hidden" name="unitLevelCode" id="unitLevelCode" value="<c:out value="${departmentAdminForm.adminUnitLevelCode}" escapeXml="true"></c:out>" />
				<input type="hidden" name="adminCoverage" id="adminCoverage" value="" />
				<input type="hidden" name="wardCoverage" id="wardCoverage" value="" />
				<input type="hidden" name="lbidWard" id='lbidWard' />
				<!-- <input type="hidden" name="deletedCoverage" id='deletedCoverage' /> -->
				<input type="hidden" name="overlappValue" id ='overlappValue' value='${isExistOverlap}' />
				<input type="hidden" name="adminUnitCode" id='adminUnitCode' value="<c:out value="${departmentAdminForm.adminUnitCode}" escapeXml="true"></c:out>" />
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.GENDETAILENTITY" htmlEscape="true" text="General Details of Admin Unit Entity"></spring:message>
							</div>
							<ul class="blocklist">
								<li class="lblBold">
									<label><spring:message code="Label.AdminUnitEntityEng" text="Administrative Unit Entity (In English)"></spring:message></label><br />
									<label><c:out value="${departmentAdminForm.adminLevelNameEnglish}" escapeXml="true"></c:out></label><br />
								</li>
								<li class="lblBold">
									<label><spring:message code="Label.AdminUnitEntityLoc" text="Administrative Unit Entity (In Local)"></spring:message></label><br />
									<label><c:out value="${departmentAdminForm.adminLevelNameLocal}" escapeXml="true"></c:out></label><br />
								</li>
								<li></li>
								<li></li>
							</ul>
							<br />
							<c:if test="${! empty entityCoverageData}">
								<table width="98%">
									<tr align="center">
										<td colspan="4"><label><h2>
													<spring:message code="Label.COVEREDAREA" htmlEscape="true"></spring:message>
												</h2> </label></td>
									</tr>
								</table>
								<br />
								<table class="tbl_with_brdr" width="98%" id="coverageTable">
									<tr class="tblRowTitle tblclear">
										<td width="10%"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
										<td width="25%"><spring:message code="Label.ENTITYTYPE" htmlEscape="true"></spring:message></td>
										<td width="50%"><spring:message code="Label.ENTITYNAMEENGLISH" htmlEscape="true"></spring:message></td>

										<td width="15%"><spring:message code="Label.TYPE" htmlEscape="true"></spring:message></td>
										<%-- <td width="15%"><spring:message code="Label.DELETE"
													htmlEscape="true"></spring:message></td> --%>

									</tr>
									<c:forEach var="adminEntityDetail" varStatus="row" items="${entityCoverageData}">
										<tr class="tblRowB">
											<td><c:out value="${row.count}" escapeXml="true" /></td>
											<td><c:out value="${adminEntityDetail.entityTypeName}" escapeXml="true"></c:out></td>
											<td><c:out value="${adminEntityDetail.entityName}" escapeXml="true"></c:out></td>
											<td><c:out value="${adminEntityDetail.coverageType}" escapeXml="true"></c:out></td>
											<%-- <td><a href="#"><img src="images/delete.png"
														onclick="javascript:deleteAddedCoverege('${adminEntityDetail.entityCode}');"
														width="18" height="19" border="0" /></a></td> --%>
										</tr>
									</c:forEach>
								</table>
							</c:if>
							<%-- <ul class="blocklist">
								
										<li>
											<label> <spring:message
																	code="Label.DEFINEADMINCOVERAGENNEW"
																	text="Add New coverage of the Administration Unit Entity"
																	htmlEscape="true"></spring:message>&nbsp;
														</label></li>
										<li>
												<label> <form:radiobutton
																					id="coverageExisttrue" path="coverageExist"
																					htmlEscape="true" value="true"
																					name="coverageExisttrue"
																					onclick="modifyCoverageOptions()" />
																		</label> YES
																		
												<label> <form:radiobutton
																					id="coverageExistfalse" path="coverageExist"
																					htmlEscape="true" value="false"
																					name="coverageExistfalse"
																					onclick="modifyCoverageOptions()" />
																		</label>NO
																		
										
										</li>
										<li></li>
										<li></li>
										<li></li>
								
								</ul> --%>
							<br />
						</div>
					</div>
					<div id="coveragediv" class="frmpnlbg" style="visibility: hidden; display: none;">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.CONTRIBUTINGLANDREGION" htmlEscape="true"></spring:message>
							</div>
							<input type="checkbox" id="existingLB" onclick="toggleLBCovergeDiv();"></input> <label><spring:message code="existingLB" text="Select From Existing Local Bodies"></spring:message></label> &nbsp; &nbsp; &nbsp; &#09; &#09; &#09; &#09;<input
								type="checkbox" id="existingLR" onclick="toggleLRCovergeDiv()"></input> <label><spring:message code="existingLR" text="Select From Land Region"></spring:message></label> <br />
							<div id="LBRegion" style="visibility: hidden; display: none" class="frmtxt">
								<div>
									<label><spring:message code="Label.EXISTINGLANDREGION"></spring:message></label>
								</div>
								<ul class="blocklist">
									<li class="margintoleft"><label><spring:message code="Label.SELECTLOCALBODYTYPE" text="Select Zilla  Panchyat"></spring:message></label> <span class="errormsg">*</span><br /> <br /> <form:select htmlEscape="true" path=""
											class="combofield" id="ulbID" onchange="getUrbanlocabodies(this.value);">
											<form:option value="0">
												<spring:message code="Label.SELECT"></spring:message>
											</form:option>
											<form:options items="${uLBTypeList}" itemLabel="nomenclatureEnglish" itemValue="localBodyTypeCode" />
										</form:select><br /> <br />
										<div class="errormsg"></div> <span class="errormsg" id="ddDestDistrict_error"> </span></li>
									<li>
										<div class="ms_container">
											<div class="ms_selectable">
												<strong> <spring:message htmlEscape="true" code="Label.AVAILABLEURBANLB"></spring:message> <form:select path="" class="frmtxtarea" id="SourceLBList" multiple="true">

													</form:select>
												</strong>
											</div>
											<div class="ms_buttons">
												<input id="src2Target1" class="bttn" type="button" onclick="addItemPartCheck('contributedLBList','SourceLBList','FULL','G');" value=" Whole &gt;&gt;" /> <input id="target2Src1" class="bttn" type="button"
													onclick="removeOnedistrictOption('contributedLBList', 'SourceLBList', true,1);" value="&lt;" /> <input id="target2Src2" type="button" class="bttn" onclick="removeAllList('contributedLBList', 'SourceLBList', true,1);" value="&lt;&lt;" />

												<input id="src2Target2" type="button" class="bttn" onclick="addItem('contributedLBList','SourceLBList', 'PART',true);" value="Part &gt;&gt;" />
											</div>
											<div class="ms_selection">
												<strong> <spring:message htmlEscape="true" code="Label.CONTRIBUTEURBANLB"></spring:message>
												</strong>
												<form:select name="select4" id="contributedLBList" size="1" multiple="multiple" path="" class="frmtxtarea">
												</form:select>

												<input name="button2" class="btn" type="button" onclick="getWardList('N')" value="Get Ward List" />
											</div>
										</div>
									</li>
									<li><div class="clear"></div></li>
									<li>
										<div class="ms_container">
											<div class="ms_selectable">
												<strong><spring:message htmlEscape="true" code="Label.AVAILEWARDLIST"></spring:message> </strong>

												<form:select name="souceWardList" size="1" id="souceWardList" path="" multiple="multiple" class="frmtxtarea">
												</form:select>
											</div>
											<div class="ms_buttons">
												<label> <input type="button" class="bttn" id="btnaddSubDistrictFull" name="Submit4" onclick="addItem('contributedWardList','souceWardList','FULL',true);" value=" Whole &gt;&gt;" />
												</label> <input id="buttonRemove1" class="bttn" type="button" onclick="removeOnedistrictOption('contributedWardList', 'souceWardList', true,0)" value="&lt;" /> <input id="buttonRemove2" type="button" class="bttn"
													onclick="removeAllList('contributedWardList', 'souceWardList', true,0)" value="&lt;&lt;" />

											</div>
											<div class="ms_selection">
												<strong> <spring:message htmlEscape="true" code="Label.CONTRIBUTWARDLIST"></spring:message>
												</strong>

												<form:select name="select4" id="contributedWardList" size="1" multiple="multiple" path="" class="frmtxtarea" htmlEscape="true">
												</form:select>
												<input name="button2" class="btn" type="button" onclick="emptyLBList()" value="<spring:message code="Button.RESET" htmlEscape="true"></spring:message>" />
											</div>
										</div>
									</li>
									<li></li>
								</ul>
								<div class="clear"></div>
							</div>
							<div id="LRRegion" style="visibility: hidden; display: none" class="frmtxt">
								<div>
									<label><spring:message code="Label.ADMINEXISTINGLANDREGION" text="Existing Land Regions"></spring:message></label>
								</div>
								<ul class="blocklist">
									<!-- block start for State at center Administrative Entities. -->
									<li id="stateSelectionBlock" style="visibility: hidden; display: none">
										<div class="ms_container">
											<div class="ms_selectable">
												<strong> <spring:message htmlEscape="true" code="Label.STATELIST"></spring:message>
												</strong> <br> <form:select path="" class="frmtxtarea" id="SourceState" multiple="true">
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
											<div class="ms_buttons">
												<input id="src2Target1" class="bttn" type="button" onclick="addItemPartCheck('contributedState','SourceState','FULL','S');" value=" Whole &gt;&gt;" /> <input id="target2Src1" class="bttn" type="button"
													onclick="removeOnedistrictOption('contributedState', 'SourceState', true,5);" value="&lt;" /> <input id="target2Src2" class="bttn" type="button" onclick="removeAllList('contributedState', 'SourceState', true,5);" value="&lt;&lt;" /> <input
													id="src2Target2" class="bttn" type="button" onclick="addItem('contributedState','SourceState', 'PART',true);" value="Part &gt;&gt;" />
											</div>
											<div class="ms_selection">
												<strong> <spring:message htmlEscape="true" code="Label.CONTStateList" text="Contributing States List"></spring:message>
												</strong>
												<form:select name="select4" id="contributedState" size="1" multiple="multiple" path="" class="frmtxtarea">
												</form:select>
												<input name="button2" class="bttn" type="button" onclick="getDistrictList('N')" value="Get District List" />
											</div>
										</div>
									</li>
									<!-- block end for State at center Administrative Entities. -->
									<li><div class="clear"></div></li>
									<li>
										<div class="ms_container">
											<div class="ms_selectable">
												<strong> <spring:message htmlEscape="true" code="Label.DISTRICTLIST"></spring:message>
												</strong> <br> <form:select path="" class="frmtxtarea" id="SourceDistrict" multiple="true">

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
											<div class="ms_buttons">
												<input id="src2Target1" class="bttn" type="button" onclick="addItemPartCheck('contributedDistirct','SourceDistrict','FULL','D');" value=" Whole &gt;&gt;" /> <input id="target2Src1" type="button" class="bttn"
													onclick="removeOnedistrictOption('contributedDistirct', 'SourceDistrict', true,2);" value="&lt;" /> <input id="target2Src2" type="button" class="bttn" onclick="removeAllList('contributedDistirct', 'SourceDistrict', true,2);"
													value="&lt;&lt;" /> <input id="src2Target2" type="button" class="bttn" onclick="addItem('contributedDistirct','SourceDistrict', 'PART',true);" value="Part &gt;&gt;" />
											</div>
											<div class="ms_selection">
												<strong> <spring:message htmlEscape="true" code="Label.CONTRIBUTEDISTRICTLIST"></spring:message>
												</strong></br>
												<form:select name="select4" id="contributedDistirct" size="1" multiple="multiple" path="" class="frmtxtarea">
												</form:select>
												<input name="button2" class="btn" type="button" onclick="getSubdistrictsList('N')" value="Get Sub-district List" />
											</div>
										</div>
									</li>
									<li><div class="clear"></div></li>
									<li>
										<div class="ms_container">
											<div class="ms_selectable">
												<strong><spring:message htmlEscape="true" code="Label.SUBDISTRICTLIST"></spring:message> </strong></br>
												<form:select name="sourceSubDistrict" size="1" id="sourceSubDistrict" path="" multiple="multiple" class="frmtxtarea">
												</form:select>
											</div>
											<div class="ms_buttons">
												<label> <input type="button" class="bttn" id="btnaddSubDistrictFull" name="Submit4" onclick="addItemPartCheck('contributedSubDistirct','sourceSubDistrict','FULL','T');" value=" Whole &gt;&gt;" />
												</label> <input id="buttonRemove1" type="button" class="bttn" onclick="removeOnedistrictOption('contributedSubDistirct', 'sourceSubDistrict', true,3)" value="&lt;" /> <input id="buttonRemove2" class="bttn" type="button"
													onclick="removeAllList('contributedSubDistirct', 'sourceSubDistrict', true,3)" value="&lt;&lt;" /> <label> <input type="button" class="bttn" id="btnaddSubDistrictPart" name="Submit4" value=" Part &gt;&gt;"
													onclick="addItem('contributedSubDistirct','sourceSubDistrict','PART',true);" />
												</label>
											</div>
											<div class="ms_selection">
												<strong> <spring:message htmlEscape="true" code="Label.CONTRIBUTESUBDISTRICTLIST"></spring:message>
												</strong> </br>
												<form:select name="select4" id="contributedSubDistirct" size="1" multiple="multiple" path="" class="frmtxtarea" htmlEscape="true">
												</form:select>
												<input type="button" class="btn" value="Get Village List" onclick="selectVillageList('N');" /> <input type="button" class="btn" value="Get Gram Panchayat List" onclick="getGPList('N')" />
											</div>
										</div>
									</li>
									<li><div class="clear"></div></li>
									<li id="villageListId" style="visibility: hidden; display: none">
										<div class="ms_container">
											<div class="ms_selectable">
												<strong><spring:message htmlEscape="true" code="Label.VILLAGELIST"></spring:message> </strong></br>
												<form:select name="souceVilalgeList" size="1" id="souceVilalgeList" path="" multiple="multiple" class="frmtxtarea">
												</form:select>
											</div>
											<div class="ms_buttons">

												<label> <input type="button" class="bttn" id="btnaddVillageFull" name="Submit4" onclick="addItem('villageListContributed','souceVilalgeList','FULL',true);" value=" Whole &gt;&gt;" />
												</label> <input name="button22" class="bttn" type="button" onclick="removeOnedistrictOption('villageListContributed', 'souceVilalgeList', true,0)" value="&lt;" /> <input name="button22" class="bttn" type="button"
													onclick="removeAllList('villageListContributed', 'souceVilalgeList', true,0)" value="&lt;&lt;" />
											</div>
											<div class="ms_selection">
												<strong> <spring:message htmlEscape="true" code="Label.CONTRIBUTVILLAGELIST"></spring:message>
												</strong>

												<form:select name="select4" id="villageListContributed" size="1" multiple="multiple" path="" class="frmtxtarea">
												</form:select>
												<%-- <input name="button2" class="btn" type="button" onclick="emptyLRList()" value="<spring:message code="Button.RESET" htmlEscape="true"></spring:message>" /> --%>
											</div>
										</div>
									</li>
									<li><div class="clear"></div></li>
									<li id="gpListId" style="visibility: hidden; display: none">
										<div class="ms_container">
											<div class="ms_selectable">
												<strong><spring:message htmlEscape="true" code="Label.GRAMPANCHAYTLIST" text="Gram Panchayat List"></spring:message> </strong></br>
												<form:select name="sourceGP" size="1" id="sourceGP" path="" multiple="multiple" class="frmtxtarea">
												</form:select>
											</div>
											<div class="ms_buttons">
												<label> <input type="button" class="bttn" id="btnAddGPFull" name="Submit4" onclick="addItemPartCheck('contributedGP','sourceGP','FULL','GP');" value=" Whole &gt;&gt;" /></label> <input id="buttonRemove1" type="button" class="bttn"
													onclick="removeOnedistrictOption('contributedGP', 'sourceGP', true,4)" value="&lt;" /> <input id="buttonRemove2" class="bttn" type="button" onclick="removeAllList('contributedGP', 'sourceGP', true,4)" value="&lt;&lt;" /> <label>
													<input type="button" class="bttn" id="btnAddGPPart" name="Submit4" value=" Part &gt;&gt;" onclick="addItem('contributedGP','sourceGP','PART',true);" />
												</label>
											</div>
											<div class="ms_selection">
												<strong> <spring:message htmlEscape="true" code="Label.CONTRIGRAMPANCHAYT"></spring:message>
												</strong> </br>
												<form:select name="select4" id="contributedGP" size="1" multiple="multiple" path="" class="frmtxtarea" htmlEscape="true">
												</form:select>
												<input type="button" class="btn" value="Get Gram Panchayat Village List" onclick="getCoveredAreaofLocalBodyList('N')" />
											</div>
										</div>
									</li>
									<li><div class="clear"></div></li>
									<li id="gpVillageListId" style="visibility: hidden; display: none">
										<div class="ms_container">
											<div class="ms_selectable">
												<strong><spring:message htmlEscape="true" code="Label.GPVILLAGELIST" text="Gram Panchayat Village List"></spring:message> </strong></br>
												<form:select name="sourceGpVillageList" size="1" id="sourceGpVillageList" path="" multiple="multiple" class="frmtxtarea">
												</form:select>
											</div>
											<div class="ms_buttons">
												<label> <input type="button" class="bttn" id="btnAddGpVillageFull" name="Submit4" onclick="addItem('gpVillageListContributed','sourceGpVillageList','FULL',true);" value=" Whole &gt;&gt;" /></label> <input name="button22" class="bttn"
													type="button" onclick="removeOnedistrictOption('gpVillageListContributed', 'sourceGpVillageList', true,0)" value="&lt;" /> <input name="button22" class="bttn" type="button"
													onclick="removeAllList('gpVillageListContributed', 'sourceGpVillageList', true,0)" value="&lt;&lt;" />
											</div>
											<div class="ms_selection">
												<strong> <spring:message htmlEscape="true" code="Label.GPCONTRIBUTVILLAGELIST" text="Contributing Gram Panchayat Village List"></spring:message>
												</strong>
												<form:select name="select4" id="gpVillageListContributed" size="1" multiple="multiple" path="" class="frmtxtarea">
												</form:select>
												<%-- <input name="button2" class="btn" type="button" onclick="emptyLRList()" value="<spring:message code="Button.RESET" htmlEscape="true"></spring:message>" /> --%>
											</div>
										</div>
									</li>
									<li><div class="clear"></div></li>
									<li>
										<div style="width: 100%;">
											<div style="margin-left: 550px;">
												<input name="button2" class="btn" type="button" onclick="emptyLRList()" value="<spring:message code="Button.RESET" htmlEscape="true"></spring:message>" />
											</div>
										</div>
									</li>
								</ul>
								<div class="clear"></div>
							</div>
						</div>
					</div>
					<div class="btnpnl">
						<div>
							<label> <input type="button" class="btn" name="Submit" onclick="ValSubmitChangeCoverageEntityForDraft()" id="submit1" value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" />
							</label> <label><input type="button" name="Submit6" class="btn" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
						</div>
					</div>
				</div>
				<div id="dialog-clearform" title="Clear Details" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert"></span> All details entered will be lost, Do you still want to clear the form?
					</p>
				</div>
			</form:form>
		</div>
		<script src="/LGD/JavaScriptServlet"></script>
	</div>
</body>
</html>