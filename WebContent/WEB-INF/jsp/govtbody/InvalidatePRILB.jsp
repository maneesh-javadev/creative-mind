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

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'>
	dwr.engine.setActiveReverseAjax(true);
</script>
<script>
	var callActionUrl = function () {
		var url='invalidateLocalBodyforPRI.htm';
		$( 'form[id=invalidateLocalBodyforPRI]' ).attr('action', url +'?<csrf:token uri="'+ url +'"/>');
		$( 'form[id=invalidateLocalBodyforPRI]' ).attr('method','post');
		$( 'form[id=invalidateLocalBodyforPRI]' ).submit();
	};
	</script>
</head>
<body
	onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);">

	<div id="frmcontent">
	

		<div class="frmhd">
			<h3 class="subtitle">
				<label><spring:message htmlEscape="true"  code="Label.INVALIDATEPLB"></spring:message></label>
			</h3>
			<ul id="showhelp" class="listing">				
				<%--//these links are not working <li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
	
		
		
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="invalidateLocalBodyforPRI.htm"
				id="invalidateLocalBodyforPRI" commandName="localGovtBodyForm"
				method="POST" enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="invalidateLocalBodyforPRI"/>" />
				<input type="hidden" name="flag1" id="flag1" value="0" />
				<input type="hidden" name="Tier" id="Tier" value="<c:out value='${Tier}' escapeXml='true'></c:out>"/>
				<input type="hidden" name="Tiertype" id="Tiertype" value="<c:out value='${Tiertype}' escapeXml='true'></c:out>"/>


				<div id='divLgdLBType' class="frmpnlbg">
					<!--'district' id name change  -->
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message htmlEscape="true"
								code="Label.SELECTLOCALBODYTYPE"></spring:message>
						</div>
						
					<div>
						<ul class="blocklist">
							<li>
								
								<label><spring:message
											htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message>
								</label><span class="errormsg">*</span><br /> <form:select path="lgd_LBTypeName" id="ddLgdLBType" onchange="HideShowDivs(this.value);"  htmlEscape="true" cssClass="combofield">
										<!--tierSetupCode id Changed  -->
										<form:option value="0" htmlEscape="true">
											<spring:message htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message>
										</form:option>

										<c:forEach var="localBodyTypelist" varStatus="var"
											items="${localBodyTypelist}">
											<form:option id="typeCode" htmlEscape="true" value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.level}:${localBodyTypelist.nomenclatureEnglish}"><c:out value="${localBodyTypelist.nomenclatureEnglish}" escapeXml="true"></c:out></form:option>
										</c:forEach>
									</form:select>&nbsp;&nbsp;
									<div class="errormsg" id="ddLgdLBType_error1">
										<form:errors path="lgd_LBTypeName" htmlEscape="true" />
									</div>
									<div class="errormsg" id="ddLgdLBType_error2" style="display: none"></div>
								
							</li>
						
							<li>
								
									<div id="divLgdlistSubTypeCode" class="frmpnlbg"
										style="visibility: hidden; display: none;">

										<label><spring:message htmlEscape="true" code="Label.AVAILSUBTYPE"></spring:message> </label> <br /> 
										<form:select path="localbodySubtype" id="ddlgdLBSubTypeCode" htmlEscape="true" cssClass="combofield"> </form:select> <br />
									</div>
								
							</li>
						
							<li>
								
									<div id="divLgdSelIntermediateP" style="visibility: hidden; display: none;">
										
												<label><spring:message htmlEscape="true" code="Label.SELECT"></spring:message><c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out>&nbsp;</label><span class="errormsg">*</span><br />
													
													 <form:select path="lgd_LBDistrictAtInter" cssClass="combofield" id="ddlgdLBDistAtInter" htmlEscape="true" onchange="getlistofIntermediatePanchayat(this.value)">
														<form:option value="0" htmlEscape="true">
															<spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
														</form:option>
														<%-- <form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" /> --%>
														<c:forEach items="${districtPanchayatList}" var="districtList">
															<c:if test="${districtList.operation_state =='A'.charAt(0)}">
																<form:option value="${districtList.localBodyCode}" htmlEscape="true"><c:out value=" ${districtList.localBodyNameEnglish}" escapeXml="true"></c:out>
																</form:option>
															</c:if>
															<c:if test="${districtList.operation_state =='F'.charAt(0)}">
																<form:option value="${districtList.localBodyCode}" disabled="true" htmlEscape="true"><c:out value=" ${districtList.localBodyNameEnglish}" escapeXml="true"></c:out>																				
																</form:option>
															</c:if>
														</c:forEach>
													</form:select> &nbsp;
													<div class="errormsg" id="ddlgdLBDistAtInter_error1">
														<form:errors path="lgd_LBDistrictAtInter" htmlEscape="true" />
													</div>
													<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none"></div>												
									</div>
								
							</li>
							<li>
								
									<div id="divLgdVillageP"
										style="visibility: hidden; display: none;">
										
										<ul class="blocklist">
											<li>											
														<label><spring:message htmlEscape="true" code="Label.SELECT"></spring:message>&nbsp;&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out></label><span class="errormsg">*</span><br /> <form:select path="lgd_LBDistrictAtVillage" cssClass="combofield " htmlEscape="true" id="ddlgdLBDistrictAtVillage" onchange="getWorkOnVillagePanchayatforDistrictP(this.value);" >
														<form:option value="0" htmlEscape="true">
															<spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
														</form:option>
														<%-- <form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" /> --%>
														
														<c:forEach items="${districtPanchayatList}" var="districtList">
															<c:if test="${districtList.operation_state =='A'.charAt(0)}">
																<form:option value="${districtList.localBodyCode}" htmlEscape="true" ><c:out value=" ${districtList.localBodyNameEnglish}" escapeXml="true"></c:out>
																</form:option>
															</c:if>
															<c:if test="${districtList.operation_state =='F'.charAt(0)}">
																<form:option value="${districtList.localBodyCode}" disabled="true" htmlEscape="true"><c:out value=" ${districtList.localBodyNameEnglish}" escapeXml="true"></c:out>																			
																</form:option>
															</c:if>
														</c:forEach>
													</form:select> &nbsp;
													<div class="errormsg" id="ddlgdLBDistrictAtVillage_error1">
														<form:errors path="lgd_LBDistrictAtVillage" htmlEscape="true" />
													</div>
													<div class="errormsg" id="ddlgdLBDistrictAtVillage_error2" style="display: none"></div>
											</li>

											<li>											
													<label><spring:message htmlEscape="true" code="Label.SELECT"></spring:message>&nbsp;&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out></label><span class="errormsg">*</span><br /> 
													<form:select path="lgd_LBIntermediateAtVillage" cssClass="combofield" id="ddlgdLBInterAtVillage"  onchange="getlistofgp(this.value);">
														<form:option value="0" htmlEscape="true">
															<spring:message htmlEscape="true" code="Label.SELECTINTERMEDIATELOCBODY"></spring:message>
														</form:option>
													</form:select> &nbsp;
													<div class="errormsg" id="ddlgdLBInterAtVillage_error1">
														<form:errors path="lgd_LBIntermediateAtVillage" htmlEscape="true" />
													</div>
													<div class="errormsg" id="ddlgdLBInterAtVillage_error2" style="display: none"></div>																						
											</li>
										</ul>
									
									</div>
								
							</li>
						</ul>
					</div>
				</div>
			</div>


				<div id="Districtlocalbody"
					style="visibility: hidden; display: none;">
					<div class="frmpnlbg" id="divLgdLBType1">
						<div class="frmtxt">

							<div class="frmhdtitle">
								<!-- dell -->
								<%-- <spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message> --%>

						<div>
								<c:if test="${Tiertype eq 2}">
                                  	<spring:message htmlEscape="true"  code="Label.selectIPdelete"></spring:message>
                                </c:if>
								<c:if test="${Tiertype eq 0}"> 
                          			 <spring:message htmlEscape="true"  code="Label.selectDPdelete"></spring:message>
                                 </c:if>
                           </div>
					</div>
							
							<div >
								<label><spring:message htmlEscape="true"
												code="Label.SELECT"></spring:message>
											<c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out> &nbsp;</label><span
										class="errormsg">*</span><br /> <%--  <c:set var="flag1" value="1"/>			 --%>

										<form:select path="districtpanlbid" cssClass="combofield" htmlEscape="true"
											id="lblist1" 
											onchange="removeData();setflag1()">
															ddSourceLocalBody id name changed 
															<form:option value="0" htmlEscape="true">
												<spring:message htmlEscape="true"
													code="Label.SELECTLOCALBODY"></spring:message>
											</form:option>
											<%-- <form:options items="${districtPanchayatList}"
												itemLabel="localBodyNameEnglish" itemValue="localBodyCode" /> --%>
												
											<c:forEach items="${districtPanchayatList}" var="districtList">
														<c:if test="${districtList.operation_state =='A'.charAt(0)}">
															<form:option value="${districtList.localBodyCode}" htmlEscape="true"><c:out value=" ${districtList.localBodyNameEnglish}" escapeXml="true"></c:out>
															</form:option>
														</c:if>
														<c:if test="${districtList.operation_state =='F'.charAt(0)}">
															<form:option value="${districtList.localBodyCode}" disabled="true" htmlEscape="true"><c:out value=" ${districtList.localBodyNameEnglish}" escapeXml="true"></c:out>																			
															</form:option>
														</c:if>
											</c:forEach>
										</form:select> &nbsp;
										<div class="errormsg" id="ddlgdLBDistAtInter_error1">
											<form:errors path="lgd_LBDistrictAtInter" htmlEscape="true" />
										</div>
										<div class="errormsg" id="ddlgdLBDistAtInter_error2"
											style="display: none"></div>
								</div>														
						</div>
					</div>
				</div>

				<div id="IPlocalbody" style="visibility: hidden; display: none;">
					<div class="frmpnlbg" id="divLgdLBType2">
						<div class="frmtxt">

							<div class="frmhdtitle">
								<!-- dell -->
							<div >
								<c:if test="${Tier eq 3}">
 									<spring:message htmlEscape="true"  code="Label.selectIPdelete"></spring:message>
								</c:if>
								<c:if test="${Tier eq 2}">
									<spring:message htmlEscape="true"  code="Label.selectGPdelete"></spring:message>
								</c:if>
							</div>
						</div>
									<div >
										<c:if test="${Tier eq 3}">
											<label><spring:message code="Label.SELINTERMPANCHYAT"></spring:message>
											</label>
										</c:if> <c:if test="${Tier eq 2}">
											<label><spring:message code="Label.SELECTGPLB"></spring:message>
											</label>
										</c:if>  <span class="errormsg">*</span><br />
										 <form:select path="intermediatepanlbid" cssClass="combofield" id="lblist2"  onchange="removeData();setflag2();">
											
										</form:select> &nbsp;
										<div class="errormsg" id="ddlgdLBDistAtInter_error1">
											<form:errors path="lgd_LBDistrictAtInter" htmlEscape="true" />
										</div>
										<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none"></div>
									</div>									
						</div>
					</div>
				</div>

				<div id="gplocalbody" style="visibility: hidden; display: none;">
					<div class="frmpnlbg" id="divLgdLBType3">
						<div class="frmtxt">

							<div class="frmhdtitle">
								<!-- dell -->
								<div >	
									<spring:message htmlEscape="true"  code="Label.selectGPdelete"></spring:message>
								</div>
							</div>

							<div >
								<label><spring:message htmlEscape="true" code="Label.SELECTGPLB"></spring:message> &nbsp;</label><span class="errormsg">*</span><br /> <%--  <c:set var="flag3" value="3"/>		 --%>
										<form:select path="grampanlbid" cssClass="combofield" id="lblist3" htmlEscape="true" onchange="removeData();setflag3();">
											<form:option value="0">
												<spring:message htmlEscape="true" code="Label.SELECTINTERMEDIATELOCBODY"></spring:message>
											</form:option>
										</form:select> &nbsp;
										<div class="errormsg" id="ddlgdLBDistAtInter_error1">
											<form:errors path="lgd_LBDistrictAtInter" htmlEscape="true" />
										</div>
										<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none"></div>
								</div>

						</div>
					</div>
				</div>



				<div class="frmpnlbg" id="divLgdLBType4">
					<div class="frmtxt">
							<div >				
								<label> <spring:message htmlEscape="true" code="Label.SELECTLBCOVERD"></spring:message>&nbsp; </label>							
								<ul class="listing">
									<li>
										<label> <form:radiobutton id="show_dp" path="lboption" htmlEscape="true" onclick="getlblist();removeListItem();" value="true" name="rdoDistrictDelete" /> </label> 
										YES
									</li>

									<li>
										<label> <form:radiobutton id="hide_dp" path="lboption" htmlEscape="true" onclick="hidelblist()" value="true" name="rdoDistrictDelete" /> </label>
										No
									</li>
								</ul>							
						</div>
						<div class="errormsg"></div>
					</div>
				</div>



				<div id="IPMerge1" style="visibility: hidden; display: none;">
					<div class="frmpnlbg" id="divLgdLBType5">
						<div class="frmpnlbrdr">								
								<div >
									<br /><label> <spring:message htmlEscape="true" code="Label.SELECTDP"></spring:message> </label><span class="errormsg">*</span><br />
									 <label> <form:select path="contSubDistrict" cssClass="combofield" id="mergelocalbody" htmlEscape="true" >
													<form:option value="0" htmlEscape="true">
														<spring:message htmlEscape="true" code="Label.DISTRICTPANCHAYAT"></spring:message>
													</form:option>
													<form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" htmlEscape="true" />
												</form:select> </label><br /><br /><br />
								</div>	
										
							
						</div>
					</div>
				</div>

				<div id="IPMerge2" style="visibility: hidden; display: none;">
					<div class="frmpnlbg" id="divLgdLBType6">
						<div class="frmpnlbrdr">
								<div >
									<br /><c:if test="${Tier eq 2}">
												<label> <spring:message htmlEscape="true" code="Label.SELECTGP"></spring:message> </label>
												<span class="errormsg">*</span>
												<br />
												
											</c:if> <c:if test="${Tier eq 3}">
												<label> <spring:message htmlEscape="true"
														code="Label.SELECTIP"></spring:message> </label>
												<span class="errormsg">*</span>
												<br />
												
											</c:if> <label><form:select path="contSubDistrict" cssClass="combofield" htmlEscape="true"
												id="mergelocalbody2" >
												<form:option value="0" htmlEscape="true">
													<spring:message htmlEscape="true" code="Label.DISTRICTPANCHAYAT"></spring:message>
												</form:option>

											</form:select> </label><br /><br /><br />
									</div>										
						</div>
					</div>
				</div>


				<div id="IPMerge3" style="visibility: hidden; display: none;">
					<div class="frmpnlbg" id="divLgdLBType6">
						<div class="frmpnlbrdr">
									<div >					
										<br /><label> <spring:message htmlEscape="true" code="Label.SELECTGP"></spring:message> </label><span class="errormsg">*</span><br />
										 <label> <form:select path="contSubDistrict" cssClass="combofield" id="mergelocalbody3" htmlEscape="true" >
													<form:option value="0" htmlEscape="true">
														<spring:message htmlEscape="true" code="Label.DISTRICTPANCHAYAT"></spring:message>
													</form:option>
												</form:select> </label><br /><br /><br />	
									</div>									
						</div>
					</div>
				</div>

				<div id="Districtlistlocalbodylist" style="visibility: hidden; display: none;">
					<div class="frmpnlbg" id="divLgdLBTypelist">
					<div class="frmpnlbrdr">							
						<div>							
							<div class="ms_container">
										<div class="ms_selectable">
											<label id="selectEntity"></label><br />
											<form:select name="select9" size="1" id="availablelb" htmlEscape="true" path="lgd_LBDistrictAtVillage" multiple="multiple" class="frmtxtarea" onclick="checkcode(this.value);">
														<form:option value="" htmlEscape="true"> </form:option>
											</form:select>
											<div class="errormsg">
												<form:errors htmlEscape="true" path="lgd_LBDistrictAtVillage"></form:errors>
											</div> 
										</div>
										<div class="ms_buttons">
												<label> <input type="button" id="btnaddVillageFull" name="Submit4" class="bttn" value="<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>" onclick="addItem('choosenlb','availablelb','',false);" /> </label>
												<label> <input type="button" id="btnremoveOneVillage" name="Submit4" value=" &lt; " class="bttn" onclick="removeItem('choosenlb','availablelb',false)" /> </label>
												<label> <input type="button" id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;" class="bttn"  onclick="removeAll('choosenlb','availablelb',false)" /> </label>															
										</div>
										<div class="ms_selection">
											<label id="selectedEntity"></label><br />
											<form:select name="select4" id="choosenlb" size="1" htmlEscape="true" multiple="multiple" path="choosenlb" class="frmtxtarea" onclick="checkcode(this.value)"></form:select>
											
											<label> <input type="button" id="chooseMoreBtn" onclick='validateSelectedAddMore();' class="btn" value="Choose more Local Bodies" /></label>											
										</div>																		
										<div class="errormsg"></div>
										
											
										
							</div>
							
								<div class="errormsg"></div>		
						
								<div class="clear"></div>
							
								<table id="dynamicTbl" width="664" class="tbl_with_brdr" style="visibility: hidden; display: none;">
									<tr class="tblRowTitle tblclear">
										<th><spring:message code="Label.LOCALBODYTYPENAME" htmlEscape="true"></spring:message></th>
										<th><spring:message code="Label.CHILDLOCALGOVTBODYDETAIL" htmlEscape="true"></spring:message>
										</th>
									</tr>
								</table>								
						</div>																						
					</div>
					
				</div>
			</div>
				<div class="btnpnl">													
									<label>
									 <input type="hidden" name="listformat" 	id="listformat" value="@" /> 
									 <input type="button" onclick="validateSelectAndSubmit(); " name="Submit" class="btn" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" />
									
									 <input type="button" name="Submit2" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLEAR"> </spring:message>" onclick="javascript:location.href='invalidateprilocalbody.htm?<csrf:token uri="invalidateprilocalbody.htm"/>'" />
									 <input type="button" name="Submit6" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
									</label>															
				</div>
	
		</form:form>
		</div>
		

	</div>
	</div>
</body>
</html>

