<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrStateFreezeService.js'></script>
<script type='text/javascript' language="javascript" src='<%=contextPath%>/dwr/interface/lgdDwrDesignationService.js'></script>
	<script type="text/javascript" language="javascript" src="<%=contextpthval%>/dwr/interface/lgdDwrLocalGovtBodyService.js"></script>
	<script type='text/javascript' language="javascript" src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);	
</script>
<title>Insert title here</title>
<script type="text/javascript">
function getDetails() {
	document.getElementById('DR').style.display = 'none';
	document.getElementById('TR').style.display = 'none';
	document.getElementById('VPAN').style.display = 'none';
	document.getElementById('IPAN').style.display = 'none';
	document.getElementById('IPANTRAD').style.display = 'none';
	document.getElementById('VPANTRAD').style.display = 'none';
	
	var stateCode = dwr.util.getValue('hdnStateCode');
	document.getElementById("errorentity").innerHTML = "";
	var e = document.getElementById('entity').value;
	var lbtype = e.split(":");
	var val = lbtype[0];
	var id1 = lbtype[0];
	var id2 = lbtype[1];
	var id3 = lbtype[2];
	var id4 = lbtype[3];
	var id5 = lbtype[4];
	var name = lbtype[5];
	if(id4=='P'){
		document.getElementById('DR').style.display = 'none';
		document.getElementById('TR').style.display = 'none';
		document.getElementById('VPAN').style.display = 'none';
		document.getElementById('IPAN').style.display = 'none';
		document.getElementById('IPANTRAD').style.display = 'none';
		document.getElementById('VPANTRAD').style.display = 'none';
		if (val == "2") {
			if(id5!=0){
				document.getElementById('IPAN').style.visibility = 'visible';
				document.getElementById('IPAN').style.display = 'block';
				getLocalBodyListbylblcCodeF(stateCode, id5);	
			}
		} else if (val == "3") {
			if(id5!=0){
				getLocalBodyListbylblcCodeF(stateCode, id5);	
			}
			
	
		}
	}
	else if(id4=='T'){
		
		document.getElementById('DR').style.display = 'none';
		document.getElementById('TR').style.display = 'none';
		document.getElementById('VPAN').style.display = 'none';
		document.getElementById('IPAN').style.display = 'none';
		document.getElementById('IPANTRAD').style.display = 'none';
		document.getElementById('VPANTRAD').style.display = 'none';
		if (val == "14") {
			if(id5!=0){
				document.getElementById('IPANTRAD').style.visibility = 'visible';
				document.getElementById('IPANTRAD').style.display = 'block';
				if(id5!=0){
					getLocalBodyListbylblcCodeFTrad(stateCode, id5);	
				}
			}
		} else if (val == "11") {
			if(id5!=0){
				getLocalBodyListbylblcCodeFTrad(stateCode, id5);	
			}
		}
		
	}
	
}



function getLocalBodyListbylblcCodeF(stateCode, lblc) {

		lgdDwrDesignationService.getLocalbodyDetailbyCode(stateCode, lblc, {
			callback : handleLocalbodylblcCodeSuccessF,
			errorHandler : handleLocalbodylblcCodeErrorF
		});
}

function handleLocalbodylblcCodeSuccessF(data) {
	var stateCode = dwr.util.getValue('hdnStateCode');

	var districtCode = dwr.util.getValue('districtCode');
	var e = document.getElementById('entity').value;
	var lbtype = e.split(":");
	var lgdLBType = dwr.util.getValue('entity');
	var temp = lgdLBType.split(":");
	var id1 = temp[0];
	var id2 = temp[1];

	var lblc = data[0].localBodyTypeCode;
	var plblc = data[0].parentTierSetupCode;
	var level = data[0].level;
	var name = data[0].nomenclatureEnglish;
	
	if (temp[2] != null && temp[2] == 'T') {
		document.getElementById("parent_level_hierarchy").value = level;
	}
	if (level == 'D') {
		document.getElementById("firstlevel").innerHTML = "Select  " + name;
		if (plblc == 0) {
			document.getElementById('IPAN').style.display = 'block';
			document.getElementById('IPAN').style.visibility = 'visible';
			if (districtCode == 0) {
				getdisVillagePanchayatListforVP(stateCode, lblc);
			}
		}
	}

	else if (level == 'I') {
		document.getElementById("secondlevel").innerHTML = "Select  " + name;
		document.getElementById('level').value = 'I';
		if (plblc == 0) {
			document.getElementById('VPAN').style.display = 'block';
			document.getElementById('VPAN').style.visibility = 'visible';
			getdisInterPanchayatListforIP(stateCode, lblc);
		}else {
			document.getElementById('VPAN').style.display = 'block';
			document.getElementById('VPAN').style.visibility = 'visible';
			getLocalBodyListbylblcCodeF(stateCode, plblc);
		}
	}
}
function getLocalBodyListbylblcCodeFTrad(stateCode, lblc) {

	lgdDwrDesignationService.getLocalbodyDetailbyCode(stateCode, lblc, {
		callback : handleLocalbodylblcCodeSuccessFTrad,
		errorHandler : handleLocalbodylblcCodeErrorF
	});
}

