<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/plugins/datatables/dataTables.bootstrap.css">
 <script src="resource/dashboard-resource/plugins/datatables/jquery.dataTables.min.js"></script>
 <script src="resource/dashboard-resource/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script type='text/javascript'  src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrParlimentService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrAssemblyService.js'></script>
<script src="js/rptMappedGPNWardforPCAC.js"></script>
<script src="js/common.js"></script>
<script language="javascript">

$(document).ready(function() {

	$('#example').dataTable({
	        "lengthMenu": [[ 50,100, -1], [ 50,100, "All"]],
	         "scrollX": true
	    });
});


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

function hideError() {
	$("#error_selectac").hide();
	$("#error_selectpc").hide();
	$("#error_selectstate").hide();
	
	$("#error_selectpc1").hide();
	$("#error_selectstate1").hide();
	}
function loadMe() {
		var pcobj='<c:out value="${rptMappedGPNWardforPCACForm.pcCheck}" escapeXml="true"></c:out>';
		//alert(pcobj);
		hideError();
		if(pcobj=="true") {
			document.getElementById('showpc').style.visibility = 'visible';
			document.getElementById('showpc').style.display = 'block';
			document.getElementById('showac').style.visibility = 'hidden';
			document.getElementById('showac').style.display = 'none';
			document.getElementById('selectPC').checked=true;
			}
		else if(pcobj=="false") {
			document.getElementById('showac').style.visibility = 'visible';
			document.getElementById('showac').style.display = 'block';
			document.getElementById('showpc').style.visibility = 'hidden';
			document.getElementById('showpc').style.display = 'none';
			document.getElementById('selectAC').checked=true;
			}
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
 		<div class="row" id="frmcontent">
     <!-- main col -->
			<div class="container">
			  <section class="col-lg-12 prebox">
			     	<div class="box">
			            <div class="box-header with-border">
			              <h3 class="box-title"><spring:message htmlEscape="true" code="Label.mappedLBWARDPCAC"></spring:message></h3>
				              <c:if test="${!empty pcACMappingforLBWardList}">			
								 <a id="showprint" class="pull-right" href="#"><img src='<%=contextPath%>/images/printer_icon.png' border="0" onclick="CallPrint();" /></a> </td>
							  </c:if>
			            </div>
			            <div class="box-header subheading">
								<h5><spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message><span class="mandatory">*</span></h5>
		                 </div>
			            <!-- /.box-header -->
			            
			            <form:form class="form-horizontal" action="rptMappedGPNWardforPCAC.do" id="form1" name="form1" method="POST" commandName="rptMappedGPNWardforPCACForm">
							<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='rptMappedGPNWardforPCAC.do'/>" />
							<form:input type="hidden" path="pcCheck" id="pcCheck" />
							<input type="hidden" name="flag1" id="flag1" value="<c:out value='${flag1}' escapeXml='true'></c:out>"/>
							<c:if test="${empty pcACMappingforLBWardList}">		
							
							<div class="box-body">
								
									<h4><spring:message htmlEscape="true" code="Label.mappedLBWARDPCAC"></spring:message></h4>
								
	                	
			                	 <div class="form-group">
					               <label class="col-sm-4 control-label"></label>
					                 <div class="col-sm-6">
				                     	<input type="radio" class="radio-inline" name="correctionRadio" id='selectPC' onclick='toggledisplay("selectPC","selectAC");'/>
											<spring:message htmlEscape="true" code="Label.SEARCHFORPARILAMENT" text="Search for Parliament"></spring:message>
										<input type="radio" class="radio-inline" name="correctionRadio" id='selectAC' onclick='toggledisplay("selectPC","selectAC");'/>
											<spring:message htmlEscape="true" code="Label.SEARCHFORASSEMBLY" text="Search for Assembly"></spring:message>
										<input type="radio" style="display: none" name="correctionRadio" checked>
					                </div>
					             </div>
							
						<div id="showpc" style="display: none">
							 <div class="form-group">
			                    <label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message><span class="mandatory">*</span></label>
			                    <div class="col-sm-6">
			                     	<form:select path="stateNameEnglish" class="form-control" id="ddSourceState" onchange="getListParliment(this.value);hideError();">
										<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
										<form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
									</form:select>
									<div class="errormsg" id="error_selectstate"><spring:message htmlEscape="true" code="error.select.SELECTSTATENAME"></spring:message><span class="mandatory">*</span></div>
									<div id="errSelectStateName" class="errormsg"></div>
			                    </div>
			                </div>
		                    
		                    
			                    <div class="form-group ">
			                      <label class="col-sm-4 control-label"><spring:message code="Label.SELECTPARLIAMENTCONSTITUENCY" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
			                      <div class="col-sm-6">
			                      	<form:select path="parlimentNameEnglish" class="form-control" id="parliment" onchange ="hideError();">
										<form:option value=""><spring:message code="Label.SELECT" htmlEscape="true"></spring:message></form:option>
									</form:select>
									<div class="errormsg" id="error_selectpc"><spring:message htmlEscape="true" code="Error.PARLIAMENTSELECT"></spring:message></div>
			                     </div>
			                   </div>
	                       
			                  <div class="form-group">
			                     <label for="captchaImage" class="col-sm-4 control-label"></label>
			                       <div class="col-sm-6">
			                        <img src="captchaImage" alt="Captcha Image"  id="captchaImageId"/><br>
			                       </div>
			                   </div>
		                    
			                   <div class="form-group">
			                     <label for="captchaAnswer" class="col-sm-4 control-label"><spring:message code="captcha.message" htmlEscape="true" /><span class="mandatory">*</span></label>
			                       <div class="col-sm-6">
			                      	<form:input path="captchaAnswer" name="captchaAnswer" maxlength="5"  autocomplete="off" />
			                      	<a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>
										 <div id="errEmptyCaptcha" class="errormsg" ></div>
										<div class="errormsg">
											<c:if test="${ captchaSuccess2 == false }"><spring:message code="captcha.errorMessage" htmlEscape="true" /></c:if>
										</div>
		                    	</div>
						  </div>
					  
							<div class="box-footer">
			                     <div class="col-sm-offset-2 col-sm-10">
			                       <div class="pull-right">
			                      		<button type="submit" name="Submit" class="btn btn-success" onclick ="return validatepc();"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true" code="Button.GETDATA"></spring:message></button>
			                            <button type="button" name="Submit3" class="btn btn-danger " onclick="javascript:go('welcome.do');"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true" code="Button.CLOSE"></spring:message></button>
			                        </div>
			                     </div>   
			                  </div>
                				<c:if test = "${flag3 eq 1}"><span id="required" class="errormsg">No Record Exist For this Parliment</span></c:if>
               			 </div>
          			  
          			  
          			  
          			  
          			  	<div class="" id="showac" style="visibility: hidden; display: none">
							
		                 <div class="form-group">
		                     <label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message><span class="mandatory">*</span></label>
		                       <div class="col-sm-6">
			                     	<form:select htmlEscape="true" path="stateNameEnglish" class="form-control" id="ddSourceState1" onchange="getListParlimentac(this.value);hideError();" >
										<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
										<form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
									</form:select> 
									<div class="errormsg" id="error_selectstate1"><spring:message htmlEscape="true" code="error.select.SELECTSTATENAME"></spring:message><span class="mandatory">*</span></div> 
					        		<div id="errSelectStateName" class="errormsg"></div>
		                    	</div>
		                   </div>
		                    
		                    
		                    <div class="form-group ">
		                      <label class="col-sm-4 control-label"><spring:message code="Label.SELECTPARLIAMENTCONSTITUENCY" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
		                      <div class="col-sm-6">
		                      	<form:select path="parlimentNameEnglish" class="form-control" id="parlimentac" onchange="getListAssembly(this.value);hideError();">
									<form:option value=""><spring:message code="Label.SELECT" htmlEscape="true"></spring:message></form:option>
								</form:select>
								<div class="errormsg" id="error_selectpc1"><spring:message htmlEscape="true" code="Error.PARLIAMENTSELECT"></spring:message></div>
		                     </div>
		                   </div>
	                       
	                        <div class="form-group ">
		                      <label class="col-sm-4 control-label"><spring:message code="Label.SELECTASSEMBLYCONSTITUENCY" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
		                      <div class="col-sm-6">
		                      	<form:select path="acNameEnglish" class="form-control" id="assembly" onchange="hideError();">
									<form:option value=""><spring:message code="Label.SELECT" htmlEscape="true"></spring:message></form:option>
								</form:select>
								<div class="errormsg" id="error_selectac"><spring:message htmlEscape="true" code="Error.ASSEMBLYSELECT"></spring:message></div>
		                     </div>
		                   </div>
	                       
		                   <div class="form-group">
		                     <label for="captchaImage" class="col-sm-4 control-label"></label>
		                      <div class="col-sm-6">
		                      	 <img src="captchaImage" alt="Captcha Image"  id="captchaImageId1"/><br>
		                      </div>
		                   </div>
		                    
		                   <div class="form-group">
		                     <label for="captchaAnswers" class="col-sm-4 control-label"><spring:message code="captcha.message"	htmlEscape="true" /><span class="mandatory">*</span></label>
		                       <div class="col-sm-6">
		                         <form:input path="captchaAnswers" name="captchaAnswers" maxlength="5"  autocomplete="off" />
		                         <a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>
								 <div id="errEmptyCaptcha" class="errormsg" ></div>
								 <div id="errEmptyCaptcha" class="errormsg" ></div>
									<div class="errormsg">
									
										<c:if test="${ captchaSuccess2 == false }"><spring:message code="captcha.errorMessage" htmlEscape="true" /></c:if>
									</div>
	                    	   </div>
					  	  </div>
					  
						  <div class="box-footer">
		                     <div class="col-sm-offset-2 col-sm-10">
		                       <div class="pull-right">
		                      		<button type="submit" name="Submit" class="btn btn-success " onclick ="return validateac();"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true" code="Button.GETDATA"></spring:message></button>
		                            <button type="button" name="Submit3" class="btn btn-danger " onclick="javascript:go('welcome.do');"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true" code="Button.CLOSE"></spring:message></button>
		                        </div>
		                     </div>   
		                  </div>
                		<c:if test="${flag2 eq 1}"><span id="required" class="errormsg">No Record Exist For this Assembly</span></c:if>
               		</div>
          	 	  </div>
	    		</c:if>
			  
			  	<c:if test="${! empty pcACMappingforLBWardList}">
			  	<h4 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;"><spring:message htmlEscape="true" code="Label.mappedLBWARDPCAC"></spring:message></h4>
				  <div class="box-body" id="divData">
					
					   			
				 <div class="table-responsive ">
				<table class="table table-striped table-bordered dataTable" id="example" >
				  <thead>
					<tr>
						<th><spring:message htmlEscape="true" code="Label.SNO"></spring:message></th>
						<th><spring:message htmlEscape="true" code="Label.PARLIAMENTCONSTITUENCY"></spring:message></th>
						<th><spring:message htmlEscape="true" code="Label.ASSEMBLYCONSTITUENCY"></spring:message></th>
						<th><spring:message htmlEscape="true" code="Label.DISTRICTCODE"></spring:message></th>
						<th><spring:message htmlEscape="true" code="Label.DISTRICTNAME"></spring:message></th>		
						<th><spring:message htmlEscape="true" code="Label.SUBDISTRICTCODE"></spring:message></th>	
						<th><spring:message htmlEscape="true" code="Label.SUBDISTRICTNAME"></spring:message></th>
						<th><spring:message htmlEscape="true" code="Label.VILLAGECODE"></spring:message></th>
						<th><spring:message htmlEscape="true" code="Label.VILLAGENAME"></spring:message></th>
						<th><spring:message htmlEscape="true" code="Label.LOCALBODYCODE"></spring:message></th>
						<th><spring:message htmlEscape="true" code="Label.LOCALGOVTBODYNAME"></spring:message></th>		
<%-- 						<th><spring:message htmlEscape="true" code="Local Body Type"></spring:message></th>			 --%>
						<th><spring:message htmlEscape="true" code="Label.WARDNUMBER"></spring:message></th>
						<th><spring:message htmlEscape="true" code="Label.WARDNAME"></spring:message></th>
<%-- 						<th><spring:message htmlEscape="true" code="Label.BLOCKCODE"></spring:message></th>		 --%>
<%-- 						<th><spring:message htmlEscape="true" code="Label.BLOCKNAME"></spring:message></th>		 --%>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="pcACMappingforLBWardDetails" varStatus="pcACMappingforLBWarRow" items="${pcACMappingforLBWardList}">
						<tr> 
							<td > <c:out value="${pcACMappingforLBWarRow.index+1}" escapeXml="true"></c:out></td> 
							<td ><c:out value="${pcACMappingforLBWardDetails.parliamentConstituency}" escapeXml="true"></c:out></td>
							<td><c:out value="${pcACMappingforLBWardDetails.assemblyConstituency}" escapeXml="true"></c:out></td>
							<td><c:out value="${pcACMappingforLBWardDetails.districtCode}" escapeXml="true"></c:out></td>
							<td><c:out value="${pcACMappingforLBWardDetails.districtNameEnglish}" escapeXml="true"></c:out></td>
							<td><c:out value="${pcACMappingforLBWardDetails.subdistrictCode}" escapeXml="true"></c:out></td>
							<td><c:out value="${pcACMappingforLBWardDetails.subdistrictNameEnglish}" escapeXml="true"></c:out></td>
							<td><c:out value="${pcACMappingforLBWardDetails.villageCode}" escapeXml="true"></c:out></td>
							<td><c:out value="${pcACMappingforLBWardDetails.villageNameEnglish}" escapeXml="true"></c:out></td>
							<td><c:out value="${pcACMappingforLBWardDetails.localBodyCode}" escapeXml="true"></c:out></td>
							<td><c:out value="${pcACMappingforLBWardDetails.localBodyNameEnglish}" escapeXml="true"></c:out></td>
<%-- 							<td><c:out value="${pcACMappingforLBWardDetails.localBodyTypeName}" escapeXml="true"></c:out></td> --%>
							<td><c:out value="${pcACMappingforLBWardDetails.wardCode}" escapeXml="true"></c:out></td>
							<td><c:out value="${pcACMappingforLBWardDetails.wardNameEnglish}" escapeXml="true"></c:out></td>
<%-- 							<td><c:out value="${pcACMappingforLBWardDetails.blockCode}" escapeXml="true"></c:out></td> --%>
<%-- 							<td><c:out value="${pcACMappingforLBWardDetails.blockNameEnglish}" escapeXml="true"></c:out></td> --%>
							<%-- <td><c:out value="${pcACMappingforLBWardDetails.coverageType}" escapeXml="true"></c:out></td> --%>
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
						<button type="button" name="Submit3" onclick="javascript:go('welcome.do');" class="btn btn-danger"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
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
