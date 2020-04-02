<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="../../common/dwr.jsp"%>
<%@include file="updateGISBoundariesJs.jsp"%>


</head>
<body>
	<div class="form_stylings">
		<div class="header">
			<h3><spring:message htmlEscape="true" code="Update GIS Boundaries" /></h3>
			<!-- <ul class="item_list">
				<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li>
			</ul> -->
		</div>
		<div class="clear"></div>
		<div class="page_content">
		<div class="form_container">
			<form:form action="updateGISBoundaries.htm" method="POST" commandName="criteriaLocalBodies"  id="formCDraftedLB">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="updateGISBoundaries.htm"/>" />
						<form:hidden path="localBodyCode" id="paramLBCode"/>
						<form:hidden path="isGISCoverage" id="paramGisCoverage"/>
						<form:hidden path="localBodyCreationType" value="P" />
						<div id="dialogBX" style="display: none;">
						<iframe id="myIframe" width="910" height="650"></iframe>
					</div>
					<c:choose>
					<c:when test="${!searchResult}">
						<div id="divCenterAligned" class="form_stylings">
							<div class="form_block">
								<div class="col_1">
									<h4>Search Criteria</h4>
									<ul class="form_body" >
										
											
										
										<li style="display:none;">
											<label>
												<spring:message code='Label.SELECTLOCALBODYTYPE' htmlEscape='true'/>
												<span class="mandate">*</span>
											</label>
											<form:select path="paramLocalBodyTypeCode" id="localBodyType">
												<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT" ></spring:message></form:option>
												
													 <c:forEach items="${localBodyTypeList}" var="objLBTypes">\
													 <c:set var="optValue" value="${objLBTypes.localBodyTypeCode}_${objLBTypes.tierSetupCode}_${objLBTypes.parentTierSetupCode}_${objLBTypes.lbLevel}" />
													 
													<c:choose>
													<c:when test="${objLBTypes.localBodyTypeCode eq 3}">
													<form:option selected="true" value="${optValue}">
															<c:out value="${objLBTypes.name}" escapeXml="true"></c:out>
														</form:option>
													</c:when>
													<c:otherwise>
													<form:option value="${optValue}">
															<c:out value="${objLBTypes.name}" escapeXml="true"></c:out>
														</form:option>
													</c:otherwise>
													</c:choose>
														
													</c:forEach> 
												</form:select>
											<span class="errormessage" id="errorLocalBodyType"></span>
										</li>
										<div id="divCreateDynamicHierarchy">
											<!-- This Div used to generate Hierarchy -->
										</div>
										
										<li>
										    <label class="inline">&nbsp;</label>
											<input class="bttn" type="button" id="actionFetchLBDetails" value="Fetch Local Bodies" />
											<input class="bttn" type="button" id="actionSearchClose" value="Close" />	
										</li>
									</ul>	
								</div>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<!-- Block for Drafted Local Government Body Details in Tabular Format. -->
					<div class="form_block">
						<div class="col_1 overflow_horz">
							<h4>Local Government Body Details</h4>
							<ul class="form_body">
								<li>
									<table id="example" class="display" cellspacing="0" width="100%">
										<thead>
											<tr>
												
												<th><spring:message code="Label.SNO" htmlEscape="true"></spring:message></th>
												<th><spring:message code="Label.LOCALBODYCODE" htmlEscape="true"></spring:message></th>
												<th><spring:message code="Label.LOCALBODYNAMEINENG" htmlEscape="true"></spring:message></th>
												
												
												<th>Update GIS Boundaries</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="objects" items="${publishedLocalBodies}" varStatus="counter">
												<c:if test="${not objects.isDrafted}">
												<tr id="trdetials">
													
													<td align="center"><c:out value="${counter.count}" escapeXml="true"></c:out></td>
													<td align="center" ><c:out value="${objects.localBodyCode}" escapeXml="true"></c:out></td>
													<td style="word-break:break-all"><c:out value="${objects.localBodyNameEnglish}" escapeXml="true"></c:out></td>
													<td align="center">
														
															<img id ="gisMapView" src="images/showMap.jpg" width="18" height="19" border="0" paramGPCode="${objects.localBodyCode}" paramLBNameEng="${objects.localBodyNameEnglish}" onclick="callGISMapView('${objects.localBodyCode}','${objects.localBodyNameEnglish}',false,false)" />
														
													</td>
												</tr>	
												</c:if>
											</c:forEach>
										</tbody>
									</table>
									
								</li>
								<il >
								<center><input class="bttn" type="button" id="actionClose" value="Close" />	</center>
								</il>
							</ul>
						</div>
					</div>
					<!-- Block for Drafted Local Government Body Details in Tabular Format Ends Here. -->
					<br/>
					</c:otherwise>
					</c:choose>
					
					
						
					
					
			</form:form>
		</div>
		

	<script src="/LGD/JavaScriptServlet"></script>
</div>
</div>
</body>
</html>