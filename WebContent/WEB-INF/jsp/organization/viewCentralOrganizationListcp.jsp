<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="../common/taglib_includes.jsp"%>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/ministry.js"></script>
<script type="text/javascript">

function manageOrganization(url,id) {
	dwr.util.setValue('organizationId', id, {	escapeHtml : false	});
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}

function getData() {
	var ministryName = document.getElementById('ddMinistry');
	var deptName = document.getElementById('ddDepartment');
	
	if(ministryName.value == "" || ministryName.value == "0") { 
		alert('Please select ministry');
		return false;
	} else if(deptName.value == "" || deptName.value == "0") { 
		alert('Please select depertment');
		return false;
	} else {
		document.forms['form1'].submit();
		return true;
	}
}

function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}

</script>
</head>
<body oncontextmenu="return false">
		<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                  <form:form action="vieworganizationbydepartmentcodeCenter.htm" id="form1" method="POST" commandName="viewDept" class="form-horizontal">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="vieworganizationbydepartmentcodeCenter.htm"/>" />
		              
				       
     <div class="box"  id="cat">
     <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message htmlEscape="true" code="Label.VIEWORG"></spring:message></h3>
                                </div>
        <div class="box-header subheading">
                          <h4><spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message>
                        </div>
       <div class="box-body">
       
          
        
            
             <div class="form-group">
               <label class="col-sm-3 control-label" ><spring:message htmlEscape="true" code="Label.SELMIN"></spring:message><span class="errormsg">*</span></label>
               <div class="col-sm-6">
                 	<form:select path="ministryName"  class="form-control" 	id="ddMinistry" onchange="getDepartmentListByMinistry(this.value)" htmlEscape="true">
													<form:option value="" htmlEscape="true"><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
													<form:options items="${userMinistryList}" itemLabel="orgName" itemValue="organizationPK.orgCode" htmlEscape="true"/>
													</form:select> <form:errors htmlEscape="true" path="ministryName" class="errormsg"></form:errors>
               </div>
             </div>
             
              
              <div class="form-group">
               <label class="col-sm-3 control-label" > <spring:message htmlEscape="true" code="Label.SELDEPT"></spring:message><span class="errormsg">*</span></label>
               <div class="col-sm-6">
                 	<form:select path="deptName"  class="form-control" id="ddDepartment" htmlEscape="true">
													<form:options items="${departmentList}" itemLabel="orgName" itemValue="organizationPK.orgCode" htmlEscape="true"/>
													</form:select>
               </div>
             </div>
             
         </div>    
           
         <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                	<button type="button" name="Submit" class="btn btn-info" onclick="getData();"  ><spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message></button>
					<button type="button" name="Submit2" class="btn btn-warning" onclick="javascript:location.href='vieworganization.htm?<csrf:token uri='vieworganization.htm'/>';" ><spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button>
					<button type="button" name="Submit3" class="btn btn-danger"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button>
                 </div>
           </div>   
       </div> 
           
           
     </div>      
         
        
      
        
        
     <c:if test="${! isSearchPage}">
				<c:choose>
					<c:when test="${! empty  listOrganization}">
						<div class="box">
							<div class="box-body">
								<table class="table table-bordered table-hover" align="center">
												<tr>
													<td  rowspan="2"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></td>
													<td  rowspan="2"><spring:message htmlEscape="true" code="Label.ORGCODE"></spring:message></td>
													<td  rowspan="2"><spring:message htmlEscape="true" code="Label.ORGNAMEINENG"></spring:message></td>
													<td colspan="6" align="center"><spring:message htmlEscape="true" code="Label.ACTION"></spring:message></td>
												</tr>
												<tr >

													<td width="6%" align="center"><spring:message htmlEscape="true" code="Label.VIEW"></spring:message></td>
													<td width="6%" align="center"><spring:message htmlEscape="true" code="Label.MODIFY"></spring:message></td>
													<td width="6%" align="center"><spring:message htmlEscape="true" code="Label.DELETE"></spring:message></td>
												</tr>

												<c:forEach var="listOrganization" varStatus="listOrganizationRow" items="${listOrganization}">
													<tr >
														<td width="6%"><c:out value="${listOrganizationRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${listOrganization.orgCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listOrganization.orgName}" escapeXml="true"></c:out>(<c:out value="${listOrganization.organizationType.orgType}" escapeXml="true"></c:out>)</td>
														<td width="8%" align="center"><a href="#" onclick="javascript:manageOrganization('viewOrganizationCenterDetail.htm',${listOrganization.orgCode});"> <i class="fa fa-eye" aria-hidden="true"></i>
														</a></td>
														<td width="8%" align="center"><a href="#" onclick="javascript:manageOrganization('modifyOrganizationCentral.htm',${listOrganization.orgCode});" ><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
													      </a></td>
														<td width="8%" align="center"><a href="#" onclick="javascript:manageOrganization('DeleteOrganizationCenterDetail.htm',${listOrganization.orgCode});" ><i class="fa fa-trash-o" aria-hidden="true"></i></a>
													</tr>
												</c:forEach>
												<form:input path="organizationId" type="hidden" name="organizationId" id="organizationId" />
											</table>
									
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<script>
									alert("No data found");
								</script>
					</c:otherwise>
				</c:choose>
				</c:if>
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>