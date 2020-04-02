<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<style type="text/css">
.redborder {
	border: 1px solid red;
}
</style>
<script type="text/javascript">var cPath="<%=contextPath%>";</script>
<script type="text/javascript" src="js/common.js"></script>
<link href="css/lgd_css.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDesignationDwr.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/js/designation_department.js'> </script>
<script type='text/javascript'>

	function validate() {
		if (document.getElementById('orgType').selectedIndex <= 0) {
			alert("Please select an Organization Type.");
			return false;
			} 
		else if (document.getElementById('olc').selectedIndex <= 0) {
			alert("Please select an Organization.");
			return false;
			}
		else
			{
			return true;
			}
		}
</script>
</head>

<body>
	<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                   <form:form action="get_designation_department.htm" method="post" commandName="lgdDesignation" name="lgdDesignation" id="lgdDesignation" class="form-horizontal">
				    <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="get_designation_department.htm"/>" />
				   <form:hidden  htmlEscape="true" path="designationType" />
				    <form:hidden  htmlEscape="true" path="flowName" />
                            <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message htmlEscape="true" code="Label.CREATEDESIGNATIONSTATE"></spring:message></h3>
                                </div>
                                
                <div class="box-body">
                     
						<div class="form-group">
						   <label   class="col-sm-3 control-label" id="lblOrgTypelIst"><spring:message htmlEscape="true" code="Label.ORGTYPELIST"></spring:message><span class="mandatory">*</span> 
						     <div id="tdError" style="display: <c:choose><c:when test="${isError}">inline</c:when><c:otherwise>none</c:otherwise></c:choose>">
											<div style="height: 40px; border: 1px solid red; background-color: #FFEBE8; padding-top: 10px;" align="center">
												<label id="errorCommon"><form:errors path="*" cssClass="errorBox"></form:errors></<label>
											</div>
									</div>
						   
						   </label>
						  <div class="col-sm-6">
						        
						      <form:select path="orgType"  id="orgType" class="form-control" onchange="getOrgbyTypeAtLevel(this.value,'0','0')" htmlEscape="true">
												<form:option  htmlEscape="true" value="0">
                                                  <esapi:encodeForHTMLAttribute><spring:message htmlEscape="true" code="Label.SELECT"></spring:message></esapi:encodeForHTMLAttribute>
												</form:option>
												<form:options htmlEscape="true"  items="${orgType}" itemLabel="orgType" itemValue="orgTypeCode" />
											</form:select>
											<div id="errorOrgType" style="height: 15px; padding-top: 3px;" class="errormsg"></div>
								</div>		
						</div>
						
						<div class="form-group">
						   <label  class="col-sm-3 control-label" id="lblOrgList"><spring:message htmlEscape="true" code="Label.ORGLIST"></spring:message><span class="mandatory">*</span> </label>
						  <div class="col-sm-6">
						      <form:select id="olc" path="olc"  class="form-control">
											<form:option htmlEscape="true" value="0">
											<esapi:encodeForHTMLAttribute><spring:message htmlEscape="true" code="Label.SELECT"></spring:message></esapi:encodeForHTMLAttribute>
											</form:option>
											<form:options items="${organization}" itemLabel="orgName" itemValue="orgCode" />
										</form:select>
										<div id="errorOlc" style="height: 15px; padding-top: 3px;" class="errormsg"></div>
								</div>		
						</div>
						
                        
            </div>
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                  <button id="submit" class="btn btn-info" type="submit" name="Submit" onclick="return validate();"  > <spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message></button>
				  <button type="button" class="btn btn-warning" name="Clear" value="Clear" onclick="go('designaton_master_center.htm');"  >Clear </button>
                  <button type="button" class="btn btn-danger " name="Cancel value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div> 
     </div>   
             
    </form:form>      
   </section>
   
 </div>
 </section>
 <script src="/LGD/JavaScriptServlet"></script>
</body>
</html>
