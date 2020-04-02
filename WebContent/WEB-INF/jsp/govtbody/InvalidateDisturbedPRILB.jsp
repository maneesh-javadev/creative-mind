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
<title>Invalidate PRI local Body <!-- dell --> <%-- <spring:message htmlEscape="true"  code="Label.INVALIDATEPRILOCALBODY"></spring:message> --%>
</title>
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
<script src="js/govtorder.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>
	
</script>
<script src="js/local_body.js"></script>
<script src="js/lgd_localbody.js"></script>
<script src="js/localbody_validation.js"></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrLocalGovtBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>


<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
</script>

<script type="text/javascript" src="js/invalidatelocalbody.js"
	charset="utf-8"></script>

<script type="text/javascript" src="js/invalidatelBdisturbed.js"
	charset="utf-8"></script>	

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'>
	dwr.engine.setActiveReverseAjax(true);
</script>

<script>
$(document).ready(function() {
	HideShowDisturblb();
	}); 
	
callActionUrl=function(){
	document.forms['invalidateLocalBodyforPRI'].action = "invalidateLocalBodyforPRI.htm?<csrf:token uri='"invalidateLocalBodyforPRI.htm'/>";
	document.forms['invalidateLocalBodyforPRI'].method = "POST";
    document.forms['invalidateLocalBodyforPRI'].submit();
};

