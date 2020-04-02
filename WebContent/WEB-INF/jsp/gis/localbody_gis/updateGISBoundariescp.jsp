<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="../../common/dwr.jsp"%>
<%@include file="updateGISBoundariesJs.jsp"%>


</head>
<body>
	<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                    <form:form action="updateGISBoundaries.htm" method="POST" commandName="criteriaLocalBodies"  id="formCDraftedLB" class="form-horizontal">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="updateGISBoundaries.htm"/>" />
						<form:hidden path="localBodyCode" id="paramLBCode"/>
						<form:hidden path="isGISCoverage" id="paramGisCoverage"/>
						<form:hidden path="localBodyCreationType" value="P" />
						<!-- <div id="dialogBX" style="display: none;">
						 <iframe id="myIframe" width="910" height="650"></iframe>
					   </div> -->
                            <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message htmlEscape="true" code="Update GIS Boundaries" /></h3>
                                </div>
                                 
                
                  <c:choose>
					<c:when test="${!searchResult}">
						<div id="divCenterAligned" class="box-body" >
							<div class="box-header subheading">
									<h4>Search Criteria</h4>
								</div>		
											
										
										<div class="form-group">
											<label class="col-sm-3 control-label">
												<spring:message code='Label.SELECTLOCALBODYTYPE' htmlEscape='true'/>
												<span class="mandate">*</span>
											</label>
											<div class="col-sm-6">
											<form:select path="paramLocalBodyTypeCode" id="localBodyType" class="form-control">
												<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT" ></spring:message></form:option>
												
													 <c:forEach items="${localBodyTypeList}" var="objLBTypes">\
													 <c:set var="optValue" value="${objLBTypes.localBodyTypeCode}_${objLBTypes.tierSetupCode}_${objLBTypes.parentTierSetupCode}_${objLBTypes.lbLevel}" />
													 
													<c:choose>
													<c:when test="${objLBTypes.localBodyTypeCode eq 3}">
													<form:option selected="true" value="${optValue}">
															<c:out value="${objLBTypes.name}" escapeXml="true"></c:out>
														</form:option>
													</c:when>
													<c:otherwise>
													<form:option value="${optValue}">
															<c:out value="${objLBTypes.name}" escapeXml="true"></c:out>
														</form:option>
													</c:otherwise>
													</c:choose>
														
													</c:forEach> 
												</form:select>
											<span class="mandatory" id="errorLocalBodyType"></span>
											</div>
										</div>
										
										<div id="divCreateDynamicHierarchy">
											<!-- This Div used to generate Hierarchy -->
										</div>
										
										 <div class="box-footer">
									           <div class="col-sm-offset-2 col-sm-10">
									                 <div class="pull-right">
														<input class="btn btn-primary " type="button" id="actionFetchLBDetails" value="Fetch Local Bodies" />
											<input class="btn btn-danger " type="button" id="actionSearchClose" value="Close" />	
										 </div>
										 </div>
										 </div>
										
										
								
							
						</div>
					</c:when>
					<c:otherwise>
						<!-- Block for Drafted Local Government Body Details in Tabular Format. -->
					<div class="box-body">
						<div class="box-header subheading">
							<h4>Local Government Body Details</h4>
							</div>
								
									<table id="example" class="table table-bordered table-hover" >
										<thead>
											<tr>
												
												<th><spring:message code="Label.SNO" htmlEscape="true"></spring:message></th>
												<th><spring:message code="Label.LOCALBODYCODE" htmlEscape="true"></spring:message></th>
												<th><spring:message code="Label.LOCALBODYNAMEINENG" htmlEscape="true"></spring:message></th>
												
												
												<th>Update GIS Boundaries</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="objects" items="${publishedLocalBodies}" varStatus="counter">
												<c:if test="${not objects.isDrafted}">
												<tr id="trdetials">
													
													<td align="center"><c:out value="${counter.count}" escapeXml="true"></c:out></td>
													<td align="center" ><c:out value="${objects.localBodyCode}" escapeXml="true"></c:out></td>
													<td style="word-break:break-all"><c:out value="${objects.localBodyNameEnglish}" escapeXml="true"></c:out></td>
													<td align="center">
													<a paramGPCode="${objects.localBodyCode}" paramLBNameEng="${objects.localBodyNameEnglish}" onclick="callGISMapView('${objects.localBodyCode}','${objects.localBodyNameEnglish}',false,false)"><i class="fa fa-map-marker" aria-hidden="true"></i>
													</a>
														
															
														
													</td>
												</tr>	
												</c:if>
											</c:forEach>
										</tbody>
									</table>
									
								
								
				 <div class="box-footer">
                     
								<input class="bttn" type="button" class="btn btn-danger pull-right" id="actionClose" value="Close" />	
					  
								</div>	
						
					</div>
					<!-- Block for Drafted Local Government Body Details in Tabular Format Ends Here. -->
					<br/>
					</c:otherwise>
					</c:choose>
                 
                 
                 
						
                      
						 
                          
                 </div> 
              
                      
    
     </div>   
             
    </form:form>     
     <div class="modal fade" id="dialogBX" tabindex="-1" role="dialog" >
			<div class="modal-dialog" style="width:950px;">
					<div class="modal-content">
		  				<div class="modal-header">
		   				   <button type="button" class="close" data-dismiss="modal">&times;</button>
		    			  	<h4 class="modal-title" id="dialogBXTitle"></h4>
		    			  	
		  				</div>
		  				<div class="modal-body" id="dialogBXbody">
		        			<iframe id="myIframe" width="910" height="650"></iframe>
		        			
		     			 
						</div>
						
			</div>
		</div>
	</div> 
   </section>
   
 </div>
 </section>
 <script src="/LGD/JavaScriptServlet"></script>
</body>
</html>