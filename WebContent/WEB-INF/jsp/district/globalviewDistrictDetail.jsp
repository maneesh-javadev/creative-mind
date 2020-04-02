<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.owasp.esapi.ESAPI"%>
<%@ page import="in.nic.pes.lgd.bean.Attachment"%>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
 
</script>
<script language="javascript" type="text/javascript">

/* Retrieve the Order Details */
function retrieveFile(fileNameToDownload){
	//alert("hello"+fileNameToDownload);
	
		//alert("Inside the correct Method");
        document.getElementById("fileNameToDownload").value=fileNameToDownload;
        document.frmViewDistrict.method="post";
        document.frmViewDistrict.action="reportFileDownloads.do?<csrf:token
		uri='reportFileDownloads.do'/>";
		document.frmViewDistrict.submit();
        return true;
}
/* Retrieve the Map Details */

function retrieveMapFile(fileNameToDownload){
	//alert("hello"+fileNameToDownload);
	
		//alert("Inside the Second correct Method");
        document.getElementById("fileNameToDownload").value=fileNameToDownload;
        document.frmViewDistrict.method="post";
     
        document.frmViewDistrict.action="mapFileDownloads.do?<csrf:token
		uri='mapFileDownloads.do'/>";
		document.frmViewDistrict.submit();
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
		<h3 class="subtitle"><spring:message htmlEscape="true" code="Label.VIEWDISTRICT"></spring:message></h3>					
	</div>
	
	<div class="frmpnlbrdr" id="frmcontent">		
		
						<form:form action="viewDistrictAction.htm" method="POST"
							commandName="districtView" id="frmViewDistrict"
							name="frmViewDistrict">
							<input type="hidden" name="<csrf:token-name/>"
								value="<csrf:token-value uri="viewDistrictAction.htm"/>" />
							<div class="frmpnlbg">
								<input type="hidden" id="fileNameToDownload"
									name="fileNameToDownload" /> <input type="hidden"
									id="orderCode" name="orderCode" />
								<div class="frmtxt">
									<div class="frmhdtitle">
										<spring:message htmlEscape="true" code="Label.DISTRICTDETAIL"></spring:message>
									</div>
									
									<div class="reports_popup"> <!-- Reports starts  -->
										<c:forEach var="listDistrictDetails"
											varStatus="listDistrictDetailsRow"
											items="${districtView.listDistrictDetails}">
										<div class="block">
											<div class="frmpnlbg">
														<div class="frmtxt">
															<div class="frmhdtitle">
																<spring:message code="Label.GENERALDETAILS"
																	htmlEscape="true"></spring:message>
															</div>
															
															<ul class="report_list">
																<li>
																	<label class="title"><spring:message htmlEscape="true" code="Label.DISTRICTCODE"></spring:message></label>
																	<div class="value">
																		<spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].districtCode">
																               <c:out value="${status.value}" escapeXml="true"></c:out>
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<label class="title">
																		<spring:message	htmlEscape="true" code="Label.DISTRICTVERSION"></spring:message>
																	</label>
																	<div class="value">
																		<spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].districtVersion">
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.DISTRICTNAMEENG"></spring:message>
																	</label>
																	<div class="value">
																		<spring:bind
																				path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].districtNameEnglish">
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.DISTRICTNAMELOCAL"></spring:message>																		
																	</label>
																	<div class="value">
																		<spring:bind
																				path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].districtNameLocal">
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.DISTRICTALIASENGLISH"></spring:message>																		
																	</label>
																	<div class="value">
																		<spring:bind
																				path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].aliasEnglish">
																				<c:out value="${status.value}" escapeXml="true"></c:out>
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.DISTRICTALIASLOCAL"></spring:message>																		
																	</label>
																	<div class="value">
																		<spring:bind
																				path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].aliasLocal">
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.HEADQUARTERSDISTRICT"></spring:message>																		
																	</label>
																	<div class="value">
																		<spring:bind
																				path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].headquarterName">						
								                                                             <c:out
																					value="${status.value}" escapeXml="true"></c:out>
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li>
																
																<%-- <li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.CENSUS2001"></spring:message>																		
																	</label>
																	<div class="value">
																		<spring:bind
																				path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].census2001Code">
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
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
																				path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].census2011Code">
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li> --%>
																
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.STATESPECIFICCODE"></spring:message>																		
																	</label>
																	<div class="value">
																		<spring:bind
																				path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].sscode">
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
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.ORDERNO"></spring:message>
																	</label>
																	<div class="value">
																		<spring:bind
																				path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].orderNocr">&nbsp;
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
																			value="${listDistrictDetails.orderDatecr}"
																			pattern="dd/MM/yyyy" /> <c:out value="${orderDatecr}" escapeXml="true"></c:out>																	
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
																			value="${listDistrictDetails.ordereffectiveDatecr}" 
																			pattern="dd/MM/yyyy" /><c:out value= "${ordereffectiveDatecr}" escapeXml="true"></c:out>
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
																			value="${listDistrictDetails.gazPubDatecr}"
																			pattern="dd/MM/yyyy hh:mm:ss" /> <c:out value="${gazPubDatecr}" escapeXml="true"></c:out>
																		<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<c:if test="${attachmentList.size() > 0 }">
																<table class="tbl_with_brdr" width="100%">

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
																				code="Label.VIEW.GOVERNMENTORDER"></spring:message>
																			<c:out value="${abc.getFileTimestamp}" escapeXml="true"></c:out>
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
																				width="22" height="18" alt="View" border="0"
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
													
													<div class="frmpnlbg">  <!-- Map starts  -->
														<div class="frmtxt">
															<div class="frmhdtitle">
																<spring:message code="Label.VIEW.MAP" htmlEscape="true"></spring:message>
															</div>
															<c:if test="${mapAttachmentList.size() > 0 }">
																<table class="tbl_with_brdr" width="100%">

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
																			code="Label.VIEW.MAP"></spring:message></td>
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
															<div class="errormsg"><form:errors path="noMapRecord" /></div>
														</div>
													</div>
										</div>
										
										<div class="clear"></div>
										 
										
										</c:forEach>	
									</div>  <!-- Reports ends  -->
									
								</div>

								
							</div>
						</form:form>
						<script src="/LGD/JavaScriptServlet"></script>
					

	</div>
</div>
</body>
</html>