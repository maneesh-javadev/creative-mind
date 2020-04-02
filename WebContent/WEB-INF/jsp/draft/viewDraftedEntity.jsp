<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="in.nic.pes.lgd.utils.OperationConstant" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%! String contextPath; %>
<%	contextPath = request.getContextPath(); %>
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- jquery pagination  -->
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/datatable/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/datatable/jquery-ui-1.8.7.custom.min.js"></script>
<script src="<%=contextpthval%>/datatable/TableTools.js" charset="utf-8" type="text/javascript"></script>
<link href="<%=contextpthval%>/datatable/jquery-ui-1.8.7.custom.css" rel="stylesheet"  type="text/css" />
<link href="<%=contextpthval%>/datatable/demo_table_jui.css" rel="stylesheet"  type="text/css" />
<!-- jquery pagination  -->

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#draftedEntityTable').dataTable({
			"sScrollY": "200px",
			"bScrollCollapse": false,
			"bPaginate": true,
			"aoColumnDefs": [
				{ "sWidth": "10%", "aTargets": [ -1 ] }
			],
			"bJQueryUI": true,
			"aaSorting": [],
			"aoColumns": [
							null,
							null,
							null,
							{ "bSortable": false },
				  			{ "bSortable": false },
				  			{ "bSortable": false },
				  			{ "bSortable": false },
				  			],
			"sPaginationType": "full_numbers"
		});
	} );

function open_win() {
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
} 

/* This function is for managing entity */
function manageEntity(operationCode, entityCode, transactionId, actionType) {	
	dwr.util.setValue('operationCode', operationCode, {	escapeHtml : false	});
	dwr.util.setValue('entityCode', entityCode, {	escapeHtml : false	});
	dwr.util.setValue('id', entityCode, {	escapeHtml : false	});
	dwr.util.setValue('actionType', actionType, {escapeHtml : false	});
	dwr.util.setValue('transactionId', transactionId, {escapeHtml : false	});
	document.getElementById('form1').submit();
}

var stateCode; 

$(function() {							
	$("#dialog").dialog({
		autoOpen : false,
		height : 450,
		width : 400,
		modal : true,
		title : 'Invalidate Village List',
		show : {
			effect : "blind",
			duration : 1000,
		},
		hide : {
			effect : "blind",
			duration : 1000
		},
		close : function(){
			$( "#tblSample tbody" ).empty();
		}
	});
});

function viewVillageData(entityCode,entityName){
	try{
	 
	 $( "#dialog" ).dialog( "open" );
	 var villageNameEnglishArr = entityName.split(",");
	  var inValidateVillageCode= entityCode.split(",");
	  for(var i=0;i<inValidateVillageCode.length;i++){
		  $( "#tblSample tbody" ).append( "<tr>" +
		          "<td>" + inValidateVillageCode[i]+ "</td>" +
		          "<td>" + villageNameEnglishArr[i] + "</td>" +
		        "</tr></br>" );  
	  }
	   
	}catch(e){
		alert(e);
	}
}
							
