<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<head>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/validation.js"></script>
<script type="text/javascript" src="js/createDistrict.js"></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrSubVillageService.js'></script>
<!-- <script type="text/javascript" src="js/new_sub_district.js" charset="utf-8"></script> -->
<script>
	//window.onload = onloaddistrict;
</script>

</head>
<body>
	<%@include file="addDistrict.jsp"%>
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message htmlEscape="true" code="Label.CREATEDISTRICT"></spring:message></h3>
			<ul class="listing">
				<%--//these links are not working <li>
					<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message> </a>
				</li>
				<li>
					<a href="viewManualHelps.do?formName=${formId}&<csrf:token uri='viewManualHelps.do'/>" class="frmhelp" onclick="centeredPopup(this.href,'myWindow','yes');return false">Help</a>
				</li> --%>
			</ul>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="addNewDistrict.htm" method="POST" commandName="district" id="form1" name="form1" enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="addNewDistrict.htm"/>" />
				<div id="DistrictdetailId" class="frmpnlbg" style="${display}">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<label><spring:message code="Label.DISTRICTDETAILS" htmlEscape="true"></spring:message> </label>
						</div>
						<c:set value="1" var="count"></c:set>
						<c:set value="WHITE" var="oddeven"></c:set>
						<div id="accordion" style="width:100%">
							<c:forEach var="histroyDistrict" items="${histroyDistrict}">
								<h3>
									<label><a href="#">${histroyDistrict.districtName}</a> </label>
								</h3>
								<div>
									<table width="100%" class="tblRowTitle tblclear">
										<tr>
											<td align="center" width="3%"><label><spring:message htmlEscape="true" code="Label.SNO"></spring:message> </label>
											</td>
											<td align="center" width="7%"><label><spring:message htmlEscape="true" code="Label.DISTRICTNAME"></spring:message> </label></td>
											<td align="center" width="10%"><label><spring:message htmlEscape="true" code="Label.CONTRIBUTINGDISTRICT"></spring:message> </label>
											</td>
											<td align="center" width="10%"><label><spring:message htmlEscape="true" code="Label.CONTRIBUTINGSUBDISTRICT"></spring:message> </label>
											</td>
											<td align="center" width="10%"><label><spring:message htmlEscape="true" code="Label.CONTRIBUTINGNEWSUBDISTRICT"></spring:message> </label>
											</td>
											<td align="center" width="10%"><label><spring:message htmlEscape="true" code="Label.CONTRIBUTINGVILLAGES"></spring:message> </label>
											</td>
											<%-- 	<td align="center" width="10%"><label><spring:message htmlEscape="true"  code="Label.OPERATION"></spring:message></label></td> --%>
											<td align="center" width="10%"><label><spring:message htmlEscape="true" code="Label.DISTRICTSTATUS"></spring:message> </label>
											</td>
										</tr>
									</table>
									<table width="100%" class="tbl_no_brdr">
										<tr>
											<td align="center" width="1%">${count}</td>
											<td align="left" width="10%"><label>${histroyDistrict.districtName }</label></td>
											<td align="left" width="10%">
												<ul style="text-align: center;">
													<c:forEach var="contributeDistrictList" items="${histroyDistrict.contributeDistrictList}">
														<c:if test="${empty contributeDistrictList}">
													    ----
													</c:if>
														<c:if test="${not empty contributeDistrictList}">
															<li>${contributeDistrictList}</li>
														</c:if>
													</c:forEach>
												</ul>
											</td>
											<td align="left" width="10%">
												<ul>
													<c:forEach var="contributeSubDistrictList" items="${histroyDistrict.contributeSubDistrictList}">
														<c:if test="${empty contributeSubDistrictList}">
													    ----
													</c:if>
														<c:if test="${not empty contributeSubDistrictList}">
															<li>${contributeSubDistrictList}</li>
														</c:if>
													</c:forEach>
												</ul>
											</td>
											<td align="left" width="10%">
												<ul>
													<c:forEach var="contributeNewCreatedSubDistrictList" items="${histroyDistrict.contributeNewCreatedSubDistrictList}">
														<c:if test="${contributeNewCreatedSubDistrictList =='null'}">
													    ----
													</c:if>
														<c:if test="${contributeNewCreatedSubDistrictList !='null'}">
															<li>${contributeNewCreatedSubDistrictList}</li>
														</c:if>
													</c:forEach>
												</ul>
											</td>
											<td align="left" width="10%">
												<ul style="height: 150px">
													<c:set var="subdistrictNames" value=""/>
													<c:forEach var="ss" items="${fn:split(histroyDistrict.villageMergeList, ',')}">
														<c:set var="string5" value="${fn:substringBefore(ss,'#')}"/>
														<c:set var="subdistrictNames" value="${subdistrictNames}, ${string5}"/>
													</c:forEach>
													<c:set var="mergeSubdistrict" value="${fn:substringAfter(subdistrictNames,',')}"></c:set>
													<c:forEach var="villMergeTotal" items="${fn:split(histroyDistrict.villageMergeList,',')}">
														<c:set var="villageMergeList" value="${fn:substringAfter(villMergeTotal,'#')}"></c:set>
														
														<c:forEach var="villageMerge" items="${fn:split(villageMergeList,':')}">
															<c:set var="checkmerge" value=""></c:set>
															<c:if test="${not empty villageMerge}">
																<li>${villageMerge}</li>
																<c:set var="checkmerge" value="true"></c:set>
															</c:if>
														</c:forEach>
													</c:forEach>
													<c:if test="${not empty checkmerge}">
														<span class="blink"><font style="font-weight: bold;" color="#105CA8">Merged into ${mergeSubdistrict}</font> </span>
													</c:if>
													
													<c:set var="addSubdistrictNames" value=""/>
													<c:forEach var="sd" items="${fn:split(histroyDistrict.villageNewList, ',')}">
														<c:set var="stringadd" value="${fn:substringBefore(sd,'#')}"/>
														<c:set var="addSubdistrictNames" value="${addSubdistrictNames}, ${stringadd}"/>
													</c:forEach>
													<c:set var="addSubdistrict" value="${fn:substringAfter(addSubdistrictNames,',')}"></c:set>
													
													<c:forEach var="villAddTotal" items="${fn:split(histroyDistrict.villageNewList,',')}">
														<c:set var="villageAddList" value="${fn:substringAfter(villAddTotal,'#')}"></c:set>
														<c:forEach var="villageNew" items="${fn:split(villageAddList,':')}">
															<c:set var="check" value=""></c:set>
															<c:if test="${not empty villageNew}">
																<li>${villageNew}</li>
																<c:set var="check" value="true"></c:set>
															</c:if>
														</c:forEach>
													</c:forEach>
													
													<c:if test="${not empty check}">
														<span class="blink"><font style="font-weight: bold;" color="#089553">Added into ${addSubdistrict}</font> </span>
													</c:if>
												</ul>
											</td>
											<%-- 	<td align="center" width="10%"><button id="create-user${count}" onclick="return openwin('${histroyDistrict.subdistrictNameEnglish }','${subDistrictInfoList.subdistrictNameLocal }','${subDistrictInfoList.aliasEnglish }','${subDistrictInfoList.aliasLocal }','${subDistrictInfoList.census2011Code }','${subDistrictInfoList.sscode }','${subDistrictInfoList.villageNumber}');">Modify</button></td> --%>
											<td align="center" width="10%"><font style="font-weight: bold;" color="#FF3232"><label><span class="blink"><spring:message code="Label.PENDING" htmlEscape="true"> </spring:message>
													</span> </label> </font></td>
										</tr>
									</table>
								</div>
								<c:set value="${count+1}" var="count"></c:set>
							</c:forEach>
						</div>
					</div>
				</div>
				
				
				<div class="frmpnlbg">
					<div class="frmtxt" style="${style}">
						<div id="mainBody" class="block">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true" code="Label.GENERALDETAILNEWDISTRICT"></spring:message>
							</div>
							<ul class="blocklist">
								<li>
									<form:hidden path="operation" value="C" /> <form:hidden path="govtOrderConfig" value="${govtOrderConfig}" /> <label for="districtNameInEn"><spring:message htmlEscape="true" code="Label.DISTRICTNAMEENG"></spring:message> </label><span
									id="required" class="errormsg"></span><span class="errormsg">*</span><br /> <form:input path="districtNameInEn" id="districtNameInEn" maxLength="50" size="50" cssClass="frmfield"
									onfocus="show_msg('districtNameInEn')" htmlEscape="true" onblur="findDuplicate(this.value);"/> <%-- "findDuplicate(this.value);"  --%> <span class="error" id="districtNameInEn_error"></span><span><form:errors
									htmlEscape="true" path="districtNameInEn" class="errormsg"></form:errors> </span>
								</li>
								<li>
									<label for="districtNameInLcl"><spring:message htmlEscape="true" code="Label.DISTRICTNAMELOCAL"></spring:message> </label><br /> <form:input path="districtNameInLcl" id="districtNameInLcl" maxLength="50" size="50" cssClass="frmfield"
									onfocus="show_msg('districtNameInLcl')" onkeyup="return chkValid(this);" htmlEscape="true" onblur="NameInLcl()" />
								</li>
								<li>
									<label for="districtAliasInEn"><spring:message htmlEscape="true" code="Label.DISTRICTALIASENGLISH"></spring:message> </label><br /> <form:input path="districtAliasInEn" id="districtAliasInEn" maxLength="50" size="50"
									onkeyup="return chkValid(this);" cssClass="frmfield" onfocus="show_msg('districtAliasInEn')" htmlEscape="true" onblur="AliasInEn()" /> 
								</li>
								<li>
									<label for="districtAliasInLcl"><spring:message htmlEscape="true" code="Label.DISTRICTALIASLOCAL"></spring:message> </label><br /> <form:input path="districtAliasInLcl" id="districtAliasInLcl" maxLength="50" size="50"
									onkeyup="return chkValid(this);" cssClass="frmfield" onfocus="show_msg('districtAliasInLcl')" htmlEscape="true" onblur="AliasInLcl()" /> 
								</li>
								<%-- <li>
									<label for="census2011Code"><spring:message htmlEscape="true" code="Label.CENSUSCODE2011"></spring:message> </label><br /> <form:input path="census2011Code" id="census2011Code" maxLength="6" onkeyup="return chkValid(this);"
									cssClass="frmfield" htmlEscape="true" onfocus="show_msg('census2011Code')" onblur="Cns2011Code()" />
								</li> --%>
								<li>
									<label for="sscode"><spring:message htmlEscape="true" code="Label.STATESPECIFICCODE" text="SS Code"></spring:message> </label><br /> <form:input path="sscode" id="sscode" name="sscode" maxLength="5" onkeyup="return chkValid(this);"
									cssClass="frmfield" onfocus="show_msg('sscode')" htmlEscape="true" onblur="sscode()" />
								</li>
							</ul>
							<div class="clear"></div>
							<%@ include file="../common/gis_nodes.jspf"%>
							<%@ include file="../common/headquarter.jspf"%>
							<div class="clear"></div>
							<div class="frmpnlbg">
									<div id='district'>
										<div class="frmtxt">
											<div class="frmhdtitle">
												<spring:message htmlEscape="true" code="Label.CONTRIBUTINGLANDREGION"></spring:message>
											</div>
											<ul class="blocklist">
												<li>
													<div class="ms_container">
														<div class="ms_selectable">
															<label><spring:message htmlEscape="true" code="Label.DISTRICTLIST"></spring:message></label><br/>
															<form:select path="districtsourcecode" class="frmtxtarea" id="ddSourceDistrict" multiple="true" disabled="${disabled}">
																<form:options items="${distList}" itemLabel="districtNameEnglish" itemValue="districtCode" />
															</form:select>
														</div>
														<div class="ms_buttons">
															
																<input id="src2Target1" class="bttn" type="button" onclick="addItem('ddDestDistrict','ddSourceDistrict','FULL',true);" value=" Whole &gt;&gt;" />
															
															
															
																<input id="target2Src1" class="bttn" type="button" onclick="removeOnedistrictOption('ddDestDistrict', 'ddSourceDistrict', true);" value="&lt;" />
															
															
															
																<input id="target2Src2" class="bttn" type="button" onclick="removeAllList('ddDestDistrict', 'ddSourceDistrict', true);" value="&lt;&lt;" />
															
															
															
																<input id="src2Target2" class="bttn" type="button" onclick="addItem('ddDestDistrict','ddSourceDistrict', 'PART',true);" value="Part &gt;&gt;" />
															
														</div>
														<div class="ms_selection">
															<label><spring:message htmlEscape="true" code="Label.CONTRIBUTEDISTRICTLIST"></spring:message></label><br/>
															<form:select name="select4" id="ddDestDistrict" size="1" multiple="multiple" path="districtNameEnglish" class="frmtxtarea" disabled="${disabled}">
																<form:options items="${listDistrict}" itemLabel="districtNameEnglish" itemValue="aliasEnglish" />
															</form:select>
															<input name="button2" class="bttn" type="button" onclick="selectsubdieverythingfdfd(${disabled})" value="Get Sub-district List"  />
														</div>
													</div>
												</li>
											</ul>
											<ul class="blocklist">
												<li>
													<div class="ms_container">
														<div class="ms_selectable">
															<label><spring:message htmlEscape="true" code="Label.SUBDISTRICTLIST"></spring:message></label><br/>
															<form:select name="ddSubdistrictforsubdistrict" size="1" id="ddSubdistrictforsubdistrict" path="subDistrictList" multiple="multiple" class="frmtxtarea" >
																<form:options items="${preSubDistrictList}" itemLabel="subdistrictNameEnglish" itemValue="subdistrictCode" />
															</form:select>
														</div>
														<div class="ms_buttons">
															 
																<input type="button" class="bttn" id="btnaddSubDistrictFull" name="Submit4" value=" Whole &gt;&gt;" />
															 
															
															 
																<input id="buttonRemove1" class="bttn" type="button" onclick="removeOnedistrictOption('subDistrictListNew', 'ddSubdistrictforsubdistrict', true)" value="&lt;" />
															 
															
															 
																<input id="buttonRemove2" class="bttn" type="button" onclick="removeAllList('subDistrictListNew', 'ddSubdistrictforsubdistrict', true)" value="&lt;&lt;" />
															 
															
															 
																<input type="button" class="bttn" id="btnaddSubDistrictPart" name="Submit4" value=" Part &gt;&gt;"  />
															 
														</div>
														<div class="ms_selection">
															<label> <spring:message htmlEscape="true" code="Label.CONTRIBUTESUBDISTRICTLIST"></spring:message></label>
															<form:select name="select4" id="subDistrictListNew" size="1" multiple="multiple" path="contributedSubDistricts" class="frmtxtarea" htmlEscape="true" disabled="${disabled}">
																<form:options items="${listpostSubDistrictForms}" itemLabel="subdistrictNameEnglish" itemValue="aliasEnglish" />
															</form:select>
															
																<input type="button" class="bttn" value="Get Village List" onclick="selectVillageList();" />
															
														</div>
													</div>
												</li>
											</ul>
											<ul class="blocklist">
												<li>
													<div class="ms_container">
														<div class="ms_selectable">
															<label><spring:message htmlEscape="true" code="Label.VILLAGELIST"></spring:message></label><br/>
															<form:select name="villageList" size="1" id="villageList" path="" multiple="multiple" class="frmtxtarea">
															</form:select>
														</div>
														<div class="ms_buttons">
															
																<input type="button" class="bttn" id="btnaddVillageFull" name="Submit4" value=" Whole &gt;&gt;" />
															
																<input name="button22" class="bttn" type="button" onclick="removeOnedistrictOption('villageListNew', 'villageList', true)" value="&lt;" />
															
																<input name="button22" class="bttn" type="button" onclick="removeAllList('villageListNew', 'villageList', true)" value="&lt;&lt;" />
															
														</div>
														<div class="ms_selection">
															<label>
																<spring:message htmlEscape="true" code="Label.CONTRIBUTVILLAGELIST"></spring:message>&nbsp;&nbsp;
															</label>
															<form:select name="select4" id="villageListNew" size="1" multiple="multiple" path="contributedVillages" class="frmtxtarea">
															</form:select>
															<div>
															<br/>
																<label>
															    	<spring:message htmlEscape="true" code="Reorganize Villages"></spring:message><br/>
															  	</label>
																<input type="button" id="merge1" value="Merge Into Subdistricts"/>
																<!-- <button id="select1">Select an action</button> -->
															</div>
															<!-- buttons for creating new sub district -->
															<div>
																<input type="button" id="create" value="Create New Subdistrict"/>
																<!-- <button id="select">Select an action</button> -->
															</div>
														</div>
														<div>
															<form:select name="select4" id="subDistrictListNewFull" size="1" multiple="multiple" path="contributedSubDistrictsFull" class="frmtxtarea" htmlEscape="true" disabled="true"
																style="display: none;">
																<form:options itemLabel="subdistrictNameEnglish" itemValue="subdistrictCode" />
															</form:select>
														</div>
													</div>
												</li>
												<li>
													<input type="hidden" name="reorganized" id="reorganized" />
													<input type="hidden" name="modifyVillage" id="modifyVillage" />
													<input type="hidden" name="modifySubDistrict" id="modifySubDistrict" />
													<input type="hidden" name="addAnotherSD" id="addAnotherSD" />
													<input type="hidden" name="statusDist" id="statusDist" />
												</li>
											</ul>
											<div class="clear"></div>
										</div>
									</div>
								</div>
							</div>
						
							<form:hidden path="totalVillage" id="totalVillage" value="${totalVillage}" />
							<form:hidden path="preDistrict" id="preDistrict" value="" />
							<form:hidden path="mergeSelectedVillageListId" id="mergeSelectedVillageListId" value="" />
							<form:hidden path="mergeSubDistId" id="mergeSubDistId" value="" />
							<form:hidden path="mergeVillageListId" id="mergeVillageListId" value="" title="mergeVillageListId"/>
							<form:hidden path="storedCombiValues" id="storedCombiValues" value="" title="storedCombiValues"/>
							<form:hidden path="storedNewCombiValues" id="storedNewCombiValues" value="" title="storedNewCombiValues"/>
							<form:hidden path="districtNameEnglishTemp" id="districtNameEnglishTemp" value="" />
							<form:hidden path="contributedSubDistrictsTemp" id="contributedSubDistrictsTemp" value="" />
							<form:hidden path="preSubDistrictsTemp" id="preSubDistrictsTemp" value="" />
							<form:hidden path="preVillageTemp" id="preVillageTemp" value="" />
							<form:hidden path="buttonClicked" id="buttonClicked" value="" />
							<form:hidden path="historyDistrictList" id="historyDistrictList" value="" />
							<form:hidden path="histrorySubDistrictList" id="histrorySubDistrictList" value="" />
							<form:hidden path="histroryNewSubDistrict" id="histroryNewSubDistrict" value="null" />
							<form:hidden path="histroryNewSubDistrictList" id="histroryNewSubDistrictList" value="null" />
							<form:hidden path="histroryMergeSubDistrictList" id="histroryMergeSubDistrictList" value="null" />
							<form:hidden path="districtNameList" id="districtNameList" value="${districtNameList}" />
							<!--  Added by sushil on 06-5-2013 -->
							<select size="1" multiple="multiple" class="frmtxtarea" name="remainDialogVillageList" style="display: none;" id="remainDialogVillageList"></select>
							<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
					</div>
				</div>
				<div class="btnpnl">
								<!-- commented temporary for stqc -->
								<input type="button" id="Submit" style="${visibilityanother}" value="<spring:message code="Button.ADDANOTHER" htmlEscape="true"></spring:message>" onclick="addanother('Add Another');" />
								<!--end commented temporary for stqc -->
								
								<input type="button" class="btn" id="Submit" style="${visibility}" name="Submit" value="<spring:message code="Button.NEXT" htmlEscape="true"></spring:message>" onclick="nextButton('Next');" /> 
								<input type="button" class="btn" id="Submit" style="${visibilityNext}" value="<spring:message htmlEscape="true"  code="Button.NEXT"></spring:message>" onclick="nextSessionButton('NextSession')" /> 
								<input type="button" name="Submit2" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>"/> 
								<input type="button" class="btn" name="Submit6" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm'" /> 
								<input type="button" class="btn" id="Proceed" style="visibility: hidden" value="<spring:message htmlEscape="true"  code="Button.PROCEED"></spring:message>" onclick="onloadSelect();" />
						</div>
				
				  <div id="dialogone" title="Merge Into Subdistricts Dialog" style="display: none;">
					<ul id="maincontainer" class="blocklist">
						<li>
							<label><spring:message text="Step 1 : Select villages that need to merge"></spring:message></label>
							<div class="ms_container">
								<div class="ms_selectable" id="list1">									
									<select size="1" multiple="multiple" class="frmtxtarea" name="dialogVillageList" id="dialogVillageList">
									</select>
								</div>
								<div class="ms_buttons">								
										<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all bttn" id="btnaddVillagePart" name="Submit4" value=" &gt;&gt;" onclick="addItemNew('selectedDialogVillageList','dialogVillageList','',true)" /> 
									
										<input id="target2Src" type="button" class="ui-button ui-widget ui-state-default ui-corner-all bttn" value="&lt;&lt;" />									
								</div>
								<div class="ms_selectable" id="list2">
									<select size="1" multiple="multiple" class="frmtxtarea" name="selectedDialogVillageList" id="selectedDialogVillageList">
									</select>
								</div>
								<div class="clear"></div>
							</div>
						</li>
						<li>
							<label><spring:message text="Step 2 : Select subdistrict in which selected villages need to merge"></spring:message></label>						
							<div class="frmtxtarea">
							    <table id="subDistRadioId"></table>
							</div>
						</li>
						<li>
							<input type="button" id="mergeButtonId" class="ui-button ui-widget ui-state-default ui-corner-all" name="merge" onclick="closeDialog('Merge');"
							value="<spring:message htmlEscape="true"  code="Label.MERGE"></spring:message>"></input><input id="closeButtonId" class="ui-button ui-widget ui-state-default ui-corner-all" type="button" name="close" onclick="closeDialog('Clear');"
							value="<spring:message htmlEscape="true" code="Button.CLEAR"></spring:message>"></input><input id="doneButtonId" class="ui-button ui-widget ui-state-default ui-corner-all" type="button" name="done" onclick="closeDialog('Done');"
							value="<spring:message htmlEscape="true" code="Label.DONE" text="Done"></spring:message>"></input>
							<input type="button" id="mergeanotherButtonId" class="ui-button ui-widget ui-state-default ui-corner-all" name="mergeanother" onclick="closeDialog(this.value);" value="<spring:message htmlEscape="true" code="Label.MERGEANOTHER" text="Merge Another"></spring:message>"></input>
						</li>
					</ul>
					
				<div class="clear"></div>
			</div>  
				
				
					
				<div id="dialogtwo" title="Create New Subdistrict Dialog" style="display: none;">
					<ul id="maincontainer" class="listing">
						<li>
							<div class="ms_container">
							<script>
								if(document.getElementById('dialogVillageListCreate')) {
									var selOb = document.getElementById('dialogVillageListCreate');
									var len = selOb.options.length;
									for ( var i = 0; i < len; i++) {
										selOb.options[i].selected = true;
									}
									for ( var i = len - 1; i >= 0; i++) {
										selOb.remove(i);
									}
								}
							</script>
								<div class="ms_selectable" id="list1">
									<label><spring:message text="Step 1 : Select villages that need to add into new Subdistrict"></spring:message></label>
									<select size="1" multiple="multiple" class="frmtxtarea" name="dialogVillageListCreate" id="dialogVillageListCreate">
									</select>
								</div>
								<div class="ms_buttons"><br></br>
									
										<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all bttn" id="btnaddVillagePart" name="Submit4" value=" &gt;&gt;" onclick="addItemNew('selectedDialogVillageListCreate','dialogVillageListCreate',true)" />
									
										<input name="button22" type="button" class="ui-button ui-widget ui-state-default ui-corner-all bttn" onclick="removeOnedistrictOption('selectedDialogVillageListCreate', 'dialogVillageListCreate', true)" value="&lt;&lt;" />
									
								</div>
								<div class="ms_selection" id="list2"><br/><br/>
									<select size="1" multiple="multiple" class="frmtxtarea" name="selectedDialogVillageListCreate" id="selectedDialogVillageListCreate">
									</select>
								</div>
							</div>
						</li>
						<br></br>
							<li>
								<label><spring:message text="Step 2 : Enter new subdistrict details"></spring:message></label></br>
								<spring:message code="Label.NAMEOFNEWSUBDISTRICTENGLISH" htmlEscape="true"></spring:message> <strong><span id="required" class="errormsg">*</span> </strong><br /> 
								<label> <input type="text" name="subDistName" id="subDistId" class="frmfield" htmlEscape="true" /></label>
							</li>
							<br></br>
							<li>
								<input id="createButtonId" type="button" name="create" onclick="closeDialogtwo('Create Sub District');" value="<spring:message htmlEscape="true"  code="Title.CREATENEWSUBDISTRICT"></spring:message>"></input>
								<input id="closeButtonId" type="button" name="close" onclick="closeDialogtwo('Clear');" value="<spring:message htmlEscape="true" code="Button.CLEAR"></spring:message>"></input>
								<input id="doneButtonId" type="button" name="done" onclick="closeDialogtwo('Done');" value="<spring:message htmlEscape="true" code="Label.DONE" text="Done"></spring:message>"></input>
							</li>
						</ul>
					<div class="clear"></div>
				</div>
			
				<div id="dialog-error" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-error" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-error1" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-error1" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-error3" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-error3" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-error2" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-error2" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-merge-success" title="Success Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-merge-success" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-merge-failure" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-merge-failure" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-confirm-create" title="Confirmation Dialog" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-confirm-create" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-confirm" title="Confirmation Dialog" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-confirm" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-district-button" title="Message Dialog" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-district-button" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-confirm-nodata" title="Message Dialog" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-confirm-nodata" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-confirm-dist-name" title="Message Dialog" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-form" htmlEscape="true" text="Please fill mandatory fields in correct format."></spring:message>
					</p>
				</div>
				<div id="dialog-village-button" title="Message Dialog" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-village-button" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-confirm-noname-created-district" title="Message Dialog" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-confirm-noname-created-district" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-confirm-nodata-village" title="Message Dialog" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-confirm-nodata-village" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-error4" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-error4" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-error5" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-error5" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-error6" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-error6" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-error7" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-error7" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-error8" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-error8" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-error9" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-error9" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-confirm-addanother" title="Add another district?" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-confirm-addanother" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-confirm-next" title="Confirmation Dialog" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-confirm-next" htmlEscape="true" text="Are you sure?"></spring:message>
					</p>
				</div>
				<div id="dialog-duplicate-district" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-duplicate-district" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-duplicate-district-two" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-duplicate-district-two" htmlEscape="true"></spring:message>
					</p>
				</div>
				<!-- Added by sushil on 08-05-2013 -->
				<div id="dialog-error-on-done" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-error-on-done" text="Invalid operation, No subdistrict found to merge" htmlEscape="true"></spring:message>
					</p>
				</div>
				
				<!-- Added by vinay on 10-09-2013 -->
				<div id="dialog-confirm-nodata-subdistt" title="Message Dialog" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						Please select at least one full sub-district for merge villages.
					</p>
				</div>
				<div id="dialog-error-subdistt-select-full" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						Can't select all subdistrict(s) of part district as full.
					</p>
				</div>
				<div id="dialog-error-vill-select-full" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						Can't select all village(s) of part sub-district as full.
					</p>
				</div>
				<!--  added by Vinay Yadav ends here -->
				</form:form>	
				
				</div>
				
				</div>
</body>
</html>