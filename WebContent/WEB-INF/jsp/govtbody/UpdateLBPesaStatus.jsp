<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
<title>Update Pesa Status</title>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>	
</script>
<script src="js/lgd_localbody.js"></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrLocalGovtBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
</script>
<script type="text/javascript" src="js/invalidatelocalbody.js"
	charset="utf-8"></script>

<script> 
$(document).ready(function() {
	var type= '<c:out value="${flag}" escapeXml="true"></c:out>';
	if (type == 0) {
		document.getElementById('firstbox').style.visibility = 'visible';
		document.getElementById('firstbox').style.display = 'block';
		document.getElementById('secondbox').style.visibility = 'hidden';
		document.getElementById('secondbox').style.display = 'none';
		
	} else if (type == 1) {
		document.getElementById('secondbox').style.visibility = 'visible';
		document.getElementById('secondbox').style.display = 'block';
		document.getElementById('firstbox').style.visibility = 'hidden';
		document.getElementById('firstbox').style.display = 'none';
		document.getElementById('divLgdLBType').style.visibility = 'hidden';
		document.getElementById('divLgdLBType').style.display = 'none';
		earlyChecked = $('input:checkbox:checked').map(function() {
			return this.value;
		}).get();
		
	}
	else if (type == 3) {
		document.getElementById('firstbox').style.visibility = 'visible';
		document.getElementById('firstbox').style.display = 'block';
		document.getElementById('secondbox').style.visibility = 'hidden';
		document.getElementById('secondbox').style.display = 'none';
		
	}
	$("#tbl_with_brdr tr:even").css("background-color", "#ffffff");
	$("#tbl_with_brdr tr:odd").css("background-color", "#dedede");
	
} );

</script>

</head>