</script>	
<title><spring:message htmlEscape="true" code="Label.MANAGEENTITYDRAFT" text="Drafted Entity"></spring:message></title>
</head>
<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
		<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle">
				<label><spring:message htmlEscape="true" code="Label.MANAGEENTITYDRAFT" text="Drafted Entity"></spring:message></label>
			</h3>
			<ul id="showhelp" class="listing">
				<%--//these links are not working <li>
						<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a>
				</li>
				<li>
						<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>	
				</li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></li> --%>
			</ul>
		</div>		
		
		   <div class="clear"></div>
		   <div class="frmpnlbrdr">
			<form:form action="manageEntity.htm" id="form1" method="POST" commandName="entityDraftBean">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="manageEntity.htm"/>" />
				<input type="hidden" name="id" id="id" />
				<form:input path="operationCode" type="hidden" id="operationCode"  />
				<form:input path="entityCode" type="hidden" id="entityCode"  />
				<form:input path="actionType" type="hidden" id="actionType"  />
				<form:input path="transactionId" type="hidden" id="transactionId"/>
				<div id="cat">
					<div class="clear"></div>
					<c:if test="${! empty DRAFTED_ENTITY_LIST}">
						<div class="frmpnlbg" id="divData">
							<div class="frmtxt">
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td width="14%" align="center">
											<table cellpadding="0" cellspacing="0" border="0" class="display" id="draftedEntityTable">
												<thead>
													<tr class="tblRowTitle tblclear">
														<td rowspan="2"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></td>
														<td rowspan="2"><spring:message htmlEscape="true" code="Label.entitycode" text="Entity Code"></spring:message></td>
														<td rowspan="2"><spring:message htmlEscape="true" code="Label.entityname" text="Entity Name"></spring:message></td>
														<td rowspan="2"><spring:message htmlEscape="true" code="Label.OPERATION"></spring:message></td>
														<td colspan="4" align="center"><spring:message htmlEscape="true" code="Label.ACTION"></spring:message></td>
													</tr>
													<tr class="tblRowTitle tblclear">
														<th><spring:message htmlEscape="true" code="Label.EDIT"></spring:message></th>
														<th><spring:message htmlEscape="true" code="Label.PUBLISH"></spring:message></th>
														<th><spring:message htmlEscape="true" code="Label.DELETE"></spring:message></th>
													</tr>
												</thead>

												<tbody>
													<c:forEach var="obj" varStatus="sno" items="${DRAFTED_ENTITY_LIST}">
														<tr class="tblRowB">
															<td><c:out value="${offsets*limits+(sno.index+1)}" escapeXml="true"></c:out></td>
															<td><c:out value="${obj.entityCode}" escapeXml="true"></c:out></td>
															<c:choose>
																<c:when test="${obj.operationCode== OperationConstant.INVALIDATE_VILLAGE}">
																	<td align="left" onclick="viewVillageData('${obj.invalidateVillageList}','${obj.invalidateVillageListNameEnglish}');"><c:out value="${obj.entityName}" escapeXml="true"></c:out></td>
																</c:when>
																<c:otherwise>
																	<td align="left"><c:out value="${obj.entityName}"></c:out></td>
																</c:otherwise>
															</c:choose>
															<td align="left">${obj.operationDesc}</td>
															<td align="center"><a href="#"><img src="images/edit_icon.png" onclick="manageEntity(${obj.operationCode},${obj.entityCode},${obj.transactionId},'E');" width="18" height="19" border="0" /></a></td>
															<td align="center"><a href="#"><img src="images/publish.png" onclick="manageEntity(${obj.operationCode},${obj.entityCode},${obj.transactionId},'P');" width="18" height="19" border="0" /></a></td>
															<td align="center"><a href="#"><img src="images/delete.png" onclick="manageEntity(${obj.operationCode},${obj.entityCode},${obj.transactionId},'D');" width="18" height="19" border="0" /></a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
								</table>
							</div>
						</div>
					</c:if>
				</div>

				<c:if test="${fn:length(viewPage) > 0}">
					<c:if test="${empty DRAFTED_ENTITY_LIST}">
						<div class="frmpnlbg" id="divData">
							<div class="frmtxt">
								<spring:message htmlEscape="true" code="Label.NoDraftVillages"></spring:message>
							</div>
						</div>
					</c:if>
				</c:if>

				<div id="dialog" title="Invalidate Village List" style="display: none">
					<label><spring:message code="Label.VILLAGELIST" htmlEscape="true"></spring:message></label> <br></br>
					<hr />
					<br></br>
					<table id="tblSample" class="data_grid" width="80%">
						<thead>
							<tr class="tblRowTitle tblclear">
								<th><spring:message code="Label.VILLAGECODE" htmlEscape="true"></spring:message></th>
								<th><spring:message code="Label.VILLAGENAMEINENGLISH" htmlEscape="true"></spring:message></th>
							</tr>
						</thead>
						<tbody>

						</tbody>
					</table>
				</div>
				<input type="hidden" name="pageType" value="V" />
			</form:form>
			<script src="/LGD/JavaScriptServlet"></script>
		  </div>
		</div>
		<c:if test="${message != null}">
			<script>alert("<c:out value='${message}'/>")</script>
		</c:if>
</body>
</html>