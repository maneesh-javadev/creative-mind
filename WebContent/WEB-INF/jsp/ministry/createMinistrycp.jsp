<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Create Ministry</title>
<%@include file="../common/taglib_includes.jsp"%>
<script src="js/common.js"></script>
<script src="js/validation.js"></script>

<script src="js/organization.js"></script>

<script>
/* $(document)
.ready(
		function() {
			$("#ministryForm")
					.validate(
							{
								rules : {
									ministryName : {
										required : true,
										onlyLetterSpace : true
									},
									shortministryName : {
										shortName : true
									}
								},
								messages : {
									ministryName : {
										required : "<font color='red'><br><spring:message code='MINISTRYNAME.REQUIRED' text='Please enter ministry name'/></font>",
										onlyLetterSpace : "<font color='red'><br><spring:message code='error.valid.ministryName' text='Please enter value in correct format'/></font>"
									},
									shortministryName : {
										shortName : "<font color='red'><br><spring:message code='error.common.short.name' text='Short Name is invalid'/></font>"
									}
								}
							});
		}); */
		
		$(document).ready(function() {	
			
			$('#ministryForm input[type=text]').attr("autocomplete",'off');
			
			$("#btnSave").click(function() {	
				clearFormErrors();
				var ministryName = $("#txtMinistryName").val();	
				var ministryShortName = $("#txtshortministryName").val();
				if(ministryName ==''){
					$('#ministryName_error').html("Please Enter Ministry Name");
					return false;
				}
				else{
					var nameReg = /^[a-zA-Z\s]+$/;
					if (!nameReg.test(ministryName)){
					$('#ministryName_error').html("Please Enter value in Correct format");
					return false;
				}
				}
				if(ministryShortName != ''){
					var nameReg = /^[a-zA-Z\s]+$/;
					if (!nameReg.test(ministryShortName)){
					$('#shortministryName_error').html("Short Name is invalid");
					return false;
				}
				}
				return true;
			});
		});
		function clearFormErrors(){
			$('#ministryName_error').html("");
			$('#shortministryName_error').html("");
		}
</script>
</head>

<body onload="loadMinistryPage();" ononcontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
	<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                   <form:form method="POST" commandName="createMinistry" action="saveMinistryDetails.htm" id="ministryForm" class="form-horizontal">
				     <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="saveMinistryDetails.htm"/>" />
				       
                                
                                
     <div class="box">
      <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.CREATEMINISTRY" htmlEscape="true"></spring:message></h3>
                                </div>
                                
                        <div class="box-header subheading">
                          <h4><spring:message code="Label.GENERALDETAILS" htmlEscape="true"></spring:message></h4>
                        </div>       
                                
       <div class="box-body">
         <div class="form-group">
			<label class="col-sm-3 control-label"><spring:message code="Label.MINISTRYNAME" htmlEscape="true"></spring:message> <span class="errormsg">*</span> </label>
			<div class="col-sm-6">
			<form:input id="txtMinistryName" path="ministryName" 	maxlength="200" class="form-control" onfocus="helpMessage(this,'txtMinistryName_msg');" onblur="hideHelp();" htmlEscape="true"></form:input>
										<span id="txtMinistryName_msg" style="display: none"><spring:message code="error.blank.ministryName" htmlEscape="true"></spring:message> </span>
										<span class="errormsg" id="txtMinistryName_error"><spring:message code="error.blank.ministryName" htmlEscape="true"></spring:message> </span>
										<span><form:errors path="ministryName" class="errormsg" htmlEscape="true"></form:errors></span>
										<span> <form:errors path="ministryName1" class="errormsg" htmlEscape="true"></form:errors></span>
										<span class="errormsg" id="ministryName_error"></span>
			</div>							
		  </div>
		  
		   <div class="form-group">
			<label class="col-sm-3 control-label"><spring:message code="Label.MINISTRYSHORTNAME" htmlEscape="true"></spring:message> <span class="errormsg">*</span> </label>
			<div class="col-sm-6">
			<form:input id="txtshortministryName" path="shortministryName" htmlEscape="true"  maxlength="10" class="form-control"></form:input>
										<span class="errormsg" id="shortministryName_error"></span>
										<span><form:errors path="shortministryName" class="errormsg" htmlEscape="true"></form:errors></span>
			</div>							
		  </div>
		  
		  
       </div>
       
         <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                  <button type="submit" name="Save" id="btnSave" class="btn btn-success" ><spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button>
			       <button type="button" name="Submit3" class="btn btn-warning"  id="btnClear" onclick="resetMinistryForm();" ><spring:message code="Button.CLEAR" htmlEscape="true"></spring:message></button>
                   <button type="button" class="btn btn-danger " name="Cancel"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div>
       
         
   
     </div>  
     
             
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script></body>
</body>
</html>