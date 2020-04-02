<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="in.nic.pes.lgd.utils.ApplicationConstant"%>
<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<%-- <%
	java.text.DateFormat df = new java.text.SimpleDateFormat(
			"dd/MM/yyyy");
%> --%>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script src="/LGD/JavaScriptServlet"></script>
<script type="text/javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
//dwr.engine.setActiveReverseAjax(true);
/* function getData()
{
	document.getElementById('text1').value = trim(document.getElementById('text1').value);
	document.getElementById('text2').value = trim(document.getElementById('text2').value);
	
	if (document.getElementById('text1').value!='' || document.getElementById('text2').value!='')
		document.forms['form1'].submit();
	else
		alert('Please enter search text!');
} */
$(document).ready(function() {
	 if($('#flag1').val() ==1){	
	document.getElementById('captchaAnswer').value="";
	document.getElementById('captchaAnswer').focus();
	 }
});
function manageGlobalState(url,id)
{
	dwr.util.setValue('globalstateId', id, {	escapeHtml : false	});
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}

function trim(stringToTrim) 
{
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}

function doRefresh()
{
	document.getElementById('text1').value=document.getElementById('text2').value='';

}
function getData()
{   
	document.getElementById('form1').method = "post";
	document.getElementById('form1').action = "globalviewstateforcitizen.do?<csrf:token uri='globalviewstateforcitizen.do'/>";
	document.getElementById('form1').submit();
	return true;
	
}

function retrieveFile1(stateCode) {
	lgdDwrStateService.getGovtOrderByEntityCode(parseInt(stateCode),'S', {
	  	 callback: getDataFromServerCallBack
  	});
}

showLoadingImage = function() {
	$.blockUI({ 
		theme:true,
		title: 'Loading...',
		message: "<div style='text-align: center'><img  src='<%= request.getContextPath() %>/images/ajax-loader-2.gif'/></div>"
    }); 
};

hideLoadingImage = function(){
	$.unblockUI();
};

function getDataFromServerCallBack(data) {	
   /* alert(data[0].filelocation); */
	if(data == null) {
	  alert("No Government Order found.");
   } else {
	  var filePath = data[0].filelocation;
	  lgdDwrStateService.openFile(filePath, {
		  callback: openFileCallBack  
	  });
  }
}

function viewstateDetails(stateCode)
{
	if(stateCode==null)
		alert("Record not found");
		else
			{

			var form = document.form1;
			var tempTarget = form.target;
			var tempAction = form.action;
			form.target = 'download_page';
			form.method = "post";
			dwr.util.setValue('globalstateId', stateCode, {	escapeHtml : false	});
			form.action = "globalviewStateDetail.do?<csrf:token uri='globalviewStateDetail.do'/>";
		
		
			if ($.browser.msie) {
				p_windowProperties = "width=903px,height=527px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised";
				newWindow = window.open('', 'download_page', p_windowProperties);
				 if (newWindow) {
					form.submit();
					form.target = tempTarget;
					form.action = tempAction;
					newWindow.focus(); 
			 	 } else {
				      alert('You must allow popups for this to work.');
			    }  
			} else if($.browser.mozilla) {
				 NewWindow = window.open('',"download_page","width=903px,height=527px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised") ;
				form.submit();		
			} else {
				NewWindow = window.open('',"download_page","width=903px,height=527px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised") ;
				form.submit();
			}
			
			}
	
	}
	
	
	
	

function openFileCallBack(data) {
	
	if(data == null ) {
	 alert("File has been moved or deleted.");
} else {
	//str.substring(3,7)
	if(data.length>5)
		{
		var d=data.substring(0,5);
		if(d=="ERROR")
			{
			 alert("File has been moved or deleted.");
			}
		else
			{
				var form = document.form1;
				var tempTarget = form.target;
				var tempAction = form.action;
				form.target = 'download_page';
				form.method = "post";
				form.action = "viewGovtOrder.do?<csrf:token uri='viewGovtOrder.do'/>";
				document.form1.govfilePath.value = data;
				document.form1.fileDisplayType.value = "<%=ApplicationConstant.FILE_INLINE%>";
			
				if ($.browser.msie) {
					p_windowProperties = "width=700px,height=450px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised";
					newWindow = window.open('', 'download_page', p_windowProperties);
					 if (newWindow) {
						form.submit();
						form.target = tempTarget;
						form.action = tempAction;
						newWindow.focus(); 
				 	 } else {
					      alert('You must allow popups for this to work.');
				    }  
				} else if($.browser.mozilla) {
					form.submit();		
				}
				
				else {
					NewWindow = window.open('',"download_page","width=903px,height=527px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised") ;
					form.submit();
				}
		  }
		
		}
	else
		{
		alert("File has been moved or deleted.");
		}
	


}
} 


