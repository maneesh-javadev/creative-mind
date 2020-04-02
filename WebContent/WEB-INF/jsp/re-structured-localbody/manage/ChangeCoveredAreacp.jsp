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


  <section class="content">
	<div class="row">
          <!-- main col -->
          <section class="col-lg-12">

          <div class="box">
		      <div class="box-header with-border">
		        <h3 class="box-title"><spring:message code="Label.CORRECTGOVTLOCALBODY.CC" htmlEscape="true"></spring:message></h3>
		      </div><!-- /.box-header -->
		      
		
				<form:form class="form-horizontal" action="changeCoverageLocalBody.htm" method="post" id="localBodyForm" commandName="localBodyForm" enctype="multipart/form-data">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="changeCoverageLocalBody.htm"/>" />
					<form:hidden path="processAction"/>
					<form:hidden path="localBodyCreationType"/>
					<form:hidden path="localBodyCode" id="paramLBCode"/>
					<form:hidden path="localBodyTypeCode"/>
					<form:hidden path="removedLangRegionCodes"/>
					<form:hidden path="isGISCoverage"/>
					<form:hidden path="localBodyNameEnglish" />
					<!-- For Preview GIS Coverage#started -->
					<!-- <div id="dialogBX" style="display: none;">
						<iframe id="myIframe" width="910" height="650"></iframe>
					</div> -->
					<!-- For Preview GIS Coverage#end -->
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
                  		<h4 class="box-title"><spring:message htmlEscape="true" code="Label.CORRECTLOCALBODY"></spring:message></h4>
               	    </div><!-- /.box-header -->
				
						
							<!-- Div Container For Current Coverred Area of Local Body -->
							
			<div class="form-group-sm-8" style="padding-left: 30px;">
				<c:choose>
					<c:when test="${localBodyForm.isGISCoverage eq 'Y'}">
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
						</c:when>
						<c:otherwise>
							<c:if test="${localBodyTypeLevel eq DISTRICT_PANCHAYAT_LEVEL.charAt(0)}">
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
									</c:if>
									
							<c:if test="${(localBodyTypeLevel eq  DISTRICT_PANCHAYAT_LEVEL.charAt(0)) or (localBodyTypeLevel eq  INTERMEDIATE_PANCHAYAT_LEVEL.charAt(0))}">	
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
													<td><c:out value="${completedCoveragesIM.landRegionNameEnglish}(Sub-District),${completedCoveragesIM.entityHierarchy}"></c:out></td>
													<td><img id="remove_${completedCoveragesIM.parentCode}" alt="remove" src="images/delete.png" param="${completedCoveragesIM.landRegionCode}">
														<%-- <input type="text" value="${completedCoveragesIM.parentCode}"/> --%>
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
												<td><c:out value="${completedCoveragesV.landRegionNameEnglish}(Village),${completedCoveragesV.entityHierarchy}"></c:out></td>
												<td><img id="remove_${completedCoveragesV.parentCode}" alt="remove" src="images/delete.png" param="${completedCoveragesV.landRegionCode}">
													<%-- <input type="text" value="${completedCoveragesV.parentCode}"/> --%>
												</td>
											</tr>
											</c:if>
										</c:forEach>
									</tbody>
								</table>		  
								</c:if>
						</c:otherwise>
				</c:choose>
			</div>		
							
		<br/>
				
				
				
				<!-- This Block used for Covered area of Local Body Started -->	
				<%-- <c:if test="${localBodyForm.isGISCoverage ne 'Y'}"> --%>
					<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message htmlEscape="true" code="Label.COVEREDAREAOFNEWLCLBDY"></spring:message></h4>
               	    </div><!-- /.box-header -->
               	    <div class="col-sm-12" style="padding-left: 30px;">
               	    <c:choose>
						<c:when test="${localBodyForm.isGISCoverage ne 'Y'}">
							
					  			<form:checkbox path="checkboxCoverageLB" id="checkboxCoverageLB" param="LB_COVERAGE"   />
					  		
						</c:when>
						<c:otherwise>
							
					  			<form:checkbox path="checkboxCoverageLB" id="checkboxCoverageLB" param="LB_COVERAGE" disabled = "true" />
					  		
						</c:otherwise>
					</c:choose>
					<spring:message htmlEscape="true" code="Label.SELEXISTINGCOVEREDAREA"></spring:message>
					
				</div>
				<br/>
			<!-- Div Container For Coverage from Existing Local Bodies Elements -->
				 <div class="box-body dept_list_button" id="firstLevelLBDiv" style="display: none;">
					<div class="ms_container row"  >
							<div class="ms_selectable col-sm-5 form-group">
								<label id="labelAvailablePanchayat">
												<spring:message htmlEscape="true" code="Label.AVAILABLE"/>
												<spring:message htmlEscape="true" code="${lblLocalBodyHeading}"/>
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
									<label id="labelContributingPanchayat">
										<spring:message htmlEscape="true" code="Label.CONTRIBUTE"/>
										<spring:message htmlEscape="true" code="${lblLocalBodyHeading}"/>
									</label>
									<form:select path="contributingLBCodes" id="coverageLBContributingPanchayatLevel" multiple="multiple" class="form-control"/>
									<br/>
						    		<form:errors htmlEscape="true" path="contributingLBCodes" cssClass="error"/>	
									<input class="btn btn-primary" type="button" id="fetchCoverageofLocalBody" value="<spring:message htmlEscape="true" code="Button.GETCOVEREDAREAOFLOCALBODY"/>" 
										   style="width:100%;" level="PanchayatLevel" paramLBLR="LB"/>	
								</div>
							</div>
						</div>	
				  </div>
				  
				  <div class="box-body dept_list_button" id="secondLevelLBDiv" style="display: none;">
					<div class="ms_container row"  >
							<div class="ms_selectable col-sm-5 form-group">
								<label><spring:message htmlEscape="true" code="Label.AVAILDISTRICTLIST"/></label>
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
									<label><spring:message htmlEscape="true" code="Label.CONTRIBUTDISTRICTLIST"/></label>
											<form:select path="contributingLBDistrictCodes" id="coverageLBContributingDisttLevel" multiple="multiple" class="form-control"/>
											<br/>
											<form:errors htmlEscape="true" path="contributingLBDistrictCodes" cssClass="error"/>	
											<input class="btn btn-primary" type="button" id="btnFetchCoverageDistrict" value="<spring:message htmlEscape="true" code="Button.GETSUBDISTRICTL"/>" 
												   style="width: 100%;" param="LB_COVERAGE_DISTRICT" level="DisttLevel" paramLBLR="LB"/>
								</div>
							</div>
						</div>	
				   </div>
				  
					<div class="box-body dept_list_button" id="thirdLevelLBDiv" style="display: none;">
					<div class="ms_container row" >
							<div class="ms_selectable col-sm-5 form-group">
								<label form-group="coverageLBAvailableSubdisttLevel"><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"/></label>
								<form:select path="" id="coverageLBAvailableSubdisttLevel" multiple="multiple" class="form-control"/>
							</div>
							<div class="ms_buttons col-sm-2"><br>
								<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="SubdisttLevel" paramLBLR="LB" value="<spring:message code='Button.WHOLE' htmlEscape='true'/>" param="Whole"/>
								<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="SubdisttLevel" paramLBLR="LB" value="<spring:message code='Button.BACK' htmlEscape='true'/>"  param="Back"/>
								<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="SubdisttLevel" paramLBLR="LB" value="<spring:message code='Button.RESET' htmlEscape='true'/>" param="Reset"/>
								<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="SubdisttLevel" paramLBLR="LB" value="<spring:message code='Button.PART' htmlEscape='true'/>"  param="Part"/>
							</div>
							<div class="ms_selection col-sm-5">
								<div class="form-group">
									<label for="coverageLBContributingSubdisttLevel"><spring:message htmlEscape="true" code="Label.CONTRIBUTSUBDISTRICTLIST"/></label>
											<form:select path="contributingLBSubDistrictCodes" id="coverageLBContributingSubdisttLevel" multiple="multiple" class="form-control"/>
											<br/>
											<form:errors htmlEscape="true" path="contributingLBSubDistrictCodes" cssClass="error"/>
											<input class="btn btn-primary" type="button" id="btnFetchCoverageSubDistrict" value="<spring:message htmlEscape="true" code="Button.GETVILLAGEL"/>" 
												   style="width:100%" param="LB_COVERAGE_SUB_DISTRICT" level="SubdisttLevel" paramLBLR="LB"/>
								</div>
							</div>
						</div>	
				   </div>	
					<div class="box-body dept_list_button" id="fourthLevelLBDiv" style="display: none;">
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
				   </div>		
								
								
								
							<br/>
				 <div class="col-sm-12" style="padding-left: 30px;">
				
							
				<c:choose>
					<c:when test="${localBodyForm.isGISCoverage ne 'Y'}">
						
				  			<form:checkbox path="checkboxCoverageUnmapped" id="checkboxCoverageUnmapped" param="UNMAPPED_COVERAGE"/>
				  	
					</c:when>
					<c:otherwise>
							
					  			<form:checkbox path="checkboxCoverageUnmapped" id="checkboxCoverageUnmapped" param="UNMAPPED_COVERAGE" disabled = "true" />
					  		
					</c:otherwise>
				</c:choose>
				<spring:message htmlEscape="true" code="Label.SELCONTRIBUTEUNMAPPEDPARTIALLY"/>
				
				</div>
				
				
							
