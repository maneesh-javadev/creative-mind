<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="../common/taglib_includes.jsp"%>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script src="js/common.js"></script>
<script src="js/createDepartment.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="javascript">
$(document)
.ready(
		function() {
			$("#modifyOrg")
					.validate(
							{
								rules : {
									orgName : {
										required : true,
										onlyLetterSpace : true
									},
									orgNameLocal : {
										nameFormatLocal : true
									},
									shortName : {
										shortName : true
									}
								},
								messages : {
									orgName : {
										required : "<font color='red'><br><spring:message code='error.required.ORGNAME'/></font>",
										onlyLetterSpace : "<font color='red'><br><spring:message code='error.invalid.format' text='Please enter value in correct format'/></font>"
									},
									orgNameLocal : {
										nameFormatLocal : "<font color='red'><br><spring:message code='error.invalid.format' text='Please enter value in correct format'/></font>"
									},
									shortName : {
										shortName : "<font color='red'><br><spring:message code='error.common.short.name' text='Short Name is invalid'/></font>"
									}
								}
							});
		});
function save() {
	var bool = $("#modifyOrg").validate().form();
	if (bool) {
		var orgNameDb = document.getElementById("orgNamech");
		var orgName		 = document.getElementById("orgName");
		if(orgName == orgNameDb) {
			document.getElementById("orgNamech").value = "";
		}
		document.forms['modifyOrg'].submit();
	}
}

</script>
</head>
<body onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">

	<div class="overlay" id="overlay1" style="display: none;"></div>
	<div class="box" id="box1">
		<a class="boxclose" id="boxclose1"></a>
		<div>
			<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<div class="success"></div>
							</center></td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div></td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center>
									<c:out value="${family_msg}" escapeXml="true"></c:out>
								</center>
							</div></td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><div class="failur"></div></td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center></td>
					</tr>
					<tr>
						<td><div id="failurMsg" class="errorfont">
								<c:out value="${family_error}" escapeXml="true"></c:out>
							</div></td>
					</tr>
				</table>

			</c:if>

		</div>
	</div>

	<div class="box" id="box">
		<a class="boxclose" id="boxclose"></a>
		<div id="validate_error">
			<table>
				<tr>
					<td rowspan="2"><div class="errorImg"></div></td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div></td>
				</tr>
				<tr>
					<td><div class="errorfont"><spring:message htmlEscape="true" code="Error.entermandatoryfield"></spring:message></div></td>
				</tr>
			</table>

		</div>
	</div>
	<div id="frmcontent">
		<div class="frmhd">
					<h3 class="subtitle"><spring:message code="Label.MODIFYORG" htmlEscape="true" text="Modify Organization"></spring:message></h3>
										 <ul id="showhelp" class="listing">
					 				      		<%--//these links are not working  <li>
					 				      					<a href="#" rel="toggle[cat]"	data-openimage="images/minus.jpg"	data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /></a>
					 				      		
					 				      		</li>
					 				        
					 				        <li>
					 				        			<a href="#" class="frmhelp"><spring:message htmlEscape="true" 	code="Button.HELP"></spring:message> </a>
					 				        </li> --%>
					 	
					 			        </ul>
		
		
		
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="modifyOrganizationCentralforpost.htm" method="POST" commandName="orgTypeForm" id="modifyOrg">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyOrganizationCentralforpost.htm"/>" />
				<form:input type="hidden" path="orgCode" name="orgCode" />
				<form:input type="hidden" path="orgTypeCode" name="orgTypeCode" />
				<div id="cat">
					<div class="frmpnlbg">
					<br/>
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message code="Label.GENERALDETAILS" htmlEscape="true"></spring:message>
										</div>
										<div>
											<ul class="blocklist">
													<li><spring:message code="Label.ORGNAMEINENG" htmlEscape="true"></spring:message> <span class="errormsg">*</span> <br />
												 	<form:input path="orgName" id="orgName" style="width: 200px" class="frmfield" onblur="('orgName','1','15','c');" maxlength="200" htmlEscape="true"></form:input>
												 	<form:hidden path="orgNamech" id="orgNamech" value="${orgTypeForm.orgName}"/></li>
													<li><form:errors htmlEscape="true" path="orgName" cssClass="errormsg"></form:errors></li>
													<li>
														<spring:message code="Label.ORGNAMEINLOCAL" htmlEscape="true"></spring:message> <br />
														<form:input path="orgNameLocal" style="width: 200px" class="frmfield" maxlength="80" htmlEscape="true"></form:input>
													</li>
													<li><form:errors htmlEscape="true" path="orgNameLocal" cssClass="errormsg"></form:errors></li>
													
													<li>
															<spring:message code="Label.ORGSHORTNAMEINENG" htmlEscape="true"></spring:message> <br />
												<form:input path="shortName" style="width: 200px" class="frmfield" maxlength="10" htmlEscape="true"></form:input>
													</li>
													<li>
														<form:errors htmlEscape="true" path="shortName" cssClass="errormsg"></form:errors>
													</li>
											</ul>
										
										</div>
										
										<div class="clear"></div>
									</div>
							
					</div>
					<div class="btnpnl">
								<label> <input type="submit" name="Submit" class="btn" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" onclick="return save();" />
								</label> <label> <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>" id="btnClear" onclick="onClear();" />
								</label> <label> <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose" />
								</label>
					</div>

				</div>
			</form:form>
			<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>
	</td>

</body>
</html>