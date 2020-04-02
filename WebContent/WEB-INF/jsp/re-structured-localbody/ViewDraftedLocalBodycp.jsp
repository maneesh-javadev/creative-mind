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
 <section class="content">
	   <div class="row">
	   <section class="col-lg-12">
	 
	 <div class="box">
                <div class="box-header with-border">
                  <h3 class="box-title"><spring:message htmlEscape="true" code="Label.viewdraftedlb"></spring:message></h3>
                </div>
                
          <form:form method="POST" id="formViewDraftedLB" commandName="draftLocalbodyTemp">
					<input type="hidden" name="tempLocalBodyCode" value="${draftLocalbodyTemp.id}"/>
					<input type="hidden" name="tempLocalBodyCreationType" value="${draftBCreationType}"/>
			
			<div class="box-body">
                <div id="divCenterAligned" >
						
							<div class="box-header subheading">
								<h4><spring:message htmlEscape="true"  code="Label.GENERALDETAILNEWLOCALBODY"></spring:message></h4>
							</div>
							<table class="table table-bordered table-hover">
							
							<tbody>
	                            <tr>
								  <td><spring:message htmlEscape="true"  code="Label.LOCALBODYNAMEENGLISH"></spring:message></td>
								  <td><c:out value="${draftLocalbodyTemp.localBodyNameEnglish}" escapeXml="true"></c:out></td>
								</tr>
							      <tr>
							        <td><spring:message htmlEscape="true" code="Label.LOCALBODYNAMELOCAL"></spring:message></td>
							        <td><c:out value="${draftLocalbodyTemp.localBodyNameLocal}" escapeXml="true"></c:out></td>
							      </tr>
							      <tr>
							        <td><spring:message htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message></td>
							        <td><c:out value="${draftLocalbodyTemp.localBodyAliasEnglish}" escapeXml="true"></c:out></td>
							      </tr>
							      <tr>
							        <td><spring:message htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message></td>
							        <td><c:out value="${draftLocalbodyTemp.localBodyAliasLocal}" escapeXml="true"></c:out></td>
							      </tr>
							      <tr>
							        <td><spring:message htmlEscape="true" code="Label.STATESPECIFICCODE"></spring:message> </td>
							        <td><c:out value="${draftLocalbodyTemp.stateSpecificCode}" escapeXml="true"></c:out></td>
							      </tr>
							     
							   </tbody>
														
							  </table>
					</div>
             
           <!-- Block for Local Body Types of Drafted Local Government Body. -->
					<div id="divCenterAligned" >
							<div class="box-header subheading">
								<h4><spring:message htmlEscape="true" code="Label.SELECTEDTYPELOCALBODY"></spring:message></h4>
								</div>
								<table class="table table-bordered table-hover">
									<tr>
										<td >
											Local Body Type
										</td>
										<td>
											<c:out value="${localbodyTypeName}" escapeXml="true"></c:out>
										</td>
									</tr>
									<c:if test="${not empty parentLBLevels}">
										<c:forEach var="parentObj" items="${parentLBLevels}">
											<tr>
												<td >
													<c:out value="${parentObj.lbTypeName}" escapeXml="true"></c:out>
												</td>
												<td>
													<c:out value="${parentObj.lbNameEnglish}" escapeXml="true"></c:out>
												</td>
											</tr>
										</c:forEach>
									</c:if>
								
							</table>
					</div>
					<br/>
					<!-- Block for Local Body Types of Drafted Local Government Body Ends. -->		
         
         <!-- Block for Coverage Details of Drafted Local Government Body. -->
					<div id="divCenterAligned" >
							<div class="box-header subheading">
								<h4><spring:message htmlEscape="true"  code="Label.COVEREDAREAOFNEWLCLBDY"></spring:message></h4>
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
					<!-- Block for Coverage Details of Drafted Local Government Body Ends. -->	

                   <!-- Block for Head quarter of Drafted Local Government Body. -->
					<c:if test="${draftBCreationType != URBAN_CONSTANT}">
						<div id="divCenterAligned" >
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
												<table id="tblHeadquarter" class="table table-bordered table-hover" width="100%">
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
						<br/>
					</c:if>
					<!-- Block for Head quarter of Drafted Local Government Body Ends. -->
                  
                  								
					<!-- Block for GIS Details of Drafted Local Government Body. -->
					<div id="divCenterAligned" >
							<div class="box-header subheading">
								<h4><spring:message code="Label.GISNODES" htmlEscape="true"></spring:message></h4>
								</div>
									
										<table class="table table-bordered table-hover" width="50%">
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
								
									<c:if test="${not empty draftLocalbodyTemp.mapUploadPath}">
										<br/>
										<div>
										<div class="box-header subheading">
											<h4><spring:message code='Label.map.uploaded.details' htmlEscape='true'/></h4>
										</div>
										<div class="form-group">
										  <label class="col-sm-3 control-label">	
												<spring:message code='Label.UPLOADMAP' htmlEscape='true'/>							
											</label>
											<div class="col-sm-6">
												<c:set var="mapFileName" value=""/>
												<c:if test="${not empty draftLocalbodyTemp.mapUploadPath}">
													<c:set var="mapsubstrng" value="${fn:substring(draftLocalbodyTemp.mapUploadPath, fn:indexOf(draftLocalbodyTemp.mapUploadPath, '_'), fn:indexOf(draftLocalbodyTemp.mapUploadPath, '.'))}" />
													<c:set var="mapFileName" value="${fn:replace(draftLocalbodyTemp.mapUploadPath, mapsubstrng, '')}" />
												</c:if>
												<a href="downloadLBMap.htm?filename=${draftLocalbodyTemp.mapUploadPath}&<csrf:token uri='downloadLBMap.htm'/>">
													<c:out value="${mapFileName}"/>
												</a>
											</div>
										  </div>
										</div>
									</c:if>
						
					</div>
					<br/>
					<!-- Block for GIS Details of Drafted Local Government Body Ends. -->	
                       <!-- Block for Government Order Details of Drafted Local Government Body. -->
					<div id="divCenterAligned" >
					
							<div class="box-header subheading">
								<h4><spring:message htmlEscape="true"  code="Label.GOVTORDERDETAILS"></spring:message></h4>
								</div>
								
								<table class="table table-bordered table-hover">
								 <tr>
								  <td><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message></td>
								  <td><c:out value="${draftLocalbodyTemp.orderNo}"></c:out></td>
								 </tr>
								 
								 <tr>
								  <td><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message></td>
								  <td><c:out value="${draftLocalbodyTemp.orderDate}"></c:out></td>
								 </tr>
								 
								 <tr>
								  <td><spring:message	code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message></td>
								  <td><c:out value="${draftLocalbodyTemp.effectiveDate}"></c:out></td>
								 </tr>
								 
								<c:choose>
										<c:when test="${isGovernmentOrderUpload}">
											<tr>
									   			<td>	
													<spring:message	code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>									
												</td>
												<td>
													<c:out value="${draftLocalbodyTemp.gazPubDate}"></c:out>
											    </td>
											</tr>
											<tr>
												<td>	
													<spring:message code='Label.UGO' htmlEscape='true'/>								
												</td>
												<td>
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
												</td>
											</tr>
										</c:when>
										<c:otherwise>
											<tr colspan="2">
												<div id="divCKEditor" >
													<form:textarea path="editorTemplateDetails" id="editorTemplateDetails" cssClass="ckeditor" disabled="true"/>
												</div>
									   		</tr>
										</c:otherwise>
									</c:choose>
								
								
								</table>
					</div>
					<br/>
				



             </div>
       <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">                   
                    <button class="btn btn-success" id="btnFormActionPublish" type="button" param="publishSingleDraftedLocalBody"><spring:message htmlEscape="true"  code="Button.SP"/></button>
					<button class="btn btn-danger" id="btnFormActionDelete" type="button"  param="deleteSingleDraftedLocalBody" >Delete</button>
					<button class="btn btn-danger" id="btnFormActionClose" type="button"  param="home">Close</button>
            </div>
            </div>
          </div>
             
             
             
             
             
            </form:form>      
              
              </div>
               </section>
            </div>
         </section>
</body>
</html>