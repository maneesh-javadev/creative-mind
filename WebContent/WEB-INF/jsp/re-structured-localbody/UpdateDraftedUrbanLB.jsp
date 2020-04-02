
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="UrbanLBJavascript.jsp"%>
<%@include file="../common/dwr.jsp"%>
<c:set var="LB_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.LOCAL_BODY_CONSTANT.toString()%>"></c:set>
<c:set var="DISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.DISTRICT_CONSTANT.toString()%>"></c:set>
<c:set var="SUBDISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.SUB_DISTRICT_CONSTANT.toString()%>"></c:set>
<c:set var="VILLAGE_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.VILLAGE_CONSTANT.toString()%>"></c:set>
<title><spring:message htmlEscape="true"  code="Label.CREATENEWLOCALGOVTBODY"></spring:message></title>
</head>
<body>
	<!-- Main Form Stylings starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message htmlEscape="true" code="Label.modifydraftedlb"></spring:message></h3>
			<ul class="item_list">
				<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form action="buildLocalBody.htm" method="post" id="localBodyForm" commandName="localBodyForm" enctype="multipart/form-data">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="buildLocalBody.htm"/>" />
					<form:hidden path="id" id="draftTempId"/>
					<form:hidden path="processAction"/>
					<form:hidden path="localBodyCreationType"/>
					<form:hidden path="isResetedCoverage"/>
					
					<!-- Set Page Level Heading and element Id(s) Started-->
					<c:set var="lblAvailableHeading" value="Label.AVAILSUBDISTRICTLIST"></c:set>
					<c:set var="lblContributingHeading" value="Label.CONTRIBUTSUBDISTRICTLIST"></c:set>
					<c:set var="nameElementForContributingLB" value="contributingLBSubDistrictCodes"></c:set>
					<c:set var="nameElementForContributingUnmapped" value="contributingUnmappedSubDistrictCodes"></c:set>
					<c:if test="${isDistrictLevel}">
						<c:set var="lblAvailableLBHeading" value="Label.AVAILDISTRICTLIST"></c:set>
						<c:set var="lblContributingLBHeading" value="Label.CONTRIBUTDISTRICTLIST"></c:set>
						<c:set var="nameElementForContributingLB" value="contributingLBDistrictCodes"></c:set>
						<c:set var="nameElementForContributingUnmapped" value="contributingUnmappedDistrictCodes"></c:set>
					</c:if> 
					<!-- Set Page Level Heading and element Id(s) Ends Here-->
							
					<!-- General Details of Local Body Started-->
					<div class="form_block">
						<div class="col_1">
							<h4><spring:message htmlEscape="true"  code="Label.GENERALDETAILNEWLOCALBODY"></spring:message></h4>
							<ul class="form_body">
								<li>
									<label>
										<spring:message htmlEscape="true"  code="Label.LOCALBODYNAMEENGLISH"></spring:message>
										<span class="mandate">*</span>
									</label>
									<form:input path="localBodyNameEnglish" id="localBodyNameEnglish" maxlength="200" htmlEscape="true"/>
									<span class="errormessage" id="errLBNameEnglish"></span>
									<br/>
									<form:errors htmlEscape="true" path="localBodyNameEnglish" cssClass="error"/>		
								</li>
								<li>
									<label>
										<spring:message htmlEscape="true" code="Label.LOCALBODYNAMELOCAL"></spring:message>
									</label>
									<form:input path="localBodyNameLocal" id="localBodyNameLocal" maxlength="100" htmlEscape="true"/>	
									<br/>
									<form:errors htmlEscape="true" path="localBodyNameLocal" cssClass="error"/>									
								</li>
								<li>
									<label>
										<spring:message htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message>
									</label>
									<form:input path="localBodyAliasEnglish" id="localBodyAliasEnglish" maxlength="50" htmlEscape="true"/>	
									<br/>
									<form:errors htmlEscape="true" path="localBodyAliasEnglish" cssClass="error"/>						
								</li>
								<li>
									<label>
										<spring:message htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message>
									</label>
									<form:input path="localBodyAliasLocal" id="localBodyAliasLocal" maxlength="50" htmlEscape="true"/>
									<br/>
									<form:errors htmlEscape="true" path="localBodyAliasLocal" cssClass="error"/>							
								</li>
								<li>
									<label>
										<spring:message htmlEscape="true" code="Label.STATESPECIFICCODE"></spring:message>
									</label>
									<form:input path="stateSpecificCode" id="stateSpecificCode" maxlength="7" htmlEscape="true"/>
									<br/>
									<form:errors htmlEscape="true" path="stateSpecificCode" cssClass="error"/>								
								</li>
							</ul>
						</div>
					</div>
					<br/>
					<!-- General Details of Local Body Ends Here-->
						
					<!-- Block for Dynamic Hierarchy of Local Body Type Started-->	
					<div class="form_block">
						<div class="col_1">
							<h4><spring:message htmlEscape="true" code="Label.SELECTEDTYPELOCALBODY"></spring:message></h4>
							<ul class="form_body">
								<li>
									<label>
										<spring:message code='Label.SELECTLOCALBODYTYPE' htmlEscape='true'/>
										<span class="mandate">*</span>
									</label>
									<form:select path="localBodyTypeCode" id="localBodyType">
										<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT" ></spring:message></form:option>
										<c:forEach items="${localBodyTypeList}" var="objLBTypes">
											<form:option value="${objLBTypes.localBodyTypeCode}" paramTierSetup="${objLBTypes.tierSetupCode}">
												<c:out value="${objLBTypes.name}" escapeXml="true"></c:out>
											</form:option>
										</c:forEach>
									</form:select>
									<span class="errormessage" id="errorLocalBodyType"></span>
									<form:errors htmlEscape="true" path="localBodyTypeCode" cssClass="error"/>
								</li>
							</ul>
						</div>
					</div>
					<br/>
					<!-- Block for Dynamic Hierarchy of Local Body Type Ends Here-->
					
					
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
											<ul class="form_body">
												<li>
													<!-- Blank heading being used in GUI -->
													<h4></h4>
												</li>
											</ul>	
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
					</div>
					<br/>
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
												<form:errors htmlEscape="true" path="${nameElementForContributingLB}" cssClass="error"/>	
											</div>
											<div class="clear"></div>
										</div>
									</li>
									<c:if test="${!isDistrictLevel}">
										<li>
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
									</c:if>									
									</ul>
								</div>	
							</div>
					</div>
				</div>
				<br/>
				<!-- Block for Covered area of Local Body Ends Here -->
				
				<!-- Block for GIS Nodes Started -->
				<div class="form_block">
					<div class="col_1">
						<h4><spring:message code="Label.GISNODES" htmlEscape="true"></spring:message></h4>
						<ul class="form_body">
							<li>
								<div class="long_latitude" >
									
									<div class="row">
										<div class="col"><label><spring:message code='Label.longituderange' htmlEscape='true'/></label></div>
										<div class="col"><label><spring:message code='Label.latituderange' htmlEscape='true'/></label></div>
									</div>
									<div class="row"> <!-- Max of 4 divs can be used inside this row div -->
										<div class="col"><form:input path="longitude" id="longitude" maxlength="7"/></div>
										<div class="col"><form:input path="latitude" id="latitude" maxlength="7"/></div>
										<div class="col"><input type="button" class="bttn addmore" id="btnAddLatLong" value="Add More"/></div>
									</div>
									<div id="divLatitudeLongitude">
										<!-- Adding Dynamic Rows for Latitude and Longitude -->
									</div>
									<br/>
									<form:errors htmlEscape="true" path="longitude" cssClass="error"/>
									<form:errors htmlEscape="true" path="latitude" cssClass="error"/> 
								</div>
							</li>
							<li>
								<div id="divMapUpload" style="display: none;">
									<c:if test="${modifyProcess or checkedServerValidation}">
										<c:set var="mapfileName" value=""/>
										<c:if test="${not empty localBodyForm.mapUploadPath}">
											<c:set var="mapSubstrng" value="${fn:substring(localBodyForm.mapUploadPath, fn:indexOf(localBodyForm.mapUploadPath, '_'), fn:indexOf(localBodyForm.mapUploadPath, '.'))}" />
											<c:set var="mapfileName" value="${fn:replace(localBodyForm.mapUploadPath, mapSubstrng, '')}" />
											<a id="attachedUploadedMapFile" href="downloadLBMap.htm?filename=${localBodyForm.mapUploadPath}&<csrf:token uri='downloadLBMap.htm'/>">
												<c:out value="${mapfileName}"/>
											</a>
										</c:if>
										<%-- <a href="downloadLBMap.htm?filename=${localBodyForm.mapUploadPath}&<csrf:token uri='downloadLBMap.htm'/>"><c:out value="${localBodyForm.mapUploadPath}"/></a> --%>
										<form:hidden path="mapUploadPath"/>
									</c:if>
									<label>	
										<spring:message code="Label.UPLOADMAP" htmlEscape="true"/>
										<spring:message code='Label.allowedfileexe' htmlEscape='true'/>
									</label>
									<form:input path="mapUpload" id="mapUpload" type="file"/>
									<form:errors htmlEscape="true" path="mapUpload" cssClass="error"/>
								</div>		
							</li>	
						</ul>
					</div>
				</div>
				<br/>
				<!-- Block for GIS Nodes Ends Here -->
							
						
				<!-- Block for Government Order Details Started -->	
				<%@include file="ExistingGovernmentOrder.jsp"%>
				<br/>
				<!-- Block for Government Order Details Ends Here-->	
				<input class="bttn" id="btnFormActionSaveDraft" type="submit" value="<spring:message code='Button.DRAFT' htmlEscape='true'/>" param="DRAFT"/>
				<input class="bttn" id="btnFormActionSave" type="submit" value="<spring:message htmlEscape="true"  code="Button.SP"/>" param="PUBLISH"/>
				<input class="bttn" id="btnActionClose" type="button" value="<spring:message htmlEscape="true"  code="Button.CLOSE"/>" param="home"/>
						
			</form:form>	
		</div>
	</div>
	<!-- Page Content ends -->
	<!-- </div> -->
			
</div>
<!-- Main Form Stylings ends -->

</body>
</html>

