<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html xmlns="http://www.w3.org/1999/xhtml">


<%@include file="../taglib_includes.jsp"%>
<%@include file="../homebody/commanInclude.jsp"%>
 
<head>
<title>E-Panchayat</title>
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib uri="/birt.tld" prefix="birt" %>
<script src="resource-tree/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource-tree/bootstrap.min.css">
<link href="css/error.css" rel="stylesheet" type="text/css" />

 <style>
	.btn-danger{
		margin: 0 0 0 772px;
	}
</style>
<script>

function setfinancialYear(code){
	$("#financialYear").val(code);
}
</script>

</head>

 <body>
 
 <section class="content">
  <div class="row">
  <section class="col-lg-12">
  
  <c:if test="${dataExists}">
               <div class="box">
               
		       <div class="box-header with-border">
		        <h3 class="box-title"><spring:message code="Label.VillageConvertedRuralToUrban ${financialYear}" htmlEscape="true"></spring:message></h3>
		        </div>
                <div class="box-body">
                    
               <% java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a"); %>
		       <birt:viewer id="birtViewer" reportDesign="${report_design}" pattern="frameset" height="755" width="1035" style=""   baseURL="${serverLoc}">
					<center>
					<birt:param name="financialYear" value="${financialYear}"></birt:param>
					<birt:param name="rpt_curr_dt_tm" value="<%=df.format(new java.util.Date())%>"></birt:param>
                   </birt:viewer>
                   </center>		
                    </div>
		            <div class="box-footer">                    
			              <button type="button" class="btn btn-danger pull-right" id="close" name="close" value="Close" onclick="javascript:location.href='welcome.do'"><i class="fa fa-times-circle"></i> Close</button>
			          </div>
		              </div>
              </c:if>
  </section>
 </div>
 </section>
 </body>
 
</html>