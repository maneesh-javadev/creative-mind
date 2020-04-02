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

<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
	$(document).ready(function() {
		var childRecords = document.getElementById("childRecords").value;
	    var parentAdminCode = '${deptAdminUnitForm.parentAdminCode}'; 
		var adminUnitLevCode = document.getElementById("administationUnit").value;
		paretnunitCode= '${deptAdminUnitForm.parentAdminCode}'; 
		$('#unitLevelCode').val(adminUnitLevCode);
		$('#parentUnit').val(parentAdminCode);
		if (childRecords > 0) {
			document.getElementById("unitLevelCode").disabled = true;
			document.getElementById("parentUnit").disabled = true;
		}
	});
</script>
<style>
</style>
</head>
<body>
	<section class="content">
    	<div class="row">
        	<section class="col-lg-12 ">
	        	<div class="box ">
	       			<div class="box-header with-border">
	           			<h3 class="box-title"><spring:message code="Label.ModifyAdminEntity" htmlEscape="true"	text="Modify Administrative Unit Entity"></spring:message></h3>
	       			</div>
		            <div class="box-body">
		            	<form:form action="modifyDeptAdmitEntity.htm" class="form-horizontal" method="POST" id="form1" commandName="deptAdminUnit">
							<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyDeptAdmitUnit.htm"/>" />
							<input type="hidden" name="stateCode" id="stateCode" value="<c:out value='${stateCode}'  escapeXml='true'></c:out>" />
							<input type="hidden" id="districtCode" value="<c:out value='${districtCode}' escapeXml='true'></c:out>"></input>
							<form:hidden path="adminUnitEntityCode" value="${deptAdminUnitForm.adminUnitCode}" />
							<form:hidden path="createdby" value="${deptAdminUnitForm.createdby}" />
							<form:hidden htmlEscape="true" path="parentCategory" value="${deptAdminUnitForm.parentCategory}" />
							<input type="hidden" id="parentAdminCode" value="<c:out value='${deptAdminUnitForm.parentAdminCode}' escapeXml='true'></c:out>" />
							<input type="hidden" id="parentCode" value="1" />
							<input type="hidden" id="administationUnit" value="<c:out value='${deptAdminUnitForm.adminUnitLevelCode}' escapeXml='true'></c:out>" />
							<input type="hidden" id="childRecords" value="<c:out value='${deptAdminUnitForm.childRecords}' escapeXml='true'></c:out>" />
							<input type="hidden" id="adminUnitName" value="<c:out value='${deptAdminUnitForm.adminLevelNameEnglish }' escapeXml='true'></c:out>" />
							<input type="hidden" id="adminUnitNameLocal" value="<c:out value='${deptAdminUnitForm.adminLevelNameLocal }' escapeXml='true'></c:out>" />
							<input type="hidden" id="adminUnitCodehidden" value="<c:out value='${deptAdminUnitForm.administrationUnit }' escapeXml='true'></c:out>" />
							
							
							
							<div class="box-header subheading">
                             	<h4><spring:message htmlEscape="true" code="Label.GENDETAILENTITY" text="General Details of Admin Unit Entity"></spring:message></h4>
		                    </div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELDeptAdministrationUNIT" text="Type of Administrative unit Level"></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6">
									<form:select htmlEscape="true" path="adminUnitLevelCode" onchange="resetAdminParentIndex();getUnitLevelEntity();" class="form-control" id="unitLevelCode">
										<form:option value="0">
											<spring:message code="Label.SELECT" text="-slect"></spring:message>
										</form:option>
										<form:options items="${unitLevels}" itemLabel="adminLevelNameEng" itemValue="adminUnitCode" />
									</form:select> 
									<div class="errormsg">
										<form:errors htmlEscape="true" path="adminUnitLevelCode"></form:errors>
									</div>
									<div id="parentAdminUnitLevel" style="color: red;"></div>
								</div>
				             </div>
				             
				             
				             <div class="form-group" id="statePopup" style="display: none;">
								<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTSTATE" text="Type of Administrative unit Level"></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6">
									<select class="form-control"
											onchange="getUnitParentLvlEntity(this.value);" id="stateId">
												<option value="0">
													<spring:message code="Label.SELECT"></spring:message>
												</option>
												<c:forEach var="stateList" items="${stateList}">
													<option value="${stateList.stateCode}">
														<c:out value='${stateList.stateNameEnglish}' escapeXml='true'></c:out>
													</option>
												</c:forEach>
										</select>
									<div class="errormsg" id="err_state" style="color: red;"></div>
								</div>
				             </div>
				             
				             
				             <div class="form-group">
								<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELDEPTPARENTUNITENTITY" text="Parent level Administrative Unit Entity"></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6">
									<form:select htmlEscape="true" path="parentAdminUnitEntityCode" class="form-control" id="parentUnit" onchange="checkmodifyEntityName();">
										<form:option value="0">
											<spring:message code="Label.SELECT" text="-slect"></spring:message>
										</form:option>
										<form:options items="${unitEntities}" itemLabel="adminLevelNameEng" itemValue="parentAdminCode" />
									</form:select>
									<div id="parentAdminUnitforEntity" style="color: red;"></div>
								</div>
				             </div>
				             
				             
				             <div class="form-group">
                      			<label for="districtNameInEn" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.AdminUnitEntityEng" text="Administrative Unit Entity Name(In English)"></spring:message><span class="mandatory">*</span></label>
	                      		<div class="col-sm-6">
	                      		<form:input path="adminEntityNameEnglish" id="adminLevelNameEng" onblur="chekModifyNameValidatonsforEntity(this.value);" name="adminLevelNameEng" htmlEscape="true" class="form-control"  value="${deptAdminUnitForm.adminLevelNameEnglish }" maxlength="100" />
	                      		</div>
	                      		<div class="errormsg">
											<form:errors htmlEscape="true" path="adminEntityNameEnglish"></form:errors>
										</div>
										<div id="UniqueDeptAdminError" style="color: red;"></div>
                    		</div>
                    		
                    		<div class="form-group">
                      			<label for="districtNameInEn" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.AdminUnitEntityLoc" text="Administrative Unit Entity Name (In Local)"></spring:message></label>
	                      		<div class="col-sm-6">
		                      		<form:input path="adminEntityNameLocal" id="adminLevelNameLocal"
									name="adminLevelNameLocal" htmlEscape="true" class="form-control"  value="${deptAdminUnitForm.adminLevelNameLocal }" maxlength="100" />
	                      		</div>
	                      		<div class="errormsg">
									<form:errors htmlEscape="true" path="adminEntityNameLocal"></form:errors>
								</div>
								
                    		</div>
							<div class="form-group" style="display: none">
								<a href="#" style="text-decoration: underline; color: navy;" onclick="showReports();">View Affected Organization Units</a>	                      		
                    		</div>
							<div id="dialog-confirm" title="Administrative Units ?" style="display: none">
								<p>
									<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span> Are you confirmed to Modify Admin Entity ?
								</p>
							</div>
							
							
						<div class="box-footer">
		                     <div class="col-sm-offset-2 col-sm-10">
		                       <div class="pull-right">
		                            <button type="button" id="submit1" name="Submit" class="btn btn-success" onclick="ValidateAndSubmitforEntityupdateForDraft();"><i class="fa fa-floppy-o"></i> <spring:message code="Button.SAVE"  htmlEscape="true"></spring:message></button>
									<button type="button" id="Submit6" class="btn btn-danger" onclick="emptymodifyEntityElements()"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button>
									<button type="button" name="Submit6" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
		                        </div>
		                     </div>   
                 		</div>	
							
							
		           		</form:form>
		            
		            </div>
		         </div>
		     </section>
		</div>
	</section>
</body>
</html>