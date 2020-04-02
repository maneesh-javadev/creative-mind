<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="java.util.*"%>
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>

<title><spring:message code="Label.VIEWLOCALGOVTBODY" htmlEscape="true"></spring:message></title>
	<script src="js/viewLocalbody.js"></script>
	<script src="js/lgd_localbody.js"></script>
	<script src="js/successMessage.js"></script>
	<script src="js/helpMessage.js"></script>
	<script src="js/trim-jquery.js"></script>
	<script src="js/validation.js"></script>
	<script type="text/javascript" language="javascript" src="<%=contextpthval%>/datatable/jquery.dataTables.js"></script>
	<script type="text/javascript" language="javascript" src="<%=contextpthval%>/datatable/jquery-ui-1.8.7.custom.min.js"></script>
	<script type="text/javascript" language="javascript" src="<%=contextpthval%>/dwr/interface/lgdDwrLocalGovtBodyService.js"></script> 
	<script type='text/javascript' language="javascript" src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	<script type='text/javascript' language="javascript" src='<%=contextPath%>/dwr/interface/lgdDwrDesignationService.js'></script>
	<script src="<%=contextpthval%>/datatable/TableTools.js" charset="utf-8" type="text/javascript"></script>
	<link href="<%=contextpthval%>/datatable/jquery-ui-1.8.7.custom.css" rel="stylesheet"  type="text/css" />
	<link href="<%=contextpthval%>/datatable/demo_table_jui.css" rel="stylesheet"  type="text/css" />
	<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
	<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
	<link href="css/errorLB.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" charset="utf-8">
$(document).ready(function() {
	$('#localbodyList').dataTable({
		"bJQueryUI": true,
		"aaSorting": [],
		"bAutoWidth": false,
		"aoColumns": [
						null,
						null,
						null,
						{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false },	
			  			<c:if test="${localGovtBodyForm.parentLB ne '0'}">
			  			<c:if test="${localGovtBodyForm.districtCode eq '0'}">
						{ "bSortable": false },
			  			</c:if>
						</c:if>
						],
		"sPaginationType": "full_numbers"
	});
} );

