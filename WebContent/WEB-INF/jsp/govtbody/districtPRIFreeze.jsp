
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>
<script type="text/javascript">
 $(document).ready(function() {
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
		 if($(this).attr('id') == "freezePRI"){
			 $("#freezePRI").validate({
				 ignoreTitle: true ,
	             submitHandler: function (form) {
	            	 customSubmitValidation(form);
	       		 }
	         }); 
	    	 validationForm(); 
	     }
	 });
	 $( 'INPUT[id^=btnFormAction]' ).click(function() {
		 validationForm();
		 $('[name = processAction]').val($(this).attr('param'));
	});
 });

</script>
</head>
<body>
	<!-- Main Form Stylings starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message code="Label.freeze.priLocalBodyFreeze"/></h3>
			<ul class="item_list">
				<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form action="freezeDistrictPRI.htm" method="post" id="freezePRI" commandName="freezePRI"> 	
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="freezeDistrictPRI.htm"/>"/>
				<form:hidden path="processAction"/>
				<form:hidden path="lbLRType" value="P"/>	
					<!-- General Details of District Started-->
					<div class="form_block">
						<div class="col_1">
							
							<ul class="form_body">
								<li>
									<label><spring:message code="App.DISTRICTCODE" text="District  Code"/></label>
							    	<form:input path="districtCode" id="districtCode" readonly="true" value="${freezeDistrict.districtCode}"/>	    	
						    	</li>
								<li>
									<label><spring:message code="Label.DISTRICTNAME" text="Org Specific Code"/></label>
								  	<form:input path="districtNameEnglish" id="districtNameInEn" readonly="true"  value="${freezeDistrict.districtNameEnglish}"/>	    	
								</li>
								<li>
							    	<label><spring:message code="Label.DISTRICTSTATUS" text=""/></label>
							     	<form:input path="districtStatus" id="statusDist" readonly="true"  value="${freezeDistrict.districtStatus}"/>	    	
								</li>
							  	<li>
							    	<label><spring:message code="Label.statusState" text="State Status"/></label>
							     	<form:input path="stateStatus" id="statusState1" readonly="true"  value="${freezeDistrict.stateStatus}"/>
							     	<div id="statusState" class="errormsg label"></div>
								 </li>  
								 <li> 
								    <c:if test="${freezeDistrict.districtStatus eq 'Freeze'}">
								    	<label for="reason" ><spring:message code="Label.REASON" text=""/> <font color="#FF0000">*</font></label> 
										<form:textarea id="reason" path="reason" maxlength="500" rows="5" cols="50"/>
										<label  id="error" class="errormsg label" ><c:out value="${errorReason}" escapeXml="true"></c:out></label>
							    	</c:if>
								 </li>
							</ul>	
						</div>
					</div>
					<br/>
					
					<!-- Buttons Freeze and Un-freeze -->
					<c:if test="${freezeDistrict.stateStatus eq 'Unfreeze'}">
						<c:choose>
							<c:when test="${freezeDistrict.districtStatus eq 'Unfreeze'}">
								<input type="submit" id="btnFormActionFreeze" class="bttn" param="Freeze" value="<spring:message htmlEscape="true" code="Freeze"/>"/>
							</c:when>
							<c:when test="${freezeDistrict.districtStatus eq 'Freeze'}">
								<input type="submit" id="btnFormActionUnfreeze" class="bttn" param="UnFreeze" value="<spring:message htmlEscape="true" code="UnFreeze"/>"/>
							</c:when>
						</c:choose>
					</c:if>
					<input type="button" onclick="javascript:location.href='home.htm'" value="Close" class="bttn"/>	
			</form:form>	
		</div>
	</div>
				
	<!-- Villages which are used in draft Started -->
	<c:if test="${ not empty draftedOrFreezedVillages}">
	<div id="divExistingFreezedVillageDeatils" class="form_stylings" style="display: none;">
		<div class="form_block">
				<div class="col_1">
					<h4>${freezePRI.districtNameEnglish} district can't be freeze because below Local Bodies are in ${headingMessage}.In order to Freeze this district first take action on below Local Bodies.</h4>
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
	<!-- Villages which are used in draft Ends -->
<!-- Page Content ends -->
</div>
<!-- Main Form Stylings ends -->

<script type='text/javascript'>
$(window).load(function () {
	<c:if test="${ not empty draftedOrFreezedVillages}">
		/**
		 * The below code used to display dialog box 
		 * to show Drafted Villages Details.
		 */  
		 $(function() {
			$( "#divExistingFreezedVillageDeatils" ).dialog({
		    	title : "Drafted or Freezed Entity Details.",
		    	resizable: false,
		      	width:700,
		      	height:500,
		      	modal: true
		    });
		 });
	</c:if>
	<c:if test="${freezeDistrict.stateStatus eq 'Freeze'}">
		customAlert("PRI LocalBody has been  already  Freezed by State User. You can't UnFreezed.");
	</c:if>
}); 
</script>

</body>
</html>

