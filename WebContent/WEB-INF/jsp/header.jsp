<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="javax.servlet.ServletContext;"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib
	uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld"
	prefix="csrf"%>
<head>
<noscript><meta http-equiv="Refresh" content="0; URL=nonscript.htm" /></noscript>
<link rel="stylesheet" href="<spring:theme code="css"/>" type="text/css" />
<!-- disable back button functionality -->
<!-- 	<script>
	function backButton() {
		window.history.forward(0);
		 if (!navigator.onLine) {
			document.body.innerHTML = 'Loading...';
			window.location = 'logout.htm';
		} 
	}
</script> -->
<!--  no-cache entry for offline mode on firefox -->

<script>
var isOnLineMode = navigator.onLine;
if(!isOnLineMode) {
	alert("Your Browser in offline mode, close the browser and disable offline mode !");
	window.location.href= "logout.htm"; 
}
	
</script>
	
<script type="text/javascript">
	
// 	window.onload=function onLoad()
// 	{
// 		makeThemeAndLanguageSelected();
// 	}
	
	/* function submitThemeForm()
	{
		var param =document.getElementById('themeId').value; 
		var form = document.getElementById('themeForm');
		var path =document.location.href;
		
		var val = path.indexOf("?", 2);
		
		var newPath = "";
		if(val>0)
		{
			newPath =  path+"&theme="+param;
		}
		else
		{
			newPath =  path+"?theme="+param;
		}
	//alert("newPath="+newPath);
		form.action = newPath;
		form.submit();
	}
	
	function submitLanguageForm()
	{
		var param =document.getElementById('languageId').value; 
		var form = document.getElementById('languageForm');
		var path =document.location.href;
		var val = path.indexOf("?", 2);
		var newPath = "";

		if(val>0)
		{
			newPath =  path+"&language="+param;
		}
		else
		{
			newPath =  path+"?language="+param;
		}
		form.action = newPath;
		form.submit();	
	} */
	
	function makeThemeAndLanguageSelected()
	{
	// alert("makeThemeAndLanguageSelected");
		var selectedTheme = document.getElementById("themeId");
		var selectedThemeValue = document.getElementById("selectedTheme").innerHTML;
		//alert(selectedThemeValue);
		selectedTheme.value=selectedThemeValue;
			
		var selectedLanguage = document.getElementById("languageId");
		var selectedLanguageValue = document.getElementById("selectedLanguage").innerHTML;
		//alert(selectedLanguageValue);
		if(selectedLanguage) {
			selectedLanguage.value=selectedLanguageValue;
		}
	}
	
	function submitThemeForm()
	{
	 

	var isOK = confirm("<spring:message code='Message.confirmThemeChange' htmlEscape='true'/>");
		if(isOK)
		{
			document.getElementById('themeForm').submit();
		}else{
		return;	
		}
	}

	function submitLanguageForm(){


	var isOK = confirm("<spring:message code='Message.confirmLanguageChange' htmlEscape='true'/>");
		if(isOK)
		{
			document.getElementById('languageForm').submit();
		}else{
		return;	
		}
	}
		
	 
	</script>
	
<!-- Code add Here for "Single Sign On" by Maneesh-->
<script>
    function showhide()
     {
        var div = document.getElementById("newpost");
    if (div.style.display !== "none") {
        div.style.display = "none";
    }
    else {
        div.style.display = "block";
    }
     }
  </script>
<style type="text/css">
.uparrowdiv{
width:600px;
min-height:40px; /*min height of DIV should be set to at least 2x the width of the arrow*/
background: black;
padding:5px;
position:relative;
word-wrap:break-word;
-moz-border-radius:5px; /*add some nice CSS3 round corners*/
-webkit-border-radius:5px;
border-radius:5px;
margin-bottom:2em;
}
.uparrowdiv:after{ /*arrow added to uparrowdiv DIV*/
content:'';
display:block;
position:absolute;
top:-20px; /*should be set to -border-width x 2 */
left:30px;
width:0;
height:0;
border-color: transparent transparent rgb(255,255,255) transparent; /*border color should be same as div div background color*/
border-style: solid;
border-width: 10px;

}
#grey_box {
  width: 417px;
  height: 450px;
  border: solid 1px #ccc;
  background-color:rgb(255,255,255);
  position: absolute;
  right:26px;
  z-index: 9999;
  -moz-box-shadow:    inset 0 0 10px #000000;
  -webkit-box-shadow: inset 0 0 10px #000000;
  box-shadow:         inset 0 0 10px #000000;
  line-height: 1.3em;
  border: 2px dashed #fff;
  border-radius: 10px;
  box-shadow: 0 0 0 4px gray, 2px 1px 6px 4px rgba(10, 10, 0, 0.5);
  
}

