<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<html>
<head>
<%@include file="../common/taglib_includes.jsp"%>
<title><spring:message code="Title.CREATENEWSUBDISTRICT"></spring:message></title>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<link href="HTML_panchayat - 2.2/css/panchayat_main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="datepicker/demos.css" />
<link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/lgdDwrSurveyService.js'></script>
<script type="text/javascript" src="js/cancel.js"></script>
<script src="js/common.js"></script>
<script type="text/javascript" src="js/sub_district_draft.js" charset="utf-8"></script>

<script src="datepicker/jquery-1.9.1.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<style>
label, input {
	display: inline;
}

input.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 25px;
}

h1 {
	font-size: 1.2em;
	margin: .6em 0;
}

div#users-contain {
	width: 550px;
	margin: 20px 0;
}

div#users-contain table {
	margin: 1em 0;
	border-collapse: collapse;
	width: 100%;
}

div#users-contain table td, div#users-contain table th {
	border: 1px solid #eee;
	padding: .6em 10px;
	text-align: left;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}
</style>
<script>

function open_win() {
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
} 
	
	$( "#dialog-form" ).dialog({
		autoOpen: false,
		height: 500,
		width: 350,
		modal: true,
		buttons: {
			"Save": function() {
			 	var bValid = true;
				allFields.removeClass( "ui-state-error" );				
				bValid = bValid && checkLength( subdisteng, "subdisteng", 1, 50 );
				bValid = bValid && checkDuplicate2(subdisteng,document.getElementById('subdisteng').value,subdistrictNameEnglish);	
				if(document.getElementById('cencd2011').value!=null && document.getElementById('cencd2011').value!=""){
				bValid = bValid && checkRegexp( cencd2011,new RegExp("^[0-9]+$"), "Census2011 Code only allow 0-9" );	
				}
				if(document.getElementById('sscd').value!=null && document.getElementById('sscd').value!=""){
				bValid = bValid && checkRegexp( sscd, new RegExp("^[a-zA-Z0-9]+$"), "State Specific Code only allow 0-9 and Alphabets");	
				}
				if ( bValid ) {		
 					document.getElementById('subdistengtemp').value=document.getElementById('subdisteng').value; 					
					document.getElementById('subdistloctemp').value=document.getElementById('subdistloc').value;
					document.getElementById('alengtemp').value=document.getElementById('aleng').value;
					document.getElementById('alloctemp').value=document.getElementById('alloc').value;
					document.getElementById('cencd2011temp').value=document.getElementById('cencd2011').value;
					document.getElementById('sscdtemp').value=document.getElementById('sscd').value;	
					
					document.forms['newsubdistrictform'].action ="updatesubdistrictdtls.htm?<csrf:token uri='updatesubdistrictdtls.htm'/>";
					document.forms['newsubdistrictform'].submit();					
					$( this ).dialog( "close" );
				} 				
			},
			Cancel: function() {
				$( this ).dialog( "close" );
			}
		},
		 close: function() {
			allFields.val( "" ).removeClass( "ui-state-error" );
		} 
	});
	
 	
	$(document).ready(function() {
		var s = document.getElementById("flag2").value;  
		if(s>0)
		getSubDistrictList(s);
		}); 



function validateForm(val1 , val2){
	document.getElementById('buttonClicked').value=val2;
	var flag = checkDuplicate(val1);	
	if(flag==true){
		formSubmitAdd(val2);
	}	
 }

function checkDuplicate(value){		
	var subDistrictName = '<%=session.getAttribute("subDistrictNames")%>';
	var arraysubDistrictName = subDistrictName.split(",");
	for(var i=0; i<arraysubDistrictName.length; i++) {
		var valueold = arraysubDistrictName[i].replace(/^\s+|\s+$/g, "");		
		if(value.toUpperCase()==valueold.toUpperCase()){			
				$( "#dialog" ).dialog({
					resizable: false,
					height:140,
					modal: true,
					buttons: {
						"Ok": function() {							
							$( this ).dialog( "close" );
						},
						
					}
				});	
		
			return false;
		}		
	}
	
		return true;
	
}

