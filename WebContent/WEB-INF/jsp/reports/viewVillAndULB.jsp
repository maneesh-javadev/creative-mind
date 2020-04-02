<%@include file="../common/taglib_includes.jsp"%>
<%@include file="CommonReportingIncludeJS.jsp"%>
<%@include file="../homebody/commanInclude.jsp"%>
<script type="text/javascript" class="init">

$(document).ready(function() {
	$('#example').dataTable({
	});
});
	$(document).ready(function() {
	    $("[id^=searchBy]").change(function() {
			$('#' + $(this).attr('paramShow')).show();
			$('#' + $(this).attr('paramHide')).hide();
			$('#entityCode').val('');
			$('#entityName').val('');
			$('#ddSourceState').val('');
			$('#ddSourceDistrict option[value != ""]').remove();
			$('#ddSourceSubDistrict option[value != ""]').remove();
		});
		$("#ddSourceState").change(function() {
		 	$('#ddSourceDistrict option[value != ""]').remove();
			$('#ddSourceSubDistrict option[value != ""]').remove();
			if(isEmptyObject($(this).val()))return false;
			buildDistrict($(this).val());
		});
		$("#ddSourceDistrict").change(function() {
			$('#ddSourceSubDistrict option[value != ""]').remove();
			if(isEmptyObject($(this).val()))return false;
			buildBlock($(this).val());
		});
		
		$( '#actionFetchDetails' ).click(function() {
			validationForm();
	 		var stateName = $("#ddSourceState option:selected").text();
	 		var districtName = $("#ddSourceDistrict option:selected").text();
	 		var blockName = $("#ddSourceBlock option:selected").text();
	 		$("#entitesForMsg").val( blockName + "," + districtName + "," + stateName);
		});
	});
	
	 var validationForm = function (){
	
	 	$("#ddSourceState" ).rules( "add", {
			  customMandateState : true, messages: {customMandateState : "Please select a State."}
		});
	 	$("#ddSourceDistrict" ).rules( "add", {
			  customMandateDist : true, messages: {customMandateDist : "Please select a District."}
		});
	 	$("#ddSourceBlock" ).rules( "add", {
	 		customMandateBlock : true, messages: {customMandateBlock : "Please select a Block."}
		});
	 	$("#captchaAnswer" ).rules( "add", {
	 		  required : true, messages: {required : "Please enter the text shown above."}
		});
	}
</script>

<section class="content">
<!-- Main row -->
 <div class="row">
     <!-- main col -->
	<div class="container">
	<section class="col-lg-12 prebox">
     	<div class="box">
            <div class="box-header with-border">
              <h3 class="box-title"><spring:message htmlEscape="true" code="Label.VIEWBLOCKWISEVILLAGESANDULB"></spring:message></h3>
            </div><!-- /.box-header -->
                
                <!-- form start -->
                <form:form action="globalviewblockwiseVillageandUlbforcitizen.do" class="form-horizontal" method="POST" id="genericReportingEntity" commandName="genericReportingEntity">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="globalviewblockwiseVillageandUlbforcitizen.do"/>" />
					<form:hidden path="entitesForMessage" id="entitesForMsg"/>
					<div id="dialogBX" style="display: none;">
						<iframe id="myIframe" width="910" height="650"></iframe>
					</div>
					<c:choose>
					<c:when test="${showSearchHierarchy}">
	                  <div class="box-body" id="divCenterAligned">
	                  	<div class="box-header subheading"><h4><spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message></h4></div>
		                   <div class="form-group">
		                      <label for="ddSourceState" class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message><span class="mandatory">*</span>
		                      </label>
		                      <div class="col-sm-6">
		                      	<form:select path="paramStateCode" class="form-control" id="ddSourceState" onchange="error_remove();">
									<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
									<form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
								</form:select><br>
		                      </div>
	                       </div>
	                        <div class="form-group">
		                     	<label for="ddSourceDistrict" class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message><span class="mandatory">*</span>
		                     	</label>
		                      <div class="col-sm-6">
		                        <form:select path="paramDistrictCode" class="form-control" id="ddSourceDistrict">
									<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
								</form:select> <br>
		                       </div>
		                    </div>
		                    <div class="form-group">
		                      <label for="ddSourceBlock" class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELBLOCK"></spring:message><span class="mandatory">*</span>
		                      </label>
		                      <div class="col-sm-6">
		                      	<form:select path="paramBlockCode" class="form-control" id="ddSourceBlock">
									<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
								</form:select><br>
		                       </div>
	                    	</div>
	                    	
	                    	    
	                      <div class="box-body">
		                    <div class="form-group">
		  <label  class="col-sm-4 control-label" for="sel1"></label>
		  <div class="col-sm-6">
		  	<img src="captchaImage" id="captchaImageId" alt="Captcha Image" border="0"/>
		  </div>
