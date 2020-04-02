<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<head>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%!int pageNumber = 1;%>
<%
	contextPath = request.getContextPath();
	String jsPath = contextPath + "/js";
%>
<%-- <link href="<%=contextPath%>/css/displayTag.css" rel="stylesheet" type="text/css" /> --%>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>

<!--Expend and Collaps Section of Form-->
<script type="text/javascript" src="js/animatedcollapse.js"></script> 
<script type="text/javascript" language="javascript">
    
	function submitForm() {
		document.getElementById('formnomen').action = "modifyNomenclatureSubDistrict.htm";
		document.getElementById('btnModify').disabled=true;
		document.forms["formnomen"].submit();
		
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />	
</head>

<body>
	<form:form action="modifyNomenclatureSubDistrict.htm" method="GET" id="formnomen" commandName="nomenclatureSubDistForm"> 
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyNomenclatureSubDistrict.htm"/>"/>
	<div id="maincontainer" class="resize">
		
	<c:if test="${nomenclatureSubdistrictList.size()>0}">			
			<div class="frmtxt">
				<div style="width: 200px; top: -9px; left: 12px" class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.NOMENCLATUREOFSUBDISTRICT"></spring:message></div>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="3" valign="top">&nbsp;</td>
					</tr>
					<tr>
						<td valign="top" style="padding: 0px">&nbsp;</td>
					</tr>
					<tr>
						<td valign="top" style="padding: 0px">
					<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#dfdfdf">
					<tr class="tblRowTitleBold">
					<!-- Modified by Pooja on 29-04-2015 -->
						<td align="left" width="25%">
						<spring:message htmlEscape="true"  code="Label.NOSDE" ></spring:message>
						</td>
						<td align="left" width="30%">
						<spring:message htmlEscape="true"  code="Label.NOSDL" ></spring:message>
						</td>
						<td align="left" width="15%">
						<spring:message htmlEscape="true"  code="Label.ACTIVE" ></spring:message>
						</td>
						<td align="left" width="30%">
						<spring:message htmlEscape="true"  code="Label.IBDS" ></spring:message>
						</td>
						</tr>


								<c:forEach var="current" varStatus="currentRow" items="${nomenclatureSubdistrictList}">
									<tr class="tblRowB">
										<td align="left"><c:out value="${current.nomenclatureEnglish}" escapeXml="true"></c:out></td>
	          							<td align="left"><c:out value="${current.nomenclatureLocal}" escapeXml="true"></c:out></td>
	          							<td align="left">
	          							<c:if test="${current.isactive}" >
	          							<spring:message htmlEscape="true"  code="Label.ACTIVEONLY" ></spring:message>
	          							</c:if>
	          							<c:if test="${!current.isactive}"> 
	          							<spring:message htmlEscape="true"  code="Label.IACT " ></spring:message>
	          							</c:if>
	          							</td>
	          							<td align="left">
	          							<c:if test="${current.issubdistrictblocksame}" >
	          							<spring:message htmlEscape="true"  code="Label.YES" ></spring:message>
	          							</c:if>
	          							<c:if test="${!current.issubdistrictblocksame}">
	          							<spring:message htmlEscape="true"  code="Label.NO" ></spring:message>
	          							</c:if>
	          							</td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</c:if>
	</div>
		 <div class="btnpnl">
		   		<ul class="listing">
		   			<li>
						<input type="button" id="btnModify" onclick="submitForm()" value="Modify"></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" id="btnCancel" value="Close" name="Submit6" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';">
					</li>
				</ul>
		</div>
</form:form>
 <script src="/LGD/JavaScriptServlet"></script>
</body>
</html>