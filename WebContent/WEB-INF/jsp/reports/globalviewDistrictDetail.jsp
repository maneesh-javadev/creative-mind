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
<%
		String requiredTitleValue = "no";
	%>
	<%
		List<Attachment> listOFMetaData = (List<Attachment>) request
				.getAttribute("attachmentList");
	%>
<%@ page import="in.nic.pes.lgd.bean.Attachment"%>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
 
</script>
<script language="javascript" type="text/javascript">

/* Retrieve the Order Details */
function retrieveFile(fileNameToDownload){
	
        document.getElementById("fileNameToDownload").value=fileNameToDownload;
        document.frmViewDistrict.method="post";
        document.frmViewDistrict.action="reportFileDownloads.do?<csrf:token
		uri='reportFileDownloads.do'/>";
		document.frmViewDistrict.submit();
        return true;
}
/* Retrieve the Map Details */

function retrieveMapFile(fileNameToDownload){
	
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

<section class="content">
  <div class="row">
          <!-- main col -->
     <section class="col-lg-12">

        <div class="box">
		      <div class="box-header with-border">
		       <%--  <h3 class="box-title"><spring:message code="Label.VIEWDISTRICT" htmlEscape="true"></spring:message></h3>
		      </div> --%><!-- /.box-header -->

			<form:form action="viewDistrictAction.htm" method="POST"	commandName="districtView" id="frmViewDistrict"	name="frmViewDistrict" class="form-horizontal">
			<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="viewDistrictAction.htm"/>" />
			<input type="hidden" id="fileNameToDownload"	name="fileNameToDownload" /> 
			<input type="hidden" id="orderCode" name="orderCode" />
							
			<div class="box-body">
			
			
			<%-- <div class="box-header subheading">
                  		<h4 class="box-title"><spring:message code="Label.DISTRICTDETAIL" htmlEscape="true"></spring:message></h4>
            </div> --%><!-- /.box-header -->
            
            
            				
							
			<%-- <c:forEach var="listDistrictDetails" varStatus="listDistrictDetailsRow" items="${districtView.listDistrictDetails}">
			<div class="form-group">
					  
					  <div class="col-sm-12">
					  
					  <label class="well-inline section_info">
					  	
            			
            			<div class="well_section">
            			
            			<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message code="Label.GENERALDETAILS" htmlEscape="true"></spring:message></h4>
            			</div>
            			
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.DISTRICTCODE' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							 <spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].districtCode">
								<c:out value="${status.value}" escapeXml="true"></c:out>
							</spring:bind>
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.DISTRICTVERSION' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							  <spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].districtVersion">
											 <c:out value="${status.value}" escapeXml="true"></c:out>
								</spring:bind>
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.DISTRICTNAMEENG' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							 <spring:bind	path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].districtNameEnglish">
								<c:out value="${status.value}" escapeXml="true"></c:out>
							 </spring:bind>
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.DISTRICTNAMELOCAL' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							 <spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].districtNameLocal">
								 <c:out value="${status.value}" escapeXml="true"></c:out>
							</spring:bind>
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.DISTRICTALIASENGLISH' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							 <spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].aliasEnglish">
								<c:out value="${status.value}" escapeXml="true"></c:out>
							</spring:bind>
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.DISTRICTALIASLOCAL' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							<spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].aliasLocal">
									<c:out value="${status.value}" escapeXml="true"></c:out>
							 </spring:bind> 
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.HEADQUARTERSDISTRICT' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							<spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].headquarterName">	
									<c:out value="${status.value}" escapeXml="true"></c:out>
							 </spring:bind> 
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.CENSUS2001' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							<spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].census2001Code">
									<c:out value="${status.value}" escapeXml="true"></c:out>
							 </spring:bind> 
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.CENSUSCODE2011' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							 <spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].census2011Code">
									<c:out value="${status.value}" escapeXml="true"></c:out>
							 </spring:bind> 
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.STATESPECIFICCODE' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							 <spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].sscode">
									<c:out value="${status.value}" escapeXml="true"></c:out>
							 </spring:bind> 
					  	  </div>
						</div>
					  
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
							  <spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].orderNocr">
									<c:out value="${status.value}" escapeXml="true"></c:out>
							 </spring:bind> 
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-5 control-label" for="sel1"><spring:message code='Label.ORDERDATE' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							 <fmt:formatDate var="orderDatecr" value="${listDistrictDetails.orderDatecr}" pattern="dd/MM/yyyy" /> <c:out value="${orderDatecr}" escapeXml="true"></c:out>		
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-5 control-label" for="sel1"><spring:message code='Label.EFFECTIVEDATE' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
						  <fmt:formatDate var="ordereffectiveDatecr" value="${listDistrictDetails.ordereffectiveDatecr}" pattern="dd/MM/yyyy" /><c:out value= "${ordereffectiveDatecr}" escapeXml="true"></c:out>
							 
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-5 control-label" for="sel1"><spring:message code='Label.GAZPUBDATE' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							<fmt:formatDate var="gazPubDatecr" value="${listDistrictDetails.gazPubDatecr}" pattern="dd/MM/yyyy hh:mm:ss" /> <c:out value="${gazPubDatecr}" escapeXml="true"></c:out>
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
			
			
			
			
			</c:forEach> --%>
			
			<c:forEach var="listDistrictDetails" varStatus="listDistrictDetailsRow" items="${districtView.listDistrictDetails}">
			
			
			<div class="box-header subheading">
                  <h4 class="box-title"><spring:message code="Label.GENERALDETAILS" htmlEscape="true"></spring:message></h4>
            	</div>
			  <table class="table table-bordered table-hover">
                    
				 <tbody>
				 
				 <tr>
				 <td><spring:message code='Label.DISTRICTCODE' htmlEscape="true"/></td>
				 <td><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].districtCode">
			         <c:out value="${status.value}" escapeXml="true"></c:out></spring:bind></td>
				 </tr>
				 
				  <tr>
				 <td><spring:message code='Label.DISTRICTVERSION' htmlEscape="true"/></td>
				 <td><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].districtVersion">
					 <c:out value="${status.value}" escapeXml="true"></c:out></spring:bind></td>
				 </tr>
				    <tr>
				  <td><spring:message htmlEscape="true"  code="label.minor.version"></spring:message></td>
				  <td>
				  <spring:bind  path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].minorVersion">
				  <c:out value="${status.value}" escapeXml="true"></c:out></spring:bind></td>
				 </tr>
				  <tr>
				 <td><spring:message code='Label.DISTRICTNAMEENG' htmlEscape="true"/></td>
				 <td><spring:bind	path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].districtNameEnglish">
					 <c:out value="${status.value}" escapeXml="true"></c:out> </spring:bind></td>
				 </tr>
				 
				 <tr>
				 <td><spring:message code='Label.DISTRICTNAMELOCAL' htmlEscape="true"/></td>
				 <td><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].districtNameLocal">
					  <c:out value="${status.value}" escapeXml="true"></c:out></spring:bind></td>
				 </tr>
				 
				  <tr>
				 <td><spring:message code='Label.DISTRICTALIASENGLISH' htmlEscape="true"/> </td>
				 <td><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].aliasEnglish">
					<c:out value="${status.value}" escapeXml="true"></c:out></spring:bind></td>
				 </tr>
				 
				 
				  <tr>
				 <td><spring:message code='Label.DISTRICTALIASLOCAL' htmlEscape="true"/></td>
				 <td><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].aliasLocal">
					 <c:out value="${status.value}" escapeXml="true"></c:out></spring:bind></td>
				 </tr>
				 
		         <tr>
				 <td><spring:message code='Label.HEADQUARTERSDISTRICT' htmlEscape="true"/></td>
				 <td><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].headquarterName">	
					 <c:out value="${status.value}" escapeXml="true"></c:out></spring:bind></td>
				 </tr>
				  
				  <tr>
				 <td><spring:message code='Label.CENSUS2001' htmlEscape="true"/></td>
				 <td><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].census2001Code">
					 <c:out value="${status.value}" escapeXml="true"></c:out></spring:bind></td>
				 </tr>
				 
				   <tr>
				 <td><spring:message code='Label.CENSUSCODE2011' htmlEscape="true"/></td>
				 <td><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].census2011Code">
					<c:out value="${status.value}" escapeXml="true"></c:out></spring:bind></td>
				 </tr>
				 
				 
				 <tr>
				 <td><spring:message code='Label.STATESPECIFICCODE' htmlEscape="true"/></td>
				 <td> <spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].sscode">
					 <c:out value="${status.value}" escapeXml="true"></c:out></spring:bind></td>
				 </tr>
				 
				 </tbody>
				 </table>
				   
				   
				   <div class="box-header subheading">
                  		<h4 class="box-title"><spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message></h4>
            			</div>
				
				    <table class="table table-bordered table-hover">
				    <tbody>
				    
				    <tr>
				     <td><spring:message code='Label.ORDERNO' htmlEscape="true"/> </td>
				     <td><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].orderNocr">
						  <c:out value="${status.value}" escapeXml="true"></c:out></spring:bind></td>
				    </tr>
				     
				     <tr>
				     <td><spring:message code='Label.ORDERDATE' htmlEscape="true"/></td>
				     <td><fmt:formatDate var="orderDatecr" value="${listDistrictDetails.orderDatecr}" pattern="dd/MM/yyyy" /> <c:out value="${orderDatecr}" escapeXml="true"></c:out></td>
				    </tr>
				    
				     <tr>
				     <td><spring:message code='Label.EFFECTIVEDATE' htmlEscape="true"/></td>
				     <td><fmt:formatDate var="ordereffectiveDatecr" value="${listDistrictDetails.ordereffectiveDatecr}" pattern="dd/MM/yyyy" /><c:out value= "${ordereffectiveDatecr}" escapeXml="true"></c:out></td>
				    </tr>
				    
				    <tr>
				 <td><spring:message code='Label.GAZPUBDATE' htmlEscape="true"/></td>
				 <td><fmt:formatDate var="gazPubDatecr" value="${listDistrictDetails.gazPubDatecr}" pattern="dd/MM/yyyy hh:mm:ss" /> <c:out value="${gazPubDatecr}" escapeXml="true"></c:out></td>
				 </tr>
				 
				    </tbody>
				    </table>
				    <div class="form-group">
							<c:if test="${attachmentList.size() > 0 }">
								 <table class="table table-bordered table-hover">
                    
				               <tbody>
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
										</tbody>
								</table>
								</c:if>
								<div class="form-group" align="center">
								 <form:errors path="noOrderRecord" class="mandatory"/>
						  	  	</div>
						  	    </div>
				    			</c:forEach>
		               </div>
						</form:form>
					
		</div>
		</section>
		</div>
		</section>



						
</body>
</html>