<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="in.nic.pes.lgd.utils.ApplicationConstant"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@ include file="restructureDeptLevelJs.jsp"%>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>


<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrBlockService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/js/administrative-department.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/js/admin-department-validation.js'></script>


<script type="text/javascript">
	dwr.engine.setActiveReverseAjax(true);
	var _DISTRICT_LEVEL = '<%=ApplicationConstant.DISTRICT_LEVEL_CODE%>';
	var _SUBDISTRICT_LEVEL = '<%=ApplicationConstant.SUBDISTRICT_LEVEL_CODE%>';
	var _BLOCK_LEVEL = '<%=ApplicationConstant.BLOCK_LEVEL_CODE%>';
	var _VILLAGE_LEVEL = '<%=ApplicationConstant.VILLAGE_LEVEL_CODE%>';
	var _STATE_LEVEL = '<%=ApplicationConstant.STATE_LEVEL_CODE%>';
	var _ADMINISTRATIVE_LEVEL = '<%=ApplicationConstant.ADMINISTRATIVE_LEVEL_CODE%>';
	
	var accessLevel = '${pageAccessLevel}';
	var stateLevelId = '${stateCode}';
	var isOrganizationFlow = isParseJson('${isOrganizationFlow}');
	var isCenterUser = isParseJson('${isCenterUser}');
	var _continue_url = "countinueRestructureDepartmentCreatetion.htm?<csrf:token uri='countinueRestructureDepartmentCreatetion.htm'/>";
	var _save_url = "saveRestructureDepartment.htm?<csrf:token uri='saveRestructureDepartment.htm'/>";	
	$( document ).ready(function() {
		initialShowHide();
		registerOnclickMethods();
		
		
	});
