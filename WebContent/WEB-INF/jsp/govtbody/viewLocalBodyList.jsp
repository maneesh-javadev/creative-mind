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
	<script src="js/successMessage.js"></script>
	<script src="js/helpMessage.js"></script>
	<script src="js/trim-jquery.js"></script>
	<script src="js/validation.js"></script>
	<script src="js/lgd_localbody.js"></script>
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
		$('#gtadp1').hide();
		$('#gtachildtr').hide();
		$('#gtaIntermediate').hide();
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
				  			{ "bSortable": false },
				  			</c:if>
							],
			"sPaginationType": "full_numbers"
		});
	} );
	
/* function open_win() {
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
 */
function closePage()
{	
	document.form1.method = "post";
	document.form1.action = "home.htm";
	document.form1.submit(); 
}


 function managePriDetail(url,id,type,operationState) {
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
 
 
  function RedirectFunction(value){
	  	var stateCode = dwr.util.getValue('hdnStateCode');
 		if(stateCode==19){
		$('#gtadp1').show();
		document.getElementById("tr_district1").style.display = 'none';
	 	var stateCode = dwr.util.getValue('hdnStateCode');
	 	lgdDwrlocalBodyService.getStateTopHierarchy(stateCode, {
	 			callback : getStateWbTopHierarchySuccess,
	 			errorHandler : handlegetStateWbTopHierarchyError
	 		});
 	} 
  }

 
 
 function redirectNornmalFlow(value) {
	 var stateCode = dwr.util.getValue('hdnStateCode');
	 if(stateCode==19){
	 
	 if (value == 1) { //DISTRICT PANCHYAT
		var id = $('#ddLgdLBType').val();
		var dis = '<c:out value="${districtCode}" escapeXml="true"></c:out>';
		var type='<c:out value="${lbType}" escapeXml="true"></c:out>';
		
		var id =$('#tierSetupCode').val();
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];
		var id3 = temp[2];
		var id5 = temp[4];
		
		
		if(stateCode == 19 && id2=='V'){
			$('#tr_intermediate1').show();
			$('#tr_district1').show();
		}
		
		if(stateCode == 19 && id2=='I'){
			$('#tr_district1').show();
			$('#tr_intermediate1').hide();
			$('#gtaIntermediate').hide();
			
		}
		
		$('#gtachildtr').hide();
		$('#gtaIntermediate').hide();
		//document.getElementById("tr_district1").style.display = 'block';
	} else {
		document.getElementById("tr_intermediate1").style.display = 'none';
		document.getElementById("tr_district1").style.display = 'none';
		$('#villagepan').hide();
		$('#gtachildtr').show();
		lgdDwrlocalBodyService.getPanchayatListbylblcCode(stateCode, value, {
			callback : getParentLbSuccess,
			errorHandler : handledisPanchayatError
		});
	}
	 }

 } 
 
 
/* gta intermeidate   */
 
 function getGtaIntermediateDataPost(getGtaInterPanch) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(getGtaInterPanch, {
			callback : handleInterGtaSuccess,
			errorHandler : handleInterGtaError
		});
	
}
 
 
 function getGtaIntermediateData(value) {
		var id =$('#tierSetupCode').val();
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];
		var id3 = temp[2];
		var id5 = temp[4];
		var e1 = document.getElementById("gtachild");
		var strUser = e1.options[e1.selectedIndex].value;
		var statevalue = dwr.util.getValue('hdnStateCode');
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
	
	
	
	/* function hideshow(id){
	 var stateCode = dwr.util.getValue('hdnStateCode');
	 if(stateCode==19){
		$('#tr_intermediate1').hide();
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];
		var id3 = temp[2];
		var id4 = temp[3];
		var id5 = temp[4];
		var stateCode = dwr.util.getValue('hdnStateCode');
		if(id2 =='V' || id2 =='I'){
			$('#tr_intermediate1').show();
			$('#gtadp1').show();
			//$('#tr_district1').show();  //Select Zilla Panchayat 
		}else{
			$('#gtadp1').hide();
			$('#gtachildtr').hide();
			$('#gtaIntermediate').hide();
		}
		}
	 
 } */
 
 
 
 function hideshow(id){
	 var stateCode = dwr.util.getValue('hdnStateCode');
	 if(stateCode==19){
		$('#tr_intermediate1').hide();
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];
		var id3 = temp[2];
		var id4 = temp[3];
		var id5 = temp[4];
		var stateCode = dwr.util.getValue('hdnStateCode');
		if(id2 =='D'){
			$('#gtadp1').hide();
			$('#gtachildtr').hide();
			$('#gtaIntermediate').hide();
		}
		}
	 
 }
 
 
 