</script>
</head>
<body
	onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);">

	<div id="frmcontent">
		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr"> 
				<tr>


					<td><spring:message htmlEscape="true"  code="Label.INVALIDATEPLB"></spring:message> <!-- dell --> <%-- <spring:message htmlEscape="true"  code="Label.INVALIDATEPRILOCALBODY"></spring:message> --%>
					</td>
					<%--//these links are not working <td width="50" align="right"><a href="#" class="frmhelp"><spring:message
								htmlEscape="true" code="Button.HELP"></spring:message> </a></td> --%>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="invalidateLocalBodyforPRI.htm" id="invalidateLocalBodyforPRI" commandName="localGovtBodyForm" method="POST" enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="invalidateLocalBodyforPRI"/>" htmlEscape="true" />
				<input type="hidden" name="flag1" id="flag1" value="0" htmlEscape="true"/>
				<input type="hidden" name="Tier" id="Tier" value="<c:out value='${Tier}' escapeXml='true'></c:out>" htmlEscape="true"/>
				<input type="hidden" name="lbselectedtype" id="lbselectedtype" value="<c:out value='${lbselectedtype}' escapeXml='true'></c:out>" htmlEscape="true" />
				<input type="hidden" name="Tiertype" id="Tiertype" value="<c:out value='${Tiertype}' escapeXml='true'></c:out>" htmlEscape="true"/>
				<input type="hidden" name="invalidatedlbcode" id="invalidatedlbcode" value="<c:out value='${invalidatedlbcode}' escapeXml='true'></c:out>" htmlEscape="true"/>
					
					
				<div id='divLgdLBType' class="frmpnlbg">
					<!--'district' id name change  -->
					<div class="frmtxt">
						<table width="80%" class="tbl_no_brdr">
							<tr>
								<td width="14%" rowspan="8">&nbsp;</td>
								<td width="50%">
									<spring:message htmlEscape="true" code="Label.LGT"></spring:message><br /> 
									<form:select path="lgd_LBTypeName" id="ddLgdLBType" onchange="HideShowDivs(this.value);" class="combofield" style="width: 200px" htmlEscape="true">
										<!--tierSetupCode id Changed  -->
										<c:forEach var="localBodyTypelist" varStatus="var" items="${localBodyTypelist}">
											<form:option id="typeCode" value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.level}:${localBodyTypelist.nomenclatureEnglish}" htmlEscape="true">
												<c:out value="${localBodyTypelist.nomenclatureEnglish}" escapeXml="true"></c:out>
											</form:option>
										</c:forEach>
									</form:select>&nbsp;&nbsp;
									<div class="errormsg" id="ddLgdLBType_error1"><form:errors path="lgd_LBTypeName" htmlEscape="true" /></div>
									<div class="errormsg" id="ddLgdLBType_error2" style="display: none"></div>
								</td>
								<td class="lblBold" width="36%"><spring:message code="Label.DISTURBEDSTATE" htmlEscape="true" ></spring:message> <br /> 
								<img src="images/red_flg.png" width="13" height="9" /></td>
							</tr>
							<tr>
										<td width="50%">&nbsp;</td>
										<td width="36%"><c:out value="${lbdisturbreason}" escapeXml="true"></c:out> </td>
							</tr>
							<tr>
								<td width="100%" colspan="2">
									<div id="divLgdlistSubTypeCode" class="frmpnlbg" style="visibility: hidden; display: none;">
										<table width="100%">
											<tr>
												<td>
													<spring:message htmlEscape="true" code="Label.AVAILSUBTYPE"></spring:message><br /> 
													<form:select path="localbodySubtype" class="combofield" id="ddlgdLBSubTypeCode" style="width: 200px" htmlEscape="true"></form:select><br />
												</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2">
									<div id="divLgdSelIntermediateP" style="visibility: hidden; display: none;">
										<table width="100%">
											<tr>
												<td>
													<spring:message htmlEscape="true" code="Label.DISTRICTPANCHYATNME"></spring:message>	
                                                    <br /> 
                                                    <form:select path="lgd_LBDistrictAtInter" class="combofield" id="ddlgdLBDistAtInter" style="width: 200px" onchange="getlistofIntermediatePanchayat(this.value)" htmlEscape="true">
														<form:option id="typeCode" value="${parentlbcode}" htmlEscape="true"><c:out value="${parentlb}" escapeXml="true"></c:out></form:option>
													</form:select> &nbsp;
													
													<div class="errormsg" id="ddlgdLBDistAtInter_error1">
														<form:errors path="lgd_LBDistrictAtInter" htmlEscape="true" />
													</div>
													<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none"></div>
												</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div id="divLgdVillageP" style="visibility: hidden; display: none;">
										<table width="100%">
											<tr>
												<td>
													 <spring:message htmlEscape="true" code="Label.DISTRICTPANCHYATNME"></spring:message><br /> 
													 <form:select path="lgd_LBDistrictAtVillage" class="combofield" id="ddlgdLBDistrictAtVillage" onchange="getWorkOnVillagePanchayatforDistrictP(this.value);" style="width: 200px" htmlEscape="true">
																<form:option id="typeCode" value="${grandparentLBcode}" htmlEscape="true"><c:out value="${grandparentLB}" escapeXml="true"></c:out></form:option>
													 </form:select>
													&nbsp;
													
													<div class="errormsg" id="ddlgdLBDistrictAtVillage_error1">
														<form:errors path="lgd_LBDistrictAtVillage" htmlEscape="true" />
													</div>
													<div class="errormsg" id="ddlgdLBDistrictAtVillage_error2"style="display: none"></div>
												</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td>
													<spring:message htmlEscape="true" code="Label.INTERPANCHYATNME"></spring:message><br/> 
													<form:select path="lgd_LBIntermediateAtVillage" class="combofield" id="ddlgdLBInterAtVillage" style="width: 200px" onchange="getlistofgp(this.value);" htmlEscape="true">
														<form:option id="typeCode" value="${parentlbcode}" htmlEscape="true"><c:out value="${parentlb}" escapeXml="true"></c:out></form:option>
													</form:select> &nbsp;
													<div class="errormsg" id="ddlgdLBInterAtVillage_error1">
														<form:errors path="lgd_LBIntermediateAtVillage" htmlEscape="true" />
													</div>
													<div class="errormsg" id="ddlgdLBInterAtVillage_error2" style="display: none"></div>
												</td>
												<td>&nbsp;</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
							
							
							
							
							
							
							
						</table>
					</div>

				</div>


				<div id="Districtlocalbody" style="visibility: hidden; display: none;">
					<div class="frmpnlbg" id="divLgdLBType1">
						<div class="frmtxt">

							<div class="frmhdtitle">
								<!-- dell -->
								<%-- <spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message> --%>


								<c:if test="${Tiertype eq 2}">
                                  <spring:message htmlEscape="true"  code="Label.selectIPdelete"></spring:message>
                                     </c:if>
								<c:if test="${Tiertype eq 0}"> 
                          <spring:message htmlEscape="true"  code="Label.selectDPdelete"></spring:message>
                                      </c:if>


							</div>
							<table width="60%" class="tbl_no_brdr">

								<tr><td></td>
													<td><label><spring:message htmlEscape="true"  code="Label.DISTRICTPANCHYATNME"></spring:message></label><br />

										<form:select path="districtpanlbid" class="combofield"
											id="lblist1" style="width: 200px"
											onchange="removeData();setflag1()" htmlEscape="true">
															ddSourceLocalBody id name changed 
															<form:option value="0" htmlEscape="true">
												<esapi:encodeForHTMLAttribute><spring:message htmlEscape="true"
													code="Label.SELECTLOCALBODY"></spring:message></esapi:encodeForHTMLAttribute>
											</form:option>
											<form:options items="${districtPanchayatList}"
												itemLabel="localBodyNameEnglish" itemValue="localBodyCode" htmlEscape="true"/>
										</form:select> &nbsp;
										
										<div class="errormsg" id="ddlgdLBDistAtInter_error1">
											<form:errors path="lgd_LBDistrictAtInter" htmlEscape="true" />
										</div>
										<div class="errormsg" id="ddlgdLBDistAtInter_error2"
											style="display: none"></div>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>

				<div id="IPlocalbody" style="visibility: hidden; display: none;">
					<div class="frmpnlbg" id="divLgdLBType2">
						<div class="frmtxt">

							

							<table width="60%" class="tbl_no_brdr">

								<tr>
									<td></td>
									<td><c:if test="${Tier eq 3}">
											<label><spring:message code="Label.INTERPANCHYATNME"></spring:message>
											</label>
										</c:if> <c:if test="${Tier eq 2}">
											<label><spring:message code="Label.VILLAGEPANCHYATNME"></spring:message>
											</label>
										</c:if><br />
										<%--  <c:set var="flag1" value="2"/>	 --%> <form:select
											path="intermediatepanlbid" class="combofield" id="lblist2"
											style="width: 200px" onchange="removeData();setflag2();"  htmlEscape="true">
											
											<%-- <form:option id="typeCode"
                                                value="${invalidatedlbcode}">${invalidatedlb}</form:option> --%>
											
											<%-- <form:option value="0">
																<spring:message htmlEscape="true"  code="Label.SELECTINTERMEDIATELOCBODY"></spring:message>
															</form:option> --%>
										</form:select> &nbsp;
										
										<div class="errormsg" id="ddlgdLBDistAtInter_error1">
											<form:errors path="lgd_LBDistrictAtInter" htmlEscape="true" />
										</div>
										<div class="errormsg" id="ddlgdLBDistAtInter_error2"
											style="display: none"></div>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>








				<div id="gplocalbody" style="visibility: hidden; display: none;">
					<div class="frmpnlbg" id="divLgdLBType3">
						<div class="frmtxt">

						

							<table width="60%" class="tbl_no_brdr">

								<tr>
									<td></td>
									<td><label>
									
 <spring:message
											htmlEscape="true" code="Label.VILLAGEPANCHYATNME"></spring:message>	
									<br /> <%--  <c:set var="flag3" value="3"/>		 --%>
										<form:select path="grampanlbid" class="combofield"
											id="lblist3" style="width: 200px"
											onchange="removeData();setflag3();" htmlEscape="true">
											<form:option value="0" htmlEscape="true">
												  <esapi:encodeForHTMLAttribute><spring:message htmlEscape="true"
													code="Label.SELECTINTERMEDIATELOCBODY"></spring:message></esapi:encodeForHTMLAttribute>
											</form:option>
										</form:select> &nbsp;
										
										<div class="errormsg" id="ddlgdLBDistAtInter_error1">
											<form:errors path="lgd_LBDistrictAtInter" htmlEscape="true" />
										</div>
										<div class="errormsg" id="ddlgdLBDistAtInter_error2"
											style="display: none"></div>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>



				<div class="frmpnlbg" id="divLgdLBType4">
					<div class="frmtxt">
						<table width="60%" class="tbl_no_brdr">
							<tbody>
								<tr>
									<td width="14%">&nbsp;</td>
									<td width="86%"><table width="80%" class="tbl_no_brdr">
											<tbody>
												<tr>
													<td width="100%"><label> <spring:message
																htmlEscape="true" code="Label.SELECTLBCOVERD"></spring:message>&nbsp;
													</label>
														<table width="104" height="21" class="tbl_no_brdr">
															<tbody>
																<tr>
																	<td width="20" class="tblclear"><label> <form:radiobutton
																				id="show_dp" path="lboption" htmlEscape="true"
																				onclick="getlblist();removeListItem();" value="true"
																				name="rdoDistrictDelete" /> </label>
																	</td>

																	<td width="37" class="tblclear">YES</td>

																	<td width="20" class="tblclear"><label> <form:radiobutton
																				id="hide_dp" path="lboption" htmlEscape="true"
																				onclick="hidelblist()" value="true"
																				name="rdoDistrictDelete" /> </label>
																	</td>
																	<td width="27" class="tblclear">No</td>
																</tr>
															</tbody>
														</table></td>
												</tr>
											</tbody>
										</table>
										<div class="errormsg"></div></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>



				<div id="IPMerge1" style="visibility: hidden; display: none;">
					<div class="frmpnlbg" id="divLgdLBType5">
						<div class="frmpnlbrdr">

							<table width="80%" class="tbl_no_brdr">

								<tr>
									<td></td>
									<tr>
										<td width="86%"><label> <spring:message
													htmlEscape="true" code="Label.SELECTDP"></spring:message> </label><span
											class="errormsg">*</span><br /> <label> <form:select
													path="contSubDistrict" class="combofield"
													id="mergelocalbody" style="width: 150px">
													<form:option value="0" htmlEscape="true">
														<esapi:encodeForHTMLAttribute><spring:message htmlEscape="true"
															code="Label.DISTRICTPANCHAYAT"></spring:message></esapi:encodeForHTMLAttribute>
													</form:option>
													<form:options items="${districtPanchayatList}"
														itemLabel="localBodyNameEnglish" itemValue="localBodyCode" htmlEscape="true" />
												</form:select> </label>
										</td>
									</tr>
								</tr>
							</table>
						</div>
					</div>
				</div>

				<div id="IPMerge2" style="visibility: hidden; display: none;">
					<div class="frmpnlbg" id="divLgdLBType6">
						<div class="frmpnlbrdr">

							<table width="60%" class="tbl_no_brdr">

								<tr>
									<td></td>
									<tr>
										<td width="86%"><c:if test="${Tier eq 2}">
												<label> <spring:message htmlEscape="true"
														code="Label.SELECTGP"></spring:message> </label>
												<span class="errormsg">*</span>
												<br />
												<label>
											</c:if> <c:if test="${Tier eq 3}">
												<label> <spring:message htmlEscape="true"
														code="Label.SELECTIP"></spring:message> </label>
												<span class="errormsg">*</span>
												<br />
												<label>
											</c:if> <form:select path="contSubDistrict" class="combofield"
												id="mergelocalbody2" style="width: 150px" htmlEscape="true">
												<form:option value="0" htmlEscape="true">
													<esapi:encodeForHTMLAttribute><spring:message htmlEscape="true"
														code="Label.DISTRICTPANCHAYAT"></spring:message></esapi:encodeForHTMLAttribute>
												</form:option>

											</form:select> </label>
										</td>
									</tr>
								</tr>
							</table>
						</div>
					</div>
				</div>


				<div id="IPMerge3" style="visibility: hidden; display: none;">
					<div class="frmpnlbg" id="divLgdLBType6">
						<div class="frmpnlbrdr">

							<table width="60%" class="tbl_no_brdr">

								<tr>
									<td></td>
									<tr>
										<td width="86%"><label> <spring:message
													htmlEscape="true" code="Label.SELECTGP"></spring:message> </label><span
											class="errormsg">*</span><br /> <label> <form:select
													path="contSubDistrict" class="combofield"
													id="mergelocalbody3" style="width: 150px">
													<form:option value="0" htmlEscape="true">
														<esapi:encodeForHTMLAttribute><spring:message htmlEscape="true"
															code="Label.DISTRICTPANCHAYAT"></spring:message></esapi:encodeForHTMLAttribute>
													</form:option>

												</form:select> </label>
										</td>
									</tr>
								</tr>
							</table>
						</div>
					</div>
				</div>

				<div id="Districtlistlocalbodylist"
					style="visibility: hidden; display: none;">
					<div class="frmpnlbg" id="divLgdLBTypelist">
						<div class="frmpnlbrdr">
							<table width="60%" class="tbl_no_brdr">


								<tr>
									<td width="14%" rowspan="7">&nbsp;</td>
									<td width="86%">&nbsp;</td>
								</tr>
								<tr>
									<td width="86%">
										<table width="664" class="tbl_no_brdr">
											<tr>

											</tr>

											<tr>
												<td><label id="selectEntity"></label> <%-- <label><spring:message htmlEscape="true"  code="App.SELECTINTERMEDIATEPANCHAYAT"></spring:message>Select Child Local Bodies </label> --%>

												</td>
												<td align="center">&nbsp;</td>
												<td><label id="selectedEntity"></label> <%-- <label>Selected Local Bodies <spring:message htmlEscape="true"  code="Label.SELDISTRICTPANCHYAT"></spring:message></label><span class="errormsg">*</span></td> --%>
											</tr>
											<tr>
												<td width="246" valign="top"><form:select htmlEscape="true"
														name="select9" size="1" id="availablelb"
														path="lgd_LBDistrictAtVillage" multiple="multiple"
														class="frmtxtarea" style="height: 80px; width: 233px"
														onclick="checkcode(this.value);">
														<form:option value="" htmlEscape="true">
														</form:option>
													</form:select>
													<div class="errormsg">
														<form:errors htmlEscape="true"
															path="lgd_LBDistrictAtVillage"></form:errors>
													</div>
												</td>
												<td align="center">
													<table width="100%" class="tbl_no_brdr">
														<tr>
															<td align="center"><label> 
												<input  type="button" id="btnaddVillageFull" name="Submit4" value="<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>" onclick="addItem('choosenlb','availablelb','',false);" />
															</label>
															</td>
														</tr>
														<tr>
															<td align="center"><label>
												 <input  type="button" id="btnremoveOneVillage" name="Submit4" value=" &lt; " class="btn"  onclick="removeItem('choosenlb','availablelb',false)" />
												 <input  type="button" id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;" class="btn"  onclick="removeAll('choosenlb','availablelb',false)" />
															</label>
															</td>
														</tr>
														<tr>
															<td align="center"><label> </label></td>
														</tr>
													</table>
												</td>
												<td width="246" valign="top"><form:select htmlEscape="true"
														name="select4" id="choosenlb" size="1" multiple="multiple"
														path="choosenlb" class="frmtxtarea"
														style="height: 80px; width: 242px"
														onclick="checkcode(this.value)"></form:select>
												</td>
											</tr>


											<tr>
												<td width="246"><div class="errormsg"></div>
													<td width="172"></td>
													<td width="246"><label> 
										<input type="button" id="chooseMoreBtn" onclick='validateSelectedAddMore();' class="btn" value="Choose more Local Bodies" /> <%-- <spring:message htmlEscape="true"  code="Button.CHOOSEMOREDISTRICT"></spring:message>" /> --%>
													</label>
												</td>
											</tr>
										</table>
										<table id="dynamicTbl" width="664" class="tbl_with_brdr"
											style="visibility: hidden; display: none;">
											<tr class="tblRowTitle tblclear">
												<th><spring:message code="Label.LOCALBODYTYPENAME"
														htmlEscape="true"></spring:message></th>
												<th><spring:message
														code="Label.CHILDLOCALGOVTBODYDETAIL" htmlEscape="true"></spring:message>
												</th>
											</tr>
										</table>

										<div class="errormsg"></div></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<div class="btnpnl">
					<table width="100%" class="tbl_no_brdr">
						<tr>
							<td width="14%">&nbsp;</td>
							<td width="86%">
								<div>
									<label> 
					<input type="hidden" name="listformat" id="listformat" value="@" /> 
					<input type="button" onclick="validateSelectAndSubmit(); " name="Submit" class="btn" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" />
					<input	type="button" name="Submit2" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>" onclick="javascript:location.href='viewResolveEntitiesinDisturbedStatePRI.htm?<csrf:token uri='viewResolveEntitiesinDisturbedStatePRI.htm'/>';" />
					<input type="button" name="Submit6" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
									</label>
								</div>
							</td>
						</tr>
					</table>
				</div>
		</div>
		</form:form>
		</td>
		</tr>

	</div>
	</div>
	</body>
</html>
