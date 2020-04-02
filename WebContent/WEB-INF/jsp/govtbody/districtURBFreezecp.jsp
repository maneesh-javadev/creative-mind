
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrStateFreezeService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrStateService.js'></script> 
<script type="text/javascript">
function validateNumber(e, id) {
	$("#err"+id).html("");
	var n = e.charCode;
	if ((n >= 48 && n <= 57) || (n == 0)) {

	} else {
		e.preventDefault();
		$("#" + id + "_type_error").fadeIn(1000, function() {
			$("#" + id + "_type_error").fadeOut(1000);
		});
	}
}

genrateOTP=function(userId){
	lgdDwrStateService.sendOTPForLGDDataConfirmation(userId, {
		callback : function( result ) {
			
			if($.parseJSON( result )){
				$("#msgsendOTP").html("OTP has been sent to your Nodal Officer's Mobile Number successfully");
			}else{
				$("#msgsendOTP").html("Some problem send OTP please contact");
			}
		},
		errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
		async : true
	});
}

 $(document).ready(function() {
	 
	 $( 'INPUT[id^=btnFormAction]' ).click(function() {
			$('[name = processAction]').val($(this).attr('param'));
			 validationForm();
		});
	 
	 var validationForm = function (){
		<c:if test="${freezeDistrict.districtStatus eq 'Freeze'}">
			$("#reason" ).rules( "add", {
		 		required : true,  
		 		messages: { required: "Please enter a valid reason for unfreeze." }
			});	
 		</c:if>
	 };
	var customSubmitValidation = function (form){
		$("input[type='button']").attr('disabled','disabled');
		$("input[type='submit']").attr('disabled','disabled');
		form.submit();
	};
	 $('form').each(function(){
		 if($(this).attr('id') == "freezeURB"){
			 $("#freezeURB").validate({
				 ignoreTitle: true ,
	             submitHandler: function (form) {
	            	 customSubmitValidation(form);
	       		 }
	         }); 
	    	 validationForm(); 
	     }
	 });
	 
 });



</script>
</head>
<body>

<section class="content">
	<div class="row">
          <!-- main col -->
          <section class="col-lg-12">

          
		<div class="box">
		      <div class="box-header with-border">
		        <h3 class="box-title"><spring:message code="Label.freeze.urbiLocalBodyFreeze" htmlEscape="true"></spring:message></h3>
		  </div><!-- /.box-header -->

