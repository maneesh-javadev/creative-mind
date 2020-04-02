<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% String contextPath = request.getContextPath(); %>
<head>
<%-- <script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script> --%>
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
<c:set var="part_value" value="_Part" />
<c:set var="full_value" value="_FULL" />
<%-- <link href="<%=contextPath%>/resources-localbody/css/localbody.css" rel="stylesheet" type="text/css" /> --%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

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
	<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                    <form:form   commandName="wardForm"  id="wardCoverageForm" action="saveWardCoverage.htm"  method="POST" class="form-horizontal">
				      <input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="saveWardCoverage.htm" />" />
				      <div id="errorMsg" class="errormsg">${errorMsg}</div>
				      <form:hidden path="wardCode" />
				      <form:hidden path="localBodyCode"  />
				       <form:hidden path="selLevel" value="${selLevel}" />
				    <%--   <c:choose>
				      	<c:when test="${isUrban}">
				      	  <form:hidden path="selLevel" />
				      	</c:when>
				    	<c:otherwise>
				    	<form:hidden path="selLevel" />
				    	</c:otherwise>
				    	</c:choose> --%>
				      <form:hidden path="PanchayatType" />
				      <form:hidden path="lblc" />
				      <form:hidden path="paramLocalBodyTypeCode" />
				      <form:hidden path="localBodyLevelCodes" />
				      <form:hidden path="URBAN_LEVEL" />
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.addnManageWardCoverage" text="Add n Manage Ward Coverage" /></h3>
                                </div>
                                 <div class="box-header subheading">
                                    <h4><spring:message code="Label.wardDetails" text="Ward Details" /></h4>
                                </div>
                        <div class="box-body">
                           
								<table class="table table-bordered table-hover">
									  <tr>
									      <td><spring:message code="Label.WARDNUMBER"/> </td><td><esapi:encodeForHTMLAttribute>${localbodyWard.wardNumber}</esapi:encodeForHTMLAttribute></td>
									  </tr>
									  <tr>
									      <td><spring:message code="Label.WARDNAMEINENG"/></td><td><esapi:encodeForHTMLAttribute>${localbodyWard.wardNameEnglish}</esapi:encodeForHTMLAttribute></td>
									  </tr>
									  <tr>
									    <td><spring:message code="Label.WARDNAMEINLOCAL"/></td> <td><esapi:encodeForHTMLAttribute>${localbodyWard.wardNameLocal}</esapi:encodeForHTMLAttribute></td>
									  </tr>
								</table>
						   
                  
             
              <div class="box-header subheading">
                 <h4><spring:message code="Label.MODIFYCOVERAGEWARD"  text="Modify Coverage of  Ward" /></h4></div>
               
               <c:choose>
			<c:when test="${PANCHAYAT_TYPE eq 'U'}">
			<c:if test="${fn:contains(wardForm.URBAN_LEVEL, 'D')}">
	             <div class="ms_container row" style="margin-left: 10px;">
	              <div class="ms_selectable col-sm-5 form-group">
	                 <label for="ddSourceBlock"><spring:message htmlEscape="true" code="Label.AVAILDISTRICTLIST"/></label>
	               <select id="localbodyCoverageDistrictList"	 multiple="multiple" class="form-control" > 
 	                  <c:forEach var="obj" items="${localbodyCoverageDistrictList}" >
						<c:choose>
								<c:when test="${fn:contains(obj.coverageType, 'P')}">
								<option value="${obj.entityCode}${part_value}">${obj.entityName}</option>
								</c:when>
								<c:otherwise>
								<option value="${obj.entityCode}">${obj.entityName}</option>
								</c:otherwise>
							 </c:choose>
						</c:forEach>
				  </select>
		        </div>
		 <div class="ms_buttons col-sm-2"></br>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage"  level="SubdistrictLevel" paramLBLR="LB" value="Whole" param="Whole" onclick="moveElement('localbodyCoverageDistrictList','wardCoverageDistrictList','Full');"">whole</button>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage"  level="SubdistrictLevel" paramLBLR="LB" value="Back"  param="Back" onclick="moveElement('wardCoverageDistrictList','localbodyCoverageDistrictList','BACK');">Back</button>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage"  level="SubdistrictLevel" paramLBLR="LB" value="Reset" param="Reset" onclick="moveElement('wardCoverageDistrictList','localbodyCoverageDistrictList','RESET');">Reset</button>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage" level="SubdistrictLevel" paramLBLR="LB" value="Part"  param="Part" onclick="moveElement('localbodyCoverageDistrictList','wardCoverageDistrictList','PART');">Part</button>
		 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message htmlEscape="true"  code="Label.CONTRIBUTDISTRICTLIST"/><span class="mandatory">*</span></label> 
			    <select id="wardCoverageDistrictList"	name="wardCoverageDistrictList"  multiple="multiple" class="form-control">
					<c:forEach var="obj" items="${wardCoverageDistrictList}" >
						<c:choose>
								<c:when test="${fn:contains(obj.coverageType, 'P')}">
								<option value="${obj.entityCode}${part_value}">${obj.entityName}</option>
								</c:when>
								<c:otherwise>
								<option value="${obj.entityCode}">${obj.entityName}</option>
								</c:otherwise>
							 </c:choose>
					</c:forEach>
					</select>	
			   <c:if test="${fn:contains(wardForm.URBAN_LEVEL, 'D') or fn:contains(wardForm.URBAN_LEVEL, 'T')}">
					  <button class="btn btn-primary" type="button" value="<spring:message code="Button.GetSubdistrictList" text="Get Sub-District List" /> "  onclick="coverageDetail('wardCoverageDistrictList','localbodyCoverageSubdistrictList','wardCoverageSubdistrictList','T');" ><spring:message code="Button.GetSubdistrictList" text="Get Sub-District List" /> </button>
				 </c:if>			
            </div>
           </div>
           </div>
         </c:if>
         
         <c:if test="${fn:contains(wardForm.URBAN_LEVEL, 'T') or fn:contains(wardForm.URBAN_LEVEL, 'D')}">
          <div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
	               <label for="ddSourceBlock"><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"/></label>
	              <select id="localbodyCoverageSubdistrictList"	 multiple="multiple" class="form-control">
						 <c:forEach var="obj" items="${localbodyCoverageSubdistrictList}" >
								<c:choose>
								<c:when test="${fn:contains(obj.coverageType, 'P')}">
								<option value="${obj.entityCode}${part_value}">${obj.entityName}</option>
								</c:when>
								<c:otherwise>
								<option value="${obj.entityCode}">${obj.entityName}</option>
								</c:otherwise>
							 </c:choose>
						 </c:forEach>
				 </select>
		        </div>
		 <div class="ms_buttons col-sm-2"><br>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage"  level="SubdistrictLevel" paramLBLR="LB" value="Whole" param="Whole" onclick="moveElement('localbodyCoverageSubdistrictList','wardCoverageSubdistrictList','Full');">whole</button>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage"  level="SubdistrictLevel" paramLBLR="LB" value="Back"  param="Back" onclick="moveElement('wardCoverageSubdistrictList','localbodyCoverageSubdistrictList','BACK');" >Back</button>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage"  level="SubdistrictLevel" paramLBLR="LB" value="Reset" param="Reset" onclick="moveElement('wardCoverageSubdistrictList','localbodyCoverageSubdistrictList','RESET');" >Reset</button>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage" level="SubdistrictLevel" paramLBLR="LB" value="Part"  param="Part" onclick="moveElement('localbodyCoverageSubdistrictList','wardCoverageSubdistrictList','PART');">Part</button>
		 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message htmlEscape="true"  code="Label.CONTRIBUTSUBDISTRICTLIST"/><c:if test="${wardForm.selLevel eq 'T' or wardForm.selLevel eq 'I' }"><span class="mandate">*</span></c:if></label> 
			    <select id="wardCoverageSubdistrictList" name="wardCoverageSubdistrictList"  multiple="multiple" class="form-control">
						<c:forEach var="obj" items="${wardCoverageSubdistrictList}" >
							<c:choose>
								<c:when test="${fn:contains(obj.coverageType, 'P')}">
								<option value="${obj.entityCode}${part_value}">${obj.entityName}</option>
								</c:when>
								<c:otherwise>
								<option value="${obj.entityCode}">${obj.entityName}</option>
								</c:otherwise>
							 </c:choose>
						</c:forEach>
							
				</select>	
			  <c:if test="${fn:contains(wardForm.URBAN_LEVEL, 'D') or fn:contains(wardForm.URBAN_LEVEL, 'V') or fn:contains(wardForm.URBAN_LEVEL, 'T')}">
				
				   <button 	type="button"  class="btn btn-primary"  value="<spring:message code="Button.GetVillageList" text="Get Village List" />" style="width:100%;" onclick="coverageDetail('wardCoverageSubdistrictList','localbodyCoverageVillageList','wardCoverageVillageList','V');"><spring:message code="Button.GetVillageList" text="Get Village List" /></button>
				
			 </c:if>			
		     </div>				
            </div>
         </div>
         </c:if>
         
       <c:if test="${fn:contains(wardForm.URBAN_LEVEL, 'D') or fn:contains(wardForm.URBAN_LEVEL, 'V') or fn:contains(wardForm.URBAN_LEVEL, 'T')}">
        <div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
	               <label for="ddSourceBlock"><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"/></label>
	              <select id="localbodyCoverageVillageList"	 multiple="multiple" class="form-control">
						<c:forEach var="obj" items="${localbodyCoverageVillageList}" >
							<c:choose>
								<c:when test="${fn:contains(obj.coverageType, 'P')}">
								<option value="${obj.entityCode}${part_value}">${obj.entityName}</option>
								</c:when>
								<c:otherwise>
								<option value="${obj.entityCode}">${obj.entityName}</option>
								</c:otherwise>
							 </c:choose>
						</c:forEach>
				   </select>
		        </div>
		 <div class="ms_buttons col-sm-2"><br>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage"  level="SubdistrictLevel" paramLBLR="LB" value="Whole" param="Whole" onclick="moveElement('localbodyCoverageVillageList','wardCoverageVillageList','Full');" >whole</button>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage"  level="SubdistrictLevel" paramLBLR="LB" value="Back"  param="Back" onclick="moveElement('wardCoverageVillageList','localbodyCoverageVillageList','BACK');" >Back</button>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage"  level="SubdistrictLevel" paramLBLR="LB" value="Reset" param="Reset" onclick="moveElement('wardCoverageVillageList','localbodyCoverageVillageList','RESET');" >Reset</button>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage" level="SubdistrictLevel" paramLBLR="LB" value="Part"  param="Part" onclick="moveElement('localbodyCoverageVillageList','wardCoverageVillageList','PART');">Part</button>
		 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message htmlEscape="true"  code="Label.CONTRIBUTVILLAGELIST"/><c:if test="${wardForm.selLevel eq 'V'}"><span class="mandate">*</span></c:if></label> 
			    <select id="wardCoverageVillageList" name="wardCoverageVillageList"	 multiple="multiple" class="form-control" >
					<c:forEach var="obj" items="${wardCoverageVillageList}" >
				
							<c:choose>
								<c:when test="${fn:contains(obj.coverageType, 'P')}">
								<option value="${obj.entityCode}${part_value}">${obj.entityName}</option>
								</c:when>
								<c:otherwise>
								<option value="${obj.entityCode}">${obj.entityName}</option>
								</c:otherwise>
							 </c:choose>
						</c:forEach>
				</select>	
		     </div>				
            </div>
         </div>
       </c:if>  
         </c:when>
         
           <c:otherwise>
		    <c:if test="${selLevel eq 'D'}">
		    <div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
	               <label for="ddSourceBlock"><spring:message htmlEscape="true" code="Label.AVAILDISTRICTLIST"/></label>
	              <select id="localbodyCoverageDistrictList"	 multiple="multiple" class="form-control">
	              
	               <c:forEach var="obj" items="${localbodyCoverageDistrictList}" >
					   <c:choose>
								<c:when test="${fn:contains(obj.coverageType, 'P')}">
								<option value="${obj.entityCode}${part_value}">${obj.entityName}</option>
								</c:when>
								<c:otherwise>
								<option value="${obj.entityCode}">${obj.entityName}</option>
								</c:otherwise>
							 </c:choose>
				     </c:forEach>
					
				</select>
		        </div>
		 <div class="ms_buttons col-sm-2"><br/>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage"  level="SubdistrictLevel" paramLBLR="LB" value="Whole" param="Whole" onclick="moveElement('localbodyCoverageDistrictList','wardCoverageDistrictList','Full');"" >whole</button>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage"  level="SubdistrictLevel" paramLBLR="LB" value="Back"  param="Back" onclick="moveElement('wardCoverageDistrictList','localbodyCoverageDistrictList','BACK');" >Back</button>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage"  level="SubdistrictLevel" paramLBLR="LB" value="Reset" param="Reset" onclick="moveElement('wardCoverageDistrictList','localbodyCoverageDistrictList','RESET');" >Reset</button>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage" level="SubdistrictLevel" paramLBLR="LB" value="Part"  param="Part" onclick="moveElement('localbodyCoverageDistrictList','wardCoverageDistrictList','PART');">Part</button>
		 </div>
			<div class="ms_selection col-sm-5">
				<div class="form-group">
				    <label for="ddDestBlock"><spring:message htmlEscape="true"  code="Label.CONTRIBUTDISTRICTLIST"/><c:if test="${wardForm.selLevel eq 'D'}"><span class="mandate">*</span></c:if></label> 
				    <select id="wardCoverageDistrictList" name="wardCoverageDistrictList"	 multiple="multiple" class="form-control" >
						<c:forEach var="obj" items="${wardCoverageDistrictList}" >
							<c:choose>
								<c:when test="${fn:contains(obj.coverageType, 'P')}">
								<option value="${obj.entityCode}${part_value}">${obj.entityName}</option>
								</c:when>
								<c:otherwise>
								<option value="${obj.entityCode}">${obj.entityName}</option>
								</c:otherwise>
							 </c:choose>
							</c:forEach>
					</select>	
					<button class="btn btn-primary" type="button" value="<spring:message code="Button.GetSubdistrictList" text="Get Sub-District List" /> " style="width:100%;"  onclick="coverageDetail('wardCoverageDistrictList','localbodyCoverageSubdistrictList','wardCoverageSubdistrictList','T');">
					<spring:message code="Button.GetSubdistrictList" text="Get Sub-District List"> </spring:message></button>
            	</div>
            </div>
         </div>
   </c:if>
    <c:if test="${selLevel eq 'D'|| selLevel eq 'I'}">
       <div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
	               <label for="ddSourceBlock"><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"/></label>
	              <select id="localbodyCoverageSubdistrictList"	 multiple="multiple" class="form-control" >
						<c:forEach var="obj" items="${localbodyCoverageSubdistrictList}" >
							<c:choose>
								<c:when test="${fn:contains(obj.coverageType, 'P')}">
								<option value="${obj.entityCode}${part_value}">${obj.entityName}</option>
								</c:when>
								<c:otherwise>
								<option value="${obj.entityCode}">${obj.entityName}</option>
								</c:otherwise>
							 </c:choose>
						</c:forEach>
				  </select>
		        </div>
		 <div class="ms_buttons col-sm-2"><br>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage"  level="SubdistrictLevel" paramLBLR="LB" value="Whole" param="Whole" onclick="moveElement('localbodyCoverageSubdistrictList','wardCoverageSubdistrictList','Full');">whole</button>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage"  level="SubdistrictLevel" paramLBLR="LB" value="Back"  param="Back" onclick="moveElement('wardCoverageSubdistrictList','localbodyCoverageSubdistrictList','BACK');"  >Back</button>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage"  level="SubdistrictLevel" paramLBLR="LB" value="Reset" param="Reset" onclick="moveElement('wardCoverageSubdistrictList','localbodyCoverageSubdistrictList','RESET');" >Reset</button>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage" level="SubdistrictLevel" paramLBLR="LB" value="Part"  param="Part" onclick="moveElement('localbodyCoverageSubdistrictList','wardCoverageSubdistrictList','PART');">PART</button>
		 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message htmlEscape="true"  code="Label.CONTRIBUTSUBDISTRICTLIST"/><c:if test="${wardForm.selLevel eq 'T' or wardForm.selLevel eq 'I' }"><span class="mandate">*</span></c:if>  </label> 
			    <select id="wardCoverageSubdistrictList" name="wardCoverageSubdistrictList"  multiple="multiple" class="form-control">
					<c:forEach var="obj" items="${wardCoverageSubdistrictList}" >
						<c:choose>
								<c:when test="${fn:contains(obj.coverageType, 'P')}">
								<option value="${obj.entityCode}${part_value}">${obj.entityName}</option>
								</c:when>
								<c:otherwise>
								<option value="${obj.entityCode}">${obj.entityName}</option>
								</c:otherwise>
							 </c:choose>
					</c:forEach>
				</select>
				<button type="button"  class="btn btn-primary"  value="<spring:message code="Button.GetVillageList" text="Get Village List" />" style="width: 100%" onclick="coverageDetail('wardCoverageSubdistrictList','localbodyCoverageVillageList','wardCoverageVillageList','V');"><spring:message code="Button.GetVillageList" text="Get Village List" /></button>
		     </div>				
            </div>
         </div>
    </c:if>
   
   
    <c:if test="${selLevel eq 'D'|| selLevel eq 'I' || selLevel eq 'V'}">
    <div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
	               <label for="ddSourceBlock"><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"/></label>
	              <select id="localbodyCoverageVillageList"	 multiple="multiple" class="form-control">
					<c:forEach var="obj" items="${localbodyCoverageVillageList}" >
						<c:choose>
								<c:when test="${fn:contains(obj.coverageType, 'P')}">
								<option value="${obj.entityCode}${part_value}">${obj.entityName}</option>
								</c:when>
								<c:otherwise>
								<option value="${obj.entityCode}">${obj.entityName}</option>
								</c:otherwise>
							 </c:choose>
					</c:forEach>
				</select>
		        </div>
		 <div class="ms_buttons col-sm-2"><br>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage"  level="SubdistrictLevel" paramLBLR="LB" value="Whole" param="Whole" onclick="moveElement('localbodyCoverageVillageList','wardCoverageVillageList','Full');" >whole</button>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage"  level="SubdistrictLevel" paramLBLR="LB" value="Back"  param="Back" onclick="moveElement('wardCoverageVillageList','localbodyCoverageVillageList','BACK');"  >Back</button>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage"  level="SubdistrictLevel" paramLBLR="LB" value="Reset" param="Reset" onclick="moveElement('wardCoverageVillageList','localbodyCoverageVillageList','RESET');" >Reset</button>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage" level="SubdistrictLevel" paramLBLR="LB" value="Part"  param="Part" onclick="moveElement('localbodyCoverageVillageList','wardCoverageVillageList','PART');">Part</button>
		 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message htmlEscape="true"  code="Label.CONTRIBUTVILLAGELIST"/><c:if test="${wardForm.selLevel eq 'V'}"><span class="mandate">*</span></c:if></label> 
			    <select id="wardCoverageVillageList" name="wardCoverageVillageList"	 multiple="multiple" class="form-control" >
						<c:forEach var="obj" items="${wardCoverageVillageList}" >
							<c:choose>
								<c:when test="${fn:contains(obj.coverageType, 'P')}">
								<option value="${obj.entityCode}${part_value}">${obj.entityName}</option>
								</c:when>
								<c:otherwise>
								<option value="${obj.entityCode}">${obj.entityName}</option>
								</c:otherwise>
							 </c:choose>
						</c:forEach>
				</select>
				
		     </div>				
            </div>
         </div>
    </c:if>
   
    </c:otherwise>
	</c:choose>
   </div>
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                   <button type="button" class="btn btn-success " name="save"  id="btnsave" onclick="validateSaveCoverageWard();"  value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" ><i class="fa fa-floppy-o"></i> Save in Draft</button>
                  <%--  <button type="submit" class="btn btn-default " name="Submit6" value="<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message>"  onclick="backMethod('wardCoverageForm','selectWardCoverage.htm');"> Clear</button> --%>
                   <button type="button" class="btn btn-danger " name="Cancel value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div> 
     </div>   
             
  </form:form>
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>



