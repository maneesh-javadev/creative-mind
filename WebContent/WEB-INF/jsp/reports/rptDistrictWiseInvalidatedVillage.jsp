<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>

<%-- <%
	java.text.DateFormat df = new java.text.SimpleDateFormat(
			"dd/MM/yyyy");
%> --%>
<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>E-Panchayat</title>
<script src="js/shiftsubdistrict.js"></script>

<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>

<script type="text/javascript" language="Javascript">
	dwr.engine.setActiveReverseAjax(true);
	
	/*Function called on the captcha getreport button*/
function getData() 
{
		var inSelectState	=	document.getElementById("ddSourceState").value;
		var inSelectDist	=	document.getElementById("ddSourceDistrict").value;
		/* State not selected */
		if(inSelectState==null ||inSelectState=="")
		{
			document.getElementById("errProSelect").innerHTML = "<spring:message code="Error.STATE"/>"; 
		    document.getElementById("errProSelect").focus();
		    return false;
		}else
		{
			document.getElementById("errProSelect").innerHTML= "";
		}
		if(inSelectDist==null ||inSelectDist==""||inSelectDist=="0")
		{
			document.getElementById("errProSelectDist").innerHTML = "<spring:message code="error.select.DISTRICTFRMLIST"/>"; 
		    document.getElementById("errProSelectDist").focus();
		    return false;
		}else
		{
			document.getElementById("errProSelectDist").innerHTML= "";
		}
		var	statCode	=	document.getElementById("ddSourceState").value;
		document.getElementById("stateCode").value	=	statCode;
		var	villCode	=	document.getElementById("ddSourceDistrict").value;
		document.getElementById("distCode").value	=	villCode;
		var errorCaptcha = document.getElementById('errorCaptcha');
		errorCaptcha.innerHTML = "";
		var sessionId = document.getElementById('sessionId').value;
		var capchaImgVal = document.getElementById('captchaAnswer').value;
		/* Empty Captcha Check */
		if(capchaImgVal==null || capchaImgVal=="")
			{
				document.getElementById("errEmptyCaptcha").innerHTML = "<spring:message code="Error.EmptyCaptcha"/>"; 
			    document.getElementById("errEmptyCaptcha").focus();
			    return false;
			}else
			{
				document.getElementById("errEmptyCaptcha").innerHTML= "";
			}
						
		document.getElementById('form1').method = "post";
		document.getElementById('form1').action = "rptDistrictWiseInvalidatedVillageDetail.do?<csrf:token uri='rptDistrictWiseInvalidatedVillageDetail.do'/>";
		document.getElementById('form1').submit(); 
		return true;
}
	/*DWR code for the retreival of the District List in the combo box on state selection*/
	
function getDistrict(stateObj)
{   
	//alert("Inside getDistrict Method");
	
	var stateName = stateObj.options[stateObj.selectedIndex].innerHTML;
	var stateCode=0;
	var stateNameObj=document.getElementById("stateName");
	if(stateNameObj!=null)
		{
		stateCode=parseInt(stateObj.value)
		stateNameObj.value=stateName;
		}
	
	lgdDwrDistrictService.getDistrictList(stateCode, {
	callback : handleDistrictSuccess,
	errorHandler : handleDistrictError
	});

}
	
function setDistrictName(districtObj)
{
	var districtName = districtObj.options[districtObj.selectedIndex].innerHTML;
	var districtNameObj=document.getElementById("districtName");
	if(districtNameObj!=null)
		{
		districtNameObj.value=districtName;
		}
}

