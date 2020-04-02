<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="DISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.DISTRICT_CONSTANT.toString()%>"></c:set>
<c:set var="SUBDISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.SUB_DISTRICT_CONSTANT.toString()%>"></c:set>
<c:set var="VILLAGE_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.VILLAGE_CONSTANT.toString()%>"></c:set>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/dist/css/dashboard.min.css">
<div class="box-header subheading">
                  		<h4 class="box-title">Ward Draft Coverage</h4>
               	    </div><!-- /.box-header -->
               	    
            	  
							<div class="form-group-sm-8" style="padding-left: 30px;">
									
										
											<table id="tblCoverage_district" class="data_grid" width="80%">
												<thead>
													<tr>
														<td colspan="2"> 															
															<strong>Current Covered District</strong>
														</td>
													</tr>
													<tr>
														<th rowspan="2" width="20%">District Code</th>
														<th rowspan="2" width="30%">District Name</th>
														<th colspan="2" align="center" width="40%"> Coverage type</th>
													</tr>
													
														
													
												</thead>
												<tbody>
													<c:forEach items="${completedCoverageDetailsDraft}" var="completedCoveragesDistrict" varStatus="completedCoveragesDistrictStatus" >
														<c:if test="${completedCoveragesDistrict.landRegionType eq  DISTRICT_CONSTANT}">
															<tr >
																<td><c:out value="${completedCoveragesDistrict.landRegionCode}"></c:out></td>
																<td><c:out value="${completedCoveragesDistrict.landRegionNameEnglish}"></c:out></td>
																<td>
																	<c:choose>
																	<c:when test="${completedCoveragesDistrict.coverageType=='P'}">
																	Part
																	</c:when>
																	<c:otherwise>
																	Full
																	</c:otherwise>
																	
																	</c:choose>
																</td>
															
															</tr>
															
														</c:if>
													</c:forEach>
												</tbody>
											</table>
										
									
									
								
										
											<table  class="data_grid" width="80%">
												<thead>
													<tr>
														<td colspan="2"> 															
															<strong>Current Covered Sub-district</strong>
														</td>
														
													</tr>
													<tr>
														<th rowspan="2" width="20%">Sub-district Code</th>
														<th rowspan="2" width="30%">Sub-district Name</th>
														<th colspan="2" align="center" width="40%"> Coverage type</th>
													</tr>
													
												</thead>
												<tbody>
													<c:forEach items="${completedCoverageDetailsDraft}" var="completedCoveragesIM"  varStatus="completedCoveragesIMStatus">
														<c:if test="${completedCoveragesIM.landRegionType eq  SUBDISTRICT_CONSTANT}">
														<tr>
															<td><c:out value="${completedCoveragesIM.landRegionCode}"></c:out></td>
															<td><c:out value="${completedCoveragesIM.landRegionNameEnglish}"></c:out></td>	
															<td>
																	<c:choose>
																	<c:when test="${completedCoveragesIM.coverageType=='P'}">
																	Part
																	</c:when>
																	<c:otherwise>
																	Full
																	</c:otherwise>
																	
																	</c:choose>
															</td>
														</tr>
														
														</c:if>
													</c:forEach>
												</tbody>
											</table>
										
									
									
									
										
											<table id="tblCoverage_village" class="data_grid" width="50%">
												<thead>
													<tr>
													
													
														<td colspan="2" >
														<strong>Current Covered Village</strong></td>
													</tr>
													<tr>
														<th rowspan="2" width="20%">Village Code</th>
														<th rowspan="2" width="30%">Village Name</th>
														<th colspan="2" align="center" width="40%"> Coverage type</th>
														
												</thead>
												<tbody>
													<c:forEach items="${completedCoverageDetailsDraft}" var="completedCoveragesV" varStatus="completedCoveragesVStatus" >													
														<c:if test="${completedCoveragesV.landRegionType eq VILLAGE_CONSTANT}">
														<tr >
															<td><c:out value="${completedCoveragesV.landRegionCode}"></c:out></td>
															<td><c:out value="${completedCoveragesV.landRegionNameEnglish}"></c:out></td>
															<td>
																	<c:choose>
																	<c:when test="${completedCoveragesV.coverageType=='P'}">
																	Part
																	</c:when>
																	<c:otherwise>
																	Full
																	</c:otherwise>
																	
																	</c:choose>
															</td>
															
														</tr>	
														</c:if>
													</c:forEach>
												</tbody>
											</table>
										
									
							</div>
							
							
							<div class="box-header subheading">
                  		<h4 class="box-title">Ward Coverage</h4>
               	    </div><!-- /.box-header -->
               	    
            	  
							<div class="form-group-sm-8" style="padding-left: 30px;">
									
										
											<table id="tblCoverage_district" class="data_grid" width="80%">
												<thead>
													<tr>
														<td colspan="2"> 															
															<strong>Current Covered District</strong>
														</td>
													</tr>
													<tr>
														<th rowspan="2" width="20%">District Code</th>
														<th rowspan="2" width="30%">District Name</th>
														<th colspan="2" align="center" width="40%"> Coverage type</th>
													</tr>
													
														
													
												</thead>
												<tbody>
													<c:forEach items="${completedCoverageDetails}" var="completedCoveragesDistrict" varStatus="completedCoveragesDistrictStatus" >
														<c:if test="${completedCoveragesDistrict.landRegionType eq  DISTRICT_CONSTANT}">
															<tr >
																<td><c:out value="${completedCoveragesDistrict.landRegionCode}"></c:out></td>
																<td><c:out value="${completedCoveragesDistrict.landRegionNameEnglish}"></c:out></td>
																<td>
																	<c:choose>
																	<c:when test="${completedCoveragesDistrict.coverageType=='P'}">
																	Part
																	</c:when>
																	<c:otherwise>
																	Full
																	</c:otherwise>
																	
																	</c:choose>
																</td>
															
															</tr>
															
														</c:if>
													</c:forEach>
												</tbody>
											</table>
										
									
									
								
										
											<table  class="data_grid" width="80%">
												<thead>
													<tr>
														<td colspan="2"> 															
															<strong>Current Covered Sub-district</strong>
														</td>
														
													</tr>
													<tr>
														<th rowspan="2" width="20%">Sub-district Code</th>
														<th rowspan="2" width="30%">Sub-district Name</th>
														<th colspan="2" align="center" width="40%"> Coverage type</th>
													</tr>
													
												</thead>
												<tbody>
													<c:forEach items="${completedCoverageDetails}" var="completedCoveragesIM"  varStatus="completedCoveragesIMStatus">
														<c:if test="${completedCoveragesIM.landRegionType eq  SUBDISTRICT_CONSTANT}">
														<tr>
															<td><c:out value="${completedCoveragesIM.landRegionCode}"></c:out></td>
															<td><c:out value="${completedCoveragesIM.landRegionNameEnglish}"></c:out></td>	
															<td>
																	<c:choose>
																	<c:when test="${completedCoveragesIM.coverageType=='P'}">
																	Part
																	</c:when>
																	<c:otherwise>
																	Full
																	</c:otherwise>
																	
																	</c:choose>
															</td>
														</tr>
														
														</c:if>
													</c:forEach>
												</tbody>
											</table>
										
									
									
									
										
											<table id="tblCoverage_village" class="data_grid" width="50%">
												<thead>
													<tr>
													
													
														<td colspan="2" >
														<strong>Current Covered Village</strong></td>
													</tr>
													<tr>
														<th rowspan="2" width="20%">Village Code</th>
														<th rowspan="2" width="30%">Village Name</th>
														<th colspan="2" align="center" width="40%"> Coverage type</th>
														
												</thead>
												<tbody>
													<c:forEach items="${completedCoverageDetails}" var="completedCoveragesV" varStatus="completedCoveragesVStatus" >													
														<c:if test="${completedCoveragesV.landRegionType eq VILLAGE_CONSTANT}">
														<tr >
															<td><c:out value="${completedCoveragesV.landRegionCode}"></c:out></td>
															<td><c:out value="${completedCoveragesV.landRegionNameEnglish}"></c:out></td>
															<td>
																	<c:choose>
																	<c:when test="${completedCoveragesV.coverageType=='P'}">
																	Part
																	</c:when>
																	<c:otherwise>
																	Full
																	</c:otherwise>
																	
																	</c:choose>
															</td>
															
														</tr>	
														</c:if>
													</c:forEach>
												</tbody>
											</table>
										
									
							</div>