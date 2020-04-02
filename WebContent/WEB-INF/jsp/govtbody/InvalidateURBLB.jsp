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
<title> <spring:message htmlEscape="true"  code="Label.INVALIDATEULB"></spring:message>
</title>
<script src="js/govtorder.js"></script>
<script src="js/validation.js"></script>
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
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
	var callActionUrl = function (url) {
		$( 'form[id=invalidateLocalBodyforURB]' ).attr('action', url +'?<csrf:token uri="'+ url +'"/>');
		$( 'form[id=invalidateLocalBodyforURB]' ).attr('method','post');
		$( 'form[id=invalidateLocalBodyforURB]' ).submit();
	};
	</script>
</head>
<body 
	onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);">
	
	<div id="frmcontent">


		<div class="frmhd">
			<h3 class="subtitle">
				<label><spring:message htmlEscape="true"  code="Label.INVALIDATEULB"></spring:message> </label>
			</h3>
			<ul id="showhelp" class="listing">												
			<%--//these links are not working 	<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		
		
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="invalidateLocalBodyforURB.htm" id="invalidateLocalBodyforURB"	commandName="localGovtBodyForm" method="POST"	enctype="multipart/form-data" >
			<input type="hidden" name="<csrf:token-name/>"		value="<csrf:token-value uri="invalidateLocalBodyforURB.htm"/>" />
					<input type="hidden" name="flag1" id="flag1" value="0"  />
					<input type="hidden" name="flag2" id="flag2" value="<c:out value='${scode}' escapeXml='true'></c:out>"/>
					
				
					
					<div id='divLgdLBType' class="frmpnlbg">
						<!--'district' id name change  -->
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message>
							</div>
							
						<div>
							<ul class="blocklist"> 
								<li>									
										<label><spring:message htmlEscape="true" 
												code="Label.SELECTLOCALBODYTYPE"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:select
											path="lgd_LBTypeName" id="ddLgdLBType"
											onchange="HideShow(this.value);" cssClass="combofield">
											<!--tierSetupCode id Changed  -->
											<form:option value="0" htmlEscape="true">
												<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message>
											</form:option>

											<c:forEach var="localBodyTypelist" varStatus="var"
												items="${localBodyTypelist}">
												<form:option id="typeCode" htmlEscape="true"
													value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.level}:${localBodyTypelist.nomenclatureEnglish}"><c:out value="${localBodyTypelist.nomenclatureEnglish}" escapeXml="true"></c:out></form:option>
											</c:forEach>
										</form:select>&nbsp;&nbsp; 
										<div class="errormsg" id="ddLgdLBType_error1"><form:errors path="lgd_LBTypeName" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddLgdLBType_error2" style="display: none" ></div>																		
								</li>
								<li>									
										<div id="divLgdlistSubTypeCode" class="frmpnlbg" style="visibility: hidden; display: none;">
											
													<label><spring:message htmlEscape="true" 
																code="Label.AVAILSUBTYPE"></spring:message></label> <br /> <form:select
															path="localbodySubtype" cssClass="combofield" id="ddlgdLBSubTypeCode" htmlEscape="true">
														</form:select>  
														<br />
												
										</div>
									
								</li>
								<li>									
										<div id="divLgdSelIntermediateP" style="visibility: hidden; display: none;">
													<label><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
															<c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out> &nbsp;</label><span
														class="errormsg">*</span><br /> <form:select
															path="lgd_LBDistrictAtInter" 
															id="ddlgdLBDistAtInter" cssClass="combofield"
															onchange="getlistofIntermediatePanchayat(this.value)">
															<form:option value="0" htmlEscape="true">
																<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
															</form:option>
															<form:options items="${districtPanchayatList}"
																itemLabel="localBodyNameEnglish"
																itemValue="localBodyCode" htmlEscape="true" />
														</form:select> &nbsp;
														<div id="ddlgdLBDistAtInter_error" class="error"><spring:message code="error.SOURCESELECTLOCALBODYPARENT" htmlEscape="true"></spring:message></div>
														<div class="errormsg" id="ddlgdLBDistAtInter_error1"><form:errors path="lgd_LBDistrictAtInter" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none" ></div>
													
										</div>									
								</li>
								<li>									
										<div id="divLgdVillageP" style="visibility: hidden; display: none;">																					
												<ul class="blocklist">
												<li>
														<label><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>&nbsp;&nbsp; <c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out></label><span
															class="errormsg"  >*</span><br /> <form:select
															path="lgd_LBDistrictAtVillage" class="combofield"
															id="ddlgdLBDistrictAtVillage" htmlEscape="true"
															onchange="getWorkOnVillagePanchayatforDistrictP(this.value);"
															>
															<form:option value="0" htmlEscape="true">
																<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
															</form:option>
															<form:options items="${districtPanchayatList}"
																itemLabel="localBodyNameEnglish"
																itemValue="localBodyCode" htmlEscape="true"/>
														</form:select> &nbsp;
														<div id="ddlgdLBDistrictAtVillage_error" class="error"><spring:message code="error.SOURCESELECTVILLAGEPARENT" htmlEscape="true"></spring:message></div>
														<div class="errormsg" id="ddlgdLBDistrictAtVillage_error1"><form:errors path="lgd_LBDistrictAtVillage" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddlgdLBDistrictAtVillage_error2" style="display: none" ></div>																								

												</li>

												<li>
														<label><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>&nbsp;&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out></label><span
														class="errormsg">*</span><br /> <form:select
															path="lgd_LBIntermediateAtVillage"  htmlEscape="true"
															id="ddlgdLBInterAtVillage" cssClass="combofield"
															onchange="getlistofgp(this.value);">
															<form:option value="0" htmlEscape="true">
																<spring:message htmlEscape="true"  code="Label.SELECTINTERMEDIATELOCBODY"></spring:message>
															</form:option>
														</form:select> &nbsp;
														
														
														<div id="ddlgdLBInterAtVillage_error" class="error"><spring:message code="error.SOURCESELECTVILLAGEPARENT" htmlEscape="true"></spring:message></div>
														<div class="errormsg" id="ddlgdLBInterAtVillage_error1"><form:errors path="lgd_LBIntermediateAtVillage" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddlgdLBInterAtVillage_error2" style="display: none" ></div>																										
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
					<div class="frmpnlbg" id="divLgdLBType2">
						<div class="frmtxt">
							<div>
								<div class="frmhdtitle">
									<spring:message htmlEscape="true" code="Label.SELECTLBD"></spring:message>
								</div>
								<label><spring:message htmlEscape="true" code="Label.SELECT"></spring:message> <c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out>&nbsp;</label><span class="errormsg">*</span><br />

								<form:select path="districtpanlbid" id="lblist1" cssClass="combofield" onchange="setflag1()">
								 	ddSourceLocalBody id name changed 								
								</form:select>
								&nbsp;
								<div class="errormsg" id="ddlgdLBDistAtInter_error1">
									<form:errors path="lgd_LBDistrictAtInter" htmlEscape="true" />
								</div>
								<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none"></div>
							</div>
						</div>
					</div>
				</div>
				
				<div id="Muncipiality" style="visibility: hidden; display: none;">
					<div class="frmpnlbg" id="divLgdLBType3">
						<div class="frmtxt">
							<div>
								<div class="frmhdtitle">
									<!-- dell -->								
									litydelete
									<spring:message htmlEscape="true" code="Label.SelectMunicipa"></spring:message>
								</div>
										<label><spring:message htmlEscape="true"
												code="Label.SELECT"></spring:message>
											<c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out> &nbsp;</label><span
										class="errormsg">*</span><br /> <%--  <c:set var="flag1" value="1"/>			 --%>

										<form:select path="intermediatepanlbid" class="combofield"
											id="lblist2" style="width: 200px" onchange="setflag2()">
															ddSourceLocalBody id name changed 
															<form:option value="0" htmlEscape="true">
												<spring:message htmlEscape="true"
													code="Label.SELECTLOCALBODY"></spring:message>
											</form:option>
											<form:options items="${districtPanchayatList2}" htmlEscape="true"
												itemLabel="localBodyNameEnglish" itemValue="localBodyCode" />
										</form:select> &nbsp;
										<div id="ddlgdLBDistAtInter_error" class="error">
											<spring:message code="error.SOURCESELECTLOCALBODYPARENT"
												htmlEscape="true"></spring:message>
										</div>
										<div class="errormsg" id="ddlgdLBDistAtInter_error1">
											<form:errors path="lgd_LBDistrictAtInter" htmlEscape="true" />
										</div>
										<div class="errormsg" id="ddlgdLBDistAtInter_error2"
											style="display: none"></div>
							</div>
						</div>
					</div>
				</div>



				<div class="btnpnl">
					<div>
						<label> 
						<input type="hidden" name="listformat"	id="listformat" value="@" /> 
						<input type="button" onclick="validateAndSubmit(); " name="Submit" class="btn" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" />
						<input type="button" name="Submit2" class="btn"	value="<spring:message htmlEscape="true"  code="Button.CLEAR"> </spring:message>" onclick="javascript:location.href='invalidateturbanlocalbody.htm?<csrf:token uri="invalidateturbanlocalbody.htm"/>'" />
						<input type="button" name="Submit6" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> 
						</label>
					</div>
				</div>
			</form:form>
		</div>
			
			
		</div>
		
</body>
</html>

