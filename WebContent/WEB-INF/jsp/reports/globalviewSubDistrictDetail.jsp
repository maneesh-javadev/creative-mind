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
<%
		String requiredTitleValue = "no";
	%>
	<%
		List<Attachment> listOFMetaData = (List<Attachment>) request
				.getAttribute("attachmentList");
	%>
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


<section class="content">
  <div class="row">
          <!-- main col -->
     <section class="col-lg-12">

        <div class="box">
		     <%--  <div class="box-header with-border">
		        <h3 class="box-title"><spring:message code="Label.VIEWSUBDISTRICT" htmlEscape="true"></spring:message></h3>
		      </div> --%><!-- /.box-header -->

			<form:form action="modifySubDistrictAction.htm" method="POST" commandName="modifySubDistrictCmd" id="frmModifySubDistrict" name="frmModifySubDistrict" class="form-horizontal">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifySubDistrictAction.htm"/>" />
			<input type="hidden" id="fileNameToDownload" name="fileNameToDownload" /> 
			<input type="hidden" id="orderCode" name="orderCode" />
							
			
			
			
			<%-- <div class="box-header subheading">
                  		<h4 class="box-title"><spring:message code="Label.SUBDISTRICTDETAIL" htmlEscape="true"></spring:message></h4>
            </div> --%><!-- /.box-header -->				
							
			<%-- <c:forEach var="listSubdistrictDetails" varStatus="listSubdistrictDetailsRow" items="${modifySubDistrictCmd.listSubdistrictDetails}">
			<div class="form-group">
					  
					  <div class="col-sm-12">
					  
					  <label class="well-inline section_info">
					  	
            			
            			<div class="well_section">
            			
            			<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message code="Label.GENERALDETAILS" htmlEscape="true"></spring:message></h4>
            			</div>
            			
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.SUBDISTRICTCODE' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							 <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictCode">
								<c:out value="${status.value}" escapeXml="true"></c:out>
							</spring:bind>
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.SUBDISTRICTVERSION' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							  <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictVersion">
											 <c:out value="${status.value}" escapeXml="true"></c:out>
								</spring:bind>
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.SUBDISTRICTNAMEENGLISH' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							 <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictNameEnglish">
								<c:out value="${status.value}" escapeXml="true"></c:out>
							 </spring:bind>
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.SUBDISTRICTNAMELOCAL' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							 <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictNameLocal">
								 <c:out value="${status.value}" escapeXml="true"></c:out>
							</spring:bind>
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.SUBDISTRICTALIASENGLISH' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].aliasEnglish">
								<c:out value="${status.value}" escapeXml="true"></c:out>
							</spring:bind>
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.SUBDISTRICTALIASLOCAL' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].aliasLocal">
									<c:out value="${status.value}" escapeXml="true"></c:out>
							 </spring:bind> 
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.HEADQUARTER' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].headquarterName">
									<c:out value="${status.value}" escapeXml="true"></c:out>
							 </spring:bind> 
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.CENSUS2001' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].census2001Code">
									<c:out value="${status.value}" escapeXml="true"></c:out>
							 </spring:bind> 
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.CENSUSCODE2011' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].census2011Code">
									<c:out value="${status.value}" escapeXml="true"></c:out>
							 </spring:bind> 
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-6 control-label" for="sel1"><spring:message code='Label.STATESPECIFICCODE' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							 <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].sscode">
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
							 <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderNocr">
									<c:out value="${status.value}" escapeXml="true"></c:out>
							 </spring:bind> 
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-5 control-label" for="sel1"><spring:message code='Label.ORDERDATE' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							<fmt:formatDate var="orderDatecr" value="${listSubdistrictDetails.orderDatecr}" pattern="dd/MM/yyyy" /><c:out value="${orderDatecr}" escapeXml="true"></c:out>
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-5 control-label" for="sel1"><spring:message code='Label.EFFECTIVEDATE' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
						  <fmt:formatDate var="ordereffectiveDatecr" value="${listSubdistrictDetails.ordereffectiveDatecr}" pattern="dd/MM/yyyy" /><c:out value="${ordereffectiveDatecr}" escapeXml="true"></c:out>
							 
					  	  </div>
						</div>
						
						<div class="form-group">
						  <label  class="col-sm-5 control-label" for="sel1"><spring:message code='Label.GAZPUBDATE' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
							<fmt:formatDate var="gazPubDatecr"	value="${listSubdistrictDetails.gazPubDatecr}" pattern="dd/MM/yyyy hh:mm:ss" /><c:out value="${gazPubDatecr}" escapeXml="true"></c:out>
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
			<div class="box-body">
			<c:forEach var="listSubdistrictDetails" varStatus="listSubdistrictDetailsRow" items="${modifySubDistrictCmd.listSubdistrictDetails}">
			
			<div class="box-header subheading">
                  <h4 class="box-title"><spring:message code="Label.GENERALDETAILS" htmlEscape="true"></spring:message></h4>
            	</div>
				<table class="table table-bordered table-hover">
                     <tbody>
				       <tr>
				         <td><spring:message code='Label.SUBDISTRICTCODE' htmlEscape="true"/> </td>
						  <td><spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictCode">
							<c:out value="${status.value}" escapeXml="true"></c:out></spring:bind></td>
	                    </tr>
	                    
	                     <tr>
				         <td><spring:message code='Label.SUBDISTRICTVERSION' htmlEscape="true"/></td>
						  <td><spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictVersion">
								<c:out value="${status.value}" escapeXml="true"></c:out></spring:bind></td>
	                    </tr>
	                    <tr>
				          <td><spring:message htmlEscape="true"  code="label.minor.version"></spring:message></td>
				          <td>
				           <spring:bind  path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].minorVersion">
				           <c:out value="${status.value}" escapeXml="true"></c:out></spring:bind></td>
				           </tr>
	                     <tr>
				         <td><spring:message code='Label.SUBDISTRICTNAMEENGLISH' htmlEscape="true"/> </td>
						  <td> <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictNameEnglish">
								<c:out value="${status.value}" escapeXml="true"></c:out>
							 </spring:bind></td>
	                    </tr>
	                    
	                    <tr>
				         <td><spring:message code='Label.SUBDISTRICTNAMELOCAL' htmlEscape="true"/></td>
						  <td><spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictNameLocal">
								 <c:out value="${status.value}" escapeXml="true"></c:out>
							</spring:bind></td>
	                    </tr>
	                    
	                    <tr>
				         <td><spring:message code='Label.SUBDISTRICTALIASENGLISH' htmlEscape="true"/> </td>
						  <td><spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].aliasEnglish">
								<c:out value="${status.value}" escapeXml="true"></c:out>
							</spring:bind></td>
	                    </tr>
	                    
	                     <tr>
				         <td><spring:message code='Label.SUBDISTRICTALIASLOCAL' htmlEscape="true"/></td>
						  <td><spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].aliasLocal">
									<c:out value="${status.value}" escapeXml="true"></c:out>
							 </spring:bind></td>
	                    </tr>
				       
				       <tr>
				         <td><spring:message code='Label.HEADQUARTER' htmlEscape="true"/></td>
						  <td><spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].headquarterName">
									<c:out value="${status.value}" escapeXml="true"></c:out>
							 </spring:bind></td>
	                    </tr>
	                    
	                     <tr>
				         <td><spring:message code='Label.CENSUS2001' htmlEscape="true"/></td>
						  <td><spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].census2001Code">
									<c:out value="${status.value}" escapeXml="true"></c:out>
							 </spring:bind></td>
	                    </tr>
	                    
	                    <tr>
				         <td><spring:message code='Label.CENSUSCODE2011' htmlEscape="true"/></td>
						  <td><spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].census2011Code">
									<c:out value="${status.value}" escapeXml="true"></c:out>
							 </spring:bind></td>
	                    </tr>
	                    
	                    <tr>
				         <td><spring:message code='Label.STATESPECIFICCODE' htmlEscape="true"/></td>
						  <td><spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].sscode">
									<c:out value="${status.value}" escapeXml="true"></c:out>
							 </spring:bind></td>
	                    </tr>
	                      </tbody>
				       </table>
			       
			       
			            <div class="box-header subheading">
                  		<h4 class="box-title"><spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message></h4>
            			</div>
				
				    <table class="table table-bordered table-hover">
                    
				      <tbody>
				 
				       <tr>
				       <td><spring:message code='Label.ORDERNO' htmlEscape="true"/></td>
						  <td><spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderNocr">
							<c:out value="${status.value}" escapeXml="true"></c:out>
							 </spring:bind></td>
				       </tr>
				       
				       <tr>
				       <td><spring:message code='Label.ORDERDATE' htmlEscape="true"/></td>
						  <td><fmt:formatDate var="orderDatecr" value="${listSubdistrictDetails.orderDatecr}" pattern="dd/MM/yyyy" /><c:out value="${orderDatecr}" escapeXml="true"></c:out></td>
				       </tr>
				       
				       <tr>
				       <td><spring:message code='Label.EFFECTIVEDATE' htmlEscape="true"/></td>
						  <td><fmt:formatDate var="ordereffectiveDatecr" value="${listSubdistrictDetails.ordereffectiveDatecr}" pattern="dd/MM/yyyy" /><c:out value="${ordereffectiveDatecr}" escapeXml="true"></c:out></td>
				       </tr>
				       
				       <tr>
				       <td><spring:message code='Label.GAZPUBDATE' htmlEscape="true"/></td>
						  <td><fmt:formatDate var="gazPubDatecr"	value="${listSubdistrictDetails.gazPubDatecr}" pattern="dd/MM/yyyy hh:mm:ss" /><c:out value="${gazPubDatecr}" escapeXml="true"></c:out></td>
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
			
			</div>
		
		</form:form>
					
		</div>
		</section>
		</div>
		</section>



			


</body>
</html>