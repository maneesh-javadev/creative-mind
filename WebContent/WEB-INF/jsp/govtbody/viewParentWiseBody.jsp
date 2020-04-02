<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page import="java.util.*"%>
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<% contextPath = request.getContextPath();
%>

<script type="text/javascript" src="js/cancel.js"></script>
<title><spring:message code="Label.VIEWLOCALGOVTBODY" htmlEscape="true"></spring:message></title>
<script src="js/common.js"></script>
<script src="js/viewLocalbody.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>

<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>

<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrCoveredLandRegionByULBService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService .js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSurveyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrLocalGovtBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>

<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 

function managePriDetail(url,id,type)
{
 	dwr.util.setValue('parentwiseId', id, {	escapeHtml : false	});
 	dwr.util.setValue('parentCategory', type, { escapeHtml : false	});
 	
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
	displayLoadingImage();
 }
 
function managePriTier(url,id,type)
{
	dwr.util.setValue('parentwiseId', id, {	escapeHtml : false	});
	dwr.util.setValue('parentCategory', type, { escapeHtml : false	});
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}

function manageTraDetail(url,id,type)
{
 	dwr.util.setValue('parentwiseId', id, {	escapeHtml : false	});
	dwr.util.setValue('parentCategory', type, { escapeHtml : false	});
	
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}

function manageUDetail(url,id,type)
{
 	dwr.util.setValue('parentwiseId', id, {	escapeHtml : false	});
	dwr.util.setValue('parentCategory', type, { escapeHtml : false	});
	
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}
function setDirection(val)
{
	//alert("PPPPPPPPPPP");
	document.getElementById('direction').value=val;
	document.forms['form1'].action = "viewLocalbodyPagination.htm?<csrf:token uri='viewLocalbodyPagination.htm'/>";
	document.forms['form1'].submit();
}

window.onload=loadviewLBPage; 
</script>

</head>

