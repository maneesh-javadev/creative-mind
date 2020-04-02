<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib
	uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld"
	prefix="csrf"%>
<html>
<head>
<link rel="stylesheet" href="<spring:theme code="css"/>" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>E-Panchayati</title>
<!-- <link href="css/panchayat_main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js_files/doctextsizer.js"></script>
<link href="css/panchayat_green.css" rel="alternate stylesheet"
	type="text/css" media="screen" title="green-theme" />
<link href="css/panchayat_peach.css" rel="alternate stylesheet"
	type="text/css" media="screen" title="peach-theme" />
<link href="css/panchayat_blue.css" rel="alternate stylesheet"
	type="text/css" media="screen" title="blue-theme" />
<link href="css/panchayat_new_theme.css" rel="alternate stylesheet"
	type="text/css" media="screen" title="new-theme" />
<script src="js_files/styleswitch.js" type="text/javascript"></script>
<script type="text/javascript" src="js_files/jquery.min.js"></script>
<script type="text/javascript" src="js_files/ddaccordion.js"></script>
<script type="text/javascript" src="js_files/ddaccordion_action.js"></script>
<script type="text/javascript" src="js_files/collapse_pnl.js"></script>
<script type="text/javascript" src="js_files/animatedcollapse.js"></script>
<script type="text/javascript" src="js_files/contentscroller.js"></script> -->

<script type="text/javascript" language="Javascript">

window.onload=function onLoad()
{
	makeThemeAndLanguageSelected();
}

function submitThemeForm()
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
}

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
	selectedLanguage.value=selectedLanguageValue;
}

	function go(url) {

		window.location = url;
	}
	function validateSourceState() {
		var ddsource = document.getElementById('ddSourceState');
		if (ddsource.selectedIndex == 0) {
			$("#ddSourceState_error").show();
			return false;
		} else {
			$("#ddSourceState_error").hide();
			return true;
		}

	}
	function UserName() {
		if (document.getElementById("user").value == "") {
			document.getElementById("userName_error").innerHTML = "Please Enter User Name ";
			$("#userName_error").show();
			$("#userName_msg").hide();
			$("#user").addClass("error_fld");
			$("#user").addClass("error_msg");
			return false;

		} else {
			$("#userName_error").hide();
			$("#userName_msg").hide();
			return true;

		}
	}
	function UserPassword() {
		if (document.getElementById("pass").value == "") {
			document.getElementById("userpass_error").innerHTML = "Please Enter Password";
			$("#userpass_error").show();
			$("#userpass_msg").hide();
			$("#pass").addClass("error_fld");
			$("#pass").addClass("error_msg");
			return false;

		} else {
			$("#userpass_msg").hide();
			$("#userpass_error").hide();
			return true;

		}
	}
	function validateAll() {
		var msg = null;
		if (!validateSourceState()) {
			if (msg != null) {
				msg = msg + "Please Select State" + '\n';
			} else {
				msg = "Please select State" + '\n';
			}
		}
		if (!UserName()) {
			if (msg != null) {
				msg = msg + "Please Enter User Name" + '\n';
			} else {
				msg = "Please Enter User Name" + '\n';
			}
		}
		if (!UserPassword()) {
			if (msg != null) {
				msg = msg + "Please Enter Password " + '\n';
			} else {
				msg = "Please Enter Password" + '\n';
			}
		}
		if (msg != null) {
			alert(msg);
			return false;
		} else {
			return true;
		}
		return false;
	}

	function loadhome() {
		$("#ddSourceState_error").hide();
	}
</script>

</head>

