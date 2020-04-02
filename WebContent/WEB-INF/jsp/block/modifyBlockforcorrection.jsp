<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%!String allowedNoOfAttachment = "5";
	String uploadLocation = "C:\\files";
	String allowedFileType = "gif,jpg,pdf,png";
	Long allowedTotalFileSize = 5L;
	Long allowedIndividualFileSize = 1L;
	String requiredTitle = "no";
	String uniqueTitle = "no";%>

<%!String contextPath;%>
<head>
<title><spring:message code="Label.MODIFYSUBDISTRICT" htmlEscape="true"></spring:message></title>
<script src="js/jquery.MultiFile.js" type="text/javascript"	language="javascript"></script>
<script src="js/attachedFiles.js" type="text/javascript" language="javascript"></script>
<script src="js/shiftsubdistrict.js"></script>
<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>

<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/disturbedEntities.js"></script>

<script src="<%=contextpthval %>/jquery-1.8.3.js"></script>
<script src="js/orderValidate.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>

	


<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>
<script src="js/dynCalendar.js" type="text/javascript"></script>
<script src="js/browserSniffer.js" type="text/javascript"></script>

<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>

<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>

<link rel="stylesheet" href="datepicker/demos.css" />
<link href="css/error.css" rel="stylesheet" type="text/css" />
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<link href="css/dynCalendar.css" rel="stylesheet" type="text/css" />
<link href="datepicker/calender.css" rel="stylesheet" type="text/css" />

<script>

$(document).ready(function(){
	/* var mandatoryFlag = isParseJson('${mandatoryFlag}');
	//alert(mandatoryFlag);
	if(mandatoryFlag){ */
		document.getElementById("EffectiveDate").disabled = true;
		//$('#EffectiveDate').prop('disabled',true);
	/* }	 */
}
);

var mandatoryFlag;
function hideAll()
	{
		mandatoryFlag='<c:out value="${mandatoryFlag}" escapeXml="true"></c:out>';
	 	$("#EffectiveFutureDate_error").hide();
		$("#OrderFutureDate_error").hide();
	 	$("#EffectiveDateBlank_error").hide();
		$("#aliasNameEngData_error").hide();
		$("#aliasNameLocData_error").hide();
		$("#OrderNoBlank_error").hide();
		$("#OrderNoDataValid_error").hide();
		$("#OrderDateBlank_error").hide();
		$("#OrderDateData_error").hide();
		$("#OrderDateValid_error").hide();
		$("#GuzpubDateValid_error").hide();
		$("#error_govorder").hide();
		$("#EffectiveDateData_error").hide();
		$("#sscode_error").hide();
		$("#headquterNameLocData_error").hide();
		$("#headquterNameEngData_error").hide();
		$("#GuzpubDateCompareOrderDate_error").hide();
		$("#GuzpubDateBlankOrderDate_error").hide();
		
	}


function setEffectiveDatetoOrderDate(orderDate)
{
	
	 //alert(orderDate);
	 if(orderDate!="" && orderDate!=undefined)
	 document.getElementById('EffectiveDate').value=orderDate;
}