</div>
		                   <div class="form-group">
		   <label for="captchaAnswer" class="col-sm-4 control-label"><spring:message code="captcha.message"	htmlEscape="true" /><span class="mandatory">*</span></label>
		  <div class="col-sm-6">
		  	<form:input path="captchaAnswer" autocomplete="off"  style="width:250px" maxlength="5"/>
		  	 <a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>
		  <form:errors path="captchaAnswer" cssStyle="color:red"/>
		                     
								<c:if test="${not empty captchaFaliedMessage and not captchaFaliedMessage}">
									<br/><label><!-- Used Label, please dont remove. --></label>
									<span class="mandatory"><strong><spring:message code="captcha.errorMessage"/></strong></span>
								</c:if>
		                       </div>
	                    	</div>
	                    	
	                    	
	                    	
	                    	
	                    	
					  </div>
	                   </div>
					 	<div class="box-footer">
		                     <div class="col-sm-offset-2 col-sm-10">
		                       <div class="pull-right">
		                      		<button type="submit" class="btn btn-success " id="actionFetchDetails"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true" code="Button.GETDATA"/></button>
		                            <button type="button" class="btn btn-danger " onclick="javascript:location.href='welcome.do'"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE"/></button>
		                        </div>
		                     </div>   
		                  </div><!-- /.box-footer -->
					  </c:when>
					 	 <c:otherwise>
					  
							<div class="box-body">
							 	 <div class="box-header subheading">
									 <h4><spring:message code="Label.blockwise.village.and.ulb.hierarchy.message" text="Block Wise Villages  of  {0} Block ( {1} - {2} )" arguments="${genericReportingEntity.entitesForMessage}" argumentSeparator=","></spring:message></h4>
							     </div>
				                   <div class="table-responsive ">
					                   <table class="table table-striped table-bordered dataTable" id="example">
											<thead>
												<tr>
													<th><spring:message code="Label.SNO"/></th>
															<%-- <th><spring:message code="Label.ENTITYTYPE"/></th> --%>
															<th><spring:message code="Label.VILLAGECODE"/></th>
															<th><spring:message code="Label.VILLAGENAMEINENGLISH"/></th>
															<th><spring:message code="Label.VILLAGENAMEINLOCAL"/></th>
															<th><spring:message code="Label.VIEW"/></th>
															<th><spring:message code="Label.VIEW.GOVERNMENTORDER"/></th>
												</tr>
											</thead>
											
											<tbody>
											 	<c:forEach var="obj" varStatus="rowstatus" items="${blockWiseEntityList}">
															<tr >
																<td width="6%"><c:out value="${rowstatus.count}"/></td>
																<%-- <td align="left"><c:out value="${obj.entityType}"></c:out></td> --%>
																<td align="left"><c:out value="${obj.entityCode}"></c:out></td>
																<td align="left"><c:out value="${obj.entityNameEnglish}"></c:out></td>
																<td align="left"><c:out value="${obj.entityNameLocal}"></c:out></td>
																<td width="8%" align="center"><a href="#" onclick="javascript:viewEntityDetailsInPopup('${obj.entityCode}', 
														     			 <c:choose>
														     			 			<c:when test="${obj.entityType eq 'Village'}">'globalviewVillageDetail.do','globalvillageId' </c:when>
														     			 			<c:otherwise>'globalviewLocalBodyDetail.do', 'id'</c:otherwise>
														     			 </c:choose>
				
														     			 );"><i class="fa fa-eye" aria-hidden="true"></i></a>
														     		
																</td>
																<td width="10%" align="center">
																	<c:if test="${not empty obj.fileTimestamp}">
																	<a href="#"  onclick="javascript:retrieveFile1('${obj.entityCode}',
																			  <c:choose>
														     			 			<c:when test="${obj.entityType eq 'Village'}">'V' </c:when>
														     			 			<c:otherwise>'G'</c:otherwise>
														     			 	</c:choose>	
																			 );" ><i class="fa fa-file-text-o" aria-hidden="true"></i></a>
																			
																    </c:if>
															    </td>	
															</tr>
														</c:forEach>
											</tbody>
										</table>
				             		</div>
				            </div>
					  
							<div class="box-footer" id="showbutton">
			                    <div class="col-sm-offset-2 col-sm-10">
			                       <div class="pull-right">
										<button type="button" class="btn btn-primary" onclick="javascript:location.href='globalviewblockwiseVillageandUlbforcitizen.do?<csrf:token uri='globalviewblockwiseVillageandUlbforcitizen'/>'"> <spring:message code="Button.BACK"/></button>
								   
										<button type="button" name="Submit3" id="btn1" class="btn btn-danger" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
								   </div>
								 </div>
							</div>
					  
					 	 </c:otherwise>
					  </c:choose>
				  </form:form>
			</div>
		</section>
		</div>
	</div>
</section>
 


