<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
<script type="text/javascript">
function loadPage() {
		var mypath = window.location.href;

		document.getElementById('btnback').style.visibility='visible';
		document.getElementById('btnCancel').style.visibility='visible';
		document.getElementById('btnClose').style.visibility='hidden';
		var mySplitResult = mypath.split("&");

		if(mySplitResult[1]!="")
			{
		var type=mySplitResult[1].replace("type=","");
		

		if(type=='S')
			{
			document.getElementById('btnback').style.visibility='hidden';
			document.getElementById('btnCancel').style.visibility='hidden';
			document.getElementById('btnClose').style.visibility='visible';
			
			}
		else
			{
			document.getElementById('btnback').style.visibility='visible';
			document.getElementById('btnCancel').style.visibility='visible';
			document.getElementById('btnClose').style.visibility='hidden';
			}
			}
	
		
		
}
		</script>
</head>
<body onload="loadPage();">	 
	          <div id="frmcontent">
				
									<div class="frmhd">
									
										<h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.VIEWDEPTDETAIL"></spring:message></h3>
										 <ul id="showhelp" class="listing">
					 				      		<%--//these links are not working <li>
					 				      					<a href="#" rel="toggle[cat]"	data-openimage="images/minus.jpg"	data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /></a>
					 				      		
					 				      		</li>
					 				        
					 				        <li>
					 				        			<a href="#" class="frmhelp"><spring:message htmlEscape="true" 	code="Button.HELP"></spring:message> </a>
					 				        </li>
					 	 --%>
					 			        </ul>
		
									</div>
									<div class="frmpnlbrdr">
										<form:form action="viewDepartmentAction.htm" method="POST" commandName="viewDepartment">
										<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewDepartmentAction.htm"/>"/>
											<div class="frmpnlbg">
												<div class="frmtxt">
													<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.DEPTDETAIL"></spring:message>
													</div>
													
													
													<div class="margintoleft" >
													<ul class="blocklist">
													<c:forEach var="listMinistry" varStatus="listMinistryDetailsRow" items="${viewDepartment.listMinistry}">
																<li class="lblBold"><spring:message htmlEscape="true" code="Label.DEPTCODE"></spring:message><br />
																	<label class="lblPlain"> <spring:bind path="viewDepartment.listMinistry[${listMinistryDetailsRow.index}].orgCode">&nbsp;
																				 <c:out value="${status.value}" escapeXml="true"></c:out>
																		</spring:bind> </label>
																	<div class="errormsg"></div></li>
																<li class="lblBold" >
																			<spring:message htmlEscape="true" code="Label.DEPTNAMEINENG"></spring:message><br />
																	      <label class="lblPlain"> <spring:bind path="viewDepartment.listMinistry[${listMinistryDetailsRow.index}].orgName">&nbsp;
																				<c:out value="${status.value}" escapeXml="true"></c:out>
																		</spring:bind> </label>
																	<div class="errormsg"></div>
																
																</li>
																<li class="lblBold">
																		<spring:message htmlEscape="true" code="Label.DEPTSHORTNAMEINENG"></spring:message><br />
																	      <label class="lblPlain"> <spring:bind path="viewDepartment.listMinistry[${listMinistryDetailsRow.index}].shortName">&nbsp;
																				<c:out value="${status.value}" escapeXml="true"></c:out>
																		</spring:bind> </label>
																	<div class="errormsg"></div>
																
																</li>
																<li class="lblBold" >
																		<spring:message htmlEscape="true" code="Label.DEPTNAMEINLOCAL"></spring:message><br />
																	      <label class="lblPlain"> <spring:bind path="viewDepartment.listMinistry[${listMinistryDetailsRow.index}].orgNameLocal">&nbsp;
																				<c:out value="${status.value}" escapeXml="true"></c:out>
																		</spring:bind> </label>
																	<div class="errormsg"></div>
																
																</li>
																<li class="lblBold" >
																				<spring:message htmlEscape="true" code="Label.ORGLEVEL"></spring:message><br />
																	      <label class="lblPlain"> <spring:bind path="viewDepartment.listMinistry[${listMinistryDetailsRow.index}].orgLevel">&nbsp;
																				<c:if test="${fn:containsIgnoreCase(status.value,'C')}">Centre</c:if>
																				<c:if  test="${fn:containsIgnoreCase(status.value,'S')}">State</c:if>
																				
																		</spring:bind> </label>
																	<div class="errormsg"></div>
																
																</li>
																<li class="lblBold">
																			<spring:message htmlEscape="true" code="Label.ORGNTYPE"></spring:message><br />
																	      <label class="lblPlain"> <spring:bind path="viewDepartment.listMinistry[${listMinistryDetailsRow.index}].organizationType.orgType">&nbsp;
																				<c:out value="${status.value}" escapeXml="true"></c:out>
																		</spring:bind> </label>
																	<div class="errormsg"></div>
																</li>
																
													</c:forEach>
													
													
													</ul>
													</div>
													<div class="clear"></div>
												
												</div>

												<div class="btnpnl">
														<label> <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose"/> </label>
												</div>
											</div>
								</div>
							</div>
					
		</form:form>
		<script src="/LGD/JavaScriptServlet"></script>		
</body>
</html>