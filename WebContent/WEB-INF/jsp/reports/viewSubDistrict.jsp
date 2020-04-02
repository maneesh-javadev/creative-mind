<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="CommonReportingIncludeJS.jsp"%>
<%@include file="../homebody/commanInclude.jsp"%>
<script type="text/javascript" class="init">
$(document).ready(function() {
	$("[id^=searchBy]").change(function() {
		$('#' + $(this).attr('paramShow')).show();
		$('#' + $(this).attr('paramHide')).hide();
		$('#entityCode').val('');
		$('#entityName').val('');
		$('#ddSourceState').val('');
		$('#ddSourceDistrict option[value != ""]').remove();
	});
	$( '#actionFetchDetails' ).click(function() {
 		validationForm();
 		var stateName = $("#ddSourceState option:selected").text();
 		var districtName = $("#ddSourceDistrict option:selected").text();
 		$("#entitesForMsg").val( districtName + "," + stateName);
	});
	$("#ddSourceState").change(function() {
		$('#ddSourceDistrict option[value != ""]').remove();
		if(isEmptyObject($(this).val()))return false;
		buildDistrict($(this).val());
	});
});

var validationForm = function (){
	$("#entityCode" ).rules( "add", {
		  customMandate : true, messages: {customMandate : "Please Enter Sub-District Code."}
	});
	$("#entityName" ).rules( "add", {
		  customMandate : true, messages: {customMandate : "Please Enter Sub-District Name."}
	});
 	$("#ddSourceState" ).rules( "add", {
		  customMandateState : true, messages: {customMandateState : "Please select a State."}
	});
 /* 	$("#ddSourceDistrict" ).rules( "add", {
		  customMandateDist : true, messages: {customMandateDist : "Please select a District."}
	}); */
 	$("#captchaAnswer" ).rules( "add", {
 		  required : true, messages: {required : "Please enter the text shown above."}
	});
}
</script>
</head>
<body>

<section class="content">

 <!-- Main row -->
 <div class="row">
     <!-- main col -->
	<div class="container">
	<section class="col-lg-12 prebox">
     	<div class="box">
            <div class="box-header with-border">
              <h3 class="box-title"><spring:message htmlEscape="true" code="Label.VIEWSUBDIST"></spring:message></h3>
            </div><!-- /.box-header -->
                
                <!-- form start -->
                <form:form action="globalviewsubdistrict.do" class="form-horizontal" method="POST" id="genericReportingEntity" commandName="genericReportingEntity">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="globalviewsubdistrict.do"/>" />
						<form:hidden path="entitesForMessage" id="entitesForMsg"/>
							<div id="dialogBX" style="display: none;">
								<iframe id="myIframe" width="910" height="650"></iframe>
							</div>
					<c:choose>
					<c:when test="${showSearchHierarchy}">
					
					<div class="box-body" id="divCenterAligned">
						 <div class="box-header subheading">
							<h4><spring:message htmlEscape="true" code="Label.FILTEROPTFORVIEWSUBDIST"></spring:message></h4>
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
		                      <label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SUBDISTRICTCODE"></spring:message><span class="mandatory">*</span></label>
		                      <div class="col-sm-6">
		                      	<form:input id="entityCode" path="paramEntityCode" class="form-control" /><br>
		                     </div>
		                     
		                     <label class="col-sm-4 control-label"></label>
		                     <div class="col-sm-6">
		                      	<label style="padding-left: 40%;"><c:out value="OR" /></label><br>
		                     </div>
		                   
	                       
		                      <label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SUBDISTRICTNAMEENGLISH"></spring:message><span class="mandatory">*</span></label>
		                      <div class="col-sm-6">
		                      	<form:input id="entityName" path="paramEntityName" class="form-control" /><br>
		                     </div>
	                       </div>
	                       
	                       <div id="displayHierarchy" style="display: none;" class="form-group">
		                      <label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message><span class="mandatory">*</span></label>
		                      <div class="col-sm-6">
		                      	<form:select path="paramStateCode" class="form-control" id="ddSourceState" onchange="error_remove();">
									<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
									<form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
								</form:select><br>
		                     </div>
		                     
		                      <label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message><span class="mandatory">*</span></label>
		                      <div class="col-sm-6">
		                      	<form:select path="paramDistrictCode" class="form-control" id="ddSourceDistrict">
									<form:option value="">All</form:option>
								</form:select>
		                     </div>
		                   </div>
	                       
	                        <div class="box-body">
		                    <div class="form-group">
		  <%-- <label  class="col-sm-4 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.ENTERTEXTASSHOWNINTHISIMAGE" ></spring:message> <span class="mandatory">*</span></label> --%>
		 
		    <label  class="col-sm-4 control-label" for="sel1"></label>
		  <div class="col-sm-6">
		  	<img src="captchaImage" id="captchaImageId" alt="Captcha Image" border="0"/>
		  </div>
