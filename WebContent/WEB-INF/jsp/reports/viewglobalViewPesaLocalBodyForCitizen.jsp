<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../homebody/commanInclude.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<!-- <script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script> -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/plugins/datatables/dataTables.bootstrap.css">

 <script src="resource/dashboard-resource/plugins/datatables/jquery.dataTables.min.js"></script>
 <script src="resource/dashboard-resource/plugins/datatables/dataTables.bootstrap.min.js"></script>
<%-- <script type="text/javascript" language="javascript" src="<%=contextPath%>/datatable/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/datatable/jquery-ui-1.8.7.custom.min.js"></script>
<script src="<%=contextPath%>/datatable/TableTools.js" charset="utf-8" type="text/javascript"></script>
<script src="<%=contextPath%>/datatable/ZeroClipboard.js" charset="utf-8" type="text/javascript"></script> --%>
<%-- <link href="<%=contextPath%>/datatable/jquery-ui-1.8.7.custom.css" rel="stylesheet"  type="text/css" />
<link href="<%=contextPath%>/datatable/demo_table_jui.css" rel="stylesheet"  type="text/css" />
<link href="<%=contextPath%>/datatable/TableTools.css" rel="stylesheet"  type="text/css" /> --%>

<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script src="js/common.js"></script>
<script type='text/javascript'>

function validation()
 {	
	var e = document.getElementById("ddSourceState");
	var strUser = e.options[e.selectedIndex].text;
	$('#abc').val(strUser.toString());
	
	var textVal = $("#ddSourceState").val();
	if (textVal == "") {
		
	$('#err_State_Name').html("State list is Required");
	return false;
	}
	
	var capchaImgVal = document.getElementById('captchaAnswer').value;
	/* Empty Captcha Check */
	if(capchaImgVal == null || capchaImgVal == ""){
		document.getElementById("errorCaptcha").innerHTML = "Please enter the Captcha shown above"; 
	    return false;
	}
	
 }
 
 
 
