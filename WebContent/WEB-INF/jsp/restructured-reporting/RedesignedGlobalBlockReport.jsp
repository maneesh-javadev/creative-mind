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
			$('#entityCode, #entityName, #ddSourceState').val('');
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
		$("#entityCode").numeric({ decimal: false, negative: false }, function() {
			this.value = ""; this.focus(); 
		});
	});
	
	var validationForm = function (){
		$("#entityCode" ).rules( "add", {
			  customMandate : true, messages: {customMandate : "Please Enter Block Code."}
		});
		$("#entityName" ).rules( "add", {
			  customMandate : true, messages: {customMandate : "Please Enter Block Name."}
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
			<h3><spring:message htmlEscape="true"  code="Label.VIEWBLOCK"></spring:message></h3>
			<%-- <ul class="item_list">
				<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li>
			</ul> --%>
		</div>
		<div class="page_content">
			<div class="form_container">
				<form:form action="globalviewBlock.do" method="POST" id="genericReportingEntity" commandName="genericReportingEntity">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="globalviewBlock.do"/>" />
					<form:hidden path="entitesForMessage" id="entitesForMsg"/>
					<div id="dialogBX" style="display: none;">
						<iframe id="myIframe" width="910" height="650"></iframe>
					</div>
					<c:choose>
					<c:when test="${showSearchHierarchy}">
						<div id="divCenterAligned" class="form_stylings">
							<div class="form_block">
								<div class="col_1">
									<h4><spring:message htmlEscape="true" code="Label.FILTEROPTFORVIEWBLOCK"></spring:message></h4>
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
												<label><spring:message htmlEscape="true" code="Label.BLOCKCODE"></spring:message></label>
												<form:input id="entityCode" path="paramEntityCode" />
											</li>
											<li>
												<label><spring:message htmlEscape="true" code="Label.BLOCKNAMEINENGLISH"></spring:message></label>
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
											<input type="submit" id="actionFetchDetails" value="<spring:message htmlEscape="true" code="Button.GETDATA"/>" />
											<input type="button" value="<spring:message code="Button.CLOSE"/>" onclick="javascript:location.href='welcome.do'"/>	
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
												<spring:message code="Label.block.hierarchy.message" arguments="${genericReportingEntity.entitesForMessage}" argumentSeparator=","></spring:message>		
											</c:when>
											<c:otherwise>Block Details</c:otherwise>
										</c:choose>
									</h4>
									<ul class="form_body" >
										<li>
											<table id="example2" class="display" cellspacing="0" width="100%">
												<thead>
													<tr>
														<th><spring:message code="Label.SNO"/></th>
														<th><spring:message code="Label.BLOCKCODE"></spring:message></th>
														<th><spring:message code="Label.BLOCKNAMEINENGLISH"></spring:message></th>
														<th><spring:message code="Label.BLOCKNAMEINLOCAL"></spring:message></th>
														<th> Hierarchy</th>
														<th><spring:message code="Label.VIEW.DETAIL"></spring:message></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="listBlockDetails" varStatus="rowstatus" items="${SEARCH_BLOCK_RESULTS_KEY}">
															<tr >
																<td width="5%"><c:out value="${rowstatus.count}"/></td>
																<td><c:out value="${listBlockDetails.entityCode}"></c:out></td>
																<td><c:out value="${listBlockDetails.entityNameEnglish}"></c:out></td>
																<td><c:out value="${listBlockDetails.entityNameLocal}"></c:out></td>
																<td><c:out value="${listBlockDetails.entityHierarchy}"></c:out></td>
																<td width="8%" align="center">
													     			<img src="images/view.png" width="18" height="19" border="0" alt="View Details" 
													     			 onclick="javascript:viewEntityDetailsInPopup('${listBlockDetails.entityCode}', 'viewglobalBlockDetail.do', 'globalblockId');"/>
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
							<input type="button" value="<spring:message code="Button.BACK"/>" onclick="javascript:location.href='globalviewBlockforcitizen.do?<csrf:token uri='globalviewBlockforcitizen.do'/>'"/>
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