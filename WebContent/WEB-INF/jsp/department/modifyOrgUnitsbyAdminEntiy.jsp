<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" />
<script>
	$(function() {		
		$( "#dialog:ui-dialog" ).dialog( "destroy" );
	
		$( "#dialog-message" ).dialog({
			modal: true,
			width: 700,
			height: 450,
			resizable: true,
            autoResize: true,
			buttons: {
				Ok: function() {
					document.getElementById('form1').action = "home.htm";
					document.forms['form1'].submit();
					$( this).dialog( "close" );
				}
			}
		});
	});
	</script>
</head>
<body>
	<form id="form1" action="">
		<!-- <div class="frmpnlbrdr"> -->

			<div id="dialog-message" title="${msgid}" style="display: none;">
				<c:if test="${adminEntityOrg.size() > 0}">
					<div class="frmtxt">
						<p>
							<span class="ui-icon ui-icon-circle-check"
								style="float: left; margin: 0 7px 50px 0;"></span> Name of Admin Unit Entity has been changed successfully. Due to this, names of the following Organization units have been changed.
						</p>
					<!-- 	<table id="tblrows" width="100%" class="tbl_no_brdr">

							<tr>
								<td align="center"> -->
									<table cellpadding="0" cellspacing="0" border="0"
										class="display" id="modifyAdminUnitLevel">
										<thead>
											<tr class="tblRowTitle tblclear">
												<th rowspan="2"><spring:message htmlEscape="true"
														code="Label.SNO"></spring:message></th>
												<th rowspan="2" align="left"><spring:message
														code="Label.ORGNAME">
													</spring:message></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="fyearval" items="${adminEntityOrg}"
												varStatus="listAdminRow">
												<tr class="tblRowB">
													<td align="center"><c:out value="${offsets*limits+(listAdminRow.index+1)}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${fyearval}" escapeXml="true"></c:out> </td>

												</tr>
											</c:forEach>
										</tbody>
									</table>
								<!-- </td>
							</tr>
						</table> -->
					</div>
				</c:if>


				<c:if test="${ empty adminEntityOrg}">
					<div class="frmtxt">

						<label><spring:message code="Label.NROrgUNis"
								text="No Records Updated for Admin Org Units"></spring:message></label>

					</div>
				</c:if>
			</div>
		<!-- </div> -->

	</form>
</body>
</html>
