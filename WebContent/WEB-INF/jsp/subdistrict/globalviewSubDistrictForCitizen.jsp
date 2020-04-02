<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="in.nic.pes.lgd.utils.ApplicationConstant"%>
<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript'
    src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<script src="js/view.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script src="js/district.js"></script>
<script type="text/javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
$(document).ready(function() {
	/* if (document.getElementById('divData')!=null)
		document.getElementById('divData').style.visibility='hidden'; */
    if($('#flag1').val() ==1){	   
	    document.getElementById('showbytext').style.visibility='visible';
		document.getElementById('showbytext').style.display='block';
		document.getElementById('showbydropdown').style.visibility='hidden';
		document.getElementById('showbydropdown').style.display='none';
		document.getElementById('captchaAnswer').focus();
  }
  if($('#flag2').val() ==2){	  
			document.getElementById('showbydropdown').style.visibility='visible';
			document.getElementById('showbydropdown').style.display='block';
			document.getElementById('showbytext').style.visibility='hidden';
			document.getElementById('showbytext').style.display='none';
			document.getElementById('captchaAnswers').focus();
		
	  }
});
function manageGlobalSubDistrict(url,id)
{
	dwr.util.setValue('globalsubdistrictId', id, {	escapeHtml : false	});
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}


function setDirection(val)
{
	document.getElementById('direction').value=val;
	document.forms['form1'].action = "globalviewSubDistrictPagination.do?<csrf:token uri='globalviewSubDistrictPagination.do'/>";
	
	document.forms['form1'].submit();
}


dwr.engine.setActiveReverseAjax(true);



function trim(stringToTrim) {
	return stringtoTrim.replace(/^\s+|\s+$/g,"");
}

function loadPage()
{	
$("#ddSourceDistrict_error").hide();
$("#ddSourceState_error").hide();
document.getElementById('chkcrsubdistrict').checked=false;
document.getElementById('chkchsubdistrict').checked=false;
if(document.getElementById('text1').value!=''|| document.getElementById('text2').value!='')
	{
	document.getElementById('chkcrsubdistrict').checked=true;
	document.getElementById('chkchsubdistrict').checked=false;
	}
else if(document.getElementById('ddSourceDistrict').value!=0)
	{
	document.getElementById('chkcrsubdistrict').checked=false;
	document.getElementById('chkchsubdistrict').checked=true;
	}

	}
	
function doRefresh(removeAll)
{
	document.getElementById('text1').value=document.getElementById('text2').value='';
	if (removeAll)
		{
			document.getElementById('ddSourceState').selectedIndex=0;	
			document.getElementById('ddSourceDistrict').selectedIndex=0;			
		}
	
}

function removeSelectedValue(val)
{
  var elSel = document.getElementById(val);
  var i;
  for (i = elSel.length - 1; i>=0; i--) 
   	elSel.remove(i);
}


function getData() {
	var subdistcode=document.getElementById("text1").value;
	var subdistname=document.getElementById("text2").value;
	
	var code	=	document.getElementById("captchaAnswer").value;
	if(subdistcode!="" || subdistname!="") 
	{
	
	document.getElementById("errInsertSubDistCodeorSubDistName").innerHTML= "";	
	
		 if(code == "")
		 {
			 document.getElementById('errorCaptcha').style.display='block';
				document.getElementById('errorCaptcha').style.visibility='visible';
			  	document.getElementById("errorCaptcha").focus();
				return false;
			}
		else{
			document.getElementById('errorCaptcha').style.display='none';
			document.getElementById('errorCaptcha').style.visibility='hidden';
			}
		document.getElementById('form1').method = "post";
		document.getElementById('form1').action = "globalviewsubdistrict.do?<csrf:token uri='globalviewsubdistrict.do'/>";
		document.getElementById('form1')
				.submit();
		errorCaptcha.innerHTML = "";
		
	}
	else
		{

		document.getElementById("errInsertSubDistCodeorSubDistName").innerHTML = "<font color='red'>District Code or District Name is Required<br/></font>"; 
		   document.getElementById("errInsertSubDistCodeorSubDistName").focus();
		}
	
}

