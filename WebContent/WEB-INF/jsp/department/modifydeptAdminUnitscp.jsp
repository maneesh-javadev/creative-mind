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
<link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" />
<script src="js/departmentAdminUnit.js"></script>

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>



<script type="text/javascript" language="javascript">
		dwr.engine.setActiveReverseAjax(true);
			$(document).ready(function() {
				var childRecords=new Boolean();
				 childRecords =  '${deptAdminUnitForm.childExist}'; 
				 var parentAdminCode = document.getElementById("parentAdminCode").value; 
				
				 var parentCategory='${deptAdminUnitForm.parentCategory}';
				 if(parentCategory=="C"){
					 $("#overlapUnityes").prop('checked', false);
					 $("#overlapUnitno").prop('checked', true);
					 $("#overlapUnityes").attr("disabled", true);
				 }
					setTimeout(function(){
						$("#parentUnit option[value='" + parentAdminCode+"#"+parentCategory+ "']").attr("selected", "selected");
						
					},200);
				// $('#parentUnit').val(parentAdminCode);
				 if(childRecords == "false")
					 {
				 document.getElementById("parentUnit").disabled=true;
					 }
				 else
					 {
				 var parentcodeForUnitLevel='<c:out value="${deptAdminUnitForm.adminUnitCode}" escapeXml="true"></c:out>';
				 var selObj = document.getElementById("parentUnit");
				 for (var j = 0; j < selObj.options.length; j++) {
				 if (selObj.options[j].value == parentcodeForUnitLevel)
				 selObj.remove(j);
				 }
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
			
			function existEntityList(){
				var isChecked ="${deptAdminUnitForm.overlapUnit }";
				var adminUnitCode='${deptAdminUnitForm.adminUnitCode}';
		    	if(isChecked == 'Y'){
		    		lgdDwrOrganizationService.getEntityList(adminUnitCode, {
		    			callback : function(data) {
		    				if(data.length>0){
		    					alert("You Can't modify this field as admin unit entities are already defined under this level");
		    					document.getElementById("overlapUnityes").checked =true;
		    				}
		    			}
		    		});
				 }
		    	
		      }
	
	</script>
<style>
</style>
</head>
<body>
	<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                    <form:form action="modifyDeptAdmitUnit.htm" method="POST" id="form1" commandName="deptAdminUnit" class="form-horizontal">
				       <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyDeptAdmitUnit.htm"/>" />
						<input type="hidden" name="stateCode" id="stateCode" value="<c:out value='${stateCode}' escapeXml='true'></c:out>" />
						<form:hidden path="adminUnitCode" value="${deptAdminUnitForm.adminUnitCode}" htmlEscape="true" />
						<form:hidden path="createdby" value="${deptAdminUnitForm.createdby}" 	htmlEscape="true" />
						<input type="hidden" id="parentAdminCode" name="parentAdminCode" value="<c:out value='${deptAdminUnitForm.parentAdminCode}' escapeXml='true'></c:out>" />
						<input type="hidden" id="parentCode" value="0" />
						<input type="hidden" id="childRecords" value="<c:out value='${deptAdminUnitForm.childRecords}' escapeXml='true'></c:out>" />
						<input type="hidden" id="adminUnitName" value="<c:out value='${deptAdminUnitForm.adminLevelNameEnglish }' escapeXml='true'></c:out>" />
						<input type="hidden" id="adminUnitNameLocal" value="<c:out value='${deptAdminUnitForm.adminLevelNameLocal }' escapeXml='true'></c:out>" />
						<input type="hidden" id="adminUnitCodehidden" value="<c:out value='${deptAdminUnitForm.administrationUnit }' escapeXml='true'></c:out>" />
						<form:hidden path="parentCategory" htmlEscape="true" />
				<!-- added by deepti on 09-05-2016	 -->
				<input type="hidden" id="overlapUnithidden" value="<c:out value='${deptAdminUnitForm.overlapUnit }' escapeXml='true'></c:out>" />
						
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.ModifyAdminUnit" htmlEscape="true"  text="Modify Administrative Unit Level"></spring:message></h3>
                                </div>
                                 
                                 <div class="box-header subheading"><h4><spring:message code="Label.GenDetailAdminUnits" htmlEscape="true" text="General Details of Admin Unit Level"></spring:message></h4></div>
                                 
                        <div class="box-body">
                        
                            <div class="form-group">
								<label for="ddSourceDistrict" class="col-sm-3 control-label"><spring:message code="Label.AdminUnitLevelEng" text="Administrative Unit Level (In English)"></spring:message>  <span class="mandatory">*</span></label>
								<div class="col-sm-6" >
									<form:input path="adminLevelNameEng" id="adminLevelNameEng" onblur="chekModifyNameValidatons(this.value);" name="adminLevelNameEng" htmlEscape="true" class="form-control"
											value="${deptAdminUnitForm.adminLevelNameEnglish }" 	maxlength="100" />
										<div class="errormsg">
											<form:errors htmlEscape="true" path="adminLevelNameEng"></form:errors>
										</div>
										<div id="UniqueDeptAdminError" class="errormsg"></div> 
								</div>
							</div>
								
							<div class="form-group">
								<label for="ddSourceDistrict" class="col-sm-3 control-label"><spring:message code="Label.AdminUnitLevelLoc" text="Administrative Unit Level (In Local)"></spring:message> </label>
								<div class="col-sm-6" >
									 <form:input path="adminLevelNameLocal" id="adminLevelNameLocal" name="adminLevelNameLocal" htmlEscape="true" class="form-control" 
											value="${deptAdminUnitForm.adminLevelNameLocal }" maxlength="100" />
										<div class="errormsg"> <form:errors htmlEscape="true" path="adminLevelNameLocal"></form:errors> </div>
										<div class="errormsg"></div> <span class="errormsg" id="ddDestDistrict_error"> </span>
								</div>
						</div>  
						
							<div class="form-group">
								<label for="ddSourceDistrict" class="col-sm-3 control-label"><spring:message	code="Label.SELDEPTPARENTUNIT" text="Select Parent Administrative Unit Level" 	htmlEscape="true"></spring:message> <span class="mandatory">*</span></label>
								<div class="col-sm-6" >
									<select htmlEscape="true" id="parentUnit"  onclick="clearDivs();checkForCountryLevel()" class="form-control">
										
										
										
										<option value="-1">Select</option>
				        				<optgroup id="landRegionUnits" label="Land Regions Unit Levels">
						        				<c:if test="${isCenterUser}">
						        							<c:forEach var="obj" items="${deptUnitExists}">
										        			   <c:if test="${obj.unitLevelType eq 'C'}">
										        			   
										        					<option value="${obj.adminUnitCode}#${obj.unitLevelType}"><c:out value="${obj.adminLevelNameEng}" escapeXml="true"></c:out></option>
																</c:if>
										        			</c:forEach>
						        				</c:if>
							        			<c:forEach var="obj" items="${deptUnitExists}">
							        			   <c:if test="${obj.unitLevelType eq 'L'}">
							        					<option value="${obj.adminUnitCode}#${obj.unitLevelType}"><c:out value="${obj.adminLevelNameEng}" escapeXml="true"></c:out></option>
													</c:if>
							        			</c:forEach>
				        				</optgroup>
				        				<optgroup id="adminUnits" label="Administrative Unit Levels">
				        					<c:forEach var="obj" items="${deptUnitExists}">
							        			   <c:if test="${obj.unitLevelType eq 'A'}">
							        				<option value="${obj.adminUnitCode}<c:out value="#"/>${obj.unitLevelType}"><c:out value="${obj.adminLevelNameEng}" escapeXml="true"></c:out></option>
													</c:if>
							        		</c:forEach>
				        				</optgroup>
				        				<c:if test="${not isCenterUser}">
										<optgroup id=" localbBodyType" label=" Local Body Types">
						        			<c:forEach var="localBody" items="${localBodyTypes}">
						        				<option value="${localBody.localBodyTypeCode}<c:out value="#G"/>"><c:out value="${localBody.name}" escapeXml="true"></c:out></option>
						        			
						        			</c:forEach>
				        				
				        				</optgroup>
				        				</c:if>
										
									</select>
          								<div class="errormsg"><form:errors htmlEscape="true" path="parentAdminCode"></form:errors> </div>
		  									<div id="parentAdminUnit" style="color: red;"></div>
								</div>
						    </div> 
						
						
						<div class="form-group">
							 <label class="col-sm-3 control-label"><spring:message  text="Is  covered area of the Admin Unit entities is overlapping or not" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
						<div class="col-sm-6">
							 <label class="radio-inline">
							   <form:radiobutton id="overlapUnityes" path="overlapUnit" htmlEscape="true" value="Y"  /><spring:message code="Label.YES"/>
							 </label>
							 		<label class="radio-inline">
							 		<form:radiobutton id="overlapUnitno" path="overlapUnit" htmlEscape="true" value="N"  onclick="existEntityList();"/><spring:message code="Label.NO"/></label>
							
						   </div>
						</div>
						 
                   </div> 
             
                     
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                   <button type="button" class="btn btn-success" name="Submit" onclick="ValidateAndSubmitforupdate()" id="submit1" ><spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button>
	               <button type="button" class="btn btn-warning" id="Submit6"  onclick="emptymodifyElements()"  ><spring:message code="Button.CLEAR"></spring:message></button>
                   <button type="button" class="btn btn-danger " name="Cancel"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div>
       
          <div id="dialog-confirm" title="Administrative Units ?"
						style="display: none">
						<p>
							<span class="ui-icon ui-icon-alert"
								style="float: left; margin: 0 7px 20px 0;"></span> Are you
							confirmed to Modify Admin Unit ?
						</p>
		 </div>
		 
		
  		  
  		  
     </div>   
             
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>