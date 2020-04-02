<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib
	uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld"
	prefix="csrf"%>
<html>
<head>
<noscript><meta http-equiv="Refresh" content="0; URL=nonscript.htm" /></noscript>
<%
	String contextpthval = request.getContextPath();
%>
<link rel="stylesheet" href="<spring:theme code="css"/>" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="<%=contextpthval%>/jquery-1.7.2.js"></script>

<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/util.js'></script>

<script src="<%=contextpthval%>/ui/jquery.ui.core.js"></script>
<script src="<%=contextpthval%>/ui/jquery.ui.widget.js"></script>
<script src="<%=contextpthval%>/ui/jquery.ui.dialog.js"></script>
<script src="<%=contextpthval%>/ui/jquery.ui.position.js"></script>
<link href="<%=contextpthval%>/themes/base/jquery.ui.all.css"
	rel="stylesheet"/>
	
<style type="text/css">
	#hint{
		cursor:pointer;
	}
	.tooltip{
		margin:8px;
		padding:8px;
		border:1px solid black;
		background-color:white;
		position: absolute;
		z-index: 2;
	}
</style>

<script type="text/javascript" language="Javascript">
	var isOnLineMode = navigator.onLine;
	if(!isOnLineMode) {
		alert("Your Browser in offline mode, close the browser and disable offline mode !");
		window.location.href= "logout.htm"; 
	}

	window.onload = function onLoad() {
		makeThemeAndLanguageSelected();
	}

function submitThemeForm() {
	var param =document.getElementById('themeId').value; 
	var form = document.getElementById('themeForm');
	var path =document.location.href;
	
	var val = path.indexOf("?", 2);
	
	var newPath = "";
	if(val > 0)	{
		newPath =  path+"&theme="+param;
	} else {
		newPath =  path+"?theme="+param;
	}
	form.action = newPath;
	form.submit();
}

function submitLanguageForm() {
	var param =document.getElementById('languageId').value; 
	var form = document.getElementById('languageForm');
	var path =document.location.href;
	var val = path.indexOf("?", 2);
	var newPath = "";

	if(val > 0)	{
		newPath =  path+"&language="+param;
	} else {
		newPath =  path+"?language="+param;
	}
	form.action = newPath;
	form.submit();	
}

