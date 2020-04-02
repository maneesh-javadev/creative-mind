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
<c:set var="URBAN_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.LB_URBAN.toString()%>"></c:set>	
<c:set var="SUBDISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.SUB_DISTRICT_CONSTANT.toString()%>"></c:set>
<c:set var="VILLAGE_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.VILLAGE_CONSTANT.toString()%>"></c:set>
<c:set var="DISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.DISTRICT_CONSTANT.toString()%>"></c:set>
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
				<form:form action="mapCoverageLocalBody.htm" method="post" id="localBodyForm" commandName="localBodyForm" enctype="multipart/form-data">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="mapCoverageLocalBody.htm"/>" />					
					<form:hidden path="localBodyCreationType"/>
					<form:hidden path="localBodyCode" id="paramLBCode"/>
					<form:hidden path="localBodyTypeCode"/>						
					<form:hidden path="changeCoverageTypeLRList"/>
					
						<!-- Set Page Level Heading and element Id(s) Started-->
					<c:set var="lblHeadingCurrentCoverage" value="Label.AVAILABLEFORCURRENTSUB"></c:set>
					<c:set var="lblColumnHeadName" value="App.SUBDISTRICTNAMEENGLISH"></c:set>
					<c:set var="lblColumnHeadCode" value="	Label.SUBDISTRICTCODE"></c:set>
					<c:set var="selectedLevel" value="${SUBDISTRICT_CONSTANT}"></c:set>
					<c:set var="lblAvailableHeading" value="Label.AVAILSUBDISTRICTLIST"></c:set>
					<c:set var="lblContributingHeading" value="Label.CONTRIBUTSUBDISTRICTLIST"></c:set>
					<c:set var="nameElementForContributingLB" value="contributingLBSubDistrictCodes"></c:set>
					<c:set var="nameElementForContributingUnmapped" value="contributingUnmappedSubDistrictCodes"></c:set>
					<c:set var="nameElementForContributing" value="contributingLBSubDistrictCodes"></c:set>
					
					<c:if test="${isDistrictLevel}">
						<c:set var="lblHeadingCurrentCoverage" value="Label.AVAILABLEFORCURRENTDIS"></c:set>
						<c:set var="lblColumnHeadName" value="App.DISTRICTNAMEENGLISH"></c:set>
						<c:set var="lblColumnHeadCode" value="	Label.DISTRICTCODE"></c:set>
						<c:set var="selectedLevel" value="${DISTRICT_CONSTANT}"></c:set>
						<c:set var="lblAvailableHeading" value="Label.AVAILDISTRICTLIST"></c:set>
						<c:set var="lblContributingHeading" value="Label.CONTRIBUTDISTRICTLIST"></c:set>
						<c:set var="nameElementForContributingLB" value="contributingLBDistrictCodes"></c:set>
						<c:set var="nameElementForContributingUnmapped" value="contributingUnmappedDistrictCodes"></c:set>
						<c:set var="nameElementForContributing" value="contributingLBDistrictCodes"></c:set>
					</c:if> 
					<!-- Set Page Level Heading and element Id(s) Ends Here-->
			
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
									<li>
										<table class="data_grid" width="50%">
											<thead>
												<tr>
													<td colspan="4">
														<strong><spring:message htmlEscape="true" code="${lblHeadingCurrentCoverage}"></spring:message></strong>
													</td>
												</tr>
												<tr>
													<th width="20%" rowspan="2"><spring:message htmlEscape="true" code="${lblColumnHeadCode}"></spring:message></th>
													<th width="30%" rowspan="2"><spring:message htmlEscape="true" code="${lblColumnHeadName}"></spring:message></th>												
													<th colspan="2" align="center" width="40%"> Change Coverage type</th>
													
												</tr>
												<tr>
													<th width="15%"> Part</th>
													<th width="15%"> Full</th>								
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${completedCoverageDetails}" var="completedCoveragesIM" >												
													<c:if test="${completedCoveragesIM.landRegionType eq  selectedLevel}">
													<tr id="tr_${completedCoveragesIM.landRegionCode}">
														<td><c:out value="${completedCoveragesIM.landRegionCode}"></c:out></td>
														<td><c:out value="${completedCoveragesIM.landRegionNameEnglish}"></c:out></td>													
														<td><input type="radio" name="contributeddistrict[${completedCoveragesIM.count }]" id="contributedDistrictCodes_P" value="P"  paramRegionType="${selectedLevel}" param="${completedCoveragesIM.landRegionCode }" 
																<c:if test="${completedCoveragesIM.coverageType=='P'}">checked="checked"</c:if> />
														</td>	
														<td><input type="radio" name="contributeddistrict[${completedCoveragesIM.count }]" id="contributedDistrictCodes_F" value="F"   paramRegionType="${selectedLevel}" param="${completedCoveragesIM.landRegionCode }" 
																<c:if test="${completedCoveragesIM.coverageType=='F'}">checked="checked"</c:if> />	
														</td>
														
													</tr>
													</c:if>
												</c:forEach>
											</tbody>
										</table>
									</li>
									
									<c:choose>
										<c:when test="${!isDistrictLevel}">
													<li>
														<table class="data_grid" width="50%">
															<thead>
																<tr>												
																	<td colspan="4"><strong><spring:message htmlEscape="true" code="Label.AVAILABLEFORCURRENTVILL"></spring:message></strong></td>
																</tr>
																<tr>
																	<th width="20%"  rowspan="2">Village Code</th>
																	<th width="30%"  rowspan="2">Village Name</th>
																	<th colspan="2" align="center" width="40%"> Change Coverage type</th>
																</tr>
																<tr>
																	<th width="15%"> Part</th>
																	<th width="15%"> Full</th>								
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${completedCoverageDetails}" var="completedCoveragesV" >
																
																	<c:if test="${completedCoveragesV.landRegionType eq  VILLAGE_CONSTANT}">
																	<tr id="tr_${completedCoveragesV.landRegionCode}">
																		<td><c:out value="${completedCoveragesV.landRegionCode}"></c:out></td>
																		<td><c:out value="${completedCoveragesV.landRegionNameEnglish}"></c:out></td>	
																		<td><input type="radio" name="contributedvillage[${completedCoveragesV.count }]" id="contributedDistrictCodes_P" value="P"  paramRegionType="${VILLAGE_CONSTANT}" param="${completedCoveragesV.landRegionCode }" 
																				<c:if test="${completedCoveragesV.coverageType=='P'}">checked="checked"</c:if> />
																		</td>	
																		<td><input type="radio" name="contributedvillage[${completedCoveragesV.count }]" id="contributedDistrictCodes_F" value="F" paramRegionType="${VILLAGE_CONSTANT}" param="${completedCoveragesV.landRegionCode }" 
																				<c:if test="${completedCoveragesV.coverageType=='F'}">checked="checked"</c:if> />	
																		</td>
																														
																	</tr>
																	</c:if>
																</c:forEach>
															</tbody>
														</table>
													</li>
										</c:when>
										<c:otherwise>
												<li>
														<table class="data_grid" width="50%">
															<thead>
																<tr>												
																	<td colspan="4"><strong><spring:message htmlEscape="true" code="Label.AVAILABLEFORCURRENTSUB"></spring:message></strong></td>
																</tr>
																<tr>
																	<th width="20%"  rowspan="2">Sub-District Code</th>
																	<th width="30%"  rowspan="2">Sub-District Name</th>
																	<th colspan="2" align="center" width="40%"> Change Coverage type</th>
																</tr>
																<tr>
																	<th width="15%"> Part</th>
																	<th width="15%"> Full</th>								
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${completedCoverageDetails}" var="completedCoveragesV" >
																
																	<c:if test="${completedCoveragesV.landRegionType eq  SUBDISTRICT_CONSTANT}">
																	<tr id="tr_${completedCoveragesV.landRegionCode}">
																		<td><c:out value="${completedCoveragesV.landRegionCode}"></c:out></td>
																		<td><c:out value="${completedCoveragesV.landRegionNameEnglish}"></c:out></td>	
																		<td><input type="radio" name="contributedvillage[${completedCoveragesV.count }]" id="contributedDistrictCodes_P" value="P"  paramRegionType="${VILLAGE_CONSTANT}" param="${completedCoveragesV.landRegionCode }" 
																				<c:if test="${completedCoveragesV.coverageType=='P'}">checked="checked"</c:if> />
																		</td>	
																		<td><input type="radio" name="contributedvillage[${completedCoveragesV.count }]" id="contributedDistrictCodes_F" value="F" paramRegionType="${VILLAGE_CONSTANT}" param="${completedCoveragesV.landRegionCode }" 
																				<c:if test="${completedCoveragesV.coverageType=='F'}">checked="checked"</c:if> />	
																		</td>
																														
																	</tr>
																	</c:if>
																</c:forEach>
															</tbody>
														</table>
													</li>
										</c:otherwise>
									</c:choose>
							
									
								</ul>
								<br/>
							</div>
							<!-- Div Container For Current Coverred Area of Local Body Ends Here -->
							
							<!-- Div Container For change the current coverage type of Local Body -->
							<div class="col_1">
								<!-- Blank heading being used in GUI -->
								<h4></h4>
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
							
							<div class="col_1">
								<ul class="form_body">									
									<li>
										<div class="ms_container">
											<div class="ms_selectable">
												<label><spring:message htmlEscape="true" code="${lblAvailableHeading}"/>
												 </label>
												<form:select path="" id="coverageLRAvailableUnmappedIntermediateLevel" multiple="multiple"/>											
											</div>
											<div class="ms_buttons">
												<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR" value="<spring:message code='Button.WHOLE' htmlEscape='true'/>" param="Whole"/>
												<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR" value="<spring:message code='Button.BACK' htmlEscape='true'/>"  param="Back"/>
												<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR" value="<spring:message code='Button.RESET' htmlEscape='true'/>" param="Reset"/>
												<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR" value="<spring:message code='Button.PART' htmlEscape='true'/>"  param="Part"/>
											</div>
											<div class="ms_selection">
												<label><spring:message htmlEscape="true" code="${lblContributingHeading}"/></label>
												<form:select path="${nameElementForContributingUnmapped }" id="coverageLRContributingUnmappedIntermediateLevel" multiple="multiple"  name="${nameElementForContributingUnmapped }"/>											
												<br/>
												<form:errors htmlEscape="true" path="${nameElementForContributingUnmapped }" cssClass="error"/>
											
												<c:choose>
													<c:when test="${!isDistrictLevel}">
														<input class="bttn" type="button" id="btnFetchCoverageUnmappedSubDistrict" value="<spring:message htmlEscape="true" code="Button.GETVILLAGEL"/>" 
													       style="width: 100%" param="LB_COVERAGE_UNMAPPED_SUB_DISTRICT" level="UnmappedIntermediateLevel" paramLBLR="LR"/>
												 
													</c:when>
													<c:otherwise>
														<input class="bttn" type="button" id="btnFetchCoverageUnmappedSubDistrict" value="<spring:message htmlEscape="true" code="Button.GETSUBDISTRICTL"/>" 
													       style="width: 100%" param="LB_COVERAGE_UNMAPPED_SUB_DISTRICT" level="UnmappedIntermediateLevel" paramLBLR="LR"/>
													</c:otherwise>
												</c:choose>
												
											</div>
											<div class="clear"></div>
										</div>
									</li>
									
									<c:choose>
										<c:when test="${!isDistrictLevel}">
											<li>
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
													</div>
													<div class="clear"></div>
												</div>
											</li>
										</c:when>
										<c:otherwise>
											<li>
												<div class="ms_container">
													<div class="ms_selectable">
														<label><spring:message htmlEscape="true" code="Available Sub-District list"/></label>
														<form:select path="" id="coverageLRAvailableUnmappedVillageLevel" multiple="multiple"/>
													</div>
													<div class="ms_buttons">
														<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR" value="<spring:message code='Button.WHOLE' htmlEscape='true'/>" param="Whole"/>
														<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR" value="<spring:message code='Button.BACK' htmlEscape='true'/>"  param="Back"/>
														<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR" value="<spring:message code='Button.RESET' htmlEscape='true'/>" param="Reset"/>
														<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR" value="<spring:message code='Button.PART' htmlEscape='true'/>"  param="Part"/>
													</div>
													<div class="ms_selection">
														<label><spring:message htmlEscape="true" code="Label.CONTRIBUTSUBDISTRICTLIST"/></label>
														<form:select path="contributingUnmappedSubDistrictCodes" id="coverageLRContributingUnmappedVillageLevel" multiple="multiple"/>
														<br/>
														<form:errors htmlEscape="true" path="contributingUnmappedVillageCodes" cssClass="error"/>											
													</div>
													<div class="clear"></div>
												</div>
											</li>
										</c:otherwise>
									</c:choose>
									
									
								</ul>
							</div>
							<!-- Div Container For Unmapped/Partially Mapped Coverred Area of Local Body Ends Here--> 	
						</div>
					</div>
					<br/>
						
					<!-- Block for Government Order Details Ends Here-->						
					<input class="bttn" id="btnFormActionSave" type="submit" value="<spring:message htmlEscape="true" code="Button.UPDATE"/>" param="UPDATE"/>
					<input class="bttn" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'"/>
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