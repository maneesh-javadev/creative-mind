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
<title><spring:message htmlEscape="true" code="Label.VIEWVILLAGE"></spring:message>
</title>

<script type="text/javascript">
function loadPage() {
		var mypath = window.location.href;

		document.getElementById('btnback').style.visibility='visible';
		document.getElementById('btnCancel').style.visibility='visible';
		document.getElementById('btnClose').style.visibility='hidden';
		var mySplitResult = mypath.split("&");

		if(mySplitResult[1]!="")
			{
		var type=mySplitResult[1].replace("type=","");
		

		if(type=='S')
			{
			document.getElementById('btnback').style.visibility='hidden';
			document.getElementById('btnCancel').style.visibility='hidden';
			document.getElementById('btnClose').style.visibility='visible';
			
			}
		else
			{
			document.getElementById('btnback').style.visibility='visible';
			document.getElementById('btnCancel').style.visibility='visible';
			document.getElementById('btnClose').style.visibility='hidden';
			}
			}
		
		
		
		
}
		</script>
</head>
<body onload="loadPage();">
	<div id="frmcontent">	
	
	

		<div class="frmhd">
			<h3 class="subtitle">
				<label><spring:message htmlEscape="true" code="Label.INVALIDATEDVILLAGE"></spring:message></label>
			</h3>
			<ul id="showhelp" class="listing">
				

				<%--//these links are not working <li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></li> --%>
			</ul>
		</div>	
		
		
		
		
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<div class="frmpnlbg">
				<div class="frmtxt">
					<div class="frmhdtitle"><spring:message htmlEscape="true" code="Label.INVALIDATEDVILLAGE"></spring:message></div>
					  <table width="100%" class="tbl_with_brdr">
						<tr>
							<td width="14%" align="center">
							<table>
								<tr class="tblRowTitle tblclear">
									<td width="6%" rowspan="2"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></td>
									<td width="16%" rowspan="2"><spring:message htmlEscape="true" code="Label.VILLAGECODE"></spring:message></td>
									<td width="21%" rowspan="2"><spring:message htmlEscape="true" code="Label.VILLAGEVERSION"></spring:message></td>
									<td width="21%" rowspan="2"><spring:message htmlEscape="true" code="Label.VILLAGENAMEINENGLISH"></spring:message></td>
									 <td width="21%" rowspan="2"><spring:message htmlEscape="true" code="Label.VILLAGESTATUS"></spring:message></td>
								</tr>
								<tr>
									<td><br />
									</td>
								</tr> <c:if test="${empty villageList}">
									<tr>
										<td colspan="1" align="left"><spring:message htmlEscape="true" code="Error.noresult"></spring:message></td>
									</tr>

								</c:if> <c:if test="${! empty villageList}">
									<c:forEach var="villageListDetail" varStatus="listVillageRow" items="${villageList}">
										<tr class="tblRowB">
											<c:forEach var="villageL" varStatus="listVRow" items="${villageListDetail}">
											<td width="6%"><c:out value="${listVillageRow.index+1}" escapeXml="true"></c:out></td>
											<td align="left"><c:out value="${villageL.villageCode}" escapeXml="true"></c:out></td>
											<td align="left"><c:out value="${villageL.villageVersion}" escapeXml="true"></c:out></td>
											<td align="left"><c:out value="${villageL.villageNameEnglish}" escapeXml="true"></c:out></td>											
											<td align="left">Invalidated</td>
									        </c:forEach>
										</tr>
									</c:forEach>																		
								</c:if>
							</table>
						</td>
					</tr>
					</table>
				</div>
				
				<div class="btnpnl">
						<input type="button" class="bttn"  name="Submit6"
								value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>"
								onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
				</div>
			</div>			
		</div>
	</div>
</body>
</html>

