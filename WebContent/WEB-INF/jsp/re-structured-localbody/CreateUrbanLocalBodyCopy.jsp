
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="UrbanLBJavascript.jsp"%>
<%@include file="../common/dwr.jsp"%>
<script type="text/javascript" src="datepicker/jquery-1.6.2.js"
	charset="utf-8"></script>
<script type="text/javascript" src="datepicker/jquery.ui.core.js"></script>
<script type="text/javascript" src="datepicker/jquery.ui.widget.js"></script>
<script type="text/javascript" src="datepicker/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="datepicker/calender.js"></script>
<!-- <script type="text/javascript" src="/js/common.js" charset="utf-8"></script> -->
<link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<!-- <script src="js/trim-jquery.js"></script> -->
<link rel="stylesheet" href="datepicker/demos.css" />
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
       		<section class="col-lg-12 ">
           		<div class="box">
   					<div class="box-header with-border">
                		<h3 class="box-title">Create Urban Local Government Body</h3>
	                </div>
	                <div class="box-body">
			            <form:form action="buildLocalBody.htm" class="form-horizontal" method="post" id="localBodyForm" commandName="localBodyForm" enctype="multipart/form-data">
							<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="buildLocalBody.htm"/>" />
							<form:hidden path="processAction"/>
							<form:hidden path="localBodyCreationType"/>
							<form:hidden path="isResetedCoverage"/>
							<!-- Set Page Level Heading and element Id(s) Started-->
							<c:set var="lblAvailableHeading1" value="Label.AVAILSUBDISTRICTLIST"></c:set>
							<c:set var="lblContributingHeading1" value="Label.CONTRIBUTSUBDISTRICTLIST"></c:set>
							<c:set var="nameElementForContributingLB1" value="contributingLBSubDistrictCodes"></c:set>
							<c:set var="nameElementForContributingUnmapped1" value="contributingUnmappedSubDistrictCodes"></c:set>
							<%-- <c:if test="${isDistrictLevel}"> --%>
								<c:set var="lblAvailableHeading" value="Label.AVAILDISTRICTLIST"></c:set>
								<c:set var="lblContributingHeading" value="Label.CONTRIBUTDISTRICTLIST"></c:set>
								<c:set var="nameElementForContributingLB" value="contributingLBDistrictCodes"></c:set>
								<c:set var="nameElementForContributingUnmapped" value="contributingUnmappedDistrictCodes"></c:set>
							<%-- </c:if> --%> 
						   <div class="box-header subheading">
						   	<h4><spring:message htmlEscape="true" code="Label.GENERALDETAILNEWLOCALBODY"></spring:message></h4>
                    	   </div> 
						   <div class="form-group">
		                       	<label for="blockNameEnglish" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="label.LOCALBODYNEWNAMEENGLISH"></spring:message><span class="mandatory">*</span></label>
	                 			<div class="col-sm-6">
		                   			<form:input path="localBodyNameEnglish" class="form-control" id="localBodyNameEnglish" maxlength="200" htmlEscape="true"/>
									<span class="errormessage" id="errLBNameEnglish"></span>
									<br/>
									<form:errors htmlEscape="true" path="localBodyNameEnglish" cssClass="error"/>
	                   			</div>
	                 		</div>
	                 		
	                 		<div class="form-group">
		                       	<label for="blockNameEnglish" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="label.LOCALBODYNEWNAMELOCAL"></spring:message></label>
	                 			<div class="col-sm-6">
		                   			<form:input path="localBodyNameLocal" class="form-control" id="localBodyNameLocal" maxlength="100" htmlEscape="true"/>	
									<br/>
									<form:errors htmlEscape="true" path="localBodyNameLocal" cssClass="error"/>	
	                   			</div>
	                 		</div>
	                 		
	                 		
	                 		<div class="form-group">
		                       	<label for="blockNameEnglish" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message></label>
	                 			<div class="col-sm-6">
		                   			<form:input path="localBodyAliasEnglish" class="form-control" id="localBodyAliasEnglish" maxlength="50" htmlEscape="true"/>	
									<br/>
									<form:errors htmlEscape="true" path="localBodyAliasEnglish" cssClass="error"/>
	                   			</div>
	                 		</div>
	                 		
	                 		<div class="form-group">
		                       	<label for="blockNameEnglish" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message></label>
	                 			<div class="col-sm-6">
		                   			<form:input path="localBodyAliasLocal" class="form-control" id="localBodyAliasLocal" maxlength="50" htmlEscape="true"/>
									<br/>
									<form:errors htmlEscape="true" path="localBodyAliasLocal" cssClass="error"/>	
	                   			</div>
	                 		</div>
	                 		
	                 		<div class="form-group">
		                       	<label for="blockNameEnglish" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.STATESPECIFICCODE"></spring:message></label>
	                 			<div class="col-sm-6">
		                   			<form:input path="stateSpecificCode" class="form-control" id="stateSpecificCode" maxlength="7" htmlEscape="true"/>
									<br/>
									<form:errors htmlEscape="true" path="stateSpecificCode" cssClass="error"/>	
	                   			</div>
	                 		</div>
	                 		
	                 		<div class="box-header subheading">
						   	<h4><spring:message htmlEscape="true" code="Label.SELECTEDTYPELOCALBODY"></spring:message></h4>
                    	   </div> 
	                 		
	                 		
	                 		<div class="form-group">
		                       	<label for="blockNameEnglish" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message><span class="mandatory">*</span></label>
	                 			<div class="col-sm-6">
		                   			<form:select path="localBodyTypeCode" class="form-control" id="localBodyType">
										<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT" ></spring:message></form:option>
										<c:forEach items="${localBodyTypeList}" var="objLBTypes">
											<form:option value="${objLBTypes.localBodyTypeCode}" paramTierSetup="${objLBTypes.tierSetupCode}">
												<c:out value="${objLBTypes.name}" escapeXml="true"></c:out>
											</form:option>
										</c:forEach>
									</form:select>
									<span class="errormessage" id="errorLocalBodyType"></span>
									<form:errors htmlEscape="true" path="localBodyTypeCode" cssClass="error"/>	
	                   			</div>
	                 		</div>
	                 		
	                 		
	                 		<div class="box-header subheading">
						   		<h4><spring:message htmlEscape="true" code="Label.COVEREDAREAOFNEWLCLBDY"></spring:message></h4>
                    	   </div> 
	                 		
	                 	<div class="form-group">
					  		<div class="col-sm-6">
							  <label>
								  <form:checkbox path="checkboxCoverageLB" id="checkboxCoverageLB" param="LB_COVERAGE"/>
								  <spring:message htmlEscape="true" code="Label.SELEXISTINGCOVEREDAREA"></spring:message>
								  <br/>
						    	  <form:errors htmlEscape="true" path="checkboxCoverageLB" cssClass="error"/>
							  </label>
					  		</div>
						</div>	
					
					
					<div id="divCoverageLBLevel" style="margin-left: 25px;display: none;">
					
					
						<div class="ms_container row">
							<div class="ms_selectable col-sm-5 form-group">
								<label>
									<spring:message	code="Label.AVAILABLEURBANLB" htmlEscape="true"></spring:message>&nbsp;
									<spring:message code="Label.LIST" htmlEscape="true"></spring:message>
								</label><br/>
								<form:select path="" id="coverageLBAvailablePanchayatLevel" class="form-control" multiple="multiple"/>
							</div>
							<div class="ms_buttons col-sm-2"><br />
								<button type="button" id="btnEventCoverage" level="PanchayatLevel" paramLBLR="LB"  param="Whole" class="btn btn-primary btn-xs btn-block"><spring:message code="Button.WHOLE"/> 
								</button>
								<button type="button" id="btnEventCoverage" level="PanchayatLevel" paramLBLR="LB" param="Back" class="btn btn-primary btn-xs btn-block"><spring:message code='Button.BACK' htmlEscape='true'/>
									
								</button>
								<button type="button" id="btnEventCoverage" level="PanchayatLevel" paramLBLR="LB" param="Reset" class="btn btn-primary btn-xs btn-block"> <spring:message code='Button.RESET' htmlEscape='true'/>
								</button>
								<button type="button" id="btnEventCoverage" level="PanchayatLevel" paramLBLR="LB" param="Part" class="btn btn-primary btn-xs btn-block"> <spring:message code='Button.PART' htmlEscape='true'/>
								</button>
								
							</div>
							<div class="ms_selection col-sm-5">
						  		<label>
						  			<spring:message code="Label.CONTRIBUTEURBANLB" htmlEscape="true"></spring:message>&nbsp;
									<spring:message code="Label.LIST" htmlEscape="true"></spring:message>
						  		</label><br/>
								<form:select path="contributingLBCodes" class="form-control" id="coverageLBContributingPanchayatLevel" multiple="multiple"/>
								<br/>
				    			<form:errors htmlEscape="true" path="contributingLBCodes" cssClass="error"/>
								<button type="button" level="PanchayatLevel" paramLBLR="LB" id="fetchCoverageofLocalBody"  class="btn btn-primary btn-xs btn-block"> <spring:message code='Button.GETCOVEREDAREAOFLOCALBODY' htmlEscape='true'/>
								</button>
                            </div>
				         </div>
				         
				         
				         
				         <div class="ms_container row">
							<div class="ms_selectable col-sm-5 form-group">
								<label>
									<spring:message	code="${lblAvailableHeading}" htmlEscape="true"></spring:message>
								</label>
								<form:select path="" class="form-control" id="coverageLBAvailableDisttLevel" multiple="multiple"/>
							</div>
							<div class="ms_buttons col-sm-2"><br />
								<button type="button" id="btnEventCoverage" level="DisttLevel" paramLBLR="LB"  param="Whole" class="btn btn-primary btn-xs btn-block"><spring:message code="Button.WHOLE"/>
								</button>
								<button type="button" id="btnEventCoverage" level="DisttLevel" paramLBLR="LB" param="Back" class="btn btn-primary btn-xs btn-block"><spring:message code='Button.BACK' htmlEscape='true'/>
								</button>
								<button type="button" id="btnEventCoverage" level="DisttLevel" paramLBLR="LB" param="Reset" class="btn btn-primary btn-xs btn-block"> <spring:message code='Button.RESET' htmlEscape='true'/>
								</button>
								<button type="button" id="btnEventCoverage" level="DisttLevel" paramLBLR="LB" param="Part" class="btn btn-primary btn-xs btn-block"> <spring:message code='Button.PART' htmlEscape='true'/>
								</button>
								
							</div>
							<div class="ms_selection col-sm-5">
						  		<label>
						  			<spring:message code="${lblContributingHeading}" htmlEscape="true"></spring:message>
						  		</label>
						  		
						  		<form:select path="${nameElementForContributingLB}" id="coverageLBContributingDisttLevel"  class="form-control" multiple="multiple"/>
								<br/>
								<form:errors htmlEscape="true" path="${nameElementForContributingLB}" cssClass="error"/>	
								<%-- <c:if test="${!isDistrictLevel}"> --%>
									<button type="button" level="SubDisttLevel" paramLBLR="LB" param=LB_COVERAGE_SUB_DISTRICT id="btnFetchCoverageSubDistrict"  class="btn btn-primary btn-xs btn-block"> <spring:message code='Button.GETSUBDISTRICTL' htmlEscape='true'/>
									</button>
								 <%-- </c:if> --%>
                            </div>
				         </div>
				         
				         <div class="ms_container row">
							<div class="ms_selectable col-sm-5 form-group">
								<label>
									<spring:message	code="${lblAvailableHeading1}" htmlEscape="true"></spring:message>
								</label>
								<form:select path="" class="form-control" id="coverageLBAvailableSubDisttLevel" multiple="multiple"/>
							</div>
							<div class="ms_buttons col-sm-2"><br />
								<button type="button" id="btnEventCoverage" level="SubDisttLevel" paramLBLR="LB"  param="Whole" class="btn btn-primary btn-xs btn-block"><spring:message code="Button.WHOLE"/>
								</button>
								<button type="button" id="btnEventCoverage" level="SubDisttLevel" paramLBLR="LB" param="Back" class="btn btn-primary btn-xs btn-block"><spring:message code='Button.BACK' htmlEscape='true'/>
								</button>
								<button type="button" id="btnEventCoverage" level="SubDisttLevel" paramLBLR="LB" param="Reset" class="btn btn-primary btn-xs btn-block"> <spring:message code='Button.RESET' htmlEscape='true'/>
								</button>
								<button type="button" id="btnEventCoverage" level="SubDisttLevel" paramLBLR="LB" param="Part" class="btn btn-primary btn-xs btn-block"> <spring:message code='Button.PART' htmlEscape='true'/>
								</button>
								
							</div>
							<div class="ms_selection col-sm-5">
						  		<label>
						  			<spring:message code="${lblContributingHeading1}" htmlEscape="true"></spring:message>
						  		</label>
						  		
						  		<form:select path="${nameElementForContributingLB1}" id="coverageLBContributingSubDisttLevel"  class="form-control" multiple="multiple"/>
								<br/>
								<form:errors htmlEscape="true" path="${nameElementForContributingLB1}" cssClass="error"/>	
								<%-- <c:if test="${!isDistrictLevel}"> --%>
									<button type="button" level="VillageLevel" paramLBLR="LB" param="LB_COVERAGE_VILLAGE_DISTRICT" id="btnFetchCoverageSubDistrict"  class="btn btn-primary btn-xs btn-block"> <spring:message code='Button.GETVILLAGEL' htmlEscape='true'/>
									</button>
								 <%-- </c:if> --%>
                            </div>
				         </div>
					
						<%-- <c:if test="${!isDistrictLevel}"> --%>
						
						<div class="ms_container row">
							<div class="ms_selectable col-sm-5 form-group">
								<label>
									<spring:message	code="Label.AVAILVILLAGELIST" htmlEscape="true"></spring:message>
								</label>
								<form:select path="" class="form-control" id="coverageLBAvailableVillageLevel" multiple="multiple"/>
							</div>
							<div class="ms_buttons col-sm-2"><br />
								<button type="button" id="btnEventCoverage" level="VillageLevel" paramLBLR="LB"  param="Whole" class="btn btn-primary btn-xs btn-block"><spring:message code="Button.WHOLE"/> 
								</button>
								<button type="button" id="btnEventCoverage" level="VillageLevel" paramLBLR="LB" param="Back" class="btn btn-primary btn-xs btn-block"><spring:message code='Button.BACK' htmlEscape='true'/>
								</button>
								<button type="button" id="btnEventCoverage" level="VillageLevel" paramLBLR="LB" param="Reset" class="btn btn-primary btn-xs btn-block"> <spring:message code='Button.RESET' htmlEscape='true'/>
								</button>
								<button type="button" id="btnEventCoverage" level="VillageLevel" paramLBLR="LB" param="Part" class="btn btn-primary btn-xs btn-block"> <spring:message code='Button.PART' htmlEscape='true'/></button>
								
							</div>
							<div class="ms_selection col-sm-5">
						  		<label>
						  			<spring:message code="Label.CONTRIBUTVILLAGELIST" htmlEscape="true"></spring:message>
						  		</label>
						  		
						  		<form:select class="form-control" path="contributingLBVillageCodes" id="coverageLBContributingVillageLevel" multiple="multiple"/>
								<br/>
								<form:errors htmlEscape="true" path="contributingLBVillageCodes" cssClass="error"/>
                            </div>
				         </div>
