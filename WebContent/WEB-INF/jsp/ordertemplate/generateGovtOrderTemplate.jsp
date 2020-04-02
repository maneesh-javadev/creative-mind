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
<script type="text/javascript" src="js/common.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/govtOrderTemplate.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrGovtOrderTemplate.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>

<script type="text/javascript" language="javascript">

dwr.engine.setActiveReverseAjax(true);

	function getTemplateList()
	{
		document.getElementById('templateListForm').action = "listTemplate.htm";
		document.forms["templateForm"].submit();
	}
	function generateDraftTemplate()
	{
		
		document.getElementById('templateForm').action = "generateDraftTemplate.htm";
		document.forms["templateForm"].submit();
	}
</script>
<link href="../sample.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="../fckeditor.gif" type="image/x-icon" />
<script type="text/javascript">
	function FCKeditor_OnComplete(editorInstance) {
		window.status = editorInstance.Description;
	}
	
	function insertVarFckEditor(val){
// 		alert("insertLblInFckEditor");
		var EditorInstance = FCKeditorAPI.GetInstance("templateBodySrc"); //message is name of field to be validate
		
		EditorInstance.InsertHtml(val);
// 		EditorInstance.InsertHtml("<img src='http://www.dan-dare.org/FreeFun/Images/CartoonsMoviesTV/CarsWallpaper800.jpg' />");
		return false;
	}
	
</script>

</head>
<body>

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
							<table width="100%" class="tbl_with_brdr">
								<tr class="tblRowTitle tblclear" >
									<td>&nbsp;</td>
									<td><spring:message htmlEscape="true"  code="Label.GOVTORDERTEMPLATE"></spring:message></td>
									<td colspan="3">&nbsp;</td>
								</tr>

								<tr class="tblRowB" colspan="4">
									<td width="20%" valign="top"  ><spring:message htmlEscape="true"  code="Label.SELECTOPERATION"></spring:message></td>
									<td width="20%" valign="top"><form:select    path="operations" id="templateDescription" >
											<form:option value="0">Please Select Operation</form:option>
											<form:options items="${oprList}" itemLabel="operationName" itemValue="operationCode"></form:options>
											</form:select>
											
<%-- 											<form:hidden path="operationName" value="${oprList.operationName}" />  --%>
									</td >
									<td width="20%" valign="top" colspan="3"><input type="button"
											onclick="generateDraftTemplate();" name="Submit"
											class="btn"
											value="<spring:message htmlEscape="true"  code="Label.GENDRAFTTEMPL"></spring:message>" /><input type="button" value="Get Data" onclick="getTemplateList()" /></td>
									
								</tr>
								<c:if test="${!empty templateList}">  
								 <tr class="tblRowTitle tblclear">
									<td width="10%" valign="top"><spring:message htmlEscape="true"  code="Label.TEMPLATECODE"></spring:message></td>
									<td width="60%" valign="top"><spring:message htmlEscape="true"  code="Label.TEMPLATENAMEENG"></spring:message></td>
									<td width="10%"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></td>
									<td width="10%"><spring:message htmlEscape="true"  code="Label.EDIT"></spring:message></td>
									<td width="10%"><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></td>
								</tr>
								
								<c:forEach items="${templateList}" var="tl" varStatus="tlRow">
								<tr class="tblRowB">
									<td width="10%" valign="top"><c:out value="${tl.templateCode}" escapeXml="true"></c:out></td>
									<td width="60%" valign="top"><c:out value="${tl.templateNameEnglish}" escapeXml="true"></c:out></td>
									<td width="10%"><a href="viewtemplate.htm?tc=${tl.templateCode}"><img width="18" height="19" src="images/view.png" border="0"/></a> </td>
									<td width="10%"><a href="createGovtTemplate.htm?mode=editmode&tc=${tl.templateCode}"><img width="18" height="19" src="images/edit.png" border="0"/></a> </td>
									<td width="10%"><a href="deletetemplate.htm?tc=${tl.templateCode}"><img width="18" height="19" src="images/delete.png" border="0" onclick="return confirm('Are you sure you want to delete this?')"/></a> </td>
								</tr>
								</c:forEach>
								</c:if>  
								
<%-- 								<c:if test="${fn:length(templateList)== 0}" >   
								<tr>
									<td width="70%" valign="top">No Template Found</td>
								</tr>
								</c:if>  --%>
							</table>
						</div>
					</div>
					

					<div class="btnpnl" style="visibility:hidden;display:none">
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