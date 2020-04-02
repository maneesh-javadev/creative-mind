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
<script type="text/javascript" language="javascript">dwr.engine.setActiveReverseAjax(true);</script>
<script> 
$(document).ready(function() {
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
} );
function manageAdminUnit(url,id)
{
	dwr.util.setValue('adminUnitCode', id, {	escapeHtml : false	});
	document.getElementById("unitCode").value = id;
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}
</script>
<title><spring:message code="Label.MANAGEUNIT" htmlEscape="true" text="Manage Unit Levels "></spring:message></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle">
				<spring:message code="Label.MANAGEUNIT" htmlEscape="true" text="Manage Unit Levels "></spring:message>
			</h3>
			<ul id="showhelp" class="listing">
				<%-- //these links are not working<li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /> </a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="view_AdminLevelDetail.htm" id="form1" method="POST" commandName="adminLevelBean">
				<form:hidden path="adminUnitCode" value="${deptAdminUnitForm.adminUnitCode}" />
				<input type="hidden" name="stateCode" id="stateCode" value="<c:out value='${stateCode}' escapeXml='true'></c:out>" />
				<input type="hidden" name="unitCode" id="unitCode" />
				<input type="hidden" name="entityType" id="entityType" />
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="view_AdminLevelDetail.htm"/>" />
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
													<th rowspan="2"><spring:message code="Label.AdminUnitCode" text="Administrative Unit Level Code"></spring:message>
														<th rowspan="2"><spring:message code="Label.AdminUnitLevelEng" text="Administrative Unit Level (In English)"></spring:message>
															<th rowspan="2"><spring:message code="Label.AdminUnitLevelLoc" text="Administrative Unit Level (In Local)"></spring:message>
																<th colspan="4" align="center"><spring:message htmlEscape="true" code="Label.ACTION"></spring:message></th>
												</tr>
												<tr class="tblRowTitle tblclear">
													<th align="right"><spring:message htmlEscape="true" code="Label.VIEW"></spring:message></th>
													<th align=""><spring:message htmlEscape="true" code=""></spring:message></th>
													<th align="center"><spring:message htmlEscape="true" code="Label.CORRECTION"></spring:message></th>
													<th align="center"><spring:message htmlEscape="true" code="Label.DELETE"></spring:message></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="adminUnitLevelDetail" varStatus="listAdminRow" items="${DEPT_ADMIN_LEVEL}">
													<tr class="tblRowB">
														<td><c:out value="${offsets*limits+(listAdminRow.index+1)}" escapeXml="true"></c:out></td>
														<td><c:out value="${adminUnitLevelDetail.adminUnitCode}" escapeXml="true"></c:out></td>
														<td align="center"><c:out value="${adminUnitLevelDetail.adminLevelNameEng}" escapeXml="true"></c:out></td>
														<td align="center"><c:out value="${adminUnitLevelDetail.adminLevelNameLocal}" escapeXml="true"></c:out></td>
														<td width="8%" align="right"><a href="#"><img src="images/view.png" onclick="javascript:manageAdminUnit('view_AdminLevelDetail.htm',${adminUnitLevelDetail.adminUnitCode});" width="18" height="19" border="0" /></a></td>
														<td align="">
															<!-- <a href="#"><img
																src=""
																width="8" height="19" border="0" /></a> -->
														</td>
														<td align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageAdminUnit('view_modifyDeptAdmitUnit.htm',${adminUnitLevelDetail.adminUnitCode});" width="18" height="19" border="0" /></a></td>
														<td width="8%" align="center"><a href="#"><img src="images/delete.png" onclick="javascript:validateChildExist(${adminUnitLevelDetail.adminUnitCode});" width="18" height="19" border="0" /></a></td>
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
				<c:if test="${fn:length(viewPage) > 0}">
					<c:if test="${empty DEPT_ADMIN_LEVEL}">
						<div class="frmpnlbg" id="divData">
							<div class="frmtxt">
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td colspan="4" align="center"><label> <spring:message htmlEscape="true" code="error.no.rec.found.admin.level" text="No Administrative Unit Level is defined for" /> <c:choose>
													<c:when test="${stateCode > 0}">
														<esapi:encodeForHTMLAttribute>${stateName}</esapi:encodeForHTMLAttribute>
														<spring:message htmlEscape="true" code="Label.STATE" />
													</c:when>
													<c:otherwise>
														<spring:message htmlEscape="true" code="Label.CENTER" />
													</c:otherwise>
												</c:choose>
										</label></td>
									</tr>
								</table>
							</div>
						</div>
					</c:if>
				</c:if>
				<input type="hidden" name="direction" id="direction" value="0" />
				<input type="hidden" name="pageType" value="D" />
				<div id="dialog-confirm" title="Administrative Unit Level?" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span> Are you confirmed to Delete Administrative Unit Level ?
					</p>
				</div>
			</form:form>
			<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>
</body>
</html>