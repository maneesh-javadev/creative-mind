<%@include file="../common/taglib_includes.jsp"%> 

<html>
<head>
<title>
	<spring:message code="Label.MODIFYSUBDISTRICT" htmlEscape="true"></spring:message>
</title>
<meta http-equiv="Content-Type" content="text/html;  charset=utf-8">
 <script src="js/govtorder.js"></script>
 <script src="js/common.js"></script>
 <script type="text/javascript" src="<%=contextpthval%>/resources-localbody/scripts/jquery.numeric.js"></script>
 <script src="js/validation.js"></script>
 <script src="js/orderValidate.js"></script>
 <script type="text/javascript" src="js/modify.js" charset="utf-8"></script>
<!--  <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<link rel="stylesheet" href="datepicker/demos.css" /> -->
 
 <script type="text/javascript">
		$(document).ready(function() {
			 var mandatoryFlag = isParseJson('${mandatoryFlag}');
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

		function validateDatetoFuture(id, diverror) {
			var entityDate = document.getElementById(id).value;
			$(diverror).hide();
			if (!compareDateforFutureDDMMYYY(entityDate)) {
				$(diverror).show();
				document.getElementById(id).value = "";

			}
		}

		function validateEffectiveDatecompOrderDate(orderdateId,
				effectivedateID, diverror) {
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

		function setEffectiveDatetoOrderDate(orderDate) {
			if (orderDate != "" && orderDate != undefined)
				document.getElementById('EffectiveDate').value = orderDate;
		}

		function validateStateCorrection() {
			if (gisNodesMismatch()) {
				if (checkLatitudeLongitudeRange()) {
					hideAll();
					var mandatoryFlag = isParseJson('${mandatoryFlag}');
					var mandatory_error = false;
					var fielattach = document.getElementById('attachFile1').value;
					var filecount = document.getElementById('govtfilecount').value;
					var error = false;
					/* var aliasNameInEn=document.getElementById('txtAliasEnglish').value; 
					var aliasNameInLc=document.getElementById('txtAliaslocal').value;  */
					var sortNameEng = document.getElementById('sortName').value;
					//	var cecsus2011=document.getElementById('txtCensus').value; 
					var orderDate = document.getElementById('OrderDate').value;
					if (mandatoryFlag == 'false') {
						var effectiveDate = document
								.getElementById('EffectiveDate').value;
					}
					var GuzpubDate = document.getElementById('gazPubDatecr').value;
					var OrderNo = document.getElementById('OrderNo').value;
					/* var sscode=document.getElementById('txtSscode').value;  */
					var HeadquterNameEng = document
							.getElementById('hquarterseng').value;
					var HeadquterNameLoc = document
							.getElementById('hquarterslocal').value;
					if (OrderNo != "" || orderDate != "" || effectiveDate != ""
							|| GuzpubDate != "" || filecount > 0
							|| fielattach != 0) {
						if (OrderNo == "") {
							$("#OrderNo_error").show();
							error = true;
						}
						if (orderDate == "") {
							$("#OrderDateBlank_error").show();
							error = true;
						}
						if (effectiveDate == "") {
							$("#EffectiveDateBlank_error").show();
							error = true;
						}
						if (filecount == 0 && fielattach == 0) {
							$("#error_govorder").show();
							error = true;
						}
					}
					if (mandatoryFlag == true) {
						if (OrderNo == "") {
							$("#OrderNo_error").show();
							mandatory_error = true;
							error = true;
						}
					}
					if (OrderNo != "") {
						if (!validateOrderNoGeneral(OrderNo)) {
							error = true;
						}
					}
					if (mandatoryFlag == true) {
						if (orderDate == "") {
							$("#OrderDateBlank_error").show();
							mandatory_error = true;
							error = true;
						}
					}
					if (orderDate != "") {
						if (!validateDateDDMMYYY(orderDate)) {
							$("#OrderDateValid_error").show();
							error = true;
						}
					}

					if (GuzpubDate != "") {
						if (!validateDateDDMMYYY(GuzpubDate)) {
							$("#GuzpubDateValid_error").show();
							error = true;
						}
					}
					/* if (!validateEntityEnglishNameData(aliasNameInEn, '#aliasNameEngData_error'))
						error = true;
					if (!validateEntityNameLocalData(aliasNameInLc, '#aliasNameLocData_error'))
						error = true; */
					if (!validateEntityEnglishNameData(sortNameEng,
							'#sortNameEngData_error'))
						error = true;
					if (!validateEntityEnglishNameData(HeadquterNameEng,
							'#headquterNameEngData_error'))
						error = true;
					if (!validateEntityNameLocalData(HeadquterNameLoc,
							'#headquterNameLocData_error'))
						error = true;
					/* if(!validateEntityNumbricData(cecsus2011,"#txtCensus_error"))
						error=true; */
					/*  if(!validateEntityAlphaNumbericData(sscode,"#sscode_error"))
						error=true;  */
					if (mandatoryFlag == true) {
						if (!fielattach.length && filecount == 0) {
							$("#error_govorder").show();
							mandatory_error = true;
							error = true;
						}
					}

					if (mandatory_error == true)
						showClientSideError();

					if (error == true) {
						return false;
					} else {
						$('input[name=Submit]').prop('disabled', true);
						var mandatoryFlag = isParseJson('${mandatoryFlag}');
						//alert(mandatoryFlag);
						if (mandatoryFlag) {
							$('#EffectiveDate').prop('disabled', false);
						}
						$('#frmModifyState').attr('action',	"modifyStateCrAction.htm?<csrf:token uri='modifyStateCrAction.htm'/>").submit();
						return true;
					}
				}
			} else {
				$("#cAlert")
						.html(
								"<spring:message code='Error.lengthmismatch' htmlEscape='true'/>");
				$("#cAlert")
						.dialog(
								{
									title : '<spring:message htmlEscape="true"  code="Label.MODIFYSTATE"></spring:message>',
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

		var mandatoryFlag;
		function hideAll() {

			mandatoryFlag = isParseJson('${mandatoryFlag}');
			$("#EffectiveFutureDate_error").hide();
			$("#OrderFutureDate_error").hide();
			$("#EffectiveDateBlank_error").hide();
			//$("#txtCensus_error").hide();
			$("#OrderNoBlank_error").hide();
			$("#OrderNoDataValid_error").hide();
			$("#OrderDateBlank_error").hide();
			$("#OrderDateData_error").hide();
			$("#OrderDateValid_error").hide();
			$("#GuzpubDateValid_error").hide();
			$("#error_govorder").hide();
			$("#EffectiveDateData_error").hide();
			$("#headquterNameLocData_error").hide();
			$("#headquterNameEngData_error").hide();
			$("#sortNameEngData_error").hide();
			$("#GuzpubDateBlankOrderDate_error").hide();
			$("#GuzpubDateCompareOrderDate_error").hide();

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
<body  onload='clearOrdernoErrors();onLoadSelectDisturbed();chkCorrectionOnLoad();'  onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event); ">
 <section class="content">

                <!-- Main row -->
                <div class="row">
                    <!-- main col -->
                    <section class="col-lg-12 ">
                            <div class="box ">
                                <div class="box-header with-border">

                                    <h3 class="box-title">STATE </h3>
                                </div>
                                 <div class="box-header subheading">
                                    <h5 class="box-title"><spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message></h5>
                               </div> 
                                <!-- /.box-header -->
                                 <form:form action="modifyStateCrAction.htm" class="form-horizontal" method="POST" commandName="modifyStateCmd" id="frmModifyState" enctype="multipart/form-data" >
                                <div class="box-body ">
                                   <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='modifySubDistrictCrAction.htm'/>" />
					                <input type="hidden" name="warningflag" value="${pageWarningEntiesFlag}"/>   
					                <input type="hidden" id="govtfilecount" name="govtfilecount" value="${govtfilecount}"/>
					                <input type="hidden" id="mapfilecount" name="mapfilecount" value="${mapfilecount}"/>
					                <c:forEach var="listStateDetails" varStatus="listStateDetailsRow" items="${modifyStateCmd.listStateDetails}">
					                 <div class="form-group">
                                        <label class="col-sm-3 control-label" ><spring:message    code="Label.STATENAMEINENGLISH" htmlEscape="true"></spring:message></label>
                                        <div class="col-sm-6" >
                                        <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateNameEnglish">
												<input type="text" class="form-control "  readonly="readonly" id="statenameenglishfor" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" escapeXml="false" /> 
										  </spring:bind>
										 <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateCode">
											  <input type="hidden" class="form-control" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="false"/>
										</spring:bind>
										 <c:if test="${pageWarningEntiesFlag==true}"> 
										 <label><spring:message code="Label.WARNINGFLAGSTATUS" htmlEscape="true"></spring:message> </label>
												<img src="images/ylw_flg.png" width="13" height="9" />
										</c:if>
										</div>
                                      </div>
					                
					                
					                <div class="form-group">
                                        <label for="statenameLocalfor" class="col-sm-3 control-label"><spring:message  code="Label.STATENAMEINLOCAL" htmlEscape="true"></spring:message> </label>
                                           <div class="col-sm-6">
                                           <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateNameLocal">
												<input type="text" class="form-control" readonly="readonly" id="statenameLocalfor" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value} "/>" escapeXml="false"/>
										    </spring:bind><div class="mandatory"></div>
										    </div>
                                      </div>
					                
					                 <div class="form-group">
                                       <label for="sortName" class="col-sm-3 control-label"><spring:message text="Label.SHORTNAMEENGLISH" code="Label.SHORTNAMEENGLISH" htmlEscape="true"></spring:message> </label>
                                           <div class="col-sm-6">
                                           <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].shortName" >
									          <input type="text" id="sortName" onkeypress="hideAll();" maxlength="2" class="form-control" name="<c:out value="${status.expression}"/>"	value="<c:out value="${status.value}"/>" escapeXml="false"/>
											</spring:bind>
											<%-- <div class="mandatory" id="sortNameEngData_error">
												<spring:message htmlEscape="true" code="Error.StateSortNameEngData"></spring:message>
									       </div> --%>
											<form:errors path="shortName" class="mandatory" htmlEscape="true"></form:errors> 
											</div>
                                      </div>
					               <div class="form-group">	
								   <label for="hquarterseng" class="col-sm-3 control-label"><spring:message text="Label.HEADQUARTERSTATEEN" code="Label.HEADQUARTERSTATEEN" htmlEscape="true"></spring:message> </label>
									<div class="col-sm-6">
										<spring:bind  path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].headquarterName">
											<input type="text" id="hquarterseng" class="form-control" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="false"/>
										</spring:bind>
									       <%-- <div class="mandatory" id="headquterNameEngData_error">
												<spring:message htmlEscape="true" code="Error.StateHeadquarterNameEngData"></spring:message>
									         </div> --%><label><form:errors path="headquarterName" cssClass="mandatory" htmlEscape="true"/></label>
								     </div>
								   </div>
								   
								   
					                <div class="form-group">
									<label for="hquarterslocal" class="col-sm-3 control-label"><spring:message code="Label.HEADQUARTERSTATELOCL" htmlEscape="true"></spring:message> </label>
										<div class="col-sm-6" >
										<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].headquarterNameLocal">
											<input type="text" id="hquarterslocal" class="form-control"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />"  onkeypress="hideAll();"/>
													</spring:bind>
										<%-- <div class="mandatory" id="headquterNameLocData_error"> 
												<spring:message htmlEscape="true" code="Error.StateHeadquarterNameLocalData"></spring:message>
									       </div> --%>
													<form:errors path="headquarterNameLocal" cssClass="mandatory" htmlEscape="true"/> 
													
												<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].headquarterCode">
														<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="false" />
													</spring:bind>  
													<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateCode">
														<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="false" />
													</spring:bind> <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateVersion">
														<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="false" />
													</spring:bind> 
													
													<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].slc">
														<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="false" />
													</spring:bind> 
													</label>
												<!-- <div class="mandatory"></div> -->
											
											</div>
					                </div>
					                
					                <div class="form-group">
					                <table width="100%" class="tbl_no_brdr" align="center">
										<tr>
										  <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].mapLandregionCode">
												<input type="hidden" name="<c:out value="${status.expression}"/>"	value="<c:out value="${status.value}"/>" escapeXml="false" />
										</spring:bind>
										<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateCode">
										      <input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"  escapeXml="false"/>
										</spring:bind>
										<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateVersion">
										   <input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="false" />
										</spring:bind>
										<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].minorVersion">
										   <input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="false" />
										</spring:bind>
										 <spring:bind	path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].cordinate">
												<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="false" />
										</spring:bind> 
										 <spring:bind	path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].warningflag">
												<input type="hidden"	name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" escapeXml="false"/>
										</spring:bind>
										</tr>
										</table>
									</div>
					                
					         <div class="box-header subheading">
                                    <h4><spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message></h4>
                             </div>  
                         
                         <c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
                         
                         <div class="form-group">
                         <label for="ordernofor" class="col-sm-3 control-label"><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message><c:if test="${mandatoryFlag==true}"> <span class="mandatory">*</span></c:if> </label>
						  <div class="col-sm-6">	
							<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].orderNocr">
								<input 	type="text" class="form-control"	readonly="readonly" 	id="ordernofor" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="false" />" />
								</spring:bind>
							<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].orderCodecr">
								<input type="hidden"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="false" />" />
										</spring:bind>  
										<form:errors path="orderNocr" cssClass="mandatory" htmlEscape="true"/>
									<!-- <div class="mandatory"></div> -->
								</div>
					    </div>
                         
                         
                       <div class="form-group" >
							<label for="orderDateFor" class="col-sm-3 control-label"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message>
							<c:if test="${mandatoryFlag==true}"> <span class="mandatory">*</span></c:if>	</label>
								<div class="col-sm-6">
									<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].orderDatecr">
											<input type="text" readonly="readonly"    class="form-control"  id="orderDateFor" name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />"  />
										</spring:bind> 
								</div>
							</div>
							
							
						  <div class="form-group">
									<label for="efectiveDateFor" class="col-sm-3 control-label"><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> 
								    <c:if test="${mandatoryFlag==true}"> <span class="mandatory">*</span></c:if>	</label>
								<div class="col-sm-6">	
									<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].ordereffectiveDatecr">
									  <input type="text" class="form-control" readonly="readonly"	 id="efectiveDateFor" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="false" />" />
								    		</spring:bind>
										<form:hidden path="govtOrderConfig" value="${govtOrderConfig}" id="hdngovtOrderConfig" />
								</div>
								</div>
                              </c:if>   
                              
                              
                         <c:if test="${govtOrderConfig == 'govtOrderUpload'}">
						  <div class="form-group">
								<label class="col-sm-3 control-label"><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message> 
									<c:if test="${mandatoryFlag==true}"> <span class="mandatory">*</span></c:if></label>
								<div class="col-sm-6">	
									<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].orderNocr">
										<input type="text" maxlength="60" class="form-control" onkeypress="return validateaGovtOrderNOforModify(event);"  id="OrderNo"	onfocus="validateOnFocus('OrderNo');"
											    onblur="vlidateOrderNo('OrderNo','1','60');" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="false" />" />
										</spring:bind>
								<form:errors path="orderNocr" cssClass="mandatory" htmlEscape="true"/>
								 	<div id="OrderNo_error" class="mandatory"><spring:message code="error.required.ORDERNUM" htmlEscape="true"></spring:message></div>
							    	<div id="OrderNo_msg" class="mandatory"><spring:message code="error.required.ORDERINPUTTYPE" text="Please Enter AlphaNumerics Space . / - ( ) Only" htmlEscape="true"/></div>
									<span class="mandatory" id="OrderNo_error"></span>
							  <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].orderCodecr">
								 <input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="false" />" />
										</spring:bind>  
								 <form:errors path="orderNocr" cssClass="mandatory" htmlEscape="true"/>
							  </div>
							</div>
							
							
						<div class="form-group">
							<label class="col-sm-3 control-label"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message>
									<c:if test="${mandatoryFlag==true}"> <span class="mandatory">*</span></c:if>	</label>					
						  <div class="col-sm-6" >	
									<c:if test="${mandatoryFlag==true}">								
										<div class="input-group date datepicker" id="bOrderDate">
									<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].orderDatecr">
											
											
											
											<input type="text" id="OrderDate" 
												class="form-control"  readonly="readonly" onfocus="hideAll();setOrderDateforCorrection('OrderDate','#OrderDateData_error');"
												onblur="vlidateOnblur('OrderDate','1','15','c');" onchange="validateDatetoFuture('OrderDate','#OrderFutureDate_error');vaidatetOrderDateforCorrectionEntity(this.value,'#OrderDateData_error');"
												onkeypress="hideAll();" name="<c:out value="${status.expression}"/>" 	value="<c:out value="${status.value}" escapeXml="false" />"  />
										</spring:bind> 
										<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
								</div>
									</c:if>
								<c:if test="${mandatoryFlag==false}">								
									
									<div class="input-group date datepicker" id="bOrderDate">
									<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].orderDatecr">
											
											
											
											<input type="text" id="OrderDate" 
												class="form-control"  readonly="readonly" onfocus="hideAll();setOrderDateforCorrection('OrderDate','#OrderDateData_error');"
												onblur="vlidateOnblur('OrderDate','1','15','c');" onchange="validateDatetoFuture('OrderDate','#OrderFutureDate_error');vaidatetOrderDateforCorrectionEntity(this.value,'#OrderDateData_error');"
												onkeypress="hideAll();" name="<c:out value="${status.expression}"/>" 	value="<c:out value="${status.value}" escapeXml="false" />"  />
										</spring:bind> 
										<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
								</div>
									
									
										</c:if>
										 <span class="mandatory" id="OrderDate_error"></span><form:errors path="orderDatecr" cssClass="mandatory" htmlEscape="true" />
										 <div class="mandatory" id="OrderDateBlank_error"> <spring:message htmlEscape="true" code="error.required.ORDERDATE"></spring:message> </div>
									     <div class="mandatory" id="OrderFutureDate_error"> 	<spring:message htmlEscape="true" code="error.INCORECT.ORDERDATE"></spring:message></div>
									     <div class="mandatory" id="OrderDateValid_error"> 	<spring:message htmlEscape="true" code="error.valid.DATEFORMAT"></spring:message></div>
									    <div class="mandatory" id="OrderDateData_error"> <spring:message htmlEscape="true" code="error.compare.ORDERDATE"></spring:message>  </div>
								  </div>
								  
								</div>
										
								<div class="form-group"> 
								<label class="col-sm-3 control-label"><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message>
								   <c:if test="${mandatoryFlag==true}"> <span class="mandatory">*</span></c:if>	 </label>
								<div class="col-sm-6">	
									<c:if test="${mandatoryFlag==true}">
									<div class="input-group date datepicker" id="bOrderDate">
									<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].ordereffectiveDatecr">
											<input type="text" id="EffectiveDate"  class="form-control" readonly="readonly"	 onfocus="validateOnFocus('EffectiveDate');"  onblur="vlidateOnblur('EffectiveDate','1','15','c');"
												onkeypress="validateNumeric();" name="<c:out value="${status.expression}"/>" 	value="<c:out value="${status.value}" escapeXml="false" />" />
										</spring:bind>
										<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
										</div>
										</c:if>
										
									    <c:if test="${mandatoryFlag==false}">
										<div class="input-group date datepicker" id="bEffectiveDate">
										 <spring:bind  path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].ordereffectiveDatecr">
											<input type="text" id="EffectiveDate"  	class="form-control" readonly="readonly" 	onfocus="validateOnFocus('EffectiveDate');"
										        onblur="vlidateOnblur('EffectiveDate','1','15','c');"
												onchange="validateDatetoFuture('EffectiveDate','#EffectiveFutureDate_error');validateEffectiveDatecompOrderDate('OrderDate','EffectiveDate','#EffectiveDateData_error');"
												onkeypress="hideAll();"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="false" />" />

										</spring:bind>
										 <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
										</div>
											
											</c:if>
											<div class="mandatory" id="EffectiveDateData_error"> <spring:message htmlEscape="true" code="error.compare.EFFECTIVEDATE"></spring:message>  </div>
											<div class="mandatory" id="EffectiveDateBlank_error"> <spring:message htmlEscape="true" code="ordereffectiveDate.required"></spring:message>  </div>
											<div class="mandatory" id="EffectiveFutureDate_error"> <spring:message htmlEscape="true" code="error.valid.EFFECTIVEFUTUREDATE"></spring:message> </div>
									         <div class="mandatory"></div>
										<span class="mandatory" id="EffectiveDate_error"></span> <form:errors path="ordereffectiveDatecr" cssClass="mandatory" />
							   </div>
							</div>
							
							
							<div class="form-group">    
							
									<label class="col-sm-3 control-label"><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message> </label>
							<div class="col-sm-6">	
							
										<div class="input-group date datepicker" id="bGazPubDate">
								 			<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].gazPubDatecr">
												<input type="text" id="gazPubDatecr"  class="form-control" 
													onchange="noOrderDataValid('gazPubDatecr','#GuzpubDateBlankOrderDate_error','OrderDate');compareGuzpubDatetoOrderDate('gazPubDatecr','#GuzpubDateCompareOrderDate_error','OrderDate')"
													readonly="readonly" onkeyup="validateNumeric();" 	onkeypress="hideAll();"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />"  />

											</spring:bind>
											<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
										</div>
										 
									 
											 <div class="mandatory" id="GuzpubDateValid_error">
												<spring:message htmlEscape="true" code="error.valid.DATEFORMAT"></spring:message>
									       </div>
									      <div class="mandatory" id="GuzpubDateBlankOrderDate_error">
												<spring:message htmlEscape="true" code="error.required.ORDERDATE"></spring:message>
									       </div>
									       
									        <div class="mandatory" id="GuzpubDateCompareOrderDate_error">
												<spring:message htmlEscape="true" code="error.compare.GuzpubDate"></spring:message>
									       </div>
											<form:errors path="gazPubDatecr" cssClass="mandatory" />
											<form:hidden path="govtOrderConfig" value="${govtOrderConfig}" id="hdngovtOrderConfig" />
									</div>
							   </div>
								
										<%@ include	file="../common/updateattachcp.jspf"%>
										
 								</c:if>
 								
 								
 								
                           <%--  <div class="box-header subheading">
                                    <h4><spring:message code="Label.GISNODES" htmlEscape="true"></spring:message></h4>
                                 </div>
                           <div class="form-group">
                                 
									<%@ include file="../common/modifyGis_nodescp.jspf"%>
						  
								 <c:if test="${mapConfig eq 'false' and modifyStateCmd.listStateDetails[listStateDetailsRow.index].cordinate!=null}">
										<script>
											var cordinates='${modifyStateCmd.listStateDetails[listStateDetailsRow.index].cordinate}';
											buildLatitudeLongitude(cordinates);
										</script>
									</c:if> 
							</div> --%>	 
					         </c:forEach>
					  
					              
			 <div class="box-footer">
                   <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
                            <button type="button" name="Submit" id="Submit" class="btn btn-success "  value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" onclick="return validateStateCorrection();"><i class="fa fa-floppy-o"></i> Submit</button>
                          
                           
                             <button type="button" name="Submit3" class="btn btn-danger " value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                        
                        </div>
                     </div>   
                  </div>
             </div>
                  
                  
         </form:form>
       </div>
       </section>
       </div>
       </section>
       
<script src="/LGD/JavaScriptServlet"></script>


 </body>
 </html>