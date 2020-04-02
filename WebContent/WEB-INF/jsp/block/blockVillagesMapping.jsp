
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>
<%@include file="blockVillagesMappingJs.jsp"%>

<title><spring:message htmlEscape="true"  code="Label.ASSIGNEDVILLAGES"></spring:message></title>
</head>
<body>
	<!-- Main Form Stylings starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message htmlEscape="true" code="Label.ASSIGNEDVILLAGES"></spring:message></h3>
			<%-- <ul class="item_list">
				<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li>
			</ul> --%>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form action="saveBlockVillageMapping.htm" method="post" id="blockVillageForm" commandName="blockVillage" >
				<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="saveBlockVillageMapping.htm"/>" />
					
					<div class="form_block">
						<div class="col_1">
							<h4><%-- <spring:message htmlEscape="true"  code="error.select.download.type"></spring:message> --%></h4>
							<ul class="form_body">
								<c:if test="${!isDistrict}">
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message>
											<span class="mandate">*</span>
										</label>
										
										<form:select path="" id="districtCode">
										<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT"/></form:option>
											<c:forEach  var="obj" items="${districtList}">
												 <c:choose>
												 	<c:when test="${obj.operation_state == 'F'.charAt(0)}">
												 		 <form:option value="${obj.districtCode}" disabled="true">${obj.districtNameEnglish}</form:option>
												 	</c:when>
												 	<c:otherwise>
												 		<form:option value="${obj.districtCode}">${obj.districtNameEnglish}</form:option>
												 	</c:otherwise>	 
												 </c:choose>	
														  
											</c:forEach>
										</form:select>
										<span class="errormessage" id="errdistrictCode"></span>
										
									</li>
								</c:if>
								<li>
									<label>
										<spring:message htmlEscape="true" code="Label.TARGETBLOCK"></spring:message>
										<span class="mandate">*</span>
									</label>
									
									<form:select path="blockCode" id="ddTargetBlock">
									<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT"/></form:option>
									<c:forEach items="${blockList}" var="obj">
									  <c:if test="${obj.operation_state == 'F'.charAt(0)}">
									    <form:option value="${obj.blockCode}-${obj.blockVersion}" disabled="true" htmlEscape="true"><c:out value="${obj.blockNameEnglish}" escapeXml="true"></c:out> </form:option>
									  </c:if>  
									  <c:if test="${obj.operation_state == 'A'.charAt(0)}">
									     <form:option value="${obj.blockCode}-${obj.blockVersion}" htmlEscape="true"><c:out value="${obj.blockNameEnglish}" escapeXml="true"></c:out></form:option>
									  </c:if>
								</c:forEach>
									</form:select>
									<span class="errormessage" id="errddTargetBlock"></span>
									<form:errors htmlEscape="true" path="blockCode" cssClass="error"/>
								</li>
							</ul>
						 </div>
					</div>
					
					<br/>
				
				
				<!-- This Block used for Covered area of Local Body Started -->	
					<div class="form_block">
						<div class="col_1">
							<h4></h4>
							<ul class="form_body">
								<li>
									<div class="ms_container">
										<div class="ms_selectable">
											<label><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"/></label>
											<form:select path="villageMappedListDel" id="ddSourceVillage" multiple="multiple"/>
										</div>
										<div class="ms_buttons">
											
											<input class="bttn" id="btnEventCoverage" type="button" value=" &gt;" onclick="moveElement('FORWARD')"/>
											<input class="bttn" id="btnEventCoverage" type="button" value=" &lt;"  onclick="moveElement('BACK')"/>
											
											
										</div>
										<div class="ms_selection">
											<label><spring:message htmlEscape="true" code="Label.VILLAGESTOINVALIDATE"/><span class="mandate">*</span></label>
											<form:select path="villageMappedListNew" id="ddTargetVillage" multiple="multiple"/>
											<span class="errormessage" id="errddTargetVillage"></span>
											<form:errors htmlEscape="true" path="villageMappedListNew" cssClass="error"/>	
											</div>
										<div class="clear"></div>
									</div>
								</li>
								<br/>
								<il>
								
								<div id="diverrchangevillage" style="display:none; height: 40px; border: 1px solid red; background-color:#FFEBE8; padding-top: 10px;" align="center">
																	<span class="errormessage" id="errchangevillage"></span>
								</div>
								</il>
								
								
								
								</ul>
							</div>
						</div>
					
				<br/>
				<input class="bttn" id="btnFormActionProceestoSave" type="button" value="<spring:message htmlEscape="true" code="Button.SAVE"/>" />
				<input class="bttn" id="btnActionClose" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="callActionUrl('home.htm')"/>
			</form:form>	
		</div>
	</div>
	<!-- Page Content ends -->
	<!-- </div> -->
			
</div>
<!-- Main Form Stylings ends -->

</body>
</html>

