<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<script type="text/javascript" src="js/cancel.js"></script>

<title><spring:message code="Label.MODIFYLGTYPE"
		htmlEscape="true"></spring:message>
</title>
<head>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<script src="js/govtorder.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />


<script src="js/common.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>
	
</script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script src="js/dynCalendar.js" type="text/javascript"></script>
<script src="js/browserSniffer.js" type="text/javascript"></script>
<script type="text/javascript" src="js/common.js"></script>
<link href="css/dynCalendar.css" rel="stylesheet" type="text/css" />
<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<link rel="stylesheet" href="datepicker/demos.css" /> -->

<script src="js/createlocalgovttype.js" type="text/javascript">
	dwr.engine.setActiveReverseAjax(true);
</script>

<script type="text/javascript">
	
function setValue(category)
{
	alert("hi");
	var category=document.getElementById('category').value;
	var level=document.getElementById('listGovtTypeDetail[0].level').value;
	alert(category);
	alert(level);
/* if(category=="P")
	{ */
	document.getElementById('ddRuralCategory').value=category;
	document.getElementById('ddlevel').value=level;
	
/* 	} */
	
}
function loadPage() {
		var mypath = window.location.href;
		var mySplitResult = mypath.split("&");
		if (mySplitResult[1] != "") {
			var type = mySplitResult[1].replace("type=", "");
		}
	}

	function disableLevel() {
		if (document.getElementById('rdoPRI').checked) {
			document.getElementById('ddlevel').disabled = true;
		}
		if (document.getElementById('rdoTrad').checked) {
			document.getElementById('ddlevel').disabled = false;
			document.getElementById('rdoPRI').disabled = true;
		}

	}
	function hidediv() {
		var ruralId = document.getElementById('ruralId');
		if (ruralId.checked) {
			document.getElementById('divRCategory').style.display = 'block';
		} else {
			document.getElementById('divRCategory').style.display = 'none';
		}
	}

	function showdiv(id) {

		if (id == 'R') {
			document.getElementById('divRCategory').style.display = 'block';
		} else {
			document.getElementById('divRCategory').style.display = 'none';
		}
	} 
	function onSave() {
		var errors = new Array();

		var error = false;
		errors[0] = !officialAddress();

		if (errors[0] == true) {
			error = true;
		}

		if (error == true) {

			showClientSideError();

			return false;
		} else {
			return true;
		}

		return false;
	}

	function getLevel() {
		if (document.getElementById('txtLevel').value == "D")
			document.getElementById('ddlevel').selectedIndex = 1;
		else if (document.getElementById('txtLevel').value == "I")
			document.getElementById('ddlevel').selectedIndex = 2;
		else if (document.getElementById('txtLevel').value == "V")
			document.getElementById('ddlevel').selectedIndex = 3;

		if (document.getElementById('urbanId').checked)
			showdiv('U');
	}
	function officialAddress() {

		if (document.getElementById("OfficialAddress").value == "") {
			document.getElementById("OfficialAddress_error").innerHTML = "Local Government Type Name is Required";
			$("#OfficialAddress_error").show();
			$("#OfficialAddress_msg").hide();
			$("#OfficialAddress").addClass("error_fld");
			$("#OfficialAddress").addClass("error_msg");
			return false;

		} else {
			$("#OfficialAddress_msg").hide();
			return true;

		}
	}
</script>