</script>
</head>
<body>
	
	<c:set var="deptOrgNameEng" value="Label.DEPTNAMEINENG"></c:set>
	<c:set var="deptOrgNameLocal" value="Label.DEPTNAMEINLOCAL"></c:set>
	<c:set var="deptOrgNameShort" value="Label.SHORTDEPTNAME"></c:set>
	<c:set var="deptOrgOperationalAt" value="Label.DEPTOPERATIONALAT"></c:set>
	<c:if test="${isOrganizationFlow}">
		
		<c:set var="deptOrgNameEng" value="Label.ORGNAMEINENG"></c:set>
		<c:set var="deptOrgNameLocal" value="Label.ORGNAMEINLOCAL"></c:set>
		<c:set var="deptOrgNameShort" value="Label.SHORTORGNAME"></c:set>
		<c:set var="deptOrgOperationalAt" value="Label.ORGOPERATIONALAT"></c:set>
	</c:if>
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle">
				<spring:message htmlEscape="true" code="${formTitle}" />
			</h3>
			<ul id="showhelp" class="listing">
				<%--//these links are not working <li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a></li>

				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>

			</ul>



		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form id="adminOrgDeptForm" commandName="adminOrgDeptForm">
				<form:hidden path="formAccessLevel" value="${pageAccessLevel}" htmlEscape="true" />
				<form:hidden path="unitLevelCode" value="${unitLevelCode}" />
				<form:hidden path="parentUnitLevelCode" />
				<form:input type="hidden" path="sameLevel" id="sameLevel" />
				<form:input type="hidden" path="newLevel" id="newLevel" />
				<form:input type="hidden" path="orgLocatedLevelCode" id="orgLocatedLevelCode" />
				<form:input type="hidden" path="searchOrgLocatedLevelCode" id="searchOrgLocatedLevelCode" />
				<form:input type="hidden" path="topNode" id="topNode" />
				<input type="hidden" name="unitCodesName" value="<c:out value='${unitCodesName}' escapeXml='true'></c:out>"/>
				<form:hidden path="olc" value="${adminOrgDeptForm.olc}"/>
				<form:hidden path="sortOrder"  />
				<form:hidden path="organizationFlow" />

				<c:if test="${(empty pageAccessLevel and ( isCenterUser or isOrganizationFlow))}">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<c:set var="organizationtitle" value="Label.SELDEPT" />
								<c:if test="${isCenterUser}">
									<c:set var="organizationtitle" value="Label.MINISTRIES" />
								</c:if>
								<spring:message code="${organizationtitle}" htmlEscape="true" /> 
							</div>
							<div>
								<ul class="blocklist">
									<c:if test="${isCenterUser}">

										<li><spring:message htmlEscape="true" code="Label.MINISTRIES" /> <span class="errormsg">*</span><br /> <form:select path="ministryId" id="ministryId" class="combofield" onchange="getDepartmentList1(this.value)">
												<form:option value="">
													<spring:message code="Label.SELECT" htmlEscape="true" />
												</form:option>
												<form:options items="${ministryList}" itemLabel="orgName" itemValue="orgCode" />
											</form:select> <br /> <label id="err_ministry" class="errormsg"></label> <form:errors path="ministryId" htmlEscape="true" cssClass="errormsg" /> <br /></li>
									</c:if>

									<c:if test="${isOrganizationFlow}">
										<li><spring:message code="Label.DEPTLIST" htmlEscape="true"></spring:message> <span class="errormsg">*</span> <br /> <form:select path="deptOrgCode" id="deptOrgCode" cssClass="combofield ">
												<form:option value="">
													<spring:message code="Label.SELECT" htmlEscape="true" />
												</form:option>
												<form:options items="${listDepartments}" itemLabel="orgName" itemValue="orgCode" />
											</form:select> <br /> <label id="err_dept" class="errormsg" /> <form:errors htmlEscape="true" path="deptOrgCode" cssClass="errormsg"></form:errors> <br /></li>
										<li><spring:message code="Label.ORGTYPELIST" htmlEscape="true"></spring:message> <span class="errormsg">*</span> <br /> <form:select path="orgType" id="orgType" cssClass="combofield">
												<form:option value="">
													<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
												</form:option>
												<form:options items="${listOrgTypes}" itemLabel="orgType" itemValue="orgTypeCode" />
											</form:select> <br /> <label id="err_org" class="errormsg"></label> <form:errors htmlEscape="true" path="orgType" cssClass="errormsg"></form:errors> <br /></li>
									</c:if>

								</ul>

							</div>


						</div>
					</div>
				</c:if>


				<c:if test="${not empty pageAccessLevel}">
					<!-- Entity Selection Details Tags started -->
					<c:set var="lblChooseLevelAll"></c:set>
					<c:set var="lblChooseLevelSpecific"></c:set>
					<c:set var="titleLavel"></c:set>
					<c:set var="titleLavel"></c:set>
					<c:set var="titleGeneral"></c:set>
					<c:choose>
						<c:when test="${fn:contains(pageAccessLevel, 'X')}">
									<c:set var="lblChooseLevelAll" value="Label.DISTTLB"></c:set>
									<c:if test="${!isCenterUser}">
										<c:set var="lblChooseLevelAll" value="Label.DISTTLB"></c:set>
									</c:if>
									<c:set var="lblChooseLevelSpecific" value="Label.SPECIFICDISTRICTLB"></c:set>
									<c:set var="titleLavel" value="Label.DISTRICTLBNAME"></c:set>	
									<c:set var="titleGeneral" value="Label.DISTLBDEPT"></c:set>
									<c:if test="${isOrganizationFlow}">
									<c:set var="titleGeneral" value="Label.DISTLBORG"></c:set>		
									</c:if>
								</c:when>
								<c:when test="${fn:contains(pageAccessLevel, 'Y')}">
									<c:set var="lblChooseLevelAll" value="Label.ALLINTERMIDIATELB"></c:set>
									<c:set var="lblChooseLevelSpecific" value="Label.SPECIFICINTERMIDIATELB"></c:set>
									<c:set var="titleLavel" value="Label.INTERMIDIATELB"></c:set>
									<c:set var="titleGeneral" value="Label.INTERMIDIATELBDEPT"></c:set>
									<c:if test="${isOrganizationFlow}">
									<c:set var="titleGeneral" value="Label.INTERMIDIATELBORG"></c:set>		
									</c:if>
								</c:when>
								<c:when test="${fn:contains(pageAccessLevel, 'Z')}">
										<c:set var="lblChooseLevelAll" value="Label.ALLVILLAGELBSTATE"></c:set>
									<c:set var="lblChooseLevelSpecific" value="Label.SPECIFICVILLAGELB"></c:set>
									<c:set var="titleLavel" value="Label.VILLAGELB"></c:set>
									<c:set var="titleGeneral" value="Label.VILLAGELBDETP"></c:set>
									<c:if test="${isOrganizationFlow}">
									<c:set var="titleGeneral" value="Label.VILLAGELBORG"></c:set>		
									</c:if>
								</c:when>
						<c:when test="${fn:contains(pageAccessLevel, 'S')}">
							<c:set var="lblChooseLevelAll" value="Label.ALLSTATE"></c:set>
							<c:set var="lblChooseLevelSpecific" value="Label.SpecificStatesIndia"></c:set>
							<c:set var="titleLavel" value="Label.STATELEVEL"></c:set>
							<c:set var="titleGeneral" value="Label.STATEDEPT"></c:set>
							<c:if test="${isOrganizationFlow}">
								<c:set var="titleGeneral" value="Label.STATEORG"></c:set>
							</c:if>
						</c:when>
						<c:when test="${fn:contains(pageAccessLevel, 'D')}">
							<c:set var="lblChooseLevelAll" value="Label.ALLDISTRICT"></c:set>
							<c:if test="${!isCenterUser}">
								<c:set var="lblChooseLevelAll" value="Label.ALLDISTRICTSTATE"></c:set>
							</c:if>
							<c:set var="lblChooseLevelSpecific" value="Label.SPECIFICDISTRICT"></c:set>
							<c:set var="titleLavel" value="Label.DISTRICTTRADNME"></c:set>
							<c:set var="titleGeneral" value="Label.DISTDEPT"></c:set>
							<c:if test="${isOrganizationFlow}">
								<c:set var="titleGeneral" value="Label.DISTORG"></c:set>
							</c:if>
						</c:when>
						<c:when test="${fn:contains(pageAccessLevel, 'T')}">
							<c:set var="lblChooseLevelAll" value="Label.ALLSUBDISTRICTSTATE"></c:set>
							<c:set var="lblChooseLevelSpecific" value="Label.SPECIFICSUBDISTRICT"></c:set>
							<c:set var="titleLavel" value="Label.SUBDISTRICTLVL"></c:set>
							<c:set var="titleGeneral" value="Label.SUBDISTDEPT"></c:set>
							<c:if test="${isOrganizationFlow}">
								<c:set var="titleGeneral" value="Label.SUBDISTORG"></c:set>
							</c:if>
						</c:when>
						<c:when test="${fn:contains(pageAccessLevel, 'B')}">
							<c:set var="lblChooseLevelAll" value="Label.ALLBLOCK"></c:set>
							<c:set var="lblChooseLevelSpecific" value="Label.SPECIFICBLOCK"></c:set>
							<c:set var="titleLavel" value="Label.BLOCKLEVEL"></c:set>
							<c:set var="titleGeneral" value="Label.BLOCKDEPT"></c:set>
							<c:if test="${isOrganizationFlow}">
								<c:set var="titleGeneral" value="Label.BLOCKORG"></c:set>
							</c:if>
						</c:when>
						<c:when test="${fn:contains(pageAccessLevel, 'V')}">
							<c:set var="lblChooseLevelAll" value="Label.ALLVILLAGESTATE"></c:set>
							<c:set var="lblChooseLevelSpecific" value="Label.SPECIFICVILLAGE"></c:set>
							<c:set var="titleLavel" value="Label.VILLAGELVL"></c:set>
							<c:set var="titleGeneral" value="Label.VILLAGEDETP"></c:set>
							<c:if test="${isOrganizationFlow}">
								<c:set var="titleGeneral" value="Label.VILLAGEORG"></c:set>
							</c:if>
						</c:when>
						<c:when test="${fn:contains(pageAccessLevel, 'A')}">
							<c:set var="lblChooseLevelAll" value="Label.ALLADMINUNIT"></c:set>
							<c:set var="lblChooseLevelSpecific" value="Label.SPECADMINUNIT"></c:set>
							<c:set var="titleLavel" value="Label.ADMINUNITLEVEL"></c:set>
							<c:set var="titleGeneral" value="Label.ADMINDEPT"></c:set>
							<c:if test="${isOrganizationFlow}">
								<c:set var="titleGeneral" value="Label.ADMINORG"></c:set>
							</c:if>
						</c:when>
					</c:choose>
				</c:if>


				<!-- General Details started -->
				<div class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message code="Label.GENERALDETAILS" htmlEscape="true" />
							<c:out value="-" />
							<c:if test="${not empty pageAccessLevel and not fn:contains(pageAccessLevel, 'A')}">
							<spring:message htmlEscape="true" code="${titleLavel}" />
							</c:if>
							<%-- <c:if test="${}"> --%>
							<c:if test="${fn:contains(pageAccessLevel, 'A') and (not empty unitCodesName)}">
								<c:out value="${unitCodesName}" escapeXml="true"/>
								<spring:message code="Label.LEVEL" htmlEscape="true" />
								<%-- <c:forEach var="varCodesNames" items="${unitCodesNames}">
											<c:if test="${unitLevelCode eq varCodesNames[0]}">	
												( <c:out value="${varCodesNames[1]}"/> )
											</c:if>
										</c:forEach> --%>
							</c:if>

						</div>
					<div class="tree_view">
						<div class="block">
							<ul class="blocklist">
								<c:if test="${not empty pageAccessLevel}">

									<li><spring:message htmlEscape="true" code="Label.SELPARENT" /> <span class="errormsg">*</span><br />
								
											 <form:select path="parentDepartmentName" id="parent" class="combofield" style="width: 200px">
												<c:forEach var="existDeptList" items="${existDeptList}">
													<option value="${existDeptList.orgLevelSpecificName}"><c:out value="${existDeptList.orgLevelSpecificName}" escapeXml="true"></c:out></option>
												</c:forEach>
												<c:forEach var="newparentList" items="${newparentList}">
													<option value="${newparentList.departmentNameEnglish}"><c:out value="${newparentList.departmentNameEnglish}" escapeXml="true"></c:out></option>
												</c:forEach>
												<c:forEach var="firstDeptList" items="${firstDeptList}">
													<option value="${firstDeptList.orgLevelSpecificName}"><c:out value="${firstDeptList.orgLevelSpecificName}" escapeXml="true"></c:out></option>
												</c:forEach>
											</form:select>
									
									 
									
										 <br />	
										<form:errors path="" htmlEscape="true" cssClass="errormsg" /> <br /></li>
								</c:if>
								<li><spring:message code="${deptOrgNameEng}" htmlEscape="true" /> <span class="errormsg">*</span><br /> 
										
											<form:input path="departmentNameEnglish" id="deptNameEnglish" style="width: 200px" htmlEscape="true" maxlength="200" />
										
										
									 <br /> <label id="err_deptName" class="errormsg"></label > <form:errors htmlEscape="true" path="departmentNameEnglish" cssClass="errormsg" /> <br />
								</li>
								<li><spring:message code="${deptOrgNameLocal}"  htmlEscape="true" /> <br /> 
											<form:input path="departmentNameLocal" id="deptNameLocal" style="width: 200px" class="frmfield" htmlEscape="true" maxlength="200" />
										 <br /> <form:errors htmlEscape="true" path="" cssClass="errormsg" /> <br />
								</li>
								<li><spring:message code="${deptOrgNameShort}" htmlEscape="true" /> <br /> 
											<form:input path="departmentShortName" id="sortNameDept" style="width: 200px" class="frmfield" htmlEscape="true" maxlength="10" />
										
											
								</li>


							</ul>
							
						</div>
						<div class="block"> 
						<c:if test="${not empty newOfficeList || not empty existOfficeList }">
						<spring:message code="Label.ExistOffices" htmlEscape="true" />
						<c:choose>
						<c:when test="${fn:contains(pageAccessLevel, 'A') and (not empty unitCodesName)}">
						 <c:out value="${unitCodesName}" escapeXml="true"/>
								<spring:message code="Label.LEVEL" htmlEscape="true" />
						</c:when>
						<c:otherwise>
						<spring:message htmlEscape="true" code="${titleLavel}" />
						</c:otherwise>
						
						</c:choose>
						<c:out value="-" />
						<ol>
						<c:forEach var="existOfficeList" varStatus="rowVal" items="${existOfficeList}">
						<li><c:out value="${existOfficeList.adminLevelNameEng}" escapeXml="true"></c:out> </li>
						</c:forEach>
						<c:forEach var="newOfficeList" varStatus="rowVal" items="${newOfficeList}">
						<li><c:out value="${newOfficeList.departmentNameEnglish}" escapeXml="true"></c:out> </li>
						</c:forEach>
						</ol>
						</c:if>
						</div>
						<div class="clear"></div>
					</div>
					</div>
				</div>
				<!-- General Details block ended here -->


				<!-- Display Specific Location Details Here -->
				<%-- <c:if test="${isHierarchyAvailable and empty pageAccessLevel and adminOrgDeptForm.newLevel } ">
				   			<div class="frmpnlbg">
					   			<div class="frmtxt">
									<div class="frmhdtitle">
										<spring:message htmlEscape="true"  code="${deptOrgOperationalAt}"/>
									</div>
									
									<ul class="blocklist">
										<li>
										<c:forEach var="keys" items="${keySetUnitCodes}">
													<c:forEach var="varCodesNames" items="${unitCodesNames}">
														<c:if test="${keys eq varCodesNames[0]}">	
															<input type="checkbox" checked="checked" disabled="disabled"/>&nbsp;
															<c:out value="${varCodesNames[1]}"/>&nbsp;
														</c:if>
													</c:forEach>
										</c:forEach>
										</li>
									
									</ul>
									<div class="clear"></div>
								
												
										
					   			</div>
					   		</div>		
				   		</c:if> --%>
				<!-- Display Specific Location Details Here -->

				<c:if test="${not empty pageAccessLevel and adminOrgDeptForm.newLevel}">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true" code="${titleLavel}" />
							</div>

							<div>
								<ul class="blocklist" id="tblChooseAllSpecific">
									<li><form:radiobutton path="chooseLevelAllOrSpecific" id="rdAllStateDis" htmlEscape="true" value="F" onclick="showAllOrSpecificDetails(this.value);" /> <spring:message htmlEscape="true" code="${lblChooseLevelAll}"  />&nbsp;&nbsp;&nbsp;&nbsp;
										<form:radiobutton path="chooseLevelAllOrSpecific" value="S" id="rdSpecificStateDis" checked="true" onclick="showAllOrSpecificDetails(this.value);" /> <spring:message htmlEscape="true" code="${lblChooseLevelSpecific}" /></li>
									<li><c:set var="domainNameErrors">
											<form:errors path="setErrorMsg" />
										</c:set> <c:if test="${not empty domainNameErrors}">
											<div style="height: 40px; border: 1px solid red; background-color: #FFEBE8; padding-top: 10px;" align="center">
												<label id="errorCommon"><c:out value="${domainNameErrors}" escapeXml="true"></c:out></<label>
											</div>
										</c:if></li>
									<li><br />
									<br /></li>
									<li></li>
								</ul>
							</div>
							<div class="clear"></div>


							<ul class="blocklist" id="tblAllSpecificDetails">
								<li>
									<ul class="blocklist">
										<li>
											<!-- State Level  Tags Started --> <c:if test="${isCenterUser == true}">
												<c:if
													test="${ fn:contains(pageAccessLevel, 'S')
																						    or fn:contains(pageAccessLevel, 'D') 
																					  	    or fn:contains(pageAccessLevel, 'T')
																					  		or fn:contains(pageAccessLevel, 'V')}">

													<c:if test="${fn:contains(pageAccessLevel, 'D')																	
																				  	    or fn:contains(pageAccessLevel, 'T')
																				  		or fn:contains(pageAccessLevel, 'V')}">
														<ul class="blocklist" id=" tblForFullAndParialCoverage">
															<li id="columnFullCover" class="margintoleft"><form:checkbox name="checkbox" id="ChkFullCoverage" value="Full" path="ChkFullCoveragestate" htmlEscape="true" onclick="StateFullPart();" /> <spring:message htmlEscape="true"
																	code="Label.STATEFULLCOVERAGE" /><br /> <label id="err_statechk" class="errormsg"></label></li>


														</ul>
														<div class="clear"></div>
													</c:if>

													<ul class="blocklist" id="tblStateSpecific">

														<li></li>
														<li>
															<div class="ms_container">
																<div class="ms_selectable">
																	<spring:message htmlEscape="true" code="Label.STATELIST" />
																	<br />
																	<form:select path="" id="ddSourceState" multiple="true" htmlEscape="true" class="frmtxtarea">
																		<c:forEach var="state" items="${statelist}">
																			<form:option value="${state.statePK.stateCode}"><c:out value="${state.stateNameEnglish }" escapeXml="true"></c:out></form:option>
																		</c:forEach>
																	</form:select>
																</div>
																<div class="ms_buttons">
																	<input type="button" id="mvFrState" class="bttn" value=" &gt;&gt;" /> <input type="button" id="mvBackState" value=" &lt;&lt;" class="bttn" />

																</div>
																<div class="ms_selection">

																	<spring:message htmlEscape="true" code="Label.STATELISTSELECTED" />
																	<br />
																	<form:select path="stateIds" id="ddTargetState" multiple="true" class="frmtxtarea" />
																	<br /> <label id="err_state" class="errormsg"></label>
																</div>
															</div>

														</li>

													</ul>

													<div class="clear"></div>
													<ul class="blocklist" id="tblPartialCoverage">
														<li id="columnPartialCover" class="margintoleft"><form:checkbox name="checkbox" id="ChkPartialCoverage" value="Part" path="ChkPartialCoverage" onclick="StatePartialPart();" /> <spring:message htmlEscape="true"
																code="Label.STATEPARTCOVERAGE" /></li>

													</ul>
													<div class="clear"></div>
												</c:if>
											</c:if> <!-- State Level  Tags Ends Here -->


										</li>
											<!-- Local Body Code  Tags Started -->	
												<c:if test="${ fn:contains(pageAccessLevel, 'X')
													               or fn:contains(pageAccessLevel, 'Y')
													            or fn:contains(pageAccessLevel, 'Z')}">
														<li>
																
																<ul class="blocklist" id="tblAdminUnits">
																		<li></li>	
																		<li>
																					<div class="ms_container">
																								<div class="ms_selectable">
																											Local Body Entity List
																											<br />
																											<form:select path=""  id="ddLocalBodyEnitiy" multiple="true"  class="frmtxtarea" htmlEscape="true">
																											<form:options items="${localBodyEntities}" itemValue="localBodyCode" itemLabel="localBodyNameEnglish" htmlEscape="true"/>
																											<%-- <c:forEach items="${localBodyEntities}" var ="obj">
																										  	<option value="${obj[0] }">${obj[1] }</option>
																											</c:forEach>	 --%>
																											</form:select>	
																											
																								</div>
																								<div class="ms_buttons">
																											<input id="mvFrLocalBodyEntity" type="button" class="bttn" value="&gt;&gt;" />
																											<input type="button" id="mvBackLocalBodyEntity" value=" &lt;&lt;" class="bttn"  />
																								</div>
																								<div class="ms_selection">
																													Selected Local Body Enities
																											<br /> 
																											<form:select path="adminUnitEntityIds" id="ddTargetLocalBodyEnitiy" multiple="true"  class="frmtxtarea" htmlEscape="true"/>
																											</br>
																											<label id="err_LocalBodyblk" class="errormsg"></label>	
																								</div>																		
																					</div>
							
																		</li>	
																																
																</ul>
																
																<div class="clear"></div>
																
															</li>
														<c:if test="${adminUnitCondition}">
															<li>
																<ul class="blocklist" id="tblAdminUnitsConditional">
																		<li></li>
																		<li>
																						<div class="ms_container">
																									<div class="ms_selectable">
																													Administrative Entity List - <br/>Conditional
																													<br />
																													<form:select path="" id="ddAdminEnitiyConditional" multiple="true"  class="frmtxtarea" htmlEscape="true">
																														<form:options items="${adminEntities}" itemValue="adminUnitEntityCode" itemLabel="adminEntityNameEnglish" htmlEscape="true"/>
																													</form:select>		
																									</div>
																									<div class="ms_buttons">
																													<input type="button" class="bttn" value="&gt;&gt;" />
																													<input type="button" value=" &lt;&lt;" class="bttn" />
																									</div>
																									<div class="ms_selection">
																													Selected Administrative Enities - <br/>Conditional
																													<br /> 
																													<form:select path="adminUnitEntityIds"  id="ddTargetAdminEnitiyConditional" multiple="true"  class="frmtxtarea" htmlEscape="true"/>
																													
																									</div>																		
																						</div>
																		
																		</li>
																		<li></li>
																
																</ul>
																
																<div class="clear"></div>
																	
																</li>
														</c:if>
													</c:if>
														<!-- Local Body Code  Tags End -->	
										<li>
											<!-- District Level  Tags Started --> <c:if test="${isCenterUser == true}">
												<c:if test="${ fn:contains(pageAccessLevel, 'D')}">

													<ul class="blocklist" id="tblStateList">
														<li class="margintoleft"><spring:message htmlEscape="true" code="Label.SELECTSTATE" /><br /> <form:select path="" class="combofield" id="stateName" onchange="getDistrictsforVillageS();">
															</form:select></li>
													</ul>
													<div class="clear"></div>
												</c:if>
											</c:if> <c:if test="${ fn:contains(pageAccessLevel, 'D') 
																				  		or fn:contains(pageAccessLevel, 'T')
																				  		or fn:contains(pageAccessLevel, 'B')
																				  		or fn:contains(pageAccessLevel, 'V')}">
												<!-- Drop-down District with full Coverage used in Sub-district-->
												<c:if test="${ fn:contains(pageAccessLevel, 'T') 
																							or fn:contains(pageAccessLevel, 'B')
																							or fn:contains(pageAccessLevel, 'V')}">

													<ul class="blocklist" id="tblCheckCoverageDistrictFull">
														<li class="margintoleft"><form:checkbox path="districtChkfull" id="districtChkFull" name="checkbox" value="DFull" htmlEscape="true" onclick="toggle2();" /> <spring:message htmlEscape="true" code="Label.DISTRICTFULLCOVERAGE" /></br> <label
															id="err_districtchk" class="errormsg"></label> </br></li>
													</ul>
													<div class="clear"></div>
												</c:if>
												<!-- Drop-down District with full Coverage used in Sub-district  Ended -->

												<c:if test="${isCenterUser == true}">
													<c:if test="${ fn:contains(pageAccessLevel, 'T')
																					              or fn:contains(pageAccessLevel,'V')}">

														<ul class="blocklist" id="tblStateList1">
															<li class="margintoleft"><spring:message htmlEscape="true" code="Label.SELECTSTATE" /><br /> <form:select path="" class="combofield" id="stateName1" onchange="getDistrictsforVillageS();">
																</form:select><br></br></li>
															<li></li>
															<li></li>

														</ul>
														<div class="clear"></div>
													</c:if>
												</c:if>

												<ul id="tblDistrictSpecific" class="blocklist">
													<li>
														<div class="ms_container">
															<div class="ms_selectable">
																<spring:message htmlEscape="true" code="Label.DISTRICTLIST" />
																<br />
																<form:select path="" id="dddistrictAtStateLvl" multiple="true" class="frmtxtarea">


																	<c:forEach items="${distList}" var="distListvar">
																		<c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
																			<form:option value="${distListvar.districtCode}" disabled="true"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
																		</c:if>
																		<c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
																			<form:option value="${distListvar.districtCode}"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
																		</c:if>
																	</c:forEach>
																	<%-- <form:options items="${distList}" itemValue="districtCode" itemLabel="districtNameEnglish"/> --%>
																</form:select>
															</div>
															<div class="ms_buttons">
																<input type="button" id="mvFrDistt" class="bttn" value="&gt;&gt;" /> <input type="button" id="mvBackDistt" value=" &lt;&lt;" class="bttn" />
															</div>
															<div class="ms_selection">
																<spring:message htmlEscape="true" code="Label.DISTRICTLISTSELECTED" />
																<br />
																<form:select path="districtIds" id="ddTargetDistrict" multiple="true" class="frmtxtarea" />
																<br /> <label id="err_district" class="errormsg"></label>
															</div>
														</div>

													</li>

												</ul>

												<div class="clear"></div>
												<br />
												<br />
											</c:if> <!-- District Level Tags Ends Here -->


										</li>
										<c:if test="${ fn:contains(pageAccessLevel, 'T') or fn:contains(pageAccessLevel, 'B')}">
											<li>

												<ul class="blocklist" id="tblDistrictChkPart">
													<li class="margintoleft"><form:checkbox path="DistrictChkpart" id="DistrictChkPart" name="checkbox" value="DPart" htmlEscape="true" onclick="toggle3();" /> <spring:message htmlEscape="true" code="Label.DISTRICTPARTCOVERAGE" /></li>

												</ul>
												<div class="clear"></div> <br />
											</li>

										</c:if>

										<c:if test="${ fn:contains(pageAccessLevel, 'V')}">
											<li>
												<ul class="blocklist" id="tblSubDistrictChkFull">
													<li class="margintoleft"><form:checkbox path="SubDistrictChkfull" id="SubDistrictChkFull" name="checkbox" value="SDFull" onclick="checkSubDistFullCovrage()" /> <spring:message htmlEscape="true" code="Label.SUBDISTRICTFULLCOVERAGE" /></br>
													</br></li>
												</ul>

												<div class="clear"></div> <br />
											</li>
										</c:if>

										<c:if test="${isCenterUser == true}">
											<c:if test="${ fn:contains(pageAccessLevel, 'T') 
																			or fn:contains(pageAccessLevel, 'B')
																			or fn:contains(pageAccessLevel, 'V')}">
												<li>
													<ul id="tblDistrictSelectBox1">
														<li class="margintoleft"><spring:message htmlEscape="true" code="Label.SELECTSTATE" /><br /> <form:select path="" class="combofield" id="stateNameDistforSD" style="width:200px" onchange="getNotInDistrictList(this.value);">
																<form:option value="">
																	<spring:message htmlEscape="true" code="Label.SELECT" />
																</form:option>
															</form:select></li>
													</ul>
													<div class="clear"></div>

												</li>

											</c:if>
										</c:if>


										<c:if test="${ fn:contains(pageAccessLevel, 'T') 
																			or fn:contains(pageAccessLevel, 'B')
																			or fn:contains(pageAccessLevel, 'V')}">
											<li>
												<ul class="blocklist" id="tblDistrictSelectBox">
													<li class="margintoleft"><spring:message htmlEscape="true" code="Label.SELECTDISTRICT" /><br /> <select id="district" name="districtName1" class="combofield" style="width: 200px"
														onchange="getSubdistrictOrBlocksByDistrict(this.value);">
															<option value="">Select</option>
													</select></li>

												</ul>
												<div class="clear"></div>
											</li>

										</c:if>

										<li>
											<!-- Sub-district Level  Tags Started --> <c:if test="${fn:contains(pageAccessLevel, 'T') or fn:contains(pageAccessLevel, 'V')}">

												<ul class="blocklist" id="tblSubdistrictSpecific">
													<li>
														<div class="ms_container">
															<div class="ms_selectable">
																<spring:message htmlEscape="true" code="Label.SUBDISTRICTLIST" />
																<br />
																<form:select path="" id="ddSubdistrictAtSubDistrictLvl" multiple="true" class="frmtxtarea">
																</form:select>
															</div>
															<div class="ms_buttons">
																<input type="button" id="mvFrSubDist" class="bttn" value=" &gt;&gt;" /> <input type="button" id="mvBackSubDist" value=" &lt;&lt;" class="bttn" />
															</div>
															<div class="ms_selection">
																<spring:message htmlEscape="true" code="Label.SELECTEDSUBDISTRICT" />
																<br />
																<form:select path="subdistrictIds" id="ddTargetDistrictSDLvl" multiple="true" class="frmtxtarea" />
																<br /> <label id="err_subDistrict" class="errormsg"></label>
															</div>
														</div>


													</li>

												</ul>

												<div class="clear"></div>

											</c:if> <!-- Sub-district Level Tags Ends Here -->

										</li>
										<li>
											<!-- Block Level  Tags Started --> <c:if test="${fn:contains(pageAccessLevel, 'B')}">
												<ul class="blocklist" id="tblBlockSpecific">

													<li>
														<div class="ms_container">
															<div class="ms_selectable">

																<spring:message htmlEscape="true" code="Label.BLOCKLIST" />
																<br />
																<form:select path="" id="ddBlockAtDistrictLvl" multiple="true" class="frmtxtarea">
																</form:select>
															</div>
															<div class="ms_buttons">
																<input type="button" id="mvFrBlock" class="bttn" value=" &gt;&gt;" /> <input type="button" id="mvBackBlock" value=" &lt;&lt;" class="bttn" />
															</div>
															<div class="ms_selection">

																<spring:message htmlEscape="true" code="Label.BLOCKLISTSELECTED" />
																<br />
																<form:select path="blockIds" id="ddTargetBlockSDLvl" multiple="true" class="frmtxtarea" />
																<br /> <label id="err_block" class="errormsg"></label>
															</div>
														</div>



													</li>

												</ul>

												<div class="clear"></div>
											</c:if> <!-- Block Level Tags Ends Here -->

										</li>
										<!-- Village Level Tags Started Here -->
										<c:if test="${ fn:contains(pageAccessLevel, 'V')}">

											<li>
												<ul class="blocklist" id="tblSubDistrictChkPart">
													<li></li>
													<li class="margintoleft"><br />
													</br/> <form:checkbox path="SubDistrictChkpart" id="SubDistrictChkPart" name="checkbox" value="SDPart" onclick="checkSubDistPartialCovrage()" /> <spring:message htmlEscape="true" code="Label.SUBDISTRICTPARTCOVERAGE" /></br>
													</br></li>
												</ul>
												<div class="clear"></div> <br />

											</li>

											<li>
												<ul class="blocklist" id="tblSubDistrictSelectState">
													<li class="margintoleft"><spring:message htmlEscape="true" code="Label.SELECTSTATE" /><br /> <form:select path="" style="width:200px" class="combofield" id="stateNameSubDistForVillage"
															onchange="getNotInDistrictListVillLvl(this.value);">
															<form:option value="">
																<spring:message htmlEscape="true" code="Label.SELECT" />
															</form:option>
														</form:select></li>
												</ul>
												<div class="clear"></div> <br />

											</li>

											<li>
												<ul class="blocklist" id="tblDisttSubDisttVillLvlSelectBox">

													<li class="margintoleft"><spring:message htmlEscape="true" code="Label.SELECTDISTRICT" /><br /> <form:select path="" id="dddistrictAtVillLvl" class="combofield" style="width:200px" onchange="getSubdistrictAtVilllvl(this.value)">
														</form:select></li>
													<li class="margintoleft"><spring:message htmlEscape="true" code="Label.SELECTSUBDISTRICT" /><br /> <form:select path="" id="ddSubdistrictAtVillLvl" class="combofield" style="width:200px"
															onchange="getVillageListAtVillageLvl(this.value)">
														</form:select></li>
													<li></li>

												</ul>
												<div class="clear"></div> <br />

											</li>


											<li>
												<ul class="blocklist" id="tblVillageSpecific">
													<li></li>
													<li>
														<div class="ms_container">
															<div class="ms_selectable">

																<spring:message htmlEscape="true" code="Label.VILLAGELIST" />
																<br />
																<form:select path="" id="ddVillageAtVillLvl" multiple="true" class="frmtxtarea">
																</form:select>
															</div>
															<div class="ms_buttons">
																<input type="button" class="bttn" id="mvFrVillage" value=" &gt;&gt;" /> <input type="button" id="mvBackVillage" value=" &lt;&lt;" class="bttn" />
															</div>
															<div class="ms_selection">
																<spring:message htmlEscape="true" code="Label.VILLAGELISTSELECTED" />
																<br />
																<form:select path="villageIds" id="ddTargetDistrictVillLvl" multiple="true" class="frmtxtarea" />
																<br /> <label id="err_village" class="errormsg"></label>
															</div>
														</div>


													</li>


												</ul>

												<div class="clear"></div>


											</li>


										</c:if>
										<!-- Village Level Tags Ends Here -->


										<!-- Administrative Unit Level Tags Started -->
										<c:if test="${ fn:contains(pageAccessLevel, 'A')}">
											<li>

												<ul class="blocklist" id="tblAdminUnits">
													<li></li>
													<li>
														<div class="ms_container">
															<div class="ms_selectable">
																Administrative Entity List <br />
																<form:select path="" id="ddAdminEnitiy" multiple="true" class="frmtxtarea">
																	<form:options items="${adminEntities}" itemValue="adminUnitEntityCode" itemLabel="adminEntityNameEnglish" />
																</form:select>
															</div>
															<div class="ms_buttons">
																<input id="mvFrAdminEntity" type="button" class="bttn" value="&gt;&gt;" /> <input type="button" id="mvBackAdminEntity" value=" &lt;&lt;" class="bttn" />
															</div>
															<div class="ms_selection">
																Selected Administrative Enities <br />
																<form:select path="adminUnitEntityIds" id="ddTargetAdminEnitiy" multiple="true" class="frmtxtarea" />
																</br> <label id="err_Administrativeblk" class="errormsg"></label>
															</div>
														</div>

													</li>

												</ul>

												<div class="clear"></div>

											</li>
											<c:if test="${adminUnitCondition}">
												<li>
													<ul class="blocklist" id="tblAdminUnitsConditional">
														<li></li>
														<li>
															<div class="ms_container">
																<div class="ms_selectable">
																	Administrative Entity List - <br />Conditional <br />
																	<form:select path="" id="ddAdminEnitiyConditional" multiple="true" class="frmtxtarea">
																		<form:options items="${adminEntities}" itemValue="adminUnitEntityCode" itemLabel="adminEntityNameEnglish" />
																	</form:select>
																</div>
																<div class="ms_buttons">
																	<input type="button" class="bttn" value="&gt;&gt;" /> <input type="button" value=" &lt;&lt;" class="bttn" />
																</div>
																<div class="ms_selection">
																	Selected Administrative Enities - <br />Conditional <br />
																	<form:select path="adminUnitEntityIds" id="ddTargetAdminEnitiyConditional" multiple="true" class="frmtxtarea" />

																</div>
															</div>

														</li>
														<li></li>

													</ul>

													<div class="clear"></div>

												</li>
											</c:if>
										</c:if>
										<!-- Administrative Unit Level Tags Ends Here -->

									</ul>
									<div class="clear"></div>




								</li>

							</ul>


						</div>
					</div>
				</c:if>
				<!-- Entity Selection Details Tags Ends Here -->
				<c:if test="${!empty existDeptList or !empty newparentList }">
					<script>
						document.getElementById('parent').value="<c:out value='${adminOrgDeptForm.parentDepartmentName}' />";
						</script>
				</c:if>
				<table width="100%">
					<tr>
						<td align="center"><c:choose>
								<c:when test="${not isHierarchyAvailable or enableSaveBtn}">
									<input type="button" id="btnSaveDept" value="<spring:message htmlEscape="true"  code="Button.SAVE"/>" onclick="javascript:reProcessForm('SAVE');" />
								</c:when>
								<c:otherwise>
									<input type="button" id="btnCreateDeptNext" value="<spring:message htmlEscape="true"  code="Button.NEXT"/>" onclick="javascript:reProcessForm('NEXT');" />

								</c:otherwise>
							</c:choose> <input type="button" id="addAnotherDeptSameLevel" value="<spring:message htmlEscape="true"  code="Button.AddDeptatSameLevel"/>" onclick="javascript:reProcessForm('AddDeptatSameLevel');" /> <input type="button" id="close"
							value="<spring:message htmlEscape="true"  code="Button.CLOSE"/>" onclick="javascript:go('closeDepartmentProcess.htm');" /></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
	<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>