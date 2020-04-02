<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" language="javascript">
	function validateSelectAndSubmit() {
		document.getElementById('templateForm').action = "createTemplate.htm";
		document.forms["templateForm"].submit();
	}
	function validateSelectAndPreview()
	{
		document.getElementById('templateForm').action = "govtordertemplatepreview.htm";
		document.forms["templateForm"].submit();
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
		EditorInstance.EditorDocument.body.innerHTML+=" {$"+document.getElementById("selType").value+"-} ";

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
				name="templateForm" method="POST"
				commandName="governmentOrderTemplateForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="createTemplate.htm"/>"/>
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"  code="Label.CREATEGOVTORDERTEMPLATE"></spring:message>
							</div>
							<table width="100%" border="1">
								<%-- <tr>
									<td>&nbsp;</td>
									<td><spring:message htmlEscape="true"  code="Label.GOVTORDERTEMPLATE"></spring:message></td>
									<td>&nbsp;</td>
								</tr> --%>
								<tr>
									<td width="15%" valign="top"><spring:message htmlEscape="true"  code="Label.TEMPLATENAMEENG"></spring:message></td>
									<td width="20%" valign="top"><form:input
											path="templateNameEnglish" id="templateDescription" /></td>
									<td width="65%">&nbsp;</td>
								</tr>
								<tr>
									<td width="15%" valign="top"><spring:message htmlEscape="true"  code="Label.TEMPLATEDESC"></spring:message></td>
									<td width="20%" valign="top"><form:input
											path="templateDescription" id="templateDescription" /></td>
									<td width="65%">&nbsp;</td>
								</tr>
								<tr>
									<td width="15%" valign="top"><spring:message htmlEscape="true"  code="Label.TEMPLATESRC"></spring:message></td>
									<td width="70%" valign="top"><b>Select Variable :</b><select
										id="selType" onchange="insertVarFckEditor();"><option><spring:message htmlEscape="true" code="Label.select"></spring:message></option>
											<option value="Var-1">Var-1</option>
											<option value="Var-2">Var-2</option>
											<option value="Var-3">Var-3</option>

											<option value="Var-4">Var-4</option>
											<option value="Var-5">Var-5</option>
											<option value="Var-6">Var-6</option>

											<option value="Var-7">Var-7</option>
											<option value="Var-8">Var-8</option>
											<option value="Var-9">Var-9</option>
											<option value="Var-10">Var-10</option>
											<option value="Var-11">Var-11</option>
											<option value="Var-12">Var-12</option>
									</select> <FCK:editor instanceName="templateBodySrc"
											inputName="templateBodySrc" height="400px" >
											<jsp:attribute name="value">			
			</jsp:attribute>
										</FCK:editor> <%-- 								    <form:textarea path="templateBodySrc" id="templateBodySrc" rows="10" cols="80"/> --%>



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
										<label> <input type="button"
											onclick="javascript:validateSelectAndSubmit();" name="Submit"
											class="btn"
											value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" />
										</label> <label> <input type="button" name="Submit9"
											class="btn"
											value="<spring:message htmlEscape="true"  code="Button.PREVIEW"></spring:message>"
											onclick="javascript:validateSelectAndPreview();" /> </label> <label>
											<input type="button" name="Submit6" class="btn"
											value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
											onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</form:form>
			</div>
	</div>
</body>
</html>