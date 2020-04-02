<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<script src="js/view.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<!-- <script src="js/shiftvillage.js"></script> -->
<script src="js/district.js"></script>
<script type="text/javascript">

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
	dwr.util.setValue('globalblockId', id, {	escapeHtml : false	});
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}


function getDistrictList(id){
	//alert("function called");
	
	lgdDwrDistrictService.getDistrictList(id, {
		callback : handleDistrictSuccess,
		errorHandler : handleDistrictError
	});
	
}

function handleDistrictSuccess(data) {
	// Assigns data to result id	
	//alert("data");
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
	//alert("Error");
	alert("No data found!");
}

function go1(id)
{
	var url="viewsubdistrictbydistrictCode.do?id="+id;
	window.location=url;
	}
dwr.engine.setActiveReverseAjax(true);


function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
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



/* 
function checkBlockCode(){
	 var code	=	document.getElementById("text1");
	   if(isNaN(code) || code==0)
		{
			document.getElementById("errBlockCode").innerHTML = "<font color='red'><br/><spring:message code="Error.INCORECT.BLOCKCODE"/><br/><br/></font>"; 
		    document.getElementById("errBlockCode").focus();
		    return false;
		}
}


function checkBlockName(){
	var name	=	document.getElementById("text2");
	
	if(!checkName(name))
	{
	document.getElementById("errBlockName").innerHTML = "<font color='red'><br/><spring:message code="Error.INCORECT.BLOCKNAME"/><br/><br/></font>"; 
    document.getElementById("errBlockName").focus();
    return false;
	}
}
 */
function getData() {
	 var code	=	document.getElementById("captchaAnswer").value;
	 if(code == ""){
	   alert("Please Enter Captcha Text Code");
		return false;
		}
	document.getElementById("errBlockCode").innerHTML= "";
	document.getElementById("errBlockName").innerHTML= "";

	  var code	=	document.getElementById("text1").value;
	 var name	=	document.getElementById("text2").value;
	
	
 if(code != ""){
		if(isNaN(code))
	{
	 
	document.getElementById("errBlockCode").innerHTML = "<font color='red'><spring:message code="Error.INCORECT.BLOCKCODE"/></font>"; 
	 document.getElementById("text1").value="";
    document.getElementById("text1").focus();
    return false;
	}
	/* else
	{
		document.getElementById("errBlockCode").innerHTML= "";
	} */
 }

 if(name != ""){
		if(!checkName(name))
	{
	document.getElementById("errBlockName").innerHTML = "<font color='red'><spring:message code="Error.INCORECT.BLOCKNAME"/></font>"; 
	 document.getElementById("text2").value="";
    document.getElementById("text2").focus();
    return false;
	}
	/* else
	{
		document.getElementById("errBlockName").innerHTML= "";
	} */
 }
 document.getElementById('form1').method = "post";
	document.getElementById('form1').action = "globalviewBlock.do?<csrf:token uri='globalviewBlock.do'/>";
	document.getElementById('form1')
			.submit();
	errorCaptcha.innerHTML = "";

}

function checkName(name) {

	
	if (/[^a-zA-Z0-9\x20\x09\x2D	]/g.test(name)) {
		return false;
	} else {

		return true;
	}
}

function getDatabyParent() {
	    var inSelectState	=	document.getElementById("ddSourceState").value;
		var inSelectDist	=	document.getElementById("ddSourceDistrict").value;
		
		if(inSelectState==null ||inSelectState=="")
		{  
			document.getElementById("errProSelect").innerHTML = "<font color='red'><spring:message code="Error.STATE"/></font>"; 
		    document.getElementById("errProSelect").focus();
		   		    return false;
		}
		else
		{
			document.getElementById("errProSelect").innerHTML= "";
		}
		if(inSelectDist==null ||inSelectDist==""||inSelectDist=="0")
		{   
			
			document.getElementById("errProSelectDist").innerHTML = "<font color='red'><spring:message code="error.select.DISTRICTFRMLIST"/></font>"; 
		    document.getElementById("errProSelectDist").focus();
		
		    return false;
		}
		else
		{
			document.getElementById("errProSelectDist").innerHTML= "";
		} 
	    document.getElementById('form1').method = "post";
		document.getElementById('form1').action = "globalviewBlock.do?<csrf:token uri='globalviewBlock.do'/>";
		document.getElementById('form1')
				.submit();
		errorCaptcha.innerHTML = "";
	}

