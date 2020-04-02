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
<!-- <link href="HTML_panchayat - 2.2/css/panchayat_main.css" rel="stylesheet" type="text/css" /> -->
<script src="js/departmentAdminUnit.js"></script>

<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>


<!-- <script type="text/javascript" language="javascript">
		dwr.engine.setActiveReverseAjax(true);
	</script> -->

<script> 
$(document).ready(function() {
	var stId = '${stId}';
	if(stId!=null && stId!=0 && stId!=""){
		document.getElementById('statePopup').style.display = 'block';
	}
	var type= '${viewType}';
	if (type == 0) {
		document.getElementById('selectEntityType').style.visibility = 'visible';
		document.getElementById('selectEntityType').style.display = 'block';
		document.getElementById('manageEntity').style.visibility = 'hidden';
		document.getElementById('manageEntity').style.display = 'none';
	} else if (type == 1) {
		document.getElementById('manageEntity').style.visibility = 'visible';
		document.getElementById('manageEntity').style.display = 'block';
		document.getElementById('selectEntityType').style.visibility = 'hidden';
		document.getElementById('selectEntityType').style.display = 'none';
	}
	/* $('#modifyAdminUnitLevel').dataTable({
		
		"bScrollCollapse": true,
		"bPaginate": true,
		"aoColumnDefs": [
			{ "sWidth": "10%", "aTargets": [ -1 ] }
		],
		"bJQueryUI": true,
		"aaSorting": [],
		"aoColumns": [
						null,
						null,
						null,
						{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false },
			  			],
		"sPaginationType": "full_numbers"
	}); */
	
	
	    $('#modifyAdminUnitLevel').DataTable();
	
	/* added by kirandeep on 19/05/2015 Issue in Manage Administrative Unit Entities */
	
	/* $("#dialog-message").dialog({
		autoOpen : false,
		height : 450,
		width : 650,
		modal : true,
	
		show : {
			effect : "blind",
			duration : 1000,
		},
		hide : {
			effect : "explode",
			duration : 1000
		}
	}); */
	
	    $("#modelyes").click(function() {			
			document.forms['form1'].method = "post";
	  		document.forms['form1'].action = "deleteDeptAdmitUnit.htm?<csrf:token uri='deleteDeptAdmitUnit.htm'/>";
	  		document.forms['form1'].submit();
	  	});
} );
function manageAdminUnit(url,id)
{
	dwr.util.setValue('adminUnitEntityCode', id, {	escapeHtml : false	});
	document.getElementById("unitCode").value = id;
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}
</script>

<c:if test="${isdeptAdminEntityUpdate}">
<script>
$(function() {		
	$('#sucessAlert').modal('show');
	
});
</script>
</c:if>

