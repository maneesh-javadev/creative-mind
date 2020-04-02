<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">



<%@include file="../common/taglib_includes.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/cancel.js"></script>
<title><spring:message htmlEscape="true" code="Label.Title"></spring:message>
</title>
<script src="js/common.js"></script>
<script type="text/javascript" src="js/nomenclatureSubdistrict.js"
	charset="utf-8"></script>
</head>

<body>
	<div id="frmcontent">
		<table width="100%" class="tbl_no_brdr">
			<tr>
				<td valign="top" class="tblclear">
					<div class="clear"></div>
					<div id="frmcontent">
						<div class="frmhd">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td><spring:message htmlEscape="true"
											code="Label.NOMENCLATUREOFSUBDISTRICT"></spring:message></td>
									<td>&nbsp;</td>
								<%--//this link is not working 	<td width="50" align="right"><a href="#" class="frmhelp"><spring:message
												htmlEscape="true" code="Label.HELP"></spring:message> </a></td> --%>
								</tr>
							</table>
						</div>
						<div class="frmpnlbrdr">
							<form:form action="saveNomenclature_sub_district.htm"
								id="formmodifynomen" method="POST"
								commandName="nomenclatureSubDistForm">
								<input type="hidden" name="<csrf:token-name/>"
									value="<csrf:token-value uri="saveNomenclature_sub_district.htm"/>" />
								<div class="frmpnlbg">
									<div class="frmtxt">
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td><label><spring:message htmlEscape="true"
															code="Label.NOMENCLATUREOFSUBDISTRICTENGLISH"></spring:message>
												</label></td>
											</tr>
											<tr>

												<td align="left"><form:input path="nameinEnglish"
														id="NomenInEnglish" cssClass="frmfield"
														onfocus="show_msg('NomenInEnglish')" htmlEscape="true"
														onkeypress="validateAlphanumericandDotKeysforNomenclature();" />
													<form:errors path="nameinEnglish" cssClass="errormsg"></form:errors>
													<!-- <span class="msg" id="NomenInEnglish_msg">*Please enter the nomenclature in english</span>onblur="nomenInEnglish()"  -->
													<span class="errormsg" id="NomenInEnglish_error"></span>
													<div class="errormsg"></div>
												</td>
											</tr>
											<tr>
												<td><label><spring:message htmlEscape="true"
															code="Label.NOMENCLATUREOFSUBDISTRICTLOCAL"></spring:message>
												</label></td>
											</tr>
											<tr>
												<td align="left"><form:input path="nameinlocalLanguage"
														class="frmfield" /></td>
											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td><label><spring:message htmlEscape="true"
															code="Label.ISBLOCKEXISTS"></spring:message> </label></td>
											</tr>
											<tr>
												<td align="left"><form:radiobutton path="blockExist"
														value="true" label="Yes" onclick="togglehide(1)" /> <form:radiobutton
														path="blockExist" value="false" label="No"
														onclick="togglehide(0)" /></td>
											</tr>



											<tr id="trdyn1" style="visibility: hidden">
												<td><label><spring:message htmlEscape="true"
															code="Label.ISBLOCKNAMESAME"></spring:message> </label></td>
											</tr>
											<tr id="trdyn2" style="visibility: hidden">
												<td align="left"><form:radiobutton
														path="nameofTheBlockisSameforTheState" value="true"
														label="Yes" /> <form:radiobutton
														path="nameofTheBlockisSameforTheState" value="false"
														label="No" />
												</td>

											</tr>
										</table>
										<div class="errormsg"></div>
									</div>
								</div>
								<div class="btnpnl">
									<label> <input type="button" id="btnSave" name="Button"
										class="btn" onclick="validateAndSubmit()"
										value="<spring:message htmlEscape="true"  code="Button.SAVE"/>" />
									</label> <label> <input type="button" class="btn" name="cancel"
										onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"
										value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" />
									</label>
								</div>

							</form:form>
							<script src="/LGD/JavaScriptServlet"></script>
						</div>
					</div></td>

				<!-- </td> -->
			</tr>
		</table>
	</div>
</body>
</html>