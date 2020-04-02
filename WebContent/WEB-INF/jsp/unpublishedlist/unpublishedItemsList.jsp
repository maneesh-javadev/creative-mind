<%@include file="../common/taglib_includes.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<script type="text/javascript" src="js/cancel.js"></script>
<html>
<head>
<link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript">
	function publishAll(){
		var counter=0;
		var code=null;
		
		for (var z=1;z<=document.getElementById('max').value;z++){
			if(document.getElementById(z).checked==true){
				counter++;
				if(code==null){
					code=document.getElementById(z).value + ",";
				}
				else {
					code=code + document.getElementById(z).value + ",";
				}
			}
		}
		code=code.substring(0,code.length-1);
		if(counter==0){
			alert("Kindly select entities to be selected. !");
		}
		if(counter>0){
			document.getElementById('itemCode').value="";
			document.getElementById('itemCode').value=code;
			document.forms['viewUnpublishedItems'].submit();
		} 
	}
</script>
</head>
<body>
	<form:form action="viewUnpublishedItems.htm" method="POST" commandName="viewUnpublishedItems">
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewUnpublishedItems.htm"/>"/>
		<div class="frmpnlbg"><strong><spring:message htmlEscape="true"  code="Label.VIEWUNBPUBLISHEDITEMS"></spring:message></strong>
			<div class="frmtxt">
				<table width="70%" class="tbl_no_brdr" align="center">
					<tr>
						<td width="14%" align="center">
							<table class="tbl_with_brdr" width="100%" align="center">
								<tr class="tblRowTitle tblclear">
									<td rowspan="2" align="left" width="6%"><spring:message code="Label.SNO" htmlEscape="true"></spring:message>
									</td>
									<td rowspan="2" align="left" width="6%"><spring:message code="Label.OPERATIONAL" htmlEscape="true"></spring:message>
									</td>
									<td rowspan="2" align="left" ><spring:message code="Label.ENTITYTYPE" htmlEscape="true"></spring:message>
									</td>
									<td rowspan="2" align="left" ><spring:message code="Label.ENTITYDESCRIPTION" htmlEscape="true"></spring:message>
									</td>
									<td colspan="3" align="center" ><spring:message code="Label.ACTION" htmlEscape="true"></spring:message>
									</td>
								</tr>
								<tr class="tblRowTitle tblclear">
									<td width="11%" align="center" ><spring:message code="Label.VIEW" htmlEscape="true"></spring:message> / <spring:message code="Label.MODIFY" htmlEscape="true"></spring:message></td>
									<td width="11%" align="center" ><spring:message code="Label.DELETE" htmlEscape="true"></spring:message></td>
									<td width="11%" align="center" ><spring:message code="Label.PUBLISH" htmlEscape="true"></spring:message></td>
								</tr>
								<c:forEach var="uList"
									varStatus="uListRow" items="${unpublished}">
									<tr class="tblRowB">
										<td align="center"><c:out value="${uListRow.index+1}" escapeXml="true"></c:out></td>
										<td align="center"><input type="checkbox" id="${uListRow.index+1}" value="<c:out value='${uList.itemCode}'/>">
										</td>
										<td align="left">
										<c:if test="${fn:containsIgnoreCase(uList.itemType,'V')}">Village</c:if>
										<c:if test="${fn:containsIgnoreCase(uList.itemType,'T')}">Sub District</c:if>
										<c:if test="${fn:containsIgnoreCase(uList.itemType,'D')}">District</c:if>
										<c:if test="${fn:containsIgnoreCase(uList.itemType,'S')}">State</c:if>
										</td>
										<td align="left"><c:out
												value="${uList.itemDescription}"></c:out>
										</td>
										<td align="center">
											<a href="viewUnpublishedItems.htm?idView=${uList.itemCode}">
											<img src="images/view.png" id="${uList.itemCode}" width="18" height="19" border="0" onclick="formSubmit();"/></a>
										</td>
										<td align="center">
											<a href="viewUnpublishedItems.htm?idDel=${uList.itemCode}">
											<img src="images/delete.png" width="18" id="${uList.itemCode}" height="19" border="0" /></a>
										</td>
										<td align="center">
											<a href="viewUnpublishedItems.htm?idPub=${uList.itemCode}">
											<img src="images/gvt.order.png" id="${uList.itemCode}" width="18" height="19"/></a>
										</td>
									</tr>
								</c:forEach>
								
							</table></td>
							<tr>
							<td>
								<input type="button" value="Publish All Selected" onclick="publishAll()"/>
								</td>
								</tr>
					<!-- </tr> -->
					<tr>
						<td>&nbsp;</td>

					</tr>
				</table>
			</div>
			<input type="hidden" name="itemDescription" id="itemCode"/>
			<input type="hidden" id="max" value="<c:out value='${size}'/>"/>
				<input type="button" name="Submit6"
				value="Close" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"/>
		</div>
		<!-- </div> -->
		<!-- </div> -->
	</form:form>

</body>
</html>