<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
<script type="text/javascript">
function loadPage() {
		var mypath = window.location.href;

		document.getElementById('btnback').style.visibility='visible';
		document.getElementById('btnCancel').style.visibility='visible';
		document.getElementById('btnClose').style.visibility='hidden';
		var mySplitResult = mypath.split("&");

		if(mySplitResult[1]!="")
			{
		var type=mySplitResult[1].replace("type=","");
		

		if(type=='S')
			{
			document.getElementById('btnback').style.visibility='hidden';
			document.getElementById('btnCancel').style.visibility='hidden';
			document.getElementById('btnClose').style.visibility='visible';
			
			}
		else
			{
			document.getElementById('btnback').style.visibility='visible';
			document.getElementById('btnCancel').style.visibility='visible';
			document.getElementById('btnClose').style.visibility='hidden';
			}
			}
	
		
		
}
		</script>
</head>
<body onload="loadPage();">	 
	         <section class="content">
                 <div class="row">
                    <section class="col-lg-12">
                        <div class="box">
                    <div class="box-header with-border">
                        <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.VIEWDEPTDETAIL"></spring:message></h3>
                     </div>
                     <form:form action="viewDepartmentAction.htm" method="POST" commandName="viewDepartment">
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewDepartmentAction.htm"/>"/>
						<div class="box-header subheading"><h4><spring:message htmlEscape="true"  code="Label.DEPTDETAIL"></spring:message></h4>
										</div>
               <table class="table table-bordered table-hover">
    
    <tbody>
    <c:forEach var="listMinistry" varStatus="listMinistryDetailsRow" items="${viewDepartment.listMinistry}">
      <tr>
        <td><spring:message htmlEscape="true" code="Label.DEPTCODE"></spring:message></td>
        <td><spring:bind path="viewDepartment.listMinistry[${listMinistryDetailsRow.index}].orgCode">&nbsp;
																				 <c:out value="${status.value}" escapeXml="true"></c:out>
																		</spring:bind> </td>
      </tr>
      <tr>
        <td><spring:message htmlEscape="true" code="Label.DEPTNAMEINENG"></spring:message></td>
        <td> <spring:bind path="viewDepartment.listMinistry[${listMinistryDetailsRow.index}].orgName">&nbsp;
																				<c:out value="${status.value}" escapeXml="true"></c:out>
																		</spring:bind></td>
      </tr>
      <tr>
        <td><spring:message htmlEscape="true" code="Label.DEPTSHORTNAMEINENG"></spring:message></td>
        <td><spring:bind path="viewDepartment.listMinistry[${listMinistryDetailsRow.index}].shortName">&nbsp;
																				<c:out value="${status.value}" escapeXml="true"></c:out>
																		</spring:bind> </td>
      </tr>
      <tr>
        <td><spring:message htmlEscape="true" code="Label.DEPTNAMEINLOCAL"></spring:message></td>
        <td>  <spring:bind path="viewDepartment.listMinistry[${listMinistryDetailsRow.index}].orgNameLocal">&nbsp;
																				<c:out value="${status.value}" escapeXml="true"></c:out>
																		</spring:bind></td>
      </tr>
      <tr>
        <td><spring:message htmlEscape="true" code="Label.ORGLEVEL"></spring:message></td>
        <td> <spring:bind path="viewDepartment.listMinistry[${listMinistryDetailsRow.index}].orgLevel">&nbsp;
																				<c:if test="${fn:containsIgnoreCase(status.value,'C')}">Centre</c:if>
																				<c:if  test="${fn:containsIgnoreCase(status.value,'S')}">State</c:if>
																				
																		</spring:bind></td>
      </tr>
      <tr>
        <td><spring:message htmlEscape="true" code="Label.ORGNTYPE"></spring:message></td>
        <td><spring:bind path="viewDepartment.listMinistry[${listMinistryDetailsRow.index}].organizationType.orgType">&nbsp;
																				<c:out value="${status.value}" escapeXml="true"></c:out>
																		</spring:bind></td>
      </tr>
      </c:forEach>
    </tbody>
    
  </table>
           
                  
                  <div class="box-footer">                    
                   <button type="button" name="Submit3" class="btn btn-danger" value="" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose"><spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
                  </div>
                     </form:form> 
              </div>


                    </section>

                </div>

            </section>
		<script src="/LGD/JavaScriptServlet"></script>		
</body>
</html>