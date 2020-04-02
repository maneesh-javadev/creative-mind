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
<c:set var="DISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.DISTRICT_CONSTANT.toString()%>"></c:set>
<c:set var="SUBDISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.SUB_DISTRICT_CONSTANT.toString()%>"></c:set>
<c:set var="VILLAGE_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.VILLAGE_CONSTANT.toString()%>"></c:set>
<c:set var="isDISTRICTCoveraheType" value="false"></c:set>
<c:set var="isINTERMEDIATECoveraheType" value="false"></c:set>
<c:set var="isVILLAGECoveraheType" value="false"></c:set>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrStateService.js'></script>
<%@page import="in.nic.pes.lgd.utils.ApplicationConstant"%>
	<%
		String requiredTitleValue = "no";
	%>
	<%
		List<Attachment> listOFMetaData = (List<Attachment>) request
				.getAttribute("attachmentList");
	%>
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


<section class="content">
  <div class="row">
          <!-- main col -->
     <section class="col-lg-12">

        <div class="box">
		      <%-- <div class="box-header with-border">
		        <h3 class="box-title"><spring:message code="Label.VIEWLOCALBODY" htmlEscape="true"></spring:message></h3>
		      </div><!-- /.box-header --> --%>


			<form action="viewLocalBodyAction.htm"  id="frmViewLocalBody" 	name="frmViewLocalBody" method="post"  class="form-horizontal">
			<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="viewLocalBodyAction.htm"/>" />
			<input type="hidden" name="govfilePath" id="govfilePath" />
			<input type="hidden" name="fileDisplayType"/>
			<input type="hidden" id="fileNameToDownload" name="fileNameToDownload" />
			<input type="hidden" id="orderCode" name="orderCode" />
			
			<%-- <div class="box-header subheading">
                  		<h4 class="box-title"><spring:message code="Label.LOCALBODYDETAIL" htmlEscape="true"></spring:message></h4>
            </div><!-- /.box-header -->		
			
			
			<c:choose>
			<c:when test="${!empty localGovtBodyDataFormList}">
				 <c:forEach var="entityDetail" varStatus="entityDetailRow" items="${localGovtBodyDataFormList}">
				
			
			
			<div class="form-group">
					  
					  <div class="col-sm-12">
					  
					  <label class="well-inline section_info">
					  	
            			
            			<div class="well_section">
            			
            			<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message code="Label.GENERALDETAILS" htmlEscape="true"></spring:message></h4>
            			</div>
            			
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.LOCALBODYCODE' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							<c:choose>
								<c:when test="${entityDetail.localBodyCode ne null && entityDetail.localBodyCode ne ''}">
									 <c:out value="${entityDetail.localBodyCode}" escapeXml="true"></c:out> 
								</c:when>
								<c:otherwise>
									<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
								</c:otherwise>
							</c:choose>
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.LOCALBODYVERSION' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							 <c:choose>
								<c:when test="${entityDetail.localBodyVersion ne null && entityDetail.localBodyVersion ne ''}">
									 <c:out value="${entityDetail.localBodyVersion}" escapeXml="true"></c:out>
								</c:when>
								<c:otherwise>
									<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
								</c:otherwise>
							</c:choose>
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.LOCALBODYNAMEENGLISH' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							 <c:choose>
								<c:when test="${entityDetail.localBodyNameEnglish ne null && entityDetail.localBodyNameEnglish ne ''}">
									 <c:out value="${entityDetail.localBodyNameEnglish}" escapeXml="true"></c:out> 
								</c:when>
								<c:otherwise>
									<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
								</c:otherwise>
							</c:choose>
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.LOCALBODYNAMELOCAL' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							 <c:choose>
								<c:when test="${entityDetail.localBodyNameLocal ne null && entityDetail.localBodyNameLocal ne ''}">
									 <c:out value="${entityDetail.localBodyNameLocal}" escapeXml="true"></c:out> 
								</c:when>
								<c:otherwise>
									<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
								</c:otherwise>
							</c:choose>
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.LOCALBODYALIASENGLISH' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							<c:choose>
								<c:when test="${entityDetail.aliasEnglish ne null && entityDetail.aliasEnglish ne ''}">
									 <c:out value="${entityDetail.aliasEnglish}" escapeXml="true"></c:out> 
								</c:when>
								<c:otherwise>
									<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
								</c:otherwise>
							</c:choose>
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.LOCALBODYALIASLOCAL' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							<c:choose>
								<c:when test="${entityDetail.aliasLocal ne null && entityDetail.aliasLocal ne ''}">
									 <c:out value="${entityDetail.aliasLocal}" escapeXml="true"></c:out>
								</c:when>
								<c:otherwise>
									<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
								</c:otherwise>
							</c:choose>
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.HEADQUARTER' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							<c:choose>
								<c:when test="${entityDetail.headquarterName ne null && entityDetail.headquarterName ne ''}">
									 <c:out value="${entityDetail.headquarterName}" escapeXml="true"></c:out> 
								</c:when>
								<c:otherwise>
									<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
								</c:otherwise>
							</c:choose> 
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.MAPLANDREGIONCODE' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							<c:choose>
								<c:when test="${entityDetail.mapCode ne null && entityDetail.mapCode ne ''}">
									 <c:out value="${entityDetail.mapCode}" escapeXml="true"></c:out>  
								</c:when>
								<c:otherwise>
									<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
								</c:otherwise>
							</c:choose>
					  	  </div>
						</div>
						
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.STATESPECIFICCODE' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							 <c:choose>
									<c:when test="${entityDetail.sscode ne null && entityDetail.sscode ne ''}">
										 <c:out value="${entityDetail.sscode}" escapeXml="true"></c:out>
									</c:when>
									<c:otherwise>
										<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
									</c:otherwise>
								</c:choose>
					  	  </div>
						</div>
					  
					  </div>
					 
						<div class="well_section col-sm-12" >
            			
            			<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message	htmlEscape="true" code="Label.lang.region.area.detail" text="Covered Area Detail"/></h4>
            			</div>
            			
            			<c:forEach items="${completedCoverageDetails}" var="completedCoveragesDistrict" >
            			
            			<c:choose>
            			<c:when test="${completedCoveragesDistrict.landRegionType eq  DISTRICT_CONSTANT}">
            				<c:set var="isDISTRICTCoveraheType" value="true"></c:set>
            			</c:when>
            			<c:when test="${completedCoveragesDistrict.landRegionType eq  SUBDISTRICT_CONSTANT}">
            					<c:set var="isINTERMEDIATECoveraheType" value="true"></c:set>
            			</c:when>
            			<c:when test="${completedCoveragesDistrict.landRegionType eq  VILLAGE_CONSTANT}">
            				<c:set var="isVILLAGECoveraheType" value="true"></c:set>
            			</c:when>
            			</c:choose>
						</c:forEach>
            			
						<c:if test="${isDISTRICTCoveraheType}">	
										<li>
											<table id="tblCoverage_${DISTRICT_PANCHAYAT_LEVEL}" class="data_grid" width="50%">
												<thead>
													<tr>
														<td colspan="3"><strong>Current Covered District</strong></td>
													</tr>
													<tr>
														<th width="30%">District Code</th>
														<th width="60%">District Name</th>
														<th>Coverage type</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${completedCoverageDetails}" var="completedCoveragesDistrict" >
														<c:if test="${completedCoveragesDistrict.landRegionType eq  DISTRICT_CONSTANT}">
															<tr id="tr_${completedCoveragesDistrict.landRegionCode}">
																<td><c:out value="${completedCoveragesDistrict.landRegionCode}"></c:out></td>
																<td><c:out value="${completedCoveragesDistrict.landRegionNameEnglish}"></c:out></td>
																<td>
																	<c:choose>
																		<c:when test="${completedCoveragesDistrict.coverageType eq 'F'}">
																			<c:out value="FULL"/>
																		</c:when>
																		<c:otherwise>
																			<c:out value="PART"/>
																		</c:otherwise>
																	</c:choose>
																	
																</td>
															</tr>
														</c:if>
													</c:forEach>
												</tbody>
											</table>
										</li>
									</c:if>
									
									<c:if test="${isINTERMEDIATECoveraheType}">	
										<li>
											<table id="tblCoverage_${INTERMEDIATE_PANCHAYAT_LEVEL}" class="data_grid" width="50%">
												<thead>
													<tr>
														<td colspan="3"><strong>Current Covered Sub-district</strong></td>
													</tr>
													<tr>
														<th width="30%">Sub-district Code</th>
														<th width="60%">Sub-district Name</th>
														<th>Coverage type</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${completedCoverageDetails}" var="completedCoveragesIM" >
														<c:if test="${completedCoveragesIM.landRegionType eq  SUBDISTRICT_CONSTANT}">
														<tr id="tr_${completedCoveragesIM.landRegionCode}">
															<td><c:out value="${completedCoveragesIM.landRegionCode}"></c:out></td>
															<td><c:out value="${completedCoveragesIM.landRegionNameEnglish}"></c:out></td>
															<td>
																	<c:choose>
																		<c:when test="${completedCoveragesIM.coverageType eq 'F'}">
																			<c:out value="FULL"/>
																		</c:when>
																		<c:otherwise>
																			<c:out value="PART"/>
																		</c:otherwise>
																	</c:choose>
																	
																</td>
														</tr>
														</c:if>
													</c:forEach>
												</tbody>
											</table>
										</li>
									</c:if>
									
									<c:if test="${isVILLAGECoveraheType}">		
										<li>
											<table id="tblCoverage_${VILLAGE_PANCHAYAT_LEVEL}" class="data_grid" width="50%">
												<thead>
													<tr>
														<td colspan="3"><strong>Current Covered Village</strong></td>
													</tr>
													<tr>
														<th width="30%">Village Code</th>
														<th width="60%">Village Name</th>
														<th>Coverage type</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${completedCoverageDetails}" var="completedCoveragesV" >
														<c:if test="${completedCoveragesV.landRegionType eq  VILLAGE_CONSTANT}">
														<tr id="tr_${completedCoveragesV.landRegionCode}">
															<td><c:out value="${completedCoveragesV.landRegionCode}"></c:out></td>
															<td><c:out value="${completedCoveragesV.landRegionNameEnglish}"></c:out></td>
															<td>
																	<c:choose>
																		<c:when test="${completedCoveragesV.coverageType eq 'F'}">
																			<c:out value="FULL"/>
																		</c:when>
																		<c:otherwise>
																			<c:out value="PART"/>
																		</c:otherwise>
																	</c:choose>
																	
																</td>
														</tr>
														</c:if>
													</c:forEach>
												</tbody>
											</table>
										</li>
									</c:if>
						
						
				
						
						
						</div>
						
						</label>
					  
					  
					  <label class="well-inline section_info">
					  
					
					  
					  <div class="well_section">
            			
            			<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message></h4>
            			</div>
            			
						<div class="form-group">
						  <label  class="col-sm-5 control-label" for="sel1"><spring:message code='Label.ORDERNO' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							 <c:choose>
								<c:when test="${entityDetail.orderNocr ne null && entityDetail.orderNocr ne ''}">
									 <c:out value="${entityDetail.orderNocr}" escapeXml="true"></c:out> 
								</c:when>
								<c:otherwise>
									<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
								</c:otherwise>
							</c:choose>
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-5 control-label" for="sel1"><spring:message code='Label.ORDERDATE' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							<c:choose>
								<c:when test="${entityDetail.orderDatecr ne null && entityDetail.orderDatecr ne ''}">
										 <c:out value="${entityDetail.orderDatecr}" escapeXml="true"></c:out> 
								</c:when>
								<c:otherwise>
									<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
								</c:otherwise>
							</c:choose>
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-5 control-label" for="sel1"><spring:message code='Label.EFFECTIVEDATE' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
						  <c:choose>
							<c:when test="${entityDetail.ordereffectiveDatecr ne null && entityDetail.ordereffectiveDatecr ne ''}">
									 <c:out value="${entityDetail.ordereffectiveDatecr}" escapeXml="true"></c:out> 
							</c:when>
							<c:otherwise>
								<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
							</c:otherwise>
						</c:choose>
						</div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-5 control-label" for="sel1"><spring:message code='Label.GAZPUBDATE' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							<c:choose>
								<c:when test="${entityDetail.gazPubDatecr ne null && entityDetail.gazPubDatecr ne ''}">
										 <c:out value="${entityDetail.gazPubDatecr}" escapeXml="true"></c:out> 
								</c:when>
								<c:otherwise>
									<spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/>
								</c:otherwise>
							</c:choose>
					  	  </div>
						</div>
						
						<div class="form-group">
							<c:if test="${attachmentList.size() > 0 }">
								<table class="data_grid" width="100%" >
									<tr class="tblRowTitle tblclear">
										<td width="5%"><spring:message code="Label.FILENAME"></spring:message></td>
										<td width="16%"><spring:message code="Label.FILESIZE"></spring:message></td>
										<td width="10%"><spring:message code="addAttachment.filecontenttype"></spring:message></td>
										<td width="10%" align="center"><spring:message code="Label.VIEW.GOVERNMENTORDER"></spring:message></td>
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
													onclick="javascript:retrieveFile('<c:out value="<%=abc.getFileTimestamp()%>"/>');" />
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
								<div class="col-sm-6">
								 <form:errors path="noOrderRecord" class="mandatory"/>
						  	  	</div>
						  	 </div>
					  	  	</div>
					  	  <div class="well_section col-sm-12">
					  	 	<div class="box-header subheading">
		                  		<h4 class="box-title"><spring:message code="Label.VIEW.MAP" htmlEscape="true"></spring:message></h4>
		            		</div> 
		            		
		            		<div class="form-group">
								<c:if test="${attachmentList.size() > 0 }">
									<table class="data_grid" width="100%" >
										<tr>
											<td width="5%"><spring:message code="Label.FILENAME"></spring:message></td>
											<td width="10%"><spring:message code="Label.FILESIZE"></spring:message></td>
											<td width="10%"><spring:message code="addAttachment.filecontenttype"></spring:message></td>
											<td width="10%" align="center"><spring:message code="Label.VIEW.GOVERNMENTORDER"></spring:message></td>
										</tr>
										
										<c:forEach var="listMapDetails" varStatus="listMapRow" items="${mapAttachmentList}">
											<tr>
												<td><c:out value="${listMapDetails.getFileName()}" escapeXml="true"></c:out></td>
												<td align="left"><c:out value="${listMapDetails.getFileSize()}" escapeXml="true"></c:out></td>
												<td align="left"><c:out value="${listMapDetails.getFileContentType()}" escapeXml="true"></c:out></td>
												<td width="10%" align="center"><a href="#"><img src="images/view.png" name="viewMapAttachment" width="22" height="18" alt="View" border="0" 
												onclick="javascript:retrieveMapFile('${listMapDetails.getFileTimestamp()}');" /></a></td>
											</tr>
										</c:forEach>
									</table>
								</c:if>
								
								<div class="col-sm-6">
								 <form:errors path="noMapRecord" class="mandatory"/>
						  	  	</div> 
							
							</div>
							</div> 
						
						
						
						</label>
					  </div>
				</div>
			
			
			
			
			</c:forEach>
				
			</c:when>
			<c:otherwise>
				 <div class="errormsg"><spring:message code="Error.LOCALBODY.NOT.FOUND" text="No Localbody Details Found" htmlEscape="true"/></div>
				</c:otherwise>
				</c:choose>	 --%>
				
				
			<c:choose>
			<c:when test="${!empty localGovtBodyDataFormList}">
				 <c:forEach var="entityDetail" varStatus="entityDetailRow" items="${localGovtBodyDataFormList}">
				 <div class="box-header subheading">
                  <h4 class="box-title"><spring:message code="Label.GENERALDETAILS" htmlEscape="true"></spring:message></h4>
            	</div>
			  <table class="table table-bordered table-hover">
                    
				 <tbody>
				 <tr>
				 <td><spring:message code='Label.LOCALBODYCODE' htmlEscape="true"/></td>
				<c:choose>
								<c:when test="${entityDetail.localBodyCode ne null && entityDetail.localBodyCode ne ''}">
									<td><c:out value="${entityDetail.localBodyCode}" escapeXml="true"></c:out></td>
								</c:when>
								<c:otherwise>
									<td><spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/></td>
								</c:otherwise>
				</c:choose>
				 </tr>
				 
				 <tr>
				 <td><spring:message code='Label.LOCALBODYVERSION' htmlEscape="true"/></td>
				 <c:choose>
						<c:when test="${entityDetail.localBodyVersion ne null && entityDetail.localBodyVersion ne ''}">
									<td><c:out value="${entityDetail.localBodyVersion}" escapeXml="true"></c:out></td>
						</c:when>
						<c:otherwise>
							<td><spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/></td>
						</c:otherwise>
				</c:choose>
				</tr>
				
				<tr>
				 <td><spring:message htmlEscape="true"  code="label.minor.version"></spring:message></td>
				 <c:choose>
						<c:when test="${entityDetail.minorVersion ne null && entityDetail.minorVersion ne ''}">
									<td><c:out value="${entityDetail.minorVersion}" escapeXml="true"></c:out></td>
						</c:when>
						<c:otherwise>
							<td><spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/></td>
						</c:otherwise>
				</c:choose>
				</tr>
				
				
				 <tr>
				 <td><spring:message code='Label.LOCALBODYNAMEENGLISH' htmlEscape="true"/>  </td>
				 <c:choose>
						<c:when test="${entityDetail.localBodyNameLocal ne null && entityDetail.localBodyNameLocal ne ''}">
									 <td><c:out value="${entityDetail.localBodyNameLocal}" escapeXml="true"></c:out></td> 
						</c:when>
						<c:otherwise>
									<td><spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/></td>
						</c:otherwise>
				</c:choose>
				
				 </tr>
				 <tr>
				 <td><spring:message code='Label.LOCALBODYNAMELOCAL' htmlEscape="true"/></td>
				  <c:choose>
							<c:when test="${entityDetail.localBodyNameLocal ne null && entityDetail.localBodyNameLocal ne ''}">
									<td><c:out value="${entityDetail.localBodyNameLocal}" escapeXml="true"></c:out></td> 
							</c:when>
							<c:otherwise>
								<td><spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/></td>
							</c:otherwise>
							</c:choose>
				   </tr>
				 <tr>
				 <td><spring:message code='Label.LOCALBODYALIASENGLISH' htmlEscape="true"/></td>
				 <c:choose>
								<c:when test="${entityDetail.aliasEnglish ne null && entityDetail.aliasEnglish ne ''}">
									<td><c:out value="${entityDetail.aliasEnglish}" escapeXml="true"></c:out></td> 
								</c:when>
								<c:otherwise>
									<td><spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/></td>
								</c:otherwise>
					</c:choose>
				</tr>
				 <tr>
				 <td><spring:message code='Label.LOCALBODYALIASLOCAL' htmlEscape="true"/> </td>
				 <c:choose>
						<c:when test="${entityDetail.aliasLocal ne null && entityDetail.aliasLocal ne ''}">
								<td><c:out value="${entityDetail.aliasLocal}" escapeXml="true"></c:out></td>
						</c:when>
						<c:otherwise>
								<td><spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/></td>
						</c:otherwise>
				</c:choose>
				 
				 </tr>
				 
				 <tr>
				 <td><spring:message code='Label.HEADQUARTER' htmlEscape="true"/></td>
				 <c:choose>
						<c:when test="${entityDetail.headquarterName ne null && entityDetail.headquarterName ne ''}">
								<td><c:out value="${entityDetail.headquarterName}" escapeXml="true"></c:out></td> 
						</c:when>
						<c:otherwise>
								<td><spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/></td>
						</c:otherwise>
				</c:choose> 
				 </tr>
				 <tr>
				 <td><spring:message code='Label.MAPLANDREGIONCODE' htmlEscape="true"/></td>
				 <c:choose>
								<c:when test="${entityDetail.mapCode ne null && entityDetail.mapCode ne ''}">
									 <td><c:out value="${entityDetail.mapCode}" escapeXml="true"></c:out></td> 
								</c:when>
								<c:otherwise>
									<td><spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/></td>
								</c:otherwise>
				</c:choose>
				 </tr>
				 
				 <tr>
				 <td><spring:message code='Label.STATESPECIFICCODE' htmlEscape="true"/></td>
				 <c:choose>
						<c:when test="${entityDetail.sscode ne null && entityDetail.sscode ne ''}">
									<td><c:out value="${entityDetail.sscode}" escapeXml="true"></c:out></td>
						</c:when>
						<c:otherwise>
								<td><spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/></td>
						</c:otherwise>
				</c:choose>
				 </tr>
				 </tbody>
				 </table>
				 
				 
				 	<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message	htmlEscape="true" code="Label.lang.region.area.detail" text="Covered Area Detail"/></h4>
            		</div>
				 
				
				 <c:forEach items="${completedCoverageDetails}" var="completedCoveragesDistrict" >
            			
            			<c:choose>
            			<c:when test="${completedCoveragesDistrict.landRegionType eq  DISTRICT_CONSTANT}">
            				<c:set var="isDISTRICTCoveraheType" value="true"></c:set>
            			</c:when>
            			<c:when test="${completedCoveragesDistrict.landRegionType eq  SUBDISTRICT_CONSTANT}">
            					<c:set var="isINTERMEDIATECoveraheType" value="true"></c:set>
            			</c:when>
            			<c:when test="${completedCoveragesDistrict.landRegionType eq  VILLAGE_CONSTANT}">
            				<c:set var="isVILLAGECoveraheType" value="true"></c:set>
            			</c:when>
            			</c:choose>
						</c:forEach>
				      	<c:if test="${isDISTRICTCoveraheType}">	
				  <table class="table table-bordered table-hover" id="tblCoverage_${DISTRICT_PANCHAYAT_LEVEL}">
				  
				  <thead>
				  <tr>
					<td colspan="3"><strong>Current Covered District</strong></td>
				   </tr>
					<tr>
					 <th width="30%">District Code</th>
					<th width="60%">District Name</th>
					<th>Coverage type</th>
					</tr>
				  
				  </thead>
                 <tbody>
				 
				 <c:forEach items="${completedCoverageDetails}" var="completedCoveragesDistrict" >
														<c:if test="${completedCoveragesDistrict.landRegionType eq  DISTRICT_CONSTANT}">
															<tr id="tr_${completedCoveragesDistrict.landRegionCode}">
																<td><c:out value="${completedCoveragesDistrict.landRegionCode}"></c:out></td>
																<td><c:out value="${completedCoveragesDistrict.landRegionNameEnglish}"></c:out></td>
																<td>
																	<c:choose>
																		<c:when test="${completedCoveragesDistrict.coverageType eq 'F'}">
																			<c:out value="FULL"/>
																		</c:when>
																		<c:otherwise>
																			<c:out value="PART"/>
																		</c:otherwise>
																	</c:choose>
																	
																</td>
											</tr>
								</c:if>
					</c:forEach>
				 
				 </tbody>
				 </table>
				 </c:if>
				 
				 
				 <c:if test="${isINTERMEDIATECoveraheType}">	
										
											<table id="tblCoverage_${INTERMEDIATE_PANCHAYAT_LEVEL}" class="table table-bordered table-hover" >
												<thead>
													<tr>
														<td colspan="3"><strong>Current Covered Sub-district</strong></td>
													</tr>
													<tr>
														<th width="30%">Sub-district Code</th>
														<th width="60%">Sub-district Name</th>
														<th>Coverage type</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${completedCoverageDetails}" var="completedCoveragesIM" >
														<c:if test="${completedCoveragesIM.landRegionType eq  SUBDISTRICT_CONSTANT}">
														<tr id="tr_${completedCoveragesIM.landRegionCode}">
															<td><c:out value="${completedCoveragesIM.landRegionCode}"></c:out></td>
															<td><c:out value="${completedCoveragesIM.landRegionNameEnglish}"></c:out></td>
															<td>
																	<c:choose>
																		<c:when test="${completedCoveragesIM.coverageType eq 'F'}">
																			<c:out value="FULL"/>
																		</c:when>
																		<c:otherwise>
																			<c:out value="PART"/>
																		</c:otherwise>
																	</c:choose>
																	
																</td>
														</tr>
														</c:if>
													</c:forEach>
												</tbody>
											</table>
									
									</c:if>
									
									       <c:if test="${isVILLAGECoveraheType}">		
								
											<table id="tblCoverage_${VILLAGE_PANCHAYAT_LEVEL}" class="table table-bordered table-hover">
												<thead>
													<tr>
														<td colspan="3"><strong>Current Covered Village</strong></td>
													</tr>
													<tr>
														<th width="30%">Village Code</th>
														<th width="60%">Village Name</th>
														<th>Coverage type</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${completedCoverageDetails}" var="completedCoveragesV" >
														<c:if test="${completedCoveragesV.landRegionType eq  VILLAGE_CONSTANT}">
														<tr id="tr_${completedCoveragesV.landRegionCode}">
															<td><c:out value="${completedCoveragesV.landRegionCode}"></c:out></td>
															<td><c:out value="${completedCoveragesV.landRegionNameEnglish}"></c:out></td>
															<td>
																	<c:choose>
																		<c:when test="${completedCoveragesV.coverageType eq 'F'}">
																			<c:out value="FULL"/>
																		</c:when>
																		<c:otherwise>
																			<c:out value="PART"/>
																		</c:otherwise>
																	</c:choose>
																	
																</td>
														</tr>
														</c:if>
													</c:forEach>
												</tbody>
											</table>
									
									</c:if>
									
						<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message></h4>
            			</div>		
							
						<table class="table table-bordered table-hover">
						<tbody>
						<tr>
						<td><spring:message code='Label.ORDERNO' htmlEscape="true"/></td>
						<c:choose>
								<c:when test="${entityDetail.orderNocr ne null && entityDetail.orderNocr ne ''}">
									<td> <c:out value="${entityDetail.orderNocr}" escapeXml="true"></c:out></td> 
								</c:when>
								<c:otherwise>
									<td><spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/></td>
								</c:otherwise>
							</c:choose>
						</tr>
						
						<tr>
						<td><spring:message code='Label.ORDERDATE' htmlEscape="true"/></td>
						<c:choose>
								<c:when test="${entityDetail.orderDatecr ne null && entityDetail.orderDatecr ne ''}">
										 <%-- <td><c:out value="${entityDetail.orderDatecr}" escapeXml="true"></c:out></td>  --%>
								<td><fmt:formatDate var="orderDatecr" value="${entityDetail.orderDatecr}"	pattern="dd/MM/yyyy" /><c:out value="${orderDatecr}" escapeXml="true"></c:out></td>
								</c:when>
								<c:otherwise>
								<td><spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/></td>
								</c:otherwise>
							</c:choose>
				        </tr>
						
						<tr>
						<td><spring:message code='Label.EFFECTIVEDATE' htmlEscape="true"/></td>
						<c:choose>
							<c:when test="${entityDetail.ordereffectiveDatecr ne null && entityDetail.ordereffectiveDatecr ne ''}">
							   <%--  <td> <c:out value="${entityDetail.ordereffectiveDatecr}" escapeXml="true"></c:out></td>  --%>
							   <td><fmt:formatDate var="ordereffectiveDatecr" value="${entityDetail.ordereffectiveDatecr}"	pattern="dd/MM/yyyy" /><c:out value="${ordereffectiveDatecr}" escapeXml="true"></c:out></td>
							    
							</c:when>
							<c:otherwise>
								<td><spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/></td>
							</c:otherwise>
						</c:choose>
					   </tr>
						
						
						<tr>
						<td><spring:message code='Label.GAZPUBDATE' htmlEscape="true"/></td>
						<c:choose>
								<c:when test="${entityDetail.gazPubDatecr ne null && entityDetail.gazPubDatecr ne ''}">
										<%-- <td><c:out value="${entityDetail.gazPubDatecr}" escapeXml="true"></c:out></td>  --%>
							   <td><fmt:formatDate var="gazPubDatecr" value="${entityDetail.gazPubDatecr}"	pattern="dd/MM/yyyy" /><c:out value="${gazPubDatecr}" escapeXml="true"></c:out></td>			
										
										
										
								</c:when>
								<c:otherwise>
									<td><spring:message	htmlEscape="true" code="Label.NOT.AVAILABLE" text="Not Avilable"/></td>
								</c:otherwise>
					   </c:choose>
						</tr>
						
						</tbody>
						</table>		
							<div class="form-group">
							<c:if test="${attachmentList.size() > 0 }">
								<table class="table table-bordered table-hover" >
									<tr class="tblRowTitle tblclear">
										<td width="5%"><spring:message code="Label.FILENAME"></spring:message></td>
										<td width="16%"><spring:message code="Label.FILESIZE"></spring:message></td>
										<td width="10%"><spring:message code="addAttachment.filecontenttype"></spring:message></td>
										<td width="10%" align="center"><spring:message code="Label.VIEW.GOVERNMENTORDER"></spring:message></td>
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
													onclick="javascript:retrieveFile('<c:out value="<%=abc.getFileTimestamp()%>"/>');" />
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
								<div class="form-group" align="center">
								 <form:errors path="noOrderRecord" class="mandatory"/>
						  	  	</div>		
									
				</c:forEach>
				
			</c:when>
			<c:otherwise>
				 <div class="errormsg"><spring:message code="Error.LOCALBODY.NOT.FOUND" text="No Localbody Details Found" htmlEscape="true"/></div>
				</c:otherwise>
				</c:choose>
				
				
				
				
			</form>
			</div>
			</section>
			</div>
			</section>
	
				
				
				
				
				
				
				
				
				
				
				
</body>
</html>