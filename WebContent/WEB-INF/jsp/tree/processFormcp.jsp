<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="in.nic.pes.lgd.utils.ApplicationConstant"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ include file="processFormJs.jsp"%>
<%@ include file="processFormCommanJs.jsp"%>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/createDepartment.js"></script>
<script src="js/common.js"></script>


<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrBlockService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/js/administrative-department.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/js/admin-department-validation.js'></script>


<script type="text/javascript">
	dwr.engine.setActiveReverseAjax(true);
	var _DISTRICT_LEVEL = '<%=ApplicationConstant.DISTRICT_LEVEL_CODE%>';
	var _SUBDISTRICT_LEVEL = '<%=ApplicationConstant.SUBDISTRICT_LEVEL_CODE%>';
	var _BLOCK_LEVEL = '<%=ApplicationConstant.BLOCK_LEVEL_CODE%>';
	var _VILLAGE_LEVEL = '<%=ApplicationConstant.VILLAGE_LEVEL_CODE%>';
	var _STATE_LEVEL = '<%=ApplicationConstant.STATE_LEVEL_CODE%>';
	var _ADMINISTRATIVE_LEVEL = '<%=ApplicationConstant.ADMINISTRATIVE_LEVEL_CODE%>';
	
	var accessLevel = '${pageAccessLevel}';
	var stateLevelId = '${stateCode}';
	var isOrganizationFlow = isParseJson('${isOrganizationFlow}');
	var isCenterUser = isParseJson('${isCenterUser}');
	
	$( document ).ready(function() {
		initialShowHide();
		registerOnclickMethods();
		
		
	});
