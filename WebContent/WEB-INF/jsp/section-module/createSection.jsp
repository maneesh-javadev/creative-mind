
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>
<%@include file="commonSectionClinetRule.jsp"%>

<title><spring:message htmlEscape="true"  code="Label.CREATENEWSECTION"></spring:message></title>
</head>
<body>
	<!-- Main Form Stylings starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message htmlEscape="true" code="Label.CREATENEWSECTION"></spring:message></h3>
			<%-- <ul class="item_list">
				<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li>
			</ul> --%>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form action="buildSelection.htm" method="post" id="sectionForm" commandName="sectionForm" >
				<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="buildSelection.htm"/>" />
					
					<!-- General Details of Section Started-->
					
					<div class="form_block">
						<div class="col_1">
							<h4><spring:message htmlEscape="true"  code="Label.GENERALDETAILNEWSECTION"></spring:message></h4>
							<ul class="form_body">
								<li>
									<label>
										<spring:message htmlEscape="true" code="Label.SECTIONNAMEENGLISH"></spring:message>
										<span class="mandate">*</span>
									</label>
									<form:input path="sectionNameEnglish" id="sectionNameEnglish" maxlength="200" htmlEscape="true" placeholder="Enter Section Name English" />
									<br/><span>Allowed characters are A-Z,a-z,0-9,/,-,Space,comma,dot,brackets()</span>
									<span class="errormessage" id="errsectionNameEnglish"></span>
									<form:errors htmlEscape="true" path="sectionNameEnglish" cssClass="error"/>
								</li>
							    <li>
									<label>
										<spring:message htmlEscape="true" code="Label.SECTIONNAMELOCAL"></spring:message>
									</label>
									<form:input path="sectionNameLocal" id="sectionNameLocal" maxlength="200" htmlEscape="true"/>	
									<br/>
									<form:errors htmlEscape="true" path="sectionNameLocal" cssClass="error"/>							
								</li>
								
								 <li>
									<label>
										<spring:message htmlEscape="true" code="Label.SECTIONNAMESHORT"></spring:message>
									</label>
									<form:input path="sectionShortName" id="sectionShortName" maxlength="20" htmlEscape="true"/>	
									<br/>
									<form:errors htmlEscape="true" path="sectionShortName" cssClass="error"/>							
								</li>
								
								
								<%-- <li>   
									<label>
										<spring:message htmlEscape="true" code="Label.SECTIONADDRESS1"></spring:message>
									</label>
									<form:input path="address1" id="address1" maxlength="100" htmlEscape="true"/>
									<br/>
									<form:errors htmlEscape="true" path="address1" cssClass="error"/>									
								</li>
								<li>
									<label>
										<spring:message htmlEscape="true" code="Label.SECTIONADDRESS2"></spring:message>
									</label>
									<form:input path="address2" id="address2" maxlength="100" htmlEscape="true"/>
									<br/>
									<form:errors htmlEscape="true" path="address2" cssClass="error"/>									
								</li>
								<li>
									<label>
										<spring:message htmlEscape="true" code="Label.SECTIONADDRESS3"></spring:message>
									</label>
									<form:input path="address3" id="address3" maxlength="100" htmlEscape="true"/>
									<br/>
									<form:errors htmlEscape="true" path="address3" cssClass="error"/>									
								</li>
								<li>
									<label>
										<spring:message htmlEscape="true" code="Label.SECTIONPINCODE"></spring:message>
									</label>
									<form:input path="pinCode" id="pinCode" maxlength="6" htmlEscape="true"/>
									<br/>
									<form:errors htmlEscape="true" path="pinCode" cssClass="error"/>	
									<span class="errormessage" id="errorpinCode"></span>								
								</li>
								
								<li>
									<label>
										<spring:message htmlEscape="true" code="Label.SECTIONLOCALADDRESS1"></spring:message>
									</label>
									<form:input path="localAddress1" id="localAddress1" maxlength="100" htmlEscape="true"/>
									<br/>
									<form:errors htmlEscape="true" path="localAddress1" cssClass="error"/>									
								</li>
								<li>
									<label>
										<spring:message htmlEscape="true" code="Label.SECTIONLOCALADDRESS2"></spring:message>
									</label>
									<form:input path="localAddress2" id="localAddress2" maxlength="100" htmlEscape="true"/>
									<br/>
									<form:errors htmlEscape="true" path="localAddress2" cssClass="error"/>									
								</li>
								<li>
									<label>
										<spring:message htmlEscape="true" code="Label.SECTIONLOCALADDRESS3"></spring:message>
									</label>
									<form:input path="localAddress3" id="localAddress3" maxlength="100" htmlEscape="true"/>
									<br/>
									<form:errors htmlEscape="true" path="localAddress3" cssClass="error"/>									
								</li> --%>
								
								
								
							</ul>
						</div>
					</div>
					<br/>
					
					<!-- General Details of Section Ends Here-->
					
					<!-- Localbody and Organization div for State #Started Here  -->
					<div class="form_block" id="stateBody" style="Display:none" >
								<div class="col_1">
									<%-- <h4><spring:message code="Label.select.under.lbnorg" htmlEscape="true"></spring:message></h4> --%>
										
										<!-- select entity type #started-->
										<div class="long_latitude" >
											
											<div class="row"> <!-- Max of 4 divs can be used inside this row div -->
													<h4><spring:message code="Label.select.entity" htmlEscape="true"></spring:message></h4>
													<div class="col">
														<label for="lbEntityType">
														<input type="radio"  id="lbEntityType" 	value="${entityTypeLB}" onclick="showHideOption();" name="parentTypeOption"  /> 
														<spring:message code='common.localBody' htmlEscape='true'></spring:message></label>
													</div>
													<div class="col">
														<label for="orgEntityType">
														<input type="radio"  id="orgEntityType" 	value="${entityTypeOrg}" onclick="showHideOption();" name="parentTypeOption" />
														<spring:message code="Label.Organization"   htmlEscape="true" ></spring:message></label>
													</div>
													<!-- <div class="col">
														
														<input type="radio"  id="UNSELECT" 	value="UNSELECT"  checked="checked" style="Display:none" name="parentTypeOption" />
														
													</div> -->
													
														<form:hidden path="parentType" />
														<span class="errormessage" id="errrparentType"></span>
														<form:errors htmlEscape="true" path="parentType" cssClass="error"/>
														
													
											</div>
										</div>
										<h4></h4>
											<!-- select entity type #end-->
											
											
											
											
											<!-- Entity Type Localbody block #started -->
											<div id="divEntityTypeLB" style="display: none;">
											<div id="dynamicLbStructure"></div>
											<div id="divSpecificFull"></div>
											<div id="divLBSpecificBlock" class="col_1">
												
												
										   </div>
											<!-- Blank heading being used in GUI -->
											</div>
											
											
											<!-- Entity Type Localbody block #end -->
											
											<!-- Entity Type Localbody block #started -->
											<div id="divEntityTypeOrg" style="display: none;">
											<div id="dynamicOrgStructure"></div>
											<div id="divSpecificFullOrg"></div>
											<div id="divOrgSpecificBlock" class="col_1">
											</div>
										
								</div>
						</div>
				
				</div>
				<!-- Localbody and Organization div for state #end Here  -->
				
				<!-- Localbody and Organization div for Center #started Here  -->
				<div class="form_block" id="centerBody" >
								<div class="col_1">
									<%-- <h4><spring:message code="Label.select.under.lbnorg" htmlEscape="true"></spring:message></h4> --%>
										
										
											<div id="divEntityTypeOrgCenter" style="display: none;">
											<div id="dynamicOrgStructureCenter"></div>
											<div id="deptorOrg"></div>
											<div id="divSpecificFullOrgCenter"></div>
											<div id="divOrgSpecificBlockCenter" class="col_1">
											</div>
										
											</div>
						</div>
				
				</div>
				<!-- Localbody and Organization div for Center #end Here  -->
				
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