function error_remove()
{
	
	//alert("remove");
	document.getElementById("errSelectStateName").innerHTML ="";
	document.getElementById("errSelectDistName").innerHTML ="";
	}
	
function getParentData() {
	 
	var statename=document.getElementById("ddSourceState").value;
	var distname=document.getElementById("ddSourceDistrict").value;
	if(statename!="")
		{
		//alert("@"+distname+"@");	
		//alert("kk");
		if(distname!=0)
				{
			//alert("ht");	
			document.getElementById('form1').method = "post";
				document.getElementById('form1').action = "globalviewsubdistrict.do?<csrf:token uri='globalviewsubdistrict.do'/>";
				document.getElementById('form1')
						.submit();
				errorCaptcha.innerHTML = "";
				}
			else
				{
				//alert("msg"); 
				document.getElementById("errSelectDistName").innerHTML = "<font color='red'><br/><spring:message code="error.select.SELECTDISTRICTNAME"/><br/><br/></font>"; 
				   document.getElementById("errSelectDistName").focus();
				}
				
		}
		else
		{
		 document.getElementById("errSelectStateName").innerHTML = "<font color='red'><br/><spring:message code="error.select.SELECTSTATENAME"/><br/><br/></font>"; 
		   document.getElementById("errSelectStateName").focus();
		}
	
}

