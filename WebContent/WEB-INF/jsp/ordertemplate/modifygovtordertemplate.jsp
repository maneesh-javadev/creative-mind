 <%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%-- <%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%> --%>
<head>
<script type='text/javascript' src="js/common.js"></script>
<script type="text/javascript" src="js/cancel.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type='text/javascript' src="js/validation.js"></script>
<script type='text/javascript' src="js/govtOrderTemplate.js"></script>
<script type='text/javascript' src="js/successMessage.js"></script>
<script type='text/javascript' src="js/helpMessage.js"></script>
<script type='text/javascript' src="js/trim-jquery.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<link href="css/errorLB.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrGovtOrderTemplate.js'>dwr.engine.setActiveReverseAjax(true);</script>
<link href="../sample.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="../fckeditor.gif" type="image/x-icon" />
<%-- <script type="text/javascript" language="javascript" src="<%=contextPath%>/ckeditor/ckeditor.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/ckeditor/ckeditor_components.js"></script> --%>
<script type="text/javascript">
	/* function FCKeditor_OnComplete(editorInstance) {
		window.status = editorInstance.Description;
	}
 */
	function insertVarFckEditor(val) {
		// 		alert("insertLblInFckEditor");
		//var EditorInstance = FCKeditorAPI.GetInstance("templateBodySrc"); //message is name of field to be validate
		var editor1 = CKEDITOR.instances.templateBodySrc;		
		editor1.insertText(val);	
		return false;
	}
 
 
 function loadPage()
 {
	 onloadShowTemplateVariable();
 }
 
 if ( window.addEventListener ) { 
     window.addEventListener( "load",loadPage, false );
  }
  else 
     if ( window.attachEvent ) { 
        window.attachEvent( "onload", loadPage );
  } else 
        if ( window.onLoad ) {
           window.onload = loadPage;
  }

 
