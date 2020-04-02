
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="CommonReportingIncludeJS.jsp"%>
<%@include file="../homebody/commanInclude.jsp"%>
<script type="text/javascript" class="init">
//jquery pagination  
/* $(document).ready(function() {
	$('#example').dataTable({
	});
}); */

	$(document).ready(function() {
		$( '#actionFetchDetails' ).click(function() {
	 		validationForm();
	 		$("#entitesForMsg").val($("#ddSourceState option:selected").text());
	 		
		});
	});
	
	var validationForm = function (){
		$("#captchaAnswer" ).rules( "add", {
			
	 		  required : true, messages: {required : "<font color='red'>Please enter the text shown above.</font>"}
		});
	}
</script>


<section class="content">

 <!-- Main row -->
 <div class="row">
     <!-- main col -->
	<div class="container_report">
	<section class="col-lg-12 prebox">
     	<div class="box">
            <div class="box-header with-border">
              <h3 class="box-title"><spring:message code="Label.VIEWSTATE" htmlEscape="true"></spring:message></h3>
            </div><!-- /.box-header -->
                
                <!-- form start -->
                <form:form class="form-horizontal" action="globalViewStateRepport.do" method="POST" id="genericReportingEntity" commandName="genericReportingEntity">
				<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="globalViewStateRepport.do"/>" />
					<form:hidden path="entitesForMessage" id="entitesForMsg"/>
					<!-- <div id="dialogBX" style="display: none;">
						<iframe id="myIframe" width="910" height="650"></iframe>
					</div> -->
					<c:choose>
					<c:when test="${showSearchHierarchy}">
	                  <div class="box-body">
		                    <div class="form-group">
		 <%--  <label  class="col-sm-4 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.ENTERTEXTASSHOWNINTHISIMAGE" ></spring:message> <span class="mandatory">*</span></label> --%>
		   <label  class="col-sm-4 control-label" for="sel1"></label> 
		    <div class="col-sm-6">
		  	<img src="captchaImage" id="captchaImageId" alt="Captcha Image" border="0"/>
		  </div>
