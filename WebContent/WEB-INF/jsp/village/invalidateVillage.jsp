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
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
<title><spring:message code="Label.INVAlVILL" htmlEscape="true"></spring:message>
</title>
<script type="text/javascript" src="js/invalidateVillage.js"
	charset="utf-8"></script>
	
	
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrCoveredLandRegionByULBService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
	
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<link href="css/error.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/govtorder.js"></script>
<script type="text/javascript" src="datepicker/jquery-1.6.2.js"
	charset="utf-8"></script>
<script type="text/javascript" src="datepicker/jquery.ui.core.js"></script>
<script type="text/javascript" src="datepicker/jquery.ui.widget.js"></script>
<script type="text/javascript" src="datepicker/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="datepicker/calender.js"></script>
<!-- <script type="text/javascript" src="/js/common.js" charset="utf-8"></script> -->
<link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<!-- <script src="js/trim-jquery.js"></script> -->
<link rel="stylesheet" href="datepicker/demos.css" />

<script type="text/javascript" language="javascript">


</script>
	
<title>Create Vilage</title>
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
		$( "#dialog:ui-dialog" ).dialog( "destroy" );		
		$( "#dialog-confirm" ).dialog({
			resizable: false,
			height:140,
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
		});	
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
	
</script>
</head>
<body onload="hideAll()" onkeypress="disableCtrlKeyCombination(event);"
		onkeydown="disableCtrlKeyCombination(event);">
		<div class="overlay" id="overlay1" style="display: none;"></div>
		<div class="box" id="box1">
			<a class="boxclose" id="boxclose1"></a>
			<div>
				<c:if test="${!empty param.family_msg}">
					<table>
						<tr>
							<td rowspan="2"><center>
									<div class="success"></div>
								</center></td>

							<td><div class="helpMsgHeader" style="width: 275px;">
									<h4>Success Message</h4>
								</div></td>
						</tr>
						<tr>
							<td><div id="successMsg" class="successfont">
									<center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center>
								</div>
							</td>
						</tr>
					</table>

				</c:if>

				<c:if test="${!empty family_error}">

					<table>
						<tr>
							<td rowspan="2"><div class="failur"></div></td>

							<td><center>
									<div class="helpMsgHeader" style="width: 275px;">
										<b>Failure Message</b>
									</div>
								</center></td>
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
						<td rowspan="2"><div class="errorImg"></div></td>
						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Error Message</h4>
							</div></td>
					</tr>
					<tr>
						<td><div class="errorfont"><spring:message htmlEscape="true" code="Error.entermandatoryfield"></spring:message></div>
						</td>
					</tr>
				</table>

			</div>
		</div>
	<div id="frmcontent">
		<div class="frmhd">
			
			<h3 class="subtitle"><label><spring:message code="Label.INVALIDATEVILLAGE" htmlEscape="true"></spring:message></label></h3>
										 <ul id="showhelp" class="listing">
					 				        <%--//these links are not working  <li>
					 				         <a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /> </a>           
					 				        </li>
					 				        
					 				        <li>
					 				        	<a href="#" class="frmhelp"><spring:message	code="Button.HELP" htmlEscape="true"></spring:message> </a>
					 				        </li> --%>
					 				     
					 			        </ul>
		
			
		</div>
		<div class="clear"></div>

		<div class="frmpnlbrdr">

			<form:form action="enterInvalidateVillageOrderDetails.htm" id="invalidateForm" method="POST" commandName="invalidatevillage" enctype="multipart/form-data">
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
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.INVALIDATEVILLAGE" htmlEscape="true"></spring:message>
							</div>
							
							<div>
							<ul class="blocklist">
								<li>
								<c:if test="${flag1 eq 1}">
							<label><spring:message  code="Label.SELECTDISTRICT"></spring:message><span class="errormsg">*</span></label><br /><br />
							</c:if>
							<c:if test="${flag1 eq 0}">
							<label><spring:message  code="Label.DISTRICTCAPS"></spring:message></label><br /><br /></label>
							</c:if>
									<form:hidden htmlEscape="true" path="operation" value="I"/>
										<form:hidden htmlEscape="true" path="govtOrderConfig" value="${govtOrderConfig}"/>
										<form:select htmlEscape="true" path="districtNameEnglish" id="ddDistrict" cssClass="combofield"
												             onchange="getSubDistrictandULBList(this.value);EmptlyVillageList();" onblur="vlidateOnblur('ddDistrict','1','15','c');">
												             <c:if test="${flag1 eq 1}">
												<form:option value="0"><spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message></form:option>
												<%-- <form:options items="${districtList}" itemLabel="districtNameEnglish" itemValue="districtPK.districtCode" /> --%>
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
											    </form:select> </label> <br />
											    <div class="errormsg"><form:errors htmlEscape="true" path="districtNameEnglish" ></form:errors></div>
										<div class="errormsg">
										</div>
										<span class="errormsg" id="ddDestDistrict_error">
									    </span>
								
								</li>
								<li>
										<label><spring:message code="Label.SUBDISTRICT" htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br /><br />
										<label> <form:select htmlEscape="true" path="subdistrictNameEnglish"  id="ddSubdistrict" cssClass="combofield"
										 onchange="EmptlyVillageList();">
											   </form:select> </label>
										<div class="errormsg"><form:errors htmlEscape="true" path="subdistrictNameEnglish" ></form:errors></div>
								
								</li>
								
								<li>
										<div class="errormsg">
										</div>
								<input type="checkbox" value="true" onclick="getInvalidateVillageList(1);" name="lgd_LBExistCheck" id="MapVillagelistchek"/>
								<label><%-- <spring:message  code="Label.UnmappedRegion" htmlEscape="true"></spring:message> --%> 
								Select from Mapped Regions(Villages) 
														</label>	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="checkbox" value="true" onclick="getInvalidateVillageList(2);" name="lgd_LBExistCheck" id="UnmapVillagelistchek"/>
								<label><spring:message  code="Label.UnmappedRegion" htmlEscape="true"></spring:message> 
														</label>	
														<br />
														<div class="errormsg">
										</div>
										
								</li>
								<li></li>
								<li></li>
								<li></li>
							</ul>
							
							</div>	
							<ul class="blocklist">
								<li>
									<div class="ms_container">
										<div class="ms_selectable">
												<label><spring:message code="Label.AVAILVILLAGELIST" htmlEscape="true"></spring:message> </label>
												
												<form:select htmlEscape="true" name="select9" size="1" id="villageListMainContributing" path="villageList" multiple="multiple" class="frmtxtarea"></form:select>
												
										</div>
										<div class="ms_buttons">
													<label> 
															    <input type="button" class="bttn" id="btnaddVillageFull" name="Submit4" value="Select Villages&gt;&gt;" 
																	   onclick="addanother();" />
															</label>
														<label> 
															    <input type="button" class="bttn" id="btnremoveOneVillage" name="Submit4" value=" &lt; " class="btn" 
																	   onclick="addItemList('villageListNewContri','villageListMainContributing')" />
															</label>	
															
															<label> <input
																	type="button" class="bttn" id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;" class="btn" 
																	onclick="copyAllObjectFormOnetoAnother('villageListNewContri','villageListMainContributing')" />
															</label>
													
										</div>
										<div class="ms_selection">
										
										<strong><spring:message code="Label.VILLAGESTOINVALIDATE" htmlEscape="true"></spring:message></strong><span class="errormsg">*</span><br />
										<form:select htmlEscape="true" name="select4" id="villageListNewContri" size="1" multiple="multiple" path="invalidateVillageList" class="frmtxtarea" ></form:select>
												  <div class="errormsg"><form:errors htmlEscape="true" path="invalidateVillageList" ></form:errors></div>
										
										</div>
										
									
									</div>
								</li>
							</ul>
							
											<div class="clear"></div>
						</div>
						<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message>
							</div>
							<%@ include file="../govtbody/ExistingGovernmentOrderVillage.jsp"%>
							<br /> <br />
							
							
							
							
						<div >							
							<ul class="blocklist">
							
								<li>								
											<label><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message> </label><span class="errormsg">*</span><br /> 
											<form:input path="orderNo" htmlEscape="true" id="OrderNo" type="text" class="frmfield" style="width: 140px" maxLength="60" onfocus="helpMessage(this,'OrderNo_error');" onblur="vlidateOrderNo('OrderNo','1','60');hideHelp();" onkeypress="return validateaGovtOrderNO(event);" /> 											

										<div id="OrderNo_error" class="error">
											<spring:message code="error.required.ORDERNUM"
												htmlEscape="true"></spring:message>
										</div>
										<div id="OrderNo_msg" class="error">
											<spring:message code="error.required.ORDERINPUTTYPE"
												text="Please Enter AlphaNumerics Space . / - ( ) Only"
												htmlEscape="true" />
										</div>
										<div class="errormsg" id="OrderNo_error1">
											<form:errors path="orderNo" htmlEscape="true" />
										</div>
										<div class="errormsg" id="OrderNo_error2"
											style="display: none"></div>
								</li>
								<li>
										<label><spring:message code="Label.ORDERDATE"
												htmlEscape="true"></spring:message> </label><span class="errormsg">*</span><br />
										<form:input path="orderDate" readonly="true" id="OrderDate"
											type="text" class="frmfield" style="width: 140px"
											onchange="setEffectiveDate(this.value);"
											onkeypress="validateNumeric();"
											onfocus="validateOnFocus('OrderDate');helpMessage(this,'OrderDate_msg');"
											onblur="vlidateOnblur('OrderDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('OrderDate')" /> 

										<div id="OrderDate_error" class="error">
											<spring:message code="error.required.ORDERDATE"
												htmlEscape="true"></spring:message>
										</div>
										<div id="OrderDate_msg" style="display: none">
											<spring:message code="error.required.ORDERDATE"
												htmlEscape="true" />
										</div>
										<div class="errormsg" id="OrderDate_error1">
											<form:errors path="orderDate" htmlEscape="true" />
										</div>
										<div class="errormsg" id="OrderDate_error2"
											style="display: none"></div>
								</li>
								<li>
										<label><spring:message
												code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message>
									</label><span class="errormsg">*</span><br /> <form:input
											id="EffectiveDate" readonly="true" path="effectiveDate"
											type="text" class="frmfield" style="width: 140px"
											onkeypress="validateNumeric();"
											onfocus="validateOnFocus('EffectiveDate');helpMessage(this,'EffectiveDate_msg');"
											onblur="vlidateOnblur('EffectiveDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('EffectiveDate')" /> 
										<div id="EffectiveDate_error" class="error">
											<spring:message code="error.required.EFFECTIVEDATE"
												htmlEscape="true"></spring:message>
										</div>
										<div id="EffectiveDate_msg" style="display: none">
											<spring:message code="error.required.EFFECTIVEDATE"
												htmlEscape="true" />
										</div>
										<div class="errormsg" id="EffectiveDate_error1">
											<form:errors path="effectiveDate" htmlEscape="true" />
										</div>
										<div class="errormsg" id="EffectiveDate_error2"
											style="display: none"></div>
								</li>

								<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
								<li>
											<label><spring:message
													code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>
											</label><br /> <form:input id="GazPubDate" path="gazPubDate"
												type="text" class="frmfield" style="width: 140px"
												onkeypress="validateNumeric();"
												onfocus="validateOnFocus('GazPubDate');helpMessage(this,'GazPubDate_msg');"
												onblur="vlidateOnblur('GazPubDate','1','15','m');hideHelp();"
												onkeyup="hideMessageOnKeyPress('GazPubDate')" /> <%-- <span class="errormsg" id="GazPubDate_error"></span> <form:errors
												path="gazPubDate2" cssClass="errormsg" htmlEscape="true"></form:errors> <span
											id="GazPubDate_msg" style="display: none">Please Enter
												Gazette Publication Date Like 12-04-2012</span> --%>
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
								</c:if>
								
								<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
									<li id="divGazettePublicationUpload">
											<%@ include file="../common/attachmentgovt.jspf"%>
									</li>
								</c:if>
								
								<c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
									<li>										
											<label><spring:message
													htmlEscape="true" code="Label.SELGOT"></spring:message> </label><span
											class="errormsg">*</span><br /> <form:select
												path="templateList" id="templateList" style="width:280px"
												class="combofield"
												onblur="vlidateOnblur('templateList','1','15','m');hideHelp();"
												onfocus="validateOnFocus('templateList');helpMessage(this,'templateList_msg');"
												onkeyup="hideMessageOnKeyPress('templateList')">
												<form:option value="0" htmlEscape="true">
													<spring:message htmlEscape="true"
														code="Error.templateselect"></spring:message>
												</form:option>
												<form:options items="${templateList}" htmlEscape="true"
													itemLabel="templateNameEnglish" itemValue="templateCode"></form:options>
											</form:select> <%-- <span class="errormsg" id="templateList_error"></span> <span><form:errors
													cssClass="errormsg" path="templateList" htmlEscape="true"></form:errors> </span> <span
											style="display: none;" id="templateList_msg">Please
												Select Government order Template</span> --%>
											<div id="templateList_error" class="error">
												<spring:message code="error.blank.template"
													htmlEscape="true"></spring:message>
											</div>
											<div id="templateList_msg" style="display: none">
												<spring:message code="error.blank.template"
													htmlEscape="true" />
											</div>
											<div class="errormsg" id="templateList_error1">
												<form:errors path="templateList" htmlEscape="true" />
											</div>
											<div class="errormsg" id="templateList_error2"
												style="display: none"></div>
									</li>
								</c:if>														
							</ul>												
						</div>							
					</div>
				</div>
			</div>

				<!-- End of Govt Order Config -->
					</div>
				

								 <div id="dialog-confirm" title="Invalidate Multiple Villages?" style="display: none">
									<p><span class="ui-icon ui-icon-alert ms_alert_icon" ></span> Are you confirmed  to Invalidate Multiple Villages ?</p>
								</div>
								<div class="btnpnl">
								 
										<label> 
										    <input type="button" onclick=" onSubmitSave(); " name="Submit" class="bttn" value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" />
										    <input type="button" onclick=" onSubmitPublish(); " name="Submit" class="bttn" value="<spring:message code="Label.PUBLISH" htmlEscape="true"></spring:message>" />
										</label> 
										<label>
										<input type="button" name="Submit6" class="bttn" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
											       onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
										
					</div>

					
		
						</div>
					</div>



						</div>
					</div>

					

				</div>
			</form:form>
			 <script src="/LGD/JavaScriptServlet"></script>

			</td>
			</tr>
			</table>
		</div>
		</div>
</body>
</html>