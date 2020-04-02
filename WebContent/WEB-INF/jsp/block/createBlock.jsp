<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<script type="text/javascript" src="js/cancel.js"></script>

<title><spring:message  code="Label.CREATENEWBLOCK" htmlEscape="true"></spring:message>
</title>
<script type="text/javascript" src="js/new_block.js" charset="utf-8"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSurveyService.js'></script>
<script src="js/helpMessage.js"></script>
<link href="HTML_panchayat - 2.2/css/panchayat_main.css" rel="stylesheet" type="text/css" />
<script src="js/common.js"></script>
<title><spring:message  code="Label.CREATENEWBLOCK" htmlEscape="true"></spring:message></title>
<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
/* $(document).ready(function() {
	   var s = document.getElementById("flag1").value;  
	   if(s==0)
		   $("#ddDistrict").attr("disabled", true);
	}); */
</script>
<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
</script>
</head>
<body onload="hideGISComponentOnLoad();" oncontextmenu="return false"
	onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);">


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
								<center><c:out value="${param.family_msg}"></c:out></center>
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
						<td><div id="failurMsg" class="errorfont"><c:out value="${family_error}"></c:out></div>
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
					<td><div class="errorfont">
					<spring:message htmlEscape="true" code="Error.entermandatoryfield"></spring:message></div>
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
			
					<h3 class="subtitle"><spring:message  code="Label.CREATENEWBLOCK" htmlEscape="true"></spring:message></h3>
					<ul class="listing">
					<li>
					<%-- this link is not working <a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a> --%>
					</li>
					<li>
					<%--  //this link is not working <a href="#" class="frmhelp"><spring:message 
								code="App.HELP" htmlEscape="true"></spring:message>	 --%>				
					</a>
					</li>
					</ul>
				
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="new_block.htm" method="POST" 
				commandName="newblockform" enctype="multipart/form-data" >
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="new_block.htm"/>" />
					<input type="hidden" name="flag1" id="flag1" value="${flag1}" />
				<div class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle">
						
						<c:if test="${flag1 eq 1}">
							<label><spring:message  code="Label.SELECTDISTRICT"></spring:message></label>
							</c:if>
							<c:if test="${flag1 eq 0}">
							<label><spring:message  code="Label.DISTRICTCAPS"></spring:message></label>
							</c:if>
						</div>
												
							
							
							
										
												<div  > <form:select path="districtCode"
														class="combofield" id="ddDistrict" onclick="clearalllist();" onfocus="validateOnFocus('ddDistrict');helpMessage(this,'ddDistrict_msg');"
														 onblur="vlidateOnblur('ddDistrict','1','15','c');hideHelp();"	onkeyup="hideMessageOnKeyPress('ddDistrict')" >
														<c:if test="${flag1 eq 1}">
														<form:option value="0">
															<spring:message  code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message>
														</form:option>
														<%-- <form:options items="${distList}"
															itemLabel="districtNameEnglish"
															itemValue="districtPK.districtCode" /> --%>
															
															<c:forEach items="${distList}" var="districtList">
																<c:if test="${districtList.operation_state =='A'.charAt(0)}">
																	<form:option value="${districtList.districtCode}" >${districtList.districtNameEnglish}
																	</form:option>
																</c:if>
																<c:if test="${districtList.operation_state =='F'.charAt(0)}">
																	<form:option value="${districtList.districtCode}" disabled="true">${districtList.districtNameEnglish}																				
																	</form:option>
																</c:if>
															</c:forEach>
															</c:if>
															<c:if test="${flag1 eq 0}">
														
														<form:options items="${distList}"
															itemLabel="districtNameEnglish"
															itemValue="districtPK.districtCode" />
															</c:if>
													</form:select>  <br />
												
												<div id="ddDistrict_msg" style="display:none"><spring:message code="Error.SOURCEDISTRICT" htmlEscape="true"/></div>
												
										</div>
						
					</div>
				</div>
				<div class="clear"></div>
				<div class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message  code="Label.GENERALDETAILOFNEWBLOCK" htmlEscape="true"></spring:message>
						</div>
						
							
								<ul class="blocklist"  >
								<li>
								<label for="OfficialAddress"><spring:message 
										code="Label.BLOCKNAMEEN" htmlEscape="true"></spring:message><span id ="required" class="errormsg">*</span></label>
										<br /> 
										 <form:input
											path="blockNameEnglish" id="OfficialAddress" class="frmfield"
											htmlEscape="true"
										    onblur="blocknameVal(this.value);chekcalphanumeric(this.value,1);officialAddress()"
											onkeyup="hideMessageOnKeyPress('ddDistrict')"  maxlength="50"/>
											<div id="UniqueBlockError" style="color: red;"></div>
											
											
											<form:errors path="blockNameEnglish" cssClass="errormsg"/>
									<!-- <div class="errormsg"></div> -->
									<div id="OfficialAddress_msg" style="display:none"><spring:message code="Error.SOURCEBLOCK" htmlEscape="true"/></div>
								</li>
							
							<li>
								<br /><label for="blockNameLocal"><spring:message  code="Label.BLOCKNAMELOCL" htmlEscape="true"></spring:message></label>
								<br />
								
									<form:input path="blockNameLocal" htmlEscape="true"
											class="frmfield" onblur="validatelocalCharforBLKValues(this.value,1);"
											onkeyup="hideMessageOnKeyPress('blockNameLocal')" maxlength="50"/> 
											
											<form:errors path="blockNameLocal" cssClass="errormsg"/>
									<div class="errormsg"></div>
										<div id="blockNameLocal_msg" style="display:none"> <spring:message code="Error.SOURCEBLOCK" htmlEscape="true"/> </div>
								
							</li>
							<li>
								<label for="aliasEnglish"><spring:message  code="Label.ALIASENGLISH" htmlEscape="true"></spring:message></label>
								<br />
									 <form:input  htmlEscape="true"  path="aliasEnglish" onblur="chekcalphanumeric(this.value,2);"
											class="frmfield" maxlength="50"/> 
											<form:errors path="aliasEnglish" cssClass="errormsg"/>
									<div class="errormsg"></div>
								
							</li>
							<li>
								<label for="aliasLocal"><spring:message  code="Label.ALIASLOCAL" htmlEscape="true"></spring:message></label>
								<br />
									 <form:input  htmlEscape="true"  path="aliasLocal" onblur="validatelocalCharforBLKValues(this.value,2);" class="frmfield" maxlength="50"/>
								
								<form:errors path="aliasLocal" cssClass="errormsg"/>
									<div class="errormsg"></div>
								
							</li>
							<li>
								<label for="ssCode"><spring:message  code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message></label><br />
									 <form:input  htmlEscape="true"  path="ssCode" class="frmfield" maxlength="5" onblur="chekcalphanumeric(this.value,3);"  />
								
									<div class="errormsg"></div>
								
							</li>

						</ul>
						
					</div>
				</div>
				<div class="clear"></div>
				
				<%@ include file="../common/headquarter.jspf" %>
				<div class="clear"></div>			
				<%@ include file="../common/gis_nodes.jspf" %>
				<div class="clear"></div>
				<div class="frmpnlbg">
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message  code="Label.CONTRIBUTINGLANDREGION" htmlEscape="true"></spring:message>
						                 </div>
						
							<ul class="blocklist">
							<li>
							<div>			
							<input type="checkbox" value="true" onclick="getunmapvillageList('ddDistrict');" name="lgd_LBExistCheck" id="Villagelistchek"/>
							
							<label><spring:message  code="Label.UnmappedRegion" htmlEscape="true"></spring:message> 
														</label>								
							</div>
							
							<br />	
							
											<div class="ms_container">
											
											<div class="ms_selectable">
											
											<strong><spring:message  code="Label.SELECTVILLAGE" htmlEscape="true"></spring:message>
											</strong><br />	
											
											<form:select name="select9"
													size="6" id="vilunmapbblocks" path="blockList" multiple="multiple"
													class="frmtxtarea"></form:select>
											</div>		
											
											<div class="ms_buttons btnmargin" >
											 <input
																type="button" id="btnaddSubDistrictFull" name="Submit1"
																class="bttn" value="<spring:message
																code="Label.SELECTVILLAGE"/>"
																onclick="addSelectedItems('VilListNew','vilunmapbblocks',true)" />
														
														
														
														
														 <input
																type="button" class="bttn" id="btnremoveOneSubDistrict"
																name="Submit2" value=" &lt; "
																onclick="removesingleItem('VilListNew','vilunmapbblocks',true)" />
														
													  
													  
													 
														
														 <input
																type="button" class="bttn" id="btnremoveAllSubDistricts"
																name="Submit3" value="&lt;&lt;"
																onclick="removeAlls('VilListNew','vilunmapbblocks',true)" /> 
														
													
													</div>
											
											<div class="ms_selection">
											
											
											<strong><spring:message 
														code="Label.VILLAGESTOINVALIDATE" htmlEscape="true"></spring:message> </strong><br />	
											
										<form:select name="select1"
													id="VilListNew" size="6" multiple="multiple"
													path="VillagesList" class="frmtxtarea">
													<%-- <form:options items="${blockList}" itemLabel="blockNameEnglish"
																					itemValue="aliasEnglish"/> --%>
													</form:select>
												</div>
												<div class="clear"></div>
												</div>
												</li>
												
										
											
											
						
							<!-- table ends -->
							
							
							
							 <li style="display: none">
							<div>
							<input type="checkbox" value="true" onclick="getunmapULBList('ddDistrict');" name="Ulblistchek" id="Ulblistchek"/> 
							<label>Select from ULB's</label>
								</div>						
														<br />	
									
										
						
							
							                 
											<div class="ms_container">
											
											<div class="ms_selectable">								
											<strong> <spring:message  code="Label.SelectULB" htmlEscape="true"></spring:message> 
											</strong><br />	
											<form:select name="select9"
													size="6" id="ulbunmapbblocks" path="blockList" multiple="multiple"
													class="frmtxtarea"></form:select>
											</div>
											
											
											<div class="ms_buttons btnmargin" >
												
														 <input
																type="button" id="btnaddSubDistrictFull" name="Submit4"
																class="bttn"  value="<spring:message
																code="Button.SELECTFULLBLOCK"/>""Select ULB "
																onclick="addSelectedItems('ULBListNew','ulbunmapbblocks',true)" />
														
														
														
														<input
																type="button" class="bttn" id="btnremoveOneSubDistrict"
																name="Submit5" value=" &lt; "
																onclick="removesingleItem('ULBListNew','ulbunmapbblocks',true)" />
														
														
														
														
														
														 <input
																type="button" class="bttn" id="btnremoveAllSubDistricts"
																name="Submit6" value="&lt;&lt;"
																onclick="removeAlls('ULBListNew','ulbunmapbblocks',true)" /> 
														</div>
											
											
											<div class="ms_selection">
											
											
											<strong><spring:message 
														code="Label.SelectedULB" htmlEscape="true"></spring:message>  </strong><br />	
											
											<form:select name="select2"
													id="ULBListNew" size="6" multiple="multiple"
													path="UlbLists" class="frmtxtarea">
													<form:options items="${blockList}" itemLabel="blockNameEnglish"
																					itemValue="aliasEnglish"/>
													</form:select>
												<br /><br />	
											
											
										</div>
										<div class="clear"></div>
									</div>
									</li>
									
								
									
									
									<!-- table ends -->
							
									
									
									
							         <li>
											<div>		
													<input type="checkbox" value="true" onclick="getBlockList();" name="lgd_LBExistCheck" id="Blocklistchek"/>
													<input type="hidden" value="on" name="Blocklistchek" /> 
													<label>Select from Existing Blocks</label>
												</div>			
													
										<br />		
									
								
									
										
										<div class="ms_container">
											
										<div class="ms_selectable">	
											
											<strong><spring:message  code="Label.SELBLOCK" htmlEscape="true"></spring:message>
											</strong><br />	
										  <form:select name="select9"
													size="6" id="ddBlock" path="blockList" multiple="multiple"
													class="frmtxtarea"></form:select>	
											</div>
											
											
											
											           <div class="ms_buttons btnmargin" >
											
											                   <input
																type="button" id="btnaddSubDistrictFull" name="Submit7"
																class="bttn" value="<spring:message
																code="Button.SELECTFULLBLOCK"/>"
																onclick="addItems('blockListNew','ddBlock','FULL',true)" />
														
														
														
														
														
														
														        <input
																type="button" class="bttn" id="btnremoveOneSubDistrict"
																name="Submit8" value=" &lt; "
																onclick="removesingleItem('blockListNew','ddBlock',true);clearblocksulbbvil();" />
														
														
														
													
														
														
														       <input
																type="button" class="bttn" id="btnremoveAllSubDistricts"
																name="Submit9" value="&lt;&lt;"
																onclick="removeAlls('blockListNew','ddBlock',true)" /> 
														
														
														       <input
																type="button" class="bttn"  id="btnaddSubDistrictPart"
																name="Submit10" value="<spring:message
																code="Button.SELECTPARTBLOCK"/>"
																onclick="addItems('blockListNew','ddBlock','PART',true);" />
																
															
														
														
											</div>
											
											
											<div class="ms_selection">
											
											<strong><spring:message 
														code="Label.BLOCKLISTSELECTED" htmlEscape="true"></spring:message> </strong>
											<br />	
											
											
											<form:select name="select3"
													id="blockListNew" size="6" multiple="multiple"
													path="contributedBlocks" class="frmtxtarea">
													<%-- <form:options items="${blockList}" itemLabel="blockNameEnglish"
																					itemValue="aliasEnglish"/> --%>
													</form:select>
													<input type="button" id="partSubdistrict" name="Submit11" onclick="getVillageLists()" class="bttn" value= "<spring:message  code="Button.ULBVillage"></spring:message>"  />
													
													<div class="errormsg"></div>
											</div>
											
											<div>
												
											</div>
											<div class="clear"></div>
											
											</div>	
												 
												
												
										
								</li>
								
													
							
											 <li style="display: none">
											<div id='cvillage'  visibility:hidden">
											<div class="ms_container">											
										     <div class="ms_selectable">	
											<strong> <spring:message code="Label.SelectBlockUlb" htmlEscape="true"></spring:message> 
											</strong><br />	
											<form:select
															name="select9" size="6" id="ulbListsofblock" path=""
															multiple="multiple" class="frmtxtarea"></form:select>
											
											</div>
											
											
											
										                         <div class="ms_buttons btnmargin" >
																    <input
																		type="button" id="btnaddVillageFull" name="Submit12"
																		class="bttn"  value="<spring:message  code="Label.SelectULB"></spring:message>" 
																		onclick="addItems('blockulblist','ulbListsofblock','FULL',true)" />
															
																
																
																       <input
																		type="button" class="bttn" id="btnremoveOneVillage"
																		name="Submit13" value=" &lt; "
																		onclick="removesingleItem('blockulblist','ulbListsofblock',true)" />
																
																
																
																
																       <input
																		type="button" class="bttn" id="btnremoveAllVillages"
																		name="Submit14" value="&lt;&lt;"
																		onclick="removeAlls('blockulblist','ulbListsofblock',true)" />
																
																</div>
																
														
														
														
													<div class="ms_selection">
													<strong><spring:message code="Label.SelectedBlockUlb" htmlEscape="true"></spring:message></strong><br />	
													
													<form:select
															name="select4" id="blockulblist" size="6"
															multiple="multiple" path="contributedblockUlb"
															class="frmtxtarea">
														</form:select>	
														
														</div>
														
														<div class="clear"></div>
														</div>
														
														</div>
														</li> 
														
											<li>
											<div class="ms_container">											
										     <div class="ms_selectable">
											<strong><spring:message code="Label.SELECTVILLAGE" htmlEscape="true"></spring:message>
											</strong><br />	
											<form:select
															name="select9" size="6" id="villageList" path=""
															multiple="multiple" class="frmtxtarea"></form:select>
													</div>
													
													
										                    <div class="ms_buttons btnmargin" >
																
																 <input
																		type="button" id="btnaddVillageFull" name="Submit15"
																		class="bttn" value="<spring:message  code="Label.SELECTVILLAGE"></spring:message>"
																		onclick="checkthenAdd()" />
																
																 <input
																		type="button" class="bttn" id="btnremoveOneVillage"
																		name="Submit16" value=" &lt; "
																		onclick="removesingleItem('villageListNew','villageList',true)" />
																
																 <input
																		type="button" class="bttn" id="btnremoveAllVillages"
																		name="Submit17" value="&lt;&lt;"
																		onclick="removeAlls('villageListNew','villageList',true)" />
															
																</div>
																
														<!-- </div> -->
														
													<div class="ms_selection">
													<strong><spring:message code="Label.VOBSEL" htmlEscape="true"></spring:message> </strong><br />	
										
													<form:select
															name="select4" id="villageListNew" size="6"
															multiple="multiple" path="contributedVillages"
															class="frmtxtarea">
														</form:select>
														
													
									
									<div class="errormsg"></div>	
									
									</div>	
									
									<div class="clear"></div>
									
									</div>
									</li>
									</ul>
					
				</div>
										
					</div>				
		
				<div>

						<input type="hidden" name="addAnotherBlock" id="addAnotherBlock"/>
						<form:hidden path="operation" value="C"/>
						<form:hidden path="govtOrderConfig" value="${govtOrderConfig}"/>
							<div class="btnpnl">
									 <input type="button" class="button" id="Save"
										value="<spring:message  code="Button.SAVE"></spring:message>"
										onclick="formSubmitSave();" size="40" maxlength="50" name="Submit" />  <!-- added by kirandeep on 18/06/2014 -->
									 <input type="button" name="Submit6"
										class="button"
										value="<spring:message  code="Button.CLOSE"></spring:message>"
										onclick="javascript:go('cancelBlock.htm');" /> 
									
								<%-- <label><input type="button" id="" class="button"
								value="<spring:message  code="Button.ADDANOTHERBBLOCK"></spring:message>"
								onclick="formSubmitAddPreview();" /></label> --%>
								<input type="button" class="button" id="Proceed" style="visibility: hidden"
								value="<spring:message  code="Button.PROCEED"></spring:message>"
								onclick="formSubmit();"/>
								
								</div>
							

				</div>
			</form:form>
					<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>	
	
	</div>
	</div>

		
</body>
</html>