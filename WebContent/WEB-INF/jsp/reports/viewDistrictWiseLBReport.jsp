<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../homebody/commanInclude.jsp"%>

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
			document.getElementById("errProSelect").innerHTML = "<font color='red'><spring:message code="Error.STATE"/></font>"; 
		    document.getElementById("errProSelect").focus();
		    return false;
		}else
		{
			document.getElementById("errProSelect").innerHTML= "";
		}
		if(inSelectDist==null ||inSelectDist==""||inSelectDist=="0")
		{
			document.getElementById("errProSelectDist").innerHTML = "<font color='red'><spring:message code="error.select.DISTRICTFRMLIST"/></font>"; 
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
				document.getElementById("errEmptyCaptcha").innerHTML = "<font color='red'><spring:message code="Error.EmptyCaptcha"/></font>"; 
			    document.getElementById("errEmptyCaptcha").focus();
			    return false;
			}else
			{
				document.getElementById("errEmptyCaptcha").innerHTML= "";
			}
						
		document.getElementById('form1').method = "post";
		document.getElementById('form1').action = "districtWiseLocalBodyReport.do?<csrf:token uri='districtWiseLocalBodyReport.do'/>";
		document.getElementById('form1').submit();
		return true;
}
	/*DWR code for the retreival of the District List in the combo box on state selection*/
	
function getDistrict(stateCode)
{   
	//alert("Inside getDistrict Method");
	lgdDwrDistrictService.getDistrictList(stateCode, {
	callback : handleDistrictSuccess,
	errorHandler : handleDistrictError
	});

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

<section class="content">
<!-- Main row -->
 <div class="row"  id="frmcontent">
     <!-- main col -->
	<div class="container">
	<section class="col-lg-12 prebox">
     	<div class="box">
            <div class="box-header with-border">
              <h3 class="box-title"><spring:message code="Label.distwiselbreport" htmlEscape="true"></spring:message></h3>
           </div><!-- /.box-header -->
                <!-- form start -->
            <form:form class="form-horizontal" commandName="consolidateReport" id='form1' action="districtWiseLocalBodyReport.do">
			<input type="hidden" value="" name="stateCode" id="stateCode" />
			<input type="hidden" value="" name="distCode" id="distCode" />
			<div id="box">
				<a id="boxclose"></a>
			</div>
			<c:if test="${empty reportList}">
				<div id="cat">
					<div class="box-header subheading"><spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message></div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message><span class="mandatory">*</span></label>
							<div class="col-sm-6">
							<form:select path="state_name_english" class="form-control" id="ddSourceState" onchange="getDistrict(this.value);" onfocus="validateOnFocus('ddSourceState');helpMessage(this,'ddSourceState_msg');"
								onblur="vlidateOnblur('ddSourceState','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('ddSourceState')">
								<form:option value="">
									<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
								</form:option>
								<form:options items="${statelist}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
							</form:select>
							<div id="errProSelect" class="errormsg"></div>
						  </div>
						</div>
						
							<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message><span class="mandatory">*</span></label>
							<div class="col-sm-6">
							<form:select path="districtPName" class="form-control" id="ddSourceDistrict" onfocus="validateOnFocus('ddSourceDistrict');helpMessage(this,'ddSourceDistrict_msg');"
								onblur="vlidateOnblur('ddSourceDistrict','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('ddSourceDistrict')">
								<form:option value="">
									<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
								</form:option>
								<form:options items="${districtList}" itemValue="districtPK.districtCode" itemLabel="districtNameEnglish"></form:options>											
							</form:select>
							<div id="errProSelectDist" class="errormsg"></div>
						  </div>
						</div>
						
						

								 	
							
						
						
						<div>
						<div class="form-group">
		  <label for="captchaAnswer" class="col-sm-4 control-label"></label>
		     <div class="col-sm-6">
		         <img src="captchaImage" alt="Captcha Image" id="captchaImageId" border="0"/>
		      </div>
		</div>
		                    
	<div class="form-group">
		<label for="captchaAnswer" class="col-sm-4 control-label"><spring:message code="captcha.message"	htmlEscape="true" /><span class="mandatory">*</span></label>
		      <div class="col-sm-6">
		           <form:input path="captchaAnswer" name="captchaAnswer"  autocomplete="off" onkeypress="return enter(event)" maxlength="5" style="width:250px"/> <br>
             		 <a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>
             		<form:errors path="captchaAnswer" cssStyle="color:red"/>
				<c:if test="${not empty captchaSuccess2 and not captchaSuccess2}">
				<br/>
				<span class="mandatory"><strong><spring:message code="captcha.errorMessage"/></strong></span>
			</c:if>
				
				<div id="errEmptyCaptcha" class="errormsg" ></div>
				<div id="errorCaptcha" class="errormsg"></div>	
				<div id="errormsg" style="color: red;"> <b>${errormsg}</b> </div>
		     </div>
	 </div> 
	 </div>
								<%-- <div><c:if test="${ captchaSuccess2 == false }"><spring:message code="captcha.errorMessage" htmlEscape="true" /></c:if></div> --%>
						
						
						
						
						
						<div class="box-footer">
			             <div class="col-sm-offset-2 col-sm-10">
			              <div class="pull-right">
							<button type="button" name="Submit" onclick="getData();" class="btn btn-success"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message></button>
							<button type="button"  name="Submit3" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';" class="btn btn-danger" ><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
						 </div>
						</div>
				      </div>
		           </div>
	          </c:if>
			<input type="hidden" id="sessionId" value='<%=sessionId%>'></input>
          	</form:form>
			</div>
		</section>
		</div>
	</div>
</section>

</body>
</html>