<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>
	
</script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script src="js/shiftvillage.js"></script>
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message htmlEscape="true"
							code="Label.INVALIDATEBLOCK"></spring:message>
					</td>
					<td align="right"><a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" />
					</a>
					</td>
					<%-- this link is not working <td width="50" align="right"><a href="#" class="frmhelp"><spring:message
								htmlEscape="true" code="Label.HELP"></spring:message> --%>
					</a>
					</td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="" id="form1" method="POST" commandName="">
				<div class="frmpnlbg">
					<div class="frmtxt">
						<table class="tbl_with_brdr" width="98%">
							<tr>
								<td colspan="4" class="tblRowTitle tblclear"><label><strong><spring:message
												htmlEscape="true" code="Label.INVALIDATEBLOCK"></spring:message>
									</strong>
								</label>
								</td>
							</tr>
							<tr class="tblRowTitle tblclear">
								<td width="15%"><spring:message htmlEscape="true"
										code="Label.BLOCKCODE"></spring:message>
								</td>
								<td width="15%"><spring:message htmlEscape="true"
										code="Label.BLOCKVER"></spring:message>
								</td>
								<td width="35%"><spring:message htmlEscape="true"
										code="Label.BLOCKNAMEINENGLISH"></spring:message>
								</td>
								<td width="35%"><spring:message htmlEscape="true"
										code="Label.BLOCKNAMEINLOCAL"></spring:message>
								</td>
							</tr>
							<tr class="tblRowB">
								<td width="15%"><c:out
										value="${blockBean.blockPK.blockCode}" escapeXml="true"></c:out>
								</td>
								<td width="15%"><c:out
										value="${blockBean.blockPK.blockVersion}" escapeXml="true"></c:out>
								</td>
								<td width="35%"><c:out
										value="${blockBean.blockNameEnglish}" escapeXml="true"></c:out>
								</td>
								<td width="35%"><c:out value="${blockBean.blockNameLocal}" escapeXml="true"></c:out>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<c:if test="${fn:length(dsdMapView)> 0 }">
					<div class="frmpnlbg">
						<div class="frmtxt">

							<table class="tbl_with_brdr" width="98%">
								<tr>
									<td colspan="4" class="tblRowTitle tblclear"><label><strong><spring:message
													htmlEscape="true" code="Label.MOVEDVILLAGES"></spring:message>
										</strong>
									</label>
									</td>
								</tr>
								<tr class="tblRowTitle tblclear">
									<td width="15%"><spring:message htmlEscape="true"
											code="Label.BLOCKCODE"></spring:message>
									</td>
									<td width="15%"><spring:message htmlEscape="true"
											code="Label.BLOCKVER"></spring:message>
									</td>
									<td width="15%"><spring:message htmlEscape="true"
											code="Label.BLOCKNAMEINENGLISH"></spring:message>
									</td>
									<td width="15%"><spring:message htmlEscape="true"
											code="Label.BLOCKNAMEINLOCAL"></spring:message>
									</td>
									<td width="55%" align="center" colspan="3"><spring:message
											htmlEscape="true" code="Label.VILLAGESMERGEWITHBLOCK"></spring:message>
									</td>
								</tr>
								<tr>
									<td><spring:message htmlEscape="true"
											code="Label.VILLAGECODE"></spring:message>
									</td>
									<td><spring:message htmlEscape="true"
											code="Label.VILLAGENAMEINENGLISH"></spring:message>
									</td>
									<td><spring:message htmlEscape="true"
											code="Label.VILLAGENAMEINLOCAL"></spring:message>
									</td>
								</tr>
								<c:forEach var="entity" varStatus="entityRow"
									items="${bVillMapView}">
									<tr class="tblRowB">
										<td width="15%"><c:out
												value="${entity.key.blockPK.blockCode}" escapeXml="true"></c:out>
										</td>
										<td width="15%"><c:out
												value="${entity.key.blockNameEnglish}" escapeXml="true"></c:out>
										</td>
										<td width="15%"><c:out
												value="${entity.key.blockNameLocal}" escapeXml="true"></c:out>
										</td>
										<td width="55%" colspan="3">
											<table width="100%" class="tbl_with_brdr">
												<c:forEach var="entityvalue" varStatus="entityvaluetemp"
													items="${entity.value}">
													<tr class="tblRowB tblclear">
														<td width="20%"><c:out
																value="${entityvalue.villageCode}" escapeXml="true"></c:out>
														</td>
														<td width="40%"><c:out
																value="${entityvalue.villageNameEnglish}" escapeXml="true"></c:out>
														</td>
														<td width="40%"><c:out
																value="${entityvalue.villageNameLocal}" escapeXml="true"></c:out>
														</td>
													</tr>
												</c:forEach>
											</table></td>
									</tr>
								</c:forEach>
							</table>

						</div>
					</div>
				</c:if>
				<c:if test="${not empty blockBeanMerge.blockNameEnglish}">
					<div class="frmpnlbg">
						<div class="frmtxt">

							<table class="tbl_with_brdr" width="98%">
								<tr class="tblRowTitle tblclear">
									<td width="15%" colspan="4"><spring:message
											htmlEscape="true" code="Label.TARGTBLOCK"></spring:message>
									</td>

								</tr>
								<tr class="tblRowTitle tblclear">
									<td width="15%"><spring:message htmlEscape="true"
											code="Label.BLOCKCODE"></spring:message>
									</td>
									<td width="15%"><spring:message htmlEscape="true"
											code="Label.BLOCKVER"></spring:message>
									</td>
									<td width="35%"><spring:message htmlEscape="true"
											code="Label.BLOCKNAMEINENGLISH"></spring:message>
									</td>
									<td width="35%"><spring:message htmlEscape="true"
											code="Label.BLOCKNAMEINLOCAL"></spring:message>
									</td>
								</tr>
								<tr class="tblRowB">
									<td width="15%"><c:out
											value="${blockBeanMerge.blockPK.blockCode}" escapeXml="true"></c:out>
									</td>
									<td width="15%"><c:out
											value="${blockBeanMerge.blockPK.blockVersion}" escapeXml="true"></c:out>
									</td>
									<td width="35%"><c:out
											value="${blockBeanMerge.blockNameEnglish}" escapeXml="true"></c:out>
									</td>
									<td width="35%"><c:out
											value="${blockBeanMerge.blockNameLocal}" escapeXml="true"></c:out>
									</td>
								</tr>
							</table>

						</div>
					</div>
				</c:if>
				<div class="btnpnl">
					<table width="100%" class="tbl_no_brdr">
						<tr>
							<td width="16%" rowspan="2">&nbsp;</td>
							<td width="84%" align="center"><label> <input
									type="button" name="close" class="btn"
									value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
									id="btnCancel"
									onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'" />
							</label></td>
						</tr>
					</table>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>

