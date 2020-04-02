
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>

<title><spring:message htmlEscape="true"  code="label.create.habitation"></spring:message></title>

<script>

callActionUrl=function(url){
 	/* document.forms['sectionForm'].action = "buildSelection.htm?<csrf:token uri='"buildSelection.htm'/>";
	document.forms['sectionForm'].method = "POST";
    document.forms['sectionForm'].submit(); */
   
    $( 'form[id=habitation]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
	$( 'form[id=habitation]' ).attr('method','post');
	$( 'form[id=habitation]' ).submit();
};

</script>

</head>



<body>
	<!-- Main Form Stylings starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message htmlEscape="true" code="label.create.habitation"></spring:message></h3>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form action="updateHabitation.htm" method="post" id="habitation" name="habitationForm" commandName="habitationForm" >
				<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="habitationForm.htm"/>" />
					<form:hidden path="habitationCode" id="habitationCode"/>
					
					<form:hidden path="habitationVersion" id="habitationVersion"/>
					
					<form:hidden path="parentType" id="parentType"/>
					
					<form:hidden path="parentCode" id="parentCode"/>
					<!-- General Details of Section Started-->
					
					<div class="form_block">
						<div class="col_1">
							<ul class="form_body">
								<li>
									<label>
										<spring:message htmlEscape="true" code="label.create.habitation"></spring:message>
										<span class="mandate">*</span>
									</label>
									<form:input path="habitationNameEnglish" id="habitationNameEnglish" maxlength="200" htmlEscape="true"  />
									<br/>
									<span>Allowed characters are A-Z,a-z,0-9,/,-,Space,comma,dot,brackets()</span>
									<span class="errormessage" id="errhabitationNameEnglish"></span>
									
									<form:errors htmlEscape="true" path="habitationNameEnglish" cssClass="error"/>
								</li>
							    <li>
									<label>
										<spring:message htmlEscape="true" code="label.habitation.name.local"></spring:message>
									</label>
									<form:input path="habitationNameLocal" id="habitationNameLocal" maxlength="200" htmlEscape="true"/>	
									<br/>
									<form:errors htmlEscape="true" path="habitationNameLocal" cssClass="error"/>							
								</li>
								</ul>
						</div>
					</div>
										
					<br/>
					
					<!-- General Details of Section Ends Here-->
					
				<br/> 
				<input class="bttn"  type="submit" value="<spring:message htmlEscape="true" code="Button.SAVE"/>" />
				<input class="bttn"  type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="callActionUrl('home.htm')"/>
			</form:form>	
		</div>
	</div>
	<!-- Page Content ends -->
	<!-- </div> -->
			
</div>
<!-- Main Form Stylings ends -->

</body>
</html>

