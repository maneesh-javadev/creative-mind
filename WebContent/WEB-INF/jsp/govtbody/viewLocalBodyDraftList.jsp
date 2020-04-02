<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page import="java.util.*"%>
<%@include file="../common/taglib_includes.jsp"%>
<title><spring:message code="Label.VIEWLOCALGOVTBODY" htmlEscape="true"></spring:message></title>
	<script src="js/viewLocalbody.js"></script>
	<script src="js/successMessage.js"></script>
	<script src="js/helpMessage.js"></script>
	<script type="text/javascript" language="javascript" src="<%=contextpthval%>/datatable/jquery.dataTables.js"></script>
	<script type="text/javascript" language="javascript" src="<%=contextpthval%>/datatable/jquery-ui-1.8.7.custom.min.js"></script>
	<script type="text/javascript" language="javascript" src="<%=contextpthval%>/dwr/interface/lgdDwrLocalGovtBodyService.js"></script>
	<script src="<%=contextpthval%>/datatable/TableTools.js" charset="utf-8" type="text/javascript"></script>
	<link href="<%=contextpthval%>/datatable/jquery-ui-1.8.7.custom.css" rel="stylesheet"  type="text/css" />
	<link href="<%=contextpthval%>/datatable/demo_table_jui.css" rel="stylesheet"  type="text/css" />

<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#localbodyList').dataTable({
			"bJQueryUI": true,
			"aaSorting": [],
			"aoColumns": [
				  			null,
				  			null,
				  			null,
				  			{ "bSortable": false },
				  			{ "bSortable": false }
				  			],
			"sPaginationType": "full_numbers"
		});
	} );

function viewDraft(obj) {
	document.getElementById('hidObjId').value = obj;
	document.draftForm.submit();
	displayLoadingImage();
 }

function remove(obj) {
	document.getElementById('hidObj').value = obj;
	document.draftForm.action="deleteDraft.htm?<csrf:token uri='deleteDraft.htm'/>";
	document.draftForm.submit();
	displayLoadingImage();
}

function gotoManagePage() {
	window.location.href="viewLocalBodyforPRI.htm?skip=true&<csrf:token uri='viewLocalBodyforPRI.htm'/>";
}

function showSuccess() {		
	$( "#dialog:ui-dialog" ).dialog( "destroy" );
	$( "#dialog-message" ).dialog({
		modal: true,
		buttons: {
			Ok: function() {
				//document.getElementById('draftForm').action = "getDraftFileList.htm?<csrf:token uri='getDraftFileList.htm'/>";
				//document.forms['draftForm'].submit();
				$( this).dialog( "close" );
			}
		}
	});
}

</script>
</head>

<body oncontextmenu="return false">
	
	<div id="dialog-message" title="Success Message" style="display: none;">
		<p>
			<span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
			<c:out value="${msg}" escapeXml="true"></c:out>
		</p>
	</div>
	
	<div class="box" id="box">
		<a class="boxclose" id="boxclose"></a>
		<div id="validate_error" style="display: none;">
			<table>
				<tr>
					<td rowspan="2"><div class="errorImg"></div></td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div></td>
				</tr>
				<tr>
					<td><div class="errorfont"><spring:message code="error.blank.commonAlert" htmlEscape="true"></spring:message></div>
					</td>
				</tr>
			</table>

		</div>
	</div>
	
	
	<div id="frmcontent">
		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message code="Label.VIEWLOCALGOVTBODY" htmlEscape="true"></spring:message>	</td>
					<td align="right"><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /> </a></td>
					<%--//these links are not working <td width="50" align="right" valign="middle"><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></td>
					<td width="50" align="right"><a href="#" class="frmhelp"><spring:message 	code="Label.HELP" htmlEscape="true"></spring:message> </a></td> --%>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			
				<!-- List section starts here -->

				<c:if test="${! empty fileList}">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<table  cellpadding="0" cellspacing="0" border="0" class="display" id="localbodyList">
										<thead>
											<tr>
												<th rowspan="2"><spring:message code="Label.SNO" 	htmlEscape="true"></spring:message></th>
												<th rowspan="2"><spring:message code="Label.LOCALBODYCODE" htmlEscape="true"></spring:message></th>
												<th rowspan="2"><spring:message code="Label.LOCALBODYNAMEINENG" htmlEscape="true"></spring:message></th>
												<th colspan="4" align="center"><spring:message code="Label.ACTION" htmlEscape="true"></spring:message></th>
											</tr>
											<tr>
	                                          	<th align="center"><spring:message code="App.VIEW"/></th>
												<th align="center"><spring:message code="App.DELETE"/></th> 
	                                        </tr>
											</thead>
											
												<tbody>
												<c:set var="fileList" value="${fileList}" scope="session"/>
												<c:forEach var="obj" 	varStatus="row" items="${fileList}">

													<tr>
														<td align="left"><c:out value="${row.count}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${obj.localBodyCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${obj.localBodyNameEnglish}" escapeXml="true"></c:out></td>
														<td align="center"><a href="#" onclick="viewDraft(${row.count});"><img src="<%=contextpthval%>/images/view.png" width="22" height="18" border="0" /></a></td>
														<td align="center"><a href="#" onclick="remove(${obj.localBodyCode});"><img src="<%=contextpthval%>/images/delete.png" width="22" height="18" border="0" /></a></td>
													</tr>

												</c:forEach>
												</tbody>
											
										<!--------------- End list section here ------------------------------------->
							</table>
						</div>
					</div>
				</c:if>
				<form action="viewDraft.htm" name="draftForm" id="draftForm" method="post">
					<input type="hidden" name="hidObj" id="hidObj"/>
					<input type="hidden" name="hidObjId" id="hidObjId"/>
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewDraft.htm"/>" />
				</form>
				<div class="btnpnl">
						<label> <input type="button" class="btn" name="Submit9" value="<spring:message code="Button.skip" htmlEscape="true" text="Skip"></spring:message>" onclick="gotoManagePage();" /></label>
						<label> <input type="button" class="btn" name="Submit6" 	value="<spring:message code="Button.CLOSE" htmlEscape="true"  ></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /></label>
				</div>
				
		</div>
	</div>
	<c:if test="${msg != null}"> 
		<script>
			showSuccess();
		</script>
	</c:if>	
</body>

</html>