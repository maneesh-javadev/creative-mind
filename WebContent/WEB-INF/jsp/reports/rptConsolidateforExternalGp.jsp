<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>

<%!String sessionId;%>
<%
	
	sessionId = request.getSession().getId();
%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script type="text/javascript" language="Javascript">
		$(document).ready(function(){
			 
			$('#example').dataTable({
				"pageLength": 50
		     });
			 
			});
			
	</script>
</head>
<body>
	 <section class="content">
		 <div class="row">
		 	<section class="col-lg-12 ">
	        	<div class="box ">
		        	<div class="box-header with-border">
		            	<h3 class="box-title"><c:out value="${Message}" escapeXml="true"></c:out></h3>
		            </div>
		            <div class="box-body">
	            		
					
		
						<div class="row">
					    	<div class="col-sm-12">
					    			<label class="left_padding">*N.A.- Not Applicable</label>
					    	</div>
					    </div>
					   	
		
		
		
		 				
						 <div class="row">
					    	<div class="col-sm-12 left_padding">
							
										
							<table class="table table-striped table-bordered dataTable"  id="example" >
										<c:set var="totalVP" scope="page" value="0" />
										<c:set var="totalIP" scope="page" value="0" />
										
									<thead>
										<tr>
										   <th width="4%" style="text-align: right;padding-right: 20px">
										      <label>
										         <spring:message
										            code="Label.SRNO" htmlEscape="true"></spring:message>
										      </label>
										   </th>
										   <th width="20%" style="text-align: right;padding-right: 20px">
										      <label>
										         <spring:message
										            code="Label.VILLAGEPCODE" htmlEscape="true"></spring:message>
										      </label>
										   </th>
										   <th width="20%" align="left">
										      <label>
										         <spring:message code="Label.VILLAGEPANCHAYATNAME"
										            htmlEscape="true"></spring:message>
										      </label>
										   </th>
										   <th width="22%" align="left">
										      <label>
										         <spring:message code="Label.VILLAGEPNAMELOCAL"
										            htmlEscape="true"></spring:message>
										      </label>
										   </th>
										   <th width="12%" style="text-align: right;padding-right: 20px">
										      <label>
										         <spring:message code="Label.NOOFCENSUSVILLAGESMAPPED"
										            htmlEscape="true"></spring:message>
										      </label>
										   </th>
										</tr>
									</thead>
									<tbody>
						`				<c:if test="${! empty consolidateLBList}">
											 <c:forEach var="panchaytSetUp" varStatus="panchaytSetUpRow" items="${consolidateLBList}">
											      <tr>
											         <td width="4%" style="text-align: right;padding-right: 20px">
											            <c:out value="${panchaytSetUpRow.index +1}" escapeXml="true"></c:out>
											         </td>
											         <td width="15%" style="text-align: right;padding-right: 20px">
											            <c:out
											               value="${panchaytSetUp.localBodyCode}" escapeXml="true"></c:out>
											         </td>
											         <td width="20%" align="left">
											            <c:out
											               value="${panchaytSetUp.localbodyNameEnglish}" escapeXml="true"></c:out>
											         </td>
											         <td width="22%" align="left">
											            <c:out
											               value="${panchaytSetUp.localbodyNameLocal}" escapeXml="true"></c:out>
											         </td>
											         <td width="23%" style="text-align: right;padding-right: 20px">
											            <c:choose>
											               <c:when test="${panchaytSetUp.childCount == null}">
											                  <c:out value="0"></c:out>
											               </c:when>
											               <c:otherwise>
											                  <c:out value="${panchaytSetUp.childCount}" escapeXml="true"></c:out>
											               </c:otherwise>
											            </c:choose>
											         </td>
											      </tr>
											   </c:forEach>
											</c:if>
											<tr>
												
											</tr>
										</tbody>
							</table>
							</div>
						</div>
		
	
							
					</div>
				 </div>
				</section>
			   </div>
		</section>
	
</body>

</html>
