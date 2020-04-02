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
<link href="HTML_panchayat - 2.2/css/panchayat_main.css" rel="stylesheet" type="text/css" />
<script src="js/departmentAdminUnit.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/datatable/jquery.dataTables.js"></script>
<link href="<%=contextpthval%>/datatable/demo_table_jui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript">
		dwr.engine.setActiveReverseAjax(true);
	</script>

<script> 
$(document).ready(function() {
	var stId = '${stId}';
	if(stId!=null && stId!=0 && stId!=""){
		document.getElementById('statePopup').style.display = 'block';
	}
	var type= '${viewType}';
	if (type == 0) {
		document.getElementById('selectEntityType').style.visibility = 'visible';
		document.getElementById('selectEntityType').style.display = 'block';
		document.getElementById('manageEntity').style.visibility = 'hidden';
		document.getElementById('manageEntity').style.display = 'none';
	} else if (type == 1) {
		document.getElementById('manageEntity').style.visibility = 'visible';
		document.getElementById('manageEntity').style.display = 'block';
		document.getElementById('selectEntityType').style.visibility = 'hidden';
		document.getElementById('selectEntityType').style.display = 'none';
	}
	$('#modifyAdminUnitLevel').dataTable({
		/* comment by pooja on 22-06-2015 as it's not working properly on chrome */
		/* "sScrollY": "200px", */
		"bScrollCollapse": true,
		"bPaginate": true,
		"aoColumnDefs": [
			{ "sWidth": "10%", "aTargets": [ -1 ] }
		],
		"bJQueryUI": true,
		"aaSorting": [],
		"aoColumns": [
						null,
						null,
						null,
						{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false },
			  			],
		"sPaginationType": "full_numbers"
	});
	
	/* added by kirandeep on 19/05/2015 Issue in Manage Administrative Unit Entities */
	
	$("#dialog-message").dialog({
		autoOpen : false,
		height : 450,
		width : 650,
		modal : true,
	
		show : {
			effect : "blind",
			duration : 1000,
		},
		hide : {
			effect : "explode",
			duration : 1000
		}
	});
} );
function manageAdminUnit(url,id)
{
	dwr.util.setValue('adminUnitEntityCode', id, {	escapeHtml : false	});
	document.getElementById("unitCode").value = id;
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}
</script>
<title><spring:message code="Label.ViewAdminUnitEntity" htmlEscape="true" text="View Admin Unit Entity "></spring:message></title>
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle">
				<spring:message code="Label.ViewAdminUnitEntity" htmlEscape="true" text="View Admin Unit Entity "></spring:message>
			</h3>
			<ul id="showhelp" class="listing">
				<%--//these links are not working <li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /> </a></li>
				<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>

				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="view_AdminLevelDetail.htm" id="form1" method="POST" commandName="adminLevelBean">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="view_AdminLevelDetail.htm"/>" />
				<form:hidden path="adminUnitEntityCode" value="${deptAdminUnitForm.adminUnitEntityCode}" />
				<input type="hidden" name="stateCode" id="stateCode" value="<c:out value='${stateCode}'  escapeXml='true'></c:out>" />
				<input type="hidden" name="unitCode" id="unitCode" />
				<input type="hidden" name="entityType" id="entityType" />
				<input type="hidden" value="<c:out value='${districtCode}' escapeXml='true'></c:out>" id="districtCode">
				<input type="hidden" name="overlappValue" id ='overlappValue' value='${isExistOverlap}' />
				 <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="view_AdminLevelDetail.htm"/>" />
				 
					<div id="selectEntityType">
						<div class="frmpnlbg">
							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message code="Label.GENDETAILENTITY" htmlEscape="true" text="General Details of Admin Unit Entity"></spring:message>
								</div>
								<div>
									<ul class="blocklist">
										<li><label><spring:message code="Label.SELDeptAdministrationUNIT" text="Type of Administrative unit Level" htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br /> <br /> <label> <form:select
													htmlEscape="true" path="adminUnitLevelCode" class="combofield" id="unitLevelCode" onchange="getUnitLevelEntity();getOverlappingValue(this);" style="width: 150px">
													<form:option value="0">
														<spring:message code="Label.SELECT" text="-slect"></spring:message>
													</form:option>
													<form:options items="${parentAdminEntity}" itemLabel="adminLevelNameEng" itemValue="adminUnitCode" />
												</form:select> <br />
										</label>
											<div class="errormsg"></div>
											<div id="parentAdminUnitLevel" style="color: red;"></div></li>

										<!-- block for State pop-up in case of center level -->
										<li id="statePopup" style="display: none;"><label><spring:message code="Label.SELECTSTATE" htmlEscape="true"></spring:message></label> <span class="errormsg">*</span> <br /> <label> <select class="combofield"
												onchange="getUnitParentLvlEntity(this.value);" id="stateId" name="stateId" style="width: 150px">
													<option value="0">
														<spring:message code="Label.SELECT"></spring:message>
													</option>
													<c:forEach var="stateList" items="${stateList}">
														<option value="${stateList.stateCode}" ${stateList.stateCode == stId ? 'selected="selected"' : ''}>
															<c:out value='${stateList.stateNameEnglish}' escapeXml='true'></c:out>
														</option>
													</c:forEach>
											</select><br />
										</label>
											<div class="errormsg" id="err_state" style="color: red;"></div></li>
										<!-- end block for State pop-up in case of center level -->

										<li><label><spring:message code="Label.SELDEPTPARENTUNITENTITY" text="Parent level Administrative Unit Entity" htmlEscape="true"></spring:message></label> <br /> <br /> <label> <form:select htmlEscape="true"
													path="parentAdminUnitEntityCode" class="combofield" onchange="resetAdminEntityName();" id="parentUnit" style="width: 150px" onclick="clearDivs()">
													<form:option value="0">
														<spring:message code="Label.SELECT" text="-slect"></spring:message>
													</form:option>
													<c:forEach items="${adminEntities}" var="adminEntitiesvar">
														<c:if test="${adminEntitiesvar.operation_state == 'F'.charAt(0)}">
															<form:option value="${adminEntitiesvar.parentAdminCode}" disabled="true">${adminEntitiesvar.adminLevelNameEng}</form:option>
														</c:if>
														<c:if test="${adminEntitiesvar.operation_state == 'A'.charAt(0)}">
															<form:option value="${adminEntitiesvar.parentAdminCode}">${adminEntitiesvar.adminLevelNameEng}</form:option>
														</c:if>
													</c:forEach>
												</form:select> <br /> <br />
										</label>
											<div id="parentAdminUnit" style="color: red;"></div></li>
										</ul>
									<div class="clear"></div>
								</div>
							</div>
							<!-- added by kirandeep on 18/06/2014 -->
							<div class="btnpnl">
								<div>
									<label> <input type="button" class="btn" name="Submit" onclick="validateGetEntityByLevel()" id="submit1" value="<spring:message code="Button.PROCEED" htmlEscape="true"  ></spring:message>" />
									</label> <label><input type="button" name="Submit6" class="btn" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
								</div>
							</div>
						</div>
					</div>
					<div id="manageEntity" style="visibility: hidden; display: none">
						<c:if test="${! empty DEPT_ADMIN_LEVEL}">
							<div class="frmpnlbg" id="divData">
								<div class="frmtxt">
									<table id="tblrows" width="100%" class="tbl_no_brdr">

										<tr>
											<td width="14%" align="center">
												<table cellpadding="0" cellspacing="0" border="0" class="display" id="modifyAdminUnitLevel">
													<thead>
														<tr class="tblRowTitle tblclear">
															<th rowspan="2"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></th>
															<th rowspan="2"><spring:message code="Label.AdminEntityCode" text="Administrative Entity  Code"></spring:message></th>
															<th rowspan="2"><spring:message code="Label.AdminUnitEntityEng" text="Administrative Entity  (In English)"></spring:message></th>
															<th rowspan="2"><spring:message code="Label.AdminUnitEntityLoc" text="Administrative Entity  (In Local)"></spring:message></th>
															<th colspan="4" align="center"><spring:message htmlEscape="true" code="Label.ACTION"></spring:message></th>
														</tr>
														<tr class="tblRowTitle tblclear">
															<th align="right"><spring:message htmlEscape="true" code="Label.VIEW"></spring:message></th>
															<th align="center"><spring:message htmlEscape="true" code="Label.CORRECTION"></spring:message></th>
															<th align=""><spring:message htmlEscape="true" code="Label.Changecoveredarea"></spring:message></th>
															<%-- <th align="center"><spring:message htmlEscape="true"
																code="Label.DELETE"></spring:message></th> --%>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="adminEntityDetail" varStatus="listAdminRow" items="${DEPT_ADMIN_LEVEL}">
															<tr class="tblRowB">
																<td>${offsets*limits+(listAdminRow.index+1)}</td>
																<td><c:out value="${adminEntityDetail.adminUnitEntityCode}"></c:out></td>
																<td align="center"><c:out value="${adminEntityDetail.adminEntityNameEnglish}"></c:out></td>
																<td align="center"><c:out value="${adminEntityDetail.adminEntityNameLocal}"></c:out></td>

																<td width="8%" align="right"><a href="#"><img src="images/view.png" onclick="javascript:manageAdminUnit('view_AdminLevelDetail.htm',${adminEntityDetail.adminUnitEntityCode});" width="18" height="19" border="0" /></a></td>

																<td align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageAdminUnit('view_modifyDeptAdmitEntity.htm',${adminEntityDetail.adminUnitEntityCode});" width="18" height="19" border="0" /></a></td>
																<td align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageAdminUnit('modifyAdminEntityCoverage.htm',${adminEntityDetail.adminUnitEntityCode});" width="18" height="19" border="0" /></a></td>

																<%-- <td width="8%" align="center"><a href="#"><img
																	src="images/delete.png"
																	onclick="javascript:validateEntityChildExist(${adminEntityDetail.adminUnitEntityCode});"
																	width="18" height="19" border="0" /></a></td> --%>
															</tr>
														</c:forEach>
													</tbody>
												</table>
												<tr>
												</tr>
												<tr>
													<td align="right"></td>
												</tr>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</c:if>
					</div> <c:if test="${viewType == 0}">
						<div class="frmpnlbg" id="divData">
							<div class="frmtxt">
								<ul class="blocklist">
									<li><label> <spring:message htmlEscape="true" code="error.no.rec.found.admin.entity" text="No Administrative Unit Entity is defined for" /> <esapi:encodeForHTMLAttribute>${message}</esapi:encodeForHTMLAttribute>
									</label></li>
								</ul>
								<div class="clear"></div>
							</div>
						</div>
					</c:if>
					<div id="dialog-message" title="This admin Unit entity can not be deleted as it is used in below Organization Units" style="display: none;">
						<!-- <div class="new_listmenu"> -->

						<div class="frmtxt">
							<table id="tblrows" width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table cellpadding="0" cellspacing="0" border="0" class="display" id="modifyAdminUnitLevelOrg">
											<thead>
												<tr class="tblRowTitle tblclear">
													<th rowspan="2" align="left"><spring:message code="Label.ORGNAME"></spring:message></th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
									</td>
								</tr>
							</table>
						</div>
					</div> <input type="hidden" name="direction" id="direction" value="0" /> <input type="hidden" name="pageType" value="D" />
					<div id="dialog-confirm" title="Administrative Entity ?" style="display: none">
						<p>
							<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span> Are you confirmed to Delete Administrative Entity ?
						</p>
					</div>
			</form:form>
			<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>
</body>
</html>