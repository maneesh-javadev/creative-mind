<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<%--
 * FCKeditor - The text editor 
 *@author : Ajay Kumar on 11th January 2010
--%>
<!-- <script type='text/javascript' src="../../../fckeditor/editor/js/oporations.js"></script> -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" language="javascript">
	function validateSelectAndSubmit() {
		document.getElementById('templateForm').action = "createTemplate.htm";
		document.forms["templateForm"].submit();
	}
	function editTemplate()
	{
/* 		go("createGovtTemplate.htm?mode=editmode"); */
		
		document.getElementById('templateForm').action = "createGovtTemplateAfterPreview.htm";
		document.forms["templateForm"].submit();
/* 		document.getElementById('templateForm').action = "createGovtTemplate.htm?mode=edit";
		document.forms["templateForm"].submit(); */
	}
</script>
<link href="../sample.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="../fckeditor.gif" type="image/x-icon" />
<script type="text/javascript">
	function FCKeditor_OnComplete(editorInstance) {
		window.status = editorInstance.Description;
	}
	
	function insertVarFckEditor(){
// 		alert("insertLblInFckEditor");
		var EditorInstance = FCKeditorAPI.GetInstance("templateBodySrc"); //message is name of field to be validate
		
//		alert(document.getElementById("selType").value);
		EditorInstance.EditorDocument.body.innerHTML+="{$"+document.getElementById("selType").value+"} ";

		return false;
	}
	
</script>

</head>
<body
	onload="toggledisplay('districtdelete_yes','fromothersubdistrict')">
	<div id="frmcontent">
		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message htmlEscape="true"  code="Label.GOVTORDERTEMPLATE"></spring:message>
					</td>
					<td align="right"><a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a>
					</td>
				<%-- 	//these links are not working <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a> --%>
					</td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>

		<div class="frmpnlbrdr">

			<form:form action="updateTemplate.htm" id="templateForm"
				name="templateForm" method="POST"
				commandName="governmentOrderTemplateForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="updateTemplate.htm"/>"/>
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"  code="Label.PREVIEWTEMPLATE"></spring:message>
							</div>
							<table width="100%" border="1">
								<%-- <tr>
									<td>&nbsp;</td>
									<td><spring:message htmlEscape="true"  code="Label.GOVTORDERTEMPLATE"></spring:message></td>
									<td>&nbsp;</td>
								</tr> --%>
								<tr>
									<td width="15%" valign="top"><spring:message htmlEscape="true"  code="Label.TEMPLATENAMEENG"></spring:message></td>
									<td width="20%" valign="top"><c:out value="${govtOrderTemplateForm.templateNameEnglish}" escapeXml="true"></c:out>
									<form:hidden path="templateNameEnglish" value="${govtOrderTemplateForm.templateNameEnglish}" id="templateNameEnglish" ></form:hidden></td>
									<form:hidden path="templateCode" id="templateCode" style="width: 200px" htmlEscape="true" class="frmfield"></form:hidden>
									<td width="65%">&nbsp;</td>
								</tr>
								<tr>
									<td width="15%" valign="top"><spring:message htmlEscape="true"  code="Label.OPERATION"></spring:message></td>
									<td width="20%" valign="top">
									<c:out value="${govtOrderTemplateForm.operationName}" escapeXml="true"></c:out>
									<form:hidden  path="operations" value="${govtOrderTemplateForm.operations}"></form:hidden>
									</td>
									<td width="65%">&nbsp;</td>
								</tr>
								<tr>
									<td width="15%" valign="top"><spring:message htmlEscape="true"  code="Label.TYPEOFGOVTORDER"></spring:message></td>
									<td width="70%" valign="top">
									<c:out value="${govtOrderTemplateForm.orderType}" escapeXml="true"></c:out>
									<form:hidden  path="orderType" value="${govtOrderTemplateForm.orderType}"></form:hidden>
									</td>
									<td width="15%">&nbsp;</td>
								</tr>
								<tr>
									<td width="15%" valign="top"><spring:message htmlEscape="true"  code="Label.TEMPLATELANGUAGE"></spring:message></td>
									<td width="70%" valign="top">
									<c:out value="${govtOrderTemplateForm.templateLanguage}" escapeXml="true"></c:out>
									<form:hidden  path="templateLanguage" value="${govtOrderTemplateForm.templateLanguage}"></form:hidden>
									</td>
									<td width="15%">&nbsp;</td>
								</tr>
								<tr>
									<td width="15%" valign="top"><spring:message htmlEscape="true"  code="Label.TEMPLATESRC"></spring:message></td>
									<td width="70%" valign="top">
									<c:out value="${govtOrderTemplateForm.templateBodySrc}" escapeXml="true"/>
									<form:hidden  path="templateBodySrc" value="${govtOrderTemplateForm.templateBodySrc}"></form:hidden>
									</td>
									<td width="15%">&nbsp;</td>
								</tr>
							</table>
						</div>
					</div>
					

					<div class="btnpnl">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td width="14%">&nbsp;</td>
								<td width="86%">
									<div>
<!-- BACK button removed - client requirement -->
											<%-- <label> <input type="button" name="Submit9"
											class="btn"
											value="<spring:message htmlEscape="true"  code="Button.EDITTEMPLATE"></spring:message>"
											onclick="javascript:editTemplate();" /> </label> --%> 
											<label> <input type="submit"
												name="Submit" class="btn"
												value="<spring:message htmlEscape="true" code="Button.SAVE"></spring:message>" />
											</label>
											
											<label>
											<input type="button" name="Submit6" class="btn"
											value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
											onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'" /> 
											</label>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</form:form>
			</div>
	</div>
	<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
</body>
</html>