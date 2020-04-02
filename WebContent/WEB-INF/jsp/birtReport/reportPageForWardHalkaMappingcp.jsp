<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/birt.tld" prefix="birt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
<title>Report Result</title>
<!-- <script type="text/javascript" src="js/common.js" charset="utf-8"></script> -->
</head>
<% 
 	String report_design = (String)request.getAttribute("report_design");
	String format = (String)request.getAttribute("format");
  	
%>
<body>
<section class="content">
                <div class="row">
                    <section class="col-lg-12">
                        <div class="box">
                
                <div class="box-body">
                 <birt:viewer id="birtViewer" reportDesign="<%=report_design%>" pattern="frameset" format="<%=format%>" height="700" width="648"  baseURL="${serverLoc}"></birt:viewer>
                 <%--  <iframe width="100%" height="500" src="${serverLoc}?__report=<%=report_design%>&__format=<%=format%>">
	              </iframe> --%>
                </div>
		           <!--  <div class="box-footer">                    
			               <button type="button"  class="btn btn-danger pull-right"" id="close" name="close" value="Close" onclick="javascript:go('welcome.do');"><i class="fa fa-times-circle"></i> Close</button>
		               
              </div> -->
                    </section>
                </div>
            </section>
	
	
 </body>
</html>