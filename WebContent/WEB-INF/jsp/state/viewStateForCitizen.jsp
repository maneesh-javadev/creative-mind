<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>
	
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script src="/LGD/JavaScriptServlet"></script>
<script type="text/javascript">
dwr.engine.setActiveReverseAjax(true);
/* function getData()
{
	document.getElementById('text1').value = trim(document.getElementById('text1').value);
	document.getElementById('text2').value = trim(document.getElementById('text2').value);
	
	if (document.getElementById('text1').value!='' || document.getElementById('text2').value!='')
		document.forms['form1'].submit();
	else
		alert('Please enter search text!');
}
 */
function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}

function doRefresh()
{
	document.getElementById('text1').value=document.getElementById('text2').value='';

}
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
								document.getElementById('form1').action = "viewstateforcitizen.htm?<csrf:token uri='viewstateforcitizen.htm'/>";
								document.getElementById('form1').submit();
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



</script>

</head>
<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
		<div id="frmcontent">
			<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
                <tr>
                   <td><spring:message code="Label.VIEWSTATE" htmlEscape="true"></spring:message></td>
                   <td align="right"><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a></td>
              <%--//these links are not working      <td width="50" align="right"><a href="#" class="frmhelp"><spring:message code="Label.HELP" htmlEscape="true"></spring:message></a></td> --%>
                </tr>
             </table>
		     </div>
			<div class="clear"></div>
			<div class="frmpnlbrdr">
				<form:form action="viewstateforcitizen.htm" id="form1" method="POST" commandName="stateView">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewstateforcitizen.htm"/>" />
				<div id="cat">		
					<div class="frmpnlbg">
						<div class="frmtxt" align="center">
							<div class="frmhdtitle">
								<spring:message code="Label.SEARCHCRITERIA" htmlEscape="true"></spring:message>
							</div>
						
							<table width="80%" class="tbl_no_brdr">
								<tr>
									<td class="lblBold"><label><spring:message	code="Label.STATECODE" htmlEscape="true"></spring:message></label></td>
									<td><label><form:input type="text" id="text1" onkeypress="validateNumericKeys(event)" path="code" class="frmfield" autocomplete="off"/> </label></td>
									<td align="right" class="lblBold"><label><spring:message code="Label.STATENAMEINENGLISH" htmlEscape="true"></spring:message></label></td>
									<td align="right"><label><form:input type="text" id="text2" onkeypress="validateCharKeys(event)" path="stateNameEnglish" class="frmfield" autocomplete="off"/> </label></td>

<!-- search limit dropdown removed - not required for limited records on state search - 23/05/2012 -->

								</tr>
							<tr>
								<td>&nbsp;</td>
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
											   <input type="button" name="Submit" class="btn" onclick="getData();" value="<spring:message code="Button.GETDATA"></spring:message>" /> </label><label>
											   <input type="button" name="Submit3"	class="btn" value="<spring:message code="Button.CLEAR"></spring:message>"onclick="doRefresh();" /> </label>
											   <input type="button" name="Submit3"	class="btn" value="<spring:message code="Button.CLOSE"></spring:message>"onclick="javascript:go('login.htm');" /> </label></td>
								   </td>
								</tr>
							</table>
						</div>
					</div>
					</div>
					
				  <c:if test="${! empty SEARCH_STATE_RESULTS_KEY}">								
					<div class="frmpnlbg">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" rowspan="2"><spring:message code="Label.SNO"></spring:message></td>
												<td width="16%" rowspan="2"><spring:message	code="Label.STATECODE"></spring:message></td>
												<td width="21%" rowspan="2"><spring:message code="Label.STATENAMEINENGLISH"></spring:message></td>
												<td width="21%" rowspan="2"><spring:message code="Label.STATENAMEINLOCAL"></spring:message></td>
												<td colspan="6" align="center"><spring:message code="Label.ACTION"></spring:message></td>
								</tr>
								<tr class="tblRowTitle tblclear">

												<td width="6%" align="center"><spring:message code="Label.VIEW"></spring:message></td>
												<%-- <td width="6%" align="center"><spring:message code="Label.MODIFY"></spring:message></td>
												<td width="6%" align="center"><spring:message code="Label.DELETE"></spring:message></td>
												<td width="6%" align="center"><spring:message code="Label.GOVTORDER"></spring:message></td>
												<td width="6%" align="center"><spring:message code="Label.HISTORY"></spring:message></td>
												<td width="6%" align="center"><spring:message code="Label.MAP"></spring:message></td> --%>
								</tr>
								
									<c:forEach var="listStateDetails" varStatus="listStateRow" items="${SEARCH_STATE_RESULTS_KEY}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listStateRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${listStateDetails.stateCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listStateDetails.stateNameEnglish}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listStateDetails.stateNameLocal}" escapeXml="true"></c:out></td>
     			                                        <td align="center"><a href="viewStateDetail.htm?id=${listStateDetails.stateCode}&<csrf:token uri='viewStateDetail.htm'/>"><img	src="images/view.png" width="18" height="19" border="0" /></a></td>
													   <%--  <td align="center"><a href="modifyState.htm?id=${listStateDetails.stateCode}"><img	src="images/edit.png" width="18" height="19" border="0" /></a></td> 
													    <!-- <td align="center"><a href="#"><img src="images/delete.png" width="18" height="19" border="0" /></a></td> -->
														<td align="center"><a href="#"><img src="images/gvt.order.png" width="18" height="19" /></a></td>
														<td align="center"><a href="viewStateHistory.htm?id=${listStateDetails.stateCode}"><img	src="images/history.png" width="18" height="19" border="0" /></a></td> 
														<td align="center"><a href="#"><img src="images/map.png" width="18" height="19" /></a></td> --%>
													</tr>
												</c:forEach>
											
										</table>
									</td>
								</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>

<!-- pagination removed - not required limited records for state search - 23/05/2012 -->

							 </table>
						  </div>
					   </div>
					 </c:if> 
					
					<c:if test="${fn:length(viewPage) > 0}"> 
					<c:if test="${empty SEARCH_STATE_RESULTS_KEY}">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">					
							<tr>
								<td colspan="4" align="center"><spring:message code="error.NOSTATEFOUND"></spring:message></td>
							</tr>					
						</table>
						</div>
				     </div>
				   </c:if>
			      </c:if>
			     
				</form:form>
			</div>
		</div>

</body>
</html>