</script>
</head>
<body>
	<c:choose>
		<c:when test="${fn:contains(pageAccessLevel, 'X')}">
		<c:set var="lbname" value="${adminOrgDeptForm.dpName}"></c:set>
		</c:when>
		<c:when test="${fn:contains(pageAccessLevel, 'Y')}">
		<c:set var="lbname" value="${adminOrgDeptForm.ipName}"></c:set>
		</c:when>
		<c:when test="${fn:contains(pageAccessLevel, 'Z')}">
		<c:set var="lbname" value="${adminOrgDeptForm.vpName}"></c:set>
		</c:when>
		<c:when test="${fn:contains(pageAccessLevel, 'U')}">
		<c:set var="lbname" value="${adminOrgDeptForm.uName}"></c:set>
		</c:when>
	</c:choose>
						
	<c:set var="deptOrgNameEng" value="Label.DEPTNAMEINENG"></c:set>
	<c:set var="deptOrgNameLocal" value="Label.DEPTNAMEINLOCAL"></c:set>
	<c:set var="deptOrgNameShort" value="Label.SHORTDEPTNAME"></c:set>
	<c:set var="deptOrgOperationalAt" value="Label.DEPTOPERATIONALAT"></c:set>
	<c:set var="formTitle" value="Label.ExtendDeptSetup"></c:set>
	<c:if test="${isOrganizationFlow}">
		<c:set var="formTitle" value="Label.ExtendOrgSetup"></c:set>
		<c:set var="deptOrgNameEng" value="Label.ORGNAMEINENG"></c:set>
		<c:set var="deptOrgNameLocal" value="Label.ORGNAMEINLOCAL"></c:set>
		<c:set var="deptOrgNameShort" value="Label.SHORTORGNAME"></c:set>
		<c:set var="deptOrgOperationalAt" value="Label.ORGOPERATIONALAT"></c:set>
	</c:if>
	<c:set var="titleLavelAdminNameArr" />
	
	<section class="content">
	<div class="row">
          <!-- main col -->
          <section class="col-lg-12">

          <div class="box">
		      <div class="box-header with-border">
		        <h3 class="box-title"><spring:message code="${formTitle}" htmlEscape="true"></spring:message></h3>
		      </div><!-- /.box-header -->
	
				<form:form id="adminOrgDeptForm" commandName="adminOrgDeptForm" class="form-horizontal">
				 <div class="box-body">
				<form:hidden path="formAccessLevel" value="${pageAccessLevel}" htmlEscape="true" />
				<form:hidden path="unitLevelCode" value="${unitLevelCode}" />
				<form:hidden path="parentUnitLevelCode" />
				<form:hidden path="sameLevel" />
				<form:hidden path="hierarchyIds" />
				<form:hidden path="olc" value="${adminOrgDeptForm.olc}"/>
				<form:hidden path="organizationFlow" />

				<c:if test="${(empty pageAccessLevel and ( isCenterUser or isOrganizationFlow))}">
				
				
					<div class="box-header subheading">
                  		<h4 class="box-title">
                  		<c:set var="organizationtitle" value="Label.SELDEPT" />
								<c:if test="${isCenterUser}">
									<c:set var="organizationtitle" value="Label.MINISTRIES" />
								</c:if>
								<spring:message code="${organizationtitle}" htmlEscape="true" /> 
                  	</h4>
               	    </div><!-- /.box-header -->
               	    
				
					<c:if test="${isCenterUser}">
						<div class="form-group">
							  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true"  code="Label.MINISTRIES"/> <span class="mandatory">*</span></label>
							  <div class="col-sm-6">
							  <form:select path="ministryId" id="ministryId" class="form-control" onchange="getDepartmentList1(this.value)">
										<form:option value="">
											<spring:message code="Label.SELECT" htmlEscape="true" />
										</form:option>
										<form:options items="${ministryList}" itemLabel="orgName" itemValue="orgCode" />
								</form:select> 
								<label id="err_ministry" class="errormsg"></label> 
								<form:errors path="ministryId" htmlEscape="true" cssClass="errormsg" />
							  </div>
						</div>
					</c:if>
					
					<c:if test="${isOrganizationFlow}">
					<div class="form-group">
							  <label  class="col-sm-3 control-label" for="sel1"><spring:message code="Label.DEPTLIST" htmlEscape="true"></spring:message> <span class="mandatory">*</span></label>
							  <div class="col-sm-6">
									<form:select path="deptOrgCode" id="deptOrgCode" class="form-control">
										<form:option value="">
											<spring:message code="Label.SELECT" htmlEscape="true" />
										</form:option>
										<form:options items="${listDepartments}" itemLabel="orgName" itemValue="orgCode" />
									</form:select>
									<label id="err_dept" class="errormsg" />
									<form:errors htmlEscape="true" path="deptOrgCode" cssClass="errormsg"></form:errors>
							  </div>
					</div>
					<div class="form-group">
							  <label  class="col-sm-3 control-label" for="sel1"><spring:message code="Label.ORGTYPELIST" htmlEscape="true"></spring:message>  <span class="mandatory">*</span></label>
							  <div class="col-sm-6">
									<form:select path="orgType" id="orgType" class="form-control">
										<form:option value="">
												<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
										</form:option>
										<form:options items="${listOrgTypes}" itemLabel="orgType" itemValue="orgTypeCode" />
									</form:select> 
									 <label id="err_org" class="errormsg"></label> 
									 <form:errors htmlEscape="true" path="orgType" cssClass="errormsg"></form:errors> 
							  </div>
					</div>
					</c:if>

				</c:if>


				<c:if test="${not empty pageAccessLevel}">
					<!-- Entity Selection Details Tags started -->
					<c:set var="lblChooseLevelAll"></c:set>
					<c:set var="lblChooseLevelSpecific"></c:set>
					<c:set var="titleLavel"></c:set>
					<c:set var="titleLavel"></c:set>
					<c:set var="titleGeneral"></c:set>
					<c:choose>
						<c:when test="${fn:contains(pageAccessLevel, 'X')}">
									
									<c:set var="lblChooseLevelAll" value="label.all"></c:set>
									<c:if test="${!isCenterUser}">
										<c:set var="lblChooseLevelAll" value="label.all"></c:set>
									</c:if>
									<c:set var="lblChooseLevelSpecific" value="label.specific"></c:set>
									<c:set var="titleLavel" value="Label.dpLevel"></c:set>	
									<c:set var="titleGeneral" value="Label.DISTLBDEPT"></c:set>
									<c:if test="${isOrganizationFlow}">
									<c:set var="titleGeneral" value="Label.DISTLBORG"></c:set>		
									</c:if>
								</c:when>
								<c:when test="${fn:contains(pageAccessLevel, 'Y')}">
								
									<c:set var="lblChooseLevelAll" value="label.all"></c:set>
									<c:set var="lblChooseLevelSpecific" value="label.specific"></c:set>
									<c:set var="titleLavel" value="Label.INTERMIDIATELB"></c:set>
									<c:set var="titleGeneral" value="Label.INTERMIDIATELBDEPT"></c:set>
									<c:if test="${isOrganizationFlow}">
									<c:set var="titleGeneral" value="Label.INTERMIDIATELBORG"></c:set>		
									</c:if>
								</c:when>
								<c:when test="${fn:contains(pageAccessLevel, 'Z')}">
										<c:set var="lblChooseLevelAll" value="Label.ALLVILLAGELBSTATE"></c:set>
									<c:set var="lblChooseLevelSpecific" value="Label.SPECIFICVILLAGELB"></c:set>
									<c:set var="titleLavel" value="Label.VILLAGELB"></c:set>
									<c:set var="titleGeneral" value="Label.VILLAGELBDETP"></c:set>
									<c:if test="${isOrganizationFlow}">
									<c:set var="titleGeneral" value="Label.VILLAGELBORG"></c:set>		
									</c:if>
								</c:when>
						<c:when test="${fn:contains(pageAccessLevel, 'S')}">
							<c:set var="lblChooseLevelAll" value="Label.ALLSTATE"></c:set>
							<c:set var="lblChooseLevelSpecific" value="Label.SpecificStatesIndia"></c:set>
							<c:set var="titleLavel" value="Label.STATELEVEL"></c:set>
							<c:set var="titleGeneral" value="Label.STATEDEPT"></c:set>
							<c:if test="${isOrganizationFlow}">
								<c:set var="titleGeneral" value="Label.STATEORG"></c:set>
							</c:if>
						</c:when>
						<c:when test="${fn:contains(pageAccessLevel, 'D')}">
							<c:set var="lblChooseLevelAll" value="Label.ALLDISTRICT"></c:set>
							<c:if test="${!isCenterUser}">
								<c:set var="lblChooseLevelAll" value="Label.ALLDISTRICTSTATE"></c:set>
							</c:if>
							<c:set var="lblChooseLevelSpecific" value="Label.SPECIFICDISTRICT"></c:set>
							<c:set var="titleLavel" value="Label.DISTRICTTRADNME"></c:set>
							<c:set var="titleGeneral" value="Label.DISTDEPT"></c:set>
							<c:if test="${isOrganizationFlow}">
								<c:set var="titleGeneral" value="Label.DISTORG"></c:set>
							</c:if>
						</c:when>
						<c:when test="${fn:contains(pageAccessLevel, 'T')}">
							<c:set var="lblChooseLevelAll" value="Label.ALLSUBDISTRICTSTATE"></c:set>
							<c:set var="lblChooseLevelSpecific" value="Label.SPECIFICSUBDISTRICT"></c:set>
							<c:set var="titleLavel" value="Label.SUBDISTRICTLVL"></c:set>
							<c:set var="titleGeneral" value="Label.SUBDISTDEPT"></c:set>
							<c:if test="${isOrganizationFlow}">
								<c:set var="titleGeneral" value="Label.SUBDISTORG"></c:set>
							</c:if>
						</c:when>
						<c:when test="${fn:contains(pageAccessLevel, 'B')}">
							<c:set var="lblChooseLevelAll" value="Label.ALLBLOCK"></c:set>
							<c:set var="lblChooseLevelSpecific" value="Label.SPECIFICBLOCK"></c:set>
							<c:set var="titleLavel" value="Label.BLOCKLEVEL"></c:set>
							<c:set var="titleGeneral" value="Label.BLOCKDEPT"></c:set>
							<c:if test="${isOrganizationFlow}">
								<c:set var="titleGeneral" value="Label.BLOCKORG"></c:set>
							</c:if>
						</c:when>
						<c:when test="${fn:contains(pageAccessLevel, 'V')}">
							<c:set var="lblChooseLevelAll" value="Label.ALLVILLAGESTATE"></c:set>
							<c:set var="lblChooseLevelSpecific" value="Label.SPECIFICVILLAGE"></c:set>
							<c:set var="titleLavel" value="Label.VILLAGELVL"></c:set>
							<c:set var="titleGeneral" value="Label.VILLAGEDETP"></c:set>
							<c:if test="${isOrganizationFlow}">
								<c:set var="titleGeneral" value="Label.VILLAGEORG"></c:set>
							</c:if>
						</c:when>
						<c:when test="${fn:contains(pageAccessLevel, 'A')}">
							<c:set var="lblChooseLevelAll" value="Label.ALLADMINUNIT"></c:set>
							<c:set var="lblChooseLevelSpecific" value="Label.SPECADMINUNIT"></c:set>
							<c:set var="titleLavel" value="Label.ADMINUNITLEVEL"></c:set>
							<c:set var="titleGeneral" value="Label.ADMINDEPT"></c:set>
							<c:if test="${isOrganizationFlow}">
								<c:set var="titleGeneral" value="Label.ADMINORG"></c:set>
							</c:if>
							<c:set var="titleLavelAdminNameArr" value="${adminOrgDeptForm.adminUnitLevelName}" />
						</c:when>
					</c:choose>
				</c:if>


				<!-- General Details started -->
				<div class="box-header subheading">
                  		<h4 class="box-title">
                  		<spring:message code="Label.GENERALDETAILS" htmlEscape="true" />
							<c:out value="-" />
							
							<c:choose>
								<c:when test="${ fn:contains(pageAccessLevel, 'X')  or fn:contains(pageAccessLevel, 'Y') or fn:contains(pageAccessLevel, 'Z') or fn:contains(pageAccessLevel, 'U')}">
										<spring:message code="label.entity.level" arguments="${lbname}" argumentSeparator=","></spring:message>
								
								</c:when>
								<c:otherwise>
									<spring:message htmlEscape="true" code="${titleLavel}" />
									<c:if test="${fn:contains(pageAccessLevel, 'A')}">
									<c:out value="(${titleLavelAdminNameArr})"/>
									</c:if>
								</c:otherwise>
							</c:choose> 
                  	</h4>
               	    </div><!-- /.box-header -->
				<div class="form-group">
				<div class="col-sm-8">
					<c:if test="${not empty pageAccessLevel}">
						<div class="form-group">
							  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.SELPARENT" /> <span class="mandatory">*</span></label>
							  <div class="col-sm-6">
									 <form:select path="parentDepartmentName" id="parent" class="form-control" style="width: 200px">
											<c:forEach var="existDeptList" items="${exitParentList}">
												<option value="${existDeptList.rno}"><c:out value="${existDeptList.childName}" escapeXml="true"></c:out></option>
											</c:forEach>
											<c:forEach var="newParentList" items="${newParentList}">
												<option value="${newParentList.departmentNameEnglish}"><c:out value="${newParentList.departmentNameEnglish}" escapeXml="true"></c:out></option>
											</c:forEach>
									  </form:select>
									  <form:errors path="" htmlEscape="true" cssClass="errormsg" />
							  </div>
						</div>
						
					</c:if>
					<div class="form-group">
						  <label  class="col-sm-3 control-label" for="sel1"><spring:message code='${deptOrgNameEng}' htmlEscape="true"/> <span class="mandatory">*</span></label>
						  <div class="col-sm-6">
						  	<form:input path="departmentNameEnglish" id="deptNameEnglish" style="width: 200px" class="frmfield" htmlEscape="true" maxlength="200"/>
						  	<label id="err_deptName" class="errormsg"></label>
						  	<div id="errdeptNameEnglish"  class="errormsg"></div>
							<form:errors htmlEscape="true" path="departmentNameEnglish" cssClass="errormsg"/>
						  </div>
					</div>
					<div class="form-group">
						  <label  class="col-sm-3 control-label" for="sel1"><spring:message code='${deptOrgNameLocal}' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
						  <form:input path="departmentNameLocal" id="deptNameLocal" style="width: 200px" class="frmfield" htmlEscape="true" maxlength="80"/>
						  <form:errors htmlEscape="true" path="" cssClass="errormsg"/>
						  </div>
					</div>
					<div class="form-group">
						  <label  class="col-sm-3 control-label" for="sel1"><spring:message code='${deptOrgNameShort}' htmlEscape="true"/> </label>
						  <div class="col-sm-6">
						  <form:input path="departmentShortName" id="sortNameDept" style="width: 200px" class="frmfield" htmlEscape="true" maxlength="10"/>
						  </div>
					</div>
								
