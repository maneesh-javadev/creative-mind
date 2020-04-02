<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="../common/taglib_includes.jsp"%>
<title><spring:message htmlEscape="true" code="Label.MODIFYMINISTRY"></spring:message></title>
<script type="text/javascript" src="js/common.js"></script>
<link rel="stylesheet" href="datepicker/demos.css" />

<script language="javascript">
$(document)
.ready(
		function() {
			$("#frmModifyVillage")
					.validate(
							{
								rules :{ deptNamecr : {
										required : true,
										onlyLetterSpace : true
									},
									deptNameLocal : {
										nameFormatLocal : true
									},
									shortDeptName: {
										shortName : true
									}
								},
								messages : {
									
									deptNamecr : {
										required : "<font color='red'><br><spring:message code='error.fill.DEPTNAME' text='Please enter department name'/></font>",
										onlyLetterSpace : "<font color='red'><br><spring:message code='error.invalid.format' text='Please enter value in correct format'/></font>"
									},
									deptNameLocal : {
										nameFormatLocal : "<font color='red'><br><spring:message code='error.invalid.format' text='Please enter value in correct format'/></font>"
									},
									shortDeptName : {
										shortName : "<font color='red'><br><spring:message code='error.common.short.name' text='Short Name is invalid'/></font>"
									}
								}
							});
		});

function validateModifyDepartment() {
	var bool = $("#frmModifyVillage").validate().form();
	//alert(bool);
  	return bool;
}

function onClear() {	
	document.getElementById("deptNamecr").value ="";
	document.getElementById("deptNameLocal").value ="";
	document.getElementById("shortDeptName").value ="";
}
</script>

</head>
<body onload='toggledisplay2("chkchvillage","changevillage");' onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
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
									<c:out value="${param.family_msg}" escapeXml="true"></c:out>
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

	<!-- <div class="box" id="box">
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
				<td><div class="errorfont"></div>
				</td>
			</tr>
		</table>

	</div>
