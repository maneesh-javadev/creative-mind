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
				<label><spring:message htmlEscape="true" code="Label.INVALIDATEDISTRICT"></spring:message></label>
			</h3>
			<ul id="showhelp" class="listing">				
				<%--// these links are not working <li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /> </a> </li>
				<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>											
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>	 		
		
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="" id="form1" method="POST" commandName="">
				<div class="frmpnlbg">
					<div class="frmtxt">
						<table class="tbl_with_brdr" width="98%">
							<tr>
								<td colspan="4" class="tblRowTitle tblclear"><label><strong><spring:message
												htmlEscape="true" code="Label.INVALIDATEDDISTRICT"></spring:message>
									</strong> </label></td>
							</tr>
							<tr class="tblRowTitle tblclear">
								<td width="15%"><spring:message htmlEscape="true"
										code="Label.DISTRICTCODE"></spring:message></td>
								<td width="15%"><spring:message htmlEscape="true"
										code="Label.DISTRICTVERSION"></spring:message></td>
								<td width="35%"><spring:message htmlEscape="true"
					         code="Label.DISTRICTNAMEINENGLISH"></spring:message></td>
								<td width="35%"><spring:message htmlEscape="true"
										code="Label.DISTRICTNAMEINLOCAL"></spring:message></td>
							</tr>
							
							<c:forEach var="districtBean" varStatus="entityRow"
									items="${districtBean}">
							<tr class="tblRowB">
								<td width="15%"><c:out
										value="${districtBean.districtCode}" escapeXml="true"></c:out></td>
								<td width="15%"><c:out
										value="${districtBean.districtVersion}" escapeXml="true"></c:out></td>
								<td width="35%"><c:out
										value="${districtBean.districtNameEnglish}" escapeXml="true"></c:out></td>
								<td width="35%"><c:out
										value="${districtBean.districtNameLocal}" escapeXml="true"></c:out></td>
							</tr>
							</c:forEach>
						</table>
					</div>
				</div>

				<c:if test="${districtBeanMerge.size()>0}">
					<div class="frmpnlbg">
						<div class="frmtxt">

							<table class="tbl_with_brdr" width="98%">
								<tr class="tblRowTitle tblclear">
									<td width="15%" colspan="4"><spring:message
											htmlEscape="true" code="Label.SELECTTARDISTRICT"></spring:message>
									</td>

								</tr>
								
								
								<c:forEach var="entity" varStatus="entityRow"
									items="${districtBeanMerge}">
									<%-- <tr>
									<td>${entity.districtCode}</td>
									<td>${pvcode}</td>
									<td>${entityRow.count}</td>
									</tr> --%>
								
								<c:if test="${entity.districtCode!=pvcode || entityRow.count==1}" >
									
									<tr class="tblRowTitle tblclear">
								<td width="15%" ></td>
								</tr>
									<tr class="tblRowTitle tblclear">
									<td width="15%" rowspan="2"><spring:message
											htmlEscape="true" code="Label.DISTRICTCODE"></spring:message>
									</td>
									<td width="15%" rowspan="2"><spring:message
											htmlEscape="true" code="Label.DISTRICTVERSION"></spring:message>
									</td>
									<td width="15%" rowspan="2"><spring:message
											htmlEscape="true" code="Label.DISTRICTNAMEINENGLISH"></spring:message>
									</td>
									<td width="15%" rowspan="2"><spring:message
											htmlEscape="true" code="Label.DISTRICTNAMEINLOCAL"></spring:message>
									</td>
									
									</tr>
								
									<tr></tr>
									<tr class="tblRowB">
										<td width="15%"><c:out
												value="${entity.districtCode}" escapeXml="true"></c:out></td>
										<td width="15%"><c:out
												value="${entity.districtVersion+1}" escapeXml="true"></c:out></td>
										<td width="15%"><c:out
												value="${entity.districtNameEnglish}" escapeXml="true"></c:out></td>
										<td width="15%"><c:out
												value="${entity.districtNameInLcl}" escapeXml="true"></c:out></td>
										
										
										</tr>
										
										</c:if>
											<c:if test="${entity.districtCode!=pvcode}" >	
												<tr>
											<td><spring:message htmlEscape="true"
													code="Label.SUBDISTRICTCODE"></spring:message></td>
											<td><spring:message htmlEscape="true"
													code="Label.SUBDISTRICTVERSION"></spring:message></td>
											<td><spring:message htmlEscape="true"
													code="Label.SUBDISTRICTNAMEENGLISH"></spring:message></td>
											<td><spring:message htmlEscape="true"
													code="Label.SUBDISTRICTNAMELOCAL"></spring:message></td>
												</tr>
											</c:if>
											
													<tr class="tblRowB tblclear">
														<td width="20%"><c:out
																value="${entity.subdistrictCode}" escapeXml="true"></c:out></td>
																<td width="20%"><c:out
																value="${entity.subDistrictVersion}" escapeXml="true"></c:out></td>
														<td width="40%"><c:out
																value="${entity.subdistrictNameEnglish}" escapeXml="true"></c:out></td>
														<td width="40%"><c:out
																value="${entity.subdistrictNameLocal}" escapeXml="true"></c:out></td>
													</tr>
												
									<c:set var="pvcode"  value="${entity.districtCode}"/>	
									
								</c:forEach>
								
								<%-- <tr class="tblRowTitle tblclear">
									<td width="15%"><spring:message htmlEscape="true"
											code="Label.DISTRICTCODE"></spring:message></td>
									<td width="15%"><spring:message htmlEscape="true"
											code="Label.DISTVER"></spring:message></td>
									<td width="35%""><spring:message htmlEscape="true"
											code="Label.DISTRICTNAMEINENGLISH"></spring:message></td>
									<td width="35%"><spring:message htmlEscape="true"
											code="Label.DISTRICTNAMEINLOCAL"></spring:message></td>
								</tr>
								<tr class="tblRowB">
									<td width="15%"><c:out
											value="${districtBeanMerge.districtCode}"></c:out>
									</td>
									<td width="15%"><c:out
											value="${districtBeanMerge.districtVersion}"></c:out>
									</td>
									<td width="35%"><c:out
											value="${districtBeanMerge.districtNameEnglish}"></c:out></td>
									<td width="35%"><c:out
											value="${districtBeanMerge.districtNameLocal}"></c:out></td>
								</tr> --%>
							</table>

						</div>
					</div>
				</c:if>
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

