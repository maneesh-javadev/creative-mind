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
<title><spring:message htmlEscape="true"  code="Label.CREATENEWLOCALGOVTBODY"></spring:message>
</title>
<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<script src="js/validation.js"></script>
    <script src="js/successMessage.js"></script>
	<script src="js/helpMessage.js"></script>
	<script src="js/trim-jquery.js"></script>
	<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<link href="css/errorLB.css" rel="stylesheet" type="text/css" />

<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>
	
</script>
<script src="js/local_body.js"></script>
<script src="js/lgd_localbody.js"></script>
<script src="js/localbody_validation.js"></script>

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>

<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDesignationService.js'></script>	

<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
	dwr.engine.setActiveReverseAjax(true);
	
	
	 function redirectNornmalFlow(value) {
		
		 var stateCode = dwr.util.getValue('hdnStateCode');
		 var e = document.getElementById("ddLgdLBType");
		 var strUser = e.options[e.selectedIndex].text;
		 if (value == 1) {
			var id = $('#ddLgdLBType').val();
			var dis = '<c:out value="${districtCode}" escapeXml="true"></c:out>';
			var type = '<c:out value="${lbType}" escapeXml="true"></c:out>';
			$('#zp').show();
			$('#villagepan').show();
			$('#gtachildtr').hide();
			$('#gtaIntermediate').hide();
			hideShowDivforLocalBody(id, dis,type);
		} else {
			$('#zp').hide();
			$('#villagepan').hide();
			$('#gtachildtr').show();
			
			lgdDwrlocalBodyService.getPanchayatListbylblcCode(stateCode, value, {
				callback : getParentLbSuccess,
				errorHandler : handledisPanchayatError
			});
		}

	} 
	 
	 function hidegta(){
		 $('#gtadp1').hide();
	 	 $('#gtachildtr').hide();
	 	 $('#gtaIntermediate').hide();
	 }
	 function hideshow(id){
		    hidegta();
			var temp = id.split(":");
			var id1 = temp[0];
			var id2 = temp[1];
			var id3 = temp[2];
			var id4 = temp[3];
			var id5 = temp[4];
			var stateCode = dwr.util.getValue('hdnStateCode');
			if(stateCode==19){
			if(id2 =='V' || id2 =='I'){
				$('#gtadp1').show();
			}else{
				$('#gtadp1').hide();
				$('#gtachildtr').hide();
				$('#gtaIntermediate').hide();
			}
			}
		 
	 }
	function validateGta(){
		var gtadp =('#gtadp').val();
		if(gtadp.selectedIndex==''){
			$('#errParentType').show();
			return false;
		}
		else
			return true;
		
	}
	function getGtaIntermediateData() {

		var id = document.getElementById("ddLgdLBType").value;
		var statevalue = dwr.util.getValue('hdnStateCode');
		if (id != 0) {
			var temp = id.split(":");

			var id1 = temp[0];
			var id2 = temp[1];
			var id3 = temp[2];
			var id4 = temp[3];
			var id5 = temp[4];
		}
		var e1 = document.getElementById("gtachild");
		var strUser = e1.options[e1.selectedIndex].value;
		if (statevalue == 19 && id2=='V') {
			$('#gtaIntermediate').show();
			lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(strUser, {
				callback : handleInterGtaSuccess,
				errorHandler : handleInterGtaError
			});
		} else {
			$('#gtaIntermediate').hide();
		}
	}
	function handleInterGtaSuccess(data) {
		// Assigns data to result id

		var fieldId = 'gtaIntermediateid';
		var valueText = 'localBodyCode';
		var nameText = 'localBodyNameEnglish';

		dwr.util.removeAllOptions(fieldId);
		//var st = document.getElementById('ddLgdLBInterListAtVillageCA');
		//st.add(new Option('Select Intermediate Panchayat', '0'));
		var st = document.getElementById(fieldId);
	    st.add(new Option('Select', '0'));
		dwr.util.addOptions(fieldId, data, valueText, nameText);

	}
	
	function handleInterGtaError() {

		alert("No data Found");
	}
	
</script>

<%@include file="../common/dwr.jsp"%>
</head>

