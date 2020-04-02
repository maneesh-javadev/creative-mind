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
		
	} );
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
	
});
function manageAdminUnit(url,id)
{
	dwr.util.setValue('adminUnitEntityCode', id, {	escapeHtml : false	});
	document.getElementById("unitCode").value = id;
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}


function publishAllAdminEntity(entityCode) {
	lgdDwrOrganizationService.publishAdminEntityAll(entityCode, {
		callback : publishAdminEntityCallBackAll,
		errorHandler : addedCoveredAreaError,
		arg : entityCode

	});

}

function publishAdminEntityCallBackAll(data, entityCode) {

	if (data) {
		alert("Admin unit Entity Publish successfully from Draft");
		// $('input[name=Submit]').prop('disabled', true);
		// document.getElementById('form1').action =
		// 'manage_AdminEntiy_byParentForDraft.htm';
		// document.getElementById('form1').submit();
		document.forms['form1'].method = "get";
		document.forms['form1'].action = "home.htm?<csrf:token uri='home.htm'/>";
		document.forms['form1'].submit();

	} else {
		alert("error occured");
	}
}
</script>
<title><spring:message code="Label.ViewAdminUnitEntity" htmlEscape="true" text="View Admin Unit Entity "></spring:message></title>
</head>
<body>
	
	<section class="content">
	
		<div class="row">
        	<section class="col-lg-12 ">
	              	<div class="box ">
           				<div class="box-header with-border">
		                	<h3 class="box-title"><spring:message code="Label.ViewAdminUnitEntity" htmlEscape="true" text="View Admin Unit Entity "></spring:message></h3>
		                </div>
		                <div class="box-body">
	                    	<div class="box-header subheading">
								<h4><spring:message code="Label.GENDETAILENTITY" htmlEscape="true" text="General Details of Admin Unit Entity"></spring:message></h4>
	                    	</div>
		                    <form:form class="form-horizontal" action="view_AdminLevelDetail.htm" id="form1" method="POST" commandName="adminLevelBean">
								<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="view_AdminLevelDetail.htm"/>" />
								<form:hidden path="adminUnitEntityCode" value="${deptAdminUnitForm.adminUnitEntityCode}" />
								<input type="hidden" name="stateCode" id="stateCode" value="<c:out value='${stateCode}'  escapeXml='true'></c:out>" />
								<input type="hidden" name="unitCode" id="unitCode" />
								<input type="hidden" name="entityType" id="entityType" />
								<input type="hidden" name="districtCode" id="districtCode" value="<c:out value='${districtCode}'  escapeXml='true'></c:out>" />
								<input type="hidden" name="overlappValue" id ='overlappValue' value='${isExistOverlap}' />
								
								<div id="selectEntityType" style="display: block;">
								<div class="form-group">
									<label class="col-sm-3 control-label"><spring:message code="Label.SELDeptAdministrationUNIT" text="Type of Administrative unit Level" htmlEscape="true"></spring:message></label><span class="errormsg">*</span>
									<div class="col-sm-6">
										<form:select htmlEscape="true" path="adminUnitLevelCode" class="form-control" id="unitLevelCode" onchange="getUnitLevelEntity();getOverlappingValue(this);" >
												<form:option value="0">
													<spring:message code="Label.SELECT" text="-slect"></spring:message>
												</form:option>
												<form:options items="${parentAdminEntity}" itemLabel="adminLevelNameEng" itemValue="adminUnitCode" />
										</form:select>
									</div> 
									<div class="errormsg"></div>
									<div id="parentAdminUnitLevel" style="color: red;"></div>
								</div>
								
								<div class="form-group" id="statePopup" style="display: none;">
									<label class="col-sm-3 control-label"><spring:message code="Label.SELECTSTATE" htmlEscape="true"></spring:message></label><span class="errormsg">*</span>
									<div class="col-sm-6">
										<select onchange="getUnitParentLvlEntity(this.value);" class="form-control" id="stateId" name="stateId">
												<option value="0">
													<spring:message code="Label.SELECT"></spring:message>
												</option>
												<c:forEach var="stateList" items="${stateList}">
													<option value="${stateList.stateCode}" ${stateList.stateCode == stId ? 'selected="selected"' : ''}>
														<c:out value='${stateList.stateNameEnglish}' escapeXml='true'></c:out>
													</option>
												</c:forEach>
										</select>
									</div> 
									<div class="errormsg" id="err_state" style="color: red;"></div>
								</div>
								
								
								<div class="form-group">
									<label class="col-sm-3 control-label"><spring:message code="Label.SELDEPTPARENTUNITENTITY" text="Parent level Administrative Unit Entity" htmlEscape="true"></spring:message></label>
										<div class="col-sm-6">
											<form:select htmlEscape="true" path="parentAdminUnitEntityCode" class="form-control" onchange="resetAdminEntityName();" id="parentUnit"  onclick="clearDivs()">
													<form:option value="0">
														<spring:message code="Label.SELECT" text="-slect"></spring:message>
													</form:option>
	
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
									<div class="errormsg" id="err_state" style="color: red;"></div>
								</div>
								
								<div class="box-footer">
				                     <div class="col-sm-offset-2 col-sm-10">
				                       <div class="pull-right">
				                            <button type="button" id="submit1" name="Submit" class="btn btn-success" onclick="validateGetEntityByLevelForDraft();"><i class="fa fa-floppy-o"></i> <spring:message code="Button.PROCEED" htmlEscape="true"></spring:message></button>
											<button type="button" name="Submit6" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
				                        </div>
				                     </div>   
                  				</div>
								</div>
								
								<div id="manageEntity" style="visibility: hidden; display: none">
									<c:if test="${! empty DEPT_ADMIN_LEVEL}">
										<div class="frmpnlbg" id="divData">
										<div class="table-responsive">
											<table class="table table-striped table-bordered  " cellspacing="0"  id="modifyAdminUnitLevel">
												<thead>
													<tr>
														<th rowspan="2"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></th>
														<th rowspan="2"><spring:message code="Label.AdminEntityCode" text="Administrative Entity  Code"></spring:message></th>
														<th rowspan="2"><spring:message code="Label.AdminUnitEntityEng" text="Administrative Entity  (In English)"></spring:message></th>
														<th rowspan="2"><spring:message code="Label.AdminUnitEntityLoc" text="Administrative Entity  (In Local)"></spring:message></th>
														<th rowspan="2"><spring:message code="Label.DEPTPARENTUNIT" text="Parent Adminstrative Unit"></spring:message></th>
														<th colspan="6" align="center"><spring:message htmlEscape="true" code="Label.ACTION"></spring:message></th>
													</tr>
													<tr >
														<th align="right"><spring:message htmlEscape="true" code="Label.PUBLISH"></spring:message></th>
														<th align="center"><spring:message htmlEscape="true" code="Label.CORRECTION"></spring:message></th>
														<th align=""><spring:message htmlEscape="true" code="Label.Changecoveredarea"></spring:message></th>
														<th align="center"><spring:message htmlEscape="true" code="Label.DELETE"></spring:message></th>
													</tr>
												</thead>
												<tbody>
												<c:set var="allAdminCode"/>
													<c:forEach var="adminEntityDetail" varStatus="listAdminRow" items="${DEPT_ADMIN_LEVEL}">
														<tr  id="${adminEntityDetail.adminUnitEntityCode}">
															<c:set var="allAdminCode" value="${listAdminRow.first  ? '' : allAdminCode}${adminEntityDetail.adminUnitEntityCode}${listAdminRow.last ? '' : ','}" />
															
															<td>${offsets*limits+(listAdminRow.index+1)}</td>
															<td><c:out value="${adminEntityDetail.adminUnitEntityCode}"></c:out></td>
															<td align="center"><c:out value="${adminEntityDetail.adminEntityNameEnglish}"></c:out></td>
															<td align="center"><c:out value="${adminEntityDetail.adminEntityNameLocal}"></c:out></td>
															<td align="center"><c:out value="${adminEntityDetail.parentAdminUnitEntityName}"></c:out></td>
															<td  align="right"><a href="#"><img src="images/publish.png" onclick="javascript:publishAdminEntity(${adminEntityDetail.adminUnitEntityCode});" width="18" height="19" border="0" /></a></td>
															<td align="center"><a href="#" onclick="javascript:manageAdminUnit('view_modifyDeptAdmitEntityForDraft.htm',${adminEntityDetail.adminUnitEntityCode});"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
															<td align="center"><a href="#" onclick="javascript:manageAdminUnit('modifyAdminEntityCoverageForDraft.htm',${adminEntityDetail.adminUnitEntityCode});"><i class="fa fa-pencil" aria-hidden="true"></i></a></td>
														
															<td  align="center"><a href="#" onclick="javascript:validateEntityChildExistforDraft(${adminEntityDetail.adminUnitEntityCode});"><i class="fa fa-times" aria-hidden="true"></i>
															</a></td>
															
															
														</tr>
													</c:forEach>
													
												</tbody>
											</table>
											</div>
											
									</div>
					</c:if>
					<div class="box-footer">
				                     <div class="col-sm-offset-2 col-sm-10">
				                       <div class="pull-right">
				                          
    									<button type="button" id="submit1" name="Submit" class="btn btn-success" onclick="publishAllAdminEntity('${allAdminCode}');"><i class="fa fa-floppy-o"></i> <spring:message code="Button.PUBLISHALL" text="PUBLISH ALL" htmlEscape="true"></spring:message></button>
    									<button type="button" name="Submit6" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
				                        </div>
				                     </div>   
                  				</div>
					
					</div>
								<c:if test="${viewType == 0}">
									<div class="frmpnlbg" id="divData">
										<div class="frmtxt">
											<ul class="blocklist">
												<li><label> <spring:message htmlEscape="true" code="error.no.rec.found.admin.entity" text="No Administrative Unit Entity is defined for" /> <esapi:encodeForHTMLAttribute>${message}</esapi:encodeForHTMLAttribute>
												</label></li>
											</ul>
											<div class="clear"></div>
										</div>
									</div>
							  </c:if>
							  <input type="hidden" name="direction" id="direction" value="0" />
							  <input type="hidden" name="pageType" value="D" />
								<div id="dialog-confirm" title="Administrative Entity ?" style="display: none">
									<p>
										<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span> Are you confirmed to Delete Administrative Entity ?
									</p>
								</div>
								
								
								
								
								
								
								
							</form:form>
						</div>
					</div>
			</section>
		</div>
	</section>
	
</body>
</html>