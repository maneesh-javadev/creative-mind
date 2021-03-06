<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../homebody/commanInclude.jsp"%>

<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript'  src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/plugins/datatables/dataTables.bootstrap.css">
 <script src="resource/dashboard-resource/plugins/datatables/jquery.dataTables.min.js"></script>
 <script src="resource/dashboard-resource/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="js/stateWiseUnmappedVillages.js"></script>
<script type="text/javascript" language="javascript">

$(document).ready(function() {
	$('#tbl_with_brdr').dataTable({
	        "lengthMenu": [[ 50,100, -1], [ 50,100, "All"]],
	         "scrollX": true
	    });
	
});

function printReoprtXlsFormat()
{
	document.getElementById('form1').method = "post";
	document.getElementById('form1').action = "dpwardConstituencyWiseVPPOSTXlsFormatPrint.htm?<csrf:token uri='dpwardConstituencyWiseVPPOSTXlsFormatPrint.htm'/>";
	document.getElementById('form1').submit();
	return true;
	
}

function hideMessageOnKeyPressstate()
{	
	$("#OfficialAddress_msg").hide();	
	
}

function CallPrint() {
	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';
	/*document.getElementById('footertext').style.fontSize = '13px';*/
	var printContent = document.getElementById("ash");
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
              <h3 class="box-title"><spring:message htmlEscape="true" code="Label.DPwardConstituencyWiseVP"></spring:message></h3>
             <c:if test="${!empty DPwardConstituencyWiseVPList}">
				<a id="showprint" class="pull-right" href="#"><img src='<%=contextPath%>/images/printer_icon.png' border="0" onclick="CallPrint();" alt="Print" /></a>
			</c:if>	
            </div><!-- /.box-header -->
                <!-- form start -->
                <form:form class="form-horizontal" action="dpwardConstituencyWiseVPPOST.do" id="form1" name="form1" method="POST" commandName="dpwardConstituencyWiseVPForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='dpwardConstituencyWiseVPPOST.do'/>" />
				<input type="hidden" name="direction" id="direction" value="0" />
					<div id="cat">
					  <c:if test="${empty DPwardConstituencyWiseVPList}">
							<div class="box-header subheading" id='viewentity'><spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message></div>
						<div id='showbydropdown'>
								<div class="box-body" >	
								<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message></label>
									 <div class="col-sm-6">
										<form:select path="stateNameEnglish" class="form-control" id="ddSourceState" onchange="error_remove();hideMessageOnKeyPressstate()" >
											<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
											<form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
										</form:select>
										<div class="errormsg" id="errSelectStateName" style="visibility: hidden; display: none;"><spring:message code="error.select.SELECTSTATENAME"/></div>
										<div class="errormsg" id="errSelectStatedistrictName"></div>
								 	</div>
								</div>
							
							    <div class="form-group">
							 	  <label for="captchaAnswer" class="col-sm-4 control-label"></label>
							     	<div class="col-sm-6">
							           <img src="captchaImage" alt="Captcha Image" id="captchaImageId"/>
							       </div>
							   </div>
							                    
							   <div class="form-group">
								 <label for="captchaAnswer" class="col-sm-4 control-label"><spring:message code="captcha.message" htmlEscape="true" /></label>
								    <div class="col-sm-6">
								
								        <form:input path="captchaAnswers" name="captchaAnswesr" maxlength="5" id="captchaAnswer"  autocomplete="off" />								
									   <a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>
									   <div class="errormsg">
									   <span id="errorCaptcha" class="errormsg"
										<c:if test="${ captchaSuccess2 == false }">
											<spring:message code="captcha.errorMessage" htmlEscape="true" />
										</c:if>
									  </div>
									<div class="errormsg" id="errorCaptcha" style="visibility: hidden; display: none;"><spring:message code="captcha.errorMessage"/></div>	
								 </div>
							   </div> 
							
						</div>
						<div class="box-footer">
			             <div class="col-sm-offset-2 col-sm-10">
			              <div class="pull-right">
							<button type="submit" name="Submit" onclick="return validate_report();" class="btn btn-success"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message></button>
				<%-- 			<button type="button" name="Submit2" onclick="javascript:go('dpwardConstituencyWiseVP.do');" class="btn btn-warning"> <spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button> --%>
							<button type="button" name="Submit3" id="btn1" class="btn btn-danger" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
						</div>
				      </div>
		           </div>
		           </div>
		           
	          </c:if>
					   
           	<c:if test="${! empty DPwardConstituencyWiseVPList}">
           		<div id="ash">
           		<div class="box-body" >
					<h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">
						<spring:message htmlEscape="true" code="Label.DPwardConstituencyWiseVP"></spring:message>&nbsp;${stateName}</label>
					</h6>				
				  <div class="table-responsive ">
				<table class="table table-striped table-bordered dataTable" width="100%" align="center" id="tbl_with_brdr" >
				  <thead>
					<tr>
						<th><spring:message code="Label.SNO"></spring:message></th>
						<th><spring:message code="Label.LGDCODE" text="LGD Code"/></th>
						<th><spring:message code="Label.DISTRICTPANCHAYATNAME"></spring:message></th>
						<th><spring:message code="Label.WARDNUMBER"></spring:message></th>
						<th><spring:message code="Label.WARDNAMEINENG"></spring:message></th>
						<th><spring:message code="Label.COVEREDENTITYCODE"></spring:message></th>	
						<th><spring:message code="Label.COVEREDENTITYNAME"></spring:message></th>	
						<th><spring:message code="Label.COVEREDENTITYTYPE"></spring:message></th>			
					</tr>
				</thead>
				<tbody>
					<c:forEach var="listDPwardConstituencyWiseVPDetail" varStatus="listEntityRow" items="${DPwardConstituencyWiseVPList}">
						<tr class="tblRowB">
							<td><c:out value="${(offsets*limits+listEntityRow.index+1)}" escapeXml="true"></c:out></td>
							<td align="left"><c:out value="${listDPwardConstituencyWiseVPDetail.dpCode}" escapeXml="true"></c:out></td>
							<td align="left"><c:out value="${listDPwardConstituencyWiseVPDetail.dpname}" escapeXml="true"></c:out></td>
							<td align="left"><c:out value="${listDPwardConstituencyWiseVPDetail.wardnumber}" escapeXml="true"></c:out></td>
							<td align="left"><c:out value="${listDPwardConstituencyWiseVPDetail.wardname}" escapeXml="true"></c:out></td>
							<td align="left"><c:out value="${listDPwardConstituencyWiseVPDetail.vpcode}" escapeXml="true"></c:out></td>
							<td align="left"><c:out value="${listDPwardConstituencyWiseVPDetail.vpname}" escapeXml="true"></c:out></td>
							<td align="left"><c:out value="${listDPwardConstituencyWiseVPDetail.entitytype}" escapeXml="true"></c:out></td>
						</tr>
					</c:forEach>
			</tbody>
		</table>			
		</div>
		<div style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
			<label><spring:message code="Label.URL" htmlEscape="true"></spring:message>
			<c:out value='<%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%>'/> </label>
			</br><%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%><label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message>
			<c:out value="<%=df.format(new java.util.Date())%>"/></label>
			</br><label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
		</div>	
		<div  id="footer" ></div>	
	</div>
	</div>
	<div class="box-footer">
	 <div class="col-sm-offset-2 col-sm-10">
       <div class="pull-right">
			<button type="button"  name="Submit3" onclick="javascript:go('welcome.do');" class="btn btn-danger" ><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
		</div>
	 </div>
  </div>	
			</c:if>
			<c:if test = "${flag1 eq 1}">
			<div class="box-body">
			<div class="errormsg"> <spring:message htmlEscape="true" code="error.norecoredfound"></spring:message></div>
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