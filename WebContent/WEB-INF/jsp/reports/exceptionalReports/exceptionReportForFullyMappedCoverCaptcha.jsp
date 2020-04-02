<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@include file="../../common/taglib_includes.jsp"%>
<%@ taglib uri="/birt.tld" prefix="birt" %>
<script src="resource-tree/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource-tree/bootstrap.min.css">

<style>
	.btn-danger{
		margin: 0 0 0 772px;
	}
</style>
</head>

<body>
<section class="content">
  <div class="row">
          <!-- main col -->
     <section class="col-lg-12">

        <div class="box">
		      <div class="box-header with-border">
		        <h3 class="box-title"><c:out value="List of land regions FULLY mapped under more than one local bodies"/></h3>
		      </div><!-- /.box-header -->
		      
		    <div align="center">
		                       		<% java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a"); %>
		       <birt:viewer id="birtViewer" reportDesign="${report_design}" pattern="frameset" height="1024"
					width="1500"   baseURL="${serverLoc}">
					<birt:param name="rpt_curr_dt_tm" value="<%=df.format(new java.util.Date())%>"></birt:param>
					<birt:param name="rpt_state_code" value="${state_code}"></birt:param>
                   </birt:viewer>
		</div>
				  		     <button type="button" class="btn btn-danger " onclick="javascript:location.href='welcome.do'"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE"/></button>
		
		</div>
		
	</section>
  </div>
  
</section> 
</body>
</html>























	