dwr.engine.setPreHook(showLoadingImage);
dwr.engine.setPostHook(hideLoadingImage);

	function CallPrint()
	{ 
		document.getElementById('footer').style.display = 'block';
		document.getElementById('footer').style.visibility = 'visible';
		var printContent = document.getElementById("printable"); 
		//document.getElementById('btn1').style.visibility = 'hidden';
		var Win4Print = window.open('','','left=0,top=0,width=500,height=500,top=301,left=365,resizable=1,status=0,toolbar=0'); 
		//alert("contect"+printContent.innerHTML);
		Win4Print.document.write(printContent.innerHTML); 
		Win4Print.document.close(); 
		Win4Print.focus(); 
		Win4Print.print(); 
		Win4Print.close(); 
		document.getElementById('footer').style.display = 'none';
		document.getElementById('footer').style.visibility = 'hidden';
	}
	
	function loadBasic()
	{
		 document.getElementById("captchaAnswer").focus();
		
		
		}
	
	
	function viewstateDetailsforStateVersion(stateCode)
	{
		if(stateCode==null)
			alert("Record not found");
			else
				{

				var form = document.form1;
				var tempTarget = form.target;
				var tempAction = form.action;
				form.target = 'download_page';
				form.method = "post";
				dwr.util.setValue('globalstateId', stateCode, {	escapeHtml : false	});
				form.action = "viewStateHistoryReport.do?<csrf:token uri='viewStateHistoryReport.do'/>";
			
			
				if ($.browser.msie) {
					p_windowProperties = "width=903px,height=527px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised";
					newWindow = window.open('', 'download_page', p_windowProperties);
					 if (newWindow) {
						form.submit();
						form.target = tempTarget;
						form.action = tempAction;
						newWindow.focus(); 
				 	 } else {
					      alert('You must allow popups for this to work.');
				    }  
				} else if($.browser.mozilla) {
					 NewWindow = window.open('',"download_page","width=903px,height=527px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised") ;
					form.submit();		
				} else {
					NewWindow = window.open('',"download_page","width=903px,height=527px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised") ;
					form.submit();
				}
				
				}
		
		}
	
	
	
</script>

