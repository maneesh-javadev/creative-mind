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
<%-- <script type="text/javascript" src="js/invalidateDistrict.js"
	charset="utf-8"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrCoveredLandRegionByULBService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script> --%>
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
							border="0" /> </a></td>
					<td width="50" align="right"><a href="#" class="frmhelp">Help</a>
					</td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>

		<div class="frmpnlbrdr">

			<form:form action="createTemplate.htm" id="templateForm"
				name="templateFormView" method="POST"
				commandName="addVillageNew">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="createTemplate.htm"/>"/>
				<div id="cat">
<%-- 					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"  code="Label.VIEWGOVTORDERTEMPLATE"></spring:message>
							</div>
							<table width="100%" border="1">
								
							</table>
						</div>
					</div> --%>
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"  code="Label.GOVORD"></spring:message>
							</div>
							<table width="100%" border="1">

								<tr>
									<td width="15%" valign="top"></td>
									<td width="70%" valign="top">
									<iframe src="${filepath}" height="600" width="800" scrolling="auto"></iframe>
									
									</td>
									<td width="15%">&nbsp;</td>
								</tr>
							</table>
						</div>
					</div>
					
					<c:if test="${viewMode=='viewMode'}">
					<div class="btnpnl">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td width="14%">&nbsp;</td>
								<td width="86%">
									<div>
<!-- BACK button removed - client requirement -->
										<label> <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose"/> </label>
									</div>
								</td>
							</tr>
						</table>
					</div>
					</c:if>
					<c:if test="${viewMode !='viewMode'}">
					<div class="btnpnl">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td width="14%">&nbsp;</td>
								<td width="86%">
									<div>
										
										 <label>
											<input type="button" name="Submit6" class="btn"
											value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
											onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> 
										</label>
									</div>
								</td>
							</tr>
						</table>
					</div>
					</c:if>
				</div>
			</form:form>
			</div>
	</div>
</body>
</html>