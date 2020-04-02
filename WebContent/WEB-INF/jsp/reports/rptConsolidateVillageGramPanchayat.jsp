<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../common/taglib_includes.jsp"%>
<link rel="stylesheet" href="js/pdf/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="js/pdf/css/buttons.dataTables.min.css">
<!-- <script type="text/javascript" src="js/pdf/jquery-1.12.3.js"></script> --> 
 <script type="text/javascript" src="js/pdf/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="js/pdf/dataTables.buttons.min.js"></script> 

  <script type="text/javascript" src="js/pdf/buttons.print.min.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/lgdDwrDistrictService.js"></script>

<script type="text/javascript" class="init">
$(document).ready(function() {
	$('#example').DataTable( {
		 "pageLength": 50,
        dom: 'Bfrtip',
        buttons: [
                  {
                      text: 'Print',
                      action: function ( e, dt, node, config ) {
                    	 
                    	  document.getElementById('footer').style.display = 'block';
                    		document.getElementById('footer').style.visibility = 'visible';	
                    		var printContent = document.getElementById("printable2");
                    		// document.getElementById('btn1').style.visibility = 'hidden';
                    		var Win4Print = window.open('', '', 'left=0,top=0,width=500,height=500,top=301,left=365,resizable=1,status=0,toolbar=0');
                    		// alert("contect"+printContent.innerHTML);
                    		Win4Print.document.write(printContent.innerHTML);
                    		Win4Print.document.close();
                    		Win4Print.focus();
                    		Win4Print.print();
                    		Win4Print.close();
                    		document.getElementById('footer').style.display = 'none';
                    		document.getElementById('footer').style.visibility = 'hidden';
                    		
                    		return false; 
                      }
                  }
              ]
    } );
	
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
	
	jQuery.validator.addMethod("customMandateState", function(value, element) {
		var status = true;
			if(isEmptyObject($('#ddSourceState').val())){
				status = false;
			}
		return status;
	});
	jQuery.validator.addMethod("customMandateDist", function(value, element) {
		var status = true;
		if(isEmptyObject($('#ddSourceDistrict').val())){
				status = false;
			}
		return status;
	});
	
	 $('form').each(function(){
	        if($(this).attr('id') == "genericReportingEntity"){
	        	$("#genericReportingEntity").validate({
	                ignoreTitle: true ,
	                submitHandler: function (form) {
	                	form.submit();
	                } 
	            }); 
	    		validationForm(); 
	    	}
	    });
});

var validationForm = function (){
	
 	$("#ddSourceState" ).rules( "add", {
		  customMandateState : true, messages: {customMandateState : "Please select a State."}
	});
 	$("#ddSourceDistrict" ).rules( "add", {
		  customMandateDist : true, messages: {customMandateDist : "Please select a District."}
	});
 	$("#captchaAnswer" ).rules( "add", {
 		  required : true, messages: {required : "Please enter the text shown above."}
	});
}


