<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%@include file="../common/taglib_includes.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><spring:message code="Label.INVAlVILL" htmlEscape="true"></spring:message></title>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
<script type="text/javascript" src="js/invalidateVillage.js" charset="utf-8"></script>
<script type="text/javascript" src="js/validation.js"></script>
<script type="text/javascript" src="js/govtorder.js"></script>
	
	

<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrCoveredLandRegionByULBService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
	
<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
	
	function hideAll()
	{
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
		$("#OrderDate_error").hide();
		$("#EffectiveDate_error").hide();
		$("#GazPubDate_error").hide();
		$("#GazPubDate_msg").hide();
				
	}
	function selectall() {

		var selObj = document.getElementById('villageListNewContri');
		//alert("selObj"+selObj);
		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
		}
	}
	
	 function validateform() {
		/* 		alert("validateform"); */
		/* 		alert(document.getElementById('ddDistrict').value);
		 alert(document.getElementById('ddSubdistrict').value);
		 alert(document.getElementById('villageListNewContri').value); */
		var ddDistrict = document.getElementById('ddDistrict').value;
		var ddSubdistrict = document.getElementById('ddSubdistrict').value;
		var villageList = document.getElementById('villageListNewContri').value;
		var ddSubdistrictMerge = document.getElementById('ddSubdistrictMerge').value;
		var villageListMainMerge = document
				.getElementById('villageListMainMerge').value;

		if (ddDistrict == 0) {
			alert("Please Select District");
			document.getElementById('ddDistrict').focus();
			return false;
		}
		if (ddSubdistrict == 0 || ddSubdistrict == "") {
			alert("Please Select Subdistrict");
			document.getElementById('ddSubdistrict').focus();
			return false;
		}

		if (villageList == 0 || villageList == "") {
			alert("Please Select Village To Invalidate");
			document.getElementById('villageListNewContri').focus();
			return false;
		}

		if (document.getElementById('villagedelete_yes').checked) {

			if (ddSubdistrictMerge == 0 || ddSubdistrictMerge == "") {
				alert("Please Select Subdistrict");
				document.getElementById('ddSubdistrictMerge').focus();
				return false;
			}
			if (villageListMainMerge == 0 || villageListMainMerge == "") {
				alert("Please Select Village ");
				document.getElementById('villageListMainMerge').focus();
				return false;
			}

		}

		return true;
	}
	 function onSubmitSave(){
		 document.getElementById('buttonClicked').value = 'S';
		 onSubmit();
	 }
	function onSubmitPublish(){
		document.getElementById('buttonClicked').value = 'P';
		onSubmit();
	 }
	 function onSubmit()
	 {
		 var length= document.getElementById('villageListNewContri').options.length;
		 if (document.getElementById('ddDistrict').value < 1) {
				alert("Please Select District");
				return false;
				
		}else if (document.getElementById('ddSubdistrict').selectedIndex < 1) {
				alert("Please Select Sub District");
				return false;
				
		}else if (length < 1){
				alert("Please Select Villages to Delete ");
				return false;
		}
		selectall();
		var errors = new Array();
		var error = false;
		/* errors[0] = vlidateOnblur('ddDistrict','1','15','c');
	    if(errors[0]==true){
			error = true;
		}
		errors[1] = vlidateOnblur('ddSubdistrict','1','15','c');
		if(errors[1]==true){
			error = true;
		}
		errors[2] = vlidateOnblur('villageListNewContri','1','15','p');
		if(errors[2]==true)
		{
			  error = true;
		}   */
	    if(error == true)
		{
			showClientSideError();
			return false;
		}
		else
		{    
			var villageListContri="";
			var villageListNewContri = $('#villageListNewContri').val();
			for(var i=0;i<villageListNewContri.length;i++)
				villageListContri = villageListContri + villageListNewContri[i] + ",";
			
			lgdDwrVillageService.getMaxPreVersionEffDateOfVillages(villageListContri.substring(0,villageListContri.length-1),{
	 			async : false,
	 			callback : function(data) {
	 				$('#preVersionEffDate').val(data);
	 			},
	 			errorHandler : function() {
	 				alert("Error");
	 			}
	 		});
			if (validateGovtOrderDetailsForVillage()) {
				$('#OrderDate').removeAttr("disabled");
	 		    $('#EffectiveDate').removeAttr("disabled");
	 			$('#gazPubDatecr').removeAttr("disabled");
				$('input[name=Submit]').prop('disabled', true);
				document.forms['invalidateForm'].submit();
				return true;
			}else{
				return false;
			}
			
		}
		return false;
	}		
		  
 function validateSelectAndSubmit() {
		selectall();
		if (validateform()) {

			document.forms['invalidateForm'].submit();
		}
	}  
	

	function saveAsDraft() {
		selectall();
		document.forms['invalidateForm'].action = 'saveAsDraftInvalidateVillage.htm';
		document.forms['invalidateForm'].submit();
	}

	function callGetDraft() {
		document.getElementById('invalidateForm').action = "publishSaveAsDraftInvalidateVillage.htm";
		document.forms['invalidateForm'].submit();
	}

	function getDraftData() {
		if (document.getElementById('ddDistrict').selectedIndex > 0) {
			getSubDistrictandULBList(document.getElementById('ddDistrict').value);
			getInvalidateVillageSubdistricts();
		}
	}

	function getInvalidateVillageSubdistricts() {
		lgdDwrSubDistrictService.getInvalidateVillageSubdistricts({
			async : false,
			callback : handleInvalidateVillageSubdistrictsSuccess
		});
	}

	function handleInvalidateVillageSubdistrictsSuccess(data) {

		var subdistricts = data.split(',');
		//Wait for a split second
		setTimeout('doNothing()', 100);
		document.getElementById('ddSubdistrict').value = subdistricts[0];
		getVillageList(document.getElementById('ddSubdistrict').value);

		if (subdistricts.length > 1) {
			document.getElementById('villagedelete_yes').checked = true;
			toggledisplay('villagedelete_yes', 'fromothersubdistrict');
			document.getElementById('ddSubdistrictMerge').value = subdistricts[1];
			getVillageListMerge(document.getElementById('ddSubdistrictMerge').value);
		}
		var poller = setInterval("hasVillages(" + poller + ")", 50);
	}

	function hasVillages(poller) {
		if (document.getElementById('villageListMainContributing').length > 0) {
			getInvalidateVillages();
			clearInterval(poller);
		}
	}

	function getInvalidateVillages() {
		lgdDwrSubDistrictService.getInvalidateVillages({
			async : false,
			callback : handleInvalidateVillagesSuccess
		});
	}

	function handleInvalidateVillagesSuccess(data) {
		var totalVillages = data.split(':');
		//Wait for a split second
		setTimeout('doNothing()', 100);
		switch (totalVillages.length) {
		case 2:
			document.getElementById('villageListMainMerge').value = totalVillages[1];
		case 1:
			var invalidatedVillageList = totalVillages[0].split(',');
			for ( var i = 0; i < invalidatedVillageList.length; i++) {
				document.getElementById('villageListMainContributing').value = invalidatedVillageList[i];
				addItemForInvalidate('villageListNewContri', 'villageListMainContributing',
						'', false);
			}
		default:
			break;
		}
	}

	function addanother(){
	
		var selObj = document.getElementById('villageListMainContributing');
		var len= 0;
		var length = 0;
		var temp = "";
		//alert("selObj"+selObj);
		for ( var k = 0; k < selObj.options.length; k++) {
	    if (selObj.options[k].selected)
		temp = selObj.options[k].text;
	 	if (temp.indexOf('GP') > -1)
			{
		alert("Invalidating this Village will affect the GP");
	    break;
			}
		}
		
		
		for ( var i = 0; i < selObj.options.length; i++) {
			if(selObj.options[i].selected)
				len++;
		}
		length= document.getElementById('villageListNewContri').options.length;
		len = len + length ;	
		if(len>1)
			{
			
			
			$( "#dialog-confirm" ).modal('show');
			  
	/* 	$( "#dialog:ui-dialog" ).dialog( "destroy" );		
		$( "#dialog-confirm" ).dialog({
			resizable: false,
			height:200,
			width:300,
			modal: true,
			buttons: {
				"Yes": function() {
					addItemForInvalidate('villageListNewContri','villageListMainContributing','',false);
					$( this ).dialog( "close" );
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			}
		});	 */
		
			}
		else
			addItemForInvalidate('villageListNewContri','villageListMainContributing','',false);

	}
		
	$(document).ready(function() {
		
		
		var s = document.getElementById("flag2").value;
		if(s>0)
			getSubDistrictandULBList(s);
		}); 
	
	addItemList=function(copyId, pasteId) {
		$('#'+copyId+'> option:selected').appendTo('#'+pasteId); 
		};
		
		copyAllObjectFormOnetoAnother=function(copyId,pasteId){
			$('#'+copyId+' option').clone().appendTo('#'+pasteId);
			 $('#'+copyId).empty();
		};
	
		
		function modalYes(){
			addItemForInvalidate('villageListNewContri','villageListMainContributing','',false);
			
		}
