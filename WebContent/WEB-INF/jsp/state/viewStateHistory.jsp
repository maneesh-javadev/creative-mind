<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
 
		</script>

</head>
<body >
		<div id="frmcontent">
			<div class="frmhd">
				<table width="100%" class="tbl_no_brdr">
					<tr>
						<td><spring:message code="Label.VIEWSTATEHIST" htmlEscape="true"></spring:message></td>
						<%--//these links are not working <td align="right"><a href="#" class="frmhelp"><spring:message code="Label.HELP" htmlEscape="true"></spring:message> </a></td> --%>
					</tr>
				</table>
			</div>
			<div class="clear"></div>
			<div class="frmpnlbrdr">	
					<div class="frmpnlbg">
						<div class="frmtxt">
						   <div class="frmhdtitle"><spring:message code="Label.STATEHISTORY" htmlEscape="true"></spring:message></div>
							 <table width="100%" class="tbl_with_brdr">
								<tr>
									<td width="14%" align="center">
											     <tr class="tblRowTitle tblclear">
												         <td width="6%"  rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
												         <td width="16%" rowspan="2"><spring:message code="Label.STATECODE" htmlEscape="true"></spring:message></td>
												         <td width="21%" rowspan="2"><spring:message code="Label.STATEVERSION" htmlEscape="true"></spring:message></td>
												         <td width="21%" rowspan="2"><spring:message code="Label.STATENAMEINENGLISH" htmlEscape="true"></spring:message></td>
												         <td width="10%" rowspan="2"><spring:message code="Label.CREATEDFROM" htmlEscape="true"></spring:message></td>
									                    <%--  <td width="20%" rowspan="2"><spring:message code="Label.CREATEDBY" htmlEscape="true"></spring:message></td> --%>
									                     <%-- <td width="30%" rowspan="2"><spring:message code="Label.FLAGCODE" htmlEscape="true"></spring:message></td> --%>
												</tr>
											<tr><td><br/></td></tr>
											
								     <c:if test="${empty stateHistory}">
								          <tr>
									           <td colspan="1" align="left"><spring:message htmlEscape="true" code="Error.noresult"></spring:message></td>
								          </tr>
								     </c:if>
								     <c:if test="${! empty stateHistory}">
									         <c:forEach var="stateHistoryDetail" varStatus="listStateRow" items="${stateHistory}">
												  <tr class="tblRowB">
														<td width="6%"><c:out value="${listStateRow.index+1}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${stateHistoryDetail.stateCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${stateHistoryDetail.stateVersion}" escapeXml="true"></c:out></td>														
														<td align="left"><c:out value="${stateHistoryDetail.stateNameEnglish}" escapeXml="true"></c:out></td>
														<%-- <td align="left"><c:out value="${stateHistoryDetail.active}"></c:out></td> --%>
														
									                    <td align="left"><c:out value="${stateHistoryDetail.lrReplaces}" escapeXml="true"></c:out></td>
									                  <%--   <td align="left"><c:out value="${stateHistoryDetail.lrReplacedby}"></c:out></td> --%>
									                    <%-- <td align="left"><c:out value="${districtHistoryDetail.flagCode}"></c:out></td> --%>
												  </tr>
											</c:forEach>
								    </c:if>										
								 </td>
							  </tr>
 						   </table>
						</div>
				       <div class="btnpnl">
					         <table width="100%" class="tbl_no_brdr">
						        <tr>
							        <td>
<!-- BACK button removed - client requirement -->
							     <label> <input type="button" name="Submit3" class="btn" value="<spring:message code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose"/> </label>
							        </td>
						      </tr>
					     </table>
				    </div>
			     </div>
			</form>
		</div>
	</div>
</div>
</body>
</html>

