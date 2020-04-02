<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />


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
              <h3 class="box-title"><spring:message code="Label.rptdistrictWiseInvalidatedVillage" htmlEscape="true"></spring:message></h3>
             <c:if test="${!empty districtInvalidate }">
				<a id="showprint" class="pull-right" href="#"><img src='<%=contextPath%>/images/printer_icon.png' border="0" alt="Print" onclick="CallPrint();" /></a>									
			</c:if>
          </div><!-- /.box-header -->
                <!-- form start -->
                <form:form class="form-horizontal" action="rptDistrictWiseInvalidatedVillageDetail.do" id="form1" name="form1" method="POST" commandName="districtInvalidate">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='rptDistrictWiseInvalidatedVillageDetail.do'/>" />
				<input type="hidden" name="direction" id="direction" value="0" />
					<div id="cat">
					 <c:if test="${! empty invalidatetList}">			
				
				   		<div class="box-body" id="printable">
							<h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">
							<spring:message htmlEscape="true" code="Label.rptdistrictWiseInvalidatedVillage" /><c:out value="${message}" escapeXml="true"></c:out>
							</h6>			
						  <div class="table-responsive ">
						<table class="table table-striped table-bordered dataTable" width="100%" align="center" id="tbl_with_brdr" >
						  <thead>
							<tr>
								<th><spring:message code="Label.SNO"></spring:message></th>
								<th><spring:message code="Label.VILLAGECODE"></spring:message></th>
								<th><spring:message code="Label.VILLAGENAMEINENGLISH"></spring:message></th>
								<th><spring:message code="Label.VILLAGEVERSION"></spring:message></th>
								<th><spring:message code="Label.SUBDISTRICTCODE"></spring:message></th>
								<th><spring:message code="Label.SUBDISTRICTNAMEEN"></spring:message></th>
								<th><spring:message code="Label.UlbCode"></spring:message></th>	
								<th><spring:message code="Label.UlbName"></spring:message></th>	
							</tr>
						</thead>
						<tbody>
							<c:forEach var="getDistrictWiseInvalidatedVillage" varStatus="listEntityRow" items="${invalidatetList}">
								<tr class="tblRowB">
									<td><c:out value="${(offsets*limits+listEntityRow.index+1)}" escapeXml="true"></c:out></td>
									<td><c:out value="${getDistrictWiseInvalidatedVillage.village_code}" escapeXml="true"></c:out></td>
									<td><c:out value="${getDistrictWiseInvalidatedVillage.village_name_english}" escapeXml="true"></c:out></td>
									<td align="left"><c:out value="${getDistrictWiseInvalidatedVillage.village_version}" escapeXml="true"></c:out></td>
									<td align="left"><c:out value="${getDistrictWiseInvalidatedVillage.subdistrict_code}" escapeXml="true"></c:out></td>
									<td><c:out value="${getDistrictWiseInvalidatedVillage.subdistrict_name_english}" escapeXml="true"></c:out></td>
									<td align="left"><c:out value="${getDistrictWiseInvalidatedVillage.ulb_code}" escapeXml="true"></c:out></td>
									<td align="left"><c:out value="${getDistrictWiseInvalidatedVillage.ulb_name}" escapeXml="true"></c:out></td>
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
					<button type="button"  name="Submit3" onclick="javascript:go('welcome.do');" class="btn btn-danger" ><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
				</div>
			 </div>
		  </div>	
					</c:if>
					</div>
		          	</form:form>
					</div>
					 <c:if test="${recordlength ==0}">
						<div class="errormsg" id="divData">
							<spring:message code="Error.noresult" htmlEscape="true"></spring:message>
						</div>
					</c:if>
				</section>
				</div>
			</div>
		</section>

</body>
</html>