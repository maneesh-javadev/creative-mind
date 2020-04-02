<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<title>E-Panchayat</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<script src="js/departmentAdminUnit.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type="text/javascript" language="javascript">dwr.engine.setActiveReverseAjax(true);</script>
<script> 
$(document).ready(function() {
	$('#modifyAdminUnitLevel').dataTable({
		
	});
	
	$('#modelyes').on('click', function () {
	     
	    document.forms['form1'].method = "post";
		document.forms['form1'].action = "deleteDeptAdmitUnitDraft.htm?<csrf:token uri='deleteDeptAdmitUnit.htm'/>";
		document.forms['form1'].submit();
      
     })
	
} );

function manageAdminUnit(url,id)
{
	dwr.util.setValue('adminUnitCode', id, {	escapeHtml : false	});
	document.getElementById("unitCode").value = id;
	document.getElementById("entityType").value = 0;
	if(url!=null && url=='deleteDeptAdmitUnitDraft'){
		
		 $("#model-confirm").modal('show');
		 
		
	/* 	$("#dialog:ui-dialog").dialog("destroy");
		$("#dialog-confirm").dialog({
			resizable : false,
			height : 140,
			modal : true,
			buttons : {
				"Yes" : function() {
					document.forms['form1'].method = "post";
					document.forms['form1'].action = "deleteDeptAdmitUnitDraft.htm?<csrf:token uri='deleteDeptAdmitUnit.htm'/>";
					document.forms['form1'].submit();
					$(this).dialog("close");
				},
				Cancel : function() {
					$(this).dialog("close");
				}
			}
		}); */
	
	}else{
		document.getElementById('form1').action = url;
		document.getElementById('form1').submit();
	}
}

</script>
<title><spring:message code="Label.MANAGEDRAFTUNIT" htmlEscape="true" text="Manage Draft Unit Levels "></spring:message></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>
<body>
	
	<section class="content">
	
		<div class="row">
        	<section class="col-lg-12 ">
	              	<div class="box ">
           				<div class="box-header with-border">
		                	<h3 class="box-title"><spring:message code="Label.MANAGEDRAFTUNIT" htmlEscape="true" text="Manage Draft Unit Levels"></spring:message></h3>
		                </div>
		                <div class="box-body">
	                    	<form:form action="view_AdminLevelDetail.htm" id="form1" method="POST" commandName="adminLevelBean">
								<form:hidden path="adminUnitCode" value="${deptAdminUnitForm.adminUnitCode}" />
								<input type="hidden" name="stateCode" id="stateCode" value="<c:out value='${stateCode}'  escapeXml='true'></c:out>" />
								<input type="hidden" name="unitCode" id="unitCode" />
								<input type="hidden" name="entityType" id="entityType" />
								<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="view_AdminLevelDetail.htm"/>" />
								<c:if test="${! empty DEPT_ADMIN_LEVEL}">
									<div class="frmpnlbg" id="divData">
									<table class="table table-striped table-bordered " cellspacing="0" width="100%" id="modifyAdminUnitLevel">
											<thead>
												<tr>
													<th rowspan="2"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></th>
													<th rowspan="2"><spring:message code="Label.AdminUnitCode" text="Administrative Unit Level Code"></spring:message>
														<th rowspan="2"><spring:message code="Label.AdminUnitLevelEng" text="Administrative Unit Level (In English)"></spring:message>
															<th rowspan="2"><spring:message code="Label.AdminUnitLevelLoc" text="Administrative Unit Level (In Local)"></spring:message>
																<th colspan="4" align="center"><spring:message htmlEscape="true" code="Label.ACTION"></spring:message></th>
												</tr>
												<tr>
													<th align="right"><spring:message htmlEscape="true" code="Label.EDIT"></spring:message></th>
													<th align="center"><spring:message htmlEscape="true" code="Label.PUBLISH"></spring:message></th>
													<th align="center"><spring:message htmlEscape="true" code="Label.DELETE"></spring:message></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="adminUnitLevelDetail" varStatus="listAdminRow" items="${DEPT_ADMIN_LEVEL}">
													<tr>
														<td style="text-align: center"><c:out value="${offsets*limits+(listAdminRow.index+1)}" escapeXml="true"></c:out></td>
														<td style="text-align: center"><c:out value="${adminUnitLevelDetail.adminUnitCode}" escapeXml="true"></c:out></td>
														<td style="text-align: center"><c:out value="${adminUnitLevelDetail.adminLevelNameEng}" escapeXml="true"></c:out></td>
														<td style="text-align: center"><c:out value="${adminUnitLevelDetail.adminLevelNameLocal}" escapeXml="true"></c:out></td>
														<td width="8%" style="text-align: center"><a href="#" onclick="javascript:manageAdminUnit('view_modifyDeptAdmitUnitDraft.htm',${adminUnitLevelDetail.adminUnitCode});"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
														<td width="8%" style="text-align: center"><a href="#"><img src="images/publish.png" onclick="javascript:manageAdminUnit('publish_AdminLevelDetailDraft.htm',${adminUnitLevelDetail.adminUnitCode});" width="18" height="19"
																border="0" /></a></td>
														<td width="8%" style="text-align: center"><a href="#" onclick="javascript:manageAdminUnit('deleteDeptAdmitUnitDraft',${adminUnitLevelDetail.adminUnitCode});"><i class="fa fa-times" aria-hidden="true"></i></a></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</c:if>
								
								<c:if test="${fn:length(viewPage) > 0}">
					<c:if test="${empty DEPT_ADMIN_LEVEL}">
						<div class="frmpnlbg" id="divData">
							<div class="frmtxt">
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td colspan="4" align="center"><label> <spring:message htmlEscape="true" code="error.no.rec.found.admin.level.draft" text="No Draft Administrative Unit Level is defined for" /> <c:choose>
													<c:when test="${stateCode > 0}">
														<esapi:encodeForHTMLAttribute>${stateName}</esapi:encodeForHTMLAttribute>
														<spring:message htmlEscape="true" code="Label.STATE" />
													</c:when>
													<c:otherwise>
														<spring:message htmlEscape="true" code="Label.CENTER" />
													</c:otherwise>
												</c:choose>
										</label></td>
									</tr>
								</table>
							</div>
						</div>
					</c:if>
				</c:if>
				<input type="hidden" name="direction" id="direction" value="0" />
				<input type="hidden" name="pageType" value="D" />
			<!-- 	<div id="dialog-confirm" title="Administrative Unit Level?" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span> Are you confirmed to Delete Draft Administrative Unit Level ?
					</p>
				</div> -->
				
								
								
	                    	</form:form>
	                    	<script src="/LGD/JavaScriptServlet"></script>
	                    	
	                    	<div class="box-footer">
				                     <div class="col-sm-offset-2 col-sm-10">
				                       <div class="pull-right">
											<button type="button" name="Submit6" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
				                        </div>
				                     </div>   
                  				</div>
	                    	
	                    	
	                    </div>
					</div>
			</section>
		</div>
	</section>
	<!-- --------------model start-----				 -->	
			  <div class="modal fade" id="model-confirm" role="dialog">
			    <div class="modal-dialog">
			    
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h4 class="modal-title">Administrative Unit Level</h4>
			        </div>
			        <div class="modal-body" id="customAlertbodyy">
			           <p>Are you confirmed to Delete Draft Administrative Unit Level ?</p>
			        </div>
			        <div class="modal-footer">
			          <button type="button" class="btn btn-default" id="modelyes" data-dismiss="modal" >YES</button>
			          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			        </div>
			      </div>
			      
			    </div>
			  </div>
			<!--   ----model end---- -->
	
	
</body>
</html>