<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.owasp.esapi.ESAPI"%>
<%@ page import="in.nic.pes.lgd.bean.Attachment"%>
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
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<!-- <script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script> -->
<script src="js/view.js"></script>
<!-- <link href="css/successMessage.css" rel="stylesheet" type="text/css" /> -->
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script src="js/shiftvillage.js"></script>

<script type="text/javascript" language="javascript">

function getDistrictList(id){
	
	lgdDwrDistrictService.getDistrictList(id, {
		callback : handleDistrictSuccess,
		errorHandler : handleDistrictError
	});
	
}


function handleDistrictSuccess(data) {
	// Assigns data to result id	
	
	var fieldId = 'ddSourceDistrict';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';
	dwr.util.removeAllOptions(fieldId);	
	var st = document.getElementById(fieldId);
	st.add(new Option('---------Select District----------', '0'));		
	dwr.util.addOptions(fieldId, data,valueText,nameText);
}

function handleDistrictError() {
	// Show a popup message
	alert("No data found!");
}

function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
$(document).ready(function() {
	
    if($('#flag1').val() ==1){	   
	    document.getElementById('correctionvillage').style.visibility='visible';
		document.getElementById('correctionvillage').style.display='block';
		document.getElementById('changevillage').style.visibility='hidden';
		document.getElementById('changevillage').style.display='none';
		document.getElementById('captchaAnswer').focus();
  }
  if($('#flag2').val() ==2){	  
			document.getElementById('changevillage').style.visibility='visible';
			document.getElementById('changevillage').style.display='block';
			document.getElementById('correctionvillage').style.visibility='hidden';
			document.getElementById('correctionvillage').style.display='none';
			document.getElementById('captchaAnswers').focus();
		
	  }
});
function manageGlobalVillage(url,id)
{
	dwr.util.setValue('globalvillageId', id, {	escapeHtml : false	});
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}



/* function retrieveFile1(villCode)
{
	
	document.getElementById("villageCode").value	=	villCode;
	document.getElementById('form1').action = "globalviewGovtOrderDetail.do?<csrf:token uri='globalviewGovtOrderDetail.do'/>";
	document.getElementById('form1').submit();
    return true;
	} */
function setDirection(val)
{
	//alert(val);
		//document.getElementById("villageCode").value=0;
	document.getElementById('direction').value=val;
	/* alert("val==== "+val);
	alert("document.getElementById('direction').value==== "+document.getElementById('direction').value); */
	document.forms['form1'].action = "globalviewVillagePagination.do?<csrf:token uri='globalviewVillagePagination.do'/>";
	document.forms['form1'].submit(); 
}

function go1(id)
{
	var url="viewvillagebysubdistrictCode.do?id="+id;
	window.location=url;
	}
dwr.engine.setActiveReverseAjax(true);

function getData() {
	 var code	=	document.getElementById("captchaAnswer").value;
	 if(code == "")
	 {
			document.getElementById("errorCaptcha").innerHTML = "<font color='red'><spring:message code="captcha.errorMessage"/></font>"; 
		  	document.getElementById("errorCaptcha").focus();
			return false;
		}
	else{
		document.getElementById("errorCaptcha").innerHTML= "";
		}
	document.getElementById('form1').method = "post";
	document.getElementById('form1').action = "globalviewvillage.do?<csrf:token uri='globalviewvillage.do'/>";
	document.getElementById('form1')
			.submit();
	errorCaptcha.innerHTML = "";
	return true;
	}