<body onload="loadhome();">
	<div id="selectedTheme" style="display:none ;">${THEME}</div>
	<div id="selectedLanguage" style="display:none ;">${LANGUAGE}</div>
	<div id="maincontainer" class="resize">
		<div id="new_headerwrap">
			<div id="topnav">
				<table width="100%" class="tbl_no_brdr">
					<tr>
						<td width="85" height="25" class="tblclear">Choose Theme :</td>
						<td width="150" class="tblclear">
						<%-- <form id="switchform">
								<select name="switchcontrol" size="1" class="themefield"
									onchange="chooseStyle(this.options[this.selectedIndex].value, 30)">
									<option value="none" selected="selected">Mustard Theme</option>
									<option value="green-theme">Green Theme</option>
									<option value="peach-theme">Peach Theme</option>
									<option value="blue-theme">Blue Theme</option>
									<option value="new-theme">Aqua Theme</option>
								</select>
						</form> --%>
						<form id="themeForm" name="themeForm" method="get" action="#">
						<input type="hidden" name='<csrf:token-name/>' value='<csrf:token-value/>' />
                		  <select name="theme" id="themeId" class="combofield"
							onchange="submitThemeForm()" style="width: 120px;">
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
						
						</td>
						<td class="tblclear">&nbsp;<c:if
								test="${ not empty sessionScope.login_key_id}">
								<a style="text-decoration: none;"
									href="switchunit.htm?<csrf:token uri='switchunit.htm'/>">Switch
									Unit</a>
							</c:if></td>
						<td width="20" class="tblclear"><img src="images/help.jpg"
							width="16" height="14" /></td>
						<td width="50" class="help tblclear"><a href="#">Help</a></td>
						<td width="14" class="tblclear txticon"><a href="#"
							class="texttoggler" rel="smallview" title="small size"><img
								src="images/btnMinus.jpg" width="16" height="14" border="0" />
						</a></td>
						<td width="14" class="tblclear txticon"><a href="#"
							class="texttoggler" rel="normalview" title="normal size"><img
								src="images/btnDefault.jpg" width="16" height="14" border="0" />
						</a>
						</td>
						<td width="28" class="tblclear txticon"><a href="#"
							class="texttoggler" rel="largeview" title="large size"><img
								src="images/btnPlus.jpg" width="16" height="14" border="0" /> </a>
						</td>
						<script type="text/javascript">
							//documenttextsizer.setup("shared_css_class_of_toggler_controls")
							documenttextsizer.setup("texttoggler")
						</script>
						<td width="100" align="right" class="tblclear">Select
							Language</td>
						<td width="108" align="right" class="tblclear">
						<%-- <form
								id="form2" name="form2" method="post" action="">
								<select name="select2" class="themefield">
									<option>English</option>
									<!-- <option>Hindi</option> -->
								</select>
						</form> --%>
						<form id="languageForm" name="languageForm" method="get" action="#">		     
           					  <select id="languageId" name="language" class="combofield" style="width: 120px;" onchange="submitLanguageForm()" >
								<option value="">
								<spring:message code="Label.Language" /> 
								</option>
									<c:if test="${! empty USERS_SELECTION.listLanguagePackages}">
	                  				   	<c:forEach var="languagelist" items="${USERS_SELECTION.listLanguagePackages}">
	                    		   			<option value="${languagelist.languagePackage}" ><spring:message code="${languagelist.languageName}" htmlEscape="true"/></option>
	                    				</c:forEach>
	              					</c:if>
							</select>
       					</form>
						
						</td>
					</tr>
				</table>
			</div>
			<div class="clear"></div>
			<div id="new_header">
				<div id="logoleft">&nbsp;&nbsp;Local Government Directory</div>
				<div id="logoright"></div>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
			<div id="homepagebg">
				<div id="new_homelogin">
					<form:form method="post" action="login.do"
						modelAttribute="loginForm">

						<table width="100%" class="tbl_no_brdr">

						 	<tr>
								<td width="31%">&nbsp;</td>
								<td width="69%" height="25" class="tblclear"><a
									style="text-decoration: none;"
									href="switchunit.htm?<csrf:token uri='switchunit.htm'/>">Login</a>
								</td>
							</tr> 
						
