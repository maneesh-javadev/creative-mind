<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../homebody/commanInclude.jsp"%>
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />

<%!String contextPath;%>
<%
	contextPath = request.getContextPath(); 
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="css/error.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	#tbl td{
		padding: 10px 10px 10px 10px;
	}
</style>
<script type="text/javascript">

function setStateCode(code){
	$("#stateCode").val(code);
	$("#entity").val(code);
}

function validateState(){
	var stateVal=$("#entity").val();
	if( stateVal==""){
		alert("Please Select State")
		return false;
	}
	
	 var Captcha =$("#captcha").val();
	 if(Captcha == "")
		 {
		
		 $( '#errorcaptchaAnswers').text("Please Enter the Captcha shown above");
		 return false;
		 }
	
	return true;
}

</script>




</head>
	<body onload="setStateCode('${entityName}')">
		<section class="content">
                <div class="row">
                    <section class="col-lg-12">
                  <form:form  method="POST" action="stateWiseUnmappedVillages.do" commandName="genericReportingEntity" class="form-horizontal">
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="stateWiseUnmappedVillages.do"/>"/>
						<input type="hidden" id="stateCode" name="code" value="">
						<input type="hidden" id="captchaAnswer" name="captchaAnswer" value="">
                    <div class="box">
                    <div class="box-header with-border">
                      <h4>State Wise Unmapped Villages List</h4>
                    </div>
                        <div class="box-body">
                          <div class="form-group">
                            	<label  class="col-sm-4 control-label" >State<font color="red">*</font></label>									
								  <div class="col-sm-6">	
										<select name="entityName" id="entity" onchange="setStateCode(this.value);" class="form-control">
												<option value=""><spring:message code="Label.SEL" htmlEscape="true"></spring:message></option>
												<c:forEach var="statelist" items="${stateList}" varStatus="index">
													<option value="${statelist[0]}"><c:out value="${statelist[1]}" escapeXml="true"></c:out></option>
												</c:forEach>
											</select>
                                    </div>	
                          </div>
                          
                            <div class="form-group">
                            	<label  class="col-sm-4 control-label" for="entity"></label>									
								  <div class="col-sm-6">	
										<img src="captchaImage" alt="Captcha Image" id="captchaImageId"/>
                                    </div>	
                          </div>
                        
                        <div class="form-group">
                            	<label  class="col-sm-4 control-label" for="entity"><spring:message code="captcha.message"	htmlEscape="true" /><span class="mandatory">*</span></label>									
								  <div class="col-sm-6">	
								   <input type="text" id="captcha" autocomplete="off" maxlength="5"  width="10%"/>
									<a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>
									 <span class="errormsg" id="errorcaptchaAnswers"></span>
										<c:if test="${not empty captchaFaliedMessage and not captchaFaliedMessage}">
											<span class="errormsg"><strong><spring:message code="captcha.errorMessage"/></strong></span>
											
										</c:if>
                                    </div>	
                          </div>
                        
                        </div>
                  <div class="box-footer">
		            <div class="col-sm-offset-2 col-sm-10">
		                 <div class="pull-right">
						   <button type="submit"  class="btn btn-success" value="Get Data" onclick="return validateState();"><i class="fa fa-floppy-o"></i> Get Data</button>
						   <button type="button" class=" btn btn-danger" onclick="javascript:location.href='welcome.do'"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE"/></button>
                        </div>
                      </div>
                   </div>
                        
                    </div>
                    
                    </form:form>
             
                    </section>
                </div>
            </section>
	</body>
</html>