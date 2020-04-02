<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../common/taglib_includes.jsp"%>

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
			<h3><spring:message code="Statewise GIS Mapped LocalBody" htmlEscape="true"></spring:message></h3>
			<%-- <ul class="item_list">
				<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li>
			</ul> --%>
		</div>
		<div class="page_content">
			<div class="form_container">
				<form:form action="viewGlobalStateWiseLocalBodyReport.do" method="POST" id="reportingStatewiseGISMappedLBEntity" commandName="reportingStatewiseGISMappedLBEntity">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="viewGlobalStateWiseLocalBodyReport.do"/>" />
					<form:hidden path="entitesForMessage" id="entitesForMsg"/>
					<div id="dialogBX" style="display: none;">
						<iframe id="myIframe" width="910" height="650"></iframe>
					</div>
					<c:choose>
					<c:when test="${showSearchResult}">
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
									<%-- <h4>
										<spring:message htmlEscape="true" code="STATEWISE GIS MAPPED LOCAL BODY"></spring:message>
									</h4> --%>
									<ul class="form_body" >
										<li>
											<table id="example" class="data_grid" cellspacing="0" width="100%">
												<thead>
													<tr>
														<th ><spring:message code="SNO"/></th>
														<th ><spring:message code="STATE NAME"/></th>
														<th ><spring:message code="TOTAL LOCAL BODY"/></th>
														<th ><spring:message code="MAPPED LOCAL BODY"/></th>
														<th ><spring:message code="MAPPED PERCENT"/></th>
														
													</tr>
												</thead>
												<tbody>
												<c:set var = "sumLocalBody" value = "${0}"/>
												<c:set var = "sumTotalMappedLB" value = "${0}"/>
													<c:forEach var="listStateDetails" varStatus="rowstatus" items="${SEARCH_LOCALBODY_KEY}">
														<tr >
															<td width="6%"><c:out value="${rowstatus.count}"/></td>
															<td><c:out value="${listStateDetails.stateName}"></c:out></td>
															<td align="left"><c:out value="${listStateDetails.totalLocalBody}"></c:out></td>
															<td align="left"><c:out value="${listStateDetails.mappedLocalBody}"></c:out></td>
															<td align="left"><c:out value="${listStateDetails.mappedPercent}"></c:out></td>
															<c:set var = "sumLocalBody" value = "${sumLocalBody+listStateDetails.totalLocalBody}"/>
															<c:set var = "sumTotalMappedLB" value = "${sumTotalMappedLB+listStateDetails.mappedLocalBody}"/>
														</tr>
													</c:forEach>
												</tbody>
												<tr>
												         <th ></th>
												         <th ></th>
												         <th ><spring:message code="Grand Total : ${sumLocalBody}"/></th>
												         <th ><spring:message code="Grand Total: ${sumTotalMappedLB}"/></th>
												         <th ></th>
												</tr>
											</table>
										</li>
									</ul>
									 
									
									<ul class="blocklist center" style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
						              <li>
							                     <label><spring:message code="Label.URL" htmlEscape="true"></spring:message>
							                     <%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%> </label>
						             </li>
						             <li>
							                   <%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%>
						                        <label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message>								
				 			                    <%=df.format(new java.util.Date())%>  </label>
						           </li>
						           <li>
							                  <label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
						          </li>
					              </ul>
							</div>
						<div class="buttons center" id="showbutton">
							<input type="button" value="<spring:message code="Button.BACK"/>" onclick="javascript:location.href='globalViewStatewiseLocalBody.do?<csrf:token uri='globalViewStatewiseLocalBody.do'/>'"/>
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