</script>
</head>
<body>
	<div class="overlay" id="overlay1" style="display: none;"></div>
	<div class="box" id="box1">
		<a class="boxclose" id="boxclose1"></a>
		<div>
			<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<div class="success"></div>
							</center></td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div></td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center>
							</div>
						</td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><div class="failur"></div></td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center></td>
					</tr>
					<tr>
						<td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="true"></c:out></div>
						</td>
					</tr>
				</table>

			</c:if>

		</div>
	</div>

	<div class="box" id="box">
		<a class="boxclose" id="boxclose"></a>
		<div id="validate_error">
			<table>
				<tr>
					<td rowspan="2"><div class="errorImg"></div></td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div></td>
				</tr>
				<tr>
					<td><div class="errorfont"><spring:message htmlEscape="true" code="Error.entermandatoryfield"></spring:message></div>
					</td>
				</tr>
			</table>

		</div>
	</div>
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><label><spring:message htmlEscape="true"  code="Label.GOVTORDERTEMPLATE"></spring:message></label></h3>
			<ul class="listing">
				<li>
					<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /> </a>
				</li>
				<%--//these links are not working  <li>
					<a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a>
				</li> --%>
			</ul>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">

			<form:form action="updateTemplate.htm" id="templateForm" name="templateForm" method="POST" commandName="governmentOrderTemplateForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="updateTemplate.htm"/>"/>
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"  code="Label.MODIFYGOVTORDERTEMPLATE"></spring:message>
							</div>
							<div class="search_criteria">
								<ul class="blocklist">
									<li>
										<label><spring:message htmlEscape="true" code="Label.TEMPLATENAMEENG"></spring:message><span
										id="required" class="errormsg">*</span>
										</label>
										<form:input path="templateNameEnglish" id="templateNameEnglish" class="frmfield" onkeypress="return validateforTextandspace(event);"/> <form:hidden
										path="templateCode" id="templateCode" style="width: 200px" htmlEscape="true"
										class="frmfield"></form:hidden>
										<div id="templateNameEnglish_error" class="error"><spring:message code="error.PETM" htmlEscape="true"></spring:message></div>
										<div class="errormsg"><form:errors htmlEscape="true" path="templateNameEnglish"></form:errors></div> 
										<div class="errormsg" id="templateNameEnglish_error2" style="display: none" ></div>
									</li>
									<li>
										<label><spring:message htmlEscape="true" code="Label.OPERATION"></spring:message><span id="required"
										class="errormsg">*</span>
										</label>
										<form:select path="operations" id="operations" disabled="false" onchange="getVariableList(this.value);" class="combofield" htmlEscape="true">
										<form:option value="0"><spring:message htmlEscape="true"  code="error.PSO"></spring:message></form:option>
										<form:options items="${oprList}" itemLabel="operationName"
										itemValue="operationCode"></form:options>
										</form:select> 
										<div id="operations_error" class="error"><spring:message code="error.PSO" htmlEscape="true"></spring:message></div> 
										<div class="errormsg"><form:errors htmlEscape="true"  path="operations"></form:errors></div>
										<div class="errormsg" id="operations_error2" style="display: none" ></div>
									</li>
									<li>
										<label><spring:message htmlEscape="true" code="Label.TYPEOFGOVTORDER"></spring:message><span id="required" class="errormsg">*</span>
										</label>
										<form:select path="orderType"
											id="orderType" class="combofield">
											<form:option value="0"><spring:message htmlEscape="true"  code="error.PSOT"></spring:message></form:option>
											<form:option value="O"><spring:message htmlEscape="true"  code="Label.GOVTORDER"></spring:message></form:option>
											<form:option value="A"><spring:message htmlEscape="true"  code="Label.ADDENDUM"></spring:message></form:option>
											<form:option value="C"><spring:message htmlEscape="true"  code="Label.CORRIGENDUM"></spring:message></form:option>
										</form:select> 
											<div id="orderType_error" class="error"><spring:message code="error.PSOT" htmlEscape="true"></spring:message></div>  
											<div class="errormsg"><form:errors htmlEscape="true" path="orderType"></form:errors></div>
											<div class="errormsg" id="orderType_error2" style="display: none"></div>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.TEMPLATELANGUAGE"></spring:message><span id="required" class="errormsg">*</span>
										</label>
										<form:select
											path="templateLanguage" id="templateLanguage" class="combofield">
											<form:option value="0"><spring:message htmlEscape="true"  code="Label.PSL"></spring:message></form:option>
											<form:option value="English"><spring:message htmlEscape="true" code="Label.ENGLISH"></spring:message></form:option>
											<form:option value="Local"><spring:message htmlEscape="true" code="Label.LOCAL"></spring:message></form:option>
										</form:select>
										<div id="templateLanguage_error" class="error"><spring:message code="error.PSTL" htmlEscape="true"></spring:message></div> 
										<div class="errormsg"><form:errors htmlEscape="true"  path="templateLanguage"></form:errors></div> 
										<div class="errormsg" id="templateLanguage_error2" style="display: none"></div> 
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.TEMPLATESRC"></spring:message><span id="required"class="errormsg">*</span><b><spring:message htmlEscape="true" 	code="Label.STEMPVAR"></spring:message></b><br /> 
										</label>
										<select	id="selType" onchange="insertVarFckEditor(this.value);" class="combofield">
										<option><spring:message htmlEscape="true" 	code="Label.STEMPVAR"></spring:message></option>
										</select>
										<div id="selType_error" class="error"><spring:message code="error.TEMPLATESRC" htmlEscape="true"></spring:message></div> 
										<div class="errormsg" id="selType_error2" style="display: none"></div>
										</li>
										<li>
											<div  >
												<form:textarea path="templateBodySrc" id="templateBodySrc" cssClass="ckeditor"/>
												<div class="errormsg"><form:errors htmlEscape="true"  path="templateBodySrc" ></form:errors></div> 
											</div>
										</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="btnpnl">
						<div>
								<label><input type="button"
									onclick="return onUpdateTemplate();"
									name="Submit" class="btn"
									value="<spring:message htmlEscape="true" code="Button.UPDATE"></spring:message>" />
								</label>
						 

								<label> <input type="button" name="Submit9"
								class="btn"
								value="<spring:message htmlEscape="true"  code="Button.PREVIEW"></spring:message>"
								onclick="return onUpdatePreviewTemplate();" /> </label> <label>
								<input type="button" name="Submit6" class="btn"
								value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
								onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
							</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
		<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
			<script type="text/javascript">
	CKEDITOR.replace( 'templateBodySrc', {
		filebrowserBrowseUrl: 'imgBrowser.htm',
	});
</script>
</body>
</html>