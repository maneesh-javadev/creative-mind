<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/local_govt_setup.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="js/common.js"></script>
<link href="css/lgd_css.css" rel="stylesheet" type="text/css" />
<script src="js/lgd_js.js"></script>
<script src="js/jquery.js"></script>
<script src="js/govtorder.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js"></script>
</head>

<body onload="onloadSelect()">
	<div id="frmcontent">
		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message htmlEscape="true"  code="Label.CREATENEWLOCALGOVTBODY"></spring:message>
					</td>
					<td width="50" align="right"><a href="#" class="frmhelp">Help</a>
					</td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="local_gov_setup.htm?mode=${mode}" name="lgsform"
				method="post" commandName="lGSetupForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="local_gov_setup.htm?mode=${mode}"/>"/>
				<div class="frmpnlbg">
					<div class="frmtxt">
						<table width="100%" class="tbl_no_brdr">

							<tr>
								<td class="tblclear">
									<div id="tab_panel">
										<ul>
											<li><a href="#" class="current"><spring:message htmlEscape="true" code="Label.LGTYPE"></spring:message></a> 
											</li>
											<li><a href="#"><spring:message htmlEscape="true" code="Label.LBTS"></a> 
											</li>
											<li><a href="#"><spring:message htmlEscape="true" code="Label.subtypesetup"></a> 
											</li>
										</ul>
										<div class="clear"></div>
									</div>
									<div class="clear"></div>
									<div class="greybrdr">
									
										<div id="tab1">
											<table width="100%" class="tbl_no_brdr">
												<tr>
                 								<table width="100%" class="tbl_no_brdr">
																		<tr>
																			<td class="tblclear">
																				<table width="100%" class="tbl_no_brdr">
																				<tr>
							                                                     <td height="25">&nbsp;</td>
						                                                         </tr>
																					<tr>
																						<td><strong><spring:message htmlEscape="true" 
																									code="Label.OPERATIONAL"></spring:message> </strong>
																						</td>
																						<td><strong><spring:message htmlEscape="true" 
																									code="Label.LGT"></spring:message> </strong>
																						</td>
																						<td align="center"><strong><spring:message htmlEscape="true" 
																									code="Label.NIE"></spring:message> </strong>
																						</td>
																						<td align="center"><strong><spring:message htmlEscape="true" 
																									code="Label.NILE"></spring:message> </strong>
																						</td>
																					</tr>
																					<c:if test="${! empty LgTypeDetails}">
																						<c:set var="catval" value="" />

																						<c:forEach var="LgTypeDetails"
																							items="${LgTypeDetails}">
																							<tr>
																								<td><strong
																									id="categoryheading<c:out value="${LgTypeDetails.localBodyTypeCode}" escapeXml="true"></c:out>"></strong>
																								</td>
																							</tr>
																							<tr>
																								<td><label> 
																										<input type="checkbox" id="chk<c:out value='${LgTypeDetails.localBodyTypeCode}'/>" name="check"
																										       value="<c:out value='${LgTypeDetails.localBodyTypeName}' escapeXml='true'/>"
																											   onclick="toggle('txtNE','txtNL',<c:out value='${LgTypeDetails.localBodyTypeCode}'/>,<c:out value='${size}'/>)" />
																										<input type="checkbox" id="chkCategory<c:out value='${LgTypeDetails.localBodyTypeCode}'/>" style="visibility: hidden"
																										       value="<c:out value='${LgTypeDetails.category}' escapeXml='true'/>####<c:out value='${LgTypeDetails.localBodyTypeName}' escapeXml='true'/>####<c:out value='${LgTypeDetails.localBodyTypeCode}' escapeXml='true'/>"></input>
																										<input type="hidden" id='maxrows' value="<c:out value='${size}' escapeXml='true'></c:out>"/> 
																									</label>
																								</td>
																								<td>
																									<c:out value="${LgTypeDetails.localBodyTypeName}" escapeXml='true'></c:out>
																								</td>
																								<td align="center">
																									<input type="text" name="nomenEnglish" value="<c:out value='${LgTypeDetails.nomenEnglish}' escapeXml='true'></c:out>" 
																										   id="txtNE<c:out value='${LgTypeDetails.localBodyTypeCode}' escapeXml='false'></c:out>"
																									       class="disabled" style="width: 150px" disabled />
																									<label id="lblNE<c:out value='${LgTypeDetails.localBodyTypeCode}'></c:out>" style="color: red"></label>
																								</td>
																								<td align="center">
																									<input type="text" name="nomenLocal" value="<c:out value='${LgTypeDetails.nomenLocal}' escapeXml='true'></c:out>" 
																									       id="txtNL<c:out value='${LgTypeDetails.localBodyTypeCode}' escapeXml='false'></c:out>"
																									       class="disabled" style="width: 150px" disabled />
																									<label id="lblNL<c:out value='${LgTypeDetails.localBodyTypeCode}'></c:out>"	style="color: red"></label></td>

																							</tr>
																						</c:forEach>
																					</c:if>


																					<tr>
																						<td colspan="4">
																						   <div class="btnpnl">
																								<input type="hidden" name="codes" id="codes"
																									class="frmfield" /> <input type="hidden"
																									name="names" id="names" class="frmfield" />
																							</div>
																						</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																	</table>
																
																<tr>

																	<td id="input_styl"><input type="button"
																		value="Next .. >>" onclick="validatepage1()" /></td>
																</tr>
											</table>
										</div>
										////
										<div class="clear"></div>
			<div id="tab2" style="display: none;">
			<table class="tbl_no_brdr">
				<tr>
					<td valign="top" class="tblclear">
						<div class="clear"></div>
						<div id="frmcontent">
							<div class="frmhd">
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td><spring:message htmlEscape="true"  code="Label.LGTS"></spring:message></td> 
										<td align="right"><a href="#" class="frmhelp">Help</a></td>
									</tr>
								</table>
							</div>
							 <div class="frmpnlbg">
								<div class="frmtxt">
											<table width="100%" class="tbl_no_brdr">
												<tr style="visibility:hidden">
													<td width="14%" rowspan="4">&nbsp;</td>
													<td width="86%"><spring:message htmlEscape="true"  code="Label.CATEGORY"></spring:message><br /> <label> <select
															id ="categoryType" name="categoryType" class="frmsfield" style="width: 150px;" onchange="getcategory()">
																<option><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message></option>
																<option><spring:message htmlEscape="true"  code="Label.RURALG"></spring:message></option>
																<option><spring:message htmlEscape="true"  code="Label.Traditional"></spring:message></option>
														</select> </label>
														<div style="height: 15px; padding-top: 3px;"
															class="errormsg"></div></td>
												</tr>
												<tr>
													<td><spring:message htmlEscape="true"  code="Label.LGTYPE"></spring:message> <select id="lgt1"></Br></Br>
														<option value=""><form:option selected="selected" value="" label="--select--" /></option>
														</select>
													<input type="button" name="btn3" value="Add Child" onclick="changeTab3(dynamicdiv3)" />
													<input type="button" name="Save" value="Save" onclick="populate()"/>
														<div style="height: 15px; padding-top: 3px;"
															class="errormsg"></div>
													<div id="dynamicdiv3"/>													
													</td>
													<td>
														<input type="hidden" name="childOrder" id="childOrder" class="frmfield"/>
													</td>
												</tr>


											</table>
											<div style="height: 15px; padding-top: 3px;" class="errormsg"></div>
										</div>
									
									<div style="padding: 10px 0px 10px 0px" align="center">
										<label>                         
										</label>
									</div>
								
							</div>
						</div></td>
				</tr>

				<tr>
					<td><input type="button" value="<< .. Previous"
						onclick="previous(2)" /> <input type="button" value="Next .. >>" onclick="showsubmit(true);" />
					</td>
				</tr>

			</table>

		</div>
			//
			<div class="clear"></div>
			<div id="tab3" style="display: none;">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td valign="top" style="padding: 0px">
						<div id="frmcontent">
							<div class="frmhd">
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td><spring:message htmlEscape="true"  code="Label.LGTS"></spring:message></td> 
										<td align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="<spring:message htmlEscape="true"  code="Label.LGTS"></spring:message>"></spring:message></a></td>
									</tr>
								</table>
							</div>
							<div class="frmpnlbg">
									<div class="frmtxt">
											<table width="100%" class="tbl_no_brdr">
												<tr>
													<td width="14%" rowspan="4">
														<div id="dynamicDiv4"></div>
													</td>
													<td width="86%">	
														<div id="dynamicDiv4_Addons"
															 style="background: #E1E1E1; padding: 3px; margin: 0px 10px 0px 10px;
								                             width:100%;visibility:hidden">
								                        </div>
													</td>
												</tr>
												<tr style="visibility:hidden">
													<td width="14%" rowspan="4">&nbsp;</td>
													<td width="86%"><input type="hidden" id="heading4" value='<spring:message htmlEscape="true"  code="Label.LGT"></spring:message>'></input>
													<spring:message htmlEscape="true"  code="Label.LGT"></spring:message>
														<form:select id="lgt2" name="" path="">
														<form:option selected="selected" value="" label="--select--" />
														</form:select>&nbsp;&nbsp;
													</td>
												</tr>
												<tr style="visibility:hidden">
													<td><label id="lbl_E"><spring:message htmlEscape="true"  code="Label.LGSTIE"></spring:message> </label>
														 <input id="dtxtE0" name="subTypeName" 
															type="text" class="frmfield" style="width: 220px" />
														&nbsp;&nbsp;<span style="height: 15px; padding-top: 3px;"
															class="errormsg"></span>
														<label id="lbl_L"> <spring:message htmlEscape="true"  code="Label.LGSTILL"></spring:message> </label>
														<input id="dtxtL0" name="subTypeLocal"
															type="text" class="frmfield" style="width: 220px" />
														<span style="height: 15px; padding-top: 3px;" 
															class="errormsg"></span>
														<label>
															<input type="button" name="addmore" id="addmore" value="Add More" onclick="changeTab4(dynamicDiv4)" />
														</label>
													</td>
												</tr>
												<tr style="visibility:hidden">
													<td>
														<input type="button" value="    Save    "  onclick="persist4()"/>
														<div id="div4" style="width:84%" align="right">
															<input type="text" name="subTyp" id="tab4txt"/>
														
														</div>
													</td>
												</tr>
											</table>
											<table>
												<tr>
													<c:forEach var="subTypeDetails" items="${subTypeDetails}">
														<td><input type="hidden" name="subTypE" id="subTypcd" value="<c:out value='${subTypeDetails.childOrder}' escapeXml='true'></c:out>"/></td>
														<td><input type="hidden" name="subTypE" id="subTypE" value="<c:out value='${subTypeDetails.subTypeName}' escapeXml='true'></c:out>"/></td></br></br> 
														<td><input type="hidden" name="subTypL" id="subTypL" value="<c:out value='${subTypeDetails.subTypeLocal}' escapeXml='true'></c:out>" /></td>
													</c:forEach>
												</tr>
											</table>
											<div  class="errormsg"></div>
										</div>
								</div>
						</div>
					</td>
				</tr>
			</table>
			<input type="button" value="<< .. Previous" onclick="showsubmit(false)" />
			
		</div>
		////
		<div class="clear"></div>
		<div id="tab5" style="display: none; visisbility: hidden;">
			<table width="100%" class="tbl_no_brdr" style="visisbility: hidden;">
				<tr>
					<td valign="top" class="tblclear">
						<div class="clear"></div>

						<div class="frmhd">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td><spring:message htmlEscape="true"  code="Label.DH"></spring:message></td>
									<td align="right"><a href="#" class="frmhelp">Help</a></td>
								</tr>
							</table>
						</div> 
						<div class="frmpnlbg">
								<div class="frmtxt">
									<table width="100%" class="tbl_no_brdr">
										<tr>
											<td width="14%" rowspan="4">&nbsp;</td>
											<td width="86%"><spring:message htmlEscape="true"  code="Label.LGT"></spring:message>
											
											<form:select id="lgt3" path="" onchange="fillofficials()">
											<form:option selected="selected" value="" label="--select--"></form:option>
											</form:select>
											
											<form:select id="lgt" path="" style = "visibility : hidden ; width=0px">
											<form:option value=""> <form:option selected="selected" value="" label="--select--" /></form:option>
											</form:select>
											<select id="lgt0" style = "visibility : hidden">
											<option value="" ><form:option selected="selected" value="" label="--select--" /></option>
											</select>		   
											</td>

										</tr>
										<tr>
											<td><spring:message htmlEscape="true"  code="Label.OT"></spring:message>
												<table width="242" class="tbl_no_brdr">
													<tr>
														<td width="110" class="tblclear"><label>
																<form:select  id="officialType" name="officialType" path="officialType" class="frmsfield" htmlEscape="true"
																style="width: 220px">
																	<form:option value="" > <form:option selected="selected" value="" label="--select--" /></form:option>
																	<form:option value="1">
																		<spring:message htmlEscape="true"  code="Label.EPR"></spring:message>
																	</form:option>
																	<form:option value="0">
																		<spring:message htmlEscape="true"  code="Label.POFFICIAL"></spring:message>
																	</form:option>
															</form:select>	<form:select  id="officialType1" name="officialType" path="officialType" class="frmsfield" htmlEscape="true"
																style="width: 220px; visibility:hidden">
																	<form:option value="" ><form:option selected="selected" value="" label="--select--" /></form:option>
																	<form:option value="1">
																		<spring:message htmlEscape="true"  code="Label.EPR"></spring:message>
																	</form:option>
																	<form:option value="0">
																		<spring:message htmlEscape="true"  code="Label.POFFICIAL"></spring:message>
																	</form:option>
															</form:select> 
															</label>
														</td>

													</tr>

													<tr>
														<td align="left"><spring:message htmlEscape="true"  code="Label.TD"></spring:message>
															<table width="242"  class="tbl_no_brdr">
															<tr>
																<td align="left"><label> 
																<input type="text" id="desgName0" name="desgName0" class="frmfield" 
																		style="width: 150px" />
																<input type="button" name="Submit32" onclick="changeIt(dynamicDiv)"
																		value=<spring:message htmlEscape="true"  code="Button.ACHILD"></spring:message> />
																		<div id="dynamicDiv">&nbsp;</div> </label></td>
															</tr>
															<tr>
																<td>
																	<input type="button" onclick="persist2()"  value="    Save    " />
																	<input type="hidden" name="hierarchy" id="hierarchy" />
																</td>
															</tr>
														</table>
														</td>
													</tr>
													
														
												</table>
											</td>
										</tr>
										
									</table>
								</div>
							</div>

						
					</td>
				</tr>
				<tr>
										<td id="input_styl"><input type="button"
												value="<< .. Previous" onclick="previous(2)" /> <input
												type="button" value="Next .. >>" onclick="next(2);" />
											</td>
										</tr>
			</table>


		</div>
	<input type="button" onclick="submit4()" name="Submit" id="Submit" value="Submit" align="right" style="visibility:hidden"/>							
									<div class="clear"></div>
									</div></td>
							</tr>
						</table>
					</div>
				</div>
			</form:form>
			<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
		</div>
	</div>
</body>
</html>
