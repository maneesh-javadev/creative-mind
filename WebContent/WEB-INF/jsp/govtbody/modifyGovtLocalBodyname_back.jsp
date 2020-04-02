<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message code="Label.CREATENEWLOCALGOVTBODY" htmlEscape="true" ></spring:message>
	</title> <script src="js/shiftdistrict.js"></script>
	<script src="js/govtorder.js"></script>
	<script src="js/common.js"></script>
	<script src="js/modify_LB.js"></script>
	<script src="js/local_body.js"></script>
	<script type="text/javascript" src="js/GovtLocalBody.js"></script>
	<script type="text/javascript" src="js/viewLocalbody.js"></script>
	<script src="js/lgd_localbody.js"></script>
	<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />

<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>

	<script src="js/jquery-1.7.js" type="text/javascript"
		language="javascript"></script>
	<script src="js/jquery.MultiFile.js" type="text/javascript"
		language="javascript"></script>
	<script src="js/attachedFiles.js" type="text/javascript"
		language="javascript"></script>
	<link href="css/error.css" rel="stylesheet" type="text/css" />
	<script src="js/jquery.js"></script>
	<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
	<script src="js/lgd_localbody.js">	
	</script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>
		
	</script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>

	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrCoveredLandRegionByULBService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrSurveyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrLocalGovtBodyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>

<link rel="stylesheet" href="datepicker/demos.css" /> -->
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<link href="css/error.css" rel="stylesheet" type="text/css" />


	<script type="text/javascript" language="javascript">
		dwr.engine.setActiveReverseAjax(true);

		function selectallLocalBody() {

			var selObj = document.getElementById('ddDestLocalGovtBody');
			var villageCode = "";
			for (i = 0; i < selObj.options.length; i++) {
				selObj.options[i].selected = true;
				villageCode += selObj.options[i].value + ",";

			}

			getCoveredLandRegion(villageCode);

		}

		function selectallSubDistLocalBody() {
			// alert("Alert in subdistrict");
			var selObj = document.getElementById('ddSubDistDestLocalGovtBody');
			var subDistrictCode = "";
			for (i = 0; i < selObj.options.length; i++) {
				selObj.options[i].selected = true;
				subDistrictCode += selObj.options[i].value + ",";

			}
			getCoveredLandForSubDistRegion(subDistrictCode);
		}

		function selectallDistrictName() {

			var selObj = document.getElementById('ddDestDistLocalGovtBody');
			var districtList = "";
			for (i = 0; i < selObj.options.length; i++) {
				selObj.options[i].selected = true;
				districtList += selObj.options[i].value + ",";

			}
			getCoveredLandRegionforDistrict(districtList);

		}
	</script>
</head>

