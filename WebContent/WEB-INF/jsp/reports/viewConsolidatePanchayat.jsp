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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>E-Panchayat</title>
<script type="text/javascript" src="js/common.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>
	
</script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>

 <script type="text/javascript" language="Javascript">
/*$(document).ready(function() {
    $('#printable').DataTable( {
    	searching: false,
    	paging: false,
    	ordering: false,
        info:     false,
    	dom: 'Bfrtip',
        buttons: [
             'csv', 'excel', 'pdf'
        ]
    } );
} );*/


function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
function manageState1(url,stateid,type,level)
{
 	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
	
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }

function manageState2(url,stateid,type,level)
{
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
			
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }
function manageState3(url,stateid,type,level)
{
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
			
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }
function manageState4(url,stateid,type,level)
{
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
			
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }
function manageState5(url,stateid,type,level)
{
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
			
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }
function manageState6(url,stateid,type,level)
{
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
			
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }
function manageState7(url,stateid,type,level)
{
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
			
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }

function getData() 
{
		var errorCaptcha = document.getElementById('errorCaptcha');
		errorCaptcha.innerHTML = "";
		var sessionId = document.getElementById('sessionId').value;
		/* New Captcha Code */
		var capchaImgVal = document.getElementById('captchaAnswer').value;
		//displayLoadingImage();
		if(capchaImgVal==null || capchaImgVal=="")
			{
				document.getElementById("errEmptyCaptcha").innerHTML = "<spring:message code="Error.EmptyCaptcha"/>"; 
			    document.getElementById("errEmptyCaptcha").focus();
			    return false;
			}
		
		document.getElementById('form1').method = "post";
		document.getElementById('form1').action = "rptConsolidateforPanchayat.do?<csrf:token uri='rptConsolidateforPanchayat.do'/>";
		document.getElementById('form1').submit();
		return true;
}

function loadBasic()
{
	 document.getElementById("captchaAnswer").focus();
	
	
	}
	
