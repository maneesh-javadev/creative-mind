<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script src="/LGD/JavaScriptServlet"></script>
<script	type="text/javascript">
	
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 

$(document).ready(function() {
	
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

function error_remove()
{
	
	//alert("remove");
	document.getElementById("errSelectStateName").innerHTML ="";
	}
	
function manageGlobalDistrict(url,id)
{
	alert("rere");
	dwr.util.setValue('globaldistrictId', id, {	escapeHtml : false	});
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}
	function setDirection(val) 
	{
		document.getElementById('direction').value = val;
		document.forms['form1'].action = "globalviewDistrictPagination.do?<csrf:token uri='globalviewDistrictPagination.do'/>";
		document.forms['form1'].submit();
	}

	function go1(id) {
		var url = "viewdistrictbystatecode.do?id=" + id;
		window.location = url;
	}
	dwr.engine.setActiveReverseAjax(true);

	
	function trim(stringToTrim) {
		return stringToTrim.replace(/^\s+|\s+$/g, "");
	}


		function loadPage() {
			$("#ddSourceState_error").hide();
			document.getElementById('chkcrdistrict').checked = false;
			document.getElementById('chkchdistrict').checked = false;
			if (document.getElementById('text1').value != ''
					|| document.getElementById('text2').value != '') {
				document.getElementById('chkcrdistrict').checked = true;
				document.getElementById('chkchdistrict').checked = false;
			} else if (document.getElementById('ddSourceState').value != 0) {
				document.getElementById('chkcrdistrict').checked = false;
				document.getElementById('chkchdistrict').checked = true;
			}

		}

		function doRefresh() {
			document.getElementById('text1').value = document
					.getElementById('text2').value = '';
			document.getElementById('ddSourceState').selectedIndex = 0;
		}

		function removeSelectedValue(val) {
			var elSel = document.getElementById(val);
			var i;
			for (i = elSel.length - 1; i >= 0; i--)
				elSel.remove(i);
		}

		function validate() {
			var ddSourceState = document.getElementById('ddSourceState');

			if (ddSourceState.selectedIndex < 1) {
				alert('Please select State from dropdown');
				return false;
			}
			return true;
		}

		function getData() {
						
			var distcode=document.getElementById("text1").value;
			var distname=document.getElementById("text2").value;
			//alert("dist"+distcode+distname);
			var code=document.getElementById("captchaAnswer").value;
			if(distcode!="" || distname!="") 
				{
				
				document.getElementById("errInsertDistCodeorDistName").innerHTML= "";	
				if(code == "")
					 {
							//document.getElementById("errorCaptcha").innerHTML = "<font color='red'><br><spring:message code="captcha.errorMessage"/></font>"; 
							document.getElementById('errorCaptcha').style.display='block';
							document.getElementById('errorCaptcha').style.visibility='visible';


							return false;
						}
					else{
						if(document.getElementById('errorCaptcha').style.display=='block' ||   document.getElementById('errorCaptcha').style.visibility=='visible')
						{
							document.getElementById('errorCaptcha').style.display='none';
							document.getElementById('errorCaptcha').style.visibility='hidden';
						}
						}
					document.getElementById('form1').method = "post";
					document.getElementById('form1').action = "globalviewdistrict.do?<csrf:token uri='globalviewdistrict.do'/>";
					document.getElementById('form1')
							.submit();
					//errorCaptcha.innerHTML = "";
					return true;
				
				}
			else
				{

				document.getElementById("errInsertDistCodeorDistName").innerHTML = "<font color='red'>District Code or District Name is Required</font>"; 
				   document.getElementById("errInsertDistCodeorDistName").focus();
				}
			
	
		}
		function getParentData() {
			
			
			var statename=document.getElementById("ddSourceState").value;
		
			 
			//alert ("DROP"+document.getElementById('showbydropdown').style.visibility);
			
			//alert(statename);
			if(statename!="")
				{
				document.getElementById('form1').method = "post";
				document.getElementById('form1').action = "globalviewdistrict.do?<csrf:token uri='globalviewdistrict.do'/>";
				document.getElementById('form1')
						.submit();
				//errorCaptcha.innerHTML = "";
				}
			else
				{
				 document.getElementById("errSelectStateName").innerHTML = "<font color='red'><spring:message code="error.select.SELECTSTATENAME"/></font>"; 
				   document.getElementById("errSelectStateName").focus();
				}
			return true;
	
		}
		/* Retrieve the order details */
		/* function retrieveFile1(districtCode)
			{
			//alert("Inside the retrieveFile123 "+districtCode);
			
			document.getElementById("distCode1").value	=	districtCode;
			//alert("Value of lbCode1 is::::::::::"+document.getElementById("lbCode1").value);
			document.getElementById('form1').action = "globalviewGovtOrderDetailForDist.do?<csrf:token uri='globalviewGovtOrderDetailForDist.do'/>";
			document.getElementById('form1').submit();
		    return true;
			} */
		
			
		function retrieveFile1(districtCode) {
		
			
				lgdDwrStateService.getGovtOrderByEntityCode(parseInt(districtCode),'D', {
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
						
							if ((typeof($.browser.msie) != "undefined")) {
								if($.browser.msie){
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
								}
							} else if((typeof($.browser.mozilla) != "undefined") && ($.browser.mozilla)) {
								newWindowF = window.open('', 'download_page', "width=100px,height=100px");	
								if(newWindowF){
									newWindowF.close();
									form.submit();
								}else{
									alert('You must allow popups for this to work.');
								}
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
		
		
		function viewDistrictDetails(districtCode)
		{
			if(districtCode==null)
				alert("Record not found");
				else
					{

					var form = document.form1;
					var tempTarget = form.target;
					var tempAction = form.action;
					form.target = 'download_page';
					form.method = "post";
					dwr.util.setValue('globaldistrictId', districtCode, {	escapeHtml : false	});
					form.action = "globalviewDistrictDetail.do?<csrf:token uri='globalviewDistrictDetail.do'/>";
				
				
					if ($.browser.msie) {
						p_windowProperties = "width=903px,height=550px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised";
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
			 var obj='<c:out value="${! empty SEARCH_DISTRICT_RESULTS_KEY}" escapeXml="true"></c:out>';
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
		  
			function viewDistrictDetailsforDistrictVersion(districtCode)
			{
				if(districtCode==null)
					alert("Record not found");
					else
						{

						var form = document.form1;
						var tempTarget = form.target;
						var tempAction = form.action;
						form.target = 'download_page';
						form.method = "post";
						dwr.util.setValue('globaldistrictId', districtCode, {	escapeHtml : false	});
						form.action = "viewDistrictHistoryReport.do?<csrf:token uri='viewDistrictHistoryReport.do'/>";
					
					
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

<title><spring:message htmlEscape="true" code="Label.VIEWDIST"></spring:message>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>

<body oncontextmenu="return false"
	onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);" onload="loadPage();">
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message htmlEscape="true" code="Label.VIEWDIST"></spring:message></h3>
			<ul id="showhelp" class="listing">
				<%--//these links are not working <li ><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" alt="Toggle"	border="0" /></a></li>
				<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message	htmlEscape="true" code="Label.HELP"></spring:message> </a></li>		 --%>		
			</ul>
			<a id="showprint" style="visibility: hidden; display: none;" href="#">
				<img src='<%=contextPath%>/images/printer_icon.png' border="0" onclick="CallPrint()" onmouseover="" alt="Print" />
			</a>		
		</div>
		
		<div class="frmpnlbrdr">
			<form:form action="globalviewdistrict.do" id="form1" name="form1" method="POST" commandName="districtbean">
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri='globalviewDistrictDetail.do'/>" />
					<input type="hidden" name="distCode1" id="distCode1" value="" />
					<input type="hidden" name="flag1" id="flag1" value="<c:out value='${flag1}' escapeXml='true'></c:out>" />
					<input type="hidden" name="flag2" id="flag2" value="<c:out value='${flag2}' escapeXml='true'></c:out>"/>
					  <input type="hidden" name="govfilePath" id="govfilePath" />
					<input type="hidden" name="fileDisplayType" id="fileDisplayType" /> 
						<form:input path="globaldistrictId" type="hidden" name="globaldistrictId" id="globaldistrictId"  />	
						<!-- modified by sunita on 28 june 2015 -->
				<c:if test="${ empty SEARCH_DISTRICT_RESULTS_KEY}">
				<div id="cat" >
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"
									code="Label.FILTEROPTFORVIEWDIST"></spring:message>
							</div>
							<ul class="listing">
								<li>
									<label for="chkcrdistrict"><form:radiobutton
															path="correctionRadio" id='chkcrdistrict'
															onclick='toggledisplay3("chkcrdistrict","showbytext");setBack();doRefresh();' />
															<spring:message
															htmlEscape="true" code="Label.SEARCHBYNAME"></spring:message>
									</label>
								</li>								
								<li><input type="hidden" id="sessionId"
													value='<%=sessionId%>'></input></li>
								<li>
									<label for="chkchdistrict"><form:radiobutton
															path="correctionRadio" id='chkchdistrict'
															onclick='toggledisplay3("chkchdistrict","showbydropdown");setBack();doRefresh();' />
															<spring:message
															htmlEscape="true" code="Label.SEARCHBYHIERARCHY"></spring:message>
									</label>
								</li>
								<li><input type="radio" style="display: none" name="correctionRadio" checked="checked"/></li>
								<li>
									<label for="pageRows">
										<spring:message htmlEscape="true" code="Label.NOOFRECORDPERPAGE"></spring:message></label>
									<form:select htmlEscape="true" path="pageRows" class="combofield select_xs">
										<form:option value="25" label="25" />
										<form:option value="30" label="30" />
										<form:option value="35" label="35"/>
										<form:option value="50" label="50" />
										<form:option value="100" label="100" />
									</form:select>
								   
								</li>
							</ul>
														
						</div>
					</div>
				</div>

</c:if>

				<div class="frmpnlbg" id='showbytext' style="visibility: hidden; display: none">
					<div class="frmtxt" id = "searchid">
						<div class="frmhdtitle" >
							<spring:message htmlEscape="true" code="Label.SEARCHCRITERIA"></spring:message>
						</div>
						
						<div class="search_criteria">
							<div class="block">
								<ul class="blocklist">
									<li><label for="text1"><spring:message htmlEscape="true" code="Label.DISTRICTCODE"></spring:message></label>
										<form:input type="text" class="frmfield" id="text1" onkeypress="validateNumericKeys(event)" path="code" /> 
									</li>
									<li>
										<label for="text2"><spring:message htmlEscape="true" code="Label.DISTRICTNAMEINENGLISH"></spring:message></label>
										<form:input type="text" class="frmfield" id="text2" onkeypress="validateCharKeys(event)" path="districtNameEnglish" />										
									</li>
									<li><div id="errInsertDistCodeorDistName" class="errormsg"></div></li>
								</ul>
							</div>
							<div class="block">
								<ul class="captcha_fields">
									<li><img src="captchaImage" alt="Captcha Image" /></li>
									<li>
										<label for="captchaAnswer"><spring:message code="captcha.message" htmlEscape="true"/></label><br/>
										<form:input path="captchaAnswer" name="captchaAnswer" id="captchaAnswer" class="frmfield"  autocomplete="off" />
										<div class="errormsg">										
											<c:if test="${ captchaSuccess1 == false }">
												<spring:message code="captcha.errorMessage" htmlEscape="true"/>
											</c:if>																	
										</div>
										<div id="errorCaptcha" style="color: red;visibility: hidden; display: none;"><spring:message code="captcha.errorMessage"/></div>
									</li>
									<li class="buttons">
										<input type="button" name="Submit" class="btn" onclick="getData();" value="<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message>" />
										<!-- <label> <input type="button" name="Submit2" class="btn" onclick="doRefresh();" value=<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>  /> </label> -->
										<input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:go('welcome.do');" />
									</li>
								</ul>								
							</div>
							
							<div class="clear"></div>
							
						</div>

				</div>
			</div>	
							
				<div class="frmpnlbg" id='showbydropdown' style="visibility: hidden; display: none">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message>
						</div>
						<div class="search_criteria">
							<div class="block">
								<ul class="blocklist">
									<li>
										<label for="ddSourceState"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message></label>
										<form:select path="stateNameEnglish" class="combofield"
											style="width: 175px" id="ddSourceState" onchange="error_remove();">
											<form:option value="">
												<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
											</form:option>
											<form:options items="${stateSourceList}"
												itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
										</form:select>
										<div id="errSelectStateName" class="errormsg"></div>
									</li>
								</ul>
							</div>
							<div class="block">
								<ul class="captcha_fields">								
								<li>
									<img src="captchaImage" alt="Captcha Image" />
								</li>
								<li>
									<label for="captcha_field"><spring:message code="captcha.message" htmlEscape="true" /></label><br /> 
									<form:input path="captchaAnswers" name="captchaAnswesr" id="captcha_field"	class="frmfield" autocomplete="off" />
									<div class="errormsg">
										<c:if test="${ captchaSuccess2 == false }">
											<spring:message code="captcha.errorMessage" htmlEscape="true" />
										</c:if>
									</div>
								</li>
								<li>
									<input type="button" name="Submit" class="btn" onclick="getParentData();" value="<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message>" />
								    <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
										onclick="javascript:go('welcome.do');" />
								</li>
							</ul>
							</div>
							
							<div class="clear"></div>
							
						</div>
						
					</div>
				</div>




               
				<c:if test="${! empty SEARCH_DISTRICT_RESULTS_KEY}">
				
				 <div id="printable"> <!-- Printable div starts -->
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
						<c:if test="${! empty message}">
							<table width="100%" class="tbl_no_brdr" align="center">
							<tr>
							<th align="center" width="80%"><label style="font-size:18px; font-weight:bold;margion:0"><c:out value="${message}" escapeXml="true"></c:out></label> </th>
							</tr>
							</table>
						</c:if>
							<table width="100%" class="tbl_no_brdr">

								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="100%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="2%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.SNO"></spring:message></td>
												<td width="7%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.DISTRICTCODE"></spring:message></td>
												<td width="17%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.DISTRICTNAMEINENGLISH"></spring:message></td>
												<td width="17%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.DISTRICTNAMEINLOCAL"></spring:message></td>
												<!-- district short name column add in district by Sunita 23-06-2015 -->
												<td width="11%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.SHORTNAMEOFDISTRICT"></spring:message></td>
												<td width="5%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.CENSUS2001"></spring:message></td>
												<td width="5%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.CENSUSCODE2011"></spring:message></td>
												<!-- is_pesa column add in district view by Maneesh 18Dec0214 -->
												<td width="7%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.PESA_STATUS" text="Pesa Status"></spring:message></td>
												<!-- is_pesa column add in district view by Maneesh 18Dec0214 -->
												<%-- <td width="14" align="center"><spring:message
														htmlEscape="true" code="Label.ACTION"></spring:message></td>
												<td width="14%" align="center"><spring:message
														htmlEscape="true" code="Label.ACTION"></spring:message></td> --%>
												<td width="7%" align="center" rowspan="2"><spring:message
														htmlEscape="true" code="Label.VIEW.DETAIL"></spring:message></td>
												<td width="7%" align="center" rowspan="2"><spring:message
														htmlEscape="true"   text="View History"></spring:message></td>	
												<td width="5%" align="center" rowspan="2"><spring:message
														htmlEscape="true" code="Label.VIEW.GOVERNMENTORDER"></spring:message></td>
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.VIEW.MAP"></spring:message></td>
											</tr>
											<tr class="tblRowTitle tblclear">

												<%-- <td width="14%" align="center"><spring:message
														htmlEscape="true" code="Label.VIEW"></spring:message></td>
												<td width="7%" align="center"><spring:message
														htmlEscape="true" code="Label.VIEW.GOVERNMENTORDER"></spring:message>
										</td> --%>
											</tr>
											<c:forEach var="listDistrictDetails"
												varStatus="listVillageRow"
												items="${SEARCH_DISTRICT_RESULTS_KEY}">
												<tr class="tblRowB">
													<td width="3%"><c:out value="${offsets*limits+listVillageRow.index+1}" escapeXml="true"></c:out></td>
													<td><c:out value="${listDistrictDetails.villageCode}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${listDistrictDetails.villageNameEnglish}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${listDistrictDetails.villageNameLocal}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${listDistrictDetails.villageNameShort}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${listDistrictDetails.census2001Code}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${listDistrictDetails.census2011Code}" escapeXml="true"></c:out></td>
													<!-- is_pesa column add in district view by Maneesh 18Dec0214 -->
													<td align="left">
														<c:choose>
														 <c:when test="${listDistrictDetails.isPesa eq 'P'}">
															 <spring:message htmlEscape="true" code="Label.PESA_PARTIALLY_COVERED" text="Partially Covered"/>
														 </c:when>
														  <c:when test="${listDistrictDetails.isPesa eq 'F'}">
														  	<spring:message htmlEscape="true" code="Label.PESA_FULLY_COVERED" text="Fully Covered"/>
														 </c:when>
														 <c:otherwise>
														  	<spring:message htmlEscape="true" code="Label.PESA_NOT_COVERED" text="Not Covered"/>
														 </c:otherwise>
														</c:choose>
													</td>
													<!-- is_pesa column add in district view by Maneesh 18Dec0214 -->
													<td  width="7%" align="center"><a href="#"><img src="images/view.png" onclick="javascript:viewDistrictDetails('${listDistrictDetails.villageCode}');" width="18" height="19" border="0" alt="View Details" /></a></td>
                        							<td width="7%" align="center"><a href="#"><img src="images/history.png"
																							onclick="javascript:viewDistrictDetailsforDistrictVersion('${listDistrictDetails.villageCode}');"
																							width="18" height="19" border="0" alt="View Details" /> </a></td>
                        							
                        							<td width="9%" align="center">
                        							 <c:if test="${listDistrictDetails.fileName ne ''}"> 
                        							 <a href="#">                        							
                        							<img
														src="images/gvt.order.png"
														onclick="javascript:retrieveFile1('${listDistrictDetails.villageCode}');"
														width="18" height="19" border="0" alt="View Details" />
													</a>  
													</c:if> 
													</td>
													
													<td width="3%" align="center">
														<%-- <img src="images/showMap.jpg" onclick="javascript:displayMap('${listDistrictDetails.census2011Code}');" width="18" height="19" border="0" /> --%>
														<c:if test="${listDistrictDetails.mapupload != null}">
	                        								<c:choose>
																<c:when test="${!listDistrictDetails.mapupload}">
																	<img alt="Map" src="images/showMap.jpg" onclick="javascript:displayMap('${listDistrictDetails.census2011Code}');" width="18" height="19" border="0" />
																</c:when>
																<c:when test="${listDistrictDetails.mapupload}">
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
								
								<tr>
									<td height="30" align="right"><table width="30%" class="tbl_no_brdr">
											<tr>
												<td width="150" align="right" class="pageno"><c:out value="${villageCount}" escapeXml="true"></c:out></td>
												<td width="96" align="right" class="pre"><a href="#"
													onclick="setDirection(-1)"><spring:message
															htmlEscape="true" code="Label.PREVIOUS"></spring:message>
												</a></td>
												<td width="24" align="center" class="pageno">|</td>
												<td width="51" align="right" class="nxt tblclear"><a
													href="#" onclick="setDirection(1)"><spring:message
															htmlEscape="true" code="Label.NEXT"></spring:message> </a>
												</td>
												<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr>
											
							</table>
							
							<ul class="blocklist center" style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
								<li><label><spring:message	code="Label.URL" htmlEscape="true"></spring:message>
									<%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%> </label>
								</li>
				
								<li><%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%>
									<label><spring:message
												code="Label.REPORTPRINTED" htmlEscape="true"></spring:message>&nbsp;
												
										 <%=df.format(new java.util.Date())%>  </label>
							    </li>
						 		<li><label><spring:message	code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label></li>
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
				     						 <div id="displayBox" style="text-align: center;display:none;"><img  src="<%= request.getContextPath() %>/images/ajax-loader-2.gif" alt="Loading"  /></div>
						    
			    			</div> 
						    			
							</div>
						</div>
					</div>  <!-- Printable div ends -->
					</c:if>
					
					</div>
					
					
				<c:if test="${fn:length(viewPage) > 0}">
					<c:if test="${empty SEARCH_DISTRICT_RESULTS_KEY}">
						<div class="frmpnlbg" id="divData">
							<div class="frmtxt center">
							<p class="center"><spring:message	htmlEscape="true" code="error.NODISTFOUND"></spring:message></p>							
							</div>
						</div>
					</c:if>
				</c:if>
				
				<div class="buttons center" id="showbutton">
					<input type="button" name="Submit" class="btn" value="<spring:message htmlEscape="true"  code="Button.BACK"></spring:message>"
												onclick="javascript:go('globalviewdistrictforcitizen.do?');"/>
					<input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
												onclick="javascript:go('welcome.do');"/>
				</div>
				
				<input type="hidden" name="direction" id="direction" value="0" />
				<input type="hidden" name="pageType" value="CD" />
				
			</form:form>
			<c:if test="${saveMsg != null}">
				<script>
					alert("<c:out value="${saveMsg}"/>");
				</script>
			</c:if>
			<script src="/LGD/JavaScriptServlet"></script>
		
	</div>
	<script language="javascript" type="text/javascript">
		 function displayMap (dtCode) {
			openGISModal(dtCode, 2, "L", null, null);
		 }
	</script>	
</body>
</html>

