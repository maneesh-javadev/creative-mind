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
<link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" />
<script src="js/departmentAdminUnit.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>

<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
	$(document).ready(function() {
		var childRecords = document.getElementById("childRecords").value;
	    var parentAdminCode = '<c:out value="${deptAdminUnitForm.parentAdminCode}" escapeXml="true"></c:out>'; 
		var adminUnitLevCode = document.getElementById("administationUnit").value;
		paretnunitCode= '<c:out value="${deptAdminUnitForm.parentAdminCode}" escapeXml="true"></c:out>'; 
		$('#unitLevelCode').val(adminUnitLevCode);
		$('#parentUnit').val(parentAdminCode);
		
		if (childRecords > 0 || (isParseJson( '${orgUnitsExist}' ))) {
			document.getElementById("unitLevelCode").disabled = true;
			document.getElementById("parentUnit").disabled = true;
		}

	});
	
	function showReports() {
		$("#dialog:ui-dialog").dialog("destroy");
		$("#dialog-message").dialog({
			centered : true,
			modal : true,
			width : 600,
			height : 400
		});
	}
</script>
<style>
</style>
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">
		
					<h3 class="subtitle"><spring:message code="Label.ModifyAdminEntity"	htmlEscape="true" text="Modify Administrative Unit Entity"></spring:message></h3>
										 <ul id="showhelp" class="listing">
					 				      		<%--//these links are not working <li>
					 				      					<a href="#" rel="toggle[cat]"	data-openimage="images/minus.jpg"	data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /></a>
					 				      		
					 				      		</li>
					 				        
					 				        <li>
					 				        			<a href="#" class="frmhelp"><spring:message htmlEscape="true" 	code="Button.HELP"></spring:message> </a>
					 				        </li>
					 	 --%>
					 			        </ul>
		
		
			
		</div>
		<!-- <div class="clear"> -->
			<div class="frmpnlbrdr">
				<form:form action="modifyDeptAdmitEntity.htm" method="POST"
					id="form1" commandName="deptAdminUnit">
					<input type="hidden" name="<csrf:token-name/>"
						value="<csrf:token-value uri="modifyDeptAdmitUnit.htm"/>" />
					<input type="hidden" name="stateCode" id="stateCode" value="<c:out value='${stateCode}'  escapeXml='true'></c:out>"/>
					<form:hidden path="adminUnitEntityCode" value="${deptAdminUnitForm.adminUnitCode}" htmlEscape="true"/>
					<form:hidden path="createdby" value="${deptAdminUnitForm.createdby}" htmlEscape="true" />
					<input type="hidden" id="parentAdminCode" name="parentAdminCode" value="<c:out value='${deptAdminUnitForm.parentAdminCode}' escapeXml='true'></c:out>" />
					<%-- <input type="hidden" id="parentCode" value="${deptAdminUnitForm.parentAdminCode}"/> --%>
					<input type="hidden" id="parentCode" value="1" />
					<input type="hidden" id="administationUnit" name="administationUnit" value="<c:out value='${deptAdminUnitForm.adminUnitLevelCode}'  escapeXml='true'></c:out>"/>
					<input type="hidden" id="childRecords" value="<c:out value='${deptAdminUnitForm.childRecords}' escapeXml='true'></c:out>"/>
					<input type="hidden" id="adminUnitName" value="<c:out value='${deptAdminUnitForm.adminLevelNameEnglish}'  escapeXml='true'></c:out>"/>
					<input type="hidden" id="adminUnitNameLocal" value="<c:out value='${deptAdminUnitForm.adminLevelNameLocal }' escapeXml='true'></c:out>"/>
					<input type="hidden" id="adminUnitCodehidden" value="<c:out value='${deptAdminUnitForm.administrationUnit }'  escapeXml='true'></c:out>"/>
					<input type ="hidden" value="<c:out value='${districtCode}'  escapeXml='true'></c:out>"	id="districtCode">
					<div id="popupDialogBX" style="display: none;">
						<iframe id="loginPopupFrame" scrolling="no"></iframe>
					</div>
					<div id="cat">
						<div class="frmpnlbg">
							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message code="Label.GENDETAILENTITY" htmlEscape="true"
										text="General Details of Admin Unit Entity"></spring:message>
								</div>
								
								<div >
										<ul class="blocklist">
											<li>
														<label><spring:message
													code="Label.SELDeptAdministrationUNIT"
													text="Type of Administrative unit Level" htmlEscape="true"></spring:message></label><span
											class="errormsg">*</span><br /> <br /> <label> <form:select
													htmlEscape="true" path="adminUnitLevelCode"
													onchange="resetAdminParentIndex();getUnitLevelEntity();" class="combofield"
													id="unitLevelCode" 
													style="width: 150px">
													<form:option value="0" htmlEscape="true">
														<spring:message code="Label.SELECT" text="-slect"></spring:message>
													</form:option>
													<form:options items="${unitLevels}"
														itemLabel="adminLevelNameEng" itemValue="adminUnitCode" htmlEscape="true"/>
												</form:select> <br /> <br /></label>
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
													<select class="combofield" onchange="getUnitParentLvlEntity(this.value);" id="stateId" style="width: 150px">
														<option value="0">
															<spring:message code="Label.SELECT"></spring:message>
														</option>
														 <c:forEach var="stateList" items="${stateList}">
														 	<option value="${stateList.stateCode}"><c:out value='${stateList.stateNameEnglish}'  escapeXml='true'></c:out></option>
														 </c:forEach>
													</select><br/>
												</label>
												<div class="errormsg" id="err_state" style="color: red;"></div>
											</li>
											<!-- end block for State pop-up in case of center level -->
											
											<li>
											
														<label><spring:message
													code="Label.SELDEPTPARENTUNITENTITY"
													text="Parent level Administrative Unit Entity"
													htmlEscape="true"></spring:message></label> <span class="errormsg">*</span>
											<br /> <br /> <label> <form:select
													htmlEscape="true" path="parentAdminUnitEntityCode"
													class="combofield" id="parentUnit"  onchange="checkmodifyEntityName();"
													style="width: 150px">
													
                                                    <form:option value="-1" htmlEscape="true">
														<spring:message code="Label.SELECT" text="-slect"></spring:message>
													</form:option>
													
													<form:options items="${unitEntities}"
														itemLabel="adminLevelNameEng" itemValue="parentAdminCode" htmlEscape="true"/>

												</form:select> <br /> <br /></label>
											<div id="parentAdminUnitforEntity" style="color: red;"></div>
											
											</li>
											<li>
											
														<label><spring:message
													code="Label.AdminUnitEntityEng"
													text="Administrative Unit Entity Name(In English)"></spring:message></label><span
											class="errormsg">*</span><br /> <br /> <form:input
												path="adminEntityNameEnglish" id="adminLevelNameEng"
												onblur="chekModifyNameValidatonsforEntity(this.value);"
												name="adminLevelNameEng" htmlEscape="true" class="frmfield"
												style="width: 150px"
												value="${deptAdminUnitForm.adminLevelNameEnglish }"
												maxlength="100" /> <br /> <br />
											<div class="errormsg">
												<form:errors htmlEscape="true" path="adminEntityNameEnglish"></form:errors>
											</div>
											<div id="UniqueDeptAdminError" style="color: red;"></div>
											
											</li>
											<li>
														<label><spring:message
													code="Label.AdminUnitEntityLoc"
													text="Administrative Unit Entity Name (In Local)"></spring:message></label>
											<br /> <br /> <label> <form:input
													path="adminEntityNameLocal" id="adminLevelNameLocal"
													name="adminLevelNameLocal" htmlEscape="true"
													class="frmfield" style="width: 150px"
													value="${deptAdminUnitForm.adminLevelNameLocal }"
													maxlength="100" />
										</label> <br /> <br />

										<div class="errormsg">
										<form:errors htmlEscape="true" path="adminEntityNameLocal"></form:errors>
									</div>
											
											</li>
											<li>
															<label> <a href="#" style="text-decoration: underline; color: navy;" 	onclick="showReports();">View Affected Organization Units</a> 
											</label>
											
											</li>
											<li></li>
										</ul>
										<div class="clear"></div>
								</div>
								
								
								<br />
							</div>
						</div>
						<div id="dialog-confirm" title="Administrative Units ?"
							style="display: none">
							<p>
								<span class="ui-icon ui-icon-alert"
									style="float: left; margin: 0 7px 20px 0;"></span> Are you
								confirmed to Modify Admin Entity ?
							</p>
						</div>
						
						
						
						
						
						
						<div id="dialog-message" title="View Affected Organization Units"
						style="display: none;">
						<!-- <div class="new_listmenu"> -->
						<c:if test="${adminEntityOrg.size() > 0}" >
						<div class="frmtxt">
								<table id="tblrows" width="100%" class="tbl_no_brdr">

									<tr>
										<td width="14%" align="center">
											<table cellpadding="0" cellspacing="0" border="0"
												class="display" id="modifyAdminUnitLevel">
												<thead>
													<tr class="tblRowTitle tblclear">
														<th rowspan="2"><spring:message htmlEscape="true"
																code="Label.SNO"></spring:message></th>
														<th rowspan="2" align="left"><spring:message
																code="Label.ORGNAME">
																</spring:message></th>
													</tr>
												</thead>
												<tbody>
												<c:forEach var="fyearval" items="${adminEntityOrg}" varStatus="listAdminRow" >
													<%-- <c:forEach var="adminEntityDetail" varStatus="listAdminRow"
														items="${DEPT_ADMIN_LEVEL}"> --%>
														<tr class="tblRowB">
															<td align="center"><c:out value="${offsets*limits+(listAdminRow.index+1)}"  escapeXml="true"></c:out></td>
															<td align="left">
															<c:out value="${fyearval}" escapeXml="true"></c:out>
															<%-- <c:out
																	value="${adminEntityDetail.adminUnitEntityCode}"></c:out> --%></td>
														
														</tr>
													</c:forEach>
												</tbody>
											</table>
										
										</td>
									</tr>
								</table>
							</div>
						
	                  </c:if>
	                   <c:if test="${ empty adminEntityOrg}">
	               	<div class="frmtxt">
	             
	                  <label><spring:message
													code="Label.NROrgUNis"
													text="No Records found for Admin Org Units"></spring:message></label>
	                  
	                 </div>
	                 </c:if>
						</div>
						
						<div class="btnpnl">
										<div>
											<label> <input type="button" class="btn" name="Submit"
												onclick="ValidateAndSubmitforEntityupdate()" id="submit1"
												value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" />
											</label> <label><input type="button" class="btn" id="Submit6"
												value="<spring:message code="Button.CLEAR"></spring:message>"
												onclick="emptymodifyEntityElements()" /></label> <label><input
												type="button" name="Submit6" class="btn"
												value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
												onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
											</label>
										</div>
						
						</div>
					</div>
				</form:form>
			</div>
			<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>

</body>
</html>