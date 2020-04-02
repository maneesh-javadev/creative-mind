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
	function loadPage() {
		var mypath = window.location.href;

		document.getElementById('btnback').style.visibility = 'visible';
		document.getElementById('btnCancel').style.visibility = 'visible';
		document.getElementById('btnClose').style.visibility = 'hidden';
		var mySplitResult = mypath.split("&");

		if (mySplitResult[1] != "") {
			var type = mySplitResult[1].replace("type=", "");

			if (type == 'S') {
				document.getElementById('btnback').style.visibility = 'hidden';
				document.getElementById('btnCancel').style.visibility = 'hidden';
				document.getElementById('btnClose').style.visibility = 'visible';

			} else {
				document.getElementById('btnback').style.visibility = 'visible';
				document.getElementById('btnCancel').style.visibility = 'visible';
				document.getElementById('btnClose').style.visibility = 'hidden';
			}
		}

	}
</script>


</head>
<body onload="loadPage();">

	<div id="frmcontent">
		<table width="100%" class="tbl_no_brdr">
			<tr>
				<td width="100%" valign="top" class="tblclear">
					<div class="frmhd">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td><spring:message code="Label.StandardCodeDETAIL" htmlEscape="true"></spring:message>
								</td>
								<%--//these links are not working  <td align="right"><a href="#" class="frmhelp"><spring:message
											code="Label.HELP" htmlEscape="true"></spring:message> </a> --%>
								</td>
							</tr>
						</table>
					</div>
					<div class="frmpnlbrdr">
						<form:form action="modifySubDistrictAction.htm" method="POST"
							commandName="Standard_Update" id="frmModifySubDistrict">
							<div class="frmpnlbg">
								<div class="frmtxt">
									<div class="frmhdtitle">
										<spring:message code="Label.StandardCodeDETAIL" htmlEscape="true"></spring:message>
									</div>
									<table width="100%" class="tbl_no_brdr">
										<c:forEach var="StandardCodes"
											varStatus="listSubdistrictDetailsRow"
											items="${Standard_Update.listVillageDetails}">
											<tr>
												<td width="14%" rowspan="54">&nbsp;</td>
												<td class="lblBold"><spring:message
														code="Label.VILLAGECODE" htmlEscape="true"></spring:message><br /> <label
													class="lblPlain"> <spring:bind
															path="Standard_Update.listVillageDetails[${listSubdistrictDetailsRow.index}].villageCode">&nbsp;
																				<c:out value="${status.value}" escapeXml="true"></c:out>
														</spring:bind> </label>
													<div class="errormsg"></div>
												</td>
											</tr>



											<tr>
												<td class="lblBold"><spring:message
														code="Label.VILLAGENAMEINENGLISH" htmlEscape="true"></spring:message><br /> <label
													class="lblPlain"> <spring:bind
															path="Standard_Update.listVillageDetails[${listSubdistrictDetailsRow.index}].villageNameEnglish">&nbsp;
											                                     <c:out
																value="${status.value}" escapeXml="true"></c:out>
														</spring:bind> </label>
													<div class="errormsg"></div>
												</td>
											</tr>



											<tr>
												<td class="lblBold"><spring:message
														code="Label.VILLAGENAMEINLOCAL" htmlEscape="true"></spring:message><br /> <label
													class="lblPlain"> <spring:bind
															path="Standard_Update.listVillageDetails[${listSubdistrictDetailsRow.index}].villageNameLocal">&nbsp;
																				       <c:out value="${status.value}" escapeXml="true"></c:out>
														</spring:bind> </label>
													<div class="errormsg"></div>
												</td>
											</tr>



											<tr>
												<td class="lblBold"><spring:message
														code="Label.CENCUSCODES" htmlEscape="true"></spring:message><br /> <label
													class="lblPlain"> <spring:bind
															path="Standard_Update.listVillageDetails[${listSubdistrictDetailsRow.index}].census2001Code">&nbsp;
																				       <c:out value="${status.value}" escapeXml="true"></c:out>
														</spring:bind> </label>
											</tr>




											<%-- <tr>
												<td class="lblBold"><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message><br />
													<label class="lblPlain"><spring:bind path="modifyParliamentConstituencyCmd.listParliamentFormDetail[${listSubdistrictDetailsRow.index}].orderNo">&nbsp;
																							<c:out value="${status.value}" />
															   </spring:bind> </label>	
												</label>
												<div class="errormsg"></div></td>
											</tr>
											
											<tr>
												<td class="lblBold"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message><br />
													<label class="lblPlain"><spring:bind path="modifyParliamentConstituencyCmd.listParliamentFormDetail[${listSubdistrictDetailsRow.index}].orderDate">&nbsp;
																							<c:out value="${status.value}" />
															   </spring:bind> </label>	
												</label>
												<div class="errormsg"></div></td>
											</tr>
											
											<tr>
												<td class="lblBold"><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message><br />
													<label class="lblPlain"><spring:bind path="modifyParliamentConstituencyCmd.listParliamentFormDetail[${listSubdistrictDetailsRow.index}].effectiveDateorder">&nbsp;
																							<c:out value="${status.value}" />
															   </spring:bind> </label>	
												</label>
												<div class="errormsg"></div></td>
											</tr>
											
											<tr>
												<td class="lblBold"><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message><br />
													<label class="lblPlain"><spring:bind path="modifyParliamentConstituencyCmd.listParliamentFormDetail[${listSubdistrictDetailsRow.index}].gazPubDate">&nbsp;
																							<c:out value="${status.value}" />
															   </spring:bind> </label>	
												</label>
												<div class="errormsg"></div></td>
											</tr>
											 --%>
											<%-- <tr>
												<td class="lblBold"><spring:message code="Label.UPLOADGOVTORDER" htmlEscape="true"></spring:message><br />
													<label class="lblPlain"><spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderPathcr">&nbsp;
																							<c:out value="${status.value}" />
															   </spring:bind> </label>	
												</label>
												<div class="errormsg"></div></td>
											</tr> --%>

											<%-- 																<tr>	
																	<td class="lblBold"><spring:message code="Label.FLAGCODE" htmlEscape="true"></spring:message><br />
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
											<td>
<!-- BACK button removed - client requirement -->
												<label> <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose"/> </label>
											</td>
										</tr>
									</table>
								</div>

							</div>
						</form:form>
						<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>

					</div></td>
			</tr>
		</table>

	</div>
</body>
</html>