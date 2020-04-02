<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
 
		</script>
</head>
<body >	 
  <section class="content">
      <div class="row">
         <section class="col-lg-12">
             <div class="box">
                <div class="box-header with-border">
                  <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.MINISTRYDETAIL"></spring:message></h3>
                </div>
                
                <div class="box-header subheading"><h4><spring:message htmlEscape="true"  code="Label.MINISTRYDETAIL"></spring:message></h4></div>
                <form:form action="viewMinistryAction.htm" method="POST" commandName="viewMinistry">
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewMinistryAction.htm"/>" />
              <table class="table table-bordered table-hover">
              <c:forEach var="listMinistry" varStatus="listMinistryDetailsRow" items="${viewMinistry.listMinistry}">
    
    			    <tbody>
    			                <tr>
							        <td><spring:message htmlEscape="true"  code="Label.MINISTRYCODE"></spring:message></td>
							        <td><spring:bind path="viewMinistry.listMinistry[${listMinistryDetailsRow.index}].orgCode">
											 <c:out value="${status.value}" escapeXml="true"></c:out>
									</spring:bind></td>
							      </tr>
							      <tr>
							        <td><spring:message htmlEscape="true"  code="Label.MINISTRYNAMEINENG"></spring:message></td>
							        <td><spring:bind path="viewMinistry.listMinistry[${listMinistryDetailsRow.index}].orgName">&nbsp;
												<c:out value="${status.value}" escapeXml="true"></c:out>
										</spring:bind></td>
							      </tr>
							      <tr>
							        <td><spring:message htmlEscape="true"  code="Label.MINISTRYSHORTNAMEINENG"></spring:message></td>
							        <td><spring:bind path="viewMinistry.listMinistry[${listMinistryDetailsRow.index}].shortName">&nbsp;
																							<c:out value="${status.value}" escapeXml="true"></c:out>
																							</spring:bind></td>
							      </tr>
							      
    			   </tbody>
    			</c:forEach>
  			</table>
                  <div class="box-footer">                    
                    <button type="button" class="btn btn-danger pull-right" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose" ><i class="fa fa-times-circle"></i> Close</button>
                  </div>
            </form:form>
              
              </div>
           </section>
        </div>
     </section>
</body>
</html>