<body>
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message htmlEscape="true" text="Update Pesa Status" code="Label.UPDATEPESASTATUS"></spring:message></h3>
			<ul class="listing">
				<%--//this link is not working <li>
					<a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a>
				</li> --%>
			</ul>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="getLBPesaRecords.htm" id="form1" commandName="localGovtBodyForm" method="POST" >
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="getLBPesaRecords.htm"/>" />
				<input type="hidden" name="choosenlb" id="choosenlb" value="" />
				<input type="hidden" id="listformat" name="listformat" value="" />
				<div id='divLgdLBType' class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message>
						</div>
							<div id="divLgdVillageP" class="block">
								<ul class="blocklist">
									<li>
										<label><spring:message htmlEscape="true"
												code="Label.SELECTDISTICTPANCHAYAT"></spring:message></label><span
										class="errormsg" style="width: 200px">*</span><br /> <form:select
											path="lgd_LBDistrictAtVillage" class="combofield"
											id="districtpanchyatId" htmlEscape="true"
											onchange="clearlbPesaInputErrors();getWorkOnVillagePanchayatforDistrictP(this.value);"
											style="width: 200px">
											<form:option value="0" htmlEscape="true">
												<spring:message htmlEscape="true"
													code="Label.SELECTLOCALBODY"></spring:message>
											</form:option>
											
											
											
											<c:forEach items="${districtPanchayatList}" var="distListvar">
													  <c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
													    <form:option value="${distListvar.localBodyCode}" disabled="true" htmlEscape="true"><esapi:encodeForHTMLAttribute>${distListvar.localBodyNameEnglish}</esapi:encodeForHTMLAttribute></form:option>
													  </c:if>  
													  <c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
													     <form:option value="${distListvar.localBodyCode}" htmlEscape="true"><esapi:encodeForHTMLAttribute>${distListvar.localBodyNameEnglish}</esapi:encodeForHTMLAttribute></form:option>
													  </c:if>
											</c:forEach>
										
										</form:select> &nbsp;
										<div class="errormsg" id="ddlgdLBDistrictAtVillage_error1">
											<form:errors path="lgd_LBDistrictAtVillage"
												htmlEscape="true" />
										</div>
										<div id="districtpanchyatIdError" style="color: red;"></div>
									</li>
									<li>
										<label><spring:message htmlEscape="true"
												code="Label.SELINTERMPANCHYAT"></spring:message></label><span
										class="errormsg">*</span><br /> <form:select
											onchange="clearlbPesaInputErrors()" htmlEscape="true"
											path="lgd_LBIntermediateAtVillage" class="combofield"
											id="ddlgdLBInterAtVillage" style="width: 200px">
											<form:option value="0" htmlEscape="true">
												<spring:message htmlEscape="true"
													code="Label.SELECTINTERMEDIATELOCBODY"></spring:message>
											</form:option>
										</form:select> &nbsp;
										<div id="intermediatepanchyatIdError" style="color: red;"></div>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div id="manageEntity">
					<c:if test="${! empty lbList}">
						<div class="frmpnlbg" id="divData">
							
								<table class="tbl_with_brdr" width="100%" align="center" id="tbl_with_brdr"  border="1" >
									<tr class="tblRowTitle tblclear" style="height: 50px;">
										<td width="14%" align="center">
											<table cellpadding="1" cellspacing="0" 
												class="display" id="modifyAdminUnitLevel" >
												<thead style="border:1px solid black;">
													<tr class="tblRowTitle tblclear" style="border:1px solid black;">
														<th rowspan="2" style="border:1px solid black;"><spring:message htmlEscape="true"
																code="Label.SNO"></spring:message></th>
														<th rowspan="2" style="border:1px solid black;"><spring:message htmlEscape="true"
																code="Label.LOCALBODYCODE"></spring:message></th>
														<th rowspan="2" style="border:1px solid black;"><spring:message htmlEscape="true"
																code="Label.LOCALBODYNAMEINENG"></spring:message></th>
														<th rowspan="2" style="border:1px solid black;"><spring:message htmlEscape="true"
																code="Label.NAMEINLOCALLANGUAGE"></spring:message></th>

														<th rowspan="2" style="border:1px solid black;"><spring:message htmlEscape="true"
																code="Label.STATESPECIFICCODE"></spring:message></th>

														<th rowspan="2" style="border:1px solid black;"><spring:message htmlEscape="true"
																text="Update Pesa Status" code="Label.UpdatePesaStatus"></spring:message></th>
													</tr>
													<tr style="border:1px solid black;"></tr>
												</thead>
												<tbody style="border:1px solid black;">
													<c:forEach var="adminEntityDetail" varStatus="listAdminRow"
														items="${lbList}" >
														<tr class="tblRowB" >
															<td style="border:1px solid black;"><c:out value="${offsets*limits+(listAdminRow.index+1)}" escapeXml="true"></c:out></td>
															<td style="border:1px solid black;"><c:out
																	value="${adminEntityDetail.localBodyCode}" escapeXml="true"></c:out></td>
															<td align="center" style="border:1px solid black;"><c:out
																	value="${adminEntityDetail.localBodyNameEnglish}" escapeXml="true"></c:out></td>
															<td align="center" style="border:1px solid black;"><c:out
																	value="${adminEntityDetail.localBodyNameLocal}" escapeXml="true"></c:out></td>

															<td align="center" style="border:1px solid black;"><c:out
																	value="${adminEntityDetail.stateSpecificCode}" escapeXml="true"></c:out></td>
															<td align="center" style="border:1px solid black;"><input class="messageCheckbox"
																type="checkbox"
																value="<c:out value="${adminEntityDetail.localBodyCode}" escapeXml="true"></c:out>"
																
																<c:if test="${adminEntityDetail.ispesa == true}">checked</c:if> />
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</td>
									</tr>
							</table>
						</div>
					</c:if>
				</div>
				<div class="btnpnl">
					<div id="firstbox">
						<input type="submit"
										value="Proceed"
										onclick="return checkRequiredInputsforPesa(); " name="Submit"
										class="btn" /></label> <label> <input type="button"
										name="Submit6" class="btn"
										value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
										onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
					</div>
					<div id="secondbox">
						<input type="button" value="Save" onclick="updatePesaRecords();" class="btn" />
						<input type="button" name="Submit6" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
					</div>
					<c:if test="${flag == 3}">
						<div class="frmpnlbg" id="divData">
							<div class="frmtxt">
								<spring:message htmlEscape="true" code="error.no.rec.found.LBPesa" text="No Records Found " />
							</div>
						</div>

					</c:if>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>