var buildDistrict = function(stateCode){
	lgdDwrDistrictService.getDistrictList(parseInt(stateCode), {
		callback : function( result ) {
			var options = $("#ddSourceDistrict"); 
			jQuery.each(result, function(index, obj) {
				var option = $("<option />");
				option.val(obj.districtCode).text(obj.districtNameEnglish);
				options.append(option);
			});
		},
		errorHandler : function(errorString, exception) { alert(errorString + " lgd"); alert(exception); },
		async : true
	});
};
</script>
</head>
<body class="dt-example">
	<!-- Main Form Styling starts -->
	<div class="form_stylings">
		<!-- Main Heading starts -->
		<div class="header">
			<h3>
			<c:choose>
					<c:when test="${empty reportList}">
						<spring:message htmlEscape="true" code="Label.ConsolidatedReportDistrictWise"></spring:message>
					</c:when>
					<c:otherwise>
						<spring:message code="label.report.no.twelve.subtitle" arguments="${reportDistwiseVillagandMappedGP.entitesForMessage}" argumentSeparator=","></spring:message>
					</c:otherwise>
			</c:choose>
			</h3>
		</div>
		<div class="page_content">
			<div class="form_container">
				<form:form action="rptConsolidateVillageGramPanchayat.do" method="POST" id="genericReportingEntity" commandName="reportDistwiseVillagandMappedGP">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="rptConsolidateVillageGramPanchayat.do"/>" />
					<form:hidden path="entitesForMessage" id="entitesForMsg"/>
				
					<c:choose>
					<c:when test="${empty reportList}">
						<div id="divCenterAligned" class="form_stylings">
							<div class="form_block">
								<div class="col_1">
									
									
									<ul class="form_body" >
										
										
											<li>
												<label for="ddSourceState"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message></label>
												<form:select  path="" class="combofield" id="ddSourceState" onchange="error_remove();">
													<form:option value="">
														<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
													</form:option>
													<form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
												</form:select>
											</li>
											<li>
												<label for="ddSourceDistrict"><spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message></label>
												<form:select path="paramDistrictCode" class="combofield" id="ddSourceDistrict">
													<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
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
											</c:if>
										</li>
										<li>
										    <label class="inline">&nbsp;</label>
											<input type="submit" id="actionFetchDetails" value="<spring:message htmlEscape="true" code="Button.GETDATA"/>" />
											<input type="button" value="<spring:message code="Button.CLOSE"/>" onclick="javascript:location.href='welcome.do'"/>	
										</li>
									</ul>	
								</div>
							</div>
						</div>
					</c:when>
				    <c:otherwise>
				    <div id='printable2'>
					    <div class="form_stylings">
							<div class="form_block" id="divData">
								<div class="col_1">
									<h4>
											
									</h4>
									<ul class="form_body" >
										<li>
											<table id="example" class="display" cellspacing="0" width="100%">
												<thead>
													<tr>
														<th><spring:message code="Label.SNO"></spring:message></td>
														<th><spring:message code="Label.SUBDISTRICT"></spring:message></th>
														<th><spring:message code="Label.VILLAGECODE"></spring:message></th>
														<th><spring:message code="Label.VILLAGENAME"></spring:message></th>
														<th><spring:message code="Label.COVERAGEDTYPE"></spring:message></th>
														<th><spring:message code="Label.LGDCODE" text="LGD Code" htmlEscape="true"/></th>
														<th><spring:message code="Label.GramPanchayatName" text="Pesa Status"></spring:message></th>
														
													</tr>
												</thead>
												<tbody>
													<c:forEach var="object" varStatus="rowstatus" items="${reportList}">
															<tr>
																<td width="5%"><c:out value="${rowstatus.count}"/></td>
																<td align="left"><c:out value="${object.subdistrictName}"></c:out></td>
																<td><c:out value="${object.villageCode}"></c:out></td>
																<td align="left"><c:out value="${object.villageName}"></c:out></td>
																<td width="5%"><c:out value="${object.lbtype}"></c:out></td>
																<td><c:out value="${object.localbodyCode}"></c:out></td>
																<td align="left"><c:out value="${object.lbHierarchy}"></c:out></td>
															</tr>
														</c:forEach>
													</tbody>
												
											</table>
										</li>
									</ul>	
								 </div>
							</div> 
						</div>
						<c:if test="${! empty reportList}">
							<ul class="blocklist center" style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
								<li>
									<label><spring:message code="Label.URL" htmlEscape="true"></spring:message>
									<%=request.getScheme() + "://" + request.getServerName() + request.getContextPath()%>
									</label>
								</li>
								<li>
									<%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%>
									<label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message>
									<%=df.format(new java.util.Date())%> </label>
								</li>
								<li>
									<label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
								</li>
								<li class="buttons">
									
								</li>
							</ul>
						</c:if>
			
						<div id="footer" style="visibility: hidden; display: none;">
							<p id="footertext" style="text-align: center">
													Site is designed, hosted and maintained by <a
														target="_blank" href="http://www.nic.in/">National
														Informatics Centre</a><br> Contents on this website is
														owned,updated and managed by the <a target="_blank"
														href="http://www.panchayat.gov.in/" target="_blank">Ministry of
															Panchayati Raj</a>, Government of India <br> Site best
															viewed on Internet Explorer (IE)-8 &amp; above, Mozilla
															Firefox-11 &amp; above 
												</p>
							<div id="displayBox" style="text-align: center; display: none;">
								<img src="<%=request.getContextPath()%>/images/ajax-loader-2.gif" alt="Loading..." />
							</div>
						</div>
			
			    </div>
						<div class="buttons center" id="showbutton">
							<input type="button" value="<spring:message code="Button.BACK"/>" onclick="javascript:location.href='rptConsolidateVillageGramPanchayat.do?<csrf:token uri='rptConsolidateVillageGramPanchayat.do'/>'"/>
							<input type="button" value="<spring:message code="Button.CLOSE"/>" onclick="javascript:location.href='welcome.do'"/>
						</div>
					</c:otherwise>
					</c:choose>
				</form:form>
			</div>
		</div>
	</div>
 	<script type='text/javascript'>
	$(window).load(function () {
		
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