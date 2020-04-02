<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page import="java.util.*,in.nic.pes.lgd.bean.GetLocalGovtSetup"%>
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<title>Convert RLB to ULB</title>
<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<script src="js/validation.js"></script>

<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<link href="css/error.css" rel="stylesheet" type="text/css" />

<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>

<script src="datepicker/jquery.ui.widget.js"></script>

<script type="text/javascript" language="javascript">
var level = "<c:out value='${ddLevel}'/>";

function open_win() {
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
} 

$(document).ready(function() {
	$("#convertForm").validate({ 
    rules: { 
    	localBodyNameInEn:{
    		onlyLetterNumberWithDot:true
    	},
    	localBodyNameInLcl:{
    	   nameFormatLocal: true
       },
       localBodyliasInLcl:{
    	   nameFormatLocal: true
       }
    }, 
    messages: { 
    	localBodyNameInEn: {
    		onlyLetterNumberWithDot: "<font color='red'><br><spring:message code='error.enter.valid.format' text='Please enter value in correct format'/></font>"
       },
    	localBodyNameInLcl: {
    		nameFormatLocal: "<font color='red'><br><spring:message code='TRG.req.trg.type' text='Please enter in local language only'/></font>"
       },
       localBodyliasInLcl: {
    	   nameFormatLocal: "<font color='red'><br><spring:message code='TRG.req.trg.need' text='Please enter in local language only'/></font>"
       }
    } 
  });
 });  

