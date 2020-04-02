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

<%-- <script src="<%=contextpthval %>/jquery-1.8.3.js"></script>--%>
<script src="js/orderValidate.js"></script> 
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>

	


<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>
<!-- <script src="js/dynCalendar.js" type="text/javascript"></script> -->
<script src="js/browserSniffer.js" type="text/javascript"></script>

<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script> 
<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>

<!-- <script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script> -->


<!-- <script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script> -->

<script>

$(function() {
	//date picker
	var cureffectiveDate=$("#EffectiveDate").val();
	//alert(cureffectiveDate);
	$("#bOrderDate").datetimepicker({
			format: 'dd-mm-yyyy',
			startView : 'month',
			endDate: cureffectiveDate,
	        autoclose: true,
			minView : 'month',
			pickerPosition : "bottom-left",
			
		});
	
	$("#bgazPubDatecr").datetimepicker({
		format: 'dd-mm-yyyy',
		startView : 'month',
		endDate: cureffectiveDate,
        autoclose: true,
		minView : 'month',
		pickerPosition : "bottom-left",
		
	});
	
	
});
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
		
		<section class="content">
			<div class="row">
		        	<!-- main col -->
		              <section class="col-lg-12 ">
			              	<div class="box ">
			           			<div class="box-header with-border">
			
			               			<h3 class="box-title"><spring:message htmlEscape="true" code="Label.MODIFYBLOCK"></spring:message></h3>
			           			</div>
				                <div class="box-body">
									<div class="box-header subheading">
				                              <h4><spring:message htmlEscape="true" code="Label.CORRECTION"></spring:message></h4>
				                    </div>
				                    <form:form action="modifyBlockCrAction.htm" method="POST" class="form-horizontal" commandName="modifyBlockCmd" id="frmModifySubDistrict" name="frmModifySubDistrict" enctype="multipart/form-data">
										<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyBlockCrAction.htm"/>" />
										<input type="hidden" id="govtfilecount" name="govtfilecount" value="<c:out value='${govtfilecount}' escapeXml='true'></c:out>"></input>
										<input type="hidden" id="mapfilecount" name="mapfilecount" value="<c:out value='${mapfilecount}' escapeXml='true'></c:out>"></input>
										<input type="hidden" name="warningFlag" value="<c:out value='${pageWarningEntiesFlag}' escapeXml='true'></c:out>"/>  
										<c:forEach 	var="listBlockDetails" varStatus="listBlockDetailsRow" items="${modifyBlockCmd.listBlockDetails}">
										
										<div class="form-group">
	                      					<label  class="col-sm-3 control-label"><spring:message code="Label.NAMEOFNEWBLOCKENGLISH" htmlEscape="true"></spring:message> </label>
		                      				<div class="col-sm-6">
												 <spring:bind 	path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].blockNameEnglish">
											     	<input 	type="text" class="form-control" readonly="readonly" name="<c:out value="${status.expression}" />"
													value="<c:out value="${status.value}" escapeXml="true" />"/> 
												 </spring:bind> 
												<spring:bind 	path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].blockCode">
													<input 	type="hidden" class="form-control" name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true"/>" />
												</spring:bind>
		                      				</div>
	                    				</div>
	                    				
	                    				<c:if test="${pageWarningEntiesFlag==true}">
		                    				<div class="form-group">
		                      					<label  class="col-sm-3 control-label"><spring:message code="Label.WARNINGFLAGSTATUS" htmlEscape="true"></spring:message> </label>
			                      				<div class="col-sm-6">
													 <img src="images/ylw_flg.png" width="13" height="9" />
			                      				</div>
		                    				</div>
										</c:if>
										
										
										<div class="form-group">
	                      					<label  class="col-sm-3 control-label"><spring:message code="Label.NAMEOFNEWBLOCKLOCAL" htmlEscape="true"></spring:message> </label>
		                      				<div class="col-sm-6">
												 <spring:bind 	path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].blockNameLocal">
												 	<input type="text" class="form-control" readonly="readonly" name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}"  escapeXml="true"/>"/>
												</spring:bind> 
		                      				</div>
	                    				</div>
	                    				
	                    				
	                    				<div class="form-group">
	                      					<label  class="col-sm-3 control-label"><spring:message code="Label.ALIASENGLISH" htmlEscape="true"></spring:message> </label>
		                      				<div class="col-sm-6">
												 <spring:bind path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].aliasEnglish">
															<input type="text" 
																   class="form-control"  
																   id="txtaliasEnglish"
																   name="<c:out value="${status.expression}"/>"
																   value="<c:out value="${status.value}" escapeXml="true"/>"/> 
													</spring:bind>
		                      				</div>
		                      				<div class="errormsg" id="aliasNameEngData_error"><spring:message htmlEscape="true" code="Error.BlockAliasNameEngData"/>
											</div>
													
												<div class="errormsg"></div>
	                    				</div>
										
										
										<div class="form-group">
	                      					<label  class="col-sm-3 control-label"><spring:message code="Label.ALIASLOCAL" htmlEscape="true"></spring:message> </label>
		                      				<div class="col-sm-6">
												 <spring:bind 	path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].aliasLocal">
															<input 		type="text" 
																		id="txtAliaslocal" class="form-control" 
																		name="<c:out value="${status.expression}"/>"
																		value="<c:out value="${status.value}" escapeXml="true"/>" /> 
												</spring:bind>
		                      				</div>
		                      				<div class="errormsg" 
														 id="aliasNameEngData_error" style="display:none">
														<spring:message htmlEscape="true" 
																		code="Error.BlockAliasNameEngData"/>
											</div>
											<div class="errormsg"></div>
	                    				</div>
	                    				
	                    				
	                    				<div class="form-group">
	                      					<label  class="col-sm-3 control-label"><spring:message code="Label.DISTRICTHEADQUARTERENGLISH" htmlEscape="true"></spring:message> </label>
		                      				<div class="col-sm-6">
												 <spring:bind 	path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].headquarterName">
															<input 		type="text" 
																		id="hquarterseng" 
																		class="form-control" 
																		name="<c:out value="${status.expression}"/>"
																		value="<c:out value="${status.value}" escapeXml="true"/>" />
														</spring:bind>
		                      				</div>
		                      				<div class="errormsg" id="headquterNameEngData_error">
																	<spring:message htmlEscape="true" code="Error.HeadquterNameEngData"/>
										  				</div>
														<form:errors path="headquarterName" cssClass="errormsg" htmlEscape="true"/>
	                    				</div>
	                    				
	                    				
	                    				<div class="form-group">
	                      					<label  class="col-sm-3 control-label"><spring:message code="Label.SUBDISTRICTHEADQUARTERLOCAL" htmlEscape="true"></spring:message> </label>
		                      				<div class="col-sm-6">
												 <spring:bind 	path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].headquarterNameLocal">
															<input 		type="text" 
																		id="hquarterslocal" 
																		class="form-control" 
																		name="<c:out value="${status.expression}"/>"
																		value="<c:out value="${status.value}" escapeXml="true"/>" />
														</spring:bind>
		                      				</div>
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
	                    				</div>
										
										
										<div class="form-group">
	                      					<label  class="col-sm-3 control-label"><spring:message code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message> </label>
		                      				<div class="col-sm-6">
												 <spring:bind 		path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].ssCode">
															<input 			type="text" 
																			id="txtSscode" 
																			class="form-control" 
																			name="<c:out value="${status.expression}"/>"
																			value="<c:out value="${status.value}" escapeXml="true"/>" />
														</spring:bind> 
		                      				</div>
		                      				<div class="errormsg" 
														 id="sscode_error">
															<spring:message htmlEscape="true" code="Error.SSCode"/>
										    		</div>
													<form:errors path="ssCode" cssClass="errormsg" htmlEscape="true"/>
													<div class="errormsg"></div>
													
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
													
													
	                    				</div>
	                    				
	                    				
	                    				<div class="box-header subheading">
			                              <h4><spring:message htmlEscape="true" code="Label.GOVTORDERDETAILS"></spring:message></h4>
			                    		</div>
										<c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
						                    <div class="form-group">
			                   						<label for="txtorderno" class="col-sm-3 control-label"><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message> </label>
				                      				<div class="col-sm-6">
				                        				<spring:bind path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].orderNocr">
															  <input type="text" 
															      	 readonly="readonly"
															         class="form-control"
																	name="<c:out value="${status.expression}"/>"
																	 value="<c:out value="${status.value}" escapeXml="true"/>" />
														</spring:bind>
				                      				</div>
				                    		 </div>
	                    		 
				                    		 <div class="form-group">
			                   						<label for="txtorderdate" class="col-sm-3 control-label"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message> </label>
				                      				<div class="col-sm-6">
				                      				<spring:bind path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].orderDatecr">
														 
				                        			  <div class="input-group date datepicker">
				                        				
														  <input type="text" 
														         class="form-control" 
														        readonly="readonly"
																 style="width: 100px"
																 name="<c:out value="${status.expression}"/>"
																 value="<c:out value="${status.value}" escapeXml="true"></c:out>"
																 htmlEscape="true" />
																 
													
													<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
													</div>
													</spring:bind>
				                      				</div>
				                    		 </div>
	                    		 
				                    		 <div class="form-group">
			                   						<label for="txteffectivedate" class="col-sm-3 control-label"><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> </label>
				                      				<div class="col-sm-6">
				                      					<div class="input-group date datepicker">
				                        				<spring:bind path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].ordereffectiveDatecr">
														  <input type="text" 
														        class="form-control"
														         readonly="readonly"
																 style="width: 100px"
																 name="<c:out value="${status.expression}"/>"
																value="<c:out value="${status.value}" escapeXml="true"></c:out>"
																htmlEscape="true" />
													</spring:bind>
													 <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
												</div>
				                      				</div>
				                      				<form:hidden path="govtOrderConfig"  value="${govtOrderConfig}" id="hdngovtOrderConfig" />
				                    		 </div>
			                    </c:if>
			                    
			                    
										<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
			                    
				                    <div class="form-group">
	                   						<label for="OrderNo" class="col-sm-3 control-label"><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message>  <span class="mandatory">*</span></label>
		                      				<div class="col-sm-6">
		                        				<spring:bind path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].orderNocr">
														  <input type="text" 
														         maxlength="20" 
														         class="form-control"
																 onkeypress="validateNumericAlphaKeys();" id="OrderNo"
																 onfocus="validateOnFocus('OrderNo');"
																 onblur="vlidateOnblur('OrderNo','1','15','c');"
																 name="<c:out value="${status.expression}"/>"
																 value="<c:out value="${status.value}" escapeXml="true"/>" />
													</spring:bind>
												
												<div class="errormsg" id="OrderNoBlank_error"><spring:message htmlEscape="true" code="error.required.ORDERNUM"/></div>
											    <div class="errormsg" id="OrderNoDataValid_error"><spring:message htmlEscape="true" code="error.valid.ORDERNO"/></div>
												<spring:bind path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].orderCodecr">
											    	<input type="hidden"name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="true"/>
												</spring:bind> 
												<form:errors path="orderNocr" cssClass="errormsg" htmlEscape="true"/>
		                      			</div>
		                    		 </div>
			                    
			                    <div class="form-group">
	                   						<label for="OrderDate" class="col-sm-3 control-label"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message> 
	                   						<c:if test="${mandatoryFlag==true}"> <span class="mandatory">*</span></c:if>	
	                   						</label>
		                      				<div class="col-sm-6">
			                      				<c:if test="${mandatoryFlag==true}">
			                      				<spring:bind path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].orderDatecr">
			                        				<div class="input-group date datepicker" id="bOrderDate">
			                        					
																  <input type="text" 
																         class="form-control" 
																         id="OrderDate"
																		 onblur="vlidateOnblur('OrderDate','1','15','c');"
																		 onfocus="hideAll();setOrderDateforCorrection('OrderDate','#OrderDateData_error');"
																		 onchange="vaidatetOrderDateforCorrectionEntity(this.value,'#OrderDateData_error');"
																		 onkeypress="hideAll();"
																		 name="<c:out value="${status.expression}"/>"
																		 value="<c:out value="${status.value}" />"
																		 htmlEscape="true" />
															
															 <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
														</div>
														</spring:bind>
												</c:if>
												<c:if test="${mandatoryFlag==false}">
													<div class="input-group date datepicker" id="bOrderDate">
														<spring:bind path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].orderDatecr">
															      <input type="text" 
															      		class="form-control" 
															      		id="OrderDate"
																		onblur="vlidateOnblur('OrderDate','1','15','c');"
																		onfocus="hideAll();setOrderDateforCorrection('OrderDate','#OrderDateData_error');"
																		onchange="validateDatetoFuture('OrderDate','#OrderFutureDate_error');vaidatetOrderDateforCorrectionEntity(this.value,'#OrderDateData_error');"
																		onkeypress="hideAll();"
																		name="<c:out value="${status.expression}"/>"
																		value="<c:out value="${status.value}" escapeXml="true"></c:out>"
																		htmlEscape="true" />
															</spring:bind>
															 <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
														</div>
												</c:if> <div class="errormsg" id="OrderDateBlank_error"><spring:message htmlEscape="true" code="error.required.ORDERDATE"/></div>
															<%-- <div class="errormsg" id="OrderDateBlank_error"><spring:message htmlEscape="true" code="error.required.ORDERDATE"/></div>
															<div class="errormsg" id="OrderFutureDate_error"> <spring:message htmlEscape="true"code="error.INCORECT.ORDERDATE"/></div>
												  			<div class="errormsg" id="OrderDateValid_error"><spring:message htmlEscape="true" code="error.valid.DATEFORMAT"/></div>
													 		 <div class="errormsg" id="OrderDateData_error"><spring:message htmlEscape="true" code="error.compare.ORDERDATE"/></div> --%>
		                      					</div>
		                    		 </div>
		                    		 
		                    		 
		                    		 <div class="form-group">
	                   						<label for="EffectiveDate" class="col-sm-3 control-label"><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> 
	                   						<c:if test="${mandatoryFlag==true}"> <span class="mandatory">*</span></c:if>
	                   						</label>
		                      				<div class="col-sm-6">
		                      					<c:if test="${mandatoryFlag==true}">
			                        				<div class="input-group date datepicker">
			                        					<spring:bind path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].ordereffectiveDatecr">
																  <input type="text" 
																         id="EffectiveDate"  
																         class="form-control"
																         readonly="readonly"
																		 name="<c:out value="${status.expression}"/>"
																		value="<c:out value="${status.value}" escapeXml="true"></c:out>"
																		htmlEscape="true" />
															</spring:bind>
															 <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
													</div>
												</c:if>
												
												
												<c:if test="${mandatoryFlag==false}">
													
													<div class="input-group date datepicker">
														<spring:bind path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].ordereffectiveDatecr">
														    	  <input type="text" 
														    	  		 id="EffectiveDate" 
																 		 class="form-control"
																		 onfocus="validateOnFocus('EffectiveDate');"
														        		 onblur="vlidateOnblur('EffectiveDate','1','15','c');"
																		 onchange="validateDatetoFuture('EffectiveDate','#EffectiveFutureDate_error');validateEffectiveDatecompOrderDate('OrderDate','EffectiveDate','#EffectiveDateData_error');"
																 		 onkeypress="hideAll();"
																		 name="<c:out value="${status.expression}"/>"
																		 value="<c:out value="${status.value}" escapeXml="true" />" />
														  </spring:bind>
														  <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
													</div>
												</c:if>
												<div class="errormsg" id="EffectiveDateData_error"><spring:message htmlEscape="true" code="error.compare.EFFECTIVEDATE"></spring:message></div>
													    <div class="errormsg" id="EffectiveDateBlank_error"><spring:message htmlEscape="true" code="ordereffectiveDate.required"></spring:message></div>
														 <div class="errormsg" id="EffectiveFutureDate_error">
															<spring:message htmlEscape="true" code="error.valid.EFFECTIVEFUTUREDATE"></spring:message>
														 </div>
													    </label> 
													    <form:errors path="ordereffectiveDatecr" cssClass="errormsg" htmlEscape="true"/>
		                      			</div>
		                    		 </div>
		                    		 <c:if test="${govtOrderConfig == 'govtOrderUpload'}">
		                    		 <div class="form-group">
                   						<label for="gazPubDatecr" class="col-sm-3 control-label"><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message> </label>
	                      				<div class="col-sm-6">
	                        			  <div class="input-group date datepicker" id="bgazPubDatecr">
	                        				<spring:bind path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].gazPubDatecr">
											  <input type="text" class="form-control" id="gazPubDatecr" onchange="noOrderDataValid('gazPubDatecr','#GuzpubDateBlankOrderDate_error','OrderDate');compareGuzpubDatetoOrderDate('gazPubDatecr','#GuzpubDateCompareOrderDate_error','OrderDate')"
											   onfocus="show_msg('gazPubDatecr');" onblur="validateGazPubDate1();" onkeyup="validateNumeric();" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>"  htmlEscape="true" />
											</spring:bind>
											<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
										  </div>
	                    		 		</div>
	                    		 		
	                    		 		<div class="errormsg" id="GuzpubDateValid_error"><spring:message htmlEscape="true" code="error.valid.DATEFORMAT"></spring:message> </div>
										<div class="errormsg" id="GuzpubDateBlankOrderDate_error"><spring:message htmlEscape="true" code="error.required.ORDERDATE"></spring:message></div>
										<div class="errormsg" id="GuzpubDateCompareOrderDate_error"><spring:message htmlEscape="true" code="error.compare.GuzpubDate"></spring:message></div>
										<form:errors path="gazPubDatecr" cssClass="errormsg" htmlEscape="true"></form:errors>
										<form:hidden path="govtOrderConfig" value="${govtOrderConfig}" id="hdngovtOrderConfig" />
		                    		 </div>
		                    		 </c:if>
		                    		<div class="form-group">
                   						<form:hidden path="govtOrderConfig"
										value="${govtOrderConfig}" id="hdngovtOrderConfig" />
                   						<%@ include file="../common/updateattachcp.jspf"%> 
                   						
		                    		</div>
		                    		 
			                    </c:if>
										
									<%-- <c:if test="${count!=1}">
							
									<div class="">
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
							</c:if>	 --%>
						</c:forEach>
						
						<div class="box-footer">
		                  <div class="col-sm-offset-2 col-sm-10">
		                     <div class="pull-right">
		                        <button type="button" name="Submit" class="btn btn-success" onclick="return validateBlock();"><i class="fa fa-floppy-o"></i> <spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button>
		                        <c:if test="${reqWarningFlag!=null}">
		                        	<button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='viewResolveEntitiesinDisturbedStateLR.htm?<csrf:token uri='vviewResolveEntitiesinDisturbedStateLR.htm'/>&warningEntiesFlag=${reqWarningFlag}';"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button>
		                        </c:if>
		                        <c:if test="${reqWarningFlag==null}">
		                        	<button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button>
		                     	</c:if>
		                     </div>
		                  </div>   
               			</div>			
										
										
									</form:form>
				                    
				                    
				                    
				                </div>
				             </div>
				        </section>
			</div>
		</section>			        

	</body>
</html>