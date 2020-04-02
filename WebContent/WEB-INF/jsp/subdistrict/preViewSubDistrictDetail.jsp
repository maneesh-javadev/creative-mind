<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<script type="text/javascript" src="js/cancel.js"></script>
<html>
<head>
<title><spring:message code="App.CREATENEWSUBDISTRICT"></spring:message></title>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/new_sub_district.js" charset="utf-8"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSurveyService.js'></script>
<link href="HTML_panchayat - 2.2/css/panchayat_main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
	
</script>

</head>
<body onload="onloadSelect()">
	<div id="content">
		<table width="100%" class="tbl_no_brdr">
			<tr>
				<td valign="top">
				<div id="frmcontent">
						<div class="frmhd">
						<table width="100%" class="tbl_no_brdr">
                        <tr>
                          <td><spring:message code="Label.REVIEWSUBDISTRICT" ></spring:message>&nbsp;</td>
                          <td>&nbsp;</td>
                          <td width="50" align="right"><a href="#" class="frmhelp">Help</a></td>
                        </tr>
                      </table>
						</div>
						<div class="clear"></div>
						
							<form:form action="preview_subdistrict.htm" method="POST"
								commandName="newsubdistrictform" enctype="multipart/form-data" >
								<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="preview_subdistrict.htm"/>"/>
								<div class="frmpnlbg">
									<div class="frmtxt"
										>
										<div class="frmhdtitle">
											<spring:message code="App.GENERALDETAILOFNEWSUBDISTRICT" htmlEscape="true"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
										<tr>
												<td width="14%" rowspan="8">&nbsp;</td>
												<td width="86%">
												<spring:message code="App.DISTRICT" htmlEscape="true"></spring:message><br/>
												<label>
                                                     <input type="text" value="<c:out value='${distName}'/>" readonly="readonly" class="frmfield"/>
												</label>
													<div style="height: 15px; padding-top: 3px;"
														class="errormsg"></div>
												</td>
											</tr>
											<tr>
												<td ><spring:message
														code="App.NAMEOFNEWSUBDISTRICTENGLISH" htmlEscape="true"></spring:message><br />
													<label>
                                                     <form:input path="subdistrictNameEnglish" readonly="true" htmlEscape="true" class="frmfield"/>
													</label>
													<div style="height: 15px; padding-top: 3px;"
														class="errormsg"></div></td>
											</tr>
											<tr>
												<td><spring:message code="App.NAMEOFNEWSUBDISTRICTLOCAL" htmlEscape="true"></spring:message><br />
													<label> <form:input path="subdistrictNameLocal" readonly="true" htmlEscape="true" class="frmfield"/>
												</label>
													<div style="height: 15px; padding-top: 3px;"
														class="errormsg"></div></td>
											</tr>
											<tr>
												<td><spring:message code="App.ALIASENGLISH" htmlEscape="true"></spring:message><br />
													<label> <form:input
															path="aliasEnglish" readonly="true" htmlEscape="true" class="frmfield"/> </label>
													<div style="height: 15px; padding-top: 3px;"
														class="errormsg"></div></td>
											</tr>
											<tr>
												<td><spring:message code="App.ALIASLOCAL" htmlEscape="true"></spring:message><br />
													<label> <form:input path="aliasLocal" readonly="true" htmlEscape="true" class="frmfield"/>
												</label>
													<div style="height: 15px; padding-top: 3px;"
														class="errormsg"></div></td>
											</tr>
											<tr>
												<td><spring:message code="App.CENSUSCODE2011" htmlEscape="true"></spring:message><br />
													<label> <form:input path="census2011Code" readonly="true" htmlEscape="true" class="frmfield"/> </label>
													<div style="height: 15px; padding-top: 3px;"
														class="errormsg"></div></td>
											</tr>
											<tr>
												<td><spring:message code="App.STATESPECIFICCODE" htmlEscape="true"></spring:message><br />
													<label> <form:input path="sscode" readonly="true" htmlEscape="true" class="frmfield"/>
												</label>
													<div style="height: 15px; padding-top: 3px;"
														class="errormsg"></div></td>
											</tr>
											
										</table>
									</div>
								</div>
								<%-- <div class="frmpnlbg">
									<div class="frmtxt"
										style="position: relative; background: inherit; padding-top: 10px;">
										<div class="frmhdtitle">
											<spring:message code="App.SUBDISTRICTHEADQUARTER"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="14%" rowspan="2">&nbsp;</td>
												<td width="86%" style="padding: 0px"/>
												<table width="100%" class="tbl_no_brdr">
														<tr>
															<td><label id="headquarterName"><spring:message
																	code="App.SUBDISTRICTHEADQUARTERENGLISH"></spring:message></label>
																	&nbsp;<form:input type="text" name="headquarterName" path="headquarterName" readonly="true"/>
															&nbsp;<label id="headquarterNameLocal"><spring:message
																	code="App.SUBDISTRICTHEADQUARTERLOCAL"></spring:message></label>
																	&nbsp;<form:input type="text" name="headquarterNameLocal" path="headquarterNameLocal" readonly="true" />
															<td>&nbsp;</td>
														</tr>
											</tr>
										</table>
										</tr>
										</table>
									</div>
								</div> --%>
								<%-- <%@ include file="../common/headquarter.jspf" %> --%>
								<%-- <div class="frmpnlbg">
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message code="App.GISNODES"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="14%" rowspan="2">&nbsp;</td>
												<td width="86%" style="padding: 0px">
												<table width="100%" class="tbl_no_brdr">
														<tr>
															<td width="150"><spring:message code="App.GISNODES"></spring:message>
															<td width="150" align="right">	 
															<label id="lbllatitude"><spring:message
																	code="App.LATITUDE"></spring:message></label></td>
															<td width="75"><form:input type="text" path="lati" readonly="true"/></td>
															<td width="150" align="right"><label id="lbllongitude"><spring:message
																	code="App.LONGITUDE"></spring:message></label></td>
															<td width="75"><form:input type="text" path="longi" readonly="true"/></td>
															<td>&nbsp;</td>
															
														</tr>
														<tr>
														<td width="200"></td>
															<td width="75" align="right"></td>
															<td width="75"></td>
															<td width="75" align="right"></td>
															<td width="75"></td>
															<td>&nbsp;</td>
															<td>
																<div id="addgisnodes"></div>
															</td>
														</tr>
													</table>
													<div style="height: 15px; padding-top: 3px;"
														class="errormsg"></div></td>
											</tr>
											<tr>
												<td valign="top">
												<table width="100%" class="tbl_no_brdr">
														<tr>
															<td><label><span class="blktxt"><spring:message
																			code="App.MAPSUBDISTRICT"></spring:message> </span> </label>
															&nbsp;&nbsp;&nbsp;&nbsp;<form:input path="mapUrl" readonly="true"/>
															</label></td>
														</tr>
													</table>
													<div style="height: 15px; padding-top: 3px;"
														class="errormsg"></div>
												</td>
											</tr>
										</table>
									</div>
								</div> --%>
								<%-- <%@ include file="../common/gis_nodes.jspf" %> --%>
								<div class="frmpnlbg">
									<div class="frmtxt"
										style="position: relative; background: inherit; padding-top: 20px;">
										<div
											style="position: absolute; z-index: 1; width: 164px; text-align: center; top: -11px; left: 12px"
											class="frmhdtitle">
											<spring:message code="App.CONTRIBUTINGLANDREGION" htmlEscape="true"></spring:message>
										</div>
										
									<table>
				
			
									<tr>
									<td width="86%" style="padding: 0px">
										<table width="664" border="0" cellspacing="0" cellpadding="0">

						<tr>
							<td><strong><spring:message code="Label.CONTRIBUTINGSUBDISTRICTLIST" htmlEscape="true"></spring:message>
							</strong></td>
							<td align="center">&nbsp;</td>
							<td><strong><spring:message
										code="Label.CONTRIBUTINGVILLAGELISTSD" htmlEscape="true"></spring:message> </strong></td>
						</tr>
						<tr>
							<td width="86%" style="padding: 0px">
							<form:select name="select4"
								id="subDistrictListNew" size="1" multiple="multiple" path="contributedSubDistricts" 
								class="frmtxtarea" style="height: 80px; width: 242px"  readonly="true">
								<form:options items="${listSD}" itemLabel="subdistrictNameEnglish"
									itemValue="aliasEnglish" htmlEscape="true"/>
							</form:select>
							</td>
							<td align="center" style="padding-top: 3px">
							</td>
							<td width="246" valign="top">
							<form:select name="select4"
								id="villageListNew" size="1" multiple="multiple" path="contributedVillages" 
								class="frmtxtarea" style="height: 80px; width: 242px" readonly="true" htmlEscape="true">
								<form:options items="${listVill}" itemLabel="villageNameEnglish"
									itemValue="villageCode" htmlEscape="true"/>
							</form:select></td>
						</tr>
						
					</table>
					
					<div style="height: 15px; padding-top: 3px;"></div>
					
				</td>
			</tr>
		</table>
	</div>
		</div>
	<div >
		<div class="frmtxt">
			<table width="100%" class="tbl_no_brdr">
				<tr>
				<input type="hidden" name="reorganized" id="reorganized"/>
				<input type="hidden" name="modifyVillage" id="modifyVillage"/>
				<input type="hidden" name="addAnotherSD" id="addAnotherSD"/>
				<form:hidden path="govtOrderConfig" htmlEscape="true"/>
					<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td ><div style="padding: 10px 0px 10px 0px">
								<label> <input type="submit" id="Submit" class="btn"  
								value="<spring:message code="Button.N" htmlEscape="true"></spring:message>" /></label> 
								<%-- <input type="button" id="AddAnotherSD"
								value="<spring:message code="App.ADDANOTHERSUBDISTRICT"></spring:message>"
								onclick="formSubmitAddPreview();" /> --%>
								<label> <input type="button" name="Cancel"
													class="btn"
													value=<spring:message code="Button.CLOSE"></spring:message>
													id="btnCancel" onclick="javascript:location.href='cancelSD.htm?<csrf:token uri="cancelSD.htm"/>'" /> </label>
						</div></td>
				</tr>
			</table>
		</div>
	</div>
	</form:form>
		<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>	
	
	</td>
	</tr>
	</table>

</body>
</html>