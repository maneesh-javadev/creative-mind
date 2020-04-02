<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<title><spring:message code="Label.MODIFYDRAFTVILLAGE" htmlEscape="true"></spring:message>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<!-- for Unique constrain  -->
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrCommonService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
	<!-- for Unique constrain  -->

<script src="js/jquery.MultiFile.js" type="text/javascript"
	language="javascript"></script>
<script type="text/javascript" src="js/common.js"></script>
<script src="js/govtorder.js"></script>
<link href="css/errorLB.css" rel="stylesheet" type="text/css" />

<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/shiftdistrict.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script type="text/javascript" src="js/common.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />

<link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<link rel="stylesheet" href="datepicker/demos.css" />
<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>

<script type="text/javascript">
	/*  function getOperationType(val)
	 {
	 	document.getElementById('isChange').value = val;    	
	 } */


	function hideAll(){
			mandatoryFlag='${mandatoryFlag}';
		 	$("#EffectiveFutureDate_error").hide();
			$("#OrderFutureDate_error").hide();
		 	$("#EffectiveDateBlank_error").hide();
			$("#aliasNameEngData_error").hide();
			$("#aliasNameLocData_error").hide();
			$("#txtCensus_error").hide();
			$("#OrderNoBlank_error").hide();
			$("#OrderNoDataValid_error").hide();
			$("#OrderDateBlank_error").hide();
			$("#OrderDateData_error").hide();
			$("#OrderDateValid_error").hide();
			$("#GuzpubDateValid_error").hide();
			$("#error_govorder").hide();
			$("#EffectiveDateData_error").hide();
			$("#sscode_error").hide();
			$("#GuzpubDateCompareOrderDate_error").hide();
			$("#GuzpubDateBlankOrderDate_error").hide();
			$("#OrderNo_error").hide();
			$("#OrderNo_msg").hide();
	}
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
	 function setEffectiveDatetoOrderDate(orderDate)
	 {
		
		 //alert(orderDate);
		 if(orderDate!="" && orderDate!=undefined)
		 document.getElementById('EffectiveDate').value=orderDate;
	 }
	 function toggledisplayedOnload()
		{
		<c:if test="${isExistGovt == 'Y'}">
			document.getElementById('orderCode').value='${orderCode}';
			$("#divGazettePublicationUpload").hide(); 
			$("#divExistGovtFileUpload").show();
		</c:if>
		<c:if test="${isExistGovt == 'N'}">
			$("#divExistGovtFileUpload").hide();
			$("#divGazettePublicationUpload").show();
			document.getElementById('orderCode').value='';
		</c:if>
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
	 	
	 	var orderDate= document.getElementById('OrderDate').value; 
	    var effectiveDate=document.getElementById('EffectiveDate').value; 
		var GuzpubDate=document.getElementById('GazPubDate').value; 
		var OrderNo=document.getElementById('OrderNo').value;
		if(villageNameInEnch=="" || villageNameInEnch==null){
	 		error = true;
	 		mandatory_error=true;
	 		$("#villageNameEngBlank_error").show(); 
	 		}else if (!validateVillageNameEnglish(villageNameInEnch, '#villageNameEngData_error'))
		 	{
		 		error = true;
		 	}	
	 	if (!validateEntityNameLocalData(villageInLc, '#villageNameLocData_error'))
	 		error = true;
	 	if (!validateEntityEnglishNameData(aliasNameInEn, '#aliasNameEngData_error'))
	 		error = true;
	 	if (!validateEntityNameLocalData(aliasNameInLc, '#aliasNameLocData_error'))
	 		error = true;
	 	
	 	if(OrderNo == "" || OrderNo == null){
	 		mandatory_error=true;
	 		$("#OrderNo_error").show();
	 		
	 	}
	 	if(mandatory_error==true)
	 		showClientSideError();
	 	
	 	if(error==true){
	 		return false;
	 	}
	 	else{
	 		var villageCode = $('#renameVillageCode').val();
	 		lgdDwrVillageService.getMaxPreVersionEffDateOfVillages(villageCode,{
	 			async : false,
	 			callback : function(data) {
	 				$('#preVersionEffDate').val(data);
	 			},
	 			errorHandler : function() {
	 				alert("Error");
	 			}
	 		});
	 		if(validateGovtOrderDetailsForVilModify()){
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
		var errorflag='${modifyVillageCmd.errorflag}';
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
		hideAll();	
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
				callback : handleDistrictSuccess,
				errorHandler : handleDistrictError
			});
		}
	}
	
	function handleDistrictSuccess(data) {
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

	function handleDistrictError() {
		document.getElementById(entityFieldId).value = "";
		document.getElementById(entityFieldId).focus();
	}
</script>

</head>
<body onload="toggledisplayedOnload();">

	<div class="overlay" id="overlay1" style="display:none;"></div>
    <div class="box" id="box1">
            <a class="boxclose" id="boxclose1"></a>
			<div >
			<c:if test="${!empty param.family_msg}">
				<table>
								<tr><td rowspan="2"><center><Div class= "success"></div></center></td>
								
								<td ><div  class="helpMsgHeader" style="width:275px;"><h4>Success Message</h4></div></td></tr>
								<tr><td><div id="successMsg" class= "successfont" ><center><c:out value="${param.family_msg}"></c:out></center></div>
					          </td></tr></table>
				
			</c:if>
				
				<c:if test="${!empty family_error}">
			
				<table>
				<tr><td rowspan="2"><Div class= "failur"></div></td>
				
				<td><center><div class="helpMsgHeader" style="width:275px;"><b>Failure Message</b></div></center></td></tr>
				<tr><td><div id="failurMsg" class="errorfont"><c:out value="${family_error}"></c:out></div>
	          </td></tr></table>
			
				</c:if>
							 
			</div>
       </div>
       
      <div class="box" id="box">
            <a class="boxclose" id="boxclose"></a>
            <div id="validate_error" >
							<table><tr><td rowspan="2"><div class= "errorImg"></div></td>
							<td><div  class="helpMsgHeader" style="width:275px;"><h4>Error Message</h4></div></td></tr>
			                <tr><td><div class="errorfont"><spring:message code="error.blank.commonAlert" htmlEscape="true"></spring:message></div>
			                </td></tr> </table>
                         
			</div>			
        	</div>
        	
        	  <div class="box" id="noChangeBox">
            <a class="boxclose" id="noChangeboxclose"></a>
            <div id="validate_error" >
							<table><tr><td rowspan="2"><div class= "errorImg"></div></td>
							<td><div  class="helpMsgHeader" style="width:275px;"><h4>Error Message</h4></div></td></tr>
			                <tr><td><div class="errorfont"><spring:message code="error.change.commonAlert" htmlEscape="true"></spring:message></div>
			                </td></tr> </table>
                         
			</div>			
        	</div>	

  <div id="helpDiv" class="helpMessage" >
 <div class="helpheader"><h4>Help Message</h4></div>
<div id="helpMsgText" class="helptext"></div> 
</div>	

 <div id="helpDiv" class="helpMessage" >
 <div class="helpheader"><h4>Help Message</h4></div>
<div id="helpMsgText" class="helptext"></div> 
<div id="msg" class="helptext"></div> 
</div>

  <div class="box" id="box1">
            <a class="boxclose" id="boxclose1"></a>
			<div >
			<c:if test="${!empty param.family_msg}">
				<table>
								<tr><td rowspan="2"><center><Div class= "success"></div></center></td>
								
								<td ><div  class="helpMsgHeader" style="width:275px;"><h4>Success Message</h4></div></td></tr>
								<tr><td><div id="successMsg" class= "successfont" ><center><c:out value="${param.family_msg}"></c:out></center></div>
					          </td></tr></table>
				
			</c:if>
				
				<c:if test="${!empty family_error}">
			
				<table>
				<tr><td rowspan="2"><Div class= "failur"></div></td>
				
				<td><center><div class="helpMsgHeader" style="width:275px;"><b>Failure Message</b></div></center></td></tr>
				<tr><td><div id="failurMsg" class="errorfont"><c:out value="${family_error}"></c:out></div>
	          </td></tr></table>
			
				</c:if>
							 
			</div>
       </div>
       

	<div id="frmcontent">
	

		<div class="frmhd">
			<h3 class="subtitle">
				<label><spring:message code="Label.MANAGERENAMEDRAFTVILLAGE" htmlEscape="true"></spring:message></label>
			</h3>
			<ul id="showhelp" class="listing">												
				<%--//these links are not working  <li>
					<a href="#" class="frmhelp"><spring:message code="Button.HELP" htmlEscape="true"></spring:message> </a>
				</li> --%>
			</ul>
		</div>
		
		
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="renameModifyVillage.htm" method="POST" commandName="modifyVillageCmd" id="frmModifyVillage" enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='renameModifyVillage.htm'/>" />
				<input type="hidden" name="draftVilCode" id="draftVilCode" value="${draftVilCode}"/>
				<input type="hidden" name="isExistGovt" id="isExistGovt" value="${isExistGovt}"/>
				<input type="hidden" name="count" value="" id="count"></input>
				<input type="hidden" name="renameVillageCode" value="${renameVillageCode}" id="renameVillageCode"></input>
				<c:if test="${isExistGovt == 'N'}">
				<input type="hidden" name="checkNewOrExist" id="checkNewOrExist" />
				</c:if>
				<form:hidden htmlEscape="true" path="buttonClicked"	value="" />	
				<input type="hidden" id="govtfilecount" name="govtfilecount" value="${govtfilecount}"> </input>
				<input type="hidden" name="preVersionEffDate" id="preVersionEffDate" />
				<div id='changevillage' class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle" visibility:hidden;>
							<spring:message code="Label.CHANGE" htmlEscape="true"></spring:message>
						</div>						
					
						 <ul class="blocklist">
							<c:forEach var="listVillageDetails"
								varStatus="listVillageDetailsRow"
								items="${modifyVillageCmd.listVillageDetails}">


								<li><label><spring:message code="Label.VILLAGENAMEINENGLISH" htmlEscape="true"></spring:message> </label><span class="errormsg">*</span> <br /> <label> <spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameEnglishCh">
											<input type="text" onkeypress="return validateAlphanumericUpdateKeysWard(event);hideError();" class="frmfield" maxlength="50" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="false" />"
												cssClass="frmfield" id="villagenameInEngch" onblur="modifyVillageVal(${subDistrictCode},this.value,'villagenameInEngch');" />
										</spring:bind></label>
									<div id="entityNameInEnExistDraft_error" style="color: red;">Same Village Name already exist in Draft mode. Plz go to View n Manage Draft entities and Publish the village or delete the Draft version.</div>
									<div id="entityNameInEnExist_error" class="errormsg">Same Village Name already exist.</div>
									<div id="villageNameEngBlank_error" class="errormsg">
										<spring:message code="Error.blank.VillageNameInEng" htmlEscape="true"></spring:message>
									</div>
									<div class="errormsg" id="villageNameEngData_error">
										<spring:message htmlEscape="true" code="Error.data.villageNameEng"></spring:message>
									</div> <form:errors path="villageNameEnglishCh" class="errormsg" htmlEscape="true" />
									<div class="errormsg" id="villagename_error2" style="display: none"></div></li>

								<li>																		
										<label><spring:message
												code="Label.VILLAGENAMEINLOCAL" htmlEscape="true"></spring:message>
										</label><br /> <label> <spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameLocalCh">
												<input type="text" id="villageNameInLocal"
													onkeypress="return validateAlphanumericUpdateKeysWard(event);" class="frmfield"
													maxlength="50" name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />" 
													 />
											</spring:bind>
											 <div class="errormsg" id="villageNameLocData_error">
												<spring:message htmlEscape="true" code="Error.data.VillageNameLocal"></spring:message>
									      </div>
											 </label>
											<form:errors path="villageNameLocalCh" class="errormsg" htmlEscape="true"></form:errors> 																													
								</li>
									
								<li>
									<label><spring:message
												code="Label.ALIASENGLISH" htmlEscape="true"></spring:message>
									</label><br /> <label> <spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].aliasEnglishCh">
												<input type="text" id="aliasNameInEnglish"
													onkeypress="validateCharKeys(event)" class="frmfield"
													maxlength="50" name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />" />
											</spring:bind> </label>
											<div class="errormsg" id="aliasNameEngData_error">
												<spring:message htmlEscape="true" code="Error.data.DistrictAliasNameEng"></spring:message>
									       </div>
											<form:errors path="aliasEnglishCh" class="errormsg" htmlEscape="true"></form:errors> 
										<div class="errormsg"></div>
										 
								
								</li>
								<li>
									<label><spring:message code="Label.ALIASLOCAL" htmlEscape="true"></spring:message>
									</label><br /> <label>
									<spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].aliasLocalCh">
												<input type="text" id="aliasNameInLocal"
													onkeypress="validateCharKeys(event)" class="frmfield"
													maxlength="50" name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />" />
											</spring:bind> 
											<div class="errormsg" id="aliasNameLocData_error">
												<spring:message htmlEscape="true" code="Error.data.villageAliasNameLocal"></spring:message>
									       </div>
											<form:errors path="aliasLocalCh" class="errormsg" htmlEscape="true"></form:errors>
											
											<div class="errormsg"></div>  
									
									<spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameEnglish">
												<input type="hidden" id="villagenameInEng"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />" />
													</spring:bind>
											
											
											
											<spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageCode">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />" />
											</spring:bind> 
											<spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageVersion">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />" />
											</spring:bind> <spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].subdistrictCode">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />" />
											</spring:bind> <spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].subdistrictVersion">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />" />
											</spring:bind> </label>
								</li>
							</c:forEach>
							<li>
 								<form:input path="villageId" type="hidden" name="villageId" id="villageId"  />	
								<form:hidden path="govtOrderConfig" value="${govtOrderConfig}" />
								<form:hidden path="operation" value="M" />							
							</li>
						</ul> 
						
			<!-- Govt Order Config -->
				<%-- <jsp:include page="../common/commonGovtOrder.jsp" /></jsp:include> --%>
				
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message>
							</div>
							<%@ include file="../govtbody/ExistingGovernmentOrderVillage.jsp"%>
							<br />
							
						<div >							
							<ul class="blocklist">
							<c:forEach var="listVillageDetails"
								varStatus="listVillageDetailsRow"
								items="${modifyVillageCmd.listVillageDetails}">
								<c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
								<li>
								
										<label><spring:message
												code="Label.ORDERNO" htmlEscape="true"></spring:message> </label> <c:if
											test="${mandatoryFlag==true}">
											<span class="errormsg">*</span>
										</c:if> <br /> <label> <spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderNocr">
												<input type="text" id="OrderNo" class="frmfield"
													readonly="readonly"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${fn:trim(status.value)}" escapeXml="false" />"
													style="width: 128px" />

											</spring:bind></label> <spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderCodecr">
											<input type="hidden" id="orderCode"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="false" />" />
										</spring:bind> <form:errors path="orderNocr" cssClass="errormsg"
											htmlEscape="true" />
										<div class="errormsg"></div>
									
								</li>
								<li>
										<label><spring:message code="Label.ORDERDATE"
												htmlEscape="true"></spring:message> </label> <c:if
											test="${mandatoryFlag==true}">
											<span class="errormsg">*</span>
										</c:if> <br /> <label> <spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderDatecr">
												<input type="text" id="OrderDate" readonly="readonly"
													style="width: 128px" class="frmfield"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />" />
											</spring:bind>

								</li>
								<li>
										<label><spring:message
												code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message>
										</label> <c:if test="${mandatoryFlag==true}">
											<span class="errormsg">*</span>
										</c:if> <br /> <label> <spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].ordereffectiveDatecr">
												<%--   <c:if test="${listStateDetails.ordereffectiveDatecr >}">	 --%>
												<input type="text" style="width: 128px" class="frmfield"
													id="EffectiveDate" readonly="readonly"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />" />

											</spring:bind>
								</li>
								</c:if>
								<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
								<li>										
										<label><spring:message
												code="Label.ORDERNO" htmlEscape="true"></spring:message> </label> <c:if
											test="${mandatoryFlag==true}">
											<span class="errormsg">*</span>
										</c:if> <br /> <label> <spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderNocr">
												<input type="text" maxlength="60" class="frmfield"
													onkeypress="return validateaGovtOrderNOforModify(event);"
													id="OrderNo" onfocus="validateOnFocus('OrderNo');"
													onblur="vlidateOrderNo('OrderNo','1','60');"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${fn:trim(status.value)}" escapeXml="false" />"
													style="width: 128px" />

											</spring:bind></label>
										<div id="OrderNo_error" class="errormsg">
											<spring:message code="error.required.ORDERNUM"
												htmlEscape="true"></spring:message>
										</div>
										<div id="OrderNo_msg" class="errormsg">
											<spring:message code="error.required.ORDERINPUTTYPE"
												text="Please Enter AlphaNumerics Space . / - ( ) Only"
												htmlEscape="true" />
										</div> <span class="errormsg" id="OrderNo_error"></span> <spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderCodecr">
											<input type="hidden"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="false" />" />
										</spring:bind> <form:errors path="orderNocr" cssClass="errormsg"
											htmlEscape="true" />
										<div class="errormsg"></div>
								</li>
								<li>
										<label><spring:message code="Label.ORDERDATE"
												htmlEscape="true"></spring:message> </label> <c:if
											test="${mandatoryFlag==true}">
											<span class="errormsg">*</span>
										</c:if> <br /> <label> <c:if test="${mandatoryFlag==true}">
												<spring:bind
													path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderDatecr">
													<input type="text" id="OrderDate" style="width: 128px"
														class="frmfield"
														onblur="vlidateOnblur('OrderDate','1','15','c');"
														onfocus="hideAll();setOrderDateforCorrection('OrderDate','#OrderDateData_error');"
														onchange="vaidatetOrderDateforCorrectionEntity(this.value,'#OrderDateData_error');"
														onkeypress="hideAll();"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="false" />" />
												</spring:bind>
											</c:if> <c:if test="${mandatoryFlag==false}">
												<spring:bind
													path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderDatecr">
													<input type="text" id="OrderDate" style="width: 128px"
														class="frmfield" onfocus="hideAll();"
														onblur="vlidateOnblur('OrderDate','1','15','c');"
														onchange="validateDatetoFuture('OrderDate','#OrderFutureDate_error');setEffectiveDatetoOrderDate(this.value)"
														onkeypress="hideAll();"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="false" />" />
												</spring:bind>
											</c:if>
									</label><span class="errormsg" id="OrderDate_error"></span>
									<form:errors path="orderDatecr" cssClass="errormsg"
											htmlEscape="true" />
										<div class="errormsg" id="OrderDateBlank_error">
											<spring:message htmlEscape="true"
												code="error.required.ORDERDATE"></spring:message>
										</div>

										<div class="errormsg" id="OrderFutureDate_error">
											<spring:message htmlEscape="true"
												code="error.INCORECT.ORDERDATE"></spring:message>
										</div>

										<div class="errormsg" id="OrderDateValid_error">
											<spring:message htmlEscape="true"
												code="error.valid.DATEFORMAT"></spring:message>
										</div>

										<div class="errormsg" id="OrderDateData_error">
											<spring:message htmlEscape="true"
												code="error.compare.ORDERDATE"></spring:message>
										</div>
										<div class="errormsg"></div>

								</li>
								<li>
										<label><spring:message
												code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message>
										</label> <c:if test="${mandatoryFlag==true}">
											<span class="errormsg">*</span>
										</c:if> <br /> 
										<c:if test='${preVersionEffDate != ""}' >
										Previous version Effective Date : [ ${preVersionEffDate} ]<br/>
										</c:if>
										<label> <c:if test="${mandatoryFlag==true}">
												<spring:bind
													path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].ordereffectiveDatecr">
													<%--   <c:if test="${listStateDetails.ordereffectiveDatecr >}">	 --%>
													<input type="text" id="EffectiveDate" style="width: 128px"
														class="frmfield" readonly="readonly"
														onfocus="validateOnFocus('EffectiveDate');"
														onblur="vlidateOnblur('EffectiveDate','1','15','c');"
														onkeypress="validateNumeric();"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="false" />" />

												</spring:bind>
											</c:if> <c:if test="${mandatoryFlag==false}">
												<spring:bind
													path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].ordereffectiveDatecr">
													<%--   <c:if test="${listStateDetails.ordereffectiveDatecr >}">	 --%>
													<input type="text" id="EffectiveDate" style="width: 128px"
														class="frmfield"
														onfocus="validateOnFocus('EffectiveDate');"
														onblur="vlidateOnblur('EffectiveDate','1','15','c');"
														onchange="validateDatetoFuture('EffectiveDate','#EffectiveFutureDate_error');validateEffectiveDatecompOrderDate('OrderDate','EffectiveDate','#EffectiveDateData_error');"
														onkeypress="hideAll();"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="false" />" />

												</spring:bind>
											</c:if>
										
								
											<div class="errormsg" id="EffectiveDateData_error">
												<spring:message htmlEscape="true"
													code="error.compare.EFFECTIVEDATE"></spring:message>
											</div>

											<div class="errormsg" id="EffectiveDateBlank_error">
												<spring:message htmlEscape="true"
													code="ordereffectiveDate.required"></spring:message>
											</div>
											<div class="errormsg" id="EffectiveFutureDate_error">
												<spring:message htmlEscape="true"
													code="error.valid.EFFECTIVEFUTUREDATE"></spring:message>
											</div>
											<div class="errormsg"></div>
									</label><span class="errormsg" id="EffectiveDate_error"></span> <form:errors
											path="ordereffectiveDatecr" cssClass="errormsg" />
										<div class="errormsg"></div>
							
								</li>
								<li>
										<label><spring:message code="Label.GAZPUBDATE"
												htmlEscape="true"></spring:message> </label><br /> <label>
											<spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].gazPubDatecr">
												<input type="text" id="GazPubDate" style="width: 128px"
													class="frmfield" onkeyup="validateNumeric();"
													onchange="noOrderDataValid('GazPubDate','#GuzpubDateBlankOrderDate_error','OrderDate');compareGuzpubDatetoOrderDate('GazPubDate','#GuzpubDateCompareOrderDate_error','OrderDate')"
													onkeypress="hideAll();"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />" />

											</spring:bind>
										</label>
										<div class="errormsg" id="GuzpubDateValid_error">
											<spring:message htmlEscape="true"
												code="error.valid.DATEFORMAT"></spring:message>
										</div>
										<div class="errormsg" id="GuzpubDateBlankOrderDate_error">
											<spring:message htmlEscape="true"
												code="error.required.ORDERDATE"></spring:message>
										</div>

										<div class="errormsg" id="GuzpubDateCompareOrderDate_error">
											<spring:message htmlEscape="true"
												code="error.compare.GuzpubDate"></spring:message>
										</div> <form:errors path="gazPubDatecr" cssClass="errormsg" />
										<div class="errormsg"></div>
										<div id="GazPubDate_error" class="error">
											<spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true"></spring:message>
										</div>
										<div id="GazPubDate_msg" style="display: none">
											<spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true" />
										</div>
										<div class="errormsg" id="GazPubDate_error1">
											<form:errors path="gazPubDate" htmlEscape="true" />
										</div>
										<div class="errormsg" id="GazPubDate_error2"
											style="display: none"></div>
								</li>

								<li id="divGazettePublicationUpload">									
									<div>
										<%@ include file="../common/updateattach.jspf"%>
									</div>																			
								</li>
								
								<li id="divExistGovtFileUpload">
									<div id="divfileUpload">
										<c:set var="fileName" value="${existUploadGovtOrderFileName}" />
										<c:set var="fileTimestamp" value="${existUploadGovtOrderFileTimeStamp}" />
										<label><spring:message code='Label.UGO' htmlEscape='true'/></label>&nbsp;&nbsp;
										<a href="downloadLBGovernementOrder.htm?filename=<c:out value="${fileTimestamp}"/>&<csrf:token uri='downloadLBGovernementOrder.htm'/>"><c:out value="${fileName}"/></a>
									</div>
								</li>
							</c:if>
								</c:forEach>
																												
							</ul>
																		
						</div>	
												
					</div>
				</div>
			</div>			
					</div>
					

					<div class="btnpnl">
						<label> <input type="submit" id="BtnSA" name="Submit" class="btn" value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" onclick="return valdiateChangeVillageSubmitSave();" /> </label>
						<label> <input type="submit" id="BtnSAP"  name="Submit" class="btn" value="<spring:message code="Label.PUBLISH" htmlEscape="true"></spring:message>" onclick="return valdiateChangeVillageSubmitPub();" /> </label> 
						<%-- <label>	<input 	type="button" name="Submit3" class="btn" value=<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message> id="btnClear" onclick="javascript:location.href='modifyVillagechangebyId.htm?<csrf:token uri='modifyVillagechangebyId.htm'/>&villageId=${modifyVillageCmd.villageId}';"  /></label>         --%>
						<label> <input type="button" name="Submit3" class="btn" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
					</div>
				</div>
			</form:form>
			<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
		</div>
	</div>
</body>
</html>