</div>
		   <div class="form-group">
		   <label for="captchaAnswer" class="col-sm-4 control-label"><spring:message code="captcha.message"	htmlEscape="true" /><span class="mandatory">*</span></label>
		  <div class="col-sm-6">
		  	<form:input path="captchaAnswer" autocomplete="off"  style="width:250px"  maxlength ="5" />
		  	 <a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>
		  <form:errors path="captchaAnswer" cssStyle="color:red"/>
		                     
								<c:if test="${not empty captchaFaliedMessage and not captchaFaliedMessage}">
									<br/><label><!-- Used Label, please dont remove. --></label>
									<span class="mandatory"><strong><spring:message code="captcha.errorMessage"/></strong></span>
								</c:if>
		                       </div>
	                    	</div>
	                    	        	
					  </div>
					 	<div class="box-footer">
		                     <div class="col-sm-offset-2 col-sm-10">
		                       <div class="pull-right">
		                      		<button type="submit" class="btn btn-primary" id="actionFetchDetails"><i class="fa fa-undo"></i> <spring:message htmlEscape="true" code="Button.GETDATA"/></button>
		                            <button type="button" class="btn btn-danger " onclick="javascript:location.href='welcome.do'"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE"/></button>
		                        </div>
		                     </div>   
		                  </div><!-- /.box-footer -->
					  </c:when>
					 	 <c:otherwise>
					  
							<div class="box-body">
							  <div class="box-header subheading">
							 	 <h4><spring:message htmlEscape="true" code="Label.VIEWSTATELIST"></spring:message></h4>
				              </div>
				                  <table><tr>
		                              <th><font color="red">*</font> Note : UT-Union Territory</th>
		                                  </tr>
		
		                                  </table>
				                  <div class="table-responsive ">
				                   <table class="table table-striped table-bordered dataTable" id="example"  class="data_grid">
										<thead>
											<tr>
												<th ><spring:message code="Label.SNO"/></th>
												<th ><spring:message code="Label.STATECODE"/></th>
												<th ><spring:message code="Label.STATENAMEINENGLISH"/></th>
												<th ><spring:message code="Label.STATENAMEINLOCAL"/></th>
												 <th ><spring:message code="Label.STATEORUT"/></th> 
												<th ><spring:message code="Label.CENSUS2001"/></th>
												<th ><spring:message code="Label.CENSUSCODE2011"/></th>
												<th ><spring:message htmlEscape="true" code="Label.VIEW.DETAIL"/></th>
												<th ><spring:message htmlEscape="true" code="Label.VIEW.History" text="View History"/></th>		
												<th ><spring:message htmlEscape="true" code="Label.VIEW.GOVERNMENTORDER"/></th>
												<th ><spring:message htmlEscape="true" code="Label.VIEW.MAP"/></th>
											</tr>
										</thead>
										
										<tbody>
										 	<c:forEach var="listStateDetails" varStatus="rowstatus" items="${SEARCH_STATE_RESULTS_KEY}">
												<tr >
													<td width="6%"><c:out value="${rowstatus.count}"/></td>
													<td><c:out value="${listStateDetails.entityCode}"></c:out></td>
													<td align="left"><c:out value="${listStateDetails.entityNameEnglish}"></c:out></td>
													<td align="left"><c:out value="${listStateDetails.entityNameLocal}"></c:out></td>
													
                                                     <c:choose>
                                                      <c:when test = "${listStateDetails.entityNameShort eq 'S'}">
                                                       <td align="left"><c:out value="State"></c:out></td> 
                                                       </c:when>
                                                       <c:otherwise>
                                                       <td align="left"><c:out value="UT"></c:out></td> 
                                                       </c:otherwise>
                                                       </c:choose>
                                                                                                         
													<td align="left"><c:out value="${listStateDetails.census2001Code}"></c:out></td>
													<td align="left"><c:out value="${listStateDetails.census2011Code}"></c:out></td>
													<td width="8%" align="center"><a href="#" onclick="javascript:viewEntityDetailsInPopup('${listStateDetails.entityCode}', 'globalviewStateDetail.do', 'globalstateId');"><i class="fa fa-eye" aria-hidden="true"></i>
												    </a>
													</td>
													<td width="8%" align="center"><a href="#" onclick="javascript:viewEntityDetailsInPopup('${listStateDetails.entityCode}', 'viewStateHistoryReport.do', 'globalstateId');"><i class="fa fa-history" aria-hidden="true"></i></a>
														
													</td>
													<td width="10%" align="center">
														<c:if test="${not empty listStateDetails.fileTimestamp}">
														  <a href="#"  onclick="javascript:retrieveFile1('${listStateDetails.entityCode}', 'S');"><i class="fa fa-file-text-o" aria-hidden="true"></i></a>
													    </c:if>
												    </td>
											   		<td width="3%" align="center">
												   		<a href="#" onclick="javascript:viewLandRegionGISMapInPopup(1,'${listStateDetails.entityCode}','L','S');"><i class="fa fa-map-marker" aria-hidden="true"></i></a>
												   		
											   		</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
				              	</div>
				            </div>
				
				<!-- Button for Page after POST method @Ashish 18 aUG -->	
					  
				 <div class="box-footer" id="showbutton">
                    <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
                            <button type="button"  class="btn btn-primary"  onclick="javascript:location.href='globalviewstateforcitizen.do?<csrf:token uri='globalviewstateforcitizen.do'/>'"><spring:message code="Button.BACK"/></button>
							<button type="button" name="Submit3" id="btn1" class="btn btn-danger" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
					   </div>
					 </div>
				  </div>
					  
					 	 </c:otherwise>
					  </c:choose>
				  </form:form>
			</div>
			 <div class="modal fade" id="customAlert" role="dialog">
			    <div class="modal-dialog">
			    
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h4 class="modal-title">Alert Massage</h4>
			        </div>
			        <div class="modal-body" id="customAlertbody">
			         
			        </div>
			        <div class="modal-footer">
			          <button type="button" class="btn btn-default" data-dismiss="modal">Ok</button>
			        </div>
			      </div>
			      
			    </div>
			  </div>	
		</section>
		</div>
	</div>
</section>





	