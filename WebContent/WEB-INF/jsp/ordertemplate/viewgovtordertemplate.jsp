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
<%-- <script type="text/javascript" language="javascript" src="<%=contextPath%>/ckeditor/ckeditor.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/ckeditor/ckeditor_components.js"></script> --%>
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
			<h3 class="subttitle"><spring:message htmlEscape="true"  code="Label.GOVTORDERTEMPLATE"></spring:message></h3>
			<ul class="listing">
				<li>
					<a href="#" rel="toggle[cat]"	data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /> </a>
				</li>
				<li>
					<a href="#" class="frmhelp">Help</a>
				</li>
			</ul>
		</div>
		<div class="clear"></div>
		
		
		<div class="frmpnlbrdr">

			<form:form action="createTemplate.htm" id="templateForm" name="templateFormView" method="POST" commandName="governmentOrderTemplateForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="createTemplate.htm"/>"/>
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"  code="Label.VIEWGOVTORDERTEMPLATE"></spring:message>
							</div>
							<div class="block">
								<ul class="blocklist">
									<li>
										<label for=""><spring:message htmlEscape="true"  code="Label.TEMPLATENAMEENG"></spring:message></label>&nbsp;&nbsp;&nbsp;&nbsp;
										<c:out value='${govtOrderTemplateForm.templateNameEnglish}' escapeXml="true"></c:out>
										<form:hidden path="templateNameEnglish" value="${govtOrderTemplateForm.templateNameEnglish}" id="templateNameEnglish" ></form:hidden>
									</li>
									<li>
										<label for=""><spring:message htmlEscape="true"  code="Label.Ordertype"></spring:message></label>&nbsp;&nbsp;&nbsp;&nbsp;
										<c:out value='${govtOrderTemplateForm.orderType}' ></c:out>
										<form:hidden  path="orderType" value="${govtOrderTemplateForm.orderType}"></form:hidden>
									</li>
								</ul>
							</div>
						</div>
					</div>
					
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"  code="Label.GOVTORDERTEMPLATE"></spring:message>
							</div>
							<div  >
								<div class="block">
									<ul class="blocklist">
										<li>
											<%-- <c:out value="${govtOrderTemplateForm.templateBodySrc}" escapeXml="true"></c:out>
											<form:hidden  path="templateBodySrc" value="${govtOrderTemplateForm.templateBodySrc}" cssClass="ckeditor"></form:hidden> --%>
											<form:textarea path="templateBodySrc" id="templateBodySrc" cssClass="ckeditor" value="${govtOrderTemplateForm.templateBodySrc}" />
											
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					
					<c:if test="${viewMode=='viewMode'}">
					<div class="btnpnl">
						<div>
							<!-- BACK button removed - client requirement -->
							<label> <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose"/> </label>
						</div>
					</div>
					</c:if>
					<c:if test="${viewMode !='viewMode'}">
						<div class="btnpnl">
							<div>
								<label>
									<input type="button" name="Submit6" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> 
								</label>
							</div>
						</div>
					</c:if>
				</div>
			</form:form>
			<script src="/LGD/JavaScriptServlet"></script>
		</div>	
	</div>
</body>
</html>