function getParentData()
	{
		//alert("Parent Data called");
		var stateName	=	document.getElementById("ddSourceState").value;
		var districtName	=	document.getElementById("ddSourceDistrict").value;
		var subDistrictName	=	document.getElementById("ddSourceSubDistrict").value;
		var code	=	document.getElementById("captchaAnswers").value;
		
		if(stateName==null || stateName=="")
			 {
			 	//alert(stateName);
			 	document.getElementById("ddSourceState_error").style.visibility ='visible';
				document.getElementById('ddSourceState_error').style.display='block';
			 
			 	//$("#ddSourceState_error").show();
				return false;
		 	}
		 else
		 	{
			 document.getElementById("ddSourceState_error").style.visibility ='hidden';
				document.getElementById('ddSourceState_error').style.display='none';
			}
		if(districtName==null || districtName=="0")
			 {
			 	//alert(districtName);
			 	document.getElementById("ddSourceDistrict_error").style.visibility ='visible';
				document.getElementById('ddSourceDistrict_error').style.display='block';
			 	//$("#ddSourceDistrict_error").show();
				return false;
		 	}
		 else
		 	{
			 document.getElementById("ddSourceDistrict_error").style.visibility ='hidden';
				document.getElementById('ddSourceDistrict_error').style.display='none';
			}
		if(subDistrictName==null || subDistrictName=="0")
			 {
			 	//alert(subDistrictName);
			 	document.getElementById("ddSourceSubDistrict_error").style.visibility ='visible';
				document.getElementById('ddSourceSubDistrict_error').style.display='block';
			 	//$("#ddSourceSubDistrict_error").show();
				return false;
		 	}
		 else
		 	{
				document.getElementById("ddSourceSubDistrict_error").style.visibility ='hidden';
				document.getElementById('ddSourceSubDistrict_error').style.display='none';	
			 //document.getElementById("ddSourceSubDistrict_error").innerHTML= "";
			}
		if(code == "" || code==null)
		 {
				document.getElementById("errorCaptchas").innerHTML = "<font color='red'><spring:message code="captcha.errorMessage"/></font>"; 
			  	document.getElementById("errorCaptchas").focus();
				return false;
			}
		else{
			document.getElementById("errorCaptchas").innerHTML= "";
			}
		//alert("after the innerhtml");
		document.getElementById('form1').method = "post";
		document.getElementById('form1').action = "globalviewvillage.do?<csrf:token uri='globalviewvillage.do'/>";
		document.getElementById('form1')
				.submit();
		errorCaptcha.innerHTML = "";
		return true;
	}


