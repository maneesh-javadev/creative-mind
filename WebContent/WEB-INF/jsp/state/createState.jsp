<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page import="java.util.*,in.nic.pes.lgd.bean.GetLocalGovtSetup"%>
<%@include file="../common/taglib_includes.jsp"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>

<head>
<title>E-Panchayat</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>


<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>


<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>
	
</script>



<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<link rel="stylesheet" href="datepicker/demos.css" /> -->

<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
</script>

</head>
<body oncontextmenu="return false"
	onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);">
	<div id="frmcontent">
		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message code="Label.CREATESTATE" htmlEscape="true"></spring:message>
					</td>
					<td align="right"><a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a></td>
					<%--Button.HELP <td width="50" align="right"><a href="#" class="frmhelp"><spring:message
								code="Button.HELP"></spring:message> </a></td> --%>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">

			<form:form commandName="createState" method="POST"
				enctype="multipart/form-data">
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.GENERALDETAILNEWSTATE" htmlEscape="true"></spring:message>
							</div>
							<tr>
								<td width="14%" rowspan="8">&nbsp;</td>
								<td width="86%"><spring:message
										code="Label.STATENAMEENGLISH" htmlEscape="true"></spring:message><span
									class="errormsg">*</span><br /> <form:input id="txtlgdStateNameInEnglish"
										path="lgdStateNameInEnglish" onkeypress="validateCharKeys(event)" htmlEscape="true"
										cssClass="frmfield" />&nbsp;&nbsp; <span class="errormsg"
									id="txtlgdStateNameInEnglish_error"></span> <span><form:errors htmlEscape="true" 
											path="lgdStateNameInEnglish" class="errormsg"></form:errors> </span>
								</td>
							</tr>

							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td width="86%"><spring:message code="Label.STATENAMELOCAL" htmlEscape="true"></spring:message><span
									id="required"></span><br /> <form:input
										path="lgdStateNameInLocal" onkeypress="validateCharKeys(event)" htmlEscape="true"
										cssClass="frmfield" />
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td width="86%"><spring:message
										code="Label.STATEALIASLOCAL" htmlEscape="true"></spring:message><span
									id="required"></span><br /> <form:input
										path="lgdStateAliasInEnglish" htmlEscape="true"
										onkeypress="validateCharKeys(event)" cssClass="frmfield" />
								</td>
							</tr>

							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td width="86%"><spring:message code="Label.STATEALIASLOCAL" htmlEscape="true"></spring:message><span
									id="required"></span><br /> <form:input
										path="lgdStateAliasInLocal" onkeypress="validateCharKeys(event)" htmlEscape="true"
										cssClass="frmfield" />
							</tr>

						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>


</body>
</html>