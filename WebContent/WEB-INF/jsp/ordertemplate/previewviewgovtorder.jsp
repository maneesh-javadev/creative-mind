<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<%-- <script type="text/javascript" language="javascript" src="<%=contextPath%>/ckeditor/ckeditor.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/ckeditor/ckeditor_components.js"></script> --%>
<script type="text/javascript" language="javascript">
	function validateSelectAndSubmit() {
		document.getElementById('templateForm').action = "createTemplate.htm";
		document.forms["templateForm"].submit();
	}
	function editTemplate() {
		/* 		go("createGovtTemplate.htm?mode=editmode"); */

		document.getElementById('templateForm').action = "createGovtTemplateAfterPreview.htm";
		document.forms["templateForm"].submit();
		/* 		document.getElementById('templateForm').action = "createGovtTemplate.htm?mode=edit";
		 document.forms["templateForm"].submit(); */
	}
</script>
<link href="../sample.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	

	function insertVarFckEditor() {
		var editor1 = CKEDITOR.instances.templateBodySrc;		
		editor1.insertText("{$"+ document.getElementById("selType").value + "} ");		
		return false;
	}
</script>

</head>
<body
	>
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
					<%-- //these links are not working <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a> --%>
					</td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>

		<div class="frmpnlbrdr">

			<form:form action="createpdf.htm" id="templateForm"
				name="templateFormView" method="POST" commandName="governmentOrder">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="createpdf.htm"/>"/>
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"  code="Label.VIEWGOVTORDERTEMPLATE"></spring:message>
							</div>
							<table width="100%" border="1">

							</table>
						</div>
					</div>
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"  code="Label.GOVTORDERTEMPLATE"></spring:message>
							</div>
							<table width="100%" border="1">

								<%-- <tr>
									<td width="15%" valign="top"></td>
									<td width="70%" valign="top">
									${addVillageNew.templateBodySrc}
									<form:hidden  path="templateBodySrc" value="${addVillageNew.templateBodySrc}" ></form:hidden>
									</td>
									<td width="15%">&nbsp;</td>
								</tr> --%>
								<tr>
									<td width="15%" valign="top"></td>
									<td width="70%" valign="top">
										<form:hidden path="orderNo"   /> 
										<form:hidden	path="orderDate" />
										<form:hidden  path="effectiveDate"	/>
										<form:hidden path="gazPubDate"  />
										<%-- <form:hidden path="templateBodySrc" /> --%>
										<form:textarea path="templateBodySrc" id="templateBodySrc" cssClass="ckeditor"/>
											
										
										<%-- <textarea id="templateBodySrc" name="templateBodySrc" class="ckeditor">
											<esapi:encodeForHTMLAttribute>${govtOrderForm.templateBodySrc}</esapi:encodeForHTMLAttribute>
										</textarea> --%>
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
										<label> <input type="submit" name="Submit"
											 class="btn"
											value="<spring:message htmlEscape="true"  code="Button.SAVEPUB" ></spring:message>" />
										</label> <label> <input type="button" name="Submit6"
											class="btn"
											value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
											onclick="window.location.href = 'home.htm?<csrf:token uri='home.htm'/>';" /> </label>
									</div></td>
							</tr>
						</table>
					</div>
					<c:if test="${viewMode=='viewMode'}">
						<div class="btnpnl">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%">&nbsp;</td>
									<td width="86%">
										<div>
<!-- BACK button removed - client requirement -->
											<label> <input type="button" name="Submit6"
												class="btn"
												value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
												onclick="javascript:go('home.htm?<csrf:token uri='home.htm'/>');" /> </label>
										</div></td>
								</tr>
							</table>
						</div>
					</c:if>
					
				</div>
			</form:form>
			<script src="/LGD/JavaScriptServlet"></script>
			</div>
	</div>
	
	
<script type="text/javascript">
	CKEDITOR.replace( 'templateBodySrc', {
		filebrowserBrowseUrl: 'imgBrowser.htm',
	});
</script>
</body>
</html>