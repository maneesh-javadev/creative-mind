<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<title>E-Panchayat</title>

<!-- <script type="text/javascript" src="js/district.js" charset="utf-8"></script>
 --><!-- <script type="text/javascript" src="js/createDistrict.js"></script> -->
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
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrParlimentService.js'></script>

<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrAssemblyService.js'></script>
<script src="js/dynCalendar.js" type="text/javascript"></script>
<script src="js/browserSniffer.js" type="text/javascript"></script>
<link href="css/dynCalendar.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="javascript">
dwr.engine.setActiveReverseAjax(true);

function parliamentresetForm()
{
	document.getElementById('pcNameEnglish').value="";
	document.getElementById('pcNameLocal').value="";
	document.getElementById('ecicode').value="";

}
function setDirection(val)
{
	document.getElementById('direction').value=val;
	document.forms['form1'].action = 'ParliamentConstituencyPagination.htm';
	document.forms['form1'].submit();
}
function validate()
{
			document.forms['form1'].submit();
		
}

function validModifyParliament() {
	
	var pcNameEnglish = document.getElementById('pcNameEnglish');

	if (pcNameEnglish.value == "")  {
		$("#pcNameEnglish_error").show();
		return false;
	}
	$("#pcNameEnglish_error").hide();
	return true;
}
function selectParliamentcONSTITUENCY() {
	//selectDistrict();


	var errors = new Array();
	var error = false;
	errors[0] = !validModifyParliament();
	
	
	for ( var i = 0; i < 1; i++)
	{
		
		if (errors[i] == true) {
			
			error = true;
			break;
		}
	}

	if (error == true) {

		showClientSideError();

		return false;
	} else {
		document.forms['form1'].submit();

		return true;
		
	}
	return false;
}
function validateSpecialCharactersforPCACValues(value, num) {
	var status = true;
	var obj = "";
	if (num == 1)
		obj = document.getElementById('pcNameLocal');
	else if (num == 2)
		obj = document.getElementById('txtNameLocal');
	var retVal = true;
	retVal = validateSpecialCharacters(value);
	if (retVal == false) {
		if (num == 1)
			alert("Parliament Name in Local Language contains invalid characters !");
		else if (num == 2)
			alert("Assembly  Name in Local Language contains invalid characters  !");
		obj.value = "";
		status = false;
	}

	return stautus;
}


function doRefresh(removeAll)
{
	
	if (removeAll)
		{
			document.getElementById('ddSourceState').selectedIndex=0;
			removeSelectedValue('ddSourceDistrict');
		}
	removeSelectedValue('ddSourceSubDistrict');	
}
function selectDistrict(id,name)
{     
	
		if (id.match('PART')=='PART'){
		
	var selObj=document.getElementById('ddDestDistrict');	
		lgdDwrStateService.getDistricts(id, {
		callback : handleDistrictSuccess,
		errorHandler : handleDistrictError
	});
	
		
	}else {
		dwr.util.removeAllOptions('villageList');
		alert("Kindly Select the Part State From the List");
	}
	}

function handleDistrictSuccess(data) {
	// Assigns data to result id
	
	var fieldId = 'ddDistrict';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';
		
	dwr.util.removeAllOptions(fieldId);	

	dwr.util.addOptions(fieldId, data,valueText,nameText);
	
}

function handleDistrictError() {
	// Show a popup message
	alert("No data found!");
}
	
		function resetPcForm()
		{
			document.getElementById('villagename').value="";
			document.getElementById('txtAliasLocal').value="";
			document.getElementById('txtAliasEnglish').value="";
			document.getElementById('txtNameLocal').value="";

		}

function formSubmitAdd(){
	document.getElementById('addAnotherSD').value="true";
	selectFinal();
}
function hideAll() {
	
	
	$("#pcNameEnglish_error").hide();
	$("#ECICODEExist_error").hide();
/* 	$("#ddassemblySource_error").hide();
 */	
}	

function chekcalphabets(inputtxt) {
	var status = true;
	if (inputtxt.match(/\S/)) {
		var obj =document.getElementById('pcNameEnglish');
		var retVal = false;
		var letterNumber = /^[0-9a-zA-Z\s]+$/;
		if (inputtxt.match(letterNumber))
			retVal = true;
		if (retVal == false) {
    		alert("Parliament Constituency Name (In English) Contain Invalid Characters");
			obj.value = "";
			status = false;
		}
	}
	return stautus;
}
</script>
</head>

<body onload="hideAll();">
	<div class="overlay" id="overlay1" style="display:none;"></div>
    <div class="box" id="box1">
            <a class="boxclose" id="boxclose1"></a>
			<div >
			<c:if test="${!empty param.family_msg}">
				<table>
								<tr><td rowspan="2"><center><div class= "success"></div></center></td>
								
								<td ><div  class="helpMsgHeader" style="width:275px;"><h4>Success Message</h4></div></td></tr>
								<tr><td><div id="successMsg" class= "successfont" ><center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center></div>
					          </td></tr></table>
				
			</c:if>
				
				<c:if test="${!empty family_error}">
			
				<table>
				<tr><td rowspan="2"><div class= "failur"></div></td>
				
				<td><center><div class="helpMsgHeader" style="width:275px;"><b>Failure Message</b></div></center></td></tr>
				<tr><td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="true"></c:out></div>
	          </td></tr></table>
			
				</c:if>
							 
			</div>
       </div>
       
      <div class="box" id="box">
            <a class="boxclose" id="boxclose"></a>
            <div id="validate_error" >
							<table><tr><td rowspan="2"><div class= "errorImg"></div></td>
							<td><div  class="helpMsgHeader" style="width:275px;"><h4>Error Message</h4></div></td></tr>
			                <tr><td><div class="errorfont"><spring:message htmlEscape="true"  code="error.blank.commonAlert"></spring:message></div>
			                </td></tr> </table>
                         
			</div>			
        	</div>	

  <div id="helpDiv" class="helpMessage" >
 <div class="helpheader"><h4>Help Message</h4></div>
