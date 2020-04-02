

<%@include file="taglib_includes.jsp"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'> </script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'> </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/pesswitchunitService.js"></script>


<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/trim-jquery.js"></script>
<script src="js/validation.js" type="text/javascript"></script>
<script src="js/util.js" type="text/javascript"></script>
<script src="js/menu_generation.js" type="text/javascript"></script>
<script type="text/javascript" src="js/successMessageAlert.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">



function validateswithunitForm() {
	
	var flage = false;
	var id = document.getElementById("level0").value;
	var stateICode = document.getElementById("stateId").value;
	var yearObj=document.getElementById("year");
	
	//alert("stateICode=="+stateICode);
	
	//alert("id==="+id);
		//$("#level0").val();
	
	var errors = new Array();
	var error = false;
	errors[0] = vlidateOnblur('level0','1','100','m');
	errors[1] = vlidateOnblur('assignedUnit','1','100','m');
	
	if(id=='G_1'){
		errors[3] = false;
		errors[4] =false;
	}
	else if(id=='G_2'){
		
		if(stateICode!=34){
			flage = vlidateOnblur('level1','1','75','m');
		}
		errors[2] = flage;
		errors[4] = false;
	}
     if(id=='G_3'){
		errors[2] = vlidateOnblur('level1','1','75','m');
		errors[3] = vlidateOnblur('level2','1','75','m');
	}
     
     /* if(yearObj!=null){
    	 errors[2]=vlidateOnblur('year','4','4','m')
     } */

	for ( var i = 0; i <= 2; i++) {
		if (errors[i] == true) {
			error = true;
			break;
		}
	}
	if (error == true) {
//		alert("Please fill the profile form properly ...!");
		showClientSideError();
		return false;
	} else {
		return true;
	}
}
function submitSwitchunit() {
	
	
	if(validateswithunitForm() != false)
	{
		document.switchForm.action="switchunit.htm";
		document.switchForm.submit();
		document.switchForm.switchunitsubmit.disabled=true;
	
	}

}



function getLevel_EntityType()
{	
		if(document.getElementById("stateId").value >= 0 && $('#year').val()!=-1){
			getEntityType();
		}
		else
			{
			 for(var l=0;l<5;l++){
			  $('#level'+l+'tr option').each(function(){
				  $(this).remove();
			  });	
			  $('#level'+l+'tr').hide();
			 }
			 $('#assignedUnittr option').each(function(){
				 $(this).remove();
			 });
			  $('#assignedUnittr').hide();
			  $('#memberBlock').hide();
			}
}





</script>











