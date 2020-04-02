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
<script>

function submitData(){
	 if($("#captchaAnswer").val()==""){
			$( '#errorcaptchaAnswer').text("Please enter the text shown above.");
			return false;
		}
		
		return true;
	}

</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript'  src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/plugins/datatables/dataTables.bootstrap.css">
<!--  <script src="resource/dashboard-resource/plugins/datatables/jquery.dataTables.min.js"></script>
 <script src="resource/dashboard-resource/plugins/datatables/dataTables.bootstrap.min.js"></script>  -->

<script src="js/stateWiseGPtoVillageMappingReport.js"></script>
<title>

</title>
<script>
$(document).ready(function() {

	$('#tbl_with_brdr').dataTable({
	        "lengthMenu": [[ 50,100, -1], [ 50,100, "All"]],
	         "scrollX": true
	    });
});

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
               <h3 class="box-title"><spring:message htmlEscape="true" text="State wise Village to GP, Equivalent Local Bodies, ULB Mapping Status"></spring:message></h3>
                  <c:if test="${!empty StatewiseGPtoVillageMappingList }">
				   <a id="showprint" class="pull-right" href="#"><img src='<%=contextPath%>/images/printer_icon.png' border="0" onclick="PrintDiv();" alt="Print" /></a>
			     </c:if>	
            </div><!-- /.box-header -->

