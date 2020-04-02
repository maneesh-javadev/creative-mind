<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<title>E-Panchayat</title>
<meta charset="ISO-8859-1">
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib uri="/birt.tld" prefix="birt" %>
<script src="resource-tree/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource-tree/bootstrap.min.css">
</head>
<body>
<section class="content">
  <div class="row">
        <section class="col-lg-12">
        
        <div class="box">
		      <div class="box-header with-border">
		        <h3 class="box-title">Report on Invalidated Local Bodies</h3>
		      </div><!-- /.box-header -->
		      <% java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a"); %>
		       <birt:viewer id="birtViewer" reportDesign="${invalidate_localBody}" pattern="frameset" height="1024"
					width="1450"   baseURL="${serverLoc}">
					        <birt:param name="slc" value="${stateCode}"></birt:param>
                        	<birt:param name="lbTypeCode" value="${lblc}"></birt:param>
                        	<birt:param name="stateNameEng" value="${stateNameEng}"></birt:param>
                        	<birt:param name="lbTypeName" value="${lbTypeName}"></birt:param>
                        	<birt:param name="rpt_curr_dt_tm" value="<%=df.format(new java.util.Date())%>"></birt:param>
                   </birt:viewer>
	      </div>
		 <div class="box-footer">                    
			<button type="submit" class="btn btn-danger pull-right" onclick="javascript:go('welcome.do');"><i class="fa fa-times-circle"></i> Close</button>
	     </div>
     
     
     </section>
     </div>
     </section>
</body>
</html>