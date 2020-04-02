<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>
	
</script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script src="js/shiftvillage.js"></script>
</head>
<body>
	<div id="frmcontent">
	
	
		<div class="frmhd">
			<h3 class="subtitle">
				<label><spring:message htmlEscape="true" code="Label.InvalidatedLBDetails"></spring:message> </label>
			</h3>
			<ul id="showhelp" class="listing">	
				<li>
					<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /> </a>
				</li>											
			<%--//this link is not working 	<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>	
		
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="" id="form1" method="POST" commandName="">
				<div class="frmpnlbg">
					<div class="frmtxt">
							<div class="align_center">
								<label><h2><spring:message htmlEscape="true" code="Label.lbInvalidated"></spring:message></h2> </label>
							</div>						
						<br/>
						<table class="tbl_with_brdr" width="98%">						
							<tr class="tblRowTitle tblclear">
								<td width="15%"<spring:message htmlEscape="true"
										code="Label.INVALIDATELOCALGOVBODY"></spring:message> Code</td>
								<td width="15%"><spring:message htmlEscape="true"
										code="Label.LOCALBODYVERSION"></spring:message></td>
								<td width="35%"><spring:message htmlEscape="true"
					         code="Label.NAMEOFLOCALBODY"></spring:message></td>
								<td width="35%"><spring:message htmlEscape="true"
										code="Label.NAMEINLOCALLANGUAGE"></spring:message></td>
							</tr>														
							<tr class="tblRowB">
								<td width="15%"><c:out
										value="${lbdelete.localBodyCode}" escapeXml="true"></c:out></td>
								<td width="15%"><c:out
										value="${lbdelete.localBodyVersion+1}" escapeXml="true"></c:out></td>
								<td width="35%"><c:out
										value="${lbdelete.localBodyNameEnglish}" escapeXml="true"></c:out></td>
								<td width="35%"><c:out
										value="${lbdelete.localBodyNameLocal}" escapeXml="true"></c:out></td>
							</tr>
						
						</table>
					</div>
				</div>

				<div class="btnpnl">
					 <label> 
					 		<input type="button" name="close" class="btn" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'" />
					</label>							
				</div>

			</form:form>
		</div>
	</div>
</body>
</html>

