<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<script src="js/view.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script src="js/shiftvillage.js"></script>
<script type="text/javascript" language="javascript">
function setDirection(val)
{
	document.getElementById('direction').value=val;
	document.forms['form1'].action = "viewVillagePagination.htm?<csrf:token uri='viewVillagePagination.htm'/>";
	document.forms['form1'].submit();
}

function go1(id)
{
	var url="viewvillagebysubdistrictCode.htm?id="+id;
	window.location=url;
	}
dwr.engine.setActiveReverseAjax(true);
/* function getData()
{
	var chkcrvillage=document.getElementById('chkcrvillage');
	var chkchvillage=document.getElementById('chkchvillage');
	
	if(chkcrvillage.checked)
		{		
	document.getElementById('text1').value = trim(document.getElementById('text1').value);
	document.getElementById('text2').value = trim(document.getElementById('text2').value);
	
	if (document.getElementById('text1').value!='' || document.getElementById('text2').value!='')
		{
		document.forms['form1'].submit();	
		}
	else
		{
		alert('Please enter search criteria!');
		}
		}
	else if(chkchvillage.checked)
		{
			document.getElementById('text1').value='';
			document.getElementById('text2').value='';
			document.forms['form1'].submit();
			chkchvillage.checked=true;
			chkcrvillage.checked=false;
		}

} */

function getData() {

	var errorCaptcha = document.getElementById('errorCaptcha');
	errorCaptcha.innerHTML = "";
	var sessionId = document.getElementById('sessionId').value;
	var capchaImgVal = document.getElementById('jcaptcha_response').value;
	lgdDwrCaptchaService
			.validateCaptchaCode(
					'form1',
					sessionId,
					capchaImgVal,
					{
						callback : function(data) {
							var result = data;								 
							if ("success" == result) {
								document.getElementById('form1').method = "post";
								document.getElementById('form1').action = "viewvillage.htm?<csrf:token uri='viewvillage.htm'/>";
								document.getElementById('form1')
										.submit();
								return true;

							} else {
								captchaResetImage('theimage',
										'jcaptcha_response');

								errorCaptcha.innerHTML = "<b>" + result
										+ "</b>";

								return false;
							}
						}
					});
}
  
function getDatabyParent() {

	var errorCaptcha1 = document.getElementById('errorCaptcha1');
	errorCaptcha1.innerHTML = "";
	var sessionId = document.getElementById('sessionId').value;
	var capchaImgVal1 = document.getElementById('jcaptcha_response1').value;
	lgdDwrCaptchaService
			.validateCaptchaCode(
					'form1',
					sessionId,
					capchaImgVal1,
					{
						callback : function(data) {
							var result = data;								 
							if ("success" == result) {
								if (!validateAllVillage()) {									
									
									return false;
								}

								document.getElementById('form1').method = "post";
								document.getElementById('form1').action = "viewvillage.htm?<csrf:token uri='viewvillage.htm'/>";
								document.getElementById('form1')
										.submit();
								$("#ddSourceDistrict_error").hide();
								$("#ddSourceState_error").hide();
								$("#ddSourceSubDistrict_error").hide();
								return true;

							} else {
								captchaResetImage('theimage1',
										'jcaptcha_response1');

								errorCaptcha1.innerHTML = "<b>"
										+ result + "</b>";								 
										$("#ddSourceDistrict_error").hide();
										$("#ddSourceSubDistrict_error").hide();
										$("#ddSourceState_error").hide();
								return false;
							}
						}
					});
}


function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}

function loadPage()
{	
	$("#ddSourceDistrict_error").hide();
	$("#ddSourceSubDistrict_error").hide();
	$("#ddSourceState_error").hide();
document.getElementById('chkcrvillage').checked=false;
document.getElementById('chkchvillage').checked=false;
if(document.getElementById('text1').value!=''|| document.getElementById('text2').value!='')
	{
	document.getElementById('chkcrvillage').checked=true;
	document.getElementById('chkchvillage').checked=false;
	}
else if(document.getElementById('ddSourceState').value!=0)
	{
	document.getElementById('chkcrvillage').checked=false;
	document.getElementById('chkchvillage').checked=true;
	}

	}
	
function doRefresh(removeAll)
{
	document.getElementById('text1').value=document.getElementById('text2').value='';
	if (removeAll)
		{
			document.getElementById('ddSourceState').selectedIndex=0;
			removeSelectedValue('ddSourceDistrict');
		}
	removeSelectedValue('ddSourceSubDistrict');	
}

function removeSelectedValue(val)
{
  var elSel = document.getElementById(val);
  var i;
  for (i = elSel.length - 1; i>=0; i--) 
   	elSel.remove(i);
}


</script>


<title><spring:message htmlEscape="true" code="Label.VIEWVILLAGE"></spring:message></title>

