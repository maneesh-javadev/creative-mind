<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%! String contextPath;%>
<%  contextPath = request.getContextPath(); %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message code="Label.CREATENEWLOCALGOVTBODY" htmlEscape="true" ></spring:message></title>
	<script src="js/govtorder.js"></script>
	<script src="js/common.js"></script>
	<script src="js/local_body.js"></script> 
	<script src="js/successMessage.js"></script>
		<script src="js/orderValidate.js"></script>
	
	<script src="js/helpMessage.js"></script>
	<script src="js/trim-jquery.js"></script>
	
	<script type="text/javascript" src="js/viewLocalbody.js"></script>
	
	<script src="js/attachedFiles.js" type="text/javascript" language="javascript"></script>
	
	<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
	

	<script src="js/validation.js"></script>
	
	<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
	<script src="datepicker/jquery-1.6.2.js"></script>
	<script src="datepicker/jquery.ui.core.js"></script>
	<script src="datepicker/jquery.ui.widget.js"></script>
	<script src="datepicker/jquery.ui.datepicker.js"></script>
	

	<link rel="stylesheet" href="datepicker/demos.css" /> -->
	<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
	<link href="css/errorLB.css" rel="stylesheet" type="text/css" />
		
	
	<!-- Added by Sushil on 17-11-2014 -->
	<script type="text/javascript" language="javascript">
	$(function() {
		$( "#OrderDate" ).datepicker({dateFormat: 'dd-mm-yy',
			value: new Date(<%=currentYear%>, <%=currentMonth%>, <%=currentDate%>),
			changeMonth: true,
			changeYear: true
		});	
	});
	
	$(function() {
	$( "#EffectiveDate" ).datepicker({dateFormat: 'dd-mm-yy',
		value: new Date(<%=currentYear%>, <%=currentMonth%>, <%=currentDate%>),
		changeMonth: true,
		changeYear: true
	});
	});
	
	$(function() {
	$( "#GazPubDate" ).datepicker({dateFormat: 'dd-mm-yy',
		value: new Date(<%=currentYear%>, <%=currentMonth%>, <%=currentDate%>),
		changeMonth: true,
		changeYear: true
	});
	});
	
	function openFileByPath(filePath) {		
		lgdDwrStateService.openFile(filePath, {
			  callback: openFileCallBack  
		});
	}
	
	function openFileCallBack(data) {
		if(data == null ) {
			alert("File has been moved or deleted.");
		} else {
		if(data.length>5) {
			var d=data.substring(0,5);
			if(d=="ERROR") {
				 alert("File has been moved or deleted.");
			} else {
					var form = document.downloadForm;
					var tempTarget = form.target;
					var tempAction = form.action;
					form.target = 'download_page';
					form.method = "post";
					form.action = "viewGovtOrder.do?<csrf:token uri='viewGovtOrder.do'/>";
					form.govfilePath.value = data;
					form.fileDisplayType.value = "inline";

					if ($.browser.msie) {
						p_windowProperties = "width=700px,height=450px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised";
						newWindow = window.open('', 'download_page', p_windowProperties);
						if (newWindow) {
							form.submit();
							form.target = tempTarget;
							form.action = tempAction;
							newWindow.focus();
						} else {
							alert('You must allow popups for this to work.');
						}
					} else if ($.browser.mozilla) {
						form.submit();
					}
					else {
						NewWindow = window.open('',"download_page","width=903px,height=527px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised") ;
						form.submit();
					}
				}
			} else {
				alert("File has been moved or deleted.");
			}
		}
	}
	
	function saveAsDraft() {
		document.localGovtBodyForm.action = "saveAsDraft.htm?<csrf:token uri='saveAsDraft.htm'/>";
		//document.localGovtBodyForm.submit();
	}
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
	<script type="text/javascript" language="javascript">
		dwr.engine.setActiveReverseAjax(true);
	</script>
	<script src="datepicker/calender.js"></script>
</head>

