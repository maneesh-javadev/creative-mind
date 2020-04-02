<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../common/taglib_includes.jsp"%>

<%-- <%@include file="CommonReportingIncludeJS.jsp"%> --%>
<style type="text/css">

/* Data Grid modified Stylings - Table */
table.my_data_grid{
border:1px solid #cecece;
font-size:12px;
color:#555;
table-layout: fixed;
}
table.my_data_grid tr th, table.my_data_grid tr td{
border-left:1px solid #cecece;
border-bottom:1px solid #cecece;
padding:8px;
text-align:left;
transition:all ease-in-out 0.2s;
word-wrap: break-word;
}
table.my_data_grid tr th:first-child, table.my_data_grid tr td:first-child{
border-left:none;
}
table.my_data_grid tr th{
background:#eee;
color:#333;
border-bottom:2px solid #c3c3c3;
font-weight:bold;
font-size:11px;
text-transform:uppercase;

}
table.my_data_grid tbody tr:hover{
background:#f5f5f5;
}
table.my_data_grid > tbody tr:hover td{
border-bottom:1px solid #aaa;
color:#333;
text-indent:1px;

}
#divCenterAligned.my_form_stylings label{
	display:inline-block;
	*display:inline;
	*zoom:1;
	vertical-align:top;
	width:30%;
	padding-right:1%;
	text-align:right;
}
.mcol_1 ul.form_body li > input[type="text"], .mcol_1 ul.form_body li > input[type="password"], .mcol_1 ul.form_body li > textarea, .mcol_1 ul.form_body li > select{
width:35%;
}

</style>
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
	<!-- Main Form Styling starts -->
	<div class="form_stylings">
		<!-- Main Heading starts -->
		<%-- <div class="header">
			<h3><spring:message htmlEscape="true" code="Label.LbMapped"></spring:message></h3>
		</div> --%>
		<div class="page_content">
			<div class="form_container">
				<form:form action="viewLocalBodyMappedToDistricts.do" onsubmit = "return validationForm()" method="POST" id="localBodyMappedToDistrictEntity" commandName="localBodyMappedToDistrictEntity">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="viewLocalBodyMappedToDistricts.do"/>" />
					<form:hidden path="entitesForMessage" id="entitesForMsg"/>
					<div id="dialogBX" style="display: none;">
						<iframe id="myIframe" width="910" height="650"></iframe>
					</div>
					<c:choose>
					<c:when test="${showSearchHierarchy}">
						<div id="divCenterAligned" class="my_form_stylings">
							<div class="form_block">
								<div class="mcol_1">
									<h4><spring:message htmlEscape="true" code="Label.LbMappedWithDistricts"></spring:message></h4>
								
									<ul class="form_body" >
										
										
										<li>
											<label  for="ddSourceState"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message></label>
											<form:select path="paramStateCode" class="combofield" id="ddSourceState" onchange="error_remove();">
												<form:option value="">
													<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
												</form:option>
												<form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
											</form:select>
										</li>
										
										<li>
											<label><!-- Used Label, please dont remove. --></label>
											<img src="captchaImage" alt="Captcha Image" />
										</li>
										<li>
											<label>
												<spring:message code="captcha.message"	htmlEscape="true" />
												<span class="mandate">*</span>
											</label>
											<form:input	path="captchaAnswer" id="captchaAnswer" autocomplete="off" />
											<c:if test="${not empty captchaFaliedMessage and not captchaFaliedMessage}">
												<br/><label><!-- Used Label, please dont remove. --></label>
												<span class="errormsg"><strong><spring:message code="captcha.errorMessage"/></strong></span>
												<div id="error"></div>
											</c:if>
										</li>
										<li>
										    <label class="inline">&nbsp;</label>
											<input class="bttn" type= "submit"  value="<spring:message htmlEscape="true" code="Button.GETDATA"/>" />
											<input type="button" value="<spring:message code="Button.CLOSE"/>" onclick="javascript:location.href='welcome.do'"/>	
										</li>
									</ul>	
								</div>
							</div>
						</div>
					</c:when>
				    <c:otherwise>
					    <div class="form_stylings">
							<div class="form_block" id="divData">
								<div class="col_1">
									<h4>
										<spring:message htmlEscape="true" code="Label.LbMappedWithDistricts"></spring:message>
									</h4>
									
									
									<ul class="form_body" >
										<li>
											<table id="example" class="my_data_grid" cellspacing="0" width="100%">
												<thead>
													<tr>
														<th width="3%"><spring:message code="SNO"/></th>
														<th width="5%"><spring:message code="DISTRICT PANCHAYAT CODE"/></th>
														<th width="10%"><spring:message code="DISTRICT PANCHAYAT NAME"/></th>
														<th width="8%"><spring:message code="INTERMEDIATE PANCHAYAT CODE"/></th>
														<th width="10%"><spring:message code="INTERMEDIATE PANCHAYAT NAME"/></th>
														<th width="8%"><spring:message code="GRAM PANCHAYAT CODE"/></th>
														<th width="10%"><spring:message code="GRAM PANCHAYAT NAME"/></th>
														<th  width="40%"><spring:message code="MAPPED DISTRICTS"/></th>
														
													</tr>
												</thead>
												<tbody>
												
													<c:forEach var="listLocalBodyDetails" varStatus="rowstatus" items="${SEARCH_LOCALBODY_KEY}">
														<tr >
															<td width="6%"><c:out value="${rowstatus.count}"/></td>
															<td align="left"><c:out value="${listLocalBodyDetails.dpcode}"></c:out></td>
															<td align="left"><c:out value="${listLocalBodyDetails.dpname}"></c:out></td>
															<td align="left"><c:out value="${listLocalBodyDetails.ipcode}"></c:out></td>
															<td align="left"><c:out value="${listLocalBodyDetails.ipname}"></c:out></td>
															<td align="left"><c:out value="${listLocalBodyDetails.gpcode}"></c:out></td>
															<td align="left"><c:out value="${listLocalBodyDetails.gpname}"></c:out></td>
															<td width="40%" align="left"><c:out value="${listLocalBodyDetails.mappedDistrict}"></c:out></td>
														</tr>
													</c:forEach>
												</tbody>
												
											</table>
										</li>
									</ul>	
									<ul class="blocklist center" style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
						              <li>
							                     <label><spring:message code="Label.URL" htmlEscape="true"></spring:message>
							                     <%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%> </label>
						             </li>
						             <li>
							                   <%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%>
						                        <label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message>								
				 			                    <%=df.format(new java.util.Date())%>  </label>
						           </li>
						           <li>
							                  <label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
						          </li>
					              </ul>
									
								
						</div></div></div>
						<div class="buttons center" id="showbutton">
							<input type="button" value="<spring:message code="Button.BACK"/>" onclick="javascript:location.href='globalViewLocalBodyMappedToDistricts.do?<csrf:token uri='globalViewLocalBodyMappedToDistricts.do'/>'"/>
							<input type="button" value="<spring:message code="Button.CLOSE"/>" onclick="javascript:location.href='welcome.do'"/>
						</div>
					</c:otherwise>
					</c:choose>
				</form:form>
			</div>
		</div>
	</div>
 		
</body>
</html>