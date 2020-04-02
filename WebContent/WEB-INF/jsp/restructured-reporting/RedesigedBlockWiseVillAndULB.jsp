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
			$('#ddSourceSubDistrict option[value != ""]').remove();
		});
		$("#ddSourceState").change(function() {
		 	$('#ddSourceDistrict option[value != ""]').remove();
			$('#ddSourceSubDistrict option[value != ""]').remove();
			if(isEmptyObject($(this).val()))return false;
			buildDistrict($(this).val());
		});
		$("#ddSourceDistrict").change(function() {
			$('#ddSourceSubDistrict option[value != ""]').remove();
			if(isEmptyObject($(this).val()))return false;
			buildBlock($(this).val());
		});
		
		$( '#actionFetchDetails' ).click(function() {
			validationForm();
	 		var stateName = $("#ddSourceState option:selected").text();
	 		var districtName = $("#ddSourceDistrict option:selected").text();
	 		var blockName = $("#ddSourceBlock option:selected").text();
	 		$("#entitesForMsg").val( blockName + "," + districtName + "," + stateName);
		});
	});
	
	 var validationForm = function (){
	
	 	$("#ddSourceState" ).rules( "add", {
			  customMandateState : true, messages: {customMandateState : "Please select a State."}
		});
	 	$("#ddSourceDistrict" ).rules( "add", {
			  customMandateDist : true, messages: {customMandateDist : "Please select a District."}
		});
	 	$("#ddSourceBlock" ).rules( "add", {
	 		customMandateBlock : true, messages: {customMandateBlock : "Please select a Block."}
		});
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
			<h3><spring:message htmlEscape="true" code="Label.VIEWBLOCKWISEVILLAGESANDULB"></spring:message></h3>
		</div>
		<div class="page_content">
			<div class="form_container">
				<form:form action="globalviewblockwiseVillageandUlbforcitizen.do" method="POST" id="genericReportingEntity" commandName="genericReportingEntity">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="globalviewblockwiseVillageandUlbforcitizen.do"/>" />
					<form:hidden path="entitesForMessage" id="entitesForMsg"/>
					<div id="dialogBX" style="display: none;">
						<iframe id="myIframe" width="910" height="650"></iframe>
					</div>
					<c:choose>
					<c:when test="${showSearchHierarchy}">
						<div id="divCenterAligned" class="form_stylings">
							<div class="form_block">
								<div class="col_1">
									<h4><spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message></h4>
									<ul class="form_body" >
										<li>
											<label for="ddSourceState"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message></label>
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
												<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
											</form:select> 
										</li>
										<li>
											<label for="ddSourceBlock"><spring:message htmlEscape="true" code="Label.SELBLOCK"></spring:message></label>
											<form:select path="paramBlockCode" class="combofield" id="ddSourceBlock">
												<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
											</form:select> 
										</li>
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
										<spring:message code="Label.blockwise.village.and.ulb.hierarchy.message" text="Block Wise Villages or ULB of  {0} Block ( {1} - {2} )" arguments="${genericReportingEntity.entitesForMessage}" argumentSeparator=","></spring:message>
									</h4>
									<ul class="form_body" >
										<li>
											<table id="example" class="display" cellspacing="0" width="100%">
												<thead>
													<tr>
														<th><spring:message code="Label.SNO"/></th>
														<th><spring:message code="Label.ENTITYTYPE"/></th>
														<th><spring:message code="Label.ENTITYCODE"/></th>
														<th><spring:message code="Label.ENTITYNAMEENGLISH"/></th>
														<th><spring:message code="Label.ENTITYNAMEINLOCAL"/></th>
														<th><spring:message code="Label.VIEW"/></th>
														<th><spring:message code="Label.VIEW.GOVERNMENTORDER"/></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="obj" varStatus="rowstatus" items="${blockWiseEntityList}">
														<tr >
															<td width="6%"><c:out value="${rowstatus.count}"/></td>
															<td align="left"><c:out value="${obj.entityType}"></c:out></td>
															<td align="left"><c:out value="${obj.entityCode}"></c:out></td>
															<td align="left"><c:out value="${obj.entityNameEnglish}"></c:out></td>
															<td align="left"><c:out value="${obj.entityNameLocal}"></c:out></td>
															<td width="8%" align="center">
													     		<img src="images/view.png" width="18" height="19" border="0" alt="View Details" 
													     			 onclick="javascript:viewEntityDetailsInPopup('${obj.entityCode}', 
													     			 <c:choose>
													     			 			<c:when test="${obj.entityType eq 'Village'}">'globalviewVillageDetail.do','globalvillageId' </c:when>
													     			 			<c:otherwise>'globalviewLocalBodyDetail.do', 'id'</c:otherwise>
													     			 </c:choose>
			
													     			 );"/>
															</td>
															<td width="10%" align="center">
																<c:if test="${not empty obj.fileTimestamp}">
																		<img src="images/gvt.order.png" onclick="javascript:retrieveFile1('${obj.entityCode}',
																		  <c:choose>
													     			 			<c:when test="${obj.entityType eq 'Village'}">'V' </c:when>
													     			 			<c:otherwise>'G'</c:otherwise>
													     			 	</c:choose>	
																		 );" 
																			 width="18" height="19" border="0" alt="View Details"/>
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
							<input type="button" value="<spring:message code="Button.BACK"/>" onclick="javascript:location.href='globalviewvillageforcitizen.do?<csrf:token uri='globalviewvillageforcitizen.do'/>'"/>
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
			
			if(!isEmptyObject('${genericReportingEntity.paramStateCode}') && !isEmptyObject('${genericReportingEntity.paramDistrictCode}') && !isEmptyObject('${genericReportingEntity.paramBlockCode}')){
				buildDistrict('<c:out value="${genericReportingEntity.paramStateCode}"/>');
				setTimeout(function(){
					$("#ddSourceDistrict option[value='<c:out value="${genericReportingEntity.paramDistrictCode}"/>']").attr("selected", "selected");
					buildBlock('<c:out value="${genericReportingEntity.paramDistrictCode}"/>');
					setTimeout(function(){
						$("#ddSourceBlock option[value='<c:out value="${genericReportingEntity.paramBlockCode}"/>']").attr("selected", "selected");
					}, 200);
				}, 200);
			}
			
			
		});
	</script>
	
</body>
</html>