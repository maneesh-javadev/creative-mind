<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>

<%!String contextPath;%>
<% contextPath = request.getContextPath(); %>
<head>
<meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
	<title><spring:message code="Label.CREATENEWLOCALGOVTBODY" htmlEscape="true" ></spring:message></title>
	
</head>

<body>

	<section class="content">
		<div class="row">
			<section class="col-lg-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title"></h3>
					</div>
					<form name="downloadForm">
						<input type="hidden" name="govfilePath"/>
						<input type="hidden" name="fileDisplayType"/>
					</form>
					<div class="box-body">
						<div class="overlay" id="overlay1" style="display: none;"></div>
						<div id="box1">
							<a class="boxclose" id="boxclose1"></a>
							<div>
								<c:if test="${!empty param.family_msg}">
									<table>
										<tr>
											<td rowspan="2"><center>
													<div class="success"></div>
												</center>
											</td>
					
											<td><div class="helpMsgHeader" style="width: 275px;">
													<h4>Success Message</h4>
												</div>
											</td>
										</tr>
										<tr>
											<td><div id="successMsg" class="successfont">
													<center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center>
												</div></td>
										</tr>
									</table>
					
								</c:if>
					
								<c:if test="${!empty family_error}">
					
									<table>
										<tr>
											<td rowspan="2"><div class="failur"></div>
											</td>
					
											<td><center>
													<div class="helpMsgHeader" style="width: 275px;">
														<b>Failure Message</b>
													</div>
												</center>
											</td>
										</tr>
										<tr>
											<td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="true"></c:out></div>
											</td>
										</tr>
									</table>
					
								</c:if>
					
							</div>
						</div>
						<div id="box">
							<a class="boxclose" id="boxclose"></a>
							<div id="validate_error">
								<table>
									<tr>
										<td rowspan="2"><div class="errorImg"></div>
										</td>
										<td><div class="helpMsgHeader" style="width: 275px;">
												<h4>Error Message</h4>
											</div>
										</td>
									</tr>
									<tr>
										<td><div class="errorfont"><spring:message htmlEscape="true" code="Error.entermandatoryfield"></spring:message></div>
										</td>
									</tr>
								</table>
					
							</div>
						</div>
						<div id="helpDiv" class="helpMessage">
							<div class="helpheader">
								<h4>Help Message</h4>
							</div>
							<div id="helpMsgText" class="helptext"></div>
						</div>
	                </div>
		            <div class="box-header with-border">
						<h3 class="box-title"><spring:message htmlEscape="true" code="Label.MODIFYGOVTLOCALBODY"></spring:message></h3>
					</div>
					<a href="#" class="frmhelp"><spring:message code="Label.HELP" htmlEscape="true"></spring:message> </a>
					<form:form action="modifyLocalBodyForCorrection.htm" method="POST" commandName="localGovtBodyForm" enctype="multipart/form-data" id="lgdcorrectionform" name="lgdcorrectionform" class="form-horizontal">
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyLocalBodyForCorrection.htm"/>" />
						<div class="box-body">
							<div class="box-header subheading">
			                  <h4><spring:message htmlEscape="true"  code="Label.LOCALBODYDETAILS"></spring:message></h4>
			                </div>
			                <form:hidden path="localBodyCode" id="hdnLbCode" value="${localGovtBodyForm.localBodyCode}" htmlEscape="true" /> 
							<form:hidden path="lbType" id="hdnLbTypeCode" value="${localGovtBodyForm.lbType}" htmlEscape="true" />
							<form:hidden path="operationCode" id="operationCode" value="${localGovtBodyForm.operationCode}" htmlEscape="true" />
							<form:hidden path="localbodyNameEnghidden" id="localbodyNameEnghidden" value="${localGovtBodyForm.localbodyNameEnghidden}" htmlEscape="true" />
							<form:hidden path="localbodyNameAliasEnghidden" id="localbodyNameAliasEnghidden" value="${localGovtBodyForm.localbodyNameAliasEnghidden}" htmlEscape="true" />
							<input type="hidden" id="govtfilecount" name="govtfilecount" value="<c:out value='${govtfilecount}' escapeXml='true'></c:out>"></input>
							<input type="hidden" id="mapfilecount" name="mapfilecount" value="<c:out value='${mapfilecount}' escapeXml='true'></c:out>"></input>
							<input type="hidden" id="warningEntiesFlag" name="warningEntiesFlag" value="<c:out value='${warningEntiesFlag}' escapeXml='true'></c:out>"></input>
							<input type="hidden" id="category" name="category" value="<c:out value='${category}' escapeXml='true'></c:out>"></input>
							<input type="hidden" id="orderCodeHidden" name="orderCodeHidden" value="<c:out value='${orderCodeHidden}' escapeXml='true'></c:out>"></input>
							<input type="hidden" id="Coordinates" name="Coordinates" value="<c:out value='${Coordinates}' escapeXml='true'></c:out>"></input>
							
		            	</div>
					</form:form>
					
		        </div>
		    </section>
		</div>
	</section>



	

	

	


	<div id="frmcontent">

		<div class="frmpnlbrdr">
			<form:form action="modifyLocalBodyForCorrection.htm" method="POST" commandName="localGovtBodyForm" enctype="multipart/form-data" id="lgdcorrectionform" name="lgdcorrectionform">
				
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyLocalBodyForCorrection.htm"/>" />
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<label><spring:message code="Label.LOCALBODYDETAILS" htmlEscape="true"></spring:message></label>
							</div>
							<form:hidden path="localBodyCode" id="hdnLbCode" value="${localGovtBodyForm.localBodyCode}" htmlEscape="true" /> 
							<form:hidden path="lbType" id="hdnLbTypeCode" value="${localGovtBodyForm.lbType}" htmlEscape="true" />
							<form:hidden path="operationCode" id="operationCode" value="${localGovtBodyForm.operationCode}" htmlEscape="true" />
							<form:hidden path="localbodyNameEnghidden" id="localbodyNameEnghidden" value="${localGovtBodyForm.localbodyNameEnghidden}" htmlEscape="true" />
							<form:hidden path="localbodyNameAliasEnghidden" id="localbodyNameAliasEnghidden" value="${localGovtBodyForm.localbodyNameAliasEnghidden}" htmlEscape="true" />
							<input type="hidden" id="govtfilecount" name="govtfilecount" value="<c:out value='${govtfilecount}' escapeXml='true'></c:out>"></input>
							<input type="hidden" id="mapfilecount" name="mapfilecount" value="<c:out value='${mapfilecount}' escapeXml='true'></c:out>"></input>
							<input type="hidden" id="warningEntiesFlag" name="warningEntiesFlag" value="<c:out value='${warningEntiesFlag}' escapeXml='true'></c:out>"></input>
							<input type="hidden" id="category" name="category" value="<c:out value='${category}' escapeXml='true'></c:out>"></input>
							<input type="hidden" id="orderCodeHidden" name="orderCodeHidden" value="<c:out value='${orderCodeHidden}' escapeXml='true'></c:out>"></input>
							<input type="hidden" id="Coordinates" name="Coordinates" value="<c:out value='${Coordinates}' escapeXml='true'></c:out>"></input>
											
									<ul class="blocklist">
									
								<c:forEach var="localBodyDetails"
									varStatus="localBodyUrbanNameListRow"
									items="${localGovtBodyForm.localBodyDetails}">

									<li>
										<spring:bind
											path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].isdisturbed">
											<c:if test="${status.value}">
												<spring:message
														code="Label.DISTURBEDSTATE" htmlEscape="true" ></spring:message> <br /> <label
													class="lblPlain"> <img src="images/red_flg.png"
														width="13" height="9" />
												</label>
											</c:if>
										</spring:bind>
									</li>

									<li>

										<br /> <br /> <label> <spring:message
													code="Label.NAMEOFLOCALBODY" htmlEscape="true"  ></spring:message>
										</label><br /> <label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameEnglish">
													<c:out value="${status.value}" escapeXml="true"/>

												</spring:bind>
										</label>
									</li>

									<li>
										<br /> <br /> <label> <spring:message
													code="Label.NAMEINLOCALLANGUAGE" htmlEscape="true" ></spring:message>
										</label><br /> <label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameLocal">
													<c:out value="${status.value}" escapeXml="true" />

												</spring:bind>
										</label>
									</li>
									<li>
										<br /> <br /> <label><spring:message
													code="Label.LOCALBODYALIASENGLISH" htmlEscape="true"  ></spring:message> </label><br />
											<label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].aliasNameEnglish">
													<c:out value="${status.value}" escapeXml="true" />
												</spring:bind>
										</label>
									</li>
									<li>
										<br /> <br /> <label> <spring:message
													code="Label.LOCALBODYALIASLOCAL" htmlEscape="true"  ></spring:message>
										</label><br /> <label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].alisNameLocal">
													<c:out value="${status.value}" escapeXml="true"/>
												</spring:bind>
										</label>
									</li>

									<spring:bind
										path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].level"  htmlEscape="true" >
										<input type="hidden" class="frmfield"
											name="<c:out value="${status.expression}" />"
											value="<c:out value="${status.value}" escapeXml="true" />" />

									</spring:bind>
									
									<spring:bind
										path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameEnglish"  htmlEscape="true">
										<input type="hidden" class="frmfield"
											name="<c:out value="${status.expression}" />"
											value="<c:out value="${status.value}" escapeXml="true" />" />
									</spring:bind>
									<spring:bind
										path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameLocal"  htmlEscape="true">
										<input type="hidden" class="frmfield"
											name="<c:out value="${status.expression}" />"
											value="<c:out value="${status.value}" escapeXml="true" />" />
									</spring:bind>
											
									<spring:bind
										path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].aliasNameEnglish"  htmlEscape="true">
										<input type="hidden" class="frmfield"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" escapeXml="true" />" />
									</spring:bind>
										
									<spring:bind
										path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].alisNameLocal"  htmlEscape="true">
										<input type="hidden" class="frmfield"
											name="<c:out value="${status.expression}" />"
											value="<c:out value="${status.value}" escapeXml="true" />" />
									</spring:bind>
									<li>
										
											<c:if test="${warningFlag==true}">
												<label><spring:message
													code="Label.WARNINGFLAGSTATUS" htmlEscape="true"></spring:message> </label><br/>
													<img src="images/ylw_flg.png" width="13" height="9" />
											</c:if>
										</li>
									
								</c:forEach>


							</ul>
						</div>
					</div>


					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"  ></spring:message>
							</div>

							
							<form:hidden path="govtOrderConfig" value="${localGovtBodyForm.govtOrderConfig}" id="hdngovtOrderConfig" />
							<form:hidden path="orderCode" id="hdnOrderCode" value="${localGovtBodyForm.orderCode}"/>
									
									       <ul class="blocklist">
									       <li>
											<c:if test="${orderCodeHidden != null}">
											<label>
											<spring:message
												code="Label.ORDERNO" htmlEscape="true"></spring:message> </label>
												<span class="errormsg">*</span><br />
											</c:if>
											
											
											<c:if test="${orderCodeHidden == null}">
											<label>
											<spring:message
												code="Label.ORDERNO" htmlEscape="true"></spring:message> </label>
												<br />
											</c:if>
										<form:input	path="lgd_LBorderNo" id="OrderNo" type="text" 
											class="frmfield" maxLength="60"
											onfocus="validateOnFocus('OrderNo');helpMessage(this,'OrderNo_error');"
										 onblur="hideHelp();vlidateOrderNo('OrderNo','1','60');"
											onkeyup="hideMessageOnKeyPressForOrder('OrderNo')" 
											 onkeypress="return validateaGovtOrderNOforModify(event);"/>
											
											<div id="OrderNo_error" class="errormsg"><spring:message code="error.required.ORDERNUM" htmlEscape="true"></spring:message></div>
									<div id="OrderNo_msg" class="errormsg"><spring:message code="error.required.ORDERINPUTTYPE" text="Please Enter AlphaNumerics Space . / - ( ) Only" htmlEscape="true"/></div>
										
									
											
											
											 <div class="errormsg" id="OrderNo_error1"><form:errors path="lgd_LBorderNo" htmlEscape="true"/></div>  
											<div class="errormsg" id="OrderNo_error2" style="display: none"></div>
										
									</li>
								
								<li>
									
									<c:if test="${orderCodeHidden != null}">
										<label><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message></label>
										<span class="errormsg">*</span><br /> 
									</c:if>
										
									<c:if test="${orderCodeHidden == null}">
										<label><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message>
										</label><br /> 
									</c:if>	
										<form:input
											path="lgd_LBorderDate" id="OrderDate" type="text"
											class="frmfield" 
											onkeypress="validateNumeric();" 
											onfocus="validateOnFocus('OrderDate');helpMessage(this,'OrderDate_msg');"
											onblur="vlidateOnblur('OrderDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('OrderDate')" htmlEscape="true"/>
									
											<div id="OrderDate_error" class="error"><spring:message code="error.required.ORDERDATE" htmlEscape="true"></spring:message></div>
											<div id="OrderDate_msg" style="display:none"><spring:message code="error.required.ORDERDATE" htmlEscape="true"/></div>
											<div class="errormsg" id="OrderDate_error1"><form:errors path="lgd_LBorderDate" htmlEscape="true"/></div>  
											<div class="errormsg" id="OrderDate_error2" style="display: none"></div>
									</li>
								
								<li>
									
									<c:if test="${orderCodeHidden != null}">
									<label><spring:message
												code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message></label><br />
											<form:input	id="EffectiveDateNNull" path="lgd_LBeffectiveDate" type="text" disabled="true" class="frmfield" />
									</c:if>	
									<c:if test="${orderCodeHidden == null}">
									<label><spring:message
												code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> </label> <br />
											<form:input  
											id="EffectiveDate" path="lgd_LBeffectiveDate" type="text"
											class="frmfield" 
											onkeypress="validateNumeric();" 
											onfocus="validateOnFocus('EffectiveDate');helpMessage(this,'EffectiveDate_msg');"
											onblur="vlidateOnblur('EffectiveDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('EffectiveDate')" disabled="true" />
											
										    <div id="EffectiveDate_error" class="error"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"></spring:message></div>
											<%--<div id="EffectiveDate_msg" style="display:none"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"/></div>
											<div class="errormsg" id="EffectiveDate_error1"><form:errors path="effectiveDate" htmlEscape="true"/></div>  
											<div class="errormsg" id="EffectiveDate_error2" style="display: none" ></div> --%>
									</c:if>
									</li>
								
								
								<%-- <c:if test="${govtOrderConfig == 'govtOrderUpload'}"> --%>
								<li>
									<label><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>
									</label> <br /> <form:input id="GazPubDate" path="lgd_LBgazPubDate"
											type="text" class="frmfield" 
											onkeypress="validateNumeric();" onfocus="validateOnFocus('GazPubDate');helpMessage(this,'GazPubDate_msg');"
											onblur="vlidateOnblur('GazPubDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('GazPubDate')"/>
											
											<div id="GazPubDate_error" class="error"><spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true"></spring:message></div>
											<div id="GazPubDate_msg" style="display:none"><spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true"/></div>
											<div class="errormsg" id="GazPubDate_error1"><form:errors path="lgd_LBgazPubDate" htmlEscape="true"/></div>  
											<div class="errormsg" id="GazPubDate_error2" style="display: none"></div> 		
											
										</li>
								 <%--  </c:if> --%>
								  <%-- <tr>
									<td><form:hidden path="govtOrderConfig"
											value="${govtOrderConfig}" id="hdngovtOrderConfig" />
									</td>
								 </tr> --%>
								  
