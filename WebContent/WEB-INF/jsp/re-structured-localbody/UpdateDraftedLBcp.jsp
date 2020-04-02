
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="LocalBodyJavascript.jsp"%>
<%@include file="../common/dwr.jsp"%>
<c:set var="LB_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.LOCAL_BODY_CONSTANT.toString()%>"></c:set>
<c:set var="DISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.DISTRICT_CONSTANT.toString()%>"></c:set>
<c:set var="SUBDISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.SUB_DISTRICT_CONSTANT.toString()%>"></c:set>
<c:set var="VILLAGE_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.VILLAGE_CONSTANT.toString()%>"></c:set>
<title><spring:message htmlEscape="true"  code="Label.CREATENEWLOCALGOVTBODY"></spring:message></title>
<script>
/* $(document).ready(function() {
	$( '[id^=btnFormAction]' ).click(function() {	
		var url = $(this).attr('param').concat('.htm');	
		$( 'form[id=formViewDraftedLB]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
		$( 'form[id=formViewDraftedLB]' ).attr('method','post');
		$( 'form[id=formViewDraftedLB]' ).submit();
	});
}); */


</script>
</head>
<body>
	<section class="content">
	   <div class="row">
	   <section class="col-lg-12">
	 
	 <div class="box">
                <div class="box-header with-border">
                  <h3 class="box-title"><spring:message htmlEscape="true" code="Label.modifydraftedlb"></spring:message></h3>
                </div>
                
           <form:form action="buildLocalBody.htm" method="post" id="localBodyForm" commandName="localBodyForm" enctype="multipart/form-data" class="form-horizontal">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="buildLocalBody.htm"/>" />
					<form:hidden path="id" id="draftTempId"/>
					<form:hidden path="processAction"/>
					<form:hidden path="localBodyCreationType"/>
					<form:hidden path="isResetedCoverage"/>
			<div class="box-header subheading"><h4><spring:message htmlEscape="true"  code="Label.GENERALDETAILNEWLOCALBODY"></spring:message></h4></div>
			
			<div class="box-body">
              
              	
								<div class="form-group">
									<label class="col-sm-3 control-label">
										<spring:message htmlEscape="true" code="Label.LOCALBODYNAMEENGLISH"></spring:message>
										<span class="mandate">*</span>
									</label>
									<div class="col-sm-6">
									<form:input path="localBodyNameEnglish" id="localBodyNameEnglish" maxlength="200" htmlEscape="true"  class="form-control"/>
									<span class="errormessage" id="errLBNameEnglish"></span>
									<form:errors htmlEscape="true" path="localBodyNameEnglish" cssClass="error"/>
								  </div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">
										<spring:message htmlEscape="true" code="Label.LOCALBODYNAMELOCAL"></spring:message>
									</label>
									<div class="col-sm-6">
									<form:input path="localBodyNameLocal" id="localBodyNameLocal" maxlength="100" htmlEscape="true"  class="form-control"/>	
									<br/>
									<form:errors htmlEscape="true" path="localBodyNameLocal" cssClass="error"/>							
								</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">
										<spring:message htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message>
									</label>
									<div class="col-sm-6">
									<form:input path="localBodyAliasEnglish" id="localBodyAliasEnglish" maxlength="50" htmlEscape="true"  class="form-control"/>
									<br/>
									<form:errors htmlEscape="true" path="localBodyAliasEnglish" cssClass="error"/>									
								</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">
										<spring:message htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message>
									</label>
									<div class="col-sm-6">
									<form:input path="localBodyAliasLocal" id="localBodyAliasLocal" maxlength="50" htmlEscape="true"  class="form-control"/>
									<br/>	
									<form:errors htmlEscape="true" path="localBodyAliasLocal" cssClass="error"/>						
								</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">
										<spring:message htmlEscape="true" code="Label.STATESPECIFICCODE"></spring:message>
									</label>
									<div class="col-sm-6">
									<form:input path="stateSpecificCode" id="stateSpecificCode" maxlength="7" htmlEscape="true" class="form-control"/>
									<br/>
									<form:errors htmlEscape="true" path="stateSpecificCode" cssClass="error"/>	
									</div>						
								</div>
								
								
				<div>
						  <div class="box-header subheading">
							<h4><spring:message htmlEscape="true" code="Label.SELECTEDTYPELOCALBODY"></spring:message></h4>
							</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">
										<spring:message code='Label.Selecthierarchylevel' htmlEscape='true'/>
										<span class="mandatory">*</span>
									</label>
									<div class="col-sm-6">
									<form:select path="lbTypeHierarchy" id="lbTypeHierarchy" class="form-control">
										<c:if test="${fn:length(lbTypeHierarchy) ne 1}">
   											<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT"/></form:option>
										</c:if>
										<c:forEach items="${lbTypeHierarchy}" var="objHierarchy" >
											<form:option value="${objHierarchy.localBodySetupCode},${objHierarchy.localBodySetupVersion}">
												<c:out value="${objHierarchy.localBodyTypeHierarchy}" escapeXml="true"></c:out>
											</form:option>
										</c:forEach>
									</form:select>
									<span class="errormessage" id="errorLbTypeHierarchy"></span>
									<form:errors htmlEscape="true" path="lbTypeHierarchy" cssClass="error"/>	
								</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">
										<spring:message code='Label.SELECTLOCALBODYTYPE' htmlEscape='true'/>
										<span class="mandate">*</span>
									</label>
									<div class="col-sm-6">
									<form:select path="localBodyTypePanchayat" id="localBodyType" class="form-control">
										<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT" ></spring:message></form:option>
									</form:select>
									<span class="errormessage" id="errorLocalBodyType"></span>
									<form:errors htmlEscape="true" path="localBodyTypePanchayat" cssClass="error"/>	
								</div>
								</div>
								<div id="divCreateDynamicHierarchy">
									<!-- This Div used to generate Hierarchy -->
								</div>
							
					
					</div>
					<!-- Block for Dynamic Hierarchy of Local Body Type Ends Here-->
						
              <!-- Block for Coverage Details of Drafted Local Government Body. -->
					<div id="divExistingDraftedLBCoverages" >
						<form:hidden path="hidContributingLBCodes"/>
						<form:hidden path="hidContributingLandRegionCodes"/>
						<div>
					
							<div class="box-header subheading">
							  <h4><spring:message htmlEscape="true"  code="Label.COVEREDAREAOFNEWLCLBDY"></spring:message></h4>
							
							</div>
							<div class="form-group">
							<label class="col-sm-3 control-label"></label>	
								<div class="col-sm-6">
								 <button type="button" id="btnResetExistingCoverages" class="btn btn-primary" >RESET COVERAGES</button>
								
								</div>
								
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
											<br/>
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
											<br/>
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
											<br/>
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
											<br/>
										</c:if>
									
							
						</div>
						<br/>
						<div>
							
								<div class="box-header subheading">
									<h4><spring:message code="Label.HeaquarterFrame" htmlEscape="true"></spring:message></h4>
									</div>
										
											<c:if test="${not empty headquarterDetails}">
												<c:set var="hqHeading"></c:set>
												<c:choose>
													<c:when test="${headquarterDetails.lbLrType eq DISTRICT_CONSTANT}">
														<c:set var="hqHeading" value="District"/>
													</c:when>
													<c:when test="${headquarterDetails.lbLrType eq SUBDISTRICT_CONSTANT}">
														<c:set var="hqHeading" value="Sub-district"/>
													</c:when>
													<c:when test="${headquarterDetails.lbLrType eq VILLAGE_CONSTANT}">
														<c:set var="hqHeading" value="Village"/>
													</c:when>
												</c:choose>
												<table class="table table-bordered table-hover" width="100%">
													<thead>
														<tr>
															<th>Contributing <c:out value="${hqHeading}"/> Code</th>
															<th>Contributing <c:out value="${hqHeading}"/> Version</th>
															<th>Contributing <c:out value="${hqHeading}"/> Name</th>
														</tr>
													</thead>
													<tbody>
														<tr>
															<td><c:out value="${headquarterDetails.lbLrCode}"></c:out></td>
															<td><c:out value="${headquarterDetails.lbLrVersion}"></c:out></td>
															<td><c:out value="${headquarterDetails.lbLrNameEnglish}"></c:out></td>
														</tr>
													</tbody>
												</table>
											</c:if>
									
								
						</div>
					</div>
					<!-- Block for Coverage Details of Drafted Local Government Body Ends. -->
              
              
                  <!-- This Block used for Covered area of Local Body Started -->	
					<div id="divCompleteLBCoverages" style="display: none;">
						<div class="box-header subheading">
							<h4><spring:message htmlEscape="true"  code="Label.COVEREDAREAOFNEWLCLBDY"></spring:message></h4>
							</div>
								<div class="form-group">
								<label class="col-sm-3 control-label"></label>
								<div class="col-sm-6">
								 <label class="radio-inline">
										<form:checkbox path="checkboxCoverageLB" id="checkboxCoverageLB" param="LB_COVERAGE"/>
								    	<spring:message htmlEscape="true" code="Label.SELEXISTINGCOVEREDAREA"></spring:message>
								    	<br/>
								    	<form:errors htmlEscape="true" path="checkboxCoverageLB" cssClass="error"/>	
									</label>
								</div>
										
								</div>
							</ul>	
							<!-- Div Container For Coverage from Existing Local Bodies Elements -->
							<div >
								
								
								<div id="firstLevelLBDiv" style="display: none;">
									<div class="ms_container row" style="margin-left: 10px;">
	                                    <div class="ms_selectable col-sm-5 form-group">

											<label id="labelAvailablePanchayat"></label>
											<form:select path="" id="coverageLBAvailablePanchayatLevel" multiple="multiple" class="form-control"/>
										</div>
										<div class="ms_buttons col-sm-2"><br>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="PanchayatLevel" paramLBLR="LB"  param="Whole"><spring:message code='Button.WHOLE' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="PanchayatLevel" paramLBLR="LB"  param="Back"><spring:message code='Button.BACK' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="PanchayatLevel" paramLBLR="LB"  param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="PanchayatLevel" paramLBLR="LB"  param="Part"><spring:message code='Button.PART' htmlEscape='true'/></button>
										</div>
										<div class="ms_selection col-sm-5">
			                                  <div class="form-group">

											<label id="labelContributingPanchayat"></label>
											<form:select path="contributingLBCodes" id="coverageLBContributingPanchayatLevel" multiple="multiple" class="form-control"/>
											<br/>
								    		<form:errors htmlEscape="true" path="contributingLBCodes" cssClass="error"/>	
											<input class="btn btn-primary" type="button" id="fetchCoverageofLocalBody" value="<spring:message htmlEscape="true" code="Button.GETCOVEREDAREAOFLOCALBODY"/>" 
												   style="width:100%;" level="PanchayatLevel" paramLBLR="LB"/>																
										</div>
										</div>
									</div>
								</div>
								
								
								<div id="secondLevelLBDiv" style="display: none;">
									<div class="ms_container row" style="margin-left: 10px;">
	                                 <div class="ms_selectable col-sm-5 form-group">

											<label><spring:message htmlEscape="true" code="Label.AVAILDISTRICTLIST"/></label>
											<form:select path="" id="coverageLBAvailableDisttLevel" multiple="multiple" class="form-control"/>
										</div>
										<div class="ms_buttons col-sm-2"><br>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="DisttLevel" paramLBLR="LB"  param="Whole"><spring:message code='Button.WHOLE' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="DisttLevel" paramLBLR="LB"  param="Back"><spring:message code='Button.BACK' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="DisttLevel" paramLBLR="LB"  param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="DisttLevel" paramLBLR="LB"  param="Part"><spring:message code='Button.PART' htmlEscape='true'/></button>
										</div>
										<div class="ms_selection col-sm-5">
			                               <div class="form-group">

											<label><spring:message htmlEscape="true" code="Label.CONTRIBUTDISTRICTLIST"/></label>
											<form:select path="contributingLBDistrictCodes" id="coverageLBContributingDisttLevel" multiple="multiple" class="form-control"/>
											<br/>
											<form:errors htmlEscape="true" path="contributingLBDistrictCodes" cssClass="error"/>	
											<button class="btn btn-primary" type="button" id="btnFetchCoverageDistrict" 
												   style="width: 100%;" param="LB_COVERAGE_DISTRICT" level="DisttLevel" paramLBLR="LB">	<spring:message htmlEscape="true" code="Button.GETSUBDISTRICTL"/></button>									
										</div>
										</div>
									</div>
								</div>
								
								<div id="thirdLevelLBDiv" style="display: none;">
									 <div class="ms_container row" style="margin-left: 10px;">
	                                    <div class="ms_selectable col-sm-5 form-group">

											<label><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"/></label>
											<form:select path="" id="coverageLBAvailableSubdisttLevel" multiple="multiple" class="form-control"/>
										</div>
										<div class="ms_buttons col-sm-2"><br>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="SubdisttLevel" paramLBLR="LB"   param="Whole"><spring:message code='Button.WHOLE' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="SubdisttLevel" paramLBLR="LB"   param="Back"><spring:message code='Button.BACK' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="SubdisttLevel" paramLBLR="LB"   param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="SubdisttLevel" paramLBLR="LB"   param="Part"><spring:message code='Button.PART' htmlEscape='true'/></button>
										</div>
									<div class="ms_selection col-sm-5">
			                            <div class="form-group">

											<label><spring:message htmlEscape="true" code="Label.CONTRIBUTSUBDISTRICTLIST"/></label>
											<form:select path="contributingLBSubDistrictCodes" id="coverageLBContributingSubdisttLevel" multiple="multiple" class="form-control"/>
											<br/>
											<form:errors htmlEscape="true" path="contributingLBSubDistrictCodes" cssClass="error"/>
											<button class="btn btn-primary" type="button" id="btnFetchCoverageSubDistrict" 
												   style="width:100%" param="LB_COVERAGE_SUB_DISTRICT" level="SubdisttLevel" paramLBLR="LB"><spring:message htmlEscape="true" code="Button.GETVILLAGEL"/></button>
										</div>
										</div>
									</div>
								</div>
								
								
								<div id="fourthLevelLBDiv" style="display: none;">
									 <div class="ms_container row" style="margin-left: 10px;">
	                                 <div class="ms_selectable col-sm-5 form-group">

											<label><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"/></label>
											<form:select path="" id="coverageLBAvailableVillageLevel" multiple="multiple" class="form-control"/>
										</div>
										<div class="ms_buttons col-sm-2"><br>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB"  param="Whole"><spring:message code='Button.WHOLE' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB"   param="Back"><spring:message code='Button.BACK' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB"  param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/></button>
											<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB"   param="Part"><spring:message code='Button.PART' htmlEscape='true'/></button>
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
								</div>
							
							</div>
							<br/>
							
							
								<div class="form-group">
								<label class="col-sm-3 control-label"></label>
								<div class="col-sm-6">
								 <label class="radio-inline">
										<form:checkbox path="checkboxCoverageUnmapped" id="checkboxCoverageUnmapped" param="UNMAPPED_COVERAGE"/>
										<spring:message htmlEscape="true" code="Label.SELCONTRIBUTEUNMAPPEDPARTIALLY"/>
										<br/>
										<form:errors htmlEscape="true" path="checkboxCoverageUnmapped" cssClass="error"/>	
									</label>
								
								
								</div>
									
								</div>
							
							
							<!-- Div Container for coverage from Unmapped / Partially Mapped Land Regeion Elements -->
							<div >
								<!-- Blank heading being used in GUI -->
								
								
									<div id="firstLevelUnmappedLRDiv" style="display: none;">
										 <div class="ms_container row" style="margin-left: 10px;">
	                                        <div class="ms_selectable col-sm-5 form-group">

												<label><spring:message htmlEscape="true"  code="Label.AVAILDISTRICTLIST"/></label>
												<form:select path="" id="coverageLRAvailableUnmappedDisttLevel" multiple="multiple" class="form-control"/>
											</div>
											<div class="ms_buttons col-sm-2"><br>
												<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR"  param="Whole"><spring:message code='Button.WHOLE' htmlEscape='true'/></button>
												<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR"  param="Back"><spring:message code='Button.BACK' htmlEscape='true'/></button>
												<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR"  param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/></button>
												<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR"  param="Part"><spring:message code='Button.PART' htmlEscape='true'/></button>
											</div>
											<div class="ms_selection col-sm-5">
			                                 <div class="form-group">

												<label><spring:message htmlEscape="true" code="Label.CONTRIBUTDISTRICTLIST"/></label>
												<form:select path="contributingUnmappedDistrictCodes" id="coverageLRContributingUnmappedDisttLevel" multiple="multiple" class="form-control"/>
												<br/>
												<form:errors htmlEscape="true" path="contributingUnmappedDistrictCodes" cssClass="error"/>
												<button class="btn btn-primary" type="button" id="btnFetchCoverageUnmappedDistrict" 
												       style="width: 100%" param="LB_COVERAGE_UNMAPPED_DISTRICT" level="UnmappedDisttLevel" paramLBLR="LR"><spring:message htmlEscape="true" code="Button.GETSUBDISTRICTL"/></button>
											</div>
											</div>
										</div>
									</div>
									
									
									<div id="secondLevelUnmappedLRDiv" style="display: none;">
										<div class="ms_container row" style="margin-left: 10px;">
	                                      <div class="ms_selectable col-sm-5 form-group">

												<label><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"/></label>
												<form:select path="" id="coverageLRAvailableUnmappedIntermediateLevel" multiple="multiple" class="form-control"/>
											</div>
											<div class="ms_buttons col-sm-2"><br>
												<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR"  param="Whole"><spring:message code='Button.WHOLE' htmlEscape='true'/></button>
												<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR"   param="Back"><spring:message code='Button.BACK' htmlEscape='true'/></button>
												<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR"  param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/></button>
												<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR"   param="Part"><spring:message code='Button.PART' htmlEscape='true'/></button>
											</div>
											<div class="ms_selection col-sm-5">
			                                    <div class="form-group">

												<label><spring:message htmlEscape="true" code="Label.CONTRIBUTSUBDISTRICTLIST"/></label>
												<form:select path="contributingUnmappedSubDistrictCodes" id="coverageLRContributingUnmappedIntermediateLevel" multiple="multiple" class="form-control"/>											
												<br/>
												<form:errors htmlEscape="true" path="contributingUnmappedSubDistrictCodes" cssClass="error"/>
												<button class="btn btn-primary" type="button" id="btnFetchCoverageUnmappedSubDistrict" 
												       style="width: 100%" param="LB_COVERAGE_UNMAPPED_SUB_DISTRICT" level="UnmappedIntermediateLevel" paramLBLR="LR"><spring:message htmlEscape="true" code="Button.GETVILLAGEL"/></button>
											</div>
											</div>
										</div>
									</div>
									
									
									<div id="thirdLevelUnmappedLRDiv" style="display: none;">
										 <div class="ms_container row" style="margin-left: 10px;">
	                                         <div class="ms_selectable col-sm-5 form-group">

												<label><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"/></label>
												<form:select path="" id="coverageLRAvailableUnmappedVillageLevel" multiple="multiple" class="form-control"/>
											</div>
											<div class="ms_buttons col-sm-2"><br>
												<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR"  param="Whole"><spring:message code='Button.WHOLE' htmlEscape='true'/></button>
												<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR"   param="Back"><spring:message code='Button.BACK' htmlEscape='true'/></button>
												<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR"  param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/></button>
												<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR"   param="Part"><spring:message code='Button.PART' htmlEscape='true'/></button>
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
									</div>
							
							</div>
					
				</div>
				<br/>
				<!-- Block for Covered area of Local Body Ends Here -->
				
				<!-- Block for HeadQuarters Started -->							
				<div id="divCompleteLBHeardQuarter" style="display: none;">
					<div class="box-header subheading">
						<h4>
							<spring:message htmlEscape="true" code="Label.HeaquarterFrame"></spring:message>
							<span class="mandatory">*</span>
						</h4>
						</div>
							
								<button type="button" class="btn btn-primary" id="actionBuildHeadQuarter" ><spring:message code='Label.selectHQ' htmlEscape='true'/></button>
								
								<table id="tblHeadquarter" class="table table-bordered table-hover" width="50%" style="display: none;">
									<thead>
										<tr>
											<th><spring:message code='Label.selectHQ' htmlEscape='true'/></th>
											<th id="lblHQAtLevel"></th>
										</tr>
									</thead>
									<tbody id="tbodyHQuarter"></tbody>
								</table>
								<br/>
								<input type="hidden" id="showHQError" name="showHQError"/><!-- Used Element, Do not remove. -->
								<form:errors htmlEscape="true" path="headQuarterCode" cssClass="error"/>
							
						
					
				</div>
				<br/>
				<!-- Block for HeadQuarters Ends Here-->
				
				
				<!-- Block for GIS Nodes Started -->
				<div >
					<div class="box-header subheading">
						<h4><spring:message code="Label.GISNODES" htmlEscape="true"></spring:message></h4>
						</div>
						
							  <div>
									<div class="form-group">
										<div class="col-sm-4"><label><spring:message code='Label.longituderange' htmlEscape='true'/></label></div>
										<div class="col-sm-4"><label><spring:message code='Label.latituderange' htmlEscape='true'/></label></div>
									</div>
									<div class="form-group"> <!-- Max of 4 divs can be used inside this row div -->
										<div class="col-sm-4"><form:input path="longitude" id="longitude" maxlength="7" class="form-control"/></div>
										<div class="col-sm-4"><form:input path="latitude" id="latitude" maxlength="7" class="form-control"/></div>
										<div class="col-sm-2"><input type="button" class="btn btn-primary" id="btnAddLatLong" value="Addd More"/>
										
										</div>
									</div>
									<div id="divLatitudeLongitude">
										<!-- Adding Dynamic Rows for Latitude and Longitude -->
									</div>
									<br/>
									<form:errors htmlEscape="true" path="longitude" cssClass="error"/>
									<form:errors htmlEscape="true" path="latitude" cssClass="error"/> 
								</div>
								
								
								
							</br></br>
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
									<div class="form-group">
									 <label class="col-sm-3 control-label">	
										<spring:message code="Label.UPLOADMAP" htmlEscape="true"/>
										
									</label>
									
									<div class="col-sm-6">
								
									   <form:input path="mapUpload" id="mapUpload" type="file" class="form-control"/>
									   <form:errors htmlEscape="true" path="mapUpload" cssClass="error"/>
									   	<spring:message code='Label.allowedfileexe' htmlEscape='true'/>
									
									</div>
									</div>
									
									
								</div>		
								
						
					
				</div>
				<br/>
				<!-- Block for GIS Nodes Ends Here -->
				
				<!-- Block for Government Order Details Started -->
				<%@include file="ExistingGovernmentOrdercp.jsp"%>
					
              
               </div>
                  
        <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">                   
                <input class="btn btn-success" id="btnFormActionSaveDraft" type="submit" param="DRAFT" value="<spring:message code='Button.DRAFT' htmlEscape='true'/>"/>
				<input class="btn btn-success" id="btnFormActionSave" type="submit"  param="PUBLISH" value="<spring:message htmlEscape="true"  code="Button.SP"/>"/>
				<input class="btn btn-danger" type="button" id="btnActionClose" param="home" onclick="callActionUrl('home.htm')" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>"/>
            </div>
          </div>
         </form:form>      
              
              </div>
               </section>
            </div>
         </section>
</body>
</html>