</div>
<div class="col-sm-4">
						
						<div class="block"> 
						<c:if test="${not empty newOfficeList || not empty existOfficeList }">
						<spring:message code="Label.ExistOffices" htmlEscape="true" />
						<c:choose>
						<c:when test="${fn:contains(pageAccessLevel, 'A') and (not empty unitCodesName)}">
						 <c:out value="${unitCodesName}" escapeXml="true"/>
								<spring:message code="Label.LEVEL" htmlEscape="true" />
						</c:when>
						<c:otherwise>
						<spring:message htmlEscape="true" code="${titleLavel}" />
						</c:otherwise>
						
						</c:choose>
						<c:out value="-" />
						<ol>
						<c:forEach var="existOfficeList" varStatus="rowVal" items="${existOfficeList}">
						<li><c:out value="${existOfficeList.adminLevelNameEng}" escapeXml="true"></c:out> </li>
						</c:forEach>
						<c:forEach var="newOfficeList" varStatus="rowVal" items="${newOfficeList}">
						<li><c:out value="${newOfficeList.departmentNameEnglish}" escapeXml="true"></c:out> </li>
						</c:forEach>
						</ol>
						</c:if>
						</div>
						
						<div id="showtree">
							<div id="base" class="tree"></div>
						</div>
				</div>			
				</div>	
				<!-- General Details block ended here -->

				<!-- Display Specific Location Details Here -->
				<c:if test="${not empty pageAccessLevel }">
					<div class="box-header subheading">
	                  		<h4 class="box-title">
	                  		<c:choose>
									<c:when test="${ fn:contains(pageAccessLevel, 'X')  or fn:contains(pageAccessLevel, 'Y') or fn:contains(pageAccessLevel, 'Z') or fn:contains(pageAccessLevel, 'U')}">
											<spring:message code="label.entity.level" arguments="${lbname}" argumentSeparator=","></spring:message>
									
									</c:when>
									<c:otherwise>
										<spring:message htmlEscape="true" code="${titleLavel}" />
									</c:otherwise>
								</c:choose>
	                  	</h4>
	               	    </div><!-- /.box-header -->
               	    
               	    	<c:choose>
							<c:when test="${ fn:contains(pageAccessLevel, 'X')  or fn:contains(pageAccessLevel, 'Y') or fn:contains(pageAccessLevel, 'Z') or fn:contains(pageAccessLevel, 'U')}">
								<div class="form-group">
  									<label  class="col-sm-1 control-label" for="sel1"></label>
									  <div class="col-sm-6">
									  <label class="radio-inline">
									   		<form:radiobutton path="chooseLevelAllOrSpecific" id="rdAllStateDis" htmlEscape="true" value="F" onclick="showAllOrSpecificDetails(this.value);" />
									   		<spring:message code="label.all.entity.of.state" arguments="${lbname}" argumentSeparator=","></spring:message>
									  </label>
									  <label class="radio-inline">
									  	<form:radiobutton path="chooseLevelAllOrSpecific" value="S" id="rdSpecificStateDis" checked="true" onclick="showAllOrSpecificDetails(this.value);" /> 
										<spring:message code="label.specific.entity" arguments="${lbname}" argumentSeparator=","></spring:message>
									  </label>
									  
									  </div>
								</div>
								
								
							</c:when>
								<c:otherwise>
									<div class="form-group">
		  									<label  class="col-sm-1 control-label" for="sel1"></label>
											  <div class="col-sm-6">
											  <label class="radio-inline">
											   		<form:radiobutton path="chooseLevelAllOrSpecific" id="rdAllStateDis" htmlEscape="true" value="F" onclick="showAllOrSpecificDetails(this.value);" /> 
											  		<spring:message htmlEscape="true" code="${lblChooseLevelAll}"  />
											  </label>
											  <label class="radio-inline">
											  		<form:radiobutton path="chooseLevelAllOrSpecific" value="S" id="rdSpecificStateDis" checked="true" onclick="showAllOrSpecificDetails(this.value);" />
													 <spring:message htmlEscape="true" code="${lblChooseLevelSpecific}" />
											  </label>
											  
											  </div>
										</div>
								</c:otherwise>
							</c:choose>												
							<div class="form-group">
								<c:set var="domainNameErrors">
											<form:errors path="setErrorMsg" />
								</c:set> 
								<c:if test="${not empty domainNameErrors}">
									<div style="height: 40px; border: 1px solid red; background-color: #FFEBE8; padding-top: 10px;" align="center">
										<label id="errorCommon"><c:out value="${domainNameErrors}" escapeXml="true"></c:out></label>
									</div>
								</c:if>
								<div id="coverageError" style="display: none;height: 40px; border: 1px solid red; background-color: #FFEBE8; padding-top: 10px;" align="center" >
										<span class="errormessage" id="errCoverage"></span>
								</div>
							</div>
						<div id="tblAllSpecificDetails">
						<!-- State Level  Tags Started -->
						
				<c:if test="${isCenterUser == true}">
						<c:if test="${ fn:contains(pageAccessLevel, 'S')  or fn:contains(pageAccessLevel, 'D') or fn:contains(pageAccessLevel, 'T') or fn:contains(pageAccessLevel, 'V')}">

							<c:if test="${fn:contains(pageAccessLevel, 'D')	  or fn:contains(pageAccessLevel, 'T') or fn:contains(pageAccessLevel, 'V')}">
							<div class="col-sm-12" id=" tblForFullAndParialCoverage">
					  			<form:checkbox name="checkbox" id="ChkFullCoverage" value="Full" path="ChkFullCoveragestate" htmlEscape="true" onclick="StateFullPart();" />
								<spring:message htmlEscape="true" code="Label.STATEFULLCOVERAGE" />
								<label id="err_statechk" class="errormsg"></label>
								<div class="hr_dept"></div>
					  		</div>
							</c:if>

							 <div class="box-body dept_list_button" id="tblStateSpecific" >
								<div class="ms_container row"  >
										<div class="ms_selectable col-sm-5 form-group">
											<label for="ddSourceVillageRLBS"><spring:message htmlEscape="true" code="Label.STATELIST" /></label>
											<form:select path="" id="ddSourceState" multiple="true" htmlEscape="true" class="form-control">
												<c:forEach var="state" items="${statelist}">
													<form:option value="${state.statePK.stateCode}"><c:out value="${state.stateNameEnglish }" escapeXml="true"></c:out></form:option>
												</c:forEach>
											</form:select>
										</div>
										<div class="ms_buttons col-sm-2 dbtn_align ">
											<button type="button" class="btn btn-primary btn-xs btn-block" id="mvFrState"> &gt;&gt;</button>
											<button type="button" class="btn btn-primary btn-xs btn-block" id="mvBackState"> &lt;&lt;</button>
										</div>
										<div class="ms_selection col-sm-5">
											<div class="form-group">
												<label for="ddDestVillageRLBs"><spring:message htmlEscape="true" code="Label.STATELISTSELECTED" /></label> 
												<form:select path="stateIds" id="ddTargetState" multiple="true" class="form-control" />
												<label id="err_state" class="errormsg"></label>
											</div>
										</div>
									</div>	
								</div>
								 <div class="col-sm-12" id="tblPartialCoverage">
						  				<form:checkbox name="checkbox" id="ChkPartialCoverage" value="Part" path="ChkPartialCoverage" onclick="StatePartialPart();" />
										<spring:message htmlEscape="true" code="Label.STATEPARTCOVERAGE" />
										<div class="hr_dept"></div>
					  			</div> 
								
							</c:if>
					</c:if>
 <!-- State Level  Tags Ends Here -->

