<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%!String allowedNoOfAttachment = "1";
	String uploadLocation = "C:\\files";
	String allowedFileType = "gif,jpg,pdf,png";
	Long allowedTotalFileSize = 5L;
	Long allowedIndividualFileSize = 1L;
	String requiredTitle = "no";
	String uniqueTitle = "no";%>

<%!String contextPath;%>
<head>

<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrDistrictService.js'></script>
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
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<title><spring:message code="Label.MODIFYSUBDISTRICT" htmlEscape="true"></spring:message></title>

<script type="text/javascript">
$(document).ready(function(){
	var mandatoryFlag = isParseJson('${mandatoryFlag}');
	//alert(mandatoryFlag);
	//if(mandatoryFlag){
		document.getElementById("EffectiveDate").disabled = true;
		//$('#EffectiveDate').prop('disabled',true);
	 //}	
}
); 


var mandatoryFlag;
function hideAll()
	{
		mandatoryFlag='${mandatoryFlag}';
		
		$("#OrderNo_msg").hide();
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
		$("#headquterNameLocData_error").hide();
		$("#headquterNameEngData_error").hide();
		$("#GuzpubDateCompareOrderDate_error").hide();
		$("#GuzpubDateBlankOrderDate_error").hide();
		$("#error_gisnodeblank").hide();
		$("#error_gisnodelatirange").hide();
		$("#error_gisnodelongirange").hide();
		$("#error_gisnodellatiformat").hide();
		$("#error_gisnodelongiformat").hide();
		
	}


function setEffectiveDatetoOrderDate(orderDate)
{
	
	 //alert(orderDate);
	 if(orderDate!="" && orderDate!=undefined)
	 document.getElementById('EffectiveDate').value=orderDate;
}

