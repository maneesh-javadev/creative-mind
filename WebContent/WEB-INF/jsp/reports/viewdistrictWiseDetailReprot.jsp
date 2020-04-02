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


<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/plugins/datatables/dataTables.bootstrap.css">
 <script src="resource/dashboard-resource/plugins/datatables/jquery.dataTables.min.js"></script>
 <script src="resource/dashboard-resource/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" language="Javascript">

$(document).ready(function() {

	$('#detailReport').dataTable({
	        "lengthMenu": [[ 50,100, -1], [ 50,100, "All"]],
	         "scrollX": true
	    });
});	


	dwr.engine.setActiveReverseAjax(true);
	
	/*Function called on the captcha getreport button*/
function getData() 
{
		var inSelectState	=	document.getElementById("stateList").value;
		var inSelectDist	=	document.getElementById("districtList").value;
		/* State not selected */
		if(inSelectState==null ||inSelectState==""){
			document.getElementById("errProSelect").innerHTML = "<spring:message code="Error.STATE"/>"; 
		    document.getElementById("errProSelect").focus();
		    return false;
			}else{
			document.getElementById("errProSelect").innerHTML= "";
		}
		if(inSelectDist==null ||inSelectDist==""||inSelectDist=="0"){
			document.getElementById("errProSelectDist").innerHTML = "<spring:message code="error.select.DISTRICTFRMLIST"/>"; 
		    document.getElementById("errProSelectDist").focus();
		    return false;
			}else{
			document.getElementById("errProSelectDist").innerHTML= "";
		}
		
		var errorCaptcha = document.getElementById('errorCaptcha');
		errorCaptcha.innerHTML = "";
		var sessionId = document.getElementById('sessionId').value;
		var capchaImgVal = document.getElementById('captchaAnswer').value;
		/* Empty Captcha Check */
		if(capchaImgVal==null || capchaImgVal==""){
				document.getElementById("errEmptyCaptcha").innerHTML = "<spring:message code="Error.EmptyCaptcha"/>"; 
			    document.getElementById("errEmptyCaptcha").focus();
			    return false;
			}else{
				document.getElementById("errEmptyCaptcha").innerHTML= "";
		}
						
		document.getElementById('form1').method = "post";
		document.getElementById('form1').action = "districtWiseDetailReport.do?<csrf:token uri='districtWiseDetailReport.do'/>";
		document.getElementById('form1').submit();
		return true;
}
/*DWR code for the retreival of the District List in the combo box on state selection*/
function getDistrictList(stateCode){   
	//alert("Inside getDistrict Method");
	lgdDwrDistrictService.getDistrictList(stateCode, {
	callback : handleDistrictSuccess,
	errorHandler : handleDistrictError
	});
}
//data contains the returned value
function handleDistrictSuccess(data) {
	// Assigns data to result id	
	var fieldId = 'districtList';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById(fieldId);
	st.add(new Option('Select District', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
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

/*print the Report */
function CallPrint()
{ 
	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';
	var printContent = document.getElementById("ash"); 
	//document.getElementById('btn1').style.visibility = 'hidden';
	var Win4Print = window.open('','','left=0,top=0,width=500,height=500,top=301,left=365,resizable=1,status=0,toolbar=0'); 
	//alert("contect"+printContent.innerHTML);
	Win4Print.document.write(printContent.innerHTML); 
	Win4Print.document.close(); 
	Win4Print.focus(); 
	Win4Print.print(); 
	Win4Print.close(); 
	document.getElementById('footer').style.display = 'none';
	document.getElementById('footer').style.visibility = 'hidden';
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
              <h3 class="box-title"><spring:message code="Label.distwisedetailreport" text="District wise Detail Report" htmlEscape="true"></spring:message></h3>
             <c:if test="${dataExist}">
				<a id="showprint" class="pull-right" href="#"><img src='<%=contextPath%>/images/printer_icon.png' border="0" onclick="CallPrint()" onmouseover="" alt="Print" /></a>
			</c:if>
          </div><!-- /.box-header -->
                <!-- form start -->
                
                <form:form class="form-horizontal" commandName="districtWiseDetail" id='form1' action="districtWiseDetailReport.do">
				<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="districtWiseDetailReport.do"/>" />
					<div id="box">
						<a id="boxclose"></a>
					</div>
					
					<div id="cat">
					  <c:if test="${empty resultList}">
					 		 <div class="form-group">
									<label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message></label>
									 <div class="col-sm-6">
										<form:select path="selectStateCode" class="form-control" id="stateList" onchange="getDistrictList(this.value);setStateName(this);" onfocus="validateOnFocus('stateList');helpMessage(this,'ddSourceState_msg');"
											onblur="vlidateOnblur('stateList','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('stateList')">
											<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
											<form:options items="${stateList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"/>
										</form:select>
										<div id="errProSelect" class="mandatory"></div>
								 	</div>
								</div>
								
								 <div class="form-group">
									<label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message></label>
									 <div class="col-sm-6">
										<form:select path="selectDistrictCode" class="form-control" id="districtList" onfocus="validateOnFocus('districtList');helpMessage(this,'ddSourceDistrict_msg');"
											onblur="vlidateOnblur('districtList','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('districtList')"><br />
										</form:select>
										<div id="errProSelectDist" class="mandatory"></div>
								 	</div>
								</div>
							
							    <%@ include file="../common/captcha_integration.jspf"%>
								<div class="mandatory" style="margin-left: 300px;"> <c:if test="${ captchaSuccess2 == false }"><spring:message code="captcha.errorMessage" htmlEscape="true" /></c:if>
								</div>
						
						<div class="box-footer">
			             <div class="col-sm-offset-2 col-sm-10">
			              <div class="pull-right">
							<button type="button" onclick="getData();" class="btn btn-success"><i class="fa fa-floppy-o"></i> <spring:message code="Label.GETREPORT" htmlEscape="true"></spring:message></button>
							<button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
						 </div>
						</div>
				      </div>
		        <input type="hidden" id="sessionId" value='<%=sessionId%>'></input>
	          </c:if>
					   
           	<c:if test="${dataExist}">
           		<div id="ash">
           		<div class="box-body" >
					<h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">
	   					<c:out value="${message}" escapeXml="true"></c:out>
	   				</h6>				
					<br/>
					

				
						  <table  width="90%">
						  <tr>
							<td class="bg-danger col-sm-1"  ></td>
							<td style="padding-left:5px">Block mapped with district not matched</td>
							
						</tr>
						<tr>
							
							<td align="right">*</td>
							<td>This is not applicable for ASSAM,JAMMU AND KASHMIR State because there are some blocks which falls under multiple districts</td>
						</tr>

					  </table>
 				<br/>
				
				<table class="table  table-bordered dataTable"  id="detailReport" >
				  <thead>
					<tr>
						<th width="4%"><spring:message code="Label.SNO"/></th>
						<th width="5%"><spring:message code="Label.DISTRICTCODE" /></th>
						<th width="9%"><spring:message code="Label.DISTRICTNAMEINENGLISH"/></th>
						<th width="5%"><spring:message code="Label.SUBDISTRICTCODE"/></th>
						<th width="9%"><spring:message code="Label.SUBDISTRICTNAMEENGLISH"/></th>
						<th width="5%"><spring:message code="Label.VILLAGECODE"/></th>
						<th width="9%"><spring:message code="Label.VILLAGENAMEINENGLISH"/></th>
						<th width="5%"><spring:message code="label.BLOCKPANCHAYATCODE" text="Block Panchayat Code"/></th>
						<th width="9%"><spring:message code="Label.BLOCKPANCHAYATNAME" text="Block Panchayat Name(In English)"/></th>
						<th width="5%"><spring:message code="label.GRAMPANCHAYATCODE" text="Gram Panchayat Code"/></th>
						<th width="9%"><spring:message code="Label.GRAMPANCHAYATNAME" text="Gram Panchayat Name(In English)"/></th>
						<th width="5%"><spring:message htmlEscape="true" code="Label.BLOCKCODE"/></th>
						<th width="9%"><spring:message htmlEscape="true" code="Label.BLOCKNAMEINENGLISH"/></th>
						<th width="5%"><spring:message code="Label.DISTRICTCODEOFBLOCK" /></th>
						<th width="9%"><spring:message code="Label.DISTRICTNAMEINENGLISHOFBLOCK"/></th>	
					</tr>
				</thead>
				<tbody>
					<c:forEach var="obj" items="${resultList}" varStatus="sno">
						<c:set var="trStatus" value="bg-test" />
							<c:if test="${obj.matchDistrict eq false}">
								<c:set var="trStatus" value="bg-danger"/>
							</c:if>
						<tr class="${trStatus}">
							
							
							<td width="4%" align="center"><esapi:encodeForHTMLAttribute>${sno.count}</esapi:encodeForHTMLAttribute></td>
							<td width="5%" align="center"><esapi:encodeForHTMLAttribute>${obj.districtCode}</esapi:encodeForHTMLAttribute></td>
							<td width="9%"><esapi:encodeForHTMLAttribute>${obj.districtNameEnglish}</esapi:encodeForHTMLAttribute></td>
							<td width="5%" align="center"><esapi:encodeForHTMLAttribute>${obj.subDistrictCode}</esapi:encodeForHTMLAttribute></td>
							<td width="9%"><esapi:encodeForHTMLAttribute>${obj.subDistrictNameEnglish}</esapi:encodeForHTMLAttribute></td>
							<td width="5%" align="center"><esapi:encodeForHTMLAttribute>${obj.villageCode}</esapi:encodeForHTMLAttribute></td>
							<td width="9%"><esapi:encodeForHTMLAttribute>${obj.villageNameEnglish}</esapi:encodeForHTMLAttribute></td>
							<td width="5%" align="center"><esapi:encodeForHTMLAttribute>${obj.bpCode}</esapi:encodeForHTMLAttribute></td>
							<td width="9%"><esapi:encodeForHTMLAttribute>${obj.bpNameEnglish}</esapi:encodeForHTMLAttribute></td>
							<td width="5%" align="center"><esapi:encodeForHTMLAttribute>${obj.gpCode}</esapi:encodeForHTMLAttribute></td>
							<td width="9%"><esapi:encodeForHTMLAttribute>${obj.gpNameEnglish}</esapi:encodeForHTMLAttribute></td>
							<td width="5%" align="center"><esapi:encodeForHTMLAttribute>${obj.blockCode}</esapi:encodeForHTMLAttribute></td>
							<td width="9%"><esapi:encodeForHTMLAttribute>${obj.blockNameEnglish}</esapi:encodeForHTMLAttribute></td>
							<td width="5%" align="center"><esapi:encodeForHTMLAttribute>${obj.districtCodeofBlock}</esapi:encodeForHTMLAttribute></td>
							<td width="9%"><esapi:encodeForHTMLAttribute>${obj.districtNameofBlock}</esapi:encodeForHTMLAttribute></td>
						</tr>
					</c:forEach>
			</tbody>
		</table>			
	
		<div style="text-align: center; list-style: none;"> <!-- Inline style only for Print purpose -->				
			<label><spring:message code="Label.URL" htmlEscape="true"></spring:message> <%=request.getScheme() + "://" + request.getServerName() + request.getContextPath()%></label>
			</br><%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%>
				<label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message> <%=df.format(new java.util.Date())%></label>
				</br><label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
		</div>
		<div id="footer"></div>			
	</div>
	</div>
	<div class="box-footer">
	 <div class="col-sm-offset-2 col-sm-10">
       <div class="pull-right">
			<input type="submit" class="btn btn-primary" value="<spring:message htmlEscape="true"  code="Button.BACK"/>" onclick="javascript:location.href='districtWiseDetailReport.do?<csrf:token uri='districtWiseDetailReport.do'/>';" /> 
			<button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
		</div>
	 </div>
  </div>	
</c:if>
</div>
</form:form>
<c:if test="${dataExist==false}">
	<div class="mandatory"><spring:message htmlEscape="true"  code="Label.NORECORDFOUND" text="No Record Found on your selection"/></div>
</c:if>
</div>
</section>
</div>
</div>
</section>

</body>
</html>