<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<head>

<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/validation.js"></script>
<script type="text/javascript" src="js/createDistrict.js"></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrSubVillageService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrVillageService.js'></script>
<!-- <script type="text/javascript" src="js/new_sub_district.js" charset="utf-8"></script> -->
<script>

$( document ).ready(function() {
	var stateCode=${stateCode};
	lgdDwrDistrictService.getDistrictList(stateCode,{
		asyn : false,
		callback : handleDistSuccessMarkPesa,
		errorHandler : handleDistErrorMarkPesa
	});
});


function handleDistSuccessMarkPesa(data) {
	// Assigns data to result id
	/*added by kirandeep for modified use case of is pesa landregion */
	var distCodesPart="";
	

	var fieldId = 'ddSourceDistrict';
	var fieldId2 = 'ddDestDistrict';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId2);
	var options = $("#ddDestDistrict");
	var options2 = $("#ddSourceDistrict");
	$.each(data, function(key, obj) {
		if (obj.is_pesa == 'F') {
			var option = $("<option/>");
			//$(option).attr('disabled', 'disabled'); 
			$(option).val(obj.districtCode+"FULL").text(obj.districtNameEnglish+"(FULL)");
		    options.append(option);
		}else if(obj.is_pesa == 'P'){
			var option = $("<option/>");
			/*added by kirandeep for modified use case of is pesa landregion */
			$(option).val(obj.districtCode+"PART").text(obj.districtNameEnglish+"(PART)");				
			options.append(option);
			distCodesPart = obj.districtCode +','+ distCodesPart;
			
		}else if(obj.is_pesa == 'N'){
			 var option2 = $("<option/>");
		    $(option2).val(obj.districtCode).text(obj.districtNameEnglish);
		    options2.append(option2);
		}
	});
	
	
	
	var length= distCodesPart.length;
	var distCodesPart = distCodesPart.substring(0, length-1);
//	alert(distCodesPart);
	if(distCodesPart.length > 0){
		lgdDwrDistrictService.getHeirarchyByParentCodes(distCodesPart,'D','Y',{
			callback : handledistrictOnLoadBackSuccess,
			errorHandler : handleSubDistErrorFull
			
		});
	}
	 
}
	
function handleDistErrorMarkPesa() {
	// Show a popup message
	alert("No data found!");
}	


function selectVillageListMarkPesa() {
	resetValues(true);
	dwr.util.removeAllOptions("villageList");
	//dwr.util.removeAllOptions("villageListNew");
	var selObj = document.getElementById('subDistrictListNew');
	var idValuePart = "";
	var selText = [];
	var counter = 0;
	for ( var i = 0; i < selObj.options.length; i++) {
		selObj.options[i].selected = true;
		if ((selObj.options[i].value).match('PART') == 'PART') {
			var txt = selObj.options[i].text;
			if (txt != null) {
				selText[counter] = txt.substring(0, txt.indexOf('['));
				counter++;
			}
			if (idValuePart == "") {
				idValuePart = selObj.options[i].value;
			} else {
				idValuePart = idValuePart + "," + selObj.options[i].value;
			}
		}
		
	}
   if (idValuePart != "") {
		var totalVillage = document.getElementById('totalVillage').value;
		lgdDwrDistrictService.getVillageList(idValuePart, totalVillage, selText, {
			callback : handleVillageListMarkPesa,
			errorHandler : handleVillageErrorMarkPesa
		});
	} else {
		dwr.util.removeAllOptions('villageList');
		
	}
} 
function handleVillageListMarkPesa(data) {
	
	var fieldId = 'villageList';
	var fieldId2 = 'villageListNew';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId2);
	var options = $("#villageListNew");
	var options2 = $("#villageList");
	$.each(data, function(key, obj) {
		
		if (obj.is_pesa == 'F') {
			var option = $("<option/>");
			//$(option).attr('disabled', 'disabled'); 
			$(option).val(obj.villageCode).text(obj.villageNameEnglish + ' [ ' + obj.subdistrict + ' ] '); 
		    options.append(option);
		}else if(obj.is_pesa == 'P'){
			var option = $("<option/>");
			/*added by kirandeep for modified use case of is pesa landregion */
			$(option).val(obj.villageCode).text(obj.villageNameEnglish + ' [ ' + obj.subdistrict + ' ] ');				
			options.append(option);
			//distCodesPart = obj.districtCode +','+ distCodesPart;
			
		}else if(obj.is_pesa == 'N'){
			 var option2 = $("<option/>");
		    $(option2).val(obj.villageCode).text(obj.villageNameEnglish + ' [ ' + obj.subdistrict + ' ] ');
		    options2.append(option2);
		}
		
		
		
		
		
			/* var option2 = $("<option/>");
			$(option2).val(obj.villageCode).text(obj.villageNameEnglish + ' [ ' + obj.subdistrict + ' ] ');
			options2.append(option2); */
		
	});
}