<!--  --------------------------------------------------------------------------------------- Urban Block start here      ---------------------------------------------------------------------------------  -->
	  <c:if test="${ fn:contains(pageAccessLevel, 'U')}">
	  <div class="box-body dept_list_button" id="tblStateSpecific" >
		<div class="ms_container row"  >
				<div class="ms_selectable col-sm-5 form-group">
					<label for="ddSourceVillageRLBS"><spring:message code="label.avilable.entity.list" arguments="${adminOrgDeptForm.uName}" argumentSeparator=","></spring:message></label>
					<form:select path=""  id="sourceUList" multiple="true"  class="form-control" htmlEscape="true">
						<form:options items="${urbanList}" itemValue="localBodyCode" itemLabel="localBodyNameEnglish" htmlEscape="true"/>
					</form:select>
				</div>
				<div class="ms_buttons col-sm-2 dbtn_align">
					<button type="button" class="btn btn-primary btn-xs btn-block" onclick="moveElement('sourceUList','targetUList',null)"> &gt;&gt;</button>
					<button type="button" class="btn btn-primary btn-xs btn-block" onclick="moveElement('targetUList','sourceUList',null)"> &lt;&lt;</button>
				</div>
				<div class="ms_selection col-sm-5">
					<div class="form-group">
						<label for="ddDestVillageRLBs"><spring:message code="label.contribute.entity.list" arguments="${adminOrgDeptForm.uName}" argumentSeparator=","></spring:message></label> 
							<form:select path="urbanIds" id="targetUList" multiple="true"  class="form-control" htmlEscape="true"/>
						<label id="err_ulevel" class="errormsg"></label>	
					</div>
				</div>
			</div>	
		</div>
		</c:if>
<!-- ----------------------------------------------------------------------------Urban  Block end here  ------------------------------------------------  -->
										
<!--  --------------------------------------------------------------------------------------- District Panchayat Block start here      ---------------------------------------------------------------------------------  -->
<c:if test="${ fn:contains(pageAccessLevel, 'X')}">
<div class="box-body dept_list_button" id="tblStateSpecific" >
	<div class="ms_container row" >
		<div class="ms_selectable col-sm-5 form-group">
			<label for="ddSourceVillageRLBS"><spring:message code="label.avilable.entity.list" arguments="${adminOrgDeptForm.dpName}" argumentSeparator=","></spring:message></label>
			<form:select path=""  id="sourceDPList" multiple="true"  class="form-control" htmlEscape="true">
				<form:options items="${districtPanchayatList}" itemValue="localBodyCode" itemLabel="localBodyNameEnglish" htmlEscape="true"/>
			</form:select>	
		</div>
		<div class="ms_buttons col-sm-2 dbtn_align">
			<button type="button" class="btn btn-primary btn-xs btn-block" onclick="moveElement('sourceDPList','targetDPList',null)"> &gt;&gt;</button>
			<button type="button" class="btn btn-primary btn-xs btn-block" onclick="moveElement('targetDPList','sourceDPList',null)"> &lt;&lt;</button>
		</div>
		<div class="ms_selection col-sm-5">
			<div class="form-group">
				<label for="ddDestVillageRLBs"><spring:message code="label.contribute.entity.list" arguments="${adminOrgDeptForm.dpName}" argumentSeparator=","></spring:message></label> 
					<form:select path="dpIds" id="targetDPList" multiple="true"  class="form-control" htmlEscape="true"/>
				<label id="err_dplevel" class="errormsg"></label>	
			</div>
		</div>
	</div>	
