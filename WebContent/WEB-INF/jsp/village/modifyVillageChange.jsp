<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<title><spring:message code="Label.MODIFYVILLAGE"
		htmlEscape="true"></spring:message></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<!-- for Unique constrain  -->
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<!-- for Unique constrain  -->


<script type="text/javascript" src="js/common.js"></script>
<script src="js/govtorder.js"></script>
<link href="css/errorLB.css" rel="stylesheet" type="text/css" />

<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/shiftdistrict.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/govtorder.js"></script>
<script type="text/javascript" src="datepicker/jquery-1.6.2.js"
	charset="utf-8"></script>
<script type="text/javascript" src="datepicker/jquery.ui.core.js"></script>
<script type="text/javascript" src="datepicker/jquery.ui.widget.js"></script>
<script type="text/javascript" src="datepicker/jquery.ui.datepicker.js"></script>

<script type="text/javascript" src="datepicker/calender.js"></script>
<!-- <script type="text/javascript" src="/js/common.js" charset="utf-8"></script> -->
<link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<!-- <script src="js/trim-jquery.js"></script> -->
<link rel="stylesheet" href="datepicker/demos.css" />


<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>

<script type="text/javascript">
	/*  function getOperationType(val)
	 {
	 	document.getElementById('isChange').value = val;    	
	 } */


	 function valdiateChangeVillageSubmitSave(){
		 document.getElementById('buttonClicked').value='S';
		 if(valdiateChangeVillageSubmit())
			 return true;
		 else
			 return false;
	 }
	 function valdiateChangeVillageSubmitPub(){
		 document.getElementById('buttonClicked').value='P';
		 if(valdiateChangeVillageSubmit())
			 return true;
		 else
			 return false;
	 }
	 function valdiateChangeVillageSubmit()
	 {
		hideError();
		var error=false;
	 	var mandatory_change_error=false;
	 	var mandatory_error=false;
	 	villageNameInEnch=document.getElementById('villagenameInEngch').value; 
	 	villageInEn=document.getElementById('villagenameInEng').value; 
	 	villageInLc=document.getElementById('villageNameInLocal').value; 
	 	aliasNameInEn=document.getElementById('aliasNameInEnglish').value; 
	 	aliasNameInLc=document.getElementById('aliasNameInLocal').value; 
	 	
	 	if(villageNameInEnch=="" || villageNameInEnch==null)
	 		{
	 		error = true;
	 		mandatory_error=true;
	 		$("#villageNameEngBlank_error").show(); 
	 		}
	 		
		 	else if (!validateVillageNameEnglish(villageNameInEnch, '#villageNameEngData_error'))
		 	{
		 		error = true;
		 	}
	 	
	 		
	 	
		 if (!validateEntityNameLocalData(villageInLc, '#villageNameLocData_error'))
	 		error = true;
	 	
	 	
		 if (!validateEntityEnglishNameData(aliasNameInEn, '#aliasNameEngData_error'))
	 		error = true;
		 if (!validateEntityNameLocalData(aliasNameInLc, '#aliasNameLocData_error'))
	 		error = true;
		
	 	if(mandatory_error==true)
	 		showClientSideError();
	 	else
	 		{
	 		if((villageInEn==villageNameInEnch))
	 			mandatory_change_error=true;
	 		
	 		 if(mandatory_change_error==true)
	 			{
	 			
	 			error=true;
	 			showNoChaneClientSideError();
	 			}
	 		}
	 	
	 	if(error==true){
	 		return false;
	 	}
	 	else{
	 		var villageCode = $('#villageId').val();
	 		lgdDwrVillageService.getMaxPreVersionEffDateOfVillages(villageCode,{
	 			async : false,
	 			callback : function(data) {
	 				$('#preVersionEffDate').val(data);
	 			},
	 			errorHandler : function() {
	 				alert("Error");
	 			}
	 		}); 
	 		if(validateGovtOrderDetailsForVillage()){
		 			$('#OrderDate').removeAttr("disabled");
		 		    $('#EffectiveDate').removeAttr("disabled");
		 			$('#GazPubDate').removeAttr("disabled");
		 			return true;		
		 		}else{
		 			return false;
		 		}
	 	}
	 	
	 	
	 }

	
	function LoadMe()
	{
		hideError();
		previousEntityName=document.getElementById('villagenameInEng').value;
		var errorflag='<c:out value="${modifyVillageCmd.errorflag}" escapeXml="true"></c:out>';
		//alert(errorflag);
		if(errorflag=="2")
			{
			showClientSideError();
			}
		else if(errorflag=="1")
			showNoChaneClientSideError();
	}
	
	function hideError()
	{
		$("#entityNameInEnExist_error").hide();
		$("#entityNameInEnExistDraft_error").hide();
		$("#villageNameEngBlank_error").hide();
		$("#villageNameEngData_error").hide();
		$("#villageNameLocData_error").hide();
		$("#aliasNameEngData_error").hide();
		$("#aliasNameLocData_error").hide();	
	}


	if ( window.addEventListener ) { 
	    window.addEventListener( "load",LoadMe, false );
	 }
	 else 
	    if ( window.attachEvent ) { 
	       window.attachEvent( "onload", LoadMe );
	 } else 
	       if ( window.onLoad ) {
	          window.onload = LoadMe;
	 }
	
	/*Modified by Pooja on 21-07-2015 for display difft. error msg*/
	var entityFieldId;
	function modifyVillageVal(subdisid,vilname,fieldId) {
		if (previousEntityName != vilname) {
			entityFieldId = fieldId;
			lgdDwrVillageService.VilageExist(subdisid, vilname, {
				callback : handleVillageSuccess,
				errorHandler : handleVillageError
			});
		}
	}

	function handleVillageSuccess(data) {
		$("#entityNameInEnExist_error").hide();
		$("#entityNameInEnExistDraft_error").hide();
		if (data=='P') {
			$("#entityNameInEnExist_error").show();
			document.getElementById(entityFieldId).value = previousEntityName;
			document.getElementById(entityFieldId).focus();
		}
		else if(data == 'S'){
			$("#entityNameInEnExistDraft_error").show();
			document.getElementById(entityFieldId).value = previousEntityName;
			document.getElementById(entityFieldId).focus();
		}
	}

	function handleVillageError() {
		document.getElementById(entityFieldId).value = "";
		document.getElementById(entityFieldId).focus();
	}
