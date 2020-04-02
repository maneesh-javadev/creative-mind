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
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>

<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
	$(document).ready(function() {
		var childRecords = document.getElementById("childRecords").value;
	    var parentAdminCode = '<c:out value="${deptAdminUnitForm.parentAdminCode}" escapeXml="true"></c:out>'; 
		var adminUnitLevCode = document.getElementById("administationUnit").value;
		paretnunitCode= '<c:out value="${deptAdminUnitForm.parentAdminCode}" escapeXml="true"></c:out>'; 
		$('#unitLevelCode').val(adminUnitLevCode);
		$('#parentUnit').val(parentAdminCode);
		
		if (childRecords > 0 || (isParseJson( '${orgUnitsExist}' ))) {
			document.getElementById("unitLevelCode").disabled = true;
			document.getElementById("parentUnit").disabled = true;
		}

	});
	
	function showReports() {
		$("#dialog:ui-dialog").dialog("destroy");
		$("#dialog-message").dialog({
			centered : true,
			modal : true,
			width : 600,
			height : 400
		});
	}
	
	function showExistOrg(){
		
		$("#dialog-message").modal('show');
	}
</script>
<style>
</style>
</head>
<body>
	<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                    <form:form action="modifyDeptAdmitEntity.htm" method="POST" id="form1" commandName="deptAdminUnit" class="form-horizontal">
							<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyDeptAdmitUnit.htm"/>" />
							<input type="hidden" name="stateCode" id="stateCode" value="<c:out value='${stateCode}'  escapeXml='true'></c:out>"/>
							<form:hidden path="adminUnitEntityCode" value="${deptAdminUnitForm.adminUnitCode}" htmlEscape="true"/>
							<form:hidden path="createdby" value="${deptAdminUnitForm.createdby}" htmlEscape="true" />
							<input type="hidden" id="parentAdminCode" name="parentAdminCode" value="<c:out value='${deptAdminUnitForm.parentAdminCode}' escapeXml='true'></c:out>" />
							<%-- <input type="hidden" id="parentCode" value="${deptAdminUnitForm.parentAdminCode}"/> --%>
							<input type="hidden" id="parentCode" value="1" />
							<input type="hidden" id="administationUnit" name="administationUnit" value="<c:out value='${deptAdminUnitForm.adminUnitLevelCode}'  escapeXml='true'></c:out>"/>
							<input type="hidden" id="childRecords" value="<c:out value='${deptAdminUnitForm.childRecords}' escapeXml='true'></c:out>"/>
							<input type="hidden" id="adminUnitName" value="<c:out value='${deptAdminUnitForm.adminLevelNameEnglish}'  escapeXml='true'></c:out>"/>
							<input type="hidden" id="adminUnitNameLocal" value="<c:out value='${deptAdminUnitForm.adminLevelNameLocal }' escapeXml='true'></c:out>"/>
							<input type="hidden" id="adminUnitCodehidden" value="<c:out value='${deptAdminUnitForm.administrationUnit }'  escapeXml='true'></c:out>"/>
							<input type ="hidden" value="<c:out value='${districtCode}'  escapeXml='true'></c:out>"	id="districtCode">
							<div id="popupDialogBX" style="display: none;">
								<iframe id="loginPopupFrame" scrolling="no"></iframe>
							</div>
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.ModifyAdminEntity"	htmlEscape="true" text="Modify Administrative Unit Entity"></spring:message></h3>
                                </div>
                                 <div class="box-header subheading">
                                    <h4 class="box-title"><spring:message code="Label.GENDETAILENTITY" htmlEscape="true" text="General Details of Admin Unit Entity"></spring:message></h4>
                                </div>
                              
                        <div class="box-body">
                            <div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message code="Label.SELDeptAdministrationUNIT"  text="Type of Administrative unit Level" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								 <form:select  htmlEscape="true" path="adminUnitLevelCode" onchange="resetAdminParentIndex();getUnitLevelEntity();" class="form-control" id="unitLevelCode" >
													<form:option value="0" htmlEscape="true">
														<spring:message code="Label.SELECT" text="-slect"></spring:message>
													</form:option>
													<form:options items="${unitLevels}" itemLabel="adminLevelNameEng" itemValue="adminUnitCode" htmlEscape="true"/>
												</form:select>
										<div class="mandatory"> <form:errors htmlEscape="true" path="adminUnitLevelCode"></form:errors> </div>
											<div id="parentAdminUnitLevel" style="color: red;"></div>
								  </div>
						</div>
						
						
						
						
							<div class="form-group" id="statePopup" style="display: none;">
								<label class="col-sm-3 control-label"><spring:message code="Label.SELECTSTATE" htmlEscape="true"></spring:message><span class="mandatory">*</span> </label>
								<div class="col-sm-6">
								   <select class="form-control" onchange="getUnitParentLvlEntity(this.value);" id="stateId" >
											<option value="0"> <spring:message code="Label.SELECT"></spring:message> </option>
											 <c:forEach var="stateList" items="${stateList}">
												<option value="${stateList.stateCode}"><c:out value='${stateList.stateNameEnglish}'  escapeXml='true'></c:out></option>
										</c:forEach>
									</select>
									<div class="mandatory" id="err_state" style="color: red;"></div>
						      </div>
					      </div>   
					      
					      
					      <div class="form-group">
							  <label class="col-sm-3 control-label "><spring:message code="Label.SELDEPTPARENTUNITENTITY" text="Parent level Administrative Unit Entity" htmlEscape="true"></spring:message> <span class="mandatory">*</span></label>
								<div class="col-sm-6"> 
								   <form:select 	htmlEscape="true" path="parentAdminUnitEntityCode"  class="form-control" id="parentUnit"  onchange="checkmodifyEntityName();" >
											 <form:option value="-1" htmlEscape="true"><spring:message code="Label.SELECT" text="-slect"></spring:message></form:option>
											 <form:options items="${unitEntities}" itemLabel="adminLevelNameEng" itemValue="parentAdminCode" htmlEscape="true"/>
                                                    </form:select> </div>
											<div id="parentAdminUnitforEntity" style="color: red;"></div>
											
						</div>
						
						
						<div class="form-group">
							  <label class="col-sm-3 control-label"><spring:message code="Label.AdminUnitEntityEng" text="Administrative Unit Entity Name(In English)"></spring:message><span class="mandatory">*</span></label> 
							  <div class="col-sm-6">
							   <form:input 	path="adminEntityNameEnglish" id="adminLevelNameEng" onblur="chekModifyNameValidatonsforEntity(this.value);"
													name="adminLevelNameEng" htmlEscape="true" class="form-control"
													value="${deptAdminUnitForm.adminLevelNameEnglish }"
													maxlength="100" />
							  </div>  
								<div class="mandatory"><form:errors htmlEscape="true" path="adminEntityNameEnglish"></form:errors></div>
								<div id="UniqueDeptAdminError" style="color: red;"></div>
											
						</div>
								
							<div class="form-group">
								<label class="col-sm-3 control-label"><spring:message code="Label.AdminUnitEntityLoc" text="Administrative Unit Entity Name (In Local)"></spring:message></label>
								 <div class="col-sm-6" >
											  <form:input path="adminEntityNameLocal" id="adminLevelNameLocal" name="adminLevelNameLocal" htmlEscape="true" 	class="form-control" 
																value="${deptAdminUnitForm.adminLevelNameLocal }" maxlength="100" />
									   <div class="mandatory"> <form:errors htmlEscape="true" path="adminEntityNameLocal"></form:errors></div>
								</div> 
							</div>	
									
						<c:if test="${orgUnitsExist}">			
						  <div class="form-group" style="margin-left: 10px;">
						  	<a href="#" style="text-decoration: underline; color: navy;"  onclick="showExistOrg()"	>View Affected Organization Units</a>
						  </div>		
						</c:if>
						
                      </div> 
              
                       
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                  <button type="button" class="btn btn-success" name="Submit" onclick="ValidateAndSubmitforEntityupdate()" id="submit1" value="" ><spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button>
		          <buttton type="button" class="btn btn-warning" id="Submit6" value="" onclick="emptymodifyEntityElements()" ><spring:message code="Button.CLEAR"></spring:message></buttton>
                   <button type="button" class="btn btn-danger " name="Cancel"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div> 
       
       <div id="dialog-confirm" title="Administrative Units ?"
							style="display: none">
							<p>
								<span class="ui-icon ui-icon-alert"
									style="float: left; margin: 0 7px 20px 0;"></span> Are you
								confirmed to Modify Admin Entity ?
							</p>
		</div>
		

  <div class="modal fade"  role="dialog" id="dialog-message"  >
 
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">View Affected Organization Units</h4>
        </div>
        <div class="modal-body">
         <table class="table table-bordered table-hover" id="modifyAdminUnitLevel">
			<thead>
				<tr ><th rowspan="2"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></th>
					 <th rowspan="2" align="left"><spring:message code="Label.ORGNAME"> </spring:message></th>
				</tr>
		   </thead>
			<tbody>
				<c:forEach var="fyearval" items="${adminEntityOrg}" varStatus="listAdminRow" >
					<tr>
						<td align="center"><c:out value="${offsets*limits+(listAdminRow.index+1)}"  escapeXml="true"></c:out></td>
						<td align="left"><c:out value="${fyearval}" escapeXml="true"></c:out></td>
					</tr>
				</c:forEach>
		  </tbody>
	   </table>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
    
  </div>
						

	  
	  
	  <c:if test="${!orgUnitsExist}">
	        <div class="box-body">
	            <label><spring:message code="Label.NROrgUNis" text="No Records found for Admin Org Units"></spring:message></label>
	        </div>
	      </c:if>
	</div>
										
						
						
						
     </div>   
             
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>