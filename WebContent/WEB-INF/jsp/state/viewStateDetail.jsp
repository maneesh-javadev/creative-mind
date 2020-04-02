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
<script type="text/javascript">
 
		</script>

</head>
<body >
				<div class="frmhd">
									<h3 class="subtitle"><spring:message code="Label.VIEWSTATE" htmlEscape="true"></spring:message></h3>
									<ul class="listing">
										<%--//these links are not working <li>
											<a href="#" class="frmhelp"><spring:message code="Label.HELP" htmlEscape="true"></spring:message></a>
										</li> --%>
									</ul>
								</div>
									<div class="frmpnlbrdr">
										<form:form action="viewStateAction.htm" method="POST" commandName="stateForm" id="frmViewState">
										<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewStateAction.htm"/>" />
											<c:forEach var="listStateDetails" varStatus="listStateDetailsRow" items="${stateForm.listStateDetails}">
											<div class="frmpnlbg">
												
												<div class="frmtxt">
													<div class="frmhdtitle"><spring:message code="Label.STATEDETAIL" htmlEscape="true"></spring:message></div>
													<div align="center"><font size="2">${successMsg}</font></div>
														<div>
															<ul>
																<li>
																	<spring:message code="Label.STATECODE" htmlEscape="true"></spring:message><br />
																	      <label class="lblPlain"> <spring:bind path="stateForm.listStateDetails[${listStateDetailsRow.index}].stateCode">&nbsp;
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
																		         </spring:bind> 
																	      </label>
																	<div class="errormsg"></div>
																</li>
																<li>
																	<spring:message code="Label.STATEVERSION" htmlEscape="true"></spring:message><br />
																	      <label class="lblPlain"> <spring:bind path="stateForm.listStateDetails[${listStateDetailsRow.index}].stateVersion">&nbsp;
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
																		          </spring:bind>
																		  </label>
																	<div class="errormsg"></div>
																</li>
																<li>
																	<spring:message code="Label.STATENAMEINENGLISH" htmlEscape="true"></spring:message><br />
																	      <label class="lblPlain"> <spring:bind path="stateForm.listStateDetails[${listStateDetailsRow.index}].stateNameEnglish">&nbsp;
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
																		          </spring:bind>
																		 </label>
																	<div class="errormsg"></div>
																</li>
																<li>
																	<spring:message code="Label.STATENAMEINLOCAL" htmlEscape="true"></spring:message><br />
																	       <label class="lblPlain"> <spring:bind path="stateForm.listStateDetails[${listStateDetailsRow.index}].stateNameLocal">&nbsp;
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
																		           </spring:bind>
																		   </label>
																	<div class="errormsg"></div>
																</li>
																<li>
																`	<spring:message code="Label.SHORTNAMEENGLISH" htmlEscape="true"></spring:message><br />
																	      <label class="lblPlain"> <spring:bind path="stateForm.listStateDetails[${listStateDetailsRow.index}].shortName">&nbsp;
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
																		          </spring:bind>
																		  </label>
																	<div class="errormsg"></div>
																</li>
																<%-- <li>
																	<spring:message	code="Label.CENSUS2001" htmlEscape="true"></spring:message><br /> 
																          <label class="lblPlain"><spring:bind path="stateForm.listStateDetails[${listStateDetailsRow.index}].census2001Code">&nbsp;
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
																		         </spring:bind> 
																		  </label>
																	<div class="errormsg"></div>
																</li> --%>
																<%-- <li>
																	<spring:message	code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message><br /> 
																		   <label class="lblPlain"><spring:bind path="stateForm.listStateDetails[${listStateDetailsRow.index}].census2011Code">&nbsp;
																				               <c:out value="${status.value}" escapeXml="true"></c:out>
																		          </spring:bind> 
																		   </label>
																	<div class="errormsg"></div>
																</li> --%>
																<li>
																	<spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message><br />
																		<label class="lblPlain"><spring:bind path="stateForm.listStateDetails[${listStateDetailsRow.index}].orderNocr">&nbsp;
																												<c:out value="${status.value}" escapeXml="true"></c:out>
																				   </spring:bind> </label>	
																	
																	<div class="errormsg"></div>
																</li>
																<li>
																	<spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message><br />
																		<label class="lblPlain"><spring:bind path="stateForm.listStateDetails[${listStateDetailsRow.index}].orderDatecr">&nbsp;
																												<c:out value="${status.value}" escapeXml="true"></c:out>
																				   </spring:bind> </label>	
																	
																	<div class="errormsg"></div>
																</li>
																<li>
																	<spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message><br />
																		<label class="lblPlain"><spring:bind path="stateForm.listStateDetails[${listStateDetailsRow.index}].ordereffectiveDatecr">&nbsp;
																												<c:out value="${status.value}" escapeXml="true"></c:out>
																				   </spring:bind> </label>	
																	
																	<div class="errormsg"></div>
																</li>
																<li>
																	<spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message><br />
																		<label class="lblPlain"><spring:bind path="stateForm.listStateDetails[${listStateDetailsRow.index}].gazPubDatecr">&nbsp;
																												<c:out value="${status.value}" escapeXml="true"></c:out>
																				   </spring:bind> </label>	
																	
																	<div class="errormsg"></div>
																</li>
															</ul>
														</div>
													</div>
												</div>
																			
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
														
													 <c:set var="str1" value="${listStateDetails.cordinate}"/> 
														
														<c:forEach var="str2" items="${fn:split(str1, ',')}">
														  <tr>
														  <c:set var="count" value="1"></c:set>
														   <c:forEach var="num" items="${fn:split(str2, ':')}">	
														       
																	    <c:if test="${count==1}">
																	       <td width="30%" align="center" class="lblBold">
																	      <c:out value="${num}" escapeXml="true"></c:out> 
												                           </td>
												                         
																	    </c:if>													     
																	    <c:if test="${count!=1}">
																	      <td width="30%" align="left" class="lblBold">
																	      <c:out value="${num}" escapeXml="true"></c:out> 
												                           </td>									                       
																	    </c:if>				 
									                           <c:set var="count" value="${count+1}"></c:set>  
									                          
									                       </c:forEach>	
									                        <td width="30%" align="center" class="lblBold">&nbsp;</td>
									                      </tr>								                        
									                    </c:forEach>
											
														</c:forEach>
													</table>
												</div>

												<div class="btnpnl">
													<!-- BACK button removed - client requirement -->
							                     	<label> <input type="button" name="Submit3" class="btn" value="<spring:message code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose" /> </label>
												</div>
											</div>
											 </form:form>
		  <script src="/LGD/JavaScriptServlet"></script>
									</div>
							   
	    </body>
</html>