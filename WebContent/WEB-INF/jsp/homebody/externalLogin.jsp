
<!-- 
		 * This Method is Use for External User 
		 * @param loginForm
		 * @author Maneesh Kumar
		 * @since 01-10-2019 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf"%>
<%@include file="../common/taglib_includes.jsp"%>
<script type='text/javascript'	src='${pageContext.request.contextPath}/dwr/interface/lgdDwrInitialService.js'></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/dwr/engine.js'> </script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'> </script>
	<link href="${pageContext.request.contextPath}/resource/homebody-resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/homebody-resource/css/lgd.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resource/homebody-resource/css/external-user.css" rel="stylesheet">
    <!-- Bootstrap Core JavaScript -->
    <script src="resource/homebody-resource/js/bootstrap.min.js"></script>
   <script src="resource/homebody-resource/js/aes.js"></script>
<script>
var userLoginName=null;

var checkCapcha = function(captchaAnswer) {
	
	lgdDwrInitialService.validateCaptchaAnswer(captchaAnswer, {
			callback : function( messageFlag ) {
				var timestamp = (new Date()).getTime();
				var newSrc = $("#simpleImg").attr("src").split("?");
				//  $('#captchaImage').attr('src', '').attr('src', 'Captcha.jpg');
				newSrc = newSrc[0] + "?" + timestamp;
				$("#simpleImg").attr("src", newSrc);
				$("#simpleImg").slideDown("fast");

				return messageFlag;
			},
			errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
			async : false
		});
	
	
};

var validPassword = function(username,epass) {
	
	lgdDwrInitialService.isPasswordValid(username,epass, {
			callback : function( messageFlag ) {
				
				return messageFlag;
			},
			errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
			async : false
		});
	
}

function validateForm(){
	
	
var flag=false;	
var  userLoginName=$("#username").val();
var captchaAnswer=$("#captchaAnswer").val();
var val =$("#password").val();

if(!(userLoginName!=null && userLoginName!="")){
	$("#error_status").css("display","block");
	$("#error_capcha").css("display","none");
	$( '#error_status' ).text("User Name Required");	
		return false;
}else if(!(val!=null && val!="")){
	$("#error_status").css("display","block");
	$("#error_capcha").css("display","none");
	$( '#error_status' ).text("Password Required");	
		return false;
}
else if(!(captchaAnswer!=null && captchaAnswer!="")){
	$("#error_status").css("display","none");
	$("#error_capcha").css("display","block");
	$( '#error_capcha' ).text("Capcha Required");	
		return false;
}

var rkEncryptionKey = CryptoJS.enc.Base64.parse('u/Gu5posvwDsXUnV5Zaq4g==');
var rkEncryptionIv = CryptoJS.enc.Base64.parse('5D9r9ZVzEYYgha93/aUK2w==');
var utf8Stringified = CryptoJS.enc.Utf8.parse(val);
var encrypted = CryptoJS.AES.encrypt(utf8Stringified.toString(), rkEncryptionKey, 
{mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7, iv: rkEncryptionIv});
var enpass = encrypted.ciphertext.toString(CryptoJS.enc.Base64)
$("#enpassword").val(enpass);



lgdDwrInitialService.validateCaptchaAnswer(captchaAnswer, {
	callback:function(data){
		captchaStatus=data;
	       	},
		async : false
		});
	  
if(!captchaStatus) {
	$("#loginForm").attr('target','');
		return true;
} 

	  //Validate user & Pasword
	lgdDwrInitialService.isPasswordValid(userLoginName,enpass, {
		callback:function(isvalid){
			
			
			isvalidUserPassword=isvalid;
		},
		async : false
	});
  
 if(isvalidUserPassword) {
	$("#loginForm").attr('target','_parent');
	}

 return true;
}

	
	
	
	







</script>


<html>

 <body id="cas">
<div id="maincontainer" class="resize">


				<div class="clear"></div>


<!-- <div id="homepagebg"></div>  -->
<div id="contentbrdr" align="center">

<!--

-->

<div class="box fl-panel" id="login">





<form:form action="getExternalLogin.do" method="post" commandName="lform" id="loginForm" class="fm-v clearfix" onsubmit="return validateForm();">
<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="getExternalLogin.do"/>" />
<form:hidden path="enpassword"/>





<div style="height: 50px; width: 100%;"> </div>
<h2 align="center">Ladakh User Log In</h2>
	<form:errors path="*" cssClass="errors" id="status" element="div" />
	<div id="error_status" class="errors" style="display: none;"></div>
	<div class="row fl-controls-left">
	<label for="username" class="fl-label"><span class="accesskey">U</span>serID:</label>
	

	
			<form:input path="username" class="required" tabindex="1" accesskey="u" size="25"/>	
		
    
    </div>
    
    <div class="row fl-controls-left">
     <label for="password" class="fl-label"><span class="accesskey">P</span>assword:</label>
	<form:password path="password" class="required" tabindex="2"  onfocus="avoidCopyPaste(this.id)" accesskey="p" value="" size="25" autocomplete="off"/>	
	
	<!--autocomplete="off" onfocus="avoidCopyPaste(this.id)"-->
	  </div>
	 
	 
	 
	<div class="row fl-controls-left">
     <label for="password" class="fl-label">Word verification</label><br><img src="captchaImage" id="simpleImg"><br><br>Type the characters you see in the image above<br> 
	 
    </div>
    
      <div class="row fl-controls-left">
       <label for="captcha" class="fl-label"><span class="accesskey">C</span>aptcha:</label>
	 <form:input path="captchaAnswer" class="required" tabindex="3" accesskey="c" value="" size="25" autocomplete="off"/>	
	<c:if test="${captchaFaliedMessage eq false}"> 
	<div id="error_capcha" class="errors"  >You Have Entered Wrong Captcha</div>
	</c:if>
	
    </div>
	<br/>


    <div class="row btn-row">
     <input class="btn-submit" name="submit" accesskey="l" value="LOGIN" tabindex="4" type="submit" >
     <input class="btn-submit" name="reset" accesskey="c" value="CLEAR" tabindex="5" type="reset">
	</div>
</form:form>
</div>
 <!-- <div style="height: 50px; width: 100%;" > </div> -->
<div id="list-languages" class="fl-panel">

	 
</div>
</div>




</div>






</body>
</html>