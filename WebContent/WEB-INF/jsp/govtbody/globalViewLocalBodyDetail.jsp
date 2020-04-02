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
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrStateService.js'></script>
<%@page import="in.nic.pes.lgd.utils.ApplicationConstant"%>
<script type="text/javascript">

</script>
<script language="javascript" type="text/javascript">


function openFileByPath(filePath) {		
	lgdDwrStateService.openFile(filePath, {
		  callback: openFileCallBack  
	});
}




function openFileCallBack(data) {

	if (data == null) {
		alert("File has been moved or deleted.");
	} else {
		// str.substring(3,7)
		if (data.length > 5) {
			var d = data.substring(0, 5);
			if (d == "ERROR") {
				alert("File has been moved or deleted.");
			} else {
				var form = document.frmViewLocalBody;
				var tempTarget = form.target;
				var tempAction = form.action;
				form.target = 'new_download_page';
				form.method = "post";
				form.action = "viewGovtOrder.do?<csrf:token uri='viewGovtOrder.do'/>";
				document.frmViewLocalBody.govfilePath.value = data;
		         document.frmViewLocalBody.fileDisplayType.value = "<%=ApplicationConstant.FILE_INLINE%>";

				if ($.browser.msie) {
					p_windowProperties = "width=700px,height=450px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised";
					newWindow = window.open('', 'new_download_page', p_windowProperties);
					if (newWindow) {
						form.submit();
						form.target = tempTarget;
						form.action = tempAction;
						newWindow.focus();
					} else {
						alert('You must allow popups for this to work.');
					}
				} else if ($.browser.mozilla) {
					form.submit();
				}
				
				else {
					NewWindow = window.open('',"download_page","width=903px,height=527px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised") ;
					form.submit();
				}
			}

		} else {
			alert("File has been moved or deleted.");
		}

	}
}




/* Retrieve the Order Details */
function retrieveFile(fileNameToDownload){
	//alert("hello"+fileNameToDownload);
	
		//alert("Inside the correct Method");
        document.getElementById("fileNameToDownload").value=fileNameToDownload;
        document.frmViewLocalBody.method="post";
        document.frmViewLocalBody.action="reportFileDownloads.htm?<csrf:token
		uri='reportFileDownloads.htm'/>";
		document.frmViewLocalBody.submit();
        return true;
}
/* Retrieve the Map Details */

