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
                  <form:form action="viewMinistryAction.htm" method="POST" commandName="viewOrganizationStateDetail">
										<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewMinistryAction.htm"/>" />
                    <div class="box">
                    <div class="box-header with-border">
                      <h4><spring:message htmlEscape="true"  code="Label.ViewOrganizationState"></spring:message></h4>
                    </div>
                        <div class="box-body">
                        <c:if test="${! empty listMinistry}">
						  <c:forEach var="listMinistry" items="${listMinistry}">	
                                  <table class="table table-bordered table-hover">
    
								    <tbody>
								      <tr>
								        <td><spring:message htmlEscape="true"  code="Label.ORGNAME"></spring:message></td>
								        <td><c:out value="${listMinistry.orgName}" escapeXml="true"></c:out></td>
								      </tr>
								      <tr>
								        <td><spring:message htmlEscape="true"  code="Label.NAMEORGLOCAL"></spring:message></td>
								        <td> <c:out value="${listMinistry.orgNameLocal}" escapeXml="true"></c:out></td>
								      </tr>
								      <tr>
								        <td><spring:message htmlEscape="true"  code="Label.SHORTORGNAME"></spring:message></td>
								        <td> <c:out value="${listMinistry.shortName}" escapeXml="true"></c:out></td>
								      </tr>
								      <tr>
								        <td><spring:message htmlEscape="true"  code="Label.ORGNTYPE"></spring:message></td>
								        <td> <c:out value="${listMinistry.organizationType.orgType}" escapeXml="true"></c:out></td>
								      </tr>
								      
								    </tbody>
								  </table>
                        </c:forEach>
                        </c:if>
                        </div>
                  <div class="box-footer">
		            <div class="col-sm-offset-2 col-sm-10">
		                 <div class="pull-right">
						  <button type="button" name="Submit3" class="btn btn-danger"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose" ><spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
                        </div>
                      </div>
                   </div>
                        
                    </div>
                    
                    </form:form>
             
                    </section>
                </div>
            </section>
		
</body>
</html>