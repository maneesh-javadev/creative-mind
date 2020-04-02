<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<%@include file="../common/taglib_includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <script type="text/javascript" src="js/cancel.js" ></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
</script>
<script type="text/javascript" language="javascript">
    
	function submitForm() {
		document.getElementById('lrmform').action = "lrmFormUPDATE.htm";
		document.getElementById('btnUpdate').disabled=true;
		document.forms["lrmform"].submit();
		
	}
</script>
</head>
<body>
<div class="clear"></div>
				    <div class="frmhd">
				    <h3 class="subtitle"><label><spring:message htmlEscape="true" code="Label.CGD"></spring:message></label></h3>
										 <ul id="showhelp" class="listing">
					 				      <%--  //these links are not working <li>
					 				         <a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /> </a>           
					 				        </li>
					 				   <li>
					 				       <a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
										</li>
					 				        <li>
					 				        	<a href="#" class="frmhelp"><spring:message	code="Button.HELP" htmlEscape="true"></spring:message> </a>
					 				        </li> --%>
					 				     
					 			        </ul>
		
			
		</div>
			
				   
                <div class="frmpnlbrdr">
                <form:form id="lrmform" action="lrmFormUPDATE.htm" method="POST" commandName="configGovtOrderForm">
              
                <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="lrmFormUPDATE.htm"/>"/>
				<div class="frmpnlbg">
				<div class="frmtxt">
							<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.CGD"></spring:message> </div>
						   
						    <div class="table col_3"> <!-- Table starts -->				
									<div class="trow thead">
										<div class="th col1"><spring:message htmlEscape="true"  code="Label.OPERATION"></spring:message></div>
										<div class="th col2"></div>
										<div class="th col3"><spring:message htmlEscape="true"  code="Label.OPTIONS"></spring:message></div>						
									</div>	
						   
						   
						    <c:forEach var="administratorUnit" varStatus="administratorUnitRow" items="${configGovtOrderForm.listAdminUnits}">
                             <div class="trow red">                                                     
                              <spring:bind path="configGovtOrderForm.listAdminUnits[${administratorUnitRow.index}].operationBlockValue">
                               <%-- <c:out value="${status.value}"/> --%>
                                  <input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" />                                
                                  <div class="td column col1"><c:out value="${administratorUnit.operationBlockName}" escapeXml="true"></c:out> </div>
                               </spring:bind>
                               <spring:bind path="configGovtOrderForm.listAdminUnits[${administratorUnitRow.index}].isgovtorderuploadBlock">
                                <div class="td column col1">
                                <input type="radio" name="<c:out value="${status.expression}"/>" value="true" <c:if test="${status.value == true}">checked</c:if> htmlEscape="true" />
                                
                               <label> <spring:message htmlEscape="true"  code="Label.UGO"></spring:message></label>                                 
                                </div>
                                <div class="td column col3">
                                <input type="radio" name="<c:out value="${status.expression}" escapeXml="true"></c:out>" value="false" <c:if test="${status.value == false}">checked</c:if> htmlEscape="true" />
								<label> <spring:message htmlEscape="true"  code="Label.GGO"></spring:message></label> 
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
                              <label>
                              <input type="submit" id="btnUpdate" class="btn" onclick="submitForm()" name="Submit" value="<spring:message htmlEscape="true"  code="Button.UPDATE"></spring:message>" />
							  </label>
							  <label>
                              <input type="button" class="btn" name="Submit6" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
                              </label> 
                      </li>
                      </ul>
                      </div>
                      
						</form:form>
						 <script src="/LGD/JavaScriptServlet"></script>
						</div>
</body>
</html>