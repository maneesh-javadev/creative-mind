
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="LocalBodyJavascript.jsp"%>
<%-- <%@include file="../common/dwr.jsp"%> --%>

<title><spring:message htmlEscape="true"  code="Label.CREATENEWLOCALGOVTBODY"></spring:message></title>
</head>
<script type="text/javascript">

	$(document).ready(function(){
		$('#localBodyForm input[type=text]').attr("autocomplete",'off');
	});


</script>



<body>

	<section class="content">
		<div class="row">
			<section class="col-lg-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title"><spring:message htmlEscape="true" code="Label.CREATENEWLOCALGOVTBODY"></spring:message></h3>
					</div>
					<form:form action="buildLocalBody.htm" method="post" id="localBodyForm" commandName="localBodyForm" enctype="multipart/form-data" class="form-horizontal">
						<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="buildLocalBody.htm"/>" />
						<form:hidden path="processAction"/>
						<form:hidden path="localBodyCreationType"/>
						<form:hidden path="isResetedCoverage"/>
						<div class="box-body">
							<div class="box-header subheading">
			                  <h4><spring:message htmlEscape="true"  code="Label.GENERALDETAILNEWLOCALBODY"></spring:message></h4>
			                </div>
			                <div class="form-group">
		                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="label.LOCALBODYNEWNAMEENGLISH"></spring:message><span class="mandatory">*</span></label>
		                      	<div class="col-sm-6">
		                        	<form:input path="localBodyNameEnglish" id="localBodyNameEnglish" maxlength="200" htmlEscape="true" class="form-control"/>
									<span class="mandatory" id="errLBNameEnglish"></span>
									<form:errors htmlEscape="true" path="localBodyNameEnglish" cssClass="mandatory"/>
		                      	</div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="label.LOCALBODYNEWNAMELOCAL"></spring:message></label>
		                      	<div class="col-sm-6">
		                        	<form:input path="localBodyNameLocal" id="localBodyNameLocal" maxlength="100" htmlEscape="true" class="form-control"/>	
									<br/>
									<form:errors htmlEscape="true" path="localBodyNameLocal" cssClass="error"/>	
		                      	</div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message></label>
		                      	<div class="col-sm-6">
		                        	<form:input path="localBodyAliasEnglish" id="localBodyAliasEnglish" maxlength="50" htmlEscape="true" class="form-control"/>
									<br/>
									<form:errors htmlEscape="true" path="localBodyAliasEnglish" cssClass="error"/>	
		                      	</div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message></label>
		                      	<div class="col-sm-6">
		                        	<form:input path="localBodyAliasLocal" id="localBodyAliasLocal" maxlength="50" htmlEscape="true" class="form-control"/>
									<br/>	
									<form:errors htmlEscape="true" path="localBodyAliasLocal" cssClass="error"/>	
		                      	</div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.STATESPECIFICCODE"></spring:message></label>
		                      	<div class="col-sm-6">
		                        	<form:input path="stateSpecificCode" id="stateSpecificCode" maxlength="7" htmlEscape="true" class="form-control"/>
									<br/>
									<form:errors htmlEscape="true" path="stateSpecificCode" cssClass="error"/>	
		                      	</div>
		                    </div>
		                    <div class="box-header subheading">
			                  <h4><spring:message htmlEscape="true"  code="Label.SELECTEDTYPELOCALBODY"></spring:message></h4>
			                </div>
			                <div class="form-group">
		                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.Selecthierarchylevel"></spring:message><span class="mandatory">*</span></label>
		                      	<div class="col-sm-6">
		                        	<form:select path="lbTypeHierarchy" id="lbTypeHierarchy" class="form-control">
										<c:if test="${fn:length(lbTypeHierarchy) ne 1}">
   											<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT"/></form:option>
										</c:if>
										<c:forEach items="${lbTypeHierarchy}" var="objHierarchy" >
											<form:option value="${objHierarchy.localBodySetupCode},${objHierarchy.localBodySetupVersion}">
												<c:out value="${objHierarchy.localBodyTypeHierarchy}" escapeXml="true"></c:out>
											</form:option>
										</c:forEach>
									</form:select>
									<span class="errormessage" id="errorLbTypeHierarchy"></span>
									<form:errors htmlEscape="true" path="lbTypeHierarchy" cssClass="error"/>	
		                      	</div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message><span class="mandatory">*</span></label>
		                      	<div class="col-sm-6">
		                        	<form:select path="localBodyTypePanchayat" id="localBodyType" class="form-control">
										<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT" ></spring:message></form:option>
									</form:select>
									<span class="errormessage" id="errorLocalBodyType"></span>
									<form:errors htmlEscape="true" path="localBodyTypePanchayat" cssClass="error"/>	
		                      	</div>
		                    </div>
		                    <div id="divCreateDynamicHierarchy"></div>
		                    <div class="box-header subheading">
			                  <h4><spring:message htmlEscape="true" code="Label.COVEREDAREAOFNEWLCLBDY"></spring:message></h4>
			                </div>
			                
			                <div class="form-group">
			                	<div class="col-sm-6">
		                        	<form:checkbox path="checkboxCoverageLB" id="checkboxCoverageLB" param="LB_COVERAGE"/>
							    	<label><spring:message htmlEscape="true" code="Label.SELEXISTINGCOVEREDAREA"></spring:message></label>
							    	<br/>
							    	<form:errors htmlEscape="true" path="checkboxCoverageLB" cssClass="error"/>
							   </div>
		                    </div>
		                    
		                    <div class="ms_container row" style="margin-left: 10px;display: none;" id="firstLevelLBDiv" >
			                	<div class="ms_selectable col-sm-5 form-group">
			                    	<label id="labelAvailablePanchayat"></label>
			                    	<form:select path="" id="coverageLBAvailablePanchayatLevel" multiple="multiple" class="form-control"/>
		                      	</div>
		                      	<div class="ms_buttons col-sm-2">
		                      		<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="PanchayatLevel" paramLBLR="LB" param="Whole"><spring:message code='Button.WHOLE' htmlEscape='true'/> <i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
		                      		<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="PanchayatLevel" paramLBLR="LB" param="Back"><spring:message code='Button.BACK' htmlEscape='true'/> <i class="fa fa-angle-left" aria-hidden="true"></i></button>
									<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="PanchayatLevel" paramLBLR="LB" param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/> <i class="fa fa-angle-double-left" aria-hidden="true"></i></button>
									<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="PanchayatLevel" paramLBLR="LB" param="Part"><spring:message code='Button.PART' htmlEscape='true'/> <i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
		                      	</div>
		                      	<div class="ms_selection col-sm-5">
		                      		<div class="form-group">
		                      			<label id="labelContributingPanchayat"></label>
										<form:select path="contributingLBCodes" id="coverageLBContributingPanchayatLevel" multiple="multiple" class="form-control"/>
										<br/>
								    	<form:errors htmlEscape="true" path="contributingLBCodes" cssClass="error"/>
										<input type="button" class="btn btn-primary " id="fetchCoverageofLocalBody" style="width:100%;" level="PanchayatLevel" paramLBLR="LB" value="<spring:message htmlEscape="true" code="Button.GETCOVEREDAREAOFLOCALBODY"/>"/>
		                      		</div>
		                      	</div>
		                    </div>
		                    
		                    <div class="ms_container row" style="margin-left: 10px;display: none;" id="secondLevelLBDiv">
			                	<div class="ms_selectable col-sm-5 form-group">
			                    	<label><spring:message htmlEscape="true" code="Label.AVAILDISTRICTLIST"/></label>
									<form:select path="" id="coverageLBAvailableDisttLevel" multiple="multiple" class="form-control"/>
		                      	</div>
		                      	<div class="ms_buttons col-sm-2"><br/>
		                      		<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="DisttLevel" paramLBLR="LB" param="Whole"><spring:message code='Button.WHOLE' htmlEscape='true'/> <i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
		                      		<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="DisttLevel" paramLBLR="LB" param="Back"><spring:message code='Button.BACK' htmlEscape='true'/> <i class="fa fa-angle-left" aria-hidden="true"></i></button>
									<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="DisttLevel" paramLBLR="LB" param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/> <i class="fa fa-angle-double-left" aria-hidden="true"></i></button>
									<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="DisttLevel" paramLBLR="LB" param="Part"><spring:message code='Button.PART' htmlEscape='true'/> <i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
		                      	</div>
		                      	<div class="ms_selection col-sm-5">
		                      		<div class="form-group">
		                      			<label><spring:message htmlEscape="true" code="Label.CONTRIBUTDISTRICTLIST"/></label>
										<form:select path="contributingLBDistrictCodes" id="coverageLBContributingDisttLevel" multiple="multiple" class="form-control"/>
										<br/>
								    	<form:errors htmlEscape="true" path="contributingLBDistrictCodes" cssClass="error"/>
										<input type="button" class="btn btn-primary " id="btnFetchCoverageDistrict" style="width:100%;" param="LB_COVERAGE_DISTRICT" level="DisttLevel" paramLBLR="LB" value="<spring:message htmlEscape="true" code="Button.GETSUBDISTRICTL"/>"/>
		                      		</div>
		                      	</div>
		                    </div>
		                    
		                    <div class="ms_container row" style="margin-left: 10px;display: none;" id="thirdLevelLBDiv">
			                	<div class="ms_selectable col-sm-5 form-group">
			                    	<label><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"/></label>
									<form:select path="" id="coverageLBAvailableSubdisttLevel" multiple="multiple" class="form-control"/>
		                      	</div>
		                      	<div class="ms_buttons col-sm-2"><br/>
		                      		<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="SubdisttLevel" paramLBLR="LB" param="Whole"><spring:message code='Button.WHOLE' htmlEscape='true'/> <i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
		                      		<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="SubdisttLevel" paramLBLR="LB" param="Back"><spring:message code='Button.BACK' htmlEscape='true'/> <i class="fa fa-angle-left" aria-hidden="true"></i></button>
									<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="SubdisttLevel" paramLBLR="LB" param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/> <i class="fa fa-angle-double-left" aria-hidden="true"></i></button>
									<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="SubdisttLevel" paramLBLR="LB" param="Part"><spring:message code='Button.PART' htmlEscape='true'/> <i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
		                      	</div>
		                      	<div class="ms_selection col-sm-5">
		                      		<div class="form-group">
		                      			<label><spring:message htmlEscape="true" code="Label.CONTRIBUTSUBDISTRICTLIST"/></label>
										<form:select path="contributingLBSubDistrictCodes" id="coverageLBContributingSubdisttLevel" multiple="multiple" class="form-control"/>
										<br/>
										<form:errors htmlEscape="true" path="contributingLBSubDistrictCodes" cssClass="error"/>
										<input class="btn btn-primary " id="btnFetchCoverageSubDistrict" style="width:100%;" param="LB_COVERAGE_SUB_DISTRICT" level="SubdisttLevel" paramLBLR="LB" value="<spring:message htmlEscape="true" code="Button.GETVILLAGEL"/>"/>
		                      		</div>
		                      	</div>
		                    </div>
		                    
		                    <div class="ms_container row" style="margin-left: 10px;display: none;" id="fourthLevelLBDiv">
			                	<div class="ms_selectable col-sm-5 form-group">
			                    	<label><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"/></label>
									<form:select path="" id="coverageLBAvailableVillageLevel" multiple="multiple" class="form-control"/>
		                      	</div>
		                      	<div class="ms_buttons col-sm-2"><br/>
		                      		<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="VillageLevel" paramLBLR="LB" param="Whole"><spring:message code='Button.WHOLE' htmlEscape='true'/> <i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
		                      		<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="VillageLevel" paramLBLR="LB" param="Back"><spring:message code='Button.BACK' htmlEscape='true'/> <i class="fa fa-angle-left" aria-hidden="true"></i></button>
									<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="VillageLevel" paramLBLR="LB" param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/> <i class="fa fa-angle-double-left" aria-hidden="true"></i></button>
									<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="VillageLevel" paramLBLR="LB" param="Part"><spring:message code='Button.PART' htmlEscape='true'/> <i class="fa fa-angle-right" aria-hidden="true"></i></button>
		                      	</div>
		                      	<div class="ms_selection col-sm-5">
		                      		<div class="form-group">
		                      			<label><spring:message htmlEscape="true"  code="Label.CONTRIBUTVILLAGELIST"/></label>
										<form:select path="contributingLBVillageCodes" id="coverageLBContributingVillageLevel" multiple="multiple" class="form-control"/>
										<br/>
										<form:errors htmlEscape="true" path="contributingLBVillageCodes" cssClass="error"/>
		                      		</div>
		                      	</div>
		                    </div>
		                    
		                    <div class="form-group">
		                      	<div class="col-sm-6">
		                        	<form:checkbox path="checkboxCoverageUnmapped" id="checkboxCoverageUnmapped" param="UNMAPPED_COVERAGE"/>
									<label><spring:message htmlEscape="true" code="Label.SELCONTRIBUTEUNMAPPEDPARTIALLY"/></label>
									<br/>
									<form:errors htmlEscape="true" path="checkboxCoverageUnmapped" cssClass="error"/>
		                      	</div>
		                    </div>
		                    
		                    <div class="ms_container row" style="margin-left: 10px;display: none;" id="firstLevelUnmappedLRDiv">
			                	<div class="ms_selectable col-sm-5 form-group">
			                    	<label><spring:message htmlEscape="true"  code="Label.AVAILDISTRICTLIST"/></label>
									<form:select path="" id="coverageLRAvailableUnmappedDisttLevel" multiple="multiple" class="form-control"/>
		                      	</div>
		                      	<div class="ms_buttons col-sm-2"><br/>
		                      		<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="UnmappedDisttLevel" paramLBLR="LR" param="Whole"><spring:message code='Button.WHOLE' htmlEscape='true'/> <i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
		                      		<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="UnmappedDisttLevel" paramLBLR="LR" param="Back"><spring:message code='Button.BACK' htmlEscape='true'/> <i class="fa fa-angle-left" aria-hidden="true"></i></button>
									<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="UnmappedDisttLevel" paramLBLR="LR" param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/> <i class="fa fa-angle-double-left" aria-hidden="true"></i></button>
									<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="UnmappedDisttLevel" paramLBLR="LR" param="Part"><spring:message code='Button.PART' htmlEscape='true'/> <i class="fa fa-angle-right" aria-hidden="true"></i></button>
		                      	</div>
		                      	<div class="ms_selection col-sm-5">
		                      		<div class="form-group">
		                      			<label><spring:message htmlEscape="true" code="Label.CONTRIBUTDISTRICTLIST"/></label>
										<form:select path="contributingUnmappedDistrictCodes" id="coverageLRContributingUnmappedDisttLevel" multiple="multiple" class="form-control"/>
										<br/>
										<form:errors htmlEscape="true" path="contributingUnmappedDistrictCodes" cssClass="error"/>
										<input class="btn btn-primary" type="button" id="btnFetchCoverageUnmappedDistrict" style="width: 100%" param="LB_COVERAGE_UNMAPPED_DISTRICT" level="UnmappedDisttLevel" paramLBLR="LR" value="<spring:message htmlEscape="true" code="Button.GETSUBDISTRICTL"/>"/>
		                      		</div>
		                      	</div>
		                    </div>
		                    
		                    <div class="ms_container row" style="margin-left: 10px;display: none;" id="secondLevelUnmappedLRDiv">
			                	<div class="ms_selectable col-sm-5 form-group">
			                    	<label><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"/></label>
									<form:select path="" id="coverageLRAvailableUnmappedIntermediateLevel" multiple="multiple" class="form-control"/>
		                      	</div>
		                      	<div class="ms_buttons col-sm-2"><br/>
		                      		<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="UnmappedIntermediateLevel" paramLBLR="LR" param="Whole"><spring:message code='Button.WHOLE' htmlEscape='true'/> <i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
		                      		<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="UnmappedIntermediateLevel" paramLBLR="LR" param="Back"><spring:message code='Button.BACK' htmlEscape='true'/> <i class="fa fa-angle-left" aria-hidden="true"></i></button>
									<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="UnmappedIntermediateLevel" paramLBLR="LR" param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/> <i class="fa fa-angle-double-left" aria-hidden="true"></i></button>
									<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="UnmappedIntermediateLevel" paramLBLR="LR" param="Part"><spring:message code='Button.PART' htmlEscape='true'/> <i class="fa fa-angle-right" aria-hidden="true"></i></button>
		                      	</div>
		                      	<div class="ms_selection col-sm-5">
		                      		<div class="form-group">
		                      			<label><spring:message htmlEscape="true" code="Label.CONTRIBUTSUBDISTRICTLIST"/></label>
										<form:select path="contributingUnmappedSubDistrictCodes" id="coverageLRContributingUnmappedIntermediateLevel" multiple="multiple" class="form-control"/>											
										<br/>
										<form:errors htmlEscape="true" path="contributingUnmappedSubDistrictCodes" cssClass="error"/>
										<input class="btn btn-primary" type="button" id="btnFetchCoverageUnmappedSubDistrict" style="width: 100%" param="LB_COVERAGE_UNMAPPED_SUB_DISTRICT" level="UnmappedIntermediateLevel" paramLBLR="LR" value="<spring:message htmlEscape="true" code="Button.GETVILLAGEL"/>"/>
		                      		</div>
		                      	</div>
		                    </div>
		                    
		                    <div class="ms_container row" style="margin-left: 10px;display: none;" id="thirdLevelUnmappedLRDiv">
			                	<div class="ms_selectable col-sm-5 form-group">
			                    	<label><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"/></label>
									<form:select path="" id="coverageLRAvailableUnmappedVillageLevel" multiple="multiple" class="form-control"/>
		                      	</div>
		                      	<div class="ms_buttons col-sm-2"><br/>
		                      		<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="UnmappedVillageLevel" paramLBLR="LR" param="Whole"><spring:message code='Button.WHOLE' htmlEscape='true'/> <i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
		                      		<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="UnmappedVillageLevel" paramLBLR="LR" param="Back"><spring:message code='Button.BACK' htmlEscape='true'/> <i class="fa fa-angle-left" aria-hidden="true"></i></button>
									<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="UnmappedVillageLevel" paramLBLR="LR" param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/> <i class="fa fa-angle-double-left" aria-hidden="true"></i></button>
									<button type="button" class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" level="UnmappedVillageLevel" paramLBLR="LR" param="Part"><spring:message code='Button.PART' htmlEscape='true'/> <i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
		                      	</div>
		                      	<div class="ms_selection col-sm-5">
		                      		<div class="form-group">
		                      			<label><spring:message htmlEscape="true" code="Label.CONTRIBUTVILLAGELIST"/></label>
										<form:select path="contributingUnmappedVillageCodes" id="coverageLRContributingUnmappedVillageLevel" multiple="multiple" class="form-control"/>
										<br/>
										<form:errors htmlEscape="true" path="contributingUnmappedVillageCodes" cssClass="error"/>
		                      		</div>
		                      	</div>
		                    </div>
		                    
		                   <div class="form_block" id="habitationDiv" style="display: none;">
		                    <div class="box-header subheading">
			                  <h4><spring:message htmlEscape="true"  code="Label.habiations"></spring:message></h4>
			                </div>
			                
			                <div class="form-group">
		                      	<div class="col-sm-6">
		                        	<input type="button" class="btn btn-primary" id="actionBuildHabitation" value="<spring:message code='Label.select.habiations' htmlEscape='true'/>"/>
		                      	</div>
		                    </div>
		                    
		                    <div class="ms_container row" style="margin-left: 10px;display: none;" id="habitationListDiv">
			                	<div class="ms_selectable col-sm-5 form-group">
			                    	<label><spring:message htmlEscape="true"  code="Label.AVAILHABITATION"/></label>
									<form:select path="avilableHabitation" id="avilableHabitation" multiple="multiple" class="form-control"/>
		                      	</div>
		                      	<div class="ms_buttons col-sm-2"><br/>
		                      		<button type="button" class="btn btn-primary btn-xs btn-block" onclick="moveElement('WHOLE');"><i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
		                      		<button type="button" class="btn btn-primary btn-xs btn-block" onclick="moveElement('BACK');"><i class="fa fa-angle-left" aria-hidden="true"></i></button>
									<button type="button" class="btn btn-primary btn-xs btn-block" onclick="moveElement('BACKALL');"><i class="fa fa-angle-double-left" aria-hidden="true"></i></button>
									<button type="button" class="btn btn-primary btn-xs btn-block" onclick="moveElement('FORWARD');"><i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
		                      	</div>
		                      	<div class="ms_selection col-sm-5">
		                      		<div class="form-group">
		                      			<label><spring:message htmlEscape="true" code="Label.CONTRIBUTHABITATION"/></label>
										<form:select path="contributingHabiationCodes" id="contributingHabiationCodes" multiple="multiple" class="form-control" />
										<br/>
										<form:errors htmlEscape="true" path="contributingHabiationCodes" cssClass="error"/>
		                      		</div>
		                      	</div>
		                    </div>
		                    </div>
		                    
		                    <div class="box-header subheading">
			                  <h4><spring:message htmlEscape="true" code="Label.HeaquarterFrame"></spring:message><span class="mandatory">*</span></h4>
			                </div>
			                <div class="form-group">
		                      	<div class="col-sm-12">
		                        	<input type="button" class="btn btn-primary" id="actionBuildHeadQuarter" value="<spring:message code='Label.selectHQ' htmlEscape='true'/>"/>
		                        	<table id="tblHeadquarter" class="data_grid" width="50%">
										<thead>
											<tr>
												<th><spring:message code='Label.selectHQ' htmlEscape='true'/></th>
												<th id="lblHQAtLevel"></th>
											</tr>
										</thead>
										<tbody id="tbodyHQuarter"></tbody>
									</table>
									<br/>
									<input type="hidden" id="showHQError" name="showHQError"/><!-- Used Element, Do not remove. -->
									<form:errors htmlEscape="true" path="headQuarterCode" cssClass="error"/>
		                      	</div>
		                    </div>
		                    
		                    <div style="display: none;">
		                    <div class="box-header subheading">
			                  <h4><spring:message code="Label.GISNODES" htmlEscape="true"></spring:message></h4>
			                </div>
			                
			                <div class="form-group">
			                	<div class="long_latitude" >
			                      	<div class="col-sm-4">
			                      		<label><spring:message code='Label.longituderange' htmlEscape='true'/></label>
			                      		<form:input path="longitude" id="longitude" maxlength="6" class="form-control"/>
			                      	</div>
			                      	<div class="col-sm-4">
			                      		<label><spring:message code='Label.latituderange' htmlEscape='true'/></label>
			                      		<form:input path="latitude" id="latitude" maxlength="6" class="form-control"/>
			                      	</div>
			                      	<div class="col-sm-4">
			                      	<br/>
			                      		<button class="btn btn-success " id="btnAddLatLong"><i class="fa fa-floppy-o"></i> Add More</button>
			                      	</div>
			                      	
			                      	<div id="divLatitudeLongitude">
										<!-- Adding Dynamic Rows for Latitude and Longitude -->
									</div>
									<br/>
									<form:errors htmlEscape="true" path="longitude" cssClass="error"/>
									<form:errors htmlEscape="true" path="latitude" cssClass="error"/>	 
		                      	</div>
		                    </div>
		                    
		                    <div class="form-group">
		                    	<div id="divMapUpload" style="display: none;">
			                    	<c:if test="${checkedServerValidation}">
										<a href="#"><c:out value="${localBodyForm.mapUploadPath}"></c:out></a>
										<form:hidden path="mapUploadPath"/>
									</c:if>
									<label class="col-sm-3 control-label">	
										<spring:message code="Label.UPLOADMAP" htmlEscape="true"/>
										<spring:message code='Label.allowedfileexe' htmlEscape='true'/>
									</label>
			                      	<div class="col-sm-6">
			                        	<form:input path="mapUpload" id="mapUpload" type="file" class="form-control"/>
										<form:errors htmlEscape="true" path="mapUpload" cssClass="error"/>
			                      	</div>
		                      	</div>
		                    </div>
		                    </div>
		                    
		                    <%@include file="ExistingGovernmentOrderCpy.jsp"%>
		                    <div class="box-footer">
	                    	<div class="col-sm-offset-2 col-sm-10">
	                    		<div class="pull-right">
	                           		<div style="display: none" id="drafthide">
	                           		<input class="btn btn-primary" id="btnFormActionSaveDraft" type="submit" param="DRAFT" value="<spring:message code='Button.DRAFT' htmlEscape='true'/>"/>
	                           		</div>
									<input type="submit" class="btn btn-success" id="btnFormActionSave" param="PUBLISH" value="<spring:message htmlEscape="true" code="Button.SP"/>"/>
									<input class="btn btn-danger" type="button" id="btnActionClose" param="home" onclick="callActionUrl('home.htm')" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>"/>
	                        	</div>
	                    	 </div>   
                  		</div>
						</div>
					</form:form>
				</div>
			</section>
		</div>
	</section>
</body>
</html>

