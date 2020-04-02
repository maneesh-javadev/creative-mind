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
<%-- <script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script> --%>

<script src="js/stateWiseUnmappedVillages.js"></script>
<script	type="text/javascript">
$(document).ready(function() {
	var list = ${SEARCH_UNMAPPED_VILLAGES_RESULTS_KEY};
	if(list == ''){
		alert("No Record Found");
	}
});
</script>
<title>

</title>

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
              <h3 class="box-title"><spring:message htmlEscape="true" code="Label.StateWiseUnmappedVillages"></spring:message>
              <c:if test="${empty SEARCH_UNMAPPED_VILLAGES_RESULTS_KEY}">
                (Villages not mapped to any village panchayats/equivalent local body)
              </c:if>
              </h3>
				<c:if test="${!empty SEARCH_UNMAPPED_VILLAGES_RESULTS_KEY }">
					<a id="showprint" href="#" style="float: right;"><img src='<%=contextPath%>/images/printer_icon.png' alt="Print" border="0" onclick="CallPrint();" /></a>				
				</c:if>
				
           </div><!-- /.box-header -->
                <!-- form start -->
                <form:form class="form-horizontal" action="stateWiseUnmappedVillagesReport.do" id="form1" name="form1" method="POST" commandName="statewiseUnmappedVillagesForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='stateWiseUnmappedVillagesReport.do'/>" />
				<input type="hidden" name="direction" id="direction" value="0" />
				<form:hidden path="pageRows" value="50" />
                	
			<div class="box-body" id="cat">
				<c:if test="${empty SEARCH_UNMAPPED_VILLAGES_RESULTS_KEY}"> 
				<div id='viewentity'>
					<div class="box-header subheading">
		              <h4><spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message><span class="mandatory">*</span></h4>
		            </div>
		                  
		                    
		                    
		                    <div id="showbydropdown" class="form-group ">
		                      <label  class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message><span class="mandatory">*</span></label>
		                      <div class="col-sm-6">
		                      	<form:select path="stateNameEnglish" class="form-control" id="ddSourceState" onchange="error_remove();">
									<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
										<form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
								</form:select>
									<div class="errormsg" id="errSelectStateName" style="visibility: hidden; display: none;"><spring:message code="error.select.SELECTSTATENAME"/><span class="mandatory">*</span></div><br>
		                     </div>
		                   </div>
		                     
		                 <div class="form-group">
		                     <label for="captchaImage" class="col-sm-4 control-label"></label>
		                       <div class="col-sm-6">
		                        <img src="captchaImage" alt="Captcha Image" id="captchaImageId" /><br>
		                       </div>
		                 </div>
		                    
		                   <div class="form-group">
		                     <label for="captchaAnswer" class="col-sm-4 control-label"><spring:message code="captcha.message"	htmlEscape="true" /><span class="mandatory">*</span></label>
		                       <div class="col-sm-6">
		                      	<form:input	path="captchaAnswers" name="captchaAnswesr" maxlength="5" id="captchaAnswer"	 autocomplete="off" />
		                      	 <a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>
								 <span class ="errormsg" id="errorcaptchaAnswer"></span>
								  <div class="errormsg">
								  <div id="errEmptyCaptcha" class="errormsg" ></div>
									<c:if test="${ captchaSuccess2 == false }">
										<spring:message code="captcha.errorMessage" htmlEscape="true" />
									</c:if>
								</div>
								<div class="errormsg" id="errorCaptcha" style="visibility: hidden; display: none;"><spring:message code="captcha.errorMessage"/></div>
		                      </div>
	                       </div>
	                       
	          <c:if test = "${flag2 eq 1}"><span id="required" class="errormsg"><spring:message code="error.norecoredfound" htmlEscape="true"></spring:message></span></c:if>
	                       
               <div class="box-footer">
                    <div class="col-sm-offset-2 col-sm-10">
                      <div class="pull-right">
						<button type="submit" name="Submit" class="btn btn-success" onclick="return validate_report();"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message></button>
				   		<button type="button" name="Submit2" class="btn btn-info" onclick="javascript:go('stateWiseUnmappedVillagesReport.do');"> <spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button>
				     	<button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:go('welcome.do');"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
				   </div>
				 </div>
			  </div>
             </div>
            </c:if>
          
				
			  <c:if test="${! empty SEARCH_UNMAPPED_VILLAGES_RESULTS_KEY}">
				<div class="box-body" id="printable">
					<h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">${message}</h6>		
					* Unmapped village means village which is not mapped to any Village Panchayats or equivalent local body<br />	
				 <div class="table-responsive ">
				<table class="table table-striped table-bordered dataTable" width="100%" align="center" id="tbl_with_brdr" >
				  <thead>
					<tr>
						<th><spring:message htmlEscape="true" code="Label.SNO"></spring:message></th>
						<th><spring:message htmlEscape="true"	code="Label.DISTRICTCODE"></spring:message></th>
						<th><spring:message htmlEscape="true" code="Label.DISTRICTNAMEINENGLISH"></spring:message></th>
						<th><spring:message htmlEscape="true"	code="Label.SUBDISTRICTCODE"></spring:message></th>
						<th><spring:message htmlEscape="true" code="Label.SUBDISTRICTNAMEENGLISH"></spring:message></th>
						<th><spring:message htmlEscape="true"	code="Label.VILLAGECODE"></spring:message></th>
						<th><spring:message htmlEscape="true" code="Label.VILLAGENAMEINENGLISH"></spring:message></th>
						<th><spring:message htmlEscape="true" code="Label.VILLAGESTATUS"></spring:message></th>
											
					</tr>
				</thead>
				<tr></tr>
				<tbody>
					<c:forEach var="listStatewiseUnmappedVillagesDetail" varStatus="listEntityRow" items="${SEARCH_UNMAPPED_VILLAGES_RESULTS_KEY}">
					  <tr>
						<td><c:out value="${(offsets*limits+listEntityRow.index+1)}" escapeXml="true"></c:out></td>
						<td><c:out value="${listStatewiseUnmappedVillagesDetail.districtCode}" escapeXml="true"></c:out></td>
						<td align="left"><c:out value="${listStatewiseUnmappedVillagesDetail.districtNameEnglish}" escapeXml="true"></c:out></td>
						<td><c:out value="${listStatewiseUnmappedVillagesDetail.subdistrictCode}" escapeXml="true"></c:out></td>
						<td align="left"><c:out value="${listStatewiseUnmappedVillagesDetail.subdistrictNameEnglish}" escapeXml="true"></c:out></td>
						<td><c:out value="${listStatewiseUnmappedVillagesDetail.villageCode}" escapeXml="true"></c:out></td>
						<td align="left"><c:out value="${listStatewiseUnmappedVillagesDetail.villageNameEnglish}" escapeXml="true"></c:out></td>
						<td align="left"><c:out value="${listStatewiseUnmappedVillagesDetail.villageStatus}" escapeXml="true"></c:out></td>
					 </tr>
				  </c:forEach>	
				</tbody>
			</table>
			</div>
			 <div class="table-responsive ">
			<table class="tbl_with_brdr" width="100%" align="center">
								  	<tr>
											<td><table width="30%" class="tbl_no_brdr" align="right">
													<tr align="right">
														<td width="150" height="30" align="right" class="pageno"><c:out value="${villageCount}" escapeXml="true"></c:out></td>
														<td width="96" align="right" class="pre"><a href="#"
															onclick="setDirection(-1)"><spring:message
																	htmlEscape="true" code="Label.PREVIOUS"></spring:message>
														</a></td>
														<td width="24" align="center" class="pageno">|</td>
														<td width="51" align="right" class="nxt tblclear"><a
															href="#" onclick="setDirection(1)"><spring:message
																	htmlEscape="true" code="Label.NEXT"></spring:message> </a>
														</td>
														<td width="20" align="right" class="nxt tblclear">&nbsp;</td>
													</tr>
												</table>
											</td>
										</tr>
			</table>
			</div>
	
		<div style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
			<label><spring:message code="Label.URL" htmlEscape="true"></spring:message>
		     <c:out value='<%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%>'/> 
			</label>
			</br><%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%><label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message>
			<c:out value='<%=df.format(new java.util.Date())%>'/></label>
			</br><label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
		</div>
		<div id="footer"></div>
				
	</div>	
				
			
			   <div class="box-footer">
                    <div class="col-sm-offset-2 col-sm-10">
                      <div class="pull-right">
					 <button type="button" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';" name="Submit3" class="btn btn-danger" ><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
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

<script>


</script>