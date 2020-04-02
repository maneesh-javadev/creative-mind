<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<title></title>

<link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">

	 
	</script>
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message htmlEscape="true"  code="Label.VIEWSUBDISTHIST"></spring:message>
					</td>
					<%--//these links are not working <td align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true" 
								code="Label.HELP"></spring:message> --%>
					</a>
					</td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
		<c:if test="${! empty subDistrictHistory}">	
			<div class="frmpnlbg">
				<div class="frmtxt">
					<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.SUBDISTRICTHISTORY"></spring:message></div>
					   <table width="100%" class="tbl_with_brdr">
						<tr>
							<td width="14%" align="center">
								<tr class="tblRowTitle tblclear">
									<td width="6%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
									<td width="16%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SUBDISTRICTCODE"></spring:message></td>
									<td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SUBDISTRICTVERSION"></spring:message></td>
									<td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SUBDISTRICTNAMEENGLISH"></spring:message></td>
									 <td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.ACTIVESUBDISTRICT"></spring:message></td>
									<td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.CREATEDFROM"></spring:message></td>
									<%-- <td width="10%" rowspan="2"><spring:message htmlEscape="true"  code="Label.CREATEDBY"></spring:message></td> --%>
									<%-- <td width="30%" rowspan="2"><spring:message htmlEscape="true"  code="Label.FLAGCODE"></spring:message></td> --%>
								</tr>
								<tr>
									<td><br />
									</td>
								</tr></td>
						</tr>
												
							<c:forEach var="subDistrictHistoryDetail" varStatus="listSubDistrictRow" items="${subDistrictHistory}">
								<tr class="tblRowB">
									<td width="6%"><c:out value="${listSubDistrictRow.index+1}" escapeXml="true"></c:out></td>
									<td align="left"><c:out value="${subDistrictHistoryDetail.subdistrictCode}" escapeXml="true"></c:out></td>
									<td align="left"><c:out value="${subDistrictHistoryDetail.subdistrictVersion}" escapeXml="true"></c:out></td>
									<td align="left"><c:out value="${subDistrictHistoryDetail.subdistrictNameEnglish}" escapeXml="true"></c:out></td>
									<td align="left"><c:out value="${subDistrictHistoryDetail.active}" escapeXml="true"></c:out></td>
									<td align="left"><c:out value="${subDistrictHistoryDetail.lrReplaces}" escapeXml="true"></c:out></td>
									<%-- <td align="left"><c:out value="${subDistrictHistoryDetail.lrReplacedby}"></c:out></td> --%>
									<%-- <td align="left"><c:out value="${subDistrictHistoryDetail.flagCode}"></c:out></td> --%>
								</tr>
							</c:forEach>
						
					</table>
				</div>
				<!-- Close button removed - @vinay @since 18/01/2016 -->
				<%-- <div class="btnpnl">
					<table width="100%" class="tbl_no_brdr">
						<tr>
							<td>
								 <!-- BACK button removed - client requirement -->
							     <label><input type="button" name="Submit3" class="btn"	value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>"	onclick="javascript:location.href='login.do?<csrf:token uri='login.do'/>';" id="btnClose" /> </label>
							</td>
						</tr>
					</table>
				 </div> --%>
			   </div>
			</c:if>
		</div>
		<c:if test="${fn:length(viewPage) > 0}"> 
					<c:if test="${empty subDistrictHistory}">
					<div class="frmpnlbg" id="divData">
							<div class="frmtxt">
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td colspan="4" align="center"><spring:message htmlEscape="true" code="Error.noresult"></spring:message></td>
									</tr>
								</table>
							</div>
						</div>
					</c:if>
				</c:if> 
		
	</div>
</body>
</html>













