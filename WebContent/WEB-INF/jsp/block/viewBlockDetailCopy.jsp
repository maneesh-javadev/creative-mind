<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
 
		</script>

</head>
<body>


<section class="content">
	
		<div class="row">
        	<section class="col-lg-12 ">
	              	<div class="box ">
	              	
	              	
           				<div class="box-header with-border">
		                	<h3 class="box-title"><spring:message htmlEscape="true" code="Label.MODIFYBLOCK"></spring:message></h3>
		                </div>
		                
		                
		                
		                <div class="box-body">
		                
		                
		               
		                
		                <form:form action="modifyBlockAction.htm" method="POST" commandName="blockView" id="frmModifyBlock">
						 <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyBlockAction.htm"/>"/>
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
							      
							      
								 </c:forEach>
								 </tbody>
							 
							 </table>
						 
						 
						 
						 </form:form>
		                <script src="/LGD/JavaScriptServlet"></script>
		                </div>
		                <div class="box-footer">
                     		<div class="col-sm-offset-2 col-sm-10">
                       			<div class="pull-right">
									<button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>	
                        		</div>
                    		 </div>   
                  		</div>
		             </div>
		     </section>
		  </div>
		</section>
</body>
</html>