</div> -->
	<div id="frmcontent">
		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message htmlEscape="true" code="Label.MODIFYDEPARTMENT"></spring:message>&nbsp;</td>
					<td>&nbsp;</td>
					<%--//these links are not working <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message></a></td> --%>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="modifyDepartmentAction.htm" method="POST" commandName="modifyDepartmentCmd" id="frmModifyVillage" enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyDepartmentAction.htm"/>" />
				<form:input type="hidden" path="specificLevel" htmlEscape="true" />
				<form:input type="hidden" path="departmentId" htmlEscape="true" />
				<form:input type="hidden" path="orgLevel" htmlEscape="true" />
				
				<div class="frmtxt">
					<%--  <div class="frmhdtitle"  ><spring:message htmlEscape="true"  code="Label.CORRECTION"></spring:message> </div> --%>
					<table width="100%" class="tbl_no_brdr">
						<tr>
							<td width="14%" rowspan="4">&nbsp;</td>
							<td width="86%"><spring:message htmlEscape="true" code="Label.DEPTNAMEINENG"></spring:message><span class="errormsg">*</span> <br />
							<form:input path="deptNamecr" maxlength="200" class="frmfield" id="deptNamecr" onblur="vlidateOnblur('deptNamecr','1','15','c');" size="40" />
							<form:input path="deptName" type="hidden" htmlEscape="true" />
							<form:errors htmlEscape="true" path="deptNamecr" cssStyle="color:red"></form:errors></td>
						</tr>
						<tr>
							<td width="86%"><spring:message htmlEscape="true" code="Label.DEPTNAMEINLOCAL"></spring:message> <br /> <form:input path="deptNameLocal" class="frmfield" id="deptNameLocal" maxlength="80"
									onblur="vlidateOnblur('deptNameLocal','1','15','c');" size="40" htmlEscape="true"/> <form:errors htmlEscape="true" path="deptNameLocal" cssStyle="color:red"></form:errors></td>
						</tr>
						<tr>
							<td width="86%"><spring:message htmlEscape="true" code="Label.DEPTSHORTNAMEINENG"></spring:message> <br /> <form:input path="shortDeptName" class="frmfield" id="shortDeptName" maxlength="10"
									onblur="vlidateOnblur('shortDeptName','1','15','c');" size="40" htmlEscape="true"/> <form:input path="orgCode" class="frmfield" type="hidden" htmlEscape="true" /> <%-- <form:input path="orgTypeCode" class="frmfield" type="hidden"/>
										<form:input path="localBodySpecific" class="frmfield" type="hidden"/> --%> <form:input path="orgVersion" class="frmfield" type="hidden" htmlEscape="true" /> <form:errors htmlEscape="true" path="shortDeptName" cssStyle="color:red"></form:errors></td>
						</tr>

					</table>
				</div>
				<div class="btnpnl">
					<table width="100%" class="tbl_no_brdr">
						<tr>
							<td><label> <input type="submit" name="Submit" class="btn" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" onclick="return validateModifyDepartment();" />
							</label> <label> <!--  <input	name="reset"  type="reset" onclick="onClear()" value=<spring:message code="Button.CLEAR"/> />  -->
							<input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>" id="btnClear" onclick="onClear();"/>
							</label> <label> <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
							</label></td>
						</tr>
					</table>
				</div>


				<!-- </div> -->
				<%-- <div  id='changevillage'  class="frmpnlbg" style="visibility: visible; display:block">
							  <div class="frmtxt">
								   <div class="frmhdtitle"  visibility: hidden;><spring:message htmlEscape="true"  code="Label.CHANGE"></spring:message> </div>
								  <table width="100%" class="tbl_no_brdr">
								   <tr>
                                      <td width="14%" rowspan="4">&nbsp;</td>
                                      <td width="86%"><spring:message htmlEscape="true" 	code="Label.DEPTNAMEINENG"></spring:message><span class="errormsg">*</span><br />
                                          <label>
                                          <form:input path="deptName" class="frmfield" id="deptName" onblur="validateDeptName();" onfocus="show_msg('deptName');" />
                                          </label>
                                          <div class="errormsg"></div></td>
                                    </tr>
                                     
                                  </table>
                                  <table width="100%" class="tbl_no_brdr" align="center">
								<tr>
									<td width="14%" rowspan="9">&nbsp;</td>
									<td width="86%"><spring:message htmlEscape="true"  code="Label.ORDERNO"></spring:message><span class="errormsg">*</span> <br />
                                          <form:input path="orderCode" id="orderCode" type="hidden"
											class="frmfield" />
										<form:input path="orderNo" id="OrderNo" type="text" onkeypress="validateNumericKeys(event)"
											class="frmfield"
											onblur="validateOrdeNo();" onfocus="show_msg('OrderNo');" />
										<span class="error" id="OrderNo_error"></span>
										
										<div class="errormsg"> <form:errors path="orderNo" ></form:errors></div></td>
								</tr>
								<tr>
									<td><spring:message htmlEscape="true"  code="Label.ORDERDATE"></spring:message><span class="errormsg">*</span> <br />
										<table width="100%" class="tbl_no_brdr">

											<tr>
												<td colspan="2"  class="tblclear"><form:input
														path="orderDate" id="OrderDate" type="text"
														class="frmfield" htmlEscape="true"
														onfocus="show_msg('OrderDate');"
														onblur="validateOrdeDate();"
														onkeypress="validateNumeric();" /><span class="error" id="OrderDate_error"></span>
												 <div class="errormsg">  <form:errors path="orderDate" ></form:errors></div></td>
											</tr>
										</table>
										 
										
									</td>

								</tr>
								<tr>
									<td><spring:message htmlEscape="true"  code="Label.EFFECTIVEDATE"></spring:message><span class="errormsg">*</span> <br />
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td colspan="2" class="tblclear"><form:input
														id="EffectiveDate" path="ordereffectiveDate" type="text"
														class="frmfield" htmlEscape="true"
														onfocus="show_msg('EffectiveDate');"
														onblur="validateEffecDate();"
														onkeypress="validateNumeric();" /> <span class="error"
													id="EffectiveDate_error"></span>
													<div class="errormsg"> <form:errors path="ordereffectiveDate" ></form:errors></div>
													</td>

											</tr>
										</table>
										 
										
									</td>
								</tr>
								<tr>
									<td><spring:message htmlEscape="true"  code="Label.GAZPUBDATE"></spring:message><span class="errormsg">*</span> <br />
										<table width="100%" class="tbl_no_brdr">

											<tr>
												<td colspan="2" class="tblclear"><form:input
														id="GazPubDate" path="gazPubDate" type="text"
														class="frmfield" htmlEscape="true"
														onfocus="show_msg('GazPubDate');"
														onblur="validateGazPubDate();"
														onkeyup="validateNumeric();" /> <span class="error"
													id="GazPubDate_error"></span>
													<div class="errormsg"> <form:errors path="gazPubDate" ></form:errors></div>
													</td>

											</tr>
										</table>
									</td>
								</tr>

								<tr>
									<td><spring:message htmlEscape="true"  code="Label.UPLOADGOVTORDER"></spring:message><span class="errormsg">*</span><br />
										<form:input id="filGovernmentOrder" path="filePath"	class="frmfield" type="file" size="25"
										onfocus="show_msg('filGovernmentOrder');" onblur="validateSFile();" /> 
										 <span class="error" id="filGovernmentOrder_error"></span>
									</td>
								</tr>
							</table> 
							  </div>
						 
						  <div class="btnpnl">
                              <table width="100%" class="tbl_no_brdr">
                                <tr>
                                  <td ><label>
                                    <input type="submit" name="Submit" class="btn"  value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" onclick="return validateDeptAllGov();" />
                                    </label>
                                    <label>
                                     <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"/> </label>
                                  </td>
                                </tr>
                              </table>
					      </div>
					       </div> --%>
			</form:form>
		</div>
	</div>
	<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>