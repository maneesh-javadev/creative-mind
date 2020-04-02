<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% String contextPath = request.getContextPath(); %>
<head>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrWardService.js'></script>
<script type="text/javascript" src="js/addnManageWardCoverage.js"></script>
<c:choose>
<c:when test="${PANCHAYAT_TYPE eq 'P'}">
<c:set var="formTitle" value="Label.addnManagePRIWardCoverage"></c:set>
</c:when>
<c:when test="${PANCHAYAT_TYPE eq 'T'}">
<c:set var="formTitle" value="Label.addnManageTRIWardCoverage"></c:set>
</c:when>
<c:when test="${PANCHAYAT_TYPE eq 'U'}">
<c:set var="formTitle" value="Label.addnManageURBANWardCoverage"></c:set>
</c:when>
</c:choose>




<%-- <link rel="stylesheet" href="<%=contextPath %>/css/wardCoverage.css"> --%>
<%-- 	 <link rel="stylesheet" href="<%=contextPath %>/css/localbody.css"> --%>
<%-- <link href="<%=contextPath%>/resources-localbody/css/localbody.css" rel="stylesheet" type="text/css" /> --%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
function backMethod(formid, annotation) {
	var form = document.getElementById(formid);
	form.action = annotation + "?<csrf:token uri='" + annotation + "'/>";
	form.method = "post";
	form.submit();
}

</script>
<c:if test="${dataExists eq true and PANCHAYAT_TYPE ne 'U'}">
<script>
$(window).load(function () {
	$('#localBodyType option[value != ""]').remove();
	var _lb_type_value = $('#lbTypeHierarchy option:selected').val();
	if (!$_checkEmptyObject(_lb_type_value)) {
		var optVaues = _lb_type_value.split(',');
		var setupCode = optVaues[0]; // local body setup code
		var setupVersion = optVaues[1]; // local body setup version
		dwrRestructuredLocalBodyService.buildLocalBodyHierarchy(parseInt(setupCode), parseInt(setupVersion), {
			callback : function(result) {
				populateLBType(result);
				$("#localBodyType option[value='${wardForm.paramLocalBodyTypeCode}']").attr("selected", "selected");
				registerCallDynamicHierarchy($('#localBodyType').get(0));
				setTimeout(function(){
					var localbodyLevelCodes =  '${wardForm.localBodyLevelCodes}'.split(",");
					var localBodyLevelElement = $("SELECT[name='localBodyLevelCodes']");
					var elementCount = $(localBodyLevelElement).size();
					$(localBodyLevelElement).each(function(index){
						$("#" + $(this).attr('id') +" option[value='" + localbodyLevelCodes[index] + "']").attr("selected", "selected");
						if(index < (elementCount) ){
							configureLocalBodyTypeHierarchy(this);	
						}
						if(index == elementCount ){
							var elementId = $(this).attr('id');
							setTimeout(function(){
								$("#" + elementId +" option[value='" + localbodyLevelCodes[index] + "']").attr("selected", "selected");
							},200);
						}
					});
				}, 200);
			},
			errorHandler : errorLbTypeProcess,
			async : true
		});
	}
}); 
</script>
</c:if>

</head>
<body>

