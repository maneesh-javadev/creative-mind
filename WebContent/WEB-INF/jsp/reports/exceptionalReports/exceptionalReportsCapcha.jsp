
<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="../../homebody/commanInclude.jsp"%>

<script type="text/javascript" class="init">

$(document).ready(function() {
	$( '#actionFetchDetails' ).click(function() {
 		validationForm();
 	});
});
	
	var validationForm = function (){
		$("#captchaAnswer" ).rules( "add", {
			
	 		  required : true, messages: {required : "<font color='red'>Please enter the text shown above.</font>"}
		});
	}
</script>


<section class="content">

 <!-- Main row -->
 <div class="row">
     <!-- main col -->
	<div class="container">
	<section class="col-lg-12 prebox">
     	<div class="box">
            <div class="box-header with-border">
              <h3 class="box-title"><c:out value="${exceptionalReportsForm.reportName}"/></h3>
            </div><!-- /.box-header -->
                
                <!-- form start -->
                  <form:form class="form-horizontal" action="exceptionalReports.do" method="POST" id="genericReportingEntity" commandName="exceptionalReportsForm">
               	<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="exceptionalReports.do"/>" />
				<form:hidden path="reportName" />
				<form:hidden path="fileName" />
					
	                  <div class="box-body">
		                    <div class="form-group">
		                     	<label for="captchaImage" class="col-sm-4 control-label"></label>
		                      <div class="col-sm-6">
		                        <img src="captchaImage" alt="Captcha Image" id="captchaImageId" />
		                       </div>
		                    </div>
		                    <div class="form-group">
		                      <label for="captchaAnswer" class="col-sm-4 control-label"><spring:message code="captcha.message"	htmlEscape="true" /><span class="mandatory">*</span></label>
		                      <div class="col-sm-6">
		                      	<form:input	path="captchaAnswer" id="captchaAnswer" autocomplete="off" class="form-control" maxlength="5"/>
		                      	<a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>
								<c:if test="${not empty captchaFaliedMessage and not captchaFaliedMessage}">
									<br/><label><!-- Used Label, please dont remove. --></label>
									<span class="mandatory"><strong><spring:message code="captcha.errorMessage"/></strong></span>
								</c:if>
		                       </div>
	                    	</div>
					  </div>
					 	<div class="box-footer">
		                     <div class="col-sm-offset-2 col-sm-10">
		                       <div class="pull-right">
		                      		<button type="submit" class="btn btn-primary" id="actionFetchDetails"><i class="fa fa-undo"></i> <spring:message htmlEscape="true" code="Button.GETDATA"/></button>
		                            <button type="button" class="btn btn-danger " onclick="javascript:location.href='welcome.do'"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE"/></button>
		                        </div>
		                     </div>   
		                  </div><!-- /.box-footer -->
					  
				
				
				  </form:form>
			</div>
 
		</section>
		</div>
	</div>
</section>





	