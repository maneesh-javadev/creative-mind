<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="downloadDirectoryJs.jsp"%>
<c:set var="downloadPDF" value="<%=in.nic.pes.lgd.constant.ReportConstant.DOWNLOAD_DIRECTORY_PDF.toString()%>"></c:set>
<c:set var="downloadXLS" value="<%=in.nic.pes.lgd.constant.ReportConstant.DOWNLOAD_DIRECTORY_XLS.toString()%>"></c:set>
<c:set var="downloadCSV" value="<%=in.nic.pes.lgd.constant.ReportConstant.DOWNLOAD_DIRECTORY_CSV.toString()%>"></c:set>
<c:set var="downloadODT" value="<%=in.nic.pes.lgd.constant.ReportConstant.DOWNLOAD_DIRECTORY_ODT.toString()%>"></c:set>
<c:set var="downloadHTM" value="<%=in.nic.pes.lgd.constant.ReportConstant.DOWNLOAD_DIRECTORY_HTM.toString()%>"></c:set>
<title><spring:message htmlEscape="true"  code="Label.DD"></spring:message></title>
</head>
<body>
<!-- Main Form Stylings starts -->
<div class="form_stylings">

		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message code="Label.DD" htmlEscape="true"></spring:message></h3>
			<ul class="item_list">
				<!-- <li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /> </a></li> -->
				<%-- <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		<!-- Main Heading ends -->
		
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form action="downloadDirectory.do" method="post" id="downloadDirectoryForm" commandName="downloadDirectoryForm" >
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="downloadDirectory.do"/>" />
					
						<div id="cat">
						
							
							<div class="form_block">
								<div class="col_1">
									<h4><spring:message code="Label.DDCRIT" htmlEscape="true"></spring:message></h4>
										
										<!-- Download Option Body Started-->
										<div class="long_latitude" >
											<div class="row"> <!-- Max of 4 divs can be used inside this row div -->
													<div class="col">
														<label>
														<input type="radio"  id="DFDOption" 	value="DFD" onclick="showHideOption();" name="DDOption"  /> 
														
														<spring:message htmlEscape="true" code="Label.DF"></spring:message></label>
													</div>
													<div class="col">
														<label>
														<input type="radio"  id="DSWDOption" 	value="DSWD" onclick="showHideOption();" name="DDOption" />
														<spring:message code="Label.DSWD"   htmlEscape="true" ></spring:message></label>
													</div>
													<div class="col">
														
														<input type="radio"  id="UNSELECT" 	value="UNSELECT"  checked="checked" style="Display:none" name="DDOption" />
														
													</div>
													
														<form:hidden path="downloadOption" />
														<span class="errormessage" id="errrdownloadOption"></span>
														<form:errors htmlEscape="true" path="downloadOption" cssClass="error"/>
														
													
											</div>
										</div>
										<!-- Download Option Body end-->
										
										<!-- Download Option full Directory  Started-->
										<div id="DFD" style="Display:none">
											<ul class="form_body" >
				                				<li>
				                					<label>
				                						<spring:message code="Label.DDENTITY" htmlEscape="true"></spring:message>
				                						<span class="mandate">*</span>
				                					</label>
				                					<form:select path="rptFileName" id="rptFileName" >
				                						<form:option value="-1">
															<spring:message code="Label.SEL" htmlEscape="true"></spring:message>
														</form:option>
														<optgroup label="<spring:message  htmlEscape="true" code='Label.STATECAPS'></spring:message>">
															<option value="allStateofIndia" ><spring:message  htmlEscape="true" code='Label.ALLSTATE'></spring:message></option>
														</optgroup>
														<optgroup label="<spring:message  htmlEscape="true" code='Label.DISTRICTCAPS'></spring:message>">
															<option value="allDistrictofIndia" ><spring:message  htmlEscape="true" code='Label.ALLDISTRICT'></spring:message></option>
															<option value="districtofSpecificState@state" ><spring:message  htmlEscape="true" code='Label.ALLDISTRICTSTATE'></spring:message></option>
														</optgroup>
														<optgroup label="<spring:message  htmlEscape="true" code='Label.SUBDISTRICTCAPS'></spring:message>">
															<option value="allSubDistrictofIndia" ><spring:message  htmlEscape="true" code='Label.ALLSUBDISTRICT'></spring:message></option>
															<option value="subDistrictofSpecificState@state" ><spring:message  htmlEscape="true" code='Label.ALLSUBDISTRICTSTATE'></spring:message></option>
															<option value="subDistrictofSpecificDistrict@state#district" ><spring:message  htmlEscape="true" code='Label.ALLSUBDISTRICTDISTRICT'></spring:message></option>
														</optgroup>
														<optgroup label="<spring:message  htmlEscape="true" code='Label.VILLAGECAPS'></spring:message>">
															<option value="allVillagesofIndia" ><spring:message  htmlEscape="true" code='Label.Village.India'></spring:message></option>
															<option value="villageofSpecificState@state" ><spring:message  htmlEscape="true" code='Label.ALLVILLAGESTATE'></spring:message></option>
															<option value="villageofSpecificDistrict@state#district" ><spring:message  htmlEscape="true" code='Label.ALLVILLAGEDISTRICT'></spring:message></option>
															<option value="villageofSpecificSubdistrict@state#district#subdistrict" ><spring:message  htmlEscape="true" code='Label.ALLVILLAGESUBDISTRICT'></spring:message></option>
														</optgroup>
														<optgroup label="<spring:message  htmlEscape="true" code='Label.LOCALBODY'></spring:message>">
															<option value="priLbSpecificState@state" ><spring:message  htmlEscape="true" code='Label.PRISPECIFICSTATE'></spring:message></option>
															<option value="priLbSpecificDistrict@state#district" ><spring:message  htmlEscape="true" code='Label.PRISPECIFICDISTRICT'></spring:message></option>
															<option value="ulbSpecificState@state" ><spring:message  htmlEscape="true" code='Label.ULBSPECIFICSTATE'></spring:message></option>
															<option value="ulbSpecificDistrict@state#district" ><spring:message  htmlEscape="true" code='Label.ULBSPECIFICDISTRICT'></spring:message></option>
															<option value="tlbSpecificState@state" ><spring:message  htmlEscape="true" code='Label.TLBSPECIFICSTATE'></spring:message></option>
															<option value="tlbSpecificDistrict@state#district" ><spring:message  htmlEscape="true" code='Label.TLBSPECIFICDISTRICT'></spring:message></option>
															<option value="uLBWardforState@state" ><spring:message  htmlEscape="true" code='Label.ULBSTATESPECIFICWITHWARD' ></spring:message> </option>
															<option value="uLBWardforStateWithCov@state" ><spring:message  htmlEscape="true" code='ULBSTATESPECIFICWITHWARDWITHCOV' ></spring:message> </option>
															
															
															<option value="priWards@state" ><spring:message  htmlEscape="true" code='Label.PRISTATEWARDS' ></spring:message> </option>
															<option value="priLocalBodyIndia" ><spring:message  htmlEscape="true" code='Label.PRIINDIA'></spring:message></option>
															<option value="allTraditionalLBofInida" ><spring:message  htmlEscape="true" code='Label.TLBINDIA'></spring:message></option>
															
															<option value="urbanLocalBodyIndia" ><spring:message  htmlEscape="true" code='Label.UrbanINDIA'></spring:message></option>
														</optgroup>
														
														
														<optgroup label="<spring:message  htmlEscape="true" code='Label.PARLIAMENTLVSASSEMBLYCONSTITUENCY'></spring:message>">
															<option value="parliamentConstituency@state#parliament" ><spring:message  htmlEscape="true" code='Label.PCWISELOCALBODYMAP'></spring:message></option>
															<option value="assemblyConstituency@state#parliament#assembly" ><spring:message  htmlEscape="true" code='Label.ACWISELOCALBODYMAP'></spring:message></option>
															
														</optgroup>
														
														<optgroup label="<spring:message  htmlEscape="true" code='Label.DDLBMAPPINGCENSUSLANDCODE'></spring:message>">
															<option value="LocalbodyMappingtoCensusLandregionCode@state" ><spring:message  htmlEscape="true" code='Label.DDLBMAPPINGCENSUSLANDCODE'></spring:message></option>
														</optgroup>
														<optgroup label="<spring:message  htmlEscape="true" code='Label.BLOCK'></spring:message>">
															<option value="blockofspecificState@state" ><spring:message  htmlEscape="true" code='Label.ALLBLOCK'></spring:message></option>
														</optgroup>
				                					</form:select>
				                					
				                					<span class="errormessage" id="errrptFileName"></span>
													<form:errors htmlEscape="true" path="rptFileName" cssClass="error"/>
				                				
				                				</li>
				                				
				                				
				                				<li style="Display:none" id="stateLI">
				                					<label>
				                						<spring:message code="Label.SELECTSTATE" htmlEscape="true"></spring:message>
				                						<span class="mandate">*</span>
				                					</label>
													<select id="state" name="entityCodes">
														<option value="-1"><spring:message htmlEscape="true" code="Label.SEL"/></option>
														<c:forEach var="obj" items="${stateList}">
														<option  value="${obj.statePK.stateCode}">
															<c:out value="${obj.stateNameEnglish}" escapeXml="true"></c:out>
														</option>
														</c:forEach>
													</select>
													<span class="errormessage" id="errstate"></span>
												</li>
												
												<li style="Display:none" id="districtLI">
				                					<label>
				                						<spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message>
				                						<span class="mandate">*</span>
				                					</label>
													<select id="district" name="entityCodes">
													
													</select>
													<span class="errormessage" id="errdistrict"></span>
												</li>
												
												<li style="Display:none" id="subdistrictLI">
				                					<label>
				                						<spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message>
				                						<span class="mandate">*</span>
				                					</label>
													<select id="subdistrict" name="entityCodes">
														
													</select>
													<span class="errormessage" id="errsubdistrict"></span>
												</li>
												
												<li style="Display:none" id="parliamentLI">
				                					<label>
				                						<spring:message code="Label.SELECTPARLIAMENTCONSTITUENCY" htmlEscape="true"></spring:message>
				                						<span class="mandate">*</span>
				                					</label>
													<select id="parliament" name="entityCodes">
														
													</select>
													<span class="errormessage" id="errparliament"></span>
												</li>
												
												<li style="Display:none" id="assemblyLI">
				                					<label>
				                						<spring:message code="Label.SELECTPARLIAMENTCONSTITUENCY" htmlEscape="true"></spring:message>
				                						<span class="mandate">*</span>
				                					</label>
													<select id="assembly" name="entityCodes">
														
													</select>
													<span class="errormessage" id="errassembly"></span>
												</li>
				                			</ul>
										
										</div>
										<!-- Download Option full Directory end-->
										
										<!-- Download State wise Directory  Started-->
										<div id="DSWD" style="Display:none">
										
											<ul class="form_body" >
				                				<li>
				                					<label>
				                						<spring:message code="Label.SELECTSTATE" htmlEscape="true"></spring:message>
				                						<span class="mandate">*</span>
				                					</label>
				                					<form:select path="entityCode" id="statewise">
														<form:option value="-1"><spring:message code="Label.SELECT" htmlEscape="true"/></form:option>
														<form:options items="${stateList}"	itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
													</form:select>
													<span class="errormessage" id="errstatewise"></span>
													<form:errors htmlEscape="true" path="entityCode" cssClass="error"/>
				                				</li>
				                				<li>
				                					<div class="long_latitude" >
														<div class="row"> <!-- Max of 4 divs can be used inside this row div -->
															<div class="col">
																<label><form:checkbox path="multiRptFileNames" value="districtofSpecificState"/>
																<spring:message	htmlEscape="true" code="Label.ALLDISTRICTSTATE" /> </label>
															</div>
															<div class="col">
																<label><form:checkbox path="multiRptFileNames" value="subDistrictofSpecificState"/>
																<spring:message	htmlEscape="true" code="Label.ALLSUBDISTRICTSTATE" /></label>
																
															</div>
															<div class="col">
																<label><form:checkbox path="multiRptFileNames" value="villageofSpecificState"/>
																<spring:message	htmlEscape="true" code="Label.ALLVILLAGESTATE" /></label>
															</div>
															
															
														</div>
														<div class="row"> 
															<div class="col">
																<label><form:checkbox path="multiRptFileNames" value="priLbSpecificState"/>
																<spring:message	htmlEscape="true" code="Label.PRISPECIFICSTATE" /></label>
															</div>
															<div class="col">
																<label><form:checkbox path="multiRptFileNames" value="ulbSpecificState"/>
																<spring:message	htmlEscape="true" code="Label.ULBSPECIFICSTATE" /></label>
															</div>
															<div class="col">
																<label><form:checkbox path="multiRptFileNames" value="tlbSpecificState"/>
																<spring:message	htmlEscape="true" code="Label.TLBSPECIFICSTATE" /></label>
															</div>
														</div>
														
														<div class="row"> 
															<div class="col">
																<label><form:checkbox path="multiRptFileNames" value="uLBWardforState"/>
																<spring:message	htmlEscape="true" code="Label.ULBSTATESPECIFICWITHWARD" /></label>
															</div>
															
															<div class="col">
																<label><form:checkbox path="multiRptFileNames" value="uLBWardforStateWithCov"/>
																<spring:message	htmlEscape="true" code="ULBSTATESPECIFICWITHWARDWITHCOV" /></label>
															</div>
															
															<div class="col">
																<label><form:checkbox path="multiRptFileNames" value="priWards"/>
																<spring:message	htmlEscape="true" code="Label.PRISTATEWARDS" /></label>
															</div>
															
															
															
														</div>
														<div class="row"> 
															<div class="col">
																<label><form:checkbox path="multiRptFileNames" value="LocalbodyMappingtoCensusLandregionCode"/>
																<spring:message	htmlEscape="true" code="Label.DDLBMAPPINGCENSUSLANDCODE" /></label>
															</div>
															
															<div class="col">
																<label><form:checkbox path="multiRptFileNames" value="blockofspecificState"/>
																<spring:message	htmlEscape="true" code="Label.ALLBLOCK" /></label>
															</div>
															
															
															<div class="col">
																<label><form:checkbox path="multiRptFileNames" value="allBlockStateWithCoveredVillage1"/>
																<spring:message	htmlEscape="true" code="All Block of State With Covered Vilage" /></label>
															</div>
															
														</div>
														
														<span class="errormessage" id="errmultiRptFileNames"></span>
														<form:errors htmlEscape="true" path="multiRptFileNames" cssClass="error"/>
													</div>
				                				</li>
				                			</ul>
										</div>
										<!-- Download State wise Directory  end-->
										
								</div>
							</div>
							<br/>	
							<div class="form_block">
								<div class="col_1">
									<h4><spring:message code="Label.DDOPTION" htmlEscape="true"></spring:message></h4>
									
									<!-- Download Type Started-->
										<div class="download_option" >
											<div class="row"> 
												<div class="col">
													<label><form:radiobutton path="downloadType" value="${downloadPDF}" checked="checked" /><spring:message code="Label.PDF" htmlEscape="true"/></label>
												</div>
												<div class="col">
													<label><form:radiobutton path="downloadType" value="${downloadHTM}"  /><spring:message code="Label.HTM" htmlEscape="true"/></label>
												</div>
												<div class="col">
													<label><form:radiobutton path="downloadType" value="${downloadXLS}" /><spring:message code="Label.XLS" htmlEscape="true"/></label>
												 </div>
												<div class="col">
													<label><form:radiobutton path="downloadType" value="${downloadCSV}" /><spring:message code="Label.CSV" htmlEscape="true"/></label>
												</div>
												<div class="col">
													<label><form:radiobutton path="downloadType" value="${downloadODT}" /><spring:message code="Label.ODT" htmlEscape="true"/></label>
												</div>
												
											</div>
											<span class="errormessage" id="errdownloadType"></span>
										</div>
									<!-- Download Type end-->
									
				
								</div>	
							</div>
							<br/>	
							<div class="form_block">
								<div class="col_1">
									<h4><spring:message code="Label.CAPTCHA" text="Captcha" htmlEscape="true"></spring:message></h4>
									<ul class="captcha_fields">
										<li><label for="captchaAnswer"><spring:message htmlEscape="true" code="Label.ENTERTEXTASSHOWNINTHISIMAGE" ></spring:message></label></li>
										<li><img src="captchaImage" id="captchaImageId" alt="Captcha Image" border="0"/></li>
										<li>
											<div class="captcha_option" >
												<div class="row"> 
													<div class="col">
													<label><form:input path="captchaAnswer" autocomplete="off" /></label>	
													</div>
													<div class="col">
													<label><img src="${pageContext.request.contextPath}/images/refreshCaptcha.png" id="refCaptchaId" alt="Captcha Image Referesh" border="0" onclick="captchaReferesh();"/></label>	
													</div>
												</div>
											</div>	
											
											<form:errors path="captchaAnswer" cssStyle="color:red"/>
											<c:if test="${serverFlag ne true}">
											<span class="errormessage" id="errcaptchaAnswer"></span>
											</c:if>
											<%-- <img src="<%=contextPath%>/images/refresh.png"	onclick="captchaResetImage('theimage', 'jcaptcha_response');" /> --%>	
										</li>
										
									</ul>
								</div>
							</div>
							
						</div>
					
				<br/>		
				<input class="bttn" id="btnGenreateReport" type="button" value="<spring:message code='Label.GENERATEREPORT' htmlEscape='true'/>" />
				<input class="bttn" id="btnActionClear" type="button" value="<spring:message htmlEscape="true" code="Button.CLEAR"/>"/>
				<input class="bttn" id="btnActionClose" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" />
				</form:form>
			</div>
		</div>
		<!-- Page Content end -->
</div>
<!-- Main Form Stylings end -->
<c:if test="${serverFlag eq true}">
<script>
loadSelectedInformation();
</script>
</c:if>
</body>
</html>