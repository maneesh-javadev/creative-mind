<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../homebody/commanInclude.jsp"%>
<%-- <% java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy"); %> --%>
<%!String contextPath;
	String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>E-Panchayat</title>
<script src="js/shiftsubdistrict.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/plugins/datatables/dataTables.bootstrap.css">
 <script src="resource/dashboard-resource/plugins/datatables/jquery.dataTables.min.js"></script>
 <script src="resource/dashboard-resource/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>

<script type="text/javascript" language="Javascript">
	dwr.engine.setActiveReverseAjax(true);
	
/*DWR code for the retreival of the District List in the combo box on state selection*/
$(document).ready(function() {

	$('#example').dataTable({
	        "lengthMenu": [[ 50,100, -1], [ 50,100, "All"]],
	         "scrollX": true
	    });
});
function getDistrict(stateCode)
{   
	lgdDwrDistrictService.getDistrictList(stateCode, {
	callback : handleDistrictSuccess,
	errorHandler : handleDistrictError
	});
}

//data contains the returned value
function handleDistrictSuccess(data) {
	var fieldId = 'ddSourceDistrict';
	dwr.util.removeAllOptions(fieldId);
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';
	var st = document.getElementById(fieldId);
	st.add(new Option("<spring:message code="Label.SEL"/>", ''));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
	
	if('${districtId}' != null)
		document.getElementById('ddSourceDistrict').value = '<c:out value="${districtId}" escapeXml="true"></c:out>';
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



function getData() {
	/* modified by pooja on 03-07-2015 to display error message in right place */
	document.getElementById("errSelectState").innerHTML = "";
	document.getElementById("errSelectDistrict").innerHTML = "";
	document.getElementById("errSelectCaptcha").innerHTML = "";
	var stateobj = document.getElementById("ddSourceState");
	var disttobj = document.getElementById("ddSourceDistrict");
	
	var inSelectState =	stateobj.value;
	if(inSelectState == null || inSelectState == ""){
		document.getElementById("errSelectState").innerHTML = "<font color='red'><br><b><spring:message code="Error.STATE"/></b></font>"; 
		return false;
	}
	
	var inSelectDist = disttobj.value;
	if(inSelectDist == null || inSelectDist == ""){
		document.getElementById("errSelectDistrict").innerHTML = "<font color='red'><br><b><spring:message code="error.select.DISTRICTFRMLIST"/></b></font>";  
		return false;
	}
	
	var capchaImgVal = document.getElementById('captchaAnswer').value;
	/* Empty Captcha Check */
	if(capchaImgVal == null || capchaImgVal == ""){
		document.getElementById("errSelectCaptcha").innerHTML = "<font color='red'><br><b><spring:message code="Error.EmptyCaptcha"/></b></font>"; 
	    return false;
	}
	
	document.getElementById('form1').statename.value = stateobj.options[stateobj.selectedIndex].text;
	document.getElementById('form1').disttname.value = disttobj.options[disttobj.selectedIndex].text;
	document.getElementById('form1').method = "post";
	document.getElementById('form1').action = "rptConsolidateBlockGramPanchayat.do?<csrf:token uri='rptConsolidateBlockGramPanchayat.do'/>";
	document.getElementById('form1').submit();
	return true;
}

function PrintDiv() {

	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';	
	var printContent = document.getElementById("printble");
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
</script>
</head>

<body>

<section class="content">

 <!-- Main row -->
 <div class="row" id="frmcontent">
     <!-- main col -->
	<div class="container">
	<section class="col-lg-12 prebox">
     	<div class="box">
            <div class="box-header with-border">
              <h3 class="box-title"><spring:message code="form.rptConsolidateBlockGramPanchayat" htmlEscape="true"></spring:message></h3>
              <c:if test="${empty reportList}"></c:if>
				<div class="col-sm-offset-2 col-sm-10">
		            <div class="pull-right">
				<c:if test="${!empty reportList}">
					<a id="showprint" href="#"><img src='<%=contextPath%>/images/printer_icon.png' border="0" onclick="PrintDiv();" alt="Print" /> </a>
				</c:if>
				</div>
				</div>
            </div><!-- /.box-header -->
                
                <!-- form start -->
                <form:form class="form-horizontal" commandName="consolidateReport" id='form1' action="rptConsolidateBlockGramPanchayat.do">
                	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="rptConsolidateBlockGramPanchayat.do"/>" />
					<input type="hidden" id="statename" name="statename" />
					<input type="hidden" id="disttname" name="disttname" />
					<c:choose>
						<c:when test="${empty reportList}">
					
					<div class="box-body" id="cat">
							                  
		                    <div class="form-group">
			                    <label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message><span class="mandatory">*</span></label>
			                    <div class="col-sm-6">
			                     	<form:select path="state_name_english" class="form-control" id="ddSourceState" onchange="getDistrict(this.value);" onfocus="validateOnFocus('ddSourceState');helpMessage(this,'ddSourceState_msg');" onblur="vlidateOnblur('ddSourceState','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('ddSourceState')">
										<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
										<form:options items="${statelist}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
									</form:select>
									<div id="errSelectState" class="errormsg"><c:out value="${saveMsg}" escapeXml="true"></c:out></div>
		                 		</div>
		             		</div>
		                    
		                    
		                    <div class="form-group ">
			                  <label class="col-sm-4 control-label"><spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
			                      <div class="col-sm-6">
									<form:select path="districtPName" class="form-control" id="ddSourceDistrict" onfocus="validateOnFocus('ddSourceDistrict');helpMessage(this,'ddSourceDistrict_msg');"
										onblur="vlidateOnblur('ddSourceDistrict','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('ddSourceDistrict')">
											<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
											<form:options items="${districtList}" itemValue="districtPK.districtCode" itemLabel="districtNameEnglish"></form:options>
									</form:select>
									<div id="errSelectDistrict" class="errormsg"><c:out value="${saveMsg}" escapeXml="true"></c:out></div> 
			                     </div>
		                    </div>
	                          
	                       <div class="form-group">
		                     <label class="col-sm-4 control-label"></label>
		                       <div class="col-sm-6">
		                        <img src="captchaImage" alt="Captcha Image" id="captchaImageId"/><br>
		                       </div>
		                   </div>
		                 
		                   <div class="form-group">
		                     <label for="captchaAnswer" class="col-sm-4 control-label"><spring:message code="captcha.message"	htmlEscape="true" /><span class="mandatory">*</span></label>
		                       <div class="col-sm-6">
		                      	 <form:input path="captchaAnswer" id="captchaAnswer" maxlength="5" name="captchaAnswer" autocomplete="off" />
		                      	 <a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>
								   <c:if test="${captchaSuccess1 == false}">
									<div class="errormsg"><spring:message code="captcha.errorMessage" htmlEscape="true" /></div>
								  </c:if>
								  <div id="errSelectCaptcha" class="errormsg"><c:out value="${saveMsg}" escapeXml="true"></c:out></div>
									<form:errors path="captchaAnswer" cssClass="errormsg" />
								 	 <div class="errormsg" id="errorCaptcha" style="visibility: hidden; display: none;"><spring:message code="captcha.errorMessage"/></div>
		                       </div>
	                    	</div>
	                    	<div class="box-footer">
		                     <div class="col-sm-offset-2 col-sm-10">
		                       <div class="pull-right">
		                       		<button type="button" class="btn btn-success" onclick="getData();"><i class="fa fa-floppy-o"></i> <spring:message code="Label.GETREPORT" htmlEscape="true"></spring:message></button>
		                            <button type="button" class="btn btn-danger" name="Submit3" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true" code="Button.CLOSE"></spring:message></button>
		                        </div>
		                     </div>   
		                  </div><!-- /.box-footer -->
	                    </div>
	                 </c:when>
	                    	
						
		                  
		                 
		                  <c:otherwise>
		                  	<div class="box-body" id="printble">
							  <h6 id="statusmessage" style="font-size:18px; font-weight:bold; text-align:center; margin:0;"><c:out value="${statusMessage}" escapeXml="true"></c:out></h6>
								 <div class="table-responsive ">
								<table class="table table-striped table-bordered dataTable" id="example" >
										<thead>
											<tr>
												
												<th><spring:message code="Label.SNO" /></th>
												<th><spring:message code="Label.DISTRICTCODE" text="District Code" /></th>
												<th><spring:message code="Label.DISTRICTNAMEINENGLISH" text="District Name" /></th>
												<th><spring:message code="Label.BLOCKCODE" text="Block Code" /></th>
												<th><spring:message code="Label.BLOCKNAMEINENGLISH" text="Block Name" /></th>
												<th><c:out value="${villPanchyatHeading}" escapeXml="true"></c:out>&nbsp;<spring:message code="Label.CODE" text="Code" /><c:out value="/TLB Code"/></th>
												<th><c:out value="${villPanchyatHeading}" escapeXml="true"></c:out>&nbsp;<spring:message code="Label.NAME" text="Name" /><c:out value="/TLB Name"/></th>
												<th><spring:message code="Label.VILLAGECODE" text="Village code" /></th>
												<th><spring:message code="Label.VILLAGENAMEINENGLISH" text="Village Name" /></th>
											</tr>
										</thead>
										
										<tbody>
										 	<c:forEach var="listBlockDetails" varStatus="blockRow" items="${reportList}">
											<tr>
												<td align="center"><c:out value="${blockRow.count}" escapeXml="true"></c:out></td>
												<td><c:out value="${listBlockDetails[0]}" escapeXml="true"></c:out></td>
												<td><c:out value="${listBlockDetails[1]}" escapeXml="true"></c:out></td>
												<td><c:out value="${listBlockDetails[2]}" escapeXml="true"></c:out></td>
												<td><c:out value="${listBlockDetails[3]}" escapeXml="true"></c:out></td>
												<td><c:out value="${listBlockDetails[4]}" escapeXml="true"></c:out></td>
												<td><c:out value="${listBlockDetails[5]}" escapeXml="true"></c:out></td>
												<td><c:out value="${listBlockDetails[6]}" escapeXml="true"></c:out></td>
												<td><c:out value="${listBlockDetails[7]}" escapeXml="true"></c:out></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								</div>
								<c:if test="${! empty reportList}">
								  <div style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
									<label><spring:message code="Label.URL" htmlEscape="true"></spring:message><%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%> </label>
									</br><%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%><label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message><%=df.format(new java.util.Date())%></label>
									</br><label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
								  </div>
								</c:if>
								<div id="footer" ></div>
							</div>
							<div class="box-footer">
		                     <div class="col-sm-offset-2 col-sm-10">
		                       <div class="pull-right">
		                      		<button type="button" class="btn btn-primary" name="back" onclick="javascript:location.href='rptConsolidateBlockGramPanchayat.do?<csrf:token uri='rptConsolidateBlockGramPanchayat.do'/>';"><spring:message htmlEscape="true"  code="Button.BACK"></spring:message></button>
		                      		<button type="button" class="btn btn-danger" name="Submit3" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE"/></button>
		                        </div>
		                     </div>   
		                  </div><!-- /.box-footer -->
		                </c:otherwise>
		                </c:choose>							
					 </form:form>
				<c:if test="${captchaError}">
					<script>getDistrict('${stateId}');</script>
				</c:if>
			 </div>
		 </section>
	   </div>
     </div>
   </section>		
 </body>
</html>
