<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<script type="text/javascript" src="js/cancel.js" ></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" language="javascript">
	function submitForm() {
		document.getElementById('lgdmfrm').action = "modifyLGDMCenter.htm";
		document.getElementById('btnSave').disabled=true;
		document.forms["lgdmfrm"].submit();
		
	}
</script>
</head>
<body>
<div class="clear"></div>
				    <div class="frmhd">
				    
				    
				    <h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.CGD"></spring:message></h3>
		<%-- //this link is noy working <ul id="showhelp" class="listing">
				
 				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></li>
			</ul> --%>
			
				   </div>
                <div class="frmpnlbrdr">
				<form:form action="modifyLGDMCenter.htm" method="POST" id="lgdmfrm" commandName="configGovtOrderForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyLGDMCenter.htm"/>"/>
				<div class="frmpnlbg">
				<div class="frmtxt"  align="center">
                          <div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.CGD"></spring:message></div>
					      
					      
					      <div class="table col_3"> <!-- Table starts -->				
									<div class="trow thead">
										<div class="th col1"><spring:message htmlEscape="true"  code="Label.OPERATION"></spring:message></div>
										<div class="th col2"></div>
										<div class="th col3"><spring:message htmlEscape="true"  code="Label.OPTIONS"></spring:message></div>						
									</div>	
									
									
					      
					      
					       <c:forEach var="administratorUnit" varStatus="administratorUnitRow" items="${configGovtOrderForm.listAdminUnits}">
							 
                              <div class="trow red">                                               
                              
                              <spring:bind path="configGovtOrderForm.listAdminUnits[${administratorUnitRow.index}].operationBlockValue">
                                  <input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" />                                
                                <div class="td column col1"><c:out value="${administratorUnit.operationBlockName}" escapeXml="true"></c:out> </div>
                               </spring:bind>
                               <spring:bind path="configGovtOrderForm.listAdminUnits[${administratorUnitRow.index}].isgovtorderuploadBlock">
                                <div class="td column col1">
                                <input type="radio" name="<c:out value="${status.expression}"/>" value="true" <c:if test="${status.value == true}">checked</c:if> />
                                
                                <spring:message htmlEscape="true"  code="Label.UGO"></spring:message>                                
                                </div>
                                <div class="td column col3">
                                <input type="radio" name="<c:out value="${status.expression}"/>" value="false" <c:if test="${status.value == false}">checked</c:if> />
                                
                              <spring:message htmlEscape="true"  code="Label.GGO"></spring:message>
                               </div>
                               </spring:bind>
                              </div>                                                            
                           </c:forEach>
                          </div>
					      <div  class="errormsg"></div>
				      </div>
				      </div>
				      <div class="btnpnl">
				      <ul class="listing">
				      <li>
                              <label>
                              <input type="button" id="btnSave" onclick="submitForm()" class="btn" name="Submit" value="<spring:message htmlEscape="true"  code="Button.UPDATE"/>" />
							  </label>
							 <label>
                              <input type="button" class="btn" name="Submit6" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
                              </label> 
                              
                              </li>
                              </ul>
                      </div>
				      </form:form>
				      <script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
				      </div>
</body>
</html>