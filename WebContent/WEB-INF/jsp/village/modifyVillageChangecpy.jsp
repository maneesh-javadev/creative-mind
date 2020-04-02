<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<title><spring:message code="Label.MODIFYVILLAGE"
		htmlEscape="true"></spring:message></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<!-- for Unique constrain  -->
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<!-- for Unique constrain  -->


<script type="text/javascript" src="js/common.js"></script>
<script src="js/govtorder.js"></script>

<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/shiftdistrict.js"></script>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/govtorder.js"></script>
<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>


<script type="text/javascript">
	/*  function getOperationType(val)
	 {
	 	document.getElementById('isChange').value = val;    	
	 } */


	 function valdiateChangeVillageSubmitSave(){
		 document.getElementById('buttonClicked').value='S';
		 
		 if(valdiateChangeVillageSubmit())
			 return true;
		 else
			 return false;
	 }
	 function valdiateChangeVillageSubmitPub(){
		 document.getElementById('buttonClicked').value='P';
		 if(valdiateChangeVillageSubmit())
			 return true;
		 else
			 return false;
	 }
	 function valdiateChangeVillageSubmit()
	 {
		
		hideError();
		var error=false;
	 	var mandatory_change_error=false;
	 	var mandatory_error=false;
	 	villageNameInEnch=document.getElementById('villagenameInEngch').value; 
	 	villageInEn=document.getElementById('villagenameInEng').value; 
	 	villageInLc=document.getElementById('villageNameInLocal').value; 
	 	aliasNameInEn=document.getElementById('aliasNameInEnglish').value; 
	 	aliasNameInLc=document.getElementById('aliasNameInLocal').value; 
	 	if(villageNameInEnch=="" || villageNameInEnch==null)
	 		{
	 		error = true;
	 		mandatory_error=true;
	 		$("#villageNameEngBlank_error").show(); 
	 		}
	 		
		 	else if (!validateVillageNameEnglish(villageNameInEnch, '#villageNameEngData_error'))
		 	{
		 		error = true;
		 	}
	 	
	 		
	 	
		 if (!validateEntityNameLocalData(villageInLc, '#villageNameLocData_error'))
	 		error = true;
	 	
	 	
		 if (!validateEntityEnglishNameData(aliasNameInEn, '#aliasNameEngData_error'))
	 		error = true;
		 if (!validateEntityNameLocalData(aliasNameInLc, '#aliasNameLocData_error'))
	 		error = true;
		
	 	if(mandatory_error==true)
	 		showClientSideError();
	 	else
	 		{
	 		if((villageInEn==villageNameInEnch))
	 			mandatory_change_error=true;
	 			
	 		 if(mandatory_change_error==true)
	 			{
	 			alert("you have not done any changes in mandatory fields.");
	 			error=true;
	 			showNoChaneClientSideError();
	 			}
	 		}
	 	
	 	if(error==true){
	 		return false;
	 	}
	 	else{
	 		var villageCode = $('#villageId').val();
	 		lgdDwrVillageService.getMaxPreVersionEffDateOfVillages(villageCode,{
	 			async : false,
	 			callback : function(data) {
	 				$('#preVersionEffDate').val(data);
	 			},
	 			errorHandler : function() {
	 				alert("Error");
	 			}
	 		}); 
	 		if(validateGovtOrderDetailsForVillage()){
		 			$('#OrderDate').removeAttr("disabled");
		 		    $('#EffectiveDate').removeAttr("disabled");
		 			$('#GazPubDate').removeAttr("disabled");
		 			return true;		
		 		}else{
		 			return false;
		 		}
	 	}
	 	
	 	
	 }

	
	function LoadMe()
	{
		hideError();
		previousEntityName=document.getElementById('villagenameInEng').value;
		var errorflag='<c:out value="${modifyVillageCmd.errorflag}" escapeXml="true"></c:out>';
		//alert(errorflag);
		if(errorflag=="2")
			{
			showClientSideError();
			}
		else if(errorflag=="1")
			showNoChaneClientSideError();
	}
	
	function hideError()
	{
		$("#entityNameInEnExist_error").hide();
		$("#entityNameInEnExistDraft_error").hide();
		$("#villageNameEngBlank_error").hide();
		$("#villageNameEngData_error").hide();
		$("#villageNameLocData_error").hide();
		$("#aliasNameEngData_error").hide();
		$("#aliasNameLocData_error").hide();	
	}


	if ( window.addEventListener ) { 
	    window.addEventListener( "load",LoadMe, false );
	 }
	 else 
	    if ( window.attachEvent ) { 
	       window.attachEvent( "onload", LoadMe );
	 } else 
	       if ( window.onLoad ) {
	          window.onload = LoadMe;
	 }
	
	/*Modified by Pooja on 21-07-2015 for display difft. error msg*/
	var entityFieldId;
	function modifyVillageVal(subdisid,vilname,fieldId) {
		if (previousEntityName != vilname) {
			entityFieldId = fieldId;
			lgdDwrVillageService.VilageExist(subdisid, vilname, {
				callback : handleVillageSuccess,
				errorHandler : handleVillageError
			});
		}
	}

	function handleVillageSuccess(data) {
		$("#entityNameInEnExist_error").hide();
		$("#entityNameInEnExistDraft_error").hide();
		if (data=='P') {
			$("#entityNameInEnExist_error").show();
			document.getElementById(entityFieldId).value = previousEntityName;
			document.getElementById(entityFieldId).focus();
		}
		else if(data == 'S'){
			$("#entityNameInEnExistDraft_error").show();
			document.getElementById(entityFieldId).value = previousEntityName;
			document.getElementById(entityFieldId).focus();
		}
	}

	function handleVillageError() {
		document.getElementById(entityFieldId).value = "";
		document.getElementById(entityFieldId).focus();
	}
	
	 callActionUrl=function(url){
		 	/* document.forms['sectionForm'].action = "buildSelection.htm?<csrf:token uri='"buildSelection.htm'/>";
			document.forms['sectionForm'].method = "POST";
		    document.forms['sectionForm'].submit(); */
		   
		    $( 'form[id=frmModifyVillage]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
			$( 'form[id=frmModifyVillage]' ).attr('method','post');
			$( 'form[id=frmModifyVillage]' ).submit();
	};
</script>
</head>
<body>
  <section class="content">
	 <div class="row">
        <!-- main col -->
          <section class="col-lg-12 ">
	         <div class="box ">
	            <div class="box-header with-border">
               		<h3 class="box-title"><spring:message code="Label.MANAGEVILLAGE" htmlEscape="true"></spring:message></h3>
               	</div>
		        <div class="box-body">
					<div class="box-header subheading">
			            <h4><spring:message htmlEscape="true" code="LABEL.CHANGE.NAME.OF.VILLAGE"></spring:message></h4>
			        </div>
			    
			<form:form action="draftModifyVillage.htm" class="form-horizontal" method="POST" commandName="modifyVillageCmd" id="frmModifyVillage" enctype="multipart/form-data">
			 <form:input path="villageId" type="hidden" name="villageId" id="villageId" /> 
			 <form:hidden path="govtOrderConfig" value="${govtOrderConfig}" />
			 <form:hidden path="operation" value="M" /> 
			 <form:hidden path="subdistrictCode" value="${subDistrictCode}" /> 
			 <input type="hidden" name="preVersionEffDate" id="preVersionEffDate" /> 
			 <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='draftModifyVillage.htm'/>" />
				
				<div id='changevillage'>
			       <c:forEach var="listVillageDetails" varStatus="listVillageDetailsRow" items="${modifyVillageCmd.listVillageDetails}">
			                    
			          <div class="form-group">
                      	<label class="col-sm-3 control-label"><spring:message code="Label.VILLAGENAMEINENGLISH" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
	                      <div class="col-sm-6">
		                    <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameEnglishCh">
								<input type="text" onkeypress="return validateAlphanumericUpdateKeysWard(event);hideError();" class="form-control" maxlength="50"
									name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="false" />"
									id="villagenameInEngch" onblur="modifyVillageVal(${subDistrictCode},this.value,'villagenameInEngch');" />
							</spring:bind>
								<div id="entityNameInEnExistDraft_error" style="color: red;">Same Village Name already exist in Draft mode. Plz go to View n Manage Draft entities and Publish the village or delete the Draft version.</div>
								<div id="entityNameInEnExist_error" class="errormsg">SameVillage Name already exist.</div>
								<div id="villageNameEngBlank_error" class="errormsg">
									<spring:message code="Error.blank.VillageNameInEng" htmlEscape="true"></spring:message>
								</div>
								<div class="errormsg" id="villageNameEngData_error">
									<spring:message htmlEscape="true" code="Error.data.villageNameEng"></spring:message>
								</div>
								<form:errors path="villageNameEnglishCh" class="errormsg" htmlEscape="true" />
								<div class="errormsg" id="villagename_error2" style="display: none"></div>
	                   	  </div>
                  	 </div>
                  	 
                   	<div class="form-group">
			            <label class="col-sm-3 control-label"><spring:message code="Label.VILLAGENAMEINLOCAL" htmlEscape="true"></spring:message></label>
				          <div class="col-sm-6">
					         
					         <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameLocalCh">
								<input type="text" id="villageNameInLocal" onkeypress="return validateAlphanumericUpdateKeysWard(event);"
									class="form-control" maxlength="50" name="<c:out value="${status.expression}"/>"
									value="<c:out value="${status.value}" escapeXml="true" />" />
							 </spring:bind>
							 <div class="errormsg" id="villageNameLocData_error"><spring:message htmlEscape="true" code="Error.data.VillageNameLocal"></spring:message></div>
							 <form:errors path="villageNameLocalCh" class="errormsg" htmlEscape="true"></form:errors>
						 </div>
			        </div>
			                     
			                    
			       <div class="form-group">
			       	    <label class="col-sm-3 control-label"><spring:message code="Label.ALIASENGLISH" htmlEscape="true"></spring:message></label>
				           <div class="col-sm-6">
					          <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].aliasEnglishCh">
								 <input type="text" id="aliasNameInEnglish" onkeypress="validateCharKeys(event)" class="form-control" maxlength="50" name="<c:out value="${status.expression}"/>"
									value="<c:out value="${status.value}" escapeXml="true" />" />
							  </spring:bind>
							<div class="errormsg" id="aliasNameEngData_error"><spring:message htmlEscape="true" code="Error.data.DistrictAliasNameEng"></spring:message></div>
							<form:errors path="aliasEnglishCh" class="errormsg" htmlEscape="true"></form:errors>
							<div class="errormsg"></div>
						 </div>
			      </div>
			                    
			      <div class="form-group">
			       	 <label class="col-sm-3 control-label"><spring:message code="Label.ALIASLOCAL" htmlEscape="true"></spring:message> </label>
				        <div class="col-sm-6">
					          <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].aliasLocalCh">
								<input type="text" id="aliasNameInLocal" onkeypress="validateCharKeys(event)" class="form-control" maxlength="50" name="<c:out value="${status.expression}"/>"
									value="<c:out value="${status.value}" escapeXml="true" />" />
							  </spring:bind>
							<div class="errormsg" id="aliasNameLocData_error"><spring:message htmlEscape="true" code="Error.data.villageAliasNameLocal"></spring:message></div>
							<form:errors path="aliasLocalCh" class="errormsg" htmlEscape="true"></form:errors>
							<div class="errormsg"></div> 
								<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameEnglish">
								   <input type="hidden" id="villagenameInEng" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true" />" />
								</spring:bind>
								
								 <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageCode">
									<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true" />" />
								 </spring:bind>
								 
								 <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageVersion">
									<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true" />" />
								 </spring:bind>
								 
								 <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].subdistrictCode">
									<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true" />" />
								 </spring:bind>
								 
								 <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].subdistrictVersion">
									<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true" />" />
								 </spring:bind>
							
						 </div>
			      </div> 
			    </c:forEach>
			           
			<div id="cat">        
			<div class="box-header subheading">
				<h4><spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message></h4>
			</div>
				<%@ include file="../govtbody/ExistingGovernmentOrderVillagecp.jsp"%>   
			<br/>	
		  <div class="form-group">
			  <label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message><span class="mandatory">*</span></label>
			       <div class="col-sm-6">
			           <form:input path="orderNo" htmlEscape="true" id="OrderNo" type="text" class="form-control" maxLength="60"
							onfocus="helpMessage(this,'OrderNo_error');" onblur="vlidateOrderNo('OrderNo','1','60');hideHelp();" onkeypress="return validateaGovtOrderNO(event);"/>

						<div id="OrderNo_error" class="error" style="display: none"><spring:message code="error.required.ORDERNUM" htmlEscape="true"></spring:message></div>
						<div id="OrderNo_msg" class="error" style="display: none"><spring:message code="error.required.ORDERINPUTTYPE" text="Please Enter AlphaNumerics Space . / - ( ) Only" htmlEscape="true" /></div>
						<div class="errormsg" id="OrderNo_error1"><form:errors path="orderNo" htmlEscape="true" /></div>
						<div class="errormsg" id="OrderNo_error2"style="display: none"></div>
						<div  id="errOrderNo" class="errormsg"></div>
						
			      </div>
		 </div>   
		 
		 <div class="form-group">
				<label class="col-sm-3 control-label"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
				<div class="col-sm-6">
				  <div class="input-group date datepicker" id="bOrderDate">
					<form:input path="orderDate" readonly="true" id="OrderDate" type="text" class="form-control" onchange="setEffectiveDate(this.value);"
						onkeypress="validateNumeric();" onfocus="validateOnFocus('OrderDate');helpMessage(this,'OrderDate_msg');" onblur="vlidateOnblur('OrderDate','1','15','m');hideHelp();" onkeyup="hideMessageOnKeyPress('OrderDate')" />
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				  </div>
					<div id="OrderDate_error" style="display: none" class="error"><spring:message code="error.required.ORDERDATE" htmlEscape="true"></spring:message></div>
					<div id="OrderDate_msg" class="mandatory" style="display: none"><spring:message code="error.required.ORDERDATE" htmlEscape="true" /></div>
					<div class="errormsg" id="OrderDate_error1"><form:errors path="orderDate" htmlEscape="true" /></div>
					<div class="errormsg" id="OrderDate_error2"style="display: none"></div> 	
					<div  id="errOrderDate" class="errormsg"></div>
					
				</div>
		 </div>    
	 	 
		 <div class="form-group">
			<label class="col-sm-3 control-label"><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
				<div class="col-sm-6">
					<div class="input-group date datepicker" id="bEffectiveDate">
						<form:input id="EffectiveDate" readonly="true" path="effectiveDate" type="text" class="form-control" onkeypress="validateNumeric();" onfocus="validateOnFocus('EffectiveDate');helpMessage(this,'EffectiveDate_msg');"
							onblur="vlidateOnblur('EffectiveDate','1','15','m');hideHelp();" onkeyup="hideMessageOnKeyPress('EffectiveDate')" />
					  	<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				  	</div>
					<c:if test='${preVersionEffDate != ""}'>Previous version Effective Date : [ ${preVersionEffDate} ]<br /></c:if>
					<div id="EffectiveDate_error" style="display: none" class="mandatory"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"></spring:message></div>
					<div id="EffectiveDate_msg"  class="mandatory" style="display: none"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true" /></div>
					<div class="errormsg" id="EffectiveDate_error1"><form:errors path="effectiveDate" htmlEscape="true" /></div>
					<div class="errormsg" id="EffectiveDate_error2" style="display: none"></div>
					<div class="errormsg" id="errEffectiveDate" ></div>
					
				</div>
		</div>
		
    <c:if test="${govtOrderConfig == 'govtOrderUpload'}">
		 <div class="form-group">
		  <label class="col-sm-3 control-label"><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message></label>
			<div class="col-sm-6">
			  <div class="input-group date datepicker" id="bGazPubDate">
						<form:input id="GazPubDate" path="gazPubDate" type="text" class="form-control" onkeypress="validateNumeric();"
							onfocus="validateOnFocus('GazPubDate');helpMessage(this,'GazPubDate_msg');" onblur="vlidateOnblur('GazPubDate','1','15','m');hideHelp();" onkeyup="hideMessageOnKeyPress('GazPubDate')" />
						  <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				</div>
			  <%-- <div class="input-group date datepicker" id="bGazPubDate">
				<form:input id="GazPubDate" path="gazPubDate" type="text" class="form-control" onkeypress="validateNumeric();" onfocus="validateOnFocus('GazPubDate');helpMessage(this,'GazPubDate_msg');"
					onblur="vlidateOnblur('GazPubDate','1','15','m');hideHelp();" onkeyup="hideMessageOnKeyPress('GazPubDate')" />	
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
			  </div> --%>
				<div id="GazPubDate_error" class="error" style="display: none" ><spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true"></spring:message></div>
				<div id="GazPubDate_msg" class="mandatory" style="display: none"><spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true" /></div>
				<div class="errormsg" id="GazPubDate_error1"><form:errors path="gazPubDate" htmlEscape="true" /></div>
				<div class="errormsg" id="GazPubDate_error2" style="display: none"></div>
				<div  id="errGazPubDate" class="errormsg"></div>
				
			</div>
		 </div>
	</c:if>   
	
	<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
		<div id ="divGazettePublicationUpload"><%@ include file="../common/attachmentgovtcp.jspf"%></div>
	</c:if>
	
	<c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
	  	<div class="form-group">
				<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELGOT"></spring:message><span class="mandatory">*</span></label>
				<div class="col-sm-6">
				  <div class="input-group date datepicker">
					<form:select path="templateList" id="templateList" style="width:280px" class="form-control" onblur="vlidateOnblur('templateList','1','15','m');hideHelp();"
						onfocus="validateOnFocus('templateList');helpMessage(this,'templateList_msg');"	onkeyup="hideMessageOnKeyPress('templateList')">
					  <form:option value="0"><spring:message htmlEscape="true" code="Error.templateselect"></spring:message></form:option>
						<form:options items="${templateList}" itemLabel="templateNameEnglish" itemValue="templateCode"></form:options>
					</form:select>
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				  </div>
					<div id="templateList_error" class="error"><spring:message code="error.blank.template" htmlEscape="true"></spring:message></div>
					<div id="templateList_msg" style="display: none"><spring:message code="error.blank.template" htmlEscape="true" /></div>
					<div class="errormsg" id="templateList_error1"><form:errors path="templateList" htmlEscape="true" /></div>
					<div class="errormsg" id="templateList_error2" style="display: none"></div>
				</div>
		</div>
	</c:if>
