<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%!
String sessionId;
String contextPath;
%>
<%
	contextPath = request.getContextPath();
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
					    	<div class="left_padding">
						    	<div class="col-sm-12 left_padding">
						    	<c:if test="${!empty consolidateLBList}">
									<label>** Due to periodic elections, data is dynamic in nature and keep on changing</label>
								</c:if>
								<input type="hidden" id="sessionId" value='<%=sessionId%>'></input>
						    	</div>
					    	</div>
					    	
					    </div>	
					     <div class="row">
					    	<div class="col-sm-12">
					    			<div class="left_padding">
					    				<c:if test="${!empty consolidateLBList}">
										<label>*** Local Government Directory is now mapped to Census 2011 village codes</label>
										</c:if>
										<input type="hidden" id="sessionId" value='<%=sessionId%>'></input>
					    			
					    			</div>
					    		
					    	</div>
					    </div>		
		
		
		
		 				
						 <div class="row">
					    	<div class="col-sm-12 left_padding">
							
										
							<table class="table table-striped table-bordered dataTable"  id="example" >
										<c:set var="totalVP" scope="page" value="0" />
										<c:set var="totalIP" scope="page" value="0" />
										
									<thead>
										<c:set var="totalVP" scope="page" value="0" />
										<tr>
										   <th width="4%" style="text-align: right;padding-right: 20px">
										      <label>
										         <spring:message
										            code="Label.SRNO" htmlEscape="true"></spring:message>
										      </label>
										   </th>
										   <th width="15%" style="text-align: right;padding-right: 20px">
										      <label>
										         <spring:message
										            code="Label.LGDCODE" htmlEscape="true"></spring:message>
										      </label>
										   </th>
										   <th width="15%">
										      <label>
										         <spring:message
										            code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message>
										      </label>
										   </th>
										   <th width="25%" align="left">
										      <label>
										         <spring:message code="Label.INTERMLBRPT"
										            htmlEscape="true"></spring:message>
										      </label>
										   </th>
										   <th width="25%" align="left">
										      <label>
										         <spring:message code="Label.INTERMLBRPTLOCAL"
										            htmlEscape="true"></spring:message>
										      </label>
										   </th>
										   <th width="15%" style="text-align: right;padding-right: 20px">
										      <label>
										         <spring:message code="Label.NO.VILLAGEPANRPT"
										            htmlEscape="true"></spring:message>
										      </label>
										   </th>
										</tr>
									</thead>
									<tbody>
						`				<c:if test="${! empty consolidateLBList}">
										   <c:forEach var="panchaytSetUp" varStatus="panchaytSetUpRow" items="${consolidateLBList}">
										      <c:set var="index" value="${panchaytSetUpRow.index +1}" />
										      <c:set var="lbCode" value="${panchaytSetUp.localBodyCode}" />
										      <c:set var="unitName" value="${panchaytSetUp.localbodyNameEnglish}" />
										      <tr>
										         <td width="4%" style="text-align: right;padding-right: 20px">
										            <c:out value="${index}" escapeXml="true"></c:out>
										         </td>
										         <td width="15%" style="text-align: right;padding-right: 20px">
										            <c:out value="${lbCode}" escapeXml="true"></c:out>
										         </td>
										         <td width="15%" align="center">
										            <c:out value="${panchaytSetUp.sscode}" escapeXml="true"></c:out>
										         </td>
										         <td width="15%" align="left">
										            <c:out value="${unitName}" escapeXml="true"></c:out>
										         </td>
										         <td width="15%" align="left">
										            <c:out
										               value="${panchaytSetUp.localbodyNameLocal}" escapeXml="true"></c:out>
										         </td>
										         <td width="15%" style="text-align: right;padding-right: 20px">
										            <c:choose>
										               <c:when test="${panchaytSetUp.childCount == 0}">
										                  <c:out value="N.A."></c:out>
										               </c:when>
										               <c:otherwise>
										                  <c:out value="${panchaytSetUp.childCount}" escapeXml="true"></c:out>
										                  <c:set var="totalVP" scope="page" value="${totalVP+panchaytSetUp.childCount}" />
										               </c:otherwise>
										            </c:choose>
										         </td>
										      </tr>
										   </c:forEach>
										</c:if>
										</tbody>
										<tfoot>
										<tr>
											   <th width="4%"></th>
											   <th></th>
											   <th></th>
											   <th width="46%" align="left" colspan="2">
											      <spring:message htmlEscape="true" code="Label.TOTALS"></spring:message>
											   </th>
											   <th width="23%" style="text-align: right;padding-right: 20px">
											      <c:out value="${totalVP}" escapeXml="true"></c:out>
											   </th>
											  
											  
											</tr>
										</tfoot>
											
										
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
