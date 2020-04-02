<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="css/error.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">

function setStateCode(code){
	$("#stateCode").val(code);
}

function validateState(){
	var stateVal=$("#entity").val();
	if( stateVal==""){
		alert("Please Select State")
		return false;
	}
	return true;
}

</script>

</head>
	<body onload="setStateCode('${entityName}')">
		<div class="form_stylings">
			<div class="header">
				<h3>Statewise Localbody Population Details</h3>
				
			</div>
			<div class="page_content">
				<div class="form_container">
					<div class="form_block">
						<div class="col_1">
							<ul class="form_body">
								<li>
									<label for="entity">State<font color="red">*</font></label>									
										<select name="entityName" id="entity" onchange="setStateCode(this.value);" style="width: 200px">
											<option value=""><spring:message code="Label.SEL" htmlEscape="true"></spring:message></option>
											<c:forEach var="statelist" items="${statelist}" varStatus="index">
												<option value="${statelist.statePK.stateCode}" <c:if test="${statelist.statePK.stateCode eq entityName}">selected</c:if>><c:out value="${statelist.stateNameEnglish}" escapeXml="true"></c:out></option>
											</c:forEach>
										</select>
									   <div id="errorentity"></div>
								  </li>
								</ul>
						</div>
					</div><br></br>
					<form:form  method="POST" action="stateWisePopulation.do" commandName="viewDeptforadmin">
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="stateWisePopulation.do"/>"/>
						<input type="hidden" id="stateCode" name="code" value="">
						<input type="submit"  value="Get Data" onclick="return validateState();"></input>
					</form:form>
					</div>
					<c:if test="${dataExists}">
                       	<div align="center">
                       		<% java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a"); %>
                        	<iframe width="100%" height="500" src="${serverLoc}?__report=${report_design}&stateName=${stateName}&stateCode=${entityName}&rpt_curr_dt_tm=<%=df.format(new java.util.Date())%>"></iframe>           
						</div>
					</c:if>
				</div>
				
			</div>
                       
		
	</body>
</html>