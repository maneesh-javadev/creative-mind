<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../../common/taglib_includes.jsp"%>
<%-- <script type="text/javascript" language="javascript" src="<%=contextpthval%>/ckeditor/ckeditor.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/ckeditor/ckeditor_components.js"></script> --%>
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
<link href="<%=contextpthval%>/resources-localbody/css/jquery.dataTables.css" rel="stylesheet" type="text/css">	
<script src="<%=contextpthval%>/resources-localbody/scripts/jquery.dataTables.js" type="text/javascript" language="javascript" ></script>
<script type="text/javascript" language="javascript" class="init">
	$(document).ready(function() {
		$( '[id^=btnFormAction]' ).click(function() {	
			var url = $(this).attr('param').concat('.htm');	
			$( 'form[id=formViewDraftedChangeTypeLB]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
			$( 'form[id=formViewDraftedChangeTypeLB]' ).attr('method','post');
			$( 'form[id=formViewDraftedChangeTypeLB]' ).submit();
		});
	});
</script>
</head>
<body class="dt-example">
	<!-- Main Form Styling starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message code="Label.MODIFYGOVTLOCALBODY.GO" htmlEscape="true"></spring:message></h3>
			<ul class="item_list">
				<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form id="formViewDraftedChangeTypeLB" commandName="draftChangeTypeLocalbodyTemp">
					<input type="hidden" name="tempLocalBodyCode" value="${draftChangeGovtOrderLBTemp.tempPkId}"/>
					<input type="hidden" name="tempLocalBodyCreationType" value="${draftBCreationType}"/>
					
					<!-- Block for Show General Details of Drafted Local Government Body. -->
					<div id="divCenterAligned" class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4><spring:message htmlEscape="true"  code="Label.GENERALLOCALGOVTBODYDETAILS"></spring:message></h4>
								<ul class="form_body" >
									<c:if test="${localBodyTableObj.isdisturbed}">
										<li>
											<label class="inline">
												<spring:message code="Label.DISTURBEDSTATE" htmlEscape="true" ></spring:message>
										    </label>
										    <label >
										    	<img src="<%=contextpthval%>/images/red_flg.png" width="13" height="9" />
										    </label>
										</li>
									</c:if>	
									<li>
										<label class="inline">
											Name of Local Body ()
									    </label>
									    <label >
									    	<c:out value="${localBodyTableObj.localBodyNameEnglish}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYNAMELOCAL"></spring:message>
										</label>
										<label >
											<c:out value="${localBodyTableObj.localBodyNameLocal}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message>
										</label>
										<label>
											<c:out value="${localBodyTableObj.localBodyAliasEnglish}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message>
										</label>
										<label >
											<c:out value="${localBodyTableObj.localBodyAliasLocal}" escapeXml="true"></c:out>
									    </label>
									</li>
								</ul>	
							</div>
						</div>
					</div>
					<br/>
					<!-- Block for Show General Details of Drafted Local Government Body Ends. -->		
				

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
											<c:out value="${draftChangeGovtOrderLBTemp.orderNo}"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message>
										</label>
										<label >
											<c:out value="${draftChangeGovtOrderLBTemp.orderDate}"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message	code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> 
										</label>
										<label>
											<c:out value="${draftChangeGovtOrderLBTemp.effectiveDate}"></c:out>
									    </label>
									</li>
									
									<c:choose>
										<c:when test="${isGovernmentOrderUpload}">
											<li>
									   			<label>	
													<spring:message	code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>									
												</label>
												<label>
													<c:out value="${draftChangeGovtOrderLBTemp.gazPubDate}"></c:out>
											    </label>
											</li>
											<li>
												<label>	
													<spring:message code='Label.UGO' htmlEscape='true'/>								
												</label>
												<label>
													<%-- <c:choose>
														<c:when test="${not empty draftChangeGovtOrderLBTemp.orderCode}">
															<a href="downloadLBGovernementOrder.htm?filename=${govtOrderDetails.fileTimestamp}&<csrf:token uri='downloadLBGovernementOrder.htm'/>"><c:out value="${govtOrderDetails.fileName}"/></a>
														</c:when>
														<c:otherwise>
															<c:set var="fileName" value=""/>
															<c:if test="${not empty draftChangeGovtOrderLBTemp.orderPath}">
																<c:set var="substrng" value="${fn:substring(draftChangeGovtOrderLBTemp.orderPath, fn:indexOf(draftChangeGovtOrderLBTemp.orderPath, '_'), fn:indexOf(draftChangeGovtOrderLBTemp.orderPath, '.'))}" />
																<c:set var="fileName" value="${fn:replace(draftChangeTypeLocalbodyTemp.orderPath, substrng, '')}" />
															</c:if>
															<a href="downloadLBGovernementOrder.htm?filename=${draftChangeGovtOrderLBTemp.orderPath}&<csrf:token uri='downloadLBGovernementOrder.htm'/>">
																<c:out value="${fileName}"/>
															</a>
														</c:otherwise>
													</c:choose> --%>
													<c:set var="fileName" value=""/>
													<c:if test="${not empty draftChangeGovtOrderLBTemp.orderPath}">
														<c:set var="substrng" value="${fn:substring(draftChangeGovtOrderLBTemp.orderPath, fn:indexOf(draftChangeGovtOrderLBTemp.orderPath, '_'), fn:indexOf(draftChangeGovtOrderLBTemp.orderPath, '.'))}" />
														<c:set var="fileName" value="${fn:replace(draftChangeGovtOrderLBTemp.orderPath, substrng, '')}" />
													</c:if>
													<a href="downloadLBGovernementOrder.htm?filename=${draftChangeGovtOrderLBTemp.orderPath}&<csrf:token uri='downloadLBGovernementOrder.htm'/>">
														<c:out value="${fileName}"/>
													</a>
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
					
					<!-- Block for GIS Details of Drafted Local Government Body. -->
					<div id="divCenterAligned" class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4><spring:message code="Label.GISNODES" htmlEscape="true"></spring:message></h4>
								<ul class="form_body" >
									<li>
										<label>	
												Coordinates Details							
										</label>
										<label>
										<table class="data_grid" width="100%">
											<thead>
												<tr>
													<th><spring:message code='Label.longituderange' htmlEscape='true'/></th>
													<th><spring:message code='Label.latituderange' htmlEscape='true'/></th>
												</tr>
											</thead>
											<tbody>
											<c:if test="${not empty draftChangeGovtOrderLBTemp.coordinates}">
												<c:set var="arrLatLong" value="${fn:split(draftChangeGovtOrderLBTemp.coordinates, ',')}"/>
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
										</label>
									</li>
									<c:if test="${not empty draftChangeGovtOrderLBTemp.mapUploadPath}">
										<li>
											<label>	
												<spring:message code='Label.UPLOADMAP' htmlEscape='true'/>							
											</label>
											<label>
												<c:set var="mapFileName" value=""/>
												<c:set var="mapSubstrng" value="${fn:substring(draftChangeGovtOrderLBTemp.mapUploadPath, fn:indexOf(draftChangeGovtOrderLBTemp.mapUploadPath, '_'), fn:indexOf(draftChangeGovtOrderLBTemp.mapUploadPath, '.'))}" />
												<c:set var="mapFileName" value="${fn:replace(draftChangeGovtOrderLBTemp.mapUploadPath, mapSubstrng, '')}" />
												<a href="downloadLBMap.htm?filename=${draftChangeGovtOrderLBTemp.mapUploadPath}&<csrf:token uri='downloadLBMap.htm'/>"><c:out value="${mapFileName}"/></a>
											</label>
										</li>
									</c:if>
								</ul>	
							</div>
						</div>
					</div>
					<br/>
					<!-- Block for GIS Details of Drafted Local Government Body Ends. -->			

					<input class="bttn" id="btnFormActionPublish" type="button" value="<spring:message htmlEscape="true"  code="Button.SP"/>" param="publishSingleDraftedChangeGO"/>
					<input class="bttn" id="btnFormActionDelete" type="button" value="Delete" param="deleteSingleDraftedChangeGO" />
					<input class="bttn" id="btnFormActionClose" type="button" value="Close" param="home"/>
				</form:form>
			</div>
		</div>
		<!-- Page Content ends -->
	</div>
	<!-- Main Form Styling ends -->
</body>
</html>