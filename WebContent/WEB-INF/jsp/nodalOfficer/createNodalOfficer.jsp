<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<%-- <%@include file="../common/taglib_includes.jsp"%>   --%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
jQuery.validator.setDefaults({
    debug: true,
    success: "valid"
  });


$( document ).ready(function() {
	$('#createNodalOfficer input[type=text]').attr("autocomplete",'off');
});

function validateEmail() {
	if($("#emailId").val() != ""){
	var str =$("#emailId").val();
	var filter = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i
	if (filter.test(str))
		testresults = true
	else {
		$("#erremailId").html(
				"Please input a valid email address!");
		testresults = false
	}
	return (testresults)
	}
	return true;
}

function resetEmail() {
	$("#erremailId").html("");
}


function validateMobile() {
	if ($('#mobileNo').val() != "" && !$('#mobileNo').val().match('[0-9]{10}')) {
		$("#errmobileNo").html(
				"Please enter 10 digit mobile number.");
		return false;
	} else {
		return true;
	}
}

function validateNumber(e, id) {
	$("#err"+id).html("");
	var n = e.charCode;
	if ((n >= 48 && n <= 57) || (n == 0)) {

	} else {
		e.preventDefault();
		$("#" + id + "_type_error").fadeIn(1000, function() {
			$("#" + id + "_type_error").fadeOut(1000);
		});
	}
}

function validate1() {
	
		var file = $('#uploadFile').prop('files');
    	  /* if(file.length==0){
    		  $("#fileUploadErr").html("Kindly Attach Attachment Document");
    		   return false;
    	  } */
    	 if(file.length>0){
    		 var ext = $('#uploadFile').val().split('.').pop().toLowerCase();
       	  if($.inArray(ext, ['gif','png','jpg','jpeg','pdf','pjpeg']) == -1) {
       		  $("#fileUploadErr").html("Kindly Attach Approved Doc of gif,jpg,pdf,png,jpeg,pjpeg type ");
       		  return false;
       	  }
    	 }
    	 
    	  
    	
    	  
    	  
    	if ( validateMobile() && validateEmail() ){
    		
    		document.getElementById('createNodalOfficer').method = "post";
    		document.getElementById('createNodalOfficer').action="saveAssignNodelOfficer.htm?<csrf:token 
    				uri='saveAssignNodelOfficer.htm'/>";
    			document.getElementById('createNodalOfficer').submit();
    	}
		else
			return false;
    	
    	 	
    	
    }
	


</script>
</head>
<body>
<body>
	<section class="content">
    	<div class="row">
        	<section class="col-lg-12 ">
            	<div class="box ">
               		<div class="box-header with-border">
                    	<h3 class="box-title">Nodal Officer Details</h3></div>
                   
                         <div class="box-body">
                         
                         <form:form id = "createNodalOfficer" action="saveAssignNodelOfficer.htm" method="POST" commandName="nodalOfficerState" enctype="multipart/form-data" class="form-horizontal">
                         <input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="saveAssignNodelOfficer.htm"/>" />
                         <div class="form-group">
                                <label class="col-sm-3 control-label">Name Of Nodal Officer <span class="mandatory">*</span></label>
	                               <div class="col-sm-6">
	                               <form:input path= "nodalOfficerName" pattern = "^[a-zA-Z\\s]+$" class="form-control" id="nodalOfficerName" maxlength="70" required = "true" placeholder="Enter Nodal Officer Name"/>
	                               <form:errors htmlEscape="true" path="nodalOfficerName" cssClass="errormsg"/>
	                               </div> 
                             </div>
                             
                             <div class="form-group">
                                <label class="col-sm-3 control-label"> <spring:message text="Designation"></spring:message> <span class="mandatory">*</span></label>
	                               <div class="col-sm-6">
	                               <form:input class="form-control" id="designation" pattern = "^[a-zA-Z\\s]+$" path="designation" maxlength="70" required = "true" placeholder="Enter Department Designation"/>
	                               <form:errors htmlEscape="true" path="designation" cssClass="errormsg"/>
	                               </div> 
                             </div>
                             
                              <div class="form-group">
                                <label class="col-sm-3 control-label"> Email Id <span class="mandatory">*</span></label>
	                               <div class="col-sm-6">
	                                 <form:input class="form-control" id="emailId"  path="emailId" required = "true" placeholder="Enter Email Id" maxlength="100" onblur="validateEmail()" onchange="resetEmail()" />
	                                 <form:errors htmlEscape="true" path="emailId" cssClass="errormsg"/>
	                               <div id="erremailId" style="color: red;font-size: x-small;"></div>
	                               </div> 
                               </div>
                               
                                <div class="form-group">
                                <label class="col-sm-3 control-label"> Mobile No.<span class="mandatory">*</span></label>
	                               <div class="col-sm-6">
	                                 <form:input class="form-control" id="mobileNo"  path="mobileNo" required = "true" placeholder="Enter Mobile No." maxlength="10" onblur="validateMobile()" onkeypress="validateNumber(event,'mobileNo')"/>
	                                  <form:errors htmlEscape="true" path="mobileNo" cssClass="errormsg"/>
	                               <div id="mobile_type_error" style="color: red;font-size: x-small;display:none; ">Numbers only.</div>
	                               <div id="mobileErr" style="color: red;font-size: x-small;"></div>
	                               </div> 
                               </div>
                               
                               <div class="form-group">
		                      <label class="col-sm-3 control-label"><c:out value="Upload a file mentioning as your Nodal Officer"></c:out> </label>
		                      <div class="col-sm-6">
		                        <!-- <input type="file" class="form-control" id="t1" accept=".pdf" name="fileUpload[0]" placeholder="Upload file"> -->
		                        <%-- <form:errors htmlEscape="true" path="fileUpload[0]" cssClass="error" /> --%>
		                        <input type="file"   id="uploadFile"  name="fileUpload[0]"  placeholder="Upload file">
		                      </div>
		                      <div id="fileUploadErr" style="color: red;"></div>
		                      <form:errors htmlEscape="true" path="fileUpload" cssClass="errormsg"/>
		                    </div>	
                         
                         <div class="box-footer">
		                     <div class="col-sm-offset-2 col-sm-10">
		                       <div class="pull-right">
		                       <button name="saveNodelofficer" type="button" class="btn btn-success" onclick="validate1();">  <spring:message htmlEscape="true" code="Button.SAVE"/> </button>
		                           
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