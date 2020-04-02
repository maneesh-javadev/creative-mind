<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="../common/taglib_includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
</script>
</head>
<body>
	<div class="clear"></div>
		
	<div class="frmhd">
			
			<h3 class="subtitle"><label><spring:message htmlEscape="true" code="Label.CONFIGUREMAP"></spring:message></label></h3>
			 <ul id="showhelp" class="listing">
			        <li>
			         <a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /> </a>           
			        </li>
			  <%--   //this link is not working<li>
			       <a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
			</li> --%>
			    <%--//these links are not working   <li>
			        <a href="#" class="frmhelp"><spring:message	code="Button.HELP" htmlEscape="true"></spring:message> </a>
			      </li>					 				      --%>
		        </ul>					
		</div>
		<div class="clear"></div>
	
	
	
		
	<!-- </div> -->
	<div class="frmpnlbrdr">
		<form:form action="modifyLandRegion.htm" method="POST"
			commandName="configureMapForm">
			<input type="hidden" name="<csrf:token-name/>"
				value="<csrf:token-value uri="modifyLandRegion.htm"/>" />
			<div class="frmpnlbg">
				<div class="frmtxt">
					<div class="frmhdtitle">
						<spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message>
					</div>
					<table width="80%" class="tbl_with_brdr" align="center">
						<tr>
							<td class="lblBold"><spring:message htmlEscape="true" code="Label.LOCALGOVTBODYNAME" ></spring:message></td>
							<td class="lblBold"><spring:message htmlEscape="true" code="Label.uploadnseparate" ></spring:message></td>
							<td class="lblBold"><spring:message htmlEscape="true" code="Label.BASEURL" ></spring:message></td>
						</tr>
						<tr>
						
						</tr>
						<!-- <tr><td>&nbsp;</td></tr> -->
						<c:forEach var="viewConfigMapLandRegion"
							varStatus="administratorUnitRow"
							items="${configureMapForm.viewConfigMapLandRegion}">
							<tr class="tblRowB">
								<c:if test="${fn:containsIgnoreCase(viewConfigMapLandRegion.landregiontype,'S')}">
									<td><label> <spring:message htmlEscape="true"  code="Label.STATE"></spring:message>
									</label></td>
									<c:choose>
										<c:when test="${viewConfigMapLandRegion.ismapupload==true}">
											<td><label> <spring:message htmlEscape="true"  code="Label.UPLOADMAP"></spring:message>
											</label></td>
											<td align="center">-</td>
										</c:when>
										<c:otherwise>
											<td><label> <spring:message htmlEscape="true" 
														code="Label.SEPERATEMAPSERVER"></spring:message>
											</label></td>
											<td><c:out value="${viewConfigMapLandRegion.isbaseUrl}" escapeXml="true"></c:out>
											</td>
										</c:otherwise>
									</c:choose>
								</c:if>
								<c:if test="${fn:containsIgnoreCase(viewConfigMapLandRegion.landregiontype,'D')}">
									<td><label> <spring:message htmlEscape="true"  code="Label.DISTRICT"></spring:message>
									</label></td>
									<c:choose>
										<c:when test="${viewConfigMapLandRegion.ismapupload==true}">
											<td><label> <spring:message htmlEscape="true"  code="Label.UPLOADMAP"></spring:message>
											</label></td>
											<td align="center">-</td>
										</c:when>
										<c:otherwise>
											<td><label> <spring:message htmlEscape="true" 
														code="Label.SEPERATEMAPSERVER"></spring:message>
											</label></td>

											<td><c:out value="${viewConfigMapLandRegion.isbaseUrl}" escapeXml="true"></c:out>&nbsp;</td>
										</c:otherwise>
									</c:choose>
								</c:if>
								<c:if test="${fn:containsIgnoreCase(viewConfigMapLandRegion.landregiontype,'T')}">
									<td><label> <spring:message htmlEscape="true"  code="Label.SUBDISTRICT"></spring:message>
									</label></td>
									<c:choose>
										<c:when test="${viewConfigMapLandRegion.ismapupload==true}">
											<td><label> <spring:message htmlEscape="true"  code="Label.UPLOADMAP"></spring:message>
											</label></td>
											<td align="center">-</td>
										</c:when>
										<c:otherwise>
											<td><label> <spring:message htmlEscape="true" 
														code="Label.SEPERATEMAPSERVER"></spring:message>
											</label></td>

											<td><c:out value="${viewConfigMapLandRegion.isbaseUrl}" escapeXml="true"></c:out>
											</td>
										</c:otherwise>
									</c:choose>
								</c:if>
								<c:if test="${fn:containsIgnoreCase(viewConfigMapLandRegion.landregiontype,'V')}">
									<td><label> <spring:message htmlEscape="true"  code="Label.VILLAGE"></spring:message>
									</label></td>
									<c:choose>
										<c:when test="${viewConfigMapLandRegion.ismapupload==true}">
											<td><label> <spring:message htmlEscape="true"  code="Label.UPLOADMAP"></spring:message>
											</label></td>
											<td align="center">-</td>
										</c:when>
										<c:otherwise>
											<td><label> <spring:message htmlEscape="true" 
														code="Label.SEPERATEMAPSERVER"></spring:message>
											</label></td>
											<td><c:out value="${viewConfigMapLandRegion.isbaseUrl}" escapeXml="true"></c:out>
											</td>
										</c:otherwise>
									</c:choose>
								</c:if>

							</tr>
						</c:forEach>

					</table>


					<div class="errormsg"></div>
				</div>
			</div>
			<div class="btnpnl">
				<label> <input type="submit" class="btn" name="Submit"
					value="<spring:message htmlEscape="true"  code="Button.Modify"></spring:message>" />
				</label> <label> <input type="button" class="btn" name="Submit6"
					value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
					onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
			</div>
		</form:form>
		<script src="/LGD/JavaScriptServlet"></script>
	</div>
</body>
</html>