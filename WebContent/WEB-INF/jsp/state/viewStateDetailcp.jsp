<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- <script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script> -->
<script type="text/javascript">
 
		</script>

</head>
<body>
 <section class="content">
           <div class="row">
               <section class="col-lg-12">
                  <div class="box">
                     <div class="box-header with-border">
                         <h3 class="box-title"><spring:message code="Label.VIEWSTATE" htmlEscape="true"></spring:message></h3>
                     </div>
                     <form:form action="viewStateAction.htm" method="POST" commandName="stateForm" id="frmViewState">
					 <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewStateAction.htm"/>" />
					 <div class="box-body">
					   <c:forEach var="listStateDetails" varStatus="listStateDetailsRow" items="${stateForm.listStateDetails}">
                        <table class="table table-bordered table-hover">
    
                    <tbody>
                    <tr>
                       <td><spring:message code="Label.STATECODE" htmlEscape="true"></spring:message></td>
                       <td><spring:bind path="stateForm.listStateDetails[${listStateDetailsRow.index}].stateCode"> <c:out value="${status.value}" escapeXml="true"></c:out>
					        </spring:bind> </td>
      			    </tr>
      			    
                    <tr>
                       <td><spring:message code="Label.STATEVERSION" htmlEscape="true"></spring:message></td>
                       <td><spring:bind path="stateForm.listStateDetails[${listStateDetailsRow.index}].stateVersion"> <c:out value="${status.value}" escapeXml="true"></c:out>
							</spring:bind></td>
      			    </tr>
      			    
      			    
      			    <tr>
                       <td><spring:message code="label.minor.version" htmlEscape="true"></spring:message></td>
                       <td><spring:bind path="stateForm.listStateDetails[${listStateDetailsRow.index}].minorVersion"> <c:out value="${status.value}" escapeXml="true"></c:out>
							</spring:bind></td>
      			    </tr>
      			    
                   <tr>
                       <td><spring:message code="Label.STATENAMEINENGLISH" htmlEscape="true"></spring:message></td>
                       <td><spring:bind path="stateForm.listStateDetails[${listStateDetailsRow.index}].stateNameEnglish"> <c:out value="${status.value}" escapeXml="true"></c:out>
							</spring:bind></td>
      			    </tr>
      			    
      			    
      			    
                   <tr>
                       <td><spring:message code="Label.STATENAMEINLOCAL" htmlEscape="true"></spring:message></td>
                       <td> <spring:bind path="stateForm.listStateDetails[${listStateDetailsRow.index}].stateNameLocal">  <c:out value="${status.value}" escapeXml="true"></c:out>
							</spring:bind></td>
      			    </tr>
                    <tr>
                       <td><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message></td>
                       <td><spring:bind path="stateForm.listStateDetails[${listStateDetailsRow.index}].orderNocr"><c:out value="${status.value}" escapeXml="true"></c:out>
							</spring:bind> </td>
      			    </tr>
                   <tr>
                       <td><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message></td>
                       <td><spring:bind path="stateForm.listStateDetails[${listStateDetailsRow.index}].orderDatecr"><c:out value="${status.value}" escapeXml="true"></c:out>
								 </spring:bind> </td>
      			    </tr>
      			    <tr>
                       <td><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message></td>
                       <td><spring:bind path="stateForm.listStateDetails[${listStateDetailsRow.index}].ordereffectiveDatecr"><c:out value="${status.value}" escapeXml="true"></c:out>
						   </spring:bind> </td>
      			    </tr>
      			    <tr>
                       <td><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message></td>
                       <td><spring:bind path="stateForm.listStateDetails[${listStateDetailsRow.index}].gazPubDatecr"><c:out value="${status.value}" escapeXml="true"></c:out>
							</spring:bind>  </td>
      			    </tr>
    </tbody>
  </table>
 

<div class="box-header subheading">
           <h4><spring:message code="Label.GISNODES" htmlEscape="true"></spring:message></h6>
</div>      
<div class="form-group">
      <table class="table table-bordered table-hover">
      <tr>
        <td><spring:message code="Label.LATITUDE" htmlEscape="true"></spring:message></td>
        <td><spring:message code="Label.LONGITUDE" htmlEscape="true"></spring:message></td>
      </tr>
         <c:set var="str1" value="${listStateDetails.cordinate}"/> 
		<c:forEach var="str2" items="${fn:split(str1, ',')}">
      <tr>
        <c:set var="count" value="1"></c:set>
	    <c:forEach var="num" items="${fn:split(str2, ':')}">	
	    <c:if test="${count==1}">
          <td><c:out value="${num}" escapeXml="true"></c:out></td>
        </c:if>													     
		<c:if test="${count!=1}">
			<td><c:out value="${num}" escapeXml="true"></c:out></td></c:if>	
		   <c:set var="count" value="${count+1}"></c:set>  
		</c:forEach>		
      
      </tr>
       </c:forEach>
       </table>
</c:forEach>
      
 
 
 </div>
 
 
 
 </div>
  </form:form>
               
    <div class="box-footer">                    
            <button type="submit" name="Submit3" class="btn btn-danger pull-right" value="<spring:message code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose" ><i class="fa fa-times-circle"></i> Close</button>
     </div>
       </div>
     </section>
    </div>
  </section>
</body>
</html>