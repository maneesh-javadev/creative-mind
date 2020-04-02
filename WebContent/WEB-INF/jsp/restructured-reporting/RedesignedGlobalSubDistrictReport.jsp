<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="CommonReportingIncludeJS.jsp"%>
<script type="text/javascript" class="init">
$(document).ready(function() {
	$("[id^=searchBy]").change(function() {
		$('#' + $(this).attr('paramShow')).show();
		$('#' + $(this).attr('paramHide')).hide();
		$('#entityCode').val('');
		$('#entityName').val('');
		$('#ddSourceState').val('');
		$('#ddSourceDistrict option[value != ""]').remove();
	});
	$( '#actionFetchDetails' ).click(function() {
 		validationForm();
 		var stateName = $("#ddSourceState option:selected").text();
 		var districtName = $("#ddSourceDistrict option:selected").text();
 		$("#entitesForMsg").val( districtName + "," + stateName);
	});
	$("#ddSourceState").change(function() {
		$('#ddSourceDistrict option[value != ""]').remove();
		if(isEmptyObject($(this).val()))return false;
		buildDistrict($(this).val());
	});
});

var validationForm = function (){
	$("#entityCode" ).rules( "add", {
		  customMandate : true, messages: {customMandate : "Please Enter Sub-District Code."}
	});
	$("#entityName" ).rules( "add", {
		  customMandate : true, messages: {customMandate : "Please Enter Sub-District Name."}
	});
 	$("#ddSourceState" ).rules( "add", {
		  customMandateState : true, messages: {customMandateState : "Please select a State."}
	});
 	/* $("#ddSourceDistrict" ).rules( "add", {
		  customMandateDist : true, messages: {customMandateDist : "Please select a District."}
	}); */
 	$("#captchaAnswer" ).rules( "add", {
 		  required : true, messages: {required : "Please enter the text shown above."}
	});
}
</script>
</head>
<body class="dt-example">
	<!-- Main Form Styling starts -->
	<div class="form_stylings">
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message htmlEscape="true" code="Label.VIEWSUBDIST"></spring:message></h3>
		</div>
		<div class="page_content">
			<div class="form_container">
				<form:form action="globalviewsubdistrict.do" method="POST" id="genericReportingEntity" commandName="genericReportingEntity">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="globalviewsubdistrict.do"/>" />
					<form:hidden path="entitesForMessage" id="entitesForMsg"/>
					<div id="dialogBX" style="display: none;">
						<iframe id="myIframe" width="910" height="650"></iframe>
					</div>
					<c:choose>
					<c:when test="${showSearchHierarchy}">
						<div id="divCenterAligned" class="form_stylings">
							<div class="form_block">
								<div class="col_1">
									<h4><spring:message htmlEscape="true" code="Label.FILTEROPTFORVIEWSUBDIST"></spring:message></h4>
									<ul class="form_body" >
										<li>
											<form:radiobutton path="searchCriteriaType" id='searchByName' value="N" checked="checked" paramShow="displayNameCode" paramHide="displayHierarchy"/>
											<spring:message code="Label.SEARCHBYNAME"/>&nbsp;&nbsp;
									
											<form:radiobutton path="searchCriteriaType" id='searchByHierarchy' value="P" paramShow="displayHierarchy" paramHide="displayNameCode"/>
											<spring:message code="Label.SEARCHBYHIERARCHY"/>
										</li>
									</ul>
									<h4><!-- Used header for blank head, please dont remove.  --></h4>
									<ul class="form_body" >
										<div id="displayNameCode">
											<li>
												<label><spring:message htmlEscape="true" code="Label.SUBDISTRICTCODE"></spring:message></label>
												<form:input id="entityCode" path="paramEntityCode" />
											</li>
											<li>
												<label><spring:message htmlEscape="true" code="Label.SUBDISTRICTNAMEENGLISH"></spring:message></label>
												<form:input id="entityName" path="paramEntityName" />
											</li>
										</div>
										<div id="displayHierarchy" style="display: none;">
											<li>
												<label for="ddSourceState"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message><span class="mandate">*</span></label>
												<form:select path="paramStateCode" class="combofield" id="ddSourceState" onchange="error_remove();">
													<form:option value="">
														<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
													</form:option>
													<form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
												</form:select>
											</li>
											<li>
												<label for="ddSourceDistrict"><spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message></label>
												<form:select path="paramDistrictCode" class="combofield" id="ddSourceDistrict">
													<form:option value="">All</form:option>
												</form:select> 
											</li>
										</div>
										<li>
											<label><!-- Used Label, please dont remove. --></label>
											<img src="captchaImage" alt="Captcha Image" />
										</li>
										<li>
											<label>
												<spring:message code="captcha.message"	htmlEscape="true" />
												<span class="mandate">*</span>
											</label>
											<form:input	path="captchaAnswer" id="captchaAnswer" autocomplete="off" />
											<c:if test="${not empty captchaFaliedMessage and not captchaFaliedMessage}">
												<br/><label><!-- Used Label, please dont remove. --></label>
												<span class="errormsg"><strong><spring:message code="captcha.errorMessage"/></strong></span>
											</c:if>
										</li>
										<li>
										    <label class="inline">&nbsp;</label>
											<input  type="submit" id="actionFetchDetails" value="<spring:message htmlEscape="true" code="Button.GETDATA"/>" />
											<input  type="button" value="<spring:message code="Button.CLOSE"/>" onclick="javascript:location.href='welcome.do'"/>	
										</li>
									</ul>	
								</div>
							</div>
						</div>
					</c:when>
				    <c:otherwise>
					    <div class="form_stylings">
							<div class="form_block" id="divData">
								<div class="col_1">
									<h4>
										<c:choose>
											<c:when test="${radioButton eq 'P'}">
												<spring:message code="Label.subdist.hierarchy.message" arguments="${genericReportingEntity.entitesForMessage}" argumentSeparator=","></spring:message>		
											</c:when>
											<c:otherwise>Sub-District Details</c:otherwise>
										</c:choose>
									</h4>
									<ul class="form_body" >
										<li>
											<table id="example2" class="display" cellspacing="0" width="100%">
												<thead>
													<tr>
														<th><spring:message code="Label.SNO"></spring:message></td>
														<th><spring:message code="Label.SUBDISTRICTCODE"></spring:message></th>
														<th><spring:message code="Label.SUBDISTRICTNAMEENGLISH"></spring:message></th>
														<th><spring:message code="Label.SUBDISTRICTNAMELOCAL"></spring:message></th>
														<th> Hierarchy</th>
														<th><spring:message code="Label.CENSUS2001"></spring:message></th>
														<th><spring:message code="Label.CENSUSCODE2011"></spring:message></th>
														<th><spring:message code="Label.PESA_STATUS" text="Pesa Status"></spring:message></th>
														<th><spring:message code="Label.VIEW.DETAIL"></spring:message></th>
														<th><spring:message code="Label.VIEW.History" text="View History"></spring:message></th>		
														<th><spring:message code="Label.VIEW.GOVERNMENTORDER"></spring:message></th>
														<th><spring:message code="Label.VIEW.MAP"></spring:message></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="listSubDistrictDetails" varStatus="rowstatus" items="${SEARCH_SUB_DISTRICT_RESULTS_KEY}">
															<tr >
																<td width="5%"><c:out value="${rowstatus.count}"/></td>
																<td><c:out value="${listSubDistrictDetails.entityCode}"></c:out></td>
																<td align="left"><c:out value="${listSubDistrictDetails.entityNameEnglish}"></c:out></td>
																<td align="left"><c:out value="${listSubDistrictDetails.entityNameLocal}"></c:out></td>
																<td align="left"><c:out value="${listSubDistrictDetails.entityHierarchy}"></c:out></td>
																<td align="left"><c:out value="${listSubDistrictDetails.census2001Code}"></c:out></td>
																<td align="left"><c:out value="${listSubDistrictDetails.census2011Code}"></c:out></td>
																<td align="left">
															   		<c:choose>
																 		<c:when test="${listSubDistrictDetails.isPesa eq 'P'}">
																	 		<spring:message htmlEscape="true" code="Label.PESA_PARTIALLY_COVERED" text="Partially Covered"/>
																 		</c:when>
																  		<c:when test="${listSubDistrictDetails.isPesa eq 'F'}">
																  			<spring:message htmlEscape="true" code="Label.PESA_FULLY_COVERED" text="Fully Covered"/>
																 		</c:when>
																 		<c:otherwise>
																  			<spring:message htmlEscape="true" code="Label.PESA_NOT_COVERED" text="Not Covered"/>
																 		</c:otherwise>
																	</c:choose>
																</td>
													     		<td width="8%" align="center">
													     			<img src="images/view.png" width="18" height="19" border="0" alt="View Details" 
													     			 onclick="javascript:viewEntityDetailsInPopup('${listSubDistrictDetails.entityCode}', 'globalviewSubDistrictDetail.do', 'globalsubdistrictId');"/>
																</td>
																<td width="8%" align="center">
																	<img src="images/history.png" width="18" height="19" border="0" alt="View Details"  
																	 onclick="javascript:viewEntityDetailsInPopup('${listSubDistrictDetails.entityCode}', 'viewSubDistrictHistoryReport.do', 'globalsubdistrictId');"/>
																</td>
																<td width="10%" align="center">
																	<c:if test="${not empty listSubDistrictDetails.fileTimestamp}">
																		<img src="images/gvt.order.png" onclick="javascript:retrieveFile1('${listSubDistrictDetails.entityCode}', 'T');" 
																			 width="18" height="19" border="0" alt="View Details"/>
															    	</c:if>
														    	</td>
													   			<td width="3%" align="center">
																	<c:if test="${not empty listSubDistrictDetails.isMapUpload and  !listSubDistrictDetails.isMapUpload}">
													   					<img alt="Map" src="images/showMap.jpg" onclick="javascript:displayMap('${listSubDistrictDetails.census2011Code}',3,'L',null,null);" width="18" height="19" border="0" />
													   				</c:if>
																</td>
															</tr>
														</c:forEach>
													</tbody>
											</table>
										</li>
									</ul>	
								<!-- </div>
							</div> -->
						</div>
						<div class="buttons center" id="showbutton">
							<input type="button" value="<spring:message code="Button.BACK"/>" onclick="javascript:location.href='globalviewsubdistrictforcitizen.do?<csrf:token uri='globalviewsubdistrictforcitizen.do'/>'"/>
							<input type="button" value="<spring:message code="Button.CLOSE"/>" onclick="javascript:location.href='welcome.do'"/>
						</div>
					</c:otherwise>
					</c:choose>
				</form:form>
			</div>
		</div>
	</div>
 	<script type='text/javascript'>
	$(window).load(function () {
		var radioObj = $('input:radio[name="searchCriteriaType"]').filter('[value="<c:out value='${radioButton}'/>"]') ;
		$('#' + radioObj.attr('paramShow')).show();
		$('#' + radioObj.attr('paramHide')).hide();
		if(isParseJson('<c:out value="${not empty captchaFaliedMessage and not captchaFaliedMessage}"/>') &&
		   isParseJson('<c:out value="${not empty genericReportingEntity.paramStateCode}"/>')){
			buildDistrict('<c:out value="${genericReportingEntity.paramStateCode}"/>');
			setTimeout(function(){
				$("#ddSourceDistrict option[value='<c:out value="${genericReportingEntity.paramDistrictCode}"/>']").attr("selected", "selected");	
			}, 200);	
		}
	});
</script>		
</body>
</html>