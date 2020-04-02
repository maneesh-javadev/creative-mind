<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="MapCoveredAreaJavascript.jsp"%>
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
<link href="<%=contextpthval%>/resources-localbody/css/jquery.dataTables.css" rel="stylesheet" type="text/css">	
<script src="<%=contextpthval%>/resources-localbody/scripts/jquery.dataTables.js" type="text/javascript" language="javascript" ></script>
<c:set var="DISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.DISTRICT_CONSTANT.toString()%>"></c:set>
<c:set var="SUBDISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.SUB_DISTRICT_CONSTANT.toString()%>"></c:set>
<c:set var="VILLAGE_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.VILLAGE_CONSTANT.toString()%>"></c:set>
<c:set var="DISTRICT_PANCHAYAT_LEVEL" value= "<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.DISTRICT_PANCHAYAT_LEVEL.toString()%>"></c:set>
<c:set var="INTERMEDIATE_PANCHAYAT_LEVEL" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.INTERMEDIATE_PANCHAYAT_LEVEL.toString()%>"></c:set>
<c:set var="VILLAGE_PANCHAYAT_LEVEL" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.VILLAGE_PANCHAYAT_LEVEL.toString()%>"></c:set>	
</head>
<body class="dt-example">
	<!-- Main Form Styling starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message code="Label.CORRECTGOVTLOCALBODY.MC" htmlEscape="true"></spring:message></h3>
			<ul class="item_list">
				<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form action="mapCoverageLocalBody.htm" method="post" id="localBodyForm" commandName="localBodyForm">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="mapCoverageLocalBody.htm"/>" />					
					<form:hidden path="localBodyCreationType"/>
					<form:hidden path="localBodyCode" id="paramLBCode"/>
					<form:hidden path="localBodyTypeCode"/>					
					<form:hidden path="headQuarterCode" value="${mainRegionCode}"/>					
					<form:hidden path="localBodyTypePanchayat" value="${localBodyTypeLevel}"/>								
					<form:hidden path="changeCoverageTypeLRList"/>					
					
					
					<!-- Block for Show General Details of Drafted Local Government Body. -->
					<div id="divCenterAligned" class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4><spring:message htmlEscape="true"  code="Label.GENERALLOCALGOVTBODYDETAILS"></spring:message></h4>
								<ul class="form_body" >
									<c:if test="${localBodyForm.isdisturbed}">
										<li>
											<label class="inline">
												<spring:message code="Label.DISTURBEDSTATE" htmlEscape="true" ></spring:message>
										    </label>
										    <label >
										    		<img src="<%=contextpthval%>/images/red_flg.png" width="13" height="9" />
										    </label>
										</li>
									</c:if>
									<li>
										<label class="inline">
											<spring:message htmlEscape="true"  code="Label.LOCALBODYNAMEENGLISH"></spring:message>
									    </label>
									    <label >
									    	<c:out value="${localBodyForm.localBodyNameEnglish}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYNAMELOCAL"></spring:message>
										</label>
										<label >
											<c:out value="${localBodyForm.localBodyNameLocal}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message>
										</label>
										<label>
											<c:out value="${localBodyForm.localBodyAliasEnglish}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message>
										</label>
										<label >
											<c:out value="${localBodyForm.localBodyAliasLocal}" escapeXml="true"></c:out>
									    </label>
									</li>
								</ul>	
							</div>
						</div>
					</div>
					<br/>
					<!-- Block for Show General Details of Drafted Local Government Body Ends. -->	
					
					<c:set var="lblHQHeading"></c:set>	
					<c:set var="lblLocalBodyHeading"></c:set>
					<c:choose>
						<c:when test="${localBodyTypeLevel eq  DISTRICT_PANCHAYAT_LEVEL.charAt(0)}">
							<c:set var="lblHQHeading" value="Label.CONTRIBUTDISTRICTLIST"></c:set>
							<c:set var="lblLocalBodyHeading" value="Label.DISTRICTPANCHAYAT"></c:set>
						</c:when>
						<c:when test="${localBodyTypeLevel eq  INTERMEDIATE_PANCHAYAT_LEVEL.charAt(0)}">
							<c:set var="lblHQHeading" value="Label.CONTRIBUTESUBDISTRICTLIST"></c:set>
							<c:set var="lblLocalBodyHeading" value="Label.INTERMEDIATEPANCHAYAT"></c:set>
						</c:when>
						<c:when test="${localBodyTypeLevel eq  VILLAGE_PANCHAYAT_LEVEL.charAt(0)}">
							<c:set var="lblHQHeading" value="Label.CONTRIBUTVILLAGELIST"></c:set>
							<c:set var="lblLocalBodyHeading" value="Label.VILLAGEPANCHAYAT"></c:set>
						</c:when>
					</c:choose>
					<div class="form_block">
						<div class="col_1">
							<h4><spring:message htmlEscape="true" code="Label.CORRECTLOCALBODY"></spring:message></h4>
								<ul class="form_body">
								<li>
									<label><spring:message code="Label.CURRENTCOVERAREA" htmlEscape="true"></spring:message></label>
								</li>
								</ul>
							<!-- Div Container For Current Coverred Area of Local Body -->
							<div class="col_1">
								<!-- Blank heading being used in GUI -->
								<!-- <h4></h4> -->
								<ul class="form_body">
									<c:if test="${localBodyTypeLevel eq DISTRICT_PANCHAYAT_LEVEL.charAt(0)}">	
										<li>
											<table id="tblCoverage_${DISTRICT_PANCHAYAT_LEVEL}" class="data_grid" width="50%">
												<thead>
													<tr>
														<td <c:if test="${localBodyTypeLevel eq DISTRICT_PANCHAYAT_LEVEL.charAt(0)}">colspan="4"</c:if>  
															<c:if test="${localBodyTypeLevel ne DISTRICT_PANCHAYAT_LEVEL.charAt(0)}">colspan="2"</c:if> >															
															<strong>Current Covered District</strong>
														</td>
													</tr>
													<tr>
														<th rowspan="2" width="20%">District Code</th>
														<th rowspan="2" width="30%">District Name</th>
														<c:if test="${localBodyTypeLevel eq DISTRICT_PANCHAYAT_LEVEL.charAt(0)}">
															<th colspan="2" align="center" width="40%"> Change Coverage type</th>
														</c:if>														
													</tr>
													 <c:if test="${localBodyTypeLevel eq DISTRICT_PANCHAYAT_LEVEL.charAt(0)}">
														<tr>
															<th width="15%"> Part</th>
															<th width="15%"> Full</th>								
														</tr>
													 </c:if>
												</thead>
												<tbody>
													<c:forEach items="${completedCoverageDetails}" var="completedCoveragesDistrict" varStatus="completedCoveragesDistrictStatus" >
														<c:if test="${completedCoveragesDistrict.landRegionType eq  DISTRICT_CONSTANT}">
															<tr id="tr_${completedCoveragesDistrict.landRegionCode}">
																<td><c:out value="${completedCoveragesDistrict.landRegionCode}"></c:out></td>
																<td><c:out value="${completedCoveragesDistrict.landRegionNameEnglish}"></c:out></td>
																<c:if test="${localBodyTypeLevel eq DISTRICT_PANCHAYAT_LEVEL.charAt(0)}">
																	<td><input type="radio" name="contributeddistrict[${completedCoveragesDistrictStatus.count }]" id="contributedDistrictCodes_P" value="P" paramRegionType="${DISTRICT_CONSTANT}" param="${completedCoveragesDistrict.landRegionCode }" 
																			<c:if test="${completedCoveragesDistrict.coverageType=='P'}">checked="checked"</c:if> />
																	</td>	
																	<td><input type="radio" name="contributeddistrict[${completedCoveragesDistrictStatus.count }]" id="contributedDistrictCodes_F" value="F"  paramRegionType="${DISTRICT_CONSTANT}" param="${completedCoveragesDistrict.landRegionCode }" 
																			<c:if test="${completedCoveragesDistrict.coverageType=='F'}">checked="checked"</c:if> />	
																	</td>
																</c:if>
															</tr>
															
														</c:if>
													</c:forEach>
												</tbody>
											</table>
										</li>
									</c:if>
									
									<c:if test="${(localBodyTypeLevel eq  DISTRICT_PANCHAYAT_LEVEL.charAt(0)) or (localBodyTypeLevel eq  INTERMEDIATE_PANCHAYAT_LEVEL.charAt(0))}">	
										<li>
											<table id="tblCoverage_${INTERMEDIATE_PANCHAYAT_LEVEL}" class="data_grid" width="50%">
												<thead>
													<tr>
														<td <c:if test="${localBodyTypeLevel eq INTERMEDIATE_PANCHAYAT_LEVEL.charAt(0)}">colspan="4"</c:if>  
															<c:if test="${localBodyTypeLevel ne INTERMEDIATE_PANCHAYAT_LEVEL.charAt(0)}">colspan="2"</c:if> >
															<strong>Current Covered Sub-district</strong>
														</td>
													</tr>
													<tr>
														<th  rowspan="2" width="20%" >Sub-district Code</th>
														<th rowspan="2" width="30%">Sub-district Name</th>
														<c:if test="${localBodyTypeLevel eq INTERMEDIATE_PANCHAYAT_LEVEL.charAt(0)}">
														<th colspan="2" align="center" width="40%"> Change Coverage type</th>
														</c:if>														
													</tr>
													<c:if test="${localBodyTypeLevel eq INTERMEDIATE_PANCHAYAT_LEVEL.charAt(0)}">
														<tr>
															<th width="15%"> Part</th>
															<th width="15%"> Full</th>								
														</tr>
													</c:if>
												</thead>
												<tbody>
													<c:forEach items="${completedCoverageDetails}" var="completedCoveragesIM"  varStatus="completedCoveragesIMStatus">
														<c:if test="${completedCoveragesIM.landRegionType eq  SUBDISTRICT_CONSTANT}">
														<tr id="tr_${completedCoveragesIM.landRegionCode}">
															<td><c:out value="${completedCoveragesIM.landRegionCode}"></c:out></td>
															<td><c:out value="${completedCoveragesIM.landRegionNameEnglish}"></c:out></td>	
															<c:if test="${localBodyTypeLevel eq INTERMEDIATE_PANCHAYAT_LEVEL.charAt(0)}">														
																<td><input type="radio" name="contributedsubdistrict[${completedCoveragesIMStatus.count }]" id="contributedSubDistrictCodes_P" value="P"  paramRegionType="${SUBDISTRICT_CONSTANT}"  param="${completedCoveragesIM.landRegionCode }" <c:if test="${completedCoveragesIM.coverageType=='P'}">checked="checked"</c:if> /></td>																																				
																<td><input type="radio" name="contributedsubdistrict[${completedCoveragesIMStatus.count }]" id="contributedSubDistrictCodes_F" value="F" paramRegionType="${SUBDISTRICT_CONSTANT}" param="${completedCoveragesIM.landRegionCode }" <c:if test="${completedCoveragesIM.coverageType=='F'}">checked="checked"</c:if>/></td>
															</c:if>
														</tr>
														
														</c:if>
													</c:forEach>
												</tbody>
											</table>
										</li>
									</c:if>
									
									<c:if test="${(localBodyTypeLevel eq  DISTRICT_PANCHAYAT_LEVEL.charAt(0)) or 
												  (localBodyTypeLevel eq  INTERMEDIATE_PANCHAYAT_LEVEL.charAt(0)) or 
												  (localBodyTypeLevel eq  VILLAGE_PANCHAYAT_LEVEL.charAt(0))}">		
										<li>
											<table id="tblCoverage_${VILLAGE_PANCHAYAT_LEVEL}" class="data_grid" width="50%">
												<thead>
													<tr>
														<td <c:if test="${localBodyTypeLevel eq VILLAGE_PANCHAYAT_LEVEL.charAt(0)}">colspan="4"</c:if>  
															<c:if test="${localBodyTypeLevel ne VILLAGE_PANCHAYAT_LEVEL.charAt(0)}">colspan="2"</c:if> >
														<strong>Current Covered Village</strong></td>
													</tr>
													<tr>
														<th rowspan="2" width="20%">Village Code</th>
														<th rowspan="2" width="30%">Village Name</th>
														<c:if test="${localBodyTypeLevel eq VILLAGE_PANCHAYAT_LEVEL.charAt(0)}">	
															<th colspan="2" align="center" width="40%"> Change Coverage type</th>
														</c:if>													
													</tr>
													<c:if test="${localBodyTypeLevel eq VILLAGE_PANCHAYAT_LEVEL.charAt(0)}">
														<tr>
															<th width="15%"> Part</th>
															<th width="15%"> Full</th>								
														</tr>
													</c:if>
												</thead>
												<tbody>
													<c:forEach items="${completedCoverageDetails}" var="completedCoveragesV" varStatus="completedCoveragesVStatus" >													
														<c:if test="${completedCoveragesV.landRegionType eq VILLAGE_CONSTANT}">
														<script>setCoverageMap('${completedCoveragesV.landRegionCode}','${completedCoveragesV.coverageType}')</script>
														<tr id="tr_${completedCoveragesV.landRegionCode}">
															<td><c:out value="${completedCoveragesV.landRegionCode}"></c:out></td>
															<td><c:out value="${completedCoveragesV.landRegionNameEnglish}"></c:out></td>
															<c:if test="${localBodyTypeLevel eq VILLAGE_PANCHAYAT_LEVEL.charAt(0)}">																
																<td><input type="radio" name="contributedvillage[${completedCoveragesVStatus.count }]" id="contributedVillageCodes_P"   value="P" paramRegionType="${VILLAGE_CONSTANT}"  param="${completedCoveragesV.landRegionCode }" <c:if test="${completedCoveragesV.coverageType=='P'}">checked="checked"</c:if> /></td>
																<td><input type="radio" name="contributedvillage[${completedCoveragesVStatus.count }]" id="contributedVillageCodes_F"   value="F" paramRegionType="${VILLAGE_CONSTANT}" param="${completedCoveragesV.landRegionCode }" <c:if test="${completedCoveragesV.coverageType=='F'}">checked="checked"</c:if> /></td>													
															</c:if>
														</tr>	
													
														</c:if>
													</c:forEach>
												</tbody>
											</table>
										</li>
									</c:if>
								</ul>
								<br/>
							</div>
							<!-- Div Container For Current Coverred Area of Local Body Ends Here -->
						</div>
						<div class="col_1">
								<!-- Blank heading being used in GUI -->
								<h4></h4>
							</div>
							<!-- Div Container For change the current coverage type of Local Body End here-->
							<ul class="form_body">
								<li>
									<label><spring:message htmlEscape="true"  code="Label.UNMAPPPEDPARTILLYLANDREGIONS"></spring:message></label>
								</li>
							</ul>
							<!-- Div Container for coverage from Unmapped / Partially Mapped Land Regeion Elements -->
							
							
								<!-- Blank heading being used in GUI -->
							
								<ul class="form_body">
									<li id="firstLevelUnmappedLRDiv" style="display: none;">
										<div class="ms_container">
											<div class="ms_selectable">
												<label><spring:message htmlEscape="true"  code="Label.AVAILDISTRICTLIST"/></label>
												<form:select path="" id="coverageLRAvailableUnmappedDisttLevel" multiple="multiple"/>
											</div>
											<div class="ms_buttons">
												<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR" value="<spring:message code='Button.WHOLE' htmlEscape='true'/>" param="Whole"/>
												<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR" value="<spring:message code='Button.BACK' htmlEscape='true'/>" param="Back"/>
												<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR" value="<spring:message code='Button.RESET' htmlEscape='true'/>" param="Reset"/>
												<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR" value="<spring:message code='Button.PART' htmlEscape='true'/>" param="Part"/>
											</div>
											<div class="ms_selection">
												<label><spring:message htmlEscape="true" code="Label.CONTRIBUTDISTRICTLIST"/></label>
												<form:select path="contributingUnmappedDistrictCodes" id="coverageLRContributingUnmappedDisttLevel" multiple="multiple"/>
												<br/>
												<form:errors htmlEscape="true" path="contributingUnmappedDistrictCodes" cssClass="error"/>
												<input class="bttn" type="button" id="btnFetchCoverageUnmappedDistrict" value="<spring:message htmlEscape="true" code="Button.GETSUBDISTRICTL"/>" 
												       style="width: 100%" param="LB_COVERAGE_UNMAPPED_DISTRICT" level="UnmappedDisttLevel" paramLBLR="LR"/>
											</div>
											<div class="clear"></div>
										</div>
									</li>
									<li id="secondLevelUnmappedLRDiv" style="display: none;">
										<div class="ms_container">
											<div class="ms_selectable">
												<label><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"/></label>
												<form:select path="" id="coverageLRAvailableUnmappedIntermediateLevel" multiple="multiple"/>
											</div>
											<div class="ms_buttons">
												<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR" value="<spring:message code='Button.WHOLE' htmlEscape='true'/>" param="Whole"/>
												<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR" value="<spring:message code='Button.BACK' htmlEscape='true'/>"  param="Back"/>
												<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR" value="<spring:message code='Button.RESET' htmlEscape='true'/>" param="Reset"/>
												<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR" value="<spring:message code='Button.PART' htmlEscape='true'/>"  param="Part"/>
											</div>
											<div class="ms_selection">
												<label><spring:message htmlEscape="true" code="Label.CONTRIBUTSUBDISTRICTLIST"/></label>
												<form:select path="contributingUnmappedSubDistrictCodes" id="coverageLRContributingUnmappedIntermediateLevel" multiple="multiple"/>											
												<br/>
												<form:errors htmlEscape="true" path="contributingUnmappedSubDistrictCodes" cssClass="error"/>
												<input class="bttn" type="button" id="btnFetchCoverageUnmappedSubDistrict" value="<spring:message htmlEscape="true" code="Button.GETVILLAGEL"/>" 
												       style="width: 100%" param="LB_COVERAGE_UNMAPPED_SUB_DISTRICT" level="UnmappedIntermediateLevel" paramLBLR="LR"/>
											</div>
											<div class="clear"></div>
										</div>
									</li>
									<li id="thirdLevelUnmappedLRDiv" style="display: none;" >
										<div class="ms_container">
											<div class="ms_selectable">
												<label><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"/></label>
												<form:select path="" id="coverageLRAvailableUnmappedVillageLevel" multiple="multiple"/>
											</div>
											<div class="ms_buttons">
												<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR" value="<spring:message code='Button.WHOLE' htmlEscape='true'/>" param="Whole"/>
												<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR" value="<spring:message code='Button.BACK' htmlEscape='true'/>"  param="Back"/>
												<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR" value="<spring:message code='Button.RESET' htmlEscape='true'/>" param="Reset"/>
												<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR" value="<spring:message code='Button.PART' htmlEscape='true'/>"  param="Part"/>
											</div>
											<div class="ms_selection">
												<label><spring:message htmlEscape="true" code="Label.CONTRIBUTVILLAGELIST"/></label>
												<form:select path="contributingUnmappedVillageCodes" id="coverageLRContributingUnmappedVillageLevel" multiple="multiple"/>
												<br/>
												<form:errors htmlEscape="true" path="contributingUnmappedVillageCodes" cssClass="error"/>
												<c:if test="${(localBodyTypeLevel eq  VILLAGE_PANCHAYAT_LEVEL.charAt(0) and (habitationConfigration eq 'N' or  habitationConfigration eq 'V' )) }">	
												<br>
												<input class="bttn" type="button" id="btnFetchHabitationList" value="<spring:message htmlEscape="true" code="Button.GETHABITATIONLIST"/>" 
												       style="width: 100%"/>	
												</c:if>												
											</div>
											
											<div class="clear"></div>
										</div>
									</li>
									<c:if test="${(localBodyTypeLevel eq  VILLAGE_PANCHAYAT_LEVEL.charAt(0) and (habitationConfigration eq 'N' or  habitationConfigration eq 'V' )) }">		
									<li id="habitationDiv">
										<div class="ms_container">
											<div class="ms_selectable">
												<label><spring:message htmlEscape="true"  code="Label.AVAILHABITATION"/></label>
												<form:select path="avilableHabitation" id="avilableHabitation" multiple="multiple"/>
											</div>
											<div class="ms_buttons">
												<input class="bttn" type="button"  value=">>" onclick="moveElement('WHOLE');" />
												<input class="bttn" type="button" value=">" onclick="moveElement('FORWARD');" />
												<input class="bttn"  type="button"  value="<" onclick="moveElement('BACK');"/>
												<input class="bttn"  type="button" value="<<"  onclick="moveElement('BACKALL');"/>
											</div>
											<div class="ms_selection">
												<label><spring:message htmlEscape="true" code="Label.CONTRIBUTHABITATION"/></label>
												<form:select path="contributingHabiationCodes" id="contributingHabiationCodes" multiple="multiple">
													<c:forEach  var="obj" items="${mappedHabitationList}">
												 		<form:option value="${obj.habitationCode}_E">${obj.habitationNameEnglish}</form:option>
													</c:forEach>
												</form:select>
												<br/>
												<form:errors htmlEscape="true" path="contributingHabiationCodes" cssClass="error"/>
												
											</div>
											<div class="clear"></div>
										</div>
									</li>
									</c:if>
								</ul>
						</div>
					<br/>
					<!-- Block for Covered area of Local Body Ends Here -->
				
					<!-- Block for HeadQuarters Started -->							
					<div class="form_block">
						<div class="col_1">
							<h4>
								<spring:message htmlEscape="true" code="Label.HeaquarterFrame"></spring:message>
								<span class="mandate">*</span>
							</h4>
							<ul class="form_body">
								<li>
									<input type="button" class="bttn" id="actionBuildHeadQuarter" value="<spring:message code='Label.selectHQ' htmlEscape='true'/>"/>
									<table id="tblHeadquarter" class="data_grid" width="50%" style="display: none;">
										<thead>
											<tr>
												<th><spring:message code='Label.selectHQ' htmlEscape='true'/></th>
												<th id="lblHQAtLevel">
													<spring:message code='${lblHQHeading}' htmlEscape='true'/>
												</th>
											</tr>
										</thead>
										<tbody id="tbodyHQuarter">
											
										</tbody>
									</table>
									<input type="hidden" id="showMapHQError" name="showMapHQError"/> <!-- Used Element, Do not remove. -->	
									<form:errors htmlEscape="true" path="headQuarterCode" cssClass="error"/>
								</li>
							</ul>
						</div>
					</div>
					<br/>
					<!-- Block for HeadQuarters Ends Here-->
				
					
					<input class="bttn" id="btnFormActionSave" type="submit" value="<spring:message htmlEscape="true" code="Button.UPDATE"/>" param="UPDATE"/>
					<input class="bttn" type="button"  value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'"/>
					
					
				</form:form>
			</div>
		</div>
		<!-- Page Content ends -->
	</div>
	<!-- Main Form Styling ends -->
	
<!-- Select From Existing Local Government Order Started -->
<div id="displayExistingLocalBody" class="form_stylings" style="display: none;">

	<table id="existingLocalBodyDetails" class="data_grid" width="100%" style="display: none;">
		<thead>
			<tr>
				<th>Localbodycode</th>
				<th>Localbodyname</th>				
			</tr>
			
		</thead>
		<tbody id="tbodyExistLB">
		</tbody>
	</table>
</div>
<!-- Select From Existing Local Government Order Ends -->

</body>
</html>