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

</title>
<script type="text/javascript">
 	</script>

</head>
<body>

	<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                    <form:form action="viewLocalBodyAction.htm" method="POST" commandName="viewBodyType" id="frmModifyLocalBody">
		              <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewLocalBodyAction.htm"/>" />
				       <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message htmlEscape="true" code="Label.VIEWLOCALBODYTYPE"></spring:message></h3>
                                </div>
                                
                        <div class="box-header subheading">
                          <h4><spring:message htmlEscape="true" code="Label.VIEWLOCALGOVTBODYDET"></spring:message></h4>
                        </div>       
                                
                                
                                
     <div class="box">
       <div class="box-body">
       <c:forEach var="listGovtTypeDetails" varStatus="listLocalBodyTypeRow" items="${viewBodyType.listGovtTypeDetails}">
         <table class="table table-bordered table-hover">
    
		    <tbody>
		      <tr>
		        <td><spring:message htmlEscape="true"	code="Label.LOCALBODYTYPECODE"></spring:message></td>
		        <td><spring:bind path="viewBodyType.listGovtTypeDetails[${listLocalBodyTypeRow.index}].localBodyTypeCode">
									<c:out value="${status.value}" escapeXml="true"></c:out>
						</spring:bind></td>
		      </tr>
		      <tr>
		        <td><spring:message htmlEscape="true" code="Label.LOCALGOVTBODY"></spring:message></td>
		        <td><spring:bind  path="viewBodyType.listGovtTypeDetails[${listLocalBodyTypeRow.index}].localBodyTypeName">
										<c:out value="${status.value}" escapeXml="true"></c:out>
					</spring:bind></td>
		      </tr>
		      <tr>
		        <td><spring:message htmlEscape="true" code="Label.CATEGORY"></spring:message></td>
		        <td>  <c:if test="${fn:containsIgnoreCase(listGovtTypeDetails.category,'R')}">
                         <label> <c:out value="${status.value}" escapeXml="true"></c:out>Rural</c:if></label> 
                         <c:if test="${fn:containsIgnoreCase(listGovtTypeDetails.category,'U')}">
                        <label> <c:out value="${status.value}" escapeXml="true"></c:out>Urban
						</c:if></label></td>
		      </tr>
		      <tr>
		        <td><spring:message htmlEscape="true"	code="Label.LEVEL"></spring:message></td>
		        <td>	<c:if test="${fn:containsIgnoreCase(listGovtTypeDetails.level,'D')}">
                   <label> <c:out value="${status.value}" escapeXml="true"></c:out>District
							</c:if></label>
					<c:if test="${fn:containsIgnoreCase(listGovtTypeDetails.level,'I')}">
                   <label> <c:out value="${status.value}" escapeXml="true"></c:out>Intermediate
							</c:if></label>
          			 <c:if test="${fn:containsIgnoreCase(listGovtTypeDetails.level,'V')}">
                   <label> <c:out value="${status.value}" escapeXml="true"></c:out>Village
				       </c:if></label></td>
		      </tr>
		      
		    </tbody>
  </table>
               
                  <!-- /.box-body -->
                  <div class="box-footer">          
                      <button type="button" name="Submit3" class="btn btn-danger pull-right"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/> '"id="btnClose" > <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>          
                  </div>
              
              </div>
       
       </c:forEach>
       </div>
       
         
   
     </div>  
     
             
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script></body>
</body>
</html>