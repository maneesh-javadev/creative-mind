<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@include file="../common/taglib_includes.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<script type="text/javascript" src="js/cancel.js"></script>

<link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" />

<title>Label.CONTRIBUTINGBLOCK</title>
</head>
<body>
	<form:form action="rename_ContriBlock.htm" method="POST" commandName="newblockform">
		<div class="frmpnlbg"><strong>Label.CONTRIBUTINGBLOCK</strong>
			<div class="frmtxt">
				<table width="70%" class="tbl_no_brdr" align="center">
					<tr>
						<td width="14%" align="center">
							<table class="tbl_with_brdr" width="100%" align="center">
								<tr class="tblRowTitle tblclear">
									<td rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message>
									</td>
									<td colspan="2" align="center" ><spring:message htmlEscape="true" 
											code="Label.NAMEOFNEWBLOCKENGLISH"></spring:message>
									</td>
									<td colspan="2" align="center" ><spring:message htmlEscape="true" 
											code="Label.NAMEOFNEWBLOCKLOCAL"></spring:message>
									</td>
								</tr>
								<tr class="tblRowTitle tblclear">
									<td width="6%" align="center" style="color:gray;">Old</td>
									<td width="6%" align="center" style="color:gray;">New</td>
									<td width="6%" align="center" style="color:gray;">Old</td>
									<td width="6%" align="center" style="color:gray;">New</td>
								</tr>

								<c:forEach var="blockListDetail"
									varStatus="blockListRow" items="${blockList}">
									<tr class="tblRowB">
										<td width="2%"><c:out value="${blockListRow.index+1}" escapeXml="true"></c:out>
											<input type="hidden" name="aliasEnglish" value="<c:out value='${blockListDetail.blockCode}'/>"/>
										</td>
										<td align="left">
											<c:out value="${blockListDetail.blockNameEnglish}" escapeXml="true"></c:out>
										</td>
										<td>
											<form:input path="blockNameEnglish" class="frmfield" value=""/>
										</td>
										<td align="left">
											<c:out value="${blockListDetail.blockNameLocal}" escapeXml="true"></c:out>
										</td>
										<td width="6%">
											<form:input path="blockNameLocal" class="frmfield" value=""/>
										</td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>

					</tr>
				</table>
			</div>
			<input type="submit" class="btn" name="Submit3" value="Save" /> <span
				class="errormsg"> <input type="button" name="Submit6"
				value="Cancel" onclick="javascript:go('new_block.htm');"/> </span>
		</div>
	
	</form:form>

</body>
</html>