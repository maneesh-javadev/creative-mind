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
	 <section class="content">
	<div class="row">
          <!-- main col -->
          <section class="col-lg-12">

          <div class="box">
		      <div class="box-header with-border">
		       <h3><spring:message code="Label.CORRECTGOVTLOCALBODY.CC" htmlEscape="true"></spring:message></h3>
		      </div><!-- /.box-header -->
	
				<form:form class="form-horizontal" action="changeCoverageLocalBody.htm" method="post" id="localBodyForm" commandName="localBodyForm" enctype="multipart/form-data">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="changeCoverageLocalBody.htm"/>" />
					<form:hidden path="processAction"/>
					<form:hidden path="localBodyCreationType"/>
					<form:hidden path="localBodyCode" id="paramLBCode"/>
					<form:hidden path="localBodyTypeCode"/>
					<form:hidden path="removedLangRegionCodes"/>
					
					<!-- Set Page Level Heading and element Id(s) Started-->
					<c:set var="lblHeadingCurrentCoverageSubDis" value="Current Covered Sub-Districts"></c:set>
					<c:set var="lblColumnHeadNameSubDis" value="Label.SUBDISTRICTNAMEENGLISH"></c:set>
					<c:set var="lblColumnHeadCodeSubDis" value="Label.SUBDISTRICTCODE"></c:set>
					<c:set var="selectedLevelSubDis" value="${SUBDISTRICT_CONSTANT}"></c:set>
					<c:set var="lblAvailableHeadingSubDis" value="Label.AVAILSUBDISTRICTLIST"></c:set>
					<c:set var="lblContributingHeadingSubDis" value="Label.CONTRIBUTSUBDISTRICTLIST"></c:set>
					<c:set var="nameElementForContributingLBSubDis" value="contributingLBSubDistrictCodes"></c:set>
					<c:set var="nameElementForContributingUnmappedSubDis" value="contributingUnmappedSubDistrictCodes"></c:set>
					<%-- <c:if test="${isDistrictLevel}"> --%>
						<c:set var="lblHeadingCurrentCoverage" value="Current Covered Districts"></c:set>
						<c:set var="lblColumnHeadName" value="Label.DISTRICTNAMEINENGLISH"></c:set>
						<c:set var="lblColumnHeadCode" value="Label.DISTRICTCODE"></c:set>
						<c:set var="selectedLevel" value="${DISTRICT_CONSTANT}"></c:set>
						<c:set var="lblAvailableHeading" value="Label.AVAILDISTRICTLIST"></c:set>
						<c:set var="lblContributingHeading" value="Label.CONTRIBUTDISTRICTLIST"></c:set>
						<c:set var="nameElementForContributingLB" value="contributingLBDistrictCodes"></c:set>
						<c:set var="nameElementForContributingUnmapped" value="contributingUnmappedDistrictCodes"></c:set>
					<%-- </c:if> --%> 
					<!-- Set Page Level Heading and element Id(s) Ends Here-->
					
					<!-- Block for Show General Details of Drafted Local Government Body. -->
					
				<c:if test="${localBodyForm.isdisturbed}">	
				<div class="form-group">
					  <label  class="col-sm-3 control-label" for="sel1"><spring:message code="Label.DISTURBEDSTATE" htmlEscape="true" ></spring:message></label>
					  <div class="col-sm-6">
							 <img src="<%=contextpthval%>/images/red_flg.png" width="13" height="9" />
					  </div>
				</div>
				</c:if>
				<div class="form-group">
					  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true"  code="Label.LOCALBODYNAMEENGLISH"></spring:message></label>
					  <div class="col-sm-6">
							<c:out value="${localBodyForm.localBodyNameEnglish}" escapeXml="true"></c:out>
					  </div>
				</div>
				<div class="form-group">
					  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.LOCALBODYNAMELOCAL"></spring:message></label>
					  <div class="col-sm-6">
							<c:out value="${localBodyForm.localBodyNameLocal}" escapeXml="true"></c:out>
					  </div>
				</div>
				<div class="form-group">
					  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message></label>
					  <div class="col-sm-6">
							<c:out value="${localBodyForm.localBodyAliasEnglish}" escapeXml="true"></c:out>
					  </div>
				</div>
				<div class="form-group">
					  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message></label>
					  <div class="col-sm-6">
							<c:out value="${localBodyForm.localBodyAliasLocal}" escapeXml="true"></c:out>
					  </div>
				</div>				
									
					
					<!-- Block for Show General Details of Drafted Local Government Body Ends. -->	
					<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message htmlEscape="true" code="Label.CORRECTLOCALBODY"></spring:message></h4>
               	    </div><!-- /.box-header -->
					
					<div class="form-group-sm-8" style="padding-left: 30px;">
				
							<table id="tblCoverage_${selectedLevel}" class="data_grid" width="50%" >
											<thead>
												<tr>
													<td colspan="3"><strong><spring:message htmlEscape="true" code="${lblHeadingCurrentCoverage}"></spring:message></strong></td>
												</tr>
												<tr>
													<th width="10%"><spring:message  code="${lblColumnHeadCode}"></spring:message></th>
													<th width="80%"><spring:message  code="${lblColumnHeadName}"></spring:message></th>
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
							
							
							<table id="tblCoverage_${selectedLevelSubDis}" class="data_grid" width="50%" >
											<thead>
												<tr>
													<td colspan="3"><strong><spring:message htmlEscape="true" code="${lblHeadingCurrentCoverageSubDis}"></spring:message></strong></td>
												</tr>
												<tr>
													<th width="10%"><spring:message  code="${lblColumnHeadCodeSubDis}"></spring:message></th>
													<th width="80%"><spring:message  code="${lblColumnHeadNameSubDis}"></spring:message></th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${completedCoverageDetails}" var="completedCoverages">
													<c:if test="${completedCoverages.landRegionType eq  selectedLevelSubDis}">
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
							
							<%-- <c:if test="${!isDistrictLevel}"> --%>
							<table id="tblCoverage_V" class="data_grid" width="50%">
											<thead>
												<tr>
													<td colspan="3"><strong><spring:message htmlEscape="true" text="Current Covered Villages"></spring:message></strong></td>
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
							<%-- </c:if> --%>
						</div>
		

					<br/>	
							
					<!-- This Block used for Covered area of Local Body Started -->	
					<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message htmlEscape="true" code="Label.COVEREDAREAOFNEWLCLBDY"></spring:message></h4>
               	    </div><!-- /.box-header -->
               	    
               	    <div class="col-sm-12" style="padding-left: 30px;">
               	    	<form:checkbox path="checkboxCoverageLB" id="checkboxCoverageLB" param="LB_COVERAGE"/>
						<spring:message htmlEscape="true" code="Label.SELEXISTINGCOVEREDAREA"></spring:message>
						<br/>
						<form:errors htmlEscape="true" path="checkboxCoverageLB" cssClass="error"/>
					
					</div>
               	    
				
							<!-- Div Container For Coverage from Existing Local Bodies Elements -->
						
								<!-- Blank heading being used in GUI -->
								
								<div id="divCoverageLBLevel" class="box-body dept_list_button" style="display: none;">
								
								
								<div class="ms_container row"  >
									<div class="ms_selectable col-sm-5 form-group">
										<label>
													<spring:message	code="Label.AVAILABLEURBANLB" htmlEscape="true"></spring:message>&nbsp;
													<spring:message code="Label.LIST" htmlEscape="true"></spring:message>
										</label>
										<form:select path="" id="coverageLBAvailablePanchayatLevel" multiple="multiple" class="form-control"/>
									</div>
									<div class="ms_buttons col-sm-2"><br>
											<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="PanchayatLevel" paramLBLR="LB" value="<spring:message code='Button.WHOLE' htmlEscape='true'/>" param="Whole"/>
											<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="PanchayatLevel" paramLBLR="LB" value="<spring:message code='Button.BACK' htmlEscape='true'/>"  param="Back"/>
											<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="PanchayatLevel" paramLBLR="LB" value="<spring:message code='Button.RESET' htmlEscape='true'/>" param="Reset"/>
											<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="PanchayatLevel" paramLBLR="LB" value="<spring:message code='Button.PART' htmlEscape='true'/>"  param="Part"/>
									</div>
									<div class="ms_selection col-sm-5">
										<div class="form-group">
											<label>
												<spring:message code="Label.CONTRIBUTEURBANLB" htmlEscape="true"></spring:message>&nbsp;
													<spring:message code="Label.LIST" htmlEscape="true"></spring:message>
											</label>
											<form:select path="contributingLBCodes" id="coverageLBContributingPanchayatLevel" multiple="multiple" class="form-control"/>
											<br/>
								    		<form:errors htmlEscape="true" path="contributingLBCodes" cssClass="error"/>	
											<input class="btn btn-primary" type="button" id="fetchCoverageofLocalBody" value="<spring:message htmlEscape="true" code="Button.GETCOVEREDAREAOFLOCALBODY"/>" 
												   style="width:100%;" level="PanchayatLevel" paramLBLR="LB"/>	
										</div>
									</div>
								</div>	
								
								
								
								
								
								
								
								
								
								<div class="ms_container row"  >
									<div class="ms_selectable col-sm-5 form-group">
										<label><spring:message code="${lblAvailableHeading}"/></label>
										<form:select path="" id="coverageLBAvailableDisttLevel" multiple="multiple" class="form-control"/>
									</div>
									<div class="ms_buttons col-sm-2"><br>
										<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="DisttLevel" paramLBLR="LB" value="<spring:message code='Button.WHOLE' htmlEscape='true'/>" param="Whole"/>
										<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="DisttLevel" paramLBLR="LB" value="<spring:message code='Button.BACK' htmlEscape='true'/>"  param="Back"/>
										<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="DisttLevel" paramLBLR="LB" value="<spring:message code='Button.RESET' htmlEscape='true'/>" param="Reset"/>
										<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="DisttLevel" paramLBLR="LB" value="<spring:message code='Button.PART' htmlEscape='true'/>"  param="Part"/>
									</div>
									<div class="ms_selection col-sm-5">
										<div class="form-group">
											<label><spring:message code="${lblContributingHeading}"/></label>
													<form:select path="${nameElementForContributingLB}" id="coverageLBContributingDisttLevel" multiple="multiple" class="form-control"/>
													<br/>
