<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="ManageLBJavascript.jsp"%>
<script src="resource-tree/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource-tree/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource-tree/jquerysctipttop.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lbcustomBootstrap.css">
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
<link href="<%=contextpthval%>/resources-localbody/css/jquery.dataTables.css" rel="stylesheet" type="text/css">	
<script src="<%=contextpthval%>/resources-localbody/scripts/jquery.dataTables.js" type="text/javascript" language="javascript" ></script>
<c:set var="URBAN_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.LB_URBAN.toString()%>"></c:set>	
<script type="text/javascript">


validateLBSelectedList=function(){
	var selParentLB=$("#selParentLB").val();
	if($_checkEmptyObject(selParentLB)){
	$( '#errselParentLB').text("<spring:message code='Error.required.new.parent' htmlEscape='true'/>");
		return false;
	}
	return true;
};

validateGoDetails=function(){
	 var _error_flag=true;
	 var orderNo=$("#orderNo");
	if ($_checkEmptyObject($(orderNo).val())) {
		$( '#errorderNo').text("<spring:message code='ORDERNUMBER.REQUIRED' htmlEscape='true'/>"); 
		_error_flag=false;
	}else if($(orderNo).val().length>60)
	{
		$( '#errorderNo').text("<spring:message code='Error.Sizevalid' htmlEscape='true'/>"); 
		_error_flag=false;
	}
	
	 var orderDate=$("#formDateOrderDate");
	 if ($_checkEmptyObject($(orderDate).val())) {
			$( '#errformDateOrderDate').text("<spring:message code='error.required.ORDERDATE' htmlEscape='true'/>"); 
			_error_flag=false;
	 }
	
	 var effectiveDate=$("#formDateEffectiveDate");
	 if ($_checkEmptyObject($(effectiveDate).val())) {
			$( '#errformDateEffectiveDate').text("<spring:message code='error.required.EFFECTIVEDATE' htmlEscape='true'/>"); 
			_error_flag=false;
	 }
	 
	return _error_flag;
};

fileUploadValidate=function(){
	//alert("in");
	//alert(isuploadNew);
	
	//alert(editMode);
	/* var gazettePublicationUpload=$("#gazettePublicationUpload");
	
		
			if ($_checkEmptyObject($(gazettePublicationUpload).val())) {
				$( '#errgazettePublicationUpload').text("<spring:message code='GOVERMENTORDER.REQUIRED' htmlEscape='true'/>"); 
				return false;
			}else{
				var fileElement = null, mimeType = null, fileExtension = null;
				if ( $.browser.msie && $.browser.version < 10) {
					try{
						var objFSO = new ActiveXObject("Scripting.FileSystemObject"); 
						var filePath = $(element)[0].value;
						try{
						fileElement = objFSO.getFile(filePath);
						}
						catch (e) {
							return true;
						}
						if($_checkEmptyObject(fileElement)) return true;
						mimeType = objFSO.GetExtensionName(filePath);
						fileExtension = mimeType;//objFSO.GetFileName(filePath);
					} catch( e ){
						alert( 'Please make sure ActiveX is enabled in your IE browser.' );
						return false;
					}
				} else {
					
					fileElement = $(gazettePublicationUpload)[0].files[0];
					if($_checkEmptyObject(fileElement)) {
						return true;
					}
					mimeType = fileElement.type;
					fileExtension = fileElement.name.split('.').pop();
				}
				 if(! checkUploadedDocs(fileElement.size, '${attachmentMasterGO.individualFileSize}', '${attachmentMasterGO.fileType}', mimeType, fileExtension)){
					 $( '#errgazettePublicationUpload').text("<spring:message code='Error.invalidgofile' htmlEscape='true'/>"); 
					 return false;
				 }
				 else{
					 return true;
				 }
			}
		 */

	
	
	
	 
};
validateFormdeails=function(formAction){
	  $( "span[id^=err]" ).each(function(){
			$( this ).text("");
	    });
	var error=true; 
	var errors=new Array();
	
	errors[1]= validateLBSelectedList();
	errors[2]=validateGoDetails();
	//alert('${isGovernmentOrderUpload}');
	<c:if test="${isGovernmentOrderUpload}">
	errors[3]=fileUploadValidate();
	</c:if> 
	for ( var i = 0; i < errors.length; i++) {
		if (errors[i] == false) {
			error = false;
			break;
		}

	}
	
	if(error){
		//alert("save");
	 
		
		checkLocalbodyChangeParent();
	} 
	   
			
};

