<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../common/taglib_includes.jsp"%>

<%-- <%@include file="CommonReportingIncludeJS.jsp"%> --%>

<script type="text/javascript" class="init">
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
		
		
		 $("#ddSourceState").change(function() {
			    $( '#divCreateDynamicHierarchy' ).empty();
			    $( '#localBodyType' ).val('');
			    
			    $( this ).removeClass("error");
			    $( '#error' + $( this ).attr('id') ).text("");
			   	buildLocalbodyHierarchy($(this).val());
			    });
			    
			    $("#lbTypeHierarchy").change(function() {
					$( '#divCreateDynamicHierarchy' ).empty();
					$( '#errorLbTypeHierarchy' ).text("");
					$( this ).removeClass("error");
					registerCallLocalBodyType();
				});	
			    
			    $("#localBodyType").change(function() {
			    	$( '#errorLocalBodyType' ).text("");
			    	$( this ).removeClass("error");
			    	registerCallDynamicHierarchy(this);	
			    });
		
		
	});
	
	jQuery.validator.addMethod("customMandateState", function(value, element) {
	var status = true;

	
		if(isEmptyObject($('#ddSourceState').val())){
			status = false;
		}
	
	return status;
});
	
	var validationForm = function (){
		var validateform = document.getElementById("ddSourceState");
		if(ddSourceState.value == ""){
			alert("Please Select any State");
			document.getElementById("ddSourceState").value = "Please select any state";
			return false;
			
		}
		else{
			$("#entitesForMsg").val($("#ddSourceState option:selected").text());
		}
		if(captchaAnswer.value==""){
			alert("Please fill the above text");
			document.getElementById("error").innerHTML = "Please fill the above text.";
		}
	
	}
</script>
</head>
<body class="dt-example">
	<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                  <form:form action="viewLocalBodyMappedToDistricts.do" onsubmit = "return validationForm()" method="POST" id="localBodyMappedToDistrictEntity" commandName="localBodyMappedToDistrictEntity" class="form-horizontal">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="viewLocalBodyMappedToDistricts.do"/>" />
					<form:hidden path="entitesForMessage" id="entitesForMsg"/>
						
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message htmlEscape="true" code="Label.LbMappedWithDistricts"></spring:message></h3>
                                </div>
                                
	                   <div id="dialogBX" style="display: none;">
						<iframe id="myIframe" width="910" height="650"></iframe>
					</div>
                        
               <c:choose>
					<c:when test="${showSearchHierarchy}">
					<div class="box-body">
					   <div class="form-group">
							<label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message><span class="mandatory">*</span></label>
							<div class="col-sm-6"> 
							 <form:select path="paramStateCode" class="form-control" id="ddSourceState" onchange="error_remove();">
									<form:option value="">
													<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
									</form:option>
									<form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
								</form:select></div>
						</div>
						
						<div class="form-group">
						 <label class="col-sm-4 control-label"> </label>
						  <div class="col-sm-6"><img src="captchaImage" alt="Captcha Image" />
						  </div>
						</div>
						
						<div class="form-group">
						 <label class="col-sm-4 control-label"><spring:message code="captcha.message"	htmlEscape="true" /> <span class="mandatory">*</span></label>
						  <div class="col-sm-6">
						  <form:input	path="captchaAnswer" id="captchaAnswer" maxlength="5" class="form-control" autocomplete="off" />
											<c:if test="${not empty captchaFaliedMessage and not captchaFaliedMessage}">
												<br/><label></label>
												<span class="errormsg"><strong><spring:message code="captcha.errorMessage"/></strong></span>
												<div id="error"></div>
											</c:if>
						  </div>
						</div>
					</div>
					
					
			<div class="box-footer">
		           <div class="col-sm-offset-2 col-sm-10">
		                 <div class="pull-right">
		                    <button class="btn btn-success" type= "submit"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true" code="Button.GETDATA"/></button>
							<button type="button" class=" btn btn-danger" onclick="javascript:location.href='welcome.do'"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE"/></button>	
		                 </div>
		           </div>   
       			</div>
									
					</c:when>
				    <c:otherwise>
					   <div class="box-body" >
					  <div class="box-header subheading"> <spring:message htmlEscape="true" code="Label.LbMappedWithDistricts"></spring:message></div>
					  
					  <div class="table-responsive ">
							<table id="example" class="table table-bordered table-hover" >
								<thead>
									<tr>
										<th><spring:message code="S.No "/></th>
										<th><spring:message code="DISTRICT PANCHAYAT CODE"/></th>
										<th><spring:message code="DISTRICT PANCHAYAT NAME"/></th>
										<th><spring:message code="INTERMEDIATE PANCHAYAT CODE"/></th>
										<th><spring:message code="INTERMEDIATE PANCHAYAT NAME"/></th>
										<th><spring:message code="GRAM PANCHAYAT CODE"/></th>
										<th><spring:message code="GRAM PANCHAYAT NAME"/></th>
										<th><spring:message code="MAPPED DISTRICTS"/></th>
														
								   </tr>
									</thead>
								<tbody>
								<c:forEach var="listLocalBodyDetails" varStatus="rowstatus" items="${SEARCH_LOCALBODY_KEY}">
														<tr >
															<td><c:out value="${rowstatus.count}"/></td>
															<td align="left"><c:out value="${listLocalBodyDetails.dpcode}"></c:out></td>
															<td align="left"><c:out value="${listLocalBodyDetails.dpname}"></c:out></td>
															<td align="left"><c:out value="${listLocalBodyDetails.ipcode}"></c:out></td>
															<td align="left"><c:out value="${listLocalBodyDetails.ipname}"></c:out></td>
															<td align="left"><c:out value="${listLocalBodyDetails.gpcode}"></c:out></td>
															<td align="left"><c:out value="${listLocalBodyDetails.gpname}"></c:out></td>
															<td align="left"><c:out value="${listLocalBodyDetails.mappedDistrict}"></c:out></td>
														</tr>
													</c:forEach>
								</tbody>
								
							</table>
							</div>
									</div>
									
									
							<div class="form-group" style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
						     
						<label><spring:message code="Label.URL" htmlEscape="true"></spring:message><%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%> </label>
						</br><%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%><label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message><%=df.format(new java.util.Date())%></label>
						</br><label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
						          
					     </div>
					     
					     
						
					   <div class="box-footer" id="showbutton">
                      	<div class="col-sm-offset-2 col-sm-10">
		                 <div class="pull-right">
		                 	 <button type="button"  class="btn btn-info" onclick="javascript:location.href='globalViewLocalBodyMappedToDistricts.do?<csrf:token uri='globalViewLocalBodyMappedToDistricts.do'/>'" ><i class="fa fa-floppy-o"></i> <spring:message code="Button.BACK"/></button>
						  	<button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='welcome.do'" ><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button>
		                 </div>
          				 </div>   
       				</div>
					</c:otherwise>
					</c:choose>
    </div>   
             
    </form:form>      
   </section>
   </div>
   </section>
</body>
</html>