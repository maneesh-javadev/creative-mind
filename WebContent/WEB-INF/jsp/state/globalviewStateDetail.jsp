<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ page import="in.nic.pes.lgd.bean.Attachment"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.owasp.esapi.ESAPI"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
</script>

<script language="javascript" type="text/javascript">

/* Retrieve the order details */

function retrieveFile(fileNameToDownload){
	//alert("hello"+fileNameToDownload);
	
		//alert("Inside the correct Method");
        document.getElementById("fileNameToDownload").value=fileNameToDownload;
        document.frmViewState.method="post";
        document.frmViewState.action="reportFileDownloads.do?<csrf:token uri='reportFileDownloads.do'/>";
		document.frmViewState.submit();
        return true;
}

/* Retrieve the Map details */

function retrieveMapFile(fileNameToDownload){
	//alert("hello"+fileNameToDownload);
	
		//alert("Inside the Second correct Method");
        document.getElementById("fileNameToDownload").value=fileNameToDownload;
        document.frmViewState.method="post";
    
        document.frmViewState.action="mapFileDownloads.do?<csrf:token uri='mapFileDownloads.do'/>";
		document.frmViewState.submit();
        return true;
}

function goBack()
{
	/* alert("Inside the Back function");
	alert("captchaAlreadyEntered" +captchaAlreadyEntered); */
	document.getElementById('frmViewState').method = "post";
	document.getElementById('frmViewState').action = "globalviewstateforcitizen.do?<csrf:token uri='globalviewstateforcitizen.do'/>";
	document.getElementById('frmViewState').submit();
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
		<h3 class="subtitle"><spring:message code="Label.VIEWSTATE" htmlEscape="true"></spring:message></h3>					
	</div>
	
	<div class="frmpnlbrdr">
					<form:form action="viewStateAction.htm" method="POST"
						commandName="stateForm" id="frmViewState" name="frmViewState">
						<input type="hidden" name="<csrf:token-name/>"
							value="<csrf:token-value uri="viewStateAction.htm"/>" />
							<input type="hidden" value="1" name="captchaAlreadyEntered"/>
						<div class="frmpnlbg">
							<input type="hidden" id="fileNameToDownload"
								name="fileNameToDownload" /> <input type="hidden"
								id="orderCode" name="orderCode" />
							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message code="Label.STATEDETAIL" htmlEscape="true"></spring:message>
								</div>
								
								<div class="reports_popup"> <!-- Reports starts  -->
								<c:forEach var="listStateDetails" varStatus="listStateDetailsRow" items="${stateForm.listStateDetails}">
									<div class="block">
										<div class="frmpnlbg">
													<div class="frmtxt">
														<div class="frmhdtitle">
															<spring:message code="Label.GENERALDETAILS"
																htmlEscape="true"></spring:message>
														</div>
														<ul class="report_list">
															<li>
																<label class="title"><spring:message	code="Label.STATECODE" htmlEscape="true"></spring:message></label>
																<div class="value"><spring:bind path="stateForm.listStateDetails[${listStateDetailsRow.index}].stateCode">
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
																		</spring:bind> 
																	<div class="errormsg"></div>
																</div>
															</li>
															<li>
																<label class="title"><spring:message code="Label.STATEVERSION" htmlEscape="true"></spring:message></label>
																<div class="value">
																	<spring:bind path="stateForm.listStateDetails[${listStateDetailsRow.index}].stateVersion">&nbsp;
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
																		</spring:bind>
																		<div class="errormsg"></div>
																</div>
															</li>
															<li>
															  <label class="title"><spring:message code="Label.STATENAMEINENGLISH" htmlEscape="true"></spring:message></label>
																<div class="value"><spring:bind
																		path="stateForm.listStateDetails[${listStateDetailsRow.index}].stateNameEnglish">
																			               <c:out value="${status.value}" escapeXml="true"></c:out>
																	</spring:bind> 
																	<div class="errormsg"></div>
																</div>
															</li>
															<li>
																<label class="title"><spring:message code="Label.STATENAMEINLOCAL" htmlEscape="true"></spring:message></label>
																<div class="value">
																	<spring:bind
																			path="stateForm.listStateDetails[${listStateDetailsRow.index}].stateNameLocal">
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
																		</spring:bind>
																		<div class="errormsg"></div>
																</div>
															</li>
															<li>
																<label class="title"><spring:message code="Label.STATEALIASENGLISH" htmlEscape="true"></spring:message>
																</label>
																<div class="value">
																	<spring:bind
																			path="stateForm.listStateDetails[${listStateDetailsRow.index}].aliasEnglish">
																			                   <c:out value="${status.value}" escapeXml="true"></c:out>
																		</spring:bind>
																		<div class="errormsg"></div>
																</div>
															</li>
															<li>
																<label class="title">
																	<spring:message
																		code="Label.STATEALIASLOCAL" htmlEscape="true"></spring:message>
																</label>
																<div class="value">
																	<spring:bind
																			path="stateForm.listStateDetails[${listStateDetailsRow.index}].aliasLocal">
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
																		</spring:bind>
																		<div class="errormsg"></div>
																</div>
															</li>
															<li>
																<label class="title">
																	<spring:message
																		code="Label.CENSUS2001" htmlEscape="true"></spring:message>
																</label>
																<div class="value">
																	<spring:bind
																			path="stateForm.listStateDetails[${listStateDetailsRow.index}].census2001Code">
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
																		</spring:bind>
																	<div class="errormsg"></div>
																</div>
															</li>
															<li>
																<label class="title">
																	<spring:message
																		code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message>
																</label>
																<div class="value">
																	<spring:bind
																			path="stateForm.listStateDetails[${listStateDetailsRow.index}].census2011Code">
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
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
																<label class="title"><spring:message
																			htmlEscape="true" code="Label.ORDERNO"></spring:message></label>
																<div class="value">
																	<spring:bind
																				path="stateForm.listStateDetails[${listStateDetailsRow.index}].orderNocr">&nbsp;
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
																	<fmt:formatDate
																			var="orderDatecr"
																			value="${listStateDetails.orderDatecr}"
																			pattern="dd/MM/yyyy" /><c:out value="${orderDatecr}" escapeXml="true"></c:out>
																	<div class="errormsg"></div>
																			
																</div>
															</li>
															
															<li>
																<label class="title">
																	<spring:message
																			htmlEscape="true" code="Label.EFFECTIVEDATE"></spring:message>
																</label>
																<div class="value">
																	<fmt:formatDate
																			var="ordereffectiveDatecr"
																			value="${listStateDetails.ordereffectiveDatecr}"
																			pattern="dd/MM/yyyy" /><c:out value="${ordereffectiveDatecr}" escapeXml="true"></c:out>'
																		<div class="errormsg"></div>
																</div>
															</li>
															
															<li>
																<label class="title"><spring:message htmlEscape="true" code="Label.GAZPUBDATE"></spring:message></label>
																<div class="value">
																	<fmt:formatDate
																			var="gazPubDatecr"
																			value="${listStateDetails.gazPubDatecr}"
																			pattern="dd/MM/yyyy hh:mm:ss" /><c:out value="${gazPubDatecr}" escapeXml="true"></c:out>
																	<div class="errormsg"></div>
																</div>
															</li>
															
															<li>
																<c:if test="${attachmentList.size() > 0 }">
															<table class="tbl_with_brdr" width="100%" >

																<tr class="tblRowTitle tblclear">
																	<td width="5%"><spring:message
																			code="Label.FILENAME"></spring:message>
																	</td>
																	<td width="16%"><spring:message
																			code="Label.FILESIZE"></spring:message></td>
																	<td width="10%"><spring:message
																			code="addAttachment.filecontenttype"></spring:message>
																	</td>
																	<td width="10%" align="center"><spring:message
																			code="Label.VIEW.GOVERNMENTORDER"></spring:message></td>
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
																			width="22" height="18" alt="View" border="0"
																			onclick="javascript:retrieveFile('<%=abc.getFileTimestamp()%>');" />
																	</a></td>

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
														<div class="errormsg"><form:errors path="noOrderRecord" /></div>
															</li>
														</ul>															
														
														
													</div>
												</div>	
												
												<div class="frmpnlbg"> <!-- Map starts  -->
													<div class="frmtxt">
														<div class="frmhdtitle">
															<spring:message code="Label.VIEW.MAP" htmlEscape="true"></spring:message>
														</div>
														<c:if test="${mapAttachmentList.size() > 0 }">
															<table style="margin-left: 30px;" class="tbl_with_brdr"
																width="50%" align="center">

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
																				width="22" height="18" alt="View" border="0"
																				onclick="javascript:retrieveMapFile('${listMapDetails.getFileTimestamp()}');" />
																		</a></td>
																	</tr>
																</c:forEach>
															</table>
														</c:if>														
														<form:errors path="noMapRecord" cssStyle="color:red" />
													</div>
												</div> <!-- Map starts  -->																		
									</div>
									
									<div class="clear"></div>
																					
									</c:forEach>
								</div> <!-- Reports ends  -->
								
								
							</div>

						</div>
					</form:form>
					<!-- <script src="/LGD/JavaScriptServlet"></script> -->
				</div>
	
	
	<c:if test="${saveMsg != null}">
		<script>
			alert("<c:out value="${saveMsg}"/>");
		</script>
	</c:if>
</div>
</body>
</html>