<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="in.nic.pes.common.menu.pojo.UserSelection"%>
<%@page import="java.util.Properties" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%
UserSelection userSelection=(UserSelection) session.getAttribute("USERS_SELECTION");

%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>

</head>
<body>
	<table width="100%" class="tblbgclr tbl_no_brdr">
		<tr>
			<td id="span_1_0" class="tblclear coltab">
			<div class="collapse_menu"></div>
			</td>
			<td class="tblclear resize">

	<div id="panel" class="pnlsize">
		<div class="arrowlistmenu">
		
		
		
<c:forEach items="${USERS_SELECTION.menuProfile}" var="itm"> 
<c:choose>
<c:when test="${itm.parent eq 0}">
           <h3 class="menuheader expandable">${itm.resourceId}</h3>
            <ul class="categoryitems">
           <c:forEach items="${USERS_SELECTION.menuProfile}" var="itm2" varStatus="status"> 
           <c:choose>
           
           <c:when test="${(itm2.formName  eq null and itm.groupId eq itm2.groupId)and itm2.parent gt 0 and itm.appid eq itm2.appid}">
           <li class="myclass">${itm2.resourceId}</li>
           </c:when>
           
           <c:when test="${itm2.formName  ne null and itm.groupId eq itm2.groupId and itm.appid eq itm2.appid}">
			<li ><a href="<%=getServletContext().getInitParameter("URL")%>/${itm2.formName}?<csrf:token uri='${itm2.formName}'/>">${itm2.resourceId}</a></li>
			</c:when> 
         
           
         <%--    <c:when test="${itm2.formName  ne null and itm.groupId eq itm2.groupId and itm2.formName.contains('?') }">
			<li ><a href='${itm2.formName}&<csrf:token uri='${itm2.formName}'/>'>${itm2.resourceId}</a></li>
			</c:when> --%>
			
         <%--   <c:when test="${itm2.formName ne null and itm.groupId eq itm2.groupId and ! itm2.formName.contains('?')}">
			<li ><a href='${itm2.formName}?<csrf:token uri='${itm2.formName}'/>'>${itm2.resourceId}</a></li>
			</c:when> --%>
           
           
<%--            <c:when test="${itm2.formName  ne null and itm.groupId eq itm2.groupId }">
			<li ><a href="${itm2.formName}?<csrf:token uri="${itm2.formName}"/>">${itm2.resourceId}</a></li>
			</c:when> --%>
		
           </c:choose>
           
           </c:forEach>
           
           </ul>
</c:when>

</c:choose>

</c:forEach>
<ul> <li > <a href="http://localhost:8080/LGD/viewResolveEntitiesinDisturbedState.htm?type=P&<csrf:token uri='viewResolveEntitiesinDisturbedState.htm'/>">P</a> </li></ul>
<ul> <li > <a href="http://localhost:8080/LGD/viewResolveEntitiesinDisturbedState.htm?type=T&<csrf:token uri='viewResolveEntitiesinDisturbedState.htm'/>">T</a> </li></ul>
<ul> <li > <a href="http://localhost:8080/LGD/viewResolveEntitiesinDisturbedState.htm?type=U&<csrf:token uri='viewResolveEntitiesinDisturbedState.htm'/>">U</a> </li></ul>
<ul> <li > <a href="http://localhost:8080/LGD/viewResolveEntitiesinDisturbedState.htm?type=L&<csrf:token uri='viewResolveEntitiesinDisturbedState.htm'/>">L</a> </li></ul>
<%
//}
%>	
		</div>
	 <div class="clear"></div>
					<div id="newsupdate">
						<div class="pnlhd">News Update</div>
						<div class="clear"></div>
						<div class="scroltxt">
							<marquee direction="up" scrollamount="2" height="125px">
							It is a long established fact that a reader will be distracted by the readable content of a page when...
							<img src="images/activflag.png" width="17" height="12" border="0" /><br /><br />
							It is a long established fact that a reader will be distracted by the readable content of a page when...
							<img src="images/normal_flag.png" width="17" height="12" border="0" />
							</marquee>
							<!-- <div class="more">
								<a href="#"></a>
							</div> -->
						</div>
					</div>
 
	</div>	
	
	</td>
			<td valign="top" class="tblclear" id="span_2"><div class="extab">
					<img id="collapseImg" height="28" width="10" border="0"
						align="left" alt="" src="images/collapse.jpg"
						onclick="hideWrraper();" />
				</div>
			</td>
		</tr>
	</table>
</body>
</html>