function setDirection(val)
{   
	document.getElementById('direction').value=val;
	document.forms['form1'].action = "viewglobalBlockPagination.do?<csrf:token uri='viewglobalBlockPagination.do'/>";
	document.forms['form1'].submit();
}

function viewBlockDetails(blockCode)
{
	if(blockCode==null)
		alert("Record not found");
		else
			{

			var form = document.form1;
			var tempTarget = form.target;
			var tempAction = form.action;
			form.target = 'download_page';
			form.method = "post";
			dwr.util.setValue('globalblockId', blockCode, {	escapeHtml : false	});
			form.action = "viewglobalBlockDetail.do?<csrf:token uri='viewglobalBlockDetail.do'/>";
		
		
			if ($.browser.msie) {
				p_windowProperties = "width=903px,height=448px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised";
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
	 var obj='<c:out value="${! empty SEARCH_BLOCK_RESULTS_KEY}" escapeXml="true"></c:out>';
	/* alert(obj); */
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
</script>

<title><spring:message htmlEscape="true"  code="Label.VIEWBlock"></spring:message></title>
</head>

<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);" onload="loadPage();">
	
	<div class="overlay" id="overlay1" style="display: none;"></div>
	<div class="box" id="box1">
		<a class="boxclose" id="boxclose1"></a>
		<div>
			<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<div class="success"></div>
							</center>
						</td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div>
						</td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center>
							</div></td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><div class="failur"></div>
						</td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center>
						</td>
					</tr>
					<tr>
						<td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="true"></c:out></div>
						</td>
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
					<td rowspan="2"><div class="errorImg"></div>
					</td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div>
					</td>
				</tr>
				<tr>
					<td><div class="errorfont">
					<spring:message htmlEscape="true" code="Error.entermandatoryfield"></spring:message></div>
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
	</div>
	
		<div id="frmcontent">
			<div class="frmhd">
				<h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.VIEWBLOCK"></spring:message></h3>
				<ul id="showhelp" class="listing">
					<!--//this link is not working <li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" alt="Toggle"	border="0" /></a></li> -->
				<%-- 	these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
					<li><a href="#" class="frmhelp"><spring:message	htmlEscape="true" code="Label.HELP"></spring:message> </a></li> --%>
				</ul>	
				<a id="showprint" style="visibility: hidden; display: none;" href="#">
					<img src='<%=contextPath%>/images/printer_icon.png' border="0" onclick="CallPrint()" onmouseover="" alt="Print" />
				</a>		
			</div>
						
			<div class="frmpnlbrdr">
				<form:form action="globalviewBlock.do" id="form1" name="form1" method="POST" commandName="blockbean">	
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="globalviewBlock.do"/>"/>
				<input type="hidden" name="flag1" id="flag1" value="<c:out value='${flag1}' escapeXml='true'></c:out>"/>
				<input type="hidden" name="flag2" id="flag2" value="<c:out value='${flag2}' escapeXml='true'></c:out>"/>
				<form:input path="globalblockId" type="hidden" name="globalblockId" id="globalblockId"  />	
						  
				<div id="cat">
					 <div class="frmpnlbg">
						      <div class="frmtxt">
							     <div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.FILTEROPTFORVIEWBLOCK"></spring:message></div>
							     <ul class="listing">
							     	<li>
							     		<label for="chkcrsubdistrict"><form:radiobutton path="correctionRadio" id='chkcrsubdistrict' 
                                                onclick='toggledisplay3("chkcrsubdistrict","showbytext");setBack();doRefresh(true);'/>                                                 
                                              <spring:message htmlEscape="true"  code="Label.SEARCHBYNAME"></spring:message></label>
							     	</li>
							     	<li></li>
							     	<li>
							     		<label for="chkchsubdistrict">
                                                <form:radiobutton path="correctionRadio"  id='chkchsubdistrict'
                                                 onclick='toggledisplay3("chkchsubdistrict","showbydropdown");setBack();doRefresh(true);' />
                                        		<spring:message htmlEscape="true"  code="Label.SEARCHBYHIERARCHY"></spring:message>
                                        </label>
							     	</li>
							     	<li><input type="radio" style="display: none" name="correctionRadio" checked="checked"/></li>
							     	<li>
							     		<label for="pageRows"><spring:message htmlEscape="true" code="Label.NOOFRECORDPERPAGE"></spring:message></label>
										<form:select htmlEscape="true" path="pageRows" class="combofield select_xs">
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
				
					
					<div class="frmpnlbg" id='showbytext' style=" visibility: hidden; display:none">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"  code="Label.SEARCHCRITERIA"></spring:message>
							</div>
							
							<div class="search_criteria"> <!-- Seacrh criteria starts -->
								<div class="block">
									<ul class="blocklist">
										<li>
											<label for="text1"><spring:message htmlEscape="true"  code="Label.BLOCKCODE"></spring:message></label>
											<form:input type="text" class="frmfield" id="text1"  path="code"  /></label>
											<div id=errBlockCode class="errormsg">
										</li>
										<li>
											<label for="text2"><spring:message htmlEscape="true"  code="Label.BLOCKNAMEINENGLISH"></spring:message></label>
											<form:input type="text" class="frmfield" id="text2"  path="blockNameEnglish" />
											<div id="errBlockName" class="errormsg"></div>
										</li>
										<li><input type="hidden" id="sessionId"	value='<%=sessionId%>'></input></li>
									</ul>
								</div>
								<div class="block">
									<ul class="captcha_fields">
										<li><img src="captchaImage" alt="Captcha Image" /></li>
										<li>
											<label for="captchaAnswer"><spring:message code="captcha.message" htmlEscape="true"/></label><br/>
											<form:input path="captchaAnswer" name="captchaAnswer" class="frmfield"  autocomplete="off" />
											<div class="errormsg">
												<c:if test="${ captchaSuccess1 == false }">
													<spring:message code="captcha.errorMessage" htmlEscape="true"/>
												</c:if>
											</div>
										</li>
										<li class="buttons">
											<input type="button" name="Submit" class="btn" onclick="getData();" value="<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message>" />
											<!-- <input type="button" name="Submit2" class="btn" value=<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message> onclick="doRefresh(true)" /> -->
											<input type="button" name="Submit2" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:go('welcome.do');" />
										</li>
									</ul>
								</div>
								<div class="clear"></div>
							</div> <!-- Seacrh criteria ends -->
														
						</div>
					</div>
					
					
					<div class="frmpnlbg" id='showbydropdown' style=" visibility: hidden; display:none">
						      <div class="frmtxt">
							     <div class="frmhdtitle"><label><spring:message htmlEscape="true"  code="Label.SELECTCRITERIA"></spring:message></label></div>
								
								<div class="search_criteria"> <!-- Seacrh criteria starts -->
									<div class="block">
										<ul class="blocklist">
											<li>
												<label for="ddSourceState"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message></label>
												<form:select path="stateNameEnglish" class="combofield"  style="width: 175px" id="ddSourceState" onchange="getDistrictList(this.value);document.getElementById('ddSourceState').selectedIndex==0?doRefresh(true):false;">
										            <form:option selected="selected" value="" label="--select--" /> 
										            <form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
												</form:select> 
												<div id="errProSelect" class="errormsg"></div>
											</li>
											<li>
												<label for="ddSourceDistrict"><spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message></label>
												<form:select path="districtNameEnglish" class="combofield" style="width: 175px" name="ddSourceDistrict" id="ddSourceDistrict" onchange="document.getElementById('ddSourceDistrict').selectedIndex==0?doRefresh(false):false;">
									          		 <form:option selected="selected" value="" label="--select--" />
									           		 <form:options items="${districtList}" itemValue="districtPK.districtCode" itemLabel="districtNameEnglish"></form:options>
									           </form:select> 
											<div id="errProSelectDist" class="errormsg"></div>
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
												<input type="button" name="Submit" class="btn" onclick="getDatabyParent();" value="<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message>" />
												<!-- <input type="button" name="Submit2" class="btn" value=<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message> onclick="doRefresh(true)"/> -->
											    <input type="button" name="Submit3" class="btn"	value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:go('welcome.do');" />
											</li>
										</ul>
									</div>
									<div class="clear"></div>
								</div> <!-- Seacrh criteria ends -->	
															
					</div>
				  </div>
				  
							
				<c:if test="${! empty SEARCH_BLOCK_RESULTS_KEY}">		
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
						
						<c:if test="${! empty message}">
							<h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">
							${message}
							</h6>
						</c:if>
						
							<table name="View Blocks" width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="100%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
												<td width="16%" rowspan="2"><spring:message htmlEscape="true" 	code="Label.BLOCKCODE"></spring:message></td>
												<td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.BLOCKNAMEINENGLISH"></spring:message></td>
												<td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.BLOCKNAMEINLOCAL"></spring:message></td>
												<td  width="14%" align="center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></td>
											</tr>
											
											<tr class="tblRowTitle tblclear">
												<td width="14%" align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></td>
											</tr>
																
									<c:forEach var="listSubDistrictDetail" varStatus="listVillageRow" items="${SEARCH_BLOCK_RESULTS_KEY}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${offsets*limits+listVillageRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${listSubDistrictDetail.villageCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listSubDistrictDetail.villageNameEnglish}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listSubDistrictDetail.villageNameLocal}" escapeXml="true"></c:out></td>
														  <td  width="8%" align="center"><a href="#"><img alt="View Details" src="images/view.png" onclick="javascript:viewBlockDetails('${listSubDistrictDetail.villageCode}');" width="18" height="19" border="0" /></a></td>
												  
													</tr>
													 <input type="hidden" name="blockCode" id="blockCode" value="<c:out value='${listSubDistrictDetail.villageCode}' escapeXml='true'></c:out>"></input> 	
								  </c:forEach>	
								  
							
								  										
										</table>
									</td>
								</tr>
						
						<tr>
							<td height="30" align="right">
									     <table width="301">
											<tr>
												<td width="150" align="right" class="pageno"><c:out value="${blockCount}" escapeXml="true"></c:out></td>
												<td width="96" align="right" class="pre"><a href="#" onclick="setDirection(-1)"><spring:message htmlEscape="true"  code="Label.PREVIOUS"></spring:message></a></td>
												<td width="24" align="center" class="pageno">|</td>
												<td width="51" align="right" class="nxt tblclear"><a href="#" onclick="setDirection(1)"><spring:message htmlEscape="true"  code="Label.NEXT"></spring:message></a></td>
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
								    <label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message>								
						 			<%=df.format(new java.util.Date())%>  </label>
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
															Panchayati Raj</a>, Government of India <br/> Site best
															viewed on Internet Explorer (IE)-8 &amp; above, Mozilla
															Firefox-11 &amp; above 
												</p>			           
				     						 <div id="displayBox" style="text-align: center;display:none;"><img  src="<%= request.getContextPath() %>/images/ajax-loader-2.gif" alt="Loading..."  /></div>
						    
						    			</div> 
						</div>
					</div>
					</c:if>
					
					<div class="buttons center" id="showbutton">
						<input type="button" name="Submit3" class="btn"
												value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
												onclick="javascript:go('welcome.do');"/>
					</div>
					
					<c:if test="${fn:length(viewPage) > 0}"> 
					<c:if test="${empty SEARCH_BLOCK_RESULTS_KEY}">
					<div class="frmpnlbg" id="divData">
							<div class="frmtxt">
								<p class="center"><spring:message htmlEscape="true"  code="error.NOBLCOKFOUND"></spring:message></p>
							</div>
						</div>
					</c:if>
				</c:if> 
			     <input type="hidden" name="direction" id="direction" value="0" />
			     <input type="hidden" name="pageType" value="CT" />		 
			  </form:form>
			   <script src="/LGD/JavaScriptServlet"></script>
			</div>
		</div>
</body>
</html>

