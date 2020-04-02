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
								<div class="frmhd">
									<h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.MINISTRYDETAIL"></spring:message></h3>
									<ul class="listing">
										<%--//these links are not working  <li>
											<a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message> </a>
										</li> --%>
									</ul>
								</div>
									
									<!-- <div class="block">
										<div  >
											<ul class="blocklist"> -->
							<div class="frmpnlbrdr">
								<form:form action="viewMinistryAction.htm" method="POST" commandName="viewMinistry">
								<input type="hidden" name="<csrf:token-name/>"
									value="<csrf:token-value uri="viewMinistryAction.htm"/>" />
									<div class="frmpnlbg">
										<div class="frmtxt">
											<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.MINISTRYDETAIL"></spring:message></div>
												<div class="block">
													<div  >
														<ul class="blocklist">
															<c:forEach var="listMinistry" varStatus="listMinistryDetailsRow" items="${viewMinistry.listMinistry}">
															<li>
																<spring:message htmlEscape="true"  code="Label.MINISTRYCODE"></spring:message><br />
																<label class="lblPlain"> <spring:bind path="viewMinistry.listMinistry[${listMinistryDetailsRow.index}].orgCode">&nbsp;
																 <c:out value="${status.value}" escapeXml="true"></c:out>
																</spring:bind> </label>
																<div class="errormsg"></div>
															</li>
															<li>
																<spring:message htmlEscape="true"  code="Label.MINISTRYNAMEINENG"></spring:message><br />
																<label class="lblPlain"> <spring:bind path="viewMinistry.listMinistry[${listMinistryDetailsRow.index}].orgName">&nbsp;
																<c:out value="${status.value}" escapeXml="true"></c:out>
																</spring:bind> </label>
																<div class="errormsg"></div>
															</li>
															<li>
																<spring:message htmlEscape="true"  code="Label.MINISTRYSHORTNAMEINENG"></spring:message><br />
																<label class="lblPlain"> <spring:bind path="viewMinistry.listMinistry[${listMinistryDetailsRow.index}].shortName">&nbsp;
																<c:out value="${status.value}" escapeXml="true"></c:out>
																</spring:bind> </label>
																<div class="errormsg"></div>
															</li>
															
														</c:forEach>
														</ul>
													</div>
												</div>
											</div>
											<div class="btnpnl">
												<ul class="listing">
													<li>
														<label><input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose" /> </label>
													</li>
												</ul>
											</div>
										</div>
									</form:form>
									<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
								</div>
				</div>
		
</body>
</html>