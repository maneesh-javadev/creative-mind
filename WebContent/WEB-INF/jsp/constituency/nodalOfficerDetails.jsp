<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<head>
<script type="text/javascript" language="javascript">


</script>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<title>E-Panchayat</title>

<!-- <script type="text/javascript" src="js/district.js" charset="utf-8"></script>
 -->
<script type="text/javascript" src="js/createDistrict.js"></script>


<script src="js/Parliament.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<script src="js/validation.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />

<link href="css/successMessage.css" rel="stylesheet" type="text/css" />

<script src="js/govtorder.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/common.js"></script>

<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>
	
</script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrAssemblyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrParlimentService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/call/plaincall/lgdDwrParlimentECIService.existEntityName.dwr'></script>	
<script src="js/dynCalendar.js" type="text/javascript"></script>
<script src="js/browserSniffer.js" type="text/javascript"></script>
<link href="css/dynCalendar.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);

	 function saveData(){
		 if(checkEmpty()){
			 if(validateMobile()){
				 if(validateForm()){
	        		document.getElementById('nodalOfficerForm').method = "post";
	        		document.getElementById('nodalOfficerForm').action="nodalOfficerDetails.htm?<csrf:token uri='nodalOfficerDetails.htm'/>";
	       			document.getElementById('nodalOfficerForm').submit();
				 }
			 }
		 }
	 }
	 
	 function checkEmpty(){
		 var officerName=$("#nodalUserName").val();
		 var designation=$("#nodalUserDesignation").val();
		 var mobile=$("#nodalUserMobile").val();
		 var email=$("#nodalUserEmail").val();
		 var returnFlag=true;
		 
		 if(officerName==""){
			 $("#nodalUserNameErr").html("Nodal officer name field can not be empty.");
			 returnFlag=false;
		 } else {
			 $("#nodalUserNameErr").html("");
			 
		 }
		 if(designation==""){
			 $("#nodalUserDesignationErr").html("Nodal officer designation field can not be empty.");
			 returnFlag=false;
		 } else {
			 $("#nodalUserDesignationErr").html("");
		 }
		 if(mobile==""){
			 $("#nodalUserMobileErr").html("Nodal officer mobile number field can not be empty.");
			 returnFlag=false;
		 } else {
			 $("#nodalUserMobileErr").html("");
		 }
		 if(email==""){
			 $("#nodalUserEmailErr").html("Nodal officer email field can not be empty.");
			 returnFlag=false;
		 } else {
			 $("#nodalUserEmailErr").html("");
		 }
		 return returnFlag;
	 }
	 
     var testresults
	 function validateForm(){
			var str=document.getElementById('nodalOfficerForm').nodalUserEmail.value
			var filter=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i
			if (filter.test(str))
			testresults=true
			else{
			$("#nodalUserEmailErr").html("Please input a valid email address!");
			testresults=false
			}
			return (testresults)
			}
     
     function validateMobile(){
    	 if(!$('#nodalUserMobile').val().match('[0-9]{10}')) {
             $("#nodalUserMobileErr").html("Please enter 10 digit mobile number.");
             return false;
         }   else {
        	 return true;
         }
     }
     
		
    function validateName(e,id){
    	 var n = e.charCode;
 		if ((n >= 65 && n <= 90)     
 		|| (n >= 97 && n <= 122)  	
 		|| (n == 0)  	
 		|| (n == 32)  	
 		){
 			
 		} else{
         	e.preventDefault();     
			$("#"+id+"_type_error").fadeIn(1000,function(){$("#"+id+"_type_error").fadeOut(1000);});
 	      }
    }
    
    function validateNumber(e,id){
    	 $("#nodalUserMobileErr").html("");
   	 var n = e.charCode;
		if ((n >= 48 && n <= 57)  	
		|| (n == 0)  	
		){
			
		} else{
        	e.preventDefault();     
			$("#"+id+"_type_error").fadeIn(1000,function(){$("#"+id+"_type_error").fadeOut(1000);});
	      }
   }
	
    function resetEmail(){
    	$("#nodalUserEmailErr").html("");
    }
</script>
</head>

