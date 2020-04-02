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
<link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">

	 
	</script>
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.VIEWSUBDISTHIST"></spring:message></h3>
			<ul class="listing">
				<%-- <li>
					<a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a>
				</li> --%>
			</ul>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
		<c:if test="${! empty subDistrictHistory}">	
			<div class="frmpnlbg">
				<div class="frmtxt">
					<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.SUBDISTRICTHISTORY"></spring:message></div>
					   <table width="100%" class="tbl_with_brdr">
						
								<tr class="tblRowTitle tblclear">
									<td width="6%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
									<td width="16%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SUBDISTRICTCODE"></spring:message></td>
									<td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SUBDISTRICTVERSION"></spring:message></td>
									<td width="35%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SUBDISTRICTNAMEENGLISH"></spring:message></td>
									 <td width="22%" rowspan="2"><spring:message htmlEscape="true"  code="Label.ACTIVESUBDISTRICT"></spring:message></td>
									<%-- <td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.CREATEDFROM"></spring:message></td> --%>
									<%-- <td width="10%" rowspan="2"><spring:message htmlEscape="true"  code="Label.CREATEDBY"></spring:message></td> --%>
									<%-- <td width="30%" rowspan="2"><spring:message htmlEscape="true"  code="Label.FLAGCODE"></spring:message></td> --%>
								</tr>
								<tr>
								
								</tr>
								
						
												
							<c:forEach var="subDistrictHistoryDetail" varStatus="listSubDistrictRow" items="${subDistrictHistory}">
								<tr class="tblRowB">
									<td width="6%"><c:out value="${listSubDistrictRow.index+1}" escapeXml="true"></c:out></td>
									<td align="left"><c:out value="${subDistrictHistoryDetail.subdistrictCode}" escapeXml="true"></c:out></td>
									<td align="left"><c:out value="${subDistrictHistoryDetail.subdistrictVersion}" escapeXml="true"></c:out></td>
									<td align="left"><c:out value="${subDistrictHistoryDetail.subdistrictNameEnglish}" escapeXml="true"></c:out></td>
									<%-- <td align="left"><c:out value="${subDistrictHistoryDetail.active}" escapeXml="true"></c:out></td> --%>
									<td align="left"><c:out value="${subDistrictHistoryDetail.lrReplaces}" escapeXml="true"></c:out></td>
									<%-- <td align="left"><c:out value="${subDistrictHistoryDetail.lrReplacedby}"></c:out></td> --%>
									<%-- <td align="left"><c:out value="${subDistrictHistoryDetail.flagCode}"></c:out></td> --%>
								</tr>
							</c:forEach>
						
					</table>
				</div>
				<%-- <div class="btnpnl">
					<input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:go('home.htm');" id="btnClose"/> </label>
				</div> --%>
		   </div>
		</c:if>
	</div>
	<c:if test="${fn:length(viewPage) > 0}"> 
				<c:if test="${empty subDistrictHistory}">
				<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
							<ul class="listing">
								<li>
									<spring:message htmlEscape="true" code="Error.noresult"></spring:message>
								</li>
							</ul>
						</div>
					</div>
				</c:if>
			</c:if> 
	
	</div>
</body>
</html>