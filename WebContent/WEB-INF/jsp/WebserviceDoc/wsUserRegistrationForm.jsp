<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@include file="../common/taglib_includes.jsp"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<title>Register For Web Services</title>

<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrWSService.js'></script>
<!-- <script type='text/javascript' src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link type="text/css" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
<script type='text/javascript' src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script> -->



<style>
<style type='text/css'>
.fieldsetclass {
  border: 1px solid #ccc;
  padding: 10px;
}
</style>

</style>
<script type="text/javascript" language="javascript">

$(document).ready(function() {
	countIP = '${countIP}';
	count = '${count}';
	 /* $('#nicOfficialName').prop('required',true);
	$("#nicOfficialDesignation").prop('required',true);
	$("#nicOfficialEmail").prop('required',true);
	$("#nicOfficailMobile").prop('required',true);
	$("#deptOfficialName").prop('required',true);
	$("#deptOfficialDesignation").prop('required',true);
	$("#deptOfficialEmail").prop('required',true);
	$("#deptOfficialMobile").prop('required',true);  */
	/* $("#ipCheck").change(function() {
		
        if($(this).is(":checked")) {
        	$("#ipAddressBlock").show();
        }
        else
        	$("#ipAddressBlock").hide();
    }); */
    	
	if('${createWsUser.stateRadio}' === 'true'){
		document.getElementById('stateRadio').checked = true; 
		document.getElementById('centerRadio').checked = false; 
		$("#statelist").show();
		$("#centerlist").hide();
		getStatewiseOrglist(document.getElementById('stateCode').value);
	}
    
    if('${createWsUser.centerRadio}' === 'true'){
	document.getElementById('stateRadio').checked = false; 
	document.getElementById('centerRadio').checked = true; 
	$("#centerlist").show();
	$("#statelist").hide();
	$("#statewiseOrglist").hide();
	}
	
	
	
	if("${errorPage}"){
		validIp = true;
		
	}
	
});


jQuery.validator.setDefaults({
    debug: true,
    success: "valid"
  });
  



  
