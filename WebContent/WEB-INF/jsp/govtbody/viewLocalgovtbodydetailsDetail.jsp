<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<%@ include file="../common/taglib_includes.jsp"%>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrStateService.js'></script>
<title><spring:message htmlEscape="true" code="Label.VIEWLOCALGOVTBODYDETAILS"></spring:message></title>
<script>
function openFileByPath(filePath) {		
	lgdDwrStateService.openFile(filePath, {
		  callback: openFileCallBack  
	});
}

function openFileCallBack(data) {
	if(data == null ) {
		alert("File has been moved or deleted.");
	} else {
	if(data.length>5) {
		var d=data.substring(0,5);
		if(d=="ERROR") {
			 alert("File has been moved or deleted.");
		} else {
				var form = document.downloadForm;
				var tempTarget = form.target;
				var tempAction = form.action;
				form.target = 'download_page';
				form.method = "post";
				form.action = "viewGovtOrder.do?<csrf:token uri='viewGovtOrder.do'/>";
				form.govfilePath.value = data;
				form.fileDisplayType.value = "inline";

				if ($.browser.msie) {
					p_windowProperties = "width=700px,height=450px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised";
					newWindow = window.open('', 'download_page', p_windowProperties);
					if (newWindow) {
						form.submit();
						form.target = tempTarget;
						form.action = tempAction;
						newWindow.focus();
					} else {
						alert('You must allow popups for this to work.');
					}
				} else if ($.browser.mozilla) {
					form.submit();
				}
				
				else {
					NewWindow = window.open('',"download_page","width=903px,height=527px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised") ;
					form.submit();
				}crea
			}
		} else {
			alert("File has been moved or deleted.");
		}
	}
}
</script>
</head>
<body>
	
		<div id="frmcontent">
			
		 <div class="frmhd">
		      <h3 class="subtitle">&nbsp;</h3>
				        <ul class="listing">
				        <li>
						<%--//these links are not working <a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message> --%>
						</a>
						</li>
						</ul>
						
			  </div>
							<div class="frmpnlbrdr">
								<form:form action="ViewLocalBodyforPRIPost.htm" method="POST" commandName="viewlocalbody" name="downloadForm" htmlEscape="true">
									<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="ViewLocalBodyforPRIPost.htm"/>"/>
									<input type="hidden" name="govfilePath"/>
									<input type="hidden" name="fileDisplayType"/>
									
									<div class="frmpnlbg">
										<div class="frmtxt">
											<div class="frmhdtitle">
											<spring:message htmlEscape="true"
													code="Label.VIEWLOCALGOVTBODYDETAILS"></spring:message>	
											</div>
											
									

												<div class="frmpnlbg">
													<div class="frmtxt">
														<div class="frmhdtitle">
															<spring:message htmlEscape="true" code="Label.GENERALLOCALGOVTBODYDETAILS"></spring:message>
														</div>


														<div class="search_criteria">
															<ul class="blocklist">
															<li>
																<label><spring:message code="Label.LOCALBODYCODE" htmlEscape="true"/></label>
																<esapi:encodeForHTML>${viewlocalbody.localBodyCode}</esapi:encodeForHTML>
																
															</li>

															<li>
																<label><spring:message code="Label.LOCALBODYNAMEINENG" htmlEscape="true"/></label>
																<esapi:encodeForHTML>${viewlocalbody.localBodyNameEnglish}</esapi:encodeForHTML>
															</li>

															<li>
																<label><spring:message code="Label.NAMEINLOCALLANGUAGE" htmlEscape="true"/></label>
																<esapi:encodeForHTML>${viewlocalbody.localBodyNameLocal}</esapi:encodeForHTML>
															</li>

															<li>
																<label><spring:message code="Label.LOCALBODYALIASENGLISH" htmlEscape="true"/></label>
																<esapi:encodeForHTML>${viewlocalbody.aliasEnglish}</esapi:encodeForHTML>
															</li>

															<li>
																<label><spring:message
																			code="Label.LOCALBODYALIASLOCAL" htmlEscape="true"/></label>
																<esapi:encodeForHTML>${viewlocalbody.aliasLocal}</esapi:encodeForHTML>
															</li>

															<li>
																<label><spring:message
																			code="Label.STATESPECIFICCODE" htmlEscape="true"/></label>
																<esapi:encodeForHTML>${viewlocalbody.sscode}</esapi:encodeForHTML>

																</li>
															</ul>
														</div>
													</div>
												</div>

											

												<div class="frmpnlbg">
													<div class="frmtxt">
														<div class="frmhdtitle">
															<spring:message code="Label.GOVTORDERDETAILS"
																htmlEscape="true"></spring:message>
														</div>
														
															<%-- <tr class="tblRowB">
																<td align="left" width="28%"><label><spring:message
																			code="Label.ORDERCODE" /></label>&nbsp; &nbsp;</td>
																<td align="left" width="72%">
																	${viewlocalbody.orderCode}</td>
															</tr> --%>
															
															
															<div class="search_criteria">
															<ul class="blocklist">
															<li>
																<label><spring:message
																			code="Label.ORDERNO" htmlEscape="true"/></label>
																
																	<esapi:encodeForHTML>${viewlocalbody.orderNo}</esapi:encodeForHTML>
															
															</li>
															<li>	
																<label><spring:message
																			code="Label.ORDERDATE" htmlEscape="true"/></label>

																<fmt:parseDate
																		var="formatedDate" value="${viewlocalbody.orderDate}"
																		type="DATE" pattern="yyyy-MM-dd" /> <fmt:formatDate
																		value="${formatedDate}" pattern="dd-MMM-yyyy"
																		type="DATE" />


															</li>
															<li>
																<label><spring:message
																			code="Label.EFFECTIVEDATE" htmlEscape="true"/></label>
																<fmt:parseDate
																		var="formatedDate"
																		value="${viewlocalbody.effectiveDate}" type="DATE"
																		pattern="yyyy-MM-dd" /> <fmt:formatDate
																		value="${formatedDate}" pattern="dd-MMM-yyyy"
																		type="DATE" />
															</li>	
														
															<li>	
															
																<label><spring:message
																			code="Label.GAZPUBDATE" htmlEscape="true"/></label>
																<fmt:parseDate
																		var="formatedDate"
																		value=<c:out value="${viewlocalbody.gazPubDate}" escapeXml="true"></c:out>' type="DATE"
																		pattern="yyyy-MM-dd" /> <fmt:formatDate
																		value="${formatedDate}" pattern="dd-MMM-yyyy"
																		type="DATE" />
															</li>
															
															<li>
															<c:if test="${viewlocalbody.govtOrderFileName != null}">
																
																	<label><spring:message	code="Label.filename" text="File name" htmlEscape="true"/></label>
																	<a href="#" onclick="openFileByPath('${viewlocalbody.govtOrderFileLocation}');"><esapi:encodeForHTML>${viewlocalbody.govtOrderFileName}</esapi:encodeForHTML></a>
																
															</c:if>
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

																<td width="30%" align="center" class="lblBold"><label
																	id="lbllatitude"><spring:message
																			code="Label.LATITUDE" htmlEscape="true"></spring:message>
																</label></td>

																<td width="30%" align="left" class="lblBold"><label
																	id="lbllongitude"><spring:message
																			code="Label.LONGITUDE" htmlEscape="true"></spring:message>
																</label></td>
																<td width="30%" align="center" class="lblBold"></td>

															</tr>
															<c:set var="str1" value="${viewlocalbody.coordinates}" />
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
																			<td width="30%" align="left" class="lblBold"><c:out
																					value="${num}" escapeXml="true"></c:out></td>
																		</c:if>
																		<c:set var="count" value="${count+1}"></c:set>

																	</c:forEach>
																	<td width="30%" align="center" class="lblBold">&nbsp;</td>
																</tr>
															</c:forEach>

														</table>
													</div>


												</div>

											
										<c:if test="${viewlocalbody.mapFileName != null}">
											
												<div class="frmpnlbg">
													<div class="frmtxt">
														<div class="frmhdtitle">
															<spring:message code="Label.Map" text="Map Details"></spring:message>
														</div>
														<table width="100%" class="tbl_no_brdr">
															<tr>
																<td width="30%" align="center" class="lblBold">
																<label id="mapfilename"><spring:message code="Label.mapfilename" text="Map File Name" htmlEscape="true"></spring:message>
																</label></td>

																<td width="30%" align="left" class="lblBold">
																<label id="lbllongitude"><spring:message code="Label.mapfilepath" text="Map Entity Type" htmlEscape="true"></spring:message></label>
																</td>
																<td width="30%" align="center" class="lblBold"></td>
															</tr>
															<tr>
																<td width="30%" align="center" class="lblBold"><a href="#" onclick="openFileByPath('${viewlocalbody.mapFileLocation}')"><esapi:encodeForHTML>${viewlocalbody.mapFileName}</esapi:encodeForHTML></a></td>
																<td width="30%" align="left" class="lblBold"><esapi:encodeForHTML>${viewlocalbody.mapEntityType}</esapi:encodeForHTML></td>
																<td width="30%" align="center" class="lblBold">&nbsp;</td>
															</tr>
														</table>
													</div>


												</div>

											
										</c:if>
									
								</div>

								<div class="btnpnl">
									<input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose"/>
								</div>
											
			
			
			</div>
			
			 </form:form>
			 <script src="/LGD/JavaScriptServlet"></script>
			</div>
			
			
		</div>

</body>
</html>