</div>
</c:if>
<!-- ----------------------------------------------------------------------------District Panchayat Block end here  ------------------------------------------------  -->
<!--  --------------------------------------------------------------------------------------- Intermediate Panchayat Block start here      ---------------------------------------------------------------------------------  -->

<!-- FULL District Coverage in IP#staerted -->
<c:if test="${ fn:contains(pageAccessLevel, 'Y') or fn:contains(pageAccessLevel, 'Z')}">
	<div class="col-sm-12">
		<form:checkbox path="DPChkfull" id="DPChkFull"   htmlEscape="true" onclick="showHideDiv('DPCHK');" value="DPFULL" />
		 <spring:message code="label.entity.with.full.coverage" arguments="${adminOrgDeptForm.dpName}" argumentSeparator=","></spring:message> 
		 <label id="err_DPchk" class="errormsg"></label>
		 <div class="hr_dept"></div>
	</div>

</c:if>
<c:if test="${ fn:contains(pageAccessLevel, 'Y') or fn:contains(pageAccessLevel, 'Z')}">
<div class="box-body dept_list_button" id="tblDPFULL" style="display: none">
<div class="ms_container row"   >
		<div class="ms_selectable col-sm-5 form-group">
			<label for="ddSourceVillageRLBS"><spring:message code="label.avilable.entity.list" arguments="${adminOrgDeptForm.dpName}" argumentSeparator=","></spring:message></label>
			<form:select path=""  id="sourceDPList" multiple="true"  class="form-control" htmlEscape="true">
				<form:options items="${districtPanchayatList}" itemValue="localBodyCode" itemLabel="localBodyNameEnglish" htmlEscape="true"/>
			</form:select>		
		</div>
		<div class="ms_buttons col-sm-2 dbtn_align">
			<button type="button" class="btn btn-primary btn-xs btn-block" onclick="moveElement('sourceDPList','targetDPList','IPChangePartialCoverage')"> &gt;&gt;</button>
			<button type="button" class="btn btn-primary btn-xs btn-block" onclick="moveElement('targetDPList','sourceDPList','IPChangePartialCoverage')"> &lt;&lt;</button>
		</div>
		<div class="ms_selection col-sm-5">
			<div class="form-group">
				<label for="ddDestVillageRLBs"><spring:message code="label.contribute.entity.list" arguments="${adminOrgDeptForm.dpName}" argumentSeparator=","></spring:message></label> 
					<form:select path="dpIds" id="targetDPList" multiple="true"  class="form-control" htmlEscape="true"/>
				<label id="err_dplevel" class="errormsg"></label>	
			</div>
		</div>
	</div>
</div>
</c:if>

<!-- FULL District Coverage in IP#end -->
<!-- Part District Coverage in IP#started -->
<c:if test="${ fn:contains(pageAccessLevel, 'Y')  or fn:contains(pageAccessLevel, 'Z')}">
	<c:if test="${ fn:contains(pageAccessLevel, 'Y') }" > 
		<div class="col-sm-12">
			<form:checkbox path="DPChkfull" id="DPChkpart" onclick="showHideDiv('IPCHK');" value="DPPART" />
			<spring:message code="label.entity.with.partial.coverage" arguments="${adminOrgDeptForm.dpName}" argumentSeparator=","></spring:message>
			<div class="hr_dept"></div>
		</div>
	</c:if>
	 <c:if test="${ fn:contains(pageAccessLevel, 'Z') }" > 
	 	<div class="col-sm-12">
			<form:checkbox path="DPChkfull" id="DPChkpart" onclick="showHideDiv('IPCHK');" value="DPPART" />
			<spring:message code="label.entity.with.full.coverage" arguments="${adminOrgDeptForm.ipName}" argumentSeparator=","></spring:message>
			<div class="hr_dept"></div>
		</div>
	</c:if>
</c:if>

<c:if test="${ fn:contains(pageAccessLevel, 'Y')  or fn:contains(pageAccessLevel, 'Z')}">
	<div class="form-group" id="tblDPSelectBox" style="display: none">
	  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.SELECT" /><c:out value=" ${adminOrgDeptForm.dpName}" /><span class="mandatory">*</span></label>
	  <div class="col-sm-6">
		<select id="dpSelectBox"  class="form-control" style="width: 200px" onchange="getLBChild(this.value,'sourceIPList','listBox')">
			<option value="">Select</option>
			<c:forEach var="obj" items="${districtPanchayatList}">
				<option value="${obj.localBodyCode}"> <c:out value="${obj.localBodyNameEnglish}" ></c:out></option>
			</c:forEach>
		</select>
    </div>
   </div>
</c:if>

<c:if test="${fn:contains(pageAccessLevel, 'Y') or fn:contains(pageAccessLevel, 'Z')}">
<div class="box-body dept_list_button" id="tblIPSpecific" style="display: none"  >
	<div class="ms_container row" >
		<div class="ms_selectable col-sm-5 form-group">
			<label for="ddSourceVillageRLBS"><spring:message code="label.contribute.entity.list" arguments="${adminOrgDeptForm.ipName}" argumentSeparator=","></spring:message></label>
			<form:select path=""  id="sourceIPList" multiple="true"  class="form-control" htmlEscape="true"></form:select>		
		</div>
		<div class="ms_buttons col-sm-2 dbtn_align">
			<button type="button" class="btn btn-primary btn-xs btn-block" onclick="moveElement('sourceIPList','targetIPList',null)"> &gt;&gt;</button>
			<button type="button" class="btn btn-primary btn-xs btn-block" onclick="moveElement('targetIPList','sourceIPList',null)"> &lt;&lt;</button>
		</div>
		<div class="ms_selection col-sm-5">
			<div class="form-group">
				<label for="ddDestVillageRLBs"><spring:message code="label.contribute.entity.list" arguments="${adminOrgDeptForm.ipName}" argumentSeparator=","></spring:message></label> 
				<form:select path="ipIds" id="targetIPList" multiple="true"  class="form-control" htmlEscape="true"/>
				<label id="err_iplevel" class="errormsg"></label>	
			</div>
		</div>
	</div>	
	</div>
