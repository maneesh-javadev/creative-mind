<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../common/taglib_includes.jsp"%>

<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=contextpthval%>/resources-localbody/css/jquery.dataTables.css">	
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/resources-localbody/scripts/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>

<script type="text/javascript" class="init">
	
	
	$(document).ready(function() {
		$('#example').DataTable({
			"bJQueryUI" : false
		});

		$("[id^=searchBy]").change(function() {
			$('#' + $(this).attr('paramShow')).show();
			$('#' + $(this).attr('paramHide')).hide();
			$('#entityCode').val('');
			$('#entityName').val('');
			$('#ddSourceState').val('');
			$('.errormsg').empty();

		});
		var showDiv = '${showSearchHierarchy}';
		if (!showDiv) {
			$('#showDiv').hide();
		}
		var radioValue = '${radioButton}';
		if (radioValue == 'P') {
			$('#displayHierarchy').show();
			$('#displayNameCode').hide();
		}

	});


	function hideHierarchy() {
		$("#changeSearch").remove();
		$('#showDiv').show();
		$('#divData').remove();

	}
</script>
</head>
<body class="dt-example">
	<!-- Main Form Styling starts -->
	<!-- Main Form Styling starts -->
	<div class="form_stylings">
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message htmlEscape="true" code="Label.VIEWDIST"></spring:message></h3>
			<ul class="item_list">
				<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		
		<div class="page_content">
			<div class="form_container">
				<form:form action="globalviewdistrict.do" method="POST" id="form1" commandName="districtEntity">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="globalviewdistrict.do"/>" />
					<c:if test="${not empty SEARCH_DISTRICT_RESULTS_KEY}">
						<input type="button" id="changeSearch" value="CHANGE SEARCH" onclick="hideHierarchy()" />
					</c:if>
				
					<div id="showDiv">
					<div id="divCenterAligned" class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4><spring:message htmlEscape="true" code="Label.VIEWDIST"></spring:message></h4>
								<ul class="form_body" >
									<li>
										<form:radiobutton path="searchCriteriaType" id='searchByName' value="N" checked="checked" paramShow="displayNameCode" paramHide="displayHierarchy"/>
										<spring:message code="Label.SEARCHBYNAME"/>&nbsp;&nbsp;
								
										<form:radiobutton path="searchCriteriaType" id='searchByHierarchy' value="P" paramShow="displayHierarchy" paramHide="displayNameCode"/>
										<spring:message code="Label.SEARCHBYHIERARCHY"/>
									</li>
								</ul>	
							</div>
						</div>
					</div>
					<br/>
					<div id="divCenterAligned" class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4>
									Search Criteria
								</h4>
								
								<ul class="form_body" >
									<div id="displayNameCode">
										<li>
											<label><spring:message htmlEscape="true" code="Label.DISTRICTCODE"></spring:message></label>
											<form:input id="entityCode" path="paramEntityCode" />
										</li>
										<li>
											<label><spring:message htmlEscape="true" code="Label.DISTRICTNAMEINENGLISH"></spring:message></label>
											<form:input id="entityName" path="paramEntityName" />
										</li>
									</div>
									<div id="displayHierarchy" style="display: none;">
									<li>
										<label for="ddSourceState"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message></label>
										<form:select path="paramStateCode" class="combofield" id="ddSourceState" onchange="error_remove();">
											<form:option value="">
												<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
											</form:option>
											<form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
										</form:select>
										<div id="errSelectStateName" class="errormsg"></div>
									</li>
									</div>
									
									<li>
										<label></label>
										<img src="captchaImage" alt="Captcha Image" />
									</li>
									<li>
										<label>
											<spring:message code="captcha.message"	htmlEscape="true" />
											<span class="mandate">*</span>
										</label>
										<form:input	path="captchaAnswer" id="captchaAnswer" autocomplete="off" />
										<div class="errormsg">
										<c:if test="${not empty captchaFaliedMessage and not captchaFaliedMessage}">
											<spring:message code="captcha.errorMessage" htmlEscape="true" />
										</c:if>
									</div>										
									</li>
									
									
									<li>
									    <label class="inline">&nbsp;</label>
										<input class="bttn" type="submit" id="actionFetchDetails" value="<spring:message htmlEscape="true" code="Button.GETDATA"/>" />
										<input class="bttn" type="button" id="close" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" />	
									</li>
								</ul>	
							</div>
						</div>
					</div>
					</div>

					<div class="errormsg" align="center">
						<c:if test="${noDataFound}">
							<c:out value="No Data Found" />
						</c:if>
					</div>

					<c:if test="${not empty SEARCH_DISTRICT_RESULTS_KEY}">
				
				    <div id="divCenterAligned" class="form_stylings">
						<div class="form_block" id="divData">
							<div class="col_1">
								<h4>
									District
								</h4>
								<ul class="form_body" >
									<li>
										<table id="example" >
											<thead>
												<tr>
													<th ><spring:message code="Label.SNO"/></th>
													<th ><spring:message code="Label.DISTRICTCODE"/></th>
													<th ><spring:message code="Label.DISTRICTNAMEINENGLISH"/></th>
													<th ><spring:message code="Label.DISTRICTNAMEINLOCAL"/></th>
													<th ><spring:message code="Label.SHORTNAMEOFDISTRICT"/></th>
													<th ><spring:message code="Label.CENSUS2011"/></th>
													<th ><spring:message code="Label.PESA_STATUS" text="Pesa Status"/></th>
													<th align="center"><spring:message htmlEscape="true" code="Label.VIEW.DETAIL"/></th>
													<th align="center"><spring:message htmlEscape="true" code="Label.VIEW.History" text="View History"/></th>		
													<th align="center"><spring:message htmlEscape="true" code="Label.VIEW.GOVERNMENTORDER"/></th>
													<th align="center"><spring:message htmlEscape="true" code="Label.VIEW.MAP"/></th>
												</tr>
											</thead>
											<c:forEach var="listDistrictDetails" varStatus="rowstatus" items="${SEARCH_DISTRICT_RESULTS_KEY}">
												<tbody>
												<tr >
													<td width="6%"><c:out value="${rowstatus.count}"/></td>
													<td><c:out value="${listDistrictDetails.entityCode}"></c:out></td>
													<td align="left"><c:out value="${listDistrictDetails.entityNameEnglish}"></c:out></td>
													<td align="left"><c:out value="${listDistrictDetails.entityNameLocal}"></c:out></td>
													<td align="left"><c:out value="${listDistrictDetails.entityNameShort}"></c:out></td>
													<td align="left"><c:out value="${listDistrictDetails.census2011Code}"></c:out></td>
													<td align="left">
													   <c:choose>
														 <c:when test="${listDistrictDetails.isPesa eq 'P'}">
															 <spring:message htmlEscape="true" code="Label.PESA_PARTIALLY_COVERED" text="Partially Covered"/>
														 </c:when>
														  <c:when test="${listDistrictDetails.isPesa eq 'F'}">
														  	<spring:message htmlEscape="true" code="Label.PESA_FULLY_COVERED" text="Fully Covered"/>
														 </c:when>
														 <c:otherwise>
														  	<spring:message htmlEscape="true" code="Label.PESA_NOT_COVERED" text="Not Covered"/>
														 </c:otherwise>
														</c:choose>
													</td>
											     	<td width="8%" align="center">
											     		<img src="images/view.png" onclick="javascript:viewVillageDetails('${listDistrictDetails.entityCode}');"
															 width="18" height="19" border="0" alt="View Details" />
													</td>
													<td width="8%" align="center">
														<img src="images/history.png" onclick="javascript:viewVilageDetailsforVillageVersion('${listDistrictDetails.entityCode}');"
															 width="18" height="19" border="0" alt="View Details" />
													</td>
													<td width="10%" align="center">
														<c:if test="${not empty listDistrictDetails.fileTimestamp}">
															
																<img src="images/gvt.order.png" onclick="javascript:retrieveFile1('${listDistrictDetails.entityCode}');" 
																	 width="18" height="19" border="0" alt="View Details"/>
													    </c:if>
												    </td>
												   <td width="3%" align="center">
														<c:if test="${listDistrictDetails.isMapUpload != null}">
	                        								<c:choose>
																<c:when test="${!listDistrictDetails.isMapUpload}">
																	<img alt="Map" src="images/showMap.jpg" onclick="javascript:displayMap('${listDistrictDetails.census2011Code}');" width="18" height="19" border="0" />
																</c:when>
																<c:when test="${listDistrictDetails.isMapUpload}">
																	<a href="#" style="text-decoration: none;"></a><%--download --%>
																</c:when>
															</c:choose>
														</c:if>
													</td>
												</tr>
												</tbody>
											</c:forEach>
										</table>
									</li>
								</ul>	
							</div>
						</div>
					</div>
				</c:if>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>