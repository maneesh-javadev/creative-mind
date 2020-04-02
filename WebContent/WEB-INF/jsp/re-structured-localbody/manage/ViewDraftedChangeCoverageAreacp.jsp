<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../../common/taglib_includes.jsp"%>
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
<link href="<%=contextpthval%>/resources-localbody/css/jquery.dataTables.css" rel="stylesheet" type="text/css">	
<script src="<%=contextpthval%>/resources-localbody/scripts/jquery.dataTables.js" type="text/javascript" language="javascript" ></script>
<%-- <script type="text/javascript" language="javascript" src="<%=contextpthval%>/ckeditor/ckeditor.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/ckeditor/ckeditor_components.js"></script>
 --%><c:set var="URBAN_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.LB_URBAN.toString()%>"></c:set>
<c:set var="LB_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.LOCAL_BODY_CONSTANT.toString()%>"></c:set>
<c:set var="DISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.DISTRICT_CONSTANT.toString()%>"></c:set>
<c:set var="SUBDISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.SUB_DISTRICT_CONSTANT.toString()%>"></c:set>
<c:set var="VILLAGE_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.VILLAGE_CONSTANT.toString()%>"></c:set>
<c:set var="DISTRICT_PANCHAYAT_LEVEL" value= "<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.DISTRICT_PANCHAYAT_LEVEL.toString()%>"></c:set>
<c:set var="INTERMEDIATE_PANCHAYAT_LEVEL" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.INTERMEDIATE_PANCHAYAT_LEVEL.toString()%>"></c:set>
<c:set var="VILLAGE_PANCHAYAT_LEVEL" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.VILLAGE_PANCHAYAT_LEVEL.toString()%>"></c:set>	
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
                    <form:form id="formViewDraftedLB" commandName="localBodyForm" class="form-horizontal">
					<input type="hidden" name="tempLocalBodyCreationType" value="${draftBCreationType}"/>
					<input type="hidden" name="tempChangeCoverageCode" value="${draftLocalbodyTemp.id}"/>
					 
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.CORRECTGOVTLOCALBODY.CC" htmlEscape="true"></spring:message></h3>
                                </div>
                                
                        <div class="box-body">
                        <div id="divCenterAligned">
                        
                               <c:if test="${localBodyForm.isdisturbed}">
										<div class="form-group">
											<label  class="col-sm-3 control-label">
												<spring:message code="Label.DISTURBEDSTATE" htmlEscape="true" ></spring:message>
										    </label>
										   <div class="col-sm-6">
										    		<img src="<%=contextpthval%>/images/red_flg.png" width="13" height="9" />
										    </div>
										</div>
									</c:if>
								    
								    <div class="form-group"  >
										<label  class="col-sm-3 control-label" ><spring:message htmlEscape="true"  code="Label.LOCALBODYNAMEENGLISH"></spring:message></label>									
											<div class="col-sm-6">
											 <c:out value="${localBodyForm.localBodyNameEnglish}" escapeXml="true"></c:out>
									    </div>
								    </div>
								    
								    <div class="form-group" >
										<label  class="col-sm-3 control-label" ><spring:message htmlEscape="true" code="Label.LOCALBODYNAMELOCAL"></spring:message></label>									
											<div class="col-sm-6">
											<c:out value="${localBodyForm.localBodyNameLocal}" escapeXml="true"></c:out>
									    </div>
								    </div>
								    
								    
								    <div class="form-group" >
										<label  class="col-sm-3 control-label" ><spring:message htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message></label>									
											<div class="col-sm-6">
											<c:out value="${localBodyForm.localBodyAliasEnglish}" escapeXml="true"></c:out>
									    </div>
								    </div>
								    
								    
								    <div class="form-group" >
										<label  class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message></label>									
											<div class="col-sm-6">
											<c:out value="${localBodyForm.localBodyAliasLocal}" escapeXml="true"></c:out>
									    </div>
								    </div>
								    
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
								    
								    </div>
								    <div class="box-header subheading"><h4><spring:message htmlEscape="true" code="Label.CORRECTLOCALBODY"></spring:message></h4></div>
								    <c:choose>
										<c:when test="${isDistrictLevel}">
										
												<table id="tblCoverage_${DISTRICT_PANCHAYAT_LEVEL}" class="table table-bordered table-hover" width="50%">
													<thead>
														<tr>
															<td colspan="2"><strong>Current Covered District</strong></td>
														</tr>
														<tr>
															<th width="30%">District Code</th>
															<th width="60%">District Name</th>														
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${completedCoverageDetails}" var="completedCoveragesDistrict" >
															<c:if test="${completedCoveragesDistrict.landRegionType eq  DISTRICT_CONSTANT}">
																<tr id="tr_${completedCoveragesDistrict.landRegionCode}">
																	<td><c:out value="${completedCoveragesDistrict.landRegionCode}"></c:out></td>
																	<td><c:out value="${completedCoveragesDistrict.landRegionNameEnglish}"></c:out></td>																
																</tr>
															</c:if>
														</c:forEach>
													</tbody>
												</table>
											
										</c:when>
										<c:otherwise>
											<c:if test="${localBodyTypeLevel eq DISTRICT_PANCHAYAT_LEVEL.charAt(0)}">	
												
													<table id="tblCoverage_${DISTRICT_PANCHAYAT_LEVEL}" class="table table-bordered table-hover" width="50%">
														<thead>
															<tr>
																<td colspan="2"><strong>Current Covered District</strong></td>
															</tr>
															<tr>
																<th width="30%">District Code</th>
																<th width="60%">District Name</th>														
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${completedCoverageDetails}" var="completedCoveragesDistrict" >
																<c:if test="${completedCoveragesDistrict.landRegionType eq  DISTRICT_CONSTANT}">
																	<tr id="tr_${completedCoveragesDistrict.landRegionCode}">
																		<td><c:out value="${completedCoveragesDistrict.landRegionCode}"></c:out></td>
																		<td><c:out value="${completedCoveragesDistrict.landRegionNameEnglish}"></c:out></td>																
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
																<td colspan="2"><strong>Current Covered Sub-district</strong></td>
															</tr>
															<tr>
																<th width="30%">Sub-district Code</th>
																<th width="60%">Sub-district Name</th>														
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${completedCoverageDetails}" var="completedCoveragesIM" >
																<c:if test="${completedCoveragesIM.landRegionType eq  SUBDISTRICT_CONSTANT}">
																<tr id="tr_${completedCoveragesIM.landRegionCode}">
																	<td><c:out value="${completedCoveragesIM.landRegionCode}"></c:out></td>
																	<td><c:out value="${completedCoveragesIM.landRegionNameEnglish}"></c:out></td>															
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
																<td colspan="2"><strong>Current Covered Village</strong></td>
															</tr>
															<tr>
																<th width="30%">Village Code</th>
																<th width="60%">Village Name</th>														
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${completedCoverageDetails}" var="completedCoveragesV" >
																<c:if test="${completedCoveragesV.landRegionType eq  VILLAGE_CONSTANT}">
																<tr id="tr_${completedCoveragesV.landRegionCode}">
																	<td><c:out value="${completedCoveragesV.landRegionCode}"></c:out></td>
																	<td><c:out value="${completedCoveragesV.landRegionNameEnglish}"></c:out></td>															
																</tr>
																</c:if>
															</c:forEach>
														</tbody>
													</table>
												
											</c:if>
										
										</c:otherwise>
									</c:choose>
				<div id="divCenterAligned" >
						<div class="box-header subheading">
								<h4><spring:message htmlEscape="true"  code="Label.COVEREDAREAOFNEWLCLBDY"></spring:message></h4></div>
							
									<c:set var="localBodyAvailabe" value=""></c:set>
									<c:set var="districtAvailable" value=""></c:set>
									<c:set var="subDistrictAvailable" value=""></c:set>
									<c:set var="villageAvailable" value=""></c:set>
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
										<!-- Block for Head quarter of Drafted Local Government Body. -->
					<c:if test="${draftBCreationType != URBAN_CONSTANT}">
						<div id="divCenterAligned" >
							<div>
								<div class="box-header subheading">
									<h4><spring:message code="Label.HeaquarterFrame" htmlEscape="true"></spring:message></h4></div>
									
											<c:if test="${not empty headquarterDetails}">
												<c:set var="headquarterDetails" value="${headquarterDetails[0]}"></c:set>
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
						</div>
				
					</c:if>
					<!-- Block for Head quarter of Drafted Local Government Body Ends. -->
					
					<!-- Block for Government Order Details of Drafted Local Government Body. -->
					<div id="divCenterAligned" >
						
							<div class="box-header subheading">
								<h4><spring:message htmlEscape="true"  code="Label.GOVTORDERDETAILS"></spring:message></h4></div>
							
									<div class="form-group">
										<label class="col-sm-3 control-label">
											<spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message>
									    </label>
									    <div class="col-sm-6" >
											<c:out value="${draftLocalbodyTemp.orderNo}"></c:out>
									    </div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label">
											<spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message>
										</label>
										<div class="col-sm-6" >
											<c:out value="${draftLocalbodyTemp.orderDate}"></c:out>
									    </div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label">
											<spring:message	code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> 
										</label>
										<div class="col-sm-6">
											<c:out value="${draftLocalbodyTemp.effectiveDate}"></c:out>
									    </div>
									</div>
									
									<c:choose>
										<c:when test="${isGovernmentOrderUpload}">
											<div class="form-group">
									   			<label class="col-sm-3 control-label">	
													<spring:message	code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>									
												</label>
												<div class="col-sm-6">
													<c:out value="${draftLocalbodyTemp.gazPubDate}"></c:out>
											    </div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-3 control-label">	
													<spring:message code='Label.UGO' htmlEscape='true'/>								
												</label>
												<div class="col-sm-6">
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
												</div>
											
										</c:when>
										<c:otherwise>
											<div class="form-group">
											<label class="col-sm-3 control-label"></label>
												<div id="divCKEditor" class="col-sm-6">
													<form:textarea path="editorTemplateDetails" id="editorTemplateDetails" cssClass="ckeditor" disabled="true"/>
												</div>
									   		</div>
										</c:otherwise>
									</c:choose>
									
							
					</div>
				
					<!-- Block for Government Order Details of Drafted Local Government Body Ends. -->		
				
				  </div> 
             
                       
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                     <button class="btn btn-success" id="btnFormActionPublish" type="button"  param="publishSinleDraftedChangeCoverageLB"><spring:message htmlEscape="true"  code="Button.SP"/></button>
					<button class="btn btn-danger" id="btnFormActionDelete" type="button"  param="deleteSingleDraftedChangeCoverageLB" >Delete</button>
					<button class="btn btn-danger" id="btnFormActionClose" type="button"  param="home">Close</button>
                 </div>
           </div>   
       </div> 
     </div>   
             
    </form:form>      
   </section>
   </div>
   </section>
</body>
</html>