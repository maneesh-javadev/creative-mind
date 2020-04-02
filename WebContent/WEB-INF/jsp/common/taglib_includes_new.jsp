<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf"%>
<% String contextpthval = request.getContextPath(); %>

<script type="text/javascript" src='<%=contextpthval%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/util.js'> </script>

<script type="text/javascript" src="<%=contextpthval %>/resource/common-resource/jquery-3.1.0.min.js"></script> 

<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.js"> </script>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"> </script> -->


<%-- <script type="text/javascript" src="<%=contextpthval %>/resource/common-resource/jquery.validate.js"></script> 
<script type="text/javascript" src="<%=contextpthval %>/resource/common-resource/jquery.validate.min.js"></script>  --%>
<script type="text/javascript" src="<%=contextpthval %>/ui/jquery.validate.js"></script>


<%-- <script src="${pageContext.request.contextPath}/resource/dashboard-resource/bootstrap/js/bootstrap-datepicker.js"></script>
<script src="resource/dashboard-resource/plugins/datepicker/bootstrap-datetimepicker.min.js"></script> --%>
<script src="<%=contextpthval %>/resource/common-resource/jquery.blockUI.min.js"></script>
 <script src="<%=contextpthval %>/resource/common-resource/jquery.dataTables.min.js"></script>
 <script src="<%=contextpthval %>/resource/common-resource/dataTables.bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%=contextpthval %>/resource/common-resource/jquery-ui.css">
 <script src="<%=contextpthval %>/resource/common-resource/jquery-ui.js"></script>
 <script src="<%=contextpthval %>/resource/common-resource/jquery.format.js"></script>
<%--  <script src="<%=contextpthval %>/resource/common-resource/minified.js"></script> --%>


<script type="text/javascript" src="<%=contextpthval %>/resource/common-resource/jquery-migrate-3.0.1.min.js"></script> 

 
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/bootstrap/css/datepicker.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/plugins/datepicker/bootstrap-datetimepicker.min.css">


<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/common-resource/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/common-resource/bootstrap-theme.css">
<script type="text/javascript" src="<%=contextpthval %>/resource/common-resource/bootstrap.min.js"></script> 


<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script>

	dwr.engine.setErrorHandler(function(message,exception){
		var form = document.getElementById("ajaxErrorFrom");			
		form.submit();
	});
	window.onerror = noError;
	
	function noError() {
		return true;
	}

	displayLoadingImage = function() {
		 $.blockUI({ 
				theme:true,
				/* title: 'Loading...', */
				message: "<div style='text-align: center'><img  src='<%= request.getContextPath() %>/resource/dashboard-resource/dist/img/loader-lgd.gif'/></div>"
		    }); 
	   
	};
	
	showLoadingImage = function() {
		
		
		
		$.blockUI({ 
			theme:true,
			/* title: 'Loading...', */
			message: "<div style='text-align: center'><img  src='<%= request.getContextPath() %>/resource/dashboard-resource/dist/img/loader-lgd.gif'/></div>"
	    }); 
	};
	
	hideLoadingImage = function(){
		 $.unblockUI(); 
		
	};
	
	 dwr.engine.setPreHook(showLoadingImage);
	dwr.engine.setPostHook(hideLoadingImage); 
	
	
	customAlert = function(message){
		//alert(message);
		$("#bsalertbody").text(message);
		$('#cAlert').modal('show');	
	};
	
	$(document).ready(function() {
		$('#printable').dataTable({
		});
	});
	
	/* Added by Ripunj on 12-01-2015 for draft mode alert message */
	draftModeAlert = function(){
		alert("Already Used in Draft Mode");
	}
	
	/** 
	 * The {@code isEmptyObject} used to check object / element  
	 * is empty or undefined.
	 */
	var isEmptyObject = function(obj) {
		if (jQuery.type(obj) === "undefined" || (obj == null || $.trim(obj).length == 0)) {
			return true;
		}
		return false;
	};
	
	var isParseJson=function(obj){
		var temp=null;
		try{
			temp=$.parseJSON(obj);
		}catch(err) {
			temp=null;
		}
		return temp;
	}
	
	/*  $(function() {
		//date picker
		$('.date').datetimepicker({
									format : 'dd-mm-yyyy',
									startView : 'month',
									endDate: '+0d',
							        autoclose: true,
									minView : 'month',
									pickerPosition : "bottom-left"
								});
		}); */
	
	</script>
	<!-- <div id="cAlert"></div> -->
		<div class="modal fade" id="cAlert" tabindex="-1" role="dialog" >
						<div class="modal-dialog" >
								<div class="modal-content">
					  				<div class="modal-header">
					   					 <button type="button" class="close" data-dismiss="modal">&times;</button>
					    			  	<h4 class="modal-title">Alert</h4>
					  				</div>
					  				<div class="modal-body" id="bsalertbody">
					        
					  				</div>
					     			 
							</div>
						</div>
					</div>
<!-- Added by Sushil on 17-11-2014 -->
<% 
	java.util.Calendar cal = java.util.Calendar.getInstance();
	java.util.Date dt = new java.util.Date();
	cal.setTime(dt);
	int currentMonth =  cal.get(java.util.Calendar.MONTH);
	int currentDate =  cal.get(java.util.Calendar.DATE);
	int currentYear =  cal.get(java.util.Calendar.YEAR);
%>
 
