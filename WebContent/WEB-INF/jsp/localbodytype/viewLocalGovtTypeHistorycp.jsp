<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>

</head>
    <body>
			<section class="content">

                <div class="row">
                    <section class="col-lg-12">
				       <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message htmlEscape="true" code="Label.VIEWLOCALGOVTBODYHIST"></spring:message></h3>
                                </div>
                                
                            
                                
                                
                                
     <div class="box">
       <div class="box-body">
      <c:if test="${! empty SEARCH_GOVTTYPE_HISTORY_KEY}">	
        <div class="box-header subheading"><h4><spring:message htmlEscape="true"  code="Label.LOCALBODYTYPEHISTORY"></spring:message></h4></div>
      
         <table class="table table-bordered table-hover">
    
		    <tbody>
		     <tr >
				<td width="7%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
				<td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.LOCALBODYTYPECODE"></spring:message></td>
				<td width="30%" rowspan="2"><spring:message htmlEscape="true"  code="Label.LOCALGOVTBODY"></spring:message></td>
				<td width="24%" rowspan="2"><spring:message htmlEscape="true"  code="Label.CATEGORY"></spring:message></td>
				<td width="24%" rowspan="2"><spring:message htmlEscape="true"  code="Label.ACTIVE"></spring:message></td>
			</tr>
		     <c:forEach var="listLocalBodyTypeHistory" varStatus="listLocalBodyTypeRow" items="${SEARCH_GOVTTYPE_HISTORY_KEY}">
				<tr >
					<td width="6%"><c:out value="${listLocalBodyTypeRow.index+1}" escapeXml="true"></c:out></td>
					<td align="center"><c:out value="${listLocalBodyTypeHistory.localBodyTypeCode}" escapeXml="true"></c:out></td>
					<td align="left"><c:out value="${listLocalBodyTypeHistory.localBodyTypeName}" escapeXml="true"></c:out></td>
					<td><c:out value="${listLocalBodyTypeHistory.category}" escapeXml="true"></c:out></td>
					<td><c:out value="${listLocalBodyTypeHistory.isactive}" escapeXml="true"></c:out></td>
				</tr>
			</c:forEach>
		      
		      
		    </tbody>
  </table>
               
                  <!-- /.box-body -->
                  <div class="box-footer">          
                      <button type="button" name="Submit3" class="btn btn-success pull-right"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/> '"id="btnClose" > <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>          
                  </div>
    </c:if>    
    
         <c:if test="${fn:length(viewPage) > 0}"> 
					<c:if test="${empty SEARCH_GOVTTYPE_HISTORY_KEY}">
					<div  id="divData">
							<div class="form-group">
								<div colspan="4" align="center"><spring:message htmlEscape="true" code="Error.noresult"></spring:message></div>
							</div>
						</div>
					</c:if>
				</c:if>      
              </div>
     
       </div>
       
         
   
     </div>  
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script></body>
</body>
</html>

