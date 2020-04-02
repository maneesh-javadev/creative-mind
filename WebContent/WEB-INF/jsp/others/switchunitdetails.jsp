<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

 <%@ include file="modifyUser.jsp"%>
 <%@ include file="taglib_includes.jsp"%> 

<%
response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
response.setHeader("Pragma","no-cache");
response.setDateHeader("Expires",-1);
%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>Form Styles</title>
	<link type="text/css" rel="stylesheet" href="css/form.css" media="all" />

	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/pesUtility.js"></script>
	<script  src="${pageContext.request.contextPath}/js/switchunitdetails.js" type="text/javascript" ></script>
	<script  src="https://code.jquery.com/jquery-2.2.4.js" type="text/javascript" ></script>
	<script  src="https://code.jquery.com/jquery-2.2.4.min.js" type="text/javascript" ></script>

	
	<script>
	$(window).load(function () {
		getEntityTypeBasedOnUserId();
		makeThemeAndLanguageSelectedSW();
	});
		
		function makeThemeAndLanguageSelectedSW()
		{
				var selectedTheme = document.getElementById("themeId");
				var selectedThemeValue = document.getElementById("selectedThemeSW").innerHTML;
				if(selectedThemeValue != "")
				selectedTheme.value = selectedThemeValue;
				
				var selectedLanguage = document.getElementById("languageId");
				var selectedLanguageValue = document.getElementById("selectedLanguageSW").innerHTML;
				if(selectedLanguageValue != "")
					selectedLanguage.value = selectedLanguageValue;
				else
					selectedLanguage.value = 'en';
		}
		
		
		
		
		 function submitSwitchunit() {
			
		   if(validateswithunitForm() != false) {
				//validateswithunitForm();
				document.switchUnitDetailsForm.switchunitsubmit.disabled=true; 
				document.switchUnitDetailsForm.switchunitsubmit.value='Please Wait .!';
				document.switchUnitDetailsForm.action="switchunit.do";
				document.switchUnitDetailsForm.submit();
			}     
			
		
		}
 		
		
		 
		
		/* $(document).ready(function() {
			$("#switchUnitDetailsForm").validate({
	            ignoreTitle: true ,
	            submitHandler: function (form) {
	            	alert(form);
	            	form.submit();
	   			},
	            invalidHandler: function(form, validator) {
	                var errors = validator.numberOfInvalids();
	                if (errors) {
	                  var message = errors == 1
	                    ? 'Please correct the following error:\n'
	                    : 'Please correct the following ' + errors + ' errors.\n';
	                  var errors = "";
	                  if (validator.errorList.length > 0) {
	                      for (var x = 0; x < validator.errorList.length; x++) {
	                          errors += "\n\u25CF " + validator.errorList[x].message;
	                      }
	                  }
	                  alert(message + errors);
	                }
	              } 
	        });
		}); */
 
 	/* var validationForm = function (){
 		alert("asdfas");
	 	$("#year" ).rules( "add", {
			  required: true,
			  messages: {
			    required : "Year is mandatory.",
			  }
		});
	}; */

	</script>
</head>

<body> 
<%-- <div class="overlay hiddenDiv_pes" id="overlay1" ></div>
 <div class="box" id="box">
            <a class="boxclose" id="boxclose"></a>
             <div id="validate_error_su">
            <div>
            <ul class="listing_pes">
       			<li>
					<div class="leftDivMsg_pes"><div class="errorImg"></div>
					</div>
					<div class="leftDivMsg_pes">
						<div class="helpMsgHeader width_275_pes">
							<h4>
								<spring:message code="Label.ErrorMessage" htmlEscape="true" />
							</h4>
						</div>
						<div class="helpMsgHeader width_275_pes">
							<spring:message code="Error.SelectRequiredField"  htmlEscape="true"/>
						</div>
						</div>
						
				</li>
			</ul>
            </div>
                         
            </div>            
            </div>  --%>
