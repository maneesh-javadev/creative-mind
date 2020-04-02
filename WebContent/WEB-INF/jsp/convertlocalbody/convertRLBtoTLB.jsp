<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Convert RLB to TLB</title>
<%@include file="../common/taglib_includes.jsp"%>
<script src="js/convertRLBtoTLB.js"></script>
<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/lgd_localbody.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrLocalGovtBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
</head>

<body onload="loadConvertRLBTLBPage();" onsubmit="cursorwait();" oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">

	<div id="frmcontent">
		<div class="frmhd">
		
						<h3 class="subtitle"><spring:message htmlEscape="true" code="Label.CONVERSIONRLBTLB"></spring:message></h3>
										 <ul id="showhelp" class="listing">
					 				       <!--  //this link is not working  <li>
									 				 <a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /> </a>
									 		</li>
					 				         -->
					 				        <%--//these links are not working <li>
					 				        			<a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a>
					 				        </li> --%>
					 	
					 			        </ul>
			
		
		
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">

			<form:form commandName="convertRLBtoTLBForm" action="draftConversionRLBtoTLB.htm" method="POST" enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="draftConversionRLBtoTLB.htm"/>" />

				<div id="cat">
					<div class="frmpnlbg" id="divFirstPanel">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true" code="Label.CONVERTRLBToTLB"></spring:message>
							</div>
							<input type="hidden" class="btn" value="<c:out value='${stateCode}' escapeXml='true'></c:out>" id="hdnState" name="hdnState" />
							
							<div >
							
									<ul class="blocklist">
												<li  >
												
															<label><spring:message htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message></label><span class="errormsg">*</span><br /> <form:select path="rurallbTypeName" id="ddLgdLBType" class="combofield"
											htmlEscape="true" onchange="getHideShowRuralToTLBLBList(this.value)">
											<!--tierSetupCode id Changed  -->
											<form:option value="0"  htmlEscape="true">
												<spring:message htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message>
											</form:option>

											<c:forEach var="localBodyTypelist" varStatus="var" items="${localbodyTypelist}">
												<form:option id="typeCode" value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.level}"  htmlEscape="true"><esapi:encodeForHTMLAttribute>${localBodyTypelist.nomenclatureEnglish}</esapi:encodeForHTMLAttribute></form:option>
											</c:forEach>
										</form:select>&nbsp;&nbsp; <%-- <span> <form:errors path="rurallbTypeName"
												class="errormsg"></form:errors> </span> &nbsp;&nbsp; <span
										class="errormsg" id="ddLgdLBType_error"><spring:message htmlEscape="true" 
												code="error.blank.ruralLBTypeName"></spring:message> </span> --%>

										<div id="ddLgdLBType_error" class="error">
											<spring:message code="error.blank.ruralLBTypeName" htmlEscape="true"></spring:message>
										</div>
										<div class="errormsg" id="ddLgdLBType_error1">
											<form:errors path="rurallbTypeName" htmlEscape="true" />
										</div>
										<div class="errormsg" id="ddLgdLBType_error2" style="display: none"></div>
												
												
												</li>
												
												<li>
												
									<div id="divRuralDistrictPanchayat">
									
									
												<ul class="blocklist">
															<li  >
																<div id="ddLgdLBDistPDestList_error" class="error">
																		<spring:message code="error.blank.DISTRICTP" htmlEscape="true"></spring:message>
																	</div>
																	<div class="errormsg" id="ddLgdLBDistPDestList_error1">
																		<form:errors path="rurallbTypeName" htmlEscape="true" />
																	</div>
																	<div class="errormsg" id="ddLgdLBDistPDestList_error2" style="display: none"></div>
															</li>
															
												<li>
																						<div class="ms_container">
																									<div class="ms_selectable">
																											<spring:message htmlEscape="true" code="Label.AVAILABLE"></spring:message> &nbsp;<esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureDist}</esapi:encodeForHTML> &nbsp;<spring:message htmlEscape="true" code="Label.LIST"></spring:message>
													</label><br /> <form:select path="lgd_LBDistPSourceList" class="frmtxtarea" id="ddLgdLBDistPSourceList" items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" 
															multiple="true" htmlEscape="true">

														</form:select><br /> <br />
																									</div>
																									<div class="ms_buttons">
																												<input type="button" value="<spring:message
																code="Button.WHOLE"/>" class="bttn" onclick="addItemforLB('ddLgdLBDistPDestList','ddLgdLBDistPSourceList','FULL',true);" /> 
														<input type="button" class="bttn" id="btnremoveOneULB" name="Submit4" value="Back &lt;" onclick="removeItem('ddLgdLBDistPDestList','ddLgdLBDistPSourceList',true)" /> <input type="button"
														value="<spring:message code="Button.RESET"></spring:message>" class="bttn" onclick="removeAll('ddLgdLBDistPDestList', 'ddLgdLBDistPSourceList', true)" /> <input type="button" value="Part &gt;&gt;" 
														 class="bttn" onclick="addItemforLB('ddLgdLBDistPDestList','ddLgdLBDistPSourceList', 'PART',true);" />
																
																									</div>
																									<div class="ms_selection">
																											<label><spring:message htmlEscape="true" code="Label.CONTRIBUTE"></spring:message> &nbsp;<esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureDist}</esapi:encodeForHTML> &nbsp;<spring:message htmlEscape="true" code="Label.LIST"></spring:message> </label> <br />
														<form:select id="ddLgdLBDistPDestList" size="1" multiple="true" path="lgd_LBDistPDestList" class="frmtxtarea" htmlEscape="true">
														</form:select><br /> &nbsp;&nbsp;&nbsp; <input type="button" value="<spring:message htmlEscape="true" code="Button.GETDISTRICTL"/>" onclick="selectIntermediateLocalBodyListAtDCA();" />
																									</div>																		
																						</div>
							
														
																
												
												
												
												</li>
												
												<li>
														<div class="errormsg" id="ddLgdLBDistCAreaDestL_error1">
															<form:errors path="lgd_LBDistCAreaDestList" htmlEscape="true" />
														</div>
														<div class="errormsg" id="ddLgdLBDistCAreaDestL_error2" style="display: none"></div>
												</li>
												
												
												<li>
												</li>
												
												<li>
												</li>
												
												</ul>
									
									<ul class="blocklist">
									
											<li>
														<div class="ms_container">
																	<div class="ms_selectable">
																							<label><spring:message htmlEscape="true" code="Label.SELINTERMPANCHYAT"></spring:message> </label><br /> <form:select path="lgd_LBDistCAreaSourceList" class="frmtxtarea" id="ddIntermediateLbSourceL"
															multiple="true" htmlEscape="true">

														</form:select><br /> <br />
																	</div>
																	<div class="ms_buttons">
																									<input type="button"  class="bttn"  value="<spring:message
																code="Button.WHOLE"/>" 
														onclick="addItemforVillageLocalBody('ddIntermediateLbDesteL','ddIntermediateLbSourceL','FULL',true);" /> <br /> <input type="button" class="bttn"  id="btnremoveOneULB" name="Submit4" value="Back &lt;"
														onclick="removeItem('ddLgdLBDistCAreaDestL','ddLgdLBDistCAreaSourceL',true)" /><br /> <input type="button" class="bttn"  value="<spring:message code="Button.RESET"></spring:message>" 
														onclick="removeAll('ddLgdLBDistCAreaDestL', 'ddLgdLBDistCAreaSourceL', true)" /><br />
																	</div>
																	<div class="ms_selection">
																				<label><spring:message htmlEscape="true" code="Label.SELECTEDINTERRLBS"></spring:message> </label> <br /> <form:select name="select6" id="ddIntermediateLbDesteL" size="1" multiple="true" path="lgd_LBDistCAreaDestList"
															class="frmtxtarea" htmlEscape="true">
														</form:select>
																	</div>																		
														</div>
							
											</li>
									
									</ul>
									
									
									
										
										</div>
												
												
												</li>
												
												
												
									
												
									
									</ul>
									<div class="clear"></div>
									<ul class="blocklist">
											<li>
											
									
										<div id="divRuralIntermediatePanchayat" style="visibility: visible; display: none;">
													
													<ul class="blocklist">
													
													<li >
															<label> <spring:message htmlEscape="true" code="Label.AVAILABLE"></spring:message> &nbsp;<esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureDist}</esapi:encodeForHTML> &nbsp;<spring:message htmlEscape="true" code="Label.LIST"></spring:message>
													</label><br /> <form:select path="lgd_LBDistListAtInterCA" onchange="getInterPanchayatbyDcodeAtICA(this.value);" class="combofield" id="ddLgdLBDistListAtInterCA" htmlEscape="true">
															<form:option value="0" htmlEscape="true">
																<spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
															</form:option>
															<form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" htmlEscape="true"/>
														</form:select>&nbsp;&nbsp;<%-- <form:errors path="lgd_LBDistListAtInterCA"
															class="errormsg"></form:errors> &nbsp;&nbsp; <span
														class="errormsg" id="ddLgdLBDistListAtInterCA_error"><spring:message htmlEscape="true" 
																code="error.SOURCESELECTLOCALBODYPARENT"></spring:message></span> --%>

														<div id="ddLgdLBDistListAtInterCA_error" class="error">
															<spring:message code="error.SOURCESELECTLOCALBODYPARENT" htmlEscape="true"></spring:message>
														</div>
														<div class="errormsg" id="ddLgdLBDistListAtInterCA_error1">
															<form:errors path="lgd_LBDistListAtInterCA" htmlEscape="true" />
														</div>
														<div class="errormsg" id="ddLgdLBDistListAtInterCA_error2" style="display: none"></div>
													</li>
													<li>
													<div class="errormsg" id="ddLgdLBInterPDestList_error1">
															<form:errors path="lgd_LBInterPDestList" htmlEscape="true" />
														</div>
														<div class="errormsg" id="ddLgdLBInterPDestList_error2" style="display: none"></div>
													</li>
													
													<li>
																	<div class="ms_container">
																				<div class="ms_selectable">
																										<label><spring:message htmlEscape="true" code="Label.AVAILABLE"></spring:message> &nbsp;<esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureInter}</esapi:encodeForHTML> &nbsp;<spring:message htmlEscape="true" code="Label.LIST"></spring:message>
													</label><br /> <form:select path="lgd_LBInterPSourceList" class="frmtxtarea" id="ddLgdLBInterPSourceList"  multiple="true" htmlEscape="true">

														</form:select><br /> <br />
																				</div>
																				<div class="ms_buttons">
																															<input type="button" value="<spring:message
																code="Button.WHOLE"/>"  class ="bttn" onclick="addItemforLB('ddLgdLBInterPDestList','ddLgdLBInterPSourceList','FULL',true);" /> 
														<input type="button" class ="bttn" id="btnremoveOneULB" name="Submit4" value="Back &lt;" onclick="removeItem('ddLgdLBInterPDestList','ddLgdLBInterPSourceList',true)" /> <input type="button"
														class ="bttn"  value="<spring:message code="Button.RESET"></spring:message>"  onclick="removeAll('ddLgdLBDistPDestList', 'ddLgdLBInterPSourceList', true)" /> <input type="button" class ="bttn" value="Part &gt;&gt;" 
														onclick="addItemforLB('ddLgdLBInterPDestList','ddLgdLBInterPSourceList', 'PART',true);" />
														
																				</div>
																				<div class="ms_selection">
																								<label><spring:message htmlEscape="true" code="Label.CONTRIBUTE"></spring:message> &nbsp;<esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureInter}</esapi:encodeForHTML> &nbsp;<spring:message htmlEscape="true" code="Label.LIST"></spring:message> </label> <br />
														<form:select name="select6" id="ddLgdLBInterPDestList" size="1" multiple="true" path="lgd_LBInterPDestList" class="frmtxtarea" htmlEscape="true">
														</form:select><br />&nbsp;&nbsp;&nbsp; <input type="button" value="<spring:message htmlEscape="true"  code="Button.GETSUBDISTRICTL"/>" onclick="selectVillageLocalBodyListAtICA();" />
														
																				</div>																		
																	</div>
							
													
													</li>
													
													
													</ul>		
													<div class="clear"></div>
													
													<ul class="blocklist">
															<li>
															<div class="ms_container">
																		<div class="ms_selectable">
																						<label><spring:message htmlEscape="true" code="Label.AVAILVILLAGEPANCHAYATLIST"></spring:message> </label><br /> <form:select path="lgd_LBInterCAreaSourceList" class="frmtxtarea" id="ddLgdLBVillageInterCAreaSourceL"
															multiple="true" htmlEscape="true">

														</form:select><br /> <br />
																		</div>
																		<div class="ms_buttons">
																										<input type="button" class ="bttn" value="<spring:message
																code="Button.WHOLE"/>" 
														onclick="addItemforLB('ddLgdLBVillageInterCAreaDestL','ddLgdLBVillageInterCAreaSourceL','FULL',true);" /> <input type="button" class ="bttn" id="btnremoveOneULB" name="Submit4" value="Back &lt;"
														onclick="removeItem('ddLgdLBVillageInterCAreaDestL','ddLgdLBVillageInterCAreaSourceL',true)" /><input type="button" class ="bttn" value="<spring:message code="Button.RESET"></spring:message>" 
														onclick="removeAll('ddLgdLBVillageInterCAreaDestL', 'ddLgdLBVillageInterCAreaSourceL', true)" /><br />
																		</div>
																		<div class="ms_selection">
																				
													<label><spring:message htmlEscape="true" code="Label.CONTRIBUTVILLAGEPANCHAYATLIST"></spring:message> </label> <br /> <form:select name="select6" id="ddLgdLBVillageInterCAreaDestL" size="1" multiple="true"
															path="lgd_LBInterCAreaDestList" class="frmtxtarea" htmlEscape="true">
														</form:select><br /> 
								
																		</div>																		
															</div>
															
															
															</li>
															
													
													</ul>
															
															
										<div class="clear" ></div>
										</div>
									
									</li>
											
									</ul>
									
									
									<ul class="blocklist">
											
									
										<li>
											<div id="divRuralVillagePanchayat" style="visibility: hidden; display: none;"> 
												<ul class="blocklist">
												<li >
																			<label> <spring:message htmlEscape="true" code="Label.SELECT"></spring:message> &nbsp;<esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureDist}</esapi:encodeForHTML>&nbsp;<spring:message htmlEscape="true" code="Label.LIST"></spring:message><span
															class="errormsg">*</span></label><br />
															<form:select path="lgd_LBDistListAtVillageCA" class="combofield" id="ddLgdLBDistListAtVillageCA" htmlEscape="true" onchange="getInterPanchayatbyDcodeAtVCA(this.value);">
															<form:option value="0" htmlEscape="true">
																<spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
															</form:option>
															<c:forEach items="${districtPanchayatList}" var="districtPanchayatList">
																		<c:if test="${districtPanchayatList.operation_state =='A'.charAt(0)}">
																			<form:option value="${districtPanchayatList.localBodyCode}" htmlEscape="true" ><esapi:encodeForHTMLAttribute>${districtPanchayatList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute>
																			</form:option>
																		</c:if>
																		<c:if test="${districtPanchayatList.operation_state =='F'.charAt(0)}">
																			<form:option value="${districtPanchayatList.localBodyCode}" disabled="true" htmlEscape="true"><esapi:encodeForHTMLAttribute>${districtPanchayatList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute>																				
																			</form:option>
																		</c:if>
															</c:forEach>
														</form:select>

														<div id="ddLgdLBDistListAtVillageCA_error" class="error">
															<spring:message code="Error.DISTRICTLOCALBODY" htmlEscape="true"></spring:message>
														</div>
														<div class="errormsg" id="ddLgdLBDistListAtVillageCA_error1">
															<form:errors path="lgd_LBDistListAtVillageCA" htmlEscape="true" />
														</div>
														<div class="errormsg" id="ddLgdLBDistListAtVillageCA_error2" style="display: none"></div>
														
												</li>
												<li >
																<label> <spring:message htmlEscape="true" code="Label.SELECT"></spring:message> &nbsp; <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureInter}</esapi:encodeForHTML> &nbsp;<spring:message htmlEscape="true" code="Label.LIST"></spring:message><span
															class="errormsg">*</span></label><br /> <form:select path="lgd_LBInterListAtVillageCA" class="combofield" id="ddLgdLBInterListAtVillageCA" htmlEscape="true" onchange="getVillagePanchbyIntercodeAtVCA(this.value);">
															<form:option value="0" htmlEscape="true">
																<spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
															</form:option>

														</form:select> &nbsp;&nbsp;
														<div id="ddLgdLBInterListAtVillageCA_error" class="error">
															<spring:message code="Error.INTERMEDIATELOCALBODY" htmlEscape="true"></spring:message>
														</div>
														<div class="errormsg" id="ddLgdLBInterListAtVillageCA_error1">
															<form:errors path="lgd_LBInterListAtVillageCA" htmlEscape="true" />
														</div>
														<div class="errormsg" id="ddLgdLBInterListAtVillageCA_error2" style="display: none"></div>
												</li>
												<li  >
																			<div id="ddLgdLBVillageDestAtVillageCA_error" class="error">
															<spring:message code="Error.NoVPSelected" htmlEscape="true"></spring:message>
														</div>
														<div class="errormsg" id="ddLgdLBVillageDestAtVillageCA_error1">
															<form:errors path="lgd_LBVillageDestAtVillageCA" htmlEscape="true" />
														</div>
														<div class="errormsg" id="ddLgdLBVillageDestAtVillageCA_error2" style="display: none"></div>
												
												</li>
												<li>
														<div class="ms_container">
																	<div class="ms_selectable">
																					<label><spring:message htmlEscape="true" code="Label.AVAILABLE"></spring:message> &nbsp; <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureVillage}</esapi:encodeForHTML> &nbsp;<spring:message htmlEscape="true" code="Label.LIST"></spring:message>
													</label><br /> <form:select path="lgd_LBVillageSourceAtVillageCA" class="frmtxtarea" id="ddLgdLBVillageSourceAtVillageCA"  multiple="true">

														</form:select><br /> <br />
																	</div>
																	<div class="ms_buttons">
																								<input type="button" value="<spring:message
																code="Button.WHOLE"/>" 
														onclick="addItemVillageFinalS('ddLgdLBVillageDestAtVillageCA','ddLgdLBVillageSourceAtVillageCA','FULL',true);" /> <br /> <input type="button" id="btnremoveOneULB" name="Submit4" value="Back &lt;"
														onclick="removeItem('ddLgdLBVillageDestAtVillageCA','ddLgdLBVillageSourceAtVillageCA',true)" /><br /> <input type="button" value="<spring:message code="Button.RESET"></spring:message>" 
														onclick="removeAll('ddLgdLBVillageDestAtVillageCA', 'ddLgdLBVillageSourceAtVillageCA', true)" /><br />
																	</div>
																	<div class="ms_selection">
																					<label><spring:message htmlEscape="true" code="Label.CONTRIBUTE"></spring:message> &nbsp; <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureVillage}</esapi:encodeForHTML> &nbsp;<spring:message htmlEscape="true" code="Label.LIST"></spring:message><span
															class="errormsg">*</span> </label> <br /> <form:select name="select6" id="ddLgdLBVillageDestAtVillageCA" size="1" multiple="true" path="lgd_LBVillageDestAtVillageCA" class="frmtxtarea" htmlEscape="true">
														</form:select><br /> 
																	</div>																		
														</div>
							
												</li>
												
												<li>
														<div class="errormsg" id="ddLgdLBVillageCAreaDestL_error_error1">
															<form:errors path="lgd_LBVillageCAreaDestL" htmlEscape="true" />
														</div>
														<div class="errormsg" id="ddLgdLBVillageCAreaDestL_error_error2" style="display: none"></div>
												</li>
												
												</ul>
										
											<div class="clear"></div>
										</div>
										
										</li>
										
									</ul>
									
									<div class="clear"></div>
							
							</div>
							
							
							
							<div class="clear"></div>
						</div>
						<div class="btnpnl">
							<label> <input type="button" name="Submit" id="btnNext" onclick="return validateFirstAllRLBtoTLB();" value="<spring:message code="Button.NEXTSTEP" htmlEscape="true"></spring:message>" />
											</label><label><input type="button" class="btn" name="Submit6" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
										
						</div>

					</div>
					<div id="divSecondPanel" style="display: none">  
 						<div class="frmpnlbg">
							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message htmlEscape="true" code="Label.SELTRIBALLOCALBODY"></spring:message>
								</div>
								<div  >
										<ul class="blocklist">
												<li>
												<form:radiobutton path="mergeRLBtoTLB" id="rdmergeRLBtoTLB" value="M" onclick="radioClick(this.value,document.getElementById('ddLgdLBType').value);" /> &nbsp; <spring:message htmlEscape="true"
												code="Label.MERGERLBTOTLB"></spring:message> <br /> <form:radiobutton path="declarenewTLB" id="rddeclarenewTLB" onclick="radioClick(this.value,document.getElementById('ddLgdLBType').value);" value="N" /> &nbsp; <spring:message
												htmlEscape="true" code="Label.DECLARENEWTLB"></spring:message>
												</li>
												
												<li>
												<span id="rdmergeRLBtoTLB_error" class="errormsg"></span> <%-- <div id="rdmergeRLBtoTLB_error" class="error"><spring:message code="error.SOURCESELECTLOCALBODYPARENT" htmlEscape="true"></spring:message></div>  --%> <form:errors
												path="mergeRLBtoTLB" class="errormsg" htmlEscape="true"></form:errors> <form:errors path="declarenewTLB" class="errormsg" htmlEscape="true"></form:errors>
												</li>
												
												<li>
												</li>
										</ul>
								
								</div>
								
							<div class="clear"></div>
							</div>
						</div>
						<div class="frmpnlbg" id="divmergeTLB">
							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message htmlEscape="true" code="Label.EXISTINGTLB"></spring:message>
								</div>
								
								<ul class="blocklist">
										<li >
												<label><spring:message htmlEscape="true" code="Label.TRIBALLOCALBODYTYPE"></spring:message></label><span class="errormsg">*</span><br /> <form:select id="ddTraditionalLBType" path="traditionalLbTypeName" size="1"
												class="combofield"  onchange="gethideShowDivforExistTLB(this.value);" htmlEscape="true">
												<form:option value="0" htmlEscape="true">
													<spring:message htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message>
												</form:option>
												<c:forEach var="traditionalocalBodyTypelist" varStatus="var" items="${traditionalocalBodyTypelist}">
													<form:option id="typeCode" value="${traditionalocalBodyTypelist.localBodyTypeCode}:${traditionalocalBodyTypelist.level}" htmlEscape="true"> <esapi:encodeForHTMLAttribute>${traditionalocalBodyTypelist.nomenclatureEnglish}</esapi:encodeForHTMLAttribute> </form:option>
												</c:forEach>

											</form:select> <%-- <span id="ddTraditionalLBType_error" class="errormsg">
												</span> <form:errors path="traditionalLbTypeName" class="errormsg"></form:errors> --%>
											<div id="ddTraditionalLBType_error" class="error">
												<spring:message code="error.blank.ruralLBTypeName" htmlEscape="true"></spring:message>
											</div>
											<div class="errormsg" id="ddTraditionalLBType_error1">
												<form:errors path="traditionalLbTypeName" htmlEscape="true" />
											</div>
											<div class="errormsg" id="ddTraditionalLBType_error2" style="display: none"></div>
										</li>
										<li>
															<div id="divDistrictPforExist" style="visibility: hidden; display: none;">
												<!--  tr_district id name Change-->
												
												<div  >
												<ul class="blocklist">
														<li>
														<label><spring:message htmlEscape="true" code="Label.SELECT"></spring:message>  <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureDistTrade}</esapi:encodeForHTML> </label><span class="errormsg">*</span><br /> <form:select
																path="lgd_LBDistrictforExist" class="combofield" id="ddlgdLBDistrictPforExist" items="${districtTraditionalList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" htmlEscape="true">
																<form:option value="0" htmlEscape="true">
																	<spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
																</form:option>
																<%-- <form:options items="${districtTraditionalList}"
																	itemLabel="localBodyNameEnglish"
																	itemValue="localBodyCode" /> --%>
															</form:select> &nbsp;<%-- <span><form:errors
																	path="lgd_LBDistrictforExist" class="errormsg"></form:errors>
														</span> &nbsp;&nbsp;<span class="errormsg"
															id="ddlgdLBDistrictPforExist_error"> </span><br />  --%>
															<div id="ddlgdLBDistrictPforExist_error" class="error">
																<spring:message code="error.blank.ruralLBTypeName" htmlEscape="true"></spring:message>
															</div>
															<div class="errormsg" id="ddlgdLBDistrictPforExist_error1">
																<form:errors path="lgd_LBDistrictforExist" htmlEscape="true" />
															</div>
															<div class="errormsg" id="ddlgdLBDistrictPforExist_error2" style="display: none"></div> <br />
														</li>
														
												</ul>
												</div>
												<div class="clear"></div>
											</div>
										
										
										</li>
										<li>
																	<div id="divIntermediatePforExist" style="visibility: hidden; display: none;"> 
												<!-- tr_intermediate id change -->
													
												<div >
												
														<ul class="blocklist">
																<li>
																							<label><spring:message htmlEscape="true" code="Label.SELECT"></spring:message>&nbsp;&nbsp; <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureDistTrade}</esapi:encodeForHTML> </label><span class="errormsg">*</span><br /> <form:select
																path="lgd_LBDistrictPatInterforExist" class="combofield" id="ddlgdLBDistrictAtInterforExist" onchange="getInterPanchayatAtInterbyDcodeforExist(this.value);" htmlEscape="true">
																<form:option value="0" htmlEscape="true">
																	<spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
																</form:option>
																<%-- <form:options items="${districtTraditionalList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" /> --%>
																<c:forEach items="${districtTraditionalList}" var="districtTraditionalList">
																		<c:if test="${districtTraditionalList.operation_state =='A'.charAt(0)}">
																			<form:option value="${districtTraditionalList.localBodyCode}" htmlEscape="true"> <esapi:encodeForHTMLAttribute>${districtTraditionalList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute> 
																			</form:option>
																		</c:if>
																		<c:if test="${districtTraditionalList.operation_state =='F'.charAt(0)}">
																			<form:option value="${districtTraditionalList.localBodyCode}" disabled="true" htmlEscape="true"> <esapi:encodeForHTMLAttribute>${districtTraditionalList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute> 																				
																			</form:option>
																		</c:if>
																	</c:forEach>
															</form:select> &nbsp;<%-- <span><form:errors
																	path="lgd_LBDistrictPatInterforExist" class="errormsg"></form:errors>
														</span> &nbsp;&nbsp;<span class="errormsg"
															id="ddlgdLBDistrictAtInterforExist_error"> </span> <br />
															<br /> --%>

															<div id="ddlgdLBDistrictAtInterforExist_error" class="error">
																<spring:message code="error.blank.ruralLBTypeName" htmlEscape="true"></spring:message>
															</div>
															<div class="errormsg" id="ddlgdLBDistrictAtInterforExist_error1">
																<form:errors path="lgd_LBDistrictPatInterforExist" htmlEscape="true" />
															</div>
															<div class="errormsg" id="ddlgdLBDistrictAtInterforExist_error2" style="display: none"></div>
																
																</li>
																<li><label><spring:message htmlEscape="true"
																	code="Label.SELECT"></spring:message>&nbsp;&nbsp; <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureInterTrade}</esapi:encodeForHTML> </label><span
															class="errormsg">*</span><br /> <form:select
																path="lgd_LBIntermediatePanchaytforExist"
																class="combofield" htmlEscape="true"
																id="ddlgdLBIntermediatePanchayatforExist"
																>
																<form:option value="0" htmlEscape="true">
																	<spring:message htmlEscape="true"
																		code="Label.SELECTINTERMEDIATELOCBODY"></spring:message>
																</form:option>
															</form:select> &nbsp;<%-- <span><form:errors
																	path="lgd_LBIntermediatePanchaytforExist"
																	class="errormsg"></form:errors> </span> &nbsp;&nbsp;<span
															class="errormsg"
															id="ddlgdLBIntermediatePanchayatforExist_error"> </span>
															<br /> <br /> --%> <%--<div id="ddlgdLBIntermediatePanchayatforExist_error" class="error"><spring:message code="error.blank.ruralLBTypeName" htmlEscape="true"></spring:message></div>--%>
															<div class="errormsg"
																id="ddlgdLBIntermediatePanchayatforExist_error1">
																<form:errors path="lgd_LBIntermediatePanchaytforExist"
																	htmlEscape="true" />
															</div>
															<div class="errormsg"
																id="ddlgdLBIntermediatePanchayatforExist_error2"
																style="display: none"></div></li>
																<li></li>
														
														</ul>
												
												
												</div>	
													
												<div class="clear"></div>
											</div>
										
										
										</li>
										<li>
																	<div id="divVillagePforExist" style="visibility: hidden; display: none;">
												<!-- tr_intermediate id change -->
													
												<div >
															<ul>
																<li>
																
																<label><spring:message htmlEscape="true" code="Label.SELECT"></spring:message>&nbsp;&nbsp; <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureDistTrade}</esapi:encodeForHTML> </label><span class="errormsg">*</span><br /> <form:select
																path="lgd_LBDistrictAtVillageforExist" class="combofield" id="ddlgdLBDistrictAtVillageforExist" onchange="getInterPanchayatAtVillagebyDcodeforExist(this.value);" htmlEscape="true">
																<form:option value="0" htmlEscape="true">
																	<spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
																</form:option>
																<%-- <form:options items="${districtTraditionalList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" /> --%>
																
																	<c:forEach items="${districtTraditionalList}" var="districtTraditionalList">
																		<c:if test="${districtTraditionalList.operation_state =='A'.charAt(0)}">
																			<form:option value="${districtTraditionalList.localBodyCode}" htmlEscape="true"> <esapi:encodeForHTMLAttribute>${districtTraditionalList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute>
																			</form:option>
																		</c:if>
																		<c:if test="${districtTraditionalList.operation_state =='F'.charAt(0)}">
																			<form:option value="${districtTraditionalList.localBodyCode}" disabled="true" htmlEscape="true"><esapi:encodeForHTMLAttribute>${districtTraditionalList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute>																				
																			</form:option>
																		</c:if>
																	</c:forEach>
																
															</form:select> &nbsp;<%-- <span><form:errors
																	path="lgd_LBDistrictAtVillageforExist" class="errormsg"></form:errors>
														</span> &nbsp;&nbsp;<span class="errormsg"
															id="ddlgdLBDistrictAtVillageforExist_error"> </span> <br /> --%> <br />
															<div id="ddlgdLBDistrictAtVillageforExist_error" class="error">
																<spring:message code="error.SELECTHDCOUNCIL" htmlEscape="true"></spring:message>
															</div>
															<div class="errormsg" id="ddlgdLBDistrictAtVillageforExist_error1">
																<form:errors path="lgd_LBDistrictAtVillageforExist" htmlEscape="true" />
															</div>
															<div class="errormsg" id="ddlgdLBDistrictAtVillageforExist_error2" style="display: none"></div></li>
																<li>
																		<label><spring:message htmlEscape="true" code="Label.SELECT"></spring:message>&nbsp;&nbsp; <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureInterTrade}</esapi:encodeForHTML></label> <span class="errormsg">*</span><br /> <form:select
																path="lgd_LBIntermediateAtVillageforExist" class="combofield" id="ddlgdLBInterAtVillageforExist"  onchange="getVillagePanchayatAtVillagebyDcodeforExist(this.value);" htmlEscape="true">
																<form:option value="0" htmlEscape="true">
																	<spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
																</form:option>
																<%-- <form:options items="${districtTraditionalList}"
																	itemLabel="localBodyNameEnglish"
																	itemValue="localBodyCode" /> --%>
															</form:select> &nbsp;<%-- <span><form:errors
																	path="lgd_LBIntermediateAtVillageforExist"
																	class="errormsg"></form:errors> </span> &nbsp;&nbsp;<span
															class="errormsg" id="ddlgdLBInterAtVillageforExist_error">

														</span> <br /> <br /> --%>
															<div id="ddlgdLBInterAtVillageforExist_error" class="error">
																<spring:message code="error.SELECTBACOUNCIL" htmlEscape="true"></spring:message>
															</div>
															<div class="errormsg" id="ddlgdLBInterAtVillageforExist_error1">
																<form:errors path="lgd_LBIntermediateAtVillageforExist" htmlEscape="true" />
															</div>
															<div class="errormsg" id="ddlgdLBInterAtVillageforExist_error2" style="display: none"></div>
																</li>
																<li>
																		<label><spring:message htmlEscape="true" code="Label.SELECT"></spring:message>&nbsp;&nbsp; <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureVillageTrade}</esapi:encodeForHTML> </label><span class="errormsg">*</span><br /> <form:select
																path="lgd_LBVillagePanchaytforExist" class="combofield" id="ddlgdLBVillagePanchayatforExist" htmlEscape="true">
																<form:option value="0" htmlEscape="true">
																	<spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
																</form:option>
															</form:select> &nbsp;<%-- <span><form:errors
																	path="lgd_LBVillagePanchaytforExist" class="errormsg"></form:errors>
														</span> &nbsp;&nbsp;<span class="errormsg"
															id="ddlgdLBVillagePanchayatforExist_error"> </span> <br />
															<br /> --%>

															<div id="ddlgdLBVillagePanchayatforExist_error" class="error">
																<spring:message code="error.SELECTVCOUNCIL" htmlEscape="true"></spring:message>
															</div>
															<div class="errormsg" id="ddlgdLBVillagePanchayatforExist_error1">
																<form:errors path="lgd_LBVillagePanchaytforExist" htmlEscape="true" />
															</div>
															<div class="errormsg" id="ddlgdLBVillagePanchayatforExist_error2" style="display: none"></div>
																
																</li>
																<li></li>
																
															
															</ul>
															
															<div class="clear"></div>
												
												</div>
													
													
												

											</div>
										
										
										</li>
										<li></li>
								
								</ul>
										<div class="clear"></div>
							</div>
						</div>

						<div id="divdeclareNewTLB" class="frmpnlbg">

							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message htmlEscape="true" code="Label.DECNEWTLB"></spring:message>
								</div>
								<div >
									<ul class="blocklist">
											<li>
												<label><spring:message htmlEscape="true" code="Label.LOCALBODYNAMEENGLISH"></spring:message></label><span id="required" class="errormsg"></span><span class="errormsg">*</span><br /> <form:input path="localBodyNameInEn"
												id="txtlocalBodyNameInEn" size="40" cssClass="frmfield" onkeypress="validateAlphanumericKeys();" />
											<div id="txtlocalBodyNameInEn_error" class="error">
												<spring:message code="error.blank.localBodyNameInEn" htmlEscape="true"></spring:message>
											</div>
											<div class="errormsg" id="txtlocalBodyNameInEn_error1">
												<form:errors path="localBodyNameInEn" htmlEscape="true" />
											</div>
											<div class="errormsg" id="txtlocalBodyNameInEn_error2" style="display: none"></div>
											</li>
											<li><label><spring:message htmlEscape="true" code="Label.LOCALBODYNAMELOCAL"></spring:message></label> <br /> <form:input path="localBodyNameInLcl" id="txtlocalBodyNameInLcl" size="40" cssClass="frmfield"
												onkeypress="validateAlphanumericKeys();" /></li>
											<li>
												<label><spring:message htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message></label><br /> <form:input path="localBodyAliasInEn" id="txtlocalBodyNmeInAlias" size="40" cssClass="frmfield"
												onkeypress="validateAlphanumericKeys();" />
											</li>
											<li>
												<label><spring:message htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message></label><br /> <form:input path="localBodyAliasInLcl" id="txtlocalBodyliasInLcl" size="40" cssClass="frmfield"
												onkeypress="validateAlphanumericKeys();" />
											</li>
											<li>
													<label><spring:message htmlEscape="true" code="Label.TRIBALLOCALBODYTYPE"></spring:message></label><span class="errormsg">*</span><br /> <form:select id="ddTribalLocalBodyTypeNew" path="traditionalLgTypeNameNew"
												size="1" class="combofield" onchange="hideShowDivforNewTLBFinal(this.value);" htmlEscape="true">
												<form:option value="0" htmlEscape="true">
													<spring:message htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message>
												</form:option>

												<c:forEach var="traditionalocalBodyTypelist" varStatus="var" items="${traditionalocalBodyTypelist}">
													<form:option id="typeCode" value="${traditionalocalBodyTypelist.localBodyTypeCode}:${traditionalocalBodyTypelist.level}" htmlEscape="true"><esapi:encodeForHTMLAttribute>${traditionalocalBodyTypelist.nomenclatureEnglish}</esapi:encodeForHTMLAttribute></form:option>
												</c:forEach>

											</form:select> <%-- <span id="ddTribalLocalBodyTypeNew_error" class="errormsg">
											</span> <form:errors path="traditionalLgTypeNameNew" class="errormsg"></form:errors> --%>

											<div id="ddTribalLocalBodyTypeNew_error" class="error">
												<spring:message code="error.blank.ruralLBTypeName" htmlEscape="true"></spring:message>
											</div>
											<div class="errormsg" id="ddTribalLocalBodyTypeNew_error1">
												<form:errors path="traditionalLgTypeNameNew" htmlEscape="true" />
											</div>
											<div class="errormsg" id="ddTribalLocalBodyTypeNew_error2" style="display: none"></div>
											</li>
											<li>
														<div id="divDistrictPforNew" style="visibility: hidden; display: none;">
												<!--  tr_district id name Change-->
													<ul class="blocklist">
															<li>
																	<label><spring:message htmlEscape="true" code="Label.SELECT"></spring:message> <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureDistTrade}</esapi:encodeForHTML> </label><span class="errormsg">*</span><br /> <form:select
																path="lgd_LBDistrictforExistNew" class="combofield" id="ddlgdLBDistrictPforExistNew"  items="${districtTraditionalList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" htmlEscape="true">
																<form:option value="0" htmlEscape="true">
																	<spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
																</form:option>
																<form:options items="${districtTraditionalList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" htmlEscape="true"/>
															</form:select> &nbsp;<span><form:errors path="lgd_LBDistrictforExistNew" class="errormsg"></form:errors> </span> &nbsp;&nbsp;<span class="errormsg" id="ddlgdLBDistrictPforExist_error"> </span><br /> <br />
															</li>
															
													
													</ul>
												
												
											</div>
											
											</li>
											
											<li>
										<div id="divLgdSelIntermediatePforNew" style="visibility: hidden; display: none;"> 
												<!--  tr_district id name Change-->
												
												<ul class="blocklist">
														<li><label><spring:message htmlEscape="true" code="Label.SELECT"></spring:message> <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureDistTrade}</esapi:encodeForHTML> </label><span class="errormsg">*</span><br /> <form:select
																path="lgd_LBDistrictAtInterforNew" class="combofield" id="ddlgdLBDistAtInterforNew" onchange="getInterPanchayatAtInterbyDcodeforExist(this.value);" htmlEscape="true">
																<%--ddSourceLocalBody id name changed  --%>
																<form:option value="0" htmlEscape="true">
																	<spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
																</form:option>
																<%-- <form:options items="${districtTraditionalList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" /> --%>
																
																	<c:forEach items="${districtTraditionalList}" var="districtTraditionalList">
																		<c:if test="${districtTraditionalList.operation_state =='A'.charAt(0)}">
																			<form:option value="${districtTraditionalList.localBodyCode}" htmlEscape="true"><esapi:encodeForHTMLAttribute>${districtTraditionalList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute>
																			</form:option>
																		</c:if>
																		<c:if test="${districtTraditionalList.operation_state =='F'.charAt(0)}">
																			<form:option value="${districtTraditionalList.localBodyCode}" disabled="true" htmlEscape="true"><esapi:encodeForHTMLAttribute>${districtTraditionalList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute>																				
																			</form:option>
																		</c:if>
																	</c:forEach>
															</form:select> &nbsp;<%-- <span><form:errors
																	path="lgd_LBDistrictAtInterforNew" class="errormsg"></form:errors>
														</span> &nbsp;&nbsp;<span class="errormsg"
															id="ddlgdLBDistAtInterforNew_error"> </span><br /> <br /> --%> <%--<div id="ddlgdLBDistAtInterforNew_error" class="error"><spring:message code="error.blank.ruralLBTypeName" htmlEscape="true"></spring:message></div>--%>
															<div class="errormsg" id="ddlgdLBDistAtInterforNew_error1">
																<form:errors path="lgd_LBDistrictAtInterforNew" htmlEscape="true" />
															</div>
															<div class="errormsg" id="ddlgdLBDistAtInterforNew_error2" style="display: none"></div>
														</li>
														<li>
																	<label><spring:message htmlEscape="true" code="Label.SELECT"></spring:message>&nbsp;&nbsp; <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureInterTrade}</esapi:encodeForHTML> </label><span class="errormsg">*</span><br /> <form:select
																path="lgd_LBIntermediatePanchaytforExist" class="combofield" id="ddlgdLBIntermediatePanchayatforNew" htmlEscape="true">
																<form:option value="0" htmlEscape="true">
																	<spring:message htmlEscape="true" code="Label.SELECTINTERMEDIATELOCBODY"></spring:message>
																</form:option>
															</form:select> &nbsp;<%-- <span><form:errors
																	path="lgd_LBIntermediatePanchaytforExist"
																	class="errormsg"></form:errors> </span> &nbsp;&nbsp;<span
															class="errormsg"
															id="ddlgdLBIntermediatePanchayatforExist_error"> </span>
															<br /> <br /> --%> <%--<div id="ddlgdLBIntermediatePanchayatforExist_error" class="error"><spring:message code="error.blank.ruralLBTypeName" htmlEscape="true"></spring:message></div>--%>
															<div class="errormsg" id="ddlgdLBIntermediatePanchayatforExist_error1">
																<form:errors path="lgd_LBIntermediatePanchaytforExist" htmlEscape="true" />
															</div>
															<div class="errormsg" id="ddlgdLBIntermediatePanchayatforExist_error2" style="display: none"></div>
														</li>
														<li></li>
												
												</ul>
										
											</div>
											</li>
											
											<li>
											<div id="divLgdVillagePforNew" style="visibility: hidden; display: none;"> 
																
													<ul class="blocklist">
															<li>
																			<label><spring:message htmlEscape="true" code="Label.SELECT"></spring:message>&nbsp;&nbsp; <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureDistTrade}</esapi:encodeForHTML> </label><span class="errormsg">*</span><br /> <form:select
																path="lgd_LBDistrictAtVillageforNew" class="combofield" id="ddlgdLBDistrictAtVillageforNew" onchange="getIntermediatePanchayatbyDcodeforNew(this.value);" htmlEscape="true">
																<form:option value="0" htmlEscape="true">
																	<spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
																</form:option>
																<%-- <form:options items="${districtTraditionalList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" /> --%>
																
																	<c:forEach items="${districtTraditionalList}" var="districtTraditionalList">
																		<c:if test="${districtTraditionalList.operation_state =='A'.charAt(0)}">
																			<form:option value="${districtTraditionalList.localBodyCode}" htmlEscape="true"><esapi:encodeForHTMLAttribute>${districtTraditionalList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute>
																			</form:option>
																		</c:if>
																		<c:if test="${districtTraditionalList.operation_state =='F'.charAt(0)}">
																			<form:option value="${districtTraditionalList.localBodyCode}" disabled="true" htmlEscape="true"><esapi:encodeForHTMLAttribute>${districtTraditionalList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute>																				
																			</form:option>
																		</c:if>
																	</c:forEach>
															</form:select> &nbsp<%-- ;<span><form:errors
																	path="lgd_LBDistrictAtVillageforNew" class="errormsg"></form:errors>
														</span> &nbsp;&nbsp;<span class="errormsg"
															id="ddlgdLBDistrictAtVillageforNew_error"> </span> <br />
															<br /> --%>
															<div id="ddlgdLBDistrictAtVillageforNew_error" class="error">
																<spring:message code="error.SELECTHDCOUNCIL" htmlEscape="true"></spring:message>
															</div>
															<div class="errormsg" id="ddlgdLBDistrictAtVillageforNew_error1">
																<form:errors path="lgd_LBDistrictAtVillageforNew" htmlEscape="true" />
															</div>
															<div class="errormsg" id="ddlgdLBDistrictAtVillageforNew_error2" style="display: none"></div>
															</li>
															<li>
																		<label><spring:message htmlEscape="true" code="Label.SELECT"></spring:message>&nbsp;&nbsp; <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureInterTrade}</esapi:encodeForHTML> </label><span class="errormsg">*</span><br /> <form:select
																path="lgd_LBIntermediateAtVillageforNew" class="combofield" id="ddlgdLBInterAtVillageforNew" htmlEscape="true">
																<form:option value="0" htmlEscape="true">
																	<spring:message htmlEscape="true" code="Label.SELECTINTERMEDIATELOCBODY"></spring:message>
																</form:option>
															</form:select>
															<div id="ddlgdLBInterAtVillageforNew_error" class="error">
																<spring:message code="error.SELECTBACOUNCIL" htmlEscape="true"></spring:message>
															</div>
															<div class="errormsg" id="ddlgdLBInterAtVillageforNew_error1">
																<form:errors path="lgd_LBIntermediateAtVillageforNew" htmlEscape="true" />
															</div>
															<div class="errormsg" id="ddlgdLBInterAtVillageforNew_error2" style="display: none"></div>
															</li>
															<li></li>
													
													</ul>			
																
											
											</div>
											</li>
											
									</ul>
									
									</div>
								
								<div class="clear"></div>
							</div>
						</div>

						<div id="divPreviousButtons" class="btnpnl" align="center">
							<input type="button" value="<spring:message htmlEscape="true"  code="Button.PREVIOUS"></spring:message>" onclick="onPreviousClick();" class="btn" />
						</div>
						<div class="btnpnl" id="divSaveButtons">
								<input type="button" value="<spring:message htmlEscape="true"  code="Button.PREVIOUS"></spring:message>" onclick="onPreviousClick();" class="btn" /> <label> <input type="submit" name="Submit"
												onclick="return validateFinalAllRLBtoTLB();" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" />
											</label> <label> <input type="button" class="btn" name="Submit6" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
											</label>
										</div>
							
													</div>

					</div>
				</div>
			</form:form>
		</div>
	</div>

</body>
</html>