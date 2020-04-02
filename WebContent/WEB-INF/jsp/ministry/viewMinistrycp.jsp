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

<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type="text/javascript" language="javascript">

function manageMinistry(url,id)
{
	dwr.util.setValue('ministryId', id, {	escapeHtml : false	});
	//document.getElementById('form1').method = "GET";
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
                   <form:form action="viewministrypost.htm" id="form1" method="POST"  commandName="viewMinistry" class="form-horizontal">
				       <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewministrypost.htm"/>" />
		              
				       <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message htmlEscape="true" code="Label.VIEWMINISTRY"></spring:message></h3>
                                </div>
     <div class="box"  id="cat">
        <div class="box-header subheading">
                          <h4><spring:message htmlEscape="true" code="Label.SEARCHCRITERIA"></spring:message></h4>
                        </div>
       <div class="box-body">
       
          
        
            
             <div class="form-group">
               <label class="col-sm-3 control-label" ><spring:message htmlEscape="true" code="Label.MINISTRYCODE"></spring:message></label>
               <div class="col-sm-6">
                 	<form:input type="text" id="text1"  htmlEscape="true" onkeypress="validateNumericKeys(event)" path="code" class="form-control" maxlength="10" /> </label>
					<form:errors path="code" class="errormsg"  htmlEscape="true" ></form:errors>
               </div>
             </div>
             
              
              <div class="form-group">
               <label class="col-sm-3 control-label" > <spring:message htmlEscape="true" code="Label.MINISTRYNAMEINENG"></spring:message></label>
               <div class="col-sm-6">
                 	<form:input type="text" id="text2"  htmlEscape="true" onkeypress="validateCharKeys(event)" path="ministryName"  class="form-control" maxlength="200" />
	                  <form:errors path="ministryName" class="errormsg"></form:errors> 
               </div>
             </div>
             
         </div>    
           
         <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                	<button type="submit" name="Submit" class="btn btn-success"  	 ><spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message></button> 
					<button type="button" name="Submit3" class="btn btn-warning" id="btnClear" onclick="javascript:location.href='viewministry.htm?<csrf:token uri='viewministry.htm'/>';" > <spring:message code="Button.CLEAR" htmlEscape="true"></spring:message></button>
					<button type="button" name="Submit3" class="btn btn-danger"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button>
                 </div>
           </div>   
       </div> 
           
           
     </div>      
         
        
      
        
        
        <c:if test="${! empty ministrydetail}">
		<div class="box">
		  <div class="box-body">
			<table class="table table-bordered table-hover" >
					<tr>
						  <td width="5%" rowspan="2"><spring:message htmlEscape="true" code="Label.SNO"></spring:message> </td>
						  <td width="16%" rowspan="2"><spring:message htmlEscape="true" code="Label.MINISTRYCODE"></spring:message> </td>
						  <td width="21%" rowspan="2"><spring:message htmlEscape="true" code="Label.MINISTRYNAMEINENG"></spring:message> </td>
						  <td width="21%" rowspan="2"><spring:message htmlEscape="true" code="Label.MINISTRYSHORTNAMEINENG"></spring:message>  </td>
						  <td colspan="6" align="center"><spring:message htmlEscape="true" code="Label.ACTION"></spring:message> </td>
					 </tr>
					<tr >
                       <td width="6%" align="center"><spring:message htmlEscape="true" code="Label.VIEW"></spring:message> </td>
					   <td width="6%" align="center"><spring:message htmlEscape="true" code="Label.MODIFY"></spring:message> </td>
					</tr>

				<c:forEach var="ministrydetail" varStatus="listMinistryRow" items="${ministrydetail}">
					<tr >
					   <td width="6%"><c:out value="${listMinistryRow.index+1}" escapeXml="true"></c:out></td>
					  <td><c:out value="${ministrydetail.orgCode}" escapeXml="true"></c:out></td>
					  <td align="left"><c:out value="${ministrydetail.orgName}" escapeXml="true"></c:out> </td>
					  <td align="left"><c:out value="${ministrydetail.shortName}" escapeXml="true"></c:out> </td>
					  <td width="8%" align="center"><a href="#" onclick="javascript:manageMinistry('viewMinistryDetail.htm',${ministrydetail.orgCode});"><i class="fa fa-eye" aria-hidden="true"></i>
						</a>
					 </td>

													
					<td width="8%" align="center"><a href="#" onclick="javascript:manageMinistry('modifyMinistry.htm',${ministrydetail.orgCode});" ><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
					</a>
				</c:forEach>
					<form:input path="ministryId" type="hidden" htmlEscape="true" name="ministryId" id="ministryId" />
				</table>
				
								
						</div>
					</div>
				</c:if>
       
      	<c:if test="${fn:length(viewPage) > 0}">
					<c:if test="${empty ministrydetail}">
						<div class="box">
							<div class="box-body">
								
										<spring:message htmlEscape="true" code="error.NOMINISTRYFOUND"></spring:message>
									
							</div>
						</div>
					</c:if>
				</c:if>
         
   
 
     
             
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>

