<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>
<c:set var="DISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.DISTRICT_CONSTANT.toString()%>"></c:set>
<c:set var="SUBDISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.SUB_DISTRICT_CONSTANT.toString()%>"></c:set>
<c:set var="VILLAGE_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.VILLAGE_CONSTANT.toString()%>"></c:set>

<script type="text/javascript" language="javascript" class="init">
	$(function () {
	    $(document).keydown(function (e) {
	    	return (e.which || e.keyCode) != 116;
	    });
	});
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
			<h3><spring:message code="Label.published.lb.details" htmlEscape="true"></spring:message></h3>
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
					<!-- Block for General Details of Drafted Local Government Body. -->
					<div class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4><spring:message code="Label.NewLBCreated" htmlEscape="true"></spring:message></h4>
								<ul class="form_body" >
									<li>
										<table class="data_grid" width="100%">
											<thead>
												<tr>
													<th><spring:message code="Label.LOCALBODYCODE" htmlEscape="true"/></th>
													<th><spring:message code="Label.LOCALBODYVERSION" htmlEscape="true"/></th>
													<th><spring:message	code="Label.LOCALBODYNAMEENGLISH" htmlEscape="true"/></th>
													<th><spring:message code="Label.LOCALBODYNAMELOCAL" htmlEscape="true"/></th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td><c:out value="${LocalBodyDetails.localBodyCode}"></c:out></td>
													<td><c:out value="${LocalBodyDetails.childCount}"></c:out></td><!-- Child count Used to display Local Body Version -->
													<td><c:out value="${LocalBodyDetails.localBodyNameEnglish}"></c:out></td>
													<td><c:out value="${LocalBodyDetails.localBodyNameLocal}"></c:out></td>
												</tr>
											</tbody>
										</table>
									</li>	
								</ul>	
							</div>
						</div>
					</div>
					<br/>
					<!-- Block for General Details of Drafted Local Government Body Ends. -->	
					<c:set var="districtAvailable"></c:set>
					<c:set var="subDistrictAvailable" value=""></c:set>
					<c:set var="villageAvailable"></c:set>
					<c:forEach var="coverages" items="${LandRegionCoverges}">
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
													<c:forEach var="listContributingDistrict" items="${LandRegionCoverges}">
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
													<c:forEach var="listContributingSubDistrict" items="${LandRegionCoverges}">
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
													<c:forEach var="listContributingVillage"	items="${LandRegionCoverges}">
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
					<input class="bttn" id="btnFormActionClose" type="button" value="Close" param="home"/>
				</form:form>
			</div>
		</div>
		<!-- Page Content ends -->
	</div>
	<!-- Main Form Styling ends -->
</body>
</html>