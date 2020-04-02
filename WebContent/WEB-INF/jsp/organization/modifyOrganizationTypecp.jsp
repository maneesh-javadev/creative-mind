<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrMinistryService.js'></script>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type="text/javascript" language="javascript">

$(document ).ready(function() {
	$('#form1 input[type=text]').attr("autocomplete",'off');
});


/* function validSearch()
{
	alert("hi");
	 var type=document.getElementById('orgType').value;
	var code=document.getElementById('code').value;
	alert(type);
	if(type!="" || type!=null)
		{
		
		for(var i=0;i<type.length;i++)
			{
			 var ch=type.charAt(i);
			 alert(ch);
			  if(((ch >= 65) && (ch <= 90)) || ((ch >= 97) && (ch <= 122)))
					 {
				 
					 }
			 else
				 {
				  alert("Organization Type must contain letters only!");
				  return false;
				 }
			}
		}
	
		
 	 if(code!="" || code!=null)
 		{
	 		
 		for(var i=0;i<type.length;i++)
		{
		 var ch=code.charAt(i);
		 if ((ch >= 48) && (ch <= 58) || (ch == 45))
				 {
			 
				 }
		 else
			 {
			  alert("Organization Type Code must contain Number only!");
			  return false;
			 }
		}
 		
 		
 		 
	  
	return true;
}
 */
function manageOrgType(url,id)
{
	
	 var conf=confirm('Are you sure to delete Organization Type');
	 if(conf==true)
		 {
		 dwr.util.setValue('orgTypeId', id, {	escapeHtml : false	});
			//document.getElementById('form1').method = "GET";
			document.getElementById('form1').action = url;
			document.getElementById('form1').submit();
		 }
		
		 
	
}


function editOrgType(url,id)
{
	dwr.util.setValue('orgTypeId', id, {	escapeHtml : false	});
	document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}

/* function getData()
{
	document.getElementById('text1').value = trim(document.getElementById('text1').value);
	document.getElementById('text2').value = trim(document.getElementById('text2').value);
	
	if (document.getElementById('text1').value!='' || document.getElementById('text2').value!='')
		document.forms['form1'].submit();
	else
		alert('Please enter search text!');
}

function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
} */
</script>

</head>
<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                    <form:form action="modifyOrganizationType.htm" id="form1" method="POST" commandName="orgTypeForm" class="form-horizontal">
				    <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyOrganizationType.htm"/>" />
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message htmlEscape="true" code="Label.VIEWORGTYPE"></spring:message></h3>
                                </div>
                   <c:if test="${empty organizationTypeDetails}">
                                 <div class="box-header subheading">
                                    <h4 ><spring:message htmlEscape="true" code="Label.SEARCHCRITERIA"></spring:message></h4>
                                </div>
                        <div class="box-body">
                            <div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message  htmlEscape="true" code="Label.ORGTYPECODE"></spring:message></label>
								<div class="col-sm-6" >
								 	<form:input type="text" id="code" htmlEscape="true" onkeypress="validateOnlyDigit(event)" path="code" class="form-control" />  
									   <form:errors path="code" htmlEscape="true" class="errormsg"></form:errors>
								  </div>
						   </div>   
						   
						      <div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.ORGTYPENAMEINENG"></spring:message></label>
								<div class="col-sm-6" >
								 		<form:input type="text" htmlEscape="true"  id="orgType" onkeypress="validateCharKeys(event)"  path="orgType" class="form-control" />  
									    <form:errors htmlEscape="true" path="orgType" class="errormsg"></form:errors>
								  </div>
						   </div> 
						   
						   
                 </div> 
             
               
                   
         
     
		    <div class="box-footer">
		           <div class="col-sm-offset-2 col-sm-10">
		                 <div class="pull-right">
		                   <button type="submit"  name="Submit" class="btn btn-success"  ><spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message></button>
		                   <button type="button" name="Submit2" class="btn btn-warning"  onclick="javascript:location.href='modifyOrganizationType.htm?<csrf:token uri='modifyOrganizationType.htm'/>';" ><spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button>
		                   <button type="button" class="btn btn-danger " name="Cancel"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
		                 </div>
		           </div>   
		       </div> 
       
       </c:if>
       
       
       
       <c:if test="${! empty organizationTypeDetails}">
					<div class="box-body">
						<table class="table table-bordered table-hover" width="98%" align="center">
							<tr >
							<td width="5%" rowspan="2"><spring:message htmlEscape="true" code="Label.SNO"></spring:message> </td>
							<td width="16%" rowspan="2"><spring:message htmlEscape="true" code="Label.ORGTYPECODE"></spring:message> </td>
						    <td width="21%" rowspan="2"><spring:message htmlEscape="true" code="Label.ORGTYPENAMEINENG"></spring:message> </td>
							<td colspan="2" align="center"><spring:message htmlEscape="true" code="Label.ACTION"></spring:message> </td>
							</tr>
											
						<tr class="tblRowTitle tblclear">
                          <td width="6%" align="center"><spring:message htmlEscape="true" code="Label.MODIFY"></spring:message> </td>
						  <td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></td>
						</tr>
						
                <c:forEach var="organizationTypeDetails" varStatus="OrgTypeRow" items="${organizationTypeDetails}">
					<tr >
						<td width="6%"><c:out value="${OrgTypeRow.index+1}" escapeXml="true"></c:out></td>
						<td><c:out value="${organizationTypeDetails.orgTypeCode}" escapeXml="true"></c:out> </td>
						<td align="left"><c:out value="${organizationTypeDetails.orgType}" escapeXml="true"></c:out> </td>
						<td align="center"><a href="#"  onclick="javascript:editOrgType('editOrganizationType.htm',${organizationTypeDetails.orgTypeCode});"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
						</a></td>
	                    <td align="center"><a href="#" onclick="javascript:manageOrgType('deleteOrganizationType.htm',${organizationTypeDetails.orgTypeCode});"><i class="fa fa-trash-o" aria-hidden="true"></i>
	                    </a></td>
					</tr>
				</c:forEach>
				 <form:input htmlEscape="true" path="orgTypeId" type="hidden" name="orgTypeId" id="orgTypeId" /> 
			</table>
		</div>
		   <div class="box-footer">                    
                   <button type="button" name="Submit3" class="btn btn-danger pull-right"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><i class="fa fa-times-circle"></i> Close</button>
                 </div>
				</c:if>
     </div>   
             
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script>

</body>
</html>