function handleVillageErrorMarkPesa() {
	alert("No data found!");

}

//Added by Anju Gupta dwr for SubDistrict on 30/3/2015

/* $( document ).ready(function() {
	var stateCode=${stateCode};
	 lgdDwrSubDistrictService.getSubdistrictListBySLCfprMarkPesa(stateCode,{
		asyn : false,
		callback : handleDistSuccessMarkPesa1,
		errorHandler : handleDistErrorMarkPesa
	}); 
	
});

function handleDistSuccessMarkPesa1(data) {
	
	dwr.util.removeAllOptions(fieldId2);
	var fieldId = 'ddSubdistrictforsubdistrict';
	var fieldId2 = 'subDistrictListNew';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId2);
	var options = $("#subDistrictListNew");
	var options2 = $("#ddSubdistrictforsubdistrict");
	$.each(data, function(key, obj) {
		if (obj.is_pesa == 'F') {
			var option = $("<option/>");
			//$(option).attr('disabled', 'disabled');
			$(option).val(obj.subdistrictCode + "FULL").text(obj.subdistrictNameEnglish + ' [ ' + obj.districtNameEnglish + ' ] '+ "(FULL)");
		    options.append(option);
		}else if(obj.is_pesa == 'P'){
			var option = $("<option/>");
			$(option).val(obj.subdistrictCode + "PART").text(obj.subdistrictNameEnglish + ' [ ' + obj.districtNameEnglish + ' ] '+ "(PART)");
			options.append(option);
		}
		
	});
} */
//end by Anju Gupta

//added  by anju gupta dwr for Village on 30/3/2015

/* $( document ).ready(function() {
	var stateCode=${stateCode};
	lgdDwrVillageService.villageListbySlctoIspesa(stateCode,{
		asyn : false,
		callback : handleDistSuccessMarkPesaForVillage,
		errorHandler : handleDistErrorMarkPesa
	});
});


function handleDistSuccessMarkPesaForVillage(data) {
	var fieldId = 'villageList';
	var fieldId2 = 'villageListNew';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId2);
	var options = $("#villageListNew");
	var options2 = $("#villageList");
	$.each(data, function(key, obj) {
		if (obj.is_pesa == 'F') {
			var option = $("<option/>");
			$(option).val(obj.villageCode+"FULL").text(obj.villageNameEnglish + ' [ ' + obj.subdistrictNameEnglish + ' ] '+  "(FULL)");
			options.append(option);
		}else {
			alert('good');
		}
	});
} */


//end by anju gupta


