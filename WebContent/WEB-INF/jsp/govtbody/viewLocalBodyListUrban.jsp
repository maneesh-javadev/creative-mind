<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="java.util.*"%>
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>

<title><spring:message code="Label.VIEWLOCALGOVTBODY" htmlEscape="true"></spring:message></title>
	<script src="js/viewLocalbody.js"></script>
	<script src="js/validation.js"></script>
	<script src="js/successMessage.js"></script>
	<script src="js/helpMessage.js"></script>
	<script src="js/trim-jquery.js"></script>
	<script type="text/javascript" language="javascript" src="<%=contextpthval%>/datatable/jquery.dataTables.js"></script>
	<script type="text/javascript" language="javascript" src="<%=contextpthval%>/datatable/jquery-ui-1.8.7.custom.min.js"></script>
	<script type="text/javascript" language="javascript" src="<%=contextpthval%>/dwr/interface/lgdDwrLocalGovtBodyService.js"></script>
	<script src="<%=contextpthval%>/datatable/TableTools.js" charset="utf-8" type="text/javascript"></script>
	<link href="<%=contextpthval%>/datatable/jquery-ui-1.8.7.custom.css" rel="stylesheet"  type="text/css" />
	<link href="<%=contextpthval%>/datatable/demo_table_jui.css" rel="stylesheet"  type="text/css" />
	<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
	<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
	<link href="css/errorLB.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#localbodyListUrban').dataTable({
			"bJQueryUI": true,
			"aaSorting": [],
			"bAutoWidth": false,
			"aoColumns": [
				  			null,
				  			null,
				  			null,
				  			{ "bSortable": false },
				  			{ "bSortable": false },
				  			{ "bSortable": false },
				  			{ "bSortable": false },
				  			<c:if test="${localGovtBodyForm.lgd_LBTypeName ne '7:I:U'}">
				  			{ "bSortable": false },
				  			</c:if>
				  			{ "bSortable": false }				  		
				  			],
			"sPaginationType": "full_numbers"
		});
	} );

