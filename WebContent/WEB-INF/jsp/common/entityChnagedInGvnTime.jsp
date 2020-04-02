<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="../homebody/commanInclude.jsp"%>
<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf"%>
<script src="${pageContext.request.contextPath}/resource/dashboard-resource/bootstrap/js/bootstrap-datepicker.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/bootstrap/css/datepicker.min.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/plugins/datepicker/bootstrap-datetimepicker.min.css">
  <script src="resource/dashboard-resource/plugins/datepicker/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript">

$(function() {
	//date picker
	$('.date').datetimepicker({
								format : 'dd-mm-yyyy',
								startView : 'month',
								endDate: '+0d',
						        autoclose: true,
								minView : 'month',
								pickerPosition : "bottom-left"
							});
	});

function validateState(){
	var fromDate=$("#fromDates").val();
	var toDate=$("#toDates").val();
	if( fromDate==""){
		alert("From Date  should not be blank")
		return false;
	}
	if( toDate==""){
		alert("ToDate  should not be blank")
		return false;
	}
	
	if( fromDate>toDate){
		alert("From Date  should  be less than or equal to ToDate")
		return false;
	}
	
	$("#captchaAnswer").val($("#captcha").val());
	$("#fromDate").val($("#fromDates").val());
	$("#toDate").val($("#toDates").val());
	return true;
}
</script>
</head>
<body>

<section class="content">
  <div class="row">
          <!-- main col -->
     <section class="col-lg-12">

        <div class="box">
		      <div class="box-header with-border">
		        <h3 class="box-title">Report on changes occurred in a given time period</h3>
		      </div><!-- /.box-header -->
			
			<form:form  method="POST" action="changedEntity.do" class="form-horizontal" commandName="viewDeptforadmin">
           	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="changedEntity.do"/>"/>
           	<div class="box-body">
	           	<input type="hidden" id="fromDate" name="fromDate" value="">
				<input type="hidden" id="toDate" name="toDate" value="">
				<input type="hidden" id="captchaAnswer" name="captchaAnswer" value="">
            	<div class="form-group">
                   	<label for="fromDate" class="col-sm-4 control-label">From Date<span class="mandatory">*</span></label>
                    <div class="col-sm-3">
						<div class="input-group date datepicker">
					  		<input  name="fromDates" type="text" id="fromDates" class="form-control" htmlEscape="true" />
					  		<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
				   		</div>
                    </div>
                 </div>
                 		
                 <div class="form-group">
                   <label for="fromDate" class="col-sm-4 control-label">To Date<span class="mandatory">*</span></label>
                    <div class="col-sm-3">
						<div class="input-group date datepicker">
					  		<input  name="toDates" type="text" id="toDates" class="form-control" htmlEscape="true" />
					  		<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
				   		</div>
                    </div>
                 </div>
                 		
                 <div class="box-body">
                 	<div class="form-group">
                   	<label for="captchaImage" class="col-sm-4 control-label"></label>
                   	<div class="col-sm-6">
                     	<img src="captchaImage" alt="Captcha Image" id="captchaImageId"/>
                     </div>
                	</div>
                 	<div class="form-group">
                   		<label for="captchaAnswer" class="col-sm-4 control-label"><spring:message code="captcha.message"	htmlEscape="true" /><span class="mandatory">*</span></label>
                   		<div class="col-sm-3">
                    		<input type="text" id="captcha" autocomplete="off"  maxlength="5" class="form-control" />
                    		<a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>
								<c:if test="${not empty captchaFaliedMessage and not captchaFaliedMessage}">
									<br/><label><!-- Used Label, please dont remove. --></label>
									<span class="errormsg"><strong><spring:message code="captcha.errorMessage"/></strong></span>
								</c:if>
                    	</div>
                	</div>
  				</div>
  				
  				<div class="box-footer">
                     <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
                      		<button type="submit" onclick="return validateState();" class="btn btn-success"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true" code="Button.GETDATA"/></button>
                            <button type="button" class="btn btn-danger " onclick="javascript:location.href='welcome.do'"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE"/></button>
                        </div>
                     </div>   
               	</div>
               	
              </div>
	        </form:form>
			</div>
	</section>
 </div>
 </section>
</body>
</html>