<title><spring:message code="Label.ViewAdminUnitEntity" htmlEscape="true" text="View Admin Unit Entity "></spring:message></title>
</head>
<body>
	<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                    <form:form action="view_AdminLevelDetail.htm" id="form1" method="POST" commandName="adminLevelBean" class="form-horizontal">
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="view_AdminLevelDetail.htm"/>" />
						<form:hidden path="adminUnitEntityCode" value="${deptAdminUnitForm.adminUnitEntityCode}" />
						<input type="hidden" name="stateCode" id="stateCode" value="<c:out value='${stateCode}'  escapeXml='true'></c:out>" />
						<input type="hidden" name="unitCode" id="unitCode" />
						<input type="hidden" name="entityType" id="entityType" />
						<input type="hidden" value="<c:out value='${districtCode}' escapeXml='true'></c:out>" id="districtCode">
						<input type="hidden" name="overlappValue" id ='overlappValue' value='${isExistOverlap}' />
						 <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="view_AdminLevelDetail.htm"/>" />
						  <input type="hidden" name="parentCategory" id ='parentCategory' value='${parentCategory}' />
				 
                    <div class="box">
                            <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.ViewAdminUnitEntity" htmlEscape="true" text="View Admin Unit Entity "></spring:message></h3>
                                  
                                </div>
                        <div id="selectEntityType">   
                                 <div class="box-header subheading">
                                    <h4><spring:message code="Label.GENDETAILENTITY" htmlEscape="true" text="General Details of Admin Unit Entity"></spring:message></h4>
                                </div>
                        <div class="box-body">
                            <div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message code="Label.SELDeptAdministrationUNIT" text="Type of Administrative unit Level" htmlEscape="true"></spring:message> <span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								 <form:select htmlEscape="true" path="adminUnitLevelCode" class="form-control" id="unitLevelCode" onchange="getUnitLevelEntity();getOverlappingValue(this);" >
													<form:option value="0">
														<spring:message code="Label.SELECT" text="-slect"></spring:message>
													</form:option>
													<form:options items="${parentAdminEntity}" itemLabel="adminLevelNameEng" itemValue="adminUnitCode" />
												</form:select>
										<div id="parentAdminUnitLevel" style="color: red;"></div>
								  </div>
						</div>   
                 
             
                     
             
						<div class="form-group" id="statePopup" style="display: none;">
								<label for="ddDestDistrict" class="col-sm-3 control-label"><spring:message code="Label.SELECTSTATE" htmlEscape="true"></spring:message><span class="mandatory">*</span> </label>
							 <div class="col-sm-6">		
									<select class="form-control" onchange="getUnitParentLvlEntity(this.value);" id="stateId" name="stateId" >
													<option value="0">
														<spring:message code="Label.SELECT"></spring:message>
													</option>
													<c:forEach var="stateList" items="${stateList}">
														<option value="${stateList.stateCode}" ${stateList.stateCode == stId ? 'selected="selected"' : ''}>
															<c:out value='${stateList.stateNameEnglish}' escapeXml='true'></c:out>
														</option>
													</c:forEach>
									</select>
									<div class="errormsg" id="err_state" style="color: red;"></div>
							</div>
						</div>
                     
                     
                     
                     <div class="form-group">
                     <label class="col-sm-3 control-label"><spring:message code="Label.SELDEPTPARENTUNITENTITY" text="Parent level Administrative Unit Entity" htmlEscape="true"></spring:message></label> 
                       <div class="col-sm-6"> 
                       <form:select htmlEscape="true" path="parentAdminUnitEntityCode" class="form-control" onchange="resetAdminEntityName();" id="parentUnit"  onclick="clearDivs()">
							<form:option value="0"><spring:message code="Label.SELECT" text="-slect"></spring:message> </form:option>
							<c:forEach items="${adminEntities}" var="adminEntitiesvar">
								<c:if test="${adminEntitiesvar.operation_state == 'F'.charAt(0)}">
										<form:option value="${adminEntitiesvar.parentAdminCode}" disabled="true">${adminEntitiesvar.adminLevelNameEng}</form:option>
								</c:if>
								<c:if test="${adminEntitiesvar.operation_state == 'A'.charAt(0)}">
										<form:option value="${adminEntitiesvar.parentAdminCode}">${adminEntitiesvar.adminLevelNameEng}</form:option>
								</c:if>
							</c:forEach>
						</form:select> 
						</div>
					<div id="parentAdminUnit" style="color: red;"></div>
			     </div>
        </div>
     
      <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                   <button type="button" class="btn btn-info" name="Submit" onclick="validateGetEntityByLevel()" id="submit1" value="" ><spring:message code="Button.PROCEED" htmlEscape="true"  ></spring:message></button>
                   <button type="button" class="btn btn-danger " name="Cancel"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div> 
       
     </div>   
     
      <div id="manageEntity"  style="visibility: hidden; display: none">
						<c:if test="${! empty DEPT_ADMIN_LEVEL}">
							<div class="box-body">
							<div class="table-responsive">
												<table class="table table-bordered table-striped dataTable no-footer" id="modifyAdminUnitLevel" style="margin-top: 10px;">
													<thead>
														<tr >
															<th rowspan="2"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></th>
															<th rowspan="2"><spring:message code="Label.AdminEntityCode" text="Administrative Entity  Code"></spring:message></th>
															<th rowspan="2"><spring:message code="Label.AdminUnitEntityEng" text="Administrative Entity  (In English)"></spring:message></th>
															<th rowspan="2"><spring:message code="Label.AdminUnitEntityLoc" text="Administrative Entity  (In Local)"></spring:message></th>
															<th colspan="6" style="text-align: center;"><spring:message htmlEscape="true" code="Label.ACTION"></spring:message></th>
														</tr>
														<tr >
															<th style="text-align: center;"><spring:message htmlEscape="true" code="Label.VIEW"></spring:message></th>
															<th style="text-align: center;"><spring:message htmlEscape="true" code="Label.CORRECTION"></spring:message></th>
															<c:if test="${parentCategory != 'C'.charAt(0)}">
															<th style="text-align: center;"><spring:message htmlEscape="true" text="Change covered area"></spring:message></th>
															<th align=""><spring:message htmlEscape="true" code="Label.ChangeParentAdminstrativeEntity" text="Change Parent Adminstrative Entity" ></spring:message></th>
															</c:if>
															<th style="text-align: center;"><spring:message htmlEscape="true" code="Label.DELETE"></spring:message></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="adminEntityDetail" varStatus="listAdminRow" items="${DEPT_ADMIN_LEVEL}">
															<tr >
																<td>${offsets*limits+(listAdminRow.index+1)}</td>
																<td align="right"><c:out value="${adminEntityDetail.adminUnitEntityCode}"></c:out></td>
																<td align="center"><c:out value="${adminEntityDetail.adminEntityNameEnglish}"></c:out></td>
																<td align="center"><c:out value="${adminEntityDetail.adminEntityNameLocal}"></c:out></td>

																<td align="center"><a href="#" onclick="javascript:manageAdminUnit('view_AdminLevelDetail.htm',${adminEntityDetail.adminUnitEntityCode});" ><i class="fa fa-eye" aria-hidden="true"></i>
																</a></td>

																<td align="center"><a href="#" onclick="javascript:manageAdminUnit('view_modifyDeptAdmitEntity.htm',${adminEntityDetail.adminUnitEntityCode});" ><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
																<c:if test="${parentCategory!= 'C'.charAt(0)}">
																<td align="center"><a href="#" onclick="javascript:manageAdminUnit('modifyAdminEntityCoverage.htm',${adminEntityDetail.adminUnitEntityCode});"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
																															<td align="center"><a href="#" onclick="javascript:manageAdminUnit('modify_parent_Administrative_Entity_for_Draft.htm',${adminEntityDetail.adminUnitEntityCode});"><i class="fa fa-pencil" aria-hidden="true"></i></a></td>
																</c:if>
																<td  align="center"><a href="#" onclick="javascript:validateEntityChildExist(${adminEntityDetail.adminUnitEntityCode});" ><i class="fa fa-trash-o" aria-hidden="true"></i>
																
															</tr>
														</c:forEach>
													</tbody>
												</table>
												
												</div>
										</div>		
							
						</c:if>
						</div>
						<c:if test="${viewType == 0}">
						<div class="box-body" id="divData">
							<div class="form-group">
							<label style="margin-left: 10px;"> <spring:message htmlEscape="true" code="error.no.rec.found.admin.entity" text="No Administrative Unit Entity is defined for" /> <esapi:encodeForHTMLAttribute>${message}</esapi:encodeForHTMLAttribute>
									</label>
							</div>
						</div>
					</c:if>
					
					<div class="modal fade" id="model-confirm" role="dialog">
					    <div class="modal-dialog">
					    
					      <!-- Modal content-->
					      <div class="modal-content">
					        <div class="modal-header">
					          <button type="button" class="close" data-dismiss="modal">&times;</button>
					          <h4 class="modal-title">Administrative Unit Level</h4>
					        </div>
					        <div class="modal-body" id="customAlertbody">
					            
					         
					        </div>
					        <div class="modal-footer">
					          <button type="button" class="btn btn-default" id="modelyes" data-dismiss="modal">Yes</button>
					          <button type="button" class="btn btn-default" id="modelclose" data-dismiss="modal">No</button>
					        </div>
					      </div>
					      
					    </div>
					  </div>
					
					<%-- <div id="dialog-message" title="This admin Unit entity can not be deleted as it is used in below Organization Units" role="dialog" style="display: none;">
						<!-- <div class="new_listmenu"> -->

						<div class="frmtxt">
							<table id="tblrows" width="100%" class="data_grid">
								<tr>
									<td width="14%" align="center">
										<table cellpadding="0" cellspacing="0" border="0" class="display" id="modifyAdminUnitLevelOrg">
											<thead>
												<tr class="tblRowTitle tblclear">
													<th rowspan="2" align="left"><spring:message code="Label.ORGNAME"></spring:message></th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
									</td>
								</tr>
							</table>
						</div>
					</div> --%>
					
					<div class="modal fade" id="dialog-message" role="dialog">
					    <div class="modal-dialog">
					    
					      <!-- Modal content-->
					      <div class="modal-content">
					        <div class="modal-header">
					          <button type="button" class="close" data-dismiss="modal">&times;</button>
					          <h4 class="modal-title">This admin Unit entity can not be deleted as it is used in below Organization Units</h4>
					        </div>
					        <div class="modal-body" id="customAlertbody">
					            
					         
					        </div>
					        <div class="modal-footer">
					          <table id="modifyAdminUnitLevelOrg" class="data_grid" cellspacing="0" style="width: 95%; margin-left: 3%;">
									<thead>
										<tr class="tblRowTitle tblclear">
											<th rowspan="2" align="left"><spring:message code="Label.ORGNAME"></spring:message></th>
										</tr>
									</thead>
								</table>
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					        </div>
					      </div>
					      
					    </div>
					  </div>
					
					
					 <input type="hidden" name="direction" id="direction" value="0" /> <input type="hidden" name="pageType" value="D" />
					<div id="dialog-confirm" title="Administrative Entity ?" style="display: none">
						<p>
							<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span> Are you confirmed to Delete Administrative Entity ?
						</p>
					</div>
					
					  
        
        </div>
        
        <div class="modal fade" id="sucessAlert" role="dialog">
			    <div class="modal-dialog">
			    
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h4 class="modal-title">Message</h4>
			        </div>
			        <div class="modal-body">
			           <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
					   <esapi:encodeForHTML>${msgid}.</esapi:encodeForHTML>
			        </div>
			        <div class="modal-footer">
			          <button type="button" class="btn btn-default"  id="modelclose" onclick="modelClose();"  data-dismiss="modal">Ok</button>
			        </div>
			      </div>
			      
			    </div>
  		</div>
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>