<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/birt.tld" prefix="birt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
</head>
  <% 
  	String report_design = (String)request.getAttribute("report_design");
  	String orgLvlCode = (String)request.getAttribute("orgLvlCode");
  	String orgCode = (String)request.getAttribute("orgCode");
	String orgzN = (String)request.getAttribute("orgztn");
	String stateCode = (String)request.getAttribute("stateCode");
	String serverloc = (String)request.getAttribute("serverloc");
  %>
  <body>
  
  <section class="content">
                <div class="row">
                    <section class="col-lg-12">
                        <div class="box">
                
                <div class="box-body">
                <birt:viewer id="birtViewer" reportDesign="<%=report_design%>" pattern="frameset" height="700" width="648"  baseURL="${serverLoc}">
                        	<birt:param name="_ollc" value="<%=orgLvlCode%>"></birt:param>
                        	<birt:param name="_orgcode" value="<%=orgCode%>"></birt:param>
                        	<birt:param name="_orgName" value="<%=orgzN%>"></birt:param>
                        	<birt:param name="_statecode" value="<%=stateCode%>"></birt:param>
                        	
                        	
                        		</birt:viewer> 
                 <%--  <iframe width="100%" height="500" src="${serverLoc}?__report=<%=report_design%>&_ollc=<%=orgLvlCode%>&_orgcode=<%=orgCode%>&_orgName=<%=orgzN%>&_statecode=<%=stateCode%>>"></iframe> --%>
                </div>
		            <div class="box-footer">                    
			               <button type="submit" class="btn btn-danger pull-right" onclick="javascript:go('welcome.do');"><i class="fa fa-times-circle"></i> Close</button>
			          </div>
		               
              </div>
                    </section>
                </div>
            </section>
  
  </body>
</html>