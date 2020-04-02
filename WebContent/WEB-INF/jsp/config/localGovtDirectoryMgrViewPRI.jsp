<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<head>
<script type="text/javascript" src="js/cancel.js" ></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
</script>
<script type="text/javascript" language="javascript">
	function disableButtonandSubmit() {
		//alert(document.getElementById('btnModify'));
		document.getElementById('viewlocalGovtDirectoryMgr').action = 'localGovtDirectoryMgrModifyPRI.htm';
		document.getElementById('btnModify').disabled = true;
		document.getElementById('viewlocalGovtDirectoryMgr').submit();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
</head>
<body>
<div class="clear"></div>
				    <div class="frmhd">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td><spring:message htmlEscape="true"  code="Label.CGD"></spring:message>
								</td>
								 <td>&nbsp;</td>
                              <%-- //these links are not working <td width="50" align="right" valign="middle"><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></td>
                                <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a></td>
								 --%>
							</tr>
						</table>
				   </div>
                <div class="frmpnlbrdr">
				<form:form action="localGovtDirectoryMgrModifyPRI.htm" method="GET" commandName="configGovtOrderForm" id="viewlocalGovtDirectoryMgr">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="localGovtDirectoryMgrModifyPRI.htm"/>"/>
				<div class="frmpnlbg">
				<div class="frmtxt" align="center" >
                          <div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.CGD"></spring:message></div>
					      <table width="70%" class="tbl_with_brdr">
						    <thead><tr  class="tblRowTitleBold">
								<th width="20%"><spring:message htmlEscape="true"  code="Label.OPERATION"></spring:message></th><th width="5%"></th><th width="40%"><spring:message htmlEscape="true"  code="Label.OPTIONS"></spring:message></th>
						    </tr>
						    </thead>
					       <c:forEach var="administratorUnit" varStatus="administratorUnitRow" items="${configGovtOrderForm.listAdminUnits}">
							 
                              <tr class="tblRowB" align="left">                                                  
                              
                              <spring:bind path="configGovtOrderForm.listAdminUnits[${administratorUnitRow.index}].operationBlockValue">
                               <%-- <c:out value="${status.value}"/> --%>
                                                                 
                                <td width="40%" align="left"><c:out value="${administratorUnit.operationBlockName}" escapeXml="true"></c:out> </td>
                                <td width="5%"><input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" /> &nbsp;</td>
                               </spring:bind>
                               <spring:bind path="configGovtOrderForm.listAdminUnits[${administratorUnitRow.index}].isgovtorderuploadBlock">
                                <td width="30%" align="left">
                                <c:if test="${status.value == false}"><spring:message htmlEscape="true"  code="Label.GGO"></spring:message></c:if>
                                <c:if test="${status.value == true}"><spring:message htmlEscape="true"  code="Label.UGO"></spring:message></c:if>
                                </td>
                               </spring:bind>
                              </tr>                                                            
                           </c:forEach>
                          </table>
					      <div  class="errormsg"></div>
				      </div>
				      </div>
				      <div class="btnpnl">
                              <label>
                              <input type="button" id="btnModify" class="btn" onclick="disableButtonandSubmit()" name="Submit" value="<spring:message htmlEscape="true"  code="Button.Modify"/>" />
							  </label>
							 <label>
                              <input type="button" class="btn" name="Submit6" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
                              </label> 
                      </div>
				      </form:form>
				       <script src="/LGD/JavaScriptServlet"></script>
				      </div>
</body>
</html>