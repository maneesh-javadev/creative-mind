<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="MapCoveredAreaJavascript.jsp"%> 

<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
<link href="<%=contextpthval%>/resources-localbody/css/jquery.dataTables.css" rel="stylesheet" type="text/css">	
<script src="<%=contextpthval%>/resources-localbody/scripts/jquery.dataTables.js" type="text/javascript" language="javascript" ></script>
<c:set var="URBAN_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.LB_URBAN.toString()%>"></c:set>	
<c:set var="SUBDISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.SUB_DISTRICT_CONSTANT.toString()%>"></c:set>
<c:set var="VILLAGE_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.VILLAGE_CONSTANT.toString()%>"></c:set>
<c:set var="DISTRICT_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.DISTRICT_CONSTANT.toString()%>"></c:set>
</head>
<body class="dt-example">
	<section class="content">
                <div class="row">
                    <section class="col-lg-12">
                <form:form action="mapCoverageLocalBody.htm" method="post" id="localBodyForm" commandName="localBodyForm" enctype="multipart/form-data">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="mapCoverageLocalBody.htm"/>" />					
					<form:hidden path="localBodyCreationType"/>
					<form:hidden path="localBodyCode" id="paramLBCode"/>
					<form:hidden path="localBodyTypeCode"/>						
					<form:hidden path="changeCoverageTypeLRList"/>
					
						<!-- Set Page Level Heading and element Id(s) Started-->
					<c:set var="lblHeadingCurrentCoverage" value="Label.AVAILABLEFORCURRENTSUB"></c:set>
					<c:set var="lblColumnHeadName" value="Label.SUBDISTRICTNAMEENGLISH"></c:set>
					<c:set var="lblColumnHeadCode" value="Label.SUBDISTRICTCODE"></c:set>
					<c:set var="selectedLevel" value="${SUBDISTRICT_CONSTANT}"></c:set>
					<c:set var="lblAvailableHeading" value="Label.AVAILSUBDISTRICTLIST"></c:set>
					<c:set var="lblContributingHeading" value="Label.CONTRIBUTSUBDISTRICTLIST"></c:set>
					<c:set var="nameElementForContributingLB" value="contributingLBSubDistrictCodes"></c:set>
					<c:set var="nameElementForContributingUnmapped" value="contributingUnmappedSubDistrictCodes"></c:set>
					<c:set var="nameElementForContributing" value="contributingLBSubDistrictCodes"></c:set>
					
					<c:if test="${isDistrictLevel}">
						<c:set var="lblHeadingCurrentCoverage" value="Label.AVAILABLEFORCURRENTDIS"></c:set>
						<c:set var="lblColumnHeadName" value="Label.DISTRICTNAMEINENGLISH"></c:set>
						<c:set var="lblColumnHeadCode" value="Label.DISTRICTCODE"></c:set>
						<c:set var="selectedLevel" value="${DISTRICT_CONSTANT}"></c:set>
						<c:set var="lblAvailableHeading" value="Label.AVAILDISTRICTLIST"></c:set>
						<c:set var="lblContributingHeading" value="Label.CONTRIBUTDISTRICTLIST"></c:set>
						<c:set var="nameElementForContributingLB" value="contributingLBDistrictCodes"></c:set>
						<c:set var="nameElementForContributingUnmapped" value="contributingUnmappedDistrictCodes"></c:set>
						<c:set var="nameElementForContributing" value="contributingLBDistrictCodes"></c:set>
					</c:if> 
                    <div class="box">
                    <div class="box-header with-border">
                      <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.GENERALLOCALGOVTBODYDETAILS"></spring:message></h4>
                    </div>
                        <div class="box-body">
                          <div class="box-header subheading"><h4><spring:message htmlEscape="true"  code="Label.LGSUP"></spring:message></h4></div>
                           
                             <table class="table table-bordered table-hover">
    
							    <tbody>
							      <tr>
							        <td><spring:message code="Label.DISTURBEDSTATE" htmlEscape="true" ></spring:message></td>
							        <td><img src="<%=contextpthval%>/images/red_flg.png" width="13" height="9" /></td>
							      </tr>
							      <tr>
							        <td><spring:message htmlEscape="true"  code="Label.LOCALBODYNAMEENGLISH"></spring:message></td>
							        <td><c:out value="${localBodyForm.localBodyNameEnglish}" escapeXml="true"></c:out></td>
							      </tr>
							      <tr>
							        <td><spring:message htmlEscape="true" code="Label.LOCALBODYNAMELOCAL"></spring:message></td>
							        <td><c:out value="${localBodyForm.localBodyNameLocal}" escapeXml="true"></c:out></td>
							      </tr>
							      <tr>
							        <td><spring:message htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message></td>
							        <td><c:out value="${localBodyForm.localBodyAliasEnglish}" escapeXml="true"></c:out></td>
							      </tr>
							      <tr>
							        <td><spring:message htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message></td>
							        <td><c:out value="${localBodyForm.localBodyAliasLocal}" escapeXml="true"></c:out></td>
							      </tr>
							      
							    </tbody>
							  </table>
							  
							  
						   <div class="box-header subheading">
							      <h4><spring:message htmlEscape="true" code="Label.CORRECTLOCALBODY"></spring:message></h4>
							   </div>
     <div class="box-header  subheading"><h4><spring:message htmlEscape="true"  code="Label.UNMAPPPEDPARTILLYLANDREGIONS"></spring:message></h4></div>
     
     
