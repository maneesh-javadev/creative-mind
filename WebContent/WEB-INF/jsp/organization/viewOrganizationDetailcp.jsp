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
                  <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.ViewOrganizationCentral"></spring:message></h3>
                </div>
                
                <div class="box-header subheading"><h4><spring:message htmlEscape="true"  code="Label.MINISTRYDETAIL"></spring:message></h4></div>
              <form:form action="viewMinistryAction.htm" method="POST" commandName="viewOrganizationCenterDetail">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewMinistryAction.htm"/>" />
				
	<c:if test="${! empty listMinistry}">
		<c:forEach var="listMinistry" items="${listMinistry}">	
              <table class="table table-bordered table-hover">
    
    			    <tbody>
    			                <tr>
							        <td><spring:message htmlEscape="true"  code="Label.ORGNAMEINENG" ></spring:message></td>
							        <td><c:out value="${listMinistry.orgName}" escapeXml="true"></c:out></td>
							      </tr>
							      <tr>
							        <td><spring:message htmlEscape="true" code="Label.ORGNAMEINLOCAL"></spring:message></td>
							        <td><c:out value="${listMinistry.orgNameLocal}" escapeXml="true"></c:out></td>
							      </tr>
							      <tr>
							        <td><spring:message htmlEscape="true"  code="Label.ORGSHORTNAMEINENG"></spring:message></td>
							        <td><c:out value="${listMinistry.shortName}" escapeXml="true"></c:out></td>
							      </tr>
							      
    			   </tbody>
  			</table>
  			</c:forEach>
  			</c:if>
                  <div class="box-footer">                    
                    <button type="submit" class="btn btn-success pull-right" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose" ><i class="fa fa-times-circle"></i> Close</button>
                  </div>
            </form:form>
              
              </div>
           </section>
        </div>
     </section>
</body>
</html>