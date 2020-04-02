<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%@include file="../common/taglib_includes.jsp"%>
<script src="js/govtorder.js"></script>
<script src="js/new_ward.js"></script>
<script src="js/lgd_localbody.js"></script>

<script src="js/validationWard.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />

<link href="css/errorLB.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>

<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>

<script src="js/common.js"></script>
<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<link rel="stylesheet" href="datepicker/demos.css" /> -->
<script src="js/browserSniffer.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
</script>
<script type="text/javascript" language="javascript">

function manageUrbanWard(url,id)
{
	dwr.util.setValue('urbanwardId', id, {	escapeHtml : false	});
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}

function manageUrbanDeleteWard(url,id)
{
	var reset = confirm("Do you wish to delete the selected Ward?");
	if(reset==true)
	{	
		dwr.util.setValue('urbanwardId', id, {	escapeHtml : false	});
		//document.getElementById('form1').method = "GET";
		document.getElementById('form1').action = url;
		document.getElementById('form1').submit();
		return true;
	}
	else
	{
		return false;
	}	
}

function clearUrbanWard(){
	document.getElementById('form1').method = 'GET';
	document.getElementById('form1').action = 'viewwardforUrban.htm?<csrf:token uri='viewwardforUrban.htm'/>';
	document.getElementById('form1').submit();
}
dwr.engine.setActiveReverseAjax(true);
</script>
</head>
<body onload="loadViewWARDforUrbanForm();">

	<%-- <div class="overlay" id="overlay1" style="display: none;"></div>
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
									<c:out value="${param.family_msg}"></c:out>
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
								<c:out value="${family_error}"></c:out>
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
					<td><div class="errorfont">
							<spring:message htmlEscape="true" code="error.blank.commonAlert"></spring:message>
						</div></td>
				</tr>
			</table>

		</div>
	</div>

	<div id="helpDiv" class="helpMessage">
		<div class="helpheader">
			<h4>Help Message</h4>
		</div>
		<div id="helpMsgText" class="helptext"></div>
	</div> --%>



	<div id="frmcontent">
		<div class="frmhd">

			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message htmlEscape="true" code="Label.VIEWWARD"></spring:message>
					</td>
					<td align="right"><a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a></td>
				<%--//these links are not working 	<td width="50" align="right" valign="middle"><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></td> --%>
					<td width="50" align="right"><a href="#" class="frmhelp">Help</a>
					</td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">

			<form:form action="viewUrbanWard.htm" id="form1"
				onsubmit="return validateUrbanWardForm()" method="POST"
				commandName="localGovtBodyForm" enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="viewUrbanWard.htm"/>" />
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="100%">
										<div class="frmpnlbg">
											<div class="frmtxt">
												<div class="frmhdtitle">
													<spring:message htmlEscape="true" code="Label.SEARCHCRIT"></spring:message>
												</div>
												<table width="100%" class="tbl_no_brdr">
													<tr>
														<td width="14%"><input type="hidden" name="stateCode" value="<c:out value='${stateCode}'/>"/>
															<td width="30%"><spring:message htmlEscape="true"
																	code="Label.SELECTLOCALBODYTYPE"></spring:message> <span
																class="errormsg">*</span> <br /> <form:select
																	path="lgd_LBTypeName" id="localBodyType"
																	onchange="hideShowDivforUrbanWard(this.value);getPanchayatListbyStateandlbTypeCode(this.value);"
																	onfocus="validateOnFocus('localBodyType');"
																	onblur="vlidateOnblur('localBodyType','1','15','c');"
																	class="combofield" style="width: 175px">
																	<form:option value="0">
																		<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
																	</form:option>

																	<c:forEach var="localBodyTypelist" varStatus="var"
																		items="${localBodyTypelist}">
																		<form:option id="typeCode"
																			value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.level}:${localBodyTypelist.category}"><c:out value="${localBodyTypelist.nomenclatureEnglish}" escapeXml="true"></c:out></form:option>
																	</c:forEach>
																</form:select> <%-- <span class="errormsg" id="localBodyType_error"><spring:message
																		htmlEscape="true" code="error.choose.WARDLBTYPE"></spring:message></span> --%>
																		
																<div id="localBodyType_error" class="error"><spring:message code="error.choose.WARDLBTYPE" htmlEscape="true"></spring:message></div>
																<div id="localBodyTypeMsg" style="display:none"><spring:message code="error.choose.WARDLBTYPE" htmlEscape="true"/></div>
																<div class="errormsg" id="localBodyType_error1"><form:errors path="lgd_LBTypeName" htmlEscape="true"/></div>  
																<div class="errormsg" id="localBodyType_error2" style="display: none" ></div>		
														</td>
														<td width="56%">&nbsp;</td>
														</td>
													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<div id="tr_district1">
															<td width="14%">
																<td width="30%">
																	<c:out value="${localGovtBodyForm.districtPanchayatName}" escapeXml="true"></c:out>&nbsp;<label><spring:message
																			htmlEscape="true" code="Label.SELLOCALGOVTBODY"></spring:message></label><span
																	class="errormsg">*</span><br /> <form:select
																		path="lb_lgdPanchayatName" class="combofield"
																		id="wardUrbanLocalBody" style="width: 160px"
																		onfocus="validateOnFocus('wardUrbanLocalBody');"
																		onblur="vlidateOnblur('wardUrbanLocalBody','1','15','c');">
																		<form:option value="0">
																			<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
																		</form:option> 
																	</form:select>&nbsp;<span> <form:errors htmlEscape="true"
																			path="lb_lgdPanchayatName" class="errormsg"></form:errors>
																</span> &nbsp;&nbsp;<%-- <span class="errormsg"
																	id="wardUrbanLocalBodyy_error"> <spring:message htmlEscape="true"
																			code="error.blank.DISTRICTP"></spring:message> </span>  --%>

																	<%-- <span class="errormsg" id="wardUrbanLocalBody_error"><spring:message
																			htmlEscape="true" code="error.choose.WARDLB"></spring:message></span> --%>
																	
																	<div id="wardUrbanLocalBody_error" class="error"><spring:message code="error.blank.TEHSILP" htmlEscape="true"></spring:message></div>
																	<div id="wardUrbanLocalBodyMsg" style="display:none"><spring:message code="error.blank.TEHSILP" htmlEscape="true"/></div>
																	<div class="errormsg" id="wardUrbanLocalBody_error1"><form:errors path="lb_lgdPanchayatName" htmlEscape="true"/></div>  
																	<div class="errormsg" id="wardUrbanLocalBody_error2" style="display: none" ></div>		
															</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
															</td>
														</div>
													</tr>
													<tr>
														<td><form:input htmlEscape="true"
																path="lgd_lbCategory" id="hiddenCheckBox" type="hidden"
																class="frmfield"
																value="${localGovtBodyForm.lgd_lbCategory}"/></td>

													</tr>

													<tr>
														<td height="50" colspan="4" align="center"><label>
																<input type="submit" name="Submit" class="btn"
																onclick="return validateViewUrbanWardAll();"
																value=<spring:message htmlEscape="true" code="Button.GETDATA"></spring:message> />
														</label> 
														<label> <input type="button" name="Submit2"
																class="btn"
																value='<spring:message code="Button.CLEAR"></spring:message>'
																onclick="clearUrbanWard();" /></label>


														 <label><input type="button" name="Submit3"
																class="btn"
																value="<spring:message code="Button.CLOSE" ></spring:message>"
																onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
														</label></td>
														<!-- </label> -->
														<!-- </td> -->
													</tr>
												</table>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
	
		
			<c:if test="${! empty wardList}">
				<div class="frmpnlbg">
					<div class="frmtxt">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td width="14%" align="center">
									<table class="tbl_with_brdr" width="98%" align="center">
										<tr class="tblRowTitle tblclear">
											<td rowspan="2"><spring:message htmlEscape="true"
													code="Label.SNO"></spring:message></td>
											<td width="20%" rowspan="2"><spring:message
													htmlEscape="true" code="Label.WARDNUMBER"></spring:message>
											</td>
											<td width="20%" rowspan="2"><spring:message
													htmlEscape="true" code="Label.WARDNAMEINENG"></spring:message>
											</td>
											<td colspan="21%" rowspan="2"><spring:message
													htmlEscape="true" code="Label.WARDNAMEINLOCAL"></spring:message>
											</td>

											<td colspan="6" align="center"><spring:message
													htmlEscape="true" code="Label.ACTION"></spring:message></td>
										</tr>
										<tr class="tblRowTitle tblclear">

											<td width="6%" align="center"><spring:message
													htmlEscape="true" code="Label.VIEW"></spring:message></td>
											<td width="6%" align="center"><spring:message
												code="Label.MODIFY"></spring:message></td>
											<td width="6%" align="center"><spring:message
													htmlEscape="true" code="Label.DELETE"></spring:message></td>
										</tr>



										<c:forEach var="listWardDetails" varStatus="listLocalBodyRow"
											items="${wardList}">
											<tr class="tblRowB">
												<td width="6%"><c:out value="${listLocalBodyRow.index+1}" escapeXml="true"></c:out></td>
												<td width="16%" align="left"><c:out
														value="${listWardDetails.wardNumber}" escapeXml="true"></c:out></td>
												<td width="16%" align="left"><c:out
														value="${listWardDetails.wardNameInEnglish}" escapeXml="true"></c:out></td>
												<td colspan="21%" align="left"><c:out
														value="${listWardDetails.wardNameInLocal}" escapeXml="true"></c:out></td>
												<%-- <td align="center"><a href="viewWardDetails.htm?id=${listWardDetails.wardCode}&<csrf:token uri='viewWardDetails.htm'/>"><img
														src="images/view.png" width="18" height="19" border="0" /></a></td> --%>
														
								 				<td  width="8%" align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageUrbanWard('viewUrbanWardDetails.htm',${listWardDetails.wardCode});" width="18" height="19" border="0" /></a></td>
								
												<%-- <td align="center"><a href="modifyWard.htm?id=${wardList.wardCode}"><img	src="images/edit.png" width="18" height="19" border="0" /></a></td>   --%>
											<%-- 	<td align="center"><a href="invalidateWard.htm?wardCode=${listWardDetails.wardCode}&<csrf:token uri="invalidateWard.htm"/>"><img
														src="images/delete.png" width="18" height="19" border="0" /></a></td> --%>
												<td width="8%" align="center"><a href="#"><img	src="images/edit.png" onclick="javascript:manageUrbanWard('modifyUrbanWard.htm',${listWardDetails.wardCode});" width="18" height="19" border="0" /></a></td>
														
								 				<td  width="8%" align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageUrbanDeleteWard('invalidateUrbanWard.htm',${listWardDetails.wardCode});" width="18" height="19" border="0" /></a></td>
										

											</tr>
										</c:forEach>
 										<form:input path="urbanwardId" type="hidden" name="urbanwardId" id="urbanwardId"  />
 										</table>
 										
 									</td>
								</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
						
							<!-- <tr>
								<td height="30" align="right"><table width="301" class="tbl_no_brdr">
										<tr>
											<td width="99" align="right" class="pageno">(1 - 50 of 464)</td>
											<td width="96" align="right" class="pre"><a href="#"></a></td>
											<td width="24" align="center" class="pageno">|</td>
											<td width="51" align="right" class="nxt tblclear"><a href="#"></a></td>
											<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
										</tr>
									</table>
								</td>
							</tr> -->
							</table>
						 </div>
					   </div>
					    </c:if>
 							<c:if test="${fn:length(viewPage) > 0}">
								<c:if test="${empty wardList}">
									<tr>
										<td colspan="4" align="center"><spring:message htmlEscape="true" code="Error.noresult"></spring:message></td>
									</tr>
								</c:if>
							</c:if>
		</form:form>	
			<script src="/LGD/JavaScriptServlet"></script>				
		</div>
	</div>

</body>
</html>