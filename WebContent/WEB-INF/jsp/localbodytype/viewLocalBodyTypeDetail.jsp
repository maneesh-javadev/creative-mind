<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>

</title>
<script type="text/javascript">
 	</script>

</head>
<body>

	<div id="frmcontent">
		<table width="100%" class="tbl_no_brdr">
			<tr>
				<td width="100%" valign="top" class="tblclear">
					<div id="rightpnl">
						<div class="frmhd">
							<h3 class="subtitle">
								<spring:message htmlEscape="true" code="Label.VIEWLOCALBODYTYPE"></spring:message>
							</h3>
							<ul class="listing">
								<!-- <li>
									<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a>
								</li> -->
								<%-- //this link is not working<li> 
									<a href="#" class="frmhelp"><spring:message code="Label.HELP"></spring:message></a>
								</li> --%>
							</ul>
						</div>
						<div class="frmpnlbrdr">
							<form:form action="viewLocalBodyAction.htm" method="POST"
								commandName="viewBodyType" id="frmModifyLocalBody">
								<input type="hidden" name="<csrf:token-name/>"
									value="<csrf:token-value uri="viewLocalBodyAction.htm"/>" />
								<div class="frmpnlbg">
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message htmlEscape="true"
												code="Label.VIEWLOCALGOVTBODYDET"></spring:message>
										</div>
										<ul class = "blocklist" >
											<c:forEach var="listGovtTypeDetails"
												varStatus="listLocalBodyTypeRow"
												items="${viewBodyType.listGovtTypeDetails}">

													<li>
													<div width="14%" rowspan="54"></div>
													<!-- <td class="lblBold"> -->
													<div class="lblBold">
													<spring:message htmlEscape="true"
															code="Label.LOCALBODYTYPECODE"></spring:message><br /> <label
														class="lblPlain"> <spring:bind
																path="viewBodyType.listGovtTypeDetails[${listLocalBodyTypeRow.index}].localBodyTypeCode">&nbsp;
																				<c:out value="${status.value}" escapeXml="true"></c:out>
															</spring:bind> </label>
														<div class="errormsg"></div>
													</div>
													</li>
													<li>
													<div class="lblBold">
													<spring:message htmlEscape="true"
															code="Label.LOCALGOVTBODY"></spring:message><br /> <label
														class="lblPlain"> <spring:bind
																path="viewBodyType.listGovtTypeDetails[${listLocalBodyTypeRow.index}].localBodyTypeName">&nbsp;
																				<c:out value="${status.value}" escapeXml="true"></c:out>
															</spring:bind> </label>
														<div class="errormsg"></div>
												</div>
												</li>
													<li>
													<div class="lblBold">
													<spring:message htmlEscape="true"
															code="Label.CATEGORY"></spring:message><br /> <c:if
															test="${fn:containsIgnoreCase(listGovtTypeDetails.category,'R')}">&nbsp;
                                       						   <label> <c:out
																	value="${status.value}" escapeXml="true"></c:out>Rural
														</c:if></label> <c:if test="${fn:containsIgnoreCase(listGovtTypeDetails.category,'U')}">&nbsp;
                                         						 <label> <c:out
																	value="${status.value}" escapeXml="true"></c:out>Urban
														</c:if></label>
														<div class="errormsg"></div>
														</div>
													</li>
												<li>
													<div class="lblBold">
													<spring:message htmlEscape="true"
															code="Label.LEVEL"></spring:message><br /> <%-- <label class="lblPlain"> <spring:bind path="viewBodyType.listGovtTypeDetails[${listLocalBodyTypeRow.index}].level">&nbsp;
																				<c:out value="${status.value}" />
																	    </spring:bind> 
															    </label> --%> <c:if
															test="${fn:containsIgnoreCase(listGovtTypeDetails.level,'D')}">&nbsp;
                                       						   <label> <c:out
																	value="${status.value}" escapeXml="true"></c:out>District
														</c:if></label> <c:if test="${fn:containsIgnoreCase(listGovtTypeDetails.level,'I')}">&nbsp;
                                         						 <label> <c:out
																	value="${status.value}" escapeXml="true"></c:out>Intermediate
														</c:if></label> <c:if test="${fn:containsIgnoreCase(listGovtTypeDetails.level,'V')}">&nbsp;
                                         						 <label> <c:out
																	value="${status.value}" escapeXml="true"></c:out>Village
														</c:if></label>
														<div class="errormsg"></div><!-- </td> -->
												</div>
											</li>
											</c:forEach>
										</ul>
									</div>
								</div>
								<div class="btnpnl">
									<label>
										<input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/> '"id="btnClose" /> 
									</label>
								</div>
						</div>
					</div></td>
			</tr>
		</table>
	</div>
	</form:form>
	</div>
</body>
</html>