function retrieveFile1(entityCode) {
	
	lgdDwrStateService.getGovtOrderByEntityCode(parseInt(entityCode),'V', {
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
    //alert("anju"+data[0].filelocation); 
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



function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}

/* function loadPage()
{	
	

	} */
	
function doRefresh(removeAll)
{
	document.getElementById('captchaAnswer').value=document.getElementById('captchaAnswers').value='';
	if (removeAll)
		{
			document.getElementById('ddSourceState').selectedIndex=0;
			removeSelectedValue('ddSourceDistrict');
		}
	removeSelectedValue('ddSourceSubDistrict');	
}

function removeSelectedValue(val)
{
  var elSel = document.getElementById(val);
  var i;
  for (i = elSel.length - 1; i>=0; i--) 
   	elSel.remove(i);
}

/* if ( window.addEventListener ) { 
    window.addEventListener( "load", loadPage, false );
 }
 else 
    if ( window.attachEvent ) { 
       window.attachEvent( "onload", loadPage );
 } else 
       if ( window.onLoad ) {
          window.onload = loadPage;
 }
  */
function viewVillageDetails(villageCode)
{
	if(villageCode==null)
		alert("Record not found");
		else
			{

			var form = document.form1;
			var tempTarget = form.target;
			var tempAction = form.action;
			form.target = 'download_page';
			form.method = "post";
			dwr.util.setValue('globalvillageId', villageCode, {	escapeHtml : false	});
			form.action = "globalviewVillageDetail.do?<csrf:token uri='globalviewVillageDetail.do'/>";
		
		
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
	var obj='<c:out value="${display==true}" escapeXml="true"></c:out>';
	/*  var error1='${captchaSuccess2 == false }';
	 var error2='${captchaSuccess1 == false }';
 alert("obj:"+obj+"|error1:"+error1+"|erro2:"+error2+"|"); */
	
	 if(obj=="true" ) /* || (error1=="true" || error2=="true")) */
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
	 
	 
	 document.getElementById('chkcrvillage').checked=false;
	 document.getElementById('chkchvillage').checked=false;
	 if(document.getElementById('text1').value!=''|| document.getElementById('text2').value!='')
	 	{
	 	document.getElementById('chkcrvillage').checked=true;
	 	document.getElementById('chkchvillage').checked=false;
	 	}
	 else if(document.getElementById('ddSourceState').value!=0)
	 	{
	 	document.getElementById('chkcrvillage').checked=false;
	 	document.getElementById('chkchvillage').checked=true;
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
  
  
  
  /* Anju added for View History */
 
  function viewVilageDetailsforVillageVersion(villageCode)
	{
		if(villageCode==null)
			alert("Record not found");
			else
				{

				var form = document.form1;
				var tempTarget = form.target;
				var tempAction = form.action;
				form.target = 'download_page';
				form.method = "post";
				dwr.util.setValue('globalvillageId', villageCode, {	escapeHtml : false	});
				form.action = "viewVillageHistoryReport.do?<csrf:token uri='viewVillageHistoryReport.do'/>";
			
			
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
		code="Label.VIEWVILLAGE"></spring:message>
</title>

</head>
<body oncontextmenu="return false"
	onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);" >

	<%-- <div class="overlay" id="overlay1" style="display: none;"></div>
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
									<c:out value="${param.family_msg}"></c:out>
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
								<c:out value="${family_error}"></c:out>
							</div></td>
					</tr>
				</table>

			</c:if>

		</div>
	</div>

	<div class="box" id="box">
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
	</div>

	<div id="helpDiv" class="helpMessage">
		<div class="helpheader">
			<h4>Help Message</h4>
		</div>
		<div id="helpMsgText" class="helptext"></div>
	</div> --%>

	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message htmlEscape="true" code="Label.VIEWVILLAGE"></spring:message></h3>
			<ul id="showhelp" class="listing">
				<li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" alt="Toggle"	border="0" /></a></li>
				<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message	htmlEscape="true" code="Label.HELP"></spring:message></a></li> --%>
			</ul>
			<a id="showprint" style="visibility: hidden; display: none;" href="#">
				<img src='<%=contextPath%>/images/printer_icon.png' border="0" onclick="CallPrint()" onmouseover="" alt="Print" />
			</a>
		</div>
		
		
		<div class="frmpnlbrdr">
			<form:form action="globalviewvillage.do" id="form1" name="form1" method="POST"
				commandName="villagebean">
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="globalviewVillageDetail.do"/>" />
				<input type="hidden" name="flag1" id="flag1" value="<c:out value='${flag1}'/>"/>
				<input type="hidden" name="flag2" id="flag2" value="<c:out value='${flag2}'/>"/>
				<input type="hidden" name="file" id="file" value="<c:out value='${fileName}'/>"/>
					<input type="hidden" name="govfilePath" id="govfilePath" />
				<input type="hidden" name="fileDisplayType" id="fileDisplayType" />
					<form:input path="globalvillageId" type="hidden" name="globalvillageId" id="globalvillageId" />
				<div id="cat">
					<div class="frmpnlbg">
					<input type="hidden" id="fileNameToDownload" name="fileNameToDownload" />
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"
									code="Label.FILTEROPTFORVIEWVILL"></spring:message>
							</div>
							<ul class="listing">
								<li>
									<label for="chkcrvillage"><form:radiobutton htmlEscape="true" path="correctionRadio" id='chkcrvillage' value='N' onclick='toggledisplay2("chkcrvillage","correctionvillage");setBack();doRefresh(true);' />
									<spring:message htmlEscape="true" code="Label.SEARCHBYNAME"></spring:message>
									</label>
								</li>
								<li>
									<label for="chkchvillage"><form:radiobutton htmlEscape="true" path="correctionRadio" id='chkchvillage' value='P' onclick='toggledisplay2("chkchvillage","changevillage");setBack();doRefresh(true);' />
									<spring:message htmlEscape="true" code="Label.SEARCHBYHIERARCHY"></spring:message>
									</label>
								</li>
								<li>
									<label for="pageRows"><input type="radio" style="display: none" name="correctionRadio" checked="checked" />
										<spring:message htmlEscape="true" code="Label.NOOFRECORDPERPAGE"></spring:message>
									</label>
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
				
				
				<div class="frmpnlbg" id='correctionvillage' style="visibility: hidden; display: none" >
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message htmlEscape="true" code="Label.SEARCHCRITERIA"></spring:message>
						</div>
						
						<div class="search_criteria"> <!-- Seacrh criteria starts -->
							<div class="block">
								<ul class="blocklist">
									<li>
										<label for="text1"><spring:message htmlEscape="true" code="Label.VILLAGECODE"></spring:message></label>
										<form:input type="text" class="frmfield" id="text1" onkeypress="validateNumericKeys(event)" path="code" />
									</li>
									<li>
										<label for="text2"><spring:message htmlEscape="true" code="Label.VILLAGENAMEINENGLISH"></spring:message></label>
										<form:input type="text" class="frmfield" id="text2" onkeypress="validateCharKeys(event)" path="villageNameEnglish" />
									</li>
								</ul>
							</div>
							<div class="block">
								<ul class="captcha_fields">
									<li><img src="captchaImage" alt="Captcha Image" /></li>
									<li>
										<label for="captchaAnswer"><spring:message code="captcha.message"	htmlEscape="true" /></label><br/>
										<form:input	path="captchaAnswer" name="captchaAnswer" class="frmfield" autocomplete="off" />										
										<div class="errormsg">
										<c:if test="${ captchaSuccess1 == false }">
											<spring:message code="captcha.errorMessage" htmlEscape="true" />
										</c:if>
										</div>
										<div id="errorCaptcha" class="errormsg"></div>
										<input type="hidden" id="sessionId" value='<%=sessionId%>'></input>	
									</li>
									<li class="buttons">
										<input type="button" name="Submit" class="btn" onclick="getData();" value="<spring:message htmlEscape="true" code="Button.GETDATA"></spring:message>" />
										<!-- <input type="button" name="Submit2" class="btn" onclick="doRefresh(true);" value=<spring:message htmlEscape="true" code="Button.CLEAR"></spring:message> /> -->
										<input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>" onclick="javascript:go('welcome.do');" />
									</li>
								</ul>
							</div>
							<div class="clear"></div>
						</div> <!-- Seacrh criteria ends -->
						
					</div>
				</div>
				

				<div class="frmpnlbg" id='changevillage' style="visibility: hidden; display: none">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message>
						</div>
						
						<div class="search_criteria"> <!-- Seacrh criteria starts -->
							<div class="block">
								<ul class="blocklist">
									<li>
										<label for="ddSourceState"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message><span class="errormsg">*</span></label>
										<form:select htmlEscape="true" path="stateNameEnglish"
											class="combofield" style="width: 175px" id="ddSourceState"
											onchange="getDistrictList(this.value);document.getElementById('ddSourceState').selectedIndex==0?doRefresh(true):false;">
											<form:option value="">
												<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
											</form:option>
											<form:options items="${stateSourceList}"
												itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
										</form:select> 
										<div class="errormsg" id="ddSourceState_error" style=" visibility: hidden; display:none">Please Select State</div>
									</li>
									<li>
										<label for="ddSourceDistrict"><spring:message	htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message><span class="errormsg">*</span></label>
										<form:select htmlEscape="true"
										path="districtNameEnglish" class="combofield"
										style="width: 175px" id="ddSourceDistrict"
										onchange="getSubDistrictList(this.value);document.getElementById('ddSourceDistrict').selectedIndex==0?doRefresh(false):false;">
										</form:select>
										<div class="errormsg" id="ddSourceDistrict_error" style=" visibility: hidden; display:none">Please Select District</div>
									</li>
									<li>
										<label for="ddSourceSubDistrict"><spring:message htmlEscape="true" code="Label.SELECTSUBDISTRICT"></spring:message><span class="errormsg">*</span></label>
										<form:select htmlEscape="true"
										path="subdistrictNameEnglish" class="combofield"
										style="width: 175px" id="ddSourceSubDistrict">
										</form:select>
										<div class="errormsg" id="ddSourceSubDistrict_error" style=" visibility: hidden; display:none">Please Select Subdistrict</div>
									</li>
								</ul>
							</div>
							<div class="block">
								<ul class="captcha_fields">
									<li><img src="captchaImage" alt="Captcha Image" /></li>
									<li>
										<label for="captchaAnswers"><spring:message code="captcha.message" htmlEscape="true" /></label><br />
												<form:input	path="captchaAnswers" name="captchaAnswers"
												style="width:230px" class="frmfield" autocomplete="off" />												
												<div class="errormsg">
													<c:if test="${ captchaSuccess2 == false }">
														<spring:message code="captcha.errorMessage" htmlEscape="true" />
													</c:if>
													<div id="errorCaptchas" style="color: red;"></div>
												</div>											
									</li>
									<li class="buttons">
										<input type="button" name="Submit" class="btn" onclick="getParentData();" value="<spring:message htmlEscape="true" code="Button.GETDATA"></spring:message>" />
								         <!-- <label> <input type="button" name="Submit2" class="btn"
										value=<spring:message htmlEscape="true" code="Button.CLEAR"></spring:message>
										onClick="doRefresh(true);" /> </label> -->
										<input	type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>"
										onclick="javascript:go('welcome.do');" />
									</li>
								</ul>
							</div>
							<div class="clear"></div>
							
						</div> <!-- Seacrh criteria ends -->
						
					</div>
				</div>


			<c:if test="${display==true}">
				<c:if test="${! empty SEARCH_VILLAGE_RESULTS_KEY}">
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
						<c:if test="${! empty message}">
							<table width="100%" class="tbl_no_brdr" align="center">
							<tr>
							<th align="center" width="80%"> <label>${message}</label> </th>
							</tr>
							</table>
						</c:if>
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="100%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.SNO"></spring:message></td>
												<td width="8%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.VILLAGECODE"></spring:message></td>
												<td width="18%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.VILLAGENAMEINENGLISH"></spring:message></td>
												<td width="18%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.VILLAGENAMEINLOCAL"></spring:message></td>
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
														htmlEscape="true" code="Label.VIEW.History" text="View History"></spring:message></td>		
												<td width="8%" align="center" rowspan="2"><spring:message
														htmlEscape="true" code="Label.VIEW.GOVERNMENTORDER"></spring:message></td>
											</tr>
											<tr class="tblRowTitle tblclear">
												<%-- <td width="7%" align="center"><spring:message
														htmlEscape="true" code="Label.VIEW"></spring:message>
												</td>
												<td width="7%" align="center"><spring:message
														htmlEscape="true" code="Label.VIEW.GOVERNMENTORDER"></spring:message>
												</td> --%>
												
											
												
											</tr>
											<c:forEach var="listVillageDetail" varStatus="listVillageRow"
												items="${SEARCH_VILLAGE_RESULTS_KEY}">
												<tr class="tblRowB">
													<td width="6%"><c:out value="${offsets*limits+listVillageRow.index+1}" escapeXml="true"></c:out></td>
													<td><c:out value="${listVillageDetail.villageCode}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${listVillageDetail.villageNameEnglish}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${listVillageDetail.villageNameLocal}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${listVillageDetail.census2001Code}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${listVillageDetail.census2011Code}" escapeXml="true"></c:out></td>
													<!-- is_pesa column add in district view by Maneesh 18Dec0214 -->
													<td align="left">
												
														<c:choose>
														 <c:when test="${listVillageDetail.isPesa eq 'P'}">
															 <spring:message htmlEscape="true" code="Label.PESA_PARTIALLY_COVERED" text="Partially Covered"/>
														 </c:when>
														  <c:when test="${listVillageDetail.isPesa eq 'F'}">
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
																								onclick="javascript:viewVillageDetails('${listVillageDetail.villageCode}');"
																								width="18" height="19" border="0" alt="View Details" /></a></td>
													
													
										 		<td width="8%" align="center"><a href="#"><img 
																							   src="images/history.png"
																							   onclick="javascript:viewVilageDetailsforVillageVersion('${listVillageDetail.villageCode}');"
																							   width="18" height="19" border="0" alt="View Details" /> </a></td>
																													
													
													
																					
													<%-- <td width="8%" align="center"><a href="#"><img
																								src="images/gvt.order.png"
																								onclick="javascript:retrieveFile1('${listVillageDetail.villageCode}');" 
																								width="18" height="19" border="0" alt="View Details" /></a></td> --%>
													
													<%-- 							<td align="center"><a href="globalviewVillageDetail.do?id=${listVillageDetail.villageCode}&<csrf:token uri="globalviewVillageDetail.do"/>"><img	src="images/view.png" width="18" height="19" border="0" /></a></td>
						 --%>
                                                     

												<c:if test="${listVillageDetail.fileName ne '' }">
												<td width="10%" align="center"><a href="#"><img src="images/gvt.order.png" 
																							 onclick="javascript:retrieveFile1('${listVillageDetail.villageCode}');" 
																							 width="18" height="19" border="0" alt="View Details"/>
												                                             </a>
												</td>
												</c:if>
												<c:if test="${listVillageDetail.fileName eq '' }">	
												<td width="10%" align="center">
												</td>
												</c:if>

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
										</table>
								</td>
								</tr>								
							
							</table>
							
							<ul class="blocklist center" style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
									<li>
										<label><spring:message code="Label.URL" htmlEscape="true"></spring:message>
										<%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%> </label>
									</li>
									<li>
										<%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%>
										<label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message>&nbsp;
													
											 <%=df.format(new java.util.Date())%> 
										</label>
									</li>
									<li>
										<label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
									</li>
								</ul>
								
						
						<div id="footer" style="visibility: hidden; display: none;">
						 
									         <p id="footertext" style="text-align: center">
													Site is designed, hosted and maintained by <a
														target="_blank" href="http://www.nic.in/">National
														Informatics Centre</a><br/> Contents on this website is
														owned,updated and managed by the <a target="_blank"
														href="http://www.panchayat.gov.in/" target="_blank">Ministry of
															Panchayati Raj</a>, Government of India <br> Site best
															viewed on Internet Explorer (IE)-8 &amp; above, Mozilla
															Firefox-11 &amp; above 
												</p>   
				     						 <div id="displayBox" style="text-align: center;display:none;"><img  src="<%= request.getContextPath() %>/images/ajax-loader-2.gif" alt="Loading..."  /></div>
						    
						    			</div> 
						   </div>
					</div>
				</c:if>
				
			</c:if>
				<c:if test="${fn:length(viewPage) > 0}">
					<c:if test="${empty SEARCH_VILLAGE_RESULTS_KEY}">
						<div class="frmpnlbg" id="divData">
							<div class="frmtxt">
								<p class="center"><spring:message htmlEscape="true" code="error.NOVILLFOUND"></spring:message></p>								
							</div>
						</div>
					</c:if>
				</c:if>
				
				<input type="hidden" name="direction" id="direction" value="0" />
				<input type="hidden" name="pageType" value="CV" />
			</form:form>

			<c:if test="${saveMsg != null}">
				<script>
					alert("<c:out value="${saveMsg}"/>");
				</script>
			</c:if>
			<script src="/LGD/JavaScriptServlet"></script>
			
			<div class="buttons center" id="showbutton">
				<input type="button" name="Submit3" class="btn"	value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
												onclick="javascript:go('welcome.do');"/>
			</div>
			
		</div>
	</div>
</body>
</html>