<%-- 
			<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message code="Label.freeze.priLocalBodyFreeze"/></h4>
           </div><!-- /.box-header --> --%>

	
				<form:form action="freezeDistrictURB.htm" method="post" id="freezeURB" commandName="freezeURB" class="form-horizontal"> 	
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="freezeDistrictURB.htm"/>"/>
				
				<div class="box-body">
				<form:hidden path="processAction"/>
				<form:hidden path="lbLRType" value="U"/>	
				 
				
				<div class="form-group">
 				<label for="districtCode" class="col-sm-3 control-label"><spring:message code="App.DISTRICTCODE" /></label>
  				<div class="col-sm-6">
  					<form:input path="districtCode" id="districtCode" readonly="true"/>	  
    			</div>
			</div>
			
			<div class="form-group">
 				<label for="districtNameInEn" class="col-sm-3 control-label"><spring:message code="Label.DISTRICTNAME" /></label>
  				<div class="col-sm-6">
  					<form:input path="districtNameEnglish" id="districtNameInEn" readonly="true"/>	  
    			</div>
			</div>
			
			<div class="form-group">
 				<label for="statusDist" class="col-sm-3 control-label"><spring:message code="Label.DISTRICTSTATUS" /></label>
  				<div class="col-sm-6">
  					<form:input path="districtStatus" id="statusDist" readonly="true"/>	    	
    			</div>
			</div>
			
			<div class="form-group">
 				<label for="statusState1" class="col-sm-3 control-label"><spring:message code="Label.statusState" text="State Status"/></label>
  				<div class="col-sm-6">
  					<form:input path="stateStatus" id="statusState1" readonly="true"/>
					<div id="statusState" class="errormsg label"></div>
    			</div>
			</div>
			
			<c:if test="${freezeDistrict.districtStatus eq 'Freeze'}">
				<div class="form-group">
	 				<label for="districtCode" class="col-sm-3 control-label"><spring:message code="Label.REASON" text=""/> <font color="#FF0000">*</font></label>
	  				<div class="col-sm-6">
	  					<c:choose>
											<c:when test="${freezeDistrict.districtStatus eq 'Freeze' and freezeDistrict.stateStatus eq 'Freeze' }">
											<textarea readonly></textarea>
											</c:when>
											<c:otherwise>
											<form:textarea id="reason" path="reason" maxlength="500" rows="5" cols="50"  />
											</c:otherwise>
						</c:choose>
	  					
						<label  id="error" class="mandatory" ><c:out value="${errorReason}" escapeXml="true"></c:out></label>
	    			</div>
				</div>
			</c:if>
				
					
					<br/>
					<c:if test="${freezeDistrict.districtStatus eq 'Unfreeze' or freezeDistrict.stateStatus eq 'Unfreeze' }">
					<div class="box-header subheading">
                  		<h4 class="box-title"></h4>
               	    </div><!-- /.box-header -->
               	    
               	       <div class="form-group">
	                    	<label class="col-sm-5 control-label">${freezeDistrict.stateStatus}<c:out value="The data available/entered in LGD is up-to-date and correct. " escapeXml="true"></c:out></label>
	                      	<div class="col-sm-6">
	                        	  <button name="sendOTP" type="button" class="btn btn-success" onclick="genrateOTP('${userId}');" >  Confirm</button>
									   <div id="msgsendOTP" style="color: green;"></div>
	                      	</div>
	                    </div>
                          
                        <div class="form-group">
	                    	<label class="col-sm-5 control-label"><c:out value="Enter OTP" escapeXml="true"></c:out><span class="mandatory">*</span></label>
	                      	<div class="col-sm-6">
	                        	  <form:input path= "userOTP" pattern = "^[0-9\\s]+$" class="form-control" id="userOTP" maxlength="6" required = "true" placeholder="Enter OTP" onkeypress="validateNumber(event,'userOTP')" autocomplete="off"/>
	                               <form:errors htmlEscape="true" path="userOTP" cssClass="errormsg"/>
	                               <div id="userOTPErr" style="color: red;font-size: x-small;"></div>
	                      	</div>
	                    </div>
					</c:if>
					<div class="box-footer">
				     <div class="col-sm-offset-2 col-sm-10">
				       <div class="pull-right">
				       		<c:if test="${freezeDistrict.districtStatus eq 'Unfreeze' and freezeDistrict.stateStatus eq 'Unfreeze' }">
										<c:choose>
											<c:when test="${freezeDistrict.districtStatus eq 'Unfreeze'}">
												<input type="submit" id="btnFormActionFreeze" class="btn btn-success" param="Freeze" value="<spring:message htmlEscape="true" code="Freeze"/>"/>
											
											</c:when>
											<c:when test="${freezeDistrict.districtStatus eq 'Freeze'}">
												<input type="submit" id="btnFormActionFreeze" disabled="disabled" class="btn btn-success" param="Freeze" value="<spring:message htmlEscape="true" code="Freeze"/>"/>
												
											</c:when>
										</c:choose>
							</c:if>
							<c:if test="${freezeDistrict.districtStatus eq 'Freeze' and freezeDistrict.stateStatus eq 'Unfreeze' }">
								<input type="submit" id="btnFormActionUnfreeze" class="btn btn-success" param="UnFreeze" value="<spring:message htmlEscape="true" code="UnFreeze"/>"/> 
							</c:if>
									<input type="button" onclick="javascript:location.href='home.htm'" value="Close" class="btn btn-danger"/>	
				        </div>
				     </div>   
	   			</div>
				</div>	
			</form:form>	

				
	<!-- Villages which are used in draft Started -->
	<c:if test="${ not empty draftedOrFreezedVillages}">
	<div id="divExistingFreezedVillageDeatils" class="form_stylings" style="display: none;">
		<div class="form_block">
				<div class="col_1">
					<h4><c:out value="${freezeURB.districtNameEnglish} district can't be freeze because below Local Bodies are in ${headingMessage}.In order to Freeze this district first take action on below Local Bodies."></c:out> </h4>
					<ul class="form_body">
						<li>
							<table id="tblGornmentOrderDetails" class="data_grid" width="100%">
								<thead>
									<tr>
										<th>S.No.</th>
										<th><spring:message code="App.LOCALGOVTBODYCODE"/></th>
										<th><spring:message code="Label.NAMEOFLOCALBODY"/> (with its hierarchical details)</th>
										<th><spring:message code="Label.STATUS"/></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="varDraftedOrFreezedEntity" items="${draftedOrFreezedVillages}" varStatus="counter">
										<tr>
											<td><c:out value="${counter.count}" escapeXml="true"></c:out></td>
											<td><c:out value="${varDraftedOrFreezedEntity.lblc}" escapeXml="true"></c:out></td>
											<td><c:out value="${varDraftedOrFreezedEntity.lbName}" escapeXml="true"></c:out></td>
											<td><c:out value="${varDraftedOrFreezedEntity.lbStatus}" escapeXml="true"></c:out></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</li>
					</ul>	
				</div>
			</div>
			<br/>
		</div>
	</c:if>

