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
<section class="content">
              <div class="row">
                   <section class="col-lg-12">
                       <div class="box">
                <form:form id="lrmform" action="lrmFormModify.htm" method="GET" commandName="configGovtOrderForm">
			    <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="lrmFormModify.htm"/>" />
                    <div class="box-header with-border">
                        <h3 class="box-title"><spring:message htmlEscape="true" code="Label.CGD"></spring:message></h3>
                    </div>
                    <div class="box-body">
                    <table class="table table-bordered table-hover">
                        <tr>
						  <th><spring:message htmlEscape="true"  code="Label.OPERATION"></spring:message></th>
						  <th ><spring:message htmlEscape="true"  code="Label.OPTIONS"></spring:message></th>
						</tr>
					<tbody>
                        <c:forEach var="administratorUnit"  varStatus="administratorUnitRow" 	items="${configGovtOrderForm.listAdminUnits}">
							<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" />
                            <tr >                                                  
                               <spring:bind path="configGovtOrderForm.listAdminUnits[${administratorUnitRow.index}].operationBlockValue">
									<td><c:out value="${administratorUnit.operationBlockName}" /></td>
								</spring:bind>
								<spring:bind path="configGovtOrderForm.listAdminUnits[${administratorUnitRow.index}].isgovtorderuploadBlock">
								<td>
                                 <c:if test="${status.value == false}"><spring:message htmlEscape="true"  code="Label.GGO"></spring:message></c:if>                 
                               
                                 <c:if test="${status.value == true}"><spring:message htmlEscape="true"  code="Label.UGO"></spring:message></c:if>
                                 
                              </td>
                               </spring:bind>
                              </tr>                                                            
                           </c:forEach>	
                  </tbody>
               </table>
               </div>
               <div class="errormsg"></div>
                  <!-- /.box-body -->
              <div class="box-footer">  
                <div class="col-sm-offset-2 col-sm-10">
	                 <div class="pull-right">                   
			             <input type="button" id="btnModify" onclick="submitForm()" class="btn" name="Submit" value="<spring:message htmlEscape="true"  code="Button.Modify"></spring:message>" />
						 <input type="button" class="btn" name="Submit6" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
					</div>
                  </div>
              </div>
              </form:form>
              <script src="/LGD/JavaScriptServlet"></script>
         </div>
     </section>
  </div>
</section>
	</div>
</body>
</html>