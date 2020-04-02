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
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/ckeditor/ckeditor_components.js"></script> --%>
<c:set var="URBAN_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.LB_URBAN.toString()%>"></c:set>
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
<body class="dt-example">
	<!-- Main Form Styling starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message code="Label.CORRECTGOVTLOCALBODY.CC" htmlEscape="true"></spring:message></h3>
			<ul class="item_list">
				<%-- //these links are not working<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form action="changeCoverageLocalBody.htm" method="post" id="formViewDraftedLB" commandName="localBodyForm">									
					<input type="hidden" name="tempLocalBodyCreationType" value="${draftBCreationType}"/>
					<input type="hidden" name="tempLocalBodyCode" value="${draftLocalbodyTemp.id}"/>
					<!-- Set Page Level Heading and element Id(s) Started-->
					<c:set var="lblHeadingCurrentCoverage" value="Label.AVAILABLEFORCURRENTSUB"></c:set>
					<c:set var="lblColumnHeadName" value="App.SUBDISTRICTNAMEENGLISH"></c:set>
					<c:set var="lblColumnHeadCode" value="	Label.SUBDISTRICTCODE"></c:set>
					<c:set var="selectedLevel" value="${SUBDISTRICT_CONSTANT}"></c:set>				
					<c:if test="${isDistrictLevel}">
						<c:set var="lblHeadingCurrentCoverage" value="Label.AVAILABLEFORCURRENTDIS"></c:set>
						<c:set var="lblColumnHeadName" value="App.DISTRICTNAMEENGLISH"></c:set>
						<c:set var="lblColumnHeadCode" value="	Label.DISTRICTCODE"></c:set>
						<c:set var="selectedLevel" value="${DISTRICT_CONSTANT}"></c:set>						
					</c:if> 
					<!-- Set Page Level Heading and element Id(s) Ends Here-->
					
					<!-- Block for Show General Details of Drafted Local Government Body. -->
					<div id="divCenterAligned" class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4><spring:message htmlEscape="true"  code="Label.GENERALLOCALGOVTBODYDETAILS"></spring:message></h4>
								<ul class="form_body" >
									<c:if test="${localBodyForm.isdisturbed}">
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
											<spring:message htmlEscape="true"  code="Label.LOCALBODYNAMEENGLISH"></spring:message>
									    </label>
									    <label >
									    	<c:out value="${localBodyForm.localBodyNameEnglish}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYNAMELOCAL"></spring:message>
										</label>
										<label >
											<c:out value="${localBodyForm.localBodyNameLocal}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message>
										</label>
										<label>
											<c:out value="${localBodyForm.localBodyAliasEnglish}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message>
										</label>
										<label >
											<c:out value="${localBodyForm.localBodyAliasLocal}" escapeXml="true"></c:out>
									    </label>
									</li>
								</ul>	
							</div>
						</div>
					</div>
					<br/>
					<!-- Block for Show General Details of Drafted Local Government Body Ends. -->	
					
					<div class="form_block">
						<div class="col_1">
							<h4><spring:message htmlEscape="true" code="Label.CORRECTLOCALBODY"></spring:message></h4>
							
							<!-- Div Container For Current Coverred Area of Local Body -->
							<div class="col_1">
								<!-- Blank heading being used in GUI -->
								<!-- <h4></h4> -->
								<ul class="form_body">
									<li>
										<table class="data_grid" width="50%">
											<thead>
												<tr>
													<td colspan="2"><strong><spring:message htmlEscape="true" code="${lblHeadingCurrentCoverage}"></spring:message></strong></td>
												</tr>
												<tr>
													<th width="10%"><spring:message htmlEscape="true" code="${lblColumnHeadCode}"></spring:message></th>
													<th width="80%"><spring:message htmlEscape="true" code="${lblColumnHeadName}"></spring:message></th>													
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${completedCoverageDetails}" var="completedCoverages">
													<c:if test="${completedCoverages.landRegionType eq  selectedLevel}">
														<tr id="tr_${completedCoverages.landRegionCode}">
															<td><c:out value="${completedCoverages.landRegionCode}"></c:out></td>
															<td><c:out value="${completedCoverages.landRegionNameEnglish}"></c:out></td>															
														</tr>
													</c:if>
												</c:forEach>
											</tbody>
										</table>
									</li>
									<c:if test="${!isDistrictLevel}">
									<li>
										<table class="data_grid" width="50%">
											<thead>
												<tr>
													<td colspan="2"><strong><spring:message htmlEscape="true" code="Label.AVAILABLEFORCURRENTVILL"></spring:message></strong></td>
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
									</li>
									</c:if>
								</ul>
							</div>
							<!-- Div Container For Current Coverred Area of Local Body Ends Here -->
						</div>
					</div>
					<br/>	
						
						
						
							
					<!-- This Block used for Covered area of Local Body Started -->	
					<div class="form_block">
						<div class="col_1">
							<h4><spring:message htmlEscape="true"  code="Label.COVEREDAREAOFNEWLCLBDY"></spring:message></h4>							
							<!-- Div Container For New Coverred Area of Local Body -->
							<div class="col_1">
								<!-- Blank heading being used in GUI -->
								<!-- <h4></h4> -->
								<ul class="form_body">
									<li>
										<table class="data_grid" width="50%">
											<thead>
												<tr>
													<td colspan="2"><strong><spring:message htmlEscape="true" code="${lblHeadingCurrentCoverage}"></spring:message></strong></td>
												</tr>
												<tr>
													<th width="10%"><spring:message htmlEscape="true" code="${lblColumnHeadCode}"></spring:message></th>
													<th width="80%"><spring:message htmlEscape="true" code="${lblColumnHeadName}"></spring:message></th>													
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${completedCoverageDetails}" var="completedCoverages">
													<c:if test="${completedCoverages.landRegionType eq  selectedLevel}">
														<tr id="tr_${completedCoverages.landRegionCode}">
															<td><c:out value="${completedCoverages.landRegionCode}"></c:out></td>
															<td><c:out value="${completedCoverages.landRegionNameEnglish}"></c:out></td>															
														</tr>
													</c:if>
												</c:forEach>
											</tbody>
										</table>
									</li>
									<c:if test="${!isDistrictLevel}">
									<li>
										<table class="data_grid" width="50%">
											<thead>
												<tr>
													<td colspan="2"><strong><spring:message htmlEscape="true" code="Label.AVAILABLEFORCURRENTVILL"></spring:message></strong></td>
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
									</li>
									</c:if>
								</ul>
							</div>
							<!-- Div Container For New Coverred Area of Local Body Ends Here -->
							
							<br/>
						
					</div>
				</div>
				<br/>
				<!-- Block for Covered area of Local Body Ends Here -->
				
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
					<input class="bttn" id="btnFormActionPublish" type="button" value="<spring:message htmlEscape="true"  code="Button.SP"/>" param="publishSinleDraftedChangeCoverageLB"/>
					<input class="bttn" id="btnFormActionDelete" type="button" value="Delete" param="deleteSingleDraftedChangeCoverageLB" />
					<input class="bttn" id="btnFormActionClose" type="button" value="Close" param="home"/>
						
			</form:form>	
		</div>
	</div>
	<!-- Page Content ends -->
	<!-- </div> -->
			
</div>
<!-- Main Form Stylings ends -->

</body>
</html>

