<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<title>E-Panchayat</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/> 
	<script src="js/departmentAdminUnit.js"></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
		<script type="text/javascript" language="javascript">
		dwr.engine.setActiveReverseAjax(true);
			$(document).ready(function() {
				 var parentAdminCode = document.getElementById("parentAdminCode").value; 
				 $('#parentUnit').val(parentAdminCode);
				 var parentcodeForUnitLevel='${deptAdminUnitForm.adminUnitCode}';
				 var selObj = document.getElementById("parentUnit");
				 for (var j = 0; j < selObj.options.length; j++) {
				 if (selObj.options[j].value == parentcodeForUnitLevel)
				 selObj.remove(j);
				 }
				 
				 /*  Added by Deepti  on 09-05-2016 */
				 var isChecked ="${deptAdminUnitForm.overlapUnit }";
				 if(isChecked == 'Y'){
					 document.getElementById("overlapUnityes").checked =true;
				 }
				 else if(isChecked =='N'){
					 document.getElementById("overlapUnitno").checked =true;
				 }
					 
			});	
	
	</script>
	<style>
</style>
</head>
<body >
	
	<section class="content">
    	<div class="row">
        	<section class="col-lg-12 ">
	        	<div class="box ">
	       			<div class="box-header with-border">
	
	           			<h3 class="box-title"><spring:message code="Label.ModifyAdminUnitDraft" htmlEscape="true"	text="Modify Draft Administrative Unit Level"></spring:message></h3>
	       			</div>
		                <div class="box-body">
							<div class="box-header subheading">
		                              <h4><spring:message code="Label.GenDetailAdminUnitsDraft" htmlEscape="true"
										text="General Details of Draft Admin Unit Level"></spring:message></h4>
		                    </div>
		                    <form:form action="modifyDeptAdmitUnit.htm" class="form-horizontal" method="POST" id="form1" commandName="deptAdminUnit">
								<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyDeptAdmitUnitDraft.htm"/>" />
								<input type="hidden" name="stateCode" id="stateCode" value="${stateCode}" />
								<form:hidden htmlEscape="true" path="buttonClicked" value="" />	
								<form:hidden path="adminUnitCode" value="${deptAdminUnitForm.adminUnitCode}"/>
								<form:hidden path="createdby" value="${deptAdminUnitForm.createdby}"/>
								<form:hidden htmlEscape="true" path="parentCategory" value="${deptAdminUnitForm.parentCategory}" />
								<input type="hidden" id="parentAdminCode" value="<c:out value='${deptAdminUnitForm.parentAdminCode}' escapeXml='true'></c:out>"/>
								<input type="hidden" id="parentCode" value="0"/>
								<input type="hidden" id="childRecords" value="<c:out value='${deptAdminUnitForm.childRecords}' escapeXml='true'></c:out>"/>
								<input type="hidden" id="adminUnitName" value="<c:out value='${deptAdminUnitForm.adminLevelNameEnglish }' escapeXml='true'></c:out>" />
								<input type="hidden" id="adminUnitNameLocal" value="<c:out value='${deptAdminUnitForm.adminLevelNameLocal }' escapeXml='true'></c:out>" />
								<input type="hidden" id="adminUnitCodehidden" value="<c:out value='${deptAdminUnitForm.administrationUnit }' escapeXml='true'></c:out>" />
					            <input type="hidden" id="overlapUnithidden" value="<c:out value='${deptAdminUnitForm.overlapUnit }' escapeXml='true'></c:out>" />
							
							
								<div class="form-group">
                    				<label for="districtNameInEn" class="col-sm-3 control-label"><spring:message code="Label.AdminUnitLevelEng" text="Administrative Unit Level (In English)"></spring:message><span class="mandatory">*</span></label>
                      				<div class="col-sm-6">
                      				<form:input path="adminLevelNameEng" id="adminLevelNameEng" onblur="chekModifyNameValidatons(this.value);"
												name="adminLevelNameEng" htmlEscape="true" class="form-control"
												 value="${deptAdminUnitForm.adminLevelNameEnglish }" maxlength="100"/>
												<div class="errormsg">
										<form:errors htmlEscape="true" path="adminLevelNameEng"></form:errors>
									</div>
											<div id="UniqueDeptAdminError"  class="errormsg"></div>
                      				</div>
                    			</div>
                    			
                    			
                    			<div class="form-group">
                    				<label for="districtNameInEn" class="col-sm-3 control-label"><spring:message code="Label.AdminUnitLevelLoc" text="Administrative Unit Level (In Local)"></spring:message></label>
                      				<div class="col-sm-6">
                      					<form:input path="adminLevelNameLocal" id="adminLevelNameLocal" name="adminLevelNameLocal"
												htmlEscape="true" class="form-control"  value="${deptAdminUnitForm.adminLevelNameLocal }" maxlength="100"/>
										
                                       <div class="errormsg">
										<form:errors htmlEscape="true" path="adminLevelNameLocal"></form:errors>
									</div>
											<div class="errormsg"></div> <span class="errormsg"
											id="ddDestDistrict_error"> </span>
                      				</div>
                    			</div>
							
								<div class="form-group">
                    				<label for="districtNameInEn" class="col-sm-3 control-label"><spring:message
													code="Label.SELDEPTPARENTUNIT"
													text="Select Parent Administrative Unit Level" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
                      				<div class="col-sm-6">
                      					<form:select htmlEscape="true" path="parentAdminCode" class="form-control" id="parentUnit" onclick="clearDivs()" >
											<form:option value="0">
												<spring:message code="Label.SELECT" text="-slect"></spring:message>
											</form:option>
											<form:options items="${deptUnitExists}" itemLabel="adminLevelNameEng" itemValue="adminUnitCode" />
										</form:select>
                      				</div>
                      				<div class="errormsg">
										<form:errors htmlEscape="true" path="parentAdminCode"></form:errors>
									</div>
											<div id="parentAdminUnit" style="color: red;"></div>
                    			</div>
                    			
                    			
                    			<div class="form-group" >
                    			<label class="col-sm-3 control-label"><spring:message  text="Is  covered area of the Admin Unit entities is overlapping or not" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
                    			<div class="col-sm-6">
                    			<label class="radio-inline"><form:radiobutton id="overlapUnityes" path="overlapUnit" htmlEscape="true" value="Y"  /><spring:message code="Label.YES"/></label>
                    			<label class="radio-inline"><form:radiobutton id="overlapUnitno" path="overlapUnit" htmlEscape="true" value="N"  /><spring:message code="Label.NO"/></label>
                    			</div>
                    			</div>
                    			
                    			
                    			<div class="box-footer">
                     				<div class="col-sm-offset-2 col-sm-10">
			                       		<div class="pull-right">
				                            <button type="button" id="submit1" class="btn btn-success" onclick="ValidateAndSubmitforupdateForDraft();"><i class="fa fa-floppy-o"></i> <spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button>
				                            <button type="button"  id="submit1" name="Publish" class="btn btn-success" onclick="ValidateAndSubmitforupdateForDraftPublish();"><i class="fa fa-floppy-o"></i> <spring:message code="Label.PUBLISH" htmlEscape="true"></spring:message></button>
											<button type="button" id="Submit6" class="btn btn-warning" onclick="emptymodifyElements()" ><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button>	
											<button type="button" name="Submit6" class="btn btn-danger" onclick="javascript:location.href='home.htm'"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>	
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