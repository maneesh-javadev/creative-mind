<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../../common/taglib_includes.jsp"%>
<script src="resource/dashboard-resource/bootstrap/js/bootstrap.min.js"></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrDesignationService.js'></script>
<%@include file="../../homebody/commanInclude.jsp"%>
<%@include file="../../common/dwr.jsp"%>
<style>
#dialogBX {
	overflow: hidden;
}
</style>
<script >

function submitData(){
	$( '#errorddSourceState').text("");
	$( '#errSelectLocalBodyType').text("");	
	if($("#ddSourceState").val()==""){
		$( '#errorddSourceState').text("Please select State");
		return false;
	}else if($("#ddLgdLBType").val()=="0" || $("#ddLgdLBType").val()=="")
		{
		$( '#errSelectLocalBodyType').text("Please select Localbody Type");	
		return false;
		}
	else if($("#captchaAnswer").val()==""){
		$( '#errorcaptchaAnswer').text("Please enter the captcha shown above.");
		return false;
	}
	
	return true;
}

function getLocalBodyList(id) {
	
	$("#stateNameEnglish").val($('#ddSourceState option:selected').html());
	
	lgdDwrDesignationService.getLocalbodyDetail(id, {
		callback : handleLocalbodySuccess,
		errorHandler : handleLocalbodyError
	});
	$("#rurallbTypeName").val($('#ddLgdLBType option:selected').html());
}

function handleLocalbodySuccess(data) {
	// Assigns data to result id
	// alert("ti");
	// alert(data);
	var fieldId = 'ddLgdLBType';
	// alert(data);
	var valueText = 'nomenclatureEnglish';
	var nameText = 'localBodyTypeName';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddLgdLBType');
	st.add(new Option('Select Local Body', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}
function handleLocalbodyError() {
	// Show a popup message
	alert("No data found in local body!");
}

$(document).ready(function(){
$("#ddSourceState").val(); 
	getLocalBodyList($("#ddSourceState").val());
		
});



</script>
</head>
<body >
	<section class="content">
		<div class="row">
			<div class="container">
				<section class="col-lg-12 prebox">
					<form:form action="localBodyInvalidation.do"
						class="form-horizontal" id="localBodyInvalidation" method="POST"
						commandName="localBodyInvalidation" >
						<input type="hidden" name="<csrf:token-name/>"
							value="<csrf:token-value uri="localBodyInvalidation.do"/>" />
						<div class="box">
							<div class="box-header with-border">
								<h3 class="box-title">Report on Invalidated Local Bodies </h3>

								<div id="divCenterAligned">


									<div class="box-body">
										<div class="form-group">
											<label class="col-sm-4 control-label" for="ddSourceState"><spring:message
													htmlEscape="true" code="Label.SELECTSTATE"></spring:message><span class="mandatory">*</span></label>
											<div class="col-sm-6">
												<form:hidden path="stateNameEnglish" id="stateNameEnglish"/>
												<form:select path="slc" class="form-control"
													id="ddSourceState" onchange="getLocalBodyList(this.value);">
													<form:option value="">
														<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
													</form:option>
													<form:options items="${stateList}"
														itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
												</form:select>
												<span class="mandatory" id="errorddSourceState"></span>
											</div>

											<div class="form-group ">
												

												 <label
													class="col-sm-4 control-label"><spring:message
														code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message><span
													class="mandatory">*</span></label>
													
												<div class="col-sm-6">
													<form:select path="rurallbTypeName" id="ddLgdLBType"
														htmlEscape="true"  
														
														class="form-control">
														<form:option selected="selected" value=""
															label="--select--" />
													</form:select>
													<div id="errSelectLocalBodyType" class="errormsg"></div>
													<br>
												</div>
											</div>
											<div class="box-body">
												<div class="form-group">
													<label class="col-sm-4 control-label" for="sel1"><spring:message
															htmlEscape="true"
															code="Label.ENTERTEXTASSHOWNINTHISIMAGE"></spring:message>
														<span class="mandatory">*</span></label>
													<div class="col-sm-6">
														<img src="captchaImage" id="captchaImageId"
															alt="Captcha Image" border="0" />
													</div>
												</div>
												<div class="form-group">
													<label for="captchaAnswer" class="col-sm-4 control-label"><spring:message
															code="captcha.message" htmlEscape="true" /><span
														class="mandatory">*</span></label>
													<div class="col-sm-6">
														<form:input path="captchaAnswer" autocomplete="off" id="captchaAnswer"
															style="width:250px" maxlength="5" />
														<a href="#" onclick="captchaReferesh();"><i
															class="fa fa-retweet fa-2x" aria-hidden="true"></i></a><br>
														<form:errors path="captchaAnswer" cssStyle="color:red" />
                                                         <span class="mandatory" id="errorcaptchaAnswer"></span>
														<c:if
															test="${not empty captchaFaliedMessage and not captchaFaliedMessage}">
															<br />
															<label> <!-- Used Label, please dont remove. -->
															</label>
															<span class="mandatory"><strong><spring:message
																		code="captcha.errorMessage" /></strong></span>
														</c:if>
													</div>
												</div>
											</div>
											<div class="box-footer">
												<div class="col-sm-offset-2 col-sm-10">
													<div class="pull-right">
														<button type="submit" class="btn btn-success "
															id="actionFetchDetails" onclick="return submitData();">
															<i class="fa fa-floppy-o"></i>
															<spring:message htmlEscape="true" code="Button.GETDATA" />
														</button>
														<button type="button" class="btn btn-danger "
															onclick="javascript:location.href='welcome.do'">
															<i class="fa fa-times-circle"></i>
															<spring:message code="Button.CLOSE" />
														</button>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form:form>
				</section>
			</div>
		</div>
	</section>
</body>
</html>