</script>
<script src="js/convertRLBtoULB.js"></script>
</head>
<body onsubmit="cursorwait();" oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);" >

	<div class="overlay" id="overlay1" style="display: none;"></div>
	<div class="box" id="box1">
		<a class="boxclose" id="boxclose1"></a>
		<div>
			<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<div class="success"></div>
							</center>
						</td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div>
						</td>
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
						<td rowspan="2"><div class="failur"></div>
						</td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center>
						</td>
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
					<td rowspan="2"><div class="errorImg"></div>
					</td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div>
					</td>
				</tr>
				<tr>
					<td><div class="errorfont">Please enter required fields</div>
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
					<h3 class="subtitle"><spring:message code="Label.CONVERTTLBTOULB" htmlEscape="true"></spring:message></h3>
										 <ul id="showhelp" class="listing">
					 				        <%--//these links are not working <li>
									 				<a href="#" rel="toggle[cat]"	data-openimage="images/minus.jpg"		data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /> </a>
									 		</li>
					 				        
					 				        <li>
					 				        			<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
					 				        </li>
					 						<li>
					 							<a href="#" class="frmhelp"><spring:message	code="Button.HELP" htmlEscape="true"></spring:message> </a>
					 						
					 						</li> --%>
					 			        </ul>
		
		
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
		
			<form:form commandName="convertTLBtoULB" action="draftConversionTLBtoULB.htm" method="POST" enctype="multipart/form-data" name="convertForm" id="convertForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="draftConversionTLBtoULB.htm"/>" />
				<input type="hidden" id="stateid" name="stateid" value="<c:out value='${stateCode}' escapeXml='true'></c:out>" />
				<div id="cat">
					<div id="divFirstPanel" class="frmtxt">
						<div class="frmpnlbg" id="divDIV">
							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message code="Label.CONVERSIONTLB" htmlEscape="true"></spring:message>
								</div>

								<form:hidden path="hdnState" value="${stateCode}" id="hdnState" htmlEscape="true"/>
								<form:hidden path="govtOrderConfig" value="${govtOrderConfig}" htmlEscape="true"/>
								<form:hidden path="mergeUlbClick" htmlEscape="true" value="${convertTLBtoULB.mergeUlbClick}" id="mergeUlbClick" />
								<form:hidden path="districtLocalBodyCode" htmlEscape="true" value="${convertTLBtoULB.districtLocalBodyCode}" id="hdnDistrictCode" />
								<form:hidden path="interLocalBodyCode" htmlEscape="true" value="${convertTLBtoULB.interLocalBodyCode}" id="hdnInterLBCode" />
								<form:hidden path="declareUlbClick" htmlEscape="true" value="${convertTLBtoULB.declareUlbClick}" id="declareUlbClick" />
								<form:hidden path="operationCode" htmlEscape="true" value="${convertTLBtoULB.operationCode}" ></form:hidden>
								<form:hidden path="lbType" value="${convertTLBtoULB.lbType}" htmlEscape="true"></form:hidden>
								
									<div >
										<ul class="blocklist">
											<c:if test="${ddLevel.contains('D')}">
													<li >
													<label><spring:message htmlEscape="true" code="Label.SELECTDISTICTPANCHAYAT"></spring:message></label><span class="errormsg">*</span><br />
															<form:select
																	id="ddDistrictPanchayat" path="districtLocalBodies"
																	class="combofield" htmlEscape="true"
																	onchange="getIntermediatePanchayatbyDcode(this.value);"
																	onfocus="validateOnFocus('ddDistrictPanchayat');helpMessage(this,'ddDistrictPanchayat_msg');"
																	onblur="vlidateOnblur('ddDistrictPanchayat','1','15','c');hideHelp();"
																	onkeyup="hideMessageOnKeyPress('ddDistrictPanchayat')">
																	<form:option value="0" htmlEscape="true">
																		<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
																	</form:option>
																	
																	<%-- 	
																	<form:options items="${districtPanchayatList}"
																		itemLabel="localBodyNameEnglish"
																		itemValue="localBodyCode" />
																	--%>
																	<c:forEach items="${districtPanchayatList}" var="districtPanchayatList">
																			<c:if test="${districtPanchayatList.operation_state =='A'.charAt(0)}">
																				<form:option value="${districtPanchayatList.localBodyCode}" htmlEscape="true"><esapi:encodeForHTMLAttribute>${districtPanchayatList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute>
																				</form:option>
																			</c:if>
																			<c:if test="${districtPanchayatList.operation_state =='F'.charAt(0)}">
																				<form:option value="${districtPanchayatList.localBodyCode}" htmlEscape="true" disabled="true"><esapi:encodeForHTMLAttribute>${districtPanchayatList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute>																			
																				</form:option>
																			</c:if>
																	</c:forEach>
																		
																		
																</form:select>
																&nbsp;&nbsp;
																<span>
																	<form:errors path="districtLocalBodies" class="errormsg" htmlEscape="true"></form:errors>
																</span>
																<span id="ddDistrictPanchayat_error" class="errormsg"><spring:message code="Error.DISTRICTLOCALBODY" htmlEscape="true"></spring:message>	</span>
																<span id="ddDistrictPanchayat_msg" style="display: none;">
																	<spring:message code="Error.DISTRICTLOCALBODY" htmlEscape="true"></spring:message>
																</span>
																
														</li>
													</c:if>
														
														<c:if test="${ddLevel.contains('DI') || ddLevel.contains('IV')}">
														<li>
															<label><spring:message code="Label.SELINTERMPANCHYAT" htmlEscape="true"></spring:message> </label>
															<span class="errormsg">*</span><br />
															<form:select
																	id="ddIntermediateLb" path="intermediateLocalBodies"
																	class="combofield" htmlEscape="true"
																	onchange="getVillageLocalBodies(this.value)"
																	onfocus="validateOnFocus('ddIntermediateLb');helpMessage(this,'ddIntermediateLb_msg');"
																	onblur="vlidateOnblur('ddIntermediateLb','1','15','c');hideHelp();"
																	onkeyup="hideMessageOnKeyPress('ddIntermediateLb')">
	
																	<form:option value="0" htmlEscape="true">
																		<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
																	</form:option>
																	<form:options items="${interPanchayatList}" htmlEscape="true"
																		itemLabel="localBodyNameEnglish"
																		itemValue="localBodyCode" />
	
																</form:select>&nbsp;&nbsp;<span><form:errors htmlEscape="true"
																		path="intermediateLocalBodies" class="errormsg"></form:errors>
															</span> <span id="ddIntermediateLb_error" class="errormsg">
																	<spring:message code="Error.INTERMEDIATELOCALBODY" htmlEscape="true"></spring:message>
															</span> <span id="ddIntermediateLb_msg" style="display: none;">
																	<spring:message code="Error.INTERMEDIATELOCALBODY" htmlEscape="true"></spring:message>
															</span>
															</li>
													</c:if>
													
												<li>
															<div class="ms_container">
																		<div class="ms_selectable">
																									<label><spring:message htmlEscape="true"
																				code="Label.AVAILVILLAGEPANCHAYATLIST"></spring:message> </label><br /> <form:select
																			id="ddSourceVillageRLBS" items="${villageLBList}"
																			itemLabel="localBodyNameEnglish"
																			itemValue="localBodyCode"
																			path="villagelocalbodyNameSource" size="1"
																			multiple="multiple" class="frmtxtarea" htmlEscape="true">
																		</form:select>
																		</div>
																		<div class="ms_buttons">
																					<input
																		type="button"  class="bttn" value="<spring:message
																code="Button.WHOLE"/>"
																		onclick="addItemULB('ddDestVillageRLBs','ddSourceVillageRLBS','FULL',true);" />

																		<input type="button" class="bttn"  id="btnback"
																		name="Submit4"  value="Back &lt;"
																		onclick="removeItem('ddDestVillageRLBs', 'ddSourceVillageRLBS', true)" />

																		<input type="button" class="bttn" value=" Reset &lt;&lt;"
																	
																		onclick="removeAll('ddDestVillageRLBs', 'ddSourceVillageRLBS', true)" />
																		<input type="button" class="bttn" value="Part &gt;&gt;"
																		
																		onclick="addItemULB('ddDestVillageRLBs','ddSourceVillageRLBS', 'PART',true);" />
																		</div>
																		<div class="ms_selection">
																									<label><spring:message htmlEscape="true"
																				code="Label.SELECTEDRLBS"></spring:message> </label><span
																		class="errormsg">*</span><br /> <form:select
																			path="villagelocalbodyNameDest"
																			id="ddDestVillageRLBs" size="1" multiple="multiple"
																			class="frmtxtarea" htmlEscape="true"
																			onfocus="validateOnFocus('ddDestVillageRLBs');helpMessage(this,'ddDestVillageRLBs_msg');"
																			onblur="vlidateOnblur('ddDestVillageRLBs','1','15','p');hideHelp();"
																			onkeyup="hideMessageOnKeyPress('ddDestVillageRLBs')">

																		</form:select><br />
																	<span> <form:errors htmlEscape="true"
																				path="villagelocalbodyNameDest" class="errormsg"></form:errors>
																	</span> <span id="ddDestVillageRLBs_error" class="errormsg">
																			<spring:message code="Error.TARGETVILLAGELB" htmlEscape="true"></spring:message>
																	</span> <span id="ddDestVillageRLBs_PART_error"
																		class="errormsg"> <spring:message htmlEscape="true"
																				code="error.select.VILLAGEPANCHAYT"></spring:message>
																	</span> <span id="ddDestVillageRLBs_msg"
																		style="display: none;"> <spring:message
																				code="Error.TARGETVILLAGELB" htmlEscape="true"></spring:message> </span>
																				<br></br>
																				
																				<input type="button" id="childbtn" value="<spring:message code="Button.GETCOVEREDAREAOFLOCALBODY" htmlEscape="true"/>"  onclick="selectVillageLocalBodyforrlbtoulb();"/>
																		</div>																		
															</div>
											
												</li>
											<li></li>
											<li></li>
										</ul>
										
									<div class="clear"></div>
									
												<ul class="blocklist">
												
														<li></li>
														<li>
																			<div class="ms_container">
																						<div class="ms_selectable">
																									<label><spring:message htmlEscape="true" 	code="Label.AVAILBALECOVEREDAREA"></spring:message> </label><br />
														<form:select id="ddSourceCoveredAreaRLB" path="localBodyLandRegionAreaSource" size="1" multiple="multiple" class="frmtxtarea" htmlEscape="true">
														</form:select>
														<span class="errormsg" id="ddSourceCoveredAreaRLB_error"><spring:message
																code="Error.TARGETVILLAGELB" htmlEscape="true"></spring:message> </span> <form:errors
															path="localBodyLandRegionAreaSource" class="errormsg" htmlEscape="true"></form:errors>
																						</div>
																						<div class="ms_buttons">
																												<input type="button" class="bttn" id="btnAdd" value=" &gt;&gt;" onclick="listbox_moveacross('ddSourceCoveredAreaRLB', 'ddDestCoveredAreaRLB', this)" />
													 <input type="button" class="bttn"
														value=" &lt;&lt;" 
														onclick="listbox_moveacross('ddDestCoveredAreaRLB', 'ddSourceCoveredAreaRLB')" />
																						</div>
																						<div class="ms_selection">
																											<label><spring:message htmlEscape="true"
																code="Label.SELECTCOVEREDAREA"></spring:message> </label><br />
														<form:select path="localBodyLandRegionAreaDest"
															id="ddDestCoveredAreaRLB" size="1" multiple="multiple"
															class="frmtxtarea" htmlEscape="true"
															onfocus="validateOnFocus('ddDestCoveredAreaRLB');helpMessage(this,'ddDestCoveredAreaRLB_msg');"
															onblur="vlidateOnblur('ddDestCoveredAreaRLB','1','15','p');hideHelp();"
															onkeyup="hideMessageOnKeyPress('ddDestCoveredAreaRLB')">

														</form:select><br /> <span class="errormsg"
														id="ddDestCoveredAreaRLB_error"><spring:message htmlEscape="true"
																code="Error.TARGETCOVEREDAREA"></spring:message> </span> <form:errors
															path="localBodyLandRegionAreaDest" class="errormsg" htmlEscape="true"></form:errors>

														<span style="display: none;" id="ddDestCoveredAreaRLB_msg"><spring:message
																code="Error.TARGETCOVEREDAREA" htmlEscape="true"></spring:message> </span>
																						</div>																		
																			</div>
							
														
														
														</li>
														
												
												
												</ul>
									
									
									<div class="clear"></div>
									
									
										
									</div>
						
							</div>

							<div class="btnpnl">
									<label> <input type="button" name="Submit" id="btnNext" onclick="return validateFirstAll('T');" value="<spring:message code="Button.NEXTSTEP" htmlEscape="true"></spring:message>" /></label>
												<label> <input type="button" class="btn" name="Submit6" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"/> </label>
										
							</div>
						</div>
					</div>
					
					<!-- ---------------------------------------------------------This section is for click on proceed button event -------------------------------------------------------------->
					
					<div id="divSecondPanel" style="display: none">
						<div class="frmpnlbg">
							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message code="Label.SELURBANLOCALBODY" htmlEscape="true"></spring:message>
								</div>
								
								<div >
									<ul class="blocklist" >
											<li>
													<form:radiobutton path="mergeRLBtoULB" htmlEscape="true"
												id="rdmergeRLBtoULB" value="M"
												onclick="radioClick(this.value);" /> &nbsp; <spring:message
												code="Label.MERGERLBTOULB" htmlEscape="true"></spring:message> <br /> <form:radiobutton
												path="declarenewULB" id="rddeclarenewULB"
												onclick="radioClick(this.value);" value="N" htmlEscape="true"/> &nbsp; <spring:message
												code="Label.DECLARENEWULB" htmlEscape="true"></spring:message>
											</li>
											<li>
													<span id="rdmergeRLBtoULB_error" class="errormsg"></span>
											<form:errors path="mergeRLBtoULB" class="errormsg" htmlEscape="true"></form:errors>
											<form:errors path="declarenewULB" class="errormsg" htmlEscape="true"></form:errors>
											</li>
											<li></li>
											
									</ul>
								
								</div>
								
								<div class="clear"></div>
							</div>
						</div>
						<div class="frmpnlbg" id="divmergeRLB">
							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message code="Label.EXISTINGULB" htmlEscape="true"></spring:message>
								</div>
								
								<div >
								
										<ul class="blocklist">
										
												<li><spring:message
												code="Label.SELURBANTYPEBODY" htmlEscape="true"></spring:message><span
											class="errormsg">*</span><br /> <form:select
												id="ddUrbanLocalBodyType" path="urbanLgTypeName" size="1"
												class="combofield"
												onchange="getUrbanLocalBodies(this.value)" htmlEscape="true">
												<form:option value="0" htmlEscape="true">
													<spring:message code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message>
												</form:option>
												<form:options items="${urbanlocalbodyType}"
													itemLabel="localBodyTypeName" itemValue="localBodyTypeCode" htmlEscape="true"/>
											</form:select> <span id="ddUrbanLocalBodyType_error" class="errormsg"><spring:message
													code="Error.URBANLBTYPE" htmlEscape="true"></spring:message> </span> <form:errors
												path="urbanLgTypeName" class="errormsg" htmlEscape="true"></form:errors>
												</li>
												<li>
													<spring:message
												code="Label.SELURBANLOCALBODY" htmlEscape="true"></spring:message><span
											class="errormsg">*</span><br />
											<form:select id="ddUrbanLocalBody" path="urbanlocalBodyNameEnglish" size="1" class="combofield" htmlEscape="true" onchange="askForUpgrade(this);">
												<form:option value="0" htmlEscape="true">
													<spring:message code="Label.SELURBANLOCALBODY" htmlEscape="true"></spring:message>
												</form:option>
											</form:select>
											<span id="ddUrbanLocalBody_error" class="errormsg"> <spring:message
													code="Error.URBANLB" htmlEscape="true"></spring:message> </span> <form:errors
												path="urbanlocalBodyNameEnglish" class="errormsg" htmlEscape="true"></form:errors>
												
												</li>
												<li id="trForUpgrade1" style="display:none;" >
														<spring:message code="Label.ASKQUESTION" htmlEscape="true" text="Do you want to upgrade?"></spring:message><span class="errormsg">*</span><br/>
											<spring:message code="App.YES" htmlEscape="true" text="Yes"/>&nbsp;&nbsp;<form:radiobutton id="upgradeType" path="upgradeType" value="Y" onclick="selectForUpgrade(this)"/>&nbsp;&nbsp;&nbsp;&nbsp;
											<spring:message code="App.NO" htmlEscape="true" text="No"/>&nbsp;&nbsp;<form:radiobutton id="upgradeType" path="upgradeType" value ="N" onclick="selectForUpgrade(this)"/>
											<span id="upgradeType_error" class="errormsg"><spring:message code="Label.SELECTRURALBODY" htmlEscape="true"></spring:message></span>
												
												</li>
												<li  id="trForUpgrade2" style="display:none;">
												
																<spring:message code="Label.SELUPGRADEURBANLOCALBODY" htmlEscape="true" text="Select for upgrade"></spring:message><span class="errormsg">*</span>
										<br />
											<form:select id="ddUrbanLocalBodyTypeForUpgrade" path="urbanLgTypeName" size="1" class="combofield" disabled="true" htmlEscape="true">
												<form:option value="0" htmlEscape="true"><spring:message code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message></form:option>
												<form:options items="${urbanlocalbodyType}" itemLabel="localBodyTypeName" itemValue="localBodyTypeCode" htmlEscape="true"/>
											</form:select>
											<span id="ddUrbanLocalBodyTypeForUpgrade_error" class="errormsg"><spring:message code="Error.URBANLBTYPE" htmlEscape="true"></spring:message></span>
											<form:errors path="urbanLgTypeName" class="errormsg" htmlEscape="true"></form:errors>
												
												</li>
												<li></li>
										
										</ul>
										
								
								
								
								</div>
							<div class="clear"></div>
							</div>
						</div>



						<div id="divdeclareNewULB" class="frmpnlbg">

							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message code="Label.NEWULB" htmlEscape="true"></spring:message>
								</div>
								<!-- For  next button -->
								<div >
											<ul class="blocklist">
											<li>
														
										<spring:message code="Label.LOCALBODYNAMEENGLISH" htmlEscape="true"></spring:message>
										<span id="required" class="errormsg"></span><span class="errormsg">*</span><br />
											<form:input path="localBodyNameInEn" htmlEscape="true" id="txtlocalBodyNameInEn" size="40" cssClass="frmfield" onkeypress="ULbTypeDefualtSet();" />
											<div id="UniqueULBrror" class="errormsg"></div>
											
											<span class="errormsg" id="txtlocalBodyNameInEn_error">
												<spring:message htmlEscape="true" code="Error.URBANLBNEW"></spring:message>
											</span>
											
											<span>
												<form:errors path="localBodyNameInEn" class="errormsg" htmlEscape="true"></form:errors>
											</span>
											</li>
											<li>
															<spring:message code="Label.LOCALBODYNAMELOCAL" htmlEscape="true"></spring:message>
											<br/>
												<form:input 	path="localBodyNameInLcl" id="txtlocalBodyNameInLcl" htmlEscape="true" 	size="40" cssClass="frmfield" /><span>
												<form:errors path="localBodyNameInLcl" class="errormsg"/></span>
											
											</li>
											<li>
											<spring:message	code="Label.LOCALBODYALIASENGLISH" htmlEscape="true"></spring:message><br />
											<form:input path="localBodyliasInEn" htmlEscape="true" id="txtlocalBodyNmeInAlias" size="40" cssClass="frmfield" onkeypress="validateAlphanumericKeys();" /> <span><form:errors
													path="localBodyliasInEn" class="errormsg" htmlEscape="true"></form:errors> </span>
											</li>
											<li>
											
													<spring:message code="Label.LOCALBODYALIASLOCAL" htmlEscape="true"></spring:message><br />
											<form:input 	path="localBodyliasInLcl" id="txtlocalBodyliasInLcl" size="40" cssClass="frmfield" htmlEscape="true" />
											<span><form:errors path="localBodyliasInLcl" class="errormsg"></form:errors></span>
											
											</li>
											
											<li>
													<spring:message
												code="Label.SELURBANTYPEBODY" htmlEscape="true"></spring:message><span
											class="errormsg">*</span><br /> <form:select
												id="ddUrbanLocalBodyTypeNew" path="urbanLgTypeNameNew" htmlEscape="true"
												size="1" class="combofield" onchange="ConvertULBnameVal(this.value);">
												<form:option value="0" htmlEscape="true">
													<spring:message code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message>
												</form:option>
												<form:options items="${urbanlocalbodyType}"
													itemLabel="localBodyTypeName" itemValue="localBodyTypeCode" htmlEscape="true"/>
											</form:select> <span id="ddUrbanLocalBodyTypeNew_error" class="errormsg"><spring:message
													code="Error.URBANLBTYPENEW" htmlEscape="true"></spring:message> </span> <form:errors
												path="urbanLgTypeNameNew" class="errormsg" htmlEscape="true"></form:errors>
											
											</li>
											
											</ul>
											
								</div>
										<div class="clear"></div>	
							
							</div>
						</div>


						<div id="divPreviousButtons" class="btnpnl" align="center">
							<input type="button" value="<spring:message code="Button.PREVIOUS" htmlEscape="true"></spring:message>" onclick="onPreviousClick();" class="btn" />
						</div>
						<div class="btnpnl" id="divSaveButtons">
								<input type="button"
												value="<spring:message code="Button.PREVIOUS" htmlEscape="true"></spring:message>"
												onclick="onPreviousClick();" class="btn" /> <label>
												<input type="submit" name="Submit" onclick="return validateSecondULBAll();" value="<spring:message code="Button.SAVE"></spring:message>" />
											</label> <label> <input type="button" class="btn" 
												name="Submit6"
												value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
												onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
						</div>

					</div>
					<!-- ---------------------------------------------------------This section was for click on proceed button event -------------------------------------------------------------->
					
				</div>
			</form:form>
			<script>
			loadConvertULBPage();
			</script>
			<c:if test="${isError == true}">
				<script>
					hasError();
				</script>
			</c:if>
			<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>

</body>

</html>