<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<title><spring:message htmlEscape="true" code="Label.VIEWVILLAGE"></spring:message>
</title>

<script type="text/javascript">

		</script>

</head>
<body>
	
		<div id="frmcontent">
					
		<div class="frmhd">
			<h3 class="subtitle">
				<label><spring:message htmlEscape="true" code="Label.MANAGEVILLAGE"></spring:message> </label>
			</h3>
			<ul id="showhelp" class="listing">												
			<%--//these links are not working 	<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>						
							
			<div class="frmpnlbrdr">
					<form:form action="viewVillageAction.htm" method="POST" commandName="villageView" id="frmModifyVillage">
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewVillageAction.htm"/>"/>
							<div class="frmpnlbg">
										<div class="frmtxt">
										<div>
											<font size="3">${successMsg}</font>
										</div>
											<div class="frmhdtitle">
												<spring:message htmlEscape="true" code="Label.VILLDETAIL"></spring:message>
											</div>										
											<ul class="blocklist">
												<c:forEach var="listVillageDetails"
													varStatus="listVillageDetailsRow"
													items="${villageView.listVillageDetails}">
											
													<li>						
															<div class="frmpnlbg">
																<div class="frmtxt">
																	<div class="frmhdtitle">
																		<spring:message code="Label.GENERALDETAILOFNEWVILLAGE" htmlEscape="true"></spring:message>
																	</div>
												<table width="100%" class="tbl_no_brdr">
													<tr>														
														<td width="30%" align="center" class="lblBold"><spring:message htmlEscape="true"
																code="Label.VILLAGECODE"> : </spring:message> </td>
														 <td width="30%" align="left">		
																<label> <spring:bind
																	path="villageView.listVillageDetails[${listVillageDetailsRow.index}].villageCode">&nbsp;
																				                    <c:out value="${status.value}" escapeXml="false"/>
																</spring:bind></label>
															</td>
														<td  width="30%"><div class="errormsg"></div></td>														
													 </tr>

													<tr>
													
													<td width="30%" align="center" class="lblBold"><spring:message htmlEscape="true"
																code="Label.VILLAGEVERSION"> : </spring:message> </td>
														 <td width="30%" align="left">		
																<label> <spring:bind
																	path="villageView.listVillageDetails[${listVillageDetailsRow.index}].village_version">&nbsp;
																				                    <c:out value="${status.value}" escapeXml="false"/>
																</spring:bind></label>
															</td>
														<td  width="30%"><div class="errormsg"></div></td>							
													
														
													</tr>

													<tr>

													<td width="30%" align="center" class="lblBold"><spring:message htmlEscape="true"
																code="Label.VILLAGENAMEINENGLISH"> : </spring:message> </td>
														 <td width="30%" align="left">		
																<label> <spring:bind
																	path="villageView.listVillageDetails[${listVillageDetailsRow.index}].villageNameEnglish">&nbsp;
																			                       <c:out value="${status.value}" escapeXml="false"/>
																</spring:bind></label>
															</td>
														<td  width="30%"><div class="errormsg"></div></td>	

													</tr>

													<tr>
													
													
													<td width="30%" align="center" class="lblBold"><spring:message htmlEscape="true"
																code="Label.VILLAGENAMEINLOCAL"> : </spring:message> </td>
														 <td width="30%" align="left">		
																<label> <spring:bind
																	path="villageView.listVillageDetails[${listVillageDetailsRow.index}].villageNameLocal">&nbsp;
																				                  <c:out value="${status.value}" escapeXml="false"/>
																</spring:bind></label>
															</td>
														<td  width="30%"><div class="errormsg"></div></td>
														
													</tr>

													<tr>
													<td width="30%" align="center" class="lblBold"><spring:message htmlEscape="true"
																code="Label.ALIASENGLISH"> : </spring:message> </td>
														 <td width="30%" align="left">		
																<label> <spring:bind
																	path="villageView.listVillageDetails[${listVillageDetailsRow.index}].aliasEnglish">&nbsp;
																				                  <c:out value="${status.value}" escapeXml="false"/>
																</spring:bind></label>
															</td>
														<td  width="30%"><div class="errormsg"></div></td>
													
													</tr>

													<tr>
													
													<td width="30%" align="center" class="lblBold"><spring:message htmlEscape="true"
																code="Label.ALIASLOCAL"> : </spring:message> </td>
														 <td width="30%" align="left">		
																<label> <spring:bind
																	path="villageView.listVillageDetails[${listVillageDetailsRow.index}].aliasLocal">&nbsp;
																				                  <c:out value="${status.value}" escapeXml="false"/>
																</spring:bind></label>
															</td>
														<td  width="30%"><div class="errormsg"></div></td>
													</tr>

													
