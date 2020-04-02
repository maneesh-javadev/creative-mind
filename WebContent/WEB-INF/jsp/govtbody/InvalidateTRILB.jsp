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
<title> <spring:message htmlEscape="true"  code="Label.INVALIDATETLB"></spring:message> 
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

<script type="text/javascript" src="js/invalidatetrilocalbody.js"
	charset="utf-8"></script>
	
	<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'>
	dwr.engine.setActiveReverseAjax(true);</script>	
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
						<h3 class="subtitle"> <spring:message htmlEscape="true"  code="Label.INVALIDATETLB"></spring:message> </h3>
										 <ul id="showhelp" class="listing">
					 				       <%--//this link is not working  <li>
									 				       <a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
									 		</li> --%>
					 				        
					 				        <li>
					 				        	<a href="viewManualHelps.do?formName=${formId}&<csrf:token uri='viewManualHelps.do'/>" class="frmhelp" onclick="centeredPopup(this.href,'myWindow','yes');return false">Help</a>
					 				        </li>
					 				     
					 			        </ul>
		
		
		
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="invalidateLocalBodyforTRI.htm" id="invalidateLocalBodyforTRI" commandName="localGovtBodyForm" method="POST" enctype="multipart/form-data" >
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="invalidateLocalBodyforPRI"/>" />
			<input type="hidden" name="flag1" id="flag1" value="0"  />
			<input type="hidden" name="Tier" id="Tier" value="<c:out value='${Tier}' escapeXml='true'></c:out>"/>
				
					
					<div id='divLgdLBType' class="frmpnlbg">
						<!--'district' id name change  -->
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message>
							</div>
							
							<ul class="blocklist">
								<li>
										<label><spring:message htmlEscape="true" 
												code="Label.SELECTLOCALBODYTYPE"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:select
											path="lgd_LBTypeName" id="ddLgdLBType"
											onchange="HideShowDivs(this.value);" class="combofield"
											style="width: 200px">
											<!--tierSetupCode id Changed  -->
											<form:option value="0">
												<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message>
											</form:option>

											<c:forEach var="localBodyTypelist" varStatus="var"
												items="${localBodyTypelist}">
												<form:option id="typeCode"
													value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.level}:${localBodyTypelist.nomenclatureEnglish}"><c:out value="${localBodyTypelist.nomenclatureEnglish}" escapeXml="true"></c:out>  </form:option>
											</c:forEach>
										</form:select>&nbsp;&nbsp; 
										<div class="errormsg" id="ddLgdLBType_error1"><form:errors path="lgd_LBTypeName" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddLgdLBType_error2" style="display: none" ></div>						
								
								
								</li>
								<li>
										<div id="divLgdlistSubTypeCode" class="frmpnlbg" style="visibility: hidden; display: none;">
														<label><spring:message htmlEscape="true" 
																code="Label.AVAILSUBTYPE"></spring:message></label> <br /> <form:select
															path="localbodySubtype" class="combofield" id="ddlgdLBSubTypeCode"
															style="width: 200px">
														</form:select>  
														<br />
											
										</div>
								
								
								</li>
								<li>
											<div id="divLgdSelIntermediateP" style="visibility: hidden; display: none;">
											<label><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
															 <c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out> &nbsp;</label><span
														class="errormsg">*</span><br /> <form:select
															path="lgd_LBDistrictAtInter" class="combofield"
															id="ddlgdLBDistAtInter" style="width: 200px"
															onchange="getlistofIntermediatePanchayat(this.value)">
															<form:option value="0">
																<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
															</form:option>
														<%-- 	<form:options items="${districtPanchayatList}"
																itemLabel="localBodyNameEnglish"
																itemValue="localBodyCode" /> --%>
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
														<div class="errormsg" id="ddlgdLBDistAtInter_error1"><form:errors path="lgd_LBDistrictAtInter" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none" ></div>
													
										</div>
								
								
								</li>
								<li>
								
												<div id="divLgdVillageP" style="visibility: hidden; display: none;">
												
												<ul class="blocklist">
														<li>
																	<label><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>&nbsp;&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out> </label><span
														class="errormsg" style="width: 200px">*</span><br /> <form:select
															path="lgd_LBDistrictAtVillage" class="combofield"
															id="ddlgdLBDistrictAtVillage"
															onchange="getWorkOnVillagePanchayatforDistrictP(this.value);"
															style="width: 200px">
															<form:option value="0">
																<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
															</form:option>
															<%-- <form:options items="${districtPanchayatList}"
																itemLabel="localBodyNameEnglish"
																itemValue="localBodyCode" /> --%>
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
														<div class="errormsg" id="ddlgdLBDistrictAtVillage_error1"><form:errors path="lgd_LBDistrictAtVillage" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddlgdLBDistrictAtVillage_error2" style="display: none" ></div>
													
														
														
														</li>
														<li><label><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>&nbsp;&nbsp; <c:out value="${localGovtBodyForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out></label><span
														class="errormsg">*</span><br /> <form:select
															path="lgd_LBIntermediateAtVillage" class="combofield"
															id="ddlgdLBInterAtVillage" style="width: 200px"
															onchange="getlistofgp(this.value);">
															<form:option value="0">
																<spring:message htmlEscape="true"  code="divLgdLBType3"></spring:message>
															</form:option>
														</form:select> &nbsp;
														<div class="errormsg" id="ddlgdLBInterAtVillage_error1"><form:errors path="lgd_LBIntermediateAtVillage" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddlgdLBInterAtVillage_error2" style="display: none" ></div>													
													</li>
														<li></li>
												
												
												</ul>
												
												
										</div>
								
								
								</li>
								<li></li>
								<li></li>
								<li></li>
								
							</ul>
							
							
							
							
						</div>

					</div>			
					
					
					<div id="Districtlocalbody" style="visibility: hidden; display: none;">
					<div class="frmpnlbg" id="divLgdLBType1">
						<div class="frmtxt">
				
				<div class="frmhdtitle">
					 <spring:message htmlEscape="true"  code="Label.SELECTLBD"></spring:message> 
				
							
							</div>
						
													
													<ul class="blocklist">
													
														<li>
																<label><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message></label><span
														class="errormsg">*</span><br />				
															
											
														  <form:select
															path="districtpanlbid" class="combofield"
															id="lblist1" style="width: 200px"
															onchange="removeData();setflag1()">
															ddSourceLocalBody id name changed 
															<form:option value="0">
																<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
															</form:option>
															<%-- <form:options items="${districtPanchayatList}"
																itemLabel="localBodyNameEnglish"
																itemValue="localBodyCode" /> --%>
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
														</form:select> 
													
														
														&nbsp;
														<div class="errormsg" id="ddlgdLBDistAtInter_error1"><form:errors path="lgd_LBDistrictAtInter" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none" ></div>
											
														
														
														</li>
														
													</ul>
															
									</div>
							</div>
					</div> 	
					
					<div id="IPlocalbody" style="visibility: hidden; display: none;">
					<div class="frmpnlbg" id="divLgdLBType2">
					<div class="frmtxt">
				
				<div class="frmhdtitle">
							 <spring:message htmlEscape="true"  code="Label.SELECTLBD"></spring:message> 
					
							
							</div>
					
						
													
													<ul class="blocklist">
														<li>
														<label><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
															&nbsp;</label><span
														class="errormsg">*</span><br />
																<%--  <c:set var="flag1" value="2"/>	 --%>									
														<form:select
															path="intermediatepanlbid" class="combofield"
															id="lblist2" style="width: 200px"
															onchange="removeData();setflag2();">
															<%-- <form:option value="0">
																<spring:message htmlEscape="true"  code="Label.SELECTINTERMEDIATELOCBODY"></spring:message>
															</form:option> --%>
														</form:select>	
																				
														
														&nbsp;
														<div class="errormsg" id="ddlgdLBDistAtInter_error1"><form:errors path="lgd_LBDistrictAtInter" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none" ></div>
											
														
														
														</li>
														<li></li>
														<li></li>
													
													
													</ul>
													
									</div>
							</div>
					</div> 	
					
					
					
					
							
					
					
					
					<div id="gplocalbody" style="visibility: hidden; display: none;">
					<div class="frmpnlbg" id="divLgdLBType3">
					<div class="frmtxt">
				
				<div class="frmhdtitle">
				 <spring:message htmlEscape="true"  code="Label.SELECTLBD"></spring:message> 
					
							
							</div>
					
							
													
													<ul class="blocklist">
															<li>
																<label> <spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message> 
															&nbsp;</label><span
														class="errormsg">*</span><br />
																<%--  <c:set var="flag3" value="3"/>		 --%>								
														<form:select
															path="grampanlbid" class="combofield"
															id="lblist3" style="width: 200px"
															onchange="removeData();setflag3();">
															<form:option value="0">
																<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
															</form:option>
														</form:select>	
																				
														
														&nbsp;
														<div class="errormsg" id="ddlgdLBDistAtInter_error1"><form:errors path="lgd_LBDistrictAtInter" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none" ></div>
															
															</li>
															
													</ul>
													
													
												
									</div>
							</div>
					</div> 	
				
					
								
					<div class="frmpnlbg" id="divLgdLBType4">			
					<div class="frmtxt">
						<spring:message htmlEscape="true"  code="Label.SELECTLBCOVERD"></spring:message>
								<ul class="listing">
								
									<li><label>
															<form:radiobutton id="show_dp" path="lboption" htmlEscape="true"
																		onclick="getlblist();removeListItem();" value="true" name="rdoDistrictDelete"  />																		
																
															</label>YES
															
															
															
															
															</li>
									<li>
												<label>
																														
															<form:radiobutton id="hide_dp" path="lboption" htmlEscape="true"
																		onclick="hidelblist()" value="true" name="rdoDistrictDelete"  /> 
																</label>No
									
									
									</li>
									<li><div class="errormsg"></div></li>
								
								
								</ul>
					
							
							</div>
							</div>		
							
							
							
                    <div id="IPMerge1" style="visibility: hidden; display: none;">
					<div class="frmpnlbg" id="divLgdLBType5">
					<div class="frmpnlbrdr">
								<ul class="blocklist">
										<li>
										
											<label> <spring:message htmlEscape="true"  code="Label.SELECTMERGE"></spring:message> </label><span class="errormsg">*</span><br />
													<label> 
													<form:select path="contSubDistrict" class="combofield" id="mergelocalbody" style="width: 200px">
													<form:option value="0"><spring:message htmlEscape="true"  code="Label.DISTRICTPANCHAYAT"></spring:message></form:option>
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
													</form:select>
												
												    </label><br /><br /><br />
												
										
										</li>
								
								</ul>
					
													
												
									</div>
							</div>
					</div> 	
					
					<div id="IPMerge2" style="visibility: hidden; display: none;">
					   <div class="form-group" id="divLgdLBType6">
					
					  <label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTMERGE"></spring:message> <span class="errormsg">*</span></label>
						<div class="col-sm-6"> <form:select path="contSubDistrict" class="form-control" id="mergelocalbody2" htmlEscape="true" >
												<form:option value="0"><spring:message htmlEscape="true"  code="Label.DISTRICTPANCHAYAT"></spring:message></form:option>
												
										</form:select>
												
					   </div>
					
						
					</div>
			        </div> 	
					
					
					<div id="IPMerge3" style="visibility: hidden; display: none;">
					<div class="frmpnlbg" id="divLgdLBType7">
					<div class="frmpnlbrdr">
					<ul class="blocklist">
							<li><br /><label> <spring:message htmlEscape="true"  code="Label.SELECTMERGE"></spring:message> </label><span class="errormsg">*</span><br />
													<label> 
													<form:select path="contSubDistrict" class="combofield" id="mergelocalbody3" htmlEscape="true" style="width: 200px">
												<form:option value="0"><spring:message htmlEscape="true"  code="Label.DISTRICTPANCHAYAT"></spring:message></form:option>
												
										</form:select>
												
												    </label><br /><br /><br />
												    
							</li>
							<li></li>
					
					</ul>
					
			
									</div>
							</div>
					</div> 													
				
					<div id="Districtlistlocalbodylist" style="visibility: hidden; display: none;">
					<div class="frmpnlbg" id="divLgdLBTypelist">
					<div class="frmpnlbrdr">
							
									
									
										<ul class="blocklist">
										
													<li>
																<div class="ms_container">
																	<div class="ms_selectable">
																	
																	<label id="selectEntity"></label>
																			<form:select name="select9" size="1" id="availablelb" htmlEscape="true" path="lgd_LBDistrictAtVillage" multiple="multiple" class="frmtxtarea"  onclick="checkcode(this.value);">
																				<form:option value="">
																			</form:option>
																			</form:select>
																			<div class="errormsg"><form:errors htmlEscape="true"  path="lgd_LBDistrictAtVillage" ></form:errors></div>
																	
																	</div>
																	
																	<div class="ms_buttons">
																			  <input type="button" id="btnaddVillageFull" class="bttn" name="Submit4" value= "<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>"															 
																	   onclick="addItem('choosenlb','availablelb','',false);" />
																	   
																	   <input type="button"  class="bttn"  id="btnremoveOneVillage" name="Submit4" value=" &lt; " class="btn" 
																   onclick="removeItem('choosenlb','availablelb',false)" />
																   	<input
																	type="button"  class="bttn"  id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;" class="btn" 
																onclick="removeAll('choosenlb','availablelb',false)" />
																   
																	</div>
																	
																	<div class="ms_selection">
																	<label id="selectedEntity"></label>
																	  <form:select name="select4" id="choosenlb" size="1" htmlEscape="true" multiple="multiple" path="choosenlb" class="frmtxtarea" onclick="checkcode(this.value)"></form:select>
																	   <input type="button" id="chooseMoreBtn" onclick='validateSelectedAddMore();'  class="btn" value="Choose more Local Bodies"/><%-- <spring:message htmlEscape="true"  code="Button.CHOOSEMOREDISTRICT"></spring:message>" /> --%>
																	
																	</div>
																	
																</div>
																
																	
													
													</li>
													<li><div class="errormsg"></div></li>
										
													<li>
														 
													
													</li>
										
										</ul>
											<div class="clear"></div>
										
										<table id="dynamicTbl" width="664"  class="tbl_with_brdr"  style="visibility: hidden;display:none;" >
												    	<tr class="tblRowTitle tblclear">
												    		<th><spring:message code="Label.LOCALBODYTYPENAME" htmlEscape="true"></spring:message></th>
												    		<th><spring:message code="Label.CHILDLOCALGOVTBODYDETAIL" htmlEscape="true"></spring:message></th>
												    	</tr>
												    </table>
										
										<div class="errormsg"></div>
										<div class="clear"></div>
										
												   
								
						</div>
					</div>
			</div>
					<div class="btnpnl">
						
									<div>
										<label> 
										<input type="hidden" name="listformat" id="listformat" value="@" />
										<input type="button" onclick="validateSelectAndSubmit(); " name="Submit" class="btn" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" />
										<input type="button" name="Submit2" class="btn"	value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>" onclick="javascript:location.href='invalidatetriocalbody.htm?<csrf:token uri="invalidatetriocalbody.htm"/>'" />
										<input type="button" name="Submit6" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> 
										</label>
									</div>
								
					</div>
				</div>
			</form:form>
			</td>
			</tr>
			
		</div>
		</div>
</body>
</html>