<%-- 													<c:if test="${!isDistrictLevel}">	 --%>
													<input class="btn btn-primary" type="button" id="btnFetchCoverageSubDistrict" value="<spring:message htmlEscape="true" code="Button.GETSUBDISTRICTL"/>" 
												     style="width:100%" param="LB_COVERAGE_DISTRICT" level="DisttLevel" paramLBLR="LB"/>
<%-- 												     </c:if> --%>
												     <form:errors htmlEscape="true" path="${nameElementForContributingLB}" cssClass="error"/>
										</div>
									</div>
								</div>
								
								
								<div class="ms_container row"  >
									<div class="ms_selectable col-sm-5 form-group">
										<label><spring:message code="${lblAvailableHeadingSubDis}"/></label>
										<form:select path="" id="coverageLBAvailableSubDisttLevel" multiple="multiple" class="form-control"/>
									</div>
									<div class="ms_buttons col-sm-2"><br>
										<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="SubDisttLevel" paramLBLR="LB" value="<spring:message code='Button.WHOLE' htmlEscape='true'/>" param="Whole"/>
										<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="SubDisttLevel" paramLBLR="LB" value="<spring:message code='Button.BACK' htmlEscape='true'/>"  param="Back"/>
										<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="SubDisttLevel" paramLBLR="LB" value="<spring:message code='Button.RESET' htmlEscape='true'/>" param="Reset"/>
										<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="SubDisttLevel" paramLBLR="LB" value="<spring:message code='Button.PART' htmlEscape='true'/>"  param="Part"/>
									</div>
									<div class="ms_selection col-sm-5">
										<div class="form-group">
											<label><spring:message code="${lblContributingHeadingSubDis}"/></label>
													<form:select path="${nameElementForContributingLBSubDis}" id="coverageLBContributingSubDisttLevel" multiple="multiple" class="form-control"/>
													<br/>