<body onload="clearOrdernoErrors();loadModifyNamePage();">
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

			
					
					<h3 class="subtitle"><spring:message
								code="Label.MODIFYGOVTLOCALBODY" htmlEscape="true">
							</spring:message> </h3>
					<ul class="listing">
					<%--//this link is not working <li>
					<a href="#" class="frmhelp"><spring:message
								code="Label.HELP" htmlEscape="true"  ></spring:message> </a>
								</li> --%>
								</ul>
				
		</div>
		<form name="downloadForm">
			<input type="hidden" name="govfilePath"/>
			<input type="hidden" name="fileDisplayType"/>
		</form>
		<div class="frmpnlbrdr">
			<form:form action="modifyLocalBodyName.htm" method="POST" name="localGovtBodyForm" commandName="localGovtBodyForm" onsubmit="cursorwait();" enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyLocalBodyName.htm"/>" />
				<input type="hidden" name="isFromDraft" id="isFromDraft" value="<c:out value='${isFromDraft}' escapeXml='true'></c:out>"/>
				<input type="hidden" name="hidObj" id="hidObj" value="<c:out value='${localGovtBodyForm.localBodyCode}' escapeXml='true'></c:out>"/>
				<input type="hidden" name="lbNameEng" id="lbNameEng" value="<c:out value='${localGovtBodyForm.localbodyNameEnghidden}' escapeXml='true'></c:out>"/>
				<form:hidden path="lgd_lbTypeCode" id="Lgd_LBTypeName" value="${lgdLBTypeName}" htmlEscape="true" /> <!--  a form is added for getting and setting the Lgd_LBTypeName -->
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<label><spring:message code="Label.LOCALBODYDETAILS" htmlEscape="true"  ></spring:message>
								</label>
							</div>
							
									<form:hidden path="localBodyCode" id="hdnLbCode"
											value="${localGovtBodyForm.localBodyCode}" htmlEscape="true" /> <form:hidden
											path="lbType" id="hdnLbTypeCode"
											value="${localGovtBodyForm.lbType}" htmlEscape="true" />
									<form:hidden path="operationCode" id="operationCode" value="${localGovtBodyForm.operationCode}" htmlEscape="true" />	
									<form:hidden path="localbodyNameEnghidden" id="localbodyNameEnghidden"
											value="${localGovtBodyForm.localbodyNameEnghidden}" htmlEscape="true" />
									<form:hidden path="localbodyNameAliasEnghidden" id="localbodyNameAliasEnghidden"
											value="${localGovtBodyForm.localbodyNameAliasEnghidden}" htmlEscape="true" />	
									<form:hidden path="hiddenLevel" id="hiddenLevel"
											value="${localGovtBodyForm.hiddenLevel}" htmlEscape="true" />	
									<form:hidden path="hiddenLbType" id="hiddenLbType"
											value="${localGovtBodyForm.hiddenLbType}" htmlEscape="true" />
									<form:hidden path="parentLBCode" id="parentLBCode"
											value="${localGovtBodyForm.parentLBCode}" htmlEscape="true" />
											
								<ul class="blocklist">
								<c:forEach var="localBodyDetails"
									varStatus="localBodyUrbanNameListRow"
									items="${localGovtBodyForm.localBodyDetails}">

									<li>
										<spring:bind
											path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].isdisturbed">
											<c:if test="${status.value}">
												<spring:message
														code="Label.DISTURBEDSTATE" htmlEscape="true" ></spring:message> <br /> <label
													class="lblPlain"> <img src="images/red_flg.png"
														width="13" height="9" />
												</label>
											</c:if>
										</spring:bind>
									</li>

									<li>

										<br /> <br /> <label> <spring:message
													code="Label.NAMEOFLOCALBODY" htmlEscape="true"  ></spring:message>
										</label><br /> <label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameEnglish">
													<c:out value="${status.value}" escapeXml="true"/>

												</spring:bind>
										</label>
									</li>

									<li>
										<br /> <br /> <label> <spring:message
													code="Label.NAMEINLOCALLANGUAGE" htmlEscape="true" ></spring:message>
										</label><br /> <label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameLocal">
													<c:out value="${status.value}" escapeXml="true" />

												</spring:bind>
										</label>
									</li>
									<li>
										<br /> <br /> <label><spring:message
													code="Label.LOCALBODYALIASENGLISH" htmlEscape="true"  ></spring:message> </label><br />
											<label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].aliasNameEnglish">
													<c:out value="${status.value}" escapeXml="true" />
												</spring:bind>
										</label>
									</li>
									<li>
										<br /> <br /> <label> <spring:message
													code="Label.LOCALBODYALIASLOCAL" htmlEscape="true"  ></spring:message>
										</label><br /> <label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].alisNameLocal">
													<c:out value="${status.value}" escapeXml="true"/>
												</spring:bind>
										</label>
									</li>

									<spring:bind
										path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].level"  htmlEscape="true" >
										<input type="hidden" class="frmfield"
											name="<c:out value="${status.expression}" />"
											value="<c:out value="${status.value}" escapeXml="true" />" />

									</spring:bind>
								</c:forEach>


							</ul>
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
								<label><spring:message code="Label.MODIFYLOCALBODYNAME" htmlEscape="true"></spring:message>
								</label>
							</div>
							
							<ul class="blocklist">
								<c:forEach var="localBodyDetails"
									varStatus="localBodyUrbanNameListRow"
									items="${localGovtBodyForm.localBodyDetails}">
									<li>
										
										<label><spring:message
													code="Label.NEWNAMEOFLOCALBODYENGLISH" htmlEscape="true" ></spring:message> </label><span
											class="errormsg">*</span><br /> <label> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameEnglish">
													<input type="text" class="frmfield" maxlength="200"
														name="<c:out value="${status.expression}" />"
														value="<c:out value="${status.value}" escapeXml="true"/>"
														id="localBodyNameInEn" cssClass="frmfield"
														onblur="clearNames();validateLocalNameInEngModify()"
														onkeypress="return validateSpecialCharactersUpdateStandardCodeLocal(event);"/>
													<div id="modifyNameError" style="color: red;"></div>
													<div id="localBodyNameInEn_error" class="error"><spring:message code="error.blank.localBodyNameInEn" htmlEscape="true"></spring:message></div> 
													<div class="errormsg" id="localBodyNameInEn_error1"><form:errors path="lgd_LBNameInEn" htmlEscape="true"/></div>  
													<div class="errormsg" id="localBodyNameInEn_error2"><form:errors path="lgd_LBNameInEnh" htmlEscape="true"/></div>
													<div class="errormsg" id="localBodyNameInEn_error3" style="display: none"></div>
													
												</spring:bind><spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyCode">
													<input type="hidden" class="frmfield"
														name="<c:out value="${status.expression}" />"
														value="<c:out value="${status.value}" escapeXml="true" />"
														onfocus="this.blur()"/>
												</spring:bind> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyVersion">
													<input type="hidden" class="frmfield"
														name="<c:out value="${status.expression}"/>" 
														value="<c:out value="${status.value}" escapeXml="true"/>"
														onfocus="this.blur()"></input>
												</spring:bind>
										</label><%-- <span><form:errors  path="localBodyNameEnglish"
													class="errormsg" htmlEscape="true"></form:errors> </span> --%>
											<!-- <div class="errormsg"></div> -->
												
											
									</li>
									<li>
										<label><spring:message
													code="Label.NEWNAMEOFLOCALLANGUAGE" htmlEscape="true"  ></spring:message> </label><br />
											<label> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameLocal">
													<input type="text" class="frmfield"  maxlength="100"
														name="<c:out value="${status.expression}" />"
														value="<c:out value="${status.value}" escapeXml="true"/>"
														id="localBodyNameInLocal"
														onblur="validateSpecialCharactersLBName(this.value);" />
											
												</spring:bind>
										</label><%-- <span><form:errors htmlEscape="true" path="localBodyNameLocal"
													class="errormsg"></form:errors> </span>
											<div class="errormsg"> --%>
												<%-- <div id="localBodyNameInEn_error" class="error"><spring:message code="error.blank.localBodyNameInEn" htmlEscape="true"></spring:message></div> --%>
												<div class="errormsg" id="localBodyNameInLocal_error1"><form:errors path="localBodyNameLocal" htmlEscape="true"/></div>  
												<div class="errormsg" id="localBodyNameInLocal_error2" style="display: none" ></div>
											
											
											
											
									</li>
									<li>
										<label><spring:message
													code="Label.LOCALBODYALIASENGLISH" htmlEscape="true" ></spring:message> </label><br />
											<label> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].aliasNameEnglish">
													<input type="text" class="frmfield" maxlength="50"
														name="<c:out value="${status.expression}" />"
														value="<c:out value="${status.value}" escapeXml="true" />"
														onkeypress="return validateSpecialCharactersUpdateStandardCodeLocal(event);"/>
												</spring:bind>
										</label>
										<%-- <span><form:errors htmlEscape="true"  path="aliasNameEnglish"
													class="errormsg"></form:errors> </span>
											<div class="errormsg"></div> --%>
											<%-- <div id="localBodyNameInEn_error" class="error"><spring:message code="error.blank.localBodyNameInEn" htmlEscape="true"></spring:message></div> --%>
												<div class="errormsg" id="localBodyNameInEn_error1"><form:errors path="aliasNameEnglish" htmlEscape="true"/></div>  
												<div class="errormsg" id="localBodyNameInEn_error2" style="display: none" ></div>
											
									</li>
									<li>
										<label><spring:message
													code="Label.LOCALBODYALIASLOCAL" htmlEscape="true" ></spring:message> </label><br />
											<label> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].alisNameLocal">
													<input type="text" class="frmfield" maxlength="50"
														name="<c:out value="${status.expression}" />"
														value="<c:out value="${status.value}" escapeXml="true" />"
														id="localBodyNameInAliasLocal"
														onblur="validateSpecialCharactersLBNameAliasLocal(this.value);" 
														/>
												</spring:bind>
										</label>
										<%-- <span><form:errors htmlEscape="true"  path="alisNameLocal"
													class="errormsg"></form:errors> </span>
											<div class="errormsg"></div> --%>
											<%-- <div id="localBodyNameInEn_error" class="error"><spring:message code="error.blank.localBodyNameInEn" htmlEscape="true"></spring:message></div> --%>
												<div class="errormsg" id="localBodyNameInAliasLocal_error1"><form:errors path="alisNameLocal" htmlEscape="true"/></div>  
												<div class="errormsg" id="localBodyNameInAliasLocal_error2" style="display: none" ></div>
											
									</li>
									<li>
										<input name="lgd_LBlevelChk" id="hiddenCheckBox"
											class="frmfield" type="hidden"
											value="<c:out value='${localGovtBodyForm.lgd_LBlevelChk}' escapeXml='true'></c:out>"/>
									</li>

								</c:forEach>


							</ul>

						</div>
					</div>
					<!--Begining of Govt Order Details  -->

					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"  ></spring:message>
							</div>

							
							<%-- <form:hidden path="govtOrderConfig"
											value="${localGovtBodyForm.govtOrderConfig}" id="hdngovtOrderConfig" /> --%>
									
							
									<form:hidden path="orderCode"
											id="hdnOrderCode" value="${localGovtBodyForm.orderCode}"/>
									<ul class="blocklist">
									<li>
									<label><spring:message
												code="Label.ORDERNO" htmlEscape="true"></spring:message> </label><span
										class="errormsg">*</span><br /> 
										<form:input path="lgd_LBorderNo" id="OrderNo" type="text" class="frmfield" maxlength="60" 
											 onkeypress="return validateaGovtOrderNOforModify(event);" 
											onfocus="validateOnFocus('OrderNo');helpMessage(this,'OrderNo_error');"
									         onblur="hideHelp();vlidateOrderNo('OrderNo','1','60');"
											onkeyup="hideMessageOnKeyPressForOrder('OrderNo')"/>
												
											<div id="OrderNo_error" class="errormsg"><spring:message code="error.required.ORDERNUM" htmlEscape="true"></spring:message></div>
									<div id="OrderNo_msg" class="errormsg"><spring:message code="error.required.ORDERINPUTTYPE" text="Please Enter AlphaNumerics Space . / - ( ) Only" htmlEscape="true"/></div>
										
											
														
										
											<div class="errormsg" id="OrderNo_error1"><form:errors path="lgd_LBorderNo" htmlEscape="true"/></div>  
											<div class="errormsg" id="OrderNo_error2" style="display: none"></div>
										</li>
																		
								
									<li>
									<label><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message>
									</label><span class="errormsg">*</span><br /> <form:input path="lgd_LBorderDate" id="OrderDate" type="text"
											class="frmfield" 
											onchange="setEffectiveDate(this.value);"
											onkeypress="return validateNumeric(event);" 
											onfocus="validateOnFocus('OrderDate');helpMessage(this,'OrderDate_msg');"
											onblur="vlidateOnblur('OrderDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('OrderDate')" htmlEscape="true"/> 
											<div id="OrderDate_error" class="error"><spring:message code="error.required.ORDERDATE" htmlEscape="true"></spring:message></div>
											<div id="OrderDate_msg" style="display:none"><spring:message code="error.required.ORDERDATE" htmlEscape="true"/></div>
											<div class="errormsg" id="OrderDate_error1"><form:errors path="lgd_LBorderDate" htmlEscape="true"/></div>  
											<div class="errormsg" id="OrderDate_error2" style="display: none" ></div>
									</li>
									
								
								
								<li>
									<label><spring:message
												code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:input
											id="EffectiveDate" path="lgd_LBeffectiveDate" type="text"
											class="frmfield" 
											onkeypress="validateNumeric();" 
											onfocus="validateOnFocus('EffectiveDate');helpMessage(this,'EffectiveDate_msg');"
											onblur="vlidateOnblur('EffectiveDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('EffectiveDate')"/>
											
											<div id="EffectiveDate_error" class="error"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"></spring:message></div>
											<div id="EffectiveDate_msg" style="display:none"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"/></div>
											<div class="errormsg" id="EffectiveDate_error1"><form:errors path="lgd_LBeffectiveDate" htmlEscape="true"/></div>  
											<div class="errormsg" id="EffectiveDate_error2" style="display: none"></div>
										
								</li>
								
								<li>
								<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
								
									<label><spring:message code="Label.GAZPUBDATE" htmlEscape="true"  ></spring:message>
									</label> <br /> <form:input id="GazPubDate" path="lgd_LBgazPubDate"
											type="text" class="frmfield" 
											onkeypress="validateNumeric();" 
											onfocus="validateOnFocus('GazPubDate');helpMessage(this,'GazPubDate_msg');"
											onblur="vlidateOnblur('GazPubDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('GazPubDate')"/>
											<div id="GazPubDate_msg" style="display:none"><spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true"/></div>
											<div class="errormsg" id="GazPubDate_error1"><form:errors path="lgd_LBgazPubDate" htmlEscape="true"/></div>  
											<div class="errormsg" id="GazPubDate_error2" style="display: none" ></div>
												
									
									</c:if>
									</li>
									<li>
									<form:hidden path="govtOrderConfig"
											value="${govtOrderConfig}" id="hdngovtOrderConfig" />
									
								</li>
								<li>
								<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
									
										<%@ include file="../common/update_attachment.jspf"%>
									
										
											<div id="attachFileDiv">
												<form:errors path="attachFile1" element="attachFile1" class="errormsg" htmlEscape="true"></form:errors>
											</div>
										
									
								</c:if>
								</li>
								<li>
								<c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
									