function PrintDiv() {

	
	
	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';
	/* document.getElementById('footertext').style.fontSize = '13px'; */
	var printContent = document.getElementById("printable");
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
<style>
table.table-bordered.dataTable th:last-child, table.table-bordered.dataTable th:last-child, table.table-bordered.dataTable td:last-child, table.table-bordered.dataTable td:last-child {
    border-right-width: 1px;
}
</style>
</head>
<body onload="loadBasic()">

<section class="content">

<!-- Main row -->
 <div class="row">
     <!-- main col -->
	<div class="container">
	<section class="col-lg-12 prebox">
     	<div class="box">
            <div class="box-header with-border">
              <h3 class="box-title"><spring:message htmlEscape="true" code="Label.CONSOLIDATEDRPTOFLB"></spring:message></h3>
              <c:if test="${!empty consolidateLBList}">
				<a id="showprint" href="#" class="pull-right"><img src='<%=contextPath%>/images/printer_icon.png' border="0" alt="Print" onclick="PrintDiv();" /></a>			
			 </c:if>
            </div><!-- /.box-header -->
                <!-- form start -->
                <form:form class="form-horizontal" commandName="consolidateReportLBRPT" id='form1' action="rptConsolidateforPanchayat.do">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="rptConsolidateforPanchayat.do"/>" />
                	
			<c:if test="${empty consolidateLBList}">
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
		           <form:input path="captchaAnswer" name="captchaAnswer"   autocomplete="off" onkeypress="return enter(event)" maxlength="5"/> 
             		 <a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a><br>
             		<form:errors path="captchaAnswer" cssStyle="color:red"/>
				<div id="errEmptyCaptcha" class="errormsg" ></div>
				<div id="errorCaptcha" class="errormsg"></div>	
				<div id="errormsg" style="color: red;"> <b>${errormsg}</b> </div>
		     </div>
	 </div> 
	 </div>
					<div class="box-footer">
		               <div class="col-sm-offset-2 col-sm-10">
		                 <div class="pull-right">
							<button type="button" onclick="getData();" class="btn btn-success"><i class="fa fa-floppy-o"></i> <spring:message code="Label.GETREPORT" htmlEscape="true"></spring:message></button>
							<button type="button" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';" name="Submit3" class="btn btn-danger" ><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
						</div>
					</div>
				</div>
			</c:if>
			
				<div class="box-body">
				
						
							<c:if test="${!empty consolidateLBList}"><label>*N.A.- Not Applicable</label></c:if>
							<input type="hidden" id="sessionId" value='<%=sessionId%>'></input>
						
				   
			<div id="printable">
			<c:if test="${!empty consolidateLBList}">
			
			 <div class="table-responsive ">
				<table class="table table-striped table-bordered dataTable" width="100%" align="center" >
				  <thead>
					<tr>
						<th width="6%" rowspan="2" style="font-size: 15px;"><spring:message code="Label.SRNO" htmlEscape="true"></spring:message></th>
						<th width="16%" align="center" rowspan="2" style="font-size: 15px;"><spring:message code="Label.STATENAME" htmlEscape="true"></spring:message></th>
						<th width="33%"  colspan="3" style="font-size: 15px;text-align:center"><spring:message code="Label.RURALLOCALBODY" htmlEscape="true"></spring:message></th>
						<th width="16%"  rowspan="2" style="font-size: 15px;text-align: center;" >  <spring:message code="Label.URBANLB" htmlEscape="true"></spring:message></th>
					</tr>
						
					<tr>
						<th width="11%" align="center"><spring:message code="Label.DISTRICTTRADNME" htmlEscape="true"></spring:message></th>
						<th width="11%" align="center"><spring:message code="Label.INTERTRADNME" htmlEscape="true"></spring:message></th>
						<th width="11%" align="center"><spring:message code="Label.VILLAGELVL" htmlEscape="true"></spring:message></th>
					</tr>
				  </thead>

					<tbody>
							<c:if test="${not empty consolidateLBList}">
								<c:forEach var="panchaytSetUp" varStatus="panchaytSetUpRow"
									items="${consolidateLBList}">
									<tr>
										<!-- 	<td width="100%" colspan="9" >
										<table width="100%" class="tbl_no_brdr">
											<tr class="tblRowB"> -->
										<td width="6%"><c:out value="${panchaytSetUpRow.index +1}" escapeXml="true"></c:out></td>
										<td width="16%" align="left"><c:out
												value="${panchaytSetUp.state_name_english}" escapeXml="true"></c:out></td>

										<td width="11%" align="right"><c:choose>
												<c:when test="${panchaytSetUp.dp_count == 0}">
													<c:out value="N.A."></c:out>
												</c:when>
												<c:otherwise>
													<%-- <a
														href="rptConsolidateforPanbyLevel.do?selstate=${panchaytSetUp.state_code}&type=P&level=D&<csrf:token uri='rptConsolidateforPanbyLevel.do'/>">
														<c:out value="${panchaytSetUp.dp_count}"></c:out> </a> --%>
														
														<a href="#" onclick="javascript:manageState1('rptConsolidateforPanbyLevel.do',${panchaytSetUp.state_code},'R','D');"><c:out value="${panchaytSetUp.dp_count}" escapeXml="true"></c:out></a>
														
														
														
												</c:otherwise>
											</c:choose>
										</td>
										<td width="11%" align="right"><c:choose>
												
												<c:when test="${panchaytSetUp.ip_count == 0}">
													<c:out value="N.A."></c:out>
												</c:when>
												<c:otherwise>
												<c:if test="${panchaytSetUp.dp_count == 0}">
														<a href="#" onclick="javascript:manageState2('rptConsolidateforPanbyLevel.do',${panchaytSetUp.state_code},'R','I');"><c:out value="${panchaytSetUp.ip_count}" escapeXml="true"></c:out></a>
												</c:if>
												<c:if test="${panchaytSetUp.dp_count != 0}">
													<c:if test="${panchaytSetUp.state_code==35}">
														<a href="#" onclick="javascript:manageState2('rptConsolidateforPanbyLevel.do',${panchaytSetUp.state_code},'R','I');"><c:out value="${panchaytSetUp.ip_count}" escapeXml="true"></c:out></a>
													</c:if>
													<c:if test="${panchaytSetUp.state_code!=35}">
													<c:out value="${panchaytSetUp.ip_count}" escapeXml="true"></c:out>
													</c:if>
												
												</c:if>
												
												
														
												</c:otherwise>
											</c:choose>
										</td>
										<td width="11%" align="right"><c:choose>
												<c:when test="${panchaytSetUp.vp_count == 0}">
													<c:out value="N.A."></c:out>
												</c:when>
												<c:otherwise>
												<c:if test="${panchaytSetUp.dp_count == 0 and panchaytSetUp.ip_count == 0}">
													<a href="#" onclick="javascript:manageState3('rptConsolidateforPanbyLevel.do',${panchaytSetUp.state_code},'R','V');"><c:out value="${panchaytSetUp.vp_count}" escapeXml="true"></c:out></a>
												</c:if>
												<c:if test="${panchaytSetUp.dp_count != 0 or panchaytSetUp.ip_count != 0}">
													<c:out value="${panchaytSetUp.vp_count}" escapeXml="true"></c:out>
												</c:if>
												</c:otherwise>
											</c:choose>
										</td>
										<td width="12%" align="right"><c:choose>
												<c:when test="${panchaytSetUp.urban_count == 0}">
													<c:out value="N.A."></c:out>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="javascript:manageState7('rptConsolidateforPanbyLevel.do',${panchaytSetUp.state_code},'U','I');"><c:out value="${panchaytSetUp.urban_count}" escapeXml="true"></c:out></a>		

												</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
								
								<form:input path="stateId" type="hidden" name="stateId" id="stateId"  />
 								<form:input path="statetype" type="hidden" name="statetype" id="statetype" /> 
 								<form:input path="statelevel" type="hidden" name="statelevel" id="statelevel" /> 
								
							</c:if>

							 <tr>
								<td width="4%"></td>
								<td width="16%" align="left"><label><spring:message code="Label.TOTALS"></spring:message> </label></td>
								<td width="11%" align="right"><c:out value="${consolidateReportLBRPT.totalDPCount}" escapeXml="true"></c:out></td>
								<td width="11%" align="right"><c:out value="${consolidateReportLBRPT.totalIPCount}" escapeXml="true"></c:out></td>
								<td width="11%" align="right"><c:out value="${consolidateReportLBRPT.totalVPCount}" escapeXml="true"></c:out></td>
								<td width="14%" align="right"><c:out value="${consolidateReportLBRPT.totalUrbanCount}" escapeXml="true"></c:out> </td>
							</tr>
						</tbody>
						</table>
						</div>
					</c:if>
				
			
					<c:if test="${! empty consolidateLBList}">
						<div style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
							<label><spring:message code="Label.URL" htmlEscape="true"></spring:message><%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%> </label>
							</br><%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%><label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message><%=df.format(new java.util.Date())%></label>
							</br><label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
						</div>
					</c:if>
					<div  id="footer"></div>
					</div>
				</div>	
				
				<c:if test="${! empty consolidateLBList}">
					<div class="box-footer" id="showbutton">
                     <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
							<button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
					   </div>
					 </div>
				  </div>
				</c:if>	
						
          	</form:form>
			</div>
		</section>
		</div>
	</div>
</section>
</body>
</html>