function captchaSuccess(data) {
     if(data === "success") {
     	$("#captachaErr").html("");
     	document.getElementById('createWsUser').method = "post";
		
		document.getElementById('createWsUser').action="wsUserRegistrationSubmit.do?<csrf:token 
				uri='wsUserRegistrationSubmit.do'/>";
			document.getElementById('createWsUser').submit();
     	
     } else{
     	$("#captachaErr").html("Captcha text is incorrect, Please enter correct Captcha text.");
           return false;
     }
 }
 
	function validate1() {
		var maxfilesize = 5*1024 * 1024;
		var dName = $("#deptOfficialName").val();
		var dDesig = $("#deptOfficialDesignation").val();
		var dMail = $("#deptOfficialEmail").val();
		var dMobile = $("#deptOfficialMobile").val();
		
		var nName = $("#nicOfficialName").val();
		var nDesig = $("#nicOfficialDesignation").val();
		var nMail = $("#nicOfficialEmail").val();
		var nMobile = $("#nicOfficailMobile").val();
		
		if(dName  && dDesig  && dMail  && dMobile ){
			$('#nicOfficialName').removeAttr('required');
			$("#nicOfficialDesignation").removeAttr("required");
			$("#nicOfficialEmail").removeAttr("required");
			$("#nicOfficailMobile").removeAttr("required");
		}
		else if(nName  && nDesig  && nMail  && nMobile ){
			$("#deptOfficialName").removeAttr("required");
		$("#deptOfficialDesignation").removeAttr("required");
		$("#deptOfficialEmail").removeAttr("required");
		$("#deptOfficialMobile").removeAttr("required");
		}
		
	
		
		
		//$("#captchaAnswer").val($("#captcha").val());
		var form = $( "#createWsUser" );
        if(form.valid()){
        	
        	 /*  var file = $('#uploadFile').prop('files');
        	  if(file.length==0){
        		  $("#fileUploadErr").html("Kindly Attach Approved Doc");
        		   return false;
        	  }
        	  if(file[0].size==0){
        		  $("#fileUploadErr").html("Attach a nonzero size approved doc");
        		   return false;
        	  }
        	  if(file[0].size>maxfilesize){
        		  $("#fileUploadErr").html("File too large: " + file[0].size + ". Maximum size: " + maxfilesize);
        		   return false;
        	  } 
        	  var ext = $('#uploadFile').val().split('.').pop().toLowerCase();
        	  if($.inArray(ext, ['gif','png','jpg','jpeg','pdf','pjpeg']) == -1) {
        		  $("#fileUploadErr").html("Kindly Attach Approved Doc of gif,jpg,pdf,png,jpeg,pjpeg type ");
        		  return false;
        	  } */
        	  
        	
        	   for(var i = 0; i<countIP ; i++){
        		  
          		validateIpAddress(document.getElementById('wsRegisteredIpEntity'+i+'').value,i);
          		if(!validIp){
          			$("#ipAddressErr"+i).html("Please input a valid IP address!");
          			return false;
          		}
          	} 
        	  
        	if ( validateMobileDept()
    				&& validateMobileNic() && validateDeptEmail()
    				&& validateNicEmail()){
        		if(document.getElementById('centerRadio').checked){
        			document.getElementById('centerRadio').value = true;
        		}
        		if(document.getElementById('stateRadio').checked){
        			document.getElementById('stateRadio').value = true;
        		}
        		/* document.getElementById('createWsUser').method = "post";
        		
    		document.getElementById('createWsUser').action="wsUserRegistrationSubmit.do?<csrf:token 
    				uri='wsUserRegistrationSubmit.do'/>";
   			document.getElementById('createWsUser').submit(); */
        		var capchaImgVal = $("#captcha").val(); 
        		lgdDwrWSService.captachAuthentication(capchaImgVal, {
        			callback : captchaSuccess
        		}); 
        	}
    		else
    			return false;
        	
        	 	
        	
        }
		
	}

	
	function disableDeptName(val){
		if(val != 0){
			document.getElementById('deptName').disabled = true;
		document.getElementById('deptName').value =null;
		}
		else
			document.getElementById('deptName').disabled = false;
	}

	function clearOrgName(val){
		if(val != null){
			document.getElementById('stateRadio').checked = false; 
			document.getElementById('centerRadio').checked = false;
			document.getElementById('sel3').value  =  0;
			document.getElementById('sel2').value  =  0;
			document.getElementById('stateCode').value  =  0;
			$("#centerlist").hide();
			$("#statelist").hide();
			$("#statewiseOrglist").hide();
		}
			
		
	}
	
	
	/* function ipCheck(val){
		alert()
		if(val)
			$("#ipAddressBlock").show();
		else
			$("#ipAddressBlock").hide();
		
	} */
	
	function showCorS(val) {
		document.getElementById('sel3').value  =  0;
		document.getElementById('sel2').value  =  0;
		document.getElementById('stateCode').value  =  0;
		document.getElementById('deptName').disabled = false;
		
	
		if (val == 1) {
			$("#centerlist").show();
			$("#statelist").hide();
			$("#statewiseOrglist").hide();
			document.getElementById('stateRadio').checked = false;
			document.getElementById('centerRadio').value=true;
		} else {
			$("#centerlist").hide();
			$("#statelist").show();
			document.getElementById('centerRadio').checked = false;
			document.getElementById('stateRadio').value=true;

		}
	}

	function getStatewiseOrglist(entity_lc) {
		lgdDwrWSService.getOrganizationListByLocation(entity_lc, 1, {
			callback : handleOrgSuccess
		/* errorHandler : handleDistrictError */
		});

	}

	function handleOrgSuccess(data) {
		var fieldId = 'sel2';
		var valueText = 'orgUnitCode';
		var nameText = 'orgUnitName';

		dwr.util.removeAllOptions(fieldId);
		var st = document.getElementById(fieldId);
		st.add(new Option('Select Organization', '0'));
		dwr.util.addOptions(fieldId, data, valueText, nameText);
		$("#statewiseOrglist").show();
		document.getElementById('sel2').value = '${createWsUser.stateOrgUnitCode}';
	}

	function validateMobileDept() {
		if ($('#deptOfficialMobile').val() != "" && !$('#deptOfficialMobile').val().match('[0-9]{10}')) {
			$("#deptOfficialMobileErr").html(
					"Please enter 10 digit mobile number.");
			return false;
		} else {
			return true;
		}
	}

	function validateMobileNic() {
		if ($('#nicOfficailMobile').val() != "" && !$('#nicOfficailMobile').val().match('[0-9]{10}')) {
			$("#nicOfficailMobileErr").html(
					"Please enter 10 digit mobile number.");
			return false;
		} else {
			return true;
		}
	}

	function validateNumber(e, id) {
		$("#deptOfficialMobileErr").html("");
		$("#nicOfficailMobileErr").html("");
		var n = e.charCode;
		if ((n >= 48 && n <= 57) || (n == 0)) {

		} else {
			e.preventDefault();
			$("#" + id + "_type_error").fadeIn(1000, function() {
				$("#" + id + "_type_error").fadeOut(1000);
			});
		}
	}

	function validateDeptEmail() {
		if($("#deptOfficialEmail").val() != ""){
		var str = document.getElementById('createWsUser').deptOfficialEmail.value
		var filter = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i
		if (filter.test(str))
			testresults = true
		else {
			$("#deptOfficialEmailErr").html(
					"Please input a valid email address!");
			testresults = false
		}
		return (testresults)
		}
		return true;
	}

	function validateNicEmail() {
		if($("#nicOfficialEmail").val() != ""){
		var str = document.getElementById('createWsUser').nicOfficialEmail.value
		var filter = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i
		if (filter.test(str))
			testresults = true
		else {
			$("#nicOfficialEmailErr").html(
					"Please input a valid email address!");
			testresults = false
		}
		return (testresults)
		}
		return true;
	}

	function resetDeptEmail() {
		$("#deptOfficialEmailErr").html("");
	}
	function resetNicEmail() {
		$("#nicOfficialEmailErr").html("");
	}
	
	function clearErrMsg() {
		 $("#fileUploadErr").html("");
	}
	var validIp = false;
	function validateIpAddress(inputText,ipindex) {
		var ipformat = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
		if (ipformat.test(inputText)) {
			$("#ipAddressErr"+ipindex).html("");
			validIp = true;
			return true;
		} else {
			$("#ipAddressErr"+ipindex).html("Please input a valid IP address!");
			validIp = false;
			return false;
		}
	}

	function removeapp(el){
		 $(el).parents("tr").remove();
		 count--;
	};
	function removeip(el){
		 $(el).parents("tr").remove();
		 countIP--;
	};
	var count = 0;
	function addNewTextBoxApp() {
		
		if (count < 4) {
			count++;
			$('#maintableapp tr:last').after("<tr><td><input type='text' class='form-control' id='t1' name='wsSubscribedApplicationEntity["+count+"].applicationName' pattern = '^[a-zA-Z\\s]+$' required placeholder='S/w application'><form:errors htmlEscape='true' path='wsSubscribedApplicationEntity["+count+"].applicationName' cssClass='errormsg'/></td><td><button type='button' class='btn btn-danger' onclick='removeapp(this)'>Remove</button></td></tr>");
	}
	}
	
	var countIP = 0;
	function addNewTextBoxIP() {
		
		if (countIP < 4) {
			countIP++;
			$('#maintableip tr:last').after("<tr><td><input type='text' class='form-control' id = 'wsRegisteredIpEntity"+countIP+"' name='wsRegisteredIpEntity["+ countIP+ "].ipAddress' onblur='validateIpAddress(this.value,"+countIP+")' required placeholder='IP address'><form:errors htmlEscape='true' path='wsRegisteredIpEntity["+countIP+"].ipAddress' cssClass='errormsg'/><div id='ipAddressErr"+countIP+"' style='color: red;font-size: x-small;'></div> </td><td><button type='button' class='btn btn-danger'  onclick='removeip(this)'>Remove</button></td></tr>");
		}
	}
	
	
	function captchaReferesh(){
	 	
 		
		 var timestamp = (new Date()).getTime();
        var newSrc = $("#captchaImageId").attr("src").split("?");
     //  $('#captchaImage').attr('src', '').attr('src', 'Captcha.jpg');
        newSrc = newSrc[0] + "?" + timestamp;
        $("#captchaImageId").attr("src", newSrc);
        $("#captchaImageId").slideDown("fast");
	};
	
	