<%-- 
						 	<tr>
								<td><spring:message code="Label.SELSTATE"></spring:message>
								</td>
								<td><form:select path="stateNameEnglish" class="combofield"
										style="width:180px" id="ddSourceState">
										<form:option value="">
											<spring:message code="Label.SEL"></spring:message>
										</form:option>
										<form:options items="${stateSourceList}" itemValue="key"
											itemLabel="value"></form:options>
									</form:select> <span class="errormsg" id="ddSourceState_error"><spring:message
											code="Error.STATE"></spring:message> <form:errors
											path="stateNameEnglish" class="errormsg"></form:errors> </span>
								</td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
							</tr>
							<tr>
								<td height="5" colspan="2"></td>
							</tr>
							<tr>
								<td>User Name</td>
								<td><label> <form:input path="username" id="user"
											class="frmfield" cssStyle="width: 180px;" onblur="UserName()" />
										<span class="errormsg" id="userName_error"></span> </label>
								</td>
							</tr>
							<tr>
								<td height="5" colspan="2"></td>
							</tr>
							<tr>
								<td>Password</td>
								<td><label> <form:password path="password"
											id="pass" class="frmfield" cssStyle="width: 180px;"
											onblur="UserPassword()" /> <span class="errormsg"
										id="userpass_error"></span> </label>
								</td>
							</tr> 


							<tr>
								<td height="5" colspan="2"></td>
							</tr>
					 <tr>
                      <td>&nbsp;</td>
                      <td><label>
                        <input name="Submit" type="submit" class="new_btn" value="Submit" />
                      </label></td>
                    </tr>                    
	--%> 
	
				
	<%--		
					 <tr>
						<td>&nbsp;</td>
						<td><label> <input type="submit" class="new_btn"
								value="Submit" onclick="return validateAll();" />
						</label>
						</td>
					</tr>
	 --%>

	<%--
		  			<tr>
                      <td width="31%">&nbsp;</td>
                      <td width="69%" height="25" class="new_wtlink"><a href="#">Forgot Password</a></td>
                    </tr>
     --%>
						</table>
					</form:form>
				</div>
			</div>
		</div>
		<div class="clear"></div>
		<div id="contentin">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<!-- <ul>
					<li><a href="searchentity.htm">Search</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="">Download Directory</a></li>
					
				</ul> -->
				<tr>
					<td width="240" valign="top">
						<div class="new_pnlstyl">
							<div class="new_pnlbghd">
								<div class="new_pnlctzn">Citizen Section</div>
							</div>
							<div class="clear"></div>
							<div class="new_listmenu">
				<ul>
					<li><a href="globalviewstateforcitizen.do?<csrf:token uri='globalviewstateforcitizen.do'/>">View States</a></li>
					<li><a href="globalviewdistrictforcitizen.do?<csrf:token uri='globalviewdistrictforcitizen.do'/>">View	Districts</a></li>
					<li><a href="globalviewsubdistrictforcitizen.do?<csrf:token uri='globalviewsubdistrictforcitizen.do'/>">View SubDistricts</a></li>
					<li><a href="globalviewvillageforcitizen.do?<csrf:token uri='viewvillageforcitizen.do'/>">View Villages</a></li>
					 <li><a href="globalsearchentity.do?<csrf:token uri='globalsearchentity.do'/>">Search</a></li>  
					<li><a href="downloadDirectory.do?<csrf:token uri='downloadDirectory.do'/>">Download Directory</a></li>
					<li><a href="rptConsolidateforPanchayat.do?<csrf:token uri='rptConsolidateforPanchayat.do'/>">Consolidate Report of LocalBodies</a></li>
				<%--
					<li><a href="rptConsolidateforPanchayatp.htm?<csrf:token uri='rptConsolidateforPanchayatp.htm'/>">Consolidate Report of Panchayats</a></li>
					<li><a href="searchentity.htm?<csrf:token uri='searchentity.htm'/>">Search</a></li>
				--%>
				</ul>
							</div>
							<div class="clear"></div>
							<!-- <div class="new_more"><a href="#">more...</a></div> -->
						</div>
						<div class="clear"></div>
						<div class="new_pnlstyl">
							<div class="new_pnlbghd">
								<div class="new_pnlsprtdoc">Supporting Documentation</div>
							</div>
				<div class="clear"></div>
				<!-- <div class="new_supdoc"> -->
				<div class="new_listmenu">
					<ul>
						<li><a target="_blank" 
							 href="<%=request.getContextPath()%>/usr/serverDocs/lgdFiles/LGD_CBT_Offline/LGD.mp4">CBT (Offline)</a></li>
						<li><a target="_blank" rel="gb_page_center[700, 500]" 
							 href="<%=request.getContextPath()%>/usr/serverDocs/lgdFiles/LGD_CBT_Offline/index.html">CBT (Online)</a></li>
						<li><a target="_blank"
							 href="<%=request.getContextPath()%>/usr/serverDocs/lgdFiles/LocalGovernmentDirectorySWDemo.pptx">Presentation</a></li>
						<li><a target="_blank"
							 href="<%=request.getContextPath()%>/usr/serverDocs/lgdFiles/LGD_Brochure.pdf">Brochure</a></li>
						<li><a target="_blank"
							 href="<%=request.getContextPath()%>/usr/serverDocs/lgdFiles/LGD_updated_manual.pdf">User Manual</a></li>
					</ul>
				</div>
						</div>
					</td>
					<td width="80%" valign="top" class="new_tblcntent">
						<div class="new_pnlstyl">
							<div class="new_pnlbghd">
								<div class="new_pnlabout">About Local Government Directory</div>
							</div>
							<div class="clear"></div>
							<div class="new_pnlparatxt">
								The republic of India comprises the union government, 28 state
								governments, 7 union territories and about 2,45,000 local
								governments. Though there are many software applications
								developed and successfully implemented for catering the needs of
								local governments, there is no comprehensive web site or
								application which provides authenticated and up-to-date
								information on list of local governments. National Panchayat
								Directory (http://panchayatdirectory.gov.in) was an effort of
								Ministry of Panchayati Raj, developed and technically maintained
								by National Informatics Center (NIC, http://www.nic.in ), to
								maintain list of rural local governments that are called as
								Panchayati Raj Institutions. <br /><br /> 
								Local Government Directory will be used by the Central and 
								state departments who are responsible for forming new states/UTs, 
								new districts, new sub-districts, new villages and new local 
								government bodies as well as changing their status , name and formation. "<br />
							</div>
							<div class="clear"></div>
							<!-- <div class="new_more"><a href="#">read more ...</a></div> -->
						</div>
						<div class="new_pnlstyl">
							<div class="new_pnlbghd">
								<div class="new_pnlfaq">FAQ  &nbsp; &nbsp; (coming soon ... )</div>
							</div>
							<div class="clear"></div>
							<div class="new_listfaq">
								<ul>
									<li>&nbsp; How to create a new district in LGD?</li>
									<li>&nbsp; How to Define panchayat setup of a state?</li>
									<li>&nbsp; How to generate Govt. Notification on formation
										of new village panchayat?</li>
									<li>&nbsp; How to change delimitation of a panchayat?</li>
									<li>&nbsp; How to merge village panchayat into a
										municipality?</li>
								</ul>
							</div>
							<div class="clear"></div>
							<!-- <div class="new_more"><a href="#">view all...</a></div> -->
						</div>
					</td>
<%--	---- hidden announcement section ----  15.05.2012 nic-LR ----
				<td width="240" valign="top"><div class="new_pnlstyl">
					<div class="new_pnlbghd">
						<div class="new_pnlannonce">Announcement</div>
					</div>
					<div class="clear"></div>
					<div class="new_pnlcommbg">
						<div class="new_pnlcmnhd">Latest Government order</div>
					</div>
					<div class="clear"></div>
					<div class="new_listmenu">
				<!-- <ul>
					<li><a href=""> </a></li>
					<li><a href=""> </a></li>
					<li><a href=""> </a></li>
					<li><a href=""> </a></li>
				</ul> -->
							</div>
							<div class="clear"></div>
							<!-- <div class="new_more"><a href="#">view all...</a></div> -->
							<div class="clear"></div>
				</td>
--%>
			</tr>
			<tr>
				<td colspan="3">&nbsp;</td>
			</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div id="footer">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td colspan="4" class="fotbrdr"></td>
				</tr>
				<tr>
					<td width="161" class="btmlogospace"><img
						src="images/e_governance_logo.jpg" width="161" height="38" /></td>
					<td width="93" class="btmlogospace"><img
						src="images/panchayatilogo.jpg" width="93" height="38" /></td>
					<td align="right" class="btmlogospace">Site is designed,
						hosted and maintained by National Informatics Centre<br />
						Contents on this website is owned,updated and managed by the
						Ministry of Panchayati Raj</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