</div>
</section>
</div>
</section>
<!-- Main Form Stylings ends -->
<div class="modal fade" id="customAlert" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"><c:out value="Drafted or Freezed Entity Details." ></c:out></h4>
        </div>
        <div class="modal-body" id="customAlertbody">
         <div class="form_block">
				<div class="col_1">
					<h4><c:out value="${freezeURB.districtNameEnglish} district can't be freeze because below Local Bodies are in ${headingMessage}.In order to Freeze this district first take action on below Local Bodies."></c:out> </h4>
					<ul class="form_body">
						<li>
							<table id="tblGornmentOrderDetails" class="data_grid" width="100%">
								<thead>
									<tr>
										<th>S.No.</th>
										<th><spring:message code="App.LOCALGOVTBODYCODE"/></th>
										<th><spring:message code="Label.NAMEOFLOCALBODY"/> (with its hierarchical details)</th>
										<th><spring:message code="Label.STATUS"/></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="varDraftedOrFreezedEntity" items="${draftedOrFreezedVillages}" varStatus="counter">
										<tr>
											<td><c:out value="${counter.count}" escapeXml="true"></c:out></td>
											<td><c:out value="${varDraftedOrFreezedEntity.lblc}" escapeXml="true"></c:out></td>
											<td><c:out value="${varDraftedOrFreezedEntity.lbName}" escapeXml="true"></c:out></td>
											<td><c:out value="${varDraftedOrFreezedEntity.lbStatus}" escapeXml="true"></c:out></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</li>
					</ul>	
				</div>
			</div>
        </div>
        <div class="modal-footer">
         
           <button type="button" class="btn btn-default" data-dismiss="modal" >Close</button>
        </div>
      </div>
      
    </div>
  </div>
<script type='text/javascript'>
$(window).load(function () {
	<c:if test="${ not empty draftedOrFreezedVillages}">
	 $("#customAlert").modal('show');
	</c:if>
	<c:if test="${freezeDistrict.stateStatus eq 'Freeze'}">
    /* changed by kirandeep for alert in freeze unfreeze */
	customAlert("Urban LocalBody has been  already  Freezed by State User. You can't UnFreezed.");
</c:if>
}); 
</script>

</body>
</html>

