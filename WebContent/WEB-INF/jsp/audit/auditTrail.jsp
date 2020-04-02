
<%@include file="../common/taglib_includes.jsp"%>


<script type="text/javascript" class="init">
//jquery pagination  
 $(document).ready(function() {
	 $('#example').dataTable({
	        "lengthMenu": [[ 50,100, -1], [ 50,100, "All"]],
	         "scrollX": true
	    }); 
}); 

	
</script>


<section class="content">

 <!-- Main row -->
 <div class="row">
     <!-- main col -->
	<div class="container">
	<section class="col-lg-12 prebox">
     	<div class="box">
            <div class="box-header with-border">
              <h3 class="box-title"><c:out value="Audit Trail" escapeXml="true"/></h3>
            </div><!-- /.box-header -->
                
                <!-- form start -->
                <form:form class="form-horizontal" >
				
					
					
						<div class="box-body">
							 
				                   <table  id="example"  class="data_grid">
										<thead>
											<tr>
												<th ><spring:message code="Label.SNO"/></th>
												<th ><c:out value="Action Annotation" escapeXml="true"/>  </th>
												<th ><c:out value="Action URL" escapeXml="true"/></th>
												<th ><c:out value="Action Date" escapeXml="true"/></th>
												<th ><c:out value="User Login Id" escapeXml="true"/></th>
												<th ><c:out value="IP Address" escapeXml="true"/></th>
												<th ><c:out value="User Agent" escapeXml="true"/></th>
											</tr>
										</thead>
										
										<tbody>
										 	<c:forEach var="listStateDetails" varStatus="rowstatus" items="${auditTrailLGDList}">
												<tr >
													<td width="6%"><c:out value="${rowstatus.count}"/></td>
													<td><c:out value="${listStateDetails.actionAnnotation}"></c:out></td>
													<td align="left"><c:out value="${listStateDetails.actionUrl}"></c:out></td>
													<td align="left"><c:out value="${listStateDetails.actionDate}"></c:out></td>
													<td align="left"><c:out value="${listStateDetails.userLoginId}"></c:out></td>
													<td align="left"><c:out value="${listStateDetails.ipaddress}"></c:out></td>
													<td align="left"><c:out value="${listStateDetails.userAgent}"></c:out></td>
													
												</tr>
											</c:forEach>
										</tbody>
									</table>
				              
				            </div>
				
				<!-- Button for Page after POST method @Ashish 18 aUG -->	
					  
				 <div class="box-footer" id="showbutton">
                    <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
                            <button type="button" name="Submit3" class="btn btn-danger " value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
					   </div>
					 </div>
				  </div>
					  
					 	
				  </form:form>
			</div>
 <div class="modal fade" id="customAlert" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Alert Massage</h4>
        </div>
        <div class="modal-body" id="customAlertbody">
         
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Ok</button>
        </div>
      </div>
      
    </div>
  </div>	
		</section>
		</div>
	</div>
</section>





	