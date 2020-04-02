<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="ChangeCoverageJavascript.jsp"%>
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
			<h3><spring:message code="Label.CORRECTGOVTLOCALBODY.CC" htmlEscape="true"></spring:message></h3>
			<ul class="item_list">
				<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form action="changeCoverageLocalBody.htm" method="post" id="localBodyForm" commandName="localBodyForm" enctype="multipart/form-data">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="changeCoverageLocalBody.htm"/>" />
					<form:hidden path="processAction"/>
					<form:hidden path="localBodyCreationType"/>
					<form:hidden path="localBodyCode" id="paramLBCode"/>
					<form:hidden path="localBodyTypeCode"/>
					<form:hidden path="removedLangRegionCodes"/>
					<form:hidden path="isGISCoverage"/>
					<form:hidden path="localBodyNameEnglish" />
					<!-- For Preview GIS Coverage#started -->
					<div id="dialogBX" style="display: none;">
						<iframe id="myIframe" width="910" height="650"></iframe>
					</div>
					<!-- For Preview GIS Coverage#end -->
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
							<!-- Div Container For Current Coverred Area of Local Body -->
							<div class="col_1">
								<!-- Blank heading being used in GUI -->
								<!-- <h4></h4> -->
								<ul class="form_body">
								<c:choose>
								<c:when test="${localBodyForm.isGISCoverage eq 'Y'}">
										<li>
											<table id="tblCoverageGIS_${VILLAGE_PANCHAYAT_LEVEL}" class="data_grid" width="50%">
												<thead>
													<tr>
														<td colspan="4"><strong>Current GIS Covered Village</strong></td>
													</tr>
													<tr>
														<th width="30%">Village Code</th>
														<th width="60%">Village Name</th>
														<th width="30%">Coverage Type</th>
														<th>Mapped Localbody</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${completedCoverageDetails}" var="completedCoveragesV" >
														<c:if test="${completedCoveragesV.landRegionType eq  VILLAGE_CONSTANT}">
														<tr id="tr_${completedCoveragesV.landRegionCode}">
															<td><c:out value="${completedCoveragesV.landRegionCode}"></c:out></td>
															<td><c:out value="${completedCoveragesV.landRegionNameEnglish}"></c:out></td>
															<td><c:choose><c:when test="${completedCoveragesV.coverageType eq 'P'}">
																	<c:out value="PART"/>
																</c:when> 
																<c:otherwise><c:out value="FULL"/></c:otherwise> </c:choose>  
														  </td>
														  <td><c:out value="${completedCoveragesV.mappedLocalbody}"></c:out></td>
														</tr>
														</c:if>
													</c:forEach>
												</tbody>
											</table>
										</li>
								</c:when>
								<c:otherwise>
									<c:if test="${localBodyTypeLevel eq DISTRICT_PANCHAYAT_LEVEL.charAt(0)}">	
										<li>
											<table id="tblCoverage_${DISTRICT_PANCHAYAT_LEVEL}" class="data_grid" width="50%">
												<thead>
													<tr>
														<td colspan="3"><strong>Current Covered District</strong></td>
													</tr>
													<tr>
														<th width="30%">District Code</th>
														<th width="60%">District Name</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${completedCoverageDetails}" var="completedCoveragesDistrict" >
														<c:if test="${completedCoveragesDistrict.landRegionType eq  DISTRICT_CONSTANT}">
															<tr id="tr_${completedCoveragesDistrict.landRegionCode}">
																<td><c:out value="${completedCoveragesDistrict.landRegionCode}"></c:out></td>
																<td><c:out value="${completedCoveragesDistrict.landRegionNameEnglish}"></c:out></td>
																<td>
																	<img id="remove_${completedCoveragesDistrict.parentCode}" alt="remove" src="images/delete.png" param="${completedCoveragesDistrict.landRegionCode}">
																</td>
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
														<td colspan="3"><strong>Current Covered Sub-district</strong></td>
													</tr>
													<tr>
														<th width="30%">Sub-district Code</th>
														<th width="60%">Sub-district Name</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${completedCoverageDetails}" var="completedCoveragesIM" >
														<c:if test="${completedCoveragesIM.landRegionType eq  SUBDISTRICT_CONSTANT}">
														<tr id="tr_${completedCoveragesIM.landRegionCode}">
															<td><c:out value="${completedCoveragesIM.landRegionCode}"></c:out></td>
															<td><c:out value="${completedCoveragesIM.landRegionNameEnglish}"></c:out></td>
															<td><img id="remove_${completedCoveragesIM.parentCode}" alt="remove" src="images/delete.png" param="${completedCoveragesIM.landRegionCode}">
																<%-- <input type="text" value="${completedCoveragesIM.parentCode}"/> --%>
															</td>
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
														<td colspan="3"><strong>Current Covered Village</strong></td>
													</tr>
													<tr>
														<th width="30%">Village Code</th>
														<th width="60%">Village Name</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${completedCoverageDetails}" var="completedCoveragesV" >
														<c:if test="${completedCoveragesV.landRegionType eq  VILLAGE_CONSTANT}">
														<tr id="tr_${completedCoveragesV.landRegionCode}">
															<td><c:out value="${completedCoveragesV.landRegionCode}"></c:out></td>
															<td><c:out value="${completedCoveragesV.landRegionNameEnglish}"></c:out></td>
															<td><img id="remove_${completedCoveragesV.parentCode}" alt="remove" src="images/delete.png" param="${completedCoveragesV.landRegionCode}">
																<%-- <input type="text" value="${completedCoveragesV.parentCode}"/> --%>
															</td>
														</tr>
														</c:if>
													</c:forEach>
												</tbody>
											</table>
										</li>
									</c:if>
								</c:otherwise>
								</c:choose>
								
									
								
								</ul>
							</div>
							<!-- Div Container For Current Coverred Area of Local Body Ends Here -->
						</div>
					</div>
					
					<br/>
				
				
				
				<!-- This Block used for Covered area of Local Body Started -->	
				<%-- <c:if test="${localBodyForm.isGISCoverage ne 'Y'}"> --%>
					<div class="form_block">
						<div class="col_1">
							<h4><spring:message htmlEscape="true"  code="Label.COVEREDAREAOFNEWLCLBDY"></spring:message></h4>
							<ul class="form_body">
								<li>
									<label class="inline">
									<c:choose>
									<c:when test="${localBodyForm.isGISCoverage ne 'Y'}">
									<form:checkbox path="checkboxCoverageLB" id="checkboxCoverageLB" param="LB_COVERAGE"   />
									</c:when>
									<c:otherwise>
									<form:checkbox path="checkboxCoverageLB" id="checkboxCoverageLB" param="LB_COVERAGE" disabled = "true" />
									</c:otherwise>
									</c:choose>
										
								    	<spring:message htmlEscape="true" code="Label.SELEXISTINGCOVEREDAREA"></spring:message>
								    	<br/>
								    	<form:errors htmlEscape="true" path="checkboxCoverageLB" cssClass="error"/>	
									</label>	
								</li>
							</ul>	
							<!-- Div Container For Coverage from Existing Local Bodies Elements -->
							<div class="col_1">
								<!-- Blank heading being used in GUI -->
								<h4></h4>
								<ul class="form_body">
								<li id="firstLevelLBDiv" style="display: none;">
									<div class="ms_container">
										<div class="ms_selectable">
											<label id="labelAvailablePanchayat">
												<spring:message htmlEscape="true" code="Label.AVAILABLE"/>
												<spring:message htmlEscape="true" code="${lblLocalBodyHeading}"/>
											</label>
											<form:select path="" id="coverageLBAvailablePanchayatLevel" multiple="multiple"/>
										</div>
										<div class="ms_buttons">
											<input class="bttn" id="btnEventCoverage" type="button" level="PanchayatLevel" paramLBLR="LB" value="<spring:message code='Button.WHOLE' htmlEscape='true'/>" param="Whole"/>
											<input class="bttn" id="btnEventCoverage" type="button" level="PanchayatLevel" paramLBLR="LB" value="<spring:message code='Button.BACK' htmlEscape='true'/>"  param="Back"/>
											<input class="bttn" id="btnEventCoverage" type="button" level="PanchayatLevel" paramLBLR="LB" value="<spring:message code='Button.RESET' htmlEscape='true'/>" param="Reset"/>
											<input class="bttn" id="btnEventCoverage" type="button" level="PanchayatLevel" paramLBLR="LB" value="<spring:message code='Button.PART' htmlEscape='true'/>"  param="Part"/>
										</div>
										<div class="ms_selection">
											<label id="labelContributingPanchayat">
												<spring:message htmlEscape="true" code="Label.CONTRIBUTE"/>
												<spring:message htmlEscape="true" code="${lblLocalBodyHeading}"/>
											</label>
											<form:select path="contributingLBCodes" id="coverageLBContributingPanchayatLevel" multiple="multiple"/>
											<br/>
								    		<form:errors htmlEscape="true" path="contributingLBCodes" cssClass="error"/>	
											<input class="bttn" type="button" id="fetchCoverageofLocalBody" value="<spring:message htmlEscape="true" code="Button.GETCOVEREDAREAOFLOCALBODY"/>" 
												   style="width:100%;" level="PanchayatLevel" paramLBLR="LB"/>																
										</div>
										<div class="clear"></div>
									</div>
								</li>
								<li id="secondLevelLBDiv" style="display: none;">
									<div class="ms_container">
										<div class="ms_selectable">
											<label><spring:message htmlEscape="true" code="Label.AVAILDISTRICTLIST"/></label>
											<form:select path="" id="coverageLBAvailableDisttLevel" multiple="multiple"/>
										</div>
										<div class="ms_buttons">
											<input class="bttn" id="btnEventCoverage" type="button" level="DisttLevel" paramLBLR="LB" value="<spring:message code='Button.WHOLE' htmlEscape='true'/>" param="Whole"/>
											<input class="bttn" id="btnEventCoverage" type="button" level="DisttLevel" paramLBLR="LB" value="<spring:message code='Button.BACK' htmlEscape='true'/>"  param="Back"/>
											<input class="bttn" id="btnEventCoverage" type="button" level="DisttLevel" paramLBLR="LB" value="<spring:message code='Button.RESET' htmlEscape='true'/>" param="Reset"/>
											<input class="bttn" id="btnEventCoverage" type="button" level="DisttLevel" paramLBLR="LB" value="<spring:message code='Button.PART' htmlEscape='true'/>"  param="Part"/>
										</div>
										<div class="ms_selection">
											<label><spring:message htmlEscape="true" code="Label.CONTRIBUTDISTRICTLIST"/></label>
											<form:select path="contributingLBDistrictCodes" id="coverageLBContributingDisttLevel" multiple="multiple"/>
											<br/>
											<form:errors htmlEscape="true" path="contributingLBDistrictCodes" cssClass="error"/>	
											<input class="bttn" type="button" id="btnFetchCoverageDistrict" value="<spring:message htmlEscape="true" code="Button.GETSUBDISTRICTL"/>" 
												   style="width: 100%;" param="LB_COVERAGE_DISTRICT" level="DisttLevel" paramLBLR="LB"/>										
										</div>
										<div class="clear"></div>
									</div>
								</li>
								<li id="thirdLevelLBDiv" style="display: none;">
									<div class="ms_container">
										<div class="ms_selectable">
											<label><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"/></label>
											<form:select path="" id="coverageLBAvailableSubdisttLevel" multiple="multiple"/>
										</div>
										<div class="ms_buttons">
											<input class="bttn" id="btnEventCoverage" type="button" level="SubdisttLevel" paramLBLR="LB" value="<spring:message code='Button.WHOLE' htmlEscape='true'/>" param="Whole"/>
											<input class="bttn" id="btnEventCoverage" type="button" level="SubdisttLevel" paramLBLR="LB" value="<spring:message code='Button.BACK' htmlEscape='true'/>"  param="Back"/>
											<input class="bttn" id="btnEventCoverage" type="button" level="SubdisttLevel" paramLBLR="LB" value="<spring:message code='Button.RESET' htmlEscape='true'/>" param="Reset"/>
											<input class="bttn" id="btnEventCoverage" type="button" level="SubdisttLevel" paramLBLR="LB" value="<spring:message code='Button.PART' htmlEscape='true'/>"  param="Part"/>
										</div>
										<div class="ms_selection">
											<label><spring:message htmlEscape="true" code="Label.CONTRIBUTSUBDISTRICTLIST"/></label>
											<form:select path="contributingLBSubDistrictCodes" id="coverageLBContributingSubdisttLevel" multiple="multiple"/>
											<br/>
											<form:errors htmlEscape="true" path="contributingLBSubDistrictCodes" cssClass="error"/>
											<input class="bttn" type="button" id="btnFetchCoverageSubDistrict" value="<spring:message htmlEscape="true" code="Button.GETVILLAGEL"/>" 
												   style="width:100%" param="LB_COVERAGE_SUB_DISTRICT" level="SubdisttLevel" paramLBLR="LB"/>
										</div>
										<div class="clear"></div>
									</div>
								</li>
								<li id="fourthLevelLBDiv" style="display: none;">
									<div class="ms_container">
										<div class="ms_selectable">
											<label><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"/></label>
											<form:select path="" id="coverageLBAvailableVillageLevel" multiple="multiple"/>
										</div>
										<div class="ms_buttons">
											<input class="bttn" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB" value="<spring:message code='Button.WHOLE' htmlEscape='true'/>" param="Whole"/>
											<input class="bttn" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB" value="<spring:message code='Button.BACK' htmlEscape='true'/>"  param="Back"/>
											<input class="bttn" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB" value="<spring:message code='Button.RESET' htmlEscape='true'/>" param="Reset"/>
											<input class="bttn" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB" value="<spring:message code='Button.PART' htmlEscape='true'/>"  param="Part"/>
										</div>
										<div class="ms_selection">
											<label><spring:message htmlEscape="true"  code="Label.CONTRIBUTVILLAGELIST"/></label>
											<form:select path="contributingLBVillageCodes" id="coverageLBContributingVillageLevel" multiple="multiple"/>
											<br/>
											<form:errors htmlEscape="true" path="contributingLBVillageCodes" cssClass="error"/>
										</div>
										<div class="clear"></div>
									</div>
								</li>
								</ul>
							</div>
							<br/>
							
							<ul class="form_body">
								<li>
									<label class="inline">
										<c:choose>
										<c:when test="${localBodyForm.isGISCoverage ne 'Y'}">
										<form:checkbox path="checkboxCoverageUnmapped" id="checkboxCoverageUnmapped" param="UNMAPPED_COVERAGE"/>
										</c:when>
										<c:otherwise>
										<form:checkbox path="checkboxCoverageUnmapped" id="checkboxCoverageUnmapped" param="UNMAPPED_COVERAGE" disabled = "true" />
										</c:otherwise>
										</c:choose>
										
										<spring:message htmlEscape="true" code="Label.SELCONTRIBUTEUNMAPPEDPARTIALLY"/>
										<br/>
										<form:errors htmlEscape="true" path="checkboxCoverageUnmapped" cssClass="error"/>	
									</label>
								</li>
							</ul>
							
							<!-- Div Container for coverage from Unmapped / Partially Mapped Land Regeion Elements -->
							<div class="col_1">
								<!-- Blank heading being used in GUI -->
								<h4></h4>
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
									<li id="thirdLevelUnmappedLRDiv" style="display: none;">
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
								</ul>
							</div>
						</div>
					</div>
					<%-- </c:if> --%>
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
									<table id="tblHeadquarter" class="data_grid" width="50%">
										<thead>
											<tr>
												<th><spring:message code='Label.selectHQ' htmlEscape='true'/></th>
												<th id="lblHQAtLevel">
													
													<spring:message code='${lblHQHeading}' htmlEscape='true'/>
												</th>
											</tr>
										</thead>
										<tbody id="tbodyHQuarter">
											<c:forEach items="${completedCoverageDetails}" var="completedCoveragesHQ" >
												<tr id="hqTR_${completedCoveragesHQ.landRegionCode}">
													<c:choose>
														<c:when test="${completedCoveragesHQ.landRegionType.charAt(0) eq  localBodyTypeLevel}">
															<td><input type="radio" name="headQuarterCode" value="${completedCoveragesHQ.landRegionCode}" 
															    <c:if test="${completedCoveragesHQ.landRegionCode eq localBodyForm.headQuarterCode}">checked="checked"</c:if>>
															</td>
															<td><c:out value="${completedCoveragesHQ.landRegionNameEnglish}"></c:out></td>
														</c:when>
													</c:choose>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<br/>
									<input type="hidden" id="showHQError" name="showHQError"/><!-- Used Element, Do not remove. -->
									<form:errors htmlEscape="true" path="headQuarterCode" cssClass="error"/>
								</li>
							</ul>
						</div>
					</div>
					<br/>
					<!-- Block for HeadQuarters Ends Here-->
				
					<!-- Block for Government Order Details Started -->
					<%@include file="../ExistingGovernmentOrder.jsp"%>	
					<br/>
								
					
					<!-- Block for Government Order Details Ends Here-->
					<!-- For Preview GIS Coverage#started -->
					<c:if test="${localBodyTypeLevel eq  VILLAGE_PANCHAYAT_LEVEL.charAt(0)}">	
					<input class="bttn" id="btnPreviewGIS" type="button" value="<spring:message code='Button.PREVIEWGIS' htmlEscape='true'/>"  />
					</c:if> 
					<!-- For Preview GIS Coverage#end -->
					<c:if test="${localBodyForm.isGISCoverage ne 'Y'}">
					
					<input class="bttn" id="btnFormActionSaveDraft" type="submit" value="<spring:message code='Button.DRAFT' htmlEscape='true'/>" param="DRAFT"/>
					</c:if>
					<input class="bttn" id="btnFormActionSave" type="submit" value="<spring:message htmlEscape="true" code="Button.SP"/>" param="PUBLISH"/>
					<input class="bttn" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'"/>
					
					
				</form:form>
			</div>
		</div>
		<!-- Page Content ends -->
	</div>
	<!-- Main Form Styling ends -->
</body>
</html>