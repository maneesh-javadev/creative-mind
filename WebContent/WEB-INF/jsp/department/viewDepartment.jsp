<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


<%@include file="../common/taglib_includes.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>
<body onload="loadPage();">	 
	          <div id="frmcontent">
				<table width="100%" class="tbl_no_brdr">
					<tr>
						<td width="100%" valign="top" class="tblclear">
								
									<div class="frmhd">
									<h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.VIEWDEPTDETAIL"></spring:message></h3>
										 <ul id="showhelp" class="listing">
					 				      
					 				        
					 				      <%--//these links are not working   <li>
					 				        			<a href="#" class="frmhelp"><spring:message htmlEscape="true" 	code="Button.HELP"></spring:message> </a>
					 				        </li> --%>
					 	
					 			        </ul>
									</div>
									<div class="frmpnlbrdr">
										<form:form action="viewMinistryAction.htm" method="POST" commandName="viewMinistry">
										<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewMinistryAction.htm"/>"/>
											<div class="frmpnlbg">
												<div class="frmtxt">
													<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.VIEWDEPTDETAIL"></spring:message>
													</div>
													<table width="100%" class="tbl_no_brdr">
														<c:forEach var="listDept" varStatus="listDepartmentRow" items="${listDept}">
																	<tr class="tblRowB">
																		<td width="6%"><c:out value="${listDepartmentRow.index+1}" escapeXml="true"></c:out></td>
																		<td><c:out value="${listDept.orgCode}" escapeXml="true"></c:out></td>
																		<td align="left"><c:out value="${listDept.orgName}" escapeXml="true"></c:out></td>
																		<td  width="8%" align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageDepartmentState('viewDepartment.htm',${listDept.orgCode});" width="18" height="19" border="0" /></a></td>
																		<td  width="8%" align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageDepartmentState('modifyDepartment.htm',${listDept.orgCode});" width="18" height="19" border="0" /></a></td>
																	<td  width="8%" align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageDepartmentState('Deletedepartmentforstate.htm',${listDept.orgCode});" width="18" height="19" border="0" /></a></td> 	
																	</tr>
															</c:forEach>
														
														
														<c:forEach var="listDept" varStatus="listMinistryDetailsRow" items="${listDistDept}">
															<tr>
																<td width="14%" rowspan="54">&nbsp;</td>
																<td class="lblBold"><spring:message htmlEscape="true"  code="Label.DEPTCODE"></spring:message><br />
																	<label class="lblPlain"> <c:out value="${listDept.orgCode}" escapeXml="true"></c:out></label>
																	<div class="errormsg"></div></td>
															</tr>
															<tr>
																<td class="lblBold"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message><br />
																	      <label class="lblPlain"><c:out value="${listDept.orgName}" escapeXml="true"></c:out></label>
																	<div class="errormsg"></div></td>
															</tr>
															<tr>
																<td class="lblBold"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINLOCAL"></spring:message><br />
																	      <label class="lblPlain"> <c:out value="${listDept.orgNameInLocal}" escapeXml="true"></c:out></label>
																	<div class="errormsg"></div></td>
															</tr>
															
															<tr>
																<td class="lblBold"><spring:message htmlEscape="true"  code="Label.DEPTSHORTNAMEINENG"></spring:message><br />
																	      <label class="lblPlain"><c:out value="${listDept.shortDeptName}" escapeXml="true"></c:out></label>
																	<div class="errormsg"></div></td>
															</tr>
														

														</c:forEach>
													</table>
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
								</div>
							</div>
						</td>
					</tr>
				</table>
		</form:form>
		<script src="/LGD/JavaScriptServlet"></script>	
</body>
</html>