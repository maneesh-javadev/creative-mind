<%-- <link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=contextpthval%>/resources-localbody/css/jquery.dataTables.css">	
<script type="text/javascript" src="<%=contextpthval%>/resources-localbody/scripts/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/resources-localbody/scripts/jquery.numeric.js"></script>
<script src="resource-tree/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource-tree/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource-tree/jquerysctipttop.css">  --%>

<script>
var _inval_list_size=parseInt("<c:out value='${invaliSubdistList.size()}' />");
var isIvalidateSubdistrict=isParseJson("${isIvalidateSubdistrict}");

$(document).ready(function() {
	
	
	$("#btnInvalidateAction").click(function() {
		invalidateSubdistrictList();
	 });
	 /* jQuery.validator.addMethod("regex", function(value, element, regexp) {
       if(!$_checkEmptyObject(value)){
       	var re = new RegExp(regexp);
           return !(this.optional(element) || re.test(value));	
       }else{
       	return true;	
       }
   });  */
	 
	 
	 $('form').each(function(){
	        if($(this).attr('id') == "draftGovermentOrderForm"){
	        	$("#draftGovermentOrderForm").validate({
	                ignoreTitle: true ,
	                submitHandler: function (form) {
	                	form.submit();
	                } ,
	        		invalidHandler: function(form, validator) {
	        			 var errors = validator.numberOfInvalids();
	        	  
             
                    if (errors) {
                      var message = errors == 1
                        ? 'Please correct the following error:\n'
                        : 'Please correct the following ' + errors + ' errors.\n';
                      var errors = "";
                      if (validator.errorList.length > 0) {
                          for (var x = 0; x < validator.errorList.length; x++) {
                              errors += "\n\u25CF " + validator.errorList[x].message;
                          }
                      }
                     // alert(message + errors);
                    }
                  } 
	            }); 
	    		validationForm(); 
	    	}
	    });
   

	
	 jQuery.validator.addMethod("fileUploadValidate", function(value, element) {
			
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
					customAlert( 'Please make sure ActiveX is enabled in your IE browser.' );
					return false;
				}
			} else {
				fileElement = $(element)[0].files[0];
				if($_checkEmptyObject(fileElement)) {
					return true;
				}
				mimeType = fileElement.type;
				fileExtension = fileElement.name.split('.').pop();
			}
			return checkUploadedDocs(fileElement.size, '${attachmentMasterGO.individualFileSize}', '${attachmentMasterGO.fileType}', mimeType, fileExtension);
	    });
	   
   
});


/**
 * The {@code checkUploadedDocs} used to check uploaded file's type   
 * size and mime type.
 */
var checkUploadedDocs = function (iSize, maxFileSize, validFileTypes, mimeType, fileExtension){
	validFileTypes = validFileTypes + ",application/x-download";  
	var sizeInMB = (iSize / (1024*1024)).toFixed(2);
	//Validating for 5 MB size.
    if( parseFloat( sizeInMB ) > parseFloat( maxFileSize ) ) {	
    	return false;
    }
    var status = false, extenstions = validFileTypes.split(",");
    $(extenstions).each(function(index, value){
    	if(mimeType.indexOf(value) > -1 && extenstions.indexOf(fileExtension) > -1){
			status = true;
			return false;
		}
	});	
    return status;
};


var invalidateSubdistrictList=function(){
	var dataElement = new Array();
	
	for(i=1;i<=_inval_list_size;i++){
		
		if($("#invalYes"+i).is(':checked')){
			dataElement.push($("#invalYes"+i).val());
		}
	}
	
	$("#invalidateSubdistrictcodes").val(dataElement.toString())
	$('#invalidateSubDistDiv').modal('hide');
	 $( "#btnFormActionSaveDraft" ).prop( "disabled", true );
	 $( "#btnFormActionPublish" ).prop( "disabled", true );
	 $( "#btnActionClose" ).prop( "disabled", true );
		callActionUrl('buildDraftSubdistrict.htm');
};


var popupInvalidateList = function() {

	
		$('#invalidateSubDistDiv').modal('show');
};


var validateGovForm=function(){
	 $( "span[id^=err]" ).each(function(){
			$( this ).text("");
	    });
	var error=false;
	var errors=new Array();
	errors[0]= validateOrderNo();
	errors[1]= validateFieldRequried("formDateOrderDate","<spring:message code='error.required.ORDERDATE' htmlEscape='true'/>");
	errors[2]= validateFieldRequried("formDateEffectiveDate","<spring:message code='error.required.EFFECTIVEDATE' htmlEscape='true'/>");
	<c:if test="${isGovernmentOrderUpload}">
		if($_checkEmptyObject($("#orderCode").val())){
			if( isParseJson("${not modifyProcess}") ){
					errors[3]= validateUploadGovOrder();
			}
		}
	</c:if> 
	for ( var i = 0; i < errors.length; i++) {
		if (errors[i] == true) {
			error = true;
			break;
		}

	}
	//alert(error);
return error;
};

validateOrderNo=function(){
	var _error_flag=false;
	var orderno= $("#orderNo" ).val();
	if($_checkEmptyObject(orderno)){
		$( '#errorderNo').text("<spring:message code='ORDERNUMBER.REQUIRED' htmlEscape='true'/>"); 
		_error_flag=true;
	}else{
		var re = new RegExp("^[a-zA-Z0-9 ]+$", "g");
		 if(!re.test(orderno)){
			 $( '#errorderNo').text("<spring:message code='Error.invalidegovordrno' htmlEscape='true'/>");	 
			 _error_flag=true;
		 } 
	}
	return _error_flag;
}

validateFieldRequried=function(id,errorMsg){
	var _error_flag=false;
	var orderno= $("#"+id ).val();
	if($_checkEmptyObject(orderno)){
		$( '#err'+id).text(errorMsg); 
		_error_flag=true;
	}
	return _error_flag;
};


validateUploadGovOrder=function(){
	var _error_flag=false;
	var gazettePublicationUpload= $("#gazettePublicationUpload" ).val();
	if($_checkEmptyObject(gazettePublicationUpload)){
		$( '#errgazettePublicationUpload').text("<spring:message code='GOVERMENTORDER.REQUIRED' htmlEscape='true'/>"); 
		_error_flag=true;
	}
	return _error_flag;
}

</script>