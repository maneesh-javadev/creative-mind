<%@ page language="java" contentType="text/html; charset=ISO-8859-1;application/javascript;charset=utf-8"
    pageEncoding="ISO-8859-1"%>
    <%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html data-ng-app="publicModule">
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/TestSMS/style.css" rel="stylesheet" type="text/css" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf"%>
<script type="text/javascript" src="<%=contextPath%>/angularjs/angular.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/angularjs/ui-bootstrap-tpls-0.12.0.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/angularjs/testSMSandMailController.js"></script>
<script type="text/javascript" src="<%=contextPath%>/angularjs/testSMSMailControllerService.js"></script>
<script type="text/javascript" src="<%=contextPath%>/angularjs/toastr.min.js"></script>
<%-- <%@include file="../common/taglib_includes.jsp"%> --%>

<title>Register For Web Services</title>
<script>
	
function isNumber(evt) {
	
	l=$("#mobno").val().length;
	evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if((charCode < 55 || charCode > 57) && l==0){
    	return false;
    }
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}
</script>
</head>
<body data-ng-controller="testSMSMailController">
	
	
	<section class="content">
  <div class="row">
          <!-- main col -->
     <section class="col-lg-12">

        <div class="box">
		      <div class="box-header with-border">
		        <h3 class="box-title">Test SMS AND EMAIL SERVICE of LGD</h3>
		      </div><!-- /.box-header -->
		      
	
		<form name="testForm" novalidate >
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="wsUserRegistrationList.htm"/>" /> 
			<div class="gap_up"></div>
			
		<div align="center">
		
			<div class="floating-form">
			<span ng-show="isMessage" id="msgBoard" class="test"></span>
			</div>
			<div class="gap_up"></div>
		
			<div class="floating-form">
				    <div class="floating-label">      
				      	<input class="floating-input" type="text" placeholder=" " name="txtmail" ng-model="txtmail" ng-pattern="/^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/" >
				      	<span class="red-text" ng-show="testForm.txtmail.$dirty&&testForm.txtmail.$error.pattern">Please Enter Valid Email</span>
				      	<span class="highlight"></span>
				      	<label>Email ID</label>
				    </div>
	    	</div>
	    	<div class="gap_up"></div>
	    	<div class="floating-form">
				    <div class="floating-label">      
				      	<input class="floating-input" type="text" placeholder=" " onkeypress="return isNumber(event)" id="mobno"
				      	 maxlength="10" ng-model="contact" name="contact" ng-pattern="/^\+?\d{10}$/">
				      	<span class="red-text" ng-show="testForm.contact.$error.pattern">Please enter a 10 digit number</span>
				      	<span class="highlight"></span>
				      	<label>Mobile No</label>
				    </div>
	    	</div>
	    	
	    	<div class="gap_up"></div>
	    	
	    	
         <div style="margin-bottom: 10px"><button type="button" class="btnm btn-primary" ng-click="testService()">Test Service</button>
  		<button type="button" class="btnm btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';">Close</button></div>
                  
	    	
	    	
	    	
	    </div>
	   
			</form>
			<div class="gap_down"></div>
		</div>
		</section>
		</div>
		</section>
	
	
</body>
</html>