</div>
		                   <div class="form-group">
		   <label for="captchaAnswer" class="col-sm-4 control-label"><spring:message code="captcha.message"	htmlEscape="true" /><span class="mandatory">*</span></label>
		  <div class="col-sm-6">
		  	<form:input path="captchaAnswer" autocomplete="off"  style="width:250px" maxlength="5" />
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
									<c:when test="${radioButton eq 'P'}">
										<spring:message code="Label.subdist.hierarchy.message" arguments="${genericReportingEntity.entitesForMessage}" argumentSeparator=","></spring:message>		
									</c:when>
								  <c:otherwise>Sub-District Details</c:otherwise>
								</c:choose>
							 </h4>
							    <div class="table-responsive ">
				                   <table class="table table-striped table-bordered dataTable" id="example" >
										<thead>
											<tr>
												<th><spring:message code="Label.SNO"></spring:message></td>
												<th><spring:message code="Label.SUBDISTRICTCODE"></spring:message></th>
												<th><spring:message code="Label.SUBDISTRICTNAMEENGLISH"></spring:message></th>
												<th><spring:message code="Label.SUBDISTRICTNAMELOCAL"></spring:message></th>
												<th> Hierarchy</th>
												<th><spring:message code="Label.CENSUS2001"></spring:message></th>
												<th><spring:message code="Label.CENSUSCODE2011"></spring:message></th>
												<th><spring:message code="Label.PESA_STATUS" text="Pesa Status"></spring:message></th>
												<th><spring:message code="Label.VIEW.DETAIL"></spring:message></th>
												<th><spring:message code="Label.VIEW.History" text="View History"></spring:message></th>		
												<th><spring:message code="Label.VIEW.GOVERNMENTORDER"></spring:message></th>
												<th><spring:message code="Label.VIEW.MAP"></spring:message></th>
											</tr>
										</thead>
										
										<tbody>
										 	<c:forEach var="listSubDistrictDetails" varStatus="rowstatus" items="${SEARCH_SUB_DISTRICT_RESULTS_KEY}">
															<tr >
																<td width="5%"><c:out value="${rowstatus.count}"/></td>
																<td><c:out value="${listSubDistrictDetails.entityCode}"></c:out></td>
																<td align="left"><c:out value="${listSubDistrictDetails.entityNameEnglish}"></c:out></td>
																<td align="left"><c:out value="${listSubDistrictDetails.entityNameLocal}"></c:out></td>
																<td align="left"><c:out value="${listSubDistrictDetails.entityHierarchy}"></c:out></td>
																<td align="left"><c:out value="${listSubDistrictDetails.census2001Code}"></c:out></td>
																<td align="left"><c:out value="${listSubDistrictDetails.census2011Code}"></c:out></td>
																<td align="left">
															   		<c:choose>
																 		<c:when test="${listSubDistrictDetails.isPesa eq 'P'}">
																	 		<spring:message htmlEscape="true" code="Label.PESA_PARTIALLY_COVERED" text="Partially Covered"/>
																 		</c:when>
																  		<c:when test="${listSubDistrictDetails.isPesa eq 'F'}">
																  			<spring:message htmlEscape="true" code="Label.PESA_FULLY_COVERED" text="Fully Covered"/>
																 		</c:when>
																 		<c:otherwise>
																  			<spring:message htmlEscape="true" code="Label.PESA_NOT_COVERED" text="Not Covered"/>
																 		</c:otherwise>
																	</c:choose>
																</td>
													     		<td width="8%" align="center"><a href="#"  onclick="javascript:viewEntityDetailsInPopup('${listSubDistrictDetails.entityCode}', 'globalviewSubDistrictDetail.do', 'globalsubdistrictId');"><i class="fa fa-eye" aria-hidden="true"></i></a>
																</td>
																<td width="8%" align="center"><a href="#" onclick="javascript:viewEntityDetailsInPopup('${listSubDistrictDetails.entityCode}', 'viewSubDistrictHistoryReport.do', 'globalsubdistrictId');"><i class="fa fa-history" aria-hidden="true"></i></a>
																</td>
																<td width="10%" align="center">
																	<c:if test="${not empty listSubDistrictDetails.fileTimestamp}">
																	<a href="#" onclick="javascript:retrieveFile1('${listSubDistrictDetails.entityCode}', 'T');"  ><i class="fa fa-file-text-o" aria-hidden="true"></i></a>
															    	</c:if>
														    	</td>
													   			<td width="3%" align="center">
													   			<fmt:parseNumber var="census2011CodeInt" value="${listSubDistrictDetails.census2011Code}" integerOnly="true"/>
													   			<c:if test="${census2011CodeInt >0}">
													   			<a href="#" onclick="javascript:viewLandRegionGISMapInPopup(3,'${listSubDistrictDetails.entityCode}','L','T');"><i class="fa fa-map-marker" aria-hidden="true"></i></a>
													   			</c:if>
													   			 <!-- Merging issue revert beck 05/10/2017 -->
																	<%-- <c:if test="${not empty listSubDistrictDetails.isMapUpload and  !listSubDistrictDetails.isMapUpload}"> --%>
																	<%-- <a href="#" onclick="javascript:displayMap('${listSubDistrictDetails.census2011Code}',3,'L',null,null);" ><i class="fa fa-map-marker" aria-hidden="true"></i></a>
																	 --%>
													   			<%-- 	</c:if> --%>
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
							<button type="button" class="btn btn-primary" onclick="javascript:location.href='globalviewsubdistrictforcitizen.do?<csrf:token uri='globalviewsubdistrictforcitizen.do'/>'"><spring:message code="Button.BACK"/></button>
					   
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
		if(isParseJson('<c:out value="${not empty captchaFaliedMessage and not captchaFaliedMessage}"/>') &&
		   isParseJson('<c:out value="${not empty genericReportingEntity.paramStateCode}"/>')){
			buildDistrict('<c:out value="${genericReportingEntity.paramStateCode}"/>');
			setTimeout(function(){
				$("#ddSourceDistrict option[value='<c:out value="${genericReportingEntity.paramDistrictCode}"/>']").attr("selected", "selected");	
			}, 200);	
		}
	});
</script>		
</body>
</html>