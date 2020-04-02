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
	<%
		String requiredTitleValue = "no";
	%>
	<%
		List<Attachment> listOFMetaData = (List<Attachment>) request
				.getAttribute("attachmentList");
	%>
	
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message code="Label.VIEWLOCALBODY" htmlEscape="true"></spring:message></h3>
		</div>
		
		
		<div class="frmpnlbrdr">
			<form action="viewLocalBodyAction.htm"  id="frmViewLocalBody" 	name="frmViewLocalBody" method="post" >
			<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="viewLocalBodyAction.htm"/>" />
			<input type="hidden" name="govfilePath" id="govfilePath" />
			<input type="hidden" name="fileDisplayType"/>
			<input type="hidden" id="fileNameToDownload" name="fileNameToDownload" />
			<input type="hidden" id="orderCode" name="orderCode" />
			 <div class="frmpnlbg">
				<div class="frmtxt">
				<div class="frmhdtitle"><spring:message htmlEscape="true" code="Label.LOCALBODYDETAIL"/></div>
				
				
				<c:choose>
				 <c:when test="${!empty localGovtBodyDataFormList}">
					 <c:forEach var="entityDetail" varStatus="entityDetailRow" items="${localGovtBodyDataFormList}">
						
						<div class="frmtxt" style="width:40%;height:50%;display:inline-block;float:left;">
								<div class="frmhdtitle"><spring:message code="Label.GENERALDETAILS" htmlEscape="true"></spring:message></div>
								<div class="search_criteria"> <!-- Seacrh criteria starts -->
									<div class="block" >
										<ul class="blocklist">
										 
											<li>
												<spring:message	htmlEscape="true" code="Label.LOCALBODYCODE"/>
											<label class="lblPlain"> <c:out value="${entityDetail.localBodyCode}" escapeXml="true"></c:out> </label>
											</li>
											<li>
												<spring:message	htmlEscape="true" code="Label.LOCALBODYCODE"/>
												<label class="lblPlain">
													<c:choose>
														<c:when test="${entityDetail.localBodyCode ne null && entityDetail.localBodyCode ne ''}">
															 <c:out value="${entityDetail.localBodyCode}" escapeXml="true"></c:out>
														</c:when>
														<c:otherwise>
															<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
														</c:otherwise>
													</c:choose>
												 </label>
											</li>
											<li>
												<spring:message	htmlEscape="true" code="Label.LOCALBODYVERSION"/>
												<label class="lblPlain">
													<c:choose>
														<c:when test="${entityDetail.localBodyVersion ne null && entityDetail.localBodyVersion ne ''}">
															 <c:out value="${entityDetail.localBodyVersion}" escapeXml="true"></c:out>
														</c:when>
														<c:otherwise>
															<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
														</c:otherwise>
													</c:choose>
												 </label>
											</li>
											<li>
												<spring:message	htmlEscape="true" code="Label.LOCALBODYNAMEENGLISH"/>
												<label class="lblPlain">
													<c:choose>
														<c:when test="${entityDetail.localBodyNameEnglish ne null && entityDetail.localBodyNameEnglish ne ''}">
															 <c:out value="${entityDetail.localBodyNameEnglish}" escapeXml="true"></c:out> 
														</c:when>
														<c:otherwise>
															<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
														</c:otherwise>
													</c:choose>
												 </label>
											</li>
											<li>
												<spring:message	htmlEscape="true" code="Label.LOCALBODYNAMELOCAL"/>
												<label class="lblPlain">
													<c:choose>
														<c:when test="${entityDetail.localBodyNameLocal ne null && entityDetail.localBodyNameLocal ne ''}">
															 <c:out value="${entityDetail.localBodyNameLocal}" escapeXml="true"></c:out> 
														</c:when>
														<c:otherwise>
															<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
														</c:otherwise>
													</c:choose>
												 </label>
											</li>
											<li>
												<spring:message	htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"/>
												<label class="lblPlain">
													<c:choose>
														<c:when test="${entityDetail.aliasEnglish ne null && entityDetail.aliasEnglish ne ''}">
															 <c:out value="${entityDetail.aliasEnglish}" escapeXml="true"></c:out> 
														</c:when>
														<c:otherwise>
															<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
														</c:otherwise>
													</c:choose>
												 </label>
											</li>
											<li>
												<spring:message	htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"/>
												<label class="lblPlain">
													<c:choose>
														<c:when test="${entityDetail.aliasLocal ne null && entityDetail.aliasLocal ne ''}">
															 <c:out value="${entityDetail.aliasLocal}" escapeXml="true"></c:out> 
														</c:when>
														<c:otherwise>
															<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
														</c:otherwise>
													</c:choose>
												 </label>
											</li>
											<li>
												<spring:message	htmlEscape="true" code="Label.HEADQUARTER"/>
												<label class="lblPlain">
													<c:choose>
														<c:when test="${entityDetail.headquarterName ne null && entityDetail.headquarterName ne ''}">
															 <c:out value="${entityDetail.headquarterName}" escapeXml="true"></c:out> 
														</c:when>
														<c:otherwise>
															<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
														</c:otherwise>
													</c:choose>
												 </label>
											</li>
											<li>
												<spring:message	htmlEscape="true" code="Label.MAPLANDREGIONCODE"/>
												<label class="lblPlain">
													<c:choose>
														<c:when test="${entityDetail.mapCode ne null && entityDetail.mapCode ne ''}">
															 <c:out value="${entityDetail.mapCode}" escapeXml="true"></c:out> 
														</c:when>
														<c:otherwise>
															<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
														</c:otherwise>
													</c:choose>
												 </label>
											</li>
											<li>
												<spring:message	htmlEscape="true" code="Label.STATESPECIFICCODE"/>
												<label class="lblPlain">
													<c:choose>
														<c:when test="${entityDetail.sscode ne null && entityDetail.sscode ne ''}">
															 <c:out value="${entityDetail.sscode}" escapeXml="true"></c:out> 
														</c:when>
														<c:otherwise>
															<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
														</c:otherwise>
													</c:choose>
												 </label>
											</li>
																
										</ul>
									</div>
									
									<div class="clear"></div>
									
								</div>
										
							</div>
						
							<div class="frmtxt" style="width:40%;height:40%;display:inline-block;">
								<div class="frmhdtitle"><spring:message 	htmlEscape="true" code="Label.lang.region.area.detail" text="Covered Area Detail"/></div>
								<div class="search_criteria"> <!-- Seacrh criteria starts -->
									<div class="block" >
										<ul class="blocklist">
										  <c:forEach var="coveredArea"  items="${entityDetail.coveredAreaDetailList}">
											<li>
												<spring:message htmlEscape="true" code="Label.land.region.code" text="Land region code"/>
												<label class="lblPlain">
													<c:choose>
														<c:when test="${coveredArea.langRegionLinkCode ne null && coveredArea.langRegionLinkCode ne ''}">
															 <c:out value="${coveredArea.langRegionLinkCode}" escapeXml="true"></c:out> 
														</c:when>
														<c:otherwise>
															<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
														</c:otherwise>
													</c:choose>
												 </label>
											</li>
											<li>
												<spring:message htmlEscape="true" code="Label.census.code" text="Census 2011 code"/>
												<label class="lblPlain">
													<c:choose>
														<c:when test="${coveredArea.cencusCode ne null && coveredArea.cencusCode ne ''}">
															 <c:out value="${coveredArea.cencusCode }" escapeXml="true"></c:out> 
														</c:when>
														<c:otherwise>
															<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
														</c:otherwise>
													</c:choose>
												 </label>
											</li>
											<li>
												<spring:message htmlEscape="true" code="Label.land.region.name.en" text="Land region name english"/>
												<label class="lblPlain">
													<c:choose>
														<c:when test="${coveredArea.landRegionNameEn ne null && coveredArea.landRegionNameEn ne ''}">
															 <c:out value="${coveredArea.landRegionNameEn }" escapeXml="true"></c:out> 
														</c:when>
														<c:otherwise>
															<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
														</c:otherwise>
													</c:choose>
												 </label>
											</li>
											<li>
												<spring:message htmlEscape="true" code="Label.land.region.name.loc" text="Land region name local"/>
												<label class="lblPlain">
													<c:choose>
														<c:when test="${coveredArea.langRegionNameLoc ne null && coveredArea.langRegionNameLoc ne ''}">
															 <c:out value="${coveredArea.langRegionNameLoc }" escapeXml="true"></c:out> 
														</c:when>
														<c:otherwise>
															<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
														</c:otherwise>
													</c:choose>
												 </label>
											</li>
											<li>
												<spring:message htmlEscape="true" code="Label.land.region.type" text="Land region type"/>
												<label class="lblPlain">
													<c:choose>
														<c:when test="${coveredArea.landRegionTypeName ne null && coveredArea.landRegionTypeName ne ''}">
															 <c:out value="${coveredArea.landRegionTypeName }" escapeXml="true"></c:out> 
														</c:when>
														<c:otherwise>
															<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
														</c:otherwise>
													</c:choose>
												 </label>
											</li>
										</c:forEach>							
										</ul>
									</div>
									
									<div class="clear"></div>
									
								</div>
										
							</div>
							
							<div class="frmtxt" style="width:40%;height:30%;display:inline-block;">
								<div class="frmhdtitle"><spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"/></div>
								<div class="search_criteria"> <!-- Seacrh criteria starts -->
									<div class="block" >
										<ul class="blocklist">
										  <c:choose>
										  <c:when test="${orderCode ne null and orderCode ne 0}">
											  <li>
													<spring:message	htmlEscape="true" code="Label.ORDERNO"/>
													<label class="lblPlain">
														<c:choose>
															<c:when test="${entityDetail.orderNocr ne null && entityDetail.orderNocr ne ''}">
																 <c:out value="${entityDetail.orderNocr}" escapeXml="true"></c:out>
															</c:when>
															<c:otherwise>
																<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
															</c:otherwise>
														</c:choose>
													 </label>
												</li>
												<li>
													<spring:message	htmlEscape="true" code="Label.ORDERDATE"/>
													<label class="lblPlain">
														<c:choose>
															<c:when test="${entityDetail.orderDatecr ne null && entityDetail.orderDatecr ne ''}">
																 <c:out value="${entityDetail.orderDatecr}" escapeXml="true"></c:out>
															</c:when>
															<c:otherwise>
																<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
															</c:otherwise>
														</c:choose>
													 </label>
												</li>
												<li>
													<spring:message	htmlEscape="true" code="Label.EFFECTIVEDATE"/>
													<label class="lblPlain">
														<c:choose>
															<c:when test="${entityDetail.ordereffectiveDatecr ne null && entityDetail.ordereffectiveDatecr ne ''}">
																 <c:out value="${entityDetail.ordereffectiveDatecr}" escapeXml="true"></c:out>
															</c:when>
															<c:otherwise>
																<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
															</c:otherwise>
														</c:choose>
													 </label>
												</li>
												<li>
													<spring:message	htmlEscape="true" code="Label.GAZPUBDATE"/>
													<label class="lblPlain">
														<c:choose>
															<c:when test="${entityDetail.gazPubDatecr ne null && entityDetail.gazPubDatecr ne ''}">
																 <c:out value="${entityDetail.gazPubDatecr}" escapeXml="true"></c:out>
															</c:when>
															<c:otherwise>
																<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
															</c:otherwise>
														</c:choose>
													 </label>
												</li>
												<li>
													<c:choose>
													<c:when test="${!empty attachmentList }">
														<table>
														<tr class="tblRowTitle tblclear">
															<td width="5%"><spring:message	code="Label.FILENAME"/></td>
															<td width="16%"><spring:message	code="Label.FILESIZE"/></td>
															<td width="10%"><spring:message code="addAttachment.filecontenttype"/></td>
															<td width="10%" align="center"><spring:message code="Label.VIEW.GOVERNMENTORDER"/></td>
														</tr>
														<c:forEach var="govOrderDetail" items="${attachmentList}">
														<tr>
														<td><c:out value="${govOrderDetail.fileName}" escapeXml="true"></c:out></td>
														<td><c:out value="${govOrderDetail.fileSize}" escapeXml="true"></c:out></td>
														<td><c:out value="${govOrderDetail.fileContentType}" escapeXml="true"></c:out></td>
														<td width="10%" align="center"> 
														<a href="#">
															<img src="images/view.png" name="viewAttachment" width="22" height="18" border="0" onclick="javascript:openFileByPath('${govOrderDetail.fileLocation}');" />		
														</a>
														</td>
														</tr>
														</c:forEach>
														</table>
													</c:when>
													<c:otherwise>
															<div class="errormsg"><spring:message code="Error.NOATTACHMENTDETAIL" htmlEscape="true"/></div>
													</c:otherwise>
													</c:choose>
													
												
												</li>
										  </c:when>
										  <c:otherwise>
										  			<div class="errormsg"><spring:message code="error.NOGOVTORDERDETAILSFOUND" htmlEscape="true"/></div>
										  </c:otherwise>
										  </c:choose>
											
														
										</ul>
									</div>
									
									<div class="clear"></div>
									
								</div>
										
							</div>
							
							<div class="frmtxt" style="width:40%;height:30%;display:inline-block;">
								<div class="frmhdtitle"><spring:message code="Label.VIEW.MAP" htmlEscape="true"/></div>
								<div class="search_criteria"> <!-- Seacrh criteria starts -->
									<div class="block" >
										<c:choose>
											<c:when test="${!empty mapAttachmentList }">
												<table>
													<tr class="tblRowTitle tblclear">
														<td width="5%"><spring:message code="Label.FILENAME"/></td>
														<td width="10%"><spring:message code="Label.FILESIZE"/></td>
														<td width="10%"><spring:message	code="addAttachment.filecontenttype"/></td>
														<td width="10%" align="center"><spring:message code="Label.VIEW.GOVERNMENTORDER"/></td>
													</tr>
													<c:forEach var="mapDetails" items="${mapAttachmentList}">
													<tr>
														<td><c:out value="${mapDetails.fileName}" escapeXml="true"></c:out> </td>
														<td align="left"><c:out value="${listMapDetails.fileSize}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listMapDetails.fileContentType}" escapeXml="true"></c:out></td>
													
													<td width="10%" align="center">
														<a href="#">
															<img src="images/view.png" name="viewMapAttachment"	width="22" height="18" border="0" onclick="javascript:retrieveMapFile('${listMapDetails.fileTimestamp()}');" />
														</a>
													</td>
													</tr>
													</c:forEach>
												</table>
											</c:when>
										    <c:otherwise>
										    	<div class="errormsg"><spring:message code="Error.NOMAPRECORD" htmlEscape="true"/></div>
										    </c:otherwise>
										</c:choose>
										
									</div>
								</div>
							</div>  
							
							
							<div class="frmtxt" style="width:80%;display:inline-block;">
								<div class="frmhdtitle"><spring:message code="Label.LOCALBODY.HIERCHICAL.DETAIL" text="Hierchical Detail" htmlEscape="true"/></div>
								<div class="search_criteria"> <!-- Seacrh criteria starts -->
									<div class="block" >
										<ul class="blocklist">
										 
											  <li>
													<c:out value="${lbHierchicalDetail}" escapeXml="true"></c:out>
												</li>
												
										  
											
														
										</ul>
									</div>
									
									<div class="clear"></div>
									
								</div>
										
							</div>
							
							
							<%-- <div class="frmtxt" style="width:80%;">
								<div class="frmhdtitle"><spring:message code="Label.LOCALBODY.HIERCHICAL.DETAIL" text="Hierchical Detail" htmlEscape="true"/></div>
								<div class="search_criteria"> <!-- Seacrh criteria starts -->
									<div class="block" >
										<ul class="blocklist">
											<li>
											     <c:out value="${lbHierchicalDetail}" />
											</li>
										</ul>
										</div>
										</div>
								</div> --%>
										
					
					   </c:forEach>	
				</c:when>
				<c:otherwise>
				 <div class="errormsg"><spring:message code="Error.LOCALBODY.NOT.FOUND" text="No Localbody Details Found" htmlEscape="true"/></div>
				</c:otherwise>
			</c:choose>
			</div>
				
				
				
				
				
				
				
				
				
				
				
				
				</div>
				
			</form>
		</div>
   </div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<%-- <div id="frmcontent">
		<table width="100%" class="tbl_no_brdr">
			<tr>
				<td width="100%" valign="top" class="tblclear">
					<div class="frmhd">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td><spring:message htmlEscape="true" code="Label.VIEWLOCALBODY"></spring:message>
								</td>
								<td align="right"><a href="#" class="frmhelp"><spring:message
											htmlEscape="true" code="Label.HELP"></spring:message> </a></td>
							</tr>
						</table>
					</div>
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
									<table width="100%" class="tbl_no_brdr">
										<c:forEach var="entityDetail" varStatus="entityDetailRow" items="${localGovtBodyDataFormList}">
											<tr>
												<td rowspan="2" width="60%">
													<div class="frmpnlbg">
														<div class="frmtxt">
															<div class="frmhdtitle">
																<spring:message code="Label.GENERALDETAILS"
																	htmlEscape="true"></spring:message>
															</div>
															<table width="100%" class="tbl_no_brdr">
																<tr>
																	<td width="14%" rowspan="54">&nbsp;</td>
																	<td class="lblBold"><spring:message
																			htmlEscape="true" code="Label.LOCALBODYCODE"></spring:message>
																		<label class="lblPlain"> <c:out value="${status.value}" /> </label>
																		<div class="errormsg"></div></td>
																</tr>

																<tr>
																	<td class="lblBold"><spring:message
																			htmlEscape="true" code="Label.LOCALBODYVERSION"></spring:message>
																		<label class="lblPlain"> <spring:bind
																				path="localbodybean.listLocalBodyDetails[${listLocalBodyDetailsRow.index}].localBodyVersion">&nbsp;
																				               <c:out value="${status.value}" />
																			</spring:bind> </label>
																		<div class="errormsg"></div></td>
																</tr>

																<tr>
																	<td class="lblBold"><spring:message
																			htmlEscape="true" code="Label.LOCALBODYNAMEENGLISH"></spring:message>
																		<label class="lblPlain"> <spring:bind
																				path="localbodybean.listLocalBodyDetails[${listLocalBodyDetailsRow.index}].localBodyNameEnglish">&nbsp;
																				               <c:out value="${status.value}" />
																			</spring:bind> </label>
																		<div class="errormsg"></div></td>
																</tr>

																<tr>
																	<td class="lblBold"><spring:message
																			htmlEscape="true" code="Label.LOCALBODYNAMELOCAL"></spring:message><br />
																		<label class="lblPlain"> <spring:bind
																				path="localbodybean.listLocalBodyDetails[${listLocalBodyDetailsRow.index}].localBodyNameLocal">&nbsp;
																				               <c:out value="${status.value}" />
																			</spring:bind> </label>
																		<div class="errormsg"></div></td>
																</tr>

																<tr>
																	<td class="lblBold"><spring:message
																			htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message>
																		<label class="lblPlain"> <spring:bind
																				path="localbodybean.listLocalBodyDetails[${listLocalBodyDetailsRow.index}].aliasEnglish">
																				<c:out value="${status.value}" />
																			</spring:bind> </label>
																		<div class="errormsg"></div></td>
																</tr>

																<tr>
																	<td class="lblBold"><spring:message
																			htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message>
																		<label class="lblPlain"> <spring:bind
																				path="localbodybean.listLocalBodyDetails[${listLocalBodyDetailsRow.index}].aliasLocal">&nbsp;
																				               <c:out value="${status.value}" />
																			</spring:bind> </label>
																		<div class="errormsg"></div></td>
																</tr>

																<tr>
																	<td class="lblBold"><spring:message
																			htmlEscape="true" code="Label.HEADQUARTER"></spring:message>
																		<label label class="lblPlain"><spring:bind
																				path="localbodybean.listLocalBodyDetails[${listLocalBodyDetailsRow.index}].headquarterName">&nbsp;							
								                                                             <c:out
																					value="${status.value}" />
																			</spring:bind> </label>
																		<div class="errormsg"></div></td>
																</tr>

																															<tr>
																<td class="lblBold"><spring:message htmlEscape="true"  code="Label.MAPLANDREGIONCODE"></spring:message><br />
																	      <label> <spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].mapCode">&nbsp;
																				               <c:out value="${status.value}" />
																		          </spring:bind> 
																		  </label>
																	<div class="errormsg"></div></td>
															</tr>

																<tr>
																	<td class="lblBold"><spring:message
																			htmlEscape="true" code="Label.STATESPECIFICCODE"></spring:message>
																		<label class="lblPlain"> <spring:bind
																				path="localbodybean.listLocalBodyDetails[${listLocalBodyDetailsRow.index}].sscode">&nbsp;
																				                <c:out value="${status.value}" />
																			</spring:bind> </label>
																		<div class="errormsg"></div></td>
																</tr>

																
															</table>
														</div>
													</div>
													
													<!-- added by sushil on 15-03-2013 -->
													<div class="frmpnlbg">
															<div class="frmtxt">
																<div class="frmhdtitle"><spring:message 	htmlEscape="true" code="Label.lang.region.area.detail" text="Covered Area Detail"></spring:message></div>
																<table class="tbl_no_brdr" width="100%">
																		<c:if test="${listLocalBodyDetails != null}">
																			<c:forEach var="coveredArea" items="${listLocalBodyDetails.coveredAreaDetailList}">
																				<tr>
																					<td class="lblBold"><spring:message htmlEscape="true" code="Label.land.region.code" text="Land region code"></spring:message></td>
																					<td><c:out value="${coveredArea.langRegionLinkCode}"/></td>
																				</tr>
																				<tr>
																					<td class="lblBold"><spring:message htmlEscape="true" code="Label.census.code" text="Census 2011 code"></spring:message></td>
																					<td><c:out value="${coveredArea.cencusCode}"/></td>
																				</tr>
																				<tr>
																					<td class="lblBold"><spring:message htmlEscape="true" code="Label.land.region.name.en" text="Land region name english"></spring:message></td>
																					<td><c:out value="${coveredArea.landRegionNameEn}"/></td>
																				</tr>
																				<tr>
																					<td class="lblBold"><spring:message htmlEscape="true" code="Label.land.region.name.loc" text="Land region name local"></spring:message></td>
																					<td><c:out value="${coveredArea.langRegionNameLoc}"/></td>
																				</tr>
																				<tr>
																					<td class="lblBold"><spring:message htmlEscape="true" code="Label.land.region.type" text="Land region type"></spring:message></td>
																					<td><c:out value="${coveredArea.landRegionTypeName}"/></td>
																				</tr>
																				<tr>
																					<td colspan="2">
																						<hr/>
																					</td>
																				</tr>																			
																			</c:forEach>
																		</c:if>
															</table>
															</div>
													</div>
													<!-- end -->
													
												</td>
											
												<td>
													<div class="frmpnlbg">
														<div class="frmtxt">
															<div class="frmhdtitle">
																<spring:message code="Label.GOVTORDERDETAILS"
																	htmlEscape="true"></spring:message>
															</div>
															<br />
															<c:if test="${attachmentList.size() > 0 }">
																<table   class="tbl_with_brdr"
																	width="50%" >
																	<tr>
																	<td class="lblBold"><spring:message
																			htmlEscape="true" code="Label.ORDERNO"></spring:message><br />
																		<label class="lblPlain"><spring:bind
																				path="localbodybean.listLocalBodyDetails[${listLocalBodyDetailsRow.index}].orderNocr">&nbsp;
																							<c:out value="${status.value}" />
																			</spring:bind> </label> </label>
																		<div class="errormsg"></div></td>
																</tr>

																<tr>
																	<td class="lblBold"><spring:message
																			htmlEscape="true" code="Label.ORDERDATE"></spring:message><br />
																		<label class="lblPlain"><spring:bind
																				path="localbodybean.listLocalBodyDetails[${listLocalBodyDetailsRow.index}].orderDatecr">&nbsp;
																							<c:out value="${status.value}" />
																			</spring:bind> </label> </label>
																		<div class="errormsg"></div></td>
																</tr>

																<tr>
																	<td class="lblBold"><spring:message
																			htmlEscape="true" code="Label.EFFECTIVEDATE"></spring:message><br />
																		<label class="lblPlain"><spring:bind
																				path="localbodybean.listLocalBodyDetails[${listLocalBodyDetailsRow.index}].ordereffectiveDatecr">&nbsp;
																							<c:out value="${status.value}" />
																			</spring:bind> </label> </label>
																		<div class="errormsg"></div></td>
																</tr>

																<tr>
																	<td class="lblBold"><spring:message
																			htmlEscape="true" code="Label.GAZPUBDATE"></spring:message><br />
																		<label class="lblPlain"><spring:bind
																				path="localbodybean.listLocalBodyDetails[${listLocalBodyDetailsRow.index}].gazPubDatecr">&nbsp;
																							<c:out value="${status.value}" />
																			</spring:bind> </label> </label>
																</tr>
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
																		<td><%=abc.getFileContentType()%> </td>
																		<td width="10%" align="center"> <a href="#">
																		<img
																				src="images/view.png" name="viewAttachment"
																				width="22" height="18" border="0"
																				onclick="javascript:openFileByPath('${filenameattch}');"
																						
																				
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
															<form:errors path="noAttachmentRecord"
																cssClass="errormsg" />
															<form:errors path="noOrderRecord" cssClass="errormsg" />
														</div>
													</div>
												</td>
											</tr>
											<tr>
												<td>
													<div class="frmpnlbg">
														<div class="frmtxt">
															<div class="frmhdtitle">
																<spring:message code="Label.VIEW.MAP" htmlEscape="true"></spring:message>
															</div>
															<c:if test="${mapAttachmentList.size() > 0 }">
																<table   class="tbl_with_brdr"
																width="50%" align="center">

																<tr class="tblRowTitle tblclear">
																	<td width="5%"><spring:message code="Label.FILENAME"></spring:message>
																	</td>
																	<td width="10%"><spring:message
																			code="Label.FILESIZE"></spring:message></td>
																	<td width="10%"><spring:message
																			code="addAttachment.filecontenttype"></spring:message></td>
																	<td width="10%" align="center"><spring:message
																			code="Label.VIEW.GOVERNMENTORDER"></spring:message></td>
																	<td width="10%" align="center"><spring:message
																			code="Label.VIEW.GOVERNMENTORDER"></spring:message></td>
																</tr>
																<!-- Getting Values in the form of the Table -->
																<c:forEach var="listMapDetails" varStatus="listMapRow"
																	items="${mapAttachmentList}">
																	<tr class="tblRowB">
																		<td>${listMapDetails.getFileName()}</td>
																		<td align="left">${listMapDetails.getFileSize()}</td>
																		<td align="left">${listMapDetails.getFileContentType()}</td>
																		<td align="left">${listMapDetails.getImagePath()}</td>
																		<td width="10%" align="center"><a href="#"><img
																				src="images/view.png" name="viewMapAttachment"
																				width="22" height="18" border="0"
																				onclick="javascript:retrieveMapFile('${listMapDetails.getFileTimestamp()}');" />
																		</a></td>
																	</tr>
																</c:forEach>
															</table>
															</c:if>
															<c:if test="${empty mapAttachmentList}">
																	<!-- <div class="frmpnlbg" id="divData"> -->
																		<!-- <div class="frmtxt"> -->
																			<table width="100%" class="tbl_no_brdr">
																				<tr>
																					<td colspan="4" align="center"><spring:message htmlEscape="true" code="error.NOMAPFOUND"></spring:message>
																					</td>
																				</tr>
																			</table>
																		<!-- </div> -->
																	<!-- </div> -->
																</c:if>
															<form:errors path="noMapRecord" cssClass="errormsg" />
														</div>
													</div>
												</td>
											</tr>
										</c:forEach>
									</table>
								</div>

								<div class="btnpnl">
									<table width="100%" class="tbl_no_brdr">
										<tr>
											<td>
												<!-- BACK button removed - client requirement --> <label>
													<input type="button" name="Submit3" class="btn"
													value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
													onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"
													id="btnClose" /> </label>
											</td>
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

	</div> --%>

</body>
</html>