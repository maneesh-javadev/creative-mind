<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="css/error.css" rel="stylesheet" type="text/css" />
<!-- <script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script> -->
<script type="text/javascript" language="Javascript">
</script>
<script src="js/common.js"></script>
<script type="text/javascript" language="javascript">
/* $(document).ready(function() {
	document.getElementById("errBlockCode").innerHTML="";
	document.getElementById("errBlockCode1").innerHTML="";
    if($('#flag1').val() ==1){	   
    	// $("form #distid").attr('checked', true); 
    	$("form #distid").attr("disabled", true);	   
    	
	  }
    if($('#flag2').val() ==1){	   
    	// $("form #subdistid").attr('checked', true); 
    	$("form #subdistid").attr("disabled", true);	   
    	
	  }
    if($('#flag3').val() ==1){	   
    	// $("form #vilid").attr('checked', true); 
    	$("form #vilid").attr("disabled", true);	   
    	
	  }
    if($('#flag4').val() ==1){	   
   // $("form #ubid").attr('checked', true); 
    	$("form #ubid").attr("disabled", true);	   
    	
	  }
    if($('#flag5').val() ==1){	   
    	// $("form #rbid").attr('checked', true); 
    	$("form #rbid").attr("disabled", true);	   
    	
	  }
    if($('#flag6').val() ==1){	   
    	// $("form #traid").attr('checked', true); 
    	$("form #traid").attr("disabled", true);	   
    	
	  }    
}); */

