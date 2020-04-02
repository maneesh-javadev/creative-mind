<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />

<%!String contextPath;%>
<%
	contextPath = request.getContextPath(); 
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="css/error.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	#tbl td{
		padding: 10px 10px 10px 10px;
	}
</style>
<script type="text/javascript">

function setStateCode(code){
	$("#stateCode").val(code);
	$("#entity").val(code);
}

function validateState(){
	var stateVal=$("#entity").val();
	if( stateVal==""){
		alert("Please Select State")
		return false;
	}
	$("#captchaAnswer").val($("#captcha").val());
	return true;
}

</script>




</head>
	<body onload="setStateCode('${entityName}')">
		<div class="form_stylings">
			<div class="header">
				<h3>National Optical Fibre Network Panchayat</h3>
			</div>
			<div class="page_content">
				<div class="form_container">
					<div class="form_block">
						<div class="col_1">
							<table style="width: 70%;" id="tbl">
								<tr>
									<td><label for="entity" style="display: inline;">State<font color="red">*</font></label></td>
									<td>
										<select name="entityName" id="entity" onchange="setStateCode(this.value);" style="width: 200px">
												<option value=""><spring:message code="Label.SEL" htmlEscape="true"></spring:message></option>
												<c:forEach var="statelist" items="${statelist}" varStatus="index">
													<option value="${statelist[0]}"><c:out value="${statelist[1]}" escapeXml="true"></c:out></option>
												</c:forEach>
											</select>
									</td>
								</tr>
								<tr>
									<td></td>
									<td>
										<img src="captchaImage" alt="Captcha Image" />
									</td>
								</tr>
								<tr>
									<td>
										<label>
											<spring:message code="captcha.message"	htmlEscape="true" />
											<span class="mandate">*</span>
										</label>
									</td>
									<td>
										<input type="text" id="captcha" autocomplete="off" width="10%"/>
										<c:if test="${not empty captchaFaliedMessage and not captchaFaliedMessage}">
											<br/><label><!-- Used Label, please dont remove. --></label>
											<span class="errormsg"><strong><spring:message code="captcha.errorMessage"/></strong></span>
										</c:if>
									</td>
								</tr>
							</table>
						</div>
					</div>
					
					<form:form  method="POST" action="nofnStates.do" commandName="viewDeptforadmin">
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="nofnStates.do"/>"/>
						<input type="hidden" id="stateCode" name="code" value="">
						<input type="hidden" id="captchaAnswer" name="captchaAnswer" value="">
					    <label class="inline">&nbsp;</label>
					    <div align="center" style="margin-left: -150px;">
						<input type="submit" onclick="return validateState();"  value="<spring:message htmlEscape="true" code="Button.GETDATA"/>" />
						<input type="button" value="<spring:message code="Button.CLOSE"/>" onclick="javascript:location.href='welcome.do'"/>	
						</div>
					</form:form>
					<br></br>
					
					</div>
					
				</div>
				
			</div>
                       
		
	</body>
</html>