var validationForm = function (){
	$("#selParentLB" ).rules( "add", {
 		required : true,  
 		messages: { required: "<spring:message code='Error.required.new.parent' htmlEscape='true'/>" }
	});
 	$("#orderNo" ).rules( "add", {
 		required : true, 
 		maxlength: 60,
 		regex: "[\#\%&\~\!\@\$\^\*\_\+\`\=\{\}\\[\\]\|\\\\:\;\'\"\<\>\?\]" ,
 		messages: { 
 			required: "<spring:message code='ORDERNUMBER.REQUIRED' htmlEscape='true'/>",
 			maxlength: jQuery.format("<spring:message code='Error.Sizevalid' htmlEscape='true'/>"),
 			regex    : "<spring:message code='Error.invalidegovordrno' htmlEscape='true'/>"
 		}
	});
 	$("#formDateOrderDate" ).rules( "add", {
 		required : true,  
 		messages: { required: "<spring:message code='error.required.ORDERDATE' htmlEscape='true'/>" }
	});
 	$("#formDateEffectiveDate" ).rules( "add", {
 		required : true,  
 		messages: { required: "<spring:message code='error.required.EFFECTIVEDATE' htmlEscape='true'/>" }
	});
 	<c:if test="${isGovernmentOrderUpload}">
 		if($_checkEmptyObject($("#orderCode").val())){
		    $("#gazettePublicationUpload" ).rules( "add", {
		 		required : true, 
		 		fileUploadValidate : true,
		 		messages: { 
		 			required: "<spring:message code='GOVERMENTORDER.REQUIRED' htmlEscape='true'/>",
		 			fileUploadValidate: "<spring:message code='Error.invalidgofile' htmlEscape='true'/>"
		 		}
			});
		}
 	</c:if>
};


checkLocalbodyChangeParent=function(){
	var localBodyCode=$("#paramLBCode").val();
	var parentLblc=$("#selParentLB").val();
	dwrRestructuredLocalBodyService.checkLocalbodyChangeParentInSameDistrict(parseInt(localBodyCode),parseInt(parentLblc), {
		callback : function( result ){
			if(result==null  || (result!=null && result=="" )){
				formSubmitChangeParent();
			}else		
			if(result.indexOf("Do you want proceed")>-1){
				  var $confirm = $("#modalConfirmYesNo");
				    $confirm.modal('show');
				    $("#lblMsgConfirmYesNo").html(result);
			}else{
				alert(result);
			}
	
		},
		errorHandler : function( errorString, exception){
				alert(exception);
		},
		async : true
	});		
};



var formSubmitChangeParent=function(){
	//disableAllButtons();
	$("#processAction").val("PUBLISH");
	document.forms['localBodyForm'].action = "publishModifyParentLocalBody.htm?<csrf:token uri='"publishModifyParentLocalBody.htm'/>";
	document.forms['localBodyForm'].method = "POST";
    document.forms['localBodyForm'].submit();
	
};
    
