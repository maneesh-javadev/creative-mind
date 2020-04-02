<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../common/taglib_includes.jsp"%>
<%-- <%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> --%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>	
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>		
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>	
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>
	<!-- jquery pagination  -->

 <script src="resource/dashboard-resource/plugins/datatables/jquery-1.12.4.js"></script>


<script type="text/javascript">

function manageDepartmentState(url,id)
{
	
	dwr.util.setValue('departmentId', id, {	escapeHtml : false	});
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}

function validate_manage_dept_org() {
	$("#error_category").hide();
	if (checkSelectValue('category')) //if user select some value 
		{
		return true;
		}
	else{
		$("#error_category").show();
		return false;
		
		}
}

function checkSelectValue(objName) {
	if (document.getElementById(objName).selectedIndex > 0)
		return true;
	else
		return false;
}

$(document).ready(function() {
	$("#error_category").hide();
	$('#modifystateDepartment').dataTable({
		"sScrollY": "300px",
		"bScrollCollapse": true,
		"bPaginate": true,
		"aoColumnDefs": [
			{ "sWidth": "10%","sHeight":"20%", "aTargets": [ -1 ] }
		],
		"bJQueryUI": true,
		"aaSorting": [],
		"aoColumns": [
						null,
						null,
						null,
						{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false }
			  			
			  			],
		"sPaginationType": "full_numbers"
	});
} );


</script>

</head>
	<section class="content">
                <div class="row">
                    <section class="col-lg-12">
                   <form:form action="manageDeptorOrg.htm" id="form1" method="POST" commandName="viewDeptforstate" class="form-horizontal">
                   
				      	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="manageDeptorOrg.htm"/>"/>
	     				
	 	              
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><c:out value="Manage Department/Organization"/></h3>
                                </div>
                                 <div class="box-header subheading">
                                    <h4 ><spring:message htmlEscape="true"  code="Label.SELECTCRITERIA"></spring:message></h4>
                                </div>
                        <div class="box-body">
                            <div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELSEARCHENTITY"></spring:message> <span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								<form:select path="orgTypeCode"  class="form-control" id="category"  htmlEscape="true">
											  <form:option selected="selected" value="" label="--select--" htmlEscape="true"></form:option>
											  <c:forEach var="obj" varStatus="orgTypeRow" items="${organizationTypeList}">
											  	<c:if test="${obj.orgTypeCode ne 1}">
											  		<form:option value="${obj.orgTypeCode}" label="${obj.orgType}" htmlEscape="true"/>
											  	</c:if>
											  </c:forEach>
								</form:select>
											<div id="error_category" class="errormsg">	<spring:message htmlEscape="true" 
															code="errorMessage.notselectedoption"></spring:message> </div>
								  </div>
						</div>   
						
						
             
          
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                   <button  type="submit" name="Submit" class="btn btn-info" onclick="return validate_manage_dept_org();"   > <spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message></button>
				   <button type="button" name="Submit2" class="btn btn-warning"  onclick="javascript:location.href='manageDeptorOrg.htm?<csrf:token uri='manageDeptorOrg.htm'/>';" > <spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button>
                   <button type="button" class="btn btn-danger " name="Cancel"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div> 
    </div>   
       
  <c:if test="${! empty orgList}">	
 				 <div class="box">	
 				 	<div class="box-body">
					<table class="table table-bordered table-striped dataTable no-footer" id="modifystateDepartment">
													<thead>
															<tr >
																<th align="center"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></th>
																<th align="center"><c:out value="Department/Organization Code" escapeXml="true" />
																</th>
																<th align="left"><c:out value="Department/Organization Name (In English)" escapeXml="true" /></th>
																<th align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></th>
																<th align="center"><spring:message htmlEscape="true"  text="Rename"></spring:message></th>
															</tr>
											 				
													</thead>
													<tbody>
														<c:forEach var="obj" varStatus="objRow" items="${orgList}">
															<tr >
																<td align="center"><c:out value="${objRow.index+1}"  escapeXml="true"></c:out></td>
																<td align="center"><c:out value="${obj.orgCode}" escapeXml="true"></c:out></td>
																<td align="left"><c:out value="${obj.orgName}" escapeXml="true"></c:out></td>
																<td align="center"><a href="#" onclick="javascript:manageDepartmentState('viewDepartment.htm',${obj.orgCode});" ><i class="fa fa-eye" aria-hidden="true"></i>
																</a></td>
																<td align="center">
																	<a href="#"  onclick="javascript:manageDepartmentState('modifyOrganisationAtLevel.htm',${obj.orgCode});"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
						 											</a>
																</td>
															</tr>
											</c:forEach>
									</tbody>
						</table>
											
					   <form:input path="departmentId" type="hidden" name="departmentId" id="departmentId" htmlEscape="true" />
						<form:input path="entityCode" type="hidden" htmlEscape="true" />
						</div>
						</div>
</c:if>
       
   
             
    </form:form>      
   </section>
   </div>
   </section>
<script src="/LGD/JavaScriptServlet"></script>
		</body>
</html>

