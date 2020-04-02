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
<script type="text/javascript" language="javascript">
function hideMessageOnKeyPressstate()
{	
	$("#OfficialAddress_msg").hide();	
	
}</script>
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
              <h3 class="box-title"><spring:message htmlEscape="true" code="Label.stateWiseGPtoVillageMappingStatus"></spring:message></h3>
              <c:if test="${!empty StatewiseGPtoVillageMappingList }">
				<a id="showprint" class="pull-right" href="#"><img src='<%=contextPath%>/images/printer_icon.png' alt="Print" border="0" onclick="CallPrint();" /></a>			
			 </c:if>
            </div><!-- /.box-header -->
                <!-- form start -->
                <form:form class="form-horizontal" action="stateWiseMappedVillagesReport.do" id="form1" name="form1" method="POST" commandName="statewiseUnmappedVillagesForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='stateWiseMappedVillagesReport.do'/>" />
				<input type="hidden" name="direction" id="direction" value="0" />
					<div id="cat">
					   <c:if test="${empty StatewiseGPtoVillageMappingList}"> 					
							<div class="box-header subheading" id='viewentity'><spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message><span class="mandatory">*</span></div>
								<div id='showbydropdown'>
									<div class="form-group">
							
									<label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message><span class="mandatory">*</span></label>
									 <div class="col-sm-6">
									<form:select path="stateNameEnglish" class="form-control" id="ddSourceState" onchange="error_remove();hideMessageOnKeyPressstate()" >
										<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
										<form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
									</form:select>
									<div class="errormsg" id="errSelectStateName" style="visibility: hidden; display: none;"><spring:message code="error.select.SELECTSTATENAME"/></div>
									<div class="errormsg" id="errSelectStatedistrictName">
										<c:if test="${ captchaSuccess2 == false }">
											<spring:message code="captcha.errorMessage" htmlEscape="true" />
										</c:if>
								   </div>
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
							        <form:input path="captchaAnswers" name="captchaAnswesr" maxlength="5" id="captchaAnswer" autocomplete="off" />								
								 <a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>	<br>						
								 <span class="mandatory" id="errorCaptcha"></span>
								 
								
								  <div class="errormsg">
									<c:if test="${ captchaSuccess2 == false }">
										<spring:message code="captcha.errorMessage" htmlEscape="true" />
									</c:if>
								 </div>
								 <div class="errormsg" id="errorCaptcha" style="visibility: hidden; display: none;"><spring:message code="captcha.errorMessage"/></div>	
							 </div>
						 </div> 
							
						
						<div class="box-footer">
			             <div class="col-sm-offset-2 col-sm-10">
			              <div class="pull-right">
							<button type="submit" name="Submit" onclick="return validate_report();" class="btn btn-success"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message></button>
							<button type="button" name="Submit2" onclick="javascript:go('stateWiseGramPanchayats.do');" class="btn btn-info"> <spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button>
							<button type="button"  name="Submit3" onclick="javascript:go('welcome.do');" class="btn btn-danger" ><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
						 </div>
						</div>
				      </div>
		           </div>
	          </c:if>
					   
           		<c:if test="${! empty StatewiseGPtoVillageMappingList}">
           		<div class="box-body" id="printable">
					<h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">
						<spring:message htmlEscape="true" code="Label.DPGPVillMapping"></spring:message>
					</h6>				
				
				<table class="table table-striped table-bordered dataTable" width="100%" align="center" id="tbl_with_brdr" >
				  <thead>
					<tr>
						<th><spring:message code="Label.SNO"></spring:message></th>
						<th><spring:message code="Label.DISTRICTPANCHYATNME"></spring:message></th>
						<th><spring:message code="Label.TotalGP"></spring:message></th>
						<th><spring:message code="Label.TotalVLbyDP"></spring:message></th>
						<th><spring:message code="Label.TotalGPMappelVil"></spring:message></th>	
						<th><spring:message htmlEscape="true" code="Label.VILLAGEMAPPEDPERCENTAGE"></spring:message></th>				
					</tr>
				</thead>
				<tbody>
					<c:forEach var="listStatewiseGPtoVillageMappingDetail" varStatus="listEntityRow" items="${StatewiseGPtoVillageMappingList}">
						<tr class="tblRowB">
							<td><c:out value="${(offsets*limits+listEntityRow.index+1)}" escapeXml="true"></c:out></td>
							<td><c:out value="${listStatewiseGPtoVillageMappingDetail.localbodyname}" escapeXml="true"></c:out></td>
							<td align="left"><c:out value="${listStatewiseGPtoVillageMappingDetail.totalgrampan}" escapeXml="true"></c:out></td>
							<td><c:out value="${listStatewiseGPtoVillageMappingDetail.toalVillage}" escapeXml="true"></c:out></td>
							<td align="left"><c:out value="${listStatewiseGPtoVillageMappingDetail.mappedVillage}" escapeXml="true"></c:out></td>
							<td align="left"><c:out value="${listStatewiseGPtoVillageMappingDetail.mappedPercent}" escapeXml="true"></c:out></td>
						</tr>
				  </c:forEach>
				   <tr> 
					 <td><spring:message htmlEscape="true"	code="Label.TOTALS"></spring:message></td>
					<td></td>
					<td><c:out value="${totalGp}" escapeXml="true"></c:out></td>
					<td><c:out value="${totalVillage}" escapeXml="true"></c:out></td>
					<td><c:out value="${totalMappedVil}" escapeXml="true"></c:out></td>
					<td><c:out value="${mappedPercent}" escapeXml="true"></c:out></td>
					
				  </tr>
				<tr></tr>
			</tbody>
		</table>			
	
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
			<button type="button"  name="Submit3" onclick="javascript:go('welcome.do');" class="btn btn-danger" ><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
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