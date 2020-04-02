<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%!String allowedNoOfAttachment = "1";
	String uploadLocation = "C:\\files";
	String allowedFileType = "gif,jpg,pdf,png";
	Long allowedTotalFileSize = 5L;
	Long allowedIndividualFileSize = 1L;
	String requiredTitle = "no";
	String uniqueTitle = "no";%>

<%!String contextPath;%>
<head>
<title><spring:message code="Label.MODIFYVILLAGE" htmlEscape="true"></spring:message></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/resources-localbody/scripts/jquery.numeric.js"></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
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



<script type="text/javascript">
$(document).ready(function(){
	 var mandatoryFlag = isParseJson('${mandatoryFlag}');
	//alert(mandatoryFlag);*/
	//if(mandatoryFlag){ 
		document.getElementById("EffectiveDate").disabled = true;
		//$('#EffectiveDate').prop('disabled',true);
	//}	 
}); 

	/*  function getOperationType(val)
	 {
	 	document.getElementById('isChange').value = val;    	
	 } */
		var mandatoryFlag;
	 function hideAll()
		{
			mandatoryFlag='<c:out value="${mandatoryFlag}" escapeXml="true"></c:out>';
		 	$("#EffectiveFutureDate_error").hide();
			$("#OrderFutureDate_error").hide();
		 	$("#EffectiveDateBlank_error").hide();
			$("#aliasNameEngData_error").hide();
			$("#aliasNameLocData_error").hide();
			//$("#txtCensus_error").hide();
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
			
		}
	 
	 
	 function setEffectiveDatetoOrderDate(orderDate)
	 {
		
		 //alert(orderDate);
		 if(orderDate!="" && orderDate!=undefined)
		 document.getElementById('EffectiveDate').value=orderDate;
	 }
	 
	 function validateVillage()
		{  
		 if(gisNodesMismatch()){
				if(checkLatitudeLongitudeRange()){
					hideAll();
					var mandatory_error=false;
					var fielattach = document.getElementById('attachFile1').value;
				    var filecount = document.getElementById('govtfilecount').value;  
			        var error=false;	
					var aliasNameInEn=document.getElementById('txtaliasEnglish').value; 
				    var aliasNameInLc=document.getElementById('txtAliaslocal').value; 
					//var cecsus2011=document.getElementById('txtCensus').value; 
					var orderDate= document.getElementById('OrderDate').value; 
				    var effectiveDate=document.getElementById('EffectiveDate').value; 
					var GuzpubDate=document.getElementById('gazPubDatecr').value; 
					var OrderNo=document.getElementById('OrderNo').value;
					var sscode=document.getElementById('txtSscode').value; 
					/* alert("file length:"+fielattach.length);
					alert("filecount:"+filecount);
				
					
					alert("mandatory:"+mandatoryFlag);
					alert("filecount:"+document.getElementById('govtfilecount').value );  */
					
					
					if(OrderNo=="" || orderDate==""  || effectiveDate=="" || GuzpubDate=="" || filecount==0 || fielattach=="")
						{
						if(OrderNo=="")
							{
							$("#OrderNo_error").show();
							error=true;
							}
						if(orderDate=="")
							{
							$("#OrderDateBlank_error").show();
							error=true;
							}
						
						if(effectiveDate=="")
							{
							$("#EffectiveDateBlank_error").show();
							error=true;
							}
						
						if(filecount==0 && fielattach=="")
							{
							$("#error_govorder").show();
							error=true;
							}
							
						}
					
					if(mandatoryFlag==true)
						{
						if(OrderNo=="")
						{
						$("#OrderNoBlank_error").show();
						mandatory_error=true;
						error=true;
						}
						}
				
						if(OrderNo!="")
						{
						if(!validateOrderNoGeneral(OrderNo))
							{
							error=true;
							}
						}
					
					
						if(mandatoryFlag==true)
						{
							if(orderDate=="")
							{
							$("#OrderDateBlank_error").show();
							mandatory_error=true;
							error=true;
							}
						}
						
						if(orderDate!="")
						{
						if(!validateDateDDMMYYY(orderDate))
							{
							$("#OrderDateValid_error").show();
							error=true;
							}
						}
					
					if(GuzpubDate!="")
						{
						if(!validateDateDDMMYYY(GuzpubDate))
							{
							$("#GuzpubDateValid_error").show();
							error=true;
							}
						}
					
					
					
					
					if (!validateVillageNameEnglish(aliasNameInEn, '#aliasNameEngData_error'))
						error = true;
					if (!validateEntityNameLocalData(aliasNameInLc, '#aliasNameLocData_error'))
						error = true;
					
					
					/* if(!validateEntityNumbricData(cecsus2011,"#txtCensus_error"))
						error=true; */
					
				 if(!validateEntityAlphaNumbericData(sscode,"#sscode_error"))
						error=true; 
					if(mandatoryFlag==true)
						{
						if (!fielattach.length && filecount == 0) {
							$("#error_govorder").show();
							
							mandatory_error=true;
							error=true;
					    }
						}
					
					/* if(!validateGovtOrderDetails()){
						return false;
					} */
					if(mandatory_error==true)
						showClientSideError();
					
					if(error==true)
						return false;
					else{
						document.getElementById("EffectiveDate").disabled = false;
						$('input[name=Submit]').prop('disabled', true);
						$('#frmModifyVillage').attr('action', "modifyVillageCrAction.htm?<csrf:token uri='modifyVillageCrAction.htm'/>").submit();			
						return true;	   
					}
					 		
				}
		 }else{
			 $("#cAlert").html("<spring:message code='Error.lengthmismatch' htmlEscape='true'/>");
				$("#cAlert").dialog({
					title : '<spring:message htmlEscape="true"  code="Label.MANAGEVILLAGE"></spring:message>',
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
	 
	/*  function resetVillageFormCr() {

			//document.getElementById('path').value="";
			document.getElementById('gazPubDatecr').value = "";
			document.getElementById('EffectiveDate').value = "";
			document.getElementById('OrderDate').value = "";
			document.getElementById('OrderNo').value = "";
			document.getElementById('txtlatitude').value = "";
			document.getElementById('txtLongitude').value = "";
			document.getElementById('txtSscode').value = "";
			document.getElementById('txtCensus').value = "";
			

			for (var i=document.getElementById("addgisnodes").childNodes.length;i>0;i--)
				{
					document.getElementById("addgisnodes").removeChild(document.getElementById("addgisnodes").lastChild);
				}
				
		}
	 
	 
	 
	function doChange() {
		
		document.forms['frmModifyVillage'].action = "home.htm";
		document.forms['frmModifyVillage'].submit();
	}
	
	function loadPage() {
		var mypath = window.location.href;

		var mySplitResult = mypath.split("&");

		if (mySplitResult[1] != "") {
			var type = mySplitResult[1].replace("type=", "");

		}

	}
	function villageName() {
		if (document.getElementById("villagename").value == "") {
			document.getElementById("villagename_error").innerHTML = "Village Name in English is Required";
			$("#villagename_error").show();
			$("#villagename_msg").hide();
			$("#villagename").addClass("error_fld");
			$("#villagename").addClass("error_msg");
			return false;

		} else {
			$("#subdistrictname_msg").hide();
			return true;

		}
	}
 */
	function getGisNodes() {
		if (document.getElementById('hiddenCoordinates').value != '')
			document.getElementById('txtlatitude').value = document
					.getElementById('hiddenCoordinates').value;

		if (document.getElementById('txtlatitude').value != '') {
			var gisList = document.getElementById('txtlatitude').value
					.split(':');
			//i=gisList.length;

			document.getElementById('txtlatitude').value = gisList[0]
					.split(',')[0];
			document.getElementById('txtLongitude').value = gisList[0]
					.split(',')[1];

			for ( var k = 1; k < gisList.length; k++) {
				addgisnodes1();
				document.getElementById('lati' + k).value = gisList[k]
						.split(',')[0];
				document.getElementById('longi' + k).value = gisList[k]
						.split(',')[1];
			}
		}
	}
	
	function deleteRow(count){
		if(count!=2)
			{
		var id = 'trId'+count;
		var row = document.getElementById(id);
	    row.parentNode.removeChild(row);
			}
		
	}
	
	function validateSscode(value){	
		if(isNaN(value)){
			alert("State Specific Code must be integer");
			return false;
		}
	}
</script>

</head>
<body  onload="clearOrdernoErrors();" >
	<!-- onload='chkCorrectionOnLoad();' -->
	<!-- onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);" -->
	
	<div class="overlay" id="overlay1" style="display:none;"></div>
    <div class="box" id="box1">
            <a class="boxclose" id="boxclose1"></a>
			<div >
			<c:if test="${!empty param.family_msg}">
				<table>
								<tr><td rowspan="2"><center><Div class= "success"></div></center></td>
								
								<td ><div  class="helpMsgHeader" style="width:275px;"><h4>Success Message</h4></div></td></tr>
								<tr><td><div id="successMsg" class= "successfont" ><center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center></div>
					          </td></tr></table>
				
			</c:if>
				
				<c:if test="${!empty family_error}">
			
				<table>
				<tr><td rowspan="2"><Div class= "failur"></div></td>
				
				<td><center><div class="helpMsgHeader" style="width:275px;"><b>Failure Message</b></div></center></td></tr>
				<tr><td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="true"></c:out></div>
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
	

	<div id="frmcontent">
	
		<div class="frmhd">
			<h3 class="subtitle">
				<label><spring:message code="Label.MANAGEVILLAGE" htmlEscape="true"></spring:message></label>
			</h3>
			<ul id="showhelp" class="listing">												
				<%--//these links are not working <li><a href="#" class="frmhelp"><spring:message code="Button.HELP" htmlEscape="true"></spring:message> </a></li> --%>
			</ul>
		</div>

		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="modifyVillageCrAction.htm?disturb=${disturb}"
				method="POST" commandName="modifyVillageCmd" id="frmModifyVillage"
				enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="modifyVillageCrAction.htm"/>" />
					<input type="hidden" id="govtfilecount" name="govtfilecount" value="<c:out value='${govtfilecount}'/>">
					 </input>
					 <input type="hidden" id="mapfilecount" name="mapfilecount" value="<c:out value='${mapfilecount}'/>">
					 </input>
						<input type="hidden" name="warningFlag" value="<c:out value='${pageWarningEntiesFlag}'/>"/>   
					<input type="hidden" name="type" value="<c:out value='${type}'/>"/>
				<div class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message>
						</div>
						
						
						<!-- <table width="100%" class="tbl_no_brdr"> -->
					<div>
						<ul class="blocklist">
							<c:forEach var="listVillageDetails"
								varStatus="listVillageDetailsRow"
								items="${modifyVillageCmd.listVillageDetails}">
								<li>
									<ul class="listing">
										<li><label><spring:message
												code="Label.VILLAGENAMEINENGLISH" htmlEscape="true"></spring:message> </label><br /> <label>
											<spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameEnglish">
												<input type="text" class="frmfield" readonly="readonly"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true" />"
													 />
											</spring:bind> </label>
										<div class="errormsg"></div>
										</li>
										<li>
										<c:if test="${pageWarningEntiesFlag==true}">
										<label><spring:message
												code="Label.WARNINGFLAGSTATUS" htmlEscape="true"></spring:message> </label><br/>
												<img src="images/ylw_flg.png" width="13" height="9" />
										</c:if>
										</li>
									</ul>
								</li>
								
								<li>									
										<label><spring:message
												code="Label.VILLAGENAMEINLOCAL" htmlEscape="true"></spring:message> </label><br /> <label>
											<spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameLocal">
												<input type="text" class="frmfield" readonly="readonly"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true" />"
													 />
											</spring:bind> </label>
										<div class="errormsg"></div>									
								</li>
								<li>									
											<label><spring:message
												code="Label.ALIASENGLISH" htmlEscape="true"></spring:message> </label><br /> <label>
											<spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].aliasEnglish">
												<input type="text" id="txtaliasEnglish" class="frmfield"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true" />"
													  />
											</spring:bind> </label>
											<div class="errormsg" id="aliasNameEngData_error">
												<spring:message htmlEscape="true" code="Error.VillageAliasNameEngData"></spring:message>
									       </div>
										<div class="errormsg"></div>									
								</li>
								<li>									
											<label><spring:message code="Label.ALIASLOCAL" htmlEscape="true"></spring:message>
											</label><br /> <label> <spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].aliasLocal">
												<input type="text" id="txtAliaslocal" class="frmfield"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true" />"
													/>
											</spring:bind> </label>
											 <div class="errormsg" id="aliasNameLocData_error">
												<spring:message htmlEscape="true" code="Error.VillageAliasNameLocalData"></spring:message>
									       </div>
										<div class="errormsg"></div>									
								</li>

								<%-- <li>									
											<label><spring:message
												code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message></label>									
											<br />
											<label> <spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].census2011Code">
											<input type="text" id="txtCensus" class="frmfield" maxlength="6"
												onblur="numericvaluesTest(this.value,2)" 
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="true" />"  />
											</spring:bind> </label>
											<div class="errormsg"></div>
											<div class="errormsg" id="txtCensus_error">
												<spring:message htmlEscape="true" code="Error.Census2011Code"></spring:message>
									       </div>									
								</li> --%>
								<li>
									
										<label>
											<spring:message
												code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message> </label><br /> <label>
											<spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].sscode">
												<input type="text" id="txtSscode" maxlength="5"
													class="frmfield" onblur="chekcalphanumeric(this.value,5);"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true"/>" />
											</spring:bind> </label>
											<div class="errormsg" id="sscode_error">
												<spring:message htmlEscape="true" code="Error.SSCode"></spring:message>
									       </div>
											<form:errors path="sscode" class="errormsg" htmlEscape="true"></form:errors> 
										<div class="errormsg"></div>
									
								</li>
								<li>
								
										<label><spring:message code="Label.VILLAGESTATUS" htmlEscape="true"></spring:message>
										</label><br /> <label> 
								
										<c:if test="${fn:containsIgnoreCase(modifyVillageCmd.villageStatus,'R')}">
											<form:select path="villageStatus" cssClass="combofield"  htmlEscape="true">								
													<option value="R"><esapi:encodeForHTML>Reserve Forest</esapi:encodeForHTML></option>
													<option value="U"><esapi:encodeForHTML>Un-inhabitant</esapi:encodeForHTML></option>
													<option value="I"><esapi:encodeForHTML>Inhabitant</esapi:encodeForHTML></option>
											</form:select>
										</c:if>
										<c:if test="${fn:containsIgnoreCase(modifyVillageCmd.villageStatus,'U')}">
											<form:select path="villageStatus" cssClass="combofield" htmlEscape="true">								
													<option value="U"><esapi:encodeForHTML>Un-inhabitant</esapi:encodeForHTML></option>
													<option value="R"><esapi:encodeForHTML>Reserve Forest</esapi:encodeForHTML></option>
													<option value="I"><esapi:encodeForHTML>Inhabitant</esapi:encodeForHTML></option>
											</form:select>
										</c:if>
										
										<c:if test="${fn:containsIgnoreCase(modifyVillageCmd.villageStatus,'I')}">
											<form:select path="villageStatus" cssClass="combofield" htmlEscape="true">								
													<option value="I"><esapi:encodeForHTML>Inhabitant</esapi:encodeForHTML></option>
													<option value="R"><esapi:encodeForHTML>Reserve Forest</esapi:encodeForHTML></option>
													<option value="U"><esapi:encodeForHTML>Un-inhabitant</esapi:encodeForHTML></option>
											</form:select>
										</c:if>
										
									</label> <br />
									<div class="errormsg"></div>																
							</li>
						</ul>
					</div>
						<!-- </table> -->
						
						
						
					</div>
				</div>

				<div class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message>
						</div>

					<div>	
						<ul class="blocklist">
								<c:if test="${govtOrderConfig == 'govtOrderGenerate'}">								
							<li>								
										<label><spring:message
											code="Label.ORDERNO" htmlEscape="true"></spring:message> </label>
											<c:if test="${mandatoryFlag==true}"> <span class="errormsg">*</span></c:if>
											<br/>
											
												 
										<label> <spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderNocr">
											<input 	type="text" 
												   	class="frmfield"
												   	readonly="readonly"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true" />"/>

										</spring:bind></label>
										
										<spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderCodecr">
											<input type="hidden"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="true" />" />
										</spring:bind>  <form:errors path="orderNocr" cssClass="errormsg" htmlEscape="true"/>
									<div class="errormsg"></div>							
							</li>
							<li>
								
									<label><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message>
									</label>
									<c:if test="${mandatoryFlag==true}"> <span class="errormsg">*</span></c:if>							<br /> <label> 
																
									<spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderDatecr">
											<input type="text" 
													readonly="readonly" 
													class="frmfield" 
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true" />"  />
										</spring:bind> 
										
										
								
							</li>
							<li>
								
									<label><spring:message
											code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> </label>
											<c:if test="${mandatoryFlag==true}"> <span class="errormsg">*</span></c:if>	
									<br /> <label> 
									
									<spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].ordereffectiveDatecr">
											<%--   <c:if test="${listStateDetails.ordereffectiveDatecr >}">	 --%>
											<input type="text"  
												class="frmfield"
												readonly="readonly"	
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="true" />" />

										</spring:bind>									
								
							</li>														
							<li>							
								
									<form:hidden path="govtOrderConfig" value="${govtOrderConfig}" id="hdngovtOrderConfig" />
								
							</li>
								
							</c:if>	
								
								<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
							<li>
								
										<label><spring:message
											code="Label.ORDERNO" htmlEscape="true"></spring:message> </label>
											<c:if test="${mandatoryFlag==true}"> <span class="errormsg">*</span></c:if>
											<br/>
											
											 
										<label> <spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderNocr">
											<input type="text" maxlength="60" class="frmfield"
												 onkeypress="return validateaGovtOrderNOforModify(event);"id="OrderNo"
												onfocus="validateOnFocus('OrderNo');"
												onblur="vlidateOrderNo('OrderNo','1','60');"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="true" />"/>

										</spring:bind></label>
										<div id="OrderNo_error" class="errormsg"><spring:message code="error.required.ORDERNUM" htmlEscape="true"></spring:message></div>
									<div id="OrderNo_msg" class="errormsg"><spring:message code="error.required.ORDERINPUTTYPE" text="Please Enter AlphaNumerics Space . / - ( ) Only" htmlEscape="true"/></div>
										
										<span class="errormsg" id="OrderNo_error"></span> 
										<spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderCodecr">
											<input type="hidden"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="true" />" />
										</spring:bind>  <form:errors path="orderNocr" cssClass="errormsg" htmlEscape="true"/>
									<div class="errormsg"></div>
								
							</li>
							<li>
								
										<label><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message>
										</label>
									<c:if test="${mandatoryFlag==true}"> <span class="errormsg">*</span></c:if>							<br /> <label> 
									<c:if test="${mandatoryFlag==true}">								
									<spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderDatecr">
											<input type="text" id="OrderDate" 
												readonly="readonly"
												class="frmfield" 
												onblur="vlidateOnblur('OrderDate','1','15','c');"
												onfocus="hideAll();setOrderDateforCorrection('OrderDate','#OrderDateData_error');"
												onchange="vaidatetOrderDateforCorrectionEntity(this.value,'#OrderDateData_error');"
												onkeypress="hideAll();"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="true" />"  />
										</spring:bind> 
										</c:if>
										<c:if test="${mandatoryFlag==false}">								
									<spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderDatecr">
											<input type="text" id="OrderDate" 
												class="frmfield" 
												readonly="readonly"
												onfocus="hideAll();setOrderDateforCorrection('OrderDate','#OrderDateData_error');"
												onblur="vlidateOnblur('OrderDate','1','15','c');"
												onchange="validateDatetoFuture('OrderDate','#OrderFutureDate_error');vaidatetOrderDateforCorrectionEntity(this.value,'#OrderDateData_error');"
												onkeypress="hideAll();"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="true" />"  />
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
								
									<label><spring:message
											code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> </label>
											<c:if test="${mandatoryFlag==true}"> <span class="errormsg">*</span></c:if>	
									<br /> <label> 
									<c:if test="${mandatoryFlag==true}">
									<spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].ordereffectiveDatecr">
											<%--   <c:if test="${listStateDetails.ordereffectiveDatecr >}">	 --%>
											<input type="text" id="EffectiveDate" 
												class="frmfield"
												readonly="readonly"	
												onfocus="validateOnFocus('EffectiveDate');"
										        onblur="vlidateOnblur('EffectiveDate','1','15','c');"
												onkeypress="validateNumeric();"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="true" />" />

										</spring:bind>
										</c:if>
											<c:if test="${mandatoryFlag==false}">
											<spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].ordereffectiveDatecr">
											<%--   <c:if test="${listStateDetails.ordereffectiveDatecr >}">	 --%>
											<input type="text" id="EffectiveDate" 
												readonly="readonly"
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
										<div class="errormsg"></div>
										 </label><span class="errormsg" id="EffectiveDate_error"></span> <form:errors
										path="ordereffectiveDatecr" cssClass="errormsg" />
										<div class="errormsg"></div>
								
							</li>						
							<li>
									
										<label><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>
										</label><br /> <label> <spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].gazPubDatecr"  htmlEscape="true">
												<input type="text" id="gazPubDatecr" 
													readonly="readonly"
													class="frmfield" 
													onkeyup="validateNumeric();"
													onchange="noOrderDataValid('gazPubDatecr','#GuzpubDateBlankOrderDate_error','OrderDate');compareGuzpubDatetoOrderDate('gazPubDatecr','#GuzpubDateCompareOrderDate_error','OrderDate')"
													onkeypress="hideAll();"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true" />"
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
											<form:errors path="gazPubDatecr" cssClass="errormsg"  htmlEscape="true"/>
										<div class="errormsg"></div>
									
							</li>
							
							<li>															
									<form:hidden path="govtOrderConfig"
										value="${govtOrderConfig}" id="hdngovtOrderConfig"  htmlEscape="true"/>
								
							</li>
						
								<li>									
										<%@ include file="../common/updateattach.jspf"%>
																	<spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].mapCode"  htmlEscape="true">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true" />" />
											</spring:bind>
											<spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageCode"  htmlEscape="true">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true" />" />
											</spring:bind>
											<spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageVersion"  htmlEscape="true">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true" />" />
											</spring:bind>
											<spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].subdistrictCode"  htmlEscape="true">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true" />" />
											</spring:bind>
											<spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].subdistrictVersion" htmlEscape="true">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true" />" />
											</spring:bind>
												<spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].cordinate"  htmlEscape="true">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true" />" />
											</spring:bind>
											
											<spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].warningflag"  htmlEscape="true">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true" />" />
											</spring:bind>
										
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
								<ul class="blocklist">
								<li><%@ include file="../common/modifyGis_nodes.jspf"%></li>
								</ul>
										
								<c:if test="${modifyVillageCmd.listVillageDetails[listVillageDetailsRow.index].cordinate!=null}">
									<script>
										var cordinates='${modifyVillageCmd.listVillageDetails[listVillageDetailsRow.index].cordinate}';
										buildLatitudeLongitude(cordinates);
									</script>
								</c:if>
							</div>
			</div>

				</c:forEach>
                 <div class="btnpnl">
						<label> <input type="button" name="Submit" class="btn" value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" onclick="return validateVillage();"/>
						</label>
									
									<c:if test="${reqWarningFlag!=null}">
											<input type="button" name="Submit3" class="btn" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:location.href='viewResolveEntitiesinDisturbedStateLR.htm?<csrf:token uri='vviewResolveEntitiesinDisturbedStateLR.htm'/>&warningEntiesFlag=${reqWarningFlag}';" />
									</c:if>
									<c:if test="${reqWarningFlag==null}">
										<input type="button" name="Submit3" class="btn" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'"  />									
									</c:if>
									
					</div>

			</form:form>
			<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
		</div>
	</div>
</body>
</html>
