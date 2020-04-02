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
	 <section class="content">
	<div class="row">
          <!-- main col -->
          <section class="col-lg-12">

          <div class="box">
		      <div class="box-header with-border">
		        <h3 class="box-title"><spring:message code="Label.CORRECTGOVTLOCALBODY.MC" htmlEscape="true"></spring:message></h3>
		      </div><!-- /.box-header -->
		      <form:form class="form-horizontal" action="mapCoverageLocalBody.htm" method="post" id="localBodyForm" commandName="localBodyForm">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="mapCoverageLocalBody.htm"/>" />					
					<form:hidden path="localBodyCreationType"/>
					<form:hidden path="localBodyCode" id="paramLBCode"/>
					<form:hidden path="localBodyTypeCode"/>					
					<form:hidden path="headQuarterCode" value="${mainRegionCode}"/>					
					<form:hidden path="localBodyTypePanchayat" value="${localBodyTypeLevel}"/>								
					<form:hidden path="changeCoverageTypeLRList"/>					
					
					
					<!-- Block for Show General Details of Drafted Local Government Body. -->
					<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message htmlEscape="true"  code="Label.GENERALLOCALGOVTBODYDETAILS"></spring:message></h4>
               	    </div><!-- /.box-header -->
               	    
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
					
					<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message htmlEscape="true"  code="Label.CORRECTLOCALBODY"></spring:message></h4>
               	    </div><!-- /.box-header -->
               	    
            	    <div class="form-group" style="padding-left: 30px;">
            	     <label  class="col-sm-12"><spring:message htmlEscape="true"  code="Label.CURRENTCOVERAREA"></spring:message></label>
            	    </div>
					
							<!-- Div Container For Current Coverred Area of Local Body -->
							
								<!-- Blank heading being used in GUI -->
								<!-- <h4></h4> -->
							<div class="form-group-sm-8" style="padding-left: 30px;">
									<c:if test="${localBodyTypeLevel eq DISTRICT_PANCHAYAT_LEVEL.charAt(0)}">	
										
											<table id="tblCoverage_${DISTRICT_PANCHAYAT_LEVEL}" class="data_grid" width="50%">
												<thead>
													<tr>
														<td colspan="3">															
															<strong>Current Covered District</strong>
														</td>
													</tr>
													<tr>
														<th rowspan="2" width="20%">District Code</th>
														<th rowspan="2" width="30%">District Name</th>
														<th rowspan="2" width="30%"> Coverage type</th>
																										
													</tr>
													
												</thead>
												<tbody>
													<c:forEach items="${completedCoverageDetails}" var="completedCoveragesDistrict" varStatus="completedCoveragesDistrictStatus" >
														<c:if test="${completedCoveragesDistrict.landRegionType eq  DISTRICT_CONSTANT}">
															<tr id="tr_${completedCoveragesDistrict.landRegionCode}">
																<td><c:out value="${completedCoveragesDistrict.landRegionCode}"></c:out></td>
																<td><c:out value="${completedCoveragesDistrict.landRegionNameEnglish}"></c:out></td>
																<td>
																<c:choose>
																<c:when test="${completedCoveragesDistrict.coverageType=='P'}">
																<c:out value="Part" />
																
																</c:when>
																<c:otherwise>
																<c:out value="Full" />
																</c:otherwise>
																</c:choose>
																</td>
																
															</tr>
															
														</c:if>
													</c:forEach>
												</tbody>
											</table>
										
									</c:if>
									
									<c:if test="${(localBodyTypeLevel eq  DISTRICT_PANCHAYAT_LEVEL.charAt(0)) or (localBodyTypeLevel eq  INTERMEDIATE_PANCHAYAT_LEVEL.charAt(0))}">	
										
											<table id="tblCoverage_${INTERMEDIATE_PANCHAYAT_LEVEL}" class="data_grid" width="50%">
												<thead>
													<tr>
														<td colspan="3">
															<strong>Current Covered Sub-district</strong>
														</td>
													</tr>
													<tr>
														<th  rowspan="2" width="20%" >Sub-district Code</th>
														<th rowspan="2" width="30%">Sub-district Name</th>
														<th rowspan="2" width="30%"> Coverage type</th>											
													</tr>
													
												</thead>
												<tbody>
													<c:forEach items="${completedCoverageDetails}" var="completedCoveragesIM"  varStatus="completedCoveragesIMStatus">
														<c:if test="${completedCoveragesIM.landRegionType eq  SUBDISTRICT_CONSTANT}">
														<tr id="tr_${completedCoveragesIM.landRegionCode}">
															<td><c:out value="${completedCoveragesIM.landRegionCode}"></c:out></td>
															<td><c:out value="${completedCoveragesIM.landRegionNameEnglish}(Sub-District)/${completedCoveragesIM.entityHierarchy}"></c:out></td>	
															<td>
																<c:choose>
																<c:when test="${completedCoveragesIM.coverageType=='P'}">
																<c:out value="Part" />
																
																</c:when>
																<c:otherwise>
																<c:out value="Full" />
																</c:otherwise>
																</c:choose>
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
										
											<table id="tblCoverage_${VILLAGE_PANCHAYAT_LEVEL}" class="data_grid" width="50%">
												<thead>
													<tr>
														<td colspan="3">
														<strong>Current Covered Village</strong></td>
													</tr>
													<tr>
														<th rowspan="2" width="20%">Village Code</th>
														<th rowspan="2" width="30%">Village Name</th>
														<th rowspan="2" width="30%"> Coverage type</th>															
													</tr>
													
												</thead>
												<tbody>
													<c:forEach items="${completedCoverageDetails}" var="completedCoveragesV" varStatus="completedCoveragesVStatus" >													
														<c:if test="${completedCoveragesV.landRegionType eq VILLAGE_CONSTANT}">
														<script>setCoverageMap('${completedCoveragesV.landRegionCode}','${completedCoveragesV.coverageType}')</script>
														<tr id="tr_${completedCoveragesV.landRegionCode}">
															<td><c:out value="${completedCoveragesV.landRegionCode}"></c:out></td>
															<td><c:out value="${completedCoveragesV.landRegionNameEnglish}(Village),${completedCoveragesV.entityHierarchy}"></c:out></td>
															<td>
																<c:choose>
																<c:when test="${completedCoveragesV.coverageType=='P'}">
																<c:out value="Part" />
																
																</c:when>
																<c:otherwise>
																<c:out value="Full" />
																</c:otherwise>
																</c:choose>
																</td>
														</tr>	
													
														</c:if>
													</c:forEach>
												</tbody>
											</table>
										
									</c:if>
							</div>
								<br/>
							
							<!-- Div Container For Current Coverred Area of Local Body Ends Here -->
							<div class="box-header subheading">
                  				<h4 class="box-title"><spring:message htmlEscape="true" code="Label.UNMAPPPEDPARTILLYLANDREGIONS"></spring:message></h4>
               	    		</div><!-- /.box-header -->
						