</head>
<body onload="getLevel();loadModifyLBTypePage();" 
	onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);">
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
					<td><div class="errorfont"><spring:message htmlEscape="true" code="Error.entermandatoryfield"></spring:message></div>
					</td>
				</tr>
			</table>

		</div>
	</div>
	<div id="frmcontent" onload="load_page();hidediv();">
		<div class="frmhd">
			<h3 class="subtitle">
				<spring:message htmlEscape="true" code="Label.MODIFYLGTYPE"></spring:message>
			</h3>
			<ul class="listing">
				<li>
					<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a>
				</li>
				<%--//this link is not working <li> 
					<a href="#" class="frmhelp"><spring:message code="Label.HELP"></spring:message></a>
				</li> --%>
			</ul>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="draftModifyLocalgovtType.htm" method="POST"
				commandName="modifyLocalGovtTypeCmd" id="frmModifyVillage"
				enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="draftModifyLocalgovtType.htm"/>" />
					
					<%-- <table><tr><td> fdgdfg ${modifyLocalGovtTypeCmd.correction} </td></tr></table> --%>
		
					 <c:if test="${modifyLocalGovtTypeCmd.correction==true}">
						<div class="frmpnlbg">
							<div class="frmtxt">
								<div class="frmhdtitle">
							
									<spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message>
								</div>
								
								
								 <c:forEach var="listdetail" varStatus="listLocalGovtTypeRow"
					items="${modifyLocalGovtTypeCmd.listdetail}">
					
						<div class="frmpnlbg">
							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message code="Label.CHANGE" htmlEscape="true"></spring:message>
								</div>
								 <!-- <table width="100%" class="tbl_no_brdr">
									<tr>
										<td width="14%" rowspan="10"> -->
									<ul class = "listing">
										<li>
											<form:hidden path="govtOrderConfig" htmlEscape="true" value="${govtOrderConfig}"/>
										</li>
										<li>
											<label for = "txtlocalBodyTypeName"> <spring:message code="Label.NewnameLBT" htmlEscape="true"></spring:message>
										<%-- <spring:message
													code="Label.NAMELOCALGOVTTYPE" htmlEscape="true"></spring:message> --%>
										</label><span class="errormsg">*</span><br /> <label> <spring:bind
													path="modifyLocalGovtTypeCmd.listdetail[${listLocalGovtTypeRow.index}].localBodyTypeName">
													<input type="text" class="frmfield"
														name="<c:out value="${status.expression}"></c:out>"
														value="<c:out value="${status.value}" escapeXml="true"/>"
														id="txtlocalBodyTypeName" cssClass="frmfield"
														onfocus="show_msg('txtlocalBodyTypeName')"
														onblur="validateLbType()" />
													<div class="error" id="txtlocalBodyTypeName_error"></div>
												</spring:bind> <spring:bind
													path="modifyLocalGovtTypeCmd.listdetail[${listLocalGovtTypeRow.index}].localBodyTypeCode">
													<input type="hidden"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
												</spring:bind> </label>
											<div class="errormsg"></div>
										</li>
										<li>
											<label for = "ruralId">
												<spring:message code="Label.CATLOCALGOVTTYPE" htmlEscape="true"></spring:message>
											</label>
										</li>
										<li>
											<spring:bind
												path="modifyLocalGovtTypeCmd.listdetail[${listLocalGovtTypeRow.index}].category">
												<input type="radio"
													name="<c:out value="${status.expression}"/>" value="R"
													<c:if test="${status.value == 'R'}">checked</c:if>
													id="ruralId" onclick="showdiv(this.value);" />
											</spring:bind> <spring:message code="Label.RURALG" htmlEscape="true"></spring:message>&nbsp;
											&nbsp; <spring:bind
												path="modifyLocalGovtTypeCmd.listdetail[${listLocalGovtTypeRow.index}].category">
												<input type="radio"
													name="<c:out value="${status.expression}"/>" value="U"
													<c:if test="${status.value == 'U'}">checked</c:if>
													id="urbanId" onclick="showdiv(this.value);" />
											</spring:bind> <spring:message code="Label.ULG" htmlEscape="true"></spring:message> <div id="urbanId_error" class="errormsg"><spring:message code="Label.SELECTRURALBODY" htmlEscape="true"></spring:message></div>
										</li>
									</ul>
									<div id="divRCategory">
												<!-- <table width="100%" class="tbl_no_brdr">
													<tr>
														<td width="86%"> -->
													<ul class = "blocklist">
													<li>
														<spring:bind
																path="modifyLocalGovtTypeCmd.listdetail[${listLocalGovtTypeRow.index}].ispartixgovt">
																<input type="radio"
																	name="<c:out value="${status.expression}"/>"
																	value="true" id="rdoPRI" onclick="disableLevel();"
																	<c:if test="${status.value == true}">checked</c:if> />
															</spring:bind><!-- <div id="rdoPRI_error">  --><spring:message code="Label.PRI" htmlEscape="true"></spring:message><!-- </div> -->&nbsp;
															&nbsp; 
															<spring:bind
																path="modifyLocalGovtTypeCmd.listdetail[${listLocalGovtTypeRow.index}].ispartixgovt">
																<input type="radio"
																	name="<c:out value="${status.expression}"/>"
																	value="false" id="rdoTrad" onclick="disableLevel();"
																	<c:if test="${status.value == false}">checked</c:if> />
															</spring:bind>
															<spring:message code="Label.TRADITIONALBODY" htmlEscape="true"></spring:message>  <div id="rdoTrad_error" class="errormsg"><spring:message code="Label.SELECTRURALBODY"
																htmlEscape="true"></spring:message></div>
													</li>
													<li>
														<label for = "ddlevel"><spring:message
																	code="Label.LEVEL" htmlEscape="true"></spring:message>
														</label><span class="errormsg">*</span> <br /> <form:select
																path="level" id="ddlevel" class="combofield">
																<form:option value="S">
																	<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
																</form:option>
																<form:option value="D">
																	<spring:message code="Label.DISTRICT" htmlEscape="true"></spring:message>
																</form:option>
																<form:option value="I">
																	<spring:message code="Label.INTERMEDIATE"
																		htmlEscape="true"></spring:message>
																</form:option>
																<form:option value="V">
																	<spring:message code="Label.VILLAGE" htmlEscape="true"></spring:message>
																</form:option>
															</form:select><div
														class="errormsg" id="ddlevel_error"> <spring:message
																code="error.blank.level" htmlEscape="true"></spring:message> </div>
														
														<form:errors path="level" cssClass="errormsg"
															htmlEscape="true"></form:errors>
													</li>
													<li>
														<br /> <label> <spring:bind
																	path="modifyLocalGovtTypeCmd.listdetail[${listLocalGovtTypeRow.index}].level">
																	<input type="hidden" id="txtLevel" maxlength="1"
																		class="frmfield"
																		name="<c:out value="${status.expression}"/>"
																		value="<c:out value="${status.value}" escapeXml="true"></c:out>" />

																</spring:bind> </label>
															<div class="errormsg"></div>
													</li>
													<!-- </tr>
												</table> -->
											</ul>
											</div>
							</div>

							<div class="btnpnl">
								<label> <input type="submit" name="Submit"
										class="btn"
										value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>"
										onclick="return validateAllforModify();" /> </label> <input
									type="button" name="Submit3" class="btn"
									value='<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message>'
									id="btnClear" onclick="resetModifyForm();" /> <label>
										<input type="button" name="Submit3" class="btn"
										value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
										onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
								</label>
							</div>
						</div>
					
				</c:forEach> 
								
									<%--  <c:forEach var="listGovtTypeDetail"
										varStatus="listLocalGovtTypeRow" 
										items="${modifyLocalGovtTypeCmd.listGovtTypeDetail}">	
									
								<table width="100%" class="tbl_no_brdr">

									<tr>
										<td width="14%" rowspan="4">&nbsp;</td>
										<td width="86%"><label><spring:message
													code="Label.NAMELOCALGOVTTYPE" htmlEscape="true"></spring:message>
										</label><span class="errormsg">*</span><br /> <label> <spring:bind
													path="modifyLocalGovtTypeCmd.listGovtTypeDetail[${listLocalGovtTypeRow.index}].localBodyTypeName">
													<input type="text" onkeypress="validateCharKeys(event)"
														maxlength="50" class="frmfield"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="false"/>"
														id="OfficialAddress" cssClass="frmfield"
														onfocus="show_msg('OfficialAddress')"
														onblur="officialAddress()" />
															<input type="hidden" name="category" id="category" value="${modifyLocalGovtTypeCmd.category}" />
															
													<span class="error" id="OfficialAddress_error"></span>
												</spring:bind> <form:errors path="localBodyTypeName" class="errormsg"
													htmlEscape="true"></form:errors>
												
												<div class="errormsg"></div> <spring:bind
													path="modifyLocalGovtTypeCmd.listGovtTypeDetail[${listLocalGovtTypeRow.index}].category">
													<input type="hidden"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="false"/>" />
												</spring:bind> <spring:bind
													path="modifyLocalGovtTypeCmd.listGovtTypeDetail[${listLocalGovtTypeRow.index}].level">
													<input type="hidden"
													    id="<c:out value="${status.expression}"/>"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="false"/>" />
												</spring:bind> <spring:bind
													path="modifyLocalGovtTypeCmd.listGovtTypeDetail[${listLocalGovtTypeRow.index}].localBodyTypeCode">
													<input type="hidden"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="false"/>" />
												</spring:bind> <spring:bind
													path="modifyLocalGovtTypeCmd.listGovtTypeDetail[${listLocalGovtTypeRow.index}].ispartixgovt">
													<input type="hidden"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="false"/>" />
												</spring:bind> <c:out value="${listGovtTypeDetails.sixthSchedulGovt}"></c:out>

										</label>
											<div class="errormsg"></div></td>
									</tr>
									
									
									
												
								</table>
								</c:forEach>  --%>
								
								<%-- <table width="100%" class="tbl_no_brdr">
								<tr>
													<td width="14%"></td>
				
													<td width="86%"><label><spring:message
																code="Label.CATLOCALGOVTTYPE" htmlEscape="true"></spring:message> </label><span
														class="errormsg">*</span><br /> 
													<spring:message
											code="Label.ULG" htmlEscape="true"></spring:message>
												<spring:bind path="modifyLocalGovtTypeCmd.category">
													<input type="radio" id="stateupload" 
														name="<c:out value="${status.expression}" escapeXml="false"/>" value="true"
														<c:if test="${status.value == 'U'}">checked</c:if>
														onclick="ClearRd('stateseperateserver','isbaseUrlstate','stateupload');" />
												</spring:bind>
												<spring:message
											code="Label.RURALG" htmlEscape="true"></spring:message>
												<spring:bind path="modifyLocalGovtTypeCmd.category">
													<input type="radio" id="stateupload" 
														name="<c:out value="${status.expression}" escapeXml="false"/>" value="true"
														<c:if test="${status.value != 'U'}">checked</c:if>
														onclick="ClearRd('stateseperateserver','isbaseUrlstate','stateupload');" />
												</spring:bind>
											
											
														</td>
												</tr>
								</table>
								<div id="divRCategory" >
											<table width="100%" class="tbl_no_brdr" >
												
												
												
												
												
												
												
												
												<tr>
													<td width="14%"></td>

													<td width="86%" style="padding: 2px"><label><spring:message
																code="Label.RURALCATEGORY" htmlEscape="true">
													</spring:message></label> <span class="errormsg">*</span><br /> <form:select
															path="ruralCategory" id="ddRuralCategory"  
															style="width: 200px" class="combofield"
															onfocus="validateOnFocus('ddRuralCategory');helpMessage(this,'ddRuralCategory_msg');"
															onblur="vlidateOnblur('ddRuralCategory','1','15','c');hideHelp();"
															>
															 <form:option value="S" >
																<spring:message code="Label.SELECT"  htmlEscape="true"></spring:message>
															</form:option> 
															<form:option value="P">
																<spring:message code="Label.PRI" htmlEscape="true"></spring:message>
															</form:option>
															<form:option value="T" >
																<spring:message code="Label.TRADITIONALBODY"  htmlEscape="true"></spring:message>
															</form:option>
														</form:select> <span><form:errors path="ruralCategory" htmlEscape="true"
																class="errormsg"></form:errors> </span> <span
														id="ddRuralCategory_msg" style="display: none"> <spring:message
																code="error.blank.Rcategory" htmlEscape="true"></spring:message> </span> <span
														class="errormsg" id="ddRuralCategory_error"> <spring:message
																code="error.blank.Rcategory" htmlEscape="true"></spring:message> </span></td>
												</tr>
												<tr>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td width="14%"></td>

													<td width="86%" style="padding: 2px"><label><spring:message
																code="Label.LEVEL" htmlEscape="true"></spring:message> </label><span
														class="errormsg">*</span> <br /> <form:select
															path="level" id="ddlevel" style="width: 200px;"
															class="combofield"
															onfocus="validateOnFocus('ddlevel');helpMessage(this,'ddlevel_msg');"
															onblur="vlidateOnblur('ddlevel','1','15','c');hideHelp();"
															>
															<form:option value="S">
																<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
															</form:option>
															<form:option value="D">
																<spring:message code="Label.DISTRICT" htmlEscape="true"></spring:message>
															</form:option>
															<form:option value="I">
																<spring:message code="Label.INTERMEDIATE" htmlEscape="true"></spring:message>
															</form:option>
															<form:option value="V">
																<spring:message code="Label.VILLAGE" htmlEscape="true"></spring:message>
															</form:option>
														</form:select> <span><form:errors path="level" class="errormsg" htmlEscape="true"></form:errors>
													</span> <span id="ddlevel_msg" style="display: none"> <spring:message
																code="error.blank.level" htmlEscape="true"></spring:message> </span> <span
														class="errormsg" id="ddlevel_error"> <spring:message
																code="error.blank.level" htmlEscape="true"></spring:message> </span></td>
												</tr>
															
											</table>
															<script>
															setValue();
															</script>
												
										</div>
								

							</div>--%>
						<%-- 	<div class="btnpnl"> 
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td><label> <input type="submit" name="Submit"
												class="btn"
												value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>"
												onclick="return onSave();" /> </label> <input type="button"
											name="Submit3" class="btn"
											value=<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message>
											id="btnClear" onclick="resetModifyCorrectionForm();" /> <label>
												<input type="button" name="Submit3" class="btn"
												value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
												onclick="javascript:go('home.htm');" /> </label>
										</td>
									</tr>
								</table>
							</div> --%>
						</div>
					 </c:if> 
					 
					 <c:if test="${modifyLocalGovtTypeCmd.correction==false}">
					  <font color="red"> 
					 	<ul class = "listing">
						 	<li>
								<div  align="center"><b>Cannot change the type of a local government body as the type is included in a local government setup of following states-</b> </div>
						 		<div  align="center"><b><c:out value="${stateList}" escapeXml="true"></c:out></b></div>
					 		</li>
				 		</ul>
					</font>
					 
					 
					 <div class="btnpnl"> 
							   <label>
									<input type="button" name="Submit3" class="btn"
									value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
									onclick="javascript:go('home.htm');" /> </label>
					 </div>
					  </c:if> 
					  </div>
				
			

			</form:form>
			<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>
</body>
</html>