function handleLocalbodylblcCodeSuccessFTrad(data) {
		var stateCode = dwr.util.getValue('hdnStateCode');
		
		var districtCode = dwr.util.getValue('districtCode');
		var e = document.getElementById('entity').value;
		var lbtype = e.split(":");
		var lgdLBType = dwr.util.getValue('entity');
		var temp = lgdLBType.split(":");
		var id1 = temp[0];
		var id2 = temp[1];
		var lblc = data[0].localBodyTypeCode;
		var plblc = data[0].parentTierSetupCode;
		var level = data[0].level;
		var name = data[0].nomenclatureEnglish;
		if (level == 'D') {
			document.getElementById("firstleveltrad").innerHTML = "Select  " + name;
			if (plblc == 0) {
				document.getElementById('IPANTRAD').style.display = 'block';
				document.getElementById('IPANTRAD').style.visibility = 'visible';
				if (districtCode == 0) {
					getdisVillagePanchayatListforVPTrad(stateCode, lblc);
				}
			}
		}
		else if (level == 'I') {
			document.getElementById("secondleveltrad").innerHTML = "Select  " + name;
			document.getElementById('level').value = 'I';
			if (plblc == 0) {
				document.getElementById('VPANTRAD').style.display = 'block';
				document.getElementById('VPANTRAD').style.visibility = 'visible';
				getdisInterPanchayatListforIPTrad(stateCode, lblc);
			}else {
				document.getElementById('VPANTRAD').style.display = 'block';
				document.getElementById('VPANTRAD').style.visibility = 'visible';
				getLocalBodyListbylblcCodeFTrad(stateCode, plblc);
			}
}
}


function handleLocalbodylblcCodeErrorF() {
	// Show a popup message
	alert("No data found in local body by lblc!");
}


function getIntermediatePanchayatbyDcode(id) {
	var tier = document.getElementById('Tiertype').value;
	if (tier == 3)
		lgdDwrStateFreezeService.getchildlocalbodiesByParentcodeold(id, {
			callback : IntermediatePanchayatSuccessforVP,
			errorHandler : handleIntermediatePanchayatError
		});
}