validateBlockForm=function(){
	hideAll();
	var mandatory_error=false;
	var fielattach = document.getElementById('attachFile1').value;
    var filecount = document.getElementById('govtfilecount').value;  
    var error=false;	
	var aliasNameInEn=document.getElementById('txtaliasEnglish').value; 
    var aliasNameInLc=document.getElementById('txtAliaslocal').value; 
	var orderDate= document.getElementById('OrderDate').value; 
    var effectiveDate=document.getElementById('EffectiveDate').value; 
	var GuzpubDate=document.getElementById('gazPubDatecr').value; 
	var OrderNo=document.getElementById('OrderNo').value;
	var sscode=document.getElementById('txtSscode').value; 
	var HeadquterNameEng=document.getElementById('hquarterseng').value;
	var HeadquterNameLoc=document.getElementById('hquarterslocal').value;
	if(OrderNo!="" || orderDate!=""  || effectiveDate!="" || GuzpubDate!="" || filecount>0 || fielattach!=0){
		if(OrderNo==""){
			$("#OrderNoBlank_error").show();
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
		$("#OrderNoBlank_error").show();
		mandatory_error=true;
		error=true;
		}
	}

	if(OrderNo!=""){
		//alert(OrderNo);
		if(!validateOrderNoGeneral(OrderNo)){
			$("#OrderNoDataValid_error").show();
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
	if (!validateEntityEnglishNameData(aliasNameInEn, '#aliasNameEngData_error')){
		error = true;
	}
	if (!validateEntityNameLocalData(aliasNameInLc, '#aliasNameLocData_error')){
		error = true;
	}
	if (!validateEntityEnglishNameData(HeadquterNameEng, '#headquterNameEngData_error')){
		error = true;
	}
	if(!validateEntityNameLocalData(HeadquterNameLoc, '#headquterNameLocData_error')){
		error = true;
	}
		
	if(!validateEntityAlphaNumbericData(sscode,"#sscode_error")){
		error=true; 
	}
	if(mandatoryFlag==true){
		if (!fielattach.length && filecount == 0) {
			$("#error_govorder").show();
			
			mandatory_error=true;
			error=true;
	    }
	}
	if(mandatory_error==true){
		showClientSideError();
	}
	if(error==true){
		return false;
	}else{
		document.getElementById("EffectiveDate").disabled = false;
		$('input[name=Submit]').attr("disabled",true);
		$('#frmModifySubDistrict').attr('action', "modifyBlockCrAction.htm?<csrf:token uri='modifyBlockCrAction.htm'/>").submit();	
		return true;
	}

};

function validateBlock()
{  
	if(gisNodesMismatch()){
		var flag=checkLatitudeLongitudeRange();
		if(flag){
			
			validateBlockForm();
		}
	}
	else{
		$("#cAlert").html("<spring:message code='Error.lengthmismatch' htmlEscape='true'/>");
		$("#cAlert").dialog({
			title : '<spring:message htmlEscape="true"  code="Label.MODIFYBLOCK"></spring:message>',
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


function validateDatetoFuture(id,diverror)
{
	var entityDate=document.getElementById(id).value;
	 $(diverror).hide();
	 if(!compareDateforFutureDDMMYYY(entityDate)){ 
			$(diverror).show();
			document.getElementById(id).value="";
			
			
	 }
	 
}


	function validateEffectiveDatecompOrderDate(orderdateId, effectivedateID, diverror) {
		var orderDate = document.getElementById(orderdateId).value;
		var effectiveDate = document.getElementById(effectivedateID).value;
		if (orderDate != "") {
			$(diverror).hide();

			if (compareDateYYMMDD(orderDate, effectiveDate, 'dd-mm-yyyy')) {
				$(diverror).show();
				document.getElementById(effectivedateID).value = orderDate;

			}
		}

	}

	if (window.addEventListener) {
		window.addEventListener("load", hideAll, false);
	} else if (window.attachEvent) {
		window.attachEvent("onload", hideAll);
	} else if (window.onLoad) {
		window.onload = hideAll;
	}
	
	
	
</script>
	</head>

	<body
		onload='onLoadSelectDisturbed();chkCorrectionOnLoad();'
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
									<Div class="success"></div>
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
				<h3 class="subtitle"><spring:message code="Label.MODIFYBLOCK" htmlEscape="true"></spring:message></h3>
				<ul class="listing">
					<%-- this link is not working <li>
						<a href="#" class="frmhelp"><spring:message code="Label.HELP" htmlEscape="true"></spring:message> </a>				
					</li> --%>
				</ul>
			</div>
			
			
			<div class="frmpnlbrdr">
				<form:form action="modifyBlockCrAction.htm" method="POST" commandName="modifyBlockCmd" id="frmModifySubDistrict" name="frmModifySubDistrict" enctype="multipart/form-data">
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyBlockCrAction.htm"/>" />
					<input type="hidden" id="govtfilecount" name="govtfilecount" value="<c:out value='${govtfilecount}' escapeXml='true'></c:out>"></input>
					<input type="hidden" id="mapfilecount" name="mapfilecount" value="<c:out value='${mapfilecount}' escapeXml='true'></c:out>"></input>
					<input type="hidden" name="warningFlag" value="<c:out value='${pageWarningEntiesFlag}' escapeXml='true'></c:out>"/>   
					
					<div id="cat">
						
						<c:forEach 	var="listBlockDetails" varStatus="listBlockDetailsRow" items="${modifyBlockCmd.listBlockDetails}">
							<div class="frmpnlbg">
								<div class="frmtxt">
									<div class="frmhdtitle">
										<spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message>
									</div>
									<div class="block">
										<ul class="blocklist">
											<li>
												<label>
													<spring:message	code="Label.NAMEOFNEWBLOCKENGLISH" htmlEscape="true"></spring:message> 
												</label><br />
												<label>
													 <spring:bind 	path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].blockNameEnglish">
														   <input 	type="text" class="frmfield" readonly="readonly"
																  	name="<c:out value="${status.expression}" />"
																  	value="<c:out value="${status.value}" escapeXml="true" />"/> 
		
													</spring:bind> 
													<spring:bind 	path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].blockCode">
															<input 	type="hidden" class="frmfield"
																	name="<c:out value="${status.expression}"/>"
																	value="<c:out value="${status.value}" escapeXml="true"/>" />
													</spring:bind> 
												</label>
												<div class="errormsg"></div>
											</li>
											<li>
												<c:if test="${pageWarningEntiesFlag==true}">
													<label>
													<spring:message	code="Label.WARNINGFLAGSTATUS" 
																	htmlEscape="true"/> 
													</label><br/>
													<img src="images/ylw_flg.png" width="13" height="9" />
											   </c:if>
											</li>
											<li>
												<label>
													<spring:message code="Label.NAMEOFNEWBLOCKLOCAL" htmlEscape="true"></spring:message>
												</label><br />
												<label> 
													<spring:bind 	path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].blockNameLocal">
															<input 	type="text" class="frmfield" readonly="readonly"
																	name="<c:out value="${status.expression}"/>"
																	value="<c:out value="${status.value}"  escapeXml="true"/>"/>
													</spring:bind> 
												</label>
												<div class="errormsg"></div>
											</li>
											<li>
												<label>
													<spring:message code="Label.ALIASENGLISH" htmlEscape="true"></spring:message>
												 </label><br />
												<label>
													 <spring:bind path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].aliasEnglish">
															<input type="text" 
																   class="frmfield"  
																   id="txtaliasEnglish"
																   name="<c:out value="${status.expression}"/>"
																   value="<c:out value="${status.value}" escapeXml="true"/>"/> 
													</spring:bind> 
												</label>
													<div class="errormsg" 
														 id="aliasNameEngData_error">
														<spring:message htmlEscape="true" 
																		code="Error.BlockAliasNameEngData"/>
													</div>
													
												<div class="errormsg"></div>
											</li>
											<li>
												<label>
														<spring:message code="Label.ALIASLOCAL" htmlEscape="true"></spring:message> 
													</label><br />
													<label> 
														<spring:bind 	path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].aliasLocal">
															<input 		type="text" 
																		id="txtAliaslocal" class="frmfield" 
																		name="<c:out value="${status.expression}"/>"
																		value="<c:out value="${status.value}" escapeXml="true"/>" /> 
														</spring:bind>
													</label>
													<div 	class="errormsg" 
											 				id="aliasNameLocData_error">
															<spring:message htmlEscape="true" code="Error.BlocktAliasNameLocalData"/>
													</div>
													<div class="errormsg"></div>
											</li>
											<li>
												<label>
													<spring:message code="Label.DISTRICTHEADQUARTERENGLISH" htmlEscape="true"></spring:message>
												 </label><br /> 
												 <label>
														<spring:bind 	path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].headquarterName">
															<input 		type="text" 
																		id="hquarterseng" 
																		class="frmfield" 
																		name="<c:out value="${status.expression}"/>"
																		value="<c:out value="${status.value}" escapeXml="true"/>" />
														</spring:bind>
												</label>
												<label>
														<div class="errormsg" id="headquterNameEngData_error">
																	<spring:message htmlEscape="true" code="Error.HeadquterNameEngData"/>
										  				</div>
														<form:errors path="headquarterName" cssClass="errormsg" htmlEscape="true"/>
												</label>
											</li>
											<li>
												<label>
												 		<spring:message code="Label.SUBDISTRICTHEADQUARTERLOCAL" htmlEscape="true"></spring:message>
												 </label><br /> 
												 <label>
														<spring:bind 	path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].headquarterNameLocal">
															<input 		type="text" 
																		id="hquarterslocal" 
																		class="frmfield" 
																		name="<c:out value="${status.expression}"/>"
																		value="<c:out value="${status.value}" escapeXml="true"/>" />
														</spring:bind>
														
														<div 	class="errormsg" 
												 				id="headquterNameLocData_error">
																<spring:message htmlEscape="true" code="Error.HeadquterNameLocalData"/>
										     			</div>
														<form:errors path="headquarterNameLocal" cssClass="errormsg" htmlEscape="true"/>  
														
														<spring:bind 		path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].headquarterCode">
																<input 		type="hidden"
																			name="<c:out value="${status.expression}"/>"
																			value="<c:out value="${status.value}"/>" escapeXml="true" />
														</spring:bind>  
														<spring:bind		path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].blockCode">
																<input 		type="hidden"
																			name="<c:out value="${status.expression}"/>"
																			value="<c:out value="${status.value}"/>" escapeXml="true" />
														</spring:bind> 
														<spring:bind 		path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].blockVersion">
																<input 		type="hidden"
																			name="<c:out value="${status.expression}"/>"
																			value="<c:out value="${status.value}"/>" escapeXml="true" />
														</spring:bind> 
												</label>
												<div class="errormsg"></div>
											</li>
											<li>
												<label>
														<spring:message code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message> 
													</label><br /> 
													<label>
														<spring:bind 		path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].ssCode">
															<input 			type="text" 
																			id="txtSscode" 
																			class="frmfield" 
																			maxlength="5" 
																			name="<c:out value="${status.expression}"/>"
																			value="<c:out value="${status.value}" escapeXml="true"/>" />
														</spring:bind> 
													</label> 
													<div class="errormsg" 
														 id="sscode_error">
															<spring:message htmlEscape="true" code="Error.SSCode"/>
										    		</div>
													<form:errors path="ssCode" cssClass="errormsg" htmlEscape="true"/>
													<div class="errormsg"></div>
											</li>
											<li>
												<spring:bind 	path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].mapCode">
															<input 		type="hidden"
																		name="<c:out value="${status.expression}"/>"
																		value="<c:out value="${status.value}"/>" escapeXml="true" />
														</spring:bind> 
														<spring:bind 	path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].blockCode">
															<input 		type="hidden"
																		name="<c:out value="${status.expression}"/>"
																		value="<c:out value="${status.value}"/>"  escapeXml="true"/>
														</spring:bind>
														<spring:bind 	path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].blockVersion">
															<input 		type="hidden"
																		name="<c:out value="${status.expression}"/>"
																		value="<c:out value="${status.value}"/>" escapeXml="true" />
														</spring:bind>
														<spring:bind 	path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].cordinate">
															<input 		type="hidden"
																		name="<c:out value="${status.expression}"/>"
																		value="<c:out value="${status.value}"/>" escapeXml="true" />
														</spring:bind>
														
														<spring:bind 	path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].warningFlag">
															<input 		type="hidden"
																		name="<c:out value="${status.expression}"/>"
																		value="<c:out value="${status.value}"/>" escapeXml="true" />
														</spring:bind>
											</li>
										</ul>
									</div>
			
								</div>
							</div>
							<div class="frmpnlbg">
								<div class="frmtxt">
									<div class="frmhdtitle">
										<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message>
									</div>
									<div class="block">
										<ul class="blocklist">
											<c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
												<li>
													<label>
											<spring:message code="Label.ORDERNO" 
															htmlEscape="true"></spring:message> 
											</label>
												<c:if test="${mandatoryFlag==true}"> <span class="errormsg">*</span></c:if>
												<br/>
											<label> 
											<spring:bind path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].orderNocr">
												  <input type="text" 
												      	 readonly="readonly"
												         class="frmfield"
														name="<c:out value="${status.expression}"/>"
														 value="<c:out value="${status.value}" escapeXml="true"/>" />
											</spring:bind>
												</li>
												<li>
													<label>
												<spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message> </label>
													<c:if test="${mandatoryFlag==true}">
														 <span class="errormsg">*</span>
													</c:if>	<br /> 
												<label> 
													
													<spring:bind path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].orderDatecr">
														  <input type="text" 
														         class="frmfield" 
														        readonly="readonly"
																 style="width: 100px"
																 name="<c:out value="${status.expression}"/>"
																 value="<c:out value="${status.value}" escapeXml="true"></c:out>"
																 htmlEscape="true" />
													</spring:bind> 
													</label>
												</li>
												<li>
													<label>
												<spring:message code="Label.EFFECTIVEDATE" 
																htmlEscape="true"/> 
												</label>
												<c:if test="${mandatoryFlag==true}"> 
													<span class="errormsg">*</span>
												</c:if>	<br /> 
												<label> 
												
													<spring:bind path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].ordereffectiveDatecr">
														  <input type="text" 
														        
														         readonly="readonly"
																 style="width: 100px"
																 name="<c:out value="${status.expression}"/>"
																value="<c:out value="${status.value}" escapeXml="true"></c:out>"
																htmlEscape="true" />
													</spring:bind>
												</li>
												<li>
													<form:hidden path="govtOrderConfig"  value="${govtOrderConfig}" id="hdngovtOrderConfig" />
												</li>
											</c:if>
											
											
											 <c:if test="${govtOrderConfig == 'govtOrderUpload'}">
											 	<li>
													<label>
													<spring:message code="Label.ORDERNO" 
																	htmlEscape="true"></spring:message> 
													</label>
														<c:if test="${mandatoryFlag==true}"> <span class="errormsg">*</span></c:if>
														<br/>
													<label> 
													<spring:bind path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].orderNocr">
														  <input type="text" 
														         maxlength="20" 
														         class="frmfield"
																 onkeypress="validateNumericAlphaKeys();" id="OrderNo"
																 onfocus="validateOnFocus('OrderNo');"
																 onblur="vlidateOnblur('OrderNo','1','15','c');"
																 name="<c:out value="${status.expression}"/>"
																 value="<c:out value="${status.value}" escapeXml="true"/>" />
													</spring:bind>
													<div class="errormsg" 
														 id="OrderNoBlank_error">
															<spring:message htmlEscape="true" 
																			code="error.required.ORDERNUM"/>
											       </div>
											        <div class="errormsg" 
											        	 id="OrderNoDataValid_error">
															<spring:message htmlEscape="true" 
																			code="error.valid.ORDERNO"/>
											       </div>
													
													<spring:bind path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].orderCodecr">
														  <input type="hidden"
																 name="<c:out value="${status.expression}"/>"
																 value="<c:out value="${status.value}"/>" escapeXml="true"/>
													</spring:bind> 
													</label> 
													<form:errors path="orderNocr" cssClass="errormsg" htmlEscape="true"/>											 		
											 	</li>
											 	<li>
													<label>
														<spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message> </label>
															<c:if test="${mandatoryFlag==true}">
																 <span class="errormsg">*</span>
															</c:if>	<br /> 
														<label> 
															<c:if test="${mandatoryFlag==true}">	
															<spring:bind path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].orderDatecr">
																  <input type="text" 
																         class="frmfield" 
																         id="OrderDate"
																		 style="width: 100px"
																		 onblur="vlidateOnblur('OrderDate','1','15','c');"
																		 onfocus="hideAll();setOrderDateforCorrection('OrderDate','#OrderDateData_error');"
																		 onchange="vaidatetOrderDateforCorrectionEntity(this.value,'#OrderDateData_error');"
																		 onkeypress="hideAll();"
																		 name="<c:out value="${status.expression}"/>"
																		 value="<c:out value="${status.value}" />"
																		 htmlEscape="true" />
															</spring:bind> 
															</c:if>
																
															<c:if test="${mandatoryFlag==false}">	
															<spring:bind path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].orderDatecr">
															      <input type="text" 
															      		class="frmfield" 
															      		id="OrderDate"
																		style="width: 100px"
																		onblur="vlidateOnblur('OrderDate','1','15','c');"
																		onfocus="hideAll();setOrderDateforCorrection('OrderDate','#OrderDateData_error');"
																		onchange="validateDatetoFuture('OrderDate','#OrderFutureDate_error');vaidatetOrderDateforCorrectionEntity(this.value,'#OrderDateData_error');"
																		onkeypress="hideAll();"
																		name="<c:out value="${status.expression}"/>"
																		value="<c:out value="${status.value}" escapeXml="true"></c:out>"
																		htmlEscape="true" />
															</spring:bind> 	
															</c:if>
															<div class="errormsg" 
																 id="OrderDateBlank_error">
																<spring:message htmlEscape="true" 
																				code="error.required.ORDERDATE"/>
												       		</div>
															<div class="errormsg" 
																 id="OrderFutureDate_error">
															<spring:message htmlEscape="true" 
																			code="error.INCORECT.ORDERDATE"/>
												      		 </div>
												  			<div class="errormsg" 
												  				 id="OrderDateValid_error">
																<spring:message htmlEscape="true" 
																				code="error.valid.DATEFORMAT"/>
												      		</div>
												 		 <div class="errormsg" 
												 		 	  id="OrderDateData_error">
																<spring:message htmlEscape="true" 
																				code="error.compare.ORDERDATE"/>
												        </div>
													</label>											 	
											 	</li>
											 	<li>
											 		<label>
														<spring:message code="Label.EFFECTIVEDATE" 
																		htmlEscape="true"/> 
														</label>
														<c:if test="${mandatoryFlag==true}"> 
															<span class="errormsg">*</span>
														</c:if>	<br /> 
														<label> 
														<c:if test="${mandatoryFlag==true}">
															<spring:bind path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].ordereffectiveDatecr">
																  <input type="text" 
																         id="EffectiveDate"  
																         readonly="readonly"
																		 style="width: 100px"
																		 name="<c:out value="${status.expression}"/>"
																		value="<c:out value="${status.value}" escapeXml="true"></c:out>"
																		htmlEscape="true" />
															</spring:bind>
														</c:if>
														<c:if test="${mandatoryFlag==false}">
															<spring:bind path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].ordereffectiveDatecr">
														    	  <input type="text" 
														    	  		 id="EffectiveDate" 
														    	  		 style="width: 128px"
																 		 class="frmfield"
																		 onfocus="validateOnFocus('EffectiveDate');"
														        		 onblur="vlidateOnblur('EffectiveDate','1','15','c');"
																		 onchange="validateDatetoFuture('EffectiveDate','#EffectiveFutureDate_error');validateEffectiveDatecompOrderDate('OrderDate','EffectiveDate','#EffectiveDateData_error');"
																 		 onkeypress="hideAll();"
																		 name="<c:out value="${status.expression}"/>"
																		 value="<c:out value="${status.value}" escapeXml="true" />" />
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
													    </label> 
													    <form:errors path="ordereffectiveDatecr"
																	 cssClass="errormsg" 
																	 htmlEscape="true"/>											 	
											 	</li>
											 	<li>
											 		<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
														<label>
														<spring:message code="Label.GAZPUBDATE" htmlEscape="true"/> 
														</label><br /> 
														<label>
														<spring:bind path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].gazPubDatecr">
															  <input type="text" 
															         class="frmfield" 
															         id="gazPubDatecr"
																	 style="width: 100px" 
																	 onchange="noOrderDataValid('gazPubDatecr','#GuzpubDateBlankOrderDate_error','OrderDate');compareGuzpubDatetoOrderDate('gazPubDatecr','#GuzpubDateCompareOrderDate_error','OrderDate')"
																	 onfocus="show_msg('gazPubDatecr');"
																	 onblur="validateGazPubDate1();"
																	 onkeyup="validateNumeric();"
																	 name="<c:out value="${status.expression}"/>"
																	 value="<c:out value="${status.value}" escapeXml="true"></c:out>"
																	 htmlEscape="true" />
													</spring:bind>
													</label>
													 <div class="errormsg" id="GuzpubDateValid_error">
															<spring:message htmlEscape="true" code="error.valid.DATEFORMAT"></spring:message>
												       </div>
												      <div class="errormsg" id="GuzpubDateBlankOrderDate_error">
															<spring:message htmlEscape="true" code="error.required.ORDERDATE"></spring:message>
												       </div>
												       
												        <div class="errormsg" id="GuzpubDateCompareOrderDate_error">
															<spring:message htmlEscape="true" code="error.compare.GuzpubDate"></spring:message>
												       </div>
													<form:errors path="gazPubDatecr" cssClass="errormsg" htmlEscape="true"></form:errors>
												  </c:if>
											 	</li>
											 	<li>
											 		<form:hidden path="govtOrderConfig" value="${govtOrderConfig}" id="hdngovtOrderConfig" />
											 	</li>
											 	
											 	<li>
													<%@ include file="../common/updateattach.jspf"%> 	
											 	</li>
											 	<li>	
											 		<div id="errBlankOrder" style="color: red;"></div>
											 	</li>
											 </c:if>
										</ul>
									</div>
								</div>
							</div>
								
						<c:if test="${count!=1}">
							<div class="frmpnlbg">
								<div class="frmtxt">
									<div class="frmhdtitle">
										<spring:message code="Label.GISNODES" htmlEscape="true"></spring:message>
									</div>
									<div align="center">
									<%@ include file="../common/modifyGis_nodes.jspf"%>
									</div>									
									<c:if test="${mapConfig eq 'false' and modifyBlockCmd.listBlockDetails[listBlockDetailsRow.index].cordinate!=null}">
										<script>
											var cordinates='${modifyBlockCmd.listBlockDetails[listBlockDetailsRow.index].cordinate}';
											buildLatitudeLongitude(cordinates);
										</script>
									</c:if>

								</div>
							</div>
							</c:if>
						</c:forEach>
					</div>
					<div class="btnpnl">
						<ul class="listing">
							<li>
								<label> 
										<input 	type="button" 
												name="Submit"
												class="btn"
												value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>"
												onclick="return validateBlock();" />
									 </label> 
									 <label>
									 <c:if test="${reqWarningFlag!=null}">
											<input type="button" name="Submit3" class="btn"
											       value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
												   onclick="javascript:location.href='viewResolveEntitiesinDisturbedStateLR.htm?<csrf:token uri='vviewResolveEntitiesinDisturbedStateLR.htm'/>&warningEntiesFlag=${reqWarningFlag}';" />
									</c:if>
									<c:if test="${reqWarningFlag==null}">
									
									 <%-- <input type="button" name="Submit3" class="btn" id="btnClear" 
											value="<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message>"
											onclick="javascript:location.href='modifySubdistrictCrbyId.htm?filterOption=1&districtCode=&<csrf:token uri='modifySubdistrictCrbyId'/>'"  /> --%>
										
											
										<input type="button" name="Submit3" class="btn"
											   value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
											onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'"  />
									
									</c:if>
									 </label>
							</li>
						</ul>
						<input type="hidden" name="warningEntiesFlag" value="<c:out value='${warningEntiesFlag}' escapeXml='true'></c:out>"/>
						<input type="hidden" name="type" value="<c:out value='${type}' escapeXml='true'></c:out>"/>
					</div>
				</form:form>
				<script src="/LGD/JavaScriptServlet"></script>
			</div>
		</div>
	</body>
</html>