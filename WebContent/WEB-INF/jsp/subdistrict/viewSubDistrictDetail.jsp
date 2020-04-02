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
<body>
	
					<div id="frmcontent">
				  				<div class="frmhd">
							     	<h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.SUBDISTRICT"></spring:message></h3>
										<%-- <ul class="listing">
											<li>
												<a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message> </a>
											</li>
										</ul> --%>
							     </div>
							     
							     <div class="frmpnlbrdr">
										<form:form action="modifySubDistrictAction.htm" method="POST" commandName="modifySubDistrictCmd" id="frmModifySubDistrict">
										<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifySubDistrictAction.htm"/>"/>
										<input type="hidden" name="warningEntiesFlag" id="warningEntiesFlag" value="<c:out value='${warningEntiesFlag}'/>"/>
				  						<input type="hidden" name="type" id="type" value="<c:out value='${type}'/>"/>
							     		<font size="3"><esapi:encodeForHTML>${successMsg}</esapi:encodeForHTML></font>
							     			<div class="frmpnlbg">
												<div class="frmtxt">
													<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.SUBDISTRICTDETAIL"></spring:message></div>
														<div class="block">
															<div  >
																<ul class="blocklist">
																	<c:forEach var="listSubdistrictDetails" varStatus="listSubdistrictDetailsRow" items="${modifySubDistrictCmd.listSubdistrictDetails}">
																		<li>
																			<spring:message htmlEscape="true"  code="Label.SUBDISTRICTCODE"></spring:message><br />
																			<label class="lblPlain"> 
																			<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictCode">&nbsp;
																					<c:out value="${status.value}" escapeXml="true" />
																				</spring:bind> </label>
																			<div class="errormsg"></div>
																		</li>	
																		<li>
																			<spring:message htmlEscape="true"  code="Label.SUBDISTRICTVERSION"></spring:message><br />
																			<label class="lblPlain"> 
																			<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictVersion">&nbsp;													
																				 <c:out value="${status.value}" escapeXml="true" />
																			</spring:bind> </label>
																			<div class="errormsg"></div>
																		</li>
																		<li>
																			<spring:message htmlEscape="true"  code="Label.SUBDISTRICTNAMEENGLISH"></spring:message><br />
																			<label class="lblPlain">
                                                                  <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictNameEnglish">&nbsp;																				
																				  <c:out value="${status.value}" escapeXml="true" />
																			</spring:bind> </label>
																		<div class="errormsg"></div>
																		</li>
																		<li>
																			<spring:message htmlEscape="true"  code="Label.SUBDISTRICTNAMELOCAL"></spring:message><br />
																			<label class="lblPlain"> 
																			
																			<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictNameLocal">&nbsp;
											                                     <c:out value="${status.value}"  escapeXml="true"/>
																			</spring:bind> </label>
																			<div class="errormsg"></div>
																		</li>
																		<li>
																			<spring:message htmlEscape="true"  code="Label.SUBDISTRICTALIASENGLISH"></spring:message><br />
																			<label class="lblPlain"> 
																			<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].aliasEnglish">&nbsp;
																				  <c:out value="${status.value}" escapeXml="true"/>
																			</spring:bind> </label>
																			<div class="errormsg"></div>
																		</li>
																		<li>
																			<spring:message htmlEscape="true"  code="Label.SUBDISTRICTALIASLOCAL"></spring:message><br />
																			<label class="lblPlain">
																			 <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].aliasLocal">&nbsp;
																				       <c:out value="${status.value}" escapeXml="true" />
																			</spring:bind> </label>
																			<div class="errormsg"></div>
																		</li>
																		<li>
																			<spring:message htmlEscape="true"  code="Label.HEADQUARTERS"></spring:message><br />
                                                                       		 <label label class="lblPlain">
                                                                       		 <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].headquarterName">&nbsp;							
								                                                             <c:out value="${status.value}" escapeXml="true"/>							
					    	                                                      </spring:bind>
                                        										</label>
                                                                       			<div class="errormsg"></div>
																		</li>
																		<%-- <li>
																			<spring:message htmlEscape="true"  code="Label.CENSUS2001"></spring:message><br /> 
																			<label class="lblPlain"><spring:bind	path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].census2001Code">&nbsp;
																				     <c:out value="${status.value}" escapeXml="true"/>
																			</spring:bind> <form:input path="census2011Code" class="frmfield" style="width:250px"/>
																			</label>
																		<div class="errormsg"></div>
																		</li>
																		<li>
																			<spring:message htmlEscape="true"  code="Label.CENSUSCODE2011"></spring:message><br /> 
																			<label class="lblPlain"><spring:bind	path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].census2011Code">&nbsp;
																				     <c:out value="${status.value}" escapeXml="true" />
																			</spring:bind> </label>
																			<div class="errormsg"></div>
																		</li> --%>
																		<li>	
																			<spring:message htmlEscape="true"  code="Label.STATESPECIFICCODE"></spring:message><br />
																			<label class="lblPlain"> 
																			<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].sscode">&nbsp;
																				    <c:out value="${status.value}" escapeXml="true"/>
																			</spring:bind> </label>
																			<div class="errormsg"></div>
																		</li>
																		<li>
																			<spring:message htmlEscape="true"  code="Label.ORDERNO"></spring:message><br />
																			<label class="lblPlain">
																			<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderNocr">&nbsp;
																					<c:out value="${status.value}" escapeXml="true"/>
																			   </spring:bind> </label>	
																			</label>
																			<div class="errormsg"></div>
																		</li>
																		<li>
																			<spring:message htmlEscape="true"  code="Label.ORDERDATE"></spring:message><br />
																				<label class="lblPlain"><spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderDatecr">&nbsp;
																														<c:out value="${status.value}" escapeXml="true"/>
																						   </spring:bind> </label>	
																			</label>
																			<div class="errormsg"></div>
																		</li>
																		<li>
																			<spring:message htmlEscape="true"  code="Label.EFFECTIVEDATE"></spring:message><br />
																				<label class="lblPlain"><spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].ordereffectiveDatecr">&nbsp;
																														<c:out value="${status.value}" escapeXml="true"/>
																						   </spring:bind> </label>	
																			</label>
																			<div class="errormsg"></div>
																		</li>
																		<li>
																			<spring:message htmlEscape="true"  code="Label.GAZPUBDATE"></spring:message><br />
																				<label class="lblPlain"><spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].gazPubDatecr">&nbsp;
																														<c:out value="${status.value}" escapeXml="true"/>
																						   </spring:bind> </label>	
																			</label>
																			<div class="errormsg"></div>
																		</li>
																		 <c:set var="string1" value="${modifySubDistrictCmd.listSubdistrictDetails[listSubdistrictDetailsRow.index].cordinate}"/> 
																	</c:forEach>
																</ul>
															</div>
														</div>
													</div>
													
													<div class="frmtxt">
                        									<div class="frmhdtitle">
																<spring:message code="Label.GISNODES" htmlEscape="true"></spring:message>
															</div>
													<table width="100%" class="tbl_no_brdr">
													<tr>
														<td width="15%"> </td>
														<td width="30%" align="left" class="lblBold"><label id="lbllatitude"><spring:message
																	code="Label.LATITUDE" htmlEscape="true"></spring:message> </label>
														</td>
														
														<td width="30%" align="left" class="lblBold"><label id="lbllongitude"><spring:message
																	code="Label.LONGITUDE" htmlEscape="true"></spring:message> </label>
														</td>
													</tr>
														
														
														
														<%-- <c:set var="string1" value="${modifySubDistrictCmd.listSubdistrictDetails[listSubdistrictDetailsRow.index].cordinate}"/> --%>
													 	<c:forEach var="item" items="${fn:split(string1,',')}"  varStatus="loop">
													 	 <c:set var="string2" value="${fn:split(item,':')}" /> 
    														
    															 <tr>	
	    															<td width="15%"> </td>
	    															 <td>
	    															<label> 
													                   <esapi:encodeForHTML>${string2[0]}</esapi:encodeForHTML>
													                 </label>
												                   	
												                  <td >
												             	<label> 
												             	<esapi:encodeForHTML>${string2[1]}</esapi:encodeForHTML>
												            			
																</label>
												                    
												          		 </td>	
												          		 
												          		  <td>	
												          		
												          		  </td>						                       
												           
												             </tr>
												           </c:forEach>
														</table>
													</div>
													
													<%-- <div class="btnpnl">
														<ul class="listing">
															<li>
																<label>
														  			 <input type="button" name="Submit3" class="btn"	value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>&warningEntiesFlag=${warningEntiesFlag}&type=${type}';" /> 
																</label>
															</li>
														</ul>
													</div> --%>
												</div>		
								     		</form:form>
								     </div>
								<script src="/LGD/JavaScriptServlet"></script>
						</div>
		</body>
</html>