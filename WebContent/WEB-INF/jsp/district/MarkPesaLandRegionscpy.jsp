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
</head>
<body >
<%@include file="addDistrict.jsp"%>
<section class="content">
	<div class="row">
		<section class="col-lg-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title"><spring:message htmlEscape="true" code="Label.MarkPesaLandRegions"></spring:message></h3>
				</div>
				<div class="clear"></div>
				
		<div class="box-body">
			<c:choose>
			<c:when test="${displayForm}">
			<form:form action="markPesaLandRegion.htm" class="form-horizontal" method="POST" commandName="district" id="form1" name="form1" enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="markPesaLandRegion.htm"/>" />
				<div id="DistrictdetailId" class="form-control" style="${display}"></div>
				<div>
					<div style="${style}">
					
			<div class="ms_container row" style="margin-left: 10px;">
					<div class="ms_selectable col-sm-5 form-group">
						<label><spring:message htmlEscape="true" code="Label.AVAILABLEDISTRICT"></spring:message></label>
						<form:select path="districtsourcecode" class="form-control" id="ddSourceDistrict" multiple="true">
							 <form:options items="${distList}" itemLabel="districtNameEnglish" itemValue="districtCode" />
						</form:select>
					</div>
					
					<div class="ms_buttons col-sm-2"><br>
						<button type="button" id="src2Target1" class="btn btn-primary btn-xs btn-block" aria-hidden="true"  onclick="addItem('ddDestDistrict','ddSourceDistrict','FULL',true);">Whole<i class="fa fa-angle-double-right" aria-hidden="true" ></i>
						<button type="button" id="target2Src1" class="btn btn-primary btn-xs btn-block" aria-hidden="true"  onclick="removeOnedistrictOptionForMarkPesa('ddDestDistrict', 'ddSourceDistrict', true);"><i class="fa fa-angle-left" aria-hidden="true"></i>
						<button type="button" id="target2Src2" class="btn btn-primary btn-xs btn-block" aria-hidden="true"  onclick="removeAllListForMarkPesa('ddDestDistrict', 'ddSourceDistrict', true);" ><i class="fa fa-angle-double-left" aria-hidden="true"></i>
						<button type="button" id="src2Target2" class="btn btn-primary btn-xs btn-block" aria-hidden="true"  onclick="addItem('ddDestDistrict','ddSourceDistrict', 'PART',true);" >Part<i class="fa fa-angle-double-right" aria-hidden="true"></i>							
					</div>
					
					<div class="ms_selection col-sm-5">
						 <div class="form-group">
							<label><spring:message htmlEscape="true" code="Label.DISTRICTLISTSELECTED"></spring:message></label> 
							<form:select name="select4" id="ddDestDistrict"  multiple="multiple" path="districtNameEnglish" class="form-control" >
								 <form:options items="${listDistrict}" itemLabel="districtNameEnglish" itemValue="aliasEnglish" />
							</form:select>
							<input name="button2" class="btn btn-info" type="button" onclick="selectsubdistMarkPesa()" value="Get Sub-district List" />
												
						</div>				
					</div>
			</div>
			
			
			
			<div class="ms_container row" style="margin-left: 10px;">
					<div class="ms_selectable col-sm-5 form-group">
						<label><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"></spring:message></label>
						<form:select name="ddSubdistrictforsubdistrict" id="ddSubdistrictforsubdistrict" path="" multiple="multiple" class="form-control">
							<form:options items="${preSubDistrictList}" itemLabel="subdistrictNameEnglish" itemValue="subdistrictCode" />
						</form:select>
					</div>
					
					<div class="ms_buttons col-sm-2"><br>
						<button type="button" id="btnaddSubDistrictFullMarkPesa" name="Submit4" class="btn btn-primary btn-xs btn-block" aria-hidden="true">Whole<i class="fa fa-angle-double-right" aria-hidden="true" ></i>
						<button type="button" id="buttonRemove1" class="btn btn-primary btn-xs btn-block" aria-hidden="true"  onclick="removeOnedistrictOptionForMarkPesa('subDistrictListNew', 'ddSubdistrictforsubdistrict', true)"><i class="fa fa-angle-left" aria-hidden="true"></i>
						<button type="button" id="buttonRemove2" class="btn btn-primary btn-xs btn-block" aria-hidden="true"  onclick="removeAllListForMarkPesa('subDistrictListNew', 'ddSubdistrictforsubdistrict', true)"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
						<button type="button" id="btnaddSubDistrictPart" name="Submit4" class="btn btn-primary btn-xs btn-block" aria-hidden="true"  onclick="addItem('subDistrictListNew','ddSubdistrictforsubdistrict','PART',true);">Part<i class="fa fa-angle-double-right" aria-hidden="true"></i>							
					</div>
					
					<div class="ms_selection col-sm-5">
						 <div class="form-group">
							<label><spring:message htmlEscape="true" code="Label.SELECTEDSUBDISTRICT"></spring:message></label> 
							<form:select name="select4" id="subDistrictListNew" multiple="multiple" path="contributedSubDistricts" class="form-control" htmlEscape="true" >
								<form:options items="${listpostSubDistrictForms}" itemLabel="subdistrictNameEnglish" itemValue="aliasEnglish" />
							</form:select>
							<input type="button" class="btn btn-info" value="Get Village List" onclick="selectVillageListMarkPesa();" />
												
						</div>				
					</div>
			</div>
			
			
			
			
			<div class="ms_container row" style="margin-left: 10px;">
					<div class="ms_selectable col-sm-5 form-group">
						<label><spring:message htmlEscape="true" code="Label.AVAILABLEVILLAGE"></spring:message></label>
						<form:select name="villageList" id="villageList" path="" multiple="multiple" class="form-control" ></form:select>
					</div>
					
					<div class="ms_buttons col-sm-2"><br></br>
						<button type="button" id="btnaddVillageFullMarkPesa" name="Submit4" class="btn btn-primary btn-xs btn-block" aria-hidden="true">Whole<i class="fa fa-angle-double-right" aria-hidden="true" ></i>
						<button type="button" name="button22" class="btn btn-primary btn-xs btn-block" aria-hidden="true"  onclick="removeOnedistrictOptionForMarkPesa('villageListNew', 'villageList', true)"><i class="fa fa-angle-left" aria-hidden="true"></i>
						<button type="button" name="button22" class="btn btn-primary btn-xs btn-block" aria-hidden="true"  onclick="removeAllListForMarkPesa('villageListNew', 'villageList', true)" ><i class="fa fa-angle-double-left" aria-hidden="true"></i>
				   </div>
					
					<div class="ms_selection col-sm-5">
						 <div class="form-group">
							<label><spring:message htmlEscape="true" code="Label.VILLAGELISTSELECTED"></spring:message></label> 
							<form:select name="select4" id="villageListNew" multiple="multiple" path="contributedVillages" class="form-control">
								<form:options items="${listVill}" itemLabel="villageNameEnglish" itemValue="villageCode" />
							</form:select>
							<input type="hidden" name="reorganized" id="reorganized" />
											<input type="hidden" name="modifyVillage" id="modifyVillage" />
											<input type="hidden" name=modifySubDistrict id="modifySubDistrict" />
											<input type="hidden" name="addAnotherSD" id="addAnotherSD" />
											<input type="hidden" name="statusDist" id="statusDist" />
												
						</div>				
					</div>
			</div>
		</div>
	  
	  <div class="clear"></div>
	  
	  <div class="box-footer">
        <div class="col-sm-offset-2 col-sm-10">
          <div class="pull-right">
              <button id="Submit" style="${visibility}" type="button" onclick="markPesaSubmit('Next');" name="Submit" class="btn btn-success"><i class="fa fa-floppy-o"></i>  <spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button>
			  <button type="button" name="Submit2" class="btn btn-warning" onclick=""><spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button>
			  <button type="button" name="Submit6" class="btn btn-info" onclick="javascript:location.href='home.htm'"><i class="fa fa-times-circle"></i>  <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
			  <button id="Proceed" style="visibility: hidden" type="button" class="btn btn-primary" onclick="onloadSelect();"><spring:message htmlEscape="true"  code="Button.PROCEED"></spring:message></button>	
		  </div>
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
      		<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
	</div>
	</div>
	</form:form>
	</c:when>
		<c:otherwise>
		<br/>
		<c:out value="${displayMessage}"></c:out><br/>
		</c:otherwise>
	</c:choose>
	</div>       	
</div>
</section>
</div>
</section>
</body>
</html>