<%@include file="../common/taglib_includes.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="Label.MODIFYDISTRICT" htmlEscape="true"></spring:message>
</title>


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





<script>
	dwr.engine.setActiveReverseAjax(true);
</script>
<script>

$(document).ready(function(){
	/* var mandatoryFlag = isParseJson('${mandatoryFlag}');
	//alert(mandatoryFlag); */
	if(mandatoryFlag){
		document.getElementById("EffectiveDate").disabled = true;
		//$('#EffectiveDate').prop('disabled',true);
	/* }	 */
	}
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
			if ( Date.parse ( effectiveDate ) < Date.parse ( orderDate ) ) {
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

		
	

	function validateDistrictCorrection()
	{  
		if(gisNodesMismatch()){
			if(checkLatitudeLongitudeRange()){
				var check =validateOrderDetails();
				if(!check){
					return false;
				}else{
				hideAll();
				var mandatory_error=false;
				var filecount = document.getElementById('govtfilecount').value;  
		        var error=false;	
				var aliasNameInEn=document.getElementById('txtAliasEnglish').value; 
			    var aliasNameInLc=document.getElementById('txtAliaslocal').value; 
				/* var cecsus2011=document.getElementById('txtCensus').value;  */
				var sscode=document.getElementById('txtSscode').value; 
				var HeadquterNameEng=document.getElementById('hquarterseng').value;
				var HeadquterNameLoc=document.getElementById('hquarterslocal').value;
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
						if(!validateOrderNoGeneral(OrderNo))
							{
							error=true;
							}
					}
					if(mandatoryFlag==true){
						if(orderDate=="")
						{
						$("#OrderDateBlank_error").show();
						mandatory_error=true;
						error=true;
						}
					}
					if(orderDate!=""){
						if(!validateDateDDMMYYY(orderDate))
							{
							$("#OrderDateValid_error").show();
							error=true;
							}
					}
				
					if(GuzpubDate!=""){
						if(!validateDateDDMMYYY(GuzpubDate))
							{
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
				if(error==true)
					return false;
				else{
						$( "#EffectiveDate" ).prop( "disabled", true );
					    $( 'form[id=frmModifyDistrict]' ).attr('action', "modifyDistrictCrAction.htm?<csrf:token uri='modifyDistrictCrAction.htm'/>");
						$( 'form[id=frmModifyDistrict]' ).attr('method','post');
						$( 'form[id=frmModifyDistrict]' ).submit();
				
					return true;
				}
				
			}
		  }
		}else{
			customAlert("<spring:message code='Error.lengthmismatch' htmlEscape='true'/>");
			
		}		  	
		
	
	}
		

	
	var mandatoryFlag;	
	function hideAll()
	{
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
	
	function LoadPage()
	{
		//cordinateList='${modifyDistrictCmd.coordinates}';
		govtOrderConfig='${govtOrderConfig}';
		
		hideAll();
		//if(cordinateList!='')
		//setgisnodes();
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

  <div id="helpDiv" class="helpMessage" >
 <div class="helpheader"><h4>Help Message</h4></div>
<div id="helpMsgText" class="helptext"></div> 
</div>		
	
	<!-- <div id="helpDiv" class="helpMessage">
			<div class="helpheader">
				<h4>Help Message</h4>
			</div>
			<div id="helpMsgText" class="helptext"></div>
		</div> -->

	<div id="frmcontent">

		<div class="frmhd">

			<h3 class="subtitle">
				<label><spring:message code="Label.MODIFYDISTRICT" htmlEscape="true"></spring:message></label>
			</h3>
			<ul id="showhelp" class="listing">				
				<%--//these links are not working <li>
						<a href="#" class="frmhelp"><spring:message
								code="Button.HELP" htmlEscape="true"></spring:message> </a>
				</li>
 --%>
			</ul>
		</div>

		
		<!-- added by kirandeep on 18/06/2014 -->
		<div class="frmpnlbrdr">
			<form:form action="modifyDistrictCrAction.htm"  onsubmit="Submit.disabled = true; return true;"
				method="POST" commandName="modifyDistrictCmd" id="frmModifyDistrict"
				enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>" value='<csrf:token-value uri="modifyDistrictCrAction.htm"/>' />
					<input type="hidden" name="warningflag" value="${pageWarningEntiesFlag}"/>   
					 <input type="hidden" id="govtfilecount" name="govtfilecount" value="${govtfilecount}">
					 </input>
					 <input type="hidden" id="mapfilecount" name="mapfilecount" value="${mapfilecount}">
					 </input>
					<input type="hidden" name="type" value="${type}"/>
					<form:input  type="hidden" path="coordinates" />
					
				
					<c:forEach var="listDistrictDetails"
								varStatus="listDistrictDetailsRow"
								items="${modifyDistrictCmd.listDistrictDetails}">
						
				<div class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<label><spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message>
							</label>
						</div>
					
						
						<div >
							<ul class="blocklist">
								<li>
									
									<ul class="listing">
										<li>											
												<label for="txtdistrictnameinenglish"><spring:message
												code="Label.DISTRICTNAMEINENGLISH" htmlEscape="true"></spring:message> </label><br/>
												<label > <spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].districtNameEnglish">
												<input id="txtdistrictnameinenglish" type="text" class="frmfield" readonly="readonly" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="false" />" />
												 <!-- onfocus="this.blur()" --> <!-- removed for correction - full edit functionality needed. -->
												</spring:bind> </label>
												
												<div class="float_right">
													<c:if test="${pageWarningEntiesFlag==true}">
														<label><spring:message code="Label.WARNINGFLAGSTATUS" htmlEscape="true"></spring:message> </label><br/>
														<img src="images/ylw_flg.png" width="13" height="9" />
													</c:if>
												</div>
										</li>
											<li>	<div class="errormsg"></div></li>
										
									</ul>
								</li>
								<li>
										<label for="txtdistrictnameinlocal"><spring:message
												code="Label.DISTRICTNAMEINLOCAL" htmlEscape="true"></spring:message> </label><br /> <label>
											<spring:bind
												path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].districtNameLocal">
												<input id="txtdistrictnameinlocal" type="text" class="frmfield" readonly="readonly" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="false" />" /> 
												<!-- onfocus="this.blur()" --> <!-- removed for correction - full edit functionality needed. -->
											</spring:bind> </label>
										<div class="errormsg"></div>
									
								</li>
								<li>
										<label for="txtAliasEnglish"><spring:message code="Label.DISTRICTALIASENGLISH" htmlEscape="true"></spring:message> </label><br />
										<label> <spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].aliasEnglish">
												<input type="text" class="frmfield" id="txtAliasEnglish" maxlength="50" name="<c:out value="${status.expression}"/>" onkeypress="hideAll();" value="<c:out value="${status.value}" escapeXml="false" />" /> 
												<!-- onfocus="this.blur()" --> <!-- removed for correction - full edit functionality needed. -->
											</spring:bind> </label>
											 <div class="errormsg" id="aliasNameEngData_error">
												<spring:message htmlEscape="true" code="Error.DistrictAliasNameEngData"></spring:message>
									       </div>
										<div class="errormsg"></div>
									
								</li>
								<li>
										<label for="txtAliaslocal"><spring:message
												code="Label.DISTRICTALIASLOCAL" htmlEscape="true"></spring:message> </label><br /> <label>
											<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].aliasLocal">
												<input type="text" class="frmfield"  id="txtAliaslocal"  maxlength="50" name="<c:out value="${status.expression}"/>" onkeypress="hideAll();" value="<c:out value="${status.value}" escapeXml="false" />" /> 
												<!-- onfocus="this.blur()" --> <!-- removed for correction - full edit functionality needed. -->
											</spring:bind> </label>
											 <div class="errormsg" id="aliasNameLocData_error">
												<spring:message htmlEscape="true" code="Error.DistrictAliasNameLocalData"></spring:message>
									       </div>
										<div class="errormsg"></div>
									
								</li>
								<li>
										<label for="hquarterseng">
										<spring:message code="Label.DISHEADQUARTERS" htmlEscape="true"></spring:message>  
										</label><br /> <label>
													<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].headquarterName">
														<input type="text" id="hquarterseng" class="frmfield"  maxlength="50" onkeypress="hideAll();" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="false"/>" />
													</spring:bind>
													</label>
											<div class="errormsg" id="headquterNameEngData_error">
												<spring:message htmlEscape="true" code="Error.HeadquterNameEngData"></spring:message>
									       	</div>
										
													
													<form:errors path="headquarterName" cssClass="errormsg" htmlEscape="true"/>
												
								 </li>
												
								<li>
												
												 <label for="hquarterslocal">
												 <spring:message
														code="Label.DISLOCHEADQUARTERSLOCAL" htmlEscape="true"></spring:message>
												 </label> <br /> <label>
													<spring:bind
														path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].headquarterNameLocal">
														<input type="text" id="hquarterslocal" class="frmfield"  maxlength="50" onkeypress="hideAll();"
															name="<c:out value="${status.expression}"/>"
															value="<c:out value="${status.value}" escapeXml="false"/>"
															 />
													</spring:bind></label>
													<div class="errormsg" id="headquterNameLocData_error">
														<spring:message htmlEscape="true" code="Error.HeadquterNameLocalData"></spring:message>
									       			</div>
													<form:errors path="headquarterNameLocal" cssClass="errormsg" htmlEscape="true"/> 																																	
													
										<spring:bind
												path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].headquarterCode">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}"/>" />
											</spring:bind> <spring:bind
												path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].headquarterVersion">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}"/>" />
											</spring:bind> <spring:bind
												path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].districtCode">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}"/>" />
											</spring:bind> 
											<spring:bind
												path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].districtVersion">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}"/>" />
											</spring:bind> </label>
											<spring:bind
												path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].cordinate">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}"/>" />
											</spring:bind>
											<spring:bind
												path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].warningflag">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}"/>" />
											</spring:bind> 
										
											<form:errors path="headquarterName" class="errormsg" htmlEscape="true"></form:errors>
										<div class="errormsg"></div>
									
								</li>

								<%-- <li>
											<label for="txtCensus"><spring:message
												code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message> </label><br /> <label>
											<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].census2011Code">
												<input type="text" id="txtCensus" class="frmfield" maxlength="9"
													
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false"/>"
													htmlEscape="true" />
											</spring:bind> </label>
											 <div class="errormsg" id="txtCensus_error">
												<spring:message htmlEscape="true" code="Error.Census2011Code"></spring:message>
									       </div>
											<form:errors path="census2011Code" class="errormsg" htmlEscape="true"></form:errors>
										
								</li> --%>
								<li>
									<label for="txtSscode"><spring:message
												code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message> </label><br /> <label>
											<spring:bind
												path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].sscode">
												<input type="text" id="txtSscode" maxlength="5"
													class="frmfield" 
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />"
													htmlEscape="true" />
											</spring:bind> 
											
											</label>
											 <div class="errormsg" id="sscode_error">
												<spring:message htmlEscape="true" code="Label.statespecificcodealphanumeric"></spring:message>
									       </div> 
												<form:errors path="sscode" class="errormsg" htmlEscape="true"></form:errors>
										<div class="errormsg"></div>
									
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
															
					<div >
						<ul class="blocklist">
					   		 <c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
								
									<li>

										<label for="txtorderno"><spring:message
											code="Label.ORDERNO" htmlEscape="true"></spring:message> </label>
											<c:if test="${mandatoryFlag==true}"> <span class="errormsg">*</span></c:if>
											<br/>
											
											 
										<label for="txtorderno"> <spring:bind
											path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].orderNocr">
											<input 	id="txtorderno" 
													type="text" 
												   	class="frmfield"
												   	readonly="readonly"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />"
													style="width: 128px"  />

										</spring:bind></label>
										
										<spring:bind
											path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].orderCodecr">
											<input type="hidden"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="false" />" />
										</spring:bind>  <form:errors path="orderNocr" cssClass="errormsg" htmlEscape="true"/>
									<div class="errormsg"></div>
								
							</li>
							<li>
								<label for="txtorderdate"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message>
								</label>
									<c:if test="${mandatoryFlag==true}"> <span class="errormsg">*</span></c:if>							<br /> <label> 
																
									<spring:bind
											path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].orderDatecr">
											<input  id="txtorderdate" 
													type="text" 
													readonly="readonly" 
													style="width: 128px"
													class="frmfield" 
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />"  />
										</spring:bind> 
										
										
								

							</li>
							<li>
								<label for="txteffectivedate"><spring:message
											code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> </label>
											<c:if test="${mandatoryFlag==true}"> <span class="errormsg">*</span></c:if>	
									<br /> <label> 
									
									<spring:bind
											path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].ordereffectiveDatecr">
											<%--   <c:if test="${listStateDetails.ordereffectiveDatecr >}">	 --%>
											<input 	id="txteffectivedate" 
													type="text"  
													style="width: 128px"
													class="frmfield"
													readonly="readonly"	
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
								
								<label for="OrderNo"><spring:message
											code="Label.ORDERNO" htmlEscape="true"></spring:message> </label>
											<c:if test="${mandatoryFlag==true}"> <span class="errormsg">*</span></c:if>
											<br/>
											
											 
									<label> <spring:bind
											path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].orderNocr">
											<input	type="text" maxlength="60" class="frmfield"
													onkeypress="return validateaGovtOrderNOforModify(event);"  id="OrderNo"
													onfocus="validateOnFocus('OrderNo');"
											    	onblur="vlidateOrderNo('OrderNo','1','60');"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />"
													style="width: 128px"  />

										</spring:bind></label>
										<div id="OrderNo_error" class="errormsg"><spring:message code="error.required.ORDERNUM" htmlEscape="true"></spring:message></div>
									<div id="OrderNo_msg" class="errormsg"><spring:message code="error.required.ORDERINPUTTYPE" text="Please Enter AlphaNumerics Space . / - ( ) Only" htmlEscape="true"/></div>
										
									<span class="errormsg" id="OrderNo_error"></span>
																			
										<spring:bind
											path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].orderCodecr">
											<input type="hidden"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="false" />" />
										</spring:bind>  <form:errors path="orderNocr" cssClass="errormsg" htmlEscape="true"/>
									<div class="errormsg"></div>
								
							</li>
							<li>
								<label for="OrderDate"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message>
								</label>
									<c:if test="${mandatoryFlag==true}"> <span class="errormsg">*</span></c:if>							<br /> <label> 
									<c:if test="${mandatoryFlag==true}">								
									<spring:bind
											path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].orderDatecr">
											<input type="text" id="OrderDate" style="width: 128px"
												readonly="readonly"
												class="frmfield" 
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
											path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].orderDatecr">
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
							<li>
									<label for="EffectiveDate"><spring:message
											code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> </label>
											<c:if test="${mandatoryFlag==true}"> <span class="errormsg">*</span></c:if>	
									 <br /><label> 
									<c:if test="${mandatoryFlag==true}">
									<spring:bind
											path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].ordereffectiveDatecr">
											<%--   <c:if test="${listStateDetails.ordereffectiveDatecr >}">	 --%>
											<input type="text" id="EffectiveDate" style="width: 128px"
												class="frmfield"
												disabled="true"
												onfocus="validateOnFocus('EffectiveDate');"
										        onblur="vlidateOnblur('EffectiveDate','1','15','c');"
												onkeypress="validateNumeric();"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="false" />" />

										</spring:bind>
										</c:if>
											<c:if test="${mandatoryFlag==false}">
											<spring:bind
											path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].ordereffectiveDatecr">
											<%--   <c:if test="${listStateDetails.ordereffectiveDatecr >}">	 --%>
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
									<label for="gazPubDatecr"><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>
									</label><br /> <label> <spring:bind
												path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].gazPubDatecr">
												<input type="text" id="gazPubDatecr" style="width: 128px"
													readonly="readonly"
													class="frmfield" 
													onkeyup="validateNumeric();"
													onchange="noOrderDataValid('gazPubDatecr','#GuzpubDateBlankOrderDate_error','OrderDate');compareGuzpubDatetoOrderDate('gazPubDatecr','#GuzpubDateCompareOrderDate_error','OrderDate')"
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
							
							<li>
							
								<form:hidden path="govtOrderConfig"
										value="${govtOrderConfig}" id="hdngovtOrderConfig" />
								
							</li>
						
							<li>									
									<%@ include file="../common/updateattach.jspf"%>
							</li>
							</c:if>
						</ul>
					</div>
						
						
						
						
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
									<c:if test="${modifyDistrictCmd.listDistrictDetails[listDistrictDetailsRow.index].cordinate!=null}">
										<script>
											var cordinates='${modifyDistrictCmd.listDistrictDetails[listDistrictDetailsRow.index].cordinate}';
											buildLatitudeLongitude(cordinates);
										</script>
									</c:if>



						</div>
			</div>
			</c:forEach>
			
				<div class="btnpnl">
											
									<label><input type="button" name="Submit" class="btn" value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" onclick="return validateDistrictCorrection();" /> </label> 
									<label>
									<c:if test="${reqWarningFlag!=null}">
										<input type="button" name="Submit3" class="btn" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:location.href='viewResolveEntitiesinDisturbedStateLR.htm?<csrf:token uri='vviewResolveEntitiesinDisturbedStateLR.htm'/>&warningEntiesFlag=${reqWarningFlag}';" />
									</c:if>
									<c:if test="${reqWarningFlag==null}">
										<input type="button" name="Submit3" class="btn" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"  />
									</c:if>									
									</label>
											
				</div>
			</form:form>
			<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
		</div>
	</div>
</body>
</html>