<!-- 										<td width="14%" rowspan="2">&nbsp;</td> -->
										<label><spring:message htmlEscape="true" code="Label.SELGOT"></spring:message> </label><span
										class="errormsg">*</span><br />
										<form:select
												path="templateList" id="templateList" 
												class="combofield" maxlength="20" 
												onblur="vlidateOnblur('templateList','1','15','m');hideHelp();"
												onfocus="validateOnFocus('templateList');helpMessage(this,'templateList_msg');"
												onkeyup="hideMessageOnKeyPress('templateList')">
												<form:option value="0"><spring:message htmlEscape="true" code="Error.templateselect" ></spring:message></form:option>
												<form:options items="${templateList}"
													itemLabel="templateNameEnglish" itemValue="templateCode"></form:options>
											</form:select> 
											
												<div id="templateList_error" class="error"><spring:message code="error.blank.template" htmlEscape="true"></spring:message></div>
												<div id="templateList_msg" style="display:none"><spring:message code="error.blank.template" htmlEscape="true"/></div>
												<div class="errormsg" id="templateList_error1"><form:errors path="templateList" htmlEscape="true"/></div>  
												<div class="errormsg" id="templateList_error2" style="display: none" ></div>
												
									
								</c:if>
								</li>
							</ul>
						</div>
					</div>
					<!-- End of Govt Order Details  -->
                      
					
						
								<div class="btnpnl">
										 <input type="submit" name="submit" onclick="return validateModifyfororderChangeName();" value="<spring:message code="Button.SAVE" htmlEscape="true"  ></spring:message>" />
<%-- 										<label> <input type="submit" class="btn" name="Submit9" value="<spring:message code="App.DRAFT" htmlEscape="true" text="Save as Draft"></spring:message>" onclick="saveAsDraft();" /></label> --%>
										 <input type="button" class="btn" name="Submit6" 	value="<spring:message code="Button.CLOSE" htmlEscape="true"  ></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
								</div>
							
					

					<!-- End here of Coverage Area -->

				</div>

			</form:form>
				<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>

	
</body>
</html>