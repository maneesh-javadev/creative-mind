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
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script src="js/shiftvillage.js"></script>
</head>
<body>
     <section class="content">
                <div class="row">
                    <section class="col-lg-12">
                      <div class="box">
                         <div class="box-header with-border">
                            <h3 class="box-title"><spring:message htmlEscape="true" code="Label.INVALIDATEDISTRICT"></spring:message></h3>
                          </div>
                          <div class="box-header subheading">
                             <h4><spring:message htmlEscape="true" code="Label.INVALIDATEDDISTRICT"></spring:message></h4>
                          </div>
                <form:form action="" id="form1" method="POST" commandName="">          
                <div class="box-body">  
                   
                  <table class="table table-bordered table-hover">
	                  <tbody>
	     					 <tr class="active">
	                             <td><spring:message htmlEscape="true" code="Label.DISTRICTCODE"></spring:message></td>
							     <td><spring:message htmlEscape="true" code="Label.DISTRICTVERSION"></spring:message></td>
								 <td><spring:message htmlEscape="true" code="Label.DISTRICTNAMEINENGLISH"></spring:message></td>
								 <td><spring:message htmlEscape="true" code="Label.DISTRICTNAMEINLOCAL"></spring:message></td>
	     					 </tr>
	      
	                           <c:forEach var="districtBean" varStatus="entityRow" items="${districtBean}">
								<tr>
									<td><c:out value="${districtBean.districtCode}" escapeXml="true"></c:out></td>
									<td><c:out value="${districtBean.districtVersion}" escapeXml="true"></c:out></td>
									<td><c:out value="${districtBean.districtNameEnglish}" escapeXml="true"></c:out></td>
									<td><c:out value="${districtBean.districtNameLocal}" escapeXml="true"></c:out></td>
								</tr>
								</c:forEach>
	                     </tbody>
                 </table>
                </div> 
                 <c:if test="${districtBeanMerge.size()>0}">
                <div class="box-body">
                <div class="box-header subheading">
                             <h4><spring:message htmlEscape="true" code="Label.SELECTTARDISTRICT"></spring:message></h4>
                </div>
                  <table class="table table-bordered table-hover">
	                  <tbody>
	     					 <c:forEach var="entity" varStatus="entityRow" items="${districtBeanMerge}">
								    <c:if test="${entity.districtCode!=pvcode || entityRow.count==1}" >
									
									<tr class="active">
										<td  rowspan="2"><spring:message htmlEscape="true" code="Label.DISTRICTCODE"></spring:message> </td>
										<td  rowspan="2"><spring:message htmlEscape="true" code="Label.DISTRICTVERSION"></spring:message> </td>
										<td  rowspan="2"><spring:message htmlEscape="true" code="Label.DISTRICTNAMEINENGLISH"></spring:message> </td>
										<td  rowspan="2"><spring:message htmlEscape="true" code="Label.DISTRICTNAMEINLOCAL"></spring:message> </td>
									</tr>
									<tr></tr>
									<tr >
										<td><c:out value="${entity.districtCode}" escapeXml="true"></c:out></td>
										<td><c:out value="${entity.districtVersion+1}" escapeXml="true"></c:out></td>
										<td><c:out value="${entity.districtNameEnglish}" escapeXml="true"></c:out></td>
										<td><c:out value="${entity.districtNameInLcl}" escapeXml="true"></c:out></td>
									</tr>
								</c:if>
								<c:if test="${entity.districtCode!=pvcode}" >	
								     <tr class="active">
									        <td><spring:message htmlEscape="true" code="Label.SUBDISTRICTCODE"></spring:message></td>
											<td><spring:message htmlEscape="true" code="Label.SUBDISTRICTVERSION"></spring:message></td>
											<td><spring:message htmlEscape="true" code="Label.SUBDISTRICTNAMEENGLISH"></spring:message></td>
											<td><spring:message htmlEscape="true" code="Label.SUBDISTRICTNAMELOCAL"></spring:message></td>
									</tr>
								</c:if>
											
								<tr >
									<td ><c:out	value="${entity.subdistrictCode}" escapeXml="true"></c:out></td>
									<td ><c:out	value="${entity.subDistrictVersion}" escapeXml="true"></c:out></td>
									<td><c:out	value="${entity.subdistrictNameEnglish}" escapeXml="true"></c:out></td>
									<td><c:out	value="${entity.subdistrictNameLocal}" escapeXml="true"></c:out></td>
								</tr>
							   <c:set var="pvcode"  value="${entity.districtCode}"/>	
						  </c:forEach>
	                     </tbody>
                 </table>
                </div>
               </c:if>
               </form:form>
                 
                 
                 
                 
                 
         <div class="box-footer">                    
            <button type="submit" class="btn btn-danger pull-right" name="close" class="btn"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'" ><i class="fa fa-times-circle"></i> Close</button>
         </div>
              
        </div>
     </section>
  </div>
</section>
      
	
</body>
</html>

