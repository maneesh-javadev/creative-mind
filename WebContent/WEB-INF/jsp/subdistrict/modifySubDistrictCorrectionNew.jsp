<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
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
<!-- <link href="css/successMessage.css" rel="stylesheet" type="text/css" /> -->
<script src="js/validation.js"></script>
<script src="js/orderValidate.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>
<link href="datepicker/calender.css" rel="stylesheet" type="text/css" />

<!-- <script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script> 
<script src="datepicker/calender.js"></script>
<link rel="stylesheet" href="datepicker/demos.css" /> -->
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<title><spring:message code="Label.MODIFYSUBDISTRICT" htmlEscape="true"></spring:message></title>

<script type="text/javascript">

var filecountch;
var HeadquterNameEngch;
var HeadquterNameLocch;
var sscodech;
var aliasNameInEnch;
var aliasNameInLcch;
var orderDatech;
var effectiveDatech;
var GuzpubDatech;
var OrderNoch;
var fielattachch;



$(document).ready(function() {
	 var mandatoryFlag = isParseJson('${mandatoryFlag}');
	  filecountch = document.getElementById('govtfilecount').value;
	  HeadquterNameEngch=document.getElementById('hquarterseng').value;
	  HeadquterNameLocch=document.getElementById('hquarterslocal').value;
	  sscodech=document.getElementById('txtSscode').value; 
	 
	  aliasNameInEnch=document.getElementById('aliasenglishch').value; 
      aliasNameInLcch=document.getElementById('aliaslocalch').value;
	  orderDatech= document.getElementById('orderdatech').value; 
	  effectiveDatech=document.getElementById('ordereffectivedatech').value; 
      GuzpubDatech=document.getElementById('gazpubdatech').value; 
      OrderNoch=document.getElementById('ordernoch').value!=null?document.getElementById('ordernoch').value.trim():null;
      fielattachch = document.getElementById('attachFile1').value;
	 
     /* aliasenglishch
     aliaslocalch
     ordernoch
     orderdatech
     ordereffectivedatech
     gazpubdatech
     
      */
     
     
     
	 
	if(mandatoryFlag){ 
	$('#EffectiveDate').prop('disabled', true);
	 }	 
	
	$("#bOrderDate").datetimepicker({
		format: 'dd-mm-yyyy',
		startView : 'month',
		endDate: '+0d',
       autoclose: true,
		minView : 'month',
		pickerPosition : "bottom-left",
		EndDate:'0',
	});
	
	/* $("#bEffectiveDate").datetimepicker({
		format: 'dd-mm-yyyy',
		startView : 'month',
		endDate: '+0d',
       autoclose: true,
		minView : 'month',
		pickerPosition : "bottom-left",
		EndDate:'0',
	}); */
	
	$("#bGazPubDate").datetimepicker({
		format: 'dd-mm-yyyy',
		startView : 'month',
		endDate: '+0d',
       autoclose: true,
		minView : 'month',
		pickerPosition : "bottom-left",
		EndDate:'0',
	});

});


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
			    var HeadquterNameEng=document.getElementById('hquarterseng').value;
				var HeadquterNameLoc=document.getElementById('hquarterslocal').value;
				var sscode=document.getElementById('txtSscode').value; 
				var orderDate= document.getElementById('OrderDate').value; 
			    var effectiveDate=document.getElementById('EffectiveDate').value; 
				var GuzpubDate=document.getElementById('gazPubDatecr').value; 
				var OrderNo=document.getElementById('OrderNo').value;
				var fielattach = document.getElementById('attachFile1').value;
				
				var counter = document.getElementById('govtfilecount').value;
				if(fielattach=="" && counter==0){
					alert("please upload file")
					}
				
				else if(aliasNameInEnch==aliasNameInEn && aliasNameInLcch==aliasNameInLc && HeadquterNameEngch==HeadquterNameEng && HeadquterNameLocch==HeadquterNameLoc 
						&&  sscodech==sscode && OrderNoch==OrderNo && orderDatech==orderDate && effectiveDatech==effectiveDate && GuzpubDatech==GuzpubDate)
					{
				      alert("You did not any change so can't submit!!!");
					}
				
				else
					{
				
				if(govtOrderConfig=='govtOrderUpload'){
					
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
			/* $("#cAlert").html("<spring:message code='Error.lengthmismatch' htmlEscape='true'/>");
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
			}); */
			$('#customAlertbody').text('<spring:message code='Error.lengthmismatch' htmlEscape='true'/>');
			$('#customAlert').modal('show');
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


function getDistrictData(){
	 var districtElement=$( '#districtCode');
		if(isEmptyObject($( districtElement ).val())){
			$( districtElement ).addClass("error");
			$( '#err' + $( districtElement ).attr('id') ).text("<spring:message code='Label.SELECTDISTRICT' htmlEscape='true'/>");
				$(districtElement).focus();
				firstErrorElement=true;
				
		}else{
		 callActionUrl('manageSubdistrict.htm');
	 }
}


$("#Submit4").click(function() {
	
	 var districtElement=$( '#districtCode');
		if(isEmptyObject($( districtElement ).val())){
			$( districtElement ).addClass("error");
			$( '#err' + $( districtElement ).attr('id') ).text("<spring:message code='Label.SELECTDISTRICT' htmlEscape='true'/>");
				$(districtElement).focus();
				firstErrorElement=true;
		}else{
		 callActionUrl('manageSubdistrict.htm');
	 }
	
});


	

</script>
</head>

<body onload='clearOrdernoErrors()'>
	
	<!-- <div id="helpDiv" class="helpMessage">
		<div class="helpheader">
			<h4>Help Message</h4>
		</div>
		<div id="helpMsgText" class="helptext"></div>
	</div> -->

	<section class="content">
		<div class="row">
			<section class="col-lg-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title"> <spring:message code="Label.MODIFYSUBDISTRICT" htmlEscape="true"></spring:message> </h3>
					</div>
					<form:form action="modifySubDistrictCrAction.htm" method="POST" commandName="modifySubDistrictCmd" id="frmModifySubDistrict" enctype="multipart/form-data" class="form-horizontal">
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifySubDistrictCrAction.htm"/>" />
						<input type="hidden" id="govtfilecount" name="govtfilecount" value="<c:out value='${govtfilecount}'/>" />
						<input type="hidden" id="mapfilecount" name="mapfilecount" value="<c:out value='${mapfilecount}'/>"/>
						<input type="hidden" name="warningFlag" value="<c:out value='${pageWarningEntiesFlag}'/>"/>
						<form:hidden path="cordinate" />
						<div class="box-body">
							<c:forEach var="listSubdistrictDetails" varStatus="listSubdistrictDetailsRow" items="${modifySubDistrictCmd.listSubdistrictDetails}">
								<div class="box-header subheading">
				                  <h4><spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message></h4>
				                </div>
								<div class="form-group">
			                    	<label class="col-sm-3 control-label"><spring:message code="Label.SUBDISTRICTNAMEENGLISH" htmlEscape="true" /><span class="mandatory">*</span></label>
			                      	<div class="col-sm-6">
			                        	<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictNameEnglish">
											<input type="text" class="form-control" readonly="readonly" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" escapeXml="true" />" />
										</spring:bind> 
										<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictCode">
											<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" />
										</spring:bind>
										
										<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictNameEnglishch">
											<input type="hidden"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="true" />
										</spring:bind> 
										
			                      	</div>
			                    </div>
			                    <c:if test="${pageWarningEntiesFlag==true}">
				                    <div class="form-group">
				                    	<label class="col-sm-3 control-label"><spring:message code="Label.WARNINGFLAGSTATUS" htmlEscape="true" /></label>
				                      	<div class="col-sm-6">
				                        	<br />
											<img src="images/ylw_flg.png" width="13" height="9" />
				                      	</div>
				                    </div>
			                    </c:if>
			                    <div class="form-group">
			                    	<label class="col-sm-3 control-label"><spring:message code="Label.SUBDISTRICTNAMELOCAL" htmlEscape="true" /></label>
			                      	<div class="col-sm-6">
			                        	<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictNameLocal">
											<input type="text" class="form-control" readonly="readonly" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"  escapeXml="true"/>" />
										</spring:bind>
										
										<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictNameLocalch">
											<input type="hidden"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="true" />
										</spring:bind> 
										
			                      	</div>
			                    </div>
			                    <div class="form-group">
			                    	<label class="col-sm-3 control-label"><spring:message code="Label.SUBDISTRICTALIASENGLISH" htmlEscape="true" /></label>
			                      	<div class="col-sm-6">
			                        	<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].aliasEnglish">
											<input type="text" class="form-control" id="txtaliasEnglish" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" />
										</spring:bind>
										
										<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].aliasEnglishch">
											<input type="hidden"  id="aliasenglishch" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="true" />
										</spring:bind> 
										<div class="errormsg" id="aliasNameEngData_error">
											<spring:message htmlEscape="true" code="Error.SubdistrictAliasNameEngData" />
										</div>
			                      	</div>
			                    </div>
			                    <div class="form-group">
			                    	<label class="col-sm-3 control-label"><spring:message code="Label.SUBDISTRICTALIASLOCAL" htmlEscape="true" /></label>
			                      	<div class="col-sm-6">
			                        	<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].aliasLocal">
											<input type="text" id="txtAliaslocal" class="form-control" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" />
										</spring:bind>
										
										<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].aliasLocalch">
											<input type="hidden"  id="aliaslocalch" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="true" />
										</spring:bind> 
										<div class="errormsg" id="aliasNameLocData_error">
											<spring:message htmlEscape="true" code="Error.SubdistrictAliasNameLocalData" />
										</div>
			                      	</div>
			                    </div>
			                    <div class="form-group">
			                    	<label class="col-sm-3 control-label"><spring:message code="Label.HEADQUARTERS" htmlEscape="true" /></label>
			                      	<div class="col-sm-6">
			                        	<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].headquarterName">
											<input type="text" id="hquarterseng" class="form-control" onkeypress="hideAll();" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" />
										</spring:bind>
										<div class="errormsg" id="headquterNameEngData_error">
											<spring:message htmlEscape="true" code="Error.HeadquterNameEngData" />
										</div>
										<form:errors path="headquarterName" cssClass="errormsg" htmlEscape="true" />
			                      	</div>
			                    </div>
			                    <div class="form-group">
			                    	<label class="col-sm-3 control-label"><spring:message code="Label.HEADQUARTERSLOCAL" htmlEscape="true" /></label>
			                      	<div class="col-sm-6">
			                        	<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].headquarterNameLocal">
											<input type="text" id="hquarterslocal" class="form-control" onkeypress="hideAll();" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" />
										</spring:bind>
										<div class="errormsg" id="headquterNameLocData_error">
											<spring:message htmlEscape="true" code="Error.HeadquterNameLocalData" />
										</div> 
										<form:errors path="headquarterNameLocal" cssClass="errormsg" htmlEscape="true" />
										<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].headquarterCode">
											<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="true" />
										</spring:bind> 
										<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictCode">
											<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="true" />
										</spring:bind> 
										<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictVersion">
											<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="true" />
										</spring:bind> 
										<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].tlc">
											<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="true" />
										</spring:bind>
			                      	</div>
			                    </div>
			                    <div class="form-group">
			                    	<label class="col-sm-3 control-label"><spring:message code="Label.STATESPECIFICCODE" htmlEscape="true" /></label>
			                      	<div class="col-sm-6">
			                        	<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].sscode">
											<input type="text" id="txtSscode" class="form-control" maxlength="5" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" />
										</spring:bind>
										<div class="errormsg" id="sscode_error">
											<spring:message htmlEscape="true" code="Error.SSCode" />
										</div> 
										<form:errors path="sscode" cssClass="errormsg" htmlEscape="true" />
			                      	</div>
			                    </div>
			                    <div class="form-group">
			                      	<div class="col-sm-6">
			                        	<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].mapLandregionCode">
											<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" escapeXml="true" />
										</spring:bind>
										<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictCode">
											<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="true" />
										</spring:bind>
										<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictVersion">
											<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="true" />
										</spring:bind>
										<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].district_code">
											<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="true" />
										</spring:bind>
										<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].district_version">
											<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="true" />
										</spring:bind>
										<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].cordinate">
											<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="true" />
										</spring:bind>
										<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].warningFlag">
											<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="true" />
										</spring:bind>
			                      	</div>
			                    </div>
			                    <br/>
			                    <div class="box-header subheading">
				                  <h4><spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message></h4>
				                </div>
				                <c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
					                <div class="form-group">
										<label class="col-sm-3 control-label"><spring:message code="Label.ORDERNO" htmlEscape="true" />
										<c:if test="${mandatoryFlag==true}">
											<span class="mandatory">*</span>
										</c:if></label>
										<div class="col-sm-6">
											<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderNocr">
												<input type="text" readonly="readonly" class="form-control" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" />
											</spring:bind>
											
											<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderNo">
											<input type="hidden" id="ordernoch"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="true" />
										</spring:bind> 
											<div id="OrderNoBlank_error" class="mandatory"><spring:message code="error.required.ORDERNUM" htmlEscape="true"></spring:message></div>
										   <div id="OrderNoDataValid_error" class="error"><spring:message text="Enter valid order no." htmlEscape="true"></spring:message></div>
										</div>
				                    </div>
				                    <div class="form-group">
										<label class="col-sm-3 control-label"><spring:message code="Label.ORDERDATE" htmlEscape="true" />
										<c:if test="${mandatoryFlag==true}">
											<span class="mandatory">*</span>
										</c:if></label>
										<div class="col-sm-6">
											<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderDatecr">
												<input type="text" class="form-control" readonly="readonly" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" htmlEscape="true" />
											</spring:bind>
											<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderDate">
											<input type="hidden"   id="orderdatech" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="true" />
										</spring:bind> 
											
											<div id="OrderDateBlank_error" class="error"><spring:message code="error.required.ORDERDATE" htmlEscape="true"></spring:message></div>
										    <div id="OrderDateValid_error" class="error"><spring:message text="Enter valid date" htmlEscape="true"></spring:message></div>
										</div>
				                    </div>
				                    <div class="form-group">
										<label class="col-sm-3 control-label"><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true" />
										<c:if test="${mandatoryFlag==true}">
											<span class="mandatory">*</span>
										</c:if></label>
										<div class="col-sm-6">
											<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].ordereffectiveDatecr">
												<input type="text" readonly="readonly" class="form-control"   name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" htmlEscape="true" />
											</spring:bind>
											<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].ordereffectiveDate">
											<input type="hidden"  id="ordereffectivedatech" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="true" />
										  </spring:bind>
											
											<form:hidden path="govtOrderConfig" value="${govtOrderConfig}" id="hdngovtOrderConfig"  htmlEscape="true"/>
											  <div id="EffectiveDateBlank_error" class="error"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"></spring:message></div>
										</div>
				                    </div>
			                    </c:if>
			                    <c:if test="${govtOrderConfig == 'govtOrderUpload'}">
			                    	<div class="form-group">
										<label class="col-sm-3 control-label"><spring:message code="Label.ORDERNO" htmlEscape="true" />
										<c:if test="${mandatoryFlag==true}">
											<span class="mandatory">*</span>
										</c:if></label>
										<div class="col-sm-6">
											<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderNocr">
												<input type="text" maxlength="60" class="form-control" onkeypress="return validateaGovtOrderNOforModify(event);"  id="OrderNo" onfocus="helpMessage(this,'OrderNo_error');validateOnFocus('OrderNo');" onblur="hideHelp();vlidateOrderNo('OrderNo','1','60');" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true" />"  />
											</spring:bind>
											
											<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderNo">
											<input type="hidden" id="ordernoch"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="true" />
										</spring:bind> 
											
											
											<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderCodecr">
												<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="true"/>
											</spring:bind> 
											
										</div>
				                    </div>
				                    <div class="form-group">
										<label class="col-sm-3 control-label"><spring:message code="Label.ORDERDATE" htmlEscape="true" />
										<c:if test="${mandatoryFlag==true}">
											<span class="mandatory">*</span>
										</c:if></label>
										<div class="col-sm-6">
											<%-- <c:if test="${mandatoryFlag==true}">
												<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderDatecr">
													  <input type="text" readonly="readonly" class="form-control" id="OrderDate"  onblur="vlidateOnblur('OrderDate','1','15','c');" onfocus="hideAll();setOrderDateforCorrection('OrderDate','#OrderDateData_error');" onchange="vaidatetOrderDateforCorrectionEntity(this.value,'#OrderDateData_error');" onkeypress="hideAll();" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" htmlEscape="true" />
												</spring:bind> 
											</c:if>
											<c:if test="${mandatoryFlag==false}"> --%>
											<div class="input-group date datepicker" id="bOrderDate">
												<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderDatecr">
												      <input type="text" readonly="readonly" class="form-control" id="OrderDate"  onblur="vlidateOnblur('OrderDate','1','15','c');" onfocus="hideAll();setOrderDateforCorrection('OrderDate','#OrderDateData_error');" onchange="validateDatetoFuture('OrderDate','#OrderFutureDate_error');vaidatetOrderDateforCorrectionEntity(this.value,'#OrderDateData_error');" onkeypress="hideAll();" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" htmlEscape="true" />
												</spring:bind> 
												
												<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderDate">
											<input type="hidden"   id="orderdatech" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="true" />
										</spring:bind> 
												<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
												</div>
											<%-- </c:if> --%>
											<div id="OrderDateBlank_error" class="error"><spring:message code="error.required.ORDERDATE" htmlEscape="true"></spring:message></div>
										    <div id="OrderDateValid_error" class="error"><spring:message text="Enter valid date" htmlEscape="true"></spring:message></div>
										</div>
				                    </div>
				                    <div class="form-group">
										<label class="col-sm-3 control-label"><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true" />
										<c:if test="${mandatoryFlag==true}">
											<span class="mandatory">*</span>
										</c:if></label>
										<div class="col-sm-6">
											<%-- <c:if test="${mandatoryFlag==true}">
												<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].ordereffectiveDatecr">
												  <input type="text" id="EffectiveDate" class="form-control" readonly="readonly"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" htmlEscape="true" />
												</spring:bind>
												
											</c:if>
											<c:if test="${mandatoryFlag==false}"> --%>
											<div class="input-group date datepicker" id="bEffectiveDate">
												<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].ordereffectiveDatecr">
										    	  <input type="text" id="EffectiveDate" readonly="readonly" class="form-control" class="form-control" onfocus="validateOnFocus('EffectiveDate');" onblur="vlidateOnblur('EffectiveDate','1','15','c');" onchange="validateDatetoFuture('EffectiveDate','#EffectiveFutureDate_error');validateEffectiveDatecompOrderDate('OrderDate','EffectiveDate','#EffectiveDateData_error');" onkeypress="hideAll();" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true" />" />
										  		</spring:bind> 
										  		
										  		<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].ordereffectiveDate">
											<input type="hidden"  id="ordereffectivedatech" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="true" />
										  </spring:bind>
										  		<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
										  	</div>	
										<%-- 	</c:if> --%>
											  <div id="EffectiveDateBlank_error" class="error"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"></spring:message></div>
											
										</div>
				                    </div>
				                    <c:if test="${govtOrderConfig == 'govtOrderUpload'}">
					                    <div class="form-group">
											<label class="col-sm-3 control-label"><spring:message code="Label.GAZPUBDATE" htmlEscape="true" /></label>
											<div class="col-sm-6">
											<div class="input-group date datepicker" id="bGazPubDate">
												<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].gazPubDatecr">
												  <input type="text" class="form-control" readonly="readonly" id="gazPubDatecr"  onfocus="show_msg('gazPubDatecr');" onchange="noOrderDataValid('gazPubDatecr','#GuzpubDateBlankOrderDate_error','OrderDate');compareGuzpubDatetoOrderDate('gazPubDatecr','#GuzpubDateCompareOrderDate_error','OrderDate')" onblur="validateGazPubDate1();" onkeyup="validateNumeric();" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" htmlEscape="true" />
												</spring:bind>
												<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].gazPubDate">
											<input type="hidden"  id="gazpubdatech" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="true" />
										  </spring:bind>
												
												<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
												</div>
												<div id="GuzpubDateValid_error" class="error"><spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true"></spring:message></div>
											</div>
					                    </div>
				                    </c:if>
				                   
										
											<form:hidden path="govtOrderConfig" value="${govtOrderConfig}" id="hdngovtOrderConfig"  htmlEscape="true"/>
											<%@ include file="../common/updateattachcp.jspf"%>
										
				                  
			                    </c:if>
			                   <%--  <c:if test="${count!=1}">
			                    	<div class="box-header subheading">
					                  <h4><spring:message code="Label.GISNODES" htmlEscape="true"></spring:message></h4>
					                </div>
					                <div class="form-group">
										<div class="col-sm-6">
											<%@ include file="../common/modifyGis_nodes.jspf"%>
											<c:if test="${modifySubDistrictCmd.listSubdistrictDetails[listSubdistrictDetailsRow.index].cordinate!=null}">
												<script>
													var cordinates='${modifySubDistrictCmd.listSubdistrictDetails[listSubdistrictDetailsRow.index].cordinate}';
													buildLatitudeLongitude(cordinates);
												</script>
											</c:if>
										</div>
				                    </div>
			                    </c:if> --%>
							</c:forEach>
							<div class="box-footer">
		                    	<div class="col-sm-offset-2 col-sm-10">
		                    		<div class="pull-right">
		                           		<button type="button" name="Submit" class="btn btn-success" onclick="return validateSubdistrict();" ><spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button>
										<c:if test="${reqWarningFlag!=null}">
											<button type="button" name="Submit3" class="btn btn-warning" onclick="javascript:location.href='viewResolveEntitiesinDisturbedStateLR.htm?<csrf:token uri='vviewResolveEntitiesinDisturbedStateLR.htm'/>&warningEntiesFlag=${reqWarningFlag}';" ><spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button>
										</c:if>
										<c:if test="${reqWarningFlag==null}">
											<button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'" ><spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button>
										</c:if> 
										
										<%--  <c:if test="${reqWarningFlag==null}">
							            <button style="width: 80px;" type="button" name="Submit4" class="btn btn-danger" onclick="callActionUrl('manageSubdistrict.htm')" ><i class="fa fa-times-circle"></i>  <spring:message code="Button.BACK" htmlEscape="true"></spring:message></button>									
					                    </c:if> --%>
					                    
					                   <%-- <button type="button" name="Submit4" class="btn btn-info" onclick="callActionUrl('manageSubdistrict.htm')" <csrf:token uri='home.htm'/>'" ><spring:message code="Button.BACK" htmlEscape="true"></spring:message></button> --%>
										
		                        	</div>
		                    	 </div>   
	                  		</div>
	                  		<input type="hidden" name="warningEntiesFlag" value="<c:out value='${warningEntiesFlag}'/>"/>
							<input type="hidden" name="type" value="<c:out value='${type}'/>"/>
						</div>
					</form:form>
					<script src="/LGD/JavaScriptServlet"></script>
				</div>
				
<div class="modal fade" id="customAlert" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"> <spring:message code="Label.MODIFYSUBDISTRICT" htmlEscape="true"></spring:message></h4>
        </div>
        <div class="modal-body" id="customAlertbody">
         
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Ok</button>
        </div>
      </div>
      
    </div>
  </div>
			</section>
		</div>
	</section>
	</body>
</html>