function open_win() {
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 

function manageUDetail(url,id,type,operationState)
{
	dwr.util.setValue('parentwiseId', id, {	escapeHtml : false	});
	dwr.util.setValue('parentCategory', type, { escapeHtml : false	});
	if(operationState=='A'){
		if(url == "modifyGovtLocalBodyMainforname.htm") {
	 		document.getElementById('form1').method = "get";
	 		document.getElementById('form1').action = url;//"getDraftFileList.htm";
	 		document.getElementById('form1').submit();
	 	} else {
			document.getElementById('form1').action = url;
			document.getElementById('form1').submit();
	 	}
		displayLoadingImage();
	}else{
		draftModeAlert();
	}
}
 
function setDirection(val) {
	
	document.getElementById('direction').value=val;
	document.forms['form1'].action = "viewLocalbodyPagination.htm?<csrf:token uri='viewLocalbodyPagination.htm'/>";
	document.forms['form1'].submit();
}

window.onload=loadviewLBPage; 

dwr.engine.setActiveReverseAjax(true);
</script>

</head>

<body oncontextmenu="return false">
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
		
			<h3 class="subtitle">
				<spring:message code="Label.VIEWLOCALGOVTBODY" htmlEscape="true"></spring:message>
			</h3>
			<ul class="listing">
				<li>
					<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /> </a>
				</li>
				<%--//these links are not working <li>
					<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
				</li>
				<li> 
					<a href="#" class="frmhelp"><spring:message code="Label.HELP" htmlEscape="true"></spring:message> </a>
				</li> --%>
			</ul>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			
			<form:form action="modifyLocalBodyforPRI.htm" method="POST" id="form1" onsubmit="cursorwait();" name="form1" commandName="localGovtBodyForm">

				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyLocalBodyforPRI.htm"/>" />

				<input type='hidden' name="hdnType" id="hdnType" value="<c:out value='${localGovtBodyForm.hdnType}' escapeXml='true'></c:out>"/>
				<input type='hidden' name="hdnIntermediatePanchayat" id="hdnIntermediatePanchayat" value="<c:out value='${hdnIntermediatePanchayat}' escapeXml='true'></c:out>"/>
				<input type='hidden' name="hdnDistrictPanchayat" id="hdnDistrictPanchayat" value="<c:out value='${hdnDistrictPanchayat}' escapeXml='true'></c:out>"/>
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
								<!-- <table width="100%" class="tbl_no_brdr">
									<tr>
										<td width="14%" rowspan="8">&nbsp;</td>
										<td width="86%" class="lblBold"> -->
									<ul class = "blocklist">
										<li class = "lblBold">	
										<label for = "tierSetupCode"><spring:message
													code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message>
										</label><span class="errormsg">*</span><br />
										<form:select path="lgd_LBTypeName" id="tierSetupCode" name="tierSetupCode" class="combofield" style="width: 175px" onchange="hideShowDistIVMunici(this.value);"
												onfocus="validateOnFocus('tierSetupCode');helpMessage(this,'tierSetupCode_msg');"
												onblur="vlidateOnblur('tierSetupCode','1','15','c');hideHelp();"
												onkeyup="hideMessageOnKeyPress('tierSetupCode')" htmlEscape="true">
												<form:option value="" htmlEscape="true">
													<esapi:encodeForHTMLAttribute><spring:message code="Label.SELECT" htmlEscape="true"></spring:message></esapi:encodeForHTMLAttribute>
												</form:option>
												<c:forEach var="lgtLocalBodyType" varStatus="var" items="${lgtLocalBodyType}">
													<form:option id="typeCode" htmlEscape="true" value="${lgtLocalBodyType.localBodyTypeCode}:${lgtLocalBodyType.level}:${lgtLocalBodyType.category}:${lgtLocalBodyType.tierSetupCode}:${lgtLocalBodyType.parentLocalBodyTypeCode}"><esapi:encodeForHTMLAttribute>${lgtLocalBodyType.nomenclatureEnglish}</esapi:encodeForHTMLAttribute></form:option>
												</c:forEach>
												
										</form:select>
										
										<div id="tierSetupCode_error" class="error"><spring:message code="error.blank.viewlgTypeName" htmlEscape="true"></spring:message></div>
										<div id="tierSetupCode_msg" style="display:none"><spring:message code="error.blank.viewlgTypeName" htmlEscape="true"/></div>
										<div class="errormsg" id="tierSetupCode_error1"><form:errors path="lgd_LBTypeName" htmlEscape="true"/></div>  
										<div class="errormsg" id="tierSetupCode_error2" style="display: none"></div>	
										
										 <br /> <br />
										
										 <form:hidden path="lgd_lbCategory"
												id="hiddenCheckBox" class="frmfield"
												value="${localGovtBodyForm.lgd_lbCategory}"/>

											<div id="tr_district1" style="display:none">
												<!-- <table> -->
												<ul class = "blocklist">
													<c:if
														test="${! empty localGovtBodyForm.lgd_LBNomenclatureDist}">
														<li>
															<label><spring:message 	code="Label.SELECT" htmlEscape="true"></spring:message><c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out></label>
															<span class="errormsg">*</span><br />
															<form:select path="localBodyNameEnglishList" class="combofield" id="ddSourceLocalBody" style="width: 175px" onchange="getLocalBodySubDistrictPanList(this.value)" htmlEscape="true">
																	<form:option value="0"  htmlEscape="true"><esapi:encodeForHTMLAttribute><spring:message code="Label.SELECTLOCALBODY" htmlEscape="true"></spring:message></esapi:encodeForHTMLAttribute></form:option>
																	<form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode"  htmlEscape="true"/>
															</form:select>
															&nbsp;<span>
																<form:errors htmlEscape="true"
																		path="localBodyNameEnglishList" class="errormsg"></form:errors>
															</span> &nbsp;&nbsp;<span class="errormsg"
																id="ddSourceLocalBody_error"> <spring:message
																		code="error.blank.DISTRICTP" htmlEscape="true"></spring:message>
															</span> <br /> <br /></td>
															</li>
													</c:if>
												</ul>
											</div>
											<div id="tr_intermediate1" style="display:none">

												<ul class = "blocklist">
													<c:if
														test="${! empty localGovtBodyForm.lgd_LBNomenclatureInter}">
														<li>
															<label><spring:message
																		code="Label.SELECT" htmlEscape="true"></spring:message>&nbsp;&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out></label>&nbsp;<span
																class="errormsg">*</span><br /> 
																<form:select htmlEscape="true"
																	path="localBodyNameEnglishListSource"
																	class="combofield" id="localGovtBodyListMain"
																	style="width: 175px">
																	<form:option value=" " htmlEscape="true">
																		<esapi:encodeForHTMLAttribute><spring:message code="Label.SELECTINTERMEDIATELOCBODY"
																			htmlEscape="true"></spring:message></esapi:encodeForHTMLAttribute>
																	</form:option>
																	<form:options items="${localBodyforSubDistList}"
																		itemLabel="localBodyNameEnglish"
																		itemValue="localBodyCode" htmlEscape="true"/>
																</form:select> <span id="localGovtBodyListMain_error" class="errormsg"><spring:message
																		code="error.blank.INTERMEDIATEP" htmlEscape="true"></spring:message>
															</span> <br /> <br />
														</li>
													</c:if>

												</ul>
											</div>
										</li>

										<li>
											<label>
												<input type="submit" name="Submit" class="btn" onclick="return validateViewLBPage();" value="<spring:message code="Button.GETDATA"></spring:message>" />
											</label>
											<label>
											<c:if test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'U')}">
												<label>
													<%-- <input type="button" name="Submit2" class="btn" value="<spring:message code="Button.CLEAR" ></spring:message>" onclick="javascript:location.href='viewLocalBodyforPRI.htm?<csrf:token uri='viewLocalBodyforPRI.htm'/>';" /> --%>
													<input type="button" name="ClearMe" class="btn" value="<spring:message code="Button.CLEAR" ></spring:message>"  onclick="clearMe()" />
												</label>
											</c:if>
											<label>
												<%-- <input type="button" class="btn" name="Submit3" value="<spring:message htmlEscape="true" code="Button.CLOSE" ></spring:message>" onclick="javascript:window.location.href='home.htm?<csrf:token uri='home.htm'/>';" /> --%>
												<input type="button" class="btn" name="CloseMe" value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>" onclick="closeMe()" />
											</label>
									    </li>

										<li>
											<input type="hidden" id="msgid" name="msgid" />
												<c:if test="${msgid != null}">
													<b style="color: red;"><c:out value="${msgid}" escapeXml="true"></c:out></b>
												</c:if>
										</li>

								</ul>
							</div>
						</div>
						<!-- End here Localbody  -->

					</div>
				</div>
				
				<!-- List section starts here -->

				<c:if test="${! empty LocalGovtBodyList}">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<table  cellpadding="0" cellspacing="0" border="0" class="display" id="localbodyListUrban">
										<thead>
											<tr class="tblRowTitle tblclear">
												<td rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
												<td rowspan="2"><spring:message code="Label.LOCALBODYCODE" htmlEscape="true"></spring:message></td>
												<td rowspan="2"><spring:message code="Label.LOCALBODYNAMEINENG" htmlEscape="true"></spring:message></td>
												<td colspan="6" align="center"><spring:message code="Label.ACTION" htmlEscape="true"></spring:message></td>
											</tr>
											
											<tr class="tblRowTitle tblclear">
												<c:if test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'U')}">
													<th align="center"><spring:message htmlEscape="true" code="Label.VIEW"></spring:message></th>
													<th align="center"><spring:message htmlEscape="true" code="Label.GovernmentOrderCorrection" ></spring:message></th>
													<th align="center"><spring:message htmlEscape="true" code="Label.ModifyName" ></spring:message></th>
													<th align="center"><spring:message htmlEscape="true" code="Label.Changecoveredarea" ></spring:message></th>
													<th align="center"><spring:message htmlEscape="true" code="Label.Mapcoveredarea" ></spring:message></th>
													<th align="center"><spring:message htmlEscape="true" code="Label.Label.Modifytype" ></spring:message></th>
												</c:if>
											</tr>
											</thead>
											
											<c:if test="${! empty LocalGovtBodyList}">
												<tbody>
												<c:forEach var="lgdLocalGovtBodyList" 	varStatus="listLocalBodyRow" items="${LocalGovtBodyList}">

													<tr class="tblRowB">
														<td align="center"><c:out value="${offsets*limits+(listLocalBodyRow.index+1)}" escapeXml="true"></c:out></td>
														<td align="left"><c:out
																value="${lgdLocalGovtBodyList.localBodyCode}" escapeXml="true"></c:out>
														</td>
														<td align="center"><c:out
																value="${lgdLocalGovtBodyList.localBodyNameEnglish}" escapeXml="true"></c:out>
														</td>
								
														<c:if test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'U')}">
															<td align="center"><a href="#"><img
																	src="images/view.png"
																	onclick="javascript:manageUDetail('ViewLocalBodyforPRIPost.htm',${lgdLocalGovtBodyList.localBodyCode},'U','A');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>

														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'U')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:manageUDetail('modifyGovtLocalBodyMain.htm',${lgdLocalGovtBodyList.localBodyCode},'U','${lgdLocalGovtBodyList.operation_state}');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
										
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'U')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:manageUDetail('modifyGovtLocalBodyMainforname.htm',${lgdLocalGovtBodyList.localBodyCode},'U','${lgdLocalGovtBodyList.operation_state}');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
												
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'U')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:manageUDetail('modifyGovtLocalBodyMainforcoveragearea.htm',${lgdLocalGovtBodyList.localBodyCode},'U','${lgdLocalGovtBodyList.operation_state}');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
												
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'U')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:manageUDetail('correctGovtLocalBodycoveragearea.htm',${lgdLocalGovtBodyList.localBodyCode},'U','${lgdLocalGovtBodyList.operation_state}');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'U')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:manageUDetail('modifyGovtLocalBodytypeforurban.htm',${lgdLocalGovtBodyList.localBodyCode},'U','${lgdLocalGovtBodyList.operation_state}');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
													
													</tr>

												</c:forEach>
												</tbody>
											</c:if>
											
											<c:if test="${localbodysize==0}">
												<c:if test="${empty LocalGovtBodyList}">
													<div class="frmpnlbg">
														<div class="frmtxt">
															<table width="100%" class="tbl_no_brdr">					
															<tr>
																<td colspan="4" align="center"><spring:message htmlEscape="true" code="error.NOPANHLOCALGOVTBODYFOUND"></spring:message></td>
															</tr>					
															</table>
														</div>
													</div>
												</c:if>
										  </c:if>	
											
											
											<!--------------- End list section here ------------------------------------->
											
											<form:input path="parentwiseId" type="hidden" name="parentwiseId" id="parentwiseId" />
											<form:input path="parentCategory" type="hidden" name="parentCategory" id="parentCategory" />
							</table>
						</div>
					</div>
				</c:if>
			</form:form>
		</div>
	</div>
<script>
function closeMe(){
    var closeUrl = "home.htm?<csrf:token uri='home.htm'/>";
	window.document.location.href=closeUrl;
}
function clearMe(){
	var clearUrl =  "viewLocalBodyforUrban.htm?<csrf:token uri='viewLocalBodyforUrban.htm'/>";
	window.document.location.href=clearUrl;
}

</script>
</body>

</html>