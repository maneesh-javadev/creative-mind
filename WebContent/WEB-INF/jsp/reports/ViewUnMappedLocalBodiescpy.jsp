<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../homebody/commanInclude.jsp"%>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>

<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/call/plaincall/lgdDwrlocalBodyService.getchildlocalbodiesByParentcode.dwr'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDesignationService.js'></script>
<%@include file="../common/dwr.jsp"%>
<script language="javascript">
function getLocalBodyTypeListByStateCode(stateCode)
{
	
	lgdDwrDesignationService.getLocalbodyDetail(stateCode, {
		callback : handleLocalbodySuccess,
		errorHandler : handleLocalbodyError
	});

}

function handleLocalbodySuccess(data) {
	// Assigns data to result id
	// alert("ti");
	// alert(data);
	var fieldId = 'lbTypeId';
	// alert(data);
	var valueText = 'nomenclatureEnglish';
	var nameText = 'localBodyTypeName';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById(fieldId);
	
	st.add(new Option('All local body types', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleLocalbodyError() {
	// Show a popup message
	alert("No data found in local body!");
}

function validatelbt() {
	var error = false;
	var inSelectState = document.getElementById("ddSourceState").value;
	var inSelectlbtype = document.getElementById("lbTypeId").value;
    var captcha = document.getElementById("captchaAnswer").value;
	if (inSelectState != 0 && inSelectState != "") {

		if (inSelectlbtype == "") {
			error = true;
			$("#error_selectlbt").show();

		}
	
	else if(captcha == "")
		{
		
		document.getElementById("errorCaptcha").innerHTML = "Please Enter CAPTCHA  in the textbox"; 
	    document.getElementById("errorCaptcha").focus();
	    error = true;
		}
	
	}
	
	else {

		$("#error_selectstate").show();
		error = true;
	}

	if (error == true) {
		return false;
	} else {
		return true;
	}

}

function hideError() {
	$("#error_selectlbt").hide();
	$("#error_selectstate").hide();
	}
	
function loadMe() {
		hideError();		
	}
if ( window.addEventListener ) { 
    window.addEventListener( "load",loadMe, false );
 }
 else 
    if ( window.attachEvent ) { 
       window.attachEvent( "onload", loadMe );
 } else 
       if ( window.onLoad ) {
          window.onload = loadMe;
 }
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
              <h3 class="box-title"><spring:message code="Label.viewUnmappedlocalbodies" htmlEscape="true"></spring:message></h3>
           		 <a id="showprint" href="#" style="visibility: hidden; display: none;"><img src='<%=contextPath%>/images/printer_icon.png' border="0" onclick="CallPrint()" onmouseover="" alt="Print" /></a>
            </div><!-- /.box-header -->
                <!-- form start -->
                <form:form class="form-horizontal" action="rptViewUnmappedLocalBodies.do" method="POST" commandName="rptViewUnMappedLocalBodies" id="form1" name="form1">
				 <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="createWardforPRI.htm"/>" /> 
					<div id="cat">
					 <c:if test="${empty viewUnMappedLBlist}">
							<div class="box-header subheading" id='viewentity'><spring:message code="Label.SELECTCRITERIA" htmlEscape="true"></spring:message></div>
									
								<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message code="Label.SELECTSTATE" htmlEscape="true"></spring:message></label>
									 <div class="col-sm-6">
										<form:select path="stateNameEnglish" class="form-control" id="ddSourceState" onchange="getLocalBodyTypeListByStateCode(this.value);hideError();">
											<form:option value=""> <spring:message code="Label.SELECT" htmlEscape="true"></spring:message> </form:option>
											<form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
											</form:select>
											<div class="mandatory" id="error_selectstate">
												<spring:message htmlEscape="true" code="error.select.SELECTSTATENAME"></spring:message>
										    </div>			
								 	</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message></label>
									 <div class="col-sm-6">
										<form:select path="lgd_LBTypeName" id="lbTypeId" htmlEscape="true" class="form-control" onchange ="hideError();">
											<form:option selected="selected" value="" label="--select--" />
											
										</form:select>
										<div class="mandatory" id="error_selectlbt">
											<spring:message htmlEscape="true" code="error.select.LGOVTTYPE"></spring:message>
										</div>				
								 	</div>
								</div>
								
								<input type="hidden" name="stateCode" value="<c:out value='${stateCode}' escapeXml='true'></c:out>" /><input type="hidden" value="<c:out value='${lbType}' escapeXml='true'></c:out>"/>
							
							    <div class="form-group">
							 	  <label for="captchaAnswer" class="col-sm-4 control-label"></label>
							     	<div class="col-sm-6">
							           <img src="captchaImage" alt="Captcha Image"  id ="captchaImageId"/>
							       </div>
							   </div>
							                    
							   <div class="form-group">
								 <label for="captchaAnswer" class="col-sm-4 control-label"><spring:message code="captcha.message" htmlEscape="true" /></label>
								    <div class="col-sm-6">
							        	<form:input path="captchaAnswer" name="captchaAnswer" maxlength="5" id="captchaAnswer" autoComplete="off"/>									
											<a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>
											<span id="errorCaptcha" class ="errormsg"></span>
											<div class="mandatory">
												<c:if test="${ captchaSuccess1 == false }"><spring:message code="captcha.errorMessage" htmlEscape="true" /></c:if>
												<form:errors path="captchaAnswer" />
											</div>
										<div id="errorCaptcha1" class="mandatory" style="visibility: hidden; display: none;"><spring:message code="captcha.errorMessage"/></div>
								 </div>
							   </div> 
							
							
						
						<div class="box-footer">
			             <div class="col-sm-offset-2 col-sm-10">
			              <div class="pull-right">
							<button type="submit" name="Submit" onclick="return validatelbt();" class="btn btn-success"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message></button>
							<button type="button"  name="Submit3" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';" class="btn btn-danger" ><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
						 </div>
						</div>
				      </div>
	          </c:if>
					   
           	<c:if test="${! empty viewUnMappedLBlist}">
           		<div class="box-body" id="divData">
					<h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">
						<spring:message htmlEscape="true" code="Label.viewUnmappedlocalbodies"> of <c:out value="${stateNameEng}" escapeXml="true"></c:out> </spring:message> 
					</h6>			
				<div class="table-responsive ">
				<table class="table table-striped table-bordered dataTable" width="100%" align="center" >
				  <thead>
					<tr>
						<th><spring:message htmlEscape="true" code="Label.SNO"></spring:message></th>
						<th><spring:message htmlEscape="true" code="Label.LOCALBODYCODE"></spring:message></th>
						<th><spring:message htmlEscape="true" code="Label.LOCALBODYNAMEINENG"></spring:message></th>
						<th><spring:message htmlEscape="true" code="Label.LOCALBODYTYPENAME"></spring:message></th>
						<th><spring:message htmlEscape="true" code="Label.PARENTLOCALBODYNAME"></spring:message></th>				
					</tr>
				</thead>
				<tbody>
					<c:forEach var="listLocalBodyDetails" varStatus="listLocalBodyRow" items="${viewUnMappedLBlist}">
						<tr class="tblRowB">
							<td><c:out value="${listLocalBodyRow.index+1}" escapeXml="true"></c:out></td>
							<td><c:out value="${listLocalBodyDetails.localBodyCode}" escapeXml="true"></c:out></td>
							<td><c:out value="${listLocalBodyDetails.localBodyNameEnglish}" escapeXml="true"></c:out></td>
							<td> <c:out value="${listLocalBodyDetails.localBodyTypeName}" escapeXml="true"></c:out></td>
							<td> <c:out value="${listLocalBodyDetails.parentname}" escapeXml="true"></c:out></td>
						</tr>
					</c:forEach>
			</tbody>
		</table>			
		</div>
		<div style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
			<label><spring:message code="Label.URL" htmlEscape="true"></spring:message><%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%> </label>
			</br><%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%><label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message><%=df.format(new java.util.Date())%></label>
			</br><label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
		</div>	
		<div id="footer" ></div>		
	</div>
	<div class="box-footer">
	 <div class="col-sm-offset-2 col-sm-10">
       <div class="pull-right">
			<button type="submit"  name="back"  onclick="javascript:location.href='rptViewUnmappedLocalBodies.do?<csrf:token uri='rptViewUnmappedLocalBodies.do'/>';" class="btn btn-info" > <spring:message htmlEscape="true"  code="Button.BACK"></spring:message></button>
			<button type="button" name="Submit3" id="btn1" class="btn btn-danger" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
		</div>
	 </div>
  </div>	
			</c:if>
			
			<c:if test = "${flag2 eq 1}">
			
			<div class="box-body">
			   <span id="required" class="mandatory"><spring:message code="error.norecoredfound" htmlEscape="true"></spring:message></span>
			   </div>
			   </c:if>
			</div>
          	</form:form>
			</div>
		</section>
		</div>
	</div>
</section>

</body>
</html>