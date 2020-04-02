<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="CommonReportingIncludeJS.jsp"%>
<script type="text/javascript" class="init">
	$(document).ready(function() {
		$( '#actionFetchDetails' ).click(function() {
	 		validationForm();
	 		$("#entitesForMsg").val($("#ddSourceState option:selected").text());
	 		
		});
	});
	
	var validationForm = function (){
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
			<h3><spring:message code="Label.VIEWSTATE" htmlEscape="true"></spring:message></h3>
			<%-- <ul class="item_list">
				<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li>
			</ul> --%>
		</div>
		<div class="page_content">
			<div class="form_container">
				<form:form action="globalViewStateRepport.do" method="POST" id="genericReportingEntity" commandName="genericReportingEntity">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="globalViewStateRepport.do"/>" />
					<form:hidden path="entitesForMessage" id="entitesForMsg"/>
					<div id="dialogBX" style="display: none;">
						<iframe id="myIframe" width="910" height="650"></iframe>
					</div>
					<c:choose>
					<c:when test="${showSearchHierarchy}">
						<div id="divCenterAligned" class="form_stylings">
						<div id="divCenterAligned" class="form_stylings">
							<div class="form_block">
								<div class="col_1">
									<%-- <h4>
										<spring:message htmlEscape="true"  code="Label.SEARCHCRITERIA"></spring:message>
									</h4> --%>
									
									<ul class="form_body" >
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
										<spring:message htmlEscape="true" code="Label.VIEWSTATELIST"></spring:message>
									</h4>
									<ul class="form_body" >
										<li>
											<table id="example1" class="display" cellspacing="0" width="100%">
												<thead>
													<tr>
														<th ><spring:message code="Label.SNO"/></th>
														<th ><spring:message code="Label.STATECODE"/></th>
														<th ><spring:message code="Label.STATENAMEINENGLISH"/></th>
														<th ><spring:message code="Label.STATENAMEINLOCAL"/></th>
														<th ><spring:message code="Label.CENSUS2001"/></th>
														<th ><spring:message code="Label.CENSUSCODE2011"/></th>
														<th ><spring:message htmlEscape="true" code="Label.VIEW.DETAIL"/></th>
														<th ><spring:message htmlEscape="true" code="Label.VIEW.History" text="View History"/></th>		
														<th ><spring:message htmlEscape="true" code="Label.VIEW.GOVERNMENTORDER"/></th>
														<th ><spring:message htmlEscape="true" code="Label.VIEW.MAP"/></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="listStateDetails" varStatus="rowstatus" items="${SEARCH_STATE_RESULTS_KEY}">
														<tr >
															<td width="6%"><c:out value="${rowstatus.count}"/></td>
															<td><c:out value="${listStateDetails.entityCode}"></c:out></td>
															<td align="left"><c:out value="${listStateDetails.entityNameEnglish}"></c:out></td>
															<td align="left"><c:out value="${listStateDetails.entityNameLocal}"></c:out></td>
															<td align="left"><c:out value="${listStateDetails.census2001Code}"></c:out></td>
															<td align="left"><c:out value="${listStateDetails.census2011Code}"></c:out></td>
															<td width="8%" align="center">
													     		<img src="images/view.png" width="18" height="19" border="0" alt="View Details" 
													     			 onclick="javascript:viewEntityDetailsInPopup('${listStateDetails.entityCode}', 'globalviewStateDetail.do', 'globalstateId');"/>
															</td>
															<td width="8%" align="center">
																<img src="images/history.png" width="18" height="19" border="0" alt="View Details"  
																	 onclick="javascript:viewEntityDetailsInPopup('${listStateDetails.entityCode}', 'viewStateHistoryReport.do', 'globalstateId');"/>
															</td>
															<td width="10%" align="center">
																<c:if test="${not empty listStateDetails.fileTimestamp}">
																		<img src="images/gvt.order.png" onclick="javascript:retrieveFile1('${listStateDetails.entityCode}', 'S');" 
																			 width="18" height="19" border="0" alt="View Details"/>
															    </c:if>
														    </td>
													   		<td width="3%" align="center">
													   		<c:choose>
													   			<c:when test="${listStateDetails.entityCode==36}">
													   		       <c:if test="${not empty listStateDetails.isMapUpload and  !listStateDetails.isMapUpload}">
													   				<img alt="Map" src="images/showMap.jpg" onclick="javascript:displayMap('${listStateDetails.entityCode}',1,'L',null,null);" width="18" height="19" border="0" />
													   			</c:if>        
													   			</c:when>
													   			<c:otherwise>
													   				<c:if test="${not empty listStateDetails.isMapUpload and  !listStateDetails.isMapUpload}">
													   				<img alt="Map" src="images/showMap.jpg" onclick="javascript:displayMap('${listStateDetails.census2011Code}',1,'L',null,null);" width="18" height="19" border="0" />
													   				</c:if>
													   			</c:otherwise>
													   		</c:choose>
													   	
													   			
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</li>
									</ul>	
							</div>
						<div class="buttons center" id="showbutton">
							<input type="button" value="<spring:message code="Button.BACK"/>" onclick="javascript:location.href='globalviewstateforcitizen.do?<csrf:token uri='globalviewstateforcitizen.do'/>'"/>
							<input type="button" value="<spring:message code="Button.CLOSE"/>" onclick="javascript:location.href='welcome.do'"/>
						</div>
					</c:otherwise>
					</c:choose>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>