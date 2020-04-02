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
<!-- <script type="text/javascript" src="js/cancel.js"></script> -->
<script type="text/javascript" src="js/common.js"></script>
<%-- <script type="text/javascript" language="javascript" src="<%=contextPath%>/ckeditor/ckeditor.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/ckeditor/ckeditor_components.js"></script> --%>
<script type="text/javascript" language="javascript">
	function validateSelectAndSubmit() {
		document.getElementById('templateForm').action = "createTemplate.htm";
		document.forms["templateForm"].submit();
	}
	function editTemplate()
	{
		document.getElementById('templateForm').action = "createGovtTemplateAfterPreview.htm";
		document.forms["templateForm"].submit();
	}
</script>
<!-- <link href="../sample.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="../fckeditor.gif" type="image/x-icon" /> -->
<script type="text/javascript">
	function FCKeditor_OnComplete(editorInstance) {
		window.status = editorInstance.Description;
	}
	
	function insertVarFckEditor(){
		var EditorInstance = FCKeditorAPI.GetInstance("templateBodySrc"); //message is name of field to be validate
		EditorInstance.EditorDocument.body.innerHTML+="{$"+document.getElementById("selType").value+"} ";
		return false;
	}
	
</script>

</head>
<body onload="toggledisplay('districtdelete_yes','fromothersubdistrict')">
	<section class="content">
                <div class="row">
                    <section class="col-lg-12">
                            <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message htmlEscape="true" code="Label.GOVTORDERTEMPLATE" /></h3>
                                </div>
               <div class="box-body">
              <div class="box-header subheading">
                             <h4 ><spring:message htmlEscape="true"  code="Label.VIEWGOVTORDERTEMPLATE"></spring:message></h4>
                  </div>
	           <form:form action="createTemplate.htm" id="templateForm" name="templateFormView" method="POST" commandName="governmentOrderTemplateForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="createTemplate.htm"/>"/>
					<table class="table table-bordered table-hover">
					  <tr>
					    <td><spring:message htmlEscape="true"  code="Label.TEMPLATENAMEENG"></spring:message></td>
					    <td><c:out value='${govtOrderTemplateForm.templateNameEnglish}' escapeXml="true"></c:out>
							<form:hidden path="templateNameEnglish" value="${govtOrderTemplateForm.templateNameEnglish}" id="templateNameEnglish" ></form:hidden></td>
					  </tr>
					<tr>
					<td><spring:message htmlEscape="true"  code="Label.Ordertype"></spring:message></td>
					<td><c:out value='${govtOrderTemplateForm.orderType}' ></c:out>
						<form:hidden  path="orderType" value="${govtOrderTemplateForm.orderType}"></form:hidden></td>
					
					</tr>
					</table>	
					
					 <div class="box-header subheading">
							<h4><spring:message htmlEscape="true"  code="Label.GOVTORDERTEMPLATE"></spring:message></h4>
					</div>	
					<div class="form-group">
					   <form:textarea path="templateBodySrc" id="templateBodySrc" cssClass="ckeditor" value="${govtOrderTemplateForm.templateBodySrc}" />
					</div>		
				</div>
				<div class="box-footer">
                   <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
                       <c:if test="${viewMode=='viewMode'}">
                             <button type="button" id="actionSearchClose" value="Close" class="btn btn-danger " value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                        </c:if>
                        <c:if test="${viewMode !='viewMode'}">
                           <button type="button" id="actionSearchClose" value="Close" class="btn btn-danger " value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                        </c:if>
                        </div>
                     </div>   
                  </div>
			</form:form>
		
		</div>
		</section>
		</div>
		</section>
	<script src="/LGD/JavaScriptServlet"></script>	
</body>
</html>