function SaveData(x) {
		document.getElementById("errBlockCode").innerHTML="";
	document.getElementById("errBlockCode1").innerHTML="";
	if(document.getElementById("distid").checked==false && document.getElementById("subdistid").checked==false && document.getElementById("vilid").checked==false
			&& document.getElementById("ubid").checked==false && document.getElementById("rbid").checked==false && document.getElementById("traid").checked==false)
		{
		 document.getElementById("errBlockCode").innerHTML = "<font color='red'><br>Please Select A Service</font>";
		return false;
		}
	document.getElementById("btnSave").disabled=true;
	document.Emai_TEST.method="post";
	document.getElementById('Emai_TEST').action = "subscribeemaildata.htm";
	document.Emai_TEST.submit(); 	
	return true;
}
function DelData() {
	document.getElementById("errBlockCode").innerHTML="";
	document.getElementById("errBlockCode1").innerHTML="";
	 if($('#flag1').val() !=1 && $('#flag2').val() !=1 && $('#flag3').val() !=1 && $('#flag4').val() !=1 && $('#flag5').val() !=1 && $('#flag6').val() !=1 && $('#flag7').val() !=1) 
		 {
			
		 document.getElementById("errBlockCode1").innerHTML = "<font color='red'><br>No Services Sucbscribed, To Cancel</font>";
		 return false;
		 }
	document.getElementById("btnCancel").disabled=true;
	document.Emai_TEST.method="post";
	document.getElementById('Emai_TEST').action = "unsubscribeemail.htm";
	document.Emai_TEST.submit(); 	
	return true;
}
</script>
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">
		
					<h3 class="subtitle"><spring:message code="Email.Header" htmlEscape="true"></spring:message></h3>
				   <ul class="listing">
				   <li>
					<a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a>
					</li>
					<li>
					<a href="#" class="frmhelp">Help</a>
					</li>
					</ul>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form name="Emai_TEST" id="Emai_TEST" method="POST"
				commandName="email">
				<input type="hidden" name="flag1" id="flag1" value="${flag1}" />
				<input type="hidden" name="flag2" id="flag2" value="${flag2}" />
				<input type="hidden" name="flag3" id="flag3" value="${flag3}" />
				<input type="hidden" name="flag4" id="flag4" value="${flag4}" />
				<input type="hidden" name="flag5" id="flag5" value="${flag5}" />
				<input type="hidden" name="flag6" id="flag6" value="${flag6}" />
				<input type="hidden" name="flag7" id="flag7" value="${flag7}" />
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="createDepartment.htm"/>" />

				<div id="cat">
					<div class="frmpnlbg">
						
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message code="Email.Message" htmlEscape="true"></spring:message>

										</div>
										
													<div class="frmtxt">
														<div class="frmhdtitle">
															<spring:message code="Email.LandRegion" htmlEscape="true"></spring:message>
														</div>
														
																<ul class="listing">
																<li>
																<c:choose>
																		<c:when test="${flag1 eq 1}">
																			<form:checkbox name="checkbox" value="S"
																				path="district" id="distid" checked="checked" />
																		</c:when>
																		<c:otherwise>
																			<form:checkbox name="checkbox" value="S"
																				path="district" id="distid" />
																		</c:otherwise>
																	</c:choose> <spring:message code="Email.Dis" htmlEscape="true"></spring:message>
																	</li>
																	<li>
																	<c:if test="${flag1 eq 1}">

																	</c:if> <!--  if(flag==1)
															 <font class="mndt">*</font> -->
																	
																	<%-- 	<form:checkbox  name="checkbox" value="D" path="subdistrict" id="subdistid"/> --%>
																	<c:choose>
																		<c:when test="${flag2 eq 1}">
																			<form:checkbox name="checkbox" value="D"
																				path="subdistrict" id="subdistid" checked="checked" />
																		</c:when>
																		<c:otherwise>
																			<form:checkbox name="checkbox" value="D"
																				path="subdistrict" id="subdistid" />
																		</c:otherwise>
																	</c:choose> <spring:message code="Email.Sub" htmlEscape="true"></spring:message>
																	</li>
																	<li>

																	

																	<c:choose>
																		<c:when test="${flag3 eq 1}">
																			<form:checkbox name="checkbox" value="T"
																				path="village" id="vilid" checked="checked" />
																		</c:when>
																		<c:otherwise>
																			<form:checkbox name="checkbox" value="T"
																				path="village" id="vilid" />
																		</c:otherwise>
																	</c:choose> <spring:message code="Email.Vil" htmlEscape="true"></spring:message>
																	</li>
                                     								<li>
																	 <c:choose>
																		<c:when test="${flag7 eq 1}">
																			<form:checkbox name="checkbox" value="B" path="block"
																				id="blkid" checked="checked" />
																		</c:when>
																		<c:otherwise>
																			<form:checkbox name="checkbox" value="B" path="block"
																				id="blkid" />
																		</c:otherwise>
																	</c:choose> <spring:message code="Label.BLOCK" htmlEscape="true"></spring:message>
																</li>
																</ul>
															
													</div>
													
												
													<div class="frmtxt">
														<div class="frmhdtitle">
															<spring:message code="Email.Local" htmlEscape="true"></spring:message>
														</div>
														
																<ul class="listing">
																<li>
																<c:choose>
																		<c:when test="${flag4 eq 1}">
																			<form:checkbox name="checkbox" value="S"
																				path="urbanbody" id="ubid" checked="checked" />
																		</c:when>
																		<c:otherwise>
																			<form:checkbox name="checkbox" value="S"
																				path="urbanbody" id="ubid" />
																		</c:otherwise>
																	</c:choose> <spring:message code="Email.Ub" htmlEscape="true"></spring:message>
																	</li>
																	<li>	
																	 <c:choose>
																		<c:when test="${flag5 eq 1}">
																			<form:checkbox name="checkbox" value="D"
																				path="ruralbody" id="rbid" checked="checked" />
																		</c:when>
																		<c:otherwise>
																			<form:checkbox name="checkbox" value="D"
																				path="ruralbody" id="rbid" />
																		</c:otherwise>
																	</c:choose> <spring:message code="Email.Rb" htmlEscape="true"></spring:message>
																		</li>
																		<li>
																	 <c:choose>
																		<c:when test="${flag6 eq 1}">
																			<form:checkbox name="checkbox" value="T"
																				path="tradionbody" id="traid" checked="checked" />
																		</c:when>
																		<c:otherwise>
																			<form:checkbox name="checkbox" value="T"
																				path="tradionbody" id="traid" />
																		</c:otherwise>
																	</c:choose> <spring:message code="Email.Tb" htmlEscape="true"></spring:message>
																		</li>
																		</ul>
															
													</div>
												


									</div>
									<div class="btnpnl">
										
												 <input
														type="button" name="Save" class="button" id="btnSave"
														onclick="SaveData();"
														value="<spring:message code="Email.SubService" ></spring:message>" />
														
												 <input type="button" class="button"
														name="Cancel"
														value="<spring:message code="Email.UnSub"></spring:message>"
														id="btnCancel" onclick="DelData();" /> 
														
													
													
													<div id="errBlockCode" style="color: red;"></div>
													<div id="errBlockCode1" style="color: red;"></div> 
												
												</div>	
													
													

											</div>
											</div>
										<!-- </div>
				 <font class="mndt">*</font> Services Already Subscribed
				</div> -->
										</form:form>
										</div>
										</div>
									
									
	</body>								
</html>