</div>
	<form:hidden htmlEscape="true" path="buttonClicked" value="" /> 
			    
	<div class="box-footer">
	   <div class="col-sm-offset-2 col-sm-10">
		  <div class="pull-right">
				<div style="display: none" id="drafthide">
				 <button style="width: 80px;" type="submit" name="Submit" class="btn btn-success" id="BtnSA" onclick="return valdiateChangeVillageSubmitSave();"><i class="fa fa-floppy-o"></i>  <spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button>
				 </div>
				<button style="width: 80px;" type="submit" name="Submit" class="btn btn-primary" id="BtnSAP" onclick="return valdiateChangeVillageSubmitPub();"><spring:message code="Label.PUBLISH" htmlEscape="true"></spring:message></button>
				<button style="width: 80px;" type="button" name="Submit3" class="btn btn-warning" id="btnClear" onclick="javascript:location.href='modifyVillagechangebyId.htm?<csrf:token uri='modifyVillagechangebyId.htm'/>&villageId=${modifyVillageCmd.villageId}';"> <spring:message code="Button.CLEAR" htmlEscape="true"></spring:message></button>
				<button style="width: 80px;" type="button" name="Submit3" class="btn btn-danger" onclick="callActionUrl('viewvillage.htm')" ><i class="fa fa-times-circle"></i>  <spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button>
		 </div>
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