<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>

<script>
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
										<tr>
											<th width="4%">
											   <label>
											      <spring:message
											         code="Label.SRNO" htmlEscape="true"></spring:message>
											   </label>
											</th>
											<th width="12%" align="center">
											   <label>
											      <spring:message code="Label.VILLAGECODE"
											         htmlEscape="true"></spring:message>
											   </label>
											</th>
											<th width="20%" align="center">
											   <label>
											      <spring:message code="Label.CENSUSFORVILLAGE"
											         htmlEscape="true"></spring:message>
											   </label>
											</th>
											<th width="22%" align="left">
											   <label>
											      <spring:message code="Label.VILLAGENAME"
											         htmlEscape="true"></spring:message>
											   </label>
											</th>
											<th width="22%" align="left">
											   <label>
											      <spring:message code="Label.COVERAGEDTYPE"
											         htmlEscape="true"></spring:message>
											   </label>
											</th>
											<th width="22%" align="center">
											   <label>
											      <spring:message code="Label.MAINVILLAGE"
											         htmlEscape="true"></spring:message>
											   </label>
											</th>
										</tr>
									</thead>
									<tbody>
						`			<c:if test="${! empty consolidateLBList}">
									   <c:forEach var="panchaytSetUp" varStatus="panchaytSetUpRow" items="${consolidateLBList}">
									      <tr>
									         <td width="4%">
									            <c:out value="${panchaytSetUpRow.index +1}" escapeXml="true"></c:out>
									         </td>
									         <td width="12%" align="center">
									            <c:out
									               value="${panchaytSetUp[0]}" escapeXml="true"></c:out>
									         </td>
									         <td width="20%" align="center">
									            <c:out
									               value="${panchaytSetUp[1]}" escapeXml="true"></c:out>
									         </td>
									         <td width="22%" align="left">
									            <c:out
									               value="${panchaytSetUp[2]}" escapeXml="true"></c:out>
									         </td>
									         <td width="22%" align="left">
									            <c:if test="${fn:containsIgnoreCase(panchaytSetUp[3],'F')}">
									               <label>
									                  <spring:message code="Label.FULL" htmlEscape="true"></spring:message>
									               </label>
									            </c:if>
									            <c:if test="${fn:containsIgnoreCase(panchaytSetUp[3],'P')}">
									               <label>
									                  <spring:message code="Label.PART" htmlEscape="true"></spring:message>
									               </label>
									            </c:if>
									         </td>
									         <td width="22%" align="center">
									            <c:choose>
									               <c:when test="${panchaytSetUp[4] == 'true'}">
									                  <img src='<%=contextPath%>/images/right-icon5.gif' border="0" />
									               </c:when>
									               <c:otherwise>
									                  <c:out value=""></c:out>
									               </c:otherwise>
									            </c:choose>
									         </td>
									      </tr>
									   </c:forEach>
								   </c:if>
									
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