<body onload="hidegta();" onkeypress="disableCtrlKeyCombination(event);"
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
					<td><div class="errorfont">Please enter required fields</div>
					</td>
				</tr>
			</table>

		</div>
	</div>
	
	
	<div id="frmcontent">
		<div class="frmhd">
		<h3 class="subtitle">
			<spring:message htmlEscape="true"  code="Label.CREATENEWLOCALGOVTBODY"></spring:message>
		</h3>
		<ul class="listing">
			<%--//these links are not working <li>
				<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
			</li>
			<li> 
				<a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a>
			</li> --%>
		</ul>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="draftLocalBodyforPRI.htm"
				commandName="localGovtBodyForm" method="POST"
				enctype="multipart/form-data" id="localBodyFormId">

				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="draftLocalBodyforPRI.htm"/>" />
					
					
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">

							<div class="frmhdtitle">
								<label></label>
								<spring:message htmlEscape="true"  code="Label.GENERALDETAILNEWLOCALBODY"></spring:message>
								
							</div>
							<!-- <table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" rowspan="10"> -->
								<ul class = "blocklist">
									<li>
									<input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("stateCode")%>' /> 
									<form:hidden path="govtOrderConfig" value="${govtOrderConfig}"/>
										<input type="hidden" name="districtCode" value="<c:out value='${districtCode}' escapeXml='true'></c:out>"/>
										<input type="hidden" id="lbType" value="<c:out value='${lbType}' escapeXml='true'></c:out>"/>
										<input type="hidden" id="level" />
										<input type="hidden" name="flagCode" id="flagCode" />
										<input type="hidden" name="hideMap" id="hideMap" value="<c:out value='${hideMap}' escapeXml='true'></c:out>"/>
										<input type="hidden" name="levelcheck" id="levelcheck" value="<c:out value='${levelcheck}' escapeXml='true'></c:out>"></input>
									</li>
									<li>
									<label for = "localBodyNameInEn"><spring:message htmlEscape="true" 
												code="Label.LOCALBODYNAMEENGLISH"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:input
											path="lgd_LBNameInEn" id="localBodyNameInEn" size="40"
											maxlength="200" cssClass="frmfield" htmlEscape="true"
											onfocus="show_msgEng('localBodyNameInEn')"
											onblur="validateLocalNameInEng()"
											onkeypress="return validateSpecialCharactersUpdateStandardCodeLocal(event);" /> 
											
											<%-- <span class="errormsg" id="localBodyNameInEn_error"><spring:message htmlEscape="true" 
												code="Msg.LOCALBODYNAMEEN"></spring:message> </span> <span><form:errors htmlEscape="true" 
												path="lgd_LBNameInEn" class="errormsg"></form:errors> </span> --%>
												
											<div id="localBodyNameInEn_error" class="error"><spring:message code="error.blank.localBodyNameInEn" htmlEscape="true"></spring:message></div>
											<div class="errormsg" id="localBodyNameInEn_error1"><form:errors path="lgd_LBNameInEn" htmlEscape="true"/></div>  
											<div class="errormsg" id="localBodyNameInEn_error2" style="display: none" ></div>
												
									</li>
									<li>
									<label for = "localBodyNameInLcl"><spring:message htmlEscape="true" 
												code="Label.LOCALBODYNAMELOCAL"></spring:message> </label><br /> <form:input
											path="lgd_LBNameInLocal" id="localBodyNameInLcl" size="40"
											maxlength="100" cssClass="frmfield" htmlEscape="true"
											onfocus="show_msg('localBodyNameInLcl')" 
											onblur="validateSpecialCharactersLBName(this.value);" />
											
											<%-- <span><form:errors htmlEscape="true" 
												path="lgd_LBNameInLocal" class="errormsg"></form:errors> </span> --%>
											<div class="errormsg" id="localBodyNameInLcl_error1"><form:errors path="lgd_LBNameInLocal" htmlEscape="true"/></div>  
											<div class="errormsg" id="localBodyNameInLcl_error2" style="display: none" ></div>			
									</li>
									<li>
									<label for = "localBodyAliasInEn"><spring:message htmlEscape="true" 
												code="Label.LOCALBODYALIASENGLISH"></spring:message> </label><br />
										<form:input path="lgd_LBAliasInEn" id="localBodyAliasInEn"
											size="40" maxlength="50" cssClass="frmfield" htmlEscape="true"
											onfocus="show_msg('localBodyAliasInEn')" onblur="AliasInEn()"
											onkeypress="return validateSpecialCharactersUpdateStandardCodeLocal(event);" />
											
									<%-- 	<span><form:errors htmlEscape="true" 
											path="lgd_LBAliasInEn" class="errormsg"></form:errors> </span>
									 --%>		
									 		<div class="errormsg" id="localBodyAliasInEn_error1"><form:errors path="lgd_LBAliasInEn" htmlEscape="true"/></div>  
											<div class="errormsg" id="localBodyAliasInEn_error2" style="display: none" ></div>			
									</li>
									<li>
									<label for = "localBodyAliasInLcl"><spring:message htmlEscape="true" 
												code="Label.LOCALBODYALIASLOCAL"></spring:message> </label><br /> <form:input
											path="lgd_LBAliasInLocal" id="localBodyAliasInLcl" size="40"
											maxlength="50" cssClass="frmfield" htmlEscape="true"
											onfocus="show_msg('localBodyAliasInLcl')"
											onblur="validateSpecialCharactersLBNameAliasLocal(this.value);" />
											
											<%-- <span><form:errors htmlEscape="true" 
												path="lgd_LBAliasInLocal" class="errormsg"></form:errors> </span>
										 	--%>
										 	<div class="errormsg" id="localBodyAliasInLcl_error1"><form:errors path="lgd_LBAliasInLocal" htmlEscape="true"/></div>  
											<div class="errormsg" id="localBodyAliasInLcl_error2" style="display: none" ></div>			
									</li>
									<li>
									<label for = "stateSpecificCode"><spring:message htmlEscape="true" 
												code="Label.STATESPECIFICCODE"></spring:message> </label><br /> <form:input
											path="lgd_LBstateSpecificCode" id="stateSpecificCode"
											size="40" maxlength="7" cssClass="frmfield" htmlEscape="true"
											onfocus="show_msg('stateSpecificCode')"
											onblur="specificCode()" 
											onkeypress="return validateAlphanumericKeysStateSp(event);"/>
											
									<%-- 		<span><form:errors htmlEscape="true" 
												path="lgd_LBstateSpecificCode" class="errormsg"></form:errors> </span>--%>
											<div class="errormsg" id="stateSpecificCode_error1"><form:errors path="lgd_LBstateSpecificCode" htmlEscape="true"/></div>  
											<div class="errormsg" id="stateSpecificCode_error2" style="display: none" ></div>	
												
									</li>
								</ul>
							</div>
						</div>
				

				 <%-- 	<%@ include file="../common/gis_nodes.jspf"%>  --%>

					<div id='divLgdLBType' class="frmpnlbg">
						<!--'district' id name change  -->
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true" code="Label.SELECTEDTYPELOCALBODY"></spring:message>
							</div>
								<ul class ="blocklist">
									<li>
									<label  for = "ddLgdLBType"><spring:message htmlEscape="true" 
												code="Label.SELECTLOCALBODYTYPE"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:select
											path="lgd_LBTypeName" id="ddLgdLBType"
											onchange="hideShowDivforLocalBody(this.value,'${districtCode}','${lbType}');hideshow(this.value);"
											cssClass="combofield">
											<!--tierSetupCode id Changed  -->
											<form:option value="0">
												<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message>
											</form:option>

											<c:forEach var="localBodyTypelist" varStatus="var"
												items="${localBodyTypelist}">
												<form:option id="typeCode"
													value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.level}:${localBodyTypelist.nomenclatureEnglish}:${localBodyTypelist.tierSetupCode}:${localBodyTypelist.parentLocalBodyTypeCode}"><c:out value="${localBodyTypelist.nomenclatureEnglish}" escapeXml="true"></c:out></form:option>
											</c:forEach>
										</form:select>&nbsp;&nbsp; 
										
									<%-- 	<span> <form:errors htmlEscape="true"  path="lgd_LBTypeName"
												class="errormsg"></form:errors>
											</span> &nbsp;&nbsp; <span class="errormsg" id="ddLgdLBType_error">
												<spring:message htmlEscape="true"  code="error.SOURCESELECTLOCALBODYTYPE"></spring:message>
											</span> <br /> --%>
										<div id="ddLgdLBType_error" class="error"><spring:message code="error.SOURCESELECTLOCALBODYTYPE" htmlEscape="true"></spring:message></div>
										<div class="errormsg" id="ddLgdLBType_error1"><form:errors path="lgd_LBTypeName" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddLgdLBType_error2" style="display: none" ></div>
									
									</li>
								<!-- <tr>
									<td>&nbsp;</td>
								</tr>
								 -->
								
								<!-- gta and district panchyat list	  -->
												
												<%-- <c:if test="${westbengal}"> --%>
												<li id="gtadp1">
														<input type="hidden" id="gtaanddp" />
															<input type="hidden" id="gtadp2" /> <input type="hidden"name="flagCode" id="flagCode" /> <input type="hidden" name="" id="lblc" /> <label><spring:message code="label.SelectParentLocalbodytype" text="Select Parent Local body type" htmlEscape="true">
																</spring:message></label><span class="errormsg">*</span><br /> <label>
																 <form:select path="parentList" id="gtadp" cssClass="combofield" htmlEscape="true" onfocus=";helpMessage(this,'ddLgdLBTypeMsg');" onchange="redirectNornmalFlow(this.value);clearLBLists();" onkeydown="" onblur="hideHelp();"  >
																	<form:option value=""><form:option selected="selected" value="" label="--select--" /></form:option>
																	</form:select>
																<div id="errParentType" class="errormsg"><spring:message text="Select parent type" htmlEscape="true"></spring:message></div>
														</label> &nbsp;&nbsp;
													</li>
												
							    <!-- gta and district panchyat list	  -->
													
								<!-- gta panchyat list	  -->
													<li	id="gtachildtr">
														<input type="hidden" id="gtaanddp1" />
															<input type="hidden" id="gtadp2" /> <input type="hidden"name="flagCode" id="flagCode" /> <input type="hidden" name="" id="lblc" /> <label><spring:message code="label.SelectGTA" text="Gorkhaland Territorial Administration" htmlEscape="true">
																</spring:message></label><span class="errormsg">*</span><br /> <label>
																 <form:select path="gtaList"  id="gtachild" htmlEscape="true" cssClass="combofield" onfocus=";helpMessage(this,'ddLgdLBTypeMsg');" onchange="getGtaIntermediateData();" onkeydown="" onblur="hideHelp();" >
																	<form:option value=""><form:option selected="selected" value="" label="--select--" /></form:option>
																	</form:select>
																<div id="errSelectGta" class="errormsg"><spring:message text="Kindly add the Gta Child List" htmlEscape="true"></spring:message></div>
														</label> &nbsp;&nbsp;
													</li>
												<%-- </c:if> --%>
								<!-- gta panchyat list	  -->
													<li id="gtaIntermediate">
														<input type="hidden" id="gtaIntermediate1" />
															<input type="hidden" id="gtaIntermediate2" /> <input type="hidden"name="flagCode" id="flagCode" /> <input type="hidden" name="" id="lblc" /> <label><spring:message code="label.SelectIntermediate" text="Select intermediate Panchayat" htmlEscape="true">
																</spring:message></label><span class="errormsg">*</span><br /> <label>
																 <form:select path="GtaInterPanch"  id="gtaIntermediateid" htmlEscape="true" cssclass="combofield" onfocus=";helpMessage(this,'ddLgdLBTypeMsg');" onchange="" onkeydown="" onblur="hideHelp();" >
																	<form:option value=""><form:option selected="selected" value="" label="--select--" /></form:option>
																	</form:select>
																<div id="errSelectGtaInter" class="errormsg" style="display: none;"><spring:message text="Kindly add the GTA Child List" htmlEscape="true"></spring:message></div>
														</label> &nbsp;&nbsp;
													</li>
												
								
									<li>
										<div id="divLgdlistSubTypeCode" class="frmpnlbg" style="visibility: hidden; display: none;">
											<ul class = "listing">
												<li>
													<label><spring:message htmlEscape="true" 
																code="Label.AVAILSUBTYPE"></spring:message></label> <br /> <form:select
															path="localbodySubtype" cssClass="combofield " id="ddlgdLBSubTypeCode"
															>
														</form:select>  
														<br />
													</li>
												</ul>
										</div>
									</li>
								
									<li>
										<div id="divLgdSelIntermediateP" style="visibility: hidden; display: none;">
											<!--  tr_district id name Change-->
											<%-- <table width="100%">
												<c:if test="${! empty localGovtBodyForm.lgd_LBNomenclatureDist}">
												
												<tr id="zp">
													<td> --%>
													<ul class = "blocklist">
													<li>
													<label><spring:message htmlEscape="true" code="Label.SELECT" ></spring:message>
														</label><label id="firstlevel"></label>
																												
															<span class="errormsg">*</span><br /> 
														<form:select
															path="lgd_LBDistrictAtInter" cssClass="combofield"
															id="ddlgdLBDistAtInter" 
															onchange="getWorkOnIntermediatePanchayat(this.value)">
															<%--ddSourceLocalBody id name changed  --%>
															<form:option value="0">
																<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
															</form:option>
															<form:options items="${districtPanchayatList}"
																itemLabel="localBodyNameEnglish"
																itemValue="localBodyCode" />
														</form:select> &nbsp;
														
														<div id="ddlgdLBDistAtInter_error" class="error"><spring:message code="error.SOURCESELECTLOCALBODYPARENT" htmlEscape="true"></spring:message></div>
														<div class="errormsg" id="ddlgdLBDistAtInter_error1"><form:errors path="lgd_LBDistrictAtInter" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none" ></div>
													</li>
												<%-- </c:if> --%>
											</ul>
										</div>
									</li>

									<li>
										<%-- <div id="divLgdVillageP" style="visibility: hidden; display: none;">
											<!-- tr_intermediate id change -->

											<table width="100%">
												
												<c:if test="${! empty localGovtBodyForm.lgd_LBNomenclatureDist}">
												<tr>
													<td><label><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>&nbsp;&nbsp;</label><label id="InterVP"></label><span
														class="errormsg">*</span><br /> <form:select
															path="lgd_LBDistrictAtVillage" class="combofield"
															id="ddlgdLBDistrictAtVillage"
															onchange="getWorkOnVillagePanchayatforDistrictP(this.value,'${localGovtBodyForm.lgd_LBNomenclatureInter}');"
															style="width: 200px">
															<form:option value="0">
																<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
															</form:option>
															<form:options items="${districtPanchayatList}"
																itemLabel="localBodyNameEnglish"
																itemValue="localBodyCode" />
														</form:select> &nbsp;
														
														<div id="ddlgdLBDistrictAtVillage_error" class="error"><spring:message code="error.SOURCESELECTVILLAGEPARENT" htmlEscape="true"></spring:message></div>
														<div class="errormsg" id="ddlgdLBDistrictAtVillage_error1"><form:errors path="lgd_LBDistrictAtVillage" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddlgdLBDistrictAtVillage_error2" style="display: none" ></div>
													
													</td>
													<td>&nbsp;</td>
												</tr>
												</c:if>
												<c:if test="${! empty localGovtBodyForm.lgd_LBNomenclatureInter}">
												<tr>
													<td><label><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>&nbsp;&nbsp;</label><label id="districtVP"></label><span
														class="errormsg">*</span><br /> <form:select
															path="lgd_LBIntermediateAtVillage" class="combofield"
															id="ddlgdLBInterAtVillage" style="width: 200px"
															onchange="setandgetInterPanchayatbyDcodeAtVCAforPanc(this.value);">
															<form:option value="0">
																<spring:message htmlEscape="true" code="Label.SELECTINTERMEDIATELOCBODY"></spring:message>
															</form:option>
															<form:options items="${intermediatePanchayatList}"
																itemLabel="localBodyNameEnglish"
																itemValue="localBodyCode" />
														</form:select> &nbsp;
														
														<div id="ddlgdLBInterAtVillage_error" class="error"><spring:message code="error.SOURCESELECTVILLAGEPARENT" htmlEscape="true"></spring:message></div>
														<div class="errormsg" id="ddlgdLBInterAtVillage_error1"><form:errors path="lgd_LBIntermediateAtVillage" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddlgdLBInterAtVillage_error2" style="display: none" ></div>
													
													</td>
													<td>&nbsp;</td>

												</tr>
												</c:if>


											</table>
										</div> --%>
										<div id="divLgdVillageP" style="visibility: hidden; display: none;">
											<!-- <table width="100%"> -->
											<ul class = "blocklist">	
												<%-- <c:if test="${! empty localGovtBodyForm.lgd_LBNomenclatureDist}"> --%>
												<li id="villagepan">
												<!-- <tr id="villagepan"> -->
													<!-- <td> --><label><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>&nbsp;</label><label id="secondlevel"></label><span
														class="errormsg">*</span><br /> <form:select
															path="lgd_LBDistrictAtVillage" cssClass="combofield"
															id="ddlgdLBDistrictAtVillage"
															onchange="getWorkOnVillagePanchayatforDistrictP(this.value);"
															>
															<form:option value="0">
																<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
															</form:option>
															<%-- <form:options items="${districtPanchayatList}"
																itemLabel="localBodyNameEnglish"
																itemValue="localBodyCode" /> --%>
															<form:option selected="selected" value="" label="--select--" />
														</form:select> &nbsp;
														
														<div id="ddlgdLBDistrictAtVillage_error" class="error"><spring:message code="error.SOURCESELECTVILLAGEPARENT" htmlEscape="true"></spring:message></div>
														<div class="errormsg" id="ddlgdLBDistrictAtVillage_error1"><form:errors path="lgd_LBDistrictAtVillage" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddlgdLBDistrictAtVillage_error2" style="display: none" ></div>
													
													<!-- </td>
													<td>&nbsp;</td> -->
												</li>
												<%-- </c:if> --%>
												</ul>
										</div>
										<div id="divLgdVillagePanch" style="visibility: hidden; display: none;">
											<!-- <table width="100%"> -->
											<ul class = "blocklist">
												<%-- <c:if test="${! empty localGovtBodyForm.lgd_LBNomenclatureInter}"> --%>
												<li>
													<label><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>&nbsp;</label><label id="thirdlevel"></label><span
														class="errormsg">*</span><br /> <form:select
															path="lgd_LBIntermediateAtVillage" cssClass="combofield"
															id="ddlgdLBInterAtVillage" 
															onchange="setandgetInterPanchayatbyDcodeAtVCAforPanc(this.value);">
															<form:option value="0">
																<spring:message htmlEscape="true" code="Label.SELECTINTERMEDIATELOCBODY"></spring:message>
															</form:option>
															<%-- <form:options items="${intermediatePanchayatList}"
																itemLabel="localBodyNameEnglish"
																itemValue="localBodyCode" /> --%>
															<form:option selected="selected" value="" label="--select--" />
														</form:select> &nbsp;
														
														<div id="ddlgdLBInterAtVillage_error" class="error"><spring:message code="error.SOURCESELECTVILLAGEPARENT" htmlEscape="true"></spring:message></div>
														<div class="errormsg" id="ddlgdLBInterAtVillage_error1"><form:errors path="lgd_LBIntermediateAtVillage" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddlgdLBInterAtVillage_error2" style="display: none" ></div>
													
													</li>

												</ul>
												<%-- </c:if> --%>
										</div>	
									</li>
								<c:if test="${fn:containsIgnoreCase(localGovtBodyForm.lbType,'P')}">
								<li>								
									<br /> <label><spring:message htmlEscape="true" 
												code="Label.PESAACT"></spring:message>&nbsp;&nbsp;&nbsp; <form:radiobutton
												path="lgd_LBPesaAct" value="Yes" /> </label> <spring:message htmlEscape="true" 
											code="Label.PESAACTYES"></spring:message>&nbsp;&nbsp; <form:radiobutton
											path="lgd_LBPesaAct" value="No" checked="true" /> <spring:message htmlEscape="true" 
											code="Label.PESAACTNO" /> <br />
								</li>
							 	</c:if>
							</ul>
						</div>

					</div>
					<ul class="blocklist">
						<li>
							<%@ include file="../common/gis_nodesforlocalbody.jspf"%>  
						</li>
					</ul>
					<div id='divLgdLBCoveredArea' class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"  code="Label.COVEREDAREAOFNEWLCLBDY"></spring:message>
							</div>
							<!-- <table class="tbl_no_brdr">
								<tr>
									<td width="14%" rowspan="6">&nbsp;</td>
									<td width="86%"></td>
								</tr> -->
							<ul class = "blocklist">
								<li>
								<!-- <tr>
									<td colspan="2" width="100%"> -->
										<!-- <table class="tbl_no_brdr" width="100%">

											<tr>
												<td>&nbsp;&nbsp; -->
										<ul class = "blocklist">
											<li>
												<%-- <span class="errormsg"
													id="chkLgdLBExistChk_error"> <spring:message htmlEscape="true" 
															code="error.EXISTINGLOCALBODY"></spring:message>
												</span> --%>
												<div id="chkLgdLBExistChk_error" class="error"><spring:message code="error.EXISTINGLOCALBODY" htmlEscape="true"></spring:message></div>
												
											</li>
											<li>
												<form:checkbox id="chkLgdLBExistChk"
														onclick="getHideLocalBodyParentList(document.getElementById('ddLgdLBType').value, this.checked);"
														value="true" path="lgd_LBExistCheck"></form:checkbox> <label><spring:message htmlEscape="true" 
															code="Label.SELEXISTINGCOVEREDAREA"></spring:message>
														</label>	
													<%-- 		<span><form:errors htmlEscape="true" 
																path="lgd_LBExistCheck" class="errormsg"></form:errors>
													</span> 
													 --%>
													<div class="errormsg" id="chkLgdLBExistChk_error1"><form:errors path="lgd_LBExistCheck" htmlEscape="true"/></div>  
													<div class="errormsg" id="chkLgdLBExistChk_error2" style="display: none" ></div>
											</li>

										</ul>
									</li>
									<li>
										<div id='divLgdLBDistCArea' class="frmpnlbg" style="visibility: hidden; display: none;">
										<ul class = "blocklist">
											<li>
													<%-- <span class="errormsg"
														id="ddLgdLBDistPDestList_error"><spring:message htmlEscape="true" 
																code="error.blank.DistrictPanchayt"></spring:message> </span> --%>
													
													<div id="ddLgdLBDistPDestList_error" class="error"><spring:message code="error.blank.DistrictPanchayt" htmlEscape="true"></spring:message></div>
																
													<%--  <span
														class="errormsg" id="ddLgdLBDistPDestList1_error"><spring:message htmlEscape="true" 
																code="error.DESTDISTLOCALBODY"></spring:message> </span> &nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBDistPDestList" class="errormsg"></form:errors>
													</span>
													 --%>
													<div id="ddLgdLBDistPDestList1_error" class="error"><spring:message code="error.DESTDISTLOCALBODY" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBDistPDestList1_error1"><form:errors path="lgd_LBDistPDestList" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBDistPDestList1_error2" style="display: none" ></div>
													<div class="errormsg" id="ddLgdLBDistPDestList_error1"><form:errors path="lgd_LBDistPDestList" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBDistPDestList_error2" style="display: none" ></div>
												</li>
												<li>
													<div class="ms_container">
														<div class="ms_selectable">
															<label><spring:message htmlEscape="true" 
																code="Label.AVAILABLE"></spring:message></label>
																&nbsp;
															<label id="districtPanchAvail"></label>&nbsp;&nbsp;<label><spring:message htmlEscape="true" 
																code="Label.LIST"></spring:message> </label>
															<span class="errormsg">*</span><br /> <form:select
															path="lgd_LBDistPSourceList" class="frmtxtarea"
															id="ddLgdLBDistPSourceList"
															items="${districtPanchayatList}"
															itemLabel="localBodyNameEnglish"
															itemValue="localBodyCode" multiple="true">

															</form:select> <br /> <br />
														</div>
														<div class="ms_buttons">
															<input type="button" class="bttn"
															value="<spring:message
																	code="Button.WHOLE"/>"
															onclick="addItemforLBFULL('ddLgdLBDistPDestList','ddLgdLBDistPSourceList','FULL',true);" />
															<input type="button" id="btnremoveOneULB" class="bttn"
															name="Submit4" value="Back &lt;"
															onclick="removeItemLocalBody('ddLgdLBDistPDestList','ddLgdLBDistPSourceList',true)" />
															<input type="button" value=" Reset &lt;&lt;"
															class="bttn"
															onclick="removeAllLocalBody('ddLgdLBDistPDestList', 'ddLgdLBDistPSourceList', true)" />
															<input type="button" value="Part &gt;&gt;" class="bttn"
															onclick="addItemforLB('ddLgdLBDistPDestList','ddLgdLBDistPSourceList', 'PART',true);" />
														</div>
													<div class="ms_selection">
													<label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTE"></spring:message></label>
															&nbsp;<label id="districtPanchContri"></label>&nbsp;&nbsp;<label><spring:message htmlEscape="true" 
																code="Label.LIST"></spring:message> </label><span
														class="errormsg">*</span> <br /> <form:select
															id="ddLgdLBDistPDestList" size="1" multiple="true"
															path="lgd_LBDistPDestList" class="frmtxtarea">

														</form:select> <br /> &nbsp;&nbsp;&nbsp; <label><input class="bttn" type="button"
														value="<spring:message htmlEscape="true" code="Button.GETCOVEREDAREAOFLOCALBODY"/>"
														onclick="selectLocalBodyListAtDCA();" /></label>
													</div>
												</div>
												</li>
												<li><div class="clear"></div></li>
												<li>
													
													<%-- <span class="errormsg"
														id="ddLgdLBDistCAreaDestL_error"><spring:message htmlEscape="true" 
																code="error.blank.DISTRICTCAatDCA" /> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBDistCAreaDestList" class="errormsg"></form:errors>
													</span> --%>
													
													<div id="ddLgdLBDistPDestList_error" class="error"><spring:message code="error.blank.DISTRICTCAatDCA" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBDistPDestList_error1"><form:errors path="lgd_LBDistPDestList" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBDistPDestList_error2" style="display: none" ></div>
													
												</li>
												<li>
													<div class="ms_container">
														<div class="ms_selectable">
															<label><spring:message htmlEscape="true" 
																		code="Label.AVAILDISTRICTLIST"></spring:message> </label><br />
																<form:select path="lgd_LBDistCAreaSourceList"
																	class="frmtxtarea" id="ddLgdLBDistCAreaSourceL"
																	multiple="true">
		
																</form:select><br /> <br />
														</div>
														<div class="ms_buttons">
															<input type="button" class="bttn"
																value="<spring:message
																		code="Button.WHOLE"/>" 
																onclick="addItemforVillageLocalBodyFF('ddLgdLBDistCAreaDestL','ddLgdLBDistCAreaSourceL','ddLgdLBDistPDestListhidden','FULL',true,'D');" />
																 <input type="button" id="btnremoveOneULB" class="bttn"
																name="Submit4" value="Back &lt;"
																onclick="removeItemSubdistrictList('ddLgdLBDistCAreaDestL','ddLgdLBDistCAreaSourceL',true)" />
																<input type="button" class="bttn" value=" Reset &lt;&lt;"
																onclick="removeAllSubdistrictList('ddLgdLBDistCAreaDestL', 'ddLgdLBDistCAreaSourceL', true)" />
																<input type="button" class="bttn" value="Part &gt;&gt;"
																onclick="addItemforVillageLocalBodyFF('ddLgdLBDistCAreaDestL','ddLgdLBDistCAreaSourceL','ddLgdLBDistPDestListhidden','PART',true,'D');" />
														</div>
														<div class="ms_selection">
															<label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTDISTRICTLIST"></spring:message> </label> <br />
															<form:select name="select6" id="ddLgdLBDistCAreaDestL"
															size="1" multiple="true" path="lgd_LBDistCAreaDestList"
															class="frmtxtarea" >
															</form:select><br />
													
															&nbsp;&nbsp;&nbsp; <label><input type="button" class="bttn"
															value="<spring:message htmlEscape="true" code="Button.GETSUBDISTRICTL"/>"
															onclick="selectSubdistrictAtDCAFinal();" /></label>
														</div>
													</div>
												</li>
												<li><div class="clear"></div></li>
												<%-- Data Is not found For SubDistrict so Hide tha Below Pan --%>
												<li>													
												<%-- <span class="errormsg"
														id="ddLgdLBSubDistrictDestLatDCA_error"> <spring:message htmlEscape="true" 
																code="error.blank.SUBDISTRICTCAatDCA" />
													</span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBSubDistrictDestLatDCA" class="errormsg"></form:errors>
													</span> --%>
													<div id="ddLgdLBSubDistrictDestLatDCA_error" class="error"><spring:message code="error.blank.SUBDISTRICTCAatDCA" htmlEscape="true"></spring:message></div> 
													<div class="errormsg" id="ddLgdLBSubDistrictDestLatDCA_error1"><form:errors path="lgd_LBSubDistrictDestLatDCA" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBSubDistrictDestLatDCA_error2" style="display: none"></div>
												</li>
												<li>
													<div class="ms_container">
														<div class="ms_selectable">
															<label><spring:message htmlEscape="true" 
																		code="Label.AVAILSUBDISTRICTLIST"></spring:message> </label><br />
																<form:select path="lgd_LBSubDistrictSourceLatDCA"
																	class="frmtxtarea" id="ddLgdLBSubDistrictSourceLatDCA"
																	multiple="true">
		
																</form:select><br /> <br />
														</div>
														<div class="ms_buttons">
															<label><input type="button" class = "bttn"
																	value=" Whole &gt;&gt;" 
																	onclick="addItemSubDistFinalLocalBody('ddLgdLBSubDistrictDestLatDCA','ddLgdLBSubDistrictSourceLatDCA','FULL',true);" /></label>
																	<label> <input type="button"  class = "bttn" id="btnremoveOneULB"
																	name="Submit4" value="Back &lt;"
																	onclick="removeItemVillageList('ddLgdLBSubDistrictDestLatDCA','ddLgdLBSubDistrictSourceLatDCA',true)" /></label>
																	<label><input type="button"  class = "bttn" value=" Reset &lt;&lt;"
																	onclick="removeAllVillageList('ddLgdLBSubDistrictDestLatDCA', 'ddLgdLBSubDistrictSourceLatDCA', true)" /></label>
																	<label><input type="button"  class = "bttn" value="Part &gt;&gt;"
																	onclick="addItemSubDistFinalLocalBody('ddLgdLBSubDistrictDestLatDCA','ddLgdLBSubDistrictSourceLatDCA', 'PART',true);" /></label>
														</div>
														<div class="ms_selection">
															<label><spring:message htmlEscape="true" 
																		code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message>
															</label> <br /> <form:select name="select6"
																	id="ddLgdLBSubDistrictDestLatDCA" size="1"
																	multiple="true" path="lgd_LBSubDistrictDestLatDCA"
																	class="frmtxtarea">
																</form:select><br /> &nbsp;&nbsp;&nbsp; <label><input type="button" class = "bttn"
																value="<spring:message htmlEscape="true"  code="Button.GETVILLAGEL"/>"
																onclick="selectVillageAtDCAFinal();" /></label>
														</div>
													</div>
												</li>
												<li><div class="clear"></div></li>
												<li>	
													<%-- <span class="errormsg"
														id="ddLgdLBVillageDestLatDCA_error"><spring:message htmlEscape="true" 
																code="error.blank.VILLAGECAatDCA" /> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestLatDCA" class="errormsg"></form:errors>
													</span> --%>
													<div id="ddLgdLBVillageDestLatDCA_error" class="error"><spring:message code="error.blank.VILLAGECAatDCA" htmlEscape="true"></spring:message></div> 
													<div class="errormsg" id="ddLgdLBVillageDestLatDCA_error1"><form:errors path="lgd_LBVillageDestLatDCA" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageDestLatDCA_error2" style="display: none"></div>
													
												</li>
												<li>
													<div class="ms_container">
														<div class="ms_selectable">
															<label><spring:message htmlEscape="true" 
																		code="Label.AVAILVILLAGELIST"></spring:message> </label><br />
																<form:select path="lgd_LBVillageSourceLatDCA"
																	class="frmtxtarea" id="ddLgdLBVillageSourceLatDCA"
																	multiple="true">
		
																</form:select><br /> <br />
														 </div>
														 <div class="ms_buttons">
															<label><input type="button" class = "bttn" value=" Whole &gt;&gt;" 
																	onclick="addItemVillageFinalLocalBodyPan('ddLgdLBVillageDestLatDCA','ddLgdLBVillageSourceLatDCA','FULL',true);" />
																	</label> <label><input type="button" class = "bttn"  id="btnremoveOneULB"
																	name="Submit4" value="Back &lt;"
																	onclick="removeItemVillList('ddLgdLBVillageDestLatDCA','ddLgdLBVillageSourceLatDCA',true)" />
																	</label><label><input type="button" class = "bttn" value=" Reset &lt;&lt;"
																	onclick="removeAllVillList('ddLgdLBVillageDestLatDCA', 'ddLgdLBVillageSourceLatDCA', true)" />
																	</label><label><input type="button" class = "bttn" value="Part &gt;&gt;"
																	onclick="addItemVillageFinalLocalBodyPan('ddLgdLBVillageDestLatDCA','ddLgdLBVillageSourceLatDCA', 'PART',true);" />
															</label>
														</div>
														<div class="ms_selection">
															<label><spring:message htmlEscape="true" 
																		code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label> <br />
																<form:select name="select6" id="ddLgdLBVillageDestLatDCA"
																	size="1" multiple="true" path="lgd_LBVillageDestLatDCA"
																	class="frmtxtarea">
																</form:select><br />
														</div>
													</div>
												</li>
												<li><div class="clear"></div></li>
											
											  <!--  <tr>
													<td colspan="3" width="100%">
														<div id="getHeadQuartersD"></div>
													</td>
												</tr> -->
											</ul>

										</div>
									</li>
								
								<li>
								 <div class="frmpnlbghidden" style="visibility: hidden; display: none;">
								 
								<!--  	<div class="frmpnlbghidden">
								 --> 		<form:select
											id="ddLgdLBDistPDestListhidden" size="1" multiple="true"
											path="lgd_LBDistPDestListhidden" class="frmtxtarea">

									</form:select>
								</div>
								</li>
					
								<li>
									<div id='divLgdLBInterCArea' class="frmpnlbg" style="visibility: hidden; display: none;">
										<!-- <table class="tbl_no_brdr"> -->
												<!-- 	<tr>
													<td><b> <spring:message htmlEscape="true"  code="Label.AVAILABLE"></spring:message>
															&nbsp;${localGovtBodyForm.lgd_LBNomenclatureDist} &nbsp;<spring:message htmlEscape="true" 
																code="Label.LIST"></spring:message> </b><br /> <form:select
															path="lgd_LBDistListAtInterCA" style="width:175px"
															onchange="getInterPanchayatbyDcodeAtICA(this.value);"
															class="combofield" id="ddLgdLBDistListAtInterCA">
															<form:option value="0">
																<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
															</form:option>
															<form:options items="${districtPanchayatList}"
																itemLabel="localBodyNameEnglish"
																itemValue="localBodyCode" />
														</form:select></td>
												</tr> -->
											<ul class = "blocklist">
												<li>
												<!-- <tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td> -->
													
													<%-- <span class="errormsg"
														id="ddLgdLBInterPDestList_error"><spring:message htmlEscape="true" 
																code="error.blank.INTERPANCHAYT"></spring:message> </span>&nbsp;<span
														class="errormsg" id="ddLgdLBInterPDestList1_error"><spring:message htmlEscape="true" 
																code="error.select.INTERPANCHAYT"></spring:message> </span><span><form:errors htmlEscape="true" 
																path="lgd_LBInterPDestList" class="errormsg"></form:errors>
													</span> --%>
													<div id="ddLgdLBInterPDestList_error" class="error"><spring:message code="error.blank.DistrictPanchayt" htmlEscape="true"></spring:message></div> 
													<div id="ddLgdLBInterPDestList1_error" class="error"><spring:message code="error.DESTDISTLOCALBODY" htmlEscape="true"></spring:message></div> 
													<div class="errormsg" id="ddLgdLBInterPDestList1_error1"><form:errors path="lgd_LBInterPDestList" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBInterPDestList1_error2" style="display: none"></div>
													<div class="errormsg" id="ddLgdLBInterPDestList_error1"><form:errors path="lgd_LBInterPDestList" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBInterPDestList_error2" style="display: none"></div>
													
												</li>
												<li>
													<div class="ms_container">
														<div class="ms_selectable">
															<label><spring:message htmlEscape="true" 
																		code="Label.AVAILABLE"></spring:message></label>
																	&nbsp;<label id="interPanchAvail"></label>&nbsp;&nbsp;<label><spring:message htmlEscape="true" 
																		code="Label.LIST"></spring:message> </label><span
																class="errormsg">*</span><br /> <form:select
																	path="lgd_LBInterPSourceList" class="frmtxtarea"
																	id="ddLgdLBInterPSourceList"
																	multiple="true">
		
																</form:select><br /> <br />
														</div>
														<div class="ms_buttons">
															<label><input type="button" class = "bttn" value="<spring:message
																			code="Button.WHOLE"/>" 
																	onclick="addItemVillageFinalSFULL('ddLgdLBInterPDestList','ddLgdLBInterPSourceList','FULL',true);" /></label>
																	 <label><input type="button" class = "bttn" id="btnremoveOneULB"
																	name="Submit4" value="Back &lt;"
																	onclick="removeItemLocalBody('ddLgdLBInterPDestList','ddLgdLBInterPSourceList',true)" /></label>
																	<label><input type="button" class = "bttn" value=" Reset &lt;&lt;"
																	onclick="removeAllLocalBody('ddLgdLBInterPDestList', 'ddLgdLBInterPSourceList', true)" /></label>
																	<label><input type="button" class = "bttn" value="Part &gt;&gt;"
																	onclick="addItemVillageFinalSInter('ddLgdLBInterPDestList','ddLgdLBInterPSourceList', 'PART',true);" /></label>
														</div>		
														<div class="ms_selection">
															<label><spring:message htmlEscape="true" 
																		code="Label.CONTRIBUTE"></spring:message></label>
																	&nbsp;<label id="interPanchContri"></label>&nbsp;&nbsp;<label><spring:message htmlEscape="true" 
																		code="Label.LIST"></spring:message> </label><span
																class="errormsg">*</span> <br /> <form:select
																	name="select6" id="ddLgdLBInterPDestList" size="1"
																	multiple="true" path="lgd_LBInterPDestList"
																	class="frmtxtarea" >
																</form:select><br />&nbsp;&nbsp;&nbsp; <input type="button" class = "bttn"
																value="<spring:message htmlEscape="true"  code="Button.GETCOVEREDAREAOFLOCALBODY"/>"
																onclick="selectLocalBodyListAtICA();" />
														</div>
													</div>
												</li>
												<li><div class="clear"></div></li>

												<li>
													
													<%-- <span class="errormsg"
														id="ddLgdLBInterCAreaDestL_error"><spring:message htmlEscape="true" 
																code="error.blank.SUBDISTRICTCAatICA" /> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBInterCAreaDestList" class="errormsg"></form:errors>
													</span> --%>
													
													<div id="ddLgdLBInterCAreaDestL_error" class="error"><spring:message code="error.blank.SUBDISTRICTCAatICA" htmlEscape="true"></spring:message></div> 
													<div class="errormsg" id="ddLgdLBInterCAreaDestL_error1"><form:errors path="lgd_LBInterCAreaDestList" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBInterCAreaDestL_error2" style="display: none"></div>
													
												</li>


												<li>
													<div class="ms_container">
														<div class="ms_selectable">
															<label><spring:message htmlEscape="true" 
																		code="Label.AVAILSUBDISTRICTLIST"></spring:message> </label><br />
																<form:select path="lgd_LBInterCAreaSourceList"
																	class="frmtxtarea" id="ddLgdLBInterCAreaSourceL"
																	multiple="true">
		
																</form:select><br /> <br />
														</div>
														<div class="ms_buttons">
															<label><input type="button" class = "bttn"
																	value="<spring:message
																			code="Button.WHOLE"/>" 
																	onclick="addItemforSubDistrictforFULL('ddLgdLBInterCAreaDestL','ddLgdLBInterCAreaSourceL','ddLgdInterSubDestListhidden','FULL',true,'T');" /></label>
																	 <label><input type="button" class = "bttn" id="btnremoveOneULB"
																	name="Submit4" value="Back &lt;"
																	onclick="removeItemVillageListIP('ddLgdLBInterCAreaDestL','ddLgdLBInterCAreaSourceL',true)" /></label>
																	<label><input type="button" class = "bttn" value=" Reset &lt;&lt;"
																	
																	onclick="removeAllVillageListIP('ddLgdLBInterCAreaDestL', 'ddLgdLBInterCAreaSourceL', true)" /></label>
																	<label><input type="button" class = "bttn" value="Part &gt;&gt;"
																	onclick="addItemforSubDistrict('ddLgdLBInterCAreaDestL','ddLgdLBInterCAreaSourceL','ddLgdInterSubDestListhidden','PART',true,'T');" /></label>
														</div>

														<div class="ms_selection">
															<label><spring:message htmlEscape="true" 
																		code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message>
															</label> <br /> <form:select name="select6"
																	id="ddLgdLBInterCAreaDestL" size="1" multiple="true"
																	path="lgd_LBInterCAreaDestList" class="frmtxtarea">
																</form:select><br />
																&nbsp;&nbsp;&nbsp; <lable><input type="button" class = "bttn"
																value="<spring:message htmlEscape="true"  code="Button.GETVILLAGEL"/>"
															    onclick="selectVillageICAFinal();" /></lable>
													</div>
												</div>	
												</li>
												<li><div class="clear"></div></li>
												<li>
													<%-- <span class="errormsg"
														id="ddLgdLBVillageDestLatICA_error"><spring:message htmlEscape="true" 
																code="error.blank.VILLAGECAatICA" /> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestLatICA" class="errormsg"></form:errors>
													</span> --%>
													
													<div id="ddLgdLBVillageDestLatICA_error" class="error"><spring:message code="error.blank.VILLAGECAatICA" htmlEscape="true"></spring:message></div> 
													<div class="errormsg" id="ddLgdLBVillageDestLatICA_error1"><form:errors path="lgd_LBVillageDestLatICA" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageDestAtVillageCA_error2" style="display: none"></div>
													
												</li>

												<li>
													<div class="ms_container">
														<div class="ms_selectable">
															<label><spring:message htmlEscape="true" 
																		code="Label.AVAILVILLAGELIST"></spring:message> </label><br />
																<form:select path="lgd_LBVillageSourceLatICA"
																	class="frmtxtarea" id="ddLgdLBVillageSourceLatICA"
																	multiple="true">
		
																</form:select><br /> <br />
														</div>
												
													<div class="ms_buttons">
														<label><input type="button" class = "bttn"
															value=" Whole &gt;&gt;" 
															onclick="addItemVillageFinalLocalBodyPan('ddLgdLBVillageDestLatICA','ddLgdLBVillageSourceLatICA','FULL',true);" /></label>
															<label><input type="button" class = "bttn" id="btnremoveOneULB"
															name="Submit4" value="Back &lt;"
															onclick="removeItemVillList('ddLgdLBVillageDestLatICA','ddLgdLBVillageSourceLatICA',true)" /></label>
															<label><input type="button" class = "bttn" value=" Reset &lt;&lt;"
															onclick="removeAllVillList('ddLgdLBVillageDestLatICA','ddLgdLBVillageSourceLatICA',true)" /></label>
															<label><input type="button" class = "bttn" value="Part &gt;&gt;"
															onclick="addItemVillageFinalLocalBodyPan('ddLgdLBVillageDestLatICA','ddLgdLBVillageSourceLatICA', 'PART',true);" /></label>
													</div>	
													<div class="ms_selection">
														<label><spring:message htmlEscape="true" 
																	code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label> <br />
															<form:select name="select6" id="ddLgdLBVillageDestLatICA"
																size="1" multiple="true" path="lgd_LBVillageDestLatICA"
																class="frmtxtarea" >
															</form:select>
													</div>
												</div>
												</li>
												
												<li><div class="clear"></div></li>
												
												<!-- <tr>	
													<td colspan="3" width="100%">
														<div id="getHeadQuartersT"></div>
													</td>
												</tr> -->
											</ul>

										</div>
									</li>
								
								<li>
								<div class="frmpnlbgInterhidden" style="visibility: hidden; display: none;">
								<!-- 	<div class="frmpnlbgInterhidden">  -->
								 	<form:select
											id="ddLgdInterSubDestListhidden" size="1" multiple="true"
											path="lgd_LBInterSubDestListhidden" class="frmtxtarea">

									</form:select>
								</div>
							
								</li>
								<li>
								
									<!-- <td width="100%" colspan="2"> -->
										<div id='divLgdLBVillageCArea' class="frmpnlbg" style="visibility: hidden; display: none;">
											<ul class = "blocklist">
												<li>		
													<%-- <span class="errormsg"
														id="ddLgdLBVillageDestAtVillageCA_error"><spring:message htmlEscape="true" 
																code="error.blank.VILLAGEPANCHAYT"></spring:message> </span>&nbsp;<span
														class="errormsg" id="ddLgdLBVillageDestAtVillageCA1_error">
															<spring:message htmlEscape="true"  code="error.select.VILLAGEPANCHAYT"></spring:message>
													</span><span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestAtVillageCA" class="errormsg"></form:errors>
													</span> --%>
													
													<div id="ddLgdLBVillageDestAtVillageCA_error" class="error"><spring:message code="error.blank.DistrictPanchayt" htmlEscape="true"></spring:message></div> 
													<div id="ddLgdLBVillageDestAtVillageCA1_error" class="error"><spring:message code="error.DESTDISTLOCALBODY" htmlEscape="true"></spring:message></div> 
													<div class="errormsg" id="ddLgdLBVillageDestAtVillageCA1_error1"><form:errors path="lgd_LBVillageDestAtVillageCA" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageDestAtVillageCA1_error2" style="display: none"></div>
													<div class="errormsg" id="ddLgdLBVillageDestAtVillageCA_error1"><form:errors path="lgd_LBVillageDestAtVillageCA" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageDestAtVillageCA_error2" style="display: none"></div>
												</li>	
												<li>
													<div class="ms_container">
														<div class="ms_selectable">
															<label><spring:message htmlEscape="true" 
																			code="Label.AVAILABLE"></spring:message></label>
																		&nbsp;<label id="villagePanchAvail"></label>&nbsp;&nbsp;<label><spring:message htmlEscape="true"  code="Label.LIST"></spring:message>
																</label><span class="errormsg">*</span><br /> <form:select
																		path="lgd_LBVillageSourceAtVillageCA" class="frmtxtarea"
																		id="ddLgdLBVillageSourceAtVillageCA"
																		multiple="true">
			
																	</form:select><br /> <br />
														</div>
													<div class="ms_buttons">
														<label><input type="button" class = "bttn"
																value="<spring:message
																		code="Button.WHOLE"/>" 
																onclick="addItemVillageFinalFULL('ddLgdLBVillageDestAtVillageCA','ddLgdLBVillageSourceAtVillageCA','FULL',true);" /></label>
																
																
																 <label><input type="button" class = "bttn" id="btnremoveOneULB"
																name="Submit4" value="Back &lt;"
																onclick="removeItemLocalBody('ddLgdLBVillageDestAtVillageCA','ddLgdLBVillageSourceAtVillageCA',true)" /></label>
																<label><input type="button" class = "bttn" value=" Reset &lt;&lt;"
																onclick="removeAllLocalBody('ddLgdLBVillageDestAtVillageCA', 'ddLgdLBVillageSourceAtVillageCA', true)" /></label>
																<label><input type="button" class = "bttn" value="Part &gt;&gt;"
																onclick="addItemVillageFinalS('ddLgdLBVillageDestAtVillageCA','ddLgdLBVillageSourceAtVillageCA', 'PART',true);" /></label>
													</div>
													<div class="ms_selection">
														<label><spring:message htmlEscape="true" 
																	code="Label.CONTRIBUTE"></spring:message></label>
																&nbsp;<label id="villagePanchContri"></label>&nbsp;&nbsp;<label><spring:message htmlEscape="true"  code="Label.LIST"></spring:message>
														</label><span class="errormsg">*</span> <br /> <form:select
																name="select6" id="ddLgdLBVillageDestAtVillageCA"
																size="1" multiple="true"
																path="lgd_LBVillageDestAtVillageCA" class="frmtxtarea">
															</form:select><br /> &nbsp;&nbsp;&nbsp; <label></label><input type="button" class = "bttn"
															value="<spring:message htmlEscape="true"  code="Button.GETCOVEREDAREAOFLOCALBODY"/>"
															onclick="selectLocalBodyListAtVCA();" /></div>
													</div>
													</li>
													<li><div class="clear"></div></li>

													<li>
													<%-- <span class="errormsg"
														id="ddLgdLBVillageCAreaDestL_error"> <spring:message htmlEscape="true" 
																code="error.blank.VILLAGECAatICA" />
													</span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageCAreaDestL" class="errormsg"></form:errors>
													</span> --%>
													<div id="ddLgdLBVillageCAreaDestL_error" class="error"><spring:message code="error.blank.VILLAGECAatICA" htmlEscape="true"></spring:message></div> 
													<div class="errormsg" id="ddLgdLBVillageCAreaDestL_error1"><form:errors path="lgd_LBVillageCAreaDestL" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageCAreaDestL_error2" style="display: none"></div>
													
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
																<label><input type="button" class = "bttn"
																	value="<spring:message
																			code="Button.WHOLE"/>" 
																	onclick="addItemforVillageGFULL('ddLgdLBVillageCAreaDestL','ddLgdLBVillageCAreaSourceL','ddLgdInterVillageListhidden','FULL',true,'V');" /></label>
																	 <label><input type="button" class = "bttn" id="btnremoveOneULB"
																	name="Submit4" value="Back &lt;"
																	onclick="removeItemforVPanchayat('ddLgdLBVillageCAreaDestL','ddLgdLBVillageCAreaSourceL',true)" /></label>
																	<label><input type="button" class = "bttn" value=" Reset &lt;&lt;"
																	onclick="removeAllforVPanchayat('ddLgdLBVillageCAreaDestL', 'ddLgdLBVillageCAreaSourceL', true)" /></label>
																	<label><input type="button" class = "bttn" value="Part &gt;&gt;"
																	onclick="addItemforVillageG('ddLgdLBVillageCAreaDestL','ddLgdLBVillageCAreaSourceL','ddLgdInterVillageListhidden','PART',true,'V');" /></label>
															</div>
															<div class="ms_selection">
																<label><spring:message htmlEscape="true" 
																			code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label> <br />
																	<form:select name="select6" id="ddLgdLBVillageCAreaDestL"
																		size="1" multiple="true" path="lgd_LBVillageCAreaDestL"
																		class="frmtxtarea">
																	</form:select><br />
															</div>
														</div>
													</li>
													<li><div class="clear"></div></li>
												<!-- <tr>
													<td colspan="3" width="100%">
														<div id="getHeadQuartersV"></div>
													</td>
												</tr> -->

											</ul>

										</div>
									</li>
								
								<li>
								 <div class="frmpnlbgVillagehidden" style="visibility: hidden; display: none;">
								 <!-- 	<div class="frmpnlbgVillagehidden">   -->
								 	<form:select
											id="ddLgdInterVillageListhidden" size="1" multiple="true"
											path="lgd_LBInterVillageListhidden" class="frmtxtarea">

									</form:select>
								</div>
								</li>

								<li>
									<ul class = "blocklist">
										<li>
												<span class="errormsg"
													id="chkLgdLBUnmapped_error"></span>
												</li>
											<li>
												<form:checkbox value="true"
														onclick="getHideUnmappedList(document.getElementById('ddLgdLBType').value,this.checked);"
														id='chkLgdLBUnmapped' path="lgd_LBUnmappedCheck" /><label>
														<spring:message htmlEscape="true" 
															code="Label.SELCONTRIBUTEUNMAPPEDPARTIALLY"></spring:message>
													</label>		
												
													<%-- 	<span><form:errors htmlEscape="true" 
																path="lgd_LBUnmappedCheck" class="errormsg"></form:errors>
															</span>
												 	--%>
												 	<div class="errormsg" id="chkLgdLBUnmapped_error1"><form:errors path="lgd_LBUnmappedCheck" htmlEscape="true"/></div>  
													<div class="errormsg" id="chkLgdLBUnmapped_error2" style="display: none" ></div>
													
											</li>
										</ul>
									</li>
									<li>
										<div id='divLgdLBDistCAreaUnmapped' class="frmpnlbg" style="visibility: hidden; display: none;">
											<ul class = "blocklist">
												<li>
												<%-- 
												<span class="errormsg"
													id="ddLgdLBDistCAreaDestLUmapped_error"><spring:message htmlEscape="true" 
															code="error.DESTDISTLOCALBODY"></spring:message> </span>&nbsp;
													<%-- <span><form:errors htmlEscape="true" 
															path="lgd_LBDistCAreaDestListUmapped" class="errormsg"></form:errors>
													</span>
													 --%>
													 
													<div id="ddLgdLBDistCAreaDestLUmapped_error" class="error"><spring:message code="error.PSDT" htmlEscape="true"></spring:message></div> 
													<div class="errormsg" id="ddLgdLBDistCAreaDestLUmapped_error1"><form:errors path="lgd_LBDistCAreaDestListUmapped" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBSubDistrictDestLatDCAUmapped_error2" style="display: none" ></div>
													</li>
													<li>
														<div class="ms_container">
															<div class="ms_selectable">
																<label><spring:message htmlEscape="true" 
																			code="Label.AVAILDISTRICTLIST"></spring:message> </label><br />
																	<form:select path="lgd_LBDistCAreaSourceListUmapped"
																		class="frmtxtarea" id="ddLgdLBDistCAreaSourceLUmapped"
																		multiple="true">
			
																	</form:select><br /> <br />
															</div>		
															<div class="ms_buttons">
																<label><input type="button" class = "bttn"
																	value="<spring:message
																			code="Button.WHOLE"/>" 
																	onclick="addItemforLBDistrictFF('ddLgdLBDistCAreaDestLUmapped','ddLgdLBDistCAreaSourceLUmapped','ddLgdLBDistCAreaDestL','ddLgdLBDistPDestListhidden','FULL',true);" /></label>
																	 <label><input type="button" class = "bttn" id="btnremoveOneULB"
																	name="Submit4" value="Back &lt;"
																	onclick="removeItemSubdistrictListUnMapped('ddLgdLBDistCAreaDestLUmapped','ddLgdLBDistCAreaSourceLUmapped',true)" /></label>
																	<label><input type="button" class = "bttn" value=" Reset &lt;&lt;"
																	onclick="removeAllSubdistrictListUnMapped('ddLgdLBDistCAreaDestLUmapped', 'ddLgdLBDistCAreaSourceLUmapped', true)" /></label>
																	<label><input type="button" class = "bttn" value="Part &gt;&gt;"
																	onclick="addItemforLBDistrictFF('ddLgdLBDistCAreaDestLUmapped','ddLgdLBDistCAreaSourceLUmapped','ddLgdLBDistCAreaDestL','ddLgdLBDistPDestListhidden','PART',true);" /></label>
															</div>
															<div class="ms_selection">
																<label><spring:message htmlEscape="true" 
																			code="Label.CONTRIBUTDISTRICTLIST"></spring:message> </label><span class="errormsg">*</span><br />
																	<form:select name="select6"
																		id="ddLgdLBDistCAreaDestLUmapped" size="1"
																		multiple="true" path="lgd_LBDistCAreaDestListUmapped"
																		class="frmtxtarea" >
																	</form:select><br /> &nbsp;&nbsp;&nbsp; <input type="button" class = "bttn"
																	value="<spring:message htmlEscape="true"  code="Button.GETSUBDISTRICTL"/>"
																	onclick="refreshOptions('ddLgdLBSubDistrictSourceLatDCAUmapped');getUnmappedLBSDPListatUmappedFinal('T','${lbType}','D');" />
															</div>
														</div>
													</li>
												
													<li><div class="clear"></div></li>
												<li>
													
													<%--
														<span class="errormsg"
														id="ddLgdLBSubDistrictDestLatDCAUmapped_error"> </span>&nbsp;
													 	<span><form:errors htmlEscape="true" 
																path="lgd_LBSubDistrictDestLatDCAUmapped"
																class="errormsg"></form:errors> </span>
													--%>		
													<div class="errormsg" id="ddLgdLBSubDistrictDestLatDCAUmapped_error1"><form:errors path="lgd_LBSubDistrictDestLatDCAUmapped" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBSubDistrictDestLatDCAUmapped_error2" style="display: none" ></div>
													 	
												</li>
												<li>
													<div class="ms_container">
														<div class="ms_selectable">
															<label><spring:message htmlEscape="true" 
																		code="Label.AVAILSUBDISTRICTLIST"></spring:message> </label><br />
																<form:select path="lgd_LBSubDistrictSourceLatDCAUmapped"
																	class="frmtxtarea"
																	id="ddLgdLBSubDistrictSourceLatDCAUmapped"
																	multiple="true">
		
																</form:select><br /> <br />
														 </div>
														 <div class="ms_buttons">
															<label><input type="button" class = "bttn"
																value="<spring:message
																		code="Button.WHOLE"/>" 
																onclick="addItemforSubDistLB('ddLgdLBSubDistrictDestLatDCAUmapped','ddLgdLBSubDistrictSourceLatDCAUmapped','FULL',true);" />
																</label>
																<label><input type="button" class = "bttn" id="btnremoveOneULB"
																name="Submit4" value="Back &lt;"
																onclick="removeItem('ddLgdLBSubDistrictDestLatDCAUmapped','ddLgdLBSubDistrictSourceLatDCAUmapped',true)" />
																</label><label><input type="button" class = "bttn" value=" Reset &lt;&lt;"
																onclick="removeAll('ddLgdLBSubDistrictDestLatDCAUmapped', 'ddLgdLBSubDistrictSourceLatDCAUmapped', true)" />
																</label><label><input type="button" class = "bttn" value="Part &gt;&gt;"
																onclick="addItemforSubDistLB('ddLgdLBSubDistrictDestLatDCAUmapped','ddLgdLBSubDistrictSourceLatDCAUmapped', 'PART',true);" />
															</label>
														 </div>

														 <div class="ms_selection">
															<label><spring:message htmlEscape="true" 
																		code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message>
															</label> <br /> <form:select name="select6"
																	id="ddLgdLBSubDistrictDestLatDCAUmapped" size="1"
																	multiple="true" path="lgd_LBSubDistrictDestLatDCAUmapped"
																	class="frmtxtarea">
																</form:select><br /> &nbsp;&nbsp;&nbsp; <input type="button" class = "bttn"
																value="<spring:message htmlEscape="true"  code="Button.GETVILLAGEL"/>"
																onclick="refreshOptions('ddLgdLBVillageSourceLatDCAUmapped');getUnmappedLBVillPListatUmappedFinal('V','${lbType}','D');" />
														 </div>
													 </div>
													</li>
													
													<li><div class="clear"></div></li>
													<li>
													
													<%-- <span class="errormsg"
														id="ddLgdLBVillageDestLatDCAUmapped_error"></span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestLatDCAUmapped" class="errormsg"></form:errors>
													</span> --%>
													<div class="errormsg" id="ddLgdLBVillageDestLatDCAUmapped_error1"><form:errors path="lgd_LBVillageDestLatDCAUmapped" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageDestLatDCAUmapped_error2" style="display: none" ></div>
													</li>
												<li>
													<div class="ms_container">
														<div class="ms_selectable">
															<label><spring:message htmlEscape="true" 
																		code="Label.AVAILVILLAGELIST"></spring:message> </label><br />
																<form:select path="lgd_LBVillageSourceLatDCAUmapped"
																	class="frmtxtarea" id="ddLgdLBVillageSourceLatDCAUmapped"
																	multiple="true">
		
																</form:select><br /> <br />
														 </div>
													<div class="ms_buttons">
														<label><input type="button" class = "bttn"
															value="<spring:message
																	code="Button.WHOLE"/>" 
															onclick="addItemforVillageLBFinal('ddLgdLBVillageDestLatDCAUmapped','ddLgdLBVillageSourceLatDCAUmapped','FULL',true);" /></label>
															 <label><input type="button" class = "bttn" id="btnremoveOneULB"
															name="Submit4" value="Back &lt;"
															onclick="removeItemVillList('ddLgdLBVillageDestLatDCAUmapped','ddLgdLBVillageSourceLatDCAUmapped',true)" /></label>
															<label><input type="button" class = "bttn" value=" Reset &lt;&lt;"
															onclick="removeAllVillList('ddLgdLBVillageDestLatDCAUmapped','ddLgdLBVillageSourceLatDCAUmapped',true)" /></label>
															<label><input type="button" class = "bttn" value="Part &gt;&gt;"
															onclick="addItemforVillageLBFinal('ddLgdLBVillageDestLatDCAUmapped','ddLgdLBVillageSourceLatDCAUmapped', 'PART',true);" /></label>
													</div>

													<div class="ms_selection">
														<label><spring:message htmlEscape="true" 
																	code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label> <br />
															<form:select name="select6"
																id="ddLgdLBVillageDestLatDCAUmapped" size="1"
																multiple="multiple" path="lgd_LBVillageDestLatDCAUmapped"
																class="frmtxtarea">
															</form:select><br />
													</div>
												</div>
													</li>
													
													<li><div class="clear"></div></li>
											</ul>
										</div>
									</li>

									<li>
										<div id='divLgdLBInterCAreaUnmapped' class="frmpnlbg" style="visibility: hidden; display: none;">
											<ul class = "blocklist">
												<li>
													<%-- <span class="errormsg"
														id="ddLgdLBInterCAreaDestLUmapped_error"></span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBInterCAreaDestListUmapped" class="errormsg"></form:errors>
													</span> --%>
													<div id="ddLgdLBInterCAreaDestLUmapped_error" class="error"><spring:message code="error.PSSDT" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBInterCAreaDestLUmapped_error1"><form:errors path="lgd_LBInterCAreaDestListUmapped" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBInterCAreaDestLUmapped_error2" style="display: none" ></div>
													</li>


												<li>
													<div class="ms_container">
														<div class="ms_selectable">
															<label><spring:message htmlEscape="true" 
																		code="Label.AVAILSUBDISTRICTLIST"></spring:message> </label><br />
																<form:select path="lgd_LBInterCAreaSourceListUmapped"
																	class="frmtxtarea" id="ddLgdLBInterCAreaSourceLUmapped"
																	multiple="true">
		
																</form:select><br /> <br />
														 </div>
														 <div class="ms_buttons">
															<label><input type="button" class = "bttn"
																value="<spring:message
																		code="Button.WHOLE"/>" 
																onclick="addItemforLBSubDistrictFF('ddLgdLBInterCAreaDestLUmapped','ddLgdLBInterCAreaSourceLUmapped','ddLgdLBInterCAreaDestL','ddLgdInterSubDestListhidden','FULL',true);" /></label>
																 <label><input type="button" class = "bttn" id="btnremoveOneULB"
																name="Submit4" value="Back &lt;"
																onclick="removeItemVillageListUnMappedIP('ddLgdLBInterCAreaDestLUmapped','ddLgdLBInterCAreaSourceLUmapped',true)" /></label>
																<label><input type="button" class = "bttn" value=" Reset &lt;&lt;"
																onclick="removeAllVillageListUnMappedInterPanch('ddLgdLBInterCAreaDestLUmapped', 'ddLgdLBInterCAreaSourceLUmapped', true)" /></label>
																<label><input type="button" class = "bttn" value="Part &gt;&gt;"
																onclick="addItemforLBSubDistrictFF('ddLgdLBInterCAreaDestLUmapped','ddLgdLBInterCAreaSourceLUmapped','ddLgdLBInterCAreaDestL','ddLgdInterSubDestListhidden','PART',true);" /></label>
														 </div>

													<div class="ms_selection">
														<label><spring:message htmlEscape="true" 
																	code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message>
														</label><span class="errormsg">*</span><br /> <form:select name="select6"
																id="ddLgdLBInterCAreaDestLUmapped" size="1"
																multiple="true" path="lgd_LBInterCAreaDestListUmapped"
																class="frmtxtarea">
															</form:select><br /> &nbsp;&nbsp;&nbsp; <label><input type="button" class = "bttn"
															value="<spring:message htmlEscape="true"  code="Button.GETVILLAGEL"/>"
															onclick="refreshOptions('ddLgdLBVillageSourceLatICAUmapped');selectSubDistrictAtICAUmapped('V','${lbType}','I');" /></label>
													</div>
													</div>
													</li>
													<li> <div class="clear"></div></li>
													
													<li>
													<%-- <span class="errormsg"
														id="ddLgdLBVillageDestLatICAUmapped_error"> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestLatICAUmapped" class="errormsg"></form:errors>
													</span> --%>
													
													<div class="errormsg" id="ddLgdLBVillageDestLatICAUmapped_error1"><form:errors path="lgd_LBVillageDestLatICAUmapped" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageDestLatICAUmapped_error2" style="display: none" ></div>
													
													</li>

													<li>
														<div class="ms_container">
															<div class="ms_selectable">
																<label><spring:message htmlEscape="true" 
																			code="Label.AVAILVILLAGELIST"></spring:message> </label><br />
																	<form:select path="lgd_LBVillageSourceLatICAUmapped"
																		class="frmtxtarea" id="ddLgdLBVillageSourceLatICAUmapped"
																		multiple="true">
			
																	</form:select><br /> <br />
															 </div>
														<div class="ms_buttons">
															<label><input type="button" class = "bttn"
																value="<spring:message
																		code="Button.WHOLE"/>" 
																onclick="addItemforLBVillage('ddLgdLBVillageDestLatICAUmapped','ddLgdLBVillageSourceLatICAUmapped','FULL',true);" /></label>
																<label><input type="button" class = "bttn" id="btnremoveOneULB"
																name="Submit4" value="Back &lt;"
																onclick="removeItemVillList('ddLgdLBVillageDestLatICAUmapped','ddLgdLBVillageSourceLatICAUmapped',true)" /></label>
																<label><input type="button" class = "bttn" value=" Reset &lt;&lt;"
																onclick="removeAllVillList('ddLgdLBVillageDestLatICAUmapped','ddLgdLBVillageSourceLatICAUmapped',true)" /></label>
																<label><input type="button" class = "bttn" value="Part &gt;&gt;"
																onclick="addItemforLBVillage('ddLgdLBVillageDestLatICAUmapped','ddLgdLBVillageSourceLatICAUmapped', 'PART',true);" /></label>
														</div>

														<div class="ms_selection">
															<label><spring:message htmlEscape="true" 
																		code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label> <br />
																<form:select name="select6"
																	id="ddLgdLBVillageDestLatICAUmapped" size="1"
																	multiple="true" path="lgd_LBVillageDestLatICAUmapped"
																	class="frmtxtarea" >
																</form:select>
														</div>
													</div>
													</li>
													<li> <div class="clear"></div></li>
											</ul>

										</div>
									</li>
									<li>
										<div id='divLgdLBVillageCAreaUnmapped' class="frmpnlbg" style="visibility: hidden; display: none;">
											<ul class = "blocklist">
												<li>
												<%-- <span class="errormsg"
														id="ddLgdLBVillageCAreaDestLUnmapped_error"></span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageCAreaDestLUnmapped" class="errormsg"></form:errors>
													</span>
												 --%>
												 	<div id="ddLgdLBVillageCAreaDestLUnmapped_error" class="error"><spring:message code="error.PSCV" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBVillageCAreaDestLUnmapped_error1"><form:errors path="lgd_LBVillageCAreaDestLUnmapped" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageCAreaDestLUnmapped_error2" style="display: none" ></div>
												 	
												</li>
												<li>
													<div class="ms_container">
														<div class="ms_selectable">
															<label><spring:message htmlEscape="true" 
																		code="Label.AVAILVILLAGELIST"></spring:message> </label><br />
																<form:select path="lgd_LBVillageCAreaSourceLUnmapped"
																	class="frmtxtarea"
																	id="ddLgdLBVillageCAreaSourceLUnmapped"
																	items="${unmappedVillList}"
																	itemLabel="localBodyNameEnglish"
																	itemValue="landRegionCode"
																	multiple="true">
		
																</form:select><br /> <br />
														 </div>
														 <div class="ms_buttons">
															<label><input type="button" class = "bttn"
																value="<spring:message
																		code="Button.WHOLE"/>" 
																onclick="addItemforVillageLBFFinal('ddLgdLBVillageCAreaDestLUnmapped','ddLgdLBVillageCAreaSourceLUnmapped','ddLgdLBVillageCAreaDestL','ddLgdInterVillageListhidden','FULL',true);" /></label>
																 <label><input type="button" class = "bttn" id="btnremoveOneULB"
																name="Submit4" value="Back &lt;"
																onclick="removeItemVillUnmappedCreate('ddLgdLBVillageCAreaDestLUnmapped','ddLgdLBVillageCAreaSourceLUnmapped',true)" /></label>
																<label><input type="button" class = "bttn" value=" Reset &lt;&lt;"
																onclick="removeAllVillUnmapped('ddLgdLBVillageCAreaDestLUnmapped', 'ddLgdLBVillageCAreaSourceLUnmapped', true)" /></label>
																<label><input type="button" class = "bttn" value="Part &gt;&gt;"
																onclick="addItemforVillageLBFFinal('ddLgdLBVillageCAreaDestLUnmapped','ddLgdLBVillageCAreaSourceLUnmapped','ddLgdLBVillageCAreaDestL','ddLgdInterVillageListhidden','PART',true);" /></label>
														 </div>

														<div class="ms_selection">
															<label><spring:message htmlEscape="true" 
																		code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label><span class="errormsg">*</span><br />
																<form:select name="select6"
																	id="ddLgdLBVillageCAreaDestLUnmapped" size="1"
																	multiple="true" path="lgd_LBVillageCAreaDestLUnmapped"
																	class="frmtxtarea">
																</form:select><br />
														</div>
													</div>
													</li>
													<li> <div class="clear"></div></li>
											</ul>
										</div>
									</li>
								</ul>
							</div>
						
						
						
						<div id='divLgdLBCoveredArea' class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true" code="Label.HeaquarterFrame"></spring:message>
							</div>
							
							<!-- <table class="tbl_no_brdr" width="50%">
								<tr>
								 	<td colspan="3" width="100%"> -->
								 	<ul class = "blocklist">
								 		<li>
							    			<div id="getHeadQuartersD"></div>
							    			<div id="getHeadQuartersT"></div>
							    			<div id="getHeadQuartersV"></div>
						    			</li>
									</ul>
						</div>
						
						<div class="btnpnl">
								<form:hidden path="lbType"
												value="${localGovtBodyForm.lbType}"/>
										
							<input type="button" class = "bttn" name="Submit" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" onclick="return validateLocalbodyAllFinal();" />
						
						<!--  -->
						<c:if test="${fn:containsIgnoreCase(localGovtBodyForm.lbType,'P')}">
							<input type="button" class="bttn"
								name="Submit6"
								value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>"
								onclick="javascript:go('createLocalBodyforPRI.htm');" />
							
						</c:if>
						<c:if test="${fn:containsIgnoreCase(localGovtBodyForm.lbType,'T')}">
							<input type="button" class="bttn"
								name="Submit6"
								value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>"
								onclick="javascript:go('createLocalBodyforTraditional.htm');" />
							
						</c:if>
						<input type="button" class="bttn"
							name="Submit6"
							value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
							onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
						</div>
					</div>
				</div>
			</div>
			</form:form>
			<script src="/LGD/JavaScriptServlet"></script>
			
		</div>
	
	</div>

</body>
</html>