<!--      District List -->
     
     
             <c:if test="${isDistrictLevel}">
			<div class="ms_container row" style="margin-left: 10px;">
					<div class="ms_selectable col-sm-5 form-group">
						<label ><spring:message htmlEscape="true" code="Label.AVAILDISTRICTLIST"/> </label>
							<form:select path="" id="coverageLRAvailableUnmappedDisttLevel" multiple="multiple" class="form-control"/>
					</div>
						 <div class="ms_buttons col-sm-2"><br><br>
							<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR" value="<spring:message code='Button.WHOLE' htmlEscape='true'/>" param="Whole"/>
							<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR" value="<spring:message code='Button.BACK' htmlEscape='true'/>" param="Back"/>
							<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR" value="<spring:message code='Button.RESET' htmlEscape='true'/>" param="Reset"/>
							<input class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedDisttLevel" paramLBLR="LR" value="<spring:message code='Button.PART' htmlEscape='true'/>" param="Part"/>
						 </div>
						<div class="ms_selection col-sm-5">
						 	<div class="form-group">
						 		<label><spring:message htmlEscape="true" code="Label.CONTRIBUTDISTRICTLIST"/></label>
						    	<form:select path="contributingUnmappedDistrictCodes" class="form-control" id="coverageLRContributingUnmappedDisttLevel" multiple="multiple"  name="${nameElementForContributingUnmapped }">
						     		<c:forEach items="${completedCoverageDetails}" var="completedCoveragesV" >
						    			<c:if test="${completedCoveragesV.landRegionType eq  DISTRICT_CONSTANT}">
											<c:choose>
											<c:when test="${completedCoveragesV.coverageType=='P'}">
												<c:set var="covValue" value="${completedCoveragesV.landRegionCode}_PART"/>
											</c:when>
											<c:otherwise>
												<c:set var="covValue" value="${completedCoveragesV.landRegionCode}_FULL"/>
											</c:otherwise>
											</c:choose>
											
											<form:option value="${covValue}" disabled="true">
												<c:out value="${completedCoveragesV.landRegionNameEnglish}--"/>
												<c:out value="(${completedCoveragesV.landRegionCode})"/>
												<c:choose>
													<c:when test="${completedCoveragesV.coverageType=='P'}">
														<c:out value="--(PARTLY)"/>
													</c:when>
													<c:otherwise>
														<c:out value="--(FULLY)"/>
													</c:otherwise>
												 </c:choose>
											</form:option>
										</c:if>
						    		</c:forEach>
						       </form:select><br/>
							   <form:errors htmlEscape="true" path="contributingUnmappedDistrictCodes" cssClass="error"/>
								
								<label class="button-inline">
									<button class="btn btn-primary" type="button" id="btnFetchCoverageUnmappedSubDistrict" value="" style="width: 100%" 
									param="LB_COVERAGE_UNMAPPED_DISTRICT" level="UnmappedDisttLevel" paramLBLR="LR"><spring:message htmlEscape="true" 
									code="Button.GETSUBDISTRICTL"/></button>
								</label>
							 	<%-- <label class="button-inline">
									<button class="btn btn-primary" type="button" id="btnCoveragePartFullDisttLevel" value="" param="coverageLRContributingUnmappedDisttLevel" paramLandregionType="D"> <spring:message htmlEscape="true" 
									code="Button.ChnageCOVPARTFULL" text="Change Exist Coverage PART/FULL"/></button>
								 </label> --%>
					     	</div>				
			            </div>
                </div>
                </c:if> 
				