<%-- 						</c:if> --%>
					
					</div>
	                 		
               		<div class="form-group">
			  			<div class="col-sm-6">
						  		<label>
							    	<form:checkbox path="checkboxCoverageUnmapped" id="checkboxCoverageUnmapped" param="UNMAPPED_COVERAGE"/>
								  	<spring:message htmlEscape="true" code="Label.SELCONTRIBUTEUNMAPPEDPARTIALLY"></spring:message>
								  	<br/>
						    	  	<form:errors htmlEscape="true" path="checkboxCoverageUnmapped" cssClass="error"/>
							   </label>
					  	</div>
					</div>	
	                 	
	                 <div id="divCoverageLRLevel" style="display: none; margin-left: 25px">
	                 	<div class="ms_container row">
							<div class="ms_selectable col-sm-5 form-group">
								<label>
									<spring:message	code="${lblAvailableHeading}" htmlEscape="true"></spring:message>
								</label>
								<form:select path="" class="form-control" id="coverageLRAvailableUnmappedDisttLevel" multiple="multiple"/>
							</div>
							<div class="ms_buttons col-sm-2"><br />
								<button type="button" id="btnEventCoverage" level="UnmappedDisttLevel" paramLBLR="LR"  param="Whole" class="btn btn-primary btn-xs btn-block"><spring:message code="Button.WHOLE"/></button>
								<button type="button" id="btnEventCoverage" level="UnmappedDisttLevel" paramLBLR="LR" param="Back" class="btn btn-primary btn-xs btn-block"><spring:message code='Button.BACK' htmlEscape='true'/></button>
								<button type="button" id="btnEventCoverage" level="UnmappedDisttLevel" paramLBLR="LR" param="Reset" class="btn btn-primary btn-xs btn-block"> <spring:message code='Button.RESET' htmlEscape='true'/></button>
								<button type="button" id="btnEventCoverage" level="UnmappedDisttLevel" paramLBLR="LR" param="Part" class="btn btn-primary btn-xs btn-block"> <spring:message code='Button.PART' htmlEscape='true'/></button>
								
							</div>
							<div class="ms_selection col-sm-5">
						  		<label>
						  			<spring:message code="${lblContributingHeading}" htmlEscape="true"></spring:message>
						  		</label>
						  		
						  		<form:select class="form-control" path="${nameElementForContributingUnmapped}" id="coverageLRContributingUnmappedDisttLevel" multiple="multiple"/>
								<br/>
								<form:errors htmlEscape="true" path="${nameElementForContributingUnmapped}" cssClass="error"/>
