<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@include file="../common/taglib_includes.jsp"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<title>E-Panchayat</title>
<script src="js/shiftdistrict.js"></script>
<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>

<link href="css/error.css" rel="stylesheet" type="text/css" />

<script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script src="js/dynCalendar.js" type="text/javascript"></script>
<script src="js/browserSniffer.js" type="text/javascript"></script>
<link href="css/dynCalendar.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" language="javascript">


dwr.engine.setActiveReverseAjax(true);


	
	
</script>
</head>

<%-- <%
	Calendar cal = Calendar.getInstance();

	int year = cal.get(Calendar.YEAR);

	System.out.println("Current Date: " + cal.getTime());
	String dateDay = cal.getTime().toString();

	// System.out.println("Day: " + day+":::dateDay---"+dateDay.substring(0,11)); 
	String currentDate = dateDay.substring(0, 10) + ",  " + year;
%>  --%>
<body onload="districtloadPage();"  oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
	<div id="frmcontent">

		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message code="Label.SHIFTDISTRICT" htmlEscape="true"></spring:message>
					</td>
				<%--//these links are not working  	<td width="50" align="right"><a href="#" class="frmhelp"><spring:message
								code="Button.HELP" htmlEscape="true"></spring:message> --%>
					</a></td>
				</tr>
			</table>
		</div>
	
		<div class="clear"></div>
		<div class="frmpnlbrdr">
		<form:form id="shiftdistrict" commandName="shiftdistrict" action="ShiftDistrict.htm" onsubmit="cursorwait();"
			method="POST" enctype="multipart/form-data">
			<div id="cat">
			<div class="frmpnlbg">
				<div class="frmtxt">
					<table width="100%" class="tbl_no_brdr">

						<tr>
							<td class="tblclear">
								<div id="tab_panel">
									<%-- <ul>
										<li><a href="#" class="current" id="tab1_header"  onclick="tabdisplay(id);"><spring:message
													code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message>
										</a>
										</li> --%>
										<li><a href="#" id="tab2_header"  onclick="tabdisplay(id);"><spring:message
													code="Label.SHIFTDISTRICTETAILS">
												</spring:message>
										</a>
										</li>
									</ul>
									<div class="clear"></div>
								</div>
								<div class="clear"></div>
								<div class="greybrdr">



