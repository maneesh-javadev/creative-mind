<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%> 
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" language="javascript">

/* function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
}  */
</script>
<script type="text/javascript" src="js/add_designation.js"></script>

<script type="text/javascript" src="js/designation_hierarchy.js"></script>

<title><spring:message htmlEscape="true" code="Label.Title"></spring:message></title>

<script src="js/common.js"></script>
<script type="text/javascript" src="js/add_designation.js"></script>
<script type="text/javascript" src="js/designation_hierarchy.js"></script> 
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />

<link href="css/errorLB.css" rel="stylesheet" type="text/css" />
<link href="css/addDesignation.css" rel="stylesheet" type="text/css" />

<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDesignationService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
	
<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
</script>	
</head>
<body onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);">

	<div class="overlay" id="overlay1" style="display: none;"></div>
	<div class="box" id="box1">
		<a class="boxclose" id="boxclose1"></a>
		<div>
			<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<div class="success"></div>
							</center></td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div></td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center>
									<c:out value="${family_msg}" escapeXml="true"></c:out>
								</center>
							</div></td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><div class="failur"></div></td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center></td>
					</tr>
					<tr>
						<td><div id="failurMsg" class="errorfont">
								<c:out value="${family_error}" escapeXml="true"></c:out>
							</div></td>
					</tr>
				</table>

			</c:if>

		</div>
	</div>

	<div class="box" id="box">
		<a class="boxclose" id="boxclose"></a>
		<div id="validate_error">
			<table>
				<tr>
					<td rowspan="2"><div class="errorImg"></div></td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div></td>
				</tr>
				<tr>
					<td><div class="errorfont"><spring:message htmlEscape="true" code="Error.entermandatoryfield"></spring:message></div>
					</td>
				</tr>
			</table>

		</div>
	</div>
	<div id="frmcontent">
		<div class="frmhd">


			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message htmlEscape="true"
							code="Label.CREATEDESIGNATIONSTATE"></spring:message></td>
					<td align="right"><a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a></td>
					<%--//these links are not working  <td width="50" align="right" valign="middle"><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></td>
					<td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a> --%>
					</td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="designaton_master_state.htm" method="post" id="designationForm" commandName="designationForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="designaton_master_state.htm"/>" />
				<input type="hidden" name="bindex" id="bindex"  />
				<input type="hidden" name="opeation" id="opeation" />
				<div>
					<div style="margin: 20px; background: #F7F7F7; padding: 10px">
						<div class="frmtxt">
						<table width="100%" class="tbl_no_brdr">
                               <tr>
                               <td width="10%">&nbsp;</td>
                               <td width="60%">
                                 <font color="red"><form:errors path="*" cssClass="errorBox" ></form:errors></font>
                            	 	<div id="errormsg" class="errormsg"></div>
                               </td>
                               </tr>
                           	</table>
                           	<input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("slcCode")%>' />
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" rowspan="2">&nbsp;</td>
									<td width="86%" style="padding: 0px">
										<table width="400" class="tbl_no_brdr">
											<tr>
											<td><label id="lblOrgTypelIst"><spring:message htmlEscape="true" code="Label.ORGTYPELIST"></spring:message>
											</label> <span class="errormsg">*</span> <br />
											    <form:select
													path="orgType" style="width: 200px" id="orgType"
													class="combofield"
													onchange="getOrgbyTypeAtLevel(this.value,'S','${hdnStateCode}')"
													htmlEscape="true"
													htmlonblur="vlidateOnblur('orgType','1','15','c');">
													<form:option value="0">
														<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
													</form:option>
													<form:options items="${orgType}" itemLabel="orgType"
														itemValue="orgTypeCode" />
												</form:select>
												<div id="orgType_error" class="error"><spring:message code="error.select.ORGTYPE" htmlEscape="true"/></div>
													<div id="orgType_msg" style="display:none"><spring:message code="error.select.ORGTYPE" htmlEscape="true"/></div>
													<div class="errormsg" id="orgType_error1"><form:errors path="orgType" htmlEscape="true"/></div>   
													<div class="errormsg" id="orgType_error2" style="display: none" ></div>
												</td>
												
											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td><label id="lblOrgList"><spring:message
															htmlEscape="true" code="Label.ORGLIST"></spring:message>
												</label> <span class="errormsg">*</span> <br /> <form:select
														id="orgCode" path="orgCode" style="width: 200px"
														class="combofield" onchange="getOrgAtLevel(this.value,'S')"
														onblur="vlidateOnblur('orgCode','1','15','c');">
														<form:option value="0">
															<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
														</form:option>
													</form:select></label>
													<div id="orgCode_error" class="error"><spring:message code="Label.SELORG" htmlEscape="true"/></div>
														<div id="orgCode_msg" style="display:none"><spring:message code="Label.SELORG" htmlEscape="true"/></div>
														<div class="errormsg" id="orgCode_error1"><form:errors path="orgCode" htmlEscape="true"/></div>   
														<div class="errormsg" id="orgCode_error2" style="display: none" ></div>
													</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td><label id="lblLevel"><spring:message
															htmlEscape="true" code="Label.SELECTLEVEL" /> </label><span
													class="errormsg">*</span><br /> <label> <form:select
															path="locatedAtLevel" id="locatedAtLevel"
															class="combofield" style="width:200px" htmlEscape="true"
															onchange="getOrganizationDesignation(orgCode.value,this.value)"
															onblur="setMaxDesg()">
															<form:option value="0">
																<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
															</form:option>
														</form:select> </label>
													<div style="height: 15px; padding-top: 3px;"
														class="errormsg"></div>
														<div id="locatedAtLevel_error" class="error"><spring:message code="Label.SELECTLEVEL" htmlEscape="true"/></div>
														<div id="locatedAtLevel_msg" style="display:none"><spring:message code="Label.SELECTLEVEL" htmlEscape="true"/></div>
														<div class="errormsg" id="locatedAtLevel_error1"><form:errors path="locatedAtLevel" htmlEscape="true"/></div>   
														<div class="errormsg" id="locatedAtLevel_error2" style="display: none" ></div>	
													</td>
											</tr>
										</table>
										<div style="height: 15px; padding-top: 3px;" class="errormsg"></div>
									</td>
								</tr>
							</table>

							<div class="clear" style="height: 25px"></div>
							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message htmlEscape="true" code="Label.TD"></spring:message>
								</div>
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td width="13%" rowspan="2">&nbsp;</td>
										<td width="87%" style="padding: 0px">
											<table width="300" class="tbl_no_brdr">
												<tr>
													<td align="left" width="200"><Label><spring:message	htmlEscape="true" code="Label.NE"></spring:message></Label><span class="errormsg">*</span></td>
													<td align="left" width="200"><Label><spring:message	htmlEscape="true" code="Label.NL"></spring:message></Label><span class="errormsg">*</span></td>
													<td width="100">&nbsp;</td>
												</tr>
												<tr>
													<td><label id="lbldesg0" style="color: red"></label> <form:input
															path="desgName" maxlength="50" id="desgName0" type="text" onkeypress="validateforTextandspace();"
															class="frmfield" style="width: 200px" />&nbsp;&nbsp;
													</td>
													<td><label id="lblreport0" style="color: red"></label>
														<form:input path="desgNameLocal" id="desgNameLocal0"
															type="text" maxlength="50" class="frmfield" style="width: 200px" />
															<div style="height: 15px; padding-top: 3px;" class="errormsg"></div></td>
												</tr>
												
											</table>
										</td>
									</tr>
								</table>
							</div>
							<div class="clear" style="height: 25px"></div>
							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message htmlEscape="true" code="Label.DEGOTHER"></spring:message>
								</div>
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td width="13%" rowspan="2">&nbsp;</td>
										<td width="87%" style="padding: 0px">
											<table width="300" class="tbl_no_brdr">
											</table>
											<div style="height: 15px; padding-top: 3px;" class="errormsg"></div>
										</td>
									</tr>
									<tr>
										<td style="padding: 0px" />
										<table width="650" class="tbl_no_brdr">
											<tr>
												<td align="left" width="200"><label><spring:message	htmlEscape="true" code="Label.NE"></spring:message></label><span class="errormsg">*</span></td>
												<td align="left" width="200"><label><spring:message	htmlEscape="true" code="Label.NL"></spring:message></label><span class="errormsg">*</span></td>
												<td width="120"><label><spring:message htmlEscape="true" code="Label.ISMULTI"></spring:message></label></td>
												<td width="120">&nbsp;&nbsp;<label><spring:message htmlEscape="true" code="Label.ISCONTRACTPERMENENT"></spring:message></label></td>
												
											</tr>
											<tr>
												<td><label id="lbldesg0" style="color: red"></label><form:input
														path="desgName" maxlength="100" id="desgName1" type="text" onkeypress="validateforTextandspace();"
														class="frmfield" style="width: 200px" />
												</td>
												<td><label id="lblreport0" style="color: red"></label>
													<form:input path="desgNameLocal" id="desgNameLocal1" maxlength="100"
														type="text" class="frmfield" style="width: 200px" />
												</td>
												<td align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="isMultiple1" id="chkbx1" />
													<input type="hidden" name="isMultiple" id="isMultiple"></input>
												</td>
												<td align="center"><input type="checkbox" name="isContractPer1" id="contractPer1" />
													<input type="hidden" name="isContractPer" id="isContractPer"></input>
												</td>	
												<td width="100" align="center"><input type="button"
													name="Submit32" onclick="changeItOrgan(dynamicDiv,'dynamic')"
													value=<spring:message htmlEscape="true" code="Button.ACHILD"></spring:message> /><br />
													<br /></td>
											</tr>
											
											<tr>
												<td><label id="lbldesg0" class="mndt"></label>
												<form:input path="designationCode" name="designationCode" id="designationCode0" type="hidden" />
												<td colspan="4"> <form:input path="designationCode" name="designationCode" id="designationCode1" type="hidden"/> 
												<input type="hidden" id="deletedDesignation" name="deletedDesignation"/>
												<form:input path="modifiedDesignation" type="hidden" id="modifiedDesignation" name="modifiedDesignation"/><br/>
												</td>
											</tr>
												
											<tr>
												<td colspan="5">
													<div id="dynamicDiv" style="width: 100%"></div>
												</td>
											</tr>
										</table>
									</tr>
								</table>
							</div>
							
							<table width="100%" class="tbl_no_brdr">

								<tr>
									<td>&nbsp;</td>
									<td colspan="3"><div style="padding: 10px 0px 10px 0px"
											align="center">
											<label> <input type="submit" name="Submit" id="btnSave" style="width: 100px" onclick="return onSubmitDesignationAtCenterFinal()"
												value="<spring:message code="Button.SUBMIT" htmlEscape="true"></spring:message>" /></label>
											 <label><input
												type="hidden" id="orgDesigDetails" /> </label> <label> <input
												type="button" name="Submit2" class="btn"
												value="<spring:message code="Button.CLEAR"></spring:message>"
												onclick="javascript:location.href='designaton_master_center.htm?<csrf:token uri='designaton_master_center.htm'/>';" />
											</label>
											<label>
												<input type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'" />
											</label>
										</div>
									
									
									<div id="designationDiv" style="visibility:false">
               						 
               						 <table border="1" width="346" cellspacing="0" cellpadding="0" id="mtab">
										<thead>
											
										</thead>
										<tbody id="Designationrows"> </tbody>
										
										</table>
									</div> 	
										  
									</td>
								</tr>
							</table>
							
							
							
							
							<div style="height: 15px; padding-top: 3px;" class="errormsg"></div>
						</div>
					</div>
				</div>
		</form:form>
		<script src="/LGD/JavaScriptServlet"></script>
		</div>
	
	</div>
</body>
</html>