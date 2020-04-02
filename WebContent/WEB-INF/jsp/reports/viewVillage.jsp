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
			buildSubDistrict($(this).val());
		});
		$( '#actionFetchDetails' ).click(function() {
			validationForm();
	 		var stateName = $("#ddSourceState option:selected").text();
	 		var districtName = $("#ddSourceDistrict option:selected").text();
	 		var subDistrictName = $("#ddSourceSubDistrict option:selected").text();
	 		$("#entitesForMsg").val( subDistrictName + "," + districtName + "," + stateName);
		});
	});
	
	var validationForm = function (){
		$("#entityCode" ).rules( "add", {
			  customMandate : true, messages: {customMandate : "Please Enter Village Code."}
		});
		$("#entityName" ).rules( "add", {
			  customMandate : true, messages: {customMandate : "Please Enter Village Name."}
		});
	 	$("#ddSourceState" ).rules( "add", {
			  customMandateState : true, messages: {customMandateState : "Please select a State."}
		});
	 	$("#ddSourceDistrict" ).rules( "add", {
			  customMandateDist : true, messages: {customMandateDist : "Please select a District."}
		});
	 /* 	$("#ddSourceSubDistrict" ).rules( "add", {
	 		customMandateSubDist : true, messages: {customMandateSubDist : "Please select a Sub-District."}
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
              <h3 class="box-title"><spring:message htmlEscape="true" code="Label.VIEWVILLAGE"></spring:message></h3>
            </div><!-- /.box-header -->
                
                <!-- form start -->
                <form:form action="globalviewvillage.do" class="form-horizontal" method="POST" id="genericReportingEntity" commandName="genericReportingEntity">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="globalviewvillage.do"/>" />
						<form:hidden path="entitesForMessage" id="entitesForMsg"/>
							<div id="dialogBX" style="display: none;">
								<iframe id="myIframe" width="910" height="650"></iframe>
							</div>
					<c:choose>
					<c:when test="${showSearchHierarchy}">
					
					<div class="box-body" id="divCenterAligned">
						<div class="box-header subheading">
							<h4><spring:message htmlEscape="true" code="Label.FILTEROPTFORVIEWVILL"></spring:message></h4>
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
		                      <label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.VILLAGECODE"></spring:message><span class="mandatory">*</span></label>
		                      <div class="col-sm-6">
		                      	<form:input id="entityCode" path="paramEntityCode" class="form-control" /><br>
		                     </div>
	                       
	                          <label class="col-sm-4 control-label"></label>
		                     <div class="col-sm-6">
		                      	<label style="padding-left: 40%;"><c:out value="OR" /></label><br>
		                     </div>
		                   
	                       
		                      <label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.VILLAGENAMEINENGLISH"></spring:message><span class="mandatory">*</span></label>
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
									<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
								</form:select><br>
		                     </div>
		                     
		                     <label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELECTSUBDISTRICT"></spring:message><span class="mandatory">*</span></label>
		                      <div class="col-sm-6">
		                      	<form:select path="paramSubDistrictCode" class="form-control" id="ddSourceSubDistrict">
									<form:option value="">All</form:option>
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
									<c:when test="${radioButton eq 'P'}">
										<spring:message code="Label.village.hierarchy.message" arguments="${genericReportingEntity.entitesForMessage}" argumentSeparator=","></spring:message>		
									</c:when>
									<c:otherwise>Village Details</c:otherwise>
								</c:choose>
							 </h4>
							  <div class="table-responsive ">
							    <table class="table table-striped table-bordered dataTable" id="example" >
										<thead>
											<tr>
												<th ><spring:message code="Label.SNO"/></th>
												<th ><spring:message code="Label.VILLAGECODE"/></th>
												<%-- <th ><spring:message code="Label.VILLAGEVERSION"/></th>
												<th ><spring:message code="label.minor.version"/></th> --%>
												<th ><spring:message code="Label.VILLAGENAMEINENGLISH"/></th>
												<th ><spring:message code="Label.VILLAGENAMEINLOCAL"/></th>
												<th> Hierarchy</th>
												<th ><spring:message code="Label.CENSUS2001"/></th>
												<th ><spring:message code="Label.CENSUSCODE2011"/></th>
												<th ><spring:message code="label.villagestatus"/></th>
												 <th ><spring:message code="Label.PESA_STATUS" text="Pesa Status"/></th>
												<th ><spring:message htmlEscape="true" code="Label.VIEW.DETAIL"/></th>
												<th ><spring:message htmlEscape="true" code="Label.VIEW.History" text="View History"/></th>		
												<th ><spring:message htmlEscape="true" code="Label.VIEW.GOVERNMENTORDER"/></th>
												<th ><spring:message htmlEscape="true" code="Label.VIEW.MAP"/></th> 
											</tr>
										</thead>
										
										 <tbody>
									 	
												<tr >
												<c:forEach var="listVillageDetails" varStatus="rowstatus" items="${village_status_details}">
												
												<%-- <c:if test="${not empty village_status_details}"> --%>
										               <c:choose>
                                                      <c:when test="${not empty village_status_details}">
												     <tr>
												     <td width="5%"><c:out value="${rowstatus.count}"></c:out></td>
											         <td align="left"><c:out value="${listVillageDetails[0]}"></c:out></td>
											       <%--   <td align="left"><c:out value="${listVillageDetails[1]}"></c:out></td>
											         <td align="left"><c:out value="${listVillageDetails[2]}"></c:out></td> --%>
													 <td align="left"><c:out value="${listVillageDetails[3]}"></c:out></td>
													 <td align="left"><c:out value="${listVillageDetails[4]}"></c:out></td>
													  <td align="left"><c:out value="${listVillageDetails[5]}"></c:out></td>
													 <td align="left"><c:out value="${listVillageDetails[6]}"></c:out></td>
													 <td align="left"><c:out value="${listVillageDetails[7]}"></c:out></td>
													 <td align="left"><c:out value="${listVillageDetails[8]}"></c:out></td>
													 <td align="left"><c:out value="${listVillageDetails[9]}"></c:out></td>
										              
													
											     	<td width="8%" align="center"><a href="#" onclick="javascript:viewEntityDetailsInPopup('${listVillageDetails[0]}', 'globalviewVillageDetail.do', 'globalvillageId');"><i class="fa fa-eye" aria-hidden="true"></i></a>
											     	</td>
											     	
													<td width="8%" align="center"><a href="#" onclick="javascript:viewEntityDetailsInPopup('${listVillageDetails[0]}', 'viewVillageHistoryReport.do', 'globalvillageId');"><i class="fa fa-history" aria-hidden="true"></i></a>
													</td>
													
													<td width="10%" align="center">
														<c:if test="${not empty listVillageDetails[10]}">
														<a href="#"  onclick="javascript:retrieveFile1('${listVillageDetails[0]}', 'V');"  ><i class="fa fa-file-text-o" aria-hidden="true"></i></a>
														</c:if>
												    </td>
												    
											   		<td>
										   			<a href="#" onclick="javascript:viewLandRegionGISMapInPopup(3,'${listVillageDetails[0]}','L','V');"><i class="fa fa-map-marker" aria-hidden="true"></i></a>
										   			</td>
												</tr>
												<%--  </c:if> --%>
												 
												  </c:when>
												  <c:otherwise>
												     <p>Record Not Found</p>
												  </c:otherwise>
												  </c:choose>
												</c:forEach>
												</tbody> 
				                 </table>
				                 </div>
				            </div>
		                  
		                  <!-- Button for Page after POST method @Ashish 18 aUG -->	
					  
				 <div class="box-footer" id="showbutton">
                    <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
							<button type="button" class="btn btn-primary" onclick="javascript:location.href='globalviewvillageforcitizen.do?<csrf:token uri='globalviewvillageforcitizen.do'/>'"><spring:message code="Button.BACK"/></button>
							<button type="button" class="btn btn-danger" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
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
					buildSubDistrict('<c:out value="${genericReportingEntity.paramDistrictCode}"/>');
					setTimeout(function(){
						$("#ddSourceSubDistrict option[value='<c:out value="${genericReportingEntity.paramSubDistrictCode}"/>']").attr("selected", "selected");
					}, 200);
				}, 200);
			}
		});
	</script>	

</body>
</html>