<%-- 													<c:if test="${!isDistrictLevel}">	 --%>
													<input class="btn btn-primary" type="button" id="btnFetchCoverageSubDistrict" value="<spring:message htmlEscape="true" code="Button.GETVILLAGEL"/>" 
												     style="width:100%" param="LB_COVERAGE_SUB_DISTRICT" level="SubdisttLevel" paramLBLR="LB"/>
<%-- 												     </c:if> --%>
												     <form:errors htmlEscape="true" path="${nameElementForContributingLBSubDis}" cssClass="error"/>
										</div>
									</div>
								</div>
									
								
							
<%-- 									<c:if test="${!isDistrictLevel}"> --%>
									<div class="ms_container row"  >
										<div class="ms_selectable col-sm-5 form-group">
											<label><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"/></label>
											<form:select path="" id="coverageLBAvailableVillageLevel" multiple="multiple" class="form-control"/>
										</div>
										<div class="ms_buttons col-sm-2"><br>
											<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB" value="<spring:message code='Button.WHOLE' htmlEscape='true'/>" param="Whole"/>
											<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB" value="<spring:message code='Button.BACK' htmlEscape='true'/>"  param="Back"/>
											<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB" value="<spring:message code='Button.RESET' htmlEscape='true'/>" param="Reset"/>
											<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB" value="<spring:message code='Button.PART' htmlEscape='true'/>"  param="Part"/>
										</div>
										<div class="ms_selection col-sm-5">
											<div class="form-group">
												<label><spring:message htmlEscape="true"  code="Label.CONTRIBUTVILLAGELIST"/></label>
														<form:select path="contributingLBVillageCodes" id="coverageLBContributingVillageLevel" multiple="multiple" class="form-control"/>
														<br/>
														<form:errors htmlEscape="true" path="contributingLBVillageCodes" cssClass="error"/>
											</div>
										</div>
									</div>	
									
