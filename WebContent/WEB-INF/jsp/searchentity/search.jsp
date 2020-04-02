<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>


<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/shiftdistrict.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>
	
</script>

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>


<script src="js/common.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function mulSelCheckBox(val)
{	
	if (val)
		{
		document.getElementById('chkS').checked=true;
		document.getElementById('chkD').checked=true;
		document.getElementById('chkSD').checked=true;
		document.getElementById('chkV').checked=true;
		document.getElementById('chkR').checked=true;
		document.getElementById('chkU').checked=true;
		document.getElementById('chkAC').checked=true;
		document.getElementById('chkPC').checked=true;
		document.getElementById('chkP').checked=true;
		document.getElementById('chkT').checked=true;
		}
	else
	{
	document.getElementById('chkS').checked=true;
	document.getElementById('chkD').checked=true;
	document.getElementById('chkSD').checked=true;
	document.getElementById('chkV').checked=true;
	document.getElementById('chkR').checked=true;
	document.getElementById('chkU').checked=true;
	document.getElementById('chkAC').checked=true;
	document.getElementById('chkPC').checked=true;
	document.getElementById('chkP').checked=true;
	document.getElementById('chkT').checked=true;
	}
}


function getData() {
	 
	var errorCaptcha = document.getElementById('errorCaptcha');
	errorCaptcha.innerHTML = "";
	var sessionId = document.getElementById('sessionId').value;
	var capchaImgVal = document.getElementById('jcaptcha_response').value;
	lgdDwrCaptchaService.validateCaptchaCode('form1', sessionId,
			capchaImgVal, {
				callback : function(data) {
					var result = data;
					if ("success" == result) {
						 
						 if(validateSearchAlert())
							 {
								document.getElementById('form1').method = "post";
								document.getElementById('form1').action = "searchentity.do?<csrf:token uri='searchentity.do'/>";
								document.getElementById('form1')
										.submit();  
							 }
						 else {
								captchaResetImage('theimage', 'jcaptcha_response');

								errorCaptcha.innerHTML = "<b>" + result + "</b>";
								document.getElementById('jcaptcha_response').value = "";
								return true;
							}
						return true;

					} else {
						captchaResetImage('theimage', 'jcaptcha_response');

						errorCaptcha.innerHTML = "<b>" + result + "</b>";
						document.getElementById('jcaptcha_response').value = "";
						return true;
					}
				}
			});
}
/* 
function ruralCheckBox(val)
{	
	if (val)
		{
		document.getElementById('chkR').checked=true;
		document.getElementById('chkP').checked=true;
		document.getElementById('chkT').checked=true;
		}
	else
	{
    document.getElementById('chkR').checked=true;	
	document.getElementById('chkP').checked=true;
	document.getElementById('chkT').checked=true;
	}
} */
	function chkSearchOnLoad() {
		$("#entityName_error").hide();

}
</script>
</head>
<body onload='chkSearchOnLoad();'>

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
					<td rowspan="2"><div class="errorImg"></div>
					</td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div>
					</td>
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
	</div>




	<div id="frmcontent">
		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message htmlEscape="true" code="Label.SEARCH"></spring:message>
					</td>
					<td align="right"><a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" />
					</a>
					</td>
					<%--//these links are not working <td width="50" align="right"><a href="#" class="frmhelp"><spring:message
								htmlEscape="true" code="Label.HELP"></spring:message> --%>
					</a>
					</td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="searchentity.do" method="POST" id="form1"
				commandName="searchView">
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="searchentity.do"/>" />
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true" code="Label.SEARCHCRITERIA"></spring:message>
							</div>

							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" rowspan="3"><input type="hidden" id="sessionId"
													value='<%=sessionId%>'></input></td>
									<td width="10%" align="right"><label><spring:message
												htmlEscape="true" code="Label.ENTITYNAME"></spring:message>
									</label><span class="errormsg">*</span></td>
									<td width="76%" valign="top" height="25"><label> <form:input
												type="text" id="entityNameA" class="frmfield"
												onkeypress="validateCharKeys(event)" path="entityName"
												onfocus="validateOnFocus('entityNameA');" htmlEscape="true"
												onblur="vlidateOnblur('entityNameA','1','15','c');" /> </label> <span
										class="errormsg" id="entityName_error">Enter Search
											Criteria</span> <form:errors cssClass="errormsg" path="entityName"></form:errors>

									</td>
								</tr>
								<tr>
									<td align="right"><label> <form:checkbox
												name="checkbox" value="" path=""
												onclick="mulSelCheckBox(this.checked)" />
									</label></td>
									<td><label><spring:message htmlEscape="true"
												code="Label.SELECTALL"></spring:message>
									</label>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td valign="top"><table width="90%" class="tbl_no_brdr">
											<tr class="tbl_nml_title tblclear">
												<td width="15%" height="20"><spring:message
														htmlEscape="true" code="Label.LANDREGION"></spring:message>
												</td>
												<td width="25%"><spring:message htmlEscape="true"
														code="Label.LOCALGOVTBODY"></spring:message></td>
												<td width="25%"><spring:message htmlEscape="true"
														code="Label.ELECTIONCONSTITUENCY"></spring:message></td>
											</tr>
											<tr>
												<td><label> <form:checkbox name="checkbox"
															id="chkS" path="stateChecked" /> <spring:message
															htmlEscape="true" code="Label.STATE"></spring:message> </label></td>
												<td><label> <form:checkbox name="checkbox"
															id="chkR" path="ruralChecked" /> <%-- onclick="ruralCheckBox(this.checked)" --%>
														<spring:message htmlEscape="true" code="Label.RURALG"></spring:message>
												</label></td>
												<td><label> <form:checkbox name="checkbox"
															id="chkAC" path="acChecked" /> <spring:message
															htmlEscape="true" code="Label.ASSEMBLYCONSTITUENCY"></spring:message>
												</label></td>
											</tr>
											<tr>
												<td><label> <form:checkbox name="checkbox"
															id="chkD" path="districtChecked" /> <spring:message
															htmlEscape="true" code="Label.DISTRICT"></spring:message>
												</label></td>
												<td><label> <form:checkbox name="checkbox"
															id="chkP" path="panchayatChecked" /> <spring:message
															htmlEscape="true" code="Label.PANCHAYAT"></spring:message>
												</label></td>
												<td><label> <form:checkbox name="checkbox"
															id="chkPC" path="pcChecked" /> <spring:message
															htmlEscape="true" code="Label.PARLIAMENTCONSTITUENCY"></spring:message>
												</label></td>
											</tr>
											<tr>
												<td><label> <form:checkbox name="checkbox"
															id="chkSD" path="subDistrictChecked" /> <spring:message
															htmlEscape="true" code="Label.SUBDISTRICT"></spring:message>
												</label></td>
												<td><label> <form:checkbox name="checkbox"
															id="chkT" path="traditionalChecked" /> <spring:message
															htmlEscape="true" code="Label.TRADITIONAL"></spring:message>
												</label></td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td><label> <form:checkbox name="checkbox"
															id="chkV" path="villageChecked" /> <spring:message
															htmlEscape="true" code="Label.VILLAGE"></spring:message>
												</label></td>
												<td><label> <form:checkbox name="checkbox"
															id="chkU" path="urbanChecked" /> <spring:message
															htmlEscape="true" code="Label.ULG"></spring:message> </label></td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<form:errors cssClass="errormsg" path="selectedVal"></form:errors>
											</tr>

											<tr>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td colspan="2"><%@ include
														file="../common/captcha_integration.jspf"%> </td><td colspan="2">&nbsp;</td>
											</tr>
										</table></td>
								</tr>
							</table>

						</div>
					</div>
					<div class="btnpnl">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td><label> <input type="submit" name="Submit"
										class="btn"
										value="<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message>"
										onclick="getData();" /> </label> <label> <input
										type="button" name="Submit2" class="btn"
										value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>"
										onclick="javascript:location.href='searchentity.do?<csrf:token uri='searchentity.do'/>';" />
								</label> <label> <input type="button" name="Submit3" class="btn"
										value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
										onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
								</label></td>
							</tr>
						</table>
					</div>
				</div>


				<!-- State -->

				<c:if test="${! empty search_s}">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true" code="Label.STATEDETAIL"></spring:message>
							</div>
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" align="center">
											<tr class="tblRowTitle tblclear">
												<td rowspan="2"><spring:message htmlEscape="true"
														code="Label.SNO"></spring:message>
												</td>
												<td width="16%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.STATECODE"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.STATENAMEINENGLISH"></spring:message>
												</td>
												<%-- <td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.STATENAMEINLOCAL"></spring:message></td> --%>
												<td colspan="6" align="center"><spring:message
														htmlEscape="true" code="Label.ACTION"></spring:message>
												</td>
											</tr>
											<tr class="tblRowTitle tblclear">

												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.VIEW"></spring:message>
												</td>
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.HISTORY"></spring:message>
												</td>
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.GOVTORDER"></spring:message>
												</td>
											</tr>

											<c:forEach var="searchState" varStatus="listStateRow"
												items="${search_s}">
												<c:if test="${fn:containsIgnoreCase(searchState.type,'S')}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listStateRow.index+1}" escapeXml="true"></c:out></td>
														<%-- <td><c:out value="${searchState.type}"></c:out></td> --%>
														<td><c:out value="${searchState.stateCode}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchState.stateNameEnglish}" escapeXml="true"></c:out>
														</td>
														<td><a
															href="viewStateDetail.do?id=${searchState.stateCode}&<csrf:token uri='viewStateDetail.do'/>"><img
																src="images/view.png" width="18" height="19" border="0" />
														</a>
														</td>
														<td align="center"><a
															href="viewStateHistory.do?id=${searchState.stateCode}&<csrf:token uri='viewStateHistory.do'/>"><img
																src="images/history.png" width="18" height="19"
																border="0" />
														</a>
														</td>
														<td align="center"><a href="#"><img
																src="images/gvt.order.png" width="18" height="19" />
														</a>
														</td>
													</tr>
												</c:if>
											</c:forEach>
										</table></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>

								<c:if test="${empty search_s}">
									<tr>
										<td colspan="4" align="center"><spring:message
												htmlEscape="true" code="error.NOSTATEFOUND"></spring:message>
										</td>
									</tr>

								</c:if>

								<tr>
									<td height="30" align="right"><table width="301"
											class="tbl_no_brdr">
											<tr>
												<td width="150" align="right" class="pageno"><c:out value="${villageCount}" escapeXml="true"></c:out></td>
												<td width="96" align="right" class="pre"><a href="#" onclick="setDirection(-1)"><spring:message htmlEscape="true"  code="Label.PREVIOUS"></spring:message></a></td>
												<td width="24" align="center" class="pageno">|</td>
												<td width="51" align="right" class="nxt tblclear"><a href="#" onclick="setDirection(1)"><spring:message htmlEscape="true"  code="Label.NEXT"></spring:message></a></td>
												<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
											</tr>
										</table></td>
								</tr>
							</table>
						</div>
					</div>
				</c:if>


				<!-- District-->

				<c:if test="${! empty search_d}">

					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true" code="Label.DISTRICTDETAIL"></spring:message>
							</div>
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.SNO"></spring:message>
												</td>
												<td width="16%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.DISTRICTCODE"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.DISTRICTNAMEINENGLISH"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.STATENAME"></spring:message>
												</td>
												<td colspan="6" align="center"><spring:message
														htmlEscape="true" code="Label.ACTION"></spring:message>
												</td>
											</tr>
											<tr class="tblRowTitle tblclear">

												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.VIEW"></spring:message>
												</td>
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.HISTORY"></spring:message>
												</td>
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.GOVTORDER"></spring:message>
												</td>
											</tr>
											<c:forEach var="searchState" varStatus="listDistirctRow"
												items="${search_d}">
												<c:if test="${fn:containsIgnoreCase(searchState.type,'D')}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listDistirctRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${searchState.districtCode}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchState.districtNameEnglish}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchState.stateNameEnglish}" escapeXml="true"></c:out>
														</td>
														<td align="center"><a
															href="viewDistrictDetail.htm?id=${searchState.districtCode}"><img
																src="images/view.png" width="18" height="19" border="0" />
														</a>
														</td>
														<td align="center"><a
															href="viewDistrictHistory.htm?id=${searchState.districtCode}"><img
																src="images/history.png" width="18" height="19"
																border="0" />
														</a>
														</td>
														<td align="center"><a href="#"><img
																src="images/gvt.order.png" width="18" height="19" />
														</a>
														</td>
													</tr>
												</c:if>
											</c:forEach>
										</table></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<%-- <c:if test="${fn:length(viewPage) > 0}">  --%>
								<c:if test="${empty search_d}">
									<tr>
										<td colspan="4" align="center"><spring:message
												htmlEscape="true" code="error.NODISTFOUND"></spring:message>
										</td>
									</tr>
								</c:if>
								<%-- </c:if> --%>
								<tr>
									<td height="30" align="right">
										<table width="301" class="tbl_no_brdr">
											<tr>
		<td width="150" align="right" class="pageno"><c:out value="${villageCount}" escapeXml="true"></c:out></td>
		<td width="96" align="right" class="pre"><a href="#" onclick="setDirection(-1)"><spring:message htmlEscape="true"  code="Label.PREVIOUS"></spring:message></a></td>
		<td width="24" align="center" class="pageno">|</td>
		<td width="51" align="right" class="nxt tblclear"><a href="#" onclick="setDirection(1)"><spring:message htmlEscape="true"  code="Label.NEXT"></spring:message></a></td>
		<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
											</tr>
										</table></td>
								</tr>
							</table>
						</div>
					</div>
				</c:if>

				<!--  Sub District-->

				<c:if test="${! empty search_sd}">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true" code="Label.SUBDISTRICTDETAIL"></spring:message>
							</div>
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.SNO"></spring:message>
												</td>
												<td width="16%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.SUBDISTRICTCODE"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.SUBDISTRICTNAMEENGLISH"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.DISTRICTNAME"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.STATENAME"></spring:message>
												</td>
												<td colspan="6" align="center"><spring:message
														htmlEscape="true" code="Label.ACTION"></spring:message>
												</td>
											</tr>
											<tr class="tblRowTitle tblclear">
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.VIEW"></spring:message>
												</td>
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.HISTORY"></spring:message>
												</td>
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.GOVTORDER"></spring:message>
												</td>
											</tr>
											<c:forEach var="searchState" varStatus="listSubdistirctRow"
												items="${search_sd}">
												<c:if test="${fn:containsIgnoreCase(searchState.type,'T')}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listSubdistirctRow.index+1}" escapeXml="true"></c:out></td>

														<td><c:out value="${searchState.subdistrictCode}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchState.subdistrictNameEnglish}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchState.districtNameEnglish}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchState.stateNameEnglish}" escapeXml="true"></c:out>
														</td>
														<td align="center"><a
															href="viewSubDistrictDetail.htm?id=${searchState.subdistrictCode}"><img
																src="images/view.png" width="18" height="19" border="0" />
														</a>
														</td>
														<td align="center"><a
															href="viewSubDistrictHistory.htm?id=${searchState.subdistrictCode}"><img
																src="images/history.png" width="18" height="19"
																border="0" />
														</a>
														</td>
														<td align="center"><a href="#"><img
																src="images/gvt.order.png" width="18" height="19" />
														</a>
														</td>
													</tr>
												</c:if>
											</c:forEach>
										</table></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<%-- <c:if test="${fn:length(viewPage) > 0}">  --%>
								<c:if test="${empty search_sd}">
									<tr>
										<td colspan="4" align="center"><spring:message
												htmlEscape="true" code="error.NOSUBDISTFOUND"></spring:message>
										</td>
									</tr>
								</c:if>
								<%-- </c:if> --%>
								<tr>
									<td height="30" align="right">
										<table width="301" class="tbl_no_brdr">
											<tr>
		<td width="150" align="right" class="pageno"><c:out value="${villageCount}" escapeXml="true"></c:out></td>
		<td width="96" align="right" class="pre"><a href="#" onclick="setDirection(-1)"><spring:message htmlEscape="true"  code="Label.PREVIOUS"></spring:message></a></td>
		<td width="24" align="center" class="pageno">|</td>
		<td width="51" align="right" class="nxt tblclear"><a href="#" onclick="setDirection(1)"><spring:message htmlEscape="true"  code="Label.NEXT"></spring:message></a></td>
		<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
											</tr>
										</table></td>
								</tr>
							</table>
						</div>
					</div>
				</c:if>

				<!-- Village -->

				<c:if test="${! empty search_v}">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true" code="Label.VILLDETAIL"></spring:message>
							</div>
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.SNO"></spring:message>
												</td>
												<td width="15%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.VILLAGECODE"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.VILLAGENAMEINENGLISH"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.SUBDISTRICTNAME"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.DISTRICTNAME"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.STATENAME"></spring:message>
												</td>
												<td colspan="6" align="center"><spring:message
														htmlEscape="true" code="Label.ACTION"></spring:message>
												</td>
											</tr>
											<tr class="tblRowTitle tblclear">
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.VIEW"></spring:message>
												</td>
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.HISTORY"></spring:message>
												</td>
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.GOVTORDER"></spring:message>
												</td>
											</tr>

											<c:forEach var="searchState" varStatus="listVillageRow"
												items="${search_v}">
												<c:if test="${fn:containsIgnoreCase(searchState.type,'V')}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listVillageRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${searchState.villageCode}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchState.villageNameEnglish}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchState.subdistrictNameEnglish}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchState.districtNameEnglish}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchState.stateNameEnglish}" escapeXml="true"></c:out>
														</td>
														<td align="center"><a
															href="viewVillageDetail.htm?id=${searchState.villageCode}"><img
																src="images/view.png" width="18" height="19" border="0" />
														</a>
														</td>
														<td align="center"><a
															href="viewVillageHistory.htm?id=${searchState.villageCode}"><img
																src="images/history.png" width="18" height="19"
																border="0" />
														</a>
														</td>
														<td align="center"><a href="#"><img
																src="images/gvt.order.png" width="18" height="19" />
														</a>
														</td>
													</tr>
												</c:if>
											</c:forEach>
										</table></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<%-- <c:if test="${fn:length(viewPage) > 0}">  --%>
								<c:if test="${empty search_v}">
									<tr>
										<td colspan="4" align="center"><spring:message
												htmlEscape="true" code="error.NOSUBDISTFOUND"></spring:message>
										</td>
									</tr>
								</c:if>
								<%-- </c:if> --%>
								<tr>
									<td height="30" align="right">
										<table width="301" class="tbl_no_brdr">
											<tr>
		<td width="150" align="right" class="pageno"><c:out value="${villageCount}" escapeXml="true"></c:out></td>
		<td width="96" align="right" class="pre"><a href="#" onclick="setDirection(-1)"><spring:message htmlEscape="true"  code="Label.PREVIOUS"></spring:message></a></td>
		<td width="24" align="center" class="pageno">|</td>
		<td width="51" align="right" class="nxt tblclear"><a href="#" onclick="setDirection(1)"><spring:message htmlEscape="true"  code="Label.NEXT"></spring:message></a></td>
		<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
											</tr>
										</table></td>
								</tr>
							</table>
						</div>
					</div>
				</c:if>

				<!--  Local Government Body-->

				<c:if test="${! empty search_r}">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true" code="Label.RURALDEATIL"></spring:message>
							</div>
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.SNO"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.LOCALBODYCODE"></spring:message>
												</td>
												<td width="12%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.CATEGORY"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.LGBNAME"></spring:message>
												</td>
												<td width="24%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.PARENTLOCALBODYNAME"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.STATENAME"></spring:message>
												</td>
												<td colspan="6" align="center"><spring:message
														htmlEscape="true" code="Label.ACTION"></spring:message>
												</td>
											</tr>
											<tr class="tblRowTitle tblclear">

												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.VIEW"></spring:message>
												</td>
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.HISTORY"></spring:message>
												</td>
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.GOVTORDER"></spring:message>
												</td>
											</tr>

											<c:forEach var="searchLGB" varStatus="listLGBRow"
												items="${search_r}">
												<c:if
													test="${fn:containsIgnoreCase(searchLGB.category,'P') || fn:containsIgnoreCase(searchLGB.category,'T')}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listLGBRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${searchLGB.localBodyCode}" escapeXml="true"></c:out>
														</td>
														<c:if test="${fn:containsIgnoreCase(searchLGB.category,'P')}">
															<td align="left"><c:out value="Panchayat" escapeXml="true"></c:out>
															</td>
														</c:if>
														<c:if test="${fn:containsIgnoreCase(searchLGB.category,'T')}">
															<td align="left"><c:out value="Traditional" escapeXml="true"></c:out>
															</td>
														</c:if>
														<td align="left"><c:out
																value="${searchLGB.localBodyNameEnglish}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchLGB.parentLocalBodyNameEnglish}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchLGB.stateNameEnglish}" escapeXml="true"></c:out>
														</td>

														<td align="center"><a href="#"><img
																src="images/view.png" width="18" height="19" border="0" />
														</a>
														</td>
														<td align="center"><a href="#"><img
																src="images/history.png" width="18" height="19"
																border="0" />
														</a>
														</td>
														<td align="center"><a href="#"><img
																src="images/gvt.order.png" width="18" height="19" />
														</a>
														</td>
													</tr>
												</c:if>
											</c:forEach>
										</table></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<%-- <c:if test="${fn:length(viewPage) > 0}">  --%>
								<c:if test="${empty search_r}">
									<tr>
										<td colspan="4" align="center"><spring:message
												htmlEscape="true" code="error.NORURALLOCALGOVTBODYFOUND"></spring:message>
										</td>
									</tr>
								</c:if>
								<%-- </c:if> --%>
								<tr>
									<td height="30" align="right">
										<table width="301" class="tbl_no_brdr">
											<tr>
		<td width="150" align="right" class="pageno"><c:out value="${villageCount}" escapeXml="true"></c:out></td>
		<td width="96" align="right" class="pre"><a href="#" onclick="setDirection(-1)"><spring:message htmlEscape="true"  code="Label.PREVIOUS"></spring:message></a></td>
		<td width="24" align="center" class="pageno">|</td>
		<td width="51" align="right" class="nxt tblclear"><a href="#" onclick="setDirection(1)"><spring:message htmlEscape="true"  code="Label.NEXT"></spring:message></a></td>
		<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
											</tr>
										</table></td>
								</tr>
							</table>
						</div>
					</div>
				</c:if>



				<c:if test="${! empty search_p}">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true" code="Label.PANCHAYATDEATIL"></spring:message>
							</div>
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.SNO"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.LOCALBODYCODE"></spring:message>
												</td>
												<td width="12%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.CATEGORY"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.LGBNAME"></spring:message>
												</td>
												<td width="24%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.PARENTLOCALBODYNAME"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.STATENAME"></spring:message>
												</td>
												<td colspan="6" align="center"><spring:message
														htmlEscape="true" code="Label.ACTION"></spring:message>
												</td>
											</tr>
											<tr class="tblRowTitle tblclear">
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.VIEW"></spring:message>
												</td>
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.HISTORY"></spring:message>
												</td>
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.GOVTORDER"></spring:message>
												</td>
											</tr>
											<c:forEach var="searchLGB" varStatus="listLGDRow"
												items="${search_p}">
												<c:if test="${fn:containsIgnoreCase(searchLGB.category,'P')}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listLGDRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${searchLGB.localBodyCode}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out value="Panchayat"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchLGB.localBodyNameEnglish}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchLGB.parentLocalBodyNameEnglish}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchLGB.stateNameEnglish}" escapeXml="true"></c:out>
														</td>

														<td align="center"><a href="#"><img
																src="images/view.png" width="18" height="19" border="0" />
														</a>
														</td>
														<td align="center"><a href="#"><img
																src="images/history.png" width="18" height="19"
																border="0" />
														</a>
														</td>
														<td align="center"><a href="#"><img
																src="images/gvt.order.png" width="18" height="19" />
														</a>
														</td>
													</tr>
												</c:if>
											</c:forEach>
										</table></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<%-- <c:if test="${fn:length(viewPage) > 0}">  --%>
								<c:if test="${empty search_p}">
									<tr>
										<td colspan="4" align="center"><spring:message
												htmlEscape="true" code="error.NOPANHLOCALGOVTBODYFOUND"></spring:message>
										</td>
									</tr>
								</c:if>

								<tr>
									<td height="30" align="right">
										<table width="301" class="tbl_no_brdr">
											<tr>
		<td width="150" align="right" class="pageno"><c:out value="${villageCount}" escapeXml="true"></c:out></td>
		<td width="96" align="right" class="pre"><a href="#" onclick="setDirection(-1)"><spring:message htmlEscape="true"  code="Label.PREVIOUS"></spring:message></a></td>
		<td width="24" align="center" class="pageno">|</td>
		<td width="51" align="right" class="nxt tblclear"><a href="#" onclick="setDirection(1)"><spring:message htmlEscape="true"  code="Label.NEXT"></spring:message></a></td>
		<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
											</tr>
										</table></td>
								</tr>
							</table>
						</div>
					</div>
				</c:if>



				<c:if test="${! empty search_t}">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true" code="Label.TRADITIONALDEATIL"></spring:message>
							</div>
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.SNO"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.LOCALBODYCODE"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.CATEGORY"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.LGBNAME"></spring:message>
												</td>
												<td width="16%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.PARENTLOCALBODYNAME"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.STATENAME"></spring:message>
												</td>
												<td colspan="6" align="center"><spring:message
														htmlEscape="true" code="Label.ACTION"></spring:message>
												</td>
											</tr>
											<tr class="tblRowTitle tblclear">

												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.VIEW"></spring:message>
												</td>
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.HISTORY"></spring:message>
												</td>
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.GOVTORDER"></spring:message>
												</td>

											</tr>



											<c:forEach var="searchLGB" varStatus="listLGDRow"
												items="${search_t}">
												<c:if test="${fn:containsIgnoreCase(searchLGB.category,'T')}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listLGDRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${searchLGB.localBodyCode}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out value="Traditional"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchLGB.localBodyNameEnglish}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchLGB.parentLocalBodyNameEnglish}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchLGB.stateNameEnglish}" escapeXml="true"></c:out>
														</td>
														<td align="center"><a href="#"><img
																src="images/view.png" width="18" height="19" border="0" />
														</a>
														</td>
														<td align="center"><a href="#"><img
																src="images/history.png" width="18" height="19"
																border="0" />
														</a>
														</td>
														<td align="center"><a href="#"><img
																src="images/gvt.order.png" width="18" height="19" />
														</a>
														</td>
													</tr>
												</c:if>
											</c:forEach>
										</table></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<%-- <c:if test="${fn:length(viewPage) > 0}">  --%>
								<c:if test="${empty search_t}">
									<tr>
										<td colspan="4" align="center"><spring:message
												htmlEscape="true" code="error.NOTRADLOCALGOVTBODYFOUND"></spring:message>
										</td>
									</tr>
								</c:if>
								<%-- </c:if> --%>
								<tr>
									<td height="30" align="right">
										<table width="301" class="tbl_no_brdr">
											<tr>
		<td width="150" align="right" class="pageno"><c:out value="${villageCount}" escapeXml="true"></c:out></td>
		<td width="96" align="right" class="pre"><a href="#" onclick="setDirection(-1)"><spring:message htmlEscape="true"  code="Label.PREVIOUS"></spring:message></a></td>
		<td width="24" align="center" class="pageno">|</td>
		<td width="51" align="right" class="nxt tblclear"><a href="#" onclick="setDirection(1)"><spring:message htmlEscape="true"  code="Label.NEXT"></spring:message></a></td>
		<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
											</tr>
										</table></td>
								</tr>
							</table>
						</div>
					</div>
				</c:if>

				<c:if test="${! empty search_u}">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true" code="Label.URBANDEATIL"></spring:message>
							</div>
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.SNO"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.LOCALBODYCODE"></spring:message>
												</td>
												<td width="12%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.CATEGORY"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.LGBNAME"></spring:message>
												</td>
												<td width="24%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.PARENTLOCALBODYNAME"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.STATENAME"></spring:message>
												</td>
												<td colspan="6" align="center"><spring:message
														htmlEscape="true" code="Label.ACTION"></spring:message>
												</td>
											</tr>
											<tr class="tblRowTitle tblclear">

												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.VIEW"></spring:message>
												</td>
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.HISTORY"></spring:message>
												</td>
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.GOVTORDER"></spring:message>
												</td>
											</tr>
											<c:forEach var="searchLGB" varStatus="listLGBRow"
												items="${search_u}">
												<c:if test="${fn:containsIgnoreCase(searchLGB.category,'U')}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listLGBRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${searchLGB.localBodyCode}" escapeXml="true"></c:out>
														</td>
														<td><c:out value="Urban"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchLGB.localBodyNameEnglish}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchLGB.parentLocalBodyNameEnglish}"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchLGB.stateNameEnglish}" escapeXml="true"></c:out>
														</td>

														<td align="center"><a href="#"><img
																src="images/view.png" width="18" height="19" border="0" />
														</a>
														</td>
														<td align="center"><a href="#"><img
																src="images/history.png" width="18" height="19"
																border="0" />
														</a>
														</td>
														<td align="center"><a href="#"><img
																src="images/gvt.order.png" width="18" height="19" />
														</a>
														</td>
													</tr>
												</c:if>
											</c:forEach>
										</table></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<%-- <c:if test="${fn:length(viewPage) > 0}">  --%>
								<c:if test="${empty search_u}">
									<tr>
										<td colspan="4" align="center"><spring:message
												htmlEscape="true" code="error.NOURBANLOCALGOVTBODYFOUND"></spring:message>
										</td>
									</tr>
								</c:if>
								<%-- </c:if> --%>
								<tr>
									<td height="30" align="right">
										<table width="301" class="tbl_no_brdr">
											<tr>
		<td width="150" align="right" class="pageno"><c:out value="${villageCount}" escapeXml="true"></c:out></td>
		<td width="96" align="right" class="pre"><a href="#" onclick="setDirection(-1)"><spring:message htmlEscape="true"  code="Label.PREVIOUS"></spring:message></a></td>
		<td width="24" align="center" class="pageno">|</td>
		<td width="51" align="right" class="nxt tblclear"><a href="#" onclick="setDirection(1)"><spring:message htmlEscape="true"  code="Label.NEXT"></spring:message></a></td>
		<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
											</tr>
										</table></td>
								</tr>
							</table>
						</div>
					</div>
				</c:if>


				<!-- Election Constituency -->

				<c:if test="${! empty search_a}">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"
									code="Label.ASSCONSTITUENCYDETAIL"></spring:message>
							</div>
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.SNO"></spring:message>
												</td>
												<td width="25%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.ASSEMBLYCONSTITUENCYCODE"></spring:message>
												</td>
												<td width="26%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.ASSEMBLYCONSTITUENCYNAME"></spring:message>
												</td>
												<td width="25%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.PARLIAMENTCONSTITUENCYNAME"></spring:message>
												</td>
												<td width="35%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.STATENAME"></spring:message>
												</td>
												<td colspan="6" align="center"><spring:message
														htmlEscape="true" code="Label.ACTION"></spring:message>
												</td>
											</tr>
											<tr class="tblRowTitle tblclear">

												<td width="3%" align="center"><spring:message
														htmlEscape="true" code="Label.VIEW"></spring:message>
												</td>
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.HISTORY"></spring:message>
												</td>
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.GOVTORDER"></spring:message>
												</td>
											</tr>

											<c:forEach var="searchEC" varStatus="listACRow"
												items="${search_a}">
												<c:if test="${fn:containsIgnoreCase(searchLGB.category,'A')}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listACRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${searchEC.acCode}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchEC.acNameEnglish}" escapeXml="true"></c:out>
														</td>
														<%-- <td align="left"><c:out value="${searchEC.pcCode}"></c:out></td> --%>
														<td align="left"><c:out
																value="${searchEC.pcNameEnglish}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchEC.stateNameEnglish}" escapeXml="true"></c:out>
														</td>

														<td align="center"><a href="#"><img
																src="images/view.png" width="18" height="19" border="0" />
														</a>
														</td>
														<td align="center"><a href="#"><img
																src="images/history.png" width="18" height="19"
																border="0" />
														</a>
														</td>
														<td align="center"><a href="#"><img
																src="images/gvt.order.png" width="18" height="19" />
														</a>
														</td>
													</tr>
												</c:if>
											</c:forEach>


										</table></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<%-- <c:if test="${fn:length(viewPage) > 0}">  --%>
								<c:if test="${empty search_a}">
									<tr>
										<td colspan="4" align="center"><spring:message
												htmlEscape="true" code="error.NOASSEMBLYCONSTITUENCYFOUND"></spring:message>
										</td>
									</tr>
								</c:if>
								<%-- </c:if> --%>
								<tr>
									<td height="30" align="right">
										<table width="301" class="tbl_no_brdr">
											<tr>
		<td width="150" align="right" class="pageno"><c:out value="${villageCount}" escapeXml="true"></c:out></td>
		<td width="96" align="right" class="pre"><a href="#" onclick="setDirection(-1)"><spring:message htmlEscape="true"  code="Label.PREVIOUS"></spring:message></a></td>
		<td width="24" align="center" class="pageno">|</td>
		<td width="51" align="right" class="nxt tblclear"><a href="#" onclick="setDirection(1)"><spring:message htmlEscape="true"  code="Label.NEXT"></spring:message></a></td>
		<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
											</tr>
										</table></td>
								</tr>
							</table>
						</div>
					</div>
				</c:if>


				<c:if test="${! empty search_pc}">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"
									code="Label.PARCONSTITUENCYDETAIL"></spring:message>
							</div>
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.SNO"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.PARLIAMENTCONSTITUENCYCODE"></spring:message>
												</td>
												<td width="30%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.PARLIAMENTCONSTITUENCYNAME"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.STATENAME"></spring:message>
												</td>
												<td colspan="6" align="center"><spring:message
														htmlEscape="true" code="Label.ACTION"></spring:message>
												</td>
											</tr>
											<tr class="tblRowTitle tblclear">

												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.VIEW"></spring:message>
												</td>
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.HISTORY"></spring:message>
												</td>
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.GOVTORDER"></spring:message>
												</td>
											</tr>

											<c:forEach var="searchEC" varStatus="listECRow"
												items="${search_pc}">

												<c:if test="${fn:containsIgnoreCase(searchEC.type,'P')}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listECRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${searchEC.pcCode}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchEC.pcNameEnglish}" escapeXml="true"></c:out>
														</td>
														<td align="left"><c:out
																value="${searchEC.stateNameEnglish}" escapeXml="true"></c:out>
														</td>
														<td align="center"><a href="#"><img
																src="images/view.png" width="18" height="19" border="0" />
														</a>
														</td>
														<td align="center"><a href="#"><img
																src="images/history.png" width="18" height="19"
																border="0" />
														</a>
														</td>
														<td align="center"><a href="#"><img
																src="images/gvt.order.png" width="18" height="19" />
														</a>
														</td>
													</tr>
												</c:if>
											</c:forEach>
										</table></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<%-- <c:if test="${fn:length(viewPage) > 0}">  --%>
								<c:if test="${empty search_pc}">
									<tr>
										<td colspan="4" align="center"><spring:message
												htmlEscape="true" code="error.NOPARLIAMENTCONSTITUENCYFOUND"></spring:message>
										</td>
									</tr>
								</c:if>
								<%-- </c:if> --%>
								<tr>
									<td height="30" align="right">
										<table width="301" class="tbl_no_brdr">
											<tr>
		<td width="150" align="right" class="pageno"><c:out value="${villageCount}" escapeXml="true"></c:out></td>
		<td width="96" align="right" class="pre"><a href="#" onclick="setDirection(-1)"><spring:message htmlEscape="true"  code="Label.PREVIOUS"></spring:message></a></td>
		<td width="24" align="center" class="pageno">|</td>
		<td width="51" align="right" class="nxt tblclear"><a href="#" onclick="setDirection(1)"><spring:message htmlEscape="true"  code="Label.NEXT"></spring:message></a></td>
		<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
											</tr>
										</table></td>
								</tr>
							</table>
						</div>
					</div>
				</c:if>


			</form:form>
			<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>

</body>
</html>

