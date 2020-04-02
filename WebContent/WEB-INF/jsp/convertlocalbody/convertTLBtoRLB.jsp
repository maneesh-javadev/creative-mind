<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<title>E-Panchayat</title>

	<script src="js/convertTLBtoRLB.js"></script>

	<script src="js/govtorder.js"></script>
	<script src="js/common.js"></script>
	<script src="js/successMessage.js"></script>
	<script src="js/lgd_localbody.js"></script>

	<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
		


	<script type="text/javascript" language="javascript">
		dwr.engine.setActiveReverseAjax(true);
	</script>
</head>

<body onload="loadConvertTLBRLBPage();" 
	onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);">

	<div id="frmcontent">
		<div class="frmhd">
						<h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.CONVERSIONTLBRLB"></spring:message></h3>
										 <ul id="showhelp" class="listing">
					 				       <!--  //this link is not working  <li>
									 				 <a href="#" rel="toggle[cat]"
												data-openimage="images/minus.jpg"
												data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
													border="0" /> </a>
											</li> -->
					 				        
					 				        <%--//these links are not working <li>
					 				        			<a href="#" class="frmhelp"><spring:message htmlEscape="true" 	code="Button.HELP"></spring:message> </a>
					 				        </li> --%>
					 	
					 			        </ul>
			
		
		
		
			
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">

			<form:form commandName="convertTLBtoRLBForm" action="draftConversionTLBtoRLB.htm" method="POST" enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="draftConversionTLBtoRLB.htm"/>" />
			
				<div id="cat">
					<div class="frmpnlbg" id="divFirstPanel">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"  code="Label.CONVERTTLBTORLB"></spring:message>
							</div>
							<input type="hidden" class="btn" value="<c:out value='${stateCode}' escapeXml='true'></c:out>" id="hdnState" name="hdnState" />
							
							<div >
							
									<ul class="blocklist">
											<li >
																	<label><spring:message htmlEscape="true" 
																	code="Label.SELECTLOCALBODYTYPE"></spring:message></label><span
																class="errormsg">*</span><br /> <form:select
																	path="rurallbTypeName" id="ddLgdLBType" class="combofield"
																	onchange="getHideShowTLBToRLBList(this.value)">
																	<!--tierSetupCode id Changed  -->
																	<form:option value="0">
																		<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message>
																	</form:option>
						
																	<c:forEach var="traditionalocalBodyTypelist" varStatus="var"
																		items="${traditionalocalBodyTypelist}">
																		<form:option id="typeCode"
																			value="${traditionalocalBodyTypelist.localBodyTypeCode}:${traditionalocalBodyTypelist.level}"><c:out value="${traditionalocalBodyTypelist.nomenclatureEnglish}" escapeXml="true"></c:out></form:option>
																	</c:forEach>
																</form:select>&nbsp;&nbsp; <%-- <span> <form:errors path="rurallbTypeName"
																		class="errormsg"></form:errors> </span> &nbsp;&nbsp; <span
																class="errormsg" id="ddLgdLBType_error"><spring:message htmlEscape="true" 
																		code="error.blank.ruralLBTypeName"></spring:message> </span> --%>
																		
																	<div id="ddLgdLBType_error" class="error"><spring:message code="error.blank.ruralLBTypeName" htmlEscape="true"></spring:message></div>
																	<div class="errormsg" id="ddLgdLBType_error1"><form:errors path="rurallbTypeName" htmlEscape="true"/></div>  
																	<div class="errormsg" id="ddLgdLBType_error2" style="display: none" ></div>
																	
											</li>
											<li>
																					<div id="divTraditionalDistrictPanchayat">
										
														<div >
														
															<ul class="blocklist">
															
																		<li  >
																						<div id="ddLgdLBDistPDestList_error" class="error"><spring:message code="error.blank.DISTRICTP" htmlEscape="true"></spring:message></div>
																						<div class="errormsg" id="ddLgdLBDistPDestList_error1"><form:errors path="rurallbTypeName" htmlEscape="true"/></div>  
																						<div class="errormsg" id="ddLgdLBDistPDestList_error2" style="display: none" ></div>
																						
																		
																		</li>
																		<li>
																					<div class="ms_container">
																								<div class="ms_selectable">
																												<label><spring:message htmlEscape="true" 
																														code="Label.AVAILABLE"></spring:message>
																													&nbsp;<c:out value="${convertTLBtoRLBForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out> &nbsp;<spring:message htmlEscape="true" 
																														code="Label.LIST"></spring:message> </label><br /> <form:select
																													path="lgd_LBDistPSourceList" class="frmtxtarea"
																													id="ddLgdLBDistPSourceList"
																													items="${districtTraditionalList}"
																													itemLabel="localBodyNameEnglish"
																													itemValue="localBodyCode"
																													multiple="true">
														
																												</form:select><br /> <br />
																								</div>
																								<div class="ms_buttons">
																													<input type="button" class="bttn"
																														value="<spring:message
																																code="Button.WHOLE"/>" 
																														onclick="addItemforLB('ddLgdLBDistPDestList','ddLgdLBDistPSourceList','FULL',true);" />
																														 <input type="button" class="bttn" id="btnremoveOneULB"
																														name="Submit4" value="Back &lt;"
																														onclick="removeItem('ddLgdLBDistPDestList','ddLgdLBDistPSourceList',true)" />
																														<input type="button" class="bttn" value="<spring:message code="Button.RESET"></spring:message>"
																														onclick="removeAll('ddLgdLBDistPDestList', 'ddLgdLBDistPSourceList', true)" />
																														<input type="button" class="bttn" value="Part &gt;&gt;"
																														onclick="addItemforLB('ddLgdLBDistPDestList','ddLgdLBDistPSourceList', 'PART',true);" />
																								</div>
																								<div class="ms_selection">
																															<label><spring:message htmlEscape="true"  code="Label.CONTRIBUTE"></spring:message>
																															&nbsp;<c:out value="${convertTLBtoRLBForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out> &nbsp;<spring:message htmlEscape="true" 
																																code="Label.LIST"></spring:message> </label> <br /> <form:select
																															id="ddLgdLBDistPDestList" size="1" multiple="true"
																															path="lgd_LBDistPDestList" class="frmtxtarea"
																															>
																														</form:select><br /> &nbsp;&nbsp;&nbsp; <input type="button"
																														value="<spring:message htmlEscape="true" code="Button.GETDISTRICTL"/>"
																														 onclick="selectIntermediateLocalBodyListAtDCA();" />
																								</div>																		
																					</div>
																		
																		</li>
																		<li>
																						<div class="errormsg" id="ddLgdLBDistCAreaDestL_error1"><form:errors path="lgd_LBDistCAreaDestList" htmlEscape="true"/></div>  
																						<div class="errormsg" id="ddLgdLBDistCAreaDestL_error2" style="display: none" ></div>
																		
																		</li>
																		<li>
																				<div class="clear"></div>
																		</li>
																		
																		<li>
																				<div class="ms_container">
																							<div class="ms_selectable">
																													<label><spring:message htmlEscape="true" 
																													code="Label.SELINTERMPANCHYAT"></spring:message> </label><br />
																											<form:select path="lgd_LBDistCAreaSourceList"
																												class="frmtxtarea" id="ddIntermediateLbSourceL"
																												multiple="true">
													
																											</form:select><br /> <br />
																							</div>
																							<div class="ms_buttons">
																												<input type="button" class="bttn"
																												value="<spring:message
																														code="Button.WHOLE"/>" 
																												onclick="addItemforVillageLocalBody('ddIntermediateLbDesteL','ddIntermediateLbSourceL','FULL',true);" />
																												 <input type="button" class="bttn" id="btnremoveOneULB"
																												name="Submit4" value="Back &lt;"
																												onclick="removeItem('ddLgdLBDistCAreaDestL','ddLgdLBDistCAreaSourceL',true)" />
																												<input type="button" class="bttn" value="<spring:message code="Button.RESET"></spring:message>"
																												onclick="removeAll('ddLgdLBDistCAreaDestL', 'ddLgdLBDistCAreaSourceL', true)" />
																																									
																																									
																							</div>
																							<div class="ms_selection">
																							
																											<label><spring:message htmlEscape="true" 
																												code="Label.SELECTEDINTERRLBS"></spring:message> </label> <br />
																										<form:select name="select6" id="ddIntermediateLbDesteL"
																											size="1" multiple="true" path="lgd_LBDistCAreaDestList"
																											class="frmtxtarea" >
																										</form:select><br /> &nbsp;&nbsp;&nbsp; 	
																																			
													
																							</div>																		
																				</div>
							
																				
																		</li>
															</ul>
														
														<div class="clear"></div>
														</div>
										
										
										</div>
											
											</li>
											<li>
											
																		<div id="divTraditionalIntermediatePanchayat" style="visibility: hidden; display: none;">
										
												<div>
														<ul class="blocklist">
														
																<li >
																		<label> <spring:message htmlEscape="true"  code="Label.AVAILABLE"></spring:message>
																			&nbsp;<c:out value="${convertTLBtoRLBForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out> &nbsp;<spring:message htmlEscape="true" 
																				code="Label.LIST"></spring:message> </label><br /> <form:select
																			path="lgd_LBDistListAtInterCA" 
																			onchange="getInterPanchayatbyDcodeAtICA(this.value);"
																			class="combofield" id="ddLgdLBDistListAtInterCA">
																			<form:option value="0">
																				<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
																			</form:option>
																			<form:options items="${districtTraditionalList}"
																				itemLabel="localBodyNameEnglish"
																				itemValue="localBodyCode" />
																		</form:select>&nbsp;&nbsp;<%-- <form:errors path="lgd_LBDistListAtInterCA"
																			class="errormsg"></form:errors> &nbsp;&nbsp; <span
																		class="errormsg" id="ddLgdLBDistListAtInterCA_error"><spring:message htmlEscape="true" 
																				code="error.SOURCESELECTLOCALBODYPARENT"></spring:message></span> --%>
																				
																		<div id="ddLgdLBDistListAtInterCA_error" class="error"><spring:message code="error.SOURCESELECTLOCALBODYPARENT" htmlEscape="true"></spring:message></div>
																		<div class="errormsg" id="ddLgdLBDistListAtInterCA_error1"><form:errors path="lgd_LBDistListAtInterCA" htmlEscape="true"/></div>  
																		<div class="errormsg" id="ddLgdLBDistListAtInterCA_error2" style="display: none" ></div>	
																</li>
																<li>
																				<div class="errormsg" id="ddLgdLBInterPDestList_error1"><form:errors path="lgd_LBInterPDestList" htmlEscape="true"/></div>  
																				<div class="errormsg" id="ddLgdLBInterPDestList_error2" style="display: none" ></div> 
													
																
																</li>
																<li>
																				<div class="ms_container">
																							<div class="ms_selectable">
																											<label><spring:message htmlEscape="true" 
																										code="Label.AVAILABLE"></spring:message>
																									&nbsp;<c:out value="${convertTLBtoRLBForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out> &nbsp;<spring:message htmlEscape="true" 
																										code="Label.LIST"></spring:message> </label><br /> <form:select
																									path="lgd_LBInterPSourceList" class="frmtxtarea"
																									id="ddLgdLBInterPSourceList"
																									 multiple="true">
										
																								</form:select><br /> <br />
																							</div>
																							<div class="ms_buttons">
																												<input type="button" class="bttn"
																												value="<spring:message
																														code="Button.WHOLE"/>" 
																												onclick="addItemforLB('ddLgdLBInterPDestList','ddLgdLBInterPSourceList','FULL',true);" />
																												 <input type="button" class="bttn" id="btnremoveOneULB"
																												name="Submit4" value="Back &lt;"
																												onclick="removeItem('ddLgdLBInterPDestList','ddLgdLBInterPSourceList',true)" />
																												<input type="button" class="bttn" value="<spring:message code="Button.RESET"></spring:message>"
																												onclick="removeAll('ddLgdLBDistPDestList', 'ddLgdLBInterPSourceList', true)" />
																												<input type="button" class="bttn" value="Part &gt;&gt;"
																												onclick="addItemforLB('ddLgdLBInterPDestList','ddLgdLBInterPSourceList', 'PART',true);" />
																							</div>
																							<div class="ms_selection">
																												<label><spring:message htmlEscape="true"  code="Label.CONTRIBUTE"></spring:message>
																														&nbsp;<c:out value="${convertTLBtoRLBForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out> &nbsp;<spring:message htmlEscape="true" 
																															code="Label.LIST"></spring:message> </label> <br /> <form:select
																														name="select6" id="ddLgdLBInterPDestList" size="1"
																														multiple="true" path="lgd_LBInterPDestList"
																														class="frmtxtarea" >
																													</form:select><br />&nbsp;&nbsp;&nbsp; <input type="button"
																													value="<spring:message htmlEscape="true"  code="Button.GETSUBDISTRICTL"/>"
																													onclick="selectVillageLocalBodyListAtICA();" />
																							</div>																		
																				</div>
							
																
																</li>
																<li>
																
																	<div class="clear"></div>
																
																</li>
																<li>
																			<div class="ms_container">
																						<div class="ms_selectable">
																										<label><spring:message htmlEscape="true" 
																									code="Label.AVAILVILLAGEPANCHAYATLIST"></spring:message> </label><br />
																							<form:select path="lgd_LBInterCAreaSourceList"
																								class="frmtxtarea" id="ddLgdLBVillageInterCAreaSourceL"
																								multiple="true">
									
																							</form:select><br /> <br />
																						</div>
																						<div class="ms_buttons">
																													<input type="button" class="bttn"
																							value="<spring:message
																									code="Button.WHOLE"/>" 
																							onclick="addItemforLB('ddLgdLBVillageInterCAreaDestL','ddLgdLBVillageInterCAreaSourceL','FULL',true);" />
																							<input type="button" class="bttn" id="btnremoveOneULB"
																							name="Submit4" value="Back &lt;"
																							onclick="removeItem('ddLgdLBVillageInterCAreaDestL','ddLgdLBVillageInterCAreaSourceL',true)" />
																							<input type="button" class="bttn" value="<spring:message code="Button.RESET"></spring:message>"
																							onclick="removeAll('ddLgdLBVillageInterCAreaDestL', 'ddLgdLBVillageInterCAreaSourceL', true)" />
																															
													
																						</div>
																						<div class="ms_selection">
																											<label><spring:message htmlEscape="true" 
																												code="Label.CONTRIBUTVILLAGEPANCHAYATLIST"></spring:message>
																									</label> <br /> <form:select name="select6"
																											id="ddLgdLBVillageInterCAreaDestL" size="1" multiple="true"
																											path="lgd_LBInterCAreaDestList" class="frmtxtarea"
																											>
																										</form:select><br /> &nbsp;&nbsp;&nbsp;
																						</div>																		
																			</div>
																			
																			
																</li>
														
														</ul>
														<div class="clear"></div>
												
												</div>
										
											
										</div>
											
											</li>
											<li>
																<div id="divTraditionalVillagePanchayat" style="visibility: hidden; display: none;">
										
													<div>
														<ul class="blocklist">
														
																<li >
																						<label> <spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
																						&nbsp;<c:out value="${convertTLBtoRLBForm.lgd_LBNomenclatureDistTrade}" escapeXml="true"></c:out> &nbsp;<spring:message htmlEscape="true" 
																							code="Label.LIST"></spring:message> </label><span class="errormsg">*</span><br /> <form:select
																						path="lgd_LBDistListAtVillageCA" class="combofield"
																						id="ddLgdLBDistListAtVillageCA"
																						onchange="getInterPanchayatbyDcodeAtVCA(this.value);">
																						<form:option value="0">
																							<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
																						</form:option>
																						<c:forEach items="${districtTraditionalList}" var="districtTraditionalList">
																							<c:if test="${districtTraditionalList.operation_state =='A'.charAt(0)}">
																								<form:option value="${districtTraditionalList.localBodyCode}" ><c:out value="${districtTraditionalList.localBodyNameEnglish}" escapeXml="true"></c:out>
																								</form:option>
																							</c:if>
																							<c:if test="${districtTraditionalList.operation_state =='F'.charAt(0)}">
																								<form:option value="${districtTraditionalList.localBodyCode}" disabled="true"><c:out value="${districtTraditionalList.localBodyNameEnglish}" escapeXml="true"></c:out>																				
																								</form:option>
																							</c:if>
																						</c:forEach>
																		
																							
																					</form:select>&nbsp;&nbsp;<%-- <form:errors path="lgd_LBDistListAtVillageCA"
																						class="errormsg"></form:errors> &nbsp;&nbsp; <span
																					class="errormsg" id="ddLgdLBDistListAtVillageCA_error"><spring:message htmlEscape="true" 
																							code="error.SOURCESELECTLOCALBODYPARENT"></spring:message></span> --%>
																							
																					<div id="ddLgdLBDistListAtVillageCA_error" class="error"><spring:message code="error.SELECTHDCOUNCIL" htmlEscape="true"></spring:message></div>
																					<div class="errormsg" id="ddLgdLBDistListAtVillageCA_error1"><form:errors path="lgd_LBDistListAtVillageCA" htmlEscape="true"/></div>  
																					<div class="errormsg" id="ddLgdLBDistListAtVillageCA_error2" style="display: none" ></div>	
																
																</li>
																<li  >
																						<label> <spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
																						&nbsp;<c:out value="${convertTLBtoRLBForm.lgd_LBNomenclatureInterTrade}" escapeXml="true"></c:out> &nbsp;<spring:message htmlEscape="true" 
																							code="Label.LIST"></spring:message> </label><span class="errormsg">*</span><br /> <form:select
																						path="lgd_LBInterListAtVillageCA" class="combofield"
																						id="ddLgdLBInterListAtVillageCA" 
																						onchange="getVillagePanchbyIntercodeAtVCA(this.value);">
																						<form:option value="0">
																							<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
																						</form:option>
							
																					</form:select>
																					&nbsp;&nbsp;<%-- <form:errors path="lgd_LBInterListAtVillageCA"
																						class="errormsg"></form:errors> &nbsp;&nbsp; <span
																					class="errormsg" id="ddLgdLBInterListAtVillageCA_error"><spring:message htmlEscape="true" 
																							code="error.blank.INTERMEDIATEP"></spring:message></span> --%>
																					<div id="ddLgdLBInterListAtVillageCA_error" class="error"><spring:message code="error.SELECTBACOUNCIL" htmlEscape="true"></spring:message></div>
																					<div class="errormsg" id="ddLgdLBInterListAtVillageCA_error1"><form:errors path="lgd_LBInterListAtVillageCA" htmlEscape="true"/></div>  
																					<div class="errormsg" id="ddLgdLBInterListAtVillageCA_error2" style="display: none" ></div>				
																
																</li>
																<li   >
																						<div id="ddLgdLBVillageDestAtVillageCA_error" class="error"><spring:message code="error.SELECTVCOUNCIL" htmlEscape="true"></spring:message></div> 
																						<div class="errormsg" id="ddLgdLBVillageDestAtVillageCA_error1"><form:errors path="lgd_LBVillageDestAtVillageCA" htmlEscape="true"/></div>  
																						<div class="errormsg" id="ddLgdLBVillageDestAtVillageCA_error2" style="display: none" ></div> 
																						
																									
																</li>
																<li>
																					<div class="ms_container">
																								<div class="ms_selectable">
																														<label><spring:message htmlEscape="true" 
																												code="Label.AVAILABLE"></spring:message>
																											&nbsp;<c:out value="${convertTLBtoRLBForm.lgd_LBNomenclatureVillageTrade}" escapeXml="true"></c:out>
																											&nbsp;<spring:message htmlEscape="true"  code="Label.LIST"></spring:message>
																									</label><br /> <form:select path="lgd_LBVillageSourceAtVillageCA"
																											class="frmtxtarea" id="ddLgdLBVillageSourceAtVillageCA"
																											multiple="true">
												
																										</form:select><br /> 
																								</div>
																								<div class="ms_buttons">
																											<input type="button" class="bttn"
																											value="<spring:message
																													code="Button.WHOLE"/>" 
																											onclick="addItemVillageFinalS('ddLgdLBVillageDestAtVillageCA','ddLgdLBVillageSourceAtVillageCA','FULL',true);" />
																											 <input type="button" class="bttn" id="btnremoveOneULB"
																											name="Submit4" value="Back &lt;"
																											onclick="removeItem('ddLgdLBVillageDestAtVillageCA','ddLgdLBVillageSourceAtVillageCA',true)" />
																											<input type="button" class="bttn" value="<spring:message code="Button.RESET"></spring:message>"
																											onclick="removeAll('ddLgdLBVillageDestAtVillageCA', 'ddLgdLBVillageSourceAtVillageCA', true)" />
																											<input type="button" class="bttn" value="Part &gt;&gt;"
																											onclick="addItemVillageFinalS('ddLgdLBVillageDestAtVillageCA','ddLgdLBVillageSourceAtVillageCA', 'PART',true);" />
																											
																								</div>
																								<div class="ms_selection">
																														<label><spring:message htmlEscape="true"  code="Label.CONTRIBUTE"></spring:message>
																																&nbsp;<c:out value="${convertTLBtoRLBForm.lgd_LBNomenclatureVillageTrade}" escapeXml="true"></c:out>
																																&nbsp;<spring:message htmlEscape="true" code="Label.LIST"></spring:message>
																														</label><span class="errormsg">*</span> <br /> <form:select name="select6"
																																id="ddLgdLBVillageDestAtVillageCA" size="1"
																																multiple="true" path="lgd_LBVillageDestAtVillageCA"
																																class="frmtxtarea" >
																															</form:select><br /> &nbsp;&nbsp;&nbsp; <input type="button"
																															value="<spring:message htmlEscape="true"  code="Button.GETVILLAGEL"/>"
																												 onclick="selectLocalBodyListAtVCA();" />
																								</div>																		
																					</div>
																
																</li>
																<li>
																			<div class="errormsg" id="ddLgdLBVillageCAreaDestL_error_error1"><form:errors path="lgd_LBVillageCAreaDestL" htmlEscape="true"/></div>  
																			<div class="errormsg" id="ddLgdLBVillageCAreaDestL_error_error2" style="display: none" ></div> 
													
																</li>
																
																<li>
																	<div class="clear"></div>
																</li>
																<li>
																				<div class="ms_container">
																							<div class="ms_selectable">
																											<label><spring:message htmlEscape="true" 
																															code="Label.AVAILVILLAGELIST"></spring:message> </label><br />
																													<form:select path="lgd_LBVillageCAreaSourceL"
																														class="frmtxtarea" id="ddLgdLBVillageCAreaSourceL"
																														 multiple="true">
															
																													</form:select><br /> <br />
																							</div>
																							<div class="ms_buttons">
																														<input type="button" class="bttn"
																														value="<spring:message
																																code="Button.WHOLE"/>" 
																														onclick="addItemforVillageG('ddLgdLBVillageCAreaDestL','ddLgdLBVillageCAreaSourceL','FULL',true);" />
																														 <input type="button" class="bttn" id="btnremoveOneULB"
																														name="Submit4" value="Back &lt;"
																														onclick="removeItem('ddLgdLBVillageCAreaDestL','ddLgdLBVillageCAreaSourceL',true)" />
																														<input type="button"  class="bttn" value="<spring:message code="Button.RESET"></spring:message>"
																														onclick="removeAll('ddLgdLBVillageCAreaDestL', 'ddLgdLBVillageCAreaSourceL', true)" />
																							</div>
																							<div class="ms_selection">
																															<label><spring:message htmlEscape="true" 
																																	code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label> <br />
																															<form:select name="select6" id="ddLgdLBVillageCAreaDestL"
																																size="1" multiple="true" path="lgd_LBVillageCAreaDestL"
																																class="frmtxtarea" >
																															</form:select><br />
																							</div>																		
																				</div>
																
																</li>
																<li>
																		<div class="clear"></div>
																</li>
														
														</ul>
													
													</div>
										
											
										</div>
											
											</li>
											<li></li>
											<li></li>
											<li></li>
											<li></li>
											<li></li>
											<li></li>
									
									</ul>
							<div class="clear"></div>
							
							</div>
							
						
						</div>
						<div class="btnpnl">
							<label> <input type="button" name="Submit"
												id="btnNext" onclick="return validateFirstAllTLBtoRLB();"
												value="<spring:message htmlEscape="true"  code="Button.NEXTSTEP"></spring:message>" />
											</label><label><input type="button" class="btn" name="Submit6"
												value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
												onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
						</div>
					</div>
					<div id="divSecondPanel" style="display: none">
						<div class="frmpnlbg">
							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message htmlEscape="true"  code="Label.SELPRIBALLOCALBODY"></spring:message>
								</div>
								
								<div >
										<ul class="blocklist">
										
												<li>
											<form:radiobutton path="mergeTLBtoRLB"	id="rdmergeTLBtoRLB" value="M"	onclick="radioClick(this.value,document.getElementById('ddLgdLBType').value);" />
												
											&nbsp; <spring:message htmlEscape="true"  code="Label.MERGETLBTORLB"></spring:message>
											
												</li>
												
												<li>
														<form:radiobutton path="declarenewRLB"	id="rddeclarenewRLB" onclick="radioClick(this.value,document.getElementById('ddLgdLBType').value);"	value="N" />
											 &nbsp; <spring:message htmlEscape="true" code="Label.DECLARENEWRLB"></spring:message>
												
												</li>
												<li>
															<span id="rdmergeRLBtoULB_error" class="errormsg"></span>
															<form:errors path="mergeTLBtoRLB" class="errormsg"></form:errors>
															<form:errors path="declarenewRLB" class="errormsg"></form:errors>
												</li>
												
										
										
										</ul>
										<div class="clear"></div>
								
								</div>
								
							
							</div>
						</div>
						<div class="frmpnlbg" id="divmergeRLB">
							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message htmlEscape="true"  code="Label.EXISTINGRLB"></spring:message>
								</div>
								
									<div >
											<ul class="blocklist">
													<li>
															<label><spring:message htmlEscape="true" 
																code="Label.SELECTLOCALBODYTYPE"></spring:message></label><span
															class="errormsg">*</span><br />
															<form:select id="ddRuralLBType" path="traditionalLbTypeName"
																size="1" class="combofield" 
																onchange="gethideShowDivforExistRLB(this.value);">
																	<form:option value="0">
																		<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message>
																	</form:option>
																	<c:forEach var="localbodyTypelist" varStatus="var" items="${localbodyTypelist}">
																	<form:option id="typeCode"
																		value="${localbodyTypelist.localBodyTypeCode}:${localbodyTypelist.level}"><c:out value="${localbodyTypelist.nomenclatureEnglish}" escapeXml="true"></c:out></form:option>
																	</c:forEach>
													
																</form:select> <%-- <span id="ddRuralLBType_error" class="errormsg">
																</span> <form:errors path="traditionalLbTypeName" class="errormsg"></form:errors> --%>
																<div id="ddRuralLBType_error" class="error"><spring:message code="error.blank.ruralLBTypeName" htmlEscape="true"></spring:message></div>
															 	<div class="errormsg" id="ddRuralLBType_error1"><form:errors path="traditionalLbTypeName" htmlEscape="true"/></div>  
																<div class="errormsg" id="ddRuralLBType_error2" style="display: none" ></div>				
													
													
													</li>
													<li>
																		<div id="divDistrictPforExist" style="visibility: hidden; display: none;">
												<!--  tr_district id name Change-->
												
												
																	<div   >
																			<ul class="blocklist">
																					<li>
																								<label><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
																							<c:out value="${convertTLBtoRLBForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out></label><span
																							class="errormsg">*</span><br /> <form:select
																								path="lgd_LBDistrictforExist" class="combofield"
																								id="ddlgdLBDistrictPforExist" 
																								items="${districtPanchayatList}"
																								itemLabel="localBodyNameEnglish"
																								itemValue="localBodyCode"> 
																								<form:option value="0">
																									<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
																								</form:option>
																								<%-- <form:options items="${districtTraditionalList}"
																									itemLabel="localBodyNameEnglish"
																									itemValue="localBodyCode" /> --%>
																							</form:select> &nbsp;<span><form:errors
																									path="lgd_LBDistrictforExist" class="errormsg"></form:errors>
																						</span> &nbsp;&nbsp;<span class="errormsg"
																							id="ddlgdLBDistrictPforExist_error"> </span><br /> <br />
																					</li>
																				
																			</ul>
																			<div class="clear"></div>
																	
																	</div>
																	
												
											</div>			
													
													</li>
													<li>
																						<div id="divIntermediatePforExist" style="visibility: hidden; display: none;">
												<!-- tr_intermediate id change -->
												
																						<div>
																								<ul class="blocklist">
																											<li>
																																			<label><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>&nbsp;&nbsp;<c:out value="${convertTLBtoRLBForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out></label><span
																																			class="errormsg">*</span><br /> <form:select
																																				path="lgd_LBDistrictPatInterforExist" class="combofield"
																																				id="ddlgdLBDistrictAtInterforExist"
																																				onchange="getInterPanchayatAtInterbyDcodeforExist(this.value);"
																																				>
																																				<form:option value="0">
																																					<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
																																				</form:option>
																																				<form:options items="${districtPanchayatList}"
																																					itemLabel="localBodyNameEnglish"
																																					itemValue="localBodyCode" />
																																					
																																			  
																																			  <c:forEach items="${districtPanchayatList}" var="districtPanchayatList">
																																				<c:if test="${districtPanchayatList.operation_state =='A'.charAt(0)}">
																																					<form:option value="${districtPanchayatList.localBodyCode}" ><c:out value="${districtPanchayatList.localBodyNameEnglish}" escapeXml="true"></c:out>
																																					</form:option>
																																				</c:if>
																																				<c:if test="${districtPanchayatList.operation_state =='F'.charAt(0)}">
																																					<form:option value="${districtPanchayatList.localBodyCode}" disabled="true"><c:out value="${districtPanchayatList.localBodyNameEnglish}" escapeXml="true"></c:out>																			
																																					</form:option>
																																				</c:if>
																																			</c:forEach>		
																																					
																																			</form:select> &nbsp;<%-- <span><form:errors
																																					path="lgd_LBDistrictPatInterforExist" class="errormsg"></form:errors>
																																		</span> &nbsp;&nbsp;<span class="errormsg"
																																			id="ddlgdLBDistrictAtInterforExist_error"> </span> <br />
																																			<br /> --%>
																																			
																																			<div id="ddlgdLBDistrictAtInterforExist_error" class="error"><spring:message code="error.blank.ruralLBTypeName" htmlEscape="true"></spring:message></div>
																																		 	<div class="errormsg" id="ddlgdLBDistrictAtInterforExist_error1"><form:errors path="lgd_LBDistrictPatInterforExist" htmlEscape="true"/></div>  
																																			<div class="errormsg" id="ddlgdLBDistrictAtInterforExist_error2" style="display: none" ></div>	
																																					
																											
																											</li>
																											<li>
																																			<label><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>&nbsp;&nbsp;<c:out value="${convertTLBtoRLBForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out></label><span
																																			class="errormsg">*</span><br /> <form:select
																																				path="lgd_LBIntermediatePanchaytforExist"
																																				class="combofield"
																																				id="ddlgdLBIntermediatePanchayatforExist"
																																				>
																																				<form:option value="0">
																																					<spring:message htmlEscape="true"  code="Label.SELECTINTERMEDIATELOCBODY"></spring:message>
																																				</form:option>
																																			</form:select> &nbsp;<%-- <span><form:errors
																																					path="lgd_LBIntermediatePanchaytforExist"
																																					class="errormsg"></form:errors> </span> &nbsp;&nbsp;<span
																																			class="errormsg"
																																			id="ddlgdLBIntermediatePanchayatforExist_error"> </span>
																																			<br /> <br /> --%>
																																			<%--<div id="ddlgdLBIntermediatePanchayatforExist_error" class="error"><spring:message code="error.blank.ruralLBTypeName" htmlEscape="true"></spring:message></div>--%>
																																		 	<div class="errormsg" id="ddlgdLBIntermediatePanchayatforExist_error1"><form:errors path="lgd_LBIntermediatePanchaytforExist" htmlEscape="true"/></div>  
																																			<div class="errormsg" id="ddlgdLBIntermediatePanchayatforExist_error2" style="display: none" ></div>	
																																			
																											
																											</li>
																											<li>
																													<div class="clear"></div>
																											</li>
																										
																								</ul>
																						</div>

											
											</div>
													
													
													
													</li>
													<li>
													
																										<div id="divVillagePforExist" style="visibility: hidden; display: none;">
												<!-- tr_intermediate id change -->
												
																				<div>
																					<ul class="blocklist">
																						<li>
																						
																												<label><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>&nbsp;&nbsp;<c:out value="${convertTLBtoRLBForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out></label><span
																												class="errormsg">*</span><br /> <form:select
																													path="lgd_LBDistrictAtVillageforExist"
																													class="combofield" id="ddlgdLBDistrictAtVillageforExist"
																													onchange="getInterPanchayatAtVillagebyDcodeforExist(this.value);"
																													>
																													<form:option value="0">
																														<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
																													</form:option>
																													  <c:forEach items="${districtPanchayatList}" var="districtPanchayatList">
																														<c:if test="${districtPanchayatList.operation_state =='A'.charAt(0)}">
																																	<form:option value="${districtPanchayatList.localBodyCode}" ><c:out value="${districtPanchayatList.localBodyNameEnglish}" escapeXml="true"></c:out>
																																	</form:option>
																																</c:if>
																																<c:if test="${districtPanchayatList.operation_state =='F'.charAt(0)}">
																																	<form:option value="${districtPanchayatList.localBodyCode}" disabled="true"><c:out value="${districtPanchayatList.localBodyNameEnglish}" escapeXml="true"></c:out>																				
																																	</form:option>
																																</c:if>
																													 </c:forEach>
																												</form:select> &nbsp;<%-- <span><form:errors
																														path="lgd_LBDistrictAtVillageforExist" class="errormsg"></form:errors>
																											</span> &nbsp;&nbsp;<span class="errormsg"
																												id="ddlgdLBDistrictAtVillageforExist_error"> </span> <br /> --%>
																												<br />
																												<div id="ddlgdLBDistrictAtVillageforExist_error" class="error"><spring:message code="Error.DISTRICTLOCALBODY" htmlEscape="true"></spring:message></div>
																											 	<div class="errormsg" id="ddlgdLBDistrictAtVillageforExist_error1"><form:errors path="lgd_LBDistrictAtVillageforExist" htmlEscape="true"/></div>  
																												<div class="errormsg" id="ddlgdLBDistrictAtVillageforExist_error2" style="display: none" ></div>	
																												
																						
																						</li>
																						<li>
																												<label><spring:message htmlEscape="true" code="Label.SELECT"></spring:message>&nbsp;&nbsp;<c:out value="${convertTLBtoRLBForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out></label>
																												<span class="errormsg">*</span><br /> <form:select
																													path="lgd_LBIntermediateAtVillageforExist"
																													class="combofield" id="ddlgdLBInterAtVillageforExist"
																													
																													onchange="getVillagePanchayatAtVillagebyDcodeforExist(this.value);">
																													<form:option value="0">
																														<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
																													</form:option>
																													<%-- <form:options items="${districtTraditionalList}"
																														itemLabel="localBodyNameEnglish"
																														itemValue="localBodyCode" /> --%>
																												</form:select> &nbsp;<%-- <span><form:errors
																														path="lgd_LBIntermediateAtVillageforExist"
																														class="errormsg"></form:errors> </span> &nbsp;&nbsp;<span
																												class="errormsg" id="ddlgdLBInterAtVillageforExist_error">
													
																											</span> <br /> <br /> --%>
																											    <div id="ddlgdLBInterAtVillageforExist_error" class="error"><spring:message code="Error.INTERMEDIATELOCALBODY" htmlEscape="true"></spring:message></div>
																											 	<div class="errormsg" id="ddlgdLBInterAtVillageforExist_error1"><form:errors path="lgd_LBIntermediateAtVillageforExist" htmlEscape="true"/></div>  
																												<div class="errormsg" id="ddlgdLBInterAtVillageforExist_error2" style="display: none" ></div>				
																						
																						</li>
																						<li>
																													<label><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>&nbsp;&nbsp;<c:out value="${convertTLBtoRLBForm.lgd_LBNomenclatureVillage}" escapeXml="true"></c:out></label><span
																													class="errormsg">*</span><br /> <form:select
																														path="lgd_LBVillagePanchaytforExist" class="combofield"
																														id="ddlgdLBVillagePanchayatforExist"
																														>
																														<form:option value="0">
																															<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
																														</form:option>
																													</form:select> &nbsp;<%-- <span><form:errors
																															path="lgd_LBVillagePanchaytforExist" class="errormsg"></form:errors>
																												</span> &nbsp;&nbsp;<span class="errormsg"
																													id="ddlgdLBVillagePanchayatforExist_error"> </span> <br />
																													<br /> --%>
																													
																													<div id="ddlgdLBVillagePanchayatforExist_error" class="error"><spring:message code="Error.NoVPSelected" htmlEscape="true"></spring:message></div>
																												 	<div class="errormsg" id="ddlgdLBVillagePanchayatforExist_error1"><form:errors path="lgd_LBVillagePanchaytforExist" htmlEscape="true"/></div>  
																													<div class="errormsg" id="ddlgdLBVillagePanchayatforExist_error2" style="display: none" ></div>
																													
																						
																						</li>
																						<li> <div class="clear"></div></li>
																						<li></li>
																					
																					</ul>
																				
																				</div>
							
																			
							
																		</div>
													
													
													
													</li>											
											</ul>
									
										<div class="clear"></div>
									</div>
								
								<div class="clear"></div>
								
							</div>
						</div>

						<div id="divdeclareNewRLB" class="frmpnlbg">

							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message htmlEscape="true"  code="Label.DECNEWRLB"></spring:message>
								</div>
								
										<div >
											<ul class="blocklist">
											
													<li>
																	<label><spring:message htmlEscape="true" 
																		code="Label.LOCALBODYNAMEENGLISH"></spring:message></label><span
																	id="required" class="errormsg"></span><span class="errormsg">*</span><br />
																	<form:input path="localBodyNameInEn"
																		id="txtlocalBodyNameInEn" size="40" cssClass="frmfield"
																		onkeypress="validateAlphanumericKeys();" /> <%-- <span
																	class="errormsg" id="txtlocalBodyNameInEn_error"></span> <span><form:errors
																			path="localBodyNameInEn" class="errormsg"></form:errors> </span> --%>
																	<div id="txtlocalBodyNameInEn_error" class="error"><spring:message code="error.blank.localBodyNameInEn" htmlEscape="true"></spring:message></div>
																	<div class="errormsg" id="txtlocalBodyNameInEn_error1"><form:errors path="localBodyNameInEn" htmlEscape="true"/></div>  
																	<div class="errormsg" id="txtlocalBodyNameInEn_error2" style="display: none" ></div>			
													
													
													</li>
													<li>
																		<label><spring:message htmlEscape="true" 
																			code="Label.LOCALBODYNAMELOCAL"></spring:message></label> <br /> <form:input
																			path="localBodyNameInLcl" id="txtlocalBodyNameInLcl"
																			size="40" cssClass="frmfield"
																			onkeypress="validateAlphanumericKeys();" />
													
													</li>
													<li>
																				<label><spring:message htmlEscape="true" 
																					code="Label.LOCALBODYALIASENGLISH"></spring:message></label><br />
																				<form:input path="localBodyAliasInEn"
																					id="txtlocalBodyNmeInAlias" size="40" cssClass="frmfield"
																					onkeypress="validateAlphanumericKeys();" />
													</li>
													<li>
																		<label><spring:message htmlEscape="true" 
																			code="Label.LOCALBODYALIASLOCAL"></spring:message></label><br /> <form:input
																			path="localBodyAliasInLcl" id="txtlocalBodyliasInLcl"
																			size="40" cssClass="frmfield"
																			onkeypress="validateAlphanumericKeys();" />
													
													</li>
													
													<li>
																			<label><spring:message htmlEscape="true" 
																				code="Label.SELECTLOCALBODYTYPE"></spring:message></label><span
																			class="errormsg">*</span><br /> <form:select
																				id="ddRuralLocalBodyTypeNew"
																				path="traditionalLgTypeNameNew" size="1" class="combofield"
																				onchange="getHideShowTLBToRuralList(this.value);">
																				<form:option value="0">
																					<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message>
																				</form:option>
								
																				<c:forEach var="localbodyTypelist" varStatus="var"
																					items="${localbodyTypelist}">
																					<form:option id="typeCode"
																						value="${localbodyTypelist.localBodyTypeCode}:${localbodyTypelist.level}"><c:out value="${localbodyTypelist.nomenclatureEnglish}" escapeXml="true"></c:out></form:option>
																				</c:forEach>	
																				
																			</form:select> <%-- <span id="ddRuralLocalBodyTypeNew_error" class="errormsg">
																		</span> <form:errors path="traditionalLgTypeNameNew" class="errormsg"></form:errors> --%>
																		
																		<div id="ddRuralLocalBodyTypeNew_error" class="error"><spring:message code="error.blank.ruralLBTypeName" htmlEscape="true"></spring:message></div>
																		<div class="errormsg" id="ddRuralLocalBodyTypeNew_error1"><form:errors path="traditionalLgTypeNameNew" htmlEscape="true"/></div>  
																		<div class="errormsg" id="ddRuralLocalBodyTypeNew_error2" style="display: none" ></div>	
													
													</li>
													<li>
																<div id="divDistrictPforNew" style="visibility: hidden; display: none;">
																		<!--  tr_district id name Change-->
																		<div >
																				<ul class="blocklist">
																						<li>
																								<label><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
																					<c:out value="${convertTLBtoRLBForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out></label><span
																					class="errormsg">*</span><br /> <form:select
																						path="lgd_LBDistrictforExistNew" class="combofield"
																						id="ddlgdLBDistrictPforExistNew" 
																						onchange="getInterPanchayatAtVillagebyDcodeforExistF(this.value);"> 
																						<form:option value="0">
																							<spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
																						</form:option>
																						<form:options items="${districtPanchayatList}"
																							itemLabel="localBodyNameEnglish"
																							itemValue="localBodyCode" />
																					</form:select> &nbsp;<span><form:errors
																							path="lgd_LBDistrictforExistNew" class="errormsg"></form:errors>
																				</span> &nbsp;&nbsp;<span class="errormsg"
																					id="ddlgdLBDistrictPforExist_error"> </span><br /> <br />
																						
																						</li>
																						<li><div class="clear"></div></li>
																				</ul>
																		
																		</div>
																		
																		
																	</div>
													
													</li>
													<li>
																			<div id="divLgdSelIntermediatePforNew" style="visibility: hidden; display: none;">
												<!--  tr_district id name Change-->
																				
																				<div  >
																							<ul class="blocklist">
																							
																									<li>
																													<label><spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
																													<c:out value="${convertTLBtoRLBForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out></label><span
																													class="errormsg">*</span><br /> <form:select
																														path="lgd_LBDistrictAtInterforNew" class="combofield"
																														id="ddlgdLBDistAtInterforNew" 
																														onchange="getInterPanchayatAtInterbyDcodeforExist(this.value);">
																														<%--ddSourceLocalBody id name changed  --%>
																														<form:option value="0">
																															<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
																														</form:option>
																														<c:forEach items="${districtPanchayatList}" var="districtPanchayatList">
																															<c:if test="${districtPanchayatList.operation_state =='A'.charAt(0)}">
																																	<form:option value="${districtPanchayatList.localBodyCode}" ><c:out value="${districtPanchayatList.localBodyNameEnglish}" escapeXml="true"></c:out>
																																	</form:option>
																																</c:if>
																																<c:if test="${districtPanchayatList.operation_state =='F'.charAt(0)}">
																																	<form:option value="${districtPanchayatList.localBodyCode}" disabled="true"><c:out value="${districtPanchayatList.localBodyNameEnglish}" escapeXml="true"></c:out>																				
																																	</form:option>
																																</c:if>
																														 </c:forEach>	
																													</form:select> &nbsp;<%-- <span><form:errors
																															path="lgd_LBDistrictAtInterforNew" class="errormsg"></form:errors>
																												</span> &nbsp;&nbsp;<span class="errormsg"
																													id="ddlgdLBDistAtInterforNew_error"> </span><br /> <br /> --%>
																													<%--<div id="ddlgdLBDistAtInterforNew_error" class="error"><spring:message code="error.blank.ruralLBTypeName" htmlEscape="true"></spring:message></div>--%>
																									 			<div class="errormsg" id="ddlgdLBDistAtInterforNew_error1"><form:errors path="lgd_LBDistrictAtInterforNew" htmlEscape="true"/></div>  
																												<div class="errormsg" id="ddlgdLBDistAtInterforNew_error2" style="display: none" ></div>
																									
																									</li>
																									<li>
																														<label><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>&nbsp;&nbsp;<c:out value="${convertTLBtoRLBForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out></label><span
																														class="errormsg">*</span><br /> <form:select
																															path="lgd_LBIntermediatePanchaytforExist"
																															class="combofield"
																															id="ddlgdLBIntermediatePanchayatforNew"
																															>
																															<form:option value="0">
																																<spring:message htmlEscape="true"  code="Label.SELECTINTERMEDIATELOCBODY"></spring:message>
																															</form:option>
																														</form:select> &nbsp;<%-- <span><form:errors
																																path="lgd_LBIntermediatePanchaytforExist"
																																class="errormsg"></form:errors> </span> &nbsp;&nbsp;<span
																														class="errormsg"
																														id="ddlgdLBIntermediatePanchayatforExist_error"> </span>
																														<br /> <br /> --%>
																														<%--<div id="ddlgdLBIntermediatePanchayatforExist_error" class="error"><spring:message code="error.blank.ruralLBTypeName" htmlEscape="true"></spring:message></div>--%>
																													 	<div class="errormsg" id="ddlgdLBIntermediatePanchayatforExist_error1"><form:errors path="lgd_LBIntermediatePanchaytforExist" htmlEscape="true"/></div>  
																														<div class="errormsg" id="ddlgdLBIntermediatePanchayatforExist_error2" style="display: none" ></div>	
																									
																									</li>
																									<li><div class="clear"></div></li>
																							
																							</ul>
																				
																				</div>
																			
																			</div>
													
													</li>
													<li>
																				<div id="divLgdVillagePforNew" style="visibility: hidden; display: none;">
															
															
																					<div >
																							<ul class="blocklist">
																									<li>
																															<label><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>&nbsp;&nbsp;<c:out value="${convertTLBtoRLBForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out></label><span
																															class="errormsg">*</span><br /> <form:select
																																path="lgd_LBDistrictAtVillageforNew" class="combofield"
																																id="ddlgdLBDistrictAtVillageforNew"
																																onchange="getIntermediatePanchayatbyDcodeforNewF(this.value);"
																																>
																																<form:option value="0">
																																	<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
																																</form:option>
												
																																	
																																	<c:forEach items="${districtPanchayatList}" var="districtPanchayatList">
																																		<c:if test="${districtPanchayatList.operation_state =='A'.charAt(0)}">
																																			<form:option value="${districtPanchayatList.localBodyCode}" ><c:out value="${districtPanchayatList.localBodyNameEnglish}" escapeXml="true"></c:out>
																																			</form:option>
																																		</c:if>
																																		<c:if test="${districtPanchayatList.operation_state =='F'.charAt(0)}">
																																			<form:option value="${districtPanchayatList.localBodyCode}" disabled="true"><c:out value="${districtPanchayatList.localBodyNameEnglish}" escapeXml="true"></c:out>																				
																																			</form:option>
																																		</c:if>
																														 			</c:forEach>
																															</form:select> &nbsp;<%-- ;<span><form:errors
																																	path="lgd_LBDistrictAtVillageforNew" class="errormsg"></form:errors>
																														</span> &nbsp;&nbsp;<span class="errormsg"
																															id="ddlgdLBDistrictAtVillageforNew_error"> </span> <br />
																															<br /> --%>
																														<div id="ddlgdLBDistrictAtVillageforNew_error" class="error"><spring:message code="Error.DISTRICTLOCALBODY" htmlEscape="true"></spring:message></div>
																											 			<div class="errormsg" id="ddlgdLBDistrictAtVillageforNew_error1"><form:errors path="lgd_LBDistrictAtVillageforNew" htmlEscape="true"/></div>  
																														<div class="errormsg" id="ddlgdLBDistrictAtVillageforNew_error2" style="display: none" ></div>
																									
																									</li>
																									<li>
																														<label><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>&nbsp;&nbsp;<c:out value="${convertTLBtoRLBForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out></label><span
																														class="errormsg">*</span><br /> <form:select
																															path="lgd_LBIntermediateAtVillageforNew"
																															class="combofield" id="ddlgdLBInterAtVillageforNew"
																															>
																															<form:option value="0">
																																<spring:message htmlEscape="true"  code="Label.SELECTINTERMEDIATELOCBODY"></spring:message>
																															</form:option>
																														</form:select> &nbsp;<%-- ;<span><form:errors
																																path="lgd_LBIntermediateAtVillageforNew"
																																class="errormsg"></form:errors> </span> &nbsp;&nbsp;<span
																														class="errormsg" id="ddlgdLBInterAtVillage_error">
															
																													</span> <br /> <br /> --%>
																													<div id="ddlgdLBInterAtVillageforNew_error" class="error"><spring:message code="Error.INTERMEDIATELOCALBODY" htmlEscape="true"></spring:message></div>
																										 			<div class="errormsg" id="ddlgdLBInterAtVillageforNew_error1"><form:errors path="lgd_LBIntermediateAtVillageforNew" htmlEscape="true"/></div>  
																													<div class="errormsg" id="ddlgdLBInterAtVillageforNew_error2" style="display: none" ></div>
																																		
																									</li>
																									<li> <div class="clear"></div></li>
																							
																							</ul>
																					
																					</div>
											
													</div>	
													
													</li>
													<li>
																					
													
													</li>
											
											</ul>
											<div class="clear"></div>
										
										</div>
								
			</div>
				</div>
				
						<div id="divPreviousButtons" class="btnpnl" align="center">
																							<input type="button"
																								value="<spring:message htmlEscape="true"  code="Button.PREVIOUS"></spring:message>"
																								onclick="onPreviousClick();" class="btn" />
																						</div>
																						
																						
																						<div class="btnpnl" id="divSaveButtons">
																							<input type="button"
																												value="<spring:message htmlEscape="true"  code="Button.PREVIOUS"></spring:message>"
																												onclick="onPreviousClick();" class="btn" /> <label>
																												<input type="submit" name="Submit" onclick="return validateFinalAllTLBtoRLB();"
																												value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" />
																											</label> <label> <input type="button" class="btn"
																												name="Submit6"
																												value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
																												onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
																						</div>
				
				
				</div>
				</div>
			</form:form>
			
		</div>
	
	</div>

</body>
</html>
