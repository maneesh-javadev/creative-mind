<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="str1"  />
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
 
		</script>

</head>
<body >
	
		<div id="frmcontent">
				<div class="frmhd">
					<h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.DISTRICT"></spring:message></h3>
					<ul class="listing">
						<%--//this link is not working <li>
							<a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a>
						</li> --%>
					</ul>
				</div>
				<div class="frmpnlbrdr">
					<form:form action="viewDistrictAction.htm" method="POST" commandName="districtView" id="frmViewDistrict">
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewDistrictAction.htm"/>"/>
						<div class="frmpnlbg">
							<div class="frmtxt">
							<div  ><font size="3"><c:out value="${successMsg}" escapeXml="true"></c:out></font></div>
								<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.DISTRICTDETAIL"></spring:message></div>
								<div class="block">
									<div  >
										<ul class="blocklist">
											<c:forEach var="listDistrictDetails" varStatus="listDistrictDetailsRow" items="${districtView.listDistrictDetails}">
												<li>
													<spring:message htmlEscape="true"  code="Label.DISTRICTCODE"></spring:message><br />
													<label class="lblPlain"><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].districtCode">&nbsp;
													<c:out value="${status.value}" escapeXml="true"></c:out></spring:bind> 
													</label>
													<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true"  code="Label.DISTRICTVERSION"></spring:message><br />
													<label class="lblPlain"> <spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].districtVersion">&nbsp;
													<c:out value="${status.value}" escapeXml="true"></c:out></spring:bind>
													</label>
													<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true"  code="Label.DISTRICTNAMEENG"></spring:message><br />
													<label class="lblPlain"> <spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].districtNameEnglish">&nbsp;
													<c:out value="${status.value}" escapeXml="true"></c:out></spring:bind>
													</label>
													<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true"  code="Label.DISTRICTNAMELOCAL"></spring:message><br />
													<label class="lblPlain"> <spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].districtNameLocal">&nbsp;
													<c:out value="${status.value}" escapeXml="true"></c:out></spring:bind>
													</label>
													<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true"  code="Label.DISTRICTALIASENGLISH"></spring:message><br />
													<label class="lblPlain"> <spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].aliasEnglish">&nbsp;
													<c:out value="${status.value}" escapeXml="true"></c:out></spring:bind> 
													</label>
													<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true"  code="Label.DISTRICTALIASLOCAL"></spring:message><br />
													<label class="lblPlain"> <spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].aliasLocal">&nbsp;
													<c:out value="${status.value}" escapeXml="true"></c:out></spring:bind> 
													</label>
													<div class="errormsg"></div>	
												</li>
												<li>
													<spring:message htmlEscape="true"  code="Label.HEADQUARTER"></spring:message><br />
                                                                <label label class="lblPlain"><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].headquarterName">&nbsp;							
				                                    <c:out value="${status.value}" escapeXml="true"></c:out></spring:bind>
                                    							</label>
                                                               <div class="errormsg"></div>
												</li>
												<%-- <li>
													<spring:message htmlEscape="true" 	code="Label.CENSUS2001"></spring:message><br /> 
												   	<label class="lblPlain"><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].census2001Code">&nbsp;
													<c:out value="${status.value}" escapeXml="true"></c:out></spring:bind> 
													</label>
													<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true" 	code="Label.CENSUSCODE2011"></spring:message><br /> 
													<label class="lblPlain"><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].census2011Code">&nbsp;
													<c:out value="${status.value}" escapeXml="true"></c:out>
													</spring:bind> 
													</label>
													<div class="errormsg"></div>
												</li> --%>
												<li>
													<spring:message htmlEscape="true"  code="Label.STATESPECIFICCODE"></spring:message><br />
													<label class="lblPlain"> <spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].sscode">&nbsp;
													<c:out value="${status.value}" escapeXml="true"></c:out>
													</spring:bind> 
													</label>
													<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true"  code="Label.ORDERNO"></spring:message><br />
													<label class="lblPlain"><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].orderNocr">&nbsp;
													<c:out value="${status.value}" escapeXml="true"></c:out>
											   		</spring:bind> </label>	
													<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true"  code="Label.ORDERDATE"></spring:message><br />
													<label class="lblPlain"><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].orderDatecr">&nbsp;
													<c:out value="${status.value}" escapeXml="true"></c:out>
											   		</spring:bind> </label>	
													<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true"  code="Label.EFFECTIVEDATE"></spring:message><br />
													<label class="lblPlain"><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].ordereffectiveDatecr">&nbsp;
													<c:out value="${status.value}" escapeXml="true"></c:out>
											  		</spring:bind> </label>	
													<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true"  code="Label.GAZPUBDATE"></spring:message><br />
													<label class="lblPlain"><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].gazPubDatecr">&nbsp;
													<c:out value="${status.value}" escapeXml="true"></c:out>
											   		</spring:bind> </label>
											   		<spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].cordinate">
													<c:set var="str1" value="${status.value}" />
													</spring:bind>	
												</li>
										</c:forEach>	
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
											<!-- <script>alert("${str1}");</script>
										 -->
											<%-- <c:if test="${str1 ne null and str1.indexOf(',') eq -1 and str1.indexOf(':') gt -1 }">
											<c:set var="str1" value="${st1}" />
											</c:if> --%>
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
						               </table>
								</div>
								</div>
							<div class="btnpnl">
								<div class="listing">
									<ul class="listing">
										<li>
											<label> <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose" /> </label>
										</li>
									</ul>
								</div>
							</div>
							
						</div>
					</form:form>
					<script src="/LGD/JavaScriptServlet"></script>
				</div>
			</div>
		 </body>
	</html>