<body>
<div class="frmhd">
		<!-- Main Heading starts -->
			<h3 class="subtitle"><spring:message code="Label.AddUpdateNodalOfficer" text=" Add/Update Nodal Officer Details"/></h3>
		</div>
		<div class="clear">	</div>	


	<form:form method="post" id="nodalOfficerForm" action="nodalOfficerDetails.htm" commandName="nodalOfficerForm" >
										<div>
											<div align="center">
												<div ></div>
													<div class="frmpnlbg">
												<table id="nodal" width="100%" align="center" class="tbl_with_brdr">
						                            <tr class="tblRowTitle tblclear" style="line-height: 30px">
							                        	<th  style="width:40%"><spring:message text="Name Of Nodal Officer" htmlEscape="true"></spring:message><font color="red">*</font><br /> </th>
							                        	<td style="width:60%"><form:input path="nodalUserName" id="nodalUserName" name="nodalUserName" maxlength="200" onkeypress="validateName(event,'nodalUserName')"/>
								              	       		<div id="nodalUserName_type_error" style="color: red;font-size: x-small;display:none; ">Alphabets only.</div>
								              	       		<div id="nodalUserNameErr" style="color: red;font-size: x-small;"></div>
								              	       		<form:hidden path="nodalUserId" name="nodalUserId"/>
								              	       		<form:hidden path="nodalUserVersion" name="nodalUserVersion"/>
								              	       		<form:hidden path="createdOn" name="createdOn"/>
								              	       		<%-- <form:hidden path="flag"/> --%>
								              	       		</td>
								              	      </tr>
								              	     <tr class="tblRowTitle tblclear" style="line-height: 30px">
							                        	<th><spring:message text="Designation"></spring:message><font color="red">*</font><br /> </th>
							                        	<td  ><form:input path="nodalUserDesignation" id="nodalUserDesignation" maxlength="200" onkeypress="validateName(event,'nodalUserDesignation')"/>
							                        	<div id="nodalUserDesignation_type_error" style="color: red;font-size: x-small;display:none; ">Alphabets only.</div>
							                        	<div id="nodalUserDesignationErr" style="color: red;font-size: x-small;"></div>
							                        	</td> 
							                        </tr>
							                        <tr class="tblRowTitle tblclear" style="line-height: 30px">
							                        	<th ><spring:message text="Mobile No."></spring:message><font color="red">*</font><br /> </th>
							                        	<td><form:input path="nodalUserMobile" id="nodalUserMobile" maxlength="10" onkeypress="validateNumber(event,'nodalUserMobile')"/>
							                        	<div id="nodalUserMobile_type_error" style="color: red;font-size: x-small;display:none; ">Numbers only.</div>
							                        	<div id="nodalUserMobileErr" style="color: red;font-size: x-small;"></div>
							                        	</td>
							                        </tr>
							                        <tr class="tblRowTitle tblclear" style="line-height: 30px">
							                        	<th><spring:message text="Email Id"></spring:message><font color="red">*</font><br /> </th>
							                        	<td><form:input path="nodalUserEmail" id="nodalUserEmail" name="nodalUserEmail" maxlength="100" onblur="validateForm();" onchange="resetEmail()"/>
							                        	<div id="nodalUserEmailErr" style="color: red;font-size: x-small;"></div>
							                        	</td>
							                        </tr>
							                        <tr class="tblRowTitle tblclear" style="line-height: 30px">
							                        	<!-- <input type="button"  value="Edit" class="btn"  onclick="enableCheck()";  /> -->
							                        	<td align="right" margin-right="20px">
							                        	<c:choose>
							                        	<c:when test="${nodalOfficerForm.nodalUserId ne null }">
							                        	<input type="button"  value="Update" class="btn"  onclick="saveData()" />
							                        	</c:when>
							                        	<c:otherwise>
							                        	<input type="button"  value="Save" class="btn"  onclick="saveData()"  />
							                        	</c:otherwise>
							                        	</c:choose>
							                        	</td>
							                        	
							                        	
														<td>&nbsp;&nbsp;<input class="bttn" type="button" value="<spring:message code="Button.CLOSE"/>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'"/>
													</td>
							                        </tr>
							                    
												</table>
												</div>
												
										
											</div>
										</div>
									</form:form> 
<c:if test="${msgid!= null}">
	<script>
	alert("<spring:message code="${msgid}"/>");
	</script>
</c:if>

</body>
</html>