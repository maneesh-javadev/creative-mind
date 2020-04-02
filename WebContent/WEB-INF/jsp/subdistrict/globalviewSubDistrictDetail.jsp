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
<script language="javascript" type="text/javascript">

/* Retrieve the Order Details */

function retrieveFile(fileNameToDownload){
	//alert("hello"+fileNameToDownload);
	
		//alert("Inside the correct Method");
        document.getElementById("fileNameToDownload").value=fileNameToDownload;
        document.frmModifySubDistrict.method="post";
        document.frmModifySubDistrict.action="reportFileDownloads.do?<csrf:token
		uri='reportFileDownloads.do'/>";
		document.frmModifySubDistrict.submit();
        return true;
}

/* Retrieve the Order Details */
 
function retrieveMapFile(fileNameToDownload){
	//alert("hello"+fileNameToDownload);
	
		//alert("Inside the Second correct Method");
        document.getElementById("fileNameToDownload").value=fileNameToDownload;
        document.frmModifySubDistrict.method="post";
   
        document.frmModifySubDistrict.action="mapFileDownloads.do?<csrf:token
		uri='mapFileDownloads.do'/>";
		document.frmModifySubDistrict.submit();
        return true;
}

</script>

<style type="text/css">
    .divOuter{
        display:inline;
        text-align:center;
    }

    .divInner1, .divInner2, .divInner3{
        border: 1px solid;
        float:left;
        width:150px;
        height:150px;
        margin-left:3px;
        margin-right:3px;
    }
    </style>
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
		<h3 class="subtitle"><spring:message htmlEscape="true" code="Label.VIEWSUBDISTRICT"></spring:message></h3>					
	</div>

	<div id="frmcontent" class="frmpnlbrdr">
			<form:form action="modifySubDistrictAction.htm" method="POST" commandName="modifySubDistrictCmd" id="frmModifySubDistrict" name="frmModifySubDistrict">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifySubDistrictAction.htm"/>" />
				<div class="frmpnlbg">
					<input type="hidden" id="fileNameToDownload" name="fileNameToDownload" /> 
					<input type="hidden" id="orderCode" name="orderCode" />
						<div class="frmtxt">
							<div class="frmhdtitle"><spring:message htmlEscape="true" code="Label.SUBDISTRICTDETAIL"></spring:message></div>
							
							<div class="reports_popup"> <!-- Reports starts  -->
								<c:forEach var="listSubdistrictDetails" varStatus="listSubdistrictDetailsRow" items="${modifySubDistrictCmd.listSubdistrictDetails}">
								<div class="block">
									<div class="frmpnlbg">
										<div class="frmtxt">
											<div class="frmhdtitle"><spring:message code="Label.GENERALDETAILS" htmlEscape="true"></spring:message></div>
											
											<ul class="report_list">
												<li>
													<label class="title"><spring:message htmlEscape="true" code="Label.SUBDISTRICTCODE"></spring:message></label>
													<div class="value">
														<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictCode"><c:out value="${status.value}" escapeXml="true"></c:out></spring:bind>
														<div class="errormsg"></div>
													</div>
												</li>
												
												<li>
													<label class="title"><spring:message htmlEscape="true" code="Label.SUBDISTRICTVERSION"></spring:message></label>
													<div class="value">
														<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictVersion"><c:out value="${status.value}" escapeXml="true"></c:out></spring:bind>
														<div class="errormsg"></div>
													</div>
												</li>
												
												<li>
													<label class="title"><spring:message htmlEscape="true" code="Label.SUBDISTRICTNAMEENGLISH"></spring:message></label>
													<div class="value">
														<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictNameEnglish"><c:out value="${status.value}" escapeXml="true"></c:out></spring:bind>
														<div class="errormsg"></div>
													</div>
												</li>
												
												<li>													
													<label class="title"><spring:message htmlEscape="true" code="Label.SUBDISTRICTNAMELOCAL"></spring:message></label>
													<div class="value">
														<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictNameLocal"><c:out value="${status.value}" escapeXml="true"></c:out></spring:bind>
														<div class="errormsg"></div>
													</div>
												</li>
												
												<li>
													<label class="title"><spring:message htmlEscape="true" code="Label.SUBDISTRICTALIASENGLISH"></spring:message></label>
													<div class="value">
														<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].aliasEnglish">&nbsp;<c:out value="${status.value}" escapeXml="true"></c:out></spring:bind>
														<div class="errormsg"></div>
													</div>
												</li>
												
												<li>
													<label class="title"><spring:message	htmlEscape="true" code="Label.SUBDISTRICTALIASLOCAL"></spring:message></label>
													<div class="value">
														<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].aliasLocal">&nbsp;<c:out value="${status.value}" escapeXml="true"></c:out></spring:bind>
														<div class="errormsg"></div>
													</div>
												</li>
												
												<li>
													<label class="title"><spring:message	htmlEscape="true" code="Label.HEADQUARTER"></spring:message></label>
													<div class="value">
														<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].headquarterName">&nbsp;<c:out	value="${status.value}" escapeXml="true"></c:out></spring:bind>
														<div class="errormsg"></div>
													</div>
												</li>
												
												<li>
													<label class="title"><spring:message	htmlEscape="true" code="Label.CENSUS2001"></spring:message></label>
													<div class="value">
														<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].census2001Code"><c:out value="${status.value}" escapeXml="true"></c:out></spring:bind>
														<div class="errormsg"></div>
													</div>
												</li>
												
												<li>
													<label class="title"><spring:message	htmlEscape="true" code="Label.CENSUSCODE2011"></spring:message></label>
													<div class="value">
														<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].census2011Code"><c:out value="${status.value}" escapeXml="true"></c:out></spring:bind>
														<div class="errormsg"></div>
													</div>
												</li>
												
												<li>
													<label class="title"><spring:message htmlEscape="true" code="Label.STATESPECIFICCODE"></spring:message></label>
													<div class="value">
														<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].sscode"><c:out value="${status.value}" escapeXml="true"></c:out></spring:bind>
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
													<div class="frmhdtitle"><spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message></div>
													
													<ul class="report_list">
														<li>
															<label class="title"><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message></label>
															<div class="value">
																<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderNocr"><c:out value="${status.value}" escapeXml="true" /></spring:bind>
																<div class="errormsg"></div>
															</div>															
														</li>
														
														<li>
														</td>
															<label class="title"><spring:message htmlEscape="true" code="Label.ORDERDATE"></spring:message></label>
															<div class="value">
																<fmt:formatDate var="orderDatecr" value="${listSubdistrictDetails.orderDatecr}" pattern="dd/MM/yyyy" /><c:out value="${orderDatecr}" escapeXml="true"></c:out>
																<div class="errormsg"></div>
															</div>
														</li>
														
														<li>
															<label class="title"><spring:message htmlEscape="true" code="Label.EFFECTIVEDATE"></spring:message></label>
															<div class="value">
																<fmt:formatDate var="ordereffectiveDatecr" value="${listSubdistrictDetails.ordereffectiveDatecr}" pattern="dd/MM/yyyy" /><c:out value="${ordereffectiveDatecr}" escapeXml="true"></c:out>
																<div class="errormsg"></div>
															</div>
														</li>
														
														<li>
															<label class="title"><spring:message	htmlEscape="true" code="Label.GAZPUBDATE"></spring:message></label>
															<div class="value">
																<fmt:formatDate var="gazPubDatecr"	value="${listSubdistrictDetails.gazPubDatecr}" pattern="dd/MM/yyyy hh:mm:ss" /><c:out value="${gazPubDatecr}" escapeXml="true"></c:out>
																<div class="errormsg"></div>
															</div>
														</li>
														
														<li>
															<c:if test="${attachmentList.size() > 0 }">
														<table  class="tbl_with_brdr" width="100%">
															<tr class="tblRowTitle tblclear">
																		<td width="5%"><spring:message	code="Label.FILENAME"></spring:message></td>
																		<td width="16%"><spring:message	code="Label.FILESIZE"></spring:message></td>
																		<td width="10%"><spring:message	code="addAttachment.filecontenttype"></spring:message></td>
																		<td width="10%" align="center"><spring:message code="Label.VIEW.GOVERNMENTORDER"></spring:message></td>
															</tr>
																<%
																		try {
																			Iterator<Attachment> it = listOFMetaData.iterator();
																			while (it.hasNext()) 
																				{
																			Attachment abc = (Attachment) it.next();
																%>
															<tr id="row<%=abc.getAttachmentId()%>" class="tblRowB">
																<%
																		if ("yes".equalsIgnoreCase(requiredTitleValue))
																		{
																%>
																<td><%=ESAPI.encoder().encodeForHTML(abc.getFileTitle())%></td>
																<%
																		}
																%>
																<td><%=ESAPI.encoder().encodeForHTML(abc.getFileName())%></td>
																<td><%=abc.getFileSize()%></td>
																<td><%=abc.getFileContentType()%></td>
																<td width="10%" align="center"><a href="#"><img	src="images/view.png" name="viewAttachment"	width="22" height="18" border="0" onclick="javascript:retrieveFile('<%=abc.getFileTimestamp()%>');" /></a></td>

															</tr>
																<%
																				}
																%>
																<%
																			} catch (Exception e)
																			{}
																%>
															</table>
													</c:if>
														<div class="errormsg"><form:errors path="noAttachmentRecord" />
														<form:errors path="noOrderRecord" /></div>
														</li>
													</ul>
													
												</div>
											</div>
											
											<div class="frmpnlbg">
												<div class="frmtxt">
													<div class="frmhdtitle"><spring:message code="Label.VIEW.MAP" htmlEscape="true"></spring:message></div>
													<c:if test="${mapAttachmentList.size() > 0 }">
														<table class="tbl_with_brdr" width="100%">
															<tr class="tblRowTitle tblclear">
																	<td width="5%"><spring:message code="Label.FILENAME"></spring:message></td>
																	<td width="10%"><spring:message	code="Label.FILESIZE"></spring:message></td>
																	<td width="10%"><spring:message	code="addAttachment.filecontenttype"></spring:message></td>
																	<td width="10%" align="center"><spring:message	code="Label.VIEW.GOVERNMENTORDER"></spring:message></td>
															</tr>
																<!-- Getting Values in the form of the Table -->
															<c:forEach var="listMapDetails" varStatus="listMapRow"	items="${mapAttachmentList}">
															<tr class="tblRowB">
																	<td><c:out value="${listMapDetails.getFileName()}" escapeXml="true"></c:out></td>
																	<td align="left"><c:out value="${listMapDetails.getFileSize()}" escapeXml="true"></c:out></td>
																	<td align="left"><c:out value="${listMapDetails.getFileContentType()}" escapeXml="true"></c:out></td>
																	<td width="10%" align="center"><a href="#"><img	src="images/view.png" name="viewMapAttachment" width="22" height="18" border="0" onclick="javascript:retrieveMapFile('${listMapDetails.getFileTimestamp()}');" /></a></td>
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
							</div> <!-- Reports ends  -->
							
						</div>
					</div>
				</form:form>
						
				<script src="/LGD/JavaScriptServlet"></script>
			</div>

</div>
</body>
</html>