<%-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<title><spring:message htmlEscape="true"  code="Label.VIEWSUBDISTHIST"></spring:message></title>
</head>
<body>
	<div class="frmpnlbrdr">
		<div id="frmcontent">
				<table width="100%" class="tbl_no_brdr">
					<tr>
						<td width="100%" valign="top" class="tblclear">
							<div id="frmcontent">
									<div class="frmhd">
										<table width="100%" class="tbl_no_brdr">
											<tr>
												  <td><spring:message htmlEscape="true"  code="Label.SEARCHHISTORY"></spring:message></td>
												  <td align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="App.HELP"></spring:message></a></td>
											</tr>
										</table>
									</div>
									<div class="frmpnlbrdr">
										<form:form action="viewSubDistrictAction.htm" method="POST" commandName="subDistrictForm" id="frmViewChildGovtBody">
											<div class="frmpnlbg">
												<div class="frmtxt">
													<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.SUBDISTRICTHISTORY"></spring:message></div>
													<table width="100%" class="tbl_no_brdr">
														<c:forEach var="subDistrictHistoryDetail" varStatus="listsubDistrictRow" items="${subDistrictForm.subDistrictHistoryDetail}">

															<tr>
																<td width="14%" rowspan="54">&nbsp;</td>
																<td><spring:message htmlEscape="true"  code="Label.SUBDISTRICTCODE"></spring:message><br />
																	      <label> <spring:bind path="subDistrictForm.subDistrictHistoryDetail[${listsubDistrictRow.index}].subdistrictCode">&nbsp;
																				               <c:out value="${status.value}" />
																		         </spring:bind> 
																	      </label>
																	<div class="errormsg"></div></td>
															</tr>

															<tr>
																<td><spring:message htmlEscape="true"  code="Label.SUBDISTVER"></spring:message><br />
																	      <label> <spring:bind path="subDistrictForm.subDistrictHistoryDetail[${listsubDistrictRow.index}].subdistrictVersion">&nbsp;
																				               <c:out value="${status.value}" />
																		          </spring:bind>
																		  </label>
																	<div class="errormsg"></div></td>
															</tr>
															
															<tr>
																<td><spring:message htmlEscape="true"  code="Label.SUBDISTRICTNAMEENGLISH"></spring:message><br />
																	      <label> <spring:bind path="subDistrictForm.subDistrictHistoryDetail[${listsubDistrictRow.index}].subdistrictNameEnglish">&nbsp;
																				               <c:out value="${status.value}" />
																		          </spring:bind>
																		 </label>
																	<div class="errormsg"></div></td>
															</tr>

													<tr>
														<td><spring:message htmlEscape="true"  code="Label.CREATEDFROM"></spring:message><br />
															<label> <spring:bind
																	path="subDistrictForm.subDistrictHistoryDetail[${listsubDistrictRow.index}].lrReplaces">&nbsp;
																				               <c:out value="${status.value}" />
																</spring:bind> </label>
															<div class="errormsg"></div>
														</td>
													</tr>

													<tr>
														<td><spring:message htmlEscape="true" 
																code="Label.CREATEDBY"></spring:message><br />
															<label> <spring:bind
																	path="subDistrictForm.subDistrictHistoryDetail[${listsubDistrictRow.index}].lrReplacedby">&nbsp;
																				               <c:out value="${status.value}" />
																</spring:bind> </label>
															<div class="errormsg"></div>
														</td>
													</tr>

												</c:forEach>
													</table>
												</div>

												<div class="btnpnl">
													<table width="100%" class="tbl_no_brdr">
														<tr>
															 <td>
															      <label><input type="button" class="btn" name="Submit" value="<spring:message htmlEscape="true"  code="App.BACK"></spring:message>" onclick="javascript:go('viewlocalgovtbody.htm');"/></label>
															      <label><input type="button" class="btn" name="Submit3" value="<spring:message htmlEscape="true"  code="App.CANCEL"></spring:message>" onclick="javascript:go('home.htm');" /> </label>
															</td>
														</tr>
													</table>
												</div>
											</div>
									</div>
								</div>
						   </td>
					 </tr>
				</table>
		  </form:form>
		</div>
	  </div>	
    </body>
</html> --%>