//data contains the returned value
function handleDistrictSuccess(data) {
	// Assigns data to result id	
	var fieldId = 'ddSourceDistrict';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('ddSourceDistrict');
	st.add(new Option('Select district', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
	//document.getElementById("displayDistrict").style.display='block';

}

function handleDistrictError() {
	// Show a popup message
	test="${fn:length(districtList)}";
	alert("No data found!");
}

/*DWR code ends*/
function noenter(e) {
    e = e || window.event;
    var key = e.keyCode || e.charCode;
    return key !== 13; 
}

function PrintDiv() {

	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';
	var printContent = document.getElementById("printable2");
	// document.getElementById('btn1').style.visibility = 'hidden';
	var Win4Print = window.open('', '', 'left=0,top=0,width=500,height=500,top=301,left=365,resizable=1,status=0,toolbar=0');
	// alert("contect"+printContent.innerHTML);
	Win4Print.document.write(printContent.innerHTML);
	Win4Print.document.close();
	Win4Print.focus();
	Win4Print.print();
	Win4Print.close();
	document.getElementById('footer').style.display = 'none';
	document.getElementById('footer').style.visibility = 'hidden';
	
	return false;
	

}

$(document).ready(function() {
	document.getElementById("ddSourceState").selectedIndex = 0;
	
});	
</script>
</head>

<body>

<div id="frmcontent">
	<div class="frmhd">
		<h3 class="subtitle"><spring:message code="Label.rptdistrictWiseInvalidatedVillage" htmlEscape="true"></spring:message></h3>			
	</div>
		
	<div class="clear"></div>
	
	<div class="frmpnlbrdr">
		<form:form commandName="districtInvalidate" id='form1' action="rptDistrictWiseInvalidatedVillageDetail.do">
        <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='rptDistrictWiseInvalidatedVillageDetail.do'/>" />
			<!-- Hidden fields set to pass on as a parameter for the village list retreival -->
			<input type="hidden" value="" name="stateCode" id="stateCode" />
			<input type="hidden" value="" name="distCode" id="distCode" />
			<input type="hidden" id="stateName" name="stateName" />
			<input type="hidden" id="districtName" name="districtName" />
			<div class="box" id="box">
				<a class="boxclose" id="boxclose"></a>
			</div>

	<c:if test="${empty invalidatetList}">
			<div id="cat">
				<div class="frmpnlbg">
					<div class="frmtxt">
						<div class="search_criteria">
							<div class="block">
								<ul class="blocklist">
									<li>
										<label for="ddSourceState"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message></label>
											<form:select path="state_name_english" class="combofield"
											style="width: 175px" id="ddSourceState"
											onchange="getDistrict(this);"
											onfocus="validateOnFocus('ddSourceState');helpMessage(this,'ddSourceState_msg');"
											onblur="vlidateOnblur('ddSourceState','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddSourceState')">
											<form:option value="">
												<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
											</form:option>
											<form:options items="${statelist}"
												itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
										</form:select>
										<div id="errProSelect" class="errormsg"></div>
									</li>
									<li>
										<label for="ddSourceDistrict"><spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message></label>
											<form:select path="districtPName" class="combofield"
											style="width: 175px" id="ddSourceDistrict"
											onchange="setDistrictName(this);"
											onfocus="validateOnFocus('ddSourceDistrict');helpMessage(this,'ddSourceDistrict_msg');"
											onblur="vlidateOnblur('ddSourceDistrict','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddSourceDistrict')">
											<form:option value="">
												<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
											</form:option>
											<form:options items="${districtList}"
												itemValue="districtPK.districtCode"
												itemLabel="districtNameEnglish"></form:options>											
										</form:select>
										<div id="errProSelectDist" class="errormsg"></div>
									</li>
								</ul>
							</div>
							<div class="block">
								<%@ include	file="../common/captcha_integration.jspf"%>
								<div class="errormsg">
										<c:if test="${ captchaSuccess2 == false }">
											<spring:message code="captcha.errorMessage" htmlEscape="true" />
										</c:if>
									</div>
									<c:if test = "${flag1 eq 1}"><span id="required" class="errormsg"><spring:message code="Error.noresult" htmlEscape="true"></spring:message></span></c:if>
								<div clas="buttons">
									<input type="button" value="<spring:message code="Label.GETREPORT" htmlEscape="true"></spring:message>" onclick="getData();" />
									<input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
										onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';" />
								</div>
							</div>
							<div class="clear"></div>
						</div>
							
					</div>
				</div>
			</div>
			</c:if>
							
			<input type="hidden" id="sessionId" value='<%=sessionId%>'></input>

		</form:form>
	</div>
	</div>
</body>
</html>