</head>
<body oncontextmenu="return false"
	onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);"
	 onload="loadBasic();">
	<div id="frmcontent">
		<div class="frmhd">
		<h3 class="subtitle"><spring:message code="Label.VIEWSTATE" htmlEscape="true"></spring:message></h3>				
					<c:if test="${empty SEARCH_STATE_RESULTS_KEY }">
						<ul id="showhelp" class="listing">
							<li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" alt="Toggle"	border="0" /></a></li>
							<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
							<li width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a> --%>
							</li>
						</ul>
					</c:if>					
			
					<c:if test="${!empty SEARCH_STATE_RESULTS_KEY }">
						<a id="showprint" href="#">
							<img src='<%=contextPath%>/images/printer_icon.png' border="0" onclick="CallPrint()" onmouseover="" alt="Print" />
						</a>
					</c:if>
		</div>
		
		<div class="frmpnlbrdr">
			<form:form action="globalviewstateforcitizen.do" name="form1" id="form1" method="POST" commandName="stateView">
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="globalviewStateDetail.do"/>" />
					<input type="hidden" name="stateCode1" id="stateCode1" value="" />
					<input type="hidden" name="govfilePath" id="govfilePath" />
					<input type="hidden" name="fileDisplayType" id="fileDisplayType" />
					<form:input path="globalstateId" type="hidden" name="globalstateId" id="globalstateId" />
				<%--<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="globalviewstateforcitizen.do"/>" />   --%>
				<input type="hidden" name="flag1" id="flag1" value="<c:out value='${flag1}' escapeXml='true'></c:out>" />
				<c:if test="${empty SEARCH_STATE_RESULTS_KEY }">
					<div id="cat">
						<div class="frmpnlbg">
						
						<input type="hidden" id="sessionId" value='<%=sessionId%>'></input>
						<ul class="captcha_fields viewstates">
							<li><img src="captchaImage" alt="Captcha image" /></li>							
							<li> 
								<label for="captcha_field"><spring:message code="captcha.message" htmlEscape="true" /></label><br />
								<form:input	path="captchaAnswer" id="captcha_field" name="captchaAnswer" class="frmfield" autocomplete="off" />								
							</li>
							<li>
								<div class="errormsg">
									<c:if test="${ captchaSuccess == false }">
										<spring:message code="captcha.errorMessage"
											htmlEscape="true" />
									</c:if>
								</div>
							</li>
							<li>
							<input type="button" name="Submit" class="btn" onclick="getData();"	value="<spring:message code="Button.GETDATA"></spring:message>" />
							<input type="button" name="Submit3" class="btn"	value="<spring:message code="Button.CLOSE"></spring:message>"
										onclick="javascript:go('welcome.do');" />
							</li>
						</ul>
							
							
							
						</div>
					</div>
				</c:if>
				
				<c:if test="${! empty SEARCH_STATE_RESULTS_KEY}">
				<div id="printable">
					<c:if test="${!empty SEARCH_STATE_RESULTS_KEY}">
							<h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">
						<spring:message htmlEscape="true" code="Label.VIEWSTATELIST"></spring:message>
						</h6>
						</c:if>
					
					<table width="100%" class="tbl_no_brdr" >						
						<tr>
							<td align="center">
								<table class="tbl_with_brdr" width="100%" align="center">
									<tr class="tblRowTitle tblclear">
										<td width="4%" rowspan="2"><spring:message
												code="Label.SNO"></spring:message></td>
										<td width="10%" rowspan="2"><spring:message
												code="Label.STATECODE"></spring:message></td>
										<td width="19%" rowspan="2"><spring:message
												code="Label.STATENAMEINENGLISH"></spring:message></td>
										<td width="19%" rowspan="2"><spring:message
												code="Label.STATENAMEINLOCAL"></spring:message></td>
										<td width="8%" rowspan="2"><spring:message
												htmlEscape="true" code="Label.CENSUS2001"></spring:message></td>
										<td width="8%" rowspan="2"><spring:message
												htmlEscape="true" code="Label.CENSUSCODE2011"></spring:message></td>
										<td width="8%" rowspan="2" ><spring:message
														htmlEscape="true"  code="Label.VIEW.DETAIL"></spring:message></td>
										<td width="8%" rowspan="2" ><spring:message
														htmlEscape="true"   text="View History"></spring:message></td>				
										<td width="8%" rowspan="2" ><spring:message
														htmlEscape="true" code="Label.VIEW.GOVERNMENTORDER"></spring:message></td>
										<td width="10%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.VIEW.MAP"></spring:message></td>
									</tr>
									<tr class="tblRowTitle tblclear">
									
									<%-- <td></td><td></td><td></td><td></td>
										<td width="14%" align="center"><spring:message
												code="Label.VIEW"></spring:message></td>
										<td width="7%" align="center"><spring:message
														htmlEscape="true" code="Label.VIEW.GOVERNMENTORDER"></spring:message>
										</td> --%>		
									</tr>

									<c:forEach var="listStateDetails" varStatus="listStateRow"
										items="${SEARCH_STATE_RESULTS_KEY}">
										<tr class="tblRowB">
											<td width="6%"><c:out value="${listStateRow.index+1}" escapeXml="true"></c:out></td>
											<td><c:out value="${listStateDetails.stateCode}" escapeXml="true"></c:out></td>
											<td align="left"><c:out value="${listStateDetails.stateNameEnglish}" escapeXml="true"></c:out></td>
											<td align="left"><c:out value="${listStateDetails.stateNameLocal}" escapeXml="true"></c:out></td>
											<td align="left"><c:out value="${listStateDetails.census2001Code}" escapeXml="true"></c:out></td>
											<td align="left"><c:out value="${listStateDetails.census2011Code}" escapeXml="true"></c:out></td>
											<td width="8%" align="center"><a href="#"><img src="images/view.png"
																							<%-- onclick="javascript:manageGlobalState('globalviewStateDetail.do',${listStateDetails.stateCode});" --%>
																							onclick="javascript:viewstateDetails('${listStateDetails.stateCode}');"
																							width="18" height="19" border="0" alt="View Details" /> </a></td>
											
											<td width="8%" align="center"><a href="#"><img src="images/history.png"
																							<%-- onclick="javascript:manageGlobalState('globalviewStateDetail.do',${listStateDetails.stateCode});" --%>
																							onclick="javascript:viewstateDetailsforStateVersion('${listStateDetails.stateCode}');"
																							width="18" height="19" border="0" alt="View Details" /> </a></td>
																							
										<%--  <td> <a	href="view_State_Version.htm?<csrf:token uri='view_State_Version.htm'/>">&nbsp;<spring:message htmlEscape="true" code="Label.TestForm" text="New Test Form"></spring:message></a></td>													
										 --%>
                                        <!-- Changed by Anju Gupta on 12/03/2015 -->
											<td width="10%" align="center">
											<c:if test="${listStateDetails.fileName ne '' }">
												 <a href="#"> <img src="images/gvt.order.png"
													name="viewAttachment" width="22" height="18" alt="View"
													border="0" onclick="javascript:retrieveFile1('${listStateDetails.stateCode}');"
													width="18" height="19" border="0" alt="View Details"/>
												 </a>
											</c:if>
											</td>





												<td width="8%" align="center">
												<%-- <img src="images/showMap.jpg" onclick="javascript:displayMap('${listStateDetails.stateCode}');" width="18" height="19" border="0" /> --%>
												<c:if test="${listStateDetails.mapupload != null}">
                       								<c:choose>
														<c:when test="${!listStateDetails.mapupload}">
															<img  alt="Map" src="images/showMap.jpg" onclick="javascript:displayMap('${listStateDetails.stateCode}');" width="18" height="19" border="0" />
														</c:when>
														<c:when test="${listStateDetails.mapupload}">
															<a href="#" style="text-decoration: none;"></a><%--download --%>
														</c:when>
													</c:choose>
												</c:if>
											</td>
										</tr>
									</c:forEach>

									
								</table>
							</td>
						</tr>						
					</table>
					
					<ul class="blocklist center" style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
						<!-- pagination removed - not required limited records for state search - 23/05/2012 -->
						<c:if test="${! empty SEARCH_STATE_RESULTS_KEY}">
													
						<li><label><spring:message	code="Label.URL" htmlEscape="true"></spring:message>
							   <%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%>
					    	</label>
					    </li>
					    
					    <li><%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%>
					    <label><spring:message	code="Label.REPORTPRINTED" htmlEscape="true"></spring:message>&nbsp;									
							 <%=df.format(new java.util.Date())%>
						</label>
						</li>
						
						<li><label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message>
						</label>
						</li>
							</c:if>
							<li>
							<input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:go('welcome.do');"/>
							</li>
					</ul>
					
					 <div id="footer" style="visibility: hidden; display: none;">
						<p id="footertext" style="text-align: center">
													Site is designed, hosted and maintained by <a
														target="_blank" href="http://www.nic.in/">National
														Informatics Centre</a><br> Contents on this website is
														owned,updated and managed by the <a target="_blank"
														href="http://www.panchayat.gov.in/" target="_blank">Ministry of
															Panchayati Raj</a>, Government of India <br> Site best
															viewed on Internet Explorer (IE)-8 &amp; above, Mozilla
															Firefox-11 &amp; above 
												</p>
						<div id="displayBox" style="text-align: center;display:none;"><img  src="<%= request.getContextPath() %>/images/ajax-loader-2.gif" alt="Loading..."  /></div>
						    
	    			 </div> 
					</div>
					
					
					
				</c:if>
				<c:if test="${fn:length(viewPage) > 0}">
					<c:if test="${empty SEARCH_STATE_RESULTS_KEY}">
						<p class="errormsg"><spring:message	code="error.NOSTATEFOUND"></spring:message></p>							
					</c:if>
				</c:if>

			</form:form>
			<c:if test="${saveMsg != null}">
				<script>
					alert("<c:out value="${saveMsg}"/>");
				</script>
			</c:if>
		</div>
	</div>
	<script language="javascript" type="text/javascript">
		 function displayMap (stateCode) {
			openGISModal(stateCode, 1, "L", null, null);
		 }
	</script>
</body>
</html>


