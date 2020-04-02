<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<style>
table.data_grid_dialog{
border:1px solid #cecece;
font-size:12px;
color:#555;
}
table.data_grid_dialog tr th, table.data_grid_dialog tr td{
border-left:1px solid #cecece;
border-bottom:1px solid #cecece;
padding:4px;
text-align:left;
transition:all ease-in-out 0.2s;
}
table.data_grid_dialog tr th:first-child, table.data_grid_dialog tr td:first-child{
border-left:none;
}
table.data_grid_dialog tr th{
background:#eee;
color:#333;
border-bottom:2px solid #c3c3c3;
font-weight:bold;
font-size:11px;
text-transform:uppercase;
}
table.data_grid_dialog tbody tr:hover{
background:#f5f5f5;
}
table.data_grid_dialog > tbody tr:hover td{
border-bottom:1px solid #aaa;
color:#333;
text-indent:1px;
}
</style>
<c:if test="${adminEntityOrg.size() > 0}" >
<table class="data_grid_dialog" width="100%">
												<thead>
													<tr >
														<th rowspan="2"><spring:message htmlEscape="true"
																code="Label.SNO"></spring:message></th>
														<th rowspan="2" align="left"><spring:message
																code="Label.ORGNAME">
																</spring:message></th>
													</tr>
												</thead>
												<tbody>
												<c:forEach var="fyearval" items="${adminEntityOrg}" varStatus="listAdminRow" >
													<%-- <c:forEach var="adminEntityDetail" varStatus="listAdminRow"
														items="${DEPT_ADMIN_LEVEL}"> --%>
														<tr class="tblRowB">
															<td align="center"><c:out value="${offsets*limits+(listAdminRow.index+1)}"  escapeXml="true"></c:out></td>
															<td align="left">
															<c:out value="${fyearval}" escapeXml="true"></c:out>
															<%-- <c:out
																	value="${adminEntityDetail.adminUnitEntityCode}"></c:out> --%></td>
														
														</tr>
													</c:forEach>
												</tbody>
											</table>
	<!-- <table id="tblrows" width="100%" class="tbl_no_brdr">
			<tr>
				<td  align="center">
					
										
										</td>
									</tr>
								</table> -->
							
						
	                  </c:if>
	                   <c:if test="${ empty adminEntityOrg}">
	               	<div class="frmtxt">
	             
	                  <label><spring:message
													code="Label.NROrgUNis"
													text="No Records found for Admin Org Units"></spring:message></label>
	                  
	                 </div>
	                 </c:if>

</body>
</html>