</head>
<body>
<div class="overlay" id="overlay1" style="display:none;"></div>
 <div class="box" id="box">
            <a class="boxclose" id="boxclose"></a>
             <div id="validate_error" >
            <table>
      		 <tr>
					<td rowspan="2"><div class="errorImg"></div>
					</td>
					<td>
						<div class="helpMsgHeader" style="width: 275px;" >
							<h4>
								<spring:message code="Label.ErrorMessage" htmlEscape="true" />
							</h4>
						</div></td>
				</tr>
				<tr>
					<td>
						<div class="errorfont">
							<spring:message code="switchunitform.entitytype.required"  htmlEscape="true"/>
						</div></td>
				</tr>
            </table>
                         
            </div>            
            </div>   
            
            
           

	<form:form commandName="switchUnitForm" name="switchForm" action="switchunit.htm">
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="switchunit.htm"/>
		<form:input path="applicationId" id="applicationId" name="applicationId" type="hidden" class="frmfield"  value="${userPrivilegesForm.applicationId}"/>
		<center style="color: red;"><c:out value="${switchUnit_error}" escapeXml="true"></c:out></center>
		<div class="frmpnlbg " style="display: none;">
			<div class="frmtxt">
				<div class="frmhdtitle">Level</div>
				<table width="100%" class="tbl_no_brdr contentTable">
					<tr valign="top">
						<td width="75%"><label for="typeOfUser"> <spring:message code="switchunitform.typeofuser" htmlEscape="true"/> </label><span class="mndt">*</span><br />
						 <form:select cssStyle="width:250px;" path="typeOfUser" id="typeOfUser"
								cssClass="combofield"  onchange="getLevelOfAssignedUnit()" htmlEscape="true">
								
									
								
								
								<c:if test="${fn:contains(IS_CENTER_STATE, 's')}">
								<option value="s" selected="selected">State</option>
								</c:if>
								
								<c:if test="${fn:contains(IS_CENTER_STATE, 'c')}">  
								<option value="c" selected="selected">Center</option>
								</c:if>
								
							</form:select>
						<td width="25%"></td>
					</tr>
				</table>
			</div>
		</div>
		 <div class="errormsg"> 
                                       <form:errors path="typeOfUser" htmlEscape="true"/>
                                       </div>
		<div class="frmpnlbg" style="display: none;" id="assignedUnitDiv">
			<div class="frmtxt">
				<div class="frmhdtitle"><spring:message code="Label.assignedUnit" htmlEscape="true"/></div>
				<div class="error"  style="display: none;"  id="district_not_enable"><spring:message	code="Error.district.not.enable" htmlEscape="true"/></div>
					<div class="errormsg">	<form:errors path="entityType" htmlEscape="true" /> </div>
				<table width="100%" id="tableToAppend"
					class="tbl_no_brdr contentTable">
					<tr valign="top" id="stateTr" style="display: none;">
						<td width="75%"><label for="stateId"> <spring:message code="Label.State" htmlEscape="true"/></label><span class="mndt">*</span><br />
						<form:select cssStyle="width:250px;" path="stateId" htmlEscape="true" id="stateId"
						cssClass="combofield" onfocus="validateOnFocus('stateId')" 
						onblur="vlidateOnblur('stateId','1','75','m')" onchange="getEntityType()">
								<!-- <option value="">--- Select---</option> -->
								<c:forEach items="${userPrivilegesForm.listUserCategories}" var="userCategories">
									<option value="${userCategories.categoryCode}" selected="selected"><c:out value="${userCategories.categoryName}" escapeXml="true"></c:out></option>
								</c:forEach>
							</form:select>
							<div class="error" id="stateId_error"><spring:message code="switchunitform.entitytype.required" htmlEscape="true"/></div></td>
						<td></td>
					</tr>
					
					<tr valign="top" id="Year" style="">
						<td width="auto"><label for="Year"> <spring:message	code="Label.Year" htmlEscape="true" /> </label><span
							class="mndt">*</span><br /><select  style="width:250px;" name="year" id="year" class="combofield" onblur="vlidateOnblur('year','4','4','m')" onchange="getLevel_EntityType()">
								
								<option value="-1"><spring:message code="label.select.financial.year" text="Select Financial Year"/></option>
								<c:forEach var="years" items="${yearList}">
									<option value="${years.id}"><c:out value="${years.financialYear}" escapeXml="true"></c:out></option>
								</c:forEach>
							</select>
							<div class="error" id="year_error" style="width:25%">
								<spring:message code="switchunitform.entitytype.required" htmlEscape="true"/>
							</div>
							<div id="year_maxlength_error" class="error" style="width:25%"><spring:message code="Error.InvalidLength" htmlEscape="true"/></div>
							 </td>
						<td width="25%"></td>
					</tr> 
					
					
					<tr valign="top" id="level0tr" style="display: none;">
						<td width="75%"><label for="level0"> <spring:message code="Label.entityType" htmlEscape="true"/> </label><span class="mndt">*</span><br />
						<form:select cssStyle="width:250px;" path="entityType" htmlEscape="true" id="level0"
								cssClass="combofield" onfocus="validateOnFocus('level0')"
								onblur="vlidateOnblur('level0','1','75','m')"
								onchange="getAssignedUnit(0);enableSwitchunitForm();">
							<!-- 	<option value="">--- Select gg---</option> -->
							</form:select>
							<div class="error" id="level0_error"><spring:message code="switchunitform.entitytype.required" htmlEscape="true"/></div>
						<td width="25%"></td>
					</tr>
					<tr valign="top" id="level1tr" style="display: none;">
						<td width="75%"><label for="level1"></label><span class="mndt">*</span><br /> 
						<form:select cssStyle="width:250px;" path="level1" htmlEscape="true" id="level1"
								cssClass="combofield" onfocus="validateOnFocus('level1')"
								onblur="vlidateOnblur('level1','1','75','m')"
								onchange="validateDistrict('${userPrivilegesForm.applicationId}');getAssignedUnit(1);">
								<!-- <option value="">--- Select ---</option> -->
							</form:select>
							<div class="error" id="level1_error"><spring:message code="switchunitform.entitytype.required" htmlEscape="true"/></div>
						<td width="25%"></td>
					</tr>
					
					<tr valign="top" id="level2tr" style="display: none;">
						<td width="75%"><label for="level2"></label><span class="mndt">*</span><br /> 
						<form:select cssStyle="width:250px;" htmlEscape="true" path="level2" id="level2"
								cssClass="combofield" onfocus="validateOnFocus('level2')"
								onblur="vlidateOnblur('level2','1','75','m')"
								onchange="getAssignedUnit(2)">
							<!-- 	<option value="">--- Select ---</option> -->
							</form:select>
							<div class="error" id="level2_error"><spring:message code="switchunitform.entitytype.required" htmlEscape="true"/></div>
						<td width="25%"></td>
					</tr>
					<tr valign="top" id="level3tr" style="display: none;">
						<td width="75%"><label for="level3"></label><span class="mndt">*</span><br /> 
						<form:select cssStyle="width:250px;" htmlEscape="true" path="level3" id="level3"
								cssClass="combofield" onfocus="validateOnFocus('level3')"
								onblur="vlidateOnblur('level3','1','75','m')"
								onchange="getAssignedUnit(3)">
							<!-- 	<option value="">--- Select ---</option> -->
							</form:select>
							<div class="error" id="level3_error"><spring:message code="switchunitform.entitytype.required" htmlEscape="true"/></div>
						<td width="25%"></td>
					</tr>
					<tr valign="top" id="level4tr" style="display: none;">
						<td width="75%"><label for="level4"></label><span class="mndt">*</span><br /> 
						<form:select cssStyle="width:250px;" htmlEscape="true" path="level4" id="level4"
								cssClass="combofield" onfocus="validateOnFocus('level4')"
								onblur="vlidateOnblur('level4','1','75','m')"
								onchange="getAssignedUnit(4)">
							<!-- 	<option value="">--- Select ---</option> -->
							</form:select>
							<div class="error" id="level4_error"><spring:message code="switchunitform.entitytype.required" htmlEscape="true"/></div>
						<td width="25%"></td>
					</tr>
					<tr valign="top" id="assignedUnittr" style="display: none;">
						<td width="75%"><label for="assignedUnit"><spring:message code="Label.assignedUnit" htmlEscape="true"/></label><span class="mndt">*</span><br /> 
						<form:select path="assignedUnit" htmlEscape="true" id="assignedUnit"
								cssClass="combofield" cssStyle="width:250px;" multiple="false"
								onfocus="validateOnFocus('assignedUnit')"
								onblur="vlidateOnblur('assignedUnit','1','75','m')"
								onchange="validateZilaDistrict('${userPrivilegesForm.applicationId}');">
							<!-- 	<option value="">--- Select ---</option> -->
							</form:select>
							<div class="error" id="assignedUnit_error"><spring:message code="switchunitform.entitytype.required" htmlEscape="true"/></div>
						<td width="25%"></td>
					</tr>
						
				</table>
			</div>
		</div>
		
		<div class="btnpnl">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><label> <input type="button" class="btn"
							id="switchunitsubmit"   	onclick="submitSwitchunit()" value="Submit" /> </label> <label> <input
							name="reset" class="btn" type="reset" value="Clear" /> </label></td>
				</tr>
			</table>
		</div>
	</form:form>
	
	
<script  type="text/javascript"> 

if(document.getElementById("typeOfUser").value == 's' || document.getElementById("typeOfUser").value == 'c')
{
	getLevelOfAssignedUnit();
}
if(document.getElementById("stateId").value >= 0){
	getEntityType();

}



if(document.getElementById("level0").value == ""){
	
}else{
	getAssignedUnit(1);
}


</script>
	

	</body>
</html>