</c:if> 
<!-- ----------------------------------------------------------------------------Intermediate Panchayat Block end here  ------------------------------------------------  -->
<!-- ----------------------------------------------------------------------------Village Panchayat Block start here  ------------------------------------------------  -->																		
<c:if test="${ fn:contains(pageAccessLevel, 'Z')}">
	<div class="col-sm-12">
		<form:checkbox path="DPChkfull" id="DPChkpartVP" onclick="showHideDiv('VPCHK');" value="DPPART" />
		<spring:message code="label.entity.with.partial.coverage" arguments="${adminOrgDeptForm.ipName}" argumentSeparator=","></spring:message>
		<div class="hr_dept"></div>
	</div>
	<div class="form-group" id="tblDPSelectBoxVP" style="display: none">
	  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.SELECT" /><c:out value=" ${adminOrgDeptForm.dpName}" /><span class="mandatory">*</span></label>
	  <div class="col-sm-6">
		<select id="dpSelectBoxvp"  class="form-control" style="width: 200px" onchange="getLBChild(this.value,'ipSelectBoxvp','selectBox')">
				<option value="">Select</option>
				<c:forEach var="obj" items="${districtPanchayatList}">
					<option value="${obj.localBodyCode}"> <c:out value="${obj.localBodyNameEnglish}" ></c:out></option>
				</c:forEach>
		</select>
      </div>
   </div>

	<div class="form-group" id="tblIPSelectBoxVP" style="display: none">
	  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.SELECT" /><c:out value=" ${adminOrgDeptForm.ipName}" /><span class="mandatory">*</span></label>
	  <div class="col-sm-6">
		<select id="ipSelectBoxvp"  class="form-control" style="width: 200px" onchange="getLBChild(this.value,'sourceVPList','listBox')">
			<option value="">Select</option>
		</select>
      </div>
   </div>
<div class="box-body dept_list_button" id="tblVPSpecific" style="display: none">
<div class="ms_container row"  >
	<div class="ms_selectable col-sm-5 form-group">
		<label for="ddSourceVillageRLBS"><spring:message code="label.contribute.entity.list" arguments="${adminOrgDeptForm.vpName}" argumentSeparator=","></spring:message></label>
		<form:select path=""  id="sourceVPList" multiple="true"  class="form-control" htmlEscape="true">		</form:select>
	</div>
	<div class="ms_buttons col-sm-2 dbtn_align">
		<button type="button" class="btn btn-primary btn-xs btn-block"  onclick="moveElement('sourceVPList','targetVpList',null)"> &gt;&gt;</button>
		<button type="button" class="btn btn-primary btn-xs btn-block" onclick="moveElement('targetVpList','sourceVPList',null)"> &lt;&lt;</button>
	</div>
	<div class="ms_selection col-sm-5">
		<div class="form-group">
			<label for="ddDestVillageRLBs"><spring:message code="label.contribute.entity.list" arguments="${adminOrgDeptForm.vpName}" argumentSeparator=","></spring:message></label> 
			<form:select path="vpIds" id="targetVpList" multiple="true"  class="form-control" htmlEscape="true"/>
			<label id="err_vplevel" class="errormsg"></label>	
		</div>
	</div>
</div>
</div>	
</c:if>
	<!-- ----------------------------------------------------------------------------Village Panchayat Block end here  ------------------------------------------------  -->														
													
<c:if test="${adminUnitCondition}">
	<div class="box-body dept_list_button" id="tblAdminUnitsConditional" >
		<div class="ms_container row"  >
			<div class="ms_selectable col-sm-5 form-group">
				<label for="ddSourceVillageRLBS">Administrative Entity List - <br/>Conditional</label>
				<form:select path="" id="ddAdminEnitiyConditional" multiple="true"  class="form-control" htmlEscape="true">
					<form:options items="${adminEntities}" itemValue="adminUnitEntityCode" itemLabel="adminEntityNameEnglish" htmlEscape="true"/>
				</form:select>		
			</div>
			<div class="ms_buttons col-sm-2 dbtn_align">
				<button type="button" class="btn btn-primary btn-xs btn-block"  id="mvFrAdminEntity"> &gt;&gt;</button>
				<button type="button" class="btn btn-primary btn-xs btn-block" id="mvBackAdminEntity"> &lt;&lt;</button>
			</div>
			<div class="ms_selection col-sm-5">
				<div class="form-group">
					<label for="ddDestVillageRLBs">Selected Administrative Enities - <br/>Conditional</label> 
					<form:select path="adminUnitEntityIds"  id="ddTargetAdminEnitiyConditional" multiple="true"  class="form-control" htmlEscape="true"/>
				</div>
			</div>
		</div>
	</div>	
	</c:if>
<!-- District Level  Tags Started --> 
<c:if test="${isCenterUser == true}">
	<c:if test="${ fn:contains(pageAccessLevel, 'D')}">
		 <div class="form-group" id="tblStateList">
			  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.SELECTSTATE" /></label>
			  <div class="col-sm-6">
				<form:select path="" class="form-control" id="stateName" onchange="getDistrictsforVillageS();"></form:select>
		      </div>
	   </div>
	</c:if>
</c:if>
 <c:if test="${ fn:contains(pageAccessLevel, 'D') or fn:contains(pageAccessLevel, 'T') or fn:contains(pageAccessLevel, 'B') or fn:contains(pageAccessLevel, 'V')}">
<!-- Drop-down District with full Coverage used in Sub-district-->
	<c:if test="${ fn:contains(pageAccessLevel, 'T') or fn:contains(pageAccessLevel, 'B') or fn:contains(pageAccessLevel, 'V')}">
		<div class="col-sm-12" id="tblCheckCoverageDistrictFull">
				<form:checkbox path="districtChkfull" id="districtChkFull" name="checkbox" value="DFull" htmlEscape="true" onclick="toggle2();" />
				<spring:message htmlEscape="true" code="Label.DISTRICTFULLCOVERAGE" />
				<label id="err_districtchk" class="errormsg"></label> 
				<div class="hr_dept"></div>
				
		</div>
												
	</c:if>
