<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<head>

<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/validation.js"></script>
<script type="text/javascript" src="js/createDistrict.js"></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrSubVillageService.js'></script>
 <script src="resource/dashboard-resource/bootstrap/js/bootstrap.min.js"></script>

<!-- <script type="text/javascript" src="js/new_sub_district.js" charset="utf-8"></script> -->
<script>
	//window.onload = onloaddistrict;
</script>

</head>
<body>
	<%@include file="addDistrict.jsp"%>
	<section class="content">
    	<!-- Main row -->
    	<div class="row">
        	<!-- main col -->
              <section class="col-lg-12 ">
	              	<div class="box ">
		                			<div class="box-header with-border">
		
		                    			<h3 class="box-title"><spring:message htmlEscape="true" code="Label.CREATEDISTRICT"></spring:message></h3>
		                			</div>
		                          <!-- /.box-header -->
		                <div class="box-body">
							<div class="box-header subheading">
		                              <h4><spring:message htmlEscape="true" code="Label.GENERALDETAILNEWDISTRICT"></spring:message></h4>
		                    </div>
		                 
		                    <form:form action="addNewDistrict.htm" class="form-horizontal"  method="POST" commandName="district" id="form1" name="form1" enctype="multipart/form-data">
		                    	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="addNewDistrict.htm"/>" />
		                    	
		         <div id="DistrictdetailId" class="frmpnlbg" style="${display}">
					
					<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message code="Label.DISTRICTDETAILS" htmlEscape="true"></spring:message></h4>
               	    </div><!-- /.box-header -->
					
					
				
						<c:set value="1" var="count"></c:set>
						<c:set value="WHITE" var="oddeven"></c:set>
						<div id="accordion" style="width:100%">
							<c:forEach var="histroyDistrict" items="${histroyDistrict}">
								<%-- <h3>
									<label><a href="#">${histroyDistrict.districtName}</a> </label>
								</h3> --%>
								<div>
									<table width="100%" class="tblRowTitle tblclear">
										<tr>
											<td align="center" width="3%"><label><spring:message htmlEscape="true" code="Label.SNO"></spring:message> </label>
											</td>
											<td align="center" width="7%"><label><spring:message htmlEscape="true" code="Label.DISTRICTNAME"></spring:message> </label></td>
											<td align="center" width="10%"><label><spring:message htmlEscape="true" code="Label.CONTRIBUTINGDISTRICT"></spring:message> </label>
											</td>
											<td align="center" width="10%"><label><spring:message htmlEscape="true" code="Label.CONTRIBUTINGSUBDISTRICT"></spring:message> </label>
											</td>
											<td align="center" width="10%"><label><spring:message htmlEscape="true" code="Label.CONTRIBUTINGNEWSUBDISTRICT"></spring:message> </label>
											</td>
											<td align="center" width="10%"><label><spring:message htmlEscape="true" code="Label.CONTRIBUTINGVILLAGES"></spring:message> </label>
											</td>
											<%-- 	<td align="center" width="10%"><label><spring:message htmlEscape="true"  code="Label.OPERATION"></spring:message></label></td> --%>
											<td align="center" width="10%"><label><spring:message htmlEscape="true" code="Label.DISTRICTSTATUS"></spring:message> </label>
											</td>
										</tr>
									</table>
									<table width="100%" class="tbl_no_brdr">
										<tr>
											<td align="center" width="1%">${count}</td>
											<td align="left" width="10%"><label>${histroyDistrict.districtName }</label></td>
											<td align="left" width="10%">
												<ul style="text-align: center;">
													<c:forEach var="contributeDistrictList" items="${histroyDistrict.contributeDistrictList}">
														<c:if test="${empty contributeDistrictList}">
													    ----
													</c:if>
														<c:if test="${not empty contributeDistrictList}">
															<li>${contributeDistrictList}</li>
														</c:if>
													</c:forEach>
												</ul>
											</td>
											<td align="left" width="10%">
												<ul>
													<c:forEach var="contributeSubDistrictList" items="${histroyDistrict.contributeSubDistrictList}">
														<c:if test="${empty contributeSubDistrictList}">
													    ----
													</c:if>
														<c:if test="${not empty contributeSubDistrictList}">
															<li>${contributeSubDistrictList}</li>
														</c:if>
													</c:forEach>
												</ul>
											</td>
											<td align="left" width="10%">
												<ul>
													<c:forEach var="contributeNewCreatedSubDistrictList" items="${histroyDistrict.contributeNewCreatedSubDistrictList}">
														<c:if test="${contributeNewCreatedSubDistrictList =='null'}">
													    ----
													</c:if>
														<c:if test="${contributeNewCreatedSubDistrictList !='null'}">
															<li>${contributeNewCreatedSubDistrictList}</li>
														</c:if>
													</c:forEach>
												</ul>
											</td>
											<td align="left" width="10%">
												<ul style="height: 150px">
													<c:set var="subdistrictNames" value=""/>
													<c:forEach var="ss" items="${fn:split(histroyDistrict.villageMergeList, ',')}">
														<c:set var="string5" value="${fn:substringBefore(ss,'#')}"/>
														<c:set var="subdistrictNames" value="${subdistrictNames}, ${string5}"/>
													</c:forEach>
													<c:set var="mergeSubdistrict" value="${fn:substringAfter(subdistrictNames,',')}"></c:set>
													<c:forEach var="villMergeTotal" items="${fn:split(histroyDistrict.villageMergeList,',')}">
														<c:set var="villageMergeList" value="${fn:substringAfter(villMergeTotal,'#')}"></c:set>
														
														<c:forEach var="villageMerge" items="${fn:split(villageMergeList,':')}">
															<c:set var="checkmerge" value=""></c:set>
															<c:if test="${not empty villageMerge}">
																<li>${villageMerge}</li>
																<c:set var="checkmerge" value="true"></c:set>
															</c:if>
														</c:forEach>
													</c:forEach>
													<c:if test="${not empty checkmerge}">
														<span class="blink"><font style="font-weight: bold;" color="#105CA8">Merged into ${mergeSubdistrict}</font> </span>
													</c:if>
													
													<c:set var="addSubdistrictNames" value=""/>
													<c:forEach var="sd" items="${fn:split(histroyDistrict.villageNewList, ',')}">
														<c:set var="stringadd" value="${fn:substringBefore(sd,'#')}"/>
														<c:set var="addSubdistrictNames" value="${addSubdistrictNames}, ${stringadd}"/>
													</c:forEach>
													<c:set var="addSubdistrict" value="${fn:substringAfter(addSubdistrictNames,',')}"></c:set>
													
													<c:forEach var="villAddTotal" items="${fn:split(histroyDistrict.villageNewList,',')}">
														<c:set var="villageAddList" value="${fn:substringAfter(villAddTotal,'#')}"></c:set>
														<c:forEach var="villageNew" items="${fn:split(villageAddList,':')}">
															<c:set var="check" value=""></c:set>
															<c:if test="${not empty villageNew}">
																<li>${villageNew}</li>
																<c:set var="check" value="true"></c:set>
															</c:if>
														</c:forEach>
													</c:forEach>
													
													<c:if test="${not empty check}">
														<span class="blink"><font style="font-weight: bold;" color="#089553">Added into ${addSubdistrict}</font> </span>
													</c:if>
												</ul>
											</td>
											<%-- 	<td align="center" width="10%"><button id="create-user${count}" onclick="return openwin('${histroyDistrict.subdistrictNameEnglish }','${subDistrictInfoList.subdistrictNameLocal }','${subDistrictInfoList.aliasEnglish }','${subDistrictInfoList.aliasLocal }','${subDistrictInfoList.census2011Code }','${subDistrictInfoList.sscode }','${subDistrictInfoList.villageNumber}');">Modify</button></td> --%>
											<td align="center" width="10%"><font style="font-weight: bold;" color="#FF3232"><label><span class="blink"><spring:message code="Label.PENDING" htmlEscape="true"> </spring:message>
													</span> </label> </font></td>
										</tr>
									</table>
								</div>
								<c:set value="${count+1}" var="count"></c:set>
							</c:forEach>
						</div>
					
				</div>
		                    	
		                    	
			                	<input type="hidden" name="reorganized" id="reorganized" />
													<input type="hidden" name="modifyVillage" id="modifyVillage" />
													<input type="hidden" name="modifySubDistrict" id="modifySubDistrict" />
													<input type="hidden" name="addAnotherSD" id="addAnotherSD" />
													<input type="hidden" name="statusDist" id="statusDist" />
													<form:hidden path="totalVillage" id="totalVillage" value="${totalVillage}" />
							<form:hidden path="preDistrict" id="preDistrict" value="" />
							<form:hidden path="mergeSelectedVillageListId" id="mergeSelectedVillageListId" value="" />
							<form:hidden path="mergeSubDistId" id="mergeSubDistId" value="" />
							<form:hidden path="mergeVillageListId" id="mergeVillageListId" value="" title="mergeVillageListId"/>
							<form:hidden path="storedCombiValues" id="storedCombiValues" value="" title="storedCombiValues"/>
							<form:hidden path="storedNewCombiValues" id="storedNewCombiValues" value="" title="storedNewCombiValues"/>
							<form:hidden path="districtNameEnglishTemp" id="districtNameEnglishTemp" value="" />
							<form:hidden path="contributedSubDistrictsTemp" id="contributedSubDistrictsTemp" value="" />
							<form:hidden path="preSubDistrictsTemp" id="preSubDistrictsTemp" value="" />
							<form:hidden path="preVillageTemp" id="preVillageTemp" value="" />
							<form:hidden path="buttonClicked" id="buttonClicked" value="" />
							<form:hidden path="historyDistrictList" id="historyDistrictList" value="" />
							<form:hidden path="histrorySubDistrictList" id="histrorySubDistrictList" value="" />
							<form:hidden path="histroryNewSubDistrict" id="histroryNewSubDistrict" value="null" />
							<form:hidden path="histroryNewSubDistrictList" id="histroryNewSubDistrictList" value="null" />
							<form:hidden path="histroryMergeSubDistrictList" id="histroryMergeSubDistrictList" value="null" />
							<form:hidden path="districtNameList" id="districtNameList" value="${districtNameList}" />
				<div style="${style}">			
							
							
			                    	<div class="form-group">
                      					<label for="districtNameInEn" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.DISTRICTNAMEENG"></spring:message><span class="mandatory">*</span></label>
	                      				<div class="col-sm-6">
		                      				<form:hidden path="operation" value="C" /> <form:hidden path="govtOrderConfig" value="${govtOrderConfig}" />
		                        			<form:input path="districtNameInEn" class="form-control" id="districtNameInEn" maxLength="50"  onfocus="show_msg('districtNameInEn')"  onblur="findDuplicate(this.value);"/>	
		                        			<span class="error" id="districtNameInEn_error"></span><span><form:errors htmlEscape="true" path="districtNameInEn" class="errormsg"></form:errors> </span>
	                        				
	                      				</div>
                    				</div>
                    				<div class="form-group">
                      					<label for="districtNameInLcl" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.DISTRICTNAMELOCAL"></spring:message></label>
	                      				<div class="col-sm-6">
		                      				<form:input path="districtNameInLcl" id="districtNameInLcl" class="form-control" maxLength="50" onfocus="show_msg('districtNameInLcl')" onkeyup="return chkValid(this);" htmlEscape="true" onblur="NameInLcl()" />
	                      				</div>
                    				</div>
                    				<div class="form-group">
                      					<label for="districtAliasInEn" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.DISTRICTALIASENGLISH"></spring:message></label>
	                      				<div class="col-sm-6">
		                      				<form:input path="districtAliasInLcl" id="districtAliasInLcl" class="form-control" maxLength="50" onkeyup="return chkValid(this);"  onfocus="show_msg('districtAliasInLcl')" htmlEscape="true" onblur="AliasInLcl()" /> 
	                      				</div>
                    				</div>
                    				
                    				<div class="form-group">
                      					<label for="districtAliasInLcl" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.DISTRICTALIASLOCAL"></spring:message></label>
	                      				<div class="col-sm-6">
		                      				<form:input path="districtAliasInEn" id="districtAliasInEn" class="form-control"  maxLength="50"  onkeyup="return chkValid(this);"  onfocus="show_msg('districtAliasInEn')" htmlEscape="true" onblur="AliasInEn()" /> 
	                      				</div>
                    				</div>
                    				
                    				<div class="form-group">
                      					<label for="sscode" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.STATESPECIFICCODE"></spring:message></label>
	                      				<div class="col-sm-6">
		                      				<form:input path="sscode" class="form-control" id="sscode" name="sscode" maxLength="5" onkeyup="return chkValid(this);" onfocus="show_msg('sscode')" htmlEscape="true" onblur="sscode()" /> 
	                      				</div>
                    				</div>
			                   
			                    
			                  <%--   <div class="box-header subheading">
			                              <h4><spring:message code="Label.GISNODES" htmlEscape="true"></spring:message></h4>
			                    </div>
			                    <div class="form-group" style="margin-left: 25px;">
			                    	<%@ include file="../common/gis_nodesCopy.jspf"%>
			                    </div> --%>
			                    
			                    
			                    
			                    
				                    <div class="box-header subheading">
			                              <h4><spring:message htmlEscape="true" code="Label.HEADQUARTER"></spring:message></h4>
			                    	</div>
				                    <div class="form-group">
	                      					<label for="headquarterName" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.DISTRICTHEADQUARTERENGLISH"></spring:message></label>
		                      				<div class="col-sm-6">
			                      				<form:input type="text" maxLength="50"  class="form-control" htmlEscape="true" name="headquarterName" path="headquarterName" onkeypress="return validateAlphaWithSpace(event);" />
			                      				 <form:errors path="headquarterName" cssStyle="color:red" htmlEscape="true"/>
		                      				</div>
	                    			</div>
	                    			<div class="form-group">
	                      					<label for="headquarterNameLocal" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SUBDISTRICTHEADQUARTERLOCAL"></spring:message></label>
		                      				<div class="col-sm-6">
			                      				<form:input type="text"  maxLength="50" class="form-control" htmlEscape="true" name="headquarterNameLocal" path="headquarterNameLocal" onkeypress="return validateSpecialCharactersUpdateStandardCodeLocal(event)" />
			                      				 <form:errors htmlEscape="true" path="headquarterNameLocal" cssStyle="color:red" />
		                      				</div>
	                    			</div>
	                   				 <div class="box-header subheading">
	                                    <h4><spring:message htmlEscape="true" code="Label.CONTRIBUTINGLANDREGION"></spring:message></h4>
	                                </div>
					                    <div class="ms_container row" style="margin-left: 10px;">
											<div class="ms_selectable col-sm-5 form-group">
											<label><spring:message htmlEscape="true" code="Label.DISTRICTLIST"></spring:message></label><br/>
													<form:select path="districtsourcecode" multiple="multiple" class="form-control" id="ddSourceDistrict"  disabled="${disabled}">
														<form:options items="${distList}" itemLabel="districtNameEnglish" itemValue="districtCode" />
													</form:select>
											</div>
											<div class="ms_buttons col-sm-2"><br />
												<button type="button" id="src2Target1" onclick="addItem('ddDestDistrict','ddSourceDistrict','FULL',true);" class="btn btn-primary btn-xs btn-block">Whole<i class="fa fa-angle-double-right" aria-hidden="true"></i>
												</button>
												<button type="button" id="target2Src1" onclick="removeOnedistrictOption('ddDestDistrict', 'ddSourceDistrict', true);" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-left" aria-hidden="true"></i>
												</button>
												<button type="button" id="target2Src2" onclick="removeAllList('ddDestDistrict', 'ddSourceDistrict', true);" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
												</button>
												<button type="button" id="src2Target2" onclick="addItem('ddDestDistrict','ddSourceDistrict', 'PART',true);" class="btn btn-primary btn-xs btn-block">Part<i class="fa fa-angle-double-right" aria-hidden="true"></i>
												</button>
												
											</div>
											<div class="ms_selection col-sm-5">
											  
											  <label><spring:message htmlEscape="true" code="Label.CONTRIBUTEDISTRICTLIST"></spring:message></label><br/>
												<form:select name="select4" id="ddDestDistrict" class="form-control" multiple="multiple" path="districtNameEnglish"  disabled="${disabled}">
													<form:options items="${listDistrict}" itemLabel="districtNameEnglish" itemValue="aliasEnglish" />
												</form:select>
													 <div>
                            							<button type="button" name="button2" onclick="selectsubdieverythingfdfd(${disabled})" class="btn btn-primary  "> Get Sub-district List</button>
                       								 </div>
												
		                                	 </div>
				                    </div>
			                    
					                    <div class="ms_container row" style="margin-left: 10px;">
											<div class="ms_selectable col-sm-5 form-group">
											<label><spring:message htmlEscape="true" code="Label.SUBDISTRICTLIST"></spring:message></label><br/>
													<form:select name="ddSubdistrictforsubdistrict" class="form-control" id="ddSubdistrictforsubdistrict" path="subDistrictList" multiple="multiple" >
																<form:options items="${preSubDistrictList}" itemLabel="subdistrictNameEnglish" itemValue="subdistrictCode" />
													</form:select>
											</div>
											<div class="ms_buttons col-sm-2"><br>
												<button type="button" id="btnaddSubDistrictFull" name="Submit4" class="btn btn-primary btn-xs btn-block">Whole<i class="fa fa-angle-double-right" aria-hidden="true"></i>
												</button>
												<button type="button" id="buttonRemove1" onclick="removeOnedistrictOption('subDistrictListNew', 'ddSubdistrictforsubdistrict', true)" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-left" aria-hidden="true"></i>
												</button>
												<button type="button" id="buttonRemove2" onclick="removeAllList('subDistrictListNew', 'ddSubdistrictforsubdistrict', true)" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
												</button>
												<button type="button" id="btnaddSubDistrictPart" onclick="addItem('ddDestDistrict','ddSourceDistrict', 'PART',true);" class="btn btn-primary btn-xs btn-block">Part<i class="fa fa-angle-double-right" aria-hidden="true"></i>
												</button>
												
											</div>
											<div class="ms_selection col-sm-5">
											  
											  <label><spring:message htmlEscape="true" code="Label.CONTRIBUTESUBDISTRICTLIST"></spring:message></label><br/>
												<form:select name="select4" class="form-control" id="subDistrictListNew"  multiple="multiple" path="contributedSubDistricts"  htmlEscape="true" disabled="${disabled}">
																<form:options items="${listpostSubDistrictForms}" itemLabel="subdistrictNameEnglish" itemValue="aliasEnglish" />
												</form:select>
															<div>
                            							<button type="button"  onclick="selectVillageList()" class="btn btn-primary ">Get Village List</button>
                       								 </div>
		                                	 </div>
				                    </div>
			                    
	                   				 
					                    <div class="ms_container row" style="margin-left: 10px;">
											<div class="ms_selectable col-sm-5 form-group">
											<label><spring:message htmlEscape="true" code="Label.VILLAGELIST"></spring:message></label><br/>
													<form:select name="villageList" class="form-control" id="villageList" path="" multiple="multiple" >
															</form:select>
											</div>
											<div class="ms_buttons col-sm-2"><br>
												<button type="button" id="btnaddVillageFull" name="Submit4" class="btn btn-primary btn-xs btn-block">Whole<i class="fa fa-angle-double-right" aria-hidden="true"></i>
												</button>
												<button type="button" name="button22" onclick="removeOnedistrictOption('villageListNew', 'villageList', true)" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-left" aria-hidden="true"></i>
												</button>
												<button type="button" name="button22" onclick="removeAllList('villageListNew', 'villageList', true)" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
												</button>
												
											</div>
											<div class="ms_selection col-sm-5">
											  
											  <label><spring:message htmlEscape="true" code="Label.CONTRIBUTVILLAGELIST"></spring:message></label><br/>
												<form:select name="select4" class="form-control" id="villageListNew"  multiple="multiple" path="contributedVillages" >
												</form:select>
												
		                                	 </div>
		                                	 <div>
												<form:select name="select4" id="subDistrictListNewFull" size="1" multiple="multiple" path="contributedSubDistrictsFull" class="frmtxtarea" htmlEscape="true" disabled="true"
													style="display: none;">
													<form:options itemLabel="subdistrictNameEnglish" itemValue="subdistrictCode" />
												</form:select>
											</div>
				                    </div>
			                    
			                    	<div class="ms_container row">
			                    		<div class="ms_selection col-sm-5 col-sm-offset-7">
			                    		<div class="form-group">
								  				<label>
											    	<spring:message htmlEscape="true" code="Reorganize Villages"></spring:message><br/>
											  </label>
								  				<br/><button type="button" id="merge1"  class="btn btn-primary "> Merge Into Subdistricts</button>
								  				<div style="margin-top: 3px;">
								  				<button type="button" id="create" class="btn btn-primary "> Create New Subdistrict</button>
	                              	 	</div>
	                              	 	</div>
	                              	 </div>
			                    </div>
			                    
			             
			                    
			      <%-- <div id="dialogtwo" title="Create New Subdistrict Dialog" style="display: none;">
					<ul id="maincontainer" class="listing">
						<li>
							<div class="ms_container">
							<script>
								if(document.getElementById('dialogVillageListCreate')) {
									var selOb = document.getElementById('dialogVillageListCreate');
									var len = selOb.options.length;
									for ( var i = 0; i < len; i++) {
										selOb.options[i].selected = true;
									}
									for ( var i = len - 1; i >= 0; i++) {
										selOb.remove(i);
									}
								}
							</script>
								<div class="ms_selectable" id="list1">
									<label><spring:message text="Step 1 : Select villages that need to add into new Subdistrict"></spring:message></label>
									<select size="1" multiple="multiple" class="frmtxtarea" name="dialogVillageListCreate" id="dialogVillageListCreate">
									</select>
								</div>
								<div class="ms_buttons"><br></br>
									
										<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all bttn" id="btnaddVillagePart" name="Submit4" value=" &gt;&gt;" onclick="addItemNew('selectedDialogVillageListCreate','dialogVillageListCreate',true)" />
									
										<input name="button22" type="button" class="ui-button ui-widget ui-state-default ui-corner-all bttn" onclick="removeOnedistrictOption('selectedDialogVillageListCreate', 'dialogVillageListCreate', true)" value="&lt;&lt;" />
									
								</div>
								<div class="ms_selection" id="list2"><br/><br/>
									<select size="1" multiple="multiple" class="frmtxtarea" name="selectedDialogVillageListCreate" id="selectedDialogVillageListCreate">
									</select>
								</div>
							</div>
						</li>
						<br></br>
							<li>
								<label><spring:message text="Step 2 : Enter new subdistrict details"></spring:message></label></br>
								<spring:message code="Label.NAMEOFNEWSUBDISTRICTENGLISH" htmlEscape="true"></spring:message> <strong><span id="required" class="errormsg">*</span> </strong><br /> 
								<label> <input type="text" name="subDistName" id="subDistId" class="frmfield" htmlEscape="true" /></label>
							</li>
							<br></br>
							<li>
								<input id="createButtonId" type="button" name="create" onclick="closeDialogtwo('Create Sub District');" value="<spring:message htmlEscape="true"  code="Title.CREATENEWSUBDISTRICT"></spring:message>"></input>
								<input id="closeButtonId" type="button" name="close" onclick="closeDialogtwo('Clear');" value="<spring:message htmlEscape="true" code="Button.CLEAR"></spring:message>"></input>
								<input id="doneButtonId" type="button" name="done" onclick="closeDialogtwo('Done');" value="<spring:message htmlEscape="true" code="Label.DONE" text="Done"></spring:message>"></input>
							</li>
						</ul>
					<div class="clear"></div>
				</div> --%>
			    
			    </div>
				<div class="modal fade" id="dialogtwo" role="dialog">
				<script>
								if(document.getElementById('dialogVillageListCreate')) {
									var selOb = document.getElementById('dialogVillageListCreate');
									var len = selOb.options.length;
									for ( var i = 0; i < len; i++) {
										selOb.options[i].selected = true;
									}
									for ( var i = len - 1; i >= 0; i++) {
										selOb.remove(i);
									}
								}
				 </script>
				    <div class="modal-dialog">
				    
				      <!-- Modal content-->
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal" id="dialogclose" >&times;</button>
				          <h4 class="modal-title">Create New Subdistrict</h4>
				        </div>
				        <div class="modal-body" id="customAlertbody">
				       	   <div class="sm-12">Step 1 : Select villages that need to add into new Subdistrict</div>
				          <div class="ms_container row" >
											<div class="ms_selectable col-sm-5 form-group">
											<label></label>
											<select  multiple="multiple" class="form-control" name="dialogVillageListCreate" id="dialogVillageListCreate">
									             </select>
											</div>
											<div class="ms_buttons col-sm-2"><br></br></br>
											  <button type="button" class="btn btn-primary btn-xs btn-block" id="btnaddVillagePart" name="Submit4"  onclick="addItemNew('selectedDialogVillageListCreate','dialogVillageListCreate',true)" > &gt;&gt;</button>
									          <button name="button22" type="button" class="btn btn-primary btn-xs btn-block" onclick="removeOnedistrictOption('selectedDialogVillageListCreate', 'dialogVillageListCreate', true)"  >&lt;&lt;</button>
												
											</div>
											<div class="ms_selection col-sm-5">
											  
											  <select multiple="multiple" class="form-control" name="selectedDialogVillageListCreate" id="selectedDialogVillageListCreate">
									            </select>
		                                	 </div>
		                                	 
				                    </div>
				                    
				                    <div class="form-group-sm-12">
						 				 Step 2 : Enter new subdistrict details
						 			</div>
						  			
				                <div class="form-group">
				                <label class="col-sm-3 control-label "><spring:message code="Label.NAMEOFNEWSUBDISTRICTENGLISH" htmlEscape="true"></spring:message> <strong><span id="required" class="errormsg">*</span> </strong></label>
				                 <div class="col-sm-6"><input type="text" name="subDistName" id="subDistId" class="form-control" htmlEscape="true" /></div>
				                </div>  
				                    
				                    
				                   	   
				        </div>
				        <div class="modal-footer">
				                <button id="createButtonId" type="button" class="btn btn-info"  name="create" onclick="closeDialogtwo('Create Sub District');" ><spring:message htmlEscape="true"  code="Title.CREATENEWSUBDISTRICT"></spring:message></button>
								<button id="closeButtonId" type="button" name="close" class="btn btn-warning" onclick="closeDialogtwo('Clear');" ><spring:message htmlEscape="true" code="Button.CLEAR"></spring:message></button>
								<button id="doneButtonId" type="button" name="done" class="btn btn-success" onclick="closeDialogtwo('Done');" ><spring:message htmlEscape="true" code="Label.DONE" text="Done"></spring:message></button>
				        </div>
				      </div>
				      
				    </div>
				  </div>
				  
				  
				  <div class="modal fade" id="alertbox" tabindex="-1" role="dialog" >
						<div class="modal-dialog" >
								<div class="modal-content">
					  				<div class="modal-header">
					   				 
					    			  	<h4 class="modal-title" id="alertboxTitle">Create District</h4>
					  				</div>
					  				<div class="modal-body" id="alertboxbody">
					        
					  				</div>
					     			 <div class="modal-footer">
					        		  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					      
					      			</div>
							</div>
						</div>
					</div>
					
					<div class="modal fade" id="confirmbox" tabindex="-1" role="dialog" >
						<div class="modal-dialog" >
								<div class="modal-content">
					  				<div class="modal-header">
					   				</div>
					  				<div class="modal-body" >
					       			 
					  				</div>
					     			 <div class="modal-footer">
					     			</div>
							</div>
						</div>
					</div>
					
					
					<div class="modal fade" id="dialogone" tabindex="-1" role="dialog" >
						<div class="modal-dialog" >
								<div class="modal-content">
					  				<div class="modal-header">
					   				 
					    			  	<h4 class="modal-title" id="alertboxTitle">Merge Into Subdistricts</h4>
					  				</div>
					  				<div class="modal-body" >
					        			<div class="sm-12">Step 1 : Select villages that need to merge</div>
					        			<div class="ms_container row" >
											<div class="ms_selectable col-sm-5 form-group">
											<label></label>
													<select   multiple="true" class="form-control" name="dialogVillageList" id="dialogVillageList"></select>
											</div>
											<div class="ms_buttons col-sm-2"><br>
												<button type="button" id="btnaddVillagePart" class="btn btn-primary btn-xs btn-block" onclick="addItemNew('selectedDialogVillageList','dialogVillageList','',true)"><i class="fa fa-angle-double-right" aria-hidden="true"></i>
												</button>
												<button type="button" id="target2Src"  class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
												</button>
												
												
											</div>
											<div class="ms_selection col-sm-5">
											  
											  <label></label><br/>
												<select multiple="true" class="form-control" name="selectedDialogVillageList" id="selectedDialogVillageList"></select>
												
		                                	 </div>
		                                	 
				                   	 </div>
				                   	 
				                   	 <div class="form-group-sm-12">
						 				 <label >Step 2 : Select subdistrict in which selected villages need to merge</label>
						 				 </div>
						  			<div class="form-group-sm-12">
						  			 <table id="subDistRadioId"></table>
						  			</div>
									
									
									
					     			 <div class="modal-footer">
					        		 	<button type="button" class="btn btn-success" id="mergeButtonId" onclick="closeDialog('Merge');">
												<spring:message htmlEscape="true"  code="Label.MERGE"></spring:message>
												</button>
													
												<button type="button" class="btn btn-success" id="closeButtonId" onclick="closeDialog('Clear');">
												<spring:message htmlEscape="true" code="Button.CLEAR"></spring:message></button>
												
												<button type="button" class="btn btn-success" id="doneButtonId" onclick="closeDialog('Done');">
												<spring:message htmlEscape="true" code="Label.DONE" text="Done"></spring:message></button>
												
												<button type="button" class="btn btn-success" id="mergeanotherButtonId" onclick="closeDialog(this.value);">
												<spring:message htmlEscape="true" code="Label.MERGEANOTHER" text="Merge Another"></spring:message></button>
					      
					      			</div>
							</div>
						</div>
					</div>
					
					
					</div>
					
			    
			    <div class="box-footer">
                     <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
                       		<!--button Add another functionality hide working on letter   -->
                            <%-- <button type="button" style="${visibilityanother}" class="btn btn-info" onclick="addanother('Add Another');"><i class="fa fa-floppy-o"></i> <spring:message code="Button.ADDANOTHER" htmlEscape="true"></spring:message></button> --%>
                            
                            <button type="button" style="${visibility}" name="Submit" class="btn btn-info" onclick="nextButton('Next');"><i class="fa fa-floppy-o"></i> <spring:message code="Button.NEXT" htmlEscape="true"></spring:message></button>
                            
                            <button type="button" style="${visibilityNext}" name="Submit" class="btn btn-primary" onclick="nextSessionButton('NextSession');"><i class="fa fa-floppy-o"></i> <spring:message code="Button.NEXT" htmlEscape="true"></spring:message></button>
								
							
							<%-- <input type="button" name="Submit2" class="btn btn-warning" value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>"/>  --%>	 
							<input type="button" class="btn btn-danger" name="Submit6" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm'" /> 
							<button type="button" style="visibility: hidden" id="Proceed" class="btn btn-primary" onclick="onloadSelect();"><i class="fa fa-floppy-o"></i> <spring:message code="Button.PROCEED" htmlEscape="true"></spring:message></button>	
								
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