</head>
<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);" onload="loadPage();">
		
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
						             <td><spring:message htmlEscape="true" code="Label.VIEWVILLAGE"></spring:message></td>
						             <td align="right"><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a></td>
						         <%--//these links are not working     <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></td> --%>
					     </tr>
				  </table>
			</div>
			   <div class="clear"></div>
			   <div class="frmpnlbrdr">
				   <form:form action="viewvillage.htm" id="form1" method="POST" commandName="villagebean">
				   <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewvillage.htm"/>"/>
				       <div id="cat">				       
				       		 <div class="frmpnlbg">
						      <div class="frmtxt" align="center">
							     <div class="frmhdtitle"><spring:message htmlEscape="true" code="Label.FILTEROPTFORVIEWVILL"></spring:message></div>
						          <table width="800" class="tbl_no_brdr">
						          <div>
						             <td width="86%">
                                          <table width="900" height="21" class="tbl_no_brdr">
                                            <tr>
                                              <td class="tblclear" align="center"><label>
                                               <form:radiobutton htmlEscape="true" path="correctionRadio" id='chkcrvillage'  value='N'
                                                onclick='toggledisplay2("chkcrvillage","correctionvillage");doRefresh(true);'/>
                                                 </label></td>
                                              <td width="120" class="lblBold" align="left"><label><spring:message htmlEscape="true" code="Label.SEARCHBYNAME"></spring:message></label></td>
                                              <td>&nbsp;&nbsp;</td>
                                              <td class="tblclear"><label>
                                                <form:radiobutton htmlEscape="true" path="correctionRadio" id='chkchvillage'  value='P'
                                                 onclick='toggledisplay2("chkchvillage","changevillage");doRefresh(true);' />
                                              </label></td>
                                              <td width="128" class="lblBold" align="left"><label><spring:message htmlEscape="true" code="Label.SEARCHBYHIERARCHY"></spring:message></label></td>
                                              <td colspan="5" align="right" class="lblBold" ><label><spring:message htmlEscape="true" code="Label.NOOFRECORDPERPAGE"></spring:message></label><label>
											<form:select htmlEscape="true" path="pageRows" class="combofield" style="width: 50px">
															<form:option value="5" label="5" />
															<form:option value="10" label="10" />
															<form:option value="25" label="25" selected="selected" />
															<form:option value="50" label="50" />
															<form:option value="100" label="100" />
										</form:select>  </label>
								  		 </td>
                                            </tr>
                                          
                                          </table>
                                      </td>				          						          
                                 </table>
                             </div>
						</div>
					</div>
					      <div class="frmpnlbg" id='correctionvillage' style=" visibility: hidden; display:none">
						      <div class="frmtxt" align="center">
							     <div class="frmhdtitle"><spring:message htmlEscape="true" code="Label.SEARCHCRITERIA"></spring:message></div>
						          <table width="800" class="tbl_no_brdr">
								    <tr>
									   <td width="120" class="lblBold"><spring:message htmlEscape="true"	code="Label.VILLAGECODE"></spring:message></td>
									   <td><label><form:input type="text" class="frmfield" id="text1" onkeypress="validateNumericKeys(event)"  path="code" autocomplete="off"/> </label></td>
									   <td width="230" align="right" class="lblBold" ><spring:message htmlEscape="true" code="Label.VILLAGENAMEINENGLISH"></spring:message></td>
									   <td align="right"><label><form:input type="text" class="frmfield" id="text2" onkeypress="validateCharKeys(event)" path="villageNameEnglish" autocomplete="off"/> </label></td>
								  </tr>
								<tr>
								<td>&nbsp;<input type="hidden" id="sessionId"
													value='<%=sessionId%>'></input></td>
							</tr>
							<tr>
								<td colspan="4">  <%@ include
										file="../common/captcha_integration.jspf"%>  
								</td>
							</tr>
								<tr>
								<td>&nbsp;</td>
							</tr>
								
								<tr>
									<td height="50" colspan="4" align="center"><label>
											<input type="button" name="Submit" class="btn" onclick="getData();" value=<spring:message htmlEscape="true" code="Button.GETDATA"></spring:message> /> </label><label>
											<input type="button" name="Submit2" class="btn" value=<spring:message htmlEscape="true" code="Button.CLEAR"></spring:message> onclick="doRefresh(true);" /> </label><label>
											<input type="button" name="Submit3" class="btn"	value=<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message> onclick="javascript:go('login.htm');" /> </label></td>

								</tr>
							</table>
						</div>
					</div>
				
					<div class="frmpnlbg" id='changevillage' style=" visibility: hidden; display:none">
						 <div class="frmtxt" align="center">
						 <div class="frmhdtitle"><spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message></div>
						<table width="580" class="tbl_no_brdr">
							<tr>
								<td width="14%" rowspan="9">&nbsp;</td>
								<td width="86%" class="lblBold" align="left"><label><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message></label><br />
									<form:select htmlEscape="true" path="stateNameEnglish" class="combofield" style="width: 200px" id="ddSourceState" onchange="getDistrictList(this.value);document.getElementById('ddSourceState').selectedIndex==0?doRefresh(true):false;">
									            <form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
									            <form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
									</form:select> <span class="errormsg" id="ddSourceState_error">Please Select State</span>
							</tr>