<!-- form start -->
            <form:form class="form-horizontal" action="stateWiseGPtoVillageMappingReport.do" id="form1" name="form1" method="POST" commandName="stateWiseGPtoVillageMappingForm">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='stateWiseGPtoVillageMappingReport.do'/>" />
               <div id="cat">
               	<c:if test="${empty StatewiseGPtoVillageMappingList}">  
               	  <div id='viewentity'>	
               	    <div id='showbydropdown'>
						
						<div class="form-group">
							  <label for="captchaAnswer" class="col-sm-4 control-label"></label>
							     <div class="col-sm-6">
							         <img src="captchaImage" alt="Captcha Image" id= "captchaImageId"/>
							      </div>
							</div>
							                    
						<div class="form-group">
							<label for="captchaAnswer" class="col-sm-4 control-label"><spring:message code="captcha.message" htmlEscape="true" /><span class="mandatory">*</span></label>
							  <div class="col-sm-6">
							        <form:input path="captchaAnswers" name="captchaAnswesr" maxlength="5" id="captchaAnswer"  autocomplete="off" />								
								 <a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a><br>
								  <div class="errormsg">
								  <span class="mandatory" id="errorcaptchaAnswer"></span>
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
							<button type="submit" name="button" onclick="return submitData();" class="btn btn-success"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true" code="Button.GETDATA"></spring:message></button>
							<%-- <button type="button" name="Submit2" onclick="javascript:go('stateWiseGPtoVillageMappingReport.do');" class="btn btn-info" > <spring:message htmlEscape="true" code="Button.CLEAR"></spring:message></button> --%>
							<button type="button" name="Submit3" onclick="javascript:go('welcome.do');" class="btn btn-danger" ><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true" code="Button.CLOSE"></spring:message></button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:if>
			
	<c:if test="${! empty StatewiseGPtoVillageMappingList}">
	<div class="box-body" id="printable">
	<div id="divData">
	   <h6 id="statusmessage" style="font-size:18px; font-weight:bold; text-align:center; margin:0;"><spring:message htmlEscape="true" text="State wise Village to GP, Equivalent Local Bodies, ULB Mapping Status"></spring:message></h6><br />
			
		* All Villages means village with status.- Inhabitant / Un-Inhabitant / Urban / Forest <br />	
	 <div class="table-responsive ">		
	<table class="table table-striped table-bordered dataTable" width="100%" align="center" id="tbl_with_brdr" >
	  <thead>
	  
	  		<tr>
	  			<th colspan="2"></th>
  				<th  colspan="3" style="text-align: center;"> *All Villages</th>
  				<th  colspan="3" style="text-align: center;">Inhabitant Villages</th>
 			</tr>
	  
		<tr>
			<th style="text-align: center;" width="5%"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></th>
			<th><spring:message htmlEscape="true" text="State Name"></spring:message></th>
			<th style="text-align: right;"><spring:message htmlEscape="true" text="No. Of Villages"></spring:message></th>
			<th style="text-align: right;"><spring:message htmlEscape="true" text="No. Of Villages Mapped"></spring:message></th>
			<th style="text-align: right;"><spring:message htmlEscape="true" code="Label.VILLAGEMAPPEDPERCENTAGE"></spring:message></th>
			<th style="text-align: right;"><spring:message htmlEscape="true" text="No. Of Villages"></spring:message></th>
			<th style="text-align: right;"><spring:message htmlEscape="true" text="No. Of Villages Mapped to GPs and ULBs"></spring:message></th>
			<th style="text-align: right;"><spring:message htmlEscape="true" code="Label.VILLAGEMAPPEDPERCENTAGE"></spring:message></th>
		</tr>
	</thead>

		<tbody>
		<c:set var="totalVillages" value="0"></c:set>
		<c:set var="totalVillagesMapped" value="0"></c:set>
		<c:set var="totalInhabitantVillages" value="0"></c:set>
		<c:set var="totalInhabitantVillagesMapped" value="0"></c:set>
			<c:forEach var="listStatewiseGPtoVillageMappingDetail" varStatus="listEntityRow" items="${StatewiseGPtoVillageMappingList}">
			<c:set var="totalVillages" value="${listStatewiseGPtoVillageMappingDetail.totalVillage + totalVillages}"></c:set>
			<c:set var="totalVillagesMapped" value="${listStatewiseGPtoVillageMappingDetail.mappedVillage + totalVillagesMapped}"></c:set>
			<c:set var="totalInhabitantVillages" value="${listStatewiseGPtoVillageMappingDetail.totalInhabitantVillage + totalInhabitantVillages}"></c:set>
			<c:set var="totalInhabitantVillagesMapped" value="${listStatewiseGPtoVillageMappingDetail.mappedInhabitantVillage + totalInhabitantVillagesMapped}"></c:set>
				<tr>
					<td style="text-align: center;"><c:out value="${(offsets*limits+listEntityRow.index+1)}" escapeXml="true"></c:out></td>
					<td><c:out value="${listStatewiseGPtoVillageMappingDetail.stateName}" escapeXml="true"></c:out></td>
					<td style="text-align: right;"><c:out value="${listStatewiseGPtoVillageMappingDetail.totalVillage}" escapeXml="true"></c:out></td>
					<td style="text-align: right;"><c:out value="${listStatewiseGPtoVillageMappingDetail.mappedVillage}" escapeXml="true"></c:out></td>
					<td style="text-align: right;"><c:out value="${listStatewiseGPtoVillageMappingDetail.mappedPercent}" escapeXml="true"></c:out></td>
					<td style="text-align: right;"><c:out value="${listStatewiseGPtoVillageMappingDetail.totalInhabitantVillage}" escapeXml="true"></c:out></td>
					<td style="text-align: right;"><c:out value="${listStatewiseGPtoVillageMappingDetail.mappedInhabitantVillage}" escapeXml="true"></c:out></td>
					<td style="text-align: right;"><c:out value="${listStatewiseGPtoVillageMappingDetail.inhabitantVillageMappedPercent}" escapeXml="true"></c:out></td>
				</tr>
		  </c:forEach>
		  </tbody>
		  <c:set var="percentageOfMappedVillages" value="${(totalVillagesMapped / totalVillages)*100}"></c:set>
		  <c:set var="percentageOfMappedVillagesInhabitant" value="${(totalInhabitantVillagesMapped / totalInhabitantVillages)*100}"></c:set>
		  <tfoot>
		  <tr>
		  	<td></td>
		  	<td><b>Total</b></td>
		  	<td style="text-align: right;">${totalVillages}</td>
		  	<td style="text-align: right;">${totalVillagesMapped}</td>
		 	<td style="text-align: right;"><fmt:formatNumber type="number" maxFractionDigits="2" value="${percentageOfMappedVillages}"/></td>
		 	<td style="text-align: right;">${totalInhabitantVillages}</td>
		  	<td style="text-align: right;">${totalInhabitantVillagesMapped}</td>
		 	<td style="text-align: right;"><fmt:formatNumber type="number" maxFractionDigits="2" value="${percentageOfMappedVillagesInhabitant}"/></td>
		  </tr>
		  </tfoot>
		
	</table>
 </div>
		
		<div style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
			<label><spring:message code="Label.URL" htmlEscape="true"></spring:message>
			<c:out value='<%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%>'/> </label>
			</br><%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%><label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message>
			<c:out value="<%=df.format(new java.util.Date())%>"/></label>
			</br><label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
		</div>
		<div id="footer"></div>
	</div>	
	</div>
	
	
	<div class="box-footer">
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