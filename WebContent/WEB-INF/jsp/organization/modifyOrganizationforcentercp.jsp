<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="../common/taglib_includes.jsp"%>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script src="js/common.js"></script>
<script src="js/createDepartment.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="javascript">
$(document)
.ready(
		function() {
			$("#modifyOrg")
					.validate(
							{
								rules : {
									orgName : {
										required : true,
										onlyLetterSpace : true
									},
									orgNameLocal : {
										nameFormatLocal : true
									},
									shortName : {
										shortName : true
									}
								},
								messages : {
									orgName : {
										required : "<font color='red'><br><spring:message code='error.required.ORGNAME'/></font>",
										onlyLetterSpace : "<font color='red'><br><spring:message code='error.invalid.format' text='Please enter value in correct format'/></font>"
									},
									orgNameLocal : {
										nameFormatLocal : "<font color='red'><br><spring:message code='error.invalid.format' text='Please enter value in correct format'/></font>"
									},
									shortName : {
										shortName : "<font color='red'><br><spring:message code='error.common.short.name' text='Short Name is invalid'/></font>"
									}
								}
							});
		});
function save() {
	var bool = $("#modifyOrg").validate().form();
	if (bool) {
		var orgNameDb = document.getElementById("orgNamech");
		var orgName		 = document.getElementById("orgName");
		if(orgName == orgNameDb) {
			document.getElementById("orgNamech").value = "";
		}
		document.forms['modifyOrg'].submit();
	}
}

</script>
</head>
<body onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">

	<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                   <form:form action="modifyOrganizationCentralforpost.htm" method="POST" commandName="orgTypeForm" id="modifyOrg" class="form-horizontal">
				     <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyOrganizationCentralforpost.htm"/>" />
				    <form:input type="hidden" path="orgCode" name="orgCode" />
				    <form:input type="hidden" path="orgTypeCode" name="orgTypeCode" />
				
		              
				      
     <div class="box"  id="cat">
      <div class="box-header with-border">
             <h3 class="box-title"><spring:message code="Label.MODIFYORG" htmlEscape="true" text="Modify Organization"></spring:message></h3>
       </div>
        <div class="box-header subheading">
                          <h4><spring:message code="Label.GENERALDETAILS" htmlEscape="true"></spring:message></h4>
                        </div>
       <div class="box-body">
            
             <div class="form-group">
               <label class="col-sm-3 control-label" ><spring:message code="Label.ORGNAMEINENG" htmlEscape="true"></spring:message><span class="errormsg">*</span></label>
               <div class="col-sm-6">
                 	<form:input path="orgName" id="orgName"  class="form-control" onblur="('orgName','1','15','c');" maxlength="200" htmlEscape="true"></form:input>
					<form:hidden path="orgNamech" id="orgNamech" value="${orgTypeForm.orgName}"/>
                   <form:errors htmlEscape="true" path="orgName" cssClass="errormsg"></form:errors>
               </div>
             </div>
             
              
              <div class="form-group">
               <label class="col-sm-3 control-label" ><spring:message code="Label.ORGNAMEINLOCAL" htmlEscape="true"></spring:message> &nbsp;<span class="errormsg">*</span></label>
               <div class="col-sm-6">
                 	<form:input path="orgNameLocal"  class="form-control" maxlength="80" htmlEscape="true"></form:input>
                 	<form:errors htmlEscape="true" path="orgNameLocal" cssClass="errormsg"></form:errors>
               </div>
             </div>
             
             <div class="form-group">
               <label class="col-sm-3 control-label" ><spring:message code="Label.ORGSHORTNAMEINENG" htmlEscape="true"></spring:message> </label>
               <div class="col-sm-6">
                 	<form:input path="shortName"  class="form-control" maxlength="10" htmlEscape="true"></form:input>
                 	<form:errors htmlEscape="true" path="shortName" cssClass="errormsg"></form:errors>
               </div>
             </div>
         </div>    
           
         <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                	<button type="submit" name="Submit" class="btn btn-success" value="" onclick="return save();" ><spring:message htmlEscape="true"  code="Button.SAVE"></spring:message></button> 
					<button type="button" name="Submit3" class="btn btn-warning" id="btnClear" onclick="onClear();" > <spring:message code="Button.CLEAR" htmlEscape="true"></spring:message></button>
					<button type="button" name="Submit3" class="btn btn-danger"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button>
                 </div>
           </div>   
       </div> 
           
           
     </div>      
         
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script>

</body>
</html>