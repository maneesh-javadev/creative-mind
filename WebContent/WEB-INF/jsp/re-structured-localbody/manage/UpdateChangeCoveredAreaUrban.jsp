<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="ChangeCoverageUrbanJavascript.jsp"%>
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
<link href="<%=contextpthval%>/resources-localbody/css/jquery.dataTables.css" rel="stylesheet" type="text/css">	
<script src="<%=contextpthval%>/resources-localbody/scripts/jquery.dataTables.js" type="text/javascript" language="javascript" ></script>
<c:set var="URBAN_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.LB_URBAN.toString()%>"></c:set>
<c:set var="DISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.DISTRICT_CONSTANT.toString()%>"></c:set>
<c:set var="SUBDISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.SUB_DISTRICT_CONSTANT.toString()%>"></c:set>
<c:set var="VILLAGE_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.VILLAGE_CONSTANT.toString()%>"></c:set>

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
					<form:hidden path="id" id="paramLBCode"/>
					<form:hidden path="processAction"/>
					<form:hidden path="localBodyCode"/>
					<form:hidden path="localBodyTypeCode"/>
					<form:hidden path="localBodyCreationType"/>
					<form:hidden path="removedLangRegionCodes"/>
					<form:hidden path="isResetedCoverage"/>
					
					<!-- Set Page Level Heading and element Id(s) Started-->
					<c:set var="lblHeadingCurrentCoverage" value="Label.AVAILABLEFORCURRENTSUB"></c:set>
					<c:set var="lblColumnHeadName" value="App.SUBDISTRICTNAMEENGLISH"></c:set>
					<c:set var="lblColumnHeadCode" value="	Label.SUBDISTRICTCODE"></c:set>
					<c:set var="selectedLevel" value="${SUBDISTRICT_CONSTANT}"></c:set>
					<c:set var="lblAvailableHeading" value="Label.AVAILSUBDISTRICTLIST"></c:set>
					<c:set var="lblContributingHeading" value="Label.CONTRIBUTSUBDISTRICTLIST"></c:set>
					<c:set var="nameElementForContributingLB" value="contributingLBSubDistrictCodes"></c:set>
					<c:set var="nameElementForContributingUnmapped" value="contributingUnmappedSubDistrictCodes"></c:set>
					<c:if test="${isDistrictLevel}">
						<c:set var="lblHeadingCurrentCoverage" value="Label.AVAILABLEFORCURRENTDIS"></c:set>
						<c:set var="lblColumnHeadName" value="App.DISTRICTNAMEENGLISH"></c:set>
						<c:set var="lblColumnHeadCode" value="	Label.DISTRICTCODE"></c:set>
						<c:set var="selectedLevel" value="${DISTRICT_CONSTANT}"></c:set>
						<c:set var="lblAvailableHeading" value="Label.AVAILDISTRICTLIST"></c:set>
						<c:set var="lblContributingHeading" value="Label.CONTRIBUTDISTRICTLIST"></c:set>
						<c:set var="nameElementForContributingLB" value="contributingLBDistrictCodes"></c:set>
						<c:set var="nameElementForContributingUnmapped" value="contributingUnmappedDistrictCodes"></c:set>
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
							
							<!-- Div Container For Current Coverred Area of Local Body -->
							<div class="col_1">
								<ul class="form_body">
									<li>
										<table class="data_grid" width="50%">
											<thead>
												<tr>
													<td colspan="3"><strong><spring:message htmlEscape="true" code="${lblHeadingCurrentCoverage}"></spring:message></strong></td>
												</tr>
												<tr>
													<th width="10%"><spring:message htmlEscape="true" code="${lblColumnHeadCode}"></spring:message></th>
													<th width="80%"><spring:message htmlEscape="true" code="${lblColumnHeadName}"></spring:message></th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${completedCoverageDetails}" var="completedCoverages">
													<c:if test="${completedCoverages.landRegionType eq  selectedLevel}">
														<tr id="tr_${completedCoverages.landRegionCode}">
															<td><c:out value="${completedCoverages.landRegionCode}"></c:out></td>
															<td><c:out value="${completedCoverages.landRegionNameEnglish}"></c:out></td>
															<td>
																<img id="remove_${completedCoverages.parentCode}" alt="remove" src="images/delete.png" param="${completedCoverages.landRegionCode}">
															</td>
														</tr>
													</c:if>
												</c:forEach>
											</tbody>
										</table>
									</li>
									<c:if test="${!isDistrictLevel}">
									<li>
										<table class="data_grid" width="50%">
											<thead>
												<tr>
													<td colspan="3"><strong><spring:message htmlEscape="true" code="Label.AVAILABLEFORCURRENTVILL"></spring:message></strong></td>
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
														<td>
															<img id="remove_${completedCoveragesV.parentCode}" alt="remove" src="images/delete.png" param="${completedCoveragesV.landRegionCode}">
														</td>												
													</tr>
													</c:if>
												</c:forEach>
											</tbody>
										</table>
									</li>
									</c:if>
								</ul>
							</div>
							<!-- Div Container For Current Coverred Area of Local Body Ends Here -->
						</div>
					</div>
					<br/>	
						
					<!-- Block for Coverage Details of Drafted Local Government Body. -->
					<div id="divExistingDraftedLBCoverages" class="form_stylings">
						<form:hidden path="hidContributingLBCodes"/>
						<form:hidden path="hidContributingLandRegionCodes"/>
						<div class="form_block">
							<div class="col_1">
								<h4>
									<spring:message htmlEscape="true"  code="Label.COVEREDAREAOFNEWLCLBDY"></spring:message>&nbsp;
									<input type="button" id="btnResetExistingCoverages" class="bttn" value="RESET COVERAGES"/>
								</h4>
								
								<ul class="form_body" >
									<c:set var="localBodyAvailabe" value=""></c:set>
									<c:set var="districtAvailable" value=""></c:set>
									<c:set var="subDistrictAvailable" value=""></c:set>
									<c:set var="villageAvailable" value=""></c:set>
									<c:set var="headquarterDetails" value=""></c:set>
									<c:forEach var="coverages" items="${draftedLBCoverages}">
										<c:choose>
											<c:when test="${coverages.lbLrType eq LB_CONSTANT}">
												<c:set var="localBodyAvailabe" value="true"/>
											</c:when>
											<c:when test="${coverages.lbLrType eq DISTRICT_CONSTANT}">
												<c:set var="districtAvailable" value="true"/>
											</c:when>
											<c:when test="${coverages.lbLrType eq SUBDISTRICT_CONSTANT}">
												<c:set var="subDistrictAvailable" value="true"/>
											</c:when>
											<c:when test="${coverages.lbLrType eq VILLAGE_CONSTANT}">
												<c:set var="villageAvailable" value="true"/>
											</c:when>
										</c:choose>
										<c:if test="${coverages.headquarter}">
											<c:set var="headquarterDetails" value="${coverages}"/>
										</c:if>
									</c:forEach>
									<li>
										<c:if test="${localBodyAvailabe}">
											<table class="data_grid" width="100%">
												<thead>
													<tr>
														<td colspan="5"><strong><spring:message code="Label.NewLBCreated" htmlEscape="true"></spring:message></strong></td>
													</tr>
													<tr>
														<th><spring:message code="Label.LOCALBODYCODE" htmlEscape="true"/></th>
														<th><spring:message code="Label.LOCALBODYVERSION" htmlEscape="true"/></th>
														<th><spring:message code='LOCALBODYNAMEENGLISH' htmlEscape='true'/></th>
														<th><spring:message code='LOCALBODYNAMEINLOCAL' htmlEscape='true'/></th>
														<th><spring:message code='Label.CoverageType' htmlEscape='true'/></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="listContributingLB" items="${draftedLBCoverages}">
														<c:if test="${listContributingLB.lbLrType eq LB_CONSTANT}">
															<tr>
																<td><c:out value="${listContributingLB.lbLrCode}"/> </td>
																<td><c:out value="${listContributingLB.lbLrVersion}"/></td>
																<td><c:out value="${listContributingLB.lbLrNameEnglish}"/></td>
																<td><c:out value="${listContributingLB.lbLrNameLocal}"/></td>
																<td><c:out value="${listContributingLB.coverageType}"/></td>
															</tr>
														</c:if>	
													</c:forEach>
												</tbody>
											</table>
											<br/>
										</c:if>
									
										<c:if test="${districtAvailable}">
												
											<table class="data_grid" width="100%">
												<thead>
													<tr>
														<td colspan="4"><strong><spring:message code="Label.CONTRIBUTINGDISTRICT" htmlEscape="true"/></strong></td>
													</tr>
													<tr>
														<th><spring:message code="Label.DISTRICTCODE" htmlEscape="true"/></th>
														<th><spring:message code="Label.DISTRICTVERSION" htmlEscape="true"/></th>
														<th><spring:message code="Label.DISTRICTNAMEENGLISH" htmlEscape="true"/></th>
														<th><spring:message code='Label.CoverageType' htmlEscape='true'/></th>
													</tr>
												</thead>
												<tbody>	
													<c:forEach var="listContributingDistrict" items="${draftedLBCoverages}">
														<c:if test="${listContributingDistrict.lbLrType eq DISTRICT_CONSTANT}">
															<tr>
																<td><c:out value="${listContributingDistrict.lbLrCode}"/> </td>
																<td><c:out value="${listContributingDistrict.lbLrVersion}"/></td>
																<td><c:out value="${listContributingDistrict.lbLrNameEnglish}"/></td>
																<td><c:out value="${listContributingDistrict.coverageType}"/></td>
															</tr>
														</c:if>	
													</c:forEach>
												</tbody>
											</table>
											<br/>
										</c:if>
										
										<c:if test="${subDistrictAvailable}">
											<ul class="form_body">
												<li>
													<!-- Blank heading being used in GUI -->
													<h4></h4>
												</li>
											</ul>
											<table class="data_grid" width="100%">
												<thead>
													<tr>
														<td colspan="4"><strong><spring:message code="Label.CONTRIBUTINGSUBDISTRICT" htmlEscape="true"/></strong></td>
													</tr>
													<tr>
														<th><spring:message code="Label.SUBDISTRICTCODE" htmlEscape="true"/></th>
														<th><spring:message code="Label.SUBDISTRICTVERSION" htmlEscape="true"/></th>
														<th><spring:message code="Label.SUBDISTRICTNAMEENGLISH" htmlEscape="true"/></th>
														<th><spring:message code='Label.CoverageType' htmlEscape='true'/></th>
													</tr>
												</thead>
												<tbody>	
													<c:forEach var="listContributingSubDistrict" items="${draftedLBCoverages}">
														<c:if test="${listContributingSubDistrict.lbLrType eq SUBDISTRICT_CONSTANT}">
															<tr>
																<td><c:out value="${listContributingSubDistrict.lbLrCode}"/> </td>
																<td><c:out value="${listContributingSubDistrict.lbLrVersion}"/></td>
																<td><c:out value="${listContributingSubDistrict.lbLrNameEnglish}"/></td>
																<td><c:out value="${listContributingSubDistrict.coverageType}"/></td>
															</tr>
														</c:if>
													</c:forEach>
												</tbody>
											</table>
											<br/>
										</c:if>
										
										<c:if test="${villageAvailable}">
											<ul class="form_body">
												<li>
													<!-- Blank heading being used in GUI -->
													<h4></h4>
												</li>
											</ul>
											<table class="data_grid" width="100%">
												<thead>
													<tr>
														<td colspan="4"><strong><spring:message code="Label.CONTRIBUTINGVILLAGES" htmlEscape="true"/></strong></td>
													</tr>
													<tr>
														<th><spring:message code="Label.VILLAGECODE" htmlEscape="true"/></th>
														<th><spring:message code="Label.VILLAGEVERSION" htmlEscape="true"/></th>
														<th><spring:message code="Label.VILLAGENAMEINENGLISH" htmlEscape="true"/></th>
														<th><spring:message code='Label.CoverageType' htmlEscape='true'/></th>
													</tr>
												</thead>
												<tbody>	
													<c:forEach var="listContributingVillage" items="${draftedLBCoverages}">
														<c:if test="${listContributingVillage.lbLrType eq VILLAGE_CONSTANT}">
															<tr>
																<td><c:out value="${listContributingVillage.lbLrCode}"/> </td>
																<td><c:out value="${listContributingVillage.lbLrVersion}"/></td>
																<td><c:out value="${listContributingVillage.lbLrNameEnglish}"/></td>
																<td><c:out value="${listContributingVillage.coverageType}"/></td>
															</tr>
														</c:if>
													</c:forEach>
												</tbody>
											</table>
											<br/>
										</c:if>
									</li>
								</ul>	
							</div>
						</div>
						<br/>
					</div>
					<!-- Block for Coverage Details of Drafted Local Government Body Ends. -->	
						
							
					<!-- This Block used for Covered area of Local Body Started -->	
					<div id="divCompleteLBCoverages" class="form_block" style="display: none;">
						<div class="col_1">
							<h4><spring:message htmlEscape="true"  code="Label.COVEREDAREAOFNEWLCLBDY"></spring:message></h4>
							<ul class="form_body">
								<li>
									<label class="inline">
										<!-- <input type="checkbox" name="checkboxCoverageLB" id="checkboxCoverageLB"  value="LB_COVERAGE"/> -->
								    	<form:checkbox path="checkboxCoverageLB" id="checkboxCoverageLB" param="LB_COVERAGE"/>
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
								<div id="divCoverageLBLevel" style="display: none;">
									<ul class="form_body">
									<li>
										<div class="ms_container">
											<div class="ms_selectable">
												<label>
													<spring:message	code="Label.AVAILABLEURBANLB" htmlEscape="true"></spring:message>&nbsp;
													<spring:message code="Label.LIST" htmlEscape="true"></spring:message>
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
												<label>
													<spring:message code="Label.CONTRIBUTEURBANLB" htmlEscape="true"></spring:message>&nbsp;
													<spring:message code="Label.LIST" htmlEscape="true"></spring:message>
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
									<li>
										<div class="ms_container">
											<div class="ms_selectable">
												<label>
													<spring:message code="${lblAvailableHeading}"/>
												</label>
												<form:select path="" id="coverageLBAvailableDisttLevel" multiple="multiple"/>
											</div>
											<div class="ms_buttons">
												<input class="bttn" id="btnEventCoverage" type="button" level="DisttLevel" paramLBLR="LB" value="<spring:message code='Button.WHOLE' htmlEscape='true'/>" param="Whole"/>
												<input class="bttn" id="btnEventCoverage" type="button" level="DisttLevel" paramLBLR="LB" value="<spring:message code='Button.BACK' htmlEscape='true'/>"  param="Back"/>
												<input class="bttn" id="btnEventCoverage" type="button" level="DisttLevel" paramLBLR="LB" value="<spring:message code='Button.RESET' htmlEscape='true'/>" param="Reset"/>
												<input class="bttn" id="btnEventCoverage" type="button" level="DisttLevel" paramLBLR="LB" value="<spring:message code='Button.PART' htmlEscape='true'/>"  param="Part"/>
											</div>
											<div class="ms_selection">
												<label>
													<spring:message code="${lblContributingHeading}"/>
												</label>
												<form:select path="${nameElementForContributingLB}" id="coverageLBContributingDisttLevel" multiple="multiple"/>
												<br/>
												<c:if test="${!isDistrictLevel}">
													<input class="bttn" type="button" id="btnFetchCoverageSubDistrict" value="<spring:message htmlEscape="true" code="Button.GETVILLAGEL"/>" 
												     style="width:100%" param="LB_COVERAGE_SUB_DISTRICT" level="SubdisttLevel" paramLBLR="LB"/>
												</c:if>												 
												<br/>
												<form:errors htmlEscape="true" path="${nameElementForContributingLB}" cssClass="error"/>	
											</div>
											<div class="clear"></div>
										</div>
									</li>
									<c:if test="${!isDistrictLevel}">
										<li id="secondLevelLBDiv">
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
								    </c:if>								   
								 </ul>
							</div>
						</div>
						<br/>
							
							<ul class="form_body">
								<li>
									<label class="inline">
										<form:checkbox path="checkboxCoverageUnmapped" id="checkboxCoverageUnmapped" param="UNMAPPED_COVERAGE"/>
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
								<div id="divCoverageLRLevel" style="display: none;">
								
									<ul class="form_body">
										<li >
											<div class="ms_container">
												<div class="ms_selectable">
													<label>
														<spring:message code="${lblAvailableHeading}"/>
													</label>
													<form:select path="" id="coverageLRAvailableUnmappedDisttLevel" multiple="multiple"/>
												</div>
												<div class="ms_buttons">
													<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR" value="<spring:message code='Button.WHOLE' htmlEscape='true'/>" param="Whole"/>
													<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR" value="<spring:message code='Button.BACK' htmlEscape='true'/>" param="Back"/>
													<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR" value="<spring:message code='Button.RESET' htmlEscape='true'/>" param="Reset"/>
													<input class="bttn" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR" value="<spring:message code='Button.PART' htmlEscape='true'/>" param="Part"/>
												</div>
												<div class="ms_selection">
													<label>
														<spring:message code="${lblContributingHeading}"/>
													</label>
													<form:select path="${nameElementForContributingUnmapped}" id="coverageLRContributingUnmappedDisttLevel" multiple="multiple"/>
													<br/>													
													<c:if test="${!isDistrictLevel}">
														<input class="bttn" type="button" id="btnFetchCoverageUnmappedSubDistrict" value="<spring:message htmlEscape="true" code="Button.GETVILLAGEL"/>" 
												        style="width: 100%" param="LR_COVERAGE_UNMAPPED_SUB_DISTRICT" level="UnmappedDisttLevel" paramLBLR="LR"/>
												     </c:if>														 
												  	<form:errors htmlEscape="true" path="${nameElementForContributingUnmapped}" cssClass="error"/>
												</div>
												<div class="clear"></div>
											</div>
										</li>
										
										<c:if test="${!isDistrictLevel}">
											<li id="secondLevelUnmappedLRDiv" >
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
									</c:if>									
									</ul>
								</div>	
							</div>
					</div>
				</div>
				<br/>
				<!-- Block for Covered area of Local Body Ends Here -->
				
				<!-- Block for Government Order Details Started -->	
				<%@include file="../ExistingGovernmentOrder.jsp"%>
				<br/>
				<!-- Block for Government Order Details Ends Here-->	
				<input class="bttn" id="btnFormActionSaveDraft" type="submit" value="<spring:message code='Button.DRAFT' htmlEscape='true'/>" param="DRAFT"/>
				<input class="bttn" id="btnFormActionSave" type="submit" value="<spring:message htmlEscape="true" code="Button.SP"/>" param="PUBLISH"/>
				<input class="bttn" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'"/>
						
			</form:form>	
		</div>
	</div>
	<!-- Page Content ends -->
	<!-- </div> -->
			
</div>
<!-- Main Form Stylings ends -->

</body>
</html>