<!--  District List  Ends-->
				
<!-- Subdistrict List -->
				
			    <div class="ms_container row" style="margin-left: 10px;">
				         <div class="ms_selectable col-sm-5 form-group">
					      		<label ><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"/> </label>
					            <form:select path="" id="coverageLRAvailableUnmappedIntermediateLevel" multiple="multiple" class="form-control"/>
					     </div>
						 <div class="ms_buttons col-sm-2"><br><br>
							<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR"   param="Whole"><spring:message code='Button.WHOLE' htmlEscape='true'/></button>
							<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR"   param="Back"><spring:message code='Button.BACK' htmlEscape='true'/></button>
							<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR"   param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/></button>
							<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR"   param="Part"><spring:message code='Button.PART' htmlEscape='true'/></button>
						 </div>
						<div class="ms_selection col-sm-5">
						 	<div class="form-group">
						 		<label><spring:message htmlEscape="true" code="Label.CONTRIBUTSUBDISTRICTLIST"/></label>
						    	<form:select path="contributingUnmappedSubDistrictCodes" class="form-control" id="coverageLRContributingUnmappedIntermediateLevel" multiple="multiple"  name="${nameElementForContributingUnmapped }">
						     		<c:forEach items="${completedCoverageDetails}" var="completedCoveragesV" >
						    			<c:if test="${completedCoveragesV.landRegionType eq  SUBDISTRICT_CONSTANT}">
											<c:choose>
											<c:when test="${completedCoveragesV.coverageType=='P'}">
												<c:set var="covValue" value="${completedCoveragesV.landRegionCode}_PART"/>
											</c:when>
											<c:otherwise>
												<c:set var="covValue" value="${completedCoveragesV.landRegionCode}_FULL"/>
											</c:otherwise>
											</c:choose>
											
											<form:option value="${covValue}"	 disabled="true">
												<c:out value="${completedCoveragesV.landRegionNameEnglish}--"/>
												<c:out value="(${completedCoveragesV.landRegionCode})"/>
												<c:choose>
													<c:when test="${completedCoveragesV.coverageType=='P'}">
														<c:out value="--(PARTLY)"/>
													</c:when>
													<c:otherwise>
														<c:out value="--(FULLY)"/>
													</c:otherwise>
												 </c:choose>
												  <c:if test="${isDistrictLevel}">
												  <c:out value="[District:${completedCoveragesV.parentName}]"/>
												  </c:if>
											</form:option>
										</c:if>
						    		</c:forEach>
						       </form:select><br/>
							   <form:errors htmlEscape="true" path="contributingUnmappedSubDistrictCodes" cssClass="error"/>
								 <c:if test="${isDistrictLevel}">   
								<label class="button-inline">
										<button class="btn btn-primary" type="button" id="btnFetchCoverageUnmappedSubDistrict" value="" 
										 param="LB_COVERAGE_UNMAPPED_SUB_DISTRICT" level="UnmappedIntermediateLevel" paramLBLR="LR">
										 <spring:message htmlEscape="true" code="Button.GETVILLAGEL"/></button>
													 
								</label>
								</c:if>
							
							<%--  	<label class="button-inline">
									<button class="btn btn-primary" type="button" id="btnCoveragePartFullIntermediateLevel" value="" param="coverageLRContributingUnmappedIntermediateLevel" paramLandregionType="T"> <spring:message htmlEscape="true" 
									code="Button.ChnageCOVPARTFULL" text="Change Exist Coverage PART/FULL"/></button>
								 </label> --%>
					     	</div>
			            </div>
                </div>        