/*  Retrieve the order details
function retrieveFile1(subDistrictCode)
	{
	alert("Inside the retrieveFile123 "+localBodyCode);
	document.getElementById("sdCode1").value	=	subDistrictCode;
	alert("Value of lbCode1 is::::::::::"+document.getElementById("lbCode1").value);
	document.getElementById('form1').action = "globalviewGovtOrderDetailForSD.do?<csrf:token uri='globalviewGovtOrderDetailForSD.do'/>";
	document.getElementById('form1').submit();
    return true;
	} */
	
	
	function retrieveFile1(entityCode) {
		
		lgdDwrStateService.getGovtOrderByEntityCode(parseInt(entityCode),'T', {
		  	 callback: getDataFromServerCallBack
	  	});
	}

	showLoadingImage = function() {
		$.blockUI({ 
			theme:true,
			title: 'Loading...',
			message: "<div style='text-align: center'><img  src='<%= request.getContextPath() %>/resources/images/ajax-loader.gif'/></div>"
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

	
	function viewSubdistrictDetails(subDistrictCode)
	{
		if(subDistrictCode==null)
			alert("Record not found");
			else
				{

				var form = document.form1;
				var tempTarget = form.target;
				var tempAction = form.action;
				form.target = 'download_page';
				form.method = "post";
				dwr.util.setValue('globalsubdistrictId', subDistrictCode, {	escapeHtml : false	});
				form.action = "globalviewSubDistrictDetail.do?<csrf:token uri='globalviewSubDistrictDetail.do'/>";
			
			
				if ($.browser.msie) {
					p_windowProperties = "width=903px,height=580px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised";
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
				}
				else {
					NewWindow = window.open('',"download_page","width=903px,height=527px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised") ;
					form.submit();
				}
				
				}
		
		}
	
	
	function CallPrint()
	{ 
		document.getElementById('footer').style.display = 'block';
		document.getElementById('footer').style.visibility = 'visible';
		var printContent = document.getElementById("divData"); 
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
	
	function setBack()
	{
		
		document.getElementById('showhelp').style.display = 'block';
		document.getElementById('showhelp').style.visibility = 'visible';
		document.getElementById('showprint').style.display = 'none';
		document.getElementById('showprint').style.visibility = 'hidden';
		document.getElementById('showbutton').style.display = 'none';
		document.getElementById('showbutton').style.visibility = 'hidden';
	}
	function ClearAll()
	{
		 var obj='<c:out value="${! empty SEARCH_SUBDISTRICT_RESULTS_KEY}" escapeXml="true"></c:out>';
		 //alert(obj);
		 if(obj=="true")
			 {
			 document.getElementById('showhelp').style.display = 'none';
				document.getElementById('showhelp').style.visibility = 'hidden';
				document.getElementById('showprint').style.display = 'block';
				document.getElementById('showprint').style.visibility = 'visible';
				document.getElementById('showbutton').style.display = 'block';
				document.getElementById('showbutton').style.visibility = 'visible';
			 }
		 else
			 {
			 document.getElementById('showhelp').style.display = 'block';
				document.getElementById('showhelp').style.visibility = 'visible';
				document.getElementById('showprint').style.display = 'none';
				document.getElementById('showprint').style.visibility = 'hidden';
				document.getElementById('showbutton').style.display = 'none';
				document.getElementById('showbutton').style.visibility = 'hidden';
				
			 }
	}
	
	
	  if ( window.addEventListener ) { 
		     window.addEventListener( "load",ClearAll, false );
		  }
		  else 
		     if ( window.attachEvent ) { 
		        window.attachEvent( "onload", ClearAll );
		  } else 
		        if ( window.onLoad ) {
		           window.onload = ClearAll;
		  }
	  
	  
	  function viewDetailsforSubDistrictVersion(subDistrictCode)
		{
			if(subDistrictCode==null)
				alert("Record not found");
				else
					{

					var form = document.form1;
					var tempTarget = form.target;
					var tempAction = form.action;
					form.target = 'download_page';
					form.method = "post";
					dwr.util.setValue('globalsubdistrictId', subDistrictCode, {	escapeHtml : false	});
					form.action = "viewSubDistrictHistoryReport.do?<csrf:token uri='viewSubDistrictHistoryReport.do'/>";
				
				
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

<title><spring:message htmlEscape="true"
		code="Label.VIEWSUBDIST"></spring:message>
</title>
</head>

<body onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);" onload="loadPage();">

<%-- 	<div class="overlay" id="overlay1" style="display: none;"></div>
	<div class="box" id="box1">
		<a class="boxclose" id="boxclose1"></a>
		<div>
			<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<div class="success"></div>
							</center></td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div></td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center>
									<c:out value="${param.family_msg}" escapeXml="false"></c:out>
								</center>
							</div>
						</td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><div class="failur"></div></td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center></td>
					</tr>
					<tr>
						<td><div id="failurMsg" class="errorfont">
								<c:out value="${family_error}" escapeXml="false"></c:out>
							</div></td>
					</tr>
				</table>

			</c:if>

		</div>
	</div> --%>

	<!-- <div class="box" id="box">
		<a class="boxclose" id="boxclose"></a>
		<div id="validate_error">
			<table>
				<tr>
					<td rowspan="2"><div class="errorImg"></div></td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div></td>
				</tr>
				<tr>
					<td><div class="errorfont"><spring:message htmlEscape="true" code="Error.entermandatoryfield"></spring:message></div>
					</td>
				</tr>
			</table>

		</div>
	</div> -->

	<!-- <div id="helpDiv" class="helpMessage">
		<div class="helpheader">
			<h4>Help Message</h4>
		</div>
		<div id="helpMsgText" class="helptext"></div>
	</div> -->
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message htmlEscape="true" code="Label.VIEWSUBDIST"></spring:message></h3>
			<ul id="showhelp" class="listing">
				<li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" alt="Toggle"	border="0" /></a></li>
				<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message	htmlEscape="true" code="Label.HELP"></spring:message> </a></li>				 --%>
			</ul>
			<a id="showprint" style="visibility: hidden; display: none;" href="#">
					<img src='<%=contextPath%>/images/printer_icon.png' border="0" onclick="CallPrint()" onmouseover="" alt="Print" />
			</a>
		</div>
		
		<div class="frmpnlbrdr">
			<form:form action="globalviewsubdistrict.do" id="form1" name="form1" method="POST"
				commandName="subdistrictbean">
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="globalviewSubDistrictDetail.do"/>" />
				<input type="hidden" name="flag1" id="flag1" value="<c:out value='${flag1}'/>"/>
				<input type="hidden" name="flag2" id="flag2" value="<c:out value='${flag2}'/>" />
				<input type="hidden" name="sdCode1" id="sdCode1" value="" />
				<input type="hidden" name="govfilePath" id="govfilePath" />
				<input type="hidden" name="fileDisplayType" id="fileDisplayType" />
				<form:input path="globalsubdistrictId" type="hidden"
										name="globalsubdistrictId" id="globalsubdistrictId" />
				<div id="cat">

					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"
									code="Label.FILTEROPTFORVIEWSUBDIST"></spring:message>
							</div>
							<ul class="listing">
								<li>
									<label for="chkcrsubdistrict"><form:radiobutton path="correctionRadio" id='chkcrsubdistrict' onclick='toggledisplay3("chkcrsubdistrict","showbytext");setBack();doRefresh(true);' />
										<spring:message htmlEscape="true" code="Label.SEARCHBYNAME"></spring:message>
									</label>
								</li>
								<li></li>
								<li><label for="chkchsubdistrict">
										<form:radiobutton
												path="correctionRadio" id='chkchsubdistrict'
												onclick='toggledisplay3("chkchsubdistrict","showbydropdown");setBack();doRefresh(true);' />
										<spring:message htmlEscape="true" code="Label.SEARCHBYHIERARCHY"></spring:message>		
									</label>
								</li>
								<li><input type="radio" style="display: none" name="correctionRadio" checked="checked" /></li>
								<li>
									<label for="pageRows"><spring:message htmlEscape="true" code="Label.NOOFRECORDPERPAGE"></spring:message></label>
												<form:select
												htmlEscape="true" path="pageRows" class="combofield select_xs">
												<form:option value="5" label="5" />
												<form:option value="10" label="10" />
												<form:option value="25" label="25" selected="selected" />
												<form:option value="50" label="50" />
												<form:option value="100" label="100" />
											</form:select> 
								</li>
							</ul>
						</div>

					</div>
				</div>
		</div>


		<div class="frmpnlbg" id='showbytext' style="visibility: hidden; display: none">
			<div class="frmtxt">
				<div class="frmhdtitle">
					<spring:message htmlEscape="true" code="Label.SEARCHCRITERIA"></spring:message>
				</div>
				
				<div class="search_criteria"> <!-- Seacrh criteria starts -->
					<div class="block">
						<ul class="blocklist">
							<li>
								<label for="text1"><spring:message htmlEscape="true" code="Label.SUBDISTRICTCODE"></spring:message></label>
								<form:input type="text" class="frmfield" id="text1" onkeypress="validateNumericKeys(event)" path="code" />
							</li>
							<li>
								<label for="text2"><spring:message htmlEscape="true" code="Label.SUBDISTRICTNAMEENGLISH"></spring:message></label>
								<form:input type="text" class="frmfield" id="text2" onkeypress="validateCharKeys(event)" path="subdistrictNameEnglish" />
								<input type="hidden" id="sessionId" value='<%=sessionId%>'></input>
							</li>
							<li><div id="errInsertSubDistCodeorSubDistName" class="errormsg"></div></li>
						</ul>
					</div>
					<div class="block">
						<ul class="captcha_fields">
							<li><img src="captchaImage" alt="Captcha Image" /></li>
							<li>
								<label for="captchaAnswer"><spring:message code="captcha.message" htmlEscape="true" /></label><br />
								<form:input path="captchaAnswer" name="captchaAnswer" class="frmfield" autocomplete="off" />													
								<div class="errormsg">
									<c:if test="${ captchaSuccess1 == false }">
										<spring:message code="captcha.errorMessage" htmlEscape="true" />
									</c:if>
								</div>
								<div class="errormsg" id="errorCaptcha" style="visibility: hidden; display: none;"><spring:message code="captcha.errorMessage"/></div>
							</li>
							<li class="buttons">
								<input type="button" name="Submit" class="btn" onclick="getData();"	value=<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message> />
								<input	type="button" name="Submit2" class="btn" value=<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>
								onclick="javascript:go('welcome.do');" />
							</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div> <!-- Seacrh criteria ends -->	
				
			</div>
		</div>


		<div class="frmpnlbg" id='showbydropdown' style="visibility: hidden; display: none;">
			<div class="frmtxt">
				<div class="frmhdtitle">
					<label><spring:message htmlEscape="true"
							code="Label.SELECTCRITERIA"></spring:message>
					</label>
				</div>
				
				<div class="search_criteria"> <!-- Seacrh criteria starts -->
					<div class="block">
						<ul class="blocklist">
							<li>
								<label for="ddSourceState"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message></label>
								<form:select path="stateNameEnglish" class="combofield" style="width: 175px" id="ddSourceState"
								            onchange="error_remove();getDistrictList(this.value);document.getElementById('ddSourceState').selectedIndex==0?doRefresh(true):false;">
											
								<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
								<form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
								</form:select>
								<div id="errSelectStateName" style="color: red;">&nbsp;</div>
							</li>
							<li>
								<label for="ddSourceDistrict"><spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message></label>
								<form:select path="districtNameEnglish" class="combofield" style="width: 175px" id="ddSourceDistrict"
									onchange="error_remove();document.getElementById('ddSourceDistrict').selectedIndex==0?doRefresh(false):false;">
								
								<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
								<form:options items="${districtList}" itemValue="districtPK.districtCode" itemLabel="districtNameEnglish"></form:options>
								</form:select> <div id="errSelectDistName" style="color: red;">&nbsp;</div>
							</li>
						</ul>
					</div>
					
					<div class="block">
						<ul class="captcha_fields">
							<li><img src="captchaImage" alt="Captcha Image" /></li>
							<li>
								<label for="captchaAnswers"><spring:message code="captcha.message" htmlEscape="true" /></label><br />
								<form:input path="captchaAnswers" name="captchaAnswers" class="frmfield" autocomplete="off" />
								<div class="errormsg">
									<c:if test="${ captchaSuccess2 == false }">
										<spring:message code="captcha.errorMessage" htmlEscape="true" />
									</c:if>
								</div>
							</li>
							<li class="buttons">
								<input type="button" name="Submit" class="btn" onclick="getParentData();" value="<spring:message htmlEscape="true"  
											code="Button.GETDATA"></spring:message>" />
									<!-- <label> <input type="button" name="Submit2" class="btn" value=<spring:message htmlEscape="true"  
											code="Button.CLEAR"></spring:message> onclick="doRefresh(true)" /> </label> -->
									<input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true" 
											 code="Button.CLOSE"></spring:message>" onclick="javascript:go('welcome.do');" />
							</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div> <!-- Seacrh criteria ends -->
				
				
			</div>
		</div>

		<c:if test="${! empty SEARCH_SUBDISTRICT_RESULTS_KEY}">
			<div class="frmpnlbg" id="divData">
				<div class="frmtxt">
					<table width="100%" class="tbl_no_brdr">
						<tr>
							<td width="14%" align="center">
								
								<c:if test="${! empty message}">
							<table width="100%" class="tbl_no_brdr" align="center">
							<tr>
							<th align="center" width="80%"> <label><c:out value="${message}" escapeXml="true"></c:out></label> </th>
							</tr>
							</table>
							</c:if>
								<table class="tbl_with_brdr" width="100%" align="center">
									<tr class="tblRowTitle tblclear">
										<td width="5%" rowspan="2"><spring:message
												htmlEscape="true" code="Label.SNO"></spring:message></td>
										<td width="8%" rowspan="2"><spring:message
												htmlEscape="true" code="Label.SUBDISTRICTCODE"></spring:message></td>
										<td width="19%" rowspan="2"><spring:message
												htmlEscape="true" code="Label.SUBDISTRICTNAMEENGLISH"></spring:message></td>
										<td width="19%" rowspan="2"><spring:message
												htmlEscape="true" code="Label.SUBDISTRICTNAMELOCAL"></spring:message></td>
										<td width="8%" rowspan="2"><spring:message
												htmlEscape="true" code="Label.CENSUS2001"></spring:message></td>
										<td width="8%" rowspan="2"><spring:message
												htmlEscape="true" code="Label.CENSUSCODE2011"></spring:message></td>
										<!-- is_pesa column add in district view by Maneesh 18Dec0214 -->
									    <td width="9%" rowspan="2"><spring:message htmlEscape="true" code="Label.PESA_STATUS" text="Pesa Status"/></td>
										<!-- is_pesa column add in district view by Maneesh 18Dec0214 -->
										<td width="8%" align="center" rowspan="2"><spring:message
												htmlEscape="true" code="Label.VIEW.DETAIL"></spring:message></td>
										<td width="8%" align="center" rowspan="2"><spring:message
												htmlEscape="true" code="View History"></spring:message></td>
										<td width="8%" align="center" rowspan="2"><spring:message
														htmlEscape="true" code="Label.VIEW.GOVERNMENTORDER"></spring:message></td>
										<td width="8%" align="center"><spring:message
														htmlEscape="true" code="Label.VIEW.MAP"></spring:message></td>
									</tr>
									<tr class="tblRowTitle tblclear">
										<%-- <td width="14%" align="center"><spring:message
												htmlEscape="true" code="Label.VIEW"></spring:message>
										</td>
										<td width="7%" align="center"><spring:message
														htmlEscape="true" code="Label.VIEW.GOVERNMENTORDER"></spring:message>
										</td> --%>
									</tr>

									<c:forEach var="listSubDistrictDetail"
										varStatus="listVillageRow"
										items="${SEARCH_SUBDISTRICT_RESULTS_KEY}">
										<tr class="tblRowB">
											<td width="6%"><c:out value="${offsets*limits+listVillageRow.index+1}" escapeXml="true"></c:out></td>
											<td><c:out value="${listSubDistrictDetail.villageCode}" escapeXml="true"></c:out></td>
											<td align="left"><c:out value="${listSubDistrictDetail.villageNameEnglish}" escapeXml="true"></c:out></td>
											<td align="left"><c:out value="${listSubDistrictDetail.villageNameLocal}" escapeXml="true"></c:out></td>
											<td align="left"><c:out value="${listSubDistrictDetail.census2001Code}" escapeXml="true"></c:out></td>
											<td align="left"><c:out value="${listSubDistrictDetail.census2011Code}" escapeXml="true"></c:out></td>
											<!-- is_pesa column add in district view by Maneesh 18Dec0214 -->
													<td align="left">
														<c:choose>
														 <c:when test="${listSubDistrictDetail.isPesa eq 'P'}">
															 <spring:message htmlEscape="true" code="Label.PESA_PARTIALLY_COVERED" text="Partially Covered"/>
														 </c:when>
														  <c:when test="${listSubDistrictDetail.isPesa eq 'F'}">
														  	<spring:message htmlEscape="true" code="Label.PESA_FULLY_COVERED" text="Fully Covered"/>
														 </c:when>
														 <c:otherwise>
														  	<spring:message htmlEscape="true" code="Label.PESA_NOT_COVERED" text="Not Covered"/>
														 </c:otherwise>
														</c:choose>
													</td>
											<!-- is_pesa column add in district view by Maneesh 18Dec0214 -->
											<td width="8%" align="center"><a href="#"><img
																						src="images/view.png"
																						onclick="javascript:viewSubdistrictDetails('${listSubDistrictDetail.villageCode}');"
																						width="18" height="19" border="0" alt="View Details" /></a></td>
											<td width="8%" align="center"><a href="#"><img src="images/history.png"
																							onclick="javascript:viewDetailsforSubDistrictVersion('${listSubDistrictDetail.villageCode}');"
																							width="18" height="19" border="0" alt="View Details" /> </a></td>
											<td width="8%" align="center">
											<c:if test="${listSubDistrictDetail.fileName ne ''}">
											<a href="#"><img
																						src="images/gvt.order.png"
																						onclick="javascript:retrieveFile1('${listSubDistrictDetail.villageCode}');"
																						width="18" height="19" border="0" alt="View Details" /></a>
											</c:if>											
											</td>
											<td width="8%" align="center">
												<%-- <img src="images/showMap.jpg" onclick="javascript:displayMap('${listSubDistrictDetail.census2011Code}');" width="18" height="19" border="0" /> --%>
												<c:if test="${listSubDistrictDetail.mapupload != null}">
                       								<c:choose>
														<c:when test="${!listSubDistrictDetail.mapupload}">
															<img alt="Map" src="images/showMap.jpg" onclick="javascript:displayMap('${listSubDistrictDetail.census2011Code}');" width="18" height="19" border="0" />
														</c:when>
														<c:when test="${listDistrictDetails.mapupload}">
															<a href="#" style="text-decoration: none;"></a><%--download --%>
														</c:when>
													</c:choose>
												</c:if>
											</td>
										</tr>
									</c:forEach>

									

								</table></td>
						</tr>
						
						<tr>
							<td height="30" align="right">
								<table width="30%">
									<tr>
										<td width="150" align="right" class="pageno"><c:out value="${villageCount}" escapeXml="true"></c:out></td>
										<td width="96" align="right" class="pre"><a href="#"
											onclick="setDirection(-1)"><spring:message
													htmlEscape="true" code="Label.PREVIOUS"></spring:message>
										</a>
										</td>
										<td width="24" align="center" class="pageno">|</td>
										<td width="51" align="right" class="nxt tblclear"><a
											href="#" onclick="setDirection(1)"><spring:message
													htmlEscape="true" code="Label.NEXT"></spring:message>
										</a>
										</td>
										<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
									</tr>
								</table></td>
						</tr>
							
					</table>
					
					<ul class="blocklist center" style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
						<li><label><spring:message
								code="Label.URL" htmlEscape="true"></spring:message>
				<%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%> </label></li>
						<li>
							<%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%>
							<label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message>
							<%=df.format(new java.util.Date())%>
						</li>
						<li>
							<label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
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
				     						 <div id="displayBox" style="text-align: center;display:none;"><img src="<%= request.getContextPath() %>/images/ajax-loader-2.gif" alt="Loading..."  /></div>
						    
						    			</div> 
				</div>
				
			</div>
		</c:if>

		<c:if test="${fn:length(viewPage) > 0}">
			<c:if test="${empty SEARCH_SUBDISTRICT_RESULTS_KEY}">
				<div class="frmpnlbg" id="divData">
					<div class="frmtxt">
						<p class="center"><spring:message htmlEscape="true" code="error.NOSUBDISTFOUND"></spring:message></p>						
					</div>
				</div>
			</c:if>
		</c:if>
		
		<div class="buttons center" id="showbutton">		
			<input type="button" name="Submit3" class="btn"
			value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
			onclick="javascript:go('welcome.do');"/>
		</div>
		
		<input type="hidden" name="direction" id="direction" value="0" /> <input
			type="hidden" name="pageType" value="CT" />
		</form:form>
		<c:if test="${saveMsg != null}">
				<script>
					alert("<c:out value="${saveMsg}"/>");
				</script>
			</c:if>
		<script src="/LGD/JavaScriptServlet"></script>
	</div>
<script language="javascript" type="text/javascript">
	 function displayMap (subDisttCode) {
		openGISModal(subDisttCode, 3, "L", null, null);
	}
</script>	
</body>
</html>

