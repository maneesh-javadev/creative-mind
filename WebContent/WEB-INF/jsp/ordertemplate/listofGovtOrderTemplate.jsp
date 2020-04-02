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
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/govtOrderTemplate.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrGovtOrderTemplate.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript">

function manageTemplate(url,id)
{
	dwr.util.setValue('templateId', id, {	escapeHtml : false	});
	//document.getElementById('templateListForm').method = "GET";
	document.getElementById('templateListForm').action = url;
	document.getElementById('templateListForm').submit();
}

dwr.engine.setActiveReverseAjax(true);

	/* function getTemplateList()
	{
		document.getElementById('templateListForm').action = "listTemplate.htm";
		document.forms["templateForm"].submit();
	} */
	function getTemplateList(){
		
         var errors = new Array();
		
		 var error = false;
		document.getElementById('templateListForm').action = "listTemplate.htm";
		    errors[0] = vlidateOnblur('templateDescription','1','15','c');
            if(errors[0]==true){
			  error = true;
		  }
	
             if(error == true)
             {
				showClientSideError();			
				return false;
				}
			else
				{
		
				document.forms["templateForm"].submit();
				return true; 
				}
		
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
<body onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);">

	<div class="overlay" id="overlay1" style="display: none;"></div>
	<div class="box" id="box1">
		<a class="boxclose" id="boxclose1"></a>
		<div>
			<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<div class="success"></div>
							</center>
						</td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div>
						</td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center>
							</div></td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><div class="failur"></div>
						</td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center>
						</td>
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
					<td rowspan="2"><div class="errorImg"></div>
					</td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div>
					</td>
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
			<h3><spring:message htmlEscape="true"  code="Label.GOVTORDERTEMPLATE"></spring:message></h3>
			<ul class="listing">
				<li>
					<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /> </a>
				</li>
				<li>
					<a href="#" class="frmhelp">Help</a>
				</li>
			</ul>
		</div>
		<div class="clear"></div>

		<div class="frmpnlbrdr">

			<form:form action="createTemplate.htm" id="templateListForm"
				name="templateForm" method="POST"
				commandName="governmentOrderTemplateForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="createTemplate.htm"/>"/>
				<div id="cat" align= "center"> 
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"  code="Label.MANAGEGOVTORDERTEMPLATE"></spring:message>
							</div>
							<table width="100%" class="tbl_with_brdr">
								<tr>
								<td>
									<input type="hidden" name="category" id="category" value="<c:out value='${category}' escapeXml='true'></c:out>"/>
								</td>
								</tr>
								<tr class="tblRowTitle tblclear">
									<td><spring:message htmlEscape="true" code="Label.GOVTORDERTEMPLATE"></spring:message><span class="errormsg">*</span></td>
									<td colspan="3">&nbsp;</td>
									
								</tr>

								<tr class="tblRowB" colspan="4">
									<td width="20%" valign="top" ><spring:message htmlEscape="true"  code="Label.SELECTOPERATION"></spring:message></td>
									<td width="20%" valign="top"><form:select path="operations" id="templateDescription" class="combofield" htmlEscape="true" onblur="vlidateOnblur('templateDescription','1','15','c'); ">
											<form:option value="0"><spring:message htmlEscape="true"  code="error.PSO"></spring:message></form:option>
											<form:options items="${oprList}" itemLabel="operationName" itemValue="operationCode"></form:options>
											</form:select>
											
<%-- 											<form:hidden path="operationName" value="${oprList.operationName}" />  --%>
											<div id="templateDescription_error" class="error"><spring:message code="error.blank.template" htmlEscape="true"></spring:message></div>
											<div class="errormsg" id="templateDescription_error1"><form:errors path="operations" htmlEscape="true"/></div>  
											<div class="errormsg" id="templateDescription_error2" style="display: none" ></div>	

									</td >
									
									
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
							<%-- 	<td width="10%"><a href="viewtemplate.htm?tc=${tl.templateCode}&<csrf:token uri="viewtemplate.htm"/>"><img width="18" height="19" src="images/view.png" border="0"/></a> </td>
									<td width="10%"><a href="modifyGovtTemplate.htm?tc=${tl.templateCode}&<csrf:token uri="modifyGovtTemplate.htm"/>"><img width="18" height="19" src="images/edit.png" border="0"/></a> </td>
									<td width="10%"><a href="deletetemplate.htm?tc=${tl.templateCode}&<csrf:token uri="deletetemplate.htm"/>"><img width="18" height="19" src="images/delete.png" border="0" onclick="return confirm('Are you sure you want to delete this?')"/></a> </td>
							 --%>		
									<td  width="8%" align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageTemplate('viewtemplate.htm',${tl.templateCode});" width="18" height="19" border="0" /></a></td>
									<td  width="8%" align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageTemplate('modifyGovtTemplate.htm',${tl.templateCode});" width="18" height="19" border="0" /></a></td>
									<td  width="8%" align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageTemplate('deletetemplate.htm',${tl.templateCode});" width="18" height="19" border="0" /></a></td>	
									
									
								</tr>
								</c:forEach>
								
								<form:input path="templateId" type="hidden" name="templateId" id="templateId" />
											
								
								</c:if>  
								
 							<%-- 	<c:if test="${fn:length(templateList)== 0}">   
								<tr>
									<td width="70%" valign="top">No Template Found</td>
								</tr>
								</c:if>  --%> 
								
								<%-- <c:if test="${fn:length(templateList) > 0}">
									<c:if test="${empty templateList}">
										<tr>
											<td colspan="4" width="70%" valign="top" align="center">No Template Found</td>
										</tr>
									</c:if>
								</c:if> --%>
								
								<c:if test="${templateSize==0}">
									<tr>
										<td colspan="4" width="70%" valign="top" align="center"><spring:message htmlEscape="true" code="Label.Notemplate"></spring:message></td>
									</tr>
								</c:if>
								
							</table>
						</div>
					</div>
					

					<div class="btnpnl" style="visibility:hidden;display:none">
						
						<ul>
							<li>
								
								<label>
									<input type="button" onclick="javascript:validateSelectAndSubmit();" name="Submit" class="btn" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" />
								</label>
								<label> 
								 	<input type="button" name="Submit9" class="btn" value="<spring:message htmlEscape="true"  code="Button.PREVIEW"></spring:message>"onclick="javascript:validateSelectAndPreview();" />
								 </label>
								 <label>
								 	<input type="button" name="Submit6" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
								</label>
							</li>
						</ul>
					</div>
					<input type="submit" value="Get Data" onclick="getTemplateList()" name="Submit" /> <!-- added by kirandeep on 18/06/2014 -->
					<input type="button" name="Submit3" class="btn"value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"onclick="javascript:go('home.htm');"/>
				</div>
			</form:form>
			</div>
	</div>
</body>
</html>