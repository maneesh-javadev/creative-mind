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
<title><spring:message htmlEscape="true" code="Label.VIEWWARD"></spring:message>
</title>

<script type="text/javascript">

		</script>

</head>
<body>
	
		<div id="frmcontent">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td width="100%" valign="top" class="tblclear">
						<div class="frmhd">
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td><spring:message htmlEscape="true" code="Label.WARD"></spring:message>
										</td>
										<%--//these links are not working <td align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"
													code="Label.HELP"></spring:message> --%>
										</a>
										</td>
									</tr>
								</table>
							</div>
							<div class="frmpnlbrdr">
								<form:form action="viewWardAction.htm" method="POST"
									commandName="wardViewDetails" id="frmModifyVillage">
									<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewWardAction.htm"/>"/>
									<div class="frmpnlbg">
										<div class="frmtxt">
											<div class="frmhdtitle">
												<spring:message htmlEscape="true" code="Label.WARDDETAILS"></spring:message>
											</div>
											<table width="100%" class="tbl_no_brdr">
												<c:forEach var="listWardDetails"
													varStatus="listWardDetailsRow"
													items="${wardViewDetails.listLBWard}">
													<%-- <tr>
														<td width="14%" rowspan="54">&nbsp;</td>
														<td class="lblBold"><spring:message htmlEscape="true"
																code="Label.VILLAGECODE"></spring:message><br /> <label
															class="lblPlain"> <spring:bind
																	path="wardViewDetails.listLBWard[${listWardDetailsRow.index}].villageCode">&nbsp;
																				                    <c:out value="${status.value}" escapeXml="true"/>
																</spring:bind> </label>
															<div class="errormsg"></div></td>
													</tr>
