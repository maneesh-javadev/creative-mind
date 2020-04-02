<!DOCTYPE html>
<html>
<%!String allowedNoOfAttachment = "1";
	String uploadLocation = "C:\\files";
	String allowedFileType = "gif,jpg,pdf,png";
	Long allowedTotalFileSize = 5L;
	Long allowedIndividualFileSize = 1L;
	String requiredTitle = "no";
	String uniqueTitle = "no";%>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="manageOrgLevelsJS.jsp"%>
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />

</head>
<body class="dt-example">
	
	<!-- Main Form Styling starts -->
	<div class="form_stylings">
		
		<!-- Main Heading starts -->
		<div class="header">
			<h3>Rename Level Specific Name of Organization</h3>
			<ul class="item_list">
				<%-- //this link is not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		<!-- Main Heading ends -->

		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form method="POST" name="manageOrgLevels" id="manageOrgLevels" commandName="orgLocatedAtLevelsForm" action="manageOrgLevels.htm" enctype="multipart/form-data">
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="manageOrgLevels.htm"/>" />
					<input type="hidden" name="orgName" id="orgName" value="" />
					<div id="divCenterAligned" class="form_stylings">
					<!-- General Details Block Start -->
						<div class="form_block">
							<div class="col_1">
								<h4>General Details</h4>
								<ul class="form_body">
									<li>
									<spring:message htmlEscape="true"  code="Label.ORGTYPELIST"/><span class="mandate">*</span><br/>
										<form:select path="orgType" id="orgType" class="combofield" style="width :400px;">
											<form:option value="">																
												<spring:message code="Label.SELECT" htmlEscape="true"/>
											</form:option>
											<form:options items="${orgTypeList}" itemLabel="orgType" itemValue="orgTypeCode" />
										</form:select>	<br/>										
										<span class="error" id="orgTypeError"></span>							
										<form:errors  path="orgType" htmlEscape="true" cssClass="errormsg"/>	
										<div id="err_orgType" class="errormsg label"></div>											
										<br/>
									</li>
									<li>
									<spring:message htmlEscape="true"  code="Label.ORGLIST"/><span class="mandate">*</span><br/>
										<form:select path="olc" id="olc" class="combofield" style="width :400px;">
											<form:option value="">																
												<spring:message code="Label.SELECT" htmlEscape="true"/>
											</form:option>
										</form:select><br/>										
										<span class="error" id="olcError"></span>							
										<form:errors  path="olc" htmlEscape="true" cssClass="errormsg"/>	
										<div id="err_olc" class="errormsg label"></div>											
										<br/>
									</li>
									<li>
									<spring:message htmlEscape="true" code="Label.ORGLEVEL"/><span class="mandate">*</span><br/>
										<form:select path="orgLocatedLevelCode" id="orgLocatedLevelCode" class="combofield" style="width :400px;">
											<form:option value="">																
												<spring:message code="Label.SELECT" htmlEscape="true"/>
											</form:option>
										</form:select><br/>											
										<span class="error" id="orgLocatedLevelCodeError"></span>							
										<form:errors  path="orgLocatedLevelCode" htmlEscape="true" cssClass="errormsg"/>
										<div id="err_orgLocatedLevelCode" class="errormsg label"></div>												
										<br/>
									</li>
									<li>
									<spring:message htmlEscape="true" code="Label.NewOrgLevelName" text="New Level specific Name of the Organisation/Department in English"></spring:message>
										<span class="mandate">*</span><br/>
										<form:input path="orgLevelSpecificName" id="orgLevelSpecificName" maxlength="200" htmlEscape="true" style="width :400px;"/><br/>
											<span class="error" id="orgLevelSpecificNameError"></span>
										<form:errors htmlEscape="true" path="orgLevelSpecificName" cssClass="error"/>
										<div id="err_orgLevelSpecificName" class="errormsg label"></div>
									</li>
								</ul>
								</div>
								</div>
								<br/>
								<!-- General Details Block End -->
								
								<!-- Government Order Details Block Start -->
							<div class="form_block">
							<div class="col_1">
							<h4>Government Order Details</h4>
								<ul class="form_body">
									<li>
									<spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message><br/>
										<form:input path="orderNo" id="orderNo" maxlength="200" htmlEscape="true" style="width :400px;"/>
										<br/><span class="error" id="orderNoError"></span>
										<form:errors htmlEscape="true" path="orderNo" cssClass="error"/>
										<div id="err_orderNo" class="errormsg label"></div>
									</li>
									<li>
									<spring:message htmlEscape="true" code="Label.ORDERDATE"></spring:message><br/>
										<form:input path="orderDate" id="OrderDate" maxlength="200" htmlEscape="true" style="width :400px;"/>
										<br/><span class="error" id="orderDateError"></span>
										<form:errors htmlEscape="true" path="orderDate" cssClass="error"/>
										<div id="err_orderDate" class="errormsg label"></div>
									</li>
									<li>
									<spring:message htmlEscape="true" code="Label.EFFECTIVEDATE"></spring:message><br/>
										<form:input path="ordereffectiveDate" id="EffectiveDate" maxlength="200" htmlEscape="true" style="width :400px;"/>
											<br/><span class="error" id="effectiveDateError"></span>
										<form:errors htmlEscape="true" path="ordereffectiveDate" cssClass="error"/>
										<div id="err_effectiveDate" class="errormsg label"></div>
									</li>
									<li>
									<spring:message htmlEscape="true" code="Label.GAZPUBDATE"></spring:message><br/>
										<form:input path="gazPubDate" id="gazPubDatecr" maxlength="200" htmlEscape="true" style="width :400px;"/>
										<br/><span class="error" id="gazPubDateError"></span>
										<form:errors htmlEscape="true" path="gazPubDate" cssClass="error"/>
										<div id="err_gazPubDate" class="errormsg label"></div>
									</li>
									<li>
										<spring:message code="Label.UPLOADGOVTORDER" htmlEscape="true" />
										<spring:message code='Label.allowedfileexe' htmlEscape='true' /><br/>
										<form:input path="attachFile" id="attachFile" type="file" />
										<form:errors htmlEscape="true" path="attachFile" cssClass="error" />
										<div id="err_attachFile" class="errormsg label"></div>
									</li>
								</ul>
							</div>
						</div>	
						<br/>
								<!-- Government Order Details Block End -->
					</div>
					<br />
					<div id="buttonFormAction">
						<input class="bttn" id="btnFormActionSave" type="button" value="<spring:message code='Button.SAVE' htmlEscape='true'/>" param="SAVE" />
						<input class="bttn" id="btnFormActionClear" type="button" value="<spring:message htmlEscape="true" code="Button.CLEAR"/>" param="CLEAR" />
						<input class="bttn" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'" />
					</div>
				</form:form>
			</div>
		</div>
		<!-- Page Content ends -->
	
	</div>
	<!-- Main Form Styling ends -->
</body>
</html>