
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="../../common/dwr.jsp"%>
<%@include file="../govermentOrder/draftExistingGovernmentOrderJs.jsp"%>
<%@include file="changeSubdistrictJs.jsp"%>

<title><spring:message htmlEscape="true" code="label.edit.draft.subdistrict"></spring:message></title>

</head>
<body>
	<!-- Main Form Stylings starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message htmlEscape="true" code="Label.MODIFYSUBDISTRICT"></spring:message></h3>
			<%-- <ul class="item_list">
				<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li>
			</ul> --%>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form action="buildSubdistrictDraftChange.htm" method="post" id="draftManageSubdistrictForm" commandName="draftManageSubdistrictForm" enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="buildSubdistrictDraftChange.htm"/>" />
				<input type="hidden" name="subdistrictNameEnglish" id="subdistrictOld" value="${draftManageSubdistrictForm.subdistrictNameEnglish}" />
				<form:hidden path="formAction"	id="formAction"/>
				<form:hidden path="districtCode"  />
				<form:hidden path="subdistrictCode" />
				<form:hidden path="groupId"  />
				<form:hidden path="editMode" />
				<form:hidden path="draftCode" />
					
					<div class="form_block">
						<div class="col_1">
							
							<ul class="form_body">
								<li>
									<label for="subdistrictNameEnglish">
										<spring:message htmlEscape="true" code="Label.SUBDISTRICTNAMEENGLISH"></spring:message>
										<span class="mandate">*</span>
										
									</label>
									<form:input path="changeSubdistrictNameEnglish" id="subdistrictNameEnglish"  maxlength="50" htmlEscape="true" placeholder="Enter Subdistrict Name English" />
									
									<span class="errormessage" id="errsubdistrictNameEnglish"></span>
									<form:errors htmlEscape="true" path="changeSubdistrictNameEnglish" cssClass="error"/>
								</li>
								<li>
									<label for="subdistrictNameLocal">
										<spring:message htmlEscape="true" code="Label.SUBDISTRICTNAMELOCAL"></spring:message>
									</label>
									<form:input path="subdistrictNameLocal" id="subdistrictNameLocal" maxlength="50" htmlEscape="true"  />
									<span class="errormessage" id="errsubdistrictNameLocal"></span>
									<form:errors htmlEscape="true" path="subdistrictNameLocal" cssClass="error"/>
								</li>
								<li>
									<label for="aliasEnglish">
										<spring:message htmlEscape="true" code="Label.ALIASENGLISH"></spring:message>
									</label>
									<form:input path="aliasEnglish" id="aliasEnglish" maxlength="50" htmlEscape="true"  />
									<span class="errormessage" id="erraliasEnglish"></span>
									<form:errors htmlEscape="true" path="aliasEnglish" cssClass="error"/>
								</li>
								<li>
									<label for="aliasLocal">
										<spring:message htmlEscape="true" code="Label.ALIASLOCAL"></spring:message>
									</label>
									<form:input path="aliasLocal" id="aliasLocal" maxlength="50" htmlEscape="true"  />
									<span class="errormessage" id="erraliasLocal"></span>
									<form:errors htmlEscape="true" path="aliasLocal" cssClass="error"/>
								</li>
								
							</ul>
						</div>
					</div>
					<br/>
					
					
				<!-- Select From Existing Local Government Order Started -->
<div id="divExistingOrderDeatils" class="form_stylings" style="display: none;">

<!-- Block for Search Options Started-->							
	<div class="form_block">
		<div class="col_1">
			<h4>Search By Government Order No</h4>
			<ul class="form_body">
				<li>
					<label class="inline">
						<spring:message code='Label.GOvordernumber' htmlEscape='true'/>
		    		</label>
		    		<input type="text" id="paramOrderNo" maxlength="200"/>
		    		<span class="errormessage" id="goErrOrderNo"></span>
				</li>
			</ul>
			
			<h4>Search By Government Order Date</h4>
			<ul class="form_body">
				<li>
					<label class="inline">
						<spring:message code='Label.FROMDATE' htmlEscape='true'/>
		    		</label>
		    		<input type="text" id="dateParamFrom" readonly="readonly"/>
		    		<span class="errormessage" id="goErrFromDate"></span>
				</li>
				<li>
					<label class="inline">
						<spring:message code='Label.TODATE' htmlEscape='true'/>
		    		</label>
		    		<input type="text" id="dateParamTo" readonly="readonly"/>
		    		<span class="errormessage" id="goErrToDate"></span>
				</li>
			</ul>
		</div>
	</div>
