<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
</script>
<script type="text/javascript" language="javascript">
    
	function submitForm() {
		document.getElementById('lrmform').action = "lrmFormModify.htm";
		document.getElementById('btnModify').disabled=true;
		document.forms["lrmform"].submit();
		
	}
</script>
</head>
<body>
	<div class="clear"></div>
	<div class="frmhd">
	<h3 class="subtitle"><label><spring:message htmlEscape="true" code="Label.CGD"></spring:message></label></h3>
										 <ul id="showhelp" class="listing">
					 				        <li>
					 				         <a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /> </a>           
					 				        </li>
					 				  <%--  // these links are not working<li>
					 				       <a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
										</li>
					 				        <li>
					 				        	<a href="#" class="frmhelp"><spring:message	code="App.HELP" htmlEscape="true"></spring:message> </a>
					 				        </li> --%>
					 				     
					 			        </ul>
		
			
		</div>
		
	
	<div class="frmpnlbrdr">
		<form:form id="lrmform" action="lrmFormModify.htm" method="GET"
			commandName="configGovtOrderForm">
			<input type="hidden" name="<csrf:token-name/>"
				value="<csrf:token-value uri="lrmFormModify.htm"/>" />
			<div class="frmpnlbg">
				<div class="frmtxt">
					<div class="frmhdtitle">
						<spring:message htmlEscape="true" code="Label.CGD"></spring:message>
					</div>
					
					<div class="table col_3"> <!-- Table starts -->				
									<div class="trow thead">
										<div class="th col1"><spring:message htmlEscape="true"  code="Label.OPERATION"></spring:message></div>										
										<div class="th col2"><spring:message htmlEscape="true"  code="Label.OPTIONS"></spring:message></div>						
									</div>
					
					
						<c:forEach var="administratorUnit"
							varStatus="administratorUnitRow"
							items="${configGovtOrderForm.listAdminUnits}">
							<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" />
							<div class="trow">
								<spring:bind
									path="configGovtOrderForm.listAdminUnits[${administratorUnitRow.index}].operationBlockValue">
									<%-- <c:out value="${status.value}"/> --%>
									<div class="td column col1">
										<c:out value="${administratorUnit.operationBlockName}" />
									</div>
								</spring:bind>
								<spring:bind
									path="configGovtOrderForm.listAdminUnits[${administratorUnitRow.index}].isgovtorderuploadBlock">
									<div class="td column col2"><c:if test="${status.value == false}">
											<spring:message htmlEscape="true" code="Label.GGO"></spring:message>
										</c:if> <c:if test="${status.value == true}">
											<spring:message htmlEscape="true" code="Label.UGO"></spring:message>
										</c:if> <%--                                 <input type="radio" disabled="disabled" name="<c:out value="${status.expression}"/>" value="true" <c:if test="${status.value == true}">checked</c:if> />
                                <input type="radio" disabled="disabled" name="<c:out value="${status.expression}"/>" value="false" <c:if test="${status.value == false}">checked</c:if> /> --%>
									</div>
								</spring:bind>
							</div>
						</c:forEach>

						
					</div>
					
					
					<div class="errormsg"></div>
				</div>
			</div>
			<div class="btnpnl">
			<ul class="listing">
			<li>
				<label> <input type="button" id="btnModify"
					onclick="submitForm()" class="btn" name="Submit"
					value="<spring:message htmlEscape="true"  code="Button.Modify"></spring:message>" />
				</label> <label> <input type="button" class="btn" name="Submit6"
					value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
					onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
			</li>
			</ul>
			</div>
		</form:form>
		<script src="/LGD/JavaScriptServlet"></script>
	</div>
</body>
</html>