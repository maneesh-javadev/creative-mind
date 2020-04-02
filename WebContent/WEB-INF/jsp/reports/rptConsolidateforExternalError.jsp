<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%!String sessionId;%>
<%
	sessionId = request.getSession().getId();
%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
</head>
<body>

<body>
	 <section class="content">
		 <div class="row">
		 	<section class="col-lg-12 ">
	        	<div class="box ">
		        	<div class="box-header with-border">
		            	<h3 class="box-title"><spring:message code="label.error.page" text="Error Page for Report" htmlEscape="true"></spring:message></h3>
		            </div>
		            <div class="box-body">
	            		
					
		
						<div class="row">
					    	<div class="col-sm-12">
					    			<label class="left_padding"><c:out value="${errorMsg}" escapeXml="true"></c:out></label>
					    	</div>
					    </div>
					    
					    </div>
				 </div>
				</section>
			   </div>
		</section>
	
</body>
</html>
