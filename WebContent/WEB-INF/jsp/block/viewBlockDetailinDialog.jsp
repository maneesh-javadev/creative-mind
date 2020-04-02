<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/lgdDwrStateService.js"></script>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
function retrieveFile(fileNameToDownload){
	//alert("hello"+fileNameToDownload);
	
		//alert("Inside the correct Method");
        document.getElementById("fileNameToDownload").value=fileNameToDownload;
        document.frmModifyBlock.method="post";
        document.frmModifyBlock.action="reportFileDownloads.htm?<csrf:token uri='reportFileDownloads.htm'/>";
		document.frmModifyBlock.submit();
        return true;
}
		</script>

</head>
<body>


<section class="content">
	
		<div class="row">
        	<section class="col-lg-12 ">
	              	<div class="box ">
	              	
	              	
           				
		                
		                
		                <div class="box-body">
		                
		                
		               
		                
		                <form:form action="modifyBlockAction.htm" method="POST" commandName="blockView" id="frmModifyBlock" name="frmModifyBlock">
						 <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyBlockAction.htm"/>"/>
						 <input type="hidden" id="fileNameToDownload" name="fileNameToDownload" />
						 <h2><center><font size="3"><esapi:encodeForHTML>${successMsg}</esapi:encodeForHTML></font></center></h2>
							 <div class="box-header subheading">
									<h4><spring:message htmlEscape="true"  code="Label.BLOCKDETAIL"></spring:message></h4>	
			                  </div>
							 <table class="table table-bordered table-hover">
							 
								 <tbody>
								 <c:forEach var="listBlockDetails" varStatus="listBlockDetailsRow" items="${blockView.listBlockDetails}">
								 
								 	<tr>
							        	<td><spring:message htmlEscape="true"  code="Label.BLOCKCODE"></spring:message></td>
							        	<td>
							        	<spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].blockCode">
							        		<c:out value="${status.value}" escapeXml="true"></c:out>
							        	</spring:bind>
							        	</td>
							      </tr>
							      <tr>
							        	<td><spring:message htmlEscape="true"  code="Label.BLOCKVER"></spring:message></td>
							        	<td>
							        	<spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].blockVersion">
							        		<c:out value="${status.value}" escapeXml="true"></c:out>
										</spring:bind>
							        	</td>
							      </tr>
							      
							      <tr>
							        	<td><spring:message htmlEscape="true"  code="label.minor.version"></spring:message></td>
							        	<td>
							        	<spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].minorVersion">
							        		<c:out value="${status.value}" escapeXml="true"></c:out>
										</spring:bind>
							        	</td>
							      </tr>
							      
							      <tr>
							        	<td><spring:message htmlEscape="true"  code="Label.BLOCKNAMEINENGLISH"></spring:message></td>
							        	<td>
							        	<spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].blockNameEnglish">
							        		<c:out value="${status.value}" escapeXml="true"></c:out>
										</spring:bind>
							        	</td>
							      </tr>
							      
							      <tr>
							        	<td><spring:message htmlEscape="true"  code="Label.BLOCKNAMEINLOCAL"></spring:message></td>
							        	<td>
							        	<spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].blockNameLocal">
							        		<c:out value="${status.value}" escapeXml="true"></c:out>
										</spring:bind>
							        	</td>
							      </tr>
							      
							      <tr>
							        	<td><spring:message htmlEscape="true"  code="Label.BLOCKALIASENGLISH"></spring:message></td>
							        	<td>
							        	<spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].aliasEnglish">
							        		<c:out value="${status.value}" escapeXml="true"></c:out>
										</spring:bind>
							        	</td>
							      </tr>
							      
							      <tr>
							        	<td><spring:message htmlEscape="true"  code="Label.BLOCKALIASLOCAL"></spring:message></td>
							        	<td>
							        	<spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].aliasLocal">
							        		<c:out value="${status.value}" escapeXml="true"></c:out>
										</spring:bind>
							        	</td>
							      </tr>
							      
							      
							      <tr>
							        	<td><spring:message htmlEscape="true"  code="Label.STATESPECIFICCODE"></spring:message></td>
							        	<td>
							        	<spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].ssCode">
							        		<c:out value="${status.value}" escapeXml="true"></c:out>
										</spring:bind>
							        	</td>
							      </tr>
							      
							      <tr>
							        	<td><spring:message htmlEscape="true"  code="Label.DISTRICTHEADQUARTERENGLISH"></spring:message></td>
							        	<td>
							        	<spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].headquarterName">
							        		<c:out value="${status.value}" escapeXml="true"></c:out>
										</spring:bind>
							        	</td>
							      </tr>
							      
							      <tr>
							        	<td><spring:message htmlEscape="true"  code="Label.SUBDISTRICTHEADQUARTERLOCAL"></spring:message></td>
							        	<td>
							        	<spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].headquarterNameLocal">
							        		<c:out value="${status.value}" escapeXml="true"></c:out>
										</spring:bind>
							        	</td>
							      </tr>
							      
							      <tr>
							        	<td><spring:message htmlEscape="true"  code="Label.ORDERNO"></spring:message></td>
							        	<td>
							        	<spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].orderNocr">
							        		<c:out value="${status.value}" escapeXml="true"></c:out>
										</spring:bind>
							        	</td>
							      </tr>
							      
							       <tr>
							        	<td><spring:message htmlEscape="true"  code="Label.ORDERDATE"></spring:message></td>
							        	<td>
							        	<spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].orderDatecr">
							        		<c:out value="${status.value}" escapeXml="true"></c:out>
										</spring:bind>
							        	</td>
							      </tr>
							      
							       <tr>
							        	<td><spring:message htmlEscape="true"  code="Label.EFFECTIVEDATE"></spring:message></td>
							        	<td>
							        	<spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].ordereffectiveDatecr">
							        		<c:out value="${status.value}" escapeXml="true"></c:out>
										</spring:bind>
							        	</td>
							      </tr>
							      
							      <tr>
							        	<td><spring:message htmlEscape="true"  code="Label.GAZPUBDATE"></spring:message></td>
							        	<td>
							        	<spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].gazPubDatecr">
							        		<c:out value="${status.value}" escapeXml="true"></c:out>
										</spring:bind>
							        	</td>
							      </tr>
							      <tr>
							        	<td><spring:message code="Label.VIEW.GOVERNMENTORDER"></spring:message></td>
							        	<td>
							        	<spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].gazPubDatecr">
							        		<c:out value="${status.value}" escapeXml="true"></c:out>
										</spring:bind>
							        	</td>
							      </tr>
							      
								 </c:forEach>
								 </tbody>
							 
							 </table>
							 <table class="table table-striped table-bordered" width="100%" >
									<tr class="tblRowTitle tblclear">
										<td width="5%"><spring:message code="Label.FILENAME"></spring:message></td>
										<td width="16%"><spring:message code="Label.FILESIZE"></spring:message></td>
										<td width="10%"><spring:message code="addAttachment.filecontenttype"></spring:message></td>
										<td width="10%" align="center"><spring:message code="Label.VIEW.GOVERNMENTORDER"></spring:message></td>
									</tr>
						 			<tr>
						 			
						 			
						 				<td width="5%"><c:out value="${attachment.fileName}" /> </td>
										<td width="16%"><c:out value="${attachment.fileSize}" /></td>
										<td width="10%"><c:out value="${attachment.fileContentType}" /></td>
										<td width="10%" align="center">
										<c:choose>
											<c:when test="${empty attachment.fileTimestamp}">
											N.A.
											</c:when>
											<c:otherwise>
												<a href="#">
													<img	src="images/view.png" name="viewAttachment"	width="22" height="18" alt="View" border="0" onclick="javascript:retrieveFile('<c:out value="${attachment.fileTimestamp}"/>');" />
												</a>
											</c:otherwise>
										</c:choose>
										
											
										</td>
						 			</tr>
						 		</table>
						 	<div class="box-header subheading">
									<h4><spring:message htmlEscape="true" code="Label.BLOCKHISTORY"></spring:message></h4>	
			                  </div>
						 	<table class="table table-striped table-bordered" cellspacing="0">
								
											     <thead>
												         <th  rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></th>
														 <th rowspan="2"><spring:message htmlEscape="true"  code="Label.BLOCKCODE"></spring:message></th>
														 <th  rowspan="2"><spring:message htmlEscape="true"  code="Label.BLOCKVER"></spring:message></th>
														  <th  rowspan="2"><spring:message htmlEscape="true"  code="label.minor.version"></spring:message></th>
														 <th  rowspan="2"><spring:message htmlEscape="true"  code="Label.BLOCKNAMEINENGLISH"></spring:message></th>
										  				 <th  rowspan="2"><spring:message htmlEscape="true"  code="Label.ACTIVEBLOCK"></spring:message></th>
														 <th  rowspan="2"><spring:message htmlEscape="true"  code="Label.CREATEDFROM"></spring:message></th>
												</thead>
								    			<tbody>					    
									         <c:forEach var="blockHistoryDetail" varStatus="listBlockRow" items="${blockHistory}">
												  <tr>
														<td><c:out value="${listBlockRow.index+1}" escapeXml="true"></c:out></td>
														<td ><c:out value="${blockHistoryDetail.blockCode}" escapeXml="true"></c:out></td>
														<td ><c:out value="${blockHistoryDetail.blockVersion}" escapeXml="true"></c:out></td>
															<td ><c:out value="${blockHistoryDetail.minorVersion}" escapeXml="true"></c:out></td>
														<td ><c:out value="${blockHistoryDetail.blockNameEnglish}" escapeXml="true"></c:out></td>
														<td ><c:out value="${blockHistoryDetail.lrReplaces}" escapeXml="true"></c:out></td>
									    				<td ><c:out value="${blockHistoryDetail.active}" escapeXml="true"></c:out></td>
									    				</tr>
											</c:forEach>
								   			</tbody>						
								 
 						   	</table>
						 </form:form>
		                <script src="/LGD/JavaScriptServlet"></script>
		                </div>
		             
		             </div>
		     </section>
		  </div>
		</section>
</body>
</html>