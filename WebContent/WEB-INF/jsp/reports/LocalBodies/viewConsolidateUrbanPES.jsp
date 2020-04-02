<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<head>
<script language="javascript">
function goBack()
{
	//alert("inside the back method");
	document.getElementById('form1').method = "post";
	document.getElementById('form1').action = "rptConsolidateforPanchayatPES.do?<csrf:token uri='rptConsolidateforPanchayatPES.do'/>";
	document.getElementById('form1').submit();
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

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>E-Panchayat</title>
<script type="text/javascript" src="js/common.js"></script>
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
              <h3 class="box-title"></h3>
              <c:if test="${!empty consolidateLBList}"> <a href="#" class="pull-right"> <img src='<%=contextPath%>/images/printer_icon.png' border="0" onclick="PrintDiv();" /></a></c:if>
	        </div><!-- /.box-header -->
               
                <!-- form start -->
                <form:form class="form-horizontal" commandName="consolidateReportLBRPT" id='form1'>
				<form:input path="captchaAnswer" type="hidden" value="0" /> 
	 			<form:input path="captchaAnswers" type="hidden" value="0" /> 
								
				<div class="box-body" id="printble">
					<h3 align="center" style="font-size: 24px;">
						<spring:message code="Label.ULB" htmlEscape="true"></spring:message>&nbsp;
						<spring:message code="Label.OF" htmlEscape="true"></spring:message>&nbsp;
						<c:out value="${consolidateReportLBRPT.state_name_english}" escapeXml="true"></c:out>
					</h3>
				
				<div class="">*N.A.- Not Applicable</div><br />
				
				<table class="table table-striped table-bordered dataTable" align="center">
				  <thead>
					<tr>
						<th><spring:message code="Label.SRNO" htmlEscape="true"></spring:message></th>
						<th><spring:message code="Label.LGDCODE" text="LGD Code" htmlEscape="true"></spring:message></th>
						<th><spring:message code="Label.URBANLBNAME" htmlEscape="true"></spring:message></th>
						<th><spring:message code="Label.URBANLBNAMELOCAL" htmlEscape="true"></spring:message></th>
						<th><spring:message code="Label.URBANLBTYPENAME" htmlEscape="true"></spring:message></th>
					</tr>
				 </thead>
					<tbody>
						<c:if test="${! empty consolidateLBList}">
							<c:forEach var="panchaytSetUp" varStatus="panchaytSetUpRow" items="${consolidateLBList}">
								<tr>
									<td><c:out value="${panchaytSetUpRow.index +1}" escapeXml="true"></c:out></td>
									<td><c:out value="${panchaytSetUp.localBodyCode}" escapeXml="true"></c:out></td>
									<td><c:out value="${panchaytSetUp.localbodyNameEnglish}" escapeXml="true"></c:out></td>
									<td><c:out value="${panchaytSetUp.localbodyNameLocal}" escapeXml="true"></c:out></td>
									<td><c:out value="${panchaytSetUp.localbodyTypeName}" escapeXml="true"></c:out></td>
								</tr>
							</c:forEach>
						</c:if>
						
										
				</tbody>
			</table>
			<div style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
						<label><spring:message code="Label.URL" htmlEscape="true"></spring:message><%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%> </label>
						</br><%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%><label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message><%=df.format(new java.util.Date())%></label>
						</br><label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
					</div>
					<div id="footer"></div>
		</div>	
		
		<div class="box-footer">
                  <div class="col-sm-offset-2 col-sm-10">
                    <div class="pull-right">
                   		<button type="button" name="Submit2" class="btn btn-info" onclick="goBack()"><i class="fa fa-arrow-circle-o-left" aria-hidden="true"></i> <spring:message htmlEscape="true"  code="Button.BACK"></spring:message></button>
				<button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
		   </div>
	   </div>
	  </div>						
     </form:form>
	</div>
   </section>
  </div>
 </div>
</section>

</body>
</html>

					

