<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
 
		</script>
</head>
<body >	 
	          <div id="frmcontent">
				<table width="100%" class="tbl_no_brdr">
					<tr>
						<td width="100%" valign="top" class="tblclear">
								
									<div class="frmhd">
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td><spring:message htmlEscape="true"  code="Label.ViewOrganizationCentral"></spring:message>
												</td>
												<%--//this link is not working  <td align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message> </a> --%>
												</td>
											</tr>
										</table>
									</div>
									<div class="frmpnlbrdr">
										<form:form action="viewMinistryAction.htm" method="POST" commandName="viewOrganizationCenterDetail">
										<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewMinistryAction.htm"/>" />
											<div class="frmpnlbg">
												<div class="frmtxt">
												
												
													<div >
														<ul class="blocklist">
																<c:if test="${! empty listMinistry}">
																	
																		<c:forEach var="listMinistry" items="${listMinistry}">	
																		
																			<li>
																					<spring:message htmlEscape="true"  code="Label.ORGNAMEINENG" ></spring:message><br />
																					<label class="lblPlain"> <c:out value="${listMinistry.orgName}" escapeXml="true"></c:out></label>						  
																					<div class="errormsg"></div>
																			</li>
																			<li>
																					<spring:message htmlEscape="true" code="Label.ORGNAMEINLOCAL"></spring:message><br />
																					<label class="lblPlain"> <c:out value="${listMinistry.orgNameLocal}" escapeXml="true"></c:out></label>
																					<div class="errormsg"></div>
																			
																			</li>
																			<li>
																				<spring:message htmlEscape="true"  code="Label.ORGSHORTNAMEINENG"></spring:message><br />
																				<label class="lblPlain"> <c:out value="${listMinistry.shortName}" escapeXml="true"></c:out></label>						  
																				<div class="errormsg"></div>
																			
																			</li>
																			<li></li>
																		
																		</c:forEach>
																</c:if>
																
														
														</ul>
													
													</div>
												
														<div class="clear"></div>
												</div>

												<div class="btnpnl">
													<table width="100%" class="tbl_no_brdr">
														<tr>
															<td>
													<!-- BACK button removed - client requirement -->
												       <label><input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose" /> </label>
												   </td>
														</tr>
													</table>
												</div>
											</div>
											</form:form>
											
								</div>
						</td>
					</tr>
				</table>
				</div>
		
</body>
</html>