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

</title>

<script type="text/javascript">

		</script>
</head>
<body>
	<div id="frmcontent">
	
		<div class="frmhd">
			<h3 class="subtitle">
				<label><spring:message htmlEscape="true" code="Label.MANAGEVILLAGE"></spring:message></label>
			</h3>
			<ul id="showhelp" class="listing">												
				<%--//these links are not working <li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></li> --%>
			</ul>
		</div>	
		
		<div class="clear"></div>
		<div class="frmpnlbrdr">
	     <c:if test="${! empty villageHistory}">	
			<div class="frmpnlbg">
				<div class="frmtxt">
					<div class="frmhdtitle"><spring:message htmlEscape="true" code="Label.VILLAGEHISTORY"></spring:message></div>
					  <table width="100%" class="tbl_with_brdr">
						<tr>
							<td width="14%" align="center">
							<table>
								<tr class="tblRowTitle tblclear">
									<td width="6%" rowspan="2"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></td>
									<td width="16%" rowspan="2"><spring:message htmlEscape="true" code="Label.VILLAGECODE"></spring:message></td>
									<td width="21%" rowspan="2"><spring:message htmlEscape="true" code="Label.VILLAGEVERSION"></spring:message></td>
									<td width="21%" rowspan="2"><spring:message htmlEscape="true" code="Label.VILLAGENAMEINENGLISH"></spring:message></td>
									 <td width="21%" rowspan="2"><spring:message htmlEscape="true" code="Label.ACTIVEVILLAGE"></spring:message></td>
									<td width="40%" rowspan="2"><spring:message htmlEscape="true" code="Label.CREATEDFROM"></spring:message></td>
									<%-- <td width="10%" rowspan="2"><spring:message htmlEscape="true" code="Label.CREATEDBY"></spring:message></td> --%>
									<%-- <td width="30%" rowspan="2"><spring:message htmlEscape="true" code="Label.FLAGCODE"></spring:message></td> --%>
								</tr>
								<tr>
									<td><br />
									</td>
								</tr>
								
									<c:forEach var="villageHistory" varStatus="listVillageRow" items="${villageHistory}">
										<tr class="tblRowB">
											<td width="6%"><c:out value="${listVillageRow.index+1}" escapeXml="true"></c:out></td>
											<td align="left"><c:out value="${villageHistory.villageCode}" escapeXml="true"></c:out></td>
											<td align="left"><c:out value="${villageHistory.villageVersion}" escapeXml="true"></c:out></td>
											<td align="left"><c:out value="${villageHistory.villageNameEnglish}" escapeXml="true"></c:out></td>
											<td align="left"><c:out value="${villageHistory.active}" escapeXml="true"></c:out></td>
											<td align="left"><c:out value="${villageHistory.lrReplaces}" escapeXml="true"></c:out></td>
									        <%-- <td align="left"><c:out value="${villageHistory.lrReplacedby}"></c:out></td> --%>
									        <%-- <td align="left"><c:out value="${villageHistoryDetail.flagCode}"></c:out></td> --%>
										</tr>
									</c:forEach>
							</table>
							</td>
						</tr>
					</table>
				</div>
				<div class="btnpnl">					
						<!-- BACK button removed - client requirement -->
						<label><input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose"/></label>						   
				</div>
			  </div>
		   </c:if>
		</div>
		
		<c:if test="${fn:length(viewPage) > 0}"> 
					<c:if test="${empty villageHistory}">
					<div class="frmpnlbg" id="divData">
							<div class="frmtxt">
								<spring:message htmlEscape="true" code="Error.noresult"></spring:message>
							</div>
						</div>
					</c:if>
				</c:if> 
	</div>
</body>
</html>

