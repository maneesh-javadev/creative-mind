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
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>
	
</script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script src="js/shiftvillage.js"></script>
</head>
<body>
	 <section class="content">
        <div class="row">
            <section class="col-lg-12">
                 <div class="box">
                <div class="box-header with-border">
                  <h3 class="box-title"><spring:message htmlEscape="true" code="Label.InvalidatedLBDetails"></spring:message></h3>
                </div>
              <form:form action="" id="form1" method="POST" commandName="">
               <div class="box-header subheading">
					<label><h4><spring:message htmlEscape="true" code="Label.lbInvalidated"></spring:message></h4> </label>
				</div>
				<div class="box-body">
              <table class="table table-bordered table-hover">
    
				    <tbody>
				      <tr >
								<td width="15%"><spring:message htmlEscape="true"
										code="Label.INVALIDATELOCALGOVBODY"></spring:message> Code</td>
								<td width="15%"><spring:message htmlEscape="true"
										code="Label.LOCALBODYVERSION"></spring:message></td>
									<td width="15%"><spring:message htmlEscape="true"
										code="label.minor.version"></spring:message></td>
								<td width="30%"><spring:message htmlEscape="true"
					         code="Label.NAMEOFLOCALBODY"></spring:message></td>
								<td width="25%"><spring:message htmlEscape="true"
										code="Label.NAMEINLOCALLANGUAGE"></spring:message></td>
							</tr>														
							<tr >
								<td><c:out
										value="${lbdelete.localBodyCode}" escapeXml="true"></c:out></td>
								<td><c:out
										value="${lbdelete.localBodyVersion}" escapeXml="true"></c:out></td>
								<td><c:out
										value="${lbdelete.minorVersion}" escapeXml="true"></c:out></td>		
								<td><c:out
										value="${lbdelete.localBodyNameEnglish}" escapeXml="true"></c:out></td>
								<td><c:out
										value="${lbdelete.localBodyNameLocal}" escapeXml="true"></c:out></td>
							</tr>
				    </tbody>
				  </table>
               </div>
                
                  <div class="box-footer">                    
                    <button type="button" class="btn btn-danger pull-right" onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'"><i class="fa fa-times-circle"></i> Close</button>
                  </div>
                  
               </form:form>   
              
              </div>
               </section>
               </div>
               </section>
</body>
</html>

