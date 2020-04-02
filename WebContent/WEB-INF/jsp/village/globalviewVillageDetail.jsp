<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.io.File"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<%@include file="../common/taglib_includes.jsp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.owasp.esapi.ESAPI"%>
<%@ page import="in.nic.pes.lgd.bean.Attachment"%>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<title><spring:message htmlEscape="true" code="Label.VIEWVILLAGE"></spring:message></title>

<script language="javascript" type="text/javascript">

/* Retrieve the order details */
function retrieveFile(fileNameToDownload){
	//alert("hello"+fileNameToDownload);
	
		//alert("Inside the correct Method");
        document.getElementById("fileNameToDownload").value=fileNameToDownload;
        document.frmModifyVillage.method="post";
        document.frmModifyVillage.action="reportFileDownloads.do?<csrf:token
		uri='reportFileDownloads.do'/>";
		document.frmModifyVillage.submit();
        return true;
}

/* Retrieve the Map details */
function retrieveMapFile(fileNameToDownload){
	//alert("hello"+fileNameToDownload);
	
		//alert("Inside the Second correct Method");
        document.getElementById("fileNameToDownload").value=fileNameToDownload;
        document.frmModifyVillage.method="post";
        document.frmModifyVillage.action="mapFileDownloads.do?<csrf:token
		uri='mapFileDownloads.do'/>";
		document.frmModifyVillage.submit();
        return true;
}

</script>

