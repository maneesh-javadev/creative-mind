<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/datatable/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/datatable/jquery-ui-1.8.7.custom.min.js"></script>
<script src="<%=contextPath%>/datatable/TableTools.js" charset="utf-8" type="text/javascript"></script>
<script src="<%=contextPath%>/datatable/ZeroClipboard.js" charset="utf-8" type="text/javascript"></script>
<link href="<%=contextPath%>/datatable/jquery-ui-1.8.7.custom.css" rel="stylesheet"  type="text/css" />
<link href="<%=contextPath%>/datatable/demo_table_jui.css" rel="stylesheet"  type="text/css" />
<link href="<%=contextPath%>/datatable/TableTools.css" rel="stylesheet"  type="text/css" />

<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script src="js/common.js"></script>

<title>Insert title here</title>
<style type="text/css">
	.datatable{
		border-collapse: collapse;
	}
	
	.datatablehead{
		background-color: #dedede;
	}
	
</style>

<script type="text/javascript"> 

$(document).ready(function() {
	$("#tbl_with_brdr tr:even").css("background-color", "#f7f7f7");
	$("#tbl_with_brdr tr:odd").css("background-color", "#fff");
	//$('#abc').val(strUser.toString());
	
} );


function CallPrint()
{ 

	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';
	var printContent = document.getElementById("viewLbPesaDetail"); 
	//document.getElementById('btn1').style.visibility = 'hidden';
	var Win4Print = window.open('','','left=0,top=0,width=500,height=500,top=301,left=365,resizable=1,status=0,toolbar=0'); 
	//alert("contect"+printContent.innerHTML);
	Win4Print.document.write(printContent.innerHTML); 
	Win4Print.document.close(); 
	Win4Print.focus(); 
	window.print(); 
	window.close(); 
	document.getElementById('footer').style.display = 'none';
	document.getElementById('footer').style.visibility = 'hidden';
}

</script>

</head>
<body onload="CallPrint();">

		<input type="hidden" path="coordinates" id="abc" ></input>
				<div id="viewLbPesaDetail" >
					<c:if test="${! empty getLbPesa}">
						<div class="frmpnlbg" id="divData">
							<div align="center" style="font-size: 12pt;">
								<b><spring:message htmlEscape="true" code="Label.PESAPanchayat"	text="List of PESA enabled Village Panchayats of " />
									<c:out value="${stateName}" escapeXml="true"></c:out></b>
							</div>
							<br />

							<div class="frmtxt">
								<table id="tblrows" width="100%" class="tbl_no_brdr">

									<tr>
										<td>
											<table class="datatable" cellpadding="0" cellspacing="0" border="1" id="tbl_with_brdr">
												<thead>
													<tr style="background-color: gray;">
														<th align="center"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></th>
														<th align="center"><spring:message code="Label.ZillaPanchayatname" text="Zilla Panchayat name"></spring:message></th>
														<th align="center"><spring:message code="Label.INTERMEDIATEPANCHAYATNAME" text="Intermediate Panchayat Name"></spring:message></th>
														<th align="center"><spring:message code="Label.LGDCodeofLocalbody" text="LGD Code of Village Panchayat"></spring:message></th>
														<th align="center"><spring:message code="Label.NameofLocalbody(In English)" text="Name of Village Panchayat (In English)"></spring:message></th>
														<th align="center"><spring:message code="Label.NameofLocalbody(In Local language)" text="Name of Village Panchayat (In Local language)"></spring:message></th>
														<th align="center"><spring:message code="Label.PesaCoverageType" text="PESA Coverage Type"></spring:message></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="adminEntityDetail" varStatus="listAdminRow"	items="${getLbPesa}">
														<tr >
															<td align="center"><c:out value="${listAdminRow.count}" escapeXml="true"></c:out></td>
															<td align="center"><c:out value="${adminEntityDetail.dpName}" escapeXml="true"></c:out></td>
															<td align="center"><c:out value="${adminEntityDetail.ipName}" escapeXml="true"></c:out></td>
															<td align="center"><c:out value="${adminEntityDetail.vpCode}" escapeXml="true"></c:out></td>
															<td align="center"><c:out value="${adminEntityDetail.vpNameEng}" escapeXml="true"></c:out></td>
															<td align="center"><c:out value="${adminEntityDetail.vpNameLoc}" escapeXml="true"></c:out></td>
															<td align="center">
																<c:choose>
																	<c:when test="${fn:contains(adminEntityDetail.coverageType,'P')}"><spring:message code="Label.PART" ></spring:message></c:when>
																	<c:otherwise><spring:message code="Label.FULL" ></spring:message></c:otherwise>
																</c:choose>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</td>
									</tr>
									
							</table>
							<ul class="blocklist center" style="text-align: center; list-style: none;"> <!-- Inline style only for Print purpose -->
										<li><label><spring:message code="Label.URL"
													htmlEscape="true"></spring:message> <%=request.getScheme() + "://"
									+ request.getServerName()
									+ request.getContextPath()%>
										</label></li>
										<li>
											<%
												java.text.DateFormat df = new java.text.SimpleDateFormat(
																		"dd/MM/yyyy hh:mm:ss a");
											%>
											<label><spring:message code="Label.REPORTPRINTED"
													htmlEscape="true"></spring:message> <%=df.format(new java.util.Date())%>
										</label></li>
										<li><label><spring:message
													code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message>
										</label></li>
									</ul>
						</div>
					</c:if>
					<div id="footer" style="visibility: hidden; display: none;">

						<p id="footertext" style="text-align: center">
										Site is designed, hosted and maintained by <a
											target="_blank" href="http://www.nic.in/">National
											Informatics Centre</a><br> Contents on this website is
											owned,updated and managed by the <a target="_blank"
											href="http://www.panchayat.gov.in/" target="_blank">Ministry of
												Panchayati Raj</a>, Government of India <br> Site best
												viewed on Internet Explorer (IE)-8 &amp; above, Mozilla
												Firefox-11 &amp; above 
						</p>
						<div id="displayBox" style="text-align: center;display:none;"><img  src="<%= request.getContextPath() %>/images/ajax-loader-2.gif"  /></div>
			      		<form id="ajaxErrorFrom" action="badRequest.htm"></form> 	

					</div> 
								
						      
						      
				</div>
</body>
</html>