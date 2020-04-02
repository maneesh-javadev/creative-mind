<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<%@include file="../common/taglib_includes.jsp"%>

<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/common.js"></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrBlockService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/js/administrative-department.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/js/admin-department-validation.js'></script>

<%@include file="../common/dwr.jsp"%>
<script type="text/javascript">
	dwr.engine.setActiveReverseAjax(true);
	var _DISTRICT_LEVEL = '<%=ApplicationConstant.DISTRICT_LEVEL_CODE%>';
	var _SUBDISTRICT_LEVEL = '<%=ApplicationConstant.SUBDISTRICT_LEVEL_CODE%>';
	var _BLOCK_LEVEL = '<%=ApplicationConstant.BLOCK_LEVEL_CODE%>';
	var _VILLAGE_LEVEL = '<%=ApplicationConstant.VILLAGE_LEVEL_CODE%>';
	var _STATE_LEVEL = '<%=ApplicationConstant.STATE_LEVEL_CODE%>';
	var _ADMINISTRATIVE_LEVEL = '<%=ApplicationConstant.ADMINISTRATIVE_LEVEL_CODE%>';
	
	
	var accessLevel = '<c:out value="${pageAccessLevel}" escapeXml="true"></c:out>';
	var stateLevelId = '<c:out value="${stateCode}" escapeXml="true"></c:out>';
	var isOrganizationFlow = isParseJson('<c:out value="${isOrganizationFlow}" escapeXml="true"></c:out>');
	var isCenterUser = isParseJson('<c:out value="${isCenterUser}" escapeXml="true"></c:out>');
	var _continue_url = "continueCreateAdminDepartmentProcess.htm?<csrf:token uri='continueCreateAdminDepartmentProcess.htm'/>";
	var _save_url = "saveCreatedAdminDepartmentProcess.htm?<csrf:token uri='saveCreatedAdminDepartmentProcess.htm'/>";	
	$( document ).ready(function() {
		initialShowHide();
		registerOnclickMethods();
		
		
	});
</script>
</head>
<body>


</body>
</html>