</script>
</head>
<body onload="hideAll()" onkeypress="disableCtrlKeyCombination(event);" 	onkeydown="disableCtrlKeyCombination(event);">
	<section class="content">
                <div class="row">
                    <section class="col-lg-12">
                     <div class="box">
                    <form:form action="enterInvalidateVillageOrderDetails.htm" id="invalidateForm" method="POST" commandName="invalidatevillage" enctype="multipart/form-data" class="form-horizontal">
			         <form:hidden htmlEscape="true" path="buttonClicked" value="" />
			   			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="enterInvalidateVillageOrderDetails.htm"/>"/>
			   			<input type="hidden" name="stateCode"  id ="stateCode" value="<c:out value='${stateCode}'/>"/>
						<input type="hidden" name="flag1" id="flag1" value="<c:out value='${flag1}'/>"/>
                		<input type="hidden" name="flag2" id="flag2" value="<c:out value='${flag2}'/>"/>
                		<input type="hidden" name="subDistrictCode" id="subDistrictCode" value="<c:out value='${subDistrictCode}'/>"/>
						<input type="hidden" name="districtCode" id="districtCode" value="<c:out value='${districtCode}'/>"/>
						<input type="hidden" name="draftVilCode" id="draftVilCode" value="<c:out value='${draftVilCode}'/>"/>
						<input type="hidden" name="existVilOrUlbFlag" id="existVilOrUlbFlag" value="<c:out value='${existVilOrUlbFlag}'/>"/>
						<input type="hidden" name="isExistGovt" id="isExistGovt" value="<c:out value='${isExistGovt}'/>"/>
						<input type="hidden" name="count" value="" id="count"></input>
						<form:hidden htmlEscape="true" path="buttonClicked"	value="" />	
						<input type="hidden" id="govtfilecount" name="govtfilecount" value="<c:out value='${govtfilecount}'/>"></input>
						<input type="hidden" name="preVersionEffDate" id="preVersionEffDate" />
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.INVALIDATEVILLAGE" htmlEscape="true"></spring:message></h3>
                                </div>
                                 
                        <div class="box-body">
                            <div class="form-group">
                             <c:if test="${flag1 eq 1}">
							         <label class="col-sm-3 control-label"><spring:message  code="Label.SELECTDISTRICT"></spring:message><span class="mandatory">*</span></label>
							 </c:if>
							<c:if test="${flag1 eq 0}">
							     <label class="col-sm-3 control-label"><spring:message  code="Label.DISTRICTCAPS"></spring:message></label>
							</c:if>
                             <div class="col-sm-6">
                                      <form:hidden htmlEscape="true" path="operation" value="I"/>
									  <form:hidden htmlEscape="true" path="govtOrderConfig" value="${govtOrderConfig}"/>
									  <form:select htmlEscape="true" path="districtNameEnglish" id="ddDistrict"  class="form-control"  onchange="getSubDistrictandULBList(this.value);EmptlyVillageList();" onblur="vlidateOnblur('ddDistrict','1','15','c');">
										 <c:if test="${flag1 eq 1}">
												      <form:option value="0"><spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message></form:option>
													  <c:forEach items="${districtList}" var="districtList">
															<c:if test="${districtList.operation_state =='A'.charAt(0)}">
																<form:option value="${districtList.districtCode}" htmlEscape="true" ><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>
																</form:option>
															</c:if>
															<c:if test="${districtList.operation_state =='F'.charAt(0)}">
																<form:option value="${districtList.districtCode}" disabled="true" htmlEscape="true"><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>																				
																</form:option>
															</c:if>
														</c:forEach>
											</c:if>
												
										   <c:if test="${flag1 eq 0}">
											<form:options items="${districtList}" itemLabel="districtNameEnglish" itemValue="districtPK.districtCode" htmlEscape="true" />
									       </c:if>
										</form:select> 
											    
										<div class="mandatory"><form:errors htmlEscape="true" path="districtNameEnglish" ></form:errors></div>
										<div class="mandatory"> </div>
										<span class="mandatory" id="ddDestDistrict_error"> </span>
                              </div>
						 </div> 
						 
						 
						  <div class="form-group">
                             <label class="col-sm-3 control-label" ><spring:message code="Label.SUBDISTRICT" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
                             <div class="col-sm-6">
                                     <form:select htmlEscape="true" path="subdistrictNameEnglish"  id="ddSubdistrict" class="form-control" onchange="EmptlyVillageList();">
									     </form:select> 
										<div class="mandatory"><form:errors htmlEscape="true" path="subdistrictNameEnglish" ></form:errors></div>
                              </div>
						 </div> 
						
					 <div class="form-group">
					 <label class="col-sm-3 "></label>
						  <div class="col-sm-6">
								<label class="checkbox-inline"><input type="checkbox" value="true" onclick="getInvalidateVillageList(1);" name="lgd_LBExistCheck" id="MapVillagelistchek"/> Select from Mapped Regions(Villages) </label>
								<label class="checkbox-inline"><input type="checkbox" value="true" onclick="getInvalidateVillageList(2);" name="lgd_LBExistCheck" id="UnmapVillagelistchek"/><spring:message  code="Label.UnmappedRegion" htmlEscape="true"></spring:message> </label>
							</div>							
						</div>
						
			     <div class="ms_container row" style="margin-left: 10px;">
	           		<div class="ms_selectable col-sm-5 form-group">
		               <label><spring:message code="Label.AVAILVILLAGELIST" htmlEscape="true"></spring:message> </label>
		             <form:select htmlEscape="true" name="select9" id="villageListMainContributing" path="villageList" multiple="multiple"  class="form-control"></form:select>
		           </div>
				  <div class="ms_buttons col-sm-2"><br><br>
						<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnaddVillageFull" name="Submit4" value="Select Villages&gt;&gt;"  onclick="addanother();">Select Villages&gt;&gt;</button>
						<button type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveOneVillage" name="Submit4" value=" &lt;"   onclick="addItemList('villageListNewContri','villageListMainContributing')"><i class="fa fa-angle-double-left" aria-hidden="true"></i> </button>
					    <button type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;" class="btn" onclick="copyAllObjectFormOnetoAnother('villageListNewContri','villageListMainContributing')"><i class="fa fa-angle-double-left" aria-hidden="true"></i>  <i class="fa fa-angle-double-left" aria-hidden="true"></i> </button>
				 </div>
			  <div class="ms_selection col-sm-5">
			  <div class="form-group">
			     <label for="ddDestBlock"><spring:message code="Label.VILLAGESTOINVALIDATE" htmlEscape="true"></spring:message></strong><span class="mandatory">*</span></label> 
			        <form:select htmlEscape="true" name="select4" id="villageListNewContri"  multiple="multiple" path="invalidateVillageList" class="form-control" ></form:select>
						<div class="mandatory"><form:errors htmlEscape="true" path="invalidateVillageList" ></form:errors></div>
						
		       </div>				
              </div>
            </div>
			
			
	   <div class="box-header subheading">
               <h4 ><spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message></h4>
         </div>	
         <div class="form-group" >
            <%@ include file="../govtbody/ExistingGovernmentOrderVillagecp.jsp"%>	
         </div>
         
         <div class="form-group">
           <label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message> <span class="mandatory">*</span></label>
           <div class="col-sm-6">
            <form:input path="orderNo" htmlEscape="true" id="OrderNo" type="text" class="form-control"  maxLength="60" onfocus="helpMessage(this,'OrderNo_error');" onblur="vlidateOrderNo('OrderNo','1','60');hideHelp();" onkeypress="return validateaGovtOrderNO(event);" /> 
             <div id="OrderNo_error" class="mandatory"> <spring:message code="error.required.ORDERNUM" htmlEscape="true"></spring:message> </div>
			 <div id="OrderNo_msg" class="mandatory"> <spring:message code="error.required.ORDERINPUTTYPE" text="Please Enter AlphaNumerics Space . / - ( ) Only" htmlEscape="true" /> </div>
			 <div class="mandatory" id="OrderNo_error1"> <form:errors path="orderNo" htmlEscape="true" /> </div>
			 <div class="mandatory" id="OrderNo_error2" style="display: none"></div>
          
            </div>
          </div>	
		
		 <div class="form-group">
			<label for="OrderDate" class="col-sm-3 control-label"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
			<div class="col-sm-6">
				<div class="input-group date datepicker" id="bOrderDate">
				 <form:input path="orderDate" readonly="true" id="OrderDate" type="text" class="form-control" onchange="setEffectiveDate(this.value);" onkeypress="validateNumeric();" onfocus="validateOnFocus('OrderDate');helpMessage(this,'OrderDate_msg');" onblur="vlidateOnblur('OrderDate','1','15','m');hideHelp();"	onkeyup="hideMessageOnKeyPress('OrderDate')" /> 
				<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				</div>
					<div id="OrderDate_error" class="mandatory"> <spring:message code="error.required.ORDERDATE" htmlEscape="true"></spring:message> </div>
					<div id="OrderDate_msg" style="display: none"> <spring:message code="error.required.ORDERDATE" htmlEscape="true" /> </div>
					<div class="mandatory" id="OrderDate_error1"> <form:errors path="orderDate" htmlEscape="true" /> </div>
				<div class="mandatory" id="OrderDate_error2" style="display: none"></div>
			</div>
		</div>
				
		<div class="form-group">
			<label for="EffectiveDate" class="col-sm-3 control-label"><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
			<div class="col-sm-6">
				<div class="input-group date datepicker" id="bEffectiveDate">
				 	 <form:input id="EffectiveDate" readonly="true" path="effectiveDate" type="text" class="form-control"  onkeypress="validateNumeric();" onfocus="validateOnFocus('EffectiveDate');helpMessage(this,'EffectiveDate_msg');" onblur="vlidateOnblur('EffectiveDate','1','15','m');hideHelp();"   onkeyup="hideMessageOnKeyPress('EffectiveDate')" /> 
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				</div>
				<div id="EffectiveDate_error" class="mandatory"> <spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"></spring:message> </div>
				<div id="EffectiveDate_msg" style="display: none"> <spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true" /> </div>
				<div class="mandatory" id="EffectiveDate_error1"> <form:errors path="effectiveDate" htmlEscape="true" /> </div>
				<div class="mandatory" id="EffectiveDate_error2" style="display: none"></div>
				<span id="errEffectiveDate"></span>
			</div>
		</div>			
						
	
		
		
		<c:if test="${govtOrderConfig == 'govtOrderUpload'}">	
		<div class="form-group">
			<label for="GazPubDate" class="col-sm-3 control-label"><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message> </label>
			 <div class="col-sm-6">
				 <div class="input-group date datepicker" id="bGazPubDate">
		 			 <form:input id="GazPubDate" path="gazPubDate" type="text" class="form-control" onkeypress="validateNumeric();" onfocus="validateOnFocus('GazPubDate');helpMessage(this,'GazPubDate_msg');" onblur="vlidateOnblur('GazPubDate','1','15','m');hideHelp();"
							onkeyup="hideMessageOnKeyPress('GazPubDate')" />
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				</div>
			
				<div id="GazPubDate_error" class="mandatory"> <spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true"></spring:message> </div>
				<div id="GazPubDate_msg" style="display: none"> <spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true" /> </div>
				<div class="mandatory" id="GazPubDate_error1"> <form:errors path="gazPubDate" htmlEscape="true" /> </div>
				<div class="mandatory" id="GazPubDate_error2" style="display: none"></div>	
			</div>			
		</div>			
		
         </c:if>					
						
						
						
		<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
						<div id="divGazettePublicationUpload"><%@ include file="../common/attachmentgovtcp.jspf"%></div>
		</c:if>			
			
   <c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
	<div class="form-group">										
	 <label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELGOT"></spring:message><span class="mandatory">*</span></label>
	  <form:select path="templateList" id="templateList" class="form-control" onblur="vlidateOnblur('templateList','1','15','m');hideHelp();" onfocus="validateOnFocus('templateList');helpMessage(this,'templateList_msg');"
						onkeyup="hideMessageOnKeyPress('templateList')">
						<form:option value="0" htmlEscape="true"><spring:message htmlEscape="true"  code="Error.templateselect"></spring:message></form:option>
						<form:options items="${templateList}" htmlEscape="true" itemLabel="templateNameEnglish" itemValue="templateCode"></form:options>
			</form:select> 
						<div id="templateList_error" class="mandatory"><spring:message code="error.blank.template" htmlEscape="true"></spring:message></div>
						<div id="templateList_msg" style="display: none"><spring:message code="error.blank.template" htmlEscape="true" /></div>
						<div class="mandatory" id="templateList_error1"><form:errors path="templateList" htmlEscape="true" /></div>
						<div class="mandatory" id="templateList_error2" style="display: none"></div>
	</div>
  </c:if>	


     <!-- <div id="dialog-confirm" title="Invalidate Multiple Villages?" style="display: none">
			 <p><span class="ui-icon ui-icon-alert ms_alert_icon" ></span> Are you confirmed  to Invalidate Multiple Villages ?</p>
	 </div>	 -->
	 
	  <div class="modal fade" id="dialog-confirm" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Invalidate Multiple Villages?</h4>
        </div>
        <div class="modal-body" id="customAlertbody">
           Are you confirmed  to Invalidate Multiple Villages ?
        </div>
        <div class="modal-footer">
         <button type="button" class="btn btn-default" data-dismiss="modal" onclick="modalYes();">Yes</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Ok</button>
        </div>
      </div>
      
    </div>
  </div>
 <!--  ---------modal end------ -->
	  <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                 <div id="drafthide" style="display: none;"><input type="button" onclick=" onSubmitSave(); " name="Submit1" class="btn btn-success" value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" /> </div>
				 <input type="button" onclick=" onSubmitPublish(); " name="Submit" class="btn btn-success" value="<spring:message code="Label.PUBLISH" htmlEscape="true"></spring:message>" />
				 <input type="button" name="Submit6" class="btn btn-danger" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />

                 </div>
           </div>   
       </div> 								
			
</div></div>
</form:form>
					</div>
					</section>
				</div>
	</section>
<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>