<!-- Subdistrict List ends-->

<%-- 			         <c:if test="${!isDistrictLevel}">    --%>
<!-- Village List -->
			     		<div class="ms_container row" style="margin-left: 10px;">
				           <div class="ms_selectable col-sm-5 form-group">
					               <label ><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"/></label>
					              <form:select path="" id="coverageLRAvailableUnmappedVillageLevel" multiple="multiple" class="form-control" />
					        </div>
								 <div class="ms_buttons col-sm-2"><br><br>
									<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR" value="" param="Whole"><spring:message code='Button.WHOLE' htmlEscape='true'/></button>
									<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR" value=""  param="Back"><spring:message code='Button.BACK' htmlEscape='true'/></button>
									<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR" value="" param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/></button>
									<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button" level="UnmappedVillageLevel" paramLBLR="LR" value=""  param="Part"><spring:message code='Button.PART' htmlEscape='true'/></button>
								 </div>
						<div class="ms_selection col-sm-5">
						 <div class="form-group">
						 <label><spring:message htmlEscape="true" code="Label.CONTRIBUTVILLAGELIST"/></label>
						    <form:select path="contributingUnmappedVillageCodes" id="coverageLRContributingUnmappedVillageLevel" multiple="multiple" class="form-control">
						    	<c:forEach items="${completedCoverageDetails}" var="completedCoveragesV" >
											<c:if test="${completedCoveragesV.landRegionType eq  VILLAGE_CONSTANT}">
											<form:option value="${completedCoveragesV.landRegionCode}_${completedCoveragesV.coverageType}" disabled="true">
											
												<c:out value="${completedCoveragesV.landRegionNameEnglish}--"/>
												<c:out value="(${completedCoveragesV.landRegionCode})"/>
												<c:choose>
													<c:when test="${completedCoveragesV.coverageType=='P'}">
														<c:out value="--(PARTLY)[Subdistrict:${completedCoveragesV.parentName}]"/>
													</c:when>
													<c:otherwise>
														<c:out value="--(FULLY)[Subdistrict:${completedCoveragesV.parentName}]"/>
													</c:otherwise>
												 </c:choose>
											</form:option>
											
											</c:if>
								</c:forEach>
							</form:select>
								<form:errors htmlEscape="true" path="contributingUnmappedVillageCodes" cssClass="error"/>
							</div>
						</div>
						<%-- <label class="button-inline">
							<button class="btn btn-primary" type="button" id="btnCoveragePartFullIntermediateLevel" value="" param="coverageLRContributingUnmappedVillageLevel" paramLandregionType="V"> <spring:message htmlEscape="true" 
							code="Button.ChnageCOVPARTFULL" text="Change Exist Coverage PART/FULL"/></button>
						</label> --%>
					</div> 
<%-- 			</c:if>  --%>