<!-- Drop-down District with full Coverage used in Sub-district  Ended -->
	<c:if test="${isCenterUser == true}">
		<c:if test="${ fn:contains(pageAccessLevel, 'T') or fn:contains(pageAccessLevel,'V')}">
			<div class="form-group" id="tblStateList1">
				  <label  class="col-sm-3 control-label" for="stateName1"><spring:message htmlEscape="true" code="Label.SELECTSTATE" /></label>
				  <div class="col-sm-6">
					 <form:select path="" class="form-control" id="stateName1" onchange="getDistrictsforVillageS();"></form:select>
			      </div>
	   		</div>
		</c:if>
	</c:if>
	<div class="box-body dept_list_button" id="tblDistrictSpecific" >
		<div class="ms_container row"  >
			<div class="ms_selectable col-sm-5 form-group">
				<label for="ddSourceVillageRLBS"><spring:message htmlEscape="true" code="Label.DISTRICTLIST" /></label>
				<form:select path="" id="dddistrictAtStateLvl" multiple="true" class="form-control">
					<c:forEach items="${distList}" var="distListvar">
						<c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
							<form:option value="${distListvar.districtCode}" disabled="true"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
						</c:if>
						<c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
							<form:option value="${distListvar.districtCode}"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
						</c:if>
					</c:forEach>
				</form:select>	
			</div>
			<div class="ms_buttons col-sm-2 dbtn_align">
				
				<button type="button" class="btn btn-primary btn-xs btn-block"  id="mvFrDistt"> &gt;&gt;</button>
				<button type="button" class="btn btn-primary btn-xs btn-block" id="mvBackDistt"> &lt;&lt;</button>
			</div>
			<div class="ms_selection col-sm-5">
				<div class="form-group">
					<label for="ddDestVillageRLBs"><spring:message htmlEscape="true" code="Label.DISTRICTLISTSELECTED" /></label> 
					<form:select path="districtIds" id="ddTargetDistrict" multiple="true" class="form-control" />
					<label id="err_district" class="errormsg"></label>
				</div>
			</div>
		</div>
		</div>	
</c:if>
<!-- District Level Tags Ends Here -->
<c:if test="${ fn:contains(pageAccessLevel, 'T') or fn:contains(pageAccessLevel, 'B')}">
<div class="col-sm-12" id="tblDistrictChkPart">
		<form:checkbox path="DistrictChkpart" id="DistrictChkPart" name="checkbox" value="DPart" htmlEscape="true" onclick="toggle3();" /> 
		<spring:message htmlEscape="true" code="Label.DISTRICTPARTCOVERAGE" />
		<div class="hr_dept"></div>
</div>	
</c:if>

<c:if test="${ fn:contains(pageAccessLevel, 'V')}">
<div class="col-sm-12" id="tblSubDistrictChkFull">
		<form:checkbox path="SubDistrictChkfull" id="SubDistrictChkFull" name="checkbox" value="SDFull" onclick="checkSubDistFullCovrage()" />
		<spring:message htmlEscape="true" code="Label.SUBDISTRICTFULLCOVERAGE" />
		<div class="hr_dept"></div>
</div>	
</c:if>

<c:if test="${isCenterUser == true}">
	<c:if test="${ fn:contains(pageAccessLevel, 'T')  or fn:contains(pageAccessLevel, 'B')	or fn:contains(pageAccessLevel, 'V')}">
		<div class="form-group dept_list_button" id="tblDistrictSelectBox1">
				  <label  class="col-sm-3 control-label" for="sel1">tblDistrictSelectBox1</label>
				  <div class="col-sm-6">
					 <form:select path="" class="form-control" id="stateNameDistforSD" style="width:200px" onchange="getNotInDistrictList(this.value);">
							<form:option value="">
								<spring:message htmlEscape="true" code="Label.SELECT" />
							</form:option>
					</form:select>
			      </div>
	   	</div>
	</c:if>
</c:if>

<c:if test="${ fn:contains(pageAccessLevel, 'T') or fn:contains(pageAccessLevel, 'B') or fn:contains(pageAccessLevel, 'V')}">
	<div class="form-group" id="tblDistrictSelectBox">
		  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.SELECTDISTRICT" /></label>
		  <div class="col-sm-6">
			  <select id="district" name="districtName1" class="form-control" style="width: 200px" onchange="getSubdistrictOrBlocksByDistrict(this.value);">
					<option value="">Select</option>
			  </select>
	      </div>
	</div>
</c:if>
<!-- Sub-district Level  Tags Started -->
 <c:if test="${fn:contains(pageAccessLevel, 'T') or fn:contains(pageAccessLevel, 'V')}">
 <div class="box-body dept_list_button" id="tblSubdistrictSpecific" >
		<div class="ms_container row"  >
			<div class="ms_selectable col-sm-5 form-group">
				<label for="ddSourceVillageRLBS"><spring:message htmlEscape="true" code="Label.SUBDISTRICTLIST" /></label>
				<form:select path="" id="ddSubdistrictAtSubDistrictLvl" multiple="true" class="form-control"></form:select>	
			</div>
			<div class="ms_buttons col-sm-2 dbtn_align">
				<button type="button" class="btn btn-primary btn-xs btn-block"  id="mvFrSubDist"> &gt;&gt;</button>
				<button type="button" class="btn btn-primary btn-xs btn-block" id="mvBackSubDist"> &lt;&lt;</button>
			</div>
			<div class="ms_selection col-sm-5">
				<div class="form-group">
					<label for="ddDestVillageRLBs"><spring:message htmlEscape="true" code="Label.SELECTEDSUBDISTRICT" /></label> 
					<form:select path="subdistrictIds" id="ddTargetDistrictSDLvl" multiple="true" class="form-control" />
					 <label id="err_subDistrict" class="errormsg"></label>
				</div>
			</div>
		</div>
	</div>	

</c:if>
<!-- Sub-district Level Tags Ends Here -->
<!-- Block Level  Tags Started -->
 <c:if test="${fn:contains(pageAccessLevel, 'B')}">
	 <div class="box-body dept_list_button" id="tblBlockSpecific" >
			<div class="ms_container row"  >
				<div class="ms_selectable col-sm-5 form-group">
					<label for="ddSourceVillageRLBS"><spring:message htmlEscape="true" code="Label.BLOCKLIST" /></label>
					<form:select path="" id="ddBlockAtDistrictLvl" multiple="true" class="form-control"></form:select>	
				</div>
				<div class="ms_buttons col-sm-2 dbtn_align">
					<button type="button" class="btn btn-primary btn-xs btn-block"  id="mvFrBlock"> &gt;&gt;</button>
					<button type="button" class="btn btn-primary btn-xs btn-block" id="mvBackBlock"> &lt;&lt;</button>
				</div>
				<div class="ms_selection col-sm-5">
					<div class="form-group">
						<label for="ddDestVillageRLBs"><spring:message htmlEscape="true" code="Label.BLOCKLISTSELECTED" /></label> 
						<form:select path="blockIds" id="ddTargetBlockSDLvl" multiple="true" class="form-control" />
						<label id="err_block" class="errormsg"></label>
					</div>
				</div>
			</div>
		</div>	
 </c:if> 
 <!-- Block Level Tags Ends Here -->
 <!-- Village Level Tags Started Here -->
