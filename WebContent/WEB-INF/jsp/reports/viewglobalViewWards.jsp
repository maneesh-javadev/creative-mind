<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../homebody/commanInclude.jsp"%>

<script type='text/javascript' src="<%=contextpthval%>/js/stateWiseUnmappedVillages.js"></script>
<script type='text/javascript' src="<%=contextpthval%>/js/common.js"></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/util.js'> </script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrCaptchaService.js'></script>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/plugins/datatables/dataTables.bootstrap.css">
 <script src="resource/dashboard-resource/plugins/datatables/jquery.dataTables.min.js"></script>
 <script src="resource/dashboard-resource/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="<%=contextpthval%>/JavaScriptServlet"></script>
<script type="text/javascript" language="javascript">
	$(document).ready(function() {
		
		$('#tblViewLBTWards').dataTable({
			"bJQueryUI": true,
			"aoColumns":[{"bSortable": false}, null,{"bSortable": false},{"bSortable": false}, {"bSortable": false}],
			"sPaginationType": "full_numbers"
		});
	} );
	
	resetFrom = function(){
		displayLoadingImage();
		document.forms['lbTypeWiseWards'].method = "GET"; 
		document.forms['lbTypeWiseWards'].action = "lbWiseWardforCitizen.do?<csrf:token uri='lbWiseWardforCitizen.do'/>";
		document.forms['lbTypeWiseWards'].submit();
	};
	
	
	fetchLBTWards = function(){
 		var stateCode = $('#state').val();
 		var captchaAnswer = $('#captchaAnswer').val();
 		if(stateCode == "-1"){
 			document.getElementById('errorstate').innerHTML="Please select a state.";
 			return false;
 		}
 		
 		
 		else if(captchaAnswer == ""){
 			document.getElementById('errorCaptcha').innerHTML="Please enter captcha  shown above";
 			return false;
 		}
 		displayLoadingImage();
 		document.forms['lbTypeWiseWards'].method = "post";
		document.forms['lbTypeWiseWards'].action = "lbTypeWiseWardsData.do?<csrf:token uri='lbTypeWiseWardsData.do'/>";
		document.forms['lbTypeWiseWards'].submit();
		return true; 
 	 };
 	 
 	 
	showLBWards = function(localbodyTypeCode){
		var stateCode = $('#state').val();
 		if(stateCode == "-1"){
 			document.getElementById('errorstate').innerHTML="Please select a state.";
 			return false;
 		}
 		//displayLoadingImage();
 		document.forms['lbTypeWiseWards'].lbTypeCode.value = localbodyTypeCode;
		document.forms['lbTypeWiseWards'].method = "post";
		document.forms['lbTypeWiseWards'].action = "localBodyWiseWards.do?<csrf:token uri='localBodyWiseWards.do'/>";
		document.forms['lbTypeWiseWards'].submit();
		document.getElementById('backButton').show();
		return true;
 	 };
 	 
	clearMessage = function() {
		document.getElementById('errorstate').innerHTML="";
		document.getElementById('errorCaptcha').innerHTML="";
	};
 	 
	function CallPrint() {
		document.getElementById('footer').style.display = 'block';
		document.getElementById('footer').style.visibility = 'visible';
		/* document.getElementById('footertext').style.fontSize = '13px'; */
		var printContent = document.getElementById("divData");
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
              <h3 class="box-title"><spring:message code="label.HeaderSummaryReportWard"></spring:message></h3>
              <c:if test="${!empty listLBTypeWiseWards}">
				<a id="showprint" href="#" class="pull-right"><img src='<%=contextpthval%>/images/printer_icon.png' alt="Print" border="0" onclick="CallPrint();" /></a>	
			  </c:if>
            </div><!-- /.box-header -->
                <!-- form start -->
                <form:form class="form-horizontal" action="lbTypeWiseWardsData.do" id="lbTypeWiseWards" name="lbTypeWiseWards" commandName="lbTypeWiseWards">
				<input type="hidden" name="<csrf:token-name/>"    value="<csrf:token-value uri="lbTypeWiseWardsData.do"/>" />
				   <input type="hidden" id="lbTypeCode" name="lbTypeCode"/>
					<div id="showbytext" <c:if test="${!enableSearch}">style="display: none;"</c:if>>
					 	<div class="box-header subheading" id='searchid'><spring:message htmlEscape="true" code="Label.SEARCHCRITERIA"></spring:message></div>
							<div class="form-group">
								<label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message><span class="mandatory">*</span></label>
								 <div class="col-sm-6">
								<select id="state" class="form-control" name="state" onchange="clearMessage();">
									<option value="-1"><spring:message htmlEscape="true" code="Label.SEL"/></option>
									<c:forEach var="states" items="${stateSourceList}">
										<option <c:if test="${states.statePK.stateCode == state}">selected="selected"</c:if> value="${states.statePK.stateCode}">
											<c:out value="${states.stateNameEnglish}" escapeXml="true"></c:out>
										</option>
									</c:forEach>
								</select>										
								<div id="errorstate" class="errormsg"><c:out value="${errorMsgState}" escapeXml="true"></c:out></div>
							</div>
						 </div>
						
						<div class="form-group">
							  <label for="captchaAnswer" class="col-sm-4 control-label"></label>
							     <div class="col-sm-6">
							         <img src="captchaImage" alt="Captcha Image" id ="captchaImageId"/>
							      </div>
							</div>
							                    
						<div class="form-group">
							<label for="captchaAnswer" class="col-sm-4 control-label"><spring:message code="captcha.message" htmlEscape="true" /><span class="mandatory">*</span></label>
							  <div class="col-sm-6">
							        <input type="text"  id="captchaAnswer" maxlength="5" autocomplete="off" name="captchaAnswer" onblur="clearMessage();"/>									
										<a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>
										<div id="errorCaptcha" class="errormsg">
											<c:if test="${invalidCaptcha}"><spring:message code="captcha.errorMessage" htmlEscape="true"/></c:if>
										</div>
							 </div>
								 <div class="errormsg" id="errorCaptcha" style="visibility: hidden; display: none;"><spring:message code="captcha.errorMessage"/></div>	
						</div>
					
						<div class="box-footer">
			             <div class="col-sm-offset-2 col-sm-10">
			              <div class="pull-right">
							<button type="submit" name="Submit" onclick="return validate_report();" class="btn btn-success"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message></button>
							<%-- <button type="button" name="Submit2" onclick="javascript:go('stateWiseGramPanchayats.do');" class="btn btn-warning"> <spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button> --%>
							<button type="button"  name="close" onclick="javascript:go('welcome.do');" class="btn btn-danger" ><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
							</div>
						</div>
				      </div>
					</div> 
					   
           		<c:if test="${enableLBTypeWise}">
           		<div id="divData">
           		<div class="box-body" id="showbytext">
           			<div id="resultLBType">
						<h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">
							<spring:message code="label.LocalBodyTypeWards" htmlEscape="true"/>
						</h6>				
				  <div class="table-responsive ">
				<table class="table table-striped table-bordered dataTable" width="100%" align="center" id="tblViewLBTWards" >
				  <thead>
					<tr>
						<th><spring:message code="Label.SNO"/></th>
						<th><spring:message code="Label.LGT"/></th>
						<th><spring:message code="label.LocalbodyTypeNameEnglish"/></th>
						<th><spring:message code="label.LocalbodyTypeNameLocal"/></th>
						<th><spring:message code="label.TotalWards"/></th>		
					</tr>
				</thead>
				<tbody>
					<c:forEach var="data" items="${listLBTypeWiseWards}" varStatus="sn">
						<tr style="height: 30px;">		
							<td align="center"><c:out value="${sn.count}" escapeXml="true"></c:out></td>
								<c:choose>
									<c:when test="${data[2] gt 0}">
										<td align="left"><a href="#" onclick="return showLBWards('${data[0]}');"><c:out value="${data[1]}" escapeXml="true"></c:out></a></td>
										<td><a href="#" onclick="return showLBWards('${data[0]}');"><c:out value="${data[3]}" escapeXml="true"></c:out></a></td>
										<td><a href="#" onclick="return showLBWards('${data[0]}');"><c:out value="${data[4]}" escapeXml="true"></c:out></a></td>	
										<td align="center"><a href="#" onclick="return showLBWards('${data[0]}');"><c:out value="${data[2]}" escapeXml="true"></c:out></a></td>
									</c:when>
									
									<c:otherwise>
										<td align="left"><c:out value="${data[1]}" escapeXml="true"></c:out></td>
										<td><c:out value="${data[3]}" escapeXml="true"></c:out></td>
										<td><c:out value="${data[4]}" escapeXml="true"></c:out></td>	
										<td align="center"><c:out value="${data[2]}" escapeXml="true"></c:out></td>
									</c:otherwise> 
								</c:choose>
						</tr>
					</c:forEach>
			   </tbody>
		    </table>
		    </div>
		    <div id="footer"></div>			
	     </div>
		</div>
		<div class="box-footer">
		 <div class="col-sm-offset-2 col-sm-10">
		      <div class="pull-right">
				<button type="button" id="backButton" style="display: none;" name="close" onclick="resetFrom();" class="btn btn-info" > <spring:message htmlEscape="true"  code="Button.BACK"></spring:message></button>
				<button type="button"  name="close" onclick="javascript:go('welcome.do');" class="btn btn-danger" ><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
			</div>
		 </div>
		 </div>	
	  </c:if>
    </form:form>
    <script src="/LGD/JavaScriptServlet"></script>
   </div>
</section>
</div>
</div>
</section>	
	
</body>
</html>