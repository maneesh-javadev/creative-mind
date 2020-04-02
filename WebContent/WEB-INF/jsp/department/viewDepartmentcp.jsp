<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


<%@include file="../common/taglib_includes.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>
<body onload="loadPage();">	 
	           <section class="content">
                <div class="row">
                    <section class="col-lg-12">
                        <div class="box">
                <div class="box-header with-border">
                  <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.VIEWDEPTDETAIL"></spring:message></h3>
                </div>
       <form:form action="viewMinistryAction.htm" method="POST" commandName="viewMinistry">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewMinistryAction.htm"/>"/>
			<div class="box-header subheading"><h4><spring:message htmlEscape="true"  code="Label.VIEWDEPTDETAIL"></spring:message></h4>
													</div>
              <table class="table table-bordered table-hover">
    
              <tbody>
                 <c:forEach var="listDept" varStatus="listDepartmentRow" items="${listDept}">
					<tr >
					  <td width="6%"><c:out value="${listDepartmentRow.index+1}" escapeXml="true"></c:out></td>
					   <td><c:out value="${listDept.orgCode}" escapeXml="true"></c:out></td>
					    <td align="left"><c:out value="${listDept.orgName}" escapeXml="true"></c:out></td>
						<td  width="8%" align="center"><a href="#" onclick="javascript:manageDepartmentState('viewDepartment.htm',${listDept.orgCode});"><i class="fa fa-eye" aria-hidden="true"></i>
                            </a></td>
						<td  width="8%" align="center"><a href="#"  onclick="javascript:manageDepartmentState('modifyDepartment.htm',${listDept.orgCode});"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                          </a></td>
						<td  width="8%" align="center"><a href="#"  onclick="javascript:manageDepartmentState('Deletedepartmentforstate.htm',${listDept.orgCode});"><i class="fa fa-trash" aria-hidden="true"></i>
                             </a></td> 	
					</tr>
			 </c:forEach>
			 
				<c:forEach var="listDept" varStatus="listMinistryDetailsRow" items="${listDistDept}">		 
				      <tr>
				        <td><spring:message htmlEscape="true"  code="Label.DEPTCODE"></spring:message></td>
				        <td> <c:out value="${listDept.orgCode}" escapeXml="true"></c:out></td>
				      </tr>
				      <tr>
				        <td><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message></td>
				        <td><c:out value="${listDept.orgName}" escapeXml="true"></c:out></td>
				      </tr>
				      <tr>
				        <td><spring:message htmlEscape="true"  code="Label.DEPTNAMEINLOCAL"></spring:message></td>
				        <td><c:out value="${listDept.orgNameInLocal}" escapeXml="true"></c:out></td>
				      </tr>
				      <tr>
				        <td><spring:message htmlEscape="true"  code="Label.DEPTSHORTNAMEINENG"></spring:message></td>
				        <td><c:out value="${listDept.shortDeptName}" escapeXml="true"></c:out></td>
				      </tr>
			      
			      </c:forEach>
    </tbody>
  </table>
               
                  <div class="box-footer">                    
                    <button type="button" class="btn btn-danger pull-right"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><i class="fa fa-times-circle"></i> Close</button>
                  </div>
      </form:form>
              </div>
          </section>
        </div>
   </section>
		<script src="/LGD/JavaScriptServlet"></script>	
</body>
</html>