<section class="content">
  <div class="row">
          <!-- main col -->
     <section class="col-lg-12">

        <div class="box">
		      

	
		<form:form  name="switchUnitDetailsForm" id="switchUnitDetailsForm" modelAttribute="switchUnitDetailsForm" class="form-horizontal">	
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='switchunit.do'/>" />
		<fmt:formatDate var="curYear" value="${now}" pattern="yyyy"/>
		<!-- app : --> <form:hidden path="applicationId" id="applicationId" class="frmfield" htmlEscape="true"  />
		<!-- State --><form:hidden path="stateOldId" id="stateOldId" class="frmfield"  htmlEscape="true"/>
		<!-- UserId --><form:hidden path="userId" id="userId" class="frmfield" htmlEscape="true" />
		<!-- tier --><form:hidden path="tierSetUp" id="tierSetUp" class="frmfield"  htmlEscape="true"/>
		<!-- entityType --><form:hidden path="entityId" id="entityId" class="frmfield" htmlEscape="true" />
		<!-- LevelId --><form:hidden path="levelId" id="levelId" class="frmfield" htmlEscape="true" />
		<!-- tradionalTierSetUp --><form:hidden path="tradionalTierSetUp" id="tradionalTierSetUp" class="frmfield" htmlEscape="true" />
		<!-- centerorState --> <form:hidden path="isCenterOrState" id="isCenterOrState" class="frmfield" htmlEscape="true" />
		<!-- stateVersion --> <form:hidden path="stateVersion" id="stateVersion" class="frmfield" htmlEscape="true" />
		
		<!-- isSelfRegisterUser --><form:hidden path="isSelfRegisterUser" id="isSelfRegisterUser" class="frmfield" htmlEscape="true" />
	    <!-- isSelfRegisterAgency --> <form:hidden path="isSelfRegisterAgency" id="isSelfRegisterAgency" class="frmfield" htmlEscape="true" />
	    <!-- isAreaProfilerUser --> <form:hidden path="isAreaProfilerUser" id="isAreaProfilerUser" class="frmfield" htmlEscape="true" />
		
		<div class="well">
			
			<div class="box-header subheading">
                  <h4 class="box-title">Assigned Unit</h4>
            </div>
			
			<div class="form-group" id="Year">
					<label  class="col-sm-3 control-label" for="year">Year <span class="mandatory">*</span></label>
				  	<div class="col-sm-6">
				  		<form:select class="form-control" htmlEscape="true" path="year" id="year" onchange="getEntityTypeBasedOnUserId();">								
									<form:option htmlEscape="true" value="">--- Select ---</form:option>								
										<c:forEach var="years" items="${yearList}" varStatus="counter">
											<c:choose> 
												<c:when test="${fn:contains(years.financialYear, curYear)}">
													<option selected value="${years.financialYear}"><c:out value="${years.financialYear}" escapeXml="true"></c:out></option>
												</c:when>
												<c:otherwise>
													<option  value="${years.financialYear}"><c:out value="${years.financialYear}" escapeXml="true"></c:out></option>
												</c:otherwise>
											</c:choose>
										</c:forEach>					
						</form:select>
						<div class="error" id="year_error" style="width:25%"> <spring:message code="Error.Required" htmlEscape="true" /> </div>
				  	</div>
				</div>
				
				<div class="form-group" id="level0tr">
					<label  class="col-sm-3 control-label" for="level0"><spring:message code="Label.entityType" htmlEscape="true" /> <span class="mandatory">*</span></label>
				  	<div class="col-sm-6">
				  									
									<form:select class="form-control" path="entityType" id="level0" onchange="clearAllLevel(0);">		
									<option value="" htmlEscape="true">--- Select ---</option>						
							    </form:select>
							    <div class="error" id="level0_error" style="width:25%"> <spring:message code="Error.Required" htmlEscape="true" /></div>
				  	</div>
				</div>
				
				<div class="form-group" id="level1tr" style="display: none">
					<label  class="col-sm-3 control-label" for="level1"><span id="spanlevel1"></span> <span class="mandatory">*</span></label>
				  	<div class="col-sm-6">
				  		<form:select class="form-control" htmlEscape="true" path="level1" id="level1" onchange="clearAllLevel(1);">	
									<option value="">--- Select ---</option>							
								</form:select>
								<div class="error" id="level1_error" style="width:25%"> <spring:message code="Error.Required" htmlEscape="true" /></div>
				  	</div>
				</div>
						
						
				<div class="form-group" id="level2tr" style="display: none">
					<label  class="col-sm-3 control-label" for="level2"><span id="spanlevel2"></span> <span class="mandatory">*</span></label>
				  	<div class="col-sm-6">
				  		<form:select class="form-control" path="level2" id="level2" onchange="clearAllLevel(2);">	
									<option value="" htmlEscape="true">--- Select ---</option>							
								</form:select>
								<div class="error" id="level2_error" style="width:25%"> <spring:message code="Error.Required" htmlEscape="true" /></div>
				  	</div>
				</div>		
						
				<div class="form-group" id="level3tr" style="display: none">
					<label  class="col-sm-3 control-label" for="level3"><span id="spanlevel3"></span> <span class="mandatory">*</span></label>
				  	<div class="col-sm-6">
				  		<form:select class="form-control" htmlEscape="true" path="level3" id="level3" onchange="clearAllLevel(3);">	
									<option value="">--- Select ---</option>							
								</form:select>
								<div class="error" id="level3_error" style="width:25%"> <spring:message code="Error.Required" htmlEscape="true" /></div>
				  	</div>
				</div>			
						
				<div class="form-group" id="level4tr" style="display: none">
					<label  class="col-sm-3 control-label" for="level4"><span id="spanlevel4"></span> <span class="mandatory">*</span></label>
				  	<div class="col-sm-6">
				  		<form:select class="form-control" htmlEscape="true" path="level4" id="level4" onchange="clearAllLevel(4);">	
									<option value="">--- Select ---</option>							
								</form:select>
								<div class="error" id="level4_error" style="width:25%"> <spring:message code="Error.Required" htmlEscape="true" /></div>
				  	</div>
				</div>		
					
				<div class="form-group" id="assignedUnittr" style="display: none">
					<label  class="col-sm-3 control-label" for="assignedUnit"><spring:message code="Label.assignedUnit" htmlEscape="true" /> <span class="mandatory">*</span></label>
				  	<div class="col-sm-6">
				  		<form:select class="form-control" htmlEscape="true" path="assignedUnit" id="assignedUnit" onchange="getStateNameOrLbDetails(this.value);">	
									<option value="">--- Select ---</option>							
								</form:select>
								<div class="error" id="assignedUnit_error" style="width:25%"> <spring:message code="Error.Required" htmlEscape="true" /></div>
				  	</div>
				</div>				
						
				<div class="form-group" id="statetr" style="display: none">
					<label  class="col-sm-3 control-label" for="stateId"><spring:message code="Label.State" htmlEscape="true" /> <span class="mandatory">*</span></label>
				  	<div class="col-sm-6">
				  		<form:select class="form-control" htmlEscape="true" path="stateId" id="stateId">	
									<option value="">--- Select ---</option>							
								</form:select>
								<div class="error" id="stateId_error" style="width:25%"> <spring:message code="Error.Required" htmlEscape="true" /></div>
				  	</div>
				</div>		
						
						
						
				<div class="box-footer">
	             	<div class="col-sm-offset-2 col-sm-10">
	                    <div class="pull-right">	
							<input type="button" id="switchunitsubmit" onclick="submitSwitchunit()" value="Submit" class="btn btn-primary" />
							<input type="reset" value="Clear" class="btn btn-primary" />
						</div>
					</div>
				</div>
				
				</div>
				</form:form>
				
	</div>
	</section>
	</div>
	</section>
			
</body>
</html>