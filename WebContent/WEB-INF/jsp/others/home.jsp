<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Properties" pageEncoding="utf-8" errorPage="true"%> 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


</head>
<body>

<c:if  test="${!empty profile_msg}" >
<div style="text-align: center;" class="errormsg"><c:out value="${profile_msg}" escapeXml="true"></c:out></div>
</c:if>



<c:if  test="${!empty addGeneralElectionDetailSuccess}" >
<div style="text-align: center;" class="txtlink"><c:out value="${addGeneralElectionDetailSuccess}" escapeXml="true"></c:out></div>
</c:if>


<c:if  test="${!empty addByElectionDetailSuccess}" >
<div style="text-align: center;" class="txtlink"><c:out value="${addByElectionDetailSuccess}" escapeXml="true"></c:out></div>
</c:if>



<c:if  test="${!empty addGeneralElectionError}" >
<div style="text-align: center;" class="errormsg"><c:out value="${addGeneralElectionError}" escapeXml="true"></c:out></div>
</c:if>


<c:if  test="${!empty byElectionError}" >
<div style="text-align: center;" class="errormsg"><c:out value="${byElectionError}" escapeXml="true"></c:out></div>
</c:if>



<c:if  test="${!empty GeneralElectionError}" >
<div style="text-align: center;" class="errormsg"><c:out value="${GeneralElectionError}" escapeXml="true"></c:out></div>
</c:if>
<center><spring:message htmlEscape="true"  code="Label.Welcome"/></center>



</body>
</html>