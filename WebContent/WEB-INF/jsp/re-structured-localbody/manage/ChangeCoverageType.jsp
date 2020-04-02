<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
 <%@include file="../../common/taglib_includes.jsp"%> 
<%@include file="ChangeCoverageTypeJs.jsp"%>
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
		        <h3 class="box-title"><spring:message code="Label.CHANGECOVERAGETYPE" htmlEscape="true"></spring:message></h3>
		      </div><!-- /.box-header -->
		      <form:form class="form-horizontal" action="mapCoverageLocalBody.htm" method="post" id="localBodyForm" commandName="localBodyForm">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="mapCoverageLocalBody.htm"/>" />					
					<form:hidden path="localBodyCreationType"/>
					<form:hidden path="localBodyCode" id="paramLBCode"/>
					<form:hidden path="localBodyTypeCode"/>					
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
														<td colspan="2"> 															
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
																
																	<td><input type="radio" name="contributeddistrict[${completedCoveragesDistrictStatus.count }]" id="contributedDistrictCodes_P" value="P" paramRegionType="${DISTRICT_CONSTANT}" param="${completedCoveragesDistrict.landRegionCode }" 
																			<c:if test="${completedCoveragesDistrict.coverageType=='P'}">checked="checked"</c:if> />
																	</td>	
																	<td><input type="radio" name="contributeddistrict[${completedCoveragesDistrictStatus.count }]" id="contributedDistrictCodes_F" value="F"  paramRegionType="${DISTRICT_CONSTANT}" param="${completedCoveragesDistrict.landRegionCode }" 
																			<c:if test="${completedCoveragesDistrict.coverageType=='F'}">checked="checked"</c:if> />	
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
														<td colspan="2"> 															
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
															<td><c:out value="${completedCoveragesIM.landRegionNameEnglish}(Sub-District)/${completedCoveragesIM.entityHierarchy}"></c:out></td>	
																												
																<td><input type="radio" name="contributedsubdistrict[${completedCoveragesIMStatus.count }]" id="contributedSubDistrictCodes_P" value="P"  paramRegionType="${SUBDISTRICT_CONSTANT}"  param="${completedCoveragesIM.landRegionCode }" <c:if test="${completedCoveragesIM.coverageType=='P'}">checked="checked"</c:if> /></td>																																				
																<td><input type="radio" name="contributedsubdistrict[${completedCoveragesIMStatus.count }]" id="contributedSubDistrictCodes_F" value="F" paramRegionType="${SUBDISTRICT_CONSTANT}" param="${completedCoveragesIM.landRegionCode }" <c:if test="${completedCoveragesIM.coverageType=='F'}">checked="checked"</c:if>/></td>
															
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
													
													
														<td colspan="2" >
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
														<tr id="tr_${completedCoveragesV.landRegionCode}">
															<td><c:out value="${completedCoveragesV.landRegionCode}"></c:out></td>
															<td><c:out value="${completedCoveragesV.landRegionNameEnglish}(Village),${completedCoveragesV.entityHierarchy}"></c:out></td>
															<td><input type="radio" name="contributedvillage[${completedCoveragesVStatus.count }]" id="contributedVillageCodes_P"   value="P" paramRegionType="${VILLAGE_CONSTANT}"  param="${completedCoveragesV.landRegionCode }" <c:if test="${completedCoveragesV.coverageType=='P'}">checked="checked"</c:if> /></td>
															<td><input type="radio" name="contributedvillage[${completedCoveragesVStatus.count }]" id="contributedVillageCodes_F"   value="F" paramRegionType="${VILLAGE_CONSTANT}" param="${completedCoveragesV.landRegionCode }" <c:if test="${completedCoveragesV.coverageType=='F'}">checked="checked"</c:if> /></td>													
															
														</tr>	
														</c:if>
													</c:forEach>
												</tbody>
											</table>
										
									</c:if>
							</div>
								<br/>
							
							
								
		<div class="box-footer">
     		<div class="col-sm-offset-2 col-sm-10">
      			 <div class="pull-right">
					
					<input class="btn btn-success" id="btnFormActionSave" type="button" value="<spring:message htmlEscape="true" code="Button.SP"/>" param="UPDATE"/>
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