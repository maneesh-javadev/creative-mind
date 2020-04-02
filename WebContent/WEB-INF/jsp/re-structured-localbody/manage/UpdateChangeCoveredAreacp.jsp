<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="ChangeCoverageJavascript.jsp"%>
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />

<c:set var="DISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.DISTRICT_CONSTANT.toString()%>"></c:set>
<c:set var="SUBDISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.SUB_DISTRICT_CONSTANT.toString()%>"></c:set>
<c:set var="VILLAGE_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.VILLAGE_CONSTANT.toString()%>"></c:set>
<c:set var="DISTRICT_PANCHAYAT_LEVEL" value= "<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.DISTRICT_PANCHAYAT_LEVEL.toString()%>"></c:set>
<c:set var="INTERMEDIATE_PANCHAYAT_LEVEL" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.INTERMEDIATE_PANCHAYAT_LEVEL.toString()%>"></c:set>
<c:set var="VILLAGE_PANCHAYAT_LEVEL" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.VILLAGE_PANCHAYAT_LEVEL.toString()%>"></c:set>	
</head>
<body class="dt-example">
<section class="content">

   <div class="row">
     <section class="col-lg-12">
     <form:form action="changeCoverageLocalBody.htm" method="post" id="localBodyForm" commandName="localBodyForm" enctype="multipart/form-data" class="form-horizontal">
		<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="changeCoverageLocalBody.htm"/>" />
		<form:hidden path="id" id="paramLBCode"/>
		<form:hidden path="processAction"/>
		<form:hidden path="localBodyCode"/>
		<form:hidden path="localBodyTypeCode"/>
		<form:hidden path="localBodyCreationType"/>
		<form:hidden path="removedLangRegionCodes"/>
		<form:hidden path="isResetedCoverage"/>
          <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.CORRECTGOVTLOCALBODY.CC" htmlEscape="true"></spring:message></h3>
                                </div>
       <div class="box-body">
                 <div id="divCenterAligned" >
							<div class="box-header subheading">
								<h4><spring:message htmlEscape="true"  code="Label.GENERALLOCALGOVTBODYDETAILS"></spring:message></h4></div>
								
									<c:if test="${localBodyForm.isdisturbed}">
										<div class="form-group">
											<label class="col-sm-4 control-label">
												<spring:message code="Label.DISTURBEDSTATE" htmlEscape="true" ></spring:message>
										    </label>
										    <div class="col-sm-6" >
										    		<img src="<%=contextpthval%>/images/red_flg.png" width="13" height="9" />
										    </div>
										</div>
									</c:if>
									<div class="form-group">
										<label class="col-sm-4 control-label">
											<spring:message htmlEscape="true"  code="Label.LOCALBODYNAMEENGLISH"></spring:message>
									    </label>
									    <div class="col-sm-6" >
									    	<c:out value="${localBodyForm.localBodyNameEnglish}" escapeXml="true"></c:out>
									    </div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">
											<spring:message htmlEscape="true" code="Label.LOCALBODYNAMELOCAL"></spring:message>
										</label>
										<div class="col-sm-6" >
											<c:out value="${localBodyForm.localBodyNameLocal}" escapeXml="true"></c:out>
									    </div>
									</div>
									
									<div class="form-group">
										<label class="col-sm-4 control-label">
											<spring:message htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message>
										</label>
										<div class="col-sm-6">
											<c:out value="${localBodyForm.localBodyAliasEnglish}" escapeXml="true"></c:out>
									    </div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">
											<spring:message htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message>
										</label>
										<div class="col-sm-6" >
											<c:out value="${localBodyForm.localBodyAliasLocal}" escapeXml="true"></c:out>
									    </div>
									</div>
									
						</div>	
						
				<!-- Block for Show General Details of Drafted Local Government Body Ends. -->	
					<c:set var="lblHQHeading"></c:set>	
					<c:set var="lblLocalBodyHeading"></c:set>
					<c:set var="HQ_LEVEL_CONSTANT" value="${localBodyTypeLevel}"></c:set>
					<c:choose>
						<c:when test="${localBodyTypeLevel eq  DISTRICT_PANCHAYAT_LEVEL.charAt(0)}">
							<c:set var="lblHQHeading" value="Label.CONTRIBUTDISTRICTLIST"></c:set>
							<c:set var="lblLocalBodyHeading" value="Label.DISTRICTPANCHAYAT"></c:set>
						</c:when>
						<c:when test="${localBodyTypeLevel eq  INTERMEDIATE_PANCHAYAT_LEVEL.charAt(0)}">
							<c:set var="lblHQHeading" value="Label.CONTRIBUTESUBDISTRICTLIST"></c:set>
							<c:set var="lblLocalBodyHeading" value="Label.INTERMEDIATEPANCHAYAT"></c:set>
							<c:set var="HQ_LEVEL_CONSTANT" value="${SUBDISTRICT_CONSTANT.charAt(0)}"></c:set>
						</c:when>
						<c:when test="${localBodyTypeLevel eq  VILLAGE_PANCHAYAT_LEVEL.charAt(0)}">
							<c:set var="lblHQHeading" value="Label.CONTRIBUTVILLAGELIST"></c:set>
							<c:set var="lblLocalBodyHeading" value="Label.VILLAGEPANCHAYAT"></c:set>
						</c:when>
					</c:choose>
					<div >
						<div class="box-header subheading">
							<h4><spring:message htmlEscape="true" code="Label.CORRECTLOCALBODY"></spring:message></h4></div>
							<!-- Div Container For Current Coverred Area of Local Body -->
							
								<!-- Blank heading being used in GUI -->
								
								
									<c:if test="${localBodyTypeLevel eq DISTRICT_PANCHAYAT_LEVEL.charAt(0)}">	
							
											<table id="tblCoverage_${DISTRICT_PANCHAYAT_LEVEL}" class="table table-bordered table-hover" width="50%">
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
										
									</c:if>
									
									<c:if test="${(localBodyTypeLevel eq DISTRICT_PANCHAYAT_LEVEL.charAt(0)) or (localBodyTypeLevel eq  INTERMEDIATE_PANCHAYAT_LEVEL.charAt(0))}">	
								
											<table id="tblCoverage_${INTERMEDIATE_PANCHAYAT_LEVEL}" class="table table-bordered table-hover" width="50%">
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
															<td>
																<img id="remove_${completedCoveragesIM.parentCode}" alt="remove" src="images/delete.png" param="${completedCoveragesIM.landRegionCode}">
															</td>
														</tr>
														</c:if>
													</c:forEach>
												</tbody>
											</table>
									
									</c:if>
									
									<c:if test="${(localBodyTypeLevel eq  DISTRICT_PANCHAYAT_LEVEL.charAt(0)) or 
												  (localBodyTypeLevel eq  INTERMEDIATE_PANCHAYAT_LEVEL.charAt(0)) or 
												  (localBodyTypeLevel eq  VILLAGE_PANCHAYAT_LEVEL.charAt(0))}">		
									
											<table id="tblCoverage_${VILLAGE_PANCHAYAT_LEVEL}" class="table table-bordered table-hover" width="50%">
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
															<td>
																<img id="remove_${completedCoveragesV.parentCode}" alt="remove" src="images/delete.png" param="${completedCoveragesV.landRegionCode}">
															</td>
														</tr>
														</c:if>
													</c:forEach>
												</tbody>
											</table>
										
									</c:if>
							
							<!-- Div Container For Current Coverred Area of Local Body Ends Here -->
						
					</div>
				

