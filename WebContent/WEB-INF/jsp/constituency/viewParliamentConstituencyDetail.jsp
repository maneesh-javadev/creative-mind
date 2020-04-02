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
<script type="text/javascript">
 
		</script>


</head>
<body >

	<div id="frmcontent">
		<table width="100%" class="tbl_no_brdr">
			<tr>
				<td width="100%" valign="top" class="tblclear">
					<div class="frmhd">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td><spring:message htmlEscape="true"
										code="Label.PARCONSTITUENCYDETAIL"></spring:message>
								</td>
								<%--//this link is not working <td align="right"><a href="#" class="frmhelp"><spring:message
											htmlEscape="true" code="Label.HELP"></spring:message> </a> --%>
								</td>
							</tr>
						</table>
					</div>
					<div class="frmpnlbrdr">
						<form:form action="modifySubDistrictAction.htm" method="POST"
							commandName="modifyParliamentConstituencyCmd"
							id="frmModifySubDistrict">
							<div class="frmpnlbg">
								<div class="frmtxt">
									<div class="frmhdtitle">
										<spring:message htmlEscape="true" code="Label.PARCONSTITUENCYDETAIL"></spring:message>
									</div>
									<table width="100%" class="tbl_no_brdr">
										<c:forEach var="Parliamentconstituencymodify"
											varStatus="listSubdistrictDetailsRow"
											items="${modifyParliamentConstituencyCmd.listParliamentFormDetail}">
											<tr>
												<td width="14%" rowspan="54">&nbsp;</td>
												<td class="lblBold"><spring:message htmlEscape="true"
														code="Label.PARLIAMENTCONSTITUENCYNAMEINENG"></spring:message><br /> <label
													class="lblPlain"> <spring:bind
															path="modifyParliamentConstituencyCmd.listParliamentFormDetail[${listSubdistrictDetailsRow.index}].pcNameEnglish">&nbsp;
																				<c:out value="${status.value}" escapeXml="true"></c:out>
														</spring:bind> </label>
													<div class="errormsg"></div>
												</td>
											</tr>



											<tr>
												<td class="lblBold"><spring:message htmlEscape="true"
														code="Label.PARLIAMENTCONSTITUENCYNAMEINLOCAL"></spring:message><br /> <label
													class="lblPlain"> <spring:bind
															path="modifyParliamentConstituencyCmd.listParliamentFormDetail[${listSubdistrictDetailsRow.index}].pcNameLocal">&nbsp;
											                                     <c:out
																value="${status.value}" escapeXml="true"></c:out>
														</spring:bind> </label>
													<div class="errormsg"></div>
												</td>
											</tr>

											<%-- <tr>
																	<td class="lblBold"><spring:message htmlEscape="true"  code="App.PCNAMEIAIE"></spring:message><br />
																		<label class="lblPlain"> <spring:bind path="modifyParliamentConstituencyCmd.listParliamentFormDetail[${listSubdistrictDetailsRow.index}].newParliamentAliasEnglish">&nbsp;
																				  <c:out value="${status.value}" />
																			</spring:bind> </label>
																		<div class="errormsg"></div></td>
																</tr>

																<tr>
																	<td class="lblBold"><spring:message htmlEscape="true"  code="App.PCNAMEIAIL"></spring:message><br />
																		<label class="lblPlain"> <spring:bind path="modifyParliamentConstituencyCmd.listParliamentFormDetail[${listSubdistrictDetailsRow.index}].newParliamentAliasLocal">&nbsp;
																				       <c:out value="${status.value}" />
																			</spring:bind> </label>
																		<div class="errormsg"></div></td>
																</tr> --%>

											<tr>
												<td class="lblBold"><spring:message htmlEscape="true"
														code="Label.ECICODE"></spring:message><br /> <label
													class="lblPlain"> <spring:bind
															path="modifyParliamentConstituencyCmd.listParliamentFormDetail[${listSubdistrictDetailsRow.index}].eciCode">&nbsp;
																				       <c:out value="${status.value}" escapeXml="true"></c:out>
														</spring:bind> </label>
													<div class="errormsg"></div>
												</td>
											</tr>

											<%-- 
																 --%>





											<%-- <tr>
												<td class="lblBold"><spring:message htmlEscape="true"  code="Label.ORDERNO"></spring:message><br />
													<label class="lblPlain"><spring:bind path="modifyParliamentConstituencyCmd.listParliamentFormDetail[${listSubdistrictDetailsRow.index}].orderNo">&nbsp;
																							<c:out value="${status.value}" />
															   </spring:bind> </label>	
												</label>
												<div class="errormsg"></div></td>
											</tr>
											
											<tr>
												<td class="lblBold"><spring:message htmlEscape="true"  code="Label.ORDERDATE"></spring:message><br />
													<label class="lblPlain"><spring:bind path="modifyParliamentConstituencyCmd.listParliamentFormDetail[${listSubdistrictDetailsRow.index}].orderDate">&nbsp;
																							<c:out value="${status.value}" />
															   </spring:bind> </label>	
												</label>
												<div class="errormsg"></div></td>
											</tr>
											
											<tr>
												<td class="lblBold"><spring:message htmlEscape="true"  code="Label.EFFECTIVEDATE"></spring:message><br />
													<label class="lblPlain"><spring:bind path="modifyParliamentConstituencyCmd.listParliamentFormDetail[${listSubdistrictDetailsRow.index}].effectiveDateorder">&nbsp;
																							<c:out value="${status.value}" />
															   </spring:bind> </label>	
												</label>
												<div class="errormsg"></div></td>
											</tr>
											
											<tr>
												<td class="lblBold"><spring:message htmlEscape="true"  code="Label.GAZPUBDATE"></spring:message><br />
													<label class="lblPlain"><spring:bind path="modifyParliamentConstituencyCmd.listParliamentFormDetail[${listSubdistrictDetailsRow.index}].gazPubDate">&nbsp;
																							<c:out value="${status.value}" />
															   </spring:bind> </label>	
												</label>
												<div class="errormsg"></div></td>
											</tr>
											 --%>
											<%-- <tr>
												<td class="lblBold"><spring:message htmlEscape="true"  code="Label.UPLOADGOVTORDER"></spring:message><br />
													<label class="lblPlain"><spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderPathcr">&nbsp;
																							<c:out value="${status.value}" />
															   </spring:bind> </label>	
												</label>
												<div class="errormsg"></div></td>
											</tr> --%>

											<%-- 																<tr>	
																	<td class="lblBold"><spring:message htmlEscape="true"  code="Label.FLAGCODE"></spring:message><br />
																		<label> <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].flagCode">&nbsp;
																				    <c:out value="${status.value}" />
																			</spring:bind> </label>
																		<div class="errormsg"></div>
																	</td>
																</tr> --%>
										</c:forEach>
									</table>
								</div>
								<div class="btnpnl">
									<table width="100%" class="tbl_no_brdr">
										<tr>
											<td><label> <input type="button" name="Close"
													class="btn"
													value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
													id="btnClose"
													onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'" />
											</label></td>
										</tr>
									</table>
								</div>

							</div>
						</form:form>
						<script src="/LGD/JavaScriptServlet"></script>
					</div></td>
			</tr>
		</table>

	</div>
</body>
</html>