</head>
<body>
<div id="contentin">
	<%
		String requiredTitleValue = "no";
	%>
	<%
		List<Attachment> listOFMetaData = (List<Attachment>) request
				.getAttribute("attachmentList");
	%>
	
	<div class="frmhd">
		<h3 class="subtitle"><spring:message htmlEscape="true" code="Label.VIEWVILLAGE"></spring:message></h3>					
	</div>
					
	<div id="frmcontent">
		
		<div class="frmpnlbrdr">
						<form:form action="viewVillageAction.do" method="POST"
							commandName="villageView" id="frmModifyVillage"
							name="frmModifyVillage">
							<input type="hidden" name="<csrf:token-name/>"
								value="<csrf:token-value uri="viewVillageAction.do"/>" />
							<div class="frmpnlbg">
								<input type="hidden" id="fileNameToDownload"
									name="fileNameToDownload" /> <input type="hidden"
									id="orderCode" name="orderCode" />
								<div class="frmtxt">
									<div class="frmhdtitle">
										<spring:message htmlEscape="true" code="Label.VILLDETAIL"></spring:message>
									</div>
									
									<div class="reports_popup"> <!-- Reports starts  -->
										<c:forEach var="listVillageDetails"
											varStatus="listVillageDetailsRow"
											items="${villageView.listVillageDetails}">
										<div class="block">
											<div class="frmpnlbg">
														<div class="frmtxt">
															<div class="frmhdtitle">
																<spring:message code="Label.GENERALDETAILS"
																	htmlEscape="true"></spring:message>
															</div>
															
															<ul class="report_list">
																<li>
																	<label class="title">
																	<spring:message
																			htmlEscape="true" code="Label.VILLAGECODE"></spring:message>
																	</label>
																	<div class="value">
																		<spring:bind
																				path="villageView.listVillageDetails[${listVillageDetailsRow.index}].villageCode">
																				                    <c:out value="${status.value}"
																					escapeXml="true" />
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.VILLAGEVERSION"></spring:message>
																	</label>
																	<div class="value">
																		<spring:bind
																				path="villageView.listVillageDetails[${listVillageDetailsRow.index}].village_version">
																				                    <c:out value="${status.value}"
																					escapeXml="true" />
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.VILLAGENAMEINENGLISH"></spring:message>
																	</label>
																	<div class="value">
																		<spring:bind
																				path="villageView.listVillageDetails[${listVillageDetailsRow.index}].villageNameEnglish">
																			                       <c:out value="${status.value}"
																					escapeXml="true" />
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.VILLAGENAMEINLOCAL"></spring:message>
																	</label>
																	<div class="value">
																		<spring:bind
																				path="villageView.listVillageDetails[${listVillageDetailsRow.index}].villageNameLocal">
																				                  <c:out value="${status.value}"
																					escapeXml="true" />
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.ALIASENGLISH"></spring:message>
																	</label>
																	<div class="value">
																		<spring:bind
																				path="villageView.listVillageDetails[${listVillageDetailsRow.index}].aliasEnglish">
																				                  <c:out value="${status.value}"
																					escapeXml="true" />
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.ALIASLOCAL"></spring:message>
																	</label>
																	<div class="value">
																		<spring:bind
																				path="villageView.listVillageDetails[${listVillageDetailsRow.index}].aliasLocal">
																				                  <c:out value="${status.value}"
																					escapeXml="true" />
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.CENSUS2001"></spring:message>
																	</label>
																	<div class="value">
																		<spring:bind
																				path="villageView.listVillageDetails[${listVillageDetailsRow.index}].census2001Code">
																				                <c:out value="${status.value}"
																					escapeXml="true" />
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.CENSUSCODE2011"></spring:message>
																	</label>
																	<div class="value">
																		<spring:bind
																				path="villageView.listVillageDetails[${listVillageDetailsRow.index}].census2011Code">
																				               <c:out value="${status.value}"
																					escapeXml="true" />
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.STATESPECIFICCODE"></spring:message>
																	</label>
																	<div class="value">
																		<spring:bind
																				path="villageView.listVillageDetails[${listVillageDetailsRow.index}].sscode">
																				              <c:out value="${status.value}"
																					escapeXml="true" />
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li>
															</ul>
															
															
														</div>
													</div>
										</div>
										
										<div class="block">
											<div class="frmpnlbg">
														<div class="frmtxt">
															<div class="frmhdtitle">
																<spring:message code="Label.GOVTORDERDETAILS"
																	htmlEscape="true"></spring:message>
															</div>
															
															<ul class="report_list">
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.ORDERNO"></spring:message>
																	</label>
																	<div class="value">
																		<spring:bind
																				path="villageView.listVillageDetails[${listVillageDetailsRow.index}].orderNocr">
																							<c:out value="${status.value}" escapeXml="true" />
																			</spring:bind>
																			<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.ORDERDATE"></spring:message>
																	</label>
																	<div class="value">
																		
																	</div><fmt:formatDate
																			var="orderDatecr"
																			value="${listVillageDetails.orderDatecr}"
																			pattern="dd/MM/yyyy" /><c:out value="${orderDatecr}" escapeXml="true"></c:out>																	
																	<div class="errormsg"></div>
																</li>
																
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.EFFECTIVEDATE"></spring:message>
																	</label>
																	<div class="value">
																		<fmt:formatDate
																			var="ordereffectiveDatecr"
																			value="${listVillageDetails.ordereffectiveDatecr}"
																			pattern="dd/MM/yyyy" /><c:out value="${ordereffectiveDatecr}" escapeXml="true"></c:out>
																		<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.GAZPUBDATE"></spring:message>
																	</label>
																	<div class="value">
																		<fmt:formatDate
																			var="gazPubDatecr"
																			value="${listVillageDetails.gazPubDatecr}"
																			pattern="dd/MM/yyyy" /><c:out value="${gazPubDatecr}" escapeXml="true"></c:out>
																	
																	<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<c:if test="${attachmentList.size() > 0 }">
																<table style="margin-left: 30px;" class="tbl_with_brdr"
																	width="50%" align="center">

																	<tr class="tblRowTitle tblclear">
																		<td width="5%"><spring:message
																				code="Label.FILENAME"></spring:message></td>
																		<td width="16%"><spring:message
																				code="Label.FILESIZE"></spring:message>
																		</td>
																		<td width="10%"><spring:message
																				code="addAttachment.filecontenttype"></spring:message>
																		</td>
																		<td width="10%" align="center"><spring:message
																				code="Label.VIEW"></spring:message>
																		</td>
																	</tr>
																	<%
																		try {
																						Iterator<Attachment> it = listOFMetaData.iterator();
																						while (it.hasNext()) {
																							Attachment abc = (Attachment) it.next();
																	%>
																	<tr id="row<%=abc.getAttachmentId()%>" class="tblRowB">
																		<%
																			if ("yes".equalsIgnoreCase(requiredTitleValue)) {
																		%>
																		<td><%=ESAPI.encoder().encodeForHTML(
											abc.getFileTitle())%></td>
																		<%
																			}
																		%>
																		<td><%=ESAPI.encoder().encodeForHTML(
										abc.getFileName())%></td>
																		<td><%=abc.getFileSize()%></td>
																		<td><%=abc.getFileContentType()%></td>
																		<td width="10%" align="center"><a href="#"><img
																				src="images/view.png" name="viewAttachment"
																				width="22" height="18" border="0"
																				onclick="javascript:retrieveFile('<%=abc.getFileTimestamp()%>');" />
																		</a>
																		</td>

																	</tr>
																	<%
																		}
																	%>
																	<%
																		} catch (Exception e) {
																					}
																	%>
																</table>
															</c:if>
																<div class="errormsg"><form:errors path="noAttachmentRecord" />
																<form:errors path="noOrderRecord" /></div>
																</li>
															</ul>
															
														</div>
													</div>
													
													<div class="frmpnlbg"> <!-- Map Starts -->
														<div class="frmtxt">
															<div class="frmhdtitle">
																<spring:message code="Label.VIEW.MAP" htmlEscape="true"></spring:message>
															</div>
															<c:if test="${mapAttachmentList.size() > 0 }">
																<table class="tbl_with_brdr" width="100%" >

																<tr class="tblRowTitle tblclear">
																	<td width="5%"><spring:message code="Label.FILENAME"></spring:message>
																	</td>
																	<td width="10%"><spring:message
																			code="Label.FILESIZE"></spring:message></td>
																	<td width="10%"><spring:message
																			code="addAttachment.filecontenttype"></spring:message></td>
																	<%-- <td width="10%" align="center"><spring:message
																			code="Label.VIEW.GOVERNMENTORDER"></spring:message></td> --%>
																	<td width="10%" align="center"><spring:message
																			code="Label.VIEW.GOVERNMENTORDER"></spring:message></td>
																</tr>
																<!-- Getting Values in the form of the Table -->
																<c:forEach var="listMapDetails" varStatus="listMapRow"
																	items="${mapAttachmentList}">
																	<tr class="tblRowB">
																		<td><c:out value="${listMapDetails.getFileName()}" escapeXml="true"></c:out></td>
																		<td align="left"><c:out value="${listMapDetails.getFileSize()}" escapeXml="true"></c:out></td>
																		<td align="left"><c:out value="${listMapDetails.getFileContentType()}" escapeXml="true"></c:out></td>
																		<%-- <td align="left">${listMapDetails.getImagePath()}</td> --%>
																		<td width="10%" align="center"><a href="#"><img
																				src="images/view.png" name="viewMapAttachment"
																				width="22" height="18" border="0"
																				onclick="javascript:retrieveMapFile('${listMapDetails.getFileTimestamp()}');" />
																		</a></td>
																	</tr>
																</c:forEach>
															</table>
															</c:if>
																<div class="errormsg"><form:errors path="noMapRecord" cssStyle="color:red" /></div>
															
														</div>
													</div>
										</div>
										
										<div class="clear"></div>
										
										</c:forEach>
									</div> <!-- Reports ends  -->
								</div>

								
							</div>
						</form:form>
						<script src="/LGD/JavaScriptServlet"></script>
					</div>
		
		<c:if test="${saveMsg != null}">
			<script>
			alert("<c:out value="${saveMsg}"/>");
		</script>
		</c:if>
		
		
	</div>

</div>
</body>
</html>