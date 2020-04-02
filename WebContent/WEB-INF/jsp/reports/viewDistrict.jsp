
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
		$("[id^=searchBy]").change(function() {
			$('#' + $(this).attr('paramShow')).show();
			$('#' + $(this).attr('paramHide')).hide();
			$('#entityCode').val('');
			$('#entityName').val('');
			$('#ddSourceState').val('');
		});
		$( '#actionFetchDetails' ).click(function() {
	 		validationForm();
	 		$("#entitesForMsg").val($("#ddSourceState option:selected").text());
	 		
		});
		
		
	});
	
	var validationForm = function (){
		
	 	$("#ddSourceState" ).rules( "add", {
	 		customMandateState : true, messages: {customMandateState : "Please select a State."}
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
              <h3 class="box-title"><spring:message htmlEscape="true" code="Label.VIEWDIST"></spring:message></h3>
            </div><!-- /.box-header -->
                
                <!-- form start -->
                <form:form action="globalviewdistrict.do" class="form-horizontal" method="POST" id="genericReportingEntity" commandName="genericReportingEntity">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="globalviewdistrict.do"/>" />
						<form:hidden path="entitesForMessage" id="entitesForMsg"/>
							<!-- <div id="dialogBX" style="display: none;">
								<iframe id="myIframe" width="910" height="650"></iframe>
							</div> -->
					<c:choose>
					<c:when test="${showSearchHierarchy}">
					
					<div class="box-body" id="divCenterAligned">
						 <%-- <div class="box-header subheading">
							<h4><spring:message htmlEscape="true" code="Label.FILTEROPTFORVIEWDIST"></spring:message></h4>
						 </div>
		                    <div class="form-group">
		                    <label class="col-sm-4 control-label"></label>
		                    <div class="col-sm-6">
		                     	<form:radiobutton class="radio-inline" path="searchCriteriaType" id='searchByName' value="N" checked="checked" paramShow="displayNameCode" paramHide="displayHierarchy"/>
									<spring:message code="Label.SEARCHBYNAME"/>&nbsp;&nbsp;&nbsp;
								<form:radiobutton class="radio-inline" path="searchCriteriaType" id='searchByHierarchy' value="P" paramShow="displayHierarchy" paramHide="displayNameCode"/>
									<spring:message code="Label.SEARCHBYHIERARCHY"/>
		                    </div>
		                    </div>
		                    <h4><!-- Used header for blank head, please dont remove.  --></h4>
		                    
		                    
		                    <div id="displayNameCode" class="form-group ">
		                      <label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.DISTRICTCODE"></spring:message></label>
		                      <div class="col-sm-6">
		                      	<form:input id="entityCode" path="paramEntityCode" class="form-control" /><br>
		                     </div>
	                       
		                      <label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.DISTRICTNAMEINENGLISH"></spring:message></label>
		                      <div class="col-sm-6">
		                      	<form:input id="entityName" path="paramEntityName" class="form-control" /><br>
		                     </div>
	                       </div> --%>
	                       
	                       <div id="displayHierarchy"  class="form-group">
		                      <label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message><span class="mandatory">*</span></label>
		                      <div class="col-sm-6">
		                      	<form:select path="paramStateCode" class="form-control" id="ddSourceState" onchange="error_remove();">
									<form:option value="0">
													 All States
												</form:option>
									<form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
								</form:select>
		                     </div>
	                       </div>
	                       
	                       <div class="box-body">
		                    <div class="form-group">
<%-- 		  <label  class="col-sm-4 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.ENTERTEXTASSHOWNINTHISIMAGE" ></spring:message> <span class="mandatory">*</span></label>
 --%>		 
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
		                      		<button type="submit" class="btn btn-primary" id="actionFetchDetails"><i class="fa fa-undo"></i> <spring:message htmlEscape="true" code="Button.GETDATA"/></button>
		                            <button type="button" class="btn btn-danger " onclick="javascript:location.href='welcome.do'"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE"/></button>
		                        </div>
		                     </div>   
		                  </div><!-- /.box-footer -->
		                  </c:when>
		                  <c:otherwise>
		                  
		                  	<div class="box-body">
							  <h4>
							 	<c:choose>
									<c:when test="${fn:trim(genericReportingEntity.entitesForMessage) eq  'All States'}"> 
										<c:out value="Districts of All States of India" />
											
									 </c:when>
								  <c:otherwise>
								  <spring:message code="Label.dist.hierarchy.message" arguments="${genericReportingEntity.entitesForMessage}"></spring:message>	
								  </c:otherwise>
								</c:choose> 
							 </h4>
							  <div class="table-responsive ">
				                   <table class="table table-striped table-bordered dataTable" id="example" >
										<thead>
											<tr>
												<th ><spring:message code="Label.SNO"/></th>
												<th ><spring:message code="Label.DISTRICTCODE"/></th>
												<th ><spring:message code="Label.DISTRICTNAMEINENGLISH"/></th>
												<th ><spring:message code="Label.DISTRICTNAMEINLOCAL"/></th>
												<th> Hierarchy</th>
												<th ><spring:message code="Label.SHORTNAMEOFDISTRICT"/></th>
												<th ><spring:message code="Label.CENSUS2001"/></th>
												<th ><spring:message code="Label.CENSUSCODE2011"/></th>
												<th ><spring:message code="Label.PESA_STATUS" text="Pesa Status"/></th>
												<th ><spring:message htmlEscape="true" code="Label.VIEW.DETAIL"/></th>
												<th ><spring:message htmlEscape="true" code="Label.VIEW.History" text="View History"/></th>		
												<th ><spring:message htmlEscape="true" code="Label.VIEW.GOVERNMENTORDER"/></th>
												<th ><spring:message htmlEscape="true" code="Label.VIEW.MAP"/></th>
											</tr>
										</thead>
										
										<tbody>
										 	<c:forEach var="listDistrictDetails" varStatus="rowstatus" items="${SEARCH_DISTRICT_RESULTS_KEY}">
												<tr >
													<td width="5%"><c:out value="${rowstatus.count}"/></td>
													<td><c:out value="${listDistrictDetails.entityCode}"></c:out></td>
													<td align="left"><c:out value="${listDistrictDetails.entityNameEnglish}"></c:out></td>
													<td align="left"><c:out value="${listDistrictDetails.entityNameLocal}"></c:out></td>
													<td align="left"><c:out value="${listDistrictDetails.entityHierarchy}"></c:out></td>
													<td align="left"><c:out value="${listDistrictDetails.entityNameShort}"></c:out></td>
													<td align="left"><c:out value="${listDistrictDetails.census2001Code}"></c:out></td>
													<td align="left"><c:out value="${listDistrictDetails.census2011Code}"></c:out></td>
													<td align="left">
													   	<c:choose>
														 	<c:when test="${listDistrictDetails.isPesa eq 'P'}">
															 	<spring:message htmlEscape="true" code="Label.PESA_PARTIALLY_COVERED" text="Partially Covered"/>
														 	</c:when>
														  	<c:when test="${listDistrictDetails.isPesa eq 'F'}">
														  		<spring:message htmlEscape="true" code="Label.PESA_FULLY_COVERED" text="Fully Covered"/>
														 	</c:when>
														 	<c:otherwise>
														  		<spring:message htmlEscape="true" code="Label.PESA_NOT_COVERED" text="Not Covered"/>
														 	</c:otherwise>
														</c:choose>
													</td>
											     	<td width="5%" align="center"><a href="#" onclick="javascript:viewEntityDetailsInPopup('${listDistrictDetails.entityCode}', 'globalviewDistrictDetail.do', 'globaldistrictId');"><i class="fa fa-eye" aria-hidden="true"></i></a>
													</td>
													<td width="5%" align="center"><a href="#" onclick="javascript:viewEntityDetailsInPopup('${listDistrictDetails.entityCode}', 'viewDistrictHistoryReport.do', 'globaldistrictId');"><i class="fa fa-history" aria-hidden="true"></i></a>
													</td>
													<td width="5%" align="center">
														<c:if test="${not empty listDistrictDetails.fileTimestamp}">
														<a href="#" onclick="javascript:retrieveFile1('${listDistrictDetails.entityCode}', 'D');"><i class="fa fa-file-text-o" aria-hidden="true"></i></a>
													    </c:if>
												    </td>
											   		<td width="3%" align="center">
											   		 <!-- Merging issue revert beck 05/10/2017 -->
											   			<%-- <c:if test="${not empty listDistrictDetails.isMapUpload and  !listDistrictDetails.isMapUpload}"> --%>
											   			<%-- <a href="#" onclick="javascript:displayMap('${listDistrictDetails.census2011Code}',2,'L',null,null);" ><i class="fa fa-map-marker" aria-hidden="true"></i></a> --%>
											   			<a href="#" onclick="javascript:viewLandRegionGISMapInPopup(2,'${listDistrictDetails.entityCode}','L','D');"><i class="fa fa-map-marker" aria-hidden="true"></i></a>
											   			<%-- </c:if> --%>
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
							<button type="button"  class="btn btn-primary"  onclick="javascript:location.href='globalviewdistrictforcitizen.do?<csrf:token uri='globalviewdistrictforcitizen.do'/>'"><spring:message code="Button.BACK"/></button>
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
		
		
		<script type='text/javascript'>
		$(window).load(function () {
			var radioObj = $('input:radio[name="searchCriteriaType"]').filter('[value="<c:out value='${radioButton}'/>"]') ;
			$('#' + radioObj.attr('paramShow')).show();
			$('#' + radioObj.attr('paramHide')).hide();
		});
	</script>	