<!-- Div Container for coverage from Unmapped / Partially Mapped Land Regeion Elements -->
				
				
				<div class="box-body dept_list_button" id="firstLevelUnmappedLRDiv" style="display: none;">
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
				   <div class="box-body dept_list_button" id="secondLevelUnmappedLRDiv" style="display: none;">
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
				 	<div class="box-body dept_list_button" id="thirdLevelUnmappedLRDiv" style="display: none;">
						<div class="ms_container row"  >
							<div class="ms_selectable col-sm-5 form-group">
								<label><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"/></label>
								<form:select path="" id="coverageLRAvailableUnmappedVillageLevel" multiple="multiple" class="form-control" />
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
											
												<form:errors htmlEscape="true" path="contributingUnmappedVillageCodes" cssClass="error"/>	
								</div>
								
							</div>
						</div>	
				  	</div>
						
								
									
					<%-- </c:if> --%>
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
					</div>
					</div>
					<br/>
					<!-- Block for HeadQuarters Ends Here-->
				
					<!-- Block for Government Order Details Started -->
					<div style="padding-left: 30px;"><%@include file="../ExistingGovernmentOrderCpy.jsp"%>	</div>
					<br/>
								
					
					<!-- Block for Government Order Details Ends Here-->
					
		<div class="box-footer">
     		<div class="col-sm-offset-2 col-sm-10">
      			 <div class="pull-right">
					<!-- For Preview GIS Coverage#started -->
					<c:if test="${localBodyTypeLevel eq  VILLAGE_PANCHAYAT_LEVEL.charAt(0) and localBodyForm.isGISCoverage ne 'Y'}">	
					<input class="btn btn-primary" id="btnPreviewGIS" type="button" value="<spring:message code='Button.PREVIEWGIS' htmlEscape='true'/>"  />
					</c:if> 
					<!-- For Preview GIS Coverage#end -->
					<c:if test="${localBodyForm.isGISCoverage ne 'Y'}">
					
					<div style="display: none" id="drafthide">
					<input class="btn btn-success" id="btnFormActionSaveDraft" type="submit" value="<spring:message code='Button.DRAFT' htmlEscape='true'/>" param="DRAFT"/>
					</div>
					</c:if>
					<input class="btn btn-success" id="btnFormActionSave" type="submit" value="<spring:message htmlEscape="true" code="Button.SP"/>" param="PUBLISH"/>
					<input class="btn btn-danger" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'"/>
				</div>	
			</div>
		</div>
		
		 <div class="modal fade" id="dialogBX" tabindex="-1" role="dialog" >
			<div class="modal-dialog" style="width:950px;">
					<div class="modal-content">
		  				<div class="modal-header">
		   				   <button type="button" class="close" data-dismiss="modal">&times;</button>
		    			  	<h4 class="modal-title" id="dialogBXTitle"></h4>
		    			  	
		  				</div>
		  				<div class="modal-body" id="dialogBXbody">
		        			<iframe id="myIframe" width="910" height="650"></iframe>
		        			
		     			 
						</div>
						
				</div>
			</div>
		</div>
				</form:form>
			</div>
			</section>
	</div>
		<!-- Page Content ends -->
	</section>
	<!-- Main Form Styling ends -->
</body>
</html>