<!-- Div Container for coverage from Unmapped / Partially Mapped Land Regeion Elements -->
							
							
						<!-- Blank heading being used in GUI -->
							<div class="box-body " id="firstLevelUnmappedLRDiv" style="display: none;padding-left: 30px;">
								<div class="ms_container row"  >
										<div class="ms_selectable col-sm-5 form-group">
											<label><spring:message htmlEscape="true"  code="Label.AVAILDISTRICTLIST"/></label>
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
												<label><spring:message htmlEscape="true" code="Label.CONTRIBUTDISTRICTLIST"/></label>
												<form:select path="contributingUnmappedDistrictCodes" id="coverageLRContributingUnmappedDisttLevel" multiple="multiple" class="form-control"/>
												<br/>
												<form:errors htmlEscape="true" path="contributingUnmappedDistrictCodes" cssClass="error"/>
												<input class="btn btn-primary" type="button" id="btnFetchCoverageUnmappedDistrict" value="<spring:message htmlEscape="true" code="Button.GETSUBDISTRICTL"/>" 
												       style="width: 100%" param="LB_COVERAGE_UNMAPPED_DISTRICT" level="UnmappedDisttLevel" paramLBLR="LR"/>
											</div>
										</div>
									</div>	
							  </div>
							<div class="box-body" id="secondLevelUnmappedLRDiv" style="display: none;padding-left: 30px;">
								<div class="ms_container row"  >
										<div class="ms_selectable col-sm-5 form-group">
											<label><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"/></label>
											<form:select path="" id="coverageLRAvailableUnmappedIntermediateLevel" multiple="multiple" class="form-control"/>
										</div>
										<div class="ms_buttons col-sm-2"><br>
												<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR" value="<spring:message code='Button.WHOLE' htmlEscape='true'/>" param="Whole"/>
												<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR" value="<spring:message code='Button.BACK' htmlEscape='true'/>"  param="Back"/>
												<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR" value="<spring:message code='Button.RESET' htmlEscape='true'/>" param="Reset"/>
												<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR" value="<spring:message code='Button.PART' htmlEscape='true'/>"  param="Part"/>
												
										</div>
										<div class="ms_selection col-sm-5">
											<div class="form-group">
												<label><spring:message htmlEscape="true" code="Label.CONTRIBUTSUBDISTRICTLIST"/></label>
												<form:select path="contributingUnmappedSubDistrictCodes" id="coverageLRContributingUnmappedIntermediateLevel" multiple="multiple" class="form-control"/>											
												<br/>
												<form:errors htmlEscape="true" path="contributingUnmappedSubDistrictCodes" cssClass="error"/>
												<input class="btn btn-primary" type="button" id="btnFetchCoverageUnmappedSubDistrict" value="<spring:message htmlEscape="true" code="Button.GETVILLAGEL"/>" 
												       style="width: 100%" param="LB_COVERAGE_UNMAPPED_SUB_DISTRICT" level="UnmappedIntermediateLevel" paramLBLR="LR"/>
											</div>
										</div>
									</div>	
							  </div>
								<div class="box-body" id="thirdLevelUnmappedLRDiv" style="display: none;padding-left: 30px;">
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
												<c:if test="${(localBodyTypeLevel eq  VILLAGE_PANCHAYAT_LEVEL.charAt(0) and (habitationConfigration eq 'N' or  habitationConfigration eq 'V' )) }">	
												<br>
												<input class="btn btn-primary" type="button" id="btnFetchHabitationList" value="<spring:message htmlEscape="true" code="Button.GETHABITATIONLIST"/>" 
												       style="width: 100%"/>	
												</c:if>						
										</div>
									</div>	
							  </div>
								</div>
								
									<c:if test="${(localBodyTypeLevel eq  VILLAGE_PANCHAYAT_LEVEL.charAt(0) and (habitationConfigration eq 'N' or  habitationConfigration eq 'V' )) }">		
									<div class="box-body" id="habitationDiv" style="padding-left: 30px;" >
								<div class="ms_container row"  >
										<div class="ms_selectable col-sm-5 form-group">
											<label><spring:message htmlEscape="true"  code="Label.AVAILHABITATION"/></label>
												<form:select path="avilableHabitation" id="avilableHabitation" multiple="multiple" class="form-control"/>
										</div>
										<div class="ms_buttons col-sm-2"><br>
												<input class="btn btn-primary btn-xs btn-block" type="button"  value=">>" onclick="moveElement('WHOLE');" />
												<input class="btn btn-primary btn-xs btn-block" type="button" value=">" onclick="moveElement('FORWARD');" />
												<input class="btn btn-primary btn-xs btn-block"  type="button"  value="<" onclick="moveElement('BACK');"/>
												<input class="btn btn-primary btn-xs btn-block"  type="button" value="<<"  onclick="moveElement('BACKALL');"/>
												
										</div>
										<div class="ms_selection col-sm-5">
											<div class="form-group">
												<label><spring:message htmlEscape="true" code="Label.CONTRIBUTHABITATION"/></label>
												<form:select path="contributingHabiationCodes" id="contributingHabiationCodes" multiple="multiple" class="form-control">
													<c:forEach  var="obj" items="${mappedHabitationList}">
												 		<form:option value="${obj.habitationCode}_E">${obj.habitationNameEnglish}</form:option>
													</c:forEach>
												</form:select>
												<br/>
												<form:errors htmlEscape="true" path="contributingHabiationCodes" cssClass="error"/>
										</div>
									</div>	
							  </div>
								</div>
									
									</c:if>
								
					<br/>
					<!-- Block for Covered area of Local Body Ends Here -->
				
					<!-- Block for HeadQuarters Started -->							
					<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message htmlEscape="true" code="Label.HeaquarterFrame"></spring:message><span class="mandatory">*</span></h4>
               	    </div><!-- /.box-header -->
               	    <div style="padding-left: 30px;">						
					<div class="form-group-sm-12">
									<input type="button" class="btn btn-primary" id="actionBuildHeadQuarter" value="<spring:message code='Label.selectHQ' htmlEscape='true'/>"/>
									<br/>
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
											
										</tbody>
									</table>
									
							
									<br/>
									<input type="hidden" id="showHQError" name="showHQError"/><!-- Used Element, Do not remove. -->
									<form:errors htmlEscape="true" path="headQuarterCode" cssClass="error"/>
					</div>
					</div>
					<br/>
					<!-- Block for HeadQuarters Ends Here-->
								
		<div class="box-footer">
     		<div class="col-sm-offset-2 col-sm-10">
      			 <div class="pull-right">
					
					<input class="btn btn-success" id="btnFormActionSave" type="submit" value="<spring:message htmlEscape="true" code="Button.UPDATE"/>" param="UPDATE"/>
					<input class="btn btn-danger" type="button"  value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'"/>
				</div>
			</div>
		</div>	
					
				</form:form>
			
	</div>
	</section>
	</div>
	</section>
<!-- Select From Existing Local Government Order Started -->
 <div class="modal fade" id="displayExistingLocalBody" tabindex="-1" role="dialog" style="display: none;">
		<div class="modal-dialog" style="width:950px;">
				<div class="modal-content">
	  				<div class="modal-header">
	   				   <button type="button" class="close" data-dismiss="modal">&times;</button>
	    			  	<h4 class="modal-title">Associated Local bodies</h4>
	    			  	
	  				</div>
	  				<div class="modal-body" >
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
					
		</div>
	</div>
</div>
<!-- <div id="displayExistingLocalBody"  style="display: none;">

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
</div> -->
<!-- Select From Existing Local Government Order Ends -->

</body>
</html>