function retrieveMapFile(fileNameToDownload){
	//alert("hello"+fileNameToDownload);
	
		//alert("Inside the Second correct Method");
        document.getElementById("fileNameToDownload").value=fileNameToDownload;
        document.frmViewLocalBody.method="post";
        
        document.frmViewLocalBody.action="mapFileDownloads.do?<csrf:token
		uri='mapFileDownloads.do'/>";
		document.frmViewLocalBody.submit();
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
		<h3 class="subtitle"><spring:message htmlEscape="true" code="Label.VIEWLOCALBODY"></spring:message></h3>					
	</div>
	
	<div id="frmcontent">
		<div class="frmpnlbrdr">
						<form:form action="viewLocalBodyAction.htm" method="POST"
							commandName="localbodybean" id="frmViewLocalBody" 
							name="frmViewLocalBody">
							<input type="hidden" name="<csrf:token-name/>"
								value="<csrf:token-value uri="viewLocalBodyAction.htm"/>" />
							<div class="frmpnlbg">
								<input type="hidden" name="govfilePath" id="govfilePath" />
									<input type="hidden" name="fileDisplayType"/>
								<input type="hidden" id="fileNameToDownload"
									name="fileNameToDownload" /> <input type="hidden"
									id="orderCode" name="orderCode" />
								<div class="frmtxt">
									<div class="frmhdtitle">
										<spring:message htmlEscape="true" code="Label.LOCALBODYDETAIL"></spring:message>
									</div>
									
									<div class="reports_popup"> <!-- Reports starts  -->
									<c:forEach var="listLocalBodyDetails" varStatus="listLocalBodyDetailsRow" items="${localbodybean.listLocalBodyDetails}">
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
																			htmlEscape="true" code="Label.LOCALBODYCODE"></spring:message>																		
																	</label>
																	<div class="value">
																		<spring:bind
																				path="localbodybean.listLocalBodyDetails[${listLocalBodyDetailsRow.index}].localBodyCode">
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.LOCALBODYVERSION"></spring:message>																		
																	</label>
																	<div class="value">
																		<spring:bind
																				path="localbodybean.listLocalBodyDetails[${listLocalBodyDetailsRow.index}].localBodyVersion">
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.LOCALBODYNAMEENGLISH"></spring:message>																		
																	</label>
																	<div class="value">
																		<spring:bind
																				path="localbodybean.listLocalBodyDetails[${listLocalBodyDetailsRow.index}].localBodyNameEnglish">
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.LOCALBODYNAMELOCAL"></spring:message>																		
																	</label>
																	<div class="value">
																		<spring:bind
																				path="localbodybean.listLocalBodyDetails[${listLocalBodyDetailsRow.index}].localBodyNameLocal">
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message>																		
																	</label>
																	<div class="value">
																		<spring:bind
																				path="localbodybean.listLocalBodyDetails[${listLocalBodyDetailsRow.index}].aliasEnglish">
																				<c:out value="${status.value}" escapeXml="true"></c:out>
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message>																		
																	</label>
																	<div class="value">
																		<spring:bind
																				path="localbodybean.listLocalBodyDetails[${listLocalBodyDetailsRow.index}].aliasLocal">
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li>
																
																<li>
																	<label class="title">
																		<spring:message
																			htmlEscape="true" code="Label.HEADQUARTER"></spring:message>																		
																	</label>
																	<div class="value">
																		<spring:bind
																				path="localbodybean.listLocalBodyDetails[${listLocalBodyDetailsRow.index}].headquarterName">				
								                                                             <c:out
																					value="${status.value}" escapeXml="true"></c:out>
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
																				path="localbodybean.listLocalBodyDetails[${listLocalBodyDetailsRow.index}].sscode">
																				                <c:out value="${status.value}" escapeXml="true"></c:out>
																			</spring:bind>
																		<div class="errormsg"></div>
																	</div>
																</li>
															</ul>															
														</div>
													</div>
													
													<!-- added by sushil on 15-03-2013 -->
													<div class="frmpnlbg">
															<div class="frmtxt">
																<div class="frmhdtitle"><spring:message 	htmlEscape="true" code="Label.lang.region.area.detail" text="Covered Area Detail"></spring:message></div>
																
																<ul class="report_list">
																		<c:if test="${listLocalBodyDetails != null}">
																			<c:forEach var="coveredArea" items="${listLocalBodyDetails.coveredAreaDetailList}">
																				<li>
																					<label class="title"><spring:message htmlEscape="true" code="Label.land.region.code" text="Land region code"></spring:message></label>
																					<div class="value"><c:out value="${coveredArea.langRegionLinkCode}" escapeXml="true"></c:out></div>
																				</li>
																				<li>
																					<label class="title"><spring:message htmlEscape="true" code="Label.census.code" text="Census 2011 code"></spring:message></label>
																					<div class="value"><c:out value="${coveredArea.cencusCode}" escapeXml="true"></c:out></div>
																				</li>
																				<li>
																					<label class="title"><spring:message htmlEscape="true" code="Label.land.region.name.en" text="Land region name english"></spring:message></label>
																					<div class="value"><c:out value="${coveredArea.landRegionNameEn}" escapeXml="true"></c:out></div>
																				</li>
																				<li>
																					<label class="title"><spring:message htmlEscape="true" code="Label.land.region.name.loc" text="Land region name local"></spring:message></label>
																					<div class="value"><c:out value="${coveredArea.langRegionNameLoc}" escapeXml="true"></c:out></div>
																				</li>
																				<li>
																					<label class="title"><spring:message htmlEscape="true" code="Label.land.region.type" text="Land region type"></spring:message></label>
																					<div class="value"><c:out value="${coveredArea.landRegionTypeName}" escapeXml="true"></c:out></div>
																				</li>																																						
																			</c:forEach>
																		</c:if>
															</ul>
															</div>
													</div>
													<!-- end -->
										</div>
										
										<div class="block">
											<div class="frmpnlbg">
														<div class="frmtxt">
															<div class="frmhdtitle">
																<spring:message code="Label.GOVTORDERDETAILS"
																	htmlEscape="true"></spring:message>
															</div>
															
															<c:if test="${attachmentList.size() > 0 }">
																<ul class="report_list">
																	<li>
																	<label class="title"><spring:message
																			htmlEscape="true" code="Label.ORDERNO"></spring:message></label>
																		<div class="value"><spring:bind
																				path="localbodybean.listLocalBodyDetails[${listLocalBodyDetailsRow.index}].orderNocr">&nbsp;
																							<c:out value="${status.value}" escapeXml="true"></c:out>
																			</spring:bind> 
																			<div class="errormsg"></div>
																		</div>
																</li>

																<li>
																	<label class="title"><spring:message
																			htmlEscape="true" code="Label.ORDERDATE"></spring:message></label>
																		<div class="value"><spring:bind
																				path="localbodybean.listLocalBodyDetails[${listLocalBodyDetailsRow.index}].orderDatecr">&nbsp;
																							<c:out value="${status.value}" escapeXml="true"></c:out>
																			</spring:bind> 
																			<div class="errormsg"></div>
																		</div>
																</li>

																<li>
																	<label class="title"><spring:message
																			htmlEscape="true" code="Label.EFFECTIVEDATE"></spring:message></label>
																		<div class="value"><spring:bind
																				path="localbodybean.listLocalBodyDetails[${listLocalBodyDetailsRow.index}].ordereffectiveDatecr">&nbsp;
																							<c:out value="${status.value}" escapeXml="true"></c:out>
																			</spring:bind> </label> </label>
																		<div class="errormsg"></div></td>
																</li>

																<li>
																	<label class="title"><spring:message
																			htmlEscape="true" code="Label.GAZPUBDATE"></spring:message></label>
																		<div class="value"><spring:bind
																				path="localbodybean.listLocalBodyDetails[${listLocalBodyDetailsRow.index}].gazPubDatecr">&nbsp;
																							<c:out value="${status.value}" escapeXml="true"></c:out>
																			</spring:bind>
																		</div>
																</li>
																	<li>
																		<table class="tbl_with_brdr" width="100%">
																			<tr class="tblRowTitle tblclear">
																				<td width="5%"><spring:message	code="Label.FILENAME"></spring:message></td>
																				<td width="16%"><spring:message	code="Label.FILESIZE"></spring:message></td>
																				<td width="10%"><spring:message code="addAttachment.filecontenttype"></spring:message></td>
																				<td width="10%" align="center"><spring:message	code="Label.VIEW.GOVERNMENTORDER"></spring:message></td>
																				<td width="10%" align="center"></td>
																			</tr>
																			<%
																				try {
																								Iterator<Attachment> it = listOFMetaData.iterator();
																								while (it.hasNext()) {
																									Attachment abc = (Attachment) it.next();
																			%>
																			<tr id="row<%=abc.getAttachmentId()%>">
																				<%
																					if ("yes".equalsIgnoreCase(requiredTitleValue)) {
																				%>
																				<td><%=ESAPI.encoder().encodeForHTML(
																						abc.getFileTitle())%> </td>
																				<%
																					}
																				%>
																				<td><%=ESAPI.encoder().encodeForHTML(
																						abc.getFileName())%> </td>
																				<td><%=abc.getFileSize()%> </td>
																				<td><%=abc.getFileContentType()%> </td>
																				<td><a href="#">
																				<img
																						src="images/view.png" name="viewAttachment"
																						width="22" height="18" border="0"
																						onclick="javascript:openFileByPath('${filenameattch}');"></img>
																								
																						
																				</a></td>
																				
																			</tr>
																		</table>																		 
																		 															
																	</li>
																	
																	
																	<%
																		}
																	%>
																	<%
																		} catch (Exception e) {
																					}
																	%>
																</ul>
															</c:if>
															<div class="errormsg"><form:errors path="noAttachmentRecord" />
															<form:errors path="noOrderRecord" /></div>
														</div>
													</div>
													
													<div class="frmpnlbg"> <!-- Map Starts -->
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
	
</div>
</body>
</html>