<!-- Village List Ends-->

             </div>
                  <div class="box-footer">
		            <div class="col-sm-offset-2 col-sm-10">
		                 <div class="pull-right">
		                 <input class="btn btn-success" id="btnFormActionSave" type="submit" value="<spring:message htmlEscape="true" code="Button.UPDATE"/>" param="UPDATE"/>
						<input class="btn btn-danger" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'"/>
						  </div>
                      </div>
                   </div>
                   
        
        
         <div class="modal fade" id="displayExistingLocalBody" tabindex="-1" role="dialog" style="display: none;">
		<div class="modal-dialog" style="width:950px;">
				<div class="modal-content">
	  				<div class="modal-header">
	   				   <button type="button" class="close" data-dismiss="modal">&times;</button>
	    			  	<h4 class="modal-title">Associated Local bodies</h4>
	    			  	
	  				</div>
	  				<div class="modal-body" >
	        			<table id="existingLocalBodyDetails" class="data_grid" width="100%" style="display: none;">
						<thead>
							<tr>
								<th>Localbodycode</th>
								<th>Localbodyname</th>
							</tr>
							
						</thead>
						<tbody id="tbodyExistLB">
						</tbody>
					</table>
	        			
	     			 
					</div>
					
		</div>
	</div>
</div>           
<!-- <div id="displayExistingLocalBody" class="form_stylings" style="display: none;">

	<table id="existingLocalBodyDetails" class="data_grid" width="100%" style="display: none;">
		<thead>
			<tr>
				<th>Localbodycode</th>
				<th>Localbodyname</th>				
			</tr>
			
		</thead>
		<tbody id="tbodyExistLB">
		</tbody>
	</table>
</div>  -->
                   
                        
                    </div>
                    
                    	<div class="modal fade" id="changeExistCoverage" tabindex="-1" role="dialog" >
						<div class="modal-dialog" >
								<div class="modal-content">
					  				<div class="modal-header">
					   				 
					    			  	<h4 class="modal-title" id="alertboxTitle"><c:out value="Change existing coverage FULL to PART"/></h4>
					  				</div>
					  				<div class="modal-body" >
					        
					        
					        
					      <div class="ms_container row" style="margin-left: 10px;">
			           			<div class="ms_selectable col-sm-5 form-group">
				              	 <label ><spring:message htmlEscape="true" code="label.avilable.exist.coverage"/> </label>
				              	<form:select path="" id="avilableExistCoverage" class="form-control">
				              		
				              	</form:select>
					       </div>
						   <div class="ms_buttons col-sm-2"><br><br>
								<button class="btn btn-primary btn-xs btn-block"  type="button" id="changeExistCoverageFull" param="FULL" paramLandregionType="x" ><spring:message code='Button.WHOLE' htmlEscape='true'/></button>
								<button class="btn btn-primary btn-xs btn-block"  type="button" id="changeExistCoverageBACK" param="BACK" paramLandregionType="x"><spring:message code='Button.BACK' htmlEscape='true'/></button>
							<%-- 	<button class="btn btn-primary btn-xs btn-block" id="btnEventChangeCoverage" type="button" level="UnmappedIntermediateLevel" paramLBLR="LR"   param="Reset"><spring:message code='Button.RESET' htmlEscape='true'/></button> --%>
								<button class="btn btn-primary btn-xs btn-block"   type="button" id="changeExistCoveragePART" param="PART" paramLandregionType="x" ><spring:message code='Button.PART' htmlEscape='true'/></button>
							</div>
							<div class="ms_selection col-sm-5">
						 		<div class="form-group">
						 			<label><spring:message htmlEscape="true" code="label.contribute.exist.coverage"/></label>
						    		<form:select path="" class="form-control" id="contributeExistCoverage" multiple="multiple" >
							    		
						    		</form:select>
												
						    	</div>
			            	</div>
                		</div> 
                       
					        
					        </div>
					     			 <div class="modal-footer">
					     			 <button type="button"  id="ChangeCoverageLandregionType" param="xyz" class="btn btn-default" data-dismiss="modal">Change Coverage</button>
					        		  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					      
					      			</div>
							</div>
						</div>
					</div>
                    </form:form>
             
                    </section>
                </div>
            </section>
</body>
</html>