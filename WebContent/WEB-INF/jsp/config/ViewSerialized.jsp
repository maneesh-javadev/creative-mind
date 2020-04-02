<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="../common/taglib_includes.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*" %> 
<%
	String contextPath = request.getContextPath();
%>
<head>
	<title>Serialized Elements</title>
	<script type="text/javascript" language="javascript" src="<%=contextPath%>/external/jqueryCustom/js/jquery-ui-1.8.7.custom.min.js"></script>
	<script type="text/javascript" language="javascript" src="<%=contextPath%>/external/jqueryCustom/js/jquery.dataTables.js"></script>
	<script type="text/javascript" charset="utf-8" 		 src="<%=contextPath%>/external/jqueryCustom/js/TableTools.js" ></script>
	<link href="<%=contextPath%>/external/jqueryCustom/css/jquery-ui-1.8.7.custom.css" rel="stylesheet"  type="text/css" />
	<link href="<%=contextPath%>/external/jqueryCustom/css/demo_table_jui.css" rel="stylesheet"  type="text/css" />

<script>
		function test (object){
	 		var entityName = object.value;
			if(entityName == "-1"){
				document.getElementById('errorentity').innerHTML="Please select atleast Entity.";
	 			return false;
			}
			document.searchForm.entityForm.value = entityName; 
 			document.searchForm.method = "post";
 	     	document.searchForm.action = "viewSerialized.htm?<csrf:token uri='viewSerialized.htm'/>";
 	     	document.searchForm.submit();
 			return true;
	 	 };

		function deserializeComponent(entityName, entityvalue){
			alert(entityName +" "+entityvalue);
			document.searchForm.entityForm.value = entityName; 
			document.searchForm.entityValue.value = entityvalue; 
 			document.searchForm.method = "post";
 	     	document.searchForm.action = "deserializeSingle.htm?<csrf:token uri='deserializeSingle.htm'/>";
 	     	document.searchForm.submit();
 			return true;
		}	 	 
	</script> 	 
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td>Serialized Elements</td>
					<td align="right"><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a></td>
<%-- //this link is not working	<td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></td>
 --%>				</tr>
			</table>
		</div>
		
		
		
		
		
		<div class="clear"></div>
			   <div class="frmpnlbrdr">
				   <form:form id="searchForm" method="POST" commandName="searchForm" name="searchForm">
				   		<input type="hidden" id="entityForm" name="entityForm"/>
				   		<input type="hidden" id="entityValue" name="entityValue"/>
				   		
				   		<table width="100%" class="tbl_no_brdr">
							<tr style="height: 40Px;">
								<td align="right" width="50%">
									<b>Select Entity Name</b>&nbsp;<label style="color: red;">*</label>
								</td>
								<td align="left" width="50%">
									<select id="state" name="state" style="width:230px" onchange="test(this)">
										<option value="-1"><spring:message htmlEscape="true" code="Label.SEL"/></option>
										<c:forEach var="entities" items="${list}">
											<option <c:if test="${entities eq selectedEntity}">selected="selected"</c:if> value="${entities}">
												<c:out value="${entities}" escapeXml="true"></c:out>
											</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><div id="errorentity" class="errormsg"></div></td>
							</tr>
						</table>
				   		<br/>
				   		<table id="tblViewSerialized" width="100%" >
							<thead>
								<tr >
									<th align="center" width="5%"><b>S.No.</b></th>
									<th align="center"><b>Name from Entity</b></th>
									<th align="center" width="10%"><b>Action</b></th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${selectedEntity eq 'VillageForm'}">
									<c:forEach var="villelements" items="${deserialized}" varStatus="c">
										<tr>
											<td><c:out value="${c.count}" escapeXml="true"></c:out></td>
											<td><c:out value="${villelements.newVillageNameEnglish}" escapeXml="true"></c:out></td>
											<td align="center"><a href="#" onclick="deserializeComponent('${selectedEntity}','${villelements.newVillageNameEnglish}')">de-serialize</a></td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table> 
				  </form:form>	
				  <script src="/LGD/JavaScriptServlet"></script>
			  </div>
		</div>

<script type="text/javascript">
$(document).ready(function() {
	$('#tblViewSerialized').dataTable({
		"bJQueryUI": true,
		"aoColumns":[null, {"bSortable": false}, {"bSortable": false}],
		"sPaginationType": "full_numbers"
	});
} );
</script>
</body>
</html>