<%-- 
									<div id="tab1">
										<div class="frmpnlbg">
													<div class="frmtxt">

														<table width="100%" class="tbl_no_brdr">
															<tr>
																<td width="14%" rowspan="9">&nbsp;</td>
																<td width="86%"><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message><br />

																	<form:input path="orderNo" id="OrderNo" type="text"
																		class="frmfield" onblur="validateOrdeNo();"
																		onfocus="show_msg('OrderNo');" onkeypress="validateNumericAlphaKeys();"/> <span class="msg"
																	id="OrderNo_msg"><spring:message
																			code="Msg.ORDERNO" htmlEscape="true"></spring:message> </span><span
																	class="error" id="OrderNo_error"></span></td>
															</tr>
															<tr>
																<td><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message><br />
																	<table width="100%" class="tbl_no_brdr">

																		<tr>
																			<td colspan="2" class="tblclear"><form:input
																					path="orderDate" id="OrderDate" type="text"
																					class="frmfield" style="width: 100px"
																					onfocus="show_msg('OrderDate');"
																					onblur="validateOrdeDate();"
																					onkeypress="validateNumeric();" /> <script
																					language="JavaScript" type="text/javascript">
               
                    function exampleCallback_ISO10(date, month, year)
                    {
                            if (String(month).length == 1) 
                            {
                                    month = '0' + month;
                            }

                            if (String(date).length == 1) 
                            {
                                    date = '0' + date;
                            }    
                        document.getElementById('OrderDate').value = date + '-' + month + '-' +year;
                        document.getElementById('EffectiveDate').value = date + '-' + month + '-' +year; 
                        document.getElementById('OrderDate').focus();  
                        document.getElementById('EffectiveDate_error').style.visibility='hidden';
                    	$("#EffectiveDate").removeClass("error_fld");
                    }
                    calendar10 = new dynCalendar('calendar10', 'exampleCallback_ISO10','<%=contextPath%>/images/');
														calendar10
																.setMonthCombo(true);
														calendar10
																.setYearCombo(true);
														calendar10
																.setYearComboRange(60);
													</script> <span class="msg" id="OrderDate_msg"><spring:message
																						code="Msg.ORDERDATE" htmlEscape="true"></spring:message> </span> <span
																				class="error" id="OrderDate_error"></span>
																			</td>
																			<!-- <td width="276" align="center" style="padding: 0px">
												<img
												src="images/calender.gif" width="17" height="16" border="0"
												alt="Pick Date" title="Pick Date" /></td> -->
																		</tr>
																	</table></td>

															</tr>
															<tr>
																<td><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message><br />
																	<table width="100%" class="tbl_no_brdr">
																		<tr>
																			<td colspan="2" class="tblclear"><form:input
																					id="EffectiveDate" path="effectiveDate" type="text"
																					class="frmfield" style="width: 100px"
																					onfocus="show_msg('EffectiveDate');"
																					onblur="validateEffecDate();"
																					onkeypress="validateNumeric();" /> <script
																					language="JavaScript" type="text/javascript">
               
                    function exampleCallback_ISO11(date, month, year)
                    {
                            if (String(month).length == 1) 
                            {
                                    month = '0' + month;
                            }

                            if (String(date).length == 1) 
                            {
                                    date = '0' + date;
                            }    
                        document.getElementById('EffectiveDate').value = date + '-' + month + '-' +year; 
                        document.getElementById('EffectiveDate').focus();                         
                    	                     
                    }
                    calendar11 = new dynCalendar('calendar11', 'exampleCallback_ISO11','<%=contextPath%>/images/');
                    calendar11.setMonthCombo(true);
                    calendar11.setYearCombo(true);
                   
													</script> <span class="msg" id="EffectiveDate_msg"><spring:message
																						code="Msg.EFFECTIVEDATE" htmlEscape="true"></spring:message> </span> <span
																				class="error" id="EffectiveDate_error"></span></td>

																		</tr>
																	</table></td>
															</tr>
															<tr>
																<td><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message><br />
																	<table width="100%	" class="tbl_no_brdr">

																		<tr>
																			<td colspan="2" class="tblclear"><form:input
																					id="GazPubDate" path="gazPubDate" type="text"
																					class="frmfield" style="width: 100px"
																					onfocus="show_msg('GazPubDate');"
																					onblur="validateGazPubDate();"
																					onkeypress="validateNumeric();" /> <script
																					language="JavaScript" type="text/javascript">
               
                    function exampleCallback_ISO12(date, month, year)
                    {
                            if (String(month).length == 1) 
                            {
                                    month = '0' + month;
                            }

                            if (String(date).length == 1) 
                            {
                                    date = '0' + date;
                            }                         
                        document.getElementById('GazPubDate').value = date + '-' + month + '-' +year;
                        document.getElementById('GazPubDate').focus();  
                    }
                    calendar12 = new dynCalendar('calendar12', 'exampleCallback_ISO12','<%=contextPath%>/images/');
																						calendar12
																								.setMonthCombo(true);
																						calendar12
																								.setYearCombo(true);
																					</script> <span class="msg" id="GazPubDate_msg"><spring:message
																						code="Msg.GAZPUBDATE" htmlEscape="true"></spring:message> </span> <span
																				class="error" id="GazPubDate_error"></span></td>

																		</tr>
																	</table></td>
															</tr>

															<tr>
																<td><spring:message code="Label.UPLOADGOVTORDER" htmlEscape="true"></spring:message><br />
																	<form:input id="filGovernmentOrder" path="filePath"
																		class="frmfield" type="file" size="25"
																		onfocus="show_msg('filGovernmentOrder');"
																		onblur="validateSFile();" /> <span class="msg"
																	id="filGovernmentOrder_msg"><spring:message
																			code="Msg.UPLOADGOVTORDER" htmlEscape="true"></spring:message> </span> <span
																	class="error" id="filGovernmentOrder_error"></span>
																</td>
															</tr>
															<tr>
								<td>
								&nbsp;
								</td>
								
								</tr>
															<tr>

																<td><label><input type="button"
																	name="Submit2" class="btn" value=<spring:message code="Button.NEXT" htmlEscape="true"></spring:message>
																	id="btnNext" onclick="next(1)" /> </label> <label> <input
																	type="button" name="Submit2" class="btn" value=<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>
																	id="btnCancel" onclick="javascript:go('home.htm');" />
															</label>
															</td>
															</tr>
															<tr>
																<td>&nbsp;</td>

															</tr>
														</table>
													</div>

												</div>
									</div>
									<div id="tab2" style="display: none;">
 --%>
										
										<div class="frmpnlbg">
											<div class="frmtxt">

												<table width="100%" class="tbl_no_brdr">
													<tr>

														<td width="14%" rowspan="4">&nbsp;</td>
														<td width="86%"><spring:message
																code="Label.SOURCESTATE" htmlEscape="true"></spring:message>
																<span class="errormsg">*</span>
																<br /> <form:select
																path="stateNameEnglish" class="combofield" htmlEscape="true"
																style="width: 230px" id="ddSourceState"
																onchange="getList(this.value);">
																<form:option value="0"><spring:message
																code="Label.SELECTSTATE" htmlEscape="true"></spring:message></form:option>
																<%-- <form:options items="${stateSourceList}" itemLabel="stateNameEnglish" itemValue="stateCode"/> --%>
																<form:options items="${stateSourceList}"
																	itemValue="statePK.stateCode"
																	itemLabel="stateNameEnglish"></form:options>	</form:select>
															<span class="errormsg" id="ddSourceState_error"><spring:message
																code="Error.SOURCESTATE" htmlEscape="true"></spring:message>
														 <form:errors path="stateNameEnglishSource" htmlEscape="true"
																class="errormsg"></form:errors></span></td>


													</tr>
													<tr>
														<td><spring:message code="Label.TARGETSTATE" htmlEscape="true"></spring:message><span class="errormsg">*</span>
															<br /> <form:select path="stateNameEnglish"
																class="combofield" style="width: 230px" id="ddDestState">

															</form:select> 
																<span class="errormsg" id="ddDestState_error">
																<spring:message
																code="Error.TARGETSTATE" htmlEscape="true"></spring:message>
															<form:errors
																	path="stateNameEnglishDest" class="errormsg"></form:errors>
														</span></td>
													</tr>
													<tr>
														<td><br /> <label></label>
														</td>
													</tr>
													<tr>
														<td class="tblclear"><table width="570"
																class="tbl_no_brdr">

																<tr>
																	<td><spring:message code="Label.DISTRICT" htmlEscape="true"></spring:message>
																	</td>
																	<td>&nbsp;</td>
																	<td>&nbsp;</td>
																</tr>
																<tr>
																	<td width="235" rowspan="3">
																	<spring:message
																				code="Label.AVAILABLEDISTRICT" htmlEscape="true"></spring:message><br/>
																	<form:select
																			id="ddSourceDistrict" path="districtNameEnglish" htmlEscape="true"
																			size="1" multiple="multiple" class="frmtxtarea"
																			style="height: 120px; width: 230px">

																		</form:select></td>
																	<td width="100" align="center"><input
																		type="button" class="btn" value=" &gt;&gt;"
																		style="width: 40px"
																		onclick="listbox_moveacross('ddSourceDistrict', 'ddDestDistrict')" />

																	</td>
																	<td width="235" rowspan="3">
																	<spring:message 		code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message><br/>
																	<form:select
																			path="districtNameEnglish" id="ddDestDistrict" htmlEscape="true"
																			size="1" multiple="multiple" class="frmtxtarea"
																			style="height:120px; width: 233px"
																			onfocus="show_msg('ddDestDistrict');">
																		</form:select><br /> 
																		<span class="errormsg" id="ddDestDistrict_error">
																		<spring:message code="Error.DISTRICT" htmlEscape="true"></spring:message>
																		</span>
																		<form:errors path="districtNameEnglish" class="errormsg"></form:errors>
																	
																	
																	</td>
																</tr>
																<tr>
																	<td align="center"><input type="button"
																		class="btn" value=" &lt;&lt;" style="width: 40px"
																		onclick="listbox_moveacross('ddDestDistrict', 'ddSourceDistrict')" />
																	</td>

																</tr>
															</table></td>
													</tr>

												</table>
											</div>

										</div>
										<div class="btnpnl">
											<table width="100%" class="tbl_no_brdr">
												<tr>
													<td width="16%" rowspan="2">&nbsp;</td>
													<td width="84%">
														<input type="button" value=<spring:message code="Button.PREVIOUS"></spring:message>
													onclick="previous(2)" class="btn" />
															<label> <input type="submit" name="Save"
																id="btnSave" onclick="return validateAll();" 
																value=<spring:message code="Button.SAVE"></spring:message> />
															</label><label> <input type="button" class="btn"
																name="Cancel"
																value=<spring:message code="Button.CLOSE"></spring:message>
																id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
															</label>
														</div> <br /> <br /> <br />
													</td>

												</tr>
												<tr>
													<td>&nbsp;</td>
												</tr>
											</table>
										</div>
									</div>
								</div>


								</div></td>
						</tr>
					</table>
				</div>
			</div>
		</form:form>
		</div>
		</div>
		
</body>
</html>