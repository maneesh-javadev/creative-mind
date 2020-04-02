<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<title>E-Panchayat</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link href="HTML_panchayat - 2.2/css/panchayat_main.css"	rel="stylesheet" type="text/css" />
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script src="js/setParentOrgUnitCenter.js"></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>
<script type="text/javascript" language="javascript">	dwr.engine.setActiveReverseAjax(true);</script>
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">



			<h3 class="subtitle">
				<spring:message code="Label.ParentOrgUnit" htmlEscape="true"
					text="Set Parent Org Unit"></spring:message>
			</h3>
			<ul id="showhelp" class="listing">
				<%--//these links are not working  <li><a href="#" rel="toggle[cat]"
					data-openimage="images/minus.jpg"
					data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
						border="0" /></a></li>

				<li><a href="#" class="frmhelp"><spring:message
							htmlEscape="true" code="Button.HELP"></spring:message> </a></li>
 --%>
			</ul>


		</div>

		<!-- <div class="clear"> -->

		<div class="frmpnlbrdr">
			<form:form action="setParentOrgUnitCenter.htm" method="POST"
				id="form1" commandName="orgUnitCenterForm">
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="setParentOrgUnitCenter.htm"/>" />

				<input type="hidden" name="stateCode" id="stateCode"
					value="<c:out value='${stateCode}' escapeXml='true'></c:out>" />
				<input type="hidden" name="parentOrgCode" id='parentOrgCode' />
				<input type="hidden" name="childOrgCode" id='childOrgCode' />
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">

							<div class="margintoleft">
								<ul class="blocklist">

									<li><label><spring:message
												code="Label.SELOrganization" text="Select Organization Type"
												htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br />
										<div id="topLevelType" style="color: red;"></div> <br /> <label>
											<form:select htmlEscape="true" path="" class="combofield"
												id="topLevelEntityType"
												onchange="getTopLevelEntityByType(this.value)"
												style="width: 260px">
												<form:option value="0" htmlEscape="true">
													<spring:message code="Label.SELECT" text="-slect"></spring:message>
												</form:option>
												<form:option value="2" htmlEscape="true">
													<spring:message code="Label.SELECTDEP" text="DEPARTMENT"></spring:message>
												</form:option>
												<form:option value="3" htmlEscape="true">
													<spring:message code="Label.SELECTORG" text="ORGANIZATION"></spring:message>
												</form:option>
											</form:select> <br />
									</label></li>
									<div style='display: none;' id='minDiv'>
										<li><br /> <label><spring:message
													code="Label.SELOrganization" text="Select Ministry"
													htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br />
											<div id="parentAdminOrg" style="color: red;"></div> <br /> <label>
												<form:select htmlEscape="true" path="orgTypeCode"
													class="combofield" id="orgCode"
													onchange="getParentLevelEntity(this.value)" style="width: 260px">
													<form:option value="0" htmlEscape="true">
														<spring:message code="Label.SELECT" text="-slect"></spring:message>
													</form:option>

												</form:select> <br />
										</label>
											<div class="errormsg">
												<form:errors htmlEscape="true" path="orgCode"></form:errors>
											</div></li>
											
											<li><label><spring:message
													code="Label.SELOrgLevel" text="Select Department"
													htmlEscape="true"></spring:message></label> <span class="errormsg">*</span>
											<br />
											<div id="parentAdminOrgLevel" style="color: red;"></div> <br />
											<label> <form:select htmlEscape="true"
													path="orgTypeCode" class="combofield"
													onchange="getLevelofDepartment(this.value);" id="orgType"
													style="width: 260px">
													<form:option value="0">
														<spring:message code="Label.SELECT" text="-slect -"></spring:message>
													</form:option>

												</form:select> <br /> <br />
										</label></li><li><div class="clear"></div></li>
									</div>
									
									
									
										
										
										
										<div style='display: none;' id='deptDiv'>
										 <li><label><spring:message
													code="Label.SELOrgLevel" text="Select Level of Department"
													htmlEscape="true"></spring:message></label> <span class="errormsg">*</span>
											<br />
											<div id="parentAdminOrgLevel" style="color: red;"></div> <br />
											<label> <form:select htmlEscape="true"
													path="orgTypeCode" class="combofield"
													onchange="getparentOrganizations(this.value);" id="orgLevel"
													style="width: 260px">
													<form:option value="0">
														<spring:message code="Label.SELECT" text="-slect -"></spring:message>
													</form:option>

												</form:select> <br /> <br />
										</label></li>
					
										<li><div class="clear"></div></li>
									</div>
										
										
										


										<li><div class="clear"></div></li>
										
										
									


									<div style='display: none;' id='selOrgDiv'>
										<li><label><spring:message
													code="Label.SELOrgLevel" text="Select organisation"
													htmlEscape="true"></spring:message>
											</label> <span class="errormsg">*</span>
											<br />
											<div id="parentAdminOrgLevel" style="color: red;"></div> <br />
											<label> <form:select htmlEscape="true"
													path="orgTypeCode" class="combofield"
													onchange="getLevelofOrganisation(this.value);" id="orgDeptType"
													style="width: 260px">
													<form:option value="0">
														<spring:message code="Label.SELECT" text="-slect -"></spring:message>
													</form:option>

												</form:select> <br /> <br />
										   </label>
										</li><li><div class="clear"></div></li>
										<li><label><spring:message
													code="Label.SELOrgLevel" text="Select Level of Organisation"
													htmlEscape="true"></spring:message>
											  </label> <span class="errormsg">*</span>
											<br />
											<div id="parentAdminOrgLevelofDept" style="color: red;"></div> <br />
											<label> <form:select htmlEscape="true"
													path="orgTypeCode" class="combofield"
													onchange="getparentOrganizations(this.value);" id="orgLevelDept"
													style="width: 260px">
													<form:option value="0">
														<spring:message code="Label.SELECT" text="-slect -"></spring:message>
													</form:option>

												</form:select> <br /> <br />
										   </label>
										</li>


										<li><div class="clear"></div></li>
									</div>
									


								</ul>
							</div>




							<div style='display: none;' id='commonDiv'>
								<ul class="blocklist">

									<li>
										<div class="ms_container">
											<div class="ms_selectable">
												<label><spring:message
														code="Label.SELECTPARENTLEVELORG"
														text="Select Organization Unit"></spring:message></label><span
													class="errormsg">*</span> <br />
												<div id="parentOrganization" style="color: red;"></div>
												<br /> <br />

												<div id='test'
													style="border: 1px solid grey; overflow-x: scroll; height: 134px; width: 550px; overflow: -moz-scrollbars-horizontal;">
													<select id='parentLevelOrg' name="parentLevelOrg" size="7"
														style="width: 600px; background: none repeat scroll 0 0 #fbf9f0;"
														onchange="clearOrgsUnitsData();">

													</select>
												</div>



												<br /> <br />

												<div class="errormsg"></div>
												<span class="errormsg" id="parentOrganization_error">
												</span>
											</div>
											<div class="ms_buttons">
												<input name="button2" class="bttn" type="button"
													style="width: 230px; margin-top: 50%; margin-left: 120%"
													onclick="getChildOrganizations()"
													value="Get all child level Organization Units" />
											</div>
											<div class="ms_selection"></div>
										</div>

									</li>
									
									<li><div class="clear"></div></li>
									<li>

										<ul class="blocklist">
											<li>
												<div class="ms_container">
													<div class="ms_selectable">
														<label><spring:message
																code="Label.SELECTPARENTLEVELORG"
																text="Select Child Level Organization "></spring:message><br></br></label>

														<strong> <spring:message htmlEscape="true"
																code="Label.AVAILABLECDHILDORG"
																text="Available Child level Organization Units"></spring:message>
														</strong> <br />
														<div id="childOrganization" style="color: red;"></div>

														<div id='test'
															style="border: 1px solid grey; overflow-x: scroll; height: 135px; width: 300px; overflow: -moz-scrollbars-horizontal;">
															<select multiple="multiple" id='sourceOrgList'
																name="sourceOrgList" size="9"
																style="width: 600px; background: none repeat scroll 0 0 #fbf9f0;">

															</select>

														</div>
													</div>
													<div class="ms_buttons">
														<input id="src2Target1" class="bttn" type="button"
															style="width: 50px; margin-left: 27%; margin-top: 30%"
															onclick="addOrgUnits('contributedOrgist','sourceOrgList');"
															value=" &gt;&gt;" /> <input id="target2Src2"
															class="bttn" type="button"
															style="width: 50px; margin-left: 27%;"
															onclick="removeOrgUnits('contributedOrgist', 'sourceOrgList');"
															value="&lt;&lt;" /> <input id="target2Src2" class="bttn"
															type="button" style="width: 180px"
															value="Shift suitable Child Org Units"
															onclick="getSpecificChildOrganizations();"
															value="&lt;&lt;" />


													</div>
													<div class="ms_selection">
														<br></br> <strong> <spring:message
																htmlEscape="true" code="Label.CONTRIBUTECDHILDORG"
																text="Selected Child level Organization Units"></spring:message>
														</strong>
														<div id='test'
															style="border: 1px solid grey; overflow-x: scroll; height: 135px; width: 300px; overflow: -moz-scrollbars-horizontal;">
															<select id='contributedOrgist' multiple="multiple"
																name="contributedOrgist" size="9"
																style="width: 600px; background: none repeat scroll 0 0 #fbf9f0;">

															</select>

														</div>

													</div>
												</div>





											</li>

											<li class="clear"></li>


										</ul>


									</li>
									<li>
										<div class="clear"></div>
									</li>

								</ul>
							</div>

						</div>
					</div>
					<div class="btnpnl">
						<div>
							<label> <input type="button" class="btn" name="Submit"
								onclick="ValidateAndSubmitforEntity()" id="submit1"
								value="<spring:message code="Button.SAVEMappin" text="Save Mapping"  htmlEscape="true"  ></spring:message>" />
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

				<div id="dialog-confirm" title=" Set Parent Org Unit ?"
					style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert"
							style="float: left; margin: 0 7px 20px 0;"></span> The selected
						mapping would be saved. Do you wish to continue?
					</p>
				</div>
				<div id="dialog-clear" title=" Set Parent Org Unit ?"
					style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert"
							style="float: left; margin: 0 7px 20px 0;"></span> All the
						details entered in the form would be cleared. Do you still wish to
						continue?
					</p>
				</div>
			</form:form>
			<!-- 			</div> -->
			<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>
</body>
</html>