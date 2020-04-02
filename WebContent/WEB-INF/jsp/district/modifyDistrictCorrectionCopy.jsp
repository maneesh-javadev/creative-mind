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
<script src="js/validation.js"></script>
<script src="js/orderValidate.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="datepicker/demos.css" />



<script>
	dwr.engine.setActiveReverseAjax(true);
</script>
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


var aliasNameInEnch;
var aliasNameInLcch;
var sscodech;
var HeadquterNameEngch;
var HeadquterNameLocch;
var orderDatech;
var effectiveDatech;
var GuzpubDatech;
var OrderNoch;
var fielattachch;


$(document).ready(function(){
	
	if(mandatoryFlag){
		       document.getElementById("EffectiveDate").disabled = true;
		
		           aliasNameInEnch=document.getElementById('aliasenglishch').value; 
		           aliasNameInLcch=document.getElementById('aliaslocalch').value; 
			
		     	  sscodech=document.getElementById('txtSscode').value; 
			      HeadquterNameEngch=document.getElementById('hquarterseng').value;
			      HeadquterNameLocch=document.getElementById('hquarterslocal').value;
			
		         orderDatech= document.getElementById('orderdatech').value; 
			     effectiveDatech=document.getElementById('ordereffectivedatech').value; 
				 GuzpubDatech=document.getElementById('gazpubdatech').value; 
			     OrderNoch=document.getElementById('ordernoch').value!=null?document.getElementById('orderno').value.trim():null;
				 fielattachch = document.getElementById('attachFile1').value;
		
		
				
		
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
				
				var orderDate= document.getElementById('OrderDate').value; 
				var effectiveDate=document.getElementById('EffectiveDate').value; 
				var GuzpubDate=document.getElementById('gazPubDatecr').value; 
				var OrderNo=document.getElementById('OrderNo').value;
				var fielattach = document.getElementById('attachFile1').value;
				 var orderDate= document.getElementById('OrderDate').value; 
					var effectiveDate=document.getElementById('EffectiveDate').value; 
					var GuzpubDate=document.getElementById('gazPubDatecr').value; 
					var OrderNo=document.getElementById('OrderNo').value;
					var fielattach = document.getElementById('attachFile1').value;
					
					
					var counter = document.getElementById('govtfilecount').value;
					if(fielattach=="" && counter==0){
						alert("please upload file")
						return false;
						
					}
					
				
					else if(aliasNameInEnch==aliasNameInEn && aliasNameInLcch==aliasNameInLc && sscodech== sscode && HeadquterNameEngch==HeadquterNameEng && 
							HeadquterNameLocch==HeadquterNameLoc && orderDatech==orderDate && effectiveDatech==effectiveDate && GuzpubDatech==GuzpubDate && OrderNoch==OrderNo)
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
					$( "#EffectiveDate" ).prop( "disabled", false );
				    $( 'form[id=frmModifyDistrict]' ).attr('action', "modifyDistrictCrAction.htm?<csrf:token uri='modifyDistrictCrAction.htm'/>");
					$( 'form[id=frmModifyDistrict]' ).attr('method','post');
					$( 'form[id=frmModifyDistrict]' ).submit();
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
	<section class="content">
		<div class="row">
	        	<!-- main col -->
	              <section class="col-lg-12 ">
		              	<div class="box ">
		           			<div class="box-header with-border">
		
		               			<h3 class="box-title"><spring:message htmlEscape="true" code="Label.MODIFYDISTRICT"></spring:message></h3>
		           			</div>
			                <div class="box-body">
								<div class="box-header subheading">
			                              <h4><spring:message htmlEscape="true" code="Label.CORRECTION"></spring:message></h4>
			                    </div>
			                    <form:form action="modifyDistrictCrAction.htm"  class="form-horizontal" onsubmit="Submit.disabled = true; return true;" method="POST" commandName="modifyDistrictCmd" id="frmModifyDistrict" enctype="multipart/form-data">
			                    	<input type="hidden" name="<csrf:token-name/>" value='<csrf:token-value uri="modifyDistrictCrAction.htm"/>' />
									<input type="hidden" name="warningflag" value="${pageWarningEntiesFlag}"/>   
					 				<input type="hidden" id="govtfilecount" name="govtfilecount" value="${govtfilecount}"/>
					 				<input type="hidden" id="mapfilecount" name="mapfilecount" value="${mapfilecount}"/>
									<input type="hidden" name="type" value="${type}"/>
									<form:input  type="hidden" path="coordinates" />
									<c:forEach var="listDistrictDetails" varStatus="listDistrictDetailsRow" items="${modifyDistrictCmd.listDistrictDetails}">
				                    	<div class="form-group">
	                      					<label for="txtdistrictnameinenglish" class="col-sm-3 control-label"><spring:message code="Label.DISTRICTNAMEINENGLISH" htmlEscape="true"></spring:message> </label>
		                      				<div class="col-sm-6">
		                        				<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].districtNameEnglish">
												<input id="txtdistrictnameinenglish" class="form-control" type="text"  readonly="readonly" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="false" />" />
												</spring:bind>
		                      				</div>
	                    				</div>
	                    				
	                    				
	                    				<div class="form-group">
	                      					<label for="txtdistrictnameinlocal" class="col-sm-3 control-label"><spring:message code="Label.DISTRICTNAMEINLOCAL" htmlEscape="true"></spring:message> </label>
		                      				<div class="col-sm-6">
		                        				<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].districtNameLocal">
													<input id="txtdistrictnameinlocal" type="text" class="form-control" readonly="readonly" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="false" />" /> 
												</spring:bind>
												<div class="errormsg"></div>
		                      				</div>
	                    				</div>
			                    
			                    
			                    		<div class="form-group">
	                      					<label for="txtdistrictnameinenglish" class="col-sm-3 control-label"><spring:message code="Label.DISTRICTALIASENGLISH" htmlEscape="true"></spring:message> </label>
		                      				<div class="col-sm-6">
		                        				<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].aliasEnglish">
		                        				<input type="text" class="form-control" id="txtAliasEnglish" maxlength="50" name="<c:out value="${status.expression}"/>" onkeypress="hideAll();" value="<c:out value="${status.value}" escapeXml="false" />" /> 
											    </spring:bind>
											    
												<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].aliasEnglishch">
												<input type="hidden" id="aliasenglishch" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" />
											  </spring:bind> 
												
												
												<div class="errormsg" id="aliasNameEngData_error">
												<spring:message htmlEscape="true" code="Error.DistrictAliasNameEngData"></spring:message>
									       </div>
										<div class="errormsg"></div>
		                      				</div>
	                    				</div>
	                    				
	                    				<div class="form-group">
	                      					<label for="txtAliaslocal" class="col-sm-3 control-label"><spring:message code="Label.DISTRICTALIASLOCAL" htmlEscape="true"></spring:message> </label>
		                      				<div class="col-sm-6">
		                        				<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].aliasLocal">
													<input type="text" class="form-control"  id="txtAliaslocal"  maxlength="50" name="<c:out value="${status.expression}"/>" onkeypress="hideAll();" value="<c:out value="${status.value}" escapeXml="false" />" /> 
											   </spring:bind> 
											   
											   <spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].aliasLocalch">
												<input type="hidden" id="aliaslocalch" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" />
											  </spring:bind> 
											   
												 <div class="errormsg" id="aliasNameLocData_error">
													<spring:message htmlEscape="true" code="Error.DistrictAliasNameLocalData"></spring:message>
										        </div>
												<div class="errormsg"></div>
		                      				</div>
	                    				</div>
	                    				
	                    				
	                    				<div class="form-group">
	                      					<label for="hquarterseng" class="col-sm-3 control-label"><spring:message code="Label.DISHEADQUARTERS" htmlEscape="true"></spring:message> </label>
		                      				<div class="col-sm-6">
		                        				<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].headquarterName">
														<input type="text" id="hquarterseng" class="form-control"  maxlength="50" onkeypress="hideAll();" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="false"/>" />
												</spring:bind>
												<div class="errormsg" id="headquterNameEngData_error">
													<spring:message htmlEscape="true" code="Error.HeadquterNameEngData"></spring:message>
								       			</div>
												<form:errors path="headquarterName" cssClass="errormsg" htmlEscape="true"/>
		                      				</div>
	                    				</div>
	                    				
	                    				<div class="form-group">
	                      					<label for="hquarterslocal" class="col-sm-3 control-label"><spring:message code="Label.DISLOCHEADQUARTERSLOCAL" htmlEscape="true"></spring:message> </label>
		                      				<div class="col-sm-6">
		                        				<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].headquarterNameLocal">
											    	<input type="text" id="hquarterslocal" class="form-control"  maxlength="50" onkeypress="hideAll();"
													name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="false"/>"/>
												</spring:bind>
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
		                      			  </div>
	                    			</div>
			                    
			                    	<div class="form-group">
                   						<label for="txtSscode" class="col-sm-3 control-label"><spring:message code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message> </label>
	                      				<div class="col-sm-6">
	                        				<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].sscode">
												<input type="text" id="txtSscode" maxlength="5" class="form-control"  name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="false" />" htmlEscape="true" />
											</spring:bind>
											
											<div class="errormsg" id="sscode_error">
												<spring:message htmlEscape="true" code="Label.statespecificcodealphanumeric"></spring:message>
									       </div> 
												<form:errors path="sscode" class="errormsg" htmlEscape="true"></form:errors>
											<div class="errormsg"></div>
	                      				</div>
	                    		 </div>
			                    
			                    <div class="box-header subheading">
			                              <h4><spring:message htmlEscape="true" code="Label.GOVTORDERDETAILS"></spring:message></h4>
			                    </div>
			                    <c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
			                    <div class="form-group">
                   						<label for="txtorderno" class="col-sm-3 control-label"><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message> </label>
	                      				<div class="col-sm-6">
	                        				<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].orderNocr">
												<input 	id="txtorderno" type="text" class="form-control"readonly="readonly"name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="false" />"/>
											</spring:bind>
											
											<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].orderNo">
												<input type="hidden" id="ordernoch" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" />
											  </spring:bind> 
											<div class="errormsg" id="sscode_error">
												<spring:message htmlEscape="true" code="Label.statespecificcodealphanumeric"></spring:message>
									       </div> 
												<form:errors path="sscode" class="errormsg" htmlEscape="true"></form:errors>
											<div class="errormsg"></div>
											
											
											<spring:bind
											path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].orderCodecr">
											<input type="hidden"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="false" />" />
										</spring:bind>  <form:errors path="orderNocr" cssClass="errormsg" htmlEscape="true"/>
									<div class="errormsg"></div>
	                      				</div>
	                    		 </div>
	                    		 
	                    		 <div class="form-group">
                   						<label for="txtorderdate" class="col-sm-3 control-label"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message> </label>
	                      				<div class="col-sm-6">
	                        				<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].orderDatecr">
												<input  id="txtorderdate" type="text" readonly="readonly" class="form-control" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="false" />"  />
											</spring:bind>
											
											<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].orderDate">
												<input type="hidden" id="orderdatech" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" />
											  </spring:bind>  
	                      				</div>
	                    		 </div>
	                    		 
	                    		 <div class="form-group">
                   						<label for="txteffectivedate" class="col-sm-3 control-label"><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> </label>
	                      				<div class="col-sm-6">
	                        				<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].ordereffectiveDatecr">
												<input 	id="txteffectivedate" type="text" class="form-control"readonly="readonly" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="false" />" />
											</spring:bind>
											
											<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].ordereffectiveDate">
												<input type="hidden"  id="ordereffectivedatech"   name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" />
											  </spring:bind>  
	                      				</div>
	                      				<form:hidden path="govtOrderConfig"
										value="${govtOrderConfig}" id="hdngovtOrderConfig" />
	                    		 </div>
	                    		 
			                    </c:if>
			                    <c:if test="${govtOrderConfig == 'govtOrderUpload'}">
			                    
				                    <div class="form-group">
	                   						<label for="OrderNo" class="col-sm-3 control-label"><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message> </label>
		                      				<div class="col-sm-6">
		                        				<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].orderNocr">
													<input	type="text" maxlength="60" class="form-control" onkeypress="return validateaGovtOrderNOforModify(event);"  id="OrderNo"
													onfocus="validateOnFocus('OrderNo');" onblur="vlidateOrderNo('OrderNo','1','60');" name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />"/>

												</spring:bind>
												<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].orderNo">
												<input type="hidden" id="ordernoch" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" />
											  </spring:bind> 
												
												<div id="OrderNo_error" class="errormsg"><spring:message code="error.required.ORDERNUM" htmlEscape="true"></spring:message></div>
												<div id="OrderNo_msg" class="errormsg"><spring:message code="error.required.ORDERINPUTTYPE" text="Please Enter AlphaNumerics Space . / - ( ) Only" htmlEscape="true"/></div>
										
												<span class="errormsg" id="OrderNo_error"></span>
																			
												<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].orderCodecr">
													<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="false" />" />
												</spring:bind>  
												<form:errors path="orderNocr" cssClass="errormsg" htmlEscape="true"/>
												<div class="errormsg"></div>
		                      			</div>
		                    		 </div>
			                    
			                    <div class="form-group">
	                   						<label for="OrderDate" class="col-sm-3 control-label"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message> 
	                   						<c:if test="${mandatoryFlag==true}"> <span class="errormsg">*</span></c:if>	
	                   						</label>
		                      				<div class="col-sm-6">
			                      				<c:if test="${mandatoryFlag==true}">
			                      				
			                      				
			                      				 <div class="input-group date datepicker" id="bOrderDate">
								                     <spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].orderDatecr">
														<input type="text" id="OrderDate" 
														readonly="readonly"
														class="form-control" 
														onblur="vlidateOnblur('OrderDate','1','15','c');"
														onfocus="hideAll();setOrderDateforCorrection('OrderDate','#OrderDateData_error');"
														onchange="vaidatetOrderDateforCorrectionEntity(this.value,'#OrderDateData_error');"
														onkeypress="hideAll();"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="false" />"  />
													</spring:bind> 
													<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
												</div>
			                        				
												</c:if>
												<c:if test="${mandatoryFlag==false}">
													 <div class="input-group date datepicker" id="bOrderDate">
								                     <spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].orderDatecr">
														<input type="text" id="OrderDate" 
														readonly="readonly"
														class="form-control" 
														onblur="vlidateOnblur('OrderDate','1','15','c');"
														onfocus="hideAll();setOrderDateforCorrection('OrderDate','#OrderDateData_error');"
														onchange="vaidatetOrderDateforCorrectionEntity(this.value,'#OrderDateData_error');"
														onkeypress="hideAll();"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="false" />"  />
													</spring:bind> 
													<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
												</div>
												</c:if>
												<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].orderDate">
												<input type="hidden" id="orderdatech" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" />
											  </spring:bind>
												<span class="errormsg" id="OrderDate_error"></span><form:errors path="orderDatecr"
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
		                      			</div>
		                    		 </div>
		                    		 
		                    		 
		                    		 <div class="form-group">
	                   						<label for="EffectiveDate" class="col-sm-3 control-label"><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> 
	                   						<c:if test="${mandatoryFlag==true}"> <span class="errormsg">*</span></c:if>
	                   						</label>
		                      				<div class="col-sm-6">
		                      					<c:if test="${mandatoryFlag==true}">
		                      					
			                      					<div class="input-group date datepicker">
											          <spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].ordereffectiveDatecr">
														<input type="text" id="EffectiveDate" 
															class="form-control"
															disabled="true"
															onfocus="validateOnFocus('EffectiveDate');"
													        onblur="vlidateOnblur('EffectiveDate','1','15','c');"
															onkeypress="validateNumeric();"
															name="<c:out value="${status.expression}"/>"
															value="<c:out value="${status.value}" escapeXml="false" />" />
													</spring:bind>
													  <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
												  </div>
			                        				
												</c:if>
												
												
												<c:if test="${mandatoryFlag==false}">
												<div class="input-group date datepicker">
													<spring:bind
														path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].ordereffectiveDatecr">
														<input type="text" id="EffectiveDate"
															class="form-control"
															readonly="readonly"
															onfocus="validateOnFocus('EffectiveDate');"
													        onblur="vlidateOnblur('EffectiveDate','1','15','c');"
															onchange="validateDatetoFuture('EffectiveDate','#EffectiveFutureDate_error');validateEffectiveDatecompOrderDate('OrderDate','EffectiveDate','#EffectiveDateData_error');"
															onkeypress="hideAll();"
															name="<c:out value="${status.expression}"/>"
															value="<c:out value="${status.value}" escapeXml="false" />" />
													</spring:bind>
													<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
												</div>
													
												</c:if>
												
												<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].ordereffectiveDate">
												<input type="hidden"  id="ordereffectivedatech"   name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" />
											  </spring:bind> 
												
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
		                      			</div>
		                    		 </div>
		                    		 
		                    		 <div class="form-group">
                   						<label for="gazPubDatecr" class="col-sm-3 control-label"><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message> </label>
	                      				<div class="col-sm-6">
	                      				
	                      				 <div class="input-group date datepicker" id="bgazPubDatecr">
						                     <spring:bind
												path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].gazPubDatecr">
												<input type="text" id="gazPubDatecr"
													readonly="readonly"
													class="form-control" 
													onkeyup="validateNumeric();"
													onchange="noOrderDataValid('gazPubDatecr','#GuzpubDateBlankOrderDate_error','OrderDate');compareGuzpubDatetoOrderDate('gazPubDatecr','#GuzpubDateCompareOrderDate_error','OrderDate')"
													onkeypress="hideAll();"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />" />
											</spring:bind>
											
											<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].gazPubDate">
												<input type="hidden"  id="gazpubdatech"     name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" />
											  </spring:bind>
											<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
										</div>
	                        				
	                    		 		</div>
	                    		 		
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
		                    		 </div>
		                    		 
		                    		<div class="form-group">
                   						<form:hidden path="govtOrderConfig"  value="${govtOrderConfig}" id="hdngovtOrderConfig" />
                   						<%@ include file="../common/updateattachcp.jspf"%>
                   						
		                    		</div>
		                    		 
			                    </c:if>
			                    <div class="box-header subheading" style="display:none;">
			                              <h4><spring:message htmlEscape="true" code="Label.GISNODES"></spring:message></h4>
			                    </div>
			                    <div class="form-group" style="display:none;">
                   						
                   						<%@ include file="../common/modifyGis_nodescp.jspf"%>
                   						
                   						<c:if test="${modifyDistrictCmd.listDistrictDetails[listDistrictDetailsRow.index].cordinate!=null}">
										<script>
											var cordinates='${modifyDistrictCmd.listDistrictDetails[listDistrictDetailsRow.index].cordinate}';
											buildLatitudeLongitude(cordinates);
										</script>
									</c:if>
	                    		</div>
			                    
			                </c:forEach>
			                
			                
			                 <div class="box-footer">
	                     <div class="col-sm-offset-2 col-sm-10">
	                       <div class="pull-right">
								<button type="button" name="Submit" class="btn btn-success" onclick="return validateDistrictCorrection();"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true"  code="Button.SAVE"></spring:message></button>	
									
								<c:if test="${reqWarningFlag!=null}">
									<button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='viewResolveEntitiesinDisturbedStateLR.htm?<csrf:token uri='vviewResolveEntitiesinDisturbedStateLR.htm'/>&warningEntiesFlag=${reqWarningFlag}';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
								</c:if>
								<c:if test="${reqWarningFlag==null}">
									<button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
								</c:if>		
	                        </div>
	                    </div>   
                  </div>     
			                    
			                    
			          </form:form>
			                  
			            
			      <script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>          
			                    
		           </div>
     </div>
	               </section>
	    </div>
	</section>
	
</body>
</html>












