<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%-- <%@include file="../common/taglib_includes.jsp"%> --%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf"%>
<% String contextpthval = request.getContextPath(); %>
<head>
<link rel="stylesheet" href="https://js.arcgis.com/3.22/dijit/themes/claro/claro.css">
  <link rel="stylesheet" href="https://js.arcgis.com/3.20/esri/css/esri.css">
  <script src="https://js.arcgis.com/3.20/"></script>
<script type="text/javascript" src="<%=contextpthval%>/js/GISComponent.js"></script>
<script type="text/javascript" src="<%=contextpthval %>/resource/common-resource/jquery-2.2.4.min.js"></script> 
	
<style type="text/css">

html, body, #map {
	height: 100%;
	width: 100%;
	margin: 0;
	padding: 0;
	margin: 0;
	overflow: hidden;
	font-family: Verdana;
	font-size: 12px;
}

#map_root{
height: 650px !important;
}

.container {
	height: 100%;
}
	.mapErrorMsg{color:red;text-align:center;}
</style>
<script type="text/javascript">
var cPath = "<%=contextpthval%>";
var parentbaseURL = '${parentbaseURL}';
//alert(parentbaseURL);
var baseURL = '${baseURL}';  
//alert(baseURL);
var paramName ='${paramName}'; 
var attributeName = '${attributeName}'; 
var token='${token}'; 
var attributeCodeName='${attributeCodeName}';


displayLoadingImage = function() {
	 $.blockUI({ 
			theme:true,
			/* title: 'Loading...', */
			message: "<div style='text-align: center'><img  src='<%= request.getContextPath() %>/resource/dashboard-resource/dist/img/loader-lgd.gif'/></div>"
	    }); 
  
};

showLoadingImage = function() {
	 $.blockUI({ 
		theme:true,
		/* title: 'Loading...', */
		message: "<div style='text-align: center'><img  src='<%= request.getContextPath() %>/resource/dashboard-resource/dist/img/loader-lgd.gif'/></div>"
   }); 
};

hideLoadingImage = function(){
	 $.unblockUI(); 
	
};
</script>
</head>
<body class="claro">
	<form:form id="lGRuralSetupHierarchyForm" name="lGRuralSetupHierarchyForm" method="post" >
        <input type="hidden" id="lvCode" name="lvCode"/>
        <input type="hidden" id="inParam" name="inParam" value="${inParam}"/>
        <input type="hidden" id="localGovBodyType" name="localGovBodyType" />
        <input type="hidden" id="vpFlag" name="vpFlag"/>
        <input type="hidden" id="stateCode" name="stateCode"/>
        <input type="hidden" id="parentObject" name="parentObject" />
         <input type="hidden" id="subDistCensus2011Code" name="subDistCensus2011Code" value="${subDistCensus2011Code}"/>
        <div id="map" ></div>  
    	    
	</form:form>
	<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
	<div id="displayBox" style="text-align: center;position:absolute;display:none;"><img  src="<%= request.getContextPath() %>/images/ajax-loader-2.gif"  /></div>
</body>
</html>
