<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%@include file="../common/taglib_includes.jsp"%>
<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<script src="js/lgd_localbody.js"></script>
<script src="js/validationWard.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script type="text/javascript" src="js/view_localbody.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/call/plaincall/lgdDwrlocalBodyService.getchildlocalbodiesByParentcode.dwr'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDesignationService.js'></script>
<%@include file="../common/dwr.jsp"%>
<script language="javascript">
	function CallPrint() {
		document.getElementById('footer').style.display = 'block';
		document.getElementById('footer').style.visibility = 'visible';
		var printContent = document.getElementById("divData");
		var Win4Print = window.open('', '', 'left=0,top=0,width=500,height=500,top=301,left=365,resizable=1,status=0,toolbar=0');
		Win4Print.document.write(printContent.innerHTML);
		Win4Print.document.close();
		Win4Print.focus();
		Win4Print.print();
		Win4Print.close();
		document.getElementById('footer').style.display = 'none';
		document.getElementById('footer').style.visibility = 'hidden';
	}

	function toggledisplayDivs(obj, val) {
		document.getElementById("errProSelectDist").innerHTML= "";
		document.getElementById("errorCaptchaMessage").innerHTML= "";
		if (document.getElementById('divData') != null)
			document.getElementById('divData').style.visibility = 'hidden';
	
		//document.getElementById('captchaAnswer').value="";
		//document.getElementById('captchaAnswers').value="";
		
			if (val == 'showbydropdown') {
				document.getElementById('cat').style.visibility = 'visible';
				document.getElementById('cat').style.display = 'block';
				document.getElementById('viewentity').style.display = 'block';
				document.getElementById('viewentity').style.visibility = 'visible';
				document.getElementById('showbytext').style.visibility = 'hidden';
				document.getElementById('showbytext').style.display = 'none';

			} else if (val == 'showbytext') {
				document.getElementById('showbytext').style.visibility = 'visible';
				document.getElementById('showbytext').style.display = 'block';
				document.getElementById('cat').style.visibility = 'hidden';
				document.getElementById('cat').style.display = 'none';
			}

		

	}
	function setdefaultData()
	
	{	$('#gtadp1').hide();
		$('#gtachildtr').hide();
		document.getElementById('state').selectedIndex=0;
		document.getElementById("errProSelectDist").innerHTML= "";
		var val = document.getElementById("captchastatus").value;
		document.getElementById("errorCaptchaMessage").innerHTML= "";
		if(val==2)
			{
			document.getElementById('showbytext').style.visibility = 'visible';
			document.getElementById('showbytext').style.display = 'block';
			document.getElementById('cat').style.visibility = 'hidden';
			document.getElementById('cat').style.display = 'none';
			}
		else if(val==1)
		{
			document.getElementById('cat').style.visibility = 'visible';
			document.getElementById('cat').style.display = 'block';
			document.getElementById('viewentity').style.display = 'block';
			document.getElementById('viewentity').style.visibility = 'visible';
			document.getElementById('showbytext').style.visibility = 'hidden';
			document.getElementById('showbytext').style.display = 'none';
			}
		else
			{
			document.getElementById('text1').value="";
			document.getElementById('text2').value="";
			}
		
	}
	function checkLength()
	{
		var len= document.getElementById("text2").value;
		if(len.length<3)
			{
			alert("Enter Atleast Three Characters");
			document.getElementById("text2").value="";
			document.getElementById("text2").value.focus();
			return false;
			}
			
		
	}
	function validateLBViewForm()
	{
		
		var flag = true;
		var len= document.getElementById("text1").value;
		var name= document.getElementById("text2").value;
		var captchalen= document.getElementById("captchaAnswers").value;
		len=len.length+name.length;
		
		if(len==0)
			{
			document.getElementById("errProSelectDist").innerHTML = "<font color='red'><spring:message code="Error.NoLBCodeNameEntered"/></font>";
			flag=false;
			}else if(name.length>0 && name.length<3){
				$("#errProSelectDist").html("Enter Atleast Three Characters");
				$("#text2").focus();
				flag=false;
			}else{
				$("#errProSelectDist").html("");
			}
		
		if(captchalen.length==0)
		{
		document.getElementById("errorCaptchaMessage").innerHTML = "<font color='red'><spring:message code="captcha.errorMessage"/></font>";
		flag=false;
		}
		return flag;
			
	}
	function ClearAll() {
		var obj = '<c:out value="${! empty lgtLocalBodyType}" escapeXml="true"></c:out>';
		var obj1 = '<c:out value="${! empty lgtLocalBody}" escapeXml="true"></c:out>';
		if (obj == "true" || obj1 == "true") {
			document.getElementById('viewentity').style.display = 'none';
			document.getElementById('viewentity').style.visibility = 'hidden';
			document.getElementById('showhelp').style.display = 'none';
			document.getElementById('showhelp').style.visibility = 'hidden';
			document.getElementById('showprint').style.display = 'block';
			document.getElementById('showprint').style.visibility = 'visible';
			document.getElementById('showbutton').style.display = 'block';
			document.getElementById('showbutton').style.visibility = 'visible';
		} else {
			document.getElementById('viewentity').style.display = 'block';
			document.getElementById('viewentity').style.visibility = 'visible';
			document.getElementById('showhelp').style.display = 'block';
			document.getElementById('showhelp').style.visibility = 'visible';
			document.getElementById('showprint').style.display = 'none';
			document.getElementById('showprint').style.visibility = 'hidden';
			document.getElementById('showbutton').style.display = 'none';
			document.getElementById('showbutton').style.visibility = 'hidden';

		}
	}
	if (window.addEventListener) {
		window.addEventListener("load", ClearAll, false);
	} else if (window.attachEvent) {
		window.attachEvent("onload", ClearAll);
	} else if (window.onLoad) {
		window.onload = ClearAll;
	}
	
	
	
	
	
	
	
	
	function showgtaandlb(value) {
	
		var e = document.getElementById("ddLgdLBType");
		var strUser = e.options[e.selectedIndex].text;
		var statevalue = $('#state').val();

		lgdDwrlocalBodyService.getStateTopHierarchy(statevalue, {
			callback : getStateTopHierarchySuccess,
			errorHandler : handlegetStateTopHierarchyError
		});

	}

	function getStateTopHierarchySuccess(data) {
		var fieldId = 'gtadp';
		var valueText = 'localBodyTypeCode';
		var nameText = 'localBodyName';
		dwr.util.removeAllOptions(fieldId);
		var dataq = [ {
			id : "",
			select : "Select"
		} ];
		dwr.util.addOptions(fieldId, dataq, "id", "select");
		dwr.util.addOptions(fieldId, data, valueText, nameText);
	}

	function handlegetStateTopHierarchyError() {
		// Show a popup message
		alert("No data found hello!");
	}

		function getParentLb(value) {
		
		var a = document.getElementById("gtadp");
		var parentlist = a.options[a.selectedIndex].text;
		//alert(parentlist);
		
		var statevalue = $('#state').val();
		if (value == 20) {
			$('#tr_village_dist').hide();
			$('#tr_village_inter').hide();
			//$('#gtaanddp').show();
			$('#gtachildtr').show();
			lgdDwrlocalBodyService.getPanchayatListbylblcCode(statevalue, value, {
				callback : getParentLbSuccess,
				errorHandler : handledisPanchayatError
			});
		}
		if (value == 1) {
			//$('#tr_village_dist').show();
			//$('#tr_village_inter').show();
			value=$('#ddLgdLBType').val();
			$('#gtachildtr').hide();
			hideShowDivforWard(value, '${districtCode}', '${lbType}');
			
		}
	}

	function getParentLbSuccess(data) {
		$('#gtaanddp').show();
		var fieldId = 'gtachild';
		var valueText = 'localBodyCode';
		var nameText = 'localBodyNameEnglish';

		dwr.util.removeAllOptions(fieldId);
		var st = document.getElementById(fieldId);
		st.add(new Option('Select', '0'));
		dwr.util.addOptions(fieldId, data, valueText, nameText);

	}

	function handledisPanchayatError() {
		// Show a popup message
		alert("No data found!");
	}

	
	function redirectFunction(value) {
		//var statevalue = $('#gtadp').val();
		//alert(statevalue);
		var e = document.getElementById("ddLgdLBType");
		var strUser = e.options[e.selectedIndex].text;
		var statevalue = $('#state').val();

		if (statevalue == 19) {
			if (strUser == 'Village Panchayat') {
				//$('#tr_village_dist').hide();
				//$('#tr_village_inter').hide();
				$('#gtadp1').show();
				//$('#gtachildtr').show();
				showgtaandlb(value);
			}else {
				$('#gtadp1').hide();
				$('#gtachildtr').hide();
		 		hideShowDivforWard(value, '${districtCode}', '${lbType}');

			}

		}else {
			$('#gtadp1').hide();
			$('#gtachildtr').hide();
	 		hideShowDivforWard(value, '${districtCode}', '${lbType}');

		}

	}
	
	
	function viewLocalBodyDetailsNew(isState, code) {
		if (code == null)
			alert("Record not found");
		else {

			var form = document.form2;
			var tempTarget = form.target;
			var tempAction = form.action;
			form.target = 'download_page';
			form.method = "post";
			dwr.util.setValue('isState', isState, {
				escapeHtml : false
			});
			dwr.util.setValue('code', code, {
				escapeHtml : false
			});

			form.action = "viewEntityDetail.do?<csrf:token uri='viewEntityDetail.do'/>";

			if ($.browser.msie) {
				p_windowProperties = "width=999px,height=650px, left=200, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised";
				newWindow = window.open('', 'download_page', p_windowProperties);
				if (newWindow) {
					form.submit();
					form.target = tempTarget;
					form.action = tempAction;
					/* newWindow.focus(); */
				} else {
					alert('You must allow popups for this to work.');
				}
			} else if ($.browser.mozilla) {
				NewWindow = window
						.open('', "download_page",
								"width=999px,height=650px, left=200, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=yes, modal=yes, edge=raised");
				form.submit();
			}

			else {
				NewWindow = window
						.open('', "download_page",
								"width=903px,height=527px, left=270, top=200, status=no,title=no,help=no, toolbar=no, resizable=yes, scrollbars=no, modal=yes, edge=raised");
				form.submit();
			}
		}
	}
	
	function viewDetailsforlocalbody(localbodyCode)
	{
		if(localbodyCode==null)
			alert("Record not found");
			else
				{

				var form = document.form1;
				var tempTarget = form.target;
				var tempAction = form.action;
				form.target = 'download_page';
				form.method = "post";
				dwr.util.setValue('globallocalbodyId', localbodyCode, {	escapeHtml : false	});
				form.action = "viewLocalBodyHistoryReports.do?<csrf:token uri='viewLocalBodyHistoryReports.do'/>";
			
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
<body onload="setdefaultData();">
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message code="Label.VIEWLOCALBODY" htmlEscape="true"></spring:message></h3>
			<ul id="showhelp" class="listing">
				<li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg"	data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a></li>
<%-- 				<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></li> --%>
			</ul>
			<a id="showprint" style="visibility: hidden; display: none;" href="#"><img src='<%=contextPath%>/images/printer_icon.png' border="0"	onclick="CallPrint()" onmouseover="" style="cursor: pointer;" /></a>			
		</div>
		
		<div class="frmpnlbrdr">
			<form:form action="globalviewlocalbody.do" method="POST"
				commandName="localGovtBodyForm" enctype="multipart/form-data"
				id="viewWardForm" name="form1">
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="createWardforPRI.htm"/>" />
				<input type="hidden" name="govfilePath" id="govfilePath" />
				<input type="hidden" name="fileDisplayType" id="fileDisplayType" />
					<input type="hidden" name="captchastatus" id="captchastatus" value="<c:out value='${flag1}' escapeXml='true'></c:out>"/>
			
				<form:input path="globallocalbodyId" type="hidden"	name="globallocalbodyId" id="globallocalbodyId" />
					
				
				
				<div id="showdivoption">
					<div class="frmpnlbg">
						<div class="frmtxt"> 
							<div class="frmhdtitle"><spring:message htmlEscape="true" code="Label.FilterLBOption"></spring:message></div>
							<ul class="listing">
								<li>
									<label for="chkcrdistrict">
									<input type="radio"  id="chkcrdistrict"  onclick='toggledisplayDivs("chkcrdistrict","showbytext");doRefresh();' name="correctionRadio" checked="checked" />
									<%-- <form:radiobutton path="" id='chkcrdistrict' onclick='toggledisplayDivs("chkcrdistrict","showbytext");doRefresh();'  /> --%>
										   <spring:message htmlEscape="true" code="Label.SEARCHBYNAME"></spring:message>
									</label>
								</li>
								<li>
									<label for="chkchdistrict">
										<input type="radio"  id="chkcrdistrict"  onclick='toggledisplayDivs("chkcrdistrict","showbydropdown");doRefresh();' name="correctionRadio"   />
										<%-- <form:radiobutton path="correctionRadio" id='chkchdistrict' onclick='toggledisplayDivs("chkchdistrict","showbydropdown");doRefresh();' /> --%>
										<spring:message	htmlEscape="true" code="Label.SEARCHBYHIERARCHY"></spring:message>
									</label>
								</li>
								
							</ul>
						</div>
					</div>
				</div>


				<div id="cat" style="visibility: hidden; display: none;">
					<div class="frmpnlbg" id="viewentity">
						<div class="frmtxt">
							<div class="frmhdtitle"><spring:message code="Label.SELECTLOCALBODY" htmlEscape="true"></spring:message></div>
							
							<div class="search_criteria"> <!-- Seacrh criteria starts -->
								<div class="block">
									<ul class="blocklist">
										<li>
											<label for="state"><spring:message code="Label.SELECTSTATE" htmlEscape="true"></spring:message></label>
											 <form:select
														path="stateNameEnglish" class="combofield"
														style="width: 175px" id="state"
														onchange="getLocalBodyList(this.value);">
														<form:option value="">
															<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
														</form:option>
														<form:options items="${stateList}"
															itemValue="statePK.stateCode"
															itemLabel="stateNameEnglish"></form:options>
											</form:select>
											<div id="errSelectState" class="errormsg"></div>
										</li>
										<li>
											<input type="hidden" id="cattype" />
											<input type="hidden" id="level" /> <input type="hidden"
											name="flagCode" id="flagCode" /> <input type="hidden"
											name="lblc" id="lblc" /> 
											<label for="ddLgdLBType"><spring:message
													code="Label.SELECTLOCALBODYTYPE" htmlEscape="true">
												</spring:message><span class="mndt">*</span>
											</label>
											<form:select
												path="lgd_LBTypeName" id="ddLgdLBType"
												htmlEscape="true"
												onfocus="validateOnFocus('ddLgdLBType');helpMessage(this,'ddLgdLBTypeMsg');"
												onchange="redirectFunction(this.value);"
												onkeydown="validateOnKeyPessDown(event,'ddLgdLBType','char')"
												onblur="vlidateOnblur('ddLgdLBType','1','15','c');hideHelp();"
												class="combofield" style="width: 175px">
												<form:option selected="selected" value="" label="--select--"></form:option>
											</form:select>
											<div id="errSelectLocalBodyType" class="errormsg"></div>
										</li>
										<li id="gtadp1"> <!-- gta and district panchyat list -->
											<input type="hidden" id="gtaanddp" />
											<input type="hidden" id="gtadp2" /> <input type="hidden"name="flagCode" id="flagCode" /> <input type="hidden" name="" id="lblc" />
											<label for="gtadp"><spring:message code="" text="Parent Type" htmlEscape="true"></spring:message><span class="errormsg">*</span></label>
											<form:select path="parentList" 
											id="gtadp" 
											htmlEscape="true" 
											onfocus=";helpMessage(this,'ddLgdLBTypeMsg');" 
											onchange="getParentLb(this.value);" 
											onkeydown="" onblur="hideHelp();" 
											class="combofield" 
											style="width: 175px">
												<form:option value=""><form:option selected="selected" value="" label="--select--" /></form:option>
											</form:select>
											<div id="errParentType" class="errormsg"></div>
										</li>
										<li id="gtachildtr"> <!-- gta panchyat list	  -->
											<input type="hidden" id="gtaanddp1" />
											<input type="hidden" id="gtadp2" /> <input type="hidden"name="flagCode" id="flagCode" /> <input type="hidden" name="" id="lblc" />
											<label for="gtachild"><spring:message code="" text="Select Gta" htmlEscape="true"></spring:message><span class="mndt">*</span></label>
											<form:select path="gtaList"  id="gtachild" htmlEscape="true" onfocus=";helpMessage(this,'ddLgdLBTypeMsg');" onchange="" onkeydown="" onblur="hideHelp();" class="combofield" style="width: 175px">
												<form:option value=""><form:option selected="selected" value="" label="--select--" /></form:option>
											</form:select>
											<div id="errSelectGta" class="errormsg"></div>	
										</li>
										<li>
											<div id="tr_district2" style="visibility: hidden; display: none;">
												<label for="wardUrbanLocalBody"><spring:message code="Label.SELECT" htmlEscape="true"><span id="urbanlevel"></span><span class="mndt">*</span></spring:message></label>
										 		<form:select
												htmlEscape="true" path="lb_lgdPanchayatName"
												class="combofield" id="wardUrbanLocalBody"
												style="width: 175px"
												onfocus="validateOnFocus('wardUrbanLocalBody');helpMessage(this,'wardUrbanLocalBodyMsg');"
												onchange="remove_error(4);getCovereSubDistUrbanExWardList(this.value)"
												onkeydown="validateOnKeyPessDown(event,'wardUrbanLocalBody','char')"
												onblur="vlidateOnblur('wardUrbanLocalBody','1','15','c');hideHelp();">
													<form:option value="0">
														<spring:message code="Label.SELECTLOCALBODY"></spring:message>
													</form:option>
												</form:select>
												<div id="errSelectUrban" class="errormsg" style="visibility: hidden; display: none;"></div>
												<div id="wardUrbanLocalBody_error" class="errormsg"><spring:message code="error.blank.TEHSILP"	htmlEscape="true"></spring:message></div>
												<div id="wardUrbanLocalBodyMsg" class="errormsg" style="display: none"><spring:message code="error.blank.TEHSILP" htmlEscape="true" /></div>
												<div class="errormsg" id="wardUrbanLocalBody_error1">
													<form:errors path="lb_lgdPanchayatName"
														htmlEscape="true" />
												</div>
												<div class="errormsg" id="wardUrbanLocalBody_error2" style="display: none"></div>
											</div>
										</li>
										<li>
											<div id="tr_village_dist" style="visibility: hidden; display: none;">												
												<label for="ddLgdLBDistListAtVillageCA"><spring:message code="Label.SELECT" htmlEscape="true"><span id="firstlevel"></span><span	class="mndt">*</span></spring:message></label>
													<form:select
														path="lgd_LBDistListAtVillageCA" class="combofield"
														htmlEscape="true" style="width: 175px"
														id="ddLgdLBDistListAtVillageCA"
														onfocus="validateOnFocus('ddLgdLBDistListAtVillageCA');helpMessage(this,'ddLgdLBDistListAtVillageCAMsg');"
														onchange="remove_error(3);callList(this.value);"
														onkeydown="validateOnKeyPessDown(event,'ddLgdLBDistListAtVillageCA','char')"
														onblur="vlidateOnblur('ddLgdLBDistListAtVillageCA','1','15','c');hideHelp();">
														<form:option value=""><form:option selected="selected" value="" label="--select--" /></form:option>
														<form:options items="${districtPanchayatList}"
															itemLabel="localBodyNameEnglish"
															itemValue="localBodyCode" />
													</form:select>
													<div id="errSelectVIP" class="errormsg" style="visibility: hidden; display: none;"></div>
													<div id="ddLgdLBDistListAtVillageCA_error" class="errormsg">
														<spring:message
															code="error.SOURCESELECTLOCALBODYPARENT"
															htmlEscape="true"></spring:message>
													</div>
													<div id="ddLgdLBDistListAtVillageCAMsg" class="errormsg" style="display: none">
														<spring:message
															code="error.SOURCESELECTLOCALBODYPARENT"
															htmlEscape="true" />
													</div>
													<div class="errormsg" id="ddLgdLBDistListAtVillageCA_error1">
														<form:errors path="lgd_LBDistListAtVillageCA"
															htmlEscape="true" />
													</div>
													<div class="errormsg" id="ddLgdLBDistListAtVillageCA_error2" style="display: none"></div>
											</div>
										</li>
										<li>
											<div id="tr_village_inter" style="visibility: hidden; display: none;">
												<label for="ddLgdLBInterListAtVillageCA"><spring:message code="Label.SELECT" htmlEscape="true"><span id="secondlevel"></span><span class="mndt">*</span></spring:message></label>
													    <form:select
															path="lgd_LBInterListAtVillageCA" class="combofield"
															id="ddLgdLBInterListAtVillageCA" htmlEscape="true"
															style="width: 175px"
															onfocus="validateOnFocus('ddLgdLBInterListAtVillageCA');helpMessage(this,'ddLgdLBInterListAtVillageCAMsg');"
															onchange="remove_error(3);getLGBforCoveredVillageListExWard(this.value);"
															onkeydown="validateOnKeyPessDown(event,'ddLgdLBInterListAtVillageCA','char')"
															onblur="vlidateOnblur('ddLgdLBInterListAtVillageCA','1','15','c');hideHelp();">	
															<form:option value=""><form:option selected="selected" value="" label="--select--" /></form:option>
															</form:select>															
														<div id="errSelectVDP" class="errormsg" style="visibility: hidden; display: none;"></div>
														<div id="ddLgdLBInterListAtVillageCAMsg" class="errormsg" style="display: none">
															<spring:message code="error.blank.INTERMEDIATEP"
																htmlEscape="true" />
														</div>
														<div class="errormsg" id="ddLgdLBInterListAtVillageCA_error1">
															<form:errors path="lgd_LBInterListAtVillageCA"
																htmlEscape="true" />
														</div>
														<div class="errormsg" id="ddLgdLBInterListAtVillageCA_error2" style="display: none"></div>
											</div>
										</li>
										<li>
											<div id="tr_village_pan" style="visibility: hidden; display: none;">
													<label for="ddLgdLBVillageSourceAtVillageCA"><spring:message code="Label.SELECT" htmlEscape="true"><span id="thirdlevel"></span><span class="mndt">*</span></spring:message></label>
															<form:select
															path="lgd_LBVillageSourceAtVillageCA"
															class="combofield" style="width: 175px"
															htmlEscape="true"
															id="ddLgdLBVillageSourceAtVillageCA"
															onchange="remove_error();getLGBforCoveredVillageListExWard(this.value);"
															onfocus="validateOnFocus('ddLgdLBVillageSourceAtVillageCA');helpMessage(this,'ddLgdLBVillageSourceAtVillageCAMsg');"
															onkeydown="validateOnKeyPessDown(event,'ddLgdLBVillageSourceAtVillageCA','char')"
															onblur="vlidateOnblur('ddLgdLBVillageSourceAtVillageCA','1','15','c');hideHelp();">
															<form:option selected="selected" value="" label="--select--" />
															</form:select>
														<div class="errormsg" id="errSelectVVP" style="visibility: hidden; display: none;"></div>
														<div class="errormsg" id="ddLgdLBVillageSourceAtVillageCA_error" class="error">
															<spring:message code="error.blank.VILLAGEP"
																htmlEscape="true"></spring:message>
														</div>
														<div class="errormsg" id="ddLgdLBVillageSourceAtVillageCAMsg" style="display: none">
															<spring:message code="error.blank.VILLAGEP"
																htmlEscape="true" />
														</div>
														<div class="errormsg"
															id="ddLgdLBVillageSourceAtVillageCA_error1">
															<form:errors path="lgd_LBVillageSourceAtVillageCA"
																htmlEscape="true" />
														</div>
														<div class="errormsg" id="ddLgdLBVillageSourceAtVillageCA_error2" style="display: none"></div>
											</div>
										</li>										
									</ul>
								</div>
								<div class="block">
									<ul class="captcha_fields">
										<li>
											<img src="captchaImage" alt="Captcha Image" />
										</li>
										<li>
											<label for="captchaAnswer"><spring:message code="captcha.message" htmlEscape="true" /></label><br /> 
											<form:input path="captchaAnswer" class="frmfield" name="captchaAnswer" id="captchaAnswer" /> 
												<div class="errormsg">
													<c:if test="${ captchaSuccess1 == false }">
														<spring:message code="captcha.errorMessage"
															htmlEscape="true" />
													</c:if>
													<form:errors path="captchaAnswer" cssStyle="color:red" />													
												</div>
												<div class="errormsg" id="errorCaptcha" style="visibility: hidden; display: none;">
													<spring:message code="captcha.errorMessage" />
												</div>
										</li>
										<li>
											<input type="submit" name="get" id="btnGet" class="btn" value="<spring:message code='Button.GET' htmlEscape='true'></spring:message>"
											onclick="return validatePRIWardAll();" />
											<input type="button" class="btn" name="Close" value=<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>
											id="btnCancel" onclick="javascript:go('welcome.do');" />
										</li>
									</ul>
								</div>
								<div class="block">
									<input type="hidden" name="stateCode" value="<c:out value='${stateCode}' escapeXml='true'></c:out>"/>
									<input type="hidden" name="districtCode" value="<c:out value='${districtCode}' escapeXml='true'></c:out>"/>
									<input type="hidden" value="<c:out value='${lbType}' escapeXml='true'></c:out>"/>
								</div>
								<div class="clear"></div>
								
							</div>
									
						</div>
					</div>
				</div>

				
				<div class="frmpnlbg" id='showbytext' style="visibility: visible; display: block">
					<div class="frmtxt" id = "searchid">
						<div class="frmhdtitle" >
							<spring:message htmlEscape="true" code="Label.SEARCHCRITERIA"></spring:message>
						</div>
						
						<div class="search_criteria"> <!-- Seacrh criteria starts -->
							<div class="block">
								<ul class="blocklist">
									<li>
										<label for="text1"><spring:message htmlEscape="true" code="Label.LOCALBODYCODE"></spring:message></label>
										<form:input type="text" class="frmfield" id="text1" maxlength="8" onkeypress="return validateNumericKeys(event)" path="code" />
									</li>
									<li>
										<label for="text2"><spring:message htmlEscape="true" code="Label.LOCALGOVTBODYNAME"></spring:message></label>
										<form:input type="text" class="frmfield" id="text2" onkeypress="return validateAlphanumericKeysStateSp(event)" path="districtNameEnglish" />
										
									</li>
									<li>
										<div id="errProSelectDist" class="errormsg"></div>
										<div id="errInsertDistCodeorDistName" class="errormsg" ></div>
									</li>
								</ul>
							</div>
							<div class="block">
								<ul class="captcha_fields">
									<li><img src="captchaImage" alt="Captcha Image" /></li>
									<li>
										<label for="captchaAnswers"><spring:message code="captcha.message" htmlEscape="true"/></label><br/>
										<label><form:input path="captchaAnswers" name="captchaAnswers" id="captchaAnswers" class="frmfield"  autocomplete="off" /></label>
										<div class="errormsg">
										<div id="errorCaptchaMessage" style="color: red;"></div>
											<c:if test="${ captchaSuccess2 == false }">
												<spring:message code="captcha.errorMessage" htmlEscape="true"/>
											</c:if>										
										</div>
										<div class="errormsg" id="errorCaptcha" style="visibility: hidden; display: none;"><spring:message code="captcha.errorMessage"/></div>
									</li>
									<li>
										<input type="submit" onclick="return validateLBViewForm();" id="getbycodeandname" name="Submit" class="btn"  value=<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message>  />
										<input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:go('welcome.do');" />
									</li>
								</ul>
							</div>
							<div class="clear"></div>
							
						</div>  <!-- Seacrh criteria starts -->

				</div>
			</div>	

             </form:form>
                 <form id="form2" name="form2">
                 <input type="hidden" name="isState" id="isState" />	
				<input type="hidden" name="code" id="code" />	
				 <c:if test="${recfound eq false}">
							<div style="height: 40px; border: 1px solid red; background-color: #FFEBE8; padding-top: 10px;" align="center">
												<label id="errorCommon"><spring:message htmlEscape="true" code="error.NOLOCALBODYFOUND"></spring:message></<label>
							</div>
				</c:if>
				
				
				<c:if test="${! empty lgtLocalBodyType}">
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
							<h6 class="title">${message}</h6>
							<table width="100%" class="tbl_no_brdr">

								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="100%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.SNO"></spring:message></td>
												<td width="15%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.LOCALBODYCODE"></spring:message></td>
												<td width="20%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.LOCALBODYNAMEINENG"></spring:message></td>
												<c:if test="${category=='U'}" >
												<td width="10%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.CENSUS2001"></spring:message></td>
											    <td width="10%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.CENSUSCODE2011"></spring:message></td>
												</c:if>
												<%-- <td width="14" align="center"><spring:message
														htmlEscape="true" code="Label.ACTION"></spring:message></td>
												<td width="14%" align="center"><spring:message
														htmlEscape="true" code="Label.ACTION"></spring:message></td> --%>
											</tr>
												
										 <tr class="tblRowTitle tblclear">

												<td width="10%" align="center"><spring:message
														htmlEscape="true" code="Label.VIEW.DETAIL"></spring:message></td>
												<td width="8%" align="center" ><spring:message
												htmlEscape="true" code="View History"></spring:message></td>
												<td width="7%" align="center"><spring:message
														htmlEscape="true" code="Label.VIEW.GOVERNMENTORDER"></spring:message></td>
											 </tr> 
											<c:forEach var="listLocalBodyDetails"
												varStatus="listLocalBodyRow" items="${lgtLocalBodyType}">
												<tr class="tblRowB">
													<td width="6%"><c:out value="${listLocalBodyRow.index+1}" escapeXml="true"></c:out></td>
													<td><c:out value="${listLocalBodyDetails.localBodyCode}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${listLocalBodyDetails.localBodyNameEng}" escapeXml="true"></c:out></td>
													<c:if test="${category=='U'}" >
														<td align="left"><c:out value="${listLocalBodyDetails.census2001Code}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listLocalBodyDetails.census2011Code}" escapeXml="true"></c:out></td>
													</c:if>
													<td width="8%" align="center"><a href="#"><img src="images/view.png" onclick="javascript:viewLocalBodyDetailsNew('N','${listLocalBodyDetails.localBodyCode}');" width="18" height="19" border="0" /> </a></td>
												<td width="8%" align="center"><a href="#"><img src="images/history.png"
											onclick="javascript:viewDetailsforlocalbody('${listLocalBodyDetails.localBodyCode}');"
													width="18" height="19" border="0" alt="View Details" /> </a></td>
													<td width="8%" align="center">
													<c:if test="${listLocalBodyDetails.fileName ne '' }">
													<a href="#"><img src="images/gvt.order.png" onclick="javascript:retrieveFile1('${listLocalBodyDetails.localBodyCode}');" width="18" height="19" border="0" />
													 </a>
													 </c:if>
													 </td>
											</tr>
											</c:forEach>

											
										</table>										
									</td>
								</tr>								
							</table>
							
							
							
							<ul class="blocklist center">
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
															Panchayati Raj</a>, Government of India <br> Site best
															viewed on Internet Explorer (IE)-8 &amp; above, Mozilla
															Firefox-11 &amp; above 
									</p>									
								</div> 
							
						</div>						
					</div>
					
					<div class="buttons center">
						<input type="button" onclick="javascript:go('welcome.do');" value="Close" class="btn" name="Submit3">
				</div>
					
					
					
			
				
				</c:if>
				</form>
				
				
			<input type="hidden" name="direction" id="direction" value="0" />
				<input type="hidden" name="pageType" value="CD" />
			<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>


</body>
</html>