<div id="helpMsgText" class="helptext"></div> 
</div>


 	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.MPC"></spring:message></h3>
			<ul class="listing">
				<li><a href="#" class="frmhelp">Help</a></li>
			</ul>
		</div>
		
		
		
		<form:form action="modifyParliamentConstituencytAction.htm?disturb=${disturb}" method="POST" commandName="modifyParliamentConstituencyCmd" id="form1" >
   	    	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyParliamentConstituencytAction.htm"/>" htmlEscape="true"/>
            <input type="hidden" name="stateCode" id="stateCodeinteger" value="<c:out value='${stateCode}' escapeXml='true'></c:out>" htmlEscape="true" />
            <input type="hidden" name="parliamentName" id="parliamentName" value="<c:out value='${parliamentName}' escapeXml='true'></c:out>" htmlEscape="true"/>
            <input type="hidden" name="eciUniqueCode" id="eciUniqueCode" value="<c:out value='${eciCode}' escapeXml='true'></c:out>" htmlEscape="true" />
         <table width="100%" class="tbl_no_brdr"></table>
		
		<div class="frmpnlbg">
			<div class="frmtxt">
				<div class="frmhdtitle">
					<spring:message htmlEscape="true"  code="Label.MPC"></spring:message>
				</div>
				<div id='changevillage' class="frmpnlbg">
					 <div class="frmtxt">
						<div  >
							<c:forEach var="listParliamentFormDetail" varStatus="listSubdistrictDetailsRow" items="${modifyParliamentConstituencyCmd.listParliamentFormDetail}">
								<ul class="blocklist">
									<li>
										<label><spring:message htmlEscape="true"  code="Label.PARLIAMENTCONSTITUENCYNAMEINENG"></spring:message></label><span class="errormsg">*</span><br />
                                        <label>
                                        <spring:bind path="modifyParliamentConstituencyCmd.listParliamentFormDetail[${listSubdistrictDetailsRow.index}].pcNameEnglish">							
							            <input type="text" class="frmfield" maxlength="50"  id="pcNameEnglish" path="newParliamentNameEnglish" onkeypress="validateCharKeys(event)" name="<c:out value="${status.expression}"/>"  value="<c:out value="${status.value}" escapeXml="true"></c:out>"   id="subdistrictname" cssClass="frmfield"  onblur="constituencynameVal(this.value,'P');chekcalphabets(this.value);" htmlEscape="true" />							
				    	                <div id="UniquePCError" style="color: red;"></div>
				    	                <span class="errormsg" id="pcNameEnglish_error"><spring:message htmlEscape="true" 
										code="Error.PARLIAMENTENTCONENGLISH"></spring:message></span><span><form:errors htmlEscape="true" 
										path="newParliamentNameEnglish" class="errormsg"></form:errors>
										</span>							
				    	                </spring:bind>
				    	                <spring:bind path="modifyParliamentConstituencyCmd.listParliamentFormDetail[${listSubdistrictDetailsRow.index}].pcCode">							
						  				<input type="hidden" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>"/>
									  	</spring:bind>
										</label><br></br>									
									</li>
									<li>
										<label><spring:message htmlEscape="true"  code="Label.PARLIAMENTCONSTITUENCYNAMEINLOCAL"></spring:message></label><br />
                                         <label>
                                         <spring:bind path="modifyParliamentConstituencyCmd.listParliamentFormDetail[${listSubdistrictDetailsRow.index}].pcNameLocal">	
                                         <input type="text" class="frmfield" id="pcNameLocal"  maxlength="50"   onblur="validateSpecialCharactersforPCACValues(this.value,1);" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" id="subdistrictname" cssClass="frmfield" onfocus="show_msg('subdistrictname')" onblur="subdistrictName()" htmlEscape="true" />							
                                         </spring:bind>
                                         </label><br></br>
                                         <div class="errormsg"></div>
									</li>
									<li>
										<label><spring:message htmlEscape="true"  code="Label.ECICODE"></spring:message></label><br />
                                          <label>
                                           <spring:bind path="modifyParliamentConstituencyCmd.listParliamentFormDetail[${listSubdistrictDetailsRow.index}].eciCode">							
								            <input type="text" class="frmfield" id="ecicode" onkeypress="return validateNumericKeys(event)" maxlength="50" onfocus="show_msg('txtAliasEnglish')"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" />							
                                           						
					    	                </spring:bind>
                                          </label>
                                       	<form:errors path="eciCode" class="errormsg" htmlEscape="true"></form:errors>
									</li>
								</ul>
							</c:forEach>
							<table width="100%" class="tbl_no_brdr"></table>
						</div>
					</div>
					 <div class="btnpnl">
					 	
							<input type="button" class="btn" id="Submit" name="Submit"
							value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>"
							onclick="selectParliamentConstituency();"/>  <!-- added by kirandeep on 18/06/2014 -->
							
                            <input
							type="button" name="Submit3" class="btn"
							value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>"                    
							id="btnClear" onclick="parliamentresetForm()"/>
                                 
                                  <input type="button" name="Cancel"
							class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
				  			  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'" /> 
					  </div>
				</div>
			</div>
		</div>
	  </form:form>
	</div>
<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>

</body>
</html>