var customSubmitValidation = function (form){
	disableAllButtons();
	form.submit();
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
				  <h3 class="box-title"><spring:message code="Label.MODIFYGOVTLOCALBODY.CP" htmlEscape="true"></spring:message></h3>
		      </div>
             
              <div class="box-body">
				<div class="box-header subheading">
                   <h4><spring:message htmlEscape="true"  code="Label.GENERALLOCALGOVTBODYDETAILS"></spring:message></h4>
               </div>
                  <form:form class="form-horizontal" action="publishModifyParentLocalBody.htm" method="post" id="localBodyForm" commandName="localBodyForm" enctype="multipart/form-data">
                  	<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="publishModifyParentLocalBody.htm"/>" />
					<form:hidden path="processAction"/>
					<form:hidden path="localBodyCreationType"/>
					<form:hidden path="localBodyCode" id="paramLBCode"/>
					
					<div id="divCenterAligned">
					   <table id="" class="table table-bordered table-hover">
							<tbody>
								<c:if test="${localBodyTableObj.isdisturbed}">
								<tr>
									<td><spring:message code="Label.DISTURBEDSTATE" htmlEscape="true" ></spring:message></td>
									<td ><img src="<%=contextpthval%>/images/red_flg.png" width="13" height="9" /></td>
								</tr></c:if>
								
								<tr>
									<td ><spring:message htmlEscape="true"  code="Label.LOCALBODYNAMEENGLISH"></spring:message></td>
									<td ><c:out value="${localBodyForm.localBodyNameEnglish}" escapeXml="true"></c:out></td>
								</tr>
								
								<tr>
									<td ><spring:message htmlEscape="true" code="Label.LOCALBODYNAMELOCAL"></spring:message></td>
									<td ><c:out value="${localBodyForm.localBodyNameLocal}" escapeXml="true"></c:out></td>
								</tr>
								
								<tr>
									<td ><spring:message htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message></td>
									<td ><c:out value="${localBodyForm.localBodyAliasEnglish}" escapeXml="true"></c:out></td>
								</tr>
								
								<tr>
									<td ><spring:message htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message></td>
									<td ><c:out value="${localBodyForm.localBodyAliasLocal}" escapeXml="true"></c:out></td>
								</tr>
								
								<tr>
									<td ><spring:message code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message></td>
									<td ><c:out value="${localBodyForm.stateSpecificCode}" escapeXml="true"></c:out></td>
								</tr>
								
							 </tbody>
						</table>
					</div>
									
					
				<div class="">
					<div class="box-header subheading">
                  		 <h4><spring:message code="label.MODIFYPARENT" htmlEscape="true"></spring:message></h4>
              	   </div>
				
					<div class="form-group">
		               <label for="txtdistrictnameinenglish" class="col-sm-3 control-label"><spring:message	code="Label.SELECTEDTYPEOFLOCALBODY" htmlEscape="true"></spring:message></label>
                  		   <div class="col-sm-6">
                    			<input type="text" class="form-control" id="localBodyTypeName" value="${modifyParentAttributes.localbodyTypeName}" disabled="disabled"/>
                  		  </div>
		           </div>
                				
                				
       				<div class="form-group">
         				<label for="txtdistrictnameinlocal" class="col-sm-3 control-label"><spring:message code="Label.PARENTOFLOCALBODY" htmlEscape="true"></spring:message></label>
          					<div class="col-sm-6">
            					<input type="text" class="form-control" id="localBodyParentName" value="${modifyParentAttributes.parentLocalBodyName}"  disabled="disabled"/>	
          				   </div>
       				</div>
                  
                  
                  	<div class="form-group">
                  		<label class="col-sm-3 control-label"><spring:message code="Label.PARENTNEWLOCALBODY" htmlEscape="true"></spring:message> <c:out value="${localBodyForm.localBodyNameEnglish}"></c:out><span class="mandatory">*</span></label>
                   			<div class="col-sm-6">
                     			<form:select path="parentLocalBodyCode" class="form-control" id="selParentLB">
									<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT" ></spring:message></form:option>
										<c:forEach var="lbParents" items="${modifyParentAttributes.listLocalBodyEntities}">
											<form:option value="${lbParents.localBodyCode}">
												<c:out value="${lbParents.localBodyNameEnglish}" escapeXml="true"></c:out>
											</form:option>
										</c:forEach>
									</form:select>
								<span class="errormessage" id="errselParentLB"></span>
				      		</div>
						</div>
                	</div>
						
						<%@include file="../ExistingGovernmentOrderCpy.jsp"%>	
				
				
				</div>  		
		                  	
  
                
       <div class="box-footer">
         <div class="col-sm-offset-2 col-sm-10">
            <div class="pull-right">
            	<div style="display: none" id="drafthide">
            	<input class="btn btn-success" id="btnFormActionSaveDraft" type="submit" value="<spring:message code='Button.DRAFT' htmlEscape='true'/>" param="DRAFT"/>
            	 </div>
				<input class="btn btn-success" id="btnNewFormActionSave" type="button" value="<spring:message htmlEscape="true" code="Button.SP"/>" param="PUBLISH"/>
				<input class="btn btn-danger" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'"/>
		
		 </div>
       </div>   
   </div>         
   </form:form>
  <div id="modalConfirmYesNo" class="modal fade">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" 
		
		                class="close" data-dismiss="modal" aria-label="Close">
		                    <span aria-hidden="true">&times;</span>
		                </button>
		                <h4 id="lblTitleConfirmYesNo" class="modal-title">Change parent of Localbody</h4>
		            </div>
		            <div class="modal-body">
		                <p id="lblMsgConfirmYesNo"></p>
		            </div>
		            <div class="modal-footer">
		                <button id="btnYesConfirmYesNo" 
		
		                type="button" class="btn btn-primary">Yes</button>
		                <button id="btnNoConfirmYesNo" 
		
		                type="button" class="btn btn-default">No</button>
		            </div>
		        </div>
		    </div>
		</div>
                  
        </div>
	   </section>
	</div>
</section>

</body>
</html>