<%-- 								<c:if test="${!isDistrictLevel}"> --%>
									<button type="button" level="UnmappedDisttLevel" paramLBLR="LR" param="LR_COVERAGE_UNMAPPED_SUB_DISTRICT" id="btnFetchCoverageUnmappedSubDistrict"  class="btn btn-primary btn-xs btn-block"> <spring:message code='Button.GETSUBDISTRICTL' htmlEscape='true'/>
									</button>
<%-- 								 </c:if> --%>
                            </div>
                            </div>
                            
                            
                            <div class="ms_container row">
							<div class="ms_selectable col-sm-5 form-group">
								<label>
									<spring:message	code="${lblAvailableHeading1}" htmlEscape="true"></spring:message>
								</label>
								<form:select path="" class="form-control" id="coverageLRAvailableUnmappedSubDisttLevel" multiple="multiple"/>
							</div>
							<div class="ms_buttons col-sm-2"><br />
								<button type="button" id="btnEventCoverage" level="UnmappedSubDisttLevel" paramLBLR="LR"  param="Whole" class="btn btn-primary btn-xs btn-block"><spring:message code="Button.WHOLE"/></button>
								<button type="button" id="btnEventCoverage" level="UnmappedSubDisttLevel" paramLBLR="LR" param="Back" class="btn btn-primary btn-xs btn-block"><spring:message code='Button.BACK' htmlEscape='true'/></button>
								<button type="button" id="btnEventCoverage" level="UnmappedSubDisttLevel" paramLBLR="LR" param="Reset" class="btn btn-primary btn-xs btn-block"> <spring:message code='Button.RESET' htmlEscape='true'/></button>
								<button type="button" id="btnEventCoverage" level="UnmappedSubDisttLevel" paramLBLR="LR" param="Part" class="btn btn-primary btn-xs btn-block"> <spring:message code='Button.PART' htmlEscape='true'/></button>
								
							</div>
							<div class="ms_selection col-sm-5">
						  		<label>
						  			<spring:message code="${lblContributingHeading1}" htmlEscape="true"></spring:message>
						  		</label>
						  		
						  		<form:select class="form-control" path="${nameElementForContributingUnmapped1}" id="coverageLRContributingUnmappedSubDisttLevel" multiple="multiple"/>
								<br/>
								<form:errors htmlEscape="true" path="${nameElementForContributingUnmapped1}" cssClass="error"/>
