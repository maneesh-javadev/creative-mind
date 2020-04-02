<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>
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
			<h3><spring:message htmlEscape="true" code="Label.viewdraftedlb"></spring:message></h3>
			<ul class="item_list">
				<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form method="POST" id="formViewDraftedLB" commandName="draftLocalbodyTemp">
					<input type="hidden" name="tempLocalBodyCode" value="${draftLocalbodyTemp.id}"/>
					<input type="hidden" name="tempLocalBodyCreationType" value="${draftBCreationType}"/>
					<!-- Block for General Details of Drafted Local Government Body. -->
					<div id="divCenterAligned" class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4><spring:message htmlEscape="true"  code="Label.GENERALDETAILNEWLOCALBODY"></spring:message></h4>
								<ul class="form_body" >
									<li>
										<label class="inline">
											<spring:message htmlEscape="true"  code="Label.LOCALBODYNAMEENGLISH"></spring:message>
									    </label>
									    <label class="wrapped">
									    	<c:out value="${draftLocalbodyTemp.localBodyNameEnglish}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYNAMELOCAL"></spring:message>
										</label>
										<label class="wrapped">
											<c:out value="${draftLocalbodyTemp.localBodyNameLocal}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message>
										</label>
										<label class="wrapped">
											<c:out value="${draftLocalbodyTemp.localBodyAliasEnglish}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message>
										</label>
										<label class="wrapped">
											<c:out value="${draftLocalbodyTemp.localBodyAliasLocal}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.STATESPECIFICCODE"></spring:message>
										</label>
										<label >
											<c:out value="${draftLocalbodyTemp.stateSpecificCode}" escapeXml="true"></c:out>
									    </label>
									</li>
									
									<!-- PESA Hide from UI -->
									<%-- <c:if test="${draftBCreationType != URBAN_CONSTANT}">
										<li>
											<label class="inline">
												<spring:message htmlEscape="true" code="Label.PESAACT"></spring:message>
											</label>
											<label >
												<c:choose>
													<c:when test="${draftLocalbodyTemp.pesaActImpl}"><spring:message code='Label.YES' htmlEscape='true'/></c:when>
													<c:otherwise><spring:message code='Label.NO' htmlEscape='true'/></c:otherwise>
												</c:choose>
											</label>
										</li>
									</c:if> --%>
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
								<h4><spring:message htmlEscape="true" code="Label.SELECTEDTYPELOCALBODY"></spring:message></h4>
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
								
					<!-- Block for Coverage Details of Drafted Local Government Body. -->
					<div id="divCenterAligned" class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4><spring:message htmlEscape="true"  code="Label.COVEREDAREAOFNEWLCLBDY"></spring:message></h4>
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
					
					<!-- Block for Head quarter of Drafted Local Government Body. -->
					<c:if test="${draftBCreationType != URBAN_CONSTANT}">
						<div id="divCenterAligned" class="form_stylings">
							<div class="form_block">
								<div class="col_1">
									<h4><spring:message code="Label.HeaquarterFrame" htmlEscape="true"></spring:message></h4>
									<ul class="form_body" >
										<li>
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
					</c:if>
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
											<c:if test="${not empty draftLocalbodyTemp.coordinates}">
												<c:set var="arrLatLong" value="${fn:split(draftLocalbodyTemp.coordinates, ',')}"/>
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
									<c:if test="${not empty draftLocalbodyTemp.mapUploadPath}">
										<br/>
										<li>
											<h4><spring:message code='Label.map.uploaded.details' htmlEscape='true'/></h4>
											<label>	
												<spring:message code='Label.UPLOADMAP' htmlEscape='true'/>							
											</label>
											<label>
												<c:set var="mapFileName" value=""/>
												<c:if test="${not empty draftLocalbodyTemp.mapUploadPath}">
													<c:set var="mapsubstrng" value="${fn:substring(draftLocalbodyTemp.mapUploadPath, fn:indexOf(draftLocalbodyTemp.mapUploadPath, '_'), fn:indexOf(draftLocalbodyTemp.mapUploadPath, '.'))}" />
													<c:set var="mapFileName" value="${fn:replace(draftLocalbodyTemp.mapUploadPath, mapsubstrng, '')}" />
												</c:if>
												<a href="downloadLBMap.htm?filename=${draftLocalbodyTemp.mapUploadPath}&<csrf:token uri='downloadLBMap.htm'/>">
													<c:out value="${mapFileName}"/>
												</a>
											</label>
										</li>
									</c:if>
								</ul>	
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
											<c:out value="${draftLocalbodyTemp.orderNo}"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message>
										</label>
										<label >
											<c:out value="${draftLocalbodyTemp.orderDate}"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message	code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> 
										</label>
										<label>
											<c:out value="${draftLocalbodyTemp.effectiveDate}"></c:out>
									    </label>
									</li>
									
									<c:choose>
										<c:when test="${isGovernmentOrderUpload}">
											<li>
									   			<label>	
													<spring:message	code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>									
												</label>
												<label>
													<c:out value="${draftLocalbodyTemp.gazPubDate}"></c:out>
											    </label>
											</li>
											<li>
												<label>	
													<spring:message code='Label.UGO' htmlEscape='true'/>								
												</label>
												<label>
													<c:choose>
														<c:when test="${not empty draftLocalbodyTemp.orderCode}">
															<a href="downloadLBGovernementOrder.htm?filename=${govtOrderDetails.fileTimestamp}&<csrf:token uri='downloadLBGovernementOrder.htm'/>"><c:out value="${govtOrderDetails.fileName}"/></a>
														</c:when>
														<c:otherwise>
															<c:set var="fileName" value=""/>
															<c:if test="${not empty draftLocalbodyTemp.orderPath}">
																<c:set var="substrng" value="${fn:substring(draftLocalbodyTemp.orderPath, fn:indexOf(draftLocalbodyTemp.orderPath, '_'), fn:indexOf(draftLocalbodyTemp.orderPath, '.'))}" />
																<c:set var="fileName" value="${fn:replace(draftLocalbodyTemp.orderPath, substrng, '')}" />
															</c:if>
															<a href="downloadLBGovernementOrder.htm?filename=${draftLocalbodyTemp.orderPath}&<csrf:token uri='downloadLBGovernementOrder.htm'/>">
																<c:out value="${fileName}"/>
															</a>
														</c:otherwise>
													</c:choose>
												</label>
											</li>
										</c:when>
										<c:otherwise>
											<li>
												<div id="divCKEditor" >
													<form:textarea path="editorTemplateDetails" id="editorTemplateDetails" cssClass="ckeditor" disabled="true"/>
												</div>
									   		</li>
										</c:otherwise>
									</c:choose>
									
								</ul>	
							</div>
						</div>
					</div>
					<br/>
					<!-- Block for Government Order Details of Drafted Local Government Body Ends. -->	
					<input class="bttn" id="btnFormActionPublish" type="button" value="<spring:message htmlEscape="true"  code="Button.SP"/>" param="publishSingleDraftedLocalBody"/>
					<input class="bttn" id="btnFormActionDelete" type="button" value="Delete" param="deleteSingleDraftedLocalBody" />
					<input class="bttn" id="btnFormActionClose" type="button" value="Close" param="home"/>
				</form:form>
			</div>
		</div>
		<!-- Page Content ends -->
	</div>
	<!-- Main Form Styling ends -->
</body>
</html>