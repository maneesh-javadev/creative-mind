<%@include file="../common/taglib_includes.jsp"%> 

<html>
<head>
<title>
	<spring:message code="Label.MODIFYSUBDISTRICT" htmlEscape="true"></spring:message>
</title>
<meta http-equiv="Content-Type" content="text/html;  charset=utf-8">

<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/resources-localbody/scripts/jquery.numeric.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script src="js/validation.js"></script>
<script src="js/orderValidate.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>
<link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<link rel="stylesheet" href="datepicker/demos.css" />


<script>

/* var checkDecimalPlaces = function (e){
	var maxPlaces = 3, integer = e.target.value.split('.')[0], mantissa = e.target.value.split('.')[1];
    if (typeof mantissa === 'undefined') {
        mantissa = '';
    }
    if (mantissa.length > maxPlaces) {
        e.target.value = integer + '.' + mantissa.substring(0, maxPlaces);
    }
};  
 */



 

$(document).ready(function(){
	
	
	/* var mandatoryFlag = $.parseJSON('${mandatoryFlag}');
	if(mandatoryFlag){ */
		$('#EffectiveDate').prop('disabled',true);
	/* }	 */
	
		
}); 
 
 



function validateDatetoFuture(id,diverror)
{
	var entityDate=document.getElementById(id).value;
	 $(diverror).hide();
	 if(!compareDateforFutureDDMMYYY(entityDate)){ 
			$(diverror).show();
			document.getElementById(id).value="";
			
			
	 }
	 
}

function validateEffectiveDatecompOrderDate(orderdateId,effectivedateID,diverror)
{
	 var orderDate=document.getElementById(orderdateId).value;
	 var effectiveDate=document.getElementById(effectivedateID).value;
	if(orderDate!="") 
		{
		$(diverror).hide();	
		
			if (compareDateYYMMDD(orderDate, effectiveDate, 'dd-mm-yyyy'))
		{
				$(diverror).show();
				document.getElementById(effectivedateID).value=orderDate;
				
			}
		}
	 
	 
}

function setEffectiveDatetoOrderDate(orderDate)
{
	
	 //alert(orderDate);
	 if(orderDate!="" && orderDate!=undefined)
	 document.getElementById('EffectiveDate').value=orderDate;
}

	