<%-- 								<c:if test="${!isDistrictLevel}">
 --%>									<button type="button" level="UnmappedDisttLevel" paramLBLR="LR" param="LR_COVERAGE_UNMAPPED_VILLAGE" id="btnFetchCoverageUnmappedSubDistrict1"  class="btn btn-primary btn-xs btn-block"> <spring:message code='Button.GETVILLAGEL' htmlEscape='true'/>
									</button>
<%-- 								 </c:if> --%>
                            </div>
                            </div>
                            
<%--                            <c:if test="${!isDistrictLevel}"> --%>
						
						<div class="ms_container row">
							<div class="ms_selectable col-sm-5 form-group">
								<label>
									<spring:message	code="Label.AVAILVILLAGELIST" htmlEscape="true"></spring:message>
								</label>
								<form:select path="" class="form-control" id="coverageLRAvailableUnmappedVillageLevel" multiple="multiple"/>
							</div>
							<div class="ms_buttons col-sm-2"><br />
								<button type="button" id="btnEventCoverage" level="UnmappedVillageLevel" paramLBLR="LR"  param="Whole" class="btn btn-primary btn-xs btn-block"><spring:message code="Button.WHOLE"/> 
								</button>
								<button type="button" id="btnEventCoverage" level="UnmappedVillageLevel" paramLBLR="LR" param="Back" class="btn btn-primary btn-xs btn-block"><spring:message code='Button.BACK' htmlEscape='true'/>
								</button>
								<button type="button" id="btnEventCoverage" level="UnmappedVillageLevel" paramLBLR="LR" param="Reset" class="btn btn-primary btn-xs btn-block"> <spring:message code='Button.RESET' htmlEscape='true'/>
								</button>
								<button type="button" id="btnEventCoverage" level="UnmappedVillageLevel" paramLBLR="LR" param="Part" class="btn btn-primary btn-xs btn-block"> <spring:message code='Button.PART' htmlEscape='true'/>
								</button>
								
							</div>
							<div class="ms_selection col-sm-5">
						  		<label>
						  			<spring:message code="Label.CONTRIBUTVILLAGELIST" htmlEscape="true"></spring:message>
						  		</label>
						  		
						  		<form:select class="form-control" path="contributingUnmappedVillageCodes" id="coverageLRContributingUnmappedVillageLevel" multiple="multiple"/>
								<br/>
								<form:errors htmlEscape="true" path="contributingUnmappedVillageCodes" cssClass="error"/>
                            </div>
				         </div>