</script>
</head>
<body>
	
		<form:form id = "createWsUser" action="wsUserRegistrationSubmit.do" method="POST" commandName="createWsUser" enctype="multipart/form-data" class="form-horizontal">
		<!-- <input type="hidden" id="captchaAnswer" name="captchaAnswer" value=""> -->
 <section class="content">
    <div class="row">
        <section class="col-lg-12 ">
            <div class="box ">
               <div class="box-header with-border">
                    <h3 class="box-title">Registration form to consume web services</h3></div>
                   
                         <div class="box-body">
                         
                         
                          <fieldset style="border: 1px solid #ccc;padding: 10px;">
                               <legend> <h4>Department Official Details</h4></legend>
                              
                             <div class="form-group">
                                <label class="col-sm-3 control-label"> Name <span class="mandatory">*</span></label>
	                               <div class="col-sm-6">
	                               <form:input path= "deptOfficialName" pattern = "^[a-zA-Z\\s]+$" class="form-control" id="deptOfficialName" maxlength="70" required = "true" placeholder="Enter Department Name"/>
	                               <form:errors htmlEscape="true" path="deptOfficialName" cssClass="errormsg"/>
	                               </div> 
                             </div>
                             
                             <div class="form-group">
                                <label class="col-sm-3 control-label"> Designation <span class="mandatory">*</span></label>
	                               <div class="col-sm-6">
	                               <form:input class="form-control" id="deptOfficialDesignation" pattern = "^[a-zA-Z\\s]+$" path="deptOfficialDesignation" maxlength="70" required = "true" placeholder="Enter Department Designation"/>
	                               <form:errors htmlEscape="true" path="deptOfficialDesignation" cssClass="errormsg"/>
	                               </div> 
                             </div>
                             
                              <div class="form-group">
                                <label class="col-sm-3 control-label"> Email Id <span class="mandatory">*</span></label>
	                               <div class="col-sm-6">
	                                 <form:input class="form-control" id="deptOfficialEmail"  path="deptOfficialEmail" required = "true" placeholder="Enter Department Email Id" maxlength="100" onblur="validateDeptEmail()" onchange="resetDeptEmail()" />
	                                 <form:errors htmlEscape="true" path="deptOfficialEmail" cssClass="errormsg"/>
	                               <div id="deptOfficialEmailErr" style="color: red;font-size: x-small;"></div>
	                               </div> 
                               </div>
                               
                                <div class="form-group">
                                <label class="col-sm-3 control-label"> Mobile No.<span class="mandatory">*</span></label>
	                               <div class="col-sm-6">
	                                 <form:input class="form-control" id="deptOfficialMobile"  path="deptOfficialMobile" required = "true" placeholder="Enter Department Mobile No." maxlength="10" onblur="validateMobileDept()" onkeypress="validateNumber(event,'deptOfficialMobile')"/>
	                                  <form:errors htmlEscape="true" path="deptOfficialMobile" cssClass="errormsg"/>
	                               <div id="deptOfficialMobile_type_error" style="color: red;font-size: x-small;display:none; ">Numbers only.</div>
	                               <div id="deptOfficialMobileErr" style="color: red;font-size: x-small;"></div>
	                               </div> 
                               </div>

                               </fieldset>
                               
                               
                               
                               
                               <fieldset style="border: 1px solid #ccc;padding: 10px;">
                               <legend> <h4>NIC Official Details</h4></legend>
                               <div class="form-group">
                                <label class="col-sm-3 control-label"> Name <span class="mandatory">*</span></label>
	                               <div class="col-sm-6">
	                               <form:input class="form-control" id="nicOfficialName" pattern = "^[a-zA-Z\\s]+$" path="nicOfficialName" maxlength="70" required = "true" placeholder="Enter NIC Official Name"/>
	                               <form:errors htmlEscape="true" path="nicOfficialName" cssClass="errormsg"/>
	                               </div> 
                             </div>
                              <div class="form-group">
                                <label class="col-sm-3 control-label"> Designation <span class="mandatory">*</span></label>
	                               <div class="col-sm-6">
	                               <form:input  class="form-control" id="nicOfficialDesignation" pattern = "^[a-zA-Z\\s]+$" path="nicOfficialDesignation" maxlength="70" required = "true" placeholder="Enter NIC Official Designation"/>
	                               <form:errors htmlEscape="true" path="nicOfficialDesignation" cssClass="errormsg"/>
	                               </div> 
                             </div>
                             
                              <div class="form-group">
                                <label class="col-sm-3 control-label"> Email Id <span class="mandatory">*</span></label>
	                               <div class="col-sm-6">
	                                 <form:input class="form-control" id="nicOfficialEmail"  path="nicOfficialEmail" required = "true" placeholder="Enter NIC Official Email Id" maxlength="100" onblur="validateNicEmail()" onchange="resetNicEmail()"/>
	                                 <form:errors htmlEscape="true" path="nicOfficialEmail" cssClass="errormsg"/>
	                               <div id="nicOfficialEmailErr" style="color: red;font-size: x-small;"></div>
	                               </div> 
                               </div>
                               
                                <div class="form-group">
                                <label class="col-sm-3 control-label"> Mobile No.<span class="mandatory">*</span></label>
	                               <div class="col-sm-6">
	                                 <form:input class="form-control" id="nicOfficailMobile" path="nicOfficailMobile" required = "true" placeholder="Enter NIC Official Mobile No." maxlength="10" onblur="validateMobileNic()" onkeypress="validateNumber(event,'nicOfficailMobile')"/>
	                                 <form:errors htmlEscape="true" path="nicOfficailMobile" cssClass="errormsg"/>
										<div id="nicOfficailMobile_type_error" style="color: red;font-size: x-small;display:none; ">Numbers only.</div>
							            <div id="nicOfficailMobileErr" style="color: red;font-size: x-small;"></div>
	                               
	                               </div> 
                               </div>
                             </fieldset>  
                             <div id="deptNicDetails" style="color: red;"></div>
                            <div class="form-group">
								  <label  class="col-sm-3 control-label" for="sel1">Department/Organization</label>
								  <div class="col-sm-6">
									  <label class="radio-inline"><form:radiobutton id="centerRadio" path = "centerRadio" onclick="showCorS(1);"/>Center</label>
									  <label class="radio-inline"><form:radiobutton id="stateRadio" path = "stateRadio" onclick="showCorS(2);" />State</label>
								  </div>
					       </div>
					       
					      <%--  <div class="form-group" id="centerlist" style="display: none;">
					        <label class="col-sm-3 control-label"> Select Center </label>
					         <div class="col-sm-6">
					          <form:select class="form-control" id="sel1" path = "orgUnitCode">
					          <form:option value="0">Select</</form:option>
									<c:forEach items="${centerlist}"
											var="centerlist">
											<form:option value="${centerlist.orgUnitCode}">${centerlist.orgUnitName}</form:option>
										</c:forEach>
									</</form:select>
					         </div>
					       </div> --%>
					       
					        <div class="form-group" id="centerlist" style="display: none;">
					        <label class="col-sm-3 control-label">Select Center</label>
					         <div class="col-sm-6">
					          <form:select class="form-control" id="sel3" onchange = "disableDeptName(this.value)" path = "orgUnitCode">
					          <form:option value="0">Select</form:option>
									<c:forEach items="${centerlist}"
											var="centerlist">
											<form:option value="${centerlist.orgUnitCode}">${centerlist.orgUnitName}</form:option>
										</c:forEach>
									</form:select>
					         </div>
					       </div>
					       
					       <div class="form-group" id="statelist" style="display: none;">
					        <label class="col-sm-3 control-label">Select State</label>
					         <div class="col-sm-6">
					          <form:select class="form-control" id="stateCode" path = "stateCode" onchange="getStatewiseOrglist(this.value)">
					          <form:option value="0">Select</form:option>
									<c:forEach items="${stateList}"
											var="stateList">
											<form:option value="${stateList.statePK.stateCode}">${stateList.stateNameEnglish}</form:option>
										</c:forEach>
									</form:select>
					         </div>
					       </div>
					       
					       <div class="form-group" id="statewiseOrglist" style="display: none;">
					        <label class="col-sm-3 control-label">Select Organization</label>
					         <div class="col-sm-6">
					          <select class="form-control" id="sel2" name = "stateOrgUnitCode" onchange = "disableDeptName(this.value)" value="${createWsUser.stateOrgUnitCode}">
									<%-- <c:forEach items="${statewiseOrglist}"
											var="statewiseOrglist">
											<option value="${statewiseOrglist.orgUnitCode}">${statewiseOrglist.org_unit_name}</option>
										</c:forEach> --%>
									</select>
					         </div>
					       </div>
					       
					        <div class="form-group" >
					        <label class="col-sm-3 control-label">Department Name</label>
					         <div class="col-sm-6">
					           <form:input  class="form-control" id="deptName" path="deptName" onchange = "disableDeptName(this.value)" placeholder="Enter department name if department name is not present above"/>
					         </div>
					       </div>
					       
						<div class="box-header subheading">
	                    <h4 >Name of the software application in which these web services will be used </h4></div>
                    
		                    <div class="form-group">
		                      <label  class="col-sm-3 control-label"> Application Name </label>
		                      <table id="maintableapp" >
		                      <c:if test = "${intialPage}">
		                      <tr>
		                      <td>
		                      
		                      <form:input  class="form-control" id="t1" path="wsSubscribedApplicationEntity[0].applicationName" pattern = "^[a-zA-Z\\s]+$" required = "true" placeholder="S/w application" />
		                        <form:errors htmlEscape="true" path="wsSubscribedApplicationEntity[0].applicationName" cssClass="errormsg"/>
		                     
		                      </td>
		                      <td> <input type="button" value="Add New" name="add" class="btn btn-primary" id="add_new" onclick="addNewTextBoxApp()"> </td>
		                      </tr>
		                      </c:if>
		                      <c:if test = "${errorPage}">
		                      <c:forEach items="${createWsUser.wsSubscribedApplicationEntity}" var="abc" varStatus="index">
		                      <tr><td>
		                      <form:input  class="form-control" id="t1" path="wsSubscribedApplicationEntity[${index.index}].applicationName" pattern = "^[a-zA-Z\\s]+$" required = "true" placeholder="S/w application"/>
		                        </td>
		                       
		                        <td> <c:if test="${index.index ne 0 }"><button type='button' onclick='removeapp(this)'>Remove</button></c:if>
		                         <form:errors htmlEscape="true" path="wsSubscribedApplicationEntity[${index.index}].applicationName" cssClass="errormsg"/>
		                        </td>
		                        </tr>
		                      </c:forEach>
		                      </c:if>
		                      </table>
		                    </div>
		                    
		                     <!-- <div class="form-group">
		                      <label for="inputPassword3" class="col-sm-3 control-label"></label>
		                      <div class="col-sm-6">
		                        <input type="text" class="form-control" id="t2" name="npassword" placeholder="S/w application 2">
		                      </div>
		                    </div>
		                    <div class="form-group">
		                      <label for="inputPassword3" class="col-sm-3 control-label"></label>
		                      <div class="col-sm-6">
		                        <input type="password" class="form-control" id="t3" name="cpassword" placeholder="S/w application 3">
		                      </div>
		                    </div> -->
		                    
						<div  class="box-header subheading">
	                    <h4 > Enter IP Addresses from which you would like to consume web services </h4></div>
                    
		                     <div class="form-group" >
		                      <label class="col-sm-3 control-label">IP Addresses </label>
		                      <table id="maintableip">
		                       <c:if test = "${intialPage}">
		                      <tr>
		                      <td>
		                     
		                        <form:input id ="wsRegisteredIpEntity0" path="wsRegisteredIpEntity[0].ipAddress" class="form-control"  required = "true" onblur="validateIpAddress(this.value,0)"  placeholder="IP address"/>
		                        <form:errors htmlEscape="true" path="wsRegisteredIpEntity[0].ipAddress" cssClass="errormsg"/>
		                        <div id="ipAddressErr0" style="color: red;font-size: x-small;"></div>
		                      
		                      </td>
		                      
		                      <td> <input type="button" value="Add New" name="addn" class="btn btn-primary" id="add_new" onclick="addNewTextBoxIP()"> </td>
		                      </tr>
		                      </c:if>
		                      <c:if test = "${errorPage}">
		                      <c:forEach items="${createWsUser.wsRegisteredIpEntity}" var="abc" varStatus="index">
		                      <tr><td>
		                      <form:input  class="form-control" id="wsRegisteredIpEntity0" path="wsRegisteredIpEntity[${index.index}].ipAddress" onblur="validateIpAddress(this.value)" required = "true" placeholder="IP address"/>
		                        </td>
		                        <td> <c:if test="${index.index ne 0 }"><button type='button' onclick='removeip(this)'>Remove</button></c:if>
		                         <form:errors htmlEscape="true" path="wsRegisteredIpEntity[${index.index}].ipAddress" cssClass="errormsg"/>
		                        </td>
		                        </tr>
		                      </c:forEach>
		                      </c:if>
		                      </table>
		                    </div>
		                    
		                   
		                <!--     <div class="form-group">
		                      <label for="inputPassword3" class="col-sm-3 control-label"></label>
		                      <div class="col-sm-6">
		                        <input type="text" class="form-control" id="t2" name="npassword" placeholder="IP address 2">
		                      </div>
		                    </div>
		                    <div class="form-group">
		                      <label for="inputPassword3" class="col-sm-3 control-label"></label>
		                      <div class="col-sm-6">
		                        <input type="password" class="form-control" id="t3" name="cpassword" placeholder="IP address 3">
		                      </div>
		                    </div>	 -->       
 							<div class="form-group">
		                    <label class="col-sm-3 control-label"> Click to consume web services from Mobile App </label>
		                    <form:checkbox id ="ipCheck" path ="ipCheck" title="Please keep generated token in .so or .lib file."  />
		                    </div>
 							
 					<div class="box-header subheading">
	                    <h4 > File Upload Request </h4></div>
                    
		                   <%--   <div class="form-group">
		                      <label class="col-sm-3 control-label"> </label>
		                      <div class="col-sm-6">
		                        <!-- <input type="file" class="form-control" id="t1" accept=".pdf" name="fileUpload[0]" placeholder="Upload file"> -->
		                        <form:errors htmlEscape="true" path="fileUpload[0]" cssClass="error" />
		                        <form:input type="file"   id="uploadFile" onchange="clearErrMsg()" path="fileUpload"  placeholder="Upload file" accept="application/pdf,image/x-png,image/gif,image/jpeg"/>
		                        <form:errors htmlEscape="true" path="fileUpload" cssClass="errormsg" />
		                      </div>
		                      <div id="fileUploadErr" style="color: red;"></div>
		                    </div>	 --%>
		                    
		                    
		                     <div class="form-group">
		                     <label class="col-sm-4 control-label"></label>
		                       <div class="col-sm-6">
		                        <img src="captchaImage" alt="Captcha Image" /><br>
		                       </div>
		                   </div>
		                    
		                   <div class="form-group">
		                     <label for="captchaAnswer" class="col-sm-4 control-label"><spring:message code="captcha.message"	htmlEscape="true" /><span class="mandatory">*</span></label>
		                       <div class="col-sm-6">
		                      	 <input type="text" class = "form-control" id="captcha" maxlength="5" name="captchaAnswer"  required = "true" autocomplete="off" class="form-control" />
								   <c:if test="${not empty captchaFaliedMessage and not captchaFaliedMessage}">
									<div class="errormsg"><spring:message code="captcha.errorMessage" htmlEscape="true" /></div>
								  </c:if>
								  <div id="captachaErr" style="color: red;"></div>
								  <%-- <form:errors path="captchaAnswer" />
										<div class="errormsg" id="errorCaptcha" style="visibility: hidden; display: none;"><spring:message code="captcha.errorMessage"/></div> --%>
		                       </div>
	                    	</div>
		                    
		                    <%--  <div class="form-group" align="center">
					    <div class="col_1" style="width: 70%;" align="left">
											<img src="captchaImage" id="captchaImageId" alt="Captcha Image" />
													<label><img src="${pageContext.request.contextPath}/images/refreshCaptcha.png" id="refCaptchaId" alt="Captcha Image Referesh" border="0" onclick="captchaReferesh();"/></label>	
											<label>
												<spring:message code="captcha.message"	htmlEscape="true" />
												<span class="mandatory">*</span>
											</label>
											<div class="col-sm-6">
											<input type="text" class = "form-control" id="captcha" autocomplete="off" width="10%"/>
											<c:if test="${not empty captchaFaliedMessage and not captchaFaliedMessage}">
												<br/><label><!-- Used Label, please dont remove. --></label>
												<span class="errormsg"><strong><spring:message code="captcha.errorMessage"/></strong></span>
											</c:if>
											</div>
								
							</div>
							</div> --%>
		                    
		                    
		                 
		            <div class="box-footer">
                     <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
                       <button name="register" value="Register" class="btn btn-success" type="submit"> <span class="glyphicon glyphicon-user"> Register</button>
                            <!-- <button type="submit"  onclick="validate()" class="btn btn-primary"><span class="glyphicon glyphicon-user">   Register</button> -->
                            <!-- <button type="submit"  class="btn btn-danger "><i class="fa fa-times-circle"></i> Close</button> -->
                            <button type="button" name="Submit3" id="btn1" class="btn btn-danger" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
                        </div>
                     </div>   
                  </div>
		                    			
                 </div>
                 
                  
              
                
              </div>
         </section>
 </div>
 </section>
 </form:form>

</body>
</html>