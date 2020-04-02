<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%@include file="../common/taglib_includes.jsp"%>
<script src="js/govtorder.js"></script>
<script src="js/new_ward.js"></script>
<script src="js/lgd_localbody.js"></script>
<link href="css/errorLB.css" rel="stylesheet" type="text/css" />
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script src="js/validationWard.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script type="text/javascript" language="Javascript">
	
</script>

<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>

<script src="js/common.js"></script>
<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<link rel="stylesheet" href="datepicker/demos.css" /> -->
<script src="js/browserSniffer.js" type="text/javascript"></script>

<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
</script>
</head>
<body onload="onLoadWardForm();">
	
	<div class="overlay" id="overlay1" style="display: none;"></div>
	<div class="box" id="box1">
		<a class="boxclose" id="boxclose1"></a>
		<div>
			<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<div class="success"></div>
							</center></td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div></td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center>
							</div></td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><div class="failur"></div></td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center></td>
					</tr>
					<tr>
						<td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="true"></c:out></div>
						</td>
					</tr>
				</table>

			</c:if>

		</div>
	</div>

	<div class="box" id="box">
		<a class="boxclose" id="boxclose"></a>
		<div id="validate_error">
			<table>
				<tr>
					<td rowspan="2"><div class="errorImg"></div></td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div></td>
				</tr>
				<tr>
					<td><div class="errorfont"><spring:message htmlEscape="true" code="Error.entermandatoryfield"></spring:message></div>
					</td>
				</tr>
			</table>

		</div>
	</div>

	<div id="helpDiv" class="helpMessage">
		<div class="helpheader">
			<h4>Help Message</h4>
		</div>
		<div id="helpMsgText" class="helptext"></div>
	</div>

	<div id="frmcontent">
		<div class="frmhd">

			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message htmlEscape="true" code="Label.MODIFYWARD" ></spring:message>
					</td>
					<td align="right"><a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a>
					</td>
					<td width="50" align="right"><a href="#" class="frmhelp">Help</a>
					</td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
		<form:form action="modifyUrbanWardAction.htm" method="POST" commandName="modifyUrbanWardCmd" id="frmModifyVillage" enctype="multipart/form-data">
			<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="modifyWardAction.htm"/>" />
		 	<form:hidden path="lgd_LBNameInEn" id="oldWardName" value="${modifyUrbanWardCmd.ward_Name}" htmlEscape="true" />
		    <form:hidden path="wardCcode" id="oldWardCode" value="${modifyUrbanWardCmd.ward_number}" htmlEscape="true" />
		     
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">
								<%--<tr>
									 <td width="100%">
										<div class="frmpnlbg">
											<div class="frmtxt">
												<div class="frmhdtitle">
													<spring:message htmlEscape="true" code="Label.LOCALBODY"></spring:message>
												</div>
												<table width="100%" class="tbl_no_brdr">
													<tr>
														<td width="14%"><input type="hidden" name="stateCode"
															value="${stateCode}" />
															<td width="30%"><spring:message htmlEscape="true"
																	code="Label.SELLOCALBODYTYPE"></spring:message> <span
																class="errormsg">*</span> <br /> <form:select path="lb_lgdLocalBodyType"
																	style="width: 160px" id="localBodyType"
																	class="combofield"
																	onchange="getPanchayatListbyStateandlbTypeCode(this.value)">
																	<form:option value="0">
																		<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
																	</form:option>
																	<form:options items="${localBodyTypelist}"
																		itemLabel="localBodyTypeName"
																		itemValue="localBodyTypeCode" />
																</form:select></td>
															<td width="56%"><form:errors htmlEscape="true" path="lb_lgdLocalBodyType"
																	cssClass="errormsg"></form:errors>
														</td>
													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>

													<tr>

														<div id="tr_district1">

															<td width="14%">
																<td width="30%"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
																	${localGovtBodyForm.districtPanchayatName}&nbsp;<spring:message htmlEscape="true"
																		code="Label.OFLOCALBODY"></spring:message><br /> <form:select
																		path="lb_lgdPanchayatName" class="combofield"
																		id="wardUrbanLocalBody" style="width: 160px"
																		onchange="getSubdistrictListbyLBtypeforULB(this.value)">
																		<form:option value="0">
																			<spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
																		</form:option>
																	</form:select>&nbsp;<span> <form:errors htmlEscape="true"
																			path="localBodyNameEnglishList" class="errormsg"></form:errors>
																</span> &nbsp;&nbsp;<span class="errormsg"
																	id="wardUrbanLocalBodyy_error"> <spring:message htmlEscape="true"
																			code="error.blank.DISTRICTP"></spring:message> </span> <br />
																	<br /></td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
															</td>
														</div>
													</tr>
													<tr>
														<td><form:input htmlEscape="true" path="lgd_lbCategory"
																id="hiddenCheckBox" type="hidden" class="frmfield"
																value="${localGovtBodyForm.lgd_lbCategory}" />
														</td>



													</tr>


												</table>
											</div>
										</div></td> 

								</tr>--%>
								<tr>
									<td width="100%">
										<div class="frmpnlbg">
											<div class="frmtxt">
												<div class="frmhdtitle">
													<spring:message htmlEscape="true" code="Label.GENERALDETAILSOFWARD"></spring:message>
												</div>
												
											 	<form:hidden path="ward_code" id="hdnLbCode"
												value="${modifyUrbanWardCmd.ward_code}" htmlEscape="true" />
												<form:hidden path="lblc" id="lblc" value="${modifyUrbanWardCmd.lblc}" htmlEscape="true" />
												
												
												<%-- <form:hidden
												path="lbType" id="hdnLbTypeCode"
												value="${modifyUrbanWardCmd.lbType}"  htmlEscape="true" />  --%>
												
												<table width="100%" class="tbl_no_brdr">
													<tr>
														<td width="14%"></td>
														<td width="30%"><spring:message htmlEscape="true" code="Label.WARDNAME"></spring:message>
															<span class="errormsg">*</span> <br /> <form:input htmlEscape="true"
																path="ward_Name" id="wardname" maxlength="250" style="width: 200px"
																onfocus="validateOnFocus('wardname');helpMessage(this,'wardnameMsg');" 
																class="frmfield" onkeypress="return validateSpecialCharactersUpdateStandardCodeLocal(event);"
																onblur="UniqueWardValidationforManage(this.value,1);vlidateOnblur('wardname','1','15','m');hideHelp();"></form:input>
														   	<div id="UniqueWardNameError" style="color: red;"></div>
															<div id="wardnameMsg" style="display:none"><spring:message code="error.blank.WARDNAME" htmlEscape="true"/></div>
															<div class="errormsg" id="wardname_error1"><form:errors path="ward_Name" htmlEscape="true"/></div>  
															<div class="errormsg" id="wardname_error2" style="display: none" ></div>	
														</td>
														<td width="56%">
														</td>
													</tr>

													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td width="14%"></td>
														<td width="30%"><spring:message htmlEscape="true"
																code="Label.WARDNUMBER"></spring:message> <span
															class="errormsg">*</span> <br /> <form:input htmlEscape="true"
																path="ward_number" id="wardnumber" maxlength="10" style="width: 200px"
																class="frmfield"
																onfocus="validateOnFocus('wardnumber');helpMessage(this,'wardnumberMsg');"
																onblur="UniqueWardValidationforManage(this.value,2);vlidateOnblur('wardnumber','1','15','c');hideHelp();" onkeypress="return validateNumberWardNum(event);"></form:input>
																<form:input htmlEscape="true" path="ward_version" type="hidden" style="width: 200px" class="frmfield"></form:input>
															<div id="UniqueWardCodeError" style="color: red;"></div>		
															<div id="wardnumberMsg" style="display:none"><spring:message code="error.blank.WARDNUMBER" htmlEscape="true"/></div>
															<div class="errormsg" id="wardnumber_error1"><form:errors path="ward_number" htmlEscape="true"/></div>  
															<div class="errormsg" id="wardnumber_error2" style="display: none" ></div>	
																
														</td>
													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td width="14%"></td>
														<td width="30%"><spring:message htmlEscape="true"
																code="Label.WARDNAMEINLOCAL"></spring:message> <br /> <form:input htmlEscape="true"
																path="ward_NameLocal" id="wardnamdlocal" maxlength="250" style="width: 200px"
																class="frmfield" onfocus="validateOnFocus('wardnamdlocal');helpMessage(this,'wardnamdlocalMsg');"
																onblur="validateSpecialCharactersWardNLocal(this.value);"></form:input>
																
																 <div class="errormsg" id="wardnamdlocal_error1"><form:errors path="ward_NameLocal" htmlEscape="true"/></div>	
																 <div id="wardnamdlocalMsg" style="display:none"><spring:message code="error.blank.WARDNAMELOCAL" htmlEscape="true"/></div>
														 		<div class="errormsg" id="wardnamdlocal_error2" style="display: none"></div>
																														
														</td>
													</tr></table>
											</div>
										</div>
									</td>
								</tr>
						<%-- 		<!-- Govt. Order Details  -->
								<tr>
									<td width="100%">
										<div class="frmpnlbg">
											<div class="frmtxt">
												<div class="frmhdtitle">
													<spring:message htmlEscape="true" code="Label.GOVTORDERDETAILS"></spring:message>
												</div>

												<table width="100%" class="tbl_no_brdr">
													<tr>
														<td width="14%" rowspan="12">&nbsp;</td>
														<td width="86%"><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message><br />

															<form:input htmlEscape="true" path="lgd_LBorderNo" id="OrderNo" type="text"
																class="frmfield"
																onkeypress="validateNumericAlphaKeys();" /> <span
															class="error" id="OrderNo_error"></span>
														</td>
													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td><spring:message htmlEscape="true" code="Label.ORDERDATE"></spring:message><br />
															<form:input htmlEscape="true" path="lgd_LBorderDate" id="OrderDate"
																type="text" class="frmfield" style="width: 100px"
																onkeypress="validateNumeric();" /> <span class="error"
															id="OrderDate_error"></span>
														</td>

													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td><spring:message htmlEscape="true" code="Label.EFFECTIVEDATE"></spring:message><br />
															<form:input htmlEscape="true" id="EffectiveDate" path="lgd_LBeffectiveDate"
																type="text" class="frmfield" style="width: 100px"
																onkeypress="validateNumeric();" /> <span class="error"
															id="EffectiveDate_error"></span></td>
													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td><spring:message htmlEscape="true" code="Label.GAZPUBDATE"></spring:message><br />
															<form:input htmlEscape="true" id="GazPubDate" path="lgd_LBgazPubDate"
																type="text" class="frmfield" style="width: 100px"
																onkeypress="validateNumeric();" /> <span class="error"
															id="GazPubDate_error"></span></td>


													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td><spring:message htmlEscape="true" code="Label.UPLOADGOVTORDER"></spring:message><br />
															<form:input htmlEscape="true" id="filGovernmentOrder" path="lgd_LBfilePath"
																class="frmfield" type="file" size="30" /> <span
															class="error" id="filGovernmentOrder_error"></span>
														</td>
													</tr>
													
												</table>
											</div>
										</div></td>
								</tr> --%>

							<%-- 	<tr>
									<td width="100%">

										<div class="frmpnlbg">
											<div class="frmtxt">
												<div class="frmhdtitle">
													<spring:message htmlEscape="true" code="Label.GISNODES"></spring:message>
												</div>
												<table width="100%" class="tbl_no_brdr">
													<tr>
														<td width="14%" rowspan="2">&nbsp;</td>
														<td width="86%">
															<table width="100%" class="tbl_no_brdr">
																<tr>
																	<td width="159"><spring:message htmlEscape="true"
																			code="Label.GISNODES"></spring:message>
																	</td>
																	<td width="155" align="right"><label
																		id="lbllatitude"><spring:message htmlEscape="true"
																				code="Label.LATITUDE"></spring:message> </label>
																	</td>
																	<td width="75"><form:input htmlEscape="true" path="lgd_LBlatitude"
																			id="txtLgdLBlatitude" type="text" class="frmfield" />
																	</td>
																	<td width="150" align="right"><label
																		id="lbllongitude"><spring:message htmlEscape="true"
																				code="Label.LONGITUDE"></spring:message> </label>
																	</td>
																	<td width="75"><form:input htmlEscape="true" path="lgd_LBlongitude"
																			id="txtLgdLBlongitude" type="text" class="frmfield" />
																	</td>
																	<td>&nbsp;</td>
																	<td width="150" align="right"><label> <input
																			type="button" name="Submit3"
																			value="<spring:message htmlEscape="true" code="Button.ADDNODES"></spring:message>"
																			onclick="addgisnodes()" /> </label>
																	</td>
																</tr>
															</table>

															<table>
																<tr>
																	<td width="1"></td>
																	<td width="50" align="center"></td>
																	<td width="50"></td>
																	<td width="50" align="center"></td>
																	<td width="45"></td>
																	<td><div id="addgisnodes"></div>
																	</td>
																</tr>
															</table>

															<div class="errormsg"></div></td>
													</tr>
													<tr>
														<td>

															<table width="468" class="tbl_no_brdr">
																<tr>
																	<td><spring:message htmlEscape="true" code="Label.MAPOFLOCALBODY"></spring:message><br />
																		<form:input htmlEscape="true" id="txtLBfileMapUpLoad"
																			path="lgd_LBfileMapUpLoad" class="frmfield"
																			type="file" size="25" /> <span><form:errors htmlEscape="true"
																				path="lgd_LBfileMapUpLoad" class="errormsg"></form:errors>
																	</span>
																	</td>
																</tr>
															</table></td>
													</tr>
												</table>
											</div>

										</div>
									</td>

								</tr> --%>
								<tr>
									<td width="100%">
										<div class="frmpnlbg">
											<div class="frmtxt" id="divStatelvl">
												<div class="frmhdtitle">
													<spring:message htmlEscape="true" code="Label.CONTRIBUTINGLANDREGION"></spring:message>
												</div>
												<table width="100%" class="tbl_no_brdr">
													<tr>
														<td width="14%"></td>
														<td width="86%">
															<div id="divSpecificState">
																<table width="570" class="tbl_no_brdr">

																	<tr>
																		<td>&nbsp;</td>
																		<td>&nbsp;</td>
																	</tr>
																	<c:if test="${! empty landRegionSubDistrictList}">
																	<tr>
																		<td width="235"><b><spring:message htmlEscape="true"
																					code="Label.AVAILSUBDISTRICTLIST"></spring:message>
																		</b><br /> <form:select
																				path="lgd_LBSubDistrictSourceLatDCAUmapped"
																				class="frmtxtarea"
																				id="ddLgdWardSubDistrictUListSource"
																				items="${landRegionSubDistrictList}"
																				itemLabel="landRegionName"
																				itemValue="landRegionCode"
																				style="height: 110px; width: 233px" multiple="true">

																			</form:select><br /> <br />
																		</td>

																	<!-- 	<td width="100" align="center"><input
																			type="button" value=" Whole &gt;&gt;"
																			style="width: 80px"
																			onclick="addItem('ddLgdWardSubDistrictUListDest','ddLgdWardSubDistrictUListSource','FULL',true);" />
																			<br /> <input type="button" id="btnremoveOneULB"
																			name="Submit4" value="Back &lt;"
																			onclick="removeItem('ddLgdWardSubDistrictUListDest','ddLgdWardSubDistrictUListSource',true)" /><br />
																			<input type="button" value=" Reset &lt;&lt;"
																			style="width: 70px"
																			onclick="removeAll('ddLgdWardSubDistrictUListDest', 'ddLgdWardSubDistrictUListSource', true)" /><br />
																			<input type="button" value="Part &gt;&gt;"
																			style="width: 70px"
																			onclick="addItem('ddLgdWardSubDistrictUListDest','ddLgdWardSubDistrictUListSource', 'PART',true);" />
																		</td> -->

																		<%-- <td><b><spring:message htmlEscape="true"
																					code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message>
																		</b> <br /> <form:select name="select6"
																				id="ddLgdWardSubDistrictUListDest" size="1"
																				multiple="multiple" path="lgd_LBSubDistrictList"
																				class="frmtxtarea"
																				style="height: 110px; width: 242px">
																			</form:select><br /> --%> <%-- &nbsp;&nbsp;&nbsp; <input type="button"
																			value="<spring:message htmlEscape="true" code="Button.GETVILLAGEL"/>"
																			style="width: 200px"
																			onclick="selectSubdistrictAtDCA();" /> --%><!-- </td> -->
																	</tr>
																	</c:if>
																</table>
															</div></td>
													</tr>
												</table>
											</div>
										</div>
									</td>
								</tr>

							</table>


						</div>
						<div class="btnpnl">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="16%" rowspan="2">&nbsp;</td>
									<td width="84%" align="center"><label> <input
											type="submit" name="Save" id="btnSave"
											value="<spring:message htmlEscape="true" code="Button.SAVE"></spring:message>" onclick="return validateUrbanWardModifyAll();"/>
									</label><label> <input type="button" class="btn" name="Cancel"
											value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>"
											id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
										<!-- </div> -->
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>

</body>
</html>