<%-- 						</c:if> --%>
	                 </div>	
	                 
	                 <div style="display: none;">
                 	<div class="box-header subheading">
			   			<h4><spring:message htmlEscape="true" code="Label.GISNODES"></spring:message></h4>
                 	</div> 
	                 <div class="form-group" style="margin-left: 25px;">
	                 	<div class="col-sm-4">
						    <label id="lbllatitude"><spring:message code="Label.LATITUDE" htmlEscape="true"></spring:message> </label>
						    <form:input path="latitude" id="latitude" maxlength="7" class="form-control"/>
						</div>

						<div class="col-sm-4">
							    <label id="lbllongitude"><spring:message code="Label.LONGITUDE" htmlEscape="true"></spring:message> </label>
							    <form:input path="longitude" id="longitude" maxlength="7" class="form-control"/>
						 </div>
						<div class="col-sm-4">
						<br/>
						    <button type="button" id="btnAddLatLong" class="btn btn-success btn-sm addmore "><i class="fa fa-floppy-o"></i> <spring:message code="Button.ADDNODES" htmlEscape="true"></spring:message></button>
						 
						</div>
	                 	
						<br/>
						<form:errors htmlEscape="true" path="longitude" cssClass="error"/>
						<form:errors htmlEscape="true" path="latitude" cssClass="error"/>
			          </div>
			          <div id="divLatitudeLongitude" class="form-group" style="margin-left: 25px;">
					  </div>
			          
			          <div id="divMapUpload" style="display: none;">
						<div class="form-group" >
						<label for="aliasLocal" class="col-sm-6 control-label"><spring:message code="Label.UPLOADMAP" htmlEscape="true"/>
							<spring:message code='Label.allowedfileexe' htmlEscape='true'/></label>
							<div class="col-sm-6">
	                  					<form:input class="form-group" path="mapUpload" id="mapUpload" type="file"/>
						<form:errors htmlEscape="true" path="mapUpload" cssClass="error"/>
	                  				</div>
							
						</div>
					</div>	
					</div>
	                 		
	                 		
	             <%@include file="ExistingGovernmentOrderCpy.jsp"%>
				<br/>
				<div class="box-footer">
                     <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
                           <div style="display: none" id="drafthide">
                           <input type="submit" id="btnFormActionSaveDraft" param="DRAFT" type="submit" class="btn btn-primary" value="<spring:message code="Button.DRAFT" htmlEscape="true"></spring:message>"/>  
                           </div>
							<input type="submit" id="btnFormActionSave" param="PUBLISH" class="btn btn-success" value="<spring:message htmlEscape="true"  code="Button.SP"></spring:message>"/>	
							<input type="button" id="btnActionClose" param="home" class="btn btn-danger" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"/>	
								
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