function CallPrint()
{ 
	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';
	var printContent = document.getElementById("print"); 
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
		$('#modifyAdminUnitLevel').dataTable({
			 "lengthMenu": [[ 50,100, -1], [ 50,100, "All"]],
	         "scrollX": true
			
		});
		var type= '<c:out value="${flag}" escapeXml="true"></c:out>';
		if (type == 1) {
			document.getElementById('selectTypeofLb').style.visibility = 'visible';
			document.getElementById('selectTypeofLb').style.display = 'block';
			document.getElementById('viewLbPesaDetail').style.visibility = 'hidden';
			document.getElementById('viewLbPesaDetail').style.display = 'none';
			document.getElementById('print').style.display = 'hidden';
			
			
		} 
		else if (type == 2) {
			document.getElementById('viewLbPesaDetail').style.visibility = 'visible';
			document.getElementById('viewLbPesaDetail').style.display = 'block';
			document.getElementById('selectTypeofLb').style.visibility = 'hidden';
			document.getElementById('selectTypeofLb').style.display = 'none';
			document.getElementById('showprint').style.display = 'block';
			document.getElementById('print').style.display = 'hidden';
		}
		
	} );
	 
	 
	 function test()
	 {
		 
		var sOptions = "dialogWidth:940px; dialogHeight:660px; dialogLeft: 300; dialogTop: 350; scroll:no; center:yes; resizable: no; status:no; edge:sunken;unadorned :yes";
		showModalDialog("PrintPesaReport.do",sOptions);
		 
	 }
		</script>

	</script>
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
              <h3 class="box-title"><spring:message htmlEscape="true" text="List of PESA Panchayats" code="Label.ListofPESAPanchayats"></spring:message></h3>
             <a id="showprint" class="pull-right" href="#" style="display: none;"><img src='<%=contextPath%>/images/printer_icon.png' alt="Print" border="0" onclick="test();" /></a>	
            </div><!-- /.box-header -->
                <!-- form start -->
                <form:form class="form-horizontal" method="post" commandName="localGovtBodyForm" id="localGovtBodyForm" onsubmit="" name="localGovtBodyForm">
				<div id="selectTypeofLb">
				   <div class="box-header subheading"><spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message></div>
					 <div id='showbydropdown'>
						<form:input type="hidden" path="coordinates" id="abc" ></form:input>			
								<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message	htmlEscape="true" code="Label.SELECTSTATE"></spring:message></label>
									 <div class="col-sm-6">
										<form:select path="slc" class="form-control" id="ddSourceState"	onchange=""><form:option value="">
											<spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
											<form:options items="${statesList}" itemValue="stateCode" itemLabel="stateNameEnglish"></form:options>
										</form:select><br/>
										<div id="err_State_Name" class="errormsg"></div>
								 	</div>
								</div>
							
							    <div class="form-group">
							 	  <label for="captchaAnswer" class="col-sm-4 control-label"></label>
							     	<div class="col-sm-6">
							           <img src="captchaImage" alt="Captcha Image" id ="captchaImageId"/>
							       </div>
							   </div>
							                    
							   <div class="form-group">
								 <label for="captchaAnswer" class="col-sm-4 control-label"><spring:message code="captcha.message" htmlEscape="true" /></label>
								    <div class="col-sm-6">
								        <form:input path="captchaAnswer" name="captchaAnswer" maxlength="5" autocomplete ="off" id="captchaAnswer" onblur="clearMessage(this.value);" />
								         <a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a> 
										<div id="errorCaptcha" class="errormsg"></div>
											<c:if test="${captchaSuccess == false}">
												<div class="errormsg"><spring:message code="captcha.errorMessage" htmlEscape="true" /></div>
											</c:if> 
											<div class="errormsg"><form:errors path="captchaAnswer" cssStyle="color:red" /></div>
											<div class="errormsg" id="errorCaptcha" style="visibility: hidden; display: none;">
											<spring:message code="captcha.errorMessage" />
										</div>
								 </div>
							   </div> 
						
						<div class="box-footer">
			             <div class="col-sm-offset-2 col-sm-10">
			              <div class="pull-right">
							<button type="submit" id="grampanchyatlist"	name="submmit" onclick="return validation(this.value);" class="btn btn-success"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message></button>
							<button type="button"  id="close" name="close" onclick="javascript:go('welcome.do');" class="btn btn-danger" ><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
						 </div>
						</div>
				      </div>
		           </div>
	          
					   
           	<c:if test="${dataflag == 0}">
				<div class="errormsg" id="divData">
					<label><spring:message htmlEscape="true" code="error.no.rec.found.LBPesa" text="No PESA Panchayat Found"/>*</label>
				</div>
			</c:if>
		</div>
           		<div class="box-body" id="viewLbPesaDetail"	style="visibility: hidden; display: none;">
           			<c:if test="${! empty getLbPesa}">
           				<div id="divData">
					<h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">
						<spring:message htmlEscape="true" code="Label.PESAPanchayat"	text="List of PESA enabled Village Panchayats of " />
						<c:out value="${localGovtBodyForm.coordinates}" escapeXml="true"></c:out>
					</h6>				
			<div class="table-responsive ">
				<table class="table table-striped table-bordered dataTable" width="100%" align="center" id="modifyAdminUnitLevel" >
				  <thead>
					<tr>
						<th><spring:message htmlEscape="true" code="Label.SNO"></spring:message></th>
						<th><spring:message code="Label.ZillaPanchayatname" text="Zilla Panchayat name"></spring:message></th>
						<th><spring:message code="Label.INTERMEDIATEPANCHAYATNAME" text="Intermediate Panchayat Name"></spring:message></th>
						<th><spring:message code="Label.LGDCodeofVillagePanchayat" text="LGD Code of Village Panchayat"></spring:message></th>
						<th><spring:message code="Label.NameofVillagePanchayat(In English)" text="Name of Village Panchayat (In English)"></spring:message></th>
						<th><spring:message code="Label.NameofVillagePanchayat(In Local language)" text="Name of Village Panchayat (In Local language)"></spring:message></th>
						<th><spring:message code="Label.PesaCoverageType" text="PESA Coverage Type"></spring:message></th>		
					</tr>
				</thead>
				<tbody>
					<c:forEach var="adminEntityDetail" varStatus="listAdminRow" items="${getLbPesa}">
						<tr>
							<td align="center"><c:out value="${offsets*limits+(listAdminRow.index+1)}" escapeXml="true"></c:out></td>
							<td align="center"><c:out value="${adminEntityDetail.dpName}" escapeXml="true"></c:out></td>
							<td align="center"><c:out value="${adminEntityDetail.ipName}" escapeXml="true"></c:out></td>
							<td align="center"><c:out value="${adminEntityDetail.vpCode}" escapeXml="true"></c:out></td>
							<td align="center"><c:out value="${adminEntityDetail.vpNameEng}" escapeXml="true"></c:out></td>
							<td align="center"><c:out value="${adminEntityDetail.vpNameLoc}" escapeXml="true"></c:out></td>
							<td align="center">
								<c:choose>
									<c:when test="${fn:contains(adminEntityDetail.coverageType,'P')}"><spring:message code="Label.PART" ></spring:message></c:when>
									<c:otherwise><spring:message code="Label.FULL" ></spring:message></c:otherwise>
								</c:choose>
							</td>
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
		<div id="footer"></div>	
	</div>
	<div class="box-footer">
	 <div class="col-sm-offset-2 col-sm-10">
       <div class="pull-right">
			<button type="button" onclick="javascript:location.href='listOfPesaPanchyat.do?<csrf:token uri='listOfPesaPanchyat.do'/>';" class="btn btn-info" > <spring:message htmlEscape="true"  code="Button.BACK"/></button>
			<button type="button" id="close" name="close" onclick="javascript:go('welcome.do');" class="btn btn-danger" ><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
		</div>
	 </div>
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