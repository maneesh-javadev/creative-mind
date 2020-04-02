
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>
<%@include file="manageSectionJs.jsp"%>

<title><spring:message htmlEscape="true"  code="Label.MANAGESECTION"></spring:message></title>
</head>
<body>
	<!-- Main Form Stylings starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message htmlEscape="true" code="Label.MANAGESECTION"></spring:message></h3>
			<%-- <ul class="item_list">
				<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li>
			</ul> --%>
		</div>
		<!-- Main Heading ends -->
			
		
					<div class="page_content">
			<div class="form_container">
				<form:form action="manageSection.htm" method="post" id="sectionForm" commandName="sectionForm" >
				<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="manageSection.htm"/>" />
				<form:hidden path="sectionCode" id="paramSectionCode"/>
				<form:hidden path="sectionVersion" id="paramSectionVersion"/>
				<form:hidden path="sectionNameEnglish" id="paramSctionName"/>
					<div class="form_block" id="stateBody" style="Display:none" >
								<div class="col_1">
								
										
										<!-- select entity type #started-->
										<div class="long_latitude" >
											<div class="row"> <!-- Max of 4 divs can be used inside this row div -->
													<h2><spring:message code="Label.select.manage.entity" htmlEscape="true"></spring:message></h2>
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
														<span class="errormsg" id="errrparentType"></span>
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
						<div class="form_block" id="centerBody" >
								<div class="col_1">
									 <h4><spring:message code="Label.select.manage.entity" htmlEscape="true"></spring:message></h4> 
										
										
											<div id="divEntityTypeOrgCenter" style="display: none;">
											<div id="dynamicOrgStructureCenter"></div>
											<div id="deptorOrg"></div>
											<div id="divSpecificFullOrgCenter"></div>
											<div id="divOrgSpecificBlockCenter" class="col_1">
											</div>
										
											</div>
						</div>
				
					</div>
						<br/>
					<input class="bttn" id="btnFormActionGet" type="button" value="<spring:message htmlEscape="true" code="Button.GET"/>" />
					<input class="bttn" id="btnActionClose" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="callActionUrl('home.htm')"/>	
					<br/><br/>
					<c:if test="${searchFlag}">
					<!-- Block for Section Update and Delete block #started. -->
					<div class="form_block">
						<div class="col_1 overflow_horz">
							<h2><spring:message code="Label.section.details" htmlEscape="true"></spring:message></h2>
							<ul class="form_body">
								<li>
									<table id="example" class="display" cellspacing="0" width="100%">
										<thead>
											<tr>
												
												<th><spring:message code="Label.SNO" htmlEscape="true"></spring:message></th>
												<th><spring:message code="Label.section.code" htmlEscape="true"></spring:message></th>
												<th><spring:message code="Label.section.name.english" htmlEscape="true"></spring:message></th>
												<th><spring:message code="Label.section.name.local" htmlEscape="true"></spring:message></th>
												
												<%-- <th style="word-break:break-all"><spring:message code="Label.GovernmentOrderCorrection" htmlEscape="true"></spring:message></th> --%>
												<th><spring:message code="Label.MODIFY" htmlEscape="true"></spring:message></th>
												<th><spring:message code="Label.DELETE" htmlEscape="true"></spring:message></th>
												
											</tr>
										</thead>
										<tbody>
											<c:forEach var="objects" items="${sectionForm.sectionList}" varStatus="counter">
												<tr id="trdetials">
													
													<td align="center"><c:out value="${counter.count}" escapeXml="true"></c:out></td>
													<td><c:out value="${objects.sectionPK.sectionCode}" escapeXml="true"></c:out></td>
													<td style="word-break:break-all"><c:out value="${objects.sectionNameEnglish}" escapeXml="true"></c:out></td>
													<td style="word-break:break-all"><c:out value="${objects.sectionNameLocal}" escapeXml="true"></c:out></td>
													
													<td align="center">
														<a href="#" onclick="processLinkActions('${objects.sectionPK.sectionCode}',null,'${objects.sectionNameEnglish}', 'updateSection.htm');">
															<img src="images/view.png" width="18" height="19" border="0"/>
														</a>
													</td>
													
													<td align="center">
														<a href="#" onclick="processLinkActions('${objects.sectionPK.sectionCode}',null,'${objects.sectionNameEnglish}', 'deleteSection.htm');">
															<img src="images/delete.png" width="18" height="19" border="0"/>
														</a>
													</td>
													
												</tr>	
											</c:forEach>
										</tbody>
									</table>
									
								</li>
							</ul>
						</div>
					</div>
					<!-- Block for Section Update and Delete block #end. -->
					<br/>
					<!-- Block for Section Reactive block #started -->
					<div class="form_block">
						<div class="col_1 overflow_horz">
							<h2><spring:message code="Label.deleted.section.details" htmlEscape="true"></spring:message></h2>
							<ul class="form_body">
								<li>
									<table id="reactiveTable" class="display" cellspacing="0" width="100%">
										<thead>
											<tr>
												
												<th><spring:message code="Label.SNO" htmlEscape="true"></spring:message></th>
												<th><spring:message code="Label.section.code" htmlEscape="true"></spring:message></th>
												<th><spring:message code="Label.section.name.english" htmlEscape="true"></spring:message></th>
												<th><spring:message code="Label.section.name.local" htmlEscape="true"></spring:message></th>
												<th><spring:message code="Label.RESTORE" htmlEscape="true"></spring:message></th>
												
												
											</tr>
										</thead>
										<tbody>
											<c:forEach var="objects" items="${sectionForm.sectionDeleteList}" varStatus="counter">
												<tr id="trdetials">
													
													<td align="center"><c:out value="${counter.count}" escapeXml="true"></c:out></td>
													<td><c:out value="${objects.sectionCode}" escapeXml="true"></c:out></td>
													<td style="word-break:break-all"><c:out value="${objects.sectionNameEnglish}" escapeXml="true"></c:out></td>
													<td style="word-break:break-all"><c:out value="${objects.sectionNameLocal}" escapeXml="true"></c:out></td>
													
													<td align="center">
														<a href="#" onclick="processLinkActions('${objects.sectionCode}','${objects.sectionVersion}','${objects.sectionNameEnglish}', 'reActiveSection.htm');">
															<img src="images/Restores.png" width="32" height="32" border="0"/>
														</a>
													</td>
													
													
													
												</tr>	
											</c:forEach>
										</tbody>
									</table>
									
								</li>
							</ul>
						</div>
					</div>
					<!-- Block for Section Update and Delete block #end. -->
					<br/>

				<center><input class="bttn" id="btnActionClose" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="callActionUrl('home.htm')"/></center>
					</c:if>
				<c:if test="${searchFlag eq false}">
				
					<div class="form_block">
						<div class="col_1">
							<h2><spring:message code="Label.section.not.found.given.criteria" htmlEscape="true"></spring:message></h2>
						</div>
					</div>
				
				</c:if>
				
				
			</form:form>	
		</div>
	</div>
	<!-- Page Content ends -->
	<!-- </div> -->
			
</div>
<!-- Main Form Stylings ends -->
<div id="dialog-confirm">
</div>
</body>
</html>