#grey_box li {
    float: left;
    list-style: outside none none;
    text-align: center;
    width: 103px;
}
</style>
<script type="text/javascript">
/*$(document).ready(function(){
	showhide();
});*/
</script>
<%!String contextPath; %>
 <%
 	contextPath = request.getContextPath();
    String imagePath = contextPath + "/images/app_logo/logo";
  %>
<!-- code end here by maneesh -->

	<%--Code commented as told by Arnab on 21-03-2013 --%>
	<%
		response.setCharacterEncoding("utf-8");
   		 response.setHeader("Pragma", "No-cache");
   		 response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
   		 response.setDateHeader("Expires", -1);
	%>
	
	
	
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
</head>
<body>
		<div id="selectedTheme" style="display:none ;">${THEME}</div>
		<div id="selectedLanguage" style="display:none ;">${LANGUAGE}</div>

	<div id="headerwrap">
		<div id="header">
			<div id="new_header">
			
			 <c:choose>
			 <c:when test="${!empty APPLICATION_NAME}">
			<div id="logoleft">${APPLICATION_NAME}</div>
			</c:when>
			
			<c:otherwise>
			<div id="logoleft">Local Government Directory</div>
			</c:otherwise>
			</c:choose>
				
				<div id="logoright"></div>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
			<div id="loginnav">
				<ul class=" listing">
					<li style="float:left;margin-top:10px;">
						<c:choose>
				             <c:when test="${!empty login_key_id}">
				               <div id="mainnav_home">
							 <a href='<c:out value="${HOME_PAGE_ANNOTATION}" escapeXml="true"></c:out>?<csrf:token uri='${HOME_PAGE_ANNOTATION}'/>'>Home</a> 
							 </div>
							 </c:when>
							 <c:otherwise>
							 	<label><a href="<%=getServletContext().getInitParameter("URL") %>/welcome.do?<csrf:token uri='welcome.do'/>">Home</a></label>
							 </c:otherwise>
						</c:choose>
					</li>
					<li  style="float:right;">
						<c:if test="${! empty login_key_id}">
							<b>Welcome <c:out value="${login_key_id}" escapeXml="true"></c:out></b>|
						</c:if>
					
						<c:if test="${! empty login_key_id}">
							<label id="mainnav_logout"><a href="<%=getServletContext().getInitParameter("URL")%>/logout.htm?<csrf:token uri='logout.htm'/>">Logout</a></label>
						</c:if>
					</li>
				</ul>
			</div>
		</div>
		<div class="clear"></div>
		
		<!--  Choose theme and Font size Starts -->
		<div id="topnav">
			<div class="col_left">
				<label class="lbl">Choose Theme :</label>
				<form id="themeForm" name="themeForm" method="get" action="${HOME_PAGE_ANNOTATION}">
					<input type="hidden" name='<csrf:token-name/>'
						value='<csrf:token-value/>' /> <select name="theme" id="themeId"
						class="combofield" onchange="submitThemeForm()">
						<option value="default">
							<spring:message code="Label.SELECT" />
						</option>
						<option value="default">Default Theme</option>
						<option value="mustard">Mustard Theme</option>
						<option value="peach">Peach Theme</option>
						<option value="green">Green Theme</option>
						<option value="blue">Blue Theme</option>
					</select>
				</form>
					<!-- <script>alert('${SWITCHUNIT_PAGE_ANNOTATION}');
					alert('${sessionScope.userid}');
					alert('${APPLICATION_ID}');
					</script>  -->
					<c:if test="${!empty APPLICATION_ID}">
							<%-- <c:if test="${ not empty sessionScope.userid }">  --%>
							 <a style="text-decoration: none;" href='switchunitchangefinancialYear.htm?<csrf:token uri='switchunitchangefinancialYear.htm'/>'>Switch Unit</a>
							<%--  <a style="text-decoration: none;" href='${SWITCHUNIT_PAGE_ANNOTATION}?<csrf:token uri='${SWITCHUNIT_PAGE_ANNOTATION}'/>'>Switch Unit</a> --%>
							 <!--SWITCHUNIT_PAGE_ANNOTATION come from AcrossApplicationAccess-1.4.1.jar so currently we hardcode the switch unit page annotation to synchronize new switch-unit-3.3.jar  by Maneesh Kumar 29Mar2016 -->
						 <%-- </c:if>  --%>
					</c:if>
			</div>
			<div class="col_right">
				<ul class="listing">
					<li>
						<img src="images/help.jpg" alt="user help" title="user help" width="16" height="14" />
						<span>Help</span>
					</li>
					<li><a href="#" class="texttoggler" rel="smallview"
						title="small size"><img src="images/btnMinus.jpg" width="16"
							height="14" border="0" />
					</a>
					</li>
					<li><a href="#" class="texttoggler" rel="normalview"
						title="normal size"><img src="images/btnDefault.jpg"
							width="16" height="14" border="0" />
					</a>
					</li>
					<li><a href="#" class="texttoggler" rel="largeview"
						title="large size"><img src="images/btnPlus.jpg" width="16"
							height="14" border="0" />
					</a>
					</li>
				</ul>
				<script type="text/javascript">
	
					//documenttextsizer.setup("shared_css_class_of_toggler_controls")
					documenttextsizer.setup("texttoggler")
				</script>
			</div>

		</div>
		<!--  Choose theme and Font size Ends -->
		
		<%-- <div id="topnav">
			<ul class="listing">
				<li>
					<label><spring:message text="Choose Theme :"></spring:message></label>
				</li>
				<li>
					<form id="themeForm" name="themeForm" method="get" action="${HOME_PAGE_ANNOTATION}">
					<input type="hidden" name='<csrf:token-name/>' value='<csrf:token-value/>' />
	                <select name="theme" id="themeId" class="combofield" onchange="submitThemeForm()" style="width: 120px;">
								<option value="default">
									<spring:message code="Label.SELECT" />
								</option>
								<option value="default">Default Theme</option>
								<option value="mustard">Mustard Theme</option>
								<option value="peach">Peach Theme</option>
								<option value="green">Green Theme</option>
								<option value="blue">Blue Theme</option>
						</select>
	              </form>
				</li>
				<li  >
					<c:if test="${!empty APPLICATION_ID}">
							<c:if test="${ not empty sessionScope.userid }">
							 <a style="text-decoration: none;" href='${SWITCHUNIT_PAGE_ANNOTATION}?<csrf:token uri='${SWITCHUNIT_PAGE_ANNOTATION}'/>'>Switch Unit</a> 
						</c:if>
					</c:if>
				</li>
				<li>
					<img src="images/help.jpg" width="16" height="14" />
				</li>
				<li>
					<a href="#">Help</a>
				</li>
				<li>
					<a href="#" class="texttoggler" rel="smallview" title="small size"><img src="images/btnMinus.jpg" width="16" height="14" border="0" /> </a>
				</li>
				<li>
					<a href="#" class="texttoggler" rel="normalview" title="normal size"><img src="images/btnDefault.jpg" width="16" height="14" border="0" /></a>
				</li>
				<li>
					<a href="#" class="texttoggler" rel="largeview" title="large size"><img src="images/btnPlus.jpg" width="16" height="14" border="0" /> </a>
				</li>
				<script type="text/javascript">
			  		documenttextsizer.setup("texttoggler");
			 	</script>
			</ul>
		</div> --%>
		
	<div class="clear"></div>
	<div id="breadcrumbnav">
	<ul class="listing">
		<li class="hierarchy">
			<c:if test="${!empty login_key_id}"> ${USERS_SELECTION.assignedUnitHierarchy}</c:if>
		</li>
		<li class="other_apps">
			<a href="#" onclick="showhide()"><img src="images/icon_app.jpg" width="16" height="14" border="0" />  Other Application </a>
	  	</li>
	  	</ul>
	  	
  			<div id="newpost" style="display:none;">
  			  <div id="grey_box" class="uparrowdiv">
  			  <ul>
					<c:forEach items="${allApp}" var="app" varStatus="status">
					    <c:choose>
					  	  <c:when test="${(status.count-1) mod 3  eq 0}"></c:when>
					  	</c:choose>
				  		<c:choose>
				  			<c:when test="${empty app.privilageAppId}">
				  				<li><img width="100" height="70" src=<%=imagePath%><c:out value="${app.pesApplicationId}"/>.png oncilck=location.href="#"/></img>	
							
								<div><c:out value="${app.pesApplicationName} "/></div></li>
				  			</c:when>
				  			<c:otherwise>
				  			<li><a href="<c:out value="${app.pesApplicationUrl}"/>" target="_black">
				  				<img width="100" height="70" src=<%=imagePath%><c:out value="${app.pesApplicationId}"/>.1.png onclick='changeText()'></img>
							<div ><c:out value="${app.pesApplicationName} "/></div></a></li>
				  			</c:otherwise>
				  		</c:choose>
				  	 </c:forEach> 
				  	 </ul>
				</div>
			 </div>
	  	
	</div>
	</div>
</body>
</html>