function checkDuplicate2(o,value,oldValue){			
	var subDistrictName = '<%=session.getAttribute("subDistrictNames")%>';
	var arraysubDistrictName = subDistrictName.split(",");	
	for(var i=0; i<arraysubDistrictName.length; i++) {
		var valueoldArray = arraysubDistrictName[i].replace(/^\s+|\s+$/g, "");		
		if(value.toUpperCase()==valueoldArray.toUpperCase()){
			if(value.toUpperCase()==oldValue.toUpperCase()){
				
			}
			else{
				o.addClass( "ui-state-error" );
				updateTips( "Sub district name can not be same as previously created Sub districts" );
				return false;
			   }
			} 		
		}		
	
		return true;

	}
	
function nextButton(val) {	
	if(val == 'Save'){
		formSubmitAdd(val);
	}
}

/* This function is for reset entity */
function clearEntity(operationCode, entityCode, transactionId, actionType) {
	dwr.util.setValue('operationCode', operationCode, {	escapeHtml : false	});
	dwr.util.setValue('subdistrictCode', entityCode, {	escapeHtml : false	});
	dwr.util.setValue('transactionId', transactionId, {escapeHtml : false	});
	document.getElementById('modifySubdistrictDraft').action= "modifySubdistrictDraft.htm?<csrf:token uri='modifySubdistrictDraft.htm'/>";
	document.getElementById('modifySubdistrictDraft').submit();
}

$(function() {
	$( "#OrderDate" ).datepicker({dateFormat: 'dd-mm-yy',
		value: new Date(<%=currentYear%>, <%=currentMonth%>, <%=currentDate%>),
		changeMonth: true,
		changeYear: true
	});	
});

$(function() {
$( "#EffectiveDate" ).datepicker({dateFormat: 'dd-mm-yy',
	value: new Date(<%=currentYear%>, <%=currentMonth%>, <%=currentDate%>),
	changeMonth: true,
	changeYear: true
});
});

$(function() {
$( "#GazPubDate" ).datepicker({dateFormat: 'dd-mm-yy',
	value: new Date(<%=currentYear%>, <%=currentMonth%>, <%=currentDate%>),
	changeMonth: true,
	changeYear: true
});
});

function setEffectiveDate(orderdate) {
	document.getElementById('EffectiveDate').value = orderdate;
}

function viewFile(val) { 
	if(val != "") {
		document.modifySubdistrictDraft.govfilePath.value = val;
		document.modifySubdistrictDraft.fileDisplayType.value = "inline";
		document.modifySubdistrictDraft.action = "viewGovtOrder.htm?<csrf:token uri='viewGovtOrder.htm'/>";
		document.modifySubdistrictDraft.submit();
	}
}
</script>
<%@include file="../common/dwr.jsp"%>
</head>

