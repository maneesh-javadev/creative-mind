<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../common/taglib_includes.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
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

<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrParlimentService.js'></script>
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

function resetAcForm()
{
	document.getElementById('acnameEnglish').value="";
	document.getElementById('txtNameLocal').value="";
	document.getElementById('ECICODE').value="";

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
function selectDistrict(id,name)
{     
	
	if (id.match('PART')=='PART')
	{
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
function hideAll() {
	$("#acnameEnglish_error").hide();
	$("#ECICODEExist_error").hide();

}	

function formSubmitAdd(){
	document.getElementById('addAnotherSD').value="true";
	selectFinal();
}
function chekcalphabets(inputtxt) {
	var status = true;
	if (inputtxt.match(/\S/)) {
		var obj =document.getElementById('acnameEnglish');
		var retVal = false;
		var letterNumber = /^[0-9a-zA-Z\s]+$/;
		if (inputtxt.match(letterNumber))
			retVal = true;
		if (retVal == false) {
    		alert("Assembly Constituency Name (In English) Contain Invalid Characters");
			obj.value = "";
			status = false;
		}
	}
	return stautus;
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
			                <tr><td><div class="errorfont"><spring:message htmlEscape="true" code="error.blank.commonAlert"></spring:message></div>
			                </td></tr> </table>
                         
			</div>			
        	</div>	

  <div id="helpDiv" class="helpMessage" >
 <div class="helpheader"><h4>Help Message</h4></div>
<div id="helpMsgText" class="helptext"></div> 
</div>


 		<div id="frmcontent">
			<div class="frmhd">
			<h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.ASSCONSTITUENCYDETAIL"></spring:message></h3>
			<ul class="listing">
				<li><a href="#" class="frmhelp">Help</a></li>
			</ul>
		</div>
		<form:form action="modifyAssemblyConstituencytAction.htm?disturb=${disturb}" method="POST" commandName="modifyassemblyConstituencyCmd" id="form1">
	       <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyAssemblyConstituencytAction.htm"/>" htmlEscape="true"/>
           <input type="hidden" name="stateCode" id="stateCodeinteger" value="<c:out value='${stateCode}' escapeXml='true'></c:out>" htmlEscape="true"/>
           <input type="hidden" name="parliamentName" id="parliamentName" value="<c:out value='${parliamentName}' escapeXml='true'></c:out>" htmlEscape="true"/>
           <input type="hidden" name="eciUniqueCode" id="eciUniqueCode" value="<c:out value='${eciCode}' escapeXml='true'></c:out>" htmlEscape="true"/>
           <input type="hidden" name="parliamentCCode" id="parliamentCCode" value="<c:out value='${parliamentCCode}' escapeXml='true'></c:out>" htmlEscape="true"/>
           
           <table width="100%" class="tbl_no_brdr"></table>
			<div class="frmpnlbg">
				<div class="frmtxt">
					<div class="frmhdtitle">
						<spring:message htmlEscape="true"  code="Label.ASSCONSTITUENCYDETAIL"></spring:message>
					</div>
					<div id='changevillage' class="frmpnlbg">
						  <div class="frmtxt">
						  	<div  >
								 <c:forEach var="listAssemblyFormDetail" varStatus="listSubdistrictDetailsRow" items="${modifyassemblyConstituencyCmd.listAssemblyFormDetail}">
		                          	<ul class="blocklist">
		                          		<li>
		                          			<spring:message htmlEscape="true"
												code="Label.ASSEMBLYCONSTITUENCYNAMEINENG"></spring:message></label><span
												class="errormsg">*</span><br /> <label> <spring:bind
													path="modifyassemblyConstituencyCmd.listAssemblyFormDetail[${listSubdistrictDetailsRow.index}].acNameEnglish">
													<input type="text" class="frmfield" id="acnameEnglish"
														onkeypress="validateCharKeys(event)"
														path="newAssemblyNameEnglish" maxlength="50"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"></c:out>"
														cssClass="frmfield"
														onblur="constituencynameVal(this.value,'A');chekcalphabets(this.value);"
														htmlEscape="true" />
													<div id="UniqueACError" style="color: red;"></div>
													<span class="errormsg" id="acnameEnglish_error"><spring:message
															htmlEscape="true" code="Error.PARLIAMENTENGISHNAME"></spring:message></span>
													<span><form:errors htmlEscape="true"
															path="newAssemblyNameEnglish" class="errormsg"></form:errors>
													</span>
												</spring:bind> <spring:bind
													path="modifyassemblyConstituencyCmd.listAssemblyFormDetail[${listSubdistrictDetailsRow.index}].acCode">
													<input type="hidden" class="frmfield"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
												</spring:bind> <spring:bind
													path="modifyassemblyConstituencyCmd.listAssemblyFormDetail[${listSubdistrictDetailsRow.index}].pcCode">
													<input type="hidden" class="frmfield"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
												</spring:bind>

												</label>
												<div class="errormsg"></div>
										</li>
										<li>
											<label><spring:message htmlEscape="true"  code="Label.ASSEMBLYCONSTITUENCYNAMEINLOCAL"></spring:message></label><br />
	                                        <label>
	                                         <spring:bind path="modifyassemblyConstituencyCmd.listAssemblyFormDetail[${listSubdistrictDetailsRow.index}].acNameLocal">							
								            <input type="text" class="frmfield" id="txtNameLocal"  maxlength="50"  onblur="validateSpecialCharactersforPCACValues(this.value,2);" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" htmlEscape="true" />						
					    	               </spring:bind>
		                                        </label>
	                                        <div class="errormsg"></div>
										</li>
										<li>
											<label><spring:message htmlEscape="true" code="Label.ECICODE"></spring:message></label><br />
	                                        <label>
	                                         <spring:bind path="modifyassemblyConstituencyCmd.listAssemblyFormDetail[${listSubdistrictDetailsRow.index}].eciCode">							
								            <input type="text" class="frmfield" id="ECICODE" onkeypress="return validateNumericKeys(event)" maxlength="50" onfocus="show_msg('txtAliasEnglish')" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" htmlEscape="true" />					
					    	                </spring:bind>
		                                        </label>
		                                          <!--  	<div id="ECICODEExist_error" style="color: red;"></div>	 -->		
		                                       <div class="errormsg" id="ECICODEExist_error">
											<spring:message htmlEscape="true" code="Error.ECICODEExist"></spring:message>
									       </div>
		                                       
		                                       <form:errors path="eciCode" class="errormsg" htmlEscape="true"></form:errors> 
										</li>
		                          	</ul>
								</c:forEach>
								<table width="100%" class="tbl_no_brdr"></table>
							</div>
						</div>
						<div class="btnpnl">
							<ul class="listing">
								<li>
									<label>
									<input htmlEscape="true" type="button" class="btn" id="Submit123" name="Submit"
									value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>"
									onclick="selectAssemblyConstituency();"/>  <!-- added by kirandeep on 18/06/2014 -->
									</label>
	                                     <label>
	                                   <input htmlEscape="true"
									type="button" name="Submit3" class="btn"
									value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>"
									id="btnClear" onclick="resetAcForm();" />
	                                   </label>                                      
	                                   <label> <input type="button" name="Cancel" htmlEscape="true"
							          class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
						              id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'" /> </label>
								</li>
							</ul>
				      </div>
					</div>
				</div>
			</div>
		</form:form>
	</div>
	<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
	</body>
</html>