function validateStateCorrection()
{
	if(gisNodesMismatch()){
		if(checkLatitudeLongitudeRange()){
			hideAll();
			var mandatoryFlag = $.parseJSON('${mandatoryFlag}');
			var mandatory_error=false;
			var fielattach = document.getElementById('attachFile1').value;
		    var filecount = document.getElementById('govtfilecount').value;  
	        var error=false;	
			/* var aliasNameInEn=document.getElementById('txtAliasEnglish').value; 
		    var aliasNameInLc=document.getElementById('txtAliaslocal').value;  */
		    var sortNameEng=document.getElementById('sortName').value; 
		   //	var cecsus2011=document.getElementById('txtCensus').value; 
			var orderDate= document.getElementById('OrderDate').value; 
			if(mandatoryFlag=='false'){
				var effectiveDate=document.getElementById('EffectiveDate').value; 
			}
		   	var GuzpubDate=document.getElementById('gazPubDatecr').value; 
			var OrderNo=document.getElementById('OrderNo').value;
			/* var sscode=document.getElementById('txtSscode').value;  */
			var HeadquterNameEng=document.getElementById('hquarterseng').value;
			var HeadquterNameLoc=document.getElementById('hquarterslocal').value;
			if(OrderNo!="" || orderDate!=""  || effectiveDate!="" || GuzpubDate!="" || filecount>0 || fielattach!=0){
				if(OrderNo==""){
					$("#OrderNo_error").show();
					error=true;
				}
				if(orderDate==""){
					$("#OrderDateBlank_error").show();
					error=true;
				}
				if(effectiveDate==""){
					$("#EffectiveDateBlank_error").show();
					error=true;
				}
				if(filecount==0 && fielattach==0){
					$("#error_govorder").show();
					error=true;
				}
			}
			if(mandatoryFlag==true){
				if(OrderNo==""){
					$("#OrderNo_error").show();
					mandatory_error=true;
					error=true;
				}
			}
			if(OrderNo!=""){
				if(!validateOrderNoGeneral(OrderNo)){
					error=true;
				}
			}
			if(mandatoryFlag==true){
				if(orderDate==""){
					$("#OrderDateBlank_error").show();
					mandatory_error=true;
					error=true;
				}
			}
			if(orderDate!=""){
				if(!validateDateDDMMYYY(orderDate)){
					$("#OrderDateValid_error").show();
					error=true;
				}
			}
			
			if(GuzpubDate!=""){
				if(!validateDateDDMMYYY(GuzpubDate)){
					$("#GuzpubDateValid_error").show();
					error=true;
				}
			}
			/* if (!validateEntityEnglishNameData(aliasNameInEn, '#aliasNameEngData_error'))
				error = true;
			if (!validateEntityNameLocalData(aliasNameInLc, '#aliasNameLocData_error'))
				error = true; */
			if (!validateEntityEnglishNameData(sortNameEng, '#sortNameEngData_error'))
					error = true;
			if (!validateEntityEnglishNameData(HeadquterNameEng, '#headquterNameEngData_error'))
				error = true;
			if (!validateEntityNameLocalData(HeadquterNameLoc, '#headquterNameLocData_error'))
				error = true;
			/* if(!validateEntityNumbricData(cecsus2011,"#txtCensus_error"))
				error=true; */
			/*  if(!validateEntityAlphaNumbericData(sscode,"#sscode_error"))
				error=true;  */
			if(mandatoryFlag==true){
				if (!fielattach.length && filecount == 0) {
					$("#error_govorder").show();
					mandatory_error=true;
					error=true;
			    }
			}
		 
			if(mandatory_error==true)
				showClientSideError();
				
			if(error==true){
				return false;
			}
			else{
				$('input[name=Submit]').prop('disabled', true); 
				var mandatoryFlag = $.parseJSON('${mandatoryFlag}');
				if(mandatoryFlag){
					$('#EffectiveDate').prop('disabled',false);
				}
				$('#frmModifyState').attr('action', "modifyStateCrAction.htm?<csrf:token uri='modifyStateCrAction.htm'/>").submit();			
				return true;
			}
		}
	}else{
				$("#cAlert").html("<spring:message code='Error.lengthmismatch' htmlEscape='true'/>");
				$("#cAlert").dialog({
					title : '<spring:message htmlEscape="true"  code="Label.MODIFYSTATE"></spring:message>',
					resizable : false,
					height : 200,
					modal : true,
					buttons : {
						Ok : function() {
							$(this).dialog("close");
							return false;
						}
					}
				});
	}		  
}
	
	var mandatoryFlag;	
	function hideAll()
	{
		
		mandatoryFlag=$.parseJSON('${mandatoryFlag}');
		$("#EffectiveFutureDate_error").hide();
		$("#OrderFutureDate_error").hide();
	 	$("#EffectiveDateBlank_error").hide();
		//$("#txtCensus_error").hide();
		$("#OrderNoBlank_error").hide();
		$("#OrderNoDataValid_error").hide();
		$("#OrderDateBlank_error").hide();
		$("#OrderDateData_error").hide();
		$("#OrderDateValid_error").hide();
		$("#GuzpubDateValid_error").hide();
		$("#error_govorder").hide();
		$("#EffectiveDateData_error").hide();
		$("#headquterNameLocData_error").hide();
		$("#headquterNameEngData_error").hide();
		$("#sortNameEngData_error").hide();
		$("#GuzpubDateBlankOrderDate_error").hide();
		$("#GuzpubDateCompareOrderDate_error").hide();
		
		
		
	}
	
	
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

	<body
		onload='clearOrdernoErrors();onLoadSelectDisturbed();chkCorrectionOnLoad();'
		onkeypress="disableCtrlKeyCombination(event);"
		onkeydown="disableCtrlKeyCombination(event); ">


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
									<center><c:out value="${param.family_msg}" escapeXml="false"></c:out></center>
								</div></td>
						</tr>
					</table>

				</c:if>

				<c:if test="${!empty family_error}">

					<table>
						<tr>
							<td rowspan="2"><Div class="failur"></div>
							</td>

							<td><center>
									<div class="helpMsgHeader" style="width: 275px;">
										<b>Failure Message</b>
									</div>
								</center>
							</td>
						</tr>
						<tr>
							<td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="false"></c:out></div>
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
								<spring:message code="error.blank.commonAlert" htmlEscape="true"></spring:message>
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


		<div id="frmcontent">
			<div class="frmhd">
						<h3 class="subtitle"><spring:message code="Label.MODIFYSTATE" htmlEscape="true"></spring:message></h3>
										 <ul id="showhelp" class="listing">
					 				       <%--//these links are not working  <li>
					 				          <a href="#" class="frmhelp"><spring:message	code="Label.HELP" htmlEscape="true"></spring:message> </a>                     
					 				        </li>
					 				      --%>
					 			        </ul>
			
				<%-- <label for="id"><spring:message code=></spring:message></label> --%>
			</div>
			<div class="frmpnlbrdr">
				<%-- <form:form action="modifySubDistrictCrAction.htm?disturb=${disturb}" --%>
				<form:form action="modifyStateCrAction.htm"
					method="POST" commandName="modifyStateCmd"
					id="frmModifyState" enctype="multipart/form-data">
					<input type="hidden" name="<csrf:token-name/>"
						value="<csrf:token-value uri='modifySubDistrictCrAction.htm'/>" />
						<input type="hidden" name="warningflag" value="${pageWarningEntiesFlag}"/>   
					 <input type="hidden" id="govtfilecount" name="govtfilecount" value="${govtfilecount}">
					 </input>
					 <input type="hidden" id="mapfilecount" name="mapfilecount" value="${mapfilecount}">
					 </input>
					<div id="cat">
						<c:forEach var="listStateDetails"
							varStatus="listStateDetailsRow"
							items="${modifyStateCmd.listStateDetails}">
							<div class="frmpnlbg">
								<div class="frmtxt">
									<div class="frmhdtitle">
										<spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message>
									</div>
									
									<ul class="blocklist">
											<li><label for="statenameenglishfor"><spring:message
														code="Label.STATENAMEINENGLISH" htmlEscape="true"></spring:message> </label><br />
												<label> <spring:bind
														path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateNameEnglish">
														<input type="text" class="frmfield" readonly="readonly" id="statenameenglishfor"
															name="<c:out value="${status.expression}" />"
															value="<c:out value="${status.value}" escapeXml="false" />"
															/> <!-- onfocus="this.blur()" --> <!-- removed for correction - full edit functionality needed. -->

													</spring:bind> <spring:bind
														path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateCode">
														<input type="hidden" class="frmfield"
															name="<c:out value="${status.expression}"/>"
															value="<c:out value="${status.value}" escapeXml="false"/>" />
													</spring:bind> </label>
												<div class="errormsg"></div> 
												
												<c:if test="${pageWarningEntiesFlag==true}">
													<label><spring:message
												code="Label.WARNINGFLAGSTATUS" htmlEscape="true"></spring:message> </label><br/>
												<img src="images/ylw_flg.png" width="13" height="9" />
													</c:if>
												</li>
											<li><label for="statenameLocalfor"><spring:message
														code="Label.STATENAMEINLOCAL" htmlEscape="true"></spring:message> </label><br />
												<label> <spring:bind
														path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateNameLocal">
														<input type="text" class="frmfield" readonly="readonly" id="statenameLocalfor"
															name="<c:out value="${status.expression}"/>"
															value="<c:out value="${status.value}"  escapeXml="false"/>"
															/> <!-- onfocus="this.blur()" --> <!-- removed for correction - full edit functionality needed. -->

													</spring:bind> </label>
												<div class="errormsg"></div>
											</li>
											<li>
												<label for="sortName"><spring:message
												code="Label.SHORTNAMEENGLISH" htmlEscape="true"></spring:message>
									</label><br /> <label> <spring:bind
												path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].shortName" >
												<input type="text" id="sortName"
													onkeypress="hideAll();" maxlength="2"
													class="frmfield"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false"/>"  />
											</spring:bind> </label>
											 <div class="errormsg" id="sortNameEngData_error">
												<spring:message htmlEscape="true" code="Error.StateSortNameEngData"></spring:message>
									       </div>
											<form:errors path="shortName" class="errormsg" htmlEscape="true"></form:errors> 
										<div class="errormsg"></div>
											
											</li>
											<li>			
													<label for="hquarterseng"><spring:message
														code="Label.HEADQUARTERSTATEEN" htmlEscape="true"></spring:message> </label><br /> <label>
													<spring:bind
														path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].headquarterName">
														<input type="text" id="hquarterseng" class="frmfield" 
															name="<c:out value="${status.expression}"/>"
															value="<c:out value="${status.value}" escapeXml="false"/>"
															onkeypress=hideAll();
															 />
													</spring:bind>
													</label>
													 <div class="errormsg" id="headquterNameEngData_error">
												<spring:message htmlEscape="true" code="Error.StateHeadquarterNameEngData"></spring:message>
									           </div>
													<label>
													
													<form:errors path="headquarterName" cssClass="errormsg" htmlEscape="true"/>
											</li>
											<li>
											
														 <label for="hquarterslocal"><spring:message
														code="Label.HEADQUARTERSTATELOCL" htmlEscape="true"></spring:message> </label><br /> <label>
													<spring:bind
														path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].headquarterNameLocal">
														<input type="text" id="hquarterslocal" class="frmfield" 
															name="<c:out value="${status.expression}"/>"
															value="<c:out value="${status.value}" escapeXml="false"/>"
															onkeypress=hideAll();
															 />
													</spring:bind>
													 <div class="errormsg" id="headquterNameLocData_error">
												<spring:message htmlEscape="true" code="Error.StateHeadquarterNameLocalData"></spring:message>
									       </div>
													<form:errors path="headquarterNameLocal" cssClass="errormsg" htmlEscape="true"/> 
													
												<spring:bind
														path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].headquarterCode">
														<input type="hidden"
															name="<c:out value="${status.expression}"/>"
															value="<c:out value="${status.value}"/>" escapeXml="false" />
													</spring:bind>  <spring:bind
														path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateCode">
														<input type="hidden"
															name="<c:out value="${status.expression}"/>"
															value="<c:out value="${status.value}"/>" escapeXml="false" />
													</spring:bind> <spring:bind
														path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateVersion">
														<input type="hidden"
															name="<c:out value="${status.expression}"/>"
															value="<c:out value="${status.value}"/>" escapeXml="false" />
													</spring:bind> 
													
													<spring:bind
														path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].slc">
														<input type="hidden"
															name="<c:out value="${status.expression}"/>"
															value="<c:out value="${status.value}"/>" escapeXml="false" />
													</spring:bind> 
													</label>
												<div class="errormsg"></div>
											
											</li>
											<%-- <li><label for="txtCensus"><spring:message
														code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message> </label><br /> <label>
													<spring:bind
														path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].census2011Code">
														<input type="text" id="txtCensus" class="frmfield" 
															maxlength="9" onkeypress="validateNumericKeys(event)"
															name="<c:out value="${status.expression}"/>"
															value="<c:out value="${status.value}" escapeXml="false"/>"
															 />
													</spring:bind>
													 <div class="errormsg" id="txtCensus_error">
												<spring:message htmlEscape="true" code="Error.Census2011Code"></spring:message>
									       </div>
													 </label> <form:errors path="census2011Code" cssClass="errormsg" htmlEscape="true"/>
												<div class="errormsg"></div>
												
												</li> --%>
											<li><table width="100%" class="tbl_no_brdr" align="center">

													<tr>
														<spring:bind
															path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].mapLandregionCode">
															<input type="hidden"
																name="<c:out value="${status.expression}"/>"
																value="<c:out value="${status.value}"/>" escapeXml="false" />
														</spring:bind>
														<spring:bind
															path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateCode">
															<input type="hidden"
																name="<c:out value="${status.expression}"/>"
																value="<c:out value="${status.value}"/>"  escapeXml="false"/>
														</spring:bind>
														<spring:bind
															path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateVersion">
															<input type="hidden"
																name="<c:out value="${status.expression}"/>"
																value="<c:out value="${status.value}"/>" escapeXml="false" />
														</spring:bind>
														 <spring:bind
															path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].cordinate">
															<input type="hidden"
																name="<c:out value="${status.expression}"/>"
																value="<c:out value="${status.value}"/>" escapeXml="false" />
														</spring:bind>
													 <spring:bind
															path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].warningflag">
															<input type="hidden"
																name="<c:out value="${status.expression}"/>"
																value="<c:out value="${status.value}"/>" escapeXml="false"/>
														</spring:bind>
													</tr>
												</table></li>
											<li></li>
											<li></li>
											
									
									</ul>

								</div>
							</div>
							 <div class="frmpnlbg">
								<div class="frmtxt">
									<div class="frmhdtitle">
										<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message>
									</div>
						
						
						
						<ul class="blocklist">
							<c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
								<li><label for="ordernofor"><spring:message
											code="Label.ORDERNO" htmlEscape="true"></spring:message> </label>
											<c:if test="${mandatoryFlag==true}"> <span class="errormsg">*</span></c:if>
											<br/>
											
											 
									<label> <spring:bind
											path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].orderNocr">
											<input 	type="text" 
												   	class="frmfield"
												   	readonly="readonly"
												   	id="ordernofor"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />"
													style="width: 128px"  />

										</spring:bind></label>
										
										<spring:bind
											path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].orderCodecr">
											<input type="hidden"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="false" />" />
										</spring:bind>  <form:errors path="orderNocr" cssClass="errormsg" htmlEscape="true"/>
									<div class="errormsg"></div>
								</li>
								
								<li>
								
									<label for="orderDateFor"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message>
								</label>
									<c:if test="${mandatoryFlag==true}"> <span class="errormsg">*</span></c:if>							<br /> <label> 
																
									<spring:bind
											path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].orderDatecr">
											<input type="text" 
													readonly="readonly" 
													style="width: 128px"
													class="frmfield" 
													id="orderDateFor"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />"  />
										</spring:bind> 
										
										
								</li>
								
								<li>
								
										<label for="efectiveDateFor"><spring:message
											code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> </label>
											<c:if test="${mandatoryFlag==true}"> <span class="errormsg">*</span></c:if>	
									<br /> <label> 
									
									<spring:bind
											path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].ordereffectiveDatecr">
											<%--   <c:if test="${listStateDetails.ordereffectiveDatecr >}">	 --%>
											<input type="text"  style="width: 128px"
												class="frmfield"
												readonly="readonly"	
												id="efectiveDateFor"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="false" />" />

										</spring:bind>
								</li>
								
								<li>
								
										<form:hidden path="govtOrderConfig"
										value="${govtOrderConfig}" id="hdngovtOrderConfig" />
								</li>
								
							</c:if>
							
								<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
								
										<li>
											<label><spring:message
											code="Label.ORDERNO" htmlEscape="true"></spring:message> </label>
											<c:if test="${mandatoryFlag==true}"> <span class="errormsg">*</span></c:if>
											<br/>
											
											 
									<label> <spring:bind
											path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].orderNocr">
											<input type="text" maxlength="60" class="frmfield"
												onkeypress="return validateaGovtOrderNOforModify(event);"  id="OrderNo"
												onfocus="validateOnFocus('OrderNo');"
											    onblur="vlidateOrderNo('OrderNo','1','60');"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="false" />"
												style="width: 128px"  />

										</spring:bind></label>
										<form:errors path="orderNocr" cssClass="errormsg" htmlEscape="true"/>
									<div id="OrderNo_error" class="errormsg"><spring:message code="error.required.ORDERNUM" htmlEscape="true"></spring:message></div>
									<div id="OrderNo_msg" class="errormsg"><spring:message code="error.required.ORDERINPUTTYPE" text="Please Enter AlphaNumerics Space . / - ( ) Only" htmlEscape="true"/></div>
										
										
											<span class="errormsg" id="OrderNo_error"></span>
																			
										 <spring:bind
											path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].orderCodecr">
											<input type="hidden"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="false" />" />
										</spring:bind>  <form:errors path="orderNocr" cssClass="errormsg" htmlEscape="true"/>
									<div class="errormsg"></div>
										
										</li>
										
										
										<li>
										
													<label><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message>
								</label>
									<c:if test="${mandatoryFlag==true}"> <span class="errormsg">*</span></c:if>							<br /> <label> 
									<c:if test="${mandatoryFlag==true}">								
									<spring:bind
											path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].orderDatecr">
											<input type="text" id="OrderDate" style="width: 128px"
												class="frmfield" 
												readonly="readonly"
												onblur="vlidateOnblur('OrderDate','1','15','c');"
												onfocus="hideAll();setOrderDateforCorrection('OrderDate','#OrderDateData_error');"
												onchange="vaidatetOrderDateforCorrectionEntity(this.value,'#OrderDateData_error');"
												onkeypress="hideAll();"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="false" />"  />
										</spring:bind> 
										</c:if>
										<c:if test="${mandatoryFlag==false}">								
									<spring:bind
											path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].orderDatecr">
											<input type="text" id="OrderDate" style="width: 128px"
												class="frmfield" 
												readonly="readonly"
												onfocus="hideAll();setOrderDateforCorrection('OrderDate','#OrderDateData_error');"
												onblur="vlidateOnblur('OrderDate','1','15','c');"
												onchange="validateDatetoFuture('OrderDate','#OrderFutureDate_error');vaidatetOrderDateforCorrectionEntity(this.value,'#OrderDateData_error');"
												onkeypress="hideAll();"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="false" />"  />
										</spring:bind> 
										</c:if>
										</label><span class="errormsg" id="OrderDate_error"></span><form:errors path="orderDatecr"
										cssClass="errormsg" htmlEscape="true" />
										 <div class="errormsg" id="OrderDateBlank_error">
												<spring:message htmlEscape="true" code="error.required.ORDERDATE"></spring:message>
									       </div>
									 
									  <div class="errormsg" id="OrderFutureDate_error">
												<spring:message htmlEscape="true" code="error.INCORECT.ORDERDATE"></spring:message>
									       </div>
									  
									  <div class="errormsg" id="OrderDateValid_error">
												<spring:message htmlEscape="true" code="error.valid.DATEFORMAT"></spring:message>
									       </div>
									 
									 <div class="errormsg" id="OrderDateData_error">
												<spring:message htmlEscape="true" code="error.compare.ORDERDATE"></spring:message>
									       </div>
									<div class="errormsg"></div>
										
										</li>
										
								<li> <label><spring:message
											code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> </label>
											<c:if test="${mandatoryFlag==true}"> <span class="errormsg">*</span></c:if>	
									<br /> <label> 
									<c:if test="${mandatoryFlag==true}">
									<spring:bind
											path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].ordereffectiveDatecr">
											
											<input type="text" id="EffectiveDate" style="width: 128px"
												class="frmfield"
												readonly="readonly"	
												onfocus="validateOnFocus('EffectiveDate');"
										        onblur="vlidateOnblur('EffectiveDate','1','15','c');"
												onkeypress="validateNumeric();"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="false" />" />

										</spring:bind>
										</c:if>
											<c:if test="${mandatoryFlag==false}">
											<spring:bind
											path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].ordereffectiveDatecr">
											
											<input type="text" id="EffectiveDate" style="width: 128px"
												class="frmfield"
												readonly="readonly"
												onfocus="validateOnFocus('EffectiveDate');"
										        onblur="vlidateOnblur('EffectiveDate','1','15','c');"
												onchange="validateDatetoFuture('EffectiveDate','#EffectiveFutureDate_error');validateEffectiveDatecompOrderDate('OrderDate','EffectiveDate','#EffectiveDateData_error');"
												onkeypress="hideAll();"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="false" />" />

										</spring:bind>
										
										
												
											</c:if>
											
											<div class="errormsg" id="EffectiveDateData_error">
												<spring:message htmlEscape="true" code="error.compare.EFFECTIVEDATE"></spring:message>
									       </div>
											
											<div class="errormsg" id="EffectiveDateBlank_error">
												<spring:message htmlEscape="true" code="ordereffectiveDate.required"></spring:message>
									       </div>
											<div class="errormsg" id="EffectiveFutureDate_error">
												<spring:message htmlEscape="true" code="error.valid.EFFECTIVEFUTUREDATE"></spring:message>
									       </div>
									<div class="errormsg"></div>
										 </label><span class="errormsg" id="EffectiveDate_error"></span> <form:errors
										path="ordereffectiveDatecr" cssClass="errormsg" />
									<div class="errormsg"></div>
							</li>
							
							<li>     
							
									<label><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>
									</label><br /> <label> <spring:bind
												path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].gazPubDatecr">
												<input type="text" id="gazPubDatecr" style="width: 128px"
													class="frmfield" 
													onchange="noOrderDataValid('gazPubDatecr','#GuzpubDateBlankOrderDate_error','OrderDate');compareGuzpubDatetoOrderDate('gazPubDatecr','#GuzpubDateCompareOrderDate_error','OrderDate')"
													readonly="readonly"
													onkeyup="validateNumeric();"
													onkeypress="hideAll();"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />"
													 />

											</spring:bind> </label> 
											 <div class="errormsg" id="GuzpubDateValid_error">
												<spring:message htmlEscape="true" code="error.valid.DATEFORMAT"></spring:message>
									       </div>
									      <div class="errormsg" id="GuzpubDateBlankOrderDate_error">
												<spring:message htmlEscape="true" code="error.required.ORDERDATE"></spring:message>
									       </div>
									       
									        <div class="errormsg" id="GuzpubDateCompareOrderDate_error">
												<spring:message htmlEscape="true" code="error.compare.GuzpubDate"></spring:message>
									       </div>
											<form:errors path="gazPubDatecr" cssClass="errormsg" />
										<div class="errormsg"></div>
							
							   </li>
							
									<li><form:hidden path="govtOrderConfig"
										value="${govtOrderConfig}" id="hdngovtOrderConfig" /> </li>
										
								<li>
								
										<%@ include	file="../common/updateattach.jspf"%>
								</li>
								
								</c:if>
						
						   
						
						</ul>
						
						
						
	
								</div>
							</div>
						
							<div class="frmpnlbg">
								<div class="frmtxt">
									<div class="frmhdtitle">
										<spring:message code="Label.GISNODES" htmlEscape="true"></spring:message>
									</div>
								
									<div align="center">
									<%@ include file="../common/modifyGis_nodes.jspf"%>
									</div>									
									<c:if test="${mapConfig eq 'false' and modifyStateCmd.listStateDetails[listStateDetailsRow.index].cordinate!=null}">
										<script>
											var cordinates='${modifyStateCmd.listStateDetails[listStateDetailsRow.index].cordinate}';
											buildLatitudeLongitude(cordinates);
										</script>
									</c:if>
								</div>
							</div>
								
						</c:forEach>
					</div>
					<div class="btnpnl">
								<label>
									<input type="button" name="Submit" id="Submit" class="btn" value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" onclick="return validateStateCorrection();" />
								</label> 
								
								<c:if test="${reqWarningFlag!=null}">
									<input type="button" name="Submit3" class="btn" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:location.href='viewResolveEntitiesinDisturbedStateLR.htm?<csrf:token uri='vviewResolveEntitiesinDisturbedStateLR.htm'/>&warningEntiesFlag=${reqWarningFlag}';" />
								</c:if>
								<c:if test="${reqWarningFlag==null}">
									<input type="button" name="Submit3" class="btn" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"  />
								</c:if>

					</div>
				
				</form:form>
				
				<script src="/LGD/JavaScriptServlet"></script>
			</div>
		</div>
	</body>
</html>