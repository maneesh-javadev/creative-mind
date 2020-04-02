<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Create Ministry</title>
<%@include file="../common/taglib_includes.jsp"%>
<script src="js/common.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/organization.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script>
/* $(document)
.ready(
		function() {
			$("#ministryForm")
					.validate(
							{
								rules : {
									ministryName : {
										required : true,
										onlyLetterSpace : true
									},
									shortministryName : {
										shortName : true
									}
								},
								messages : {
									ministryName : {
										required : "<font color='red'><br><spring:message code='MINISTRYNAME.REQUIRED' text='Please enter ministry name'/></font>",
										onlyLetterSpace : "<font color='red'><br><spring:message code='error.valid.ministryName' text='Please enter value in correct format'/></font>"
									},
									shortministryName : {
										shortName : "<font color='red'><br><spring:message code='error.common.short.name' text='Short Name is invalid'/></font>"
									}
								}
							});
		}); */
		
		$(document).ready(function() {	
			$("#btnSave").click(function() {	
				clearFormErrors();
				var ministryName = $("#txtMinistryName").val();	
				var ministryShortName = $("#txtshortministryName").val();
				if(ministryName ==''){
					$('#ministryName_error').html("Please Enter Ministry Name");
					return false;
				}
				else{
					var nameReg = /^[a-zA-Z\s]+$/;
					if (!nameReg.test(ministryName)){
					$('#ministryName_error').html("Please Enter value in Correct format");
					return false;
				}
				}
				if(ministryShortName != ''){
					var nameReg = /^[a-zA-Z\s]+$/;
					if (!nameReg.test(ministryShortName)){
					$('#shortministryName_error').html("Short Name is invalid");
					return false;
				}
				}
				return true;
			});
		});
		function clearFormErrors(){
			$('#ministryName_error').html("");
			$('#shortministryName_error').html("");
		}
</script>
</head>

<body onload="loadMinistryPage();" ononcontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
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
									<c:out value="${param.family_msg}" escapeXml="true"></c:out>
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

	<div id="helpDiv" class="helpMessage">
		<div class="helpheader">
			<h4>Help Message</h4>
		</div>
		<div id="helpMsgText" class="helptext"></div>
	</div>

	<div id="frmcontent">
		<div class="frmhd">
		
						<h3 class="subtitle"><spring:message code="Label.CREATEMINISTRY" htmlEscape="true"></spring:message></h3>
										 <ul id="showhelp" class="listing">
					 				      		
					 				        
					 				       <%--//these links are not working  <li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /> </a></li>
					 				        
					 				        <li>
					 				        			<a href="#" class="frmhelp"><spring:message htmlEscape="true" 	code="Button.HELP"></spring:message> </a>
					 				        </li> --%>
					 	
					 			        </ul>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form method="POST" commandName="createMinistry" action="saveMinistryDetails.htm" id="ministryForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="saveMinistryDetails.htm"/>" />
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.GENERALDETAILS" htmlEscape="true"></spring:message>
							</div>
							
							<div  >
								<ul class="blocklist">
								
										<li>
										<label><spring:message code="Label.MINISTRYNAME" htmlEscape="true"></spring:message> </label> <span class="errormsg">*</span> <br />
										<form:input id="txtMinistryName" path="ministryName" style="width: 300px"	maxlength="200" class="frmfield" onfocus="helpMessage(this,'txtMinistryName_msg');" onblur="hideHelp();" htmlEscape="true"></form:input>
										<span id="txtMinistryName_msg" style="display: none"><spring:message code="error.blank.ministryName" htmlEscape="true"></spring:message> </span>
										<span class="errormsg" id="txtMinistryName_error"><spring:message code="error.blank.ministryName" htmlEscape="true"></spring:message> </span>
										<span><form:errors path="ministryName" class="errormsg" htmlEscape="true"></form:errors></span>
										<span> <form:errors path="ministryName1" class="errormsg" htmlEscape="true"></form:errors></span>
										<span class="errormsg" id="ministryName_error"></span>
										</li>
										<li>
										<label><spring:message code="Label.MINISTRYSHORTNAME" htmlEscape="true"></spring:message> </label> <br />
										<form:input id="txtshortministryName" path="shortministryName" htmlEscape="true" style="width: 120px" maxlength="10" class="frmfield"></form:input>
										<span class="errormsg" id="shortministryName_error"></span>
										<span><form:errors path="shortministryName" class="errormsg" htmlEscape="true"></form:errors></span>
										
										</li>
										<li></li>
								
								
								</ul>
								<div class="clear"></div>
							
							</div>
							
							
						</div>
						<div class="btnpnl">
						
								<label> <input type="submit" name="Save" id="btnSave" class="btn" value='<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>'/>
									</label><label> <input type="button" name="Submit3" class="btn" value='<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message>' id="btnClear" onclick="resetMinistryForm();" />
									</label><label> <input type="button" name="Cancel" class="btn" value='<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>' id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
									</label>
						
							
						</div>
					</div>
			</form:form>
		</div>
	</div>
</body>
</html>