<!-- Block for Coverage Details of Drafted Local Government Body. -->
					<div id="divExistingDraftedLBCoverages" >
						<form:hidden path="hidContributingLBCodes"/>
						<form:hidden path="hidContributingLandRegionCodes"/>
						<div class="form_block">
							<div class="box-header subheading">
								<h4>
									<spring:message htmlEscape="true"  code="Label.COVEREDAREAOFNEWLCLBDY"></spring:message>&nbsp;
									
								</h4>
								</div>
								<div class="form-group">
								  <div class="col-sm-6"><button type="button" id="btnResetExistingCoverages" class="btn btn-primary" >RESET COVERAGES</button></div>
								
								</div>
								
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
									
										<c:if test="${localBodyAvailabe}">
											<table class="table table-bordered table-hover" width="100%">
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
										
										</c:if>
									
										<c:if test="${districtAvailable}">
												
											<table class="table table-bordered table-hover" width="100%">
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
										
										</c:if>
										
										<c:if test="${subDistrictAvailable}">
										
											<table class="table table-bordered table-hover" width="100%">
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
										
										</c:if>
										
										<c:if test="${villageAvailable}">
											
											<table class="table table-bordered table-hover" width="100%">
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
									
										</c:if>
					</div>
					<!-- Block for Coverage Details of Drafted Local Government Body Ends. -->
					
					</div>
					
					<div  id="divCompleteLBCoverages"  style="display: none;">
						<div class="box-header subheading">
							<h4><spring:message htmlEscape="true"  code="Label.COVEREDAREAOFNEWLCLBDY"></spring:message></h4></div>
					
								<div class="form-group">
									<label class="col-sm-3 control-label"></label>
										<div class="col-sm-6"> 
										 <form:checkbox path="checkboxCoverageLB" id="checkboxCoverageLB" param="LB_COVERAGE"/>
								    	<spring:message htmlEscape="true" code="Label.SELEXISTINGCOVEREDAREA"></spring:message>
								    	<br/>
								    	<form:errors htmlEscape="true" path="checkboxCoverageLB" cssClass="error"/>	
										
										
										</div>
									
								</div>
						
							<!-- Div Container For Coverage from Existing Local Bodies Elements -->
						
								<!-- Blank heading being used in GUI -->
								
							
								<div id="firstLevelLBDiv" style="display: none;">
								  <div class="ms_container row" style="margin-left: 10px;">
						           <div class="ms_selectable col-sm-5 form-group">
							               <label id="labelAvailablePanchayat">
												<spring:message htmlEscape="true" code="Label.AVAILABLE"/>
												<spring:message htmlEscape="true" code="${lblLocalBodyHeading}"/>
											</label>
											<form:select path="" id="coverageLBAvailablePanchayatLevel" multiple="multiple"/>
							        </div>
										 <div class="ms_buttons col-sm-2"><br><br>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="PanchayatLevel" paramLBLR="LB"  param="Whole"><spring:message code='Button.WHOLE' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="PanchayatLevel" paramLBLR="LB"   param="Back"><spring:message code='Button.BACK' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="PanchayatLevel" paramLBLR="LB"  param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="PanchayatLevel" paramLBLR="LB"  param="Part"><spring:message code='Button.PART' htmlEscape='true'/></button>
										 </div>
								<div class="ms_selection col-sm-5">
								 <div class="form-group">
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
					            </div>
					         </div>
								
						<div id="secondLevelLBDiv" style="display: none;">
									<div class="ms_container row" style="margin-left: 10px;">
										<div class="ms_selectable col-sm-5 form-group">
											<label><spring:message htmlEscape="true" code="Label.AVAILDISTRICTLIST"/></label>
											<form:select path="" id="coverageLBAvailableDisttLevel" multiple="multiple"/>
										</div>
										<div class="ms_buttons col-sm-2">
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="DisttLevel" paramLBLR="LB" param="Whole"><spring:message code='Button.WHOLE' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="DisttLevel" paramLBLR="LB"   param="Back"><spring:message code='Button.BACK' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="DisttLevel" paramLBLR="LB"  param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="DisttLevel" paramLBLR="LB"   param="Part"><spring:message code='Button.PART' htmlEscape='true'/></button>
										</div>
										<div class="ms_selection col-sm-5">
										<div class="form-group">
											<label><spring:message htmlEscape="true" code="Label.CONTRIBUTDISTRICTLIST"/></label>
											<form:select path="contributingLBDistrictCodes" id="coverageLBContributingDisttLevel" multiple="multiple"/>
											<br/>
											<form:errors htmlEscape="true" path="contributingLBDistrictCodes" cssClass="error"/>	
											<input class="bttn" type="button" id="btnFetchCoverageDistrict" value="<spring:message htmlEscape="true" code="Button.GETSUBDISTRICTL"/>" 
												   style="width: 100%;" param="LB_COVERAGE_DISTRICT" level="DisttLevel" paramLBLR="LB"/>										
										</div>
										</div>
									</div>
								</div>
								
								
								
								<div id="thirdLevelLBDiv" style="display: none;">
									<div class="ms_container row" style="margin-left: 10px;">
										<div  class="ms_selectable col-sm-5 form-group">
											<label><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"/></label>
											<form:select path="" id="coverageLBAvailableSubdisttLevel" multiple="multiple"/>
										</div>
										<div class="ms_buttons col-sm-2">
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="SubdisttLevel" paramLBLR="LB"  param="Whole"><spring:message code='Button.WHOLE' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="SubdisttLevel" paramLBLR="LB"   param="Back"><spring:message code='Button.BACK' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="SubdisttLevel" paramLBLR="LB"  param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="SubdisttLevel" paramLBLR="LB"   param="Part"><spring:message code='Button.PART' htmlEscape='true'/></button>
										</div>
										<div class="ms_selection col-sm-5">
										<div class="form-group">
											<label><spring:message htmlEscape="true" code="Label.CONTRIBUTSUBDISTRICTLIST"/></label>
											<form:select path="contributingLBSubDistrictCodes" id="coverageLBContributingSubdisttLevel" multiple="multiple"/>
											<br/>
											<form:errors htmlEscape="true" path="contributingLBSubDistrictCodes" cssClass="error"/>
											<button class="btn btn-info" type="button" id="btnFetchCoverageSubDistrict" 
												   style="width:100%" param="LB_COVERAGE_SUB_DISTRICT" level="SubdisttLevel" paramLBLR="LB"><spring:message htmlEscape="true" code="Button.GETVILLAGEL"/></button>
										</div>
										</div>
									</div>
								</div>
								
								
								<div id="fourthLevelLBDiv" style="display: none;">
									<div class="ms_container row" style="margin-left: 10px;">
										<div class="ms_selectable col-sm-5 form-group">
											<label><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"/></label>
											<form:select path="" id="coverageLBAvailableVillageLevel" multiple="multiple"/>
										</div>
										<div class="ms_buttons col-sm-2">
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB"  param="Whole"><spring:message code='Button.WHOLE' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB"   param="Back"><spring:message code='Button.BACK' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB"  param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB"   param="Part"><spring:message code='Button.PART' htmlEscape='true'/></button>
										</div>
										<div class="ms_selection col-sm-5">
										<div class="form-group">
											<label><spring:message htmlEscape="true"  code="Label.CONTRIBUTVILLAGELIST"/></label>
											<form:select path="contributingLBVillageCodes" id="coverageLBContributingVillageLevel" multiple="multiple"/>
											<br/>
											<form:errors htmlEscape="true" path="contributingLBVillageCodes" cssClass="error"/>
										</div>
										</div>
									</div>
								</div>
								
						
							<br/>
							
							
								<div class="form-group">
									<label class="col-sm-3 control-label"></label>
									<div class="col-sm-6">
									     <form:checkbox path="checkboxCoverageUnmapped" id="checkboxCoverageUnmapped" param="UNMAPPED_COVERAGE"/>
										<spring:message htmlEscape="true" code="Label.SELCONTRIBUTEUNMAPPEDPARTIALLY"/>
										<br/>
										<form:errors htmlEscape="true" path="checkboxCoverageUnmapped" cssClass="error"/>
									</div>
								</div>
							
							
							<!-- Div Container for coverage from Unmapped / Partially Mapped Land Regeion Elements -->
							
									<div id="firstLevelUnmappedLRDiv" style="display: none;">
										<div class="ms_container row" style="margin-left: 10px;">
											<div class="ms_selectable col-sm-5 form-group">
												<label><spring:message htmlEscape="true"  code="Label.AVAILDISTRICTLIST"/></label>
												<form:select path="" id="coverageLRAvailableUnmappedDisttLevel" multiple="multiple"/>
											</div>
											<div class="ms_buttons col-sm-2">
												<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR" param="Whole"><spring:message code='Button.WHOLE' htmlEscape='true'/></button>
												<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR"  param="Back"><spring:message code='Button.BACK' htmlEscape='true'/></button>
												<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR"  param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/></button>
												<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR"  param="Part"><spring:message code='Button.PART' htmlEscape='true'/></button>
											</div>
											<div class="ms_selection col-sm-5">
										<div class="form-group">
												<label><spring:message htmlEscape="true" code="Label.CONTRIBUTDISTRICTLIST"/></label>
												<form:select path="contributingUnmappedDistrictCodes" id="coverageLRContributingUnmappedDisttLevel" multiple="multiple"/>
												<br/>
												<form:errors htmlEscape="true" path="contributingUnmappedDistrictCodes" cssClass="error"/>
												<button class="btn btn-info" type="button" id="btnFetchCoverageUnmappedDistrict" 
												       style="width: 100%" param="LB_COVERAGE_UNMAPPED_DISTRICT" level="UnmappedDisttLevel" paramLBLR="LR"><spring:message htmlEscape="true" code="Button.GETSUBDISTRICTL"/></button>
											</div>
											</div>
										</div>
									</div>
									
									
									<div id="secondLevelUnmappedLRDiv" style="display: none;">
										<div class="ms_container row" style="margin-left: 10px;">
											<div class="ms_selectable col-sm-5 form-group">
												<label><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"/></label>
												<form:select path="" id="coverageLRAvailableUnmappedIntermediateLevel" multiple="multiple"/>
											</div>
											<div class="ms_buttons col-sm-2">
												<button class="bttn" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR"  param="Whole"><spring:message code='Button.WHOLE' htmlEscape='true'/></button>
												<button class="bttn" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR"   param="Back"><spring:message code='Button.BACK' htmlEscape='true'/></button>
												<button class="bttn" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR"  param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/></button>
												<button class="bttn" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR"  param="Part"><spring:message code='Button.PART' htmlEscape='true'/></button>
											</div>
									<div class="ms_selection col-sm-5">
										<div class="form-group">
												<label><spring:message htmlEscape="true" code="Label.CONTRIBUTSUBDISTRICTLIST"/></label>
												<form:select path="contributingUnmappedSubDistrictCodes" id="coverageLRContributingUnmappedIntermediateLevel" multiple="multiple"/>											
												<br/>
												<form:errors htmlEscape="true" path="contributingUnmappedSubDistrictCodes" cssClass="error"/>
												<input class="btn btn-primary" type="button" id="btnFetchCoverageUnmappedSubDistrict" value="<spring:message htmlEscape="true" code="Button.GETVILLAGEL"/>" 
												       style="width: 100%" param="LB_COVERAGE_UNMAPPED_SUB_DISTRICT" level="UnmappedIntermediateLevel" paramLBLR="LR"/>
											</div>
											</div>
										</div>
									</div>
									
									<div id="thirdLevelUnmappedLRDiv" style="display: none;">
										<div class="ms_container row" style="margin-left: 10px;">
											<div class="ms_selectable col-sm-5 form-group">
												<label><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"/></label>
												<form:select path="" id="coverageLRAvailableUnmappedVillageLevel" multiple="multiple"/>
											</div>
											<div class="ms_buttons col-sm-2">
												<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR"  param="Whole"><spring:message code='Button.WHOLE' htmlEscape='true'/></button>
												<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR"  param="Back"><spring:message code='Button.BACK' htmlEscape='true'/></button>
												<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR"  param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/></button>
												<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR"   param="Part"><spring:message code='Button.PART' htmlEscape='true'/></button>
											</div>
									  <div class="ms_selection col-sm-5">
										<div class="form-group">
												<label><spring:message htmlEscape="true" code="Label.CONTRIBUTVILLAGELIST"/></label>
												<form:select path="contributingUnmappedVillageCodes" id="coverageLRContributingUnmappedVillageLevel" multiple="multiple"/>
												<br/>
												<form:errors htmlEscape="true" path="contributingUnmappedVillageCodes" cssClass="error"/>											
											</div>
											</div>
										</div>
								  </div>
						
					
					</div>
				</div>
					<!-- Block for Covered area of Local Body Ends Here -->
						<!-- Block for HeadQuarters Started -->							
					<div >
						
							<div class="form-group">
							<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.HeaquarterFrame"></spring:message>
								<span class="mandate">*</span></label>
							  <div class="col-sm-6 ">
							  <button type="button" class="btn btn-primary" id="actionBuildHeadQuarter" ><spring:message code='Label.selectHQ' htmlEscape='true'/></button></div>
							   
							</div>
									
									<table id="tblHeadquarter" class="table table-bordered table-hover" width="50%">
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
														<c:when test="${completedCoveragesHQ.landRegionType.charAt(0) eq  HQ_LEVEL_CONSTANT}">
															<td>
																<form:radiobutton path="headQuarterCode" value="${completedCoveragesHQ.landRegionCode}"/>
															</td>
															<td><c:out value="${completedCoveragesHQ.landRegionNameEnglish}"></c:out></td>
														</c:when>
													</c:choose>
												</tr>
											</c:forEach>
											<c:forEach var="draftedLBCoveragesHQ" items="${draftedLBCoverages}">
												<c:if test="${draftedLBCoveragesHQ.lbLrType.charAt(0) eq HQ_LEVEL_CONSTANT}">
													<tr id="hqTR_${draftedLBCoveragesHQ.lbLrCode}">
														<td>
															<form:radiobutton path="headQuarterCode" value="${draftedLBCoveragesHQ.lbLrCode}"/>
														</td>
														<td><c:out value="${draftedLBCoveragesHQ.lbLrNameEnglish}"></c:out></td>
													</tr>
												</c:if>
											</c:forEach>
										</tbody>
									</table>
									<br/>
									<input type="hidden" id="showHQError" name="showHQError"/><!-- Used Element, Do not remove. -->
									<form:errors htmlEscape="true" path="headQuarterCode" cssClass="error"/>
							
						
					</div>
				
					<!-- Block for HeadQuarters Ends Here-->
				<!-- Block for Government Order Details Started -->
					<%@include file="../ExistingGovernmentOrdercp.jsp"%>
								
						
		</div>
						
		<div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                  <button class="btn btn-success" id="btnFormActionSaveDraft" type="submit" param="DRAFT"><spring:message code='Button.DRAFT' htmlEscape='true'/></button>
				  <button class="btn btn-success" id="btnFormActionSave" type="submit"  param="PUBLISH"><spring:message htmlEscape="true" code="Button.SP"/></button>
				  <button class="btn btn-danger" type="button" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'"><spring:message htmlEscape="true" code="Button.CLOSE"/></button>
					
                 </div>
           </div>   
         </div> 

       
     </div>   
             
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script>

</body>
</html>