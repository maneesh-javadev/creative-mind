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
				<!-- <table width="100%" class="tbl_no_brdr">
					<tr>
						<td width="100%" valign="top" class="tblclear"> -->
								
									<div class="frmhd">
										
												<h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.ViewOrganizationState"></spring:message> </h3>
												<ul class="listing">
												<%--//this link is not working  <li><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message> </a></li> --%>
											</ul>
									</div>
									<div class="frmpnlbrdr">
										<form:form action="viewMinistryAction.htm" method="POST" commandName="viewOrganizationStateDetail">
										<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewMinistryAction.htm"/>" />
											<div class="frmpnlbg">
												<div class="frmtxt" >
													<%-- <div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.MINISTRYDETAIL"></spring:message>
													</div> --%>
													

													
														<%-- <c:forEach var="listOrgStateList" varStatus="listMinistryDetailsRow" items="${viewOrgState.listOrgStateList}">
															<tr>
																<td width="14%" rowspan="54">&nbsp;</td>
																<td class="lblBold"><spring:message htmlEscape="true"  code="Label.MINISTRYCODE"></spring:message><br />
																	<label class="lblPlain"> <spring:bind path="viewOrgState.listOrgStateList[${listMinistryDetailsRow.index}].orgCode">&nbsp;
																				 <c:out value="${status.value}" />
																		</spring:bind> </label>
																	<div class="errormsg"></div></td>
															</tr>
															<tr>
																<td class="lblBold"><spring:message htmlEscape="true"  code="Label.MINISTRYNAMEINENG"></spring:message><br />
																	      <label class="lblPlain"> <spring:bind path="viewOrgState.listOrgStateList[${listMinistryDetailsRow.index}].orgName">&nbsp;
																				<c:out value="${status.value}" />
																		</spring:bind> </label>
																	<div class="errormsg"></div></td>
															</tr>
															<tr>
																<td class="lblBold"><spring:message htmlEscape="true"  code="Label.MINISTRYSHORTNAMEINENG"></spring:message><br />
																	      <label class="lblPlain"> <spring:bind path="viewOrgState.listOrgStateList[${listMinistryDetailsRow.index}].shortName">&nbsp;
																				<c:out value="${status.value}" />
																		</spring:bind> </label>
																	<div class="errormsg"></div></td>
															</tr>
															</c:forEach>
														 --%>
														
													<c:if test="${! empty listMinistry}">
																	
														<c:forEach var="listMinistry" items="${listMinistry}">	
														<%-- <tr>
																<td width="14%">&nbsp;</td>
																<td class="lblBold"><spring:message htmlEscape="true"  code="Label.ORGNAME"></spring:message><br />
																	<label class="lblPlain"> <c:out value="${listMinistry.orgCode}"></c:out> </label>						  
																	<div class="errormsg"></div></td>
														</tr>	 --%>
														 
														
																 <div  >
																 <ul class="blocklist">
																 <li>
																<spring:message htmlEscape="true"  code="Label.ORGNAME"></spring:message><br />
																	<label class="lblPlain"> <c:out value="${listMinistry.orgName}" escapeXml="true"></c:out></label>						  
																	<div class="errormsg"></div>
																	</li>
																	
														
																
																<li>
																<spring:message htmlEscape="true"  code="Label.NAMEORGLOCAL"></spring:message><br />
																	<label class="lblPlain"> <c:out value="${listMinistry.orgNameLocal}" escapeXml="true"></c:out></label>						  
																	<div class="errormsg"></div>
																	</li>
															
														
														<%-- <tr>
																<td width="14%">&nbsp;</td>
																<td class="lblBold"><spring:message htmlEscape="true"  code="Label.MINISTRYCODE"></spring:message><br />
																	<label class="lblPlain"> <c:out value="${listMinistry.orgVersion}"></c:out> </label>						  
																	<div class="errormsg"></div></td>
														</tr> --%>
														
														
														
																<li>
																<label><spring:message htmlEscape="true"  code="Label.SHORTORGNAME"></spring:message></label><br />
																	<label class="lblPlain"> <c:out value="${listMinistry.shortName}" escapeXml="true"></c:out></label>						  
																	<div class="errormsg"></div>
																	</li>
															
																<li>
																<spring:message htmlEscape="true"  code="Label.ORGNTYPE"></spring:message><br />
																	<label class="lblPlain"> <c:out value="${listMinistry.organizationType.orgType}" escapeXml="true"></c:out></label>						  
																	<div class="errormsg"></div>
																	
																	</li>
																	</ul>
															</div>
														
														<%-- <tr>
																<td width="14%">&nbsp;</td>
																<td class="lblBold"><spring:message htmlEscape="true"  code="Label.NAMELOCALGOVTTYPE"></spring:message><br />
																	<label class="lblPlain"> <c:out value="${listMinistry.localBodyType.localBodyTypeName}"></c:out> </label>						  
																	<div class="errormsg"></div></td>
														</tr>
														 --%>
														<%-- <tr>
																<td width="14%">&nbsp;</td>
																<td class="lblBold"><spring:message htmlEscape="true"  code="Label.STATENAMEINENGLISH"></spring:message><br />
																	<label class="lblPlain"> <c:out value="${listMinistry.stateNameEnglish}"></c:out> </label>						  
																	<div class="errormsg"></div></td>
														</tr> --%>
																											
															
													</c:forEach>
													</c:if>		
														

												</div>

												<div class="btnpnl">
													
													<!-- BACK button removed - client requirement -->
												       <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose" /> 
												   
												</div>
											</div>
											</form:form>
											
								</div>
					
				</div>
		
</body>
</html>