function IntermediatePanchayatSuccessforVP(data) {
	var fieldId = 'vilpanchayat';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('vilpanchayat');
	st.add(new Option('Select Intermediate Panchayat', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}
function getIntermediatePanchayatbyDcodeTrad(id) {
	var tier = document.getElementById('Tiertype').value;
	if (tier == 3)
		lgdDwrStateFreezeService.getchildlocalbodiesByParentcodeold(id, {
			callback : IntermediatePanchayatSuccessforVPTrad,
			errorHandler : handleIntermediatePanchayatError
		});
}

function IntermediatePanchayatSuccessforVPTrad(data) {
	var fieldId = 'vilpanchayattrad';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('vilpanchayattrad');
	st.add(new Option('Select  Block Advisory Committee', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleIntermediatePanchayatError() {
	alert("No data found!");
}

function validateAllforDistrict() {
	var flag = true;
	var tier = document.getElementById('Tiertype').value;
	var e = document.getElementById('entity').value;
	var lbtype = e.split(":");
	var val = lbtype[0];
	var id1 = lbtype[0];
	var id2 = lbtype[1];
	var id3 = lbtype[2];
	var id4 = lbtype[3];
	var id5 = lbtype[4];
	
	if (e == "" || e == "0") {
		document.getElementById("errorentity").innerHTML = "<font color='red'><br>Select One of Local Body Type</font>";
		document.getElementById("entity").focus();
		flag = false;
	}

	else if (e == "villageDistUser") {
		if (document.getElementById('ddSubdistrict').value == "" || document.getElementById('ddSubdistrict').value == "0") {
			document.getElementById("errorsubdistrict").innerHTML = "<font color='red'><br>Select One of Subdistrict<spring:message code='error.select.DISTRICTFRMDROPDWN'/></font>";
			document.getElementById("ddSubdistrict").focus();
			flag = false;
		}
	} else {
		lbtype = e.split(":");
		val = lbtype[0];
		if(id5!=0){
			if (val == 2) {
				var district= $('#dispanchayat').val();
				if (document.getElementById('dispanchayat').value == "" || document.getElementById('dispanchayat').value == "0" || document.getElementById('dispanchayat').value == "undefined") {
					alert("Please Select District Panchayat");
					flag = false;
				}
			} else if (val == 3) {
				if(tier==3){
						if (document.getElementById('dispanchayat').value == "" || document.getElementById('dispanchayat').value == "0") {
							alert("Please Select District Panchayat");
							flag = false;
						} else if (document.getElementById('vilpanchayat').value == ""
								|| document.getElementById('vilpanchayat').value == "0") {
							alert("Please Select Intermediate Panchayat");
							flag = false;
						}
					}else if(tier==2){ 
						if ((document.getElementById('dispanchayat').value == "" || document.getElementById('dispanchayat').value == "0") &&
								 (document.getElementById('vilpanchayat').value == ""|| document.getElementById('vilpanchayat').value == "0")) {
							alert("Please Select One of the Panchayat");
							flag = false;
						}
					}
			}else if(val==14){
						if (document.getElementById('dispanchayattrad').value == "" || document.getElementById('dispanchayattrad').value == "0" || document.getElementById('dispanchayattrad').value == "undefined") {
							alert("Please Select District Council");
							flag = false;
						}
			}else if(val==11){
				if(tier==3){		
				if (document.getElementById('dispanchayattrad').value == "" || document.getElementById('dispanchayattrad').value == "0") {
							alert("Please Select District Council");
							flag = false;
						} else if (document.getElementById('vilpanchayattrad').value == ""
								|| document.getElementById('vilpanchayattrad').value == "0") {
							alert("Please Select Block Advisory Commitee");
							flag = false;
						}
				}else if(tier==2){		
					if ((document.getElementById('dispanchayattrad').value == "" || document.getElementById('dispanchayattrad').value == "0")
								&& (document.getElementById('vilpanchayattrad').value == ""	|| document.getElementById('vilpanchayattrad').value == "0")) {
							alert("Please Select One of the Council");
							flag = false;
						}
					}
			}
		}
	}
	return flag;
}

function getdisVillagePanchayatListforVP(id1, id2) {
	lgdDwrlocalBodyService.getPanchayatListbylblcCode(id1, id2, {
		callback : handledisVillagePanchayatSuccessforVP,
		errorHandler : handledisVillagePanchayatErrorforVP
	});
}



function handledisVillagePanchayatSuccessforVP(data) {
	var fieldId = 'dispanchayat';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('dispanchayat');
	st.add(new Option('Select', '0'));
	var options1 = $("#dispanchayat");
	$.each(data, function(key, obj) {
		var option1 = $("<option/>");
		
			$(option1).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options1.append(option1);
		
	});
	
}


function getdisVillagePanchayatListforVPTrad(id1, id2) {
	lgdDwrlocalBodyService.getPanchayatListbylblcCode(id1, id2, {
		callback : handledisVillagePanchayatSuccessforVPTrad,
		errorHandler : handledisVillagePanchayatErrorforVP
	});
}



function handledisVillagePanchayatSuccessforVPTrad(data) {
	var fieldId = 'dispanchayattrad';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('dispanchayattrad');
	st.add(new Option('Select', '0'));
	var options1 = $("#dispanchayattrad");
	$.each(data, function(key, obj) {
		var option1 = $("<option/>");
		
			$(option1).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options1.append(option1);
		
	});
	
}

function handledisVillagePanchayatErrorforVP() {
	// Show a popup message
	alert("No data found in District Panchayat!");
}

function getdisInterPanchayatListforIP(id1, id2) {
	// alert("inter"+id1+":"+id2);
	lgdDwrlocalBodyService.getPanchayatListbylblcCode(id1, id2, {
		callback : handledisInterPanchayatSuccessforIP,
		errorHandler : handledisInterPanchayatErrorforIP
	});
}

function handledisInterPanchayatSuccessforIP(data) {
	var fieldId = 'vilpanchayat';
	
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	
	var st = document.getElementById('vilpanchayat');
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
	
}



function getdisInterPanchayatListforIPTrad(id1, id2) {
	// alert("inter"+id1+":"+id2);
	lgdDwrlocalBodyService.getPanchayatListbylblcCode(id1, id2, {
		callback : handledisInterPanchayatSuccessforIPTrad,
		errorHandler : handledisInterPanchayatErrorforIP
	});
}

function handledisInterPanchayatSuccessforIPTrad(data) {
	var fieldId = 'vilpanchayattrad';
	
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	
	var st = document.getElementById('vilpanchayattrad');
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
	
}
function handledisInterPanchayatErrorforIP() {
	// Show a popup message
	alert("No data found in District Panchayat!");
}


</script>
</head>
		<body>
		<div class="form_stylings">
			<div class="header">
				<h3><spring:message code="Label.LocalBodyFreeze/Unfreeze" text="Freeze/Unfreeze Status List(${districtName})."/></h3>
				<ul class="item_list">
					<%--//this link is not working <li>
						<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message> </a>
					</li> --%>
					<%--//this link is not working <li>
						<a href="viewManualHelps.do?formName=${formId}&<csrf:token uri='viewManualHelps.do'/>" class="frmhelp" onclick="centeredPopup(this.href,'myWindow','yes');return false">Help</a>
					</li> --%>
				</ul>
			</div>
			<div class="page_content">
				<div class="form_container">
				<form:form commandName="localBodyFreeze" method="POST" action="districtLocalBodyFreezePost.htm" htmlEscape="true">
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="districtLocalBodyFreezePost.htm"/>"/>
					<input type="hidden" name="Tiertype" id="Tiertype" value="<c:out value='${Tiertype}' escapeXml='true'></c:out>" />
					<input type='hidden' id="hdnStateCode" value="<c:out value='${stateCode}' escapeXml='true'></c:out>" />
					<input type='hidden' id="stateCode" value="<c:out value='${stateCode}' escapeXml='true'></c:out>" />
					<input type='hidden' name="districtCode" value="<c:out value='${districtCode}' escapeXml='true'></c:out>" />
					<input type="hidden" name="parent_level_hierarchy" id="parent_level_hierarchy"></input>
					<input type="hidden" id="level" />
					<div class="form_block">
						<div class="col_1">
							<ul class="form_body">
								<li>
										<label for="entity"><spring:message text="Select Local Body Type" code="Label.LBType" htmlEscape="true"></spring:message><font color="red">*</font></label>									
											<form:select path="entityName" id="entity" onchange="getDetails();" htmlEscape="true">
												<form:option value="" htmlEscape="true">
													<esapi:encodeForHTMLAttribute><spring:message code="Label.SEL" htmlEscape="true"></spring:message></esapi:encodeForHTMLAttribute>
												</form:option>
											<form:options items="${getLocalGovtSetupList}" itemLabel="localBodyTypeName"  itemValue="nomenclatureEnglish" htmlEscape="true"/>
										</form:select>				
									    <div id="errorentity"></div>
								    </li>
											
									<li>
									 	<div id="DR" style="display:none">
									 		<label for="ddDistrict"> <spring:message code="Label.DISTRICT" htmlEscape="true"></spring:message> <font color="red">*</font></label><br>								
											    <form:select path="districtCode" id="ddDistrict" htmlEscape="true">
													<form:option value="" htmlEscape="true"><esapi:encodeForHTMLAttribute>Select District</esapi:encodeForHTMLAttribute></form:option>
													<form:options items="${districtList}" itemLabel="districtNameEnglish" itemValue="districtPK.districtCode" htmlEscape="true"/>
												</form:select> 
												<div id="errordistrict"></div>
										</div>
							        </li>
								<li id="IPAN" style="display:none">
											<label for="dispanchayat" style="display: inline-flex;"> 
								 				<label></label><label id="firstlevel"></label>
																												
															<font color="red">*</font><br/> 
								 			</label><br/>
											 <form:select path="districtPanchyat" id="dispanchayat" onchange="getIntermediatePanchayatbyDcode(this.value);remove_error1()" htmlEscape="true">
												<form:option value=""><esapi:encodeForHTMLAttribute><spring:message code="Label.SELECTDISTICTPANCHAYAT" htmlEscape="true"/></esapi:encodeForHTMLAttribute></form:option>
												<form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" htmlEscape="true"/>
											</form:select> 
										<div id="errordistrict"></div>
										
							 	</li>
							 	<li id="IPANTRAD" style="display:none">
							 	<label for="dispanchayattrad" style="display: inline-flex;"> 
								 				<label id="firstleveltrad"></label>
																												
															<font color="red">*</font>
								 			</label><br/>
											 <form:select path="districtPanchyattrad" id="dispanchayattrad" onchange="getIntermediatePanchayatbyDcodeTrad(this.value);remove_error1()" htmlEscape="true">
												<form:option value="" htmlEscape="true"><esapi:encodeForHTMLAttribute><label id="firstlevel"></label></esapi:encodeForHTMLAttribute></form:option>
												<form:options items="${districtPanchayatListTrad}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" htmlEscape="true"/>
											</form:select> 
										<div id="errordistrict"></div>
							 	</li>
								 <li>
									<div id="VPAN" style="display:none">
										 <ul class="blocklist" >
												<li id="inter">
														<label for="vilpanchayat" style="display: inline-flex;"><label id="secondlevel"></label><font color="red">*</font></label><br>
															<form:select path="intermediatePanchyat" id="vilpanchayat"   onchange="remove_error2();" htmlEscape="true">
															</form:select> 
														<div id="errorsubdistrict"></div>
											 	</li>
										 </ul>
									</div>
								</li>
								<li>
									<div id="VPANTRAD" style="display:none">
										 <ul class="blocklist">
												<li id="intertrad">
														<label for="vilpanchayattrad" style="display: inline-flex;"><label id="secondleveltrad"></label><font color="red">*</font></label><br>
															<form:select path="intermediatePanchyattrad" id="vilpanchayattrad" onchange="remove_error2();" htmlEscape="true">
															</form:select> 
														<div id="errorsubdistrict"></div>
											 	</li>
										 </ul>
									</div>
								</li>
								<li>
									<div id="TR" style="display:none">
										 <ul class="blocklist">
											<li>	
												<label for="ddDistrict2"><spring:message code="Label.DISTRICT" htmlEscape="true"></spring:message><font color="red">*</font></label><br>
													 <form:select path="districtCode" id="ddDistrict2"   onchange="getSubDistrictList(this.value);" onfocus="validateOnFocus('ddDistrict2');" onblur="vlidateOnblur('ddDistrict2','1','15','c');" htmlEscape="true">
														<form:option value="" htmlEscape="true"><esapi:encodeForHTMLAttribute>Select District</esapi:encodeForHTMLAttribute></form:option>
														<form:options items="${districtList}" itemLabel="districtNameEnglish" itemValue="districtPK.districtCode" htmlEscape="true"/>
													</form:select> 
													<div id="errordistrict2"></div>
												</li>
												<li>
													<label for="ddSubdistrict"><spring:message	code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message><font color="red">*</font> </label><br>
														<form:select path="subdistrictCodes" id="ddSubdistrict"  onchange="remove_error2();"	onfocus="validateOnFocus('ddSubdistrict');" onblur="vlidateOnblur('ddSubdistrict','1','15','c');" htmlEscape="true">
														</form:select>
													<div id="errorsubdistrict"></div>
												</li>
											</ul>
								 	</div>
								</li>
							</ul>
						</div>
					</div><br></br>
							<input type="submit" id="createhabitation" name="submmit" value="Get Data" onclick="return validateAllforDistrict();"></input>
							<input type="button" id="close" name="close" value="Close" onclick="javascript:location.href='home.htm'" ></input>
					</form:form>
				</div>
			</div>
		</div>
	</body>
</html>