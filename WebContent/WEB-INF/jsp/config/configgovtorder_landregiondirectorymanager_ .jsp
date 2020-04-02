<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<%@include file="../common/taglib_includes.jsp"%>

<script type="text/javascript" src="js/cancel.js"></script>

</head>

<body>
	<div class="clear"></div>
				    <div class="frmhd">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td><spring:message htmlEscape="true"  code="Label.CGD"></spring:message>
								</td>
								 <td>&nbsp;</td>
                               <%-- //this link is not working <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a></td> --%>
								
							</tr>
						</table>
				   </div>
                <div class="frmpnlbrdr">
		<form:form id="form3" name="formgovtorder" method="post" action="configureOrder.htm" commandName="configGovtOrderForm">
		<div class="frmpnlbg">
			<div class="frmtxt">
				<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.CGD"></spring:message></div>
				<table width="100%" class="tbl_no_brdr">
					<tr>
						<td width="7%" rowspan="6">&nbsp;</td>
						<td width="2%"><label> <input type="checkbox"
								name="checkbox322" value="checkbox" /> </label>
						</td>
						<td width="20%"><spring:message htmlEscape="true"  code="Label.DLGS"></spring:message></td>
						<td width="19%"><spring:message htmlEscape="true"  code="Label.UGO"></spring:message></td>
						<td width="8%"><input name="radiobutton" type="radio"
							value="radiobutton" />
						</td>
						<td width="19%"><spring:message htmlEscape="true"  code="Label.GGO"></spring:message></td>
						<td width="9%"><input name="radiobutton" type="radio"
							value="radiobutton" />
						</td> <spring:message htmlEscape="true"  code="Label.GGO"></spring:message>
					</tr>
					<tr>
						<td><label> <input type="checkbox" name="checkbox422"
								value="checkbox" /> </label>
						</td>
						<td><spring:message htmlEscape="true"  code="Label.CNLGB"></spring:message></td>
						<td><spring:message htmlEscape="true"  code="Label.UGO"></spring:message></td>
						<td><input name="radiobutton" type="radio"
							value="radiobutton" />
						</td>
						<td><spring:message htmlEscape="true"  code="Label.GGO"></spring:message></td>
						<td><input name="radiobutton" type="radio"
							value="radiobutton" />
						</td>
					</tr>
					<tr>
						<td><label> <input type="checkbox" name="checkbox422"
								value="checkbox" /> </label>
						</td>
						<td><spring:message htmlEscape="true"  code="Label.MNOLGB"></spring:message></td>
						<td><spring:message htmlEscape="true"  code="Label.UGO"></spring:message></td>
						<td><input name="radiobutton" type="radio"
							value="radiobutton" />
						</td>
						<td><spring:message htmlEscape="true"  code="Label.GGO"></spring:message></td>
						<td><input name="radiobutton" type="radio"
							value="radiobutton" />
						</td>
						<td width="12%" rowspan="4"><label></label>
						</td>
					</tr>
					<tr>
						<td><input type="checkbox" name="checkbox4222"
							value="checkbox" />
						</td>
						<td><spring:message htmlEscape="true"  code="Label.MTOLGB"></spring:message></td>
						<td><spring:message htmlEscape="true"  code="Label.UGO"></spring:message></td>
						<td><input name="radiobutton" type="radio"
							value="radiobutton" />
						</td>
						<td><spring:message htmlEscape="true"  code="Label.GGO"></spring:message></td>
						<td><input name="radiobutton" type="radio"
							value="radiobutton" />
						</td>
					</tr>
					<tr>
						<td><input type="checkbox" name="checkbox4223"
							value="checkbox" />
						</td>
						<td><p><spring:message htmlEscape="true"  code="Label.MPOLGB"></spring:message></p></td>
						<td><spring:message htmlEscape="true"  code="Label.UGO"></spring:message></td>
						<td><input name="radiobutton" type="radio"
							value="radiobutton" />
						</td>
						<td><spring:message htmlEscape="true"  code="Label.GGO"></spring:message></td>
						<td><input name="radiobutton" type="radio"
							value="radiobutton" />
						</td>
					</tr>
					<tr>
						<td><input type="checkbox" name="checkbox4224"
							value="checkbox" />
						</td>
						<td><spring:message htmlEscape="true"  code="Label.MCALGB"></spring:message></td>
						<td><spring:message htmlEscape="true"  code="Label.UGO"></spring:message></td>
						<td><input name="radiobutton" type="radio"
							value="radiobutton" />
						</td>
						<td><spring:message htmlEscape="true"  code="Label.GGO"></spring:message></td>
						<td><input name="radiobutton" type="radio"
							value="radiobutton" />
						</td>
					</tr>
				</table>
				<div class="errormsg"></div>
				</div>
				<div class="btnpnl">
                              <label>
                              <input type="submit" class="btn" name="Submit" value="<spring:message htmlEscape="true"  code="Button.SAVE"/>" />
							  </label>
							  <label>
                              <input type="button" class="btn" name="Submit6" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
                              </label> 
                      </div>
			</div>
			
		</form:form>
	</div>
	</body>
	</html>