function makeThemeAndLanguageSelected() {
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

function loadhome()	{
		$("#ddSourceState_error").hide();
		var selectedtitle=getCookie("mysheet");
		if (document.getElementById && selectedtitle!=null) //load user chosen style sheet from cookie if there is one stored			
		setStylesheet(selectedtitle);
}  
	
function showReports(){
		$( "#dialog:ui-dialog" ).dialog("destroy");
		$( "#dialog-message" ).dialog({
			centered: true,
            modal: true,
            width: 600,
            height: 400
		});
}
	
	
	
function download(link,filename) {
		window.open("supportdownloaddoc.do?<csrf:token uri='supportdownloaddoc.do'/>&link="+link+"&filename="+filename,"mywindow","menubar=1,resizable=1");
}

$(document).ready(function() {
	var changeTooltipPosition = function(event) {
		var tooltipX = event.pageX - 8;
		var tooltipY = event.pageY + 8;
		$('div.tooltip').css({top: tooltipY, left: tooltipX});
	};
		 
	var showTooltip = function(event) {
	  	$('div.tooltip').remove();
	  	lgdDwrStateService.getDetailsofDocument("cbt", {
			callback : function(data){
				 $('<div class="tooltip">File Type :'+data[0]+'<br>File Length : '+data[1]+'KB<br>Last Modified :'+data[2]+'</div>')
		            .appendTo('body');
			  changeTooltipPosition(event);
			},
			errorHandler : handleError,
	  	}
	);
};
			
var showTooltipforoffline = function(event) {
		$('div.tooltip').remove();
		lgdDwrStateService.getDetailsofDocument("cbt", {
			callback : function(data){
				$('<div class="tooltip">File Type :'+data[0]+'<br>File Length : '+data[1]+'KB<br>Last Modified :'+data[2]+'</div>')
				.appendTo('body');
				changeTooltipPosition(event);
			},
			errorHandler : handleError,
			}
				  
		);
};
				
				
var showTooltipforpresentation = function(event) {
	  $('div.tooltip').remove();
	
	  lgdDwrStateService.getDetailsofDocument("presentation", {
			callback : function(data){
				 $('<div class="tooltip">File Type :'+data[0]+'<br>File Length : '+data[1]+'KB<br>Last Modified :'+data[2]+'</div>')
		            .appendTo('body');
			  changeTooltipPosition(event);
			},
			errorHandler : handleError,
	  }
	  
	  );
	 
};
		
var showTooltipforusermanual = function(event) {
	  $('div.tooltip').remove();
	  lgdDwrStateService.getDetailsofDocument("usermanual", {
			callback : function(data){
				 $('<div class="tooltip">File Type :'+data[0]+'<br>File Length : '+data[1]+'KB<br>Last Modified :'+data[2]+'</div>')
		            .appendTo('body');
			  changeTooltipPosition(event);
			},
			errorHandler : handleError,
	  }
	  
	  );
	
};
	
var showTooltipforbrouchre = function(event) {
	  $('div.tooltip').remove();
	  lgdDwrStateService.getDetailsofDocument("brochure", {
			callback : function(data){
				 $('<div class="tooltip">File Type :'+data[0]+'<br>File Length : '+data[1]+'KB<br>Last Modified :'+data[2]+'</div>')
		            .appendTo('body');
			  changeTooltipPosition(event);
			},
			errorHandler : handleError,
	  }
	  
	  );
	  
};
	
var showTooltipforregister = function(event) {
		  $('div.tooltip').remove();
		  lgdDwrStateService.getDetailsofDocument("register", {
				callback : function(data){
					 $('<div class="tooltip">File Type :'+data[0]+'<br>File Length : '+data[1]+'KB<br>Last Modified :'+data[2]+'</div>')
			            .appendTo('body');
				  changeTooltipPosition(event);
				},
				errorHandler : handleError,
		  }
		  
		  );
};

var hideTooltip = function() {
   $('div.tooltip').remove();
};


$("a#cbtonline").bind({
	   mousemove : changeTooltipPosition,
	   mouseenter : showTooltip,
	   mouseleave: hideTooltip
});

$("a#cbtoffline").bind({
	   mousemove : changeTooltipPosition,
	   mouseenter : showTooltipforoffline,
	   mouseleave: hideTooltip
});

$("a#presentaion").bind({
	   mousemove : changeTooltipPosition,
	   mouseenter : showTooltipforpresentation,
	   mouseleave: hideTooltip
});

$("a#usermanual").bind({
	   mousemove : changeTooltipPosition,
	   mouseenter : showTooltipforusermanual,
	   mouseleave: hideTooltip
});

$("a#brouchre").bind({
	   mousemove : changeTooltipPosition,
	   mouseenter : showTooltipforbrouchre,
	   mouseleave: hideTooltip
});

$("a#register").bind({
	   mousemove : changeTooltipPosition,
	   mouseenter : showTooltipforregister,
	   mouseleave: hideTooltip
});
});
	
function handletoopTipInfo(data){
		alert(data);
}

function handleError(){
		alert("error");
}

</script>
	<style>
.ui-dialog,.ui-dialog-content {
	border: 5px solid gray;
	background-color: #EFF5FB;
	color: lightblue;
}

.ui-dialog-titlebar {
	background-image: none;
	background-color: #A9D0F5;
}
</style>
</head>

<body onload="loadhome();">
	<div id="selectedTheme" style="display: none;">${THEME}</div>
	<div id="selectedLanguage" style="display: none;">${LANGUAGE}</div>

	<div id="maincontainer" class="resize">

		<!--  Main Header Starts -->
		<div id="header">
			<div id="new_header">
				<div id="logoleft">
					<h1>Local Government Directory</h1>
				</div>
				<div id="logoright"></div>
				<div class="clear"></div>
			</div>

			<div id="loginnav"></div>

		</div>
		<!--  Main Header Ends -->

		<div class="clear"></div>

		<!--  Choose theme and Font size Starts -->
		<div id="topnav">
			<div class="col_left">
				<label class="lbl">Choose Theme :</label>
				<form id="themeForm" name="themeForm" method="get" action="#">
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
			</div>
			<div class="col_right">
				<ul class="listing">
					<li><img src="images/help.jpg" alt="user help" title="user help" width="16" height="14" />
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


		<div class="clear"></div>

		<!--  Homepage BG Starts -->
		<div id="homepagebg">
			<div id="new_homelogin">
				<form:form method="get" action="login.do" modelAttribute="loginForm">
				  	<div class = "loginleftform"><b><a href="login.htm?<csrf:token uri='login.htm'/>">Login</a></b></div>
				  			<form:input path="username" />
					<input type="submit" value="Submit"/>
				</form:form>
			</div>
		</div>
		<!--  Homepage BG Ends -->

		<div class="clear"></div>

		<!--  Main Content Starts -->
		<div id="contentin">

			<div class="col_left">
				<!--  Left Column Starts -->

				<div class="new_pnlstyl">
					<div class="new_pnlbghd">
						<h2 class="title">
							<i class="icon user"></i>Citizen Section
						</h2>
					</div>
					<div class="new_listmenu">
						<ul>

							<%-- <li><a href="globalViewLocalBodyForCitizen.do?<csrf:token uri='globalViewLocalBodyForCitizen.do'/>"><spring:message htmlEscape="true"
																				code="Label.LOCALBODYCITIZEN"></spring:message></a></li>  --%>
							<li><a target="_blank"
								href="globalViewLGDRegistration.do?<csrf:token uri='globalViewLGDRegistration.do'/>"><spring:message
										htmlEscape="true" code="Label.LINKTOLGDREG"></spring:message>
							</a>
							</li>
							<li><a
								href="downloadDirectory.do?<csrf:token uri='downloadDirectory.do'/>"><spring:message
										htmlEscape="true" code="Label.DD"></spring:message>
							</a>
							</li>
							<li><a
								href="globalsearchentity.do?<csrf:token uri='globalsearchentity.do'/>"><spring:message
										htmlEscape="true" code="Label.SEARCH"></spring:message>
							</a>
							</li>

						</ul>
					</div>
				</div>

				<!-- -----------support------------------ -->
				<div class="new_pnlstyl">
					<div class="new_pnlbghd">
						<h2 class="title">
							<i class="icon file"></i>Supporting Documentation
						</h2>
					</div>
					<div class="new_listmenu">
						<div class="new_supdoc">
							<ul>
							<!-- 	<li><a href="cbt.htm?docType=cbtfilepath">CBT</a></li> -->
								 
								 <li><a  id="cbtonline"  href="javascript:download('<%=request.getContextPath()%>/media/CBT/LGD Combined CBT.html','CBT(Play Online)')" >CBT(Play Online)</a></li>
								 <li><a  id="cbtoffline" href="javascript:download('cbt.htm?docType=cbtplaypath','CBT(Play Offline)')">CBT(Play Offline)</a></li>
								 <li><a  id="presentaion" href="javascript:download('cbt.htm?docType=presentationfilepath','Presentation')">Presentation</a></li>
								 <li><a  id="usermanual" href="javascript:download('cbt.htm?docType=UserManualfilepath','User Manual')">User Manual</a></li> 
								 <li><a  id="brouchre" href="javascript:download('cbt.htm?docType=Brochure','Brochure')">Brochure</a></li> 
								 <li><a  id="register" href="javascript:download('cbt.htm?docType=DataCollectionRegisterfilepath','Data Registers')">Data Registers</a></li> 
								  
							</ul>
						</div>
					</div>
				</div>

				<!-- -----------view responses------------------ -->
				<div class="new_pnlstyl">
					<div class="new_pnlbghd">
						<h2 class="title">
							<i class="icon arrow"></i>View Responses
						</h2>
					</div>
					<p>
						<c:if test="${empty responseList}">
							<span id="required" class="mndt"> No Record found </span>							
						</c:if>
					</p>
					<ul class="qa_list">
						<c:if test="${! empty responseList}">
							<c:forEach var="viewResponseList" varStatus="serialNo"
								items="${responseList}">

								<li class="tblRowB"><span class="indexno">${serialNo.index+1}.</span>
									<a href="#"
									onclick="document.getElementById('response ${serialNo.index+1}').style.display=(document.getElementById('response ${serialNo.index+1}').style.display== 'block')?'none':'block';">
										<c:out value="${viewResponseList.queryQuestionText}"
											escapeXml="false" /> </a></li>
								<li id="response ${serialNo.index+1}"><c:out
										value="${viewResponseList.queryAnswerText}" escapeXml="false" />
								</li>

							</c:forEach>
						</c:if>
					</ul>

				</div>

			</div>
			<!--  Left Column Ends -->
		
			<div class="col_ryt"><!--  Right Column Starts -->
				<!--  code given by vinay on 24-09-2014 -->
				<c:if test="${not empty announcementList}">
					<div class="new_pnlstyl">
							<div class="new_pnlbghd">
								<h2 class="title">
									<i class="icon user"></i>Announcement
								</h2>
							</div>
							<div class="new_listmenu">
								<ul>
									<li>
										<c:choose>
											<c:when test="${empty announcementList}">
												<marquee onmouseover="this.stop()"	 onmouseout="this.start()" scrollamount="2"	 direction="up" height="125px">
													<span class="style2"> 
													<spring:message code="manageAnnouncement.norecord" htmlEscape="true"/>
													</span> 
												</marquee>
											</c:when>
											<c:otherwise>
												<marquee onmouseover="this.stop()" onmouseout="this.start()" scrollamount="2"direction="up" height="125px"> 
													<c:forEach var="announcements"	items="${announcementList}">
														<a href="viewpublicAnnouncementdetails.do?announcementId=${announcements.announcementId}" id="announcementId">  
														<span class="style2">
																<b><c:out value="${announcements.features}" escapeXml="false" />... <img src="images/newannounce.gif" height="15" width="35" height="12" border="0" />  </b> <br /> 
																<c:if test="${not empty announcements.features}"> <spring:message code="announce.des" htmlEscape="true"/> :  <c:out	value="${announcements.featureWiseDescription}" escapeXml="false" />  </c:if> 
															 	<c:if test="${empty announcements.features}"> <spring:message code="createAnnouncement.reason" htmlEscape="true"/>:	<c:out	value="${announcements.reason}" escapeXml="false" />  </c:if><br />
																<spring:message code="announcedisplay.from" htmlEscape="true"/>:  <c:out	value="${announcements.displayStartDate}" escapeXml="false" /><br />  
																<spring:message code="announce.to" htmlEscape="true"/>:	 <c:out value="${announcements.displayEndDate}" escapeXml="false" /><br /><br />
														</span> 
														</a>
												 	</c:forEach> 
												</marquee>
											</c:otherwise>
										</c:choose>
											<%-- <c:if test="${ empty announcementList}">
												<marquee onmouseover="this.stop()"	 onmouseout="this.start()" scrollamount="2"	 direction="up" height="125px">
													<span class="style2"> 
													<spring:message code="manageAnnouncement.norecord" htmlEscape="true"/>
													</span> 
												</marquee>
											</c:if>
											
											<marquee onmouseover="this.stop()" onmouseout="this.start()" scrollamount="2"direction="up" height="125px"> 
												<c:forEach var="announcements"	items="${announcementList}">
													<a href="viewpublicAnnouncementdetails.do?announcementId=${announcements.announcementId}" id="announcementId">  
													<span class="style2">
															<b><c:out value="${announcements.features}" escapeXml="false" />... <img src="images/newannounce.gif" height="15" width="35" height="12" border="0" />  </b> <br /> 
															<c:if test="${not empty announcements.features}"> <spring:message code="announce.des" htmlEscape="true"/> :  <c:out	value="${announcements.featureWiseDescription}" escapeXml="false" />  </c:if> 
														 	<c:if test="${empty announcements.features}"> <spring:message code="createAnnouncement.reason" htmlEscape="true"/>:	<c:out	value="${announcements.reason}" escapeXml="false" />  </c:if><br />
															<spring:message code="announcedisplay.from" htmlEscape="true"/>:  <c:out	value="${announcements.displayStartDate}" escapeXml="false" /><br />  
															<spring:message code="announce.to" htmlEscape="true"/>:	 <c:out value="${announcements.displayEndDate}" escapeXml="false" /><br /><br />
													</span> 
													</a>
											 	</c:forEach> 
											</marquee> --%>
									</li>
								</ul>
							</div>
						</div>
					</c:if>
				<%-- 
				<div class="new_listmenu">
						<c:if test="${ empty announcementList}">
							<marquee onmouseover="this.stop()"	 onmouseout="this.start()" scrollamount="2"	 direction="up" height="125px">
								<span class="style2"> 
								<spring:message code="manageAnnouncement.norecord" htmlEscape="true"/>
								</span> 
							</marquee>
						</c:if>
						
							<marquee onmouseover="this.stop()" onmouseout="this.start()" scrollamount="2"direction="up" height="125px"> 
							
								<c:forEach var="announcements"	items="${announcementList}">
									<a href="viewpublicAnnouncementdetails.do?announcementId=${announcements.announcementId}" id="announcementId">  
									<span class="style2">
											<b><c:out value="${announcements.features}" escapeXml="false" />... <img src="images/newannounce.gif" height="15" width="35" height="12" border="0" />  </b> <br /> 
											<c:if test="${not empty announcements.features}"> <spring:message code="announce.des" htmlEscape="true"/> :  <c:out	value="${announcements.featureWiseDescription}" escapeXml="false" />  </c:if> 
										 	<c:if test="${empty announcements.features}"> <spring:message code="createAnnouncement.reason" htmlEscape="true"/>:	<c:out	value="${announcements.reason}" escapeXml="false" />  </c:if><br />
											<spring:message code="announcedisplay.from" htmlEscape="true"/>:  <c:out	value="${announcements.displayStartDate}" escapeXml="false" /><br />  
											<spring:message code="announce.to" htmlEscape="true"/>:	 <c:out value="${announcements.displayEndDate}" escapeXml="false" /><br /><br />
									</span> 
									</a>
							 	</c:forEach> 
							</marquee>
				
				<div class="clear"></div>
				<div class="new_more"></div>
				<div class="clear"></div> --%>
				
				<!-- end code given by vinay on 24-09-2014 -->
				
				<div class="new_pnlstyl">
					<div class="new_pnlbghd">
						<h2 class="title"><i class="icon user"></i>Reports</h2>
					</div>
					<div class="new_listmenu">
						<ul>
							<li><a
								href="globalviewstateforcitizen.do?<csrf:token uri='globalviewstateforcitizen.do'/>">1.
									&nbsp;<spring:message htmlEscape="true" code="Label.VIEWSTATE"></spring:message>
							</a>
							</li>
							<li><a
								href="globalviewdistrictforcitizen.do?<csrf:token uri='globalviewdistrictforcitizen.do'/>">2.
									&nbsp;<spring:message htmlEscape="true" code="Label.VIEWDIST"></spring:message>
							</a>
							</li>
							<li><a
								href="globalviewsubdistrictforcitizen.do?<csrf:token uri='globalviewsubdistrictforcitizen.do'/>">3.
									&nbsp;<spring:message htmlEscape="true"
										code="Label.VIEWSUBDIST"></spring:message> </a>
							</li>
							<li><a
								href="globalviewvillageforcitizen.do?<csrf:token uri='viewvillageforcitizen.do'/>">4.
									&nbsp;<spring:message htmlEscape="true"
										code="Label.VIEWVILLAGE"></spring:message> </a>
							</li>
							<li><a
								href="globalviewBlockforcitizen.do?<csrf:token uri='globalviewBlockforcitizen.do'/>">5.
									&nbsp;<spring:message htmlEscape="true" code="Label.VIEWBLOCK"></spring:message>
							</a>
							</li>
							<li><a
								href="globalviewblockwiseVillageandUlbforcitizen.do?<csrf:token uri='globalviewblockwiseVillageandUlbforcitizen.do'/>">6.
									&nbsp;<spring:message htmlEscape="true"
										code="Label.VIEWBLOCKWISEVILLAGESANDULB"></spring:message> </a>
							</li>
							<li><a href="viewWard.do?<csrf:token uri='viewWard.do'/>">7.
									&nbsp;<spring:message htmlEscape="true" code="Label.VIEWWARD"></spring:message>
							</a>
							</li>
							<li><a
								href="globalViewLocalBodyForCitizen.do?<csrf:token uri='globalViewLocalBodyForCitizen.do'/>">8.
									&nbsp;<spring:message htmlEscape="true"
										code="Label.VIEWLOCALBODY"></spring:message> </a>
							</li>
							
							
							
							
							
							
							<li><a
								href="rptConsolidateforPanchayat.do?<csrf:token uri='rptConsolidateforPanchayat.do'/>">9.
									&nbsp;<spring:message htmlEscape="true"
										code="Label.CONSOLIDATEDRPTOFLB"></spring:message> </a>
							</li>
							<li><a
								href="rptConsolidateforRuralLB.do?<csrf:token uri='rptConsolidateforRuralLB.do'/>">10.
									&nbsp;<spring:message htmlEscape="true"
										code="Label.ConsolidatedReportForRuralLB"></spring:message> </a>
							</li>
						</ul>
					</div>
					<p class="readmore ta_ryt">
						<a href="#" style="text-decoration: none; color: navy;"
							onclick="showReports();">View More Reports</a>
					</p>
					<div id="dialog-message" title="View Reports"
						style="display: none;">
						<div class="new_listmenu">
							<ul>
								<li><a
									href="rptConsolidateforLandregions.do?<csrf:token uri='rptConsolidateforLandregions.do'/>">
										11. &nbsp;<spring:message
											htmlEscape="true" code="Label.CONSOLIDATEDRPTONLRENTITIES"></spring:message>
								</a>
								</li>

								<li><a
									href="rptConsolidateVillageGramPanchayat.do?<csrf:token uri='rptConsolidateVillageGramPanchayat.do'/>">
										12. &nbsp;<spring:message
											htmlEscape="true"
											code="Label.RPTONDISTRICTWISEVILLANDMAPGRAMPANCH"></spring:message>
								</a>
								</li>
								<li><a
									href="rptConsolidateBlockGramPanchayat.do?<csrf:token uri='rptConsolidateBlockGramPanchayat.do'/>">
										13. &nbsp;<spring:message
											htmlEscape="true"
											code="Label.RPTONDISTRICTWISEBLOCKANDMAPGRAMPANCH"
											text="Report on District wise Blocks and Mapped Gram Panchayats"></spring:message>
								</a>
								</li>
								<li><a
									href="rptStatePanchayats.do?<csrf:token uri='rptStatePanchayats.do'/>">
										14. &nbsp;<spring:message
											htmlEscape="true" code="Label.StatePanchayatReportSetup"></spring:message>
								</a>
								</li>
								<li><a
									href="stateWiseUnmappedVillagesReport.do?<csrf:token uri='stateWiseUnmappedVillagesReport.do'/>">
										15. &nbsp;<spring:message
											htmlEscape="true" code="Label.StateWiseUnmappedVillages"></spring:message>
								</a>
								</li>

								<li><a
									href="rptMappedGPNWardforPCAC.do?<csrf:token uri='rptMappedGPNWardforPCAC.do'/>">
										16. &nbsp;<spring:message
											htmlEscape="true" code="Label.mappedLBWARDPCAC"></spring:message>
								</a>
								</li>
								<li><a
									href="stateWiseGPtoVillageMappingReport.do?<csrf:token uri='stateWiseGPtoVillageMappingReport.do'/>">
										17. &nbsp;<spring:message
											htmlEscape="true"
											code="Label.stateWiseGPtoVillageMappingStatus"></spring:message>
								</a>
								</li>
								<li><a
									href="stateWiseGramPanchayats.do?<csrf:token uri='stateWiseGramPanchayats.do'/>">
										18. &nbsp;<spring:message
											htmlEscape="true"
											code="Label.StateWiseGPCencusVillageMapping"></spring:message>
								</a>
								</li>
								<li><a
									href="unmapLBWardforPCAC.do?<csrf:token uri='unmapLBWardforPCAC.do'/>">
										19. &nbsp;<spring:message
											code="Label.unmappedLBWARDPCAC" /> </a>
								</li>
								<li><a
									href="districtWiseLBReport.do?<csrf:token uri='districtWiseLBReport.do'/>">
										20. &nbsp;<spring:message
											code="Label.distwiselbreport" /> </a>
								</li>
								<li><a
									href="lbWiseWardforCitizen.do?<csrf:token uri='lbWiseWardforCitizen.do'/>">
										21. &nbsp;<spring:message
											code="label.HeaderSummaryReportWard" /> </a>
								</li>
								<li><a
									href="rptViewUnmappedLocalBodies.do?<csrf:token uri='"rptViewUnmappedLocalBodies.do'/>">
										22. &nbsp;<spring:message
											htmlEscape="true" code="Label.viewUnmappedlocalbodies"></spring:message>
								</a>
								</li>
								<li><a
									href="rptDistrictWiseInvalidatedVillage.do?<csrf:token uri='"rptDistrictWiseInvalidatedVillage.do'/>">
										23. &nbsp;<spring:message
											htmlEscape="true"
											code="Label.rptdistrictWiseInvalidatedVillage"></spring:message>
								</a>
								</li>
								<li><a
									href="dpwardConstituencyWiseVP.do?<csrf:token uri='"dpwardConstituencyWiseVP.do'/>">
										24. &nbsp;<spring:message
											htmlEscape="true" code="Label.DPwardConstituencyWiseVP"></spring:message>
								</a>
								</li>
								<li><a
									href="districtWiseDetailReport.do?<csrf:token uri='"districtWiseDetailReport.do'/>">
										25. &nbsp;<spring:message
											htmlEscape="true" code="Label.DistrictWiseDetailReport"
											text="District wise Detailed Report"></spring:message> </a>
								</li>
								<li><a
									href="listOfPesaPanchyat.do?<csrf:token uri='"listOfPesaPanchyat.do'/>">
										26. &nbsp;<spring:message
											htmlEscape="true" code="Label.ListofPESAPanchayat"
											text="List of PESA Panchayat"></spring:message> </a>
								</li>
								<!-- added by Ashish Dhupia on 20/1/2015 for Habitation use case -->
								<li>
									<a href="habitation.do?<csrf:token uri='"habitation.do'/>">
									27. &nbsp;<spring:message htmlEscape="true" code="Label.habitation" text="Report on habitation"></spring:message> </a>
								</li>
								<li><a href="departOrganizationUnit.do?<csrf:token uri='"departOrganizationUnit.do'/>">
										28. &nbsp;<spring:message htmlEscape="true" code="Label.ROOUNIT" text="Report on Organization Unit"></spring:message> </a>
								</li>
								<li><a href="stateFreezeReport.do?<csrf:token uri='"stateFreezeReport.do'/>">
										29. &nbsp;<spring:message htmlEscape="true" code="Label.StateFreezeStatusReport" text="State Freeze Status Report"></spring:message> </a>
								</li>
								
								<li><a
								href="localBodyInvalidation.do?<csrf:token uri='localBodyInvalidation.do'/>">48.
									&nbsp;localBodyInvalidation</spring:message> </a>
							</li>
							</ul>
						</div>
					</div>

				</div>

				<!--  ----------Training---------------- -->
				<div class="new_pnlstyl">
					<div class="new_pnlbghd">
						<h2 class="title">
							<i class="icon user"></i>Training
						</h2>
					</div>
					<div class="new_listmenu">
						<ul>
							<li><a
								href="http://164.100.72.23/priprofiler/ReportData.do?method=getAllTraining&type=T&trtype=LGD"
								target="_blank"><spring:message htmlEscape="true"
										code="Label.TRAININGSCHEDULE"></spring:message> </a></li>
						</ul>
					</div>
				</div>


			</div>
			<!--  Right Column Ends -->

			<div class="col_middle">
				<!--  Middle Column Starts -->
				<!-- 	----------------about LGD --------------- -->
				<div class="new_pnlstyl">
					<div class="new_pnlbghd">
						<h2 class="title">
							<i class="icon about"></i>About Local Government Directory
						</h2>
					</div>
					<p class="new_pnlparatxt">
						The Ministry of Panchayati Raj (MoPR) has undertaken e-Panchayat
						Mission Mode Project(e-Panchayat MMP) with a view to introduce and
						strengthen e-Governance in Panchayati Raj Institutions (PRIs)
						across the country and build associated capacities of the PRIs for
						effective adoption of the e-Governance initiative. Under this
						project, Panchayat Enterprise Suite (PES) has been conceptualised
						which comprises 11 Core Common applications. At present, Panchayat
						Enterprise Suite has been deployed/operational with 10 Core Common
						Applications and GIS layer module is under conceptualisation. The
						operational modules includes LGD(Local Government Directory), Area
						Profiler(Socio-economic & general details), PlanPlus(to strengthen
						Decentralised & Participatory Planning), PriaSoft(Panchayat
						Accounting), ActionSoft(Works/scehme implementation Monitoring
						System), NAD(National Asset Directory), Service Plus(To facilitate
						Service Delivery), Social Audit, Training and National Panchayat
						Portal(Dynamic Website of Panchayats) <br /> <br /> Local
						Government Directory will be used by the Central and State
						departments who are responsible for forming new States/UTs, new
						Districts, new Sub-Districts, new villages and new local
						government bodies as well as changing their status , name and
						formation."
					</p>
				</div>

				<!-- ---------------- FAQ --------------- -->
				<div class="new_pnlstyl">
					<div class="new_pnlbghd">
						<h2 class="title">
							<i class="icon faq"></i>FAQ
						</h2>
					</div>
					<p>
						<c:if test="${empty faqList}">
							<span id="required" class="mndt"> <spring:message
									code="manageAnnouncement.FAQ" htmlEscape="true" />
						</c:if>
					</p>

					<ul class="qa_list faq">

						<c:if test="${! empty faqList}">
							<c:forEach var="viewFAQList" varStatus="serialNo"
								items="${faqList}">
								<li class="tblRowB"><span class="indexno">${serialNo.index+1}.</span>
									<a href="#"
									onclick="document.getElementById('${serialNo.index+1}').style.display=(document.getElementById('${serialNo.index+1}').style.display== 'block')?'none':'block';">
										<c:out value="${viewFAQList.faqQuestionText}"
											escapeXml="false" /> </a></li>

								<li id="${serialNo.index+1}"><c:out
										value="${viewFAQList.faqAnswerText}" escapeXml="false" /></li>

							</c:forEach>
						</c:if>

					</ul>
					<p class="readmore">
						If you have any Queries to be answered by the PES Technical Team,
						Click Add Query <a
							href="globalAddQuery.do?<csrf:token uri='globalAddQuery.do'/>">Click
							Here</a>
					</p>
				</div>

			</div>
			<!--  Middle Column Ends -->

		</div>
		<!-- Main Content Ends -->

		<!--  Footer Starts -->
		<div id="footer">
			<div class="footer_links">
				<ul class="listing">
					<li><a
						href="citizenFeedback.do?<csrf:token uri='"citizenFeedback.do'/>">
							<spring:message htmlEscape="true" code="Label.citizen.feedback"
								text="Feedback" /> </a>
					</li>
					<li><a href="contactUs.do?<csrf:token uri='"contactUs.do'/>">
							<spring:message htmlEscape="true" code="Label.citizen.contactus"
								text="Contact Us" /> </a>
					</li>
					<li><a href="siteMap.do?<csrf:token uri='"siteMap.do'/>">
							<spring:message htmlEscape="true" code="Label.citizen.sitemap"
								text="Site Map" /> </a>
					</li>
					<li><a href="copyRightPolicy.do?<csrf:token uri='"copyRightPolicy.do'/>">
							<spring:message htmlEscape="true" code=""
								text="Copyright Policy" /> </a>
					</li>
					<li><a href="privacyPolicy.do?<csrf:token uri='"privacyPolicy.do'/>">
							<spring:message htmlEscape="true" code=""
								text="Privacy Policy" /> </a>
					</li>
					<li><a href="termsconditions.do?<csrf:token uri='"termsconditions.do'/>">
							<spring:message htmlEscape="true" code=""
								text="Terms And Conditions" /> </a>
					</li>
				</ul>
			</div>
			<div class="copyrights">
				<div class="col_left">
					<ul class="listing">
						<li><img src="images/e_governance_logo.jpg" width="161"
							height="38" />
						</li>
						<li><img src="images/panchayatilogo.jpg" width="93"
							height="38" />
						</li>
						<li><a target="_blank" href="http://india.gov.in/"
							title="http://india.gov.in, The National Portal of India : External website that opens in a new window">
								<img height="38" width="158" src="images/india_govlogo.jpg"
								alt="http://india.gov.in, The National Portal of India : External website that opens in a new window" />
						</a>
						</li>
					</ul>
				</div>
				<div class="col_ryt">
					<p>
						Site is designed, hosted and maintained by <a target="_blank" href="http://www.nic.in/"> National Informatics
						Centre</a><br> Contents on this website are owned,updated and managed by the
						State Panchayati Raj </a>/Local Government Departments<br>
						Site best viewed on Internet Explorer (IE)-8 &amp; above, Mozilla Firefox-11 &amp; above&nbsp; ${display_version}
					</p>
				</div>
				<div class="clear"></div>
			</div>

		</div>
		<!--  Footer ends -->

		<c:if test="${msg != null}">
			<script>
				alert("<c:out value="${msg}"/>");
			</script>
		</c:if>
</body>
</html>