<%-- 								   </c:if>								    --%>
								
							</div>
						
						<br/>
							
							<div class="col-sm-12" style="padding-left: 30px;">
		               	    	<form:checkbox path="checkboxCoverageUnmapped" id="checkboxCoverageUnmapped" param="UNMAPPED_COVERAGE"/>
								<spring:message htmlEscape="true" code="Label.SELCONTRIBUTEUNMAPPEDPARTIALLY"/>
								<br/>
								<form:errors htmlEscape="true" path="checkboxCoverageUnmapped" cssClass="error"/>
							
							</div>
							
							
							
							<!-- Div Container for coverage from Unmapped / Partially Mapped Land Regeion Elements -->
							
								<!-- Blank heading being used in GUI -->
								
								<div id="divCoverageLRLevel" class="box-body dept_list_button" style="display: none;">
								
								
									<div class="ms_container row"  >
										<div class="ms_selectable col-sm-5 form-group">
											<label><spring:message code="${lblAvailableHeading}"/></label>
											<form:select path="" id="coverageLRAvailableUnmappedDisttLevel" multiple="multiple" class="form-control"/>
										</div>
										<div class="ms_buttons col-sm-2"><br>
											<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR" value="<spring:message code='Button.WHOLE' htmlEscape='true'/>" param="Whole"/>
											<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR" value="<spring:message code='Button.BACK' htmlEscape='true'/>" param="Back"/>
											<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR" value="<spring:message code='Button.RESET' htmlEscape='true'/>" param="Reset"/>
											<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR" value="<spring:message code='Button.PART' htmlEscape='true'/>" param="Part"/>
										</div>
										<div class="ms_selection col-sm-5">
											<div class="form-group">
												<label><spring:message code="${lblContributingHeading}"/></label>
														<form:select path="${nameElementForContributingUnmapped}" id="coverageLRContributingUnmappedDisttLevel" multiple="multiple" class="form-control"/>
														<br/>
<%-- 														<c:if test="${!isDistrictLevel}"> --%>
														<input class="btn btn-primary" type="button" id="btnFetchCoverageUnmappedSubDistrict" value="<spring:message htmlEscape="true" code="Button.GETSUBDISTRICTL"/>" 
												        style="width: 100%" param="LR_COVERAGE_UNMAPPED_DISTRICT" level="UnmappedDisttLevel" paramLBLR="LR"/>