<%-- 
													<tr>
													
													<td width="30%" align="center" class="lblBold"><spring:message htmlEscape="true"
																code="Label.CENSUS2001"> : </spring:message> </td>
														 <td width="30%" align="left">		
																<label><spring:bind
																	path="villageView.listVillageDetails[${listVillageDetailsRow.index}].census2001Code">&nbsp;
																				                <c:out value="${status.value}" escapeXml="false"/>
																</spring:bind></label>
															</td>
														<td  width="30%"><div class="errormsg"></div></td>
														
													   </tr>

													<tr>
													
													
													<td width="30%" align="center" class="lblBold"><spring:message htmlEscape="true"
																code="Label.CENSUSCODE2011"> : </spring:message> </td>
														 <td width="30%" align="left">		
																<label><spring:bind
																	path="villageView.listVillageDetails[${listVillageDetailsRow.index}].census2011Code">&nbsp;
																				               <c:out value="${status.value}" escapeXml="false"/>
																</spring:bind></label>
															</td>
														<td  width="30%"><div class="errormsg"></div></td>
													
													</tr>

													<tr> --%>
													
													<td width="30%" align="center" class="lblBold"><spring:message htmlEscape="true"
																code="Label.STATESPECIFICCODE"> : </spring:message> </td>
														 <td width="30%" align="left">		
																<label><spring:bind
																	path="villageView.listVillageDetails[${listVillageDetailsRow.index}].sscode">&nbsp;
																				              <c:out value="${status.value}" escapeXml="false"/>
																</spring:bind></label>
															</td>
														<td  width="30%"><div class="errormsg"></div></td>
													</tr>													
											</table>
										</div>
									</div>						
								
								</li>				
								<li>																						
										<div class="frmpnlbg">
											<div class="frmtxt">
												<div class="frmhdtitle">
													<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message>
												</div>
												
									<table width="100%" class="tbl_no_brdr">
										<tr>
											
											<td width="30%" align="center" class="lblBold"><spring:message htmlEscape="true"
																code="Label.ORDERNO"> : </spring:message> </td>
														 <td width="30%" align="left">		
																<label><spring:bind path="villageView.listVillageDetails[${listVillageDetailsRow.index}].orderNocr">&nbsp;
																							<c:out value="${status.value}" escapeXml="false"/>
															   </spring:bind></label>
															</td>
														<td  width="30%"><div class="errormsg"></div></td>											
												
										</tr>
											
										<tr>											
												<td width="30%" align="center" class="lblBold"><spring:message htmlEscape="true"
																code="Label.ORDERDATE"> : </spring:message> </td>
														 <td width="30%" align="left">	
														 	<fmt:formatDate var="orderDatecr" value="${listVillageDetails.orderDatecr}"  pattern="dd/MM/yyyy"/>
																<label>&nbsp;&nbsp;${orderDatecr}
															   </label>
															</td>
														<td  width="30%"><div class="errormsg"></div></td>												
										</tr>
											
										<tr>
											
											<td width="30%" align="center" class="lblBold"><spring:message htmlEscape="true"
																code="Label.EFFECTIVEDATE"> : </spring:message> </td>
														 <td width="30%" align="left">
														 <fmt:formatDate var="ordereffectiveDatecr" value="${listVillageDetails.ordereffectiveDatecr}"  pattern="dd/MM/yyyy"/>
																<label>&nbsp;&nbsp;${ordereffectiveDatecr}
															   </label>
															</td>		
																
															
														<td  width="30%"><div class="errormsg"></div></td>	
														
											
										</tr>
											
										<tr>
											
											<td width="30%" align="center" class="lblBold"><spring:message htmlEscape="true"
																code="Label.GAZPUBDATE"> : </spring:message> </td>
														 <td width="30%" align="left">	
														 
														  <fmt:formatDate var="gazPubDatecr" value="${listVillageDetails.gazPubDatecr}"  pattern="dd/MM/yyyy"/>
																<label>&nbsp;&nbsp;${gazPubDatecr}
															   </label>
															   	
																
															</td>
														<td  width="30%"><div class="errormsg"></div></td>	
														
											
										</tr>												
									</table>
								</div>
							</div>										
							</li>																	
							<li>								
									<div class="frmpnlbg">										
										<div class="frmtxt">
												<div class="frmhdtitle">
													<spring:message code="Label.GISNODES" htmlEscape="true"></spring:message>
												</div>
												<table width="100%" class="tbl_no_brdr">
													<tr>
														
																					<td width="30%" align="center" class="lblBold"><label id="lbllatitude"><spring:message
																								code="Label.LATITUDE" htmlEscape="true"></spring:message> </label>
																					</td>
																					
																					<td width="30%" align="left" class="lblBold"><label id="lbllongitude"><spring:message
																								code="Label.LONGITUDE" htmlEscape="true"></spring:message> </label>
																					</td>
																					<td width="30%" align="center" class="lblBold"></td>
																					
													</tr>
														 <c:set var="str1" value="${listVillageDetails.cordinate}"/>
														<c:forEach var="str2" items="${fn:split(str1, ',')}">
													<tr>
														  <c:set var="count" value="1"></c:set>
														   <c:forEach var="num" items="${fn:split(str2, ':')}">	
														       
																	    <c:if test="${count==1}">
																	       <td width="30%" align="center" class="lblBold">
																	      <c:out value="${num}" /> 
												                           </td>
												                         
																	    </c:if>													     
																	    <c:if test="${count!=1}">
																	      <td width="30%" align="left" class="lblBold">
																	      <c:out value="${num}" /> 
												                           </td>									                       
																	    </c:if>				 
									                           <c:set var="count" value="${count+1}"></c:set>  
									                          
									                       </c:forEach>	
									                        <td width="30%" align="center" class="lblBold">&nbsp;</td>
									               </tr>								                        
									                    </c:forEach>							
												</table>
											</div>			
										</div>
									</li>		
								</c:forEach>
							</ul>														
						</div>

										<div class="btnpnl">										
											<label>
												<input style="width: 50px;" type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose" /> 
											</label>												
										</div>
									</div>
								  </form:form>	
								   <script src="/LGD/JavaScriptServlet"></script>
							</div>
				
		</div>
	
	</div>
</body>
</html>