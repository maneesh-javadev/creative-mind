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
	<link href="HTML_panchayat - 2.2/css/panchayat_main.css" 	rel="stylesheet" type="text/css" />
	<script src="js/departmentAdminUnit.js"></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
    <script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrDistrictService.js'></script>	
    <script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<!-- <script type="text/javascript" src="js/invalidatetrilocalbody.js"
	charset="utf-8"></script> -->
		<script type="text/javascript" language="javascript">
		dwr.engine.setActiveReverseAjax(true);
		
		$( document ).ready(function() {
			$('#form1 input[type=text]').attr("autocomplete",'off');
		});
		
		
	</script>
	
	<style>
</style>
</head>
<body>
	<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                    
                     <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.GENDETAILENTITY" htmlEscape="true" text="General Details of Admin Unit Entity"></spring:message></h3>
                                </div>
                                 
                      
                   <form:form action="saveAdminEntityUnit.htm" method="POST" id="form1" commandName="departmentAdminForm" class="form-horizontal">
					    <input type="hidden" name="<csrf:token-name/>"  value="<csrf:token-value uri="saveAdminEntityUnit.htm"/>" />
					    <input type="hidden" name="stateCode" id="stateCode" value="<c:out value='${stateCode}' escapeXml='true'></c:out>" />
						<input type="hidden" name="districtCode" id="districtCode"	value="<c:out value='${districtCode}' escapeXml='true'></c:out>" />
						<input type="hidden" name="adminCoverage"	id="adminCoverage" value="" />
						<form:input type="hidden" path="publishOrSave" id = "publishOrSave" />
						<input type="hidden" name="wardCoverage"	id="wardCoverage" value="" />
						<input type="hidden" name="lbidWard" id ='lbidWard' />
						<input type="hidden" name="overlappValue" id ='overlappValue' />
						
                     <div class="box-body">
                        
                            <div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message	code="Label.SELDeptAdministrationUNIT" text="Type of Administrative unit Level" htmlEscape="true"></spring:message> <span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								      <form:select htmlEscape="true" path="adminUnitLevelCode" class="form-control" id="unitLevelCode"  onchange="getParentLevelEntity();getOverlappingValue(this);" onclick="clearDivs();" >
													<form:option value="-1">
														<spring:message code="Label.SELECT" text="-slect"></spring:message>
													</form:option>
													<form:options items="${parentAdminEntity}"  itemLabel="adminLevelNameEng" itemValue="adminUnitCode" />
									   </form:select>
									   <div class="errormsg"> <form:errors htmlEscape="true" path="adminUnitLevelCode"></form:errors> </div>
									  <div id="parentAdminUnitLevel" style="color: red;"></div>
								</div>
							</div>
							
							
							<!-- block for State pop-up in case of center level -->
								<div class="form-group" id="statePopup" style="display: none;">
								<label class="col-sm-3 control-label"><spring:message code="Label.SELECTSTATE" htmlEscape="true"></spring:message> <span class="errormsg">*</span> </label>
									<div class="col-sm-6">
										<select class="form-control" onchange="getParentLvlEntity(this.value);" id="stateId" >
														<option value="0">
															<spring:message code="Label.SELECT"></spring:message>
														</option>
														 <c:forEach var="stateList" items="${stateList}">
														 	<option value="${stateList.stateCode}"><c:out value="${stateList.stateNameEnglish}" escapeXml="true"></c:out></option>
														 </c:forEach>
													</select>
												<div class="errormsg" id="err_state" style="color: red;"></div>
									 </div>			
						        </div>
							<!-- end block for State pop-up in case of center level -->
							<div class="form-group">
								<label class="col-sm-3 control-label"><spring:message code="Label.SELDEPTPARENTUNITENTITY" text="Parent level Administrative Unit Entity"	htmlEscape="true"></spring:message><span class="errormsg">*</span></label>
								<div class="col-sm-6">
								 <form:select htmlEscape="true"	path="parentAdminUnitEntityCode" class="form-control" onchange="resetAdminEntityName();"	id="parentUnit"   onclick="clearDivs()">
														<form:option value="-1">
															<spring:message code="Label.SELECT" text="-select-"></spring:message>
														</form:option>
								 </form:select>  
								<div id="parentAdminUnit" style="color: red;"></div>
								</div>  		
							</div>
								
					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message  code="Label.AdminUnitEntityEng"  text="Administrative Unit Entity Name(In English)"></spring:message><span class="errormsg">*</span> </label>
						   <div class="col-sm-6">	 
							 <form:input path="adminEntityNameEnglish" id="adminLevelNameEng" onblur="chekNameValidatonsforEntity(this.value);getPartAddedCoverage();"
													name="adminLevelNameEng" htmlEscape="true" class="form-control"  maxlength="100"/> <br />
								<div class="errormsg"> <form:errors htmlEscape="true" path="adminEntityNameEnglish"></form:errors> </div>
								<div id="UniqueDeptAdminError" style="color: red;"></div>
						</div>		 
					</div>
						
							<div class="form-group">
					            <label class="col-sm-3 control-label"><spring:message code="Label.AdminUnitEntityLoc"  text="Administrative Unit Entity Name (In Local)"></spring:message></label>
							    <div class="col-sm-6">
								    <form:input path="adminEntityNameLocal"  id="adminLevelNameLocal" name="adminLevelNameLocal" htmlEscape="true" class="form-control"  maxlength="100"/>
									<div class="errormsg"> <form:errors htmlEscape="true" path="adminEntityNameLocal"></form:errors> </div>
								</div>
						    </div> 
						
						
						
						<div class="form-group" id="covdiv">
							 <label class="col-sm-3 control-label"><spring:message code="Label.DEFINEADMINCOVERAGE" text="Define the coverage of the Administration Unit Entity" htmlEscape="true"></spring:message></label>
						<div class="col-sm-6">
							 <label class="radio-inline">
							    <form:radiobutton id="coverageExisttrue" path="coverageExist" htmlEscape="true" value="true"	name="coverageExisttrue" onclick="populateDistrictList();toggleCovergeDiv();"/>Yes
							 </label>
							 		<label class="radio-inline">
							 		<form:radiobutton id="coverageExistfalse" path="coverageExist" htmlEscape="true" value="false" name="coverageExistfalse" onclick="toggleCovergeDiv()"/>No</label>
						   </div>
						</div>
						 
                   
             
                       <div id="coveragediv"  class="box-body" style="visibility: hidden;display: none;">
						
						<div class="box-header subheading"><h4> <spring:message  code="Label.CONTRIBUTINGLANDREGION" htmlEscape="true"></spring:message> </h4></div>
						 
						 <div class="form-group" style="margin-left: 10px;">
						   <label class="checkbox-inline"><input type="checkbox" id="existingLB" onclick="toggleLBCovergeDiv();"></input><spring:message code="existingLB" text="Select From Existing Local Bodies" ></spring:message></label>
						   <label class="checkbox-inline"><input type="checkbox" id="existingLR" onclick="toggleLRCovergeDiv()"></input><spring:message code="existingLR" text="Select From Land Region"></spring:message></label>
						 </div>
						
						
						
						
						
		<div id="LBRegion" style="visibility: hidden; display: none"  class="box-body">
			<div> <label><spring:message code="Label.EXISTINGLANDREGION"></spring:message></label> </div>	
						
					<div class="ms_container row" style="margin-left: 10px;">
	                    <div class="ms_selectable col-sm-5 form-group">
		                  <label > <spring:message htmlEscape="true" code="Label.AVAILABLELocalbodytype"></spring:message> </label>
		                  <form:select path="" class="form-control" id="lbTypeList" multiple="true">
								<form:options items="${uLBTypeList}" itemLabel="nomenclatureEnglish" itemValue="localBodyTypeCode" />
							</form:select>
		               </div>
					 <div class="ms_buttons col-sm-2"><br>
						<button type="button" class="btn btn-primary btn-xs btn-block"  onclick="moveElement('FORWARD');" value="&gt;"><i class="fa fa-angle-double-right" aria-hidden="true"></i> </button>
						<button type="button" class="btn btn-primary btn-xs btn-block"  onclick="moveElement('BACK');" value="&lt;"><i class="fa fa-angle-double-left" aria-hidden="true"></i> </button>
					    <button type="button" class="btn btn-primary btn-xs btn-block"  onclick="moveElement('BACKALL');" value="&lt;&lt;" ><i class="fa fa-angle-double-left" aria-hidden="true"></i><i class="fa fa-angle-double-left" aria-hidden="true"></i> </button>
						<button type="button" class="btn btn-primary btn-xs btn-block"  onclick="moveElement('FORWARDALL');;" value="&gt;&gt;"><i class="fa fa-angle-double-right" aria-hidden="true"></i><i class="fa fa-angle-double-right" aria-hidden="true"></i> </button>
					 </div>
					<div class="ms_selection col-sm-5">
					 <div class="form-group">
					    <label ><spring:message htmlEscape="true" code="Label.CONTRIBUTEURBANLB"></spring:message></label> 
					    <form:select name="select4" id="contributedLBTypeList"  multiple="multiple" path="" class="form-control">
														</form:select>
															
						 <button name="button2" class="btn btn-primary" type="button" onclick="getLbList();" value="Get Urban List" >Get Urban List</button>
					    
					     </div>				
			            </div>
			         </div>
						
						
				<div class="ms_container row" style="margin-left: 10px;">
	                 <div class="ms_selectable col-sm-5 form-group">
		                  <label ><spring:message htmlEscape="true" code="Label.AVAILABLEURBANLB"></spring:message> </label>
		                  <form:select path="" class="form-control" id="SourceLBList" multiple="true"> </form:select>
		               </div>
					 <div class="ms_buttons col-sm-2"><br>
						<button id="src2Target1" class="btn btn-primary btn-xs btn-block" type="button" onclick="addItemPartCheck('contributedLBList','SourceLBList','FULL','G');" value=" Whole &gt;&gt;" >Whole<i class="fa fa-angle-double-right" aria-hidden="true"></i> <i class="fa fa-angle-double-right" aria-hidden="true"></i> </button>
						<button id="target2Src1" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeOnedistrictOption('contributedLBList', 'SourceLBList', true,1);" value="&lt;" ><i class="fa fa-angle-double-left" aria-hidden="true"></i>  </button>
						<button id="target2Src2" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeAllList('contributedLBList', 'SourceLBList', true,1);" value="&lt;&lt;" ><i class="fa fa-angle-double-left" aria-hidden="true"></i> <i class="fa fa-angle-double-left" aria-hidden="true"></i> </button>
						<button id="src2Target2" class="btn btn-primary btn-xs btn-block" type="button" onclick="addItem('contributedLBList','SourceLBList', 'PART',true);" value="Part &gt;&gt;" >Part<i class="fa fa-angle-double-right" aria-hidden="true"></i> <i class="fa fa-angle-double-right" aria-hidden="true"></i> </button>
					 </div>
					<div class="ms_selection col-sm-5">
					 <div class="form-group">
					    <label ><spring:message htmlEscape="true" code="Label.CONTRIBUTEURBANLB"></spring:message></label> 
					    <form:select name="select4" id="contributedLBList"  multiple="multiple" path="" class="form-control">
														</form:select></br>
															
						 <button name="button2" class="btn btn-primary" type="button" onclick="getWardList('N');" value="Get Ward List" >Get Ward List</button>
					    
					     </div>				
			            </div>
			         </div>
						
					
							
				<div class="ms_container row" style="margin-left: 10px;">
	                 <div class="ms_selectable col-sm-5 form-group">
		                  <label ><spring:message htmlEscape="true" code="Label.AVAILEWARDLIST"></spring:message></label>
		                  <form:select name="souceWardList"  id="souceWardList" path="" multiple="multiple" class="form-control">
						 </form:select>
		               </div>
					 <div class="ms_buttons col-sm-2"><br><br>
						<button type="button" class="btn btn-primary btn-xs btn-block" id="btnaddSubDistrictFull" name="Submit4" onclick="addItem('contributedWardList','souceWardList','FULL',true);" value=" Whole &gt;&gt;" >Whole &gt;&gt;</button>
														<button id="buttonRemove1" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeOnedistrictOption('contributedWardList', 'souceWardList', true,0)" value="&lt;" >&lt;</button>
														<button id="buttonRemove2" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeAllList('contributedWardList', 'souceWardList', true,0)" value="&lt;&lt;" >&lt;&lt;</button>
					 </div>
					<div class="ms_selection col-sm-5">
					 <div class="form-group">
					    <label ><spring:message htmlEscape="true" code="Label.CONTRIBUTWARDLIST"></spring:message></label> 
					   <form:select name="select4" id="contributedWardList"  multiple="multiple" path="" class="form-control" htmlEscape="true">
														</form:select>
															
						 <button name="button2" class="btn btn-primary" type="button" onclick="emptyLBList()"  ><spring:message code="Button.RESET" htmlEscape="true"></spring:message></button>
					    
					     </div>				
			            </div>
			         </div>

									
							
					</div>				
					
						<div id="LRRegion"  style="visibility: hidden; display: none"  class="box-body">
						<div class="box-header subheading"> <label><spring:message code="Label.ADMINEXISTINGLANDREGION" text="Existing Land Regions"></spring:message></label> </div>	
						  <div class="ms_container row"  id="stateSelectionBlock" style="visibility: hidden; display: none;margin-left: 10px;">
	                        <div class="ms_selectable col-sm-5 form-group">
			                  <label ><spring:message htmlEscape="true" code="Label.STATELIST"></spring:message></label>
			                    <form:select path="" class="form-control" id="SourceState" multiple="true">
									<c:forEach items="${stateList}" var="stateListvar">
										<c:if test="${stateListvar.operation_state == 'F'.charAt(0)}">
											<form:option value="${stateListvar.stateCode}" disabled="true"><c:out value='${stateListvar.stateNameEnglish}' escapeXml='true'></c:out></form:option>
										</c:if>
										<c:if test="${stateListvar.operation_state == 'A'.charAt(0)}">
											<form:option value="${stateListvar.stateCode}"><c:out value='${stateListvar.stateNameEnglish}' escapeXml='true'></c:out></form:option>
										</c:if>
									</c:forEach>
								</form:select>
		              	    </div>
					    <div class="ms_buttons col-sm-2"><br>
							<button id="src2Target1" class="btn btn-primary btn-xs btn-block" type="button" onclick="addItemPartCheck('contributedState','SourceState','FULL','S');" value=" Whole &gt;&gt;" > Whole &gt;&gt;</button>
							<button id="target2Src1" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeOnedistrictOption('contributedState', 'SourceState', true,5);" value="&lt;" > &lt;</button>
							<button id="target2Src2" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeAllList('contributedState', 'SourceState', true,5);" value="&lt;&lt;" > &lt;&lt;</button>
							<button id="src2Target2" class="btn btn-primary btn-xs btn-block" type="button" onclick="addItem('contributedState','SourceState', 'PART',true);" value="Part &gt;&gt;" >Part &gt;&gt;</button>
					   </div>
					<div class="ms_selection col-sm-5">
					 <div class="form-group">
					    <label ><spring:message htmlEscape="true" code="Label.CONTStateList" text="Contributing States List"></spring:message></label> 
					   <form:select name="select4" id="contributedState"  multiple="multiple" path="" class="form-control">
												</form:select>
															
						 <button name="button2" class="btn btn-primary " type="button" onclick="getDistrictList('N')" >Get District List</button>
					    
					     </div>				
			            </div>
			         </div>
						  
				<div class="ms_container row" style="margin-left: 10px;" >
	                        <div class="ms_selectable col-sm-5 form-group">
			                  <label > <spring:message htmlEscape="true" code="Label.DISTRICTLIST"></spring:message></label>
			                    <form:select path="" class="form-control" id="SourceDistrict" multiple="true">
													</form:select>
		              	    </div>
					    <div class="ms_buttons col-sm-2"><br>
							<button id="src2Target1" class="btn btn-primary btn-xs btn-block" type="button" onclick="addItemPartCheck('contributedDistirct','SourceDistrict','FULL','D');" value=" Whole &gt;&gt;" >Whole &gt;&gt;</button> 
							<button id="target2Src1" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeOnedistrictOption('contributedDistirct', 'SourceDistrict', true,2);" value="&lt;" > &lt;</button>
							<button id="target2Src2" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeAllList('contributedDistirct', 'SourceDistrict', true,2);" value="&lt;&lt;" >&lt;&lt;</button>
							<button id="src2Target2" class="btn btn-primary btn-xs btn-block" type="button" onclick="addItem('contributedDistirct','SourceDistrict', 'PART',true);" value="Part &gt;&gt;" >Part &gt;&gt;</button>
					   </div>
					<div class="ms_selection col-sm-5">
					 <div class="form-group">
					    <label ><spring:message htmlEscape="true" code="Label.CONTRIBUTEDISTRICTLIST"></spring:message></label> 
					   <form:select name="select4" id="contributedDistirct"  multiple="multiple" path="" class="form-control">
												</form:select>
															
						 <button name="button2"  class="btn btn-primary" type="button" onclick="getSubdistrictsList('N')"  >Get Sub-district List</button>
					    
					     </div>				
			            </div>
			         </div>
			         
			         
			         <div class="ms_container row" style="margin-left: 10px;" >
	                        <div class="ms_selectable col-sm-5 form-group">
			                  <label > <spring:message htmlEscape="true" code="Label.SUBDISTRICTLIST"></spring:message></label>
			                   <form:select name="sourceSubDistrict"  id="sourceSubDistrict" path="" multiple="multiple" class="form-control">
												</form:select>
		              	    </div>
					    <div class="ms_buttons col-sm-2"><br>
							<button type="button" class="btn btn-primary btn-xs btn-block" id="btnaddSubDistrictFull" name="Submit4" onclick="addItemPartCheck('contributedSubDistirct','sourceSubDistrict','FULL','T');" value=" Whole &gt;&gt;" >Whole &gt;&gt;</button> 
							<button id="buttonRemove1" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeOnedistrictOption('contributedSubDistirct', 'sourceSubDistrict', true,3)" value="&lt;" >&lt;</button>
							<button id="buttonRemove2" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeAllList('contributedSubDistirct', 'sourceSubDistrict', true,3)" value="&lt;&lt;" >&lt;&lt;</button> 
							<button type="button" class="btn btn-primary btn-xs btn-block" id="btnaddSubDistrictPart" name="Submit4" value=" Part &gt;&gt;" onclick="addItem('contributedSubDistirct','sourceSubDistrict','PART',true);" > Part &gt;&gt;</button>
					   </div>
					<div class="ms_selection col-sm-5">
					 <div class="form-group">
					    <label ><spring:message htmlEscape="true" code="Label.CONTRIBUTESUBDISTRICTLIST"></spring:message></label> 
					   <form:select name="select4" id="contributedSubDistirct"  multiple="multiple" path="" class="form-control" htmlEscape="true">
												</form:select>
															
						 <button type="button" class="btn btn-primary" value="Get Village List" onclick="selectVillageList('N');" >Get Village List </button>
						 <button type="button" class="btn btn-primary" value="Get Gram Panchayat List" onclick="getGPList('N')" >Get Gram Panchayat List</button>
					    
					     </div>				
			            </div>
			         </div>
			         <div id="villageListId" style="visibility: hidden; display: none">
			            <div class="ms_container row" style="margin-left: 10px;" >
	                        <div class="ms_selectable col-sm-5 form-group">
			                  <label > <spring:message htmlEscape="true" code="Label.VILLAGELIST"></spring:message> </label>
			                  <form:select name="souceVilalgeList"  id="souceVilalgeList" path="" multiple="multiple" class="form-control">
												</form:select>
		              	    </div>
					    <div class="ms_buttons col-sm-2"><br><br>
							<button type="button" class="btn btn-primary btn-xs btn-block" id="btnaddVillageFull" name="Submit4" onclick="addItem('villageListContributed','souceVilalgeList','FULL',true);" value=" Whole &gt;&gt;" >Whole &gt;&gt;</button>
							 <button name="button22" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeOnedistrictOption('villageListContributed', 'souceVilalgeList', true,0)" value="&lt;" >&lt;</button>
							 <button name="button22" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeAllList('villageListContributed', 'souceVilalgeList', true,0)" value="&lt;&lt;" >&lt;&lt;</button>
					   </div>
					<div class="ms_selection col-sm-5">
					 <div class="form-group">
					    <label ><spring:message htmlEscape="true" code="Label.CONTRIBUTVILLAGELIST"></spring:message></label> 
					  <form:select name="select4" id="villageListContributed"  multiple="multiple" path="" class="form-control">
												</form:select>
															
					    
					     </div>				
			            </div>
			         </div>
			    </div>
						 
						 
		<div id="gpListId" style="visibility: hidden; display: none">
		  <div class="ms_container row" style="margin-left: 10px;" >
	                        <div class="ms_selectable col-sm-5 form-group">
			                  <label > <spring:message htmlEscape="true" code="Label.GRAMPANCHAYTLIST" text="Gram Panchayat List"></spring:message></label>
			                  <form:select name="sourceGP"  id="sourceGP" path="" multiple="multiple" class="form-control">
												</form:select>
		              	    </div>
					    <div class="ms_buttons col-sm-2"><br>
							<button type="button" class="btn btn-primary btn-xs btn-block" id="btnAddGPFull" name="Submit4" onclick="addItemPartCheck('contributedGP','sourceGP','FULL','GP');" value=" Whole &gt;&gt;" >Whole &gt;&gt;</button> 
							<button id="buttonRemove1" type="button" class="btn btn-primary btn-xs btn-block" onclick="removeOnedistrictOption('contributedGP', 'sourceGP', true,4)" value="&lt;" >&lt;</button> 
							<button id="buttonRemove2" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeAllList('contributedGP', 'sourceGP', true,4)" value="&lt;&lt;" >&lt;&lt;</button>
							<button type="button" class="btn btn-primary btn-xs btn-block" id="btnAddGPPart" name="Submit4" value=" Part &gt;&gt;" onclick="addItem('contributedGP','sourceGP','PART',true);" >Part &gt;&gt;</button>
					   </div>
					<div class="ms_selection col-sm-5">
					 <div class="form-group">
					    <label ><spring:message htmlEscape="true" code="Label.CONTRIGRAMPANCHAYT"></spring:message></label> 
					  <form:select name="select4" id="contributedGP"  multiple="multiple" path="" class="form-control" htmlEscape="true">
												</form:select>
															
						 <button type="button" class="btn btn-primary" value="Get Gram Panchayat Village List" onclick="getCoveredAreaofLocalBodyList('N')" >Get Gram Panchayat Village List</button>
					    
					     </div>				
			            </div>
			         </div>
		  </div>				   
									
					
		<div  id="gpVillageListId" style="visibility: hidden; display: none" >
		    <div class="ms_container row" style="margin-left: 10px;" >
	                        <div class="ms_selectable col-sm-5 form-group">
			                  <label > <spring:message htmlEscape="true" code="Label.GPVILLAGELIST" text="Gram Panchayat Village List"></spring:message></label>
			                 <form:select name="sourceGpVillageList"  id="sourceGpVillageList" path="" multiple="multiple" class="form-control">
												</form:select>
		              	    </div>
					    <div class="ms_buttons col-sm-2"><br><br>
							<button type="button" class="btn btn-primary btn-xs btn-block" id="btnAddGpVillageFull" name="Submit4" onclick="addItem('gpVillageListContributed','sourceGpVillageList','FULL',true);" value=" Whole &gt;&gt;" >Whole &gt;&gt;</button>
				 <button name="button22" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeOnedistrictOption('gpVillageListContributed', 'sourceGpVillageList', true,0)" value="&lt;" >&lt;</button> 
				 <button name="button22" class="btn btn-primary btn-xs btn-block" type="button" onclick="removeAllList('gpVillageListContributed', 'sourceGpVillageList', true,0)" value="&lt;&lt;" >&lt;&lt;</button>
					   </div>
					<div class="ms_selection col-sm-5">
					 <div class="form-group">
					    <label ><spring:message htmlEscape="true" code="Label.GPCONTRIBUTVILLAGELIST" text="Contributing Gram Panchayat Village List"></spring:message></label> 
					  <form:select name="select4" id="gpVillageListContributed"  multiple="multiple" path="" class="form-control">
												</form:select>
															
						 <button name="button2" class="btn btn-primary" type="button" onclick="emptyLRList()" value="" ><spring:message code="Button.RESET" htmlEscape="true"></spring:message></button>
					    
					     </div>				
			            </div>
			         </div>
		
		
		
		
		</div>							
													
										
	  </div>
  
				</div>  
		</div>         
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                   <button type="button" class="btn btn-success" name="Submit" onclick="ValidateAndSubmitforEntity('D')" id="submit1" value="" ><spring:message code="Button.SAVE"  htmlEscape="true"  ></spring:message></button>
                   <button type="button" class="btn btn-success" name="Submit" onclick="ValidateAndSubmitforEntity('P')" id="submit2" value="" ><spring:message code="Label.PUBLISH"  htmlEscape="true"  ></spring:message></button>
			       <button type="button" class="btn btn-warning" id="Submit6"  value=""  onclick="emptyElements()" ><spring:message code="Button.CLEAR"></spring:message></button>
                   <button type="button" class="btn btn-danger " name="Cancel"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div>
       
         
             
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script>
	 <div id="dialog-confirm" title="Administrative Unit Entity" style="display: none">
									<p><span class="ui-icon ui-icon-alert"></span> Are you confirmed  to create this Entity as Parent Admin Entity Unit?</p>
								</div>
								 <div id="dialog-clearform" title="Clear Details" style="display: none">
									<p><span class="ui-icon ui-icon-alert" ></span> All details entered will be lost, Do you still want to clear the form?</p>
								</div>
								
		<!-- --------------model start-----				 -->	
			  <div class="modal fade" id="model-confirm" role="dialog">
			    <div class="modal-dialog">
			    
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h4 class="modal-title">Set Parent Org Unit</h4>
			        </div>
			        <div class="modal-body" id="customAlertbodyy">
			           <p><span class="ui-icon ui-icon-alert"></span> Are you confirmed  to create this Entity as Parent Admin Entity Unit?</p>
			        </div>
			        <div class="modal-footer">
			          <button type="button" class="btn btn-default" id="clear" data-dismiss="modal" >Yes</button>
			          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			        </div>
			      </div>
			      
			    </div>
			  </div>
			<!--   ----model end---- -->
			<!-- --------------model start-----				 -->	
			<div class="modal fade" id="sucessAlert" role="dialog">
			    <div class="modal-dialog">
			    
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h4 class="modal-title">Message</h4>
			        </div>
			        <div class="modal-body">
			           <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
					   <esapi:encodeForHTML>${msgid}.</esapi:encodeForHTML>
			        </div>
			        <div class="modal-footer">
			          <button type="button" class="btn btn-default"  id="modelclose" onclick="modelClose();"  data-dismiss="modal">Ok</button>
			        </div>
			      </div>
			      
			    </div>
  			</div>
  			<!--   ----model end---- -->
     </div> 
     <c:if test="${isDeptAdminEntitySaved}">
		<script>
		$(function() {		
			$('#sucessAlert').modal('show');
			var adminUnitLevelCode='${departmentAdminForm.adminUnitLevelCode}';
			var parentAdminUnitEntityCode='${departmentAdminForm.parentAdminUnitEntityCode}';
			//alert("adminUnitLevelCode:"+adminUnitLevelCode+":"+"parentAdminUnitEntityCode:"+parentAdminUnitEntityCode);

			getParentLevelEntity();
			setTimeout(function(){
				$("#unitLevelCode option[value='" + adminUnitLevelCode + "']").attr("selected", "selected");
				$("#parentUnit option[value='" + parentAdminUnitEntityCode + "']").attr("selected", "selected");
				
				
			},200);
		});
		</script>
	</c:if>  
</body>
</html>