<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../common/taglib_includes.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>E-Panchayat</title>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script type="text/javascript" src="js/common.js"></script>




<script src="js/govtorder.js"></script>

<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrCoveredLandRegionByULBService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageServiceForDraftVillageList.js'></script>

<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSurveyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageServiceForDraft.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>


<c:set var="type" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.MANAGE_ADMINISTRATIVE_UNIT.toString()%>"></c:set>
<script type="text/javascript" language="javascript">
var stateChoice;
var _state_code=parseInt('${stateCode}');
var _type='${type}';
var _entity_type='${entityType}';
var _entity_code='${entityCode}';
var _org_type=0;
//alert("@@"+'${stateCode}');
	dwr.engine.setActiveReverseAjax(true);
</script>
<script type="text/javascript" src="js/updateStandardCode.js"></script>
</head>


<body onload="hideAll();">
	<section class="content">

                <div class="row">
                    <section class="col-lg-12">
      <form:form action="ModifyStandardCodes.htm" method="POST" commandName="Standard_Update" id="form1" class="form-horizontal">
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="ModifyStandardCodes.htm"/>" />

		<input type="hidden" name="Tiertype" id="Tiertype" value="<c:out value='${Tiertype}' escapeXml='true'></c:out>"/>
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Update Standard Code/Local Names(Urban)</h3>
                                </div>
              <c:if test="${empty distuser}">          
                 <div class="box-body">
                  <div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message code="Label.ENTITYSTANDARD" htmlEscape="true"></spring:message><font color="red">*</font></label>
					<div class="col-sm-6" >
							<form:select path="entityName" htmlEscape="true"  class="form-control" id="entity" onchange="getDetails();">



										<form:option value="" htmlEscape="true" >
											<esapi:encodeForHTMLAttribute><spring:message code="Label.SEL" htmlEscape="true"></spring:message></esapi:encodeForHTMLAttribute>
										</form:option>

										
										<form:options items="${getLocalGovtSetupList}" itemLabel="localBodyTypeName" itemValue="nomenclatureEnglish" htmlEscape="true" />
										

									</form:select>
									<div id="errorentity"></div>
					</div>
				</div>   
				
				
				<div id="ORGUNIT" style="Display:none" >
					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.SELOrganization" text="Select Organization Type" htmlEscape="true"></spring:message><font color="red">*</font></label>
						<div class="col-sm-6">
						 <form:select path="" class="form-control" id="topLevelEntityType"	onchange="getTopLevelEntityByType(this.value);">
											<form:option value="" htmlEscape="true"><spring:message code="Label.SELECT" text="-slect"></spring:message></form:option>
											<form:option value="2" htmlEscape="true"><spring:message code="Label.SELECTDEP" text="DEPARTMENT"></spring:message></form:option>
											<form:option value="3" htmlEscape="true"><spring:message code="Label.SELECTORG" text="ORGANIZATION"></spring:message></form:option>
											</form:select>
							</div>
							<span class="errormessage" id="errtopLevelEntityType"></span>
						</div>
											
					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.SELOrganization" text="Select Organization" htmlEscape="true"></spring:message><font color="red">*</font></label>
								<div class="col-sm-6">
								<form:select htmlEscape="true" path="orgCode" class="form-control"	id="orgCode"	onchange="getParentLevelEntity(this.value)">
											</form:select>
											<span class="errormessage" id="errorgCode"></span>
								
								</div>			
					</div>
											
						<div class="form-group">
							<label class="col-sm-3 control-label"><spring:message code="Label.SELOrgLevel" text="Select Level  of Organization" htmlEscape="true"></spring:message><font color="red">*</font></label>
								<div class="col-sm-6">
								 <form:select htmlEscape="true" path="" class="form-control"	id="orgTypeCode"	onchange="getparentOrganizations(this.value);">
											</form:select>
											<span class="errormessage" id="errorgTypeCode"></span>
								
								</div>
							</div>
									
					</div>
				
				<div style="Display:none" id="deptLI" class="form-group">
               		<label class="col-sm-3 control-label"> <spring:message code="Label.SELDEPT" htmlEscape="true"></spring:message> <span class="mandate">*</span> </label>
					  <div class="col-sm-6" >
					    <select id="deptList" name="deptList" class="form-control"  onchange="removedeptListError();divShowOrgBlock();">
								<option value=""><spring:message htmlEscape="true" code="Label.SEL"/></option>
								
								</select>
								<span class="errormessage" id="errdeptList"></span>
					  
					  </div>
				</div>
				
				<div class="form-group" id="ORG" style="Display: none">
				  <label for=category class="col-sm-3 control-label"> <spring:message code="Label.ORGLEVEL" htmlEscape="true"></spring:message> <font color="red">*</font></label>
				  <div class="col-sm-6">
							<form:select htmlEscape="true" path="" class="form-control" id="categoryOption" onchange="getCategoryWisesShowData(this.value,'org')" onfocus="validateOnFocus('adminUnitLevel');" onblur="vlidateOnblur('adminUnitLevel','1','15','c');">
									 						<form:option selected="selected" value="" label="--select--" />
													         <form:option value="S" label="State Level Organization" />
													         <form:option value="D@district" label="District Level Organization"  />
													         <form:option value="T@district#subdistrict" label="SubDistrict Level Organization" />
													         <form:option value="B@district#block" label="Block Level Organization" />
													         <form:option value="V@district#subdistrict#village" label="Village Level Organization" />
													         <form:option value="A@adminUnitLevel#parentUnitEntity" label="Administrative Unit Organization" />
									</form:select>
									<span class="errormessage" id="errcategoryOption"></span>
									<form:hidden  path="category" />
							
							
					</div>		
				</div>
				
					<div class="form-group" id="DEPT" style="Display: none">
						<label for=category class="col-sm-3 control-label"> <spring:message code="label.departmentLevel" htmlEscape="true"></spring:message> <font color="red">*</font></label>
						  <div class="col-sm-6">
						    <form:select htmlEscape="true" path="" class="form-control" id="categoryOptionDept" onchange="getCategoryWisesShowData(this.value,'dept')" onfocus="validateOnFocus('adminUnitLevel');" onblur="vlidateOnblur('adminUnitLevel','1','15','c');">
									 						<form:option selected="selected" value="" label="--select--" />
													         <form:option value="S" label="State Line Department" />
													         <form:option value="D@district" label="District Line Department"  />
													         <form:option value="T@district#subdistrict" label="SubDistrict Line Department" />
													         <form:option value="B@district#block" label="Block Line Department" />
													         <form:option value="V@district#subdistrict#village" label="Village Line Department" />
													         <form:option value="A@adminUnitLevel#parentUnitEntity" label="Administrative Unit Department" />
									</form:select>
									<span class="errormessage" id="errcategoryOptionDept"></span>
						  </div>
						</div>
				
				<div class="form-group" style="Display:none" id="adminUnitLevelLI">
               		<label class="col-sm-3 control-label"><spring:message code="Label.ADMINUNITLEVEL" htmlEscape="true"></spring:message> <span class="mandate">*</span>
               					</label>
					<div class="col-sm-6">
					 <select id="adminUnitLevel" name="entityCodes" class="form-control" onchange="fillParentUnitEntity(this.value)">
								<option value=""><spring:message htmlEscape="true" code="Label.SEL"/></option>
								
								</select>
								<span class="errormessage" id="erradminUnitLevel"></span>
					
					</div>
				</div>
				
				<div class="form-group" style="Display:none" id="parentUnitEntityLI">
               			<label class="col-sm-3 control-label"><spring:message code="sel.parent.admin.unit" text="Select Parent Administrative Unit" htmlEscape="true"></spring:message>
               						<span class="mandate">*</span></label>
						<div class="col-sm-6">
						<select id="parentUnitEntity" name="entityCodes" class="form-control" >
								<option value=""><spring:message htmlEscape="true" code="Label.SEL"/></option>
								
								</select>
								<span class="errormessage" id="errparentUnitEntity"></span>
						
						</div>
				</div>
				
				
				<div class="form-group" style="Display:none" id="districtLI">
               			<label class="col-sm-3 control-label"><spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message> <span class="mandate">*</span>
               					</label>
						<div class="col-sm-6">
						<select id="district" name="entityCodes" class="form-control" onchange="changeDistrict(this.value)">
								<option value=""><spring:message htmlEscape="true" code="Label.SEL"/></option>
								<c:forEach var="obj" items="${districtList}">
									<option  value="${obj.districtPK.districtCode}">
										<c:out value="${obj.districtNameEnglish}" escapeXml="true"></c:out>
									</option>
								</c:forEach>
								</select>
								<span class="errormessage" id="errdistrict"></span>
						
						</div>
				 </div>
				
				<div class="form-group" style="Display:none" id="subdistrictLI">
               					<label class="col-sm-3 control-label">
               						<spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message>
               						<span class="mandate">*</span>
               					</label>
								<div class="col-sm-6">
								  <select id="subdistrict" name="entityCodes" class="form-control" onchange="fillVillageList(this.value)">
									
								</select>
								<span class="errormessage" id="errsubdistrict"></span>
								</div>
							</div>
							
							<div class="form-group" style="Display:none" id="villageLI">
               					<label class="col-sm-3 control-label">
               						<spring:message code="Label.SELECTVILLAGE" htmlEscape="true" ></spring:message>
               						<span class="mandate">*</span>
               					</label>
               					<div class="col-sm-6">
               					<select id="village" name="entityCodes" class="form-control" onchange="removeVilageError();">
									
								</select>
								<span class="errormessage" id="errvillage" ></span>
               					
               					</div>
								
							</div>
							<div class="form-group" style="Display:none" id="blockLI">
               					<label class="col-sm-3 control-label">
               						<spring:message code="Label.SELBLOCK" htmlEscape="true"></spring:message>
               						<span class="mandate">*</span>
               					</label>
               					<div class="col-sm-6">
               					 <select id="block" name="entityCodes" class="form-control" onchange="removeBlockError();">
									
								</select>
								<span class="errormessage" id="errblock"></span>
               					</div>
								
							</div>
							
					<div id="ADMINLEVEL" style="Display: none">

                            <div class="form-group">
                               <label for="adminUnitLevel" class="col-sm-3 control-label"> <spring:message code="Label.SELDeptAdministrationUNIT" htmlEscape="true"></spring:message> <font color="red">*</font></label>
		                            <div class="col-sm-6">
		                            <form:select htmlEscape="true" path="adminCode" class="form-control" id="adminUnitLevelforEntity" onchange="getParentAdminUnitLevel(this.value)" onfocus="validateOnFocus('adminUnitLevel');" onblur="vlidateOnblur('adminUnitLevel','1','15','c');"/>
										<span class="errormessage" id="erradminUnitLevelforEntity"></span>
		                            
		                            </div>
                            
                            </div>
								<div class="form-group">		
									<label for="parentAdminUnitLevel" class="col-sm-3 control-label"> <spring:message code="sel.parent.admin.unit"  text="Select Parent Administrative Unit" htmlEscape="true"></spring:message> <font color="red">*</font></label>
									<div class="col-sm-6">
									 <form:select htmlEscape="true" path="parentAdminCode" class="form-control" id="parentAdminUnitLevel" onchange="removeError()" onfocus="validateOnFocus('adminUnitLevel');" onblur="vlidateOnblur('adminUnitLevel','1','15','c');">
											<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT"/></form:option>
											<form:options items="${parentAdminEntity}" itemLabel="adminLevelNameEng" itemValue="adminUnitCode" />
										</form:select>
										<span class="errormessage" id="errparentAdminUnitLevel"></span>
									</div>	
										

									</div>
				     </div>
				     
				     
				     <div id="DR" style="Display: none" class="form-control">
                       <label for="ddDistrict" class="col-sm-3 control-label"> <spring:message code="Label.DISTRICT" htmlEscape="true"></spring:message> <font color="red">*</font></label>
							<div class="col-sm-6">
							  	<form:select htmlEscape="true" path="districtCode" class="form-control" id="ddDistrict" style="width: 150px" onchange="remove_error1()" onfocus="validateOnFocus('ddDistrict');" onblur="vlidateOnblur('ddDistrict','1','15','c');">
											<form:option value="" htmlEscape="true" >Select District</form:option>
											<form:options items="${districtList}" itemLabel="districtNameEnglish" itemValue="districtPK.districtCode" htmlEscape="true" />
										</form:select>

										<div id="errordistrict"></div>
							
							
							</div>
						</div>
						
				<div id="IPAN" style="Display: none" class="form-group">
                     <label for="dispanchayat" class="col-sm-3 control-label"> <spring:message code="Label.DISTRICTPANCHYATNME" htmlEscape="true"></spring:message><font color="red">*</font>
										</label>
                    <div class="col-sm-6">
                     <form:select path="districtPanchyat" class="form-control" id="dispanchayat" style="width: 150px" onchange="remove_error1()" onfocus="validateOnFocus('ddDistrict');" onblur="vlidateOnblur('ddDistrict','1','15','c');" htmlEscape="true">
											<form:option value="" htmlEscape="true">
											<esapi:encodeForHTMLAttribute><spring:message code="Label.SELECTDISTICTPANCHAYAT" htmlEscape="true"></spring:message></esapi:encodeForHTMLAttribute>
											</form:option>
											<form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" htmlEscape="true" />
										</form:select>

										<div id="errordistrict"></div>
                    
                    </div>
			   </div>
			   
			   
			   <div id="VPAN" style="Display: none">
                        <div class="form-group">
                          <label class="col-sm-3 control-label"><spring:message code="Label.DISTRICTPANCHYATNME" htmlEscape="true"></spring:message><font color="red">*</font> </label>
                            <div class="col-sm-6">
                              <form:select path="disPanchyat" class="form-control" id="dispanchayatforvp"
													style="width: 150px" onchange="getIntermediatePanchayatbyDcode(this.value);">
													<form:option value="" htmlEscape="true">
														<esapi:encodeForHTMLAttribute><spring:message code="Label.SELECTDISTICTPANCHAYAT" htmlEscape="true"></spring:message></esapi:encodeForHTMLAttribute>
													</form:option>
													<form:options htmlEscape="true" items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" />
												</form:select>
								<div id="errordistrict2"></div>
                            
                            </div>
                        
                        </div>
                         <c:if test="${Tiertype eq 3}">
		                        <div class="form-group">
		                          
		                               <label for="vilpanchayat" class="col-sm-3 control-label"><spring:message code="Label.INTERPANCHYATNME" htmlEscape="true"></spring:message> <font color="red">*</font></label>
		                                   <div class="col-sm-6">
		                                    <form:select htmlEscape="true" path="intermediatePanchyat" class="form-control" id="vilpanchayat" style="width: 150px" onchange="remove_error2();" onfocus="validateOnFocus('ddSubdistrict');" onblur="vlidateOnblur('ddSubdistrict','1','15','c');">
															</form:select>
		                                      <div id="errorsubdistrict"></div>
		                             </div>
		                        </div>
                        </c:if>
                     </div>
                     
                     <div id="TR" style="Display: none">
                     <div class="form-group">
                          <label class="col-sm-3 control-label"><spring:message code="Label.DISTRICT" htmlEscape="true"></spring:message><font color="red">*</font></label> 
                          <div class="col-sm-6">
                             <form:select htmlEscape="true" path="districtCode" class="" id="ddDistrict2" style="width: 150px"
													onchange="getSubDistrictList(this.value);" onfocus="validateOnFocus('ddDistrict2');" onblur="vlidateOnblur('ddDistrict2','1','15','c');">
													<form:option value="" htmlEscape="true">Select District</form:option>
													<form:options htmlEscape="true" items="${districtList}" itemLabel="districtNameEnglish" itemValue="districtPK.districtCode" />
												</form:select>
												<div id="errordistrict1"></div
                          </div>
                       </div>
                       <div class="form-group">
                         <label for="ddSubdistrict" class="col-sm-3 control-label"><spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message><font color="red">*</font> </label> 
                          <div class="col-sm-6">
                            <form:select htmlEscape="true" path="subdistrictCodes" class="form-control" id="ddSubdistrict"
													style="width: 150px" onchange="remove_error2();" onfocus="validateOnFocus('ddSubdistrict');" onblur="vlidateOnblur('ddSubdistrict','1','15','c');">
												</form:select>
												<div id="errorsubdistrict1"></div>
                         
                          </div>
                       
                       </div>
                   </div>	
					
            </div> 
             
                       
        <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                  <button type="submit" name="Submit" id="Submit" class="btn btn-info" onclick="return validateAll();"  ><spring:message code="Button.GETDATA"></spring:message></button>
				  <button type="button" name="Submit2" class="btn btn-warning"  onclick="javascript:go('standard_Code_Urban.htm');" ><spring:message code="Button.CLEAR"></spring:message></button> 
				   <button type="button" name="Cancel" class="btn btn-danger"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'" ><spring:message code="Button.CLOSE"></spring:message></button>
                   <spring:bind path="stateCode"><input type="hidden" class="form-control" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
				   </spring:bind>
                 </div>
           </div>   
       </div> 
       
       </c:if>
       <c:if test="${!empty distuser}">
          <div class="box-body">
			<div class="form-group">
			<label for="entity" class="col-sm-3 control-label"><spring:message code="Label.ENTITYSTANDARD" htmlEscape="true"></spring:message></label> 
			 <div class="col-sm-6">
			   <form:select path="entityName" class="form-control" id="entity" onchange="getDetails_district();">
                       <form:option value="" htmlEscape="true">
										<esapi:encodeForHTMLAttribute><spring:message code="Label.SEL" htmlEscape="true"></spring:message></esapi:encodeForHTMLAttribute>
										</form:option>

										<optgroup label="<spring:message  htmlEscape="true" code='Label.LANDREGIONENTITY'></spring:message>">
											<option value="districtDistUser">
												<spring:message htmlEscape="true" code='Label.DISTRICT'></spring:message>
											</option>
											<option value="blockDistUser">
												<spring:message htmlEscape="true" code='Label.BLOCK'></spring:message>
											</option>
											<option value="subdistrictDistUser">
												<spring:message htmlEscape="true" code='Label.SUBDISTRICT'></spring:message>
											</option>
											<option value="villageDistUser">
												<spring:message htmlEscape="true" code='Label.VILLAGE'></spring:message>
											</option>

										</optgroup>

										<form:options htmlEscape="true"  items="${getLocalGovtSetupList}" itemLabel="localBodyTypeName" itemValue="nomenclatureEnglish" />
										
										

									</form:select>

									<div id="errorentity"></div>
			
			     </div>
			  </div>


					<div id="DTR" style="Display: none" class="form-group">
                            <label for="ddSubdistrict" class="col-sm-3 control-label"><spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message> <span id="required" class="errormsg">*</span> </label>
                            <div class="col-sm-6">
                              <form:select htmlEscape="true"  path="subdistrictCodes" class="form-control" id="ddSubdistrict" style="width: 150px" onchange="remove_error4();" onfocus="validateOnFocus('ddSubdistrict');" onblur="vlidateOnblur('ddSubdistrict','1','15','c');">
											<form:option value="">Select Subdistrict</form:option>
											<form:options items="${subdistrictList}" itemLabel="subdistrictNameEnglish" itemValue="subdistrictPK.subdistrictCode" />
										</form:select>

										<div id="errorsubdistrict"></div>
						   </div>
						</div>
								
								
					<div id="DISIPAN" style="Display: none" class="form-group">
                          <label for="distleveldispanchayat" class="col-sm-3 control-label"> <spring:message code="Label.DISTRICTPANCHYATNME" htmlEscape="true"></spring:message> <font color="red">*</font>
										</label>
                           <div class="col-sm-6">
                             <form:select htmlEscape="true"  path="districtPanchyat" class="form-control" id="distleveldispanchayat" style="width: 150px" onchange="remove_error1()" onfocus="validateOnFocus('ddDistrict');" onblur="vlidateOnblur('ddDistrict','1','15','c');">
											<form:option value="" htmlEscape="true">
											<esapi:encodeForHTMLAttribute><spring:message code="Label.SELECTDISTICTPANCHAYAT" htmlEscape="true"></spring:message></esapi:encodeForHTMLAttribute>
											</form:option>
											<form:options htmlEscape="true"  items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" />
										</form:select>

										<div id="errordistrict"></div>
                           </div>
                       </div>
							
					<div id="DISVPAN" style="Display: none">
							<div class="form-group">
							<label for="distleveldispanchayatforvp" class="col-sm-3 control-label"><spring:message code="Label.DISTRICTPANCHYATNME" htmlEscape="true"></spring:message><font color="red">*</font></label>
							<div class="col-sm-6">
							  <form:select htmlEscape="true" path="disPanchyat" class="form-control"
													id="distleveldispanchayatforvp"  onchange="getIPcodeforDistrictUser(this.value);">
													<form:option value="" htmlEscape="true">
													<esapi:encodeForHTMLAttribute><spring:message code="Label.SELECTDISTICTPANCHAYAT" htmlEscape="true"></spring:message></esapi:encodeForHTMLAttribute>
													</form:option>
													<form:options htmlEscape="true" items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" />
												</form:select>

												<div id="errordistrict2"></div>
							
							
							</div> 
							
				         </div>
						
						<c:if test="${Tiertype eq 3}">
                              <div class="form-group">
                               <label for="distlevelvilpanchayats" class="col-sm-3 control-label"><spring:message code="Label.INTERPANCHYATNME" htmlEscape="true"></spring:message> <font color="red">*</font> </label>
                                <form:select htmlEscape="true" path="intermediatePanchyat" class="form-control" id="distlevelvilpanchayats"  onchange="remove_error2();" onfocus="validateOnFocus('ddSubdistrict');" onblur="vlidateOnblur('ddSubdistrict','1','15','c');">
													</form:select>
                              <div id="errorsubdistrict"></div>
                              
                              </div>
													
                          </c:if>
									
						</div>
								
						
						</div>
						
		<div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                  <button type="submit" name="Submit" id="Submit" class="btn btn-info" onclick="return validateAllforDistrict();" value="<spring:message code="Button.GETDATA"></spring:message>" ></button>
				  <button type="button" name="Submit2" class="btn btn-warning" value="<spring:message code="Button.CLEAR"></spring:message>" onclick="javascript:go('standard_Code.htm');" ></button>
				  <button type="button" name="Cancel" class="btn btn-danger" value="<spring:message code="Button.CLOSE"></spring:message>" id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'" ></button>
                 </div>
           </div>   
         </div> 

	</c:if>
       
     </div>   
             
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script>

</body>
</html>