<!-- Main Form Styling starts -->
	<div class="form_stylings">
		
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message code="Label.addnManageWardCoverage" text="Add n Manage Ward Coverage" /></h3>
			<ul class="item_list">
			<%--//these links are not working 	<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				
				<form:form   commandName="wardForm"  id="wardCoverageForm" action="saveWardCoverage.htm"  method="POST">
 				<!-- <form  id='form1' action="saveWardCoverage.htm"  method="POST"> -->
				<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="saveWardCoverage.htm" />" />
				<form:hidden path="wardCode" />
				<form:hidden path="localBodyCode"  />
				<form:hidden path="selLevel" />
				<form:hidden path="PanchayatType" />
				<form:hidden path="lblc" />
				<form:hidden path="paramLocalBodyTypeCode" />
				<form:hidden path="localBodyLevelCodes" />
				<form:hidden path="URBAN_LEVEL" />
				<div class="form_block">
						<div class="col_1">
								<h4><spring:message code="Label.wardDetails" text="Ward Details" /></h4>
								<ul class="form_body" >
									<li >
								  		<label class="inline"><spring:message code="Label.WARDNUMBER"/> </label><label class="inline"><esapi:encodeForHTMLAttribute>${localbodyWard.wardNumber}</esapi:encodeForHTMLAttribute> </label>
								 	</li>
									<li>
								  		<label class="inline"><spring:message code="Label.WARDNAMEINENG"/> </label><label class="inline"><esapi:encodeForHTMLAttribute>${localbodyWard.wardNameEnglish}</esapi:encodeForHTMLAttribute> </label>
								 	</li>
								 	<li>
								  		<label class="inline"><spring:message code="Label.WARDNAMEINLOCAL"/></label><label class="inline"><esapi:encodeForHTMLAttribute>${localbodyWard.wardNameLocal}</esapi:encodeForHTMLAttribute> </label>
								 	</li>
								</ul>
					     </div>
				</div>
				</br>
										
								
				
				<div class="form_block">
						<div class="col_1">
								<h4><spring:message code="Label.MODIFYCOVERAGEWARD"  text="Modify Coverage of a Ward" /></h4>
								<ul class="form_body" >
								<li>
										<div id="errorMsg" class="errormsg">${errorMsg}</div>
										
								</li>
								<br/>
								   <c:choose>
								   <c:when test="${PANCHAYAT_TYPE eq 'U'}">
								   	 	
								   	 	
								   	 	 	<c:if test="${fn:contains(wardForm.URBAN_LEVEL, 'D')}">
								   	 	 	 	<li id="districtLevelDiv" >
												<div class="ms_container">
													<div class="ms_selectable">
														<label><spring:message htmlEscape="true" code="Label.AVAILDISTRICTLIST"/></label>
														<select id="localbodyCoverageDistrictList"	 multiple="multiple" >
															<c:forEach var="obj" items="${localbodyCoverageDistrictList}" >
																				<option value="${obj.entityCode }">${obj.entityName}</option>
															</c:forEach>
														</select>
													</div>
													<div class="ms_buttons">
														<input class="bttn" id="btnEventCoverage" type="button" level="SubdistrictLevel" paramLBLR="LB" value="Whole" param="Whole"
														onclick="addItemforLBWardCoverage('localbodyCoverageDistrictList','wardCoverageDistrictList','Full');" />
														<input class="bttn" id="btnEventCoverage" type="button" level="SubdistrictLevel" paramLBLR="LB" value="Back"  param="Back"
														onclick="backItemfromLBWardCoverage('localbodyCoverageDistrictList','wardCoverageDistrictList','D');" />
														<input class="bttn" id="btnEventCoverage" type="button" level="SubdistrictLevel" paramLBLR="LB" value="Reset" param="Reset"
														onclick="backAllItemfromLBWardCoverage('localbodyCoverageDistrictList','wardCoverageDistrictList','D');" />
														<input class="bttn" id="btnEventCoverage" type="button" level="SubdistrictLevel" paramLBLR="LB" value="Part"  param="Part"
														onclick="addItemforLBWardCoverage('localbodyCoverageDistrictList','wardCoverageDistrictList','Part');" />
													</div>
													<div class="ms_selectable">
														<label><spring:message htmlEscape="true"  code="Label.CONTRIBUTDISTRICTLIST"/><c:if test="${wardForm.selLevel eq 'D'}"><span class="mandate">*</span></c:if></label>
														<select id="wardCoverageDistrictList"	name="wardCoverageDistrictList"  multiple="multiple" >
															<c:forEach var="obj" items="${wardCoverageDistrictList}" >
																				<option value="${obj.entityCode }">${obj.entityName}</option>
															</c:forEach>
														</select>
														<br/>
														<c:if test="${fn:contains(wardForm.URBAN_LEVEL, 'T')}">
														<div class="ms_get_buttons">
														<input class="bttn" type="button" value="<spring:message code="Button.GetSubdistrictList" text="Get Sub-District List" /> "  style="width:100%;"
																onclick="coverageDetail('wardCoverageDistrictList','localbodyCoverageSubdistrictList','wardCoverageSubdistrictList','T');"  />
														</div>
														</c:if>
														
													</div>
													<div class="clear"></div>
												</div>
												</li>
								   	 	 	</c:if>
								   	 	 	<c:if test="${fn:contains(wardForm.URBAN_LEVEL, 'T')}">
								   	 	 		<li id="subdistrictLevelDiv" >
												<div class="ms_container">
													<div class="ms_selectable">
														<label><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"/></label>
														<select id="localbodyCoverageSubdistrictList"	 multiple="multiple" >
															<c:forEach var="obj" items="${localbodyCoverageSubdistrictList}" >
																	<option value="${obj.entityCode }">${obj.entityName}</option>
															</c:forEach>
														</select>
													</div>
													<div class="ms_buttons">
														<input class="bttn" id="btnEventCoverage" type="button" level="DistrictLevel" paramLBLR="LB" value="Whole" param="Whole"
														onclick="addItemforLBWardCoverage('localbodyCoverageSubdistrictList','wardCoverageSubdistrictList','Full');" />
														<input class="bttn" id="btnEventCoverage" type="button" level="DistrictLevel" paramLBLR="LB" value="Back"  param="Back"
															onclick="backItemfromLBWardCoverage('localbodyCoverageSubdistrictList','wardCoverageSubdistrictList','T');" />
														<input class="bttn" id="btnEventCoverage" type="button" level="DistrictLevel" paramLBLR="LB" value="Reset" param="Reset"
														onclick="backAllItemfromLBWardCoverage('localbodyCoverageSubdistrictList','wardCoverageSubdistrictList','T');" />
														<input class="bttn" id="btnEventCoverage" type="button" level="DistrictLevel" paramLBLR="LB" value="Part"  param="Part"
														onclick="addItemforLBWardCoverage('localbodyCoverageSubdistrictList','wardCoverageSubdistrictList','Part');" />
													</div>
													<div class="ms_selection">
													<label><spring:message htmlEscape="true"  code="Label.CONTRIBUTSUBDISTRICTLIST"/><c:if test="${wardForm.selLevel eq 'T' or wardForm.selLevel eq 'I' }"><span class="mandate">*</span></c:if></label>
														
														<select id="wardCoverageSubdistrictList" name="wardCoverageSubdistrictList"  multiple="multiple" >
															<c:forEach var="obj" items="${wardCoverageSubdistrictList}" >
																				<option value="${obj.entityCode }">${obj.entityName}</option>
															</c:forEach>
														</select>
														<br/>
														<c:if test="${fn:contains(wardForm.URBAN_LEVEL, 'V') or fn:contains(wardForm.URBAN_LEVEL, 'T')}">
														<div class="ms_get_buttons">
														<input 	type="button"  class="bttn"  value="<spring:message code="Button.GetVillageList" text="Get Village List" />" 
																onclick="coverageDetail('wardCoverageSubdistrictList','localbodyCoverageVillageList','wardCoverageVillageList','V');"  style="width:100%;" />
														</div>
														</c:if>
													</div>
													<div class="clear"></div>
												</div>
												</li>
								   	 	 	</c:if>
								   	 	 	<c:if test="${fn:contains(wardForm.URBAN_LEVEL, 'V') or fn:contains(wardForm.URBAN_LEVEL, 'T')}">
								   	 	 	<li id="villageLevelDiv" >
												<div class="ms_container">
													<div class="ms_selectable">
														<label><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"/></label>
														<select id="localbodyCoverageVillageList"	 multiple="multiple" >
															<c:forEach var="obj" items="${localbodyCoverageVillageList}" >
																				<option value="${obj.entityCode }">${obj.entityName}</option>
															</c:forEach>
														</select>
													</div>
													<div class="ms_buttons">
														<input class="bttn" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB" value="Whole" param="Whole"
														onclick="addItemforLBWardCoverage('localbodyCoverageVillageList','wardCoverageVillageList','Full');" />
														<input class="bttn" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB" value="Back"  param="Back"
														onclick="backItemfromLBWardCoverage('localbodyCoverageVillageList','wardCoverageVillageList','V');" />
														<input class="bttn" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB" value="Reset" param="Reset"
														onclick="backAllItemfromLBWardCoverage('localbodyCoverageVillageList','wardCoverageVillageList','V');" />
														<input class="bttn" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB" value="Part"  param="Part"
														onclick="addItemforLBWardCoverage('localbodyCoverageVillageList','wardCoverageVillageList','Part');" />
														
													</div>
													<div class="ms_selection">
														<label><spring:message htmlEscape="true"  code="Label.CONTRIBUTVILLAGELIST"/><c:if test="${wardForm.selLevel eq 'V'}"><span class="mandate">*</span></c:if></label>
														<select id="wardCoverageVillageList" name="wardCoverageVillageList"	 multiple="multiple" >
															<c:forEach var="obj" items="${wardCoverageVillageList}" >
																				<option value="${obj.entityCode }">${obj.entityName}</option>
															</c:forEach>
														</select>
														<br/>
														
													</div>
													<div class="clear"></div>
												</div>
											</li>
											
								   	 	 	</c:if>
								   	 	
								   
								   </c:when>
								   <c:otherwise>
								   	<c:if test="${selLevel eq 'D'}">
											<li id="districtLevelDiv" >
												<div class="ms_container">
													<div class="ms_selectable">
														<label><spring:message htmlEscape="true" code="Label.AVAILDISTRICTLIST"/></label>
														<select id="localbodyCoverageDistrictList"	 multiple="multiple" >
															<c:forEach var="obj" items="${localbodyCoverageDistrictList}" >
																				<option value="${obj.entityCode }">${obj.entityName}</option>
															</c:forEach>
														</select>
													</div>
													<div class="ms_buttons">
														<input class="bttn" id="btnEventCoverage" type="button" level="SubdistrictLevel" paramLBLR="LB" value="Whole" param="Whole"
														onclick="addItemforLBWardCoverage('localbodyCoverageDistrictList','wardCoverageDistrictList','Full');" />
														<input class="bttn" id="btnEventCoverage" type="button" level="SubdistrictLevel" paramLBLR="LB" value="Back"  param="Back"
														onclick="backItemfromLBWardCoverage('localbodyCoverageDistrictList','wardCoverageDistrictList','D');" />
														<input class="bttn" id="btnEventCoverage" type="button" level="SubdistrictLevel" paramLBLR="LB" value="Reset" param="Reset"
														onclick="backAllItemfromLBWardCoverage('localbodyCoverageDistrictList','wardCoverageDistrictList','D');" />
														<input class="bttn" id="btnEventCoverage" type="button" level="SubdistrictLevel" paramLBLR="LB" value="Part"  param="Part"
														onclick="addItemforLBWardCoverage('localbodyCoverageDistrictList','wardCoverageDistrictList','Part');" />
													</div>
													<div class="ms_selectable">
														<label><spring:message htmlEscape="true"  code="Label.CONTRIBUTDISTRICTLIST"/><c:if test="${wardForm.selLevel eq 'D'}"><span class="mandate">*</span></c:if></label>
														<select id="wardCoverageDistrictList"	name="wardCoverageDistrictList"  multiple="multiple" >
															<c:forEach var="obj" items="${wardCoverageDistrictList}" >
																				<option value="${obj.entityCode }">${obj.entityName}</option>
															</c:forEach>
														</select>
														<br/>
														
														<input class="bttn" type="button" value="<spring:message code="Button.GetSubdistrictList" text="Get Sub-District List" /> "  style="width:100%;"
																onclick="coverageDetail('wardCoverageDistrictList','localbodyCoverageSubdistrictList','wardCoverageSubdistrictList','T');"  />
														
													</div>
													<div class="clear"></div>
												</div>
											</li>
										 </c:if>
										 
										 <c:if test="${selLevel eq 'D'|| selLevel eq 'I'}">
										 <li id="subdistrictLevelDiv" >
												<div class="ms_container">
													<div class="ms_selectable">
														<label><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"/></label>
														<select id="localbodyCoverageSubdistrictList"	 multiple="multiple" >
															<c:forEach var="obj" items="${localbodyCoverageSubdistrictList}" >
																	<option value="${obj.entityCode }">${obj.entityName}</option>
															</c:forEach>
														</select>
													</div>
													<div class="ms_buttons">
														<input class="bttn" id="btnEventCoverage" type="button" level="DistrictLevel" paramLBLR="LB" value="Whole" param="Whole"
														onclick="addItemforLBWardCoverage('localbodyCoverageSubdistrictList','wardCoverageSubdistrictList','Full');" />
														<input class="bttn" id="btnEventCoverage" type="button" level="DistrictLevel" paramLBLR="LB" value="Back"  param="Back"
															onclick="backItemfromLBWardCoverage('localbodyCoverageSubdistrictList','wardCoverageSubdistrictList','T');" />
														<input class="bttn" id="btnEventCoverage" type="button" level="DistrictLevel" paramLBLR="LB" value="Reset" param="Reset"
														onclick="backAllItemfromLBWardCoverage('localbodyCoverageSubdistrictList','wardCoverageSubdistrictList','T');" />
														<input class="bttn" id="btnEventCoverage" type="button" level="DistrictLevel" paramLBLR="LB" value="Part"  param="Part"
														onclick="addItemforLBWardCoverage('localbodyCoverageSubdistrictList','wardCoverageSubdistrictList','Part');" />
													</div>
													<div class="ms_selection">
													<label><spring:message htmlEscape="true"  code="Label.CONTRIBUTSUBDISTRICTLIST"/><c:if test="${wardForm.selLevel eq 'T' or wardForm.selLevel eq 'I' }"><span class="mandate">*</span></c:if></label>
														
														<select id="wardCoverageSubdistrictList" name="wardCoverageSubdistrictList"  multiple="multiple" >
															<c:forEach var="obj" items="${wardCoverageSubdistrictList}" >
																				<option value="${obj.entityCode }">${obj.entityName}</option>
															</c:forEach>
														</select>
														<br/>
														<div class="ms_get_buttons">
														<input 	type="button"  class="bttn"  value="<spring:message code="Button.GetVillageList" text="Get Village List" />" 
																onclick="coverageDetail('wardCoverageSubdistrictList','localbodyCoverageVillageList','wardCoverageVillageList','V');"  style="width:100%;" />
														</div>
														
													</div>
													<div class="clear"></div>
												</div>
											</li>
											</c:if>
											
											 <c:if test="${selLevel eq 'D'|| selLevel eq 'I' || selLevel eq 'V'}">
											<li id="villageLevelDiv" >
												<div class="ms_container">
													<div class="ms_selectable">
														<label><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"/></label>
														<select id="localbodyCoverageVillageList"	 multiple="multiple" >
															<c:forEach var="obj" items="${localbodyCoverageVillageList}" >
																				<option value="${obj.entityCode }">${obj.entityName}</option>
															</c:forEach>
														</select>
													</div>
													<div class="ms_buttons">
														<input class="bttn" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB" value="Whole" param="Whole"
														onclick="addItemforLBWardCoverage('localbodyCoverageVillageList','wardCoverageVillageList','Full');" />
														<input class="bttn" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB" value="Back"  param="Back"
														onclick="backItemfromLBWardCoverage('localbodyCoverageVillageList','wardCoverageVillageList','V');" />
														<input class="bttn" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB" value="Reset" param="Reset"
														onclick="backAllItemfromLBWardCoverage('localbodyCoverageVillageList','wardCoverageVillageList','V');" />
														<input class="bttn" id="btnEventCoverage" type="button" level="VillageLevel" paramLBLR="LB" value="Part"  param="Part"
														onclick="addItemforLBWardCoverage('localbodyCoverageVillageList','wardCoverageVillageList','Part');" />
														
													</div>
													<div class="ms_selection">
														<label><spring:message htmlEscape="true"  code="Label.CONTRIBUTVILLAGELIST"/><c:if test="${wardForm.selLevel eq 'V'}"><span class="mandate">*</span></c:if></label>
														<select id="wardCoverageVillageList" name="wardCoverageVillageList"	 multiple="multiple" >
															<c:forEach var="obj" items="${wardCoverageVillageList}" >
																				<option value="${obj.entityCode }">${obj.entityName}</option>
															</c:forEach>
														</select>
														<br/>
														
													</div>
													<div class="clear"></div>
												</div>
											</li>
											
										 </c:if>
								   
								   </c:otherwise>
								   </c:choose>
									
										 
										 <li>
												<label class="inline">&nbsp;</label>
											    <label>
												<input type="button" name="save" id="btnSave"  class="bttn"  value="<spring:message code='Button.SAVE' htmlEscape='true'></spring:message>"  onclick="validateSaveCoverageWard();"  />
												<input type="button" class="bttn" name="Back" value=<spring:message code="Button.Back" htmlEscape="true"></spring:message>  onclick="backMethod('wardCoverageForm','selectWardCoverage.htm');" />											
												<input type="button" class="bttn" name="Close" value=<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>	 onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'" /> 
												</label>
												
											
											</li>
										
								</ul>
					     </div>
					</div>
			
				</form:form>
			</div>
		</div>
   </div>
</body>
</html>



