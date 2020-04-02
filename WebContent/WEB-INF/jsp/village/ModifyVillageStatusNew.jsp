<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" ng-app="publicModule">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<script>
	var isSelect = null;
	var selectobject;
	$(document).ready(function() {
		//isState= document.getElementById('r1').checked;
		//isSelect = document.getElementById("mytable").rows[2].cells.length;
		isSelect = document.getElementById('selectcol');
	});
</script>


<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<%-- <%@include file="../common/taglib_includes.jsp"%> --%>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script src="js/validation.js"></script>
<script src="js/orderValidate.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>

<title>Insert title here</title>


<script>
	callActionUrl = function(url) {
		$('form[id=frmModifyVillage]').attr('action',
				url + '?<csrf:token uri="' + url + '"/>');
		$('form[id=frmModifyVillage]').attr('method', 'post');
		$('form[id=frmModifyVillage]').submit();
	};

	function updatesave() {
		errorFlag = 1;
		var department = $('#villagestatus').val();
		if (department == "Select")
		//errorFlag =0;
		{
			$('#err_tdMinDepartment').html(
					"Please Select New Status of the Village");
			return false;

		}
		if (errorFlag == 1) {
			document.forms['villageStatusForm'].method = "POST";
			document.forms['villageStatusForm'].action = "saveModifyVillageStatus.htm";
			document.forms['villageStatusForm'].submit();

		}
	}
</script>
</head>
<body>

	<section class="content">
	<div class="row">
		<section class="col-lg-12">
		<div class="box">

			<div class="box-header with-border">
				<h3 class="box-title">
					<spring:message code="LABEL.CHANGEVILLAGESTATUS" htmlEscape="true"></spring:message>
				</h3>
			</div>

			<form:form class="form-horizontal"
				action="saveModifyVillageStatus.htm" method="POST"
				onsubmit="return updatesave();" commandName="modifyVillageCmd"
				id="villageStatusForm">
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="modifyVillageStatus.htm"/>" />



				<c:forEach var="listVillageDetails"
					varStatus="listVillageDetailsRow"
					items="${modifyVillageCmd.listVillageDetails}">
					<div class="box-body">
						<table class="table table-bordered table-hover" id="mytable">

							<tr>
								<td width="30%"><spring:message htmlEscape="true"
										code="Label.VILLAGECODE"> : </spring:message></td>
								<td width="50%"><spring:bind
										path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageCode">&nbsp;
							 <c:out value="${status.value}" escapeXml="false" />
									</spring:bind></td>
								<input type="hidden" name="villageCodeVal"
									value="${listVillageDetails.villageCode}" />
							</tr>
							<tr>
								<td><spring:message htmlEscape="true"
										code="Label.VILLAGENAMEINENGLISH"> : </spring:message></td>
								<td><spring:bind
										path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameEnglish">&nbsp;
								 <c:out value="${status.value}" escapeXml="false" />
									</spring:bind></td>
							</tr>

							<tr>
								<td><spring:message htmlEscape="true"
										code="Label.VILLAGENAMEINLOCAL"> : </spring:message></td>
								<td><spring:bind
										path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameLocal">&nbsp;
								 <c:out value="${status.value}" escapeXml="false" />
									</spring:bind></td>
							</tr>
							<tr>
								<%-- <td><spring:message htmlEscape="true" code="Label.VILLAGESTATUS"> : </spring:message></td>
				        <td ><spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageStatus">&nbsp;
								 <c:out value="${status.value}" escapeXml="false"/></spring:bind></td> --%>

								<td><spring:message htmlEscape="true" code="Label.VILLAGESTATUS"> : </spring:message></td>
								<td><spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageStatus">&nbsp;</spring:bind>
									<c:if test="${listVillageDetails.villageStatus eq 'I'}">
										<%-- <spring:message htmlEscape="true" code="" text="Inhabitant"/> --%>
										<c:out value="Inhabitant"></c:out>
									</c:if> 
									<c:if test="${listVillageDetails.villageStatus eq 'U'}">
										<%-- <spring:message htmlEscape="true" code="" text="Un-inhabitant"/> --%>
										<c:out value="Un-inhabitant"></c:out>
									</c:if> 
									<c:if test="${listVillageDetails.villageStatus eq 'R'}">
										<%--  <spring:message htmlEscape="true" code="" text="Forest"/> --%>
										<c:out value="Forest"></c:out>
									</c:if>
								</td>


							</tr>


						</table>



					</div>
				</c:forEach>

				<div class="box-header subheading">
					<h4 class="box-title">
						<spring:message code="LABEL.MODIFYVILLAGESTATUS" htmlEscape="true"></spring:message>
					</h4>
				</div>





				<div class="form-group">
					<label class="col-sm-3 control-label"><spring:message
							code="Label.VILLAGESTATUSNEW" htmlEscape="true"></spring:message></label>
					<div class="col-sm-3">
						<form:select path="villageStatus" cssClass="form-control"
							id="villagestatus">
							<option value="Select"><spring:message
									code="Label.SELECT" htmlEscape="true"></spring:message></option>
							<c:forEach var="statuslist" items="${village_details}">
								<c:if test="${statuslist  ne village_details_seletced }">
									<c:if test="${statuslist  eq 'R' }">
										<option value="${statuslist}">Forest Village</option>
									</c:if>
									<c:if test="${statuslist  eq 'U' }">
										<option value="${statuslist}">Un-inhabitant</option>
									</c:if>
									<c:if test="${statuslist  eq 'I' }">
										<option value="${statuslist}">Inhabitant</option>
									</c:if>
								</c:if>
							</c:forEach>
						</form:select>
					</div>
					<div class="col-sm-3">
						<div id="err_tdMinDepartment" class="mandatory"></div>
					</div>

				</div>
				<div class="box-footer">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="pull-right">
							<button type="button" name="Submit" class="btn btn-primary"
								onclick="return updatesave();">
								<i class="fa fa-floppy-o"></i>
								<spring:message code="Button.SP" htmlEscape="true"></spring:message>
							</button>
							<button type="button" name="Submit3" class="btn btn-danger"
								onclick="javascript:location.href='viewvillage.htm?<csrf:token uri='home.htm'/>';">
								<i class="fa fa-times-circle"></i>
								<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>
							</button>
						</div>
					</div>
				</div>



			</form:form>
			<script src="/LGD/JavaScriptServlet"></script>
		</div>
		</section>
	</div>
	</section>

</body>
</html>