function validateSubdistrict()
	{  
	if(gisNodesMismatch()){
		if(checkLatitudeLongitudeRange()){
			
				hideAll();
				var mandatory_error=false;
			    var filecount = document.getElementById('govtfilecount').value;  
		        var error=false;	
				var aliasNameInEn=document.getElementById('txtaliasEnglish').value; 
			    var aliasNameInLc=document.getElementById('txtAliaslocal').value; 
				//var cecsus2011=document.getElementById('txtCensus').value; 
				var HeadquterNameEng=document.getElementById('hquarterseng').value;
				var HeadquterNameLoc=document.getElementById('hquarterslocal').value;
				var sscode=document.getElementById('txtSscode').value; 
				if(govtOrderConfig=='govtOrderUpload'){
					var orderDate= document.getElementById('OrderDate').value; 
				    var effectiveDate=document.getElementById('EffectiveDate').value; 
					var GuzpubDate=document.getElementById('gazPubDatecr').value; 
					var OrderNo=document.getElementById('OrderNo').value;
					var fielattach = document.getElementById('attachFile1').value;
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
				
					if(mandatoryFlag==true){
						if (!fielattach.length && filecount == 0) {
							$("#error_govorder").show();
							mandatory_error=true;
							error=true;
			    		}
					}
					if(mandatory_error==true)
						showClientSideError();
				}
				if (!validateEntityEnglishNameData(aliasNameInEn, '#aliasNameEngData_error'))
					error = true;
				if (!validateEntityNameLocalData(aliasNameInLc, '#aliasNameLocData_error'))
					error = true;
				if (!validateEntityEnglishNameData(HeadquterNameEng, '#headquterNameEngData_error'))
					error = true;
				if (!validateEntityNameLocalData(HeadquterNameLoc, '#headquterNameLocData_error'))
					error = true;
				/* if(!validateEntityNumbricData(cecsus2011,"#txtCensus_error"))
					error=true; */
				 if(!validateEntityAlphaNumbericData(sscode,"#sscode_error"))
					error=true; 
				 if(error==true){
					return false;
				  }else{
					document.getElementById("EffectiveDate").disabled = false;
					$('input[name=Submit]').attr("disabled",true);
					$('#frmModifySubDistrict').attr('action', "modifySubDistrictCrAction.htm?<csrf:token uri='modifySubDistrictCrAction.htm'/>").submit();
					return true;
				 }
						
				}
			
		}else{
			$("#cAlert").html("<spring:message code='Error.lengthmismatch' htmlEscape='true'/>");
			$("#cAlert").dialog({
				title : '<spring:message code="Label.MODIFYSUBDISTRICT" htmlEscape="true"></spring:message>',
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
	
function LoadPage()
{
	cordinateList='<c:out value="${modifySubDistrictCmd.cordinate}" escapeXml="true"></c:out>';
	govtOrderConfig='<c:out value="${govtOrderConfig}" escapeXml="true"></c:out>';
	/* alert(cordinateList);
	alert(govtOrderConfig); */
	hideAll();
	if(cordinateList!='')
	setgisnodes();
}

if ( window.addEventListener ) { 
    window.addEventListener( "load",LoadPage, false );
 }
 else 
    if ( window.attachEvent ) { 
       window.attachEvent( "onload", LoadPage );
 } else 
       if ( window.onLoad ) {
          window.onload = LoadPage;
 }

	
	

</script>
</head>

<body onload='clearOrdernoErrors()'>
	
	<div id="helpDiv" class="helpMessage">
		<div class="helpheader">
			<h4>Help Message</h4>
		</div>
		<div id="helpMsgText" class="helptext"></div>
	</div>


	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message code="Label.MODIFYSUBDISTRICT" htmlEscape="true"></spring:message></h3>
			<ul class="listing">
				<%--//these links are not working <li>
					<a href="#" class="frmhelp"><spring:message code="Label.HELP" htmlEscape="true"></spring:message></a>
				</li> --%>
			</ul>
		</div>




	<div class="frmpnlbrdr">
		<form:form action="modifySubDistrictCrAction.htm" method="POST" commandName="modifySubDistrictCmd" id="frmModifySubDistrict" enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="modifySubDistrictCrAction.htm"/>" />
				<input type="hidden" id="govtfilecount" name="govtfilecount"
					value="<c:out value='${govtfilecount}'/>" />
				<input type="hidden" id="mapfilecount" name="mapfilecount"
					value="<c:out value='${mapfilecount}'/>"/>
				<input type="hidden" name="warningFlag"
					value="<c:out value='${pageWarningEntiesFlag}'/>"/>
				<form:input type="hidden" path="cordinate" />

				<div id="cat">
					<c:forEach var="listSubdistrictDetails" varStatus="listSubdistrictDetailsRow" items="${modifySubDistrictCmd.listSubdistrictDetails}">
						<div class="frmpnlbg">
							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message>
								</div>
								<div class="block">
									<ul class="blocklist">
										<li>
											<label><spring:message
													code="Label.SUBDISTRICTNAMEENGLISH" htmlEscape="true" /> </label><br />
											<label> <spring:bind
													path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictNameEnglish">
													<input type="text" class="frmfield" readonly="readonly"
														name="<c:out value="${status.expression}" />"
														value="<c:out value="${status.value}" escapeXml="true" />" />
												</spring:bind> <spring:bind
													path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictCode">
													<input type="hidden" class="frmfield"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"/>" />
												</spring:bind>
											</label>
										</li>
										<li>
											<c:if test="${pageWarningEntiesFlag==true}">
												<label> <spring:message
														code="Label.WARNINGFLAGSTATUS" htmlEscape="true" />
												</label>
												<br />
												<img src="images/ylw_flg.png" width="13" height="9" />
											</c:if>
										</li>
										<li>
											<label> <spring:message code="Label.SUBDISTRICTNAMELOCAL" htmlEscape="true" />
											</label><br /> <label> <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictNameLocal">
											<input type="text" class="frmfield" readonly="readonly" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"  escapeXml="true"/>" />
											</spring:bind>
										</label>
										</li>
										<li>
											<label> <spring:message code="Label.SUBDISTRICTALIASENGLISH" htmlEscape="true" />
											</label><br /> <label> <spring:bind
											path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].aliasEnglish">
											<input type="text" class="frmfield" id="txtaliasEnglish"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" escapeXml="true"/>" />
											</spring:bind>
											<div class="errormsg" id="aliasNameEngData_error">
											<spring:message htmlEscape="true"
											code="Error.SubdistrictAliasNameEngData" />
											</div>
											</label>
										</li>
										<li>
											<label> <spring:message
											code="Label.SUBDISTRICTALIASLOCAL" htmlEscape="true" />
											</label><br /> <label> <spring:bind
											path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].aliasLocal">
											<input type="text" id="txtAliaslocal" class="frmfield"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="true"/>" />
											</spring:bind>
											</label>
												<div class="errormsg" id="aliasNameLocData_error">
													<spring:message htmlEscape="true"
											code="Error.SubdistrictAliasNameLocalData" />
											</div>
										</li>
										<li>
											<label> <spring:message
											code="Label.HEADQUARTERS" htmlEscape="true" />
											</label><br /> <label> <spring:bind
											path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].headquarterName">
											<input type="text" id="hquarterseng" class="frmfield"
												onkeypress="hideAll();"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" escapeXml="true"/>" />
											</spring:bind>
											</label>
												<div class="errormsg" id="headquterNameEngData_error">
													<spring:message htmlEscape="true"
											code="Error.HeadquterNameEngData" />
											</div> <label> <form:errors path="headquarterName"
											cssClass="errormsg" htmlEscape="true" />
											</label>
										</li>
										<li>
												<label> <spring:message
													code="Label.HEADQUARTERSLOCAL" htmlEscape="true" />
												</label><br /> <label> <spring:bind
													path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].headquarterNameLocal">
													<input type="text" id="hquarterslocal" class="frmfield"
														onkeypress="hideAll();"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"/>" />
												</spring:bind>
												<div class="errormsg" id="headquterNameLocData_error">
													<spring:message htmlEscape="true"
														code="Error.HeadquterNameLocalData" />
												</div> <form:errors path="headquarterNameLocal"
													cssClass="errormsg" htmlEscape="true" />
												</label> <spring:bind
												path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].headquarterCode">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}"/>" escapeXml="true" />
												</spring:bind> <spring:bind
												path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictCode">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}"/>" escapeXml="true" />
												</spring:bind> <spring:bind
												path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictVersion">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}"/>" escapeXml="true" />
												</spring:bind> <spring:bind
												path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].tlc">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}"/>" escapeXml="true" />
											</spring:bind>
										</li>
										<%-- <li>
											<label> <spring:message
													code="Label.CENSUSCODE2011" htmlEscape="true" />
											</label><br /> <label> <spring:bind
													path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].census2011Code">
													<input type="text" id="txtCensus" class="frmfield"
														maxlength="9" onkeypress="validateNumericKeys(event)"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"/>" />
												</spring:bind>
											</label>
											<div class="errormsg" id="txtCensus_error">
												<spring:message htmlEscape="true"
													code="Error.Census2011Code" />
											</div> <form:errors path="census2011Code" cssClass="errormsg" htmlEscape="true" />
										</li> --%>
										<li>
											<label> <spring:message
													code="Label.STATESPECIFICCODE" htmlEscape="true" />
											</label><br /> <label> <spring:bind
													path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].sscode">
													<input type="text" id="txtSscode" class="frmfield"
														maxlength="5" name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"/>" />
												</spring:bind>
											</label>
											<div class="errormsg" id="sscode_error">
												<spring:message htmlEscape="true" code="Error.SSCode" />
											</div> <form:errors path="sscode" cssClass="errormsg"
												htmlEscape="true" />
										</li>
									</ul>
									<ul class="blocklist">
										<li>
											<spring:bind
												path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].mapLandregionCode">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" />"
													escapeXml="true" />
											</spring:bind>
											<spring:bind
												path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictCode">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}"/>"
													escapeXml="true" />
											</spring:bind>
											<spring:bind
												path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictVersion">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}"/>"
													escapeXml="true" />
											</spring:bind>

											<spring:bind
												path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].district_code">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}"/>"
													escapeXml="true" />
											</spring:bind>
											<spring:bind
												path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].district_version">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}"/>"
													escapeXml="true" />
											</spring:bind>
											<spring:bind
												path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].cordinate">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}"/>"
													escapeXml="true" />
											</spring:bind>
											<spring:bind
												path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].warningFlag">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}"/>"
													escapeXml="true" />
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
										<label> <spring:message
												code="Label.ORDERNO" htmlEscape="true"></spring:message>
										</label> <c:if test="${mandatoryFlag==true}">
											<span class="errormsg">*</span>
										</c:if> <br /> <label> <spring:bind
												path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderNocr">
												<input type="text" readonly="readonly" class="frmfield"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true"/>" />
											</spring:bind>
										</label>
									</li>
									<li>
										<label> <spring:message
												code="Label.ORDERDATE" htmlEscape="true"></spring:message>
										</label> <c:if test="${mandatoryFlag==true}">
											<span class="errormsg">*</span>
										</c:if> <br /> <label> <spring:bind
												path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderDatecr">
												<input type="text" class="frmfield" readonly="readonly"
													style="width: 100px"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true"></c:out>"
													htmlEscape="true" />
											</spring:bind>
										</label>
									</li>
									<li>
										<label> <spring:message
												code="Label.EFFECTIVEDATE" htmlEscape="true" />
										</label> <c:if test="${mandatoryFlag==true}">
											<span class="errormsg">*</span>
										</c:if> <br /> <label> <spring:bind
											path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].ordereffectiveDatecr">
											<input type="text" readonly="readonly"
												style="width: 100px"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="true"></c:out>"
												htmlEscape="true" />
										</spring:bind>
									</li>
									<li>
										<form:hidden path="govtOrderConfig" value="${govtOrderConfig}" id="hdngovtOrderConfig"  htmlEscape="true"/>
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
											<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderNocr">
											<input type="text" maxlength="60" class="frmfield" onkeypress="return validateaGovtOrderNOforModify(event);"  id="OrderNo"
												onfocus="helpMessage(this,'OrderNo_error');validateOnFocus('OrderNo');"
											    onblur="hideHelp();vlidateOrderNo('OrderNo','1','60');"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="true" />"
												style="width: 128px"  />

											</spring:bind></label>
											<%-- <div id="OrderNo_error" class="errormsg"><spring:message code="error.required.ORDERNUM" htmlEscape="true"></spring:message></div> --%>
										   <div id="OrderNo_msg" class="errormsg"><spring:message htmlEscape="true" code="error.valid.ORDERNO"/></div>
											<div class="errormsg"    	 id="OrderNoDataValid_error">
												<spring:message htmlEscape="true" code="error.valid.ORDERNO"/>
											   </div>
										<span class="errormsg" id="OrderNo_error"></span>
									       
											
											<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderCodecr">
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
												<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderDatecr">
													  <input type="text" 
													  		readonly="readonly"
													         class="frmfield" 
													         id="OrderDate"
															 style="width: 100px"
															 onblur="vlidateOnblur('OrderDate','1','15','c');"
															 onfocus="hideAll();setOrderDateforCorrection('OrderDate','#OrderDateData_error');"
															 onchange="vaidatetOrderDateforCorrectionEntity(this.value,'#OrderDateData_error');"
															 onkeypress="hideAll();"
															 name="<c:out value="${status.expression}"/>"
															 value="<c:out value="${status.value}" escapeXml="true"></c:out>"
															 htmlEscape="true" />
												</spring:bind> 
												</c:if>
													
												<c:if test="${mandatoryFlag==false}">	
												<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderDatecr">
												      <input type="text" 
												      		readonly="readonly"
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
											<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].ordereffectiveDatecr">
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
											<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].ordereffectiveDatecr">
										    	  <input type="text" 
										    	  		 id="EffectiveDate" 
										    	  		 readonly="readonly"
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
									<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
										<li>
											<label>
										<spring:message code="Label.GAZPUBDATE" htmlEscape="true"/> 
										</label><br /> 
										<label>
										<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].gazPubDatecr">
											  <input type="text" 
											         class="frmfield" 
											         readonly="readonly"
											         id="gazPubDatecr"
													 style="width: 100px" 
													 onfocus="show_msg('gazPubDatecr');"
													 onchange="noOrderDataValid('gazPubDatecr','#GuzpubDateBlankOrderDate_error','OrderDate');compareGuzpubDatetoOrderDate('gazPubDatecr','#GuzpubDateCompareOrderDate_error','OrderDate')"
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
										</li>
									</c:if>
										<li>
											<form:hidden path="govtOrderConfig" value="${govtOrderConfig}" id="hdngovtOrderConfig"  htmlEscape="true"/>
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
									<c:if test="${modifySubDistrictCmd.listSubdistrictDetails[listSubdistrictDetailsRow.index].cordinate!=null}">
										<script>
											var cordinates='${modifySubDistrictCmd.listSubdistrictDetails[listSubdistrictDetailsRow.index].cordinate}';
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
							<label> <input type="button" name="Submit"
								class="btn"
								value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>"
								onclick="return validateSubdistrict();" />
							</label> <c:if test="${reqWarningFlag!=null}">
								<input type="button" name="Submit3" class="btn"
									value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
									onclick="javascript:location.href='viewResolveEntitiesinDisturbedStateLR.htm?<csrf:token uri='vviewResolveEntitiesinDisturbedStateLR.htm'/>&warningEntiesFlag=${reqWarningFlag}';" />
							</c:if> 
							<c:if test="${reqWarningFlag==null}">
								<input type="button" name="Submit3" class="btn"
								value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
								onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'" />
							</c:if>
						</li>
					</ul>
				</div>
				<input type="hidden" name="warningEntiesFlag" value="<c:out value='${warningEntiesFlag}'/>"/>
				<input type="hidden" name="type" value="<c:out value='${type}'/>"/>
			</form:form>
				<script src="/LGD/JavaScriptServlet"></script>
			</div>
		</div>
	</body>
</html>