/* 	function clearLBLists() {
		$('#ddLgdLBVillageSourceAtVillageCA').empty();
		$('#ddLgdLBVillageDestAtVillageCA').empty();
		$('#ddLgdLBVillageCAreaSourceL').empty();
		$('#ddLgdLBVillageCAreaDestL').empty();
		$('#chkLgdLBExistChk').prop('checked', false);

	} */
 
 

window.onload=loadviewLBPage; 
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
			
					<h3 class="subtitle"><spring:message code="Label.VIEWLOCALGOVTBODY" htmlEscape="true"></spring:message>
					</h3>
					<ul class="listing">
					<li>
					<a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a>
					</li>
					<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
					</li>
					<li><a href="#" class="frmhelp"><spring:message
								code="Label.HELP" htmlEscape="true"></spring:message> </a>
					</li>			 --%>
				</ul>
			
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			
			<form:form action="modifyLocalBodyforPRI.htm" method="POST" id="form1" onsubmit="cursorwait();" name="form1" commandName="localGovtBodyForm">

				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyLocalBodyforPRI.htm"/>" />

				<input type='hidden' name=hdnType id="hdnType" value="<c:out value='${localGovtBodyForm.hdnType}' escapeXml='true'></c:out>" />
					<!-- gta value on post to show	  -->
				<input type='hidden' name="gtadppost" id="gtadppost" value="<c:out value='${gtadpPost}' escapeXml='true'></c:out>"/>
				<input type='hidden' name="getGtaList" id="getGtaList" value="<c:out value='${getGtaListPost}' escapeXml='true'></c:out>"/>
				<input type='hidden' name="getGtaInterPanch" id="getGtaInterPanch" value="<c:out value='${getGtaInterPanchPost}' escapeXml='true'></c:out>"/>
				<input type='hidden' name=hdnIntermediatePanchayat id="hdnIntermediatePanchayat" value="<c:out value='${hdnIntermediatePanchayat}' escapeXml='true'></c:out>"/>
				<input type='hidden' name=hdnDistrictPanchayat id="hdnDistrictPanchayat" value="<c:out value='${hdnDistrictPanchayat}' escapeXml='true'></c:out>"/>
				<input type='hidden' name="districtCode" value="<c:out value='${districtCode}' escapeXml='true'></c:out>"/>
				<input type='hidden' name="lbType" id="lbType" value="<c:out value='${lbType}' escapeXml='true'></c:out>"/>
				<input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("stateCode")%>' />
				<input type="hidden"  id="lbTypeCode" value="<c:out value='${lbTypeCode}' escapeXml='true'></c:out>" /> <!--  a form is added for getting and setting the Lgd_LBTypeName -->
				<form:hidden path="lbtypeLevel" id="lbtypeLevel" value="${localGovtBodyForm.lbtypeLevel}"/>
				<form:hidden path="districtCode" id="districtCode" value="${localGovtBodyForm.districtCode}"/>
				<form:hidden path="hiddenLevel" id="hiddenLevel" value="${localGovtBodyForm.hiddenLevel}"/>
				<form:hidden path="parentLB" id="parentLB" value="${localGovtBodyForm.parentLB}"/>
				
				<div id="cat">
					<div class="frmpnlbg">
						<!-- For Localbody list for different district, Intermediate, Village Panchayat  -->
						<div id='district' class="frmpnlbg">
							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message code="Label.SEARCHCRIT" htmlEscape="true"></spring:message>
								</div>
								
								        <div  >
										<ul class="blocklist" >
										<li>
										
										<label><spring:message code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message>
										</label><span class="errormsg">*</span><br />
										<form:select path="lgd_LBTypeName" id="tierSetupCode" name="tierSetupCode" class="combofield" style="width: 175px" onchange="hideShowDistIVMunici(this.value,'${districtCode}','${lbType}');RedirectFunction(this.value);hideshow(this.value);"
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
										 <form:hidden path="lgd_lbCategory"
												id="hiddenCheckBox" class="frmfield"
												value="${localGovtBodyForm.lgd_lbCategory}"/>
												
										</li>
										<li>		
										<div id="gtadp1">
														
																<input type="hidden" id="gtaanddp" />
																	<input type="hidden" id="gtadp2" /> <input type="hidden"name="flagCode" id="flagCode" /> <input type="hidden" name="" id="lblc" /> <label><spring:message code="Label.SelectParentLocalbodytype" text="Select Parent Local body type" htmlEscape="true">
																		</spring:message></label><span class="errormsg">*</span><br /> <label>
																		 <form:select path="parentList" id="gtadp" style="width: 175px" class="combofield" htmlEscape="true" onfocus=";helpMessage(this,'ddLgdLBTypeMsg');" onchange="redirectNornmalFlow(this.value);clearLBLists();" onkeydown="" onblur="hideHelp();"  >
																			<form:option selected="selected" value="" label="--select--"></form:option>
																		 </form:select>
																		<div id="errParentType" style="color: red;display: none;"><spring:message text="Select parent type" htmlEscape="true"></spring:message></div>
																</label> &nbsp;&nbsp;
													
											</div>
											
											</li>
											<li>
												<!-- gta panchyat list	  -->
													<div id="gtachildtr">
															
																	<input type="hidden" id="gtaanddp1" />
																		<input type="hidden" id="gtadp2" /> <input type="hidden"name="flagCode" id="flagCode" /> <input type="hidden" name="" id="lblc" /> <label><spring:message code="Label.SELECTGTA" text="Gorkhaland Territorial Administration" htmlEscape="true">
																			</spring:message></label><span class="errormsg">*</span><br /> <label>
																			 <form:select path="gtaList"  id="gtachild" htmlEscape="true" class="combofield" onfocus=";helpMessage(this,'ddLgdLBTypeMsg');" onchange="getGtaIntermediateData(this.value);" onkeydown="" onblur="hideHelp();" style="width: 175px">
																				<form:option value=""><form:option selected="selected" value="" label="--select--" /></form:option>
																				</form:select>
																			<div id="errSelectGta" style="color: red;display: none;"><spring:message text="Kindly add the Gta Child List" htmlEscape="true"></spring:message></div>
																	</label> &nbsp;&nbsp;
																	
															
														
													</div>
												</li>	
												
											<!-- gta panchyat list	  -->
															
											<!-- gta Intermediate panchyat list	  -->
											<li>
											<div id="gtaIntermediate">
												
														<input type="hidden" id="gtaIntermediate1" />
															<input type="hidden" id="gtaIntermediate2" /> <input type="hidden"name="flagCode" id="flagCode" /> <input type="hidden" name="" id="lblc" /> <label><spring:message code="Label.SelectintermediatePanchayat" text="Select Intermediate Panchayat" htmlEscape="true">
																</spring:message></label><span class="errormsg">*</span><br /> <label>
																 <form:select path="GtaInterPanch"  id="gtaIntermediateid" htmlEscape="true" class="combofield" onfocus=";helpMessage(this,'ddLgdLBTypeMsg');" onchange="" onkeydown="" onblur="hideHelp();" style="width: 175px">
																	<form:option value=""><form:option selected="selected" value="" label="--select--" /></form:option>
																	</form:select>
																<div id="errSelectGtaInter" style="color: red;display: none;"><spring:message text="Kindly add the GTA Child List" htmlEscape="true"></spring:message></div>
														</label> &nbsp;&nbsp;
														
											</div>
											</li>
															
										<!-- gta Intermediate panchyat list	  -->	
											
											

											<li>		
											<div id="tr_district1" style="display:none">
												
													<c:if
														test="${! empty localGovtBodyForm.lgd_LBNomenclatureDist}">
														
															<label><spring:message 	code="Label.SELECT" htmlEscape="true"></spring:message>&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out></label>
															<span class="errormsg">*</span><br />
															<form:select path="localBodyNameEnglishList" class="combofield" id="ddSourceLocalBody" style="width: 175px" onchange="getLocalBodySubDistrictPanListF(this.value)">
																	<form:option value="0"><spring:message code="Label.SELECTLOCALBODY" htmlEscape="true"></spring:message></form:option>
																	<%-- <form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" /> --%>
																	<c:forEach items="${districtPanchayatList}" var="districtPanchayatListDet">
																	 <c:if test="${(districtPanchayatListDet.operation_state) eq 'F'.charAt(0)}">
																		<form:option value="${districtPanchayatListDet.localBodyCode}" disabled="true"><c:out value="${districtPanchayatListDet.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>		 																		 
																	 </c:if>
																	 <c:if test="${districtPanchayatListDet.operation_state eq 'A'.charAt(0)}">
																		<form:option value="${districtPanchayatListDet.localBodyCode}"><c:out value="${districtPanchayatListDet.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>		 																		 
																	 </c:if>
																</c:forEach>
															</form:select>
															&nbsp;<span>
																<form:errors htmlEscape="true"
																		path="localBodyNameEnglishList" class="errormsg"></form:errors>
															</span> &nbsp;&nbsp;<span class="errormsg"
																id="ddSourceLocalBody_error"> <spring:message
																		code="error.SOURCESELECTLOCALBODYPARENT" htmlEscape="true"></spring:message>
															</span> <br /> 
															
														
													</c:if>
													
												
											</div>
											</li>
											<li>
											<div id="tr_intermediate1" style="display:none">

												
													<c:if
														test="${! empty localGovtBodyForm.lgd_LBNomenclatureInter}">
														
															<label><spring:message
																		code="Label.SELECT" htmlEscape="true"></spring:message>&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out></label>&nbsp;<span
																class="errormsg">*</span><br /> <form:select
																	path="localBodyNameEnglishListSource"
																	class="combofield" id="localGovtBodyListMain"
																	style="width: 175px">
																	<form:option value=" ">
																		<spring:message code="Label.SELECTINTERMEDIATELOCBODY"
																			htmlEscape="true"></spring:message>
																	</form:option>
																	<form:options items="${localBodyforSubDistList}"
																		itemLabel="localBodyNameEnglish"
																		itemValue="localBodyCode" />
																</form:select> <span id="localGovtBodyListMain_error" class="errormsg"><spring:message
																		code="error.SOURCESELECTVILLAGEPARENT" htmlEscape="true"></spring:message>
															</span> <br /> 
															
														
													</c:if>

												
											</div>
											</li>
										    
								           
										<li>
											
												<input type="submit" name="Submit" class="btn" onclick="return validateViewLBPage();" value=<spring:message code="Button.GETDATA"></spring:message> />
											
											
											<c:if test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'P')}">
												
													<input type="button" name="ClearMe" class="btn" value="<spring:message code="Button.CLEAR" ></spring:message>"  onclick="clearMe()" /> 
												
											</c:if>
											
												<input type="button" class="btn" name="CloseMe" value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>" onclick="closeMe()" /> 
												<!-- <input type="button" class="btn" name="Submit3" onclick="closePage();" value=<spring:message htmlEscape="true" code="Button.CLOSE" ></spring:message> /> -->
											
										
									
										<input type="hidden" id="msgid" name="msgid" />
											<c:if test="${msgid != null}">
												<b style="color: red;"><c:out value="${msgid}" escapeXml="true"></c:out></b>
											</c:if>
										</li>
									</ul>
									</div>
								
							</div>
						</div>
						<!-- End here Localbody  -->

					</div>
				</div>
				
				<!-- List section starts here -->

				<c:if test="${! empty LocalGovtBodyList}">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<table  cellpadding="0" cellspacing="0" border="0" id="localbodyList" class="display">
										<thead>
											<tr class="tblRowTitle tblclear">
												<td rowspan="2"><spring:message code="Label.SNO" 	htmlEscape="true"></spring:message></td>
												<td rowspan="2"><spring:message code="Label.LOCALBODYCODE" htmlEscape="true"></spring:message></td>
												<td rowspan="2"><spring:message code="Label.LOCALBODYNAMEINENG" htmlEscape="true"></spring:message></td>
												<td colspan="6" align="center"><spring:message code="Label.ACTION" htmlEscape="true"></spring:message></td>
											</tr>
											
											<tr class="tblRowTitle tblclear">
												<c:if test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'P')}">
													<th align="center"><spring:message htmlEscape="true" code="Label.VIEW"></spring:message></th>
													<th align="center">Government Order Correction</th>
													<th align="center"><spring:message htmlEscape="true" code="Label.ModifyName" ></spring:message></th>
													<th align="center"><spring:message htmlEscape="true" code="Label.Changecoveredarea" ></spring:message></th>
													<th align="center"><spring:message htmlEscape="true" code="Label.Mapcoveredarea" ></spring:message></th>
																 
													<c:if test="${localGovtBodyForm.parentLB ne '0'}">
													  		<th align="center"><spring:message htmlEscape="true" code="Label.Modifyparent" ></spring:message></th>
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
								
														<c:if test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'P')}">
															<td align="center"><a href="#"><img
																	src="images/view.png"
																	onclick="javascript:managePriDetail('ViewLocalBodyforPRIPost.htm',${lgdLocalGovtBodyList.localBodyCode},'P','A');"
																	height="19" border="0" />
															</a>
															</td>
														</c:if>

														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'P')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:managePriDetail('modifyGovtLocalBodyMain.htm',${lgdLocalGovtBodyList.localBodyCode},'P','${lgdLocalGovtBodyList.operation_state}');"
																	height="19" border="0" />
															</a>
															</td>
														</c:if>
										
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'P')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:managePriDetail('modifyGovtLocalBodyMainforname.htm',${lgdLocalGovtBodyList.localBodyCode},'P','${lgdLocalGovtBodyList.operation_state}');"
																	 height="19" border="0" />
															</a>
															</td>
														</c:if>
												
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'P')}">
																<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:managePriDetail('modifyGovtLocalBodyMainforcoveragearea.htm',${lgdLocalGovtBodyList.localBodyCode},'P','${lgdLocalGovtBodyList.operation_state}');"
																	height="19" border="0" />
																</a>
																</td>
														</c:if>
												
														<c:if
															test="${fn:containsIgnoreCase(localGovtBodyForm.lgd_lbCategory,'P')}">
															<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:managePriDetail('correctGovtLocalBodycoveragearea.htm',${lgdLocalGovtBodyList.localBodyCode},'P','${lgdLocalGovtBodyList.operation_state}');"
																	height="19" border="0" />
															</a>
															</td>
														</c:if>
												
														 <c:if test="${localGovtBodyForm.parentLB ne '0'}">
															 	<td align="center"><a href="#"><img
																	src="images/edit.png"
																	onclick="javascript:managePriDetail('modifyGovtLocalBodyMainfortype.htm',${lgdLocalGovtBodyList.localBodyCode},'P','${lgdLocalGovtBodyList.operation_state}');"
																	 height="19" border="0" />
																	</a>
																</td>
														 </c:if>	
														
													
													</tr>

												</c:forEach>
												</tbody>
											</c:if>
											
											<!--------------- End list section here ------------------------------------->
											
											<form:input path="parentwiseId" type="hidden" name="parentwiseId" id="parentwiseId" />
											<form:input path="parentCategory" type="hidden" name="parentCategory" id="parentCategory" />
							</table>
						</div>
					</div>
				</c:if>
		
				<c:if test="${localbodysize==0}">
					<c:if test="${empty LocalGovtBodyList}">
						<div class="frmpnlbg">
							<div class="frmtxt">
								<table width="100%" class="tbl_no_brdr">					
									<tr>
										<td colspan="4" align="center"><spring:message htmlEscape="true" code="error.NOPANHLOCALGOVTBODYFOUND"></spring:message></td>
									</tr>					
								</table>
							</div>
						</div>
					</c:if>
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
	var clearUrl =  "viewLocalBodyforPRI.htm?<csrf:token uri='viewLocalBodyforPRI.htm'/>";
	window.document.location.href=clearUrl;
}

</script>
</body>

</html>