--%>
                                <td width="14%" rowspan="54">&nbsp;</td>
													<%-- <tr>
														<td class="lblBold"><spring:message htmlEscape="true"
																code="Label.WARDNAME"></spring:message><br /> <label
															class="lblPlain"> <spring:bind
																	path="wardViewDetails.listLBWard[${listWardDetailsRow.index}].village_version">&nbsp;
																				                    <c:out value="${status.value}" escapeXml="true"/>
																</spring:bind> </label>
															<div class="errormsg"></div></td>
													</tr>  --%>

													<tr>

														<td class="lblBold"><spring:message htmlEscape="true"
																code="Label.WARDNAMEINENG"></spring:message><br />
															<label class="lblPlain"> <spring:bind
																	path="wardViewDetails.listLBWard[${listWardDetailsRow.index}].wardNameEnglish">&nbsp;
																			                       <c:out value="${status.value}" escapeXml="true"/>
																</spring:bind> </label>
															<div class="errormsg"></div></td>
													</tr>

													<tr>
														<td class="lblBold"><spring:message htmlEscape="true"
																code="Label.WARDNAMEINLOCAL"></spring:message><br />
															<label class="lblPlain"> <spring:bind
																	path="wardViewDetails.listLBWard[${listWardDetailsRow.index}].wardNameLocal">&nbsp;
																				                  <c:out value="${status.value}" escapeXml="true"/>
																</spring:bind> </label>
															<div class="errormsg"></div></td>
													</tr>

													<tr>
														<td class="lblBold"><spring:message htmlEscape="true"
																code="Label.WARDNUMBER"></spring:message><br /> <label
															class="lblPlain"> <spring:bind
																	path="wardViewDetails.listLBWard[${listWardDetailsRow.index}].wardNumber">&nbsp;
																				                  <c:out value="${status.value}" escapeXml="true"/>
																</spring:bind> </label>
															<div class="errormsg"></div>
														</td>
													</tr>

											 

													<%-- 																<tr>
																	<td class="lblBold"><spring:message htmlEscape="true" code="Label.MAPLANDREGIONCODE"></spring:message><br />
																		     <label> <spring:bind path="villageView.listVillageDetails[${listVillageDetailsRow.index}].mapCode">&nbsp;
																				                 <c:out value="${status.value}" />
																			         </spring:bind>
																	         </label>
																		<div class="errormsg"></div>
																   </td>
																</tr> --%>

													<%-- <tr>
														<td class="lblBold"><spring:message htmlEscape="true"
																code="Label.CENSUS2001"></spring:message><br /> <label
															class="lblPlain"><spring:bind
																	path="wardViewDetails.listLBWard[${listWardDetailsRow.index}].census2001Code">&nbsp;
																				                <c:out value="${status.value}" escapeXml="true"/>
																</spring:bind> </label>
															<div class="errormsg"></div></td>
													</tr>

													<tr>
														<td class="lblBold"><spring:message htmlEscape="true"
																code="Label.CENSUSCODE2011"></spring:message><br /> <label
															class="lblPlain"><spring:bind
																	path="wardViewDetails.listLBWard[${listWardDetailsRow.index}].census2011Code">&nbsp;
																				               <c:out value="${status.value}" escapeXml="true"/>
																</spring:bind> </label>
															<div class="errormsg"></div></td>
													</tr>

													<tr>
														<td class="lblBold"><spring:message htmlEscape="true"
																code="Label.STATESPECIFICCODE"></spring:message><br />
															<label class="lblPlain"><spring:bind
																	path="wardViewDetails.listLBWard[${listWardDetailsRow.index}].sscode">&nbsp;
																				              <c:out value="${status.value}" escapeXml="true"/>
																</spring:bind> </label>
															<div class="errormsg"></div></td>
													</tr>

											<tr>
												<td class="lblBold"><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message><br />
													<label class="lblPlain"><spring:bind path="villageView.listVillageDetails[${listWardDetailsRow.index}].orderNocr">&nbsp;
																							<c:out value="${status.value}" escapeXml="true"/>
															   </spring:bind> </label>	
												</label>
												<div class="errormsg"></div></td>
											</tr>
											
											<tr>
												<td class="lblBold"><spring:message htmlEscape="true" code="Label.ORDERDATE"></spring:message><br />
													<label class="lblPlain"><spring:bind path="villageView.listVillageDetails[${listWardDetailsRow.index}].orderDatecr">&nbsp;
																							<c:out value="${status.value}" escapeXml="true"/>
															   </spring:bind> </label>	
												</label>
												<div class="errormsg"></div></td>
											</tr>
											
											<tr>
												<td class="lblBold"><spring:message htmlEscape="true" code="Label.EFFECTIVEDATE"></spring:message><br />
													<label class="lblPlain"><spring:bind path="villageView.listVillageDetails[${listWardDetailsRow.index}].ordereffectiveDatecr">&nbsp;
																							<c:out value="${status.value}" escapeXml="true"/>
															   </spring:bind> </label>	
												</label>
												<div class="errormsg"></div></td>
											</tr>
											
											<tr>
												<td class="lblBold"><spring:message htmlEscape="true" code="Label.GAZPUBDATE"></spring:message><br />
													<label class="lblPlain"><spring:bind path="villageView.listVillageDetails[${listWardDetailsRow.index}].gazPubDatecr">&nbsp;
																							<c:out value="${status.value}" escapeXml="true"/>
															   </spring:bind> </label>	
												</label>
												<div class="errormsg"></div></td>
											</tr> --%>
											
										 

												</c:forEach>
											</table>
										</div>

										<div class="btnpnl">
											<table width="100%" class="tbl_no_brdr">
												<tr>
													<td>
<!-- BACK button removed - client requirement -->
													  <label>
															<input type="button" name="Submit3" class="btn"
															value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>"
															onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose" /> </label></td>
												</tr>
											</table>
										</div>
									</div>
								  </form:form>	
								   <script src="/LGD/JavaScriptServlet"></script>
							</div>
						</td>
				</tr>
			</table>
		</div>
	
	<!-- </div> -->
</body>
</html>