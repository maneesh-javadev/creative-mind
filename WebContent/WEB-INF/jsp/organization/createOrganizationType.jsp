<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<script src="js/organization.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script src="js/trim-jquery.js"></script>
<script src="js/common.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>

<script type="text/javascript" language="Javascript">
	
</script>
<script language="javascript">
	if ( window.addEventListener ) { 
	    window.addEventListener( "load",loadOrgTypePage, false );
	 }
	 else 
	    if ( window.attachEvent ) { 
	       window.attachEvent( "onload", loadOrgTypePage );
	 } else 
	       if ( window.onLoad ) {
	          window.onload = loadOrgTypePage;
	 }

	
	</script>
</head>


<body onload="loadOrgTypePage();" oncontextmenu="return false"
	onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);"
	onload="loadOrgTypePage();">

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
								<center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center>
							</div>
						</td>
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
						<td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="true"></c:out></div>
						</td>
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
					<td><div class="errorfont"><spring:message htmlEscape="true" code="Error.entermandatoryfield"></spring:message></div>
					</td>
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
		<div class="frmhd" style="background: #E4E4E4; padding: 3px;">
			<h3 class="subtitle"><label><spring:message code="Label.CREATEORGTYPE" htmlEscape="true"></spring:message></label></h3>
			<ul class="listing">
				<li>
					<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /> </a>			
				</li>
			<%--//this link is not working 	<li>
					<a href="#" class="frmhelp"><spring:message code="Button.HELP" htmlEscape="true"></spring:message> </a>
				</li> --%>
			</ul>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form method="POST" commandName="createOrganizationType" action="saveOrganizationType.htm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="saveOrganizationType.htm"/>"/>
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">

							<div class="frmhdtitle">
								<spring:message code="Label.GENERALDETAILS" htmlEscape="true"></spring:message>
							</div>
							<div class="block">
								<ul class="blocklist'">
									<li>
										<label><spring:message
												code="Label.ORGTYPENAME" htmlEscape="true"></spring:message> </label> <span
										class="errormsg">*</span> <br /> <form:input htmlEscape="true"
											id="txtorgTypeName" path="orgTypeName" style="width: 200px"
											class="frmfield" onkeypress="validateAlphanumericKeys();"
											onfocus="validateOnFocus('txtorgTypeName');helpMessage(this,'txtorgTypeName_msg');"
											onblur="vlidateOnblur('txtorgTypeName','1','35','m');hideHelp();"></form:input>

										<div id="txtorgTypeName_msg" style="display: none"><spring:message
												code="error.blank.orgTypeName" htmlEscape="true"></spring:message> </div> <div
										class="errormsg" id="txtorgTypeName_error"><spring:message
												code="error.blank.orgTypeName" htmlEscape="true"></spring:message> </div> <div><form:errors
												path="orgTypeName" class="errormsg" htmlEscape="true"></form:errors> </div>
												<br/> <div><form:errors
												path="orgTypeName1" class="errormsg" htmlEscape="true"></form:errors> </div>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="btnpnl">
						<label> <input type="submit" name="Save" id="btnSave" class="btn" onclick="return vaildateOrgTypeAll();" value=<spring:message code="Button.SAVE" htmlEscape="true"></spring:message> />
						</label> <label> <input type="button" name="Submit3" class="btn" value='<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message>' id="btnClear" onclick="resetOrgTypeForm();" /> </label> <label>
						<input type="button" class="btn" name="Cancel" value='<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>' id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	
</body>
</html>
