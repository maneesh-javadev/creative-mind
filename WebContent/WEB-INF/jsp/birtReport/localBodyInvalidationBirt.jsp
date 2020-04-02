<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib uri="/birt.tld" prefix="birt" %>
<script src="resource-tree/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource-tree/bootstrap.min.css">


</head>


<body>
<section class="content">
  <div class="row">
          <!-- main col -->
     <section class="col-lg-12">

        <div class="box">
		      <div class="box-header with-border">
		        <h3 class="box-title">Report on Invalidated Local Bodies</h3>
		      </div><!-- /.box-header -->
		      
		       <birt:viewer id="birtViewer" reportDesign="${invalidate_localBody}" pattern="frameset" height="1024"
					width="1500"   baseURL="${serverLoc}">
					        <birt:param name="slc" value="${stateCode}"></birt:param>
                        	<birt:param name="lbTypeCode" value="${lblc}"></birt:param>
                        	<birt:param name="stateNameEng" value="${stateNameEng}"></birt:param>
                        	<birt:param name="lbTypeName" value="${lbTypeName}"></birt:param>
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























	