<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<title>E-Panchayat</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	<link href="HTML_panchayat - 2.2/css/panchayat_main.css"
		rel="stylesheet" type="text/css" />
	<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
	<script src="js/departmentAdminUnit.js"></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
    <script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrDistrictService.js'></script>	
    <script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<!-- <script type="text/javascript" src="js/invalidatetrilocalbody.js"
	charset="utf-8"></script> -->
		<script type="text/javascript" language="javascript">
		dwr.engine.setActiveReverseAjax(true);
	</script>
	<style>
</style>
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">
		
				<h3 class="subtitle"><spring:message code="Label.AdminUnitEntity" htmlEscape="true"
							text="Administrative Unit Entity"></spring:message></h3>
										 <ul id="showhelp" class="listing">
					 				      		<li>
					 				      					<a href="#" rel="toggle[cat]"	data-openimage="images/minus.jpg"	data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /></a>
					 				      		
					 				      		</li>
					 				        
					 				        <%--//these links are not working <li>
					 				        			<a href="#" class="frmhelp"><spring:message htmlEscape="true" 	code="Button.HELP"></spring:message> </a>
					 				        </li> --%>
					 	
					 			        </ul>
		
		</div>
		<div class="clear"></div>
			<div class="frmpnlbrdr">
				<form:form action="saveAdminEntityUnit.htm" method="POST"
					id="form1" commandName="departmentAdminForm">
					<input type="hidden" name="<csrf:token-name/>"
						value="<csrf:token-value uri="saveAdminEntityUnit.htm"/>" />
					<input type="hidden" name="stateCode" id="stateCode" value="<c:out value='${stateCode}' escapeXml='true'></c:out>" />
						<input type="hidden" name="districtCode" id="districtCode"	value="<c:out value='${districtCode}' escapeXml='true'></c:out>" />
						<input type="hidden" name="adminCoverage"	id="adminCoverage" value="" />
						<form:input type="hidden" path="publishOrSave" id = "publishOrSave" />
						<input type="hidden" name="wardCoverage"	id="wardCoverage" value="" />
						<input type="hidden" name="lbidWard" id ='lbidWard' />
						<input type="hidden" name="overlappValue" id ='overlappValue' />
					<div id="cat">
						<div class="frmpnlbg">
							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message code="Label.GENDETAILENTITY" htmlEscape="true"
										text="General Details of Admin Unit Entity"></spring:message>
								</div>
								
									<ul class="blocklist">
											<li>
															<label><spring:message	code="Label.SELDeptAdministrationUNIT" text="Type of Administrative unit Level" htmlEscape="true"></spring:message></label><span
											class="errormsg">*</span> <br /> <label>
											 <form:select htmlEscape="true" path="adminUnitLevelCode" class="combofield" id="unitLevelCode"  onchange="getParentLevelEntity();getOverlappingValue(this);" onclick="clearDivs();" style="width: 150px">
													<form:option value="-1">
														<spring:message code="Label.SELECT" text="-slect"></spring:message>
													</form:option>
													<form:options items="${parentAdminEntity}"
														itemLabel="adminLevelNameEng" itemValue="adminUnitCode" />
												</form:select> <br /> </label>
													<div class="errormsg">
										<form:errors htmlEscape="true" path="adminUnitLevelCode"></form:errors>
									</div>
											<div id="parentAdminUnitLevel" style="color: red;"></div>
											
											
											</li>
											
											<!-- block for State pop-up in case of center level -->
											<li id="statePopup" style="display: none;">
												<label><spring:message code="Label.SELECTSTATE" htmlEscape="true"></spring:message></label>
													<span class="errormsg">*</span> <br />
												<label>
													<select class="combofield" onchange="getParentLvlEntity(this.value);" id="stateId" style="width: 150px">
														<option value="0">
															<spring:message code="Label.SELECT"></spring:message>
														</option>
														 <c:forEach var="stateList" items="${stateList}">
														 	<option value="${stateList.stateCode}"><c:out value="${stateList.stateNameEnglish}" escapeXml="true"></c:out></option>
														 </c:forEach>
													</select><br/>
												</label>
												<div class="errormsg" id="err_state" style="color: red;"></div>
											</li>
											<!-- end block for State pop-up in case of center level -->
										
											<li>
													<label><spring:message code="Label.SELDEPTPARENTUNITENTITY" text="Parent level Administrative Unit Entity"	htmlEscape="true"></spring:message></label>
													<span class="errormsg">*</span>
													
											<br /> <label> <form:select htmlEscape="true"	path="parentAdminUnitEntityCode" class="combofield" onchange="resetAdminEntityName();"	id="parentUnit"  style="width: 150px" onclick="clearDivs()">
													<form:option value="-1">
														<spring:message code="Label.SELECT" text="-select-"></spring:message>
													</form:option>
												
												</form:select>  <br /></label>
											<div id="parentAdminUnit" style="color: red;"></div>
											
											</li>
											<li>
														<label><spring:message
													code="Label.AdminUnitEntityEng"
													text="Administrative Unit Entity Name(In English)"></spring:message></label><span
											class="errormsg">*</span> <br />  <form:input
												path="adminEntityNameEnglish" id="adminLevelNameEng"
												onblur="chekNameValidatonsforEntity(this.value);getPartAddedCoverage();"
												name="adminLevelNameEng" htmlEscape="true" class="frmfield"
												style="width: 150px" maxlength="100"/> <br />
													<div class="errormsg">
										<form:errors htmlEscape="true" path="adminEntityNameEnglish"></form:errors>
									</div>
											<div id="UniqueDeptAdminError" style="color: red;"></div> 
											
											
											</li>
											<li>
														<label><spring:message
													code="Label.AdminUnitEntityLoc"
													text="Administrative Unit Entity Name (In Local)"></spring:message></label>
											 <br /> <label> <form:input path="adminEntityNameLocal"
												id="adminLevelNameLocal" name="adminLevelNameLocal"
												htmlEscape="true" class="frmfield" style="width: 150px" maxlength="100"/>
											</label> <br />

											<div class="errormsg">
										<form:errors htmlEscape="true" path="adminEntityNameLocal"></form:errors>
									</div>
											</li>
											<li><div class="clear"></div></li>
											<li>
											<label> <spring:message code="Label.DEFINEADMINCOVERAGE" text="Define the coverage of the Administration Unit Entity" htmlEscape="true"></spring:message>&nbsp;</label>
												<ul class="listing"><li><label> <form:radiobutton id="coverageExisttrue" path="coverageExist" htmlEscape="true" value="true"	name="coverageExisttrue" onclick="populateDistrictList();toggleCovergeDiv();"/> </label> YES
												<label> <form:radiobutton id="coverageExistfalse" path="coverageExist" htmlEscape="true" value="false" name="coverageExistfalse" onclick="toggleCovergeDiv()"/> </label> NO
												</li></ul></li>
											<li><div class="clear"></div></li>
									</ul>
								
								
							</div>
						</div>
						<div id="coveragediv" class="frmpnlbg" style="visibility: hidden;display: none;">
						<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message  code="Label.CONTRIBUTINGLANDREGION" htmlEscape="true"></spring:message>
						</div>
						<input type="checkbox" id="existingLB" onclick="toggleLBCovergeDiv();"></input>
						<label><spring:message code="existingLB" text="Select From Existing Local Bodies" ></spring:message></label>
						
						 	&nbsp; 	&nbsp; 	&nbsp; &#09; &#09; &#09; &#09;<input type="checkbox" id="existingLR" onclick="toggleLRCovergeDiv()"></input>
						 	<label><spring:message code="existingLR" text="Select From Land Region"></spring:message></label>
						
						<br />
						<div id="LBRegion" style="visibility: hidden; display: none"  class="frmtxt">
						<div>
						<label><spring:message code="Label.EXISTINGLANDREGION"></spring:message></label>
													
					</div>	
					
								<ul class="blocklist">

									<%-- <li class="margintoleft"><label><spring:message code="Label.SELECTLOCALBODYTYPE" text="Select Zilla  Panchyat"></spring:message></label><span class="errormsg">*</span><br />
									<form:select htmlEscape="true" path="" class="combofield" id="ulbID" onchange="getUrbanlocabodies(this.value);">
										<form:option value="0">
											<spring:message code="Label.SELECT"></spring:message>
										</form:option>
										<form:options items="${uLBTypeList}" itemLabel="nomenclatureEnglish" itemValue="localBodyTypeCode" />
									</form:select> <br />

										<div class="errormsg"></div> <span class="errormsg" id="ddDestDistrict_error"> </span></li> --%>
									<li>
														<ul class="blocklist">

											<li>
												<div class="ms_container">
													<div class="ms_selectable">
														<strong> <spring:message htmlEscape="true" code="Label.AVAILABLELocalbodytype"></spring:message>
														</strong>
														<form:select path="" class="frmtxtarea" id="lbTypeList" multiple="true">
														<form:options items="${uLBTypeList}" itemLabel="nomenclatureEnglish" itemValue="localBodyTypeCode" />
														</form:select>




													</div>
													<div class="ms_buttons">

														<input class="bttn" type="button" onclick="moveElement('FORWARD');" value="&gt;" />
														<input class="bttn" type="button" onclick="moveElement('BACK');" value="&lt;" />
														<input class="bttn" type="button" onclick="moveElement('BACKALL');" value="&lt;&lt;" />
														<input class="bttn" type="button" onclick="moveElement('FORWARDALL');;" value="&gt;&gt;" />


													</div>
													<div class="ms_selection">
													<strong>
														<spring:message htmlEscape="true" code="Label.CONTRIBUTEURBANLB"></spring:message>
														</strong>
														<form:select name="select4" id="contributedLBTypeList" size="1" multiple="multiple" path="" class="frmtxtarea">
														</form:select>

														<input name="button2" class="btn" type="button" onclick="getLbList();" value="Get Urban List" />

													</div>
												</div>




											</li>
											
											<li>
												<div class="ms_container">
													<div class="ms_selectable">
														<strong> <spring:message htmlEscape="true" code="Label.AVAILABLEURBANLB"></spring:message>
														</strong>
														<form:select path="" class="frmtxtarea" id="SourceLBList" multiple="true">

														</form:select>




													</div>
													<div class="ms_buttons">

														<input id="src2Target1" class="bttn" type="button" onclick="addItemPartCheck('contributedLBList','SourceLBList','FULL','G');" value=" Whole &gt;&gt;" /> <input id="target2Src1" class="bttn" type="button"
															onclick="removeOnedistrictOption('contributedLBList', 'SourceLBList', true,1);" value="&lt;" /> <input id="target2Src2" class="bttn" type="button" onclick="removeAllList('contributedLBList', 'SourceLBList', true,1);" value="&lt;&lt;" />
														<input id="src2Target2" class="bttn" type="button" onclick="addItem('contributedLBList','SourceLBList', 'PART',true);" value="Part &gt;&gt;" />


													</div>
													<div class="ms_selection">
													<strong>
														<spring:message htmlEscape="true" code="Label.CONTRIBUTEURBANLB"></spring:message>
														</strong>
														<form:select name="select4" id="contributedLBList" size="1" multiple="multiple" path="" class="frmtxtarea">
														</form:select>

														<input name="button2" class="btn" type="button" onclick="getWardList('N');" value="Get Ward List" />

													</div>
												</div>




											</li>
											<li><div class="clear"></div></li>
											<li>
												<div class="ms_container">
													<div class="ms_selectable">
														<strong><spring:message htmlEscape="true" code="Label.AVAILEWARDLIST"></spring:message> </strong>
														<form:select name="souceWardList" size="1" id="souceWardList" path="" multiple="multiple" class="frmtxtarea">
														</form:select>
													</div>
													<div class="ms_buttons">
														<label> <input type="button" class="bttn" id="btnaddSubDistrictFull" name="Submit4" onclick="addItem('contributedWardList','souceWardList','FULL',true);" value=" Whole &gt;&gt;" />
														</label> <input id="buttonRemove1" class="bttn" type="button" onclick="removeOnedistrictOption('contributedWardList', 'souceWardList', true,0)" value="&lt;" /> <input id="buttonRemove2" class="bttn" type="button"
															onclick="removeAllList('contributedWardList', 'souceWardList', true,0)" value="&lt;&lt;" />


													</div>
													<div class="ms_selection">
														<strong> <spring:message htmlEscape="true" code="Label.CONTRIBUTWARDLIST"></spring:message>
														</strong>
														<form:select name="select4" id="contributedWardList" size="1" multiple="multiple" path="" class="frmtxtarea" htmlEscape="true">
														</form:select>

														<input name="button2" class="btn" type="button" onclick="emptyLBList()" value="<spring:message code="Button.RESET" htmlEscape="true"></spring:message>" />
													</div>
												</div>
											</li>
											<li>
												<div class="clear"></div>
											</li>


										</ul>




									</li>
										<li><div class="clear"></div></li>
										<li></li>
								
								</ul>
					</div>
						<div id="LRRegion"  style="visibility: hidden; display: none"  class="frmtxt">
						<div>
						<label><spring:message code="Label.ADMINEXISTINGLANDREGION" text="Existing Land Regions"></spring:message></label>
													
					</div>	
										
													
								<ul class="blocklist">
									
									<!-- block start for State at center Administrative Entities. -->
									<li id="stateSelectionBlock" style="visibility: hidden; display: none">
										<div class="ms_container">
											<div class="ms_selectable">
												<strong> <spring:message htmlEscape="true" code="Label.STATELIST"></spring:message>
												</strong> <br> <form:select path="" class="frmtxtarea" id="SourceState" multiple="true">
														<c:forEach items="${stateList}" var="stateListvar">
															<c:if test="${stateListvar.operation_state == 'F'.charAt(0)}">
																<form:option value="${stateListvar.stateCode}" disabled="true"><c:out value='${stateListvar.stateNameEnglish}' escapeXml='true'></c:out></form:option>
															</c:if>
															<c:if test="${stateListvar.operation_state == 'A'.charAt(0)}">
																<form:option value="${stateListvar.stateCode}"><c:out value='${stateListvar.stateNameEnglish}' escapeXml='true'></c:out></form:option>
															</c:if>
														</c:forEach>
													</form:select>
											</div>
											<div class="ms_buttons">
												<input id="src2Target1" class="bttn" type="button" onclick="addItemPartCheck('contributedState','SourceState','FULL','S');" value=" Whole &gt;&gt;" /> <input id="target2Src1" class="bttn" type="button"
													onclick="removeOnedistrictOption('contributedState', 'SourceState', true,5);" value="&lt;" /> <input id="target2Src2" class="bttn" type="button" onclick="removeAllList('contributedState', 'SourceState', true,5);"
													value="&lt;&lt;" /> <input id="src2Target2" class="bttn" type="button" onclick="addItem('contributedState','SourceState', 'PART',true);" value="Part &gt;&gt;" />


											</div>
											<div class="ms_selection">
												<strong> <spring:message htmlEscape="true" code="Label.CONTStateList" text="Contributing States List"></spring:message>
												</strong>
												<form:select name="select4" id="contributedState" size="1" multiple="multiple" path="" class="frmtxtarea">
												</form:select>
												<input name="button2" class="bttn" type="button" onclick="getDistrictList('N')" value="Get District List" />
											</div>
										</div>
									</li>
								<!-- block end for State at center Administrative Entities. -->
									<li><div class="clear"></div></li>
									<li>
										<div class="ms_container">
											<div class="ms_selectable">
												<strong> <spring:message htmlEscape="true" code="Label.DISTRICTLIST"></spring:message>
												</strong> <br> <form:select path="" class="frmtxtarea" id="SourceDistrict" multiple="true">
														<%-- <c:forEach items="${distList}" var="distListvar">
															<c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
																<form:option value="${distListvar.districtCode}" disabled="true"><c:out value='${distListvar.districtNameEnglish}' escapeXml='true'></c:out></form:option>
															</c:if>
															<c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
																<form:option value="${distListvar.districtCode}"><c:out value='${distListvar.districtNameEnglish}' escapeXml='true'></c:out></form:option>
															</c:if>
														</c:forEach> --%>
													</form:select>
											</div>
											<div class="ms_buttons">
												<input id="src2Target1" class="bttn" type="button" onclick="addItemPartCheck('contributedDistirct','SourceDistrict','FULL','D');" value=" Whole &gt;&gt;" /> <input id="target2Src1" class="bttn" type="button"
													onclick="removeOnedistrictOption('contributedDistirct', 'SourceDistrict', true,2);" value="&lt;" /> <input id="target2Src2" class="bttn" type="button" onclick="removeAllList('contributedDistirct', 'SourceDistrict', true,2);"
													value="&lt;&lt;" /> <input id="src2Target2" class="bttn" type="button" onclick="addItem('contributedDistirct','SourceDistrict', 'PART',true);" value="Part &gt;&gt;" />


											</div>
											<div class="ms_selection">
												<strong> <spring:message htmlEscape="true" code="Label.CONTRIBUTEDISTRICTLIST"></spring:message>
												</strong>
												<form:select name="select4" id="contributedDistirct" size="1" multiple="multiple" path="" class="frmtxtarea">
												</form:select>
												<input name="button2" class="bttn" type="button" onclick="getSubdistrictsList('N')" value="Get Sub-district List" />
											</div>
										</div>




									</li>
									<li>
										<div class="clear"></div>
									</li>
									<li>
										<div class="ms_container">
											<div class="ms_selectable">
												<strong><spring:message htmlEscape="true" code="Label.SUBDISTRICTLIST"></spring:message> </strong></br>
												<form:select name="sourceSubDistrict" size="1" id="sourceSubDistrict" path="" multiple="multiple" class="frmtxtarea">
												</form:select>
											</div>
											<div class="ms_buttons">
												<label><input type="button" class="bttn" id="btnaddSubDistrictFull" name="Submit4" onclick="addItemPartCheck('contributedSubDistirct','sourceSubDistrict','FULL','T');" value=" Whole &gt;&gt;" /> </label> <input id="buttonRemove1"
													class="bttn" type="button" onclick="removeOnedistrictOption('contributedSubDistirct', 'sourceSubDistrict', true,3)" value="&lt;" /> <input id="buttonRemove2" class="bttn" type="button"
													onclick="removeAllList('contributedSubDistirct', 'sourceSubDistrict', true,3)" value="&lt;&lt;" /> <label> <input type="button" class="bttn" id="btnaddSubDistrictPart" name="Submit4" value=" Part &gt;&gt;"
													onclick="addItem('contributedSubDistirct','sourceSubDistrict','PART',true);" />
												</label>

											</div>
											<div class="ms_selection">
												<strong> <spring:message htmlEscape="true" code="Label.CONTRIBUTESUBDISTRICTLIST"></spring:message>
												</strong>
												<form:select name="select4" id="contributedSubDistirct" size="1" multiple="multiple" path="" class="frmtxtarea" htmlEscape="true">
												</form:select>
												<input type="button" class="btn" value="Get Village List" onclick="selectVillageList('N');" /> <input type="button" class="btn" value="Get Gram Panchayat List" onclick="getGPList('N')" />
											</div>
										</div>




									</li>
									<li><div class="clear"></div></li>
									<li id="villageListId" style="visibility: hidden; display: none">
										<div class="ms_container">
											<div class="ms_selectable">
												<strong><spring:message htmlEscape="true" code="Label.VILLAGELIST"></spring:message> </strong></br>
												<form:select name="souceVilalgeList" size="1" id="souceVilalgeList" path="" multiple="multiple" class="frmtxtarea">
												</form:select>

											</div>
											<div class="ms_buttons">
												<label> <input type="button" class="bttn" id="btnaddVillageFull" name="Submit4" onclick="addItem('villageListContributed','souceVilalgeList','FULL',true);" value=" Whole &gt;&gt;" />
												</label> <input name="button22" class="bttn" type="button" onclick="removeOnedistrictOption('villageListContributed', 'souceVilalgeList', true,0)" value="&lt;" /> <input name="button22" class="bttn" type="button"
													onclick="removeAllList('villageListContributed', 'souceVilalgeList', true,0)" value="&lt;&lt;" />

											</div>
											<div class="ms_selection">
												<strong> <spring:message htmlEscape="true" code="Label.CONTRIBUTVILLAGELIST"></spring:message>
												</strong>
												<form:select name="select4" id="villageListContributed" size="1" multiple="multiple" path="" class="frmtxtarea">
												</form:select>
												<%-- <input name="button2" class="btn" type="button" onclick="emptyLRList()" value="<spring:message code="Button.RESET" htmlEscape="true"></spring:message>"  /> --%>
											</div>
										</div>




									</li>
									<li><div class="clear"></div></li>
									<li id="gpListId" style="visibility: hidden; display: none">
										<div class="ms_container">
											<div class="ms_selectable">
												<strong><spring:message htmlEscape="true" code="Label.GRAMPANCHAYTLIST" text="Gram Panchayat List"></spring:message> </strong></br>

												<form:select name="sourceGP" size="1" id="sourceGP" path="" multiple="multiple" class="frmtxtarea">
												</form:select>

											</div>
											<div class="ms_buttons">
												<label> <input type="button" class="bttn" id="btnAddGPFull" name="Submit4" onclick="addItemPartCheck('contributedGP','sourceGP','FULL','GP');" value=" Whole &gt;&gt;" /></label> <input id="buttonRemove1" type="button" class="bttn"
													onclick="removeOnedistrictOption('contributedGP', 'sourceGP', true,4)" value="&lt;" /> <input id="buttonRemove2" class="bttn" type="button" onclick="removeAllList('contributedGP', 'sourceGP', true,4)" value="&lt;&lt;" /> <label>
													<input type="button" class="bttn" id="btnAddGPPart" name="Submit4" value=" Part &gt;&gt;" onclick="addItem('contributedGP','sourceGP','PART',true);" />
												</label>

											</div>
											<div class="ms_selection">
												<strong> <spring:message htmlEscape="true" code="Label.CONTRIGRAMPANCHAYT"></spring:message>
												</strong> </br>

												<form:select name="select4" id="contributedGP" size="1" multiple="multiple" path="" class="frmtxtarea" htmlEscape="true">
												</form:select>

												<input type="button" class="btn" value="Get Gram Panchayat Village List" onclick="getCoveredAreaofLocalBodyList('N')" />
											</div>
										</div>


									</li>
									<li><div class="clear"></div></li>
									<li id="gpVillageListId" style="visibility: hidden; display: none">
										<div class="ms_container">
											<div class="ms_selectable">
												<strong><spring:message htmlEscape="true" code="Label.GPVILLAGELIST" text="Gram Panchayat Village List"></spring:message> </strong></br>
												<form:select name="sourceGpVillageList" size="1" id="sourceGpVillageList" path="" multiple="multiple" class="frmtxtarea">
												</form:select>

											</div>
											<div class="ms_buttons">

												<label> <input type="button" class="bttn" id="btnAddGpVillageFull" name="Submit4" onclick="addItem('gpVillageListContributed','sourceGpVillageList','FULL',true);" value=" Whole &gt;&gt;" /></label> <input name="button22" class="bttn"
													type="button" onclick="removeOnedistrictOption('gpVillageListContributed', 'sourceGpVillageList', true,0)" value="&lt;" /> <input name="button22" class="bttn" type="button"
													onclick="removeAllList('gpVillageListContributed', 'sourceGpVillageList', true,0)" value="&lt;&lt;" />
											</div>
											<div class="ms_selection">
												<strong> <spring:message htmlEscape="true" code="Label.GPCONTRIBUTVILLAGELIST" text="Contributing Gram Panchayat Village List"></spring:message>
												</strong>

												<form:select name="select4" id="gpVillageListContributed" size="1" multiple="multiple" path="" class="frmtxtarea">
												</form:select>
												<%-- <input name="button2" class="btn" type="button" onclick="emptyLRList()" value="<spring:message code="Button.RESET" htmlEscape="true"></spring:message>" /> --%>
											</div>
										</div>

									</li>
									<li><div class="clear"></div></li>
									<li>
										<div style="width: 100%;">
											<div style="margin-left: 550px;">
												<input name="button2" class="btn" type="button" onclick="emptyLRList()" value="<spring:message code="Button.RESET" htmlEscape="true"></spring:message>" />
											</div>
										</div>
									</li>

								</ul>
														
													
										
						</div>
						</div>
						</div>
						
					<!-- 	<div id="dialog-confirm" title="Administrative Units ?"
							style="display: none">
							<p>
								<span class="ui-icon ui-icon-alert"
									style="float: left; margin: 0 7px 20px 0;"></span> Are you
								confirmed to Save Admin Unit ?
							</p>
						</div> -->
                                    <!-- added by kirandeep on 18/06/2014 -->
						<div class="btnpnl">
                       				<div>
											<label> <%-- <input type="button" class="btn" name="Submit"
												onclick="ValidateAndSubmitforEntity('D')" id="submit1"
												value="<spring:message code="Button.SAVE" htmlEscape="true"  ></spring:message>" /> --%>
												
												<input type="button" class="btn" name="Submit"
												onclick="ValidateAndSubmitforEntity('P')" id="submit2"
												value="<spring:message code="Label.PUBLISH"  htmlEscape="true"  ></spring:message>" />
											</label> <label><input type="button" class="btn" id="Submit6"
												value="<spring:message code="Button.CLEAR"></spring:message>"
												onclick="emptyElements()" /></label> <label><input
												type="button" name="Submit6" class="btn"
												value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
												onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
											</label>
									</div>
						</div>



					</div>
					 <div id="dialog-confirm" title="Administrative Unit Entity" style="display: none">
									<p><span class="ui-icon ui-icon-alert"></span> Are you confirmed  to create this Entity as Parent Admin Entity Unit?</p>
								</div>
								 <div id="dialog-clearform" title="Clear Details" style="display: none">
									<p><span class="ui-icon ui-icon-alert" ></span> All details entered will be lost, Do you still want to clear the form?</p>
								</div>
				
				</form:form>
			</div>
			<script src="/LGD/JavaScriptServlet"></script>


		<!-- </div> -->
	</div>

</body>
</html>