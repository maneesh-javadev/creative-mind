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
<title><spring:message code="Label.CREATEURBANLOCALGOVTBODY" htmlEscape="true"></spring:message></title>
<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<link href="css/errorLB.css" rel="stylesheet" type="text/css" />
<script src="js/localbody_validation.js"></script>
<script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script src="js/lgd_localbody.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<link rel="stylesheet" href="datepicker/demos.css" /> -->

<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
	/* 
	 * Conditional Check whether Logged in state is ULB oprate District wise.
	 * Set value for javascript file reference.
	 */  
	var isDistrictLevel = "${isDistrictLevel}";
</script>
<%@include file="../common/dwr.jsp"%>
</head>

<body oncontextmenu="return false" 
	onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);"> 
<body>	
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
							</div>
						</td>
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
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message code="Label.CREATENEWLOCALGOVTBODY" htmlEscape="true"></spring:message></h3>
			<ul class="listing">
				<%--//these links are not working <li>
					<a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"  ></spring:message> </a>
				</li> --%>
			</ul>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="draftUrbanLocalGovtBody.htm" commandName="localGovtBodyForm" method="POST" enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="draftUrbanLocalGovtBody.htm"/>" />
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<label><spring:message code="Label.GENERALDETAILNEWLOCALBODY" htmlEscape="true"></spring:message></label>
							</div>
							<div class="block">
								<ul class="blocklist">
									<li>
										<input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("stateCode")%>' />
										<form:hidden path="govtOrderConfig" value="${govtOrderConfig}" />
										<input type="hidden" name="districtCode" value="<c:out value='${districtCode}' escapeXml='true'></c:out>"/>
										<label><spring:message code="Label.LOCALBODYNAMEENGLISH" htmlEscape="true"></spring:message> </label><span id="required" class="errormsg"></span><span class="errormsg">*</span><br />
										<form:input path="lgd_LBNameInEn" id="localBodyNameInEn"
										size="40" maxlength="200" cssClass="frmfield"
										onfocus="show_msgEng('localBodyNameInEn')"
										onblur="validateLocalNameInEng()"
										onkeypress="return validateSpecialCharactersUpdateStandardCodeLocal(event);" />
										<div id="localBodyNameInEn_error" class="error"><spring:message code="error.blank.localBodyNameInEn" htmlEscape="true"></spring:message></div>
										<div class="errormsg" id="localBodyNameInEn_error1"><form:errors path="lgd_LBNameInEn" htmlEscape="true"/></div>  
										<div class="errormsg" id="localBodyNameInEn_error2" style="display: none" ></div>
									</li>
									<li>
										<label><spring:message code="Label.LOCALBODYNAMELOCAL" htmlEscape="true"></spring:message> </label><span
										id="required"></span><br /> <form:input
										path="lgd_LBNameInLocal" id="localBodyNameInLcl" size="40"
										maxlength="100" cssClass="frmfield"
										onfocus="show_msg('localBodyNameInLcl')" 
										onblur="validateSpecialCharactersUpdateStandardCodeLocal(this.value);" />
									</li>
									<li>
										<label><spring:message code="Label.LOCALBODYALIASENGLISH" htmlEscape="true"></spring:message> </label><span
										id="required"></span><br /> <form:input
										path="lgd_LBAliasInEn" id="localBodyAliasInEn" size="40"
										maxlength="50" cssClass="frmfield"
										onfocus="show_msg('localBodyAliasInEn')" onblur="AliasInEn()"
										onkeypress="return validateSpecialCharactersUpdateStandardCodeLocal(event);" />
									</li>
									<li>
										<label><spring:message code="Label.LOCALBODYALIASLOCAL" htmlEscape="true"></spring:message> </label><span
										id="required"></span><br /> <form:input
										path="lgd_LBAliasInLocal" id="localBodyAliasInLcl" size="40"
										maxlength="50" cssClass="frmfield"
										onfocus="show_msg('localBodyAliasInLcl')"
										onblur="validateSpecialCharactersLBNameAliasLocal(this.value);" />
									</li>
									<li>
										<label><spring:message htmlEscape="true" code="Label.STATESPECIFICCODE"></spring:message> </label><br /> <form:input
										path="lgd_LBstateSpecificCode" id="stateSpecificCode"
										size="40" maxlength="7" cssClass="frmfield" htmlEscape="true"
										onfocus="show_msg('stateSpecificCode')"
										onblur="specificCode()" 
										onkeypress="return validateAlphanumericKeysStateSp(event);"/>
									</li>
								</ul>
							</div>
							
						</div>
					</div>
					<div id='divLgdLBType' class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.SELECTEDTYPELOCALBODY" htmlEscape="true"></spring:message>
							</div>
							<div>
								<ul class="blocklist">
									<li>
										<label><spring:message code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message><span
										class="errormsg">*</span> </label><br /> <form:select
										path="lgd_LBTypeName" id="ddLgdLBType" htmlEscape="true"
										onchange="hideShowDivUrban(this.value);" class="combofield">
										<!--tierSetupCode id Changed  -->
										<form:option value="0" htmlEscape="true">
											<spring:message code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message>
										</form:option>

										<c:forEach var="localBodyTypelist" varStatus="var"
											items="${localBodyTypelist}">
											<form:option id="typeCode" htmlEscape="true"
												value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.nomenclatureEnglish}"><esapi:encodeForHTMLAttribute>${localBodyTypelist.nomenclatureEnglish}</esapi:encodeForHTMLAttribute></form:option>
		    					
										</c:forEach>
										</form:select>&nbsp;&nbsp; 
										<div id="ddLgdLBType_error" class="error"><spring:message code="error.SOURCESELECTLOCALBODYTYPE" htmlEscape="true"></spring:message></div>
										<div class="errormsg" id="ddLgdLBType_error1"><form:errors path="lgd_LBTypeName" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddLgdLBType_error2" style="display: none" ></div>
									</li>
									<li id="divLgdlistSubTypeCode" class="frmpnlbg" style="visibility: hidden; display: none;">
										<label><spring:message code="Label.AVAILSUBTYPE" htmlEscape="true"></spring:message></label> <br /> <form:select path="localbodySubtype" htmlEscape="true" cssClass="combofield" id="ddlgdLBSubTypeCode" ></form:select> <br />
									</li>
								</ul>
							</div>
						</div>
					</div>
					<%@ include file="../common/gis_nodesforlocalbody.jspf"%>  

					<div id='divLgdLBCoveredAreaforUrban' class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.COVEREDAREAOFNEWLCLBDY" htmlEscape="true"></spring:message>
							</div>
							<div class="block">
								<ul class="blocklist">
									<li>
										<div id="chkLgdLBExistChk_error" class="error"><spring:message code="error.EXISTINGLOCALBODY" htmlEscape="true"></spring:message></div>
									</li>
									<li>
										<form:checkbox id="chkLgdLBExistChk"
										onclick="getHideLocalBodyParentListforUrban(document.getElementById('ddLgdLBType').value,this.checked);"
										value="true" path="lgd_LBExistCheck"></form:checkbox><label><spring:message
											code="Label.SELEXISTINGCOVEREDAREA" htmlEscape="true"></spring:message>
										</label>	
										<div class="errormsg" id="chkLgdLBExistChk_error1"><form:errors path="lgd_LBExistCheck" htmlEscape="true"/></div>  
										<div class="errormsg" id="chkLgdLBExistChk_error2" style="display: none" ></div>
									</li>
									<%-- <li>
										<form:checkbox value="true" onclick="getHidePartiallyMappedforUrban(document.getElementById('ddLgdLBType').value,this.checked)" id='chkLgdLBUnmapped' path="lgd_LBUnmappedCheck" /><label>
										<spring:message code="Label.SELCONTRIBUTEUNMAPPEDPARTIALLY" htmlEscape="true"></spring:message>
										</label>			
										<div class="errormsg" id="chkLgdLBUnmapped_error1"><form:errors path="lgd_LBUnmappedCheck" htmlEscape="true"/></div>  
								 		<div class="errormsg" id="chkLgdLBUnmapped_error2" style="display: none" ></div>	
									</li> --%>
									<li>
										<div id="ddLgdUrbanLBDistExistDest_error" class="error"><spring:message code="error.blank.DistrictPanchayt" htmlEscape="true"></spring:message></div>
										<div class="errormsg" id="ddLgdUrbanLBDistExistDest_error1"><form:errors path="lgd_UrbanLBDistExistDest" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddLgdUrbanLBDistExistDest_error2" style="display: none" ></div> 
										<div id="ddLgdUrbanLBDistExistDest1_error" class="error"><spring:message code="error.DESTDISTLOCALBODY" htmlEscape="true"></spring:message></div>
										<div class="errormsg" id="ddLgdUrbanLBDistExistDest1_error1"><form:errors path="lgd_UrbanLBDistExistDest" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddLgdUrbanLBDistExistDest1_error2" style="display: none" ></div> 
										
									</li>
								</ul>
							</div>
								
							<div id='divLgdLBVExist' class="frmpnlbg" style="visibility: hidden; display: none;">
								<ul class="blocklist">
									<li>
										<div class="ms_container">
											<div class="ms_selectable">
												<label>
													<spring:message code="Label.AVAILABLEURBANLB" htmlEscape="true"></spring:message>
													<spring:message code="Label.LIST" htmlEscape="true"></spring:message>
													<span class="errormsg">*</span><br />
												</label>
												<form:select path="lgd_UrbanLBDistExistSource" class="frmtxtarea" id="ddLgdUrbanLBDistExistSource"  multiple="true" htmlEscape="true">
												</form:select><br /> <br />
											</div>
											
											
											<div class="ms_buttons">
												<label> 
													<input type="button" class="bttn" value="<spring:message code="Button.WHOLE"/>" onclick="addItemULBFinalFULL('ddLgdUrbanLBDistExistDest','ddLgdUrbanLBDistExistSource','FULL',true);" />
												</label>
												
												<label>
													<input type="button" class="bttn" id="btnremoveOneULB" name="Submit4" value="Back &lt;" onclick="removeItemUrbanLB('ddLgdUrbanLBDistExistDest','ddLgdUrbanLBDistExistSource',true)" />
												</label>
												
												<label> 
													<input type="button" class="bttn" value="<spring:message code="Button.RESET"  ></spring:message>" onclick="removeAllUrbanLB('ddLgdUrbanLBDistExistDest', 'ddLgdUrbanLBDistExistSource', true)" />
												</label>
												
												<label>
													<input type="button" class="bttn" value="Part &gt;&gt;" onclick="addItemULBFinal('ddLgdUrbanLBDistExistDest','ddLgdUrbanLBDistExistSource', 'PART',true);" />
												</label>
											</div>
											
											<div class="ms_selection">
												<label>
													<spring:message code="Label.CONTRIBUTEURBANLB" htmlEscape="true"></spring:message> &nbsp;<spring:message code="Label.LIST" htmlEscape="true"></spring:message>
													<span class="errormsg">*</span><br />
												</label>
												<form:select name="select6" id="ddLgdUrbanLBDistExistDest" size="1" multiple="true" path="lgd_UrbanLBDistExistDest" class="frmtxtarea" htmlEscape="true">
												</form:select> 
												<input type="button" class="bttn" value="<spring:message code="Button.GETCOVEREDAREAOFLOCALBODY"/>" onclick="selectLocalBodyListforUrban();" />
											</div>
										</div>
									</li>
									<li>
										<div class="ms_container">
											<div class="ms_selectable">
												<label>
													<c:choose>
														<c:when test="${isDistrictLevel}"><spring:message code="Label.AVAILDISTRICTLIST" htmlEscape="true"></spring:message></c:when>
														<c:otherwise><spring:message code="Label.AVAILSUBDISTRICTLIST" htmlEscape="true"></spring:message></c:otherwise>
													</c:choose>
												</label>
												<form:select path="lgd_UrbanLBSubdistrictListSource" class="frmtxtarea" id="ddLgdUrbanLBSubdistrictListSource" multiple="true" htmlEscape="true">
												</form:select><br/><br/>
											</div>
											
											
											<div class="ms_buttons">
												<label> 
													<input type="button" class=" bttn" value="<spring:message code="Button.WHOLE"/>" onclick="addItemULBFinalSubDFULL('ddLgdUrbanLBSubdistrictListDest','ddLgdUrbanLBSubdistrictListSource','FULL',true);" />
												</label>
												
												<label>
													<input type="button" class=" bttn"  id="btnremoveOneULB" name="Submit4" value="Back &lt;" onclick="removeItemListUrbanSDist('ddLgdUrbanLBSubdistrictListDest','ddLgdUrbanLBSubdistrictListSource',true)" />
												</label>
												
												<label> 
													<input type="button" class=" bttn" value="<spring:message code="Button.RESET" ></spring:message>" onclick="removeAllUrbanSDist('ddLgdUrbanLBSubdistrictListDest', 'ddLgdUrbanLBSubdistrictListSource', true)" />
												</label>
												
												<label>
													<input type="button" class=" bttn" value="Part &gt;&gt;" onclick="addItemULBFinalSubD('ddLgdUrbanLBSubdistrictListDest','ddLgdUrbanLBSubdistrictListSource', 'PART',true);" />
												</label>
											</div>
											
											<div class="ms_selection">
												<label>
												<c:choose>
													<c:when test="${isDistrictLevel}"><spring:message code="Label.CONTRIBUTDISTRICTLIST" htmlEscape="true"></spring:message></c:when>
													<c:otherwise><spring:message code="Label.CONTRIBUTSUBDISTRICTLIST" htmlEscape="true"></spring:message></c:otherwise>
												</c:choose>
												</label><br/> 
												<form:select name="select6" id="ddLgdUrbanLBSubdistrictListDest" size="1" multiple="true" path="lgd_UrbanLBSubdistrictListDest" class="frmtxtarea" htmlEscape="true">
												</form:select><br/>
											</div>
										</div>
									</li>
									<li>
										<div class="frmpnlbgInterhidden" style="visibility: hidden; display: none;">
											<!-- <div class="frmpnlbgInterhidden">   -->
						 					<form:select htmlEscape="true"
												id="ddLgdSubDestListhiddenULB" size="1" multiple="true"
												path="lgd_LBInterSubDestListhidden" class="frmtxtarea">
											</form:select>
										</div>
									</li>
								</ul>
								<div class="clear"></div>
							</div>
							<ul class = "blocklist">
								<li>
									<form:checkbox value="true" onclick="getHidePartiallyMappedforUrban(document.getElementById('ddLgdLBType').value,this.checked)" id='chkLgdLBUnmapped' path="lgd_LBUnmappedCheck" /><label>
									<spring:message code="Label.SELCONTRIBUTEUNMAPPEDPARTIALLY" htmlEscape="true"></spring:message>
									</label>			
									<div class="errormsg" id="chkLgdLBUnmapped_error1"><form:errors path="lgd_LBUnmappedCheck" htmlEscape="true"/></div>  
							 		<div class="errormsg" id="chkLgdLBUnmapped_error2" style="display: none" ></div>	
								</li>
							</ul>	
							<div id='divLgdLBVUmapped' class="frmpnlbg" style="visibility: hidden; display: none;">
								<ul class="blocklist">
									<li>
										<div id="ddLgdUrbanLBDistUmappedDest_error" class="error"><spring:message code="error.PSSDT" htmlEscape="true"></spring:message></div>
										<div class="errormsg" id="ddLgdUrbanLBDistUmappedDest_error1"><form:errors path="lgd_UrbanLBDistUmappedDest" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddLgdUrbanLBDistUmappedDest_error2" style="display: none" ></div>	 
									</li>
									<li>
										<div class="ms_container">
											<div class="ms_selectable">
												<label>
													<c:choose>
														<c:when test="${isDistrictLevel}"><spring:message code="Label.AVAILDISTRICTLIST" htmlEscape="true"></spring:message></c:when>
														<c:otherwise><spring:message code="Label.AVAILSUBDISTRICTLIST" htmlEscape="true"></spring:message></c:otherwise>
													</c:choose>
												</label> <br /> 
												<form:select path="lgd_UrbanLBDistUmappedSource" class="frmtxtarea" id="ddLgdUrbanLBDistUmappedSource"  multiple="true" htmlEscape="true">
												</form:select><br />
											</div>
											
											
											<div class="ms_buttons">
												<label> 
													<input type="button" class="bttn" value="<spring:message code="Button.WHOLE"/>" onclick="addItemforLBFinal('ddLgdUrbanLBDistUmappedDest','ddLgdUrbanLBDistUmappedSource','FULL',true);" />
												</label>
												
												<label>
													<input type="button" class="bttn" id="btnremoveOneULB" name="Submit4" value="Back &lt;" onclick="removeItemUnmappedSDist('ddLgdUrbanLBDistUmappedDest','ddLgdUrbanLBDistUmappedSource',true)" />
												</label>
												
												<label> 
													<input type="button" class="bttn" value="<spring:message code="Button.RESET"></spring:message>" onclick="removeAllUnmappedSDist('ddLgdUrbanLBDistUmappedDest', 'ddLgdUrbanLBDistUmappedSource', true)"/>
												</label>
												
												<label>
													<input type="button" class="bttn" value="Part &gt;&gt;" onclick="addItemforLBFinal('ddLgdUrbanLBDistUmappedDest','ddLgdUrbanLBDistUmappedSource', 'PART',true);" />
												</label>
											</div>
											
											<div class="ms_selection">
												<label>
													<c:choose>
														<c:when test="${isDistrictLevel}"><spring:message code="Label.CONTRIBUTDISTRICTLIST" htmlEscape="true"></spring:message></c:when>
														<c:otherwise><spring:message code="Label.CONTRIBUTSUBDISTRICTLIST" htmlEscape="true"></spring:message></c:otherwise>
													</c:choose>
												</label>
												<span class="errormsg">*</span><br/> 
												<form:select name="select6" id="ddLgdUrbanLBDistUmappedDest" size="1" multiple="true" path="lgd_UrbanLBDistUmappedDest" class="frmtxtarea" htmlEscape="true">
												</form:select><br/>
											</div>
										</div>
									</li>
								</ul>
								<div class="clear"></div>						
							</div>
						</div>
					</div>
					<div class="btnpnl">
						<label> <input type="submit" name="Submit"
							value="<spring:message code="Button.SAVE"  ></spring:message>"
							onclick="return validateUrbanLocalbodyAll()" />
						</label>
						<label> <input type="button" class="btn"
							name="Submit6"
							value="<spring:message code="Button.CLEAR"  ></spring:message>"
							onclick="javascript:go('createLocalBodyforUrban.htm');" />
						</label> <label> <input type="button" class="btn"
							name="Submit6"
							value="<spring:message code="Button.CLOSE"  ></spring:message>"
							onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
						</label>
					</div>
				</div>
			</form:form>
		<script src="/LGD/JavaScriptServlet"></script>
	</div>
</div>
</body>
</html>