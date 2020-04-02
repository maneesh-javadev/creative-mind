
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>
<%@include file="mapParliamentJs.jsp"%>

<script type="text/javascript" language="javascript">
		
	$(document).ready(function() {
		var statecode = document.getElementById('hdnState').value;
		var temp ='';
		var val = '';
		var selObj ='';
		var  i='';
		if (statecode == 34)
		{
		selObj = document.getElementById('ddLgdLBType');
		for ( i = 0; i < selObj.options.length; i++) {
			
			val= selObj.options[i].value;
			temp = val.split(":");
			val = temp[0];
			if(val==2)
				{
				temp =selObj.options[i].value ;
				selObj.options[i].value = "1:D"+temp.substring(3,temp.length);
				}
			
				
		}
		}
		
		});	
	
	function open_win() {
		
		var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
		//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
	} 
	dwr.engine.setActiveReverseAjax(true);

	 if ( window.addEventListener ) { 
	     window.addEventListener( "load",hideAll, false );
	  }
	  else 
	     if ( window.attachEvent ) { 
	        window.attachEvent( "onload", hideAll );
	  } else 
	        if ( window.onLoad ) {
	           window.onload = hideAll;
	  }	
	</script>
</head>


<body oncontextmenu="return false"
	onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);">






	<div class="frmhd">
		<h3 class="subtitle">
			<spring:message htmlEscape="true" code="Label.MOPTLG"></spring:message>
		</h3>
		<ul class="listing">
			<%-- //these links are not working<li>
				<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /> </a>
			</li>
			<li>
				<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message> </a>
			</li>
			<li>
				<a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message> </a>	
			</li> --%>
		</ul>

	</div>

	<div class="clear"></div>
	<div class="frmpnlbrdr">
		<form:form action="MapofParliament.htm" method="POST" id="form1" name="form1"
			commandName="localGovtBodyForm">
			<input type="hidden" name="<csrf:token-name/>"
				value="<csrf:token-value uri="MapofParliament.htm"/>" />
			<form:hidden path="lbFullMap" />
			<form:hidden path="lbPartMap" />
			<form:hidden path="villageMap" />
			<form:hidden path="wardMap" />
			<form:hidden path="deleteMap" />
			<form:hidden path="ccCodeMap" />
			
			<input type="hidden" name="listformat" id="listformat" value="" />
			<input type="hidden" name="selListFormat" id="selListFormat" value="" />
			<input type='hidden' name="hdnType" id="hdnType" value="${typeCode}" />
			<input type='hidden' name="hdnIntermediatePanchayat"
				id="hdnIntermediatePanchayat" value="${hdnIntermediatePanchayat}" />
			<input type='hidden' name="hdnDistrictPanchayat"
				id="hdnDistrictPanchayat" value="${hdnDistrictPanchayat}" />
			<input type='hidden' id="hdnStateCode"
				value='<%=request.getAttribute("stateCode")%>' />
			<input type="hidden" name="flag1" id="flag1" value="0" />
			<input type="hidden" name="choosenlb" id="choosenlb" value="1" />
			<input type="hidden" name="ward_number" id="ward_number" value="1" />
			<input type="hidden" name="lbtiertype" id="lbtiertype"
				value="${Tier}" />
			
			<input type="hidden" name="partULb" id="partULb" value="0" />


			<div id="cat">





				<div class="clear"></div>
				<div class="frmpnlbg">
					<div id='district'>
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true" code="Label.MOPTLG"></spring:message>
							</div>

							<ul class="blocklist">
								<li><spring:message htmlEscape="true"
										code="Label.SELECTPARLIAMENTCONSTITUENCY"></spring:message><span
									class="errormsg">*</span> <br /> <form:select
										path="contributedParliament" class="combofield"
										id="ddStateParliamnetSource" onchange="getAssemblyList(this);">
										<form:option value="">
											<spring:message htmlEscape="true" code="Label.SELECTPC"></spring:message>
										</form:option>
										<form:options items="${distList}" itemValue="id.pcCode"
											itemLabel="pcNameEnglish"></form:options>
									</form:select> <br />
								<span class="errormsg" id="errorddStateParliamnetSource">
								</span> <form:errors htmlEscape="true" path="contributedParliament"
										class="errormsg"></form:errors></li>
							</ul>
							<br></br>
 
							<ul class="blocklist">
								<li><spring:message htmlEscape="true"
										code="Label.SELECTASSEMBLYCONSTITUENCY"></spring:message><br />
									<form:select path="contributedAssembly"
										onchange="loadData(this.value)" class="combofield"
										id="ddassemblySource" >
										<form:option value="">
											<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
										</form:option>
									</form:select> <br />
								<span class="errormsg" id="errorddassemblySource"></li>
								<li></li>
							</ul>



						</div>
					</div>
				</div>
			</div>
			<div id="cat">
				<div class="frmpnlbg" id="divFirstPanel">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message htmlEscape="true" code="Label.CONTRIBUTELOCAL"></spring:message>
						</div>
						<input type="hidden" class="btn" value="${stateCode}"
							id="hdnState" name="hdnState" />



						<ul class="blocklist">
							<li><spring:message htmlEscape="true"
									code="Label.SELECTLOCALBODYTYPE"></spring:message> <span
								class="errormsg">*</span><br /> <form:select
									path="rurallbTypeName" id="ddLgdLBType" class="combofield"
									onchange="hideShowDivbyType(this.value)"
									onfocus="validateOnFocus('ddLgdLBType');">
									<form:option value="">
										<spring:message htmlEscape="true"
											code="Label.SELECTLOCALBODYTYPE"></spring:message>
									</form:option>
									<c:forEach var="localBodyTypelist" varStatus="var"
										items="${localbodyTypelist}">
										<form:option id="typeCode"
											value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.level}:${localBodyTypelist.category}:${localBodyTypelist.localBodyTypeName}">${localBodyTypelist.nomenclatureEnglish}</form:option>
									</c:forEach>
								</form:select> <span class="errormsg" id="errorddLgdLBType"></span> <form:errors
									htmlEscape="true" path="rurallbTypeName" class="errormsg"></form:errors>
							</li>
							<li>
								<ul class='blocklist'>
									<li>
										<div id="divSpecificStateforward">
											<ul class="blocklist">
												<li>
													<div class="ms_container">
														<div class="ms_selectable">
															<span class="errormsg"
																id="ddLgdLBSubDistrictDestLatDCA_error"> </span>&nbsp;<span><form:errors
																	htmlEscape="true" path="lgd_LBWardCArea"
																	class="errormsg"></form:errors> </span> <b><label
																id='lbl_header'><spring:message
																		htmlEscape="true" code="Label.AVAILEPANCHAYATLIST"></spring:message>
															</label>${localGovtBodyForm.districtPanchayatName}&nbsp; </b><br />
															<form:select path="lgd_LBSubDistrictSourceLatDCAUmapped"
																class="frmtxtarea" id="ddLgdWardSubDistrictUListSource"
																multiple="true">

															</form:select>
															<br /> <br />



														</div>
														<div class="ms_buttons">

															<input type="button" value=" Whole &gt;&gt;" class="bttn"
																onclick="moveElementURBAN('FULL');" />
															<br /> <input type="button" id="btnremoveOneULB"
																name="Submit4" value="Back &lt&lt;"
																onclick="moveElementURBAN('BACK')" /><br />
															<input type="button" value="Part &gt;&gt;" class="bttn"
																onclick="moveElementURBAN('PART');" />



														</div>
														<div class="ms_selection">
															<span class="errormsg" id="errorddLgdWardSubDistrictUListDest">
															</span>&nbsp;<span><form:errors
																	htmlEscape="true" path="lgd_LBSubDistrictDestLatDCA"
																	class="errormsg"></form:errors> </span> <b><label
																id='lbl_header_contri'><spring:message
																		htmlEscape="true" code="Label.CONTRIBUTPANCHAYATLIST"></spring:message>
															</label> </b> <br />
															<form:select name="select6"
																id="ddLgdWardSubDistrictUListDest" size="1"
																multiple="true" path="lgd_LBWardCArea"
																class="frmtxtarea">
																<%--  <form:option value="IP1 (FULL)"></form:option>
																											<form:option value="IP2 (FULL)"></form:option>
																											<form:option value="IP3 (PART)"></form:option>  --%>
															</form:select>
															<br /> &nbsp;&nbsp;&nbsp; <input type="button"
																value="<spring:message htmlEscape="true"  code="Button.GETWARDLIST"/>"
																onclick="getWardList();" />
														</div>
													</div>


												</li>

											</ul>
											<div class="clear"></div>


											<ul class="blocklist">
												<li>
													<div class="ms_container">
														<div class="ms_selectable">
															<b><spring:message htmlEscape="true"
																	code="Label.AVAILEWARDLIST"></spring:message> </b><br />
															<form:select path="lgd_LBSubDistrictSourceLatDCA"
																class="frmtxtarea" id="ddLgdLBwardSourceLatDCA"
																multiple="true">

															</form:select>
															<br /> <br />
														</div>
														<div class="ms_buttons">
															<input type="button" class="bttn" value=" Whole &gt;&gt;"
																onclick="moveElementWard('FULL');" />
															<br /> <input type="button" id="btnremoveOneULB"
																class="bttn" name="Submit4" value="Back &lt&lt;"
																onclick="moveElementWard('BACK')" /><br />

														</div>

														<div class="ms_selection">
															<b><spring:message htmlEscape="true"
																	code="Label.CONTRIBUTWARDLIST"></spring:message> </b> <br />
															<form:select name="select6" id="ddLgdLBVillageDestLatDCA"
																size="1" multiple="true" path="lgd_LBwardDestLatDCA"
																class="frmtxtarea">
																<%--  <form:option value="IP1 (FULL)"></form:option>
																									<form:option value="IP2 (FULL)"></form:option>
																									<form:option value="IP3 (PART)"></form:option>   --%>
															</form:select>
															<span class="errormsg" id="errorddLgdLBVillageDestLatDCA"></span>
															<br /> &nbsp;&nbsp;&nbsp; <label> <input
																type="button" id="chooseMoreBtn1" onclick='addCoverdArea();'
																name="chooseMoreBtn1" class="bttn"
																value="Add <spring:message htmlEscape="true"  code="Label.COVEREDAREA"></spring:message>" /><span
																class="errormsg">*</span>
															</label>

														</div>

													</div>





												</li>

											</ul>
											<div class="clear"></div>





										</div>




									</li>
									<li>
										<div id="divRuralDistrictPanchayat">
											<ul class="blocklist">
												<li>
													<div class="ms_container">
														<div class="ms_selectable">
															<b><spring:message htmlEscape="true"
																	code="Label.AVAILABLE"></spring:message>
																&nbsp;${localGovtBodyForm.lgd_LBNomenclatureDist} &nbsp;<spring:message
																	htmlEscape="true" code="Label.LIST"></spring:message> </b><br />
															<form:select path="lgd_LBDistPSourceList"
																class="frmtxtarea" id="ddLgdDispandist"
																items="${districtPanchayatList}"
																itemLabel="localBodyNameEnglish"
																itemValue="localBodyCode" multiple="true">

															</form:select>
															<br /> <br /> <span class="errormsg"
																id="ddLgdLBSubDistrictDestLatDCA_error"> </span>&nbsp;<span><form:errors
																	htmlEscape="true" path="lgd_LBSubDistrictDestLatDCA"
																	class="errormsg"></form:errors> </span>


														</div>
														<div class="ms_buttons">
															<input type="button" value=" Whole &gt;&gt;" class="bttn"
																onclick="moveElementDP('FULL');" />
															<br /> <input type="button" id="btnremoveOneULB"
																name="Submit4" class="bttn" value="Back &lt&lt;"
																onclick="moveElementDP('BACK')" /><br />

														</div>
														<div class="ms_selection">
															<span class="errormsg" id="ddLgdLBDistCAreaDestL_error">
															</span>&nbsp;<span><form:errors htmlEscape="true"
																	path="lgd_LBDistCAreaforintermediate" class="errormsg"></form:errors>
															</span> <b><spring:message htmlEscape="true"
																	code="Label.CONTRIBUTE"></spring:message>
																&nbsp;${localGovtBodyForm.lgd_LBNomenclatureDist} &nbsp;<spring:message
																	htmlEscape="true" code="Label.LIST"></spring:message> </b>
															<br />
															<form:select id="ddLgdLBDistPList" size="1"
																multiple="true" value="D" path="lgd_LBDistPDestList"
																class="frmtxtarea">
															</form:select>
															<br /> &nbsp;&nbsp;&nbsp;
															<%--  <input type="button"
																										value="<spring:message htmlEscape="true"  code="Button.GETINTERMEDIATEL"/>"
																										style="width: 200px"
																										onclick="selectDistrictPanchaytListAtDP();" /> --%>
															<input type="button" id="chooseMoreBtn2"
																onclick='addCoverdArea();' name="chooseMoreBtn2"
																class="bttn"
																value="Add <spring:message htmlEscape="true"  code="Label.COVEREDAREA"></spring:message>" /><span
																class="errormsg">*</span>

														</div>
													</div>



												</li>
												<li></li>

											</ul>
											<div class="clear"></div>



										</div>


									</li>
									<li>
										<div id="divRuralDistrictPanchayatforinter">

											<ul class="blocklist">

												<li><span class="errormsg"
													id="ddLgdLBDistPDestList_error"><spring:message
															htmlEscape="true" code="error.blank.DISTRICTP"></spring:message>
												</span>&nbsp;<span><form:errors htmlEscape="true"
															path="lgd_LBDistPDestList" class="errormsg"></form:errors>
												</span></li>
												<li>

													<div class="ms_container">
														<div class="ms_selectable">

															<b><spring:message htmlEscape="true"
																	code="Label.AVAILABLE"></spring:message>
																&nbsp;${localGovtBodyForm.lgd_LBNomenclatureDist} &nbsp;<spring:message
																	htmlEscape="true" code="Label.LIST"></spring:message> </b><br />
															<form:select path="lgd_LBDistPSourceList"
																class="frmtxtarea" id="ddLgdLBDistPSourceList"
																items="${districtPanchayatList}"
																itemLabel="localBodyNameEnglish"
																itemValue="localBodyCode" multiple="true">

															</form:select>
															<br /> <br />


														</div>

														<div class="ms_buttons">
															<input type="button" value=" Whole &gt;&gt;" class="bttn"
																onclick="addItemPC('ddLgdLBDistPDestList','ddLgdLBDistPSourceList','FULL',true);" />
															<br /> <input type="button" id="btnremoveOneULB"
																class="bttn" name="Submit4" value="Back &lt;"
																onclick="removeItem('ddLgdLBDistPDestList','ddLgdLBDistPSourceList',true)" /><br />
															<input type="button" value=" Reset &lt;&lt;" class="bttn"
																onclick="removeAll('ddLgdLBDistPDestList', 'ddLgdLBDistPSourceList', true)" /><br />
															<input type="button" value="Part &gt;&gt;" class="bttn"
																onclick="addItemPC('ddLgdLBDistPDestList','ddLgdLBDistPSourceList', 'PART',true);" />


														</div>

														<div class="ms_selection">
															<b><spring:message htmlEscape="true"
																	code="Label.CONTRIBUTE"></spring:message>
																&nbsp;${localGovtBodyForm.lgd_LBNomenclatureDist} &nbsp;<spring:message
																	htmlEscape="true" code="Label.LIST"></spring:message> </b>
															<br />
															<form:select id="ddLgdLBDistPDestList" size="1"
																multiple="true" path="lgd_LBDistPDestList"
																class="frmtxtarea">
															</form:select>
															<br /> &nbsp;&nbsp;&nbsp; <span class="errormsg"
																id="ddLgdLBDistCAreaDestL_error"> </span>&nbsp;<span><form:errors
																	htmlEscape="true" path="lgd_LBDistPDestList"
																	class="errormsg"></form:errors> </span>
														</div>

													</div>




												</li>
											</ul>
											<div class="clear"></div>

										</div>


									</li>
									<li>
										<div id="divRuralIntermediatePanchayat">

											<ul class="blocklist">
												<li><b> <spring:message htmlEscape="true"
															code="Label.AVAILABLE"></spring:message>
														&nbsp;${localGovtBodyForm.lgd_LBNomenclatureDist} &nbsp;<spring:message
															htmlEscape="true" code="Label.LIST"></spring:message>
												</b><br /> <form:select path="lgd_LBDistListAtInterCA"
														onchange="getInterPanchyatList(this,true,'ddLgdLBInterPSourceList');"
														class="combofield" id="ddLgdLBDistListAtInterCA">
														<form:option value="0">
															<spring:message htmlEscape="true"
																code="Label.SELECTLOCALBODY"></spring:message>
														</form:option>
														<form:options items="${districtPanchayatList}"
															itemLabel="localBodyNameEnglish"
															itemValue="localBodyCode" />
													</form:select>&nbsp;&nbsp;<form:errors htmlEscape="true"
														path="lgd_LBDistListAtInterCA" class="errormsg"></form:errors>
													&nbsp;&nbsp; <%-- <span class="errormsg"
														id="ddLgdLBDistListAtInterCA_error"><spring:message
																htmlEscape="true" code="error.blank.DistrictPancayatLit"></spring:message> --%>

												</li>
												<li><span class="errormsg"
													id="ddLgdLBInterPDestList_error"> </span>&nbsp;<span><form:errors
															htmlEscape="true" path="lgd_LBInterPDestList"
															class="errormsg"></form:errors> </span></li>

												<li>
													<div class="ms_container">
														<div class="ms_selectable">
															<b><spring:message htmlEscape="true"
																	code="Label.AVAILABLE"></spring:message>
																&nbsp;${localGovtBodyForm.lgd_LBNomenclatureInter}
																&nbsp;<spring:message htmlEscape="true"
																	code="Label.LIST"></spring:message> </b><br />
															<form:select path="lgd_LBInterPSourceList"
																class="frmtxtarea" id="ddLgdLBInterPSourceList"
																multiple="true">

															</form:select>
															<br /> <br />
														</div>
														<div class="ms_buttons">
															<input type="button" class="bttn" value=" Whole &gt;&gt;"
																onclick="moveElementIP('FULL');" />
															<br /> <input type="button" class="bttn"
																id="btnremoveOneULB" name="Submit4" value="Back &lt&lt;"
																onclick="moveElementIP('BACK');" /><br />


														</div>

														<div class="ms_selection">

															<b><spring:message htmlEscape="true"
																	code="Label.CONTRIBUTE"></spring:message>
																&nbsp;${localGovtBodyForm.lgd_LBNomenclatureInter}
																&nbsp;<spring:message htmlEscape="true"
																	code="Label.LIST"></spring:message> </b> <br />
															<form:select name="select6" id="ddLgdLBInterPDestList"
																size="1" multiple="true" path="lgd_LBInterPDestList"
																value="I" class="frmtxtarea">
																<%-- < <form:option value="IP1 (FULL)"></form:option>
																									<form:option value="IP2 (FULL)"></form:option>
																									<form:option value="IP3 (PART)"></form:option>  --%>
															</form:select>
															<br />&nbsp;&nbsp;&nbsp;
															<%-- <input type="button"
																								value="<spring:message htmlEscape="true"  code="Button.GETVILLAGEPANCHAYATL"/>"
																								style="width: 200px" onclick="selectIntermediate();" /> --%>


															<input type="button" id="chooseMoreBtn3"
																onclick='addCoverdArea();' name="chooseMoreBtn3"
																class="bttn"
																value="Add <spring:message htmlEscape="true"  code="Label.COVEREDAREA"></spring:message>" /><span
																class="errormsg">*</span>



														</div>



													</div>



												</li>

											</ul>
											<div class="clear"></div>



										</div>



									</li>

									<li>
										<div id="divRuralVillagePanchayat">

											<ul class="blocklist">
												<li><b> <spring:message htmlEscape="true"
															code="Label.SELECT"></spring:message>
														&nbsp;${localGovtBodyForm.lgd_LBNomenclatureDist}&nbsp;<spring:message
															htmlEscape="true" code="Label.LIST"></spring:message>
												</b><br /> <form:select path="lgd_LBDistListAtVillageCA"
														class="combofield" id="ddLgdLBDistListAtVillageCA"
														onchange="getInterPanchyatList(this,false,'ddLgdLBInterListAtVillageCA');">
														<form:option value="">
															<spring:message htmlEscape="true"
																code="Label.SELECTLOCALBODY"></spring:message>
														</form:option>
														<form:options items="${districtPanchayatList}"
															itemLabel="localBodyNameEnglish"
															itemValue="localBodyCode" />
													</form:select>&nbsp;&nbsp; 
													<form:errors htmlEscape="true"	path="lgd_LBDistListAtVillageCA" class="errormsg"></form:errors><br/>
													<span class="errormsg" id="errorddLgdLBDistListAtVillageCA"></span>
													&nbsp;&nbsp;<%--  <span class="errormsg"
														id="ddLgdLBDistListAtVillageCA_error"><spring:message
																htmlEscape="true" code="error.blank.DistrictPancayatLit"></spring:message></span> --%>



												</li>
												<c:if test="${Tier eq 3}">
													<li><b> <spring:message htmlEscape="true"
																code="Label.SELECT"></spring:message>
															&nbsp;${localGovtBodyForm.lgd_LBNomenclatureInter} &nbsp;<spring:message
																htmlEscape="true" code="Label.LIST"></spring:message>
													</b><br /> <form:select path="lgd_LBInterListAtVillageCA"
															class="combofield" id="ddLgdLBInterListAtVillageCA"
															onchange="getVillagePanchyatList(this,true,'ddLgdLBVillageSourceAtVillageCA');">
															<form:option value="">
																<spring:message htmlEscape="true"
																	code="Label.SELECTLOCALBODY"></spring:message>
															</form:option>

														</form:select> &nbsp;&nbsp;<form:errors htmlEscape="true"
															path="lgd_LBInterListAtVillageCA" class="errormsg"></form:errors><br/>
																<span class="errormsg" id="errorddLgdLBInterListAtVillageCA"></span>
														&nbsp;&nbsp; <%-- <span class="errormsg"
														id="ddLgdLBInterListAtVillageCA_error"><spring:message
																htmlEscape="true" code="error.blank.INTERMEDIATEP"></spring:message></span> --%>

													</li>
												</c:if>
												<li>
													<div class="ms_container">
														<div class="ms_selectable">
															<span class="errormsg"
																id="ddLgdLBVillageDestAtVillageCA_error"> </span>&nbsp;<span><form:errors
																	htmlEscape="true" path="lgd_LBVillageDestAtVillageCA"
																	class="errormsg"></form:errors> </span> <b><spring:message
																	htmlEscape="true" code="Label.AVAILABLE"></spring:message>
																&nbsp;<c:out
																	value="${localGovtBodyForm.lgd_LBNomenclatureVillage}"></c:out>
																&nbsp;<spring:message htmlEscape="true"
																	code="Label.LIST"></spring:message> </b><br />
															<form:select path="lgd_LBVillageSourceAtVillageCA"
																class="frmtxtarea" id="ddLgdLBVillageSourceAtVillageCA"
																multiple="true">

															</form:select>
															<br /> <br />



														</div>
														<div class="ms_buttons">
															<input type="button" value=" Whole &gt;&gt;" onclick="moveElementVP('FULL');" /><br /> 
															<input type="button" id="btnremoveOneULB" name="Submit4" value="Back &lt&lt;" onclick="moveElementVP('BACK');" /><br />
															<input type="button" value="Part &gt;&gt;" onclick="moveElementVP('PART');" />
														</div>
														<div class="ms_selection">
														<span class="errormsg"	id="ddLgdLBSubDistrictDestLatDCA_error"> </span>&nbsp;<span><form:errors
																	htmlEscape="true" path="lgd_LBInterPDestListforvillage"
																	class="errormsg"></form:errors> </span> <b>Contributting <c:out
																	value=" ${localGovtBodyForm.lgd_LBNomenclatureVillage}"></c:out>

															</b> <br />
															<form:select name="select6"
																id="ddLgdLBVillageDestAtVillageCA" size="1"
																multiple="true" path="lgd_LBInterPDestListforvillage"
																class="frmtxtarea">
																<%-- <form:option value="VP1 (PART)"></form:option>
																										<form:option value="VP2 (FULL)"></form:option>
																										<form:option value="VP3 (FULL)"></form:option>  --%>
															</form:select>
															<span class="errormsg"	id="errorddLgdLBVillageDestAtVillageCA">
															<br />&nbsp;&nbsp;&nbsp; <input type="button"
																value="<spring:message htmlEscape="true"  code="Button.GETVILLAGEL"/>"
																class="bttn" onclick="getVillageList();" />

														</div>
													</div>




												</li>
											</ul>
											<div class="clear"></div>
											<ul class="blocklist">
												<li>
													<div class="ms_container">
														<div class="ms_selectable">
															<b><spring:message htmlEscape="true"
																	code="Label.AVAILVILLAGELIST"></spring:message> </b><br />
															<form:select path="lgd_LBSubDistrictSourceLatDCA"
																class="frmtxtarea"
																id="ddLgdLBVillageSourceLatDCAforvillage"
																multiple="true">

															</form:select>
															<br /> <br />
														</div>
														<div class="ms_buttons">
															<input type="button" class="bttn" value=" Whole &gt;&gt;" onclick="moveElementVillage('FULL');" /><br /> 
															<input type="button" class="bttn" id="btnremoveOneULB" name="Submit4" value="Back &lt&lt;" onclick="moveElementVillage('BACK');" /><br />

														</div>
														<div class="ms_selection">
															<b><spring:message htmlEscape="true"
																	code="Label.CONTRIBUTVILLAGELIST"></spring:message> </b> <br />
															<form:select name="select6"
																id="ddLgdLBVillageDestLatDCAforvillage" size="1"
																multiple="true"
																path="lgd_LBSubDistrictDestLatDCAforvillage"
																class="frmtxtarea">
																<%-- <form:option value="VP1 (PART)"></form:option>
																															<form:option value="VP2 (FULL)"></form:option>
																															<form:option value="VP3 (FULL)"></form:option>  --%>
															</form:select>
															<br /> &nbsp;&nbsp;&nbsp; <input type="button"
																id="chooseMoreBtn4" onclick='addCoverdArea();'
																name="chooseMoreBtn4" class="bttn"
																value="Add <spring:message htmlEscape="true"  code="Label.COVEREDAREA"></spring:message>" /><span
																class="errormsg">*</span>

														</div>
													</div>





												</li>
											</ul>

											<div class="clear"></div>


											<div class="errormsg"></div>
										</div>


									</li>
									<li>
										
													<table id="dynamicTbl" path="newAddLocalBody" width="100%"
														class="data_grid">
													  <thead>
														<tr>
															<th width="5%">S.No.</th>
															<!-- <th width="10%">Entity Type</th> -->
															<th width="30%">Entity Name</th>
															<th width="5%">Coverage Type</th>
															<th width="40%">Entity Hierarchy Details</th>
															<th width="10%">Delete</th>
															<!-- <th>Delete</th> -->
														</tr>
														</thead>



													</table>
												</li>
												<li>
												<br/><br/><br/>
													<div class="btnpnl">
														<!-- added by kirandeep on 18//2014 -->
														<input type="button" class="bttn" id="Submit"
															name="Submit"
															value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>"
															onclick="callActionUrl();" /> <input type="button"
															name="Submit2" class="bttn"
															value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>"
															onclick="javascript:location.href='map_constitutionBody.htm?<csrf:token uri="map_constitutionBody.htm"/>'" />
														<input type="button" class="bttn" name="Submit6"
															value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
															onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
													</div>



									</li>
									<li></li>
									<li></li>
									<li></li>

								</ul>




							</li>
							<li></li>
						</ul>


					</div>
				</div>


			</div>

		</form:form>

	</div>



	<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>


</body>
</html>

