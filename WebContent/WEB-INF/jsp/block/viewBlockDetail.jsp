<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
					<h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.MODIFYBLOCK"></spring:message></h3>
						<ul class="listing">
							<%-- this link is not working <li>
								<a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message> </a>			
							</li> --%>
						</ul>
				</div>
							
				<div class="frmpnlbrdr">
					<form:form action="modifyBlockAction.htm" method="POST" commandName="blockView" id="frmModifyBlock">
						 <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyBlockAction.htm"/>"/>
							<h2><font size="3"><esapi:encodeForHTML>${successMsg}</esapi:encodeForHTML></font></h2>
							<div class="frmpnlbg">
								<div class="frmtxt">
									<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.BLOCKDETAIL"></spring:message>
									</div>
									<c:forEach var="listBlockDetails" varStatus="listBlockDetailsRow" items="${blockView.listBlockDetails}">
										<div class="blocklist">
											<ul class="blocklist">
												<li>
													<spring:message htmlEscape="true"  code="Label.BLOCKCODE"></spring:message><br />
													<label class="lblPlain"> <spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].blockCode">
																 <c:out value="${status.value}" escapeXml="true"></c:out>
														</spring:bind> </label>
													<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true"  code="Label.BLOCKVER"></spring:message><br />
													<label class="lblPlain"> <spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].blockVersion">
																<c:out value="${status.value}" escapeXml="true"></c:out>
														</spring:bind> </label>
													<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true"  code="Label.BLOCKNAMEINENGLISH"></spring:message><br />
													<label class="lblPlain"> <spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].blockNameEnglish">
																<esapi:encodeForHTML>${status.value}</esapi:encodeForHTML>
														</spring:bind> </label>
													<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true"  code="Label.BLOCKNAMEINLOCAL"></spring:message><br />
													<label class="lblPlain"> <spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].blockNameLocal">
																 <c:out value="${status.value}" escapeXml="true"></c:out>
														</spring:bind> </label>
													<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true"  code="Label.BLOCKALIASENGLISH"></spring:message><br />
													<label class="lblPlain"> <spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].aliasEnglish">
															<c:out value="${status.value}" escapeXml="true"></c:out>
														</spring:bind> </label>
													<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true"  code="Label.BLOCKALIASLOCAL"></spring:message><br />
													<label class="lblPlain"> <spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].aliasLocal">
																<c:out value="${status.value}" escapeXml="true"></c:out>
														</spring:bind> </label>
													<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true"  code="Label.STATESPECIFICCODE"></spring:message><br />
													<label class="lblPlain"> <spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].ssCode">
																<c:out value="${status.value}" escapeXml="true"></c:out>
														</spring:bind> </label>
													<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true"  code="Label.DISTRICTHEADQUARTERENGLISH"></spring:message><br />
													<label class="lblPlain"> <spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].headquarterName">
																<c:out value="${status.value}" escapeXml="true"></c:out>
														</spring:bind> </label>
													<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true"  code="Label.SUBDISTRICTHEADQUARTERLOCAL"></spring:message><br />
													<label class="lblPlain"> <spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].headquarterNameLocal">
																<c:out value="${status.value}" escapeXml="true"></c:out>
														</spring:bind> </label>
													<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true"  code="Label.ORDERNO"></spring:message><br />
													<label class="lblPlain"><spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].orderNocr">
													<c:out value="${status.value}" escapeXml="true"></c:out>
													</spring:bind>
													</label>	
													<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true"  code="Label.ORDERDATE"></spring:message><br />
													<label>
													<spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].orderDatecr">
													<c:out value="${status.value}" escapeXml="true"></c:out>
									
													</spring:bind>
													</label>
													<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true"  code="Label.EFFECTIVEDATE"></spring:message><br />
													<label>
													  <spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].ordereffectiveDatecr">
														<c:out value="${status.value}" escapeXml="true"></c:out>
													
														</spring:bind>
													  
													</label>
													<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true"  code="Label.GAZPUBDATE"></spring:message><br />
													<label>
													
													   <spring:bind path="blockView.listBlockDetails[${listBlockDetailsRow.index}].gazPubDatecr">
														<c:out value="${status.value}" escapeXml="true"></c:out>
														
														</spring:bind>
													
													</label>
													<div class="errormsg"></div>
												</li>
											</ul>										
										</div>
									</c:forEach>
								</div>
								<div class="btnpnl">
									<ul class="listing">
										<li>
											<label> <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose"/> </label>
										</li>
									</ul>
								</div>
							</div>
					</form:form>
					<script src="/LGD/JavaScriptServlet"></script>
				</div>
			</div>
		
</body>
</html>