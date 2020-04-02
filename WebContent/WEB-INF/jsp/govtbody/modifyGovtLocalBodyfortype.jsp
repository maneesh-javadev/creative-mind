
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
	
	<script src="js/orderValidate.js"></script>
	
	<script type="text/javascript" src="js/GovtLocalBody.js"></script>
	<script type="text/javascript" src="js/viewLocalbody.js"></script>
	<script src="js/lgd_localbody.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
	<script src="js/jquery-1.7.js" type="text/javascript"
		language="javascript"></script>
	<script src="js/jquery.MultiFile.js" type="text/javascript"
		language="javascript"></script>
	<script src="js/attachedFiles.js" type="text/javascript"
		language="javascript"></script>
	<link href="css/errorLB.css" rel="stylesheet" type="text/css" />
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
			<h3 class="subtitle"><spring:message code="Label.MODIFYGOVTLOCALBODY" htmlEscape="true"></spring:message></h3>
			<ul class="listing">
				<%--//this link is not working <li><a href="#" class="frmhelp"><spring:message code="Label.HELP" htmlEscape="true"  ></spring:message> </a></li> --%>
			</ul>
		</div>

		<div class="frmpnlbrdr">
			<form:form action="modifyLocalBodyTypeForPri.htm" method="POST" commandName="localGovtBodyForm" onsubmit="cursorwait();"  enctype="multipart/form-data">
				 <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyLocalBodyTypeForPri.htm"/>" />
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
							<form:hidden path="operationCode" id="operationCode"
								value="${localGovtBodyForm.operationCode}" htmlEscape="true" />	
							<form:hidden path="oldlocalbodyParentNamehidden" id="oldlocalbodyParentNamehidden"
								value="${localGovtBodyForm.oldlocalbodyParentNamehidden}" htmlEscape="true" />
							<form:hidden path="lgd_LBlevelChk" id="lgd_LBlevelChk"
								value="${localGovtBodyForm.lgd_LBlevelChk}" htmlEscape="true" />	
							<c:forEach var="localBodyDetails" varStatus="localBodyUrbanNameListRow" items="${localGovtBodyForm.localBodyDetails}">
								<div class="block">
									<ul class="blocklist">
										<li>
											<spring:bind
												path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].isdisturbed">
												<c:if test="${status.value}">
													<spring:message code="Label.DISTURBEDSTATE" htmlEscape="true" > </spring:message> <br /> <label class="lblPlain"> <img src="images/red_flg.png" width="13" height="9" /></label>
												</c:if>
											</spring:bind>
										</li>
										<li>
										<label> <spring:message code="Label.NAMEOFLOCALBODY" htmlEscape="true"  ></spring:message>
											</label><br /> <label class="lblPlain"> <spring:bind
											path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameEnglish">
											<c:out value="${status.value}" escapeXml="true"/>
											</spring:bind>
											</label>
										</li>
										<li>
											 <label> <spring:message
														code="Label.NAMEINLOCALLANGUAGE" htmlEscape="true" ></spring:message>
											</label><br /> <label class="lblPlain"> <spring:bind
														path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameLocal">
														<c:out value="${status.value}" escapeXml="true" />
											</spring:bind>
											</label>
										</li>
										<li>
											<label><spring:message
														code="Label.LOCALBODYALIASENGLISH" htmlEscape="true"  ></spring:message> </label><br />
												<label class="lblPlain"> <spring:bind
														path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].aliasNameEnglish">
														<c:out value="${status.value}" escapeXml="true" />
													</spring:bind>
											</label>
										</li>
										<li>
											<label> <spring:message
														code="Label.LOCALBODYALIASLOCAL" htmlEscape="true"  ></spring:message>
											</label><br /> <label class="lblPlain"> <spring:bind
														path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].alisNameLocal">
														<c:out value="${status.value}" escapeXml="true"/>
													</spring:bind>
											</label>
										</li>
										<li>
											<label> <spring:message
														code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message>
											</label><br /> <label class="lblPlain"> <spring:bind
														path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].sscode">
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
										<spring:bind
											path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameEnglish"  htmlEscape="true">
											<input type="hidden" class="frmfield"
												name="<c:out value="${status.expression}" />"
												value="<c:out value="${status.value}" escapeXml="true" />" />
										</spring:bind>
										<spring:bind
											path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameLocal"  htmlEscape="true">
											<input type="hidden" class="frmfield"
												name="<c:out value="${status.expression}" />"
												value="<c:out value="${status.value}" escapeXml="true" />" />
										</spring:bind>
												
										<spring:bind
											path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].aliasNameEnglish"  htmlEscape="true">
											<input type="hidden" class="frmfield"
												name="<c:out value="${status.expression}" />"
												value="<c:out value="${status.value}" escapeXml="true" />" />
										</spring:bind>
											
										<spring:bind
											path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].alisNameLocal"  htmlEscape="true">
											<input type="hidden" class="frmfield"
												name="<c:out value="${status.expression}" />"
												value="<c:out value="${status.value}" escapeXml="true" />" />
										</spring:bind>
									</ul>
								</div>
							</c:forEach>
						</div>
					</div>
					<div id="modify_parent_list" style="visibility: visible; display: block;" class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<label><spring:message code="label.MODIFYPARENT"   htmlEscape="true"></spring:message>
								</label>
							</div>
							<c:forEach var="localBodyDetails" varStatus="localBodyUrbanNameListRow" items="${localGovtBodyForm.localBodyDetails}">
								<div class="block">
									<ul class="blocklist">
										<li>
											<label><spring:message code="Label.SELECTEDTYPEOFLOCALBODY" htmlEscape="true"  ></spring:message> </label><br /> <label>
												<spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyTypeName">
													<input type="text" size="40" maxlength="50"
														class="frmfield"
														name="<c:out value="${status.expression}" />"
														value="<c:out value="${status.value}" escapeXml="true" />" readonly />
												</spring:bind>
											</label>
											<div class="errormsg"></div>
										</li></br>
										<li>
											<label><spring:message code="Label.PARENTOFLOCALBODY" htmlEscape="true"  ></spring:message> </label><br /> <label>
												<spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].parentLocalBodyNameEnglish">
													<input type="text" size="40" maxlength="50"
														class="frmfield"
														name="<c:out value="${status.expression}" />"
														value="<c:out value="${status.value}" escapeXml="true"/>"
														onfocus="this.blur()" readonly />
												</spring:bind>

											</label>
											<div class="errormsg"></div>
										</li>
									</ul>	
								</div>					
							</c:forEach></br>
							<div>
								<label><spring:message code="Label.PARENTNEWLOCALBODY" htmlEscape="true" ></spring:message>
										<c:out value="${localGovtBodyForm.localBodyDetails[0].localBodyNameEnglish}" escapeXml="true"></c:out></label><span
										class="errormsg">*</span>
									<br /> <form:select path="localBodyNameEnglishList"
										class="combofield" id="parentLocalBody"
										style="width: 200px"  htmlEscape="true"
										onfocus="validateOnFocus('parentLocalBody');helpMessage(this,'parentLocalBody_msg');"
										onblur="vlidateOnblur('parentLocalBody','1','15','c');hideHelp();"
										onkeyup="hideMessageOnKeyPress('parentLocalBody')">
										<form:option value="0">
											<spring:message code="Label.SELECTLOCALBODY" htmlEscape="true"></spring:message>
										</form:option>
										<c:forEach items="${lgd_LGdistrictPanchayatList}" var="lgd_LGdistrictPanchayatList">
																<!--Condtion remove of draft mode for online issue   -->
																 <form:option value="${lgd_LGdistrictPanchayatList.localBodyCode}"><c:out value="${lgd_LGdistrictPanchayatList.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>
															  	 <%-- <c:if test="${lgd_LGdistrictPanchayatList.operation_state == 'F'.charAt(0)}">
															  	  <form:option value="${lgd_LGdistrictPanchayatList.localBodyCode}" disabled="true">${lgd_LGdistrictPanchayatList.localBodyNameEnglish}</form:option>
															 	 </c:if>  
															   	<c:if test="${lgd_LGdistrictPanchayatList.operation_state == 'A'.charAt(0)}">
															   	 <form:option value="${lgd_LGdistrictPanchayatList.localBodyCode}">${lgd_LGdistrictPanchayatList.localBodyNameEnglish}</form:option>
															  	</c:if> --%>
															</c:forEach>
									</form:select> 
								
								<div id="parentLocalBody_error" class="error"><spring:message code="Label.PARENTOFLOCALBODY" htmlEscape="true"></spring:message></div>
								<div id="parentLocalBody_msg" style="display:none"><spring:message code="Label.PARENTOFLOCALBODY" htmlEscape="true"/></div>
								<div class="errormsg" id="parentLocalBody_error1"><form:errors path="localBodyNameEnglishList" htmlEscape="true"/></div>  
								<div class="errormsg" id="parentLocalBody_error2" style="display: none"></div>
							</div>

						</div>
					</div>
					<!--End of Parent Govt Localbody  -->
					<!--Begining of Govt Order Details  -->

					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"  ></spring:message>
							</div>
							<div>
								<ul class="blocklist">
								<form:hidden path="orderCode" id="hdnOrderCode" value="${localGovtBodyForm.orderCode}" />
									<li>
										<label><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:input
										path="lgd_LBorderNo" id="OrderNo" type="text"
										class="frmfield" maxLength="60"
										onfocus="validateOnFocus('OrderNo');helpMessage(this,'OrderNo_error');"
										 onblur="hideHelp();vlidateOrderNo('OrderNo','1','60');"
										onkeyup="hideMessageOnKeyPressForOrder('OrderNo')"
										onkeypress="return validateaGovtOrderNOforModify(event);" />
										 <div id="OrderNo_error" class="errormsg"><spring:message code="error.required.ORDERNUM" htmlEscape="true"></spring:message></div>
										<div id="OrderNo_msg" class="errormsg"><spring:message code="error.required.ORDERINPUTTYPE" text="Please Enter AlphaNumerics Space . / - ( ) Only" htmlEscape="true"/></div>
										<div class="errormsg" id="OrderNo_error1"><form:errors path="lgd_LBorderNo" htmlEscape="true"/></div>  
										<div class="errormsg" id="OrderNo_error2" style="display: none" ></div>
									</li>
									<li>
										<label><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message>
										</label><span class="errormsg">*</span><br /> <form:input
										path="lgd_LBorderDate" id="OrderDate" type="text"
										class="frmfield"
										onchange="setEffectiveDate(this.value);"
										onkeypress="validateNumeric();" 
										onfocus="validateOnFocus('OrderDate');helpMessage(this,'OrderDate_msg');"
										onblur="vlidateOnblur('OrderDate','1','15','m');hideHelp();"
										onkeyup="hideMessageOnKeyPress('OrderDate')" htmlEscape="true" /> 
										
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
										onkeyup="hideMessageOnKeyPress('EffectiveDate')" />
										<div id="EffectiveDate_error" class="error"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"></spring:message></div>
										<div id="EffectiveDate_msg" style="display:none"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"/></div>
										<div class="errormsg" id="EffectiveDate_error1"><form:errors path="lgd_LBeffectiveDate" htmlEscape="true"/></div>  
										<div class="errormsg" id="EffectiveDate_error2" style="display: none" ></div>		
									</li>
									<li>
										<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
											<label><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>
											</label> <br /> <form:input id="GazPubDate" path="lgd_LBgazPubDate"
											type="text" class="frmfield" 
											onkeypress="validateNumeric();" 
											onfocus="validateOnFocus('GazPubDate');helpMessage(this,'GazPubDate_msg');"
											onblur="vlidateOnblur('GazPubDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('GazPubDate')"/>
											
											<div id="GazPubDate_error" class="error"><spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true"></spring:message></div>
											<div id="GazPubDate_msg" style="display:none"><spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true"/></div>
											<div class="errormsg" id="GazPubDate_error1"><form:errors path="lgd_LBgazPubDate" htmlEscape="true"/></div>  
											<div class="errormsg" id="GazPubDate_error2" style="display: none" ></div>	

										</c:if>
									</li>
									<li>
										<form:hidden path="govtOrderConfig" value="${govtOrderConfig}" id="hdngovtOrderConfig" />
									</li>
									
									
									
									
									<li>
										<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
											<%@ include file="../common/update_attachment.jspf"%>
										</c:if>
									</li>
									<li>
										<c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
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
					</div>
					<div class="btnpnl">
						<div class="btnpnl">
							<label> <input type="submit" name="submit"
								 onclick="return validateModifyfororderChangeUpperTierLB();"
								value="<spring:message code="Button.SAVE" htmlEscape="true"  ></spring:message>" />
							</label>
							<%-- <label> <input type="button" class="btn"
								name="Submit9"
								value="<spring:message code="App.DRAFT" htmlEscape="true"></spring:message>"
								onclick="callSaveDraft()" /> </label> --%>
							<label> <input type="button" class="btn"
								name="Submit6"
								value="<spring:message code="Button.CLOSE" htmlEscape="true"  ></spring:message>"
								onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
							</label>
						</div>
					</div>
				</div>
			</form:form>
			<c:if test="${msg != null}"> 
				<script>
					alert("<c:out value="${msg}"/>");
				</script>
			</c:if>	
				<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>

	<!-- </div> -->
</body>
</html>