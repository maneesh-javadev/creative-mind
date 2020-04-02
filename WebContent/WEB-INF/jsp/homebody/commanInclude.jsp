<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript" src='${pageContext.request.contextPath}/dwr/engine.js'> </script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'> </script>

<script>

captchaReferesh=function(){
 	
 		
 		 var timestamp = (new Date()).getTime();
         var newSrc = $("#captchaImageId").attr("src").split("?");
      //  $('#captchaImage').attr('src', '').attr('src', 'Captcha.jpg');
         newSrc = newSrc[0] + "?" + timestamp;
         $("#captchaImageId").attr("src", newSrc);
         $("#captchaImageId").slideDown("fast");
         $("#captchaImageId1").attr("src", newSrc);
         $("#captchaImageId1").slideDown("fast");
 	};
 	</script>