<body onload="loadModifyNamePage();">
<div class="overlay" id="overlay1" style="display: none;"></div>
	<div class="box" id="box1">
		<a class="boxclose" id="boxclose1"></a>
		<div>
			<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<div class="success"></div>
							</center>
						</td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div>
						</td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center>
							</div></td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><div class="failur"></div>
						</td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center>
						</td>
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
					<td rowspan="2"><div class="errorImg"></div>
					</td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div>
					</td>
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
		<div class="frmhd">

			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><label><spring:message
								code="Label.MODIFYGOVTLOCALBODY" htmlEscape="true">
							</spring:message> </label></td>
					<td>&nbsp;</td>
					<%--//this link is not working <td width="50" align="right"><a href="#" class="frmhelp"><spring:message
								code="Label.HELP" htmlEscape="true"  ></spring:message> </a></td> --%>
				</tr>
			</table>
		</div>

		<div class="frmpnlbrdr">
			<form:form action="modifyLocalBodyName.htm" method="POST"
				commandName="localGovtBodyForm" onsubmit="cursorwait();" enctype="multipart/form-data">
				
					<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="modifyLocalBodyName.htm"/>" />
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<label><spring:message code="Label.LOCALBODYDETAILS" htmlEscape="true"  ></spring:message>
								</label>
							</div>
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td>&nbsp;</td>
									<td><form:hidden path="localBodyCode" id="hdnLbCode"
											value="${localGovtBodyForm.localBodyCode}" htmlEscape="true" /> <form:hidden
											path="lbType" id="hdnLbTypeCode"
											value=${localGovtBodyForm.lbType}"  htmlEscape="true" />
									<form:hidden path="operationCode" id="operationCode" value="${localGovtBodyForm.operationCode}" htmlEscape="true" />	
									<form:hidden path="localbodyNameEnghidden" id="localbodyNameEnghidden"
											value="${localGovtBodyForm.localbodyNameEnghidden}" htmlEscape="true" />
									<form:hidden path="localbodyNameAliasEnghidden" id="localbodyNameAliasEnghidden"
											value="${localGovtBodyForm.localbodyNameAliasEnghidden}" htmlEscape="true" />	
									</td>
								</tr>
								<c:forEach var="localBodyDetails"
									varStatus="localBodyUrbanNameListRow"
									items="${localGovtBodyForm.localBodyDetails}">

									<tr>
										<td width="14%" rowspan="14"></td>
										<spring:bind
											path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].isdisturbed">
											<c:if test="${status.value}">
												<td class="lblBold"><spring:message
														code="Label.DISTURBEDSTATE" htmlEscape="true" ></spring:message> <br /> <label
													class="lblPlain"> <img src="images/red_flg.png"
														width="13" height="9" />
												</label></td>
											</c:if>
										</spring:bind>
									</tr>

									<tr>

										<td class="lblBold"><br /> <br /> <label> <spring:message
													code="Label.NAMEOFLOCALBODY" htmlEscape="true"  ></spring:message>
										</label><br /> <label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameEnglish">
													<c:out value="${status.value}" escapeXml="true"/>

												</spring:bind>
										</label></td>
									</tr>

									<tr>
										<td class="lblBold"><br /> <br /> <label> <spring:message
													code="Label.NAMEINLOCALLANGUAGE" htmlEscape="true" ></spring:message>
										</label><br /> <label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameLocal">
													<c:out value="${status.value}" escapeXml="true" />

												</spring:bind>
										</label></td>
									</tr>
									<tr>
										<td class="lblBold"><br /> <br /> <label><spring:message
													code="Label.LOCALBODYALIASENGLISH" htmlEscape="true"  ></spring:message> </label><br />
											<label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].aliasNameEnglish">
													<c:out value="${status.value}" escapeXml="true" />
												</spring:bind>
										</label></td>
									</tr>
									<tr>
										<td class="lblBold"><br /> <br /> <label> <spring:message
													code="Label.LOCALBODYALIASLOCAL" htmlEscape="true"  ></spring:message>
										</label><br /> <label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].alisNameLocal">
													<c:out value="${status.value}" escapeXml="true"/>
												</spring:bind>
										</label></td>
									</tr>

									<spring:bind
										path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].level"  htmlEscape="true" >
										<input type="hidden" class="frmfield"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" escapeXml="true" />" />

									</spring:bind>
								</c:forEach>


							</table>
						</div>
					</div>


			

							
							<%-- <div class="frmhdtitle">
								<label><spring:message code="Label.MODIFYDETAILS" htmlEscape="true"  ></spring:message>
								</label>
							</div> --%>
							<table width="100%" class="tbl_no_brdr">

								<tr>
									<%-- <td><br /> <form:checkbox name="checkbox" value="true"
											id='modifyLocalBodyName' path="lgd_LBchkModifyName"
											onclick="getHideModifyNameList(this.checked)" />&nbsp;&nbsp;&nbsp;<label><spring:message
												code="Label.MODIFYNAME" htmlEscape="true"  ></spring:message>&nbsp;&nbsp;&nbsp;<span>
												<form:errors htmlEscape="true"  path="lgd_LBchkModifyName" class="errormsg"></form:errors>
										</span> </label><br /></td> --%>
									<%-- <td><br /> <c:choose>
											<c:when test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_LBlevelChk,'U')}">

												<form:checkbox name="checkbox" value="true" id='modifyType'
													path="lgd_LBchkModifyType"
													onclick="getHideModifyTypeList(this.checked)" />&nbsp;&nbsp;&nbsp;
											<label><spring:message
														code="Label.MODIFYURBANLOCALBODYTYPE" htmlEscape="true" ></spring:message>&nbsp;&nbsp;&nbsp;
													<span> <form:errors htmlEscape="true"  path="lgd_LBchkModifyType"
															class="errormsg"></form:errors>
												</span> </label>
											</c:when>
										</c:choose> <br /></td> --%>
									<td><br /> <c:choose>
											<c:when	test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_LBlevelChk,'D')} && ${fn:containsIgnoreCase(localGovtBodyForm.lgd_LBlevelChk,'U')}" >
												<form:checkbox name="checkbox" value="true"
													id='modifyParent' path="lgd_LBchkModifyParent"
													onclick="getHideModifyParentList(this.checked)" />&nbsp;&nbsp;&nbsp;
											<label><spring:message code="label.MODIFYPARENT" htmlEscape="true" ></spring:message>&nbsp;&nbsp;&nbsp;
													<span> <form:errors htmlEscape="true"  path="lgd_LBchkModifyParent"
															class="errormsg"></form:errors>
												</span> </label>

											</c:when>
										</c:choose> <br /></td>
									<%-- <c:choose>
										<c:when
											test="${localGovtBodyForm.lgd_LBlevelChk =='D' ||  localGovtBodyForm.lgd_LBlevelChk !='T'}">
											<td><br /> <form:checkbox name="checkbox" value="true"
													id='modifyCoverage' path="lgd_LBchkModifyCoverage"
													onclick="getHideCoverageArea(this.checked);fillTable(document.getElementById('hdnLbCode').value);" />
												&nbsp;&nbsp;&nbsp; <label><spring:message
														code="Label.MODIFYCOVERAGE" htmlEscape="true"></spring:message>&nbsp;&nbsp;&nbsp;<span>
														<form:errors htmlEscape="true"  path="lgd_LBchkModifyCoverage"
															class="errormsg"></form:errors>
												</span> </label><br /></td>
										</c:when>
									</c:choose> --%>
								</tr>


							</table>
						

				

					<div id="modify_name" style="visibility: visible; display: block;"
						class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<label><spring:message code="Label.MODIFYLOCALBODYNAME" htmlEscape="true"  ></spring:message>
								</label>
							</div>
							<table width="100%" class="tbl_no_brdr">
								<c:forEach var="localBodyDetails"
									varStatus="localBodyUrbanNameListRow"
									items="${localGovtBodyForm.localBodyDetails}">
									<tr>
										<td width="14%" rowspan="14">&nbsp;</td>
										<td><label><spring:message
													code="Label.NEWNAMEOFLOCALBODYENGLISH" htmlEscape="true" ></spring:message> </label><span
											class="errormsg">*</span><br /> <label> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameEnglish">
													<input type="text" style="width: 200px" class="frmfield"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"/>"
														id="localBodyNameInEn" cssClass="frmfield"
														onblur="validateLocalNameInEngModify()"
														onkeypress="validateAlphanumericKeys();"  />
													<span class="errormsg" id="localBodyNameInEn_error">
														<spring:message code="Msg.LOCALBODYNAMEEN" htmlEscape="true" ></spring:message>
													</span> 
												</spring:bind><spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyCode">
													<input type="hidden" class="frmfield"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true" />"
														onfocus="this.blur()"/>
												</spring:bind> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyVersion">
													<input type="hidden" class="frmfield"
														name="<c:out value="${status.expression}"/>" 
														value="<c:out value="${status.value}" escapeXml="true"/>"
														onfocus="this.blur()"></input>
												</spring:bind>
										</label><span><form:errors  path="localBodyNameEnglish"
													class="errormsg" htmlEscape="true"></form:errors> </span>
											<div class="errormsg"></div></td>
									</tr>
									<tr>
										<td><label><spring:message
													code="Label.NEWNAMEOFLOCALLANGUAGE" htmlEscape="true"  ></spring:message> </label><br />
											<label> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameLocal">
													<input type="text" class="frmfield" style="width: 200px"
														name="<c:out value="${status.expression}" />"
														value="<c:out value="${status.value}" escapeXml="true"/>"
														onkeypress="validateAlphanumericKeys();"  />

												</spring:bind>
										</label><span><form:errors htmlEscape="true"  path="localBodyNameLocal"
													class="errormsg"></form:errors> </span>
											<div class="errormsg"></div></td>
									</tr>
									<tr>
										<td><label><spring:message
													code="Label.LOCALBODYALIASENGLISH" htmlEscape="true" ></spring:message> </label><br />
											<label> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].aliasNameEnglish">
													<input type="text" class="frmfield" style="width: 200px"
														name="<c:out value="${status.expression}" />"
														value="<c:out value="${status.value}" escapeXml="true" />"
														onkeypress="validateAlphanumericKeys();" />
												</spring:bind>
										</label>
										<span><form:errors htmlEscape="true"  path="aliasNameEnglish"
													class="errormsg"></form:errors> </span>
											<div class="errormsg"></div></td>
									</tr>
									<tr>
										<td><label><spring:message
													code="Label.LOCALBODYALIASLOCAL" htmlEscape="true" ></spring:message> </label><br />
											<label> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].alisNameLocal">
													<input type="text" class="frmfield" style="width: 200px"
														name="<c:out value="${status.expression}" />"
														value="<c:out value="${status.value}" escapeXml="true" />"
														onkeypress="validateAlphanumericKeys();" />
												</spring:bind>
										</label>
										<span><form:errors htmlEscape="true"  path="alisNameLocal"
													class="errormsg"></form:errors> </span>
											<div class="errormsg"></div></td>
									</tr>
									<tr>
										<td><input name="lgd_LBlevelChk" id="hiddenCheckBox"
											class="frmfield" type="hidden"
											value="<c:out value='${localGovtBodyForm.lgd_LBlevelChk}' escapeXml='true'></c:out>"/></td>
									</tr>

								</c:forEach>


							</table>

						</div>
					</div>
					<!--Begining of Govt Order Details  -->

					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"  ></spring:message>
							</div>

							<table width="100%" class="tbl_no_brdr">
							<tr>
							<td><form:hidden path="govtOrderConfig"
											value="${localGovtBodyForm.govtOrderConfig}" id="hdngovtOrderConfig" />
									</td>
									</tr>
								<tr>
									<td width="14%" rowspan="14"><form:hidden path="orderCode"
											id="hdnOrderCode" value="${localGovtBodyForm.orderCode}"/>
									</td>
									<td width="86%"><label><spring:message
												code="Label.ORDERNO" htmlEscape="true"  ></spring:message> </label><span
										class="errormsg">*</span><br /> 
										<form:input 	path="lgd_LBorderNo" id="OrderNo" type="text" style="width: 120px" class="frmfield" onblur="hideHelp();" maxlength="25" />
											<%-- <span class="errormsg"	id="OrderNo_error"></span>
											<span
										id="OrderNo_msg" style="display: none;">Please Enter
											Order No Like N893,AU435</span> <span
										class="error" id="OrderNo_error"></span>&nbsp;&nbsp;&nbsp;<span>
												<form:errors htmlEscape="true"  path="lgd_LBorderNo" class="errormsg"></form:errors>
										</span> --%>
											<div id="OrderNo_error" class="error"><spring:message code="error.required.ORDERNUM" htmlEscape="true"></spring:message></div>
											<div id="OrderNo_msg" style="display:none"><spring:message code="error.required.ORDERNUM" htmlEscape="true"/></div>
											<div class="errormsg" id="OrderNo_error1"><form:errors path="lgd_LBorderNo" htmlEscape="true"/></div>  
											<div class="errormsg" id="OrderNo_error2" style="display: none" ></div>
										</td>
																		
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
									<td><label><spring:message code="Label.ORDERDATE" htmlEscape="true"  ></spring:message>
									</label><span class="errormsg">*</span><br /> <form:input
											path="lgd_LBorderDate" id="OrderDate" type="text"
											class="frmfield" style="width: 120px"
											onkeypress="validateNumeric();" 
											onfocus="validateOnFocus('OrderDate');"
											onblur="vlidateOnblur('OrderDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('OrderDate')"   htmlEscape="true"  /> <%-- <span id="OrderDate_msg"
										style="display: none">Please Enter Order Date Like
											12-04-2012</span><span class="errormsg" id="OrderDate_error"></span>
										<form:errors path="lgd_LBorderDate" cssClass="errormsg" htmlEscape="true"></form:errors>
										<form:errors path="lgd_LBorderDate" cssClass="errormsg" htmlEscape="true"></form:errors> --%>
											<div id="OrderDate_error" class="error"><spring:message code="error.required.ORDERDATE" htmlEscape="true"></spring:message></div>
											<div id="OrderDate_msg" style="display:none"><spring:message code="error.required.ORDERDATE" htmlEscape="true"/></div>
											<div class="errormsg" id="OrderDate_error1"><form:errors path="orderDate" htmlEscape="true"/></div>  
											<div class="errormsg" id="OrderDate_error2" style="display: none" ></div>
									</td>
										
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><label><spring:message
												code="Label.EFFECTIVEDATE" htmlEscape="true"  ></spring:message> </label><span
										class="errormsg">*</span><br /> <form:input
											id="EffectiveDate" path="lgd_LBeffectiveDate" type="text"
											class="frmfield" style="width: 120px"
											onkeypress="validateNumeric();" 
											onfocus="validateOnFocus('EffectiveDate');"
											onblur="vlidateOnblur('EffectiveDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('EffectiveDate')" /><%-- <span
										class="errormsg" id="EffectiveDate_error"></span> <form:errors
											path="lgd_LBeffectiveDate" cssClass="errormsg" htmlEscape="true"></form:errors> <form:errors
											path="lgd_LBeffectiveDate" cssClass="errormsg" htmlEscape="true"></form:errors> <span
										id="EffectiveDate_msg" style="display: none;">Please
											Enter Effective Date Like 12-04-2012</span> --%>
											<div id="EffectiveDate_error" class="error"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"></spring:message></div>
											<div id="EffectiveDate_msg" style="display:none"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"/></div>
											<div class="errormsg" id="EffectiveDate_error1"><form:errors path="effectiveDate" htmlEscape="true"/></div>  
											<div class="errormsg" id="EffectiveDate_error2" style="display: none" ></div>
										</td>
								</tr>
								
								<tr>
									<td>&nbsp;</td>
								</tr>
								<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
								<tr>
									<td><label><spring:message code="Label.GAZPUBDATE" htmlEscape="true"  ></spring:message>
									</label> <br /> <form:input id="GazPubDate" path="lgd_LBgazPubDate"
											type="text" class="frmfield" style="width: 120px"
											onkeypress="validateNumeric();" 
												onfocus="validateOnFocus('GazPubDate');"
											onblur="vlidateOnblur('GazPubDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('GazPubDate')"/>
											<%-- <span class="errormsg" id="GazPubDate_error"></span> <form:errors
												path="lgd_LBgazPubDate" cssClass="errormsg" htmlEscape="true"></form:errors> <span
											id="GazPubDate_msg" style="display: none">Please Enter
												Gazette Publication Date Like 12-04-2012</span> --%>
											<div id="GazPubDate_error" class="error"><spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true"></spring:message></div>
											<div id="GazPubDate_msg" style="display:none"><spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true"/></div>
											<div class="errormsg" id="GazPubDate_error1"><form:errors path="lgd_LBgazPubDate" htmlEscape="true"/></div>  
											<div class="errormsg" id="GazPubDate_error2" style="display: none" ></div>
												
									</td>
									</tr>
									</c:if>
									<tr>
									<td><form:hidden path="govtOrderConfig"
											value="${govtOrderConfig}" id="hdngovtOrderConfig" />
									</td>
								</tr>
								<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
								<tr>
									<td class="tblclear"><%@ include file="../common/update_attachment.jspf"%></td>
								</tr>
								</c:if>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
									<tr>
