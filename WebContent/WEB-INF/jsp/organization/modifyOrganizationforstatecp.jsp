<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%@include file="../common/taglib_includes.jsp"%>
<head>
<!-- <script src="js/createDepartment.js"></script> -->
<!-- <script src="js/validation.js"></script> -->
<!-- <script src="js/successMessage.js"></script> -->
<!-- <script src="js/helpMessage.js"></script> -->
<!-- <script src="js/trim-jquery.js"></script> -->
<!-- <link href="css/successMessage.css" rel="stylesheet" type="text/css" /> -->


<script type="text/javascript"></script>

<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>	
</script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>

<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<%-- <script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script> --%>
<%-- <script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script> --%>
<%-- <script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script> --%>
<%-- <script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script> --%>
<script src="js/common.js">dwr.engine.setActiveReverseAjax(true);</script>


<script type="text/javascript" language="javascript">

function showHideDiv(){
	document.getElementById('errorDiv').style.display = 'none';
	if($('#selectOrganisationLevel').val() !=''){
		document.getElementById('newNameForOrganisation').style.display = 'block';
		document.getElementById('footerDiv').style.display = 'block';
		$('#orgName').val($('#selectOrganisationLevel').val().split('_')[1]);
	}else{
		$('#orgName').val('');
		document.getElementById('footerDiv').style.display = 'none';
		document.getElementById('newNameForOrganisation').style.display = 'none';
	}
}

function onClear(){
	$('#selectOrganisationLevel').val('')
	$('#orgName').val('');
}

function isNext(id){
	document.getElementById('btnSave').value="Save";
	if (document.getElementById(id).checked) {
		document.getElementById('btnSave').value="Next";
	} 
}

function saveEntity(){
	document.submitChnageOrgName.method = "post";
	document.submitChnageOrgName.action = "modifyOrganizationStatepost.htm?<csrf:token uri='modifyOrganizationStatepost.htm'/>";
	document.submitChnageOrgName.submit();
}
</script>
</head>
<body onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
<section class="content">
	<div class="row">
		<section class="col-lg-12">
			<form:form name="submitChnageOrgName" id="submitChnageOrgName" action="modifyOrganizationStatepost.htm" method="POST" class="form-horizontal" commandName="modifyOrganizationstate" onsubmit="Submit.disabled = true; return true;"  >
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyOrganizationStatepost.htm"/>"/>
				<%-- <input type="hidden" name="orgCode" value="${modifyOrganizationstate.orgCode}"/> --%>
				<form:hidden path="orgCode" />
			
					<div class="box">
						
						<div class="box-header with-border">
							<h3>Rename Department/Organization Name</h3>
						</div>

						<div class="box-body">
							<div class="form-group" id="td_adminunit">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true" text="Select Level"></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6" >
									<form:select onchange="showHideDiv()" path="orgNameAndLevel" class="form-control" id="selectOrganisationLevel">
										<form:option value="">--Select--</form:option>
										<c:forEach items="${organisationAtLevels}" var="organisation">
									  		<form:option value="${organisation[0]}_${organisation[2]}_${organisation[1]}_${organisation[3]}">${organisation[2]} -&nbsp; ${organisation[1]}</form:option>
									  </c:forEach>
									</form:select>
								</div>
							</div>

							<div class="form-group" id="newNameForOrganisation" >
								<label  class="col-sm-3 control-label" ><c:out value="Enter new name for Organization" escapeXml="true"/><span class="mandatory">*</span></label>									
										<div class="col-sm-6">
											<form:input path="orgName" id="orgName" maxlength="200"  class="form-control" htmlEscape="true"></form:input>
										</div>	
							</div>
		
							<div class="form-group" id="errorDiv">
								<label  class="col-sm-3 control-label" ></label>	
								<div class="col-sm-6">
											<c:if test="${hasError}">
												<form:errors htmlEscape="true"  path="orgName"	class="errormsg"></form:errors>
											</c:if>
										</div>
								
							</div>
					
						</div>
						
						<div class="box-footer" style="display: none;" id="footerDiv">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="pull-right">
									<button type="button" name="Submit" class="btn btn-success" onclick="saveEntity()"><spring:message htmlEscape="true"  code="Button.SAVE"></spring:message></button>
									<button type="button" name="Submit3" class="btn btn-warning"  id="btnClear" onclick="onClear();" ><spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button>
									<button type="button" class=" btn btn-danger"onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><spring:message code="Button.CLOSE"/></button>
								</div>
							</div>
						</div>
						
					</div>
			</form:form>
		</section>
	</div>
</section>

</body>
</html>