<c:if test="${ fn:contains(pageAccessLevel, 'V')}">
	<div class="col-sm-12" id="tblSubDistrictChkPart">
			 <form:checkbox path="SubDistrictChkpart" id="SubDistrictChkPart" name="checkbox" value="SDPart" onclick="checkSubDistPartialCovrage()" />
			 <spring:message htmlEscape="true" code="Label.SUBDISTRICTPARTCOVERAGE" />
			 <div class="hr_dept"></div>
			 
	</div>	
	<div class="form-group dept_list_button" id="tblDistrictSelectBox" style="display:none; ">
		  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.SELECTSTATE" /></label>
		  <div class="col-sm-6">
			 <form:select path="" style="width:200px" class="form-control" id="stateNameSubDistForVillage" onchange="getNotInDistrictListVillLvl(this.value);">
					<form:option value="">
						<spring:message htmlEscape="true" code="Label.SELECT" />
					</form:option>
				</form:select>
	      </div>
	</div>
	<div id="tblDisttSubDisttVillLvlSelectBox">
		<div class="form-group" >
			  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.SELECTDISTRICT" /></label>
			  <div class="col-sm-6">
				<form:select path="" id="dddistrictAtVillLvl" class="form-control" style="width:200px" onchange="getSubdistrictAtVilllvl(this.value)"></form:select>
		      </div>
		</div>	
		<div class="form-group" >
			  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.SELECTSUBDISTRICT" /></label>
			  <div class="col-sm-6">
				<form:select path="" id="ddSubdistrictAtVillLvl" class="form-control" style="width:200px" onchange="getVillageListAtVillageLvl(this.value)"></form:select>
		      </div>
		</div>											
	</div>
	<div class="box-body dept_list_button" id="tblVillageSpecific" >
			<div class="ms_container row"  >
				<div class="ms_selectable col-sm-5 form-group">
					<label for="ddSourceVillageRLBS"><spring:message htmlEscape="true" code="Label.VILLAGELIST" /></label>
					<form:select path="" id="ddVillageAtVillLvl" multiple="true" class="form-control"></form:select>	
				</div>
				<div class="ms_buttons col-sm-2 dbtn_align">
					<button type="button" class="btn btn-primary btn-xs btn-block"  id="mvFrVillage"> &gt;&gt;</button>
					<button type="button" class="btn btn-primary btn-xs btn-block" id="mvBackVillage"> &lt;&lt;</button>
				</div>
				<div class="ms_selection col-sm-5">
					<div class="form-group">
						<label for="ddDestVillageRLBs"><spring:message htmlEscape="true" code="Label.VILLAGELISTSELECTED" /></label> 
						<form:select path="villageIds" id="ddTargetDistrictVillLvl" multiple="true" class="form-control" />
						<label id="err_village" class="errormsg"></label>
					</div>
				</div>
			</div>
		</div>										
</c:if>
<!-- Village Level Tags Ends Here -->
<!-- Administrative Unit Level Tags Started -->

<c:if test="${ fn:contains(pageAccessLevel, 'A')}">
<div class="box-body dept_list_button" id="tblAdminUnits" >
	<div class="ms_container row" >
		<div class="ms_selectable col-sm-5 form-group">
			<label for="ddSourceVillageRLBS">Administrative Entity List</label>
			<form:select path=""  id="ddAdminEnitiy" multiple="true"  class="form-control" htmlEscape="true">
				<form:options items="${adminEntities}" itemValue="adminUnitEntityCode" itemLabel="adminEntityNameEnglish" htmlEscape="true"/>
			</form:select>	
		</div>
		<div class="ms_buttons col-sm-2 dbtn_align">
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="mvFrAdminEntity"> &gt;&gt;</button>
			<button type="button" class="btn btn-primary btn-xs btn-block" id="mvBackAdminEntity"> &lt;&lt;</button>
		</div>
		<div class="ms_selection col-sm-5">
			<div class="form-group">
				<label for="ddDestVillageRLBs">Selected Administrative Enities</label> 
				<form:select path="adminUnitEntityIds" id="ddTargetAdminEnitiy" multiple="true"  class="form-control" htmlEscape="true"/>
				<label id="err_Administrativeblk" class="errormsg"></label>	
			</div>
		</div>
	</div>	
</div>

	<c:if test="${adminUnitCondition}">
	<div class="box-body dept_list_button" id="tblAdminUnitsConditional" >
		<div class="ms_container row"  >
			<div class="ms_selectable col-sm-5 form-group">
				<label for="ddSourceVillageRLBS">Administrative Entity List - <br/>Conditional</label>
				<form:select path="" id="ddAdminEnitiyConditional" multiple="true"  class="form-control" htmlEscape="true">
					<form:options items="${adminEntities}" itemValue="adminUnitEntityCode" itemLabel="adminEntityNameEnglish" htmlEscape="true"/>
				</form:select>		
			</div>
			<div class="ms_buttons col-sm-2 dbtn_align">
				<button type="button" class="btn btn-primary btn-xs btn-block"  id="mvFrAdminEntity"> &gt;&gt;</button>
				<button type="button" class="btn btn-primary btn-xs btn-block" id="mvBackAdminEntity"> &lt;&lt;</button>
			</div>
			<div class="ms_selection col-sm-5">
				<div class="form-group">
					<label for="ddDestVillageRLBs">Selected Administrative Enities - <br/>Conditional</label> 
					<form:select path="adminUnitEntityIds"  id="ddTargetAdminEnitiyConditional" multiple="true"  class="form-control" htmlEscape="true"/>
				</div>
			</div>
		</div>
		</div>	
	</c:if>
</c:if>
<!-- Administrative Unit Level Tags Ends Here -->

<!-- Entity Selection Details Tags Ends Here -->
</div>
</c:if>
<c:if test="${!empty existDeptList or !empty newparentList }">
	<script>
		document.getElementById('parent').value="<c:out value='${adminOrgDeptForm.parentDepartmentName}' />";
	</script>
</c:if>
</div>
<div class="box-footer">
     <div class="col-sm-offset-2 col-sm-10">
       <div class="pull-right">
       		<c:choose>
				
				<c:when test="${enableSaveBtn}">
					<button type="button" class="btn btn-success" id="btnSaveDept" onclick="javascript:validateFormdeails('SAVE');"><i class="fa fa-floppy-o"></i><spring:message htmlEscape="true"  code="Button.SAVE"/></button>
				</c:when>
				<c:otherwise>
					<button type="button" class="btn btn-success" id="btnCreateDeptNext"  onclick="javascript:processForm('Next');"><i class="fa fa-floppy-o"></i><spring:message htmlEscape="true"  code="Button.NEXT"/></button>
				</c:otherwise>
			</c:choose>
			<button type="button" class="btn btn-success" id="addAnotherDeptSameLevel" onclick="javascript:validateFormdeails('AddDeptatSameLevel');"><i class="fa fa-floppy-o"></i><spring:message htmlEscape="true"  code="Button.AddDeptatSameLevel"/></button>
       		<button type="button" class="btn btn-danger" id="close"  onclick="javascript:go('closeExtendDepartmentProcess.htm');"><i class="fa fa-floppy-o"></i>Close</button>
        </div>
     </div>   
   </div>

			</form:form>
		</div>
		</section>
	</div>
	</section>
	<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>