<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../homebody/commanInclude.jsp"%>
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
<body>

<section class="content">

 <!-- Main row -->
 <div class="row">
     <!-- main col -->
	<div class="container">
	<section class="col-lg-12 prebox">
     	<div class="box">
            <div class="box-header with-border">
              <h3><c:choose>
				<c:when test="${empty reportList}">
					<spring:message htmlEscape="true" code="Label.ConsolidatedReportDistrictWise"></spring:message>
				</c:when>
				<c:otherwise>
					<spring:message code="label.report.no.twelve.subtitle" arguments="${reportDistwiseVillagandMappedGP.entitesForMessage}" argumentSeparator=","></spring:message>
				</c:otherwise>
			</c:choose></h3>
            </div><!-- /.box-header -->
                
                <!-- form start -->
                <form:form class="form-horizontal" action="rptConsolidateVillageGramPanchayat.do" method="POST" id="genericReportingEntity" commandName="reportDistwiseVillagandMappedGP">
				<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="rptConsolidateVillageGramPanchayat.do"/>" />
				<form:hidden path="entitesForMessage" id="entitesForMsg"/>
						
				<c:choose>
					<c:when test="${empty reportList}">
					
					<div class="box-body" id="divCenterAligned">
						 <div class="form-group ">
		                      <label for="ddSourceState" class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message><span class="mandatory">*</span></label>
		                      <div class="col-sm-6">
		                      	<form:select  path="" class="form-control" id="ddSourceState" onchange="error_remove();">
									<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
									<form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
								</form:select>
		                     </div>
	                      </div>
	                       
	                       <div class="form-group">
		                      <label for="ddSourceDistrict" class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message><span class="mandatory">*</span></label>
		                      <div class="col-sm-6">
		                      	<form:select path="paramDistrictCode" class="form-control" id="ddSourceDistrict">
									<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
								</form:select> <br>
		                     </div>
		                    </div>
	                       <div>
	                       <div class="form-group">
		  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.ENTERTEXTASSHOWNINTHISIMAGE" ></spring:message> <span class="mandatory">*</span></label>
		  <div class="col-sm-6">
		  	<img src="captchaImage" id="captchaImageId" alt="Captcha Image" border="0"/>
		  </div>
</div>
<div class="form-group">
		  <label  class="col-sm-3 control-label" for="sel1">
		 </label>
		  <div class="col-sm-6">
		  	<form:input path="captchaAnswer" autocomplete="off"  style="width:250px" maxlength="5"/>
		  	 <a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>
		  <form:errors path="captchaAnswer" class="mandatory"/>
			<c:if test="${not empty captchaFaliedMessage and not captchaFaliedMessage}">
				<br/>
				<span class="mandatory"><strong><spring:message code="captcha.errorMessage"/></strong></span>
			</c:if>
		  </div>
</div>


				</div>	  </div>
					  
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
		                   <div id='printable2'>
		                  <div class="box-body" id="divData">
		                   <div class="table-responsive ">
							<table class="table table-striped table-bordered dataTable" id="example" >
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
							</div>
				          </div>
		                  <!-- Button for Page after POST method @Ashish 18 aUG -->	
					  
						 <c:if test="${! empty reportList}">
							<div style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
							<label><spring:message code="Label.URL" htmlEscape="true"></spring:message><%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%> </label>
							</br><%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%><label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message><%=df.format(new java.util.Date())%></label>
							</br><label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
						</div>
						</c:if>
						<div id="footer" ></div>
				 </div>
				 <div class="box-footer" id="showbutton">
                    <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
							<input type="button" class="btn btn-primary" value="<spring:message code="Button.BACK"/>" onclick="javascript:location.href='rptConsolidateVillageGramPanchayat.do?<csrf:token uri='rptConsolidateVillageGramPanchayat.do'/>'"/>
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