function open_win() {
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 

function manageTraDetail(url,id,type,operationState)
{
	dwr.util.setValue('parentwiseId', id, {	escapeHtml : false	});
	dwr.util.setValue('parentCategory', type, { escapeHtml : false	});
	if(operationState=='A'){
		if(url == "modifyGovtLocalBodyMainforname.htm") {
	 		document.getElementById('form1').method = "get";
	 		document.getElementById('form1').action = url;//"getDraftFileList.htm";
	 		document.getElementById('form1').submit();
	 	} else {
			document.getElementById('form1').action = url;
			document.getElementById('form1').submit();
	 	}
		displayLoadingImage();
	}else{
		draftModeAlert();
	}
}
 
 
function setDirection(val) {
	
	document.getElementById('direction').value=val;
	document.forms['form1'].action = "viewLocalbodyPagination.htm?<csrf:token uri='viewLocalbodyPagination.htm'/>";
	document.forms['form1'].submit();
}

window.onload=loadviewLBPageTrad; 
dwr.engine.setActiveReverseAjax(true);
</script>

</head>

<body oncontextmenu="return false">

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
					<td><div class="errorfont"><spring:message htmlEscape="true" code="Error.entermandatoryfield"></spring:message></div>
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
			<h3 class="subtitle">
				<spring:message code="Label.VIEWLOCALGOVTBODY" htmlEscape="true"></spring:message>
			</h3>
			<ul class="listing">
				<li>
					<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /> </a>
				</li>
				<%--//these links are not working <li>
					<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
				</li>
				<li> 
					<a href="#" class="frmhelp"><spring:message code="Label.HELP" htmlEscape="true"></spring:message> </a>
				</li> --%>
			</ul>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			
			<form:form action="modifyLocalBodyforPRI.htm" method="POST" id="form1" onsubmit="cursorwait();" name="form1" commandName="localGovtBodyForm">

				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyLocalBodyforPRI.htm"/>" />

				<input type='hidden' name=hdnType id="hdnType" value="<c:out value='${localGovtBodyForm.hdnType}' escapeXml='true'></c:out>"/>
				<input type='hidden' name=hdnIntermediatePanchayat id="hdnIntermediatePanchayat" value="<c:out value='${hdnIntermediatePanchayat}' escapeXml='true'></c:out>"/>
				<input type='hidden' name=hdnDistrictPanchayat id="hdnDistrictPanchayat" value="<c:out value='${hdnDistrictPanchayat}' escapeXml='true'></c:out>"/>
				<input type="hidden" name="districtCode" value="<c:out value='${districtCode}' escapeXml='true'></c:out>"/>
				<input type="hidden" name="lbType" value="<c:out value='${lbType}' escapeXml='true'></c:out>"/>
				<input type="hidden" id="level" />
				<input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("stateCode")%>' />
				<form:hidden path="lbtypeLevel" id="lbtypeLevel" value="${localGovtBodyForm.lbtypeLevel}"/>
				<form:hidden path="districtCode" id="districtCode" value="${localGovtBodyForm.districtCode}"/>
				<form:hidden path="parentLB" id="parentLB" value="${localGovtBodyForm.parentLB}"/>
				<input type="hidden" name="levelcheck" id="levelcheck" value="<c:out value='${levelcheck}' escapeXml='true'></c:out>"></input>
				<input type="hidden" name="parent_level_hierarchy" id="parent_level_hierarchy"></input>
				<div id="cat">
					<div class="frmpnlbg">
						
						<div id='district' class="frmpnlbg">
							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message code="Label.SEARCHCRIT" htmlEscape="true"></spring:message>
								</div>
								<!-- <table width="100%" class="tbl_no_brdr">
									<tr>
										<td width="14%" rowspan="8">&nbsp;</td>
										<td width="86%" class="lblBold">  -->
										
									<ul class = "blocklist">
										<li class = "lblBold">
										<label for="tierSetupCode"><spring:message	code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message>
										</label><span class="errormsg">*</span><br />
										<form:select path="lgd_LBTypeName" id="tierSetupCode" name="tierSetupCode" class="combofield" style="width: 175px" onchange="hideShowDivforLocalBodyTradView(this.value,'${districtCode}','${lbType}');"
										
												onfocus="validateOnFocus('tierSetupCode');helpMessage(this,'tierSetupCode_msg');"
												onblur="vlidateOnblur('tierSetupCode','1','15','c');hideHelp();"
												onkeyup="hideMessageOnKeyPress('tierSetupCode')">
												<form:option value="">
													<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
												</form:option>
												<c:forEach var="lgtLocalBodyType" varStatus="var" items="${lgtLocalBodyType}">
													<form:option id="typeCode" value="${lgtLocalBodyType.localBodyTypeCode}:${lgtLocalBodyType.level}:${lgtLocalBodyType.category}:${lgtLocalBodyType.tierSetupCode}:${lgtLocalBodyType.parentLocalBodyTypeCode}"><c:out value="${lgtLocalBodyType.nomenclatureEnglish}" escapeXml="true"></c:out></form:option>
												</c:forEach>
										</form:select>
										
										<div id="tierSetupCode_error" class="error"><spring:message code="error.blank.viewlgTypeName" htmlEscape="true"></spring:message></div>
										<div id="tierSetupCode_msg" style="display:none"><spring:message code="error.blank.viewlgTypeName" htmlEscape="true"/></div>
										<div class="errormsg" id="tierSetupCode_error1"><form:errors path="lgd_LBTypeName" htmlEscape="true"/></div>  
										<div class="errormsg" id="tierSetupCode_error2" style="display: none"></div>
										
										<br /> <br /> <form:hidden path="lgd_lbCategory"
												id="hiddenCheckBox" class="frmfield"
												value="${localGovtBodyForm.lgd_lbCategory}"/>

						
											
										<div id="divLgdSelIntermediateP" style="visibility: hidden; display: none;">
											
											<!-- <table width="100%">
												<tr>
													<td> -->
											<ul class = "blocklist">
												<li>
													<label><spring:message htmlEscape="true" code="Label.SELECT" ></spring:message>
														</label><label id="firstlevel"></label>
																												
															<span class="errormsg">*</span><br /> 
														<form:select
															path="lgd_LBDistrictAtInter" class="combofield"
															id="ddlgdLBDistAtInter" style="width: 200px"
															onchange="getWorkOnIntermediatePanchayat(this.value)">
															<%--ddSourceLocalBody id name changed  --%>
															<form:option value="">
																<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
															</form:option>         
															<c:forEach items="${districtPanchayatList}" var="districtPanchayatList">
																<c:if test="${districtPanchayatList.operation_state == 'F'.charAt(0)}" >
																 <form:option value="${districtPanchayatList.localBodyCode}"  disabled="true"><c:out value="${districtPanchayatList.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>
																</c:if>
																<c:if test="${districtPanchayatList.operation_state == 'A'.charAt(0)}" >
																 <form:option value="${districtPanchayatList.localBodyCode}" ><c:out value="${districtPanchayatList.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>
																</c:if>
															</c:forEach>	
														</form:select> &nbsp;
														
														<div id="ddlgdLBDistAtInter_error" class="error"><spring:message code="error.SOURCESELECTLOCALBODYPARENT" htmlEscape="true"></spring:message></div>
														<div class="errormsg" id="ddlgdLBDistAtInter_error1"><form:errors path="lgd_LBDistrictAtInter" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none" ></div>
													</li>
												</ul>
										</div>
										
										
										<div id="divLgdVillageP" style="visibility: hidden; display: none;">
											<!-- <table width="100%">
												
												<tr>
													<td> -->
											<ul class = "blocklist">
												<li>		
													<label><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>&nbsp;</label><label id="secondlevel"></label><span
														class="errormsg">*</span><br /> <form:select
															path="lgd_LBDistrictAtVillage" class="combofield"
															id="ddlgdLBDistrictAtVillage"
															onchange="getWorkOnVillagePanchayatforDistrictP(this.value);"
															style="width: 200px">
															<form:option value="">
																<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
															</form:option>
															
															<%--  <form:option value=""><form:option selected="selected" value="" label="--select--"> </form:option></form:option> --%>
													<%-- 		<form:options items="${localBodyforSubDistList}"
																		itemLabel="localBodyNameEnglish"
																		itemValue="localBodyCode" />   --%>
															<c:forEach items="${localBodyforSubDistList}" var="localBodyforSubDistList">
																<c:if test="${localBodyforSubDistList.operation_state=='F'.charAt(0)}" >
																 <form:option value="${localBodyforSubDistList.localBodyCode}" label="${localBodyforSubDistList.localBodyNameEnglish}" disabled="true"></form:option>
																</c:if>
																<c:if test="${localBodyforSubDistList.operation_state=='A'.charAt(0)}" >
																 <form:option value="${localBodyforSubDistList.localBodyCode}" label="${localBodyforSubDistList.localBodyNameEnglish}"></form:option>
																</c:if>
															</c:forEach>
															<%-- <form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" /> --%>
															</form:select>
														
														<div id="ddlgdLBDistrictAtVillage_error" class="error"><spring:message code="error.SOURCESELECTVILLAGEPARENT" htmlEscape="true"></spring:message></div>
														<div class="errormsg" id="ddlgdLBDistrictAtVillage_error1"><form:errors path="lgd_LBDistrictAtVillage" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddlgdLBDistrictAtVillage_error2" style="display: none" ></div>
													
													</li>
												</ul>
										</div>
										<div id="divLgdVillagePanch" style="visibility: hidden; display: none;">
											<!-- <table width="100%">
											
												<tr>
													<td> -->
											<ul class = "blocklist">
												<li>		
													<label><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>&nbsp;</label><label id="thirdlevel"></label><span
														class="errormsg">*</span><br /> <form:select
															path="lgd_LBIntermediateAtVillage" class="combofield"
															id="ddlgdLBInterAtVillage" style="width: 200px"
															onchange="setandgetInterPanchayatbyDcodeAtVCAforPanc(this.value);">
															<form:option value="">
																<spring:message htmlEscape="true" code="Label.SELECTINTERMEDIATELOCBODY"></spring:message>
															</form:option>
															<form:option selected="selected" value="" label="--select--"></form:option>  
															 
														</form:select>
														<div id="ddlgdLBInterAtVillage_error" class="error"><spring:message code="error.SOURCESELECTVILLAGEPARENT" htmlEscape="true"></spring:message></div>
														<div class="errormsg" id="ddlgdLBInterAtVillage_error1"><form:errors path="lgd_LBIntermediateAtVillage" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddlgdLBInterAtVillage_error2" style="display: none" ></div>
													
													</li>
												</ul>
										</div>
									</li>		
										

									<li>
											<label>
												<input type="submit" name="Submit" class="btn" onclick="return validateViewLBTraditionalPage();" value=<spring:message code="Button.GETDATA"></spring:message> />
											</label>
											<label>
											<c:if test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'T')}">
											<label>
											
												<input type="button" name="ClearMe" class="btn" value="<spring:message code="Button.CLEAR" ></spring:message>"  onclick="clearMe()" /> 
											</label>
											</c:if>
											<label>
												<input type="button" class="btn" name="CloseMe" value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>" onclick="closeMe()" />
											</label>
									</li>

									<li>
										<input type="hidden" id="msgid" name="msgid" />
											<c:if test="${msgid != null}">
												<b style="color: red;"><c:out value="${msgid}" escapeXml="true"></c:out></b>
											</c:if>
									</li>

								</ul>
							</div>
						</div>
						

					</div>
				</div>
				
		

				<c:if test="${! empty LocalGovtBodyList}">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<table  cellpadding="0" cellspacing="0" border="0" class="display" id="localbodyList">
										<thead>
											<tr class="tblRowTitle tblclear">
												<td rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
												<td rowspan="2"><spring:message code="Label.LOCALBODYCODE" htmlEscape="true"></spring:message></td>
												<td rowspan="2"><spring:message code="Label.LOCALBODYNAMEINENG" htmlEscape="true"></spring:message></td>
												<td colspan="6" align="center"><spring:message code="Label.ACTION" htmlEscape="true"></spring:message></td>
											</tr>
											
											<tr class="tblRowTitle tblclear">
												<c:if test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'T')}">
													<th align="center"><spring:message htmlEscape="true" code="Label.VIEW"></spring:message></th>
													<th align="center"><spring:message htmlEscape="true" code="Label.GovernmentOrderCorrection" ></spring:message></th>
													<th align="center"><spring:message htmlEscape="true" code="Label.ModifyName" ></spring:message></th>
													<th align="center"><spring:message htmlEscape="true" code="Label.Changecoveredarea" ></spring:message>hange Covered Area</th>
													<th align="center"><spring:message htmlEscape="true" code="Label.Mapcoveredarea" ></spring:message></th>
													<c:if test="${localGovtBodyForm.parentLB ne '0'}">
														<c:if test="${localGovtBodyForm.districtCode eq '0'}">
													  		<th align="center"><spring:message htmlEscape="true" code="Label.Modifyparent" ></spring:message></th>
													  	</c:if>	
													</c:if>
												</c:if>
											</tr>
											</thead>
											
											<c:if test="${! empty LocalGovtBodyList}">
												<tbody>
												<c:forEach var="lgdLocalGovtBodyList" 	varStatus="listLocalBodyRow" items="${LocalGovtBodyList}">

													<tr class="tblRowB">
														<td align="center"><c:out value="${offsets*limits+(listLocalBodyRow.index+1)}" escapeXml="true"></c:out></td>
														<td align="left"><c:out
																value="${lgdLocalGovtBodyList.localBodyCode}" escapeXml="true"></c:out>
														</td>
														<td align="center"><c:out
																value="${lgdLocalGovtBodyList.localBodyNameEnglish}" escapeXml="true"></c:out>
														</td>
								
														<c:if test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'T')}">
															<td align="center"><a href="#"><img
																	src="images/view.png"
																	onclick="javascript:manageTraDetail('ViewLocalBodyforPRIPost.htm',${lgdLocalGovtBodyList.localBodyCode},'T','A');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>

														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'T')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:manageTraDetail('modifyGovtLocalBodyMain.htm',${lgdLocalGovtBodyList.localBodyCode},'T','${lgdLocalGovtBodyList.operation_state}');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
										
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'T')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:manageTraDetail('modifyGovtLocalBodyMainforname.htm',${lgdLocalGovtBodyList.localBodyCode},'T','${lgdLocalGovtBodyList.operation_state}');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
												
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'T')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:manageTraDetail('modifyGovtLocalBodyMainforcoveragearea.htm',${lgdLocalGovtBodyList.localBodyCode},'T','${lgdLocalGovtBodyList.operation_state}');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
												
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'T')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:manageTraDetail('correctGovtLocalBodycoveragearea.htm',${lgdLocalGovtBodyList.localBodyCode},'T','${lgdLocalGovtBodyList.operation_state}');"
																	width="18" height="19" border="0" />
															</a>
															</td>
														</c:if>
												
														 <c:if test="${localGovtBodyForm.parentLB ne '0'}">
														 	<c:if test="${localGovtBodyForm.districtCode eq '0'}">
															 	<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:manageTraDetail('modifyGovtLocalBodyMainfortype.htm',${lgdLocalGovtBodyList.localBodyCode},'T','${lgdLocalGovtBodyList.operation_state}');"
																	width="18" height="19" border="0" />
																	</a>
																</td>
															</c:if>	
														 </c:if>
														
													</tr>

												</c:forEach>
												</tbody>
											</c:if>
											<c:if test="${localbodysize==0}">
												<c:if test="${empty LocalGovtBodyList}">
													<div class="frmpnlbg">
														<div class="frmtxt">
															<table width="100%" class="tbl_no_brdr">					
															<tr>
																<td colspan="4" align="center"><spring:message htmlEscape="true" code="error.NOTRADLOCALGOVTBODYFOUND"></spring:message></td>
															</tr>					
															</table>
														</div>
													</div>
												</c:if>
										</c:if>	
										
											
											<form:input path="parentwiseId" type="hidden" name="parentwiseId" id="parentwiseId" />
											<form:input path="parentCategory" type="hidden" name="parentCategory" id="parentCategory" />
							</table>
						</div>
					</div>
				</c:if>
			</form:form>
		</div>
	</div>

<script>
function closeMe(){
    var closeUrl = "home.htm?<csrf:token uri='home.htm'/>";
	window.document.location.href=closeUrl;
}
function clearMe(){
	var clearUrl =  "viewLocalBodyforTraditional.htm?<csrf:token uri='viewLocalBodyforTraditional.htm'/>";
	window.document.location.href=clearUrl;
}

</script>

</body>

</html>