<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
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
		<div id="validate_error" style="display: none;">
			<table>
				<tr>
					<td rowspan="2"><div class="errorImg"></div></td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div></td>
				</tr>
				<tr>
					<td><div class="errorfont"><spring:message code="error.blank.commonAlert" htmlEscape="true"></spring:message></div>
					</td>
				</tr>
			</table>

		</div>
	</div>
	
	
	<div id="frmcontent">
		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message code="Label.VIEWLOCALGOVTBODY" htmlEscape="true"></spring:message>
					</td>
					<td align="right"><a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a></td>
				<%--//these links are not working 	<td width="50" align="right" valign="middle"><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></td>
					<td width="50" align="right"><a href="#" class="frmhelp"><spring:message
								code="Label.HELP" htmlEscape="true"></spring:message> </a></td> --%>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="modifyLocalBodyforPRI.htm" method="POST" id="form1" onsubmit="cursorwait();" name="form1" commandName="localGovtBodyForm">

				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyLocalBodyforPRI.htm"/>" />

				<input type='hidden' name=hdnType id="hdnType" value="<c:out value='${localGovtBodyForm.hdnType}' escapeXml='true'></c:out>"/>
				<input type='hidden' name=hdnIntermediatePanchayat id="hdnIntermediatePanchayat" value="<c:out value='${hdnIntermediatePanchayat}' escapeXml='true'></c:out>"/>
				<input type='hidden' name=hdnDistrictPanchayat id="hdnDistrictPanchayat" value="<c:out value='${hdnDistrictPanchayat}' escapeXml='true'></c:out>"/>
				<input type="hidden" name="districtCode" value="<c:out value='${districtCode}' escapeXml='true'></c:out>"/>
				<input type="hidden" name="lbType" value="<c:out value='${lbType}' escapeXml='true'></c:out>"/>
				<input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("stateCode")%>' />
				
				<div id="cat">
					<div class="frmpnlbg">
						<!-- For Localbody list for different district, Intermediate, Village Panchayat  -->
						<div id='district' class="frmpnlbg">
							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message code="Label.SEARCHCRIT" htmlEscape="true"></spring:message>
								</div>
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td width="14%" rowspan="8">&nbsp;</td>
										<td width="86%" class="lblBold"><label><spring:message
													code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message>
										</label><span class="errormsg">*</span><br />
										<form:select path="lgd_LBTypeName" id="tierSetupCode" name="tierSetupCode" class="combofield" style="width: 175px" onchange="hideShowDistIVMunici(this.value);">
												<form:option value="">
													<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
												</form:option>
												<c:forEach var="lgtLocalBodyType" varStatus="var" items="${lgtLocalBodyType}">
													<form:option id="typeCode" value="${lgtLocalBodyType.localBodyTypeCode}:${lgtLocalBodyType.level}:${lgtLocalBodyType.category}"><c:out value="${lgtLocalBodyType.nomenclatureEnglish}" escapeXml="true"></c:out></form:option>
												</c:forEach>
										</form:select>
											<span id="tierSetupCode_error" class="errormsg"> <spring:message
													code="error.blank.viewlgTypeName" htmlEscape="true"></spring:message>
										</span> <br /> <br /> <form:hidden path="lgd_lbCategory"
												id="hiddenCheckBox" class="frmfield"
												value="${localGovtBodyForm.lgd_lbCategory}"/>



											<div id="tr_district1" style="display:none">
												<table>
													<c:if
														test="${! empty localGovtBodyForm.lgd_LBNomenclatureDist}">
														<tr>
															<td><label><spring:message 	code="Label.SELECT" htmlEscape="true"></spring:message><c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out></label>
															<span class="errormsg">*</span><br />
															<form:select path="localBodyNameEnglishList" class="combofield" id="ddSourceLocalBody" style="width: 175px" onchange="getLocalBodySubDistrictPanList(this.value)">
																	<form:option value="0"><spring:message code="Label.SELECTLOCALBODY" htmlEscape="true"></spring:message></form:option>
																	<form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" 	itemValue="localBodyCode" />
															</form:select>
															&nbsp;<span>
																<form:errors htmlEscape="true"
																		path="localBodyNameEnglishList" class="errormsg"></form:errors>
															</span> &nbsp;&nbsp;<span class="errormsg"
																id="ddSourceLocalBody_error"> <spring:message
																		code="error.blank.DISTRICTP" htmlEscape="true"></spring:message>
															</span> <br /> <br /></td>
															<td>&nbsp;</td>
															<td>&nbsp;</td>
														</tr>
													</c:if>
												</table>
											</div>
											<div id="tr_intermediate1" style="display:none">

												<table>
													<c:if
														test="${! empty localGovtBodyForm.lgd_LBNomenclatureInter}">
														<tr>
															<td><label><spring:message
																		code="Label.SELECT" htmlEscape="true"></spring:message>&nbsp;&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out></label>&nbsp;<span
																class="errormsg">*</span><br /> <form:select
																	path="localBodyNameEnglishListSource"
																	class="combofield" id="localGovtBodyListMain"
																	style="width: 175px">
																	<form:option value=" ">
																		<spring:message code="Label.SELECTINTERMEDIATELOCBODY"
																			htmlEscape="true"></spring:message>
																	</form:option>
																	<form:options items="${localBodyforSubDistList}"
																		itemLabel="localBodyNameEnglish"
																		itemValue="localBodyCode" />
																</form:select> <span id="localGovtBodyListMain_error" class="errormsg"><spring:message
																		code="error.blank.INTERMEDIATEP" htmlEscape="true"></spring:message>
															</span> <br /> <br />
															</td>
															<td>&nbsp;</td>
															<td>&nbsp;</td>
														</tr>
													</c:if>

												</table>
											</div>
										</td>
									</tr>
									<tr>
										<!-- correctly aligned no. of records label 22/05/2012 -->
										<%-- <td colspan="5" class="lblBold" align="right" >
		<label>
			<spring:message htmlEscape="true" code="Label.NOOFRECORDPERPAGE"></spring:message>
		<form:select htmlEscape="true" path="pageRows" class="combofield" style="width: 50px">
			<form:option value="5" label="5" />
			<form:option value="10" label="10" />
			<form:option value="25" label="25" selected="selected" />
			<form:option value="50" label="50" />
			<form:option value="100" label="100" />
		</form:select>
	   </label>
	</td> --%>
									</tr>

									<tr>
										<td height="50" colspan="4"><label> <input
												type="submit" name="Submit" class="btn"
												onclick="return validateViewLBPage();"
												value=<spring:message code="Button.GETDATA"  ></spring:message> />
										</label><label> <c:if
													test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'U')}">
													<label><input type="button" name="Submit2"
														class="btn"
														value=<spring:message code="Button.CLEAR"  ></spring:message>
														onclick="javascript:location.href='viewLocalBodyforUrban.htm?<csrf:token uri='viewLocalBodyforUrban.htm'/>';" />
													</label>
										</label> </c:if> <c:if
												test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'T')}">
												<label><input type="button" name="Submit2"
													class="btn"
													value=<spring:message code="Button.CLEAR"  ></spring:message>
													onclick="javascript:location.href='viewLocalBodyforTraditional.htm?<csrf:token uri='viewLocalBodyforTraditional.htm'/>';" />
												</label>

												</label>
											</c:if> <c:if
												test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'P')}">
												<label><input type="button" name="Submit2"
													class="btn"
													value=<spring:message code="Button.CLEAR"  ></spring:message>
													onclick="javascript:location.href='viewLocalBodyforPRI.htm?<csrf:token uri='viewLocalBodyforPRI.htm'/>';" />
												</label>

												</label>

											</c:if> <label><input type="button" name="Submit3"
												class="btn"
												value="<spring:message code="Button.CLOSE" ></spring:message>"
												onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
										</label>
										</td>
									</tr>

									<tr>
										<td><input type="hidden" id="msgid" name="msgid" />
											<c:if test="${msgid != null}">
												<b style="color: red;"><c:out value="${msgid}" escapeXml="true"></c:out></b>
											</c:if>
										</td>
									</tr>

								</table>
							</div>
						</div>
						<!-- End here Localbody  -->

					</div>
				</div>


				<script src="/LGD/JavaScriptServlet"></script>
				<c:if test="${! empty LocalGovtBodyList}">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="5%" align="center">



										<table class="tbl_with_brdr" width="165%" align="center">
											<tr class="tblRowTitle tblclear">
												<td rowspan="2"><spring:message code="Label.SNO"
														htmlEscape="true"></spring:message>
												</td>
												<td rowspan="2"><spring:message
														code="Label.LOCALBODYCODE" htmlEscape="true"></spring:message>
												</td>
												<td rowspan="2"><spring:message
														code="Label.LOCALBODYNAMEINENG" htmlEscape="true"></spring:message>
												</td>
												<%--  <c:if test="${localGovtBodyForm.lgd_lbCategory !='U'}">
                                          <td width="20%" rowspan="2"><spring:message code="Label.NOOFPANCHAYAT" htmlEscape="true"></spring:message></td>
                                          </c:if> --%>
												<td colspan="6" align="center"><spring:message
														code="Label.ACTION" htmlEscape="true"></spring:message>
												</td>

											</tr>
											<tr class="tblRowTitle tblclear">
												<c:if test="${localGovtBodyForm.lgd_lbCategory !='U'}">
													<c:if
														test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'M')}">
														<td align="center"><spring:message code="Label.VIEW"
																htmlEscape="true"></spring:message>
														</td>
													</c:if>
												</c:if>
												<c:if
													test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'P')}">
													<td align="center"><spring:message htmlEscape="true"
															code="Label.VIEW"></spring:message>
													</td>
													<td align="center"><spring:message htmlEscape="true" code="Label.GovernmentOrderCorrection" ></spring:message></td> 
													<td align="center"><spring:message htmlEscape="true" code="Label.ModifyName" ></spring:message></td> 
													<td align="center"><spring:message htmlEscape="true" code="Label.Changecoveredarea" ></spring:message></td> 
													<td align="center"><spring:message htmlEscape="true" code="Label.Mapcoveredarea" ></spring:message></td> 
													

													<c:if
														test="${localGovtBodyForm.lgd_LBTypeName ne  '1:D:P'}">
														<td align="center"><spring:message htmlEscape="true" code="Label.Modifytoptier" ></spring:message></td> Label.Modifytoptier
													</c:if>
												</c:if>
												<c:if
													test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'U')}">
													<td align="center"><spring:message htmlEscape="true"
															code="Label.VIEW"></spring:message>
													</td>
													<td align="center"><spring:message htmlEscape="true" code="Label.GovernmentOrderCorrection" ></spring:message></td>
													<td align="center"><spring:message htmlEscape="true" code="Label.ModifyName" ></spring:message></td>
													<td align="center"><spring:message htmlEscape="true" code="Label.Changecoveredarea" ></spring:message></td>
													<td align="center"><spring:message htmlEscape="true" code="Label.Mapcoveredarea" ></spring:message></td>
													<td align="center"><spring:message htmlEscape="true" code="Label.Label.Modifytype" ></spring:message></td>
												</c:if>
												<c:if
													test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'T')}">
													<td align="center"><spring:message htmlEscape="true"
															code="Label.VIEW"></spring:message>
													</td>
													<td align="center"><spring:message htmlEscape="true" code="Label.GovernmentOrderCorrection" ></spring:message></td>
													<td align="center"><spring:message htmlEscape="true" code="Label.ModifyName" ></spring:message></td>
													<td align="center"><spring:message htmlEscape="true" code="Label.Changecoveredarea" ></spring:message></td>
													<td align="center"><spring:message htmlEscape="true" code="Label.Mapcoveredarea" ></spring:message></td>
													<c:if
														test="${localGovtBodyForm.lgd_LBTypeName ne  '9:D:T'}">
														<td align="center"><spring:message htmlEscape="true" code="Label.Modifytoptier" ></spring:message></td>
													</c:if>
												</c:if>
											</tr>

											<c:if test="${! empty LocalGovtBodyList}">
												<c:forEach var="lgdLocalGovtBodyList"
													varStatus="listLocalBodyRow" items="${LocalGovtBodyList}">

													<tr class="tblRowB">
														<td align="center"><c:out value="${offsets*limits+(listLocalBodyRow.index+1)}" escapeXml="true"></c:out></td>
														<td align="left"><c:out
																value="${lgdLocalGovtBodyList.localBodyCode}" escapeXml="true"></c:out>
														</td>
														<td align="center"><c:out
																value="${lgdLocalGovtBodyList.localBodyNameEnglish}" escapeXml="true"></c:out>
														</td>
														<%-- <c:if test="${localGovtBodyForm.lgd_lbCategory !='U' && lgdLocalGovtBodyList.childCount!=0 }">
                                          <td align="center"><a href="#" onclick="javascript:managePriTier('viewChildLocalBody.htm',${lgdLocalGovtBodyList.localBodyCode},'P');"><c:out
															value="${lgdLocalGovtBodyList.childCount}"></c:out> </a></td></c:if> --%>
														<%-- <c:if test="${localGovtBodyForm.lgd_lbCategory !='U' && lgdLocalGovtBodyList.childCount==0 }">
                                          <td align="center"><c:out value="-"></c:out></td></c:if> --%>

														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'P')}">
															<td align="center"><a href="#"><img
																	src="images/view.png"
																	onclick="javascript:managePriDetail('ViewLocalBodyforPRIPost.htm',${lgdLocalGovtBodyList.localBodyCode},'P');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'T')}">
															<td align="center"><a href="#"><img
																	src="images/view.png"
																	onclick="javascript:manageTraDetail('ViewLocalBodyforPRIPost.htm',${lgdLocalGovtBodyList.localBodyCode},'T');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'U')}">
															<td align="center"><a href="#"><img
																	src="images/view.png"
																	onclick="javascript:manageUDetail('ViewLocalBodyforPRIPost.htm',${lgdLocalGovtBodyList.localBodyCode},'U');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'P')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:managePriDetail('modifyGovtLocalBodyMain.htm',${lgdLocalGovtBodyList.localBodyCode},'P');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'T')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:manageTraDetail('modifyGovtLocalBodyMain.htm',${lgdLocalGovtBodyList.localBodyCode},'T');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'U')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:manageUDetail('modifyGovtLocalBodyMain.htm',${lgdLocalGovtBodyList.localBodyCode},'U');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'P')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:managePriDetail('modifyGovtLocalBodyMainforname.htm',${lgdLocalGovtBodyList.localBodyCode},'P');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'T')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:manageTraDetail('modifyGovtLocalBodyMainforname.htm',${lgdLocalGovtBodyList.localBodyCode},'T');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'U')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:manageUDetail('modifyGovtLocalBodyMainforname.htm',${lgdLocalGovtBodyList.localBodyCode},'U');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'P')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:managePriDetail('modifyGovtLocalBodyMainforcoveragearea.htm',${lgdLocalGovtBodyList.localBodyCode},'P');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'T')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:manageTraDetail('modifyGovtLocalBodyMainforcoveragearea.htm',${lgdLocalGovtBodyList.localBodyCode},'T');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'U')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:manageUDetail('modifyGovtLocalBodyMainforcoveragearea.htm',${lgdLocalGovtBodyList.localBodyCode},'U');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'P')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:managePriDetail('correctGovtLocalBodycoveragearea.htm',${lgdLocalGovtBodyList.localBodyCode},'P');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'T')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:manageTraDetail('correctGovtLocalBodycoveragearea.htm',${lgdLocalGovtBodyList.localBodyCode},'T');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'U')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:manageUDetail('correctGovtLocalBodycoveragearea.htm',${lgdLocalGovtBodyList.localBodyCode},'U');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
														<c:if
															test="${localGovtBodyForm.lgd_LBTypeName ne  '1:D:P'}">
															<c:if
																test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'P')}">
																<td align="center"><a href="#"><img
																		src="images/edit.png"
																		onclick="javascript:managePriDetail('modifyGovtLocalBodyMainfortype.htm',${lgdLocalGovtBodyList.localBodyCode},'P');"
																		width="18" height="19" border="0" />
																</a>
																</td>
															</c:if>
														</c:if>
														<c:if
															test="${localGovtBodyForm.lgd_LBTypeName ne  '9:D:T'}">
															<c:if
																test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'T')}">
																<td align="center"><a href="#"><img
																		src="images/edit.png"
																		onclick="javascript:manageTraDetail('modifyGovtLocalBodyMainfortype.htm',${lgdLocalGovtBodyList.localBodyCode},'T');"
																		width="18" height="19" border="0" />
																</a>
																</td>
															</c:if>
														</c:if>
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'U')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:manageUDetail('modifyGovtLocalBodytypeforurban.htm',${lgdLocalGovtBodyList.localBodyCode},'U');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
													</tr>

												</c:forEach>
											</c:if>


											<form:input path="parentwiseId" type="hidden"
												name="parentwiseId" id="parentwiseId" />
											<form:input path="parentCategory" type="hidden"
												name="parentCategory" id="parentCategory" />
										</table></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<c:if test="${fn:length(viewPage) > 0}">
									<c:if test="${empty LocalGovtBodyList}">
										<tr>
											<td colspan="4" align="center"><spring:message htmlEscape="true" code="Error.noresult"></spring:message></td>
										</tr>
									</c:if>
								</c:if>
								<tr>
									<td align="right">
										<table width="301">
											<tr>
												<td width="150" align="right" class="pageno"><c:out value="${LocalBodyCount}" escapeXml="true"></c:out></td>
												<td width="96" align="right" class="pre"><a href="#"
													onclick="setDirection(-1)"><spring:message
															htmlEscape="true" code="Label.PREVIOUS"></spring:message>
												</a>
												</td>
												<td width="24" align="right" class="pageno">|</td>

												<td width="51" align="right" class="nxt tblclear"><c:if
														test="${movenext==true}">

														<a href="#" onclick="setDirection(1)"><spring:message
																htmlEscape="true" code="Label.NEXT"></spring:message>
														</a>
												</td>
												</c:if>
												<td width="16" align="right" class="nxt tblclear">&nbsp;</td>

											</tr>
										</table></td>
									<td><input type="hidden" name="direction" id="direction"
										value="0" />
									</td>
								</tr>
							</table>
						</div>
					</div>
				</c:if>
				<c:if test="${localbodysize==0}">
					<table width="80%">
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr align="center">
							<td><c:out value="${listnull}" escapeXml="true"></c:out></td>
						</tr>
					</table>
				</c:if>
			</form:form>
		</div>
	</div>

</body>

</html>