<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td class="lblBold" align="left"><label><spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message></label><br />
									           <form:select htmlEscape="true" path="districtNameEnglish" class="combofield" style="width: 200px" id="ddSourceDistrict" onchange="getSubDistrictList(this.value);document.getElementById('ddSourceDistrict').selectedIndex==0?doRefresh(false):false;">
									           </form:select> <span class="errormsg" id="ddSourceDistrict_error">Please Select District</span></td>
							</tr>
<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td class="lblBold" align="left"><label><spring:message htmlEscape="true" code="Label.SELECTSUBDISTRICT"></spring:message></label><br />
									<form:select htmlEscape="true" path="subdistrictNameEnglish" class="combofield" style="width: 200px" id="ddSourceSubDistrict" >
									</form:select> <span class="errormsg" id="ddSourceSubDistrict_error">Please Select Subdistrict</span></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td colspan="4">  <%@ include
										file="../common/captcha_integration_sec.jspf"%>  
								</td>
							</tr>
								<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
									<td height="50" colspan="4" align="left"><label>
											<input type="button" name="Submit" class="btn"  onclick="getDatabyParent();" value=<spring:message htmlEscape="true" code="Button.GETDATA"></spring:message>  /> </label><label>
											<input type="button" name="Submit2" class="btn" value=<spring:message htmlEscape="true" code="Button.CLEAR"></spring:message> onClick="doRefresh(true);" /> </label><label>
											<input type="button" name="Submit3" class="btn"	value=<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message> onclick="javascript:go('login.htm');" /> </label></td>

								</tr>
					  </table>
					</div>
				  </div>

			
			      <c:if test="${! empty SEARCH_VILLAGE_RESULTS_KEY}">
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">
							
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" rowspan="2"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></td>
												<td width="16%" rowspan="2"><spring:message htmlEscape="true"	code="Label.VILLAGECODE"></spring:message></td>
												<td width="21%" rowspan="2"><spring:message htmlEscape="true" code="Label.VILLAGENAMEINENGLISH"></spring:message></td>
												<td width="21%" rowspan="2"><spring:message htmlEscape="true" code="Label.VILLAGENAMEINLOCAL"></spring:message></td>
												<td colspan="6" align="center"><spring:message htmlEscape="true" code="Label.ACTION"></spring:message></td>
								</tr>
								<tr class="tblRowTitle tblclear">

												<td width="6%" align="center"><spring:message htmlEscape="true" code="Label.VIEW"></spring:message></td>
											
								</tr>
															
									<c:forEach var="listVillageDetail" varStatus="listVillageRow" items="${SEARCH_VILLAGE_RESULTS_KEY}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listVillageRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${listVillageDetail.villageCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listVillageDetail.villageNameEnglish}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listVillageDetail.villageNameLocal}" escapeXml="true"></c:out></td>
														<td align="center"><a href="viewVillageDetail.htm?id=${listVillageDetail.villageCode}&<csrf:token uri="viewVillageDetail.htm"/>"><img	src="images/view.png" width="18" height="19" border="0" /></a></td>
														
													</tr>
								  </c:forEach>											
								</table>
							  </td>
						   </tr>
						<tr>
							<td>&nbsp;</td>
						</tr>

						<tr>
							<td height="30" align="right">
									     <table width="301">
											<tr>
												<td width="150" align="right" class="pageno"><c:out value="${villageCount}" escapeXml="true"></c:out></td>
												<td width="96" align="right" class="pre"><a href="#" onclick="setDirection(-1)"><spring:message htmlEscape="true" code="Label.PREVIOUS"></spring:message></a></td>
												<td width="24" align="center" class="pageno">|</td>
												<td width="51" align="right" class="nxt tblclear"><a href="#" onclick="setDirection(1)"><spring:message htmlEscape="true" code="Label.NEXT"></spring:message></a></td>
												<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						  </div>
					    </div>
					 </c:if>

				<c:if test="${fn:length(viewPage) > 0}">
					<c:if test="${empty SEARCH_VILLAGE_RESULTS_KEY}">
						<div class="frmpnlbg" id="divData">
							<div class="frmtxt">
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td colspan="4" align="center"><spring:message htmlEscape="true" code="error.NOVILLFOUND"></spring:message>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</c:if>
				</c:if>
			     <input type="hidden" name="direction" id="direction" value="0" />
			     <input type="hidden" name="pageType" value="CV" />
				  </form:form>	
				  <script src="/LGD/JavaScriptServlet"></script>
			  </div>
		</div>
</body>
</html>

