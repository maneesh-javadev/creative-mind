<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="../../common/dwr.jsp"%>
<%-- <script type="text/javascript" language="javascript" src="<%=contextpthval%>/ckeditor/ckeditor.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/ckeditor/ckeditor_components.js"></script> --%>
<c:set var="URBAN_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.LB_URBAN.toString()%>"></c:set>
<c:set var="LB_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.LOCAL_BODY_CONSTANT.toString()%>"></c:set>
<c:set var="DISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.DISTRICT_CONSTANT.toString()%>"></c:set>
<c:set var="SUBDISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.SUB_DISTRICT_CONSTANT.toString()%>"></c:set>
<c:set var="VILLAGE_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.VILLAGE_CONSTANT.toString()%>"></c:set>

<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="javascript" class="init">
	$(document).ready(function() {
		$( '[id^=btnFormAction]' ).click(function() {	
			var url = $(this).attr('param').concat('.htm');	
			$( 'form[id=formViewDraftedLB]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
			$( 'form[id=formViewDraftedLB]' ).attr('method','post');
			$( 'form[id=formViewDraftedLB]' ).submit();
		});
	});
</script>
</head>
<body class="dt-example">
	<!-- Main Form Styling starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message htmlEscape="true" code="Label.VIEWLOCALGOVTBODYDETAILS"></spring:message></h3>
			<ul class="item_list">
				<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form method="POST" id="formViewDraftedLB" commandName="criteriaLocalBodies">
					
					<!-- Block for General Details of Drafted Local Government Body. -->
					<div id="divCenterAligned" class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4><spring:message htmlEscape="true"  code="Label.GENERALLOCALGOVTBODYDETAILS"></spring:message></h4>
								<ul class="form_body" >
									<li>
										<label class="inline">
											<spring:message htmlEscape="true"  code="Label.LOCALBODYCODE"></spring:message>
									    </label>
									    <label >
									    	<c:out value="${localBodyEntity.localBodyCode}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label class="inline">
											<spring:message htmlEscape="true"  code="Label.LOCALBODYNAMEENGLISH"></spring:message>
									    </label>
									    <label >
									    	<c:out value="${localBodyEntity.localBodyNameEnglish}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYNAMELOCAL"></spring:message>
										</label>
										<label >
											<c:out value="${localBodyEntity.localBodyNameLocal}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message>
										</label>
										<label>
											<c:out value="${localBodyEntity.localBodyAliasEnglish}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message>
										</label>
										<label >
											<c:out value="${localBodyEntity.localBodyAliasLocal}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.STATESPECIFICCODE"></spring:message>
										</label>
										<label >
											<c:out value="${localBodyEntity.stateSpecificCode}" escapeXml="true"></c:out>
									    </label>
									</li>
									
									<c:if test="${criteriaLocalBodies.localBodyCreationType != URBAN_CONSTANT}">
										<li>
											<label class="inline">
												<spring:message htmlEscape="true" code="Label.PESAACT"></spring:message>
											</label>
											<label >
												<c:choose>
													<c:when test="${localBodyEntity.isPesa}"><spring:message code='Label.YES' htmlEscape='true'/></c:when>
													<c:otherwise><spring:message code='Label.NO' htmlEscape='true'/></c:otherwise>
												</c:choose>
											</label>
										</li>
									</c:if>
								</ul>	
							</div>
						</div>
					</div>
					<br/>
					<!-- Block for General Details of Drafted Local Government Body Ends. -->	
					
					<!-- Block for Local Body Types of Drafted Local Government Body. -->
					<div id="divCenterAligned" class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4><spring:message htmlEscape="true" code="label.localbody.details"></spring:message></h4>
								<ul class="form_body" >
									<li>
										<label class="inline">
											Local Body Type
										</label>
										<label>
											<c:out value="${localbodyTypeName}" escapeXml="true"></c:out>
										</label>
									</li>
									<c:if test="${not empty parentLBLevels}">
										<c:forEach var="parentObj" items="${parentLBLevels}">
											<li>
												<label class="inline">
													<c:out value="${parentObj.lbTypeName}" escapeXml="true"></c:out>
												</label>
												<label>
													<c:out value="${parentObj.lbNameEnglish}" escapeXml="true"></c:out>
												</label>
											</li>
										</c:forEach>
									</c:if>
								</ul>	
							</div>
						</div>
					</div>
					<br/>
					<!-- Block for Local Body Types of Drafted Local Government Body Ends. -->		
								
					<!-- Block for General Details of Drafted Local Government Body Ends. -->	
					<c:set var="districtAvailable"></c:set>
					<c:set var="subDistrictAvailable" value=""></c:set>
					<c:set var="villageAvailable"></c:set>
					<%-- <c:set var="headquarterDetails" value=""></c:set> --%>
					<c:forEach var="coverages" items="${publishedLBCoverages}">
						<c:choose>
							<c:when test="${coverages.landRegionType eq DISTRICT_CONSTANT}">
								<c:set var="districtAvailable" value="true" />
							</c:when>
							<c:when test="${coverages.landRegionType eq SUBDISTRICT_CONSTANT}">
								<c:set var="subDistrictAvailable" value="true"  />
							</c:when>
							<c:when test="${coverages.landRegionType eq VILLAGE_CONSTANT}">
								<c:set var="villageAvailable" value="true" />
							</c:when>
						</c:choose>
						<%-- <c:if test="${coverages.headquarter}">
											<c:set var="headquarterDetails" value="${coverages}"/>
										</c:if> --%>
					</c:forEach>
				
				
					<!-- Block for Coverage Details of Drafted Local Government Body. -->
					<div class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4><spring:message htmlEscape="true"  code="Label.COVEREDAREAOFNEWLCLBDY"></spring:message></h4>
								<ul class="form_body" >
									<li>
										<c:if test="${districtAvailable}">
											<table class="data_grid" width="100%">
												<thead>
													<tr>
														<td colspan="3"><strong><spring:message code="Label.CONTRIBUTINGDISTRICT" htmlEscape="true"/></strong></td>
													</tr>
													<tr>
														<th><spring:message code="Label.DISTRICTCODE" htmlEscape="true"/></th>
														<th><spring:message code="Label.DISTRICTVERSION" htmlEscape="true"/></th>
														<th><spring:message code="Label.DISTRICTNAMEENGLISH" htmlEscape="true"/></th>
													</tr>
												</thead>
												<tbody>	
													<c:forEach var="listContributingDistrict" items="${publishedLBCoverages}">
														<c:if test="${listContributingDistrict.landRegionType eq DISTRICT_CONSTANT}">
															<tr>
																<td><c:out value="${listContributingDistrict.landRegionCode}"/> </td>
																<td><c:out value="${listContributingDistrict.landRegionVersion}"/></td>
																<td><c:out value="${listContributingDistrict.landRegionNameEnglish}"/></td>
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
														<td colspan="3"><strong><spring:message code="Label.CONTRIBUTINGSUBDISTRICT" htmlEscape="true"/></strong></td>
													</tr>
													<tr>
														<th><spring:message code="Label.SUBDISTRICTCODE" htmlEscape="true"/></th>
														<th><spring:message code="Label.SUBDISTRICTVERSION" htmlEscape="true"/></th>
														<th><spring:message code="Label.SUBDISTRICTNAMEENGLISH" htmlEscape="true"/></th>
													</tr>
												</thead>
												<tbody>	
													<c:forEach var="listContributingSubDistrict" items="${publishedLBCoverages}">
														<c:if test="${listContributingSubDistrict.landRegionType eq SUBDISTRICT_CONSTANT}">
															<tr>
																<td><c:out value="${listContributingSubDistrict.landRegionCode}"/> </td>
																<td><c:out value="${listContributingSubDistrict.landRegionVersion}"/></td>
																<td><c:out value="${listContributingSubDistrict.landRegionNameEnglish}"/></td>
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
														<td colspan="3"><strong><spring:message code="Label.CONTRIBUTINGVILLAGES" htmlEscape="true"/></strong></td>
													</tr>
													<tr>
														<th><spring:message code="Label.VILLAGECODE" htmlEscape="true"/></th>
														<th><spring:message code="Label.VILLAGEVERSION" htmlEscape="true"/></th>
														<th><spring:message code="Label.VILLAGENAMEINENGLISH" htmlEscape="true"/></th>
													</tr>
												</thead>
												<tbody>	
													<c:forEach var="listContributingVillage"	items="${publishedLBCoverages}">
														<c:if test="${listContributingVillage.landRegionType eq VILLAGE_CONSTANT}">
															<tr>
																<td><c:out value="${listContributingVillage.landRegionCode}"/> </td>
																<td><c:out value="${listContributingVillage.landRegionVersion}"/></td>
																<td><c:out value="${listContributingVillage.landRegionNameEnglish}"/></td>
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
					
					<!-- Block for Head quarter of Drafted Local Government Body. -->
					<%-- <c:if test="${criteriaLocalBodies.localBodyCreationType != URBAN_CONSTANT}">
						<div id="divCenterAligned" class="form_stylings">
							<div class="form_block">
								<div class="col_1">
									<h4><spring:message code="Label.HeaquarterFrame" htmlEscape="true"></spring:message></h4>
									<ul class="form_body" >
										<li>
											<c:if test="${not empty headquarterDetails}">
												<c:set var="hqHeading"></c:set>
												<c:choose>
													<c:when test="${headquarterDetails.landRegionType eq DISTRICT_CONSTANT}">
														<c:set var="hqHeading" value="District"/>
													</c:when>
													<c:when test="${headquarterDetails.landRegionType eq SUBDISTRICT_CONSTANT}">
														<c:set var="hqHeading" value="Sub-district"/>
													</c:when>
													<c:when test="${headquarterDetails.landRegionType eq VILLAGE_CONSTANT}">
														<c:set var="hqHeading" value="Village"/>
													</c:when>
												</c:choose>
												<table id="tblHeadquarter" class="data_grid" width="100%">
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
										</li>
									</ul>	
								</div>
							</div>
						</div>
						<br/>
					</c:if> --%>
					<!-- Block for Head quarter of Drafted Local Government Body Ends. -->
											
					<!-- Block for GIS Details of Drafted Local Government Body. -->
					<div id="divCenterAligned" class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4><spring:message code="Label.GISNODES" htmlEscape="true"></spring:message></h4>
								<ul class="form_body" >
									<li>
										<table class="data_grid" width="50%">
											<thead>
												<tr>
													<th><spring:message code='Label.longituderange' htmlEscape='true'/></th>
													<th><spring:message code='Label.latituderange' htmlEscape='true'/></th>
												</tr>
											</thead>
											<tbody>
											<c:if test="${not empty localBodyEntity.coordinates}">
												<c:set var="arrLatLong" value="${fn:split(localBodyEntity.coordinates, ',')}"/>
												<c:forEach var="objLatLong" items="${arrLatLong}">
													<c:set var="arrColumnValues" value="${fn:split(objLatLong, ':')}"/>
													<tr>
														<td>
															<c:out value="${arrColumnValues[0]}"></c:out>
														</td>
														<td>
															<c:out value="${arrColumnValues[1]}"></c:out>
														</td>
													</tr>		
												</c:forEach>
											</c:if>
											</tbody>
										</table>
									</li>
									
									<%-- <c:if test="${not empty draftLocalbodyTemp.mapUploadPath}">
										<li>
											<label>	
												<spring:message code='Label.UPLOADMAP' htmlEscape='true'/>							
											</label>
											<c:set var="mapfileName" value=""/>
											<c:if test="${not empty localBodyForm.mapUploadPath}">
												<c:set var="mapSubstrng" value="${fn:substring(localBodyForm.mapUploadPath, fn:indexOf(localBodyForm.mapUploadPath, '_'), fn:indexOf(localBodyForm.mapUploadPath, '.'))}" />
												<c:set var="mapfileName" value="${fn:replace(localBodyForm.mapUploadPath, mapSubstrng, '')}" />
												<a id="attachedUploadedFile" href="downloadLBMap.htm?filename=${localBodyForm.mapUploadPath}&<csrf:token uri='downloadLBMap.htm'/>">
													<c:out value="${mapfileName}"/>
												</a>
											</c:if>
											<label>
												<a href="downloadLBMap.htm?filename=${draftLocalbodyTemp.mapUploadPath}&<csrf:token uri='downloadLBMap.htm'/>"><c:out value="${draftLocalbodyTemp.mapUploadPath}"/></a>
											</label>
											
										</li>
									</c:if> --%>
								</ul>	
								<c:if test="${not empty mapAttachment}">
												<div class="col_1">
													<h4>Map Uploaded File Details</h4>
													<ul class="form_body">
														<li>
															<table class="data_grid" width="50%">
																<thead>
																	<tr>
																		<th><spring:message code="Label.FILENAME" htmlEscape="true"/></th>
																		<th><spring:message code="Label.FILESIZE" htmlEscape="true"/></th>
																		<th><spring:message code="Label.CONTENTTYPE" htmlEscape="true"/></th>
																	</tr>
																</thead>
																<tbody >
																	<tr>
																		<td>
																			<c:set var="mapfileName" value=""/>
																			<c:if test="${not empty mapAttachment.fileName}">
																				<c:set var="mapSubstrng" value="${fn:substring(mapAttachment.fileName, fn:indexOf(mapAttachment.fileName, '_'), fn:indexOf(mapAttachment.fileName, '.'))}" />
																				<c:set var="mapfileName" value="${fn:replace(mapAttachment.fileName, mapSubstrng, '')}" />
																				<a id="attachedUploadedFile" href="downloadLBMap.htm?filename=${mapAttachment.fileName}&<csrf:token uri='downloadLBMap.htm'/>">
																					<c:out value="${mapfileName}"/>
																				</a>
																			</c:if>
																		</td>
																		<td>
																			<c:out value="${mapAttachment.fileSize}"></c:out>
																		</td>
																		<td>
																			<c:out value="${mapAttachment.fileContentType}"></c:out>
																		</td>
																	</tr>
																</tbody>
															</table>
														</li>
													</ul>
												</div>
											<br/>
									</c:if>
							</div>
						</div>
					</div>
					<br/>
					<!-- Block for GIS Details of Drafted Local Government Body Ends. -->	
					
					<!-- Block for Government Order Details of Drafted Local Government Body. -->
					<div id="divCenterAligned" class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4><spring:message htmlEscape="true"  code="Label.GOVTORDERDETAILS"></spring:message></h4>
								<ul class="form_body" >
									<li>
										<label class="inline">
											<spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message>
									    </label>
									    <label >
											<c:out value="${governmentOrderDetails.orderNo}"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message>
										</label>
										<label >
											<c:out value="${governmentOrderDetails.orderDate}"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message	code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> 
										</label>
										<label>
											<c:out value="${governmentOrderDetails.effectiveDate}"></c:out>
									    </label>
									</li>
									<li>
							   			<label>	
											<spring:message	code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>									
										</label>
										<label>
											<c:out value="${governmentOrderDetails.gazPubDate}"></c:out>
									    </label>
									</li>
									<li>
							   			<label>	
											<spring:message code='Label.UGO' htmlEscape='true'/>							
										</label>
										<label>
											<c:set var="fileName" value=""/>
											<c:if test="${not empty governmentOrderDetails.orderPath}">
												<c:set var="substrng" value="${fn:substring(governmentOrderDetails.orderPath, fn:indexOf(governmentOrderDetails.orderPath, '_'), fn:indexOf(governmentOrderDetails.orderPath, '.'))}" />
												<c:set var="fileName" value="${fn:replace(governmentOrderDetails.orderPath, substrng, '')}" />
											</c:if>
											<a href="downloadLBGovernementOrder.htm?filename=${governmentOrderDetails.orderPath}&<csrf:token uri='downloadLBGovernementOrder.htm'/>">
												<c:out value="${fileName}" escapeXml="true"/>
											</a>
									    </label>
									</li>
								</ul>	
							</div>
						</div>
					</div>
					<br/>
					<!-- Block for Government Order Details of Drafted Local Government Body Ends. -->	
					<input class="bttn" id="btnFormActionClose" type="button" value="Close" param="home"/>
				</form:form>
			</div>
		</div>
		<!-- Page Content ends -->
	</div>
	<!-- Main Form Styling ends -->
</body>
</html>