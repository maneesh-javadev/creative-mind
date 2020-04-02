<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@include file="../common/taglib_includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     
<head>
<script type="text/javascript" src="js/cancel.js" ></script>
<script type="text/javascript" src="js/common.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
</script>
<script type="text/javascript" language="javascript">
    
	function submitForm() {
		document.getElementById('lgdmfrm').action = "lgdmFormActionTrade.htm";
		document.getElementById('btnSave').disabled=true;
		document.forms["lgdmfrm"].submit();
		
	}
</script>
</head>
<body>
			<div class="clear">
				</div>
				    <div class="frmhd">
				    <h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.CGD"></spring:message></h3>
				    	<ul class="listing">
				    		<%-- //these links are not working <li>
				    			<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
				    		</li>
				    		<li>
				    			<a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a>
				    		</li> --%>
				    	</ul>
					</div>
					<div class="frmpnlbrdr">
						<form:form action="lgdmFormActionTrade.htm" method="POST" id="lgdmfrm"
							commandName="configGovtOrderForm">
							<input type="hidden" name="<csrf:token-name/>"
								value="<csrf:token-value uri="lgdmFormActionTrade.htm"/>" />
							<div class="frmpnlbg">
								<div class="frmtxt">
									<div class="frmhdtitle">
										<spring:message htmlEscape="true"  code="Label.CGD"></spring:message>
									</div>
									<div class="table col_2"> <!-- Table starts -->				
										<div class="trow thead">
											<div class="th col1"><spring:message htmlEscape="true"  code="Label.OPERATION"></spring:message></div>
											<div class="th col2"><spring:message htmlEscape="true"  code="Label.OPTIONS"></spring:message></div>
										
										</div>
										<c:forEach var="administratorUnit" varStatus="administratorUnitRow" items="${configGovtOrderForm.listAdminUnits}">
										 <div class="trow red">
											<div class="td column col1">
												<spring:bind path="configGovtOrderForm.listAdminUnits[${administratorUnitRow.index}].operationBlockValue">
													<%-- <c:out value="${status.value}"/> --%>
													<input type="hidden"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
													${administratorUnit.operationBlockName}
												</spring:bind>											
											</div>
											<div class="td column col2">
												<spring:bind
													path="configGovtOrderForm.listAdminUnits[${administratorUnitRow.index}].isgovtorderuploadBlock">
													<input type="radio"
														name="<c:out value="${status.expression}"/>" value="true"
														<c:if test="${status.value == true}">checked</c:if> />
													<spring:message htmlEscape="true"  code="Label.UGO"></spring:message>&nbsp;
													<input type="radio"
														name="<c:out value="${status.expression}"/>" value="false"
														<c:if test="${status.value == false}">checked</c:if> />
													<spring:message htmlEscape="true"  code="Label.GGO"></spring:message>
												</spring:bind>
											</div>
										</div>
									</c:forEach>
								</div>
									
									
									
									
									
									
									
									<%-- <table width="70%" class="tbl_with_brdr">
										<thead>
											<tr class="tblRowTitleBold">
												<th width="30%"><spring:message htmlEscape="true"  code="Label.OPERATION"></spring:message>
												</th>
												<th width="2%"></th>
												<th width="30%" colspan="4"><spring:message htmlEscape="true" 
														code="Label.OPTIONS"></spring:message>
												</th>
											</tr>
										</thead>
										<c:forEach var="administratorUnit"
											varStatus="administratorUnitRow"
											items="${configGovtOrderForm.listAdminUnits}">
											<tr align="left" class="tblRowB">
												<spring:bind path="configGovtOrderForm.listAdminUnits[${administratorUnitRow.index}].operationBlockValue">
													<c:out value="${status.value}"/>
													<input type="hidden"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}"/>" />
													<td width="25%">${administratorUnit.operationBlockName}</td>
												</spring:bind>
												<spring:bind
													path="configGovtOrderForm.listAdminUnits[${administratorUnitRow.index}].isgovtorderuploadBlock">
													<td width="2%"><input type="radio"
														name="<c:out value="${status.expression}"/>" value="true"
														<c:if test="${status.value == true}">checked</c:if> /></td>
													<td width="20%"><spring:message htmlEscape="true"  code="Label.UGO"></spring:message>
													</td>
													<td width="2%"><input type="radio"
														name="<c:out value="${status.expression}"/>" value="false"
														<c:if test="${status.value == false}">checked</c:if> /></td>
													<td width="30%"><spring:message htmlEscape="true"  code="Label.GGO"></spring:message>
													</td>
												</spring:bind>
											</tr>
										</c:forEach>
									</table> --%>
									<div class="errormsg"></div>
								</div>
							</div>
							<div class="btnpnl">
								<label> <input type="button" id="btnSave" class="btn"
									onclick="submitForm()" name="Submit"
									value="<spring:message htmlEscape="true"  code="Button.SAVE"/>" /> </label> <label> <input
									type="button" class="btn" name="Submit6"
									value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
									onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
							</div>
						</form:form>
						 <script src="/LGD/JavaScriptServlet"></script>
					</div>
				</body>
				</html>