<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;  charset=utf-8">
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrDistrictService.js'/>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrSubDistrictService.js'/>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrVillageService.js'/>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>


<script>

$(document).ready(function() {
    $('#modifystateCorrectiontable').DataTable();
} );

function manageState(url,id){
	dwr.util.setValue('stateId', id, {	escapeHtml : false	});
	$( 'form[id=form1]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
	document.getElementById('form1').submit();
}
dwr.engine.setActiveReverseAjax(true);

</script>
</head>
   <body> 
   
  <div class="box">
         <div class="box-header">
             <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.MANAGESTATE"></spring:message></h3>
              </div><!-- /.box-header -->
               <div class="box-body">
                <div id="example1_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">				  
				 <div class="row">
				  <div class="col-sm-12 ">
				   <div class="table-responsive">
				   
     <form:form action="viewstate.htm" id="form1" method="POST" commandName="statebean">	
	<%-- <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewstate.htm"/>" /> --%>	
	<c:if test="${! empty SEARCH_STATE_RESULTS_KEY}">	
					
									<table id="modifystateCorrectiontable" class="table table-bordered table-striped dataTable no-footer" width="100%" cellspacing="0">
										<thead>
											 <tr>
												<th rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></th>
												<th  rowspan="2"><spring:message htmlEscape="true" 	code="Label.STATECODE"></spring:message></th>
												<th  rowspan="2"><spring:message htmlEscape="true"  code="Label.STATENAMEINENGLISH"></spring:message></th>
												<th  rowspan="2"><spring:message htmlEscape="true"  code="Label.STATENAMEINLOCAL"></spring:message></th>
												<th colspan="2" style="text-align:center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></th>
								          	</tr>
										  	<tr >
												<th><spring:message htmlEscape="true"  code="Label.CORRECTION"></spring:message></th>
												<th><spring:message htmlEscape="true"  code="Label.CHANGE"></spring:message></th>
												<th align="center"><spring:message htmlEscape="true"  code="Label.CHANGE.EFFECTIVE.DATE"></spring:message></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="stateWiseEntityDetails" varStatus="listStateRow" items="${SEARCH_STATE_RESULTS_KEY}">
												<tr >
													<td ><c:out value="${offsets*limits+(listStateRow.index+1)}" escapeXml="true"></c:out></td>
													<td><c:out value="${stateWiseEntityDetails.stateCode}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${stateWiseEntityDetails.stateNameEnglish}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${stateWiseEntityDetails.stateNameLocal}" escapeXml="true"></c:out></td>													
													<td align="left"><a href="#" onclick="javascript:manageState('modifyStateCrbyId.htm',${stateWiseEntityDetails.stateCode});"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
													<td align="left"><a href="#" onclick="javascript:manageState('modifyStatechangebyId.htm',${stateWiseEntityDetails.stateCode});"><i class="fa fa-pencil" aria-hidden="true"></i></a></td>
													<td align="left"><a href="#" onclick="javascript:manageState('modifyStateChangeEffectiveDate.htm',${stateWiseEntityDetails.stateCode});"><i class="fa fa-pencil"  aria-hidden="true"></i></a></td>											 												
												</tr>
											</c:forEach>
										</tbody>
									</table>
				<form:hidden path="stateId" name="stateId" id="stateId"  />	
			</c:if>		   
				<c:if test="${fn:length(viewPage) > 0}"> 
				   		<c:if test="${empty SEARCH_STATE_RESULTS_KEY}">
							<div class="form-group" id="divData">
								
									
									<div class="alert alert-danger">
                                       <strong><spring:message htmlEscape="true"  code="error.NOSTATEFOUND"></spring:message></strong> 
                                  </div>
								
				     		</div>
				   		</c:if>
			    	</c:if>
				     <input type="hidden" name="direction" id="direction" value="0" />
				     <input type="hidden" name="pageType" value="D" />
			
         </form:form>
          <script src="/LGD/JavaScriptServlet"></script>	
      </div>
	 </div>
	</div>
  </div><!-- /.box-body -->
</div><!-- /.box -->
 </body>
 </html>