<body>

	<table width="100%" class="tbl_no_brdr">
		<tr>
			<td>
				<div id="frmcontent">
					<div class="frmhd">
						<h3 class="subtitle">
							<spring:message code="Title.CREATENEWSUBDISTRICT" htmlEscape="true"></spring:message>
							&nbsp;&nbsp;${subDistrictTitle}
						</h3>
						<ul class="listing">
							<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
							<li><a href="#" class="frmhelp"><spring:message code="App.HELP" htmlEscape="true"></spring:message></a></li> --%>
						</ul>
					</div>
					<div class="clear"></div>
					<div class="frmpnlbrdr">
						<form:form action="saveSubdistrictDraft.htm" name="modifySubdistrictDraft" id="modifySubdistrictDraft" method="POST" commandName="subdistrictDraft" enctype="multipart/form-data">
							<form:errors path="*">
						        <div class="msg error">
						        <h4>ATTENTION!</h4>
						        <p>Please make the following correction(s) before proceeding.</p>
						        </div>
						    </form:errors>
							<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="saveSubdistrictDraft.htm"/>" />
							<input type="hidden" name="govfilePath"/>
							<input type="hidden" name="fileDisplayType"/>
							<form:input path="operationCode" type="hidden" id="operationCode"  />
							<form:input path="subdistrictCode" type="hidden" id="subdistrictCode"  />
							<form:input path="transactionId" type="hidden" id="transactionId"/>
							<div id="topDiv">
								<div class="frmpnlbg">
									<div class="frmtxt">
										<div class="frmhdtitle">
											<label><spring:message code="Label.SELECTDISTRICT"></spring:message></label>
										</div>
										<div class="block">
											<ul class="blocklist">
												<li>
													<label for="dlc"><spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message></label>
													<strong><span id="required" class="errormsg">*</span></strong>
													<br />
													<label>
													<form:select path="dlc" class="combofield" id="dlc" style="width: 150px" onchange="getSubDistrictList(this.value)" disabled="${disabled}">
														<form:option value="0">Select District</form:option>
														<c:forEach items="${distList}" var="distListvar">
															<form:option value="${distListvar.districtCode}"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
														</c:forEach>
													</form:select>
													</label> 
													<div class="errormsg"></div></li>
											</ul>
										</div>
									</div>
								</div>

								<div class="frmpnlbg">
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message code="Legend.GENERALDETAILOFNEWSUBDISTRICT" htmlEscape="true"></spring:message>
										</div>
										<div class="block">
											<ul class="blocklist">
												<li>
												<label for="OfficialAddress"><spring:message code="Label.NAMEOFNEWSUBDISTRICTENGLISH" htmlEscape="true"></spring:message></label> <strong><span id="required" class="errormsg">*</span></strong><br />
												<label>
													<form:input	path="subdistrictNameEnglish" id="subdistrictNameEnglish" class="frmfield" onfocus="show_msg('subdistrictNameEnglish')" onblur="uniquesubdistrictNameValidation(this.value);" htmlEscape="true" maxlength="50" />
												<div id="UniquesubDistrictError" style="color: red;"></div>
												</label>
												<form:errors path="subdistrictNameEnglish" cssClass="errormsg" htmlEscape="true" />
												<div class="errormsg"></div></li>
												<li>
												<label for="subdistrictNameLocal"><spring:message code="Label.NAMEOFNEWSUBDISTRICTLOCAL" htmlEscape="true"></spring:message></label><br /> <label>
													<form:input path="subdistrictNameLocal" id="subdistrictNameLocal" class="frmfield" htmlEscape="true" maxlength="50" onblur="validatelocalCharachterforsubdistrict(this.value,1);" />
												</label>
												<form:errors path="subdistrictNameLocal" cssClass="errormsg" htmlEscape="true" /> <br />
												<div class="errormsg"></div></li>
												<li><label for="aliasEnglish"><spring:message code="Label.ALIASENGLISH" htmlEscape="true"></spring:message></label><br /> <label>
												<form:input path="aliasEnglish" id="aliasEnglish" class="frmfield" htmlEscape="true" maxlength="50" onblur="chekcalphanumeric(this.value,2);" />
												</label> <form:errors path="aliasEnglish" cssClass="errormsg" htmlEscape="true" />
													<div class="errormsg"></div></li>
												<li><label for="aliasLocal"><spring:message code="Label.ALIASLOCAL" htmlEscape="true"></spring:message></label><br /> <label>
												<form:input path="aliasLocal" id="aliasLocal" class="frmfield" htmlEscape="true" maxlength="50"	onblur="validatelocalCharachterforsubdistrict(this.value,2);" />
												</label>
												<form:errors path="aliasLocal" cssClass="errormsg" htmlEscape="true" />
												<div class="errormsg"></div></li>
												<li><label for="census2011Code"><spring:message code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message></label><br /> <label>
												<form:input path="census2011Code" id="census2011Code" class="frmfield" htmlEscape="true" onblur="numericvaluesTest(this.value,1)" maxlength="7" />
												</label> <form:errors path="census2011Code" cssClass="errormsg" htmlEscape="true" />
												<div class="errormsg"></div></li>
												<li><label for="sscode"><spring:message code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message></label><br /> <label>
												<form:input path="sscode" id="sscode" class="frmfield" htmlEscape="true" onblur="chekcalphanumeric(this.value,3);" maxlength="5" />
												</label> <form:errors path="sscode" cssClass="errormsg" htmlEscape="true" />
												<div class="errormsg"></div></li>
											</ul>
										</div>
									</div>
								</div>
								<!-- -------------------------------------------------- Headquarter section ----------------------------------------------------------------- -->
								<div class="frmpnlbg">
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message htmlEscape="true" code="Label.HEADQUARTER"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="100%" class="tblclear" />
												<table class="tbl_no_brdr" width="491">
													<tr>
														<td><label id="headquarterName"><spring:message htmlEscape="true" code="Label.DISTRICTHEADQUARTERENGLISH"></spring:message> </label></td>
														<td><label id="headquarterNameLocal"><spring:message htmlEscape="true" code="Label.SUBDISTRICTHEADQUARTERLOCAL"></spring:message> </label></td>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td>
															<spring:bind path="headquartersDraft.headquarterNameEnglish">
																<input type="text" maxLength="50" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" onkeypress="return validateAlphaWithSpace(event);" />
															</spring:bind>
															<form:errors path="headquartersDraft.headquarterNameEnglish" cssStyle="color:red" htmlEscape="true" />
														</td>
														<td>
															<spring:bind path="headquartersDraft.headquarterLocalName">
																<input type="text" maxLength="50" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" onkeypress="return validateSpecialCharactersUpdateStandardCodeLocal(event)" />
															</spring:bind>
															<form:errors htmlEscape="true" path="headquartersDraft.headquarterLocalName" cssStyle="color:red" />
														</td>
													</tr>
												</table>
											</tr>
										</table>
									</div>
								</div>
								<!-- ----------------------------------------------- End Headquarter section ----------------------------------------------------------------- --> 
								<%@ include file="../common/gis_nodes.jspf"%>

								<div class="frmpnlbg">
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message code="Label.CONTRIBUTINGLANDREGION" htmlEscape="true"></spring:message>
										</div>
										<ul class="blocklist">
											<li>
												<div class="ms_container">
													<div class="ms_selectable">
														<label> <spring:message code="Label.SUBDISTRICTS" htmlEscape="true"></spring:message>
														</label><br>
														<form:select name="ddSubdistrict" size="1" id="ddSubdistrict" path="" multiple="multiple" class="frmtxtarea">
															<form:options items="${subdistrictDraft.subdistrictList}" itemLabel="subdistrictNameEnglish" itemValue="subdistrictCode" />
														</form:select>
													</div>


													<div class="ms_buttons">
														<input type="button" class="bttn" id="btnaddSubDistrictFull" name="Submit4" value="Select Full Sub Districts&gt;&gt;" onclick="CheckthenaddItem('subDistrictListNew','ddSubdistrict','FULL',true)" /> <input type="button" class="bttn"
															id="btnremoveOneSubDistrict" name="Submit4" value=" &lt; " onclick="removepartVilagelist();removeItem('subDistrictListNew','ddSubdistrict',true);" /> <input type="button" class="bttn" id="btnremoveAllSubDistricts" name="Submit4"
															value="&lt;&lt;" onclick="removeAllListItems('subDistrictListNew','ddSubdistrict',true)" /> <input type="button" class="bttn" id="btnaddSubDistrictPart" name="Submit4" value="Select Part Sub Districts&gt;&gt;"
															onclick="addItem('subDistrictListNew','ddSubdistrict','PART',true);" />
													</div>

													<div class="ms_selection">
														<label> <spring:message code="Label.CONTRIBUTINGSUBDISTRICTLIST" htmlEscape="true"></spring:message>
														</label>
														<form:select name="select4" id="subDistrictListNew" size="1" multiple="multiple" path="" class="frmtxtarea">
															<%-- disabled="${disabled}" --%>
															<form:options items="${subdistrictDraft.newsubdistrictList}" itemLabel="subdistrictNameEnglish" itemValue="subdistrictCode" />
															<%-- items="${listSubDistrict1}" --%>
														</form:select>
														<br></br> <label> <input type="button" class="bttn" id="partSubdistrict" name="Submit7" onclick="getVillageList()" value="<spring:message code="Button.GETPARTSUBDISTRICT" htmlEscape="true"></spring:message>" />
														</label>
													</div>
													<div style="height: 15px; padding-top: 3px;" class="errormsg"></div>
													<div class="errormsg"></div>
												</div>
											</li>
										</ul>

										<ul class="blocklist">
											<li>
												<div class="ms_container">
													<div class="ms_selectable">
														<label> <spring:message code="Label.VILLAGES" htmlEscape="true"></spring:message>
														</label><br>
														<form:select name="select9" size="1" id="villageList" path="" multiple="multiple" class="frmtxtarea">
															<c:forEach items="${subdistrictDraft.villageList}" var="obj">
																<form:option value="${obj.villageCode}:${obj.villageVersion}"><c:out value="${obj.villageNameEnglish}" escapeXml="true"></c:out></form:option>
															</c:forEach>
														</form:select>
													</div>


													<div class="ms_buttons">
														<label> <input type="button" class="bttn" id="btnaddVillageFull" name="Submit4" value="Select Villages&gt;&gt;" onclick="addItem('villageListNew','villageList','FULL',true)" />
														</label> <label> <input type="button" class="bttn" id="btnremoveOneVillage" name="Submit4" value=" &lt; " onclick="removeItem('villageListNew','villageList',true)" />
														</label> <label> <input type="button" class="bttn" id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;" onclick="removeAll('villageListNew','villageList',true)" />
														</label>
													</div>

													<div class="ms_selection">
														<label> <spring:message code="Label.CONTRIBUTINGVILLAGELISTSD" htmlEscape="true"></spring:message>
														</label>
														<form:select id="villageListNew" size="1" multiple="multiple" path="villageArray" class="frmtxtarea">
															<c:forEach items="${subdistrictDraft.tlc}" var="obj">
																<form:option value="${obj.villageCode}:${obj.villageVersion}"><c:out value="${obj.villageNameEnglish}" escapeXml="true"></c:out></form:option>
															</c:forEach>
														</form:select>
													</div>
													<div class="errormsg"></div>
												</div>
											</li>
										</ul>
										<div class="clear"></div>
									</div>
								</div>
							</div>
							
						<!-- -----------------------------------------------Section for government order details---------------------------------------------------------- -->
							<div id="cat">
								<div class="frmpnlbg">
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message>
										</div>

										<div>
											<ul class="blocklist">
												<li><label><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message> </label>
														<span class="errormsg">*</span>
													<br />
													<label>
														<spring:bind path="governmentOrderDraft.orderNo">
															<input type="text" id="OrderNo" class="frmfield" readonly="readonly" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true" />" style="width: 128px" />
														</spring:bind>
													</label>
													<form:errors path="governmentOrderDraft.orderNo" cssClass="errormsg" htmlEscape="true" />
												</li>
												
												<li><label><spring:message code="Label.ORDERCODE" htmlEscape="true"></spring:message> </label>
														<span class="errormsg">*</span>
													 <br />
													<label>
														<spring:bind path="governmentOrderDraft.orderCode">
															<input type="text" id="orderCode" readonly="readonly" style="width: 128px" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true" />" />
														</spring:bind>
												</li>
												
												<li><label><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message> </label>
														<span class="errormsg">*</span>
													<br />
													<label>
														<spring:bind path="governmentOrderDraft.orderDate">
															<input type="text" id="OrderDate" onchange="setEffectiveDate(this.value);" 
																readonly="readonly" style="width: 128px" 
																class="frmfield" 
																name="<c:out value="${status.expression}"/>" 
																value="<c:out value="${status.value}" escapeXml="true" />" />
														</spring:bind>
												</li>
												<li><label><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message></label>
														<span class="errormsg">*</span>
													<br /> <label>
													<spring:bind path="governmentOrderDraft.effectiveDate">
														<input type="text" style="width: 128px" class="frmfield" id="EffectiveDate" readonly="readonly" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true" />" />
													</spring:bind>
												</li>

												<li id="divGazettePublicationUpload">
													<div>
														<%@ include file="../common/updateattach.jspf"%>
													</div>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						<!-- --------------------------------------------end section of governmrnt order details ------------------------------------------------------------->
							<div class="block">
								<input type="hidden" name="subdistengtemp" id="subdistengtemp" value="" /> <input type="hidden" name="subdistloctemp" id="subdistloctemp" /> <input type="hidden" name="alengtemp" id="alengtemp" /> <input type="hidden" name="alloctemp"
									id="alloctemp" value="" /> <input type="hidden" name="cencd2011temp" id="cencd2011temp" value="" /> <input type="hidden" name="sscdtemp" id="sscdtemp" value="" /> <input type="hidden" name="villageNumber" id="villageNumber" value="" /> <input
									type="hidden" name="buttonClicked" id="buttonClicked" value="" /> <input type="hidden" name="reorganized" id="reorganized" /> <input type="hidden" name="modifyVillage" id="modifyVillage" /> <input type="hidden" name="addAnotherSD"
									id="addAnotherSD" value="" /> <input type="hidden" name="villageNameList" id="villageNameList" value="" /> <input type="hidden" name="subDistrictListForSession" id="subDistrictListForSession" value="" /> <input type="hidden"
									name="contsubDistrictListForSession" id="contsubDistrictListForSession" value="" /> <input type="hidden" name="villagesListForSession" id="villagesListForSession" value="" /> <input type="hidden" name="contvillagesListForSession"
									id="contvillagesListForSession" /> <input type="hidden" name="previousAddedVillageCodes" id="previousAddedVillageCodes" value="${previousAddedVillageCodes}" /> <input type="hidden" name="partAddedSubdistrict" id="partAddedSubdistrict"
									value="${partAddedSubdistrict}" /> <input type="hidden" name="hdn" id="hdn" /> <input type="hidden" name="flag1" id="flag1" value="${flag1}" /> <input type="hidden" name="flag2" id="flag2" value="${flag2}" />

								<div id="dialogcscode" title="Alert Message" style="display: none">
									<p>Census2011 Code must be numeric value.</p>
								</div>
								<div id="dialogsscode" title="Alert Message" style="display: none">
									<p>State Specific Code must be numeric value.</p>
								</div>

								<div class="btnpnl">
									<label>
										<input type="button" class="button" id="Submit2" value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" onclick="nextButton(this.value);" />
									</label>
									
									<label>
										<input type="button" class="button" value="<spring:message code="Button.CLEAR"/>" onclick="clearEntity(${subdistrictDraft.operationCode},${subdistrictDraft.subdistrictCode},${subdistrictDraft.transactionId},'E');" />
									</label>
									<label>
										<input type="button" class="button" name="Submit6" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:go('cancelSD.htm');" />
									</label>
									
								</div>
								
							</div>
					</div>
				
					</form:form>
					<script src="/LGD/JavaScriptServlet"></script>
				</div>

			</td>
		</tr>
	</table>
	<c:if test="${error != null}">
		<script>alert("<c:out value='${error}'/>")</script>
	</c:if>
</body>
</html>