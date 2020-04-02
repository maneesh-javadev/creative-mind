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
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/plugins/datatables/dataTables.bootstrap.css">
 <script src="resource/dashboard-resource/plugins/datatables/jquery.dataTables.min.js"></script>
 <script src="resource/dashboard-resource/plugins/datatables/dataTables.bootstrap.min.js"></script>


<script type="text/javascript" language="Javascript">


$(document).ready(function() {

	$('#tbl_with_brdr').dataTable({
	        "lengthMenu": [[ 50,100, -1], [ 50,100, "All"]],
	         "scrollX": true
	    });
});

function getData() 
{
		var errorCaptcha = document.getElementById('errorCaptcha');
		errorCaptcha.innerHTML = "";
		var sessionId = document.getElementById('sessionId').value;
		var capchaImgVal = document.getElementById('captchaAnswer').value;
		
if(capchaImgVal==0 ||capchaImgVal ==null)
			{
			document.getElementById("errEmptyCaptcha").innerHTML = "<spring:message code="Error.EmptyCaptcha"/>"; 
		    document.getElementById("errEmptyCaptcha").focus();
		    return false;
			} 
		/* 	$("#form1").validate({
		        rules : {
		        	"capchaImgVal" : {
		                        required : true
		                 }     
		                },
			 messages : {
		        	"capchaImgVal" : {
		                        required : "*Required"
		                
		                }
}); */
		 
		 /* $("#form1" ).rules( "captchaAnswer" : {required : true}, 
	 		 messages: {"captchaAnswer": {required : "Please enter the text shown above."}}
		}); */
		 
		document.getElementById('form1').method = "post";
		document.getElementById('form1').action = "rptStatePanchayats.do?<csrf:token uri='rptStatePanchayats.do'/>";
		document.getElementById('form1').submit();
		return true;
}

function PrintDiv() {


	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';	
	var printContent = document.getElementById("printble2");
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


/* $(document).ready(function() {

	$("#tbl_with_brdr tr:even").css("background-color", "#dedede");

	$("#tbl_with_brdr tr:odd").css("background-color", "#ffffff");

}); */
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
              <h3 class="box-title">Report on State Panchayats</h3>
              <c:if test="${not empty reportList}">						
				<a id="showprint" class="pull-right" href="#"><img	src='<%=contextPath%>/images/printer_icon.png' border="0" alt="Print" onclick="PrintDiv();" /></a>				
			  </c:if>
            </div><!-- /.box-header -->
                <!-- form start -->
                <form:form class="form-horizontal" commandName="reportStatePanchayat" id='form1' action="rptStatePanchayats.do">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="rptStatePanchayat.do"/>" />
                	
			<c:if test="${empty reportList}">
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
             		 <a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a><br />
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
			
				<div class="box-body" id="printble2">
			
						<input type="hidden" id="sessionId" value='<%=sessionId%>'></input>
				<c:if test="${!empty reportList}">
						<div class="box-header subheading"><h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;"><spring:message htmlEscape="true" code="Label.StatePanchayatReportSetup"></spring:message></h6></div>
						<label>*N.A.- Not Applicable</label>
				 <div class="table-responsive ">
				<table class="table table-striped table-bordered dataTable" width="100%" align="center" id="tbl_with_brdr" >
				  <thead>
					<tr>
						<th><spring:message code="Label.SNO"></spring:message></th>
						<th><spring:message code="Label.STATENAME"></spring:message></th>
						<th><spring:message code="Label.DISTRICTPANCHAYATNAME"></spring:message></th>
						<th><spring:message code="Label.INTERMEDIATEPANCHAYATNAME"></spring:message></th>
						<th><spring:message code="Label.VILLAGEPANCHAYATNAME"></spring:message></th>					
					</tr>
				</thead>
				<tbody>
					<c:forEach var="listStatePanchayatDetails" varStatus="listStatePanchayatRow" items="${reportList}">
						<tr class="tblRowB">
							<td><c:out value="${listStatePanchayatRow.index+1}" escapeXml="true"></c:out></td>
							<td><c:out value="${listStatePanchayatDetails.stateNameEnglish}" escapeXml="true"></c:out></td>
							<td align="left"><c:out value="${listStatePanchayatDetails.dp}" escapeXml="true"></c:out></td>
							<td align="left"><c:out value="${listStatePanchayatDetails.ip}" escapeXml="true"></c:out></td>
							<td align="left"><c:out value="${listStatePanchayatDetails.vp}" escapeXml="true"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
		</c:if>

			
	<c:if test="${! empty reportList}">
		<div style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
			<label><spring:message code="Label.URL" htmlEscape="true"></spring:message><%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%> </label>
			</br><%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%><label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message><%=df.format(new java.util.Date())%></label>
			</br><label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
		</div>
	</c:if>
	<div  id="footer"></div>
				
	</div>	
				
			<c:if test="${! empty reportList}">
			   <div class="box-footer" id="showbutton">
                    <div class="col-sm-offset-2 col-sm-10">
                      <div class="pull-right">
					 <button type="button" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';" name="Submit3" class="btn btn-danger" ><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
		       	   </div>
		       	 </div>
		       </div>
		   </c:if>
		   
		 
		<!-- Alert on getting empty village list -->
		<c:if test="${empty reportList}">
			<c:if test="${saveMsg != null}">
				<div>
					<spring:message htmlEscape="true" code="error.NOVILLFOUND"></spring:message>
					<script>alert("<c:out value="${saveMsg}"/>");</script>
				</div>			
			</c:if>
		</c:if>
						
          	</form:form>
			</div>
		</section>
		</div>
	</div>
</section>
 </body>
</html>