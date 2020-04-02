<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/local_govt_setup.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/lgd_css.css" rel="stylesheet" type="text/css" />
<script src="js/lgd_js.js"></script>
<script src="js/jquery.js"></script>
<script src="js/govtorder.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js"></script>
<script src="js/local_govt_setup.js"></script>

</head>

<body> <!-- onload="refreshpage3()"> -->
	<form:form action="local_gov_setup_step5.htm" name="lgsform" method="post" commandName="lGSetupForm" id="lgsform">
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="local_gov_setup_step5.htm"/>"/>
		<div id="tab3">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td valign="top" style="padding: 0px">
						<div id="frmcontent">
							<div class="frmhd">
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td><spring:message htmlEscape="true"  code="Label.LGGS"></spring:message></td>
										<%-- //this link is not working <td align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a></td> --%>
									</tr>
								</table>
							</div>
							<div class="frmpnlbg">
									<div class="frmtxt">
											<table width="100%" class="tbl_no_brdr">
												<tr>
													<td width="14%" rowspan="4">
														<div id="dynamicDiv4"></div>
													</td>
													<td width="86%">	
														<div id="dynamicDiv4_Addons"
															 style="background: #EEEEEE; padding: 3px; margin: 0px 10px 0px 10px;
								                             width:80%;visibility:hidden">
								                        </div>
													</td>
												</tr>
												<tr>
															<table width="100%">
															<tr>
															<td width="15%"></td>
															<td width="20%"><input type="button" value="Save" onclick="submitLast()" /></td>
															<td width="65%"></td>
															</tr>
															</table>
												</tr>
												<tr>
													<td width="14%" rowspan="4">&nbsp;</td>
													<td width="86%" style="visibility: hidden;display:none"><input type="hidden" id="heading4" value='<spring:message htmlEscape="true"  code="Label.LGT"></spring:message>'></input>
													<spring:message htmlEscape="true"  code="Label.LGT"></spring:message>
														<form:select id="lgt2" path="lBTList">
														<form:option value="">- - Select - -</form:option>
														<form:options items="${listLBT}" itemLabel="localbodyName" itemValue="localbodyCode" />
														</form:select>&nbsp;&nbsp;
													</td>
												</tr>
												<tr style="visibility:hidden;display:none">
													<td><label id="lbl_E"><spring:message htmlEscape="true"  code="Label.LGSTIE"></spring:message> </label>
														 <input id="dtxtE0" name="subTypeName" 
															type="text" class="frmfield" style="width: 220px" />
														&nbsp;&nbsp;<span style="height: 15px; padding-top: 3px;"
															class="errormsg"></span>
														<label id="lbl_L"> <spring:message htmlEscape="true"  code="Label.LGSTILL"></spring:message> </label>
														<input id="dtxtL0" name="subTypeLocal"
															type="text" class="frmfield" style="width: 220px" />
														<span style="height: 15px; padding-top: 3px;" 
															class="errormsg"></span>
														<label>
															<input type="button" name="addmore" id="addmore" value="Add More" onclick="changeTab4(dynamicDiv4)" />
														</label>
													</td>
												</tr>
												<tr style="visibility: hidden;display:none">
													<td>
														<input type="button" value="    Save    "  onclick="persist4()"/>
														<div id="div4" style="width:84%" align="right">
															<input type="text" name="subTyp" id="tab4txt"/>
														
														</div>
													</td>
												</tr>
											</table>
											<table>
												<tr>
													<c:forEach var="subTypeDetails" items="${subTypeDetails}">
														<td><input type="hidden" name="subTypE" id="subTypcd" value="<c:out value='${subTypeDetails.childOrder}' escapeXml='true'></c:out>"/></td>
														<td><input type="hidden" name="subTypE" id="subTypE" value="<c:out value='${subTypeDetails.subTypeName}' escapeXml='true'></c:out>"/></td></br></br> 
														<td><input type="hidden" name="subTypL" id="subTypL" value="<c:out value='${subTypeDetails.subTypeLocal}' escapeXml='true'></c:out>"/></td>
													</c:forEach>
												</tr>
											</table>
											<div  class="errormsg"></div>
										</div>
								</div>
						</div>
					</td>
				</tr>
			</table>

			
		</div>		
		
	</form:form>
	<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>

</body>
</html>
