<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
         <%@ taglib uri="/birt.tld" prefix="birt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="../../common/taglib_includes.jsp"%>
 <title>Report Result</title>
  </head>
  <% 
  	String report_design = (String)request.getAttribute("report_design");
  	String parenttype = (String)request.getAttribute("parenttype");
 
  %>
  <body>
  
  
<section class="content">
<!-- Main row -->
 <div class="row"  id="frmcontent">
     <!-- main col -->
	<div class="container">
	<section class="col-lg-12 prebox">
     	<div class="box">
         	  <div class="box-body">
         	 
         	 	<%-- <iframe width="100%" height="500" src="${serverLoc}?__report=<%=report_design%>&_ptype=<%=parenttype%>"></iframe> --%>
         	  <birt:viewer id="birtViewer" reportDesign="<%=report_design%>" pattern="frameset"  height="700" width="1110"  baseURL="${serverLoc}">
                        	<birt:param name="_ptype" value="<%=parenttype%>"></birt:param>
                </birt:viewer>
			  </div>
			  <div class="box-footer">
             	<div class="col-sm-offset-2 col-sm-10">
            	  <div class="pull-right">
					<button type="button"  name="Submit3" onclick="javascript:location.href='welcome.do'" class="btn btn-danger" ><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
				 </div>
			  </div>
	        </div>
		  </div>
		</section>
		</div>
	</div>
</section>
  
  </body>
</html>