<%-- 
									<td><div id="titleId">
											<form:hidden id="showTitle" path="showTitle" name="showTitle" class="frmfield" />
										</div>
										<div id="hiddentitle"></div>
										<br />
										<div id="hideAttachmentUtilDiv">
											<br /> <br />
											<div id="alreadyAttachedFiles">
									</td> --%>
								
								<%-- <c:if test="${govtOrderConfig == 'govtOrderUpload'}">  --%>
									<li>
										<%@ include file="../common/update_attachment.jspf"%>
									</li>
								<%-- </c:if>  --%>
								<%-- <c:if test="${govtOrderConfig == 'govtOrderGenerate'}"> 
									<tr>
										<td class="tblclear"><%@ include file="../common/update_attachmentforGenCovChange.jspf"%></td>
									</tr>
								</c:if>  --%>
								
								
								<%-- <c:if test="${govtOrderConfig == 'govtOrderGenerate'}"> 
									<tr>
<!-- 										<td width="14%" rowspan="2">&nbsp;</td> -->
										<td width="86%"><label><spring:message htmlEscape="true" code="Label.SELGOT"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:select
												path="templateList" id="templateList" style="width:280px"
												class="combofield" 
												onblur="vlidateOnblur('templateList','1','15','m');hideHelp();"
												onfocus="validateOnFocus('templateList');helpMessage(this,'templateList_msg');"
												onkeyup="hideMessageOnKeyPress('templateList')">
												<form:option value="0"><spring:message htmlEscape="true" code="Error.templateselect" ></spring:message></form:option>
												<form:options items="${templateList}"
													itemLabel="templateNameEnglish" itemValue="templateCode"></form:options>
											</form:select> 
											
												<div id="templateList_error" class="error"><spring:message code="error.blank.template" htmlEscape="true"></spring:message></div>
												<div id="templateList_msg" style="display:none"><spring:message code="error.blank.template" htmlEscape="true"/></div>
												<div class="errormsg" id="templateList_error1"><form:errors path="templateList" htmlEscape="true"/></div>  
												<div class="errormsg" id="templateList_error2" style="display: none" ></div>
												
											</td>
									</tr>
									<tr>
										<td>&nbsp;</td>

									</tr>
								 </c:if> --%>
								
								</ul>
						</div>
					</div>
					<!-- End of Govt Order Details  -->
                      <div>
					<%-- <%@ include file="../common/gis_nodesformodifylocalbody.jspf"%> --%> 
                      </div> 
					
						
						<div class="btnpnl">
							<input type="submit" name="submit" class="bttn" onclick="return validateGovtOrderCovChng();" value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" />
										<%-- <label> <input type="button" class="btn"
											name="Submit9"
											value="<spring:message code="App.DRAFT" htmlEscape="true"></spring:message>"
											onclick="callSaveDraft()" /> </label> --%>
								
										
										<c:if test="${warningEntiesFlag==null}">
											<input type="button" class="btn"
												name="Submit6"
												value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
												onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
										</c:if>		
										
										
										<c:if test="${warningEntiesFlag !=null}">
											<c:if test="${fn:containsIgnoreCase(category,'P')}">
												<input type="button" class="btn"
													name="Submit6"
													value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
													onclick="javascript:location.href='viewResolveEntitiesinDisturbedStatePRI.htm?<csrf:token uri='viewResolveEntitiesinDisturbedStatePRI.htm'/>&warningEntiesFlag=${warningEntiesFlag}';"/>
											</c:if>
											<c:if test="${fn:containsIgnoreCase(category,'T')}">
												<input type="button" class="btn"
													name="Submit6"
													value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
													onclick="javascript:location.href='viewResolveEntitiesinDisturbedStateTra.htm?<csrf:token uri='viewResolveEntitiesinDisturbedStateTra.htm'/>&warningEntiesFlag=${warningEntiesFlag}';"/>
											</c:if>
											<c:if test="${fn:containsIgnoreCase(category,'U')}">
												<input type="button" class="btn"
													name="Submit6"
													value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
													onclick="javascript:location.href='viewResolveEntitiesinDisturbedStateULB.htm?<csrf:token uri='viewResolveEntitiesinDisturbedStateULB.htm'/>&warningEntiesFlag=${warningEntiesFlag}';"/>
											</c:if>
											
										</c:if>	
										
											
									</div>
									
							<div class="clear"></div>
					

					<!-- End here of Coverage Area -->

				</div>

			</form:form>
				<script src="/LGD/JavaScriptServlet"></script>
				<div class="clear"></div>
		</div>
	</div>

	
</body>
</html>