<!-- 										<td width="14%" rowspan="2">&nbsp;</td> -->
										<td width="86%"><label><spring:message htmlEscape="true" code="Label.SELGOT"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:select
												path="templateList" id="templateList" style="width:280px"
												class="combofield" 
												onblur="vlidateOnblur('templateList','1','15','m');hideHelp();"
												onfocus="validateOnFocus('templateList');helpMessage(this,'templateList_msg');"
												onkeyup="hideMessageOnKeyPress('templateList')">
												<form:option value="0"><spring:message htmlEscape="true" code="Error.templateselect" ></spring:message></form:option>
												<form:options items="${templateList}"
													itemLabel="templateNameEnglish" itemValue="templateCode"></form:options>
											</form:select> <%-- <span class="errormsg" id="templateList_error"></span> <span><form:errors
													cssClass="errormsg" path="templateList" htmlEscape="true"></form:errors> </span> <span
											style="display: none;" id="templateList_msg">Please
												Select Government order Template</span> --%>
												<div id="templateList_error" class="error"><spring:message code="error.blank.template" htmlEscape="true"></spring:message></div>
												<div id="templateList_msg" style="display:none"><spring:message code="error.blank.template" htmlEscape="true"/></div>
												<div class="errormsg" id="templateList_error1"><form:errors path="templateList" htmlEscape="true"/></div>  
												<div class="errormsg" id="templateList_error2" style="display: none" ></div>
												
												</td>
									</tr>
									<tr>
										<td>&nbsp;</td>

									</tr>
								</c:if>
							</table>
						</div>
					</div>
					<!-- End of Govt Order Details  -->
                      
						<div class="btnpnl">
						<table width="100%" align="center" class="tbl_no_brdr">

							<tr>
								<td>&nbsp;</td>
								<td>
								<div class="btnpnl">
										<label> <input type="submit" name="submit" onclick="return validateModifyfororder();" value="<spring:message code="Button.SAVE" htmlEscape="true"  ></spring:message>" /></label>
										<label> <input type="button" class="btn" name="Submit9" value="<spring:message code="App.DRAFT" htmlEscape="true" text="Save as Draft"></spring:message>" onclick="callSaveDraft()" /></label>
										<label> <input type="button" class="btn" name="Submit6" 	value="<spring:message code="Button.CLOSE" htmlEscape="true"  ></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /></label>
								</div>
								</td>
							</tr>
						</table>
					</div>

					<!-- End here of Coverage Area -->

				</div>

			</form:form>
				<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>

	</div>
</body>
</html>