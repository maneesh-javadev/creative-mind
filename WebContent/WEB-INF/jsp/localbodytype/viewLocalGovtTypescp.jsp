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
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrLocalBodyService.js'></script>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type="text/javascript" language="javascript">

function manageLocalGovt(url,id)
{
	dwr.util.setValue('localgovtId', id, {	escapeHtml : false	});
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit(); 
} 
</script>
</head>
<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
		<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                    <form:form action="viewlocalgovttype.htm" id="form1" method="POST" commandName="viewGovtType" class="form-horizontal">
				     <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewlocalgovttype.htm"/>" />
				      
				                
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title">View Local Body Type</h3>
                                </div>
                                 <div class="box-header subheading">
                                    <h4 ><spring:message code="Label.SELSOURCE" htmlEscape="true"></spring:message></h4>
                                </div>
                        <div class="box-body">
                            <div class="form-group">
								<label  class="col-sm-3 control-label" for = "category"><spring:message  htmlEscape="true" code="Label.LOCALBODYCAT"></spring:message> <span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								 <form:select path="category" class="form-control" id="category" htmlEscape="true" >
								                 <form:option value="N" label="Select" htmlEscape="true"/>
										         <form:option value="P" label="Panchayat" htmlEscape="true" />
										         <form:option value="T" label="Traditional" htmlEscape="true" />
										         <form:option value="R" label="Rural" htmlEscape="true"/>
										         <form:option value="U" label="Urban" htmlEscape="true" />
									</form:select>
								  </div>
						</div>  
						
						     <div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.NOOFRECORDPERPAGE"></spring:message></label>
								<div class="col-sm-6" >
								 <form:select htmlEscape="true" path="pageRows" class="form-control" >
										<form:option value="5" label="5" htmlEscape="true" />
										<form:option value="10" label="10" htmlEscape="true" />
										<form:option value="25" label="25" selected="selected" htmlEscape="true" />
										<form:option value="50" label="50" htmlEscape="true"/>
										<form:option value="100" label="100" htmlEscape="true" />
									</form:select>
								  </div>
						</div> 
						 
                 </div> 
             
                       
        
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                  <button type="button" name="Submit" class="btn btn-info" onclick="excludeTopSelectAndSubmit('category');doSubmit('form1','Please select a local body type to be viewed');"  ><spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message></button>
			       <button type="button" name="Submit2" class="btn btn-warning"  onclick="javascript:location.href='viewlocalgovttype.htm?<csrf:token uri='viewlocalgovttype.htm'/>';" ><spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button>
                   <button type="button" class="btn btn-danger " name="Cancel"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div> 
     </div> 
     
     
     <c:if test="${! empty SEARCH_GOVTTYPE_RESULTS_KEY}">
     <div class="box">
       <div class="box-body">
         <table class="table table-bordered table-hover">
				<tr >
					<td rowspan="2"><spring:message code="Label.SNO"></spring:message></td>
					<td  rowspan="2"><spring:message	code="Label.LOCALBODYTYPECODE"></spring:message></td>
					<td  rowspan="2"><spring:message code="Label.LOCALBODYTYPE"></spring:message></td>
					<td colspan="6" align="center"><spring:message code="Label.ACTION"></spring:message></td>
				</tr>
										
				<tr class="tblRowTitle tblclear">
                    <td  align="center"><spring:message code="Label.VIEW"></spring:message></td>
					<td  align="center"><spring:message code="Label.MODIFY"></spring:message></td>
					<td  align="center"><spring:message code="Label.HISTORY"></spring:message></td>
				</tr>
										
				<c:forEach var="listGovtTypeDetails" varStatus="listGovtTypeRow" items="${SEARCH_GOVTTYPE_RESULTS_KEY}">
					<tr >
					  <td ><c:out value="${listGovtTypeRow.index+1}" escapeXml="true"></c:out></td>
					  <td><c:out value="${listGovtTypeDetails.localBodyTypeCode}" escapeXml="true"></c:out></td>
					  <td align="left"><c:out value="${listGovtTypeDetails.localBodyTypeName}" escapeXml="true"></c:out></td>
					   <td   align="center"><a href="#"  onclick="javascript:manageLocalGovt('viewLocalBodyTypeDetail.htm',${listGovtTypeDetails.localBodyTypeCode});"><i class="fa fa-eye" aria-hidden="true"></i>
				        <td   align="center"><a href="#" onclick="javascript:manageLocalGovt('modifyLoacalGovTypebyId.htm',${listGovtTypeDetails.localBodyTypeCode});"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>

						<td   align="center"><a href="#" onclick="javascript:manageLocalGovt('viewLocalBodyTypeHistory.htm',${listGovtTypeDetails.localBodyTypeCode});"><i class="fa fa-history" aria-hidden="true"></i>
					</tr>
				</c:forEach>
			<form:input path="localgovtId" type="hidden" name="localgovtId" id="localgovtId"  />											
		</table>
       
       
         <div width="96" align="right" class="pre">	<a href="#"><spring:message htmlEscape="true" code="Label.PREVIOUS"></spring:message></a></div>
		<div width="24" align="center" class="pageno"></div>
		<div width="51" align="right" class="nxt tblclear"><a href="#"><spring:message htmlEscape="true" code="Label.NEXT"></spring:message></a><!-- </td> --></div>
		<div width="16" align="right" class="nxt tblclear"></div>
       
       </div>
       
          <c:if test="${fn:length(viewPage) > 0}"> 
					<c:if test="${empty SEARCH_GOVTTYPE_RESULTS_KEY}">
					<div class ="box-body">
							<div align="center"><spring:message htmlEscape="true" code="error.NOLOCALBODYTYPEFOUND"></spring:message></div>
				     </div>
				   </c:if>
			     </c:if>
			     
   
     </div>  
     </c:if>
     
             
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script></body>
</html>

 