</script>
<body >
	<%@include file="addDistrict.jsp"%>
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message htmlEscape="true" code="Label.MarkPesaLandRegions"></spring:message></h3>
			<ul class="listing">
				<%--//this link is not working <li>
					<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message> </a>
				</li> --%>
				<%--  //this link is not working <li>
					<a href="viewManualHelps.do?formName=${formId}&<csrf:token uri='viewManualHelps.do'/>" class="frmhelp" onclick="centeredPopup(this.href,'myWindow','yes');return false">Help</a>
				</li> --%>
			</ul>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<c:choose>
			<c:when test="${displayForm}">
			<form:form action="markPesaLandRegion.htm" method="POST" commandName="district" id="form1" name="form1" enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="markPesaLandRegion.htm"/>" />
				<div id="DistrictdetailId" class="frmpnlbg" style="${display}">
				</div>
				<div class="frmpnlbg">
					<div class="frmtxt" style="${style}">
					<div class="frmhdtitle">
						<spring:message htmlEscape="true" code="Label.MarkPesaLandRegions"></spring:message>
					</div>
					<div id="mainBody">
								
						<div class="clear"></div>
							<div class="frmpnlbg">
								<div id='district'>
								<ul class="blocklist">
										<li>
											<div class="ms_container">
												<div class="ms_selectable">
														<label>
															<spring:message htmlEscape="true" code="Label.AVAILABLEDISTRICT"></spring:message>
														</label>			
														<%-- <form:select path="districtsourcecode" class="frmtxtarea" id="ddSourceDistrict" multiple="true" disabled="${disabled}">
														 --%>
														 <form:select path="districtsourcecode" class="frmtxtarea" id="ddSourceDistrict" multiple="true">
														   <form:options items="${distList}" itemLabel="districtNameEnglish" itemValue="districtCode" />
														</form:select>
												</div>
												<div class="ms_buttons">
													<input id="src2Target1" type="button" class="bttn" onclick="addItem('ddDestDistrict','ddSourceDistrict','FULL',true);" value=" Whole &gt;&gt;" />
													 <input id="target2Src1" type="button"  class="bttn" onclick="removeOnedistrictOptionForMarkPesa('ddDestDistrict', 'ddSourceDistrict', true);" value="&lt;" /> 
													 <input id="target2Src2" type="button"  class="bttn" onclick="removeAllListForMarkPesa('ddDestDistrict', 'ddSourceDistrict', true);" value="&lt;&lt;" />
													 <input id="src2Target2" type="button" class="bttn" onclick="addItem('ddDestDistrict','ddSourceDistrict', 'PART',true);" value=" Part &gt;&gt;" />
												</div>
												
												<div class="ms_selection">
													<label>
														<spring:message htmlEscape="true" code="Label.DISTRICTLISTSELECTED"></spring:message>
													</label>                                                                  
													
													 
													 <form:select name="select4" id="ddDestDistrict" size="1" multiple="multiple" path="districtNameEnglish" class="frmtxtarea" >
													 <form:options items="${listDistrict}" itemLabel="districtNameEnglish" itemValue="aliasEnglish" />
													</form:select><br></br>
													<input name="button2" class="btn" type="button" onclick="selectsubdistMarkPesa()" value="Get Sub-district List" />
												</div>
											</div>
										</li><br></br>
										
										
										<li>
											<div class="ms_container">
												<div class="ms_selectable">
													<label>
														<spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"></spring:message>
													</label>
													<form:select name="ddSubdistrictforsubdistrict" size="1" id="ddSubdistrictforsubdistrict" path="" multiple="multiple" class="frmtxtarea">
														<form:options items="${preSubDistrictList}" itemLabel="subdistrictNameEnglish" itemValue="subdistrictCode" />
													</form:select>
													
												</div>
												
												
												<div class="ms_buttons">
													<label> 
														<input type="button" class="bttn" id="btnaddSubDistrictFullMarkPesa" name="Submit4" value=" Whole &gt;&gt;" />
													</label>
													
													<label> 
														<input id="buttonRemove1" type="button" class="bttn" onclick="removeOnedistrictOptionForMarkPesa('subDistrictListNew', 'ddSubdistrictforsubdistrict', true)" value="&lt;" />
													</label>
													<label>
														<input id="buttonRemove2" type="button" class="bttn" onclick="removeAllListForMarkPesa('subDistrictListNew', 'ddSubdistrictforsubdistrict', true)" value="&lt;&lt;" />
													</label>
													<label>
														 <input type="button" class="bttn" id="btnaddSubDistrictPart" name="Submit4" value=" Part &gt;&gt;" onclick="addItem('subDistrictListNew','ddSubdistrictforsubdistrict','PART',true);" />
													</label>
												</div>
												
												<div class="ms_selection">
													<label>
														<spring:message htmlEscape="true" code="Label.SELECTEDSUBDISTRICT"></spring:message>
													</label>
													<form:select name="select4" id="subDistrictListNew" size="1" multiple="multiple" path="contributedSubDistricts" class="frmtxtarea" htmlEscape="true" >
														<form:options items="${listpostSubDistrictForms}" itemLabel="subdistrictNameEnglish" itemValue="aliasEnglish" />
													</form:select><br></br>
													<input type="button" class="bttn" value="Get Village List" onclick="selectVillageListMarkPesa();" />
												</div>
											</div>
										</li>
										
										
										<li>
											<div class="ms_container">
												<div class="ms_selectable">
													<label>
														<spring:message htmlEscape="true" code="Label.AVAILABLEVILLAGE"></spring:message>
													</label>
													<form:select name="villageList" size="1" id="villageList" path="" multiple="multiple" class="frmtxtarea" >
													</form:select>
												</div>
												
												
												<div class="ms_buttons">
													<label> 
														<input type="button" class="bttn" id="btnaddVillageFullMarkPesa" name="Submit4" value=" Whole &gt;&gt;" />
													</label>
													
													<label> 
														<input name="button22" class="bttn" type="button" onclick="removeOnedistrictOptionForMarkPesa('villageListNew', 'villageList', true)" value="&lt;" />
													</label>
													<label>
														<input name="button22" class="bttn" type="button" onclick="removeAllListForMarkPesa('villageListNew', 'villageList', true)" value="&lt;&lt;" />
													</label>
												</div>
												
												<div class="ms_selection">
													<label>
														<spring:message htmlEscape="true" code="Label.VILLAGELISTSELECTED"></spring:message>
													</label>
													<form:select name="select4" id="villageListNew" size="1" multiple="multiple" path="contributedVillages" class="frmtxtarea">
														<form:options items="${listVill}" itemLabel="villageNameEnglish" itemValue="villageCode" />
													</form:select>							
												
												</div>
											</div>
										</li>
										<li>
											<input type="hidden" name="reorganized" id="reorganized" />
											<input type="hidden" name="modifyVillage" id="modifyVillage" />
											<input type="hidden" name=modifySubDistrict id="modifySubDistrict" />
											<input type="hidden" name="addAnotherSD" id="addAnotherSD" />
											<input type="hidden" name="statusDist" id="statusDist" />
										</li>
									</ul>
								</div>
							</div>
						</div>
					<div class="clear"></div>
					<div class="btnpnl">
						<label>
						<input type="button" class="btn" id="Submit" style="${visibility}" name="Submit" value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" onclick="markPesaSubmit('Next');" /> </label>
						<label>
						<input type="button" name="Submit2" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>" onclick="" /> </label> <label>
						<input type="button" class="btn" name="Submit6" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm'" /> </label> <label>
						<input type="button" class="btn" id="Proceed" style="visibility: hidden" value="<spring:message htmlEscape="true"  code="Button.PROCEED"></spring:message>" onclick="onloadSelect();" /></label>
					</div>
					<form:hidden path="totalVillage" id="totalVillage" value="${totalVillage}"/>
							<form:hidden path="preDistrict" id="preDistrict" value="" />
							<form:hidden path="mergeSelectedVillageListId" id="mergeSelectedVillageListId" value="" />
							<form:hidden path="mergeSubDistId" id="mergeSubDistId" value="" />
							<form:hidden path="mergeVillageListId" id="mergeVillageListId" value="" title="mergeVillageListId"/>
							<form:hidden path="storedCombiValues" id="storedCombiValues" value="" title="storedCombiValues"/>
							<form:hidden path="storedNewCombiValues" id="storedNewCombiValues" value="" title="storedNewCombiValues"/>
							<form:hidden path="districtNameEnglishTemp" id="districtNameEnglishTemp" value="" />
							<form:hidden path="contributedSubDistrictsTemp" id="contributedSubDistrictsTemp" value="" />
							<form:hidden path="preSubDistrictsTemp" id="preSubDistrictsTemp" value="" />
							<form:hidden path="preVillageTemp" id="preVillageTemp" value="" />
							<form:hidden path="buttonClicked" id="buttonClicked" value="" />
							<form:hidden path="historyDistrictList" id="historyDistrictList" value="" />
							<form:hidden path="histrorySubDistrictList" id="histrorySubDistrictList" value="" />
							<form:hidden path="histroryNewSubDistrict" id="histroryNewSubDistrict" value="null" />
							<form:hidden path="histroryNewSubDistrictList" id="histroryNewSubDistrictList" value="null" />
							<form:hidden path="histroryMergeSubDistrictList" id="histroryMergeSubDistrictList" value="null" />
							<form:hidden path="districtNameList" id="districtNameList" value="${districtNameList}"/>
							<!--  Added by sushil on 06-5-2013 -->
							
							<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
				</div>
			</div>
			<div id="dialog-error" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-error" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-error1" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-error1" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-error3" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-error3" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-error2" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-error2" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-merge-success" title="Success Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-merge-success" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-merge-failure" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-merge-failure" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-confirm-create" title="Confirmation Dialog" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-confirm-create" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-confirm" title="Confirmation Dialog" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-confirm" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-district-button" title="Message Dialog" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-district-button" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-confirm-nodata" title="Message Dialog" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-confirm-nodata" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-confirm-dist-name" title="Message Dialog" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-form" htmlEscape="true" text="Please fill mandatory fields in correct format."></spring:message>
					</p>
				</div>
				<div id="dialog-village-button" title="Message Dialog" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-village-button" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-confirm-noname-created-district" title="Message Dialog" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-confirm-noname-created-district" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-confirm-nodata-village" title="Message Dialog" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-confirm-nodata-village" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-error4" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-error4" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-error5" title="Mark PESA Land Regions" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Label.DistrictSelectErrormsg" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-error6" title="Mark PESA Land Regions" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Label.SubDistrictSelectErrormsg" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-error7" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-error7" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-error8" title="Mark PESA Land Regions" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Label.VillageSelectErrormsg" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-error9" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-error9" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-confirm-addanother" title="Add another district?" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-confirm-addanother" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-confirm-next" title="Confirmation Dialog" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-confirm-next" htmlEscape="true" text="Are you sure?"></spring:message>
					</p>
				</div>
				<div id="dialog-duplicate-district" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-duplicate-district" htmlEscape="true"></spring:message>
					</p>
				</div>
				<div id="dialog-duplicate-district-two" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-duplicate-district-two" htmlEscape="true"></spring:message>
					</p>
				</div>
				<!-- Added by sushil on 08-05-2013 -->
				<div id="dialog-error-on-done" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-error-on-done" text="Invalid operation, No subdistrict found to merge" htmlEscape="true"></spring:message>
					</p>
				</div>
				
				<!-- Added by vinay on 10-09-2013 -->
				<div id="dialog-confirm-nodata-subdistt" title="Message Dialog" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						Please select at least one full sub-district for merge villages.
					</p>
				</div>
				<div id="dialog-error-subdistt-select-full" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						Can't select all subdistrict(s) of part district as full.
					</p>
				</div>
				<div id="dialog-error-vill-select-full" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						Can't select all village(s) of part sub-district as full.
					</p>
				</div>
				<!--  added by Vinay Yadav ends here -->
				
					<!-- added by Anju start here-->
				
				<div id="dialog-error10" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-error10" htmlEscape="true"></spring:message>
						<!-- You cann't select all the sub-districts of a district selected as part -->
						
					</p>
				</div>
				<div id="dialog-error11" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
						<!-- canot select all the villages full of a sub district selected as part. -->
						<spring:message code="Error.dialog-error11" htmlEscape="true"></spring:message>
					</p>
				</div>
				
				<!-- ended by Anju Gupta end here-->
				
				
				
		</form:form></c:when>
		<c:otherwise>
		<br/>
		<c:out value="${displayMessage}"></c:out><br/>
		</c:otherwise>
		</c:choose>
		</div>		
</body>
</html>