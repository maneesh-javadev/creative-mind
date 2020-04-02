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
<body>
	 <section class="content">
		 <div class="row">
		 	<section class="col-lg-12 ">
	        	<div class="box ">
		        	<div class="box-header with-border">
		            	<h3 class="box-title"><spring:message htmlEscape="true" code="Label.VIEWLOCALGOVTBODYDETAILS"></spring:message></h3>
		            </div>
		            
		                <form:form method="POST" class="form-horizontal" id="formViewDraftedLB" commandName="criteriaLocalBodies">
		                <div class="box-body">
	            		<div class="box-header subheading">
                        	<h4><spring:message htmlEscape="true" code="Label.GENERALLOCALGOVTBODYDETAILS"></spring:message></h4>
		                </div>
		                	<table  class="table table-bordered table-hover" cellspacing="0" width="100%">
		                	    <tbody>					    
									<tr>
										<td><spring:message htmlEscape="true"  code="Label.LOCALBODYCODE"></spring:message></td>
										<td><c:out value="${localBodyEntity.localBodyCode}" escapeXml="true"></c:out></td>
									</tr>
									<tr>
										<td><spring:message htmlEscape="true"  code="Label.LOCALBODYVERSION"></spring:message></td>
										<td><c:out value="${localBodyEntity.localBodyVersion}" escapeXml="true"></c:out></td>
									</tr>
									<tr>
										<td><spring:message htmlEscape="true"  code="label.minor.version"></spring:message></td>
										<td><c:out value="${localBodyEntity.minorVersion}" escapeXml="true"></c:out></td>
									</tr>
									<tr>
									 	<td><spring:message htmlEscape="true"  code="Label.LOCALBODYNAMEENGLISH"></spring:message></td>
										<td><c:out value="${localBodyEntity.localBodyNameEnglish}" escapeXml="true"></c:out></td>
									</tr>
									<tr>
									 	<td><spring:message htmlEscape="true"  code="Label.LOCALBODYNAMELOCAL"></spring:message></td>
										<td><c:out value="${localBodyEntity.localBodyNameLocal}" escapeXml="true"></c:out></td>
									</tr>
									<tr>
										<td><spring:message htmlEscape="true"  code="Label.LOCALBODYALIASENGLISH"></spring:message></td>
										<td><c:out value="${localBodyEntity.localBodyAliasEnglish}" escapeXml="true"></c:out></td>
									</tr>
									<tr>
										<td><spring:message htmlEscape="true"  code="Label.LOCALBODYALIASLOCAL"></spring:message></td>
										<td><c:out value="${localBodyEntity.localBodyAliasLocal}" escapeXml="true"></c:out></td>
									</tr>
									<tr>
										<td><spring:message htmlEscape="true"  code="Label.STATESPECIFICCODE"></spring:message></td>
				    					<td><c:out value="${localBodyEntity.stateSpecificCode}" escapeXml="true"></c:out></td>
				    				</tr>
				    				
				    					<c:if test="${criteriaLocalBodies.localBodyCreationType != URBAN_CONSTANT}">
				    						<tr>
				    							<td><spring:message htmlEscape="true"  code="Label.PESAACT"></spring:message></td>
					    						<td><c:choose>
														<c:when test="${localBodyEntity.isPesa eq 'P'.charAt(0)}"><c:out value="Partial"></c:out></c:when>
														<c:when test="${localBodyEntity.isPesa eq 'F'.charAt(0)}"><c:out value="FULL"></c:out></c:when>
														<c:otherwise><spring:message code='Label.NO' htmlEscape='true'/></c:otherwise>
												</c:choose></td>
											</tr>
				    					</c:if>
				   				</tbody>	
		                	</table>
		                	
		                	 <div class="box-header subheading">
                        		<h4><spring:message htmlEscape="true" code="label.localbody.details"></spring:message></h4>
		               		 </div>
		               		 
		               		 <table  class="table table-bordered table-hover" cellspacing="0" width="100%">
		                	    <tbody>					    
									<tr>
										<td>Local Body Type</td>
										<td><c:out value="${localbodyTypeName}" escapeXml="true"></c:out></td>
									</tr>
									
									<c:if test="${not empty parentLBLevels}">
										<c:forEach var="parentObj" items="${parentLBLevels}">
											<tr>
									 			<td>${parentObj.lbTypeName}</td>
												<td><c:out value="${parentObj.lbNameEnglish}" escapeXml="true"></c:out></td>
											</tr>
										</c:forEach>
									</c:if>
				   				</tbody>	
		                	</table>
		               		 <c:set var="districtAvailable"></c:set>
							<c:set var="subDistrictAvailable" value=""></c:set>
							<c:set var="villageAvailable"></c:set>
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
						   </c:forEach>
		               		 
		               		 <div class="box-header subheading">
                        		<h4><spring:message htmlEscape="true" code="Label.COVEREDAREAOFNEWLCLBDY"></spring:message></h4>
		               		 </div>
		               		 
		               		<c:if test="${districtAvailable}">
								<table class="table table-bordered table-hover" cellspacing="0" width="100%">
									<thead>
										<tr>
											<td colspan="3"><strong><spring:message code="Label.CONTRIBUTINGDISTRICT" htmlEscape="true"/></strong></td>
										</tr>
										<tr>
											<th><spring:message code="Label.DISTRICTCODE" htmlEscape="true"/></th>
											<th><spring:message code="Label.DISTRICTVERSION" htmlEscape="true"/></th>
											<th><spring:message code="Label.DISTRICTNAMEINENGLISH" htmlEscape="true"/></th>
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
								<table class="table table-bordered table-hover" cellspacing="0" width="100%">
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
								<table class="table table-bordered table-hover" cellspacing="0" width="100%">
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
							
							<div class="box-header subheading">
                        		<h4><spring:message code="Label.GISNODES" htmlEscape="true"></spring:message></h4>
		               		 </div>
		               		 
								<table class="table table-bordered table-hover" cellspacing="0" width="100%">
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
							
								<c:if test="${not empty mapAttachment}">
									<div class="box-header subheading">
	                        			<h4><spring:message code="Label.GISNODES" htmlEscape="true"></spring:message></h4>
			               		 	</div>
									<table class="table table-bordered table-hover" cellspacing="0" >
										<thead>
											<tr>
												<th><spring:message code="Label.FILENAME" htmlEscape="true"/></th>
												<th><spring:message code="Label.FILESIZE" htmlEscape="true"/></th>
												<th><spring:message code="Label.CONTENTTYPE" htmlEscape="true"/></th>
											</tr>
										</thead>
										<tbody>
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
							  </c:if>
		                	
		                	
		                	
		                	<div class="box-header subheading">
                        		<h4><spring:message htmlEscape="true" code="Label.GOVTORDERDETAILS"></spring:message></h4>
		                	</div>
		                	<table  class="table table-bordered table-hover" cellspacing="0" width="100%">
		                	    <tbody>					    
									<tr>
										<td><spring:message htmlEscape="true"  code="Label.ORDERNO"></spring:message></td>
										<td><c:out value="${governmentOrderDetails.orderNo}" escapeXml="true"></c:out></td>
									</tr>
									<tr>
									 	<td><spring:message htmlEscape="true"  code="Label.ORDERDATE"></spring:message></td>
									 	<fmt:formatDate var="orderDatecr" value="${governmentOrderDetails.orderDate}" pattern="dd-MM-yyyy" /> 
										<td><c:out value="${orderDatecr}" escapeXml="true"></c:out></td>
									</tr>
									<tr>
									 	<td><spring:message htmlEscape="true"  code="Label.EFFECTIVEDATE"></spring:message></td>
									 	<fmt:formatDate var="ordereffectiveDatecr" value="${governmentOrderDetails.effectiveDate}" pattern="dd-MM-yyyy" /> 
										<td><c:out value="${ordereffectiveDatecr}" escapeXml="true"></c:out></td>
									</tr>
									<tr>
										<td><spring:message htmlEscape="true"  code="Label.GAZPUBDATE"></spring:message></td>
										<fmt:formatDate var="gazPubDatecr" value="${governmentOrderDetails.gazPubDate}" pattern="dd-MM-yyyy" /> 
										<td><c:out value="${gazPubDatecr}" escapeXml="true"></c:out></td>
									</tr>
									<tr>
										<td><spring:message htmlEscape="true"  code="Label.UGO"></spring:message>
										</td>
										<c:set var="fileName" value=""/>
										<td>
										<c:if test="${not empty governmentOrderDetails.orderPath}">
											<c:set var="substrng" value="${fn:substring(governmentOrderDetails.orderPath, fn:indexOf(governmentOrderDetails.orderPath, '_'), fn:indexOf(governmentOrderDetails.orderPath, '.'))}" />
											<c:set var="fileName" value="${fn:replace(governmentOrderDetails.orderPath, substrng, '')}" />
											
											<a href="downloadLBGovernementOrder.htm?filename=${governmentOrderDetails.orderPath}&<csrf:token uri='downloadLBGovernementOrder.htm'/>">
												<c:out value="${fileName}" escapeXml="true"/>
											</a>
										</c:if>
										</td>
									</tr>
				   				</tbody>	
		                	</table>
		                	
		                <div class="box-footer">
                     		<div class="col-sm-offset-2 col-sm-10">
                       			<div class="pull-right">
									<button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>	
                        		</div>
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