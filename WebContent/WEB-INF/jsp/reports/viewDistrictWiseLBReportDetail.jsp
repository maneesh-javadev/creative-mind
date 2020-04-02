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
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/plugins/datatables/dataTables.bootstrap.css">
 <script src="resource/dashboard-resource/plugins/datatables/jquery.dataTables.min.js"></script>
 <script src="resource/dashboard-resource/plugins/datatables/dataTables.bootstrap.min.js"></script>

<script src="js/stateWiseUnmappedVillages.js"></script>
<script type="text/javascript" language="javascript">
function hideMessageOnKeyPressstate()
{	
	$("#OfficialAddress_msg").hide();	
	
}
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
 <div class="row"  id="frmcontent">
     <!-- main col -->
	<div class="container">
	<section class="col-lg-12 prebox">
     	<div class="box">
            <div class="box-header with-border">
              <h3 class="box-title"><spring:message code="Label.distwiselbreport" htmlEscape="true"></spring:message></h3>
              <c:if test="${!empty StatewiseGPtoVillageMappingList }">
				<a id="showprint" href="#"><img src='<%=contextPath%>/images/printer_icon.png' border="0" onclick="CallPrint();" /></a>				
			</c:if>
            </div><!-- /.box-header -->
                <!-- form start -->
                <form:form class="form-horizontal" action="stateWiseMappedVillagesReport.do" id="form1" name="form1" method="POST" 	commandName="consolidateReport">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='stateWiseMappedVillagesReport.do'/>" />
				<input type="hidden" name="direction" id="direction" value="0" />
					<div id="cat">
						<c:if test="${! empty reportList}">
						
		           		<div class="box-body" >
						  <h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">District Wise Local Body Report</h6>				
						    <div class="table-responsive ">
						    
						    <table class="table table-striped table-bordered dataTable" width="100%" align="center" id="tbl_with_brdr" >
							  <thead>
								<tr>
									<th><spring:message code="Label.SNO"></spring:message></th>
									<th><spring:message code="Label.LOCALBODYCODE"></spring:message></th>
									<th><spring:message code="Label.LOCALGOVTBODYNAME"></spring:message></th>
									<th><spring:message code="Label.LOCALBODYTYPENAME"></spring:message></th>
									<th><spring:message code="Label.coveredvillage"></spring:message> </th>		
									<th><spring:message code="Label.parentpanchayat"></spring:message></th>		
								</tr>
							</thead>
							<tbody>
								<c:forEach var="listStatewiseGPtoVillageMappingDetail" varStatus="listEntityRow" items="${reportList}">
								  <tr class="tblRowB">
									<td><c:out value="${(offsets*limits+listEntityRow.index+1)}" escapeXml="true"></c:out></td>
									<td><c:out value="${listStatewiseGPtoVillageMappingDetail.local_body_code}" escapeXml="true"></c:out></td>
									<td align="left"><c:out value="${listStatewiseGPtoVillageMappingDetail.local_body_name_english}" escapeXml="true"></c:out></td>
									<td align="left"><c:out value="${listStatewiseGPtoVillageMappingDetail.local_body_type_name}" escapeXml="true"></c:out></td>
									<td><c:out value="${listStatewiseGPtoVillageMappingDetail.village_name}" escapeXml="true"></c:out></td>
									<td align="left"><c:out value="${listStatewiseGPtoVillageMappingDetail.parent_name}" escapeXml="true"></c:out></td>
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
					<div  id="footer"></div>	
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