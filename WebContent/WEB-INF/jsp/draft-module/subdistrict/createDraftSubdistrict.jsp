
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="../../common/dwr.jsp"%>
<%@include file="createDraftSubdistrictJs.jsp"%>

<title><spring:message htmlEscape="true"  code="Title.CREATENEWSUBDISTRICT"></spring:message></title>
</head>
<body>
	<!-- Main Form Stylings starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message htmlEscape="true" code="Title.CREATENEWSUBDISTRICT"></spring:message></h3>
			<%-- <ul class="item_list">
				<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li>
			</ul> --%>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form action="buildSelection.htm" method="post" id="draftSubdistrictForm" commandName="draftSubdistrictForm" enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="buildSelection.htm"/>" />
					<form:hidden path="selectDistrictCode" value="${draftSubdistrictForm.selectDistrictCode}"/>
					<form:hidden path="formAction"	id="formAction"/>
					<div class="form_block">
						<div class="col_1">
							
							<ul class="form_body">
								<li>
									<label>
										<spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message>
										<span class="mandate">*</span>
									</label>
									
									<form:select path="districtCode" id="districtCode">
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
									<form:errors htmlEscape="true" path="districtCode" cssClass="error"/>
								</li>
							</ul>
						 </div>
					</div>
					
					<br/>
					<!-- General Details of Section Started-->
					
					<div class="form_block">
						<div class="col_1">
							<h4><spring:message htmlEscape="true"  code="Legend.GENERALDETAILOFNEWSUBDISTRICT"></spring:message></h4>
							<ul class="form_body">
								<li>
									<label for="subdistrictNameEnglish">
										<spring:message htmlEscape="true" code="Label.NAMEOFNEWSUBDISTRICTENGLISH"></spring:message>
										<span class="mandate">*</span>
									</label>
									<form:input path="subdistrictNameEnglish" id="subdistrictNameEnglish" name="subdistrictNameEnglish" maxlength="50" htmlEscape="true" placeholder="Enter Subdistrict Name English" />
									<br/><span>Allowed characters are A-Z,a-z and Space</span>
									<span class="errormessage" id="errsubdistrictNameEnglish"></span>
									<form:errors htmlEscape="true" path="subdistrictNameEnglish" cssClass="error"/>
								</li>
								<li>
									<label for="subdistrictNameLocal">
										<spring:message htmlEscape="true" code="Label.NAMEOFNEWSUBDISTRICTLOCAL"></spring:message>
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
								<%-- <li>
									<label for="census2011Code">
										<spring:message htmlEscape="true" code="Label.CENSUSCODE2011"></spring:message>
									</label>
									<form:input path="census2011Code" id="census2011Code" maxlength="9" htmlEscape="true"  />
									<span class="errormessage" id="errcensus2011Code"></span>
									<form:errors htmlEscape="true" path="census2011Code" cssClass="error"/>
								</li> --%>
								<li>
									<label for="sscode">
										<spring:message htmlEscape="true" code="Label.STATESPECIFICCODE"></spring:message>
									</label>
									<form:input path="sscode" id="sscode" maxlength="7" htmlEscape="true"  />
									<span class="errormessage" id="errsscode"></span>
									<form:errors htmlEscape="true" path="sscode" cssClass="error"/>
								</li>
							</ul>
						</div>
					</div>
					<br/>
					
					<!-- General Details of Section Ends Here-->
				
					<div class="form_block">
						<div class="col_1">
							<h4><spring:message htmlEscape="true"  code="Label.HEADQUARTER"></spring:message></h4>
							<ul class="form_body">
								<li>
									<label for="headquarterNameEnglish">
										<spring:message htmlEscape="true" code="Label.DISTRICTHEADQUARTERENGLISH"></spring:message>
									</label>
									<form:input path="headquarterNameEnglish" id="headquarterNameEnglish" maxlength="50" htmlEscape="true" />
									<span class="errormessage" id="errheadquarterNameEnglish"></span>
									<form:errors htmlEscape="true" path="headquarterNameEnglish" cssClass="error"/>
								</li>
								<li>
									<label for="headquarterNameEnglish">
										<spring:message htmlEscape="true" code="Label.SUBDISTRICTHEADQUARTERLOCAL"></spring:message>
									</label>
									<form:input path="headquarterNameLocal" id="headquarterNameLocal" maxlength="50" htmlEscape="true" />
									<span class="errormessage" id="errheadquarterNameLocal"></span>
									<form:errors htmlEscape="true" path="headquarterNameLocal" cssClass="error"/>
								</li>
							</ul>
						</div>
					</div>	
					<br/>
					
					<!-- Block for GIS Nodes Started -->
				<div class="form_block">
					<div class="col_1">
						<h4><spring:message code="Label.GISNODES" htmlEscape="true"></spring:message></h4>
						<ul class="form_body">
							<li>
								<div class="long_latitude" >
									
									<div class="row">
										<div class="col"><label><spring:message code='Label.longituderange' htmlEscape='true'/></label></div>
										<div class="col"><label><spring:message code='Label.latituderange' htmlEscape='true'/></label></div>
									</div>
									<div class="row"> <!-- Max of 4 divs can be used inside this row div -->
										<div class="col"><form:input path="longitude" id="longitude" maxlength="6"/></div>
										<div class="col"><form:input path="latitude" id="latitude" maxlength="6"/></div>
										<div class="col"><input type="button" class="bttn addmore" id="btnAddLatLong" value="Add More"/></div>
									</div>
									<div id="divLatitudeLongitude">
										<!-- Adding Dynamic Rows for Latitude and Longitude -->
									</div>
									<br/>
									<form:errors htmlEscape="true" path="longitude" cssClass="error"/>
									<form:errors htmlEscape="true" path="latitude" cssClass="error"/>	 
									<span class="errormessage" id="errcordinate"></span>
								</div>
							</li>
							<li>
								<div id="divMapUpload" style="display: none;">
									<c:if test="${checkedServerValidation}">
										<a href="#"><c:out value="${localBodyForm.mapUploadPath}"></c:out></a>
										<form:hidden path="mapUploadPath"/>
									</c:if>
									<label>	
										<spring:message code="Label.UPLOADMAP" htmlEscape="true"/>
										<spring:message code='Label.allowedfileexe' htmlEscape='true'/>
									</label>
									<form:input path="mapUpload" id="mapUpload" type="file"/>
									<form:errors htmlEscape="true" path="mapUpload" cssClass="error"/>
								</div>	
							</li>	
						</ul>
					</div>
				</div>
				<br/>
				<!-- Block for GIS Nodes Ends Here -->
				
				<!-- This Block used for Covered area of Local Body Started -->	
					<div class="form_block">
						<div class="col_1">
							<h4><spring:message code="Label.CONTRIBUTINGLANDREGION" htmlEscape="true"></spring:message></h4>
							<ul class="form_body">
								<li>
									<div class="ms_container">
										<div class="ms_selectable">
											<label><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"/></label>
											<form:select path="" id="subdistrictList" multiple="multiple"/>
										</div>
										<div class="ms_buttons">
											<input class="bttn" id="btnEventCoverage" type="button" value="Select Full Sub Districts &gt;&gt;" onclick="moveElement('FULL')" />
											<input class="bttn" id="btnEventCoverage" type="button" value=" &lt;"  onclick="moveElement('BACK')"/>
											<input class="bttn" id="btnEventCoverage" type="button" value=" &lt;&lt;" onclick="moveElement('RESET')"/>
											<input class="bttn" id="btnEventCoverage" type="button" value="Select Part Sub Districts &gt;&gt;"  onclick="moveElement('PART')" />
										</div>
										<div class="ms_selection">
											<label><spring:message htmlEscape="true" code="Label.CONTRIBUTSUBDISTRICTLIST"/></label>
											<form:select path="contibutingSubdistrict" id="contibutingSubdistrict" multiple="multiple"/>
											<span class="errormessage" id="errcontibutingSubdistrict"></span>
											<form:errors htmlEscape="true" path="contibutingSubdistrict" cssClass="error"/>	
											<input class="bttn" type="button" id="fetchVillageList" value="<spring:message htmlEscape="true" code="Button.GETPARTSUBDISTRICT"/>" style="width:100%;"/>																
										</div>
										<div class="clear"></div>
									</div>
								</li>
								<li>
									<div class="ms_container">
										<div class="ms_selectable">
											<label><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"/></label>
											<form:select path="" id="villageList" multiple="multiple"/>
											<span class="errormessage" id="errvillageList"></span>
										</div>
										<div class="ms_buttons">
											<input class="bttn" id="btnEventCoverage" type="button"  value="Select Villages&gt;&gt;" onclick="moveElementVillage('FULL')"/>
											<input class="bttn" id="btnEventCoverage" type="button"  value=" &lt; "  onclick="moveElementVillage('BACK')"/>
											<input class="bttn" id="btnEventCoverage" type="button"  value=" &lt;&lt;" onclick="moveElementVillage('RESET')"/>
											
										</div>
										<div class="ms_selection">
											<label><spring:message htmlEscape="true" code="Label.CONTRIBUTVILLAGELIST"/></label>
											<form:select path="contibutingVillage" id="contibutingVillage" multiple="multiple"/>
											<br/>
											<form:errors htmlEscape="true" path="contibutingVillage" cssClass="error"/>	
											<span class="errormessage" id="errcontibutingVillage"></span>
											
										
										</div>
										<div class="clear"></div>
									</div>
								</li>
								
								</ul>
							</div>
						</div>
					
				<br/>
				<input class="bttn" id="btnFormActionAddAnoter" type="button" value="<spring:message htmlEscape="true" code="Button.ADDANOTHER"/>" />
				<input class="bttn" id="btnFormActionProceestoSave" type="button" value="<spring:message htmlEscape="true" code="button.proccessing.to.save"/>" />
	
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