<%-- 												    	</c:if>	 --%>
														<form:errors htmlEscape="true" path="${nameElementForContributingUnmapped}" cssClass="error"/>
														
											</div>
										</div>
									</div>	
									
									
									<div class="ms_container row"  >
										<div class="ms_selectable col-sm-5 form-group">
											<label><spring:message code="${lblAvailableHeadingSubDis}"/></label>
											<form:select path="" id="coverageLRAvailableUnmappedSubDisttLevel" multiple="multiple" class="form-control"/>
										</div>
										<div class="ms_buttons col-sm-2"><br>
											<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedSubDisttLevel" paramLBLR="LR" value="<spring:message code='Button.WHOLE' htmlEscape='true'/>" param="Whole"/>
											<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedSubDisttLevel" paramLBLR="LR" value="<spring:message code='Button.BACK' htmlEscape='true'/>" param="Back"/>
											<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedSubDisttLevel" paramLBLR="LR" value="<spring:message code='Button.RESET' htmlEscape='true'/>" param="Reset"/>
											<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedSubDisttLevel" paramLBLR="LR" value="<spring:message code='Button.PART' htmlEscape='true'/>" param="Part"/>
										</div>
										<div class="ms_selection col-sm-5">
											<div class="form-group">
												<label><spring:message code="${lblContributingHeadingSubDis}"/></label>
														<form:select path="${nameElementForContributingUnmappedSubDis}" id="coverageLRContributingUnmappedSubDisttLevel" multiple="multiple" class="form-control"/>
														<br/>
<%-- 														<c:if test="${!isDistrictLevel}"> --%>
														<input class="btn btn-primary" type="button" id="btnFetchCoverageUnmappedSubDistrict" value="<spring:message htmlEscape="true" code="Button.GETVILLAGEL"/>" 
												        style="width: 100%" param="LR_COVERAGE_UNMAPPED_SUB_DISTRICT" level="UnmappedSubDisttLevel" paramLBLR="LR"/>
<%-- 												    	</c:if>	 --%>
														<form:errors htmlEscape="true" path="${nameElementForContributingUnmappedSubDis}" cssClass="error"/>
														
											</div>
										</div>
									</div>	
									
									
								
									
<%-- 										<c:if test="${!isDistrictLevel}"> --%>
											<div class="ms_container row"  >
												<div class="ms_selectable col-sm-5 form-group">
													<label><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"/></label>
													<form:select path="" id="coverageLRAvailableUnmappedVillageLevel" multiple="multiple" class="form-control"/>
												</div>
												<div class="ms_buttons col-sm-2"><br>
														<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR" value="<spring:message code='Button.WHOLE' htmlEscape='true'/>" param="Whole"/>
														<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR" value="<spring:message code='Button.BACK' htmlEscape='true'/>"  param="Back"/>
														<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR" value="<spring:message code='Button.RESET' htmlEscape='true'/>" param="Reset"/>
														<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR" value="<spring:message code='Button.PART' htmlEscape='true'/>"  param="Part"/>
												</div>
												<div class="ms_selection col-sm-5">
													<div class="form-group">
														<label><spring:message htmlEscape="true" code="Label.CONTRIBUTVILLAGELIST"/></label>
																	<form:select path="contributingUnmappedVillageCodes" id="coverageLRContributingUnmappedVillageLevel" multiple="multiple" class="form-control"/>											
																	<br/>
																	<form:errors htmlEscape="true" path="contributingUnmappedVillageCodes" cssClass="error"/>
													</div>
												</div>
											</div>	
										
											
<%-- 										</c:if>									 --%>
									</ul>
								</div>	
							
					
				<br/>
				<!-- Block for Covered area of Local Body Ends Here -->
				
				<!-- Block for Government Order Details Started -->	
				<div style="padding-left: 30px;"><%@include file="../ExistingGovernmentOrderCpy.jsp"%>	</div>
				<br/>
				<!-- Block for Government Order Details Ends Here-->
				<div class="box-footer">
     				<div class="col-sm-offset-2 col-sm-10">
      			 		<div class="pull-right">	
							<div style="display: none" id="drafthide">
							<input class="btn btn-success" id="btnFormActionSaveDraft" type="submit" value="<spring:message code='Button.DRAFT' htmlEscape='true'/>" param="DRAFT"/>
							</div>
							<input class="btn btn-success" id="btnFormActionSave" type="submit" value="<spring:message htmlEscape="true" code="Button.SP"/>" param="PUBLISH"/>
							<input class="btn btn-danger" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'"/>
						</div>
					</div>
				</div>
							
						
			</form:form>
			</div>
			</section>
			</div>
			</section>
			
		
<!-- Main Form Stylings ends -->

</body>
</html>

