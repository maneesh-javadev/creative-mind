<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
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
		document.getElementById('form3').action = "administrativeUnitMgrAction.htm";
		document.getElementById('btnSave').disabled = true;
		document.forms["form3"].submit();
	}
</script>
</head>
<body>
<section class="content">
              <div class="row">
                   <section class="col-lg-12">
                       <div class="box">
               <form:form action="administrativeUnitMgrAction.htm" method="POST" id="form3" commandName="configGovtOrderForm">
				 <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="administrativeUnitMgrAction.htm"/>"/>
                    <div class="box-header with-border">
                        <h3 class="box-title"><spring:message htmlEscape="true" code="Label.CGD"></spring:message></h3>
                    </div>
                    <div class="box-body">
                    <table class="table table-bordered table-hover">
                        <tr>
						  <th><spring:message htmlEscape="true" code="Label.OPERATION"></spring:message></th>
						  <th colspan="2"><center><spring:message htmlEscape="true" code="Label.OPTIONS" ></spring:message></center></th>
						</tr>
					<tbody>
                         <c:forEach var="administratorUnit" varStatus="administratorUnitRow" items="${configGovtOrderForm.listAdminUnits}">
                            <tr >                                                  
                              <spring:bind path="configGovtOrderForm.listAdminUnits[${administratorUnitRow.index}].operationBlockValue">
                               <input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" />                                
	                               <td ><c:out value="${administratorUnit.operationBlockName}" /> </td>
	                               </spring:bind>
	                          <spring:bind path="configGovtOrderForm.listAdminUnits[${administratorUnitRow.index}].isgovtorderuploadBlock">
                                <td >
                                <label class="radio-inline">
                                 <input type="radio" name="<c:out value="${status.expression}"/>" value="true" <c:if test="${status.value == true}">checked</c:if> /><spring:message htmlEscape="true" code="Label.UGO"></spring:message></label>
                                </td>
                                                         
                                <td>
                                <label class="radio-inline">
                                <input type="radio" name="<c:out value="${status.expression}"/>" value="false" <c:if test="${status.value == false}">checked</c:if> /><spring:message htmlEscape="true" code="Label.GGO"></spring:message></label>
                                </td>
                              
                              </spring:bind>
                              </tr>                                                            
                           </c:forEach>	
                  </tbody>
               </table>
               </div>
                  <!-- /.box-body -->
              <div class="box-footer">  
                <div class="col-sm-offset-2 col-sm-10">
	                 <div class="pull-right">                   
			              <button type="button" id="btnSave" class="btn btn-success" onclick="submitForm()" name="Submit" ><spring:message htmlEscape="true" code="Button.SAVE"/></button>
			             <button type="button" class="btn btn-danger" name="Submit6"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message> </button>
                      </div>
                  </div>
              </div>
              </form:form>
         </div>
     </section>
  </div>
</section>

</body>
</html>