</script>

</head>
<body>

	<div class="overlay" id="overlay1" style="display: none;"></div>
	<div class="box" id="box1">
		<a class="boxclose" id="boxclose1"></a>
		<div>
			<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<Div class="success"></div>
							</center></td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div></td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center>
									<c:out value="${param.family_msg}" escapeXml="true"></c:out>
								</center>
							</div></td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><Div class="failur"></div></td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center></td>
					</tr>
					<tr>
						<td><div id="failurMsg" class="errorfont">
								<c:out value="${family_error}" escapeXml="true"></c:out>
							</div></td>
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
					<td><div class="errorfont">
							<spring:message code="error.blank.commonAlert" htmlEscape="true"></spring:message>
						</div></td>
				</tr>
			</table>

		</div>
	</div>

	<div class="box" id="noChangeBox">
		<a class="boxclose" id="noChangeboxclose"></a>
		<div id="validate_error">
			<table>
				<tr>
					<td rowspan="2"><div class="errorImg"></div></td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div></td>
				</tr>
				<tr>
					<td><div class="errorfont">
							<spring:message code="error.change.commonAlert" htmlEscape="true"></spring:message>
						</div></td>
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

	<div id="helpDiv" class="helpMessage">
		<div class="helpheader">
			<h4>Help Message</h4>
		</div>
		<div id="helpMsgText" class="helptext"></div>
		<div id="msg" class="helptext"></div>
	</div>

	<div class="box" id="box1">
		<a class="boxclose" id="boxclose1"></a>
		<div>
			<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<Div class="success"></div>
							</center></td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div></td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center>
									<c:out value="${param.family_msg}" escapeXml="true"></c:out>
								</center>
							</div></td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><Div class="failur"></div></td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center></td>
					</tr>
					<tr>
						<td><div id="failurMsg" class="errorfont">
								<c:out value="${family_error}" escapeXml="true"></c:out>
							</div></td>
					</tr>
				</table>

			</c:if>

		</div>
	</div>


	<div id="frmcontent">


		<div class="frmhd">
			<h3 class="subtitle">
				<label><spring:message code="Label.MANAGEVILLAGE"
						htmlEscape="true"></spring:message></label>
			</h3>
			<ul id="showhelp" class="listing">
				<%--//these links are not working <li><a href="#" class="frmhelp"><spring:message
							code="Button.HELP" htmlEscape="true"></spring:message> </a></li> --%>
			</ul>
		</div>


		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="draftModifyVillage.htm" method="POST" commandName="modifyVillageCmd" id="frmModifyVillage"	enctype="multipart/form-data">
			<input type="hidden" name="<csrf:token-name/>"		value="<csrf:token-value uri='draftModifyVillage.htm'/>" />
				<div id='changevillage' class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle"visibility:hidden;>
							<spring:message code="Label.CHANGE" htmlEscape="true"></spring:message>
						</div>

						<ul class="blocklist">
							<c:forEach var="listVillageDetails"
								varStatus="listVillageDetailsRow"
								items="${modifyVillageCmd.listVillageDetails}">


								<li><label><spring:message
											code="Label.VILLAGENAMEINENGLISH" htmlEscape="true"></spring:message>
								</label><span class="errormsg">*</span> <br /> <label> <spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameEnglishCh">
											<input type="text"
												onkeypress="return validateAlphanumericUpdateKeysWard(event);hideError();"
												class="frmfield" maxlength="50"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="false" />"
												cssClass="frmfield" id="villagenameInEngch"
												onblur="modifyVillageVal(${subDistrictCode},this.value,'villagenameInEngch');" />
										</spring:bind>
								</label>
									<div id="entityNameInEnExistDraft_error" style="color: red;">Same
										Village Name already exist in Draft mode. Plz go to View n
										Manage Draft entities and Publish the village or delete the
										Draft version.</div>
									<div id="entityNameInEnExist_error" class="errormsg">Same
										Village Name already exist.</div>
									<div id="villageNameEngBlank_error" class="errormsg">
										<spring:message code="Error.blank.VillageNameInEng"
											htmlEscape="true"></spring:message>
									</div>
									<div class="errormsg" id="villageNameEngData_error">
										<spring:message htmlEscape="true"
											code="Error.data.villageNameEng"></spring:message>
									</div> <form:errors path="villageNameEnglishCh" class="errormsg"
										htmlEscape="true" />
									<div class="errormsg" id="villagename_error2"
										style="display: none"></div></li>

								<li><label><spring:message
											code="Label.VILLAGENAMEINLOCAL" htmlEscape="true"></spring:message>
								</label><br /> <label> <spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameLocalCh">
											<input type="text" id="villageNameInLocal"
												onkeypress="return validateAlphanumericUpdateKeysWard(event);"
												class="frmfield" maxlength="50"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="true" />" />
										</spring:bind>
										<div class="errormsg" id="villageNameLocData_error">
											<spring:message htmlEscape="true"
												code="Error.data.VillageNameLocal"></spring:message>
										</div>
								</label> <form:errors path="villageNameLocalCh" class="errormsg"
										htmlEscape="true"></form:errors></li>

								<li><label><spring:message
											code="Label.ALIASENGLISH" htmlEscape="true"></spring:message>
								</label><br /> <label> <spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].aliasEnglishCh">
											<input type="text" id="aliasNameInEnglish"
												onkeypress="validateCharKeys(event)" class="frmfield"
												maxlength="50" name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="true" />" />
										</spring:bind>
								</label>
									<div class="errormsg" id="aliasNameEngData_error">
										<spring:message htmlEscape="true"
											code="Error.data.DistrictAliasNameEng"></spring:message>
									</div> <form:errors path="aliasEnglishCh" class="errormsg"
										htmlEscape="true"></form:errors>
									<div class="errormsg"></div></li>
								<li><label><spring:message code="Label.ALIASLOCAL"
											htmlEscape="true"></spring:message> </label><br /> <label> <spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].aliasLocalCh">
											<input type="text" id="aliasNameInLocal"
												onkeypress="validateCharKeys(event)" class="frmfield"
												maxlength="50" name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="true" />" />
										</spring:bind>
										<div class="errormsg" id="aliasNameLocData_error">
											<spring:message htmlEscape="true"
												code="Error.data.villageAliasNameLocal"></spring:message>
										</div> <form:errors path="aliasLocalCh" class="errormsg"
											htmlEscape="true"></form:errors>

										<div class="errormsg"></div> <spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameEnglish">
											<input type="hidden" id="villagenameInEng"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="true" />" />
										</spring:bind> <spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageCode">
											<input type="hidden"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="true" />" />
										</spring:bind> <spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageVersion">
											<input type="hidden"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="true" />" />
										</spring:bind> <spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].subdistrictCode">
											<input type="hidden"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="true" />" />
										</spring:bind> <spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].subdistrictVersion">
											<input type="hidden"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="true" />" />
										</spring:bind>
								</label></li>
							</c:forEach>
							<li><form:input path="villageId" type="hidden"
									name="villageId" id="villageId" /> <form:hidden
									path="govtOrderConfig" value="${govtOrderConfig}" /> <form:hidden
									path="operation" value="M" /> <input type="hidden"
								name="preVersionEffDate" id="preVersionEffDate" /></li>
						</ul>

						<!-- Govt Order Config -->
						<%-- <jsp:include page="../common/commonGovtOrder.jsp" /></jsp:include> --%>

						<div id="cat">
							<div class="frmpnlbg">
								<div class="frmtxt">
									<div class="frmhdtitle">
										<spring:message code="Label.GOVTORDERDETAILS"
											htmlEscape="true"></spring:message>
									</div>
									<%@ include
										file="../govtbody/ExistingGovernmentOrderVillage.jsp"%>
									<br /> <br />

									<div>
										<ul class="blocklist">
											<li><label><spring:message htmlEscape="true"
														code="Label.ORDERNO"></spring:message> </label><span
												class="errormsg">*</span><br /> <form:input path="orderNo"
													htmlEscape="true" id="OrderNo" type="text" class="frmfield"
													style="width: 140px" maxLength="60"
													onfocus="helpMessage(this,'OrderNo_error');"
													onblur="vlidateOrderNo('OrderNo','1','60');hideHelp();"
													onkeypress="return validateaGovtOrderNO(event);" />

												<div id="OrderNo_error" class="error">
													<spring:message code="error.required.ORDERNUM"
														htmlEscape="true"></spring:message>
												</div>
												<div id="OrderNo_msg" class="error">
													<spring:message code="error.required.ORDERINPUTTYPE"
														text="Please Enter AlphaNumerics Space . / - ( ) Only"
														htmlEscape="true" />
												</div>
												<div class="errormsg" id="OrderNo_error1">
													<form:errors path="orderNo" htmlEscape="true" />
												</div>
												<div class="errormsg" id="OrderNo_error2"
													style="display: none"></div></li>
											<li><label><spring:message
														code="Label.ORDERDATE" htmlEscape="true"></spring:message>
											</label><span class="errormsg">*</span><br /> <form:input
													path="orderDate" readonly="true" id="OrderDate" type="text"
													class="frmfield" style="width: 140px"
													onchange="setEffectiveDate(this.value);"
													onkeypress="validateNumeric();"
													onfocus="validateOnFocus('OrderDate');helpMessage(this,'OrderDate_msg');"
													onblur="vlidateOnblur('OrderDate','1','15','m');hideHelp();"
													onkeyup="hideMessageOnKeyPress('OrderDate')" />

												<div id="OrderDate_error" class="error">
													<spring:message code="error.required.ORDERDATE"
														htmlEscape="true"></spring:message>
												</div>
												<div id="OrderDate_msg" style="display: none">
													<spring:message code="error.required.ORDERDATE"
														htmlEscape="true" />
												</div>
												<div class="errormsg" id="OrderDate_error1">
													<form:errors path="orderDate" htmlEscape="true" />
												</div>
												<div class="errormsg" id="OrderDate_error2"
													style="display: none"></div></li>
											<li><label><spring:message
														code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message>
											</label><span class="errormsg">*</span><br /> <c:if
													test='${preVersionEffDate != ""}'>
										Previous version Effective Date : [ ${preVersionEffDate} ]<br />
												</c:if> <form:input id="EffectiveDate" readonly="true"
													path="effectiveDate" type="text" class="frmfield"
													style="width: 140px" onkeypress="validateNumeric();"
													onfocus="validateOnFocus('EffectiveDate');helpMessage(this,'EffectiveDate_msg');"
													onblur="vlidateOnblur('EffectiveDate','1','15','m');hideHelp();"
													onkeyup="hideMessageOnKeyPress('EffectiveDate')" />

												<div id="EffectiveDate_error" class="error">
													<spring:message code="error.required.EFFECTIVEDATE"
														htmlEscape="true"></spring:message>
												</div>
												<div id="EffectiveDate_msg" style="display: none">
													<spring:message code="error.required.EFFECTIVEDATE"
														htmlEscape="true" />
												</div>
												<div class="errormsg" id="EffectiveDate_error1">
													<form:errors path="effectiveDate" htmlEscape="true" />
												</div>
												<div class="errormsg" id="EffectiveDate_error2"
													style="display: none"></div></li>

											<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
												<li><label><spring:message
															code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>
												</label><br /> <form:input id="GazPubDate" path="gazPubDate"
														type="text" class="frmfield" style="width: 140px"
														onkeypress="validateNumeric();"
														onfocus="validateOnFocus('GazPubDate');helpMessage(this,'GazPubDate_msg');"
														onblur="vlidateOnblur('GazPubDate','1','15','m');hideHelp();"
														onkeyup="hideMessageOnKeyPress('GazPubDate')" /> <%-- <span class="errormsg" id="GazPubDate_error"></span> <form:errors
												path="gazPubDate2" cssClass="errormsg" htmlEscape="true"></form:errors> <span
											id="GazPubDate_msg" style="display: none">Please Enter
												Gazette Publication Date Like 12-04-2012</span> --%>
													<div id="GazPubDate_error" class="error">
														<spring:message code="GAZPUBDATE.REQUIRED"
															htmlEscape="true"></spring:message>
													</div>
													<div id="GazPubDate_msg" style="display: none">
														<spring:message code="GAZPUBDATE.REQUIRED"
															htmlEscape="true" />
													</div>
													<div class="errormsg" id="GazPubDate_error1">
														<form:errors path="gazPubDate" htmlEscape="true" />
													</div>
													<div class="errormsg" id="GazPubDate_error2"
														style="display: none"></div></li>
											</c:if>

											<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
												<li id="divGazettePublicationUpload"><%@ include
														file="../common/attachmentgovt.jspf"%>
												</li>
											</c:if>

											<c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
												<li><label><spring:message htmlEscape="true"
															code="Label.SELGOT"></spring:message> </label><span
													class="errormsg">*</span><br /> <form:select
														path="templateList" id="templateList" style="width:280px"
														class="combofield"
														onblur="vlidateOnblur('templateList','1','15','m');hideHelp();"
														onfocus="validateOnFocus('templateList');helpMessage(this,'templateList_msg');"
														onkeyup="hideMessageOnKeyPress('templateList')">
														<form:option value="0">
															<spring:message htmlEscape="true"
																code="Error.templateselect"></spring:message>
														</form:option>
														<form:options items="${templateList}"
															itemLabel="templateNameEnglish" itemValue="templateCode"></form:options>
													</form:select> <%-- <span class="errormsg" id="templateList_error"></span> <span><form:errors
													cssClass="errormsg" path="templateList" htmlEscape="true"></form:errors> </span> <span
											style="display: none;" id="templateList_msg">Please
												Select Government order Template</span> --%>
													<div id="templateList_error" class="error">
														<spring:message code="error.blank.template"
															htmlEscape="true"></spring:message>
													</div>
													<div id="templateList_msg" style="display: none">
														<spring:message code="error.blank.template"
															htmlEscape="true" />
													</div>
													<div class="errormsg" id="templateList_error1">
														<form:errors path="templateList" htmlEscape="true" />
													</div>
													<div class="errormsg" id="templateList_error2"
														style="display: none"></div></li>
											</c:if>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<form:hidden htmlEscape="true" path="buttonClicked" value="" />

					</div>

					<div class="btnpnl">

						<label> <input type="submit" name="Submit" class="bttn"
							id="BtnSA"
							value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>"
							onclick="return valdiateChangeVillageSubmitSave();" />
						</label> <label> <input type="submit" name="Submit" class="bttn"
							id="BtnSAP"
							value="<spring:message code="Label.PUBLISH" htmlEscape="true"></spring:message>"
							onclick="return valdiateChangeVillageSubmitPub();" />
						</label> <label> <input type="button" name="Submit3" class="bttn"
							value="<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message>"
							id="btnClear"
							onclick="javascript:location.href='modifyVillagechangebyId.htm?<csrf:token uri='modifyVillagechangebyId.htm'/>&villageId=${modifyVillageCmd.villageId}';" /></label>
						<label> <input type="button" name="Submit3" class="bttn"
							value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
							onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
						</label>
					</div>
				</div>
			</form:form>
			<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
		</div>
	</div>
</body>
</html>