<br/>
<!-- Block for Search Options Ends Here-->


<!-- Block for Fetch Buttons and GO Details-->
	<ul class="form_body" >
		<li>
		    <label class="inline">&nbsp;</label>
			<input class="bttn" type="button" id="fetchGODetails" value="Fetch Government Order" />
			<input class="bttn" type="button" id="goClose" value="Close" />	
		</li>
	</ul>	
	<br/>
	<table id="tblGornmentOrderDetails" class="data_grid" width="100%" style="display: none;">
		<thead>
			<tr>
				<th><!-- Header for Radio Button --></th>
				<th><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message></th>
				<th><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message></th>
				<th><spring:message	code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message></th>
				<th><spring:message	code="Label.GAZPUBDATE" htmlEscape="true"></spring:message></th>
				<th><spring:message code='Label.UGO' htmlEscape='true'/></th>
			</tr>
		</thead>
	</table>
</div>
<!-- Select From Existing Local Government Order Ends -->

<!-- Block for Government Order Details Started -->	
<div class="form_block">
	<div class="col_1">
		<h4><spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message></h4>
		<ul class="form_body">
			<c:if test="${isGovernmentOrderUpload}">
				<li>
					<input type="button" class="bttn" id="actionExistingGO" value="<spring:message code='Label.selectexistinggo' htmlEscape='true'/>"/>
					<input type="button" class="bttn" id="actionNewGO" value="<spring:message code='Label.selectnewgo' htmlEscape='true'/>"/>
				</li>
			</c:if>
			<li>
				<label>
					<spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message>
					<span class="mandate">*</span>
				</label>
				<form:hidden path="orderCode" id="orderCode" htmlEscape="true"/>
				<form:input path="orderNo" id="orderNo" value="${draftManageSubdistrictForm.orderNo}" maxlength="60" htmlEscape="true"/>
				<span class="errormessage" id="errorderNo"></span>
				<br/>
				<form:errors htmlEscape="true" path="orderNo" cssClass="error"/>
				<input type="hidden" id="checkNewOrExistGovtOrder"/>
			</li>
			<li>
			   	<label>	
					<spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message>
					<span class="mandate">*</span>											
				</label> 
				<fmt:formatDate var="orderDateValue" value="${draftManageSubdistrictForm.orderDate}" pattern="dd/MM/yyyy" /> 
				<form:input path="orderDate" id="formDateOrderDate" value="${orderDateValue}" readonly="true" />
				<br/>
				<span class="errormessage" id="errformDateOrderDate"></span>
				<form:errors htmlEscape="true" path="orderDate"  cssClass="error"/>
			</li>
			<li>
			   	<label>	
					<spring:message	code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> 
					<c:if test="${not empty draftManageSubdistrictForm.iParamEffectiveDate}">
						<strong>( <spring:message	code="Label.Previous.Version.Effective.Date" htmlEscape="true"></spring:message> : <fmt:formatDate pattern="dd/MM/yyyy" value="${draftManageSubdistrictForm.iParamEffectiveDate}" /> )</strong>
					</c:if>
					<span class="mandate">*</span>									
				</label>
				<fmt:formatDate var="effectiveDateValue" value="${draftManageSubdistrictForm.effectiveDate}" pattern="dd/MM/yyyy" /> 
				<form:input path="effectiveDate" id="formDateEffectiveDate" readonly="true" value="${effectiveDateValue}"/>
				<form:hidden path="iParamEffectiveDate" htmlEscape="true"/><!-- Previous Version Effective Date -->
				<br/>
				<span class="errormessage" id="errformDateEffectiveDate"></span>
				<form:errors htmlEscape="true" path="effectiveDate" cssClass="error"/>
			</li>
			
			<c:choose>
				<c:when test="${isGovernmentOrderUpload}">
					<li>
			   			<label>	
						<spring:message	code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>									
						</label>
						<fmt:formatDate var="gazPubDateValue" value="${draftManageSubdistrictForm.gazPubDate}" pattern="dd/MM/yyyy" /> 
						<form:input	 path="gazPubDate" id="formDateGazPubDate" readonly="true" value="${gazPubDateValue}" />
						<br/>
						<form:errors htmlEscape="true" path="gazPubDate" cssClass="error"/>
					</li>
					<li>
						<c:if test="${(empty draftManageSubdistrictForm.orderCode) and (modifyProcess or checkedServerValidation)}">
							<c:set var="fileName" value=""/>
							<c:if test="${not empty draftManageSubdistrictForm.orderPath}">
								<c:set var="substrng" value="${fn:substring(draftManageSubdistrictForm.orderPath, fn:indexOf(draftManageSubdistrictForm.orderPath, '_'), fn:indexOf(draftManageSubdistrictForm.orderPath, '.'))}" />
								<c:set var="fileName" value="${fn:replace(draftManageSubdistrictForm.orderPath, substrng, '')}" />
							</c:if>
							<a id="attachedUploadedFile" href="downloadLBGovernementOrder.htm?filename=${draftManageSubdistrictForm.orderPath}&<csrf:token uri='downloadLBGovernementOrder.htm'/>">
								<c:out value="${fileName}"/>
							</a>
							<form:hidden path="orderPath"/>
							<br/><br/>
						</c:if>
						<div id="divGazettePublicationUpload" <c:if test="${not empty draftManageSubdistrictForm.orderCode}">style="display: none;"</c:if>>
							<label>	
								<spring:message code="Label.UPLOADGOVTORDER" htmlEscape="true"/>
								<spring:message code='Label.allowedfileexe' htmlEscape='true'/>
								<span class="mandate">*</span>								
							</label>
							<form:input path="gazettePublicationUpload" id="gazettePublicationUpload" type="file" onclick="return validateFileUpload();"/>
							<form:errors htmlEscape="true" path="gazettePublicationUpload" cssClass="error"/>
							<span class="errormessage" id="errgazettePublicationUpload"></span><br/>
							<c:if test="${draftManageSubdistrictForm.editMode}">
							
							<table id="attachFile" class="data_grid">
							<tr>
							<th><spring:message code="Label.FILENAME" htmlEscape="true"/></th>
							<th><spring:message code="Label.FILESIZE" htmlEscape="true"/></th>
							<th><spring:message code="Label.CONTENTTYPE" htmlEscape="true"/></th>
							<th><spring:message code="Label.MARKDELETEFILE" htmlEscape="true"/></th>
							</tr>
							<tr>
							<td><c:out value="${draftManageSubdistrictForm.fileName}" /></td>
							<td><c:out value="${draftManageSubdistrictForm.fileSize}" /></td>
							<td><c:out value="${draftManageSubdistrictForm.fileContentType}" /></td>
							<td>
									<a href="#" onclick="deleteExistUploadFile();">
										<img src="images/delete.png" width="18" height="19" border="0"/>
									</a>
							</td>
							</tr>
							</table>
							</c:if>
						</div>		
					</li>	
				</c:when>
				<c:otherwise>
					<li>
			   			<label>	
						<spring:message htmlEscape="true" code="Label.SELGOT"></spring:message>
						<span class="mandate">*</span>								
						</label>
						<form:select path="templateId" id="templateId">
							<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT"/></form:option>
							<form:options items="${templateList}" itemLabel="templateNameEnglish" itemValue="templateCode"></form:options>
						</form:select>
					</li>	
					<li>
						<div id="divCKEditor" <c:if test="${not (modifyProcess or checkedServerValidation)}">style="display: none;"</c:if>>
							<form:textarea path="editorTemplateDetails" id="editorTemplateDetails" cssClass="ckeditor"/>
						</div>
			   		</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</div>

<!-- Block for Government Order Details Ends Here-->	
				<input class="bttn" id="btnFormActionSaveDraft" type="button" value="<spring:message htmlEscape="true" code="Button.DRAFT"/>" />
				<c:if test="${!draftManageSubdistrictForm.editMode}">
					<input class="bttn" id="btnFormActionPublish" type="button" value="<spring:message htmlEscape="true" code="Button.SP"/>" />
				</c:if>
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

