
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../taglib_includes.jsp"%>

<%!String contextPath;%>
<%@include file="../homebody/commanInclude.jsp"%>
 <%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%> 
<head>
<title>E-Panchayat</title>
<<!-- meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> -->
<!-- <script type="text/javascript" src="js/common.js"></script> -->
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgd  DwrCaptchaService.js'></script>

<script>

function setfinancialYear(code){
	$("#financialYear").val(code);
}
	 
function validateReport(){
		
   var error=false;
   var capchaAns=$("#captchaAnswer").val();
	var stateVal=$("#financialYear").val();
	if( stateVal==""){
		alert("Please Select Financial Year")
		return false;
	}
	 if(capchaAns =="")
		{
		 /* document.getElementById("errorCaptcha").innerHTML = "Please Enter CAPTCHA  in the textbox"; 
	    document.getElementById("errorCaptcha").focus(); */
	    alert("Please Enter the Captcha...")
	    return false;
		}
	
      if(!error){
		 callActionUrl('rptVillageConvertedRuralToUrbanView.do');
	 }
}

 

function validatefinancialYear(){
	var stateVal=$("#financialYear").val();
	if( stateVal==""){
		alert("Please Select Financial Year")
		return false;
	}
}

</script>

</head>
<body  onload="setfinancialYear('${financialYear}'>
<section class="content">

<!-- Main row -->
 <div class="row">
     <!-- main col -->
	<div class="container">
	<section class="col-lg-12 prebox">
     	<div class="box">
            <div class="box-header with-border">
             <h3 class="box-title"><spring:message code="Label.VillageConvertedRuralToUrban" htmlEscape="true"></spring:message></h3>
           </div>
		     
		     <form:form class="form-horizontal" commandName="consolidateReportForRuralLB" id="form1" name="form1" action="rptVillageConvertedRuralToUrbanView.do" method="POST" >
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="rptVillageConvertedRuralToUrbanView.do"/>" />
		         
		                 
		                  <div id="displayHierarchy"  class="form-group">
		                      <label  class="col-sm-4 control-label" for="financialYear">Select Financial Year<font color="red">*</font></label>	
		                       <div class="col-sm-6">
		                      	 <form:select  path="financialYear"  name="financialYear"  class="form-control" id="financialYear" onchange="setfinancialYear(this.value);" >
		                      	   <option value=""><spring:message code="Label.SEL" htmlEscape="true"></spring:message></option>
		                      	   <form:option value="2019-2020"><c:out value= "2019-2020" escapeXml="true"></c:out></form:option>
		                      	   <form:option value="2018-2019"><c:out value= "2018-2019" escapeXml="true"></c:out></form:option>
		                      	   <form:option value="2017-2018"><c:out value= "2017-2018" escapeXml="true"></c:out></form:option>
		                      	   <form:option value="2016-2017"><c:out value= "2016-2017" escapeXml="true"></c:out></form:option>
		                      	   <form:option value="2015-2016"><c:out value= "2015-2016" escapeXml="true"></c:out></form:option>
		                      	   <form:option value="2014-2015"><c:out value= "2014-2015" escapeXml="true"></c:out></form:option>
		                      	   <form:option value="2013-2014"><c:out value= "2013-2014" escapeXml="true"></c:out></form:option>
		                      	   <form:option value="2012-2013"><c:out value= "2012-2013" escapeXml="true"></c:out></form:option>
		                      	   <form:option value="2011-2012"><c:out value= "2011-2012" escapeXml="true"></c:out></form:option>
		                      	   <form:option value="2010-2011"><c:out value= "2010-2011" escapeXml="true"></c:out></form:option>
		                      	   <form:option value="209-2010"><c:out  value= "2009-2010" escapeXml="true"></c:out></form:option>
		                      	   <form:option value="2008-2009"><c:out value= "2008-2009" escapeXml="true"></c:out></form:option>
		                      	   <form:option value="2007-2008"><c:out value= "2007-2008" escapeXml="true"></c:out></form:option>
		                      	   <form:option value="2006-2007"><c:out value= "2006-2007" escapeXml="true"></c:out></form:option>
		                      	   <form:option value="2005-2006"><c:out value= "2005-2006" escapeXml="true"></c:out></form:option>
		                      	  </form:select><br>
		                      	  <span class="errormsg" id="errorfinancialYear"></span> 
		                     </div> 
		                  </div> 
		                  
		                 <div>
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
								  <c:if test="${not empty nullCaptcha and not nullCaptcha}">
									<br/><label><!-- Used Label, please dont remove. --></label>
									<span class="mandatory"><strong><spring:message code="Kindly enter the captcha shown above"/></strong></span>
								</c:if>
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
	                      		<button class="btn btn-success" type="submit" onclick="return  validateReport();"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true" code="Button.GETDATA"></spring:message></button>
	                            <button type="button" class="btn btn-danger" onclick="javascript:location.href='welcome.do'"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE"/></button>
	                        </div>
	                     </div>   
		                </div> 
		     
		   </form:form>
		     
			</div>
			</section>
		</div>
	</div>
</section>
</body>
</html>