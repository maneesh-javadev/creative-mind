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


	 <section class="content">
		 <div class="row">
		 	<section class="col-lg-12 ">
	        	<div class="box ">
		        	<div class="box-header with-border">
		            	<h3 class="box-title"><spring:message code="label.error.page" text="LGD Error Page" htmlEscape="true"></spring:message></h3>
		            </div>
		            <div class="box-body">
	            		<form>
					
		
						<div class="row">
					    	<div class="col-sm-12">
					    			<label class="left_padding"><c:out value="${errorMsg}" escapeXml="true"></c:out></label>
					    	</div>
					    </div>
					    
					     <div class="box-footer" id="showbutton">
		                    <div class="col-sm-offset-2 col-sm-10">
		                       <div class="pull-right">
		                           <button type="button" name="Submit3" id="btn1" class="btn btn-danger" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
							   </div>
							 </div>
						  </div>
					    
					   </form>
					    
					    </div>
				 </div>
				</section>
			   </div>
		</section>
	
</body>
</html>
