
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>
<%@include file="createHabitaionJs.jsp"%>

<title><spring:message htmlEscape="true"  code="label.create.habitation"></spring:message></title>
</head>
<body>
	<!-- Main Form Stylings starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message htmlEscape="true" code="label.create.habitation"></spring:message></h3>
			<%-- <ul class="item_list">
				<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li>
			</ul> --%>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form action="buildHabitation.htm" method="post" id="habitationForm" commandName="habitationForm" >
				
				<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="buildHabitation.htm"/>" />
					<input type="hidden" value="<c:out value='${habitationForm.parentTypeName }' />" />
					<!-- General Details of Section Started-->
					
					<div class="form_block">
						<div class="col_1">
						<%-- 	<h4><spring:message htmlEscape="true"  code="Label.GENERALDETAILNEWSECTION"></spring:message></h4> --%>
							<ul class="form_body">
								<li>
									<label>
										<spring:message htmlEscape="true" code="label.habitation.name.eng"></spring:message>
										<span class="mandate">*</span>
									</label>
									<form:input path="habitationNameEnglish" id="habitationNameEnglish" maxlength="200" htmlEscape="true"  autocomplete="off"/>
									<br/><span>Allowed characters are A-Z,a-z,0-9,/,-,Space,comma,dot,brackets()</span>
									<span class="errormessage" id="errhabitationNameEnglish"></span>
									<form:errors htmlEscape="true" path="habitationNameEnglish" cssClass="error"/>
								</li>
							    <li>
									<label>
										<spring:message htmlEscape="true" code="label.habitation.name.local"></spring:message>
									</label>
									<form:input path="habitationNameLocal" id="habitationNameLocal" maxlength="200" htmlEscape="true" autocomplete="off"/>	
									<br/>
									<form:errors htmlEscape="true" path="habitationNameLocal" cssClass="error"/>							
								</li>
								<li>
									<label>
										<spring:message htmlEscape="true" code="Label.EFFECTIVEDATE"></spring:message>
									</label>
									<form:input path="effectiveDate" id="effectiveDate"  readonly="true" htmlEscape="true" maxlength="10"/>	
									<br/>
									<form:errors htmlEscape="true" path="effectiveDate" cssClass="error"/>							
								</li>
								
								<li>
									<label>
										<spring:message htmlEscape="true" code="Label.STATESPECIFICCODE"></spring:message>
									</label>
									<form:input path="sscode" id="sscode" maxlength="10" htmlEscape="true" autocomplete="off"/>	
									<br/>
									<form:errors htmlEscape="true" path="sscode" cssClass="error"/>							
								</li>
								
								
								<li>
									<label>
										<spring:message htmlEscape="true" code="Label.habitation.parent.type"></spring:message>
									</label>
									<form:input path="parentTypeName" id="parentTypeName" maxlength="1" htmlEscape="true" disabled="true" autocomplete="off"/>	
									<br/>
									<form:hidden path="parentType" />
									<form:errors htmlEscape="true" path="parentType" cssClass="error"/>							
								</li>
								
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
										<div id="divCreateDynamicHierarchy" style="display:none;">
											<!-- This Div used to generate Hierarchy -->
										</div>
										
										
										<div id="divDisplayVillage" style="display: none;">
											
											<li>
												<label for="ddSourceDistrict"><spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message></label>
												<form:select path="" class="combofield" id="ddSourceDistrict">
													<form:option value="">
														<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
													</form:option>
													<form:options items="${districtList}" itemValue="districtPK.districtCode" itemLabel="districtNameEnglish"></form:options>
												</form:select> 
												<br/>
												<span class="errormessage" id="errorddSourceDistrict"></span>
												
											</li>
											<li>
												<label for="ddSourceSubDistrict"><spring:message htmlEscape="true" code="Label.SELECTSUBDISTRICT"></spring:message></label>
												<form:select path="" class="combofield" id="ddSourceSubDistrict">
													<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
												</form:select> 
												<br/><span class="errormessage" id="errorddSourceSubDistrict"></span>
											</li>
											
											<li>
												<label for="ddSourceVillage"><spring:message htmlEscape="true" code="Label.SELECTVILLAGE"></spring:message></label>
												<form:select path="paramVillageCode" class="combofield" id="ddSourceVillage">
													<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
												</form:select> 
												<br/><span class="errormessage" id="errorddSourceVillage"></span>
											
											</li>
										</div>
					<br/>
					
					<!-- General Details of Section Ends Here-->
					
				<br/>
				<input class="bttn" id="btnFormActionSave" type="button" value="<spring:message htmlEscape="true" code="Button.SAVE"/>" />
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

